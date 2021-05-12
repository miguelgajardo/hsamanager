<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
    <%@ page import="controlador.*"%>
    
    <%@page session="true" %>
    
    
   <%
	session = request.getSession();

	if (session.getAttribute("logout") == null) {
		response.sendRedirect("/Login");
	} else if (session.getAttribute("iam").equals("Administrador")) {
		request.getRequestDispatcher("/WEB-INF/admin_principal.jsp").forward(request, response);
	} else if (session.getAttribute("iam").equals("Usuario")) {
		request.getRequestDispatcher("/WEB-INF/empresa_principal.jsp").forward(request, response);
	}
	%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</html>