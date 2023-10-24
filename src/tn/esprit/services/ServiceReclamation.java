package tn.esprit.services;

/**
 *
 * @author mchai
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.User;
import tn.esprit.entities.UserRole;
import tn.esprit.entities.Reclamation;


import java.sql.Timestamp;
import tn.esprit.entities.TypeRec;
import tn.esprit.entities.EtatRec;
public class ServiceReclamation implements IServiceReclamation<Reclamation> {
    private static ServiceReclamation instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public ServiceReclamation() {
        connection = tn.esprit.utils.Datasource.getInstance().getCnx();    

}
     public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
     }
    

    @Override
    public void ajouter(Reclamation rec) throws SQLException {
        String req = "insert into reclamation(iduser,date,description,typerec,etatrec) values(?,NOW(),?,?,?)";
        PreparedStatement ps = connection.prepareStatement(req); 
        
        ps.setInt(1,rec.getUser().getIduser());
         ps.setString(2,rec.getDescription());
        ps.setString(3,rec.getTyperec().toString());
        ps.setString(4,rec.getEtatrec().toString());
       
        ps.executeUpdate();
    }


    @Override
    public void modifier(Reclamation rec) throws SQLException {
        String req = "update reclamation set iduser = ?, date = NOW(),description = ? , typerec = ? ,etatrec = ? where idrec=?";
        PreparedStatement ps = connection.prepareStatement(req);
        
        ps.setInt(1,rec.getUser().getIduser());
        ps.setString(2,rec.getDescription());
        ps.setString(3, rec.getTyperec().toString());
         ps.setString(4, rec.getEtatrec().toString());
        ps.setInt(5,rec.getIdrec());
        ps.executeUpdate();
System.out.println("ID de l'utilisateur : " + rec.getUser().getIduser());
System.out.println("Nouveau type de réclamation : " + rec.getTyperec().toString());
System.out.println("Nouvel état de réclamation : " + rec.getEtatrec().toString());
System.out.println("ID de la réclamation : " + rec.getIdrec());
    }


    @Override
    public void supprimer(int i) throws SQLException {
    String req = "DELETE FROM reclamation WHERE idrec = ?";
    PreparedStatement ps = connection.prepareStatement(req);
    ps.setInt(1, i);
    
    ps.executeUpdate();
}
   

     @Override
    
    public List<Reclamation> recuperer() throws SQLException {
        
        List<Reclamation> reclamations = new ArrayList<>();
    try {
        preparedStatement = connection.prepareStatement("" +
                "SELECT * FROM `reclamation` AS r " +
               
                "INNER JOIN `user` AS u ON r.iduser = u.iduser");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
           Reclamation reclam = new Reclamation();
        reclam.setIdrec(resultSet.getInt("idrec"));
        
          reclam.setDate(resultSet.getDate("date"));
        reclam.setDescription(resultSet.getString("description"));
        reclam.setTyperec(TypeRec.valueOf(resultSet.getString("typerec")));
  reclam.setEtatrec(EtatRec.valueOf(resultSet.getString("etatrec")));

           

            User user = new User();
            user.setIduser(resultSet.getInt("iduser"));
           user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setFirstName(resultSet.getString("firstName"));
            user.setLastName(resultSet.getString("lastName"));
            user.setTel(resultSet.getString("tel"));
            user.setRole(UserRole.valueOf(resultSet.getString("role")));


           
            reclam.setUser(user);
 reclamations.add(reclam);
            
        }
    } catch (SQLException exception) {
        System.out.println("Error (recuperer) Reclamation : " + exception.getMessage());
    } finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
    return reclamations;
}

public Reclamation rechercherParId(int idrec) throws SQLException {
    Reclamation reclamation = null;
    try {
        String req = "SELECT * FROM reclamation WHERE idrec = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, idrec);

        ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()) {
            reclamation = new Reclamation();
            reclamation.setIdrec(resultSet.getInt("idrec"));
            reclamation.setDate(resultSet.getDate("date"));
            reclamation.setDescription(resultSet.getString("description"));
            reclamation.setTyperec(TypeRec.valueOf(resultSet.getString("typerec")));
            reclamation.setEtatrec(EtatRec.valueOf(resultSet.getString("etatrec")));

            User user = new User();
            user.setIduser(resultSet.getInt("iduser"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setFirstName(resultSet.getString("firstName"));
            user.setLastName(resultSet.getString("lastName"));
            user.setTel(resultSet.getString("tel"));
            user.setAddress(resultSet.getString("address"));
            user.setRole(UserRole.valueOf(resultSet.getString("role")));

            reclamation.setUser(user);
        }
    } catch (SQLException exception) {
        System.out.println("Error (rechercherParId) Reclamation : " + exception.getMessage());
    }

    return reclamation;
}
}