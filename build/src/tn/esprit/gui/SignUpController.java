/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.lang3.RandomStringUtils;
import tn.esprit.entities.User;
import tn.esprit.entities.UserRole;
import tn.esprit.services.ServiceUser;
import tn.esprit.utils.Datasource;
/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField firstNameField;
    @FXML
    private PasswordField passwordSignupField;
    @FXML
    private Button signUpButton;
    @FXML
    private Hyperlink loginHyperlink;
    @FXML
    private PasswordField confirmPasswordSignupField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameSignupField;
    @FXML
    private TextField telephoneField;
    @FXML
    private TextField addressField;
    @FXML
    private ComboBox roleComboBox;
     private Connection con;
    private PreparedStatement pre;
    private Statement ste;
    @FXML
    private TextField suggestedUsernameField;
    @FXML
    private Button SuggestionButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ObservableList <String> list = FXCollections.observableArrayList("CLIENT","EXPERT");
         roleComboBox.setItems(list);
          // Gestion du clic sur le lien "Already registered? Log in now"
        loginHyperlink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Code pour charger la page de connexion (login.fxml)
                loadLoginPage();
            }
        });
         
    }    
     @FXML
    void select(ActionEvent event) {
        String s =  roleComboBox.getSelectionModel().getSelectedItem().toString();
        
    }   

    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
         String username = usernameSignupField.getText();
        String password = passwordSignupField.getText();
        String confirmPassword = confirmPasswordSignupField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String telephone = telephoneField.getText();
        String address = addressField.getText();
      //  String role = roleComboBox.getValue().toString(); // Récupérer la valeur sélectionnée dans le ComboBox
        String role = roleComboBox.getValue() != null ? roleComboBox.getValue().toString() : null;

 // Vérifier si les mots de passe correspondent
        if (!password.equals(confirmPassword)) {
            showAlert("Erreur d'inscription", "Les mots de passe ne correspondent pas.");
            return;
        }
        if (username.isEmpty() && password.isEmpty() && confirmPassword.isEmpty() &&
        firstName.isEmpty() && lastName.isEmpty() && email.isEmpty() &&
        telephone.isEmpty() && address.isEmpty() && role == null) {
        showAlert("Erreur d'inscription", "Veuillez remplir Tous les champs obligatoires avant de vous inscrire.");
        return;
    }
        
         if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
        firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || role == null ) {
        showAlert("Erreur d'inscription", "Tous les champs obligatoires doivent être renseignés.");
         
        return;
    }
         if (!isValidEmail(email)) {
        showAlert("Erreur d'inscription", "L'adresse e-mail n'est pas valide.");
        return;
    }
          if (isUsernameTaken(username)) {
         //   showAlert("Erreur d'inscription", "Nom d'utilisateur déjà utilisé. Veuillez en choisir un autre.");
             String suggestedUsername = generateSuggestedUsername(username);
             showAlert("Username Taken", "The username is already in use. You can try a different one or use the suggested username: " + suggestedUsername);
              suggestedUsernameField.setText(suggestedUsername);
            usernameSignupField.clear();
            confirmPasswordSignupField.clear();
            return;
        }
          if (!isValidPassword(password)) {
        showAlert("Erreur d'inscription", "Le mot de passe doit avoir au moins 8 caractères et contenir au moins une majuscule.");
        passwordSignupField.clear();
        return;
    }
         
        UserRole type = "CLIENT".equals(roleComboBox.getValue()) ? UserRole.CLIENT : UserRole.EXPERT;

        // Créer un nouvel utilisateur
         // Créer un nouvel utilisateur avec les données du formulaire
         
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setTel(telephone);
        user.setAddress(address);
        user.setRole(type);
       //  String hashedPassword = BCrypt.hashpw(mdp, BCrypt.gensalt());
         //  User u = new User(Nom, Prenom, email, tel, hashedPassword);
        // Appeler la méthode d'ajout
        ServiceUser userService = new ServiceUser();
        userService.ajouter(user);

        // Affichez un message de confirmation
        showAlert("Inscription réussie", "Votre compte a été créé avec succès.");
         clearFields();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private void loadLoginPage() {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    private boolean isValidEmail(String email) {
    // Vous pouvez utiliser une expression régulière (regex) pour valider l'adresse e-mail.
    // Voici un exemple simple de regex pour une adresse e-mail :
    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

    return email.matches(emailRegex);
}
    private boolean isUsernameTaken(String username) {
        try {
            con = Datasource.getInstance().getCnx(); // Use the connection from Datasource
            String query = "SELECT * FROM user WHERE username = ?";
            pre = con.prepareStatement(query);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            return rs.next(); // Return true if username exists in the database, false otherwise
        } catch (SQLException ex) {
            showAlert("Erreur d'inscription", "Erreur lors de la vérification de l'unicité du nom d'utilisateur.");
            return false;
        }
    }
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

    private void clearFields() {
    // Clear all input fields and reset selections
    usernameSignupField.clear();
    passwordSignupField.clear();
    confirmPasswordSignupField.clear();
    firstNameField.clear();
    lastNameField.clear();
    emailField.clear();
    telephoneField.clear();
    addressField.clear();
    roleComboBox.getSelectionModel().clearSelection();
}
     private String generateSuggestedUsername(String username) {
        // Generate a random alphanumeric suffix of 3 characters
        String randomSuffix = RandomStringUtils.randomAlphanumeric(3);
        return username + randomSuffix;
    }

    @FXML
    private void useSuggestion(ActionEvent event) {
          usernameSignupField.setText(suggestedUsernameField.getText());
    }

}
    

