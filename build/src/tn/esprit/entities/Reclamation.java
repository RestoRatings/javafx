
package tn.esprit.entities;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Med-Amine
 */
    public class Reclamation {
    private int idrec ;
    private User user;
    private Date date ;
    private String description;
    private TypeRec typerec;
    private EtatRec etatrec;
    

    public Reclamation() {
    }

    public Reclamation(int idrec, User user, Date date, String description, TypeRec typerec, EtatRec etatrec) {
        this.idrec = idrec;
        this.user = user;
        this.date = date;
        this.description = description;
        this.typerec = typerec;
        this.etatrec = etatrec;
    }

    public Reclamation(User user, String description, TypeRec typerec, EtatRec etatrec) {
        this.user = user;
        this.description = description;
        this.typerec = typerec;
        this.etatrec = etatrec;
    }

    public Reclamation(int idrec, User user, String description, TypeRec typerec, EtatRec etatrec) {
        this.idrec = idrec;
        this.user = user;
        this.description = description;
        this.typerec = typerec;
        this.etatrec = etatrec;
    }

    

 
    
     
    public Reclamation(int idrec) {
       this.idrec=idrec;}
      
    public int getIdrec() {
        return idrec;
    }

    public void setIdrec(int idrec) {
        this.idrec = idrec;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TypeRec getTyperec() {
        return typerec;
    }

    public void setTyperec(TypeRec typerec) {
        this.typerec = typerec;
    }

    public EtatRec getEtatrec() {
        return etatrec;
    }

    public void setEtatrec(EtatRec etatrec) {
        this.etatrec = etatrec;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = (date != null) ? dateFormat.format(date) : "N/A";
        return "Reclamation{" + "idrec=" + idrec + ", user=" + user.getIduser() + ", date=" +  formattedDate  + ", description=" +  description  + ", typerec=" + typerec + ", etatrec=" + etatrec + '}';
    }}