/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers.others;

import com.esprit.mooc.Entities.Utilisateur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author kods
 */
class ListeCoursController implements Initializable{

    private Utilisateur loggeUtilisateur;
    ListeCoursController(Utilisateur loggedUser) {
       this.loggeUtilisateur=loggedUser; 
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
