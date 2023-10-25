/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ReservationuserController implements Initializable {

    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<?> hourres;
    @FXML
    private ComboBox<?> minuteres;
    @FXML
    private ListView<?> restaurantres;
    @FXML
    private ListView<?> listresu;
    @FXML
    private TextField idres;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addres(ActionEvent event) {
    }

    @FXML
    private void modres(ActionEvent event) {
    }

    @FXML
    private void suppres(ActionEvent event) {
    }

    @FXML
    private void goBackToManagement(ActionEvent event) {
    }

    @FXML
    private void chart(ActionEvent event) {
    }
    
}
