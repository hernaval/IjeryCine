/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenjava.mavenjavafx;

import com.mavenjava.Class.Reservations;
import com.mavenjava.core.Method;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class EditTicketController extends Method implements Initializable {

    @FXML
    private ComboBox<?> comboPlace;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void findPlaces(){
        try {
            String ref = BlablaController.numTicket;
            Reservations resa = new Reservations("sdf",ref);
            String idTemp = "";
             ArrayList<Integer> list = new ArrayList<>();
            ResultSet res = resa.findTemp();
            while(res.next()){
                idTemp = res.getString("IdTemp");
            }
            Reservations resa2 = new Reservations(idTemp,ref);
            ResultSet numPlace = resa2.find();
            while(numPlace.next()){
                int num =  parseInt(numPlace.getString("NumPlace"));
                list.add(num);
            }
            
           
            
        } catch (SQLException ex) {
            Logger.getLogger(EditTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
