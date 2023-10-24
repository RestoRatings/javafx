/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import tn.esprit.entities.CategorieP;
import tn.esprit.services.ServiceAchat;

/**
 * FXML Controller class
 *
 * @author Med-Amine
 */
public class StatsbrhFXMLController implements Initializable {

    @FXML
    private PieChart statsbrh;

    /**
     * Initializes the controller class.
     */
   public void initialize(URL url, ResourceBundle rb) {
    ServiceAchat serviceAchat = ServiceAchat.getInstance();
    try {
        Map<CategorieP, Integer> statistiques = serviceAchat.statistiquesParCategorie();
        statsbrh.getData().clear(); // Efface toutes les données précédentes (au cas où)

        for (Map.Entry<CategorieP, Integer> entry : statistiques.entrySet()) {
            PieChart.Data data = new PieChart.Data(entry.getKey().toString(), entry.getValue());
            statsbrh.getData().add(data);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }}}

