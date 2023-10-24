/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entities.Evennement;
import tn.esprit.entities.Participant;
import tn.esprit.entities.User;
import tn.esprit.utils.Datasource;
/**
 *
 * @author Med Iheb
 */
public class Participationservices implements IserviceParticipation<Participant>{
Connection cnx;
      Statement ste;
    private static Datasource instance;
    public Participationservices  (){
        cnx=tn.esprit.utils.Datasource.getInstance().getCnx();}
    @Override
    public void ajouter(Participant participation) {
      String req="INSERT INTO participant(datepar,numero,iduser,idevent)values(?,?,?,?)";
    try{
        PreparedStatement pre= cnx.prepareStatement(req);
                pre.setDate(1, Date.valueOf(participation.getDatepar()));
                pre.setInt(2, participation.getNumero());
               pre.setInt(3,participation.getUser().getIduser() );
                  pre.setInt(4, participation.getEvent().getId());
     pre.executeUpdate();
     System.out.println("Ajouter avec succées");
    }   catch (SQLException ex) {
          System.out.println(ex); 
        }
    
    }

    @Override
    public void modifier(Participant participation,int id) {
        
       String req="UPDATE participant SET `datepar` = ?,`numero` = ? WHERE `numero`=" + id;
        try {
            PreparedStatement pre=cnx.prepareStatement(req);
            pre.setDate(1, Date.valueOf(participation.getDatepar()));
            pre.setInt(2, participation.getNumero());
     
            
            pre.executeUpdate();
            System.out.println("Modifier avec succées");
        } catch (SQLException ex) {
            System.out.println(ex);
    }
    }
      @Override

    public void supprimer(int numero) {
        String req="DELETE FROM participant WHERE `numero`=?";
         try {
              PreparedStatement pre= cnx.prepareStatement(req);
              pre.setInt(1, numero);
              pre.executeUpdate();
              System.out.println("Participant deleted");
          } catch (SQLException ex) {
              System.out.println("ERROR SUPRIMER Participant :"+ex.getMessage());
          }
    
      
    }

    @Override
    public List<User> getAllUSER(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Participant> getAll() {
       List<Participant> listParticipant=new ArrayList<>();
       String req = "SELECT p.numero, p.datepar, u.iduser, u.username, u.email, u.password, u.firstName, u.lastName, u.tel, u.address, e.idevent,e.lieu, e.titre, e.date, e.description, e.adresse, e.img"
               + " FROM participant p "
               + "JOIN user u ON p.iduser = u.iduser "
               + "JOIN evennement e "
               + "ON p.idevent = e.idevent";
    try{
        ste= cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                 User user = new User(
                        rs .getInt("iduser"),
                        rs .getString("username"),
                        rs .getString("email"),
                        rs .getString("password"),
                        rs .getString("firstName"),
                        rs .getString("lastName"),
                        rs .getString("tel"),
                        rs .getString("address")
                );
                Evennement evennement = new Evennement(
                     rs.getInt("idevent"),
                    rs.getString("titre"),
                    LocalDate.parse(String.valueOf(rs.getDate("date"))),
                    rs.getString("description"),
                    rs.getString("img"),
                    rs.getString("adresse"),
                    rs.getString("lieu")
                );
            
         Participant participant = new Participant  (
                         LocalDate.parse(String.valueOf(rs.getDate("datepar"))),
                        rs .getInt("numero"),
                 user,
                  evennement
                        
                );
               listParticipant.add(participant);
                
                
            }
              
            
                
              
        
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
       return listParticipant;
    }

    @Override
    public void supprimerMonParticipation(int idUser, int idEvent) {
               String req="DELETE FROM participant WHERE `iduser`=? and `idevent`=? ";
         try {
              PreparedStatement pre= cnx.prepareStatement(req);
              pre.setInt(1, idUser);
              pre.setInt(2, idEvent);
              pre.executeUpdate();
              System.out.println("Participant deleted");
          } catch (SQLException ex) {
              System.out.println("ERROR SUPRIMER Participant :"+ex.getMessage());
          }
    }
public int getNombreParticipants(int id) {
    int nombreParticipants = 0;

    // Requête SQL pour compter le nombre de participants pour un événement donné
    String req = "SELECT COUNT(*) FROM participant WHERE idevent = ?";

    try {
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setInt(1, id);

        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            nombreParticipants = rs.getInt(1);
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération du nombre de participants : " + ex.getMessage());
    }

    return nombreParticipants;
}
    
  
        
}
       
       
       
    
    
    

