package tn.esprit.gui;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
    



import javafx.scene.image.Image;


    
    

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import tn.esprit.entities.Evennement;
import tn.esprit.services.Eventservice;

/**
 * FXML Controller class
 *
 * @author Med Iheb
 */
public class AddevntController implements Initializable {
    private Stage stage;
    private Stage scene;
    
    @FXML
    private TextField recTitre;
    @FXML
    private TextField recDiscription;
    @FXML
    private TextField recAdresse;
    @FXML
    private DatePicker recDate;
    @FXML
    private Button recImage;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TextField recChercher;
    @FXML
    private TableView<Evennement> EventTable;
    @FXML
    private TableColumn<Evennement, String> titre_eventView;
    
    @FXML
    private TableColumn<Evennement, LocalDate> date_eventView;
    @FXML
    private TableColumn<Evennement, String> description_eventView;
    @FXML
    private TableColumn<Evennement, String> adresse_eventView;
    @FXML
    private TextField recLieu;
    @FXML
    private TableColumn<Evennement, String> lieu_eventView;
    @FXML
    private TableColumn<Evennement,Integer > id_eventView1;
    
    Eventservice e = new Eventservice();

    /**
     * Initializes the controller class.
     */
    
    int    myIndex;
    @FXML
    private Button Participantajoouter;
    @FXML
    private TextField TXTimg;
    @FXML
    private ImageView lbl_image;
    @FXML
    private Button chercher;
    @FXML
    private Button excel;
    @FXML
    private ImageView qrCodeImageView;
    @FXML
    private Button btnretouriheb;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        EventTable();
    }    
 
 
    @FXML
    private void ADDimage(ActionEvent event) {
        
          JFileChooser  chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        TXTimg.setText(filename);
        String path= TXTimg.getText();
        System.out.println("PATH :"  +path);
       
    
             Image imn = new Image(
              "file:/" +path );
            lbl_image.setImage(imn);

        
        
        
        
    }
    @FXML
