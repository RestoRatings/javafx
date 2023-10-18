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
import tn.esprit.entities.User;
import tn.esprit.entities.UserRole;
import tn.esprit.utils.Datasource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
}
