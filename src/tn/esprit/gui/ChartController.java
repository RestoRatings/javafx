/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
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
public class ChartController implements Initializable {
      @FXML
    private PieChart chart;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             // Retrieve the list of reservations from your data source
        ServiceReservation reservationService = new ServiceReservation();
        List<Reservation> reservations = reservationService.affihcer();

        // Create a PieChart.Data list to store the data points
        List<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        // Group reservations by location and count them
          Map<String, Long> locationCounts = reservations.stream()
                .collect(Collectors.groupingBy(reservation -> reservation.getRestau().getlocation(), Collectors.counting()));

        // Create PieChart.Data for each location and count
        locationCounts.forEach((location, count) -> {
            String label = location + " (" + count + ")";
            
            PieChart.Data data = new PieChart.Data(label, count);
            pieChartData.add(data);
        });

        // Add the pieChartData list to the PieChart
        chart.getData().addAll(pieChartData);
    }
    @FXML
public void back (ActionEvent event) {
    // Load the FXML file for the "restaurant management" scene
    FXMLLoader loader = new FXMLLoader(getClass().getResource("reservation.fxml"));
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
        // TODO
    }    
    
