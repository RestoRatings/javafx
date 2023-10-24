/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package restorating;



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
        // TODO code application logic here
       //     
       //  Connection cnx;
       ////  ServiceAchat sa = new ServiceAchat();

     /* List<Plat> list = pl.recuperer();
      for(int i=0;i<list.size();i++){
      System.out.println(list.get(i));*/
      
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
 }}
    
    

