/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.sql.Date;

/**
 *
 * @author Med-Amine
 */
public class Expertise {
    private int idIdee;
    private int iduser; // Clé étrangère vers la classe User
    private String titreIdee;
    private String pubIdee;
    private Date dateIdee;
    private String reponseAvis;

    public Expertise() {
    }

    public Expertise(int idIdee, int iduser, String titreIdee, String pubIdee, Date dateIdee, String reponseAvis) {
        this.idIdee = idIdee;
        this.iduser = iduser;
        this.titreIdee = titreIdee;
        this.pubIdee = pubIdee;
        this.dateIdee = dateIdee;
        this.reponseAvis = reponseAvis;
    }

    public int getIdIdee() {
        return idIdee;
    }

    public void setIdIdee(int idIdee) {
        this.idIdee = idIdee;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getTitreIdee() {
        return titreIdee;
    }

    public void setTitreIdee(String titreIdee) {
        this.titreIdee = titreIdee;
    }

    public String getPubIdee() {
        return pubIdee;
    }

    public void setPubIdee(String pubIdee) {
        this.pubIdee = pubIdee;
    }

    public Date getDateIdee() {
        return dateIdee;
    }

    public void setDateIdee(Date dateIdee) {
        this.dateIdee = dateIdee;
    }

    public String getReponseAvis() {
        return reponseAvis;
    }

    public void setReponseAvis(String reponseAvis) {
        this.reponseAvis = reponseAvis;
    }

    @Override
    public String toString() {
        return "Expertise{" + "idIdee=" + idIdee + ", iduser=" + iduser + ", titreIdee=" + titreIdee + ", pubIdee=" + pubIdee + ", dateIdee=" + dateIdee + ", reponseAvis=" + reponseAvis + '}';
    }
    
    
}
