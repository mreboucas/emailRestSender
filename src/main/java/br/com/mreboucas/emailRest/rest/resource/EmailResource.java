package br.com.mreboucas.emailRest.rest.resource;

import java.io.File;
import java.net.URLConnection;
import java.nio.file.Files;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.sun.jersey.api.client.ClientResponse;

import br.com.mreboucas.emailUtil.dto.DtoAnexoEmail;
import br.com.mreboucas.emailUtil.dto.DtoEmail;
import br.com.mreboucas.emailUtil.enumeration.EnumMimeType;
import br.com.mreboucas.emailUtil.exception.EmailSenderException;
import br.com.mreboucas.emailUtil.util.EmailSenderRest;
import br.com.mreboucas.emailRest.rest.security.ResourceInterceptor;
import br.com.mreboucas.emailRest.rest.security.SecurityChecked;
import br.com.mreboucas.emailRest.rest.util.EmailSender;
import br.com.mreboucas.emailRest.rest.util.RestUtil;

/**
 * @author Marcelo Rebouças 21 de jun de 2018 - 18:43:27 [marceloreboucas10@gmail.com]
 */
@Path("v1/email")
@Produces(MediaType.APPLICATION_JSON)
// @Consumes(MediaType.APPLICATION_JSON)
public class EmailResource extends ResourceInterceptor {

	private final String USER = "3M@a!L";
	private final String KEY = "3M@4!Ls3nd34";
	
	@Path("/sender")
	@POST
	// @SecurityChecked(user = USER, key = KEY)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@SecurityChecked(key = KEY, user = USER)
	public Response senders(DtoEmail emailDto) {

		try {
			
			EmailSender.enviarEmail(emailDto);
			return Response.ok("Olá fofuxo, e-mail enviado.").build();

		} catch (Exception e) {
			e.printStackTrace();
			//return Response.serverError().build();
			return RestUtil.buildErrorResponse(e);
		} catch (EmailSenderException e) {
			e.printStackTrace();
			return RestUtil.buildErrorResponse(e);
		}
	}

	@Path("/senderTeste")
	@GET
	// @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response senderTeste() {

		try {

			// Map<String, Object> paramsCombos = new NgcRelatorioEstatisticoTramitacao().getCombobox();
			DtoEmail emailDto = new DtoEmail("Risoto_de_Rest", "Engolindo um GET da hora!");
			emailDto.getEmailConfiguracaoDto().setSiglaSistema("RESTOSO");
			
			return Response.ok(emailDto).build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Path("/send/{url}")
	@GET
	// @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response send(@PathParam("url") String url) throws Exception, EmailSenderException {

		try {

			// Map<String, Object> paramsCombos = new NgcRelatorioEstatisticoTramitacao().getCombobox();
			DtoEmail emailDto = new DtoEmail("Risoto_de_Rest", "Engolindo um GET da hora!");
			emailDto.setMensagemCorpoEmail("Teste. Bj!"/*
			                                * "Felipe: Max, Max, olha o babau, ele vai te PEGAR. Corree, correeee.\n " +
			                                * "Max: vou ficar pq eu quero que ele me pegue mesmo, estou vivendo um inferno com esse monte de projetos na cagece... Acho que vou virar gay para"
			                                * + "viver mais feliz!!!!\n" + "Felipe: num faz isso rapa!!!!\n" + "Max: eu não suporto +++++\n" + "" + "" +
			                                * "" + "\n\n\n\nBrincadeira pessoal, só para descontrair...\n\n\n" + "" +
			                                * "Vou já enviar esse e-mail par ao grupo-cagece!!!!! kkkkk"
			                                */);
			emailDto.addTo("marcelo.reboucas@cagece.com.br");
//			emailDto.addTo("max.wilkson@cagece.com.br");
//			emailDto.addTo("felipe.avelar@cagece.com.br");
//			emailDto.addTo("diego.freire@cagece.com.br");
			//emailDto.addTo("felipe.souza@cagece.com.br");

			//File file = new File("/home/207608/Downloads/pmbok-v5.pdf");
			File file = new File("/home/207608/Downloads/original.jpg");
			String mime = URLConnection.guessContentTypeFromName(file.getName());
			DtoAnexoEmail anexoEmailDto = new DtoAnexoEmail();
			anexoEmailDto.setName("anexo");
			//anexoEmailDto.addAnexoBase64(Files.readAllBytes(file.toPath()), EnumMimeType.PDF);
			anexoEmailDto.addAnexo(Files.readAllBytes(file.toPath()));
			//anexoEmailDto.exibirAnexoCorpoEmail();
			
			System.out.println(anexoEmailDto.getData().length);
			anexoEmailDto.getData();
			System.out.println(anexoEmailDto.getData());
			emailDto.prioridadeBaixa();
			//4609725
			emailDto.addAnexo(anexoEmailDto);
			
			Response clientResponse = EmailSenderRest.enviarEmail(emailDto, "http://"+url+"/emailRest/api/v1/email/sender", "Basic M01AYSFMOjNNQDQhTHMzbmQzNA==");

			return Response.ok("E-mail enviado com sucesso!!!").build();

		} catch (Exception e) {
			e.printStackTrace();
			//return Response.serverError().build();
			throw e;
		} catch (EmailSenderException e) {
			//e.printStackTrace();
			throw e;
			//return Response.serverError().build();
		}
	}
}
