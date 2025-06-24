package com.easyhabitsapp.android;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class Item_for_home_habits {
    private String m_icon;
    private String m_title_color;
    private String m_habit_name;
    private Amount_object amount;
    private int id;
    private ArrayList<String> last_5_days;
    private int first_item_margin;
    private String type; // can be build_up or quit

    public Item_for_home_habits(String habit_name, String icon, String title_color, Amount_object amount, int id, ArrayList<String> last_5_days, int first_item_margin,String type) {
        m_icon = icon;
        m_habit_name = habit_name;
        m_title_color = title_color;
        this.amount = amount;
        this.id = id;
        this.last_5_days = last_5_days;
        this.first_item_margin = first_item_margin;
        this.type = type;
    }
    public String getM_icon(){
        return m_icon;
    }
    public String getM_habit_name(){
        return m_habit_name;
    }
    public String getM_title_color(){
        return m_title_color;
    }
    public Amount_object getAmount() {return amount;}
    public int getId() {return id;}
    public ArrayList<String> getLast_5_days() {return last_5_days;}
    public int getFirst_item_margin() {return first_item_margin;}
    public String getType() {return type;}



}
