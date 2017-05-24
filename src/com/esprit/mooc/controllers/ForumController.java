/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.DisciplineDao;
import com.esprit.mooc.DAO.ForumDAO;
import com.esprit.mooc.DAO.SujetDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Discipline;
import com.esprit.mooc.Entities.Forum;
import com.esprit.mooc.Entities.Sujet;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;

/**
 *
 * @author Souhaib
 */
public class ForumController implements Initializable {

    @FXML
    Tab sujet = new Tab();
    @FXML
    Tab mesSujets = new Tab();

    //statistique
    @FXML
    PieChart pieChar;
    List<Discipline> listDiscipline;
    DisciplineDao disciplineDAO = new DisciplineDao();

    //mes sujets
    @FXML
    Button afficherLesMessage = new Button();
    private List<Sujet> mesSujetsList = new ArrayList<>();
    @FXML
    TableView<Sujet> mesSujets2 = new TableView<>();
    private ObservableList<Sujet> oListMesSujets;
    @FXML
    TableColumn<Sujet, String> mesSujetsTitreSujet;
    @FXML
    TableColumn<Sujet, String> mesSujetDatePublicationSujet;
    @FXML
    HTMLEditor mesSujetContenuSujet = new HTMLEditor();
    @FXML
    Button editButton = new Button();
    @FXML
    Button supprimerButton = new Button();
    @FXML
    TextField titreSujetMesSujet = new TextField();
    @FXML
    TextField sousTitreSujetMesSujet = new TextField();

//sujets
    @FXML
    AnchorPane editAnchor = new AnchorPane();
    @FXML
    TextField chercherForumText = new TextField();
    @FXML
    TextField chercherSujetText = new TextField();
    @FXML
    HTMLEditor contenuSujet = new HTMLEditor();
    Button addButton = new Button();
    @FXML
    Button detailsButton = new Button();
    @FXML
    Button publierButton = new Button();
    @FXML
    ComboBox<Forum> forumCombo;
    @FXML
    TableView<Forum> forumTable = new TableView<>();
    @FXML
    TableView<Sujet> sujetTable = new TableView<>();
    @FXML
    TextField titreSujetTexte = new TextField();
    @FXML
    TextField soustitreSujetTexte = new TextField();
    @FXML
    TextArea descriptionSujetTexte = new TextArea();

    @FXML
    private TableColumn<Forum, String> nomForum;
    @FXML
    private TableColumn<Sujet, String> titreSujet;
    @FXML
    private TableColumn<Sujet, String> datePublicationSujet;

    private ForumDAO forumDAO = new ForumDAO();
    private SujetDAO sujetDAO = new SujetDAO();

    private List<Forum> listForum;
    private List<Sujet> listSujet;

    private ObservableList<Forum> oListForum;
    private ObservableList<Sujet> oListSujet;
    int id;
    MenuFormateurController mfc;

    public ForumController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//sujet
        nomForum.setCellValueFactory(new PropertyValueFactory<Forum, String>("nomForum"));
        titreSujet.setCellValueFactory(new PropertyValueFactory<Sujet, String>("titre"));
        datePublicationSujet.setCellValueFactory(new PropertyValueFactory<Sujet, String>("datePublication"));
        editAnchor.setVisible(false);
        listForum = forumDAO.findAllForums();
        oListForum = FXCollections.observableArrayList(listForum);
        forumTable.setItems(oListForum);
        forumCombo.setItems(oListForum);
        contenuSujet.setDisable(true);
        detailsButton.setVisible(false);

//mes sujets
        mesSujetsTitreSujet.setCellValueFactory(new PropertyValueFactory<Sujet, String>("titre"));
        mesSujetDatePublicationSujet.setCellValueFactory(new PropertyValueFactory<Sujet, String>("datePublication"));
        mesSujetsList = sujetDAO.findSujetByUtilisateur(UtilisateurDAO.loggedUser.getId());
        oListMesSujets = FXCollections.observableArrayList(mesSujetsList);
        mesSujets2.setItems(oListMesSujets);

