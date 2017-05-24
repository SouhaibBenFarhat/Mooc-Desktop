/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.InformationEntrepriseDAO;
import com.esprit.mooc.DAO.InformationFormateurDAO;
import com.esprit.mooc.Entities.InformationEntreprise;
import com.esprit.mooc.Entities.InformationFormateur;
import com.esprit.mooc.Entities.Utilisateur;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;



/**
 *
 * @author kods
 */
public class UserProfilController implements Initializable{
   
    
    @FXML
    private TextField emailText;

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
    private Hyperlink attestationText;
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
    private Button btnSupprimer;
    @FXML
    private Button btnInviter;
    @FXML
    private Button btnContacter;
    @FXML
    private Button close;
    
    @FXML
    private AnchorPane entreprisePane;
    @FXML
    private AnchorPane formateurPane;
    
         @FXML
    private TextField usernameText;

    @FXML
    private TextField passwordText;
    
    
    @FXML
    private TextField googlePlusText;
     
    @FXML
    private Hyperlink cvText;

     @FXML
    private TextArea biographieText;
    
     @FXML
    private TextArea miniBiographieText;
     
     @FXML
    private TextArea aProposText;
     @FXML
    private AnchorPane apprenantPane;
     
    @FXML
    private ImageView imageUser;
    
    @FXML
    TextField specialiteTextFormateur;   
    
    
      @FXML
    private TextField emailText2;

    @FXML
    private Text lblEmail;

     @FXML
    private Text lblSpecialite; 
  
     @FXML
    private TextField siteWebText1;
     
   
    Utilisateur user;   
    
  Utilisateur loggedUser;  
    
    InformationEntrepriseDAO iedao=new  InformationEntrepriseDAO();
    InformationEntreprise infoEnteprise;
    InformationFormateurDAO informationFormateurDAO=new InformationFormateurDAO();
    InformationFormateur info;
    UserProfilController(Utilisateur user) {
       this.user=user; 
       
    }

