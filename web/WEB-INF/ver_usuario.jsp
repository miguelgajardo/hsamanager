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
session = request.getSession();
String rutusuario = session.getAttribute("rutUsuario").toString();
Usuario usuario = UsuarioDAO.getUsuario(rutusuario);
List<Empresa> empresasDelUsuario = EmpresaDAO.getAllEmpresasDelUsuario(rutusuario);
Iterator<Empresa> iteradorEmpresasDelUsuario = empresasDelUsuario.iterator();
Empresa empresaDelUsuario = null;
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Ver usuario <%= usuario.getNombre() + " " + usuario.getApellido() %> - HSA Auditores</title>
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
			<h4 class="h3 mb-0 text-gray-800">Visualizar Usuario Empresa</h4>
		</div>

		<!-- Fila Datos del Usuario y Empresas Administradas -->
		<div class="row">

			<!-- Area Chart -->
			<div class="col-xl-12 col-lg-11">
				<div class="card shadow mb-4">
					<!-- Card Header - Dropdown -->
					<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
						<h6 class="m-0 font-weight-bold text-primary">Datos de usuario(a) administrador(a)</h6>
					</div>
				
					<!-- Card Body -->
					<div class="card-body">

						<!-- Formulario Cargar Remuneración por Trabajador -->
						<form action="" method="post">
							<div class="form-group row">
							<div class="col-md-4">
								<label for="rutusuario">RUT:</label> 
								<input type="text" class="form-control" id="rutusuario" value="<%= usuario.getRut()%>" readonly>
							</div>
							
							<div class="col-md-8">
								<label for="nombreusuario">Nombre Completo:</label> 
								<input type="text" id="nombreusuario" class="form-control" value="<%= usuario.getNombre() %> <%= usuario.getApellido() %>" readonly>
							</div>
							</div>
										
							<div class="form-group row">
							
							<div class="col-md-3">
								<label for="numeroempresas">N&deg;. Empresas Administradas: </label> 
								<input type="text" class="form-control" id="numeroempresas" placeholder="<%= empresasDelUsuario.size() %>" readonly>
							</div>
											
							<div class="col-md-9">
								<label for="usuariomail">Correo Electrónico:</label>
								<input type="text" class="form-control" id="usuariomail" placeholder="<%=usuario.getEmail()%>" readonly>
							</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- /Fila Empresas del Usuario -->
			
		<!-- Fila Tabla de Empresas del Usuario -->
		<div class="row">

		<!-- Content Column -->
		<div class="col-xl-12 col-lg-12">

			<!-- DataTales Example -->
			<div class="card shadow mb-4">
				<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">Visualizar Remuneraciones por Empresa</h6>
				</div>
				<div class="card-body">
				
					<!-- Tabla Empresas del Usuario -->
					<div class="table-responsive">
					<table class="table table-bordered" id="dataTable">
						<thead>
							<tr>
								<th>Empresa</th>
								<th>RUT</th>
								<th>Email</th>
								<th>Usuario</th>
								<th>Visualizar</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>Empresa</th>
								<th>RUT</th>
								<th>Email</th>
								<th>Usuario</th>
								<th>Visualizar</th>
							</tr>
						</tfoot>
						<tbody>
						<!-- Empresas Iterator -->
						<%
						Empresa empresa = null;
						while (iteradorEmpresasDelUsuario.hasNext()) {
						empresa = iteradorEmpresasDelUsuario.next();
						%>	
							<tr>
								<td><%= empresa.getRazonSocial() %></td>
								<td><%= empresa.getRut() %></td>
								<td><%= empresa.getEmail() %></td>
								<td><%= empresa.getUsuario() %>
								</td>
								<td>
									<a type="button" class="btn btn-warning btn-sm" href="/Usuario/remuneraciones?go_to=remuneraciones_empresa&rut_empresa=<%= empresa.getRut() %>"><i class="fa fa-file-pdf"></i> Remuneraciones</a>
								</td>
							</tr>
						<% } %>
						</tbody>
					</table>
					</div>
					<!-- / Tabla de Empresas del Usuario -->
				</div>
			</div>
		</div>
		</div>
		<!-- Fila Tabla de Empresas del Usuario -->
		
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