package br.com.mreboucas.emailRest.rest.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import br.com.mreboucas.emailUtil.dto.DtoEmailConfiguracao;
import br.com.mreboucas.emailUtil.dto.DtoEmail;

/**
 * @author Marcelo Rebouças 11 de jul de 2018 - 13:33:20 [marceloreboucas10@gmail.com]
 */
public class ServerUtil {

	public static InetAddress getInetAdress() {

		InetAddress IP = null;

		try {

			IP = InetAddress.getLocalHost();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return IP;

	}

	public static String getIpServer() {

		InetAddress IP = getInetAdress();

		return IP != null ? IP.getHostAddress() : "IP não encontrado";

	}

	public static String getHostName() {

		InetAddress IP = getInetAdress();

		return IP != null ? IP.getHostName() : "Hostname não encontrado";

	}

	/**
	 * @param emailDto
	 */
	protected static void setIpAddress(DtoEmail emailDto) {

		if (emailDto.getEmailConfiguracaoDto() == null) {
			emailDto.setEmailConfiguracaoDto(new DtoEmailConfiguracao());
		}

		if (emailDto.getEmailConfiguracaoDto().getParams() == null) {
			emailDto.getEmailConfiguracaoDto().setParams(new HashMap<String, String>());
		}

		emailDto.getEmailConfiguracaoDto().getParams().put(DtoEmailConfiguracao.IP, getIpServer());
		emailDto.getEmailConfiguracaoDto().getParams().put(DtoEmailConfiguracao.HOST_NAME, getHostName());

	}
}
