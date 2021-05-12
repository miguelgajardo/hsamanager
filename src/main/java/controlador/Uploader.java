/*
 * Copyright <2020> <Miguel Gajardo M.>
 * Por la presente se concede permiso, libre de cargos, a cualquier persona que obtenga una copia de este software y de los archivos de documentación asociados (el "Software"), a utilizar el Software sin restricción, incluyendo sin limitación los derechos a usar, copiar, modificar, fusionar, publicar, distribuir, sublicenciar, y/o vender copias del Software, y a permitir a las personas a las que se les proporcione el Software a hacer lo mismo, sujeto a las siguientes condiciones:
 *
 * El aviso de copyright anterior y este aviso de permiso se incluirán en todas las copias o partes sustanciales del Software.
 *
 * EL SOFTWARE SE PROPORCIONA "COMO ESTA", SIN GARANTÍA DE NINGÚN TIPO, EXPRESA O IMPLÍCITA, INCLUYENDO PERO NO LIMITADO A GARANTÍAS DE COMERCIALIZACIÓN, IDONEIDAD PARA UN PROPÓSITO PARTICULAR E INCUMPLIMIENTO. EN NINGÚN CASO LOS AUTORES O PROPIETARIOS DE LOS DERECHOS DE AUTOR SERÁN RESPONSABLES DE NINGUNA RECLAMACIÓN, DAÑOS U OTRAS RESPONSABILIDADES, YA SEA EN UNA ACCIÓN DE CONTRATO, AGRAVIO O CUALQUIER OTRO MOTIVO, DERIVADAS DE, FUERA DE O EN CONEXIÓN CON EL SOFTWARE O SU USO U OTRO TIPO DE ACCIONES EN EL SOFTWARE.
 */

package controlador;

import modelo.Remuneracion;
import com.google.cloud.storage.BlobInfo;
import com.google.common.base.Strings;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementa el control sobre la carga de documentos y su registro como Remuneracion en la tabla
 * remuneraciones de la instancia CloudSQL y en el bucket correspondiente de CloudStorage.
 *
 * @author Miguel Gajardo M. - Desarrollador Full Stack Java.
 */
@WebServlet(name = "Uploader",
        description = "Uploader",
        urlPatterns = {"/Upload/uploader"}
)
public class Uploader extends HttpServlet {

    public Uploader() {
        super();
    }

