package br.com.alura.gerenciador.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import br.com.alura.gerenciador.acao.ListarEmpresas;
import br.com.alura.gerenciador.acao.MostrarEmpresa;
import br.com.alura.gerenciador.acao.RemoverEmpresa;


public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String paramAcao = request.getParameter("acao");
		
		if (paramAcao.equals("ListaEmpresas")) {
			
			ListarEmpresas acao = new ListarEmpresas();
			acao.executa(request, response);
			
		}else if (paramAcao.equals("RemovaEmpresa")) {
			
			RemoverEmpresa acao = new RemoverEmpresa();
			acao.executa(request, response);
			
		}else if (paramAcao.equals("MostraEmpresa")) {
			
			MostrarEmpresa acao = new MostrarEmpresa();
			acao.executa(request, response);
			
		}
		
	}

}
