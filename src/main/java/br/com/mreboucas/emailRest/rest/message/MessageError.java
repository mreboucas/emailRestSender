package br.com.mreboucas.emailRest.rest.message;

import javax.ws.rs.core.Response.Status;
/**
 * @author Marcelo Rebouças 7 de mai de 2018 - 10:19:34 [marceloreboucas10@gmail.com]
 */
public class MessageError {

	private String details;
	private String message;
	private Status enumStatusResponse;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDetails() {
		return details;
	}

	public Status getEnumStatusResponse() {
		return enumStatusResponse;
	}

	public void setEnumStatusResponse(Status enumStatusResponse) {
		this.enumStatusResponse = enumStatusResponse;
	}
}