/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.ForumDAO;
import com.esprit.mooc.DAO.MessageDAO;
import com.esprit.mooc.DAO.SujetDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Forum;
import com.esprit.mooc.Entities.Message;
import com.esprit.mooc.Entities.Sujet;
import com.esprit.mooc.Entities.Utilisateur;
import com.esprit.mooc.ressources.config2;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import javax.swing.text.html.HTML;
import sun.plugin.javascript.navig.Anchor;

/**
 *
 * @author Souhaib
 */
public class SujetDetailController implements Initializable {

    @FXML
    TextField chercherMessageTxt = new TextField();
    @FXML
    Button mesInterventionsBtn = new Button();
    @FXML
    Button mesSujetsBtn = new Button();
    @FXML
    Label promptText = new Label();
    @FXML
    Label titreMessageTxt = new Label();
    @FXML
    WebView messageBrowser = new WebView();
    WebEngine messageWebEngine = new WebEngine();
    @FXML
    Button publierMessageBtn = new Button();
    @FXML
    TextField titreMessage = new TextField();
    @FXML
    TextArea messageTxt = new TextArea();
    @FXML
    Label datePublication;
    @FXML
    Label user = new Label();
    @FXML
    Label sujetName = new Label();
    @FXML
    AnchorPane paneData = new AnchorPane();
    @FXML
    WebView browser = new WebView();
    WebEngine webEngine = new WebEngine();
    TextFlow tf = new TextFlow();
    Sujet sujet;
    @FXML
    TextArea contenuSujet = new TextArea();
    FXMLLoader loader;
    AnchorPane anch;
    UtilisateurDAO userDAO;
    Utilisateur utilisateur;
    private Date date = new Date();
    private java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    MenuFormateurController menuFormateurController;
    MenuApprenantController menuApprenantController;

    public SujetDetailController(Sujet sujet) {
        this.sujet = sujet;

    }

    public SujetDetailController(Sujet sujet, MenuFormateurController menuFormateurController) {
        this.sujet = sujet;
        this.menuFormateurController = menuFormateurController;

    }

