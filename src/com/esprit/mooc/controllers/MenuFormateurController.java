/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.ChapitreDao;
import com.esprit.mooc.DAO.CourDAO;
import com.esprit.mooc.DAO.DisciplineDao;
import com.esprit.mooc.DAO.ForumDAO;
import com.esprit.mooc.DAO.InvitationEntrepriseFormateurDAO;
import com.esprit.mooc.DAO.InvitationFormateurEntrepriseDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Chapitre;
import com.esprit.mooc.Entities.Cours;
import com.esprit.mooc.Entities.Discipline;
import com.esprit.mooc.Entities.Forum;
import com.esprit.mooc.Entities.InvitationEntrepriseFormateur;
import com.esprit.mooc.Entities.InvitationFormateurEntreprise;
//import com.esprit.mooc.controllers.others.MesCoursController;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author Herudi
 */
public class MenuFormateurController implements Initializable {
    
    @FXML
    ImageView menuImage = new ImageView();
    @FXML
    ImageView guideImage = new ImageView();
    @FXML
    Button btnAddCours = new Button();
    @FXML
    Label menuTitle = new Label();
    @FXML
    public TextField rechercherchap;
    @FXML
    public Button creerchapitre;
    @FXML
    public AnchorPane anchorrechchap;
    @FXML
    TextField chercherDiscipline = new TextField();
    @FXML
    Button showStatBtn = new Button();
    @FXML
    Button ajouterDisciplineBtn = new Button();
    @FXML
    Button ajouterForumBtn = new Button();
    @FXML
    TextField chercherForumTxt = new TextField();
    @FXML
    private Button close;
    @FXML
    private Button maximize;
    @FXML
    private Button minimize;
    @FXML
    Button mecCoursBtn = new Button();
    @FXML
    private Button fullscreen;
    @FXML
    private Label title;
    Stage stage;
    Rectangle2D rec2;
    Double w, h;
    @FXML
    Label selectedMenu = new Label();
    @FXML
    public Pane paneData;
    @FXML
    Button disciplineBtn = new Button();
    @FXML
    public Button forumButton;
    @FXML
    Pane paneButton = new Pane();
    @FXML
    Button btnEntreprises;
    @FXML
    Button btnProfil;
    config2 con = new config2();
    @FXML
    private Button btnLogout;
    
    private Utilisateur loggedUser;
    
    @FXML
    private Label ApprenantName;
    public AnchorPane anch = new AnchorPane();
    
    public TextFlow tf;
    FXMLLoader loader;
    
    MenuFormateurController(Utilisateur user) {
        loggedUser = user;
        
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuImage.setVisible(false);
        
        mecCoursBtn.setTooltip(new Tooltip("Mes Cours"));
        anchorrechchap.setVisible(false);
        paneButton.setVisible(true);
        disciplineBtn.setTooltip(new Tooltip("Nos discipline"));
        ajouterForumBtn.setVisible(true);
        ajouterForumBtn.setTooltip(new Tooltip("Lancer un nouveau forum"));
        chercherForumTxt.setVisible(false);
        rec2 = Screen.getPrimary().getVisualBounds();
        w = 0.1;
        h = 0.1;
        tf = new TextFlow();
        tf.setPrefWidth(1090);
        
        Platform.runLater(() -> {
            
            stage = (Stage) maximize.getScene().getWindow();
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
            maximize.getStyleClass().add("decoration-button-restore");
            
            this.disciplineAction();
            
        });
        forumButton.setOnMouseEntered(myHandler);
        disciplineBtn.setOnMouseEntered(myHandler);
        mecCoursBtn.setOnMouseEntered(myHandler);
        ajouterForumBtn.setOnMouseEntered(myHandler);
        showStatBtn.setOnMouseEntered(myHandler);
        btnAddCours.setOnMouseEntered(myHandler);
        btnLogout.setOnMouseEntered(myHandler);
        paneData.setOnMouseEntered(paneHandler);
        ajouterDisciplineBtn.setOnMouseEntered(myHandler);
    }
    final EventHandler<MouseEvent> paneHandler = new EventHandler<MouseEvent>() {
        
        @Override
        public void handle(final MouseEvent event) {
            menuTitle.setText("");
            guideImage.setVisible(false);
        }
    };
    
