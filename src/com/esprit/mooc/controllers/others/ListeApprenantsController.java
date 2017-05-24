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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author kods
 */
public class ListeApprenantsController implements Initializable {

    
     @FXML 
    public TableView<Utilisateur> tableApprenant;
    
    @FXML 
    private TableColumn<Utilisateur,Integer> idCol;
    @FXML 
    private TableColumn<Utilisateur,String> usernameCol;
    @FXML 
    private TableColumn<Utilisateur,String> emailCol;
  

    
    @FXML
    private Button btnSupprimer;
    
    @FXML
    private TextField rechercheText;
    int id;
    
    private Utilisateur loggedUser;
    
    UtilisateurDAO userDao=new UtilisateurDAO();
    List<Utilisateur> lApprenant=userDao.findUtilisateurByRole("ROLE_APPRENANT");
    
    public ObservableList<Utilisateur> listApprenants=FXCollections.observableArrayList(lApprenant);

    ListeApprenantsController(Utilisateur loggedUser) {
      this.loggedUser=loggedUser;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idCol.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("id"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("usename"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("email"));
     
        tableApprenant.setItems(listApprenants);

        
    }    
    @FXML
    public void selectTable(){
        Utilisateur u = tableApprenant.getSelectionModel().getSelectedItem();

        id = u.getId();
        System.out.println("id"+id);
       btnSupprimer.setVisible(true);

    }
    @FXML
    public void supprimerApprenantAction(){
        System.out.println("clicked");
        userDao.supprimerUtilisateur(id);
        this.refreshtable();
    }
    
    @FXML
    public void rechercherApprenantAction(){
       ObservableList<Utilisateur> list=FXCollections.observableArrayList(userDao.findApprenantByName(rechercheText.getText())); 
       tableApprenant.setItems(list);
    }
    
    
    public void refreshtable(){
        
        ObservableList<Utilisateur> list=FXCollections.observableArrayList(userDao.findUtilisateurByRole("ROLE_APPRENANT")); 
            tableApprenant.setItems(list);
//             tableFormateur.setItems(list);
    }
}
