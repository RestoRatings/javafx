/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

//import gestion_hamdi.pdf;
import tn.esprit.entities.Restaurant;
import tn.esprit.services.Servicerestaurant;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hamdi
 */
public class AjouterRestauController implements Initializable {
      @FXML
    private DialogPane dialoguerestau;
      @FXML
    private TextField nomrestau;
    @FXML
    private TextField locationrestau;
       @FXML
    private TextField idrestaus;
            @FXML
    private ListView<Restaurant> listv;
    
     private ObservableList<Restaurant> restaurantlist = FXCollections.observableArrayList();
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                 loadInitialDataFromDatabase();
                 
    
    listv.setCellFactory(param -> new ListCell<Restaurant>() {//added19
        @Override
        protected void updateItem(Restaurant item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
            } else {
                setText("Nom: " + item.getNom() + ", Location: " + item.getlocation());
            }
        }
    });

    
        listv.setItems(restaurantlist);
    } // TODO
        
      private void loadInitialDataFromDatabase() {
          Servicerestaurant ps = new Servicerestaurant();
    List<Restaurant> initialrestaurant = ps.affihcer();
    
    // Populate circuitList with the initial data from the database
    restaurantlist.clear();
    restaurantlist.addAll(initialrestaurant);
}

        // TODO
        
    
     @FXML
    private void ajouterrestau(ActionEvent event) {
        String nom=nomrestau.getText();
        String location = locationrestau.getText();
         
        if (nom.isEmpty()||location.isEmpty()) {
            // Set the message on the Label
              showAlert("incomplete data ");
            
        
        } 
      
                
         Servicerestaurant sp = new Servicerestaurant();
        Restaurant p = new Restaurant( nom,location );
        sp.ajouter(p);
         List<Restaurant> updatedCircuits = sp.affihcer();
         
            
                // Specify the output file path where you want to save the PDF
    String outputPath = "restaurant_data.pdf";
    
    // Use the PDFGenerator class to generate the PDF
//    pdf.generateRestaurantPDF(updatedCircuits, outputPath);
    restaurantlist.clear();
    restaurantlist.addAll(updatedCircuits);

    // Set the ListView items to circuitList to reflect the updated data
    listv.setItems(restaurantlist);

    // Clear the TextFields after saving
    clearTextFields();
}@FXML
        private void modifierrestaus (ActionEvent event) {
   
    
    // Check if an item is selected
    listv.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {//added 19
            if (event.getClickCount() == 2) { // Double-click event
                Restaurant selectedRestaurant = listv.getSelectionModel().getSelectedItem();
                if (selectedRestaurant != null) {
                    // Populate the fields with the selected restaurant's data
                    int id = selectedRestaurant.getId();
                    String nom = selectedRestaurant.getNom();
                    String location = selectedRestaurant.getlocation();
                    
                    // Now set the individual fields with the retrieved data
                    idrestaus.setText(String.valueOf(id));
                    nomrestau.setText(nom);
                    locationrestau.setText(location);
                }
            }   }
    });
         /////
  
        String nom=nomrestau.getText();
        String location = locationrestau.getText();
        int id = Integer.parseInt( idrestaus.getText());
    if (nom.isEmpty()||location.isEmpty()) {
            // Set the message on the Label
              showAlert("incomplete data ");
            
        
        } 
                
         Servicerestaurant sp = new Servicerestaurant();
        Restaurant p = new Restaurant( id,nom,location );
        sp.modifier(p);
            clearTextFields();
            List<Restaurant> updatedCircuits = sp.affihcer();
            restaurantlist.clear();
    restaurantlist.addAll(updatedCircuits);
     // Refresh the ListView to reflect the changes
    listv.setItems(null); // Clear the items
    listv.setItems(restaurantlist); 
}
        @FXML
        private void supprimerrestau (ActionEvent event) {
 Restaurant selectedrestaurant = listv.getSelectionModel().getSelectedItem();

    // Check if an item is selected
    if (selectedrestaurant == null) {
        showAlert("no item selected");
        return;
    }

    // Remove the selected item from the database
    Servicerestaurant sp = new Servicerestaurant();
    sp.supprimer(selectedrestaurant);

    // Remove the selected item from the ListView
    restaurantlist.remove(selectedrestaurant);

    // Clear the TextFields after deleting
    clearTextFields();
                List<Restaurant> updatedCircuits = sp.affihcer();
            restaurantlist.clear();
    restaurantlist.addAll(updatedCircuits);
     // Refresh the ListView to reflect the changes
    listv.setItems(null); // Clear the items
    listv.setItems(restaurantlist); 
}
@FXML
public void goBackToManagement(ActionEvent event) {
    // Load the FXML file for the "restaurant management" scene
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminMainForm.fxml"));
    try {
        Parent root = loader.load();
        Scene managementScene = new Scene(root);

        // Get the current stage
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the scene of the stage to the "restaurant management" scene
        currentStage.setScene(managementScene);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    private void clearTextFields() {
           idrestaus.clear();
    nomrestau.clear();
    locationrestau.clear();
   
    }
    private void showAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
}
