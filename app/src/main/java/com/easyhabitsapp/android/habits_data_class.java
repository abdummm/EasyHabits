package com.easyhabitsapp.android;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@Entity(tableName = "habits")
public class habits_data_class implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int Id;

    @ColumnInfo(name = "bad_or_good_habit")
    private String bad_or_good_habit;

    @ColumnInfo(name = "name_of_the_habit")
    private String name_of_the_habit;

    @ColumnInfo(name = "date_of_last_relapse")
    private long last_relapse;

    @ColumnInfo(name = "goal")
    private int goal;

    @ColumnInfo(name = "color")
    private String color;

    @ColumnInfo(name = "icon")
    private String icon;

    @ColumnInfo(name = "reminder")
    private String reminder;

    @ColumnInfo(name = "days_per_week")
    private String days_per_week;

    @ColumnInfo(name = "type_of_the_habit")
    private String type_of_the_habit;

    @ColumnInfo(name = "extra_type_info")
    private int extra_type_info;

    @ColumnInfo(name = "notifications_on_or_off")
    private boolean notifications_on_or_off;

    @ColumnInfo(name = "notifications_freq")
    private String notifications_freq;

    @ColumnInfo(name = "notifications_freq_extra")
    private String notifications_freq_extra;

    @ColumnInfo(name = "notifications_time")
    private long notifications_time;

    @ColumnInfo(name = "habits_freq")
    private String habits_freq;

    @ColumnInfo(name = "habits_freq_extra")
    private String habits_freq_extra;

    @ColumnInfo(name = "relapse")
    private ArrayList<Long> relapse;

    @ColumnInfo(name = "relapse_amount_timer")
    private HashMap<Long,Integer> relapse_amount_timer;

    @ColumnInfo(name = "relapse_amount")
    private HashMap<Long,Integer> relapse_amount;

    public String getNotifications_freq_extra() {
        return notifications_freq_extra;
    }

    public void setNotifications_freq_extra(String notifications_freq_extra) {
        this.notifications_freq_extra = notifications_freq_extra;
    }

    public boolean isNotifications_on_or_off() {
        return notifications_on_or_off;
    }

    public void setNotifications_on_or_off(boolean notifications_on_or_off) {
        this.notifications_on_or_off = notifications_on_or_off;
    }

    public String getNotifications_freq() {
        return notifications_freq;
    }

    public void setNotifications_freq(String notifications_freq) {
        this.notifications_freq = notifications_freq;
    }

    public long getNotifications_time() {
        return notifications_time;
    }

    public void setNotifications_time(long notifications_time) {
        this.notifications_time = notifications_time;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getBad_or_good_habit() {
        return bad_or_good_habit;
    }

    public void setBad_or_good_habit(String bad_or_good_habit) {
        this.bad_or_good_habit = bad_or_good_habit;
    }

    public String getName_of_the_habit() {
        return name_of_the_habit;
    }

    public void setName_of_the_habit(String name_of_the_habit) {
        this.name_of_the_habit = name_of_the_habit;
    }

    public long getLast_relapse() {
        return last_relapse;
    }

    public void setLast_relapse(long last_relapse) {
        this.last_relapse = last_relapse;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getDays_per_week() {
        return days_per_week;
    }

    public void setDays_per_week(String days_per_week) {
        this.days_per_week = days_per_week;
    }

    public String getType_of_the_habit() {
        return type_of_the_habit;
    }

    public void setType_of_the_habit(String type_of_the_habit) {
        this.type_of_the_habit = type_of_the_habit;
    }

    public int getExtra_type_info() {
        return extra_type_info;
    }

    public void setExtra_type_info(int extra_type_info) {
        this.extra_type_info = extra_type_info;
    }

    public String getHabits_freq() {
        return habits_freq;
    }

    public void setHabits_freq(String habits_freq) {
        this.habits_freq = habits_freq;
    }

    public String getHabits_freq_extra() {
        return habits_freq_extra;
    }

    public void setHabits_freq_extra(String habits_freq_extra) {
        this.habits_freq_extra = habits_freq_extra;
    }

    public ArrayList<Long> getRelapse() {
        return relapse;
    }

    public void setRelapse(ArrayList<Long> relapse) {
        this.relapse = relapse;
    }

    public HashMap<Long, Integer> getRelapse_amount_timer() {
        return relapse_amount_timer;
    }

    public void setRelapse_amount_timer(HashMap<Long, Integer> relapse_amount_timer) {
        this.relapse_amount_timer = relapse_amount_timer;
    }

    public HashMap<Long, Integer> getRelapse_amount() {
        return relapse_amount;
    }

    public void setRelapse_amount(HashMap<Long, Integer> relapse_amount) {
        this.relapse_amount = relapse_amount;
    }
}
