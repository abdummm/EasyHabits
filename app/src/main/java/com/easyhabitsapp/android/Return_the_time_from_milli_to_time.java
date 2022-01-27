package com.easyhabitsapp.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Return_the_time_from_milli_to_time {
    private static String open_the_time(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("main_streak", context.MODE_PRIVATE);
        return sharedPreferences.getString("final_information", "");
    }

    public static String read_the_files(Context context) {
        String total_string;
        String read_the_file = remove_the_repeats(context);
        String[] date_split = read_the_file.split("split");
        total_string = "start".concat("_split_").concat(date_split[0]).concat("_split_here_");
        Log.w("hey_there",total_string);
        for (int i = 1; i < date_split.length; i++) {
            if(!total_string.contains(date_split[i])) {
                total_string = total_string.concat("end".concat("_split_").concat(date_split[i].concat("_split_here_")));
                total_string = total_string.concat("start".concat("_split_").concat(get_the_next_days(date_split[i])).concat("_split_here_"));
            } else {
                String old_string = "start".concat("_split_").concat(date_split[i]).concat("_split_here_");
                total_string = total_string.replace(old_string,"");
                total_string = total_string.concat("beg_last".concat("_split_").concat(date_split[i].concat("_split_here_")));
                total_string = total_string.concat("start".concat("_split_").concat(get_the_next_days(date_split[i])).concat("_split_here_"));
            }
        }
        return total_string;
    }

    private static String return_date(long milli) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy", Locale.getDefault());
        return formatter.format(new Date(milli));
    }

    private static String remove_the_repeats(Context context) {
        String original = open_the_time(context);
        String[] split_into_streaks = original.split("_split_max_here_");
        String[] temp = split_into_streaks[0].split("_split_here_");
        String the_saver = return_date(Long.parseLong(temp[temp.length - 1])).concat("split");
        for (int i = 1; i < split_into_streaks.length; i++) {
            String[] split_inside = split_into_streaks[i].split("_split_here_");
            String the_date = return_date(Long.parseLong(split_inside[5]));
            if (i != 1) {
                if (!the_saver.contains(the_date)) {
                    the_saver = the_saver.concat(the_date).concat("split");
                }
            } else {
                the_saver = the_saver.concat(the_date).concat("split");
            }
        }
        return the_saver;
    }

    private static String get_the_next_days(String start_date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy", Locale.getDefault());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(start_date));
            calendar.add(Calendar.DATE, 1);  // number of days to add
            return simpleDateFormat.format(calendar.getTime());  // dt is now the new date
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return start_date;
    }
}