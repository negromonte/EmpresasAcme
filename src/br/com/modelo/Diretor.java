package br.com.modelo;

public class Diretor extends Funcionario {

	public  Diretor() {

	}

	public void calculaSalario() {
		super.salario += (super.salario * 2);

	}
}