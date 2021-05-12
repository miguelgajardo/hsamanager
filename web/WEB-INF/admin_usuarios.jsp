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
List<Usuario> usuarios = UsuarioDAO.getAllUsuarios();
Iterator<Usuario> usuariosIterator = usuarios.iterator();

%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Administrar Usuarios - HSA Auditores</title>
<!-- FONT-AWESOME CSS -->
<link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
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
			<h1 class="h3 mb-0 text-gray-800">Administrar Usuarios</h1>
		</div>

		<!-- Fila Tabla de los usuarios activos -->
		<div class="row">

			<div class="col-xl-12 col-lg-9">

				<!-- Tabla Administrar Empresas -->
				<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">Usuarios Activos</h6>
				</div>
				<div class="card-body">
				<!-- Mensaje de Exito en Creación/Modificacion/Eliminación de Usuario -->
					<p>
					<%
					if (request.getAttribute("registrousuarioexitoso") != null && request.getAttribute("registrousuarioexitoso").equals(1)) {
					out.write("<div class=\"alert alert-success\" role=\"alert\">");
					out.write("El usuario " + " <strong>" + request.getAttribute("nombrenuevousuario") + " </strong>" + "fue registrado exitosamente.");
					out.write("</div>");
					request.removeAttribute("registrousuarioexitoso");
					request.removeAttribute("nunombre");
					}
					%>
					<%
					if (request.getAttribute("registrousuarioexitoso") != null && request.getAttribute("registrousuarioexitoso").equals(0)) {
					out.write("<div class=\"alert alert-danger\" role=\"alert\">");
                    out.write("El usuario " + " <strong>" + request.getAttribute("nombrenuevousuario") + " </strong>" + "no pudo ser registrado." + "<br>" + request.getAttribute("mensaje"));
                    out.write("</div>");
                    request.removeAttribute("registrousuarioexitoso");
                    request.removeAttribute("nunombre");
                    request.removeAttribute("mensaje");
					}
					%>
					<%
					if (request.getAttribute("updateusuarioexitoso") != null && request.getAttribute("updateusuarioexitoso").equals(1)) {
					out.write("<div class=\"alert alert-success\" role=\"alert\">");
					out.write("El usuario " + " <strong>" + request.getAttribute("modifusuarionombre") + " </strong>" + "ha sido actualizado exitosamente.");
					out.write("</div>");
					request.removeAttribute("updateusuarioexitoso");
					request.removeAttribute("modifusuarionombre");

					}
					%>
					<%
					if (request.getAttribute("eliminarusuarioexitoso") != null && request.getAttribute("eliminarusuarioexitoso").equals(1)) {
					out.write("<div class=\"alert alert-success\" role=\"alert\">");
					out.write("El usuario " + " <strong>" + request.getAttribute("nombreusuarioeliminado") + " </strong>" + "ha sido eliminado exitosamente.");
					out.write("</div>");
					request.removeAttribute("eliminarusuarioexitoso");
					request.removeAttribute("nombreusuarioeliminado");

					}
					%>
					</p>
					<!-- Mensaje de Exito en Creación/Modificacion/Eliminación de Usuario -->
					
					<div class="table-responsive">
					<table class="table table-bordered" id="dataTable">
						<thead>
							<tr>
								<th>Nombre</th>
								<th>RUT</th>
								<th>Email</th>
								<th>Gestionar</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>Empresa</th>
								<th>RUT</th>
								<th>Email</th>
								<th>Gestionar</th>
							</tr>
						</tfoot>
						<tbody>
						<!-- Usuarios Iterator -->
						<%
						Usuario usuario = null;
						while (usuariosIterator.hasNext()) {
						usuario = usuariosIterator.next();
						%>	
							<tr>
								<td><%= usuario.getNombre() %> <%= usuario.getApellido() %></td>
								<td><%= usuario.getRut() %></td>
								<td><%= usuario.getEmail() %></td>
								<td>
									<a class="btn btn-secondary" 
									href="/Admin/adminempresas?go_to=modificar_usuario&rut_usuario=<%= usuario.getRut() %>"><i class="fas fa-edit"></i>
									</a>
									<a class="btn btn-secondary" 
									href="/Admin/adminempresas?go_to=eliminar_usuario&rut_usuario=<%= usuario.getRut() %>"><i class="fas fa-trash-alt"></i>
									</a>
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
		<!-- / Fila Tabla de los usuarios activos -->
		
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
	<a class="scroll-to-top rounded" href="#page-top"><i class="fas fa-angle-up"></i></a>

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