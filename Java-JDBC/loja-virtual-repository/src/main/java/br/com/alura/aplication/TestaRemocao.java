package br.com.alura.aplication;

import br.com.alura.factory.ConectionFactory;

import java.sql.*;

public class TestaRemocao {
    public static void main(String[] args) throws SQLException {

        ConectionFactory factory = new ConectionFactory();
        Connection cn = factory.recuperarConexao();

        PreparedStatement stm = cn.prepareStatement("DELETE FROM PRODUTO  WHERE id > ?");
        stm.setInt(1, 2);
        stm.execute();

        Integer linhasModificadas = stm.getUpdateCount();

        System.out.println("A Quantidade total de linhas que foram modificas foi " + linhasModificadas);


    }
}
