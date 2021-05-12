package controlador;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * El sistema implementa esta clase Mail que permite el envío de datos a través de un formulario
 * ubicado en la vista index del sitio web corporativo. Permite a clientes y usuarios enviar
 * datos que son procesados por JavaMail, API de Java que permite el envío en formato correo electrónico.
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/mail/package-summary.html" target="_blank">javax.mail</a>
 */
public class Mail {

    /**
     * Permite el retorno de un objeto de tipo Message que es utilizado por el método estático sendMail
     * para enviar los datos procesador por el formulario JSP en el index del sitio corporativo.
     * @param session        Requiere un objeto del tipo Session para ser recibido por la instancia MimeMessage
     *                       que requiere el objeto del tipo Message retornado por el método.
     * @param myAccountEmail Recibe un String con el email emisor requerido por la instancia de InternetAddress
     *                       seteado como Address en el objeto message (setFrom).
     * @param recipient      Recibe un String con el email de destino a través del método setRecipient del objeto message.
     * @param email          Recibe un String con el mensaje concatenado enviado a través del formulario JSP, y que es
     *                       requerido por el método estático sendMail que a su vez, implementa el método prepareMessage
     *                       donde finalmente es registrado.
     * @return Retorna un objeto del tipo Message que es utilizado cuando se implementa en el método sendMail.
     */
    private static Message prepareMessage(Session session, String myAccountEmail, String recipient, String email) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("My First Email from Java");
            message.setText(email);
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Permite el envío del correo electrónico basado en los datos del formulario en el sitio index.jsp principal.
     * @param recipient Recibe un String utilizado en el método prepareMessage y que corresponde al email de destino.
     * @param email Recibe un String utilizado en el método prepareMessage que corresponde al texto concatenado como mensaje.
     */
    public static void sendMail(String recipient, String email) {
        System.out.println("Preparing send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.");
        properties.put("mail.smtp.port", "587");

        //        Inserta aquí el correo electrónico emisor
        String myAccountEmail = "correo_electronico@dominio";
        //        Inserta aquí la contraseña
        String password = "correo_electronico_password";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);

            }
        });

        Message message = prepareMessage(session, myAccountEmail, recipient, email);

        try {
            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