    /** Permite mediante el uso del parámetro solcitado por el cliente, y los datos registrados
     * en el formulario HTML, capturar los datos para el registro y carga de una remuneración (documento).
     * Utiliza la clase CloudStorage para controlar la carga de documentos en CloudStorage siempre
     * que el nivel de acceso corresponda al de Administrador.
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        assert ServletFileUpload.isMultipartContent(request);
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        if (session.getAttribute("iam").equals("Administrador") && request.getParameter("action") != null) {
            String action = request.getParameter("action");
            switch (action) {
                case "cargar_remuneracion":
                    Remuneracion remuneracion = new Remuneracion();
                    String newFileUrl;
                    String rutTrabajador;
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate periodo;
                    Map<String, String> params = new HashMap<>();
                    CloudStorage cloud = new CloudStorage();
                    try {
                        FileItemIterator iter = new ServletFileUpload().getItemIterator(request);
                        while (iter.hasNext()) {
                            FileItemStream item = iter.next();
                            if (item.isFormField()) {
                                params.put(item.getFieldName(), Streams.asString(item.openStream()));
                            } else if (!Strings.isNullOrEmpty(item.getName())) {
                                String periodoLocal = params.get("dateper") + "-01";
                                System.out.println(periodoLocal);
                                periodo = LocalDate.parse(periodoLocal, formatter);
                                System.out.println("Periodo a JAVA:" + periodo);
                                rutTrabajador = params.get("ruttrabajador");
                                Date fechasql = Date.valueOf(periodo);
                                boolean verify = RemuneracionDAO.verificarRemuneracionExists(params.get("ruttrabajador"), fechasql);
                                if (verify) {
                                    System.out.println("La remuneracion ya existe");
                                    request.setAttribute("remuneracionexiste", 1);
                                    request.setAttribute("periodo", params.get("dateper"));
                                } else {
                                    System.out.println("La remuneracion no existe, se está registrando una nueva");
                                    newFileUrl = cloud.uploadFile(params.get("ruttrabajador"), periodoLocal, item, "bucket_nombre");
                                    System.out.println("Nombre del Item: " + item.getName());
                                    remuneracion.setTrabajador(rutTrabajador);
                                    remuneracion.setPeriodo(periodo);
                                    remuneracion.setUrl(newFileUrl);
                                    remuneracion.setDocumento(item.getName());
                                    try {
                                        RemuneracionDAO.setRemuneracion(remuneracion);

                                    } catch (Exception em) {
                                        em.printStackTrace();
                                    }
                                    request.setAttribute("registroremuneracionexitosa", 1);
                                    request.setAttribute("buttonregistroremuneracion", "remsubmit");
                                    request.setAttribute("periodocargado", params.get("dateper"));
                                }
                                request.setAttribute("adminruttrabajador", rutTrabajador);
                                request.getRequestDispatcher("/WEB-INF/admin_trabajador.jsp").forward(request, response);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } break;

                case "cargar_remuneracion_cc":
                    Remuneracion remuneracioncc = new Remuneracion();
                    String newFileUrlCc;
                    String documento;
                    int centroCosto;
                    DateTimeFormatter formatterCc = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate periodoCc;
                    Map<String, String> paramsCc = new HashMap<>();
                    CloudStorage cloudCc = new CloudStorage();
                    try {
                        FileItemIterator iter = new ServletFileUpload().getItemIterator(request);
                        while (iter.hasNext()) {
                            FileItemStream item = iter.next();
                            if (item.isFormField()) {
                                paramsCc.put(item.getFieldName(), Streams.asString(item.openStream()));
                            } else if (!Strings.isNullOrEmpty(item.getName())) {
                                String periodoLocal = paramsCc.get("dateper") + "-01";
                                System.out.println(periodoLocal);
                                periodoCc = LocalDate.parse(periodoLocal, formatterCc);
                                System.out.println("Periodo a JAVA:" + periodoCc);
                                centroCosto = Integer.valueOf(paramsCc.get("idcc"));
                                Date fechasql = Date.valueOf(periodoCc);
                                boolean verify = RemuneracionDAO.verificarRemuneracionExistsEnCc(Integer.valueOf(paramsCc.get("idcc")), fechasql);
                                if (verify) {
                                    System.out.println("La remuneracion ya existe");
                                    request.setAttribute("remuneracionexiste", 1);
                                    request.setAttribute("periodo", paramsCc.get("dateper"));
                                } else {
                                    System.out.println("La remuneracion no existe, se está registrando una nueva");
                                    BlobInfo blobInfo = cloudCc.uploadFileCc(Integer.valueOf(paramsCc.get("idcc")), periodoLocal, item, "bucket_nombre");
                                    newFileUrlCc = blobInfo.getMediaLink();
                                    System.out.println("URL: " + newFileUrlCc);
                                    documento = blobInfo.getName();
                                    System.out.println("Blob Name:" + documento);
                                    remuneracioncc.setCentroCosto(centroCosto);
                                    remuneracioncc.setPeriodo(periodoCc);
                                    remuneracioncc.setUrl(newFileUrlCc);
                                    remuneracioncc.setDocumento(documento);
                                    try {
                                        RemuneracionDAO.setRemuneracionCc(remuneracioncc);

                                    } catch (Exception em) {
                                        em.printStackTrace();
                                    }
                                    request.setAttribute("registroremuneracionexitosa", 1);
                                    request.setAttribute("buttonregistroremuneracion", "remsubmit");
                                    request.setAttribute("periodocargado", paramsCc.get("dateper"));
                                }
                                request.setAttribute("idcc", centroCosto);
                                request.getRequestDispatcher("/WEB-INF/admin_centrodecosto.jsp").forward(request, response);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } break;

                }
            }
            }
    }