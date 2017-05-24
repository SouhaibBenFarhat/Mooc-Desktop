/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.ForumDAO;
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
public class PaneModifierForumController implements Initializable {

    @FXML
    private Button updateBtn;
    @FXML
    private TextField titreForumTxt;
    @FXML
    private Text lblWelcome;
    @FXML
    private Text lblTitre;
    @FXML
    private Text forumName;
    @FXML
    private Label lblClose;
    @FXML
    private Label label;
    Forum forum = new Forum();
    PaneForumController paneForumController;
    Date date = new Date();
    private java.sql.Date sqlDate = new java.sql.Date(date.getTime());

    public PaneModifierForumController(Forum forum) {
        this.forum = forum;
    }

    PaneModifierForumController(Forum forum, PaneForumController aThis) {
        this.forum = forum;
        this.paneForumController = aThis;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(() -> {
            forumName.setText(forum.getNomForum());
            lblWelcome.setText(forum.getNomForum());
            new FadeInLeftTransition(lblWelcome).play();
            new FadeInLeftTransition1(lblTitre).play();
            new FadeInLeftTransition1(titreForumTxt).play();
            new FadeInRightTransition(updateBtn).play();
            label.setTextFill(Color.web("#FFFFFF"));
            titreForumTxt.setText(forum.getNomForum());
            lblClose.setOnMouseClicked((MouseEvent event) -> {
                Stage stage = (Stage) lblClose.getScene().getWindow();
                paneForumController.updateForumBtn.setVisible(true);
                stage.close();
            });
        });

    }

    @FXML
    public void modifierForumAction(ActionEvent event) {
        boolean etat = true;
        if ("".equals(titreForumTxt.getText())) {
            titreForumTxt.setStyle(" -fx-border-color: red;   -fx-border-width:1;");
            etat = false;
        }

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2),
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        titreForumTxt.setStyle(" -fx-border-color: black, #bababa;");

                    }
                }));
        timeline.play();
        if (etat) {
            ForumDAO forumDAO = new ForumDAO();
            Forum f = forum;
            f.setNomForum(titreForumTxt.getText());
            if (forumDAO.modifierForum(f)) {
                TrayNotification tray = new TrayNotification("Success", "Modification effectuée", Notifications.SUCCESS);
                tray.showAndDismiss(Duration.seconds(2));
                paneForumController.updateForumBtn.setVisible(true);
                paneForumController.menuFormateurController.forumAction();
                Stage stage = (Stage) updateBtn.getScene().getWindow();
                stage.close();

            } else {
                TrayNotification tray = new TrayNotification("Error", "Modification non effectuée", Notifications.ERROR);
                tray.showAndDismiss(Duration.seconds(2));
            }
        }
    }
}
