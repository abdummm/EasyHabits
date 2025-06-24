package com.easyhabitsapp.android;

import androidx.room.TypeConverter;

import java.util.ArrayList;

public class Notification_converter {
    @TypeConverter
    public ArrayList<Notification_object_add_new_habit> string_to_notification(String value) {
        ArrayList<Notification_object_add_new_habit> notification_object_add_new_habits = new ArrayList<>();
        if(value.isEmpty()){
            return notification_object_add_new_habits;
        } else {
            String[] big_split = value.split("big_split");
            for (int i = 0; i < big_split.length; i++) {
                String[] small_split = big_split[i].split("small_split");
                String[] week_split = small_split[0].split("weak_split");
                ArrayList<String> days = new ArrayList<>();
                for (int j = 0; j < week_split.length; j++) {
                    days.add(week_split[j]);
                }
                Notification_object_add_new_habit notification_object_add_new_habit = new Notification_object_add_new_habit(days, Long.parseLong(small_split[1]));
                notification_object_add_new_habits.add(notification_object_add_new_habit);
            }
            return notification_object_add_new_habits;
        }
    }

    @TypeConverter
    public String notification_to_string(ArrayList<Notification_object_add_new_habit> notification_object_add_new_habits) {
        String value = "";
        if(notification_object_add_new_habits!=null) {
            for (int i = 0; i < notification_object_add_new_habits.size(); i++) {
                String days = "";
                for (int j = 0; j < notification_object_add_new_habits.get(i).getDays().size(); j++) {
                    days = days.concat(notification_object_add_new_habits.get(i).getDays().get(j)).concat("weak_split");
                }
                value = value.concat(days).concat("small_split").concat(String.valueOf(notification_object_add_new_habits.get(i).getTime())).concat("big_split");
            }
        }
        return value;
    }
}
