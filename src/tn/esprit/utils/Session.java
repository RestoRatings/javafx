/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.utils;

import tn.esprit.entities.User;


/**
 *
 * @author LENOVO
 */
public class Session {

    
    private static Session instance;
    private static User currentUser;
    // other instance variables
    
   // private Session() {
        // constructor
  //  }
    
  //  public static synchronized Session getInstance() {
     //   if (instance == null) {
     //       instance = new Session();
     //   }
      //  return instance;
   // }

   
    
   
    
    // other methods


// 3ibara memoire sghira nsajlo fiha data 
   // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int iduser ; 
    private static String username ; 
    private static String email; 
    private static String password ;
    private static String firstName;
    private static String lastName;
    private static String tel;
    private static String address;
    private Session() {
        // constructor
    }
     public static Session getInstance() {
        return instance;
    }

    public static void setInstance(Session instance) {
        Session.instance = instance;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static int getIduser() {
        return iduser;
        
    }

    public static String getUsername() {
        return username;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }

    public static void setCurrentUser(User currentUser) {
        Session.currentUser = currentUser;
    }

    public static void setIduser(int iduser) {
        Session.iduser = iduser;
    }

    public static void setUsername(String username) {
        Session.username = username;
    }

    public static void setEmail(String email) {
        Session.email = email;
    }

    public static void setPassword(String password) {
        Session.password = password;
    }

    public static void setFirstName(String firstName) {
        Session.firstName = firstName;
    }

    public static void setLastName(String lastName) {
        Session.lastName = lastName;
    }

    public static void setTel(String tel) {
        Session.tel = tel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Session(String username) {
        this.username = username;
        
    }
}
    


