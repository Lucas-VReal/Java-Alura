package br.com.alura.aplication;

import br.com.alura.factory.ConectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteDeConexao {
    public static void main(String[] args) throws SQLException {

        ConectionFactory cc = new ConectionFactory();

        Connection con = cc.recuperarConexao();

        System.out.println("A Conexão foi fechada");

        con.close();
    }
}
