/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities.dto;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Med-Amine
 */
public class Item {
    private final SimpleStringProperty name;
    private final SimpleDoubleProperty price;
    private final SimpleIntegerProperty quantite;


    public Item(String name, double price,int quantite) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.quantite = new SimpleIntegerProperty(quantite);

    }

    public String getName() {
        return name.get();
    }

    public double getPrice() {
        return price.get();
    }
      public int getQuantite() {
        return quantite.get();
    }
}
