package com.easyhabitsapp.android;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.FileProvider;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;

public class Weight_tracker_emergency extends AppCompatActivity implements Bottom_sheet_to_add_entry_weight.Bottom_sheet_for_add_entry_listen, DialogInterface.OnDismissListener, Bottom_sheet_dialog_to_change_start_weight_tracker.BottomSheetListener_for_changing_start_weight_info, Dialog_view_history_for_weight_tracker.Dialog_history_dismmiss_listen, AdapterView.OnItemSelectedListener {
    private long milli_start;
    private long milli_end;
    private boolean am_i_in_fragment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_tracker_emergency);
        hide_top_bar();
        back_button_listen();
        check_if_weight_has_been_set();
        add_entry_listen();
        three_dots_lsiten();
        share_button_listen();
        hide_the_info();
        buy_premuim_button_listen();
    }

    private void hide_top_bar() {
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().hide();
        }
    }

    private void back_button_listen() {
        Button button_to_go_back_counter = findViewById(R.id.button_to_go_back_counter);
        button_to_go_back_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Weight_tracker_emergency.this, after_login.class);
                intent.putExtra("Start_the_emergency_true", true);
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void check_if_weight_has_been_set() {
        SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
        String weight = sharedPreferences.getString("weight", "");
        if (weight != null && !weight.equals("")) {
            set_the_start_weight();
            set_the_current_weight();
            set_the_goal_weight();
            set_up_the_weight_saying_absolute_weight();
            set_up_the_progress();
            calculate_and_set_bmi();
            stats_at_the_bottom();
            set_up_spinner();
            set_up_the_chart();
            check_if_text_is_off_the_screen();
        } else {
            final Dialog_to_set_up_weight_tracker dialog_to_set_up_weight_tracker = new Dialog_to_set_up_weight_tracker();
            dialog_to_set_up_weight_tracker.show(getSupportFragmentManager(), "dialog_weight");
        }
    }

    private void set_the_start_weight() {
        TextView start_weight_under_text = findViewById(R.id.start_weight_under_text);
        SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
        float start_weight = sharedPreferences.getFloat("start_weight", 0);
        String units = sharedPreferences.getString("units", "metric");
        if (units != null) {
            if (units.equals("metric")) {
                start_weight_under_text.setText(String.format("%.1f", start_weight).concat(" kg"));
            } else {
                float weight_in_pound = start_weight * 2.205f;
                start_weight_under_text.setText(String.format("%.1f", weight_in_pound).concat(" lb"));
            }
        }
    }

    private void set_the_current_weight() {
        TextView current_weight_under_text = findViewById(R.id.current_weight_under_text);
        SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
        String all_weight = sharedPreferences.getString("weight", "");
        String units = sharedPreferences.getString("units", "metric");
        if (all_weight != null && units != null) {
            String[] split_weights = all_weight.split("split");
            ArrayList<Long> list_of_milli = new ArrayList<>();
            for (int i = 0; i < split_weights.length; i++) {
                String[] second_split = split_weights[i].split("small_divide");
                list_of_milli.add(Long.parseLong(second_split[0]));
            }
            Collections.sort(list_of_milli);
            for (int i = 0; i < split_weights.length; i++) {
                String[] second_split = split_weights[i].split("small_divide");
                if (second_split[0].equals(String.valueOf(list_of_milli.get(list_of_milli.size() - 1)))) {
                    if (units.equals("metric")) {
                        current_weight_under_text.setText(String.format("%.1f", Float.parseFloat(second_split[1])).concat(" kg"));
                    } else {
                        float weight_in_pound = Float.parseFloat(second_split[1]) * 2.205f;
                        current_weight_under_text.setText(String.format("%.1f", weight_in_pound).concat(" lb"));
                    }
                }
            }
        }
    }

    private void set_the_goal_weight() {
        TextView goal_weight_under_text = findViewById(R.id.goal_weight_under_text);
        SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
        float goal = sharedPreferences.getFloat("goal_start", -1);
        String units = sharedPreferences.getString("units", "metric");
        if (goal != -1 && units != null) {
            if (units.equals("metric")) {
                goal_weight_under_text.setText(String.format("%.1f", goal).concat(" kg"));
            } else {
                float weight_in_pound = goal * 2.205f;
                goal_weight_under_text.setText(String.format("%.1f", weight_in_pound).concat(" lb"));
            }
        }
    }

    private void add_entry_listen() {
        Button add_entry_button_under_chart = findViewById(R.id.add_entry_button_under_chart);
        add_entry_button_under_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bottom_sheet_to_add_entry_weight bottom_sheet_to_add_entry_weight = new Bottom_sheet_to_add_entry_weight();
                bottom_sheet_to_add_entry_weight.show(getSupportFragmentManager(), "add_entry_tag");
            }
        });
    }

    @Override
    public void on_entry_added(String text) {
        if (text.equals("update")) {
            set_the_start_weight();
            set_the_current_weight();
            set_the_goal_weight();
            set_up_the_weight_saying_absolute_weight();
            set_up_the_progress();
            calculate_and_set_bmi();
            stats_at_the_bottom();
            set_up_the_chart();
            check_if_text_is_off_the_screen();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        set_the_start_weight();
        set_the_current_weight();
        set_the_goal_weight();
        set_up_the_weight_saying_absolute_weight();
        set_up_the_progress();
        calculate_and_set_bmi();
        stats_at_the_bottom();
        set_up_the_chart();
        set_up_spinner();
        check_if_text_is_off_the_screen();
    }

    private void three_dots_lsiten() {
        Button button_to_add_three_dots = findViewById(R.id.button_to_add_three_dots);
        button_to_add_three_dots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bottom_sheet_dialog_to_change_start_weight_tracker bottom_sheet_dialog_to_change_start_weight_tracker = new Bottom_sheet_dialog_to_change_start_weight_tracker();
                bottom_sheet_dialog_to_change_start_weight_tracker.show(getSupportFragmentManager(), "three_dots");
            }
        });
    }

    @Override
    public void on_dailog_close_weight() {
        set_the_start_weight();
        set_the_current_weight();
        set_the_goal_weight();
        set_up_the_weight_saying_absolute_weight();
        set_up_the_progress();
        calculate_and_set_bmi();
        stats_at_the_bottom();
        set_up_the_chart();
        check_if_text_is_off_the_screen();
    }

    private void set_up_the_weight_saying_absolute_weight() {
        TextView text_view_showing_the_change_weight = findViewById(R.id.text_view_showing_the_change_weight);
        TextView current_weight_under_text = findViewById(R.id.current_weight_under_text);
        TextView start_weight_under_text = findViewById(R.id.start_weight_under_text);
        if (!current_weight_under_text.getText().toString().equals("") && !start_weight_under_text.getText().toString().equals("")) {
            float current_weight = Float.parseFloat(current_weight_under_text.getText().toString().replace(" kg", "").replace(" lb", ""));
            float start_weight = Float.parseFloat(start_weight_under_text.getText().toString().replace(" kg", "").replace(" lb", ""));
            SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
            String units = sharedPreferences.getString("units", "metric");
            if (units != null) {
                if (current_weight > start_weight) {
                    float difference = current_weight - start_weight;
                    if (units.equals("metric")) {
                        text_view_showing_the_change_weight.setText(String.format("%.1f", difference).concat(" kg"));
                    } else {
                        text_view_showing_the_change_weight.setText(String.format("%.1f", difference).concat(" lb"));
                    }
                } else if (current_weight < start_weight) {
                    float difference = start_weight - current_weight;
                    if (units.equals("metric")) {
                        text_view_showing_the_change_weight.setText(String.format("%.1f", difference).concat(" kg"));
                    } else {
                        text_view_showing_the_change_weight.setText(String.format("%.1f", difference).concat(" lb"));
                    }
                } else {
                    if (units.equals("metric")) {
                        text_view_showing_the_change_weight.setText("0.0".concat(" kg"));
                    } else {
                        text_view_showing_the_change_weight.setText("0.0".concat(" lb"));
                    }
                }
            }
        }
    }

    private void set_up_the_progress() {
        TextView text_view_above_progress_negative = findViewById(R.id.text_view_above_progress_negative);
        TextView text_view_under_progress_negative = findViewById(R.id.text_view_under_progress_negative);
        TextView text_view_above_progress_positive = findViewById(R.id.text_view_above_progress_positive);
        TextView text_view_under_progress_positive = findViewById(R.id.text_view_under_progress_positive);
        TextView text_view_showing_the_change_weight = findViewById(R.id.text_view_showing_the_change_weight);
        ProgressBar progress_bar_to_lose_weight = findViewById(R.id.progress_bar_to_lose_weight);
        ProgressBar proress_bar_for_add_weight = findViewById(R.id.proress_bar_for_add_weight);
        if (Payment_processer.getInstance().state_of_the_user()) {
            SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
            float start_weight = sharedPreferences.getFloat("start_weight", 0);
            float goal = sharedPreferences.getFloat("goal_start", 0);
            String units = sharedPreferences.getString("units", "metric");
            float difference = Math.abs(start_weight - goal);
            float second_difference = Math.abs(start_weight - return_the_current_weight());
            float upper_difference = Math.abs((start_weight + difference) - return_the_current_weight());
            float lower_difference = Math.abs(return_the_current_weight() - goal);
            if (units != null && units.equals("metric")) {
                text_view_showing_the_change_weight.setText(String.format("%.1f", second_difference).concat(" kg"));
                text_view_above_progress_negative.setText(String.format("%.1f", start_weight - difference).concat(" kg"));
                text_view_above_progress_positive.setText(String.format("%.1f", start_weight + difference).concat(" kg"));
            } else if (units != null && units.equals("imperial")) {
                text_view_showing_the_change_weight.setText(String.format("%.1f", second_difference * 2.205f).concat(" lb"));
                text_view_above_progress_negative.setText(String.format("%.1f", (start_weight - difference) * 2.205f).concat(" lb"));
                text_view_above_progress_positive.setText(String.format("%.1f", (start_weight + difference) * 2.205f).concat(" lb"));
            }
            if (return_the_current_weight() > start_weight) {
                proress_bar_for_add_weight.setProgress((int) ((second_difference / difference) * 100));
                progress_bar_to_lose_weight.setProgress(0);
                text_view_showing_the_change_weight.setTextColor(Color.parseColor("#ffff4444"));
                if ((int) ((second_difference / difference) * 100) <= 100) {
                    if (units != null && units.equals("metric")) {
                        text_view_under_progress_positive.setVisibility(View.VISIBLE);
                        String text_in_positive = String.format("Left: %.1f", upper_difference).concat(" kg").concat(", ".concat(String.valueOf((int) ((second_difference / difference) * 100)))).concat("%");
                        text_view_under_progress_positive.setText(text_in_positive);
                        text_view_under_progress_negative.setVisibility(View.INVISIBLE);
                    } else if (units != null && units.equals("imperial")) {
                        text_view_under_progress_positive.setVisibility(View.VISIBLE);
                        String text_in_positive = String.format("Left: %.1f", upper_difference * 2.205f).concat(" lb").concat(", ".concat(String.valueOf((int) ((second_difference / difference) * 100)))).concat("%");
                        text_view_under_progress_positive.setText(text_in_positive);
                        text_view_under_progress_negative.setVisibility(View.INVISIBLE);
                    }
                } else {
                    if (units != null && units.equals("metric")) {
                        text_view_under_progress_positive.setVisibility(View.VISIBLE);
                        text_view_under_progress_positive.setText("100%");
                        text_view_under_progress_negative.setVisibility(View.INVISIBLE);
                    } else if (units != null && units.equals("imperial")) {
                        text_view_under_progress_positive.setVisibility(View.VISIBLE);
                        text_view_under_progress_positive.setText("100%");
                        text_view_under_progress_negative.setVisibility(View.INVISIBLE);
                    }
                }
            } else if (return_the_current_weight() < start_weight) {
                progress_bar_to_lose_weight.setProgress((int) ((second_difference / difference) * 100));
                proress_bar_for_add_weight.setProgress(0);
                text_view_showing_the_change_weight.setTextColor(Color.parseColor("#32CD32"));
                if ((int) ((second_difference / difference) * 100) <= 100) {
                    if (units != null && units.equals("metric")) {
                        text_view_under_progress_negative.setVisibility(View.VISIBLE);
                        String text_in_negative = String.format("Left: %.1f", lower_difference).concat(" kg").concat(", ".concat(String.valueOf((int) ((second_difference / difference) * 100)))).concat("%");
                        text_view_under_progress_negative.setText(text_in_negative);
                        text_view_under_progress_positive.setVisibility(View.INVISIBLE);
                    } else if (units != null && units.equals("imperial")) {
                        text_view_under_progress_negative.setVisibility(View.VISIBLE);
                        String text_in_negative = String.format("Left: %.1f", lower_difference * 2.205f).concat(" lb").concat(", ".concat(String.valueOf((int) ((second_difference / difference) * 100)))).concat("%");
                        text_view_under_progress_negative.setText(text_in_negative);
                        text_view_under_progress_positive.setVisibility(View.INVISIBLE);
                    }
                } else {
                    if (units != null && units.equals("metric")) {
                        text_view_under_progress_negative.setVisibility(View.VISIBLE);
                        text_view_under_progress_negative.setText("100%");
                        text_view_under_progress_positive.setVisibility(View.INVISIBLE);
                    } else if (units != null && units.equals("imperial")) {
                        text_view_under_progress_negative.setVisibility(View.VISIBLE);
                        text_view_under_progress_negative.setText("100%");
                        text_view_under_progress_positive.setVisibility(View.INVISIBLE);
                    }
                }
            } else {
                proress_bar_for_add_weight.setProgress(0);
                progress_bar_to_lose_weight.setProgress(0);
                text_view_showing_the_change_weight.setTextColor(Color.parseColor("#607D8B"));
                text_view_under_progress_negative.setVisibility(View.INVISIBLE);
                text_view_under_progress_positive.setVisibility(View.INVISIBLE);
            }
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
            String units = sharedPreferences.getString("units", "metric");
            Random random = new Random();
            int low = random.nextInt(100) + 1;
            int high = random.nextInt(100) + low;
            int first_loss = random.nextInt(100) + 1;
            int second_loss = random.nextInt(10) + 1;
            int mode = random.nextInt(2);
            int percent = random.nextInt(100) + 1;
            if (units != null && units.equals("metric")) {
                text_view_showing_the_change_weight.setText(String.valueOf(first_loss).concat(".").concat(String.valueOf(second_loss)).concat(" kg"));
                text_view_above_progress_negative.setText(String.valueOf(low).concat(" kg"));
                text_view_above_progress_positive.setText(String.valueOf(high).concat(" kg"));
            } else if (units != null && units.equals("imperial")) {
                text_view_showing_the_change_weight.setText(String.valueOf(first_loss).concat(".").concat(String.valueOf(second_loss)).concat(" lb"));
                text_view_above_progress_negative.setText(String.valueOf(low).concat(" lb"));
                text_view_above_progress_positive.setText(String.valueOf(high).concat(" lb"));
            }
            if(mode == 0){
                progress_bar_to_lose_weight.setProgress(percent);
            } else {
                proress_bar_for_add_weight.setProgress(percent);
            }
        }
    }

    private float return_the_current_weight() {
        SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
        String all_weight = sharedPreferences.getString("weight", "");
        String units = sharedPreferences.getString("units", "metric");
        if (all_weight != null && units != null) {
            String[] split_weights = all_weight.split("split");
            ArrayList<Long> list_of_milli = new ArrayList<>();
            for (int i = 0; i < split_weights.length; i++) {
                String[] second_split = split_weights[i].split("small_divide");
                list_of_milli.add(Long.parseLong(second_split[0]));
            }
            Collections.sort(list_of_milli);
            for (int i = 0; i < split_weights.length; i++) {
                String[] second_split = split_weights[i].split("small_divide");
                if (second_split[0].equals(String.valueOf(list_of_milli.get(list_of_milli.size() - 1)))) {
                    /*if (units.equals("metric")) {
                        return Float.parseFloat(second_split[1]);
                    } else {
                        return Float.parseFloat(second_split[1]) * 2.205f;
                    }*/
                    return Float.parseFloat(second_split[1]);
                }
            }
        }
        return 0;
    }

    private void calculate_and_set_bmi() {
        TextView text_view_showing_the_bmi_in_color_weight = findViewById(R.id.text_view_showing_the_bmi_in_color_weight);
        View location_above_bmi_weight = findViewById(R.id.location_above_bmi_weight);
        if(Payment_processer.getInstance().state_of_the_user()){
            SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
            float height = sharedPreferences.getFloat("height_start", 0);
            float bmi = return_the_current_weight() / ((height / 100) * (height / 100));
            text_view_showing_the_bmi_in_color_weight.setText(String.format("%.1f", bmi));
            if (bmi < 18.5) {
                location_above_bmi_weight.setBackgroundResource(R.drawable.location_drawable_blue);
                text_view_showing_the_bmi_in_color_weight.setTextColor(Color.parseColor("#33b5e5"));
            } else if (bmi <= 25) {
                location_above_bmi_weight.setBackgroundResource(R.drawable.location_drawable_green);
                text_view_showing_the_bmi_in_color_weight.setTextColor(Color.parseColor("#32CD32"));
            } else {
                location_above_bmi_weight.setBackgroundResource(R.drawable.location_drawble_red);
                text_view_showing_the_bmi_in_color_weight.setTextColor(Color.parseColor("#ffff4444"));
            }
            float percent_location = (bmi - 13.5f) / (30f - 13.5f);
            if (percent_location < 0) {
                percent_location = 0;
            } else if (percent_location > 1) {
                percent_location = 1;
            }
            ConstraintLayout constraintLayout = findViewById(R.id.constraint_layout_showing_the_info_of_bmi);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.setHorizontalBias(location_above_bmi_weight.getId(), percent_location);
            constraintSet.applyTo(constraintLayout);
        } else {
            Random random = new Random();
            int bmi = random.nextInt(35);
            text_view_showing_the_bmi_in_color_weight.setText(String.valueOf(bmi));
            if (bmi < 18.5) {
                location_above_bmi_weight.setBackgroundResource(R.drawable.location_drawable_blue);
                text_view_showing_the_bmi_in_color_weight.setTextColor(Color.parseColor("#33b5e5"));
            } else if (bmi <= 25) {
                location_above_bmi_weight.setBackgroundResource(R.drawable.location_drawable_green);
                text_view_showing_the_bmi_in_color_weight.setTextColor(Color.parseColor("#32CD32"));
            } else {
                location_above_bmi_weight.setBackgroundResource(R.drawable.location_drawble_red);
                text_view_showing_the_bmi_in_color_weight.setTextColor(Color.parseColor("#ffff4444"));
            }
            float percent_location = (bmi - 13.5f) / (30f - 13.5f);
            if (percent_location < 0) {
                percent_location = 0;
            } else if (percent_location > 1) {
                percent_location = 1;
            }
            ConstraintLayout constraintLayout = findViewById(R.id.constraint_layout_showing_the_info_of_bmi);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.setHorizontalBias(location_above_bmi_weight.getId(), percent_location);
            constraintSet.applyTo(constraintLayout);
        }
    }

    private void stats_at_the_bottom() {
        TextView text_view_saying_the_actual_weight_beside_bmi = findViewById(R.id.text_view_saying_the_actual_weight_beside_bmi);
        TextView text_view_saying_the_actula_entries_behing_line = findViewById(R.id.text_view_saying_the_actula_entries_behing_line);
        TextView text_saying_all_time_high_stats_but_teh_actual_number = findViewById(R.id.text_saying_all_time_high_stats_but_teh_actual_number);
        TextView text_saying_all_time_low_stats_but_thre_real_low = findViewById(R.id.text_saying_all_time_low_stats_but_thre_real_low);
        TextView this_week_gain_or_loss_but_the_real_number = findViewById(R.id.this_week_gain_or_loss_but_the_real_number);
        TextView this_month_gain_or_loss_but_the_real_number = findViewById(R.id.this_month_gain_or_loss_but_the_real_number);
        TextView total_gain_loss_but_only_text_showing_real_number = findViewById(R.id.total_gain_loss_but_only_text_showing_real_number);
        if(Payment_processer.getInstance().state_of_the_user()){
            SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
            float height = sharedPreferences.getFloat("height_start", 0);
            String units = sharedPreferences.getString("units", "metric");
            float start_weight = sharedPreferences.getFloat("start_weight", 0);
            float lower_bmi_weight = 18.5f * ((height / 100) * (height / 100));
            float higher_bmi_weight = 25f * ((height / 100) * (height / 100));
            if (units != null && units.equals("metric")) {
                text_view_saying_the_actual_weight_beside_bmi.setText(String.format("%.1fkg - %.1fkg", lower_bmi_weight, higher_bmi_weight));
                text_view_saying_the_actula_entries_behing_line.setText(String.valueOf(get_the_entries()));
                text_saying_all_time_high_stats_but_teh_actual_number.setText(String.format("%.1fkg", get_the_all_time_high()));
                text_saying_all_time_low_stats_but_thre_real_low.setText(String.format("%.1fkg", get_the_lowest_weight_in_a_list()));
                this_week_gain_or_loss_but_the_real_number.setText(String.format("%.1fkg", Math.abs(this_week_absolute_max() - this_week_absolute())));
                this_month_gain_or_loss_but_the_real_number.setText(String.format("%.1fkg", Math.abs(this_month_absolute_max() - this_month_absolute())));
                total_gain_loss_but_only_text_showing_real_number.setText(String.format("%.1fkg", Math.abs(return_the_current_weight() - start_weight)));
            } else if (units != null && units.equals("imperial")) {
                text_view_saying_the_actual_weight_beside_bmi.setText(String.format("%.1flb - %.1flb", lower_bmi_weight * 2.205f, higher_bmi_weight * 2.205f));
                text_view_saying_the_actula_entries_behing_line.setText(String.valueOf(get_the_entries()));
                text_saying_all_time_high_stats_but_teh_actual_number.setText(String.format("%.1flb", get_the_all_time_high() * 2.205f));
                text_saying_all_time_low_stats_but_thre_real_low.setText(String.format("%.1flb", get_the_lowest_weight_in_a_list() * 2.205f));
                this_week_gain_or_loss_but_the_real_number.setText(String.format("%.1flb", Math.abs(this_week_absolute_max() - this_week_absolute()) * 2.205f));
                this_month_gain_or_loss_but_the_real_number.setText(String.format("%.1flb", Math.abs(this_month_absolute_max() - this_month_absolute()) * 2.205f));
                total_gain_loss_but_only_text_showing_real_number.setText(String.format("%.1flb", Math.abs(return_the_current_weight() - start_weight) * 2.205f));
            }
            if (this_week_absolute_max() > this_week_absolute()) {
                this_week_gain_or_loss_but_the_real_number.setTextColor(Color.parseColor("#ffff4444"));
            } else if (this_week_absolute_max() < this_week_absolute()) {
                this_week_gain_or_loss_but_the_real_number.setTextColor(Color.parseColor("#32CD32"));
            } else {
                this_week_gain_or_loss_but_the_real_number.setTextColor(Color.parseColor("#607D8B"));
            }
            if (this_month_absolute_max() > this_month_absolute()) {
                this_month_gain_or_loss_but_the_real_number.setTextColor(Color.parseColor("#ffff4444"));
            } else if (this_month_absolute_max() < this_month_absolute()) {
                this_month_gain_or_loss_but_the_real_number.setTextColor(Color.parseColor("#32CD32"));
            } else {
                this_month_gain_or_loss_but_the_real_number.setTextColor(Color.parseColor("#607D8B"));
            }
            if (return_the_current_weight() > start_weight) {
                total_gain_loss_but_only_text_showing_real_number.setTextColor(Color.parseColor("#ffff4444"));
            } else if (return_the_current_weight() < start_weight) {
                total_gain_loss_but_only_text_showing_real_number.setTextColor(Color.parseColor("#32CD32"));
            } else {
                total_gain_loss_but_only_text_showing_real_number.setTextColor(Color.parseColor("#607D8B"));
            }
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
            String units = sharedPreferences.getString("units", "metric");
            Random random = new Random();
            int low = random.nextInt(90) + 10;
            int high = random.nextInt(90)+low;
            int entries = random.nextInt(100);
            int all_time_low = random.nextInt(100);
            int all_time_high = random.nextInt(100) + all_time_low;
            int week_gain = random.nextInt(5);
            int month_gain = random.nextInt(5) + week_gain;
            int all_gain = random.nextInt(10) + month_gain;
            if (units != null && units.equals("metric")) {
                text_view_saying_the_actual_weight_beside_bmi.setText(String.valueOf(low).concat("kg").concat(" - ").concat(String.valueOf(high)).concat("kg"));
                text_view_saying_the_actula_entries_behing_line.setText(String.valueOf(entries));
                text_saying_all_time_high_stats_but_teh_actual_number.setText(String.valueOf(all_time_high).concat("kg"));
                text_saying_all_time_low_stats_but_thre_real_low.setText(String.valueOf(all_time_low).concat("kg"));
                this_week_gain_or_loss_but_the_real_number.setText(String.valueOf(week_gain).concat("kg"));
                this_month_gain_or_loss_but_the_real_number.setText(String.valueOf(month_gain).concat("kg"));
                total_gain_loss_but_only_text_showing_real_number.setText(String.valueOf(all_gain).concat("kg"));
            } else if (units != null && units.equals("imperial")) {
                text_view_saying_the_actual_weight_beside_bmi.setText(String.valueOf(low).concat("lb").concat(" - ").concat(String.valueOf(high)).concat("lb"));
                text_view_saying_the_actula_entries_behing_line.setText(String.valueOf(entries));
                text_saying_all_time_high_stats_but_teh_actual_number.setText(String.valueOf(all_time_high).concat("lb"));
                text_saying_all_time_low_stats_but_thre_real_low.setText(String.valueOf(all_time_low).concat("lb"));
                this_week_gain_or_loss_but_the_real_number.setText(String.valueOf(week_gain).concat("lb"));
                this_month_gain_or_loss_but_the_real_number.setText(String.valueOf(month_gain).concat("lb"));
                total_gain_loss_but_only_text_showing_real_number.setText(String.valueOf(all_gain).concat("lb"));
            }
        }
    }

    private int get_the_entries() {
        SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
        String weight = sharedPreferences.getString("weight", "");
        if (weight != null) {
            String[] split_for_length = weight.split("split");
            return split_for_length.length;
        } else {
            return 0;
        }
    }

    private float get_the_lowest_weight_in_a_list() {
        SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
        String weight = sharedPreferences.getString("weight", "");
        float start_weight = sharedPreferences.getFloat("start_weight", 0);
        ArrayList<Float> lowest_weight_list = new ArrayList<>();
        if (weight != null) {
            String[] split_for_length = weight.split("split");
            for (int i = 0; i < split_for_length.length; i++) {
                String[] second_split = split_for_length[i].split("small_divide");
                lowest_weight_list.add(Float.parseFloat(second_split[1]));
            }
            float lowest_weight = Collections.min(lowest_weight_list);
            if (lowest_weight <= start_weight) {
                return lowest_weight;
            } else {
                return start_weight;
            }
        } else {
            return 0;
        }
    }

    private float get_the_all_time_high() {
        SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
        String weight = sharedPreferences.getString("weight", "");
        float start_weight = sharedPreferences.getFloat("start_weight", 0);
        ArrayList<Float> lowest_weight_list = new ArrayList<>();
        if (weight != null) {
            String[] split_for_length = weight.split("split");
            for (int i = 0; i < split_for_length.length; i++) {
                String[] second_split = split_for_length[i].split("small_divide");
                lowest_weight_list.add(Float.parseFloat(second_split[1]));
            }
            float highest_weight = Collections.max(lowest_weight_list);
            if (highest_weight >= start_weight) {
                return highest_weight;
            } else {
                return start_weight;
            }
        } else {
            return 0;
        }
    }

    private float this_week_absolute() {
        SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
        String weight = sharedPreferences.getString("weight", "");
        float start_weight = sharedPreferences.getFloat("start_weight", 0);
        ArrayList<Long> seconds_only_in_week = new ArrayList<>();
        long eight_days_ago = System.currentTimeMillis() - 691200000L;
        if (weight != null) {
            String[] split_for_length = weight.split("split");
            for (int i = 0; i < split_for_length.length; i++) {
                String[] second_split = split_for_length[i].split("small_divide");
                if (Long.parseLong(second_split[0]) > eight_days_ago) {
                    seconds_only_in_week.add(Long.parseLong(second_split[0]));
                }
            }
            if (seconds_only_in_week.size() > 0) {
                long oldest_second = Collections.min(seconds_only_in_week);
                for (int i = 0; i < split_for_length.length; i++) {
                    String[] second_split = split_for_length[i].split("small_divide");
                    if (Long.parseLong(second_split[0]) == oldest_second) {
                        return Float.parseFloat(second_split[1]);
                    }
                }
            } else {
                return start_weight;
            }
        }
        return 0;
    }

    private float this_month_absolute() {
        SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
        String weight = sharedPreferences.getString("weight", "");
        float start_weight = sharedPreferences.getFloat("start_weight", 0);
        ArrayList<Long> seconds_only_in_week = new ArrayList<>();
        long eight_days_ago = System.currentTimeMillis() - 2678400000L;
        if (weight != null) {
            String[] split_for_length = weight.split("split");
            for (int i = 0; i < split_for_length.length; i++) {
                String[] second_split = split_for_length[i].split("small_divide");
                if (Long.parseLong(second_split[0]) > eight_days_ago) {
                    seconds_only_in_week.add(Long.parseLong(second_split[0]));
                }
            }
            if (seconds_only_in_week.size() > 0) {
                long oldest_second = Collections.min(seconds_only_in_week);
                for (int i = 0; i < split_for_length.length; i++) {
                    String[] second_split = split_for_length[i].split("small_divide");
                    if (Long.parseLong(second_split[0]) == oldest_second) {
                        return Float.parseFloat(second_split[1]);
                    }
                }
            } else {
                return start_weight;
            }
        }
        return 0;
    }

    private float this_week_absolute_max() {
        SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
        String weight = sharedPreferences.getString("weight", "");
        float start_weight = sharedPreferences.getFloat("start_weight", 0);
        ArrayList<Long> seconds_only_in_week = new ArrayList<>();
        long eight_days_ago = System.currentTimeMillis() - 691200000L;
        if (weight != null) {
            String[] split_for_length = weight.split("split");
            for (int i = 0; i < split_for_length.length; i++) {
                String[] second_split = split_for_length[i].split("small_divide");
                if (Long.parseLong(second_split[0]) > eight_days_ago) {
                    seconds_only_in_week.add(Long.parseLong(second_split[0]));
                }
            }
            if (seconds_only_in_week.size() > 0) {
                long oldest_second = Collections.max(seconds_only_in_week);
                for (int i = 0; i < split_for_length.length; i++) {
                    String[] second_split = split_for_length[i].split("small_divide");
                    if (Long.parseLong(second_split[0]) == oldest_second) {
                        return Float.parseFloat(second_split[1]);
                    }
                }
            } else {
                return start_weight;
            }
        }
        return 0;
    }

    private float this_month_absolute_max() {
        SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
        String weight = sharedPreferences.getString("weight", "");
        float start_weight = sharedPreferences.getFloat("start_weight", 0);
        ArrayList<Long> seconds_only_in_week = new ArrayList<>();
        long eight_days_ago = System.currentTimeMillis() - 2678400000L;
        if (weight != null) {
            String[] split_for_length = weight.split("split");
            for (int i = 0; i < split_for_length.length; i++) {
                String[] second_split = split_for_length[i].split("small_divide");
                if (Long.parseLong(second_split[0]) > eight_days_ago) {
                    seconds_only_in_week.add(Long.parseLong(second_split[0]));
                }
            }
            if (seconds_only_in_week.size() > 0) {
                long oldest_second = Collections.max(seconds_only_in_week);
                for (int i = 0; i < split_for_length.length; i++) {
                    String[] second_split = split_for_length[i].split("small_divide");
                    if (Long.parseLong(second_split[0]) == oldest_second) {
                        return Float.parseFloat(second_split[1]);
                    }
                }
            } else {
                return start_weight;
            }
        }
        return 0;
    }

    @Override
    public void on_dialoge_gone_history() {
        set_the_start_weight();
        set_the_current_weight();
        set_the_goal_weight();
        set_up_the_weight_saying_absolute_weight();
        set_up_the_progress();
        calculate_and_set_bmi();
        stats_at_the_bottom();
        set_up_the_chart();
        check_if_text_is_off_the_screen();
    }

    private void set_up_the_chart() {
        String right_data = return_list_in_between();
        TextView text_to_say_no_data_in_the_middle = findViewById(R.id.text_to_say_no_data_in_the_middle);
        if (!right_data.equals("")) {
            SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
            String units = sharedPreferences.getString("units", "metric");
            String[] split_weight = right_data.split("split");
            LineChart line_chart_to_draw_weight = findViewById(R.id.line_chart_to_draw_weight);
            text_to_say_no_data_in_the_middle.setVisibility(View.INVISIBLE);
            line_chart_to_draw_weight.setVisibility(View.VISIBLE);
            line_chart_to_draw_weight.invalidate();
            line_chart_to_draw_weight.clear();
            line_chart_to_draw_weight.getXAxis().setDrawGridLines(false);
            line_chart_to_draw_weight.getAxisLeft().setDrawGridLines(true);
            line_chart_to_draw_weight.getAxisRight().setDrawGridLines(false);
            XAxis xAxis = line_chart_to_draw_weight.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            if (units != null && units.equals("metric")) {
                line_chart_to_draw_weight.getDescription().setText("weight in kg");
            } else if (units != null && units.equals("imperial")) {
                line_chart_to_draw_weight.getDescription().setText("weight in lb");
            }
            line_chart_to_draw_weight.getAxisRight().setEnabled(false);
            line_chart_to_draw_weight.getXAxis().setEnabled(true);
            line_chart_to_draw_weight.setScaleEnabled(false);
            line_chart_to_draw_weight.getLegend().setEnabled(false);
            line_chart_to_draw_weight.getAxisLeft().setTextColor(Color.parseColor("#607D8B"));
            line_chart_to_draw_weight.getAxisLeft().setAxisLineColor(Color.parseColor("#607D8B"));
            line_chart_to_draw_weight.getAxisLeft().setTextSize(17);
            line_chart_to_draw_weight.getXAxis().setTextColor(Color.parseColor("#000000"));
            line_chart_to_draw_weight.getXAxis().setAxisLineColor(Color.parseColor("#000000"));
            line_chart_to_draw_weight.getXAxis().setTextSize(8);
            line_chart_to_draw_weight.getXAxis().setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            //line_chart_to_draw_weight.getAxisLeft().setAxisMinimum(0);
            //line_chart_to_draw_weight.getAxisLeft().setAxisMaximum(37);
            line_chart_to_draw_weight.getAxisLeft().setDrawAxisLine(false);
            //line_chart_to_draw_weight.getXAxis().setEnabled(false);
            line_chart_to_draw_weight.getAxisLeft().setLabelCount(5, true);
            //line_chart_to_draw_weight.getXAxis().setLabelCount(5, true);
            xAxis.setGranularity(1f);
            line_chart_to_draw_weight.setExtraBottomOffset(5);
            line_chart_to_draw_weight.setExtraRightOffset(15);
            ArrayList<Entry> y_values = new ArrayList<>();
            y_values.clear();
            Calendar calendar = Calendar.getInstance();
            final ArrayList<String> axis_values = new ArrayList<>();
            if (units != null && units.equals("metric")) {
                for (int i = 0; i < split_weight.length; i++) {
                    String[] small_split = split_weight[i].split("small_divide");
                    y_values.add(new Entry(i, Float.parseFloat(small_split[1])));
                    calendar.setTimeInMillis(Long.parseLong(small_split[0]));
                    axis_values.add(return_month_short(calendar.get(Calendar.MONTH) + 1).concat(" ").concat(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))));
                }
            } else if (units != null && units.equals("imperial")) {
                for (int i = 0; i < split_weight.length; i++) {
                    String[] small_split = split_weight[i].split("small_divide");
                    y_values.add(new Entry(i, Float.parseFloat(small_split[1]) * 2.205f));
                    calendar.setTimeInMillis(Long.parseLong(small_split[0]));
                    axis_values.add(return_month_short(calendar.get(Calendar.MONTH) + 1).concat("").concat(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))));
                }
            }

            /*y_values.add(new Entry(0, 10));
            y_values.add(new Entry(1, 20));
            y_values.add(new Entry(2, 8));
            y_values.add(new Entry(3, 26));
            y_values.add(new Entry(4, 37));*/
            xAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    if (axis_values.size() > value) {
                        return axis_values.get((int) value);
                    } else {
                        return "";
                    }
                }
            });
            if (line_chart_to_draw_weight.getAxisLeft().getAxisMinimum() <= return_lower_goal() + 5) {
                LimitLine llRange = new LimitLine(return_lower_goal(), "");
                llRange.setLineColor(Color.parseColor("#32CD32")); // 32CD32
                llRange.setLineWidth(2.5f);
                line_chart_to_draw_weight.getAxisLeft().addLimitLine(llRange);
                if (line_chart_to_draw_weight.getAxisLeft().getAxisMinimum() > return_lower_goal()) {
                    line_chart_to_draw_weight.getAxisLeft().setAxisMinimum(return_lower_goal());
                }
            }
            if (line_chart_to_draw_weight.getAxisLeft().getAxisMaximum() >= return_upper_goal() - 5) {
                LimitLine llRange = new LimitLine(return_upper_goal(), "");
                llRange.setLineColor(Color.parseColor("#ffff4444")); // 32CD32
                llRange.setLineWidth(2.5f);
                line_chart_to_draw_weight.getAxisLeft().addLimitLine(llRange);
                if (line_chart_to_draw_weight.getAxisLeft().getAxisMaximum() < return_upper_goal()) {
                    line_chart_to_draw_weight.getAxisLeft().setAxisMaximum(return_upper_goal());
                }
            }
            line_chart_to_draw_weight.getXAxis().setAxisMinimum(-0.2f);
            line_chart_to_draw_weight.getAxisLeft().setDrawLimitLinesBehindData(true);
            xAxis.setCenterAxisLabels(false);
            LineDataSet lineDataSet = new LineDataSet(y_values, "data");
            lineDataSet.setColor(Color.parseColor("#607D8B"));
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.clear();
            dataSets.add(lineDataSet);
            LineData data = new LineData(dataSets);
            line_chart_to_draw_weight.setData(data);
            line_chart_to_draw_weight.getData().setHighlightEnabled(false);
            lineDataSet.setLineWidth(3.5f);
            lineDataSet.setCircleRadius(8f);
            lineDataSet.setCircleHoleRadius(3.5f);
            lineDataSet.setCircleHoleColor(Color.WHITE);
            lineDataSet.setCircleColor(Color.parseColor("#B3607D8B"));
            lineDataSet.setDrawValues(false);
        } else {
            LineChart line_chart_to_draw_weight = findViewById(R.id.line_chart_to_draw_weight);
            text_to_say_no_data_in_the_middle.setVisibility(View.VISIBLE);
            line_chart_to_draw_weight.setVisibility(View.INVISIBLE);
        }
    }

    private void set_up_spinner() {
        Spinner spinner_to_choose_dates_for_chart = findViewById(R.id.spinner_to_choose_dates_for_chart);
        spinner_to_choose_dates_for_chart.setOnItemSelectedListener(this);
        ArrayList<String> spinner_list = new ArrayList<>();
        spinner_list.add("one week");
        spinner_list.add("two weeks");
        spinner_list.add("three weeks");
        spinner_list.add("one month");
        spinner_list.add("two month");
        spinner_list.add("three months");
        spinner_list.add("four months");
        spinner_list.add("five months");
        spinner_list.add("six months");
        spinner_list.add("one year");
        spinner_list.add("All");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner_list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_to_choose_dates_for_chart.setAdapter(dataAdapter);
        milli_start = get_the_past_milli(7);
        milli_end = System.currentTimeMillis();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 0) {
            milli_start = get_the_past_milli(7);
            milli_end = System.currentTimeMillis();
        } else if (i == 1) {
            milli_start = get_the_past_milli(14);
            milli_end = System.currentTimeMillis();
        } else if (i == 2) {
            milli_start = get_the_past_milli(21);
            milli_end = System.currentTimeMillis();
        } else if (i == 3) {
            milli_start = get_the_past_milli(30);
            milli_end = System.currentTimeMillis();
        } else if (i == 4) {
            milli_start = get_the_past_milli(60);
            milli_end = System.currentTimeMillis();
        } else if (i == 5) {
            milli_start = get_the_past_milli(90);
            milli_end = System.currentTimeMillis();
        } else if (i == 6) {
            milli_start = get_the_past_milli(120);
            milli_end = System.currentTimeMillis();
        } else if (i == 7) {
            milli_start = get_the_past_milli(150);
            milli_end = System.currentTimeMillis();
        } else if (i == 8) {
            milli_start = get_the_past_milli(180);
            milli_end = System.currentTimeMillis();
        } else if (i == 9) {
            milli_start = get_the_past_milli(365);
            milli_end = System.currentTimeMillis();
        } else if (i == 10) {
            milli_start = 0;
            milli_end = System.currentTimeMillis();
        }
        set_up_the_chart();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private long get_the_past_milli(int days) {
        return System.currentTimeMillis() - (86400000L * (days + 1));
    }

    private String return_list_in_between() {
        SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
        String weight = sharedPreferences.getString("weight", "");
        if (weight != null) {
            String[] split_weight = weight.split("split");
            String return_string = "";
            ArrayList<Long> seconds = new ArrayList<>();
            for (int i = 0; i < split_weight.length; i++) {
                String[] split_small = split_weight[i].split("small_divide");
                if (Long.parseLong(split_small[0]) >= milli_start && Long.parseLong(split_small[0]) <= milli_end) {
                    seconds.add(Long.parseLong(split_small[0]));
                }
            }
            Collections.sort(seconds);
            for (int j = 0; j < seconds.size(); j++) {
                for (int i = 0; i < split_weight.length; i++) {
                    String[] split_small = split_weight[i].split("small_divide");
                    if (seconds.get(j) == Long.parseLong(split_small[0])) {
                        return_string = return_string.concat(split_weight[i]).concat("split");
                        break;
                    }
                }
            }
            return return_string;
        }
        return "";
    }

    private String return_month_short(int month) {
        if (month == 1) {
            return "Jan";
        } else if (month == 2) {
            return "Feb.";
        } else if (month == 3) {
            return "Mar.";
        } else if (month == 4) {
            return "Apr.";
        } else if (month == 5) {
            return "May";
        } else if (month == 6) {
            return "Jun.";
        } else if (month == 7) {
            return "Jul.";
        } else if (month == 8) {
            return "Aug.";
        } else if (month == 9) {
            return "Sep.";
        } else if (month == 10) {
            return "Oct.";
        } else if (month == 11) {
            return "Nov.";
        } else {
            return "Dec.";
        }
    }

    private float return_lower_goal() {
        SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
        float start_weight = sharedPreferences.getFloat("start_weight", 0);
        float goal = sharedPreferences.getFloat("goal_start", 0);
        String units = sharedPreferences.getString("units", "metric");
        float difference = Math.abs(start_weight - goal);
        if (units != null && units.equals("metric")) {
            return start_weight - difference;
        } else if (units != null && units.equals("imperial")) {
            return (start_weight - difference) * 2.205f;
        }
        return 0;
    }

    private float return_upper_goal() {
        SharedPreferences sharedPreferences = getSharedPreferences("weight_emergency", MODE_PRIVATE);
        float start_weight = sharedPreferences.getFloat("start_weight", 0);
        float goal = sharedPreferences.getFloat("goal_start", 0);
        String units = sharedPreferences.getString("units", "metric");
        float difference = Math.abs(start_weight - goal);
        if (units != null && units.equals("metric")) {
            return start_weight + difference;
        } else if (units != null && units.equals("imperial")) {
            return (start_weight + difference) * 2.505f;
        }
        return 0;
    }

    private void share_button_listen() {
        final Button button_to_share_current_goal_and_start_weight_in_weight = findViewById(R.id.button_to_share_current_goal_and_start_weight_in_weight);
        final Button button_to_share_the_weight_chart_in_weight = findViewById(R.id.button_to_share_the_weight_chart_in_weight);
        final Button button_to_share_the_gain_loss_in_weight = findViewById(R.id.button_to_share_the_gain_loss_in_weight);
        final Button button_to_share_the_bmi_across_the_three_colors = findViewById(R.id.button_to_share_the_bmi_across_the_three_colors);
        final Button button_to_share_stats_in_weight_card = findViewById(R.id.button_to_share_stats_in_weight_card);
        final CardView card_view_to_show_best_current_and_goal_weight_in_weight = findViewById(R.id.card_view_to_show_best_current_and_goal_weight_in_weight);
        final CardView card_to_show_weight_graph_in_weights = findViewById(R.id.card_to_show_weight_graph_in_weights);
        final CardView card_to_show_gain_or_loss_in_weight = findViewById(R.id.card_to_show_gain_or_loss_in_weight);
        final CardView card_to_show_bmi_in_the_weight_with_colrs = findViewById(R.id.card_to_show_bmi_in_the_weight_with_colrs);
        final CardView card_to_show_stats_in_weight = findViewById(R.id.card_to_show_stats_in_weight);
        final Button add_entry_button_under_chart = findViewById(R.id.add_entry_button_under_chart);
        button_to_share_current_goal_and_start_weight_in_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_to_share_current_goal_and_start_weight_in_weight.setVisibility(View.INVISIBLE);
                share_screen_shot(screenShot(card_view_to_show_best_current_and_goal_weight_in_weight));
                button_to_share_current_goal_and_start_weight_in_weight.setVisibility(View.VISIBLE);
            }
        });
        button_to_share_the_weight_chart_in_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_entry_button_under_chart.setVisibility(View.GONE);
                button_to_share_the_weight_chart_in_weight.setVisibility(View.GONE);
                add_entry_button_under_chart.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        add_entry_button_under_chart.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        share_screen_shot(screenShot(card_to_show_weight_graph_in_weights));
                        add_entry_button_under_chart.setVisibility(View.VISIBLE);
                        button_to_share_the_weight_chart_in_weight.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        button_to_share_the_gain_loss_in_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_to_share_the_gain_loss_in_weight.setVisibility(View.INVISIBLE);
                share_screen_shot(screenShot(card_to_show_gain_or_loss_in_weight));
                button_to_share_the_gain_loss_in_weight.setVisibility(View.VISIBLE);
            }
        });
        button_to_share_the_bmi_across_the_three_colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_to_share_the_bmi_across_the_three_colors.setVisibility(View.INVISIBLE);
                share_screen_shot(screenShot(card_to_show_bmi_in_the_weight_with_colrs));
                button_to_share_the_bmi_across_the_three_colors.setVisibility(View.VISIBLE);
            }
        });
        button_to_share_stats_in_weight_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_to_share_stats_in_weight_card.setVisibility(View.INVISIBLE);
                share_screen_shot(screenShot(card_to_show_stats_in_weight));
                button_to_share_stats_in_weight_card.setVisibility(View.VISIBLE);
            }
        });
    }

    private Bitmap screenShot(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    private void share_the_bitmap(Bitmap bitmap) {
        // save bitmap to cache directory
        try {
            File cachePath = new File(this.getCacheDir(), "images");
            cachePath.mkdirs(); // don't forget to make the directory
            FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        File imagePath = new File(this.getCacheDir(), "images");
        File newFile = new File(imagePath, "image.png");
        Uri contentUri = FileProvider.getUriForFile(this, "com.easyhabitsapp.android.fileprovider", newFile);

        if (contentUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
            shareIntent.setDataAndType(contentUri, this.getContentResolver().getType(contentUri));
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            startActivity(Intent.createChooser(shareIntent, "Share"));
        }
    }

    private void share_screen_shot(Bitmap bitmap) {
        final ImageView view_to_pass_the_real_view_to_for_screen_shot = findViewById(R.id.view_to_pass_the_real_view_to_for_screen_shot);
        //final ConstraintLayout layou_to_show_screen_shot_in_good_habits = getView().findViewById(R.id.layou_to_show_screen_shot_in_good_habits);
        final ConstraintLayout layou_to_show_screen_shot_in_good_habits = findViewById(R.id.layou_to_show_screen_shot_in_good_habits);
        view_to_pass_the_real_view_to_for_screen_shot.setImageBitmap(bitmap);
        layou_to_show_screen_shot_in_good_habits.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                layou_to_show_screen_shot_in_good_habits.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                share_the_bitmap(screenShot(layou_to_show_screen_shot_in_good_habits));
                view_to_pass_the_real_view_to_for_screen_shot.setImageDrawable(null);
            }
        });
    }

    private void check_if_text_is_off_the_screen() {
        final ConstraintLayout weight_in_emergency = findViewById(R.id.weight_in_emergency);
        final TextView text_view_showing_the_bmi_in_color_weight = findViewById(R.id.text_view_showing_the_bmi_in_color_weight);
        final CardView card_to_show_bmi_in_the_weight_with_colrs = findViewById(R.id.card_to_show_bmi_in_the_weight_with_colrs);
        ViewTreeObserver viewTreeObserver = weight_in_emergency.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    weight_in_emergency.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int[] text_location_array = new int[2];
                    int[] card_location_array = new int[2];
                    text_view_showing_the_bmi_in_color_weight.getLocationOnScreen(text_location_array);
                    card_to_show_bmi_in_the_weight_with_colrs.getLocationOnScreen(card_location_array);
                    int text_location = text_location_array[0] + text_view_showing_the_bmi_in_color_weight.getMeasuredWidth();
                    int card_location = card_location_array[0] + card_to_show_bmi_in_the_weight_with_colrs.getMeasuredWidth();
                    if (text_location > card_location) {
                        connect_the_text(1);
                    } else {
                        connect_the_text(0);
                    }
                }
            });
        }
    }

    private void connect_the_text(int mode) {
        TextView text_view_showing_the_bmi_in_color_weight = findViewById(R.id.text_view_showing_the_bmi_in_color_weight);
        View location_above_bmi_weight = findViewById(R.id.location_above_bmi_weight);
        if (mode == 0) {
            ConstraintLayout constraintLayout = findViewById(R.id.constraint_layout_showing_the_info_of_bmi);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.clear(text_view_showing_the_bmi_in_color_weight.getId(), ConstraintSet.END);
            constraintSet.connect(text_view_showing_the_bmi_in_color_weight.getId(), ConstraintSet.END, location_above_bmi_weight.getId(), ConstraintSet.END, 0);
            constraintSet.connect(text_view_showing_the_bmi_in_color_weight.getId(), ConstraintSet.START, location_above_bmi_weight.getId(), ConstraintSet.START, 0);
            constraintSet.applyTo(constraintLayout);
        } else if (mode == 1) {
            ConstraintLayout constraintLayout = findViewById(R.id.constraint_layout_showing_the_info_of_bmi);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.clear(text_view_showing_the_bmi_in_color_weight.getId(), ConstraintSet.END);
            constraintSet.clear(text_view_showing_the_bmi_in_color_weight.getId(), ConstraintSet.START);
            constraintSet.connect(text_view_showing_the_bmi_in_color_weight.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, (int) convertDpToPixel(5f, this));
            constraintSet.applyTo(constraintLayout);
        }
    }

    private static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private void hide_the_info() {
        ConstraintLayout layout_to_blur_in_the_weight_tracker = findViewById(R.id.layout_to_blur_in_the_weight_tracker);
        ConstraintLayout constraint_layout_showing_the_info_of_bmi = findViewById(R.id.constraint_layout_showing_the_info_of_bmi);
        ConstraintLayout layout_containting_all_the_infor_for_weight_in_gain_loss_weight_loss = findViewById(R.id.layout_containting_all_the_infor_for_weight_in_gain_loss_weight_loss);
        Button this_is_only_for_chart_iin_weight_in_gain_loss_in_weight = findViewById(R.id.this_is_only_for_chart_iin_weight_in_gain_loss_in_weight);
        Button this_is_only_for_chart_iin_weight_in_bmi_weight = findViewById(R.id.this_is_only_for_chart_iin_weight_in_bmi_weight);
        Button this_is_only_for_chart = findViewById(R.id.this_is_only_for_chart);
        if (Payment_processer.getInstance().state_of_the_user()) {
            layout_to_blur_in_the_weight_tracker.setAlpha(1f);
            constraint_layout_showing_the_info_of_bmi.setAlpha(1f);
            layout_containting_all_the_infor_for_weight_in_gain_loss_weight_loss.setAlpha(1f);
            this_is_only_for_chart_iin_weight_in_gain_loss_in_weight.setVisibility(View.INVISIBLE);
            this_is_only_for_chart_iin_weight_in_bmi_weight.setVisibility(View.INVISIBLE);
            this_is_only_for_chart.setVisibility(View.INVISIBLE);
        } else {
            layout_to_blur_in_the_weight_tracker.setAlpha(0.2f);
            constraint_layout_showing_the_info_of_bmi.setAlpha(0.2f);
            layout_containting_all_the_infor_for_weight_in_gain_loss_weight_loss.setAlpha(0.2f);
            this_is_only_for_chart_iin_weight_in_gain_loss_in_weight.setVisibility(View.VISIBLE);
            this_is_only_for_chart_iin_weight_in_bmi_weight.setVisibility(View.VISIBLE);
            this_is_only_for_chart.setVisibility(View.VISIBLE);
        }
    }

    private void buy_premuim_button_listen(){
        Button this_is_only_for_chart_iin_weight_in_gain_loss_in_weight = findViewById(R.id.this_is_only_for_chart_iin_weight_in_gain_loss_in_weight);
        Button this_is_only_for_chart_iin_weight_in_bmi_weight = findViewById(R.id.this_is_only_for_chart_iin_weight_in_bmi_weight);
        Button this_is_only_for_chart = findViewById(R.id.this_is_only_for_chart);
        this_is_only_for_chart_iin_weight_in_gain_loss_in_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy_premuim_was_clicked();
            }
        });
        this_is_only_for_chart_iin_weight_in_bmi_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy_premuim_was_clicked();
            }
        });
        this_is_only_for_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy_premuim_was_clicked();
            }
        });
    }

    private void buy_premuim_was_clicked(){
        /*Buy_premuim buy_premuim = new Buy_premuim("add more than 1 counter", true, "activity");
        hide_activity();
        buy_premuim.set_the_function(new Buy_premuim.show_the_activity() {
            @Override
            public void show_the_activity_function() {
                show_activity();
            }
        });
        getSupportFragmentManager().beginTransaction().add(R.id.hold_buy_premuim_in_weight_tracker, buy_premuim, "buy premium").show(buy_premuim).commit();*/
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(am_i_in_fragment){
            show_activity();
            hide_buy_premuim();
        } else {
            this.finish();
        }
    }

    private void hide_activity(){
        am_i_in_fragment = true;
        ScrollView scroll_view_to_show_the_weight = findViewById(R.id.scroll_view_to_show_the_weight);
        scroll_view_to_show_the_weight.setVisibility(View.INVISIBLE);
    }

    private void show_activity(){
        am_i_in_fragment = false;
        ScrollView scroll_view_to_show_the_weight = findViewById(R.id.scroll_view_to_show_the_weight);
        scroll_view_to_show_the_weight.setVisibility(View.VISIBLE);
    }

    private void hide_buy_premuim(){
        Buy_premuim old_fragment = (Buy_premuim) getSupportFragmentManager().findFragmentByTag("buy premium");
        if (old_fragment != null) {
            getSupportFragmentManager().beginTransaction().remove(old_fragment).commit();
        }
    }


}