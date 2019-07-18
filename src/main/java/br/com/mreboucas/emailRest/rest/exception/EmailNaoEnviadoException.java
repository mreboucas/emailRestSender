package br.com.mreboucas.emailRest.rest.exception;

/**
 * @author Marcelo Reboucas [marceloreboucas10@gmail.com] - 17 de jul de 2019 as 11:24:00
 */

public class EmailNaoEnviadoException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmailNaoEnviadoException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public EmailNaoEnviadoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public EmailNaoEnviadoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public EmailNaoEnviadoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public EmailNaoEnviadoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
