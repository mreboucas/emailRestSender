package br.com.mreboucas.emailRest.rest.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import br.com.mreboucas.emailUtil.response.ResponseData;

/**
 * @author Marcelo Rebouças 3 de mai de 2018 - 10:22:58 [marceloreboucas10@gmail.com]
 */
public class RestUtil {
	
	public RestUtil() {
		super();
	}

	public static <Thutcha> Response buildResponse(Thutcha t) {
		return t != null ? Response.ok(t).build() : Response.noContent().build();
	}
	
	public static Response buildErrorResponse(Throwable ex) {
		ResponseData responseData = new ResponseData();
		
		responseData.setStatus(Status.INTERNAL_SERVER_ERROR);
		responseData.setException(ex);
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseData).build();
	}
}