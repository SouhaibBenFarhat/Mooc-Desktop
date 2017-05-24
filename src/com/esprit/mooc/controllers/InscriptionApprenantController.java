/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.DisciplineDao;
import com.esprit.mooc.DAO.PaysDao;
import com.esprit.mooc.DAO.PhotoUtilisateurDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.PhotoUtilisateur;
import com.esprit.mooc.Entities.Utilisateur;
import com.esprit.mooc.controllers.others.ChapitreFXMLController;
import com.esprit.mooc.ressources.config2;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ressources.FadeInLeftTransition1;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.util.Duration;

/**
 *
 * @author kods
 */
public class InscriptionApprenantController implements Initializable {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    
        @FXML
    private Button close;
    @FXML
    private Button maximize;
    @FXML
    private Button minimize;

    @FXML
    private Label title;
    Stage stage;
    Rectangle2D rec2;
    Double w,h;
    //partie commune
    @FXML
    private Label label;
    @FXML
    private Button btnInscription;
    
    @FXML
    private TextField usernameText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField passwordText;

    @FXML
    private TextField imageText;
    @FXML
    private Text lblUserLogin;
    @FXML
    private Text lblUsername;
    @FXML
    private Text lblPassword;

    @FXML
    private Text lblEmail;
     
        @FXML
    private Text lblPhoto;
        
     String username,pwd;
     @FXML
     Button btnLogout;
    
     @FXML
     Button btnChoisir;


  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
        Platform.runLater(() -> {
             
            
            new FadeInLeftTransition1(lblUserLogin).play();
            new FadeInLeftTransition1(lblPassword).play();
            new FadeInLeftTransition1(lblUsername).play();
            new FadeInLeftTransition1(lblEmail).play();
            new FadeInLeftTransition1(usernameText).play();
            new FadeInLeftTransition1(passwordText).play();
            new FadeInLeftTransition1(emailText).play();
            new FadeInLeftTransition1(btnInscription).play();               
            new FadeInLeftTransition1(imageText).play();    
            new FadeInLeftTransition1(lblPhoto).play();    
            //inscri2Pane.setVisible(false);
            label.setTextFill(Color.web("#FFFFFF"));
 
        });
         
    }
        TrayNotification tray;
    @FXML
    private void inscriptionApprenantAction(ActionEvent event) {
        if (usernameText.getText().equals("") || emailText.getText().equals("") || !mailValid(emailText.getText()) || passwordText.getText().equals("") || imageText.getText().equals("")) {
            System.out.println("erreur il y a des champs vide ou bien l'email n'est pas valide");
            //label.setText("erreur il y a des champs vide ou bien l'email n'est pas valide");
             tray = new TrayNotification("Erreur d'ajout ","Il y des informations manquantes ou  \n bien l'email n'est pas valide", Notifications.ERROR);
                    tray.showAndDismiss(Duration.seconds(5));
                    
        } else {
            boolean uploadPhoto=uploadPhoto(imageText);
            if(uploadPhoto){
                        PhotoUtilisateur photo=new PhotoUtilisateur();
                        photo.setPathPhoto(usernameText.getText()+".png");
                        PhotoUtilisateurDAO photoUtilisateurDAO=new PhotoUtilisateurDAO();
                        photoUtilisateurDAO.ajouterPhoto(photo);
                   
            try {
                PhotoUtilisateur pu=new PhotoUtilisateur();
                if(imageText.getText().endsWith(".png")){
                 pu=new PhotoUtilisateurDAO().findPhotoByPath(usernameText.getText()+".png");
                }
                else if(imageText.getText().endsWith(".jpg")){
                 pu=new PhotoUtilisateurDAO().findPhotoByPath(usernameText.getText()+".jpg");
                }
                Utilisateur user = new Utilisateur();
                UtilisateurDAO userDao = new UtilisateurDAO();
                if(userDao.MailExistant(emailText.getText())){
                    label.setText("Email existant Déja ");
                }
                else{
                user.setUsename(usernameText.getText());
                user.setUsenameCanonical(usernameText.getText());
                user.setEmail(emailText.getText());
                user.setEmailCanonical(emailText.getText());
                user.setPassword(passwordText.getText());
                user.setPhoto(pu.getIdPhoto());
                user.setRole("a:1:{i:0;s:14:ROLE_APPRENANT;}");
                userDao.ajouterUtilisateur(user);
                 ((Node) (event.getSource())).getScene().getWindow().hide();
                Stage stage = new Stage();
                 FXMLLoader loader=new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/MenuApprenant.fxml"));
                    MenuApprenantController menuApprenantController=new MenuApprenantController(user);
                     loader.setController(menuApprenantController);
                    Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                reinitialise();
                stage.show();
                 tray = new TrayNotification("Vous etes bien ajoutée " , "Bienvenu à World Elephent", Notifications.SUCCESS);
                 tray.showAndDismiss(Duration.seconds(5));
                
                System.out.println(" U did it man");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("U didn't do it man");
            }

        } else {
                tray = new TrayNotification("Erreur ", "upload de la photo n'est pas effectué", Notifications.ERROR);
                 tray.showAndDismiss(Duration.seconds(5));
                
                        //label.setText("erreur dans l'upload du photo");
                
                }
        
    }
    }
     @FXML
    private void btnChoisirAction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(" image FILES", "*.png"));
        if (selectedFile != null) {

            imageText.setText(selectedFile.getAbsolutePath());

        } else {
            label.setText("erreur dans l upload du photo ");
            System.out.println("erreur dans l upload du photo ");
        }
    }
    public boolean uploadPhoto(TextField imageText){
        String source=imageText.getText();
        Path path = Paths.get(source);
        String target_dir="C:\\wamp\\www\\MOOCV5\\web\\uploads\\userPhoto\\"+usernameText.getText();
        Path target=Paths.get(target_dir);
        try {
        Files.copy(path, target, StandardCopyOption.REPLACE_EXISTING);
        return true;
        } catch (IOException ex) {
        Logger.getLogger(ChapitreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
}
        return false;
    }
 
    public boolean mailValid(String mail) {

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(mail);
        return matcher.matches();
    }

 
      public void reinitialise(){
      usernameText.setText("");
     emailText.setText("");
    passwordText.setText("");
    }

    public TextField getUsernameText() {
        return usernameText;
    }

    public void setUsernameText(TextField usernameText) {
        this.usernameText = usernameText;
    }

    public TextField getEmailText() {
        return emailText;
    }

    public void setEmailText(TextField emailText) {
        this.emailText = emailText;
    }

    public TextField getPasswordText() {
        return passwordText;
    }

    public void setPasswordText(TextField passwordText) {
        this.passwordText = passwordText;
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
    private void menuClose(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
  @FXML
    private void menuLogout(ActionEvent event) throws IOException {
    config2 config = new config2();
    config.newStage2(stage, btnLogout, "/com/esprit/mooc/moocfx_1/authentification.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
    }
      
}

 