package com.easyhabitsapp.android;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.text.HtmlCompat;

import java.util.Calendar;

public class Send_notifcation_at_set_time extends BroadcastReceiver {
    //private String channel_name;
    private Context context;
    //private String channel_id;
    //private int id;
    private String habit_name;
    private String type_of_notification;
    private String extra_information = "";
    private String good_or_bad_habit;
    private String type_of_habit;
    private int id;
    //    private int value_for_position;
    private long start_time;
    private Intent intent;
    private String icon;
//    private long set_time;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
        define_the_constant(intent);
        if (should_i_run() && are_notifications_enabled(context)) {
            create_channel();
            show_notifaction();
        }
    }

    private void create_channel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Daily log reminder";
            String description = "reminder to log your daily input";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("Daily log", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void show_notifaction() {
        String text_of_notification = "";
        if (good_or_bad_habit.equals("good")) {
            if (type_of_habit.equals("yes/no")) {
                text_of_notification = "Did you complete this habit today?";
            } else if (type_of_habit.equals("amount")) {
                text_of_notification = "How many times did you complete this habit today?";
            } else if (type_of_habit.equals("timer")) {
                text_of_notification = "How many times did you complete this habit?";
            }
        } else if (good_or_bad_habit.equals("bad")) {
            text_of_notification = "Did you relapse today?";
        }
        int notificaion_id = write_shared_preferences();
        Intent yes_intent = new Intent(context, Notification_save_input.class);
        yes_intent.putExtra("yes_or_no", "yes");
//        yes_intent.setAction(String.valueOf(notificaion_id + 1));
        Intent no_intent = new Intent(context, Notification_save_input.class);
        no_intent.putExtra("yes_or_no", "no");
//        no_intent.setAction(String.valueOf(notificaion_id + 2));

        yes_intent.putExtra("good_or_bad", good_or_bad_habit);
        no_intent.putExtra("good_or_bad", good_or_bad_habit);
        if (good_or_bad_habit.equals("good")) {
            yes_intent.putExtra("type_of_habit", type_of_habit);
            no_intent.putExtra("type_of_habit", type_of_habit);
        }
//        yes_intent.putExtra("value_for_position", value_for_position);
//        no_intent.putExtra("value_for_position", value_for_position);
        yes_intent.putExtra("id", id);
        no_intent.putExtra("id", id);
        yes_intent.putExtra("notification_id", notificaion_id);
        no_intent.putExtra("notification_id", notificaion_id);
        /*yes_intent.putExtra("start_time",start_time);
        no_intent.putExtra("start_time",start_time);*/

        PendingIntent contentIntent = PendingIntent.getActivity(context, notificaion_id, new Intent(context, after_login.class).putExtra("open_habit", id), PendingIntent.FLAG_IMMUTABLE);
        PendingIntent yes_pending_intent = PendingIntent.getBroadcast(context, notificaion_id + 1, yes_intent, PendingIntent.FLAG_IMMUTABLE);
        PendingIntent no_pending_intent = PendingIntent.getBroadcast(context, notificaion_id + 2, no_intent, PendingIntent.FLAG_IMMUTABLE);
        if (good_or_bad_habit.equals("bad")) {
            @SuppressLint("NotificationTrampoline") NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Daily log")
                    .setContentIntent(contentIntent)
//                    .setWhen(0)
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.easy_habits_icon)
                    .setContentTitle("Did you relapse today?")
                    .setContentText("Don't forget to log your input for ".concat(habit_name))
                    .setColor(Color.parseColor("#607D8B"))
                    .addAction(0, HtmlCompat.fromHtml("<font color=\"" + ContextCompat.getColor(context, R.color.fav) + "\">" + "Yes" + "</font>", HtmlCompat.FROM_HTML_MODE_LEGACY), yes_pending_intent)
                    .addAction(0, HtmlCompat.fromHtml("<font color=\"" + ContextCompat.getColor(context, R.color.fav) + "\">" + "No" + "</font>", HtmlCompat.FROM_HTML_MODE_LEGACY), no_pending_intent)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(notificaion_id, builder.build());
        } else if (good_or_bad_habit.equals("good")) {
            if (type_of_habit.equals("yes/no")) {
                @SuppressLint("NotificationTrampoline") NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Daily log")
                        .setContentIntent(contentIntent)
//                        .setWhen(0)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.easy_habits_icon)
                        .setContentTitle("Did you complete this habit today?")
                        .setContentText("Don't forget to log your input for ".concat(habit_name))
                        .setColor(Color.parseColor("#607D8B"))
                        .addAction(0, HtmlCompat.fromHtml("<font color=\"" + ContextCompat.getColor(context, R.color.fav) + "\">" + "Yes" + "</font>", HtmlCompat.FROM_HTML_MODE_LEGACY), yes_pending_intent)
                        .addAction(0, HtmlCompat.fromHtml("<font color=\"" + ContextCompat.getColor(context, R.color.fav) + "\">" + "No" + "</font>", HtmlCompat.FROM_HTML_MODE_LEGACY), no_pending_intent)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                notificationManager.notify(notificaion_id, builder.build());
            } else if (type_of_habit.equals("amount")) {
                @SuppressLint("NotificationTrampoline") NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Daily log")
                        .setContentIntent(contentIntent)
//                        .setWhen(0)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.easy_habits_icon)
                        .setContentTitle("Did you complete this habit today?")
                        .setContentText("Don't forget to log your input for ".concat(habit_name))
                        .setColor(Color.parseColor("#607D8B"))
                        .setPriority(NotificationCompat.PRIORITY_HIGH);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                notificationManager.notify(notificaion_id, builder.build());
            }
        }
    }


    private void define_the_constant(Intent intent) {
        //channel_name = intent.getStringExtra("channel_name");
        //channel_id = intent.getStringExtra("channel_id");
        //id = intent.getIntExtra("id", 99);
        habit_name = intent.getStringExtra("habit_name");
        type_of_notification = intent.getStringExtra("type_of_notification");
        good_or_bad_habit = intent.getStringExtra("good_or_bad_habit");
        type_of_habit = intent.getStringExtra("type_of_habit");
        icon = intent.getStringExtra("icon_of_habit");
//        this.value_for_position = intent.getIntExtra("value_for_position", 0);
        this.id = intent.getIntExtra("id", 0);
        this.start_time = intent.getLongExtra("start_time", System.currentTimeMillis());
//        this.set_time = intent.getLongExtra("set_time", System.currentTimeMillis());
        this.extra_information = intent.getStringExtra("extra_information");
    }

    private boolean should_i_run() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        if (type_of_notification.equals("everyday")) {
            return true;
        } else if (type_of_notification.equals("daysperweek") && !extra_information.equals("")) {
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                if (extra_information.contains("Mo")) {
                    return true;
                } else {
                    return false;
                }
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                if (extra_information.contains("Tu")) {
                    return true;
                } else {
                    return false;
                }
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                if (extra_information.contains("We")) {
                    return true;
                } else {
                    return false;
                }
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                if (extra_information.contains("Th")) {
                    return true;
                } else {
                    return false;
                }
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                if (extra_information.contains("Fr")) {
                    return true;
                } else {
                    return false;
                }
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                if (extra_information.contains("Sa")) {
                    return true;
                } else {
                    return false;
                }
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                if (extra_information.contains("Su")) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } else if (type_of_notification.equals("everyndays")&& !extra_information.equals("")) {
            long how_many_days_passed = (Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(start_time)) % 86400000L;
            if (how_many_days_passed == 0) {
                return true;
            } else {
                return false;
            }
        } else if (type_of_notification.equals("dayspermonth")&& !extra_information.equals("")) {
            String[] array = extra_information.split("_");
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)))) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /*private int return_icon(String name){
        Resources resources = context.getResources();
        return resources.getIdentifier(name, "drawable", context.getPackageName());
    }*/

    private int write_shared_preferences() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("latest_notification_for_habit", MODE_PRIVATE);
        int notification_id = sharedPreferences.getInt("latest_notification_for_habit", 0);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putInt("latest_notification_for_habit", notification_id + 3);
        myEdit.commit();
        return notification_id;
    }

    private boolean are_notifications_enabled(Context context) {
        return NotificationManagerCompat.from(context).areNotificationsEnabled();
    }
}
