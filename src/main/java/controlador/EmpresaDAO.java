/*
 * Copyright <2020> <Miguel Gajardo M.>
 * Por la presente se concede permiso, libre de cargos, a cualquier persona que obtenga una copia de este software y de los archivos de documentación asociados (el "Software"), a utilizar el Software sin restricción, incluyendo sin limitación los derechos a usar, copiar, modificar, fusionar, publicar, distribuir, sublicenciar, y/o vender copias del Software, y a permitir a las personas a las que se les proporcione el Software a hacer lo mismo, sujeto a las siguientes condiciones:
 *
 * El aviso de copyright anterior y este aviso de permiso se incluirán en todas las copias o partes sustanciales del Software.
 *
 * EL SOFTWARE SE PROPORCIONA "COMO ESTA", SIN GARANTÍA DE NINGÚN TIPO, EXPRESA O IMPLÍCITA, INCLUYENDO PERO NO LIMITADO A GARANTÍAS DE COMERCIALIZACIÓN, IDONEIDAD PARA UN PROPÓSITO PARTICULAR E INCUMPLIMIENTO. EN NINGÚN CASO LOS AUTORES O PROPIETARIOS DE LOS DERECHOS DE AUTOR SERÁN RESPONSABLES DE NINGUNA RECLAMACIÓN, DAÑOS U OTRAS RESPONSABILIDADES, YA SEA EN UNA ACCIÓN DE CONTRATO, AGRAVIO O CUALQUIER OTRO MOTIVO, DERIVADAS DE, FUERA DE O EN CONEXIÓN CON EL SOFTWARE O SU USO U OTRO TIPO DE ACCIONES EN EL SOFTWARE.
 */

package controlador;

import modelo.Empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Capa de acceso a los datos contenidos en la tabla empresas de la instancia CloudSQL.
 * Contiene las funciones generales para invocar, registrar, actualizar y eliminar datos.
 *
 * @author Miguel Gajardo M. - Desarrollador Full Stack Java.
 */
public class EmpresaDAO {

