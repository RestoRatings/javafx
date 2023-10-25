/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import static com.oracle.nio.BufferSecrets.instance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.Restaurant;

/**
 *
 * @author Ltifi
 */
public class Servicerestaurant implements Iservice<Restaurant>{

    Statement ste;
    private Connection connection; // Initialisez votre connexion à la base de données
    private static Servicerestaurant instance;
     public Servicerestaurant() {
        connection = tn.esprit.utils.Datasource.getInstance().getCnx();    

}
     public static Servicerestaurant getInstance() {
        if (instance == null) {
            instance = new Servicerestaurant();
        }
        return instance;
    }

     
      

    @Override
    public void ajouter(Restaurant t) {
        try {
            String req = "INSERT INTO restaurant(nom,location)values(?,?)";
            PreparedStatement pre = connection.prepareStatement(req);
            pre.setString(1,t.getNom());
            pre.setString(2,t.getlocation());
            pre.executeUpdate();
            
           
            
            
            } catch (SQLException ex) {
                System.out.println(ex);
            
        }
       
    }

    @Override
    public void supprimer(Restaurant t) {
  
        try{
        
         String req = "DELETE FROM restaurant WHERE id_restau = ?";
          PreparedStatement pre = connection.prepareStatement(req);
         pre.setInt(1, t.getId()); 
             pre.executeUpdate();}
         catch (SQLException ex) {
                System.out.println(ex);
            
        }}
     @Override
    public void modifier(Restaurant t) {
        
              try {
            String req = "UPDATE restaurant SET nom = ?, location = ? WHERE id_restau = ?";

            PreparedStatement pre = connection.prepareStatement(req);
           
            pre.setString(1,t.getNom());
            pre.setString(2,t.getlocation());
             pre.setInt(3, t.getId());
          
            pre.executeUpdate();
            
            
            
            } catch (SQLException ex) {
                System.out.println(ex);
            
        }
       
    }
    

    @Override
    public List<Restaurant> affihcer() {
        List<Restaurant> restaurantss = new ArrayList<>();
        String sql ="select * from restaurant";
        try {
            ste= connection.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Restaurant r= new Restaurant(rs.getInt("id_restau"),
                        rs.getString("nom"), rs.getString("location"));
                restaurantss.add(r);
                for (Restaurant element : restaurantss) {
                String  hell=element.toStringForDisplay();
                System.out.println(  hell);
            
        }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return restaurantss;
    }
    
    ////
    public Restaurant getRestaurantParId(int id) {
    String sql = "SELECT * FROM restaurant WHERE id_restau = ?";
    try {
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            int restaurantId = rs.getInt("id_restau");
            String nom = rs.getString("nom");
            String location = rs.getString("location");
            
            // Create and return a restaurant object with retrieved data
            return new Restaurant(restaurantId, nom, location);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null;
}

     
     
     
     
     
     
     
     
     
     
     
     
    public List<String> getRestaurantNames() throws SQLException {
    List<String> restaurantNames = new ArrayList<>();
    String query = "SELECT nom FROM restaurant";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
            String nom = resultSet.getString("nom");
            restaurantNames.add(nom);
        }
    }
    return restaurantNames;
}
    
     public int getIdRestaurantParNom(String nomRestaurant) throws SQLException {
        int idRestaurant = -1; // Une valeur par défaut en cas d'erreur

        String query = "SELECT id_restau FROM restaurant WHERE nom = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nomRestaurant);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    idRestaurant = resultSet.getInt("id_restau");
                }
            }
        }

        return idRestaurant;
    }
     
        public Restaurant getRestaurantById(int idresto) throws SQLException {
    Restaurant restaurant = null; // Une valeur par défaut en cas d'erreur

    String query = "SELECT * FROM restaurant WHERE id_restau = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, idresto);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                restaurant = createRestaurantFromResultSet(resultSet);
            }
        }
    }

    return restaurant;
}

private Restaurant createRestaurantFromResultSet(ResultSet resultSet) throws SQLException {
    Restaurant restaurant = new Restaurant();
    restaurant.setId(resultSet.getInt("id_restau"));
    restaurant.setNom(resultSet.getString("nom"));

    // Vous pouvez ajouter d'autres attributs de restaurant ici si nécessaire

    return restaurant;
}


 
}

