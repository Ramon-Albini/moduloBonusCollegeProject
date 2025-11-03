package moduloBonus.CollegeProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuloBonus {

	public static int executar(String caminhoDoArquivo) {
		Map<String, Departamento> departamentos = new HashMap<>();
		List<Funcionario> funcionarios = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(caminhoDoArquivo))){
			String linha;
			String nomeDepartamento = null, cargo = null, nomeFuncionario = null;
			double salario = 0, venda = 0;
			
			while ((linha = br.readLine()) != null) {
				String[] partes = linha.split(" ", 2);
				if (partes.length < 2) continue;
				String chave = partes[0];
				String valor = partes[1];
				
				switch(chave) {
				
					case "Departamento":
						nomeDepartamento = valor.trim();
						break;

						
					case "Codigo":
						if (nomeDepartamento != null) {
							departamentos.put(nomeDepartamento, new Departamento(nomeDepartamento, valor.trim()));
						}
						nomeDepartamento = null;
						break;
						
					case "Nome":
						nomeFuncionario = valor.trim();
						cargo = null;
						salario = 0;
						venda = 0;
						break;
						
					case "Cargo":
						cargo = valor.trim();
						break;
					
					case "Venda":
						venda = Double.parseDouble(valor.trim());
						break;
					
					case "Salario":
						salario = Double.parseDouble(valor.trim());
						if (departamentos.containsKey(nomeDepartamento)) {
							Departamento dep = departamentos.get(nomeDepartamento);
							
							Funcionario func;
							
							if ("Gerente".equalsIgnoreCase(cargo)) {
								func = new Gerente(nomeFuncionario, salario, dep);
							}else {
								func = new Vendedor(nomeFuncionario, salario, dep, venda);
							}
							
							dep.adicionarFuncionarios(func);
							funcionarios.add(func);
						}
						
						break;
				}
			}
			
			// Se não houver departamentos ou funcionários retorna código 1
			if (departamentos.isEmpty() || funcionarios.isEmpty())
				return 1;
			
            // Encontrar o departamento com maior total de vendas
			Departamento topDept = null;
			double maiorVenda = 0;
			for (Departamento dep : departamentos.values()) {
				double total = dep.getTotalVendas();
				if (total > maiorVenda) {
					maiorVenda = total;
					topDept = dep;
				}
			}
			
			//Aplicar o bonus
			if (topDept != null) {
				for (Funcionario func : topDept.getFuncionarios()) {
					if (!(func instanceof Gerente) && func.getSalario() <= 15000) {
						func.aplicarBonus(2000);
					}
				}
			}
			
			
            System.out.println("Departamento com maior venda: " + topDept.getName());
            for (Funcionario func : topDept.getFuncionarios()) {
                System.out.println(func.getName() + " - " + func.getCargo() + " - R$ " + func.getSalario());
            }
            
            return 0;
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
			return 1;
		}
	}
	
	
    public static void main(String[] args) {
        int resultado = executar("entrada.txt");
        System.out.println("Código de retorno: " + resultado);
    }
}
