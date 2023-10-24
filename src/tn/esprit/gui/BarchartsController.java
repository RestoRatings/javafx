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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tn.esprit.entities.Evennement;
import tn.esprit.services.Eventservice;
import tn.esprit.services.Participationservices;

public class BarchartsController implements Initializable {
   private Stage stage;
    private Stage scene;
    @FXML
    private BarChart<String, Number> barcharts;

    @FXML
    private CategoryAxis CategoryAxis;

    @FXML
    private NumberAxis NumberAxis;
    @FXML
    private Button RETOURPARTICIPANT;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Participationservices participationService = new Participationservices();
        Eventservice eventService = new Eventservice();

      
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Nombre de Participants");

    
        for (Evennement event : eventService.getAll()) {

            int nombreParticipants = participationService.getNombreParticipants(event.getId());
       
            series.getData().add(new XYChart.Data<>(event.getTitre(), nombreParticipants));
        }

      
        barcharts.getData().add(series);
    }

    @FXML
    private void RETOURparticipant(ActionEvent event) {
        
         try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("addparticipant.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}