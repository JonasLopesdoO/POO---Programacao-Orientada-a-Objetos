package br.ufc.qx.poo.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
	
	private List<Livro> livrosDisponiveis;
	private List<Livro> livrosAlugados;
	private String nome;
	
	
	public Biblioteca(String nome) {
		this.nome = nome;
		livrosDisponiveis = new ArrayList<Livro>();
		livrosAlugados = new ArrayList<Livro>();
	}
	
	public void adicionarLivro(Livro novoLivro){
		livrosDisponiveis.add(novoLivro);
	}
	
	public boolean alugarLivro(Usuario usuario, Livro livro, int codigo, int senha){
		if(usuario.getSenha() != senha || usuario.getCodigo() != codigo){
			System.out.println("Senha incorreta ou codigo incorreto");
			return false;
		}
		if(!livrosDisponiveis.contains(livro)){
			System.out.println("Livro nao disponivel");
			return false;
		}
		
		livrosDisponiveis.remove(livro);
		livrosAlugados.add(livro);
		return true;
	}
	
	public String getAcervo(){
		StringBuffer livros = new StringBuffer();
		livros.append("----- Acervo - ").append(this.nome).append("------\n");
		livros.append("----- Alugados -\n ");
		for (Livro livro : livrosAlugados) {
			livros.append(livro).append("\n");
		}
		livros.append("----- Disponiveis -\n ");
		for (Livro livro : livrosDisponiveis) {
			livros.append(livro).append("\n");
		}
		return livros.toString();
	}

}
