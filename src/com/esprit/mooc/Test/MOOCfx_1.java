/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.mooc.Test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author kods
 */
public class MOOCfx_1 extends Application {
    
    public static MOOCfx_1 INSTANCE;
    private Stage primaryStage;    
    
    @Override
    public void start(Stage stage) throws Exception {
        INSTANCE=this;
        Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/mooc/moocfx_1/Load.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Authentification");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        Timeline timeline = new Timeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2),
        new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/com/esprit/mooc/moocfx_1/authentification.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
       
        stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MOOCfx_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }));
timeline.play();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
