/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.DisciplineDao;
import com.esprit.mooc.DAO.InformationEntrepriseDAO;
import com.esprit.mooc.DAO.InformationFormateurDAO;
import com.esprit.mooc.DAO.PaysDao;
import com.esprit.mooc.DAO.PhotoUtilisateurDAO;
import com.esprit.mooc.Entities.InformationEntreprise;
import com.esprit.mooc.Entities.InformationFormateur;
import com.esprit.mooc.Entities.PhotoUtilisateur;
import com.esprit.mooc.Entities.Utilisateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;

/**
 *
 * @author kods
 */
public class MonProfilController implements Initializable{

    @FXML
    private TextField usernameText;
    @FXML
    private TextField emailText;
   
      @FXML
    private TextField usernameTextEntreprise;
    @FXML
    private TextField emailTextEntreprise;
    @FXML
    private TextField googlePlusText;
     
    @FXML
    private TextField cvText;
     @FXML
    private TextField siteWebText;
        @FXML
    private TextField siteWebTextEntreprise;

     @FXML
    private TextArea biographieText;
    
     @FXML
    private TextArea miniBiographieText;
     
     @FXML
    private TextArea aProposText;
     @FXML
    private AnchorPane apprenantPane;
    
     @FXML
    private AnchorPane formateurPane;
     
     @FXML
    private AnchorPane paneApprenant;
     
    @FXML
    private ImageView imageUser;

    @FXML
   private TextField specialiteText;   


    @FXML
    private Text lblEmail;

     @FXML
    private Text lblSpecialite; 
    @FXML
    private Text lblBiographie;
     
     @FXML
    private AnchorPane entreprisePane;

   
    @FXML
    private TextField telText;
       @FXML
    private TextField telTextEntreprise;
    @FXML
    private TextField abreviationTextEntreprise;
    @FXML
    private TextField matriculeTextEntreprise;
    
    @FXML
    private TextField adresseTextEntreprise;
    
    @FXML
    private TextField raisonInscriptionTextEntreprise;
    @FXML
    private TextField attestationTextEntreprise;
    @FXML
    private TextField specialiteTextEntreprise;
    @FXML
    private TextArea descriptionTextEntreprise;
    
   
     @FXML
    private ComboBox<String> comboPays;
      @FXML
    private ComboBox<String> comboType;
    ObservableList<String> typeList=FXCollections.observableArrayList("Etatique","Privée");
    
    MenuFormateurController parentFormateur;
    MenuEntrepriseController parentEntreprise;
     @FXML
    private Button btnModifierEntreprise;
         @FXML
   private Button btnModifierFormateur;
      PaysDao paysDao=new PaysDao();
     List<String> lsPays=paysDao.findAll();
     public ObservableList<String> listPays=FXCollections.observableArrayList(lsPays);
    private Utilisateur loggedUser;
    private InformationFormateur info;
    private InformationFormateurDAO informationFormateurDAO=new InformationFormateurDAO();
    
    private InformationEntrepriseDAO informationEntrepriseDao=new InformationEntrepriseDAO();
    @FXML
    private TextField usernameText1;
    @FXML
    private TextField emailText1;
   
    MonProfilController(Utilisateur user) {
        loggedUser=user;
        
       }

    MonProfilController(Utilisateur user, MenuFormateurController aThis) {
         loggedUser=user;
        parentFormateur=aThis;
        
    }
    
