/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.InvitationEntrepriseFormateurDAO;
import com.esprit.mooc.DAO.InvitationFormateurEntrepriseDAO;
import com.esprit.mooc.DAO.PhotoUtilisateurDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.InvitationEntrepriseFormateur;
import com.esprit.mooc.Entities.InvitationFormateurEntreprise;
import com.esprit.mooc.Entities.PhotoUtilisateur;
import com.esprit.mooc.Entities.Utilisateur;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author kods
 */
public class PaneEntrepriseController implements Initializable {
    
    @FXML
    private Label labelNom;
    @FXML
    private Label labelEmail;
    @FXML
    private ImageView image;
    @FXML
    private Pane paneUser;
    @FXML
    private Button btnProfil;
    @FXML
    private Button btnLock;
    @FXML
    private Button btnUnlock;
      @FXML
    private Button btnAccept;
    @FXML
    private Button btnReject;
    @FXML
    private Button btnAcceptEntreprise;
    @FXML
    private Button btnRejectEntreprise;
    @FXML
    public Button btnAddEntreprise = new Button();
    @FXML
    private Button btnAddFormateur;
    private MenuFormateurController parentFormateur;
    private MenuAdminController parentAdmin;
  
   Utilisateur loggedUser;
    Utilisateur entreprise;
    UtilisateurDAO aO=new UtilisateurDAO();
    public PaneEntrepriseController(Utilisateur u) {
        entreprise=u;
        btnUnlock=new Button();
       
    }

    PaneEntrepriseController(Utilisateur user, MenuFormateurController aThis) {
       parentFormateur=aThis;
        this.entreprise=user;
    }
    
