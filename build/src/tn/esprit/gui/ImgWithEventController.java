package tn.esprit.gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import tn.esprit.entities.Evennement;
import tn.esprit.services.Eventservice;
import tn.esprit.services.IserviceEvenement;

/**
 * FXML Controller class
 *
 * @author Med Iheb
 */
public class ImgWithEventController implements Initializable {
   private Stage stage;
    private Stage scene;
   
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane NumeroParticipantT;

    /**
     * Initializes the controller class.
     */
    
    public static String num;
    
    static TextField NumerParticipant;
    @FXML
    private TextField chercherevent;
    @FXML
    private ComboBox<String> comboCat;

List<Evennement> eqs = new ArrayList<Evennement>();
    @FXML
    private Button btnretouruser;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Eventservice sc = new Eventservice();
        // TODO
            this.eqs = sc.getAll();
        try {
            afficherEquipementsMag(eqs);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
         comboCat.getItems().addAll("titre","lieu","date");
    }  
  

    @FXML
    private void chercherProduitsMag(KeyEvent event) {
        
    }

    @FXML
    private void clearSelection(MouseEvent event) {
    }


    @FXML
    private void switchToItemInt(MouseEvent event) {
    }
    
        public void afficherEquipementsMag(List<Evennement> eqs) throws IOException {
            IserviceEvenement sc = new Eventservice();
        int column = 0;
        int row = 1;
        try{
        for (int i = 0; i < this.eqs.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("itemEquipement.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            ItemEquipementController itemController = fxmlLoader.getController();
            
            itemController.setData(this.eqs.get(i));

            if (column == 1) {
                column = 0;
                row++;
            }
            grid.add(anchorPane, column++, row); 
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
    private static void createQRImage(File qrFile, String qrCodeText, int size, String fileType)
            throws WriterException, IOException {
        // Create the ByteMatrix for the QR-Code that encodes the given String
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, 500, 500, hintMap);
        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // Paint and save the image using the ByteMatrix
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        ImageIO.write(image, fileType, qrFile);

    }

    @FXML
    private void btnchercherevent(ActionEvent event) {
        
           Eventservice sc = new Eventservice();
             String selectedTypeReseach = comboCat.getValue();
            String GetResearchValue =chercherevent.getText();
        this.eqs =sc.recupererBytitreByDateByLieu(GetResearchValue, selectedTypeReseach);
       
        try {
             grid.getChildren().clear();
            afficherEquipementsMag(this.eqs);
        } catch (IOException ex) {
            Logger.getLogger(ImgWithEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retouruser(ActionEvent event) {
          try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
    }
    