    MonProfilController(Utilisateur user, MenuEntrepriseController aThis) {
         loggedUser=user;
        parentEntreprise=aThis;
        System.out.println(" id logge User"+loggedUser.getId());
        System.out.println("le role de :"+loggedUser.getRole());
             
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
             //imageUser.setVisible(false);
         Platform.runLater(() -> {
             System.out.println("le role de :"+loggedUser.getRole());
             System.out.println("l email"+loggedUser.getEmail());
             PhotoUtilisateur photoUtilisateur=new PhotoUtilisateurDAO().findPhotoById(loggedUser.getPhoto());
             File file ;
             FileInputStream inputStream = null;
             if(photoUtilisateur.getPathPhoto() != null){
         file = new File("C:\\wamp\\www\\MOOCV5\\web\\uploads\\userPhoto\\" + photoUtilisateur.getPathPhoto());
                System.out.println("la logo"+photoUtilisateur.getPathPhoto());
                
             try {
                 inputStream = new FileInputStream(file);
                 imageUser.setImage(new Image(inputStream));
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(PaneEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
             }
             }
             else{
            try {
                file = new File("C:\\wamp\\www\\MOOCV5\\web\\uploads\\userPhoto\\entreprise.png");
                inputStream = new FileInputStream(file);
                System.out.println("la logo"+photoUtilisateur.getPathPhoto());
                imageUser.setImage(new Image(inputStream));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PaneEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
            }
             }
       
           if(loggedUser.getRole().contains("FORMATEUR"))
        {
            entreprisePane.setVisible(false);
            usernameText.setText(loggedUser.getUsename());
            emailText.setText(loggedUser.getEmail());
            info=informationFormateurDAO.findInfoFormateurByIdFormateur(loggedUser.getId());
            aProposText.setText(info.getaPropos());
            aProposText.setWrapText(true);
            biographieText.setText(info.getBiographie());
            biographieText.setWrapText(true);
            googlePlusText.setText(info.getGooglePlus());
            miniBiographieText.setText(info.getMiniBiographie());
            miniBiographieText.setWrapText(true);
            siteWebText.setText(info.getSiteWeb());
           
            specialiteText.setText(info.getSpecialite());            
            formateurPane.setVisible(true);
        } 
            else 
               if(loggedUser.getRole().contains("ENTREPRISE"))
        {
            
        InformationEntreprise ie=informationEntrepriseDao.findInfoEntrepriseByIdEntreprise(loggedUser.getId());
          entreprisePane.setVisible(true);
          formateurPane.setVisible(false);
                adresseTextEntreprise.setText(ie.getAdresse());
                usernameTextEntreprise.setText(loggedUser.getUsename());
                emailTextEntreprise.setText(loggedUser.getEmail());
                descriptionTextEntreprise.setText(ie.getDescription());
                siteWebTextEntreprise.setText(ie.getSiteWeb());
                specialiteTextEntreprise.setText(ie.getSpecialite());
                matriculeTextEntreprise.setText(ie.getMatriculeFiscal());
                comboType.setItems(typeList);
                comboType.setValue(ie.getType());                
                comboPays.setItems(listPays);
                comboPays.setValue(ie.getNationnalite());
                telTextEntreprise.setText(ie.getNumTel());
                raisonInscriptionTextEntreprise.setText(ie.getRaisonInscription());
                comboType.setValue(ie.getType());
               
                abreviationTextEntreprise.setText(ie.getAbreviation());
                
        }
        else
            if(loggedUser.getRole().contains("MEMBRE_COMITE")){
                
            }
           if(loggedUser.getRole().contains("APPRENANT")){
                paneApprenant.setVisible(true);
            usernameText1.setText(loggedUser.getUsename());
            emailText1.setText(loggedUser.getEmail());
            }
           
           
          
           
    });
    
     }
    private String stripHTMLTags(String htmlText) {

        Pattern pattern = Pattern.compile("<[^>]*>");
        Matcher matcher = pattern.matcher(htmlText);
        final StringBuffer sb = new StringBuffer(htmlText.length());
        while(matcher.find()) {
            matcher.appendReplacement(sb, " ");
        }
        matcher.appendTail(sb);
        return sb.toString().trim();
//     Document doc = Jsoup.parse(htmlText);
//    Elements scriptElements = doc.select("script[type=\"application/ld+json\"]");
//    String scriptContent = scriptElements.first().html()

    }
    @FXML
    public void modifierEntrepriseAction(){
        try {
         InformationEntreprise ie=new InformationEntrepriseDAO().findInfoEntrepriseByIdEntreprise(loggedUser.getId());
                   ie.setAdresse(adresseTextEntreprise.getText());
                ie.setDescription(descriptionTextEntreprise.getText());
                ie.setSiteWeb(siteWebTextEntreprise.getText());
               ie.setNationnalite(comboPays.getValue());
                ie.setNumTel(telTextEntreprise.getText());
                ie.setType(comboType.getValue());
                ie.setAbreviation(abreviationTextEntreprise.getText());
               InformationEntrepriseDAO informationDao=new InformationEntrepriseDAO();                
               informationDao.modifierInformationEntreprise(ie);
                parentEntreprise.monProfilAction();
//         entreprisePane.setVisible(true);
//          formateurPane.setVisible(false);
                 } catch (Exception e) {
                     e.printStackTrace();
        }
    }
        public void modifierFormateurAction(){
            
            InformationFormateur ie=informationFormateurDAO.findInfoFormateurByIdFormateur(loggedUser.getId());

            ie.setaPropos(aProposText.getText());
            ie.setBiographie(biographieText.getText());
            ie.setGooglePlus(googlePlusText.getText());
            ie.setMiniBiographie(miniBiographieText.getText());
            ie.setSiteWeb(siteWebText.getText());
            try {
//                System.out.println(" "+ ie);
                 informationFormateurDAO.modifierInformationFormateur(ie);
            parentFormateur.monProfilAction();
            } catch (Exception e) {
                e.printStackTrace();
            }
           
        }
                
    }

