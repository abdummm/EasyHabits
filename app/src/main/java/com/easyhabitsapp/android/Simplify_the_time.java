package com.easyhabitsapp.android;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Simplify_the_time {
    public static String getting_the_time(String current_time){
        String[] split_for_current_time = current_time.split("_");
        return "Date: ".concat(return_month(split_for_current_time[2])).concat(" ").concat(split_for_current_time[1]).concat(",").concat(" ").concat(return_year(split_for_current_time[3])).concat(return_time(split_for_current_time[0]));

    }
    public static String return_time(String time){
        String[] min_and_hour = time.split(":");
        int hour = Integer.parseInt(min_and_hour[1]);
        int minutes = Integer.parseInt(min_and_hour[0]);
        String am_or_pm;
        String real_hour;
        String real_minute;
        if (hour >= 12){
            if(hour!=12) {
                real_hour = String.valueOf(hour - 12);
                am_or_pm = "PM";
            } else {
                real_hour = "12";
                am_or_pm = "PM";
            }
        } else {
            if (hour != 0){
                real_hour = String.valueOf(hour);
                am_or_pm = "AM";
            } else {
                real_hour = "12";
                am_or_pm = "AM";
            }
        }
        if (minutes >=10){
            real_minute = String.valueOf(minutes);
        } else {
            real_minute = "0".concat(String.valueOf(minutes));
        }
        return real_hour.concat(":").concat(real_minute).concat(" ").concat(am_or_pm);
    }
    public static String return_month(String month_string){
        int month = Integer.parseInt(month_string);
        if(month==0){
            return "January";
        }else if (month == 1){
            return "February";
        }else if (month == 2){
            return "March";
        }else if (month == 3){
            return "April";
        }else if (month == 4){
            return "May";
        }else if (month == 5){
            return "June";
        }else if (month == 6){
            return "July";
        }else if (month == 7){
            return "August";
        }else if (month == 8){
            return "September";
        }else if (month == 9){
            return "October";
        }else if (month == 10){
            return "November";
        }else {
            return "December";
        }
    }
    private static String return_year(String year_string){
        int year_int = Integer.parseInt(year_string);
        int current_year = Calendar.getInstance().get(Calendar.YEAR);
        if(current_year!=year_int){
            return String.valueOf(year_int).concat(", ");
        } else {
            return "";
        }
    }
    public static void save_the_milli(String time, Context context){
        String[] split_time = time.split("_");
        String[] split_to_hour_and_minute = split_time[0].split(":");
        String final_time = split_time[3].concat("-").concat(return_month_number(split_time[2])).concat("-").concat(return_day_number(split_time[1])).concat(" ").concat(return_the_hour(split_to_hour_and_minute[1])).concat(":").concat(return_minute_number(split_to_hour_and_minute[0]));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date = formatter.parse(final_time);
            SharedPreferences shared = context.getSharedPreferences("temp_time_to_save",context.MODE_PRIVATE);
            SharedPreferences.Editor edit = shared.edit();
            edit.putLong("temp_time_to_save",date.getTime());
            edit.apply();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private static String return_the_hour(String hour){
        int hour_int = Integer.parseInt(hour);
        if(hour_int >=10){
            return String.valueOf(hour_int);
        } else {
            return "0".concat(String.valueOf(hour_int));
        }
    }
    private static String return_month_number(String month){
        int month_int = Integer.parseInt(month);
        if((month_int+1) >=10){
            return String.valueOf(month_int+1);
        } else {
            return "0".concat(String.valueOf(month_int+1));
        }
    }
    private static String return_day_number(String day){
        int day_int = Integer.parseInt(day);
        if(day_int>=10){
            return day;
        } else {
            return "0".concat(day);
        }
    }
    private static String return_minute_number(String minute){
        int minute_int = Integer.parseInt(minute);
        if(minute_int>=10){
            return minute;
        } else {
            return "0".concat(minute);
        }
    }
    public static long return_time_in_midnight(long millis){
        Calendar calendar_old = Calendar.getInstance();
        calendar_old.setTimeInMillis(millis);
        int day = calendar_old.get(Calendar.DAY_OF_MONTH);
        int month = calendar_old.get(Calendar.MONTH);
        int year = calendar_old.get(Calendar.YEAR);
        /*if(calendar_old.get(Calendar.HOUR_OF_DAY) == 0 && calendar_old.get(Calendar.MINUTE) == 0 && calendar_old.get(Calendar.SECOND) == 0 && calendar_old.get(Calendar.MILLISECOND) == 0){
            return millis;
        }*/
        Calendar calendar_new = Calendar.getInstance();
        calendar_new.set(Calendar.YEAR, year);
        calendar_new.set(Calendar.MONTH, month);
        calendar_new.set(Calendar.DAY_OF_MONTH, day);
        calendar_new.set(Calendar.HOUR_OF_DAY,0);
        calendar_new.set(Calendar.MINUTE,0);
        calendar_new.set(Calendar.SECOND,0);
        calendar_new.set(Calendar.MILLISECOND,0);
        return calendar_new.getTimeInMillis();
    }
}
