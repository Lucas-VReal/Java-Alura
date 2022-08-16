package br.com.alura.aplication;

import br.com.alura.factory.ConectionFactory;

import java.sql.*;

public class TestaInsercaoComParametro {

    public static void main(String[] args) throws SQLException {
        ConectionFactory factory = new ConectionFactory();
        try (Connection cn = factory.recuperarConexao()) {
            cn.setAutoCommit(false);

            try (PreparedStatement stm = cn.prepareStatement("INSERT INTO produto (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                novoProduto("Mouse1", "Novo Mouse 1", stm);
                novoProduto("Mouse2", "Novo Mouse 2", stm);

                cn.commit();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("RollBack Executado");
                cn.rollback();
            }
        }

        }

        private static void novoProduto (String nome, String descricao, PreparedStatement stm) throws SQLException {
            stm.setString(1, nome); //referência ao primeiro '?'
            stm.setString(2, descricao);//referência ao segundo '?'

            if (nome.equalsIgnoreCase("Mouse2")) {
                throw new RuntimeException("Não foi possível adicionar o produto");
            }

            stm.execute();

            try (ResultSet rst = stm.getGeneratedKeys();) {
                while (rst.next()) {
                    Integer id = rst.getInt(1); //pegar primeira coluna
                    System.out.println("O id criado foi: " + id);
                }
            }
        }
}
