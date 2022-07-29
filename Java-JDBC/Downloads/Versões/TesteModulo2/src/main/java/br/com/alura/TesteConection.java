package br.com.alura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConection {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/loja_virtual2?useTimezone=true&serverTimezone=UTC","root", "123456");

        System.out.println("Fechando conexão");

        connection.close();
    }

}
