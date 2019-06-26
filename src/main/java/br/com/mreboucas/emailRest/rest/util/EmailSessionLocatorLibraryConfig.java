package br.com.mreboucas.emailRest.rest.util;

import javax.mail.Session;

import br.com.mreboucas.emailUtil.enumeration.EnumPortas;
import br.com.mreboucas.emailRest.rest.interfaces.EmailSessionServiceLocatorInterface;

/**
 * @author Marcelo Rebouças 15 de jun de 2018 - 08:31:10 [marceloreboucas10@gmail.com]
 * @description responsável por carregar as credencias de sessão do e-mail das propriedades definidas no enum local
 */
public class EmailSessionLocatorLibraryConfig implements EmailSessionServiceLocatorInterface {
	
	private enum EnumEmail {

		USER("noreply@cagece.com.br"), 
		KEY("IDSx6YYS996T1g8oRDDJ"), 
		SERVER("172.25.131.80"), 
		TLS(EnumPortas.SMTP_TLS.getPorta()), 
		;

		private String value;

		EnumEmail(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	@Override
	public Session loadEmailSessionConfig(boolean isToAutenticate) {
		
		Session session = null;
		final String msgErro = "Erro ao carregar o email Session a partir das configurações locais da lib de e-mail Util.";

		try {

			String servidorMail = EnumEmail.SERVER.getValue();
			String servidorEmailPorta = EnumEmail.TLS.getValue();
			String usuario = EnumEmail.USER.getValue();
			String senha = EnumEmail.KEY.getValue();
			String remetente = EnumEmail.USER.getValue();

			session = EmailSessionLocator.getSessionFromParameters(servidorMail, servidorEmailPorta, usuario, senha, remetente, isToAutenticate);

		} catch (Exception e) {
			System.out.println(msgErro);
		}

		System.out.println(session != null ? "Session mail from LOCAL CONFIGURATION LIB was successful loaded ;-)" : "");
		
		return session;

	}
}
