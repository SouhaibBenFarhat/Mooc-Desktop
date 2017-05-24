/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Message;
import com.esprit.mooc.Entities.Sujet;
import com.esprit.mooc.Entities.Utilisateur;
import com.esprit.mooc.ressources.config2;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebEngine;

/**
 * FXML Controller class
 *
 * @author kods
 */
public class PaneMessageController implements Initializable {

    @FXML
    Button modifierMessageBtn = new Button();
    @FXML
    Button afficherMessageBtn = new Button();
    @FXML
    private Label publierPar;
    @FXML
    private Label datepublication;
    @FXML
    private Label nbMessage;
    @FXML
    private Label titreMessage;
    @FXML
    Label time;
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
    SujetDetailController sujetDetailController;
    MenuFormateurController menuFormateurController;

    Message message;

    public PaneMessageController(Message message) {
        this.message = message;
        userDAO = new UtilisateurDAO();
        user = userDAO.findUtilisateurById(message.getUtilisateurMessage());
    }

    PaneMessageController(Message message, MenuFormateurController menuFormateurController) {
        this.message = message;
        this.menuFormateurController = menuFormateurController;
        userDAO = new UtilisateurDAO();
        user = userDAO.findUtilisateurById(message.getUtilisateurMessage());
    }

    PaneMessageController(Message message, SujetDetailController sujetDetailController) {
        this.message = message;
        this.sujetDetailController = sujetDetailController;
        userDAO = new UtilisateurDAO();
        user = userDAO.findUtilisateurById(message.getUtilisateurMessage());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(() -> {
            time.setText(message.getDatePublicationMessage().substring(10, 16));
            titreMessage.setText(message.getTitreMessage());
            datepublication.setText(message.getDatePublicationMessage().substring(0, 10));
            publierPar.setText(user.getUsename());
            if (UtilisateurDAO.loggedUser.getId() == message.getUtilisateurMessage()) {
                modifierMessageBtn.setVisible(true);
            } else {
                modifierMessageBtn.setVisible(false);
            }

        });

    }

    public void afficherMessage() {
        WebEngine webEngine = sujetDetailController.messageBrowser.getEngine();
        webEngine.loadContent(message.getContenuMessage());
        sujetDetailController.titreMessageTxt.setText(message.getTitreMessage());
    }

    public void modifierMessage() {
        sujetDetailController.messageTxt.setText(message.getContenuMessage());
        sujetDetailController.titreMessage.setText(message.getTitreMessage());
    }

}
