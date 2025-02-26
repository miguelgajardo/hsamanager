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
import java.util.Properties;

@WebServlet(name = "Contacto",
        description = "Controlador de Tareas de Contacto",
        urlPatterns = {"/Contacto/contacto"})
        //urlPatterns = {"Contacto/contacto"})

/**
 * Servlet que permite controlar el envío de correos electrónicos para fines de contacto corporativo,
 * haciendo uso de la clase Mail que incorpora la API JavaMail.
 * Recibe una solicitud y entrega una respuesta mediante método POST.
 *
 * @author Miguel Gajardo M. - Desarrollador Full Stack Java.
 */
public class Contacto extends HttpServlet {

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");
//        if (request.getParameter("send") != null && request.getParameter("send").equals("enviar")) {
//           String receptorMail = "contacto@infograf.cl";
//           String nombre = request.getParameter("nombre");
//           String email = request.getParameter("email");
//           String telefono = request.getParameter("ciudad");
//           String mensaje = request.getParameter("mensaje");
//           String sending = "HSA Manager - Nuevo mensaje desde sitio web" +
//                   "Nombre: " + nombre +
//                   "E-mail: " + email +
//                   "Teléfono: " + telefono +
//                   "Mensaje: " + mensaje;
//            Mail.sendMail(receptorMail ,sending);
//            request.setAttribute("envioexitoso", 1);
//            response.sendRedirect("URL para redigir ");
//        }
//    }
}