    SujetDetailController(Sujet sujet, MenuApprenantController menuApprenantController) {
        this.sujet = sujet;
        this.menuApprenantController = menuApprenantController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(sujet.getForum() + "aaaaaaaaazzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        messageTxt.setWrapText(true);
        mesSujetsBtn.setOnMouseEntered(myHandler);
        mesInterventionsBtn.setOnMouseEntered(myHandler);
        mesSujetsBtn.setOnMouseExited(myHandler2);
        mesInterventionsBtn.setOnMouseExited(myHandler2);
        userDAO = new UtilisateurDAO();
        utilisateur = userDAO.findUtilisateurById(sujet.getUtilisateur());
        user.setText(utilisateur.getUsename());
        sujetName.setText(sujet.getTitre());
        datePublication.setText(sujet.getDatePublication().substring(0, 10));
        webEngine = browser.getEngine();
        webEngine.loadContent(sujet.getSousTitre() + "<br><br>" + sujet.getDescriptionSujet());
        browser.setBlendMode(BlendMode.LIGHTEN);
        messageBrowser.setBlendMode(BlendMode.LIGHTEN);
        browser.getEngine().setUserStyleSheetLocation(getClass().getResource("/com/esprit/mooc/css/webView.css").toString());
        messageBrowser.getEngine().setUserStyleSheetLocation(getClass().getResource("/com/esprit/mooc/css/webView.css").toString());
        browser.setVisible(true);
        messageBrowser.setVisible(true);
        tf.setMaxWidth(215);
        tf.getChildren().clear();
        paneData.getChildren().clear();
        MessageDAO messageDAO = new MessageDAO();
        List<Message> lMessage = messageDAO.findMessageBySujet(sujet.getId());
        for (Message message : lMessage) {
            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneMessage.fxml"));
            PaneMessageController paneController = new PaneMessageController(message, this);
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

    public void ajouterMessage() {
        boolean etat = true;
        if ("".equals(messageTxt.getText())) {
            messageTxt.setStyle(" -fx-border-color: red;   -fx-border-width:2;");
            etat = false;
        }
        if ("".equals(titreMessage.getText())) {
            titreMessage.setStyle(" -fx-border-color: red;   -fx-border-width:2;");
            etat = false;
        }
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2),
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        titreMessage.setStyle(" -fx-border-color: black, #bababa;");
                        messageTxt.setStyle(" -fx-border-color: black, #bababa;");

                    }
                }));
        timeline.play();
        if (etat) {

            MessageDAO messageDAO = new MessageDAO();
            Date date = new Date();
            Message message = new Message(UtilisateurDAO.loggedUser.getId(), sujet.getId(), messageTxt.getText(), titreMessage.getText(), sqlDate.toString(), null);
            if (messageDAO.ajouterMessage(message)) {
                messageTxt.setText("");
                titreMessage.setText("");

                TrayNotification tray = new TrayNotification("Success", "Publication effectuée", Notifications.SUCCESS);
                tray.showAndDismiss(Duration.seconds(2));
                tf.setMaxWidth(187);
                tf.getChildren().clear();
                paneData.getChildren().clear();
                List<Message> lMessage = messageDAO.findMessageBySujet(sujet.getId());
                for (Message m : lMessage) {
                    loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneMessage.fxml"));
                    PaneMessageController paneController = new PaneMessageController(m, this);
                    loader.setController(paneController);
                    try {
                        anch = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    tf.getChildren().add(anch);
                }
                paneData.getChildren().addAll(tf);

            } else {
                TrayNotification tray = new TrayNotification("ERREUR", "Publication non effectuée", Notifications.ERROR);
                tray.showAndDismiss(Duration.seconds(2));
            }
        }

    }

    final EventHandler<MouseEvent> myHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(final MouseEvent event) {
            Button x = (Button) event.getSource();
            if (x.getId().equals(mesSujetsBtn.getId())) {
                promptText.setText("Mes Sujets");
            } else if (x.getId().equals(mesInterventionsBtn.getId())) {
                promptText.setText("Intervention");
            } else {
                promptText.setText("");
            }
        }
    };

    final EventHandler<MouseEvent> myHandler2 = new EventHandler<MouseEvent>() {

        @Override
        public void handle(final MouseEvent event) {
            Button x = (Button) event.getSource();
            if (x.getId().equals(mesSujetsBtn.getId())) {
                promptText.setText("");
            } else if (x.getId().equals(mesInterventionsBtn.getId())) {
                promptText.setText("");
            }
        }
    };

    public void afficherMesSujets() {

        if (UtilisateurDAO.loggedUser.getRole().contains("APPRENANT")) {
            config2 con = new config2();
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/mesSujet.fxml"));
            MesSujetController sujetController = new MesSujetController(sujet, menuApprenantController);
            loader.setController(sujetController);
            con.loadPane(menuApprenantController.paneData, loader);
        }
        if (UtilisateurDAO.loggedUser.getRole().contains("FORMATEUR")) {
            config2 con = new config2();
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/mesSujet.fxml"));
            MesSujetController sujetController = new MesSujetController(sujet, menuFormateurController);
            loader.setController(sujetController);
            con.loadPane(menuFormateurController.paneData, loader);
        }
    }

    @FXML
    public void ConvertSujetPDF() {

        System.out.println("pdf OK");
        String nomPDF = sujet.getTitre();
        ForumDAO forumDao = new ForumDAO();
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        try {
            OutputStream file = new FileOutputStream(new File("C:\\Users\\Souhaib\\Documents\\NetBeansProjects\\SujetPDF\\" + nomPDF + ".pdf"));

            Document document = new Document();
            PdfWriter.getInstance(document, file);
            System.out.println(sujet.getForum() + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            document.open();
            document.add(new Paragraph("Forum: "
                    + forumDao.findForumById(sujet.getForum()).getNomForum()
            ));
            document.add(new Paragraph(sujet.getDatePublication()));
            document.addAuthor(utilisateurDAO.findUtilisateurById(sujet.getUtilisateur()).getUsename());
            document.addCreationDate();
            document.addCreator("JavaBeat");
            document.addTitle("Sample PDF");

            //Create Paragraph
            Paragraph paragraph = new Paragraph(sujet.getTitre(), new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 18, com.itextpdf.text.Font.BOLD));

            //New line
            paragraph.add(new Paragraph(" "));
            paragraph.add(new Paragraph("Sujet publier"));
            paragraph.add(new Paragraph(sujet.getDescriptionSujet()));
            document.add(paragraph);

            document.close();
            file.close();
            System.out.println("pdf Done");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void chercherMessage() {
        tf.getChildren().clear();
        tf.setMaxWidth(187);
        paneData.getChildren().clear();
        MessageDAO messageDAO = new MessageDAO();
        List<Message> messages = messageDAO.findMessageByUsername(chercherMessageTxt.getText());
        for (Message message : messages) {
            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneMessage.fxml"));
            PaneMessageController paneController = new PaneMessageController(message, this);
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
}
