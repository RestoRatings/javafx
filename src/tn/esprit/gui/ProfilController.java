/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.entities.User;
import tn.esprit.services.IServiceUser;
import tn.esprit.services.ServiceUser;
import tn.esprit.utils.Datasource;
import tn.esprit.utils.Session;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ProfilController implements Initializable {

    @FXML
    private TextField firstNameProfilField;
    @FXML
    private PasswordField newPasswordProfilField;
    @FXML
    private Button editProfilSaveButton;
    @FXML
    private TextField EmailProfilField;
    @FXML
    private TextField lastNameProfilField;
    @FXML
    private TextField AdressProfilField;
    @FXML
    private TextField usernameProfilField;
    @FXML
    private TextField NumberProfilField;
    @FXML
    private PasswordField ConfirmPasswordProfilField;
    @FXML
    private PasswordField oldPasswordProfilField;
     private Connection con;
    private PreparedStatement pre;
    private Statement ste;
    
    
    private ServiceUser userService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceUser userService = new ServiceUser();
         User currentUser = userService.getById(Session.getInstance().getCurrentUser().getIduser() );
        // Set the user data in the fields
        firstNameProfilField.setText(currentUser.getFirstName());
        lastNameProfilField.setText(currentUser.getLastName());
        usernameProfilField.setText(currentUser.getUsername());
        EmailProfilField.setText(currentUser.getEmail());
        NumberProfilField.setText(currentUser.getTel() );
        AdressProfilField.setText(currentUser.getAddress());
        // Set other fields as needed
    }    

    
    // Add a method to set user data
   
    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
    // Get the user input
    String newFirstName = firstNameProfilField.getText();
    String newUserName = usernameProfilField.getText();

    String newLastName = lastNameProfilField.getText();
    String newEmail = EmailProfilField.getText();
    String newTel = NumberProfilField.getText();
    String newAddress = AdressProfilField.getText();
    String oldPassword = oldPasswordProfilField.getText();
    String newPassword = newPasswordProfilField.getText();
    String confirmPassword = ConfirmPasswordProfilField.getText();
    ServiceUser userService = new ServiceUser();
         User currentUser = userService.getById(Session.getInstance().getCurrentUser().getIduser() );
    // Check if the old password matches the user's current password
    // Check if the old password matches the current password
       if (oldPassword.equals(currentUser.getPassword()) || oldPassword.isEmpty()) {
    // The old password matches or is not provided, proceed with updating user data
        
    // Update the user object with the new data
     if (newUserName.isEmpty() ||  newFirstName.isEmpty() ||
        newLastName.isEmpty() || newEmail.isEmpty()   ) {
        showAlert("Erreur de modification", "Tous les champs obligatoires doivent être renseignés.");
         
        return;
    }
         if (!isValidEmail(newEmail)) {
        showAlert("Erreur de modification", "L'adresse e-mail n'est pas valide.");
        return;
    }
        if (newFirstName.equals(currentUser.getFirstName()) &&
    newUserName.equals(currentUser.getUsername()) &&
    newLastName.equals(currentUser.getLastName()) &&
    newEmail.equals(currentUser.getEmail()) &&
    newAddress.equals(currentUser.getAddress()) &&
    newTel.equals(currentUser.getTel()) &&
    (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty())) {
    showAlert("Aucune modification", "Vous n'avez apporté aucune modification.");
    return;
}

        //  if (isUsernameTaken(newUserName)) {
       //     showAlert("Erreur de modification", "Nom d'utilisateur déjà utilisé. Veuillez en choisir un autre.");
       //     usernameProfilField.clear();
       //     
       //     return;
      //  }

    currentUser.setFirstName(newFirstName);
    currentUser.setLastName(newLastName);
   currentUser.setUsername(newUserName);
    currentUser.setEmail(newEmail);
    currentUser.setTel(newTel);
    currentUser.setAddress(newAddress);
     // Check if a new password is provided
     
    if (!oldPassword.isEmpty()) {
        // Check if the new password and confirm password match
        if (newPassword.equals(confirmPassword)) {
          //   Set the new password
          if (!isValidPassword(newPassword)) {
        showAlert("Erreur de modification", "Le mot de passe doit avoir au moins 8 caractères et contenir au moins une majuscule.");
        oldPasswordProfilField.clear();
        newPasswordProfilField.clear();
        ConfirmPasswordProfilField.clear();
        return;
    }
            currentUser.setPassword(newPassword);
        } else {
            showAlert("Error", "New password and confirm password do not match.");
            
        }
    }
    
    // Save the updated user to the database using the modifier method of serviceUser
    userService.modifier(currentUser);

    // Display a success alert to the user
    showAlert("Success", "User data updated successfully.");
 
    }
       else {
            showAlert("Error", "old password incorrect");
            
        }
    }
// A helper method to display alerts
       private void showAlert(String title, String content) {
       Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle(title);
               alert.setHeaderText(null);
           alert.setContentText(content);
            alert.showAndWait();
}
        private boolean isValidEmail(String email) {
    // Vous pouvez utiliser une expression régulière (regex) pour valider l'adresse e-mail.
    // Voici un exemple simple de regex pour une adresse e-mail :
    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

    return email.matches(emailRegex);
}
   // private boolean isUsernameTaken(String username) {
    //    try {
     //       con = Datasource.getInstance().getCnx(); // Use the connection from Datasource
     //       String query = "SELECT * FROM user WHERE username = ?";
    //        pre = con.prepareStatement(query);
    //        pre.setString(1, username);
     //       ResultSet rs = pre.executeQuery();
     //       return rs.next(); // Return true if username exists in the database, false otherwise
    //    } catch (SQLException ex) {
    //        showAlert("Erreur d'inscription", "Erreur lors de la vérification de l'unicité du nom d'utilisateur.");
      //      return false;
    //    }
  //  }
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
