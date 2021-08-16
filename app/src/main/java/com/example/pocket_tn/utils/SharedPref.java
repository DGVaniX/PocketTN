package com.example.pocket_tn.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

@SuppressLint("ApplySharedPref")
public class SharedPref {
    SharedPreferences mysharedpref;
    SharedPreferences.Editor editor;

    public SharedPref(Context context) {
        mysharedpref = context.getSharedPreferences("theme", Context.MODE_PRIVATE);
        editor = mysharedpref.edit();
        editor.apply();

    }


    public void setNightModeState(Boolean state) {
        SharedPreferences.Editor editor = mysharedpref.edit();
        editor.putBoolean("NightMode", state);
        editor.commit();
    }

    public Boolean loadNightMode() {
        return mysharedpref.getBoolean("NightMode", false);

    }

    public void setTheme(String name) {
        editor = mysharedpref.edit();
        editor.putString("themeName", name);
        editor.commit();
    }

    public String loadTheme() {
        return mysharedpref.getString("themeName", null);

    }

    public void clearAll() {
        editor = mysharedpref.edit();
        editor.clear();
        editor.commit();
    }

    public void setFirstTime(boolean flag) {
        editor.putBoolean("firstTime", flag);
        editor.commit();
    }

    public boolean isFirstTime() {
        return mysharedpref.getBoolean("firstTime", true);
    }


    public void setpremium(boolean flag) {
        editor.putBoolean("premium", flag);
        editor.commit();
    }

    public boolean isPremium() {
        return mysharedpref.getBoolean("premium", false);
    }

    public void setPackage(String flag) {
        editor.putString("selectedPackage", flag);
        editor.commit();
    }

    public String getPackage() {

        return mysharedpref.getString("selectedPackage", "");
    }


    public void setView(String flag) {
        editor.putString("selectedView", flag);
        editor.commit();
    }

    public String getView() {

        return mysharedpref.getString("selectedView", "");
    }


    public void setCaloriesGoal(int flag) {
        editor.putInt("caloriesgoal", flag);
        editor.commit();
    }

    public int getCaloriesGoal() {
        return mysharedpref.getInt("caloriesgoal", 2000);
    }


    public void setConsumedCalories(int flag) {
        editor.putInt("caloriesconsumed", flag);
        editor.commit();
    }

    public int getConsumedCalories() {
        return mysharedpref.getInt("caloriesconsumed", 0);
    }


}