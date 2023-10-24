/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tn.esprit.entities.User;
import tn.esprit.services.ServiceUser;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.esprit.entities.UserRole;
import tn.esprit.utils.Session;


/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Hyperlink forgotPasswordLink;
    @FXML
    private Hyperlink signupLink;
    private static User currentUser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          signupLink.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            // Code pour charger la page d'inscription
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
     forgotPasswordLink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadResetPasswordPage();
            }
      }); 
   }           
     @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        ServiceUser userService = new ServiceUser();
        User user = userService.authenticate(username, password);

        if (user != null) {
            // Authentification réussie
            
            Session.setCurrentUser(user);
            if (user.getRole() == UserRole.ADMIN) {
            loadBackOfficePage();
        } else {
            loadHomePage();
             showAlert("Authentification réussie", "Bienvenue, " + user.getUsername());
             
             //// the error is here as well 
        }
           
              // user is the authenticated user
        // Redirigez l'utilisateur vers la page d'accueil ou effectuez d'autres actions nécessaires
            // Redirigez l'utilisateur vers la page d'accueil ou effectuez d'autres actions nécessaires
        } else {
            // Authentification échouée, afficher un message d'erreur
            showAlert("Erreur d'authentification", "Nom d'utilisateur ou mot de passe incorrect.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private void loadSignUpPage() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
        Parent root = loader.load();

        // Créez une nouvelle fenêtre (Stage) pour afficher la page d'inscription
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Inscription");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
    private void loadHomePage() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent root = loader.load();
        HomeController homeController = loader.getController();
      //  homeController.initUser(user); // Initialisation des données de l'utilisateur
        Stage stage = (Stage) loginButton.getScene().getWindow();
        ///the error is here 
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
  
 private void loadBackOfficePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminMainForm.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  private void loadResetPasswordPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
    /*  private void loadAdminOptionPage() {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminOption.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
*/

