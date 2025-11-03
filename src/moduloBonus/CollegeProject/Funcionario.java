package moduloBonus.CollegeProject;

public abstract class Funcionario {
	private String name;
	private String cargo;
	private double salario;
	private Departamento departamento;
	
	public Funcionario(String name, String cargo, double salario, Departamento departamento) {
		this.name = name;
		this.cargo = cargo;
		this.salario = salario;
		this.departamento = departamento;
	}

	//Geters
	public String getName() { return this.name; }
	public String getCargo() { return this.cargo; }
	public double getSalario() { return this.salario;}
	public Departamento getDepartamento() {	return this.departamento; }
	
    public void aplicarBonus(double valor) {
        this.salario += valor;
    }
}
