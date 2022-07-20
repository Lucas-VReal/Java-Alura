package br.com.alura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteDeConexao {
    public static void main(String[] args) throws SQLException {

        Connection conection = DriverManager
                .getConnection("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC","root", "root");

        conection.close();
    }
}
