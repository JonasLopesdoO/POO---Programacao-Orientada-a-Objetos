package cliente;

import java.util.List;


public interface IGContatos{
	
    /**
     * Adiciona um contato no repositorio de contatos
     * @param id O nome do contato. Deve ser unico
     * @param email O email do contato
     * @return true caso o contato seja adicionado,
     * false se um contato com o mesmo id ja existir
     */
    boolean createContact(String id, String email);

    /**
     * Retora a lista com todos os contatos ORDENADOS POR NOME
     * @return a lista de contatos
     */
    List<ContatoBase> getAllContacts();
    
    /**
     * Atualiza o email do contato idenficado pelo id
     * @param contato com o id e novo email
     * @return true caso o contato seja atualizado,
     * false se o contato com nao existir
     */
    boolean updateContact(ContatoBase contato);

    
    /**
     * Remove um contato
     * @param id identificador do contato a ser removido
     * @return true caso o contato seja removido,
     * false se o contato nao existir
     */
    boolean removeContact(String id);

    /**
     * Retorna um contato
     * @param id do contato a ser recuperado
     * @return contato caso ele exista,
     * null se nenhum contato com o id informado for encontrado
     */
    ContatoBase getContact(String id);
    
    /**
     * Retorna o numero de contatos cadastrados
     * @return o numero de contatos
     */
    int getNumberOfContacts();

    /**
     * Adiciona um circulo
     * @param id do circulo. Deve ser unico
     * @param limite define o maximo de contatos que esse circulo pode conter
     * @return true caso o contato seja adicionado,
     * false se ja existir um circulo com o mesmo id
     */
    boolean createCircle(String id, int limite);
    
    /**
     * Atualiza o limite do circulo
     * @param circulo com o mesmo identifador e novo limite
	 * @return true caso o circulo seja atualizado,
     * false se o circulo com nao existir
     */
    boolean updateCircle(CirculoBase circulo);

    /**
     * Retorna um circulo
     * @param id do circulo a ser recuperado
     * @return circulo caso ele exista,
     * null se nenhum circulo com o id informado for encontrado
     */
    CirculoBase getCircle(String idCirculo);
    
    /**
     * @return a lista dos circulos ordenados pelo nome
     */
    List<CirculoBase> getAllCircles();
    
    /**
     * Remove um circulo
     * @param id identificador do circulo a ser removido
     * @return true caso o circulo seja removido,
     * false se o circulo nao existir
     */
    boolean removeCircle(String idCirculo);
    
    /**
     * @return o numero de circulos cadastrados
     */
    int getNumberOfCircles();

    
    /**
     * Adiciona um contato a um circulo
     * @param idContato identificao do contato 
     * @param idCirculo identificao do do circulo
     * @return true se o contato for adicionado ao circulo,
     * false, se o circulo ja estiver cheio
     * @throws CirculoNotFoundException caso o circulo informado nao exista
     * @throws ContatoNotFoundException caso o contato informado nao exista
     */
    boolean tie(String idContato, String idCirculo) 
    		throws CirculoNotFoundException, ContatoNotFoundException;

    /**
     * Remove um contato de um circulo
     * @param idContato identificao do contato
     * @param idCirculo identificao do circulo
     * @return true caso o contato seja removido,
     * false se o contato nao estiver contido no circulo
     * @throws CirculoNotFoundException caso o circulo informado nao exista
     * @throws ContatoNotFoundException caso o contato informado nao exista
     */
    boolean untie(String idContato, String idCirculo) 
    		throws CirculoNotFoundException, ContatoNotFoundException;
    
    /**
     * Retorna a lista de contatos ordenas por nome contido em um circulo
     * @param id do circulo 
     * @return a lista de contato contido no circulo ordenado pelo nome
     * @throws CirculoNotFoundException caso o circulo informado nao exista
     */
    List<ContatoBase> getContacts(String id) throws CirculoNotFoundException;
    
    /**
     * Retorna a lista de circulos cujo o contato pertence
     * @param id do contato
     * @return a lista de circulo que contem o contato ordenado pelo nome
     * @throws ContatoNotFoundException caso o contato nao exista
     */
    List<CirculoBase> getCircles(String id) throws ContatoNotFoundException;

    /**
     * Retorna a lista de circulo ordenados pelo nome que os
     * dois contatos possuem em comum
     * @param idContato1 identificador de um contato
     * @param idContato2 identificador do outro contato
     * @return a lista de circulos em comum ordenados pelo nome
     * @throws ContatoNotFoundException caso algum dos contatos nao existam
     */
    List<CirculoBase> getCommomCircle(String idContato1, String idContato2) throws ContatoNotFoundException;
    
    
    /**
     * Marca um contato como favorito
     * @param id identificador do contato
     * @return true caso o contato seja marcado,
     * false se o contato nao existir
     */
    boolean favoriteContact(String idContato);
    
    
    /**
     * Faz um contato deixar de ser favorito
     * @param id identificador do contato
     * @return true caso contato deixar de ser favorito,
     * false se o contato nao existir
     */
    boolean unfavoriteContact(String idContato);
    
    
    /**
     * Verifica se um contato e favorito
     * @param id identificador do contato
     * @return true se o contato for favorito,
     * false caso contrario
     */
    boolean isFavorited(String id);
    
    /**
     * Pega a lista de todos os favoritos
     * @return a lista de favoritos
     */
    List<ContatoBase> getFavorited();
}
