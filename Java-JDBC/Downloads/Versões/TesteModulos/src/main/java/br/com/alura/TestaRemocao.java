package br.com.alura;

import br.com.alura.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection conection = factory.recuperarConexao();

        PreparedStatement stm = conection.prepareStatement("DELETE FROM PRODUTO WHERE ID > 2");
        stm.execute();

        Integer linhasModificadas = stm.getUpdateCount();

        System.out.println("Quantidade de linhas que foram modificadas: " + linhasModificadas);

        stm.close();
        conection.close();

    }
}
