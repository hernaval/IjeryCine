/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenjava.core;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author HERNAVAL
 */
public class Method {
    
    
    public void openView(String page){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/"+page+".fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(Method.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public  void generate(String text,int width,int height,String filepath) throws WriterException, IOException{
        QRCodeWriter qr  = new QRCodeWriter();
        BitMatrix bitMatrix = qr.encode(text, BarcodeFormat.QR_CODE, width, height);
        Path path = FileSystems.getDefault().getPath(filepath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path); 
    }
   public void fillCombo(ComboBox c,ResultSet res,String item,String value){
     
        try {
            ObservableList<String> list  = FXCollections.observableArrayList();
            while(res.next()){
                list.add(res.getString(item));
            }
            c.setValue(value);
            c.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(Method.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public String refGen(String code,int length){
      String ref = code+generateRandomString(length);
      return ref;
   }
   
   public void alert(String message){
       Alert al = new Alert(Alert.AlertType.INFORMATION);
       al.setContentText(message);
       al.show();
   }
   
   public void confirm(String message){
       Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
    // ... user chose OK
            } else {
    // ... user chose CANCEL or closed the dialog
            }
   }
   
   
   
  public void showContextMenu(ContextMenu cm,TableView ob,MouseEvent e){
      cm.show(ob,e.getScreenX(),e.getScreenY());
  }
   
    public static String generateRandomString(int length) {
         String str  = "ABCDE";
         String number = "0123456789";
         SecureRandom random = new SecureRandom();

         String DATA_FOR_RANDOM_STRING = str + number ;
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

			// 0-62 (exclusive), random returns 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            // debug
            System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);

            sb.append(rndChar);

        }

        return sb.toString();

    }
   
   
}