    public Utilisateur getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Utilisateur loggedUser) {
        this.loggedUser = loggedUser;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        Platform.runLater(() -> {
            System.out.println("le role de user de ce profil est "+user.getRole());
       if(user.getRole().contains("ENTREPRISE")){
           entreprisePane.setVisible(true);
           infoEnteprise=iedao.findInfoEntrepriseByIdEntreprise(user.getId());
           usernameText.setText(user.getUsename());
            emailText.setText(user.getEmail());
       telText.setText(infoEnteprise.getNumTel());
       abreviationText.setText(infoEnteprise.getAbreviation());
       matriculeText.setText(infoEnteprise.getMatriculeFiscal());
    
        adresseText.setText(infoEnteprise.getAdresse());
           System.out.println(" atte "+infoEnteprise.getFilename());
        raisonInscriptionText.setText(infoEnteprise.getRaisonInscription());
        attestationText.setText(infoEnteprise.getFilename());
        specialiteText.setText(infoEnteprise.getSpecialite());
        siteWebText.setText(infoEnteprise.getSiteWeb());
        descriptionText.setText(infoEnteprise.getDescription());
    
        PaysText.setText(infoEnteprise.getNationnalite());
        TypeText.setText(infoEnteprise.getType());
        
        if(loggedUser.getRole().contains("admin")){
            
         attestationText.setOnMouseClicked((MouseEvent e)->{
        
//        Object[] path;
//        path=new Object[]{"C:\\wamp\\www\\MOOCV5\\web\\uploads\\attestation\\attestation"+user.getUsename()+".pdf"};
//    Viewer viewer = new Viewer();
//    viewer.setupViewer();
  //  viewer.executeCommand(Commands.OPENFILE, path );

    //viewer.setupViewer();
             
//             PdfDecoder pdf = new PdfDecoder();
//             try {  
//                 pdf.openPdfFile("C:\\wamp\\www\\MOOCV5\\web\\uploads\\attestation\\attestation"+user.getUsename()+".pdf");
//                 pdf.setVisible(true);
//                
//             } catch (PdfException ex) {
//                 Logger.getLogger(UserProfilController.class.getName()).log(Level.SEVERE, null, ex);
//             }

   // pdf.closePdfFile();
        });
        }
       // else attestationText.setDisable(true);
    
       }
       else if(user.getRole().contains("FORMATEUR")){
           
           info=informationFormateurDAO.findInfoFormateurByIdFormateur(user.getId());
            usernameText.setText(user.getUsename());
            emailText.setText(user.getEmail());
            aProposText.setText(info.getaPropos());
            biographieText.setText(info.getBiographie());
            googlePlusText.setText(info.getGooglePlus());
            miniBiographieText.setText(info.getMiniBiographie());
            siteWebText.setText(info.getSiteWeb());
       
            specialiteTextFormateur.setText(info.getSpecialite());            
            
            
            cvText.setText(info.getFilename());
            formateurPane.setVisible(true);
            
            
//            if(loggedUser.getRole().contains("COMITE")){
//            
//         attestationText.setOnMouseClicked((MouseEvent e)->{
//        
////        Object[] path;
////        path=new Object[]{"C:\\wamp\\www\\MOOCV5\\web\\uploads\\cv\\cv"+user.getUsename()+".pdf"};
////    Viewer viewer = new Viewer();
////    viewer.setupViewer();
//  //  viewer.executeCommand(Commands.OPENFILE, path );
//
//    //viewer.setupViewer();
//        });
//        }
//        else cvText.setDisable(true);
//    
//       
      }
    
        
        close.setOnMouseClicked((MouseEvent event)->{
        
         ((Node)(event.getSource())).getScene().getWindow().hide();
        
        });
        
       
    });
                }

    public TextField getEmailText() {
        return emailText;
    }

    public void setEmailText(TextField emailText) {
        this.emailText = emailText;
    }

    public TextField getTelText() {
        return telText;
    }

    public void setTelText(TextField telText) {
        this.telText = telText;
    }

    public TextField getAbreviationText() {
        return abreviationText;
    }

    public void setAbreviationText(TextField abreviationText) {
        this.abreviationText = abreviationText;
    }

    public TextField getMatriculeText() {
        return matriculeText;
    }

    public void setMatriculeText(TextField matriculeText) {
        this.matriculeText = matriculeText;
    }

    public TextField getAdresseText() {
        return adresseText;
    }

    public void setAdresseText(TextField adresseText) {
        this.adresseText = adresseText;
    }

    public TextField getRaisonInscriptionText() {
        return raisonInscriptionText;
    }

    public void setRaisonInscriptionText(TextField raisonInscriptionText) {
        this.raisonInscriptionText = raisonInscriptionText;
    }

    public Hyperlink getAttestationText() {
        return attestationText;
    }

    public void setAttestationText(Hyperlink attestationText) {
        this.attestationText = attestationText;
    }

    public TextField getSpecialiteText() {
        return specialiteText;
    }

    public void setSpecialiteText(TextField specialiteText) {
        this.specialiteText = specialiteText;
    }

    public TextField getSiteWebText() {
        return siteWebText;
    }

    public void setSiteWebText(TextField siteWebText) {
        this.siteWebText = siteWebText;
    }

    public TextArea getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(TextArea descriptionText) {
        this.descriptionText = descriptionText;
    }

    public TextField getPaysText() {
        return PaysText;
    }

    public void setPaysText(TextField PaysText) {
        this.PaysText = PaysText;
    }

    public TextField getRechercheText() {
        return rechercheText;
    }

    public void setRechercheText(TextField rechercheText) {
        this.rechercheText = rechercheText;
    }

    public TextField getTypeText() {
        return TypeText;
    }

    public void setTypeText(TextField TypeText) {
        this.TypeText = TypeText;
    }

    public Button getBtnSupprimer() {
        return btnSupprimer;
    }

    public void setBtnSupprimer(Button btnSupprimer) {
        this.btnSupprimer = btnSupprimer;
    }

    public Button getBtnInviter() {
        return btnInviter;
    }

    public void setBtnInviter(Button btnInviter) {
        this.btnInviter = btnInviter;
    }

    public Button getBtnContacter() {
        return btnContacter;
    }

    public void setBtnContacter(Button btnContacter) {
        this.btnContacter = btnContacter;
    }
    
    
    
}
