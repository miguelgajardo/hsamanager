<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="controlador.*"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HSA Manager - Ingresar</title>
</head>
<body>
<%
	session = request.getSession();

	if (session.getAttribute("logout") == null) {
		request.getRequestDispatcher("loginform?action=ingresar").forward(request, response);
	} else if (session.getAttribute("iam").equals("Administrador")) {
		response.sendRedirect("/Admin");
	} else if (session.getAttribute("iam").equals("Usuario")) {
		response.sendRedirect("/Usuario");
	}
	%>
</body>
</html>