private void addevent(ActionEvent event) {
    if (isInputValid()) {
        String titre;
        LocalDate date;
        String description;
        String adresse;
        String img;
        String lieu;

        titre = recTitre.getText();
        description = recDiscription.getText();
        adresse = recAdresse.getText();
        date = recDate.getValue();
        lieu = recLieu.getText();
        img = TXTimg.getText();

        Evennement evennementPourAjouter = new Evennement(titre, date, description, img, adresse, lieu);
        Eventservice Serviceevent = new Eventservice();

        // Ajoutez l'événement
        Serviceevent.ajouter(evennementPourAjouter);

        // Générez le code QR
        String eventInfo = "Titre : " + titre + "\nDate : " + date + "\nDescription : " + description + "\nAdresse : " + adresse;
        Image qrCodeImage = generateQRCodeImage(eventInfo, 200, 200);

        // Affichez le code QR dans l'ImageView
        qrCodeImageView.setImage(qrCodeImage);

        showAlert("AJOUTER AVEC SUCCES", "AJOUTER AVEC SUCCES");
        EventTable();
    }
}
    

    @FXML
    private void Modifierevent(ActionEvent event) {
         if (isInputValid()) { 
        myIndex = EventTable.getSelectionModel().getSelectedIndex();
       int idEvent = Integer.parseInt(String.valueOf(EventTable.getItems().get(myIndex).getId()));
        
     String titre;
     LocalDate date;
     String  description;
     String adresse;    
     String img;   
     String lieu ;
    titre =recTitre.getText();
    description= recDiscription.getText();
    adresse= recAdresse.getText();
    date= recDate.getValue();
    lieu =recLieu.getText(); 
    img =TXTimg.getText();

    Evennement evennementPourUpdate = new  Evennement( idEvent,titre, date,  description, img,  adresse, lieu);
    Eventservice Serviceevent = new Eventservice();
    Serviceevent.modifier(evennementPourUpdate);
     showAlert("MODIFIER AVEC SUCCES", "MODIFIER AVEC SUCCES");
    EventTable();
         } 
    }

    @FXML
    private void supprimerevent(ActionEvent event) {
        
         myIndex = EventTable.getSelectionModel().getSelectedIndex();
         if (myIndex == -1) {
        // Aucun élément sélectionné, affichez un message d'erreur à l'utilisateur.
        showAlert("Aucun événement sélectionné", "Veuillez sélectionner un événement à supprimer.");
        return;
    }
       int idEvent = Integer.parseInt(String.valueOf(EventTable.getItems().get(myIndex).getId()));
       Evennement evennementPoursupprimer = new  Evennement( idEvent,"", LocalDate.now(),  "", "",  "", "");
        Eventservice Serviceevent = new Eventservice();
        Serviceevent.supprimer(idEvent);
        EventTable();
        
    }

    @FXML
    private void displayDateD(ActionEvent event) {
    }
    
      
      public void EventTable(){
          Eventservice ServiceEvent= new Eventservice();
     ArrayList<Evennement> challengess = new ArrayList<>();
              
           challengess=  (ArrayList<Evennement>) ServiceEvent.getAll();
              
  
    ObservableList<Evennement> obsl = FXCollections.observableArrayList(challengess); 
  
    EventTable.setItems(obsl);
    
    titre_eventView.setCellValueFactory(new  PropertyValueFactory<>("titre"));
    date_eventView.setCellValueFactory(new  PropertyValueFactory<>("date")); 
    description_eventView.setCellValueFactory(new  PropertyValueFactory<>("description"));
     id_eventView1.setCellValueFactory(new  PropertyValueFactory<>("id"));
    adresse_eventView.setCellValueFactory(new  PropertyValueFactory<>("adresse"));
    lieu_eventView.setCellValueFactory(new  PropertyValueFactory<>("lieu"));
    EventTable.setRowFactory( tv -> {
     TableRow<Evennement> myRow = new TableRow<>();
     myRow.setOnMouseClicked (event ->
     {
        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
        {
           myIndex =  EventTable.getSelectionModel().getSelectedIndex();
            
     
    recTitre.setText(EventTable.getItems().get(myIndex).getTitre());
     recDiscription.setText(EventTable.getItems().get(myIndex).getDescription());

    recAdresse.setText(EventTable.getItems().get(myIndex).getAdresse());
  
    recDate.setValue(EventTable.getItems().get(myIndex).getDate());  
    recLieu.setText(EventTable.getItems().get(myIndex).getLieu());

         }
        
            
            
            
        
     });
        return myRow;
                   });
    
    }

    @FXML
    private void addParticipant(ActionEvent event) {
       
       
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
    
     ObservableList<Evennement> eventsData = FXCollections.observableArrayList();
    @FXML
    private void chercher(ActionEvent event) throws SQLException {
        List<Evennement> events = null;
        String recherche = recChercher.getText();
         
            events = recupererBytitre(recherche);
         
              if (events != null && !events.isEmpty()) {
            eventsData.setAll(events);
            EventTable.setItems(eventsData);
        } else {
            afficherAlerte("Aucun résultat", "Aucun résultat trouvé pour la recherche.", Alert.AlertType.INFORMATION);
        }
        }
    
    private void afficherAlerte(String titre, String contenu, Alert.AlertType type) {
    Alert alert = new Alert(type);
    alert.setTitle(titre);
    alert.setHeaderText(contenu);
    alert.showAndWait();
}
    
    
        private List<Evennement> recupererBytitre(String titre) throws SQLException {
        return e.recupererBytitre(titre);
    }

    private boolean isInputValid() {
          if (recTitre.getText().isEmpty() || recDiscription.getText().isEmpty() || 
        recDate.getValue() == null || recAdresse.getText().isEmpty() || 
        TXTimg.getText().isEmpty() || recLieu.getText().isEmpty()) {
       
        showAlert("Champs vides", "Tous les champs doivent être remplis.");
        return false;
    }
    
   
    
    return true;
    }

    private void showAlert(String champs_vides, String tous_les_champs_doivent_être_remplis) {
       Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle( champs_vides);
    alert.setHeaderText(null);
    alert.setContentText(tous_les_champs_doivent_être_remplis);
    alert.showAndWait();
    }

   @FXML
    private void exportToExcel(ActionEvent event) {
        
       
        
          FileChooser fileChooser = new FileChooser();

        // Set extension filter for Excel files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (.xlsx)", ".xlsx");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(excel.getScene().getWindow());

        if (file != null) {
            try {
                // Create new Excel workbook and sheet
                Workbook workbook = new XSSFWorkbook();

                Sheet sheet = workbook.createSheet("Events");

                // Create header row
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("ID");
                headerRow.createCell(1).setCellValue("Titre");
                headerRow.createCell(2).setCellValue("Description");     
                headerRow.createCell(3).setCellValue("Date");
                headerRow.createCell(4).setCellValue("adresse");
                headerRow.createCell(5).setCellValue("Lieu");

                // Add data rows
                Eventservice EventService = new  Eventservice();
                List<Evennement> events = EventService.getAll();
                for (int i = 0; i < events.size(); i++) {
                    Row row = sheet.createRow(i + 1);
                    row.createCell(0).setCellValue(events.get(i).getId());
                    row.createCell(1).setCellValue(events.get(i).getTitre());
                    row.createCell(2).setCellValue(events.get(i).getDescription());
                    row.createCell(3).setCellValue(events.get(i).getDate().toString());
                      row.createCell(4).setCellValue(events.get(i).getAdresse());
                    row.createCell(5).setCellValue(events.get(i).getLieu());
                }

                // Write to file
                FileOutputStream fileOut = new FileOutputStream(file);
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();

                // Show success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Export Successful");
                alert.setHeaderText(null);
                alert.setContentText("Events exported to Excel file.");
                alert.showAndWait();

            
            
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Export Failed");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred while exporting events to Excel file.");
                alert.showAndWait();
            }
        }
    }
    
       private Image generateQRCodeImage(String text, int width, int height) {
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix;
    try {
        bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        // Convertir la matrice BitMatrix en image JavaFX
        BufferedImage bufferedImage = toBufferedImage(bitMatrix);
        return SwingFXUtils.toFXImage(bufferedImage, null);
    } catch (WriterException e) {
        e.printStackTrace();
        return null;
    }
}

private BufferedImage toBufferedImage(BitMatrix bitMatrix) {
    int width = bitMatrix.getWidth();
    int height = bitMatrix.getHeight();
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
            image.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
        }
    }
    return image;
}

    @FXML
    private void retouradminiheb(ActionEvent event) {
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
    
    


    
    



    





