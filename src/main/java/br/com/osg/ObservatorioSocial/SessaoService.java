package br.com.osg.ObservatorioSocial;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.osg.dao.SessaDAO;
import br.com.osg.model.Sessao;

@Path("sessao")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
public class SessaoService {

	@POST
	public Response salvar(String json) {

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
		Sessao sessao = gson.fromJson(json, Sessao.class);

		// java.sql.Date sqlDate = new java.sql.Date(new
		// java.util.Date().getTime());
		java.sql.Timestamp sqlTime = new java.sql.Timestamp(new java.util.Date().getTime());

		// set current date and time
		sessao.setDataPrevista(sqlTime);
		sessao.setHoraPrevista(sqlTime);

		Sessao sessaoResponse = SessaDAO.salvar(sessao);

		if (sessaoResponse != null) {
			String sessaoStr = gson.toJson(sessaoResponse);
			return Response.status(Status.CREATED).entity(sessaoStr).build();

		}

		return Response.status(Status.NOT_FOUND).build();

	}

	@GET
	public Response listar() {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		Gson gson = gsonBuilder.create();

		List<Sessao> sessoes = SessaDAO.listar();

		if (sessoes != null && !sessoes.isEmpty()) {
			String sessaoResponse = gson.toJson(sessoes);
			return Response.ok(sessaoResponse).build();

		}

		return null;

	}

	@PUT
	public Response editar(String json) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		Gson gson = gsonBuilder.create();

		Sessao sessao = SessaDAO.editar(gson.fromJson(json, Sessao.class));

		if (sessao != null) {
			String sessaoResponse = gson.toJson(sessao);
			return Response.status(Status.ACCEPTED).entity(sessaoResponse).build();

		}

		return Response.status(Status.NOT_MODIFIED).build();
	}

	// sessao por id
	@Path("{id}")
	@GET
	public Response listar(@PathParam("id") int codigo) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		Gson gson = gsonBuilder.create();

		Sessao sessao = SessaDAO.buscaPorId(codigo);

		if (sessao != null) {
			String sessaoResponse = gson.toJson(sessao);
			return Response.ok(sessaoResponse).build();

		}

		return Response.status(Status.NOT_FOUND).build();

	}

	// sessao atual
	@GET
	@Path("atual")
	public Response sessaoAtual() {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		Gson gson = gsonBuilder.create();

		List<Sessao> sessao = SessaDAO.buscaSessaoAtual();

		if (sessao != null && !sessao.isEmpty()) {
			String sessaoResponse = gson.toJson(sessao);
			return Response.ok(sessaoResponse).build();

		}

		return Response.status(Status.NOT_FOUND).build();

	}

}
