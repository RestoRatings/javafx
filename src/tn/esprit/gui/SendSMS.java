/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import tn.esprit.entities.Badge;
/**
/**
 *
 * @author LENOVO
 */
public class SendSMS {
    
    
    public static final String ACCOUNT_SID = System.getenv("ACba0c2140f127b621d474ff52a586a2af");
    public static final String AUTH_TOKEN = System.getenv("1aeea7c2b409f56d30ae00d405cb31c6");

    public static void sendSMS(String username, String restaurantName, String badgeType) {
    Twilio.init("ACba0c2140f127b621d474ff52a586a2af", "1aeea7c2b409f56d30ae00d405cb31c6");
    Message message = Message.creator(new PhoneNumber("+21640994876"),
            new PhoneNumber("+12407676656"), "Badge ajouté : " + badgeType + " par l'utilisateur " + username + " au restaurant " + restaurantName).create();

    System.out.println(message.getSid());
}
}
