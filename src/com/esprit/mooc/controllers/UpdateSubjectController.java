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
public class UpdateSubjectController implements Initializable {

    @FXML
    Text txtModification = new Text();
    @FXML
    TextArea contenuSujet = new TextArea();
    @FXML
    private Button btnModification;
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
    private Label lblClose;
    @FXML
    private Label label;
    MesSujetController mesSujetController;
    Sujet sujet;
    PaneMesSujetController paneMesSujetController;
    Forum forum = new Forum();
    PaneForumController paneForumController;
    Date date = new Date();
    private java.sql.Date sqlDate = new java.sql.Date(date.getTime());

    public UpdateSubjectController(Forum forum) {
        this.forum = forum;
    }

    UpdateSubjectController(Forum forum, PaneForumController aThis) {
        this.forum = forum;
        this.paneForumController = aThis;
    }

    public UpdateSubjectController(Sujet sujet, PaneMesSujetController paneMesSujetController) {
        this.paneMesSujetController = paneMesSujetController;
        this.sujet = sujet;

    }

    public UpdateSubjectController(Sujet sujet, PaneMesSujetController paneMesSujetController, MesSujetController mesSujetController) {
        this.paneMesSujetController = paneMesSujetController;
        this.sujet = sujet;
        this.mesSujetController = mesSujetController;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblWelcome.setText(sujet.getTitre());
        txtTitreSujet.setText(sujet.getTitre());
        txtSousTitreSujet.setText(sujet.getSousTitre());
        contenuSujet.setText(sujet.getDescriptionSujet());
        contenuSujet.setWrapText(true);
        Platform.runLater(() -> {

            new FadeInLeftTransition(contenuSujet).play();
            new FadeInLeftTransition(lblWelcome).play();
            new FadeInLeftTransition1(lblPassword).play();
            new FadeInLeftTransition1(lblUsername).play();
            new FadeInLeftTransition1(txtTitreSujet).play();
            new FadeInLeftTransition1(txtSousTitreSujet).play();
            new FadeInRightTransition(btnModification).play();
            new FadeInRightTransition(txtModification).play();
            label.setTextFill(Color.web("#FFFFFF"));

            lblClose.setOnMouseClicked((MouseEvent event) -> {
                Stage stage = (Stage) lblClose.getScene().getWindow();
                stage.close();
            });
        });

    }

    @FXML
    public void modifierSujetAction(ActionEvent event) throws IOException {
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

            sujet.setTitre(txtTitreSujet.getText());
            sujet.setSousTitre(txtSousTitreSujet.getText());
            sujet.setDescriptionSujet(contenuSujet.getText());
            sujet.setDateModification(sqlDate.toString());
            sujet.setEtatSujet(1);

            if (sujetDao.modifierSujet(sujet)) {
                TrayNotification tray = new TrayNotification("Success", "Modification effectuée", Notifications.SUCCESS);
                tray.showAndDismiss(Duration.seconds(2));
                Stage stage = (Stage) btnModification.getScene().getWindow();
                mesSujetController.refreshSubjectList();
                stage.close();

            } else {
                TrayNotification tray = new TrayNotification("Error", "Modification non effectuée", Notifications.ERROR);
                tray.showAndDismiss(Duration.seconds(2));
            }
        }
    }
}
