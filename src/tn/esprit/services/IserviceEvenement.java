/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.util.List;

/**
 *
 * @author Med Iheb
 * @param <Evennement>
 */
public interface IserviceEvenement <Evennement>{
     public void ajouter(Evennement event) ;
    public void modifier(Evennement even);
    public void supprimer(int id);
    public Evennement getOne(Evennement even);
    public List<Evennement> getAll( );
    List<Evennement> recupererBytitre(String titre) ;

}