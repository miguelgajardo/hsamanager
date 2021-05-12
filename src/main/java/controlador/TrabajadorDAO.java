/*
 * Copyright <2020> <Miguel Gajardo M.>
 * Por la presente se concede permiso, libre de cargos, a cualquier persona que obtenga una copia de este software y de los archivos de documentación asociados (el "Software"), a utilizar el Software sin restricción, incluyendo sin limitación los derechos a usar, copiar, modificar, fusionar, publicar, distribuir, sublicenciar, y/o vender copias del Software, y a permitir a las personas a las que se les proporcione el Software a hacer lo mismo, sujeto a las siguientes condiciones:
 *
 * El aviso de copyright anterior y este aviso de permiso se incluirán en todas las copias o partes sustanciales del Software.
 *
 * EL SOFTWARE SE PROPORCIONA "COMO ESTA", SIN GARANTÍA DE NINGÚN TIPO, EXPRESA O IMPLÍCITA, INCLUYENDO PERO NO LIMITADO A GARANTÍAS DE COMERCIALIZACIÓN, IDONEIDAD PARA UN PROPÓSITO PARTICULAR E INCUMPLIMIENTO. EN NINGÚN CASO LOS AUTORES O PROPIETARIOS DE LOS DERECHOS DE AUTOR SERÁN RESPONSABLES DE NINGUNA RECLAMACIÓN, DAÑOS U OTRAS RESPONSABILIDADES, YA SEA EN UNA ACCIÓN DE CONTRATO, AGRAVIO O CUALQUIER OTRO MOTIVO, DERIVADAS DE, FUERA DE O EN CONEXIÓN CON EL SOFTWARE O SU USO U OTRO TIPO DE ACCIONES EN EL SOFTWARE.
 */

package controlador;

import modelo.Trabajador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Capa de acceso a los datos contenidos en la tabla trabajadores de la instancia CloudSQL.
 * Contiene las funciones generales para invocar, registrar, actualizar y eliminar datos en la tabla.
 *
 * @author Miguel Gajardo M. - Desarrollador Full Stack Java.
 */
public class TrabajadorDAO {

