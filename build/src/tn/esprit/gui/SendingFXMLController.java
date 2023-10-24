/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileOutputStream;
import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;

import java.io.InputStreamReader;
import static java.lang.Math.round;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.util.Base64;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
/**
 * FXML Controller class
 *
 * @author mchai
 */
public class SendingFXMLController implements Initializable {

    @FXML
    private TextField recipientField;
    @FXML
    private TextField subjectField;
    @FXML
    private TextArea messageArea;

    private final String email = "mchairiayassine@gmail.com";  // Remplacez par votre adresse e-mail
    private final String motDePasse = "kfpv abwj woos hfek";  // Remplacez par votre mot de passe

    private final String host = "smtp.gmail.com";  // Serveur SMTP de Gmail
    private final int port = 587;  // Port pour Gmail
    @FXML
    private Button retR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        subjectField.setText("Votre réclamation");
        
    }

    @FXML
    private void sendEmail(ActionEvent event) {
       
    
    final String username = "mchairiayassine@gmail.com ";
    final String password = "kfpv abwj woos hfek";
    
    
       
     
      Properties props = new Properties();
       props.setProperty("mail.transport.protocol", "smtp");     
    props.setProperty("mail.host", "smtp.gmail.com");  
    props.put("mail.smtp.auth", "true");  
    props.put("mail.smtp.port", "465");  
    props.put("mail.debug", "true");  
    props.put("mail.smtp.socketFactory.port", "465");  
    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
    props.put("mail.smtp.socketFactory.fallback", "false");
     
      Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
     
     
   try {         
            
      Message message = new MimeMessage(session);

          
            message.setFrom(new InternetAddress(username));

   
             {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientField.getText()));
            }
              message.setSubject(subjectField.getText());
            message.setText(messageArea.getText());
       
            Transport.send(message);
             Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("E-mail envoyé avec succès !");
        alert.showAndWait();

        // Videz les champs de texte
        recipientField.clear();
        subjectField.clear();
        messageArea.clear();
        } catch (MessagingException ex) {
Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Erreur lors de l'envoi de l'e-mail : " + ex.getMessage());
        alert.showAndWait();         
        }

          


    }

    @FXML
    private void ret(ActionEvent event) {
             try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("gestionReclamation.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        
        
        
        
        
        
        
        
        
        
    
    }
}