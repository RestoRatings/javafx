/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restoratings;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.message.Media;
import java.io.File;
import java.io.FileOutputStream;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;
import java.util.Optional;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import tn.esprit.entities.Avis;
import tn.esprit.entities.Badge;
import tn.esprit.entities.Evennement;
import tn.esprit.entities.Restaurant;
import tn.esprit.entities.User;
import tn.esprit.services.ServiceAvis;
import tn.esprit.services.ServiceBadge;
import tn.esprit.services.Servicerestaurant;
import tn.esprit.entities.TypeBadge;
import restoratings.SendSMS;
import static restoratings.SendSMS.sendSMS;

/**
 * FXML Controller class
 *
 * @author Ltifi
 */
public class GestionBadgeController implements Initializable {
Badge badge = new Badge();
Servicerestaurant restaurantService =new Servicerestaurant();
ServiceBadge serviceBadge =new ServiceBadge();
    /** 
     * Initializes the controller class.
     */
    
        @FXML
    private TextField RestoNameTextfieldBadge;
        @FXML
    private TextField BadgeRechTextfieldBadge1;

    @FXML
    private Button btnAjouterBadge;

    @FXML
    private Button btnModifierBadge;

    @FXML
    private Button btnSupprimerBadge;

    @FXML
    private ComboBox<String> comboBoxRestaurant;

    @FXML
    private ComboBox<String> comboBoxTypeBadge;

    @FXML
    private TextArea commentaireTextArea;
    
     @FXML
    private TableColumn<Badge, LocalDate> DateCol;
     
      @FXML
    private TableColumn<Badge, TypeBadge> badgeCol;
      
    @FXML
    private TableView<Badge> tableViewBadge;

    @FXML
    private TableColumn<Badge, String> userCol;
    
     @FXML
    private TableColumn<Badge, String> commentaireCol;

    @FXML
    private TableColumn<Badge, String> nomRestoCol;
   
    private int selectedBadgeIndex = -1;
    
    @FXML
    private Button btnMesBdges;
    
    @FXML
    private Button print;
    
    @FXML
    private Pagination pagination;
    
