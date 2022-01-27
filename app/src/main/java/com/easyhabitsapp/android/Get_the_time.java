package com.easyhabitsapp.android;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.TimeUnit;

public class Get_the_time {
    public static String open_the_file(Context context){
        SharedPreferences sharedPreferences= context.getSharedPreferences("future_time_in_milli_seconds", context.MODE_PRIVATE);
        long future_time = sharedPreferences.getLong("time", 0);
        long final_time_in_milli = future_time - System.currentTimeMillis();
        if (final_time_in_milli > 0) {
            long hours = TimeUnit.MILLISECONDS.toHours(final_time_in_milli);
            int minutes = (int) (Math.ceil(final_time_in_milli / 60000.0) - (hours * 60));
            if (minutes >= 10) {
                return "0".concat(String.valueOf(hours).concat(":").concat(String.valueOf(minutes)));
            } else {
                return "0".concat(String.valueOf(hours).concat(":").concat("0".concat(String.valueOf(minutes))));
            }
        } else {
            return "00:00";
        }
    }
    public static int return_the_seconds(Context context){
        SharedPreferences sharedPreferences= context.getSharedPreferences("future_time_in_milli_seconds", context.MODE_PRIVATE);
        long future_time = sharedPreferences.getLong("time", 0);
        long final_time_in_milli = future_time - System.currentTimeMillis();
        if (final_time_in_milli > 0){
            return (int) TimeUnit.MILLISECONDS.toSeconds(final_time_in_milli);
        } else {
            return 0;
        }
    }
}
