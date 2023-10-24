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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tn.esprit.entities.Achat;
import tn.esprit.entities.CategorieP;
import tn.esprit.entities.Plat;
import tn.esprit.entities.Role;
import tn.esprit.entities.TypeC;
import tn.esprit.entities.User;

/**
 *
 * @author Med-Amine
 */
public class ServiceAchat implements IServiceAchat<Achat>{
    
    private static ServiceAchat instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public ServiceAchat() {
        connection = tn.esprit.utils.Datasource.getInstance().getCnx();    

}
     public static ServiceAchat getInstance() {
        if (instance == null) {
            instance = new ServiceAchat();
        }
        return instance;
     }
    
    

    @Override
public void ajouter(Achat A) throws SQLException {
    String request = "INSERT INTO `achat`(`iduser`,`idplat`,`montanttotal`,`quantite`,`date`,`type`) VALUES(?, ?, ?, ?, NOW(), ?)";
    try {
        preparedStatement = connection.prepareStatement(request);

        preparedStatement.setInt(1, A.getUser().getIduser());
        preparedStatement.setInt(2, A.getPlat().getIdplat());
        preparedStatement.setFloat(3, A.getMontanttotal());
        preparedStatement.setInt(4, A.getQuantite());
        preparedStatement.setString(5, A.getTypec().toString());

        int rowsInserted = preparedStatement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Achat ajouté avec succès");
        } else {
            System.out.println("Échec de l'insertion de l'achat");
        }
    } catch (SQLException exception) {
        System.out.println("Erreur (ajouter) Achat : " + exception.getMessage());
        throw exception;
    }
}


 
public void ajouter2(Achat A,List <Plat> listplat) throws SQLException {
    String request = "INSERT INTO `achat`(`iduser`,`idplat`,`montanttotal`,`quantite`,`date`,`type`) VALUES(?, ?, ?, ?, NOW(), ?)";
    try {
        preparedStatement = connection.prepareStatement(request);

        preparedStatement.setInt(1, A.getUser().getIduser());
        preparedStatement.setInt(2, A.getPlat().getIdplat());
        preparedStatement.setFloat(3, A.getMontanttotal());
        preparedStatement.setInt(4, A.getQuantite());
        preparedStatement.setString(5, A.getTypec().toString());

        int rowsInserted = preparedStatement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Achat ajouté avec succès");
        } else {
            System.out.println("Échec de l'insertion de l'achat");
        }
    } catch (SQLException exception) {
        System.out.println("Erreur (ajouter) Achat : " + exception.getMessage());
        throw exception;
    }
}

@Override
public void modifier(Achat A) throws SQLException {
    String request = "UPDATE `achat` SET `iduser` = ?, `idplat` = ?, `montanttotal` = ?, `quantite` = ?, `date` = NOW(), `type` = ? WHERE `idachat` = ?";
    try {
        preparedStatement = connection.prepareStatement(request);

        preparedStatement.setInt(1, A.getUser().getIduser());
        preparedStatement.setInt(2, A.getPlat().getIdplat());
        preparedStatement.setFloat(3, A.getMontanttotal());
        preparedStatement.setInt(4, A.getQuantite());
        preparedStatement.setString(5, A.getTypec().toString());
        preparedStatement.setInt(6, A.getIdachat());  // Corrigé l'index

        int rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Achat mis à jour avec succès");
        } else {
            System.out.println("Échec de la mise à jour de l'achat");
        }
    } catch (SQLException exception) {
        System.out.println("Erreur (modifier) Achat : " + exception.getMessage());
        throw exception;
    }
}

@Override
public void supprimer(int i) throws SQLException {
    String req = "DELETE FROM achat WHERE idachat = ?";
    PreparedStatement ps = connection.prepareStatement(req);
    ps.setInt(1, i);

    ps.executeUpdate();
}

