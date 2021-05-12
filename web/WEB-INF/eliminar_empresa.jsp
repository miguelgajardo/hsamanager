<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controlador.*"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.Empresa"%>
<%@page import="modelo.Trabajador"%>
<%@page import="modelo.Remuneracion"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page session="true"%>
<%
String rutEmpresaEliminar = request.getAttribute("rutempresaeliminar").toString();
Empresa empresa = EmpresaDAO.getEmpresa(rutEmpresaEliminar);
List<Trabajador> trabajadoresDeLaEmpresa = TrabajadorDAO.getAllTrabajadoresByEmpresa(rutEmpresaEliminar);
String trabajadoresEmpresa = Integer.valueOf(trabajadoresDeLaEmpresa.size()).toString();
int remuneracionesEmpresa = 0;
for (Trabajador trabajador : trabajadoresDeLaEmpresa) {
	String rut = trabajador.getRut();
	List<Remuneracion> listaDeRemuneracionesPorTrabajador = RemuneracionDAO.getAllRemuneracionesByRut(rut);
	remuneracionesEmpresa += listaDeRemuneracionesPorTrabajador.size();
}
String remuneraciones = Integer.valueOf(remuneracionesEmpresa).toString();
%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Eliminar <%=empresa.getRazonSocial() %> - HSA Auditores</title>
<!-- Custom fonts for this template-->
<link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
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
				
	<!-- Begin Page Content -->
	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="d-sm-flex align-items-center justify-content-between mb-4">
			<h1 class="h3 mb-0 text-gray-800">Eliminar Empresa</h1>
		</div>

		<!-- Fila Datos del Trabajador a Eliminar -->
		<div class="row">
			<div class="col-xl-12 col-lg-11">
			<div class="card shadow mb-4">
			<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
				<h6 class="m-0 font-weight-bold text-primary">Datos de Empresa a Eliminar</h6>
			</div>
			<div class="card-body">
			
				<!-- Mensaje de Advertencia Eliminar Trabajador -->
				<% out.write("<div class=\"alert alert-danger\" role=\"alert\">");
				out.write("<strong> Atención! Usted va a eliminar la siguiente empresa: " + "</strong>" + " <strong>" 
				+ empresa.getRazonSocial() + " </strong>"
				+ "<br>Esta acción <strong>eliminará</strong> todos los trabajadores y remuneraciones asociados.");
				out.write("</div>");
				%>
				
				<!-- Formulario para eliminar empresa -->
				<form action="/Admin/adminempresas?action=eliminar_empresa" method="POST">
					<div class="form-group row">
						<div class="col-md-4">
							<label for="rutporeliminar">RUT Empresa:</label>
							<input type="text" class="form-control" id="rutporeliminar" name="rutporeliminar" value="<%= empresa.getRut() %>" readonly>
						</div>
						<div class="col-md-8">
							<label for="razonsocial">Nombre o Razón Social:</label>
							<input type="text" id="razonsocial" name="razonsocial" class="form-control" value="<%= empresa.getRazonSocial() %>" readonly>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-md-7">
							<label for="domicilio">Domicilio:</label>
							<input type="text" class="form-control" id="domicilio" placeholder="<%= empresa.getDomicilio() %>" readonly>			
						</div>
						<div class="col-md-5">
							<label for="email">Correo Electrónico:</label>
							<input type="text" class="form-control" id="email" placeholder="<%= empresa.getEmail() %>" readonly>
						</div>	
					</div>
					<div class="form-group row">
						<div class="col-md-7">
							<label for="trabajadores">Trabajadores Registrados:</label>
							<input type="text" class="form-control" id="trabajadores" placeholder="<% out.write(trabajadoresEmpresa); %>" readonly>			
						</div>
						<div class="col-md-5">
							<label for="remuneraciones">Remuneraciones Totales:</label>
							<input type="text" class="form-control" id="remuneraciones" placeholder="<% out.write(remuneraciones); %>" readonly>
						</div>	
					</div>

					<div class="form-group row">
						<div class="col-md-6">
							<button type="submit" name=eliminarempresasubmit class="btn btn-danger"><i class="fas fa-trash-alt"></i> Eliminar Empresa</button>
						</div>
					</div>
				</form>
				<!-- Formulario para eliminar empresa -->
			</div>
			</div>
			</div>
		</div>
		<!-- Fila Datos de empresa a Eliminar -->
		

	</div>
	<!-- / Contenedor Fluid -->
	
	<!-- Footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- End of Footer -->

	</div>
	<!-- End of Main Content -->

	
</div>
<!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<jsp:include page="logout_modal.jsp"></jsp:include>
	

	<!-- Bootstrap core JavaScript-->
	<script src="/vendor/jquery/jquery.min.js"></script>
	<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="/js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script src="/vendor/chart.js/Chart.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="/js/demo/chart-area-demo.js"></script>
	<script src="/js/demo/chart-pie-demo.js"></script>

</body>

</html>