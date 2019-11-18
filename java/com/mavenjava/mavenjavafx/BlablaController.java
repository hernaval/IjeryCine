/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenjava.mavenjavafx;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.mavenjava.Class.Diffusions;
import com.mavenjava.Class.Films;
import com.mavenjava.Class.Reservations;
import com.mavenjava.Class.Temps;
import com.mavenjava.core.Method;
import com.mavenjava.core.Model;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HERNAVAL
 */
public class BlablaController extends Method implements Initializable {

    public static String getIdTemp;
    public static String dateStart;
    public static String timeStart;
    
    public static String numTicket;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button ticketing;
    @FXML
    private Button movie;
    @FXML
    private Button calendar;
    @FXML
    private PieChart piechart;
    @FXML
    private Pane chartContainer;
    @FXML
    private Button home;
    @FXML
    private Button save;
    @FXML
    private Label nbrFilm;
    @FXML
    private Label titreEnCours;
    @FXML
    private Button logout;
    @FXML
    private LineChart<?, ?> linechart;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initComponent();
    }  
    
    public void initComponent(){
        Image icon = new Image(getClass().getResourceAsStream("/img/icons8_calendar_32.png"));
        Image icon2 = new Image(getClass().getResourceAsStream("/img/icons8_tv_32.png"));
        Image icon3 = new Image(getClass().getResourceAsStream("/img/icons8_check_32.png"));
        Image icon4 = new Image(getClass().getResourceAsStream("/img/icons8_home_32.png"));
        Image icon5 = new Image(getClass().getResourceAsStream("/img/save_32px.png"));
        Image icon6 = new Image(getClass().getResourceAsStream("/img/export_32px.png"));
        
        ticketing.setGraphic(new ImageView(icon3));
        movie.setGraphic(new ImageView(icon2));
        calendar.setGraphic(new ImageView(icon));
        home.setGraphic(new ImageView(icon4));
        save.setGraphic(new ImageView(icon5));
        logout.setGraphic(new ImageView(icon6));
        //viewTicket.setGraphic(new ImageView(icon3));
        drawChart();
        info();
        attendance();
    }
    
    public void info(){
        try {
            Films f = new Films();
            LocalDate date = LocalDate.now();
            String param = date.toString();
            Diffusions diff = new Diffusions();
            ResultSet currentLive = diff.getLive(param);
            ResultSet res = f.count();
            
            while(res.next()) {
                nbrFilm.setText(res.getString("total"));
            }
            while(currentLive.next()){
                String titre = currentLive.getString("Titre");
                titreEnCours.setText(titre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlablaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void attendance(){
        
            Reservations resa = new Reservations();
            ResultSet res = resa.getAttendance();
            XYChart.Series s = new XYChart.Series<>();
          
            s.getData().add(new XYChart.Data<>("Titanic",10));
            s.getData().add(new XYChart.Data<>("Jojopil",15));
            s.getData().add(new XYChart.Data<>("Raosy Jamba",13));
            s.getData().add(new XYChart.Data<>("Avengers",21));
            
            
            linechart.getData().add(s);
        
        
    }
    
    public void drawChart(){
        ObservableList<PieChart.Data> details = FXCollections.observableArrayList();
        Diffusions diff = new Diffusions();
        
        String[] nomSalle={"Salle1","Salle2","Salle3","Salle4"};
        
        for(int i=0;i<4;i++){
            try {
                diff.setNomSalle(nomSalle[i]);
                ResultSet res = diff.getRepart();
                
                if(res.next()){
                    int value = res.getInt("total");
                    details.addAll(new PieChart.Data(nomSalle[i],value));
                }
            } catch (SQLException ex) {
                Logger.getLogger(BlablaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
           
        piechart = new PieChart();
        piechart.setData(details);
      // piechart.setTitle("Test");
        piechart.setLegendSide(Side.LEFT);

        chartContainer.getChildren().add(piechart);
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
    

    //Films, TicketList, 
   

    @FXML
    private void ticketList(ActionEvent event) {
        loadPage("TicketList");
        
    }

    @FXML
    private void filmList(ActionEvent event) {
        loadPage("Films");
    }
    
    @FXML
    private void home(ActionEvent event) {
        bp.setCenter(ap);
    }

    @FXML
    private void CalendarView(ActionEvent event) {
        try {
            CalendarView calendarView = new CalendarView(); 
            Calendar salle1 = new Calendar("SALLE 1 ");
            Calendar salle2 = new Calendar("SALLE 2 ");
            Calendar salle3 = new Calendar("SALLE 3 ");
            Calendar salle4 = new Calendar("SALLE 4 ");
            
            Diffusions diff = new Diffusions();
            //les variables 
            String dateStart;
            String timeStart;
            String timeEnd;
            String idTemp;
            String titre;
            String lieu; //nom salle
            String[] salles = {"salle1","salle2","salle3","salle4"};
            
            //en cas de changement
            EventHandler<CalendarEvent> handler = evt -> foo(evt);
            salle1.addEventHandler(handler);
            
            
            for(int k=0; k<salles.length;k++){
                ResultSet res = diff.getProgram(salles[k]);
                    while(res.next()){
                    Entry program = new Entry();
                    idTemp = res.getString("IdTemp");
                    dateStart = res.getString("DateStart");
                    timeStart = res.getString("TimeStart");
                    timeEnd = res.getString("TimeEnd");
                    titre = res.getString("Titre");
                    lieu = res.getString("NomSalle");
                    
                    program.setId(idTemp);
                    program.setTitle(titre);
                    program.setLocation(lieu);
                    program.changeStartDate(LocalDate.parse(dateStart, DateTimeFormatter.ISO_DATE));
                    program.changeStartTime(LocalTime.parse(timeStart, DateTimeFormatter.ISO_LOCAL_TIME));
                    program.changeEndTime(LocalTime.parse(timeStart, DateTimeFormatter.ISO_LOCAL_TIME));
                    
                     calendarView.setEntryContextMenuCallback( param -> {
                ContextMenu cm = new ContextMenu();
                MenuItem item  = new MenuItem("voir");
                MenuItem item2  = new MenuItem("checking");
                cm.getItems().addAll(item,item2);
                
                item.setOnAction((ActionEvent evt) ->{
                    //super.alert(param.getEntry().getId());
                    SelectDateController.idtemp = param.getEntry().getId();
                    
                    super.openView("Reservations");
                });
                
                 item2.setOnAction((ActionEvent evt) ->{
                    //super.alert(param.getEntry().getId());
                    SelectDateController.idtemp = param.getEntry().getId();
                   String today = LocalDate.now().toString();
                   String currentDate = param.getEntry().getStartDate().toString();
                   if( currentDate.equals(today) ){
                       super.openView("Checking");
                       //super.alert(currentDate);
                      
                   }else{
                       super.alert("checking impossible avant et après  le jour J");
                   }
                   
                  
                });
                
                return  cm;
             });
                    
                    
                    switch(k){
                        case 0 : 
                            salle1.addEntries(program);
                            salle1.setStyle(Style.STYLE1);
                            break;
                        case 1 : 
                            salle2.addEntries(program);
                            salle2.setStyle(Style.STYLE2);
                            break;
                        case 2 :
                            salle3.addEntries(program);
                            salle3.setStyle(Style.STYLE3);
                            break;
                        case 3 :
                            salle4.addEntries(program);   
                            salle4.setStyle(Style.STYLE4);
                    }
                    
                    
                    }
            }
             
           // en cas de changement 
            
            
       
        CalendarSource myCalendarSource = new CalendarSource("LES SALLES ");
                myCalendarSource.getCalendars().addAll(salle1,salle2,salle3,salle4 );
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

               
                bp.setCenter(calendarView);    
            
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(BlablaController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
    }

    

   

    private EventHandler<CalendarEvent> foo(CalendarEvent evt) {
        getIdTemp = evt.getEntry().getId();
        dateStart = evt.getEntry().getStartDate().toString();
        timeStart = evt.getEntry().getStartTime().toString();
        return null;
    }

    //enregistrez les changements
     @FXML
    private void saveChanges(ActionEvent e) {
       Temps t = new Temps(getIdTemp,dateStart,timeStart,timeStart);
       t.changeTime();
       
       super.alert("Saves");
   }

    @FXML
    private void addProgram(ActionEvent event) {
        super.openView("NewProgram");
        
    }

    @FXML
    private void ajoutFilm(ActionEvent event) {
        super.openView("AjoutFilm");
    }

    @FXML
    private void logout(ActionEvent event) {
        Stage stage = (Stage) logout.getScene().getWindow();
                        stage.close();
        super.openView("Login");
        
    }

    


}
