/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

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
public class PaneAfficherDisciplineController implements Initializable {

    @FXML
    private Button fermerBtn;
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

    public PaneAfficherDisciplineController(Discipline discipline) {
        this.discipline = discipline;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        File file = new File("C:\\wamp\\www\\MOOCV5\\web\\uploads\\discipline\\logo\\" + discipline.getLogo());
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            image.setImage(new Image(inputStream));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PaneDisciplineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Platform.runLater(() -> {
            lblWelcome.setText(discipline.getNomDiscipline());
            descriptionDiscipline.setText(discipline.getDescriptionDiscipline());
            descriptionDiscipline.setWrapText(true);
            new FadeInLeftTransition(lblWelcome).play();
            new FadeInLeftTransition(image).play();
            new FadeInLeftTransition(lbl).play();
            new FadeInLeftTransition1(lblDescription).play();
            new FadeInLeftTransition1(descriptionDiscipline).play();
            new FadeInRightTransition(fermerBtn).play();
            label.setTextFill(Color.web("#FFFFFF"));
            fermerBtn.setOnMouseClicked((MouseEvent event) -> {
                Stage stage = (Stage) lblClose.getScene().getWindow();
                stage.close();
            });
            lblClose.setOnMouseClicked((MouseEvent event) -> {
                Stage stage = (Stage) lblClose.getScene().getWindow();
                stage.close();
            });
        });

    }

}
