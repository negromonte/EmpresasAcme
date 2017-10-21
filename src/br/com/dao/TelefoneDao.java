package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.conexao.ConectionFactory;
import br.com.modelo.Telefone;

public class TelefoneDao {

	Connection connection = null;

	public TelefoneDao() {

		this.connection = ConectionFactory.getConnection();
	}
	
	public boolean create(Integer idFuncionario,Telefone telefone) {

		PreparedStatement statement = null;
		String SQL = "INSERT INTO telefone(id_funcionario, ddd, numero) VALUES (?,?,?)";

		try {

			statement = connection.prepareStatement(SQL);
			statement.setInt(1, idFuncionario);
			statement.setString(2, telefone.getDdd());
			statement.setString(3, telefone.getNumero());

			statement.executeUpdate();

			JOptionPane.showMessageDialog(null, "Telefones Salvos Com Sucesso!");
			return true;

		} catch (SQLException e) {

			System.err.println("Erro ao salvar Telefones. " + e);
			return false;
			
		} finally {

			ConectionFactory.closeConnection(connection, statement);
		}

	}
	
	public List<Telefone> read(Integer idFuncionario){
		
		this.connection = ConectionFactory.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String SQL = "SELECT * FROM telefone WHERE id_funcionario = ?";
		
		List<Telefone> telefones = new ArrayList<>();
		
		try {
			
			statement = connection.prepareStatement(SQL);
			statement.setInt(1, idFuncionario);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Telefone telefone = new Telefone();
				telefone.setId(resultSet.getInt("id_funcionario"));
				telefone.setDdd(resultSet.getString("ddd"));
				telefone.setNumero(resultSet.getString("numero"));
				telefones.add(telefone);	
			}
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Listar Telefones." + e);
			
		}finally {
			
			ConectionFactory.closeConnection(this.connection, statement, resultSet);
		}
		
		return telefones;
	}
	
	public boolean update(int id, Telefone telefone) {
		
		connection = ConectionFactory.getConnection();
		PreparedStatement statement = null;
		String SQL = "UPDATE telefone SET ddd = ?, numero = ? WHERE id_funcionario = ?";

		try {

			statement = connection.prepareStatement(SQL);
			statement.setString(1, telefone.getDdd());
			statement.setString(2, telefone.getNumero());
			statement.setInt(3, id);

			statement.executeUpdate();

			JOptionPane.showMessageDialog(null, "Telefones Atualizados Com Sucesso!");
			return true;

		} catch (SQLException e) {

			System.err.println("Erro ao Atualizar Telefones. " + e);
			return false;
			
		} finally {

			ConectionFactory.closeConnection(connection, statement);
		}

	}
	
	public boolean delete(int id, Telefone telefone) {
		
		String SQL = "DELETE FROM telefone WHERE id_funcionario = ?";
		PreparedStatement statement = null;
		
		try {
			
			statement = connection.prepareStatement(SQL);
			statement.setInt(1, id);
			statement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "E-mail Deletado com Sucesso!");
			return true;
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Deletar E-mail.");
			return false;
			
		}finally {
			
			ConectionFactory.closeConnection(connection, statement);
		}
				
	}
}