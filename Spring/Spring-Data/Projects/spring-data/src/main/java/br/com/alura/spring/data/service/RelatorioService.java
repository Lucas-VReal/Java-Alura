package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
            System.out.println("Digite 3. Para Buscar Funcionarios pela Data de Contratacao");
            System.out.println("Digite 4. Para Buscar Funcionarios e Salarios");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:buscarFuncionariosPeloNome(scanner);break;
                case 2:buscarFuncionariosSalarioMaiorData(scanner);break;
                case 3:buscarFuncionariosPorDataDeContratacao(scanner);break;
                case 4:pesquisarFuncionarioSalario();break;
                default:system = false;break;
            }
        }
    }

    private void buscarFuncionariosPorDataDeContratacao(Scanner scanner) {
        System.out.println("Digite a data mínima de contratacao:");
        String dataString = scanner.next();
        LocalDate data = LocalDate.parse(dataString, formatter);

        List<Funcionario> funcionarios = funcionarioRepository.findMaiorDataContratacao(data);
        funcionarios.forEach(funcionario -> System.out.println(funcionarios));
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

        System.out.println("Escreva o numero da pagina desejado");
        Integer page = scanner.nextInt();

        System.out.println("Digite o nome do(s) Funcionario(s) desejado(s):");
        String nome = scanner.next();

        Pageable pageable = PageRequest.of(page, 5, Sort.unsorted());

        List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome, pageable);
        funcionarios.forEach(System.out::println);
    }

    private void pesquisarFuncionarioSalario(){
        List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();

        list.forEach(f -> System.out.println("Funcionario: Id: " + f.getId() +
                " | Nome: " + f.getNome() + " | Salario: " + f.getSalario()));
    }

}