    final EventHandler<MouseEvent> myHandler = new EventHandler<MouseEvent>() {
        
        @Override
        public void handle(final MouseEvent event) {
            Button x = (Button) event.getSource();
            if (x.getId().equals(forumButton.getId())) {
                menuTitle.setText("Nos forums");
                guideImage.setVisible(true);
            } else if (x.getId().equals(disciplineBtn.getId())) {
                menuTitle.setText("Nos discipline");
                guideImage.setVisible(true);
            } else if (x.getId().equals(mecCoursBtn.getId())) {
                menuTitle.setText("Mes cours");
                guideImage.setVisible(true);
            } else if (x.getId().equals(ajouterForumBtn.getId())) {
                menuTitle.setText("Lancer Forum");
                guideImage.setVisible(true);
            } else if (x.getId().equals(showStatBtn.getId())) {
                menuTitle.setText("Voir Statistique");
                guideImage.setVisible(true);
            } else if (x.getId().equals(btnAddCours.getId())) {
                menuTitle.setText("Lancer cours");
                guideImage.setVisible(true);
            } else if (x.getId().equals(btnLogout.getId())) {
                menuTitle.setText("Quitter");
                guideImage.setVisible(true);
            } else if (x.getId().equals(ajouterDisciplineBtn.getId())) {
                menuTitle.setText("Lancer une discipline");
                guideImage.setVisible(true);
            } else {
                menuTitle.setText("");
            }
        }
    };
    
    @FXML
    public void monProfilAction() {
        creerchapitre.setVisible(false);
        selectedMenu.setText("Mon Profil");
        loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/MonProfil.fxml"));
        MonProfilController profilFormateurController2 = new MonProfilController(loggedUser, this);
        loader.setController(profilFormateurController2);
        con.loadPane(paneData, loader);        
        
    }
     @FXML
    public void listeEntreprisesAction(){
         tf.getChildren().clear();
         paneData.getChildren().clear();
          UtilisateurDAO userDao=new UtilisateurDAO();
          List<Utilisateur> lEntreprises=userDao.findUtilisateurByRole("ROLE_ENTREPRISE");
          for (Utilisateur user : lEntreprises) {
               loader=new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneEntreprise.fxml"));
                PaneEntrepriseController paneController=new PaneEntrepriseController(user,this);            
                paneController.setLoggedUser(loggedUser);
                loader.setController(paneController);
                try {
                    anch=loader.load();          
                } catch (IOException ex) {
                    Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tf.getChildren().add(anch);
            }

             paneData.getChildren().addAll(tf);
         
    
    
    }
    
      
    @FXML
    public void listeDemandeEntreprisesAction(){
        tf.getChildren().clear();
        paneData.getChildren().clear();
//        System.out.println("liste demande entreprise");
//        System.out.println(" l id du l entre"+loggedUser.getIdEntrepriseUtilisateur());
         if(loggedUser.getIdEntrepriseUtilisateur()==0){
             
       InvitationEntrepriseFormateurDAO iefdao = new InvitationEntrepriseFormateurDAO();
        List<InvitationEntrepriseFormateur> lief = iefdao.findInvitationEntrepriseFormateur(loggedUser.getId());

        for (InvitationEntrepriseFormateur lief1 : lief) {
            //System.out.println("l id du l invit"+lief1.getId());
            UtilisateurDAO udao=new UtilisateurDAO();
             Utilisateur entreprise=udao.findEntrepriseById(lief1.getIdEntreprise());
                 loader=new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneEntreprise.fxml"));
                PaneEntrepriseController paneController=new PaneEntrepriseController(entreprise,this);
                paneController.setLoggedUser(loggedUser);
                loader.setController(paneController);
                try {
                    anch=loader.load();          
                } catch (IOException ex) {
                    Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tf.getChildren().add(anch);
            }

             paneData.getChildren().addAll(tf);

        }
    }  
    
    
    public Utilisateur getLoggedUser() {
        return loggedUser;
    }
    
    public void setLoggedUser(Utilisateur loggedUser) {
        this.loggedUser = loggedUser;
    }
    
    @FXML
    void AddCour(ActionEvent event) {
        selectedMenu.setText("Lancer un nouveau cours");
        menuImage.setVisible(true);
        anchorrechchap.setVisible(false);
        config2 con = new config2();
        creerchapitre.setVisible(false);
        anchorrechchap.setVisible(false);
        
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/Cour.fxml"));
        
        CourController courController = new CourController(loggedUser, null, 1);
        loader.setController(courController);
        System.out.println("ghghjgj");
        con.loadPane(paneData, loader);
        
    }
    
    @FXML
    void MesCours() {
        selectedMenu.setText("Mes cours");
        menuImage.setVisible(true);
        anchorrechchap.setVisible(false);
        tf.getChildren().clear();
        paneData.getChildren().clear();
        creerchapitre.setVisible(false);
        anchorrechchap.setVisible(false);
        
        CourDAO coursDao = new CourDAO();
        List<Cours> lCour = coursDao.displayMyCours(loggedUser);
        for (Cours c : lCour) {
            
            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneCours.fxml"));
            PaneCourController paneController = new PaneCourController(c, 3, this, 5);
            
            loader.setController(paneController);
            try {
                anch = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tf.getChildren().add(anch);
        }
        
        paneData.getChildren().addAll(tf);
        
    }
    
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
            } else {
                stage.setMaximized(false);
                maximize.getStyleClass().remove("decoration-button-restore");
            }
            
        } else {
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
        } else {
            stage.setIconified(true);
        }
    }
    
