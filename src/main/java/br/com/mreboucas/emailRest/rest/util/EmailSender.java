package br.com.mreboucas.emailRest.rest.util;

import br.com.mreboucas.emailRest.rest.exception.DestinatarioEmailInvalidoException;
import br.com.mreboucas.emailRest.rest.exception.EmailNaoEnviadoException;
import br.com.mreboucas.emailUtil.dto.DtoEmail;
import br.com.mreboucas.emailUtil.exception.EmailSenderException;

/**
 * @author Marcelo Rebouças 15 de jun de 2018 - 15:49:14 [marceloreboucas10@gmail.com]
 */
public abstract class EmailSender {

	public static void enviarEmail(DtoEmail emailDto) throws EmailSenderException, DestinatarioEmailInvalidoException, EmailNaoEnviadoException {

		if (emailDto != null) {

			emailDto.setMensagemCorpoEmail(EmailUtil.replaceBrokenRowDataBaseByHtmlTag(emailDto.getMensagemCorpoEmail()));
			
			if (!emailDto.getEmailConfiguracaoDto().getUtilizaThread()) {

				//ServerUtil.setIpAddress(emailDto);

				MailClientSender.senderEmail(emailDto);

			} else {

				enviarEmailThread(emailDto);

			}
		}
	}

	public static void enviarEmailThread(DtoEmail emailDto) throws EmailSenderException {

		EmailThreadSender mailThreadUtil = new EmailThreadSender(emailDto);

		mailThreadUtil.run();

	}

	/**
	 * @author Marcelo Rebouças 26 de mar de 2018 - 10:30:16 [marceloreboucas10@gmail.com]
	 * @param emailDto
	 * @return void
	 */
	/*
	 * public static void enviarEmailThread(EmailDto emailDto) {
	 * 
	 * Runnable task = () -> { enviarEmail(emailDto); };
	 * 
	 * task.run();
	 * 
	 * new Thread(task).start();;
	 * 
	 * }
	 */

	/**
	 * Escrever o conteúdo de um exceção numa string.
	 * 
	 * @author Aldivone Correia[aldivone@gmail.com] - 23/07/2009 - 10:01:21
	 * @param e Exceção a ser impressa.
	 * @return O conteúdo da exceção em uma string.
	 */
	/*
	 * public static String imprimirExcecaoEmString(final Exception e) { try { final StringWriter sw = new StringWriter();
	 * 
	 * final PrintWriter pw = new PrintWriter(sw);
	 * 
	 * e.printStackTrace(pw);
	 * 
	 * final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy aa hh:mm:ss");
	 * 
	 * final String text = "\r\n" + sdf.format(new Date()) + "\r\n" + sw.toString();
	 * 
	 * return "------\r\n" + text + "------\r\n"; } catch (final Exception e2) { return "Erro ao escrever a exceção na string!"; } }
	 */

}
