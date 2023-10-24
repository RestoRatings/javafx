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
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.Reservation;
import tn.esprit.entities.Restaurant;

/**
 *
 * @author LENOVO
 */
public class ServiceReservation implements Iservice<Reservation> {
     Connection con; 
    Statement ste;

    public ServiceReservation() {
        con = tn.esprit.utils.Datasource.getInstance().getCnx();   }
    
    

    @Override
    public void ajouter(Reservation r) {
        try {
            String req = "INSERT INTO reservation(datereser,timereser,id_restau,id_user)values(?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(req);
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = r.getDatereser().format(formatter);
         DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = r.getTimereser().format(timeFormatter);
            pre.setString(1,formattedDate);
            pre.setString(2,formattedTime);
            pre.setInt(3,r.getRestau().getId());
            pre.setInt(4,55);
            pre.executeUpdate();
            
            
            
            } catch (SQLException ex) {
                System.out.println(ex);
            
        }
       
    }

    @Override
    public void supprimer(Reservation r) {
             try{
        
         String req = "DELETE FROM reservation WHERE id_res = ?";
          PreparedStatement pre = con.prepareStatement(req);
         pre.setInt(1, r.getId()); 
             pre.executeUpdate();}
         catch (SQLException ex) {
                System.out.println(ex);
            
        }}
    

   @Override
    public void modifier(Reservation r) {
         try {
            String req = "update reservation   SET datereser=?,timereser=?,id_restau=?,id_user= ? WHERE id_res = ?";
            PreparedStatement pre = con.prepareStatement(req);
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = r.getDatereser().format(formatter);
         DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = r.getTimereser().format(timeFormatter);
            pre.setString(1,formattedDate);
            pre.setString(2,formattedTime);
            pre.setInt(3,r.getRestau().getId());
            pre.setInt(4,55);
            pre.setInt(5, r.getId_res());
            pre.executeUpdate();
            
            
            
            } catch (SQLException ex) {
                System.out.println(ex);
            
        }
       
    }

    @Override
    public List<Reservation> affihcer() {
        List<Reservation> reservation = new ArrayList<>();
       // String sql ="select * from Reservation";
        String sql = "SELECT r.*, re.nom AS restauNom, re.location AS restauLocation " +
             "FROM reservation r " +
             "JOIN restaurant re ON r.id_restau = re.id_restau ";// + // Add a space here
           //  "WHERE r.id_restau = 21";

        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

       
    
            LocalDate localDate = LocalDate.parse(rs.getString("datereser"), formatter);
              DateTimeFormatter formatters = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime times = LocalTime.parse(rs.getString("timereser"), formatters);
                Restaurant restaurantObject = new Restaurant();
                     Reservation r= new Reservation(rs.getInt("id_res"),
                        localDate, times,restaurantObject);
                     
           // restaurant restaurantObject = new restaurant();
            restaurantObject.setNom(rs.getString("restauNom"));
            restaurantObject.setlocation(rs.getString("restauLocation"));

            r.setRestau(restaurantObject);
                      
                reservation.add(r);
                        for (Reservation element : reservation) {
                System.out.print("Reservation ID: " + element.getId_res());
    System.out.print(" | Reservation Date: " + element.getDatereser());
    System.out.print(" | Reservation Time: " + element.getTimereser());
    System.out.print(" | Restaurant Name: " + element.getRestau().getNom());
    System.out.println(" | Restaurant Location: " + element.getRestau().getlocation());

            
            }}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reservation;
    }
        public Reservation getReservationParId(int id) {
    String sql = "SELECT * FROM reservation WHERE id_res= ?";
    try {
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            int reservation_id = rs.getInt("id_res");
            String date = rs.getString("datereser");
            String time = rs.getString("timereser");
            
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
               LocalDate Dates = LocalDate.parse(date, formatter);
                DateTimeFormatter formatters = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime times = LocalTime.parse(time, formatters);
            // Create and return a restaurant object with retrieved data
            return new Reservation(reservation_id, Dates, times);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null;
}

}