@Override
public List<Achat> recuperer() throws SQLException {
    List<Achat> listAchat = new ArrayList<>();
    try {
        preparedStatement = connection.prepareStatement("" +
                "SELECT * FROM `achat` AS a " +
                "INNER JOIN `plat` AS p ON a.idplat = p.idplat " +
                "INNER JOIN `user` AS u ON a.iduser = u.iduser");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Achat achat = new Achat();
            achat.setIdachat(resultSet.getInt("idachat"));
            achat.setQuantite(resultSet.getInt("quantite"));
            achat.setMontanttotal(resultSet.getFloat("montanttotal"));
            achat.setTypec(TypeC.valueOf(resultSet.getString("type")));
            achat.setDate(resultSet.getDate("date"));

            Plat plt = new Plat();
            plt.setIdplat(resultSet.getInt("idplat"));
            plt.setNom(resultSet.getString("nom"));
            plt.setDescription(resultSet.getString("description"));
            plt.setImage(resultSet.getString("image"));
            plt.setPrix(resultSet.getFloat("prix"));
            plt.setCategorie(CategorieP.valueOf(resultSet.getString("categorie")));

            User user = new User();
            user.setIduser(resultSet.getInt("iduser"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setFirstName(resultSet.getString("firstName"));
            user.setLastName(resultSet.getString("lastName"));
            user.setTel(resultSet.getString("tel"));
            user.setRole(Role.valueOf(resultSet.getString("role")));

            achat.setPlat(plt);
            achat.setUser(user);

            listAchat.add(achat);
        }
    } catch (SQLException exception) {
        System.out.println("Erreur (recuperer) Achat : " + exception.getMessage());
    } finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
    return listAchat;
}

    @Override
    public List<Achat> recupererByDate(Date date) throws SQLException {
List<Achat> listAchat = new ArrayList<>();
    try {
        preparedStatement = connection.prepareStatement("" +
                "SELECT * FROM `achat` AS a " +
                "INNER JOIN `plat` AS p ON a.idplat = p.idplat " +
                "INNER JOIN `user` AS u ON a.iduser = u.iduser " +
                "WHERE a.date = ?");

        preparedStatement.setDate(1, date);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Achat achat = new Achat();
            achat.setIdachat(resultSet.getInt("idachat"));
            achat.setQuantite(resultSet.getInt("quantite"));
            achat.setMontanttotal(resultSet.getFloat("montanttotal"));
            achat.setTypec(TypeC.valueOf(resultSet.getString("type")));
            achat.setDate(resultSet.getDate("date"));

            Plat plt = new Plat();
            plt.setIdplat(resultSet.getInt("idplat"));
            plt.setNom(resultSet.getString("nom"));
            plt.setDescription(resultSet.getString("description"));
            plt.setImage(resultSet.getString("image"));
            plt.setPrix(resultSet.getFloat("prix"));
            plt.setCategorie(CategorieP.valueOf(resultSet.getString("categorie")));

            User user = new User();
            user.setIduser(resultSet.getInt("iduser"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setFirstName(resultSet.getString("firstName"));
            user.setLastName(resultSet.getString("lastName"));
            user.setTel(resultSet.getString("tel"));
            user.setRole(Role.valueOf(resultSet.getString("role")));

            achat.setPlat(plt);
            achat.setUser(user);

            listAchat.add(achat);
        }
    } catch (SQLException exception) {
        System.out.println("Erreur (recupererByDate) Achat : " + exception.getMessage());
    } finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
    return listAchat;    }

    @Override
    public List<Achat> recupererByType(TypeC type) throws SQLException {
  List<Achat> listAchat = new ArrayList<>();
    try {
        preparedStatement = connection.prepareStatement("" +
                "SELECT * FROM `achat` AS a " +
                "INNER JOIN `plat` AS p ON a.idplat = p.idplat " +
                "INNER JOIN `user` AS u ON a.iduser = u.iduser " +
                "WHERE a.type = ? AND a.date != '0000-00-00'");

        preparedStatement.setString(1, type.name());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Achat achat = new Achat();
            achat.setIdachat(resultSet.getInt("idachat"));
            achat.setQuantite(resultSet.getInt("quantite"));
            achat.setMontanttotal(resultSet.getFloat("montanttotal"));
            achat.setTypec(TypeC.valueOf(resultSet.getString("type")));
            achat.setDate(resultSet.getDate("date"));

            Plat plt = new Plat();
            plt.setIdplat(resultSet.getInt("idplat"));
            plt.setNom(resultSet.getString("nom"));
            plt.setDescription(resultSet.getString("description"));
            plt.setImage(resultSet.getString("image"));
            plt.setPrix(resultSet.getFloat("prix"));
            plt.setCategorie(CategorieP.valueOf(resultSet.getString("categorie")));

            User user = new User();
            user.setIduser(resultSet.getInt("iduser"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setFirstName(resultSet.getString("firstName"));
            user.setLastName(resultSet.getString("lastName"));
            user.setTel(resultSet.getString("tel"));
            user.setRole(Role.valueOf(resultSet.getString("role")));

            achat.setPlat(plt);
            achat.setUser(user);

            listAchat.add(achat);
        }
    } catch (SQLException exception) {
        System.out.println("Erreur (recupererByType) Achat : " + exception.getMessage());
    } finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
    return listAchat;
}
    public Map<CategorieP, Integer> statistiquesParCategorie() throws SQLException {
    Map<CategorieP, Integer> statistiques = new HashMap<>();
    String query = "SELECT p.categorie, COUNT(*) AS nombre_achats " +
                   "FROM achat AS a " +
                   "INNER JOIN plat AS p ON a.idplat = p.idplat " +
                   "GROUP BY p.categorie";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
            CategorieP categorie = CategorieP.valueOf(resultSet.getString("categorie"));
            int nombreAchats = resultSet.getInt("nombre_achats");
            statistiques.put(categorie, nombreAchats);
        }
    }

    return statistiques;
}

}

   

    

