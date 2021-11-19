package com.example.finalproject;

public class Day {

  public int day;
  public int year;

  public Menu morning;
  public Menu afternoon;
  public Menu evening;
  public Menu snacks;

  public Day(int day, int year, Menu morning, Menu afternoon, Menu evening, Menu snacks) {
    setDay(day);
    setYear(year);
    this.morning = morning;
    this.afternoon = afternoon;
    this.evening = evening;
    this.snacks = snacks;
  }

  public int getDay() {
    return this.day;
  }

  public void setDay(int day) {
    // validate day is between 1 and 31
    if (day >= 1 && day <= 31) {
      this.day = day;
    }
 }

  public boolean isValidNumberMonth(int month) {
    return month >= 1 && month <= 12;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    // validate the year is between 2020 and 2100
    if (year >= 2020 && year <= 2100) {
      this.year = year;
    }
 }

  public Menu getMorning() {
    return morning;
  }

  public void setMorning(Menu morning) {
    this.morning = morning;
  }

  public Menu getAfternoon() {
    return afternoon;
  }

  public void setAfternoon(Menu afternoon) {
    this.afternoon = afternoon;
  }

  public Menu getEvening() {
    return evening;
  }

  public void setEvening(Menu evening) {
    this.evening = evening;
  }

  public Menu getSnacks() {
    return snacks;
  }

  public void setSnacks(Menu snacks) {
    this.snacks = snacks;
  }

  public int getTotalKcal() {
    return calculateTotalKcal();
  }

  public int calculateTotalKcal() {
    return this.morning.getKcal() + this.afternoon.getKcal() + this.evening.getKcal() + this.snacks.getKcal();
  }

  @Override
  public String toString() {
    return "Day{" +
        "day=" + day +
        ", year=" + year +
        ", morning=" + morning +
        ", afternoon=" + afternoon +
        ", evening=" + evening +
        ", snacks=" + snacks +
        '}';
  }

}
