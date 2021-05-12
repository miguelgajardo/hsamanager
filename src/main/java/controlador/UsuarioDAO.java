/*
 * Copyright <2020> <Miguel Gajardo M.>
 * Por la presente se concede permiso, libre de cargos, a cualquier persona que obtenga una copia de este software y de los archivos de documentación asociados (el "Software"), a utilizar el Software sin restricción, incluyendo sin limitación los derechos a usar, copiar, modificar, fusionar, publicar, distribuir, sublicenciar, y/o vender copias del Software, y a permitir a las personas a las que se les proporcione el Software a hacer lo mismo, sujeto a las siguientes condiciones:
 *
 * El aviso de copyright anterior y este aviso de permiso se incluirán en todas las copias o partes sustanciales del Software.
 *
 * EL SOFTWARE SE PROPORCIONA "COMO ESTA", SIN GARANTÍA DE NINGÚN TIPO, EXPRESA O IMPLÍCITA, INCLUYENDO PERO NO LIMITADO A GARANTÍAS DE COMERCIALIZACIÓN, IDONEIDAD PARA UN PROPÓSITO PARTICULAR E INCUMPLIMIENTO. EN NINGÚN CASO LOS AUTORES O PROPIETARIOS DE LOS DERECHOS DE AUTOR SERÁN RESPONSABLES DE NINGUNA RECLAMACIÓN, DAÑOS U OTRAS RESPONSABILIDADES, YA SEA EN UNA ACCIÓN DE CONTRATO, AGRAVIO O CUALQUIER OTRO MOTIVO, DERIVADAS DE, FUERA DE O EN CONEXIÓN CON EL SOFTWARE O SU USO U OTRO TIPO DE ACCIONES EN EL SOFTWARE.
 */

package controlador;

import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Capa de acceso a los datos contenidos en la tabla usuarios de la instancia CloudSQL.
 * Contiene las funciones generales para invocar, registrar, actualizar y eliminar datos en la tabla.
 *
 * @author Miguel Gajardo M. - Desarrollador Full Stack Java.
 */
public class UsuarioDAO {

    /**
     * Registra un nuevo usuario en la taba usuarios de la instancia CloudSQL.
     *
     * @param usuario Recibe como parámetro un objeto del tipo Usuario.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static void registrarNuevoUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {
        String insertarNuevaEmpresa = "INSERT INTO usuarios (nombre, apellido, rut, email, credentials, controlacceso) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(insertarNuevaEmpresa);
        ps.setString(1, usuario.getNombre());
        ps.setString(2, usuario.getApellido());
        ps.setString(3, usuario.getRut());
        ps.setString(4, usuario.getEmail());
        ps.setString(5, usuario.getCredentials());
        ps.setInt(6, usuario.getControlAcceso());
        ps.execute();
        ps.close();
        conectar.close();
    }

    /**
     * Permite enlistar todos los usuarios registrados en la tabla usuarios de la instancia CloudSQL,
     * que cuentan con un nivel de acceso usuario (2).
     *
     * @return Retorna una lista de objetos del tipo Usuario.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static List<Usuario> getAllUsuarios() throws SQLException, ClassNotFoundException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Connection conectar = AdminConexion.conexion();
        String adminUsuariosQuery = "SELECT * FROM usuarios where controlacceso = 2";
        PreparedStatement ps = conectar.prepareStatement(adminUsuariosQuery);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Usuario us = new Usuario();
            us.setRut(rs.getString("rut"));
            us.setNombre(rs.getString("nombre"));
            us.setApellido(rs.getString("apellido"));
            us.setEmail(rs.getString("email"));
            usuarios.add(us);
        }
        ps.close();
        rs.close();
        conectar.close();
        return usuarios;
    }

    /**
     * Permite obtener el nivel de acceso de un usuario.
     *
     * @param rutControl  Recibe como parámetro un String correspondiente al rut del usuario en la tabla usuarios.
     * @param passControl Recibe como parámetro un String correspondiente al password del usuario.
     * @return Retorna el valor entero correspondiente a si es usuario Administrador (1), o usuario simple (2).
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static int controlAcceso(String rutControl, String passControl) throws SQLException, ClassNotFoundException {
        int controlAcceso = 0;
        Connection conectar = AdminConexion.conexion();
        String controlAccesoQuery = "SELECT controlacceso from usuarios where rut='" + rutControl
                + "' and credentials='" + passControl + "'";
        PreparedStatement ps = conectar.prepareStatement(controlAccesoQuery);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            controlAcceso = rs.getInt(1);
        }
        ps.close();
        rs.close();
        conectar.close();
        return controlAcceso;
    }

    /**
     * Permite obtener un usuario.
     *
     * @param rutControl Recibe como parámetro un String, correspondiente al rut del usuario en la tabla usuarios.     * @return
     * @return Retorna un objeto del tipo Usuario.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static Usuario getUsuario(String rutControl) throws SQLException, ClassNotFoundException {
        Connection conectar = AdminConexion.conexion();
        Usuario us = new Usuario();
        String usuarioLogeadoQuery = "SELECT * FROM usuarios where rut='" + rutControl + "'";
        PreparedStatement ps = conectar.prepareStatement(usuarioLogeadoQuery);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            us.setRut(rs.getString("rut"));
            us.setNombre(rs.getString("nombre"));
            us.setApellido(rs.getString("apellido"));
            us.setEmail(rs.getString("email"));
        }
        ps.close();
        rs.close();
        conectar.close();
        return us;
    }

    /**
     * Modifica los datos de un usuario registrado en la tabla usuarios de la instancia CloudSQL.
     *
     * @param usuario Recibe como parámetro un objeto del tipo Usuario.
     * @param rut     Recibe como parámetro un String correspondiente al rut del usuario por modificar.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static void modificarUsuario(Usuario usuario, String rut) throws SQLException, ClassNotFoundException {
        String updateUsuarios = "UPDATE usuarios SET rut = ?, nombre = ?, apellido = ?, email = ? WHERE rut = '" + rut + "'";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(updateUsuarios);
        ps.setString(1, usuario.getRut());
        ps.setString(2, usuario.getNombre());
        ps.setString(3, usuario.getApellido());
        ps.setString(4, usuario.getEmail());
        ps.executeUpdate();
        ps.close();
        conectar.close();
    }

    /**
     * Elimina un usuario de la tabla usuarios de la instancia CloudSQL.
     * @param rut Recibe como parámetro un String correspondiente al rut del usuario a eliminar.
     * @throws SQLException           Lanza excepción cuando un error ocurre a nivel de la instancia CloudSQL.
     * @throws ClassNotFoundException Lanza excepción cuando no pudo cargarse la clase Driver de la conexión.
     */
    public static void eliminarUsuariorByRut(String rut) throws SQLException, ClassNotFoundException {
        String deleteUsuario = "DELETE FROM usuarios where rut = '" + rut + "'";
        Connection conectar = AdminConexion.conexion();
        PreparedStatement ps = conectar.prepareStatement(deleteUsuario);
        ps.execute();
        conectar.close();
        ps.close();
    }
}