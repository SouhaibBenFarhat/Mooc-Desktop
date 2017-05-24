/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.DisciplineDao;
import com.esprit.mooc.DAO.ForumDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Discipline;
import com.esprit.mooc.Entities.Forum;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author Herudi
 */
public class MenuAdminController implements Initializable {

    @FXML
    ImageView menuImage;
    @FXML
    private Button close;
    @FXML
    private Button maximize;
    @FXML
    private Button minimize;
    @FXML
    private Button resize;
    @FXML
    private Button fullscreen;
    @FXML
    private Label title;
    Stage stage;
    Rectangle2D rec2;
    Double w, h;

    @FXML
    public AnchorPane paneData;

    config2 con = new config2();
    @FXML
    private Button btnLogout;

    @FXML
    private Button btnApprenants;
    @FXML
    private Button btnFormateurs;

    @FXML
    private Button btnDisabledFormateurs;
    @FXML
    private Button btnEntreprises;
    @FXML
    private Button btnDemandeInscEntreprise;

    @FXML
    TextField chercherApprenantText;
    @FXML
    TextField chercherFormateurText;
    @FXML
    TextField chercherEntrepriseText;
    @FXML
    Label menuTitle;
    @FXML
    Label selectedMenu;
    @FXML
    Button forumButton = new Button();
    private Utilisateur loggedUser;
    @FXML
    ImageView guideImage;
    public TextFlow tf;
    @FXML
    Button btnDisabledApprenants;
    @FXML
    Button btnDisabledEntreprises;
    ScrollPane scrollPane;
    @FXML
    Button disciplineBtn = new Button();
    @FXML
    Button ajouterDisciplineBtn = new Button();
    @FXML
    Button showStatBtn = new Button();
    @FXML
    Button ajouterForumBtn = new Button();

    public MenuAdminController(Utilisateur loggedUser) {
        this.loggedUser = loggedUser;
    }
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    UtilisateurDAO userDao;
    public AnchorPane anch = new AnchorPane();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedMenu.setText("Liste des formateurs");
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
            resize.setVisible(false);
            userDao = new UtilisateurDAO();
            List<Utilisateur> lFormateurs = userDao.findUtilisateurByRole("ROLE_FORMATEUR");
            for (Utilisateur user : lFormateurs) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneFormateur.fxml"));
                PaneFormateurController paneController = new PaneFormateurController(user, this);
                paneController.setLoggedUser(loggedUser);
                loader.setController(paneController);
                try {
                    anch = loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }

