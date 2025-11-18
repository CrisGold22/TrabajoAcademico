/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.persona;

/**
 *
 * @author Cristhian Horacio
 */
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.EmailService;

public class EmailServiceImpl implements EmailService {

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final int SMTP_PORT = 587;

    // TU CORREO DE GMAIL
    private static final String GMAIL_CORREO = "galileodevscorp@gmail.com";

    // CONTRASEÑA DE APLICACIÓN (NO la normal)
    private static final String GMAIL_APP_PASSWORD = "doeq jdxy cbvh dpkt";

    @Override
    public void enviarCorreo(String para, String asunto, String cuerpoHtml) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // TLS
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", String.valueOf(SMTP_PORT));

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(GMAIL_CORREO, GMAIL_APP_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(GMAIL_CORREO));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
            message.setSubject(asunto);
            message.setContent(cuerpoHtml, "text/html; charset=UTF-8");

            Transport.send(message);
            System.out.println("Correo enviado a: " + para);

        } catch (MessagingException e) {
            throw new RuntimeException("Error enviando correo: " + e.getMessage(), e);
        }
    }
}
