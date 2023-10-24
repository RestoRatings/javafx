/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;



/**
 *
 * @author LENOVO
 */

import tn.esprit.entities.User;

import java.util.List;

public interface IServiceUser {

    void ajouter(User user);
    void modifier(User user);
    void supprimer(int iduser);
    User getById(int iduser);
    List<User> afficherTous();
}
   
