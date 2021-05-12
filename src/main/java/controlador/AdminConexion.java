/*
 * Copyright <2020> <Miguel Gajardo M.>
 * Por la presente se concede permiso, libre de cargos, a cualquier persona que obtenga una copia de este software y de los archivos de documentación asociados (el "Software"), a utilizar el Software sin restricción, incluyendo sin limitación los derechos a usar, copiar, modificar, fusionar, publicar, distribuir, sublicenciar, y/o vender copias del Software, y a permitir a las personas a las que se les proporcione el Software a hacer lo mismo, sujeto a las siguientes condiciones:
 *
 * El aviso de copyright anterior y este aviso de permiso se incluirán en todas las copias o partes sustanciales del Software.
 *
 * EL SOFTWARE SE PROPORCIONA "COMO ESTA", SIN GARANTÍA DE NINGÚN TIPO, EXPRESA O IMPLÍCITA, INCLUYENDO PERO NO LIMITADO A GARANTÍAS DE COMERCIALIZACIÓN, IDONEIDAD PARA UN PROPÓSITO PARTICULAR E INCUMPLIMIENTO. EN NINGÚN CASO LOS AUTORES O PROPIETARIOS DE LOS DERECHOS DE AUTOR SERÁN RESPONSABLES DE NINGUNA RECLAMACIÓN, DAÑOS U OTRAS RESPONSABILIDADES, YA SEA EN UNA ACCIÓN DE CONTRATO, AGRAVIO O CUALQUIER OTRO MOTIVO, DERIVADAS DE, FUERA DE O EN CONEXIÓN CON EL SOFTWARE O SU USO U OTRO TIPO DE ACCIONES EN EL SOFTWARE.
 */

package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Proporciona al sistema en su totalidad, las características,
 * datos y drivers necesarios para la conexión con la base de datos, que corre
 * como instancia CloudSQL en los servicios de Google Cloud.
 * Es invocada en cada Objeto de Acceso a Datos, en los que se requiera consultar,
 * insertar, actualizar y eliminar datos, haciendo uso de las clases del Modelo,
 * tales como Empresa, Trabajador, Usuario y Remuneración.
 *
 * @author Miguel Gajardo M. - Desarrollador Full Stack Java.
 */
public class AdminConexion {
    /**
     * Método protegido. Incluye constantes con los datos
     * de proyecto, instancia y base de datos, proporcionados por Google Cloud.
     * Carga automáticamente el Driver necesario para la conexión.
     * Utilice en Objetos de Acceso a Datos ubicados en el paquete Controlador.
     *
     * @return Java SQL Connection
     * @throws ClassNotFoundException
     */
    protected static Connection conexion() throws ClassNotFoundException {

        Connection conectar = null;

        //Información del Proyecto, Instancia y Zona

        final String idProyecto = "";

        final String idInstancia = "";

        final String zonaRegion = "";

        //Información de la base de datos MySQL.

        final String dbUsuario = "";

        final String dbCredencial = "";

        final String jdbcUrl = "jdbc:mysql://google/" + idProyecto + "?cloudSqlInstance=" + idProyecto + ":" + zonaRegion + ":" + idInstancia + "&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=" + dbUsuario + "&password=" + dbCredencial + "";

        try {
            conectar = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conectar;
    }
}