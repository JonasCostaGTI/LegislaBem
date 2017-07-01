package br.com.osg.ObservatorioSocial;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import br.com.osg.dao.VereadorDAO;
import br.com.osg.model.Vereador;

@Path("vereador")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML} )
public class VereadorService {

	@POST
	public Response salvar(String json) {

		Gson gson = new Gson();
		Vereador vereador = VereadorDAO.salvar(gson.fromJson(json, Vereador.class));
		
		if(vereador != null){
			String vereadorResponse = gson.toJson(vereador);
			return	Response.status(Status.CREATED).entity(vereadorResponse).build();
		}

		return Response.status(Status.NOT_FOUND).build();

	}

	@PUT
	public Response editar(String json) {

		Gson gson = new Gson();
		Vereador vereador = VereadorDAO.editar(gson.fromJson(json, Vereador.class));
		
		if(vereador != null){
			String vereadorResponse = gson.toJson(vereador);
			return	Response.status(Status.ACCEPTED).entity(vereadorResponse).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();

	}
	
	@DELETE
	@Path("{vereador_id}")
	public Response apagar(@PathParam("vereador_id") String id){
		Gson gson = new Gson();
		Vereador vereador = VereadorDAO.apagar(VereadorDAO.buscarPorCodigo(id));

		if(vereador != null){
			String vereadorResponse = gson.toJson(vereador);
			return	Response.status(Status.ACCEPTED).entity(vereadorResponse).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	public Response listar(){
		List<Vereador> vereadores = VereadorDAO.listar();
		Gson gson = new Gson();
		
		if(vereadores != null && !vereadores.isEmpty()){
			String vereadorResponse = gson.toJson(vereadores);
			return Response.ok(vereadorResponse).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
		
	}

}
