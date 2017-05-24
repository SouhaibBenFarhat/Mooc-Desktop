/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.InformationEntrepriseDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.InformationEntreprise;
import com.esprit.mooc.Entities.Utilisateur;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author kods
 */
class ListeDemandeInscriptionEntrepriseController implements Initializable{
    
    
    @FXML
    private TextField telText;
    @FXML
    private TextField abreviationText;
    @FXML
    private TextField matriculeText;
    
    @FXML
    private TextField adresseText;
    
    @FXML
    private TextField raisonInscriptionText;
    @FXML
    private TextField attestationText;
    @FXML
    private TextField specialiteText;
     @FXML
    private TextField siteWebText;
    @FXML
    private TextArea descriptionText;
    
    @FXML
    private TextField PaysText;
    @FXML
    private TextField rechercheText;
    @FXML
    private TextField TypeText;
    @FXML
    private TextField emailText;
    @FXML
    private DatePicker dateCreationPicker;

        @FXML 
    public TableView<Utilisateur> tableEntreprise;

    
    @FXML 
    private TableColumn<Utilisateur,Integer> idCol;
    @FXML 
    private TableColumn<Utilisateur,String> usernameCol;
//    @FXML 
//    private TableColumn<Utilisateur,String> emailCol;
     //@FXML 
   // private TableColumn<InformationFormateur,String> specialiteCol;
    
   
   
  
    @FXML
    private Button btnAccepter;
    @FXML
    private Button btnRefuser;
    int id;
    
    
    private Utilisateur loggedUtilisateur;
    UtilisateurDAO userDao=new UtilisateurDAO();
    List<Utilisateur> lEntreprise=userDao.findlockedEntreprises();

    
    public ObservableList<Utilisateur> listEntreprises=FXCollections.observableArrayList(lEntreprise);

    ListeDemandeInscriptionEntrepriseController(Utilisateur loggedUser) {
        this.loggedUtilisateur=loggedUser;
     }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idCol.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("id"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("usename"));
        //emailCol.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("email"));
       // specialiteCol.setCellValueFactory(new PropertyValueFactory<InformationFormateur,String>("specialite"));
       // tableApprenant.setItems(listApprenants);
        tableEntreprise.setItems(listEntreprises);
     
    }    
    @FXML
    public void selectTable(){
        Utilisateur u2 = tableEntreprise.getSelectionModel().getSelectedItem();
        id = u2.getId();
        InformationEntrepriseDAO informationEntrepriseDAO=new InformationEntrepriseDAO();
        InformationEntreprise infoEnteprise=informationEntrepriseDAO.findInfoEntrepriseByIdEntreprise(id);
       emailText.setText(u2.getEmail());
       telText.setText(infoEnteprise.getNumTel());
       abreviationText.setText(infoEnteprise.getAbreviation());
       matriculeText.setText(infoEnteprise.getMatriculeFiscal());
    
        adresseText.setText(infoEnteprise.getAdresse());
    
        raisonInscriptionText.setText(infoEnteprise.getRaisonInscription());
        attestationText.setText(infoEnteprise.getFilename());
        specialiteText.setText(infoEnteprise.getSpecialite());
        siteWebText.setText(infoEnteprise.getSiteWeb());
        descriptionText.setText(infoEnteprise.getDescription());
    
        PaysText.setText(infoEnteprise.getNationnalite());
        TypeText.setText(infoEnteprise.getType());

    }
    @FXML
    public void refuserAction(){
        userDao.supprimerEntreprise(id);
        emailText.setText("");
        telText.setText("");
        abreviationText.setText("");
        matriculeText.setText("");
    
        adresseText.setText("");
    
        raisonInscriptionText.setText("");
        attestationText.setText("");
        specialiteText.setText("");
        siteWebText.setText("");
        descriptionText.setText("");
        PaysText.setText("");
        TypeText.setText("");
        this.refreshtable();
    }
    
     @FXML
    public void accepterAction(){
        Utilisateur user=new Utilisateur();
        user.setId(id);
        userDao.accepterUtilisateur(user);
        emailText.setText("");
        telText.setText("");
        abreviationText.setText("");
        matriculeText.setText("");
    
        adresseText.setText("");
    
        raisonInscriptionText.setText("");
        attestationText.setText("");
        specialiteText.setText("");
        siteWebText.setText("");
        descriptionText.setText("");
        PaysText.setText("");
        TypeText.setText("");
        this.refreshtable();
    }
    public void refreshtable(){
        ObservableList<Utilisateur> list=FXCollections.observableArrayList(userDao.findlockedEntreprises());
                tableEntreprise.setItems(list);
    }
}
