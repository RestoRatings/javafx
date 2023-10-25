/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.entities.Reservation;
import tn.esprit.services.ServiceReservation;
import tn.esprit.entities.Restaurant;
import tn.esprit.services.Servicerestaurant;
/**
 * FXML Controller class
 *
 * @author Hamdi
 */
public class RestaurantUserController implements Initializable {
              @FXML
    private ListView<Restaurant> listresuser;
                      
               @FXML
    private TextField locationss;
    
     private ObservableList<Restaurant> restaurantlist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadInitialDataFromDatabase();
            listresuser.setCellFactory(param -> new ListCell<Restaurant>() {//added19
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

    
        listresuser.setItems(restaurantlist);
     
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
public void backrestauu(ActionEvent event) {
    // Load the FXML file for the "restaurant management" scene
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
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

private void filterListView(String location) {
    // Create a filtered list based on the provided location
    List<Restaurant> filteredList = restaurantlist.stream()
            .filter(restaurant -> restaurant.getlocation().contains(location))
            .collect(Collectors.toList());

    // Update the ListView with the filtered data
    listresuser.setItems(FXCollections.observableArrayList(filteredList));
}
@FXML
public void search(ActionEvent event) {
            
         
        String location = locationss.getText();
        filterListView(location);
    }


}
    

