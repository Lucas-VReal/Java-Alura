<%@ page language="java" contentType = "text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, br.com.alura.gerenciador.servlet.Empresa" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<meta charset="ISO-8859-1">
	<title> Java Standart Taglib</title>
</head>

<body>
	
	
	Lista de Empresas <br />
	<ul>
		<c:forEach items="${empresas}" var="empresa">
					
			<li>
				${empresa.nome } - <fmt:formatDate value="${empresa.dataAbertura}" pattern="dd/MM/yyyy"/>
				<a href="/gerenciador/mostrarEmpresa?id=${empresa.id }">editar</a>
				<a href="/gerenciador/removeEmpresa?id=${empresa.id }">remove</a>
			</li>
	
		</c:forEach>

	</ul>
</body>

</html>