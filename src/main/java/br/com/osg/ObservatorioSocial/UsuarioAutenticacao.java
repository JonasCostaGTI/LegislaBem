package br.com.osg.ObservatorioSocial;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.shiro.crypto.hash.SimpleHash;

import com.google.gson.Gson;

import br.com.osg.dao.UsuarioDAO;
import br.com.osg.model.Usuario;

@Path("users/me")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML})
public class UsuarioAutenticacao {
	
	@POST
	public Response autenticacao(String json){
		List<Usuario> usuarios = UsuarioDAO.listar();
		
		Gson gson = new Gson();
		Usuario usuarioJson = gson.fromJson(json, Usuario.class);
		
		SimpleHash simpleHash = new SimpleHash("md5",usuarioJson.getSenha());
		usuarioJson.setSenha(simpleHash.toHex());
		
		Usuario usuarioLogado = usuarioAutentico(usuarios, usuarioJson);

		if(usuarioLogado != null){
			String usuarioResponse = gson.toJson(usuarioLogado);
			return Response.ok(usuarioResponse).build();
		}
		
			
		return Response.status(Status.FORBIDDEN).build();
		
	}

	private Usuario usuarioAutentico(List<Usuario> usuarios, Usuario usuarioJson) {
		
		for (Usuario usuario : usuarios) {
			if(usuario.getSenha().equals(usuarioJson.getSenha()) && 
					usuario.getNome().equals(usuarioJson.getNome())){
				return usuario;
			}
		}
		
		return null;
	}

}
