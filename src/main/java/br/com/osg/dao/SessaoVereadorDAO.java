package br.com.osg.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.osg.connexion.ConnexionFactory;
import br.com.osg.model.SessaoVereador;

public class SessaoVereadorDAO {

	// salvar
	public static SessaoVereador salvar(SessaoVereador sessaoVereador) {

		ConnexionFactory connexionFactory = new ConnexionFactory();

		try {
			PreparedStatement preparedStatement = connexionFactory.connect().prepareStatement(
					"insert into sessao_vereador (sessao_id, vereador_id, datahoraevento, evento) values(?,?,?,?)");

			preparedStatement.setInt(1, sessaoVereador.getSessao());
			preparedStatement.setInt(2, sessaoVereador.getVereador());
			preparedStatement.setTimestamp(3, new java.sql.Timestamp(sessaoVereador.getDataHoraEvento().getTime()));
			preparedStatement.setString(4, String.valueOf(sessaoVereador.getEvento()));

			preparedStatement.execute();

		} catch (SQLException ex) {
			System.out.println("Erro Salvar: " + ex);
			return null;

		}
		return sessaoVereador;

	}

	
	//listar
	public static List<SessaoVereador> listar() {

		ConnexionFactory connexionFactory = new ConnexionFactory();
		connexionFactory.connect();

		List<SessaoVereador> sessaoVereadors = new ArrayList<>();
		
		connexionFactory.executeSQL("SELECT * FROM sessao_vereador ORDER BY sessao_id ASC");

		try {
			
			while (connexionFactory.resultSet.next()) {

				SessaoVereador sessaoVereador = new SessaoVereador();
	
				sessaoVereador.setDataHoraEvento(connexionFactory.resultSet.getTimestamp("datahoraevento"));
				sessaoVereador.setEvento(connexionFactory.resultSet.getString("evento").charAt(0));
				sessaoVereador.setSessao(connexionFactory.resultSet.getInt("sessao_id"));
				sessaoVereador.setSessao_vereador_id(connexionFactory.resultSet.getInt("sessao_vereador_id"));
				sessaoVereador.setVereador(connexionFactory.resultSet.getInt("vereador_id"));
				
		

				sessaoVereadors.add(sessaoVereador);

			}

		} catch (SQLException ex) {
			System.out.println("Erro Listar: " + ex);
			return null;
		}

		return sessaoVereadors;
	}


	
	//busca por evento
	public static List<SessaoVereador> buscarEventos(int codigo) {

		ConnexionFactory connexionFactory = new ConnexionFactory();
		connexionFactory.connect();
		
		List<SessaoVereador> sessaoVereadors = new ArrayList<>();
		
		connexionFactory.executeSQL("SELECT v.nome, count(sv.vereador_id) as "
				+ "ausencias FROM sessao_vereador sv inner join vereador v on "
				+ "sv.vereador_id = v.vereador_id "
				+ "inner join sessao s on s.sessao_id = sv.sessao_id where (1=1) and "
				+ "s.sessao_id ='"+codigo+"' and sv.evento = 'A' group by v.nome "
				+ "order by ausencias DESC");
		
		
		try {
			
			while (connexionFactory.resultSet.next()) {

				SessaoVereador sessaoVereador = new SessaoVereador();

				sessaoVereador.setEvento(connexionFactory.resultSet.getString("ausencias").charAt(0));
				sessaoVereador.setVereador_nome(connexionFactory.resultSet.getString("nome"));
				

				sessaoVereadors.add(sessaoVereador);

			}

		} catch (SQLException ex) {
			System.out.println("Erro Listar: " + ex);
			return null;
		}

		return sessaoVereadors;
	
	}
	
	
	//busca por log
	public static List<SessaoVereador> buscarLogs(int codigo) {

		ConnexionFactory connexionFactory = new ConnexionFactory();
		connexionFactory.connect();
		
		List<SessaoVereador> sessaoVereadors = new ArrayList<>();
		
		connexionFactory.executeSQL("SELECT v.nome, sv.datahoraevento, sv.evento "
				+ "FROM sessao_vereador sv "
				+ "inner join vereador v on sv.vereador_id = v.vereador_id "
				+ "inner join sessao s on s.sessao_id = sv.sessao_id "
				+ "where (1=1) "
				+ "and s.sessao_id ='"+codigo+"' order by sv.datahoraevento, v.nome");
		
		
		try {
			
			while (connexionFactory.resultSet.next()) {

				SessaoVereador sessaoVereador = new SessaoVereador();

				sessaoVereador.setEvento(connexionFactory.resultSet.getString("evento").charAt(0));
				sessaoVereador.setVereador_nome(connexionFactory.resultSet.getString("nome"));
				sessaoVereador.setDataHoraEvento(connexionFactory.resultSet.getTimestamp("datahoraevento"));
				

				sessaoVereadors.add(sessaoVereador);

			}

		} catch (SQLException ex) {
			System.out.println("Erro Listar: " + ex);
			return null;
		}

		return sessaoVereadors;
	
	}

}
