/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.SujetDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Sujet;
import com.esprit.mooc.Entities.Utilisateur;
import com.esprit.mooc.ressources.config2;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
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
 * @author souhaib
 */
public class PaneMesSujetController implements Initializable {

    @FXML
    Button modifierSujetBtn = new Button();
    @FXML
    Button supprimerSujetBtn = new Button();
    @FXML
    Button afficherInformationBtn = new Button();
    @FXML
    private Label datePublication;
    @FXML
    private Label publierPar;
    @FXML
    private Label datepublication;
    @FXML
    private Label nbMessage;
    @FXML
    private Label sujetName;
    @FXML
    TextFlow tf = new TextFlow();
    @FXML
    Pane paneData;
    @FXML
    private ImageView image;
    @FXML
    private Pane paneForum;
    @FXML
    private Button btnAfficherSujet;

    public AnchorPane anch = new AnchorPane();
    @FXML
    private Button btnForum;
    private UtilisateurDAO userDAO;
    File file;
    Utilisateur user;
    MenuFormateurController menuFormateurController;
    MesSujetController mesSujetController;
    Sujet sujet;
    MenuApprenantController menuApprenantController;

    public PaneMesSujetController(Sujet sujet) {
        this.sujet = sujet;
        userDAO = new UtilisateurDAO();
        user = userDAO.findUtilisateurById(sujet.getUtilisateur());
    }

    PaneMesSujetController(Sujet sujet, MenuFormateurController menuFormateurController) {
        this.sujet = sujet;
        this.menuFormateurController = menuFormateurController;
        userDAO = new UtilisateurDAO();
        user = userDAO.findUtilisateurById(sujet.getUtilisateur());
    }

    PaneMesSujetController(Sujet sujet, MenuFormateurController menuFormateurController, MesSujetController mesSujetController) {
        this.sujet = sujet;
        this.menuFormateurController = menuFormateurController;
        userDAO = new UtilisateurDAO();
        user = userDAO.findUtilisateurById(sujet.getUtilisateur());
        this.mesSujetController = mesSujetController;
    }

    PaneMesSujetController(Sujet sujet, MenuApprenantController menuApprenantController, MesSujetController aThis) {
        this.sujet = sujet;
        this.menuApprenantController = menuApprenantController;
        userDAO = new UtilisateurDAO();
        user = userDAO.findUtilisateurById(sujet.getUtilisateur());
        this.mesSujetController = aThis;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        supprimerSujetBtn.setTooltip(new Tooltip("Supprimer" + sujet.getTitre()));
        Platform.runLater(() -> {
            sujetName.setText(sujet.getTitre());

            nbMessage.setText(String.valueOf(sujet.getNombreMessage()));
            publierPar.setText(user.getUsename());
            datePublication.setText(sujet.getDatePublication().substring(0, 10));

        });

    }

    public void afficherSujet() {

        if (UtilisateurDAO.loggedUser.getRole().contains("FORMATEUR")) {
            System.out.println("je marche");
            config2 con = new config2();
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/SujetDetail.fxml"));
            SujetDetailController sujetController = new SujetDetailController(sujet, menuFormateurController);
            loader.setController(sujetController);
            con.loadPane(menuFormateurController.paneData, loader);
        }
        if (UtilisateurDAO.loggedUser.getRole().contains("APPRENANT")) {
            System.out.println("je marche");
            config2 con = new config2();
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/SujetDetail.fxml"));
            SujetDetailController sujetController = new SujetDetailController(sujet, menuApprenantController);
            loader.setController(sujetController);
            con.loadPane(menuApprenantController.paneData, loader);
        }

    }

    public void afficherInformationSujet() {
        mesSujetController.browser.getEngine().loadContent("<b>" + sujet.getSousTitre() + " <br> " + "</b>" + sujet.getDescriptionSujet());
        mesSujetController.titreSujet.setText(sujet.getTitre());
        try {
            mesSujetController.RemplirListMessage(sujet.getId());
        } catch (SQLException ex) {
            Logger.getLogger(PaneMesSujetController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PaneMesSujetController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerSujet() {
        mesSujetController.messageBrowser.getEngine().loadContent("");
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Confirmation Delete");
        alert.setHeaderText("");

        alert.setContentText("Voulez vous supprimer (" + sujet.getTitre() + ")?");
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonData.OK_DONE);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonData.CANCEL_CLOSE);
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
            SujetDAO sujetDao = new SujetDAO();
            if (sujetDao.supprimerSujet(sujet.getId())) {
                TrayNotification tray = new TrayNotification("Success", "Suppression effectuée", Notifications.SUCCESS);
                tray.showAndDismiss(Duration.seconds(2));
                mesSujetController.refreshSubjectList();

            } else {
                TrayNotification tray = new TrayNotification("Success", "Suppression non effectuée", Notifications.ERROR);
                tray.showAndDismiss(Duration.seconds(2));
            }
        }

    }

    public void modifierSujet() {
        Parent root;

        Stage stage = new Stage();
        FXMLLoader loader;

        try {

            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneUpdateSubject.fxml"));
            UpdateSubjectController updateSubjectController = new UpdateSubjectController(sujet, this, mesSujetController);
            loader.setController(updateSubjectController);
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
