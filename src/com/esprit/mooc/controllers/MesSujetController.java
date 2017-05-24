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
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.swing.text.html.HTML;
import javax.xml.soap.Detail;
import sun.plugin.javascript.navig.Anchor;

/**
 *
 * @author Souhaib
 */
public class MesSujetController implements Initializable {

    @FXML
    TextField rechercheTxt = new TextField();
    @FXML
    Label titreSujet = new Label();
    @FXML
    Label datePublication = new Label();
    @FXML
    ListView<Message> messageList;
    @FXML
    WebView browser = new WebView();
    @FXML
    WebView messageBrowser = new WebView();
    WebEngine webEngine = new WebEngine();
    @FXML
    AnchorPane paneData = new AnchorPane();
    TextFlow tf = new TextFlow();
    Sujet sujet;
    FXMLLoader loader;
    AnchorPane anch;
    UtilisateurDAO userDAO;
    Utilisateur utilisateur;
    private Date date = new Date();
    private java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    MenuFormateurController menuFormateurController;
    MenuApprenantController menuApprenantController;

    public MesSujetController(Sujet sujet) {
        this.sujet = sujet;

    }

    public MesSujetController(Sujet sujet, MenuFormateurController menuFormateurController) {
        this.sujet = sujet;
        this.menuFormateurController = menuFormateurController;

    }

