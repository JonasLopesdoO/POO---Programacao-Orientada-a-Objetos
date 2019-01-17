package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import aluno.GContatos;
import aluno.base.Circulo;
import cliente.CirculoBase;

public class CircleTests {
	private static final String AMIGOS = "amigos";
	private static final String TRABALHO = "trabalho";
	private static final String FAMILIA = "familia";
	
	private GContatos gcont;
	private CirculoBase familia, trabalho, amigos;
	
	@Before
    public void setUp() {
        familia = new Circulo(FAMILIA, 3);
        trabalho = new Circulo(TRABALHO, 3);
        amigos = new Circulo(AMIGOS, 1);

        gcont = new GContatos();
    }
	
	@Test
	public void adicionarCirculos(){
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(FAMILIA, 3));
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(AMIGOS, 1));
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(TRABALHO, 3));
		
		assertEquals("Todos os 3 circulos devem ser adiconados", 3, gcont.getNumberOfCircles());
	}
	
	@Test
	public void adicionarCirculoLimiteInvalido(){
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(TRABALHO, 3));
		assertFalse("Circulo com limite menor ou igual a zero nao deve ser adicionado",
				gcont.createCircle(TRABALHO, -5));
		
		assertEquals("Apenas um ciruclo devia ter sido adicionado", 1, gcont.getNumberOfCircles());
	}
	
	@Test
	public void adicionarCirculoDuplicado(){
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(TRABALHO, 3));
		assertFalse("Circulo com id duplicado nao deve ser adicionado", gcont.createCircle(TRABALHO, 5));
		
		assertEquals("Apenas um ciruclo devia ter sido adicionado", 1, gcont.getNumberOfCircles());
	}
	
	@Test
	public void buscandoCirculoExistente(){
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(FAMILIA, 3));
		assertEquals("O circulo recuperado nao foi procurado", gcont.getCircle(FAMILIA), familia);
	}
	
	@Test
	public void buscandoCirculoInexistente(){
		assertEquals("Circulo nao existente", gcont.getCircle("inimigos"), null);
	}
	
	@Test
	public void recuperandoTodosOsCirculos(){
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(FAMILIA, 3));
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(AMIGOS, 1));
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(TRABALHO, 3));
		
		assertEquals("Lista de circulos incorreta", Arrays.asList(amigos, familia, trabalho),
				gcont.getAllCircles());
	}
	
	@Test
	public void removendoCirculoExistente(){
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(FAMILIA, 3));
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(AMIGOS, 1));
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(TRABALHO, 3));
		
		assertTrue("Circulo nao removido", gcont.removeCircle(AMIGOS));
		assertEquals("Quantidade de circulos errada", 2, gcont.getNumberOfCircles());
		assertEquals("Lista de circulos incorreta", 
				Arrays.asList(familia, trabalho), gcont.getAllCircles());
	}
	
	@Test
	public void removendoCirculoInexistente(){
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(FAMILIA, 3));
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(AMIGOS, 1));
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(TRABALHO, 3));
		
		assertFalse("Circulo nao existe, logo nao pode ser removido", gcont.removeCircle("inimigos"));
		assertEquals("Quantidade de circulos errada", 3, gcont.getNumberOfCircles());
		assertEquals("Lista de circulos incorreta", 
				Arrays.asList(amigos, familia, trabalho), gcont.getAllCircles());
	}
	
	@Test
	public void atualizandoCirculoExistente(){
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(FAMILIA, 3));
		assertTrue("O circulo deve ser atualizado", gcont.updateCircle(new Circulo(FAMILIA, 4)));
		assertEquals("O circulo nao foi atualizado corretamente", 
				gcont.getCircle(FAMILIA), new Circulo(FAMILIA, 4));
	}
	
	@Test
	public void atualizandoCirculoLimiteInvalido(){
		assertTrue("O circulo deve ser adicionado", gcont.createCircle(FAMILIA, 3));
		assertFalse("O circulo possui limite invalido", gcont.updateCircle(new Circulo(FAMILIA, 0)));
	}
	
	@Test
	public void atualizandoCirculoInexistente(){
		assertFalse("Circulo nao existente", gcont.updateCircle(new Circulo("inimigos", 4)));
		assertEquals("Circulo nao foi cadastrado", gcont.getCircle(FAMILIA), null);
	}
}
