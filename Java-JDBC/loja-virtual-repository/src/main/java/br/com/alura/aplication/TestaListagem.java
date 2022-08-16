package br.com.alura.aplication;

import br.com.alura.factory.ConectionFactory;

import java.sql.*;

public class TestaListagem {

        public static void main(String[] args) throws SQLException {


            ConectionFactory cc = new ConectionFactory();
            Connection con = cc.recuperarConexao();

            PreparedStatement stm = con.prepareStatement("select id, nome, descricao from produto");
            stm.execute ();

            ResultSet rst = stm.getResultSet();

            while (rst.next()){
                Integer id = rst.getInt("id");
                System.out.println(id);
                String nome = rst.getString("nome");
                System.out.println(nome);
                String descricao = rst.getString("descricao");
                System.out.println(descricao);
            }

            con.close();
        }
}
