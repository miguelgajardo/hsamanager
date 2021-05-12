<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="controlador.*"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="/css/style.css">

<meta charset="UTF-8">
<title>HSA Manager - Ingresar</title>
</head>
<body>
<div class="wrapper align-items-center w-100 h-100 container-fluid bg-dark text-white">
		<div class="row">
			<div class="col-md-12 col-lg-6">
				<!-- Llamar al formulario login o los errores -->
				<div class="card bg-dark border-0">

					<div class="card-header bg-dark">
					<div class="row align-items-center">
					<div class="col-md-3">
					<img src="http://hsa-auditores.cl/img/hsalogo.svg"
							alt="" height="120" class="d-inline-block align-center">
					</div>
						<div class="col-md-9">
					<h5 class="card-title">Bienvenido a HSA Manager</h5>
						<h6 class="card-subtitle mb-2 text-muted">Inicie sesión para
							gestionar empresas y trabajadores.</h6>
					</div>
					</div>
					</div>
					<div class="card-body">
						<form action="logincontrol" method="POST">
							<div class="form-group">
								<label for="inputuser">RUT</label> <input type="text"
									class="inputfield form-control" id="inputuser" name="inputuser"
									placeholder="Ingrese su RUT"> <small
									id="ejemploRut" class="form-text text-muted">Ingrese su
									RUT completo, sin puntos ni guión</small>
							</div>
							<div class=form-group>
								<label for="inputpass">Contraseña</label> 
								<input type="password"
									class="inputfield  form-control" id="inputpass" name="inputpass">
							</div>
							<input type="submit" class="btn btn-outline-light btn-lg" name="inputsubmit"
								value="Ingresar">
							
							<p>
							<%
							if (request.getAttribute("button") != null && request.getAttribute("control").equals(0)) {
								out.write("<div class=\"alert alert-danger\" role=\"alert\">");
								out.write("Los datos ingresados son incorrectos. <br>Por favor, inténtelo nuevamente.");
								out.write("</div>");
								request.removeAttribute("control");
								request.removeAttribute("button");
							}
							%>
						</p>
						</form>
						<div class="row align-items-start">
					<div class="col-sm-6">
						<button type="button" class="text-white btn btn-link">¿Problemas para acceder?</button>

					</div>
					<div class="col-sm-6">
						<button type="button" class="text-white btn btn-link">Registrar una cuenta</button>

					</div>
					</div>
					</div>
					
					
				</div>
			</div>
			<div class="col-md-6 d-xs-none d-sm-none d-lg-block">
				<img class="h-100" src="/img/" alt="" height="90%" class="d-inline-block align-center">
			</div>

		</div>
</div>
</body>
</html>