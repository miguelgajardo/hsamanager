<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controlador.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@page import="java.util.Iterator"%>
<%@ page import="modelo.*" %>
<%@page session="true"%>
<%
    List<CentroCosto> centrosDeCostoEmpresa = (ArrayList)request.getAttribute("centrosdecostoempresas");
    Iterator<CentroCosto> ccIterator = centrosDeCostoEmpresa.iterator();
    CentroCosto cc = null;
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Centros de Obra-Costo - HSA Auditores</title>
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
                    <h1 class="h3 mb-0 text-gray-800">Ver Centros de Obra-Costo</h1>
                </div>

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
                                                <a type="button" class="btn btn-warning btn-sm" href="/Usuario/centrosdecosto?go_to=ver_centrodecosto&id_cc=<%= cc.getId() %>"><i class="fas fa-file-pdf"></i> Remuneraciones</a>
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