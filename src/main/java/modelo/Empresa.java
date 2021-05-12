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
 * Corresponde a la representación básica de una organización de tipo Empresa, con aspectos locales
 * y fiscales.
 *
 * @author Miguel Gajardo M. - Desarrollador Full Stack Java.
 */
public class Empresa {

	private int idEmpresa;
	
	private String razonSocial;
	
	private String domicilio;
	
	private String rut;
	
	private String email;
	
	private String usuario;
	
	public Empresa() {
		
	}

	/**
	 * Construye un objeto del tipo Empresa correspondiente a una organización con número fiscal chileno (RUT).
	 * @param razonSocial Corresponde al nombre legal de la empresa terminado en tipo legal
	 *                       (S.p.A - Ltda. S.A. etc...).
	 * @param domicilio Corresponde al domicilio legal de la empresa.
	 * @param rut Corresponde al número fiscal en Chile, de 9 dígitos, sin puntos ni guión.
	 * @param email Correo Electrónico de formato con caracter arroba.
	 * @param usuario Corresponde al rut del usuario que accede a los datos de la empresa.
	 */
	public Empresa(String razonSocial, String domicilio, String rut, String email, String usuario) {
		this.razonSocial = razonSocial;
		this.domicilio = domicilio;
		this.rut = rut;
		this.email = email;
		this.usuario = usuario;
	}
	
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	public String getRazonSocial() {
		return razonSocial;
	}
	
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	
	public String getDomicilio() {
		return domicilio;
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
	
	public void setUsuario(String rut) {
		this.usuario = rut;
	}
	
	public String getUsuario() {
		return usuario == null ? "Sin Asignar" : usuario;
	}

	@Override
	public String toString() {
		String empresa = this.razonSocial + " | RUT: " + this.rut;
		return empresa.toUpperCase();
	}
	
}
