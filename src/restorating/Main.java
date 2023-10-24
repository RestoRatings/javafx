/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package restorating;



/*import tn.esprit.entities.User;
import tn.esprit.entities.UserRole;
import tn.esprit.services.ServiceUser;
import tn.esprit.services.IServiceUser;
import tn.esprit.utils.Datasource;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author remo
 */
/*public class Main {

    /**
     * @param args the command line arguments
     */

    /*public static void main(String[] args) {
        // TODO code application logic here
              /*  Connection cnx;
                cnx = Datasource.getInstance().getCnx();
 IServiceUser userService = new ServiceUser();

        // Ajouter un nouvel utilisateur
       User newUser = new User("yamen","yamen@test.com","123456","Yamen","khefacha","55530947","",UserRole.ADMIN);
        
        userService.ajouter(newUser);
        System.out.println("Utilisateur ajouté : " + newUser); 
        
   List<User> users = userService.afficherTous();
System.out.println("Liste de tous les utilisateurs :");
for (User user : users) {
    System.out.println("ID: " + user.getIduser());
    System.out.println("Nom d'utilisateur: " + user.getUsername());
    System.out.println("Email: " + user.getEmail());
    System.out.println("Mot de passe: " + user.getPassword());
    System.out.println("Prénom: " + user.getFirstName());
    System.out.println("Nom: " + user.getLastName());
    System.out.println("Téléphone: " + user.getTel());
    System.out.println("Adresse: " + user.getAddress());
    System.out.println("Rôle: " + user.getRole());
    // Vous pouvez afficher d'autres informations si nécessaire
    System.out.println();
}


        // Mettre à jour un utilisateur (par exemple, changer le rôle)
        User userToUpdate = userService.getById(3); // Remplacez 1 par l'ID de l'utilisateur à mettre à jour
        if (userToUpdate != null) {
            userToUpdate.setRole(UserRole.ADMIN);
            userService.modifier(userToUpdate);
            System.out.println("Utilisateur mis à jour : " + userToUpdate);
        }

        // Supprimer un utilisateur (par exemple, supprimer l'utilisateur avec l'ID 2)
        int userIdToDelete = 1; // Remplacez 2 par l'ID de l'utilisateur à supprimer
        userService.supprimer(1);
        System.out.println("Utilisateur supprimé avec l'ID : " + userIdToDelete);
    }
}  */




import java.sql.Connection;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import tn.esprit.entities.Evennement;
import tn.esprit.entities.Participant;
import tn.esprit.entities.User;
import tn.esprit.services.Eventservice;
import tn.esprit.services.Participationservices;
import tn.esprit.utils.Datasource;


/**
 *
 * @author remo
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
             
        // TODO code application logic here
        Connection cnx;
        cnx = Datasource.getInstance().getCnx();
           
        Evennement evennement1=new Evennement( 2,"titreupdated", LocalDate.now(),"iheb","C:/iheb/img/photo.png", "adresseUpdated", "appppp");
        Evennement evennementPourModifier=new Evennement( 4,"titreupdated", LocalDate.now(),"descriptionUpdate","C:/iheb/img/photo.png", "adresseUpdated", "TunisieUpdated");
Participationservices participation=new Participationservices();

               // Eventservice  evennement =new Eventservice();
            //   public User(int iduser, String username, String email, String password, String firstName, String lastName, String tel, String address) 
              // User newwUser = new User("yamen","yamen@test.com","123456","Yamen","khefacha","55530947","");
           User newUser = new User();
           newUser.setIduser(1);
           
           Evennement ev =new Evennement();
           ev.setId(2);
//int iduser =1;
                   //int idevent=2;
               Participant p=new  Participant( LocalDate.now(),50163445,newUser,ev);
   
       Eventservice eventservice = new Eventservice();
   
        //participation.supprimerMonParticipation(1,14);
         // Evennement eve=new Evennement( 3,"titreupdated", LocalDate.now(),"iheb","C:/iheb/img/photo.png", "adresseUpdated", "appppp");
      
       // List<Evennement> recupererBytitre;
       
       //for(Evennement ee :recupererBytitre  ){
         //   System.out.println("affiche :"+ee);
       // }
        
        // String titre="titreupdated";
        
    //System.out.println("Affichge : \n" +eventservice.recupererBytitre());
      
        
  //eventservice.ajouter(evennement1);     
      //eventservice.supprimer(9);
    // System.out.println("Affichge : \n"+eventservice.getAll(evennement1));
      //  System.out.println("afficher:\n"+eventservice.getAll());
  //  eventservice.modifier(evennementPourModifier);
       
      participation.ajouter(p);
       //participation.modifier(participantModifier);
     // participation.supprimer(3);
      
      
      
   // System.out.println("Resultas : " +participation.getAll());
     
     /*List<Evennement> listev = new LinkedList<>();
     listev = eventservice.recupererBytitreByDateByLieu("BOCA", "titre");
                 for(Evennement ee :listev  ){
           System.out.println("affiche :"+ee);
               */
    
    }
    
    }