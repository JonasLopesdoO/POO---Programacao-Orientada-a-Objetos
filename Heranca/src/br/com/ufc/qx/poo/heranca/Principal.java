package br.com.ufc.qx.poo.heranca;

public class Principal {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Funcionario f = new Funcionario();
		f.setNome("Peao");
		f.setSalario(788);
		f.setCpf("0000000000");
		System.out.println(f.getDetalhes());
		f.setSalario(-5);
		System.out.println(f.getDetalhes());
		
		
		Gerente g = new Gerente("12345");
		g.setNome("Rico");
		g.setCpf("111111");
		g.setSalario(100000);
		System.out.println(g.getDetalheGerente());
		g.aumento(-2);
		System.out.println(g.getDetalheGerente());
			
	}

}