    MesSujetController(Sujet sujet, MenuApprenantController menuApprenantController) {
        this.sujet = sujet;
        this.menuApprenantController = menuApprenantController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        browser.getEngine().loadContent("Click to display... <img alt=\":p\" src=\"https://openclassrooms.com/bundles/common/images/smiley/langue.png\" />");
        browser.getEngine().setUserStyleSheetLocation(getClass().getResource("/com/esprit/mooc/css/webView.css").toString());
        messageBrowser.getEngine().setUserStyleSheetLocation(getClass().getResource("/com/esprit/mooc/css/webView.css").toString());
        messageBrowser.getEngine().loadContent("Click to display... <img alt=\":p\" src=\"https://openclassrooms.com/bundles/common/images/smiley/langue.png\" />");
        browser.setVisible(true);
        userDAO = new UtilisateurDAO();
        utilisateur = userDAO.findUtilisateurById(sujet.getUtilisateur());
        tf.setMaxWidth(600);
        tf.getChildren().clear();
        paneData.getChildren().clear();
        FXMLLoader loader;
        tf.getChildren().clear();
        if (UtilisateurDAO.loggedUser.getRole().contains("FORMATEUR")) {
            menuFormateurController.paneData.getChildren().clear();
            SujetDAO sujetDAO = new SujetDAO();
            List<Sujet> lSujet = sujetDAO.findSujetByUtilisateur(UtilisateurDAO.loggedUser.getId());
            for (Sujet sujet : lSujet) {
                loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneMesSujet.fxml"));
                PaneMesSujetController paneController = new PaneMesSujetController(sujet, menuFormateurController, this);
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
        if (UtilisateurDAO.loggedUser.getRole().contains("APPRENANT")) {
            menuApprenantController.paneData.getChildren().clear();
            SujetDAO sujetDAO = new SujetDAO();
            List<Sujet> lSujet = sujetDAO.findSujetByUtilisateur(UtilisateurDAO.loggedUser.getId());
            for (Sujet sujet : lSujet) {
                loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneMesSujet.fxml"));
                PaneMesSujetController paneController = new PaneMesSujetController(sujet, menuApprenantController, this);
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

    public void RemplirListMessage(int idSujet) throws SQLException, ClassNotFoundException {
        messageList.getItems().clear();
        MessageDAO messageDao = new MessageDAO();
        ObservableList<Message> obListMessage = FXCollections.observableList(messageDao.findMessageBySujet(idSujet));
        messageList.setItems(obListMessage);
        messageList.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>() {
            @Override
            public ListCell<Message> call(ListView<Message> param) {
                VBox vb = new VBox();
                HBox hb = new HBox();
                Button detail = new Button();
                hb.setPadding(new Insets(1, 1, 1, 1));
                Text titre = new Text("");
                titre.setFont(new Font("System", 13));
                titre.setFill(Paint.valueOf("#ffffff"));
                titre.setWrappingWidth(300);
                Text date = new Text();
                date.setFont(new Font("System", 13));
                date.setFill(Paint.valueOf("#ffffff"));
                date.setWrappingWidth(300);
                ImageView imageView = new ImageView();
                ImageView imageView2 = new ImageView();
                ListCell<Message> Cell = new ListCell<Message>() {
                    @Override
                    protected void updateItem(Message item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null && item.getTitreMessage() != null) {
                            try {
                                Image image = new Image(new FileInputStream(new File("C:\\Users\\Souhaib\\Documents\\NetBeansProjects\\JAVAMOOC\\src\\com\\esprit\\mooc\\ressources\\msg.png")));
                                imageView.setImage(image);
                                imageView.setFitHeight(35);
                                imageView.setFitWidth(35);
                                Image image2 = new Image(new FileInputStream(new File("C:\\Users\\Souhaib\\Documents\\NetBeansProjects\\JAVAMOOC\\src\\com\\esprit\\mooc\\ressources\\date.png")));
                                imageView2.setImage(image2);
                                imageView2.setFitHeight(20);
                                imageView2.setFitWidth(20);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(MesSujetController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            date.setText(item.getDatePublicationMessage().substring(0, 10));
                            titre.setText(item.getTitreMessage());
                            titre.setStyle("-fx-font-weight: bold");
                            detail.setMinWidth(25);
                            hb.getStylesheets().add(getClass().getResource("/com/esprit/mooc/css/webView.css").toString());
                            detail.setVisible(true);
                            detail.setStyle("-fx-background-image: url('/com/esprit/mooc/ressources/smalldetails.png'); -fx-background-color:#0097A7");
                            detail.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    messageBrowser.getEngine().loadContent("<b>" + item.getTitreMessage() + "</b><br>" + item.getContenuMessage());
                                    datePublication.setText(item.getDatePublicationMessage().substring(0, 10));
                                }
                            });
                        } else {
                            detail.setVisible(false);
                        }
                    }
                };
                Text txt = new Text("   ");
                Text txt2 = new Text("          ");
                vb.getChildren().addAll(titre, date);
                hb.getChildren().addAll(imageView, txt, vb, detail);
                Cell.setGraphic(hb);
                return Cell;

            }
        });

    }

    public void chercherMesSujets() {
        if (UtilisateurDAO.loggedUser.getRole().contains("FORMATEUR")) {
            tf.getChildren().clear();
            tf.setMaxWidth(600);
            paneData.getChildren().clear();
            SujetDAO sujetDAO = new SujetDAO();
            List<Sujet> lSujet = sujetDAO.chercherSujetByNom(rechercheTxt.getText(), UtilisateurDAO.loggedUser.getId());
            for (Sujet sujet : lSujet) {
                loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneMesSujet.fxml"));
                PaneMesSujetController paneController = new PaneMesSujetController(sujet, menuFormateurController, this);
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
        if (UtilisateurDAO.loggedUser.getRole().contains("APPRENANT")) {
            tf.getChildren().clear();
            tf.setMaxWidth(600);
            paneData.getChildren().clear();
            SujetDAO sujetDAO = new SujetDAO();
            List<Sujet> lSujet = sujetDAO.chercherSujetByNom(rechercheTxt.getText(), UtilisateurDAO.loggedUser.getId());
            for (Sujet sujet : lSujet) {
                loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneMesSujet.fxml"));
                PaneMesSujetController paneController = new PaneMesSujetController(sujet, menuApprenantController, this);
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

    public void refreshSubjectList() {
        if (UtilisateurDAO.loggedUser.getRole().contains("FORMATEUR")) {
            tf.getChildren().clear();
            tf.setMaxWidth(600);
            paneData.getChildren().clear();
            SujetDAO sujetDAO = new SujetDAO();
            List<Sujet> lSujet = sujetDAO.findSujetByUtilisateur(UtilisateurDAO.loggedUser.getId());
            for (Sujet sujet : lSujet) {
                loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneMesSujet.fxml"));
                PaneMesSujetController paneController = new PaneMesSujetController(sujet, menuFormateurController, this);
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
        if (UtilisateurDAO.loggedUser.getRole().contains("APPRENANT")) {
            tf.getChildren().clear();
            tf.setMaxWidth(600);
            paneData.getChildren().clear();
            SujetDAO sujetDAO = new SujetDAO();
            List<Sujet> lSujet = sujetDAO.findSujetByUtilisateur(UtilisateurDAO.loggedUser.getId());
            for (Sujet sujet : lSujet) {
                loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneMesSujet.fxml"));
                PaneMesSujetController paneController = new PaneMesSujetController(sujet, menuApprenantController, this);
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
}
