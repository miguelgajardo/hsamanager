<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controlador.*"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.Empresa"%>
<%@page import="modelo.Trabajador"%>
<%@page import="modelo.Remuneracion"%>
<%@page import="java.time.*" %>
<%@page import="java.util.Locale" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.time.format.*"%>
<%@page session="true"%>
<% 
session = request.getSession();
List<Empresa> empresas = EmpresaDAO.getAllEmpresas();
Iterator<Empresa> iteradorEmpresas = empresas.iterator();
List<Usuario> usuarios = UsuarioDAO.getAllUsuarios();
List<Trabajador> trabajadores = TrabajadorDAO.getAllTrabajadores();
List<Remuneracion> remuneracionesMes = RemuneracionDAO.getAllRemuneracionesMes();
System.out.print(trabajadores.size());
int empresasCount = empresas.size();
int usersCount = usuarios.size();
int trabajadoresCount = trabajadores.size();
int remuneracionesMesCount = remuneracionesMes.size();
String empresasCounts = Integer.toString(empresasCount);
String usersCounts = Integer.toString(usersCount);
String trabajadoresCounts = Integer.toString(trabajadoresCount);
String remuneracionesMesCounts = Integer.toString(trabajadoresCount - remuneracionesMesCount);
Locale espanol = new Locale ( "es" , "ES" );
String currentMonth = LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es-ES")) + " de " + LocalDate.now().getYear();
String periodoActual = currentMonth.substring(0, 1).toUpperCase() + currentMonth.substring(1);
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>HSA Auditores - Panel de Administración</title>
<!-- FONT-AWESOME CSS -->
<link href="/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<!-- GOOGLE FONTS CSS -->
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<!-- ADMIN PANEL CSS -->
<link href="/css/sb-admin-2.min.css" rel="stylesheet">
<!-- DATA TABLES CSS -->
<link href="/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
</head>
<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