    private static final int ITEMS_PER_PAGE = 5; 
    private ObservableList<Badge> data;
    @FXML
   public void onBtnMesBdgesClick(ActionEvent event) {
        try {
            // Charger le fichier pie.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pie.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Obtenir la fenêtre actuelle (étape facultative)
            Stage currentStage = (Stage) btnMesBdges.getScene().getWindow();

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
    @Override
public void initialize(URL url, ResourceBundle rb) {
          configureComboBoxResto();
    ObservableList <String> list = FXCollections.observableArrayList("VIP","Silver","Diamant");
         comboBoxTypeBadge.setItems(list);
        
                 tableViewBadge.getStylesheets().add(getClass().getResource("GestionAis.css").toExternalForm());

        // Appliquer les classes de style à la TableView
        tableViewBadge.getStyleClass().add("my-table-view");

        // Appliquer les classes de style aux cellules de la TableView
        tableViewBadge.setRowFactory(tv -> {
            TableRow<Badge> row = new TableRow<>();
            row.getStyleClass().add("my-table-cell");
            return row;
        });
        
        
        
        configureColumns();
        serviceBadge = ServiceBadge.getInstance();
        chargerBadge(null);
        tableViewBadge.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedBadgeIndex = tableViewBadge.getSelectionModel().getSelectedIndex();
            Badge selectedBadge = tableViewBadge.getSelectionModel().getSelectedItem();
            remplirComboBoxAvecBadgeSelectionne(selectedBadge);
            
            String comm = selectedBadge.getCommantaire();
            commentaireTextArea.setText(comm);
        });

        RestoNameTextfieldBadge.textProperty().addListener((observable, oldValue, newValue) -> {
            rechercherBadges(null);
        });

        BadgeRechTextfieldBadge1.textProperty().addListener((observable, oldValue, newValue) -> {
            rechercherBadges(null);
        });
        
    try {
        /////////////////Pagination
        data = FXCollections.observableArrayList(serviceBadge.recuperer());
    } catch (SQLException ex) {
        Logger.getLogger(GestionBadgeController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    int pageCount = (int) Math.ceil((double) data.size() / ITEMS_PER_PAGE);
    pagination.setPageCount(pageCount);

    pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> showPage(newIndex.intValue()));
    
    // Affichez la première page au démarrage.
    showPage(0);
}
private void showPage(int pageIndex) {
    int fromIndex = pageIndex * ITEMS_PER_PAGE;
    int toIndex = Math.min(fromIndex + ITEMS_PER_PAGE, data.size());

    List<Badge> pageData = data.subList(fromIndex, toIndex);

    // Mettez à jour le contenu du TableView avec les éléments de la page actuelle.
    tableViewBadge.setItems(FXCollections.observableArrayList(pageData));
}



private void remplirComboBoxAvecBadgeSelectionne(Badge badge) {
    if (badge != null) {
        comboBoxRestaurant.setValue(badge.getRestaurant().getNom());
        comboBoxTypeBadge.setValue(badge.getTypeBadge().name());
        commentaireTextArea.setText(badge.getCommantaire());
    }
}


    /**
     *
     * @param event
     */
    @FXML
   public void select(ActionEvent event) {
        String s =  comboBoxTypeBadge.getSelectionModel().getSelectedItem().toString();
         
    }
   
   
   
    // Remplacez les valeurs par les vôtres depuis le tableau de bord Twilio
    public static final String ACCOUNT_SID = "ACba0c2140f127b621d474ff52a586a2af";
    public static final String AUTH_TOKEN = "1aeea7c2b409f56d30ae00d405cb31c6";
    public static final String FROM_NUMBER = "+21640994876";

    public  void sendWhatsAppMessage(String to, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message whatsappMessage = Message.creator(
                new PhoneNumber("whatsapp:" + to),
                new PhoneNumber("whatsapp:" + FROM_NUMBER),
                message)
                .create();

        System.out.println(whatsappMessage.getSid());
    }

    /////////////////////////////////////////Ajouter//////////////////////////////////////////////////
  @FXML
private void ajouterBadge(ActionEvent event) throws SQLException {
    String commentaire = commentaireTextArea.getText();
    String restaurantSelectionne = comboBoxRestaurant.getValue();
    String badgeSelectionne = comboBoxTypeBadge.getValue();

    User user = new User();
    user.setIduser(1);

    if (badgeSelectionne != null && !badgeSelectionne.isEmpty() && commentaire != null && !commentaire.isEmpty() && restaurantSelectionne != null) {
        Badge nouvelBadge = new Badge();
        commentaire = commentaire.replaceAll("israel", " ");
        nouvelBadge.setCommantaire(commentaire);

        TypeBadge typeBadge = null;
        switch (badgeSelectionne) {
            case "VIP":
                typeBadge = TypeBadge.VIP;
                break;
            case "Silver":
                typeBadge = TypeBadge.Silver;
                break;
            case "Diamant":
                typeBadge = TypeBadge.Diamant;
                break;
            default:
                showAlert(Alert.AlertType.INFORMATION, "Badge non reconnu", "Le type de badge sélectionné n'est pas valide.", "");
                return;
        }
        int idRestaurant = restaurantService.getIdRestaurantParNom(restaurantSelectionne);

        if (ServiceBadge.getInstance().badgeExistePourRestaurant(typeBadge, idRestaurant)) {
            showAlert(Alert.AlertType.INFORMATION, "Badge déjà existant", "Ce badge existe déjà pour ce restaurant.", "");
            return;
        }
        nouvelBadge.setTypeBadge(typeBadge);

        nouvelBadge.setDateBadge(LocalDate.now());

        Restaurant restaurant = new Restaurant();
        restaurant.setIdrestau(restaurantService.getIdRestaurantParNom(restaurantSelectionne));

        boolean commentaireValide = commentaire.length() <= 170; // Contrôle de longueur du commentaire

        if (!commentaireValide) {
            showAlert(Alert.AlertType.INFORMATION, "Erreur de saisie", "Le commentaire ne doit pas dépasser 170 caractères.", "");
            return;
        }

        try {
            ServiceBadge.getInstance().ajouter(nouvelBadge, restaurant, user);
//            String message = "Badge ajouté : " + badgeSelectionne;
//            String phoneNumber = "+21652474552"; // Numéro WhatsApp du destinataire
//            sendWhatsAppMessage(phoneNumber, message);
            
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Badge ajouté avec succès", "");
            comboBoxRestaurant.setValue(null);
            comboBoxTypeBadge.setValue(null);
            commentaireTextArea.clear();
            Notification();
            show();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de l'ajout du badge", e.getMessage());
            e.printStackTrace();
        }
    } else {
        showAlert(Alert.AlertType.INFORMATION, "", "Remplissez tous les champs", "");
    }
    chargerBadge(event);
        String username = "NomUtilisateurConnecté"; 
        
        // Obtenez le nom du restaurant depuis restaurantSelectionne (vous devrez peut-être avoir une méthode pour cela)
       
        
        sendSMS("amin", restaurantSelectionne, badgeSelectionne);
}



 private Badge badgeSelectionne;
    
    private static int utilisateurConnecte = 1;



       //////////////////////////////////////////////Modifier////////////////////////////////////////////////////

@FXML
private void updateBadge(ActionEvent event) {
    int selectedBadgeIndex = tableViewBadge.getSelectionModel().getSelectedIndex();

    if (selectedBadgeIndex >= 0) {
        Badge selectedBadge = tableViewBadge.getSelectionModel().getSelectedItem();

        String newComment = commentaireTextArea.getText();
        String selectedRestaurantName = comboBoxRestaurant.getValue();
        String selectedType = comboBoxTypeBadge.getValue();
        
        if (newComment.isEmpty() || selectedRestaurantName == null || selectedType.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Champs vides", "Veuillez remplir tous les champs", "Assurez-vous de remplir tous les champs avant de mettre à jour le badge.");
        } else {
            LocalDate currentDate = LocalDate.now();

            try {
                int selectedRestaurantId = restaurantService.getIdRestaurantParNom(selectedRestaurantName);

                boolean commentaireValide = newComment.length() <= 150; // Contrôle de longueur du commentaire

                if (!commentaireValide) {
                    showAlert(Alert.AlertType.INFORMATION, "Erreur de saisie", "Le commentaire ne doit pas dépasser 150 caractères.", "");
                    return;
                }

                newComment = newComment.replaceAll("israel", " ");
                selectedBadge.setCommantaire(newComment);
                selectedBadge.setDateBadge(currentDate);
                selectedBadge.getRestaurant().setIdrestau(selectedRestaurantId);
                selectedBadge.setTypeBadge(TypeBadge.valueOf(selectedType));

                serviceBadge.modifier(selectedBadge.getId(), selectedBadge, selectedRestaurantId);
              
                show();
                Notification1();
                chargerBadge(event);
                comboBoxRestaurant.setValue(null);
                comboBoxTypeBadge.setValue(null);
                commentaireTextArea.clear();
                
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Badge mis à jour avec succès", "Le badge a été mis à jour avec succès.");
                
                // Appel de la fonction sendWhatsAppMessage pour envoyer le message WhatsApp
               
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la mise à jour du badge", e.getMessage());
            }
        }
    } else {
        showAlert(Alert.AlertType.ERROR, "Erreur", "Aucun badge sélectionné", "Veuillez sélectionner un badge à mettre à jour.");
    }
}



//////////////////////////////////////////DELETE///////////////////////////////////
@FXML
private void supprimerBadg(ActionEvent event) throws SQLException {
    Badge badgeSelectionne = tableViewBadge.getSelectionModel().getSelectedItem();
    if (badgeSelectionne != null) {
        User utilisateurAvis = badgeSelectionne.getUser();

        if (utilisateurAvis.getIduser() == utilisateurConnecte) {
            ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setContentText("Voulez-vous supprimer cet Badge ?");
            dialog.getDialogPane().getButtonTypes().addAll(okButtonType, cancelButtonType);

            Optional<ButtonType> result = dialog.showAndWait();

            if (result.isPresent() && result.get() == okButtonType) {
                // Supprimez l'avis
                serviceBadge.supprimer(badgeSelectionne.getId());
                showAlert(Alert.AlertType.CONFIRMATION, "Succès", "Suppression de l'Badge réussie", "");
                Notification2();
                // Appelez ici la méthode pour mettre à jour l'affichage
            } else {
                System.out.println("Annulation de la suppression");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Vous ne pouvez pas supprimer cet Badge", "Cet avis n'appartient pas à vous.");
        }

        // Appelez ici la méthode pour mettre à jour l'affichage si nécessaire
    }
    show();
}


    
    
    //////////////////////////////////////////////Afficher////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void rechercherBadges(ActionEvent event) {
    String restoName = RestoNameTextfieldBadge.getText();
    String badgeName = BadgeRechTextfieldBadge1.getText();

    List<Badge> result = new ArrayList<>();

    if (!restoName.isEmpty() && !badgeName.isEmpty()) {
        
        for (Badge badge : tableViewBadge.getItems()) {
            if (badge.getRestaurant().getNom().toLowerCase().contains(restoName.toLowerCase()) &&
                badge.getTypeBadge().name().toLowerCase().contains(badgeName.toLowerCase())) {
                result.add(badge);
            }
        }
    } else if (!restoName.isEmpty()) {
        
        for (Badge badge : tableViewBadge.getItems()) {
            if (badge.getRestaurant().getNom().toLowerCase().contains(restoName.toLowerCase())) {
                result.add(badge);
            }
        }
    } else if (!badgeName.isEmpty()) {
       
        for (Badge badge : tableViewBadge.getItems()) {
            if (badge.getTypeBadge().name().toLowerCase().contains(badgeName.toLowerCase())) {
                result.add(badge);
            }
        }
    } else {
        
        chargerBadge(event);
        return; 
    }

    tableViewBadge.setItems(FXCollections.observableArrayList(result));
    
}


        public void show() throws SQLException
    {
        
    ObservableList<Badge> data = FXCollections.observableArrayList(ServiceBadge.getInstance().recuperer());
       DateCol.setCellValueFactory(new PropertyValueFactory<>("dateBadge"));
badgeCol.setCellValueFactory(new PropertyValueFactory<>("typeBadge"));
commentaireCol.setCellValueFactory(new PropertyValueFactory<>("commantaire"));
//userCol.setCellValueFactory(new PropertyValueFactory<>("username"));
//nomRestoCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
userCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getUsername()));
nomRestoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRestaurant().getNom()));

        tableViewBadge.setItems(data);
        
     
        FilteredList<Badge> filteredData = new FilteredList<>(data, b -> true);

       
        RestoNameTextfieldBadge.textProperty().addListener((observable, oldValue, newValue) -> {
           filteredData.setPredicate(badge -> {
        String restoName = RestoNameTextfieldBadge.getText().toLowerCase();
        String badgeName = BadgeRechTextfieldBadge1.getText().toLowerCase();

        String badgeRestaurantName = badge.getRestaurant().getNom().toLowerCase();
        String badgeTypeName = badge.getTypeBadge().name().toLowerCase();

        boolean nomRestaurantCorrespond = badgeRestaurantName.contains(restoName);
        boolean nomBadgeCorrespond = badgeTypeName.contains(badgeName);

        return (restoName.isEmpty() || nomRestaurantCorrespond) && (badgeName.isEmpty() || nomBadgeCorrespond);
    });
            });
        

       
        SortedList<Badge> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableViewBadge.comparatorProperty());

        tableViewBadge.setItems(sortedData);   
         
        
    }
        
        
        
        
        
        
        
        
        
    /////////////////////////////////Configuration/////////////////////////////////
    private void configureComboBoxResto() {
    try {
        Servicerestaurant restaurantService = new Servicerestaurant();

        List<String> restaurantNames = restaurantService.getRestaurantNames();

        ObservableList<String> restaurantNameList = FXCollections.observableArrayList(restaurantNames);

      comboBoxRestaurant.setItems(restaurantNameList);
    } catch (SQLException e) {
        showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors du chargement des noms de restaurants", e.getMessage());
        e.printStackTrace();
    }
}
 private void configureColumns() {
DateCol.setCellValueFactory(new PropertyValueFactory<>("dateBadge"));
badgeCol.setCellValueFactory(new PropertyValueFactory<>("typeBadge"));
commentaireCol.setCellValueFactory(new PropertyValueFactory<>("commantaire"));

userCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getUsername()));
nomRestoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRestaurant().getNom()));

 }
 

 

