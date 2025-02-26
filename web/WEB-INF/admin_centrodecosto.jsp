<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controlador.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page import="modelo.*" %>
<%@ page import="java.sql.SQLException" %>
<%
    String admincentrocostoid = request.getAttribute("idcc").toString();
    int idcc = Integer.parseInt(admincentrocostoid);
    CentroCosto cc = null;
    try {
        cc = CentroCostoDAO.getCentroDeCostoById(idcc);
    } catch (SQLException | ClassNotFoundException throwables) {
        throwables.printStackTrace();
    }
    List<Remuneracion> remuneracionescc = null;
    try {
        remuneracionescc = RemuneracionDAO.getAllRemuneracionesByCc(idcc);
    } catch (SQLException | ClassNotFoundException throwables) {
        throwables.printStackTrace();
    }
    assert remuneracionescc != null;
    Iterator<Remuneracion> iteradorRemCc = remuneracionescc.iterator();
    Remuneracion remuneracion;
    assert cc != null;%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Administrar <%= cc.getNombre() %> - HSA Auditores</title>
    <!-- FONT-AWESOME CSS -->
    <link href="${pageContext.request.contextPath}/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css">
    <!-- GOOGLE FONTS CSS -->
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- ADMIN PANEL CSS -->
    <link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- DATA TABLES CSS -->
    <link href="${pageContext.request.contextPath}/vendor/datatables/dataTables.bootstrap4.min.css"
          rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
