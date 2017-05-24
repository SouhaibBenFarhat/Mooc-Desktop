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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class PaneModifierDisciplineController implements Initializable {

    @FXML
    private Button modifierBtn;
    @FXML
    TextField txtNomDiscipline = new TextField();
    @FXML
    private TextArea descriptionDiscipline;
    @FXML
    private Text lblWelcome;
    @FXML
    private Text lblDescription;
    @FXML
    private Text lbl;
    @FXML
    private Label lblClose;
    @FXML
    private Label label;
    @FXML
    ImageView image = new ImageView();
    Discipline discipline = new Discipline();
    PaneDisciplineController paneDisciplineController;

    public PaneModifierDisciplineController(Discipline discipline) {
        this.discipline = discipline;
    }

    public PaneModifierDisciplineController(Discipline discipline, PaneDisciplineController paneDisciplineController) {
        this.discipline = discipline;
        this.paneDisciplineController = paneDisciplineController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paneDisciplineController.modifierDisciplineBtn.setVisible(false);
        File file = new File("C:\\wamp\\www\\MOOCV5\\web\\uploads\\discipline\\logo\\" + discipline.getLogo());
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            image.setImage(new Image(inputStream));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PaneDisciplineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Platform.runLater(() -> {
            txtNomDiscipline.setText(discipline.getNomDiscipline());
            lblWelcome.setText(discipline.getNomDiscipline());
            descriptionDiscipline.setText(discipline.getDescriptionDiscipline());
            txtNomDiscipline.setText(discipline.getNomDiscipline());
            descriptionDiscipline.setWrapText(true);
            new FadeInLeftTransition(lblWelcome).play();
            new FadeInLeftTransition(txtNomDiscipline).play();
            new FadeInLeftTransition(image).play();
            new FadeInLeftTransition(lbl).play();
            new FadeInLeftTransition1(lblDescription).play();
            new FadeInLeftTransition1(descriptionDiscipline).play();
            new FadeInRightTransition(modifierBtn).play();
            label.setTextFill(Color.web("#FFFFFF"));
            modifierBtn.setOnMouseClicked((MouseEvent event) -> {
                Stage stage = (Stage) lblClose.getScene().getWindow();
                stage.close();
            });
            lblClose.setOnMouseClicked((MouseEvent event) -> {
                Stage stage = (Stage) lblClose.getScene().getWindow();
                paneDisciplineController.modifierDisciplineBtn.setVisible(true);
                stage.close();
            });
        });

    }

    public void modifierDisciplineAction() {

        boolean etat = true;
        if ("".equals(txtNomDiscipline.getText())) {
            txtNomDiscipline.setStyle(" -fx-border-color: red;   -fx-border-width:1;");
            etat = false;
        }
        if ("".equalsIgnoreCase(descriptionDiscipline.getText())) {
            descriptionDiscipline.setStyle(" -fx-border-color: red;   -fx-border-width:1;");
            etat = false;
        }

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2),
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        descriptionDiscipline.setStyle(" -fx-border-color: black, #bababa;");
                        descriptionDiscipline.setStyle(" -fx-border-color: black, #bababa;");
                    }
                }));
        timeline.play();
        if (etat) {

            DisciplineDao disciplineDao = new DisciplineDao();
            discipline.setNomDiscipline(txtNomDiscipline.getText());
            discipline.setDescriptionDiscipline(descriptionDiscipline.getText());

            if (disciplineDao.modifierDonneDiscipline(discipline)) {
                TrayNotification tray = new TrayNotification("Success", "Modification effectuée", Notifications.SUCCESS);
                tray.showAndDismiss(Duration.seconds(2));
                paneDisciplineController.modifierDisciplineBtn.setVisible(true);
                Stage stage = (Stage) modifierBtn.getScene().getWindow();
                stage.close();
                paneDisciplineController.menuAdminController.disciplineAction();

            } else {
                TrayNotification tray = new TrayNotification("Error", "Modification non effectuée", Notifications.ERROR);
                tray.showAndDismiss(Duration.seconds(2));
            }
        }
    }

}
