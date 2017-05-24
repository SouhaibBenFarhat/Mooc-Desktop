
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.InvitationEntrepriseFormateurDAO;
import com.esprit.mooc.DAO.InvitationFormateurEntrepriseDAO;
import com.esprit.mooc.DAO.PhotoUtilisateurDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.InvitationEntrepriseFormateur;
import com.esprit.mooc.Entities.InvitationFormateurEntreprise;
import com.esprit.mooc.Entities.PhotoUtilisateur;
import com.esprit.mooc.Entities.Utilisateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author kods
 */
public class PaneFormateurController implements Initializable {

    @FXML
    private Label labelNom;
    @FXML
    private Label labelEmail;
    @FXML
    private ImageView image;
    @FXML
    private Pane paneUser;
    @FXML
    private Button btnProfil;
    @FXML
    private Button btnLock;
    @FXML
    private Button btnUnlock;
    @FXML
    private Button btnAccept;
    @FXML
    private Button btnReject;
    @FXML
    private Button btnAcceptAppartenance;
    @FXML
    private Button btnRejectAppartenance;

    @FXML
    private Button btnRefuser;

    @FXML
    private Button btnAccepterFormateur;

    @FXML
    private Button btnAddFormateur;

    private MenuAdminController parentAdmin;
    private MenuMembreComiteController parentMembreComite;
    private MenuEntrepriseController parentEntreprise;
    Utilisateur loggedUser;
    Alert a = new Alert(Alert.AlertType.INFORMATION);

    Utilisateur formateur;
    UtilisateurDAO aO = new UtilisateurDAO();

    public PaneFormateurController(Utilisateur u) {
        formateur = u;
        btnUnlock = new Button();

    }

    PaneFormateurController(Utilisateur user, MenuAdminController aThis) {
        parentAdmin = aThis;
        this.formateur = user;
    }

    PaneFormateurController(Utilisateur user, MenuMembreComiteController aThis) {
        parentMembreComite = aThis;
        this.formateur = user;
    }

    PaneFormateurController(Utilisateur user, MenuEntrepriseController aThis) {
        parentEntreprise = aThis;
        this.formateur = user;
    }

    /**
     * Initializes the controller class.
     */
    @FXML
    public void refuserForamateurAction() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirmation supression");
        alert.setHeaderText("");

