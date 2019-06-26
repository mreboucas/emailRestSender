package br.com.mreboucas.emailRest.rest.util;

import java.util.Properties;
import javax.mail.Session;
import javax.naming.InitialContext;

import br.com.mreboucas.emailRest.rest.interfaces.EmailSessionServiceLocatorInterface;

/**
 * @author Marcelo Rebouças 15 de jun de 2018 - 08:31:10 [marceloreboucas10@gmail.com]
 */
class EmailSessionLocatorLookup implements EmailSessionServiceLocatorInterface {

	private static final Properties PROPERTIES = new Properties();

	private static final String DEFAULT_JNDI_MAIL = System.getProperty("JNDI_EMAIL_PADRAO", "java:jboss/mail/cagece-mail");

	static {
		PROPERTIES.put("java.naming.factory.initial", "org.jboss.as.naming.InitialContextFactory");
	}

	
	@Override
	public Session loadEmailSessionConfig(boolean isToAutenticate) {

		final String msgErro = "Erro ao realizar LOOKUP das configurações do Subsystem: Mail do Jboss";
		Session session = null;
		
		try {
			
			InitialContext context = new InitialContext(PROPERTIES);
			session = (Session) context.lookup(DEFAULT_JNDI_MAIL);
			
		} catch (Exception e) {
			System.out.println(msgErro);
		}

		if (session != null) {

			// Enable TLS
			session.getProperties().put("mail.smtp.starttls.enable", Boolean.TRUE);
			// Disable SSL
			session.getProperties().put("mail.smtp.ssl.enable", Boolean.FALSE);

		}
		
		System.out.println(session != null ? "Session mail from LOOKUP was successful loaded ;-)" : "");

		return session;
	}
}