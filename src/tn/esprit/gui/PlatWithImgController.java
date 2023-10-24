/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Date;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import tn.esprit.entities.Achat;
import tn.esprit.entities.CategorieP;
import tn.esprit.entities.Evennement;
import tn.esprit.entities.Plat;
import tn.esprit.entities.TypeC;
import tn.esprit.entities.dto.Item;
import tn.esprit.services.IservicePlat;
import tn.esprit.services.ServicePlat;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.output.bitmap.BitmapEncoder;
import org.krysalis.barcode4j.output.bitmap.BitmapEncoderRegistry;
import org.krysalis.barcode4j.tools.UnitConv;
import tn.esprit.services.ServiceAchat;

/**
 * FXML Controller class
 *
 * @author Med-Amine
 */
public class PlatWithImgController implements Initializable {

    @FXML
    private AnchorPane NumeroParticipantT;
    @FXML
    private TextField chercherMagasin;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private ImageView panierImg;
    @FXML
    private  Label Nbr_Plat_Au_panier;
    @FXML
    private Button AnnulerAction;

    /**
     * Initializes the controller class.
     */
        static List<Achat> achatsUser= new ArrayList<Achat>();
    TableView<Item> tableView = new TableView<>();
       int myIndex;
    int myIndexPannier;  
  float x=0;
static ObservableList<Item> platItemsData = FXCollections.observableArrayList();

     static int compteur;
     static int nombre;
    @FXML
    private ComboBox<String> typeCmdbox;
    
             static List<Plat> listEvent= new ArrayList<Plat>();
    @FXML
    private Button btnrech_brh;
    @FXML
    private RadioButton dessertRadio;
    @FXML
    private RadioButton pizzaRadio;
    @FXML
    private RadioButton boissonsRadio;
    @FXML
    private RadioButton bergerRadio;
    @FXML
    private ImageView actuimg;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
            
        
         actuimg.setOnMouseClicked(event -> {
    try {
          grid.getChildren().clear();

        afficherEquipementsMag(); 
    } catch (SQLException e) {
        e.printStackTrace();
    }        catch (IOException ex) {
                 Logger.getLogger(PlatWithImgController.class.getName()).log(Level.SEVERE, null, ex);
             }
    
});
        
        
        IservicePlat scc = new ServicePlat();
  
