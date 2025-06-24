package com.easyhabitsapp.android;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;

public class Habits_freq_converter {

    @TypeConverter
    public ArrayList<String> string_to_habit_freq(String value) {
        ArrayList<String> return_me_days = new ArrayList<>();
        if(!value.isEmpty()){
            String[] days = value.split("split");
            return_me_days.addAll(Arrays.asList(days));
        }
        return return_me_days;
    }

    @TypeConverter
    public String habit_freq_to_string(ArrayList<String> habit_freq) {
        String days = "";
        if(habit_freq !=null) {
            for (int i = 0; i < habit_freq.size(); i++) {
                days = days.concat(habit_freq.get(i)).concat("split");
            }
            return days;
        } else {
            return days;
        }
    }
}
