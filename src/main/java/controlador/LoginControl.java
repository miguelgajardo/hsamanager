/*
 * Copyright <2020> <Miguel Gajardo M.>
 * Por la presente se concede permiso, libre de cargos, a cualquier persona que obtenga una copia de este software y de los archivos de documentación asociados (el "Software"), a utilizar el Software sin restricción, incluyendo sin limitación los derechos a usar, copiar, modificar, fusionar, publicar, distribuir, sublicenciar, y/o vender copias del Software, y a permitir a las personas a las que se les proporcione el Software a hacer lo mismo, sujeto a las siguientes condiciones:
 *
 * El aviso de copyright anterior y este aviso de permiso se incluirán en todas las copias o partes sustanciales del Software.
 *
 * EL SOFTWARE SE PROPORCIONA "COMO ESTA", SIN GARANTÍA DE NINGÚN TIPO, EXPRESA O IMPLÍCITA, INCLUYENDO PERO NO LIMITADO A GARANTÍAS DE COMERCIALIZACIÓN, IDONEIDAD PARA UN PROPÓSITO PARTICULAR E INCUMPLIMIENTO. EN NINGÚN CASO LOS AUTORES O PROPIETARIOS DE LOS DERECHOS DE AUTOR SERÁN RESPONSABLES DE NINGUNA RECLAMACIÓN, DAÑOS U OTRAS RESPONSABILIDADES, YA SEA EN UNA ACCIÓN DE CONTRATO, AGRAVIO O CUALQUIER OTRO MOTIVO, DERIVADAS DE, FUERA DE O EN CONEXIÓN CON EL SOFTWARE O SU USO U OTRO TIPO DE ACCIONES EN EL SOFTWARE.
 */

package controlador;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Implementa el control de Login estableciendo atributos para los estados de
 * administrador, usuario, sesión activa y cierre de sesión.
 * @author Miguel Gajardo M. - Desarrollador Full Stack Java.
 */
@WebServlet(name = "LoginControl",
        description = "Example Servlet Using Annotations",
        urlPatterns = {"/Login/logincontrol", "/Login/logoutcontrol", "/Login/loginform"}
)
public class LoginControl extends HttpServlet {

    public LoginControl() {
        super();
    }

    /**
     * Controla los datos capturados mediante el parámetro enviado, y deriva mediante el uso de
     * switch, al formulario de login o al cierre de sesión y Login.
     *
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (request.getParameter("action") != null) {
            String action = request.getParameter("action");
            switch (action) {
                case "ingresar":
                    request.setAttribute("mensaje", "Bienvenido");
                    request.getRequestDispatcher("/WEB-INF/login_form.jsp").forward(request, response);
                    break;

                case "salir":
                    session.setAttribute("logout", null);
                    session.invalidate();
                    request.getRequestDispatcher(request.getContextPath() + "/Login").forward(request, response);
                    break;
            }
        }
    }

    /** Controla los datos del formulario login, mediante la asignación de los valores capturados
     * a los atributos de sesión, controlando si esta corresponde a Administrador o Usuario.
     * Deriva de acuerdo al nivel de acceso a los sitios de cliente, o muestra un error de acceso.
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int controlAcceso = 0;
        try {
            controlAcceso = UsuarioDAO.controlAcceso(request.getParameter("inputuser"), request.getParameter("inputpass"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (controlAcceso) {
            case 1:
                session.setAttribute("rutUsuario", request.getParameter("inputuser"));
                session.setAttribute("iam", "Administrador");
                session.setAttribute("logout", "exist");
                response.sendRedirect(request.getContextPath() + "/Admin");
                break;
            case 2:
                session.setAttribute("rutUsuario", request.getParameter("inputuser"));
                session.setAttribute("iam", "Usuario");
                session.setAttribute("logout", "exist");
                request.getRequestDispatcher(request.getContextPath() + "/Usuario").forward(request, response);
                break;
            default:
                request.setAttribute("control", 0);
                request.setAttribute("button", "inputsubmit");
                request.getRequestDispatcher("/WEB-INF/login_form.jsp").forward(request, response);
                break;
        }
    }
}