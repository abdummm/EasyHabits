package com.easyhabitsapp.android;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class RestartAlarmsService extends IntentService {

    public RestartAlarmsService() {
        super("RestartAlarmsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = getSharedPreferences("alarms_saved", MODE_PRIVATE);
        String alarms = sharedPreferences.getString("alarms","");
        if(!alarms.isEmpty()){
            String[] big_split = alarms.split("big_split");
            for(int i = 0;i<big_split.length;i++){
                String[] small_split = big_split[i].split("small_split");
                create_the_alarm(small_split[0],small_split[1],small_split[2],small_split[3],small_split[4],Integer.parseInt(small_split[5]),Integer.parseInt(small_split[6]),Long.parseLong(small_split[7]),Long.parseLong(small_split[8]),Long.parseLong(small_split[9]));
            }
        }
    }
    private void create_the_alarm(String habit_name,String type_of_notification,String good_or_bad_habit,String type_of_habit,String icon_of_habit,int value_of_position,int id,long time,long start_time,long set_time){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, Send_notifcation_at_set_time.class);
        intent.putExtra("habit_name",habit_name);
        intent.putExtra("type_of_notification",type_of_notification);
        intent.putExtra("good_or_bad_habit",good_or_bad_habit);
        intent.putExtra("type_of_habit",type_of_habit);
        intent.putExtra("icon_of_habit",icon_of_habit);
        intent.putExtra("value_for_position",value_of_position);
        intent.putExtra("id",id);
        intent.putExtra("start_time",start_time);
        intent.putExtra("set_time",set_time);
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                time,
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}