///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.esprit.mooc.controllers.others;
//
//import com.esprit.mooc.DAO.InformationEntrepriseDAO;
//import com.esprit.mooc.DAO.InvitationFormateurEntrepriseDAO;
//import com.esprit.mooc.DAO.UtilisateurDAO;
//import com.esprit.mooc.Entities.InformationEntreprise;
//import com.esprit.mooc.Entities.InvitationFormateurEntreprise;
//import com.esprit.mooc.Entities.Utilisateur;
//import java.net.URL;
//import java.util.List;
//import java.util.ResourceBundle;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.geometry.Orientation;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.DatePicker;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.Pane;
//import javafx.scene.text.Text;
//
//
///**
// * FXML Controller class
// *
// * @author kods
// */
//public class ListeEntreprisesController implements Initializable {
//
////    
////     @FXML 
////    public TableView<Utilisateur> tableApprenant;
//    @FXML 
//    public TableView<Utilisateur> tableEntreprise;
//
//    
//    @FXML 
//    private TableColumn<Utilisateur,Integer> idCol;
//    @FXML 
//    private TableColumn<Utilisateur,String> usernameCol;
//   
//    @FXML
//    private TextField emailText;
//
//    @FXML
//    private Text lblEmail;
//
//     @FXML
//    private Text lblSpecialite; 
//
//     
//     @FXML
//    private Pane inscri2Pane;
//
//    
//    @FXML
//    private TextField telText;
//    @FXML
//    private TextField abreviationText;
//    @FXML
//    private TextField matriculeText;
//    
//    @FXML
//    private TextField adresseText;
//    
//    @FXML
//    private TextField raisonInscriptionText;
//    @FXML
//    private TextField attestationText;
//    @FXML
//    private TextField specialiteText;
//     @FXML
//    private TextField siteWebText;
//    @FXML
//    private TextArea descriptionText;
//    
//    @FXML
//    private TextField PaysText;
//    @FXML
//    private TextField rechercheText;
//    @FXML
//    private TextField TypeText;
//    @FXML
//    private DatePicker dateCreationPicker;
//  
//    @FXML
//    private Button btnSupprimer;
//    @FXML
//    private Button btnInviter;
//    @FXML
//    private Button btnContacter;
//    
//    @FXML
//    private Alert a;
//    int id;
//    
//    
//    private Utilisateur loggedUtilisateur;
//    UtilisateurDAO userDao=new UtilisateurDAO();
//    List<Utilisateur> lEntreprise=userDao.findUtilisateurByRole("ROLE_ENTREPRISE");
//    
//    public ObservableList<Utilisateur> listEntreprises=FXCollections.observableArrayList(lEntreprise);
//    
//    ListeEntreprisesController(Utilisateur loggedUser) {
//    
//        this.loggedUtilisateur=loggedUser;
//    }
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        idCol.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("id"));
//        usernameCol.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("usename"));
//        tableEntreprise.setItems(listEntreprises);
//     
//    }    
//    @FXML
//    public void selectTable(){
//        Utilisateur u2 = tableEntreprise.getSelectionModel().getSelectedItem();
//        id = u2.getId();
//        InformationEntrepriseDAO informationEntrepriseDAO=new InformationEntrepriseDAO();
//        InformationEntreprise infoEnteprise=informationEntrepriseDAO.findInfoEntrepriseByIdEntreprise(id);
//       emailText.setText(u2.getEmail());
//       telText.setText(infoEnteprise.getNumTel());
//       abreviationText.setText(infoEnteprise.getAbreviation());
//       matriculeText.setText(infoEnteprise.getMatriculeFiscal());
//    
//        adresseText.setText(infoEnteprise.getAdresse());
//    
//        raisonInscriptionText.setText(infoEnteprise.getRaisonInscription());
//        attestationText.setText(infoEnteprise.getFilename());
//        specialiteText.setText(infoEnteprise.getSpecialite());
//        siteWebText.setText(infoEnteprise.getSiteWeb());
//        descriptionText.setText(infoEnteprise.getDescription());
//    
//        PaysText.setText(infoEnteprise.getNationnalite());
//        TypeText.setText(infoEnteprise.getType());
//        //dateCreationPicker.setValue(infoEnteprise.getDateCreation());
//      if(loggedUtilisateur.getRole().contains("ADMIN")){
//           btnSupprimer.setVisible(true);
//      }
//      else if(loggedUtilisateur.getRole().contains("FORMATEUR"))
//      {
//          UtilisateurDAO udao=new UtilisateurDAO();
//          Utilisateur user=udao.findFormateurById(loggedUtilisateur.getId());
//          if(user.getIdEntrepriseUtilisateur()==0){
//           btnInviter.setVisible(true); 
//           btnContacter.setVisible(true);
//          }
//          
//      }
//
//    }
//    @FXML
//    public void supprimerAction(){
//        userDao.supprimerEntreprise(id);
//        emailText.setText("");
//        telText.setText("");
//        abreviationText.setText("");
//        matriculeText.setText("");
//    
//        adresseText.setText("");
//    
//        raisonInscriptionText.setText("");
//        attestationText.setText("");
//        specialiteText.setText("");
//        siteWebText.setText("");
//        descriptionText.setText("");
//        PaysText.setText("");
//        TypeText.setText("");
//        this.refreshtable();
//    }
//    public void refreshtable(){
//        ObservableList<Utilisateur> list=FXCollections.observableArrayList(userDao.findUtilisateurByRole("ROLE_ENTREPRISE")); 
//             tableEntreprise.setItems(list);
//    }
//    
//    
//     @FXML
//    public void rechercherEntrepriseAction(){
//       ObservableList<Utilisateur> list=FXCollections.observableArrayList(userDao.findEntrepriseByName(rechercheText.getText())); 
//       tableEntreprise.setItems(list);
//    }
//    
//      @FXML
//    public void InviterAction(){
//          InvitationFormateurEntrepriseDAO dAO=new InvitationFormateurEntrepriseDAO();
//          InvitationFormateurEntreprise ife=new InvitationFormateurEntreprise();
//          ife.setIdFormateur(loggedUtilisateur.getId());
//          ife.setIdEntreprise(id);
//          ife.setEtat("en attente");
//                  dAO.envoyerInvitationFormateurEntreprise(ife);
//        a=new Alert(Alert.AlertType.INFORMATION, "Votre invitation a ete envoye", ButtonType.OK);
//        a.show();
//    }
//    
//     @FXML
//    public void ContacterAction(){
//       ObservableList<Utilisateur> list=FXCollections.observableArrayList(userDao.findEntrepriseByName(rechercheText.getText())); 
//       tableEntreprise.setItems(list);
//    }
//}