    @FXML
    private void menufullscreen(ActionEvent event) {
        stage = (Stage) fullscreen.getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        } else {
            stage.setFullScreen(true);
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
    public void forumAction() {
        creerchapitre.setVisible(false);
        selectedMenu.setText("Nos Forum");
        menuImage.setVisible(true);
        anchorrechchap.setVisible(false);
        chercherDiscipline.setVisible(false);
        paneButton.setVisible(true);
        ajouterForumBtn.setVisible(true);
        chercherForumTxt.setVisible(true);
        chercherDiscipline.setVisible(false);
        tf.getChildren().clear();
        paneData.getChildren().clear();
        ForumDAO forumDAO = new ForumDAO();
        List<Forum> lForum = forumDAO.findAllForums();
        for (Forum forum : lForum) {
            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneForum.fxml"));
            PaneForumController paneController = new PaneForumController(forum, this);
            loader.setController(paneController);
            try {
                anch = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tf.getChildren().add(anch);
        }
        paneData.getChildren().addAll(tf);
        
    }
    
    public void chercherForum() {
        anchorrechchap.setVisible(false);
        tf.getChildren().clear();
        paneData.getChildren().clear();
        ForumDAO forumDAO = new ForumDAO();
        List<Forum> lForum = forumDAO.chercherForum(chercherForumTxt.getText());
        for (Forum forum : lForum) {
            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneForum.fxml"));
            PaneForumController paneController = new PaneForumController(forum, this);
            loader.setController(paneController);
            try {
                anch = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tf.getChildren().add(anch);
        }
        paneData.getChildren().addAll(tf);
    }
    
    public void chercherDiscipline() {
        anchorrechchap.setVisible(false);
        tf.getChildren().clear();
        paneData.getChildren().clear();
        DisciplineDao disciplineDao = new DisciplineDao();
        List<Discipline> disciplines = disciplineDao.findDisciplineByNom(chercherDiscipline.getText());
        for (Discipline discipline : disciplines) {
            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneDiscipline.fxml"));
            PaneDisciplineController paneController = new PaneDisciplineController(discipline, this);
            loader.setController(paneController);
            try {
                anch = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tf.getChildren().add(anch);
        }
        paneData.getChildren().addAll(tf);
    }
    
    public void ajouterForum() {
        anchorrechchap.setVisible(false);
        Parent root;
        Stage stage = new Stage();
        FXMLLoader loader;
        ajouterForumBtn.setVisible(true);
        try {
            
            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/AddForum.fxml"));
            PaneAddForumController paneAddForumController = new PaneAddForumController(this);
            loader.setController(paneAddForumController);
            root = loader.load();
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(PaneForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        stage.show();
    }
    
    public void ajouterDiscipline() {
        anchorrechchap.setVisible(false);
        Parent root;
        
        Stage stage = new Stage();
        FXMLLoader loader;
        paneButton.setVisible(true);
        try {
            
            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/AddDiscipline.fxml"));
            PaneAddDisciplineController paneAddDisciplineController = new PaneAddDisciplineController(this);
            loader.setController(paneAddDisciplineController);
            root = loader.load();
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(PaneForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        stage.show();
    }
    
    public void disciplineAction() {
        selectedMenu.setText("Nos Discipline");
        creerchapitre.setVisible(false);
        
        menuImage.setVisible(true);
        anchorrechchap.setVisible(false);
        menuTitle.setText("Nos discipline");
        ajouterForumBtn.setVisible(true);
        chercherForumTxt.setVisible(false);
        chercherDiscipline.setVisible(true);
        paneButton.setVisible(true);
        tf.getChildren().clear();
        paneData.getChildren().clear();
        DisciplineDao disciplineDao = new DisciplineDao();
        List<Discipline> lDiscipline = disciplineDao.findAllDisciplineObject();
        for (Discipline discipline : lDiscipline) {
            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneDiscipline.fxml"));
            PaneDisciplineController paneController = new PaneDisciplineController(discipline, this);
            loader.setController(paneController);
            try {
                anch = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tf.getChildren().add(anch);
        }
        paneData.getChildren().addAll(tf);
        
    }
    
    public void showStat() {
        anchorrechchap.setVisible(false);
        Parent root;
        Stage stage = new Stage();
        FXMLLoader loader;
        paneButton.setVisible(true);
        try {
            
            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/pieChar.fxml"));
            PieController pieSubjectController = new PieController(this);
            loader.setController(pieSubjectController);
            root = loader.load();
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(PaneForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        stage.show();
    }
    
    @FXML
    public void addchap() {
        anchorrechchap.setVisible(false);
        System.out.println("Je suis la");
        loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/addchapitre.fxml"));
        creerchapitre.setVisible(false);
        AddChapitreController addchap = new AddChapitreController();
        loader.setController(addchap);
        con.loadPane(paneData, loader);
    }
    
    public void rechchap() throws IOException {
        String rech = rechercherchap.getText();
        PaneCourController pc = new PaneCourController();
        PaneChapitreController pch = new PaneChapitreController();
        ChapitreDao chapdao = new ChapitreDao();
        int id = pch.idcours;
        if (rech.isEmpty()) {
            creerchapitre.setVisible(true);
            anchorrechchap.setVisible(true);
            tf.getChildren().clear();
            paneData.getChildren().clear();
            creerchapitre.setVisible(true);
            anchorrechchap.setVisible(true);
            List<Chapitre> lChap = chapdao.find2Chapitre(id);
            for (Chapitre c : lChap) {
                
                loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneChapitre.fxml"));
                PaneChapitreController paneController = new PaneChapitreController(c, pc);
                
                loader.setController(paneController);
                try {
                    anch = loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tf.getChildren().add(anch);
            }
            
            paneData.getChildren().addAll(tf);
            
        } else {
            creerchapitre.setVisible(true);
            anchorrechchap.setVisible(true);
            tf.getChildren().clear();
            paneData.getChildren().clear();
            creerchapitre.setVisible(true);
            anchorrechchap.setVisible(true);
            List<Chapitre> lChap = chapdao.findchap(id, rech);
            for (Chapitre c : lChap) {
                
                loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneChapitre.fxml"));
                PaneChapitreController paneController = new PaneChapitreController(c, pc);
                
                loader.setController(paneController);
                try {
                    anch = loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tf.getChildren().add(anch);
            }
            
            paneData.getChildren().addAll(tf);
            
        }
    }
}
