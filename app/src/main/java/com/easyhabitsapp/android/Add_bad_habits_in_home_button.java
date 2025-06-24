package com.easyhabitsapp.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.easyhabitsapp.android.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Add_bad_habits_in_home_button extends AppCompatActivity implements Dialog_asking_which_habit.dialog_choosing_habit_listen_cancel {
    private int which_layout_for_buttons = 1;
    private String which_spinner_is_chosen = "";
    private String first_screen_result;
    private String second_screen_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bad_habits_in_home_button);
        set_up_which_button_to_choose();
        hide_top_bar();
        set_the_color_spinner();
        button_listen_next();
        back_button_listen();
        button_listen();
        back_and_finish_button_listen();
        color_the_text_in_top();
        button_choose_date_listen();
        watching_text();
        button_listen_for_final();
    }

    private void change_layout(int which) {
        ConstraintLayout layout_to_add_bad_habits_one = findViewById(R.id.layout_to_add_bad_habits_one);
        ConstraintLayout layout_to_add_bad_habits_two = findViewById(R.id.layout_to_add_bad_habits_two);
        ConstraintLayout layout_to_add_bad_habits_three = findViewById(R.id.layout_to_add_bad_habits_three);
        ScrollView layout_to_add_bad_habits_three_scroll_view = findViewById(R.id.layout_to_add_bad_habits_three_scroll_view);
        if (which == 1) {
            layout_to_add_bad_habits_one.setVisibility(View.VISIBLE);
            layout_to_add_bad_habits_two.setVisibility(View.INVISIBLE);
            layout_to_add_bad_habits_three.setVisibility(View.INVISIBLE);
            layout_to_add_bad_habits_three_scroll_view.setVisibility(View.INVISIBLE);
            which_layout_for_buttons = 1;
        } else if (which == 2) {
            layout_to_add_bad_habits_one.setVisibility(View.INVISIBLE);
            layout_to_add_bad_habits_two.setVisibility(View.VISIBLE);
            layout_to_add_bad_habits_three.setVisibility(View.INVISIBLE);
            layout_to_add_bad_habits_three_scroll_view.setVisibility(View.INVISIBLE);
            which_layout_for_buttons = 2;
        } else if (which == 3) {
            layout_to_add_bad_habits_one.setVisibility(View.INVISIBLE);
            layout_to_add_bad_habits_two.setVisibility(View.INVISIBLE);
            layout_to_add_bad_habits_three.setVisibility(View.VISIBLE);
            layout_to_add_bad_habits_three_scroll_view.setVisibility(View.VISIBLE);

            which_layout_for_buttons = 3;
        }
    }

    private void control_next_back_button() {
        Button next_button_to_next = findViewById(R.id.next_button_to_next);
        Button back_button_to_previous = findViewById(R.id.back_button_to_previous);
        if (which_layout_for_buttons == 1) {
            back_button_to_previous.setVisibility(View.INVISIBLE);
            next_button_to_next.setVisibility(View.VISIBLE);
        } else if (which_layout_for_buttons == 2) {
            back_button_to_previous.setVisibility(View.VISIBLE);
            next_button_to_next.setVisibility(View.VISIBLE);
        } else {
            back_button_to_previous.setVisibility(View.INVISIBLE);
            next_button_to_next.setVisibility(View.INVISIBLE);
        }
    }

    private void hide_top_bar() {
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().hide();
        }
    }

    private void set_up_which_button_to_choose() {
        Button submit_data_first_time_button = findViewById(R.id.submit_data_first_time_button);
        submit_data_first_time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog_asking_which_habit dialog_asking_which_habit = new Dialog_asking_which_habit();
                dialog_asking_which_habit.show(getSupportFragmentManager(), "tag");
            }
        });
    }

    private int dip_to_pixels(float dip) {
        Resources r = getResources();
        float pixels = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );
        return Math.round(pixels);
    }

    private void set_the_color_spinner() {
        ArrayList<Color_item> mcolor_item;
        mcolor_item = new ArrayList<>();
        mcolor_item.add(new Color_item("Teal", R.drawable.z_dark_green));
        mcolor_item.add(new Color_item("Red", R.drawable.z_red));
        mcolor_item.add(new Color_item("Green", R.drawable.z_green));
        mcolor_item.add(new Color_item("Yellow", R.drawable.z_yellow));
        mcolor_item.add(new Color_item("Blue", R.drawable.z_blue));
        mcolor_item.add(new Color_item("Orange", R.drawable.z_orange));
        mcolor_item.add(new Color_item("Purple", R.drawable.z_purple));
        mcolor_item.add(new Color_item("Cyan", R.drawable.z_cyan));
        mcolor_item.add(new Color_item("Magenta", R.drawable.z_magenta));
        mcolor_item.add(new Color_item("Lime", R.drawable.z_lime));
        mcolor_item.add(new Color_item("Pink", R.drawable.z_pink));
        mcolor_item.add(new Color_item("Brown", R.drawable.z_brown));
        mcolor_item.add(new Color_item("Navy", R.drawable.z_navy));
        mcolor_item.add(new Color_item("Black", R.drawable.z_black));
        Color_adapter m_adapter;
        Spinner spinner_to_ask_which_color = findViewById(R.id.spinner_to_ask_which_color);
        m_adapter = new Color_adapter(this, mcolor_item);
        spinner_to_ask_which_color.setAdapter(m_adapter);
        spinner_to_ask_which_color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void button_listen_next() {
        SharedPreferences sharedPreferences = getSharedPreferences("all_the_bad_habits", MODE_PRIVATE);
        final String bad_habits = sharedPreferences.getString("bad_habits", "");
        Button next_button_to_next = findViewById(R.id.next_button_to_next);
        final Button submit_data_first_time_button = findViewById(R.id.submit_data_first_time_button);
        final EditText edit_text_showing_habit_name = findViewById(R.id.edit_text_showing_habit_name);
        next_button_to_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (which_layout_for_buttons == 1) {
                    if (submit_data_first_time_button.getText().toString().equals("Choose a habit")) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please choose a habit", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        if (edit_text_showing_habit_name.getText().toString().trim().equals("")) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Habit name can't be empty", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            if (bad_habits != null && bad_habits.contains(edit_text_showing_habit_name.getText().toString().trim())) {
                                Toast toast = Toast.makeText(getApplicationContext(), "You already have a habit with the same name, please change habit name", Toast.LENGTH_LONG);
                                toast.show();
                            } else if (bad_habits != null && !bad_habits.contains(edit_text_showing_habit_name.getText().toString().trim())) {
                                save_the_info();
                                change_layout(2);
                                control_next_back_button();
                                read_and_set();
                            } else {
                                save_the_info();
                                change_layout(2);
                                control_next_back_button();
                                read_and_set();
                            }
                        }
                    }
                } else if (which_layout_for_buttons == 2) {
                    if (check_if_at_least_one_button()) {
                        save_which_buttons();
                        change_layout(3);
                        control_next_back_button();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please choose at least one feature", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {

                }
            }
        });
    }

    private void save_the_info() {
        EditText edit_text_showing_habit_name = findViewById(R.id.edit_text_showing_habit_name);
        first_screen_result = edit_text_showing_habit_name.getText().toString().trim().concat("__small_split__").concat(which_spinner_is_chosen).concat("__small_split__").concat(return_the_color_name()).concat("##splitting_for_bad_habits##");
    }

    private String return_the_color_name() {
        Spinner spinner_to_ask_which_color = findViewById(R.id.spinner_to_ask_which_color);
        int item_position = spinner_to_ask_which_color.getSelectedItemPosition();
        if (item_position == 0) {
            return "Teal";
        } else if (item_position == 1) {
            return "Red";
        } else if (item_position == 2) {
            return "Green";
        } else if (item_position == 3) {
            return "Yellow";
        } else if (item_position == 4) {
            return "Blue";
        } else if (item_position == 5) {
            return "Orange";
        } else if (item_position == 6) {
            return "Purple";
        } else if (item_position == 7) {
            return "Cyan";
        } else if (item_position == 8) {
            return "Magenta";
        } else if (item_position == 9) {
            return "Lime";
        } else if (item_position == 10) {
            return "Pink";
        } else if (item_position == 11) {
            return "Brown";
        } else if (item_position == 12) {
            return "Navy";
        } else {
            return "Black";
        }
    }

    @Override
    public void on_habit_choose(String text) {
        Button submit_data_first_time_button = findViewById(R.id.submit_data_first_time_button);
        EditText edit_text_showing_habit_name = findViewById(R.id.edit_text_showing_habit_name);
        TextView text_asking_to_choose_a_color = findViewById(R.id.text_asking_to_choose_a_color);
        if (edit_text_showing_habit_name.getVisibility() == View.INVISIBLE) {
            ConstraintLayout constraintLayout = findViewById(R.id.layout_to_add_bad_habits_one);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(text_asking_to_choose_a_color.getId(), ConstraintSet.TOP, edit_text_showing_habit_name.getId(), ConstraintSet.BOTTOM, dip_to_pixels(30));
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

    private void back_button_listen() {
        Button back_button_to_previous = findViewById(R.id.back_button_to_previous);
        back_button_to_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_layout(which_layout_for_buttons - 1);
                control_next_back_button();
            }
        });
    }

    private void read_and_set() {
        String[] splitter = first_screen_result.split("__small_split__");
        select_the_buttons(1);
        if (splitter[1].toLowerCase().equals("video games")) {
            select_the_buttons(4);
            select_the_buttons(5);
            select_the_buttons(7);
            select_the_buttons(9);
        } else if (splitter[1].toLowerCase().equals("social media")) {
            select_the_buttons(2);
            select_the_buttons(6);
            select_the_buttons(8);
            select_the_buttons(9);
        } else if (splitter[1].toLowerCase().equals("impulsive shopping")) {
            select_the_buttons(2);
            select_the_buttons(9);
        } else if (splitter[1].toLowerCase().equals("porn / masturbating")) {
            select_the_buttons(2);
            select_the_buttons(3);
            select_the_buttons(4);
            select_the_buttons(5);
            select_the_buttons(6);
            select_the_buttons(7);
            select_the_buttons(9);
        } else if (splitter[1].toLowerCase().equals("smoking")) {
            select_the_buttons(4);
            select_the_buttons(5);
            select_the_buttons(6);
            select_the_buttons(7);
            select_the_buttons(9);
        } else if (splitter[1].toLowerCase().equals("alcohol")) {
            select_the_buttons(5);
            select_the_buttons(6);
            select_the_buttons(7);
            select_the_buttons(9);
        } else if (splitter[1].toLowerCase().equals("drugs")) {
            select_the_buttons(5);
            select_the_buttons(6);
            select_the_buttons(7);
            select_the_buttons(9);
        } else if (splitter[1].toLowerCase().equals("cursing")) {
            select_the_buttons(10);
        } else if (splitter[1].toLowerCase().equals("procrastination")) {
            select_the_buttons(10);
        } else if (splitter[1].toLowerCase().equals("lying")) {
            select_the_buttons(10);
        } else if (splitter[1].toLowerCase().equals("fast food")) {
            select_the_buttons(4);
            select_the_buttons(5);
            select_the_buttons(7);
            select_the_buttons(9);
            select_the_buttons(11);
        } else if (splitter[1].toLowerCase().equals("sugar")) {
            select_the_buttons(4);
            select_the_buttons(5);
            select_the_buttons(7);
            select_the_buttons(9);
            select_the_buttons(11);
        } else if (splitter[1].toLowerCase().equals("over eating")) {
            select_the_buttons(4);
            select_the_buttons(5);
            select_the_buttons(7);
            select_the_buttons(9);
            select_the_buttons(11);
        } else if (splitter[1].toLowerCase().equals("gambling")) {
            select_the_buttons(2);
            select_the_buttons(6);
            select_the_buttons(7);
            select_the_buttons(9);
        }
    }

    private void select_the_buttons(int which) {
        Button journal_button_on_off_at_start = findViewById(R.id.journal_button_on_off_at_start);
        Button lock_phone_button_on_off_at_start = findViewById(R.id.lock_phone_button_on_off_at_start);
        Button cold_shower_button_on_off_at_start = findViewById(R.id.cold_shower_button_on_off_at_start);
        Button push_ups_button_on_off_at_start = findViewById(R.id.push_ups_button_on_off_at_start);
        Button running_or_biking_phone_button_on_off_at_start = findViewById(R.id.running_or_biking_phone_button_on_off_at_start);
        Button mediation_button_on_off_at_start = findViewById(R.id.mediation_button_on_off_at_start);
        Button motivational_quotes_button_on_off_at_start = findViewById(R.id.motivational_quotes_button_on_off_at_start);
        Button view_total_phone_usage_phone_button_on_off_at_start = findViewById(R.id.view_total_phone_usage_phone_button_on_off_at_start);
        Button view_user_submitted_stories_button_on_off_at_start = findViewById(R.id.view_user_submitted_stories_button_on_off_at_start);
        Button counter_button_on_off_at_start = findViewById(R.id.counter_button_on_off_at_start);
        Button weight_tracker_button_on_off_at_start = findViewById(R.id.weight_tracker_button_on_off_at_start);
        if (which == 1) {
            journal_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            journal_button_on_off_at_start.setTextColor(Color.WHITE);
            journal_button_on_off_at_start.setText(journal_button_on_off_at_start.getText().toString().replace(" ✓", "").concat(" ✓"));
        } else if (which == 2) {
            lock_phone_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            lock_phone_button_on_off_at_start.setTextColor(Color.WHITE);
            lock_phone_button_on_off_at_start.setText(lock_phone_button_on_off_at_start.getText().toString().replace(" ✓", "").concat(" ✓"));
        } else if (which == 3) {
            cold_shower_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            cold_shower_button_on_off_at_start.setTextColor(Color.WHITE);
            cold_shower_button_on_off_at_start.setText(cold_shower_button_on_off_at_start.getText().toString().replace(" ✓", "").concat(" ✓"));
        } else if (which == 4) {
            push_ups_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            push_ups_button_on_off_at_start.setTextColor(Color.WHITE);
            push_ups_button_on_off_at_start.setText(push_ups_button_on_off_at_start.getText().toString().replace(" ✓", "").concat(" ✓"));
        } else if (which == 5) {
            running_or_biking_phone_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            running_or_biking_phone_button_on_off_at_start.setTextColor(Color.WHITE);
            running_or_biking_phone_button_on_off_at_start.setText(running_or_biking_phone_button_on_off_at_start.getText().toString().replace(" ✓", "").concat(" ✓"));
        } else if (which == 6) {
            mediation_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            mediation_button_on_off_at_start.setTextColor(Color.WHITE);
            mediation_button_on_off_at_start.setText(mediation_button_on_off_at_start.getText().toString().replace(" ✓", "").concat(" ✓"));
        } else if (which == 7) {
            motivational_quotes_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            motivational_quotes_button_on_off_at_start.setTextColor(Color.WHITE);
            motivational_quotes_button_on_off_at_start.setText(motivational_quotes_button_on_off_at_start.getText().toString().replace(" ✓", "").concat(" ✓"));
        } else if (which == 8) {
            view_total_phone_usage_phone_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            view_total_phone_usage_phone_button_on_off_at_start.setTextColor(Color.WHITE);
            view_total_phone_usage_phone_button_on_off_at_start.setText(view_total_phone_usage_phone_button_on_off_at_start.getText().toString().replace(" ✓", "").concat(" ✓"));
        } else if (which == 9) {
            view_user_submitted_stories_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            view_user_submitted_stories_button_on_off_at_start.setTextColor(Color.WHITE);
            view_user_submitted_stories_button_on_off_at_start.setText(view_user_submitted_stories_button_on_off_at_start.getText().toString().replace(" ✓", "").concat(" ✓"));
        } else if (which == 10) {
            counter_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            counter_button_on_off_at_start.setTextColor(Color.WHITE);
            counter_button_on_off_at_start.setText(counter_button_on_off_at_start.getText().toString().replace(" ✓", "").concat(" ✓"));
        } else if (which == 11) {
            weight_tracker_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            weight_tracker_button_on_off_at_start.setTextColor(Color.WHITE);
            weight_tracker_button_on_off_at_start.setText(weight_tracker_button_on_off_at_start.getText().toString().replace(" ✓", "").concat(" ✓"));
        }
    }

    private void remove_some_buttons(int which) {
        Button journal_button_on_off_at_start = findViewById(R.id.journal_button_on_off_at_start);
        Button lock_phone_button_on_off_at_start = findViewById(R.id.lock_phone_button_on_off_at_start);
        Button cold_shower_button_on_off_at_start = findViewById(R.id.cold_shower_button_on_off_at_start);
        Button push_ups_button_on_off_at_start = findViewById(R.id.push_ups_button_on_off_at_start);
        Button running_or_biking_phone_button_on_off_at_start = findViewById(R.id.running_or_biking_phone_button_on_off_at_start);
        Button mediation_button_on_off_at_start = findViewById(R.id.mediation_button_on_off_at_start);
        Button motivational_quotes_button_on_off_at_start = findViewById(R.id.motivational_quotes_button_on_off_at_start);
        Button view_total_phone_usage_phone_button_on_off_at_start = findViewById(R.id.view_total_phone_usage_phone_button_on_off_at_start);
        Button view_user_submitted_stories_button_on_off_at_start = findViewById(R.id.view_user_submitted_stories_button_on_off_at_start);
        Button counter_button_on_off_at_start = findViewById(R.id.counter_button_on_off_at_start);
        Button weight_tracker_button_on_off_at_start = findViewById(R.id.weight_tracker_button_on_off_at_start);
        if (which == 1) {
            journal_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            journal_button_on_off_at_start.setTextColor(Color.BLACK);
            journal_button_on_off_at_start.setText(journal_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        } else if (which == 2) {
            lock_phone_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            lock_phone_button_on_off_at_start.setTextColor(Color.BLACK);
            lock_phone_button_on_off_at_start.setText(lock_phone_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        } else if (which == 3) {
            cold_shower_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            cold_shower_button_on_off_at_start.setTextColor(Color.BLACK);
            cold_shower_button_on_off_at_start.setText(cold_shower_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        } else if (which == 4) {
            push_ups_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            push_ups_button_on_off_at_start.setTextColor(Color.BLACK);
            push_ups_button_on_off_at_start.setText(push_ups_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        } else if (which == 5) {
            running_or_biking_phone_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            running_or_biking_phone_button_on_off_at_start.setTextColor(Color.BLACK);
            running_or_biking_phone_button_on_off_at_start.setText(running_or_biking_phone_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        } else if (which == 6) {
            mediation_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            mediation_button_on_off_at_start.setTextColor(Color.BLACK);
            mediation_button_on_off_at_start.setText(mediation_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        } else if (which == 7) {
            motivational_quotes_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            motivational_quotes_button_on_off_at_start.setTextColor(Color.BLACK);
            motivational_quotes_button_on_off_at_start.setText(motivational_quotes_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        } else if (which == 8) {
            view_total_phone_usage_phone_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            view_total_phone_usage_phone_button_on_off_at_start.setTextColor(Color.BLACK);
            view_total_phone_usage_phone_button_on_off_at_start.setText(view_total_phone_usage_phone_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        } else if (which == 9) {
            view_user_submitted_stories_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            view_user_submitted_stories_button_on_off_at_start.setTextColor(Color.BLACK);
            view_user_submitted_stories_button_on_off_at_start.setText(view_user_submitted_stories_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        } else if (which == 10) {
            counter_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            counter_button_on_off_at_start.setTextColor(Color.BLACK);
            counter_button_on_off_at_start.setText(counter_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        } else if (which == 11) {
            weight_tracker_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            weight_tracker_button_on_off_at_start.setTextColor(Color.BLACK);
            weight_tracker_button_on_off_at_start.setText(weight_tracker_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        }
    }

    private void button_listen() {
        final Button journal_button_on_off_at_start = findViewById(R.id.journal_button_on_off_at_start);
        final Button lock_phone_button_on_off_at_start = findViewById(R.id.lock_phone_button_on_off_at_start);
        final Button cold_shower_button_on_off_at_start = findViewById(R.id.cold_shower_button_on_off_at_start);
        final Button push_ups_button_on_off_at_start = findViewById(R.id.push_ups_button_on_off_at_start);
        final Button running_or_biking_phone_button_on_off_at_start = findViewById(R.id.running_or_biking_phone_button_on_off_at_start);
        final Button mediation_button_on_off_at_start = findViewById(R.id.mediation_button_on_off_at_start);
        final Button motivational_quotes_button_on_off_at_start = findViewById(R.id.motivational_quotes_button_on_off_at_start);
        final Button view_total_phone_usage_phone_button_on_off_at_start = findViewById(R.id.view_total_phone_usage_phone_button_on_off_at_start);
        final Button view_user_submitted_stories_button_on_off_at_start = findViewById(R.id.view_user_submitted_stories_button_on_off_at_start);
        final Button counter_button_on_off_at_start = findViewById(R.id.counter_button_on_off_at_start);
        final Button weight_tracker_button_on_off_at_start = findViewById(R.id.weight_tracker_button_on_off_at_start);
        journal_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (journal_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(1);
                } else {
                    select_the_buttons(1);
                }
            }
        });
        lock_phone_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lock_phone_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(2);
                } else {
                    select_the_buttons(2);
                }
            }
        });
        cold_shower_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cold_shower_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(3);
                } else {
                    select_the_buttons(3);
                }
            }
        });
        push_ups_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (push_ups_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(4);
                } else {
                    select_the_buttons(4);
                }
            }
        });
        running_or_biking_phone_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (running_or_biking_phone_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(5);
                } else {
                    select_the_buttons(5);
                }
            }
        });
        mediation_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediation_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(6);
                } else {
                    select_the_buttons(6);
                }
            }
        });
        motivational_quotes_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (motivational_quotes_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(7);
                } else {
                    select_the_buttons(7);
                }
            }
        });
        view_total_phone_usage_phone_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view_total_phone_usage_phone_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(8);
                } else {
                    select_the_buttons(8);
                }
            }
        });
        view_user_submitted_stories_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view_user_submitted_stories_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(9);
                } else {
                    select_the_buttons(9);
                }
            }
        });
        counter_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(10);
                } else {
                    select_the_buttons(10);
                }
            }
        });
        weight_tracker_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (weight_tracker_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(11);
                } else {
                    select_the_buttons(11);
                }
            }
        });
    }

    private void save_which_buttons() {
        String save_me = "";
        Button journal_button_on_off_at_start = findViewById(R.id.journal_button_on_off_at_start);
        Button lock_phone_button_on_off_at_start = findViewById(R.id.lock_phone_button_on_off_at_start);
        Button cold_shower_button_on_off_at_start = findViewById(R.id.cold_shower_button_on_off_at_start);
        Button push_ups_button_on_off_at_start = findViewById(R.id.push_ups_button_on_off_at_start);
        Button running_or_biking_phone_button_on_off_at_start = findViewById(R.id.running_or_biking_phone_button_on_off_at_start);
        Button mediation_button_on_off_at_start = findViewById(R.id.mediation_button_on_off_at_start);
        Button motivational_quotes_button_on_off_at_start = findViewById(R.id.motivational_quotes_button_on_off_at_start);
        Button view_total_phone_usage_phone_button_on_off_at_start = findViewById(R.id.view_total_phone_usage_phone_button_on_off_at_start);
        Button view_user_submitted_stories_button_on_off_at_start = findViewById(R.id.view_user_submitted_stories_button_on_off_at_start);
        Button counter_button_on_off_at_start = findViewById(R.id.counter_button_on_off_at_start);
        Button weight_tracker_button_on_off_at_start = findViewById(R.id.weight_tracker_button_on_off_at_start);
        //String save_me = edit_text_showing_habit_name.getText().toString().concat("__small_split__").concat(which_spinner_is_chosen).concat("__small_split__").concat(return_the_color_name()).concat("##splitting_for_bad_habits##");
        if (journal_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(journal_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        if (lock_phone_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(lock_phone_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        if (cold_shower_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(cold_shower_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        if (push_ups_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(push_ups_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        if (running_or_biking_phone_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(running_or_biking_phone_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        if (mediation_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(mediation_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        if (motivational_quotes_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(motivational_quotes_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        if (view_total_phone_usage_phone_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(view_total_phone_usage_phone_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        if (view_user_submitted_stories_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(view_user_submitted_stories_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        if (counter_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(counter_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        if (weight_tracker_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(weight_tracker_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        second_screen_result = save_me;
    }

    private Boolean check_if_at_least_one_button() {
        Button journal_button_on_off_at_start = findViewById(R.id.journal_button_on_off_at_start);
        Button lock_phone_button_on_off_at_start = findViewById(R.id.lock_phone_button_on_off_at_start);
        Button cold_shower_button_on_off_at_start = findViewById(R.id.cold_shower_button_on_off_at_start);
        Button push_ups_button_on_off_at_start = findViewById(R.id.push_ups_button_on_off_at_start);
        Button running_or_biking_phone_button_on_off_at_start = findViewById(R.id.running_or_biking_phone_button_on_off_at_start);
        Button mediation_button_on_off_at_start = findViewById(R.id.mediation_button_on_off_at_start);
        Button motivational_quotes_button_on_off_at_start = findViewById(R.id.motivational_quotes_button_on_off_at_start);
        Button view_total_phone_usage_phone_button_on_off_at_start = findViewById(R.id.view_total_phone_usage_phone_button_on_off_at_start);
        Button view_user_submitted_stories_button_on_off_at_start = findViewById(R.id.view_user_submitted_stories_button_on_off_at_start);
        Button counter_button_on_off_at_start = findViewById(R.id.counter_button_on_off_at_start);
        Button weight_tracker_button_on_off_at_start = findViewById(R.id.weight_tracker_button_on_off_at_start);
        if (journal_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (lock_phone_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (cold_shower_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (push_ups_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (running_or_biking_phone_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (mediation_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (motivational_quotes_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (view_total_phone_usage_phone_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (view_user_submitted_stories_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (counter_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (weight_tracker_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        }
        return false;
    }

    private void back_and_finish_button_listen() {
        Button button_for_back_in_main = findViewById(R.id.button_for_back_in_main);
        Button submit_data_first_time_button_for_third_screen = findViewById(R.id.submit_data_first_time_button_for_third_screen);
        button_for_back_in_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_layout(which_layout_for_buttons - 1);
                control_next_back_button();
            }
        });
        submit_data_first_time_button_for_third_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void color_the_text_in_top() {
        TextView text_saying_getting_there_in_the_top = findViewById(R.id.text_saying_getting_there_in_the_top);
        Spannable wordtoSpan = new SpannableString("Getting there!!");
        wordtoSpan.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 8, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_saying_getting_there_in_the_top.setText(wordtoSpan);
    }

    private void button_choose_date_listen() {
        Button button_that_will_open_time_user_hacked = findViewById(R.id.button_that_will_open_time_user_hacked);
        button_that_will_open_time_user_hacked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText enter_streak_edittext = findViewById(R.id.enter_streak_edittext);
                enter_streak_edittext.clearFocus();
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), "date_picker");
            }
        });
    }

    private void watching_text() {
        final EditText enter_streak_edittext = findViewById(R.id.enter_streak_edittext);
        enter_streak_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (enter_streak_edittext.hasFocus()) {
                    Button button_that_will_open_time_user_hacked = findViewById(R.id.button_that_will_open_time_user_hacked);
                    if (button_that_will_open_time_user_hacked.getCurrentTextColor() == -1) {
                        button_that_will_open_time_user_hacked.setBackgroundResource(R.drawable.color_for_botton_off);
                        button_that_will_open_time_user_hacked.setTextColor(Color.parseColor("#607D8B"));
                        ConstraintLayout constraintLayout = findViewById(R.id.main_activity_constraint_layout);
                        ConstraintSet constraintSet = new ConstraintSet();
                        constraintSet.clone(constraintLayout);
                        View empty_view_for_padding2_main_activity = findViewById(R.id.empty_view_for_padding2_main_activity);
                        constraintSet.clear(empty_view_for_padding2_main_activity.getId(), ConstraintSet.TOP);
                        constraintSet.connect(empty_view_for_padding2_main_activity.getId(), ConstraintSet.TOP, button_that_will_open_time_user_hacked.getId(), ConstraintSet.BOTTOM, dip_to_pixels(10));
                        constraintSet.applyTo(constraintLayout);
                        TextView showing_the_date_to_the_user = findViewById(R.id.showing_the_date_to_the_user);
                        showing_the_date_to_the_user.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private boolean check_the_goals() {
        EditText goal1_enter_for_first_time = findViewById(R.id.goal1_enter_for_first_time);
        EditText goal2_enter_for_first_time = findViewById(R.id.goal2_enter_for_first_time);
        EditText goal3_enter_for_first_time = findViewById(R.id.goal3_enter_for_first_time);
        EditText goal4_enter_for_first_time = findViewById(R.id.goal4_enter_for_first_time);
        EditText goal5_enter_for_first_time = findViewById(R.id.goal5_enter_for_first_time);
        int goal1;
        int goal2;
        int goal3;
        int goal4;
        int goal5;
        if (goal1_enter_for_first_time.getText().toString().length() > 0) {
            goal1 = Integer.parseInt(goal1_enter_for_first_time.getText().toString());
        } else {
            goal1 = 0;
        }
        if (goal2_enter_for_first_time.getText().toString().length() > 0) {
            goal2 = Integer.parseInt(goal2_enter_for_first_time.getText().toString());
        } else {
            goal2 = 0;
        }
        if (goal3_enter_for_first_time.getText().toString().length() > 0) {
            goal3 = Integer.parseInt(goal3_enter_for_first_time.getText().toString());
        } else {
            goal3 = 0;
        }
        if (goal4_enter_for_first_time.getText().toString().length() > 0) {
            goal4 = Integer.parseInt(goal4_enter_for_first_time.getText().toString());
        } else {
            goal4 = 0;
        }
        if (goal5_enter_for_first_time.getText().toString().length() > 0) {
            goal5 = Integer.parseInt(goal5_enter_for_first_time.getText().toString());
        } else {
            goal5 = 0;
        }
        boolean is_there_a_problem = false;
        if (goal1 == goal2) {
            is_there_a_problem = true;
            goal1_enter_for_first_time.setError("Goal one can't be equal to goal two");
            goal2_enter_for_first_time.setError("Goal two can't be equal to goal one");
        }
        if (goal1 == goal3) {
            is_there_a_problem = true;
            goal1_enter_for_first_time.setError("Goal one can't be equal to goal three");
            goal3_enter_for_first_time.setError("Goal three can't be equal to goal one");
        }
        if (goal1 == goal4) {
            is_there_a_problem = true;
            goal1_enter_for_first_time.setError("Goal one can't be equal to goal four");
            goal4_enter_for_first_time.setError("Goal four can't be equal to goal one");
        }
        if (goal1 == goal5) {
            is_there_a_problem = true;
            goal1_enter_for_first_time.setError("Goal one can't be equal to goal five");
            goal5_enter_for_first_time.setError("Goal five can't be equal to goal one");
        }
        if (goal2 == goal3) {
            is_there_a_problem = true;
            goal2_enter_for_first_time.setError("Goal two can't be equal to goal three");
            goal3_enter_for_first_time.setError("Goal three can't be equal to goal two");
        }
        if (goal2 == goal4) {
            is_there_a_problem = true;
            goal2_enter_for_first_time.setError("Goal two can't be equal to goal four");
            goal4_enter_for_first_time.setError("Goal four can't be equal to goal two");
        }
        if (goal2 == goal5) {
            is_there_a_problem = true;
            goal2_enter_for_first_time.setError("Goal two can't be equal to goal five");
            goal5_enter_for_first_time.setError("Goal five can't be equal to goal two");
        }
        if (goal3 == goal4) {
            is_there_a_problem = true;
            goal3_enter_for_first_time.setError("Goal three can't be equal to goal four");
            goal4_enter_for_first_time.setError("Goal four can't be equal to goal three");
        }
        if (goal3 == goal5) {
            is_there_a_problem = true;
            goal3_enter_for_first_time.setError("Goal three can't be equal to goal five");
            goal5_enter_for_first_time.setError("Goal five can't be equal to goal three");
        }
        if (goal4 == goal5) {
            is_there_a_problem = true;
            goal4_enter_for_first_time.setError("Goal four can't be equal to goal five");
            goal5_enter_for_first_time.setError("Goal five can't be equal to goal four");
        }
        if (!is_there_a_problem) {
            return true;
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Goals can't be matching", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
    }

    private boolean check_the_goal_int() {
        EditText goal1_enter_for_first_time = findViewById(R.id.goal1_enter_for_first_time);
        EditText goal2_enter_for_first_time = findViewById(R.id.goal2_enter_for_first_time);
        EditText goal3_enter_for_first_time = findViewById(R.id.goal3_enter_for_first_time);
        EditText goal4_enter_for_first_time = findViewById(R.id.goal4_enter_for_first_time);
        EditText goal5_enter_for_first_time = findViewById(R.id.goal5_enter_for_first_time);
        int goal1;
        int goal2;
        int goal3;
        int goal4;
        int goal5;
        if (goal1_enter_for_first_time.getText().toString().length() > 0) {
            goal1 = Integer.parseInt(goal1_enter_for_first_time.getText().toString());
        } else {
            goal1 = 0;
        }
        if (goal2_enter_for_first_time.getText().toString().length() > 0) {
            goal2 = Integer.parseInt(goal2_enter_for_first_time.getText().toString());
        } else {
            goal2 = 0;
        }
        if (goal3_enter_for_first_time.getText().toString().length() > 0) {
            goal3 = Integer.parseInt(goal3_enter_for_first_time.getText().toString());
        } else {
            goal3 = 0;
        }
        if (goal4_enter_for_first_time.getText().toString().length() > 0) {
            goal4 = Integer.parseInt(goal4_enter_for_first_time.getText().toString());
        } else {
            goal4 = 0;
        }
        if (goal5_enter_for_first_time.getText().toString().length() > 0) {
            goal5 = Integer.parseInt(goal5_enter_for_first_time.getText().toString());
        } else {
            goal5 = 0;
        }
        if (goal1 != 0 && goal2 != 0 && goal3 != 0 && goal4 != 0 && goal5 != 0 && goal1 < 10000 && goal2 < 10000 && goal3 < 10000 && goal4 < 10000 && goal5 < 10000) {
            return true;
        } else {
            if (goal1 == 0) {
                goal1_enter_for_first_time.setError("Goal cant be set to zero");
            }
            if (goal2 == 0) {
                goal2_enter_for_first_time.setError("Goal cant be set to zero");
            }
            if (goal3 == 0) {
                goal3_enter_for_first_time.setError("Goal cant be set to zero");
            }
            if (goal4 == 0) {
                goal4_enter_for_first_time.setError("Goal cant be set to zero");
            }
            if (goal5 == 0) {
                goal5_enter_for_first_time.setError("Goal cant be set to zero");
            }
            if (goal1 > 10000) {
                goal1_enter_for_first_time.setError("Goal cant be greater than 10000");
            }
            if (goal2 > 10000) {
                goal2_enter_for_first_time.setError("Goal cant be greater than 10000");
            }
            if (goal3 > 10000) {
                goal3_enter_for_first_time.setError("Goal cant be greater than 10000");
            }
            if (goal4 > 10000) {
                goal4_enter_for_first_time.setError("Goal cant be greater than 10000");
            }
            if (goal5 > 10000) {
                goal5_enter_for_first_time.setError("Goal cant be greater than 10000");
            }
            if (goal1 == 0 || goal2 == 0 || goal3 == 0 || goal4 == 0 || goal5 == 0) {
                Toast toast = Toast.makeText(getApplicationContext(), "Goals can't be zero", Toast.LENGTH_LONG);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Goals can't be greater than 10000 days", Toast.LENGTH_LONG);
                toast.show();
            }
            return false;
        }
    }

    private boolean check_if_streak_is_bigger() {
        EditText goal1_enter_for_first_time = findViewById(R.id.goal1_enter_for_first_time);
        EditText goal2_enter_for_first_time = findViewById(R.id.goal2_enter_for_first_time);
        EditText goal3_enter_for_first_time = findViewById(R.id.goal3_enter_for_first_time);
        EditText goal4_enter_for_first_time = findViewById(R.id.goal4_enter_for_first_time);
        EditText goal5_enter_for_first_time = findViewById(R.id.goal5_enter_for_first_time);
        int goal1;
        int goal2;
        int goal3;
        int goal4;
        int goal5;
        if (goal1_enter_for_first_time.getText().toString().length() > 0) {
            goal1 = Integer.parseInt(goal1_enter_for_first_time.getText().toString());
        } else {
            goal1 = 0;
        }
        if (goal2_enter_for_first_time.getText().toString().length() > 0) {
            goal2 = Integer.parseInt(goal2_enter_for_first_time.getText().toString());
        } else {
            goal2 = 0;
        }
        if (goal3_enter_for_first_time.getText().toString().length() > 0) {
            goal3 = Integer.parseInt(goal3_enter_for_first_time.getText().toString());
        } else {
            goal3 = 0;
        }
        if (goal4_enter_for_first_time.getText().toString().length() > 0) {
            goal4 = Integer.parseInt(goal4_enter_for_first_time.getText().toString());
        } else {
            goal4 = 0;
        }
        if (goal5_enter_for_first_time.getText().toString().length() > 0) {
            goal5 = Integer.parseInt(goal5_enter_for_first_time.getText().toString());
        } else {
            goal5 = 0;
        }
        long time_left_in_days = return_the_streak();
        if (time_left_in_days < goal1 && time_left_in_days < goal2 && time_left_in_days < goal3 && time_left_in_days < goal4 && time_left_in_days < goal5) {
            return true;
        } else {
            if (goal1 == time_left_in_days || goal2 == time_left_in_days || goal3 == time_left_in_days || goal4 == time_left_in_days || goal5 == time_left_in_days) {
                if (goal1 == time_left_in_days) {
                    goal1_enter_for_first_time.setError("Goal cant be equal to the current streak. Please increase it");
                }
                if (goal2 == time_left_in_days) {
                    goal2_enter_for_first_time.setError("Goal cant be equal to the current streak. Please increase it");
                }
                if (goal3 == time_left_in_days) {
                    goal3_enter_for_first_time.setError("Goal cant be equal to the current streak. Please increase it");
                }
                if (goal4 == time_left_in_days) {
                    goal4_enter_for_first_time.setError("Goal cant be equal to the current streak. Please increase it");
                }
                if (goal5 == time_left_in_days) {
                    goal5_enter_for_first_time.setError("Goal cant be equal to the current streak. Please increase it");
                }
            } else {
                if (goal1 < time_left_in_days) {
                    goal1_enter_for_first_time.setError("Goal cant be less than the current streak. Please increase it");
                }
                if (goal2 < time_left_in_days) {
                    goal2_enter_for_first_time.setError("Goal cant be less than the current streak. Please increase it");
                }
                if (goal3 < time_left_in_days) {
                    goal3_enter_for_first_time.setError("Goal cant be less than the current streak. Please increase it");
                }
                if (goal4 < time_left_in_days) {
                    goal4_enter_for_first_time.setError("Goal cant be less than the current streak. Please increase it");
                }
                if (goal5 < time_left_in_days) {
                    goal5_enter_for_first_time.setError("Goal cant be less than the current streak. Please increase it");
                }
            }
            return false;
        }
    }

    private void button_listen_for_final() {
        Button submit_data_first_time_button_for_third_screen = findViewById(R.id.submit_data_first_time_button_for_third_screen);
        submit_data_first_time_button_for_third_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_is_pressed();
            }
        });
    }

    private void button_is_pressed() {
        if (check_if_date_is_chosen()) {
            if (check_the_goal_int()) {
                if (check_the_goals()) {
                    if (check_if_streak_is_bigger()) {
                        SharedPreferences sharedPreferences = getSharedPreferences("main_streak", MODE_PRIVATE);
                        SharedPreferences.Editor my_edit = sharedPreferences.edit();
                        String[] small_split = first_screen_result.split("__small_split__");
                        String save_me = "first_time_split_here_first_time_split_here_first_time_split_here_first_time_split_here_first_time_split_here_".concat(String.valueOf(sharedPreferences.getLong("time_in_milli", 0))).concat("_split_max_here_");
                        my_edit.putString(small_split[0], save_me);
                        my_edit.commit();
                        save_the_goals();
                        SharedPreferences sharedPreferences_for_habit = getSharedPreferences("all_the_bad_habits", MODE_PRIVATE);
                        String bad_habits = sharedPreferences_for_habit.getString("bad_habits", "");
                        SharedPreferences.Editor edit_for_habit = sharedPreferences_for_habit.edit();
                        if (bad_habits != null) {
                            edit_for_habit.putString("bad_habits", bad_habits.concat(first_screen_result));
                        } else {
                            edit_for_habit.putString("bad_habits", first_screen_result);
                        }
                        edit_for_habit.commit();
                        second_screen_result = second_screen_result.concat("##splitting_for_bad_habits##");
                        SharedPreferences sharedPreferences_for_second = getSharedPreferences("emergency_options", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences_for_second.edit();
                        String old = sharedPreferences_for_second.getString("emergency", "");
                        if (old != null) {
                            myEdit.putString("emergency", old.concat(second_screen_result));
                        } else {
                            myEdit.putString("emergency", second_screen_result);
                        }
                        myEdit.commit();
                        Intent intent = new Intent(Add_bad_habits_in_home_button.this, after_login.class);
                        finish();
                        startActivity(intent);
                        //overridePendingTransition(R.anim.swipe_right,R.anim.swipe_out_left);
                    }
                }
            }
        }
    }

    private boolean check_if_date_is_chosen() {
        SharedPreferences sharedPreferences = getSharedPreferences("main_streak", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        EditText enter_streak_edittext = findViewById(R.id.enter_streak_edittext);
        Button button_that_will_open_time_user_hacked = findViewById(R.id.button_that_will_open_time_user_hacked);
        if ((enter_streak_edittext.getText().toString().length() > 0 || (button_that_will_open_time_user_hacked.getBackground().getConstantState() == getResources().getDrawable(R.drawable.color_for_botton_on).getConstantState()))) {
            if (enter_streak_edittext.getText().toString().length() > 0) {
                Calendar calender = Calendar.getInstance();
                //calender.add(Calendar.DAY_OF_MONTH, 0);
                calender.set(Calendar.HOUR_OF_DAY, 0);
                calender.set(Calendar.MINUTE, 0);
                calender.set(Calendar.SECOND, 0);
                calender.set(Calendar.MILLISECOND, 0);
                long days_in_millis = calender.getTimeInMillis() - (86400000 * Integer.parseInt(enter_streak_edittext.getText().toString()));
                myEdit.putLong("time_in_milli", days_in_millis);
                myEdit.apply();
            }
            return true;
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Please enter your streak or data of last relapse", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
    }

    private int return_the_streak() {
        EditText enter_streak_edittext = findViewById(R.id.enter_streak_edittext);
        if (enter_streak_edittext.getText().toString().length() > 0) {
            return Integer.parseInt(enter_streak_edittext.getText().toString());
        } else {
            SharedPreferences shared = getSharedPreferences("main_streak", MODE_PRIVATE);
            long milli = shared.getLong("time_in_milli", 0);
            return (int) TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis() - milli);
        }
    }

    public void save_the_goals() {
        EditText goal1_enter_for_first_time = findViewById(R.id.goal1_enter_for_first_time);
        EditText goal2_enter_for_first_time = findViewById(R.id.goal2_enter_for_first_time);
        EditText goal3_enter_for_first_time = findViewById(R.id.goal3_enter_for_first_time);
        EditText goal4_enter_for_first_time = findViewById(R.id.goal4_enter_for_first_time);
        EditText goal5_enter_for_first_time = findViewById(R.id.goal5_enter_for_first_time);
        int goal1;
        int goal2;
        int goal3;
        int goal4;
        int goal5;
        if (goal1_enter_for_first_time.getText().toString().length() > 0) {
            goal1 = Integer.parseInt(goal1_enter_for_first_time.getText().toString());
        } else {
            goal1 = 0;
        }
        if (goal2_enter_for_first_time.getText().toString().length() > 0) {
            goal2 = Integer.parseInt(goal2_enter_for_first_time.getText().toString());
        } else {
            goal2 = 0;
        }
        if (goal3_enter_for_first_time.getText().toString().length() > 0) {
            goal3 = Integer.parseInt(goal3_enter_for_first_time.getText().toString());
        } else {
            goal3 = 0;
        }
        if (goal4_enter_for_first_time.getText().toString().length() > 0) {
            goal4 = Integer.parseInt(goal4_enter_for_first_time.getText().toString());
        } else {
            goal4 = 0;
        }
        if (goal5_enter_for_first_time.getText().toString().length() > 0) {
            goal5 = Integer.parseInt(goal5_enter_for_first_time.getText().toString());
        } else {
            goal5 = 0;
        }
        ArrayList<Integer> goal_list = new ArrayList<>();
        goal_list.add(goal1);
        goal_list.add(goal2);
        goal_list.add(goal3);
        goal_list.add(goal4);
        goal_list.add(goal5);
        Collections.sort(goal_list);
        String final_goal = "";
        for (int i = 0; i < goal_list.size(); i++) {
            if (i == goal_list.size() - 1) {
                final_goal = final_goal.concat(String.valueOf(goal_list.get(i)));
            } else {
                final_goal = final_goal.concat(String.valueOf(goal_list.get(i))).concat("_");
            }
        }
        SharedPreferences shared = getSharedPreferences("main_goals", MODE_PRIVATE);
        SharedPreferences.Editor edit = shared.edit();
        edit.putString(return_save_name(), final_goal);
        edit.apply();
    }

    private String return_save_name() {
        String[] splitter = first_screen_result.split("__small_split__");
        return splitter[0];
    }

    @Override
    public void onBackPressed() {
        if (which_layout_for_buttons > 1) {
            change_layout(which_layout_for_buttons - 1);
            control_next_back_button();
        } else {
            Intent intent = new Intent(Add_bad_habits_in_home_button.this, after_login.class);
            finish();
            startActivity(intent);
            overridePendingTransition(0, 0);
        }
    }
}