                tf.getChildren().add(anch);
                chercherFormateurText.setVisible(true);
                chercherApprenantText.setVisible(false);
                chercherEntrepriseText.setVisible(false);
            }
            paneData.getChildren().addAll(tf);

        });

        btnLogout.setOnMouseEntered(myHandler);
        btnFormateurs.setOnMouseEntered(myHandler);
        btnDisabledFormateurs.setOnMouseEntered(myHandler);
        btnApprenants.setOnMouseEntered(myHandler);
        btnDisabledApprenants.setOnMouseEntered(myHandler);
        btnEntreprises.setOnMouseEntered(myHandler);
        btnDisabledEntreprises.setOnMouseEntered(myHandler);
        btnDemandeInscEntreprise.setOnMouseEntered(myHandler);

        forumButton.setOnMouseEntered(myHandler);
        disciplineBtn.setOnMouseEntered(myHandler);
        ajouterDisciplineBtn.setOnMouseEntered(myHandler);
        showStatBtn.setOnMouseEntered(myHandler);
        ajouterDisciplineBtn.setOnMouseEntered(myHandler);
        ajouterForumBtn.setOnMouseEntered(myHandler);

        paneData.setOnMouseEntered(paneHandler);

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
            if (x.getId().equals(btnLogout.getId())) {
                menuTitle.setText("Quitter");
                guideImage.setVisible(true);
            } else if (x.getId().equals(btnFormateurs.getId())) {
                menuTitle.setText("Formateurs...");
                guideImage.setVisible(true);
            } else if (x.getId().equals(btnDisabledFormateurs.getId())) {
                menuTitle.setText("Formateur bloqués");
                guideImage.setVisible(true);
            } else if (x.getId().equals(btnApprenants.getId())) {
                menuTitle.setText("Apprenant");
                guideImage.setVisible(true);
            } else if (x.getId().equals(btnDisabledApprenants.getId())) {
                menuTitle.setText("Apprenant bloqués");
                guideImage.setVisible(true);
            } else if (x.getId().equals(btnEntreprises.getId())) {
                menuTitle.setText("Entreprises");
                guideImage.setVisible(true);
            } else if (x.getId().equals(btnDisabledEntreprises.getId())) {
                menuTitle.setText("Entreprise bloquées");
                guideImage.setVisible(true);
            } else if (x.getId().equals(btnDemandeInscEntreprise.getId())) {
                menuTitle.setText("Demandes d'inscription");
                guideImage.setVisible(true);
            } else if (x.getId().equals(ajouterDisciplineBtn.getId())) {
                menuTitle.setText("Lancer une discipline");
                guideImage.setVisible(true);
            } else if (x.getId().equals(forumButton.getId())) {
                menuTitle.setText("Nos forums");
                guideImage.setVisible(true);
            } else if (x.getId().equals(disciplineBtn.getId())) {
                menuTitle.setText("Nos discipline");
                guideImage.setVisible(true);
            } else if (x.getId().equals(ajouterForumBtn.getId())) {
                menuTitle.setText("Lancer Forum");
                guideImage.setVisible(true);
            } else if (x.getId().equals(ajouterDisciplineBtn.getId())) {
                menuTitle.setText("Lancer discipline");
                guideImage.setVisible(true);
            } else if (x.getId().equals(showStatBtn.getId())) {
                menuTitle.setText("Voir statistique");
                guideImage.setVisible(true);
            } else {
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
                resize.setVisible(true);
            } else {
                stage.setMaximized(false);
                maximize.getStyleClass().remove("decoration-button-restore");
                resize.setVisible(true);
            }

        } else {
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
            maximize.getStyleClass().add("decoration-button-restore");
            resize.setVisible(false);
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
    private void menuResize(ActionEvent event) {
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
    public void listeFormateursAction() throws IOException {
        selectedMenu.setText("Liste des Formateurs");
        menuImage.setVisible(true);
        tf.getChildren().clear();
        paneData.getChildren().clear();
        scrollPane = new ScrollPane();
        userDao = new UtilisateurDAO();
        List<Utilisateur> lFormateurs = userDao.findUtilisateurByRole("ROLE_FORMATEUR");
        for (Utilisateur user : lFormateurs) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneFormateur.fxml"));
            PaneFormateurController paneController = new PaneFormateurController(user, this);
            paneController.setLoggedUser(loggedUser);
            loader.setController(paneController);
            try {
                anch = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tf.getChildren().add(anch);
        }
        //paneData.getContent().se
        paneData.getChildren().add(tf);
        scrollPane.setContent(paneData);
        chercherFormateurText.setVisible(true);
        chercherApprenantText.setVisible(false);
        chercherEntrepriseText.setVisible(false);

    }

    @FXML
    public void listeDisabledFormateursAction() throws IOException {
        selectedMenu.setText("Liste des Formateurs Bloqués");
        menuImage.setVisible(true);
        paneData.getChildren().clear();
        scrollPane = new ScrollPane();
        UtilisateurDAO userDao = new UtilisateurDAO();
        List<Utilisateur> lDisabledFormateurs = userDao.findDisabledUtilisateurByRole("ROLE_FORMATEUR");
        tf.getChildren().clear();
        for (Utilisateur user : lDisabledFormateurs) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneFormateur.fxml"));
            PaneFormateurController paneController = new PaneFormateurController(user, this);
            paneController.setLoggedUser(loggedUser);
            loader.setController(paneController);
            try {
                anch = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tf.getChildren().add(anch);
        }
        //paneData.getContent().se

        paneData.getChildren().add(tf);
        scrollPane.setContent(paneData);
        chercherFormateurText.setVisible(false);
        chercherApprenantText.setVisible(false);
        chercherEntrepriseText.setVisible(false);

    }

    @FXML
    public void listeApprenantsAction() throws IOException {

        selectedMenu.setText("Liste des Apprenants");
        menuImage.setVisible(true);
        tf.getChildren().clear();
        paneData.getChildren().clear();
        scrollPane = new ScrollPane();
        userDao = new UtilisateurDAO();
        List<Utilisateur> lApprenant = userDao.findUtilisateurByRole("ROLE_APPRENANT");
        for (Utilisateur user : lApprenant) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneApprenant.fxml"));
            PaneApprenantController paneController = new PaneApprenantController(user, this);
            paneController.setLoggedUser(loggedUser);
            loader.setController(paneController);
            try {
                anch = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tf.getChildren().add(anch);
        }
        // paneData.backgroundProanchperty().setValue(Background.EMPTY);

        paneData.getChildren().add(tf);
        scrollPane.setContent(paneData);
        chercherFormateurText.setVisible(false);
        chercherApprenantText.setVisible(true);
        chercherEntrepriseText.setVisible(false);

    }

    @FXML
    public void listeDisabledApprenantsAction() throws IOException {

        selectedMenu.setText("Liste des Apprenants Bloqués");
        menuImage.setVisible(true);
        tf.getChildren().clear();
        paneData.getChildren().clear();
        scrollPane = new ScrollPane();
        userDao = new UtilisateurDAO();
        List<Utilisateur> lDisabledApprenants = userDao.findDisabledUtilisateurByRole("ROLE_APPRENANT");
        for (Utilisateur user : lDisabledApprenants) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneApprenant.fxml"));
            PaneApprenantController paneController = new PaneApprenantController(user, this);
            paneController.setLoggedUser(loggedUser);
            loader.setController(paneController);
            try {
                anch = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }

            tf.getChildren().add(anch);
        }

        paneData.getChildren().add(tf);
        scrollPane.setContent(paneData);
        chercherFormateurText.setVisible(false);
        chercherApprenantText.setVisible(false);
        chercherEntrepriseText.setVisible(false);

    }

    @FXML
    public void listeEntreprisesAction() throws IOException {
        selectedMenu.setText("Liste des Entreprise");
        menuImage.setVisible(true);
        tf.getChildren().clear();
        paneData.getChildren().clear();
        scrollPane = new ScrollPane();
        userDao = new UtilisateurDAO();
        List<Utilisateur> lEntreprises = userDao.findUtilisateurByRole("ROLE_ENTREPRISE");
        for (Utilisateur user : lEntreprises) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneEntreprise.fxml"));
            PaneEntrepriseController paneController = new PaneEntrepriseController(user, this);
            paneController.setLoggedUser(loggedUser);
            loader.setController(paneController);
            try {
                anch = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tf.getChildren().add(anch);
        }

        paneData.getChildren().add(tf);
        scrollPane.setContent(paneData);
        chercherFormateurText.setVisible(false);
        chercherApprenantText.setVisible(false);
        chercherEntrepriseText.setVisible(true);

    }

    @FXML
    public void listeDisabledEntreprisesAction() throws IOException {
        selectedMenu.setText("Liste des Entreprises Bloquées");
        menuImage.setVisible(true);
        tf.getChildren().clear();
        paneData.getChildren().clear();
        scrollPane = new ScrollPane();
        userDao = new UtilisateurDAO();
        List<Utilisateur> lDisabledEntreprises = userDao.findDisabledUtilisateurByRole("ROLE_ENTREPRISE");
        for (Utilisateur user : lDisabledEntreprises) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneEntreprise.fxml"));
            PaneEntrepriseController paneController = new PaneEntrepriseController(user, this);
            paneController.setLoggedUser(loggedUser);
            loader.setController(paneController);
            try {
                anch = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tf.getChildren().add(anch);
        }

        paneData.getChildren().add(tf);
        scrollPane.setContent(paneData);
        chercherFormateurText.setVisible(false);
        chercherApprenantText.setVisible(false);
        chercherEntrepriseText.setVisible(false);

    }

    @FXML
    public void listeDemandesAction() throws IOException {
        selectedMenu.setText("Liste des Demandes");
        menuImage.setVisible(true);
        tf.getChildren().clear();
        paneData.getChildren().clear();
        userDao = new UtilisateurDAO();
        scrollPane = new ScrollPane();
        List<Utilisateur> lDemandeEntreprises = userDao.findlockedEntreprises();
        for (Utilisateur user : lDemandeEntreprises) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneEntreprise.fxml"));
            PaneEntrepriseController paneController = new PaneEntrepriseController(user, this);
//                paneController.getBtnUnlock().setVisible(false);
            paneController.setLoggedUser(loggedUser);
            loader.setController(paneController);
            try {
                anch = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tf.getChildren().add(anch);
        }

        paneData.getChildren().add(tf);
        scrollPane.setContent(paneData);
        chercherFormateurText.setVisible(false);
        chercherApprenantText.setVisible(false);
        chercherEntrepriseText.setVisible(false);

    }

    @FXML
    public void rechercherEntrepriseAction() {
        userDao = new UtilisateurDAO();
        paneData.getChildren().clear();
        scrollPane = new ScrollPane();
        List<Utilisateur> list = userDao.findEntrepriseByName(chercherEntrepriseText.getText());
        tf.getChildren().clear();
        for (Utilisateur user : list) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneEntreprise.fxml"));
            PaneEntrepriseController paneController = new PaneEntrepriseController(user, this);
            paneController.setLoggedUser(loggedUser);
            loader.setController(paneController);
            try {
                anch = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tf.getChildren().add(anch);
        }

        paneData.getChildren().add(tf);
        chercherFormateurText.setVisible(false);
        chercherApprenantText.setVisible(false);
        chercherEntrepriseText.setVisible(true);

    }

    @FXML
    public void rechercherApprenantAction() {
        paneData.getChildren().clear();
        userDao = new UtilisateurDAO();
        scrollPane = new ScrollPane();
        tf.getChildren().clear();
        List<Utilisateur> list = userDao.findApprenantByName(chercherApprenantText.getText());

        for (Utilisateur user : list) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneApprenant.fxml"));
            PaneApprenantController paneController = new PaneApprenantController(user, this);
            paneController.setLoggedUser(loggedUser);
            loader.setController(paneController);
            try {
                anch = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tf.getChildren().add(anch);
        }

        paneData.getChildren().add(tf);
        chercherFormateurText.setVisible(false);
        chercherApprenantText.setVisible(true);
        chercherEntrepriseText.setVisible(false);

    }

    @FXML
    public void rechercherFormateurAction() {
        userDao = new UtilisateurDAO();
        scrollPane = new ScrollPane();
        paneData.getChildren().clear();
        List<Utilisateur> list = userDao.findFormateurByName(chercherFormateurText.getText());
        tf.getChildren().clear();
        for (Utilisateur user : list) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneFormateur.fxml"));
            PaneFormateurController paneController = new PaneFormateurController(user, this);
            paneController.setLoggedUser(loggedUser);
            loader.setController(paneController);
            try {
                anch = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tf.getChildren().add(anch);
        }

        paneData.getChildren().add(tf);
        chercherFormateurText.setVisible(true);
        chercherApprenantText.setVisible(false);
        chercherEntrepriseText.setVisible(false);

    }

    public Utilisateur getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Utilisateur loggedUser) {
        this.loggedUser = loggedUser;
    }

    @FXML
    public void forumAction() {
        chercherFormateurText.setVisible(false);
        selectedMenu.setText("Nos Forum");
        menuImage.setVisible(true);
        tf.getChildren().clear();
        paneData.getChildren().clear();
        ForumDAO forumDAO = new ForumDAO();
        List<Forum> lForum = forumDAO.findAllForums();
        for (Forum forum : lForum) {
            FXMLLoader loader;
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

    public void showStat() {

        Parent root;
        Stage stage = new Stage();
        FXMLLoader loader;

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

    public void ajouterForum() {

        Parent root;
        Stage stage = new Stage();
        FXMLLoader loader;

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

        Parent root;

        Stage stage = new Stage();
        FXMLLoader loader;

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
        menuImage.setVisible(true);
        menuTitle.setText("Nos discipline");
        tf.getChildren().clear();
        paneData.getChildren().clear();
        DisciplineDao disciplineDao = new DisciplineDao();
        List<Discipline> lDiscipline = disciplineDao.findAllDisciplineObject();

        for (Discipline discipline : lDiscipline) {
            FXMLLoader loader;
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
}
