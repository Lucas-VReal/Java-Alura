package br.com.alura.gerenciador.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class AlterarEmpresaServlet
 */
public class AlterarEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomeDaEmpresa = request.getParameter("nome");
		String dateParam = request.getParameter("data");
		String ParamId = request.getParameter("id");
		Integer id = Integer.valueOf(ParamId);
		
		Date dataDeAbertura = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dataDeAbertura = sdf.parse(dateParam);
		} catch (ParseException e1) {
			throw new ServletException(e1);
		}
		
		System.out.println(id);
		
		Banco banco = new Banco();
		Empresa empresa = banco.buscarEmpresaPeloId(id);
		empresa.setNome(nomeDaEmpresa);
		empresa.setDataAbertura(dataDeAbertura);
		
		response.sendRedirect("listaEmpresas");
		
	}

}
