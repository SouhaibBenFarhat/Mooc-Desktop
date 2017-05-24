///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.esprit.mooc.controllers;
//
//import com.esprit.mooc.DAO.InvitationFormateurEntrepriseDAO;
//import com.esprit.mooc.DAO.UtilisateurDAO;
//import com.esprit.mooc.Entities.InvitationFormateurEntreprise;
//import com.esprit.mooc.Entities.Utilisateur;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.application.Platform;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//
///**
// * FXML Controller class
// *
// * @author kods
// */
//public class PaneUserController implements Initializable {
//    
//    @FXML
//    private Label labelNom;
//    @FXML
//    private Label labelEmail;
//    @FXML
//    private ImageView image;
//    @FXML
//    private Pane paneUser;
//    @FXML
//    private Button btnProfil;
//    @FXML
//    private Button btnLock;
//    @FXML
//    private Button btnUnlock;
//      @FXML
//    private Button btnAccept;
//    @FXML
//    private Button btnReject;
//    @FXML
//    private Button btnAddEntreprise;
//    @FXML
//    private Button btnAddFormateur;
//    
//   Utilisateur loggedUser;
//   Alert a=new Alert(Alert.AlertType.INFORMATION);
//
//    Utilisateur user;
//    UtilisateurDAO aO=new UtilisateurDAO();
//    public PaneUserController(Utilisateur u) {
//        user=u;
//        btnUnlock=new Button();
//       
//    }
//    
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//        
//         Platform.runLater(() -> {
//             labelNom.setText(user.getUsename());
//        labelEmail.setText(user.getEmail());
//        if(!UtilisateurDAO.loggedUser.getRole().contains("FORMATEUR")){
//            if(user.getRole().contains("APPRENANT"))
//            {
//                if(user.getEnbaled()==1){
//                    btnLock.setVisible(true);
//                    btnUnlock.setVisible(false);
//                }
//                    else
//                    {
//                    btnLock.setVisible(false);
//                    btnUnlock.setVisible(true);
//                    }
//                btnProfil.setDisable(true);
//            }
//            else if(user.getRole().contains("FORMATEUR") || user.getRole().contains("ENTREPRISE") )
//                {
//                    if(user.getEnbaled()==1 && user.getLocked()==0){
//                        btnLock.setVisible(true);
//                        btnUnlock.setVisible(false);
//                    }
//            
//                    else
//                        if(user.getRole().contains("ENTREPRISE")&& user.getLocked()==1 ){
//                            btnUnlock.setVisible(false);
//                            btnLock.setVisible(false);
//                            btnAccept.setVisible(true);
//                            btnReject.setVisible(true);
//                        }
//                        
//                        else
//                            {
//                            btnLock.setVisible(false);
//                            btnUnlock.setVisible(true);
//                            }
//                }
//         }  
//             if(UtilisateurDAO.loggedUser.getRole().contains("FORMATEUR") && UtilisateurDAO.loggedUser.getIdEntrepriseUtilisateur()==0)            
//             {         btnLock.setVisible(false);
//                                btnUnlock.setVisible(false);
//                                btnAddEntreprise.setVisible(true);
//                                btnAccept.setVisible(false);
//                                btnReject.setVisible(false);
//         
//              }
//                else 
//                    if(UtilisateurDAO.loggedUser.getRole().contains("FORMATEUR") && UtilisateurDAO.loggedUser.getIdEntrepriseUtilisateur()!=0) {           
//                                btnLock.setVisible(false);
//                                btnUnlock.setVisible(false);
//                                btnAddEntreprise.setVisible(false);
//                                btnAccept.setVisible(false);
//                                btnReject.setVisible(false);
//                                
//         
//                    }
//         
//                     btnProfil.setOnMouseClicked((MouseEvent event)->{
//        Stage stage=new Stage();
//        FXMLLoader loader;
//        loader=new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/InformationUser.fxml"));
//        UserProfilController userProfilController=new UserProfilController(user);
//        loader.setController(userProfilController);
//        Parent root;
//        try {
//            root = loader.load();
//            Scene scene = new Scene(root);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setScene(scene);
//        stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(PaneUserController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        });
//        
//
//        
//         });          
//    }   
//    
//    public Label getLabelNom() {
//        return labelNom;
//    }
//
//    public void setLabelNom(Label labelNom) {
//        this.labelNom = labelNom;
//    }
//
//    public Label getLblPrenom() {
//        return labelEmail;
//    }
//
//    public void setLblPrenom(Label lblPrenom) {
//        this.labelEmail = lblPrenom;
//    }
//
//    public ImageView getImage() {
//        return image;
//    }
//
//    public void setImage(ImageView image) {
//        this.image = image;
//    }
//
//    public Pane getPaneUser() {
//        return paneUser;
//    }
//
//    public void setPaneUser(Pane paneUser) {
//        this.paneUser = paneUser;
//    }
//
//    public Label getLabelEmail() {
//        return labelEmail;
//    }
//
//    public void setLabelEmail(Label labelEmail) {
//        this.labelEmail = labelEmail;
//    }
//
//    public Button getBtnProfil() {
//        return btnProfil;
//    }
//
//    public void setBtnProfil(Button btnProfil) {
//        this.btnProfil = btnProfil;
//    }
//
//    public Button getBtnLock() {
//        return btnLock;
//    }
//
//    public void setBtnLock(Button btnLock) {
//        this.btnLock = btnLock;
//    }
//
//    public Button getBtnUnlock() {
//        return btnUnlock;
//    }
//
//    public void setBtnUnlock(Button btnUnlock) {
//        this.btnUnlock = btnUnlock;
//    }
//
//    public Utilisateur getLoggedUser() {
//        return loggedUser;
//    }
//
//    public void setLoggedUser(Utilisateur loggedUser) {
//        this.loggedUser = loggedUser;
//    }
//
//}
