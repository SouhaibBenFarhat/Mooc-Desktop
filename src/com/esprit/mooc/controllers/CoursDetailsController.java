/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.controllers;

import com.esprit.mooc.DAO.BibliothequeDAO;
import com.esprit.mooc.DAO.ChapitreDao;
import com.esprit.mooc.DAO.CourDAO;
import com.esprit.mooc.Entities.Chapitre;
import com.esprit.mooc.Entities.Cours;
import com.esprit.mooc.Entities.Utilisateur;
import com.esprit.mooc.ressources.config2;
import com.itextpdf.text.Document;
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
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.Media;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class CoursDetailsController implements Initializable {
    @FXML
    AnchorPane paneData = new AnchorPane();

    @FXML
    private Label labelDate;

    @FXML
    private Label LabelTitreCours;

    @FXML
    private ListView<Chapitre> lvChapitre;

    @FXML
    private Label Labelauteur;

    @FXML
    private WebView browserDes;

    @FXML
    private MediaView video;

    @FXML
    private WebView browserobj;
    private Cours c;

    @FXML

    private WebEngine webEngine = new WebEngine();
    private WebEngine webEngineObjectif = new WebEngine();
    private Utilisateur loggedUser;

    private MediaPlayer mp;
    private Media me;
    private static String myVariable;


    CoursDetailsController(Cours cour) {

        this.c = cour;

    }
    public static String getMyVariable() {
        return myVariable;
    }
    public static void setMyVariable(String myVariable) {
        CoursDetailsController.myVariable = myVariable;
    }

    @FXML
    public void ConvertCourPDF() {
        System.out.println("pdf OK");
        String nomPDF = c.getTitre_cours();
        try {
            OutputStream file = new FileOutputStream(new File("C:\\Users\\Souhaib\\Documents\\NetBeansProjects\\Cours_PDF\\"+nomPDF+".pdf"));

            Document document = new Document();
            PdfWriter.getInstance(document, file);

            document.open();
            document.add(new Paragraph(c.getDiscipline().getNomDiscipline()));
            document.add(new Paragraph(new Date().toString()));

            document.addAuthor(c.getUtilisateur().getUsename());
            document.addCreationDate();
            document.addCreator("JavaBeat");
            document.addTitle("Sample PDF");

            //Create Paragraph
            Paragraph paragraph = new Paragraph(c.getTitre_cours(), new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 18,com.itextpdf.text.Font.BOLD));

            //New line
            paragraph.add(new Paragraph(" "));
            paragraph.add(new Paragraph("Objectif du cours"));
            paragraph.add(new Paragraph(c.getObjectif_cours()));
            paragraph.add(new Paragraph(" "));
            paragraph.add(new Paragraph("Description"));
            paragraph.add(new Paragraph(c.getDescription_cours()));
            paragraph.add(new Paragraph(" "));
            paragraph.add(new Paragraph("Prerequis du cours"));
            paragraph.add(new Paragraph(c.getPrerequis_cours()));
            document.add(paragraph);

            document.close();
            file.close();
            System.out.println("pdf Done");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

  public void RemplirListCour() throws SQLException, ClassNotFoundException {

        ChapitreDao ch = new ChapitreDao();

        lvChapitre.setItems(ch.findChapitreByCour(c));

        lvChapitre.setCellFactory(new Callback<ListView<Chapitre>, ListCell<Chapitre>>() {
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        LabelTitreCours.setText(c.getTitre_cours());
        Labelauteur.setText(c.getUtilisateur().getUsename());
        labelDate.setText(c.getDate_ajout().toString().substring(0, 10));
        System.out.println(c.getDate_ajout().toString().substring(0, 10));

        webEngine = browserDes.getEngine();
        webEngine.loadContent(c.getDescription_cours());
        browserDes.getEngine().setUserStyleSheetLocation(getClass().getResource("/com/esprit/mooc/css/webView2.css").toString());
        browserDes.setBlendMode(BlendMode.DARKEN);
        browserDes.setVisible(true);

        webEngineObjectif = browserobj.getEngine();
        webEngineObjectif.loadContent(c.getObjectif_cours());
        browserobj.getEngine().setUserStyleSheetLocation(getClass().getResource("/com/esprit/mooc/css/webView2.css").toString());
        browserobj.setBlendMode(BlendMode.DARKEN);
        browserobj.setVisible(true);
        try {
            RemplirListCour();
        } catch (SQLException ex) {
            Logger.getLogger(CoursDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoursDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String path = new File("C:\\wamp\\www\\MOOCV5\\web\\uploads\\video\\" + c.getVideo_cours()).getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        video.setMediaPlayer(mp);
        mp.setAutoPlay(true);

    }

    public void play(ActionEvent event) {
        mp.play();
    }
    
    public void pause(ActionEvent event) {
        mp.pause();
    }
    
    public void stop(ActionEvent event) {
        mp.seek(mp.getCurrentTime());
        mp.stop();
    }
    
    public void replay(ActionEvent event) {
        mp.seek(mp.getStartTime());
        mp.play();
    }

    @FXML
    public void ClickTableView(MouseEvent arg0) {
                setMyVariable(Labelauteur.getText());
        config2 con = new config2();
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("/com/esprit/mooc/moocfx_1/ChapitreDetails.fxml"));
        ChapitreDetailsController chapitreDetailsController = new ChapitreDetailsController(lvChapitre.getSelectionModel().getSelectedItem());
        loader.setController(chapitreDetailsController);
        con.loadPane(paneData, loader);

    }
}
