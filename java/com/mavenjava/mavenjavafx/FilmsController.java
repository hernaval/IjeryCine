/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenjava.mavenjavafx;

import com.mavenjava.Class.Films;
import com.mavenjava.Class.Reservations;
import com.mavenjava.core.Method;
import static com.mavenjava.mavenjavafx.TicketListController.refT;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author HERNAVAL
 */
public class FilmsController extends Method implements Initializable {

    @FXML
    private TableView<Films> filmList;
    @FXML
    private TableColumn<Films,String > idfilm;
    @FXML
    private TableColumn<Films,String > titre;
    @FXML
    private TableColumn<Films,String > duree;
    @FXML
    private TableColumn<Films,String > genre;
    private ContextMenu cm;
    
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showFilms();
    }  
    
    
    public void showFilms(){
        try {
            ObservableList<Films> films = FXCollections.observableArrayList();
            Films f = new Films();
            ResultSet res = f.getFilms();
            while(res.next()){
                films.add(new Films(
                res.getString("IdFilm"),
                res.getString("Titre"),
                res.getString("Duree"),
                res.getString("Genre")       
                ));
            }
            
            idfilm.setCellValueFactory(new PropertyValueFactory("IdFilm"));
            titre.setCellValueFactory(new PropertyValueFactory("Titre"));
            duree.setCellValueFactory(new PropertyValueFactory("Duree"));
            genre.setCellValueFactory(new PropertyValueFactory("Genre"));
            
            filmList.setItems(films);
        } catch (SQLException ex) {
            Logger.getLogger(FilmsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void getIdFilm(MouseEvent event) {
        if(event.getButton() == MouseButton.SECONDARY){
            ContextMenu cm2 = new ContextMenu();
                MenuItem item  = new MenuItem("voir");
                MenuItem item2  = new MenuItem("supprimer");
                cm2.getItems().addAll(item,item2);
                
                /*item2.setOnAction((javafx.event.ActionEvent evt) ->{
                    Films f = filmList.getSelectionModel().getSelectedItem();
                   
                    String id =f.getTitre();
                    Films film = new Films(id);
                    film.delete();
                    super.alert("Film supprimé");
                });*/
            
        }else{
            
        }
    }
    
    /*public void actionButton(){
       voir.setOnAction(ActionEvent ->{
                BlablaController.numTicket = refT;
                super.openView("TicketV");
       });
       delete.setOnAction(ActionEvent->{
           Reservations resa = new Reservations();
           resa.setRef(refT);
           resa.cancel();
           super.alert("Ticket supprimé");
           //update disponibilite
           
       });
   }*/
}
