/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.DisciplineDao;
import com.esprit.mooc.DAO.ForumDAO;
import com.esprit.mooc.DAO.SujetDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Discipline;
import com.esprit.mooc.Entities.Forum;
import com.esprit.mooc.Entities.Sujet;
import com.esprit.mooc.Entities.Utilisateur;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class PaneAddForumController implements Initializable {

    @FXML
    Text messageTxt = new Text();
    @FXML
    ComboBox<Discipline> disciplineCombo;
    @FXML
    private Button btnAjout;
    @FXML
    private TextField txtTitreForum;
    @FXML
    private Text lblWelcome;
    @FXML
    private Text lblTitreForum;
    @FXML
    private Text forumName;
    @FXML
    private Label lblClose;
    @FXML
    private Label label;
    List<Discipline> disciplines = new ArrayList<>();
    private ObservableList<Discipline> oListDisciplineDisponible;
    Forum forum;
    MenuFormateurController menuFormateurController;
    Date date = new Date();
    private java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    MenuAdminController menuAdminController;

    PaneAddForumController(Forum forum, MenuFormateurController aThis) {
        this.forum = forum;
        this.menuFormateurController = aThis;
    }

    PaneAddForumController(MenuFormateurController menuFormateurController) {
        this.menuFormateurController = menuFormateurController;
    }

    PaneAddForumController(MenuAdminController menuAdminController) {
        this.menuAdminController = menuAdminController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        messageTxt.setVisible(false);
        int x = 0;
        DisciplineDao disciplineDAO = new DisciplineDao();
        for (Discipline d : disciplineDAO.findAllDisciplineObject()) {
            if ("0".equals(d.getForumDiscipline().toString())) {
                disciplines.add(d);
                x = x + 1;
            }
        }
        if (x == 0) {
            messageTxt.setVisible(true);
        }

        Platform.runLater(() -> {
            oListDisciplineDisponible = FXCollections.observableArrayList(disciplines);
            disciplineCombo.setItems(oListDisciplineDisponible);
            new FadeInLeftTransition(lblWelcome).play();
            new FadeInLeftTransition1(lblTitreForum).play();
            new FadeInLeftTransition1(txtTitreForum).play();
            new FadeInLeftTransition1(forumName).play();
            new FadeInRightTransition(btnAjout).play();
            label.setTextFill(Color.web("#FFFFFF"));

            lblClose.setOnMouseClicked((MouseEvent event) -> {
                Stage stage = (Stage) lblClose.getScene().getWindow();
                stage.close();
            });
        });

    }

    @FXML
    public void publierForumAction(ActionEvent event) throws IOException {
        boolean etat = true;

        if ("".equalsIgnoreCase(txtTitreForum.getText())) {
            txtTitreForum.setStyle(" -fx-border-color: red;   -fx-border-width:1;");
            etat = false;
        }
        if (disciplineCombo.getSelectionModel().getSelectedItem() == null) {
            disciplineCombo.setStyle(" -fx-border-color: red;   -fx-border-width:1;");
            etat = false;
        }

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2),
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        txtTitreForum.setStyle(" -fx-border-color: black, #bababa;");
                        disciplineCombo.setStyle(" -fx-border-color: black, #bababa;");

                    }
                }));
        timeline.play();
        if (etat) {
            ForumDAO forumDAO = new ForumDAO();
            DisciplineDao disciplineDao = new DisciplineDao();
            Forum f = new Forum();
            f.setDateCreation(sqlDate.toString());
            f.setDiscipline(disciplineCombo.getSelectionModel().getSelectedItem().getId());
            System.out.println(disciplineCombo.getItems().get(disciplineCombo.getSelectionModel().getSelectedIndex()).getId());
            f.setNomForum(txtTitreForum.getText());
            f.setNombreSujet(0);
            f.setNombreVue(0);

            if (forumDAO.ajouterForum(f)) {
                Discipline d = disciplineCombo.getSelectionModel().getSelectedItem();
                d.setForumDiscipline(forumDAO.getTheLastId());
                if (disciplineDao.modifierDiscipline(d)) {
                    TrayNotification tray = new TrayNotification("Success", "Ajout effectuée", Notifications.SUCCESS);
                    tray.showAndDismiss(Duration.seconds(2));

                    menuAdminController.forumAction();
                    disciplineCombo.getItems().clear();
                    Stage stage = (Stage) btnAjout.getScene().getWindow();
                    stage.close();
                }
            } else {
                TrayNotification tray = new TrayNotification("Error", "Ajout non effectuée", Notifications.ERROR);
                tray.showAndDismiss(Duration.seconds(2));
            }
        }
    }
}
