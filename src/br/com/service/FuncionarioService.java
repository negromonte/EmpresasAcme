package br.com.service;

import java.util.List;

import br.com.dao.DependenteDao;
import br.com.dao.EmailDao;
import br.com.dao.FuncionarioDao;
import br.com.dao.TelefoneDao;
import br.com.modelo.Dependente;
import br.com.modelo.Email;
import br.com.modelo.Funcionario;
import br.com.modelo.Telefone;

/**
 * @author Victor Negromonte
 *
 */
public class FuncionarioService {

	private FuncionarioDao funcionarioDao;
	private DependenteDao dependenteDao;
	private EmailDao emailDao;
	private TelefoneDao telefoneDao;
	
	public FuncionarioService() {
		
		this.funcionarioDao = new FuncionarioDao();
		this.dependenteDao = new DependenteDao();
		this.emailDao = new EmailDao();
		this.telefoneDao = new TelefoneDao();

	}
	
	public void save(Integer dpt, Funcionario funcionario) {
	
		Integer departamento = dpt;
		
	//INSERT de Funcionário
		Integer key = funcionarioDao.create(departamento, funcionario);
	
	//INSERT de E-mail do Funcionário
		for(Email email : funcionario.getEmails()) {
			emailDao.create(key, email);
		}
		
	//INSERT da Lista de Telefones do Funcionário	
		for(Telefone telefone : funcionario.getTelefones()) {
			telefoneDao.create(key, telefone);
		}
		
	//INSERT da Lista de Dependentes do Funcionário	
		for(Dependente dependente : funcionario.getDependentes()) {
			dependenteDao.create(key, dependente);
		}
				
	}
	
	public List<Funcionario> list() {
		
		return this.funcionarioDao.read();
	}
	
	public void edit(Funcionario funcionario) {
		
		funcionarioDao.update(funcionario);
	
		for(Email email : funcionario.getEmails()) {
			emailDao.update(funcionario.getId(), email);
		}
		
		for(Telefone telefone : funcionario.getTelefones()) {
			telefoneDao.update(funcionario.getId(), telefone);
		}
		
		for(Dependente dependente : funcionario.getDependentes()) {
			dependenteDao.update(funcionario.getId(), dependente);
		}
				
	}
	
	public void delete(Funcionario funcionario) {
		
		funcionarioDao.delete(funcionario);
		
		for(Dependente dependente : funcionario.getDependentes()) {
			dependenteDao.delete(funcionario.getId(), dependente);
		}
		
		for(Telefone telefone : funcionario.getTelefones()) {
			telefoneDao.delete(funcionario.getId(), telefone);
		}
		
		for(Email email : funcionario.getEmails()) {
			emailDao.delete(funcionario.getId(), email);
		}
	}
}