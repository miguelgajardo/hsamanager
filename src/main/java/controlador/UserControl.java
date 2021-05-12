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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementa el control que sirve a todas las operaciones admitidas a nivel de Usuario,
 * referencia el uso de todas las clases del Modelo a través
 * de los métodos GET y POST, y el uso de Switch (Java 1.8) para la redirección o el destino de los datos.
 *
 *  @author Miguel Gajardo M. - Desarrollador Full Stack Java.
 */
@WebServlet(name = "UserControl",
        description = "Controlador de Tareas del Usuario",
        urlPatterns = {"/Usuario/usercontrol", "/Usuario/usuarioempresas", "/Usuario/remuneraciones", "/Usuario/trabajadores", "/Usuario/centrosdecosto"}
)
public class UserControl extends HttpServlet {

    public UserControl() {
        super();
    }

    /** Redirecciona al usuario a las vistas correspondientes, dependiendo del parámetro go_to y su valor
     * capturado en la URL de la petición, siempre y cuando se trate de un usuario común.
     * Proporciona controles a la identidad del usuario y los datos que le son autorizados para
     * visualizar, permitiendo redirigir a las vistas correspondientes, o emitiendo un error si
     * por alguna razón, no le corresponde a dicho usuario el acceso a tales datos.
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        if (session.getAttribute("iam").equals("Usuario") && request.getParameter("go_to") != null) {
            String goTo = request.getParameter("go_to");
            switch (goTo) {
                case "ver_usuario":
                    request.getRequestDispatcher("/WEB-INF/ver_usuario.jsp").forward(request, response);
                    break;

                case "remuneraciones_empresa":
                    String rutEmpresa = request.getParameter("rut_empresa");
                    String rutUsuario = session.getAttribute("rutUsuario").toString();
                    Empresa controlEmpresa = null;
                    try {
                        controlEmpresa = EmpresaDAO.getEmpresa(rutEmpresa);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    assert controlEmpresa != null;
                    if (controlEmpresa.getUsuario().equals(rutUsuario)) {
                        request.setAttribute("rutEmpresa", rutEmpresa);
                        request.getRequestDispatcher("/WEB-INF/remuneraciones_empresa.jsp").forward(request, response);
                    } else {
                        System.out.print("Usted no está autorizado para visualizar los datos de esta empresa");
                    }
                    break;

                case "ver_trabajadores":
                    String rutUsuarioEmpresas = session.getAttribute("rutUsuario").toString();
                    List<Empresa> empresasDelUsuario = new ArrayList<>();
                    List<Trabajador> trabajadoresEmpresas = new ArrayList<>();
                    try {
                        empresasDelUsuario = EmpresaDAO.getAllEmpresasDelUsuario(rutUsuarioEmpresas);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    for (Empresa empresa : empresasDelUsuario) {
                        String rutEmpresaTr = empresa.getRut();
                        try {
                            trabajadoresEmpresas.addAll(TrabajadorDAO.getAllTrabajadoresByEmpresa(rutEmpresaTr));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    request.setAttribute("trabajadoresEmpresas", trabajadoresEmpresas);
                    request.getRequestDispatcher("/WEB-INF/ver_trabajadores.jsp").forward(request, response);
                    break;

                case "ver_trabajador":

                    session = request.getSession();
                    String rutTrabajador = request.getParameter("rut_trabajador");
                    String rutUsuarioEmpresa = session.getAttribute("rutUsuario").toString();
                    List<Empresa> AllempresasDelUsuario = new ArrayList<>();
                    try {
                        AllempresasDelUsuario = EmpresaDAO.getAllEmpresasDelUsuario(rutUsuarioEmpresa);
                    } catch (SQLException | ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    List<Trabajador> trabajadoresDeLaEmpresa = new ArrayList<>();

                    for (Empresa empresa : AllempresasDelUsuario) {
                        String rutEmpresas = empresa.getRut();
                        if (empresa.getUsuario().equals(rutUsuarioEmpresa)) {
                            try {
                                trabajadoresDeLaEmpresa.addAll(TrabajadorDAO.getAllTrabajadoresByEmpresa(rutEmpresas));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    Trabajador trabajador = null;
                    try {
                        trabajador = TrabajadorDAO.getTrabajador(rutTrabajador);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    assert trabajador != null;
                    boolean existeTrabajador = trabajador.containsName(trabajadoresDeLaEmpresa, rutTrabajador);
                    if (existeTrabajador) {
                        request.setAttribute("trabajador", trabajador);
                        request.getRequestDispatcher("/WEB-INF/ver_trabajador.jsp").forward(request, response);
                    } else {
                        System.out.print("No autorizado para ver al trabajador");
                    }
                    break;

                case "ver_centrodecosto":
                    session = request.getSession();
                    String idCc = request.getParameter("id_cc");
                    int centroCostoId = Integer.valueOf(idCc);
                    String rutUsuarioCc = session.getAttribute("rutUsuario").toString();
                    List<Empresa> AllempresasDelUsuarioConCc = new ArrayList<>();
                    try {
                        AllempresasDelUsuarioConCc = EmpresaDAO.getAllEmpresasDelUsuario(rutUsuarioCc);
                    } catch (SQLException | ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    List<CentroCosto> centrosDeCostoDeLaEmpresa = new ArrayList<>();

                    for (Empresa empresa : AllempresasDelUsuarioConCc) {
                        String rutEmpresas = empresa.getRut();
                        if (empresa.getUsuario().equals(rutUsuarioCc)) {
                            try {
                                centrosDeCostoDeLaEmpresa.addAll(CentroCostoDAO.getAllCentrosDeCostoByEmpresa(rutEmpresas));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    CentroCosto cc = null;
                    try {
                        cc = CentroCostoDAO.getCentroDeCostoById(centroCostoId);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    assert cc != null;
                    boolean existeCc = cc.containsName(centrosDeCostoDeLaEmpresa, centroCostoId);
                    if (existeCc) {
                        request.setAttribute("centrodecosto", cc);
                        request.getRequestDispatcher("/WEB-INF/ver_centrodecosto.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("/WEB-INF/error.jsp");
                    }
                    break;

                case "ver_centrosdecosto": {
                    String rutUsuarioCentroCosto = session.getAttribute("rutUsuario").toString();
                    List<Empresa> empresasConCentroDeCosto = new ArrayList<>();
                    List<CentroCosto> centrosDeCosto = new ArrayList<>();
                    try {
                        empresasConCentroDeCosto = EmpresaDAO.getAllEmpresasDelUsuario(rutUsuarioCentroCosto);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    for (Empresa empresa : empresasConCentroDeCosto) {
                        String rutEmpresaCc = empresa.getRut();
                        try {
                            centrosDeCosto.addAll(CentroCostoDAO.getAllCentrosDeCostoByEmpresa(rutEmpresaCc));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    request.setAttribute("centrosdecostoempresas", centrosDeCosto);
                    request.getRequestDispatcher("/WEB-INF/ver_centrosdecosto.jsp").forward(request, response);
                }
                break;

                default:
                    response.sendRedirect("/WEB-INF/error.jsp");
                    break;
            }
        } else {
            session.invalidate();
            response.sendRedirect("/Login");
        }
    }
}
