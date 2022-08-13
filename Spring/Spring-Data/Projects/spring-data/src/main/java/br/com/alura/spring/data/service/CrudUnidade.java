package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudUnidade {

    private final UnidadeRepository unidadeRepository;
    private final FuncionarioRepository funcionarioRepository;

    public CrudUnidade(UnidadeRepository unidadeRepository, FuncionarioRepository funcionarioRepository) {
        this.unidadeRepository = unidadeRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner){
        boolean system = true;

        while(system) {

            System.out.println("Bem vindo ao Menu de alteração de Unidades:");
            System.out.println("");
            System.out.println("Digite 0. Para voltar ao Menu inicial");
            System.out.println("Digite 1. Para Salvar");
            System.out.println("Digite 2. Para visualizar");
            System.out.println("Digite 3. Para Atualizar");
            System.out.println("Digite 4. Para Deletar Unidade");

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


    void salvar(Scanner scanner){

        var unidade = new UnidadeTrabalho();

        System.out.println("Quais são os dados dessa Unidade?");
        System.out.println("1. Descricao");
        String descricao = scanner.next();
        unidade.setDescricao(descricao);
        System.out.println("2. Endereço");
        String endereco = scanner.next();
        unidade.setEndereco(endereco);
        System.out.println("3. Lista de Funcionarios");
        unidade.setFuncionarios(atribuirFuncionario(scanner));

        unidadeRepository.save(unidade);

    }

    private List<Funcionario> atribuirFuncionario(Scanner scanner) {

        boolean continuar = true;
        List<Funcionario> funcionarios = null;

        while (continuar){
            System.out.println("Deseja cadastrar mais um Funcionário? Y or N");
            String resposta = scanner.next();
            if(resposta.equalsIgnoreCase("Y")){
                System.out.println("Informe o ID do Funcionario");
                Integer id = scanner.nextInt();
                var funcionário = new Funcionario();
                Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);
                Funcionario funcionario = funcionarioOptional.get();
                funcionarios.add(funcionario);
            } else{
                continuar = false;
            }

        }

        return funcionarios;

    }




    private void atualizar (Scanner scanner){


        System.out.println("Informe o ID da Unidade");
        Integer id = scanner.nextInt();
        Optional<UnidadeTrabalho> unidadeTrabalhoOptional =  unidadeRepository.findById(id);
        UnidadeTrabalho unidade = unidadeTrabalhoOptional.get();

        System.out.println("Quais são os dados dessa Unidade?");
        System.out.println("1. Descricao");
        String descricao = scanner.next();

        System.out.println("2. Endereço");
        System.out.println("Deseja alterar o endereço: " + unidade.getEndereco() + "?");
        String resposta = scanner.next();

        if(resposta.equalsIgnoreCase("Y")){
            System.out.println("Qual é o novo endereço?");
            String endereco = scanner.next();
            unidade.setEndereco(endereco);
        }

        System.out.println("3. Lista de Funcionarios");
        System.out.println("Deseja alterar a Lista de Funcionários dessa Unidade?");
        resposta = scanner.next();

        if(resposta.equalsIgnoreCase("Y")){
            System.out.println("Deseja 1. Cadastrar ou 2. remover:");
            int respostaF = scanner.nextInt();
            switch (respostaF){
                case 1: unidade.setFuncionarios(atribuirFuncionario(scanner));break;
                case 2: unidade.setFuncionarios(removerFuncionarios(unidade, scanner));break;
            }

        }

        unidadeRepository.save(unidade);

    }

    private List<Funcionario> removerFuncionarios(UnidadeTrabalho unidade, Scanner scanner) {
        List<Funcionario> funcionarios = unidade.getFuncionarios();
        boolean continuar = true;

        while (continuar){
            System.out.println("Informe o ID do Funcionário a ser removido");
            Integer id = scanner.nextInt();
            Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);
            Funcionario funcionario = funcionarioOptional.get();
            System.out.println("Funcionario " + funcionario.getNome() + "removido");
            funcionarios.remove(funcionario);
            System.out.println("Deseja continuar? Y or N");
            String sair = scanner.next();
            if(sair.equalsIgnoreCase("N")){
                continuar = false;
            }
        }

        return funcionarios;

    }


    private void visualizar(){
        Iterable<UnidadeTrabalho> unidades = unidadeRepository.findAll();
        unidades.forEach(unidadeTrabalho -> System.out.println(unidades));
    }

    private void deletar(Scanner scanner){

        System.out.println("Informe o ID da Unidade que deseja deletar:");
        int id = scanner.nextInt();

        unidadeRepository.deleteById(id);
        System.out.println("Deletado");
    }

}