     PaneEntrepriseController(Utilisateur user, MenuAdminController aThis) {
       parentAdmin=aThis;
        this.entreprise=user;
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
             PhotoUtilisateur photoUtilisateur=new PhotoUtilisateurDAO().findPhotoById(entreprise.getPhoto());
             File file ;
             FileInputStream inputStream = null;
             if(photoUtilisateur.getPathPhoto() != null){
         file = new File("C:\\wamp\\www\\MOOCV5\\web\\uploads\\userPhoto\\" + photoUtilisateur.getPathPhoto());
                System.out.println("la logo"+photoUtilisateur.getPathPhoto());
                
             try {
                 inputStream = new FileInputStream(file);
                 image.setImage(new Image(inputStream));
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(PaneEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
             }
             }
             else{
            try {
                file = new File("C:\\wamp\\www\\MOOCV5\\web\\uploads\\userPhoto\\entreprise.png");
                inputStream = new FileInputStream(file);
                System.out.println("la logo"+photoUtilisateur.getPathPhoto());
                image.setImage(new Image(inputStream));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PaneEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
            }
             }
       
        
      
        if(!loggedUser.getRole().contains("FORMATEUR")){
            

                    if(entreprise.getEnbaled()==1 && entreprise.getLocked()==0){
                        btnLock.setVisible(true);
                        btnUnlock.setVisible(false);
                    }
            
                    else
                        if(entreprise.getLocked()==1){
                            btnUnlock.setVisible(false);
                            btnLock.setVisible(false);
                            btnAccept.setVisible(true);
                            btnReject.setVisible(true);
                        }
                        
                        else
                            {
                            btnLock.setVisible(false);
                            btnUnlock.setVisible(true);
                            }
                }
        //à verifier
        else{
        
         boolean  invi=false;
          InvitationEntrepriseFormateur invitationEntrepriseFormateur;
          invi=new InvitationFormateurEntrepriseDAO().findInvitationByIdFormateur(loggedUser.getId());
    if(invi==true){
        
        btnAddEntreprise.setVisible(false);
          
         btnLock.setVisible(false);
         btnUnlock.setVisible(false);
         
         
           invitationEntrepriseFormateur=new InvitationEntrepriseFormateurDAO().findInvitationByFormateurIdEntrepriseId(loggedUser.getId(), entreprise.getId());
           
            if(invitationEntrepriseFormateur.getId()==0 )            
             {    
                btnAcceptEntreprise.setVisible(false);
                btnRejectEntreprise.setVisible(false);
              }
               else {
                        System.out.println("en attente ");
                         btnAcceptEntreprise.setVisible(true);
                         btnRejectEntreprise.setVisible(true);
                    }
                    
                    if(loggedUser.getIdEntrepriseUtilisateur()!=0 ){
                           
                                btnAcceptEntreprise.setVisible(false);
                                btnRejectEntreprise.setVisible(false);
                                
         
                    }
    }
    else{
        btnLock.setVisible(false);
        btnUnlock.setVisible(false);
        
         invitationEntrepriseFormateur=new InvitationEntrepriseFormateurDAO().findInvitationByFormateurIdEntrepriseId(loggedUser.getId(), entreprise.getId());
           
            if(invitationEntrepriseFormateur.getId()==0 )            
             {    
                btnAcceptEntreprise.setVisible(false);
                btnRejectEntreprise.setVisible(false);
                btnAddEntreprise.setVisible(true);
              }
               else {
                        System.out.println("en attente ");
                         btnAcceptEntreprise.setVisible(true);
                         btnRejectEntreprise.setVisible(true);
                         btnAddEntreprise.setVisible(false);
                    }
                    
                    if(loggedUser.getIdEntrepriseUtilisateur()!=0 ){
                           
                                btnAcceptEntreprise.setVisible(false);
                                btnRejectEntreprise.setVisible(false);
                                 btnAddEntreprise.setVisible(false);
                                
         
                    }
        
        
    }
        
        }
        
                     btnProfil.setOnMouseClicked((MouseEvent event)->{
        Stage stage=new Stage();
        FXMLLoader loader;
        loader=new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/InformationUser.fxml"));
        UserProfilController userProfilController=new UserProfilController(entreprise);
        userProfilController.setLoggedUser(loggedUser);
        loader.setController(userProfilController);
        Parent root;
        try {
            root = loader.load();
            Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PaneEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        });
        
        btnUnlock.setOnMouseClicked((MouseEvent event)->{

            if(entreprise.getEnbaled()==0){
                try {
                    aO.enableUtilisateur(entreprise.getId());
                    parentAdmin.listeDisabledEntreprisesAction();
                } catch (IOException ex) {
                    Logger.getLogger(PaneEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
            
        });
        btnLock.setOnMouseClicked((MouseEvent event)->{
              aO.disableUtilisateur(entreprise.getId());
                try {
                    parentAdmin.listeEntreprisesAction();
                } catch (IOException ex) {
                    Logger.getLogger(PaneEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            
              
                
        
        });
        
        btnAccept.setOnMouseClicked((MouseEvent event)->{
            try {
                aO.accepterUtilisateur(entreprise);
                parentAdmin.listeDemandesAction();
            } catch (IOException ex) {
                Logger.getLogger(PaneEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        });
           
           btnReject.setOnMouseClicked((MouseEvent event)->{
            try {
                aO.supprimerEntreprise(entreprise.getId());
                UtilisateurDAO userD=new UtilisateurDAO();
                userD.supprimerUtilisateur(entreprise.getId());
                parentAdmin.listeDemandesAction();
            } catch (IOException ex) {
                Logger.getLogger(PaneEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        });
           
           btnAddEntreprise.setOnMouseClicked((MouseEvent event)->{
                   
                     InvitationFormateurEntreprise ife=new InvitationFormateurEntreprise();
                     ife.setIdEntreprise(entreprise.getId());
                     ife.setIdFormateur(loggedUser.getId());
                     InvitationFormateurEntrepriseDAO aO=new InvitationFormateurEntrepriseDAO();
                     aO.envoyerInvitationFormateurEntreprise(ife);
                     
                     TrayNotification tray = new TrayNotification("Success","invitation envoyée", Notifications.SUCCESS);
                     tray.showAndDismiss(Duration.seconds(5));
                     parentFormateur.listeEntreprisesAction();
                  
           
           });
           
           btnAcceptEntreprise.setOnMouseClicked((MouseEvent event)->{
           
           InvitationEntrepriseFormateurDAO aO=new InvitationEntrepriseFormateurDAO();

           aO.accepterInvitation(entreprise.getId(),loggedUser.getId());
           aO.supprimerInvitation(entreprise.getId(),loggedUser.getId());  
           parentFormateur.listeEntreprisesAction();
           
           });
           
           btnRejectEntreprise.setOnMouseClicked((MouseEvent event)->{
            
           InvitationEntrepriseFormateurDAO aO=new InvitationEntrepriseFormateurDAO();
           aO.supprimerInvitation(entreprise.getId(),loggedUser.getId());  
           parentFormateur.listeEntreprisesAction();
           });
         });
        
        
                 
    }   
    
    
    public Label getLabelNom() {
        return labelNom;
    }

    public void setLabelNom(Label labelNom) {
        this.labelNom = labelNom;
    }

    public Label getLblPrenom() {
        return labelEmail;
    }

    public void setLblPrenom(Label lblPrenom) {
        this.labelEmail = lblPrenom;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public Pane getPaneUser() {
        return paneUser;
    }

    public void setPaneUser(Pane paneUser) {
        this.paneUser = paneUser;
    }

    public Label getLabelEmail() {
        return labelEmail;
    }

    public void setLabelEmail(Label labelEmail) {
        this.labelEmail = labelEmail;
    }

    public Button getBtnProfil() {
        return btnProfil;
    }

    public void setBtnProfil(Button btnProfil) {
        this.btnProfil = btnProfil;
    }

    public Button getBtnLock() {
        return btnLock;
    }

    public void setBtnLock(Button btnLock) {
        this.btnLock = btnLock;
    }

    public Button getBtnUnlock() {
        return btnUnlock;
    }

    public void setBtnUnlock(Button btnUnlock) {
        this.btnUnlock = btnUnlock;
    }

    public Utilisateur getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Utilisateur loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Button getBtnAddEntreprise() {
        return btnAddEntreprise;
    }

    public void setBtnAddEntreprise(Button btnAddEntreprise) {
        this.btnAddEntreprise = btnAddEntreprise;
    }

  
    
    
}
