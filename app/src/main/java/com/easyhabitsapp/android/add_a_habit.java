package com.easyhabitsapp.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class add_a_habit extends Fragment {

    private long time_in_milli = 0;
    private String color = "";
    private String icon = "";
    private String which_days_are_chosen;

    public add_a_habit() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_a_habit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        set_up_time_in_hour();
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
    }

    /*private void choose_a_habit_button_listen() {
        if (getView() != null && getActivity() != null) {
            Button submit_data_first_time_button = getView().findViewById(R.id.submit_data_first_time_button);
            submit_data_first_time_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog_asking_which_habit dialog_asking_which_habit = new Dialog_asking_which_habit();
                    dialog_asking_which_habit.setTargetFragment(add_a_habit.this, 1001);
                    dialog_asking_which_habit.show(getActivity().getSupportFragmentManager(), "tag");
                }
            });
        }
    }*/

    private void choose_date_button_listen() {
        if (getView() != null) {
            Button button_that_will_open_time_user_hacked = getView().findViewById(R.id.button_that_will_open_time_user_hacked);
            button_that_will_open_time_user_hacked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null && getView() != null) {
                        Universal_date_picker universal_date_picker = new Universal_date_picker();
                        universal_date_picker.setTargetFragment(add_a_habit.this, 22);
                        universal_date_picker.show(getActivity().getSupportFragmentManager(), "date_picker");
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
                    bottomSheetDialog.setTargetFragment(add_a_habit.this, 1000);
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
                    button_that_will_open_time_user_hacked.setText("Change Date");
                    text_to_sayy_enter_time_of_last_relapse.setText("Date: ".concat(date_in_string));
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
                        dialog_to_choose_good_habit.setTargetFragment(add_a_habit.this, 100);
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
            if (color.equals("")) {
                Toast.makeText(getActivity(), "Please choose a color", Toast.LENGTH_SHORT).show();
                return false;
            }
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
            final add_a_habit add_a_habit = (add_a_habit) getActivity().getSupportFragmentManager().findFragmentByTag("add a habit");
            final home_fragment home_fragment = (com.easyhabitsapp.android.home_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("home");
            button_to_go_back_good_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (add_a_habit != null) {
                        if (getActivity() != null && home_fragment != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().show(home_fragment).remove(add_a_habit).commit();
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

            if (switch_to_turn_notifications_add_a_habit.isChecked()) {
                habits_data_class.setNotifications_on_or_off(true);
                if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 0) {
                    habits_data_class.setNotifications_freq("everyday");
                } else if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 1) {
                    habits_data_class.setNotifications_freq("daysperweek");
                    habits_data_class.setNotifications_freq_extra(show_which_days_are_chosen_add_a_habit_notifications.getText().toString());
                } else if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 2) {
                    habits_data_class.setNotifications_freq("everyndays");
                    habits_data_class.setNotifications_freq_extra(enter_goal_for_new_good_habit_in_habits_add_habit_notification.getText().toString());
                } else if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 3) {
                    habits_data_class.setNotifications_freq("dayspermonth");
                    habits_data_class.setNotifications_freq_extra(check_which_button_is_chosen_notification());
                } else if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 4) {
                    habits_data_class.setNotifications_freq("timesaperiod");
                    habits_data_class.setNotifications_freq_extra(times_per_period_in_add_habit_frequency_notification.getText().toString().concat("_").concat(times_per_period_in_add_habit_frequency_in_the_end_notification.getText().toString()));
                }
                habits_data_class.setNotifications_time(return_time_for_notification());
            } else {
                habits_data_class.setNotifications_on_or_off(false);
            }
            ArrayList<Long> relapse = new ArrayList<>();
            habits_data_class.setRelapse(relapse);
            HashMap<Long,Integer> hasmap = new HashMap<>();
            habits_data_class.setRelapse_amount_timer(hasmap);
            Room_database_habits database_habits = Room_database_habits.getInstance(getContext());
            database_habits.dao_for_habits_data().insert(habits_data_class);
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

            if (yes_or_no_add_habit.getText().toString().contains("✓")) {
                habits_data_class.setType_of_the_habit("yes/no");
            } else if (amount_add_habit.getText().toString().contains("✓")) {
                habits_data_class.setType_of_the_habit("amount");
                habits_data_class.setExtra_type_info(Integer.parseInt(edit_text_to_enter_amount_in_add_habit.getText().toString()));
            } else {
                habits_data_class.setType_of_the_habit("timer");
                if (spinner_saying_minutes_or_hours.getSelectedItemPosition() == 0) {
                    habits_data_class.setExtra_type_info(Integer.parseInt(edit_text_to_enter_time_in_add_habit.getText().toString()));
                } else {
                    habits_data_class.setExtra_type_info(Integer.parseInt(edit_text_to_enter_time_in_add_habit.getText().toString())*60);
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

            if (switch_to_turn_notifications_add_a_habit.isChecked()) {
                habits_data_class.setNotifications_on_or_off(true);
                if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 0) {
                    habits_data_class.setNotifications_freq("everyday");
                } else if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 1) {
                    habits_data_class.setNotifications_freq("daysperweek");
                    habits_data_class.setNotifications_freq_extra(show_which_days_are_chosen_add_a_habit_notifications.getText().toString());
                } else if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 2) {
                    habits_data_class.setNotifications_freq("everyndays");
                    habits_data_class.setNotifications_freq_extra(enter_goal_for_new_good_habit_in_habits_add_habit_notification.getText().toString());
                } else if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 3) {
                    habits_data_class.setNotifications_freq("dayspermonth");
                    habits_data_class.setNotifications_freq_extra(check_which_button_is_chosen_notification());
                } else if (spinner_to_choose_the_notification_of_the_habit.getSelectedItemPosition() == 4) {
                    habits_data_class.setNotifications_freq("timesaperiod");
                    habits_data_class.setNotifications_freq_extra(times_per_period_in_add_habit_frequency_notification.getText().toString().concat("_").concat(times_per_period_in_add_habit_frequency_in_the_end_notification.getText().toString()));
                }
                habits_data_class.setNotifications_time(return_time_for_notification());
            } else {
                habits_data_class.setNotifications_on_or_off(false);
            }
            ArrayList<Long> relapse = new ArrayList<>();
            habits_data_class.setRelapse(relapse);

            HashMap<Long,Integer> hasmap = new HashMap<>();
            habits_data_class.setRelapse_amount_timer(hasmap);
            Room_database_habits database_habits = Room_database_habits.getInstance(getContext());
            database_habits.dao_for_habits_data().insert(habits_data_class);
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

    private void set_up_time_in_hour() {
        if (getView() != null) {
            TextView text_view_saying_the_minute_of_notification = getView().findViewById(R.id.text_view_saying_the_minute_of_notification);
            Spinner spinner_to_choose_am_or_pm_in_add_habit_notifactaion_freq = getView().findViewById(R.id.spinner_to_choose_am_or_pm_in_add_habit_notifactaion_freq);
            TextView text_view_saying_the_hour_of_notification = getView().findViewById(R.id.text_view_saying_the_hour_of_notification);
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR);
            text_view_saying_the_hour_of_notification.setText(String.valueOf(hour));
            int am_pm = calendar.get(Calendar.AM_PM);
            if (am_pm == Calendar.AM) {
                spinner_to_choose_am_or_pm_in_add_habit_notifactaion_freq.setSelection(0);
            } else {
                spinner_to_choose_am_or_pm_in_add_habit_notifactaion_freq.setSelection(1);
            }
            int minutes = calendar.get(Calendar.MINUTE);
            if (minutes % 5 < 3) {
                text_view_saying_the_minute_of_notification.setText(String.valueOf(minutes - (minutes % 5)));
            } else {
                text_view_saying_the_minute_of_notification.setText(String.valueOf(minutes - (minutes % 5) + 5));
            }
        }
    }

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
}