        //statistique
        listDiscipline = disciplineDAO.findAllDisciplineObject();
        ObservableList<Data> list = FXCollections.observableArrayList();
        for (Discipline d : listDiscipline) {
            Data data = new Data(d.getNomDiscipline(), d.getNombreCours());
            list.add(data);
        }
        pieChar.setData(list);
        Node[] nodes = contenuSujet.lookupAll(".tool-bar").toArray(new Node[0]);
        for (Node node : nodes) {
            node.setVisible(false);
            node.setManaged(false);
        }
    }

    public void refreshtableForum() {
        listForum = FXCollections.observableArrayList(forumDAO.findAllForums());
        forumTable.setItems(oListForum);
        forumTable.editableProperty();
    }

    public void refreshtableSujet() {
        listForum = FXCollections.observableArrayList(forumDAO.findAllForums());
        forumTable.setItems(oListForum);
        forumTable.editableProperty();
    }

    @FXML
    public void selectTable() {
        Forum f = forumTable.getSelectionModel().getSelectedItem();
        id = f.getId();
        listSujet = sujetDAO.findAllSujets(id);
        oListSujet = FXCollections.observableArrayList(listSujet);
        sujetTable.setItems(oListSujet);
        detailsButton.setVisible(false);

    }

    public void selectTableMesSujets() {

        Sujet monSujet = mesSujets2.getSelectionModel().getSelectedItem();
        mesSujetContenuSujet.setHtmlText(monSujet.getDescriptionSujet());
        titreSujetMesSujet.setText(monSujet.getTitre());
        sousTitreSujetMesSujet.setText(monSujet.getSousTitre());
        editAnchor.setVisible(true);
    }

    @FXML
    public void ajouterSujet() {
        int idForum = forumCombo.getSelectionModel().getSelectedItem().getId();
        Sujet sujet = new Sujet();
        sujet.setForum(idForum);
        sujet.setTitre(titreSujetTexte.getText());
        sujet.setSousTitre(soustitreSujetTexte.getText());
        sujet.setDescriptionSujet(descriptionSujetTexte.getText());
        sujet.setEtatSujet(0);
        sujet.setNombreMessage(0);
        sujet.setUtilisateur(UtilisateurDAO.loggedUser.getId());
        sujetDAO.ajouterSujet(sujet);
        titreSujetTexte.setText("");
        soustitreSujetTexte.setText("");
        descriptionSujetTexte.setText("");
        this.selectTable();
        this.refreshTableMesSujet2();
    }

    @FXML
    public void selectSujetTable() {
        Sujet sujet = sujetTable.getSelectionModel().getSelectedItem();
        Node[] nodes = contenuSujet.lookupAll(".tool-bar").toArray(new Node[0]);

        if (sujetTable.getSelectionModel().getSelectedItem() != null) {
            detailsButton.setVisible(true);
        } else {
            detailsButton.setVisible(false);
        }

        if (sujet.getUtilisateur() == UtilisateurDAO.loggedUser.getId()) {
            contenuSujet.setDisable(false);
            for (Node node : nodes) {
                node.setVisible(false);
                node.setManaged(false);
            }
        }
        if (sujet.getUtilisateur() != UtilisateurDAO.loggedUser.getId()) {

            for (Node node : nodes) {
                node.setVisible(false);
                node.setManaged(false);
            }

        }
        contenuSujet.setHtmlText(sujetTable.getSelectionModel().getSelectedItem().getDescriptionSujet());
    }

    @FXML
    public void afficherMessageSujet() throws IOException {

        Sujet sujet = sujetTable.getSelectionModel().getSelectedItem();
        SujetController sujetController = new SujetController(sujet);
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/Sujet.fxml"));
        loader.setController(sujetController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();

    }

    @FXML
    public void mesSujetAfficherMessageSujet() throws IOException {

        Sujet sujet = mesSujets2.getSelectionModel().getSelectedItem();
        SujetController sujetController = new SujetController(sujet);
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/Sujet.fxml"));
        loader.setController(sujetController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();

    }

    public void chercherSujet() {
        oListSujet = FXCollections.observableArrayList(sujetDAO.chercherSujet(chercherSujetText.getText(), forumTable.getSelectionModel().getSelectedItem().getId()));
        sujetTable.setItems(oListSujet);
    }

    public void chercherForum() {
        listForum = forumDAO.chercherForum(chercherForumText.getText());
        oListForum = FXCollections.observableArrayList(listForum);
        forumTable.setItems(oListForum);
    }

    @FXML
    public void editSujet() {
        Sujet selectedSujet = mesSujets2.getSelectionModel().getSelectedItem();
        selectedSujet.setDescriptionSujet(mesSujetContenuSujet.getHtmlText());
        selectedSujet.setTitre(titreSujetMesSujet.getText());
        selectedSujet.setSousTitre(sousTitreSujetMesSujet.getText());
        if (sujetDAO.modifierSujet(selectedSujet)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("");
            alert.setContentText("Modification effectuée !");
            alert.showAndWait();

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Confirmation");
            alert.setHeaderText("");
            alert.setContentText("Modification non effectuée !");
            alert.showAndWait();
        }
        editAnchor.setVisible(false);
        refreshTableMesSujet2();
        this.selectSujetTable();

    }

    @FXML
    public void supprimerSujet() {
        Sujet selectedSujet = mesSujets2.getSelectionModel().getSelectedItem();
        SujetDAO sujetDao = new SujetDAO();
        if (sujetDAO.supprimerSujet(selectedSujet.getId())) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("");
            alert.setContentText("Suppression effectuée !");
            alert.showAndWait();
            this.refreshTableMesSujet2();
            mesSujetContenuSujet.setHtmlText("");
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Confirmation");
            alert.setHeaderText("");
            alert.setContentText("Suppression non effectuée !");
            alert.showAndWait();
        }
    }

    public void refreshTableMesSujet2() {
        List<Sujet> mesSujetsList2 = sujetDAO.findSujetByUtilisateur(UtilisateurDAO.loggedUser.getId());
        ObservableList<Sujet> oListMesSujets2 = FXCollections.observableArrayList(mesSujetsList2);
        mesSujets2.setItems(oListMesSujets2);
    }
}
