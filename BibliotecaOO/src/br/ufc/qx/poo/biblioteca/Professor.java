package br.ufc.qx.poo.biblioteca;

public class Professor extends Usuario{

	private int siape;
	
	public Professor(int siape, String nome, String email, char genero) {
		super(nome, email, genero);
		this.siape = siape;
	}
	
	@Override
	public int getCodigo() {
		return siape;
	}

}
