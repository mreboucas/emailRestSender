package br.com.mreboucas.emailRest.rest.security;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * @author Marcelo Rebouças 11 de mai de 2018 - 14:03:42 [marceloreboucas10@gmail.com]
 * 
 */
@Provider
@SuppressWarnings("unused")
public class ResourceInterceptor implements ContainerRequestFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceInterceptor.class);
	
	private String user;
	private String key;
	private String authWs;
	
	//Load class from request origin
	@Context
	ResourceInfo resourceInfo;
	
	@Context
	ResourceContext resourceContext;
	
	@Context
	protected ContainerRequestContext requestContext;

	private void setCredentials(String user, String key) {
		this.user = user;
		this.key = key;
		this.authWs = user+":"+key;
	}
	
	@Context
	HttpServletRequest webRequest;

	private static final String REALM = "example";
	private static final String AUTHENTICATION_SCHEME = "Bearer";
	
	@AroundInvoke
	public Object interceptor(InvocationContext ctx) throws Exception {
		
		try {
			checkSecurityInterceptor(webRequest);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return ctx.proceed();
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		// Validate the Authorization header
/*		if (!isTokenBasedAuthentication(authorizationHeader)) {
			abortWithUnauthorized(requestContext);
			return;
		}*/
		checkSecurity(requestContext);
	}
	
	private void checkSecurityInterceptor(HttpServletRequest resquest) throws Exception {
		try {

			Boolean isSecurityAnnotationPresent = isSecurityAnnotationPresent(resourceInfo.getResourceClass());
			
			if (isSecurityAnnotationPresent) {
				
				// Get the Authorization header from the request
				String authorizationHeader = resquest.getHeader(HttpHeaders.AUTHORIZATION);
				// Extract the token from the Authorization header
				String authenticateCredencialsRequest = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

				AuthenticationResourceService.authenticate(authenticateCredencialsRequest, this.authWs);

			}

		} catch (Exception e) {
			abortWithUnauthorized(requestContext);
			throw e;
		}
	}
	
	private void checkSecurity(ContainerRequestContext requestContext) {
		try {

			Boolean isSecurityAnnotationPresent = isSecurityAnnotationPresent(resourceInfo.getResourceClass());
			
			if (isSecurityAnnotationPresent) {
				
				// Get the Authorization header from the request
				String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
				// Extract the token from the Authorization header
				String authenticateCredencialsRequest = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

				AuthenticationResourceService.authenticate(authenticateCredencialsRequest, this.authWs);

			}

		} catch (Exception e) {
			abortWithUnauthorized(requestContext);
		}
	}
	/**
	 * @author Marcelo Rebouças 28 de jun de 2018 - 12:00:44 [marceloreboucas10@gmail.com]
	 * @description checa se a anotação SecurityChecked está presente no método invocado. 		
	 * @param clazz
	 * @return
	 * @throws Exception Boolean
	 */
	@SuppressWarnings("unchecked")
	private Boolean isSecurityAnnotationPresent(@SuppressWarnings("rawtypes") Class clazz) throws Exception {
		
		Object obj;
		try {
			
			LOGGER.info("Checking if security annotation is presente from class: " + clazz);
			
			obj = clazz.newInstance();
			Method method = null;
			
			SecurityChecked securityCheckedAnnotation = resourceInfo.getResourceMethod().getDeclaredAnnotation(SecurityChecked.class);
			
			if (securityCheckedAnnotation != null) {

				String user = null;
				String key = null;
				
				user = securityCheckedAnnotation.user();
				key = securityCheckedAnnotation.key();
				
				/*@SuppressWarnings("rawtypes")
				Class noparams[] = {};
				
				method = clazz.getDeclaredMethod("getUser", noparams);
				user = method != null ? (String) method.invoke(obj, "") : null;
				
				method = clazz.getDeclaredMethod("getKey", noparams);
				key = method != null ? (String) method.invoke(obj, "") : null;*/
				
				setCredentials(user, key);
				
				return Boolean.TRUE;
				
			}
			
		} catch (Exception e) {
			LOGGER.error(e.toString());
			throw new Exception();
		}
		
		
		return Boolean.FALSE;
		
	}

	private boolean isTokenBasedAuthentication(String authorizationHeader) {

		// Check if the Authorization header is valid
		// It must not be null and must be prefixed with "Bearer" plus a whitespace
		// The authentication scheme comparison must be case-insensitive
		return authorizationHeader != null && authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
	}

	private void abortWithUnauthorized(ContainerRequestContext requestContext) {

		// Abort the filter chain with a 401 status code response
		// The WWW-Authenticate header is sent along with the response
		requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"").build());
	}

/*	private void validateAcess(String token) throws Exception {
		
		if (token != null);
		
		byte[] bytes = Base64.getDecoder().decode(token);
		String decoder = new String(bytes);
			
		
	}*/

	/**
	 * @author Marcelo Rebouças 11 de mai de 2018 - 15:41:00 [marceloreboucas10@gmail.com]
	 * @return void
	 */
	private void renewSessionSystem() {

		webRequest.getSession(Boolean.TRUE);

	}

}
