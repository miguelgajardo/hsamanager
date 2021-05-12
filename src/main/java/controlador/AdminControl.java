/*
 * Copyright <2020> <Miguel Gajardo M.>
 * Por la presente se concede permiso, libre de cargos, a cualquier persona que obtenga una copia de este software y de los archivos de documentación asociados (el "Software"), a utilizar el Software sin restricción, incluyendo sin limitación los derechos a usar, copiar, modificar, fusionar, publicar, distribuir, sublicenciar, y/o vender copias del Software, y a permitir a las personas a las que se les proporcione el Software a hacer lo mismo, sujeto a las siguientes condiciones:
 *
 * El aviso de copyright anterior y este aviso de permiso se incluirán en todas las copias o partes sustanciales del Software.
 *
 * EL SOFTWARE SE PROPORCIONA "COMO ESTA", SIN GARANTÍA DE NINGÚN TIPO, EXPRESA O IMPLÍCITA, INCLUYENDO PERO NO LIMITADO A GARANTÍAS DE COMERCIALIZACIÓN, IDONEIDAD PARA UN PROPÓSITO PARTICULAR E INCUMPLIMIENTO. EN NINGÚN CASO LOS AUTORES O PROPIETARIOS DE LOS DERECHOS DE AUTOR SERÁN RESPONSABLES DE NINGUNA RECLAMACIÓN, DAÑOS U OTRAS RESPONSABILIDADES, YA SEA EN UNA ACCIÓN DE CONTRATO, AGRAVIO O CUALQUIER OTRO MOTIVO, DERIVADAS DE, FUERA DE O EN CONEXIÓN CON EL SOFTWARE O SU USO U OTRO TIPO DE ACCIONES EN EL SOFTWARE.
 */

package controlador;

import modelo.CentroCosto;
import modelo.Empresa;
import modelo.Trabajador;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Implementa el control que sirve a todas las operaciones admitidas a nivel de Usuario Administrador,
 * con excepción a la carga de documentos. Referencia al uso de todas las clases del Modelo a través
 * de los métodos GET y POST, y el uso de Switch (Java 1.8) para la redirección o el destino de los datos.
 *
 * @author Miguel Gajardo M. - Desarrollador Full Stack Java.
 */

@WebServlet(name = "AdminControl",
        description = "Controlador de Tareas de Administrador",
        urlPatterns = {"/Admin/admincontrol", "/Admin/adminempresas", "/Admin/admintrabajadores", "/Admin/adminusuarios", "/Admin/admincc", "/Admin/contacto"})

