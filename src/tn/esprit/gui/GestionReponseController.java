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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.entities.Reclamation;
import tn.esprit.entities.Reponse;
import tn.esprit.services.ServiceReclamation;
import tn.esprit.services.ServiceReponse;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * FXML Controller class
 *
 * @author mchai
 */
public class GestionReponseController implements Initializable {

    @FXML
    private Button addRep;
    @FXML
    private Button uppdateRep;
    @FXML
    private Button deleteRep;
    @FXML
    private DatePicker daterepfield;
    @FXML
    private TextField contenuefield;
    @FXML
    private TableView<Reponse> repTV;
    @FXML
    private TableColumn<Reponse, String> contenueTV;
    @FXML
    private TableColumn<Reponse,Date> daterepTV;
    
    @FXML
    private Button retourRep;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try {
            RepTable();
        } catch (SQLException ex) {
            Logger.getLogger(GestionReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
         repTV.setRowFactory(tv -> {
            TableRow<Reponse> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = repTV.getSelectionModel().getSelectedIndex();
                    if (myIndex >= 0) {
                        // Sélectionnez la réponse du tableau
                        Reponse selectedReponse = repTV.getItems().get(myIndex);

                        // Remplissez les champs avec les données de la réponse
                        daterepfield.setValue(selectedReponse.getDaterep().toLocalDate());
                        contenuefield.setText(selectedReponse.getContenue());
                    }
                }
            });
            return myRow;
        });
    }
     int myIndex;    
         
    
    
         

    @FXML
    private void add_rep(ActionEvent event)  throws SQLException {
     String contenue = contenuefield.getText();
    LocalDate dateRep = daterepfield.getValue();

    if (contenue != null && dateRep != null) {
        int idrec = 75; // L'identifiant de la réclamation existante

        // Créez une instance de la réclamation existante avec l'identifiant idrec
        Reclamation reclamation = new Reclamation(idrec);
        Reponse newReponse = new Reponse(reclamation,contenue, Date.valueOf(dateRep));
        repTV.getItems().add(newReponse);
        ServiceReponse _serviceReponse = new ServiceReponse();

       
           
            _serviceReponse.ajouter(newReponse);

            // Rafraîchissez le tableau des réponses
            RepTable();

            // Effacez les champs de texte après avoir ajouté une réponse
            contenuefield.clear();
            daterepfield.setValue(null);
        
        }}
    
    

    @FXML
    private void uppdate_rep(ActionEvent event) throws SQLException {
          myIndex = repTV.getSelectionModel().getSelectedIndex();
        if (myIndex >= 0) {
            Reponse selectedReponse = repTV.getItems().get(myIndex);
            int idrec = selectedReponse.getIdrep();
            LocalDate selectedDate = daterepfield.getValue();
String modifiedContenue = contenuefield.getText();

// Obtenez la date actuelle correctement
           
            selectedReponse.setDaterep(Date.valueOf(selectedDate));
            selectedReponse.setContenue(modifiedContenue);
            
           
            ServiceReponse _serviceReponse= new ServiceReponse();

            _serviceReponse.modifier(selectedReponse);
            
            RepTable();
            contenuefield.clear();
daterepfield.setValue(null);
        }
    }

    @FXML
    private void delete_rep(ActionEvent event)  throws SQLException {
        myIndex = repTV.getSelectionModel().getSelectedIndex();
        if (myIndex >= 0) {
            int idrep = repTV.getItems().get(myIndex).getIdrep();
            ServiceReponse _serviceReponse = new ServiceReponse();
            _serviceReponse.supprimer(idrep);
            RepTable();
        }
    }
    public void RepTable() throws SQLException{
     ServiceReponse serviceReponse = new ServiceReponse();
         List<Reponse> reponseList = serviceReponse.recuperer();

    // Convertissez la liste en ArrayList
        ArrayList<Reponse> reponseArrayList = new ArrayList<>(reponseList);
        ObservableList<Reponse> obsl = FXCollections.observableArrayList(reponseList);
        repTV.setItems(obsl);

        contenueTV.setCellValueFactory(new PropertyValueFactory<>("contenue"));
        daterepTV.setCellValueFactory(new PropertyValueFactory<>("daterep"));
         
        
       

        repTV.setRowFactory(tv -> {
            TableRow<Reponse> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = repTV.getSelectionModel().getSelectedIndex();
                    if (myIndex >= 0) {
                        // Vous pouvez afficher les détails dans le formulaire d'édition ici
                        
                }}
            });
            return myRow;
        });
    
    }

    @FXML
    private void retour_rec(ActionEvent event) {
        
     try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("gestionReclamation.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        
        
        
        
        
        
        
        
        
        
    }
}
