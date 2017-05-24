    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.BibliothequeDAO;
import com.esprit.mooc.DAO.CourDAO;
import com.esprit.mooc.DAO.DisciplineDao;
import com.esprit.mooc.DAO.ForumDAO;
import com.esprit.mooc.Entities.Cours;
import com.esprit.mooc.Entities.Discipline;
import com.esprit.mooc.Entities.Forum;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author Herudi
 */
public class MenuApprenantController implements Initializable {

    @FXML
    Label selectedMenu;
    @FXML
    Label menuTitle;
    @FXML
    private Button close;
    @FXML
    private Button maximize;
    @FXML
    private Button minimize;

    @FXML
    private Button fullscreen;

    Stage stage;
    Rectangle2D rec2;
    Double w, h;

    @FXML
    TextField chercherForumTxt;
    @FXML
    public Pane paneData;

    @FXML
    private Label nomespace;
    @FXML
    Button btnProfil = new Button();
    config2 con = new config2();

    @FXML
    private Button btnLogout;

    @FXML
    private TextField tfFind;

    private Utilisateur loggedUser;

    @FXML
    private Label ApprenantName;
    public AnchorPane anch = new AnchorPane();
    @FXML
    ImageView guideImage;
    public TextFlow tf;
    @FXML
    Button btnListCour = new Button();
    @FXML
    Button btnBibliotheque = new Button();
    @FXML
    Button disciplineBtn = new Button();
    @FXML
    Button forumButton = new Button();
    FXMLLoader loader;

    MenuApprenantController(Utilisateur user) {
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
        chercherForumTxt.setVisible(false);
        tfFind.setVisible(false);
        rec2 = Screen.getPrimary().getVisualBounds();
        w = 0.1;
        h = 0.1;
        tf = new TextFlow();
        tf.setPrefWidth(1090);
//        listMenu.getItems().addAll("Mon Profil","Mes Cours", "  Mes Invitations ","  Liste des \n Entreprises");
        Platform.runLater(() -> {
//            ApprenantName.setText(loggedUser.getUsename());
            stage = (Stage) maximize.getScene().getWindow();
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
            nomespace.setText(loggedUser.getUsename());
            maximize.getStyleClass().add("decoration-button-restore");
            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/MonProfil.fxml"));
            MonProfilController profilFormateurController = new MonProfilController(loggedUser);
            loader.setController(profilFormateurController);
            con.loadPane(paneData, loader);

        });
        forumButton.setOnMouseEntered(myHandler);
        disciplineBtn.setOnMouseEntered(myHandler);
        btnLogout.setOnMouseEntered(myHandler);
        btnProfil.setOnMouseEntered(myHandler);
        btnListCour.setOnMouseEntered(myHandler);
        btnBibliotheque.setOnMouseEntered(myHandler);
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
            } else if (x.getId().equals(btnProfil.getId())) {
                menuTitle.setText("Mon Profil...");
                guideImage.setVisible(true);
            } else if (x.getId().equals(btnListCour.getId())) {
                menuTitle.setText("Liste des cours");
                guideImage.setVisible(true);
            } else if (x.getId().equals(btnBibliotheque.getId())) {
                menuTitle.setText("Favoris");
                guideImage.setVisible(true);
            } else if (x.getId().equals(disciplineBtn.getId())) {
                menuTitle.setText("Nos disciplines");
                guideImage.setVisible(true);
            } else if (x.getId().equals(forumButton.getId())) {
                menuTitle.setText("Nos forums");
                guideImage.setVisible(true);
            } else {
                menuTitle.setText("");
            }
        }
    };

    @FXML
    public void monProfilAction() {
        chercherForumTxt.setVisible(false);
        loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/MonProfil.fxml"));
        MonProfilController profilFormateurController2 = new MonProfilController(loggedUser);
        loader.setController(profilFormateurController2);
        con.loadPane(paneData, loader);

    }

    @FXML
    public void listeCoursAction() throws IOException {
        chercherForumTxt.setVisible(false);
        tfFind.setVisible(true);
        tf.getChildren().clear();
        paneData.getChildren().clear();
        CourDAO courDao = new CourDAO();
        List<Cours> lCour = courDao.displayAllAccepted();
        for (Cours c : lCour) {

            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneCours.fxml"));
            PaneCourController paneController = new PaneCourController(c, 1);

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

    void afficheCour(Cours cour) {
        tf.getChildren().clear();
        paneData.getChildren().clear();

    }

    @FXML
    void maBibliotheque() throws IOException {
        chercherForumTxt.setVisible(false);
        tfFind.setVisible(false);

        tf.getChildren().clear();
        paneData.getChildren().clear();
        BibliothequeDAO bibliothequeDAO = new BibliothequeDAO();
        List<Cours> lCour = bibliothequeDAO.displayAll(loggedUser);
        for (Cours c : lCour) {

            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneCours.fxml"));
            PaneCourController paneController = new PaneCourController(c, 2, this);

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

    public Utilisateur getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Utilisateur loggedUser) {
        this.loggedUser = loggedUser;
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
    public void chercherCours() {

        tf.getChildren().clear();
        paneData.getChildren().clear();
        CourDAO courDao = new CourDAO();
        List<Cours> lCour = courDao.findCours(tfFind.getText());
        for (Cours c : lCour) {

            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneCours.fxml"));
            PaneCourController paneController = new PaneCourController(c, 1);

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

    public void disciplineAction() {
        chercherForumTxt.setVisible(false);
        selectedMenu.setText("Nos Discipline");
        menuTitle.setText("Nos discipline");
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

    public void forumAction() {
        chercherForumTxt.setVisible(true);

        selectedMenu.setText("Nos Forum");
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
}
