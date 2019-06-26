package br.com.mreboucas.emailRest.rest.util;

import br.com.mreboucas.emailUtil.dto.DtoEmail;
import br.com.mreboucas.emailUtil.exception.EmailSenderException;

/**
 * @author Marcelo Rebouças 15 de jun de 2018 - 15:28:13 [marceloreboucas10@gmail.com]
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
			
		} catch (EmailSenderException e) {
			
			e.printStackTrace();
			
		}
	}
}