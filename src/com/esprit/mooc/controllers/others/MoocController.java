/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers.others;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author kods
 */
public class MoocController implements Initializable {
  
    Stage stage;
    Parent root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       stage=new Stage();
       stage.initStyle(StageStyle.UNDECORATED);
        
         try {
             root = FXMLLoader.load(getClass().getResource("/com/esprit/mooc/moocfx_1/MenuFXML.fxml"));
         
        Scene scene = new Scene(root);
        stage.setScene(scene);
       
        stage.show();
        } catch (IOException ex) {
             Logger.getLogger(MoocController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }    
 

             
}

