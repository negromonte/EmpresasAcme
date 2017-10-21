package br.com.principal;

import java.sql.SQLException;
import java.util.List;

import br.com.modelo.Dependente;
import br.com.modelo.Email;
import br.com.modelo.Funcionario;
import br.com.modelo.Telefone;
import br.com.service.FuncionarioService;

public class Principal{


	public static void main(String args[]) throws SQLException {
			
		FuncionarioService service = new FuncionarioService();
		List<Funcionario> funcionarios = service.list();
		
		for(Funcionario funcionario : funcionarios) {
			
			System.out.println("ID: " + funcionario.getId());
			System.out.println("Nome: " + funcionario.getNome());
			System.out.println("Salário: " + funcionario.getSalario());
			System.out.println("Matricula: " + funcionario.getMatricula());
			
				for(Telefone telefone : funcionario.getTelefones()) {
					System.out.println("Telefones: " + telefone.getDdd()+" "+telefone.getNumero());
				}
				
				for(Dependente dependente : funcionario.getDependentes()) {
					System.out.println("Dependentes: " + dependente.getNome());
				}
				
				for(Email email : funcionario.getEmails()) {
					System.out.println("Emails : " + email.getDescricao());
				}
			System.out.println("----------------------------------------");
		}
		
	}
}