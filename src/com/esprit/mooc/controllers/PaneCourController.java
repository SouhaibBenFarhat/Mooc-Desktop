/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.BibliothequeDAO;
import com.esprit.mooc.DAO.ChapitreDao;
import com.esprit.mooc.DAO.CourDAO;
import com.esprit.mooc.DAO.DisciplineDao;

import com.esprit.mooc.DAO.InvitationFormateurEntrepriseDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Chapitre;
import com.esprit.mooc.Entities.Cours;
import com.esprit.mooc.Entities.Discipline;
//import com.esprit.mooc.Entities.Forum;
import com.esprit.mooc.Entities.InvitationFormateurEntreprise;
import com.esprit.mooc.Entities.Utilisateur;
import static com.esprit.mooc.Entities.Utilisateur.loggedUser;
import com.esprit.mooc.ressources.config2;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.Action;


/**
 * FXML Controller class
 *
 * @author kods
 */
public class PaneCourController implements Initializable {

    @FXML
    private Label labelnbrVu;
    @FXML
    private Button meschapitres;

    @FXML
    private Label labelTitre;

    @FXML
    private ImageView Ajout;

    @FXML
    private Pane paneCour;

    @FXML
    private Button btnCour;

    @FXML
    private Button btnAjout;

    @FXML
    private Label labelrealise;

    @FXML
    private ImageView IMGdiscipline;

    @FXML
    private Button btnSuprim;

    @FXML
    private ImageView Supprim;

    @FXML
    private Button btnLire;

    MenuApprenantController parentApprenant;

    MenuFormateurController parentFormateur;

    MenuMembreComiteController parentComit;

    @FXML
    private Button accepted;

    @FXML
    private Button refused;

    @FXML
    private Button waiting;

    @FXML
    private Button btnSuprimCour;

    @FXML
    private Button btnAcceptCour;

    @FXML
    private Button btnRefuseCour;

    @FXML
    private Button btnLireComite;

    @FXML
    private Button btnModifier;

    FXMLLoader loader;

    private Utilisateur loggedUser;

    // ****
    Discipline discipline;
    DisciplineDao disciplineDao = new DisciplineDao();

    File file;
//Utilisateur loggedUser;
    //Alert a = new Alert(Alert.AlertType.INFORMATION);
    Cours cour;
    int selectedBtn;

    CourDAO courDAO = new CourDAO();
    BibliothequeDAO bibliothequeDAO = new BibliothequeDAO();
    static String myVariable;

    public static String getMyVariable() {
        return myVariable;
    }

    public static void setMyVariable(String myVariable) {
        PaneCourController.myVariable = myVariable;
    }

    public PaneCourController() {
    }

    public PaneCourController(Cours cour, int btn) {
        this.cour = cour;
        this.selectedBtn = btn;

    }

    PaneCourController(Cours c, int selected, MenuApprenantController aThis) {
        this.cour = c;
        this.selectedBtn = selected;
        parentApprenant = aThis;
    }

    PaneCourController(Cours c, int i, MenuFormateurController aThis, int num) {
        this.cour = c;
        this.selectedBtn = i;
        parentFormateur = aThis;
    }

