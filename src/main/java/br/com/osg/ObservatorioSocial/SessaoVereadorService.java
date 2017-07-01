package br.com.osg.ObservatorioSocial;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.osg.dao.SessaoVereadorDAO;
import br.com.osg.model.SessaoVereador;

@Path("sessaoVereador")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML} )
public class SessaoVereadorService {
	
	@POST
	public Response salvar(String json) {
		
		Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
	    SessaoVereador sessaoVereador = gson.fromJson(json, SessaoVereador.class);
	      
	    SessaoVereador sessaoVereadorResponse = SessaoVereadorDAO.salvar(sessaoVereador);
	    
	    if(sessaoVereadorResponse != null){
	    	
			return Response.status(Status.CREATED).entity(sessaoVereadorResponse).build();
			
		}
	
		return Response.status(Status.NOT_FOUND).build();
		
		
	}
	
	@GET
	public Response listar(){
		List<SessaoVereador> sessaoVereadors = SessaoVereadorDAO.listar();
		
		Gson gson = new Gson();
		
		if(sessaoVereadors != null && !sessaoVereadors.isEmpty()){
			
			String sessaoVereadoresStr = gson.toJson(sessaoVereadors);
			return Response.ok(sessaoVereadoresStr).build();
			
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}
	
	
	@GET
	@Path("{id}")
	public Response eventos(@PathParam("id") int codigo){
	
		List<SessaoVereador> sessaoVereador = SessaoVereadorDAO.buscarEventos(codigo);
		Gson gson = new Gson();
	
		
		if(sessaoVereador != null && !sessaoVereador.isEmpty()){
			
			String sessaoVereadoresStr = gson.toJson(sessaoVereador);
			return Response.ok(sessaoVereadoresStr).build();
			
		}
		
		return Response.status(Status.NOT_FOUND).build();

		
	}
	
	@GET
	@Path("log/{id}")
	public Response logs(@PathParam("id") int codigo){
	
		List<SessaoVereador> sessaoVereador = SessaoVereadorDAO.buscarLogs(codigo);
		Gson gson = new Gson();
	
		
		if(sessaoVereador != null && !sessaoVereador.isEmpty()){
			
			String sessaoVereadoresStr = gson.toJson(sessaoVereador);
			return Response.ok(sessaoVereadoresStr).build();
			
		}
		
		return Response.status(Status.NOT_FOUND).build();

		
	}

}
