package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.scene.text.Text;


public class CalendarController {
    @FXML
    private Text title;

    @FXML
    protected void onHelloButtonClick() {
        title.setText("Calendar!");
    }
}