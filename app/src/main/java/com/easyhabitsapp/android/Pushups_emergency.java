package com.easyhabitsapp.android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Pushups_emergency extends AppCompatActivity {
    private long time_at_start;
    private long total_time;
    private int sets;
    private int which_set_am_i_on = 1;
    private int pushups;
    private int seconds_to_rest;
    private int actual_rest_taken;
    private long rest_calc;
    private Handler handler = new Handler();
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushups_emergency);
        hide_top_bar();
        change_the_layout(0);
        pushups_text_watcher();
        open_data_and_set_it();
        sets_text_watcher();
        set_colors_for_the_first_text();
        set_the_colors_for_second_text();
        start_button_listen();
        big_button_listen();
        stop_pushups_button_listen();
        back_button_listen();
    }

    private void hide_top_bar() {
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().hide();
        }
    }

    private void sets_text_watcher() {
        final EditText enter_number_of_sets_for_pushups = findViewById(R.id.enter_number_of_sets_for_pushups);
        final EditText enter_number_of_pushups_for_pushups = findViewById(R.id.enter_number_of_pushups_for_pushups);
        final TextView saying_total_pushups_in_pushups = findViewById(R.id.saying_total_pushups_in_pushups);
        final TextView showing_total_pushups_in_pushups = findViewById(R.id.showing_total_pushups_in_pushups);
        final TextView sets_of_text_view_in_the_middle = findViewById(R.id.sets_of_text_view_in_the_middle);
        enter_number_of_sets_for_pushups.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (enter_number_of_sets_for_pushups.getText().toString().equals("") || enter_number_of_pushups_for_pushups.getText().toString().equals("") || Integer.parseInt(enter_number_of_sets_for_pushups.getText().toString()) == 0 || Integer.parseInt(enter_number_of_pushups_for_pushups.getText().toString()) == 0) {
                    saying_total_pushups_in_pushups.setVisibility(View.INVISIBLE);
                    showing_total_pushups_in_pushups.setVisibility(View.INVISIBLE);
                } else {
                    saying_total_pushups_in_pushups.setVisibility(View.VISIBLE);
                    showing_total_pushups_in_pushups.setVisibility(View.VISIBLE);
                    int total_pushups = Integer.parseInt(enter_number_of_sets_for_pushups.getText().toString()) * Integer.parseInt(enter_number_of_pushups_for_pushups.getText().toString());
                    showing_total_pushups_in_pushups.setText("Total Pushups = ".concat(String.valueOf(total_pushups)));
                    set_the_colors_for_second_text();
                    if (Integer.parseInt(enter_number_of_sets_for_pushups.getText().toString()) == 1) {
                        sets_of_text_view_in_the_middle.setText("Set of");
                    } else {
                        sets_of_text_view_in_the_middle.setText("Sets of");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void pushups_text_watcher() {
        final EditText enter_number_of_sets_for_pushups = findViewById(R.id.enter_number_of_sets_for_pushups);
        final EditText enter_number_of_pushups_for_pushups = findViewById(R.id.enter_number_of_pushups_for_pushups);
        final TextView saying_total_pushups_in_pushups = findViewById(R.id.saying_total_pushups_in_pushups);
        final TextView showing_total_pushups_in_pushups = findViewById(R.id.showing_total_pushups_in_pushups);
        final TextView pushups_text_view_beside_sets = findViewById(R.id.pushups_text_view_beside_sets);
        enter_number_of_pushups_for_pushups.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (enter_number_of_sets_for_pushups.getText().toString().equals("") || enter_number_of_pushups_for_pushups.getText().toString().equals("") | Integer.parseInt(enter_number_of_sets_for_pushups.getText().toString()) == 0 || Integer.parseInt(enter_number_of_pushups_for_pushups.getText().toString()) == 0) {
                    saying_total_pushups_in_pushups.setVisibility(View.INVISIBLE);
                    showing_total_pushups_in_pushups.setVisibility(View.INVISIBLE);
                } else {
                    saying_total_pushups_in_pushups.setVisibility(View.VISIBLE);
                    showing_total_pushups_in_pushups.setVisibility(View.VISIBLE);
                    int total_pushups = Integer.parseInt(enter_number_of_sets_for_pushups.getText().toString()) * Integer.parseInt(enter_number_of_pushups_for_pushups.getText().toString());
                    showing_total_pushups_in_pushups.setText("Total Pushups = ".concat(String.valueOf(total_pushups)));
                    set_the_colors_for_second_text();
                    if (Integer.parseInt(enter_number_of_pushups_for_pushups.getText().toString()) == 1) {
                        pushups_text_view_beside_sets.setText("Pushup");
                    } else {
                        pushups_text_view_beside_sets.setText("Pushups");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void open_data_and_set_it() {
        EditText enter_number_of_sets_for_pushups = findViewById(R.id.enter_number_of_sets_for_pushups);
        EditText enter_number_of_pushups_for_pushups = findViewById(R.id.enter_number_of_pushups_for_pushups);
        EditText enter_of_rest_in_seconds_for_pushups = findViewById(R.id.enter_of_rest_in_seconds_for_pushups);
        SharedPreferences sharedPreferences = getSharedPreferences("pushups_emergency", MODE_PRIVATE);
        int pushups = sharedPreferences.getInt("pushups", 8);
        int sets = sharedPreferences.getInt("sets", 4);
        int rest = sharedPreferences.getInt("rest",30);
        enter_number_of_sets_for_pushups.setText(String.valueOf(sets));
        enter_number_of_pushups_for_pushups.setText(String.valueOf(pushups));
        enter_of_rest_in_seconds_for_pushups.setText(String.valueOf(rest));
    }

    private void set_colors_for_the_first_text() {
        TextView saying_total_pushups_in_pushups = findViewById(R.id.saying_total_pushups_in_pushups);
        Spannable color_me = new SpannableString("Total Pushups = Sets x Pushups");
        color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 16, 21, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 23, saying_total_pushups_in_pushups.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        saying_total_pushups_in_pushups.setText(color_me);
    }

    private void set_the_colors_for_second_text() {
        TextView showing_total_pushups_in_pushups = findViewById(R.id.showing_total_pushups_in_pushups);
        int length = showing_total_pushups_in_pushups.getText().toString().replaceAll("\\D+", "").length();
        Spannable color_me = new SpannableString(showing_total_pushups_in_pushups.getText().toString());
        color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), showing_total_pushups_in_pushups.getText().toString().length() - length, showing_total_pushups_in_pushups.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        showing_total_pushups_in_pushups.setText(color_me);
    }

    private void save_the_pushups() {
        EditText enter_number_of_sets_for_pushups = findViewById(R.id.enter_number_of_sets_for_pushups);
        EditText enter_number_of_pushups_for_pushups = findViewById(R.id.enter_number_of_pushups_for_pushups);
        EditText enter_of_rest_in_seconds_for_pushups = findViewById(R.id.enter_of_rest_in_seconds_for_pushups);
        SharedPreferences sharedPreferences = getSharedPreferences("pushups_emergency", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putInt("sets", Integer.parseInt(enter_number_of_sets_for_pushups.getText().toString()));
        myEdit.putInt("pushups", Integer.parseInt(enter_number_of_pushups_for_pushups.getText().toString()));
        myEdit.putInt("rest",Integer.parseInt(enter_of_rest_in_seconds_for_pushups.getText().toString()));
        myEdit.commit();
    }

    private void start_button_listen() {
        Button start_push_ups_button = findViewById(R.id.start_push_ups_button);
        final EditText enter_number_of_sets_for_pushups = findViewById(R.id.enter_number_of_sets_for_pushups);
        final EditText enter_number_of_pushups_for_pushups = findViewById(R.id.enter_number_of_pushups_for_pushups);
        final EditText enter_of_rest_in_seconds_for_pushups = findViewById(R.id.enter_of_rest_in_seconds_for_pushups);
        start_push_ups_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!enter_number_of_sets_for_pushups.getText().toString().equals("") && !enter_number_of_pushups_for_pushups.getText().toString().equals("") && Integer.parseInt(enter_number_of_sets_for_pushups.getText().toString()) != 0 && Integer.parseInt(enter_number_of_pushups_for_pushups.getText().toString()) != 0 && !enter_of_rest_in_seconds_for_pushups.getText().toString().equals("") && Integer.parseInt(enter_of_rest_in_seconds_for_pushups.getText().toString()) != 0) {
                    sets = Integer.parseInt(enter_number_of_sets_for_pushups.getText().toString());
                    seconds_to_rest = Integer.parseInt(enter_of_rest_in_seconds_for_pushups.getText().toString());
                    pushups = Integer.parseInt(enter_number_of_pushups_for_pushups.getText().toString());
                    save_the_pushups();
                    color_the_set_above_button();
                    change_the_layout(1);
                } else {
                    if (enter_number_of_sets_for_pushups.getText().toString().equals("")) {
                        Toast toast = Toast.makeText(getApplicationContext(), "number of setups can't be empty", Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (enter_number_of_pushups_for_pushups.getText().toString().equals("")) {
                        Toast toast = Toast.makeText(getApplicationContext(), "number of pushups can't be empty", Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (Integer.parseInt(enter_number_of_sets_for_pushups.getText().toString()) == 0) {
                        Toast toast = Toast.makeText(getApplicationContext(), "number of setups can't be zero", Toast.LENGTH_SHORT);
                        toast.show();
                    }else if (Integer.parseInt(enter_of_rest_in_seconds_for_pushups.getText().toString()) == 0) {
                        Toast toast = Toast.makeText(getApplicationContext(), "rest can't be zero", Toast.LENGTH_SHORT);
                        toast.show();
                    }else if (enter_of_rest_in_seconds_for_pushups.getText().toString().equals("")) {
                        Toast toast = Toast.makeText(getApplicationContext(), "rest can't be empty", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "number of pushups can't be zero", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
    }

    private void big_button_listen() {
        final Button button_to_press_on_add_counter = findViewById(R.id.button_to_press_on_add_counter);
        final TextView text_showing_number_of_counter_in_circle = findViewById(R.id.text_showing_number_of_counter_in_circle);
        button_to_press_on_add_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (text_showing_number_of_counter_in_circle.getText().toString().equals("Tap to start")) {
                    start_the_timer();
                } else if(text_showing_number_of_counter_in_circle.getText().toString().equals("Ready to go?") || text_showing_number_of_counter_in_circle.getText().toString().equals("Let's go")){
                    if(countDownTimer!=null){
                        countDownTimer.cancel();
                    }
                    which_set_am_i_on = which_set_am_i_on +1;
                    put_the_rest_times(2);
                    add_one_to_the_set();
                    color_the_set_above_button();
                    time_at_start = System.currentTimeMillis();
                    call_the_handler();

                } else {
                    put_the_rest_times(1);
                    handler.removeCallbacksAndMessages(null);
                    add_the_times();
                    take_rest();
                }
            }
        });
    }

    private void color_the_set_above_button() {
        TextView text_view_typing_which_set = findViewById(R.id.text_view_typing_which_set);
        int numbers_length = text_view_typing_which_set.getText().toString().replaceAll("\\D+", "").length();
        Spannable color_me = new SpannableString(text_view_typing_which_set.getText().toString());
        color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), text_view_typing_which_set.getText().toString().length() - numbers_length, text_view_typing_which_set.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_view_typing_which_set.setText(color_me);
    }

    private void start_the_timer() {
        time_at_start = System.currentTimeMillis();
        call_the_handler();
    }

    private void call_the_handler() {
        handler.postDelayed(new Runnable() {
            public void run() {
                TextView text_showing_number_of_counter_in_circle = findViewById(R.id.text_showing_number_of_counter_in_circle);
                if(System.currentTimeMillis()-time_at_start>60000){
                    SimpleDateFormat formatter = new SimpleDateFormat("mm:ss:SS", Locale.getDefault());
                    String text = formatter.format(new Date(System.currentTimeMillis()-time_at_start));
                    text_showing_number_of_counter_in_circle.setText(text);
                } else {
                    SimpleDateFormat formatter = new SimpleDateFormat("ss:SS", Locale.getDefault());
                    String text = formatter.format(new Date(System.currentTimeMillis()-time_at_start));
                    text_showing_number_of_counter_in_circle.setText(text);
                }
                handler.postDelayed(this, 10);
            }
        }, 10);
    }
    private void take_rest(){
        final TextView text_view_typing_which_set = findViewById(R.id.text_view_typing_which_set);
        final TextView text_showing_number_of_counter_in_circle = findViewById(R.id.text_showing_number_of_counter_in_circle);
        int next_set = Integer.parseInt(text_view_typing_which_set.getText().toString().replaceAll("\\D+", ""))+1;
        if(next_set>sets){
            change_the_layout(2);
            set_the_final_text();

        } else {
            text_showing_number_of_counter_in_circle.setText("Ready to go?");
            countDownTimer = new CountDownTimer(seconds_to_rest*1000, 1000) {
                @Override
                public void onTick(long l) {
                    String before_color = "Rest: ".concat(String.valueOf((int) l/1000).concat(" seconds remaining"));
                    Spannable spannable = new SpannableString(before_color);
                    String seconds_left = String.valueOf((int) l/1000);
                    spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")),6,6+seconds_left.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text_view_typing_which_set.setText(spannable);
                }

                @Override
                public void onFinish() {
                    text_view_typing_which_set.setText("Rest is up");
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(2000, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        v.vibrate(2000);
                    }
                    text_showing_number_of_counter_in_circle.setText("Let's go");
                }
            };
            countDownTimer.start();
        }
    }
    private void add_one_to_the_set(){
        TextView text_view_typing_which_set = findViewById(R.id.text_view_typing_which_set);
        text_view_typing_which_set.setText("Set ".concat(String.valueOf(which_set_am_i_on)));
    }
    private void add_the_times(){
        TextView text_showing_number_of_counter_in_circle = findViewById(R.id.text_showing_number_of_counter_in_circle);
        String[] split_time = text_showing_number_of_counter_in_circle.getText().toString().split(":");
        if(split_time.length==3){
            total_time = total_time + Integer.parseInt(split_time[0])*60000;
            total_time = total_time + Integer.parseInt(split_time[1])*1000;
            total_time = total_time + Integer.parseInt(split_time[2])*10;
        } else {
            total_time = total_time + Integer.parseInt(split_time[0])*1000;
            total_time = total_time + Integer.parseInt(split_time[1])*10;
        }
    }
    private void put_the_rest_times(int which){
        /*TextView text_view_typing_which_set = findViewById(R.id.text_view_typing_which_set);
        if(text_view_typing_which_set.getText().toString().contains("Rest: ")){
            actual_rest_taken = actual_rest_taken + Integer.parseInt(text_view_typing_which_set.getText().toString().replaceAll("\\D+", ""));
        } else {
            actual_rest_taken = actual_rest_taken + seconds_to_rest;
        }*/
        if(which==1){
            rest_calc = System.currentTimeMillis();
        } else if(which==2){
            actual_rest_taken = actual_rest_taken + (int) (System.currentTimeMillis()-rest_calc)/1000;
        }
    }
    private void change_the_layout(int which){
        Button start_push_ups_button = findViewById(R.id.start_push_ups_button);
        ConstraintLayout layout_to_put_pushups_in_the_middle = findViewById(R.id.layout_to_put_pushups_in_the_middle);
        TextView saying_total_pushups_in_pushups = findViewById(R.id.saying_total_pushups_in_pushups);
        TextView showing_total_pushups_in_pushups = findViewById(R.id.showing_total_pushups_in_pushups);
        ConstraintLayout rest_layout_under_layout_pushups = findViewById(R.id.rest_layout_under_layout_pushups);

        Button Stop_Pushups_button_in_after_start_text = findViewById(R.id.Stop_Pushups_button_in_after_start_text);
        View circle_button_counter_view  =findViewById(R.id.circle_button_counter_view);
        Button button_to_press_on_add_counter = findViewById(R.id.button_to_press_on_add_counter);
        TextView text_view_typing_which_set = findViewById(R.id.text_view_typing_which_set);
        TextView text_showing_number_of_counter_in_circle = findViewById(R.id.text_showing_number_of_counter_in_circle);

        TextView text_saying_time_in_pushups = findViewById(R.id.text_saying_time_in_pushups);
        TextView text_saying_time_in_rest = findViewById(R.id.text_saying_time_in_rest);
        TextView text_saying_congrtas_in_pushups = findViewById(R.id.text_saying_congrtas_in_pushups);

        if(which==0){
            start_push_ups_button.setVisibility(View.VISIBLE);
            layout_to_put_pushups_in_the_middle.setVisibility(View.VISIBLE);
            saying_total_pushups_in_pushups.setVisibility(View.VISIBLE);
            showing_total_pushups_in_pushups.setVisibility(View.VISIBLE);
            rest_layout_under_layout_pushups.setVisibility(View.VISIBLE);

            Stop_Pushups_button_in_after_start_text.setVisibility(View.INVISIBLE);
            circle_button_counter_view.setVisibility(View.INVISIBLE);
            button_to_press_on_add_counter.setVisibility(View.INVISIBLE);
            text_view_typing_which_set.setVisibility(View.INVISIBLE);
            text_showing_number_of_counter_in_circle.setVisibility(View.INVISIBLE);

            text_saying_time_in_pushups.setVisibility(View.INVISIBLE);
            text_saying_time_in_rest.setVisibility(View.INVISIBLE);
            text_saying_congrtas_in_pushups.setVisibility(View.INVISIBLE);
        } else if(which==1){
            start_push_ups_button.setVisibility(View.INVISIBLE);
            layout_to_put_pushups_in_the_middle.setVisibility(View.INVISIBLE);
            saying_total_pushups_in_pushups.setVisibility(View.INVISIBLE);
            showing_total_pushups_in_pushups.setVisibility(View.INVISIBLE);
            rest_layout_under_layout_pushups.setVisibility(View.INVISIBLE);

            Stop_Pushups_button_in_after_start_text.setVisibility(View.VISIBLE);
            circle_button_counter_view.setVisibility(View.VISIBLE);
            button_to_press_on_add_counter.setVisibility(View.VISIBLE);
            text_view_typing_which_set.setVisibility(View.VISIBLE);
            text_showing_number_of_counter_in_circle.setVisibility(View.VISIBLE);

            text_saying_time_in_pushups.setVisibility(View.INVISIBLE);
            text_saying_time_in_rest.setVisibility(View.INVISIBLE);
            text_saying_congrtas_in_pushups.setVisibility(View.INVISIBLE);
        } else if(which==2){
            start_push_ups_button.setVisibility(View.INVISIBLE);
            layout_to_put_pushups_in_the_middle.setVisibility(View.INVISIBLE);
            saying_total_pushups_in_pushups.setVisibility(View.INVISIBLE);
            showing_total_pushups_in_pushups.setVisibility(View.INVISIBLE);
            rest_layout_under_layout_pushups.setVisibility(View.INVISIBLE);

            Stop_Pushups_button_in_after_start_text.setVisibility(View.VISIBLE);
            circle_button_counter_view.setVisibility(View.INVISIBLE);
            button_to_press_on_add_counter.setVisibility(View.INVISIBLE);
            text_view_typing_which_set.setVisibility(View.INVISIBLE);
            text_showing_number_of_counter_in_circle.setVisibility(View.INVISIBLE);

            text_saying_time_in_pushups.setVisibility(View.VISIBLE);
            text_saying_time_in_rest.setVisibility(View.VISIBLE);
            text_saying_congrtas_in_pushups.setVisibility(View.VISIBLE);

            Stop_Pushups_button_in_after_start_text.setText("Let's go");
            Stop_Pushups_button_in_after_start_text.setTextColor(Color.parseColor("#607D8B"));
        }
    }
    private void set_the_final_text(){
        TextView text_saying_time_in_pushups = findViewById(R.id.text_saying_time_in_pushups);
        TextView text_saying_time_in_rest = findViewById(R.id.text_saying_time_in_rest);
        TextView text_saying_congrtas_in_pushups = findViewById(R.id.text_saying_congrtas_in_pushups);
        int number_of_pushups = which_set_am_i_on*pushups;
        text_saying_congrtas_in_pushups.setText("Congrats!! You completed ".concat(String.valueOf(number_of_pushups)).concat(" pushups!!"));
        Spannable color_me = new SpannableString(text_saying_congrtas_in_pushups.getText().toString());
        color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 25, String.valueOf(number_of_pushups).length()+25, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 0, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_saying_congrtas_in_pushups.setText(color_me);

        text_saying_time_in_rest.setText("Rest: ".concat(seconds_to_sentence_time(actual_rest_taken)));
        text_saying_time_in_pushups.setText("Pushups: ".concat(seconds_to_sentence_time((int) total_time/1000)));

        String[] split_to_get_length_pushups = text_saying_time_in_pushups.getText().toString().split(" and ");
        Spannable color_me_pushups = new SpannableString(text_saying_time_in_pushups.getText().toString());
        color_me_pushups.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 9, split_to_get_length_pushups[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        color_me_pushups.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), split_to_get_length_pushups[0].length() + 5, text_saying_time_in_pushups.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_saying_time_in_pushups.setText(color_me_pushups);

        String[] split_to_get_length_rest = text_saying_time_in_rest.getText().toString().split(" and ");
        Spannable color_me_rest = new SpannableString(text_saying_time_in_rest.getText().toString());
        color_me_rest.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 6, split_to_get_length_rest[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        color_me_rest.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), split_to_get_length_rest[0].length() + 5, text_saying_time_in_rest.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_saying_time_in_rest.setText(color_me_rest);
    }
    private String seconds_to_sentence_time(int seconds){
        int minutes = seconds/60;
        int real_seconds = seconds-minutes*60;
        if(minutes==1){
            if(real_seconds==1){
                return String.valueOf(minutes).concat(" minute and ").concat(String.valueOf(real_seconds).concat(" second"));
            } else {
                return String.valueOf(minutes).concat(" minute and ").concat(String.valueOf(real_seconds).concat(" seconds"));
            }
        } else {
            if(real_seconds==1){
                return String.valueOf(minutes).concat(" minutes and ").concat(String.valueOf(real_seconds).concat(" second"));
            } else {
                return String.valueOf(minutes).concat(" minutes and ").concat(String.valueOf(real_seconds).concat(" seconds"));
            }
        }
    }
    private void restart_everything(){
        TextView text_showing_number_of_counter_in_circle = findViewById(R.id.text_showing_number_of_counter_in_circle);
        TextView text_view_typing_which_set = findViewById(R.id.text_view_typing_which_set);
        Button Stop_Pushups_button_in_after_start_text = findViewById(R.id.Stop_Pushups_button_in_after_start_text);
        change_the_layout(0);
        open_data_and_set_it();
        which_set_am_i_on = 1;
        total_time = 0;
        actual_rest_taken = 0;
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
        text_showing_number_of_counter_in_circle.setText("Tap to start");
        text_view_typing_which_set.setText("Set 1");
        Stop_Pushups_button_in_after_start_text.setText(" Stop Pushups ");
        Stop_Pushups_button_in_after_start_text.setTextColor(Color.parseColor("#DC143C"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        restart_everything();
    }
    private void stop_pushups_button_listen(){
        final Button Stop_Pushups_button_in_after_start_text = findViewById(R.id.Stop_Pushups_button_in_after_start_text);
        final TextView text_view_typing_which_set = findViewById(R.id.text_view_typing_which_set);
        Stop_Pushups_button_in_after_start_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Stop_Pushups_button_in_after_start_text.getText().toString().equals(" Stop Pushups ")){
                    if(text_view_typing_which_set.getText().toString().equals("Set 1")){
                        restart_everything();
                    } else {
                        change_the_layout(2);
                        set_the_final_text();
                    }
                } else {
                    restart_everything();
                }
            }
        });
    }
    private void back_button_listen() {
        Button button_to_go_back_counter = findViewById(R.id.button_to_go_back_counter);
        button_to_go_back_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pushups_emergency.this, after_login.class);
                intent.putExtra("Start_the_emergency_true", true);
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }
}