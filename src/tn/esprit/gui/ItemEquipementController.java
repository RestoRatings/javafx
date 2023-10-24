/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import tn.esprit.entities.Evennement;
import tn.esprit.entities.Participant;
import tn.esprit.entities.User;
import tn.esprit.services.Eventservice;
import tn.esprit.services.Participationservices;
import tn.esprit.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Med Iheb
 */
public class ItemEquipementController implements Initializable {

    @FXML
    private ImageView pngBoxRecompense;
    @FXML
    private Label nameLabel;
    @FXML
    private ImageView imgItemProduit;
    @FXML
    private Label priceLable;
    @FXML
    private Label IdItem;
    @FXML
    private Label descriptionlabel;
    @FXML
    private Label titrelabel;
    @FXML
    private Button btParticipant;
    @FXML
    private Button btAnnuler;
    
    ImgWithEventController imgWithEventController= new ImgWithEventController();
    @FXML
    private TextField NumerParticipant;
     int myIndex;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      FadeTransition fade = new FadeTransition();
  fade.setNode(pngBoxRecompense);
  fade.setDuration(Duration.millis(3000));
  fade.setCycleCount(TranslateTransition.INDEFINITE);
  fade.setInterpolator(Interpolator.LINEAR);
  fade.setFromValue(0);
  fade.setToValue(1);
  fade.play();
        // TODO
    }


    public void setData(Evennement eq) throws MalformedURLException {
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String formattedString = eq.getDate().format(formatter);
        this.nameLabel.setText(eq.getLieu()  +", " + eq.getAdresse());
        this.priceLable.setText(formattedString);
        this.titrelabel.setText(eq.getTitre());
        this.descriptionlabel.setText(eq.getDescription());
        this.IdItem.setText(String.valueOf(eq.getId()));


        Image imn = new Image(
                "file:/" + eq.getImg());
        imgItemProduit.setImage(imn);
        System.out.println("file:/" + eq.getImg());
    }




    @FXML
    private void Participants(ActionEvent event) {
        
      int  idEvent = parseInt(this.IdItem.getText());
       Evennement selectedEvent= new Evennement();
       selectedEvent.setId(idEvent);
       User userConnecter = new User(); 
       userConnecter.setIduser(3);
       Participant particperAEvent=new Participant(LocalDate.now(), parseInt(NumerParticipant.getText()), userConnecter ,selectedEvent);
        Participationservices participation=new Participationservices();
        participation.ajouter(particperAEvent);
        showAlert("Participation réussie", "Vous avez participé avec succès à l'événement.");
        
    }

    @FXML
    private void AnnulerParticipation(ActionEvent event) {
  
    
                   int  idEvent = parseInt(this.IdItem.getText());
        User userConnecter = new User(); 
       userConnecter.setIduser(1);

          Participationservices participation=new Participationservices();
     
          participation.supprimerMonParticipation(userConnecter.getIduser(),idEvent);
             showAlert("Annulation de la participation réussie", "Vous avez annulé votre participation avec succès."); 
    }
private void showAlert(String title, String content) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}







}
