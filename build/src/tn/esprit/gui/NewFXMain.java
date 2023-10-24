/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author LENOVO
 */
public class NewFXMain extends Application {
     public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    //Login.fxml
    //ResetPassword.fxml
    //addevnt
         //barcharts 
      //addevnt
      //ParticpeEvent
      //ImgWithEvent
    /////////////////////////
       //AjouterPlat
        //PlatWithImg
        //AchatFXML
        //StatsbrhFXML
    //////////////////////////
        // GestionBadge
        // GestionAvis
        // BackGestionBadge
        // BackGestionAvis
   public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
   
/*
    public static void main(String[] args) {
        launch(args);
    }
}
*/   
     /*
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminMainForm.fxml"));
        Parent root = loader.load();

        // Obtenez une référence au contrôleur de la fenêtre
        AdminMainFormController adminController = loader.getController();

        Scene scene = new Scene(root);

        primaryStage.setTitle("Admin Main Form");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Vous pouvez maintenant tester des méthodes du contrôleur, telles que loadUserData, en les appelant depuis adminController
        adminController.loadUserData();
    }
*/
}
    

