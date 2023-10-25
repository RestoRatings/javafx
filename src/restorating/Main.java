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




//import java.sql.Connection;

//import java.time.LocalDate;
//import java.time.ZonedDateTime;
//import static java.util.Collections.list;
//import java.util.LinkedList;
//import java.util.List;
//import tn.esprit.entities.Evennement;
//import tn.esprit.entities.Participant;
//import tn.esprit.entities.Plat;
//import tn.esprit.entities.User;
//import tn.esprit.services.Eventservice;
//import tn.esprit.services.Participationservices;
//import tn.esprit.utils.Datasource;


/**
 *
 * @author remo
 */
//public class Main {

    /**
     * @param args the command line arguments
     */

   // public static void main(String[] args) {
             
        // TODO code application logic here
        //Connection cnx;
       // cnx = Datasource.getInstance().getCnx();
           
        /*Evennement evennement1=new Evennement( 2,"titreupdated", LocalDate.now(),"iheb","C:/iheb/img/photo.png", "adresseUpdated", "appppp");
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
    
    
     // TODO code application logic here
       //     
       //  Connection cnx;
       ////  ServiceAchat sa = new ServiceAchat();

     // List<Plat> list = pl.recuperer();
     // for(int i=0;i<list.size();i++){
     // System.out.println(list.get(i));
      
     // pl.supprimer(12);
          //System.out.println("platsupprimer");
      
      //Plat plat =new Plat(1, "amine", "brh", "ok",22, CategorieP.Berger);
      //pl.ajouter(plat);*/

         //Plat pp = new Plat("jhjh","dddf","ddf",333f,CategorieP.Berger);
        // p.ajouter(pp);
        // Plat ppp = new Plat(13,"amine","brh","amine",599f,CategorieP.Berger);
        
        //: for(int i=0;i<plats.size();i++){
           // System.out.println(plats.get(i));
         //p.modifier(ppp);
        /*List<Commande> list = sc.recuperer();
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }*/
         //sc.supprimer(1);
         //Commande cmd = new Commande(222f,TypeC.livraison);
          //User u = new User(24);
         //sc.ajouter(cmd);
         //sc.modifier(cmdd);
       //ServiceAchat ac = ServiceAchat.getInstance();
      /* List<Achat> achats = new ArrayList<>();
       achats = sa.recuperer();
       for(int i=0;i<achats.size();i++){
            System.out.println(achats.get(i));
        }*/
     //Achat a = new Achat(21, u, cmdd, ppp, 55);
          // Achat a = new Achat(36,u,ppp,99f,5,TypeC.livraison);
           //(int idachat, User user, Plat plat, float montanttotal, int quantite, TypeC typec)
          //System.out.println("Affichage : <<< \n" + sa.recuperer());  
          // sa.modifier(a);
         // sa.supprimer(36);
       /*  ServicePlat p = ServicePlat.getInstance();
         Plat pp = new Plat(13, "hazem", "brahmi", "c://user/msi/download/kousski.png", 700f, CategorieP.Berger);
        List<Plat> plats = p.recupererByNom(pp.getNom());
            for (Plat plat : plats) {
                System.out.println("Plat par nom: " + plat);
               

        
              /* TypeC typeARechercher = TypeC.surplace;

        
            List<Achat> achatsByType = sa.recupererByType(typeARechercher);

            for (Achat achat : achatsByType) {
                System.out.println("Achat par type: " + achat);
      
                
                

        System.out.println("Plat par nom: " + p.recupererByNom("ha"));
    
    
    }}
      
         
*/
        /*Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("mon_fichier.pdf"));
            document.open();

            // Ajoutez du contenu au PDF
            document.add(new Paragraph("Hello, world!"));
            document.add(new Paragraph("Ceci est un exemple simple de fichier PDF généré avec iText."));

            document.close();

            System.out.println("Fichier PDF généré avec succès : mon_fichier.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    
    
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */



import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.Achat;
import tn.esprit.entities.CategorieP;

import tn.esprit.entities.Plat;
import tn.esprit.entities.TypeC;
import tn.esprit.entities.User;
import tn.esprit.services.ServiceAchat;
import tn.esprit.services.ServicePlat;
import tn.esprit.utils.Datasource;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.time.LocalDate;

/**
 * 
 *
 * @author remo
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws SQLException {
       
       ServiceAchat sa = new ServiceAchat();
         Plat ppp = new Plat(3,"amine","brh","amine",599f,CategorieP.Berger);
        

          User u = new User();
            u.setIduser(3);
          
     //public Achat(int idachat, User user, Plat plat, float montanttotal, int quantite, TypeC typec)//
     
       //public Achat(User user, Plat plat, float montanttotal, int quantite, Date date, TypeC typec) 
           //Achat a = new Achat(3,u,ppp,99f,5,TypeC.livraison);
           // public Achat(User user, Plat plat, float montanttotal, int quantite, TypeC typec)
           Achat a = new Achat(u,ppp,99f,5,TypeC.livraison);
           sa.ajouter(a);
           List<Achat> achats = new ArrayList<>();
           System.out.println("Affichage : <<< \n" + sa.recuperer());
   
            }
    //  ServicePlat p = ServicePlat.getInstance();
      //Plat ppp = new Plat(3,"amine","brh","amine",599f,CategorieP.Boissons);
            // List<Plat> achats = new ArrayList<>();
   //System.out.println("Affichage : <<< \n" + p.recuperer());  
     
      
        
        
         
    }
    

