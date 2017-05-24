/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.Entities.Utilisateur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author kods
 */
public class PaneDemandeInsEntrepriseController implements Initializable {
    
    @FXML
    private Label labelNom;
    @FXML
    private Label labelEmail;
    @FXML
    private Pane paneEntreprise;

    Utilisateur entreprise;
    public PaneDemandeInsEntrepriseController(Utilisateur e) {
        entreprise=e;
       
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         Platform.runLater(() -> {
        labelNom.setText(entreprise.getUsename());
        labelEmail.setText(entreprise.getEmail());
         });
    }    

    public Label getLabelNom() {
        return labelNom;
    }

    public void setLabelNom(Label labelNom) {
        this.labelNom = labelNom;
    }

    public Label getLabelEmail() {
        return labelEmail;
    }

    public void setLabelEmail(Label labelEmail) {
        this.labelEmail = labelEmail;
    }

}
