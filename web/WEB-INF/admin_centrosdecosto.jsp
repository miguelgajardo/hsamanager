<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controlador.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page import="modelo.*" %>
<%@page session="true"%>
<%
    List<CentroCosto> centrosDeCosto = CentroCostoDAO.getAllCentrosDeCosto();
    Iterator<CentroCosto> ccIterator = centrosDeCosto.iterator();

%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Administrar Centros de Costo - HSA Auditores</title>
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
                    <h1 class="h3 mb-0 text-gray-800">Administrar Centros de Costo</h1>
                </div>

                <!-- Fila Tabla de los Centros de Costo -->
                <div class="row">

                    <div class="col-xl-12 col-lg-9">

                        <!-- Tabla Administrar Centros de Costo -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Centros de Costo Activos</h6>
                            </div>
                            <div class="card-body">
                                <!-- Mensaje de Exito en Creación/Modificacion/Eliminación de CC-->
                                <p>
                                    <%
                                        if (request.getAttribute("registroccexitoso") != null && request.getAttribute("registroccexitoso").equals(1)) {
                                            out.write("<div class=\"alert alert-success\" role=\"alert\">");
                                            out.write("El Centro de Costo-Obra " + " <strong>" + request.getAttribute("nuevoccnombre") + " </strong>" + "fue registrado exitosamente.");
                                            out.write("</div>");
                                            request.removeAttribute("registroccexitoso");
                                            request.removeAttribute("nuevoccnombre");
                                        }
                                    %>
                                    <%
                                        if (request.getAttribute("eliminarccexitoso") != null && request.getAttribute("eliminarccexitoso").equals(1)) {
                                            out.write("<div class=\"alert alert-success\" role=\"alert\">");
                                            out.write("El Centro de Costo-Obra " + " <strong>" + request.getAttribute("nombrecceliminado") + " </strong>" + "fue eliminado exitosamente.");
                                            out.write("</div>");
                                            request.removeAttribute("eliminarccexitoso");
                                            request.removeAttribute("nombrecceliminado");
                                        }
                                    %>
                                    <%
                                        if (request.getAttribute("updateccexitoso") != null && request.getAttribute("updateccexitoso").equals(1)) {
                                            out.write("<div class=\"alert alert-success\" role=\"alert\">");
                                            out.write("El Centro de Costo-Obra " + " <strong>" + request.getAttribute("modifccnombre") + " </strong>" + "fue actualizado exitosamente.");
                                            out.write("</div>");
                                            request.removeAttribute("updateccexitoso");
                                            request.removeAttribute("modifccnombre");
                                        }
                                    %>
                                </p>
                                <!-- Mensaje de Exito en Creación/Modificacion/Eliminación de CC -->

                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable">
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
                                        <!-- cc Iterator -->
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
                                                <a type="button" class="btn btn-secondary btn-sm" href="/Admin/admincc?go_to=editar_centrodecosto&id_cc=<%= cc.getId() %>"><i class="fas fa-edit"></i></a>
                                                <a type="button" class="btn btn-secondary btn-sm" href="/Admin/admincc?go_to=eliminar_centrodecosto&id_cc=<%= cc.getId() %>"><i class="fas fa-trash-alt"></i></a>
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
                <!-- / Fila Tabla de los centros de costo -->

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