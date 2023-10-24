/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import tn.esprit.entities.User;
import tn.esprit.services.ServiceUser;
import tn.esprit.utils.Session;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class HomeController implements Initializable {
 private Stage stage;
    private Stage scene;
    @FXML
    private AnchorPane main_form;
    @FXML
    private Circle top_profile;
    @FXML
    private Label top_username;
    @FXML
    private Button logout_btn;
    @FXML
    private Label nav_username;
    @FXML
    private Button home_btn;
    @FXML
    private Button plat_btn;
    @FXML
    private Button evenement_btn;
    @FXML
    private Button restaurant_btn;
    @FXML
    private Button reclamation_btn;
    @FXML
    private Button profile_btn;
    @FXML
    private Button avis_btn;
    @FXML
    private Button avis_btn_expert;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         User currentUser = Session.getCurrentUser();


        // Mettez à jour l'interface graphique avec les informations de l'utilisateur
        if (currentUser != null) {
           
            top_username.setText(currentUser.getUsername());
            nav_username.setText(currentUser.getUsername());
        }
    }    
     

   
     @FXML
private void handleLogout(ActionEvent event) {
    // Create a confirmation dialog
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Logout Confirmation");
    alert.setHeaderText("Are you sure you want to log out?");
    alert.setContentText("Choose your option:");

    // Add "OK" and "Cancel" buttons to the dialog
    ButtonType confirmButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
    ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
    alert.getButtonTypes().setAll(confirmButtonType, cancelButtonType);

    Optional<ButtonType> result = alert.showAndWait();

    if (result.isPresent() && result.get() == confirmButtonType) {
        // User chose to log out
        loadLoginPage();
    }
}

private void loadLoginPage() {
    // Implement logic to open the login page window or stage
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();

        // Create a new stage for the login page
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.show();

        // Close the current window or stage
        Stage currentStage = (Stage) logout_btn.getScene().getWindow();
        currentStage.close();
    } catch (IOException e) {
        e.printStackTrace();
    }}
    @FXML
    private void switchForm(ActionEvent event) {
    }
    @FXML
private void profileSetting(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Profil.fxml"));
        Parent root = loader.load();
        ProfilController profilController = loader.getController();
        
        // Pass the user data from Home to Profil
        

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Profil");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    private void EVENTHOME(ActionEvent event) {
        
     try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("ImgWithEvent.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
           
    }

    @FXML
    private void gotoPlat(ActionEvent event) {
          try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("PlatWithImg.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
           
    }

    @FXML
    private void gotoavis(ActionEvent event) {
         try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("GestionAvis.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        
    }
    ServiceUser serviceuser =new ServiceUser();
    /////////// User currentUser = userService.getById(Session.getInstance().getCurrentUser().getIduser() );

   @FXML
private void goToBadge(ActionEvent event) {
    // Vous pouvez maintenant utiliser le bouton en toute sécurité ici.
           try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("GestionBadge.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}
    

    
