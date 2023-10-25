/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import EmailSender.EmailSender;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.services.ServiceUser;
import tn.esprit.utils.UserData;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ResetPasswordController implements Initializable {

   

    @FXML
    private TextField emailField;
    @FXML
    private Button resetButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
    private void resetPassword(ActionEvent event) throws IOException {
        if (emailField.getText().isEmpty()) { // Use getText to get the value from the TextField
            showAlert("Error", "Please enter your email address.");
            return;
        }
        String userEmail = emailField.getText(); // Use getText to get the value from the TextField
        // Generate a random reset code (you can use a library like Apache Commons Lang)
        String resetCode = generateRandomCode(); // Implement your code to generate the reset code
       if (!isValidEmail(userEmail)) {
        showAlert("Erreur de saisie", "L'adresse e-mail n'est pas valide.");
       return ;
    }
    
        ServiceUser userService = new ServiceUser(); // Create an instance of your ServiceUser

        if (!userService.isEmailRegistered(userEmail)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
               alert.setHeaderText("Email not found");
           alert.setContentText("The email you entered is not registered.");
           alert.showAndWait();
             return; // Return or handle the case where the email is not registered
}

        // Send the reset email
        boolean emailSent = EmailSender.sendPasswordResetEmail(userEmail, resetCode);

        if (emailSent) {
            showAlert("Password Reset", "An email with a password reset link has been sent to your email.");
             UserData userData = UserData.getInstance();
        userData.setUserEmail(userEmail);
        userData.setResetCode(resetCode);

        openCodeConfirmationPage();
        } else {
            showAlert("Password Reset", "Failed to send the reset email. Please try again.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    String generateRandomCode() {
    Random rand = new Random();
    int randomInt = rand.nextInt(10000); // Generates a random integer between 0 and 9999
    return String.format("%04d", randomInt); // Format it as a 4-digit string with leading zeros
}
    private boolean isValidEmail(String email) {
    // Vous pouvez utiliser une expression régulière (regex) pour valider l'adresse e-mail.
    // Voici un exemple simple de regex pour une adresse e-mail :
    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

    return email.matches(emailRegex);
}
    @FXML
private void openCodeConfirmationPage() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Codeconfirmation.fxml"));
    Parent codeConfirmationParent = loader.load();

   // CodeConfirmationController codeConfirmationController = loader.getController();
   // codeConfirmationController.setUserEmail(userEmail);
  //  codeConfirmationController.setResetCode(resetCode);

    Scene codeConfirmationScene = new Scene(codeConfirmationParent);

    // Get the current stage
    Stage stage = (Stage) resetButton.getScene().getWindow();

    // Set the new scene on the stage
    stage.setScene(codeConfirmationScene);
}
}