/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.entities.Evennement;
import tn.esprit.entities.Participant;
import tn.esprit.entities.User;
import tn.esprit.services.Eventservice;
import tn.esprit.services.Participationservices;


/**
 * FXML Controller class
 *
 * @author Med Iheb
 */
public class AddparticipantController implements Initializable {

    private Stage stage;
    private Stage scene;
    @FXML
    private TableView<Participant> TableviewParticipant;
    @FXML
    private TableColumn<Participant, Integer> TableviewNumero;
    @FXML
    private TableColumn<Participant,  LocalDate> tableviewDate;
    @FXML
    private Button Participantsupprimer;
    @FXML
    private Button Modifier;
    @FXML
    private DatePicker recDateparticipant;
    
    @FXML
    private TextField recNumero;

    @FXML
    private TextField recRecherche;
    @FXML
    private TableColumn<Participant, String> nameeventview;
    @FXML
    private TableColumn<Participant, String> tableNomParticipant;
    
    /**
     * Initializes the controller class.
     */
      int    myIndex;
    @FXML
    private Button retourajouterevnt;
    @FXML
    private Button satis;
    
 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableviewParticipant ();
    }    


    @FXML
    private void supprimerParicipant(ActionEvent event) {
        
         myIndex =   TableviewParticipant.getSelectionModel().getSelectedIndex();
         int numero = Integer.parseInt(String.valueOf( TableviewParticipant.getItems().get(myIndex).getNumero()));
      

          Participationservices participation=new Participationservices();
          participation.supprimer(numero);
              TableviewParticipant ();
            
        
                
    }

    @FXML
    private void ModiferParticipant(ActionEvent event) {
       int numero = parseInt(recRecherche.getText());
       LocalDate Date;
       Evennement selectedEvent= new Evennement();
       User userConnecter = new User();
       Date= recDateparticipant.getValue();
        int Numero= parseInt(recNumero.getText());
        
       Participant particperAEvent=new Participant(Date, Numero, userConnecter ,selectedEvent);
        Participationservices participation=new Participationservices();
        participation.modifier(particperAEvent,numero);
           TableviewParticipant ();
        
       
        
        
        
        
    }
    public void TableviewParticipant(){
         Participationservices participation=new Participationservices();
     ArrayList<Participant> challengess = new ArrayList<>();
              
                     challengess=  (ArrayList<Participant>) participation.getAll();
              
  
    ObservableList<Participant> obsl = FXCollections.observableArrayList(challengess); 
  
    TableviewParticipant.setItems(obsl);
    tableviewDate.setCellValueFactory(new  PropertyValueFactory<>("datepar"));
    TableviewNumero.setCellValueFactory(new  PropertyValueFactory<>("numero"));
    
     nameeventview.setCellValueFactory(cellData -> 
        new SimpleStringProperty(cellData.getValue().getEvent().getTitre()));
        tableNomParticipant.setCellValueFactory(cellData ->
                
        new SimpleStringProperty(cellData.getValue().getUser().getUsername()));

/* nameeventview.setCellValueFactory(cellData -> 
        new SimpleStringProperty(cellData.getValue().getEvent().getTitre()));
        tableNomParticipant.setCellValueFactory(cellData -> 
        new SimpleStringProperty(cellData.getValue().getUser().getUsername()));*/


 

TableviewParticipant.setRowFactory(tv -> {
     TableRow<Participant> myRow = new TableRow<>();
     myRow.setOnMouseClicked (event ->
     {
        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
        {
           myIndex =  TableviewParticipant.getSelectionModel().getSelectedIndex();
            
     
   
 recDateparticipant.setValue( TableviewParticipant.getItems().get(myIndex).getDatepar()); 
    recNumero.setText( Integer.toString(TableviewParticipant.getItems().get(myIndex).getNumero()));
    recRecherche.setText( Integer.toString(TableviewParticipant.getItems().get(myIndex).getNumero()));
      
   // recLieu.setText( TableviewParticipant.getItems().get(myIndex).getLieu());

         }
        
            
            
            
        
     });
        return myRow;
                   });
    
    }

    @FXML
    private void retouraddEvnt(ActionEvent event) {
        
         try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("addevnt.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        
        
    }

    @FXML
    private void statistique(ActionEvent event) {
         try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("barcharts.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
    
}
