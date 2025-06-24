package com.easyhabitsapp.android;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class RestartAlarmsService extends IntentService {

    public RestartAlarmsService() {
        super("RestartAlarmsService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        post_the_notication();
        SharedPreferences sharedPreferences = getSharedPreferences("alarms_saved", MODE_PRIVATE);
        String alarms = sharedPreferences.getString("alarms","");
        if(!alarms.isEmpty()){
            String[] big_split = alarms.split("big_split");
            for(int i = 0;i<big_split.length;i++){
                String[] small_split = big_split[i].split("small_split",-1);
                if(small_split.length ==12) {
                    create_the_alarm(small_split[0], small_split[1], small_split[2], small_split[3], small_split[4], Integer.parseInt(small_split[5]), Integer.parseInt(small_split[6]), Long.parseLong(small_split[7]), Long.parseLong(small_split[8]), Long.parseLong(small_split[9]), Integer.parseInt(small_split[10]), small_split[11]);
                }
            }
        }
    }
    private void create_the_alarm(String habit_name,String type_of_notification,String good_or_bad_habit,String type_of_habit,String icon_of_habit,int value_of_position,int id,long time,long start_time,long set_time,int request_code,String extra_notification_information){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, Send_notifcation_at_set_time.class);
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
        pendingIntent = PendingIntent.getBroadcast(this, request_code, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) + time + TimeUnit.DAYS.toMillis(1),
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    private void post_the_notication(){
        if (Build.VERSION.SDK_INT >= 26) {
            String CHANNEL_ID = "Configuring_notifications";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Configuring notifications",
                    NotificationManager.IMPORTANCE_MIN);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.easy_habits_icon)
                    .setColor(Color.parseColor("#607D8B"))
                    .setContentTitle("Configuring Notifications")
                    .setContentText("Setting up your notifications").build();

            this.startForeground(106437, notification);
        }
    }
}