/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;
import java.sql.Date;
import java.text.SimpleDateFormat;
/**
 *
 * @author mchai
 */
public class Reponse {
    private int idrep ;
    private Reclamation reclamation;
    private String contenue;
    private Date daterep ;
    
    public Reponse() {
        
    }

    public Reponse(int idrep, String contenue, Date daterep) {
        this.idrep = idrep;
        this.contenue = contenue;
        this.daterep = daterep;
    }


    public Reponse(int idrep,Reclamation reclamation, String contenue, Date daterep) {
        this.idrep = idrep;
        this.reclamation=reclamation;
        this.contenue = contenue;
        this.daterep = daterep;
    }

    public Reponse(Reclamation reclamation,String contenue, Date daterep) {
         this.reclamation=reclamation;
        this.contenue = contenue;
        this.daterep = daterep;
    }

    public Reponse(int idrep,Reclamation reclamation, String contenue) {
        this.idrep = idrep;
         this.reclamation=reclamation;
        this.contenue = contenue;
    }

    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    
    

    public int getIdrep() {
        return idrep;
    }

    public void setIdrep(int idrep) {
        this.idrep = idrep;
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
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = (daterep != null) ? dateFormat.format(daterep) : "N/A";
        return "Reponse{" + "idrep=" + idrep + ",reclamation=" + reclamation.getIdrec() +", contenue=" + contenue + ", date=" + daterep +  '}';
    }

}
