package br.com.mreboucas.emailRest.rest.util;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import org.apache.commons.lang.StringUtils;
import br.com.mreboucas.emailUtil.dto.DtoAnexoEmail;
import br.com.mreboucas.emailUtil.dto.DtoEmail;
import br.com.mreboucas.emailUtil.dto.DtoEmailConfiguracao;
import br.com.mreboucas.emailUtil.enumeration.EnumContentTypeMail;
import br.com.mreboucas.emailUtil.enumeration.EnumEncondingType;
import br.com.mreboucas.emailUtil.enumeration.EnumPrioridadeMail;
import br.com.mreboucas.emailUtil.exception.EmailSenderException;
import br.com.mreboucas.emailUtil.util.FileUtil;

/**
 * @author Marcelo Rebouças 10 de jul de 2018 - 11:27:02 [marceloreboucas10@gmail.com]
 */
public class MailClientSender {

	/**
	 * @author Marcelo Rebouças 26 de mar de 2018 - 10:23:17 [marceloreboucas10@gmail.com]
	 * @param emailDto
	 * @return void
	 * @throws Exception
	 * @throws EmailSenderException
	 */
	protected static void senderEmail(DtoEmail emailDto) throws EmailSenderException {

		try {

			final List<InternetAddress> emailsDest = getInternetAdressList(emailDto.getDestinatarioList());
			final List<InternetAddress> emailsDestCC = getInternetAdressList(emailDto.getDestinatarioCCList());
			final List<InternetAddress> emailsDestCCO = getInternetAdressList(emailDto.getDestinatarioCCOList());

			final InternetAddress[] arrayEmailDest = emailsDest.toArray(new InternetAddress[emailsDest.size()]);
			final InternetAddress[] arrayEmailDestCC = emailsDestCC.toArray(new InternetAddress[emailsDestCC.size()]);
			final InternetAddress[] arrayEmailDestCCO = emailsDestCCO.toArray(new InternetAddress[emailsDestCCO.size()]);

			final Session mailSession =
			      emailDto.getEmailConfiguracaoDto() != null && emailDto.getEmailConfiguracaoDto().getIsAuthenticate() ? EmailUtil.getSessionEmailAuthenticator() : EmailUtil.getSessionEmailWithoutAuthenticator();

			mailSession.setDebug(emailDto.getEmailConfiguracaoDto() != null && emailDto.getEmailConfiguracaoDto().getIsDebugMode() != null ? emailDto.getEmailConfiguracaoDto().getIsDebugMode() : Boolean.FALSE);

			EmailSessionLocator.setPropertiesSessionMail(
			      (emailDto.getEmailConfiguracaoDto() != null && emailDto.getEmailConfiguracaoDto().getPropertiesEmailMap() != null ? emailDto.getEmailConfiguracaoDto().getPropertiesEmailMap() : null),
			      mailSession);

			final InternetAddress remetente = new InternetAddress(mailSession.getProperty("mail.from"));

			final Message message = new MimeMessage(mailSession);

			message.setHeader(DtoEmailConfiguracao.IP, emailDto.getEmailConfiguracaoDto() != null && emailDto.getEmailConfiguracaoDto().getParams() != null && emailDto.getEmailConfiguracaoDto().getParams().containsKey(DtoEmailConfiguracao.IP)
			            ? emailDto.getEmailConfiguracaoDto().getParams().get(DtoEmailConfiguracao.IP) : "IP_NAO_CAPTURADO");
			message.setHeader(DtoEmailConfiguracao.HOST_NAME, emailDto.getEmailConfiguracaoDto() != null && emailDto.getEmailConfiguracaoDto().getParams() != null && emailDto.getEmailConfiguracaoDto().getParams().containsKey(DtoEmailConfiguracao.HOST_NAME)
	            ? emailDto.getEmailConfiguracaoDto().getParams().get(DtoEmailConfiguracao.HOST_NAME) : "HOST_NAO_CAPTURADO");

			message.setFrom(remetente);

			message.setRecipients(Message.RecipientType.TO, arrayEmailDest);

			message.setRecipients(Message.RecipientType.CC, arrayEmailDestCC);

			message.setRecipients(Message.RecipientType.BCC, arrayEmailDestCCO);

			message.setSentDate(new Date());
			
			if (emailDto.getEmailConfiguracaoDto().getEnumPrioridadeMail() != null) {
				
				EnumPrioridadeMail enumPrioridadeMail = emailDto.getEmailConfiguracaoDto().getEnumPrioridadeMail();
				if ( EnumPrioridadeMail.isBaixa(enumPrioridadeMail) ) {
					message.addHeader("X-Priority", "5");
				}
				else if ( EnumPrioridadeMail.isNormal(enumPrioridadeMail) ) {
					message.addHeader("X-Priority", "2"); 
				}
				else if (EnumPrioridadeMail.isAlta(enumPrioridadeMail)) {
					message.addHeader("X-Priority", "1");
					message.addHeader("Importance", "High");
					message.addHeader("X-MSMail-Priority", "High");
				}
			}
			
			// message.setSubject("[" + Constants.SIGLA_SISTEMA + "] " + emailDto.getAssunto());
			if (StringUtils.isNotBlank(emailDto.getEmailConfiguracaoDto().getSiglaSistema())) {
				message.setSubject("[" + emailDto.getEmailConfiguracaoDto().getSiglaSistema() + "] " + emailDto.getAssunto());
			} else {
				message.setSubject(emailDto.getAssunto());
			}

			String contentType =
			      emailDto.getEmailConfiguracaoDto() != null && emailDto.getEmailConfiguracaoDto().getEnumContentTypeEmail() != null ? emailDto.getEmailConfiguracaoDto().getEnumContentTypeEmail().getContentType()
			            : EnumContentTypeMail.TEXT_HTML.getContentType();

			contentType += ";charset="
			      + (emailDto.getEmailConfiguracaoDto() != null && emailDto.getEmailConfiguracaoDto().getEnumEncondingType() != null ? emailDto.getEmailConfiguracaoDto().getEnumEncondingType().getEncoding()
			            : EnumEncondingType.UTF_8.getEncoding());

			message.setHeader("Content-Type", contentType);
			message.setHeader("Content-Transfer-Encoding", "quoted-printable");

			final Multipart corpoEmail = new MimeMultipart();

			final MimeBodyPart msg = new MimeBodyPart();

			msg.setContent(emailDto.getMensagemCorpoEmail(), contentType);

			MimeBodyPart parAnexo = null;

			if (ListaUtils.collectionIsNotNullOrIsNotEmpty(emailDto.getAnexoEmailDtoList())) {

				DtoAnexoEmail anexoEmailDto = null;

				for (int i = 0; i < emailDto.getAnexoEmailDtoList().size(); i++) {

					anexoEmailDto = emailDto.getAnexoEmailDtoList().get(i);

					if (anexoEmailDto.getData() != null && anexoEmailDto.getData().length > 0) {

						parAnexo = new MimeBodyPart();

						String mimeType = anexoEmailDto.getEnumMimeType() != null ? anexoEmailDto.getEnumMimeType().getMimeType() : FileUtil.getMimeFromByteArray(anexoEmailDto.getData());
						
						parAnexo.setDataHandler(new DataHandler(new ByteArrayDataSource(anexoEmailDto.getData(), mimeType)));

						if (anexoEmailDto != null && anexoEmailDto.getExibirAnexoCorpo() != null && anexoEmailDto.getExibirAnexoCorpo()) {
							parAnexo.setDisposition(Part.INLINE);
						}
						
						//parAnexo.setFileName(StringUtils.isNotBlank(anexoEmailDto.getName()) ? anexoEmailDto.getName() : "file_anexo" + anexoEmailDto.getEnumMimeType().getExtensaoComPonto());
						parAnexo.setFileName(StringUtils.isNotBlank(anexoEmailDto.getName()) ? anexoEmailDto.getName() : "file_anexo");
						
						corpoEmail.addBodyPart(parAnexo);

					}
				}
			}

			corpoEmail.addBodyPart(msg);
			message.setContent(corpoEmail, contentType);

			Transport.send(message);

		} catch (Throwable e) {
			e.printStackTrace();
			throw new EmailSenderException(e);
		}
	}

	/**
	 * @author Marcelo Rebouças 23 de mar de 2018 - 16:57:32 [marceloreboucas10@gmail.com]
	 * @param destinatarioList
	 * @return List<InternetAddress>
	 * @throws AddressException
	 */
	private static List<InternetAddress> getInternetAdressList(List<String> destinatarioList) throws AddressException {

		final List<InternetAddress> emailsDestinatarioList = new ArrayList<InternetAddress>();

		InternetAddress email = null;

		if (ListaUtils.collectionIsNotNullOrIsNotEmpty(destinatarioList)) {

			ListaUtils.removeDuplicatesElementsFromList(destinatarioList);

			for (final String emailString : destinatarioList) {

				if (EmailUtil.isEmailValido(emailString)) {
					email = new InternetAddress(emailString);
					emailsDestinatarioList.add(email);
				}
			}
		}

		return emailsDestinatarioList;
	}
}
