/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.InvitationFormateurEntrepriseDAO;
import com.esprit.mooc.DAO.PhotoUtilisateurDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.InvitationFormateurEntreprise;
import com.esprit.mooc.Entities.PhotoUtilisateur;
import com.esprit.mooc.Entities.Utilisateur;
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

/**
 * FXML Controller class
 *
 * @author kods
 */
public class PaneApprenantController implements Initializable {
    
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
  

    private MenuAdminController parentAdmin;

    
   Utilisateur loggedUser;

    Utilisateur user;
    UtilisateurDAO aO=new UtilisateurDAO();
    public PaneApprenantController(Utilisateur u) {
        user=u;
        btnUnlock=new Button();
       
    }

   
    
     PaneApprenantController(Utilisateur user, MenuAdminController aThis) {
       parentAdmin=aThis;
        this.user=user;
    }
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         Platform.runLater(() -> {
        labelNom.setText(user.getUsename());
        labelEmail.setText(user.getEmail());
         PhotoUtilisateur photoUtilisateur=new PhotoUtilisateurDAO().findPhotoById(user.getPhoto());
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
                file = new File("C:\\wamp\\www\\MOOCV5\\web\\uploads\\userPhoto\\student.png");
                inputStream = new FileInputStream(file);
                System.out.println("la logo"+photoUtilisateur.getPathPhoto());
                image.setImage(new Image(inputStream));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PaneEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
            }
             }
            
                if(user.getEnbaled()==1){
                    btnLock.setVisible(true);
                    btnUnlock.setVisible(false);
                }
                    else
                    {
                    btnLock.setVisible(false);
                    btnUnlock.setVisible(true);
                    }
                btnProfil.setDisable(true);

        
        btnUnlock.setOnMouseClicked((MouseEvent event)->{

               
                     aO.enableUtilisateur(user.getId());
                try {
                    parentAdmin.listeDisabledApprenantsAction();
                    
                } catch (IOException ex) {
                    Logger.getLogger(PaneApprenantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                   
        
            
        });
        btnLock.setOnMouseClicked((MouseEvent event)->{
               aO.disableUtilisateur(user.getId());
                try {
                    parentAdmin.listeApprenantsAction();
                     parentAdmin.chercherApprenantText.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(PaneApprenantController.class.getName()).log(Level.SEVERE, null, ex);
                }
     
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

}
