/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restoratings;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.esprit.entities.TypeBadge;
import tn.esprit.services.ServiceBadge;

/**
 * FXML Controller class
 *
 * @author Ltifi
 */
public class PieController implements Initializable {

    @FXML
    private PieChart pieChart;
    
    @FXML
    private Button btnreturn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
            // Get badge counts from your ServiceBadge
            ServiceBadge serviceBadge = ServiceBadge.getInstance();
            Map<TypeBadge, Integer> badgeCounts = serviceBadge.countBadgesByType();

            // Create PieChart.Data for each badge type and count
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            badgeCounts.forEach((badgeType, count) ->
                pieChartData.add(new PieChart.Data(badgeType.toString(), count))
            );

            // Create a PieChart and set the data
            pieChart.setData(pieChartData);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the error
        }

    } 
    
  

    @FXML
     public void onBtnMescloseClick(ActionEvent event) {
        try {
            // Charger le fichier pie.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBar.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Obtenir la fenêtre actuelle (étape facultative)
            Stage currentStage = (Stage) btnreturn.getScene().getWindow();

            // Créer une nouvelle fenêtre
            Stage newStage = new Stage();
            newStage.setScene(scene);

            // Masquer la fenêtre actuelle (étape facultative)
            if (currentStage != null) {
                currentStage.hide();
            }

            // Afficher la nouvelle fenêtre
            newStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
   }
    
}
