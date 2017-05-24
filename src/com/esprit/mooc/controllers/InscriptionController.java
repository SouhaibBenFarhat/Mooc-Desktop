/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ressources.FadeInLeftTransition;
import ressources.FadeInLeftTransition1;
import ressources.FadeInRightTransition;

/**
 *
 * @author kods
 */
public class InscriptionController implements Initializable {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @FXML
    private Label label;
    @FXML
    private Button btnChoisirCV;
    @FXML
    private Button btnInscription;
    @FXML
    private TextField cvText;
    @FXML
    private TextField usernameText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField passwordText;

    @FXML
    private Text lblUserLogin;
    @FXML
    private Text lblUsername;
    @FXML
    private Text lblPassword;

    @FXML
    private Label lblClose;

    @FXML
    private Text lblEmail;

     @FXML
    private Text lblCV;
      @FXML
    private Text lblSexe;
       @FXML
    private Text lblSpecialite;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            new FadeInRightTransition(lblUserLogin).play();
            new FadeInLeftTransition1(lblPassword).play();
            new FadeInLeftTransition1(lblUsername).play();
            new FadeInLeftTransition1(lblEmail).play();
            new FadeInLeftTransition1(usernameText).play();
            new FadeInLeftTransition1(passwordText).play();
            new FadeInLeftTransition1(emailText).play();
            new FadeInRightTransition(btnInscription).play();
            new FadeInLeftTransition1(lblCV).play();
            new FadeInLeftTransition1(lblSexe).play();
            new FadeInLeftTransition1(lblSpecialite).play();
           

            label.setTextFill(Color.web("#FFFFFF"));

        });
         
    }

    @FXML
    private void inscriptionActionApprenant(ActionEvent event) {
        if (usernameText.getText().equals("") || emailText.getText().equals("") || !mailValid(emailText.getText()) || passwordText.getText().equals("")) {
            System.out.println("erreur il y a des champs vide ou bien l'email n'est pas valide");
        } else {
            try {
                Utilisateur user = new Utilisateur();
                UtilisateurDAO userDao = new UtilisateurDAO();
                user.setUsename(usernameText.getText());
                user.setUsenameCanonical(usernameText.getText());
                user.setEmail(emailText.getText());
                user.setEmailCanonical(emailText.getText());
                user.setPassword(passwordText.getText());
                
                user.setRole("a:1:{i:0;s:14:ROLE_APPRENANT;}");
                userDao.ajouterUtilisateur(user);

                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/com.esprit.mooc.moocfx_1/espaceFormateur.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
                System.out.println(" U did it man");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("U didn't do it man");
            }

        }
    }

    @FXML
    private void btnChoisirAction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        fc.getExtensionFilters().addAll(new ExtensionFilter("PDF FILES", "*.pdf"));
        if (selectedFile != null) {

            label.setText(selectedFile.getAbsolutePath());

        } else {
            System.out.println("erreur");
        }
    }

    @FXML
    public void btnChoisirAttestationAction(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        fc.getExtensionFilters().addAll(new ExtensionFilter("PDF FILES", "*.pdf"));
        if (selectedFile != null) {

            label.setText(selectedFile.getAbsolutePath());

        } else {
            System.out.println("erreur");
        }
    }

    public boolean mailValid(String mail) {

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(mail);
        return matcher.matches();
    }

    public void inscriptionEntrepriseAction(ActionEvent e) {

    }

    public void inscriptionFormateurAction(ActionEvent e) {

    }
}
