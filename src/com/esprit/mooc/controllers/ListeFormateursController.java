/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.InformationFormateur;
import com.esprit.mooc.Entities.Utilisateur;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author kods
 */
public class ListeFormateursController implements Initializable {

//    
//     @FXML 
//    public TableView<Utilisateur> tableApprenant;
    @FXML 
    public TableView<Utilisateur> tableFormateur;
//    @FXML 
//    public TableView<Utilisateur> tableEntreprise;
    
    @FXML 
    private TableColumn<Utilisateur,Integer> idCol;
    @FXML 
    private TableColumn<Utilisateur,String> usernameCol;
    @FXML 
    private TableColumn<Utilisateur,String> emailCol;

    @FXML
    private Button btnSupprimer;
    int id;
    
    
    
    UtilisateurDAO userDao=new UtilisateurDAO();
   // List<Utilisateur> lFormateur=userDao.findAllUtilisateur();
    List<Utilisateur> lFormateur=userDao.findUtilisateurByRole("ROLE_FORMATEUR");
//    List<Utilisateur> lApprenant=userDao.findUtilisateurByRole("ROLE_APPRENANT");
    
    public ObservableList<Utilisateur> listFormateurs=FXCollections.observableArrayList(lFormateur);
//    public ObservableList<Utilisateur> listApprenants=FXCollections.observableArrayList(lApprenant);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idCol.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("id"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("usename"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("email"));
       // specialiteCol.setCellValueFactory(new PropertyValueFactory<InformationFormateur,String>("specialite"));
       // tableApprenant.setItems(listApprenants);
        tableFormateur.setItems(listFormateurs);

    }    
    @FXML
    public void selectTable(){
//        Utilisateur u = tableApprenant.getSelectionModel().getSelectedItem();
        Utilisateur u2 = tableFormateur.getSelectionModel().getSelectedItem();
        id = u2.getId();
      
       btnSupprimer.setVisible(true);

    }
    @FXML
    public void supprimerFormateurAction(){
        System.out.println("clicked");
//        userDao.supprimerFormateur(id);
        this.refreshtable();
    }
    public void refreshtable(){
        
        ObservableList<Utilisateur> list=FXCollections.observableArrayList(userDao.findUtilisateurByRole("ROLE_FORMATEUR")); 
//            tableApprenant.setItems(list);
             tableFormateur.setItems(list);
    }
}
