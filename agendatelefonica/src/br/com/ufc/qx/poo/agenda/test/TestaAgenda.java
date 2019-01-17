package br.com.ufc.qx.poo.agenda.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.ufc.qx.poo.agenda.aluno.Agenda;
import br.com.ufc.qx.poo.agenda.cliente.AbstractAgenda;
import br.com.ufc.qx.poo.agenda.cliente.AbstractContato;

public class TestaAgenda {

	private static final String NOME_VAZIO = "";
	private AbstractAgenda agenda;

	@Before
	public void iniciaAgenda(){
		agenda = new Agenda();
		agenda.resetar(4);
	}

	@Test
	public void resetando(){
		AbstractAgenda agenda = new Agenda();
		assertTrue("Uma agenda deve ser criada vazia", agenda.getContatos().size() == 0);
		assertFalse("Nao e possivel adicionar um contato antes de resetar a agenda", agenda.adicionarContato("1111", "ze"));
		agenda.resetar(10);
		assertEquals("Todos os espacos devem estar disponiveis", 10, agenda.getEspacoRestante());
	}

	@Test
	public void adicionandoComNomeInvalido(){
		assertFalse("Nome do contato invalido", agenda.adicionarContato("1111", NOME_VAZIO));
		assertFalse("Contato sem nome", agenda.adicionarContato("1111", null));
	}

	@Test
	public void adicionandoComTelefoneInvalido(){
		assertFalse("Telefone so deve conter numero", agenda.adicionarContato("a0", "Zezin"));
		assertFalse("Telefone so deve conter numero", agenda.adicionarContato("88-0000", "Joao"));
		assertFalse("Telefone nao informado", agenda.adicionarContato("", "Joao"));
		assertFalse("Telefone nao informado", agenda.adicionarContato(null, "Joao"));
	}

	@Test
	public void adicionandoComNomeETelefoneValidos(){
		assertTrue("Contato com telefone e nome validos", agenda.adicionarContato("11110000", "Pedro Álvares"));
		assertTrue("Contato com telefone e nome validos", agenda.adicionarContato("11110010", "Dom Pedro"));
		assertEquals("Contatos nao foram adicionados" , 2, agenda.getContatos().size());
	}

	@Test
	public void adicionandoTelefonesDuplicados(){
		assertTrue("Contato com telefone e nome validos", agenda.adicionarContato("2345678", "Gabriel Pensador"));
		assertFalse("Nao deve ser possivel adicionar telefones duplicados", agenda.adicionarContato("2345678", "Mark"));
		assertEquals("Contatos nao foram adicionados corretamente" , 1, agenda.getContatos().size());
	}

	@Test
	public void adicionandoTelefonesDiferentesUnicaPessoa(){
		assertTrue("Contato com telefone e nome validos", agenda.adicionarContato("123456", "Tiziu"));
		assertTrue("Contato com telefone e nome validos", agenda.adicionarContato("654321", "Tiziu"));
		assertEquals("Contatos nao foram adicionados" , 2, agenda.getContatos().size());
	}

	@Test
	public void adicionandoAlemDaCapacidade(){
		agenda.resetar(1);
		assertTrue("Contato com telefone e nome validos", agenda.adicionarContato("123456", "Tiziu"));
		assertFalse("Capacidade maxima atingida", agenda.adicionarContato("654321", "Tiziu"));
		assertEquals("Agenda esta lotada", 0, agenda.getEspacoRestante());
	}

	@Test
	public void buscandoTelefoneNaoExistente(){
		assertNull("Ao buscar telefone nao existente deve resultar em null", agenda.getContatoByTel("0000"));
	}

	@Test
	public void buscandoTelefoneExistente(){
		agenda.adicionarContato("1111", "marcos");
		assertEquals("Contato existente nao encontrado", agenda.getContatoByTel("1111").getNome(), "marcos");
		agenda.adicionarContato("2222", "maria");
		agenda.adicionarContato("3333", "maria");
		assertEquals("Contanto existente nao encontrado", agenda.getContatoByTel("2222").getNome(), "maria");
		assertEquals("Contanto existente nao encontrado", agenda.getContatoByTel("3333").getNome(), "maria");
	}

