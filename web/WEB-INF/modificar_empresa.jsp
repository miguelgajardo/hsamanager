<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="controlador.*"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.Empresa"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page session="true"%>
<%
String rutempresa = request.getAttribute("rutempresamodificar").toString();
Empresa empresa = EmpresaDAO.getEmpresa(rutempresa);
String usuarioEmpresa =  empresa.getUsuario();
List<Usuario> usuarios = UsuarioDAO.getAllUsuarios();
Iterator<Usuario> usuariosIterator = usuarios.iterator();
Usuario usuario = null;
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Modificar Empresa <%= empresa.getRazonSocial() %>- HSA Auditores</title>
<!-- Custom fonts for this template-->
<link href="/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<!-- Custom styles for this template-->
<link href="/css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">
	
<!-- Menú Lateral -->
<jsp:include page="admin_nav.html"></jsp:include>

<!-- Content Wrapper -->
<div id="content-wrapper" class="d-flex flex-column">

	<!-- Main Content -->
	<div id="content">

	<!-- Admin Top Nav -->
	<jsp:include page="admin_top.jsp"></jsp:include>

	<!-- Begin Page Content -->
	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="d-sm-flex align-items-center justify-content-between mb-4">
			<h1 class="h3 mb-0 text-gray-800">Modificar Empresa</h1>
		</div>

		<!-- Fila Información de la Empresa a Modificar -->
		<div class="row">
			<div class="col-xl-12 col-lg-11">
				<div class="card shadow mb-4">
					<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
						<h6 class="m-0 font-weight-bold text-primary">Modificar Información de Empresa</h6>
					</div>
					<div class="card-body">
					<% 
					String actionUpdate = empresa.getUsuario().equals("Sin Asignar")? "actualizar_empresa_su" : "actualizar_empresa";
					%>
						<!-- Formulario para modificar Empresa -->
						<form action="/Admin/adminempresas?action=<% out.write("" + actionUpdate + "\"   method=\"POST\"");%>" >
							<div class="form-group row">
								<div class="col-md-4">
									<label for="rutpormodificar">RUT Empresa:</label>
									<input type="text" class="form-control" id="rutpormodificar" name="rutpormodificar" value="<%= empresa.getRut() %>">
									<small>RUT completo sin puntos ni guión Ej: 765554443</small>
								</div>
								<div class="col-md-8">
									<label for="modifnombre">Nombre o Razón Social:</label>
									<input type="text" id="modifnombre" class="form-control" name="modifnombre" value="<%= empresa.getRazonSocial() %>">
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-6">
									<label for="modifdomicilio">Domicilio:</label>
									<input type="text" class="form-control" id="modifdomicilio" name="modifdomicilio" value="<%= empresa.getDomicilio() %>">
								</div>
								<div class="col-md-6">
									<label for="modifemail">Correo Electrónico:</label>
									<input type="text" class="form-control" id="modifemail" name="modifemail" value="<%= empresa.getEmail() %>">	
									<input type="hidden" class="form-control" name="rutactual" value="<%= empresa.getRut() %>">
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-12">
								<% if (empresa.getUsuario().equals("Sin Asignar")) {
									out.write("<div class=\"alert alert-danger\" role=\"alert\">");
									out.write("Esta empresa no tiene un usuario asignado. Por favor, selecciona un usuario de la lista.");
									out.write("</div>");
									out.write("<label>Asignar Usuario a Empresa:</label>");
									out.write("<select class=\"form-control\" name=\"usuario\">");
									while (usuariosIterator.hasNext()) {
									usuario = usuariosIterator.next();
									String usuariosLabel = usuario.toString();
									out.write("<option label=\" " + usuariosLabel + " \"> " + usuario.getRut().toString() + " </option>");
									}
									out.write("</select> ");
								} else {
									out.write("<label for=\"usuario\">Usuario:</label>");
									out.write("<input type=\"text\" class=\"form-control\" id=\"usuario\" placeholder=\" "  + usuarioEmpresa + " \"readonly>");
								}
								%>									
									
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-6">
									<button type="submit" name=updateempresasubmit class="btn btn-secondary btn-outline"><i class="fa fa-save"></i> Guardar Cambios</button>
								</div>
							</div>
						</form>
						<!-- / Formulario para modificar Empresa -->
					</div>
				</div>
			</div>
		</div>
		<!-- / Fila Información de la Empresa a Modificar -->
		
	</div>
	<!-- /.container-fluid -->

	</div>
	<!-- End of Main Content -->

	<!-- Footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- End of Footer -->

</div>
<!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"><i class="fa fa-angle-up"></i></a>

	<!-- Logout Modal-->
	<jsp:include page="logout_modal.jsp"></jsp:include>
	

	<!-- Bootstrap core JavaScript-->
	<script src="/vendor/jquery/jquery.min.js"></script>
	<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="/js/sb-admin-2.min.js"></script>


</body>

</html>