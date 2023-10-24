/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.FileOutputStream;
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
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
/**
 * FXML Controller class
 *
 * @author mchai
 */
public class GestionReclamationController implements Initializable {
private Stage stage;
    private Stage scene;
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
    @FXML
    private TextField usernamefield;
    @FXML
    private Button repondreR;
    @FXML
    private TableColumn<Reclamation, String> descriptionTV;
    @FXML
    private ComboBox<TypeRec> typerecComboBox;
    @FXML
    private Button exportToExcelButton;
    @FXML
    private Button retourreclamationADMIN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Remplissez les ComboBox avec les valeurs appropriées
        
 typerecComboBox.getItems().addAll(TypeRec.values());
        try {
            RecTable();
        } catch (SQLException ex) {
            Logger.getLogger(GestionReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
         recTV.setOnMouseClicked(event -> {
        if (event.getClickCount() == 1) {
            Reclamation selectedReclamation = recTV.getSelectionModel().getSelectedItem();
            if (selectedReclamation != null) {
                // Vous avez maintenant la réclamation sélectionnée (selectedReclamation)
                // Vous pouvez accéder à ses propriétés, telles que l'ID de la réclamation, la description, etc.
                int idrec = selectedReclamation.getIdrec();
                String username=selectedReclamation.getUser().getUsername();
                String description = selectedReclamation.getDescription();
                TypeRec typerec=selectedReclamation.getTyperec();
                EtatRec etatrec=selectedReclamation.getEtatrec();
                // Utilisez ces données pour les afficher ou les transmettre à l'interface de réponse
            }
        }
        });
        usernamefield.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                rechercheAvancee(newValue);
            } catch (SQLException ex) {
                Logger.getLogger(GestionReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    });
        typerecComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
     try {
         updateFilteredReclamations();
     } catch (SQLException ex) {
         Logger.getLogger(GestionReclamationController.class.getName()).log(Level.SEVERE, null, ex);
     }
    });
         
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
    // Récupérez la réclamation sélectionnée dans le TableView
    Reclamation selectedReclamation = recTV.getSelectionModel().getSelectedItem();
    
    if (selectedReclamation != null) {
         Stage stage = (Stage) repondreR.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionReponse.fxml"));
        Parent root;
        try {
            root = loader.load();
            GestionReponseController reponseController = loader.getController();
            reponseController.setReclamation(selectedReclamation);
            
            Stage stageRe = new Stage();
            Scene scene = new Scene(root);
            stageRe.setScene(scene);
            stageRe.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }}
private void rechercheAvancee(String username) throws SQLException {
    ServiceReclamation serviceReclamation = new ServiceReclamation();
    List<Reclamation> reclamationList = serviceReclamation.recuperer();

    // Filtrer les résultats en fonction du texte du champ "username"
    List<Reclamation> filteredReclamations = new ArrayList<>();
    for (Reclamation reclamation : reclamationList) {
        if (reclamation.getUser().getUsername().contains(username)) {
            filteredReclamations.add(reclamation);
        }
    }

    // Mettez à jour la TableView avec les résultats filtrés
    ObservableList<Reclamation> obsl = FXCollections.observableArrayList(filteredReclamations);
    recTV.setItems(obsl);
}
private void updateFilteredReclamations() throws SQLException {
    String username = usernamefield.getText();
    TypeRec selectedTypeRec = typerecComboBox.getValue();

    ServiceReclamation serviceReclamation = new ServiceReclamation();
    List<Reclamation> reclamationList = serviceReclamation.recuperer();

    // Filtrer les résultats en fonction du nom d'utilisateur et du TypeRec
    List<Reclamation> filteredReclamations = new ArrayList<>();
    for (Reclamation reclamation : reclamationList) {
        if ((username.isEmpty() || reclamation.getUser().getUsername().contains(username)) &&
            (selectedTypeRec == null || reclamation.getTyperec() == selectedTypeRec)) {
            filteredReclamations.add(reclamation);
        }
    }

    // Mettez à jour la TableView avec les résultats filtrés
    ObservableList<Reclamation> obsl = FXCollections.observableArrayList(filteredReclamations);
    recTV.setItems(obsl);
}

    @FXML
    private void exportToExcel(ActionEvent event) {
        
    try {
        // Create a new Excel workbook and a sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reclamations");

        // Create the header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Username");
        headerRow.createCell(1).setCellValue("Date");
        headerRow.createCell(2).setCellValue("TypeRec");
        headerRow.createCell(3).setCellValue("EtatRec");
        headerRow.createCell(4).setCellValue("Description");

        // Get the data from the TableView and add it to the Excel sheet
        ObservableList<Reclamation> reclamations = recTV.getItems();
        int rowNum = 1;
        for (Reclamation reclamation : reclamations) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(reclamation.getUser().getUsername());
            row.createCell(1).setCellValue(reclamation.getDate().toString());
            row.createCell(2).setCellValue(reclamation.getTyperec().toString());
            row.createCell(3).setCellValue(reclamation.getEtatrec().toString());
            row.createCell(4).setCellValue(reclamation.getDescription());
        }

        // Save the workbook to a file
        String fileName = "Reclamations.xlsx";
        FileOutputStream fileOut = new FileOutputStream(fileName);
        workbook.write(fileOut);
        fileOut.close();

        // Show a success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export Successful");
        alert.setHeaderText(null);
        alert.setContentText("Reclamations data has been exported to " + fileName);
        alert.showAndWait();
    } catch (Exception e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Export Error");
        alert.setHeaderText(null);
        alert.setContentText("An error occurred while exporting data.");
        alert.showAndWait();
    }





}

    @FXML
    private void retourreclamationadmin(ActionEvent event) {
          try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("AdminMainForm.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
           
    }
    }
