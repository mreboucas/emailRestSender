package br.com.mreboucas.emailRest.rest.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Session;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import br.com.mreboucas.emailRest.rest.timer.TimerSchedule;

/**
 * @author Marcelo Rebouças 28 de fev de 2018 - 09:53:27 [marceloreboucas10@gmail.com]
 */
public abstract class EmailUtil {

	private static final String DOMINIO_CAGECE = "cagece.com.br";
	private static final String EMAIL_REGEX = "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$";
	
	private static Session session = getSessionEmailAuthenticator();
	private static Session sessionWithoutAuthenticator = getSessionEmailWithoutAuthenticator();
	
	/**
	 * {@link TimerSchedule#setConfigSessionMail}
	 */
	public static void setSessions() {
		session = getSessionEmailAuthenticator();
		sessionWithoutAuthenticator = getSessionEmailWithoutAuthenticator();
	}

	public static Session getSessionEmailAuthenticator() throws RuntimeException {

		return session != null ? session : EmailSessionLocator.getSessionEmailAuthenticator(Boolean.TRUE);

	}
	
	public static Session getSessionEmailWithoutAuthenticator() throws RuntimeException {

		return sessionWithoutAuthenticator != null ? sessionWithoutAuthenticator : EmailSessionLocator.getSessionEmailAuthenticator(Boolean.FALSE);

	}

	/**
	 * Pesquisa dns disponï¿½veis, atravï¿½s do email informado;
	 *
	 * a travï¿½s da fï¿½brica de contexto "com.sun.jndi.dns.DnsContextFactory" e InitialDirContext para realizar pesquisa de DNS.
	 *
	 * @author Dirceu da Silva [dirceu.rodrigues@cagece.com.br - dirceusr@gmail.com] 17/05/2013 - 15:37:15.
	 * @param email
	 * @return boolean
	 * @throws NamingException
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static boolean validaDominioEmail(final String email) {
		try {
			final String dominio = getDominio(email);

			if (dominio.equals(DOMINIO_CAGECE)) {
				return true;
			}

			final java.util.Hashtable env = new java.util.Hashtable();
			env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
			env.put("com.sun.jndi.dns.timeout.initial", "600");
			env.put("com.sun.jndi.dns.timeout.retries", "3");
			env.put("java.naming.provider.url", "dns:");

			final DirContext ictx = new InitialDirContext(env);
			// DNS record types MX:mail exchange record
			final javax.naming.directory.Attributes attrs = ictx.getAttributes(dominio, new String[] {"MX"});
			final Attribute attr = (Attribute) attrs.get("MX");
			final boolean retorno = attr != null && ((Map) attr).size() > 0 ? true : false;
			ictx.close();
			return retorno;
		} catch (final NameNotFoundException e) {
			return false;
		} catch (final NamingException e) {
			return true;
		} catch (final Exception e) {
			return true;
		}
	}
	
	public static String getDominio(final String email) {
		int pos = email.indexOf('@');

		if (pos == -1) {
			return "";
		}
		final String domain = email.substring(++pos);

		return domain;
	}

	/**
	 * Valida o formato do email.
	 * 
	 * @author Dirceu da Silva [dirceu.rodrigues@cagece.com.br - dirceusr@gmail.com] 22/05/2013 - 16:22:03.
	 * @param email
	 * @return
	 */
	public static boolean isEmailValido(final String email) {
		final Pattern p = Pattern.compile(EMAIL_REGEX);
		final Matcher m = p.matcher(email);
		final boolean valido = m.matches();

		return valido;
	}

	/**
	 * 
	 * @author Dirceu da Silva [dirceu.rodrigues@cagece.com.br - dirceusr@gmail.com] 22/05/2013 - 16:25:56.
	 * @param dominio
	 * @param ictx
	 * @param attr
	 * @return
	 * @throws NamingException
	 */
	@SuppressWarnings({"rawtypes", "unused"})
	public static List<String> getListIpDominio(final String dominio, final DirContext ictx, Attribute attr) throws NamingException {
		Attributes attrs;

		// DNS record types A:address record
		attrs = ictx.getAttributes(dominio, new String[] {"A"});
		attr = attrs.get("A");

		final List<String> result = new ArrayList<String>();
		if (attr != null && attr.size() > 0) {
			final NamingEnumeration e = attr.getAll();

			while (e.hasMore()) {
				result.add((String) e.next());
			}
		}
		return result;
	}
	
	/**
	 * @author Marcelo Rebouças Jan 20, 2015 - 3:34:44 PM
	 * @description substitui as quebras de linhas das strings do banco pela tag <br/>
	 *              para haver a quebra de linha no front-end.
	 * @param text
	 * @return String
	 */
	public static String replaceBrokenRowDataBaseByHtmlTag(String text) {

		if (text != null)
			return text.replaceAll("(\r\n|\n)", "<br/>");

		return text;
	}
}