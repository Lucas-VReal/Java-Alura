<%@ page language="java" contentType = "text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, br.com.alura.gerenciador.servlet.Empresa" %>
<html><body>
<ul>
<% 
	List<Empresa> lista = (List<Empresa>)request.getAttribute("empresas");
	for (Empresa empresa: lista){
%>
</ul>
		<li><%= empresa.getNome() %></li>
		<%} %>
</body></html>