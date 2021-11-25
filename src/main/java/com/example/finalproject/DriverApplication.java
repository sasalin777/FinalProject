package com.example.finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DriverApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DriverApplication.class.getResource("calendar-view.fxml"));
        // get the width and height of the screen
        double width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        // set the scene
        Scene scene = new Scene(fxmlLoader.load(), width, height);
      
        stage.setTitle("DietTrack");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}