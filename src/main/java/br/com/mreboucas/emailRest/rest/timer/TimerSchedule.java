package br.com.mreboucas.emailRest.rest.timer;

import javax.ejb.Schedule;
import javax.ejb.Singleton;

import br.com.mreboucas.emailRest.rest.util.EmailUtil;
	
@Singleton
public class TimerSchedule {

	@Schedule(dayOfWeek = "*", dayOfMonth = "*", hour = "02,12", minute = "00", second = "00")
	public void setConfigSessionMailAux() {

		EmailUtil.setSessions();

	}
}