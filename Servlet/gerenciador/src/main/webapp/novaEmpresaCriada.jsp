<%
	String nomeDaEmpresa = (String)request.getAttribute("empresa");
	System.out.println(nomeDaEmpresa);
%>
<html>
<body>
<%//Exemplo: Empresa  out.println(nomeDaEmpresa); cadastrada com sucesso!%>
Empresa  <%=nomeDaEmpresa%> cadastrada com sucesso!
</body>
</html>