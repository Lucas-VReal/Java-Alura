package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class RelatorioService {

    private final FuncionarioRepository funcionarioRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatorioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        boolean system = true;

        while (system) {
            System.out.println("Bem vindo ao Menu de Relatorios:");
            System.out.println("");
            System.out.println("Digite 0. Para voltar ao Menu inicial");
            System.out.println("Digite 1. Para Buscar Funcionarios pelo Nome");
            System.out.println("Digite 2. Para Buscar Funcionarios pelo Salario e Data de Contratacao");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:buscarFuncionariosPeloNome(scanner);break;
                case 2:buscarFuncionariosSalarioMaiorData(scanner);break;
                default:system = false;break;
            }
        }
    }

    private void buscarFuncionariosSalarioMaiorData(Scanner scanner) {
        System.out.println("Digite o nome do(s) Funcionario(s) desejado(s):");
        String nome = scanner.next();

        System.out.println("Digite a Data de Contratacao desejada:");
        String data = scanner.next();
        LocalDate date = LocalDate.parse(data, formatter);

        System.out.println("O salário maior ou igual a:");
        Double salario = scanner.nextDouble();

        List<Funcionario> funcionarios = funcionarioRepository.findNomeMaiorSalarioDataDeContratacao(nome, salario, date);
        funcionarios.forEach(System.out::println);

    }

    private void buscarFuncionariosPeloNome(Scanner scanner) {

        System.out.println("Digite o nome do(s) Funcionario(s) desejado(s):");
        String nome = scanner.next();
        List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);
        funcionarios.forEach(System.out::println);
    }

}
