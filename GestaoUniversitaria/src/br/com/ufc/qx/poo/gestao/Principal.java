package br.com.ufc.qx.poo.gestao;

public class Principal {

	public static void main(String[] args) {
		Funcionario f1, f2, f3;
		Professor p1, p2, p3;
		
		f1 = new Funcionario("Fulano", "000", 1000);
		f2 = new Funcionario("Fulano 2", "111", 2000);
		f3 = new Funcionario("Fulano 3", "222", 3000);
		
		p1 = new Professor("Cicrano", "000", 1000, 5);
		p2 = new Professor("Cicrano 2", "000", 2000, 10);
		p3 = new Professor("Cicrano 3", "222", 3000, 20);
		
		Relatorio relatorio = new Relatorio(6);
		relatorio.registra(f1);
		relatorio.registra(f2);
		relatorio.registra(f3);
		relatorio.registra(p1);
		relatorio.registra(p2);
		relatorio.registra(p3);
		
		relatorio.imprimirRelatorio();
		

	}

	
	
	
	
	
	
	
	
}
