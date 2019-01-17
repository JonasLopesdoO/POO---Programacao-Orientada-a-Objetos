package br.ufc.qx.poo.biblioteca;

public class Usuario extends Pessoa {
	
	private int senha;

	public Usuario(String nome, String email, char genero) {
		super(nome, email, genero);
	}

	public int getCodigo() {
		return -1;
	}

	public int getSenha() {
		return senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}
	
	

}
