/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenjava.mavenjavafx;

import com.google.zxing.WriterException;
import com.mavenjava.Class.Disponibilites;
import com.mavenjava.Class.Reservations;
import com.mavenjava.core.Method;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author HERNAVAL
 */
public class SIMPLEController extends Method implements Initializable {

    @FXML
    private Label nomcat;
    @FXML
    private GridPane gp;
    @FXML
    private TextField numPlace;
    @FXML
    private Pane solde;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        findPlace();
    } 
    
    public void findPlace(){
       ArrayList<String> placeComplet = new ArrayList<>();
       ArrayList<String> list = new ArrayList<>();
       String[] temp;
       
       Reservations resa = new Reservations();
       String idTemp = SelectDateController.idtemp;
       resa.setIdTemp(idTemp);resa.setNomCategorie("SIMPLE");
       
        placeComplet = resa.loadPlace();
        String[] place = new String[placeComplet.size()];
        placeComplet.toArray(place);
        
        for(int i=0;i<place.length;i++){
          list.add(place[i]);
        }
        
        String[] placeDetails= new String[list.size()];
        list.toArray(placeDetails);
        
        for(int i=64,ligne=0;(i<165 && ligne<10);ligne++,i+=10){
            for(int j=i, col=0;  (j< i+10 && col<10) ;col++,j++){
                Button b = new Button();
                Image seat = new Image(getClass().getResourceAsStream("/img/icons8_chair_32.png"));
                boolean exist = false;
                
                b.setGraphic(new ImageView(seat));
                Pane p = new Pane();
                String value =""+j;
                b.setText(value);
                gp.add(p,col,ligne);
                b.setAlignment(Pos.CENTER);
                gp.add(b, col, ligne);
                String num =b.getText();
                
                if(list.contains(num)){
                   p.setStyle("-fx-background-color:#1578ff;"); 
                }
               if(list.size() >= 20) solde.setVisible(true);
               
               b.setOnAction((ActionEvent event) -> {
                   
                   String text  = b.getText();
                   Alert a = new Alert(Alert.AlertType.INFORMATION);
                   a.setContentText("deja prix");
                    if (list.contains(text)) {
                        a.show();
                    } else {
                        numPlace.setText(text);
                    }
                });
               
            }
        }
    }
    @FXML
    private void reserver(ActionEvent event) {
       String reference = super.refGen("TK-", 6);
       String id = SelectDateController.idtemp;
       String nomCategorie = nomcat.getText();
       String num = numPlace.getText();
       String [] numPlace = num.split(",");
       
       int nb = numPlace.length;
       
       
       Reservations resa = new Reservations(id,reference,nomCategorie,num);
       Disponibilites disp = new Disponibilites(id,nomCategorie);
       
       resa.valider();
       disp.setIdTemp(id);disp.setNomCategorie(nomCategorie);
       disp.majNbPlace(nb);
       
       //génération du qr
       File f = new File("F:\\projet\\MavenJavaFx\\src\\main\\resources\\qr\\"+reference+".png");
       
        try {
            f.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(SIMPLEController.class.getName()).log(Level.SEVERE, null, ex);
        }
            String path ="F:\\projet\\MavenJavaFx\\src\\main\\resources\\qr\\"+reference+".png";
            
        int width = 200 ;
        int heigth= 200 ;
        
        try {
            super.generate(reference, width, heigth, path);
        } catch (WriterException ex) {
            Logger.getLogger(SIMPLEController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SIMPLEController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String msg ="Réservation avec succès";
        super.alert(msg);
        BlablaController.numTicket = reference;
        super.openView("TicketV");
        
        super.alert(msg);
    }
    
}
