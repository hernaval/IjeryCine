package com.mavenjava.mavenjavafx;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.mavenjava.core.Model;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class FXMLController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void newpage(ActionEvent event) {
        try {
             Parent root = FXMLLoader.load(getClass().getResource("/fxml/Dashboard.fxml"));
            Stage t = new Stage();
            Scene sc = new Scene(root);
            t.setScene(sc);
            t.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }

    @FXML
    private void home(MouseEvent event) {
        bp.setCenter(ap);
    }

    @FXML
    private void films(MouseEvent event) {
        loadPage("Films");
    }

    @FXML
    private void about(MouseEvent event) {
        loadPage("About");
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
    private void viewCalendar(MouseEvent event) {
        Model m = new Model();
        m.etablirConnection();
        ResultSet res = m.select("Diffusion", "1=1");
        String sql = "select * from diffusion";
         
           String dateStart;
           String heureStart;
           String heureEnd;
           String titre;
           CalendarView calendarView = new CalendarView();
                   Stage stage = new Stage();
             
                Calendar birthdays = new Calendar("Birthdays"); 
                Calendar holidays = new Calendar("Holidays");
                
               
        try {
            while(res.next()){
                Entry annif = new Entry();
                dateStart = res.getString("DateDiff");
                heureStart = res.getString("HeureDiff");
                titre = res.getString("Titre");
                
                annif.changeStartDate(LocalDate.parse(dateStart, DateTimeFormatter.ISO_DATE));
                annif.changeStartTime(LocalTime.parse(heureStart, DateTimeFormatter.ISO_TIME));
                annif.changeEndTime(LocalTime.of(10, 20));
                annif.setTitle(titre);
                birthdays.addEntry(annif);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
               //EventHandler<CalendarEvent> handler = evt -> foo(evt);
              // birthdays.addEventHandler(handler);
              
                CalendarSource myCalendarSource = new CalendarSource("My Calendars");
                myCalendarSource.getCalendars().addAll(birthdays, holidays);

                calendarView.getCalendarSources().addAll(myCalendarSource); 

                calendarView.setRequestedTime(LocalTime.now());
              
                Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
                        @Override
                        public void run() {
                                while (true) {
                                        Platform.runLater(() -> {
                                                calendarView.setToday(LocalDate.now());
                                                calendarView.setTime(LocalTime.now());
                                        });

                                        try {
                                                // update every 10 seconds
                                                //every direct update shoud place here
                                                sleep(10000);
                                        } catch (InterruptedException e) {
                                                e.printStackTrace();
                                        }

                                }
                        };
                };

                updateTimeThread.setPriority(Thread.MIN_PRIORITY);
                updateTimeThread.setDaemon(true);
                updateTimeThread.start();

                Scene scene = new Scene(calendarView);
                stage.setTitle("Calendar");
                stage.setScene(scene);
                stage.setWidth(800);
                stage.setHeight(800);
                stage.centerOnScreen();
                stage.show();
        
    }

    @FXML
    private void newFilm(MouseEvent event) {
        try {
            Stage stage = new Stage();
            
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/AjoutFilm.fxml"));
            Scene sc = new Scene(root);
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }


}
