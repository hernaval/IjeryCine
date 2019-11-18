/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenjava.mavenjavafx;

import com.mavenjava.Class.Films;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HERNAVAL
 */
public class AjoutFilmController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private TextField genre;
    @FXML
    private TextField duree;
    @FXML
    private TextField idfilm;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addFilm(ActionEvent event) {
        String id = idfilm.getText();
        String t = titre.getText();
        String d = duree.getText();
        String ge = genre.getText();
        Films f = new Films(id,t,d,ge);
        f.insert();
        idfilm.setText("");
        titre.setText("");
        duree.setText("");
        genre.setText("");
    }
    
}
