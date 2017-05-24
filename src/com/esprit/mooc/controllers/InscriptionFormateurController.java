/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.controllers.others.ChapitreFXMLController;
import com.esprit.mooc.DAO.DisciplineDao;
import com.esprit.mooc.DAO.InformationFormateurDAO;
import com.esprit.mooc.DAO.PaysDao;
import com.esprit.mooc.DAO.PhotoUtilisateurDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.InformationFormateur;
import com.esprit.mooc.Entities.PhotoUtilisateur;
import com.esprit.mooc.Entities.Utilisateur;
import com.esprit.mooc.ressources.config2;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import ressources.FadeInLeftTransition;
import ressources.FadeInLeftTransition1;
import ressources.FadeInRightTransition;
import java.nio.file.*;
import java.time.format.DateTimeFormatter;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author kods
 */
public class InscriptionFormateurController implements Initializable {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final String URL_PATTERN
            ="^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?"
            + "=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    
    
    public static  final String NUM_TEl="(\\\\+[0-9][0-9][0-9]( [0-9][0-9])+)|([0-9]+)";
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
    private Label label2;
      @FXML
    private Label label3;
    @FXML
    private Button btnChoisir;
    @FXML
    private Button btnInscription;
     @FXML
    private Button btnNextStep;
    
    @FXML
    private TextField usernameText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField passwordText;

    @FXML
    private Text lblUserLogin;
    @FXML
    private Text lblUsername;
    @FXML
    private Text lblPassword;

    @FXML
    private Text lblEmail;

     @FXML
    private Text lblSpecialite; 
     
     @FXML
    private Pane inscri1Pane;
     
     @FXML
    private Pane inscri2Pane;
    //partie Formateur
    @FXML
    private TextField cvText;
     @FXML
    private Text lblCv;


    @FXML
   ComboBox<String> comboSpecialite;   
        
      @FXML
    private Text lblGooglePlus; 
      
      @FXML
    private Text lblSiteWeb; 
      
      @FXML
    private Text lblBiographie; 
      
      @FXML
    private Text lblMiniBiographie; 
      
      @FXML
    private Text lblAPropos; 
      
     @FXML
    private Text lblPhoto; 
 
     @FXML
    private TextField googlePlusText;
     
     @FXML
    private TextField siteWebText;
    
     @FXML
    private TextField imageText;     
    
     @FXML
    private TextArea biographieText;
    
     @FXML
    private TextArea miniBiographieText;
     
     @FXML
    private TextArea aProposText;
     String username,pwd;
     @FXML
     private Button btnChoisirPhoto;
     
     DisciplineDao disciplineDao=new DisciplineDao();
     List<String> lsDiscipline=disciplineDao.findAllDiscipline();
     public ObservableList<String> list=FXCollections.observableArrayList(lsDiscipline);
    
     PaysDao paysDao=new PaysDao();
     List<String> lsPays=paysDao.findAll();
     public ObservableList<String> listPays=FXCollections.observableArrayList(lsPays);
    UtilisateurDAO aO=new UtilisateurDAO();
    @FXML
    private Button btnLogout;
    
    TrayNotification tray;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            //partie utilisateur
            new FadeInLeftTransition1(lblUserLogin).play();
            new FadeInLeftTransition1(lblPassword).play();
            new FadeInLeftTransition1(lblUsername).play();
            new FadeInLeftTransition1(lblEmail).play();
            new FadeInLeftTransition1(usernameText).play();
            new FadeInLeftTransition1(passwordText).play();
            new FadeInLeftTransition1(emailText).play();
            new FadeInLeftTransition1(btnInscription).play();
            new FadeInLeftTransition1(btnNextStep).play();
            //partie formateur
            comboSpecialite.setItems(list);
           
              new FadeInRightTransition(inscri1Pane).play();
           
            new FadeInRightTransition(inscri2Pane).play();
            new FadeInLeftTransition1(lblCv).play();
            new FadeInLeftTransition1(lblSpecialite).play();
            new FadeInLeftTransition(btnChoisir).play();
            new FadeInLeftTransition1(cvText).play();         
            
