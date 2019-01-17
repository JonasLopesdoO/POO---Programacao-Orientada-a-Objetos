package br.com.ufc.qx.poo.heranca;

public class Funcionario {

	private String nome;
	private String cpf;
	private double salario;
	
	public double getSalario() {
		return salario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setSalario(double salario) {
		if(salario > 0){
			this.salario = salario;
		}
	}
	
	public String getDetalhes(){
		return nome + "[" + cpf+ "] " + "salario = " + salario; 
	}
	
	
	
	
}
