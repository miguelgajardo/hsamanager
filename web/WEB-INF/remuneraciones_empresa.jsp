<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controlador.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@ page import="modelo.*" %>
<%@ page import="java.sql.SQLException" %>
<%@page session="true"%>
<%
String rutEmpresa = request.getAttribute("rutEmpresa").toString();
Empresa empresa = EmpresaDAO.getEmpresa(rutEmpresa);
List<Trabajador> trabajadoresEmpresa = TrabajadorDAO.getAllTrabajadoresByEmpresa(rutEmpresa);
List<Remuneracion> remuneracionesEmpresa = new ArrayList<>();
for (Trabajador trabajador : trabajadoresEmpresa) {
	String rutTrabajador = trabajador.getRut();
	remuneracionesEmpresa.addAll(RemuneracionDAO.getAllRemuneracionesByRut(rutTrabajador));
}
Iterator<Remuneracion> remuneracionesEmpresaIterator = remuneracionesEmpresa.iterator();
Remuneracion remuneracion = null;

	String rutUsuarioCentroCosto = session.getAttribute("rutUsuario").toString();
	List<Empresa> empresasConCentroDeCosto = new ArrayList<>();
	List<CentroCosto> centrosDeCosto = new ArrayList<>();
	try {
		empresasConCentroDeCosto = EmpresaDAO.getAllEmpresasDelUsuario(rutUsuarioCentroCosto);
	} catch (SQLException | ClassNotFoundException e) {
		e.printStackTrace();
	}
	for (Empresa em : empresasConCentroDeCosto) {
		String rutEmpresaCc = em.getRut();
		try {
			centrosDeCosto.addAll(CentroCostoDAO.getAllCentrosDeCostoByEmpresa(rutEmpresaCc));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	List<CentroCosto> centrosDeCostoEmpresa = centrosDeCosto;
	Iterator<CentroCosto> ccIterator = centrosDeCostoEmpresa.iterator();
	CentroCosto cc = null;
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Remuneraciones de <%= empresa.getRazonSocial()%> - HSA Auditores</title>
<!-- FONT-AWESOME CSS -->
<link href="/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet"
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
			<h4 class="h3 mb-0 text-gray-800">Ver Remuneraciones Empresa</h4>
		</div>

		<!-- Fila Datos del Trabajador -->
		<div class="row">

			<!-- Area Chart -->
			<div class="col-xl-12 col-lg-11">
				<div class="card shadow mb-4">
					<!-- Card Header - Dropdown -->
					<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
						<h6 class="m-0 font-weight-bold text-primary">Empresa: <%= empresa.getRazonSocial() %></h6>
					</div>
				
					<!-- Card Body -->
					<div class="card-body">

						<!-- Formulario Datos de la Empresa -->
						<form action="" method="post">
							<div class="form-group row">
							<div class="col-md-4">
								<label for="rutporeliminar">RUT:</label> 
								<input type="text" class="form-control" id="rutporeliminar" name="rutporeliminar" 
								value="<%= empresa.getRut()%>" readonly>
							</div>
							
							<div class="col-md-8">
								<label for="deleteempresanombre">Razón Social:</label> <input type="text" id="deleteempresanombre"
								class="form-control" name="deleteempresanombre"
						 		value="<%= empresa.getRazonSocial() %>" readonly>
							</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- /Fila de Datos de la Empresa -->

		<!-- Fila Tabla de los Centros de Costo activos -->
		<div class="row">

			<div class="col-xl-12 col-lg-9">

				<!-- Tabla Ver Centros de Costo -->
				<div class="card shadow mb-4">
					<div class="card-header py-3">
						<h6 class="m-0 font-weight-bold text-primary">Centros de Obra-Costo Activos</h6>
					</div>
					<div class="card-body">

						<div class="table-responsive">
							<table class="table table-bordered" id="dataTable">
								<thead>
								<tr>
									<th>Cód.</th>
									<th>Nombre Centro de Obra-Costo</th>
									<th>Empresa</th>
									<th>Gestionar</th>
								</tr>
								</thead>
								<tfoot>
								<tr>
									<th>Cód.</th>
									<th>Nombre Centro de Obra-Costo</th>
									<th>Empresa</th>
									<th>Gestionar</th>
								</tr>
								</tfoot>
								<tbody>
								<!-- CC Iterator -->
								<%
									while (ccIterator.hasNext()) {
										cc = ccIterator.next();
								%>
								<tr>
									<td><%= cc.getId() %></td>
									<td><%= cc.getNombre() %></td>
									<td><%= cc.getEmpresaNombre() %></td>
									<td>
										<a type="button" class="btn btn-warning btn-sm" href="/Usuario/centrosdecosto?go_to=ver_centrodecosto&id_cc=<%= cc.getId() %>"><i class="fa fa-file-pdf"></i> Remuneraciones</a>
									</td>
								</tr>
								<% } %>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- / Fila Tabla de los CC activos -->

		<!-- Fila Tabla de Remuneraciones por Trabajador -->
		<div class="row">

		<!-- Content Column -->
		<div class="col-xl-12 col-lg-12">

			<!-- DataTales Example -->
			<div class="card shadow mb-4">
				<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">Visualizar Remuneraciones por Trabajador</h6>
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
							<% while (remuneracionesEmpresaIterator.hasNext()) {
							remuneracion = remuneracionesEmpresaIterator.next();
							%>
							<tr>
							<td><%= remuneracion.getNombreTrabajador() + " " + remuneracion.getApellidoTrabajador() %></td>
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

	<!-- Page level plugins -->
	<script src="/vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="/vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="/js/demo/datatables-demo.js"></script>
</body>

</html>