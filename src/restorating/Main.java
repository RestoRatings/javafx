/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package restorating;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import tn.esprit.entities.EtatRec;
import tn.esprit.entities.Reclamation;
import tn.esprit.entities.Reponse;
import tn.esprit.entities.TypeRec;
import java.sql.Date;
import tn.esprit.entities.User;







import tn.esprit.services.ServiceReclamation;
import tn.esprit.services.ServiceReponse;
import tn.esprit.utils.Datasource;


public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
                Connection cnx;
                cnx = Datasource.getInstance().getCnx();
    
             
     ServiceReclamation p2=ServiceReclamation.getInstance();
      ServiceReponse p3=ServiceReponse.getInstance();
       Reclamation r =new Reclamation(4);
         
   List<Reponse> list = p3.recuperer();
      for(int i=0;i<list.size();i++){
      System.out.println(list.get(i));
     /* Reponse rep =new Reponse (1,r,"ff");
       p3.modifier(rep);*/
     /* User u =new User(47);
     /* Reclamation reclamation =new Reclamation (u,"llll", TypeRec.facturation,EtatRec.resolue);
     p2.ajouter(reclamation);*/
 /*Reclamation rec1 =new Reclamation (4,u,"eee", TypeRec.facturation,EtatRec.en_attente);
     p2.modifier(rec1);*/
 /*p2.supprimer(9);
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
     * @param  the command line arguments
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


/*
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
/*public class Main {

    /**
     * @param args the command line arguments
     */

 /*   public static void main(String[] args) throws SQLException {
       
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
    
*/


//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.ArrayList;

//import tn.esprit.entities.Avis;
//import tn.esprit.entities.Badge;
//import tn.esprit.entities.Restaurant;
//import tn.esprit.entities.TypeBadge;
//import tn.esprit.entities.User;
//import tn.esprit.services.IserviceBadge;
//import tn.esprit.services.ServiceAvis;
//import tn.esprit.services.ServiceBadge;
//import tn.esprit.utils.Datasource;



/**
 *
 * @author remo
 */
//public class Main {

 
   // public static void main(String[] args) throws SQLException {
        // Initialisez votre connexion et votre service
       // Datasource.getInstance().getCnx(); // Assurez-vous que la connexion est correctement initialisée
       // ServiceAvis serviceAvis = ServiceAvis.getInstance();
        //ServiceBadge serviceBadge;
       // serviceBadge = ServiceBadge.getInstance();
        
        
        
        
        
        
        
        
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
      
                     //++++++++++++++++ CRUD Avis++++++++++++++   

////////////////////////////////////////////////////////////////////////////////////////////////////////////
            
        //-------------------------------------------Test Ajouter Avis----------------------------------------------------------------
    
 
//        
//    
//            Avis nouvelAvis = new Avis();
//            Restaurant restaurant = new Restaurant();
//            User user = new User();
//            nouvelAvis.setDateAvis(LocalDate.now()); 
//            nouvelAvis.setPubAvis("Madrid Rojla " ); 
//            nouvelAvis.setTitreAvis("Madrid lgar7a "); 
//            restaurant.setIdrestau(1);
//            restaurant.setNom("walid");
//            user.setIduser(30);
//            try {
//               
//                serviceAvis.ajouter(nouvelAvis,restaurant,user);
//                  
//                System.out.println("Avis ajouté avec succès ! L'ID généré est : " + nouvelAvis.getId());
//                
//            System.out.println("/////////////////////////////////////////////////////////////////////////////////////////\n");
//            
//            System.out.println("Liste des avis pour le restaurant " + restaurant.getNom() + ":");
//                
//                for (Avis avis : restaurant.getAvis()) {
//                System.out.println("ID: " + avis.getId() + ", Titre: " + avis.getTitreAvis());
//                System.out.println("ID: " + avis.getPubAvis()+ ", Titre: " + avis.getTitreAvis());
//                
//                }
//            System.out.println("/////////////////////////////////////////////////////////////////////////////////////////\n");
//            System.out.println("Liste des avis de l'utilisateur " + user.getIduser() + ":");
//             for (Avis avis : user.getAvis()) {
//            System.out.println("ID: " + avis.getId() + ", Titre: " + avis.getTitreAvis());
//             }
//           
//                
//                
//            } catch (SQLException e) {
//                System.err.println("Erreur lors de l'ajout de l'avis : " + e.getMessage());
//            }       
//           
//            
//        
//          
    
        
      
      
      
       
       //-------------------------------------------Test Modifier Avis----------------------------------------------------------------
//      
//        Avis nouvelAvis = new Avis();
//
//       try {
//           
//            Avis avisModifie = new Avis();
//            avisModifie.setDateAvis(LocalDate.now());
//            avisModifie.setPubAvis("Nouveau contenu de l'avis");
//            avisModifie.setTitreAvis("Nouveau Titre");
//
//          
//            int idAvisAModifier = 2; 
//
//        
//            int nouvelIdRestaurant = 2; 
//
//            
//            serviceAvis.modifier(idAvisAModifier, avisModifie, nouvelIdRestaurant);
//
//            System.out.println("Mise à jour de l'avis réussie !");
//        } catch (SQLException e) {
//            System.err.println("Erreur lors de la mise à jour de l'avis : " + e.getMessage());
//        }
//      
//    
    
      
      
      
      
      
      
       //-------------------------------------------Test Supprimer Avis----------------------------------------------------------------
      

//    
//    int idAvisASupprimer = 123; 
//    
//    try {
//       
//        serviceAvis.supprimer(idAvisASupprimer);
//        System.out.println("Avis supprimé avec succès !");
//    } catch (SQLException e) {
//        System.err.println("Erreur lors de la suppression de l'avis : " + e.getMessage());
//    }
//      
//      
    
    
    
    
    
     //-------------------------------------------Test Recuperer Avis----------------------------------------------------------------
//      
//    try {
//             List<Avis> listeAvis = serviceAvis.recuperer();
//
//           for (Avis avis : listeAvis) {
//                System.out.println("ID de l'avis: " + avis.getId());
//                System.out.println("Date de l'avis: " + avis.getDateAvis());
//                System.out.println("Contenu de l'avis: " + avis.getPubAvis());
//                System.out.println("Titre de l'avis: " + avis.getTitreAvis());
//                
//                
//                if (avis.getRestaurant() != null) {
//                    System.out.println("ID du Restaurant associé: " + avis.getRestaurant().getIdrestau());
//                    System.out.println("Nom du Restaurant associé: " + avis.getRestaurant().getNom());
//                } else {
//                    System.out.println("Aucun restaurant associé à cet avis.");
//                }
//                
//                 if (avis.getUser() != null) {
//                    System.out.println("ID de l'Utilisateur associé: " + avis.getUser().getIduser());
//                    System.out.println("Nom de l'Utilisateur associé: " + avis.getUser().getUsername());
//                } else {
//                    System.out.println("Aucun utilisateur associé à cet avis.");
//                }
//
//                System.out.println("-----------------------------------");
//            }
//        } catch (SQLException e) {
//            System.err.println("Erreur lors de la récupération des avis : " + e.getMessage());
//        }
//    

      
      
 
      
////////////////////////////////////////////////////////////////////////////////////////////////////////////
      
                     //++++++++++++++++ CRUD Badge++++++++++++++   

////////////////////////////////////////////////////////////////////////////////////////////////////////////
      //-------------------------------------------Test Ajouter Badge----------------------------------------------------------------
       
    
//            Badge nouvelBadge = new Badge();
//            Restaurant restaurant = new Restaurant();
//            User user = new User();
//            nouvelBadge.setDateBadge(LocalDate.now());
//            nouvelBadge.setCommantaire("Madrid Rojla " ); 
//            nouvelBadge.setTypeBadge(TypeBadge.Diamant); 
//            restaurant.setIdrestau(1);
//            user.setIduser(30);
//            try {
//                
//                serviceBadge.ajouter(nouvelBadge,restaurant,user);
//                System.out.println("Badge ajouté avec succès ! L'ID généré est : " + nouvelBadge.getId());
//            } catch (SQLException e) {
//                System.err.println("Erreur lors de l'ajout de le badge : " + e.getMessage());
//            }        
  
//    
        
             //-------------------------------------------Test Modifier Badge----------------------------------------------------------------
////      
//        Badge nouvelbadge = new Badge();
//
//       try {
//          
//            Badge badgeModifie = new Badge();
//            badgeModifie.setDateBadge(LocalDate.now());
//            badgeModifie.setCommantaire("Nouveau contenu de l'avis");
//            badgeModifie.setTypeBadge(TypeBadge.VIP); 
//            
//            int idBadgeAModifier = 4; 
//            
//            int nouvelIdRestaurant = 2;
//            serviceBadge.modifier(idBadgeAModifier, badgeModifie, nouvelIdRestaurant);
//
//            System.out.println("Mise à jour de le badge réussie !");
//        } catch (SQLException e) {
//            System.err.println("Erreur lors de la mise à jour de le badge : " + e.getMessage());
//        }
//      
// 
       
        //-------------------------------------------Test Supprimer Badge----------------------------------------------------------------
//       
//        int idBadgeASupprimer = 2;
//    try {
//        
//        serviceBadge.supprimer(idBadgeASupprimer);
//        System.out.println("Badge supprimé avec succès !");
//    } catch (SQLException e) {
//        System.err.println("Erreur lors de la suppression de l'Badge : " + e.getMessage());
//    }
////    
       
       
               //-------------------------------------------Test Récuperer Badge----------------------------------------------------------------
 
//      try {
//            List<Badge> listeBadges = serviceBadge.recuperer();
//
//             for (Badge badge : listeBadges) {
//                System.out.println("ID de la badge: " + badge.getId());
//                System.out.println("Date de la badge: " + badge.getDateBadge());
//                System.out.println("Commantaire de la badge: " + badge.getCommantaire());
//                System.out.println("Type de la badge: " + badge.getTypeBadge());
//                
//               if (badge.getRestaurant() != null) {
//                    System.out.println("ID du Restaurant associé: " + badge.getRestaurant().getIdrestau());
//                    System.out.println("Nom du Restaurant associé: " + badge.getRestaurant().getNom());
//                } else {
//                    System.out.println("Aucun restaurant associé à cet badge.");
//                }
//                
//                if (badge.getUser() != null) {
//                    System.out.println("ID du User associé: " + badge.getUser().getIduser());
//                    System.out.println("Nom d'utilisateur du User associé: " + badge.getUser().getUsername());
//                } else {
//                    System.out.println("Aucun user associé à cet badge.");
//                }
//
//                System.out.println("-----------------------------------");
//            }
//        } catch (SQLException e) {
//            System.err.println("Erreur lors de la récupération des badges : " + e.getMessage());
//        }


    }}
}


