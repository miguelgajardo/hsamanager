package controlador;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.apache.commons.fileupload.FileItemStream;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Permite el uso del cliente para la carga de documentos en repositorios de Google
 * Cloud Storage.
 */
public class CloudStorage {
    private final Logger logger = Logger.getLogger(CloudStorage.class.getName());
    private static Storage storage = null;

    static {
        storage = StorageOptions.getDefaultInstance().getService();
    }

    /**
     * Crea un nuevo blob y carga el contenido haciendo uso de la interfaz Storage de Google Cloud.
     * @see <a href="https://javadoc.io/doc/com.google.cloud/google-cloud-storage/latest/index.html" target="_blank">com.google.cloud.storage</a>
     * @param trabajador String rut trabajador.
     * @param fileStream FileItemStream Valor del item iterado.
     * @param bucketName Nombre del Bucket proporcionado por Google Cloud Storage.
     * @return Retorna el link de descarga del archivo como un String.
     * @throws IOException      Retorna excepción I/O cuando uno de los parámetros falla en la conexión o registro.
     * @throws ServletException Retorna excepción cuando falla el servlet controlador.
     */
    public String uploadFile(final String trabajador, String periodo, FileItemStream fileStream, final String bucketName) throws IOException, ServletException {
       /*
       Definición del nombre final del documento cargado.
       Pueden incluirse valores adicionales para agregar identidad al documento.
       En el caso de nuestro sistema se requiere como String sólo el rut del trabajador y el nombre del documento,
       ya que el controlador Uploader agrega directamente el periodo, personalizando aún más el nombre del documento.
       */
        final String fileName = trabajador + periodo + fileStream.getName();

        @SuppressWarnings("deprecation")
        BlobInfo blobInfo = storage.create(BlobInfo.newBuilder(bucketName, fileName).setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER)))).build(), fileStream.openStream());
        logger.log(Level.INFO, "Uploaded File {0} as {1}", new Object[]{fileStream.getName(), fileName});
        return blobInfo.getMediaLink();
    }

    /**
     * Retorna un blob, del cual se pueden extraer datos.
     * @param idCc
     * @param fechaCarga
     * @param fileStream
     * @param bucketName
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public BlobInfo uploadFileCc(final int idCc, String fechaCarga, FileItemStream fileStream, final String bucketName) throws IOException, ServletException {
       /*
       Definición del nombre final del documento cargado.
       Pueden incluirse valores adicionales para agregar identidad al nombre del documento, como el Centro de Costo, y la
       fecha de carga del documento.
       */
        final String fileName = idCc + fechaCarga + fileStream.getName();

        @SuppressWarnings("deprecation")
        BlobInfo blobInfo = storage.create(BlobInfo.newBuilder(bucketName, fileName).setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER)))).build(), fileStream.openStream());
        logger.log(Level.INFO, "Uploaded File {0} as {1}", new Object[]{fileStream.getName(), fileName});
        return blobInfo;
    }

    /**
     * Permite eliminar un objeto indicando el nombre del proyecto, bucket y documento.
     * @param projectId ID del proyecto en Google Cloud
     * @param bucketName Nombre del bucket en Cloud Storage
     * @param objectName Nombre del documento u objeto en el bucket
     */
    public static void deleteObject(String projectId, String bucketName, String objectName) {

            Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
            storage.delete(bucketName, objectName);

            System.out.println("Object " + objectName + " was deleted from " + bucketName);
        }
}
