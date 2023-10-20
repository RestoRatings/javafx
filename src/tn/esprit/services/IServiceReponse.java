/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.SQLException;
import java.util.List;
import tn.esprit.entities.Reponse;
/**
 *
 * @author mchai
 */
public interface IServiceReponse <Reponse> {
      void ajouter(Reponse rec) throws SQLException ;

    void modifier(Reponse rec) throws SQLException ;

    void supprimer(int i) throws SQLException ;
    List<Reponse> recuperer() throws SQLException ;
    
}
