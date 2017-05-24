/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.BibliothequeDAO;
import com.esprit.mooc.DAO.ChapitreDao;
import com.esprit.mooc.DAO.CourDAO;
import com.esprit.mooc.DAO.UtilisateurDAO;
import com.esprit.mooc.Entities.Chapitre;
import com.esprit.mooc.Entities.Cours;
import com.esprit.mooc.Entities.Utilisateur;
import static com.esprit.mooc.controllers.CoursDetailsController.setMyVariable;
import com.esprit.mooc.ressources.config2;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class ChapitreDetailsController implements Initializable {

//    @FXML
//    private Label labelDatechap;
//
    
    double i;
    @FXML
    private ImageView N;
    @FXML
    private Label Notificate;
    @FXML
    private AnchorPane Bonnechance;
   @FXML
    private Button contenubutton;
    @FXML
    private Button preschap;
    @FXML
    private Label labeltitre;
   @FXML
   private WebView descripchap;
   @FXML
   private Label username;
   @FXML
   private Label dateajout;
       @FXML
    private MediaView video;
    private MediaPlayer mp;
    private Media me ;
    @FXML
    private WebView contenuchap;
   @FXML
    private ListView<Chapitre> lvchap;
    @FXML
    private Label dateconx;
    @FXML
    AnchorPane paneData = new AnchorPane();
    @FXML
    private TextField recherchechap;
    
                String[] Strings = {
                    "Ne retardez pas vos révisions ", "Choisissez un endroit plus adapté.","Oubliez pas de faire vos prières","Restez pas trop longtemps devant votre pc","Ne combattez pas votre fatigue, travaillez avec.","En cas de problème,veuillez nous contacter",
                };
//
//    @FXML
//    private MediaView video;
//
//    @FXML
//    private WebView browserobjChap;
//  
   @FXML
   private Chapitre c;
   private WebEngine webEngine = new WebEngine();
    private WebEngine webEngineContenu = new WebEngine();
    private Utilisateur loggedUser;

    ChapitreDetailsController(Chapitre chap) {
        this.c = chap;
        Timeline timeline2= new Timeline(new KeyFrame(
        Duration.millis(2500),
        ae -> labelchanger()));
 timeline2.play();
        Timeline timeline3= new Timeline(new KeyFrame(
        Duration.millis(2500),
        ae -> elephantbouger()));
 timeline3.play();
    }

    public void RemplirListChap() throws SQLException, ClassNotFoundException {

        ChapitreDao ch = new ChapitreDao();

        lvchap.setItems(ch.findChapitreById(c.getId_cours_chapitre(),c.getId_chapitre()));

        lvchap.setCellFactory(new Callback<ListView<Chapitre>, ListCell<Chapitre>>() {
            @Override
            public ListCell<Chapitre> call(ListView<Chapitre> param) {
                VBox vb = new VBox();
                HBox hb = new HBox();

                hb.setPadding(new Insets(20, 20, 20, 20));
                Text titreChapitre = new Text();
                titreChapitre.setFont(Font.font ("Berlin Sans FB", 13));
                titreChapitre.setFill(Paint.valueOf("#0097A7"));
                titreChapitre.setWrappingWidth(170);
                WebView descrip = new WebView();
                descrip.setMaxSize(250, 100);
                // descrip.setDisable(false);
                descrip.contextMenuEnabledProperty().set(false);
                //System.out.println(f.toPath().toString());
                ImageView imageView = new ImageView();

                ListCell<Chapitre> Cell = new ListCell<Chapitre>() {
                    @Override
                    protected void updateItem(Chapitre item, boolean empty) {
                        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.

                        if (item != null) {

                            titreChapitre.setText(item.getTitre_chapitre());
                            descrip.getEngine().loadContent(item.getDescription_chapitre());

                        }

                    }

                };

                imageView.setFitHeight(100);
                imageView.setFitWidth(100);

                hb.getChildren().addAll(titreChapitre);
                // hb.getChildren().addAll(imageView, vb, detail, sup, modif, bib);
                Cell.setGraphic(hb);
                return Cell;

            }
        });

    }
    
    public void RemplirListChapRech(String rech) throws SQLException, ClassNotFoundException {

        ChapitreDao ch = new ChapitreDao();

        lvchap.setItems(ch.findChapitreRech(c.getId_cours_chapitre(),c.getId_chapitre(),rech));

        lvchap.setCellFactory(new Callback<ListView<Chapitre>, ListCell<Chapitre>>() {
            @Override
            public ListCell<Chapitre> call(ListView<Chapitre> param) {
                VBox vb = new VBox();
                HBox hb = new HBox();

                hb.setPadding(new Insets(20, 20, 20, 20));
                Text titreChapitre = new Text();
                titreChapitre.setFont(Font.font ("Berlin Sans FB", 13));
                titreChapitre.setFill(Paint.valueOf("#0097A7"));
                titreChapitre.setWrappingWidth(170);
                WebView descrip = new WebView();
                descrip.setMaxSize(250, 100);
                // descrip.setDisable(false);
                descrip.contextMenuEnabledProperty().set(false);
                //System.out.println(f.toPath().toString());
                ImageView imageView = new ImageView();

                ListCell<Chapitre> Cell = new ListCell<Chapitre>() {
                    @Override
                    protected void updateItem(Chapitre item, boolean empty) {
                        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.

                        if (item != null) {

                            titreChapitre.setText(item.getTitre_chapitre());
                            descrip.getEngine().loadContent(item.getDescription_chapitre());
                        }

                    }

                };

                imageView.setFitHeight(100);
                imageView.setFitWidth(100);

                hb.getChildren().addAll(titreChapitre);
                // hb.getChildren().addAll(imageView, vb, detail, sup, modif, bib);
                Cell.setGraphic(hb);
                return Cell;

            }
        });

    }
     @FXML
    public void ConvertContenuChapPDF() throws FileNotFoundException, DocumentException, IOException {
  OutputStream file = new FileOutputStream(new File("C:\\Users\\Anas\\Desktop\\"+c.getTitre_chapitre()+".pdf"));


            Document document = new Document();

  
            PdfWriter.getInstance(document, file);
          Font font1 = new Font("Arial",25);

            document.open();
          document.addAuthor(CoursDetailsController.getMyVariable());
            document.addCreationDate();
            document.addCreator("JavaBeat");
            document.addTitle("Sample PDF");
            document.add(new Paragraph(c.getContenu_chapitre()));

            document.add(new Paragraph(new Date().toString()));

            document.close();

            file.close();
    }
        public void ConvertPresChap() throws FileNotFoundException, DocumentException, IOException {
  OutputStream file = new FileOutputStream(new File("C:\\Users\\Anas\\Desktop\\"+c.getTitre_chapitre()+".pdf"));


            Document document = new Document();

  
            PdfWriter.getInstance(document, file);
          Font font1 = new Font("Arial",25);

            document.open();
          document.addAuthor(CoursDetailsController.getMyVariable());
            document.addCreationDate();
            document.addCreator("JavaBeat");
            document.addTitle("Sample PDF");
            document.add(new Paragraph(c.getDescription_chapitre()));
            document.add(new Paragraph(new Date().toString()));
            document.close();

            file.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UtilisateurDAO ul = new UtilisateurDAO();
        try {
            dateconx.setText(ul.findlastlogin(CoursDetailsController.getMyVariable()));
        } catch (SQLException ex) {
            Logger.getLogger(ChapitreDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Notificate.setText("Choisissez bien votre temps de révision!");
        String mp2 = "C:\\wamp\\www\\MOOCV4\\web\\uploads\\"+c.getChemin_chapitre();
       labeltitre.setText(c.getTitre_chapitre());
               username.setText(CoursDetailsController.getMyVariable());
        dateajout.setText(c.getDate_ajout().toString().substring(0, 10));
        System.out.println(c.getDate_ajout().toString().substring(0, 10));
        preschap.setTooltip(new Tooltip("Telecharger la presentation du chapitre"));
        contenubutton.setTooltip(new Tooltip("Télécharger le contenu du chapitre"));
        webEngine = descripchap.getEngine();
        webEngine.loadContent(c.getDescription_chapitre());
        descripchap.getEngine().setUserStyleSheetLocation(getClass().getResource("/com/esprit/mooc/css/webView2.css").toString());
        descripchap.setBlendMode(BlendMode.DARKEN);
        descripchap.setVisible(true);
        webEngineContenu = contenuchap.getEngine();
        webEngineContenu.loadContent(c.getContenu_chapitre());
        contenuchap.getEngine().setUserStyleSheetLocation(getClass().getResource("/com/esprit/mooc/css/webView2.css").toString());
        contenuchap.setBlendMode(BlendMode.DARKEN);
        contenuchap.setVisible(true);
        String path= new File("C:\\wamp\\www\\MOOCV4\\web\\uploads\\"+c.getChemin_video_chapitre()).getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp =new MediaPlayer(me);
        video.setMediaPlayer(mp);
        mp.setAutoPlay(true);
               try {
            RemplirListChap();
        } catch (SQLException ex) {
            Logger.getLogger(CoursDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoursDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }

                       Timeline timeline = new Timeline();
timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(5),
    new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            Bonnechance.setVisible(false);
        }
    }));
timeline.play();

    }
        @FXML
    public void ClickTableView(MouseEvent arg0) {
                setMyVariable(username.getText());
        config2 con = new config2();
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/ChapitreDetails.fxml"));
        ChapitreDetailsController chapitreDetailsController = new ChapitreDetailsController(lvchap.getSelectionModel().getSelectedItem());
        System.out.println(lvchap.getSelectionModel().getSelectedItem().getDate_ajout());
        loader.setController(chapitreDetailsController);
        con.loadPane(paneData, loader);

    }
    @FXML
    public void RechercheChapitre() throws SQLException, ClassNotFoundException{
            String rech=recherchechap.getText();
        if(rech.isEmpty()){
            this.RemplirListChap();
        }
        else{
RemplirListChapRech(rech);
    }
    
    }

    private void labelchanger() {
        for(int y=0;y<1000;y++){
int lower = 0;
int higher = 6;

int random = (int)(Math.random() * (higher-lower)) + lower;
Notificate.setText(Strings[random]);

    }
                      Timeline timeline2 = new Timeline();
timeline2.getKeyFrames().add(new KeyFrame(Duration.seconds(5),
    new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            labelchanger();
             i=i+3;
            N.setTranslateX(i);

        }
    }));
timeline2.play();

}
        private void elephantbouger() {

                      Timeline timeline2 = new Timeline();
timeline2.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
    new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            elephantbouger();
             i=i+3;
            N.setTranslateX(i);
                        System.out.println(i);
if(i==255.0){
            N.setTranslateX(-255);
           
            i=0;

}
        }
    }));
timeline2.play();

}
        
}