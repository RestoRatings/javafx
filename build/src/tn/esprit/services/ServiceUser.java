/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

/**
 *
 * @author LENOVO
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import tn.esprit.entities.User;
import tn.esprit.entities.UserRole;
import tn.esprit.utils.Datasource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.utils.Session;

public class ServiceUser implements IServiceUser {

    private Connection con;
    private PreparedStatement pre;
    private Statement ste;

    public ServiceUser() {
        con = Datasource.getInstance().getCnx(); // Use the connection from Datasource
    }

    @Override
    public void ajouter(User user) {
        try {
            String req = "INSERT INTO user(username, email, password, firstName, lastName, tel, address, role) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pre = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pre.setString(1, user.getUsername());
            pre.setString(2, user.getEmail());
            pre.setString(3, user.getPassword());
            pre.setString(4, user.getFirstName());
            pre.setString(5, user.getLastName());
            pre.setString(6, user.getTel());
            pre.setString(7, user.getAddress());
            pre.setString(8, user.getRole().name()); // Convert the enum to a string
            pre.executeUpdate();

            ResultSet generatedKeys = pre.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setIduser(generatedKeys.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(User user) {
        try {
            String req = "UPDATE user SET username=?, email=?, password=?, firstName=?, lastName=?, " +
                    "tel=?, address=?, role=? WHERE iduser=?";
            pre = con.prepareStatement(req);
            pre.setString(1, user.getUsername());
            pre.setString(2, user.getEmail());
            pre.setString(3, user.getPassword());
            pre.setString(4, user.getFirstName());
            pre.setString(5, user.getLastName());
            pre.setString(6, user.getTel());
            pre.setString(7, user.getAddress());
            pre.setString(8, user.getRole().name()); // Convert the enum to a string
            pre.setInt(9, user.getIduser());
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int userId) {
        try {
            String req = "DELETE FROM user WHERE iduser=?";
            pre = con.prepareStatement(req);
            pre.setInt(1, userId);
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public User getById(int userId) {
        User user = null;
        try {
            String req = "SELECT * FROM user WHERE iduser=?";
            pre = con.prepareStatement(req);
            pre.setInt(1, userId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setIduser(rs.getInt("iduser"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setTel(rs.getString("tel"));
                user.setAddress(rs.getString("address"));
                user.setRole(UserRole.valueOf(rs.getString("role"))); // Convert the string to an enum
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }
   

    @Override
    public List<User> afficherTous() {
        List<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM user";
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setIduser(rs.getInt("iduser"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setTel(rs.getString("tel"));
                user.setAddress(rs.getString("address"));
                user.setRole(UserRole.valueOf(rs.getString("role"))); // Convert the string to an enum
                users.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }
    public User authenticate(String username, String password) {
        User user = null;
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setIduser(resultSet.getInt("iduser"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setTel(resultSet.getString("tel"));
                user.setAddress(resultSet.getString("address"));
                user.setRole(UserRole.valueOf(resultSet.getString("role")));
                 
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return user;
    }
     public List<User> searchByUsername(String username) {
        List<User> searchResults = new ArrayList<>();

        try {
            String query = "SELECT * FROM user WHERE username LIKE ?";
            pre = con.prepareStatement(query);
            pre.setString(1, "%" + username + "%"); // Use a wildcard to find usernames containing the entered text
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setIduser(rs.getInt("iduser"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setTel(rs.getString("tel"));
                user.setAddress(rs.getString("address"));
                user.setRole(UserRole.valueOf(rs.getString("role"))); // Convert the string to an enum

                searchResults.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return searchResults;
    }
     public boolean isEmailRegistered(String email) {
    try {
        String query = "SELECT COUNT(*) FROM user WHERE email = ?";
        pre = con.prepareStatement(query);
        pre.setString(1, email);
        ResultSet resultSet = pre.executeQuery();
        
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            return count > 0; // Return true if count is greater than 0, indicating that the email is registered
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    return false; // Return false by default (an error occurred or email not found)
}
public void updateUserPasswordByEmail(String email, String newPassword) {
    try {
        String query = "UPDATE user SET password = ? WHERE email = ?";
        pre = con.prepareStatement(query);
        pre.setString(1, newPassword);
        pre.setString(2, email);
        pre.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    public List<User> advancedSearch(String searchAttribute, String searchValue) {
    List<User> searchResults = new ArrayList<>();

    try {
        String query = "SELECT * FROM user WHERE " + searchAttribute + " LIKE ?";
        pre = con.prepareStatement(query);
        pre.setString(1, "%" + searchValue + "%"); // Use a wildcard to find values containing the entered text
        ResultSet rs = pre.executeQuery();

        while (rs.next()) {
            User user = new User();
            user.setIduser(rs.getInt("iduser"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setTel(rs.getString("tel"));
            user.setAddress(rs.getString("address"));
            user.setRole(UserRole.valueOf(rs.getString("role"))); // Convert the string to an enum

            searchResults.add(user);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return searchResults;
}
 
    public User getUserById(int userId) throws SQLException {
        String query = "SELECT * FROM user WHERE iduser = ?";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setIduser(resultSet.getInt("iduser"));
                    user.setUsername(resultSet.getString("username"));

                    // Vous pouvez ajouter d'autres attributs de l'utilisateur ici si nécessaire

                    return user;
                }
            }
        }

        return null; // Retourne null si l'utilisateur n'est pas trouvé
    }


// ...


    // ... other methods

    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); // Handle the exception according to your application's error handling
        }
        return null;
    }
}



