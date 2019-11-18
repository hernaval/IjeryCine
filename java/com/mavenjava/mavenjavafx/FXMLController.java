package com.mavenjava.mavenjavafx;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.calendarfx.view.DateControl;
import com.mavenjava.Class.Diffusions;
import com.mavenjava.Class.Temps;
import com.mavenjava.core.Method;
import com.mavenjava.core.Model;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;


public class FXMLController extends Method implements Initializable {

   
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
   /* @FXML
    private void viewCalendar(MouseEvent event) {
        Model m = new Model();
        m.etablirConnection();
        String table = "Diffusions,Films,Temps";
        String conditions ="Diffusions.IdFilm = Films.IdFilm and Temps.IdTemp = Diffusions.IdTemp "
                + "and NomSalle = 'Salle1' ";
        ResultSet res = m.select(table,conditions);
        String sql = "select * from diffusion";
         
           String dateStart;
           String heureStart;
           String heureEnd;
           String titre;
           String id;
           CalendarView calendarView = new CalendarView();
           Stage stage = new Stage();
             
                Calendar salle1 = new Calendar("SALLE 1 "); 
                Calendar holidays = new Calendar("Holidays");
               
               
        try {
            while(res.next()){
                Entry diff = new Entry();
                dateStart = res.getString("DateStart");
                heureStart = res.getString("TimeStart");
                heureEnd = res.getString("TimeEnd");
                titre = res.getString("Titre");
                id=res.getString("IdTemp");
                
                diff.setId(id);
                diff.changeStartDate(LocalDate.parse(dateStart, DateTimeFormatter.ISO_DATE));
                diff.changeStartTime(LocalTime.parse(heureStart, DateTimeFormatter.ISO_TIME));
                diff.changeEndTime(LocalTime.parse(heureEnd,DateTimeFormatter.ISO_TIME));
                diff.setTitle(titre);
                diff.setLocation("Salle 1");
                salle1.addEntry(diff);
                
                //context menu for entry
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //en cas de changement 
               EventHandler  handler = evt -> change((CalendarEvent) evt);
               salle1.addEventHandler(handler);
               
               
              //context menu
             calendarView.setEntryContextMenuCallback( param -> {
                ContextMenu cm = new ContextMenu();
                MenuItem item  = new MenuItem("navalona");
                MenuItem item2  = new MenuItem("navadsfsdlona");
                cm.getItems().addAll(item,item2);
                return  cm;
             });
              calendarView.setOnContextMenuRequested((ContextMenuEvent event1) -> {
                  ContextMenu cm = new ContextMenu();
                MenuItem item  = new MenuItem("navalona");
                MenuItem item2  = new MenuItem("navadsfsdlona");
                cm.getItems().addAll(item,item2);
                cm.show(calendarView,event.getScreenX(),event.getScreenY());
               
        });
             
              
                CalendarSource myCalendarSource = new CalendarSource("My Calendars");
                myCalendarSource.getCalendars().addAll(salle1, holidays);

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
        
    }*/
    
    @FXML
    private void resa(MouseEvent event) {
        String page ="Reservation";
       
        super.openView(page);
    }
    
    

    private void change(CalendarEvent evt) {
           Temps t= new Temps();
        
        String dateStart = evt.getEntry().getStartDate().toString();
        String timeStart =evt.getEntry().getStartTime().toString();
        String timeEnd =evt.getEntry().getEndTime().toString();
        String id =evt.getEntry().getId();
        t.setDateStart(dateStart);t.setTimeEnd(timeEnd);t.setTimeStart(timeStart);t.setIdTemp(id);
        t.changeTime();
        
        //change view 
       
  
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
    @FXML
    private void newpage(ActionEvent event) {  
       super.openView("Dashboard"); 
    }

    @FXML
    private void ajoutFilm(ActionEvent event) {
        super.openView("AjoutFilm");
    }

    @FXML
    private void menuView(ActionEvent event) {
        super.openView("blabla");
    }

    private static class MyEntryContextMenu {

        public MyEntryContextMenu() {
        }
    }

    

    
   

   

}
