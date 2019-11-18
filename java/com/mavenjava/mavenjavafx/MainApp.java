package com.mavenjava.mavenjavafx;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    
        @Override
        public void start(Stage stage) throws Exception {
             Parent root = FXMLLoader.load(getClass().getResource("/fxml/blabla.fxml"));
          /* connect();
           String sql = "select * from diffusion";
          ResultSet res = stat.executeQuery(sql);
           String dateStart;
           String heureStart;
           String heureEnd;
           String titre;
           CalendarView calendarView = new CalendarView();
             
                Calendar birthdays = new Calendar("Birthdays"); 
                Calendar holidays = new Calendar("Holidays");
                
               
                while(res.next()){
                    Entry annif = new Entry();
                    dateStart = res.getString("DateDiff");
                    heureStart = res.getString("HeureDiff");
                    titre = res.getString("Titre");
                    DateTimeFormatter dt =  DateTimeFormatter.ofPattern("HH:mm");
                    annif.changeStartDate(LocalDate.parse(dateStart, DateTimeFormatter.ISO_DATE));
                    annif.changeStartTime(LocalTime.parse(heureStart, DateTimeFormatter.ISO_TIME));
                    annif.changeEndTime(LocalTime.of(10, 20));
                    annif.setTitle(titre);
                    birthdays.addEntry(annif);
                }
               
                /*annif.changeStartDate(LocalDate.of(2019, Month.OCTOBER, 25));
                annif.changeEndDate(LocalDate.of(2019,Month.OCTOBER,26));
               
               EventHandler<CalendarEvent> handler = evt -> foo(evt);
               birthdays.addEventHandler(handler);
              
                birthdays.setStyle(Style.STYLE1); 
                holidays.setStyle(Style.STYLE2);
                
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
                updateTimeThread.start();*/

                Scene scene = new Scene(root);
                stage.setTitle("Menu");
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
        }
        
        public static void main(String[] args) {
                launch(args);
        }

   
}