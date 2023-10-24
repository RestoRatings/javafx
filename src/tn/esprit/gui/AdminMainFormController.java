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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.services.ServiceUser;
import tn.esprit.entities.User;
import tn.esprit.entities.UserRole;
import tn.esprit.utils.Datasource;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AdminMainFormController implements Initializable {
 private Stage stage;
    private Stage scene;
    @FXML
    private Label greet_username;
    @FXML
    private Button btnDash;
    @FXML
    private Button dashTestButton ;
    
    @FXML
    private Button btnUser;
    @FXML
    private Button add_buton_dash;
    @FXML
    private Button update_button_dash;
    @FXML
    private Button clea_button_dash;
    @FXML
    private Button delete_button_dash;
    @FXML
    private TextField firstNameFielddasha;
    @FXML
    private TextField lastNameFielddash;
    @FXML
    private TextField usernamedashfield;
    @FXML
    private TextField emailDashField;
    @FXML
    private TextField passwordDashField;
    @FXML
    private TextField numberDashField;
    @FXML
    private TextField confirPasswordDashField;
    @FXML
    private TextField AddressDashField;
    @FXML
    private ComboBox  roleDashComboBox;
    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableColumn<?, ?> userIdColumn;
    @FXML
    private TableColumn<?, ?> firstNameColumn;
    @FXML
    private TableColumn<?, ?> lastNameColumn;
    @FXML
    private TableColumn<?, ?> userNameColumn;
    @FXML
    private TableColumn<?, ?> emailColumn;
    @FXML
    private TableColumn<?, ?> passwordColumn;
    @FXML
    private TableColumn<?, ?> NumberColumn;
    @FXML
    private TableColumn<?, ?> addressColumn;
    @FXML
    private TableColumn<?, ?> roleColumn;
    private TextField searchUsernameField;    

    /**
     * Initializes the controller class.
     */
     private ServiceUser userService;
    private int selectedUserIndex = -1;
    private Connection con;
    private PreparedStatement pre;
    private Statement ste;
    @FXML
    private Button refreshButton;
    @FXML
    private ComboBox<String> searchAttributeComboBox;

    @FXML
    private TextField searchValueTextField;
    @FXML
    private Button advancedSearchButton;
    @FXML
    private Button dashTestButton1;
    @FXML
    private Button recadminRECLATION;
    
    public void loadUserData() {
    ServiceUser serviceUser = new ServiceUser();
    List<User> users = serviceUser.afficherTous();
    ObservableList<User> userObservableList = FXCollections.observableArrayList(users);
    userTableView.setItems(userObservableList);
}
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // In your initialize method, set up the ComboBox with available search attributes
    ObservableList<String> searchAttributes = FXCollections.observableArrayList(
    "username", "firstName", "lastName", "email", "address", "role"
      );
           searchAttributeComboBox.setItems(searchAttributes);

// Add a listener for the advanced search button
          
        
        
        
       dashTestButton.setOnAction(event -> chartsSetting());
        // Initialisez ici les autres éléments si nécessaire
         ObservableList <String> list = FXCollections.observableArrayList("CLIENT", "EXPERT", "ADMIN");
         roleDashComboBox.setItems(list);
         delete_button_dash.setOnAction(event -> deleteSelectedUser());
    //     searchButton.setOnAction(event -> searchByUsername(event));
        userService = new ServiceUser();

        // Configurez les CellValueFactory pour chaque colonne en utilisant PropertyValueFactory
     //   userIdColumn.setCellValueFactory(new PropertyValueFactory<>("iduser"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        NumberColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        // Chargez les données des utilisateurs dans la TableView
        loadUserData();
          // Add a listener to handle user selection in the TableView
        userTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Update the text fields with the selected user's data
                User selectedUser = newValue;
                firstNameFielddasha.setText(selectedUser.getFirstName());
                lastNameFielddash.setText(selectedUser.getLastName());
                usernamedashfield.setText(selectedUser.getUsername());
                emailDashField.setText(selectedUser.getEmail());
                passwordDashField.setText(selectedUser.getPassword());
                numberDashField.setText(selectedUser.getTel());
                AddressDashField.setText(selectedUser.getAddress());
                roleDashComboBox.setValue(selectedUser.getRole());
                selectedUserIndex = userTableView.getSelectionModel().getSelectedIndex();
                 }
        });
     }
     @FXML
    void selected(ActionEvent event) {
        String s =  roleDashComboBox.getSelectionModel().getSelectedItem().toString();
        
    }   

    
    
    @FXML
    private void addUser(ActionEvent event) {
         String username = usernamedashfield.getText();
        String password = passwordDashField.getText();
        String confirmPassword = confirPasswordDashField.getText();
        String firstName = firstNameFielddasha.getText();
        String lastName = lastNameFielddash.getText();
        String email = emailDashField.getText();
        String telephone = numberDashField.getText();
        String address = AddressDashField.getText();
        //String role = roleDashComboBox.getValue().toString() ; // Récupérer la valeur sélectionnée dans le ComboBox
         String role = roleDashComboBox.getValue() != null ? roleDashComboBox.getValue().toString() : null;
 // Vérifier si les mots de passe correspondent
        if (!password.equals(confirmPassword)) {
            showAlert("Erreur d'inscription", "Les mots de passe ne correspondent pas.");
            return;
        }
         if (username.isEmpty() && password.isEmpty() && confirmPassword.isEmpty() &&
        firstName.isEmpty() && lastName.isEmpty() && email.isEmpty() &&
        telephone.isEmpty() && address.isEmpty() && role == null) {
        showAlert("Erreur d'inscription", "Veuillez remplir Tous les champs obligatoires afin d'ajouter un utilisateur.");
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
                  UserRole type;

                        if ("CLIENT".equals(roleDashComboBox.getValue())) {
                                         type = UserRole.CLIENT;
                                } else if ("EXPERT".equals(roleDashComboBox.getValue())) {
                                             type = UserRole.EXPERT;
                                } else {
                                             type = UserRole.ADMIN;
}      
          if (!isValidEmail(email)) {
        showAlert("Erreur d'inscription", "L'adresse e-mail n'est pas valide.");
        return;
    }
          if (isUsernameTaken(username)) {
            showAlert("Erreur d'inscription", "Nom d'utilisateur déjà utilisé. Veuillez en choisir un autre.");
            usernamedashfield.clear();
            
            return;
        }
          if (!isValidPassword(password)) {
        showAlert("Erreur d'inscription", "Le mot de passe doit avoir au moins 8 caractères et contenir au moins une majuscule.");
        passwordDashField.clear();
        return;
    }

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

        // Appeler la méthode d'ajout
        ServiceUser userService = new ServiceUser();
        userService.ajouter(user);

        // Affichez un message de confirmation
        showAlert("Inscription réussie", "Votre compte a été créé avec succès.");
        loadUserData();
    }
    @FXML
    private void updateUser(ActionEvent event) {
        UserRole type;

                        if ("CLIENT".equals(roleDashComboBox.getValue())) {
                                         type = UserRole.CLIENT;
                                } else if ("EXPERT".equals(roleDashComboBox.getValue())) {
                                             type = UserRole.EXPERT;
                                } else {
                                             type = UserRole.ADMIN;
}      
        // Handle the event when the Update button is clicked
        if (selectedUserIndex >= 0) {
            // Get the selected user from the TableView
            User selectedUser = userTableView.getSelectionModel().getSelectedItem();
            
             if (!selectedUser.getUsername().equals(usernamedashfield.getText()) && isUsernameTaken(usernamedashfield.getText())) {
            showAlert("Input Error", "Username already taken. Please choose a different username.");
            return;
        }
            // Update the selected user's data with the data from text fields and ComboBox
            selectedUser.setUsername(usernamedashfield.getText());
            selectedUser.setEmail(emailDashField.getText());
            selectedUser.setPassword(passwordDashField.getText());
            selectedUser.setFirstName(firstNameFielddasha.getText());
            selectedUser.setLastName(lastNameFielddash.getText());
            selectedUser.setTel(numberDashField.getText());
            selectedUser.setAddress(AddressDashField.getText());
            selectedUser.setRole(type);
            
            
             if (validateUserInput()) {
            // Call the service to update the user
            userService.modifier(selectedUser);
            loadUserData();
            // Reload the user data in the TableView
         //  loadUserData();
             

            // Call the service to update the user
          //  userService.modifier(selectedUser);

            // Reload the user data in the TableView
             
            // Show a success alert
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Success");
        successAlert.setHeaderText("User Updated Successfully");
        successAlert.setContentText("The user has been updated successfully.");
        successAlert.showAndWait();
             }
        } else {
            // Show an alert that no user is selected for updating
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No User Selected");
            alert.setContentText("Please select a user to update.");
            alert.showAndWait();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
    @FXML
    public void deleteSelectedUser() {
    User selectedUser = userTableView.getSelectionModel().getSelectedItem();

    if (selectedUser != null) {
        // Create a confirmation alert
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Confirm Deletion");
        confirmationAlert.setContentText("Are you sure you want to delete this user?");

        // Show the confirmation dialog and wait for the user's response
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User confirmed the deletion, proceed with deleting the user

            // Remove the selected user from the TableView
            userTableView.getItems().remove(selectedUser);

            // Delete the user from the database
            userService.supprimer(selectedUser.getIduser()); // Use the ID to delete the user
             Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText("User Deleted Successfully");
            successAlert.setContentText("The user has been deleted successfully.");
            successAlert.showAndWait();
        }
         } else {
        // Affichez une alerte indiquant qu'aucun utilisateur n'a été sélectionné pour la suppression
        showAlert("Error", "No User Selected for Deletion");
    }
}
    


   @FXML
private void clearFields(ActionEvent event) {
    firstNameFielddasha.clear();
    lastNameFielddash.clear();
    usernamedashfield.clear();
    emailDashField.clear();
    passwordDashField.clear();
    numberDashField.clear();
    confirPasswordDashField.clear();
    AddressDashField.clear();
    roleDashComboBox.getSelectionModel().clearSelection(); // Clear the selected item in the ComboBox
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
    private boolean validateUserInput() {
    String username = usernamedashfield.getText();
    String password = passwordDashField.getText();
    String confirmPassword = confirPasswordDashField.getText();
    String firstName = firstNameFielddasha.getText();
    String lastName = lastNameFielddash.getText();
    String email = emailDashField.getText();
    String telephone = numberDashField.getText();
    String address = AddressDashField.getText();
    String role = roleDashComboBox.getValue().toString();

    // Vérifier si les mots de passe correspondent
    if (selectedUserIndex < 0) {
        showAlert("Input Error", "Please select a user to update.");
         return false;
    }
    if (!password.equals(confirmPassword)) {
        showAlert("Erreur de saisie", "Les mots de passe ne correspondent pas.");
        return false;
    }

    if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
        firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || role == null) {
        showAlert("Erreur de modification", "Tous les champs obligatoires doivent être renseignés.");
        return false;
    }

    if (!isValidEmail(email)) {
        showAlert("Erreur de saisie", "L'adresse e-mail n'est pas valide.");
        return false;
    }
     
    if (!isValidPassword(password)) {
        showAlert("Erreur de saisie", "Le mot de passe doit avoir au moins 8 caractères et contenir au moins une majuscule.");
        passwordDashField.clear();
        return false;
    }

    return true; // La saisie est valide
}
     private void chartsSetting() {
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Charts.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

     }

   // @FXML
  //  private void searchByUsername(ActionEvent event) {
    //     String searchUsername = searchUsernameField.getText().trim();

  //  if (searchUsername.isEmpty()) {
   //     showAlert("Input Error", "Please enter a username to search for.");
  //      return;
  //  }

 //   List<User> searchResults = userService.searchByUsername(searchUsername);

 //   if (searchResults.isEmpty()) {
     //   showAlert("Search Results", "No users found with the provided username.");
  //  } else {
     //   ObservableList<User> searchResultsList = FXCollections.observableArrayList(searchResults);
     //   userTableView.setItems(searchResultsList);
          
       
   // }
    
  //  }

    @FXML
    private void refreshTable(ActionEvent event) {
    loadUserData();
    searchUsernameField.clear();
    }

    @FXML
    private void performAdvancedSearch(ActionEvent event) {
          String searchAttribute = searchAttributeComboBox.getValue();
    String searchValue = searchValueTextField.getText().trim();

    if (searchAttribute == null || searchValue.isEmpty()) {
        showAlert("Input Error", "Please select a search attribute and enter a search value.");
        return;
    }

    List<User> searchResults = userService.advancedSearch(searchAttribute, searchValue);

    // Update the TableView with the search results
    ObservableList<User> searchResultsList = FXCollections.observableArrayList(searchResults);
    userTableView.setItems(searchResultsList);
    }

    @FXML
    private void ADMINEVENTadd(ActionEvent event) {
          try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("addevnt.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
           
    }

    @FXML
    private void gotoPlatAdmin(ActionEvent event) {
         try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("AjouterPlat.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
           
    }

    @FXML
    private void gotoAchatAdmin(ActionEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("AchatFXML.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gotoAvisAdmin(ActionEvent event) {
          try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("BackGestionAvis.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gotoBadgeAdmin(ActionEvent event) {
          try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("BackGestionBadge.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void reclamationbtnaffich(ActionEvent event) {
         try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("GestionReclamation.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}


    
    


/*
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList <String> list = FXCollections.observableArrayList("CLIENT","EXPERT");
         roleDashComboBox.setItems(list);
        userService = new ServiceUser();
        configureTableView();

        userTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                User selectedUser = userTableView.getSelectionModel().getSelectedItem();
                displayUserDetails(selectedUser);
                selectedUserIndex = userTableView.getSelectionModel().getSelectedIndex();
            }
        });

        loadUserData();
    }

    private void configureTableView() {
        // Configurez les CellValueFactory pour chaque colonne
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("iduser"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        NumberColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
    }

    private void displayUserDetails(User user) {
        if (user != null) {
            firstNameFielddasha.setText(user.getFirstName());
            lastNameFielddash.setText(user.getLastName());
            usernamedashfield.setText(user.getUsername());
            emailDashField.setText(user.getEmail());
            passwordDashField.setText(user.getPassword());
            numberDashField.setText(user.getTel());
            AddressDashField.setText(user.getAddress());
            roleDashComboBox.setValue(user.getRole().toString());
        } else {
            clearFields();
        }
    }

    private void clearFields() {
        firstNameFielddasha.clear();
        lastNameFielddash.clear();
        usernamedashfield.clear();
        emailDashField.clear();
        passwordDashField.clear();
        numberDashField.clear();
        AddressDashField.clear();
        roleDashComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void addOrUpdateUser(ActionEvent event) {
        String username = usernamedashfield.getText();
        String password = passwordDashField.getText();
        String firstName = firstNameFielddasha.getText();
        String lastName = lastNameFielddash.getText();
        String email = emailDashField.getText();
        String tel = numberDashField.getText();
        String address = AddressDashField.getText();
        String role = roleDashComboBox.getValue();

        if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() ||
            email.isEmpty() || tel.isEmpty() || address.isEmpty() || role == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez remplir tous les champs.");
            return;
        }

        UserRole userRole = UserRole.valueOf(role);
        User newUser = new User(username, email, password, firstName, lastName, tel, address, userRole);

        if (selectedUserIndex == -1) {
            userService.ajouter(newUser);
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Succès", "Utilisateur ajouté avec succès.");
        } else {
            int userId = userTableView.getItems().get(selectedUserIndex).getIduser();
            newUser.setIduser(userId);
            userService.modifier(newUser);
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Succès", "Utilisateur mis à jour avec succès.");
        }

        clearFields();
        loadUserData();
    }

    @FXML
    private void deleteUser(ActionEvent event) {
        if (selectedUserIndex == -1) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Erreur", "Sélectionnez un utilisateur à supprimer.");
            return;
        }

        int userId = userTableView.getItems().get(selectedUserIndex).getIduser();
        userService.supprimer(userId);
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Succès", "Utilisateur supprimé avec succès.");
        clearFields();
        loadUserData();
    }

    private void loadUserData() {
        List<User> users = userService.afficher
                }
    @FXML
    private void selectcombo(ActionEvent event) {
        String s =  roleDashComboBox.getSelectionModel().getSelectedItem().toString();
    }
    }
    
}
*/