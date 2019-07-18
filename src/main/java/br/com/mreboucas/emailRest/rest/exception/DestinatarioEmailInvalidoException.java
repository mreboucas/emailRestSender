package br.com.mreboucas.emailRest.rest.exception;

/**
 * @author Marcelo Reboucas [marceloreboucas10@gmail.com] - 17 de jul de 2019 as 11:24:00
 */

public class DestinatarioEmailInvalidoException extends Exception {
	
	private static final String MSG_ERORR = "Os destinatários de e-mails são inválidos: ";

	private static final long serialVersionUID = 1L;

	public DestinatarioEmailInvalidoException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DestinatarioEmailInvalidoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DestinatarioEmailInvalidoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public DestinatarioEmailInvalidoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public DestinatarioEmailInvalidoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getMessage() {
		return MSG_ERORR + super.getMessage();
	}
}