        alert.setContentText("Voulez vous refuser et supprimer (" + formateur.getUsename() + ") ?");
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
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
            aO.supprimerFormateur(formateur.getId());
            parentMembreComite.ListFormateur();
            System.out.println("SUP bib");
        } else {
            System.out.println("bib no");
        }
    }

    @FXML
    public void AccepterForamateurAction() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirmation supression");
        alert.setHeaderText("");

        alert.setContentText("Voulez vous accepter (" + formateur.getUsename() + ") ?");
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
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
            aO.accepterUtilisateur(formateur);
            parentMembreComite.ListFormateur();
            System.out.println("formateur accepter");
        } else {
            System.out.println("no accepter");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("comiter " + UtilisateurDAO.loggedUser.getRole().contains("COMITE"));
        Platform.runLater(() -> {
            labelNom.setText(formateur.getUsename());
            labelEmail.setText(formateur.getEmail());
            PhotoUtilisateur photoUtilisateur = new PhotoUtilisateurDAO().findPhotoById(formateur.getPhoto());
            File file;
            FileInputStream inputStream = null;
            if (photoUtilisateur.getPathPhoto() != null) {
                file = new File("C:\\wamp\\www\\MOOCV5\\web\\uploads\\userPhoto\\" + photoUtilisateur.getPathPhoto());
                System.out.println("la logo" + photoUtilisateur.getPathPhoto());

                try {
                    inputStream = new FileInputStream(file);
                    image.setImage(new Image(inputStream));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PaneEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    file = new File("C:\\wamp\\www\\MOOCV5\\web\\uploads\\userPhoto\\formateur.png");
                    inputStream = new FileInputStream(file);
                    image.setImage(new Image(inputStream));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PaneEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (UtilisateurDAO.loggedUser.getRole().contains("COMITE")) {
                btnRefuser.setVisible(true);
                btnAccepterFormateur.setVisible(true);

                if (formateur.getEnbaled() == 1 && formateur.getLocked() == 0) {
                    btnLock.setVisible(true);
                    btnUnlock.setVisible(false);
                } else if (formateur.getEnbaled() == 1 && formateur.getLocked() == 1) {
                    btnAccept.setVisible(true);
                    btnReject.setVisible(true);
                    btnLock.setVisible(false);
                    btnUnlock.setVisible(false);
                } else {
                    btnLock.setVisible(false);
                    btnUnlock.setVisible(true);
                }

            } else if (loggedUser.getRole().contains("ADMIN")) {
                if (formateur.getEnbaled() == 1 && formateur.getLocked() == 0) {
                    btnLock.setVisible(true);
                    btnUnlock.setVisible(false);
                } else {
                    btnLock.setVisible(false);
                    btnUnlock.setVisible(true);
                }

            } else if (loggedUser.getRole().contains("ENTREPRISE")) {

                InvitationFormateurEntreprise ife = new InvitationFormateurEntrepriseDAO().findInvitationByFormateurIdEntrepriseId(formateur.getId(), loggedUser.getId());

                if (ife.getId() == 0) {
                    btnLock.setVisible(false);
                    btnUnlock.setVisible(false);
                    btnAccept.setVisible(false);
                    btnReject.setVisible(false);
                    btnAddFormateur.setVisible(true);
                } else {
                    System.out.println("ife" + ife.getId());
                    System.out.println("etat" + ife.getEtat());
                    if (ife.getEtat().equalsIgnoreCase("en attente")) {
                        btnAcceptAppartenance.setVisible(true);
                        btnRejectAppartenance.setVisible(true);
                        btnAddFormateur.setVisible(false);
                        btnLock.setVisible(false);
                        btnUnlock.setVisible(false);
                    } else if (formateur.getIdEntrepriseUtilisateur() == loggedUser.getId()) {
                        btnAcceptAppartenance.setVisible(false);
                        btnRejectAppartenance.setVisible(false);
                        btnAddFormateur.setVisible(false);
                        btnLock.setVisible(false);
                        btnUnlock.setVisible(false);
                    }
                }

            }

            btnProfil.setOnMouseClicked((MouseEvent event) -> {
                Stage stage = new Stage();
                FXMLLoader loader;
                loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/InformationUser.fxml"));
                UserProfilController userProfilController = new UserProfilController(formateur);
                userProfilController.setLoggedUser(loggedUser);
                loader.setController(userProfilController);
                Parent root;
                try {
                    root = loader.load();
                    Scene scene = new Scene(root);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(PaneFormateurController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

            btnUnlock.setOnMouseClicked((MouseEvent event) -> {

                if (formateur.getRole().contains("FORMATEUR")) {
                    try {
                        aO.enableUtilisateur(formateur.getId());
                        parentAdmin.listeDisabledFormateursAction();
                    } catch (IOException ex) {
                        Logger.getLogger(PaneFormateurController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
            btnLock.setOnMouseClicked((MouseEvent event) -> {
                if (formateur.getRole().contains("FORMATEUR")) {
                    aO.disableUtilisateur(formateur.getId());
                    try {
                        parentAdmin.listeFormateursAction();
                    } catch (IOException ex) {
                        Logger.getLogger(PaneFormateurController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            });

            btnAccept.setOnMouseClicked((MouseEvent event) -> {
                aO.accepterUtilisateur(formateur);
                try {
                    parentAdmin.listeEntreprisesAction();
                } catch (IOException ex) {
                    Logger.getLogger(PaneFormateurController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

            btnReject.setOnMouseClicked((MouseEvent event) -> {
                try {
                    aO.supprimerEntreprise(formateur.getId());
                    UtilisateurDAO userD = new UtilisateurDAO();
                    userD.supprimerUtilisateur(formateur.getId());
                    parentAdmin.listeEntreprisesAction();
                } catch (IOException ex) {
                    Logger.getLogger(PaneFormateurController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
        });

        btnAddFormateur.setOnMouseClicked((MouseEvent event) -> {
            InvitationEntrepriseFormateur entrepriseFormateur = new InvitationEntrepriseFormateur();
            entrepriseFormateur.setIdEntreprise(loggedUser.getId());
            entrepriseFormateur.setIdFormateur(formateur.getId());
            InvitationEntrepriseFormateurDAO aO = new InvitationEntrepriseFormateurDAO();
            aO.envoyerInvitationEntrepriseFormateur(entrepriseFormateur);
            try {
                parentEntreprise.listeFormateursAction();
            } catch (IOException ex) {
                Logger.getLogger(PaneFormateurController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btnAcceptAppartenance.setOnMouseClicked((MouseEvent e) -> {

            InvitationFormateurEntrepriseDAO ife = new InvitationFormateurEntrepriseDAO();
            ife.accepterInvitation(formateur.getId(), loggedUser.getId());
            ife.supprimerInvitation(formateur.getId(), loggedUser.getId());
            parentEntreprise.listeInvitationFormateursAction();
        });
    }

    public Label getLabelNom() {
        return labelNom;
    }

    public void setLabelNom(Label labelNom) {
        this.labelNom = labelNom;
    }

    public Label getLblPrenom() {
        return labelEmail;
    }

    public void setLblPrenom(Label lblPrenom) {
        this.labelEmail = lblPrenom;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public Pane getPaneUser() {
        return paneUser;
    }

    public void setPaneUser(Pane paneUser) {
        this.paneUser = paneUser;
    }

    public Label getLabelEmail() {
        return labelEmail;
    }

    public void setLabelEmail(Label labelEmail) {
        this.labelEmail = labelEmail;
    }

    public Button getBtnProfil() {
        return btnProfil;
    }

    public void setBtnProfil(Button btnProfil) {
        this.btnProfil = btnProfil;
    }

    public Button getBtnLock() {
        return btnLock;
    }

    public void setBtnLock(Button btnLock) {
        this.btnLock = btnLock;
    }

    public Button getBtnUnlock() {
        return btnUnlock;
    }

    public void setBtnUnlock(Button btnUnlock) {
        this.btnUnlock = btnUnlock;
    }

    public Utilisateur getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Utilisateur loggedUser) {
        this.loggedUser = loggedUser;
    }

}
