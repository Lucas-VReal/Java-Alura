package br.com.alura.gerenciador.servlet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import javax.servlet.anotation.WebFilter;

@WebFilter(urlPatterns="/entrada")
public class MonotoramentoFilter implements Filter {

    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long antes = System.currentTimeMillis();

        String acao = request.getParameter("acao");
        //executa a acao
        chain.doFilter(request, response);

        long depois = System.currentTimeMillis();

        System.out.println("Tempo de execução da ação: "+ acao + " -> " + (depois - antes));

    }



}