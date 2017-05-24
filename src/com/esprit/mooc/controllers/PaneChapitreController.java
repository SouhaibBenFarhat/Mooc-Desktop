/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.ChapitreDao;
import com.esprit.mooc.Entities.Chapitre;
import com.esprit.mooc.ressources.config2;
import com.github.plushaze.traynotification.animations.Animations;
import com.github.plushaze.traynotification.notification.Notification;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Anas
 */
public class PaneChapitreController implements Initializable {

    Chapitre ch;
    @FXML
    private Label createurchap;
    @FXML
    private ImageView supprimerchapitre;
    @FXML
    private Label titrechap;
    @FXML
    private ImageView editer;
    @FXML
    MenuFormateurController parentFormateur;
        static int idcours;
        static int idchap;
    PaneCourController pc;

    public PaneChapitreController() {
    }

    public static int getIdcours() {
        return idcours;
    }

    public static int getIdchap() {
        return idchap;
    }
    public static void setIdchap(int idchap) {
        PaneChapitreController.idchap = idchap;
    }



    public PaneChapitreController(Chapitre ch) {
        this.ch = ch;
    }

    PaneChapitreController(Chapitre c, PaneCourController aThis) {
        this.ch = c;
        this.pc = aThis;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Tooltip.install(supprimerchapitre.getParent(), new Tooltip("Supprimer chapitre"));

        Tooltip.install(editer.getParent(), new Tooltip("Editer chapitre"));
        idcours = ch.getId_cours_chapitre();
        idchap= ch.getId_chapitre();
        createurchap.setText(PaneCourController.myVariable);
        titrechap.setText(ch.getTitre_chapitre());
    }

    @FXML
    private void voirchap() {
        config2 con = new config2();
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/ChapitreDetails.fxml"));
        ChapitreDetailsController chapitreDetailsController = new ChapitreDetailsController(ch);
        loader.setController(chapitreDetailsController);
        con.loadPane(parentFormateur.paneData, loader);

    }

    public void setParentFormateur(MenuFormateurController parentFormateur) {
        this.parentFormateur = parentFormateur;
    }

    @FXML
    private void supprimerchap() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression Chapitre");
        alert.setContentText("Êtes vous sur que vous allez supprimer le Chapitre?");
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ChapitreDao chap = new ChapitreDao();
            chap.supprimerChapitre(ch.getId_chapitre());
        } else {
            alert.close();
        }
        String title = "Félicitation";
        String message = "La suppression du chapitre: " + ch.getTitre_chapitre() + " a été établi avec succès";
        Notification notification = Notifications.INFORMATION;
        TrayNotification tray = new TrayNotification();
        tray.setAnimation(Animations.POPUP);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotification(notification);
        tray.showAndDismiss(Duration.seconds(2));
        pc.refreshlistechap(ch.getId_cours_chapitre());

    }

    @FXML
    public void editerchap() {
        ChapitreDao chdao=new ChapitreDao();
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/ModifChapitre.fxml"));
            Parent root1 = null;
       ModifChapitreController Mod = new ModifChapitreController(ch);
        try {
                    fxmlLoader.setController(Mod);
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(PaneChapitreController.class.getName()).log(Level.SEVERE, null, ex);
        }
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));  
            stage.show();
                config2 con = new config2();

    }
}
