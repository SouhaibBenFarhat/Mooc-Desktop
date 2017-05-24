/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers.others;

import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.InformationFormateur;
import com.esprit.mooc.Entities.Utilisateur;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    @FXML 
    public TableView<Utilisateur> tableAutreFormateurs;
//    @FXML 
//    public TableView<Utilisateur> tableEntreprise;
    
    @FXML 
    private TableColumn<Utilisateur,Integer> idCol;
    @FXML 
    private TableColumn<Utilisateur,String> usernameCol;
    @FXML 
    private TableColumn<Utilisateur,String> emailCol;

    @FXML
    private Button btnDesactiver;
    @FXML
    private TextField rechercheText;
    
    private Utilisateur loggedUser;
    int id;
   
    UtilisateurDAO userDao=new UtilisateurDAO();
    List<Utilisateur> lFormateur=userDao.findUtilisateurByRole("ROLE_FORMATEUR");
   
    public ObservableList<Utilisateur> listFormateurs=FXCollections.observableArrayList(lFormateur);
  
    ListeFormateursController(Utilisateur loggedUser) {
    
        this.loggedUser=loggedUser;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          Platform.runLater(() -> {
        idCol.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("id"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("usename"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("email"));
        
        if(loggedUser.getRole().contains("ADMIN")){
             tableFormateur.setItems(listFormateurs);
        }
        else if(loggedUser.getRole().contains("ENTREPRISE")){
    List<Utilisateur> lMesFormateurs=userDao.findFormateursByEntreprise(loggedUser.getId());
   // List<Utilisateur> lAutresFormateurs=userDao.findAutreFormateurs(loggedUser.getId());
    ObservableList<Utilisateur> listMesFormateurs=FXCollections.observableArrayList(lMesFormateurs);
    //ObservableList<Utilisateur> listAutreFormateurs=FXCollections.observableArrayList(lAutresFormateurs);
    tableFormateur.setItems(listMesFormateurs);
   // tableAutreFormateurs.setItems(listAutreFormateurs);
        }
        else if(loggedUser.getRole().contains("COMITE")){
              tableFormateur.setItems(listFormateurs);
        }
        
      
          });
          
    }    
    @FXML
    public void selectTable(){

        Utilisateur u2 = tableFormateur.getSelectionModel().getSelectedItem();
        id = u2.getId();
      
       btnDesactiver.setVisible(true);

    }
    @FXML
    public void desactiverFormateurAction(){
        System.out.println("clicked");
        userDao.disableUtilisateur(id);
        this.refreshtable();
    }
    public void refreshtable(){
        
        ObservableList<Utilisateur> list=FXCollections.observableArrayList(userDao.findUtilisateurByRole("ROLE_FORMATEUR")); 

             tableFormateur.setItems(list);
    }
    
    @FXML
    public void rechercherFormateurAction(){
       ObservableList<Utilisateur> list=FXCollections.observableArrayList(userDao.findFormateurByName(rechercheText.getText())); 
       tableFormateur.setItems(list);
    }
}
