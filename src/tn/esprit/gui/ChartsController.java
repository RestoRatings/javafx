/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tn.esprit.entities.User;
import tn.esprit.entities.UserRole;
import tn.esprit.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ChartsController implements Initializable {

    @FXML
    private Label greet_username;
    @FXML
    private Button btnDashChart;
    @FXML
    private Button btnUserChart;
    @FXML
    private PieChart pieChartPic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceUser userService = new ServiceUser();
        List<User> users = userService.afficherTous();

        long clientCount = users.stream().filter(user -> user.getRole() == UserRole.CLIENT).count();
        long expertCount = users.stream().filter(user -> user.getRole() == UserRole.EXPERT).count();

        // Create PieChart data
        PieChart.Data clientData = new PieChart.Data("Clients", clientCount);
        PieChart.Data expertData = new PieChart.Data("Experts", expertCount);

        pieChartPic.getData().addAll(clientData, expertData);
    }
        // TODO
    }    
    

