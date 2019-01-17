package br.com.ufc.qx.poo.gestao;

public class Professor extends Funcionario{
	
	private int horas;

	public Professor(String nome, String cpf, double salario, int horas) {
		super(nome, cpf, salario);
		this.horas = horas;
	}
	
	
	public int getHoras() {
		return horas;
	}


	public void setHoras(int horas) {
		this.horas = horas;
	}


	@Override
	public double getSalario() {
		return super.getSalario() + horas*30;
	}
	
	@Override
	public String toString() {		
		return "Professor " + super.toString() + " horas = "+ horas;
	}

}













