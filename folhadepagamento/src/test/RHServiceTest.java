package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import aluno.base.Professor;
import aluno.base.STA;
import aluno.base.Terceirizado;
import aluno.manager.RHService;
import cliente.IRHService;

public class RHServiceTest {
	Professor profDavid, profValdemir;
	STA staNatalia, staVenicio;
	Terceirizado tercBismark, tercGeovana;
	
	String cpfDavid = "16";
	String cpfValdemir = "15";
	String cpfNatalia = "23";
	String cpfVenicio = "43";
	String cpfBismark = "12";
	String cpfGeovana = "78";
	String cpfNulo = "99";
	
	private IRHService rh;
	
	@Before
	public void setUp(){
		rh = new RHService();	
		profDavid = new Professor(cpfDavid,  "David", 'C');//salario 7000
		profValdemir = new Professor(cpfValdemir, "Valdemir", 'B');//salario 5000
		
		staVenicio = new STA(cpfVenicio, "Venicio", 10);//salario 2000
		staNatalia = new STA(cpfNatalia, "Natalia", 5);//salario 1500
		
		tercBismark = new Terceirizado(cpfBismark, "Bismark", false);//salario 1000
		tercGeovana = new Terceirizado(cpfGeovana, "Geovana", true);//salario 1500
	}
	
	@Test
	public void cadastrarTerceirizado(){
		assertEquals("O RH deve iniciar vazio", 0, rh.getTotalFuncionarios());
		assertTrue("O terceirizado deveria ter sido adicionado", rh.cadastrar(tercGeovana));
		assertTrue("O terceirizado deveria ter sido adicionado", rh.cadastrar(tercBismark));
		assertEquals("O RH deveria ter dois funcionarios registrados", 2, rh.getTotalFuncionarios());
	}
	
	@Test
	public void cadastrarSTA(){
		assertEquals("O RH deve iniciar vazio", 0, rh.getTotalFuncionarios());
		assertTrue("O STA deveria ter sido adicionado", rh.cadastrar(staVenicio));
		assertEquals("O RH deveria ter um funcionario registrado", 1, rh.getTotalFuncionarios());
	}
	
	@Test
	public void cadastrarProfessor(){
		assertEquals("O RH deve iniciar vazio", 0, rh.getTotalFuncionarios());
		assertTrue("O Professor deveria ter sido adicionado", rh.cadastrar(profDavid));
		assertEquals("O RH deveria ter um funcionario registrado", 1, rh.getTotalFuncionarios());
	}
	
	@Test
	public void cadastrarFuncionarioDuplicado(){
		assertTrue("O terceirizado deveria ter sido adicionado", rh.cadastrar(tercBismark));
		assertFalse("Nao deve ser possivel adiciona o mesmo funcionario(cpf) duas vezes", rh.cadastrar(new Professor(cpfBismark, "claudio", 'C')));
		assertEquals("O RH deveria ter um funcionario registrado", 1, rh.getTotalFuncionarios());
	}
	
	@Test
	public void cadastrarProfessorComClasseInvalida(){
		assertFalse("Nao podemos cadastrar professor com classe invalida", rh.cadastrar(new Professor(cpfNulo, "claudio", 'X')));
		assertEquals("Funcionario cadastrado indevidamente", 0, rh.getTotalFuncionarios());
	}
	
	@Test
	public void cadastrarSTAComNivelInvalido(){
		assertFalse("Nao podemos cadastrar sta com nivel invalido", rh.cadastrar(new STA(cpfNulo, "claudio", 35)));
		assertEquals("Funcionario cadastrado indevidamente", 0, rh.getTotalFuncionarios());
	}
	
	private void inserirFuncionarios() {
		assertTrue("O terceirizado deveria ter sido adicionado", rh.cadastrar(profValdemir));
		assertTrue("O terceirizado deveria ter sido adicionado", rh.cadastrar(profDavid));
		
		assertTrue("O STA deveria ter sido adicionado", rh.cadastrar(staVenicio));
		assertTrue("O STA deveria ter sido adicionado", rh.cadastrar(staNatalia));

		assertTrue("O terceirizado deveria ter sido adicionado", rh.cadastrar(tercBismark));
		assertTrue("O terceirizado deveria ter sido adicionado", rh.cadastrar(tercGeovana));
	}
	
	@Test
	public void removerFuncionario(){
		inserirFuncionarios();
		
		assertTrue("Deve ser possivel remover funcionario cadastrado", rh.remover(cpfNatalia));
		assertTrue("Deve ser possivel remover funcionario cadastrado", rh.remover(cpfGeovana));
		assertTrue("Deve ser possivel remover funcionario cadastrado", rh.remover(cpfValdemir));
		assertFalse("Nao e possivel remover um usuario nao cadastrado", rh.remover(cpfNulo));
		assertEquals(3, rh.getTotalFuncionarios());
		
	}

	@Test
	public void removerFuncionarioDuasVezes(){
		inserirFuncionarios();
		
		assertTrue("Deve ser possivel remover funcionario cadastrado", rh.remover(cpfValdemir));
		assertFalse("Nao e possivel remover um usuario duas vezes", rh.remover(cpfValdemir));
		assertEquals(5, rh.getTotalFuncionarios());
	}
	
