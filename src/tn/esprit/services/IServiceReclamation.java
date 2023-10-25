/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.SQLException;
import java.util.List;
import tn.esprit.entities.Reclamation;

/**
 *
 * @author mchai
 */
public interface IServiceReclamation<Reclamation> {
     void ajouter(Reclamation rec) throws SQLException ;

    void modifier(Reclamation rec) throws SQLException ;

    void supprimer(int i) throws SQLException ;
    
    List<Reclamation> recuperer() throws SQLException;
public Reclamation rechercherParId(int idrec) throws SQLException;
}
