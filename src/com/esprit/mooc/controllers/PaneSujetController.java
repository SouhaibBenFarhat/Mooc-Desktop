/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.UtilisateurDAO;
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

/**
 * FXML Controller class
 *
 * @author kods
 */
public class PaneSujetController implements Initializable {

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
    MenuApprenantController menuApprenantController;
    Sujet sujet;

    public PaneSujetController(Sujet sujet) {
        this.sujet = sujet;
        userDAO = new UtilisateurDAO();
        user = userDAO.findUtilisateurById(sujet.getUtilisateur());
    }

    PaneSujetController(Sujet sujet, MenuFormateurController menuFormateurController) {
        this.sujet = sujet;
        this.menuFormateurController = menuFormateurController;
        userDAO = new UtilisateurDAO();
        user = userDAO.findUtilisateurById(sujet.getUtilisateur());
    }

    PaneSujetController(Sujet sujet, MenuApprenantController menuApprenantController) {
        this.sujet = sujet;
        userDAO = new UtilisateurDAO();
        user = userDAO.findUtilisateurById(sujet.getUtilisateur());
        this.menuApprenantController = menuApprenantController;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(sujet.getId() + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
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
            System.out.println(sujet.getId() + "aaaaaaaaaaaaaaaaaabababababababababababababababababababababab");
        }
        if (UtilisateurDAO.loggedUser.getRole().contains("APPRENANT")) {
            System.out.println("je marche");
            config2 con = new config2();
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/SujetDetail.fxml"));
            SujetDetailController sujetController = new SujetDetailController(sujet, menuApprenantController);
            loader.setController(sujetController);
            con.loadPane(menuApprenantController.paneData, loader);
            System.out.println(sujet.getId() + "aaaaaaaaaaaaaaaaaabababababababababababababababababababababab");
        }

    }

}
