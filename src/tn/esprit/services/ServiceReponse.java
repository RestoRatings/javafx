/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.EtatRec;


import tn.esprit.entities.Reclamation;
import tn.esprit.entities.Reponse;
import tn.esprit.entities.TypeRec;


/**
 *
 * @author Med-Amine
 */
public class ServiceReponse implements IServiceReponse<Reponse>{

   private static ServiceReponse instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public ServiceReponse() {
        connection = tn.esprit.utils.Datasource.getInstance().getCnx();    

}
     public static ServiceReponse getInstance() {
        if (instance == null) {
            instance = new ServiceReponse();
        }
        return instance;
     }
    

    @Override
    public void ajouter(Reponse rep) throws SQLException {
         String req = "insert into reponse(idrec,contenue,daterep) values(?,?,NOW())";
        PreparedStatement ps = connection.prepareStatement(req);
       ps.setInt(1,rep.getReclamation().getIdrec());
        ps.setString(2,rep.getContenue());
        
        
        ps.executeUpdate();
    }

    @Override
    public void modifier(Reponse rep) throws SQLException {
          String req = "update reponse set idrec = ? ,contenue = ?, daterep = NOW()  where idrep= ?";
        PreparedStatement ps = connection.prepareStatement(req);

         ps.setInt(1,rep.getReclamation().getIdrec());
        ps.setString(2, rep.getContenue());
          ps.setInt(3,rep.getIdrep());
          ps.executeUpdate();
          System.out.println("ID de reclamation : " + rep.getReclamation().getIdrec());
System.out.println("Nouveau contenue de reponse : " + rep.getContenue().toString());


    }


    @Override
    public void supprimer(int i) throws SQLException {
    String rep = "DELETE FROM reponse WHERE idrep = ?";
    PreparedStatement ps = connection.prepareStatement(rep);
    ps.setInt(1, i);
    
    ps.executeUpdate();
}
  

     @Override
    
    public List<Reponse> recuperer() throws SQLException {
        
         List<Reponse> reponses = new ArrayList<>();
    try {
        preparedStatement = connection.prepareStatement("" +
                "SELECT * FROM `reponse` AS re " +
               
                "INNER JOIN `reclamation` AS rec ON re.idrec = rec.idrec");

       ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
          Reponse reponse = new Reponse();
reponse.setIdrep(resultSet.getInt("idrep"));
reponse.setDaterep(resultSet.getDate("daterep"));
         reponse.setContenue( resultSet.getString("contenue"));
         

            Reclamation reclamation = new Reclamation();
            reclamation.setIdrec(resultSet.getInt("idrec"));
            //reclamation.setDate(resultSet.getString("date"));
            
        reclamation.setDescription(resultSet.getString("description"));
            reclamation.setTyperec(TypeRec.valueOf(resultSet.getString("typerec")));

            reclamation.setEtatrec(EtatRec.valueOf(resultSet.getString("etatrec")));
           
            reponse.setReclamation(reclamation);
 reponses.add(reponse);
            
        }
    } catch (SQLException exception) {
        System.out.println("Error (recuperer) Reponse : " + exception.getMessage());
    } finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
    return reponses;
}}


