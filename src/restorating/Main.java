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

/**
 *
 * @author remo
 */
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
      User u =new User(2);
      /*Reclamation reclamation =new Reclamation (u,"llll", TypeRec.facturation,EtatRec.resolue);
     p2.ajouter(reclamation);
     /*Reclamation r =new Reclamation(46);
         
     Reponse reponse =new Reponse (2,r,"gggj" );
     p3.ajouter(reponse);
     User u =new User(1);
    / int idReclamationARechercher = 41; // Remplacez 123 par l'ID de réclamation que vous recherchez
Reclamation reclamationTrouvee = p2.rechercherParId(idReclamationARechercher);

if (reclamationTrouvee != null) {
    System.out.println("Réclamation trouvée : " + reclamationTrouvee);
} else {
    System.out.println("Réclamation non trouvée.");
}*/
    /*Reclamation reclamation =new Reclamation (u, TypeRec.service,EtatRec.resolue);
     p2.ajouter(reclamation);*/
   /* Reclamation rec1 =new Reclamation (76,u,"eee", TypeRec.facturation,EtatRec.en_attente);
     p2.modifier(rec1);*/
      /* p2.supprimer(39);*/
          //System.out.println("reclamation supprimer");
    /* List<Reclamation> list = p2.recuperer();
      for(int i=0;i<list.size();i++){
      System.out.println(list.get(i));*/
      Reclamation r =new Reclamation(79);
         
  
      Reponse rep =new Reponse (13,r,"kk");
     p3.modifier(rep);
      /*p3.supprimer(2);*/
     /*List<Reponse> list = p3.recuperer();
      for(int i=0;i<list.size();i++){
      System.out.println(list.get(i));
       
          /*System.out.println("reclamation supprimer");*/
      
    

      }}
    
    

