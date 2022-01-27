package com.easyhabitsapp.android;


import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class button_fragment extends Fragment {

    final ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_button_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        check_if_spinner_is_empty();
        display_streak();
        set_text_watcher();
        edit_text_scroll();
        Button yes_i_watched_porn_textview = (Button) getActivity().findViewById(R.id.yes_i_watched_porn_textview);
        yes_i_watched_porn_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button yes_i_watched_porn_textview = (Button) getActivity().findViewById(R.id.yes_i_watched_porn_textview);
                Button no_i_watched_porn_textview = (Button) getActivity().findViewById(R.id.no_i_watched_porn_textview);
                yes_i_watched_porn_textview.setBackgroundResource(R.drawable.color_for_botton_on);
                no_i_watched_porn_textview.setBackgroundResource(R.drawable.color_for_botton_off);
                yes_i_watched_porn_textview.setTextColor(Color.parseColor("#FFFFFF"));
                no_i_watched_porn_textview.setTextColor(Color.parseColor("#607D8B"));
                turn_off_switch();
                turn_on_switch();
            }
        });
        Button no_i_watched_porn_textview = (Button) getActivity().findViewById(R.id.no_i_watched_porn_textview);
        no_i_watched_porn_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button no_i_watched_porn_textview = (Button) getActivity().findViewById(R.id.no_i_watched_porn_textview);
                Button yes_i_watched_porn_textview = (Button) getActivity().findViewById(R.id.yes_i_watched_porn_textview);
                no_i_watched_porn_textview.setBackgroundResource(R.drawable.color_for_botton_on);
                yes_i_watched_porn_textview.setBackgroundResource(R.drawable.color_for_botton_off);
                no_i_watched_porn_textview.setTextColor(Color.parseColor("#FFFFFF"));
                yes_i_watched_porn_textview.setTextColor(Color.parseColor("#607D8B"));
                turn_off_switch();
                turn_on_switch();
            }
        });
        Button none_masturbate = (Button) getActivity().findViewById(R.id.none_masturbate);
        none_masturbate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button none_masturbate = (Button) getActivity().findViewById(R.id.none_masturbate);
                Button once_masturbate = (Button) getActivity().findViewById(R.id.once_masturbate);
                Button custom_masturbate = (Button) getActivity().findViewById(R.id.custom_masturbate);
                none_masturbate.setBackgroundResource(R.drawable.color_for_botton_on);
                once_masturbate.setBackgroundResource(R.drawable.color_for_botton_off);
                custom_masturbate.setBackgroundResource(R.drawable.color_for_botton_off);
                none_masturbate.setTextColor(Color.parseColor("#FFFFFF"));
                once_masturbate.setTextColor(Color.parseColor("#607D8B"));
                custom_masturbate.setTextColor(Color.parseColor("#607D8B"));
                turn_off_switch();
                turn_on_switch();
                custom_masturbate.setText("Custom");
            }
        });
        Button once_masturbate = (Button) getActivity().findViewById(R.id.once_masturbate);
        once_masturbate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button none_masturbate = (Button) getActivity().findViewById(R.id.none_masturbate);
                Button once_masturbate = (Button) getActivity().findViewById(R.id.once_masturbate);
                Button custom_masturbate = (Button) getActivity().findViewById(R.id.custom_masturbate);
                once_masturbate.setBackgroundResource(R.drawable.color_for_botton_on);
                none_masturbate.setBackgroundResource(R.drawable.color_for_botton_off);
                custom_masturbate.setBackgroundResource(R.drawable.color_for_botton_off);
                once_masturbate.setTextColor(Color.parseColor("#FFFFFF"));
                none_masturbate.setTextColor(Color.parseColor("#607D8B"));
                custom_masturbate.setTextColor(Color.parseColor("#607D8B"));
                turn_off_switch();
                turn_on_switch();
                custom_masturbate.setText("Custom");
            }
        });
        Button custom_masturbate = (Button) getActivity().findViewById(R.id.custom_masturbate);
        custom_masturbate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button none_masturbate = (Button) getActivity().findViewById(R.id.none_masturbate);
                Button once_masturbate = (Button) getActivity().findViewById(R.id.once_masturbate);
                Button custom_masturbate = (Button) getActivity().findViewById(R.id.custom_masturbate);
                once_masturbate.setBackgroundResource(R.drawable.color_for_botton_off);
                none_masturbate.setBackgroundResource(R.drawable.color_for_botton_off);
                once_masturbate.setTextColor(Color.parseColor("#607D8B"));
                none_masturbate.setTextColor(Color.parseColor("#607D8B"));
                turn_off_switch();
                turn_on_switch();
                openDialog();
            }
        });
        Button choose_date_for_streak_button = getActivity().findViewById(R.id.choose_date_for_streak_button);
        choose_date_for_streak_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView timer_to_restart_streak_textview = getActivity().findViewById(R.id.timer_to_restart_streak_textview);
                timer_to_restart_streak_textview.setVisibility(View.INVISIBLE);
                Button choose_date_for_streak_button = getActivity().findViewById(R.id.choose_date_for_streak_button);
                choose_date_for_streak_button.setBackgroundResource(R.drawable.color_for_botton_on);
                choose_date_for_streak_button.setTextColor(Color.parseColor("#FFFFFF"));
                Button set_restart_time_now = getActivity().findViewById(R.id.set_restart_time_now);
                set_restart_time_now.setBackgroundResource(R.drawable.color_for_botton_off);
                set_restart_time_now.setTextColor(Color.parseColor("#607D8B"));
                DialogFragment datepicker = new Relapse_date_picker();
                datepicker.show(getActivity().getSupportFragmentManager(), "time picker");
            }
        });
        Button set_restart_time_now = getActivity().findViewById(R.id.set_restart_time_now);
        set_restart_time_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button choose_date_for_streak_button = getActivity().findViewById(R.id.choose_date_for_streak_button);
                choose_date_for_streak_button.setBackgroundResource(R.drawable.color_for_botton_off);
                choose_date_for_streak_button.setTextColor(Color.parseColor("#607D8B"));
                Button set_restart_time_now = getActivity().findViewById(R.id.set_restart_time_now);
                set_restart_time_now.setBackgroundResource(R.drawable.color_for_botton_on);
                set_restart_time_now.setTextColor(Color.parseColor("#FFFFFF"));
                set_time_now();
            }
        });
        Button suubmit_button_to_save_streak_data = getActivity().findViewById(R.id.suubmit_button_to_save_streak_data);
        suubmit_button_to_save_streak_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_all();
            }
        });
    }


    private void turn_off_switch() {
        if (getActivity() != null) {
            Button none_masturbate = getActivity().findViewById(R.id.none_masturbate);
            Button no_i_watched_porn_textview = getActivity().findViewById(R.id.no_i_watched_porn_textview);
            int non_hex_color_for_yes_masturbaite;
            int non_hex_color_for_yes_porn;
            String hex_color_for_yes_masturbaite;
            String hex_color_for_yes_porn;
            Switch switch_to_restart_streak_option = getActivity().findViewById(R.id.switch_to_restart_streak_option);
            non_hex_color_for_yes_masturbaite = none_masturbate.getCurrentTextColor();
            non_hex_color_for_yes_porn = no_i_watched_porn_textview.getCurrentTextColor();
            hex_color_for_yes_masturbaite = String.format("#%06X", (0xFFFFFF & non_hex_color_for_yes_masturbaite));
            hex_color_for_yes_porn = String.format("#%06X", (0xFFFFFF & non_hex_color_for_yes_porn));
            if ((hex_color_for_yes_masturbaite.equals("#FFFFFF")) && (hex_color_for_yes_porn.equals("#FFFFFF"))) {
                switch_to_restart_streak_option.setChecked(false);
            }
        }
    }

    private void turn_on_switch() {
        if (getActivity() != null) {
            Button none_masturbate = getActivity().findViewById(R.id.none_masturbate);
            Button no_i_watched_porn_textview = getActivity().findViewById(R.id.no_i_watched_porn_textview);
            int non_hex_color_for_yes_masturbaite;
            int non_hex_color_for_yes_porn;
            String hex_color_for_yes_masturbaite;
            String hex_color_for_yes_porn;
            Switch switch_to_restart_streak_option = getActivity().findViewById(R.id.switch_to_restart_streak_option);
            non_hex_color_for_yes_masturbaite = none_masturbate.getCurrentTextColor();
            non_hex_color_for_yes_porn = no_i_watched_porn_textview.getCurrentTextColor();
            hex_color_for_yes_masturbaite = String.format("#%06X", (0xFFFFFF & non_hex_color_for_yes_masturbaite));
            hex_color_for_yes_porn = String.format("#%06X", (0xFFFFFF & non_hex_color_for_yes_porn));
            if ((hex_color_for_yes_masturbaite.equals("#607D8B")) || (hex_color_for_yes_porn.equals("#607D8B"))) {
                switch_to_restart_streak_option.setChecked(true);
            }
        }
    }

    private void openDialog() {
        if (getActivity() != null) {
            Popup_dialog popup_dialog = new Popup_dialog();
            popup_dialog.show(getActivity().getSupportFragmentManager(), "example dialog");
        }
    }


    private void display_streak() {
        if (getActivity() != null && getContext() != null) {
            TextView textView = (TextView) getActivity().findViewById(R.id.Your_streak_display_TextView_for_botton);
            int streak = Time_for_calculation.current_streak(getContext(),"");
            if (streak == 1) {
                String streak_string = Integer.toString(streak);
                String sentence_with_streak = "Your current streak is ".concat(streak_string).concat(" day.");
                SpannableString spanned_sentence_with_streak = new SpannableString(sentence_with_streak);
                int color_for_streak = Color.rgb(96, 125, 139);
                ForegroundColorSpan blue_color_to_span = new ForegroundColorSpan(color_for_streak);
                StyleSpan boldspan = new StyleSpan(Typeface.BOLD);
                spanned_sentence_with_streak.setSpan(blue_color_to_span, 23, (23 + streak_string.length()), 0);
                spanned_sentence_with_streak.setSpan(boldspan, 0, spanned_sentence_with_streak.length(), 0);
                textView.setText(spanned_sentence_with_streak);
            } else {
                String streak_string = Integer.toString(streak);
                String sentence_with_streak = "Your current streak is " + streak_string + " days.";
                SpannableString spanned_sentence_with_streak = new SpannableString(sentence_with_streak);
                int color_for_streak = Color.rgb(96, 125, 139);
                ForegroundColorSpan orange_color_to_span = new ForegroundColorSpan(color_for_streak);
                StyleSpan boldspan = new StyleSpan(Typeface.BOLD);
                spanned_sentence_with_streak.setSpan(orange_color_to_span, 23, (23 + streak_string.length()), 0);
                spanned_sentence_with_streak.setSpan(boldspan, 0, spanned_sentence_with_streak.length(), 0);
                textView.setText(spanned_sentence_with_streak);
            }
        }
    }

    private void set_time_now() {
        if (getActivity() != null) {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            String string_minute = String.valueOf(minute);
            String string_hour = String.valueOf(hour);
            String string_day = String.valueOf(day);
            String string_month = String.valueOf(month);
            String string_year = String.valueOf(year);
            TextView timer_to_restart_streak_textview = getActivity().findViewById(R.id.timer_to_restart_streak_textview);
            timer_to_restart_streak_textview.setText(Simplify_the_time.getting_the_time(string_minute.concat(":").concat(string_hour).concat("_").concat(string_day).concat("_").concat(string_month).concat("_").concat(string_year)));
            if (timer_to_restart_streak_textview.getVisibility() != View.VISIBLE) {
                timer_to_restart_streak_textview.setVisibility(View.VISIBLE);
                ConstraintLayout constraintLayout = getActivity().findViewById(R.id.button_fragment);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(timer_to_restart_streak_textview.getId(), ConstraintSet.TOP, R.id.choose_date_for_streak_button, ConstraintSet.BOTTOM, dip_to_pixels(20));
                constraintSet.connect(R.id.Restart_streak_for_switch_textview, ConstraintSet.TOP, R.id.timer_to_restart_streak_textview, ConstraintSet.BOTTOM, dip_to_pixels(25));
                constraintSet.applyTo(constraintLayout);
            }
            SharedPreferences shared = getActivity().getSharedPreferences("temp_time_to_save", getContext().MODE_PRIVATE);
            SharedPreferences.Editor edit = shared.edit();
            edit.putLong("temp_time_to_save", System.currentTimeMillis());
            edit.apply();
        }
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

    private void setting_the_spinner() {
        if (getActivity() != null && getContext() != null) {
            final Spinner spinner_for_old_relapse_reason = getActivity().findViewById(R.id.spinner_for_old_relapse_reason);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("spinner_details", getContext().MODE_PRIVATE);
            String spinner_text = sharedPreferences.getString("spinner", "");
            if (!spinner_text.equals("")) {
                String[] splitted = spinner_text.split("_");
                list.addAll(Arrays.asList(splitted));
            }
            arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_layout_for_reason, list);
            arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item_layout);
            spinner_for_old_relapse_reason.setAdapter(arrayAdapter);
            spinner_for_old_relapse_reason.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(getActivity()!=null) {
                        EditText edit_text_for_new_relapse_reason = getActivity().findViewById(R.id.edit_text_for_new_relapse_reason);
                        edit_text_for_new_relapse_reason.getText().clear();
                        if(list.get(0).equals("")) {
                            list.remove(0);
                            arrayAdapter.notifyDataSetChanged();
                        }
                    }
                    return false;
                }
            });
            spinner_for_old_relapse_reason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    Object item = adapterView.getItemAtPosition(position);
                    if (item != null&&!item.toString().equals("")) {
                        Toast.makeText(getContext(), item.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    // TODO Auto-generated method stub

                }
            });
        }
    }

    private void check_if_spinner_is_empty() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("spinner_details", getContext().MODE_PRIVATE);
            String spinner_text = sharedPreferences.getString("spinner", "");
            if (spinner_text.equals("")) {
                TextView text_saying_or_in_child_in_button_relapse = getActivity().findViewById(R.id.text_saying_or_in_child_in_button_relapse);
                View box_around_spinner = getActivity().findViewById(R.id.box_around_spinner);
                Spinner spinner_for_old_relapse_reason = getActivity().findViewById(R.id.spinner_for_old_relapse_reason);
                text_saying_or_in_child_in_button_relapse.setVisibility(View.GONE);
                box_around_spinner.setVisibility(View.GONE);
                spinner_for_old_relapse_reason.setVisibility(View.GONE);
                ConstraintLayout constraintLayout = getActivity().findViewById(R.id.child_to_hold_name_and_spinner);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(R.id.edit_text_for_new_relapse_reason, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
                constraintSet.applyTo(constraintLayout);
                EditText edit_text_for_new_relapse_reason = getActivity().findViewById(R.id.edit_text_for_new_relapse_reason);
                edit_text_for_new_relapse_reason.setGravity(Gravity.CENTER);
            } else
                setting_the_spinner();
        }
    }
    private void set_text_watcher() {
        if (getActivity() != null) {
            EditText edit_text_for_new_relapse_reason = getActivity().findViewById(R.id.edit_text_for_new_relapse_reason);
            edit_text_for_new_relapse_reason.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (getActivity() != null) {
                        Spinner spinner_for_old_relapse_reason = getActivity().findViewById(R.id.spinner_for_old_relapse_reason);
                        if(spinner_for_old_relapse_reason.getVisibility()==View.VISIBLE) {
                            if (!list.get(0).equals("")) {
                                list.add(0, "");
                            }
                            spinner_for_old_relapse_reason.setSelection(0);
                            arrayAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }
    private void check_all() {
        if (getActivity() != null&&getContext()!=null) {
            String first;
            String second;
            String third;
            String four;
            String five;
            String six;
            Button yes_i_watched_porn_textview = getActivity().findViewById(R.id.yes_i_watched_porn_textview);
            Button none_masturbate = getActivity().findViewById(R.id.none_masturbate);
            Button once_masturbate = getActivity().findViewById(R.id.once_masturbate);
            Button no_i_watched_porn_textview = getActivity().findViewById(R.id.no_i_watched_porn_textview);
            Button custom_masturbate = getActivity().findViewById(R.id.custom_masturbate);
            TextView timer_to_restart_streak_textview = getActivity().findViewById(R.id.timer_to_restart_streak_textview);
            Spinner spinner_for_old_relapse_reason  = getActivity().findViewById(R.id.spinner_for_old_relapse_reason);
            EditText edit_text_for_new_relapse_reason = getActivity().findViewById(R.id.edit_text_for_new_relapse_reason);
            Switch switch_to_restart_streak_option = getActivity().findViewById(R.id.switch_to_restart_streak_option);
            EditText enter_how_you_feel_after_relapse= getActivity().findViewById(R.id.enter_how_you_feel_after_relapse);
            TextView did_you_watch_porn_textview = getActivity().findViewById(R.id.did_you_watch_porn_textview);
            TextView how_many_times_masturbaite = getActivity().findViewById(R.id.how_many_times_masturbaite);
            TextView choose_date_to_restart_your_streak_textview = getActivity().findViewById(R.id.choose_date_to_restart_your_streak_textview);
            TextView choose_reason_for_relapse_text = getActivity().findViewById(R.id.choose_reason_for_relapse_text);
            TextView text_to_tell_user_to_tell_how_they_feel = getActivity().findViewById(R.id.text_to_tell_user_to_tell_how_they_feel);
            if(yes_i_watched_porn_textview.getBackground().getConstantState()==getResources().getDrawable(R.drawable.color_for_botton_on).getConstantState()){
                first = "yes";
            } else if(no_i_watched_porn_textview.getBackground().getConstantState()==getResources().getDrawable(R.drawable.color_for_botton_on).getConstantState()) {
                first = "no";
            } else {
                first = "not_allowed";
            }
            if (none_masturbate.getBackground().getConstantState()==getResources().getDrawable(R.drawable.color_for_botton_on).getConstantState()){
                second = "0";
            } else if(once_masturbate.getBackground().getConstantState()==getResources().getDrawable(R.drawable.color_for_botton_on).getConstantState()){
                second="1";
            } else if(custom_masturbate.getBackground().getConstantState()==getResources().getDrawable(R.drawable.color_for_botton_on).getConstantState()){
                second = custom_masturbate.getText().toString();
            } else {
                second = "not_allowed";
            }
            if(timer_to_restart_streak_textview.getVisibility() != View.VISIBLE){
                third = "not_allowed";
            } else {
                third = "allowed";
            }
            if(spinner_for_old_relapse_reason.getVisibility() == View.VISIBLE){
                if(!edit_text_for_new_relapse_reason.getText().toString().trim().equals("") || is_spinner_chosen()){
                    if(!edit_text_for_new_relapse_reason.getText().toString().trim().equals("")){
                        five = edit_text_for_new_relapse_reason.getText().toString();
                    } else{
                        five = spinner_for_old_relapse_reason.getSelectedItem().toString();
                    }
                } else {
                    five = "not_allowed";
                }
            } else {
                if(!edit_text_for_new_relapse_reason.getText().toString().trim().equals("")){
                    five = edit_text_for_new_relapse_reason.getText().toString();
                } else {
                    five = "not_allowed";
                }
            }
            if(switch_to_restart_streak_option.isChecked()){
                four = "yes";
            } else {
                four = "no";
            }
            if(!enter_how_you_feel_after_relapse.getText().toString().trim().equals("")){
                six = enter_how_you_feel_after_relapse.getText().toString();
            } else {
                six = "not_allowed";
            }
            if(first.equals("not_allowed") || second.equals("not_allowed") || third.equals("not_allowed") || five.equals("not_allowed") ||six.equals("not_allowed")){
                ScrollView scroll_view_for_button = getActivity().findViewById(R.id.scroll_view_for_button);
                if(first.equals("not_allowed")){
                    scroll_view_for_button.smoothScrollTo(0,did_you_watch_porn_textview.getTop());
                    Toast.makeText(getContext(), "Please indicate if you watched porn", Toast.LENGTH_LONG).show();
                } else if(second.equals("not_allowed")){
                    scroll_view_for_button.smoothScrollTo(0,how_many_times_masturbaite.getTop());
                    Toast.makeText(getContext(), "Please indicate how many times did you do it", Toast.LENGTH_LONG).show();
                } else if(third.equals("not_allowed")){
                    scroll_view_for_button.smoothScrollTo(0,choose_date_to_restart_your_streak_textview.getTop());
                    Toast.makeText(getContext(), "Please indicate date of relapse", Toast.LENGTH_LONG).show();
                } else if(five.equals("not_allowed")){
                    scroll_view_for_button.smoothScrollTo(0,choose_reason_for_relapse_text.getTop());
                    Toast.makeText(getContext(), "Please indicate reason of relapse", Toast.LENGTH_LONG).show();
                    if(spinner_for_old_relapse_reason.getVisibility()!=View.VISIBLE){
                        edit_text_for_new_relapse_reason.setError("Can't be Empty");
                    }
                } else{
                    scroll_view_for_button.smoothScrollTo(0,text_to_tell_user_to_tell_how_they_feel.getTop());
                    Toast.makeText(getContext(), "Please indicate how you feel", Toast.LENGTH_LONG).show();
                    enter_how_you_feel_after_relapse.setError("Can't be empty");
                }
            } else {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("temp_time_to_save", getContext().MODE_PRIVATE);
                String final_text = first.concat("_split_here_").concat(second).concat("_split_here_").concat(four).concat("_split_here_").concat(five).concat("_split_here_").concat(six).concat("_split_here_").concat(String.valueOf(sharedPreferences.getLong("temp_time_to_save", 0))).concat("_split_max_here_");
                SharedPreferences shared = getActivity().getSharedPreferences("main_streak", getContext().MODE_PRIVATE);
                SharedPreferences.Editor edit = shared.edit();
                edit.putString("final_information",shared.getString("final_information","").concat(final_text));
                edit.apply();
                save_the_spinner();
                change_the_fragment();
            }
        }
    }
    private boolean is_spinner_chosen() {
        if (getActivity() != null) {
            Spinner spinner_for_old_relapse_reason = getActivity().findViewById(R.id.spinner_for_old_relapse_reason);
            if (spinner_for_old_relapse_reason.getVisibility() == View.VISIBLE) {
                if (spinner_for_old_relapse_reason.getSelectedItemPosition() == 0) {
                    if (spinner_for_old_relapse_reason.getSelectedItem().toString().equals("")) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    private void change_the_fragment() {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new home_fragment()).commit();
        }
    }
    private void save_the_spinner() {
        if (getActivity() != null) {
            String save_me;
            Spinner spinner_for_old_relapse_reason = getActivity().findViewById(R.id.spinner_for_old_relapse_reason);
            EditText edit_text_for_new_relapse_reason = getActivity().findViewById(R.id.edit_text_for_new_relapse_reason);
            if(is_spinner_chosen()){
                save_me = spinner_for_old_relapse_reason.getSelectedItem().toString();
           } else {
                save_me = edit_text_for_new_relapse_reason.getText().toString().trim();
           }
            SharedPreferences shared = getActivity().getSharedPreferences("spinner_details", getContext().MODE_PRIVATE);
            SharedPreferences.Editor edit = shared.edit();
            String[] old_save = shared.getString("spinner", "").split("_");
            boolean save_or_no = true;
            for(int i =0;i<old_save.length-1;i++){
                if(old_save[i].toLowerCase().equals(save_me.toLowerCase())){
                    save_or_no = false;
                }
            }
            if(save_or_no) {
                edit.putString("spinner", shared.getString("spinner", "").concat("_").concat(save_me));
                edit.apply();
            }
        }
    }
    @SuppressLint("ClickableViewAccessibility")
    private void edit_text_scroll() {
        if (getActivity() != null) {
            EditText enter_how_you_feel_after_relapse = getActivity().findViewById(R.id.enter_how_you_feel_after_relapse);
            enter_how_you_feel_after_relapse.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (v.getId() == R.id.enter_how_you_feel_after_relapse) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        switch (event.getAction() & MotionEvent.ACTION_MASK) {
                            case MotionEvent.ACTION_UP:
                                v.getParent().requestDisallowInterceptTouchEvent(false);
                                break;
                        }
                    }
                    return false;
                }
            });
        }
    }
}