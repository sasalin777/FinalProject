package com.example.finalproject;

public class Menu {
    public String icon = "\uD83C\uDF55";
    public Food[] food;

    public Menu(Food[] food) {
        this.food = food;
    }
    
    public Menu(Food[] food, String icon) {
        this.food = food;
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Food[] getFood() {
        return food;
    }

    public void setFood(Food[] food) {
        this.food = food;
    }

    public String toString() {
        String menu = "";
        for (int i = 0; i < food.length; i++) {
            menu += food[i].toString() + "\n";
        }
        return menu;
    }
}
