package br.com.alura.spring.data.service;


import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargo {
    private final CargoRepository repository;

    public CrudCargo(CargoRepository repository) {
        this.repository = repository;
    }

    public void inicial(Scanner scanner){
        boolean system = true;

        while(system) {

            System.out.println("Bem vindo ao Menu de alteração de Cargos:");
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


    public void salvar(Scanner scanner){
        System.out.println("Informe a descrição do Cargo");
        String resposta = scanner.next();

        var cargo = new Cargo();
        cargo.setDescricao(resposta);
        repository.save(cargo);

        System.out.println("Salvo com o ID: " + cargo.getId());
    }


    private void atualizar (Scanner scanner){

        System.out.println("Informe o ID do cargo que deseja atualizar:");
        int id = scanner.nextInt();

        System.out.println("Informe a nova descricao do cargo de ID n° "+ id);
        String descricao = scanner.next();

        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(descricao);

        repository.save(cargo);
        System.out.println("Atualizado");

    }

    private void visualizar(){
        Iterable<Cargo> cargos = repository.findAll();
        cargos.forEach(cargo -> System.out.println(cargo));
    }

    private void deletar(Scanner scanner){
        System.out.println("Informe o ID do cargo que deseja deletar:");
        int id = scanner.nextInt();

        repository.deleteById(id);
        System.out.println("Deletado");
    }

}
