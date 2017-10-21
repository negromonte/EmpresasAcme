package br.com.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConectionFactory {

	public static Connection ConectionFactory(){
		try{
			return DriverManager.getConnection("jbdc:sqlserver://10.10.1.5:1434/ACME", "victor", "chupachupa");
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public static Connection getConnection() {
		// TODO Auto-generated method stu
		try{
			return DriverManager.getConnection("jbdc:sqlserver://10.10.1.5:1434/ACME", "victor", "chupachupa");
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public static void closeConnection(Connection connection) {
		
		try {				
			if(connection!=null) {
				connection.close();
			}				
		} catch (SQLException e) {
			throw new RuntimeException("Fechamento da Conexão Falhou!");
		}
}

	public static void closeConnection(Connection connection, PreparedStatement statement) {
	
	try {				
		if(statement!=null) {
			statement.close();
		}				
	} catch (SQLException e) {
		throw new RuntimeException("Fechamento da Conexão Falhou!");
	}
	
	closeConnection(connection);
}

 	public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) {
	
	try {				
		if(resultSet!=null) {
			resultSet.close();
		}				
	} catch (SQLException e) {
		
		throw new RuntimeException("Fechamento da Conexão Falhou!");
		
	}finally {
	
		closeConnection(connection, statement);
	
	}
}
}