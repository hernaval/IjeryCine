/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenjava.mavenjavafx;

import com.mavenjava.Class.Diffusions;
import com.mavenjava.Class.Reservations;
import com.mavenjava.core.Method;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class CheckingController  extends Method implements Initializable {

    @FXML
    private Label titre;
    @FXML
    private Label numPlace;
    @FXML
    private Label salle;
    @FXML
    private TextField refT;
    @FXML
    private ImageView response;
    @FXML
    private Label date;
    public static Image img=null;
    public static String validate;
    @FXML
    private Label validation;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillCheckInfo();
        response.setVisible(false);
    }    
    
    public void fillCheckInfo(){
        try {
            String dt = LocalDate.now().toString();
            date.setText(dt);
            String id = SelectDateController.idtemp;
            Diffusions diff = new Diffusions(id);
            Reservations resa = new Reservations(id);
            ResultSet p = resa.getPlaces();
            ResultSet res = diff.getTitre();
            while(res.next()){
                titre.setText(res.getString("Titre"));
                salle.setText(res.getString("NomSalle"));
            }
            while(p.next()){
                String temp = numPlace.getText();
                String current = p.getString("NumPlace");
                numPlace.setText(temp+" "+current);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void checkTicket(ActionEvent event) {
        Image defaultImage  = new Image(getClass().getResourceAsStream("/img/synchronize_50px.png"));
        response.setImage(defaultImage);
        
        response.setVisible(true);
        RotateTransition rt = new RotateTransition(Duration.seconds(3),response);
            rt.setCycleCount(1);
            rt.setAutoReverse(false);
            rt.setFromAngle(720);
            rt.setToAngle(0);
            rt.play();
        
        try {
            String reference = refT.getText();
           
            String id = SelectDateController.idtemp;
            String status = "";
            String num = ""; 
            Reservations resa = new Reservations(id,reference);
            int count = 0;
            ResultSet res = resa.getInfo();
            while(res.next()){
                status = res.getString("status");
                num = res.getString("NumPlace");   
                count++;
            }
            
            if(count ==0) {
                img = new Image(getClass().getResourceAsStream("/img/high_priority_48px.png"));
                validate = "Ce ticket n'existe pas ou falsifié";
            }else{
                if(status.equals("ok")) {
                img = new Image(getClass().getResourceAsStream("/img/delete_sign_48px.png"));
                validate ="Ce ticket a déjà passé";
                } else{
                resa.checkDone();
                img = new Image(getClass().getResourceAsStream("/img/checkmark_48px.png"));
                numPlace.setText(numPlace.getText()+" "+num);
                validate = "Checking success ";
                }
            }
            
            rt.setOnFinished((e)->{
                response.setImage(img);
                validation.setText(validate);
            });
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CheckingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
