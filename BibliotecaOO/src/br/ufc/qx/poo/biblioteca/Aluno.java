package br.ufc.qx.poo.biblioteca;

public class Aluno extends Usuario{
	
	private int matricula;
	private int ira;
	private int semestreAtual;

	public Aluno(int matricula, String nome, String email, char genero) {
		super(nome, email, genero);
		this.matricula = matricula;
	}
	
	
	@Override
	public int getCodigo() {
		return matricula;
	}

}
