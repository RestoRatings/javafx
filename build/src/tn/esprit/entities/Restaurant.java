/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Med-Amine
 */
public class Restaurant {
    private int id_restau; 
    private String nom , location;
    

        public Restaurant() {
    }
    private List<Avis> avis = new ArrayList<>(); 
    List<Badge> badge = new ArrayList<>();

    public Restaurant(int id_restau, String nom) {
        this.id_restau = id_restau;
        this.nom = nom;
    }

    
    
    public void ajouterAvis(Avis avis) {
        this.avis.add(avis);
    }
    

    public List<Avis> getAvis() {
        return avis;
    }

    public void setAvis(List<Avis> avis) {
        this.avis = avis;
    }
    public int getId() {
        return id_restau;
    }

    public void setId(int id) {
        this.id_restau = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getlocation() {
        return location;
    }

    public void setlocation(String location) {
        this.location = location;
    }

        public Restaurant(int id_restau, String nom, String location) {
            this.id_restau = id_restau;
            this.nom = nom;
            this.location = location;
        }

  

    public Restaurant(String nom, String location) {
        this.nom = nom;
        this.location = location;
    }

   
         public String toStringForDisplay() {
        return this.nom + " - " + this.location;
    }
    @Override
    public String toString() {
        return "personne id:"+this.id_restau+"  "+this.nom+ " "+ this.location  ;
    }
 
      
}