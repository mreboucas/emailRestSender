package br.com.mreboucas.emailRest.rest.exception;

/**
 * @author Marcelo Rebouças [marceloreboucas10@gmail.com] - 13 de fev de 2019 as 16:41:15 
 * 
 */
public class AuthenticationException extends Exception {

	/**
	 * @author Marcelo Rebouças - [marceloreboucas10@gmail.com] - 13 de fev de 2019 as 16:43:24
	 *
	 */
	private static final long serialVersionUID = -6778027391981837548L;

	public AuthenticationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AuthenticationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AuthenticationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}