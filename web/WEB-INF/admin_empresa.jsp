<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controlador.*"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.Empresa"%>
<%@page import="modelo.Trabajador"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page import="modelo.CentroCosto" %>
<%@page session="true"%>
<%
String administrandoEmpresaRut = request.getAttribute("rutadministrar").toString();
Empresa emp = EmpresaDAO.getEmpresa(administrandoEmpresaRut);
List<Trabajador> trabajadores = TrabajadorDAO.getAllTrabajadoresByEmpresa(administrandoEmpresaRut);
Iterator<Trabajador> iteradorTrabajadores = trabajadores.iterator();
List<CentroCosto> centrosDeCosto = CentroCostoDAO.getAllCentrosDeCostoByEmpresa(administrandoEmpresaRut);
Iterator<CentroCosto> ccIterator = centrosDeCosto.iterator();
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title><%= emp.getRazonSocial() %> - HSA Auditores</title>
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
			<div class="h3 mb-0 text-gray-800">Administrar Empresa</div>
		</div>

		<!-- Fila Datos de la Empresa -->
		<div class="row">

			<div class="col-xl-12 col-lg-11">
				<div class="card shadow mb-4">
				<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h6 class="m-0 font-weight-bold text-primary">Administrando: <%=emp.getRazonSocial()%></h6>
				</div>
				<div class="card-body">
					<!-- Formulario Información Empresa -->
					<form>
						<div class="form-group row">
							<div class="col-md-4">
								<label for="rut">RUT Empresa:</label> 
								<input type="text" class="form-control" id="rut" value="<%=emp.getRut()%>" readonly>
							</div>
							
							<div class="col-md-8">
								<label for="nombre">Nombre o Razón Social:</label> 
								<input type="text" id="nombre" class="form-control" value="<%=emp.getRazonSocial()%>" readonly>
							</div>
						</div>
						
						<div class="form-group row">
							<div class="col-md-6">
								<label for="domicilio">Domicilio:</label> 
								<input type="text" class="form-control" id="domicilio" placeholder="<%=emp.getDomicilio()%>" readonly>
							</div>
							
							<div class="col-md-6">
								<label for="email">Correo Electrónico:</label>
								<input type="text" class="form-control" id="email" placeholder="<%=emp.getEmail()%>" readonly>
							</div>
						</div>
					</form>
					<!-- / Formulario Información Empresa -->
				</div>
				</div>
			</div>
		</div>
		<!-- / Fila Datos de la Empresa -->

		<!-- Fila Centros de Costo de la Empresa -->
		<div class="row">

			<div class="col-xl-12 col-lg-12">

				<div class="card shadow mb-4">
					<div class="card-header py-3">
						<h6 class="m-0 font-weight-bold text-primary">Administrar Centros de Obra-Costo de  <%=emp.getRazonSocial()%></h6>
					</div>
					<div class="card-body">
						<!-- Mensaje de Exito en Modificacion de Centro de Costo -->
						<p>
							<%
								if (request.getAttribute("updatetrabajadorexitoso") != null && request.getAttribute("updatetrabajadorexitoso").equals(1)) {
									out.write("<div class=\"alert alert-success\" role=\"alert\">");
									out.write("Los datos de " + " <strong>" + request.getAttribute("modiftrabajadornombre") + " </strong>" + "fueron actualizados exitosamente.");
									out.write("</div>");
									request.removeAttribute("modiftrabajadornombre");
									request.removeAttribute("updatetrabajadorexitoso");
								}
							%>
							<%
								if (request.getAttribute("eliminartrabajadorexitoso") != null && request.getAttribute("eliminartrabajadorexitoso").equals(1)) {
									out.write("<div class=\"alert alert-success\" role=\"alert\">");
									out.write("Trabajador(a) " + " <strong>" + request.getAttribute("nombretrabajadoreliminado") + " </strong>" + "ha sido eliminado exitosamente.");
									out.write("</div>");
									request.removeAttribute("nombretrabajadoreliminado");
									request.removeAttribute("eliminartrabajadorexitoso");

								}
							%>
						</p>
						<!-- Tabla Centros de Costo de la Empresa  -->
						<div class="table-responsive">
							<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
								<thead>
								<tr>
									<th>ID Obra-Centro de Costo</th>
									<th>Empresa</th>
									<th>Gestionar</th>
								</tr>
								</thead>
								<tfoot>
								<tr>
									<th>ID Obra-Centro de Costo</th>
									<th>Empresa</th>
									<th>Gestionar</th>
								</tr>
								</tfoot>
								<tbody>
								<!-- CC Iterator -->
								<%
									CentroCosto cc = null;
									while (ccIterator.hasNext()) {
										cc = ccIterator.next();
								%>
								<tr>
									<td><%= cc.getNombre() %></td>
									<td><%= cc.getEmpresaNombre() %></td>
									<td>
										<a type="button" class="btn btn-warning btn-sm" href="/Admin/admincc?go_to=admin_centrodecosto&id_cc=<%= cc.getId() %>"><i class="fas fa-file-pdf"></i> Remuneraciones</a>
										<a type="button" class="btn btn-secondary btn-sm" href="/Admin/admincc?go_to=modificar_centrodecosto&id_cc=<%= cc.getId() %>"><i class="fas fa-edit"></i></a>
										<a type="button" class="btn btn-secondary btn-sm" href="/Admin/admincc?go_to=eliminar_centrodecosto&id_cc=<%= cc.getId() %>"><i class="fas fa-trash-alt"></i></a>
									</td>
								</tr>
								<% } %>
								</tbody>
							</table>
						</div>
						<!-- Tabla Centros de Costo de la Empresa  -->
					</div>
				</div>
			</div>

			<!-- Columna Acciones -->
		</div>
		<!-- Fila Centros de Costo de la Empresa -->

		<!-- Fila Trabajadores de la Empresa -->
		<div class="row">

			<div class="col-xl-12 col-lg-12">

				<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">Administrar Trabajadores de  <%=emp.getRazonSocial()%></h6>
				</div>
				<div class="card-body">
					<!-- Mensaje de Exito en Modificacion de Trabajador -->
					<p>
					<%
					if (request.getAttribute("updatetrabajadorexitoso") != null && request.getAttribute("updatetrabajadorexitoso").equals(1)) {
					out.write("<div class=\"alert alert-success\" role=\"alert\">");
					out.write("Los datos de " + " <strong>" + request.getAttribute("modiftrabajadornombre") + " </strong>" + "fueron actualizados exitosamente.");
					out.write("</div>");
					request.removeAttribute("modiftrabajadornombre");
					request.removeAttribute("updatetrabajadorexitoso");
					}
					%>
					<%
					if (request.getAttribute("eliminartrabajadorexitoso") != null && request.getAttribute("eliminartrabajadorexitoso").equals(1)) {
					out.write("<div class=\"alert alert-success\" role=\"alert\">");
					out.write("Trabajador(a) " + " <strong>" + request.getAttribute("nombretrabajadoreliminado") + " </strong>" + "ha sido eliminado exitosamente.");
					out.write("</div>");
					request.removeAttribute("nombretrabajadoreliminado");
					request.removeAttribute("eliminartrabajadorexitoso");

					}
					%>
					</p>
				<!-- Tabla Trabajadores de la Empresa  -->
				<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
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
							<th>Nombre</th>
							<th>RUT</th>
							<th>Email</th>
							<th>Gestionar</th>							
						</tr>
					</tfoot>
					<tbody>
					<!-- Trabajadores Iterator -->
					<%
					Trabajador trabajador = null;
					while (iteradorTrabajadores.hasNext()) {
					trabajador = iteradorTrabajadores.next();
					%>
						<tr>
							<td><%=trabajador.getNombre() %> <%=trabajador.getApellido() %></td>
							<td><%=trabajador.getRut()%></td>
							<td><%=trabajador.getEmail()%></td>
							<td>
							<a type="button" class="btn btn-warning btn-sm" href="/Admin/admintrabajadores?go_to=administrar_trabajador&rut_trabajador=<%= trabajador.getRut() %>"><i class="fas fa-file-pdf"></i> Remuneraciones</a>
							<a type="button" class="btn btn-secondary btn-sm" href="/Admin/admintrabajadores?go_to=modificar_trabajador&rut_trabajador=<%= trabajador.getRut() %>"><i class="fas fa-edit"></i> Modificar</a>
							<a type="button" class="btn btn-secondary btn-sm" href="/Admin/admintrabajadores?go_to=eliminar_trabajador&rut_trabajador=<%= trabajador.getRut() %>"><i class="fas fa-trash-alt"></i> Eliminar</a>
														</td>
						</tr>
					<% } %>
					</tbody>
				</table>
				</div>
				<!-- Tabla Trabajadores de la Empresa  -->
				</div>
				</div>
			</div>

			<!-- Columna Acciones -->
		</div>
		<!-- Fila Trabajadores de la Empresa -->

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