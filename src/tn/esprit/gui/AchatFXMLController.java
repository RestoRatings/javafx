/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import tn.esprit.entities.Achat;
import tn.esprit.entities.CategorieP;
import tn.esprit.entities.Plat;
import tn.esprit.entities.TypeC;
import tn.esprit.entities.User;
import static tn.esprit.gui.PlatWithImgController.achatsUser;
import tn.esprit.services.ServiceAchat;
import tn.esprit.services.ServicePlat;

/**
 * FXML Controller class
 *
 * @author Med-Amine
 */
public class AchatFXMLController implements Initializable {

    @FXML
    private TableView<Achat> achatTV;
    @FXML
    private TableColumn<Achat, String> clientview;
    @FXML
    private TableColumn<Achat, String> platview;
    @FXML
    private TableColumn<Achat, Integer> quantiteview;
    @FXML
    private TableColumn<Achat, Float> Mtotalview;
    @FXML
    private TableColumn<Achat, Date> dateview;
  //  @FXML
  //  private TableColumn<Achat, ?> plat_idview;
    //@FXML
  //  private TableColumn<Achat, ?> plat_idView;
    @FXML
    private TableColumn<Achat, TypeC> typeview;
    @FXML
    private TextField Rechercheachat;
    @FXML
    private TextField nomclientacht;
    @FXML
    private TextField pltach;
    @FXML
    private Button btmdfach;
    @FXML
    private Button btsupach;
    @FXML
    private ComboBox<TypeC> typeachbox;
    @FXML
    private TextField mntnach;
    @FXML
    private TextField quntach;
    @FXML
    private DatePicker dateach;
 
    @FXML
    private ImageView imgrechAch;
    @FXML
    private ComboBox<String> typerechAch;
    @FXML
    private TableColumn<?, ?> plat_idview;
    @FXML
    private TableColumn<?, ?> plat_idView;
    @FXML
    private ImageView actu_img;

     private PlatWithImgController platWithImgController;





    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        dateach.setVisible(false);

    achatTV.setRowFactory(tv -> {
        TableRow<Achat> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && !row.isEmpty()) {
                Achat achat = row.getItem();
                afficherAchatDansChamps(achat);
                dateach.setVisible(true);
                dateach.setValue(achat.getDate().toLocalDate());
            }
        });
        return row;
    });

    achatTV.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            dateach.setVisible(true);
            dateach.setValue(newSelection.getDate().toLocalDate());
        } else {
            dateach.setVisible(false);
        }
    });
        
        
        
        typeachbox.getItems().setAll(TypeC.values());
     
        try {
            AchatTable();
        } catch (SQLException ex) {
            Logger.getLogger(AchatFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
  
               imgrechAch.setOnMouseClicked(event -> {
    try {
        rechercherAchats(new ActionEvent()); 
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
});
       actu_img.setOnMouseClicked(event -> {
    try {
        actualiserTableView(new ActionEvent()); 
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
});
               
       typerechAch.getItems().addAll("Date", "Type");
  
        
    }    
    int myIndex;

    private void AchatTable() throws SQLException{
   ServiceAchat ServiceEvent= new ServiceAchat();
     ArrayList<Achat> challengess = new ArrayList<>();
              
    challengess=  (ArrayList<Achat>) ServiceEvent.recuperer();
              
  
    ObservableList<Achat> obsl = FXCollections.observableArrayList(challengess); 
  
    achatTV.setItems(obsl);
  
    Mtotalview.setCellValueFactory(new  PropertyValueFactory<>("montanttotal"));
    quantiteview.setCellValueFactory(new  PropertyValueFactory<>("quantite"));
    dateview.setCellValueFactory(new  PropertyValueFactory<>("date"));
    typeview.setCellValueFactory(new  PropertyValueFactory<>("typec"));
    clientview.setCellValueFactory(cellData ->
    {
       return new SimpleStringProperty(cellData.getValue().getUser().getUsername());
   });
    platview.setCellValueFactory(cellData -> {
    return new SimpleStringProperty(cellData.getValue().getPlat().getNom());
    });
    

  

  achatTV.setRowFactory( tv -> {
     TableRow<Achat> myRow = new TableRow<>();
     myRow.setOnMouseClicked (event ->
     {
        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
        {
           myIndex =  achatTV.getSelectionModel().getSelectedIndex();
            
    // nomclientacht.setText(achatTV.getItems().get(myIndex).getUser().getFirstName());
    // pltach.setText(achatTV.getItems().get(myIndex).getPlat().getNom());
     mntnach.setText(Float.toString(achatTV.getItems().get(myIndex).getMontanttotal()));
     quntach.setText(Integer.toString(achatTV.getItems().get(myIndex).getQuantite()));
     dateach.setValue(LocalDate.now());
     typeachbox.setValue(achatTV.getItems().get(myIndex).getTypec());
     
     
        }
     });
        return myRow;
                   });
    
    
    
    }


private boolean estMontantValide(String montantText) {
    try {
        float montant = Float.parseFloat(montantText);
        return montant > 0; 
    } catch (NumberFormatException e) {
        return false; 
    }
}

private boolean estQuantiteValide(String quantiteText) {
    try {
        int quantite = Integer.parseInt(quantiteText);
        return quantite > 0; 
    } catch (NumberFormatException e) {
        return false; 
}
}

    private void afficherAlerteErreur(String titre, String contenu) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null); // Supprimer l'en-tête du message
        alert.setContentText(contenu);
        alert.showAndWait();}


private void afficherAlerteSucces(String titre, String contenu) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(titre);
    alert.setHeaderText(contenu);
    alert.showAndWait();
}
  private boolean estChampNonVide(TextField champ) {
    String texte = champ.getText().trim(); 
     return !texte.isEmpty();
  }

    @FXML
    private void AchatUpdate(ActionEvent event) throws SQLException {
        
        int myIndex = achatTV.getSelectionModel().getSelectedIndex();
    

    if (myIndex >= 0) {
        int idachat = Integer.parseInt(String.valueOf(achatTV.getItems().get(myIndex).getIdachat()));

        User user = new User();
        user.setIduser(24);
        user.setUsername("Amine");
        Plat plat = new Plat();
        plat.setIdplat(28);

        String MontantText = mntnach.getText();
        String quantiteText = quntach.getText();
        TypeC typec = typeachbox.getValue();

        if (estMontantValide(MontantText) && estQuantiteValide(quantiteText) && typec != null) {
            float montanttotal = Float.parseFloat(MontantText);
            int quantite = Integer.parseInt(quantiteText);
            java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());

            Achat achatPourUpdate = new Achat(idachat, user, plat, montanttotal, quantite, date, typec);

            ServiceAchat _serviceAchat = new ServiceAchat();
            _serviceAchat.modifier(achatPourUpdate);
            AchatTable();
            afficherAlerteSucces("Succès", "Achat mis à jour avec succès !");
        } else {
            afficherAlerteErreur("Erreur de saisie", "Veuillez remplir tous les champs correctement.");
        }
    } else {
        afficherAlerteErreur("Erreur de sélection", "Veuillez sélectionner un achat à mettre à jour.");
    }
}
    

