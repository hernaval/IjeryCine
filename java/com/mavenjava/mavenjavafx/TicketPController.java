/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenjava.mavenjavafx;

import com.mavenjava.Class.Diffusions;
import com.mavenjava.Class.Reservations;
import com.mavenjava.Class.Tarifs;
import com.mavenjava.Class.Temps;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author HERNAVAL
 */
public class TicketPController implements Initializable {
    
    @FXML
    private Label prix;
    @FXML
    private Label numPlace;
    @FXML
    private Label timeStart;
    @FXML
    private Label dateStart;
    @FXML
    private Label titre;
    
    @FXML
    private Label ref;
    @FXML
    private ImageView qr;
    @FXML
    private Label salle;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillTickets();
    }    
    public void fillTickets(){
        try {
            //num ticket oui -> getIdTemp -> tarif,diffusions
            Reservations resa = new Reservations();
            String refa= BlablaController.numTicket;
            String idTemp="";
            String prixEntre="";
            String film ="";
            String num="";
            String heure="";
            String date="";
            String nomSalle="";
            resa.setRef(refa);
            String param = "VIP";
            ResultSet res = resa.getTicketInfo();
            while(res.next()){
                idTemp = res.getString("IdTemp");
                num = res.getString("NumPlace");
            } 
            //tarif 
            Tarifs paf = new Tarifs(idTemp,param);
            ResultSet p = paf.getTarifs();
            while(p.next()){
                prixEntre = p.getString("Prix");
            }
            //info diffusion
            Temps tp = new Temps(idTemp);
            Diffusions diff =new Diffusions(idTemp);
            ResultSet program = diff.getTitre();
            ResultSet temps = tp.getInfoTemps();
            while(temps.next()){
                heure = temps.getString("TimeStart");
                date = temps.getString("DateStart");
            }
            while(program.next()){
                film = program.getString("Titre");
                nomSalle = program.getString("NomSalle");
            }
            //fill ticket
            dateStart.setText(date);
            timeStart.setText(heure);
            prix.setText(prixEntre+" AR");
            ref.setText(refa);
            numPlace.setText(num);
            salle.setText(nomSalle);
            titre.setText(film);
            
            Image img = new Image(getClass().getResourceAsStream("/qr/"+refa+".png"));
            qr.setImage(img);
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketVController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