    PaneCourController(Cours c, int i, MenuMembreComiteController aThis, int num) {
        this.cour = c;
        this.selectedBtn = i;
        parentComit = aThis;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        myVariable = cour.getUtilisateur().getUsename();
        if (selectedBtn == 1) {
            btnAjout.setVisible(true);
            Ajout.setVisible(true);
            btnSuprim.setVisible(false);
            Supprim.setVisible(false);
            btnLire.setVisible(false);
            accepted.setVisible(false);
            refused.setVisible(false);
            waiting.setVisible(false);
            btnSuprimCour.setVisible(false);
            btnAcceptCour.setVisible(false);
            btnRefuseCour.setVisible(false);
            btnLireComite.setVisible(false);

        } else if (selectedBtn == 2) {
            btnAjout.setVisible(false);
            Ajout.setVisible(false);
            btnSuprim.setVisible(true);
            Supprim.setVisible(true);
            btnLire.setVisible(true);
            accepted.setVisible(false);
            refused.setVisible(false);
            waiting.setVisible(false);
            btnSuprimCour.setVisible(false);
            btnAcceptCour.setVisible(false);
            btnRefuseCour.setVisible(false);
            btnLireComite.setVisible(false);
        } else if (selectedBtn == 3) {
            btnAjout.setVisible(false);
            Ajout.setVisible(false);
            btnSuprim.setVisible(false);
            Supprim.setVisible(false);
            btnLire.setVisible(false);
            btnSuprimCour.setVisible(false);
            btnAcceptCour.setVisible(false);
            btnRefuseCour.setVisible(false);
            btnLireComite.setVisible(false);
            btnModifier.setVisible(true);
            if (cour.getEtat_cours().equals("accepter")) {
                accepted.setVisible(true);
                refused.setVisible(false);
                waiting.setVisible(false);
                meschapitres.setVisible(true);
                meschapitres.setTooltip(new Tooltip("Voir les Chapitres"));
            } else if (cour.getEtat_cours().equals("en attente")) {
                accepted.setVisible(false);
                refused.setVisible(false);
                waiting.setVisible(true);
            } else {
                accepted.setVisible(false);
                refused.setVisible(true);
                waiting.setVisible(false);
                Supprim.setVisible(false);
                btnSuprim.setVisible(false);
                btnSuprimCour.setVisible(true);
                btnLire.setVisible(false);

            }

        } else {
            accepted.setVisible(false);
            refused.setVisible(false);
            waiting.setVisible(false);
            Supprim.setVisible(false);
            btnSuprim.setVisible(false);
            btnSuprimCour.setVisible(false);
            btnAcceptCour.setVisible(true);
            btnRefuseCour.setVisible(true);
            btnLire.setVisible(true);
            btnLireComite.setVisible(true);

        }
        Platform.runLater(() -> {
            String sh1 = cour.getTitre_cours();
//            String sh2 = cour.getObjectif_cours();
//            if (sh1.length() > 20) {
//                sh1 = sh1.substring(1, 19);
//            }
            labelTitre.setText(sh1);

            labelrealise.setText(cour.getUtilisateur().getUsename());

            labelnbrVu.setText(String.valueOf(cour.getNmbr_vu()));

            discipline = disciplineDao.findDisciplineById(cour.getDiscipline().getId());

            try {

                file = new File("C:\\wamp\\www\\MOOCV5\\web\\uploads\\discipline\\logo\\" + discipline.getLogo());

                FileInputStream inputStream = new FileInputStream(file);
                IMGdiscipline.setImage(new Image(inputStream));

            } catch (FileNotFoundException ex) {
                System.out.println(file.getAbsolutePath() + "aaaaaaaaaaaa");
            }

        });

        btnAjout.setTooltip(new Tooltip("Ajouter a Bibliotheque"));
        btnSuprim.setTooltip(new Tooltip("Supprimer a la Bibliotheque"));
        accepted.setTooltip(new Tooltip("cours valider"));
        refused.setTooltip(new Tooltip("Cours refuser"));
        waiting.setTooltip(new Tooltip("Cours pas encore valider"));
        btnSuprimCour.setTooltip(new Tooltip("supprimer cours "));
        btnRefuseCour.setTooltip(new Tooltip("Refuser Cours"));
        btnAcceptCour.setTooltip(new Tooltip("Accepter Cours"));

    }

