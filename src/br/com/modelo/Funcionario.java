package br.com.modelo;

import java.util.ArrayList;
import java.util.List;

public class Funcionario {

	private int id;
	private String nome;
	protected double salario = 0;
	private String matricula;
	private List<Email> emails;
	private Departamento departamento;
	private List<Telefone> telefones;
	private List<Dependente> dependentes;

	public Funcionario() {

		this.telefones = new ArrayList<Telefone>();
		this.dependentes = new ArrayList<Dependente>();
	}

	public void addTelefone(Telefone telefone) {
		telefones.add(telefone);

	}
	
	public void deleteTelefone(Telefone telefone) {
		telefones.remove(telefone);
	}

	public void addDependente(Dependente dependente) {
		dependentes.add(dependente);

	}
	
	public void deleteDependente(Dependente dependente) {
		dependentes.remove(dependente);
	}

	public void calculaSalario() {
		this.salario += (this.salario / 2);

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}
	
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<Dependente> dependentes) {
		this.dependentes = dependentes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}