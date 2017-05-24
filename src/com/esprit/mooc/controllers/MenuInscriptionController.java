/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.esprit.mooc.ressources.config2;

/**
 * FXML Controller class
 *
 * @author Herudi
 */
public class MenuInscriptionController implements Initializable {
    @FXML
    private Button close;
    @FXML
    private Button maximize;
    @FXML
    private Button minimize;
    @FXML
    private Button resize;
    @FXML
    private Button fullscreen;
     @FXML
    private Button btnLogout;
    @FXML
    private Label title;
    Stage stage;
    Rectangle2D rec2;
    Double w,h;

    @FXML
    Button btnApprenant;
    @FXML
    Button btnFormateur;
    @FXML
    Button btnEntreprise;
    
    config2 con = new config2();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        Platform.runLater(() -> {

            btnApprenant.setOnMouseClicked((MouseEvent event)->{
        ((Node)(event.getSource())).getScene().getWindow().hide();     
         Stage stage2=new Stage();
        FXMLLoader loader;
        loader=new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/inscriptionApprenant.fxml"));
        InscriptionApprenantController inscriptionApprenantController=new InscriptionApprenantController();
        loader.setController(inscriptionApprenantController);
        Parent root;
        try {
            root = loader.load();
            Scene scene = new Scene(root);
        stage2.initStyle(StageStyle.UNDECORATED);
        stage2.setScene(scene);
        stage2.show();
        } catch (IOException ex) {
        ex.printStackTrace();
        }
                
        });
            
             btnFormateur.setOnMouseClicked((MouseEvent event)->{
        
                    ((Node)(event.getSource())).getScene().getWindow().hide();     
         Stage stage2=new Stage();
        FXMLLoader loader;
        loader=new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/inscriptionFormateur.fxml"));
        InscriptionFormateurController inscriptionFormateurController=new InscriptionFormateurController();
        loader.setController(inscriptionFormateurController);
        Parent root;
        try {
            root = loader.load();
            Scene scene = new Scene(root);
        stage2.initStyle(StageStyle.UNDECORATED);
        stage2.setScene(scene);
        stage2.show();
        } catch (IOException ex) {
           ex.printStackTrace();
        }
          
                
        });
             
              btnEntreprise.setOnMouseClicked((MouseEvent event)->{
           ((Node)(event.getSource())).getScene().getWindow().hide();     
         Stage stage2=new Stage();
        FXMLLoader loader;
        loader=new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/inscriptionEntreprise.fxml"));
        InscriptionEntrepriseController inscriptionEntrepriseController=new InscriptionEntrepriseController();
        loader.setController(inscriptionEntrepriseController);
        Parent root;
        try {
            root = loader.load();
            Scene scene = new Scene(root);
        stage2.initStyle(StageStyle.UNDECORATED);
        stage2.setScene(scene);
        stage2.show();
        } catch (IOException ex) {
           ex.printStackTrace();
        }
          
                
        });
            
        });
    }    
    

    @FXML
    private void menuminimize(ActionEvent event) {
        stage = (Stage) minimize.getScene().getWindow();
        if (stage.isMaximized()) {
            w = rec2.getWidth();
            h = rec2.getHeight();
            stage.setMaximized(false);
            stage.setHeight(h);
            stage.setWidth(w);
            stage.centerOnScreen();
            Platform.runLater(() -> {
                stage.setIconified(true);
            });
        }else{
            stage.setIconified(true);
        }        
    }



    @FXML
    private void menuClose(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
    
    
    @FXML
    private void menuLogout(ActionEvent event) throws IOException {
       
    config2 config = new config2();
    config.newStage2(stage, btnLogout, "/com/esprit/mooc/moocfx_1/authentification.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
    }

}
    

