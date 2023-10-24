/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.util.List;
import tn.esprit.entities.User;

/**
 *
 * @author Med Iheb
 * @param <Participation>
 */
public interface  IserviceParticipation<Participation> {
    public void ajouter(Participation participation) ;
    public void modifier(Participation participation,int id);
    public void supprimer(int numero);
    public void supprimerMonParticipation(int idUser,int idEvent);
    public List<User> getAllUSER(User user);
    public List<Participation> getAll( );
    
    
    
}