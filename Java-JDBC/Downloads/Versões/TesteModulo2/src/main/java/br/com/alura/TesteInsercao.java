package br.com.alura;

import br.com.alura.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteInsercao {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection conection = factory.recuperarConexao();

        Statement stm = conection.createStatement();
        stm.execute("INSERT INTO produto (nome, descricao) values ('Mouse', 'Mouse sem fio')", Statement.RETURN_GENERATED_KEYS);

        ResultSet rst = stm.getGeneratedKeys();

        while(rst.next()){
            Integer id = rst.getInt(1);
            System.out.println("Seu id é " + id);
        }

        rst.close();
        stm.close();
        conection.close();
    }
}
