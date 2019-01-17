package br.ufc.qx.poo.biblioteca;

public class Livro {

	private String nome;
	private double preco;
	private Pessoa[] autores;
	
	public Livro(String nome, Pessoa[] autores, int preco){
		this.nome = nome;
		this.autores = autores;
		this.preco = preco;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public Pessoa[] getAutores() {
		return autores;
	}
	
	@Override
	public String toString() {
		String detalhes =  this.nome + "\n" ;
		for (int i = 0; i < autores.length; i++) {
			detalhes += " | " + autores[i].toString() + "\n";
			
		} 
		detalhes +=	" preco " + preco;
		return detalhes;
	}
	
	
}















