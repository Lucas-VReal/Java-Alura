package br.com.alura.testes;

import br.com.alura.dao.CategoriaDAO;
import br.com.alura.dao.ProdutoDAO;
import br.com.alura.model.Categoria;
import br.com.alura.model.Produto;
import br.com.alura.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {

        cadastrarProduto();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        Produto p = produtoDAO.buscarPorId(1L);
        System.out.println(p.getPreco());

        //List<Produto> lista = produtoDAO.buscarTodos();
        //lista.forEach(p2 -> System.out.println("Nome: " + p2.getNome() + " Descrição: "+ p2.getDescricao()));

        //List<Produto> lista2 = produtoDAO.buscarPorNome("Xiaomi");
        //lista2.forEach(p3 -> System.out.println("Nome: " + p3.getNome() + " Descrição: "+ p3.getDescricao()));

        //List<Produto> lista2 = produtoDAO.buscarPorNomeDaCategoria("CELULARES");
        //lista2.forEach(p3 -> System.out.println("Nome: " + p3.getNome() + " Descrição: "+ p3.getDescricao()));

        BigDecimal preco = produtoDAO.buscarPorPrecoUtilizandoNome("Xiaomi");
        System.out.println("O preço do "+ p.getNome() + " foi de R$ " + preco);


    }



    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi", "Muito Legal" , new BigDecimal(800), celulares);


        EntityManager em = JPAUtil.getEntityManager();

        ProdutoDAO productDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);


        em.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        productDAO.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
    }
}
