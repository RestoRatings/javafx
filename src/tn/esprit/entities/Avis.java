/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;
import java.sql.Date;
/**
 *
 * @author mchai
 */
public class Avis {
   private int idAvis;
    private int iduser; 
    private int idIdee; 
    private String titreAvis;
    private String pubAvis;
    private Date dateAvis;

    public Avis() {
    }

    public Avis(int idAvis, int iduser, int idIdee, String titreAvis, String pubAvis, Date dateAvis) {
        this.idAvis = idAvis;
        this.iduser = iduser;
        this.idIdee = idIdee;
        this.titreAvis = titreAvis;
        this.pubAvis = pubAvis;
        this.dateAvis = dateAvis;
    }

    public int getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(int idAvis) {
        this.idAvis = idAvis;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdIdee() {
        return idIdee;
    }

    public void setIdIdee(int idIdee) {
        this.idIdee = idIdee;
    }

    public String getTitreAvis() {
        return titreAvis;
    }

    public void setTitreAvis(String titreAvis) {
        this.titreAvis = titreAvis;
    }

    public String getPubAvis() {
        return pubAvis;
    }

    public void setPubAvis(String pubAvis) {
        this.pubAvis = pubAvis;
    }

    public Date getDateAvis() {
        return dateAvis;
    }

    public void setDateAvis(Date dateAvis) {
        this.dateAvis = dateAvis;
    }

    @Override
    public String toString() {
        return "Avis{" + "idAvis=" + idAvis + ", iduser=" + iduser + ", idIdee=" + idIdee + ", titreAvis=" + titreAvis + ", pubAvis=" + pubAvis + ", dateAvis=" + dateAvis + '}';
    }


    
    
  
}
