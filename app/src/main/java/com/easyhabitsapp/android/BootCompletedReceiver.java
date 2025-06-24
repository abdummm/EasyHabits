package com.easyhabitsapp.android;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class BootCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            //Intent i = new Intent(context, RestartAlarmsService.class);
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(i);
            } else {
                context.startService(i);
            }*/
            restart_alarm(context);
        }
    }

    private void restart_alarm(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("alarms_saved", MODE_PRIVATE);
        String alarms = sharedPreferences.getString("alarms","");
        if(!alarms.isEmpty()){
            String[] big_split = alarms.split("big_split");
            for(int i = 0;i<big_split.length;i++){
                String[] small_split = big_split[i].split("small_split",-1);
                if(small_split.length ==12) {
                    create_the_alarm(context,small_split[0], small_split[1], small_split[2], small_split[3], small_split[4], Integer.parseInt(small_split[5]), Integer.parseInt(small_split[6]), Long.parseLong(small_split[7]), Long.parseLong(small_split[8]), Long.parseLong(small_split[9]), Integer.parseInt(small_split[10]), small_split[11]);
                }
            }
        }
    }

    private void create_the_alarm(Context context,String habit_name,String type_of_notification,String good_or_bad_habit,String type_of_habit,String icon_of_habit,int value_of_position,int id,long time,long start_time,long set_time,int request_code,String extra_notification_information){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, Send_notifcation_at_set_time.class);
        intent.putExtra("habit_name",habit_name);
        intent.putExtra("type_of_notification",type_of_notification);
        intent.putExtra("extra_information",extra_notification_information);
        intent.putExtra("good_or_bad_habit",good_or_bad_habit);
        intent.putExtra("type_of_habit",type_of_habit);
        intent.putExtra("icon_of_habit",icon_of_habit);
        intent.putExtra("value_for_position",value_of_position);
        intent.putExtra("id",id);
        intent.putExtra("start_time",start_time);
        //intent.putExtra("set_time",set_time);
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getBroadcast(context, request_code, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) + time + TimeUnit.DAYS.toMillis(1),
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}
