package com.example.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;


public class CalendarController {
    @FXML
    private Text title;

    @FXML
    protected void onHelloButtonClick() {
        title.setText("Calendar!");
    }

    @FXML
    public void onHandleFood(ActionEvent actionEvent) {
        Button btnClicked = (Button) actionEvent.getSource();
        title.setText(btnClicked.getId());
    }
}