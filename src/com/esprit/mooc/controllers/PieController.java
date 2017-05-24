/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.DisciplineDao;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
public class PieController implements Initializable {

    @FXML
    Button backBtn = new Button();
    @FXML
    Button nextBtn = new Button();
    @FXML
    AnchorPane paneChart1 = new AnchorPane();
    @FXML
    Text txtlegend = new Text();
    @FXML
    ImageView image = new ImageView();
    @FXML
    PieChart pieChart1 = new PieChart();
    @FXML
    PieChart pieChart2 = new PieChart();
    @FXML
    private Button btnOk;
    @FXML
    private Text txtLabel;
    @FXML
    private Text lblWelcome;

    List<Discipline> listDiscipline;
    DisciplineDao disciplineDAO = new DisciplineDao();
    UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    MenuFormateurController menuFormateurController;
    Date date = new Date();
    MenuAdminController menuAdminController;

    private java.sql.Date sqlDate = new java.sql.Date(date.getTime());

    public PieController(MenuFormateurController menuFormateurController) {
        this.menuFormateurController = menuFormateurController;
    }

    public PieController(MenuAdminController menuAdminController) {
        this.menuAdminController = menuAdminController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pieChart2.setVisible(false);
        backBtn.setVisible(false);

        PieChart.Data nombreFormateur = new PieChart.Data("Nombre des Formateurs", utilisateurDAO.getNombreFormateur());
        PieChart.Data nombreApprenant = new PieChart.Data("Nombre des Apprenants", utilisateurDAO.getNombreApprenant());
        PieChart.Data nombreEntreprise = new PieChart.Data("Nombre des Entreprises", utilisateurDAO.getNombreEntreprise());
        ObservableList<PieChart.Data> listChart2 = FXCollections.observableArrayList();

        listChart2.add(nombreApprenant);
        listChart2.add(nombreEntreprise);
        listChart2.add(nombreFormateur);

        pieChart2.setData(listChart2);

        Platform.runLater(() -> {
            new FadeInLeftTransition(backBtn).play();
            new FadeInLeftTransition(nextBtn).play();
            new FadeInLeftTransition(txtlegend).play();
            new FadeInLeftTransition(image).play();
            new FadeInLeftTransition(pieChart1).play();
            new FadeInLeftTransition(txtLabel).play();
            new FadeInLeftTransition(lblWelcome).play();
            new FadeInRightTransition(btnOk).play();
            listDiscipline = disciplineDAO.findAllDisciplineObject();
            ObservableList<PieChart.Data> listChart1 = FXCollections.observableArrayList();
            for (Discipline d : listDiscipline) {

                PieChart.Data dataChart1 = new PieChart.Data(d.getNomDiscipline(), d.getNombreCours());
                listChart1.add(dataChart1);

            }
            pieChart1.setData(listChart1);

            btnOk.setOnMouseClicked((MouseEvent event) -> {
                Stage stage = (Stage) btnOk.getScene().getWindow();
                stage.close();
            });
        });

    }

    public void next() {
        nextBtn.setVisible(false);
        backBtn.setVisible(true);
        pieChart1.setVisible(false);
        pieChart2.setVisible(true);
        txtlegend.setText("Nombre des Utilisateurs par rôle");
    }

    public void back() {
        backBtn.setVisible(false);
        nextBtn.setVisible(true);
        pieChart1.setVisible(true);
        pieChart2.setVisible(false);
        txtlegend.setText("Nombre des cours par discipline");

    }

}