<!-- Menu Lateral -->
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
			<h1 class="h3 mb-0 text-gray-800">Panel de Administración</h1>
		</div>
		<div class="d-sm-flex align-items-center justify-content-between mb-4">
			<h5 class="h6 mb-0 text-secondary">Periodo: <% out.write(periodoActual); %></h5>
		</div>
		
		<!-- Fila Contadores -->
		<div class="row">
			
			<!-- Contador "Empresas Activas" -->
			<div class="col-xl-3 col-md-6 mb-4">
				<div class="card border-left-primary shadow h-100 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
							Empresas <br>Activas
							</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800">
							<% out.write(empresasCounts);%>
							</div>
						</div>
					
						<div class="col-auto">
						<i class="fa fa-fw fa-building fa-2x text-gray-300"></i>
						</div>
					</div>
				</div>
				</div>
			</div>
			<!-- / Contador "Empresas Activas" -->

			<!-- Contador "Usuarios Activos" -->
			<div class="col-xl-3 col-md-6 mb-4">
				<div class="card border-left-success shadow h-100 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div class="text-xs font-weight-bold text-success text-uppercase mb-1">
							Usuarios <br>Activos
							</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800">
							<% out.write(usersCounts);%>
							</div>
						</div>
						<div class="col-auto">
							<i class="fa fa-users fa-2x text-gray-300"></i>
						</div>
					</div>
				</div>
				</div>
			</div>
			<!-- / Contador "Usuarios Activos" -->

			<!-- Contador "Trabajadores Activos" -->
			<div class="col-xl-3 col-md-6 mb-4">
				<div class="card border-left-info shadow h-100 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div class="text-xs font-weight-bold text-info text-uppercase mb-1">
							Trabajadores <br>Activos
							</div>
							<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">
							<% out.write(trabajadoresCounts); %>
							</div>
						</div>
						<div class="col-auto">
							<i class="fa fa-fw fa-id-badge fa-2x text-gray-300"></i>
						</div>
					</div>
				</div>
				</div>
			</div>
			<!-- / Contador "Trabajadores Activos" -->
		
			<!-- Contador "Remuneraciones Pendientes" -->
			<div class="col-xl-3 col-md-6 mb-4">
				<div class="card border-left-warning shadow h-100 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
							Remuneraciones Pendientes
							</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800">
							<% out.write(remuneracionesMesCounts); %>
							</div>
						</div>
						<div class="col-auto">
							<i class="fa fa-file-pdf fa-2x text-gray-300"></i>
						</div>
					</div>
				</div>
				</div>
			</div>
			<!-- / Contador "Remuneraciones Pendientes" -->
		</div>
		<!-- / Fila Contadores -->

		<!-- Fila Tabla de las Empresas activas -->
		<div class="row">

			<div class="col-xl-12 col-lg-9">

				<!-- Tabla Administrar Empresas -->
				<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">Administrar Empresas</h6>
				</div>
				<div class="card-body">
				<!-- Mensaje de Exito en Modificacion/Eliminación de Empresa -->
					<p>
					<%
					if (request.getAttribute("updateempresaexitoso") != null && request.getAttribute("updateempresaexitoso").equals(1)) {
					out.write("<div class=\"alert alert-success\" role=\"alert\">");
					out.write("La empresa " + " <strong>" + request.getAttribute("modifempresanombre") + " </strong>" + "fue actualizada exitosamente.");
					out.write("</div>");
					request.removeAttribute("updateempresaexitoso");
					request.removeAttribute("modifempresanombre");
					}
					%>
					<%
					if (request.getAttribute("eliminacionempresaexitosa") != null && request.getAttribute("eliminacionempresaexitosa").equals(1)) {
					out.write("<div class=\"alert alert-success\" role=\"alert\">");
					out.write("La empresa " + " <strong>" + request.getAttribute("nombreempresaeliminada") + " </strong>" + "ha sido eliminada exitosamente.");
					out.write("</div>");
					request.removeAttribute("eliminacionempresaexitosa");
					request.removeAttribute("nombreempresaeliminada");

					}
					%>
					<%
					if (request.getAttribute("registroempresaexitoso") != null && request.getAttribute("registroempresaexitoso").equals(1)) {
					out.write("<div class=\"alert alert-success\" role=\"alert\">");
					out.write("La empresa " + " <strong>" + request.getAttribute("nombrenuevaempresa") + " </strong>" + "ha sido registrada exitosamente.");
					out.write("</div>");
					request.removeAttribute("registroempresaexitoso");
					request.removeAttribute("nombreempresaeliminada");

					}
					%>
					</p>
					<!-- Mensaje de Exito en Modificacion/Eliminación de Empresa -->
					
					<div class="table-responsive">
					<table class="table table-bordered" id="dataTable">
						<thead>
							<tr>
								<th>Empresa</th>
								<th>RUT</th>
								<th>Email</th>
								<th>Usuario</th>
								<th>Gestionar</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>Empresa</th>
								<th>RUT</th>
								<th>Email</th>
								<th>Usuario</th>
								<th>Gestionar</th>
							</tr>
						</tfoot>
						<tbody>
						<!-- Empresas Iterator -->
						<%
						Empresa empresa = null;
						while (iteradorEmpresas.hasNext()) {
						empresa = iteradorEmpresas.next();
						%>	
							<tr>
								<td><%= empresa.getRazonSocial() %></td>
								<td><%= empresa.getRut() %></td>
								<td><%= empresa.getEmail() %></td>
								<td><%= empresa.getUsuario() %>
								</td>
								<td>
									<a class="btn btn-warning" 
									href="/Admin/adminempresas?go_to=administrar_empresa&rut_empresa=<%= empresa.getRut() %>">
									Administrar
									</a>
									<a class="btn btn-secondary" 
									href="/Admin/adminempresas?go_to=modificar_empresa&rut_empresa=<%= empresa.getRut() %>"><i class="fa fa-edit"></i>
									Editar
									</a>
									<a class="btn btn-secondary" 
									href="/Admin/adminempresas?go_to=eliminar_empresa&rut_empresa=<%= empresa.getRut() %>"><i class="fa fa-trash-alt"></i>
									Eliminar</a>
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
		<!-- / Fila Tabla de las Empresas activas -->
		
	</div>
	<!-- Contenedor Fluid -->

	</div>
	<!-- Fin del Contenido Principal -->

	<!-- Footer -->
	
	<jsp:include page="footer.jsp"></jsp:include>
			
	<!-- / Footer -->

</div>
<!-- / Content Wrapper -->

</div>
<!-- / Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"><i class="fa fa-angle-up"></i></a>

	<!-- Logout Modal-->
	
	<jsp:include page="logout_modal.jsp"></jsp:include>

	<!-- / Logout Modal-->


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