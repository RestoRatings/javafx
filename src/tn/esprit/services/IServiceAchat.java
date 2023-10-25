/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import tn.esprit.entities.TypeC;

/**
 *
 * @author Med-Amine
 */
public interface IServiceAchat<Achat> { 
    void ajouter(Achat A) throws SQLException ;

    void modifier(Achat A) throws SQLException ;

    void supprimer(int i) throws SQLException ;

    List<Achat> recuperer() throws SQLException ;
    
    List<Achat> recupererByDate(Date date) throws SQLException;
    
    List<Achat> recupererByType(TypeC type) throws SQLException;
}
