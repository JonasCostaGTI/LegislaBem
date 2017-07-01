package br.com.osg.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.osg.connexion.ConnexionFactory;
import br.com.osg.model.Vereador;

public class VereadorDAO {

	// salvar
	public static Vereador salvar(Vereador vereador) {
		ConnexionFactory connexionFactory = new ConnexionFactory();

		try {
			PreparedStatement preparedStatement = connexionFactory.connect()
					.prepareStatement("insert into vereador (nome, partido, status, presente) values(?,?,?,?)");

			preparedStatement.setString(1, vereador.getNome());
			preparedStatement.setString(2, vereador.getPartido());
			preparedStatement.setString(3, String.valueOf(vereador.getStatus()));
			preparedStatement.setString(4, String.valueOf(vereador.getPresente()));

			preparedStatement.execute();

		} catch (SQLException ex) {

			System.out.println("Erro Salvar: " + ex);
			return null;

		}
		return vereador;

	}

	// editar
	public static Vereador editar(Vereador vereador) {

		ConnexionFactory connexionFactory = new ConnexionFactory();

		try {
			connexionFactory.connect();
			PreparedStatement preparedStatement = connexionFactory.connection
					.prepareStatement("update vereador set nome=?, partido=?, status=?, presente=? where vereador_id=?");

			preparedStatement.setString(1, vereador.getNome());
			preparedStatement.setString(2, vereador.getPartido());
			preparedStatement.setString(3, String.valueOf(vereador.getStatus()));
			preparedStatement.setString(4, String.valueOf(vereador.getPresente()));
			preparedStatement.setInt(5, vereador.getVereador_id());

			preparedStatement.execute();

			connexionFactory.disconnect();

		} catch (SQLException ex) {

			System.out.println("Erro Editar: " + ex);
		}

		return vereador;
	}

	// apagar
	public static Vereador apagar(Vereador vereador) {

		ConnexionFactory connexionFactory = new ConnexionFactory();

		try {
			PreparedStatement preparedStatement = connexionFactory.connect()
					.prepareStatement("delete from vereador where vereador_id=?");

			preparedStatement.setInt(1, vereador.getVereador_id());
			preparedStatement.execute();

		} catch (SQLException ex) {

			System.out.println("Erro Excluir: " + ex);
			return null;
		}

		connexionFactory.disconnect();

		return vereador;

	}

	//buscar por codigo
	public static Vereador buscarPorCodigo(String id) {

		ConnexionFactory connexionFactory = new ConnexionFactory();
		connexionFactory.connect();
		connexionFactory.executeSQL("SELECT * FROM vereador WHERE vereador_id= " + Integer.parseInt(id));
		Vereador vereador = new Vereador();

		try {

			if (connexionFactory.resultSet.first()) {

				vereador.setVereador_id(connexionFactory.resultSet.getInt("vereador_id"));
				vereador.setNome(connexionFactory.resultSet.getString("nome"));
				vereador.setPartido(connexionFactory.resultSet.getString("partido"));
				vereador.setStatus(connexionFactory.resultSet.getString("status").charAt(0));
				vereador.setPresente(connexionFactory.resultSet.getString("presente").charAt(0));

				connexionFactory.resultSet.close();

			}

		} catch (SQLException ex) {

			System.out.println("Erro Buscar Por Codigo: " + ex);
		}

		connexionFactory.disconnect();

		return vereador;

	}

	//listar
	public static List<Vereador> listar() {
		
		ConnexionFactory connexionFactory = new ConnexionFactory();
		connexionFactory.connect();
		
		List<Vereador> vereadors = new ArrayList<>();
		
		connexionFactory.executeSQL("SELECT * FROM vereador ORDER BY nome ASC");
		
		try {
			
			
			while(connexionFactory.resultSet.next()){
			
				Vereador vereador = new Vereador();
            	
                vereador.setVereador_id(connexionFactory.resultSet.getInt("vereador_id"));
                vereador.setNome(connexionFactory.resultSet.getString("nome"));
                vereador.setPartido(connexionFactory.resultSet.getString("partido"));
                vereador.setStatus(connexionFactory.resultSet.getString("status").charAt(0));
                vereador.setPresente(connexionFactory.resultSet.getString("presente").charAt(0));
                
                vereadors.add(vereador);
                
			}
            
        } catch (SQLException ex) {
            
            System.out.println("Erro Listar: " + ex);
            return null;
        }
		
		return vereadors;
		
	}

}
