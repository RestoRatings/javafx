/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.entities.EtatRec;
import tn.esprit.entities.Reclamation;
import tn.esprit.entities.UserRole;
import tn.esprit.entities.TypeRec;
import tn.esprit.entities.User;
import tn.esprit.services.ServiceReclamation;
import tn.esprit.utils.Session;
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
    @FXML
    private Button retourhomerec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Typerecfield.getItems().setAll(TypeRec.values());
         User currentUser = Session.getCurrentUser();


        // Mettez à jour l'interface graphique avec les informations de l'utilisateur
        if (currentUser != null) {
           
          usernamefield .setText(currentUser.getUsername());
    }    
    }
    @FXML
    private void add_rec(ActionEvent event) throws SQLException {
    User currentUser = Session.getCurrentUser();
    if (currentUser == null) {
        // Utilisateur non connecté, affichez une alerte ou effectuez le traitement approprié
        showAlert("Erreur", "Aucun utilisateur connecté.");
        return;
    }

    LocalDate date = datefield.getValue();
    String description = descriptionfield.getText();
    TypeRec typeRec = Typerecfield.getValue();

    if (description.isEmpty() || typeRec == null) {
        showAlert("Erreur de saisie", "Veuillez remplir la description et le typerec.");
        return;
    }

    EtatRec etatRec = EtatRec.en_attente;

    // Créez un nouvel objet Reclamation en utilisant les données de l'utilisateur actuellement connecté
    Reclamation newReclamation = new Reclamation(currentUser.getIduser(), currentUser, Date.valueOf(date), description, typeRec, etatRec);

    ServiceReclamation _serviceReclamation = new ServiceReclamation();
    _serviceReclamation.ajouter(newReclamation);

    usernamefield.clear();
    datefield.setValue(null);
    descriptionfield.clear();
    Typerecfield.setValue(null);
}

 private void showAlert(String titre, String contenu) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(titre);
    alert.setHeaderText(null);
    alert.setContentText(contenu);
    alert.showAndWait();
}
 private boolean contientGrosMot(String description) {
    List<String> grosMots = Arrays.asList("gros_mot1", "gros_mot2", "gros_mot3");

    for (String grosMot : grosMots) {
        if (description.toLowerCase().contains(grosMot)) {
            return true;
        }
    }

    return false;
}

    @FXML
    private void ret_rec_home(ActionEvent event) {
       
try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        
        
        
        
        
        
        
        
        
        
    }
}
   


