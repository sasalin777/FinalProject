package com.example.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.Calendar;
import java.util.HashMap;

public class CalendarController {

    @FXML
    private Text title;
    private int month = 0;

    @FXML
    protected void onHelloButtonClick() {
        title.setText("Calendar!");
    }

    @FXML
    protected void onHandleBefore() {
        month--;
        Calendar c = Calendar.getInstance();   // this takes current date
        // change the month
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, 1);
        System.out.println(c.getTime());       // this returns java.util.Date

        // get day of week
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        title.setText( monthToStrng(month) + "  " + dayOfWeek);

        printCalendar(month);

    }

    @FXML
    protected void onHandleNext() {
        month++;
        title.setText("" + monthToStrng(month));
        printCalendar(month);
    }

    @FXML
    public void onHandleFood(ActionEvent actionEvent) {
        Button btnClicked = (Button) actionEvent.getSource();
        title.setText(btnClicked.getId());
        // get the parent node ID
        Parent parent = btnClicked.getParent();
        // get the parent node ID
        String parentId = parent.getId();
        title.setText(parentId);
        btnClicked.setText("\uD83C\uDF72");

        /*
        // search a element by id
        Button btnM = (Button) parent.lookup("#morning_" + parentId);
        Button btnA = (Button) parent.lookup("#afternoon_" + parentId);
        Button btnE = (Button) parent.lookup("#evening_" + parentId);
        Button btnS = (Button) parent.lookup("#snacks_" + parentId);
        // change text button
        btnM.setText("\uD83C\uDF2E");
        btnA.setText("\uD83C\uDF72");
        btnE.setText("\uD83C\uDF55");
        btnS.setText("\uD83C\uDF72");*/
        // https://www.i2symbol.com/symbols/food
        // Food Symbols 🍞 🍖 🍗 🍔 🍟 🍕 🍳 🍲 🍱 🍘 - i2Symbol

    }


    public String monthToStrng(int month) {
        month--;
        // tranform month to string
        String monthStr = "";
        switch (month){
            case 0:
                monthStr = "January";
                break;
            case 1:
                monthStr = "February";
                break;
            case 2:
                monthStr = "March";
                break;
            case 3:
                monthStr = "April";
                break;
            case 4:
                monthStr = "May";
                break;
            case 5:
                monthStr = "June";
                break;
            case 6:
                monthStr = "July";
                break;
            case 7:
                monthStr = "August";
                break;
            case 8:
                monthStr = "September";
                break;
            case 9:
                monthStr = "October";
                break;
            case 10:
                monthStr = "November";
                break;
            case 11:
                monthStr = "December";
                break;
        }

        return  monthStr;
    }
    @FXML
    public void initialize() {

        // get current month
        Calendar cal = Calendar.getInstance();
        this.month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);



        //printCalendar(this.month);
        printCalendar(11);

        // set text title
        title.setText(monthToStrng(this.month) + " " + year);
    
        // get element by ID from root
        Parent root = title.getParent();

        // get element by ID from root
        // get the day of mouth
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        Button btnM = (Button) root.lookup("#morning_" + dayOfMonth);
        Button btnA = (Button) root.lookup("#afternoon_"+ dayOfMonth);
        Button btnE = (Button) root.lookup("#evening_"+ dayOfMonth);
        Button btnS = (Button) root.lookup("#snacks_"+ dayOfMonth);

        // change text button
        btnM.setText("\uD83C\uDF2E");
        btnA.setText("\uD83C\uDF72");
        btnE.setText("\uD83C\uDF55");
        btnS.setText("\uD83C\uDF72");

        Label labelK = (Label) root.lookup("#kcal_"+ dayOfMonth);
        Label labelD = (Label) root.lookup("#day_"+ dayOfMonth);

        labelK.setText("200 Kcal");
        labelD.setText("" + dayOfMonth);
        labelD.setStyle("-fx-text-fill: #98DDCA;");

        GridPane grid = (GridPane) root.lookup("#" + dayOfMonth);
        // change background color
        grid.setStyle("-fx-background-color: #DDDDDD;");

        // hiden gridPanes
        // get GridPane by id
        GridPane gridPane = (GridPane) root.lookup("#0");
        // set class for GridPane
        gridPane.getStyleClass().add("hidden");

    }

    public int getPaddingWeekDay(int month) {
        Calendar c = Calendar.getInstance();   // this takes current date
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        System.out.println(c.getTime());       // this returns java.util.Date

        // get day of week
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        return dayOfWeek - 2;
    }

    private void printCalendar(int month) {
        Day[] days = getData(month);
        // validate if is null days
        int paddingWeekDay = getPaddingWeekDay(month);
        if (days == null) {
            return;
        }
        System.out.println("paddingWeekDay " + paddingWeekDay);
        // iterate foreach data
        for (Day day : days) {
            // get parent node
            Parent parent = title.getParent();
            // get the day of mouth
            int dayOfMonth = day.getDay() + paddingWeekDay;
            System.out.println("#morning_" + dayOfMonth);
            // get element by ID from root
            Button btnM = (Button) parent.lookup("#morning_" + dayOfMonth);
            Button btnA = (Button) parent.lookup("#afternoon_"+ dayOfMonth);
            Button btnE = (Button) parent.lookup("#evening_"+ dayOfMonth);
            Button btnS = (Button) parent.lookup("#snacks_"+ dayOfMonth);

            // change text button
            btnM.setText(day.morning.getIcon());
            btnA.setText(day.afternoon.getIcon());
            btnE.setText(day.evening.getIcon());
            btnS.setText(day.snacks.getIcon());

            Label labelK = (Label) parent.lookup("#kcal_"+ dayOfMonth);
            Label labelD = (Label) parent.lookup("#day_"+ dayOfMonth);

            labelK.setText(day.getTotalKcal() + " Kcal");
            labelD.setText("" + (dayOfMonth - paddingWeekDay));
        }

    }

    public Day[] getData(int month) {
        HashMap<Integer, Day[]> m = createFakeData();
        return m.get(month);
    }

    public HashMap<Integer, Day[]> createFakeData() {
        // create hashmap
        HashMap<Integer, Day[]> months = new HashMap<>();

        Food[] f = {
                new Food("Fries", 200),
                new Food("Ice cream", 100),
                new Food("Big burger", 300),
                new Food("Coca cola", 50),
                new Food("Coffee", 100),
        };
        Menu m = new Menu(f, "\uD83C\uDF54");

        Day[] days = {
                new Day(1,2021, m, m, m, m),
                new Day(2,2021, m, m, m, m),
                new Day(3,2021, m, m, m, m),
                new Day(4,2021, m, m, m, m),
        };

        System.out.println(days[0].getTotalKcal());
        System.out.println(days[0].morning.icon);

        // add key and value
        // november
        months.put(10, days); // oct
        months.put(11, days); // nov
        months.put(12, days); // dec

        //System.out.println(months.get(11)[0].toString());

        return months;

    }
}