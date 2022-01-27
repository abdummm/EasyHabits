package com.easyhabitsapp.android;

import android.graphics.drawable.Drawable;

public class Item_for_home_habits {
    private Drawable m_icon;
    private int m_streak_in_day;
    private int m_title_color;
    private String m_habit_name;

    public Item_for_home_habits(Drawable icon, int streak_in_day, String habit_name,int title_color) {
        m_icon = icon;
        m_streak_in_day = streak_in_day;
        m_habit_name = habit_name;
        m_title_color = title_color;
    }
    public Drawable getM_icon(){
        return m_icon;
    }
    public int getM_streak_in_day(){
        return m_streak_in_day;
    }
    public String getM_habit_name(){
        return m_habit_name;
    }
    public int getM_title_color(){
        return m_title_color;
    }
}