	@Test
	public void removerFuncionarioInexistente(){
		inserirFuncionarios();
		
		assertFalse("Nao e possivel remover um usuario nao cadastrado", rh.remover(cpfNulo));
		assertEquals(6, rh.getTotalFuncionarios());
	}
	
	@Test
	public void buscarProfessor(){
		inserirFuncionarios();
		assertEquals(rh.obterFuncionario(cpfDavid), profDavid);
		
	}
	
	@Test
	public void buscarSTA(){
		inserirFuncionarios();
		assertEquals(rh.obterFuncionario(cpfVenicio), staVenicio);
		
	}
	
	@Test
	public void buscarTerceirizado(){
		inserirFuncionarios();
		assertEquals(rh.obterFuncionario(cpfVenicio), staVenicio);
	}
	
	@Test
	public void buscarFuncionariosNaoExistente(){
		inserirFuncionarios();
		
		assertTrue(rh.remover(cpfNatalia));
		assertTrue(rh.remover(cpfValdemir));
		
		assertEquals(rh.obterFuncionario(cpfValdemir), null);
		assertEquals(rh.obterFuncionario(cpfNatalia), null);
	}
	
	@Test
	public void buscarTodosOsProfessores(){
		inserirFuncionarios();
		
		Professor profChico = new Professor("91", "Chico", 'E');
		Professor profX = new Professor("92", "Xarles", 'D');
		rh.cadastrar(profChico);
		rh.cadastrar(profX);
		
		assertEquals("A lista deve conter os mesmo funcionario e deve estar ordenada pelo nome funcionario",
				rh.getFuncionariosPorCategoria(IRHService.Tipo.PROF), 
				Arrays.asList(profChico, profDavid, profValdemir, profX));
	}
	
	@Test
	public void buscarTodosOsSTAs(){
		inserirFuncionarios();
		
		Professor profChico = new Professor("91", "Chico", 'E');
		Professor profX = new Professor("92", "Xarles", 'D');
		rh.cadastrar(profChico);
		rh.cadastrar(profX);
		
		assertEquals("A lista deve conter os mesmo funcionario e deve estar ordenada pelo nome funcionario", 
				rh.getFuncionariosPorCategoria(IRHService.Tipo.STA), 
				Arrays.asList(staNatalia, staVenicio));
	}
	
	@Test
	public void buscarTodosOsTerceirizados(){
		inserirFuncionarios();
		
		assertEquals("A lista deve conter os mesmo funcionario e deve estar ordenada pelo nome funcionario",
				rh.getFuncionariosPorCategoria(IRHService.Tipo.TERC), 
				Arrays.asList(tercBismark, tercGeovana));
	}
	
	@Test
	public void buscarTodosOsFuncionarios(){
		inserirFuncionarios();
		
		assertEquals("A lista deve conter os mesmo funcionario e deve estar ordenada pelo nome funcionario",
				rh.getFuncionarios(), 
				Arrays.asList(tercBismark, profDavid, tercGeovana,
						staNatalia, profValdemir, staVenicio));	
	}
	
	@Test
	public void calcularSalarioProfessor(){
		assertTrue(rh.cadastrar(profDavid));
		assertTrue(rh.cadastrar(profValdemir));
		
		assertEquals("Calculo incorreto", 7000.0, rh.calcularSalarioDoFuncionario(cpfDavid), 0.01);
		assertEquals("Calculo incorreto", 5000.0, rh.calcularSalarioDoFuncionario(cpfValdemir), 0.01);
	}
	
	@Test
	public void calcularSalarioSTA(){
		assertTrue(rh.cadastrar(staVenicio));
		assertEquals("Calculo incorreto", 2000.0, rh.calcularSalarioDoFuncionario(cpfVenicio), 0.01);
	}
	
	@Test
	public void calcularSalarioTerceirizados(){
		assertTrue(rh.cadastrar(tercBismark));
		assertEquals("Calculo incorreto", 1000.0, rh.calcularSalarioDoFuncionario(cpfBismark), 0.01);
	}
	
	@Test
	public void salarioProfessorComDiaria(){
		assertTrue(rh.cadastrar(profDavid));
		assertTrue("Um professor tem direito a tres diarias", rh.solicitarDiaria(cpfDavid));
		assertEquals("Calculo de diaria incorreto", 7100.0, rh.calcularSalarioDoFuncionario(cpfDavid), 0.01);
	}
	
	@Test
	public void salarioSTAComDiaria(){
		assertTrue(rh.cadastrar(staNatalia));
		assertTrue("Um sta tem direito a uma diaria", rh.solicitarDiaria(cpfNatalia));
		assertEquals("Calculo de diaria incorreto", 1600.0, rh.calcularSalarioDoFuncionario(cpfNatalia), 0.01);
	}
	