    /**
     * Permite enlistar todas las empresas registradas.
     * @return Retorna una lista de objetos Empresa, con todas las empresas registradas en la tabla Empresas.
     * * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static List<Empresa> getAllEmpresas() throws SQLException, ClassNotFoundException {
        ArrayList<Empresa> empresas = new ArrayList<>();
        Connection conectar = AdminConexion.conexion();
        String adminEmpresasQuery = "SELECT * FROM empresas";
        PreparedStatement ps = conectar.prepareStatement(adminEmpresasQuery);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Empresa em = new Empresa();
            em.setRazonSocial(rs.getString("razonsocial"));
            em.setDomicilio(rs.getString("domicilio"));
            em.setRut(rs.getString("rut"));
            em.setEmail(rs.getString("email"));
            em.setUsuario(rs.getString("usuario"));
            empresas.add(em);
        }
        conectar.close();
        ps.close();
        rs.close();
        return empresas;
    }

    /**
     * Permite obtener un objeto del tipo Empresa.
     * @param rutEmpresa Recibe como parámetro un String correspondiente al rut(varchar) en la tabla Empresas.
     * @return Retorna un objeto Empresa.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static Empresa getEmpresa(String rutEmpresa) throws SQLException, ClassNotFoundException {
        Empresa empresa = new Empresa();
        Connection conectar = AdminConexion.conexion();
        String empresaQuery = "SELECT * FROM empresas where rut='" + rutEmpresa + "'";
        PreparedStatement ps = conectar.prepareStatement(empresaQuery);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            empresa.setRut(rs.getString("rut"));
            empresa.setRazonSocial(rs.getString("razonsocial"));
            empresa.setEmail(rs.getString("email"));
            empresa.setDomicilio(rs.getString("domicilio"));
            empresa.setUsuario(rs.getString("usuario"));
        }
        conectar.close();
        ps.close();
        rs.close();
        return empresa;
    }

    /**
     * Registra una nueva Empresa en la tabla empresas de la instancia CloudSQL. Para registrar una nueva
     * empresa en el sistema, no es imprescindible el anterior registro de un Usuario, por lo que el
     * registro se lleva cabo con la inserción null en el campo usuario(rut) de la tabla.
     *
     * @param empresa Recibe como parámetro un objeto del tipo Empresa.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static void setNuevaEmpresa(Empresa empresa) throws SQLException, ClassNotFoundException {
        Connection conectar = AdminConexion.conexion();
        String setNuevaEmpresa = "INSERT INTO empresas (razonsocial, domicilio, rut, email, usuario) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conectar.prepareStatement(setNuevaEmpresa);
        ps.setString(1, empresa.getRazonSocial());
        ps.setString(2, empresa.getDomicilio());
        ps.setString(3, empresa.getRut());
        ps.setString(4, empresa.getEmail());
        ps.setString(5, null);
        ps.execute();
        conectar.close();
        ps.close();
    }

    /**
     * Modifica una empresa que ya tiene un usuario asignado, permitiendo la visualización de todos los
     * campos con excepción del usuario.
     *
     * @param empresa Recibe como parámetro un objeto del tipo Empresa.
     * @param rut     Recibe como parámetro un String correspondiente al rut(varchar) de la empresa a modificar.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static void modificarEmpresa(Empresa empresa, String rut) throws SQLException, ClassNotFoundException {
        String modificarEmpresa = "UPDATE empresas SET razonsocial = ?, domicilio = ?, rut = ?, email = ? WHERE rut ='" + rut + "'";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(modificarEmpresa);
        ps.setString(1, empresa.getRazonSocial());
        ps.setString(2, empresa.getDomicilio());
        ps.setString(3, empresa.getRut());
        ps.setString(4, empresa.getEmail());
        ps.executeUpdate();
        conectar.close();
        ps.close();
    }

    /**
     * Modifica una empresa que no tiene un usuario asignado, permitiendo la asignación del mismo
     * mediante la representación de una lista de usuarios disponibles.
     *
     * @param empresa Recibe como parámetro un objeto del tipo Empresa.
     * @param rut     Recibe como parámetro un String correspondiente al rut(varchar) de la empresa a modificar.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static void modificarEmpresaSu(Empresa empresa, String rut) throws SQLException, ClassNotFoundException {

        String modificarEmpresa = "UPDATE empresas SET razonsocial = ?, domicilio = ?, rut = ?, email = ?, usuario = ? WHERE rut ='" + rut + "'";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(modificarEmpresa);
        ps.setString(1, empresa.getRazonSocial());
        ps.setString(2, empresa.getDomicilio());
        ps.setString(3, empresa.getRut());
        ps.setString(4, empresa.getEmail());
        ps.setString(5, empresa.getUsuario());
        ps.executeUpdate();
        conectar.close();
        ps.close();
    }

    /**
     * Elimina una empresa desde el registro de la tabla Empresas en la instancia CloudSQL.
     * @param rut Recibe como parámetro un String correspondiente al rut(varchar) de la empresa a eliminar.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static void eliminarEmpresaByRut(String rut) throws SQLException, ClassNotFoundException {
        String eliminarEmpresa = "DELETE FROM empresas WHERE rut = '" + rut + "'";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(eliminarEmpresa);
        ps.execute();
        conectar.close();
        ps.close();
    }

    /**
     * Permite enlistar todas las empresas registradas en la tabla Empresas, que tienen en común
     * a un mismo usuario.
     * @param usuario Recibe como parámetro un String con el rut del usuario(rut) registrado en la tabla Empresas.
     * @return Retorna una lista con todos los objetos del tipo Empresa donde el usuario coincida como parámetro.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static List<Empresa> getAllEmpresasDelUsuario(String usuario) throws SQLException, ClassNotFoundException {
        ArrayList<Empresa> empresas = new ArrayList<>();
        String adminEmpresasQuery = "SELECT * FROM empresas where usuario='" + usuario + "'";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(adminEmpresasQuery);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Empresa em = new Empresa();
            em.setRazonSocial(rs.getString("razonsocial"));
            em.setDomicilio(rs.getString("domicilio"));
            em.setRut(rs.getString("rut"));
            em.setEmail(rs.getString("email"));
            em.setUsuario(rs.getString("usuario"));
            empresas.add(em);
            }
        conectar.close();
        ps.close();
        rs.close();
        return empresas;
    }
}