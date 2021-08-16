package com.example.pocket_tn.models;

public class ModelMeals {
    private int icon;
    private String type;
    private String name;
    private int calories;
    private String time;
    private String ingridients;

    public ModelMeals(int icon, String type, String name, int calories, String time, String ingridients) {
        this.icon = icon;
        this.type = type;
        this.name = name;
        this.calories = calories;
        this.time = time;
        this.ingridients = ingridients;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIngridients() {
        return ingridients;
    }

    public void setIngridients(String ingridients) {
        this.ingridients = ingridients;
    }
}