public class AdminControl extends HttpServlet {

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        if (session.getAttribute("iam").equals("Administrador") && request.getParameter("go_to") != null) {
            String goTo = request.getParameter("go_to");
            switch (goTo) {

                case "administrar_usuarios":
                    request.getRequestDispatcher("/WEB-INF/admin_usuarios.jsp").forward(request, response);
                    break;

                case "administrar_trabajadores":
                    request.getRequestDispatcher("/WEB-INF/admin_trabajadores.jsp").forward(request, response);
                    break;

                case "administrar_usuario":
                    request.getRequestDispatcher("/WEB-INF/admin_usuario.jsp").forward(request, response);
                    break;

                case "administrar_centrosdecosto":
                    request.getRequestDispatcher("/WEB-INF/admin_centrosdecosto.jsp").forward(request, response);
                    break;

                case "admin_centrodecosto":
                    request.setAttribute("idcc", request.getParameter("id_cc"));
                    request.getRequestDispatcher("/WEB-INF/admin_centrodecosto.jsp").forward(request, response);
                    break;

                case "asignar_usuario":
                    request.setAttribute("rutusuarioasignar", request.getParameter("rut_usuario"));
                    request.getRequestDispatcher("/WEB-INF/asignar_usuario.jsp").forward(request, response);
                    break;

                case "modificar_usuario":
                    request.setAttribute("rutusuariomodificar", request.getParameter("rut_usuario"));
                    request.getRequestDispatcher("/WEB-INF/modificar_usuario.jsp").forward(request, response);
                    break;

                case "editar_centrodecosto":
                    request.setAttribute("idcc", request.getParameter("id_cc"));
                    request.getRequestDispatcher("/WEB-INF/modificar_centrodecosto.jsp").forward(request, response);
                    break;


                case "nueva_empresa":
                    request.getRequestDispatcher("/WEB-INF/nueva_empresa.jsp").forward(request, response);
                    break;

                case "nuevo_usuario":
                    request.getRequestDispatcher("/WEB-INF/nuevo_usuario.jsp").forward(request, response);
                    break;

                case "modificar_empresa":
                    request.setAttribute("rutempresamodificar", request.getParameter("rut_empresa"));
                    request.getRequestDispatcher("/WEB-INF/modificar_empresa.jsp").forward(request, response);
                    break;

                case "eliminar_empresa":
                    request.setAttribute("rutempresaeliminar", request.getParameter("rut_empresa"));
                    request.getRequestDispatcher("/WEB-INF/eliminar_empresa.jsp").forward(request, response);
                    break;

                case "administrar_empresa":
                    request.setAttribute("rutadministrar", request.getParameter("rut_empresa"));
                    request.getRequestDispatcher("/WEB-INF/admin_empresa.jsp").forward(request, response);
                    break;

                case "nuevo_trabajador":
                    request.setAttribute("empresanuevotrabajador", request.getParameter("rut_empresa"));
                    request.getRequestDispatcher("/WEB-INF/nuevo_trabajador.jsp").forward(request, response);
                    break;

                case "administrar_trabajador":
                    request.setAttribute("adminruttrabajador", request.getParameter("rut_trabajador"));
                    request.getRequestDispatcher("/WEB-INF/admin_trabajador.jsp").forward(request, response);
                    break;

                case "cargar_remuneraciones":
                    request.getRequestDispatcher("/WEB-INF/cargar_remuneracion.jsp").forward(request, response);
                    break;

                case "modificar_trabajador":
                    request.setAttribute("ruttrabajadormodificar", request.getParameter("rut_trabajador"));
                    request.getRequestDispatcher("/WEB-INF/modificar_trabajador.jsp").forward(request, response);
                    break;

                case "eliminar_trabajador":
                    request.setAttribute("ruttrabajadoreliminar", request.getParameter("rut_trabajador"));
                    request.getRequestDispatcher("/WEB-INF/eliminar_trabajador.jsp").forward(request, response);
                    break;

                case "eliminar_usuario":
                    request.setAttribute("rutusuarioeliminar", request.getParameter("rut_usuario"));
                    request.getRequestDispatcher("/WEB-INF/eliminar_usuario.jsp").forward(request, response);
                    break;

                case "eliminar_centrodecosto":
                    request.setAttribute("idcc", request.getParameter("id_cc"));
                    request.getRequestDispatcher("/WEB-INF/eliminar_centrodecosto.jsp").forward(request, response);
                    break;

                case "nuevo_cc":
                    request.getRequestDispatcher("/WEB-INF/nuevo_cc.jsp").forward(request, response);
                    break;

                default:
                    System.out.print("Default Go To");
                    break;

            }
        } else {
            response.sendRedirect("/Usuario");
        }

    }

    /**
     * Registra, actualiza o elimina datos en la instancia CloudSQL y retorna atributos con sus valores.
     * Detecta errores en Base de Datos y asigna un mensaje a los atributos devueltos al cliente.
     *
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if (session.getAttribute("iam").equals("Administrador") && request.getParameter("action") != null) {
            String action = request.getParameter("action");
            switch (action) {

                //CREATE

                case "registrar_usuario":
                    String nombreUsuario = request.getParameter("nunombre") + " " + request.getParameter("nuapellido");
                    Usuario usuario = new Usuario();
                    usuario.setNombre(request.getParameter("nunombre"));
                    usuario.setApellido(request.getParameter("nuapellido"));
                    usuario.setRut(request.getParameter("nurut"));
                    usuario.setEmail(request.getParameter("numail"));
                    usuario.setCredentials(request.getParameter("nupass"));
                    usuario.setControlAcceso(2);
                    try {
                        UsuarioDAO.registrarNuevoUsuario(usuario);
                    } catch (Exception e) {
                        if (e instanceof SQLException) {
                            System.out.println(((SQLException) e).getErrorCode());
                            System.out.println(((SQLException) e).getSQLState());
                            System.out.println("Error al registrar usuario");
                            String mensaje;
                            String sqlError = Integer.valueOf(((SQLException) e).getErrorCode()).toString();
                            System.out.println(sqlError);
                            if (sqlError.equals("1062")) {
                                mensaje = "Ya existe un usuario con el rut ingresado.";
                            } else {
                                mensaje = "Intente nuevamente.";
                            }
                            request.setAttribute("mensaje", mensaje);
                            request.setAttribute("registrousuarioexitoso", 0);
                            request.setAttribute("nombrenuevousuario", nombreUsuario);
                            request.getRequestDispatcher("/WEB-INF/admin_usuarios.jsp").forward(request, response);
                            break;
                        } else if (e instanceof ClassNotFoundException) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Registro exitoso del usuario");
                    request.setAttribute("registrousuarioexitoso", 1);
                    request.setAttribute("nombrenuevousuario", nombreUsuario);
                    request.getRequestDispatcher("/WEB-INF/admin_usuarios.jsp").forward(request, response);
                    break;

                case "registrar_empresa":
                    Empresa empresa = new Empresa();
                    empresa.setRazonSocial(request.getParameter("nenombre"));
                    empresa.setRut(request.getParameter("nerut"));
                    empresa.setDomicilio(request.getParameter("nedomicilio"));
                    empresa.setEmail(request.getParameter("nemail"));
                    try {
                        EmpresaDAO.setNuevaEmpresa(empresa);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Registro Exitoso de Empresa");
                    request.setAttribute("registroempresaexitoso", 1);
                    request.setAttribute("nombrenuevaempresa", request.getParameter("nenombre"));
                    request.getRequestDispatcher("/WEB-INF/admin_principal.jsp").forward(request, response);
                    break;

                case "registrar_trabajador":
                    Trabajador trabajador = new Trabajador();
                    String nombreTrabajadorNuevo = request.getParameter("ntnombre") + " " + request.getParameter("ntapellido");
                    String empresaTrabajador = request.getParameter("empresa_trabajador");
                    trabajador.setNombre(request.getParameter("ntnombre"));
                    trabajador.setApellido(request.getParameter("ntapellido"));
                    trabajador.setRut(request.getParameter("ntrut"));
                    trabajador.setEmail(request.getParameter("ntmail"));
                    trabajador.setEmpresa(empresaTrabajador);
                    try {
                        System.out.print(empresaTrabajador);
                        TrabajadorDAO.setNuevoTrabajador(trabajador);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.print("Registro Exitoso de Trabajador");
                    request.setAttribute("registrotrabajadorexitoso", 1);
                    request.setAttribute("nuevotrabajadornombre", nombreTrabajadorNuevo);
                    request.getRequestDispatcher("/WEB-INF/admin_trabajadores.jsp").forward(request, response);
                    break;

                case "registrar_cc":
                    CentroCosto centroCosto = new CentroCosto();
                    String nombreCentroCosto = request.getParameter("nccnombre");
                    String empresaCentroCosto = request.getParameter("nccrut");
                    centroCosto.setNombre(nombreCentroCosto);
                    centroCosto.setEmpresa(empresaCentroCosto);
                    try {
                        System.out.print(empresaCentroCosto);
                        CentroCostoDAO.registrarCentroDeCosto(centroCosto);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.print("Registro Exitoso de Centro de Costo");
                    request.setAttribute("registroccexitoso", 1);
                    request.setAttribute("nuevoccnombre", nombreCentroCosto);
                    request.getRequestDispatcher("/WEB-INF/admin_centrosdecosto.jsp").forward(request, response);
                    break;

                // END CREATE


                //UPDATE

                case "actualizar_usuario":
                    usuario = new Usuario();
                    String rutActual = request.getParameter("rutactual");
                    String rutUsuarioModificar = request.getParameter("rutpormodificar");
                    String nombreUsuarioModificar = request.getParameter("modifnombre");
                    String apellidoUsuarioModificar = request.getParameter("modifapellido");
                    String emailUsuarioModificar = request.getParameter("modifemail");
                    String nombreCompletoUsuario = nombreUsuarioModificar + " " + apellidoUsuarioModificar;
                    usuario.setNombre(nombreUsuarioModificar);
                    usuario.setApellido(apellidoUsuarioModificar);
                    usuario.setRut(rutUsuarioModificar);
                    usuario.setEmail(emailUsuarioModificar);
                    try {
                        UsuarioDAO.modificarUsuario(usuario, rutActual);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.print("Modificación Exitosa de Usuario");
                    request.setAttribute("updateusuarioexitoso", 1);
                    request.setAttribute("modifusuarionombre", nombreCompletoUsuario);
                    request.getRequestDispatcher("/WEB-INF/admin_usuarios.jsp").forward(request, response);
                    break;

                case "actualizar_empresa":
                    empresa = new Empresa();
                    String rutEmpresaModificar = request.getParameter("rutactual");
                    String rut = request.getParameter("rutpormodificar");
                    String razonsocial = request.getParameter("modifnombre");
                    String domicilio = request.getParameter("modifdomicilio");
                    String email = request.getParameter("modifemail");
                    empresa.setRut(rut);
                    empresa.setRazonSocial(razonsocial);
                    empresa.setDomicilio(domicilio);
                    empresa.setEmail(email);
                    try {
                        EmpresaDAO.modificarEmpresa(empresa, rutEmpresaModificar);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Modificación Exitosa de Empresa");
                    request.setAttribute("updateempresaexitoso", 1);
                    request.setAttribute("modifempresanombre", request.getParameter("modifnombre"));
                    request.getRequestDispatcher("/WEB-INF/admin_principal.jsp").forward(request, response);
                    break;

                case "actualizar_empresa_su":
                    empresa = new Empresa();
                    String rutEmpresaModificarSu = request.getParameter("rutactual");
                    String rutSu = request.getParameter("rutpormodificar");
                    String razonsocialSu = request.getParameter("modifnombre");
                    String domicilioSu = request.getParameter("modifdomicilio");
                    String emailSu = request.getParameter("modifemail");
                    String usuarioEmpresaSu = request.getParameter("usuario");
                    empresa.setRut(rutSu);
                    empresa.setRazonSocial(razonsocialSu);
                    empresa.setDomicilio(domicilioSu);
                    empresa.setEmail(emailSu);
                    empresa.setUsuario(usuarioEmpresaSu);
                    try {
                        EmpresaDAO.modificarEmpresaSu(empresa, rutEmpresaModificarSu);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println(usuarioEmpresaSu);
                    System.out.println("Modificación Exitosa de Empresa");
                    request.setAttribute("updateempresaexitoso", 1);
                    request.setAttribute("modifempresanombre", request.getParameter("modifnombre"));
                    request.getRequestDispatcher("/WEB-INF/admin_principal.jsp").forward(request, response);
                    break;

                case "actualizar_trabajador":
                    trabajador = new Trabajador();
                    String rutactual = request.getParameter("rutactual");
                    String rutmodificar = request.getParameter("rutpormodificar");
                    String nombre = request.getParameter("modifnombre");
                    String apellido = request.getParameter("modifapellido");
                    String emailmodif = request.getParameter("modifemail");
                    String nombreCompleto = nombre + " " + apellido;
                    trabajador.setRut(rutmodificar);
                    trabajador.setNombre(nombre);
                    trabajador.setApellido(apellido);
                    trabajador.setEmail(emailmodif);
                    try {
                        TrabajadorDAO.modificarTrabajador(trabajador, rutactual);

                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.print("Modificación Exitosa de Trabajador");
                    request.setAttribute("updatetrabajadorexitoso", 1);
                    request.setAttribute("modiftrabajadornombre", nombreCompleto);
                    request.setAttribute("rutadministrar", request.getParameter("rut_empresa"));
                    request.getRequestDispatcher("/WEB-INF/admin_empresa.jsp").forward(request, response);
                    break;

                case "actualizar_centrodecosto":
                    CentroCosto centroDeCosto = new CentroCosto();
                    String id_codigocc = request.getParameter("id_codigocc");
                    String modifnombrecc = request.getParameter("modifnombrecc");
                    String reasignempresa = request.getParameter("reasignempresa");
                    centroDeCosto.setEmpresa(reasignempresa);
                    centroDeCosto.setNombre(modifnombrecc);
                    try {
                        CentroCostoDAO.updateCentroDeCosto(centroDeCosto, Integer.parseInt(id_codigocc));

                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.print("Modificación Exitosa de Centro de Costo");
                    request.setAttribute("updateccexitoso", 1);
                    request.setAttribute("modifccnombre", modifnombrecc);
                    request.getRequestDispatcher("/WEB-INF/admin_centrosdecosto.jsp").forward(request, response);
                    break;

                // END UPDATE

                //DELETE

                case "eliminar_usuario":
                    String rutUsuarioEliminar = request.getParameter("rutporeliminar");
                    String nombreUsuarioEliminado = request.getParameter("nombre_usuario");
                    try {
                        UsuarioDAO.eliminarUsuariorByRut(rutUsuarioEliminar);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.print("Usuario Eliminado Exitosamente");
                    request.setAttribute("eliminarusuarioexitoso", 1);
                    request.setAttribute("nombreusuarioeliminado", nombreUsuarioEliminado);
                    request.getRequestDispatcher("/WEB-INF/admin_usuarios.jsp").forward(request, response);
                    break;


                case "eliminar_empresa":
                    String rutEliminar = request.getParameter("rutporeliminar");
                    String nombreEmpresaEliminada = request.getParameter("razonsocial");
                    try {
                        EmpresaDAO.eliminarEmpresaByRut(rutEliminar);
                        System.out.print("Eliminación de Empresa Exitosa");
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    request.setAttribute("eliminacionempresaexitosa", 1);
                    request.setAttribute("nombreempresaeliminada", nombreEmpresaEliminada);
                    request.getRequestDispatcher("/WEB-INF/admin_principal.jsp").forward(request, response);
                    break;


                case "eliminar_trabajador":
                    String rutTrabajador = request.getParameter("rutporeliminar");
                    String nombreTrabajador = request.getParameter("nombre_trabajador");
                    try {
                        TrabajadorDAO.eliminarTrabajadorByRut(rutTrabajador);

                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.print("Trabajador Eliminado Exitosamente");
                    request.setAttribute("eliminartrabajadorexitoso", 1);
                    request.setAttribute("nombretrabajadoreliminado", nombreTrabajador);
                    request.setAttribute("rutadministrar", request.getParameter("rut_empresa"));
                    request.getRequestDispatcher("/WEB-INF/admin_empresa.jsp").forward(request, response);
                    break;

                case "eliminar_remuneracion":
                    DateTimeFormatter formatterCc = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate periodoCc;
                    String idRemuneracion = request.getParameter("cc_id");
                    int id_cc = Integer.valueOf(idRemuneracion);
                    System.out.println("ID CC" + idRemuneracion);
                    String periodoLocal = request.getParameter("periodo");
                    System.out.println(periodoLocal);
                    String documento = request.getParameter("documento");
                    System.out.println("Documento: " + documento);
                    periodoCc = LocalDate.parse(periodoLocal, formatterCc);
                    System.out.println("Periodo:" + periodoCc);
                    Date fechasql = Date.valueOf(periodoCc);
                    CloudStorage cloud = new CloudStorage();
                    try {
                        RemuneracionDAO.deleteRemuneracion(fechasql, id_cc);
                        CloudStorage.deleteObject("hsamanager", "hsamanager_uploads", documento);
                    } catch (Exception e) {
                        System.out.println("Error al eliminar");
                        e.printStackTrace();
                    }
                    System.out.print("Remuneración Eliminada Exitosamente");
                    request.setAttribute("eliminarremuneracionexito", 1);
                    request.setAttribute("periodoeliminado", periodoLocal);
                    request.setAttribute("idcc", id_cc);
                    request.getRequestDispatcher("/WEB-INF/admin_centrodecosto.jsp").forward(request, response);
                    break;

                case "eliminar_centrodecosto":
                    String id_centro = request.getParameter("idcc");
                    int idcc = Integer.valueOf(id_centro);
                    String nombrecentroeliminado = request.getParameter("nombrecc");
                    try {
                        CentroCostoDAO.deleteCentroDeCosto(idcc);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.print("Centro de Costo Eliminado Exitosamente");
                    request.setAttribute("eliminarccexitoso", 1);
                    request.setAttribute("nombrecceliminado", nombrecentroeliminado);
                    request.getRequestDispatcher("/WEB-INF/admin_centrosdecosto.jsp").forward(request, response);
                    break;

                // END DELETE

                case "enviar":
                    System.out.println("Mensaje Enviado");
                    response.sendRedirect("https://hsa-auditores.cl");

                default:
                    System.out.print("Error al Procesar");
                    break;
            }

        } else {
            response.sendRedirect("/Usuario");
        }
    }
}
	