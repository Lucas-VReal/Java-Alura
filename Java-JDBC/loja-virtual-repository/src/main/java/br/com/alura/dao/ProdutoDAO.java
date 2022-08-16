package br.com.alura.dao;

import br.com.alura.modelo.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private Connection conection;

    public ProdutoDAO(Connection conection){
        this.conection = conection;
    }

    public void salvar(Produto produto) throws SQLException {
        String sql = "INSERT INTO produto (nome, descricao) VALUES (?, ?)";

        try (PreparedStatement stm = conection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, produto.getNome());
            stm.setString(2, produto.getDescricao());

            stm.execute();

            try (ResultSet resultSet = stm.getGeneratedKeys()) {
                while (resultSet.next()) {
                    produto.setId(resultSet.getInt(1));
                }
            }
        }
    }

    public List<Produto> listar() throws SQLException {

        List<Produto> produtos = new ArrayList<Produto>();

        String sql = "SELECT * FROM produto";

        try (PreparedStatement stm = conection.prepareStatement(sql)){
            stm.execute();

            try (ResultSet rst = stm.getResultSet()){
                while (rst.next()){
                    Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
                    produtos.add(produto);
                }
            }
            return produtos;

        }





    }
}
