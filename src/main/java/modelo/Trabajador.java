/*
 * Copyright <2020> <Miguel Gajardo M.>
 * Por la presente se concede permiso, libre de cargos, a cualquier persona que obtenga una copia de este software y de los archivos de documentación asociados (el "Software"), a utilizar el Software sin restricción, incluyendo sin limitación los derechos a usar, copiar, modificar, fusionar, publicar, distribuir, sublicenciar, y/o vender copias del Software, y a permitir a las personas a las que se les proporcione el Software a hacer lo mismo, sujeto a las siguientes condiciones:
 *
 * El aviso de copyright anterior y este aviso de permiso se incluirán en todas las copias o partes sustanciales del Software.
 *
 * EL SOFTWARE SE PROPORCIONA "COMO ESTA", SIN GARANTÍA DE NINGÚN TIPO, EXPRESA O IMPLÍCITA, INCLUYENDO PERO NO LIMITADO A GARANTÍAS DE COMERCIALIZACIÓN, IDONEIDAD PARA UN PROPÓSITO PARTICULAR E INCUMPLIMIENTO. EN NINGÚN CASO LOS AUTORES O PROPIETARIOS DE LOS DERECHOS DE AUTOR SERÁN RESPONSABLES DE NINGUNA RECLAMACIÓN, DAÑOS U OTRAS RESPONSABILIDADES, YA SEA EN UNA ACCIÓN DE CONTRATO, AGRAVIO O CUALQUIER OTRO MOTIVO, DERIVADAS DE, FUERA DE O EN CONEXIÓN CON EL SOFTWARE O SU USO U OTRO TIPO DE ACCIONES EN EL SOFTWARE.
 */

package modelo;

import java.util.List;
/**
 * Representa una persona legal perteneciente a una organización o grupo. Es identificada con
 * número fiscal (RUT) chileno, y tiene relación con la tabla trabajadores de la instancia CloudSQL.
 *
 * @author Miguel Gajardo M. - Desarrollador Full Stack Java.
 */
public class Trabajador {
	
	private String nombre;
	
	private String apellido;
	
	private String rut;
	
	private String email;
	
	private String empresa;
	
	private String empresaNombre;
	
	public Trabajador() {
		
	}

	/**
	 * Permite validar la existencia de un trabajador en una lista de trabajadores.
	 * @param list Recibe como parámetro una lista con objetos del tipo Trabajador.
	 * @param rutTrabajador Recibe como parámetro un String, correspondiente al rut del trabajador en el filtro.
	 * @return Retorna un valor booleano dependiendo de la existencia del trabajador en la lista.
	 */
    public boolean containsName(final List<Trabajador> list, final String rutTrabajador) {
	    return list.stream().filter(trabajador -> trabajador.getRut().equals(rutTrabajador)).findFirst().isPresent();
	}
	
	public Trabajador(String nombre, String apellido, String rut, String email, String empresa) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.rut = rut;
		this.email = email;
		this.empresa = empresa;
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
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public void setEmpresaNombre(String empresaNombre) {
		this.empresaNombre = empresaNombre;
	}
	
	public String getEmpresaNombre() {
		return empresaNombre;
	}
	
	public String getEmpresa() {
		return empresa;
	}
	
	@Override
	public String toString() {
		return this.nombre + " " + this.apellido + " " + this.rut + " " + this.empresa;
	}
	
}
