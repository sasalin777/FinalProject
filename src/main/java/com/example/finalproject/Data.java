package com.example.finalproject;

import java.util.HashMap;

public class Data {

    public HashMap<Integer, Day[]> getMonths(){
        return months;
    }

    public void saveMonth(int month, Day[] days){
        this.months.put(month, days);
    }

    public boolean isMonthSaved(int month){
        return this.months.containsKey(month);
    }

    private static Data single_instance = null;
    private HashMap<Integer, Day[]> months = new HashMap<>();

    private int day = 0;
    private int month = 0;
    private String id;

    public void setDay(int day){
        this.day = day;
    }

    public Day[] getMonths(int month) {
        return this.months.get(month);
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public int getDay(){
        return day;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    private Data() {
    }

    public static Data getInstance() {
        if (single_instance == null)
            single_instance = new Data();
        return single_instance;
    }

}