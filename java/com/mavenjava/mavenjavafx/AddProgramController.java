/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenjava.mavenjavafx;

import com.mavenjava.Class.Diffusions;
import com.mavenjava.Class.Films;
import com.mavenjava.Class.Places;
import com.mavenjava.Class.Salles;
import com.mavenjava.Class.Tarifs;
import com.mavenjava.Class.Temps;
import com.mavenjava.core.Method;
import static java.lang.Float.parseFloat;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author HERNAVAL
 */
public class AddProgramController extends Method implements Initializable {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static Image img;
    @FXML
    private ComboBox<String> comboFilm;
    private ComboBox<String> comboSalle;
    @FXML
    private TextField idFilm;
    @FXML
    private DatePicker dtStart;
    @FXML
    private TextField time;
    @FXML
    private TextField vip;
    @FXML
    private TextField simple;
    @FXML
    private TextField premium;
    @FXML
    private Label refT;
    @FXML
    private Label alert1;
    @FXML
    private Label alert2;
    @FXML
    private Button validerBtn;
    @FXML
    private ImageView check;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        init();       
        check.setVisible(false);
    }    
    public void init(){
        refT.setText(super.refGen("T-",3));
        alert1.setVisible(false);
        alert2.setVisible(false);
        validerBtn.setDisable(true);
        fillCombo();
    }
    @FXML
    private void setIdFilm(ActionEvent event) {
        String titre = comboFilm.getValue();
        try {
           
            Films f = new Films(titre);
            ResultSet res = f.getSelected();
            if(res.next()){
                idFilm.setText(res.getString("IdFilm"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddProgramController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public boolean checkPlaceWorth(){
        boolean res= false;
        float pVip = parseFloat(vip.getText());
        float pPrem = parseFloat(premium.getText());
        float pSim = parseFloat(simple.getText());
        if( !(pVip > pPrem && pPrem >  pSim) ) {
            res = false;
        }else res = true;
    return res;
    }
    @FXML
    private void regProgram(ActionEvent event) {
        boolean libre = false;    
        String idtemp =refT.getText();
        String nomsalle= comboSalle.getValue();
        String idfilm =idFilm.getText(); 
        String dateStart = dtStart.getValue().toString();
        String timeStart = time.getText();
        String timeEnd = timeStart;
        String[] cat = {"VIP","PREMIUM","SIMPLE"};
        String[] prix = {vip.getText(),premium.getText(),simple.getText()};
        
        if(checkPlaceWorth() == false){
            super.alert("respectez vip > premium > simple");
        }else{
             //configuration temps
       Temps tp = new Temps(idtemp,dateStart,timeStart,timeEnd);
       tp.insert();
       //configuration tarif
       for(int i=0;i<cat.length;i++){
           float px =  parseFloat(prix[i]);
           Tarifs tf = new Tarifs(cat[i],idtemp,px);
           tf.insert();
       }
       
       //configuration Diffusion
        Diffusions diff = new Diffusions(idfilm,idtemp,nomsalle);
        diff.insert();
        }
       
    }
    public void fillCombo(){
        Films f = new Films();
        Salles pl = new Salles();
        ResultSet res = f.getFilms();
        ResultSet res2 = pl.getSalles();
        String item2 = "NomSalle";
        String item = "Titre";
        super.fillCombo(comboSalle, res2, item2, "Salle N°");
        super.fillCombo(comboFilm, res, item, "Titre ");
    }

   
    @FXML
    private void checkDate(ActionEvent event) {
        Image defaultImage  = new Image(getClass().getResourceAsStream("/img/delete_sign_48px.png")); 
        check.setImage(defaultImage);
        check.setVisible(true);
        
        /*RotateTransition rt = new RotateTransition(Duration.seconds(1),check);
            rt.setCycleCount(1);
            rt.setAutoReverse(false);
            rt.setFromAngle(720);
            rt.setToAngle(0);
            rt.play();*/
        try {
            // String date =dtStart.getValue();
            int count=0;
            String salle = comboSalle.getValue();
            String idtemp = refT.getText();
            String dateStart = dtStart.getValue().toString();
            String timeStart = time.getText();
            String timeEnd = timeStart;
            Diffusions diff = new Diffusions(idtemp,salle);
            ResultSet res = diff.checkLibre(dateStart,timeStart);
            while(res.next()){
                count++;
            }
           
            if(count > 0){
                img = new Image(getClass().getResourceAsStream("/img/delete_sign_48px.png"));
                alert1.setVisible(true);
                alert2.setVisible(true);
            }else{
                img = new Image(getClass().getResourceAsStream("/img/checkmark_48px.png"));
                alert1.setVisible(false);
                alert2.setVisible(false);
                validerBtn.setDisable(false);
            }
                
            
        } catch (SQLException ex) {
            Logger.getLogger(AddProgramController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*rt.setOnFinished((e)->{
            check.setImage(img);
        });*/
      
    }

    @FXML
    private void heureValidation(InputMethodEvent event) {
    }

}