	@Test
	public void salarioTerceirizadoComDiaria(){
		assertTrue(rh.cadastrar(tercBismark));
		assertFalse("Um terceirizado nao tem direito a diaria", rh.solicitarDiaria(cpfBismark));
		assertEquals("Calculo de diarias incorreto", 1000.0, rh.calcularSalarioDoFuncionario(cpfBismark), 0.01);
	}
	
	@Test
	public void diariaAlemDoLimiteProfessor(){
		assertTrue(rh.cadastrar(profDavid));
		assertTrue("Um professor tem direito a tres diarias", rh.solicitarDiaria(cpfDavid));
		assertEquals(7100.0, rh.calcularSalarioDoFuncionario(cpfDavid), 0.01);
		assertTrue("Um professor tem direito a tres diarias", rh.solicitarDiaria(cpfDavid));
		assertTrue("Um professor tem direito a tres diarias", rh.solicitarDiaria(cpfDavid));
		assertFalse("Diarias alem do limite foram concedidas", rh.solicitarDiaria(cpfDavid));
		assertEquals(7300.0, rh.calcularSalarioDoFuncionario(cpfDavid), 0.01);
	}
	
	@Test
	public void diariaAlemDoLimiteSTA(){
		assertTrue(rh.cadastrar(staNatalia));
		assertTrue(rh.solicitarDiaria(cpfNatalia));
		assertEquals(1600.0, rh.calcularSalarioDoFuncionario(cpfNatalia), 0.01);
		assertFalse(rh.solicitarDiaria(cpfNatalia));
		assertEquals(1600.0, rh.calcularSalarioDoFuncionario(cpfNatalia), 0.01);
	}
	
	@Test
	public void calcularFolhaVazia(){
		assertEquals("Folha sem funcionarios", 0.0, rh.calcularFolhaDePagamento(), 0.01);		
	}
	
	@Test
	public void calcularFolha(){
		assertTrue(rh.cadastrar(profDavid));
		assertTrue(rh.cadastrar(staVenicio));
		assertTrue(rh.cadastrar(tercBismark));
		
		assertEquals("Soma de salarios incorreta", 10000.0, rh.calcularFolhaDePagamento(), 0.01);
		
		assertTrue(rh.cadastrar(staNatalia));
		assertTrue(rh.cadastrar(profValdemir));
		assertTrue(rh.cadastrar(tercGeovana));
		
		assertEquals("Soma de salarios incorreta", 18000.0, rh.calcularFolhaDePagamento(), 0.01);
	}
	
	@Test
	public void calcularFolhaComDiarias(){

		assertEquals(0.0, rh.calcularFolhaDePagamento(), 0.01);
		
		assertTrue(rh.cadastrar(profDavid));
		assertTrue(rh.cadastrar(staVenicio));
		assertTrue(rh.cadastrar(tercBismark));
		
		assertEquals("Soma de salarios incorreta", 10000.0, rh.calcularFolhaDePagamento(), 0.01);
		
		assertTrue(rh.cadastrar(staNatalia));
		assertTrue(rh.cadastrar(profValdemir));
		assertTrue(rh.cadastrar(tercGeovana));
		
		
		assertEquals("Soma de salarios incorreta", 18000.0, rh.calcularFolhaDePagamento(), 0.01);

		assertTrue(rh.solicitarDiaria(cpfDavid));
		assertEquals("Soma de salarios com diaria incorreta", 7100.0, rh.calcularSalarioDoFuncionario(cpfDavid), 0.01);
		assertTrue(rh.solicitarDiaria(cpfDavid));
		assertTrue(rh.solicitarDiaria(cpfDavid));
		assertFalse(rh.solicitarDiaria(cpfDavid));
		
		assertEquals("Soma de salarios com diaria incorreta", 7300.0, rh.calcularSalarioDoFuncionario(cpfDavid), 0.01);
		
		assertFalse(rh.solicitarDiaria(cpfBismark));
		
		assertTrue(rh.solicitarDiaria(cpfNatalia));
		assertFalse(rh.solicitarDiaria(cpfNatalia));
		
		assertEquals(18400.0, rh.calcularFolhaDePagamento(), 0.01);
		
	}
	
	@Test
	public void participacaoNosLucros(){
		assertEquals(0.0, rh.calcularFolhaDePagamento(), 0.01);
		
		assertTrue(rh.cadastrar(profDavid));
		assertTrue(rh.cadastrar(staVenicio));
		assertTrue(rh.cadastrar(tercBismark));
		
		assertEquals(10000.0, rh.calcularFolhaDePagamento(), 0.01);

		assertTrue(rh.partilharLucros(6.00));

		assertEquals("Salarios com participacao nos lucros incorreto",
				2002.0, rh.calcularSalarioDoFuncionario(cpfVenicio), 0.01);
	}
	
	@Test
	public void calcularFolhaComPL(){
		participacaoNosLucros();
		assertEquals("Soma de salarios com participacao nos lucros incorreta",
				10006.0, rh.calcularFolhaDePagamento(), 0.01);
	}
	
	@Test
	public void iniciandoNovoMes(){
		calcularFolhaComPL();
		rh.iniciarMes();
		assertEquals(10000.0, rh.calcularFolhaDePagamento(), 0.01);
		
	}
}
