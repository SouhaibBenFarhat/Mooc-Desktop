/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Discipline;
import com.esprit.mooc.Entities.Sujet;
import com.esprit.mooc.Entities.Utilisateur;
import com.esprit.mooc.ressources.config2;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author kods
 */
public class PaneDisciplineController implements Initializable {

    @FXML
    Button modifierDisciplineBtn;
    @FXML
    Button afficherDisciplineBtn = new Button();
    @FXML
    private Label datePublication;
    @FXML
    private Label nbCours;
    @FXML
    private Label disciplineName;
    @FXML
    TextFlow tf = new TextFlow();
    @FXML
    Pane paneData;
    @FXML
    private ImageView image;
    public AnchorPane anch = new AnchorPane();

    private UtilisateurDAO userDAO;
    File file;
    Utilisateur user;
    MenuFormateurController menuFormateurController;
    MenuAdminController menuAdminController;
    MenuApprenantController menuApprenantController;
//Utilisateur loggedUser;
    //Alert a = new Alert(Alert.AlertType.INFORMATION);
    Sujet sujet;
    Discipline discipline;

    public PaneDisciplineController(Sujet sujet) {
        this.sujet = sujet;
        userDAO = new UtilisateurDAO();
        user = userDAO.findUtilisateurById(sujet.getUtilisateur());
    }

    PaneDisciplineController(Sujet sujet, MenuFormateurController menuFormateurController) {
        this.sujet = sujet;
        this.menuFormateurController = menuFormateurController;
        userDAO = new UtilisateurDAO();
        user = userDAO.findUtilisateurById(sujet.getUtilisateur());
    }

    PaneDisciplineController(Discipline discipline, MenuFormateurController aThis) {
        this.discipline = discipline;
        this.menuFormateurController = aThis;
    }

    PaneDisciplineController(Discipline discipline, MenuAdminController menuAdminController) {
        this.discipline = discipline;
        this.menuAdminController = menuAdminController;
    }

    PaneDisciplineController(Discipline discipline, MenuApprenantController menuApprenantController) {
        this.discipline = discipline;
        this.menuApprenantController = menuApprenantController;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        modifierDisciplineBtn.setVisible(false);
        if (UtilisateurDAO.loggedUser.getRole().contains("ADMIN")) {
            modifierDisciplineBtn.setVisible(true);
        }
        Platform.runLater(() -> {
            disciplineName.setText(discipline.getNomDiscipline());
            file = new File("C:\\wamp\\www\\MOOCV5\\web\\uploads\\discipline\\logo\\" + discipline.getLogo());
            FileInputStream inputStream;
            try {
                inputStream = new FileInputStream(file);
                image.setImage(new Image(inputStream));
            } catch (FileNotFoundException ex) {
                try {
                    image.setImage(new Image(new FileInputStream(new File("C:\\wamp\\www\\MOOCV5\\web\\uploads\\discipline\\logo\\subject.png"))));
                } catch (FileNotFoundException ex1) {
                    Logger.getLogger(PaneDisciplineController.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }

            nbCours.setText(String.valueOf(discipline.getNombreCours()));
            datePublication.setText(discipline.getDateCreationDiscipline().substring(0, 10));

        });

    }

    public void afficherDiscipline() {
        Parent root;

        Stage stage = new Stage();
        FXMLLoader loader;

        try {

            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneAfficherDiscipline.fxml"));
            PaneAfficherDisciplineController afficherDisciplineController = new PaneAfficherDisciplineController(discipline);
            loader.setController(afficherDisciplineController);
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

    public void modifierDiscipline() {
        Parent root;

        Stage stage = new Stage();
        FXMLLoader loader;

        try {

            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneModifierDiscipline.fxml"));
            PaneModifierDisciplineController paneModifierDisciplineController = new PaneModifierDisciplineController(discipline, this);
            loader.setController(paneModifierDisciplineController);
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

}
