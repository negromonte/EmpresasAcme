package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.conexao.ConectionFactory;
import br.com.modelo.Dependente;

public class DependenteDao {
	
	Connection connection = null;
	
	public DependenteDao() {
		
		this.connection = ConectionFactory.getConnection();
	}
	
	public boolean create(Integer idFuncionario, Dependente dependente) {
		
		String SQL = "INSERT INTO dependente(id_funcionario, nome) VALUES(?, ?)";
		PreparedStatement statement = null;
				
		try {
			
			statement = connection.prepareStatement(SQL);
			statement.setInt(1, idFuncionario);
			statement.setString(2, dependente.getNome());
			statement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Dependente Cadastrado com Sucesso!");
			return true;
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Cadastrar Dependente. " + e);
			return false;
			
		}finally {
			
			ConectionFactory.closeConnection(connection, statement);
		}	
	}
	
	public List<Dependente> read(Integer idFuncionario){
		
		this.connection = ConectionFactory.getConnection();
		String SQL = "SELECT * FROM dependente WHERE id_funcionario = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		List<Dependente> dependentes = new ArrayList<>();
		
		try {
			
			statement = connection.prepareStatement(SQL);
			statement.setInt(1, idFuncionario);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
					
					Dependente dependente = new Dependente();
					dependente.setId(resultSet.getInt("id_funcionario"));
					dependente.setNome(resultSet.getString("nome"));
					dependentes.add(dependente);
				}
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Listar Dependente" + e);
		
		}finally {
			
			ConectionFactory.closeConnection(this.connection, statement, resultSet);
		}
		
		return dependentes;
	}
	
	public boolean update(int id, Dependente dependente) {
		
		String SQL = "UPDATE dependente SET nome = ? WHERE id_funcionario = ?";
		
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(SQL);
			statement.setString(1, dependente.getNome());
			statement.setLong(2, id);
			
			statement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Dependentes Atualizados com Sucesso!");
			return true;	
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Atualizar Lista de Dependentes." +e);
			return false;
			
		}finally {
			
			ConectionFactory.closeConnection(connection, statement);
			
		}
			
	}
	
	public boolean delete(int id, Dependente dependente) {
		
		String SQL = "DELETE FROM dependente WHERE id_funcionario = ?";
		
		PreparedStatement statement = null;
		
		try {
			
			statement = connection.prepareStatement(SQL);
			statement.setLong(1, id);
			statement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Dependentes Deletados Com Sucesso!");
			return true;
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Deletar Dependentes.");
			return false;
			
		}finally {
			
			ConectionFactory.closeConnection(connection, statement);
			
		}	
	}
}