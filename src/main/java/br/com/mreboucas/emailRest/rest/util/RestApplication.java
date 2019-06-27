package br.com.mreboucas.emailRest.rest.util;

import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.SecurityContext;
import br.com.mreboucas.emailRest.rest.resource.EmailResource;

/**
 * @author Marcelo Rebouças 6 de abr de 2018 - 10:38:31 [marceloreboucas10@gmail.com]
 */
@ApplicationPath("/api")
public class RestApplication extends Application {
	
	private static ServletContext context;
	private static SecurityContext securityContext;
	
	public static ServletContext getContext() {
		return context;
	}
	public static void setContext(ServletContext context) {
		RestApplication.context = context;
	}
	public static SecurityContext getSecurityContext() {
		return securityContext;
	}
	public static void setSecurityContext(SecurityContext securityContext) {
		RestApplication.securityContext = securityContext;
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> set = new HashSet<Class<?>>();
      set.add(EmailResource.class);
      return set;
	}
}