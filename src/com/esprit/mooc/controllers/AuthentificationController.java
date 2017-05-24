/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Utilisateur;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
public class AuthentificationController implements Initializable {

    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;

    @FXML
    private Text lblWelcome;
    @FXML
    private Text lblUserLogin;
    @FXML
    private Text lblUsername;
    @FXML
    private Text lblPassword;

    @FXML
    private Text lblRudyCom;
    @FXML
    private Label lblClose;
    @FXML
    private Label label;

    @FXML
    private Label inscrireLabel;
    private TrayNotification tray;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(() -> {
            new FadeInRightTransition(lblUserLogin).play();
            new FadeInLeftTransition(lblWelcome).play();
            new FadeInLeftTransition1(lblPassword).play();
            new FadeInLeftTransition1(lblUsername).play();
            new FadeInLeftTransition1(txtUsername).play();
            new FadeInLeftTransition1(txtPassword).play();
            new FadeInRightTransition(btnLogin).play();
            inscrireLabel.setTextFill(Color.web("#FFFFFF"));
            new FadeInRightTransition(inscrireLabel).play();
            label.setTextFill(Color.web("#FFFFFF"));

            lblClose.setOnMouseClicked((MouseEvent event) -> {
                Platform.exit();
                System.exit(0);
            });
            inscrireLabel.setOnMouseClicked((MouseEvent event) -> {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                Stage stage = new Stage();
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("/com/esprit/mooc/moocfx_1/MenuInscription.fxml"));

                    Scene scene = new Scene(root);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(scene);

                    stage.show();

                } catch (IOException ex) {
                    Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        });

    }

    @FXML
    public void authentificationAction(ActionEvent event) throws IOException {
        UtilisateurDAO userDao = new UtilisateurDAO();
        System.out.println("" + txtUsername.getText());
        System.out.println("" + txtPassword.getText());
        if (userDao.existeUtilisateur(txtUsername.getText(), txtPassword.getText())) {
            Utilisateur user = userDao.findUtilisateurByLoginMdp(txtUsername.getText(), txtPassword.getText());

            if (user.getLocked() == 1 || user.getEnbaled() == 0) {
                tray = new TrayNotification("Bloquer "," votre compte est bloqué pour le moment ", Notifications.INFORMATION);
                    tray.showAndDismiss(Duration.seconds(5));
                
               // label.setText("votre compte est bloqué pour le moment");
            } else {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                if (user.getRole().contains("ADMIN")) {

                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/MenuAdmin.fxml"));
                    MenuAdminController menuAdminController = new MenuAdminController(user);
                    loader.setController(menuAdminController);
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(scene);
                    stage.show();
                } else if (user.getRole().contains("APPRENANT")) {
                     tray = new TrayNotification("Salut  " + UtilisateurDAO.loggedUser.getUsename(), "Bienvenu à World Elephent", Notifications.INFORMATION);
                    tray.showAndDismiss(Duration.seconds(5));
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/MenuApprenant.fxml"));
                    MenuApprenantController menuApprenantController = new MenuApprenantController(user);
                    loader.setController(menuApprenantController);
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(scene);
                    stage.show();
                } else if (user.getRole().contains("FORMATEUR")) {
                     tray = new TrayNotification("Salut  " + UtilisateurDAO.loggedUser.getUsename(), "Bienvenu à World Elephent", Notifications.INFORMATION);
                    tray.showAndDismiss(Duration.seconds(5));
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/MenuFormateur.fxml"));
                    MenuFormateurController menuFormateurController = new MenuFormateurController(user);
                    loader.setController(menuFormateurController);
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(scene);
                    stage.show();

                } else if (user.getRole().contains("ENTREPRISE")) {
                    tray = new TrayNotification("Salut  " + UtilisateurDAO.loggedUser.getUsename(), "Bienvenu à World Elephent", Notifications.INFORMATION);
                    tray.showAndDismiss(Duration.seconds(5));
                    
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/MenuEntreprise.fxml"));
                    MenuEntrepriseController menuEntrepriseController = new MenuEntrepriseController(user);
                    loader.setController(menuEntrepriseController);
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(scene);
                    stage.show();
                } else if (user.getRole().contains("COMITE")) {
                    tray = new TrayNotification("Salut  " + UtilisateurDAO.loggedUser.getUsename(), "Bienvenu à World Elephent", Notifications.INFORMATION);
                    tray.showAndDismiss(Duration.seconds(5));
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/MenuMembreComite.fxml"));
                    MenuMembreComiteController menuMembreComiteController = new MenuMembreComiteController(user);
                    loader.setController(menuMembreComiteController);
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(scene);
                    stage.show();
                }
            }
        } else {
            tray = new TrayNotification("Eurreur "," Erreur d'authentification", Notifications.ERROR);
                    tray.showAndDismiss(Duration.seconds(5));
                
            //label.setText("Failed");
        }
    }

}
