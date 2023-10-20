/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.entities.EtatRec;
import tn.esprit.entities.Reclamation;
import tn.esprit.entities.TypeRec;
import tn.esprit.services.ServiceReclamation;
import java.time.LocalDate;
import tn.esprit.entities.User;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
/**
 * FXML Controller class
 *
 * @author mchai
 */
public class GestionReclamationController implements Initializable {

    private DatePicker datefield;
    private ComboBox<EtatRec> Etatrecfield;
    private ComboBox<TypeRec> Typerecfield;
    @FXML
    private Button deleteR;
    @FXML
    private TableView<Reclamation> recTV;
    @FXML
    private TableColumn<Reclamation, String> usernameTV;
    @FXML
    private TableColumn<Reclamation, Date> dateTV;
    @FXML
    private TableColumn<Reclamation, TypeRec> TyperecTV;
    @FXML
    private TableColumn<Reclamation, EtatRec> EtatrecTV;
    private TextField usernamefield;
    @FXML
    private Button repondreR;
    @FXML
    private TableColumn<Reclamation, String> descriptionTV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Remplissez les ComboBox avec les valeurs appropriées
        

        try {
            RecTable();
        } catch (SQLException ex) {
            Logger.getLogger(GestionReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    int myIndex;



    

    @FXML
    private void delete_rec(ActionEvent event) throws SQLException {
        myIndex = recTV.getSelectionModel().getSelectedIndex();
        if (myIndex >= 0) {
            int idrec = recTV.getItems().get(myIndex).getIdrec();
            ServiceReclamation _serviceReclamation = new ServiceReclamation();
            _serviceReclamation.supprimer(idrec);
            RecTable();
        }
    }

    public void RecTable() throws SQLException {
        ServiceReclamation serviceReclamation = new ServiceReclamation();
         List<Reclamation> reclamationList = serviceReclamation.recuperer();

    // Convertissez la liste en ArrayList
        ArrayList<Reclamation> reclamationArrayList = new ArrayList<>(reclamationList);
        ObservableList<Reclamation> obsl = FXCollections.observableArrayList(reclamationList);
        recTV.setItems(obsl);

        usernameTV.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getUsername()));
        dateTV.setCellValueFactory(new PropertyValueFactory<>("date"));
         EtatrecTV.setCellValueFactory(new PropertyValueFactory<>("etatrec"));
        descriptionTV.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        TyperecTV.setCellValueFactory(new PropertyValueFactory<>("typerec"));
       

        recTV.setRowFactory(tv -> {
            TableRow<Reclamation> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = recTV.getSelectionModel().getSelectedIndex();
                    if (myIndex >= 0) {
                        // Vous pouvez afficher les détails dans le formulaire d'édition ici
                        
                }}
            });
            return myRow;
        });
    
    }
    @FXML
    private void repond_rec(ActionEvent event) {
        
        
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("GestionReponse.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        
        
        
        
        
        
        
        
        
        
    }
}