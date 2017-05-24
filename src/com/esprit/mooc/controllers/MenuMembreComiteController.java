/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.CourDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Cours;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author Herudi
 */
public class MenuMembreComiteController implements Initializable {

    @FXML
    private Button close;
    @FXML
    private Button maximize;
    @FXML
    private Button minimize;

    @FXML
    private Button fullscreen;
    @FXML
    private Label title;
    Stage stage;
    Rectangle2D rec2;
    Double w, h;
    //@FXML
    //private ListView<String> listMenu;
    @FXML
    public Pane paneData;

    Button btnDemandeEntreprise;
    Button btnEntreprises;
    Button btnProfil;
    config2 con = new config2();
    @FXML
    private Button btnLogout;
    private MenuMembreComiteController parentFormateur;

    private Utilisateur loggedUser;

    @FXML
    private Label ApprenantName;
    public AnchorPane anch = new AnchorPane();

    public TextFlow tf;
    FXMLLoader loader;

    MenuMembreComiteController(Utilisateur user) {
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
            maximize.getStyleClass().add("decoration-button-restore");
            try {
                //            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/MonProfil.fxml"));
//            MonProfilController profilFormateurController = new MonProfilController(loggedUser);
//            loader.setController(profilFormateurController);
//            con.loadPane(paneData, loader);
                this.ListCour();
            } catch (IOException ex) {
                Logger.getLogger(MenuMembreComiteController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (loggedUser.getIdEntrepriseUtilisateur() != 0) {
                btnDemandeEntreprise.setDisable(true);
            }

        });
    }

    @FXML
    public void monProfilAction() {
        loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/MonProfil.fxml"));
        MonProfilController profilFormateurController2 = new MonProfilController(loggedUser);
        loader.setController(profilFormateurController2);
        con.loadPane(paneData, loader);

    }

    @FXML
    void ListFormateur() {
        tf.getChildren().clear();
        paneData.getChildren().clear();
        UtilisateurDAO userDao = new UtilisateurDAO();
       List<Utilisateur> lFormateurs = userDao.findNewFormateurs();
            for (Utilisateur user : lFormateurs) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneFormateur.fxml"));
                PaneFormateurController paneController = new PaneFormateurController(user, this);
                
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

    @FXML
    void ListCour() throws IOException {
        tf.getChildren().clear();
        paneData.getChildren().clear();
        CourDAO courDao = new CourDAO();
        List<Cours> lCour = courDao.displayAllEnAttente();
        for (Cours c : lCour) {

            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneCours.fxml"));
            PaneCourController paneController = new PaneCourController(c, 4, this, 5);

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

}
