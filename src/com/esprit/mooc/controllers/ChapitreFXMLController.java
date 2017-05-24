/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.ChapitreDao;
import com.esprit.mooc.Entities.Chapitre;
import com.esprit.mooc.Interfaces.IDaoChapitre;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author kods
 */
public class ChapitreFXMLController implements Initializable {

    @FXML
    public TableView<Chapitre> table;

    @FXML
    private TableColumn<Chapitre, Integer> id_chapitre;
    @FXML
    private TableColumn<Chapitre, Integer> id_cours_chapitre;
    @FXML
    private TableColumn<Chapitre, String> titre_chapitre;
    @FXML
    private TableColumn<Chapitre, String> description_chapitre;
    @FXML
    private TableColumn<Chapitre, String> chemin_chapitre;
    @FXML
    private TableColumn<Chapitre, String> chemin_video_chapitre;
    @FXML
    private TableColumn<Chapitre, String> chemin_presentation_chapitre;
    @FXML
    private TextField idchap;
    @FXML
    private ComboBox idcours;
    @FXML
    private TextField titrechap;
    @FXML
    private TextField descchap;
    @FXML
    private TextField cheminchap;
    @FXML
    private TextField cheminvideochap;
    @FXML
    private TextField cheminpreschap;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button updateBtn;
    int id;
    ChapitreDao chapdao = new ChapitreDao();
    List<Chapitre> lFormateur = chapdao.findAllChapitre();
    public ObservableList<Chapitre> list = FXCollections.observableArrayList(lFormateur);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_chapitre.setCellValueFactory(new PropertyValueFactory<Chapitre, Integer>("id_chapitre"));
        id_cours_chapitre.setCellValueFactory(new PropertyValueFactory<Chapitre, Integer>("id_cours_chapitre"));
        titre_chapitre.setCellValueFactory(new PropertyValueFactory<Chapitre, String>("titre_chapitre"));
        description_chapitre.setCellValueFactory(new PropertyValueFactory<Chapitre, String>("description_chapitre"));
        chemin_chapitre.setCellValueFactory(new PropertyValueFactory<Chapitre, String>("chemin_chapitre"));
        chemin_video_chapitre.setCellValueFactory(new PropertyValueFactory<Chapitre, String>("chemin_video_chapitre"));
        chemin_presentation_chapitre.setCellValueFactory(new PropertyValueFactory<Chapitre, String>("chemin_presentation_chapitre"));
        table.setItems(list);
    }

    @FXML
    public void selectTable() {
        Chapitre u = table.getSelectionModel().getSelectedItem();
        id = u.getId_chapitre();

        btnSupprimer.setVisible(true);

    }

    @FXML
    public void supprimerAction() {
        System.out.println("clicked");
        this.refreshtable();
    }

    public void refreshtable() {

        ObservableList<Chapitre> list = FXCollections.observableArrayList(chapdao.findAllChapitre());
        table.setItems(list);
    }

    public void modifierChapitre() {
        Chapitre c = table.getSelectionModel().getSelectedItem();
        String id_chapi=Integer.toString(c.getId_chapitre());
        idchap.setText(id_chapi);
        idcours.setValue(c.getId_cours_chapitre());
        titrechap.setText(c.getTitre_chapitre());
        descchap.setText(c.getDescription_chapitre());
        cheminchap.setText(c.getChemin_chapitre());
        cheminpreschap.setText(c.getChemin_presentation_chapitre());
        cheminvideochap.setText(c.getChemin_video_chapitre());
        updateBtn.setVisible(true);
    }

    @FXML
    public void validerModificationChapitre() {
        Chapitre c = new Chapitre((int)idcours.getValue(), titrechap.getText(), descchap.getText(), cheminchap.getText(), cheminvideochap.getText(),cheminpreschap.getText());
        ChapitreDao chap=new ChapitreDao();
        chap.modifierChapitre(c);
        this.refreshtable();
    }
}
