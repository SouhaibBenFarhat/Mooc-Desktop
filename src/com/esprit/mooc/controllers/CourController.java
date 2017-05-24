/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.CourDAO;
import com.esprit.mooc.DAO.DisciplineDao;
import com.esprit.mooc.DAO.UtilisateurDAO;

import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import com.esprit.mooc.Entities.Cours;
import com.esprit.mooc.Entities.Discipline;
import com.esprit.mooc.Entities.Utilisateur;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class CourController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane step1cours;
    @FXML
    private AnchorPane step2cours;
    @FXML
    private Label l_cs_discription;

    @FXML
    private Label l_cs_niveau;

    @FXML
    private Label l_prerequis;

    @FXML
    private Label l_objectif;

    @FXML
    private Label l_discription;

    @FXML
    private TextField tf_duree;

    @FXML
    private TextField tf_titreCours;

    @FXML
    private Label l_cs_prerequis;

    @FXML
    private Label l_cs_video;

    @FXML
    private ComboBox<String> cb_niveau;

    @FXML
    private Label l_cs_objectif;

    @FXML
    private Label l_cs_titreCour;

    @FXML
    private ComboBox<Discipline> cb_discipline;

    @FXML
    private Label l_introduction;

    @FXML
    private Label l_duree;

    @FXML
    private Label l_cs_introduction;

    @FXML
    private Label l_cs_discipline;
    @FXML
    private TextField tf_video1;

    @FXML
    private TextArea E_description;

    @FXML
    private TextArea E_Prerequis;

    @FXML
    private TextArea E_introduction;

    @FXML
    private TextArea E_objectif;

    @FXML
    private Button modifBtn;

    @FXML
    private Button btnAjout;

    Utilisateur usr;
    Cours cour;

    int selectBtn;

    CourController(Utilisateur loggedUser, Cours cour, int i) {
        this.usr = loggedUser;
        this.cour = cour;
        this.selectBtn = i;
    }

    @FXML
    void FinishModifierCour() {
        boolean resultat;
        modifBtn.setVisible(true);
        btnAjout.setVisible(false);

        String source = tf_video1.getText();
        Path path = Paths.get(source);
        String target_dir = "C:\\wamp\\www\\web\\MOOCV5\\web\\uploads\\video\\" + path.getFileName();
        Path target = Paths.get(target_dir);
        try {
            Files.copy(path, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(CourController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (tf_titreCours.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.WARNING, "champ titre vide", ButtonType.CLOSE);

            al.show();
        } else {
            Utilisateur usr = new Utilisateur();

            Cours c = new Cours(usr, cb_discipline.getSelectionModel().getSelectedItem(), tf_titreCours.getText(), E_description.getText(), E_objectif.getText(), E_Prerequis.getText(), Integer.parseInt(tf_duree.getText()), "en attente", tf_video1.getText(), 0, cb_niveau.getSelectionModel().getSelectedItem(), E_introduction.getText(), Date.valueOf(LocalDate.now()), 0);
            CourDAO cDAO = new CourDAO();
            c.setId(cour.getId());
            System.out.println(c);
            resultat = cDAO.ModifierCour(c);

            if (resultat) {
                TrayNotification tray = new TrayNotification("Modification", "Avec Succes", Notifications.SUCCESS);
                tray.showAndDismiss(Duration.seconds(5));
            }
            else{
            TrayNotification tray = new TrayNotification("Modification", "Avec Succes", Notifications.ERROR);
                tray.showAndDismiss(Duration.seconds(5));
            }
        }
    }

    @FXML
    void FinishAddCour(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        boolean resultat;
        
        String source = tf_video1.getText();
        Path path = Paths.get(source);
        String target_dir = "C:\\wamp\\www\\web\\MOOCV5\\web\\uploads\\video\\" + path.getFileName();
        Path target = Paths.get(target_dir);
        try {
            Files.copy(path, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(CourController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (tf_titreCours.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.WARNING, "champ titre vide", ButtonType.CLOSE);

            al.show();
        } else {
            Utilisateur usr = new Utilisateur();
            usr.setId(19);

            Cours c = new Cours(usr, cb_discipline.getSelectionModel().getSelectedItem(), tf_titreCours.getText(), E_description.getText(), E_objectif.getText(), E_Prerequis.getText(), Integer.parseInt(tf_duree.getText()), "en attente", tf_video1.getText(), 0, cb_niveau.getSelectionModel().getSelectedItem(), E_introduction.getText(), Date.valueOf(LocalDate.now()), 0);
            CourDAO cDAO = new CourDAO();
            System.out.println(c);
           resultat= cDAO.AjoutCour(c);

            if (resultat) {
                TrayNotification tray = new TrayNotification("Ajout", "Avec Succes", Notifications.SUCCESS);
                tray.showAndDismiss(Duration.seconds(5));
            }
            else{
            TrayNotification tray = new TrayNotification("Ajout", "Avec Succes", Notifications.ERROR);
                tray.showAndDismiss(Duration.seconds(5));
            }

        }
    }

    //  public File file = new File("c:");
    @FXML
    void BrowseVideo(ActionEvent event
    ) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Video files (*.mp4)", "*.mp4");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
        System.out.println(file);
        tf_video1.setText(file.getAbsolutePath());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        step1cours.setVisible(true);
        step2cours.setVisible(false);
        E_description.setWrapText(true);
        E_Prerequis.setWrapText(true);
        E_introduction.setWrapText(true);
        E_objectif.setWrapText(true);

        if (selectBtn == 1) {
            btnAjout.setVisible(true);
            modifBtn.setVisible(false);
        } else {
            btnAjout.setVisible(false);
            modifBtn.setVisible(true);
        }

        if (cour != null) {
            cb_discipline.setValue(cour.getDiscipline());

            tf_titreCours.setText(cour.getTitre_cours());

            E_description.setText(cour.getDescription_cours());

            E_objectif.setText(cour.getObjectif_cours());

            E_Prerequis.setText(cour.getPrerequis_cours());
            tf_duree.setText(String.valueOf(cour.getDuree_cours()));
            cour.setEtat_cours("en attente");

            tf_video1.setText(cour.getVideo_cours());
            cb_niveau.setValue(cour.getNiveau_cours());

            E_introduction.setText(cour.getIntroduction_cours());

        }

        DisciplineDao dispDAO = new DisciplineDao();

        cb_discipline.setCellFactory(
                new Callback<ListView<Discipline>, ListCell<Discipline>>() {
                    public ListCell<Discipline> call(ListView<Discipline> param) {
                        final ListCell<Discipline> cell = new ListCell<Discipline>() {

                            public void updateItem(Discipline item,
                                    boolean empty) {
                                super.updateItem(item, empty);

                                if (item == null) {
                                    setText("");
                                } else {

                                    setTextFill(Paint.valueOf("red"));
                                    setText(item.getNomDiscipline());

                                }
                            }
                        };
                        return cell;
                    }
                });

        cb_niveau.getItems().addAll("Facile", "Normale", "Difficile");

        // TODO
        cb_discipline.setItems(dispDAO.displayAll());
    }

    @FXML
    private void gotostep2() {
        step2cours.setVisible(true);
        step1cours.setVisible(false);
    }

    @FXML
    private void retourstep1() {
        step1cours.setVisible(true);
        step2cours.setVisible(false);

    }

}
