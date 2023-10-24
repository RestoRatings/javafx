/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.entities.ReclamationReponseModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;
/**
 * FXML Controller class
 *
 * @author mchai
 */
public class AffichageController implements Initializable {

    @FXML
    private TableColumn<ReclamationReponseModel, String> usernameTV1;
    @FXML
    private TableColumn<ReclamationReponseModel, Date> daterecTV1;
    @FXML
    private TableColumn<ReclamationReponseModel, String> etatrecTV1;
    @FXML
    private TableColumn<ReclamationReponseModel, String> descriptionTV1;
    @FXML
    private TableColumn<ReclamationReponseModel, String> typerecTV1;
    @FXML
    private TableColumn<ReclamationReponseModel, String> contenueTV1;
    @FXML
    private TableColumn<ReclamationReponseModel, Date> daterepTV1;
    @FXML
    private Button retourAff;
    @FXML
    private TableView<ReclamationReponseModel> affTV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        usernameTV1.setCellValueFactory(new PropertyValueFactory<>("username"));
daterecTV1.setCellValueFactory(new PropertyValueFactory<>("daterec"));

   
etatrecTV1.setCellValueFactory(new PropertyValueFactory<>("etatrec"));
descriptionTV1.setCellValueFactory(new PropertyValueFactory<>("description"));
typerecTV1.setCellValueFactory(new PropertyValueFactory<>("typerec"));
contenueTV1.setCellValueFactory(new PropertyValueFactory<>("contenue"));
daterepTV1.setCellValueFactory(new PropertyValueFactory<>("daterep"));
       populateTableView();}
    
private void populateTableView() {
    // Obtenez vos données de la base de données, c'est-à-dire des réclamations et des réponses

    List<ReclamationReponseModel> reclamationReponses = fetchDataForUser("1");

    // Convertissez votre liste en une ObservableList
    ObservableList<ReclamationReponseModel> data = FXCollections.observableArrayList(reclamationReponses);

    // Ajoutez les données à la TableView
    affTV.setItems(data);
}
 private List<ReclamationReponseModel> fetchDataForUser(String username) {
        List<ReclamationReponseModel> reclamationReponses = new ArrayList<>();

        // Add code to fetch data from the database. This code will depend on your database system.

        // Example:
        // Fetch all reclamations and their responses for the user.
        // You may use SQL or an ORM framework like Hibernate to fetch the data.
        // For each pair of reclamation and response, create a ReclamationReponseModel and add it to the list.
String query = "SELECT r.iduser, u.username, r.date, r.description, r.typerec, r.etatrec, " +
     "re.contenue, re.daterep " +
     "FROM Reclamation r " +
     "LEFT JOIN Reponse re ON r.idrec = re.idrec " +
     "LEFT JOIN User u ON r.iduser = u.iduser " + // Cette jointure récupère le nom d'utilisateur
     "WHERE r.iduser = ?";

    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restoratings?user=root&password=");
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, Integer.parseInt(username));

        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ReclamationReponseModel reclamationReponse = new ReclamationReponseModel();
                reclamationReponse.setUsername(resultSet.getString("username"));
                reclamationReponse.setDaterep(resultSet.getDate("date"));
                reclamationReponse.setEtatrec(resultSet.getString("etatrec"));
                reclamationReponse.setDescription(resultSet.getString("description"));
                reclamationReponse.setTyperec(resultSet.getString("typerec"));
                reclamationReponse.setContenue(resultSet.getString("contenue"));
                reclamationReponse.setDaterep(resultSet.getDate("daterep"));

                reclamationReponses.add(reclamationReponse);
            }
        }
    } catch (SQLException e) {
        // Handle exceptions
        e.printStackTrace();
    } return reclamationReponses;
}

    @FXML
    private void ret_aff(ActionEvent event) {
         try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        
        
        
        
        
        
        
        
        
        
    }}