    /**
     * Permite obtener un trabajador de la tabla trabajadores de la instancia CloudSQL.
     * @param rut Recibe como parámetro un String correspondiente al rut(varchar) del trabajador.
     * @return Retorna un objeto del tipo Trabajador.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static Trabajador getTrabajadorByRut(String rut) throws SQLException, ClassNotFoundException {
        Connection conectar = AdminConexion.conexion();
        Trabajador tr = new Trabajador();
        String getTrabajadorQuery = "SELECT * FROM trabajadores where rut='" + rut + "'";
        PreparedStatement ps = conectar.prepareStatement(getTrabajadorQuery);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            tr.setNombre(rs.getString("nombre"));
            tr.setApellido(rs.getString("apellido"));
            tr.setRut(rs.getString("rut"));
            tr.setEmail(rs.getString("email"));
            tr.setEmpresa(rs.getString("empresa"));
        }
        ps.close();
        rs.close();
        conectar.close();
        return tr;
    }

    /**
     * Permite registrar un nuevo trabajador en la tabla trabajadores de la instancia CloudSQL.
     * @param trabajador Recibe como parámetro un objeto del tipo Trabajador.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static void setNuevoTrabajador(Trabajador trabajador) throws SQLException, ClassNotFoundException {
        Connection conectar = AdminConexion.conexion();
        String setNuevoTrabajador = "INSERT INTO trabajadores (rut, nombre, apellido, email, empresa) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conectar.prepareStatement(setNuevoTrabajador);
        ps.setString(1, trabajador.getRut());
        ps.setString(2, trabajador.getNombre());
        ps.setString(3, trabajador.getApellido());
        ps.setString(4, trabajador.getEmail());
        ps.setString(5, trabajador.getEmpresa());
        ps.execute();
        ps.close();
        conectar.close();
    }

    /**
     * Permite enlistar todos los trabajadores registrados en la tabla trabajadores de la instancia CloudSQL.
     * @return Retorna una lista de objetos del tipo Trabajador.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static List<Trabajador> getAllTrabajadores() throws SQLException, ClassNotFoundException {
        ArrayList<Trabajador> trabajadores = new ArrayList<>();
        Connection conectar = AdminConexion.conexion();
        String getAllTrabajadores = "SELECT * FROM trabajadores";
        PreparedStatement ps = conectar.prepareStatement(getAllTrabajadores);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Trabajador tr = new Trabajador();
            tr.setNombre(rs.getString("nombre"));
            tr.setApellido(rs.getString("apellido"));
            tr.setRut(rs.getString("rut"));
            tr.setEmail(rs.getString("email"));
            tr.setEmpresa(rs.getString("empresa"));
            trabajadores.add(tr);
        }
        ps.close();
        rs.close();
        conectar.close();
        return trabajadores;
    }

    /**
     * Permite enlistar todos los trabajadores pertenecientes a una empresa.
     * @param rutEmpresa Recibe como parámetro un String correspondiente a empresa(rut) de la tabla.
     * @return Retorna una lista de objetos del tipo Trabajador.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static List<Trabajador> getAllTrabajadoresByEmpresa(String rutEmpresa) throws SQLException, ClassNotFoundException {
        ArrayList<Trabajador> trabajadores = new ArrayList<>();
        String getAllTrabajadoresByEmpresa = "SELECT * FROM trabajadores tr JOIN empresas em ON(tr.empresa = em.rut) WHERE em.rut = '" + rutEmpresa + "'";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(getAllTrabajadoresByEmpresa);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Trabajador tr = new Trabajador();
            tr.setNombre(rs.getString("nombre"));
            tr.setApellido(rs.getString("apellido"));
            tr.setEmail(rs.getString("email"));
            tr.setRut(rs.getString("rut"));
            tr.setEmpresa(rs.getString("empresa"));
            tr.setEmpresaNombre(rs.getString("razonsocial"));
            trabajadores.add(tr);
        }
        ps.close();
        rs.close();
        conectar.close();
        return trabajadores;
    }

    /**
     * Modifica los datos de un trabajador registrado en la tabla trabajadores de la instancia CloudSQL.
     * @param trabajador Recibe como parámetro un objeto del tipo Trabajador.
     * @param rut Recibe como parámetro un String correspondiente al rut sin modificar del trabajador registrado en la tabla.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static void modificarTrabajador(Trabajador trabajador, String rut) throws SQLException, ClassNotFoundException {
        String updateTrabajadores = "UPDATE trabajadores SET rut = ?, nombre = ?, apellido = ?, email = ? WHERE rut = '" + rut + "'";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(updateTrabajadores);
        ps.setString(1, trabajador.getRut());
        ps.setString(2, trabajador.getNombre());
        ps.setString(3, trabajador.getApellido());
        ps.setString(4, trabajador.getEmail());
        ps.executeUpdate();
        ps.close();
        conectar.close();
    }

    /**
     * Elimina un trabajador de la tabla de trabajadores de la instancia CloudSQL.
     * @param rut Recibe como parámetro un String, correspondiente al rut del trabajador.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static void eliminarTrabajadorByRut(String rut) throws SQLException, ClassNotFoundException {
        String deleteTrabajador = "DELETE FROM trabajadores where rut = '" + rut + "'";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(deleteTrabajador);
        ps.execute();
        ps.close();
        conectar.close();
    }

    /**
     * Permite enlistar todos los trabajadores registrados en la tabla trabajadores de la instancia
     * CloudSQL, siendo posible obtener de la lista, el nombre de la empresa a la que pertenece el trabajador.
     * @return Retorna una lista de objetos del tipo Trabajador.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static List<Trabajador> getAllTrabajadoresJoin() throws SQLException, ClassNotFoundException {
        ArrayList<Trabajador> trabajadoresJoin = new ArrayList<>();
        Connection conectar = AdminConexion.conexion();
        String getAllTrabajadoresJoin = "SELECT * FROM trabajadores tr JOIN empresas em ON (tr.empresa = em.rut)";
        PreparedStatement ps = conectar.prepareStatement(getAllTrabajadoresJoin);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Trabajador tr = new Trabajador();
            tr.setNombre(rs.getString(3));
            tr.setApellido(rs.getString(4));
            tr.setRut(rs.getString(2));
            tr.setEmail(rs.getString(5));
            tr.setEmpresa(rs.getString(6));
            tr.setEmpresaNombre(rs.getString(8));
            trabajadoresJoin.add(tr);
        }
        ps.close();
        rs.close();
        conectar.close();
        return trabajadoresJoin;
    }

    /**
     * Permite obtener un trabajador de la tabla trabajadores de la instancia CloudSQL, siendo
     * posible además obtener el nombre de la empresa a la cual pertenece.
     * @param rut Recibe como parámetro un String, correspondiente al rut del trabajador.
     * @return Retorna un objeto del tipo Trabajador.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static Trabajador getTrabajador(String rut) throws SQLException, ClassNotFoundException {
        Connection conectar = AdminConexion.conexion();
        Trabajador tr = new Trabajador();
        String getTrabajadorQuery = "SELECT * FROM trabajadores tr JOIN empresas em ON (tr.empresa = em.rut) WHERE tr.rut = '" + rut + "'";
        PreparedStatement ps = conectar.prepareStatement(getTrabajadorQuery);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            tr.setNombre(rs.getString(3));
            tr.setApellido(rs.getString(4));
            tr.setRut(rs.getString(2));
            tr.setEmail(rs.getString(5));
            tr.setEmpresa(rs.getString(6));
            tr.setEmpresaNombre(rs.getString("razonsocial"));
        }
        ps.close();
        rs.close();
        conectar.close();
        return tr;
    }
}