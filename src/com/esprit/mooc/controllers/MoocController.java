/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Utilisateur;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kods
 */
public class MoocController implements Initializable {
     @FXML
    private Button apprenantBtn;
      @FXML
    private Button formateurBtn;
       @FXML
    private Button entrepriseBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      Stage stage=new Stage();
        Parent root;
         try {
             root = FXMLLoader.load(getClass().getResource("/com/esprit/mooc/moocfx_1/MenuFXML.fxml"));
         
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
             Logger.getLogger(MoocController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }    
 
   
    
    @FXML
    public void apprenantBtnAction(ActionEvent event) throws IOException{
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/mooc/moocfx_1/inscriptionApprenant.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
    @FXML
    public void formateurBtnAction(ActionEvent event) throws IOException{
            Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/mooc/moocfx_1/inscriptionFormateur.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
    
     @FXML
    public void entrepriseBtnAction(ActionEvent event) throws IOException{
            Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/mooc/moocfx_1/inscriptionEntreprise.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
    
             
}

