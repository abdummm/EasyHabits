package com.easyhabitsapp.android;

import java.util.ArrayList;

public class Example_item_reminder_add_new_habit {
    private long time;
    private ArrayList<String> days;
    private int color;

    public Example_item_reminder_add_new_habit(long time, ArrayList<String> days,int color) {
        this.time = time;
        this.days = days;
        this.color = color;
    }
    public long getTime() {
        return time;
    }
    public ArrayList<String> getDays() {
        return days;
    }
    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }
}
