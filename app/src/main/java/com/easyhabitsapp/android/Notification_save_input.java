package com.easyhabitsapp.android;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Notification_save_input extends BroadcastReceiver {
    private Context context;
    private ArrayList<Long> history_of_relapse;
    private String good_or_bad_habit;
    private String type_of_habit;
//    private int value_for_position;
    private int id;
    private int notification_id;
    private HashMap<Long, Integer> hash_map_amount;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        this.good_or_bad_habit = intent.getStringExtra("good_or_bad");
        this.type_of_habit = intent.getStringExtra("type_of_habit");
//        this.value_for_position = intent.getIntExtra("value_for_position",0);
        this.id = intent.getIntExtra("id",0);
        this.notification_id = intent.getIntExtra("notification_id",0);
        put_all_the_relapses_into_a_array_list();
        save_the_input_for_good_habit_input(intent.getStringExtra("yes_or_no"),System.currentTimeMillis());
        cancel_notification();
        start_activity();
        show_toast();
        Event_manager_all_in_one.getInstance().record_fire_base_event(context, Event_manager_all_in_one.Event_type_fire_base_record.user_recorded_inter_action_from_notification,false);
    }

    private void save_the_input_for_good_habit_input(String yes_or_no, long time_in_milli) {
        ArrayList<Long> relapse = return_relapses();
        time_in_milli = Simplify_the_time.return_time_in_midnight(time_in_milli);
        if (yes_or_no.equals("yes")) {
            int index = Collections.binarySearch(history_of_relapse, time_in_milli);
            if (index < 0) {
                index = -index - 1;
            } else {
                return; // already in list
            }
            relapse.add(index, time_in_milli);
        } else if (yes_or_no.equals("no")) {
            relapse.remove(time_in_milli);
        }
        Room_database_habits database_habits = Room_database_habits.getInstance(context);
        database_habits.dao_for_habits_data().update_relapse(id, relapse);
    }

    private ArrayList<Long> return_relapses() {
        habits_data_class habits_data_class = return_data_with_id();
        if (habits_data_class == null || habits_data_class.getRelapse() == null) {
            return new ArrayList<>();
        } else {
            return habits_data_class.getRelapse();
        }
    }

    private void put_all_the_relapses_into_a_array_list() {
        habits_data_class habits_data_class = return_data_with_id();
        history_of_relapse = new ArrayList<>();
        hash_map_amount = new HashMap<>();
        if (good_or_bad_habit.equals("bad")) {
            if (habits_data_class.getRelapse() != null && !habits_data_class.getRelapse().isEmpty()) {
                history_of_relapse.addAll(return_relapses());
            }
        } else if (good_or_bad_habit.equals("good")) {
            if (type_of_habit.equals("yes/no")) {
                if (habits_data_class.getRelapse() != null && !habits_data_class.getRelapse().isEmpty()) {
                    history_of_relapse.addAll(return_relapses());
                }
            } else if (type_of_habit.equals("amount")) {
                hash_map_amount.putAll(return_relapse_amount());
            } /*else if (type_of_habit.equals("timer")) {

            }*/
        }
    }
    private void cancel_notification(){
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(notification_id);
    }

    private void start_activity(){
        Intent activity = new Intent(context, after_login.class).putExtra("open_habit",id);
        activity.setClassName(context.getPackageName(), after_login.class.getName());
        activity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(activity);
    }
    private habits_data_class return_data_with_id(){
        Room_database_habits room_database_habits = Room_database_habits.getInstance(context);
        List<habits_data_class> list = room_database_habits.dao_for_habits_data().getAll();
        habits_data_class habits_data_class = null;
        for(int i = 0;i< list.size();i++){
            if(list.get(i).getId() == id){
                habits_data_class = list.get(i);
            }
        }
        return habits_data_class;
    }
    private HashMap<Long, Integer> return_relapse_amount() {
        habits_data_class habits_data_class = return_data_with_id();
        if (habits_data_class.getRelapse_amount() == null) {
            return new HashMap<Long, Integer>();
        } else {
            return habits_data_class.getRelapse_amount();
        }
    }

    private void show_toast(){
        Toast.makeText(context, "Input recorded!", Toast.LENGTH_SHORT).show();
    }
}
