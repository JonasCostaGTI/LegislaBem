package br.com.osg.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.osg.connexion.ConnexionFactory;
import br.com.osg.model.Sessao;

public class SessaDAO {

	// salvar
	public static Sessao salvar(Sessao sessao) {
		ConnexionFactory connexionFactory = new ConnexionFactory();

		try {
			PreparedStatement preparedStatement = connexionFactory.connect().prepareStatement(
					"insert into sessao (tipo, numero, dataprevista, horaprevista, datarealizada, horainicio, horatermino, status) "
							+ "values(?,?,?,?,?,?,?,?)");

			preparedStatement.setString(1, String.valueOf(sessao.getTipo()));
			preparedStatement.setInt(2, sessao.getNumero());
			preparedStatement.setTimestamp(3, new java.sql.Timestamp(sessao.getDataPrevista().getTime()));
			preparedStatement.setTimestamp(4, new java.sql.Timestamp(sessao.getHoraPrevista().getTime()));
			preparedStatement.setTimestamp(5, new java.sql.Timestamp(sessao.getDataRealizada().getTime()));
			preparedStatement.setTimestamp(6, new java.sql.Timestamp(sessao.getHoraInicio().getTime()));
			preparedStatement.setTimestamp(7, new java.sql.Timestamp(sessao.getHoraTermino().getTime()));
			preparedStatement.setString(8, String.valueOf(sessao.getStatus()));

			preparedStatement.execute();

		} catch (SQLException ex) {

			System.out.println("Erro Salvar: " + ex);
			return null;

		}
		return sessao;

	}

	//listar
	public static List<Sessao> listar() {

		ConnexionFactory connexionFactory = new ConnexionFactory();
		connexionFactory.connect();

		List<Sessao> sessoes = new ArrayList<>();

		connexionFactory.executeSQL("SELECT * FROM sessao ORDER BY dataprevista ASC");

		try {

			while (connexionFactory.resultSet.next()) {

				Sessao sessao = new Sessao();

				sessao.setSessao_id(connexionFactory.resultSet.getInt("sessao_id"));
				sessao.setTipo(connexionFactory.resultSet.getString("tipo").charAt(0));
				sessao.setNumero(connexionFactory.resultSet.getInt("numero"));
				sessao.setDataPrevista(connexionFactory.resultSet.getTimestamp("dataprevista"));
				sessao.setHoraPrevista(connexionFactory.resultSet.getTimestamp("horaprevista"));
				sessao.setDataRealizada(connexionFactory.resultSet.getTimestamp("datarealizada"));
				sessao.setHoraInicio(connexionFactory.resultSet.getTimestamp("horainicio"));
				sessao.setHoraTermino(connexionFactory.resultSet.getTimestamp("horatermino"));
				sessao.setStatus(connexionFactory.resultSet.getString("status").charAt(0));

				sessoes.add(sessao);

			}

		} catch (SQLException ex) {
			System.out.println("Erro Listar: " + ex);
			return null;
		}

		return sessoes;

	}

	//editar
	public static Sessao editar(Sessao sessao) {
		
		ConnexionFactory connexionFactory = new ConnexionFactory();
		
		try {
			connexionFactory.connect();
			PreparedStatement preparedStatement = connexionFactory.connection.prepareStatement
					("update sessao set tipo=?, numero=?, dataprevista=?, horaprevista=?, "
							+ "datarealizada=?, horainicio=?, horatermino=?, status=?"
							+ " where sessao_id=?");
			
			
			preparedStatement.setString(1, String.valueOf(sessao.getTipo()));
            preparedStatement.setInt(2, sessao.getNumero());
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(sessao.getDataPrevista().getTime()));
            preparedStatement.setTimestamp(4, new java.sql.Timestamp(sessao.getHoraPrevista().getTime()));
            preparedStatement.setTimestamp(5, new java.sql.Timestamp(sessao.getDataRealizada().getTime()));
            preparedStatement.setTimestamp(6, new java.sql.Timestamp(sessao.getHoraInicio().getTime()));
            preparedStatement.setTimestamp(7, new java.sql.Timestamp(sessao.getHoraTermino().getTime()));
            preparedStatement.setString(8, String.valueOf(sessao.getStatus()));
            preparedStatement.setInt(9, sessao.getSessao_id());
            
            preparedStatement.execute();
            
            connexionFactory.disconnect();
            
            
		} catch (SQLException ex) {
			
			System.out.println("Erro Editar: " + ex);
			return null;
		}

		return sessao;
	}

	//busca sessao por id
	public static Sessao buscaPorId(int codigo) {
		
		ConnexionFactory connexionFactory = new ConnexionFactory();
		connexionFactory.connect();
		
		connexionFactory.executeSQL("select * from sessao where sessao_id =  '"+codigo+"' ");
		
		try {
			
			if(connexionFactory.resultSet.first()){
				Sessao sessao = new Sessao();
				
				sessao.setSessao_id(connexionFactory.resultSet.getInt("sessao_id"));
				sessao.setTipo(connexionFactory.resultSet.getString("tipo").charAt(0));
				sessao.setNumero(connexionFactory.resultSet.getInt("numero"));
				sessao.setDataPrevista(connexionFactory.resultSet.getTimestamp("dataprevista"));
				sessao.setHoraPrevista(connexionFactory.resultSet.getTimestamp("horaprevista"));
				sessao.setDataRealizada(connexionFactory.resultSet.getTimestamp("datarealizada"));
				sessao.setHoraInicio(connexionFactory.resultSet.getTimestamp("horainicio"));
				sessao.setHoraTermino(connexionFactory.resultSet.getTimestamp("horatermino"));
				sessao.setStatus(connexionFactory.resultSet.getString("status").charAt(0));
				
				return sessao;
			}
						
		} catch (SQLException e) {
			System.out.println("Erro ao tentar buscar sessao por codigo: " + e);
			return null;
			//e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	
	//busca sessao por Data Atual
	public static List<Sessao> buscaSessaoAtual() {
		
		ConnexionFactory connexionFactory = new ConnexionFactory();
		connexionFactory.connect();
		
		connexionFactory.executeSQL("select * from sessao where DATE(dataprevista) = DATE(NOW())  AND NOT status = 'T'");
		
		List<Sessao> sessoes = new ArrayList<>();
		
		try {
			
			while(connexionFactory.resultSet.next()){
				Sessao sessao = new Sessao();
				
				sessao.setSessao_id(connexionFactory.resultSet.getInt("sessao_id"));
				sessao.setTipo(connexionFactory.resultSet.getString("tipo").charAt(0));
				sessao.setNumero(connexionFactory.resultSet.getInt("numero"));
				sessao.setDataPrevista(connexionFactory.resultSet.getTimestamp("dataprevista"));
				sessao.setHoraPrevista(connexionFactory.resultSet.getTimestamp("horaprevista"));
				sessao.setDataRealizada(connexionFactory.resultSet.getTimestamp("datarealizada"));
				sessao.setHoraInicio(connexionFactory.resultSet.getTimestamp("horainicio"));
				sessao.setHoraTermino(connexionFactory.resultSet.getTimestamp("horatermino"));
				sessao.setStatus(connexionFactory.resultSet.getString("status").charAt(0));
				
				sessoes.add(sessao);
				
				
			}
						
		} catch (SQLException e) {
			System.out.println("Erro ao tentar buscar sessao por dataAtual: " + e);
			//e.printStackTrace();
			return null;
		}
		
		
		
		return sessoes;
	}

}
