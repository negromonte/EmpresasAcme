package br.com.service;

import java.util.List;

import br.com.dao.DepartamentoDao;
import br.com.modelo.Departamento;

/**
 * @author Victor Negromonte
 *
 */
public class DepartamentoService {

	DepartamentoDao departamentoDao;
	
	public DepartamentoService() {
		
		this.departamentoDao = new DepartamentoDao();
		
	}
	
	public void save(Departamento departamento) {
		departamentoDao.create(departamento);
		
	}
	
	public List<Departamento> list(){
		return this.departamentoDao.read();
		
	}
	
	public void edit(Departamento departamento) {
		departamentoDao.update(departamento);
		
	}
	
	public void delete(Departamento departamento) {
		departamentoDao.delete(departamento);
		
	}
	
}