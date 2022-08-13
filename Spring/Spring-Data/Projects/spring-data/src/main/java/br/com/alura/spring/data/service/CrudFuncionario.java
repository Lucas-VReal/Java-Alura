package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionario {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final FuncionarioRepository repository;
    private final CargoRepository cargoRepository;
    private final UnidadeRepository unidadeRepository;
    private final CrudCargo cargoService;

    private final CrudUnidade unidadeService;

    public CrudFuncionario(FuncionarioRepository repository, CargoRepository cargoRepository, UnidadeRepository unidadeRepository, CrudCargo cargoService, CrudUnidade unidadeService) {
        this.repository = repository;
        this.cargoRepository = cargoRepository;
        this.unidadeRepository = unidadeRepository;
        this.cargoService = cargoService;
        this.unidadeService = unidadeService;
    }

    public void inicial(Scanner scanner){
        boolean system = true;

        while(system) {

            System.out.println("Bem vindo ao Menu de alteração de Funcionarios:");
            System.out.println("");
            System.out.println("Digite 0. Para voltar ao Menu inicial");
            System.out.println("Digite 1. Para Salvar");
            System.out.println("Digite 2. Para visualizar");
            System.out.println("Digite 3. Para Atualizar");
            System.out.println("Digite 4. Para Deletar");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:salvar(scanner);break;
                case 2:visualizar();break;
                case 3:atualizar(scanner);break;
                case 4: deletar(scanner);break;
                default:system = false;break;
            }
        }

    }


    private void salvar(Scanner scanner){

        var funcionario = new Funcionario();

        System.out.println("Quais são os dados desse Funcionário?");
        System.out.println("1. Nome");
        String nome= scanner.next();
        funcionario.setNome(nome);
        System.out.println("2. CPF");
        String cpf = scanner.next();
        funcionario.setCpf(cpf);
        System.out.println("3. salario");
        Double salario = scanner.nextDouble();
        funcionario.setSalario(salario);
        System.out.println("4. Data de Contratação");
        String dataS = scanner.next();
        LocalDate data = LocalDate.parse(dataS, formatter);
        funcionario.setDataContratacao(data);

        Cargo cargo = atribuirCargo(scanner);
        funcionario.setCargo(cargo);

        List<UnidadeTrabalho> unidadeTrabalho = atribuirUnidade(scanner);
        funcionario.setUnidadeTrabalhos(unidadeTrabalho);

        repository.save(funcionario);


        System.out.println("Salvo");

    }

    private List<UnidadeTrabalho> atribuirUnidade(Scanner scanner) {

        Boolean isTrue = true;
        List<UnidadeTrabalho> unidades = new ArrayList<>();

        System.out.println("Deseja utilizar uma Unidade já existente ou criar nova Unidade? 1. atribuir 2. criar");
        int resposta = scanner.nextInt();

        if(resposta == 1) {

            while (isTrue) {
                System.out.println("Digite o unidadeId (Para sair digite 0)");
                Integer id = scanner.nextInt();
                boolean existe = unidadeRepository.existsById(id);

                if (existe) {
                    Optional<UnidadeTrabalho> unidade = unidadeRepository.findById(id);
                    unidades.add(unidade.get());
                    isTrue = false;
                }
            }
        }

        else{
            unidadeService.salvar(scanner);
        }
        return unidades;
    }

    private Cargo atribuirCargo(Scanner scanner) {
        boolean existe = true;
        Cargo cargo = new Cargo();

        while(existe){

            System.out.println("Deseja utilizar um cargo já existente ou criar novo cargo? 1. atribuir 2. criar");
            int resposta = scanner.nextInt();

            if(resposta == 1) {
                System.out.println("Informe o ID do Cargo");
                Integer id = scanner.nextInt();
                boolean existente = cargoRepository.existsById(id);

                if (existente) {
                    Optional<Cargo> cargoOptional = cargoRepository.findById(id);
                    BeanUtils.copyProperties(cargoOptional, cargo);
                    cargo.setId(cargoOptional.get().getId());
                    existe = false;
                }
            }else{
                cargoService.salvar(scanner);
            }

        }
        return cargo;
    }


    private void atualizar (Scanner scanner){


        System.out.println("Informe o ID do Funcionário que deseja atualizar:");
        int id = scanner.nextInt();

        var funcionario = buscarFuncionario(scanner, id);


        System.out.println("Dados do funcionario " + funcionario.getNome() + ": ");
        String resposta = "nada";

        System.out.println("1.Deseja alterar o Nome? Y or N");
        resposta = scanner.next();
        if(resposta.equalsIgnoreCase("Y")) {
            String nome = scanner.next();
            funcionario.setNome(nome);
        }
        System.out.println("2.Deseja alterar o CPF? Y or N");
        resposta = scanner.next();
        if(resposta.equalsIgnoreCase("Y")) {
            String cpf = scanner.next();
            funcionario.setCpf(cpf);
        }
        System.out.println("3. Deseja alterar o Salario? Y or N");
        resposta = scanner.next();
        if(resposta.equalsIgnoreCase("Y")) {
            Double salario = scanner.nextDouble();
            funcionario.setSalario(salario);
        }
        System.out.println("4. Deseja alterar a Data de Contratação? Y or N");
        resposta = scanner.next();
        if(resposta.equalsIgnoreCase("Y")) {
            LocalDate data = LocalDate.parse(scanner.next());
            funcionario.setDataContratacao(data);
        }
        System.out.println("5. Deseja alterar o cargo? Y or N");
        resposta = scanner.next();
        if(resposta.equalsIgnoreCase("Y")) {
            Cargo cargo = atribuirCargo(scanner);
            funcionario.setCargo(cargo);
        }
        System.out.println("5. Deseja alterar a Unidade de Trabalho? Y or N");
        resposta = scanner.next();
        if(resposta.equalsIgnoreCase("Y")) {
            List<UnidadeTrabalho> unidadeTrabalho = atribuirUnidade(scanner);
            funcionario.setUnidadeTrabalhos(unidadeTrabalho);
        }

        repository.save(funcionario);


    }

    private Funcionario buscarFuncionario(Scanner scanner, int id) {

        var funcionario = new Funcionario();
        Optional<Funcionario> funcionarioOp = repository.findById(id);
        funcionario = funcionarioOp.get();

        return funcionario;
    }

    private void visualizar(){
        Iterable<Funcionario> funcionarios = repository.findAll();
        funcionarios.forEach(funcionario -> System.out.println(funcionarios));
    }

    private void deletar(Scanner scanner){
        System.out.println("Informe o ID do Funcionario que deseja deletar:");
        int id = scanner.nextInt();

        repository.deleteById(id);
        System.out.println("Deletado");

    }

}
