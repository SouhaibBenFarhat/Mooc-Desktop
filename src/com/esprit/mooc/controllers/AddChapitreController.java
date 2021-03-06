/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.ChapitreDao;
import com.esprit.mooc.Entities.Chapitre;
import com.github.plushaze.traynotification.notification.Notification;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 *
 * @author Anas
 */
public class AddChapitreController implements Initializable {
    @FXML
    private AnchorPane step1chap;
    @FXML
    private AnchorPane step2chap;
    @FXML
    public TextField videochap;
    @FXML
    public TextField pdfcontenu;
    @FXML
    public TextField titrechap;
    @FXML
    public TextArea descriptionchap;
    @FXML
    public TextArea contenuchap;
    @FXML
    public TextField dureechap;
    @FXML
    public TextArea introchap;
    @FXML
    public TextArea objchap;
    @FXML
    public TextField preschap;
    ObservableList<String> options = FXCollections.observableArrayList(
        "Facile",
        "Normal",
        "Difficile"
    );
    @FXML
    public ComboBox niveauchap;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    niveauchap.setItems(options);
    step1chap.setVisible(true);
    step2chap.setVisible(false);
    introchap.setWrapText(true);
    objchap.setWrapText(true);
    descriptionchap.setWrapText(true);
    }
    @FXML
    private void goetape2(){
    step1chap.setVisible(false);
    step2chap.setVisible(true);
    }
    @FXML
    private void retourstep1(){
            step1chap.setVisible(true);
    step2chap.setVisible(false);
    }
    @FXML
      public void ParcourirVideo() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Video files (*.mp4)", "*.mp4");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
        System.out.println(file);
        videochap.setText(file.getAbsolutePath());
    }
      @FXML
          public void ParcourirPres(){
            FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
        System.out.println(file);
        preschap.setText(file.getAbsolutePath());
    }
          @FXML
               public void ParcourirContenu(){
            FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
        System.out.println(file);
        pdfcontenu.setText(file.getAbsolutePath());
    }     
    @FXML
    public void ajouterchap(){
        String source = videochap.getText();
        Path path = Paths.get(source);
        String source2 = pdfcontenu.getText();
        Path path2 = Paths.get(source2);
        String source3 = preschap.getText();
        Path path3 = Paths.get(source3);
        String target_dir = "C:\\wamp\\www\\MOOCV4\\web\\uploads\\" + path.getFileName();
        String target_dir2 = "C:\\wamp\\www\\MOOCV4\\web\\uploads\\" + path2.getFileName();
        String target_dir3 = "C:\\wamp\\www\\MOOCV4\\web\\uploads\\" + path3.getFileName();
        Path target = Paths.get(target_dir);
        Path target2 = Paths.get(target_dir2);
        Path target3 = Paths.get(target_dir3);
        try {
            Files.copy(path, target, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(path2, target2, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(path3, target3, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            System.out.println("noplease");
        }
	   //get current date time with Date()
	   Date date = new Date();
         DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
         String currDate=dateFormat.format(date);
        Chapitre c = new Chapitre(PaneChapitreController.getIdcours(), titrechap.getText(), descriptionchap.getText(), pdfcontenu.getText(), path.getFileName().toString(), preschap.getText(),contenuchap.getText(),Integer.parseInt(dureechap.getText()),(String)niveauchap.getValue(),introchap.getText(),objchap.getText(),currDate);
        ChapitreDao chap = new ChapitreDao();
        chap.ajouterChapitre(c);
        String title = "Félicitation";
        String message = "L'ajout du chapitre: "+titrechap.getText()+" a été établi avec succès";
        Notification notification = Notifications.SUCCESS;

        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotification(notification);
        tray.showAndDismiss(Duration.seconds(2));
        PaneCourController paneCoursController = new PaneCourController();
        paneCoursController.refreshlistechap(PaneChapitreController.getIdcours());
    }
}
