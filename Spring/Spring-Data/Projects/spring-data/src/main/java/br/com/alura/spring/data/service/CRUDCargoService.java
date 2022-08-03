package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CRUDCargoService {
    private final CargoRepository repository;

    public CRUDCargoService(CargoRepository repository) {
        this.repository = repository;
    }

    public void inicial(){

        Boolean continuar = true;
        Scanner scanner = new Scanner(System.in);

        while(continuar) {

            System.out.println("Qual ação você deseja executar: ");
            System.out.println("Digite 0 para sair");
            System.out.println("Digite 1 para Novo Cargo");
            System.out.println("Digite 2 para Atualizar Cargo");


            int valor = scanner.nextInt();

            switch (valor){
                case 1: salvar(scanner);break;
                case 2: atualizar(scanner);break;
                default: continuar = false; break;
            }
        }
    }

    private void salvar(Scanner scanner){

        System.out.println("Escreva a descrição que deseja inserir: ");
        String descricao = scanner.next();

        Cargo cargo = new Cargo();

        cargo.setDescricao(descricao);
        repository.save(cargo);
        System.out.println("Salvo");
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
}
