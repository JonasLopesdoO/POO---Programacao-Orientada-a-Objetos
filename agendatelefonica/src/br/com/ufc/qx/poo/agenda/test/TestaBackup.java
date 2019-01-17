package br.com.ufc.qx.poo.agenda.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.ufc.qx.poo.agenda.aluno.Agenda;
import br.com.ufc.qx.poo.agenda.aluno.Backup;
import br.com.ufc.qx.poo.agenda.cliente.AbstractAgenda;
import br.com.ufc.qx.poo.agenda.cliente.AbstractContato;

public class TestaBackup {
	
	private AbstractAgenda agenda;
	
	@Before
	public void preencher(){
		String[] nomes = {"Ana", "Breno", "Caio", "Daniel", "Erica", "Fabiola", "Gabi", "Helen", "Igor"};
		String[] telefones = {"2545451", "2545452", "2545453", "2545454", "2545455",
				"2545456", "2545457", "2545458", "2545459"};
		
		agenda = new Agenda();
		agenda.resetar(nomes.length);
		
		for(int i = 0; i < nomes.length; i++){
			agenda.adicionarContato(telefones[i], nomes[i]);
		}
	}
	
	@Test
	public void copiandoParaOutraAgenda(){
		AbstractAgenda novaAgenda = new Agenda();
		novaAgenda.resetar(14);
		novaAgenda.adicionarContato("9685", "Joazin");
		
		Backup.importar(agenda, novaAgenda);
		
		assertEquals("Quantidade incorreta de contatos", 4, novaAgenda.getEspacoRestante());
		
		boolean igual = true;
		List<AbstractContato> contatosCopiados = novaAgenda.getContatos();
		
		for (AbstractContato contato : agenda.getContatos()) {
			igual &= contatosCopiados.contains(contato);
		}
		
		assertTrue("Backup incorreto", igual);
	}
	
	@Test
	public void backupEmCSV(){
		agenda.removerContato("2545459");
		agenda.removerContato("2545458");
		agenda.removerContato("2545457");
		agenda.removerContato("2545456");
		agenda.removerContato("2545455");
		agenda.removerContato("2545454");
		String resultadoEsperado = "Ana,2545451\n" + "Breno,2545452\n" + "Caio,2545453\n";
		assertEquals("Contatos removidos erroneamente", 6, agenda.getEspacoRestante());
		assertEquals("Backup incorreto", resultadoEsperado, Backup.emCSV(agenda));
		
	}

}
