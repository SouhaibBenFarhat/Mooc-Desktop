/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.controllers.others.ChapitreFXMLController;
import com.esprit.mooc.DAO.DisciplineDao;
import com.esprit.mooc.DAO.InformationEntrepriseDAO;
import com.esprit.mooc.DAO.PaysDao;
import com.esprit.mooc.DAO.PhotoUtilisateurDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.InformationEntreprise;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ressources.FadeInLeftTransition;
import ressources.FadeInLeftTransition1;
import ressources.FadeInRightTransition;
import java.nio.file.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author kods
 */
public class InscriptionEntrepriseController implements Initializable {

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

     //partie Entreprise
     
     @FXML
    private Text lblNumeroTel; 
    
    @FXML
    private Text lblAbreviation; 
    
    @FXML
    private Text lblPays; 
    
    @FXML
    private Text lblMatricule; 
    
    @FXML
    private Text lblType; 
    
    @FXML
    private Text lblAdresse;
    
    @FXML
    private Text lblRaison;
    
    @FXML
    private Text lblAttestation;
    
    @FXML
    private Text lblDescription;
    
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
    private ComboBox<String> comboPays;
    @FXML
    private ComboBox<String> comboType;

     @FXML
    private TextField imageText; 
       @FXML
    private Text lblPhoto; 
      
     @FXML
     private Button btnChoisirPhoto;  
    
     String username,pwd;

    
     ObservableList<String> typeList=FXCollections.observableArrayList("Etatique","Privée");
     DisciplineDao disciplineDao=new DisciplineDao();
     List<String> lsDiscipline=disciplineDao.findAllDiscipline();
     public ObservableList<String> list=FXCollections.observableArrayList(lsDiscipline);
    
     PaysDao paysDao=new PaysDao();
     List<String> lsPays=paysDao.findAll();
     public ObservableList<String> listPays=FXCollections.observableArrayList(lsPays);
    @FXML
     private Button btnLogout;
    private TrayNotification tray;
    
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
             comboPays.setItems(listPays);
             comboType.setItems(typeList);
              new FadeInRightTransition(inscri1Pane).play();
              
               
              
            new FadeInLeftTransition(comboPays).play();
            new FadeInRightTransition(inscri2Pane).play();
            new FadeInLeftTransition1(lblSpecialite).play();
            new FadeInLeftTransition(btnChoisir).play();
            //inscri2Pane.setVisible(false);
            label.setTextFill(Color.web("#FFFFFF"));     
            new FadeInLeftTransition(lblNumeroTel).play();
            new FadeInLeftTransition(lblAbreviation).play();
            new FadeInLeftTransition(lblPays).play();
            new FadeInLeftTransition(lblMatricule).play();
            new FadeInLeftTransition(lblType).play();
            new FadeInLeftTransition(lblAdresse).play();
            new FadeInLeftTransition(lblRaison).play();
            new FadeInLeftTransition(lblAttestation).play();
            new FadeInLeftTransition(lblDescription).play();
            new FadeInLeftTransition(lblPhoto).play();
            new FadeInLeftTransition(imageText).play();
            new FadeInLeftTransition(btnChoisirPhoto).play();
            
