package aluno.manager;

import java.util.List;

import aluno.base.Funcionario;
import cliente.IRHService;

public class RHService implements IRHService{

	@Override
	public boolean cadastrar(Funcionario funcionario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remover(String cpf) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Funcionario obterFuncionario(String cpf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Funcionario> getFuncionarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Funcionario> getFuncionariosPorCategoria(Tipo tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalFuncionarios() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean solicitarDiaria(String cpf) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean partilharLucros(double valor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void iniciarMes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Double calcularSalarioDoFuncionario(String cpf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double calcularFolhaDePagamento() {
		// TODO Auto-generated method stub
		return 0;
	}
		
}
