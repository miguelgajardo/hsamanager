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
 * Corresponde a la representación de un centro de negocios, costo u obra, para la administración de
 * documentos relacionados a un grupo.
 *
 * @author Miguel Gajardo M. - Desarrollador Full Stack Java.
 */
public class CentroCosto {
    private int id;

    private String nombre;

    private String empresa;

    private String empresaNombre;

    public CentroCosto() {

    }

    public CentroCosto(String nombre, String empresa) {
        this.nombre = nombre;

        this.empresa = empresa;
    }

    /**
     * Permite validar la existencia de un centro de costo en una lista de Centros de Costo.
     * @param list Recibe como parámetro una lista con objetos del tipo CentroCosto.
     * @param idCentroCosto Recibe como parámetro un int, correspondiente al id del Centro de Costo en el filtro.
     * @return Retorna un valor booleano dependiendo de la existencia del objeto en la lista.
     */
    public boolean containsName(final List<CentroCosto> list, final int idCentroCosto) {
        return list.stream().filter(centrocosto -> centrocosto.getId() == idCentroCosto).findFirst().isPresent();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEmpresa(String rutEmpresa) {
        this.empresa = rutEmpresa;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresaNombre(String empresaNombre) {
        this.empresaNombre = empresaNombre;
    }

    public String getEmpresaNombre() {
        return empresaNombre;
    }

    @Override
    public String toString() {
        return "Centro de Costo: " + nombre + " Empresa: " + empresaNombre;
    }
}
