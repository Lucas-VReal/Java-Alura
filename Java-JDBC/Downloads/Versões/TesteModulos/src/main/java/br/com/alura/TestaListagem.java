package br.com.alura;

import br.com.alura.factory.ConnectionFactory;

import java.sql.*;

public class TestaListagem {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection conection = factory.recuperarConexao();

        PreparedStatement stm = conection.prepareStatement("SELECT * FROM produto");
        boolean resultado = stm.execute();

        ResultSet rst = stm.getResultSet();
        while(rst.next()) {
            int id = rst.getInt("id");
            String nome = rst.getString("nome");
            String descricao = rst.getString("descricao");
            System.out.println(id);
            System.out.println(nome);
            System.out.println(descricao);
        }

    }
}
