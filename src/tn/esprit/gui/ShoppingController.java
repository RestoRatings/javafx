/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tn.esprit.entities.Achat;
import tn.esprit.entities.CategorieP;
import tn.esprit.entities.Plat;
import tn.esprit.entities.TypeC;
import tn.esprit.entities.User;
import tn.esprit.entities.dto.Item;
import tn.esprit.services.ServiceAchat;
import tn.esprit.services.ServicePlat;

/**
 * FXML Controller class
 *
 * @author Med-Amine
 */
public class ShoppingController implements Initializable {

    @FXML
    private TableView<Plat> pltTV;
    @FXML
    private TableColumn<Plat, String> nomview;
    @FXML
    private TableColumn<Plat, String> descriptionview;
    @FXML
    private TableColumn<Plat, String> imageview;
    @FXML
    private TableColumn<Plat, Float> prixview;
    @FXML
    private TableColumn<Plat, CategorieP> categorieview;
    @FXML
    private TableColumn<Plat,Integer> plat_idview;
    @FXML
    private TableColumn<Plat, Integer> plat_idView;
    @FXML
    private TextField nomplt;
    @FXML
    private Button AjouterAuPanier;
    @FXML
    private Label Nbr_Plat_Au_panier;
    @FXML
    private TextField quantity_;
    @FXML
    private ComboBox<TypeC> typeCmdbox;
    @FXML
    private ImageView panierImg;

    /**
     * Initializes the controller class.
     */
    
    List<Achat> achatsUser= new ArrayList<Achat>();
    static TableView<Item> tableView = new TableView<>();
    @FXML
    private Button AnnulerAction;
    
    int myIndex;
    int myIndexPannier;  
  

    private int compteur = 0; 

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   typeCmdbox.getItems().setAll(TypeC.values()); //initialisation COMBOBOX

