/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;



/**
 *
 * @author Med-Amine
 */
public class Evennement {
    private int id;
    private String titre;
    private LocalDate date;  
    private Timestamp dateStamp;

    public Timestamp getDateStamp() {
        LocalDateTime localDateTime = date.atStartOfDay();
        return Timestamp.valueOf(localDateTime);
    }

    public void setDateStamp(Timestamp dateStamp) {
        this.dateStamp = dateStamp;
    }
    private String  description;
    private String adresse;    
    private String img;

    private String lieu;
    private List<Participant> participants;

    public Evennement() {
    }

    public Evennement(int id, String titre,  LocalDate date, String description,String img, String adresse, String lieu) {
        this.id = id;
        this.titre = titre;
        this.date = date;
        this.description = description; 
        this.img = img;
        this.adresse = adresse;
        this.lieu = lieu;
    }

    public Evennement(String titre, LocalDate date, String description,String img, String adresse, String lieu) {
        this.titre = titre;
        this.date = date;
        this.description = description;
        this.adresse = adresse;  
        this.img = img;

        this.lieu = lieu;
    }
    
    public Evennement(int id, String titre, LocalDate date, String description, String adresse, String lieu, List<Participant> participants) {
        this.id = id;
        this.titre = titre;
        this.date = date;
        this.description = description;
        this.adresse = adresse;
        this.lieu = lieu;
        this.participants =new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "Evennement{" + "id=" + id + ", titre=" + titre + ", date=" + date + ", dateStamp=" + dateStamp + ", description=" + description + ", adresse=" + adresse + ", img=" + img + ", lieu=" + lieu + ", participants=" + participants + '}';
    }
    

   



   
    
    
}
