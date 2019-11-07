/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mavenjava.Class.Films;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
    private TextField idfilm;
    @FXML
    private TextField durre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addFilm(MouseEvent event) {
        String t = titre.getText();
        String d = durre.getText();
        String g = genre.getText();
        String id = idfilm.getText();
        Films f = new Films();
        f.setTitre(t);f.setGenre(g);f.setIdFilm(id);f.setDuree(d);
        f.insert();
    }
    
    
}
