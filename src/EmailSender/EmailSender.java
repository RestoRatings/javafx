/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmailSender;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import  tn.esprit.utils.Datasource;
//import static com.sun.org.glassfish.external.amx.AMXUtil.prop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tn.esprit.entities.User;
/**
 *
 * @author LENOVO
 */
public class EmailSender {
    public static boolean sendPasswordResetEmail(String userEmail, String resetCode) {
        // Set up the mail server properties
        Properties properties = new Properties();
        final String moncompteEmail = "mohamedyemen.khefacha@esprit.tn";
        final String psw = "223JMT5009";
       properties.put("mail.smtp.host", "smtp.gmail.com"); // Gmail SMTP server
       properties.put("mail.smtp.port", "587"); // Gmail SMTP port
       properties.put("mail.smtp.auth", "true");
       
       properties.put("mail.smtp.starttls.enable", "true");
        // Create a Session with authentication
        Session ses = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(moncompteEmail, psw); // Replace with your email and password
            }
        });
        
        try {
            // Create a MimeMessage
            Message message = new MimeMessage(ses);
            message.setFrom(new InternetAddress(moncompteEmail)); // Replace with your email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Password Reset");

            // Email content
            String emailContent = "Use the following code to reset your password: " + resetCode;
            message.setText(emailContent);

            // Send the email
            Transport.send(message);
                   System.out.println("Message sent successfully");
                    return true;
 // Email sent successfully
        } catch (MessagingException e) {
            e.printStackTrace();
            return false; // Email sending failed
        }
    }
}
