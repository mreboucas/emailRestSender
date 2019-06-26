package br.com.mreboucas.emailRest.rest.util;

import javax.mail.Session;

import br.com.mreboucas.emailRest.rest.interfaces.EmailSessionServiceLocatorInterface;

/**
 * @author Marcelo Rebouças 15 de jun de 2018 - 10:13:32 [marceloreboucas10@gmail.com]
 */
public class EmailSessionLocatorSystemConfig implements EmailSessionServiceLocatorInterface {

	@Override
	public Session loadEmailSessionConfig(boolean isToAutenticate) {

		Session session = null;
		final String msgErro = "Erro ao carregar o email Session a partir das configurações do STANDALONE jboss ou do catalina do G@TO TOM";

		try {

			String servidorMail = System.getProperty(EmailSessionLocator.SERVIDOR_EMAIL_PROPERTY);
			String servidorEmailPorta = System.getProperty(EmailSessionLocator.SERVIDOR_EMAIL_PORTA_PROPERTY);
			String usuario = System.getProperty(EmailSessionLocator.USUARIO_ENVIA_EMAIL_PROPERTY);
			String senha = System.getProperty(EmailSessionLocator.SENHA_USUARIO_EMAIL_PROPERTY);
			String remetente = System.getProperty(EmailSessionLocator.USUARIO_ENVIA_EMAIL_PROPERTY);

			session = EmailSessionLocator.getSessionFromParameters(servidorMail, servidorEmailPorta, usuario, senha, remetente, isToAutenticate);

		} catch (Exception e) {
			System.out.println(msgErro);
		}
		
		System.out.println(session != null ? "Session mail from STANDALONE/CATALINA was successful loaded ;-)" : "");

		return session;
	}
}