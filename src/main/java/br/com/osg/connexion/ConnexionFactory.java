package br.com.osg.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexionFactory {

	private static final String USUARIO = "root";
	private static final String SENHA = "@czc2v6GTI";
	private static final String URL = "jdbc:mysql://localhost:3306/OSG";
	
	public Statement statement;
	public ResultSet resultSet;
	public Connection connection;

	public Connection connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USUARIO, SENHA);
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("Connect: " + ex);
			
		}
		return connection;
	}

	public void executeSQL(String sql) {
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(sql);
		} catch (Exception e) {
			System.out.println("ExecuteSQL: " + e);
			
		}
	}

	public Connection disconnect() {
		try {
			connection.close();
		} catch (SQLException ex) {
			
			System.out.println("Disconnect: " + ex);
		}

		return connection;
	}
	
}
