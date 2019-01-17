package br.com.ufc.qx.poo.gestao;

public class Relatorio {
	
	private double totalSalario;
	private Funcionario[] funcionarios;
	private int contador;
	
	public Relatorio(int numFuncionarios){
		funcionarios = new Funcionario[numFuncionarios];
		contador = 0;
	}
	
	public void registra(Funcionario funcionario){
		totalSalario += funcionario.getSalario();
		funcionarios[contador++] = funcionario;
	}
	
	public Funcionario getFuncionario(int posicao){
		return funcionarios[posicao];
	}
	
	public void imprimirRelatorio(){
		System.out.println("------------------------");
		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario);
		}
		System.out.println("------------------------");
		System.out.println("Total de salario = " + totalSalario);
	}
}
