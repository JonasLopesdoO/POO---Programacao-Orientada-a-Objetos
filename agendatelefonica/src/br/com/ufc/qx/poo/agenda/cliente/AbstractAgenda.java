package br.com.ufc.qx.poo.agenda.cliente;

import java.util.List;

/**
 * @author Bruno Mateus(brunomateus@ufc.br) 
 *
 */
public abstract class AbstractAgenda {
	
	/**
	 * Inicia a agenda apagando qualquer contato existente.
	 * 
	 * @param maxContatos - define a capacidade maxima da agenda 
	 */
	public abstract void resetar(int maxContatos);
	
	/**
	 * Adiciona um contato na agenda.
	 * 
	 * Nao podem existir dois contatos com o mesmo telefone.
	 * O telefone deve ser composto apenas por numeros.
	 * O nome nao pode ser vazio.
	 * 
	 * Ex: 
	 * 	088999 - numero valido
	 * 	08-000 - numero invalido
	 * 
	 * Sugestao: Usar expressoes regulares(regex)
	 * 
	 * @param telefone - numero de telefone do contato a inserir
	 * @param nome - nome do contato a inserir
	 * @return true caso o contato seja inserido
	 */
	public abstract boolean adicionarContato(String telefone, String nome);
	
	/**
	 * Retorna o nome do contato que possui determinando telefone
	 * 
	 * 
	 * @param telefone - numero do contato desejado
	 * @return o contato que possui o numero ou null caso o contato 
	 * nao esteja na agenda
	 */
	public abstract AbstractContato getContatoByTel(String telefone);
		
	/**
	 * Atualiza o nome do contato.
	 * 
	 * Deve seguir as mesmas restricoes do metodo {@link #adicionarContato(String, String)}
	 * 
	 * @param telefone - numero do contato que sera atualizado
	 * @param nome - novo nome do contato
	 * @return true caso o contato seja atualizado
	 */
	public abstract boolean atualizarContato(String telefone, String nome);
	
	/**
	 * Remove contato da agenda.
	 * 
	 * @param telefone - numero do telefone do contato a ser removido
	 * @return true caso o contato seja removido
	 */
	public abstract boolean removerContato(String telefone);

	
	/**
	 * Retorna todos os contatos da agenda.
	 * 
	 * @return lista contendo todos os contatos da agenda
	 */
	public abstract List<AbstractContato> getContatos();
	
	/**
	 * Retorna todos os telefones cadastrados em nome de uma pessoa.
	 * 
	 * @param nome - nome da pessoa cujos telefones estao sendo procurados
	 * @return lista de todos os telefones, caso nenhum seja encontrado retorna null
	 */
	public abstract List<String> getTelefones(String nome);
	
	/**
	 * Retorna a quantidade de espaco disponivel na agenda
	 * 
	 * @return a capacidade de armazenamento restasta da agenda
	 */
	public abstract int getEspacoRestante();
}
