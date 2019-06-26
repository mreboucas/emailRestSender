package emailRest;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.ws.rs.core.Response;

import br.com.mreboucas.emailUtil.dto.DtoEmail;
import br.com.mreboucas.emailUtil.exception.EmailSenderException;
import br.com.mreboucas.emailUtil.util.EmailSenderRest;

/**
 * @author Marcelo Rebouças 26 de jun de 2018 - 16:28:20 [marceloreboucas10@gmail.com]
 */
public class RestTest {

	// static String url = "http://localhost:8080/emailRest/api/v1/email/sender";
	static String url = "http://localhost:8080/emailRest/api/v1/email/sender";
	//static String url = "https://praxtst.int.cagece.com.br/emailRest/api/v1/email/sender";


	public static void main(String[] args) {

		 sendPost();
		//getIpServer();

	}

	protected static void getIpServer() {

		InetAddress IP = null;

		try {
			IP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		System.out.println("IP of my system is := " + IP.getHostAddress());
		System.out.println("Hostname is := " + IP.getHostAddress());

	}

	public static void sendPost() {

		String auth = "Basic M01AYSFMOjNNQDQhTHMzbmQzNA==";

		DtoEmail emailDto = new DtoEmail();
		emailDto.setAssunto("TESTE - REST - CLIENT - JAVA 6");
		emailDto.setMensagemCorpoEmail( "	<table width='450px' cellspacing='2' cellpadding='4' border='0' bgcolor='#ffffcc'><tbody><tr><td colspan='3' bgcolor='#1E679A'><h2><font color='#FFFFFF'><center><b>Demanda número 34625/2018</b></center></font></h2></td></tr><tr><td colspan='3'><h3><font color='#FF0000'>PRAZO: <span class='Object' id='OBJ_PREFIX_DWT1400_com_zimbra_date'>03/04/2018</span></font></h3><hr></td></tr><tr><td width='30%'><b>INSCRIÇÃO:</b></td><td colspan='2'>05901570</td></tr><tr><td><b>CLIENTE:</b></td><td colspan='2'>FRANCISCO JOSE DA SILVA F</td></tr><tr><td><b>SS:</b></td><td colspan='2'>Não informado</td></tr><tr><td colspan='3'><hr><b>DESCRICAO:</b>Cliente ingressou com reclamação através do registro do serviço 272 (OUTRAS RECLAMAÇÕES / ELOGIOS / SUGESTÕES / CRITICAS). 	" +
				"	Seguem dados abaixo:	" +
				"		" +
				"	Mensagem: Cliente compareceu a loja para registrar reclamação do corte de água que foi feito em seu imóvel, onde ficou 	" +
				"	bastante chateado pelo motivo que ao executar o serviço acabaram quebrando a tampa da caixa do medidor e deixaram 	" +
				"	jogada na calçada do imóvel, cliente solicita providencias e solicita retorno por parte da empresa. Corte feio no 	" +
				"	dia <span class='Object' id='OBJ_PREFIX_DWT1401_com_zimbra_date'>23/03/2018</span> pela manha.	" +
				"		" +
				"	Dados do cliente	" +
				"	 " +
				"	Nome: FRANCISCO JOSE DA SILVA FILHO	" +
				"	Endereço: RUA 12 CJ CARLOS JEREISSATI, 81 A, CARLOS JEREISSATI I - MARACANAU	" +
				"	Inscrição: 5901570	" +
				"	Telefone: (85) 98678.6324	" +
				"		" +
				"	Verificar real situação, entrar em contato com cliente e responder até o dia <span class='Object' id='OBJ_PREFIX_DWT1402_com_zimbra_date'>03/04/2018</span>	" +
				"		" +
				"	Cordialmente,	" +
				"	Regiana Oliveira	" +
				"	Ouvidoria	" +
				"	</td></tr></tbody></table>	" +
				"	Endereço: RUA 5 <span id='DWT2842' class='ZM-SPELLCHECK-MISSPELLED'>CJ</span> C <span id='DWT2843' class='ZM-SPELLCHECK-MISSPELLED'>JEREISSATI</span>, 567 <span id='DWT2844' class='ZM-SPELLCHECK-MISSPELLED'>CS</span> A, <span id='DWT2845' class='ZM-SPELLCHECK-MISSPELLED'>CARLOS</span> <span id='DWT2846' class='ZM-SPELLCHECK-MISSPELLED'>JEREISSATI</span> I - MARACANAU 	" +
				"	Inscrição: 5447160	" +
				"	Telefone: (85) 98533.8969	" +
				"		" +
				"		" +
				"	Solicitamos verificar real situação, entrar em contato com cliente e nos retornar até dia <span class='Object' id='OBJ_PREFIX_DWT1325_com_zimbra_date'>20/07/2018</span>.	" +
				"		" +
				"	Cordialmente,	" +
				"	Regiana Oliveira	" +
				"	Ouvidoria</td></tr></tbody></table>	");
		
		emailDto.addTo("marcelo.reboucas@cagece.com.br");
		emailDto.addTo("goethe.carvalho@cagece.com.br");

		//System.out.println(replaceBrokenRowDataBaseByHtmlTag(emailDto.getMensagemCorpoEmail()));
		
		Response response = null;
		try {
			response = EmailSenderRest.enviarEmail(emailDto, url, auth);
			if (response != null) {
				System.out.println("Opa, is not null");
			}
		} catch (EmailSenderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (response.getStatus() != 200) {
			System.out.println("Failed : HTTP error code : " + response.getStatus());
		}
	}

}
