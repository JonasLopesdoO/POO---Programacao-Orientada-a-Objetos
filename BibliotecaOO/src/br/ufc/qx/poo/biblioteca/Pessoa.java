package br.ufc.qx.poo.biblioteca;

public class Pessoa {

	private String nome;
	private String email;
	private char genero;
	
	public Pessoa(String nome, String email, char genero) {
		this.nome = nome;
		this.email = email;
		this.genero = genero;
	}
	
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public char getGenero() {
		return genero;
	}
	
	@Override
	public String toString() {
		return nome + " - " + email;
	}
}














