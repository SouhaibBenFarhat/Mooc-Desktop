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
public class PaneAutreFormateursController implements Initializable {
    
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
    private Button btnAddFormateur;
    
    private MenuAdminController parentAdmin;
    private MenuMembreComiteController parentMembreComite;
      private MenuEntrepriseController parentEntreprise;
   Utilisateur loggedUser;
   Alert a=new Alert(Alert.AlertType.INFORMATION);

    Utilisateur user;
    UtilisateurDAO aO=new UtilisateurDAO();
    public PaneAutreFormateursController(Utilisateur u) {
        user=u;
    }

    
     PaneAutreFormateursController(Utilisateur user, MenuAdminController aThis) {
       parentAdmin=aThis;
        this.user=user;
    }
    
      PaneAutreFormateursController(Utilisateur user, MenuMembreComiteController aThis) {
       parentMembreComite=aThis;
        this.user=user;
    }
        PaneAutreFormateursController(Utilisateur user, MenuEntrepriseController aThis) {
       parentEntreprise=aThis;
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
         file = new File("C:\\wamp\\www\\MOOCV5\\web\\uploads\\userPhoto\\formateur.png");// + photoUtilisateur.getPathPhoto());
                System.out.println("la logo"+photoUtilisateur.getPathPhoto());
                
             try {
                 inputStream = new FileInputStream(file);
                 image.setImage(new Image(inputStream));
                 System.out.println("image");
             } catch (FileNotFoundException ex) {
                 ex.printStackTrace();
                 //Logger.getLogger(PaneEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
             }
             }
             else{
            try {
                file = new File("C:\\wamp\\www\\MOOCV5\\web\\uploads\\userPhoto\\formateur.png");
                inputStream = new FileInputStream(file);
                System.out.println("le logo "+photoUtilisateur.getPathPhoto());
                image.setImage(new Image(inputStream));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PaneEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
            }
             }
        
           boolean  invitExiste=false;   
        invitExiste=new InvitationEntrepriseFormateurDAO().findInvitByFormateurIdEntrepriseId(user.getId(), loggedUser.getId());
        if(invitExiste ==false){
            
         btnAddFormateur.setVisible(true);
        }
         else{
            
         btnAddFormateur.setVisible(false);
        }
                      
          
                   
         
         
                     btnProfil.setOnMouseClicked((MouseEvent event)->{
        Stage stage=new Stage();
        FXMLLoader loader;
        loader=new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/InformationUser.fxml"));
        UserProfilController userProfilController=new UserProfilController(user);
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
            Logger.getLogger(PaneAutreFormateursController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        });
        
       
        
           btnAddFormateur.setOnMouseClicked((MouseEvent event)->{
               InvitationEntrepriseFormateur entrepriseFormateur=new InvitationEntrepriseFormateur();
               entrepriseFormateur.setIdEntreprise(loggedUser.getId());
               entrepriseFormateur.setIdFormateur(user.getId());
               InvitationEntrepriseFormateurDAO aO=new InvitationEntrepriseFormateurDAO();
               aO.envoyerInvitationEntrepriseFormateur(entrepriseFormateur);
             try {
                 parentEntreprise.listeFormateursAction();
             } catch (IOException ex) {
                 Logger.getLogger(PaneAutreFormateursController.class.getName()).log(Level.SEVERE, null, ex);
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

    public Label getLabelEmail() {
        return labelEmail;
    }

    public void setLabelEmail(Label labelEmail) {
        this.labelEmail = labelEmail;
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

    public Button getBtnProfil() {
        return btnProfil;
    }

    public void setBtnProfil(Button btnProfil) {
        this.btnProfil = btnProfil;
    }

    public Button getBtnAddFormateur() {
        return btnAddFormateur;
    }

    public void setBtnAddFormateur(Button btnAddFormateur) {
        this.btnAddFormateur = btnAddFormateur;
    }

    public Utilisateur getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Utilisateur loggedUser) {
        this.loggedUser = loggedUser;
    }
    
  

}
