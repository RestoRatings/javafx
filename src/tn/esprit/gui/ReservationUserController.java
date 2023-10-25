/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

//import com.sun.xml.internal.bind.IDResolver;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import tn.esprit.entities.Reservation;
import tn.esprit.services.ServiceReservation;
import tn.esprit.entities.Restaurant;
import tn.esprit.services.Servicerestaurant;

/**
 * FXML Controller class
 *
 * @author Hamdi
 */
public class ReservationUserController implements Initializable {
 @FXML
    private  DatePicker date;
     @FXML
    private ComboBox<String> hourres;

    @FXML
    private ComboBox<String> minuteres;
    @FXML
 private ListView<Restaurant> restaurantres;
           @FXML
    private TextField idres;
    
     private ObservableList<Restaurant> restaurantlist = FXCollections.observableArrayList();
                @FXML
    private ListView<Reservation> listresu;
    
     private ObservableList<Reservation> reservationlist = FXCollections.observableArrayList();
     
  private Restaurant selectedrestaurants;
    public void setSelectedRestaurant(Restaurant selectedRestaurant) {
        this.selectedrestaurants = selectedRestaurant;
    }
    
     /* Initializes the controller class.
     */
  
    
      
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
             loadInitialDataFromDatabase();
                restaurantres.setCellFactory(param -> new ListCell<Restaurant>() {//added19
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

        restaurantres.setItems(restaurantlist);
                  loadInitialDataFromDatabase();
                   listresu.setCellFactory(param -> new ListCell<Reservation>() {
        @Override
        protected void updateItem(Reservation item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
            } else {
                setText(
                         " | Date: " + item.getDatereser() +
                         " | Time: " + item.getTimereser() +
                         " | Restaurant Name: " + item.getRestau().getNom() +
                         " | Restaurant Location: " + item.getRestau().getlocation());
            }
        }
    });

        listresu.setItems(reservationlist);
        // TODO
       
         for (int i = 0; i <= 23; i++) {
            String hour = String.format("%02d", i);
            hourres.getItems().add(hour);
        }

        // Populate the minute ComboBox with different minute values (e.g., 00 to 59).
        for (int i = 0; i <= 59; i++) {
            String minute = String.format("%02d", i);
            minuteres.getItems().add(minute);
        }
        System.out.println(selectedrestaurants);
    }
   
          private void loadInitialDataFromDatabase() {
          Servicerestaurant ps = new Servicerestaurant();
    List<Restaurant> initialrestaurant = ps.affihcer();
    
    // Populate circuitList with the initial data from the database
    restaurantlist.clear();
    restaurantlist.addAll(initialrestaurant);
       
          ServiceReservation res = new ServiceReservation();
    List<Reservation> initialresList = res.affihcer();
    
    // Populate circuitList with the initial data from the database
    reservationlist.clear();
    reservationlist.addAll(initialresList);
    
}
          
       
    @FXML
    public void addres (ActionEvent event) {
     
            LocalDate selectedDate = date.getValue();
            System.out.println("Selected Date: " + selectedDate);
     
    
            String selectedHour = hourres.getValue();
        String selectedMinutes = minuteres.getValue();
           String selectedTime = selectedHour + ":" + selectedMinutes;
        System.out.println(selectedTime);
                           if (selectedDate == null || selectedHour == null || selectedMinutes == null ) {
            // Show an alert with the custom message
            showAlert("incomplete data ");
            return; // Exit the method to prevent further execution
        }
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        
            // Parse the string into a LocalTime
            LocalTime localTime = LocalTime.parse(selectedTime, formatter);
        
         Restaurant selectedrestaurant = restaurantres.getSelectionModel().getSelectedItem();
         ServiceReservation res = new ServiceReservation();
          Reservation r = new Reservation(1, selectedDate, localTime, selectedrestaurant);
          res.ajouter(r);
            List<Reservation> updatedCircuits = res.affihcer();
            // Update circuitList with the new data
    reservationlist.clear();
    reservationlist.addAll(updatedCircuits);

    // Set the ListView items to circuitList to reflect the updated data
    listresu.setItems(reservationlist);
          

        // Combine the hour and minutes into a time variable.
     
}
 @FXML
            public void modres (ActionEvent event){
                  listresu.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {//added 19
            if (event.getClickCount() == 2) { // Double-click event
                Reservation selectedReservation = listresu.getSelectionModel().getSelectedItem();
                if (selectedReservation != null) {
                    // Populate the fields with the selected restaurant's data
                    int id = selectedReservation.getId();
                            // Define the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Format the LocalDate as a string
        String formattedDate = selectedReservation.getDatereser().format(formatter);
                  
        DateTimeFormatter formattert = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Format the LocalTime as a string
        String formattedTime = selectedReservation.getTimereser().format(formattert);
                 String[] parts = formattedTime.split(":"); // Split the string using ":"
              String hour = parts[0]; // "22"
              String minute = parts[1]; // "00"
      
                      
                    
                    // Now set the individual fields with the retrieved data
                    idres.setText(String.valueOf(id));
                    date.setValue(selectedReservation.getDatereser());
                    hourres.setValue(hour);
                    minuteres.setValue(minute);
                          
                }
            }   }
    });
            LocalDate selectedDate = date.getValue();
            System.out.println("Selected Date: " + selectedDate);
     
    
            String selectedHour = hourres.getValue();
        String selectedMinutes = minuteres.getValue();
           String selectedTime = selectedHour + ":" + selectedMinutes;
        System.out.println(selectedTime);
                           if (selectedDate == null || selectedHour == null || selectedMinutes == null ) {
            // Show an alert with the custom message
            showAlert("incomplete data ");
            return; // Exit the method to prevent further execution
        }
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        
            // Parse the string into a LocalTime
            LocalTime localTime = LocalTime.parse(selectedTime, formatter);
             int id = Integer.parseInt( idres.getText());
        
         Restaurant selectedrestaurant = restaurantres.getSelectionModel().getSelectedItem();
         ServiceReservation res = new ServiceReservation();
          Reservation r = new Reservation(id, selectedDate, localTime, selectedrestaurant);
          res.modifier(r);
                     List<Reservation> updatedCircuits = res.affihcer();
            // Update circuitList with the new data
    reservationlist.clear();
    reservationlist.addAll(updatedCircuits);

    // Set the ListView items to circuitList to reflect the updated data
    listresu.setItems(reservationlist);
          
             

}
 @FXML
    public void suppres (ActionEvent event) {
 Reservation selectedres = listresu.getSelectionModel().getSelectedItem();

    // Check if an item is selected
    if (selectedres == null) {
        showAlert("no item selected");
        return;
    }

    // Remove the selected item from the database
    ServiceReservation res = new ServiceReservation();
    res.supprimer(selectedres);
     reservationlist.remove(selectedres);
                    List<Reservation> updatedCircuits = res.affihcer();
            // Update circuitList with the new data
    reservationlist.clear();
    reservationlist.addAll(updatedCircuits);

    // Set the ListView items to circuitList to reflect the updated data
    listresu.setItems(reservationlist);


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
@FXML
public void chart (ActionEvent event) {
    // Load the FXML file for the "restaurant management" scene
    FXMLLoader loader = new FXMLLoader(getClass().getResource("chart.fxml"));
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

private void showAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}}
   
        
    
    

