package br.com.ufc.qx.poo.heranca;

public class Gerente extends Funcionario {
	
	private String senha;

	public Gerente(String senha) {
		super();
		this.senha = senha;
	}
	
	public String getDetalheGerente(){
		return this.getDetalhes() + " senha " + senha;
	}
	
	public void aumento(int n){
		this.setSalario(n * getSalario());
	}

}
