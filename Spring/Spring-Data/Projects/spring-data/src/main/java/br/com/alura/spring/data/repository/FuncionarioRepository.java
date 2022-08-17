package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>, JpaSpecificationExecutor<Funcionario> {

    List<Funcionario> findByNome(String nome, Pageable pageable);

    @Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario > :salario AND f.dataContratacao = :data")
    List<Funcionario> findNomeMaiorSalarioDataDeContratacao(String nome, double salario, LocalDate data);

    @Query(value = "SELECT * FROM funcionarios WHERE data_contratacao >= :data", nativeQuery = true)
    List<Funcionario> findMaiorDataContratacao(LocalDate data);
@Query(value = "SELECT f.id, f.nome, f.salario from funcionarios f", nativeQuery = true)
List<FuncionarioProjecao> findFuncionarioSalario();
}