        try {
             this.listEvent = scc.recuperer();
        } catch (SQLException ex) {
            Logger.getLogger(PlatWithImgController.class.getName()).log(Level.SEVERE, null, ex);
        }
          typeCmdbox.getItems().addAll("Nom","Prix");
        ServicePlat sc = new ServicePlat();
    
    
panierImg.setOnMouseClicked((MouseEvent event) -> {
    Label totalLabel = new Label("Total Price: $" + getTotalePanier(achatsUser));
    totalLabel.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold");
    
    ServiceAchat sa = new ServiceAchat();

    VBox vbox = new VBox(tableView, totalLabel);
    vbox.setMinWidth(600);
    vbox.setMinHeight(400);

    
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Plats Items");
    dialog.getDialogPane().setContent(vbox);

    ButtonType confirmerButtonType = new ButtonType("OK", ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(confirmerButtonType, ButtonType.CANCEL);

    //  bouton "OK"
    dialog.setResultConverter(buttonType -> {
        
        if (buttonType == confirmerButtonType) {
            for(int i=0;i<this.achatsUser.size();i++){
                try {
                    this.achatsUser.get(i).setMontanttotal(this.achatsUser.get(i).getQuantite()*this.achatsUser.get(i).getPlat().getPrix());
                    sa.ajouter(this.achatsUser.get(i));
                } catch (SQLException ex) {
                    Logger.getLogger(PlatWithImgController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationAlert.setTitle("Confirmation PDF");
    confirmationAlert.setHeaderText(null);
    confirmationAlert.setContentText("Voulez-vous générer le PDF du panier ?");
    
    ButtonType ouiButtonType = new ButtonType("Oui", ButtonData.YES);
    ButtonType nonButtonType = new ButtonType("Non", ButtonData.NO);
    confirmationAlert.getButtonTypes().setAll(ouiButtonType, nonButtonType);
    
        Optional<ButtonType> confirmationResult = confirmationAlert.showAndWait();
            if (confirmationResult.isPresent() && confirmationResult.get() == ouiButtonType) {

            Document document = new Document();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String outputPath = "C:\\Users\\Med-Amine\\Documents\\NetBeansProjects\\restoratings\\PDF Panier\\panier_" + sdf.format(new Date()) + ".pdf";
                PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(outputPath));
                document.open();
                
                Font titleFont = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
                Paragraph title = new Paragraph("Resto-Ratings", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

               
                Image headerImage = Image.getInstance("C:\\Users\\Med-Amine\\Documents\\NetBeansProjects\\restoratings\\src\\image\\logo-restaurant.png");
                headerImage.setAlignment(Element.ALIGN_CENTER);
                headerImage.scaleAbsolute(100, 100); 
                document.add(headerImage);

                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100);

                Font tableHeaderFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
                PdfPCell cell = new PdfPCell(new Phrase("Produit", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Quantité", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Prix unitaire", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Type de commande", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Sous total", tableHeaderFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                

                Font tableCellFont = new Font(Font.FontFamily.HELVETICA, 10);
                double totalAmount = 0; 
for (Achat achat : achatsUser) {
    cell = new PdfPCell(new Phrase(achat.getPlat().getNom(), tableCellFont));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase(String.valueOf(achat.getQuantite()), tableCellFont));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase(achat.getPlat().getPrix() + "DT", tableCellFont));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell);

cell = new PdfPCell(new Phrase(achat.getTypec() != null ? achat.getTypec().toString() : "", tableCellFont));
cell.setHorizontalAlignment(Element.ALIGN_CENTER);
table.addCell(cell);

    double subtotal = achat.getPlat().getPrix() * achat.getQuantite();
    //if (achat.getTypec() != null && achat.getTypec() == TypeC.livraison) {
        //subtotal += 2.5;
    //}

    cell = new PdfPCell(new Phrase(subtotal + "DT", tableCellFont));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell);

    totalAmount += subtotal;

}
                
                document.add(table);

                PdfContentByte content = pdfWriter.getDirectContent();
                Image footerImage = Image.getInstance("C:\\Users\\Med-Amine\\Documents\\NetBeansProjects\\restoratings\\src\\image\\cachet.png");
                footerImage.scaleAbsolute(130, 125); 
                footerImage.setAbsolutePosition(document.getPageSize().getWidth() - 260, 70);
                content.addImage(footerImage);

                Font totalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
                Paragraph totalParagraph;
if (achatsUser.stream().anyMatch(achat -> achat.getTypec() == TypeC.livraison)) {
    totalAmount += 2.5; 
    totalParagraph = new Paragraph("Montant Total DT: " + totalAmount + " (Frais de livraison DT: 2.5)", totalFont);
} else {
    totalParagraph = new Paragraph("Montant Total DT: " + totalAmount, totalFont);
}

totalParagraph.setAlignment(Element.ALIGN_RIGHT);
document.add(totalParagraph);

    
            //barre    
Code128Bean code128 = new Code128Bean();
final int dpi = 100;
code128.setModuleWidth(UnitConv.in2mm(1.0f / dpi));
code128.doQuietZone(false);

// un id unique PDF
String uniqueIdentifier = java.util.UUID.randomUUID().toString(); 

String barcodeData = uniqueIdentifier;

File barcodeFile = new File("C:\\Users\\Med-Amine\\Documents\\NetBeansProjects\\restoratings\\codeabarre\\barcode.png");
FileOutputStream fos = new FileOutputStream(barcodeFile);
BitmapCanvasProvider canvas = new BitmapCanvasProvider(fos, "image/png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
code128.generateBarcode(canvas, barcodeData);
canvas.finish();

Image barcodeImage = Image.getInstance(barcodeFile.getAbsolutePath());
barcodeImage.setAlignment(Element.ALIGN_CENTER);

Paragraph emptyLines = new Paragraph("\n\n\n");
document.add(emptyLines);
document.add(emptyLines);
document.add(emptyLines);

document.add(barcodeImage);

    
              document.close();
                
    

 System.out.println("PDF généré avec succès : " + outputPath);
 Alert pdfCreatedAlert = new Alert(Alert.AlertType.INFORMATION);
        pdfCreatedAlert.setTitle("PDF Créé");
        pdfCreatedAlert.setHeaderText(null);
        pdfCreatedAlert.setContentText("Le PDF du panier a été créé avec succès " );
        pdfCreatedAlert.showAndWait();
      
            } catch (Exception ex) {
            ex.printStackTrace();
        }
            }else if(confirmationResult.isPresent() && confirmationResult.get() == nonButtonType) {
            
    System.out.println("Génération du PDF annulée.");

    Alert noPDFAlert = new Alert(Alert.AlertType.INFORMATION);
    noPDFAlert.setTitle("Génération du PDF Annulée");
    noPDFAlert.setHeaderText(null);
    noPDFAlert.setContentText("Commande Confirmer et génération du PDF a été annulée.");
    noPDFAlert.showAndWait(); }}
        return null;
});
    // Afch  dialogue
    dialog.getDialogPane().setStyle("-fx-background-color: black");
    Optional<ButtonType> result = dialog.showAndWait();

    if (result.isPresent() && result.get() == confirmerButtonType) {
        System.out.println("Mouse clicked on OK");
    } else {
        System.out.println("Mouse clicked on Annuler");
    }

    System.out.println("Liste des Achats : " + achatsUser);
    System.out.println("Mouse clicked on the image");
});

        // TODO
        try {
            afficherEquipementsMag();
            AFichagePannier();
            this.Nbr_Plat_Au_panier.setText(String.valueOf(compteur));
            
            // TODO
        } catch (IOException ex) {
            Logger.getLogger(PlatWithImgController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlatWithImgController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
         
@FXML
private void chercherProduitsMag(KeyEvent event) {
}
    @FXML
    private void clearSelection(MouseEvent event) {
    }

 public void updateLabelValue(String newValue) {
        Nbr_Plat_Au_panier.setText(newValue);
    }
 




    @FXML
    private void switchToItemInt(MouseEvent event) {
    }
          public void afficherEquipementsMag() throws IOException, SQLException {
              IservicePlat sc = new ServicePlat();
        int column = 0;
        int row = 1;
        List<Plat> listEvent = sc.recuperer();
            System.out.println("List Event : "+listEvent);
        try{
        for (int i = 0; i < listEvent.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("itemEquipement.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            ItemEquipementController itemController = fxmlLoader.getController();
            
            itemController.setData(listEvent.get(i));

            if (column == 1) {
                column = 0;
                row++;
            }

            grid.add(anchorPane, column++, row); //
            //set grid width
            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);

            //set grid height
            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);
            
            GridPane.setMargin(anchorPane, new Insets(10));
        }
            }catch(IOException ex){
                     ex.printStackTrace();
                    }
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
    
    
    
   void  increment_Panier_Nbr_Plat(){
  
    compteur++;
    
    this.Nbr_Plat_Au_panier.setText(Integer.toString(compteur));
}
   
   
   
public float getTotalePanier(List<Achat> achatsUser) {
    float total = 0.0f;
    for (Achat achat : achatsUser) {
        Plat plat = achat.getPlat();
        if (plat != null) {
            total += plat.getPrix() * achat.getQuantite();
        }
    }

    
    for (Achat achat : achatsUser) {
        if (achat.getTypec() == TypeC.livraison) {
            total += 2.5f; 
        }
    }

    return total;
}
 

          
           public void updateTableView(Item item) {
        tableView.getItems().add(item);
    }
           
            private ServicePlat servicePlat = new ServicePlat();
private ObservableList<Plat> platsData = FXCollections.observableArrayList();


private void afficherAlerte(String titre, String contenu, Alert.AlertType type) {
    Alert alert = new Alert(type);
    alert.setTitle(titre);
    alert.setHeaderText(contenu);
    alert.showAndWait();
}


        private List<Plat> recupererByNom(String nom) throws SQLException {
        return servicePlat.recupererByNom(nom);
    }

    private List<Plat> recupererByCategorie(CategorieP categorie) throws SQLException {
        return servicePlat.recupererByCategorie(categorie);
    }

    private List<Plat> recupererByPrix(float prix) throws SQLException {
        return servicePlat.recupererByPrix(prix);
    }

@FXML
private void recherchclient(ActionEvent event) throws SQLException {
    String recherche = chercherMagasin.getText();
    List<Plat> plats = new ArrayList<>();
    
    List<CategorieP> categoriesSelectionnees = new ArrayList<>();

    if (dessertRadio.isSelected()) {
        categoriesSelectionnees.add(CategorieP.Dessert);
    }
    if (pizzaRadio.isSelected()) {
        categoriesSelectionnees.add(CategorieP.Pizza);
    }
    if (boissonsRadio.isSelected()) {
        categoriesSelectionnees.add(CategorieP.Boissons);
    }
    if (bergerRadio.isSelected()) {
        categoriesSelectionnees.add(CategorieP.Berger);
    }


    if (categoriesSelectionnees.isEmpty()) {
        if (!recherche.isEmpty()) {
            String typeRecherche = typeCmdbox.getValue();
            if (typeRecherche == null) {
                afficherAlerte("Erreur de recherche", "Veuillez sélectionner un type de recherche.", Alert.AlertType.ERROR);
                return;
            }
            if (typeRecherche.equals("Nom")) {
                plats.addAll(recupererByNom(recherche));
            } else if (typeRecherche.equals("Prix")) {
                try {
                    float recherchePrix = Float.parseFloat(recherche);
                    plats.addAll(recupererByPrix(recherchePrix));
                } catch (NumberFormatException e) {
                    afficherAlerte("Erreur de recherche", "La valeur de recherche de prix n'est pas valide.", Alert.AlertType.ERROR);
                    return;
                }
            }
        } else {
            afficherAlerte("Erreur de recherche", "Veuillez sélectionner au moins une catégorie ou saisir un critère de recherche.", Alert.AlertType.ERROR);
            return;
        }
    } else {
        for (CategorieP categorie : categoriesSelectionnees) {
            plats.addAll(recupererByCategorie(categorie));
        }
    }
    
    if (!plats.isEmpty()) {
        afficherPlatsDansGrille(plats);
    } else {
        afficherAlerte("Aucun résultat", "Aucun plat trouvé pour la recherche.", Alert.AlertType.INFORMATION);
    }
}




private void afficherPlatsDansGrille(List<Plat> plats) {
    grid.getChildren().clear(); 

    int column = 0;
    int row = 1;

    for (Plat plat : plats) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("itemEquipement.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            ItemEquipementController itemController = fxmlLoader.getController();
            itemController.setData(plat);

            if (column == 1) {
                column = 0;
                row++;
            }

            grid.add(anchorPane, column++, row);
            GridPane.setMargin(anchorPane, new Insets(10));
        } catch (IOException ex) {
            ex.printStackTrace();
        }}}

    private List<Plat> recupererTousLesElements() throws SQLException {
         ServicePlat servicePlat = new ServicePlat();
    return servicePlat.recuperer();
    }
    
    
}
    