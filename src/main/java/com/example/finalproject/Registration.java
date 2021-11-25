package com.example.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Registration {
    int i = 1;

    ArrayList<String> menuss = new ArrayList<>();
    ArrayList<String> caloriess = new ArrayList<>();
    public GridPane textField;

    public void addMenu(){

    }
    @FXML
    TextField menu;
    @FXML
    TextField calories;

    @FXML
    public void buttonClicked(ActionEvent actionEvent) {
        menuss.add(menu.getText());
        caloriess.add(calories.getText());
        System.out.print(menuss);
        System.out.print(caloriess);
    }

    @FXML
    public void addMenuClicked(ActionEvent actionEvent) {
        if (menu.getText() == "" || calories.getText() == "") {
            final Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Please Input data!");
            alert.showAndWait();
        }
        else {
            menuss.add(menu.getText());
            caloriess.add(calories.getText());
            textField.setHgap(10);
            menu = new TextField();
            calories = new TextField();
            textField.add(menu, 0, i);
            textField.add(calories, 1, i);
            i = i + 1;
        }
  }
}