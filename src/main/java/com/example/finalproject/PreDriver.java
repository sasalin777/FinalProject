package com.example.finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PreDriver extends Application {

    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(DriverApplication.class.getResource("update-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 1280);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("updateStyle.css")).toExternalForm());
        stage.setTitle("EnterFood");
        stage.setScene(scene);
        stage.show();
    }
}
