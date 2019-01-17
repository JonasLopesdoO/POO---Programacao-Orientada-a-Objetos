package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import aluno.GContatos;
import aluno.base.Circulo;
import aluno.base.Contato;
import cliente.CirculoBase;
import cliente.CirculoNotFoundException;
import cliente.ContatoBase;
import cliente.ContatoNotFoundException;

public class ContactsCircleRelationsTest {

	private static final String AMIGOS = "amigos";
	private static final String TRABALHO = "trabalho";
	private static final String FAMILIA = "familia";
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
	private CirculoBase familia, trabalho, amigos;
	private ContatoBase james, jose, mario, ana, joaquim;

	@Before
	public void setUp() {
		familia = new Circulo(FAMILIA, 3);
		trabalho = new Circulo(TRABALHO, 3);
		amigos = new Circulo(AMIGOS, 2);

		james = new Contato(JAMES, JAMES_EMAIL);
		jose = new Contato(JOSE, JOSE_EMAIL);
		mario = new Contato(MARIO, MARIO_EMAIL);
		ana = new Contato(ANA, ANA_EMAIL);
		joaquim = new Contato(JOAQUIM, JOAQUIM_EMAIL);

		gcont = new GContatos();
	}

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void adicionarContatoCirculoExistente() throws CirculoNotFoundException, ContatoNotFoundException {
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(FAMILIA, 3));
		assertTrue("O contato deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));
		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JAMES, FAMILIA));
		assertEquals("Numero de contatos no circulo errado", 1, gcont.getCircle(FAMILIA).getNumberOfContacts());
		assertEquals("Lista de circulos do contato esta errada", Arrays.asList(familia), gcont.getCircles(JAMES));
		assertEquals("Lista de contatos de um circulo esta errada", Arrays.asList(james), gcont.getContacts("familia"));
	}

	@Test
	public void adicionarContatoInexistenteCirculoExistente()
			throws CirculoNotFoundException, ContatoNotFoundException {
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(FAMILIA, 3));

		exception.expect(ContatoNotFoundException.class);
		assertFalse(gcont.tie(JAMES, FAMILIA));
		assertEquals("Numero de contatos no circulo errado", 0, gcont.getCircle(FAMILIA).getNumberOfContacts());

	}

	@Test
	public void adicionarContatoCirculoInexistente() throws CirculoNotFoundException, Exception {
		assertTrue("O contato deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));

		exception.expect(CirculoNotFoundException.class);
		assertFalse(gcont.tie(JAMES, FAMILIA));
		assertEquals( "Circulo nao existente", gcont.getCircle(FAMILIA), null);
		assertEquals("Contato nao esta em nenhum circulo", Collections.EMPTY_LIST, gcont.getCircles(JAMES));
	}

	@Test
	public void adicionarContatoDuplicadoCirculoExistente() throws CirculoNotFoundException, ContatoNotFoundException {
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(FAMILIA, 3));
		assertTrue("O contato deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));
		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JAMES, FAMILIA));
		assertFalse("Contato ja esta no circulo", gcont.tie(JAMES, FAMILIA));
		assertEquals("Numero de contatos no circulo errado", 1, gcont.getCircle(FAMILIA).getNumberOfContacts());
		assertEquals("Lista de circulos do contato esta errada", Arrays.asList(familia), gcont.getCircles(JAMES));
		assertEquals("Lista de contatos de um circulo esta errada", Arrays.asList(james), gcont.getContacts("familia"));
	}

	@Test
	public void adicionarAlemDoLimite() throws CirculoNotFoundException, ContatoNotFoundException {
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(FAMILIA, 3));

		assertTrue("O contato deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));
		assertTrue("O contato deve ser adicionado", gcont.createContact(JOSE, JOSE_EMAIL));
		assertTrue("O contato deve ser adicionado", gcont.createContact(ANA, ANA_EMAIL));
		assertTrue("O contato deve ser adicionado", gcont.createContact(JOAQUIM, JOAQUIM_EMAIL));

		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JAMES, FAMILIA));
		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JOSE, FAMILIA));
		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(ANA, FAMILIA));
		assertFalse("Limite do circulo atingido", gcont.tie(JOAQUIM, FAMILIA));
	}

	@Test
	public void adicionarContatoVariosCirculos() throws CirculoNotFoundException, ContatoNotFoundException {
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(FAMILIA, 3));
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(AMIGOS, 2));

		assertTrue("O contato deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));
		assertTrue("O contato deve ser adicionado",  gcont.createContact(MARIO, MARIO_EMAIL));
		assertTrue("O contato deve ser adicionado", gcont.createContact(JOSE, JOSE_EMAIL));

		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JAMES, FAMILIA));
		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(MARIO, FAMILIA));
		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JOSE, FAMILIA));

		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JAMES, AMIGOS));

		assertEquals("Lista de circulos do contato esta errada",
				Arrays.asList(amigos, familia), gcont.getCircles(JAMES));

		assertEquals("Lista de contatos de um circulo esta errada", 
				Arrays.asList(james, jose, mario), gcont.getContacts(FAMILIA));
	}

	@Test
	public void removendoContatoDoCirculo() throws CirculoNotFoundException, ContatoNotFoundException {
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(FAMILIA, 3));
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(AMIGOS, 2));

		assertTrue("O contato deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));

		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JAMES, FAMILIA));
		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JAMES, AMIGOS));

		assertEquals("Lista de circulos do contato esta errada",
				Arrays.asList(amigos, familia), gcont.getCircles(JAMES));

		assertTrue("Contato deve ser removido ao circulo", gcont.untie(JAMES, AMIGOS));

		assertEquals("Remocao de contato errada",
				Arrays.asList(familia), gcont.getCircles(JAMES));
	}

	@Test
	public void removendoContatoInexistenteDoCirculo() throws CirculoNotFoundException, ContatoNotFoundException {
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(FAMILIA, 3));
		assertTrue("O contato deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));
		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JAMES, FAMILIA));

		exception.expect(ContatoNotFoundException.class);
		assertFalse(gcont.untie("margarida", FAMILIA));
		assertEquals("Numero de contatos no circulo errado", 1, gcont.getCircle(FAMILIA).getNumberOfContacts());
	}

	@Test
	public void removendoContatoDoCirculoInexistente() throws CirculoNotFoundException, ContatoNotFoundException {
		assertTrue("O contato deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));
		exception.expect(CirculoNotFoundException.class);
		assertFalse(gcont.untie(JAMES, FAMILIA));
	}

	@Test
	public void removendoCirculoQuePossuiContatos() throws CirculoNotFoundException, ContatoNotFoundException {

		assertTrue("O circulo deve ser adicionado", gcont.createCircle(FAMILIA, 3));
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(AMIGOS, 2));
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(TRABALHO, 3));

		assertTrue("O contato deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));
		assertTrue("O contato deve ser adicionado",  gcont.createContact(MARIO, MARIO_EMAIL));
		assertTrue("O contato deve ser adicionado", gcont.createContact(JOSE, JOSE_EMAIL));
		assertTrue("O contato deve ser adicionado", gcont.createContact(ANA, ANA_EMAIL));
		assertTrue("O contato deve ser adicionado", gcont.createContact(JOAQUIM, JOAQUIM_EMAIL));

		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JAMES, FAMILIA));
		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(MARIO, FAMILIA));
		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JOSE, FAMILIA));

		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JAMES, TRABALHO));
		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JOAQUIM, TRABALHO));
		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(ANA, TRABALHO));

		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JAMES, AMIGOS));

		assertTrue("O circulo deve ser removido", gcont.removeCircle(FAMILIA));

		assertEquals("Lista de circulos do contato esta errada",
				Arrays.asList(amigos, trabalho), gcont.getCircles(JAMES));
		
		assertEquals("Lista de circulos do contato esta errada",
				Collections.EMPTY_LIST, gcont.getCircles(JOSE));
		assertEquals("Circulo nao existe mais", null, gcont.getCircle("familia"));

	}
	
	@Test
	public void removendoContatosQueEstaEmCirculos() throws CirculoNotFoundException, ContatoNotFoundException {

		assertTrue("O circulo deve ser adicionado", gcont.createCircle(FAMILIA, 3));
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(AMIGOS, 2));
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(TRABALHO, 3));

		assertTrue("O contato deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));
		assertTrue("O contato deve ser adicionado", gcont.createContact(JOSE, JOSE_EMAIL));
		assertTrue("O contato deve ser adicionado", gcont.createContact(ANA, ANA_EMAIL));
		assertTrue("O contato deve ser adicionado",  gcont.createContact(MARIO, MARIO_EMAIL));
		assertTrue("O contato deve ser adicionado", gcont.createContact(JOAQUIM, JOAQUIM_EMAIL));

		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JAMES, FAMILIA));
		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(MARIO, FAMILIA));
		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JOSE, FAMILIA));

		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JAMES, TRABALHO));
		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JOAQUIM, TRABALHO));
		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(ANA, TRABALHO));

		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JAMES, AMIGOS));

		assertTrue("O contato deve ser removido", gcont.removeContact(JAMES));

		assertEquals("A lista de contatos do circulo esta errada", 
				Arrays.asList(jose, mario), gcont.getContacts("familia"));
		assertEquals("A lista de contatos do circulo esta errada",
				Arrays.asList(ana, joaquim), gcont.getContacts("trabalho"));
		assertEquals("A lista de contatos do circulo esta errada",
				Collections.EMPTY_LIST, gcont.getContacts("amigos"));
		
		exception.expect(ContatoNotFoundException.class);
		assertEquals(Collections.EMPTY_LIST, gcont.getCircles(JAMES));
		assertEquals(null, gcont.getContact(JAMES));

	}

	@Test
	public void circulosEmComum() throws CirculoNotFoundException, ContatoNotFoundException {
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(FAMILIA, 3));
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(AMIGOS, 2));
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(TRABALHO, 3));

		assertTrue("O contato deve ser adicionado", gcont.createContact(JAMES, JAMES_EMAIL));
		assertTrue("O contato deve ser adicionado",  gcont.createContact(MARIO, MARIO_EMAIL));
		assertTrue("O contato deve ser adicionado", gcont.createContact(JOSE, JOSE_EMAIL));
		assertTrue("O contato deve ser adicionado", gcont.createContact(ANA, ANA_EMAIL));
		assertTrue("O contato deve ser adicionado", gcont.createContact(JOAQUIM, JOAQUIM_EMAIL));

		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JAMES, FAMILIA));
		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(MARIO, FAMILIA));

		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JAMES, TRABALHO));
		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JOAQUIM, TRABALHO));
		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(ANA, TRABALHO));

		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(JAMES, AMIGOS));
		assertTrue("Contato deve ser adicionado ao circulo", gcont.tie(MARIO, AMIGOS));

		assertEquals(Arrays.asList(trabalho), gcont.getCommomCircle(JAMES, ANA));
		assertEquals(Collections.EMPTY_LIST, gcont.getCommomCircle(JAMES, JOSE));
		assertEquals(Arrays.asList(amigos, familia), gcont.getCommomCircle(JAMES, MARIO));

	}

}
