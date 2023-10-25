/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entities.Restaurant;



/**
 *
 * @author Hamdi
 */
public class Reservation {
    private int id_res; 
    private LocalDate datereser ;
    private LocalTime timereser;
    Restaurant restau;
    User user;
     //private String restauNom;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }
    
    

    public Restaurant getRestau() {
        return restau;
    }

    public void setRestau(Restaurant restau) {
        this.restau = restau;
    }
    
    
    

    public LocalTime getTimereser() {
        return timereser;
    }

    public void setTimereser(LocalTime timereser) {
        this.timereser = timereser;
    }
    

    public LocalDate getDatereser() {
        return datereser;
    }

    public void setDatereser(LocalDate datereser) {
        this.datereser = datereser;
    }

    public Reservation(LocalDate datereser, LocalTime timereser, Restaurant restau, User user) {
        this.datereser = datereser;
        this.timereser = timereser;
        this.restau = restau;
        this.user = user;
    }

  





    

 


    

    public int getId() {
        return id_res;
    }

    public void setId(int id) {
        this.id_res = id;
    }
    

 


    public Reservation() {
    }



    public Reservation(int id_res,LocalDate datereser, LocalTime timereser, Restaurant restau) {
        this.id_res = id_res;
        
        this.datereser = datereser;
        this.timereser = timereser;
        this.restau = restau;
    }

    public Reservation(int id_res, LocalDate datereser, LocalTime timereser) {
        this.id_res = id_res;
        this.datereser = datereser;
        this.timereser = timereser;
    }

 


    @Override
    public String toString() {
        return "reservation id:"+this.id_res+"  "+this.datereser + " " +this.timereser;
    }
    
    
}