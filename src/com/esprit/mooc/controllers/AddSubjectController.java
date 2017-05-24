/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.SujetDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Forum;
import com.esprit.mooc.Entities.Sujet;
import com.esprit.mooc.Entities.Utilisateur;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import ressources.FadeInLeftTransition;
import ressources.FadeInLeftTransition1;
import ressources.FadeInRightTransition;

/**
 *
 * @author kods
 */
public class AddSubjectController implements Initializable {

    @FXML
    TextArea contenuSujet = new TextArea();
    @FXML
    private Button btnAjout;
    @FXML
    private TextField txtTitreSujet;
    @FXML
    private TextField txtSousTitreSujet;
    @FXML
    private Text lblText;
    @FXML
    private Text lblWelcome;
    @FXML
    private Text lblUserLogin;
    @FXML
    private Text lblUsername;
    @FXML
    private Text lblPassword;

    @FXML
    private Text forumName;
    @FXML
    private Label lblClose;
    @FXML
    private Label label;

    @FXML
    private Label inscrireLabel;
    Forum forum = new Forum();
    PaneForumController paneForumController;
    Date date = new Date();
    private java.sql.Date sqlDate = new java.sql.Date(date.getTime());

    public AddSubjectController(Forum forum) {
        this.forum = forum;
    }

    AddSubjectController(Forum forum, PaneForumController aThis) {
        this.forum = forum;
        this.paneForumController = aThis;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contenuSujet.setWrapText(true);
        Platform.runLater(() -> {
            forumName.setText(forum.getNomForum());

            new FadeInLeftTransition(contenuSujet).play();
            new FadeInLeftTransition(lblWelcome).play();
            new FadeInLeftTransition1(lblPassword).play();
            new FadeInLeftTransition1(lblUsername).play();
            new FadeInLeftTransition1(txtTitreSujet).play();
            new FadeInLeftTransition1(txtSousTitreSujet).play();
            new FadeInRightTransition(btnAjout).play();
            label.setTextFill(Color.web("#FFFFFF"));

            lblClose.setOnMouseClicked((MouseEvent event) -> {
                Stage stage = (Stage) lblClose.getScene().getWindow();
                paneForumController.ajouterSujet.setVisible(true);
                stage.close();
            });
        });

    }

    @FXML
    public void publierSujetAction(ActionEvent event) throws IOException {
        boolean etat = true;
        if ("".equals(txtSousTitreSujet.getText())) {
            txtSousTitreSujet.setStyle(" -fx-border-color: red;   -fx-border-width:1;");
            etat = false;
        }
        if ("".equalsIgnoreCase(txtTitreSujet.getText())) {
            txtTitreSujet.setStyle(" -fx-border-color: red;   -fx-border-width:1;");
            etat = false;
        }
        if ("".equalsIgnoreCase(contenuSujet.getText())) {
            contenuSujet.setStyle(" -fx-border-color: red;   -fx-border-width:1;");
            etat = false;
        }
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2),
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        txtSousTitreSujet.setStyle(" -fx-border-color: black, #bababa;");
                        txtTitreSujet.setStyle(" -fx-border-color: black, #bababa;");
                        contenuSujet.setStyle(" -fx-border-color: black, #bababa;");
                    }
                }));
        timeline.play();
        if (etat) {

            SujetDAO sujetDao = new SujetDAO();
            Sujet sujet = new Sujet();
            sujet.setAimeSujet(0);
            sujet.setDateModification(null);
            sujet.setDatePublication(sqlDate.toString());
            sujet.setDescriptionSujet(contenuSujet.getText());
            sujet.setEtatSujet(0);
            sujet.setForum(forum.getId());
            sujet.setNombreMessage(0);
            sujet.setSousTitre(txtSousTitreSujet.getText());
            sujet.setTitre(txtTitreSujet.getText());
            sujet.setUtilisateur(UtilisateurDAO.loggedUser.getId());

            if (sujetDao.ajouterSujet(sujet)) {
                TrayNotification tray = new TrayNotification("Success", "Ajout effectuée", Notifications.SUCCESS);
                tray.showAndDismiss(Duration.seconds(2));
                paneForumController.ajouterSujet.setVisible(true);
                Stage stage = (Stage) btnAjout.getScene().getWindow();
                stage.close();

            } else {
                TrayNotification tray = new TrayNotification("Error", "Ajout non effectuée", Notifications.ERROR);
                tray.showAndDismiss(Duration.seconds(2));
            }
        }
    }
}
