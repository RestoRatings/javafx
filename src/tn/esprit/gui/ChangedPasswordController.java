/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import tn.esprit.services.ServiceUser;
import tn.esprit.utils.UserData;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ChangedPasswordController implements Initializable {

    @FXML
    private PasswordField NewPasswordChangeField;
    @FXML
    private PasswordField ConfirmPasswordChangeField;
    @FXML
    private Button passwordChangeButton;
    
     ServiceUser serviceUser = new ServiceUser(); // Create an instance of your ServiceUser


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
    private void changePassword(ActionEvent event) {
        String newPassword = NewPasswordChangeField.getText();
        String confirmPassword = ConfirmPasswordChangeField.getText();
          if (!isValidPassword(newPassword)) {
        showAlert("Erreur d'inscription", "Le mot de passe doit avoir au moins 8 caractères et contenir au moins une majuscule.", "try again");
        NewPasswordChangeField.clear();
        ConfirmPasswordChangeField.clear();
        return;
    }
        if (!newPassword.equals(confirmPassword)) {
            showAlert("Warning", "Passwords don't match", "Please make sure the new passwords match.");
        } else {
            // You should have the user's email or some unique identifier to update the password
             UserData userData = UserData.getInstance();
             String userEmail = userData.getUserEmail();
           
            serviceUser.updateUserPasswordByEmail(userEmail, newPassword);
            showAlert("Success", "Password changed", "Your password has been updated.");
                        loadLoginPage();

        }
    }

    private void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
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

       
    } catch (IOException e) {
        e.printStackTrace();
    }}
    
     private boolean isValidPassword(String password) {
    // Check if the password is at least 8 characters long
    if (password.length() < 8) {
        return false;
    }

    // Check if the password contains at least one uppercase letter
    if (!password.matches(".*[A-Z].*")) {
        return false;
    }

    return true;
}
}

