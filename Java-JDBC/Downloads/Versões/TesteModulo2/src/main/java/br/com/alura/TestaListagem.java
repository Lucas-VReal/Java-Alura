package br.com.alura;

import br.com.alura.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection conection = factory.recuperarConexao();

        Statement stm = conection.createStatement();
        boolean resultado = stm.execute("SELECT * FROM produto");

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
