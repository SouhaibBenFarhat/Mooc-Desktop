/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.DisciplineDao;
import com.esprit.mooc.DAO.ForumDAO;
import com.esprit.mooc.DAO.InvitationFormateurEntrepriseDAO;
import com.esprit.mooc.DAO.SujetDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Discipline;
import com.esprit.mooc.Entities.Forum;
import com.esprit.mooc.Entities.InvitationFormateurEntreprise;
import com.esprit.mooc.Entities.Sujet;
import com.esprit.mooc.Entities.Utilisateur;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author kods
 */
public class PaneForumController implements Initializable {

    @FXML
    Button supprimerForumBtn = new Button();
    @FXML
    Button updateForumBtn = new Button();
    @FXML
    Button ajouterSujet = new Button();
    @FXML
    private Label date;
    @FXML
    private Label nombreVue;
    @FXML
    private Label forumName;
    @FXML
    TextFlow tf = new TextFlow();
    @FXML
    Pane paneData;
    @FXML
    private ImageView image;
    @FXML
    private Pane paneForum;

    public AnchorPane anch = new AnchorPane();
    @FXML
    private Button btnForum;
    Discipline discipline;
    DisciplineDao disciplineDao = new DisciplineDao();

    File file;
//Utilisateur loggedUser;
    //Alert a = new Alert(Alert.AlertType.INFORMATION);
    Forum forum;
    ForumDAO forumDAO = new ForumDAO();
    MenuFormateurController menuFormateurController;
    MenuAdminController menuAdminController;
    MenuApprenantController menuApprenantController;

    public PaneForumController(Forum forum) {
        this.forum = forum;

    }

    PaneForumController(Forum forum, MenuFormateurController aThis) {
        this.forum = forum;
        menuFormateurController = aThis;

    }

    PaneForumController(Forum forum, MenuAdminController aThis) {
        this.forum = forum;
        menuAdminController = aThis;

    }

