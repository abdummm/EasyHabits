package com.easyhabitsapp.android;

import java.util.ArrayList;

public class Notification_object_add_new_habit {
    private ArrayList<String> days;
    private long time;

    public Notification_object_add_new_habit(ArrayList<String> days, long time) {
        this.days = days;
        this.time = time;
    }

    public ArrayList<String> getDays() {
        return days;
    }

    public void setDays(ArrayList<String> days) {
        this.days = days;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
