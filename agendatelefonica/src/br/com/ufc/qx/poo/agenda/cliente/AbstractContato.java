package br.com.ufc.qx.poo.agenda.cliente;


/**
 * @author Bruno Mateus(brunomateus@ufc.br)
 *
 */
public abstract class AbstractContato {
	
	public abstract String getNome();
	public abstract void setNome(String nome);
	public abstract String getTelefone();
	public abstract void setTelefone(String telefone);
	
	
	/** 
	 * Compara um contato com o outro
	 * 
	 * Dois contatos sao iguais se nome e telefone forem iguais
	 */
	public abstract boolean equals(Object contato);
}