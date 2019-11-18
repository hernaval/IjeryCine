/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenjava.mavenjavafx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author HERNAVAL
 */
public class ReservationsController implements Initializable {

    public  TextField numPlace;
    @FXML
    public  Label ref;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void init(){
        String id = SelectDateController.idtemp;
        ref.setText(id);
        
    }
    
    public void loadPage(String page){
        Parent root =null;
        
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/"+page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
    }

    @FXML
    private void vip(ActionEvent event) {
        loadPage("VIP");
        
    }

    @FXML
    private void premium(ActionEvent event) {
        loadPage("PREMIMUM");
    }

    @FXML
    private void simple(ActionEvent event) {
        loadPage("SIMPLE");
    }
    
    
}
