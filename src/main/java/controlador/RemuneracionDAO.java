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

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Capa de acceso a los datos contenidos en la tabla remuneraciones de la instancia CloudSQL.
 * Contiene las funciones generales para invocar, registrar, actualizar y eliminar datos en la tabla.
 *
 * @author Miguel Gajardo M. - Desarrollador Full Stack Java.
 */
public class RemuneracionDAO {
    /**
     * Registra una nueva Remuneración en la tabla remuneraciones de la instancia CloudSQL.
     *
     * @param remuneracion Recibe como parámetro un objeto del tipo Remuneracion.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static void setRemuneracion(Remuneracion remuneracion) throws SQLException, ClassNotFoundException {
        Connection conectar = AdminConexion.conexion();
        String insertRem = "INSERT INTO remuneraciones (trabajador, periodo, adjunto, documento) values (? , ?, ?, ?)";
        PreparedStatement ps = conectar.prepareStatement(insertRem);
        ps.setString(1, remuneracion.getTrabajador());
        ps.setDate(2, Date.valueOf(remuneracion.getPeriodo()));
        ps.setString(3, remuneracion.getUrl());
        ps.setString(4, remuneracion.getDocumento());
        ps.execute();
        ps.close();
        conectar.close();
    }

    /**
     * Registra una nueva Remuneración en la tabla remuneraciones de la instancia CloudSQL.
     *
     * @param remuneracion Recibe como parámetro un objeto del tipo Remuneracion.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static void setRemuneracionCc(Remuneracion remuneracion) throws SQLException, ClassNotFoundException {
        Connection conectar = AdminConexion.conexion();
        String insertRem = "INSERT INTO remuneracionescc (centrodecosto, periodo, adjunto, documento) values (?, ?, ?, ?)";
        PreparedStatement ps = conectar.prepareStatement(insertRem);
        ps.setInt(1, remuneracion.getCentroCosto());
        ps.setDate(2, Date.valueOf(remuneracion.getPeriodo()));
        ps.setString(3, remuneracion.getUrl());
        ps.setString(4, remuneracion.getDocumento());
        ps.execute();
        ps.close();
        conectar.close();
    }

    /**
     * Permite enlistar todas las remuneraciones correspondientes a un sólo trabajador(rut), registradas
     * en la tabla remuneraciones de la instancia CloudSQL.
     *
     * @param rutTrabajador Recibe como parámetro un String correspondiente al rabajador(rut) de la tabla remuneraciones.
     * @return List<Remuneracion> Retorna una lista de objetos del tipo Remuneracion.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static List<Remuneracion> getAllRemuneracionesByRut(String rutTrabajador) throws SQLException, ClassNotFoundException {
        ArrayList<Remuneracion> remuneraciones = new ArrayList<>();
        String getAllRemuneracionesByTrabajador = "SELECT * FROM remuneraciones rm JOIN trabajadores tr ON (rm.trabajador = tr.rut) WHERE tr.rut ='" + rutTrabajador + "'";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(getAllRemuneracionesByTrabajador);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Remuneracion rem = new Remuneracion();
            rem.setTrabajador(rs.getString("trabajador"));
            rem.setPeriodo(rs.getDate("periodo").toLocalDate());
            rem.setUrl(rs.getString("adjunto"));
            rem.setNombreTrabajador(rs.getString("nombre"));
            rem.setApellidoTrabajador(rs.getString("apellido"));
            remuneraciones.add(rem);
        }
        ps.close();
        rs.close();
        conectar.close();
        return remuneraciones;
    }

    /**
     * Permite enlistar todas las remuneraciones correspondientes a un sólo Centro de Costo(idCc), registradas
     * en la tabla remuneracionescc de la instancia CloudSQL.
     *
     * @param idCc Recibe como parámetro un int correspondiente al centrodecosto(id) de la tabla remuneracionescc.
     * @return List<Remuneracion> Retorna una lista de objetos del tipo Remuneracion.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static List<Remuneracion> getAllRemuneracionesByCc(int idCc) throws SQLException, ClassNotFoundException {
        ArrayList<Remuneracion> remuneraciones = new ArrayList<>();
        String getAllRemuneracionesByTrabajador = "SELECT * FROM remuneracionescc rm JOIN centrosdecosto cc ON (rm.centrodecosto = cc.id) WHERE cc.id =" + idCc + "";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(getAllRemuneracionesByTrabajador);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Remuneracion rem = new Remuneracion();
            rem.setCentroCosto(rs.getInt("id"));
            rem.setPeriodo(rs.getDate("periodo").toLocalDate());
            rem.setUrl(rs.getString("adjunto"));
            rem.setNombreCentroCosto(rs.getString("nombre"));
            rem.setDocumento(rs.getString("documento"));
            remuneraciones.add(rem);
        }
        ps.close();
        rs.close();
        conectar.close();
        return remuneraciones;
    }

    /**
     * Permite enlistar todas las remuneraciones que corresponden a un cierto periodo, registradas
     * en la tabla remuneraciones de la instancia CloudSQL. Contiene como constante, el valor del mes actual,
     * inserto como cláusula en la consulta SQL.
     *
     * @return List<Remuneracion> Retorna una lista de objetos del tipo Remuneracion.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static List<Remuneracion> getAllRemuneracionesMes() throws SQLException, ClassNotFoundException {
        ArrayList<Remuneracion> remuneraciones = new ArrayList<>();
        final int mesActual = LocalDate.now().getMonth().getValue();
        String mesActualQuery = "SELECT * FROM remuneraciones where EXTRACT(MONTH FROM periodo) ='" + mesActual + "'";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(mesActualQuery);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Remuneracion rem = new Remuneracion();
            rem.setTrabajador(rs.getString("trabajador"));
            rem.setPeriodo(rs.getDate("periodo"));
            rem.setUrl(rs.getString("adjunto"));
            remuneraciones.add(rem);
        }
        ps.close();
        rs.close();
        conectar.close();
        return remuneraciones;
    }

    /**
     * Permite enlistar todas las remuneraciones de un trabajador, correspondientes al mes corriente.
     *
     * @param rut Recibe como parámetro un String, corrspondiente al rut del trabajador(rut) en la tabla
     *            remuneraciones.
     * @return List<Remuneracion> Retorna una lista de objetos del tipo Remuneracion.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static List<Remuneracion> getAllRemuneracionesMesPorTrabajador(String rut) throws SQLException, ClassNotFoundException {
        ArrayList<Remuneracion> remuneraciones = new ArrayList<>();
        int mesActual = LocalDate.now().getMonth().getValue();
        String mesActualQuery = "SELECT * FROM remuneraciones where MONTH(periodo) ='" + mesActual + "' AND trabajador='" + rut + "'";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(mesActualQuery);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Remuneracion rem = new Remuneracion();
            rem.setTrabajador(rs.getString("trabajador"));
            rem.setPeriodo(rs.getDate("periodo"));
            rem.setUrl(rs.getString("adjunto"));
            remuneraciones.add(rem);
        }
        return remuneraciones;
    }

    /**
     * Verifica la existencia de una Remuneración en la tabla remuneraciones de la instancia CloudSQL.
     *
     * @param rut     Recibe como parámetro un String correspondiente al trabajador(rut) de la tabla.
     * @param periodo Recibe como parámetro un SQL Date, correspondiente al periodo en la tabla remuneraciones.
     * @return Retorna un valor booleano, dependiendo de la existencia de registros en las filas de la tabla.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static boolean verificarRemuneracionExists(String rut, java.sql.Date periodo) throws SQLException, ClassNotFoundException {
        boolean remuneracionExist = false;
        Remuneracion remuneracion = new Remuneracion();
        String verificarQuery = "SELECT * FROM remuneraciones WHERE trabajador = '" + rut + "' AND periodo = '" + periodo + "'";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(verificarQuery);
        ResultSet rs = ps.executeQuery();
        boolean verify = rs.next();
        if (!verify) {
            remuneracionExist = false;
        } else {
            remuneracionExist = true;
        }
        ps.close();
        rs.close();
        conectar.close();
        return remuneracionExist;
    }

    /**
     * Verifica la existencia de una Remuneración en la tabla remuneraciones de la instancia CloudSQL.
     *
     * @param rut     Recibe como parámetro un String correspondiente al trabajador(rut) de la tabla.
     * @param periodo Recibe como parámetro un SQL Date, correspondiente al periodo en la tabla remuneraciones.
     * @return Retorna un valor booleano, dependiendo de la existencia de registros en las filas de la tabla.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static boolean verificarRemuneracionExistsEnCc(int centroCosto, java.sql.Date periodo) throws SQLException, ClassNotFoundException {
        boolean remuneracionExist = false;
        Remuneracion remuneracion = new Remuneracion();
        String verificarQuery = "SELECT * FROM remuneracionescc WHERE centrodecosto = " + centroCosto + " AND periodo = '" + periodo + "'";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(verificarQuery);
        ResultSet rs = ps.executeQuery();
        boolean verify = rs.next();
        if (!verify) {
            remuneracionExist = false;
        } else {
            remuneracionExist = true;
        }
        ps.close();
        rs.close();
        conectar.close();
        return remuneracionExist;
    }

    /**
     * Permite eliminar la remuneración de un Centro de Costo.
     *
     * @param rut Recibe como parámetro un String, corrspondiente al rut del trabajador(rut) en la tabla
     *            remuneraciones.
     * @return List<Remuneracion> Retorna una lista de objetos del tipo Remuneracion.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static void deleteRemuneracion(java.sql.Date periodo, int centroCosto) throws SQLException, ClassNotFoundException {
        String eliminarRem = "DELETE FROM remuneracionescc WHERE centrodecosto = " + centroCosto + " AND periodo = '" + periodo + "'";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(eliminarRem);
        ps.execute();
        ps.close();
        conectar.close();
    }
}