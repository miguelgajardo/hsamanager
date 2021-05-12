<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controlador.*"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.Empresa"%>
<%@page import="modelo.Trabajador"%>
<%@page import="modelo.Remuneracion"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page session="true"%>
<%
Trabajador trabajador = (Trabajador)request.getAttribute("trabajador");
List<Remuneracion> remuneracionesDelTrabajador = RemuneracionDAO.getAllRemuneracionesByRut(trabajador.getRut());
Iterator<Remuneracion> remuneracionesIterator = remuneracionesDelTrabajador.iterator();
Remuneracion remuneracion = null;
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Administrar <%= trabajador.getNombre() + " " + trabajador.getApellido() %> - HSA Auditores</title>
<!-- FONT-AWESOME CSS -->
<link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<!-- GOOGLE FONTS CSS -->
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

<!-- ADMIN PANEL CSS -->
<link href="/css/sb-admin-2.min.css" rel="stylesheet">

<!-- DATA TABLES CSS -->
<link href="/vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">       
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
</head>
<!-- HTML Body -->
<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

<!-- Menú Lateral -->
<jsp:include page="usuario_nav.html"></jsp:include>

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
			<h4 class="h3 mb-0 text-gray-800">Administrar Trabajador</h4>
		</div>

		<!-- Fila Datos del Trabajador -->
		<div class="row">

			<!-- Area Chart -->
			<div class="col-xl-12 col-lg-11">
				<div class="card shadow mb-4">
					<!-- Card Header - Dropdown -->
					<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
						<h6 class="m-0 font-weight-bold text-primary">Trabajador(a): <%= trabajador.getNombre() + " " + trabajador.getApellido() %></h6>
					</div>
				
					<!-- Card Body -->
					<div class="card-body">

						<!-- Formulario Cargar Remuneración por Trabajador -->
						<form action="" method="post">
							<div class="form-group row">
							<div class="col-md-4">
								<label for="rutporeliminar">RUT:</label> 
								<input type="text" class="form-control" id="rutporeliminar" name="rutporeliminar" 
								value="<%= trabajador.getRut()%>" readonly>
							</div>
							
							<div class="col-md-8">
								<label for="deleteempresanombre">Nombre Completo:</label> <input type="text" id="deleteempresanombre"
								class="form-control" name="deleteempresanombre"
						 		value="<%= trabajador.getNombre() %> <%= trabajador.getApellido() %>" readonly>
							</div>
							</div>
										
							<div class="form-group row">
							
							<div class="col-md-6">
								<label for="deleteempresadomicilio">Empresa:</label> 
								<input type="text" class="form-control" id="deleteempresadomicilio" name="deleteempresadomicilio" 
								placeholder="<%= trabajador.getEmpresaNombre()%>" readonly>
							</div>
											
							<div class="col-md-6">
								<label for="deleteempresaemail">Correo Electrónico:</label>
								<input type="text" class="form-control" id="deleteempresaemail" name="deleteempresaemail"
								placeholder="<%=trabajador.getEmail()%>" readonly>
							</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- /Fila de Datos Trabajador -->
			
		<!-- Fila Tabla de Remuneraciones por Trabajador -->
		<div class="row">

		<!-- Content Column -->
		<div class="col-xl-12 col-lg-12">

			<!-- DataTales Example -->
			<div class="card shadow mb-4">
				<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">Administrar Remuneraciones de Trabajador(a)</h6>
				</div>
				<div class="card-body">
				
					<!-- Tabla Remuneraciones -->
					<div class="table-responsive">
					<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
						<thead>
							<tr>
							<th>Nombre</th>
							<th>RUT</th>
							<th>Periodo</th>
							<th>Remuneración</th>
							</tr>
						</thead>
						
						<tfoot>
							<tr>
							<th>Nombre</th>
							<th>RUT</th>
							<th>Periodo</th>
							<th>Remuneración</th>
							</tr>
						</tfoot>

						<tbody>
							<!-- Remuneraciones Iterator -->
							<% while (remuneracionesIterator.hasNext()) {
							remuneracion = remuneracionesIterator.next();
							%>
							<tr>
							<td><%= trabajador.getNombre() + "  " + trabajador.getApellido() %></td>
							<td><%= remuneracion.getTrabajador() %></td>
							<td><%= remuneracion.getPeriodo() %></td>
							<td><a class="btn btn-warning" href="<%= remuneracion.getUrl() %>">Ver</a></td>
							</tr>
							<% } %>
						</tbody>
					</table>
					</div>
					<!-- /Tabla Remuneraciones -->
				</div>
			</div>
		</div>
		</div>
		<!-- / Fila Tabla de Remuneraciones por Trabajador -->
		
	</div>
	<!-- Contenedor Fluid -->
	</div>
	<!-- / Main Content -->

	<!-- Footer -->
	
	<jsp:include page="footer.jsp"></jsp:include>
	
	
	<!-- End of Footer -->

</div>
<!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i class="fas fa-angle-up"></i></a>

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
	<script src="/vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="/vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="/js/demo/datatables-demo.js"></script>
</body>

</html>