/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenjava.mavenjavafx;

import com.mavenjava.Class.Temps;
import com.mavenjava.core.Method;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author HERNAVAL
 */
public class SelectDateController extends Method implements Initializable {
    public static String idtemp;
    @FXML
    private ComboBox<?> comboT;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillCombo();
    }  
    public void fillCombo(){
        Temps t = new Temps();
        ResultSet res = t.getTemps();
        String item = "IdTemp";
        String value ="Temps";
        
        super.fillCombo(comboT, res, item, value);
    }

    @FXML
    private void resaView(ActionEvent event) {
        idtemp = comboT.getValue().toString();
        super.openView("Reservations");
    }
    
   

    
    
}
