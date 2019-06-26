package br.com.mreboucas.emailRest.rest.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.interceptor.InterceptorBinding;

/**
 * @author Marcelo Rebouças 11 de mai de 2018 - 15:18:18 [marceloreboucas10@gmail.com]
 */
@Inherited
@InterceptorBinding
@Target( {ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityChecked {
	
	String user();
	String key();

}