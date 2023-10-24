/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import tn.esprit.entities.Achat;
import tn.esprit.entities.Evennement;
import tn.esprit.entities.Plat;
import tn.esprit.entities.TypeC;
import tn.esprit.entities.User;
import tn.esprit.entities.dto.Item;
import static tn.esprit.gui.PlatWithImgController.compteur;

/**
 * FXML Controller class
 *
 * @author Med-Amine
 */
public class ItemEquipementtController implements Initializable {

    @FXML
    private ImageView pngBoxRecompense;
    @FXML
    private Label nameLabel;
    @FXML
    private ImageView imgItemProduit;
    @FXML
    private Label priceLable;
    @FXML
    private Label IdItem;
    @FXML
    private Label descriptionlabel;
    @FXML
    private TextField quantity_;
    @FXML
    private ComboBox<TypeC> typeCmdbox;
    @FXML
    private Button BTaddpannier;
    
    PlatWithImgController platWithImgController = new PlatWithImgController();
    @FXML
    private Label idplat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         typeCmdbox.getItems().setAll(TypeC.values()); 
      FadeTransition fade = new FadeTransition();
  fade.setNode(pngBoxRecompense);
  
  fade.setDuration(Duration.millis(3000));
  fade.setCycleCount(TranslateTransition.INDEFINITE);
  fade.setInterpolator(Interpolator.LINEAR);
  fade.setFromValue(0);
  fade.setToValue(1);
  fade.play();
        // TODO
    } 
    
    
        public void setData(Plat eq) throws MalformedURLException {
        
        this.nameLabel.setText( "Nom du Plat: "+eq.getNom());
        this.priceLable.setText( valueOf(eq.getPrix()));
      //  this.titrelabel.setText(eq.getTitre());
        this.descriptionlabel.setText(eq.getDescription());
//        this.priceLable.setStrikethrough(true);
        this.IdItem.setText(String.valueOf(eq.getIdplat()));


        Image imn = new Image(
                "file:/" + eq.getImage());
        imgItemProduit.setImage(imn);
        System.out.println("file:/" + eq.getImage());
    }


@FXML
private void AjouterPannier(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("PlatWithImg.fxml"));
    Parent root = loader.load();
    PlatWithImgController controller = loader.getController();
    controller.compteur++;
    controller.updateLabelValue(String.valueOf(controller.compteur));
    Plat plat = new Plat();
    plat.setIdplat(Integer.parseInt(IdItem.getText()));
    plat.setPrix(Float.parseFloat(priceLable.getText()));
    plat.setNom(nameLabel.getText());
    User user = new User();
    user.setIduser(32);
    Achat achatPourAjouter = new Achat(user, plat, 0, Integer.parseInt(quantity_.getText()), new Date(System.currentTimeMillis()), typeCmdbox.getValue());
    controller.achatsUser.add(achatPourAjouter);
    // Ne créez pas de nouvelle ObservableList à chaque fois
    Item item1 = new Item(nameLabel.getText(), Double.parseDouble(priceLable.getText()), Integer.parseInt(quantity_.getText()));
    controller.platItemsData.add(item1);

    // Pas besoin de spécifier l'index lors de l'ajout
    controller.tableView.setItems(controller.platItemsData);

    Scene scene = ((Node) event.getSource()).getScene();
    scene.setRoot(root);
}








    /*        if(!nomplt.getText().isEmpty()){
       myIndex = pltTVs.getSelectionModel().getSelectedIndex();
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
    }*/
    }

    

