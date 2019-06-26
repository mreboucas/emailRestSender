package br.com.mreboucas.emailRest.rest.exception;

import javax.naming.ServiceUnavailableException;
import javax.resource.NotSupportedException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.mreboucas.emailRest.rest.message.MessageError;
/**
 * @author Marcelo Reboucas 7 de mai de 2018 - 10:10:16 [marceloreboucas10@gmail.com]
 * 
 * 
 * Se vc chegou nessa classe, meus parabéns!!! Vc ganhou um xeirU.
 * 
 * Vc é um "mero" curioso!!!
 */
@Provider
public class MapperHandlerException implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable error) {

		error.printStackTrace();
		MessageError messageError = new MessageError();
		messageError.setMessage("Ocorreu um erro interno no servidor.");
		messageError.setDetails(error.getMessage());
		messageError.setEnumStatusResponse(getEnumStatusByExceptionType(error));

		return Response.serverError().entity(messageError).build();
	}

	/**
	 * @author Marcelo Reboucas 7 de mai de 2018 - 10:29:42 [marceloreboucas10@gmail.com]
	 * @return Status
	 */
	private Status getEnumStatusByExceptionType(Throwable error) {

		if (error instanceof BadRequestException) {
			return Status.BAD_REQUEST;
		} else if (error instanceof NotAuthorizedException) {
			return Status.UNAUTHORIZED;
		} else if (error instanceof ForbiddenException) {
			return Status.FORBIDDEN;
		} else if (error instanceof NotFoundException) {
			return Status.NOT_FOUND;
		} else if (error instanceof NotAllowedException) {
			return Status.METHOD_NOT_ALLOWED;
		} else if (error instanceof NotAcceptableException) {
			return Status.NOT_ACCEPTABLE;
		} else if (error instanceof NotSupportedException) {
			return Status.UNSUPPORTED_MEDIA_TYPE;
		} else if (error instanceof ServiceUnavailableException) {
			return Status.SERVICE_UNAVAILABLE;
		}

		return Status.INTERNAL_SERVER_ERROR;

	}
}
