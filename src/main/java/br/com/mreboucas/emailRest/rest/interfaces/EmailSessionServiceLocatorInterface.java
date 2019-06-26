package br.com.mreboucas.emailRest.rest.interfaces;

import javax.mail.Session;

/**
 * @author Marcelo Rebouças 15 de jun de 2018 - 08:34:47 [marceloreboucas10@gmail.com]
 */
public interface EmailSessionServiceLocatorInterface {
	
	public Session loadEmailSessionConfig(boolean isToAutenticate);
	
}