package br.com.mreboucas.emailRest.rest.security;

import java.io.Serializable;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Marcelo Rebou�as 28 de jun de 2018 - 08:37:50 [marceloreboucas10@gmail.com]
 * @description 		
 */
@Deprecated
@Interceptor
//@SecurityChecked
public class SecurityCheckInterceptor implements Serializable {
   
	public SecurityCheckInterceptor() {}
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityCheckInterceptor.class);
   
   @AroundInvoke
   public Object checkSecurity(InvocationContext context) throws Exception {
   	
   	//LOGGER.info("validating token: " + authToken); 
   	
   	/* verifique os par�metros ou fa�a uma verifica��o de seguran�a gen�rica antes de chamar o
          m�todo original */
       Object[] params = context.getParameters();

       /* se a valida��o de seguran�a falhar, � poss�vel lan�ar uma exce��o */

       /* chame o m�todo proceed() para chamar o m�todo original */
       Object ret = context.proceed();

       /* execute qualquer p�s-trabalho da chamada de m�todo */
       return ret;
   }

/*  @Override
   public ServerResponse preProcess(final HttpRequest request, final ResourceMethod method) throws Failure, WebApplicationException {
       final List<String> authToken = request.getHttpHeaders().getRequestHeader("X-AUTH");

       if (authToken == null || !isValidToken(authToken.get(0))) {
           final ServerResponse serverResponse = new ServerResponse();
           serverResponse.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
           return serverResponse;
       }

       return null;
   }

   private static boolean isValidToken(@Nonnull final String authToken) {
       LOGGER.info("validating token: " + authToken);
       return true;
   }

   @SuppressWarnings("rawtypes")
   @Override
   public boolean accept(final Class declaring, final Method method) {
       // return declaring.isAnnotationPresent(SecurityChecked.class); // if annotation on class
       return method.isAnnotationPresent(SecurityChecked.class);
   }*/

}
