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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Capa de acceso a los datos contenidos en la tabla centrosdecosto de la instancia CloudSQL.
 * Contiene las funciones generales para invocar, registrar, actualizar y eliminar datos en la tabla.
 *
 * @author Miguel Gajardo M. - Desarrollador Full Stack Java.
 */
public class CentroCostoDAO {

    /**
     * Obtiene un objeto del tipo CentroCosto indicando el id (int)
     * @param centroDeCostoId Recibe como parámetro un entero correspondiente al id del Centro de Costo.
     * @return Retorna un objeto CentroCosto
     * @throws SQLException Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static CentroCosto getCentroDeCostoById(int centroDeCostoId)throws SQLException, ClassNotFoundException {
        String getCentroDeCosto = "SELECT * FROM centrosdecosto cc JOIN empresas em ON(cc.empresa = em.rut) WHERE id = " + centroDeCostoId + "";
        CentroCosto cc = new CentroCosto();
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(getCentroDeCosto);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            cc.setId(rs.getInt("id"));
            cc.setNombre(rs.getString("nombre"));
            cc.setEmpresa(rs.getString("empresa"));
            cc.setEmpresaNombre(rs.getString("razonsocial"));
        }
        ps.close();
        rs.close();
        conectar.close();
        return cc;
    }

    /**
     * Registra un nuevo centro de costo en la tabla centrosdecosto de la instancia CloudSQL
     * @param centroCosto Recibe como parámetro un objeto del tipo CentroCosto
     * @throws SQLException Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static void registrarCentroDeCosto(CentroCosto centroCosto) throws SQLException, ClassNotFoundException {
        String insertCentroCosto = "INSERT INTO centrosdecosto (empresa, nombre) VALUES (?, ?)";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(insertCentroCosto);
        ps.setString(1, centroCosto.getEmpresa());
        ps.setString(2, centroCosto.getNombre());
        ps.execute();
        ps.close();
        conectar.close();
    }

    /**
     * Permite obtener todos los centros de costo con los datos de la empresa a las que pertencen (Join)
     * @return Retorna una lista de objetos del tipo CentroCosto
     * @throws SQLException Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static List<CentroCosto> getAllCentrosDeCosto() throws SQLException, ClassNotFoundException {
        ArrayList<CentroCosto> centrosDeCosto = new ArrayList<>();
        String selectAllCc = "SELECT * FROM centrosdecosto cc JOIN empresas em ON(cc.empresa = em.rut)";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(selectAllCc);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            CentroCosto cc = new CentroCosto();
            cc.setId(rs.getInt("id"));
            cc.setNombre(rs.getString("nombre"));
            cc.setEmpresa(rs.getString("empresa"));
            cc.setEmpresaNombre(rs.getString("razonsocial"));
            centrosDeCosto.add(cc);
        }
        ps.close();
        rs.close();
        conectar.close();
        return centrosDeCosto;
    }

    /**
     * Permite obtener los centros de costo pertenecientes a una empresa
     * @param rutEmpresa Recibe como parámetro el rut de la empresa
     * @return Retorna una lista de objetos del tipo CentroCosto
     * @throws SQLException Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static List<CentroCosto> getAllCentrosDeCostoByEmpresa(String rutEmpresa) throws SQLException, ClassNotFoundException {
        ArrayList<CentroCosto> centrosDeCostoEmpresa = new ArrayList<>();
        String selectAllCcEmpresa = "SELECT * FROM centrosdecosto cc JOIN empresas em ON(cc.empresa = em.rut) WHERE empresa = '" + rutEmpresa + "'";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(selectAllCcEmpresa);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            CentroCosto cc = new CentroCosto();
            cc.setId(rs.getInt("id"));
            cc.setNombre(rs.getString("nombre"));
            cc.setEmpresa(rs.getString("empresa"));
            cc.setEmpresaNombre(rs.getString("razonsocial"));
            centrosDeCostoEmpresa.add(cc);
        }
        ps.close();
        rs.close();
        conectar.close();
        return centrosDeCostoEmpresa;
    }

    /**
     * Permite eliminar un centro de costo indicando el ID
     * @param idcc Recibe como parámetro el ID del centro de costo
     * @throws SQLException Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static void deleteCentroDeCosto(int idcc) throws SQLException, ClassNotFoundException {
        String deleteCc = "DELETE FROM centrosdecosto where id = " + idcc + "";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(deleteCc);
        ps.execute();
        ps.close();
        conectar.close();
    }

    /**
     * Permite actualizar un centro de costo indicando el ID
     * @param idcc Recibe como parámetro el ID del centro de costo.
     * @param centroCosto Recibe como parámetro un objeto de la clase CentroCosto.
     * @throws SQLException Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static void updateCentroDeCosto(CentroCosto centroCosto, int idcc) throws SQLException, ClassNotFoundException {
        String updateCc = "UPDATE centrosdecosto SET empresa = ?, nombre = ? where id = " + idcc + "";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(updateCc);
        ps.setString(1, centroCosto.getEmpresa());
        ps.setString(2, centroCosto.getNombre());
        ps.executeUpdate();
        ps.close();
        conectar.close();
    }
}