    PaneForumController(Forum forum, MenuApprenantController aThis) {
        this.forum = forum;
        menuApprenantController = aThis;

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        updateForumBtn.setVisible(false);
        supprimerForumBtn.setVisible(false);
        if (UtilisateurDAO.loggedUser.getRole().contains("ADMIN")) {
            updateForumBtn.setVisible(true);
            supprimerForumBtn.setVisible(true);
        }
        if (UtilisateurDAO.loggedUser.getRole().contains("ADMIN")) {
            ajouterSujet.setVisible(false);
            btnForum.setVisible(false);

        }
        updateForumBtn.setTooltip(new Tooltip("Modifier Forum" + forum.getNomForum()));
        ajouterSujet.setTooltip(new Tooltip("Publier Un Sujet"));
        Platform.runLater(() -> {
            forumName.setText(forum.getNomForum());
            nombreVue.setText(String.valueOf(forum.getNombreVue()));
            date.setText(forum.getDateCreation().substring(0, 10));
            System.out.println(forum.getDiscipline());
            discipline = disciplineDao.findDisciplineById(forum.getDiscipline());
            System.out.println(discipline.toString());
            try {

                file = new File("C:\\wamp\\www\\MOOCV5\\web\\uploads\\discipline\\logo\\" + discipline.getLogo());
                System.out.println(discipline.getLogo() + "aaaaaaaaa");
                FileInputStream inputStream = new FileInputStream(file);
                image.setImage(new Image(inputStream));

            } catch (FileNotFoundException ex) {
                try {
                    image.setImage(new Image(new FileInputStream(new File("C:\\wamp\\www\\MOOCV5\\web\\uploads\\discipline\\logo\\warning-icon.png"))));
                } catch (FileNotFoundException ex1) {
                    Logger.getLogger(PaneForumController.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }

        });

    }

    @FXML
    public void afficherSujets() {
        if (UtilisateurDAO.loggedUser.getRole().contains("FORMATEUR")) {
            menuFormateurController.selectedMenu.setText(forum.getNomForum());
            menuFormateurController.paneButton.setVisible(false);
            Forum f;
            f = forumDAO.findForumById(forum.getId());
            f.setNombreVue(f.getNombreVue() + 1);
            forumDAO.modifierForum(f);
            menuFormateurController.chercherForumTxt.setVisible(false);
            FXMLLoader loader;
            tf.getChildren().clear();
            tf.setPrefWidth(1090);
            menuFormateurController.paneData.getChildren().clear();
            SujetDAO sujetDAO = new SujetDAO();
            List<Sujet> lSujet = sujetDAO.findAllSujets(forum.getId());
            for (Sujet sujet : lSujet) {

                loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneSujet.fxml"));
                PaneSujetController paneController = new PaneSujetController(sujet, menuFormateurController);

                loader.setController(paneController);
                try {
                    anch = loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tf.getChildren().add(anch);
            }

            menuFormateurController.paneData.getChildren().addAll(tf);
        }
        if (UtilisateurDAO.loggedUser.getRole().contains("APPRENANT")) {
            menuApprenantController.selectedMenu.setText(forum.getNomForum());
            Forum f;
            f = forumDAO.findForumById(forum.getId());
            f.setNombreVue(f.getNombreVue() + 1);
            forumDAO.modifierForum(f);
            FXMLLoader loader;
            tf.getChildren().clear();
            tf.setPrefWidth(1090);
            menuApprenantController.paneData.getChildren().clear();
            SujetDAO sujetDAO = new SujetDAO();
            List<Sujet> lSujet = sujetDAO.findAllSujets(forum.getId());
            for (Sujet sujet : lSujet) {

                loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneSujet.fxml"));
                PaneSujetController paneController = new PaneSujetController(sujet, menuApprenantController);

                loader.setController(paneController);
                try {
                    anch = loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tf.getChildren().add(anch);
            }

            menuApprenantController.paneData.getChildren().addAll(tf);
        }
        if (UtilisateurDAO.loggedUser.getRole().contains("ADMIN")) {
            menuFormateurController.selectedMenu.setText(forum.getNomForum());
            menuFormateurController.paneButton.setVisible(false);
            Forum f;
            f = forumDAO.findForumById(forum.getId());
            f.setNombreVue(f.getNombreVue() + 1);
            forumDAO.modifierForum(f);
            menuFormateurController.chercherForumTxt.setVisible(false);
            FXMLLoader loader;
            tf.getChildren().clear();
            tf.setPrefWidth(1090);
            menuFormateurController.paneData.getChildren().clear();
            SujetDAO sujetDAO = new SujetDAO();
            List<Sujet> lSujet = sujetDAO.findAllSujets(forum.getId());
            for (Sujet sujet : lSujet) {

                loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneSujet.fxml"));
                PaneSujetController paneController = new PaneSujetController(sujet, menuFormateurController);

                loader.setController(paneController);
                try {
                    anch = loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tf.getChildren().add(anch);
            }

            menuFormateurController.paneData.getChildren().addAll(tf);
        }

    }

    public void ajouterSujet() {
        Parent root;

        Stage stage = new Stage();
        FXMLLoader loader;
        ajouterSujet.setVisible(false);
        try {

            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/AddSubject.fxml"));
            AddSubjectController addSubjectController = new AddSubjectController(forum, this);
            loader.setController(addSubjectController);
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

    public void modifierForum() {
        Parent root;

        Stage stage = new Stage();
        FXMLLoader loader;
        updateForumBtn.setVisible(false);
        try {

            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneModifierForum.fxml"));
            PaneModifierForumController modifierForumController = new PaneModifierForumController(forum, this);
            loader.setController(modifierForumController);
            root = loader.load();
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(PaneForumController.class.getName()).log(Level.SEVERE, null, ex);
        }

        stage.show();
    }

    public void supprimerForum() {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirmation Suppression");
        alert.setHeaderText("");

        alert.setContentText("Voulez vous supprimer (" + forum.getNomForum() + ")?");
        ButtonType buttonTypeYes = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setX(920);
        alert.setY(620);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/com/esprit/mooc/css/myDailog.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        ButtonBar buttonBar = (ButtonBar) alert.getDialogPane().lookup(".button-bar");
        buttonBar.setStyle("-fx-font-size: 10px;"
                + "-fx-background-color:  #0097A7;");
        buttonBar.getButtons().forEach(b -> b.setStyle("-fx-font-family: \"System\"; -fx-font-weight: bold;-fx-font-size: 10px;"));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            ForumDAO forumDao = new ForumDAO();
            if (forumDao.supprimerForum(forum.getId())) {
                TrayNotification tray = new TrayNotification("Success", "Suppression effectuée", Notifications.SUCCESS);
                tray.showAndDismiss(Duration.seconds(2));
                menuAdminController.forumAction();

            } else {
                TrayNotification tray = new TrayNotification("Success", "Suppression non effectuée", Notifications.ERROR);
                tray.showAndDismiss(Duration.seconds(2));
            }
        }

    }

}
