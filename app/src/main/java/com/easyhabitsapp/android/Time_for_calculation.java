package com.easyhabitsapp.android;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Time_for_calculation {
    public static int current_streak(Context context,String name_of_habit) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("main_streak", context.MODE_PRIVATE);
        String string_from_file = sharedPreferences.getString(name_of_habit, "");
        String[] splitter_to_final_value = string_from_file.split("_split_max_here_");
        if (splitter_to_final_value.length != 0) {
            String[] final_split = splitter_to_final_value[get_the_final_goal(context,name_of_habit)].split("_split_here_");
            return (int) TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis() - Long.parseLong(final_split[final_split.length - 1]));
        }
        return 0;
    }

    public static long time_left_mid_night() {
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, 1);
        calender.set(Calendar.HOUR_OF_DAY, 0);
        calender.set(Calendar.MINUTE, 0);
        calender.set(Calendar.SECOND, 0);
        calender.set(Calendar.MILLISECOND, 0);
        return (calender.getTimeInMillis() - System.currentTimeMillis());
    }

    public static String get_the_goal(Context context,String name_of_habit) {
        SharedPreferences shared = context.getSharedPreferences("main_goals", context.MODE_PRIVATE);
        String goals = shared.getString(name_of_habit, "");
        String[] split_goals = goals.split("_");
        for (int i = 0; i < split_goals.length; i++) {
            if (Integer.parseInt(split_goals[i]) > current_streak(context,name_of_habit)) {
                return split_goals[i];
            }
        }
        return "no_goals";
    }

    public static String goal_difference(Context context,String name_of_habit) {
        SharedPreferences shared = context.getSharedPreferences("main_goals", context.MODE_PRIVATE);
        String goals = shared.getString(name_of_habit, "");
        String[] split_goals = goals.split("_");
        for (int i = 0; i < split_goals.length; i++) {
            if (Integer.parseInt(split_goals[i]) > current_streak(context,name_of_habit)) {
                if (i == 0) {
                    return split_goals[i];
                } else {
                    return String.valueOf(Integer.parseInt(split_goals[i]) - Integer.parseInt(split_goals[i - 1]));
                }
            }
        }
        return "no_goals";
    }

    public static long milli_to_milli(Context context,String name_of_habit) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("main_streak", context.MODE_PRIVATE);
        String string_from_file = sharedPreferences.getString(name_of_habit, "");
        String[] splitter_first = string_from_file.split("_split_max_here_");
        String[] splitter_second = splitter_first[get_the_final_goal(context,name_of_habit)].split("_split_here_");
        long old_milli = Long.parseLong(splitter_second[splitter_second.length - 1]);
        long time_difference = System.currentTimeMillis() - old_milli;
        return 86400000 - (time_difference % 86400000);
    }

    private static int get_the_final_goal(Context context,String name_of_habit) {
        SharedPreferences shared = context.getSharedPreferences("main_streak", context.MODE_PRIVATE);
        String saved_file = shared.getString(name_of_habit, "");
        String[] first_split = saved_file.split("_split_max_here_");
        for (int i = first_split.length - 1; i > 0; i--) {
            String[] second_split = first_split[i].split("_split_here_");
            if (second_split[2].equals("yes") || second_split[2].equals("first_time")) {
                return i;
            }
        }
        return first_split.length - 1;
    }
}
