package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import aluno.GContatos;
import aluno.base.Contato;
import cliente.ContatoBase;

public class ContactTests {

	private static final String JOAQUIM_EMAIL = "joaquim@ufc.br";
	private static final String JOAQUIM = "joaquim";
	private static final String ANA_EMAIL = "ana@ufc.br";
	private static final String ANA = "ana";
	private static final String MARIO_EMAIL = "mario@ufc.br";
	private static final String MARIO = "mario";
	private static final String JOSE_EMAIL = "jose@ufc.br";
	private static final String JOSE = "jose";
	private static final String JAMES_EMAIL = "james@ufc.com";
	private static final String JAMES = "james";
	private GContatos gcont;
	private ContatoBase james, jose, mario, ana, joaquim;

	@Before
	public void setUp() {
		james = new Contato(JAMES, JAMES_EMAIL);
		jose = new Contato(JOSE, JOSE_EMAIL);
		mario = new Contato(MARIO, MARIO_EMAIL);
		ana = new Contato(ANA, ANA_EMAIL);
		joaquim = new Contato(JOAQUIM, JOAQUIM_EMAIL);

		gcont = new GContatos();
	}

	@Test
	public void adicionarContato() {
		assertTrue("Contato valido, deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));
		assertEquals("Quantidade de contatos errada", 1, gcont.getNumberOfContacts());
	}

	@Test
	public void adicionarContatoDuplicado() {
		assertTrue("Contato valido, deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));
		assertFalse("Contato com id duplicado", gcont.createContact(JAMES, "jesus2@ufc.com"));
		assertEquals("Quantidade de contatos errada", 1, gcont.getNumberOfContacts());
	}

	@Test
	public void removendoContato() {
		assertTrue("Contato valido, deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));
		assertEquals("Quantidade de contatos errada", 1, gcont.getNumberOfContacts());
		assertTrue("Contato deve ser removido", gcont.removeContact(JAMES));
		assertEquals("Quantidade de contatos errada", 0, gcont.getNumberOfContacts());
	}

	@Test
	public void removendoContatoInexistente() {
		assertTrue("Contato valido, deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));
		assertEquals("Quantidade de contatos errada", 1, gcont.getNumberOfContacts());
		assertFalse("Contato nao cadastrado nao pode ser removido", gcont.removeContact("ramiro"));
		assertEquals("Quantidade de contatos errada", 1, gcont.getNumberOfContacts());
	}

	@Test
	public void recuperandoContato() {
		assertTrue("Contato valido, deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));
		assertEquals("Quantidade de contatos errada", 1, gcont.getNumberOfContacts());
		ContatoBase james = gcont.getContact(JAMES);
		assertEquals("Contato recuperado diferente do buscado", james, this.james);
	}

	@Test
	public void recuperandoContatoInexistene() {
		assertTrue("Contato valido, deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));
		assertEquals("Quantidade de contatos errada", 1, gcont.getNumberOfContacts());
		assertEquals("Contato nao existente", null, gcont.getContact("ramiro"));
	}

	@Test
	public void recuperandoTodosOsContatos() {
		assertTrue("Contato valido, deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));
		assertTrue("Contato valido, deve ser adicionado", gcont.createContact(ANA, ANA_EMAIL));
		assertTrue("Contato valido, deve ser adicionado", gcont.createContact(JOSE, JOSE_EMAIL));

		assertEquals("Lista de contatos errada", Arrays.asList(ana, james, jose), gcont.getAllContacts());
	}

	@Test
	public void atualizandoContato() {
		assertTrue("Contato valido, deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));
		assertEquals("Quantidade de contatos errada", 1, gcont.getNumberOfContacts());

		james.email = "novo@ufc.br";

		assertTrue("Contato valido, deve ser atualizado", gcont.updateContact(james));
		ContatoBase james = gcont.getContact(JAMES);
		assertEquals("Contato nao foi atualizado corretamente", this.james, james);
	}

	@Test
	public void atualizandoInexistente() {
		assertFalse("Contato nao existente, logo nao pode ser atualizado", gcont.updateContact(james));
	}

	@Test
	public void favoritandoUmContato() {
		assertTrue("Contato valido, deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));
		assertTrue("Contato deve ser marcado como favorito", gcont.favoriteContact(JAMES));
		assertTrue("Contato esta na lista de favoritos", gcont.isFavorited(JAMES));
		assertFalse("Contato nao esta na lista de favoritos", gcont.isFavorited(ANA));

	}

	@Test
	public void favoritandoUmContatoInexistente() {
		assertFalse("Contato nao existe", gcont.favoriteContact(JAMES));
		assertFalse("Contato nao esta na lista de favoritos",gcont.isFavorited(JAMES));
	}

	@Test
	public void desfavoritandoUmContato() {
		assertTrue("Contato valido, deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));
		assertTrue("Contato deve ser marcado como favorito", gcont.favoriteContact(JAMES));
		assertTrue("Contato esta na lista de favoritos", gcont.isFavorited(JAMES));

		assertTrue("Contato nao removido dos favoritos", gcont.unfavoriteContact(JAMES));
		assertFalse("Contato nao esta na lista de favoritos",gcont.isFavorited(JAMES));
	}

	@Test
	public void desfavoritandoUmContatoInexistente() {
		assertFalse("Contato nao existe", gcont.unfavoriteContact(JAMES));
		assertFalse("Contato nao esta na lista de favoritos", gcont.isFavorited(JAMES));
	}

	@Test
	public void recuperandoTodosOsFavoritos() {
		assertTrue("Contato valido, deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));
		assertTrue("Contato valido, deve ser adicionado", gcont.createContact(MARIO, MARIO_EMAIL));
		assertTrue("Contato valido, deve ser adicionado", gcont.createContact(ANA, ANA_EMAIL));

		assertTrue("Contato deve ser marcado como favorito", gcont.favoriteContact(JAMES));
		assertTrue("Contato deve ser marcado como favorito", gcont.favoriteContact(ANA));
		assertTrue("Contato deve ser marcado como favorito", gcont.favoriteContact(MARIO));

		assertTrue("O contato esta na lista de favoritos", gcont.isFavorited(ANA));
		assertFalse("O contato nao esta na lista de favoritos", gcont.isFavorited(JOSE));

		assertEquals("Lista de favoritos errada", 
				Arrays.asList(ana, james, mario), gcont.getFavorited());

		assertTrue("Contato deve ser removido dos favoritos", gcont.unfavoriteContact(ANA));

		assertEquals("Remocao de favoritos errada", Arrays.asList(james, mario), gcont.getFavorited());

		assertTrue("Contato deve ser removido dos favoritos", gcont.removeContact(MARIO));

		assertEquals("Remocao de favoritos errada", Arrays.asList(james), gcont.getFavorited());

	}
}
