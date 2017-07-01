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

import org.apache.shiro.crypto.hash.SimpleHash;

import com.google.gson.Gson;

import br.com.osg.dao.UsuarioDAO;
import br.com.osg.model.Usuario;

@Path("usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML} )
public class UsuarioService {
	
	@POST
	public Response salvar(String json){
		
		
		Gson gson = new Gson();
		Usuario usuario = gson.fromJson(json, Usuario.class);
		
		SimpleHash simpleHash = new SimpleHash("md5",usuario.getSenha());
		usuario.setSenha(simpleHash.toHex());
		
	
		
		if(UsuarioDAO.salvar(usuario) != null){
			String usuarioResponse = gson.toJson(usuario);
			return Response.status(Status.CREATED).entity(usuarioResponse).build();
		}

		return Response.status(Status.NOT_FOUND).build();
	
	}
	
	@PUT
	public Response editar(String json){
		
		Gson gson = new Gson();
		Usuario usuario = gson.fromJson(json, Usuario.class);

		SimpleHash simpleHash = new SimpleHash("md5",usuario.getSenha());
		usuario.setSenha(simpleHash.toHex());
		
		
		if(UsuarioDAO.editar(usuario) != null){
			String usuarioResponse = gson.toJson(usuario);
			return Response.status(Status.ACCEPTED).entity(usuarioResponse).build();
		}
	
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@DELETE
	@Path("{usuario_id}")
	public Response apagar(@PathParam("usuario_id") String id){
		Gson gson = new Gson();
		Usuario usuario = UsuarioDAO.apagar(UsuarioDAO.buscarPorCodigo(id));
		
		if(usuario != null){
			String usuarioResponse = gson.toJson(usuario);
			return Response.status(Status.ACCEPTED).entity(usuarioResponse).build();
		}
				
		return Response.status(Status.NOT_FOUND).build();
		
	}
	
	@GET
	public Response listar(){
		List<Usuario> usuarios = UsuarioDAO.listar();
		
		Gson gson = new Gson();
		
		
		if(usuarios != null && !usuarios.isEmpty()){
			String usuarioResponse = gson.toJson(usuarios);
			return Response.ok(usuarioResponse).build();
		}

		return Response.status(Status.NOT_FOUND).build();
		
	}	

}
