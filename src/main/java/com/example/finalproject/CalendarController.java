package com.example.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

public class CalendarController {

    @FXML
    private
    Label title;

    private
    int month = 0;

    private
    Data db = Data.getInstance();

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
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        int year = c.get(Calendar.YEAR);
        if (month % 12 == 0 ) {year -= 1;}
        title.setText( monthToStrng(month) + "  " + year);
        resetDays();
        printCalendar(month);
    }

    @FXML
    protected void onHandleNext() {
        month++;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH,1);
        int year = cal.get(Calendar.YEAR);
        if (month % 12 == 0 ) {year -= 1;}
        title.setText( monthToStrng(month) + "  " + year);
        resetDays();
        printCalendar(month);
    }

    @FXML
    protected void onHandleUpdate() {
        resetDays();
        printCalendar(month);
    }


    @FXML
    public void onHandleFood(ActionEvent actionEvent) throws IOException {

        // get the id
        Button btnClicked = (Button) actionEvent.getSource();
        // get id of parent
        String id = btnClicked.getId();
        // get id of parent
        String parentId = btnClicked.getParent().getId();
        int padding = getPaddingWeekDay(month);

        // parentId to int
        int parentIdInt = Integer.parseInt(parentId);
        int day =  parentIdInt + padding;

        db.setDay(day);
        db.setId(id);
        db.setMonth(month);

        Parent parent = FXMLLoader.load(getClass().getResource("registration-view.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);

        stage.setTitle("What did you eat? " + id + "  "  + day);

        stage.setScene(new Scene(parent));
        stage.show();
    }

    public boolean isValidNumberMonth(int month) {
        return month >=1 && month <=12;
    }

    public String monthToStrng(int month) {
        if(isValidNumberMonth(month) == false) ;
         {
            if (month >= 12) {
                month = month % 12;
               }
            if (month == 0) {
                    month = 12;
                }
            if (month < 0) {
                    month = (month % 12) + 12;
                }

        }
        // tranform month to string
        String monthStr = "";
        switch (month){
            case 1:
                monthStr = "January";
                break;
            case 2:
                monthStr = "February";
                break;
            case 3:
                monthStr = "March";
                break;
            case 4:
                monthStr = "April";
                break;
            case 5:
                monthStr = "May";
                break;
            case 6:
                monthStr = "June";
                break;
            case 7:
                monthStr = "July";
                break;
            case 8:
                monthStr = "August";
                break;
            case 9:
                monthStr = "September";
                break;
            case 10:
                monthStr = "October";
                break;
            case 11:
                monthStr = "November";
                break;
            case 12:
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

        printCalendar(this.month);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        highlightDay(dayOfMonth);

        // set text title
        title.setText(monthToStrng(this.month) + " " + year);

        //hiddenGridPane(0);
        //resetDay(13);
        //resetDays();
    }

    public void resetDays() {
        // get all GridPane children from root
        for(int i = 0; i < 42; i++) {
            resetDay(i);
            showGridPane(i);
        }

    }

    public void resetDay(int pos) {
        // get element by ID from root
        Parent root = title.getParent();

        // get element by ID from root
        Button btnM = (Button) root.lookup("#morning_" + pos);
        Button btnA = (Button) root.lookup("#afternoon_" + pos);
        Button btnE = (Button) root.lookup("#evening_" + pos);
        Button btnS = (Button) root.lookup("#snacks_" + pos);

        // change text button
        btnM.setText("+");
        btnA.setText("+");
        btnE.setText("+");
        btnS.setText("+");

        Label labelK = (Label) root.lookup("#kcal_" + pos);
        Label labelD = (Label) root.lookup("#day_" + pos);

        labelK.setText("0 Kcal");
        labelD.setText("0");

        labelD.setStyle("-fx-text-fill: #393e46;");

        GridPane grid = (GridPane) root.lookup("#" + pos);
        // remove style background color
        grid.setStyle("-fx-background-color: #eeeeee;");
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

    public int getDaysInMonth(int month) {
        Calendar c = Calendar.getInstance();   // this takes current date
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, 1);

        int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);

        return daysInMonth;
    }

    private void printCalendar(int month) {
        // get number of days in month
        int daysInMonth = getDaysInMonth(month);
        int padding = getPaddingWeekDay(month);

        // get element by ID from root
        Parent root = title.getParent();

        // add the DAY number
        for (int i = 1; i <= daysInMonth; i++) {
            int pos = i + padding;
            Label labelD = (Label) root.lookup("#day_" + pos);
            labelD.setText(i + "");
        };

        // hidden the gridPane without data
        for (int i = 0; i < 42; i++) {
            Label labelD = (Label) root.lookup("#day_" + i);
           // get the text and compare if is equal to 0
            if(labelD.getText().equals("0")) {
                hiddenGridPane(i);
            }
        };
        printDays(month);
    }

    public void printDays(int month) {
        Day[] days = getData(month);
        // validate if is null days
        if (days == null) {
            return;
        }

        // iterate foreach data
        for (Day day : days) {
            printDay(day);
        }
    }

    public void highlightDay(int pos) {
        // get element by ID from root
        Parent root = title.getParent();

        Label labelD = (Label) root.lookup("#day_" + pos);
        labelD.setStyle("-fx-text-fill: -fx-color1;");

        GridPane grid = (GridPane) root.lookup("#" + pos);
        // change background color
        grid.setStyle("-fx-background-color: white;");
    }

    public void printDay(Day day) {
        int paddingWeekDay = getPaddingWeekDay(month);
        int dayOfMonth = day.getDay() + paddingWeekDay;

        // get parent node
        Parent parent = title.getParent();

        // get element by ID from root
        Button btnM = (Button) parent.lookup("#morning_" + dayOfMonth);
        Button btnA = (Button) parent.lookup("#afternoon_" + dayOfMonth);
        Button btnE = (Button) parent.lookup("#evening_" + dayOfMonth);
        Button btnS = (Button) parent.lookup("#snacks_" + dayOfMonth);

        // change text button
        btnM.setText(day.morning.getIcon());
        btnA.setText(day.afternoon.getIcon());
        btnE.setText(day.evening.getIcon());
        btnS.setText(day.snacks.getIcon());

        Label labelK = (Label) parent.lookup("#kcal_" + dayOfMonth);
        Label labelD = (Label) parent.lookup("#day_" + dayOfMonth);

        labelK.setText(day.getTotalKcal() + " Kcal");
        labelD.setText("" + (dayOfMonth - paddingWeekDay));
    }

    public void showGridPane(int pos) {
        // get element by ID from root
        Parent root = title.getParent();

        // get GridPane by id
        GridPane gridPane = (GridPane) root.lookup("#" + pos);

        // remove class for GridPane
        gridPane.getStyleClass().remove("hidden");
    }

    public void hiddenGridPane(int pos) {
        // get element by ID from root
        Parent root = title.getParent();

        // get GridPane by id
        GridPane gridPane = (GridPane) root.lookup("#" + pos);

        // set class for GridPane
        gridPane.getStyleClass().add("hidden");
    }

    public Day[] getData(int month) {
        HashMap<Integer, Day[]> m = createFakeData();
        return m.get(month);
    }

    public HashMap<Integer, Day[]> createFakeData() {

        // create hashmap
        //HashMap<Integer, Day[]> months = new HashMap<>();

        Food[] f = {
            new Food("Fries", 200),
            new Food("Ice cream", 100),
            new Food("Big burger", 300),
            new Food("Coca cola", 50),
            new Food("Coffee", 200),
        };

        Food[] f2 = {

        };

        Menu m = new Menu(f, "\uD83C\uDF54");
        Menu empty = new Menu(f2, "+");

        Day[] days = {
                new Day(1,2021, m, m, m, m),
                new Day(2,2021, m, m, m, m),
                new Day(3,2021, empty, empty, empty, empty),
                new Day(4,2021, m, m, m, m),
                new Day(5,2021, empty, empty, empty, empty),
                new Day(6,2021, empty, empty, empty, empty),
                new Day(7,2021, empty, empty, empty, empty),
                new Day(8,2021, empty, empty, empty, empty),
                new Day(9,2021, empty, empty, empty, empty),
                new Day(10,2021, empty, empty, empty, empty),
                new Day(11,2021, empty, empty, empty, empty),
                new Day(12,2021, empty, empty, empty, empty),
                new Day(13,2021, empty, empty, empty, empty),
                new Day(14,2021, empty, empty, empty, empty),
                new Day(15,2021, empty, empty, empty, empty),
                new Day(16,2021, empty, empty, empty, empty),
                new Day(17,2021, empty, empty, empty, empty),
                new Day(18,2021, empty, empty, empty, empty),
                new Day(19,2021, empty, empty, empty, empty),
                new Day(20,2021, empty, empty, empty, empty),
                new Day(21,2021, empty, empty, empty, empty),
                new Day(22,2021, empty, empty, empty, empty),
                new Day(23,2021, empty, empty, empty, empty),
                new Day(24,2021, empty, empty, empty, empty),
                new Day(25,2021, empty, empty, empty, empty),
                new Day(26,2021, empty, empty, empty, empty),
                new Day(27,2021, empty, empty, empty, empty),
                new Day(28,2021, empty, empty, empty, empty),
                new Day(29,2021, empty, empty, empty, empty),
                new Day(30,2021, empty, empty, empty, empty)
        };

        System.out.println(days[0].getTotalKcal());
        System.out.println(days[0].morning.icon);

        // add key and value to hashmap
        if(!db.isMonthSaved(10)) db.saveMonth(10, days); // oct
        if(!db.isMonthSaved(11)) db.saveMonth(11, days); // nov
        if(!db.isMonthSaved(12)) db.saveMonth(12, days); // dic

        return db.getMonths();

    }
}