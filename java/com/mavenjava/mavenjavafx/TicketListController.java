/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenjava.mavenjavafx;

import com.mavenjava.Class.Diffusions;
import com.mavenjava.Class.Films;
import com.mavenjava.Class.Places;
import com.mavenjava.Class.Reservations;
import com.mavenjava.Class.Tickets;
import com.mavenjava.core.Method;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author HERNAVAL
 */
public class TicketListController extends Method implements Initializable {
    public static String refT;
     public static String cat;
    @FXML
    private TableView<Reservations> tickets;
    @FXML
    private TableColumn<Reservations, String> ref;
    
    @FXML
    private TableColumn<Reservations, String> numPlace;
    @FXML
    private TableColumn<Reservations, String> nomcat;
    @FXML
    private ContextMenu cm;
    @FXML
    private MenuItem voir;
    @FXML
    private MenuItem edit;
    @FXML
    private MenuItem delete;
    @FXML
    private TableColumn<?, ?> film;
    @FXML
    private ComboBox<String> catCombo;
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showFilms();
        init();
        //fillCombo();
    } 
    public void init(){
        Image file = new Image(getClass().getResourceAsStream("/img/file_24px.png"));
        Image modif = new Image(getClass().getResourceAsStream("/img/edit_26px.png"));
       voir.setGraphic(new ImageView(file));
       edit.setGraphic(new ImageView(modif));

    }
    
    public void fillCombo(){
        Places pl = new Places();
        ResultSet res = pl.getPlaces();
        String item ="NomCategorie";
        String value="Classe";
        super.fillCombo(catCombo, res, item, value);
    }
     public void showFilms(){
        try {
            ObservableList<Reservations> ticket = FXCollections.observableArrayList();
            Reservations resa = new Reservations();
            ResultSet res = resa.getResaInfo();
            while(res.next()){
                ticket.add(new Reservations(
                res.getString("Ref"),
                res.getString("NumPlace"),
                res.getString("NomCategorie")
                ));
            }
            
            ref.setCellValueFactory(new PropertyValueFactory("Ref"));
            numPlace.setCellValueFactory(new PropertyValueFactory("NumPlace"));
            nomcat.setCellValueFactory(new PropertyValueFactory("NomCategorie"));
            
            tickets.setItems(ticket);
        } catch (SQLException ex) {
            Logger.getLogger(FilmsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public String getIdSelected(){
        /*Reservations resa = tickets.getSelectionModel().getSelectedItem();
        String id = resa.getRef();*/
        return "navalona";
    }

   @FXML
    private void getRef(MouseEvent event) {
        if(event.getButton() == MouseButton.SECONDARY){
            super.showContextMenu(cm, tickets, event);
            Reservations resa = tickets.getSelectionModel().getSelectedItem();
            refT = resa.getRef();
             cat  = resa.getNomCategorie();
            actionButton();
            
        }else{
            Reservations resa = tickets.getSelectionModel().getSelectedItem();
            refT = resa.getRef();
        }
        
    }
    
   
   public void actionButton(){
       voir.setOnAction(ActionEvent ->{
                BlablaController.numTicket = refT;
                switch(cat){
                    case "VIP":
                      super.openView("TicketV");
                    break;
                    case "PREMIUM":
                       super.openView("TicketP"); 
                    break;
                    case "SIMPLE":
                        super.openView("TicketS");
                }
                
       });
       
       delete.setOnAction(ActionEvent->{
           Reservations resa = new Reservations();
           resa.setRef(refT);
           resa.cancel();
           super.alert("Ticket supprimé");
           //update disponibilite
           
       });
       edit.setOnAction(ActionEvent ->{
           BlablaController.numTicket = refT;
       });
   }

    @FXML
    private void catFilter(ActionEvent event) {
         try {
            ObservableList<Reservations> ticket = FXCollections.observableArrayList();
            Reservations resa = new Reservations();
            
            String param = catCombo.getValue();
            resa.setNomCategorie(param);
            ResultSet res = resa.getResaInfo();
            
            while(res.next()){
                ticket.add(new Reservations(
                res.getString("Ref"),
                res.getString("NumPlace"),
                res.getString("NomCategorie")
                ));
            }
            
            ref.setCellValueFactory(new PropertyValueFactory("Ref"));
            numPlace.setCellValueFactory(new PropertyValueFactory("NumPlace"));
            nomcat.setCellValueFactory(new PropertyValueFactory("NomCategorie"));
            
            tickets.setItems(ticket);
        } catch (SQLException ex) {
            Logger.getLogger(FilmsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
