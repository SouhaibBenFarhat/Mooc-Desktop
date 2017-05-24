/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.Entities.Utilisateur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author kods
 */
class ListeCoursAValiderController implements Initializable{
    Utilisateur loggedUtilisateur;

    public ListeCoursAValiderController(Utilisateur loggedUtilisateur) {
        this.loggedUtilisateur = loggedUtilisateur;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }
    
}
