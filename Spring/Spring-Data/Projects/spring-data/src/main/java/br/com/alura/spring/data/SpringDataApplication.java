package br.com.alura.spring.data;

import br.com.alura.spring.data.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargo cargoService;
	private final CrudFuncionario funcionarioService;
	private final CrudUnidade unidadeService;

	private final RelatorioService relatorioService;

	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

	public SpringDataApplication(CrudCargo cargoService, CrudUnidade unidadeService, CrudFuncionario funcionarioService, RelatorioService relatorioService, RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {

		this.cargoService = cargoService;
		this.unidadeService = unidadeService;
		this.funcionarioService = funcionarioService;
		this.relatorioService = relatorioService;
		this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringDataApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		Boolean continuar = true;
		Scanner scanner = new Scanner(System.in);

		while(continuar) {


			System.out.println("Qual açao voce deseja executar: ");
			System.out.println("Digite 0 para sair");
			System.out.println("Digite 1 para Funcionario");
			System.out.println("Digite 2 para Cargo");
			System.out.println("Digite 3 para Unidade");
			System.out.println("Digite 4 para Relatorios");
			System.out.println("Digite 5 para Relatorio Dinamico de Funcionarios");


			int valor = scanner.nextInt();

			switch (valor){

				case 1: funcionarioService.inicial(scanner);break;
				case 2: cargoService.inicial(scanner);break;
				case 3: unidadeService.inicial(scanner);break;
				case 4: relatorioService.inicial(scanner);break;
				case 5: relatorioFuncionarioDinamico.inicial(scanner);break;
				case 0: System.out.println("Obrigado por utilizar :)");continuar = false; break;
				default: System.out.println("Opção invalida");break;

			}
		}

	}
}
