package br.com.osg.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.osg.connexion.ConnexionFactory;
import br.com.osg.model.Usuario;

public class UsuarioDAO {

	//salvar
	public static Usuario salvar(Usuario usuario) {
		ConnexionFactory connexionFactory = new ConnexionFactory();

		try {
			PreparedStatement preparedStatement = connexionFactory.connect()
					.prepareStatement("insert into usuario (nome, senha	) values(?,?)");

			preparedStatement.setString(1, usuario.getNome());
			preparedStatement.setString(2, usuario.getSenha());

			preparedStatement.execute();
			

		} catch (SQLException ex) {
			System.out.println("Erro Salvar: " + ex);
			return null;

		}
		return usuario;

	}

	//editar
	public static Usuario editar(Usuario usuario) {
		
		ConnexionFactory connexionFactory = new ConnexionFactory();
		
		try {
			connexionFactory.connect();
			PreparedStatement preparedStatement = connexionFactory.connection.prepareStatement
					("update usuario set nome=?, senha=? where usuario_id=?");
			
			preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getSenha());
            preparedStatement.setInt(3, usuario.getUsuario_id());
            
            preparedStatement.execute();
            
            connexionFactory.disconnect();
            
            
		} catch (SQLException ex) {
			
			System.out.println("Erro Editar: " + ex);
			return null;
		}
		
		return usuario;
	}
	
	//apagar
	public static Usuario apagar(Usuario usuario){
		
		ConnexionFactory connexionFactory = new ConnexionFactory();
		
		try {
            PreparedStatement preparedStatement = connexionFactory.connect().prepareStatement
                ("delete from usuario where usuario_id=?");
            
            preparedStatement.setInt(1, usuario.getUsuario_id());
            preparedStatement.execute();
            
            
        } catch (SQLException ex) {
            
            System.out.println("Erro Excluir: " + ex);
            return null;
        }
		
		connexionFactory.disconnect();
		
		return usuario;
		
	}
	
	//buscar por codigo
	public static Usuario buscarPorCodigo(String usuario_id){
		
		ConnexionFactory connexionFactory = new ConnexionFactory();
		connexionFactory.connect();
		connexionFactory.executeSQL("SELECT * FROM usuario WHERE usuario_id= " + Integer.parseInt(usuario_id));
		Usuario usuario = new Usuario();
		
		try {
			
			if(connexionFactory.resultSet.first()){
				
                usuario.setUsuario_id(connexionFactory.resultSet.getInt("usuario_id"));
                usuario.setNome(connexionFactory.resultSet.getString("nome"));
                usuario.setSenha(connexionFactory.resultSet.getString("senha"));
                
                connexionFactory.resultSet.close();
            	
			}
            
            
        } catch (SQLException ex) {
            
            System.out.println("Erro Buscar Por Codigo: " + ex);
        }
		
		connexionFactory.disconnect();
	
		return usuario;
	}

	//listar
	public static List<Usuario> listar() {
		
		ConnexionFactory connexionFactory = new ConnexionFactory();
		connexionFactory.connect();
		
		List<Usuario> usuarios = new ArrayList<>();
		
		connexionFactory.executeSQL("SELECT * FROM usuario ORDER BY nome ASC");
		
		try {
			
			
			while(connexionFactory.resultSet.next()){
			
				Usuario usuario = new Usuario();
            	
                usuario.setUsuario_id(connexionFactory.resultSet.getInt("usuario_id"));
                usuario.setNome(connexionFactory.resultSet.getString("nome"));
                usuario.setSenha(connexionFactory.resultSet.getString("senha"));
                
                usuarios.add(usuario);
                
			}
            
        } catch (SQLException ex) {
            System.out.println("Erro Listar: " + ex);
            return null;
        }
		
		return usuarios;
	
	}

}
