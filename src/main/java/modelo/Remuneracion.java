/*
 * Copyright <2021> <Miguel Gajardo M.>
 * Por la presente se concede permiso, libre de cargos, a cualquier persona que obtenga una copia de este software y de los archivos de documentación asociados (el "Software"), a utilizar el Software sin restricción, incluyendo sin limitación los derechos a usar, copiar, modificar, fusionar, publicar, distribuir, sublicenciar, y/o vender copias del Software, y a permitir a las personas a las que se les proporcione el Software a hacer lo mismo, sujeto a las siguientes condiciones:
 *
 * El aviso de copyright anterior y este aviso de permiso se incluirán en todas las copias o partes sustanciales del Software.
 *
 * EL SOFTWARE SE PROPORCIONA "COMO ESTA", SIN GARANTÍA DE NINGÚN TIPO, EXPRESA O IMPLÍCITA, INCLUYENDO PERO NO LIMITADO A GARANTÍAS DE COMERCIALIZACIÓN, IDONEIDAD PARA UN PROPÓSITO PARTICULAR E INCUMPLIMIENTO. EN NINGÚN CASO LOS AUTORES O PROPIETARIOS DE LOS DERECHOS DE AUTOR SERÁN RESPONSABLES DE NINGUNA RECLAMACIÓN, DAÑOS U OTRAS RESPONSABILIDADES, YA SEA EN UNA ACCIÓN DE CONTRATO, AGRAVIO O CUALQUIER OTRO MOTIVO, DERIVADAS DE, FUERA DE O EN CONEXIÓN CON EL SOFTWARE O SU USO U OTRO TIPO DE ACCIONES EN EL SOFTWARE.
 */

package modelo;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Corresponde al documento que incluye datos estándares de identidad, y que pertenece a un Trabajador
 * o Centro de Negocios, Costo u Obra.
 * Mantiene directa relación con la tabla remuneraciones y la asignación de periodos.
 * Tiene representación como documento cargado en un bucket de CloudStorage.
 *
 * @author Miguel Gajardo M. - Desarrollador Full Stack Java.
 */
public class Remuneracion {

    private String trabajador;

    private LocalDate periodo;

    private String url;

    private String nombreTrabajador;

    private String apellidoTrabajador;

    private int centroCosto;

    private String nombreCentroCosto;

    private String documento;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Remuneracion() {

    }

    public void setDocumento(String nombreDocumento) {
        this.documento = nombreDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setCentroCosto(int idCentroCosto) {
        this.centroCosto = idCentroCosto;
    }

    public int getCentroCosto() {
        return centroCosto;
    }

    public void setTrabajador(String trabajador) {
        this.trabajador = trabajador;
    }

    public String getTrabajador() {
        return trabajador;
    }

    public void setPeriodo(LocalDate periodo) {
        this.periodo = periodo;

    }

    public void setNombreCentroCosto(String nombreCentroCosto) {
        this.nombreCentroCosto = nombreCentroCosto;
    }

    public String getNombreCentroCosto() {
        return nombreCentroCosto;
    }

    public LocalDate getPeriodo() {
        return periodo;
    }

    public String mostrarPeriodo() {
        String yearPeriodo = Integer.valueOf(periodo.getYear()).toString();
        Locale cl = new Locale("es", "CL");
        String monthPeriodo = periodo.getMonth().getDisplayName(TextStyle.FULL, cl);
        return yearPeriodo + "-" + monthPeriodo;
    }


    public void setPeriodo(Object attribute) {

    }

    public void setNombreTrabajador(String nombreTrabajador) {
        this.nombreTrabajador = nombreTrabajador;
    }

    public String getNombreTrabajador() {
        return nombreTrabajador;
    }

    public void setApellidoTrabajador(String apellidoTrabajador) {
        this.apellidoTrabajador = apellidoTrabajador;
    }

    public String getApellidoTrabajador() {
        return apellidoTrabajador;
    }

}
