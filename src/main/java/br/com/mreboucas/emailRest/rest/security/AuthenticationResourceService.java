package br.com.mreboucas.emailRest.rest.security;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;
import javax.interceptor.AroundInvoke;
import org.apache.commons.lang.StringUtils;

/**
 * @author Marcelo Rebouças 27 de jun de 2018 - 10:07:30 [marceloreboucas10@gmail.com]
 */
public class AuthenticationResourceService {

	private static final String AUTHENTICATION_SCHEME = "Basic";
	
	/**
	 * @author Marcelo Rebouças 28 de jun de 2018 - 12:20:17 [marceloreboucas10@gmail.com]
	 * @param authenticateCredencialsRequest
	 * @param authWs
	 * @throws Exception 
	 * @return void
	 */
	protected static void authenticate(String authenticateCredencialsRequest, String authWs) throws Exception {

		if (!StringUtils.isNotBlank(authenticateCredencialsRequest))
			throw new Exception();
		
		// header value format will be "Basic encodedstring" for Basic
		// authentication. Example "Basic YWRtaW46YWRtaW4="
		final String encodedUserPassword = authenticateCredencialsRequest.replaceFirst(AUTHENTICATION_SCHEME + " ", "");
		String usernameAndPassword = null;
		try {
			
			byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
			
		} catch (IOException e) {
			throw new Exception();
		}
		
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String usernameRequest = tokenizer.nextToken();
		final String passwordRequest = tokenizer.nextToken();
		
		final StringTokenizer tokenizerWs = new StringTokenizer(authWs, ":");
		final String usernameWs = tokenizerWs.nextToken();
		final String passwordWs = tokenizerWs.nextToken();

		/*get*/
		// we have fixed the userid and password as admin
		// call some UserService/LDAP here
		boolean authenticationStatus = usernameWs.equals(usernameRequest) && passwordWs.equals(passwordRequest);

		if (!authenticationStatus)
			throw new Exception();
		
	}
}