            new FadeInLeftTransition1(lblGooglePlus).play();
            new FadeInLeftTransition1(lblBiographie).play();
            new FadeInLeftTransition1(lblMiniBiographie).play();
            new FadeInLeftTransition1(lblSiteWeb).play();
            new FadeInLeftTransition1(lblAPropos).play();
            new FadeInLeftTransition1(googlePlusText).play();
            new FadeInLeftTransition1(siteWebText).play();
            new FadeInLeftTransition1(biographieText).play();
            new FadeInLeftTransition1(miniBiographieText).play();
            new FadeInLeftTransition1(aProposText).play();
            new FadeInLeftTransition1(lblPhoto).play();
            new FadeInLeftTransition1(imageText).play();
            new FadeInLeftTransition1(btnChoisirPhoto).play();
            
            
           
            //inscri2Pane.setVisible(false);
            label.setTextFill(Color.web("#FFFFFF"));
             comboSpecialite.setItems(list);

           new FadeInLeftTransition(comboSpecialite).play();
 
        });
         
    }
 public void reinitialise(){
      cvText.setText("");
      googlePlusText.setText("");
       miniBiographieText.setText("");
       siteWebText.setText("");
       biographieText.setText("");
       aProposText.setText("");
       comboSpecialite.setValue("");
       
                   
    
    }
 
    

    @FXML
    private void btnChoisirAction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        fc.getExtensionFilters().addAll(new ExtensionFilter("PDF FILES", "*.pdf"));
        if (selectedFile != null) {

            cvText.setText(selectedFile.getAbsolutePath());

        } else {
            label.setText("erreur dans l upload du CV ");
            System.out.println("erreur dans l upload du CV ");
        }
    }

   

    public boolean mailValid(String mail) {

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(mail);
        return matcher.matches();
    }

   public boolean urlValid(String url){
        pattern = Pattern.compile(URL_PATTERN);
        matcher = pattern.matcher(url);
        return matcher.matches();
   }

    public boolean NumtelValid(String numTel){
        pattern = Pattern.compile(NUM_TEl);
       matcher = pattern.matcher(numTel);
        return matcher.matches();
   }
    
    public String uploadCV(TextField cvText){
        String source=cvText.getText();
        Path path = Paths.get(source);
        String target_dir="C:\\wamp\\www\\MOOCV5\\web\\uploads\\cv\\cv_"+username+".pdf";
        Path target=Paths.get(target_dir);
        try {
        Files.copy(path, target, StandardCopyOption.REPLACE_EXISTING);
        return target_dir;
        } catch (IOException ex) {
        Logger.getLogger(ChapitreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
}
        return null;
    }
    
   
 
    public void inscriptionFormateurPartie1Action(ActionEvent event) {

         if (usernameText.getText().equals("") || emailText.getText().equals("") || !mailValid(emailText.getText()) || passwordText.getText().equals("")|| imageText.getText().equals("")) {
            System.out.println("Empty fiels or wrong email");
             tray = new TrayNotification("Erreur d'ajout "," il y a des champs vide ou bien l'email n'est pas valide ou bien photo non valide ", Notifications.ERROR);
                    tray.showAndDismiss(Duration.seconds(5));
                    
            //label2.setText("erreur il y a des champs vide ou bien l'email n'est pas valide");
        } else 
         if(aO.MailExistant(emailText.getText()) || aO.UserNameExistant(usernameText.getText())){
           tray = new TrayNotification("Erreur d'ajout "," Email ou nom d'utilisateur existant ", Notifications.ERROR);
                    tray.showAndDismiss(Duration.seconds(5));
               
             
            // label2.setText("Email ou nom d'utilisateur existant ");   
         }

             boolean uploadPhoto=uploadPhoto(imageText);
         
            if(uploadPhoto){
                        PhotoUtilisateur photo=new PhotoUtilisateur();
                        photo.setPathPhoto(usernameText.getText()+".png");
                        PhotoUtilisateurDAO photoUtilisateurDAO=new PhotoUtilisateurDAO();
                        photoUtilisateurDAO.ajouterPhoto(photo);
                   
            try {
                PhotoUtilisateur pu=new PhotoUtilisateurDAO().findPhotoByPath(photo.getPathPhoto());
               
                Utilisateur user = new Utilisateur();
            
                UtilisateurDAO userDao = new UtilisateurDAO();
                user.setUsename(usernameText.getText());
                user.setUsenameCanonical(usernameText.getText());
                user.setEmail(emailText.getText());
                user.setEmailCanonical(emailText.getText());
                user.setPassword(passwordText.getText());
                System.out.println("l id de la photo "+pu.getIdPhoto());
                user.setPhoto(pu.getIdPhoto());
                user.setRole("a:1:{i:0;s:14:\"ROLE_FORMATEUR\";}");
                user.setLocked(1);
                userDao.ajouterUtilisateur(user);
                username=usernameText.getText();
                pwd=passwordText.getText();
                inscri1Pane.setVisible(false);
                lblUserLogin.setVisible(false);
                inscri2Pane.setVisible(true);
                label2.setVisible(false);
                System.out.println(" Inscri partie 1 done ");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("U didn't do it man");
                //label2.setText("Erreur d'authentification ");
                 tray = new TrayNotification("Erreur ","  Erreur d'ajout", Notifications.ERROR);
                    tray.showAndDismiss(Duration.seconds(5));
               
            }
            }
       
    }
        
    
    
    
        @FXML
        private void btnChoisirPhotoAction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(" image FILES", "*.png"));
        if (selectedFile != null) {

            imageText.setText(selectedFile.getAbsolutePath());

        } else {
            tray = new TrayNotification("Erreur ","upload de la photo a echoué", Notifications.ERROR);
                    tray.showAndDismiss(Duration.seconds(5));
                    
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
    public void inscriptionFormateurPartie2Action(ActionEvent event) {
            try {
                //,String username,String pwd
                
                InformationFormateur informationFormateur=new InformationFormateur();
                UtilisateurDAO userDao = new UtilisateurDAO();
                Utilisateur user=new Utilisateur();
                user=userDao.findUtilisateurByLoginMdp(username, pwd);
                String cvPath=uploadCV(cvText);
                informationFormateur.setCv(cvPath);
                informationFormateur.setFilename("cv_"+username+".pdf");
                if(googlePlusText.getText().equals("") ||
                   miniBiographieText.getText().equals("")|| 
                   siteWebText.getText().equals("")||
                   biographieText.getText().equals("")||
                   aProposText.getText().equals("") || cvText.getText().equals(""))
                {
                    //label3.setVisible(true);
                    tray = new TrayNotification("Erreur d'ajout ","Il y des informations manquantes", Notifications.ERROR);
                    tray.showAndDismiss(Duration.seconds(5));
                    //label3.setText(" des Informations maquantes !!");
                     System.out.println("des Informations maquantes !!");
                }
                else if(!urlValid(siteWebText.getText())){
              tray = new TrayNotification("Erreur d'ajout "," Site Web non valide", Notifications.ERROR);
                    tray.showAndDismiss(Duration.seconds(5));
}else{
                informationFormateur.setGooglePlus(googlePlusText.getText());
                informationFormateur.setMiniBiographie(miniBiographieText.getText());
                informationFormateur.setSiteWeb(siteWebText.getText());
                informationFormateur.setSpecialite(comboSpecialite.getValue());
                informationFormateur.setBiographie(biographieText.getText());
                informationFormateur.setaPropos(aProposText.getText());
                informationFormateur.setIdFormateur(user.getId());

                InformationFormateurDAO informationFormateurDAO=new InformationFormateurDAO();
                informationFormateurDAO.ajouterInformationFormateur(informationFormateur);
    
              //reinitialise();
                config2 config = new config2();
                config.newStage2(stage, btnInscription, "/com/esprit/mooc/moocfx_1/authentification.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
                  // label.setText("\n Vous allez etre notifié dés l'activation de votre compte !!");//notification
                  tray = new TrayNotification("Vous etes bien ajoutée ", "Bienvenu à World Elephent  \n votre Compte va etre bloqué pour un moment", Notifications.SUCCESS);
                  tray.showAndDismiss(Duration.seconds(5));
                
                System.out.println(" U did it man");
                }
               
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("U didn't do it man");
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
