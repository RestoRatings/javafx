/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;



/**
 *
 * @author Med-Amine
 */
public class Participant {
    
    private LocalDate datepar ;
    private int numero;
    private User User;
      private Evennement event;

    public Participant() {
    }

    public Participant(LocalDate datepar, int numero) {
        this.datepar = datepar;
        this.numero = numero;
    }

    public Participant(LocalDate datepar, Evennement event) {
        this.datepar = datepar;
        this.event = event;
    }

    public Participant(LocalDate datepar, int numero, User User) {
        this.datepar = datepar;
        this.numero = numero;
        this.User = User;
    }

    public Participant(LocalDate datepar, int numero, User User ,Evennement event) {
        this.datepar = datepar;
        this.numero = numero;
        this.User = User;
         this.event = event;
    }
    

    public LocalDate getDatepar() {
        return datepar;
    }

    public void setDatepar(LocalDate datepar) {
        this.datepar = datepar;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User User) {
        this.User = User;
    }

    public Evennement getEvent() {
        return event;
    }

    public void setEvent(Evennement event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Participant{" + "datepar=" + datepar + ", numero=" + numero + ", User=" + User + ", event=" + event + '}';
    }

    

   

   

  

   
    
    
    
    
    
}
