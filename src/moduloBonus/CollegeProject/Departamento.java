package moduloBonus.CollegeProject;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
	private String name;
	private String codigo;
	private List<Funcionario> funcionarios = new ArrayList<>();
	
	public Departamento(String name, String codigo) {
		super();
		this.name = name;
		this.codigo = codigo;
	}

	public String getName() { return name; }
	public String getCodigo() { return codigo; }
	public List<Funcionario> getFuncionarios() { return funcionarios; }
	
	public void adicionarFuncionarios(Funcionario f) {
		this.funcionarios.add(f);
	}
	
	public double getTotalVendas() {
		double total = 0;
		for (Funcionario f: funcionarios) {
			if (f instanceof Vendedor) {
				total += ((Vendedor) f).getVendas();
			}
		}
		return total;
	}
		
}