@FXML
private void AchatDelete(ActionEvent event) throws SQLException {
    int myIndex = achatTV.getSelectionModel().getSelectedIndex();

    if (myIndex >= 0) {
        Alert confirmation = new Alert(AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation de suppression");
        confirmation.setHeaderText("Voulez-vous vraiment supprimer cet achat ?");
        confirmation.setContentText("Cette action est irréversible.");

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.get() == ButtonType.OK) {
            int idachat = Integer.parseInt(String.valueOf(achatTV.getItems().get(myIndex).getIdachat()));
            ServiceAchat _serviceAchat = new ServiceAchat();
            _serviceAchat.supprimer(idachat);
            AchatTable();
            afficherAlerteSucces("Succès", "Achat supprimé avec succès !");
        } else {
            
        }
    } else {
        afficherAlerteErreur("Erreur de sélection", "Veuillez sélectionner un achat à supprimer.");
    }
}


    

  

 private ServiceAchat serviceAchat = new ServiceAchat();
private ObservableList<Achat> achatsData = FXCollections.observableArrayList();

private void rechercherAchats(ActionEvent event) throws SQLException {
    List<Achat> achats = null;

    String typeRecherche = typerechAch.getValue();
    String recherche = Rechercheachat.getText();

    if (typeRecherche.equals("Date")) {
        try {
            Date dateRecherche = Date.valueOf(recherche);
            achats = recupererByDate(dateRecherche);
        } catch (IllegalArgumentException e) {
            afficherAlerte("Erreur de recherche", "La date de recherche n'est pas au format YYYY-MM-DD.", Alert.AlertType.ERROR);
            return;
        }
    } else if (typeRecherche.equals("Type")) {
        try {
            TypeC type = TypeC.valueOf(recherche);
            achats = recupererByType(type);
        } catch (IllegalArgumentException e) {
            afficherAlerte("Erreur de recherche", "Type d'achat invalide.", Alert.AlertType.ERROR);
            return;
        } catch (SQLException e) {
            afficherAlerte("Erreur de recherche", "Une erreur s'est produite lors de la recherche.", Alert.AlertType.ERROR);
            e.printStackTrace();
            return;
        }
    }

    if (achats != null && !achats.isEmpty()) {
        achatsData.setAll(achats);
        achatTV.setItems(achatsData);
        afficherAlerte("Résultats de la recherche", achats.size() + " achats trouvés.", Alert.AlertType.INFORMATION);
    } else {
        afficherAlerte("Aucun résultat", "Aucun résultat trouvé pour la recherche.", Alert.AlertType.INFORMATION);
    }


}
private void afficherAlerte(String titre, String contenu, AlertType alertType) {
    Alert alert = new Alert(alertType);
    alert.setTitle(titre);
    alert.setHeaderText(null);
    alert.setContentText(contenu);
    alert.showAndWait();
}

   private List<Achat> recupererByDate(Date dateRecherche) throws SQLException {
        return serviceAchat.recupererByDate(dateRecherche);
    
   
}
private List<Achat> recupererByType(TypeC type) throws SQLException {
    return serviceAchat.recupererByType(type);
}
private void actualiserTableView(ActionEvent event) throws SQLException {
    AchatTable(); 

    }
private void afficherAchatDansChamps(Achat achat) {
    if (achat != null) {
        nomclientacht.setText(achat.getUser().getUsername());
        pltach.setText(achat.getPlat().getNom());
        mntnach.setText(String.valueOf(achat.getMontanttotal()));
        quntach.setText(String.valueOf(achat.getQuantite()));
        dateach.setValue(achat.getDate().toLocalDate());
        typeachbox.setValue(achat.getTypec());
    }
}

    private float getTotalePanier(List<Achat> achatsUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }






}





    

