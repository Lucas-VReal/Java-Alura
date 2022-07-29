package br.com.alura;

import java.sql.*;

public class TestaInsercao {

    public static void main(String[] args) throws SQLException {
        ConectionFactory factory = new ConectionFactory();
        Connection cn = factory.recuperarConexao();

        PreparedStatement stm = cn.prepareStatement("INSERT INTO produto (nome, descricao) VALUES ('Mouse', 'Mouse sem fio')", Statement.RETURN_GENERATED_KEYS);
        stm.execute();

        ResultSet rst = stm.getGeneratedKeys();

        while(rst.next()) {
            Integer id = rst.getInt(1); //pegar primeira coluna
            System.out.println("O id criado foi: " + id);
        }

    }
}
