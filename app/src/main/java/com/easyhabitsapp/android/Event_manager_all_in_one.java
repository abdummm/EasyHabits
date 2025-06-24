package com.easyhabitsapp.android;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Event_manager_all_in_one extends Application {

    private static final Event_manager_all_in_one instance = new Event_manager_all_in_one();

    enum Event_type_fire_base_record{
        home_clicked,
        sharing_clicked,
        post_clicked,
        tools_clicked,
        settings_clicked,
        monthly_clicked,
        yearly_clicked,
        buy_more_coins_clicked,
        purchase_more_coins_clicked,
        app_shared,
        get_my_referral_code_clicked,
        copy_code_clicked,
        enter_code_successful,
        post_opened,
        post_shared,
        comment_shared,
        reply_shared,
        post_reported,
        comment_reported,
        reply_reported,
        post_upvoted,
        post_downvoted,
        comment_upvote,
        comment_downvote,
        reply_upvote,
        reply_downvote,
        post_written_clicked,
        post_written_posted,
        comment_written,
        reply_written,
        post_saved,
        comment_saved,
        reply_saved,
        post_gifted,
        comment_gifted,
        reply_gifted,
        chat_started,
        rate_app_clicked,
        report_a_bug_clicked,
        contact_the_developer_clicked,
        mood_tracker_clicked,
        mood_recorded,
        share_mood_clicked,
        journal_clicked,
        weight_tracker_clicked,
        counter_clicked,
        add_a_habit_clicked,
        habit_added_bad,
        habit_added_good,
        habit_opened,
        share_habit_clicked,
        user_recorded_inter_action,
        user_recorded_inter_action_from_notification,
    }

    private Event_manager_all_in_one() {
        //empty but required constructor for a singleton
    }

    public static Event_manager_all_in_one getInstance() {
        return instance;
    }

    public void record_fire_base_event(Context context,Event_type_fire_base_record event,boolean only_once){
        if(context!=null) {
            Bundle bundle = new Bundle();
            if (only_once) {
                if (!was_this_saved_before(context, event.name())) {
                    log_event(context, event.name(), bundle);
                    save_this(context, event.name());
                }
            } else {
                log_event(context, event.name(), bundle);
                save_this(context, event.name());
            }
        }
    }
    public void record_fire_base_event(Context context,Event_type_fire_base_record event,boolean only_once,Bundle bundle){
        if(context!=null) {
            if (only_once) {
                if (!was_this_saved_before(context, event.name())) {
                    log_event(context, event.name(), bundle);
                    save_this(context, event.name());
                }
            } else {
                log_event(context, event.name(), bundle);
                save_this(context, event.name());
            }
        }
    }


    private boolean was_this_saved_before(Context context,String where){
        return Save_and_get.getInstance().get_this_boolean(context,"firebase_events",where);
    }

    private void save_this(Context context,String where){
        Save_and_get.getInstance().save_this(context,true,"firebase_events",where);
    }

    private void log_event(Context context,String name,Bundle bundle){
        FirebaseAnalytics.getInstance(context).logEvent(name, bundle);
    }
}
