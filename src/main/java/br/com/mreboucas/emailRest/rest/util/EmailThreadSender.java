package br.com.mreboucas.emailRest.rest.util;

import br.com.mreboucas.emailRest.rest.exception.DestinatarioEmailInvalidoException;
import br.com.mreboucas.emailRest.rest.exception.EmailNaoEnviadoException;
import br.com.mreboucas.emailUtil.dto.DtoEmail;

/**
 * @author Marcelo Rebouças 15 de jun de 2018 - 15:28:13
 *         [marceloreboucas10@gmail.com]
 */
class EmailThreadSender implements Runnable {

	DtoEmail emailDto;

	public EmailThreadSender(DtoEmail emailDto) {
		super();
		this.emailDto = emailDto;
	}

	@Override
	public void run() {
		
		try {
			MailClientSender.senderEmail(emailDto);
		} catch (DestinatarioEmailInvalidoException e) {
			e.printStackTrace();
		} catch (EmailNaoEnviadoException e) {
			e.printStackTrace();
		}
	}
}