package br.com.alura;

import br.com.alura.dao.ProdutoDAO;
import br.com.alura.modelo.Produto;


import java.sql.*;
import java.util.List;

public class TestaInsercaoEListagemProduto {

    public static void main(String[] args) throws SQLException {

        Produto comoda = new Produto("Comoda", "Vertical");

        try (Connection conection = new ConectionFactory().recuperarConexao()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(conection);
            produtoDAO.salvar(comoda);
            List<Produto> lista = produtoDAO.listar();

            lista.stream().forEach(lp -> System.out.println(lp));
        }

    }
}