public void Notification(){
	Platform.runLater(new Runnable() {
		@Override
		public void run() {
			Notifications notify = Notifications.create().title("Badge")
					.text("Ajout avec success")
					.hideAfter(javafx.util.Duration.seconds(4))
					.position(Pos.BOTTOM_RIGHT);
			notify.darkStyle();
			notify.showInformation();
		}
	}); 


}
 public void Notification1(){
	Platform.runLater(new Runnable() {
		@Override
		public void run() {
			Notifications notify = Notifications.create().title("Badge")
					.text("modif avec success")
					.hideAfter(javafx.util.Duration.seconds(4))
					.position(Pos.BOTTOM_RIGHT);
			notify.darkStyle();
			notify.showInformation();
		}
	}); 


}
 public void Notification2(){
	Platform.runLater(new Runnable() {
		@Override
		public void run() {
			Notifications notify = Notifications.create().title("Badge")
					.text("Suppression avec success")
					.hideAfter(javafx.util.Duration.seconds(4))
					.position(Pos.BOTTOM_RIGHT);
			notify.darkStyle();
			notify.showInformation();
		}
	}); 


}
    private void showAlert(Alert.AlertType type, String title, String header, String content) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
}

        private void chargerBadge(ActionEvent event) {
        try {
            ObservableList<Badge> observableBadges = FXCollections.observableArrayList(serviceBadge.recuperer());
            tableViewBadge.setItems(observableBadges);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors du chargement des badges", e.getMessage());
            e.printStackTrace();
        }
    }
        
 @FXML
private void OnClickedPrint(ActionEvent event) {
    PrinterJob job = PrinterJob.createPrinterJob();

    Node root = this.tableViewBadge;

    if (job != null) {
        job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage

        Printer printer = job.getPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
        boolean success = job.printPage(pageLayout, root);
        if (success) {
            job.endJob();
        }
    }
}


}