	@Test
	public void buscandoContatoNaoExistente(){
		assertNull("Usuario sem telefone cadastrado", agenda.getTelefones("tiziu"));
	}

	@Test
	public void buscandoContatosExistentes(){
		agenda.adicionarContato("1111", "marcos");
		assertEquals("Contato existente nao encontrado", 1, agenda.getTelefones("marcos").size());
		assertEquals("Contato existente nao encontrado", "1111", agenda.getTelefones("marcos").get(0));
		agenda.adicionarContato("2222", "maria");
		agenda.adicionarContato("3333", "maria");
		List<String> mariaFones = agenda.getTelefones("maria");
		assertEquals("Contanto existente nao encontrado", 2, mariaFones.size());
		assertEquals("Contanto existente nao encontrado", "2222", mariaFones.get(0));
		assertEquals("Contanto existente nao encontrado", "3333", mariaFones.get(1));
	}

	@Test
	public void listarTodosOsContatos(){
		ArrayList<String> nomes = new ArrayList<>();
		nomes.add("Joao");
		nomes.add("Zezao");
		nomes.add("Maria");
		ArrayList<String> telefones = new ArrayList<>();
		telefones.add("111");
		telefones.add("222");
		telefones.add("333");
		for(int i = 0; i < nomes.size(); i++){
			assertTrue("Contato com telefone e nome validos", agenda.adicionarContato(telefones.get(i), nomes.get(i)));
		}
		boolean todos = true;
		for (AbstractContato contato : agenda.getContatos()) {
			todos &= nomes.contains(contato.getNome());
			todos &= telefones.contains(contato.getTelefone());
		}
		assertTrue("Nem todos os contatos foram encontrados", todos);

	}

	@Test
	public void atualizarContatoNaoExistente(){
		assertFalse("Contato nao existe, logo nao deve ser atualizado", agenda.atualizarContato("1111", "Ze"));
	}


	@Test
	public void atualizarContatoExistenteNomeInvalido(){
		agenda.adicionarContato("1111", "Joao");
		assertFalse("Contato nao encontrado ou nao atualizado", agenda.atualizarContato("1111", ""));
		assertEquals("Contato nao atualizado", "Joao", agenda.getContatoByTel("1111").getNome());
		assertFalse("Contato nao encontrado ou nao atualizado", agenda.atualizarContato("1111", null));
		assertEquals("Contato nao atualizado", "Joao", agenda.getContatoByTel("1111").getNome());
	}

	@Test
	public void atualizarContatoExistenteNomeValido(){
		agenda.adicionarContato("1111", "Joao");
		assertTrue("Contato nao encontrado ou nao atualizado", agenda.atualizarContato("1111", "Ze"));
		assertEquals("Contato nao atualizado", "Ze", agenda.getContatoByTel("1111").getNome());
	}

	@Test
	public void removerContatoInexistente(){
		agenda.adicionarContato("0000", "ze");
		assertFalse("Contato nao existe, logo nao deve ser removido", agenda.removerContato("1111"));
		assertFalse("Contato nao existe, logo nao deve ser removido", agenda.removerContato(null));
		assertFalse("Contato nao existe, logo nao deve ser removido", agenda.removerContato(""));
		assertEquals("Contato removido erroneamente", 3, agenda.getEspacoRestante());
	}


	@Test
	public void removerContatoExistente(){
		agenda.adicionarContato("0000", "ze");
		assertTrue("Contato nao existe, logo nao deve ser removido", agenda.removerContato("0000"));
		assertEquals("Contato nao removido", 4, agenda.getEspacoRestante());
		assertFalse("Contato nao existe, logo nao deve ser removido", agenda.removerContato("0000"));
	}

}
