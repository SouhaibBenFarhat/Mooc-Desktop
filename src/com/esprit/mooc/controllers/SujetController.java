/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.MessageDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Forum;
import com.esprit.mooc.Entities.Message;
import com.esprit.mooc.Entities.Sujet;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.text.html.HTML;

/**
 *
 * @author Souhaib
 */
public class SujetController implements Initializable {

    Sujet sujet;
    private Date date = new Date();
    private java.sql.Date sqlDate = new java.sql.Date(date.getTime());

    public SujetController(Sujet sujet) {
        this.sujet = sujet;
    }

    @FXML
    TextField titreMessageTextField = new TextField();
    @FXML
    HTMLEditor messageDePublication = new HTMLEditor();
    @FXML
    WebView messageContenu = new WebView();
    @FXML
    HTMLEditor contenuSujet = new HTMLEditor();
    @FXML
    TableView tableMessage = new TableView();
    // les colonnes
    @FXML
    TableColumn<Message, String> titreMessage;
    @FXML
    TableColumn<Message, String> datePublicationMessage;
    MessageDAO messageDAO = new MessageDAO();
    @FXML
    HTMLEditor contenuMessageAPublier = new HTMLEditor();
    @FXML
    Button publierButton = new Button();

    private List<Message> listMessage;
    private ObservableList<Message> oListMessage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contenuSujet.setHtmlText(sujet.getDescriptionSujet());
        titreMessage.setCellValueFactory(new PropertyValueFactory<Message, String>("titreMessage"));
        datePublicationMessage.setCellValueFactory(new PropertyValueFactory<Message, String>("datePublicationMessage"));
        listMessage = messageDAO.findMessageBySujet(sujet.getId());
        oListMessage = FXCollections.observableArrayList(listMessage);
        tableMessage.setItems(oListMessage);
        Node[] nodes = contenuSujet.lookupAll(".tool-bar").toArray(new Node[0]);
        for (Node node : nodes) {
            node.setVisible(false);
            node.setManaged(false);
        }

    }

    @FXML
    public void selectMessageTable() {
        Message message = (Message) tableMessage.getSelectionModel().getSelectedItem();
        WebEngine webEngine = messageContenu.getEngine();
        webEngine.loadContent(message.getContenuMessage());

    }

    public void ajouterMessage() {
        Message message = new Message(UtilisateurDAO.loggedUser.getId(), sujet.getId(), contenuMessageAPublier.getHtmlText(), titreMessageTextField.getText(), sqlDate.toString(), null);
        if (messageDAO.ajouterMessage(message)) {
            contenuMessageAPublier.setHtmlText("");
            titreMessageTextField.setText("");
            this.refreshTable();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("");
            alert.setContentText("Ajout effectuée !");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Confirmation");
            alert.setHeaderText("");
            alert.setContentText("Ajout non effectuée !");
            alert.showAndWait();
        }

    }

    public void refreshTable() {
        listMessage = messageDAO.findMessageBySujet(sujet.getId());
        oListMessage = FXCollections.observableArrayList(listMessage);
        tableMessage.setItems(oListMessage);
        
    }

    
}