             new FadeInLeftTransition(telText).play();
            new FadeInLeftTransition(abreviationText).play();
            new FadeInLeftTransition(matriculeText).play();
            new FadeInLeftTransition(adresseText).play();
            new FadeInLeftTransition(raisonInscriptionText).play();
            new FadeInLeftTransition(attestationText).play();
            new FadeInLeftTransition(specialiteText).play();
            new FadeInLeftTransition(descriptionText).play();
           new FadeInLeftTransition(siteWebText).play();
            new FadeInLeftTransition(comboType).play();
            
         
 
        });
         
    }



    @FXML
    public void btnChoisirAttestationAction(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        fc.getExtensionFilters().addAll(new ExtensionFilter("PDF FILES", "*.pdf"));
        if (selectedFile != null) {

            attestationText.setText(selectedFile.getAbsolutePath());

        } else {
            System.out.println("erreur dans btnChoisirAttestationAction");
        }
    }

    public boolean mailValid(String mail) {

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(mail);
        return matcher.matches();
    }


  public String uploadAttestation(TextField attestationText){
        String source=attestationText.getText();
        Path path = Paths.get(source);
        String target_dir="C:\\wamp\\www\\MOOCV5\\web\\uploads\\attestation\\attestation_"+username+".pdf";
        Path target=Paths.get(target_dir);
        try {
        Files.copy(path, target, StandardCopyOption.REPLACE_EXISTING);
        return target_dir;
        } catch (IOException ex) {
        Logger.getLogger(ChapitreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
}
        return null;
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
   
    
    
     public void inscriptionEntreprisePartie1Action(ActionEvent event) {
        UtilisateurDAO aO=new UtilisateurDAO();
          if (usernameText.getText().equals("") ||
              emailText.getText().equals("") || 
             !mailValid(emailText.getText()) || 
             passwordText.getText().equals("")||
                 imageText.getText().equals("")) {
            System.out.println("erreur il y a des champs vide ou bien l'email n'est pas valide");
            //label.setText("erreur il y a des champs vide ou bien l'email n'est pas valide");
             tray = new TrayNotification("Erreur d'ajout "," il y a des champs vide ou bien l'email \n  n'est pas valide ou bien photo non valide ", Notifications.ERROR);
                    tray.showAndDismiss(Duration.seconds(5));
               
            //label.setText("");
        } else if(aO.MailExistant(emailText.getText())){
            // label.setText("Email existant");
              tray = new TrayNotification("Erreur "," Mail Existant ", Notifications.ERROR);
                    tray.showAndDismiss(Duration.seconds(5));
               
        }else{
           
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
                
                user.setRole("a:1:{i:0;s:14:\"ROLE_ENTREPRISE\";}");
                user.setLocked(1);
                userDao.ajouterUtilisateur(user);
                username=usernameText.getText();
                pwd=passwordText.getText();
                inscri1Pane.setVisible(false);
                lblUserLogin.setVisible(false);
                inscri2Pane.setVisible(true);
               
                System.out.println(" U did it man");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("U didn't do it man");
                tray = new TrayNotification(" Erreur "," Erreur d'ajout  ", Notifications.ERROR);
                tray.showAndDismiss(Duration.seconds(5));
               
            }

       
    }
    }
     }
     
     public void inscriptionEntreprisePartie2Action(ActionEvent event) {

               try {
                //,String username,String pwd
                   InformationEntreprise informationEntreprise=new InformationEntreprise();
                UtilisateurDAO userDao = new UtilisateurDAO();
                Utilisateur user=new Utilisateur();
                user=userDao.findUtilisateurByLoginMdp(username, pwd);
                String attestationPath=uploadAttestation(attestationText);
                informationEntreprise.setAttestation(attestationPath);
                informationEntreprise.setFilename("attestation_"+username+".pdf");
                if(adresseText.getText().equals("")||
                    descriptionText.getText().equals("")||
                        siteWebText.getText().equals("")||
                        specialiteText.getText().equals("")||
                        telText.getText().equals("")||
                        raisonInscriptionText.getText().equals("")||
                        abreviationText.getText().equals(""))
                {
                    tray = new TrayNotification("Erreur d'ajout "," il y a des champs vides ", Notifications.ERROR);
                    tray.showAndDismiss(Duration.seconds(5));
               

//label.setText("Des Informations manquantes ");
                    
                }else if(!urlValid(siteWebText.getText()))
                {   
                     tray = new TrayNotification("Erreur d'ajout "," URL du Site web est non valid", Notifications.ERROR);
                    tray.showAndDismiss(Duration.seconds(5));
               
                }else if(!NumtelValid(telText.getText())){
                    tray = new TrayNotification("Erreur d'ajout "," Telephone est non valid ", Notifications.ERROR);
                    tray.showAndDismiss(Duration.seconds(5));
               
                        }
                else if(!NumtelValid(matriculeText.getText())){
                    tray = new TrayNotification("Erreur d'ajout "," Matricule est non valid ", Notifications.ERROR);
                    tray.showAndDismiss(Duration.seconds(5));
               
                }
                else{
                   
                informationEntreprise.setAdresse(adresseText.getText());
                informationEntreprise.setDescription(descriptionText.getText());
                informationEntreprise.setSiteWeb(siteWebText.getText());
                informationEntreprise.setSpecialite(specialiteText.getText());
                informationEntreprise.setEntrepriseId(user.getId());
                informationEntreprise.setMatriculeFiscal(matriculeText.getText());
                informationEntreprise.setNationnalite(comboPays.getValue());
                informationEntreprise.setNumTel(telText.getText());
                informationEntreprise.setRaisonInscription(raisonInscriptionText.getText());
                informationEntreprise.setType(comboType.getValue());
                informationEntreprise.setAbreviation(abreviationText.getText()); 
                InformationEntrepriseDAO informationEntrepriseDAO=new InformationEntrepriseDAO();
                informationEntrepriseDAO.ajouterInforamtionEntreprise(informationEntreprise);
    
                    System.out.println(""+informationEntreprise);
                    tray = new TrayNotification("Vous etes bien ajoutée", "Bienvenu à World Elephent \n votre Compte va etre bloqué pour un moment", Notifications.SUCCESS);
                    tray.showAndDismiss(Duration.seconds(5));
                
                //label.setText("\n Vous allez etre notifié dés l' activation de votre compte!!");
                config2 config = new config2();
                config.newStage2(stage, btnInscription, "/com/esprit/mooc/moocfx_1/authentification.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
                

                System.out.println(" U did it man");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("U didn't do it man");
                tray = new TrayNotification("Erreur ","Erreur d'ajout", Notifications.ERROR);
                tray.showAndDismiss(Duration.seconds(5));
               
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
    
    
     @FXML
        private void btnChoisirPhotoAction(ActionEvent event) {
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
        String target_dir="C:\\wamp\\www\\MOOCV5\\web\\uploads\\userPhoto\\"+usernameText.getText()+".png";
        Path target=Paths.get(target_dir);
        try {
        Files.copy(path, target, StandardCopyOption.REPLACE_EXISTING);
        return true;
        } catch (IOException ex) {
        Logger.getLogger(ChapitreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
}
        return false;
    }
}
