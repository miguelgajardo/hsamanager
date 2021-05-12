/*
 * Copyright <2020> <Miguel Gajardo M.>
 * Por la presente se concede permiso, libre de cargos, a cualquier persona que obtenga una copia de este software y de los archivos de documentación asociados (el "Software"), a utilizar el Software sin restricción, incluyendo sin limitación los derechos a usar, copiar, modificar, fusionar, publicar, distribuir, sublicenciar, y/o vender copias del Software, y a permitir a las personas a las que se les proporcione el Software a hacer lo mismo, sujeto a las siguientes condiciones:
 *
 * El aviso de copyright anterior y este aviso de permiso se incluirán en todas las copias o partes sustanciales del Software.
 *
 * EL SOFTWARE SE PROPORCIONA "COMO ESTA", SIN GARANTÍA DE NINGÚN TIPO, EXPRESA O IMPLÍCITA, INCLUYENDO PERO NO LIMITADO A GARANTÍAS DE COMERCIALIZACIÓN, IDONEIDAD PARA UN PROPÓSITO PARTICULAR E INCUMPLIMIENTO. EN NINGÚN CASO LOS AUTORES O PROPIETARIOS DE LOS DERECHOS DE AUTOR SERÁN RESPONSABLES DE NINGUNA RECLAMACIÓN, DAÑOS U OTRAS RESPONSABILIDADES, YA SEA EN UNA ACCIÓN DE CONTRATO, AGRAVIO O CUALQUIER OTRO MOTIVO, DERIVADAS DE, FUERA DE O EN CONEXIÓN CON EL SOFTWARE O SU USO U OTRO TIPO DE ACCIONES EN EL SOFTWARE.
 */

package modelo;

/**
 * Constituye la identidad a la cual se le distribuye el control y acceso a datos, actividades y
 * solicitudes, en el entorno del sistema.
 * Es identificado con número fiscal (RUT) chileno, control de acceso y password.
 *
 * @author Miguel Gajardo M. - Desarrollador Full Stack Java.
 */
public class Usuario {

	private int idUsuario;
	
	private String nombre, apellido, rut, email, credentials;
	
	private int controlAcceso;
	
	public Usuario(String nombre, String apellido, String rut, String email, String credentials, 
			int controlAcceso) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rut = rut;
		this.email = email;
		this.credentials = credentials;
		this.controlAcceso = controlAcceso;
	}
	
	public Usuario() {
		
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setRut(String rut) {
		this.rut = rut;
	}
	
	public String getRut() {
		return rut;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}
	
	public String getCredentials() {
		return credentials;
	}
	
 	public void setControlAcceso(int controlAcceso) {
 		this.controlAcceso = controlAcceso;
 	}
 	
 	public int getControlAcceso() {
 		return controlAcceso;
 	}

 	@Override
 	public String toString() {
 		String usuario = "Usuario: " + this.nombre + " " + this.apellido + " - RUT: " + this.rut;
 				return usuario.toUpperCase();
 	}
 	
 	
	
}
