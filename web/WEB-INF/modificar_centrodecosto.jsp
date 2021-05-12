<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="controlador.*"%>
<%@page import="modelo.Trabajador"%>
<%@page import="modelo.Empresa"%>
<%@ page import="modelo.CentroCosto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@page session="true"%>
<%
	int cc_id = Integer.parseInt(request.getAttribute("idcc").toString());
	CentroCosto centroDeCosto = CentroCostoDAO.getCentroDeCostoById(cc_id);

	List<Empresa> empresas = EmpresaDAO.getAllEmpresas();
	Iterator<Empresa> empresasIterator = empresas.iterator();
	Empresa empresasList = null;
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Editar <%= centroDeCosto.getNombre() %> - HSA Auditores</title>
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

	<!-- Contenedor Fluid -->
	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="d-sm-flex align-items-center justify-content-between mb-4">
			<h1 class="h3 mb-0 text-gray-800">Editar Datos del Centro de Costo</h1>
		</div>

		<!-- Fila Formulario Datos del Centro de Costo -->
		<div class="row">
			<div class="col-xl-12 col-lg-11">
				<div class="card shadow mb-4">
				<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h6 class="m-0 font-weight-bold text-primary">Información del Centro de Costo</h6>
				</div>
				
				<div class="card-body">
					<!-- Formulario para modificar datos del Centro de Costo -->
					<form action="/Admin/admincc?action=actualizar_centrodecosto" method="post">
						<div class="form-group row">
							<div class="col-md-3">
								<label for="id_codigocc">ID Centro de Costo:</label>
								<input type="text" class="form-control" id="id_codigocc" name="id_codigocc" value="<%= centroDeCosto.getId() %>" readonly>
							</div>
							<div class="col-md-4">
								<label for="modifnombrecc">Nombre Centro de Costo/Obra:</label>
								<input type="text" id="modifnombrecc" class="form-control" name="modifnombrecc" value="<%= centroDeCosto.getNombre() %>">
							</div>
							<div class="col-md-5">
								<label for="modifnombrecc">Empresa Asignada:</label>
								<input type="text" id="empresacc" class="form-control" name="empresacc" value="<%= centroDeCosto.getEmpresaNombre() %>" readonly>
								<h6>Reasignar Empresa del Centro de Costo:</h6>
								<select class="form-control" name="reasignempresa">
									<!-- Iterador Empresas para mostrar en el Option del Select -->
									<%
										while (empresasIterator.hasNext()) {
											empresasList = empresasIterator.next();
											String empresaLabel = empresasList.toString();
									%>
									<option label="<% out.write(empresaLabel); %>"> <%= empresasList.getRut().toString() %></option>
									<% } %>
								</select>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-6">
								<button type="submit" name=updatecentrodecosto class="btn btn-secondary"><i class="fas fa-save"></i> Guardar Cambios</button>
							</div>
						</div>
					</form>
					<!-- Formulario para modificar datos del Centro de Costo -->
				</div>
				</div>
			</div>
		</div>
		<!-- Fila Formulario Datos del Centro de Costo -->
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


</body>

</html>