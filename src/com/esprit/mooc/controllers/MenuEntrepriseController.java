/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.InvitationEntrepriseFormateurDAO;
import com.esprit.mooc.DAO.InvitationFormateurEntrepriseDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.InvitationEntrepriseFormateur;
import com.esprit.mooc.Entities.InvitationFormateurEntreprise;
import com.esprit.mooc.Entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.esprit.mooc.ressources.config2;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import sun.plugin.javascript.navig.Anchor;

/**
 * FXML Controller class
 *
 * @author Herudi
 */
public class MenuEntrepriseController implements Initializable {
    @FXML
    private Button close;
    @FXML
    private Button maximize;
    @FXML
    private Button minimize;
    @FXML
    private Button fullscreen;
    @FXML
    private Label title;
    Stage stage;
    Rectangle2D rec2;
    Double w,h;
    @FXML
    public Pane paneData;
    config2 con = new config2();
    @FXML
    private Button btnLogout;
    
   private Utilisateur loggedUser;
     FXMLLoader loader;
     UtilisateurDAO userDao;
    
     Button btnDemandeInvitationFormateurs;
     @FXML
     Button btnMesFormateurs;
    @FXML
    private Label ApprenantName;
       @FXML
 private Button btnProfil; 
    @FXML
private Button btnFormateurs;
    TextFlow tf;
    AnchorPane anch;
   @FXML
    Label menuTitle = new Label();
    MenuEntrepriseController(Utilisateur user) {
        loggedUser=user;
        
    }



    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rec2 = Screen.getPrimary().getVisualBounds(); 
        w = 0.1;
        h = 0.1;
        Platform.runLater(() -> {
            tf=new TextFlow();
            tf.setPrefWidth(1090);
            stage = (Stage) maximize.getScene().getWindow();
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
            maximize.getStyleClass().add("decoration-button-restore");
       
            paneData.getChildren().clear();
             loader=new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/MonProfil.fxml"));
            MonProfilController profilEntrepriseController=new MonProfilController(loggedUser,this);
            loader.setController(profilEntrepriseController);
            con.loadPane(paneData, loader);
        });
    }    
     
    final EventHandler<MouseEvent> paneHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(final MouseEvent event) {
            menuTitle.setText("");
        }
    };
        

    final EventHandler<MouseEvent> myHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(final MouseEvent event) {
            Button x = (Button) event.getSource();
            if (x.getId().equals(btnProfil.getId())) {
                menuTitle.setText("Mon Profil ");
            } else if (x.getId().equals(btnMesFormateurs.getId())) {
                menuTitle.setText("Mes Formateurs");
             
            } else if (x.getId().equals(btnFormateurs.getId())) {
                menuTitle.setText("Autres Formateurs");
          
            } else if (x.getId().equals(btnDemandeInvitationFormateurs.getId())) {
                menuTitle.setText("Mes Invitations ");

            } else if (x.getId().equals(btnLogout.getId())) {
                menuTitle.setText("Deconnexion");
   
            }  else {
                menuTitle.setText("");
            }
        }
    };
    @FXML
    private void menuMaximized(ActionEvent event) {
        stage = (Stage) maximize.getScene().getWindow();
        if (stage.isMaximized()) {
            if (w == rec2.getWidth() && h == rec2.getHeight()) {
                stage.setMaximized(false);
                stage.setHeight(600);
                stage.setWidth(800);
                stage.centerOnScreen();
                maximize.getStyleClass().remove("decoration-button-restore");
      
            }else{
                stage.setMaximized(false);
                maximize.getStyleClass().remove("decoration-button-restore");
          
            }
            
        }else{
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
            maximize.getStyleClass().add("decoration-button-restore");
        }
    }

    @FXML
    private void menuminimize(ActionEvent event) {
        stage = (Stage) minimize.getScene().getWindow();
        if (stage.isMaximized()) {
            w = rec2.getWidth();
            h = rec2.getHeight();
            stage.setMaximized(false);
            stage.setHeight(h);
            stage.setWidth(w);
            stage.centerOnScreen();
            Platform.runLater(() -> {
                stage.setIconified(true);
            });
        }else{
            stage.setIconified(true);
        }        
    }

    @FXML
    private void menuResize(ActionEvent event) {
    }

    @FXML
    private void menufullscreen(ActionEvent event) {
        stage = (Stage) fullscreen.getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        }else{
            stage.setFullScreen(true);
        }
    }

    @FXML
    private void menuClose(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

 @FXML
    public void monProfilAction(){
        paneData.getChildren().clear();
       loader=new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/MonProfil.fxml"));
         MonProfilController profilFormateurController2=new MonProfilController(loggedUser,this);
         loader.setController(profilFormateurController2);
            con.loadPane(paneData, loader); 
             
    }
    
    
      
      @FXML
    public void listeFormateursAction() throws IOException {
         tf.getChildren().clear();
         paneData.getChildren().clear();
     userDao=new UtilisateurDAO();
   List<Utilisateur> lFormateurs=userDao.findAutreFormateurs(loggedUser.getId());
   
           for (Utilisateur user : lFormateurs) {

      
         System.out.println("l id ");
                System.out.println("l id "+user.getId());
                 loader=new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneAutreFormateurs.fxml"));
                PaneAutreFormateursController paneController=new PaneAutreFormateursController(user,this);
                paneController.setLoggedUser(loggedUser);
                loader.setController(paneController);
                try {
                    anch=loader.load();          
                } catch (IOException ex) {
                    Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tf.getChildren().add(anch);
            
    }
             paneData.getChildren().add(tf);
           

    //} 
}
    
    @FXML
     void listeInvitationFormateursAction(){

    List<InvitationFormateurEntreprise> list=new InvitationFormateurEntrepriseDAO().findInvitationFormateurEntreprise(loggedUser.getId());
     tf.getChildren().clear();
         paneData.getChildren().clear();
         UtilisateurDAO userDao=new UtilisateurDAO();
         for (InvitationFormateurEntreprise ife : list) {
             System.out.println("listeInvitationFormateursAction");
          Utilisateur formateur=userDao.findFormateurById(ife.getIdFormateur());
          if(formateur.getIdEntrepriseUtilisateur()==0){
            loader=new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneFormateur.fxml"));
                PaneFormateurController paneController=new PaneFormateurController(formateur,this);
                paneController.setLoggedUser(loggedUser);
                loader.setController(paneController);
                try {
                    anch=loader.load();          
                } catch (IOException ex) {
                    Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tf.getChildren().add(anch);
            }
         }
               //paneData.getContent().se
             paneData.getChildren().addAll(tf);
            //  chercherFormateurText.setVisible(true);

        }
         
     
     
    @FXML
    public void listeMesFormateursAction(){
         List<Utilisateur> list=new UtilisateurDAO().findFormateursByEntreprise(loggedUser.getId());
     tf.getChildren().clear();
     paneData.getChildren().clear();

     
            for (Utilisateur user : list) {
                System.out.println("formateur:"+user.getUsename());
                loader=new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneMesFormateurs.fxml"));
                PaneMesFormateursController paneController=new PaneMesFormateursController(user,this);
                paneController.setLoggedUser(loggedUser);
                loader.setController(paneController);
                try {
                    anch=loader.load();          
                } catch (IOException ex) {
                    Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tf.getChildren().add(anch);
            }
               //paneData.getContent().se
             paneData.getChildren().addAll(tf);
            //  chercherFormateurText.setVisible(true);

    
    }
    
    
    
    @FXML
    private void menuLogout(ActionEvent event) throws IOException {
    config2 config = new config2();
    config.newStage2(stage, btnLogout, "/com/esprit/mooc/moocfx_1/authentification.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
    }
}
    