</head>
<!-- HTML Body -->
<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Menú Lateral -->
    <jsp:include page="admin_nav.html" />

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Admin Top Nav -->
            <jsp:include page="admin_top.jsp" />

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

                <!-- Fila Cargar Remuneración -->
                <div class="row">

                    <div class="col-xl-12 col-lg-12 col-">

                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Cargar Remuneración en <%= cc.getNombre() %></h6>
                            </div>

                            <!-- Card Body -->
                            <div class="card-body">
                                <p>
                                    <% if (request.getAttribute("buttonregistroremuneracion") != null && request.getAttribute("registroremuneracionexitosa").equals(1)) {
                                        out.write("<div class=\"alert alert-success\" role=\"alert\">");
                                        out.write("<strong> Carga Exitosa de Remuneración" + "</strong>" + " <strong>Periodo: " + request.getAttribute("periodocargado").toString() + " </strong>");
                                        out.write("</div>");
                                        request.removeAttribute("buttonregistroremuneracion");
                                        request.removeAttribute("registroremuneracionexitosa");
                                    }
                                    %>
                                    <% if (request.getAttribute("remuneracionexiste") != null && request.getAttribute("remuneracionexiste").equals(1)) {
                                        out.write("<div class=\"alert alert-warning\" role=\"alert\">");
                                        out.write("Ya existe una remuneración para el periodo " + " <strong>" + request.getAttribute("periodo").toString() + ".</strong>" + " Elimine la remuneración existente para rectificar.");
                                        out.write("</div>");
                                        request.removeAttribute("remuneracionexiste");
                                        request.removeAttribute("periodo");
                                    }
                                    %>
                                    <% if (request.getAttribute("eliminarremuneracionexito") != null && request.getAttribute("eliminarremuneracionexito").equals(1)) {
                                        out.write("<div class=\"alert alert-warning\" role=\"alert\">");
                                        out.write("La remuneración para el periodo " + " <strong>" + request.getAttribute("periodoeliminado").toString() + "</strong>" + " fue eliminada exitosamente.");
                                        out.write("</div>");
                                        request.removeAttribute("eliminarremuneracionexito");
                                        request.removeAttribute("periodoeliminado");
                                    }
                                    %>
                                </p>

                                <!-- Formulario para cargar documento -->
                                <form class="form-inline" action="${pageContext.request.contextPath}/Upload/uploader?action=cargar_remuneracion_cc" method="POST" enctype="multipart/form-data">

                                    <div class="form-group">
                                        <input class="form-control" type="hidden" name="idcc" value="<%= cc.getId() %>">
                                    </div>

                                    <div class="col-md-4">
                                        <h6><label for="dateper">Periodo</label></h6>
                                        <div class="input-group mb-3">
                                            <input id="dateper" class="form-control" type="month" min="2021-01" value="" name="dateper">
                                        </div>
                                    </div>

                                    <div class="col-md-5">
                                        <h6>Documento</h6>
                                        <div class="input-group mb-3">
                                            <div class="custom-file">
                                                <input type="file" class="custom-file-input" id="file" name="fileImagen">
                                                <label class="custom-file-label" for="file">Seleccionar</label>
                                            </div>
                                        </div>
                                    </div>

                                    <script>
                                        $('#file').on('change',function(){
                                            //get the file name
                                            const fileName = $(this).val();
                                            //replace the "Choose a file" label
                                            $(this).next('.custom-file-label').html(fileName);
                                        })
                                    </script>

                                    <div class="col-md-3">
                                        <h6>&nbsp;</h6>
                                        <div class="input-group mb-3">
                                            <button class="btn btn-outline-secondary" name="remsubmit" value="Guardar">Cargar Remuneración</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.Fila Cargar Remuneración -->

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
                                            <th>ID Cód.</th>
                                            <th>Nombre</th>
                                            <th style="display: none">Date</th>
                                            <th>Periodo</th>
                                            <th style="display: none">Documento</th>
                                            <th>Remuneración</th>
                                        </tr>
                                        </thead>

                                        <tfoot>
                                        <tr>
                                            <th>ID Cód.</th>
                                            <th>Nombre</th>
                                            <th style="display: none">Date</th>
                                            <th>Periodo</th>
                                            <th style="display: none">Documento</th>
                                            <th>Remuneración</th>
                                        </tr>
                                        </tfoot>

                                        <tbody>
                                        <!-- RemuneracionesCc Iterator -->
                                        <% while (iteradorRemCc.hasNext()) {
                                            remuneracion = iteradorRemCc.next();
                                        %>
                                        <tr>
                                            <td><%= cc.getId() %></td>
                                            <td><%= cc.getNombre() %></td>
                                            <td style="display: none"><%= remuneracion.getPeriodo() %></td>
                                            <td><%= remuneracion.mostrarPeriodo() %></td>
                                            <td style="display: none"><%= remuneracion.getDocumento() %></td>
                                            <td>
                                                <a class="btn btn-warning" href="<%= remuneracion.getUrl() %>"><i class="fa fa-cloud-download-alt"></i> Ver</a>
                                                <a class="btn btn-secondary deletebtn" href="#deleterem" data-toggle="modal"
                                                   data-target="#deleterem"><i class="fa fa-trash-alt"></i> Eliminar</a>
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
                <!-- / Fila Tabla de Remuneraciones por Trabajador -->

            </div>
            <!-- Contenedor Fluid -->
        </div>
        <!-- / Main Content -->

        <!-- Footer -->

        <jsp:include page="footer.jsp" />


        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top"> <i class="fa fa-angle-up"></i></a>

<!-- Logout Modal-->

<jsp:include page="logout_modal.jsp" />

<!-- Modal Eliminar Remuneración de Centro de Costo. -->
<div class="modal fade" id="deleterem" tabindex="-1" role="dialog" aria-labelledby="deleteremlabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteremlabel">Está seguro que desea eliminar la información?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"></span></button>
            </div>
            <div class="modal-body">Esta acción eliminará los registros y el documento de forma permanente.<br>
                Haga clic en confirmar para eliminar.</div>
            <div class="modal-footer">
                <form action="${pageContext.request.contextPath}/Admin/admincc?action=eliminar_remuneracion" method="POST">
                    <input type="hidden" name="cc_id" id="cc_id">
                    <input type="hidden" name="periodo" id="periodo">
                    <input type="hidden" name="documento" id="documento">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal" ><i class="fa fa-chevron-circle-left"></i> Regresar</button>
                    <button type="submit" class="btn btn-danger" name="deletedata" ><i class="far fa-check-circle"></i> Eliminar Definitivamente</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="${pageContext.request.contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="${pageContext.request.contextPath}/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="${pageContext.request.contextPath}/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="${pageContext.request.contextPath}/js/demo/datatables-demo.js"></script>
<script>
    $(document).ready(function () {
        $('.deletebtn').on('click', function () {
            $('#deleterem').modal('show');
            var $tr = $(this).closest('tr');
            const data = $tr.children('td').map(function () {
                return $(this).text();
            }).get();
            console.log(data);
              $('#cc_id').val(data[0]);
              $('#periodo').val(data[2]);
              $('#documento').val(data[4]);
        });
    });
</script>
</body>
</html>