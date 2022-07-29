package br.com.alura;

import java.sql.SQLException;

public class TestePoolConexões {

    public static void main(String[] args) {


        ConectionFactory conectionFactory = new ConectionFactory();

        for (int i = 0; i < 20; i++) {
            try {
                conectionFactory.recuperarConexao();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Conexão de número " + (i + 1));
        }
    }
}
