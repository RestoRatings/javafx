/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.entities.EtatRec;
import tn.esprit.entities.Reclamation;
import tn.esprit.entities.Role;
import tn.esprit.entities.TypeRec;
import tn.esprit.entities.User;
import tn.esprit.services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author mchai
 */
public class GestionRep2Controller implements Initializable {

    @FXML
    private DatePicker datefield;
    @FXML
    private TextField usernamefield;
    @FXML
    private ComboBox<TypeRec> Typerecfield;
    @FXML
    private Button addR;
  
    @FXML
    private TextField descriptionfield;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Typerecfield.getItems().setAll(TypeRec.values());
        
    }    

    @FXML
    private void add_rec(ActionEvent event) throws SQLException {
       String username = usernamefield.getText();
   
      LocalDate date = datefield.getValue();
      String description = descriptionfield.getText();
    TypeRec typeRec = Typerecfield.getValue();
 
if (username.isEmpty() || description.isEmpty()) {
        // Affichez une boîte de dialogue d'alerte
        showAlert("Erreur de saisie", "Veuillez remplir tous les champs obligatoires.");
        return;
    }
    
    
    if (username != null && date != null && description != null && typeRec != null ) {
        // Replace 'iduser' with the actual 'iduser' you have or use for the User
        int iduser = getUserIdByUsername(username); // Remplacez ceci par la manière correcte d'obtenir l'ID de l'utilisateur

        // Si iduser est égal à -1, cela signifie que l'utilisateur n'a pas été trouvé
        if (iduser != -1) {
            User user = new User(iduser);
               EtatRec etatRec = EtatRec.en_attente;
            // Créez un nouvel objet User avec l'ID d'utilisateur
            Reclamation newReclamation = new Reclamation(iduser, user, Date.valueOf(date),description, typeRec,etatRec);
             ServiceReclamation _serviceReclamation = new ServiceReclamation();
        _serviceReclamation.ajouter(newReclamation);
         usernamefield.clear();
        datefield.setValue(null);
        descriptionfield.clear();
        Typerecfield.setValue(null);
      
         } else {
                System.out.println("Error Ajout Reclamation : ");
            }
    }
    }
 private int getUserIdByUsername(String username) {
        List<User> userList = new ArrayList<>();
    userList.add(new User(1, "gg","ggm","kkk","pp","oo","hj",Role.admin));
   

    // Parcourez la liste des utilisateurs pour trouver l'utilisateur par nom d'utilisateur
    for (User user : userList) {
        if (user.getUsername().equals(username)) {
            // L'utilisateur a été trouvé, renvoyez son ID
            return user.getIduser();
        }
    }
        return -1; // Remplacez ceci par l'ID de l'utilisateur ou la logique appropriée
    }
 private void showAlert(String titre, String contenu) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(titre);
    alert.setHeaderText(null);
    alert.setContentText(contenu);
    alert.showAndWait();
}
}
   