   AFichagePannier();
        

        
      
panierImg.setOnMouseClicked((MouseEvent event) -> {
    // Créez un Label pour le prix total
    Label totalLabel = new Label("Total Price: $" + getTotalePanier(achatsUser));
    totalLabel.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
    
  

    // Créez un VBox pour contenir le TableView et le Label
    VBox vbox = new VBox(tableView, totalLabel);

      Button customButton = new Button("- Quatite");
        customButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        myIndexPannier = tableView.getSelectionModel().getSelectedIndex();
        int Quatite = Integer.parseInt(String.valueOf(tableView.getItems().get(myIndexPannier).getQuantite()));
        Quatite--;
        AFichagePannier();  
        System.out.println(".handle()");
        }
    });
    vbox.getChildren().add(customButton);
    // Créez le Dialog et configurez-le
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Plats Items");
    dialog.getDialogPane().setContent(vbox);

    Button confirmerButton = new Button("Confirmer");
    confirmerButton.setStyle("-fx-background-color: #9efd38; -fx-text-fill: white;");
 


    // Ajoutez le bouton "Annuler" (anciennement "Terminer") avec un style personnalisé (rouge)
    Button annulerButton = new Button("Annuler");
    annulerButton.setStyle("-fx-background-color: #f9393e; -fx-text-fill: white;");

    // Ajoutez les boutons "Confirmer" et "Annuler" aux types de boutons du Dialog
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    // Associez les boutons aux actions souhaitées
    confirmerButton.setOnAction(e -> dialog.setResult(ButtonType.OK));
    annulerButton.setOnAction(e -> {
        dialog.close(); // Fermer la fenêtre Plats Items
    });

    // Définissez le style du fond de la boîte de dialogue en noir
    dialog.getDialogPane().setStyle("-fx-background-color: black;");



    Optional<ButtonType> result = dialog.showAndWait();
    if (result.isPresent()) {
        if (result.get() == ButtonType.OK) {
            System.out.println("Mouse clicked on Confirmer");
            // Code à exécuter lorsque le bouton "Confirmer" est cliqué
        } else if (result.get() == ButtonType.CANCEL) {
            System.out.println("Mouse clicked on Annuler");
            // Code à exécuter lorsque le bouton "Annuler" est cliqué
        }
    }

  System.out.println("List Des Achats : " + achatsUser);
  System.out.println("Mouse clicked on the image");
});

        
        try {
            // TODO
            PlatTable();
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



@FXML
private void AjouterAuPanier(ActionEvent event) throws SQLException {
     
      if(!nomplt.getText().isEmpty()){
       myIndex = pltTV.getSelectionModel().getSelectedIndex();
       int idplat = Integer.parseInt(String.valueOf(pltTV.getItems().get(myIndex).getIdplat()));
       float prix = pltTV.getItems().get(myIndex).getPrix();

       Plat plat = new Plat();
       plat.setIdplat(idplat);
       plat.setPrix(prix);
       User user = new User();
       user.setIduser(24);
       Achat achatPourAjouter =new Achat(user,plat,0,Integer.parseInt(quantity_.getText()),new Date(System.currentTimeMillis()),typeCmdbox.getValue());
       achatsUser.add(achatPourAjouter);
       
    //read indice from nbr du pannier
    //  int indice_element= Integer.parseInt(Nbr_Plat_Au_panier.getText());
      int quantite_du_plat = Integer.parseInt(quantity_.getText());
       Item item1 = new Item(nomplt.getText(), prix, quantite_du_plat);

  
    increment_Panier_Nbr_Plat();
     
    tableView.getItems().addAll(item1);
    }
}




    public void PlatTable() throws SQLException {
     ServicePlat ServiceEvent= new ServicePlat();
     ArrayList<Plat> challengess = new ArrayList<>();
              
    challengess=  (ArrayList<Plat>) ServiceEvent.recuperer();
              
  
    ObservableList<Plat> obsl = FXCollections.observableArrayList(challengess); 
  
    pltTV.setItems(obsl);
    categorieview.setCellValueFactory(new  PropertyValueFactory<>("categorie")); 
    prixview.setCellValueFactory(new  PropertyValueFactory<>("prix"));
    descriptionview.setCellValueFactory(new  PropertyValueFactory<>("description"));
    nomview.setCellValueFactory(new  PropertyValueFactory<>("nom"));
    plat_idView.setCellValueFactory(new  PropertyValueFactory<>("idplat")); 


    pltTV.setRowFactory( tv -> {
     TableRow<Plat> myRow = new TableRow<>();
     myRow.setOnMouseClicked (event ->
     {
        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
        {
           myIndex =  pltTV.getSelectionModel().getSelectedIndex();
            
     

    nomplt.setText(pltTV.getItems().get(myIndex).getNom());       
        }
     });
        return myRow;
    });
    
    }
        
        public float getTotalePanier(List<Achat> achatsUser){
        float Total = 0.0f;

        for (Achat A : achatsUser) {
        Plat plat = A.getPlat(); 
        if (plat != null) {
            Total += plat.getPrix() * A.getQuantite(); 
        }
    }
    return Total;    
    }
        
        
     public void  increment_Panier_Nbr_Plat(){
  
    compteur++;
    
    Nbr_Plat_Au_panier.setText(Integer.toString(compteur));
}
     


    @FXML
    private void Clear_Pannier(ActionEvent event) {
        
    compteur = 0;

    tableView.getItems().clear();
    Nbr_Plat_Au_panier.setText("0");
    achatsUser.clear();
    
        
        
    }
TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
TableColumn<Item, Double> priceColumn = new TableColumn<>("Price");
TableColumn<Item, Integer> quantiteColumn = new TableColumn<>("Quantite");

private void AFichagePannier() {
nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));

tableView.getColumns().addAll(nameColumn, priceColumn,quantiteColumn);

    tableView.setRowFactory( tv -> {
     TableRow<Item> myRow = new TableRow<>();
     myRow.setOnMouseClicked (event ->
     {
        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
        {
           myIndexPannier =  tableView.getSelectionModel().getSelectedIndex();
            
            System.out.println("i Got the Row");

        }
     });
        return myRow;
    });    }

}
