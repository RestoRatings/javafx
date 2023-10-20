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
public class Achat {
     private int idachat;
    private User user;
    private Plat plat;
    private float montanttotal;
    private int quantite;
    private Date date;
    private TypeC typec;
   

    public Achat() {
    }

    public Achat(int idachat, User user, Plat plat, float montanttotal, int quantite, Date date, TypeC typec) {
        this.idachat = idachat;
        this.user = user;
        this.plat = plat;
        this.montanttotal = montanttotal;
        this.quantite = quantite;
        this.date = date;
        this.typec = typec;
    }

    public Achat(User user, Plat plat, float montanttotal, int quantite, Date date, TypeC typec) {
        this.user = user;
        this.plat = plat;
        this.montanttotal = montanttotal;
        this.quantite = quantite;
        this.date = date;
        this.typec = typec;
    }

    public Achat(int idachat, User user, Plat plat, float montanttotal, int quantite, TypeC typec) {
        this.idachat = idachat;
        this.user = user;
        this.plat = plat;
        this.montanttotal = montanttotal;
        this.quantite = quantite;
        this.typec = typec;
    }

    public Achat(User user, Plat plat, float montanttotal, int quantite, TypeC typec) {
        this.user = user;
        this.plat = plat;
        this.montanttotal = montanttotal;
        this.quantite = quantite;
        this.typec = typec;
    }
    
    

    public int getIdachat() {
        return idachat;
    }

    public void setIdachat(int idachat) {
        this.idachat = idachat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Plat getPlat() {
        return plat;
    }

    public void setPlat(Plat plat) {
        this.plat = plat;
    }

    public float getMontanttotal() {
        return montanttotal;
    }

    public void setMontanttotal(float montanttotal) {
        this.montanttotal = montanttotal;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TypeC getTypec() {
        return typec;
    }

    public void setTypec(TypeC typec) {
        this.typec = typec;
    }

    @Override
    public String toString() {
        return "Achat{" + "idachat=" + idachat + ", user=" + user + ", plat=" + plat + ", montanttotal=" + montanttotal + ", quantite=" + quantite + ", date=" + date + ", typec=" + typec + '}'+" \n";
    }
}
