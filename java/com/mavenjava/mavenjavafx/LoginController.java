/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenjava.mavenjavafx;

import com.mavenjava.Class.Admins;
import com.mavenjava.core.Method;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author HERNAVAL
 */
public class LoginController extends Method implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ImageView sync;
    @FXML
    private Button loginBtn;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sync.setVisible(false);
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) {
            sync.setVisible(true);
            String user = username.getText();
            String pass = password.getText();
            
            Admins admin = new Admins(user,pass);
            ResultSet res = admin.auth();
            RotateTransition rt = new RotateTransition(Duration.seconds(5),sync);
            rt.setCycleCount(1);
            rt.setAutoReverse(false);
            rt.setFromAngle(720);
            rt.setToAngle(0);
            rt.play();
            
            rt.setOnFinished((e)->{
                try {
                    if(res.next()){
                        super.openView("blabla");
                        Stage stage = (Stage) loginBtn.getScene().getWindow();
                        stage.close();
                        
                    }
                    else{
                        super.alert("données incorrect");
                    }   } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
          });
    }
    
}

       