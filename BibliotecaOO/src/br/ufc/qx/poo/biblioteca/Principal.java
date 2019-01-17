package br.ufc.qx.poo.biblioteca;

public class Principal {

	public static void main(String[] args) {
		Pessoa autor1 = new Pessoa("Paulo Coelho", "coelho@gmail.com", 'M');
		Pessoa autor2 = new Pessoa("Raul Seixas", "raulseixas@gmail.com", 'M');
		Pessoa autor3 = new Pessoa("Toquinho", "toquinho@gmail.com", 'M');
		
		Pessoa[] autores = new Pessoa[3];
		autores[0] = autor1;
		autores[1] = autor2;
		autores[2] = autor3;
		
		Livro livro1 = new Livro("A volta dos que nao foram", autores, 30);
		
		autores = new Pessoa[1];
		autores[0] = autor1;
		
		Livro livro2= new Livro("Diario de um mago", autores, 99);
		
		Biblioteca qx = new Biblioteca("BCQ - Biblioteca Campus Quixada");
		qx.adicionarLivro(livro1);
		qx.adicionarLivro(livro2);
		System.out.println(qx.getAcervo());
		
		Usuario usuario = new Aluno(45646,"Fulano de tal", "fulano@gmail.com", 'M');
		usuario.setSenha(123456);
		
		qx.alugarLivro(usuario, livro2, 45646, 1223);
		qx.alugarLivro(usuario, livro2, 45646, 123456);
		qx.alugarLivro(usuario, livro2, 45646, 123456);
		
		Usuario professor = new Professor(1111, "Cicrano", "xsasa@gmail.com", 'M');
		professor.setSenha(123456);
		
		qx.alugarLivro(professor, livro1, 1111, 123456);
		
		System.out.println(qx.getAcervo());
	}

}








