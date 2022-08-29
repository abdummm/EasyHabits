package com.easyhabitsapp.android;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static android.content.Context.MODE_PRIVATE;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class edit_bad_habits extends Fragment {

    private int position;
    private int year_global;
    private int month_global;
    private int day_global;
    private String which_spinner_is_chosen;
    private long time_in_milli = 0;
    private String color = "#607D8B";
    private String icon = "";
    private String which_days_are_chosen;
    private boolean should_the_habit_spinner_run = true;
    private boolean should_the_notification_spinner_run = true;

    public edit_bad_habits() {
        // Required empty public constructor
    }

    public edit_bad_habits(int position) {
        this.position = position;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_bad_habits, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*read_the_bad_habits();
        changte_habit_button_listen();
        change_date_button_listen();
        goal_days_text_watcher();
        change_color_listen();
        pick_icon_button_lsitener();
        update_button_listen();
        back_button_listen();*/
        choose_date_button_listen();
        goal_days_text_watcher();
        done_button_listen();
        pick_icon_button_lsitener();
        back_button_listen();
        change_color_listen();
        bad_and_god_habit_listen();
        typeof_the_habit_yes_no();
        set_up_spinner();
        set_the_spinner_for_freq_of_good_habit();
        set_the_spinner_notification();
        set_am_pm_npotofaction_time_freq();
        notification_time_click_listen();
//        set_up_time_in_hour();
        notification_button_listen();
        on_switch_listen();
        on_habit_frequency_spinner_choose_listen();
        button_in_calender_set_a_habit_listen();
        text_watcher_for_goals();
        every_couple_of_days_text_watcher();
        watch_time_edit_text();
        times_per_period_watch_in_the_end();
        notification_spinner();
        every_n_days_notifacation();
        times_listen_notification();
        end_text_listen_notifications();
        notification_month_listen();
        make_text_bold();
        make_habit_frequency_text_bold();
        text_under_start_date();
        pre_setup_the_info_for_edit_habit();
    }

    private void pre_setup_the_info_for_edit_habit() {
        if (getView() != null) {
            bad_or_good_habit_setup();
            habit_name_setup();
            change_start_date_set_up();
            set_up_goals_habit();
            set_the_color_habit();
            set_the_icon_habit();
            set_habit_frequency();
            set_notification_frequency();
        }
    }

    private void bad_or_good_habit_setup() {
        if (getView() != null) {
            Button Bad_habit_button_in_add_a_habit = getView().findViewById(R.id.Bad_habit_button_in_add_a_habit);
            Button good_habit_button_in_add_a_habit = getView().findViewById(R.id.good_habit_button_in_add_a_habit);
            final ConstraintLayout layout_inside_add_habit_to_determine_good_nad_bad_habit = getView().findViewById(R.id.layout_inside_add_habit_to_determine_good_nad_bad_habit);
            final ConstraintLayout layout_listing_the_type_of_habits_good_habit_add_a_habit = getView().findViewById(R.id.layout_listing_the_type_of_habits_good_habit_add_a_habit);
            final ConstraintLayout layout_mentioning_the_spinner_and_frequency = getView().findViewById(R.id.layout_mentioning_the_spinner_and_frequency);
            final View background_of_choose_frequency_add_a_habit = getView().findViewById(R.id.background_of_choose_frequency_add_a_habit);
            Button yes_or_no_add_habit = getView().findViewById(R.id.yes_or_no_add_habit);
            Button amount_add_habit = getView().findViewById(R.id.amount_add_habit);
            Button timer_add_habit = getView().findViewById(R.id.timer_add_habit);
            final ConstraintLayout layout_saying_the_amount_edit_text = getView().findViewById(R.id.layout_saying_the_amount_edit_text);
            final ConstraintLayout layout_saying_the_time_edit_text = getView().findViewById(R.id.layout_saying_the_time_edit_text);
            final View bottom_of_yes_no_habit = getView().findViewById(R.id.bottom_of_yes_no_habit);
            EditText edit_text_to_enter_amount_in_add_habit = getView().findViewById(R.id.edit_text_to_enter_amount_in_add_habit);
            if (return_the_information_from_save(6).equals("bad")) {
                set_clicked_button(Bad_habit_button_in_add_a_habit);
                set_not_clicked_button(good_habit_button_in_add_a_habit);
                layout_inside_add_habit_to_determine_good_nad_bad_habit.setVisibility(View.VISIBLE);
                layout_listing_the_type_of_habits_good_habit_add_a_habit.setVisibility(View.GONE);
                layout_mentioning_the_spinner_and_frequency.setVisibility(View.GONE);
                background_of_choose_frequency_add_a_habit.setVisibility(View.GONE);
            } else if (return_the_information_from_save(6).equals("good")) {
                set_clicked_button(good_habit_button_in_add_a_habit);
                set_not_clicked_button(Bad_habit_button_in_add_a_habit);
                layout_inside_add_habit_to_determine_good_nad_bad_habit.setVisibility(View.VISIBLE);
                layout_listing_the_type_of_habits_good_habit_add_a_habit.setVisibility(View.VISIBLE);
                layout_mentioning_the_spinner_and_frequency.setVisibility(View.VISIBLE);
                background_of_choose_frequency_add_a_habit.setVisibility(View.VISIBLE);
                if (return_the_information_from_save(7).equals("yes/no")) {
                    set_clicked_button(yes_or_no_add_habit);
                    set_not_clicked_button(amount_add_habit);
                    set_not_clicked_button(timer_add_habit);
                    layout_saying_the_amount_edit_text.setVisibility(View.GONE);
                    layout_saying_the_time_edit_text.setVisibility(View.GONE);
                } else if (return_the_information_from_save(7).equals("amount")) {
                    set_not_clicked_button(yes_or_no_add_habit);
                    set_clicked_button(amount_add_habit);
                    set_not_clicked_button(timer_add_habit);
                    layout_saying_the_amount_edit_text.setVisibility(View.VISIBLE);
                    layout_saying_the_time_edit_text.setVisibility(View.GONE);
                    if (getView() != null && getContext() != null) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        constraintSet.clone(layout_listing_the_type_of_habits_good_habit_add_a_habit);
                        constraintSet.connect(bottom_of_yes_no_habit.getId(), ConstraintSet.TOP, layout_saying_the_amount_edit_text.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(8, getContext()));
                        constraintSet.applyTo(layout_listing_the_type_of_habits_good_habit_add_a_habit);
                    }
                    edit_text_to_enter_amount_in_add_habit.setText(return_the_information_from_save(9));
                }
            }
        }
    }

    private void habit_name_setup() {
        if (getView() != null) {
            EditText edit_text_showing_habit_name = getView().findViewById(R.id.edit_text_showing_habit_name);
            edit_text_showing_habit_name.setText(return_the_information_from_save(1));
        }
    }

    private void change_start_date_set_up() {
        if (getView() != null) {
            Button button_that_will_open_time_user_hacked = getView().findViewById(R.id.button_that_will_open_time_user_hacked);
            TextView text_to_sayy_enter_time_of_last_relapse = getView().findViewById(R.id.text_to_sayy_enter_time_of_last_relapse);
            Calendar calendar = Calendar.getInstance();
            Date date = new Date();
            date.setTime(calendar.getTimeInMillis());
            String date_in_string = DateFormat.getDateInstance(DateFormat.MEDIUM).format(date);
            time_in_milli = calendar.getTimeInMillis();
            button_that_will_open_time_user_hacked.setText("Change Start Date");
            text_to_sayy_enter_time_of_last_relapse.setText("Current Start Date: ".concat(date_in_string));
            Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
            text_to_sayy_enter_time_of_last_relapse.setTypeface(boldTypeface);
            text_to_sayy_enter_time_of_last_relapse.setTextSize(16);
        }
    }

    private void set_up_goals_habit() {
        if (getView() != null) {
            EditText enter_goal_for_new_good_habit_in_habits = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits);
            enter_goal_for_new_good_habit_in_habits.setText(return_the_information_from_save(3));
        }
    }

    private void set_the_color_habit() {
        if (getView() != null) {
            View view_showing_the_icon_and_its_color = getView().findViewById(R.id.view_showing_the_icon_and_its_color);
            TextView text_view_asying_this_is_example_of_icon = getView().findViewById(R.id.text_view_asying_this_is_example_of_icon);
            String text = return_the_information_from_save(4);
            color = text;
            view_showing_the_icon_and_its_color.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(text)));
            text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor(text));
        }
    }

    private void set_habit_frequency() {
        if (getView() != null) {
            Custom_spinner spinner_to_choose_the_frequency_of_the_habit = getView().findViewById(R.id.spinner_to_choose_the_frequency_of_the_habit);
            TextView show_which_days_are_chosen_add_a_habit = getView().findViewById(R.id.show_which_days_are_chosen_add_a_habit);
            ConstraintLayout constraint_layout_to_choose_every_N_days = getView().findViewById(R.id.constraint_layout_to_choose_every_N_days);
            ConstraintLayout calender_in_add_a_habit = getView().findViewById(R.id.calender_in_add_a_habit);
            ConstraintLayout layout_saying_the_frequency_off_per_period_in_add_habit = getView().findViewById(R.id.layout_saying_the_frequency_off_per_period_in_add_habit);
            EditText enter_goal_for_new_good_habit_in_habits_add_habit = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits_add_habit);
            if (return_the_information_from_save(8).equals("everyday")) {
                place_the_text_under_habit_frequency(0);
                should_the_habit_spinner_run = false;
                spinner_to_choose_the_frequency_of_the_habit.setSelection(0);
            } else if (return_the_information_from_save(8).equals("daysperweek")) {
                place_the_text_under_habit_frequency(1);
                which_days_are_chosen = return_the_information_from_save(10);
                show_which_days_are_chosen_add_a_habit.setVisibility(View.VISIBLE);
                constraint_layout_to_choose_every_N_days.setVisibility(View.GONE);
                calender_in_add_a_habit.setVisibility(View.GONE);
                layout_saying_the_frequency_off_per_period_in_add_habit.setVisibility(View.GONE);
                should_the_habit_spinner_run = false;
                spinner_to_choose_the_frequency_of_the_habit.setSelection(1);
                String days_per_week = return_the_information_from_save(10);
                if (days_per_week.equals("Mo".concat("Tu").concat("We").concat("Th").concat("Fr").concat("Sa").concat("Su"))) {
                    show_which_days_are_chosen_add_a_habit.setText("Everyday");
                } else if (days_per_week.length() == 12) {
                    boolean monday = false;
                    boolean tuesday = false;
                    boolean wednesday = false;
                    boolean thursday = false;
                    boolean friday = false;
                    boolean saturday = false;
                    boolean sunday = false;
                    if (days_per_week.contains("Mo")) {
                        monday = true;
                    }
                    if (days_per_week.contains("Tu")) {
                        tuesday = true;
                    }
                    if (days_per_week.contains("We")) {
                        wednesday = true;
                    }
                    if (days_per_week.contains("Th")) {
                        thursday = true;
                    }
                    if (days_per_week.contains("Fr")) {
                        friday = true;
                    }
                    if (days_per_week.contains("Sa")) {
                        saturday = true;
                    }
                    if (days_per_week.contains("Su")) {
                        sunday = true;
                    }
                    if (!monday) {
                        show_which_days_are_chosen_add_a_habit.setText("Everyday except Monday");
                    }
                    if (!tuesday) {
                        show_which_days_are_chosen_add_a_habit.setText("Everyday except Tuesday");
                    }
                    if (!wednesday) {
                        show_which_days_are_chosen_add_a_habit.setText("Everyday except Wednesday");
                    }
                    if (!thursday) {
                        show_which_days_are_chosen_add_a_habit.setText("Everyday except Thursday");
                    }
                    if (!friday) {
                        show_which_days_are_chosen_add_a_habit.setText("Everyday except Friday");
                    }
                    if (!saturday) {
                        show_which_days_are_chosen_add_a_habit.setText("Everyday except Saturday");
                    }
                    if (!sunday) {
                        show_which_days_are_chosen_add_a_habit.setText("Everyday except Sunday");
                    }
                } else if (days_per_week.length() == 2) {
                    if (days_per_week.equals("Mo")) {
                        show_which_days_are_chosen_add_a_habit.setText("Monday");
                    } else if (days_per_week.equals("Tu")) {
                        show_which_days_are_chosen_add_a_habit.setText("Tuesday");
                    } else if (days_per_week.equals("We")) {
                        show_which_days_are_chosen_add_a_habit.setText("Wednesday");
                    } else if (days_per_week.equals("Th")) {
                        show_which_days_are_chosen_add_a_habit.setText("Thursday");
                    } else if (days_per_week.equals("Fr")) {
                        show_which_days_are_chosen_add_a_habit.setText("Friday");
                    } else if (days_per_week.equals("Sa")) {
                        show_which_days_are_chosen_add_a_habit.setText("Saturday");
                    } else if (days_per_week.equals("Su")) {
                        show_which_days_are_chosen_add_a_habit.setText("Sunday");
                    }
                } else {
                    boolean monday = false;
                    boolean tuesday = false;
                    boolean wednesday = false;
                    boolean thursday = false;
                    boolean friday = false;
                    boolean saturday = false;
                    boolean sunday = false;
                    if (days_per_week.contains("Mo")) {
                        monday = true;
                    }
                    if (days_per_week.contains("Tu")) {
                        tuesday = true;
                    }
                    if (days_per_week.contains("We")) {
                        wednesday = true;
                    }
                    if (days_per_week.contains("Th")) {
                        thursday = true;
                    }
                    if (days_per_week.contains("Fr")) {
                        friday = true;
                    }
                    if (days_per_week.contains("Sa")) {
                        saturday = true;
                    }
                    if (days_per_week.contains("Su")) {
                        sunday = true;
                    }
                    String final_text = "";
                    if (monday) {
                        final_text = "Mo.,";
                    }
                    if (tuesday) {
                        final_text = final_text.concat("Tu.,");
                    }
                    if (wednesday) {
                        final_text = final_text.concat("We.,");
                    }
                    if (thursday) {
                        final_text = final_text.concat("Th.,");
                    }
                    if (friday) {
                        final_text = final_text.concat("Fr.,");
                    }
                    if (saturday) {
                        final_text = final_text.concat("Sa.,");
                    }
                    if (sunday) {
                        final_text = final_text.concat("Su.,");
                    }
                    show_which_days_are_chosen_add_a_habit.setText(final_text);
                }
            } else if (return_the_information_from_save(8).equals("everyndays")) {
                place_the_text_under_habit_frequency(2);
                should_the_habit_spinner_run = false;
                spinner_to_choose_the_frequency_of_the_habit.setSelection(2);
                show_which_days_are_chosen_add_a_habit.setVisibility(View.GONE);
                constraint_layout_to_choose_every_N_days.setVisibility(View.VISIBLE);
                calender_in_add_a_habit.setVisibility(View.GONE);
                layout_saying_the_frequency_off_per_period_in_add_habit.setVisibility(View.GONE);
                enter_goal_for_new_good_habit_in_habits_add_habit.setText(return_the_information_from_save(10));
            } else if (return_the_information_from_save(8).equals("dayspermonth")) {
                place_the_text_under_habit_frequency(3);
                should_the_habit_spinner_run = false;
                spinner_to_choose_the_frequency_of_the_habit.setSelection(3);
                show_which_days_are_chosen_add_a_habit.setVisibility(View.GONE);
                constraint_layout_to_choose_every_N_days.setVisibility(View.GONE);
                calender_in_add_a_habit.setVisibility(View.VISIBLE);
                layout_saying_the_frequency_off_per_period_in_add_habit.setVisibility(View.GONE);
                String[] days = return_the_information_from_save(10).split("_");
                color_the_buttons_automotic(days);
            }
        }
    }

    private void set_notification_frequency() {
        if (getView() != null) {
            final ConstraintLayout layout_notification_in_add_a_habit = getView().findViewById(R.id.layout_notification_in_add_a_habit);
            SwitchMaterial switch_to_turn_notifications_add_a_habit = getView().findViewById(R.id.switch_to_turn_notifications_add_a_habit);
            Custom_spinner spinner_to_choose_the_notification_of_the_habit = getView().findViewById(R.id.spinner_to_choose_the_notification_of_the_habit);
            TextView show_which_days_are_chosen_add_a_habit_notifications = getView().findViewById(R.id.show_which_days_are_chosen_add_a_habit_notifications);
            ConstraintLayout calender_in_add_a_habit_add_a_notification = getView().findViewById(R.id.calender_in_add_a_habit_add_a_notification);
            ConstraintLayout constraint_layout_to_choose_every_N_days_in_notification = getView().findViewById(R.id.constraint_layout_to_choose_every_N_days_in_notification);
            ConstraintLayout layout_saying_the_frequency_off_per_period_in_add_habit_notification = getView().findViewById(R.id.layout_saying_the_frequency_off_per_period_in_add_habit_notification);
            EditText enter_goal_for_new_good_habit_in_habits_add_habit_notification = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits_add_habit_notification);
            TextView text_view_saying_the_hour_of_notification = getView().findViewById(R.id.text_view_saying_the_hour_of_notification);
            TextView text_view_saying_the_minute_of_notification = getView().findViewById(R.id.text_view_saying_the_minute_of_notification);
            Spinner spinner_to_choose_am_or_pm_in_add_habit_notifactaion_freq = getView().findViewById(R.id.spinner_to_choose_am_or_pm_in_add_habit_notifactaion_freq);
            if (return_the_information_from_save(12).equals("true")) {
                switch_to_turn_notifications_add_a_habit.setChecked(true);
                layout_notification_in_add_a_habit.setVisibility(View.VISIBLE);
                if (return_the_information_from_save(13).equals("everyday")) {
                    should_the_notification_spinner_run = false;
                    spinner_to_choose_the_notification_of_the_habit.setSelection(0);
                    show_which_days_are_chosen_add_a_habit_notifications.setVisibility(View.GONE);
                    calender_in_add_a_habit_add_a_notification.setVisibility(View.GONE);
                    constraint_layout_to_choose_every_N_days_in_notification.setVisibility(View.GONE);
                    layout_saying_the_frequency_off_per_period_in_add_habit_notification.setVisibility(View.GONE);
                } else if (return_the_information_from_save(13).equals("daysperweek")) {
                    should_the_notification_spinner_run = false;
                    spinner_to_choose_the_notification_of_the_habit.setSelection(1);
                    show_which_days_are_chosen_add_a_habit_notifications.setVisibility(View.VISIBLE);
                    calender_in_add_a_habit_add_a_notification.setVisibility(View.GONE);
                    constraint_layout_to_choose_every_N_days_in_notification.setVisibility(View.GONE);
                    layout_saying_the_frequency_off_per_period_in_add_habit_notification.setVisibility(View.GONE);
                    String days = return_the_information_from_save(14);
                    if (days.equals("Mo".concat("Tu").concat("We").concat("Th").concat("Fr").concat("Sa").concat("Su"))) {
                        show_which_days_are_chosen_add_a_habit_notifications.setText("Everyday");
                    } else if (days.length() == 12) {
                        boolean monday = false;
                        boolean tuesday = false;
                        boolean wednesday = false;
                        boolean thursday = false;
                        boolean friday = false;
                        boolean saturday = false;
                        boolean sunday = false;
                        if (days.contains("Mo")) {
                            monday = true;
                        }
                        if (days.contains("Tu")) {
                            tuesday = true;
                        }
                        if (days.contains("We")) {
                            wednesday = true;
                        }
                        if (days.contains("Th")) {
                            thursday = true;
                        }
                        if (days.contains("Fr")) {
                            friday = true;
                        }
                        if (days.contains("Sa")) {
                            saturday = true;
                        }
                        if (days.contains("Su")) {
                            sunday = true;
                        }
                        if (!monday) {
                            show_which_days_are_chosen_add_a_habit_notifications.setText("Everyday except Monday");
                        }
                        if (!tuesday) {
                            show_which_days_are_chosen_add_a_habit_notifications.setText("Everyday except Tuesday");
                        }
                        if (!wednesday) {
                            show_which_days_are_chosen_add_a_habit_notifications.setText("Everyday except Wednesday");
                        }
                        if (!thursday) {
                            show_which_days_are_chosen_add_a_habit_notifications.setText("Everyday except Thursday");
                        }
                        if (!friday) {
                            show_which_days_are_chosen_add_a_habit_notifications.setText("Everyday except Friday");
                        }
                        if (!saturday) {
                            show_which_days_are_chosen_add_a_habit_notifications.setText("Everyday except Saturday");
                        }
                        if (!sunday) {
                            show_which_days_are_chosen_add_a_habit_notifications.setText("Everyday except Sunday");
                        }
                    } else {
                        boolean monday = false;
                        boolean tuesday = false;
                        boolean wednesday = false;
                        boolean thursday = false;
                        boolean friday = false;
                        boolean saturday = false;
                        boolean sunday = false;
                        if (days.contains("Mo")) {
                            monday = true;
                        }
                        if (days.contains("Tu")) {
                            tuesday = true;
                        }
                        if (days.contains("We")) {
                            wednesday = true;
                        }
                        if (days.contains("Th")) {
                            thursday = true;
                        }
                        if (days.contains("Fr")) {
                            friday = true;
                        }
                        if (days.contains("Sa")) {
                            saturday = true;
                        }
                        if (days.contains("Su")) {
                            sunday = true;
                        }
                        String final_text = "";
                        if (monday) {
                            final_text = "Mo.,";
                        }
                        if (tuesday) {
                            final_text = final_text.concat("Tu.,");
                        }
                        if (wednesday) {
                            final_text = final_text.concat("We.,");
                        }
                        if (thursday) {
                            final_text = final_text.concat("Th.,");
                        }
                        if (friday) {
                            final_text = final_text.concat("Fr.,");
                        }
                        if (saturday) {
                            final_text = final_text.concat("Sa.,");
                        }
                        if (sunday) {
                            final_text = final_text.concat("Su.,");
                        }
                        show_which_days_are_chosen_add_a_habit_notifications.setText(final_text);
                    }
                } else if (return_the_information_from_save(13).equals("everyndays")) {
                    should_the_notification_spinner_run = false;
                    spinner_to_choose_the_notification_of_the_habit.setSelection(2);
                    show_which_days_are_chosen_add_a_habit_notifications.setVisibility(View.GONE);
                    calender_in_add_a_habit_add_a_notification.setVisibility(View.GONE);
                    constraint_layout_to_choose_every_N_days_in_notification.setVisibility(View.VISIBLE);
                    layout_saying_the_frequency_off_per_period_in_add_habit_notification.setVisibility(View.GONE);
                    enter_goal_for_new_good_habit_in_habits_add_habit_notification.setText(return_the_information_from_save(14));
                } else if (return_the_information_from_save(13).equals("dayspermonth")) {
                    should_the_notification_spinner_run = false;
                    spinner_to_choose_the_notification_of_the_habit.setSelection(3);
                    show_which_days_are_chosen_add_a_habit_notifications.setVisibility(View.GONE);
                    calender_in_add_a_habit_add_a_notification.setVisibility(View.VISIBLE);
                    constraint_layout_to_choose_every_N_days_in_notification.setVisibility(View.GONE);
                    layout_saying_the_frequency_off_per_period_in_add_habit_notification.setVisibility(View.GONE);
                    String[] days = return_the_information_from_save(14).split("_");
                    color_the_buttons_automotic_habits(days);
                }
                long time = Long.parseLong(return_the_information_from_save(15));
                long hours = TimeUnit.MILLISECONDS.toHours(time);
                long minutes = TimeUnit.MILLISECONDS.toMinutes((time%3600000L));
                String hours_string;
                if(hours == 0){
                    spinner_to_choose_am_or_pm_in_add_habit_notifactaion_freq.setSelection(0);
                    hours_string = "12";
                } else if(hours <= 12){
                    spinner_to_choose_am_or_pm_in_add_habit_notifactaion_freq.setSelection(0);
                    hours_string = String.valueOf(hours);
                } else {
                    spinner_to_choose_am_or_pm_in_add_habit_notifactaion_freq.setSelection(1);
                    hours_string = String.valueOf(hours-12);
                }
                text_view_saying_the_hour_of_notification.setText(hours_string);
                text_view_saying_the_minute_of_notification.setText(String.valueOf(minutes));
            } else if (return_the_information_from_save(12).equals("false")) {
                switch_to_turn_notifications_add_a_habit.setChecked(false);
                layout_notification_in_add_a_habit.setVisibility(View.GONE);
            }
        }
    }

    private void color_the_buttons_automotic(String[] days) {
        if (getView() != null) {
            Button calender_button_showing_shadow_1 = getView().findViewById(R.id.calender_button_showing_shadow_1);
            Button calender_button_showing_shadow_2 = getView().findViewById(R.id.calender_button_showing_shadow_2);
            Button calender_button_showing_shadow_3 = getView().findViewById(R.id.calender_button_showing_shadow_3);
            Button calender_button_showing_shadow_4 = getView().findViewById(R.id.calender_button_showing_shadow_4);
            Button calender_button_showing_shadow_5 = getView().findViewById(R.id.calender_button_showing_shadow_5);
            Button calender_button_showing_shadow_6 = getView().findViewById(R.id.calender_button_showing_shadow_6);
            Button calender_button_showing_shadow_7 = getView().findViewById(R.id.calender_button_showing_shadow_7);
            Button calender_button_showing_shadow_8 = getView().findViewById(R.id.calender_button_showing_shadow_8);
            Button calender_button_showing_shadow_9 = getView().findViewById(R.id.calender_button_showing_shadow_9);
            Button calender_button_showing_shadow_10 = getView().findViewById(R.id.calender_button_showing_shadow_10);
            Button calender_button_showing_shadow_11 = getView().findViewById(R.id.calender_button_showing_shadow_11);
            Button calender_button_showing_shadow_12 = getView().findViewById(R.id.calender_button_showing_shadow_12);
            Button calender_button_showing_shadow_13 = getView().findViewById(R.id.calender_button_showing_shadow_13);
            Button calender_button_showing_shadow_14 = getView().findViewById(R.id.calender_button_showing_shadow_14);
            Button calender_button_showing_shadow_15 = getView().findViewById(R.id.calender_button_showing_shadow_15);
            Button calender_button_showing_shadow_16 = getView().findViewById(R.id.calender_button_showing_shadow_16);
            Button calender_button_showing_shadow_17 = getView().findViewById(R.id.calender_button_showing_shadow_17);
            Button calender_button_showing_shadow_18 = getView().findViewById(R.id.calender_button_showing_shadow_18);
            Button calender_button_showing_shadow_19 = getView().findViewById(R.id.calender_button_showing_shadow_19);
            Button calender_button_showing_shadow_20 = getView().findViewById(R.id.calender_button_showing_shadow_20);
            Button calender_button_showing_shadow_21 = getView().findViewById(R.id.calender_button_showing_shadow_21);
            Button calender_button_showing_shadow_22 = getView().findViewById(R.id.calender_button_showing_shadow_22);
            Button calender_button_showing_shadow_23 = getView().findViewById(R.id.calender_button_showing_shadow_23);
            Button calender_button_showing_shadow_24 = getView().findViewById(R.id.calender_button_showing_shadow_24);
            Button calender_button_showing_shadow_25 = getView().findViewById(R.id.calender_button_showing_shadow_25);
            Button calender_button_showing_shadow_26 = getView().findViewById(R.id.calender_button_showing_shadow_26);
            Button calender_button_showing_shadow_27 = getView().findViewById(R.id.calender_button_showing_shadow_27);
            Button calender_button_showing_shadow_28 = getView().findViewById(R.id.calender_button_showing_shadow_28);
            for (int i = 0; i < days.length; i++) {
                if (days[i].equals("1")) {
                    color_the_background(calender_button_showing_shadow_1);
                } else if (days[i].equals("2")) {
                    color_the_background(calender_button_showing_shadow_2);
                } else if (days[i].equals("3")) {
                    color_the_background(calender_button_showing_shadow_3);
                } else if (days[i].equals("4")) {
                    color_the_background(calender_button_showing_shadow_4);
                } else if (days[i].equals("5")) {
                    color_the_background(calender_button_showing_shadow_5);
                } else if (days[i].equals("6")) {
                    color_the_background(calender_button_showing_shadow_6);
                } else if (days[i].equals("7")) {
                    color_the_background(calender_button_showing_shadow_7);
                } else if (days[i].equals("8")) {
                    color_the_background(calender_button_showing_shadow_8);
                } else if (days[i].equals("9")) {
                    color_the_background(calender_button_showing_shadow_9);
                } else if (days[i].equals("10")) {
                    color_the_background(calender_button_showing_shadow_10);
                } else if (days[i].equals("11")) {
                    color_the_background(calender_button_showing_shadow_11);
                } else if (days[i].equals("12")) {
                    color_the_background(calender_button_showing_shadow_12);
                } else if (days[i].equals("13")) {
                    color_the_background(calender_button_showing_shadow_13);
                } else if (days[i].equals("14")) {
                    color_the_background(calender_button_showing_shadow_14);
                } else if (days[i].equals("15")) {
                    color_the_background(calender_button_showing_shadow_15);
                } else if (days[i].equals("16")) {
                    color_the_background(calender_button_showing_shadow_16);
                } else if (days[i].equals("17")) {
                    color_the_background(calender_button_showing_shadow_17);
                } else if (days[i].equals("18")) {
                    color_the_background(calender_button_showing_shadow_18);
                } else if (days[i].equals("19")) {
                    color_the_background(calender_button_showing_shadow_19);
                } else if (days[i].equals("20")) {
                    color_the_background(calender_button_showing_shadow_20);
                } else if (days[i].equals("21")) {
                    color_the_background(calender_button_showing_shadow_21);
                } else if (days[i].equals("22")) {
                    color_the_background(calender_button_showing_shadow_22);
                } else if (days[i].equals("23")) {
                    color_the_background(calender_button_showing_shadow_23);
                } else if (days[i].equals("24")) {
                    color_the_background(calender_button_showing_shadow_24);
                } else if (days[i].equals("25")) {
                    color_the_background(calender_button_showing_shadow_25);
                } else if (days[i].equals("26")) {
                    color_the_background(calender_button_showing_shadow_26);
                } else if (days[i].equals("27")) {
                    color_the_background(calender_button_showing_shadow_27);
                } else if (days[i].equals("28")) {
                    color_the_background(calender_button_showing_shadow_28);
                }
            }
        }
    }

    private void color_the_buttons_automotic_habits(String[] days) {
        if (getView() != null) {
            Button calender_button_showing_shadow_1_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_1_add_a_notification);
            Button calender_button_showing_shadow_2_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_2_add_a_notification);
            Button calender_button_showing_shadow_3_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_3_add_a_notification);
            Button calender_button_showing_shadow_4_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_4_add_a_notification);
            Button calender_button_showing_shadow_5_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_5_add_a_notification);
            Button calender_button_showing_shadow_6_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_6_add_a_notification);
            Button calender_button_showing_shadow_7_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_7_add_a_notification);
            Button calender_button_showing_shadow_8_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_8_add_a_notification);
            Button calender_button_showing_shadow_9_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_9_add_a_notification);
            Button calender_button_showing_shadow_10_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_10_add_a_notification);
            Button calender_button_showing_shadow_11_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_11_add_a_notification);
            Button calender_button_showing_shadow_12_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_12_add_a_notification);
            Button calender_button_showing_shadow_13_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_13_add_a_notification);
            Button calender_button_showing_shadow_14_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_14_add_a_notification);
            Button calender_button_showing_shadow_15_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_15_add_a_notification);
            Button calender_button_showing_shadow_16_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_16_add_a_notification);
            Button calender_button_showing_shadow_17_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_17_add_a_notification);
            Button calender_button_showing_shadow_18_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_18_add_a_notification);
            Button calender_button_showing_shadow_19_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_19_add_a_notification);
            Button calender_button_showing_shadow_20_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_20_add_a_notification);
            Button calender_button_showing_shadow_21_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_21_add_a_notification);
            Button calender_button_showing_shadow_22_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_22_add_a_notification);
            Button calender_button_showing_shadow_23_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_23_add_a_notification);
            Button calender_button_showing_shadow_24_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_24_add_a_notification);
            Button calender_button_showing_shadow_25_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_25_add_a_notification);
            Button calender_button_showing_shadow_26_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_26_add_a_notification);
            Button calender_button_showing_shadow_27_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_27_add_a_notification);
            Button calender_button_showing_shadow_28_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_28_add_a_notification);
            for (int i = 0; i < days.length; i++) {
                if (days[i].equals("1")) {
                    color_the_background(calender_button_showing_shadow_1_add_a_notification);
                } else if (days[i].equals("2")) {
                    color_the_background(calender_button_showing_shadow_2_add_a_notification);
                } else if (days[i].equals("3")) {
                    color_the_background(calender_button_showing_shadow_3_add_a_notification);
                } else if (days[i].equals("4")) {
                    color_the_background(calender_button_showing_shadow_4_add_a_notification);
                } else if (days[i].equals("5")) {
                    color_the_background(calender_button_showing_shadow_5_add_a_notification);
                } else if (days[i].equals("6")) {
                    color_the_background(calender_button_showing_shadow_6_add_a_notification);
                } else if (days[i].equals("7")) {
                    color_the_background(calender_button_showing_shadow_7_add_a_notification);
                } else if (days[i].equals("8")) {
                    color_the_background(calender_button_showing_shadow_8_add_a_notification);
                } else if (days[i].equals("9")) {
                    color_the_background(calender_button_showing_shadow_9_add_a_notification);
                } else if (days[i].equals("10")) {
                    color_the_background(calender_button_showing_shadow_10_add_a_notification);
                } else if (days[i].equals("11")) {
                    color_the_background(calender_button_showing_shadow_11_add_a_notification);
                } else if (days[i].equals("12")) {
                    color_the_background(calender_button_showing_shadow_12_add_a_notification);
                } else if (days[i].equals("13")) {
                    color_the_background(calender_button_showing_shadow_13_add_a_notification);
                } else if (days[i].equals("14")) {
                    color_the_background(calender_button_showing_shadow_14_add_a_notification);
                } else if (days[i].equals("15")) {
                    color_the_background(calender_button_showing_shadow_15_add_a_notification);
                } else if (days[i].equals("16")) {
                    color_the_background(calender_button_showing_shadow_16_add_a_notification);
                } else if (days[i].equals("17")) {
                    color_the_background(calender_button_showing_shadow_17_add_a_notification);
                } else if (days[i].equals("18")) {
                    color_the_background(calender_button_showing_shadow_18_add_a_notification);
                } else if (days[i].equals("19")) {
                    color_the_background(calender_button_showing_shadow_19_add_a_notification);
                } else if (days[i].equals("20")) {
                    color_the_background(calender_button_showing_shadow_20_add_a_notification);
                } else if (days[i].equals("21")) {
                    color_the_background(calender_button_showing_shadow_21_add_a_notification);
                } else if (days[i].equals("22")) {
                    color_the_background(calender_button_showing_shadow_22_add_a_notification);
                } else if (days[i].equals("23")) {
                    color_the_background(calender_button_showing_shadow_23_add_a_notification);
                } else if (days[i].equals("24")) {
                    color_the_background(calender_button_showing_shadow_24_add_a_notification);
                } else if (days[i].equals("25")) {
                    color_the_background(calender_button_showing_shadow_25_add_a_notification);
                } else if (days[i].equals("26")) {
                    color_the_background(calender_button_showing_shadow_26_add_a_notification);
                } else if (days[i].equals("27")) {
                    color_the_background(calender_button_showing_shadow_27_add_a_notification);
                } else if (days[i].equals("28")) {
                    color_the_background(calender_button_showing_shadow_28_add_a_notification);
                }
            }
        }
    }

    private void set_the_icon_habit() {
        set_the_icon(return_the_icon(return_the_information_from_save(11)));
    }

    private String return_the_information_from_save(int which) {
        Room_database_habits room_database_habits = Room_database_habits.getInstance(getContext());
        List<habits_data_class> list = room_database_habits.dao_for_habits_data().getAll();
        habits_data_class habits_data_class = list.get(position);
        if (which == 1) {
            return habits_data_class.getName_of_the_habit();
        }
        if (which == 2) {
            return String.valueOf(habits_data_class.getLast_relapse());
        }
        if (which == 3) {
            return String.valueOf(habits_data_class.getGoal());
        }
        if (which == 4) {
            return habits_data_class.getColor();
        }
        if (which == 5) {
            return String.valueOf(habits_data_class.getId());
        }
        if (which == 6) {
            return String.valueOf(habits_data_class.getBad_or_good_habit());
        }
        if (which == 7) {
            return String.valueOf(habits_data_class.getType_of_the_habit());
        }
        if (which == 8) {
            return String.valueOf(habits_data_class.getHabits_freq());
        }
        if (which == 9) {
            return String.valueOf(habits_data_class.getExtra_type_info());
        }
        if (which == 10) {
            return habits_data_class.getHabits_freq_extra();
        }
        if (which == 11) {
            return habits_data_class.getIcon();
        }
        if (which == 12) {
            return String.valueOf(habits_data_class.isNotifications_on_or_off());
        }
        if (which == 13) {
            return habits_data_class.getNotifications_freq();
        }
        if(which == 14){
            return habits_data_class.getNotifications_freq_extra();
        }
        if(which == 15){
            return String.valueOf(habits_data_class.getNotifications_time());
        }
        return ""; // cant be otherwise
    }

    private int return_the_icon(String name) {
        Resources resources = getContext().getResources();
        return resources.getIdentifier(name, "drawable",
                getContext().getPackageName());
    }

    private void choose_date_button_listen() {
        if (getView() != null) {
            Button button_that_will_open_time_user_hacked = getView().findViewById(R.id.button_that_will_open_time_user_hacked);
            button_that_will_open_time_user_hacked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null && getView() != null) {
                        Universal_date_picker universal_date_picker = new Universal_date_picker();
                        universal_date_picker.setTargetFragment(edit_bad_habits.this, 22);
                        universal_date_picker.show(getActivity().getSupportFragmentManager(), "date_picker");
                        hide_keyboard();

                    }
                }
            });
        }
    }

    private int dip_to_pixels(float dip) {
        Resources r = getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
    }

    private void goal_days_text_watcher() {
        if (getView() != null) {
            final EditText enter_goal_for_new_good_habit_in_habits = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits);
            final TextView text_saying_days_beside_habit_edit_text = getView().findViewById(R.id.text_saying_days_beside_habit_edit_text);
            enter_goal_for_new_good_habit_in_habits.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (enter_goal_for_new_good_habit_in_habits.hasFocus()) {
                        if (!charSequence.toString().equals("")) {
                            if (Integer.parseInt(charSequence.toString()) == 1) {
                                text_saying_days_beside_habit_edit_text.setText(" day");
                            } else {
                                text_saying_days_beside_habit_edit_text.setText(" days");
                            }
                        } else {
                            text_saying_days_beside_habit_edit_text.setText(" days");
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    private void change_color_listen() {
        if (getView() != null) {
            Button button_to_choose_color_of_new_good_habit = getView().findViewById(R.id.button_to_choose_color_of_new_good_habit);
            button_to_choose_color_of_new_good_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bottom_sheet_choose_good_habit_color bottomSheetDialog = new Bottom_sheet_choose_good_habit_color();
                    bottomSheetDialog.setTargetFragment(edit_bad_habits.this, 1000);
                    bottomSheetDialog.show(getActivity().getSupportFragmentManager(), "tag");
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1000:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        String text = bundle.getString("color", "#607D8B");
                        if (getView() != null) {
                            View view_showing_the_icon_and_its_color = getView().findViewById(R.id.view_showing_the_icon_and_its_color);
                            TextView text_view_asying_this_is_example_of_icon = getView().findViewById(R.id.text_view_asying_this_is_example_of_icon);
                            color = text;
                            view_showing_the_icon_and_its_color.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(text)));
                            text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor(text));
                        }
                    }
                }
                break;
            case 22:
                if (getView() != null && getContext() != null) {
                    Button button_that_will_open_time_user_hacked = getView().findViewById(R.id.button_that_will_open_time_user_hacked);
                    TextView text_to_sayy_enter_time_of_last_relapse = getView().findViewById(R.id.text_to_sayy_enter_time_of_last_relapse);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, data.getIntExtra("year", 2021));
                    calendar.set(Calendar.MONTH, data.getIntExtra("month", 1));
                    calendar.set(Calendar.DAY_OF_MONTH, data.getIntExtra("day", 20));
                    Date date = new Date();
                    date.setTime(calendar.getTimeInMillis());
                    String date_in_string = DateFormat.getDateInstance(DateFormat.MEDIUM).format(date);
                    time_in_milli = calendar.getTimeInMillis();
                    button_that_will_open_time_user_hacked.setText("Change Start Date");
                    text_to_sayy_enter_time_of_last_relapse.setText("Start Date: ".concat(date_in_string));
                    Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                    text_to_sayy_enter_time_of_last_relapse.setTypeface(boldTypeface);
                    text_to_sayy_enter_time_of_last_relapse.setTextSize(16);
                }
                break;
        }
    }

    private void pick_icon_button_lsitener() {
        if (getView() != null) {
            Button button_to_choose_icon_of_the_new_good_habit = getView().findViewById(R.id.button_to_choose_icon_of_the_new_good_habit);
            button_to_choose_icon_of_the_new_good_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getView() != null) {
                        Dialog_to_choose_good_habit dialog_to_choose_good_habit = new Dialog_to_choose_good_habit();
                        dialog_to_choose_good_habit.setTargetFragment(edit_bad_habits.this, 100);
                        dialog_to_choose_good_habit.set_icon_listen(new Dialog_to_choose_good_habit.the_icon_clicked() {
                            @Override
                            public void share_just_got_clciked(int text) {
                                set_the_icon(text);
                            }
                        });
                        dialog_to_choose_good_habit.show(getActivity().getSupportFragmentManager(), "bad_habits_main");
                    }
                }
            });
        }
    }

    private void done_button_listen() {
        if (getView() != null && getActivity() != null) {
            Button button_to_submit_good_habit_in_habits = getView().findViewById(R.id.button_to_submit_good_habit_in_habits);
            Button Bad_habit_button_in_add_a_habit = getView().findViewById(R.id.Bad_habit_button_in_add_a_habit);
            Button good_habit_button_in_add_a_habit = getView().findViewById(R.id.good_habit_button_in_add_a_habit);
            final add_a_habit add_a_habit = (add_a_habit) getActivity().getSupportFragmentManager().findFragmentByTag("add a habit");
            final home_fragment home_fragment = (com.easyhabitsapp.android.home_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("home");
            button_to_submit_good_habit_in_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (check_if_done()) {
                        if (Bad_habit_button_in_add_a_habit.getText().toString().contains("✓")) {
                            save_the_data_bad();
                        } else {
                            save_the_data_good();
                        }
                        if (add_a_habit != null) {
                            if (getActivity() != null && home_fragment != null) {
                                getActivity().getSupportFragmentManager().beginTransaction().show(home_fragment).remove(add_a_habit).commit();
                            }
                        }
                    }
                }
            });
        }
    }

    private boolean check_if_done() {
        EditText edit_text_showing_habit_name = getView().findViewById(R.id.edit_text_showing_habit_name);
        EditText enter_goal_for_new_good_habit_in_habits = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits);
        Button Bad_habit_button_in_add_a_habit = getView().findViewById(R.id.Bad_habit_button_in_add_a_habit);
        Button good_habit_button_in_add_a_habit = getView().findViewById(R.id.good_habit_button_in_add_a_habit);
        EditText edit_text_to_enter_amount_in_add_habit = getView().findViewById(R.id.edit_text_to_enter_amount_in_add_habit);
        Button amount_add_habit = getView().findViewById(R.id.amount_add_habit);
        Button timer_add_habit = getView().findViewById(R.id.timer_add_habit);
        EditText edit_text_to_enter_time_in_add_habit = getView().findViewById(R.id.edit_text_to_enter_time_in_add_habit);
        Custom_spinner spinner_to_choose_the_frequency_of_the_habit = getView().findViewById(R.id.spinner_to_choose_the_frequency_of_the_habit);
        EditText enter_goal_for_new_good_habit_in_habits_add_habit = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits_add_habit);
        EditText times_per_period_in_add_habit_frequency = getView().findViewById(R.id.times_per_period_in_add_habit_frequency);
        EditText times_per_period_in_add_habit_frequency_in_the_end = getView().findViewById(R.id.times_per_period_in_add_habit_frequency_in_the_end);
        Custom_spinner spinner_to_choose_the_notification_of_the_habit = getView().findViewById(R.id.spinner_to_choose_the_notification_of_the_habit);
        EditText enter_goal_for_new_good_habit_in_habits_add_habit_notification = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits_add_habit_notification);
        EditText times_per_period_in_add_habit_frequency_notification = getView().findViewById(R.id.times_per_period_in_add_habit_frequency_notification);
        EditText times_per_period_in_add_habit_frequency_in_the_end_notification = getView().findViewById(R.id.times_per_period_in_add_habit_frequency_in_the_end_notification);
        if (!did_i_choose_good_or_bad()) {
            Toast.makeText(getActivity(), "Please choose whether this is a good or bad habit", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Bad_habit_button_in_add_a_habit.getText().toString().contains("✓")) {
            if (edit_text_showing_habit_name.getText().toString().trim().equals("")) {
                Toast.makeText(getActivity(), "Please choose type a habit name", Toast.LENGTH_SHORT).show();
                edit_text_showing_habit_name.setError("Please choose type a habit name");
                return false;
            }
            if (time_in_milli == 0) {
                Toast.makeText(getActivity(), "Please choose the date that you last did the habit", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (enter_goal_for_new_good_habit_in_habits.getText().toString().equals("") || Integer.parseInt(enter_goal_for_new_good_habit_in_habits.getText().toString()) == 0) {
                if (enter_goal_for_new_good_habit_in_habits.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Goal can't be empty", Toast.LENGTH_SHORT).show();
                    enter_goal_for_new_good_habit_in_habits.setError("Goal can't be empty");
                } else {
                    Toast.makeText(getActivity(), "Goal can't be zero", Toast.LENGTH_SHORT).show();
                    enter_goal_for_new_good_habit_in_habits.setError("Goal can't be zero");
                }
                return false;
            }
            /*if (color.equals("")) {
                Toast.makeText(getActivity(), "Please choose a color", Toast.LENGTH_SHORT).show();
                return false;
            }*/
            if (icon.equals("")) {
                Toast.makeText(getActivity(), "Please choose an icon", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 2 && enter_goal_for_new_good_habit_in_habits_add_habit_notification.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Field can't be zero or left empty", Toast.LENGTH_SHORT).show();
                enter_goal_for_new_good_habit_in_habits_add_habit_notification.setError("Field can't be zero or left empty");
                return false;
            }
            if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 4 && times_per_period_in_add_habit_frequency_notification.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Field can't be zero or left empty", Toast.LENGTH_SHORT).show();
                times_per_period_in_add_habit_frequency_notification.setError("Field can't be zero or left empty");
                return false;
            }
            if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 4 && times_per_period_in_add_habit_frequency_in_the_end_notification.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Field can't be zero or left empty", Toast.LENGTH_SHORT).show();
                times_per_period_in_add_habit_frequency_in_the_end_notification.setError("Field can't be zero or left empty");
                return false;
            }

            if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 2 && Integer.parseInt(enter_goal_for_new_good_habit_in_habits_add_habit_notification.getText().toString()) == 0) {
                Toast.makeText(getActivity(), "Field can't be zero or left empty", Toast.LENGTH_SHORT).show();
                enter_goal_for_new_good_habit_in_habits_add_habit_notification.setError("Field can't be zero or left empty");
                return false;
            }

            if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 4 && Integer.parseInt(times_per_period_in_add_habit_frequency_notification.getText().toString()) == 0) {
                Toast.makeText(getActivity(), "Field can't be zero or left empty", Toast.LENGTH_SHORT).show();
                times_per_period_in_add_habit_frequency_notification.setError("Field can't be zero or left empty");
                return false;
            }

            if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 4 && Integer.parseInt(times_per_period_in_add_habit_frequency_in_the_end_notification.getText().toString()) == 0) {
                Toast.makeText(getActivity(), "Field can't be zero or left empty", Toast.LENGTH_SHORT).show();
                times_per_period_in_add_habit_frequency_in_the_end_notification.setError("Field can't be zero or left empty");
                return false;
            }

            if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 3 && check_which_button_is_chosen_notification().equals("")) {
                Toast.makeText(getActivity(), "please select days of the month", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            if (!did_i_choose_typr_of_habit()) {
                Toast.makeText(getActivity(), "Please choose the type of habit", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (amount_add_habit.getText().toString().contains("✓") && edit_text_to_enter_amount_in_add_habit.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Please type an amount", Toast.LENGTH_SHORT).show();
                edit_text_to_enter_amount_in_add_habit.setError("Please type an amount");
                return false;
            }
            if (timer_add_habit.getText().toString().contains("✓") && edit_text_to_enter_time_in_add_habit.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Please enter the time", Toast.LENGTH_SHORT).show();
                edit_text_to_enter_time_in_add_habit.setError("Please enter the time");
                return false;
            }
            if (edit_text_showing_habit_name.getText().toString().trim().equals("")) {
                Toast.makeText(getActivity(), "Please choose type a habit name", Toast.LENGTH_SHORT).show();
                edit_text_showing_habit_name.setError("Please choose type a habit name");
                return false;
            }
            if (time_in_milli == 0) {
                Toast.makeText(getActivity(), "Please choose the date that you last did the habit", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (enter_goal_for_new_good_habit_in_habits.getText().toString().equals("") || Integer.parseInt(enter_goal_for_new_good_habit_in_habits.getText().toString()) == 0) {
                if (enter_goal_for_new_good_habit_in_habits.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Goal can't be empty", Toast.LENGTH_SHORT).show();
                    enter_goal_for_new_good_habit_in_habits.setError("Goal can't be empty");
                } else {
                    Toast.makeText(getActivity(), "Goal can't be zero", Toast.LENGTH_SHORT).show();
                    enter_goal_for_new_good_habit_in_habits.setError("Goal can't be zero");
                }
                return false;
            }
            if (color.equals("")) {
                Toast.makeText(getActivity(), "Please choose a color", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (icon.equals("")) {
                Toast.makeText(getActivity(), "Please choose an icon", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (spinner_to_choose_the_frequency_of_the_habit.getSelectedItemPosition() == 2 && enter_goal_for_new_good_habit_in_habits_add_habit.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Field can't be zero or left empty", Toast.LENGTH_SHORT).show();
                enter_goal_for_new_good_habit_in_habits_add_habit.setError("days can't be zero or left empty");
                return false;
            }
            if (spinner_to_choose_the_frequency_of_the_habit.getSelectedItemPosition() == 4 && times_per_period_in_add_habit_frequency.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Field can't be zero or left empty", Toast.LENGTH_SHORT).show();
                times_per_period_in_add_habit_frequency.setError("Field can't be zero or left empty");
                return false;
            }
            if (spinner_to_choose_the_frequency_of_the_habit.getSelectedItemPosition() == 4 && times_per_period_in_add_habit_frequency_in_the_end.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Field can't be zero or left empty", Toast.LENGTH_SHORT).show();
                times_per_period_in_add_habit_frequency_in_the_end.setError("Field can't be zero or left empty");
                return false;
            }

            if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 2 && enter_goal_for_new_good_habit_in_habits_add_habit_notification.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Field can't be zero or left empty", Toast.LENGTH_SHORT).show();
                enter_goal_for_new_good_habit_in_habits_add_habit_notification.setError("Field can't be zero or left empty");
                return false;
            }
            if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 4 && times_per_period_in_add_habit_frequency_notification.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Field can't be zero or left empty", Toast.LENGTH_SHORT).show();
                times_per_period_in_add_habit_frequency_notification.setError("Field can't be zero or left empty");
                return false;
            }
            if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 4 && times_per_period_in_add_habit_frequency_in_the_end_notification.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Field can't be zero or left empty", Toast.LENGTH_SHORT).show();
                times_per_period_in_add_habit_frequency_in_the_end_notification.setError("Field can't be zero or left empty");
                return false;
            }
            if (spinner_to_choose_the_frequency_of_the_habit.getSelectedItemPosition() == 2 && Integer.parseInt(enter_goal_for_new_good_habit_in_habits_add_habit.getText().toString()) == 0) {
                Toast.makeText(getActivity(), "Field can't be zero or left empty", Toast.LENGTH_SHORT).show();
                enter_goal_for_new_good_habit_in_habits_add_habit.setError("days can't be zero or left empty");
                return false;
            }
            if (spinner_to_choose_the_frequency_of_the_habit.getSelectedItemPosition() == 4 && Integer.parseInt(times_per_period_in_add_habit_frequency.getText().toString()) == 0) {
                Toast.makeText(getActivity(), "Field can't be zero or left empty", Toast.LENGTH_SHORT).show();
                times_per_period_in_add_habit_frequency.setError("Field can't be zero or left empty");
                return false;
            }
            if (spinner_to_choose_the_frequency_of_the_habit.getSelectedItemPosition() == 4 && Integer.parseInt(times_per_period_in_add_habit_frequency_in_the_end.getText().toString()) == 0) {
                Toast.makeText(getActivity(), "Field can't be zero or left empty", Toast.LENGTH_SHORT).show();
                times_per_period_in_add_habit_frequency_in_the_end.setError("Field can't be zero or left empty");
                return false;
            }
            if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 2 && Integer.parseInt(enter_goal_for_new_good_habit_in_habits_add_habit_notification.getText().toString()) == 0) {
                Toast.makeText(getActivity(), "Field can't be zero or left empty", Toast.LENGTH_SHORT).show();
                enter_goal_for_new_good_habit_in_habits_add_habit_notification.setError("Field can't be zero or left empty");
                return false;
            }
            if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 4 && Integer.parseInt(times_per_period_in_add_habit_frequency_notification.getText().toString()) == 0) {
                Toast.makeText(getActivity(), "Field can't be zero or left empty", Toast.LENGTH_SHORT).show();
                times_per_period_in_add_habit_frequency_notification.setError("Field can't be zero or left empty");
                return false;
            }
            if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 4 && Integer.parseInt(times_per_period_in_add_habit_frequency_in_the_end_notification.getText().toString()) == 0) {
                Toast.makeText(getActivity(), "Field can't be zero or left empty", Toast.LENGTH_SHORT).show();
                times_per_period_in_add_habit_frequency_in_the_end_notification.setError("Field can't be zero or left empty");
                return false;
            }
            if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 3 && check_which_button_is_chosen_notification().equals("")) {
                Toast.makeText(getActivity(), "please select days of the month", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (spinner_to_choose_the_frequency_of_the_habit.getSelectedItemPosition() == 3 && check_which_button_is_chosen_habit().equals("")) {
                Toast.makeText(getActivity(), "please select days of the month", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private void back_button_listen() {
        if (getView() != null && getActivity() != null) {
            Button button_to_go_back_good_habit = getView().findViewById(R.id.button_to_go_back_good_habit);
            final edit_bad_habits edit_bad_habits = (edit_bad_habits) getActivity().getSupportFragmentManager().findFragmentByTag("edit bad habit");
            View_home_habit view_home_habit = (View_home_habit) getActivity().getSupportFragmentManager().findFragmentByTag("view home");
            button_to_go_back_good_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (edit_bad_habits != null) {
                        if (getActivity() != null && view_home_habit != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().show(view_home_habit).remove(edit_bad_habits).commit();
                        }
                    }
                }
            });
        }
    }


    private void bad_and_god_habit_listen() {
        if (getView() != null) {
            final Button Bad_habit_button_in_add_a_habit = getView().findViewById(R.id.Bad_habit_button_in_add_a_habit);
            final Button good_habit_button_in_add_a_habit = getView().findViewById(R.id.good_habit_button_in_add_a_habit);
            final ConstraintLayout layout_inside_add_habit_to_determine_good_nad_bad_habit = getView().findViewById(R.id.layout_inside_add_habit_to_determine_good_nad_bad_habit);
            final ConstraintLayout layout_listing_the_type_of_habits_good_habit_add_a_habit = getView().findViewById(R.id.layout_listing_the_type_of_habits_good_habit_add_a_habit);
            final ConstraintLayout layout_mentioning_the_spinner_and_frequency = getView().findViewById(R.id.layout_mentioning_the_spinner_and_frequency);
            final View background_of_choose_frequency_add_a_habit = getView().findViewById(R.id.background_of_choose_frequency_add_a_habit);
            final ConstraintLayout layout_mentioning_the_spinner_and_notification = getView().findViewById(R.id.layout_mentioning_the_spinner_and_notification);
            final View background_of_choose_notification_add_a_habit = getView().findViewById(R.id.background_of_choose_notification_add_a_habit);
            Bad_habit_button_in_add_a_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    set_clicked_button(Bad_habit_button_in_add_a_habit);
                    set_not_clicked_button(good_habit_button_in_add_a_habit);
                    layout_inside_add_habit_to_determine_good_nad_bad_habit.setVisibility(View.VISIBLE);
                    layout_listing_the_type_of_habits_good_habit_add_a_habit.setVisibility(View.GONE);
                    layout_mentioning_the_spinner_and_frequency.setVisibility(View.GONE);
                    background_of_choose_frequency_add_a_habit.setVisibility(View.GONE);
                    /*layout_mentioning_the_spinner_and_notification.setVisibility(View.GONE);
                    background_of_choose_notification_add_a_habit.setVisibility(View.GONE);*/
                }
            });
            good_habit_button_in_add_a_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    set_clicked_button(good_habit_button_in_add_a_habit);
                    set_not_clicked_button(Bad_habit_button_in_add_a_habit);
                    layout_inside_add_habit_to_determine_good_nad_bad_habit.setVisibility(View.VISIBLE);
                    layout_listing_the_type_of_habits_good_habit_add_a_habit.setVisibility(View.VISIBLE);
                    layout_mentioning_the_spinner_and_frequency.setVisibility(View.VISIBLE);
                    background_of_choose_frequency_add_a_habit.setVisibility(View.VISIBLE);
                    /*layout_mentioning_the_spinner_and_notification.setVisibility(View.VISIBLE);
                    background_of_choose_notification_add_a_habit.setVisibility(View.VISIBLE);*/
                }
            });
        }
    }

    private void typeof_the_habit_yes_no() {
        if (getView() != null) {
            final Button yes_or_no_add_habit = getView().findViewById(R.id.yes_or_no_add_habit);
            final Button amount_add_habit = getView().findViewById(R.id.amount_add_habit);
            final Button timer_add_habit = getView().findViewById(R.id.timer_add_habit);
            final ConstraintLayout layout_saying_the_amount_edit_text = getView().findViewById(R.id.layout_saying_the_amount_edit_text);
            final ConstraintLayout layout_saying_the_time_edit_text = getView().findViewById(R.id.layout_saying_the_time_edit_text);
            final EditText edit_text_showing_habit_name = getView().findViewById(R.id.edit_text_showing_habit_name);
            final ConstraintLayout layout_listing_the_type_of_habits_good_habit_add_a_habit = getView().findViewById(R.id.layout_listing_the_type_of_habits_good_habit_add_a_habit);
            final View bottom_of_yes_no_habit = getView().findViewById(R.id.bottom_of_yes_no_habit);
            yes_or_no_add_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    set_clicked_button(yes_or_no_add_habit);
                    set_not_clicked_button(amount_add_habit);
                    set_not_clicked_button(timer_add_habit);
                    layout_saying_the_amount_edit_text.setVisibility(View.GONE);
                    layout_saying_the_time_edit_text.setVisibility(View.GONE);
                }
            });
            amount_add_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    set_not_clicked_button(yes_or_no_add_habit);
                    set_clicked_button(amount_add_habit);
                    set_not_clicked_button(timer_add_habit);
                    layout_saying_the_amount_edit_text.setVisibility(View.VISIBLE);
                    layout_saying_the_time_edit_text.setVisibility(View.GONE);
                    if (getView() != null && getContext() != null) {
                        ConstraintLayout constraintLayout = getView().findViewById(R.id.layout_listing_the_type_of_habits_good_habit_add_a_habit);
                        ConstraintSet constraintSet = new ConstraintSet();
                        constraintSet.clone(constraintLayout);
                        constraintSet.connect(bottom_of_yes_no_habit.getId(), ConstraintSet.TOP, layout_saying_the_amount_edit_text.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(8, getContext()));
                        constraintSet.applyTo(constraintLayout);
                    }
                }
            });
            timer_add_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    set_not_clicked_button(yes_or_no_add_habit);
                    set_not_clicked_button(amount_add_habit);
                    set_clicked_button(timer_add_habit);
                    layout_saying_the_amount_edit_text.setVisibility(View.GONE);
                    layout_saying_the_time_edit_text.setVisibility(View.VISIBLE);
                    if (getView() != null) {
                        ConstraintLayout constraintLayout = getView().findViewById(R.id.layout_listing_the_type_of_habits_good_habit_add_a_habit);
                        ConstraintSet constraintSet = new ConstraintSet();
                        constraintSet.clone(constraintLayout);
                        constraintSet.connect(bottom_of_yes_no_habit.getId(), ConstraintSet.TOP, layout_saying_the_time_edit_text.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(8, getContext()));
                        constraintSet.applyTo(constraintLayout);
                    }
                }
            });
        }
    }

    private void set_clicked_button(Button button) {
        if (!button.getText().toString().contains("✓")) {
            button.setText(button.getText().toString().concat(" ✓"));
            button.setTextColor(Color.WHITE);
            button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
        }
    }

    private void set_not_clicked_button(Button button) {
        button.setText(button.getText().toString().replace(" ✓", ""));
        button.setTextColor(Color.BLACK);
        button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
    }

    private boolean did_i_choose_good_or_bad() {
        final Button Bad_habit_button_in_add_a_habit = getView().findViewById(R.id.Bad_habit_button_in_add_a_habit);
        final Button good_habit_button_in_add_a_habit = getView().findViewById(R.id.good_habit_button_in_add_a_habit);
        if (Bad_habit_button_in_add_a_habit.getText().toString().contains("✓") || good_habit_button_in_add_a_habit.getText().toString().contains("✓")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean did_i_choose_typr_of_habit() {
        final Button yes_or_no_add_habit = getView().findViewById(R.id.yes_or_no_add_habit);
        final Button amount_add_habit = getView().findViewById(R.id.amount_add_habit);
        final Button timer_add_habit = getView().findViewById(R.id.timer_add_habit);
        if (yes_or_no_add_habit.getText().toString().contains("✓") || amount_add_habit.getText().toString().contains("✓") || timer_add_habit.getText().toString().contains("✓")) {
            return true;
        } else {
            return false;
        }
    }

    private void save_the_data_bad() {
        if (getView() != null) {
            EditText edit_text_showing_habit_name = getView().findViewById(R.id.edit_text_showing_habit_name);
            EditText enter_goal_for_new_good_habit_in_habits = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits);
            SwitchMaterial switch_to_turn_notifications_add_a_habit = getView().findViewById(R.id.switch_to_turn_notifications_add_a_habit);
            Custom_spinner spinner_to_choose_the_notification_of_the_habit = getView().findViewById(R.id.spinner_to_choose_the_notification_of_the_habit);
            TextView show_which_days_are_chosen_add_a_habit_notifications = getView().findViewById(R.id.show_which_days_are_chosen_add_a_habit_notifications);
            EditText enter_goal_for_new_good_habit_in_habits_add_habit_notification = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits_add_habit_notification);
            EditText times_per_period_in_add_habit_frequency_notification = getView().findViewById(R.id.times_per_period_in_add_habit_frequency_notification);
            EditText times_per_period_in_add_habit_frequency_in_the_end_notification = getView().findViewById(R.id.times_per_period_in_add_habit_frequency_in_the_end_notification);
            habits_data_class habits_data_class = new habits_data_class();

            habits_data_class.setBad_or_good_habit("bad");

            habits_data_class.setName_of_the_habit(edit_text_showing_habit_name.getText().toString());

            habits_data_class.setLast_relapse(time_in_milli);

            habits_data_class.setGoal(Integer.parseInt(enter_goal_for_new_good_habit_in_habits.getText().toString()));

            habits_data_class.setColor(color);

            habits_data_class.setIcon(icon);
            String notification_freq = "";
            if (switch_to_turn_notifications_add_a_habit.isChecked()) {
                habits_data_class.setNotifications_on_or_off(true);
                if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 0) {
                    habits_data_class.setNotifications_freq("everyday");
                    notification_freq = "everyday";
                } else if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 1) {
                    habits_data_class.setNotifications_freq("daysperweek");
                    notification_freq = "daysperweek";
                    habits_data_class.setNotifications_freq_extra(show_which_days_are_chosen_add_a_habit_notifications.getText().toString());
                } else if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 2) {
                    habits_data_class.setNotifications_freq("everyndays");
                    notification_freq = "everyndays";
                    habits_data_class.setNotifications_freq_extra(enter_goal_for_new_good_habit_in_habits_add_habit_notification.getText().toString());
                } else if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 3) {
                    habits_data_class.setNotifications_freq("dayspermonth");
                    notification_freq = "dayspermonth";
                    habits_data_class.setNotifications_freq_extra(check_which_button_is_chosen_notification());
                }/* else if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 4) {
                    habits_data_class.setNotifications_freq("timesaperiod");
                    habits_data_class.setNotifications_freq_extra(times_per_period_in_add_habit_frequency_notification.getText().toString().concat("_").concat(times_per_period_in_add_habit_frequency_in_the_end_notification.getText().toString()));
                }*/
                habits_data_class.setNotifications_time(return_time_for_notification());
            } else {
                habits_data_class.setNotifications_on_or_off(false);
            }
            ArrayList<Long> relapse = new ArrayList<>();
            habits_data_class.setRelapse(relapse);
            HashMap<Long, Integer> hasmap = new HashMap<>();
            HashMap<Long, Integer> amount_hashmap = new HashMap<>();
            habits_data_class.setRelapse_amount_timer(hasmap);
            habits_data_class.setRelapse_amount(amount_hashmap);
            Room_database_habits database_habits = Room_database_habits.getInstance(getContext());
            database_habits.dao_for_habits_data().insert(habits_data_class);
            if (switch_to_turn_notifications_add_a_habit.isChecked()) {
                set_the_notification(edit_text_showing_habit_name.getText().toString(), notification_freq, "bad", "yes/no", icon, database_habits.dao_for_habits_data().getAll().size() + 1, database_habits.dao_for_habits_data().getAll().get(database_habits.dao_for_habits_data().getAll().size() - 1).getId(), Simplify_the_time.return_time_in_midnight(time_in_milli));
            }
        }
    }

    private void save_the_data_good() {
        if (getView() != null) {
            Button yes_or_no_add_habit = getView().findViewById(R.id.yes_or_no_add_habit);
            Button amount_add_habit = getView().findViewById(R.id.amount_add_habit);
            Button timer_add_habit = getView().findViewById(R.id.timer_add_habit);
            EditText edit_text_to_enter_amount_in_add_habit = getView().findViewById(R.id.edit_text_to_enter_amount_in_add_habit);
            EditText edit_text_to_enter_time_in_add_habit = getView().findViewById(R.id.edit_text_to_enter_time_in_add_habit);
            Spinner spinner_saying_minutes_or_hours = getView().findViewById(R.id.spinner_saying_minutes_or_hours);
            EditText edit_text_showing_habit_name = getView().findViewById(R.id.edit_text_showing_habit_name);
            EditText enter_goal_for_new_good_habit_in_habits = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits);
            SwitchMaterial switch_to_turn_notifications_add_a_habit = getView().findViewById(R.id.switch_to_turn_notifications_add_a_habit);
            Custom_spinner spinner_to_choose_the_notification_of_the_habit = getView().findViewById(R.id.spinner_to_choose_the_notification_of_the_habit);
            TextView show_which_days_are_chosen_add_a_habit_notifications = getView().findViewById(R.id.show_which_days_are_chosen_add_a_habit_notifications);
            EditText enter_goal_for_new_good_habit_in_habits_add_habit_notification = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits_add_habit_notification);
            EditText times_per_period_in_add_habit_frequency_notification = getView().findViewById(R.id.times_per_period_in_add_habit_frequency_notification);
            EditText times_per_period_in_add_habit_frequency_in_the_end_notification = getView().findViewById(R.id.times_per_period_in_add_habit_frequency_in_the_end_notification);
            Custom_spinner spinner_to_choose_the_frequency_of_the_habit = getView().findViewById(R.id.spinner_to_choose_the_frequency_of_the_habit);
            TextView show_which_days_are_chosen_add_a_habit = getView().findViewById(R.id.show_which_days_are_chosen_add_a_habit);
            EditText enter_goal_for_new_good_habit_in_habits_add_habit = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits_add_habit);
            EditText times_per_period_in_add_habit_frequency = getView().findViewById(R.id.times_per_period_in_add_habit_frequency);
            EditText times_per_period_in_add_habit_frequency_in_the_end = getView().findViewById(R.id.times_per_period_in_add_habit_frequency_in_the_end);

            habits_data_class habits_data_class = new habits_data_class();
            habits_data_class.setBad_or_good_habit("good");
            String type;
            if (yes_or_no_add_habit.getText().toString().contains("✓")) {
                habits_data_class.setType_of_the_habit("yes/no");
                type = "yes/no";
            } else if (amount_add_habit.getText().toString().contains("✓")) {
                habits_data_class.setType_of_the_habit("amount");
                habits_data_class.setExtra_type_info(Integer.parseInt(edit_text_to_enter_amount_in_add_habit.getText().toString()));
                type = "amount";
            } else {
                habits_data_class.setType_of_the_habit("timer");
                type = "timer";
                if (spinner_saying_minutes_or_hours.getSelectedItemPosition() == 0) {
                    habits_data_class.setExtra_type_info(Integer.parseInt(edit_text_to_enter_time_in_add_habit.getText().toString()));
                } else {
                    habits_data_class.setExtra_type_info(Integer.parseInt(edit_text_to_enter_time_in_add_habit.getText().toString()) * 60);
                }
            }

            habits_data_class.setName_of_the_habit(edit_text_showing_habit_name.getText().toString());

            habits_data_class.setLast_relapse(time_in_milli);

            habits_data_class.setGoal(Integer.parseInt(enter_goal_for_new_good_habit_in_habits.getText().toString()));

            habits_data_class.setColor(color);

            habits_data_class.setIcon(icon);

            if (spinner_to_choose_the_frequency_of_the_habit.getSelectedItemPosition() == 0) {
                habits_data_class.setHabits_freq("everyday");
            } else if (spinner_to_choose_the_frequency_of_the_habit.getSelectedItemPosition() == 1) {
                habits_data_class.setHabits_freq("daysperweek");
                habits_data_class.setHabits_freq_extra(which_days_are_chosen);
            } else if (spinner_to_choose_the_frequency_of_the_habit.getSelectedItemPosition() == 2) {
                habits_data_class.setHabits_freq("everyndays");
                habits_data_class.setHabits_freq_extra(enter_goal_for_new_good_habit_in_habits_add_habit.getText().toString());
            } else if (spinner_to_choose_the_frequency_of_the_habit.getSelectedItemPosition() == 3) {
                habits_data_class.setHabits_freq("dayspermonth");
                habits_data_class.setHabits_freq_extra(check_which_button_is_chosen_habit());
            } else if (spinner_to_choose_the_frequency_of_the_habit.getSelectedItemPosition() == 4) {
                habits_data_class.setHabits_freq("timesaperiod");
                habits_data_class.setHabits_freq_extra(times_per_period_in_add_habit_frequency.getText().toString().concat("_").concat(times_per_period_in_add_habit_frequency_in_the_end.getText().toString()));
            }
            String notification_freq = "";
            if (switch_to_turn_notifications_add_a_habit.isChecked()) {
                habits_data_class.setNotifications_on_or_off(true);
                if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 0) {
                    habits_data_class.setNotifications_freq("everyday");
                    notification_freq = "everyday";
                } else if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 1) {
                    habits_data_class.setNotifications_freq("daysperweek");
                    notification_freq = "daysperweek";
                    habits_data_class.setNotifications_freq_extra(show_which_days_are_chosen_add_a_habit_notifications.getText().toString());
                } else if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 2) {
                    habits_data_class.setNotifications_freq("everyndays");
                    notification_freq = "everyndays";
                    habits_data_class.setNotifications_freq_extra(enter_goal_for_new_good_habit_in_habits_add_habit_notification.getText().toString());
                } else if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 3) {
                    habits_data_class.setNotifications_freq("dayspermonth");
                    notification_freq = "dayspermonth";
                    habits_data_class.setNotifications_freq_extra(check_which_button_is_chosen_notification());
                }/* else if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 4) {
                    habits_data_class.setNotifications_freq("timesaperiod");
                    habits_data_class.setNotifications_freq_extra(times_per_period_in_add_habit_frequency_notification.getText().toString().concat("_").concat(times_per_period_in_add_habit_frequency_in_the_end_notification.getText().toString()));
                }*/
                habits_data_class.setNotifications_time(return_time_for_notification());
            } else {
                habits_data_class.setNotifications_on_or_off(false);
            }
            ArrayList<Long> relapse = new ArrayList<>();
            habits_data_class.setRelapse(relapse);

            HashMap<Long, Integer> hasmap = new HashMap<>();
            HashMap<Long, Integer> amount_hashmap = new HashMap<>();
            habits_data_class.setRelapse_amount_timer(hasmap);
            habits_data_class.setRelapse_amount(amount_hashmap);
            Room_database_habits database_habits = Room_database_habits.getInstance(getContext());
            database_habits.dao_for_habits_data().insert(habits_data_class);
            if (switch_to_turn_notifications_add_a_habit.isChecked()) {
                set_the_notification(edit_text_showing_habit_name.getText().toString(), notification_freq, "good", type, icon, database_habits.dao_for_habits_data().getAll().size() + 1, database_habits.dao_for_habits_data().getAll().get(database_habits.dao_for_habits_data().getAll().size() - 1).getId(), Simplify_the_time.return_time_in_midnight(time_in_milli));
            }
        }
    }

    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private void set_up_spinner() {
        if (getView() != null) {
            Spinner spinner = getView().findViewById(R.id.spinner_saying_minutes_or_hours);
            EditText edit_text_to_enter_time_in_add_habit = getView().findViewById(R.id.edit_text_to_enter_time_in_add_habit);
            TextView text_under_the_timer_in_add_a_habit = getView().findViewById(R.id.text_under_the_timer_in_add_a_habit);
            String[] items = new String[]{"Minutes", "Hours"};
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, items);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i == 0) {
                        edit_text_to_enter_time_in_add_habit.setHint("Minutes");
                        text_under_the_timer_in_add_a_habit.setText("Example: I want to read for 20 minutes a day");
                    } else if (i == 1) {
                        edit_text_to_enter_time_in_add_habit.setHint("Hours");
                        text_under_the_timer_in_add_a_habit.setText("Example: I want to read for 1 hour a day");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    private void set_the_spinner_for_freq_of_good_habit() {
        if (getView() != null) {
            Spinner spinner_to_choose_the_frequency_of_the_habit = getView().findViewById(R.id.spinner_to_choose_the_frequency_of_the_habit);
            String[] items = new String[]{"Everyday", "Specific days per week", "Every N days", "Specific days per month"};
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_for_kilo_pound, items);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_to_choose_the_frequency_of_the_habit.setAdapter(dataAdapter);
        }
    }

    private void set_the_spinner_notification() {
        if (getView() != null) {
            Spinner spinner_to_choose_the_notification_of_the_habit = getView().findViewById(R.id.spinner_to_choose_the_notification_of_the_habit);
            String[] items = new String[]{"Everyday", "Specific days per week", "Every N days", "Specific days per month"};
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_for_kilo_pound, items);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_to_choose_the_notification_of_the_habit.setAdapter(dataAdapter);
        }
    }

    private void set_am_pm_npotofaction_time_freq() {
        if (getView() != null) {
            Spinner spinner_to_choose_am_or_pm_in_add_habit_notifactaion_freq = getView().findViewById(R.id.spinner_to_choose_am_or_pm_in_add_habit_notifactaion_freq);
            String[] items = new String[]{"Am", "Pm"};
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_for_kilo_pound, items);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_to_choose_am_or_pm_in_add_habit_notifactaion_freq.setAdapter(dataAdapter);
        }
    }

    private void notification_time_click_listen() {
        if (getView() != null) {
            final Button go_up_hours_add_habit_notification_time_button = getView().findViewById(R.id.go_up_hours_add_habit_notification_time_button);
            final Button go_down_hours_add_habit_notification_time_button = getView().findViewById(R.id.go_down_hours_add_habit_notification_time_button);
            final Button go_up_minutes_add_habit_notification_time_button = getView().findViewById(R.id.go_up_minutes_add_habit_notification_time_button);
            final Button go_down_minutes_add_habit_notification_time_button = getView().findViewById(R.id.go_down_minutes_add_habit_notification_time_button);
            final TextView text_view_saying_the_hour_of_notification = getView().findViewById(R.id.text_view_saying_the_hour_of_notification);
            final TextView text_view_saying_the_minute_of_notification = getView().findViewById(R.id.text_view_saying_the_minute_of_notification);
            go_up_hours_add_habit_notification_time_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int hours = Integer.parseInt(text_view_saying_the_hour_of_notification.getText().toString());
                    if (hours == 12) {
                        text_view_saying_the_hour_of_notification.setText("1");
                    } else {
                        text_view_saying_the_hour_of_notification.setText(String.valueOf(hours + 1));
                    }
                }
            });
            go_down_hours_add_habit_notification_time_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int hours = Integer.parseInt(text_view_saying_the_hour_of_notification.getText().toString());
                    if (hours == 1) {
                        text_view_saying_the_hour_of_notification.setText("12");
                    } else {
                        text_view_saying_the_hour_of_notification.setText(String.valueOf(hours - 1));
                    }
                }
            });
            go_up_minutes_add_habit_notification_time_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int minutes = Integer.parseInt(text_view_saying_the_minute_of_notification.getText().toString());
                    if (minutes == 55) {
                        text_view_saying_the_minute_of_notification.setText("00");
                        int hours = Integer.parseInt(text_view_saying_the_hour_of_notification.getText().toString());
                        if (hours == 12) {
                            text_view_saying_the_hour_of_notification.setText("1");
                        } else {
                            text_view_saying_the_hour_of_notification.setText(String.valueOf(hours + 1));
                        }
                    } else {
                        text_view_saying_the_minute_of_notification.setText(String.valueOf(minutes + 5));
                    }
                }
            });
            go_down_minutes_add_habit_notification_time_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int minutes = Integer.parseInt(text_view_saying_the_minute_of_notification.getText().toString());
                    if (minutes == 0) {
                        text_view_saying_the_minute_of_notification.setText("55");
                        int hours = Integer.parseInt(text_view_saying_the_hour_of_notification.getText().toString());
                        if (hours == 1) {
                            text_view_saying_the_hour_of_notification.setText("12");
                        } else {
                            text_view_saying_the_hour_of_notification.setText(String.valueOf(hours - 1));
                        }
                    } else {
                        if (minutes - 5 == 0) {
                            text_view_saying_the_minute_of_notification.setText("00");
                        } else {
                            text_view_saying_the_minute_of_notification.setText(String.valueOf(minutes - 5));
                        }
                    }
                }
            });
        }
    }

    /*private void set_up_time_in_hour() {
        if (getView() != null) {
            TextView text_view_saying_the_minute_of_notification = getView().findViewById(R.id.text_view_saying_the_minute_of_notification);
            Spinner spinner_to_choose_am_or_pm_in_add_habit_notifactaion_freq = getView().findViewById(R.id.spinner_to_choose_am_or_pm_in_add_habit_notifactaion_freq);
            TextView text_view_saying_the_hour_of_notification = getView().findViewById(R.id.text_view_saying_the_hour_of_notification);
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR);
            if (hour == 0) {
                text_view_saying_the_hour_of_notification.setText("12");
            } else {
                text_view_saying_the_hour_of_notification.setText(String.valueOf(hour));
            }
            int am_pm = calendar.get(Calendar.AM_PM);
            if (am_pm == Calendar.AM) {
                spinner_to_choose_am_or_pm_in_add_habit_notifactaion_freq.setSelection(0);
            } else {
                spinner_to_choose_am_or_pm_in_add_habit_notifactaion_freq.setSelection(1);
            }
            int minutes = calendar.get(Calendar.MINUTE);
            if (minutes % 5 < 3) {
                if (minutes % 5 == 0) {
                    text_view_saying_the_minute_of_notification.setText("00");
                } else {
                    text_view_saying_the_minute_of_notification.setText(String.valueOf(minutes - (minutes % 5)));
                }
            } else {
                if ((minutes - (minutes % 5) + 5) == 60) {
                    int hours = Integer.parseInt(text_view_saying_the_hour_of_notification.getText().toString());
                    if (hours == 12) {
                        text_view_saying_the_hour_of_notification.setText("1");
                    } else {
                        text_view_saying_the_hour_of_notification.setText(String.valueOf(hours + 1));
                    }
                    text_view_saying_the_minute_of_notification.setText("00");
                } else {
                    text_view_saying_the_minute_of_notification.setText(String.valueOf(minutes - (minutes % 5) + 5));
                }
            }
        }
    }*/

    private void notification_button_listen() {
        if (getView() != null) {
            Button button_to_start_and_stop_notifications = getView().findViewById(R.id.button_to_start_and_stop_notifications);
            SwitchMaterial switch_to_turn_notifications_add_a_habit = getView().findViewById(R.id.switch_to_turn_notifications_add_a_habit);
            button_to_start_and_stop_notifications.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (switch_to_turn_notifications_add_a_habit.isChecked()) {
                        switch_to_turn_notifications_add_a_habit.setChecked(false);
                    } else {
                        switch_to_turn_notifications_add_a_habit.setChecked(true);
                    }
                    hide_keyboard();
                }
            });
        }
    }

    private void on_switch_listen() {
        if (getView() != null) {
            SwitchMaterial switch_to_turn_notifications_add_a_habit = getView().findViewById(R.id.switch_to_turn_notifications_add_a_habit);
            final ConstraintLayout layout_notification_in_add_a_habit = getView().findViewById(R.id.layout_notification_in_add_a_habit);
            switch_to_turn_notifications_add_a_habit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        layout_notification_in_add_a_habit.setVisibility(View.VISIBLE);
                    } else {
                        layout_notification_in_add_a_habit.setVisibility(View.GONE);
                    }
                    hide_keyboard();
                }
            });
        }
    }

    private void set_the_icon(int id) {
        if (getView() != null) {
            {
                View showing_icon_in_color_good_habits = getView().findViewById(R.id.showing_icon_in_color_good_habits);
                Drawable icon = ContextCompat.getDrawable(getContext(), id).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            }
            icon = getResources().getResourceEntryName(id);
        }
    }

    private void on_habit_frequency_spinner_choose_listen() {
        if (getView() != null) {
            ConstraintLayout constraint_layout_to_choose_every_N_days = getView().findViewById(R.id.constraint_layout_to_choose_every_N_days);
            Custom_spinner spinner_to_choose_the_frequency_of_the_habit = getView().findViewById(R.id.spinner_to_choose_the_frequency_of_the_habit);
            TextView show_which_days_are_chosen_add_a_habit = getView().findViewById(R.id.show_which_days_are_chosen_add_a_habit);
            ConstraintLayout calender_in_add_a_habit = getView().findViewById(R.id.calender_in_add_a_habit);
            ConstraintLayout layout_saying_the_frequency_off_per_period_in_add_habit = getView().findViewById(R.id.layout_saying_the_frequency_off_per_period_in_add_habit);
            spinner_to_choose_the_frequency_of_the_habit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (should_the_habit_spinner_run) {
                        place_the_text_under_habit_frequency(i);
                        if (i == 0) {
                            show_which_days_are_chosen_add_a_habit.setVisibility(View.GONE);
                            constraint_layout_to_choose_every_N_days.setVisibility(View.GONE);
                            calender_in_add_a_habit.setVisibility(View.GONE);
                            layout_saying_the_frequency_off_per_period_in_add_habit.setVisibility(View.GONE);
                        } else if (i == 1) {
                            Dialog_to_set_good_habit_frequency dialog_to_set_good_habit_frequency = new Dialog_to_set_good_habit_frequency();
                            dialog_to_set_good_habit_frequency.set_share_clicked_listen(new Dialog_to_set_good_habit_frequency.dissmiss_the_pick_frequency() {
                                @Override
                                public void share_just_got_clciked(String text) {
                                    which_days_are_chosen = text;
                                    show_which_days_are_chosen_add_a_habit.setVisibility(View.VISIBLE);
                                    constraint_layout_to_choose_every_N_days.setVisibility(View.GONE);
                                    calender_in_add_a_habit.setVisibility(View.GONE);
                                    layout_saying_the_frequency_off_per_period_in_add_habit.setVisibility(View.GONE);
                                    if (text.equals("cancel")) {
                                        spinner_to_choose_the_frequency_of_the_habit.setSelection(0);
                                    } else if (text.equals("Mo".concat("Tu").concat("We").concat("Th").concat("Fr").concat("Sa").concat("Su"))) {
                                        show_which_days_are_chosen_add_a_habit.setText("Everyday");
                                    } else if (text.length() == 12) {
                                        boolean monday = false;
                                        boolean tuesday = false;
                                        boolean wednesday = false;
                                        boolean thursday = false;
                                        boolean friday = false;
                                        boolean saturday = false;
                                        boolean sunday = false;
                                        if (text.contains("Mo")) {
                                            monday = true;
                                        }
                                        if (text.contains("Tu")) {
                                            tuesday = true;
                                        }
                                        if (text.contains("We")) {
                                            wednesday = true;
                                        }
                                        if (text.contains("Th")) {
                                            thursday = true;
                                        }
                                        if (text.contains("Fr")) {
                                            friday = true;
                                        }
                                        if (text.contains("Sa")) {
                                            saturday = true;
                                        }
                                        if (text.contains("Su")) {
                                            sunday = true;
                                        }
                                        if (!monday) {
                                            show_which_days_are_chosen_add_a_habit.setText("Everyday except Monday");
                                        }
                                        if (!tuesday) {
                                            show_which_days_are_chosen_add_a_habit.setText("Everyday except Tuesday");
                                        }
                                        if (!wednesday) {
                                            show_which_days_are_chosen_add_a_habit.setText("Everyday except Wednesday");
                                        }
                                        if (!thursday) {
                                            show_which_days_are_chosen_add_a_habit.setText("Everyday except Thursday");
                                        }
                                        if (!friday) {
                                            show_which_days_are_chosen_add_a_habit.setText("Everyday except Friday");
                                        }
                                        if (!saturday) {
                                            show_which_days_are_chosen_add_a_habit.setText("Everyday except Saturday");
                                        }
                                        if (!sunday) {
                                            show_which_days_are_chosen_add_a_habit.setText("Everyday except Sunday");
                                        }
                                    } else if (text.length() == 2) {
                                        if (text.equals("Mo")) {
                                            show_which_days_are_chosen_add_a_habit.setText("Monday");
                                        } else if (text.equals("Tu")) {
                                            show_which_days_are_chosen_add_a_habit.setText("Tuesday");
                                        } else if (text.equals("We")) {
                                            show_which_days_are_chosen_add_a_habit.setText("Wednesday");
                                        } else if (text.equals("Th")) {
                                            show_which_days_are_chosen_add_a_habit.setText("Thursday");
                                        } else if (text.equals("Fr")) {
                                            show_which_days_are_chosen_add_a_habit.setText("Friday");
                                        } else if (text.equals("Sa")) {
                                            show_which_days_are_chosen_add_a_habit.setText("Saturday");
                                        } else if (text.equals("Su")) {
                                            show_which_days_are_chosen_add_a_habit.setText("Sunday");
                                        }
                                    } else {
                                        boolean monday = false;
                                        boolean tuesday = false;
                                        boolean wednesday = false;
                                        boolean thursday = false;
                                        boolean friday = false;
                                        boolean saturday = false;
                                        boolean sunday = false;
                                        if (text.contains("Mo")) {
                                            monday = true;
                                        }
                                        if (text.contains("Tu")) {
                                            tuesday = true;
                                        }
                                        if (text.contains("We")) {
                                            wednesday = true;
                                        }
                                        if (text.contains("Th")) {
                                            thursday = true;
                                        }
                                        if (text.contains("Fr")) {
                                            friday = true;
                                        }
                                        if (text.contains("Sa")) {
                                            saturday = true;
                                        }
                                        if (text.contains("Su")) {
                                            sunday = true;
                                        }
                                        String final_text = "";
                                        if (monday) {
                                            final_text = "Mo.,";
                                        }
                                        if (tuesday) {
                                            final_text = final_text.concat("Tu.,");
                                        }
                                        if (wednesday) {
                                            final_text = final_text.concat("We.,");
                                        }
                                        if (thursday) {
                                            final_text = final_text.concat("Th.,");
                                        }
                                        if (friday) {
                                            final_text = final_text.concat("Fr.,");
                                        }
                                        if (saturday) {
                                            final_text = final_text.concat("Sa.,");
                                        }
                                        if (sunday) {
                                            final_text = final_text.concat("Su.,");
                                        }
                                        show_which_days_are_chosen_add_a_habit.setText(final_text);
                                    }
                                }
                            });
                            dialog_to_set_good_habit_frequency.show(getParentFragmentManager(), "");
                        } else if (i == 2) {
                            show_which_days_are_chosen_add_a_habit.setVisibility(View.GONE);
                            constraint_layout_to_choose_every_N_days.setVisibility(View.VISIBLE);
                            calender_in_add_a_habit.setVisibility(View.GONE);
                            layout_saying_the_frequency_off_per_period_in_add_habit.setVisibility(View.GONE);
                        } else if (i == 3) {
                            show_which_days_are_chosen_add_a_habit.setVisibility(View.GONE);
                            constraint_layout_to_choose_every_N_days.setVisibility(View.GONE);
                            calender_in_add_a_habit.setVisibility(View.VISIBLE);
                            layout_saying_the_frequency_off_per_period_in_add_habit.setVisibility(View.GONE);
                        } else if (i == 4) {
                            show_which_days_are_chosen_add_a_habit.setVisibility(View.GONE);
                            constraint_layout_to_choose_every_N_days.setVisibility(View.GONE);
                            calender_in_add_a_habit.setVisibility(View.GONE);
                            layout_saying_the_frequency_off_per_period_in_add_habit.setVisibility(View.VISIBLE);
                        }
                    } else {
                        should_the_habit_spinner_run = true;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    private void button_in_calender_set_a_habit_listen() {
        if (getView() != null) {
            Button calender_button_showing_shadow_1 = getView().findViewById(R.id.calender_button_showing_shadow_1);
            Button calender_button_showing_shadow_2 = getView().findViewById(R.id.calender_button_showing_shadow_2);
            Button calender_button_showing_shadow_3 = getView().findViewById(R.id.calender_button_showing_shadow_3);
            Button calender_button_showing_shadow_4 = getView().findViewById(R.id.calender_button_showing_shadow_4);
            Button calender_button_showing_shadow_5 = getView().findViewById(R.id.calender_button_showing_shadow_5);
            Button calender_button_showing_shadow_6 = getView().findViewById(R.id.calender_button_showing_shadow_6);
            Button calender_button_showing_shadow_7 = getView().findViewById(R.id.calender_button_showing_shadow_7);
            Button calender_button_showing_shadow_8 = getView().findViewById(R.id.calender_button_showing_shadow_8);
            Button calender_button_showing_shadow_9 = getView().findViewById(R.id.calender_button_showing_shadow_9);
            Button calender_button_showing_shadow_10 = getView().findViewById(R.id.calender_button_showing_shadow_10);
            Button calender_button_showing_shadow_11 = getView().findViewById(R.id.calender_button_showing_shadow_11);
            Button calender_button_showing_shadow_12 = getView().findViewById(R.id.calender_button_showing_shadow_12);
            Button calender_button_showing_shadow_13 = getView().findViewById(R.id.calender_button_showing_shadow_13);
            Button calender_button_showing_shadow_14 = getView().findViewById(R.id.calender_button_showing_shadow_14);
            Button calender_button_showing_shadow_15 = getView().findViewById(R.id.calender_button_showing_shadow_15);
            Button calender_button_showing_shadow_16 = getView().findViewById(R.id.calender_button_showing_shadow_16);
            Button calender_button_showing_shadow_17 = getView().findViewById(R.id.calender_button_showing_shadow_17);
            Button calender_button_showing_shadow_18 = getView().findViewById(R.id.calender_button_showing_shadow_18);
            Button calender_button_showing_shadow_19 = getView().findViewById(R.id.calender_button_showing_shadow_19);
            Button calender_button_showing_shadow_20 = getView().findViewById(R.id.calender_button_showing_shadow_20);
            Button calender_button_showing_shadow_21 = getView().findViewById(R.id.calender_button_showing_shadow_21);
            Button calender_button_showing_shadow_22 = getView().findViewById(R.id.calender_button_showing_shadow_22);
            Button calender_button_showing_shadow_23 = getView().findViewById(R.id.calender_button_showing_shadow_23);
            Button calender_button_showing_shadow_24 = getView().findViewById(R.id.calender_button_showing_shadow_24);
            Button calender_button_showing_shadow_25 = getView().findViewById(R.id.calender_button_showing_shadow_25);
            Button calender_button_showing_shadow_26 = getView().findViewById(R.id.calender_button_showing_shadow_26);
            Button calender_button_showing_shadow_27 = getView().findViewById(R.id.calender_button_showing_shadow_27);
            Button calender_button_showing_shadow_28 = getView().findViewById(R.id.calender_button_showing_shadow_28);
            calender_button_showing_shadow_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_1);
                }
            });
            calender_button_showing_shadow_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_2);
                }
            });
            calender_button_showing_shadow_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_3);
                }
            });
            calender_button_showing_shadow_4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_4);
                }
            });
            calender_button_showing_shadow_5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_5);
                }
            });
            calender_button_showing_shadow_6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_6);
                }
            });
            calender_button_showing_shadow_7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_7);
                }
            });
            calender_button_showing_shadow_8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_8);
                }
            });
            calender_button_showing_shadow_9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_9);
                }
            });
            calender_button_showing_shadow_10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_10);
                }
            });
            calender_button_showing_shadow_11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_11);
                }
            });
            calender_button_showing_shadow_12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_12);
                }
            });
            calender_button_showing_shadow_13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_13);
                }
            });
            calender_button_showing_shadow_14.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_14);
                }
            });
            calender_button_showing_shadow_15.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_15);
                }
            });
            calender_button_showing_shadow_16.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_16);
                }
            });
            calender_button_showing_shadow_17.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_17);
                }
            });
            calender_button_showing_shadow_18.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_18);
                }
            });
            calender_button_showing_shadow_19.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_19);
                }
            });
            calender_button_showing_shadow_20.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_20);
                }
            });
            calender_button_showing_shadow_21.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_21);
                }
            });
            calender_button_showing_shadow_22.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_22);
                }
            });
            calender_button_showing_shadow_23.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_23);
                }
            });
            calender_button_showing_shadow_24.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_24);
                }
            });
            calender_button_showing_shadow_25.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_25);
                }
            });
            calender_button_showing_shadow_26.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_26);
                }
            });
            calender_button_showing_shadow_27.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_27);
                }
            });
            calender_button_showing_shadow_28.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color_the_background(calender_button_showing_shadow_28);
                }
            });
        }
    }

    private void color_the_background(Button button) {
        if (getContext() != null && getView() != null) {
            Drawable fav_color = AppCompatResources.getDrawable(getContext(), R.drawable.round_button_colored_fav);
            if (button.getTextColors() == ColorStateList.valueOf(Color.WHITE)) {
                button.setBackground(null);
                button.setTextColor(Color.BLACK);
            } else {
                button.setBackground(fav_color);
                button.setTextColor(Color.WHITE);
            }
        }
    }

    private void text_watcher_for_goals() {
        if (getView() != null) {
            EditText enter_goal_for_new_good_habit_in_habits = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits);
            TextView text_saying_days_beside_habit_edit_text = getView().findViewById(R.id.text_saying_days_beside_habit_edit_text);
            enter_goal_for_new_good_habit_in_habits.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().equals("1")) {
                        text_saying_days_beside_habit_edit_text.setText(" day");
                    } else {
                        text_saying_days_beside_habit_edit_text.setText(" days");
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    private void every_couple_of_days_text_watcher() {
        if (getView() != null) {
            EditText enter_goal_for_new_good_habit_in_habits_add_habit = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits_add_habit);
            TextView text_saying_days_beside_habit_edit_text_add_habit = getView().findViewById(R.id.text_saying_days_beside_habit_edit_text_add_habit);
            enter_goal_for_new_good_habit_in_habits_add_habit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().equals("1")) {
                        text_saying_days_beside_habit_edit_text_add_habit.setText(" day");
                    } else {
                        text_saying_days_beside_habit_edit_text_add_habit.setText(" days");
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    private void watch_time_edit_text() {
        if (getView() != null) {
            EditText times_per_period_in_add_habit_frequency = getView().findViewById(R.id.times_per_period_in_add_habit_frequency);
            TextView text_saying_he_between_in_habit_frequency = getView().findViewById(R.id.text_saying_he_between_in_habit_frequency);
            times_per_period_in_add_habit_frequency.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().equals("1")) {
                        text_saying_he_between_in_habit_frequency.setText("time per");
                    } else {
                        text_saying_he_between_in_habit_frequency.setText("times per");
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    private void times_per_period_watch_in_the_end() {
        if (getView() != null) {
            EditText times_per_period_in_add_habit_frequency_in_the_end = getView().findViewById(R.id.times_per_period_in_add_habit_frequency_in_the_end);
            TextView text_saying_in_the_end_in_habit_frequency = getView().findViewById(R.id.text_saying_in_the_end_in_habit_frequency);
            times_per_period_in_add_habit_frequency_in_the_end.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().equals("1")) {
                        text_saying_in_the_end_in_habit_frequency.setText("day");
                    } else {
                        text_saying_in_the_end_in_habit_frequency.setText("days");
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    private void notification_spinner() {
        if (getView() != null) {
            Custom_spinner spinner_to_choose_the_notification_of_the_habit = getView().findViewById(R.id.spinner_to_choose_the_notification_of_the_habit);
            TextView show_which_days_are_chosen_add_a_habit_notifications = getView().findViewById(R.id.show_which_days_are_chosen_add_a_habit_notifications);
            ConstraintLayout calender_in_add_a_habit_add_a_notification = getView().findViewById(R.id.calender_in_add_a_habit_add_a_notification);
            ConstraintLayout constraint_layout_to_choose_every_N_days_in_notification = getView().findViewById(R.id.constraint_layout_to_choose_every_N_days_in_notification);
            ConstraintLayout layout_saying_the_frequency_off_per_period_in_add_habit_notification = getView().findViewById(R.id.layout_saying_the_frequency_off_per_period_in_add_habit_notification);
            spinner_to_choose_the_notification_of_the_habit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (should_the_notification_spinner_run) {
                        if (i == 0) {
                            show_which_days_are_chosen_add_a_habit_notifications.setVisibility(View.GONE);
                            calender_in_add_a_habit_add_a_notification.setVisibility(View.GONE);
                            constraint_layout_to_choose_every_N_days_in_notification.setVisibility(View.GONE);
                            layout_saying_the_frequency_off_per_period_in_add_habit_notification.setVisibility(View.GONE);
                        } else if (i == 1) {
                            Dialog_to_set_good_habit_frequency dialog_to_set_good_habit_frequency = new Dialog_to_set_good_habit_frequency();
                            dialog_to_set_good_habit_frequency.set_share_clicked_listen(new Dialog_to_set_good_habit_frequency.dissmiss_the_pick_frequency() {
                                @Override
                                public void share_just_got_clciked(String text) {
                                    show_which_days_are_chosen_add_a_habit_notifications.setVisibility(View.VISIBLE);
                                    calender_in_add_a_habit_add_a_notification.setVisibility(View.GONE);
                                    constraint_layout_to_choose_every_N_days_in_notification.setVisibility(View.GONE);
                                    layout_saying_the_frequency_off_per_period_in_add_habit_notification.setVisibility(View.GONE);
                                    if (text.equals("cancel")) {
                                        spinner_to_choose_the_notification_of_the_habit.setSelection(0);
                                    } else if (text.equals("Mo".concat("Tu").concat("We").concat("Th").concat("Fr").concat("Sa").concat("Su"))) {
                                        show_which_days_are_chosen_add_a_habit_notifications.setText("Everyday");
                                    } else if (text.length() == 12) {
                                        boolean monday = false;
                                        boolean tuesday = false;
                                        boolean wednesday = false;
                                        boolean thursday = false;
                                        boolean friday = false;
                                        boolean saturday = false;
                                        boolean sunday = false;
                                        if (text.contains("Mo")) {
                                            monday = true;
                                        }
                                        if (text.contains("Tu")) {
                                            tuesday = true;
                                        }
                                        if (text.contains("We")) {
                                            wednesday = true;
                                        }
                                        if (text.contains("Th")) {
                                            thursday = true;
                                        }
                                        if (text.contains("Fr")) {
                                            friday = true;
                                        }
                                        if (text.contains("Sa")) {
                                            saturday = true;
                                        }
                                        if (text.contains("Su")) {
                                            sunday = true;
                                        }
                                        if (!monday) {
                                            show_which_days_are_chosen_add_a_habit_notifications.setText("Everyday except Monday");
                                        }
                                        if (!tuesday) {
                                            show_which_days_are_chosen_add_a_habit_notifications.setText("Everyday except Tuesday");
                                        }
                                        if (!wednesday) {
                                            show_which_days_are_chosen_add_a_habit_notifications.setText("Everyday except Wednesday");
                                        }
                                        if (!thursday) {
                                            show_which_days_are_chosen_add_a_habit_notifications.setText("Everyday except Thursday");
                                        }
                                        if (!friday) {
                                            show_which_days_are_chosen_add_a_habit_notifications.setText("Everyday except Friday");
                                        }
                                        if (!saturday) {
                                            show_which_days_are_chosen_add_a_habit_notifications.setText("Everyday except Saturday");
                                        }
                                        if (!sunday) {
                                            show_which_days_are_chosen_add_a_habit_notifications.setText("Everyday except Sunday");
                                        }
                                    } else {
                                        boolean monday = false;
                                        boolean tuesday = false;
                                        boolean wednesday = false;
                                        boolean thursday = false;
                                        boolean friday = false;
                                        boolean saturday = false;
                                        boolean sunday = false;
                                        if (text.contains("Mo")) {
                                            monday = true;
                                        }
                                        if (text.contains("Tu")) {
                                            tuesday = true;
                                        }
                                        if (text.contains("We")) {
                                            wednesday = true;
                                        }
                                        if (text.contains("Th")) {
                                            thursday = true;
                                        }
                                        if (text.contains("Fr")) {
                                            friday = true;
                                        }
                                        if (text.contains("Sa")) {
                                            saturday = true;
                                        }
                                        if (text.contains("Su")) {
                                            sunday = true;
                                        }
                                        String final_text = "";
                                        if (monday) {
                                            final_text = "Mo.,";
                                        }
                                        if (tuesday) {
                                            final_text = final_text.concat("Tu.,");
                                        }
                                        if (wednesday) {
                                            final_text = final_text.concat("We.,");
                                        }
                                        if (thursday) {
                                            final_text = final_text.concat("Th.,");
                                        }
                                        if (friday) {
                                            final_text = final_text.concat("Fr.,");
                                        }
                                        if (saturday) {
                                            final_text = final_text.concat("Sa.,");
                                        }
                                        if (sunday) {
                                            final_text = final_text.concat("Su.,");
                                        }
                                        show_which_days_are_chosen_add_a_habit_notifications.setText(final_text);
                                    }
                                }
                            });
                            dialog_to_set_good_habit_frequency.show(getParentFragmentManager(), "");
                        } else if (i == 2) {
                            show_which_days_are_chosen_add_a_habit_notifications.setVisibility(View.GONE);
                            calender_in_add_a_habit_add_a_notification.setVisibility(View.GONE);
                            constraint_layout_to_choose_every_N_days_in_notification.setVisibility(View.VISIBLE);
                            layout_saying_the_frequency_off_per_period_in_add_habit_notification.setVisibility(View.GONE);
                        } else if (i == 3) {
                            show_which_days_are_chosen_add_a_habit_notifications.setVisibility(View.GONE);
                            calender_in_add_a_habit_add_a_notification.setVisibility(View.VISIBLE);
                            constraint_layout_to_choose_every_N_days_in_notification.setVisibility(View.GONE);
                            layout_saying_the_frequency_off_per_period_in_add_habit_notification.setVisibility(View.GONE);
                        } else if (i == 4) {
                            layout_saying_the_frequency_off_per_period_in_add_habit_notification.setVisibility(View.VISIBLE);
                            show_which_days_are_chosen_add_a_habit_notifications.setVisibility(View.GONE);
                            calender_in_add_a_habit_add_a_notification.setVisibility(View.GONE);
                            constraint_layout_to_choose_every_N_days_in_notification.setVisibility(View.GONE);
                        }
                    } else {
                        should_the_notification_spinner_run = true;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    private void every_n_days_notifacation() {
        if (getView() != null) {
            EditText enter_goal_for_new_good_habit_in_habits_add_habit_notification = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits_add_habit_notification);
            TextView text_saying_days_beside_habit_edit_text_add_habit_notification = getView().findViewById(R.id.text_saying_days_beside_habit_edit_text_add_habit_notification);
            enter_goal_for_new_good_habit_in_habits_add_habit_notification.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().equals("1")) {
                        text_saying_days_beside_habit_edit_text_add_habit_notification.setText(" day");
                    } else {
                        text_saying_days_beside_habit_edit_text_add_habit_notification.setText(" days");
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    private void times_listen_notification() {
        if (getView() != null) {
            EditText times_per_period_in_add_habit_frequency_notification = getView().findViewById(R.id.times_per_period_in_add_habit_frequency_notification);
            TextView text_saying_he_between_in_habit_frequency_notification = getView().findViewById(R.id.text_saying_he_between_in_habit_frequency_notification);
            times_per_period_in_add_habit_frequency_notification.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().equals("1")) {
                        text_saying_he_between_in_habit_frequency_notification.setText("time per");
                    } else {
                        text_saying_he_between_in_habit_frequency_notification.setText("times per");
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    private void end_text_listen_notifications() {
        if (getView() != null) {
            EditText times_per_period_in_add_habit_frequency_in_the_end_notification = getView().findViewById(R.id.times_per_period_in_add_habit_frequency_in_the_end_notification);
            TextView text_saying_in_the_end_in_habit_frequency_notification = getView().findViewById(R.id.text_saying_in_the_end_in_habit_frequency_notification);
            times_per_period_in_add_habit_frequency_in_the_end_notification.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().equals("1")) {
                        text_saying_in_the_end_in_habit_frequency_notification.setText("day");
                    } else {
                        text_saying_in_the_end_in_habit_frequency_notification.setText("days");
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    private void notification_month_listen() {
        if (getView() != null) {
            Button calender_button_showing_shadow_1_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_1_add_a_notification);
            Button calender_button_showing_shadow_2_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_2_add_a_notification);
            Button calender_button_showing_shadow_3_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_3_add_a_notification);
            Button calender_button_showing_shadow_4_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_4_add_a_notification);
            Button calender_button_showing_shadow_5_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_5_add_a_notification);
            Button calender_button_showing_shadow_6_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_6_add_a_notification);
            Button calender_button_showing_shadow_7_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_7_add_a_notification);
            Button calender_button_showing_shadow_8_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_8_add_a_notification);
            Button calender_button_showing_shadow_9_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_9_add_a_notification);
            Button calender_button_showing_shadow_10_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_10_add_a_notification);
            Button calender_button_showing_shadow_11_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_11_add_a_notification);
            Button calender_button_showing_shadow_12_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_12_add_a_notification);
            Button calender_button_showing_shadow_13_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_13_add_a_notification);
            Button calender_button_showing_shadow_14_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_14_add_a_notification);
            Button calender_button_showing_shadow_15_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_15_add_a_notification);
            Button calender_button_showing_shadow_16_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_16_add_a_notification);
            Button calender_button_showing_shadow_17_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_17_add_a_notification);
            Button calender_button_showing_shadow_18_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_18_add_a_notification);
            Button calender_button_showing_shadow_19_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_19_add_a_notification);
            Button calender_button_showing_shadow_20_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_20_add_a_notification);
            Button calender_button_showing_shadow_21_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_21_add_a_notification);
            Button calender_button_showing_shadow_22_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_22_add_a_notification);
            Button calender_button_showing_shadow_23_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_23_add_a_notification);
            Button calender_button_showing_shadow_24_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_24_add_a_notification);
            Button calender_button_showing_shadow_25_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_25_add_a_notification);
            Button calender_button_showing_shadow_26_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_26_add_a_notification);
            Button calender_button_showing_shadow_27_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_27_add_a_notification);
            Button calender_button_showing_shadow_28_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_28_add_a_notification);
            calender_button_showing_shadow_1_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_1_add_a_notification);
                }
            });
            calender_button_showing_shadow_2_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_2_add_a_notification);
                }
            });
            calender_button_showing_shadow_3_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_3_add_a_notification);
                }
            });
            calender_button_showing_shadow_4_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_4_add_a_notification);
                }
            });
            calender_button_showing_shadow_5_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_5_add_a_notification);
                }
            });
            calender_button_showing_shadow_6_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_6_add_a_notification);
                }
            });
            calender_button_showing_shadow_7_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_7_add_a_notification);
                }
            });
            calender_button_showing_shadow_8_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_8_add_a_notification);
                }
            });
            calender_button_showing_shadow_9_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_9_add_a_notification);
                }
            });
            calender_button_showing_shadow_10_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_10_add_a_notification);
                }
            });
            calender_button_showing_shadow_11_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_11_add_a_notification);
                }
            });
            calender_button_showing_shadow_12_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_12_add_a_notification);
                }
            });
            calender_button_showing_shadow_13_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_13_add_a_notification);
                }
            });
            calender_button_showing_shadow_14_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_14_add_a_notification);
                }
            });
            calender_button_showing_shadow_15_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_15_add_a_notification);
                }
            });
            calender_button_showing_shadow_16_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_16_add_a_notification);
                }
            });
            calender_button_showing_shadow_17_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_17_add_a_notification);
                }
            });
            calender_button_showing_shadow_18_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_18_add_a_notification);
                }
            });
            calender_button_showing_shadow_19_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_19_add_a_notification);
                }
            });
            calender_button_showing_shadow_20_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_20_add_a_notification);
                }
            });
            calender_button_showing_shadow_21_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_21_add_a_notification);
                }
            });
            calender_button_showing_shadow_22_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_22_add_a_notification);
                }
            });
            calender_button_showing_shadow_23_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_23_add_a_notification);
                }
            });
            calender_button_showing_shadow_24_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_24_add_a_notification);
                }
            });
            calender_button_showing_shadow_25_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_25_add_a_notification);
                }
            });
            calender_button_showing_shadow_26_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_26_add_a_notification);
                }
            });
            calender_button_showing_shadow_27_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_27_add_a_notification);
                }
            });
            calender_button_showing_shadow_28_add_a_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color_the_background(calender_button_showing_shadow_28_add_a_notification);
                }
            });
        }
    }

    private String check_which_button_is_chosen_notification() {
        Button calender_button_showing_shadow_1_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_1_add_a_notification);
        Button calender_button_showing_shadow_2_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_2_add_a_notification);
        Button calender_button_showing_shadow_3_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_3_add_a_notification);
        Button calender_button_showing_shadow_4_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_4_add_a_notification);
        Button calender_button_showing_shadow_5_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_5_add_a_notification);
        Button calender_button_showing_shadow_6_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_6_add_a_notification);
        Button calender_button_showing_shadow_7_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_7_add_a_notification);
        Button calender_button_showing_shadow_8_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_8_add_a_notification);
        Button calender_button_showing_shadow_9_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_9_add_a_notification);
        Button calender_button_showing_shadow_10_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_10_add_a_notification);
        Button calender_button_showing_shadow_11_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_11_add_a_notification);
        Button calender_button_showing_shadow_12_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_12_add_a_notification);
        Button calender_button_showing_shadow_13_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_13_add_a_notification);
        Button calender_button_showing_shadow_14_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_14_add_a_notification);
        Button calender_button_showing_shadow_15_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_15_add_a_notification);
        Button calender_button_showing_shadow_16_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_16_add_a_notification);
        Button calender_button_showing_shadow_17_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_17_add_a_notification);
        Button calender_button_showing_shadow_18_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_18_add_a_notification);
        Button calender_button_showing_shadow_19_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_19_add_a_notification);
        Button calender_button_showing_shadow_20_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_20_add_a_notification);
        Button calender_button_showing_shadow_21_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_21_add_a_notification);
        Button calender_button_showing_shadow_22_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_22_add_a_notification);
        Button calender_button_showing_shadow_23_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_23_add_a_notification);
        Button calender_button_showing_shadow_24_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_24_add_a_notification);
        Button calender_button_showing_shadow_25_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_25_add_a_notification);
        Button calender_button_showing_shadow_26_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_26_add_a_notification);
        Button calender_button_showing_shadow_27_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_27_add_a_notification);
        Button calender_button_showing_shadow_28_add_a_notification = getView().findViewById(R.id.calender_button_showing_shadow_28_add_a_notification);
        String return_me = "";
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_1_add_a_notification)) {
            return_me = return_me.concat("1").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_2_add_a_notification)) {
            return_me = return_me.concat("2").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_3_add_a_notification)) {
            return_me = return_me.concat("3").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_4_add_a_notification)) {
            return_me = return_me.concat("4").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_5_add_a_notification)) {
            return_me = return_me.concat("5").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_6_add_a_notification)) {
            return_me = return_me.concat("6").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_7_add_a_notification)) {
            return_me = return_me.concat("7").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_8_add_a_notification)) {
            return_me = return_me.concat("8").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_9_add_a_notification)) {
            return_me = return_me.concat("9").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_10_add_a_notification)) {
            return_me = return_me.concat("10").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_11_add_a_notification)) {
            return_me = return_me.concat("11").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_12_add_a_notification)) {
            return_me = return_me.concat("12").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_13_add_a_notification)) {
            return_me = return_me.concat("13").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_14_add_a_notification)) {
            return_me = return_me.concat("14").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_15_add_a_notification)) {
            return_me = return_me.concat("15").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_16_add_a_notification)) {
            return_me = return_me.concat("16").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_17_add_a_notification)) {
            return_me = return_me.concat("17").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_18_add_a_notification)) {
            return_me = return_me.concat("18").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_19_add_a_notification)) {
            return_me = return_me.concat("19").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_20_add_a_notification)) {
            return_me = return_me.concat("20").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_21_add_a_notification)) {
            return_me = return_me.concat("21").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_22_add_a_notification)) {
            return_me = return_me.concat("22").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_23_add_a_notification)) {
            return_me = return_me.concat("23").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_24_add_a_notification)) {
            return_me = return_me.concat("24").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_25_add_a_notification)) {
            return_me = return_me.concat("25").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_26_add_a_notification)) {
            return_me = return_me.concat("26").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_27_add_a_notification)) {
            return_me = return_me.concat("27").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_28_add_a_notification)) {
            return_me = return_me.concat("28").concat("_");
        }
        return return_me;
    }

    private boolean check_if_back_ground_is_the_same(Button button) {
        if (button.getTextColors() == ColorStateList.valueOf(Color.WHITE)) {
            return true;
        } else {
            return false;
        }
    }

    private long return_time_for_notification() {
        TextView text_view_saying_the_hour_of_notification = getView().findViewById(R.id.text_view_saying_the_hour_of_notification);
        TextView text_view_saying_the_minute_of_notification = getView().findViewById(R.id.text_view_saying_the_minute_of_notification);
        Spinner spinner_to_choose_am_or_pm_in_add_habit_notifactaion_freq = getView().findViewById(R.id.spinner_to_choose_am_or_pm_in_add_habit_notifactaion_freq);
        long hours = Integer.parseInt(text_view_saying_the_hour_of_notification.getText().toString());
        long minutes = Integer.parseInt(text_view_saying_the_minute_of_notification.getText().toString());
        long milli_hours;
        long milli_minutes;
        long total = 0;
        if (spinner_to_choose_am_or_pm_in_add_habit_notifactaion_freq.getSelectedItemPosition() == 0) {
            if (hours == 12) {
                milli_hours = 0;
            } else {
                milli_hours = hours * 3600000L;
            }
            milli_minutes = minutes * 60000L;
        } else {
            if (hours == 12) {
                milli_hours = 0;
            } else {
                milli_hours = hours * 3600000L;
            }
            milli_minutes = minutes * 60000L;
            total = 43200000L;
        }
        total = total + milli_hours + milli_minutes;
        return total;
    }

    private String check_which_button_is_chosen_habit() {
        Button calender_button_showing_shadow_1 = getView().findViewById(R.id.calender_button_showing_shadow_1);
        Button calender_button_showing_shadow_2 = getView().findViewById(R.id.calender_button_showing_shadow_2);
        Button calender_button_showing_shadow_3 = getView().findViewById(R.id.calender_button_showing_shadow_3);
        Button calender_button_showing_shadow_4 = getView().findViewById(R.id.calender_button_showing_shadow_4);
        Button calender_button_showing_shadow_5 = getView().findViewById(R.id.calender_button_showing_shadow_5);
        Button calender_button_showing_shadow_6 = getView().findViewById(R.id.calender_button_showing_shadow_6);
        Button calender_button_showing_shadow_7 = getView().findViewById(R.id.calender_button_showing_shadow_7);
        Button calender_button_showing_shadow_8 = getView().findViewById(R.id.calender_button_showing_shadow_8);
        Button calender_button_showing_shadow_9 = getView().findViewById(R.id.calender_button_showing_shadow_9);
        Button calender_button_showing_shadow_10 = getView().findViewById(R.id.calender_button_showing_shadow_10);
        Button calender_button_showing_shadow_11 = getView().findViewById(R.id.calender_button_showing_shadow_11);
        Button calender_button_showing_shadow_12 = getView().findViewById(R.id.calender_button_showing_shadow_12);
        Button calender_button_showing_shadow_13 = getView().findViewById(R.id.calender_button_showing_shadow_13);
        Button calender_button_showing_shadow_14 = getView().findViewById(R.id.calender_button_showing_shadow_14);
        Button calender_button_showing_shadow_15 = getView().findViewById(R.id.calender_button_showing_shadow_15);
        Button calender_button_showing_shadow_16 = getView().findViewById(R.id.calender_button_showing_shadow_16);
        Button calender_button_showing_shadow_17 = getView().findViewById(R.id.calender_button_showing_shadow_17);
        Button calender_button_showing_shadow_18 = getView().findViewById(R.id.calender_button_showing_shadow_18);
        Button calender_button_showing_shadow_19 = getView().findViewById(R.id.calender_button_showing_shadow_19);
        Button calender_button_showing_shadow_20 = getView().findViewById(R.id.calender_button_showing_shadow_20);
        Button calender_button_showing_shadow_21 = getView().findViewById(R.id.calender_button_showing_shadow_21);
        Button calender_button_showing_shadow_22 = getView().findViewById(R.id.calender_button_showing_shadow_22);
        Button calender_button_showing_shadow_23 = getView().findViewById(R.id.calender_button_showing_shadow_23);
        Button calender_button_showing_shadow_24 = getView().findViewById(R.id.calender_button_showing_shadow_24);
        Button calender_button_showing_shadow_25 = getView().findViewById(R.id.calender_button_showing_shadow_25);
        Button calender_button_showing_shadow_26 = getView().findViewById(R.id.calender_button_showing_shadow_26);
        Button calender_button_showing_shadow_27 = getView().findViewById(R.id.calender_button_showing_shadow_27);
        Button calender_button_showing_shadow_28 = getView().findViewById(R.id.calender_button_showing_shadow_28);
        String return_me = "";
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_1)) {
            return_me = return_me.concat("1").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_2)) {
            return_me = return_me.concat("2").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_3)) {
            return_me = return_me.concat("3").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_4)) {
            return_me = return_me.concat("4").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_5)) {
            return_me = return_me.concat("5").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_6)) {
            return_me = return_me.concat("6").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_7)) {
            return_me = return_me.concat("7").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_8)) {
            return_me = return_me.concat("8").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_9)) {
            return_me = return_me.concat("9").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_10)) {
            return_me = return_me.concat("10").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_11)) {
            return_me = return_me.concat("11").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_12)) {
            return_me = return_me.concat("12").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_13)) {
            return_me = return_me.concat("13").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_14)) {
            return_me = return_me.concat("14").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_15)) {
            return_me = return_me.concat("15").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_16)) {
            return_me = return_me.concat("16").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_17)) {
            return_me = return_me.concat("17").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_18)) {
            return_me = return_me.concat("18").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_19)) {
            return_me = return_me.concat("19").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_20)) {
            return_me = return_me.concat("20").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_21)) {
            return_me = return_me.concat("21").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_22)) {
            return_me = return_me.concat("22").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_23)) {
            return_me = return_me.concat("23").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_24)) {
            return_me = return_me.concat("24").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_25)) {
            return_me = return_me.concat("25").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_26)) {
            return_me = return_me.concat("26").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_27)) {
            return_me = return_me.concat("27").concat("_");
        }
        if (check_if_back_ground_is_the_same(calender_button_showing_shadow_28)) {
            return_me = return_me.concat("28").concat("_");
        }
        return return_me;
    }

    private void set_the_notification(String habit_name, String type_of_notification, String good_or_bad_habit, String type_of_habit, String icon_of_habit, int value_of_position, int id, long start_time) {
        if (getActivity() != null) {
            AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(getActivity(), Send_notifcation_at_set_time.class);
            intent.putExtra("habit_name", habit_name);
            intent.putExtra("type_of_notification", type_of_notification);
            intent.putExtra("good_or_bad_habit", good_or_bad_habit);
            intent.putExtra("type_of_habit", type_of_habit);
            intent.putExtra("icon_of_habit", icon_of_habit);
            //intent.putExtra("value_for_position",value_of_position);
            intent.putExtra("id", id);
            intent.putExtra("start_time", start_time);
            PendingIntent pendingIntent;
            pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) + return_time_for_notification(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);
            save_the_alarms(habit_name, type_of_notification, good_or_bad_habit, type_of_habit, icon_of_habit, value_of_position, id, Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) + return_time_for_notification(), start_time);
        }
    }

    private void hide_keyboard() {
        if (getActivity() != null) {
            View view = getActivity().getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    private void save_the_alarms(String habit_name, String type_of_notification, String good_or_bad_habit, String type_of_habit, String icon_of_habit, int value_of_position, int id, long time, long start_time) {
        if (getActivity() != null) {
            habit_name = habit_name.replace("big_split", "").replace("small_split", "");
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("alarms_saved", Context.MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            String final_string = habit_name.concat("small_split").concat(type_of_notification).concat("small_split").concat(good_or_bad_habit).concat("small_split").concat(type_of_habit).concat("small_split").concat(icon_of_habit).concat("small_split").concat(String.valueOf(value_of_position)).concat("small_split").concat(String.valueOf(id)).concat("small_split").concat(String.valueOf(time)).concat("small_split").concat(String.valueOf(start_time)).concat("big_split");
            myEdit.putString("alarms", final_string);
            myEdit.commit();
        }
    }

    private void make_text_bold() {
        if (getView() != null) {
            TextView text_saying_example_of_goal_under_goal_edit_text = getView().findViewById(R.id.text_saying_example_of_goal_under_goal_edit_text);
            SpannableStringBuilder sb = new SpannableStringBuilder("Example: I want to exercise 10 days in a row");
            sb.setSpan(new StyleSpan(Typeface.BOLD), 28, 28 + 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            text_saying_example_of_goal_under_goal_edit_text.setText(new SpannableString(sb));
        }
    }

    private void make_habit_frequency_text_bold() {
        if (getView() != null) {
            TextView text_saying_examples_of_habit_frequecy = getView().findViewById(R.id.text_saying_examples_of_habit_frequecy);
            SpannableStringBuilder sb = new SpannableStringBuilder("Everyday: I want to run everyday\nSpecific days per week: I want to run on Fridays\nEvery N days: I want to run once every 5 days\nSpecific days per month: I want to run on the 1st of every month");
            sb.setSpan(new StyleSpan(Typeface.BOLD), 0, 8, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            sb.setSpan(new StyleSpan(Typeface.BOLD), 32, 32 + 22, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            sb.setSpan(new StyleSpan(Typeface.BOLD), 81, 94, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            sb.setSpan(new StyleSpan(Typeface.BOLD), 127, 131 + 20, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            text_saying_examples_of_habit_frequecy.setText(new SpannableString(sb));
        }
    }

    private void place_the_text_under_habit_frequency(int which_is_selected) {
        if (getView() != null) {
            TextView text_saying_examples_of_habit_frequecy = getView().findViewById(R.id.text_saying_examples_of_habit_frequecy);
            TextView show_which_days_are_chosen_add_a_habit = getView().findViewById(R.id.show_which_days_are_chosen_add_a_habit);
            ConstraintLayout constraint_layout_to_choose_every_N_days = getView().findViewById(R.id.constraint_layout_to_choose_every_N_days);
            ConstraintLayout calender_in_add_a_habit = getView().findViewById(R.id.calender_in_add_a_habit);
            ConstraintLayout layout = getView().findViewById(R.id.layout_mentioning_the_spinner_and_frequency);
            ConstraintSet set = new ConstraintSet();
            set.clone(layout);
            if (which_is_selected == 0 || which_is_selected == 1) {
                set.connect(text_saying_examples_of_habit_frequecy.getId(), ConstraintSet.TOP, show_which_days_are_chosen_add_a_habit.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(15, getActivity()));
                set.applyTo(layout);
            } else if (which_is_selected == 2) {
                set.connect(text_saying_examples_of_habit_frequecy.getId(), ConstraintSet.TOP, constraint_layout_to_choose_every_N_days.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(15, getActivity()));
                set.applyTo(layout);
            } else if (which_is_selected == 3) {
                set.connect(text_saying_examples_of_habit_frequecy.getId(), ConstraintSet.TOP, calender_in_add_a_habit.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(15, getActivity()));
                set.applyTo(layout);
            }
        }
    }

    private void text_under_start_date() {
        if (getView() != null) {
            TextView text_to_sayy_enter_time_of_last_relapse = getView().findViewById(R.id.text_to_sayy_enter_time_of_last_relapse);
            SpannableStringBuilder sb = new SpannableStringBuilder("Choose start date\nExample: I started reading on ".concat(return_month_and_year_as_string()));
            sb.setSpan(new StyleSpan(Typeface.BOLD), 17, 17 + 8, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            text_to_sayy_enter_time_of_last_relapse.setText(new SpannableString(sb));
        }
    }

    private String return_month_and_year_as_string() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM", Locale.getDefault());
        String month_name = month_date.format(cal.getTime());
        return month_name.concat(" 1, ").concat(String.valueOf(cal.get(Calendar.YEAR)));
    }
}

    /*private void read_the_bad_habits() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("list_of_the_bad_habits", MODE_PRIVATE);
            String old = sharedPreferences.getString("Bad_habits", "");
            String[] split_big = old.split("spit_max_for_the_bad_habits");
            String[] split_small = split_big[position].split("split_here_bad_habits");
            which_spinner_is_chosen = split_small[0];
            set_the_spinner(split_small[1]);
            time_in_milli(Long.parseLong(split_small[2]));
            time_in_milli = Long.parseLong(split_small[2]);
            set_the_goal(split_small[3]);
            set_the_color(split_small[4]);
            set_the_icon(split_small[5]);
        }
    }

    private void set_the_spinner(String text) {
        if (getView() != null) {
            EditText edit_text_showing_habit_name = getView().findViewById(R.id.edit_text_showing_habit_name);
            ConstraintLayout constraintLayout = getView().findViewById(R.id.layout_to_show_in_edit_habit_bad);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.applyTo(constraintLayout);
            edit_text_showing_habit_name.setVisibility(View.VISIBLE);
            edit_text_showing_habit_name.setText(text);
            edit_text_showing_habit_name.setSelection(edit_text_showing_habit_name.getText().toString().length());
        }
    }

    private void time_in_milli(long time_in_milli) {
        if (getView() != null) {
            Button button_that_will_open_time_user_hacked = getView().findViewById(R.id.button_that_will_open_time_user_hacked);
            TextView text_to_sayy_enter_time_of_last_relapse = getView().findViewById(R.id.text_to_sayy_enter_time_of_last_relapse);
            Date date = new Date();
            date.setTime(time_in_milli);
            String date_in_string = DateFormat.getDateInstance(DateFormat.MEDIUM).format(date);
            String time_in_string = DateFormat.getTimeInstance(DateFormat.SHORT).format(date);
            button_that_will_open_time_user_hacked.setText("Change Date");
            text_to_sayy_enter_time_of_last_relapse.setText("Date: ".concat(time_in_string).concat(" ").concat(date_in_string));
            Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
            text_to_sayy_enter_time_of_last_relapse.setTypeface(boldTypeface);
            text_to_sayy_enter_time_of_last_relapse.setTextSize(16);
        }
    }

    private void set_the_goal(String goal) {
        if (getView() != null) {
            EditText enter_goal_for_new_good_habit_in_habits = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits);
            TextView text_saying_days_beside_habit_edit_text = getView().findViewById(R.id.text_saying_days_beside_habit_edit_text);
            if (goal.equals("1")) {
                text_saying_days_beside_habit_edit_text.setText("day");
            } else {
                text_saying_days_beside_habit_edit_text.setText("days");
            }
            enter_goal_for_new_good_habit_in_habits.setText(goal);
        }
    }

    private void set_the_color(String text) {
        if (getView() != null) {
            View view_showing_the_icon_and_its_color = getView().findViewById(R.id.view_showing_the_icon_and_its_color);
            TextView text_view_asying_this_is_example_of_icon = getView().findViewById(R.id.text_view_asying_this_is_example_of_icon);
            color = text;
            if (text.equals("teal")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_dark_green);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#607D8B"));
            } else if (text.equals("red")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_red);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#e6194B"));
            } else if (text.equals("green")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_green);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#3cb44b"));
            } else if (text.equals("blue")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_blue);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#4363d8"));
            } else if (text.equals("orange")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_orange);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#f58231"));
            } else if (text.equals("brown")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_brown);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#9A6324"));
            } else if (text.equals("black")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_black);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#000000"));
            } else if (text.equals("cyan")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_cyan);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#42d4f4"));
            } else if (text.equals("lime")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_lime);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#bfef45"));
            } else if (text.equals("magenta")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_magenta);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#f032e6"));
            } else if (text.equals("navy")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_navy);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#000075"));
            } else if (text.equals("pink")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_pink);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#fabed4"));
            } else if (text.equals("yellow")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_yellow);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#ffe119"));
            } else if (text.equals("purple")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_purple);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#911eb4"));
            }
        }
    }

    private void set_the_icon(String text) {
        if (getView() != null && getContext() != null) {
            View showing_icon_in_color_good_habits = getView().findViewById(R.id.showing_icon_in_color_good_habits);
            icon = text;
            if (text.equals("games")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_videogame_asset_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("social_media")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_stay_current_portrait_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("shopping")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_shopping_cart_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("smoking")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_smoking_rooms_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("alcohol")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_sports_bar_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("lying")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_record_voice_over_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("procrastination")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_restore_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("cross")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_clear_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("coffee")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_local_cafe_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("fast food")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_fastfood_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("money")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_money_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("sleeping")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_airline_seat_individual_suite_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("healing")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_healing_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("work")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_work_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("tv")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_tv_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("couch")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_weekend_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("other")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.happy_face_dark_green).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            }
        }
    }

    private void changte_habit_button_listen() {
        if (getView() != null) {
            Button submit_data_first_time_button = getView().findViewById(R.id.submit_data_first_time_button);
            submit_data_first_time_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog_asking_which_habit dialog_asking_which_habit = new Dialog_asking_which_habit();
                    dialog_asking_which_habit.setTargetFragment(edit_bad_habits.this, 1001);
                    dialog_asking_which_habit.show(getActivity().getSupportFragmentManager(), "tag");
                }
            });
        }
    }

    private void change_date_button_listen() {
        if (getView() != null) {
            Button button_that_will_open_time_user_hacked = getView().findViewById(R.id.button_that_will_open_time_user_hacked);
            button_that_will_open_time_user_hacked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Universal_date_picker universal_date_picker = new Universal_date_picker();
                    universal_date_picker.setTargetFragment(edit_bad_habits.this, 22);
                    universal_date_picker.show(getActivity().getSupportFragmentManager(), "date_picker");
                }
            });
        }
    }

    private void goal_days_text_watcher() {
        if (getView() != null) {
            final EditText enter_goal_for_new_good_habit_in_habits = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits);
            final TextView text_saying_days_beside_habit_edit_text = getView().findViewById(R.id.text_saying_days_beside_habit_edit_text);
            enter_goal_for_new_good_habit_in_habits.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (enter_goal_for_new_good_habit_in_habits.hasFocus()) {
                        if (!charSequence.toString().equals("")) {
                            if (Integer.parseInt(charSequence.toString()) == 1) {
                                text_saying_days_beside_habit_edit_text.setText(" day");
                            } else {
                                text_saying_days_beside_habit_edit_text.setText(" days");
                            }
                        } else {
                            text_saying_days_beside_habit_edit_text.setText(" days");
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    private void change_color_listen() {
        if (getView() != null) {
            Button button_to_choose_color_of_new_good_habit = getView().findViewById(R.id.button_to_choose_color_of_new_good_habit);
            button_to_choose_color_of_new_good_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bottom_sheet_choose_good_habit_color bottomSheetDialog = new Bottom_sheet_choose_good_habit_color();
                    bottomSheetDialog.setTargetFragment(edit_bad_habits.this, 1000);
                    bottomSheetDialog.show(getActivity().getSupportFragmentManager(), "tag");
                }
            });
        }
    }

    private void pick_icon_button_lsitener() {
        if (getView() != null) {
            Button button_to_choose_icon_of_the_new_good_habit = getView().findViewById(R.id.button_to_choose_icon_of_the_new_good_habit);
            button_to_choose_icon_of_the_new_good_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getView() != null) {
                        Dialog_to_choose_good_habit dialog_to_choose_good_habit = new Dialog_to_choose_good_habit();
                        dialog_to_choose_good_habit.setTargetFragment(edit_bad_habits.this, 100);
                        dialog_to_choose_good_habit.show(getActivity().getSupportFragmentManager(), "bad_habits_main");
                    }
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1000:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        String text = bundle.getString("color", "teal");
                        if (getView() != null) {
                            View view_showing_the_icon_and_its_color = getView().findViewById(R.id.view_showing_the_icon_and_its_color);
                            TextView text_view_asying_this_is_example_of_icon = getView().findViewById(R.id.text_view_asying_this_is_example_of_icon);
                            color = text;
                            if (text.equals("teal")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_dark_green);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#607D8B"));
                            } else if (text.equals("red")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_red);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#e6194B"));
                            } else if (text.equals("green")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_green);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#3cb44b"));
                            } else if (text.equals("blue")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_blue);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#4363d8"));
                            } else if (text.equals("orange")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_orange);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#f58231"));
                            } else if (text.equals("brown")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_brown);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#9A6324"));
                            } else if (text.equals("black")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_black);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#000000"));
                            } else if (text.equals("cyan")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_cyan);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#42d4f4"));
                            } else if (text.equals("lime")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_lime);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#bfef45"));
                            } else if (text.equals("magenta")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_magenta);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#f032e6"));
                            } else if (text.equals("navy")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_navy);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#000075"));
                            } else if (text.equals("pink")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_pink);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#fabed4"));
                            } else if (text.equals("yellow")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_yellow);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#ffe119"));
                            } else if (text.equals("purple")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_purple);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#911eb4"));
                            }
                        }
                    }
                }
                break;
            case 100:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        String text = bundle.getString("icon", "games");
                        if (getView() != null && getContext() != null) {
                            View showing_icon_in_color_good_habits = getView().findViewById(R.id.showing_icon_in_color_good_habits);
                            icon = text;
                            if (text.equals("games")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_videogame_asset_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("social_media")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_stay_current_portrait_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("shopping")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_shopping_cart_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("smoking")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_smoking_rooms_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("alcohol")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_sports_bar_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("lying")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_record_voice_over_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("procrastination")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_restore_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("cross")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_clear_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("coffee")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_local_cafe_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("fast food")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_fastfood_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("money")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_money_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("sleeping")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_airline_seat_individual_suite_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("healing")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_healing_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("work")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_work_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("tv")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_tv_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("couch")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_weekend_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("other")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.happy_face_dark_green).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            }
                        }
                    }
                }
                break;
            case 1001:
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    String text = bundle.getString("habit_name", "Custom");
                    if (getView() != null) {
                        Button submit_data_first_time_button = getView().findViewById(R.id.submit_data_first_time_button);
                        EditText edit_text_showing_habit_name = getView().findViewById(R.id.edit_text_showing_habit_name);
                        if (edit_text_showing_habit_name.getVisibility() != View.VISIBLE) {
                            ConstraintLayout constraintLayout = getView().findViewById(R.id.layout_to_show_under_scroll_view);
                            ConstraintSet constraintSet = new ConstraintSet();
                            constraintSet.clone(constraintLayout);
                            constraintSet.applyTo(constraintLayout);
                            edit_text_showing_habit_name.setVisibility(View.VISIBLE);
                            edit_text_showing_habit_name.setText(text);
                            edit_text_showing_habit_name.setSelection(edit_text_showing_habit_name.getText().toString().length());
                        } else {
                            edit_text_showing_habit_name.setText(text);
                            edit_text_showing_habit_name.setSelection(edit_text_showing_habit_name.getText().toString().length());
                        }
                        if (submit_data_first_time_button.getText().toString().equals("Choose a habit")) {
                            submit_data_first_time_button.setText("Change the habit");
                        }
                        which_spinner_is_chosen = text;
                    }
                }
                break;
            case 22:
                year_global = data.getIntExtra("year", 2001);
                month_global = data.getIntExtra("month", 1);
                day_global = data.getIntExtra("day", 20);
                Universal_time_picker universal_time_picker = new Universal_time_picker();
                universal_time_picker.setTargetFragment(edit_bad_habits.this, 222);
                universal_time_picker.show(getActivity().getSupportFragmentManager(), "time_picker");
                break;
            case 222:
                if (getView() != null && getContext() != null) {
                    int hour = data.getIntExtra("hour", 6);
                    int minute = data.getIntExtra("minute", 30);
                    Button button_that_will_open_time_user_hacked = getView().findViewById(R.id.button_that_will_open_time_user_hacked);
                    TextView text_to_sayy_enter_time_of_last_relapse = getView().findViewById(R.id.text_to_sayy_enter_time_of_last_relapse);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, year_global);
                    calendar.set(Calendar.MONTH, month_global);
                    calendar.set(Calendar.DAY_OF_MONTH, day_global);
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE, minute);
                    Date date = new Date();
                    date.setTime(calendar.getTimeInMillis());
                    String date_in_string = DateFormat.getDateInstance(DateFormat.MEDIUM).format(date);
                    String time_in_string = DateFormat.getTimeInstance(DateFormat.SHORT).format(date);
                    time_in_milli = calendar.getTimeInMillis();
                    button_that_will_open_time_user_hacked.setText("Change Date");
                    text_to_sayy_enter_time_of_last_relapse.setText("Date: ".concat(time_in_string).concat(" ").concat(date_in_string));
                    Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                    text_to_sayy_enter_time_of_last_relapse.setTypeface(boldTypeface);
                    text_to_sayy_enter_time_of_last_relapse.setTextSize(16);
                }
                break;
        }
    }

    private void update_button_listen() {
        if (getView() != null) {
            Button button_to_submit_good_habit_in_habits = getView().findViewById(R.id.button_to_submit_good_habit_in_habits);
            button_to_submit_good_habit_in_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (check_if_done()) {
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("list_of_the_bad_habits", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        String old = sharedPreferences.getString("Bad_habits","");
                        String save_me = "";
                        String[] big_split = old.split("spit_max_for_the_bad_habits");
                        for(int i = 0;i<big_split.length;i++){
                            //String[] small_split = big_split[i].split("split_here_bad_habits");
                            if(i == position){
                                save_me = save_me.concat(return_data_to_save()).concat("spit_max_for_the_bad_habits");
                            } else {
                                save_me = save_me.concat(big_split[i]).concat("spit_max_for_the_bad_habits");
                            }
                        }
                        myEdit.putString("Bad_habits",save_me);
                        myEdit.commit();
                        edit_bad_habits edit_a_habit = (edit_bad_habits) getActivity().getSupportFragmentManager().findFragmentByTag("edit bad habit");
                        View_home_habit view_home = (View_home_habit) getActivity().getSupportFragmentManager().findFragmentByTag("view home");
                        getActivity().getSupportFragmentManager().beginTransaction().show(view_home).remove(edit_a_habit).commit();
                    }
                }
            });
        }
    }

    private void back_button_listen() {
        if (getView() != null && getActivity() != null) {
            Button button_to_go_back_good_habit = getView().findViewById(R.id.button_to_go_back_good_habit);
            final edit_bad_habits edit_a_habit = (edit_bad_habits) getActivity().getSupportFragmentManager().findFragmentByTag("edit bad habit");
            final View_home_habit view_home = (View_home_habit) getActivity().getSupportFragmentManager().findFragmentByTag("view home");
            button_to_go_back_good_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (edit_a_habit != null) {
                        if (getActivity() != null && view_home != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().show(view_home).remove(edit_a_habit).commit();
                        }
                    }
                }
            });
        }
    }

    private boolean check_if_done() {
        EditText edit_text_showing_habit_name = getView().findViewById(R.id.edit_text_showing_habit_name);
        EditText enter_goal_for_new_good_habit_in_habits = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits);
        if (edit_text_showing_habit_name.getVisibility() != View.VISIBLE) {
            Toast.makeText(getActivity(), "Please choose a habit", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edit_text_showing_habit_name.getText().toString().trim().equals("")) {
            Toast.makeText(getActivity(), "Please choose type a habit name", Toast.LENGTH_SHORT).show();
            edit_text_showing_habit_name.setError("Please choose type a habit name");
            return false;
        }
        if (check_if_name_already_exists(edit_text_showing_habit_name.getText().toString().trim())) {
            Toast.makeText(getActivity(), "A habit with the same exact name already exists. PLease change the habit name. You can keep the type", Toast.LENGTH_LONG).show();
            edit_text_showing_habit_name.setError("Change the habit name. You can keep the same type");
            return false;
        }
        if (time_in_milli == 0) {
            Toast.makeText(getActivity(), "Please choose the date that you last did the habit", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (enter_goal_for_new_good_habit_in_habits.getText().toString().equals("") || Integer.parseInt(enter_goal_for_new_good_habit_in_habits.getText().toString()) == 0) {
            if (enter_goal_for_new_good_habit_in_habits.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Goal can't be empty", Toast.LENGTH_SHORT).show();
                enter_goal_for_new_good_habit_in_habits.setError("Goal can't be empty");
            } else {
                Toast.makeText(getActivity(), "Goal can't be zero", Toast.LENGTH_SHORT).show();
                enter_goal_for_new_good_habit_in_habits.setError("Goal can't be zero");
            }
            return false;
        }
        if (color.equals("")) {
            Toast.makeText(getActivity(), "Please choose a color", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (icon.equals("")) {
            Toast.makeText(getActivity(), "Please choose an icon", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean check_if_name_already_exists(String name) {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("list_of_the_bad_habits", MODE_PRIVATE);
            String old = sharedPreferences.getString("Bad_habits", "");
            if (old != null && !old.equals("")) {
                String[] big_split = old.split("spit_max_for_the_bad_habits");
                for (int i = 0; i < big_split.length; i++) {
                    if (i == position) {
                        continue;
                    }
                    String[] small_split = big_split[i].split("split_here_bad_habits");
                    if (name.trim().equals(small_split[1])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private String return_data_to_save() {
        if (getView() != null) {
            EditText edit_text_showing_habit_name = getView().findViewById(R.id.edit_text_showing_habit_name);
            EditText enter_goal_for_new_good_habit_in_habits = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits);
            String save_me = which_spinner_is_chosen;
            save_me = save_me.concat("split_here_bad_habits");
            save_me = save_me.concat(edit_text_showing_habit_name.getText().toString().trim());
            save_me = save_me.concat("split_here_bad_habits");
            save_me = save_me.concat(String.valueOf(time_in_milli));
            save_me = save_me.concat("split_here_bad_habits");
            save_me = save_me.concat(enter_goal_for_new_good_habit_in_habits.getText().toString());
            save_me = save_me.concat("split_here_bad_habits");
            save_me = save_me.concat(color);
            save_me = save_me.concat("split_here_bad_habits");
            save_me = save_me.concat(icon);
            Log.w("save_me",save_me);
            return save_me;
        } else {
            return "";
        }
    }*/