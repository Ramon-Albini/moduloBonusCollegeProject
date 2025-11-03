package moduloBonus.CollegeProject;

public class Vendedor extends Funcionario{
	
	private double vendas;
	
	public Vendedor(String name, double salario, Departamento departamento, double vendas) {
		super(name, "Vendedor", salario, departamento);
		this.vendas = vendas;
	}

	public double getVendas() { return this.vendas;	}
	
}