    @FXML
    public void Ajouterbibliotheque() {

        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Confirmation Ajout Cours");
        alert.setHeaderText("");

        alert.setContentText("Voulez vous ajouter (" + cour.getTitre_cours() + ") a votre Bibliotheque?");
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonData.OK_DONE);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setX(920);
        alert.setY(620);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/com/esprit/mooc/css/myDailog.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        ButtonBar buttonBar = (ButtonBar) alert.getDialogPane().lookup(".button-bar");
        buttonBar.setStyle("-fx-font-size: 10px;"
                + "-fx-background-color:  #0097A7;");
        buttonBar.getButtons().forEach(b -> b.setStyle("-fx-font-family: \"System\"; -fx-font-weight: bold;-fx-font-size: 10px;"));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            bibliothequeDAO.addCourBib(cour);
            System.out.println("c bn bib");
        } else {
            System.out.println("bib no");
        }

    }

    @FXML
    void SupprimerBibliotheque() throws IOException {

        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Confirmation supression");
        alert.setHeaderText("");

        alert.setContentText("Voulez vous supprimer (" + cour.getTitre_cours() + ") de votre Bibliotheque?");
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonData.OK_DONE);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setX(920);
        alert.setY(620);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/com/esprit/mooc/css/myDailog.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        ButtonBar buttonBar = (ButtonBar) alert.getDialogPane().lookup(".button-bar");
        buttonBar.setStyle("-fx-font-size: 10px;"
                + "-fx-background-color:  #0097A7;");
        buttonBar.getButtons().forEach(b -> b.setStyle("-fx-font-family: \"System\"; -fx-font-weight: bold;-fx-font-size: 10px;"));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            bibliothequeDAO.removeCourBib(cour);
            parentApprenant.maBibliotheque();
            System.out.println("SUP bib");
        } else {
            System.out.println("bib no");
        }

    }

    @FXML
    void SupprimerRefusedCour() throws IOException {

        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Confirmation Delete");
        alert.setHeaderText("");

        alert.setContentText("Voulez vous supprimer (" + cour.getTitre_cours() + ") ?");
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonData.OK_DONE);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setX(920);
        alert.setY(620);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/com/esprit/mooc/css/myDailog.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        ButtonBar buttonBar = (ButtonBar) alert.getDialogPane().lookup(".button-bar");
        buttonBar.setStyle("-fx-font-size: 10px;"
                + "-fx-background-color:  #0097A7;");
        buttonBar.getButtons().forEach(b -> b.setStyle("-fx-font-family: \"System\"; -fx-font-weight: bold;-fx-font-size: 10px;"));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            courDAO.SupprimerCour(cour.getId());
            parentFormateur.MesCours();
            System.out.println("SUP Cours");
        } else {
            System.out.println("bib no");
        }

    }

    @FXML
    void LireLaSuiteCours() {

        System.out.println("je marche");
        config2 con = new config2();
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/CoursDetails.fxml"));
        CoursDetailsController coursDetailsController = new CoursDetailsController(cour);
        loader.setController(coursDetailsController);

        loader.setController(coursDetailsController);
        con.loadPane(parentApprenant.paneData, loader);

    }

    @FXML
    void LireLaSuiteCoursComite() {
        System.out.println("je marche");
        config2 con = new config2();
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/CoursDetails.fxml"));
        CoursDetailsController coursDetailsController = new CoursDetailsController(cour);

        loader.setController(coursDetailsController);

        loader.setController(coursDetailsController);

        con.loadPane(parentComit.paneData, loader);

    }

    @FXML
    void RefusedCour() {

        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Refuser Cours");
        alert.setHeaderText("");

        alert.setContentText("Voulez vous Refusez (" + cour.getTitre_cours() + ")?");
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonData.OK_DONE);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setX(920);
        alert.setY(620);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/com/esprit/mooc/css/myDailog.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        ButtonBar buttonBar = (ButtonBar) alert.getDialogPane().lookup(".button-bar");
        buttonBar.setStyle("-fx-font-size: 10px;"
                + "-fx-background-color:  #0097A7;");
        buttonBar.getButtons().forEach(b -> b.setStyle("-fx-font-family: \"System\"; -fx-font-weight: bold;-fx-font-size: 10px;"));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            courDAO.RefuserCour(cour);
            try {
                parentComit.ListCour();

            } catch (IOException ex) {
                Logger.getLogger(PaneCourController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("refuser Cours");
            System.out.println("SUP Cours");
        } else {
            System.out.println("bib no");
        }

    }

    @FXML
    void AcceptCour() throws IOException {

        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Validation de Cours");
        alert.setHeaderText("");

        alert.setContentText("Voulez vous Valider (" + cour.getTitre_cours() + ") ?");
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonData.OK_DONE);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setX(920);
        alert.setY(620);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/com/esprit/mooc/css/myDailog.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        ButtonBar buttonBar = (ButtonBar) alert.getDialogPane().lookup(".button-bar");
        buttonBar.setStyle("-fx-font-size: 10px;"
                + "-fx-background-color:  #0097A7;");
        buttonBar.getButtons().forEach(b -> b.setStyle("-fx-font-family: \"System\"; -fx-font-weight: bold;-fx-font-size: 10px;"));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            courDAO.AccepterCour(cour);
            parentComit.ListCour();
            System.out.println("refuser Cours");
        } else {
            System.out.println("bib no");
        }

    }

    @FXML
    public void voirlistechap() {

        parentFormateur.tf.getChildren().clear();
        parentFormateur.paneData.getChildren().clear();
        parentFormateur.creerchapitre.setVisible(true);
        parentFormateur.anchorrechchap.setVisible(true);
        ChapitreDao courDao = new ChapitreDao();
        List<Chapitre> lCour = courDao.find2Chapitre(cour.getId());
        for (Chapitre c : lCour) {

            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneChapitre.fxml"));
            PaneChapitreController paneController = new PaneChapitreController(c, this);

            loader.setController(paneController);
            try {
                parentFormateur.anch = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            parentFormateur.tf.getChildren().add(parentFormateur.anch);
        }

        parentFormateur.paneData.getChildren().addAll(parentFormateur.tf);
    }

    @FXML
    public void ModifierCours() {

        
        config2 con = new config2();

        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/Cour.fxml"));
        System.out.println("id Cours="+cour.getId());
        CourController courController = new CourController(loggedUser, cour,2);

        loader.setController(courController);
        System.out.println("ghghjgj");
        con.loadPane(parentFormateur.paneData, loader);

    }

    public void refreshlistechap(int id) {
        parentFormateur.tf.getChildren().clear();
        parentFormateur.paneData.getChildren().clear();
        parentFormateur.creerchapitre.setVisible(true);
        parentFormateur.anchorrechchap.setVisible(true);
        ChapitreDao courDao = new ChapitreDao();
        List<Chapitre> lCour = courDao.find2Chapitre(id);
        for (Chapitre c : lCour) {

            loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/PaneChapitre.fxml"));
            PaneChapitreController paneController = new PaneChapitreController(c, this);

            loader.setController(paneController);
            try {
                parentFormateur.anch = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            parentFormateur.tf.getChildren().add(parentFormateur.anch);
        }

        parentFormateur.paneData.getChildren().addAll(parentFormateur.tf);
    }
}
