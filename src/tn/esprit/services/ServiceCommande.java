/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import tn.esprit.entities.Commande;
import java.sql.Statement;
import java.util.ArrayList;
import tn.esprit.entities.TypeC;



/**
 *
 * @author Med-Amine
 */
public class ServiceCommande implements IserviceCommande<Commande> {
    private static ServiceCommande instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public ServiceCommande() {
        connection = tn.esprit.utils.Datasource.getInstance().getCnx();    

}
     public static ServiceCommande getInstance() {
        if (instance == null) {
            instance = new ServiceCommande();
        }
        return instance;
    }

    @Override
    public void ajouter(Commande cmd) throws SQLException {
        String req = "insert into commande(montanttotal,date,type) values(?,NOW(),?)";
        PreparedStatement ps = connection.prepareStatement(req);   
        ps.setFloat(1,cmd.getMontanttotal());
        ps.setString(2,cmd.getTypec().toString());
        
        ps.executeUpdate();
    }

    @Override
    public void modifier(Commande cmd) throws SQLException {
       String req = "update commande set montanttotal = ?, date = NOW(), type = ? where idcmnd=?";
        PreparedStatement ps = connection.prepareStatement(req);

        ps.setFloat(1,cmd.getMontanttotal());
        ps.setString(2, cmd.getTypec().toString());
        ps.setInt(3,cmd.getIdcmnd());
        ps.executeUpdate();

    }

    @Override
    public void supprimer(int i) throws SQLException {
    String req = "DELETE FROM commande WHERE idcmnd = ?";
    PreparedStatement ps = connection.prepareStatement(req);
    ps.setInt(1, i);
    
    ps.executeUpdate();
}


    @Override
    public List<Commande> recuperer() throws SQLException {
        
        List<Commande> commandes = new ArrayList<>();
        String req = "select * from commande";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
        Commande com = new Commande();
        com.setIdcmnd(rs.getInt("idcmnd"));
        com.setMontanttotal(rs.getFloat("montanttotal"));
        com.setTypec(TypeC.valueOf(rs.getString("type")));
            commandes.add(com);
        }
        return commandes;
    }


}

