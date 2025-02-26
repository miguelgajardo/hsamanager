<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="controlador.*"%>
<%@page import="modelo.Trabajador"%>
<%@page import="modelo.Empresa"%>
<%@page session="true"%>
<%
String rutTrabajador = request.getAttribute("ruttrabajadormodificar").toString();
Trabajador trabajador = TrabajadorDAO.getTrabajadorByRut(rutTrabajador);
String rutEmpresaDelTrabajador = trabajador.getEmpresa().toString();
Empresa empresaTrabajador = EmpresaDAO.getEmpresa(rutEmpresaDelTrabajador);
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Editar <%= trabajador.getNombre() %> <%= trabajador.getApellido() %> - HSA Auditores</title>
<!-- Custom fonts for this template-->
<link href="/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
rel="stylesheet">
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
			<h1 class="h3 mb-0 text-gray-800">Editar Datos de Trabajador(a)</h1>
		</div>

		<!-- Fila Formulario Datos del Trabajador -->
		<div class="row">
			<div class="col-xl-12 col-lg-11">
				<div class="card shadow mb-4">
				<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h6 class="m-0 font-weight-bold text-primary">Información de Trabajador(a)</h6>
				</div>
				
				<div class="card-body">
					<!-- Formulario para modificar datos del trabajador -->
					<form action="/Admin/admintrabajadores?action=actualizar_trabajador" method="post">
						<div class="form-group row">
							<div class="col-md-3">
								<label for="rutpormodificar">RUT Trabajador(a):</label>
								<input type="text" class="form-control" id="rutpormodificar" name="rutpormodificar" value="<%= trabajador.getRut() %>">
								<small>RUT completo sin puntos ni guión.</small>
							</div>
							<div class="col-md-4">
								<label for="modifnombre">Nombre(s):</label>
								<input type="text" id="modifnombre" class="form-control" name="modifnombre" value="<%= trabajador.getNombre() %>">
							</div>
							<div class="col-md-5">
								<label for="modifnombre">Apellido(s):</label>
								<input type="text" id="modifapellido" class="form-control" name="modifapellido" value="<%= trabajador.getApellido() %>">
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-6">
								<label for="empresa">Empresa:</label>
								<input type="text" class="form-control" id="nombreEmpresa" name="nombreEmpresa" value="<%= empresaTrabajador.getRazonSocial() %>" readonly>
								<input type="hidden" class="form-control" name="rut_empresa" value="<%= empresaTrabajador.getRut() %>" readonly>
								
							</div>
							<div class="col-md-6">
								<label for="modifemail">Correo Electrónico:</label>
								<input type="text" class="form-control" id="modifemail" name="modifemail" value="<%= trabajador.getEmail() %>">	
								<input type="hidden" class="form-control" name="rutactual" value="<%= trabajador.getRut() %>">
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-6">
								<button type="submit" name=updatetrabajadorsubmit class="btn btn-secondary"><i class="fa fa-save"></i> Guardar Cambios</button>
							</div>
						</div>
					</form>
					<!-- Formulario para modificar datos del trabajador -->
				</div>
				</div>
			</div>
		</div>
		<!-- Fila Formulario Datos del Trabajador -->
	</div>
	<!-- / Contenedor Fluid -->
	
	<!-- Footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- End of Footer -->

	</div>
	<!-- / Main Content -->

</div>
<!-- / Content Wrapper -->

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