package com.example.finalproject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;


public class Registration {
    int i = 1;

    private
    Data db = Data.getInstance();


    @FXML
    private
    TextField mainAte;

    @FXML
    private
    Label totalCalories;

    @FXML
    TextField menu;

    @FXML
    TextField calories;

    public GridPane textField;

    public void addMenu(){
    }

    @FXML
    public void initialize() {

        try {
            // split the string into an array of strings
            String[] ids = db.getId().split("_");


            Day data = db.getMonths(db.getMonth())[db.getDay()];

            Menu menu = null;

            if(ids[0].equals("morning"))   menu = data.getMorning();
            if(ids[0].equals("afternoon")) menu = data.getAfternoon();
            if(ids[0].equals("evening"))   menu = data.getEvening();
            if(ids[0].equals("snacks"))    menu = data.getSnacks();

            mainAte.setText(menu.getIcon());

            System.out.println(menu.toString());

            totalCalories.setText("Total calories: " + menu.getKcal() + " kcal");

            Food[] food = menu.getFood();

            // iterate food
            for (Food f : food) {
                addMenu(f.getName(), f.getKcal() + "");
            }

        } catch (Exception e) {
          System.out.println( e);
        }
    }

    @FXML
    public void onHandleSave(ActionEvent actionEvent) {
        //System.out.print(menu.getText());
        //System.out.print(calories.getText());
        updateMenu();
    }

    public void updateMenu() {

       // create array of food with size of textField.getChildren().size() / 2
       Food[] food = new Food[(textField.getChildren().size() / 2 ) - 1];
        
       // iterate textField and get the text value menu and calories id
       for (int i = 1; i < textField.getChildren().size() / 2; i++) {

          // get GridPane by id
           TextField menu = (TextField) textField.lookup("#menu" + i);
           TextField calories = (TextField) textField.lookup("#calories" + i);

           System.out.println("----- i" + i + "----- text " + menu.getText() + "----- kcal" + calories.getText());

           // add new element array f
           food[i-1] = new Food(menu.getText(), Integer.parseInt(calories.getText()));
       }
       
       String icon = mainAte.getText().equals("") ? "\uD83C\uDF54" : mainAte.getText();
       Menu newMenu = new Menu(food, icon);

       // split the string into an array of strings
        String[] ids = db.getId().split("_");

        // Menu morning, Menu afternoon, Menu evening, Menu snacks)
        if(ids[0].equals("morning")) {
            db.getMonths(db.getMonth())[db.getDay()] = new Day(db.getDay(),2021,
                    newMenu,
                    db.getMonths(db.getMonth())[db.getDay()].afternoon,
                    db.getMonths(db.getMonth())[db.getDay()].evening,
                    db.getMonths(db.getMonth())[db.getDay()].snacks);
        }
        if(ids[0].equals("afternoon")) {
            db.getMonths(db.getMonth())[db.getDay()] = new Day(db.getDay(),2021,
                    db.getMonths(db.getMonth())[db.getDay()].morning,
                    newMenu,
                    db.getMonths(db.getMonth())[db.getDay()].evening,
                    db.getMonths(db.getMonth())[db.getDay()].snacks);
        }
        if(ids[0].equals("evening")){
            db.getMonths(db.getMonth())[db.getDay()] = new Day(db.getDay(),2021,
                    db.getMonths(db.getMonth())[db.getDay()].morning,
                    db.getMonths(db.getMonth())[db.getDay()].afternoon,
                    newMenu,
                    db.getMonths(db.getMonth())[db.getDay()].snacks);
        }
        if(ids[0].equals("snacks")){
            db.getMonths(db.getMonth())[db.getDay()] = new Day(db.getDay(),2021,
                    db.getMonths(db.getMonth())[db.getDay()].morning,
                    db.getMonths(db.getMonth())[db.getDay()].afternoon,
                    db.getMonths(db.getMonth())[db.getDay()].evening,
                    newMenu);
        }


       Day data = db.getMonths(db.getMonth())[db.getDay()];

       Menu menu = null;

       if(ids[0].equals("morning"))   menu = data.getMorning();
       if(ids[0].equals("afternoon")) menu = data.getAfternoon();
       if(ids[0].equals("evening"))   menu = data.getEvening();
       if(ids[0].equals("snacks"))    menu = data.getSnacks();

       totalCalories.setText("Total calories: " + menu.getKcal() + " kcal");
  }

    @FXML
    public void onHandleAdd(ActionEvent actionEvent) {

        if (menu.getText() == "" || calories.getText() == "") {
            final Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Please Input data!");
            alert.showAndWait();
        }
        else {
            addMenu(menu.getText(), calories.getText());
        }

        updateMenu();

    }

    public void addMenu(String title, String kcal) {

        textField.setHgap(10);

        TextField menu2 = new TextField();
        TextField calories2 = new TextField();

        menu2.setText(title);
        calories2.setText(kcal);

        // set id for each textField
        menu2.setId("menu" + i);
        calories2.setId("calories" + i);

        textField.add(menu2, 0, i);
        textField.add(calories2, 10, i);

        i++;

    }
}