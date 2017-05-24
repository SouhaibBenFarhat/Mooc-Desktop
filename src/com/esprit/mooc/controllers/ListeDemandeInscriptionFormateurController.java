/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Utilisateur;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author kods
 */
class ListeDemandeInscriptionFormateurController implements Initializable{
        @FXML 
    public TableView<Utilisateur> tableFormateur;

    
    @FXML 
    private TableColumn<Utilisateur,Integer> idCol;
    @FXML 
    private TableColumn<Utilisateur,String> usernameCol;
    @FXML 
    private TableColumn<Utilisateur,String> emailCol;
 
    @FXML
    private Button btnRefuser;
    @FXML
    private Button btnAccepter;
    @FXML
    private TextField rechercheText;
    
    private Utilisateur loggedUser;
    int id;
   
     UtilisateurDAO userDao=new UtilisateurDAO();
    List<Utilisateur> lFormateur=userDao.findDisabledFormateurs();   
    public ObservableList<Utilisateur> listFormateurs=FXCollections.observableArrayList(lFormateur);

    public ListeDemandeInscriptionFormateurController(Utilisateur loggedUser) {
        this.loggedUser=loggedUser;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         idCol.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("id"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("usename"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("email"));
        tableFormateur.setItems(listFormateurs);
      
        }
    
      @FXML
    public void selectTable(){

        Utilisateur u2 = tableFormateur.getSelectionModel().getSelectedItem();
        id = u2.getId();

    }
    public void refreshtable(){
        
        ObservableList<Utilisateur> list=FXCollections.observableArrayList(userDao.findDisabledFormateurs()); 

             tableFormateur.setItems(list);
    }
    
      @FXML
    public void refuserAction(){
        userDao.supprimerFormateur(id);
       
        this.refreshtable();
    }
    
     @FXML
    public void accepterAction(){
        Utilisateur user=new Utilisateur();
        user.setId(id);
        userDao.accepterUtilisateur(user);
       
        this.refreshtable();
    }
    
}
