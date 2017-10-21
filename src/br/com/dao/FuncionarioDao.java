package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import com.mysql.jdbc.Statement;
import br.com.conexao.ConectionFactory;
import br.com.modelo.Funcionario;



public class FuncionarioDao {

	private Connection connection = null;
	private TelefoneDao telefoneDao;
	private DependenteDao dependenteDao;
	private EmailDao emailDao;
	
	public FuncionarioDao (){
		
		this.connection = ConectionFactory.getConnection();
		this.telefoneDao = new TelefoneDao();
		this.dependenteDao = new DependenteDao();
		this.emailDao = new EmailDao();
	}
	
	public Integer create(Integer idDepartamento, Funcionario funcionario){
		
		String SQL = "INSERT INTO funcionario(id_departamento, nome, salario, matricula) VALUES(?, ?, ?, ?)";
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Integer generatedId = null;
		
		try {
			
			statement = connection.prepareStatement(SQL,statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idDepartamento);
			statement.setString(2, funcionario.getNome());
			statement.setDouble(3, funcionario.getSalario());
			statement.setString(4, funcionario.getMatricula());
			
			statement.executeUpdate();
			
			resultSet = statement.getGeneratedKeys();
			
			if(resultSet.next()) {
				generatedId = resultSet.getInt(1);
				funcionario.setId(generatedId);
			}
			
			JOptionPane.showMessageDialog(null, "Funcionário Cadastrado com Sucesso!");
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Funcionário! " + e);
			
		}finally {
			
			ConectionFactory.closeConnection(connection, statement,resultSet);
			
		}
		
		return generatedId;
	}
	
	public List<Funcionario> read(){
		
		String SQL = "SELECT * FROM funcionario";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		List<Funcionario> funcionarios = new ArrayList<>();
		
		try {
			
			statement = connection.prepareStatement(SQL);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Funcionario funcionario = new Funcionario();
				funcionario.setId(resultSet.getInt("id"));
				funcionario.setNome(resultSet.getString("nome"));
				funcionario.setSalario(resultSet.getDouble("salario"));
				funcionario.setMatricula(resultSet.getString("matricula"));
				
				funcionario.setTelefones(telefoneDao.read(funcionario.getId()));
				funcionario.setDependentes(dependenteDao.read(funcionario.getId()));
				funcionario.setEmails(emailDao.read(funcionario.getId()));
				
				funcionarios.add(funcionario);
			}
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Consultar  Funcionarios Cadastrados! " + e);
			
		}finally {
			
			ConectionFactory.closeConnection(connection, statement, resultSet);
			
		}
		
		return funcionarios;
	}
	
	public boolean update(Funcionario funcionario) {
		
		String SQL = "UPDATE funcionario SET nome = ?, salario = ?, matricula = ? WHERE id = ?";
		PreparedStatement statement = null;
		
		try {
			
			statement = connection.prepareStatement(SQL);
			statement.setString(1, funcionario.getNome());
			statement.setDouble(2, funcionario.getSalario());
			statement.setString(3, funcionario.getMatricula());
			statement.setLong(4, funcionario.getId());
			
			statement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Funcionario Atualizado com Sucesso!");
			return true;
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Atualizar Funcionario! " + e);
			return false;
			
		}finally {
			
			ConectionFactory.closeConnection(connection, statement);
		}
	}
	
	public boolean delete(Funcionario funcionario) {
		
		String SQL = "DELETE FROM funcionario WHERE id = ?";
		PreparedStatement statement = null;
		
		try {
			
			statement = connection.prepareStatement(SQL);
			statement.setLong(1, funcionario.getId());
			statement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Funcionario Deletado com Sucesso!");
			return true;
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Deletar Funcionario!" + e);
			return false;
			
		}finally {
			
			ConectionFactory.closeConnection(connection, statement);
			
		}
		
	}
}