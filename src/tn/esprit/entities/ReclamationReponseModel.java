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
public class ReclamationReponseModel {
     private String username;
    private Date daterec;
    private String etatrec;
    private String description;
    private String typerec;
    private String contenue;
    private Date daterep;

    public ReclamationReponseModel(String username, Date daterec, String etatrec, String description, String typerec, String contenue, Date daterep) {
        this.username = username;
        this.daterec = daterec;
        this.etatrec = etatrec;
        this.description = description;
        this.typerec = typerec;
        this.contenue = contenue;
        this.daterep = daterep;
    }

    public ReclamationReponseModel() {
       
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDaterec() {
        return daterec;
    }

    public void setDaterec(Date daterec) {
        this.daterec = daterec;
    }

    public String getEtatrec() {
        return etatrec;
    }

    public void setEtatrec(String etatrec) {
        this.etatrec = etatrec;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTyperec() {
        return typerec;
    }

    public void setTyperec(String typerec) {
        this.typerec = typerec;
    }

    public String getContenue() {
        return contenue;
    }

    public void setContenue(String contenue) {
        this.contenue = contenue;
    }

    public Date getDaterep() {
        return daterep;
    }

    public void setDaterep(Date daterep) {
        this.daterep = daterep;
    }

    @Override
    public String toString() {
        return "ReclamationReponseModel{" + "username=" + username + ", daterec=" + daterec + ", etatrec=" + etatrec + ", description=" + description + ", typerec=" + typerec + ", contenue=" + contenue + ", daterep=" + daterep + '}';
    }
    
    
}
