package com.example.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.w3c.dom.Element;

import java.util.HashMap;
import java.util.Map;

public class Registration {

    public Text totalCalories;

    int i = 1;
        Map<String, String> userInput = new HashMap<>();
        public GridPane textField;

        @FXML
        TextField menu;
        @FXML
        TextField calories;


        @FXML
        public void buttonClicked(ActionEvent actionEvent) {
            userInput.put(menu.getText(), calories.getText());
            System.out.println(userInput.keySet());
            System.out.println(userInput);

            // System.out.println(calories.getText());

        }
        @FXML
        public void addMenuClicked(ActionEvent actionEvent) {

            if (menu.getText() == "" || calories.getText() == "")
            {
                final Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("Please Input data!");
                alert.showAndWait();
            }
            else {

                userInput.put(menu.getText(), calories.getText());
                textField.setHgap(10);
                textField.setVgap(10);
                menu = new TextField();
                calories = new TextField();
                textField.add(menu, 0, i);
                textField.add(calories, 1, i);
                i = i + 1;
            }

            int totalCal = 0;
            for(String key: userInput.keySet()){
                String value = userInput.get(key);
                totalCal += Integer.parseInt(value);
            }
            totalCalories.setText("Total calories: " + totalCal + "Kcal");

        }
}
