package br.com.ufc.qx.poo.gestao;

public class Funcionario {

	private String nome;
	private String cpf;
	private double salario;
	
	public Funcionario(String nome, String cpf, double salario) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public double getSalario() {
		return salario;
	}
	
	@Override
	public String toString() {
		return getNome() + "[cpf="+ getCpf()+", "+ " salario = " + getSalario() + "]";
	}
}
