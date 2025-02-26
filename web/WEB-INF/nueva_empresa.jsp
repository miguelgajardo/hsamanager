<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controlador.*"%>
<%@page import="modelo.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page session="true"%>
<%
List<Usuario> usuarios = UsuarioDAO.getAllUsuarios();
Iterator<Usuario> iteradorUsuarios = usuarios.iterator();
Usuario usersList = null;
%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html" charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>HSA Auditores - Registrar Nueva Empresa</title>
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
				
	<!-- Contenedor Fluid -->
	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="d-sm-flex align-items-center justify-content-between mb-4">
			<h1 class="h3 mb-0 text-gray-800">Registrar Nueva Empresa</h1>
		</div>

		<!-- Fila registrar nueva Empresa -->
		<div class="row">
			<div class="col-xl-12 col-lg-11">
				<div class="card shadow mb-4">
					<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
						<h6 class="m-0 font-weight-bold text-primary">Datos de Nueva Empresa</h6>
					</div>
					<div class="card-body">
						<!-- Formulario para registrar nueva Empresa -->
						<form action="/Admin/adminempresas?action=registrar_empresa" method="POST">
							<div class="form-group row">
								<div class="col-md-4">
									<input type="text" class="form-control" placeholder="RUT Empresa" id="nerut" name="nerut">
									<small class="form-text text-muted">Ingrese RUT completo sin puntos ni guión</small>
								</div>
								<div class="col-md-8">
									<input type="text" class="form-control" id="nenombre" name="nenombre" placeholder="Nombre o Razón Social">
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-6">
									<input type="text" class="form-control" id="nedomicilio" name="nedomicilio" placeholder="Domicilio">
								</div>
								<div class="col-md-6">
									<input type="text" class="form-control" id="nemail" name="nemail" placeholder="Correo Electrónico">
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-12">
									<label for="exampleFormControlSelect1">Asignar Usuario a Empresa:</label>
									<%-- <select class="form-control" id="neusuario" name="neusuario">
										<!-- Iterador Usuarios para mostrar en el Option del Seelect -->
										<% while (iteradorUsuarios.hasNext()) {
										usersList = iteradorUsuarios.next();
										String usersListNombre = usersList.toString();
										%>
										<option label="<% out.write(usersListNombre); %>"> <%= usersList.getRut().toString() %></option> 
										<% } %>
									</select>  --%>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-6">
									<button type="submit" name=nuevaempresasubmit class="btn btn-outline-dark"><i class="fa fa-save"></i> Registrar Empresa</button>
								</div>
							</div>								
						</form>
						<!-- / Formulario para registrar nueva Empresa -->
					</div>
				</div>
			</div>
		</div>
		<!-- Fila registrar nueva Empresa -->

	</div>
	<!-- / Contenedor Fluid -->

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
	<a class="scroll-to-top rounded" href="#page-top"> <i class="fa fa-angle-up"></i></a>

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