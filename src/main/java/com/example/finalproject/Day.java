package com.example.finalproject;

public class Day {

  public String day;
  public String month;
  public String year;

  public Menu morning;
  public Menu afternoon;
  public Menu evening;
  public Menu snacks;

  public int totalKcal;

  public Day(String day, String month, String year, Menu morning, Menu afternoon, Menu evening, Menu snacks) {
    this.day = day;
    this.month = month;
    this.year = year;
    this.morning = morning;
    this.afternoon = afternoon;
    this.evening = evening;
    this.snacks = snacks;
    this.totalKcal = 0;
  }
}
