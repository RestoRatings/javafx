/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entities.Evennement;
import tn.esprit.utils.Datasource;

/**
 *
 * @author Med Iheb
 */
public class Eventservice implements IserviceEvenement<Evennement>{
      Connection cnx;
      Statement ste;
    private static Datasource instance;
    public Eventservice(){
        cnx=tn.esprit.utils.Datasource.getInstance().getCnx();
    }
   

    @Override
    public void ajouter(Evennement event) {
        String req= "INSERT INTO evennement(titre,date,description,img,adresse,lieu)values(?,?,?,?,?,?)";
        try {
            
               PreparedStatement pre= cnx.prepareStatement(req);
               
            pre.setString(1,event.getTitre() );
            pre.setDate(2, Date.valueOf(event.getDate()));
            pre.setString(3, event.getDescription()); 
            pre.setString(4, event.getImg());
            pre.setString(5, event.getAdresse());
            pre.setString(6, event.getLieu());
            
               
            pre.executeUpdate();
              System.out.println("Ajouter avec succées");
            
            
            } catch (SQLException ex) {
                System.out.println(ex);
            
        }
      
    } 

    @Override
    public void modifier(Evennement event) {
       String req="UPDATE evennement SET `titre` = ?,`date` = ?, `description` = ?,`img` = ?, `adresse`= ? , `lieu` = ? WHERE `idevent`=" + event.getId();
         try {
            
               PreparedStatement pre= cnx.prepareStatement(req);
               
            pre.setString(1,event.getTitre() );
            pre.setDate(2, Date.valueOf(event.getDate()));
            pre.setString(3, event.getDescription());
            pre.setString(4, event.getImg());
            pre.setString(5, event.getAdresse());
            pre.setString(6, event.getLieu());
            
               
            pre.executeUpdate();
            
              System.out.println("Update avec succées");
            
            } catch (SQLException ex) {
                System.out.println(ex);
            
        }
        
    }

    @Override
    public void supprimer(int id) {
        
        
      String req ="DELETE FROM evennement WHERE `idevent`=?" ;
          try {
              PreparedStatement pre= cnx.prepareStatement(req);
              pre.setInt(1, id);
              pre.executeUpdate();
              System.out.println("Event deleted");
          } catch (SQLException ex) {
              System.out.println("ERROR SUPRIMER EVENT :"+ex.getMessage());
          }
    
    
    }

    @Override
    public Evennement getOne(Evennement even) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        
        
        
        
        
    }

    @Override
    public List<Evennement> getAll() {
        System.out.println("aaa bbb ccc");
        List<Evennement>  listEvent=new ArrayList<>();
        String req="select * from evennement ";
         try {
          ste= cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                 listEvent.add(
                         
                new Evennement (
                    rs.getInt("idevent"),
                    rs.getString("titre"),
                    LocalDate.parse(String.valueOf(rs.getDate("date"))),
                    rs.getString("description"),
                    rs.getString("img"),
                    rs.getString("adresse"),
                    rs.getString("lieu")
            
                ));
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  listEvent ;
        
        
        
        
                
    }
    
    
   

    @Override
    public List<Evennement> recupererBytitre(String titre){
          List<Evennement>  listE=new ArrayList<>();
          String req = "SELECT * FROM  evennement WHERE titre REGEXP ?";
          try {
                 PreparedStatement st = cnx.prepareStatement(req);
                     st.setString(1, titre);
              ResultSet rs = st.executeQuery();
                while(rs.next()){
                 listE.add(
                         
                new Evennement (
                    rs.getInt("idevent"),
                    rs.getString("titre"),
                    LocalDate.parse(String.valueOf(rs.getDate("date"))),
                    rs.getString("description"),
                    rs.getString("img"),
                    rs.getString("adresse"),
                    rs.getString("lieu")
                ));
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  listE ;
              
                


    }
    public List<Evennement> recupererBytitreByDateByLieu(String ValueSaisie, String searchtype){
          List<Evennement>  listE=new ArrayList<>();
            String req = "SELECT * FROM evennement WHERE 1=1";
          try {
             if ("titre".equals(searchtype)) {
            req += " AND titre REGEXP ?";
        } else if ("date".equals(searchtype)) {
            req += " AND date REGEXP ?";
        } else if ("lieu".equals(searchtype)) {
            req += " AND lieu REGEXP ?";
        }
                 PreparedStatement st = cnx.prepareStatement(req);
                     st.setString(1, ValueSaisie);
              ResultSet rs = st.executeQuery();
                while(rs.next()){
                 listE.add(
        
                new Evennement (
                    rs.getInt("idevent"),
                    rs.getString("titre"),
                    LocalDate.parse(String.valueOf(rs.getDate("date"))),
                    rs.getString("description"),
                    rs.getString("img"),
                    rs.getString("adresse"),
                    rs.getString("lieu")
                ));
              
            }
               
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  listE ;
    }
    
}
    
    

