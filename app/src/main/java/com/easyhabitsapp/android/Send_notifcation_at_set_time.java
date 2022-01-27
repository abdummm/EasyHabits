package com.easyhabitsapp.android;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;

public class Send_notifcation_at_set_time extends BroadcastReceiver {
    private String channel_name;
    private Context context;
    private String channel_id;
    private int id;
    private String days;
    private Intent intent;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
        define_the_constant(intent);
        if(should_i_run()) {
            create_channel();
            show_notifaction();
        }
    }

    private void create_channel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = return_channel_name();
            String description = return_channel_description();
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channel_id, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void show_notifaction(){
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, after_login.class), PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channel_id)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.chat_get_message)
                .setContentTitle(channel_name)
                .setContentText("Don't forget to log your input for ".concat(channel_name))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(id, builder.build());
    }

    private String return_channel_name(){
        if(id == 1){
            return "Good habits reminder";
        } else {
            return "App notification";
        }
    }

    private String return_channel_description(){
        if(id == 1){
            return "notification sent to remind you to log input for good habits";
        } else {
            return "App description";
        }
    }

    private void define_the_constant(Intent intent){
        channel_name = intent.getStringExtra("channel_name");
        channel_id = intent.getStringExtra("channel_id");
        id = intent.getIntExtra("id",99);
        days = intent.getStringExtra("days");
        Log.w("name","name" + channel_name);
        Log.w("name","id" + channel_id);
    }

    private boolean should_i_run(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        String today = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK) -2);
        if(days.contains(today)){
            return true;
        } else {
            return false;
        }
    }
}
