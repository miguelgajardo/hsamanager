<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controlador.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page import="modelo.*" %>
<%@page session="true"%>
<%
    CentroCosto cc = (CentroCosto)request.getAttribute("centrodecosto");
    List<Remuneracion> remuneracionesDelCc = RemuneracionDAO.getAllRemuneracionesByCc(cc.getId());
    Iterator<Remuneracion> iteradorRemCc = remuneracionesDelCc.iterator();
    Remuneracion remuneracion = null;
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Ver <%= cc.getNombre() %> - HSA Auditores</title>
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
                    <h4 class="h3 mb-0 text-gray-800">Administrar Centro de Costo</h4>
                </div>

                <!-- Fila Datos del Centro de Costo -->
                <div class="row">

                    <!-- Area Chart -->
                    <div class="col-xl-12 col-lg-11">
                        <div class="card shadow mb-4">
                            <!-- Card Header - Dropdown -->
                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">Centro de Costo: <%= cc.getNombre() %></h6>
                            </div>

                            <!-- Card Body -->
                            <div class="card-body">

                                <!-- Fila Datos del Centro de Costo -->
                                <form>
                                    <div class="form-group row">
                                        <div class="col-md-4">
                                            <label for="idcc">Cód. Centro de Costo:</label>
                                            <input type="text" class="form-control" id="idcc" name="idcc"
                                                   value="<%= cc.getId()%>" readonly>
                                        </div>

                                        <div class="col-md-8">
                                            <label for="nombrecc">Nombre Centro de Costo:</label>
                                            <input type="text" id="nombrecc" class="form-control" value="<%= cc.getNombre() %> " readonly>
                                        </div>
                                    </div>

                                    <div class="form-group row">

                                        <div class="col-md-6">
                                            <label for="nombreempresacc">Empresa:</label>
                                            <input type="text" class="form-control" id="nombreempresacc"
                                                   placeholder="<%= cc.getEmpresaNombre()%>" readonly>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /Fila de Datos del CC -->

                <!-- Fila Tabla de Remuneraciones por Centro de Costo -->
                <div class="row">

                    <!-- Content Column -->
                    <div class="col-xl-12 col-lg-12">

                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Administrar Remuneraciones de <%= cc.getNombre() %></h6>
                            </div>
                            <div class="card-body">

                                <!-- Tabla Remuneraciones -->
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                        <tr>
                                            <th>Centro de Obra-Costo</th>
                                            <th>Periodo</th>
                                            <th>Remuneración</th>
                                        </tr>
                                        </thead>

                                        <tfoot>
                                        <tr>
                                            <th>Centro de Obra-Costo</th>
                                            <th>Periodo</th>
                                            <th>Remuneración</th>
                                        </tr>
                                        </tfoot>

                                        <tbody>
                                        <!-- RemuneracionesCc Iterator -->
                                        <% while (iteradorRemCc.hasNext()) {
                                            remuneracion = iteradorRemCc.next();
                                        %>
                                        <tr>
                                            <td><%= cc.getNombre() %></td>
                                            <td><%= remuneracion.mostrarPeriodo() %></td>
                                            <td>
                                                <a class="btn btn-warning" href="<%= remuneracion.getUrl() %>"><i class="fas fa-cloud-download-alt"></i> Ver</a>
                                            </td>
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
                <!-- / Fila Tabla de Remuneraciones por CC -->

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