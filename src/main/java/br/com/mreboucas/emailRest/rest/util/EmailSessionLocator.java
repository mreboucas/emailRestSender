package br.com.mreboucas.emailRest.rest.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import br.com.mreboucas.emailRest.rest.interfaces.EmailSessionServiceLocatorInterface;
import br.com.mreboucas.emailUtil.enumeration.EnumSessionLocatorMailType;
import br.com.mreboucas.emailUtil.enumeration.EnumSmtpPropertyMail;

/**
 * @author 212046
 * @author Marcelo Rebouças 10 de mai de 2018 - 14:25:12 [marceloreboucas10@gmail.com]
 */
final class EmailSessionLocator {

	protected static String SERVIDOR_EMAIL_PROPERTY = "SERVIDOR_EMAIL";
	protected static String SERVIDOR_EMAIL_PORTA_PROPERTY = "SERVIDOR_EMAIL_PORTA";
	protected static String USUARIO_ENVIA_EMAIL_PROPERTY = "USUARIO_ENVIA_EMAIL";
	protected static String SENHA_USUARIO_EMAIL_PROPERTY = "SENHA_USUARIO_EMAIL";

	public EmailSessionLocator() {}

	protected static Session getSessionFromParameters(String servidorMail, String servidorEmailPorta, final String usuario, final String senha, String remetente, Boolean isToAutenticate) {
		Authenticator authenticator = null;

		if (isToAutenticate != null && isToAutenticate) {

			authenticator = new Authenticator() {
				public final PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(usuario, senha);
				}
			};
		}

		final Properties mailProps = new Properties();
		mailProps.put("mail.smtp.host", servidorMail);
		mailProps.put("mail.smtp.auth", Boolean.TRUE);
		mailProps.put("mail.smtp.starttls.enable", Boolean.TRUE);
		mailProps.put("mail.smtp.port", servidorEmailPorta);
		mailProps.put("mail.from", remetente);

		Session session = authenticator == null ? Session.getInstance(mailProps) : Session.getInstance(mailProps, authenticator);

		return session;

	}

	/**
	 * @author Marcelo Rebouças 15 de jun de 2018 - 14:32:07 [marceloreboucas10@gmail.com]
	 * @return Session
	 * @throws RuntimeException
	 */
	protected static Session getSessionEmailAuthenticator(Boolean isToAutenticate) throws RuntimeException {

		Session session = null;

		if (session == null) {

			session = getSessionEmailAuthenticator(EnumSessionLocatorMailType.LOOKUP, isToAutenticate);

		}

		if (session == null) {

			session = getSessionEmailAuthenticator(EnumSessionLocatorMailType.STANDALONE_PROPERTIES, isToAutenticate);

		}

		if (session == null) {

			session = getSessionEmailAuthenticator(EnumSessionLocatorMailType.CATALINA_PROPERTIES, isToAutenticate);
		}

		if (session == null) {

			session = getSessionEmailAuthenticator(EnumSessionLocatorMailType.LOCAL_PROPERTIES, isToAutenticate);
		}

		if (session == null) {

			Class<?> clazz = EmailUtil.class;
			Package pack = clazz.getPackage();
			throw new RuntimeException("Não foi possível carregar o SessionEmailAuthenticator: library: email-util.jar & package --> " + pack.getName());
		}

		return session;

	}

	protected static void setPropertiesSessionMail(Map<EnumSmtpPropertyMail, String> enumSmtpPropertyMailMap, Session session) {

		if (enumSmtpPropertyMailMap != null) {

			if (session != null && enumSmtpPropertyMailMap != null) {

				for (Entry<EnumSmtpPropertyMail, String> enumSmtpPropertyMailEntry : enumSmtpPropertyMailMap.entrySet()) {

					session.getProperties().put(enumSmtpPropertyMailEntry.getKey().getProperty(), enumSmtpPropertyMailEntry.getValue());

				}
			}
		}
	}

	private static Session getSessionEmailAuthenticator(EnumSessionLocatorMailType enumMailType, boolean isToAutenticate) throws RuntimeException {

		EmailSessionServiceLocatorInterface emailSessionServiceLocatorInterface;

		switch (enumMailType) {
			case LOOKUP:

				emailSessionServiceLocatorInterface = new EmailSessionLocatorLookup();
				break;

			case STANDALONE_PROPERTIES:

				emailSessionServiceLocatorInterface = new EmailSessionLocatorSystemConfig();
				break;

			case CATALINA_PROPERTIES:

				emailSessionServiceLocatorInterface = new EmailSessionLocatorSystemConfig();
				break;

			default:

				emailSessionServiceLocatorInterface = new EmailSessionLocatorLibraryConfig();
				break;
		}

		return emailSessionServiceLocatorInterface.loadEmailSessionConfig(isToAutenticate);

	}
}
