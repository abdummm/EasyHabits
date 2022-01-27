package com.easyhabitsapp.android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class mood_tracker extends Fragment implements PopupMenu.OnMenuItemClickListener {
    private Button calender_button_showing_shadow_1;
    private Button calender_button_showing_shadow_2;
    private Button calender_button_showing_shadow_3;
    private Button calender_button_showing_shadow_4;
    private Button calender_button_showing_shadow_5;
    private Button calender_button_showing_shadow_6;
    private Button calender_button_showing_shadow_7;
    private Button calender_button_showing_shadow_8;
    private Button calender_button_showing_shadow_9;
    private Button calender_button_showing_shadow_10;
    private Button calender_button_showing_shadow_11;
    private Button calender_button_showing_shadow_12;
    private Button calender_button_showing_shadow_13;
    private Button calender_button_showing_shadow_14;
    private Button calender_button_showing_shadow_15;
    private Button calender_button_showing_shadow_16;
    private Button calender_button_showing_shadow_17;
    private Button calender_button_showing_shadow_18;
    private Button calender_button_showing_shadow_19;
    private Button calender_button_showing_shadow_20;
    private Button calender_button_showing_shadow_21;
    private Button calender_button_showing_shadow_22;
    private Button calender_button_showing_shadow_23;
    private Button calender_button_showing_shadow_24;
    private Button calender_button_showing_shadow_25;
    private Button calender_button_showing_shadow_26;
    private Button calender_button_showing_shadow_27;
    private Button calender_button_showing_shadow_28;
    private Button calender_button_showing_shadow_29;
    private Button calender_button_showing_shadow_30;
    private Button calender_button_showing_shadow_31;
    private Button calender_button_showing_shadow_32;
    private Button calender_button_showing_shadow_33;
    private Button calender_button_showing_shadow_34;
    private Button calender_button_showing_shadow_35;
    private Button calender_button_showing_shadow_36;
    private Button calender_button_showing_shadow_37;
    private String color_the_today;
    private Drawable first_part_rectangle_calender;
    private Drawable middle_part_rectangle_calender;
    private Drawable last_part_rectangle_calender;
    private Drawable first_and_last_part_rectangle_calender;
    private String[] colors;
    private HashMap<Long, Integer> history_of_mood;
    private Long start_date;
    private int color;
    private int[] modes_for_four_drawable = new int[37];
    private View[] list_of_all_unders;
    private int month_for_mood_chart = -1;
    private int year_for_mood_chart = -1;
    private LineChart line_chart_for_streak;
    private View[] list_of_all_the_calender_views_in_mood;
    private int year_global;
    private int month_global;
    private int day_global;

    public mood_tracker() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mood_tracker, container, false);
    }

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        call_me_at_start();
    }*/

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        call_me_at_start();
    }

    private void call_me_at_start() {
        add_to_list();
        get_the_color();
        color_the_stuff();
        define_start_date();
        //printall();
        //open_the_and_shared();
        //displaying_streak_for_user();
        // back_button_listen();
        //color_the_stuff();
        // declare_start_date();
        //put_all_the_relapses_into_a_array_list();
        add_all_to_a_list();
        color_the_4_drawables();
        define_the_buttons();
        set_the_first_day_of_the_week_number();
        set_the_days_on_the_real_text();
        back_and_front_button_listen();
        color_the_today_value();
        calender_button_listeners();
        remove_the_hiding_buttons();
        color_today();
        color_the_calender();
        color_the_under();
        set_up_share_and_yes_or_no_buttons();
        yes_and_no_button_listen_under_the_calender();
        divide_it_into_weeks();
        clear_the_middle();
        make_the_middle_come_again();
        hide_or_un_hide_the_button(0);
        color_the_button_under_the_calender();
        color_the_under();
        color_the_middle();
        set_up_buttons_once();
        button_listen_at_the_top();
        make_everything_average_mood();
        draw_the_mood_line_chart();
        back_and_front_button_listen_for_the_graph_mood();
        set_all_the_faces_in_the_mood();
        draw_the_bar_for_average_mood();
        three_dot_button_in_bar_chart_listen();
        draw_pie_chart();
        add_the_views();
        forward_and_back_button_listen();
        restart_all_the_year_values();
        set_the_title_of_the_year_habit();
        set_the_leap_year();
        put_values_into_year_in_good_habits();
        set_the_buttons();
        watch_all_the_share_button();
        back_button_listen();
        three_dot_button_listen();
        fade_the_views();
        buy_premuim_button_listen();
        //add_all_to_a_list();
        /*add_to_array();
        calculate_the_average_streak();
        calculate_the_best_streak();
        calculate_the_current_streak();
        set_the_text_for_in_card();
        set_up_day_of_week_bar_chart();
        draw_pie_chart();
        set_up_the_various_streak_chart();
        setup_the_four_information_card();
        add_the_views();
        set_the_title_of_the_year_habit();
        forward_and_back_button_listen();
        restart_all_the_year_values();
        set_the_title_of_the_year_habit();
        set_the_leap_year();
        put_values_into_year_in_good_habits();
        set_the_buttons();
        three_dots_to_delete_or_edit_at_top();
        watch_all_the_share_button();*/
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            call_me_at_start();
        }
    }

    private void set_the_first_day_of_the_week_number() {
        if (getView() != null) {
            Calendar calender = Calendar.getInstance();
            TextView first_day_in_the_week_calender = getView().findViewById(R.id.first_day_in_the_week_calender);
            TextView second_day_in_the_week_calender = getView().findViewById(R.id.second_day_in_the_week_calender);
            TextView third_day_in_the_week_calender = getView().findViewById(R.id.third_day_in_the_week_calender);
            TextView fourth_day_in_the_week_calender = getView().findViewById(R.id.fourth_day_in_the_week_calender);
            TextView fifth_day_in_the_week_calender = getView().findViewById(R.id.fifth_day_in_the_week_calender);
            TextView sixth_day_in_the_week_calender = getView().findViewById(R.id.sixth_day_in_the_week_calender);
            TextView seventh_day_in_the_week_calender = getView().findViewById(R.id.seventh_day_in_the_week_calender);
            int year = calender.get(Calendar.YEAR);
            String month = return_month(calender.get(Calendar.MONTH));
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            month_and_year_in_calender_for_good_habits.setText(month.concat(" ").concat(String.valueOf(year)));
            if (calender.getFirstDayOfWeek() == Calendar.SUNDAY) {
                first_day_in_the_week_calender.setText("S");
                second_day_in_the_week_calender.setText("M");
                third_day_in_the_week_calender.setText("T");
                fourth_day_in_the_week_calender.setText("W");
                fifth_day_in_the_week_calender.setText("T");
                sixth_day_in_the_week_calender.setText("F");
                seventh_day_in_the_week_calender.setText("S");
            } else if (calender.getFirstDayOfWeek() == Calendar.MONDAY) {
                first_day_in_the_week_calender.setText("M");
                second_day_in_the_week_calender.setText("T");
                third_day_in_the_week_calender.setText("W");
                fourth_day_in_the_week_calender.setText("T");
                fifth_day_in_the_week_calender.setText("F");
                sixth_day_in_the_week_calender.setText("S");
                seventh_day_in_the_week_calender.setText("S");
            } else if (calender.getFirstDayOfWeek() == Calendar.TUESDAY) {
                first_day_in_the_week_calender.setText("T");
                second_day_in_the_week_calender.setText("W");
                third_day_in_the_week_calender.setText("T");
                fourth_day_in_the_week_calender.setText("F");
                fifth_day_in_the_week_calender.setText("S");
                sixth_day_in_the_week_calender.setText("S");
                seventh_day_in_the_week_calender.setText("M");
            } else if (calender.getFirstDayOfWeek() == Calendar.WEDNESDAY) {
                first_day_in_the_week_calender.setText("W");
                second_day_in_the_week_calender.setText("T");
                third_day_in_the_week_calender.setText("F");
                fourth_day_in_the_week_calender.setText("S");
                fifth_day_in_the_week_calender.setText("S");
                sixth_day_in_the_week_calender.setText("M");
                seventh_day_in_the_week_calender.setText("T");
            } else if (calender.getFirstDayOfWeek() == Calendar.THURSDAY) {
                first_day_in_the_week_calender.setText("T");
                second_day_in_the_week_calender.setText("F");
                third_day_in_the_week_calender.setText("S");
                fourth_day_in_the_week_calender.setText("S");
                fifth_day_in_the_week_calender.setText("M");
                sixth_day_in_the_week_calender.setText("T");
                seventh_day_in_the_week_calender.setText("W");
            } else if (calender.getFirstDayOfWeek() == Calendar.FRIDAY) {
                first_day_in_the_week_calender.setText("F");
                second_day_in_the_week_calender.setText("S");
                third_day_in_the_week_calender.setText("S");
                fourth_day_in_the_week_calender.setText("M");
                fifth_day_in_the_week_calender.setText("T");
                sixth_day_in_the_week_calender.setText("W");
                seventh_day_in_the_week_calender.setText("T");
            } else {
                first_day_in_the_week_calender.setText("S");
                second_day_in_the_week_calender.setText("S");
                third_day_in_the_week_calender.setText("M");
                fourth_day_in_the_week_calender.setText("T");
                fifth_day_in_the_week_calender.setText("W");
                sixth_day_in_the_week_calender.setText("T");
                seventh_day_in_the_week_calender.setText("F");
            }
        }
    }

    private String return_month(int month) {
        if (month == 0) {
            return "January";
        } else if (month == 1) {
            return "February";
        } else if (month == 2) {
            return "March";
        } else if (month == 3) {
            return "April";
        } else if (month == 4) {
            return "May";
        } else if (month == 5) {
            return "June";
        } else if (month == 6) {
            return "July";
        } else if (month == 7) {
            return "August";
        } else if (month == 8) {
            return "September";
        } else if (month == 9) {
            return "October";
        } else if (month == 10) {
            return "November";
        } else {
            return "December";
        }
    }

    private int return_first_day_of_month() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            Calendar calender_for_first_day_of_month_only = Calendar.getInstance();
            calender_for_first_day_of_month_only.set(Calendar.MONTH, return_month_string_to_int(splitter[0]));
            calender_for_first_day_of_month_only.set(Calendar.YEAR, Integer.parseInt(splitter[1]));
            calender_for_first_day_of_month_only.set(Calendar.DAY_OF_MONTH, calender_for_first_day_of_month_only.getActualMinimum(Calendar.DAY_OF_MONTH));
            return calender_for_first_day_of_month_only.get(Calendar.DAY_OF_WEEK);
        } else {
            return 0;
        }
    }

    private int return_last_day_of_month() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            Calendar calender_for_last_day_of_month_only = Calendar.getInstance();
            String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            calender_for_last_day_of_month_only.set(Calendar.MONTH, return_month_string_to_int(splitter[0]));
            calender_for_last_day_of_month_only.set(Calendar.YEAR, Integer.parseInt(splitter[1]));
            calender_for_last_day_of_month_only.set(Calendar.DAY_OF_MONTH, 1);
            return calender_for_last_day_of_month_only.getActualMaximum(Calendar.DAY_OF_MONTH);
        } else {
            return 1;
        }
    }

    private int return_month_string_to_int(String month) {
        if (month.equals("January")) {
            return 0;
        } else if (month.equals("February")) {
            return 1;
        } else if (month.equals("March")) {
            return 2;
        } else if (month.equals("April")) {
            return 3;
        } else if (month.equals("May")) {
            return 4;
        } else if (month.equals("June")) {
            return 5;
        } else if (month.equals("July")) {
            return 6;
        } else if (month.equals("August")) {
            return 7;
        } else if (month.equals("September")) {
            return 8;
        } else if (month.equals("October")) {
            return 9;
        } else if (month.equals("November")) {
            return 10;
        } else {
            return 11;
        }
    }

    private void set_the_days_on_the_real_text() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            Calendar calender_for_first_day_of_month_only = Calendar.getInstance();
            calender_for_first_day_of_month_only.set(Calendar.MONTH, return_month_string_to_int(splitter[0]));
            calender_for_first_day_of_month_only.set(Calendar.YEAR, Integer.parseInt(splitter[1]));
            if (calender_for_first_day_of_month_only.getFirstDayOfWeek() == Calendar.SUNDAY) {
                day_is_sunday();
            } else if (calender_for_first_day_of_month_only.getFirstDayOfWeek() == Calendar.MONDAY) {
                day_is_monday();
            } else if (calender_for_first_day_of_month_only.getFirstDayOfWeek() == Calendar.TUESDAY) {
                day_is_tuesday();
            } else if (calender_for_first_day_of_month_only.getFirstDayOfWeek() == Calendar.WEDNESDAY) {
                day_is_wednesday();
            } else if (calender_for_first_day_of_month_only.getFirstDayOfWeek() == Calendar.THURSDAY) {
                day_is_thursday();
            } else if (calender_for_first_day_of_month_only.getFirstDayOfWeek() == Calendar.FRIDAY) {
                day_is_friday();
            } else {
                day_is_saturday();
            }
        }
    }

    private void day_is_sunday() {
        int day = 1;
        if (return_first_day_of_month() == Calendar.SUNDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.MONDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.TUESDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.WEDNESDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.THURSDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.FRIDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void day_is_monday() {
        int day = 1;
        if (return_first_day_of_month() == Calendar.MONDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.TUESDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.WEDNESDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.THURSDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.FRIDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.SATURDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void day_is_tuesday() {
        int day = 1;
        if (return_first_day_of_month() == Calendar.TUESDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.WEDNESDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.THURSDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.FRIDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.SATURDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.SUNDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void day_is_wednesday() {
        int day = 1;
        if (return_first_day_of_month() == Calendar.WEDNESDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.THURSDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.FRIDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.SATURDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.SUNDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.MONDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void day_is_thursday() {
        int day = 1;
        if (return_first_day_of_month() == Calendar.THURSDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.FRIDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.SATURDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.SUNDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.MONDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.TUESDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void day_is_friday() {
        int day = 1;
        if (return_first_day_of_month() == Calendar.FRIDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.SATURDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.SUNDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.MONDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.TUESDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.WEDNESDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void day_is_saturday() {
        int day = 1;
        if (return_first_day_of_month() == Calendar.SATURDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.SUNDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.MONDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.TUESDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.WEDNESDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.THURSDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void define_the_buttons() {
        if (getView() != null) {
            calender_button_showing_shadow_1 = getView().findViewById(R.id.calender_button_showing_shadow_1);
            calender_button_showing_shadow_2 = getView().findViewById(R.id.calender_button_showing_shadow_2);
            calender_button_showing_shadow_3 = getView().findViewById(R.id.calender_button_showing_shadow_3);
            calender_button_showing_shadow_4 = getView().findViewById(R.id.calender_button_showing_shadow_4);
            calender_button_showing_shadow_5 = getView().findViewById(R.id.calender_button_showing_shadow_5);
            calender_button_showing_shadow_6 = getView().findViewById(R.id.calender_button_showing_shadow_6);
            calender_button_showing_shadow_7 = getView().findViewById(R.id.calender_button_showing_shadow_7);
            calender_button_showing_shadow_8 = getView().findViewById(R.id.calender_button_showing_shadow_8);
            calender_button_showing_shadow_9 = getView().findViewById(R.id.calender_button_showing_shadow_9);
            calender_button_showing_shadow_10 = getView().findViewById(R.id.calender_button_showing_shadow_10);
            calender_button_showing_shadow_11 = getView().findViewById(R.id.calender_button_showing_shadow_11);
            calender_button_showing_shadow_12 = getView().findViewById(R.id.calender_button_showing_shadow_12);
            calender_button_showing_shadow_13 = getView().findViewById(R.id.calender_button_showing_shadow_13);
            calender_button_showing_shadow_14 = getView().findViewById(R.id.calender_button_showing_shadow_14);
            calender_button_showing_shadow_15 = getView().findViewById(R.id.calender_button_showing_shadow_15);
            calender_button_showing_shadow_16 = getView().findViewById(R.id.calender_button_showing_shadow_16);
            calender_button_showing_shadow_17 = getView().findViewById(R.id.calender_button_showing_shadow_17);
            calender_button_showing_shadow_18 = getView().findViewById(R.id.calender_button_showing_shadow_18);
            calender_button_showing_shadow_19 = getView().findViewById(R.id.calender_button_showing_shadow_19);
            calender_button_showing_shadow_20 = getView().findViewById(R.id.calender_button_showing_shadow_20);
            calender_button_showing_shadow_21 = getView().findViewById(R.id.calender_button_showing_shadow_21);
            calender_button_showing_shadow_22 = getView().findViewById(R.id.calender_button_showing_shadow_22);
            calender_button_showing_shadow_23 = getView().findViewById(R.id.calender_button_showing_shadow_23);
            calender_button_showing_shadow_24 = getView().findViewById(R.id.calender_button_showing_shadow_24);
            calender_button_showing_shadow_25 = getView().findViewById(R.id.calender_button_showing_shadow_25);
            calender_button_showing_shadow_26 = getView().findViewById(R.id.calender_button_showing_shadow_26);
            calender_button_showing_shadow_27 = getView().findViewById(R.id.calender_button_showing_shadow_27);
            calender_button_showing_shadow_28 = getView().findViewById(R.id.calender_button_showing_shadow_28);
            calender_button_showing_shadow_29 = getView().findViewById(R.id.calender_button_showing_shadow_29);
            calender_button_showing_shadow_30 = getView().findViewById(R.id.calender_button_showing_shadow_30);
            calender_button_showing_shadow_31 = getView().findViewById(R.id.calender_button_showing_shadow_31);
            calender_button_showing_shadow_32 = getView().findViewById(R.id.calender_button_showing_shadow_32);
            calender_button_showing_shadow_33 = getView().findViewById(R.id.calender_button_showing_shadow_33);
            calender_button_showing_shadow_34 = getView().findViewById(R.id.calender_button_showing_shadow_34);
            calender_button_showing_shadow_35 = getView().findViewById(R.id.calender_button_showing_shadow_35);
            calender_button_showing_shadow_36 = getView().findViewById(R.id.calender_button_showing_shadow_36);
            calender_button_showing_shadow_37 = getView().findViewById(R.id.calender_button_showing_shadow_37);
        }
    }

    private void back_and_front_button_listen() {
        if (getView() != null) {
            Button button_shadow_for_the_back_for_good_habits = getView().findViewById(R.id.button_shadow_for_the_back_for_good_habits);
            Button button_shadow_for_the_front_for_good_habits = getView().findViewById(R.id.button_shadow_for_the_front_for_good_habits);
            final TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            button_shadow_for_the_back_for_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    if (return_month_string_to_int(splitter[0]) == 0) {
                        String month_name = return_month(11);
                        String year = String.valueOf(Integer.parseInt(splitter[1]) - 1);
                        month_and_year_in_calender_for_good_habits.setText(month_name.concat(" ").concat(year));
                        clear_the_calender();
                        set_the_days_on_the_real_text();
                        clear_the_color_from_the_keyboard();
                        set_all_buttons_to_visible();
                        remove_the_hiding_buttons();
                        color_today();
                        check_if_date_is_future();
                        hide_or_un_hide_the_button(0);
                        color_the_calender();
                        check_if_date_is_future();
                        color_the_button_under_the_calender();
                        clear_all_the_unders();
                        divide_it_into_weeks();
                        clear_the_middle();
                        make_the_middle_come_again();
                        color_the_under();
                        color_the_middle();

                    } else {
                        String month_name = return_month(return_month_string_to_int(splitter[0]) - 1);
                        month_and_year_in_calender_for_good_habits.setText(month_name.concat(" ").concat(splitter[1]));
                        clear_the_calender();
                        set_the_days_on_the_real_text();
                        clear_the_color_from_the_keyboard();
                        set_all_buttons_to_visible();
                        remove_the_hiding_buttons();
                        color_today();
                        check_if_date_is_future();
                        hide_or_un_hide_the_button(0);
                        color_the_calender();
                        check_if_date_is_future();
                        color_the_button_under_the_calender();
                        clear_all_the_unders();
                        divide_it_into_weeks();
                        clear_the_middle();
                        make_the_middle_come_again();
                        color_the_under();
                        color_the_middle();

                    }
                }
            });
            button_shadow_for_the_front_for_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    if (return_month_string_to_int((splitter[0])) == 11) {
                        String month_name = return_month(0);
                        String year = String.valueOf(Integer.parseInt(splitter[1]) + 1);
                        month_and_year_in_calender_for_good_habits.setText(month_name.concat(" ").concat(year));
                        clear_the_calender();
                        set_the_days_on_the_real_text();
                        clear_the_color_from_the_keyboard();
                        set_all_buttons_to_visible();
                        remove_the_hiding_buttons();
                        color_today();
                        check_if_date_is_future();
                        hide_or_un_hide_the_button(0);
                        color_the_calender();
                        check_if_date_is_future();
                        color_the_button_under_the_calender();
                        clear_all_the_unders();
                        divide_it_into_weeks();
                        clear_the_middle();
                        make_the_middle_come_again();
                        color_the_under();
                        color_the_middle();
                    } else {
                        String month_name = return_month(return_month_string_to_int(splitter[0]) + 1);
                        month_and_year_in_calender_for_good_habits.setText(month_name.concat(" ").concat(splitter[1]));
                        clear_the_calender();
                        set_the_days_on_the_real_text();
                        clear_the_color_from_the_keyboard();
                        set_all_buttons_to_visible();
                        remove_the_hiding_buttons();
                        color_today();
                        check_if_date_is_future();
                        hide_or_un_hide_the_button(0);
                        color_the_calender();
                        check_if_date_is_future();
                        color_the_button_under_the_calender();
                        clear_all_the_unders();
                        divide_it_into_weeks();
                        clear_the_middle();
                        make_the_middle_come_again();
                        color_the_under();
                        color_the_middle();
                    }
                }
            });
        }
    }

    private void clear_the_calender() {
        calender_button_showing_shadow_1.setText("");
        calender_button_showing_shadow_2.setText("");
        calender_button_showing_shadow_3.setText("");
        calender_button_showing_shadow_4.setText("");
        calender_button_showing_shadow_5.setText("");
        calender_button_showing_shadow_6.setText("");
        calender_button_showing_shadow_7.setText("");
        calender_button_showing_shadow_8.setText("");
        calender_button_showing_shadow_9.setText("");
        calender_button_showing_shadow_10.setText("");
        calender_button_showing_shadow_11.setText("");
        calender_button_showing_shadow_12.setText("");
        calender_button_showing_shadow_13.setText("");
        calender_button_showing_shadow_14.setText("");
        calender_button_showing_shadow_15.setText("");
        calender_button_showing_shadow_16.setText("");
        calender_button_showing_shadow_17.setText("");
        calender_button_showing_shadow_18.setText("");
        calender_button_showing_shadow_19.setText("");
        calender_button_showing_shadow_20.setText("");
        calender_button_showing_shadow_21.setText("");
        calender_button_showing_shadow_22.setText("");
        calender_button_showing_shadow_23.setText("");
        calender_button_showing_shadow_24.setText("");
        calender_button_showing_shadow_25.setText("");
        calender_button_showing_shadow_26.setText("");
        calender_button_showing_shadow_27.setText("");
        calender_button_showing_shadow_28.setText("");
        calender_button_showing_shadow_29.setText("");
        calender_button_showing_shadow_30.setText("");
        calender_button_showing_shadow_31.setText("");
        calender_button_showing_shadow_32.setText("");
        calender_button_showing_shadow_33.setText("");
        calender_button_showing_shadow_34.setText("");
        calender_button_showing_shadow_35.setText("");
        calender_button_showing_shadow_36.setText("");
        calender_button_showing_shadow_37.setText("");
    }

    private void color_the_selected(int which) {
        if (which == 1) {
            calender_button_showing_shadow_1.setTextColor(Color.WHITE);
            calender_button_showing_shadow_1.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 2) {
            calender_button_showing_shadow_2.setTextColor(Color.WHITE);
            calender_button_showing_shadow_2.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 3) {
            calender_button_showing_shadow_3.setTextColor(Color.WHITE);
            calender_button_showing_shadow_3.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 4) {
            calender_button_showing_shadow_4.setTextColor(Color.WHITE);
            calender_button_showing_shadow_4.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 5) {
            calender_button_showing_shadow_5.setTextColor(Color.WHITE);
            calender_button_showing_shadow_5.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 6) {
            calender_button_showing_shadow_6.setTextColor(Color.WHITE);
            calender_button_showing_shadow_6.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 7) {
            calender_button_showing_shadow_7.setTextColor(Color.WHITE);
            calender_button_showing_shadow_7.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 8) {
            calender_button_showing_shadow_8.setTextColor(Color.WHITE);
            calender_button_showing_shadow_8.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 9) {
            calender_button_showing_shadow_9.setTextColor(Color.WHITE);
            calender_button_showing_shadow_9.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 10) {
            calender_button_showing_shadow_10.setTextColor(Color.WHITE);
            calender_button_showing_shadow_10.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 11) {
            calender_button_showing_shadow_11.setTextColor(Color.WHITE);
            calender_button_showing_shadow_11.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 12) {
            calender_button_showing_shadow_12.setTextColor(Color.WHITE);
            calender_button_showing_shadow_12.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 13) {
            calender_button_showing_shadow_13.setTextColor(Color.WHITE);
            calender_button_showing_shadow_13.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 14) {
            calender_button_showing_shadow_14.setTextColor(Color.WHITE);
            calender_button_showing_shadow_14.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 15) {
            calender_button_showing_shadow_15.setTextColor(Color.WHITE);
            calender_button_showing_shadow_15.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 16) {
            calender_button_showing_shadow_16.setTextColor(Color.WHITE);
            calender_button_showing_shadow_16.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 17) {
            calender_button_showing_shadow_17.setTextColor(Color.WHITE);
            calender_button_showing_shadow_17.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 18) {
            calender_button_showing_shadow_18.setTextColor(Color.WHITE);
            calender_button_showing_shadow_18.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 19) {
            calender_button_showing_shadow_19.setTextColor(Color.WHITE);
            calender_button_showing_shadow_19.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 20) {
            calender_button_showing_shadow_20.setTextColor(Color.WHITE);
            calender_button_showing_shadow_20.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 21) {
            calender_button_showing_shadow_21.setTextColor(Color.WHITE);
            calender_button_showing_shadow_21.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 22) {
            calender_button_showing_shadow_22.setTextColor(Color.WHITE);
            calender_button_showing_shadow_22.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 23) {
            calender_button_showing_shadow_23.setTextColor(Color.WHITE);
            calender_button_showing_shadow_23.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 24) {
            calender_button_showing_shadow_24.setTextColor(Color.WHITE);
            calender_button_showing_shadow_24.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 25) {
            calender_button_showing_shadow_25.setTextColor(Color.WHITE);
            calender_button_showing_shadow_25.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 26) {
            calender_button_showing_shadow_26.setTextColor(Color.WHITE);
            calender_button_showing_shadow_26.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 27) {
            calender_button_showing_shadow_27.setTextColor(Color.WHITE);
            calender_button_showing_shadow_27.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 28) {
            calender_button_showing_shadow_28.setTextColor(Color.WHITE);
            calender_button_showing_shadow_28.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 29) {
            calender_button_showing_shadow_29.setTextColor(Color.WHITE);
            calender_button_showing_shadow_29.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 30) {
            calender_button_showing_shadow_30.setTextColor(Color.WHITE);
            calender_button_showing_shadow_30.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 31) {
            calender_button_showing_shadow_31.setTextColor(Color.WHITE);
            calender_button_showing_shadow_31.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 32) {
            calender_button_showing_shadow_32.setTextColor(Color.WHITE);
            calender_button_showing_shadow_32.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 33) {
            calender_button_showing_shadow_33.setTextColor(Color.WHITE);
            calender_button_showing_shadow_33.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 34) {
            calender_button_showing_shadow_34.setTextColor(Color.WHITE);
            calender_button_showing_shadow_34.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 35) {
            calender_button_showing_shadow_35.setTextColor(Color.WHITE);
            calender_button_showing_shadow_35.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 36) {
            calender_button_showing_shadow_36.setTextColor(Color.WHITE);
            calender_button_showing_shadow_36.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else {
            calender_button_showing_shadow_37.setTextColor(Color.WHITE);
            calender_button_showing_shadow_37.setBackgroundResource(R.drawable.round_button_colored_fav);
        }
    }

    private void clear_the_color_from_the_keyboard() {
        calender_button_showing_shadow_1.setTextColor(Color.BLACK);
        calender_button_showing_shadow_1.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_2.setTextColor(Color.BLACK);
        calender_button_showing_shadow_2.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_3.setTextColor(Color.BLACK);
        calender_button_showing_shadow_3.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_4.setTextColor(Color.BLACK);
        calender_button_showing_shadow_4.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_5.setTextColor(Color.BLACK);
        calender_button_showing_shadow_5.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_6.setTextColor(Color.BLACK);
        calender_button_showing_shadow_6.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_7.setTextColor(Color.BLACK);
        calender_button_showing_shadow_7.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_8.setTextColor(Color.BLACK);
        calender_button_showing_shadow_8.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_9.setTextColor(Color.BLACK);
        calender_button_showing_shadow_9.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_10.setTextColor(Color.BLACK);
        calender_button_showing_shadow_10.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_11.setTextColor(Color.BLACK);
        calender_button_showing_shadow_11.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_12.setTextColor(Color.BLACK);
        calender_button_showing_shadow_12.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_13.setTextColor(Color.BLACK);
        calender_button_showing_shadow_13.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_14.setTextColor(Color.BLACK);
        calender_button_showing_shadow_14.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_15.setTextColor(Color.BLACK);
        calender_button_showing_shadow_15.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_16.setTextColor(Color.BLACK);
        calender_button_showing_shadow_16.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_17.setTextColor(Color.BLACK);
        calender_button_showing_shadow_17.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_18.setTextColor(Color.BLACK);
        calender_button_showing_shadow_18.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_19.setTextColor(Color.BLACK);
        calender_button_showing_shadow_19.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_20.setTextColor(Color.BLACK);
        calender_button_showing_shadow_20.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_21.setTextColor(Color.BLACK);
        calender_button_showing_shadow_21.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_22.setTextColor(Color.BLACK);
        calender_button_showing_shadow_22.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_23.setTextColor(Color.BLACK);
        calender_button_showing_shadow_23.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_24.setTextColor(Color.BLACK);
        calender_button_showing_shadow_24.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_25.setTextColor(Color.BLACK);
        calender_button_showing_shadow_25.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_26.setTextColor(Color.BLACK);
        calender_button_showing_shadow_26.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_27.setTextColor(Color.BLACK);
        calender_button_showing_shadow_27.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_28.setTextColor(Color.BLACK);
        calender_button_showing_shadow_28.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_29.setTextColor(Color.BLACK);
        calender_button_showing_shadow_29.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_30.setTextColor(Color.BLACK);
        calender_button_showing_shadow_30.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_31.setTextColor(Color.BLACK);
        calender_button_showing_shadow_31.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_32.setTextColor(Color.BLACK);
        calender_button_showing_shadow_32.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_33.setTextColor(Color.BLACK);
        calender_button_showing_shadow_33.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_34.setTextColor(Color.BLACK);
        calender_button_showing_shadow_34.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_35.setTextColor(Color.BLACK);
        calender_button_showing_shadow_35.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_36.setTextColor(Color.BLACK);
        calender_button_showing_shadow_36.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_37.setTextColor(Color.BLACK);
        calender_button_showing_shadow_37.setBackgroundResource(R.drawable.round_button);
    }

    private void color_the_today_value() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        color_the_today = String.valueOf(day).concat("_").concat(String.valueOf(month)).concat("_").concat(String.valueOf(year));
    }

    private void calender_button_listeners() {
        calender_button_showing_shadow_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(1);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_1.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(2);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_2.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(3);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_3.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(4);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_4.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(5);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_5.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(6);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_6.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(7);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_7.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(8);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_8.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(9);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_9.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(10);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_10.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(11);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_11.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(12);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_12.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(13);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_13.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(14);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_14.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(15);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_15.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(16);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_16.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(17);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_17.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(18);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_18.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(19);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_19.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(20);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_20.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(21);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_21.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(22);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_22.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(23);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_23.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(24);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_24.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(25);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_25.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(26);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_26.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(27);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_27.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(28);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_28.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(29);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_29.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(30);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_30.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(31);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_31.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(32);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_32.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(33);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_33.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(34);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_34.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(35);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_35.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(36);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_36.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(37);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_37.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
    }

    private void remove_the_hiding_buttons() {
        if (calender_button_showing_shadow_1.getText().toString().equals("")) {
            calender_button_showing_shadow_1.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_2.getText().toString().equals("")) {
            calender_button_showing_shadow_2.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_3.getText().toString().equals("")) {
            calender_button_showing_shadow_3.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_4.getText().toString().equals("")) {
            calender_button_showing_shadow_4.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_5.getText().toString().equals("")) {
            calender_button_showing_shadow_5.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_6.getText().toString().equals("")) {
            calender_button_showing_shadow_6.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_29.getText().toString().equals("")) {
            calender_button_showing_shadow_29.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_30.getText().toString().equals("")) {
            calender_button_showing_shadow_30.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_31.getText().toString().equals("")) {
            calender_button_showing_shadow_31.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_32.getText().toString().equals("")) {
            calender_button_showing_shadow_32.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_33.getText().toString().equals("")) {
            calender_button_showing_shadow_33.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_34.getText().toString().equals("")) {
            calender_button_showing_shadow_34.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_35.getText().toString().equals("")) {
            calender_button_showing_shadow_35.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_36.getText().toString().equals("")) {
            calender_button_showing_shadow_36.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_37.getText().toString().equals("")) {
            calender_button_showing_shadow_37.setVisibility(View.INVISIBLE);
        }
    }

    private void set_all_buttons_to_visible() {
        calender_button_showing_shadow_1.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_2.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_3.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_4.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_5.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_6.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_7.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_8.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_9.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_10.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_11.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_12.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_13.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_14.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_15.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_16.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_17.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_17.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_18.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_19.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_20.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_21.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_22.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_23.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_24.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_25.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_26.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_27.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_28.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_29.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_30.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_31.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_32.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_33.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_34.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_35.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_36.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_37.setVisibility(View.VISIBLE);
    }

    private int return_which_day_is_linked_to_calender(int which) {
        if (calender_button_showing_shadow_1.getText().toString().equals(String.valueOf(which))) {
            return 1;
        } else if (calender_button_showing_shadow_2.getText().toString().equals(String.valueOf(which))) {
            return 2;
        } else if (calender_button_showing_shadow_3.getText().toString().equals(String.valueOf(which))) {
            return 3;
        } else if (calender_button_showing_shadow_4.getText().toString().equals(String.valueOf(which))) {
            return 4;
        } else if (calender_button_showing_shadow_5.getText().toString().equals(String.valueOf(which))) {
            return 5;
        } else if (calender_button_showing_shadow_6.getText().toString().equals(String.valueOf(which))) {
            return 6;
        } else if (calender_button_showing_shadow_7.getText().toString().equals(String.valueOf(which))) {
            return 7;
        } else if (calender_button_showing_shadow_8.getText().toString().equals(String.valueOf(which))) {
            return 8;
        } else if (calender_button_showing_shadow_9.getText().toString().equals(String.valueOf(which))) {
            return 9;
        } else if (calender_button_showing_shadow_10.getText().toString().equals(String.valueOf(which))) {
            return 10;
        } else if (calender_button_showing_shadow_11.getText().toString().equals(String.valueOf(which))) {
            return 11;
        } else if (calender_button_showing_shadow_12.getText().toString().equals(String.valueOf(which))) {
            return 12;
        } else if (calender_button_showing_shadow_13.getText().toString().equals(String.valueOf(which))) {
            return 13;
        } else if (calender_button_showing_shadow_14.getText().toString().equals(String.valueOf(which))) {
            return 14;
        } else if (calender_button_showing_shadow_15.getText().toString().equals(String.valueOf(which))) {
            return 15;
        } else if (calender_button_showing_shadow_16.getText().toString().equals(String.valueOf(which))) {
            return 16;
        } else if (calender_button_showing_shadow_17.getText().toString().equals(String.valueOf(which))) {
            return 17;
        } else if (calender_button_showing_shadow_18.getText().toString().equals(String.valueOf(which))) {
            return 18;
        } else if (calender_button_showing_shadow_19.getText().toString().equals(String.valueOf(which))) {
            return 19;
        } else if (calender_button_showing_shadow_20.getText().toString().equals(String.valueOf(which))) {
            return 20;
        } else if (calender_button_showing_shadow_21.getText().toString().equals(String.valueOf(which))) {
            return 21;
        } else if (calender_button_showing_shadow_22.getText().toString().equals(String.valueOf(which))) {
            return 22;
        } else if (calender_button_showing_shadow_23.getText().toString().equals(String.valueOf(which))) {
            return 23;
        } else if (calender_button_showing_shadow_24.getText().toString().equals(String.valueOf(which))) {
            return 24;
        } else if (calender_button_showing_shadow_25.getText().toString().equals(String.valueOf(which))) {
            return 25;
        } else if (calender_button_showing_shadow_26.getText().toString().equals(String.valueOf(which))) {
            return 26;
        } else if (calender_button_showing_shadow_27.getText().toString().equals(String.valueOf(which))) {
            return 27;
        } else if (calender_button_showing_shadow_28.getText().toString().equals(String.valueOf(which))) {
            return 28;
        } else if (calender_button_showing_shadow_29.getText().toString().equals(String.valueOf(which))) {
            return 29;
        } else if (calender_button_showing_shadow_30.getText().toString().equals(String.valueOf(which))) {
            return 30;
        } else if (calender_button_showing_shadow_31.getText().toString().equals(String.valueOf(which))) {
            return 31;
        } else if (calender_button_showing_shadow_32.getText().toString().equals(String.valueOf(which))) {
            return 32;
        } else if (calender_button_showing_shadow_33.getText().toString().equals(String.valueOf(which))) {
            return 33;
        } else if (calender_button_showing_shadow_34.getText().toString().equals(String.valueOf(which))) {
            return 34;
        } else if (calender_button_showing_shadow_35.getText().toString().equals(String.valueOf(which))) {
            return 35;
        } else if (calender_button_showing_shadow_36.getText().toString().equals(String.valueOf(which))) {
            return 36;
        } else {
            return 37;
        }
    }

    private boolean check_if_i_am_chosen(int which) {
        if (which == 1 && calender_button_showing_shadow_1.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 2 && calender_button_showing_shadow_2.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 3 && calender_button_showing_shadow_3.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 4 && calender_button_showing_shadow_4.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 5 && calender_button_showing_shadow_5.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 6 && calender_button_showing_shadow_6.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 7 && calender_button_showing_shadow_7.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 8 && calender_button_showing_shadow_8.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 9 && calender_button_showing_shadow_9.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 10 && calender_button_showing_shadow_10.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 11 && calender_button_showing_shadow_11.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 12 && calender_button_showing_shadow_12.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 13 && calender_button_showing_shadow_13.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 14 && calender_button_showing_shadow_14.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 15 && calender_button_showing_shadow_15.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 16 && calender_button_showing_shadow_16.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 17 && calender_button_showing_shadow_17.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 18 && calender_button_showing_shadow_18.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 19 && calender_button_showing_shadow_19.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 20 && calender_button_showing_shadow_20.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 21 && calender_button_showing_shadow_21.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 22 && calender_button_showing_shadow_22.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 23 && calender_button_showing_shadow_23.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 24 && calender_button_showing_shadow_24.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 25 && calender_button_showing_shadow_25.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 26 && calender_button_showing_shadow_26.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 27 && calender_button_showing_shadow_27.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 28 && calender_button_showing_shadow_28.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 29 && calender_button_showing_shadow_29.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 30 && calender_button_showing_shadow_30.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 31 && calender_button_showing_shadow_31.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 32 && calender_button_showing_shadow_32.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 33 && calender_button_showing_shadow_33.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 34 && calender_button_showing_shadow_34.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 35 && calender_button_showing_shadow_35.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 36 && calender_button_showing_shadow_36.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else
            return which == 37 && calender_button_showing_shadow_37.getCurrentTextColor() == Color.WHITE;
    }

    private void color_today() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            String[] text_to_split = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            int year_from_text = Integer.parseInt(text_to_split[1]);
            int month_from_text = return_month_string_to_int(text_to_split[0]);
            String[] saved_text_split = color_the_today.split("_");
            int year = Integer.parseInt(saved_text_split[2]);
            int month = Integer.parseInt(saved_text_split[1]);
            int day = Integer.parseInt(saved_text_split[0]);
            if ((year_from_text == year) && (month_from_text == month)) {
                if (!calender_button_showing_shadow_1.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_1.getText().toString()) == day) {
                    color_the_selected(1);
                } else if (!calender_button_showing_shadow_2.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_2.getText().toString()) == day) {
                    color_the_selected(2);
                } else if (!calender_button_showing_shadow_3.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_3.getText().toString()) == day) {
                    color_the_selected(3);
                } else if (!calender_button_showing_shadow_4.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_4.getText().toString()) == day) {
                    color_the_selected(4);
                } else if (!calender_button_showing_shadow_5.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_5.getText().toString()) == day) {
                    color_the_selected(5);
                } else if (!calender_button_showing_shadow_6.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_6.getText().toString()) == day) {
                    color_the_selected(6);
                } else if (!calender_button_showing_shadow_7.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_7.getText().toString()) == day) {
                    color_the_selected(7);
                } else if (!calender_button_showing_shadow_8.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_8.getText().toString()) == day) {
                    color_the_selected(8);
                } else if (!calender_button_showing_shadow_9.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_9.getText().toString()) == day) {
                    color_the_selected(9);
                } else if (!calender_button_showing_shadow_10.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_10.getText().toString()) == day) {
                    color_the_selected(10);
                } else if (!calender_button_showing_shadow_11.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_11.getText().toString()) == day) {
                    color_the_selected(11);
                } else if (!calender_button_showing_shadow_12.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_12.getText().toString()) == day) {
                    color_the_selected(12);
                } else if (!calender_button_showing_shadow_13.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_13.getText().toString()) == day) {
                    color_the_selected(13);
                } else if (!calender_button_showing_shadow_14.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_14.getText().toString()) == day) {
                    color_the_selected(14);
                } else if (!calender_button_showing_shadow_15.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_15.getText().toString()) == day) {
                    color_the_selected(15);
                } else if (!calender_button_showing_shadow_16.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_16.getText().toString()) == day) {
                    color_the_selected(16);
                } else if (!calender_button_showing_shadow_17.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_17.getText().toString()) == day) {
                    color_the_selected(17);
                } else if (!calender_button_showing_shadow_18.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_18.getText().toString()) == day) {
                    color_the_selected(18);
                } else if (!calender_button_showing_shadow_19.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_19.getText().toString()) == day) {
                    color_the_selected(19);
                } else if (!calender_button_showing_shadow_20.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_20.getText().toString()) == day) {
                    color_the_selected(20);
                } else if (!calender_button_showing_shadow_21.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_21.getText().toString()) == day) {
                    color_the_selected(21);
                } else if (!calender_button_showing_shadow_22.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_22.getText().toString()) == day) {
                    color_the_selected(22);
                } else if (!calender_button_showing_shadow_23.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_23.getText().toString()) == day) {
                    color_the_selected(23);
                } else if (!calender_button_showing_shadow_24.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_24.getText().toString()) == day) {
                    color_the_selected(24);
                } else if (!calender_button_showing_shadow_25.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_25.getText().toString()) == day) {
                    color_the_selected(25);
                } else if (!calender_button_showing_shadow_26.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_26.getText().toString()) == day) {
                    color_the_selected(26);
                } else if (!calender_button_showing_shadow_27.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_27.getText().toString()) == day) {
                    color_the_selected(27);
                } else if (!calender_button_showing_shadow_28.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_28.getText().toString()) == day) {
                    color_the_selected(28);
                } else if (!calender_button_showing_shadow_29.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_29.getText().toString()) == day) {
                    color_the_selected(29);
                } else if (!calender_button_showing_shadow_30.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_30.getText().toString()) == day) {
                    color_the_selected(30);
                } else if (!calender_button_showing_shadow_31.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_31.getText().toString()) == day) {
                    color_the_selected(31);
                } else if (!calender_button_showing_shadow_32.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_32.getText().toString()) == day) {
                    color_the_selected(32);
                } else if (!calender_button_showing_shadow_33.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_33.getText().toString()) == day) {
                    color_the_selected(33);
                } else if (!calender_button_showing_shadow_34.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_34.getText().toString()) == day) {
                    color_the_selected(34);
                } else if (!calender_button_showing_shadow_35.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_35.getText().toString()) == day) {
                    color_the_selected(35);
                } else if (!calender_button_showing_shadow_36.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_36.getText().toString()) == day) {
                    color_the_selected(36);
                } else if (!calender_button_showing_shadow_37.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_37.getText().toString()) == day) {
                    color_the_selected(37);
                }
            }
        }
    }

    private void color_the_calender() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            String very_bad_color = return_the_color_of_mood(1);
            String bad_color = return_the_color_of_mood(2);
            String ok_color = return_the_color_of_mood(3);
            String good_color = return_the_color_of_mood(4);
            String very_good_color = return_the_color_of_mood(5);
            String no_mood_color = return_the_color_of_mood(0);
            colors = new String[return_last_day_of_month() + 1];
            Calendar calendar = Calendar.getInstance();
            String[] split_month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            int year = Integer.parseInt(split_month_and_year[1]);
            int month = return_month_string_to_int(split_month_and_year[0]);
            if (!check_past_now_or_future().equals("future")) {
                for (int i = 1; i <= return_last_day_of_month(); i++) {
                    calendar.set(year, month, i);
                    long time_in_milli = calendar.getTimeInMillis();
                    if (Simplify_the_time.return_time_in_midnight(time_in_milli) >= Simplify_the_time.return_time_in_midnight(start_date)) {
                        if (Simplify_the_time.return_time_in_midnight(time_in_milli) <= Simplify_the_time.return_time_in_midnight(System.currentTimeMillis())) {
                            if (return_color_of_days(time_in_milli) == 0) {
                                colors[i] = no_mood_color;
                            } else if (return_color_of_days(time_in_milli) == 1) {
                                colors[i] = very_bad_color;
                            } else if (return_color_of_days(time_in_milli) == 2) {
                                colors[i] = bad_color;
                            } else if (return_color_of_days(time_in_milli) == 3) {
                                colors[i] = ok_color;
                            } else if (return_color_of_days(time_in_milli) == 4) {
                                colors[i] = good_color;
                            } else if (return_color_of_days(time_in_milli) == 5) {
                                colors[i] = very_good_color;
                            }
                        } else {
                            colors[i] = no_mood_color;
                        }
                    } else {
                        colors[i] = no_mood_color;
                    }
                }
            } else {
                for (int i = 1; i <= return_last_day_of_month(); i++) {
                    colors[i] = no_mood_color;
                }
            }
            for (int i = 1; i <= return_last_day_of_month(); i++) {
                if (return_which_day_is_linked_to_calender(i) == 1) {
                    if (calender_button_showing_shadow_1.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_1.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 2) {
                    if (calender_button_showing_shadow_2.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_2.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 3) {
                    if (calender_button_showing_shadow_3.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_3.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 4) {
                    if (calender_button_showing_shadow_4.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_4.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 5) {
                    if (calender_button_showing_shadow_5.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_5.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 6) {
                    if (calender_button_showing_shadow_6.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_6.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 7) {
                    if (calender_button_showing_shadow_7.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_7.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 8) {
                    if (calender_button_showing_shadow_8.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_8.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 9) {
                    if (calender_button_showing_shadow_9.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_9.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 10) {
                    if (calender_button_showing_shadow_10.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_10.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 11) {
                    if (calender_button_showing_shadow_11.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_11.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 12) {
                    if (calender_button_showing_shadow_12.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_12.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 13) {
                    if (calender_button_showing_shadow_13.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_13.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 14) {
                    if (calender_button_showing_shadow_14.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_14.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 15) {
                    if (calender_button_showing_shadow_15.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_15.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 16) {
                    if (calender_button_showing_shadow_16.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_16.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 17) {
                    if (calender_button_showing_shadow_17.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_17.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 18) {
                    if (calender_button_showing_shadow_18.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_18.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 19) {
                    if (calender_button_showing_shadow_19.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_19.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 20) {
                    if (calender_button_showing_shadow_20.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_20.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 21) {
                    if (calender_button_showing_shadow_21.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_21.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 22) {
                    if (calender_button_showing_shadow_22.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_22.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 23) {
                    if (calender_button_showing_shadow_23.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_23.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 24) {
                    if (calender_button_showing_shadow_24.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_24.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 25) {
                    if (calender_button_showing_shadow_25.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_25.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 26) {
                    if (calender_button_showing_shadow_26.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_26.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 27) {
                    if (calender_button_showing_shadow_27.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_27.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 28) {
                    if (calender_button_showing_shadow_28.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_28.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 29) {
                    if (calender_button_showing_shadow_29.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_29.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 30) {
                    if (calender_button_showing_shadow_30.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_30.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 31) {
                    if (calender_button_showing_shadow_31.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_31.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 32) {
                    if (calender_button_showing_shadow_32.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_32.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 33) {
                    if (calender_button_showing_shadow_33.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_33.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 34) {
                    if (calender_button_showing_shadow_34.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_34.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 35) {
                    if (calender_button_showing_shadow_35.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_35.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 36) {
                    if (calender_button_showing_shadow_36.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_36.setTextColor(Color.parseColor(colors[i]));
                    }
                } else {
                    if (calender_button_showing_shadow_37.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_37.setTextColor(Color.parseColor(colors[i]));
                    }
                }
            }
        }
    }

    private void check_if_date_is_future() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            String[] split_for_day_month_year = color_the_today.split("_");
            String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            int calender_day = Integer.parseInt(split_for_day_month_year[0]);
            int calender_month = return_month_string_to_int(month_and_year[0]);
            int calender_year = Integer.parseInt(month_and_year[1]);
            Calendar calendar = Calendar.getInstance();
            int real_year = calendar.get(Calendar.YEAR);
            int real_month = calendar.get(Calendar.MONTH);
            int real_day = calendar.get(Calendar.DAY_OF_MONTH);
            Calendar calendar_new = Calendar.getInstance();
            calendar_new.set(calender_year, calender_month, calender_day);
            String[] splitter_temp_from_text = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            String month_from_text = String.valueOf(return_month_string_to_int(splitter_temp_from_text[0]));
            String year_from_text = String.valueOf(splitter_temp_from_text[1]);
            String[] splitter_for_colored_value = color_the_today.split("_");
            if (calendar_new.getTimeInMillis() < Simplify_the_time.return_time_in_midnight(start_date)) {
                hide_or_un_hide_the_button(0);
            } else {
                if (calender_year > real_year) {
                    hide_or_un_hide_the_button(0);
                } else if (calender_year == real_year) {
                    if (calender_month > real_month) {
                        hide_or_un_hide_the_button(0);
                    } else if (calender_month == real_month) {
                        if (calender_day >= real_day) {
                            hide_or_un_hide_the_button(0);
                        } else {
                            if (month_from_text.equals(splitter_for_colored_value[1]) && year_from_text.equals(splitter_for_colored_value[2])) {
                                hide_or_un_hide_the_button(1);
                            } else {
                                hide_or_un_hide_the_button(0);
                            }
                        }
                    } else {
                        if (month_from_text.equals(splitter_for_colored_value[1]) && year_from_text.equals(splitter_for_colored_value[2])) {
                            hide_or_un_hide_the_button(1);
                        } else {
                            hide_or_un_hide_the_button(0);
                        }
                    }
                } else {
                    if (month_from_text.equals(splitter_for_colored_value[1]) && year_from_text.equals(splitter_for_colored_value[2])) {
                        hide_or_un_hide_the_button(1);
                    } else {
                        hide_or_un_hide_the_button(0);
                    }
                }
            }
        }
    }

    private void hide_or_un_hide_the_button(int which) {
        if (getView() != null && getContext() != null) {
            Button very_bad_mood_button_in_habits = getView().findViewById(R.id.very_bad_mood_button_in_habits);
            Button bad_mood_button_in_habits = getView().findViewById(R.id.bad_mood_button_in_habits);
            Button ok_mood_button_in_habits = getView().findViewById(R.id.ok_mood_button_in_habits);
            Button good_mood_button_in_habits = getView().findViewById(R.id.good_mood_button_in_habits);
            Button very_good_mood_button_in_habits = getView().findViewById(R.id.very_good_mood_button_in_habits);
            View very_bad_mood_in_habits = getView().findViewById(R.id.very_bad_mood_in_habits);
            View bad_mood_in_habits = getView().findViewById(R.id.bad_mood_in_habits);
            View ok_mood_in_habits = getView().findViewById(R.id.ok_mood_in_habits);
            View good_mood_in_habits = getView().findViewById(R.id.good_mood_in_habits);
            View very_good_mood_in_habits = getView().findViewById(R.id.very_good_mood_in_habits);
            View very_bad_mood_shade_in_habits = getView().findViewById(R.id.very_bad_mood_shade_in_habits);
            View bad_mood_shade_in_habits = getView().findViewById(R.id.bad_mood_shade_in_habits);
            View ok_mood_shade_in_habits = getView().findViewById(R.id.ok_mood_shade_in_habits);
            View good_mood_shade_in_habits = getView().findViewById(R.id.good_mood_shade_in_habits);
            View very_good_mood_shade_in_habits = getView().findViewById(R.id.very_good_mood_shade_in_habits);
            View very_bad_mood_check_mark_in_mood = getView().findViewById(R.id.very_bad_mood_check_mark_in_mood);
            View bad_mood_check_mark_in_mood = getView().findViewById(R.id.bad_mood_check_mark_in_mood);
            View ok_mood_check_mark_in_mood = getView().findViewById(R.id.ok_mood_check_mark_in_mood);
            View good_mood_check_mark_in_mood = getView().findViewById(R.id.good_mood_check_mark_in_mood);
            View very_good_mood_check_mark_in_mood = getView().findViewById(R.id.very_good_mood_check_mark_in_mood);
            Button button_too_share_calender_in_good_habits = getView().findViewById(R.id.button_too_share_calender_in_good_habits);
            TextView text_asking_did_you_relapse_in_share = getView().findViewById(R.id.text_asking_did_you_relapse_in_share);
            ConstraintLayout calender_in_stats = getView().findViewById(R.id.calender_in_stats);
            if (which == 0) {
                very_bad_mood_button_in_habits.setVisibility(View.GONE);
                bad_mood_button_in_habits.setVisibility(View.GONE);
                ok_mood_button_in_habits.setVisibility(View.GONE);
                good_mood_button_in_habits.setVisibility(View.GONE);
                very_good_mood_button_in_habits.setVisibility(View.GONE);
                text_asking_did_you_relapse_in_share.setVisibility(View.GONE);
                very_bad_mood_in_habits.setVisibility(View.GONE);
                bad_mood_in_habits.setVisibility(View.GONE);
                ok_mood_in_habits.setVisibility(View.GONE);
                good_mood_in_habits.setVisibility(View.GONE);
                very_good_mood_in_habits.setVisibility(View.GONE);
                very_bad_mood_shade_in_habits.setVisibility(View.GONE);
                bad_mood_shade_in_habits.setVisibility(View.GONE);
                ok_mood_shade_in_habits.setVisibility(View.GONE);
                good_mood_shade_in_habits.setVisibility(View.GONE);
                very_good_mood_shade_in_habits.setVisibility(View.GONE);
                very_bad_mood_check_mark_in_mood.setVisibility(View.GONE);
                bad_mood_check_mark_in_mood.setVisibility(View.GONE);
                ok_mood_check_mark_in_mood.setVisibility(View.GONE);
                good_mood_check_mark_in_mood.setVisibility(View.GONE);
                very_good_mood_check_mark_in_mood.setVisibility(View.GONE);
                ConstraintLayout constraintLayout = getView().findViewById(R.id.layout_containting_the_calender);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.clear(button_too_share_calender_in_good_habits.getId(), ConstraintSet.TOP);
                constraintSet.connect(button_too_share_calender_in_good_habits.getId(), ConstraintSet.TOP, calender_in_stats.getId(), ConstraintSet.TOP, (int) convertDpToPixel(return_the_length_of_stat() + 7, getContext()));
                constraintSet.applyTo(constraintLayout);
            } else {
                very_bad_mood_button_in_habits.setVisibility(View.VISIBLE);
                bad_mood_button_in_habits.setVisibility(View.VISIBLE);
                ok_mood_button_in_habits.setVisibility(View.VISIBLE);
                good_mood_button_in_habits.setVisibility(View.VISIBLE);
                very_good_mood_button_in_habits.setVisibility(View.VISIBLE);
                text_asking_did_you_relapse_in_share.setVisibility(View.VISIBLE);
                very_bad_mood_in_habits.setVisibility(View.VISIBLE);
                bad_mood_in_habits.setVisibility(View.VISIBLE);
                ok_mood_in_habits.setVisibility(View.VISIBLE);
                good_mood_in_habits.setVisibility(View.VISIBLE);
                very_good_mood_in_habits.setVisibility(View.VISIBLE);
                /*very_bad_mood_shade_in_habits.setVisibility(View.VISIBLE);
                bad_mood_shade_in_habits.setVisibility(View.VISIBLE);
                ok_mood_shade_in_habits.setVisibility(View.VISIBLE);
                good_mood_shade_in_habits.setVisibility(View.VISIBLE);
                very_good_mood_shade_in_habits.setVisibility(View.VISIBLE);
                very_bad_mood_check_mark_in_mood.setVisibility(View.VISIBLE);
                bad_mood_check_mark_in_mood.setVisibility(View.VISIBLE);
                ok_mood_check_mark_in_mood.setVisibility(View.VISIBLE);
                good_mood_check_mark_in_mood.setVisibility(View.VISIBLE);
                very_good_mood_check_mark_in_mood.setVisibility(View.VISIBLE);*/
                text_asking_did_you_relapse_in_share.setVisibility(View.VISIBLE);
                ConstraintLayout constraintLayout = getView().findViewById(R.id.layout_containting_the_calender);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(text_asking_did_you_relapse_in_share.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, (int) convertDpToPixel(return_the_length_of_stat() + 28, getContext()));
                constraintSet.connect(button_too_share_calender_in_good_habits.getId(), ConstraintSet.TOP, very_bad_mood_in_habits.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(15, getContext()));
                constraintSet.applyTo(constraintLayout);
            }
        }
    }

    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private int return_the_length_of_stat() {
        if (calender_button_showing_shadow_36.getText().toString().equals("")) {
            if (calender_button_showing_shadow_29.getText().toString().equals("")) {
                return 240;
            } else {
                return 280;
            }
        } else {
            return 320;
        }
    }

    private void set_up_share_and_yes_or_no_buttons() {
        if (getView() != null && getContext() != null) {
            Button button_too_share_calender_in_good_habits = getView().findViewById(R.id.button_too_share_calender_in_good_habits);
            TextView text_asking_did_you_relapse_in_share = getView().findViewById(R.id.text_asking_did_you_relapse_in_share);
            ConstraintLayout calender_in_stats = getView().findViewById(R.id.calender_in_stats);
            ConstraintLayout constraintLayout = getView().findViewById(R.id.layout_containting_the_calender);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(text_asking_did_you_relapse_in_share.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, (int) convertDpToPixel(return_the_length_of_stat() + 28, getContext()));
            constraintSet.clear(button_too_share_calender_in_good_habits.getId(), ConstraintSet.TOP);
            constraintSet.connect(button_too_share_calender_in_good_habits.getId(), ConstraintSet.TOP, calender_in_stats.getId(), ConstraintSet.TOP, (int) (convertDpToPixel(return_the_length_of_stat() + 15, getContext())));
            constraintSet.applyTo(constraintLayout);
        }
    }

    private void color_only_today() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            String[] split_the_month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            String[] split_the_color = color_the_today.split("_");
            int old_day = Integer.parseInt(split_the_color[0]);
            int old_month = Integer.parseInt(split_the_color[1]);
            int old_year = Integer.parseInt(split_the_color[2]);
            int new_month = return_month_string_to_int(split_the_month_and_year[0]);
            int new_year = Integer.parseInt(split_the_month_and_year[1]);
            if (old_month == new_month && old_year == new_year) {
                if (colors[old_day] != null) {
                    if (calender_button_showing_shadow_1.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_1.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_2.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_2.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_3.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_3.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_4.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_4.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_5.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_5.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_6.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_6.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_7.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_7.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_8.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_8.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_9.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_9.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_10.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_10.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_11.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_11.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_12.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_12.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_13.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_13.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_14.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_14.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_15.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_15.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_16.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_16.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_17.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_17.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_18.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_18.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_19.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_19.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_20.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_20.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_21.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_21.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_22.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_22.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_23.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_23.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_24.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_24.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_25.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_25.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_26.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_26.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_27.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_27.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_28.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_28.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_29.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_29.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_30.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_30.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_31.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_31.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_32.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_32.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_33.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_33.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_34.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_34.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_35.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_35.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_36.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_36.setTextColor(Color.parseColor(colors[old_day]));
                    } else {
                        calender_button_showing_shadow_37.setTextColor(Color.parseColor(colors[old_day]));
                    }
                }
            }
        }
    }

    private void clear_the_background_only() {
        calender_button_showing_shadow_1.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_2.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_3.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_4.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_5.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_6.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_7.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_8.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_9.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_10.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_11.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_12.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_13.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_14.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_15.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_16.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_17.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_18.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_19.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_20.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_21.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_22.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_23.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_24.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_25.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_26.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_27.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_28.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_29.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_30.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_31.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_32.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_33.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_34.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_35.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_36.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_37.setBackgroundResource(R.drawable.round_button);
    }

    private String check_past_now_or_future() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            String[] split_month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            int calender_month = return_month_string_to_int(split_month_and_year[0]);
            int calender_year = Integer.parseInt(split_month_and_year[1]);
            Calendar calendar = Calendar.getInstance();
            int real_year = calendar.get(Calendar.YEAR);
            int real_month = calendar.get(Calendar.MONTH);
            if (calender_year > real_year) {
                return "future";
            } else if (calender_year == real_year) {
                if (calender_month > real_month) {
                    return "future";
                } else if (calender_month == real_month) {
                    return "current";
                } else {
                    return "past";
                }
            } else {
                return "past";
            }
        } else {
            return "future";
        }
    }

    private void clear_all_the_text_from_calender() {
        calender_button_showing_shadow_1.setText("");
        calender_button_showing_shadow_2.setText("");
        calender_button_showing_shadow_3.setText("");
        calender_button_showing_shadow_4.setText("");
        calender_button_showing_shadow_5.setText("");
        calender_button_showing_shadow_6.setText("");
        calender_button_showing_shadow_7.setText("");
        calender_button_showing_shadow_8.setText("");
        calender_button_showing_shadow_9.setText("");
        calender_button_showing_shadow_10.setText("");
        calender_button_showing_shadow_11.setText("");
        calender_button_showing_shadow_12.setText("");
        calender_button_showing_shadow_13.setText("");
        calender_button_showing_shadow_14.setText("");
        calender_button_showing_shadow_15.setText("");
        calender_button_showing_shadow_16.setText("");
        calender_button_showing_shadow_17.setText("");
        calender_button_showing_shadow_18.setText("");
        calender_button_showing_shadow_19.setText("");
        calender_button_showing_shadow_20.setText("");
        calender_button_showing_shadow_21.setText("");
        calender_button_showing_shadow_22.setText("");
        calender_button_showing_shadow_23.setText("");
        calender_button_showing_shadow_24.setText("");
        calender_button_showing_shadow_25.setText("");
        calender_button_showing_shadow_26.setText("");
        calender_button_showing_shadow_27.setText("");
        calender_button_showing_shadow_28.setText("");
        calender_button_showing_shadow_29.setText("");
        calender_button_showing_shadow_30.setText("");
        calender_button_showing_shadow_31.setText("");
        calender_button_showing_shadow_32.setText("");
        calender_button_showing_shadow_33.setText("");
        calender_button_showing_shadow_34.setText("");
        calender_button_showing_shadow_35.setText("");
        calender_button_showing_shadow_36.setText("");
        calender_button_showing_shadow_37.setText("");
    }

    private void color_the_button_under_the_calender() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            String very_bad_color = return_the_color_of_mood(1);
            String bad_color = return_the_color_of_mood(2);
            String ok_color = return_the_color_of_mood(3);
            String good_color = return_the_color_of_mood(4);
            String very_good_color = return_the_color_of_mood(5);
            String no_mood_color = return_the_color_of_mood(0);
            String[] splitter_temp = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            String month = String.valueOf(return_month_string_to_int(splitter_temp[0]));
            String year = String.valueOf(splitter_temp[1]);
            String[] splitter = color_the_today.split("_");
            View very_bad_mood_shade_in_habits = getView().findViewById(R.id.very_bad_mood_shade_in_habits);
            View bad_mood_shade_in_habits = getView().findViewById(R.id.bad_mood_shade_in_habits);
            View ok_mood_shade_in_habits = getView().findViewById(R.id.ok_mood_shade_in_habits);
            View good_mood_shade_in_habits = getView().findViewById(R.id.good_mood_shade_in_habits);
            View very_good_mood_shade_in_habits = getView().findViewById(R.id.very_good_mood_shade_in_habits);
            View very_good_mood_in_habits = getView().findViewById(R.id.very_good_mood_in_habits);
            if (month.equals(splitter[1]) && year.equals(splitter[2])) {
                if (colors[Integer.parseInt(splitter[0])].equals(very_bad_color)) {
                    if (very_bad_mood_shade_in_habits.getVisibility() != View.VISIBLE && very_good_mood_in_habits.getVisibility() == View.VISIBLE) {
                        selct_the_mood_in_calender(1);
                    }
                } else if (colors[Integer.parseInt(splitter[0])].equals(bad_color) && very_good_mood_in_habits.getVisibility() == View.VISIBLE) {
                    if (bad_mood_shade_in_habits.getVisibility() != View.VISIBLE) {
                        selct_the_mood_in_calender(2);
                    }
                } else if (colors[Integer.parseInt(splitter[0])].equals(ok_color) && very_good_mood_in_habits.getVisibility() == View.VISIBLE) {
                    if (ok_mood_shade_in_habits.getVisibility() != View.VISIBLE) {
                        selct_the_mood_in_calender(3);
                    }
                } else if (colors[Integer.parseInt(splitter[0])].equals(good_color) && very_good_mood_in_habits.getVisibility() == View.VISIBLE) {
                    if (good_mood_shade_in_habits.getVisibility() != View.VISIBLE) {
                        selct_the_mood_in_calender(4);
                    }
                } else if (colors[Integer.parseInt(splitter[0])].equals(very_good_color) && very_good_mood_in_habits.getVisibility() == View.VISIBLE) {
                    if (very_good_mood_shade_in_habits.getVisibility() != View.VISIBLE) {
                        selct_the_mood_in_calender(5);
                    }
                } else if (colors[Integer.parseInt(splitter[0])].equals(no_mood_color) && very_good_mood_in_habits.getVisibility() == View.VISIBLE) {
                    selct_the_mood_in_calender(0);
                }
            }
        }
    }

    private void yes_and_no_button_listen_under_the_calender() {
        if (getView() != null) {
            final Button very_bad_mood_button_in_habits = getView().findViewById(R.id.very_bad_mood_button_in_habits);
            final Button bad_mood_button_in_habits = getView().findViewById(R.id.bad_mood_button_in_habits);
            final Button ok_mood_button_in_habits = getView().findViewById(R.id.ok_mood_button_in_habits);
            final Button good_mood_button_in_habits = getView().findViewById(R.id.good_mood_button_in_habits);
            final Button very_good_mood_button_in_habits = getView().findViewById(R.id.very_good_mood_button_in_habits);
            final View very_bad_mood_shade_in_habits = getView().findViewById(R.id.very_bad_mood_shade_in_habits);
            final View bad_mood_shade_in_habits = getView().findViewById(R.id.bad_mood_shade_in_habits);
            final View ok_mood_shade_in_habits = getView().findViewById(R.id.ok_mood_shade_in_habits);
            final View good_mood_shade_in_habits = getView().findViewById(R.id.good_mood_shade_in_habits);
            final View very_good_mood_shade_in_habits = getView().findViewById(R.id.very_good_mood_shade_in_habits);
            //final CardView card_in_good_habits_habits = getView().findViewById(R.id.card_in_good_habits_habits);
            final TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            very_bad_mood_button_in_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String[] split_for_day_month_year = color_the_today.split("_");
                    String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int calender_day = Integer.parseInt(split_for_day_month_year[0]);
                    int calender_month = return_month_string_to_int(month_and_year[0]);
                    int calender_year = Integer.parseInt(month_and_year[1]);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(calender_year, calender_month, calender_day);
                    if (very_bad_mood_shade_in_habits.getVisibility() == View.VISIBLE) {
                        selct_the_mood_in_calender(0);
                        save_the_input_for_good_habit_input(0, calendar.getTimeInMillis());
                    } else {
                        selct_the_mood_in_calender(0);
                        selct_the_mood_in_calender(1);
                        save_the_input_for_good_habit_input(1, calendar.getTimeInMillis());
                    }
                    color_the_calender();
                    //set_up_day_of_week_bar_chart();
                    clear_all_the_unders();
                    divide_it_into_weeks();
                    clear_the_middle();
                    make_the_middle_come_again();
                    color_the_under();
                    color_the_middle();
                    make_everything_average_mood();
                    draw_the_mood_line_chart();
                    draw_the_right_bar_chart_mood();
                    draw_pie_chart();
                    line_chart_for_streak.fitScreen();
                    set_the_leap_year();
                    put_values_into_year_in_good_habits();
                    //displaying_streak_for_user();
                    //add_to_array();
                    //calculate_the_average_streak();
                    //calculate_the_best_streak();
                    //calculate_the_current_streak();
                    //set_the_text_for_in_card();
                    //draw_pie_chart();
                    //set_up_the_various_streak_chart();
                    //setup_the_four_information_card();
                    //set_the_leap_year();
                    //put_values_into_year_in_good_habits();
                    //line_chart_for_streak.fitScreen();
                }
            });
            bad_mood_button_in_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String[] split_for_day_month_year = color_the_today.split("_");
                    String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int calender_day = Integer.parseInt(split_for_day_month_year[0]);
                    int calender_month = return_month_string_to_int(month_and_year[0]);
                    int calender_year = Integer.parseInt(month_and_year[1]);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(calender_year, calender_month, calender_day);
                    if (bad_mood_shade_in_habits.getVisibility() == View.VISIBLE) {
                        selct_the_mood_in_calender(0);
                        save_the_input_for_good_habit_input(0, calendar.getTimeInMillis());
                    } else {
                        selct_the_mood_in_calender(0);
                        selct_the_mood_in_calender(2);
                        save_the_input_for_good_habit_input(2, calendar.getTimeInMillis());
                    }
                    color_the_calender();
                    //set_up_day_of_week_bar_chart();
                    clear_all_the_unders();
                    divide_it_into_weeks();
                    clear_the_middle();
                    make_the_middle_come_again();
                    color_the_under();
                    color_the_middle();
                    make_everything_average_mood();
                    draw_the_mood_line_chart();
                    draw_the_right_bar_chart_mood();
                    draw_pie_chart();
                    line_chart_for_streak.fitScreen();
                    set_the_leap_year();
                    put_values_into_year_in_good_habits();
                    //displaying_streak_for_user();
                    //add_to_array();
                    //calculate_the_average_streak();
                    //calculate_the_best_streak();
                    //calculate_the_current_streak();
                    //set_the_text_for_in_card();
                    //draw_pie_chart();
                    //set_up_the_various_streak_chart();
                    //setup_the_four_information_card();
                    //set_the_leap_year();
                    //put_values_into_year_in_good_habits();
                    //line_chart_for_streak.fitScreen();
                }
            });
            ok_mood_button_in_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String[] split_for_day_month_year = color_the_today.split("_");
                    String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int calender_day = Integer.parseInt(split_for_day_month_year[0]);
                    int calender_month = return_month_string_to_int(month_and_year[0]);
                    int calender_year = Integer.parseInt(month_and_year[1]);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(calender_year, calender_month, calender_day);
                    if (ok_mood_shade_in_habits.getVisibility() == View.VISIBLE) {
                        selct_the_mood_in_calender(0);
                        save_the_input_for_good_habit_input(0, calendar.getTimeInMillis());
                    } else {
                        selct_the_mood_in_calender(0);
                        selct_the_mood_in_calender(3);
                        save_the_input_for_good_habit_input(3, calendar.getTimeInMillis());
                    }
                    color_the_calender();
                    //set_up_day_of_week_bar_chart();
                    clear_all_the_unders();
                    divide_it_into_weeks();
                    clear_the_middle();
                    make_the_middle_come_again();
                    color_the_under();
                    color_the_middle();
                    make_everything_average_mood();
                    draw_the_mood_line_chart();
                    draw_the_right_bar_chart_mood();
                    draw_pie_chart();
                    line_chart_for_streak.fitScreen();
                    set_the_leap_year();
                    put_values_into_year_in_good_habits();
                    //displaying_streak_for_user();
                    //add_to_array();
                    //calculate_the_average_streak();
                    //calculate_the_best_streak();
                    //calculate_the_current_streak();
                    //set_the_text_for_in_card();
                    //draw_pie_chart();
                    //set_up_the_various_streak_chart();
                    //setup_the_four_information_card();
                    //set_the_leap_year();
                    //put_values_into_year_in_good_habits();
                    //line_chart_for_streak.fitScreen();
                }
            });
            good_mood_button_in_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String[] split_for_day_month_year = color_the_today.split("_");
                    String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int calender_day = Integer.parseInt(split_for_day_month_year[0]);
                    int calender_month = return_month_string_to_int(month_and_year[0]);
                    int calender_year = Integer.parseInt(month_and_year[1]);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(calender_year, calender_month, calender_day);
                    if (good_mood_shade_in_habits.getVisibility() == View.VISIBLE) {
                        selct_the_mood_in_calender(0);
                        save_the_input_for_good_habit_input(0, calendar.getTimeInMillis());
                    } else {
                        selct_the_mood_in_calender(0);
                        selct_the_mood_in_calender(4);
                        save_the_input_for_good_habit_input(4, calendar.getTimeInMillis());
                    }
                    color_the_calender();
                    //set_up_day_of_week_bar_chart();
                    clear_all_the_unders();
                    divide_it_into_weeks();
                    clear_the_middle();
                    make_the_middle_come_again();
                    color_the_under();
                    color_the_middle();
                    make_everything_average_mood();
                    draw_the_mood_line_chart();
                    draw_the_right_bar_chart_mood();
                    draw_pie_chart();
                    line_chart_for_streak.fitScreen();
                    set_the_leap_year();
                    put_values_into_year_in_good_habits();
                    //displaying_streak_for_user();
                    //add_to_array();
                    //calculate_the_average_streak();
                    //calculate_the_best_streak();
                    //calculate_the_current_streak();
                    //set_the_text_for_in_card();
                    //draw_pie_chart();
                    //set_up_the_various_streak_chart();
                    //setup_the_four_information_card();
                    //set_the_leap_year();
                    //put_values_into_year_in_good_habits();
                    //line_chart_for_streak.fitScreen();
                }
            });
            very_good_mood_button_in_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String[] split_for_day_month_year = color_the_today.split("_");
                    String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int calender_day = Integer.parseInt(split_for_day_month_year[0]);
                    int calender_month = return_month_string_to_int(month_and_year[0]);
                    int calender_year = Integer.parseInt(month_and_year[1]);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(calender_year, calender_month, calender_day);
                    if (very_good_mood_shade_in_habits.getVisibility() == View.VISIBLE) {
                        selct_the_mood_in_calender(0);
                        save_the_input_for_good_habit_input(0, calendar.getTimeInMillis());
                    } else {
                        selct_the_mood_in_calender(0);
                        selct_the_mood_in_calender(5);
                        save_the_input_for_good_habit_input(5, calendar.getTimeInMillis());
                    }
                    color_the_calender();
                    //set_up_day_of_week_bar_chart();
                    clear_all_the_unders();
                    divide_it_into_weeks();
                    clear_the_middle();
                    make_the_middle_come_again();
                    color_the_under();
                    color_the_middle();
                    make_everything_average_mood();
                    draw_the_mood_line_chart();
                    draw_the_right_bar_chart_mood();
                    draw_pie_chart();
                    line_chart_for_streak.fitScreen();
                    set_the_leap_year();
                    put_values_into_year_in_good_habits();
                    //displaying_streak_for_user();
                    //add_to_array();
                    //calculate_the_average_streak();
                    //calculate_the_best_streak();
                    //calculate_the_current_streak();
                    //set_the_text_for_in_card();
                    //draw_pie_chart();
                    //set_up_the_various_streak_chart();
                    //setup_the_four_information_card();
                    //set_the_leap_year();
                    //put_values_into_year_in_good_habits();
                    //line_chart_for_streak.fitScreen();
                }
            });
        }
    }

    private void clear_all_the_unders() {
        if (getView() != null) {
            View calender_under_number_state_1 = getView().findViewById(R.id.calender_under_number_state_1);
            View calender_under_number_state_2 = getView().findViewById(R.id.calender_under_number_state_2);
            View calender_under_number_state_3 = getView().findViewById(R.id.calender_under_number_state_3);
            View calender_under_number_state_4 = getView().findViewById(R.id.calender_under_number_state_4);
            View calender_under_number_state_5 = getView().findViewById(R.id.calender_under_number_state_5);
            View calender_under_number_state_6 = getView().findViewById(R.id.calender_under_number_state_6);
            View calender_under_number_state_7 = getView().findViewById(R.id.calender_under_number_state_7);
            View calender_under_number_state_8 = getView().findViewById(R.id.calender_under_number_state_8);
            View calender_under_number_state_9 = getView().findViewById(R.id.calender_under_number_state_9);
            View calender_under_number_state_10 = getView().findViewById(R.id.calender_under_number_state_10);
            View calender_under_number_state_11 = getView().findViewById(R.id.calender_under_number_state_11);
            View calender_under_number_state_12 = getView().findViewById(R.id.calender_under_number_state_12);
            View calender_under_number_state_13 = getView().findViewById(R.id.calender_under_number_state_13);
            View calender_under_number_state_14 = getView().findViewById(R.id.calender_under_number_state_14);
            View calender_under_number_state_15 = getView().findViewById(R.id.calender_under_number_state_15);
            View calender_under_number_state_16 = getView().findViewById(R.id.calender_under_number_state_16);
            View calender_under_number_state_17 = getView().findViewById(R.id.calender_under_number_state_17);
            View calender_under_number_state_18 = getView().findViewById(R.id.calender_under_number_state_18);
            View calender_under_number_state_19 = getView().findViewById(R.id.calender_under_number_state_19);
            View calender_under_number_state_20 = getView().findViewById(R.id.calender_under_number_state_20);
            View calender_under_number_state_21 = getView().findViewById(R.id.calender_under_number_state_21);
            View calender_under_number_state_22 = getView().findViewById(R.id.calender_under_number_state_22);
            View calender_under_number_state_23 = getView().findViewById(R.id.calender_under_number_state_23);
            View calender_under_number_state_24 = getView().findViewById(R.id.calender_under_number_state_24);
            View calender_under_number_state_25 = getView().findViewById(R.id.calender_under_number_state_25);
            View calender_under_number_state_26 = getView().findViewById(R.id.calender_under_number_state_26);
            View calender_under_number_state_27 = getView().findViewById(R.id.calender_under_number_state_27);
            View calender_under_number_state_28 = getView().findViewById(R.id.calender_under_number_state_28);
            View calender_under_number_state_29 = getView().findViewById(R.id.calender_under_number_state_29);
            View calender_under_number_state_30 = getView().findViewById(R.id.calender_under_number_state_30);
            View calender_under_number_state_31 = getView().findViewById(R.id.calender_under_number_state_31);
            View calender_under_number_state_32 = getView().findViewById(R.id.calender_under_number_state_32);
            View calender_under_number_state_33 = getView().findViewById(R.id.calender_under_number_state_33);
            View calender_under_number_state_34 = getView().findViewById(R.id.calender_under_number_state_34);
            View calender_under_number_state_35 = getView().findViewById(R.id.calender_under_number_state_35);
            View calender_under_number_state_36 = getView().findViewById(R.id.calender_under_number_state_36);
            View calender_under_number_state_37 = getView().findViewById(R.id.calender_under_number_state_37);
            calender_under_number_state_1.setBackgroundResource(0);
            calender_under_number_state_2.setBackgroundResource(0);
            calender_under_number_state_3.setBackgroundResource(0);
            calender_under_number_state_4.setBackgroundResource(0);
            calender_under_number_state_5.setBackgroundResource(0);
            calender_under_number_state_6.setBackgroundResource(0);
            calender_under_number_state_7.setBackgroundResource(0);
            calender_under_number_state_8.setBackgroundResource(0);
            calender_under_number_state_9.setBackgroundResource(0);
            calender_under_number_state_10.setBackgroundResource(0);
            calender_under_number_state_11.setBackgroundResource(0);
            calender_under_number_state_12.setBackgroundResource(0);
            calender_under_number_state_13.setBackgroundResource(0);
            calender_under_number_state_14.setBackgroundResource(0);
            calender_under_number_state_15.setBackgroundResource(0);
            calender_under_number_state_16.setBackgroundResource(0);
            calender_under_number_state_17.setBackgroundResource(0);
            calender_under_number_state_18.setBackgroundResource(0);
            calender_under_number_state_19.setBackgroundResource(0);
            calender_under_number_state_20.setBackgroundResource(0);
            calender_under_number_state_21.setBackgroundResource(0);
            calender_under_number_state_22.setBackgroundResource(0);
            calender_under_number_state_23.setBackgroundResource(0);
            calender_under_number_state_24.setBackgroundResource(0);
            calender_under_number_state_25.setBackgroundResource(0);
            calender_under_number_state_26.setBackgroundResource(0);
            calender_under_number_state_27.setBackgroundResource(0);
            calender_under_number_state_28.setBackgroundResource(0);
            calender_under_number_state_29.setBackgroundResource(0);
            calender_under_number_state_30.setBackgroundResource(0);
            calender_under_number_state_31.setBackgroundResource(0);
            calender_under_number_state_32.setBackgroundResource(0);
            calender_under_number_state_33.setBackgroundResource(0);
            calender_under_number_state_34.setBackgroundResource(0);
            calender_under_number_state_35.setBackgroundResource(0);
            calender_under_number_state_36.setBackgroundResource(0);
            calender_under_number_state_37.setBackgroundResource(0);
        }
    }

    private void clear_the_middle() {
        if (getView() != null) {
            View middle_calender_1 = getView().findViewById(R.id.middle_calender_1);
            View middle_calender_2 = getView().findViewById(R.id.middle_calender_2);
            View middle_calender_3 = getView().findViewById(R.id.middle_calender_3);
            View middle_calender_4 = getView().findViewById(R.id.middle_calender_4);
            View middle_calender_5 = getView().findViewById(R.id.middle_calender_5);
            View middle_calender_6 = getView().findViewById(R.id.middle_calender_6);
            View middle_calender_7 = getView().findViewById(R.id.middle_calender_7);
            View middle_calender_8 = getView().findViewById(R.id.middle_calender_8);
            View middle_calender_9 = getView().findViewById(R.id.middle_calender_9);
            View middle_calender_10 = getView().findViewById(R.id.middle_calender_10);
            View middle_calender_11 = getView().findViewById(R.id.middle_calender_11);
            View middle_calender_12 = getView().findViewById(R.id.middle_calender_12);
            View middle_calender_13 = getView().findViewById(R.id.middle_calender_13);
            View middle_calender_14 = getView().findViewById(R.id.middle_calender_14);
            View middle_calender_15 = getView().findViewById(R.id.middle_calender_15);
            View middle_calender_16 = getView().findViewById(R.id.middle_calender_16);
            View middle_calender_17 = getView().findViewById(R.id.middle_calender_17);
            View middle_calender_18 = getView().findViewById(R.id.middle_calender_18);
            View middle_calender_19 = getView().findViewById(R.id.middle_calender_19);
            View middle_calender_20 = getView().findViewById(R.id.middle_calender_20);
            View middle_calender_21 = getView().findViewById(R.id.middle_calender_21);
            View middle_calender_22 = getView().findViewById(R.id.middle_calender_22);
            View middle_calender_23 = getView().findViewById(R.id.middle_calender_23);
            View middle_calender_24 = getView().findViewById(R.id.middle_calender_24);
            View middle_calender_25 = getView().findViewById(R.id.middle_calender_25);
            View middle_calender_26 = getView().findViewById(R.id.middle_calender_26);
            View middle_calender_27 = getView().findViewById(R.id.middle_calender_27);
            View middle_calender_28 = getView().findViewById(R.id.middle_calender_28);
            View middle_calender_29 = getView().findViewById(R.id.middle_calender_29);
            View middle_calender_30 = getView().findViewById(R.id.middle_calender_30);
            View middle_calender_31 = getView().findViewById(R.id.middle_calender_31);
            middle_calender_1.setVisibility(View.INVISIBLE);
            middle_calender_2.setVisibility(View.INVISIBLE);
            middle_calender_3.setVisibility(View.INVISIBLE);
            middle_calender_4.setVisibility(View.INVISIBLE);
            middle_calender_5.setVisibility(View.INVISIBLE);
            middle_calender_6.setVisibility(View.INVISIBLE);
            middle_calender_7.setVisibility(View.INVISIBLE);
            middle_calender_8.setVisibility(View.INVISIBLE);
            middle_calender_9.setVisibility(View.INVISIBLE);
            middle_calender_10.setVisibility(View.INVISIBLE);
            middle_calender_11.setVisibility(View.INVISIBLE);
            middle_calender_12.setVisibility(View.INVISIBLE);
            middle_calender_13.setVisibility(View.INVISIBLE);
            middle_calender_14.setVisibility(View.INVISIBLE);
            middle_calender_15.setVisibility(View.INVISIBLE);
            middle_calender_16.setVisibility(View.INVISIBLE);
            middle_calender_17.setVisibility(View.INVISIBLE);
            middle_calender_18.setVisibility(View.INVISIBLE);
            middle_calender_19.setVisibility(View.INVISIBLE);
            middle_calender_20.setVisibility(View.INVISIBLE);
            middle_calender_21.setVisibility(View.INVISIBLE);
            middle_calender_22.setVisibility(View.INVISIBLE);
            middle_calender_23.setVisibility(View.INVISIBLE);
            middle_calender_24.setVisibility(View.INVISIBLE);
            middle_calender_25.setVisibility(View.INVISIBLE);
            middle_calender_26.setVisibility(View.INVISIBLE);
            middle_calender_27.setVisibility(View.INVISIBLE);
            middle_calender_28.setVisibility(View.INVISIBLE);
            middle_calender_29.setVisibility(View.INVISIBLE);
            middle_calender_30.setVisibility(View.INVISIBLE);
            middle_calender_31.setVisibility(View.INVISIBLE);
        }
    }

    private void make_the_middle_come_again() {
        if (getView() != null) {
            /*View calender_under_number_state_1 = getView().findViewById(R.id.calender_under_number_state_1);
            View calender_under_number_state_2 = getView().findViewById(R.id.calender_under_number_state_2);
            View calender_under_number_state_3 = getView().findViewById(R.id.calender_under_number_state_3);
            View calender_under_number_state_4 = getView().findViewById(R.id.calender_under_number_state_4);
            View calender_under_number_state_5 = getView().findViewById(R.id.calender_under_number_state_5);
            View calender_under_number_state_6 = getView().findViewById(R.id.calender_under_number_state_6);
            View calender_under_number_state_8 = getView().findViewById(R.id.calender_under_number_state_8);
            View calender_under_number_state_9 = getView().findViewById(R.id.calender_under_number_state_9);
            View calender_under_number_state_10 = getView().findViewById(R.id.calender_under_number_state_10);
            View calender_under_number_state_11 = getView().findViewById(R.id.calender_under_number_state_11);
            View calender_under_number_state_12 = getView().findViewById(R.id.calender_under_number_state_12);
            View calender_under_number_state_13 = getView().findViewById(R.id.calender_under_number_state_13);
            View calender_under_number_state_15 = getView().findViewById(R.id.calender_under_number_state_15);
            View calender_under_number_state_16 = getView().findViewById(R.id.calender_under_number_state_16);
            View calender_under_number_state_17 = getView().findViewById(R.id.calender_under_number_state_17);
            View calender_under_number_state_18 = getView().findViewById(R.id.calender_under_number_state_18);
            View calender_under_number_state_19 = getView().findViewById(R.id.calender_under_number_state_19);
            View calender_under_number_state_20 = getView().findViewById(R.id.calender_under_number_state_20);
            View calender_under_number_state_22 = getView().findViewById(R.id.calender_under_number_state_22);
            View calender_under_number_state_23 = getView().findViewById(R.id.calender_under_number_state_23);
            View calender_under_number_state_24 = getView().findViewById(R.id.calender_under_number_state_24);
            View calender_under_number_state_25 = getView().findViewById(R.id.calender_under_number_state_25);
            View calender_under_number_state_26 = getView().findViewById(R.id.calender_under_number_state_26);
            View calender_under_number_state_27 = getView().findViewById(R.id.calender_under_number_state_27);
            View calender_under_number_state_29 = getView().findViewById(R.id.calender_under_number_state_29);
            View calender_under_number_state_30 = getView().findViewById(R.id.calender_under_number_state_30);
            View calender_under_number_state_31 = getView().findViewById(R.id.calender_under_number_state_31);
            View calender_under_number_state_32 = getView().findViewById(R.id.calender_under_number_state_32);
            View calender_under_number_state_33 = getView().findViewById(R.id.calender_under_number_state_33);
            View calender_under_number_state_34 = getView().findViewById(R.id.calender_under_number_state_34);
            View calender_under_number_state_36 = getView().findViewById(R.id.calender_under_number_state_36);*/
            View middle_calender_1 = getView().findViewById(R.id.middle_calender_1);
            View middle_calender_2 = getView().findViewById(R.id.middle_calender_2);
            View middle_calender_3 = getView().findViewById(R.id.middle_calender_3);
            View middle_calender_4 = getView().findViewById(R.id.middle_calender_4);
            View middle_calender_5 = getView().findViewById(R.id.middle_calender_5);
            View middle_calender_6 = getView().findViewById(R.id.middle_calender_6);
            View middle_calender_7 = getView().findViewById(R.id.middle_calender_7);
            View middle_calender_8 = getView().findViewById(R.id.middle_calender_8);
            View middle_calender_9 = getView().findViewById(R.id.middle_calender_9);
            View middle_calender_10 = getView().findViewById(R.id.middle_calender_10);
            View middle_calender_11 = getView().findViewById(R.id.middle_calender_11);
            View middle_calender_12 = getView().findViewById(R.id.middle_calender_12);
            View middle_calender_13 = getView().findViewById(R.id.middle_calender_13);
            View middle_calender_14 = getView().findViewById(R.id.middle_calender_14);
            View middle_calender_15 = getView().findViewById(R.id.middle_calender_15);
            View middle_calender_16 = getView().findViewById(R.id.middle_calender_16);
            View middle_calender_17 = getView().findViewById(R.id.middle_calender_17);
            View middle_calender_18 = getView().findViewById(R.id.middle_calender_18);
            View middle_calender_19 = getView().findViewById(R.id.middle_calender_19);
            View middle_calender_20 = getView().findViewById(R.id.middle_calender_20);
            View middle_calender_21 = getView().findViewById(R.id.middle_calender_21);
            View middle_calender_22 = getView().findViewById(R.id.middle_calender_22);
            View middle_calender_23 = getView().findViewById(R.id.middle_calender_23);
            View middle_calender_24 = getView().findViewById(R.id.middle_calender_24);
            View middle_calender_25 = getView().findViewById(R.id.middle_calender_25);
            View middle_calender_26 = getView().findViewById(R.id.middle_calender_26);
            View middle_calender_27 = getView().findViewById(R.id.middle_calender_27);
            View middle_calender_28 = getView().findViewById(R.id.middle_calender_28);
            View middle_calender_29 = getView().findViewById(R.id.middle_calender_29);
            View middle_calender_30 = getView().findViewById(R.id.middle_calender_30);
            View middle_calender_31 = getView().findViewById(R.id.middle_calender_31);
            if ((modes_for_four_drawable[0] == 1 || modes_for_four_drawable[0] == 2) && (modes_for_four_drawable[1] == 3 || modes_for_four_drawable[1] == 2)) {
                middle_calender_1.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[1] == 1 || modes_for_four_drawable[1] == 2) && (modes_for_four_drawable[2] == 3 || modes_for_four_drawable[2] == 2)) {
                middle_calender_2.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[2] == 1 || modes_for_four_drawable[2] == 2) && (modes_for_four_drawable[3] == 3 || modes_for_four_drawable[3] == 2)) {
                middle_calender_3.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[3] == 1 || modes_for_four_drawable[3] == 2) && (modes_for_four_drawable[4] == 3 || modes_for_four_drawable[4] == 2)) {
                middle_calender_4.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[4] == 1 || modes_for_four_drawable[4] == 2) && (modes_for_four_drawable[5] == 3 || modes_for_four_drawable[5] == 2)) {
                middle_calender_5.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[5] == 1 || modes_for_four_drawable[5] == 2) && (modes_for_four_drawable[6] == 3 || modes_for_four_drawable[6] == 2)) {
                middle_calender_6.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[7] == 1 || modes_for_four_drawable[7] == 2) && (modes_for_four_drawable[8] == 3 || modes_for_four_drawable[8] == 2)) {
                middle_calender_7.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[8] == 1 || modes_for_four_drawable[8] == 2) && (modes_for_four_drawable[9] == 3 || modes_for_four_drawable[9] == 2)) {
                middle_calender_8.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[9] == 1 || modes_for_four_drawable[9] == 2) && (modes_for_four_drawable[10] == 3 || modes_for_four_drawable[10] == 2)) {
                middle_calender_9.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[10] == 1 || modes_for_four_drawable[10] == 2) && (modes_for_four_drawable[11] == 3 || modes_for_four_drawable[11] == 2)) {
                middle_calender_10.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[11] == 1 || modes_for_four_drawable[11] == 2) && (modes_for_four_drawable[12] == 3 || modes_for_four_drawable[12] == 2)) {
                middle_calender_11.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[12] == 1 || modes_for_four_drawable[12] == 2) && (modes_for_four_drawable[13] == 3 || modes_for_four_drawable[13] == 2)) {
                middle_calender_12.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[14] == 1 || modes_for_four_drawable[14] == 2) && (modes_for_four_drawable[15] == 3 || modes_for_four_drawable[15] == 2)) {
                middle_calender_13.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[15] == 1 || modes_for_four_drawable[15] == 2) && (modes_for_four_drawable[16] == 3 || modes_for_four_drawable[16] == 2)) {
                middle_calender_14.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[16] == 1 || modes_for_four_drawable[16] == 2) && (modes_for_four_drawable[17] == 3 || modes_for_four_drawable[17] == 2)) {
                middle_calender_15.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[17] == 1 || modes_for_four_drawable[17] == 2) && (modes_for_four_drawable[18] == 3 || modes_for_four_drawable[18] == 2)) {
                middle_calender_16.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[18] == 1 || modes_for_four_drawable[18] == 2) && (modes_for_four_drawable[19] == 3 || modes_for_four_drawable[19] == 2)) {
                middle_calender_17.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[19] == 1 || modes_for_four_drawable[19] == 2) && (modes_for_four_drawable[20] == 3 || modes_for_four_drawable[20] == 2)) {
                middle_calender_18.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[21] == 1 || modes_for_four_drawable[21] == 2) && (modes_for_four_drawable[22] == 3 || modes_for_four_drawable[22] == 2)) {
                middle_calender_19.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[22] == 1 || modes_for_four_drawable[22] == 2) && (modes_for_four_drawable[23] == 3 || modes_for_four_drawable[23] == 2)) {
                middle_calender_20.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[23] == 1 || modes_for_four_drawable[23] == 2) && (modes_for_four_drawable[24] == 3 || modes_for_four_drawable[24] == 2)) {
                middle_calender_21.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[24] == 1 || modes_for_four_drawable[24] == 2) && (modes_for_four_drawable[25] == 3 || modes_for_four_drawable[25] == 2)) {
                middle_calender_22.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[25] == 1 || modes_for_four_drawable[25] == 2) && (modes_for_four_drawable[26] == 3 || modes_for_four_drawable[26] == 2)) {
                middle_calender_23.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[26] == 1 || modes_for_four_drawable[26] == 2) && (modes_for_four_drawable[27] == 3 || modes_for_four_drawable[27] == 2)) {
                middle_calender_24.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[28] == 1 || modes_for_four_drawable[28] == 2) && (modes_for_four_drawable[29] == 3 || modes_for_four_drawable[29] == 2)) {
                middle_calender_25.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[29] == 1 || modes_for_four_drawable[29] == 2) && (modes_for_four_drawable[30] == 3 || modes_for_four_drawable[30] == 2)) {
                middle_calender_26.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[30] == 1 || modes_for_four_drawable[30] == 2) && (modes_for_four_drawable[31] == 3 || modes_for_four_drawable[31] == 2)) {
                middle_calender_27.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[31] == 1 || modes_for_four_drawable[31] == 2) && (modes_for_four_drawable[32] == 3 || modes_for_four_drawable[32] == 2)) {
                middle_calender_28.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[32] == 1 || modes_for_four_drawable[32] == 2) && (modes_for_four_drawable[33] == 3 || modes_for_four_drawable[33] == 2)) {
                middle_calender_29.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[33] == 1 || modes_for_four_drawable[33] == 2) && (modes_for_four_drawable[34] == 3 || modes_for_four_drawable[34] == 2)) {
                middle_calender_30.setVisibility(View.VISIBLE);
            }
            if ((modes_for_four_drawable[35] == 1 || modes_for_four_drawable[35] == 2) && (modes_for_four_drawable[36] == 3 || modes_for_four_drawable[36] == 2)) {
                middle_calender_31.setVisibility(View.VISIBLE);
            }
        }
    }

    private void color_the_4_drawables() {
        if (getContext() != null && getView() != null) {
            LayerDrawable layerDrawable = (LayerDrawable) ContextCompat.getDrawable(getContext(), R.drawable.first_part_rectangle_calender);
            if (layerDrawable != null) {
                Drawable drawable1 = layerDrawable.getDrawable(1);
                drawable1.setTint(color);
            }
            middle_part_rectangle_calender = ContextCompat.getDrawable(getContext(), R.drawable.middle_part_drawable_color);
            if (middle_part_rectangle_calender != null) {
                middle_part_rectangle_calender.setTint(color);
            }
            LayerDrawable layerDrawable3 = (LayerDrawable) ContextCompat.getDrawable(getContext(), R.drawable.last_part_rectangle_calender);
            if (layerDrawable3 != null) {
                Drawable drawable1 = layerDrawable3.getDrawable(1);
                drawable1.setTint(color);
            }
            LayerDrawable layerDrawable4 = (LayerDrawable) ContextCompat.getDrawable(getContext(), R.drawable.first_and_last_part_rectangle_calender);
            if (layerDrawable4 != null) {
                Drawable drawable1 = layerDrawable4.getDrawable(1);
                drawable1.setTint(color);
            }
            first_part_rectangle_calender = ContextCompat.getDrawable(getContext(), R.drawable.first_part_rectangle_calender);
            last_part_rectangle_calender = ContextCompat.getDrawable(getContext(), R.drawable.last_part_rectangle_calender);
            first_and_last_part_rectangle_calender = ContextCompat.getDrawable(getContext(), R.drawable.first_and_last_part_rectangle_calender);
        }
    }


    private void divide_it_into_weeks() {
        int month_start_day;
        if (calender_button_showing_shadow_1.getVisibility() == View.VISIBLE) {
            month_start_day = 7;
        } else if (calender_button_showing_shadow_2.getVisibility() == View.VISIBLE) {
            month_start_day = 6;
        } else if (calender_button_showing_shadow_3.getVisibility() == View.VISIBLE) {
            month_start_day = 5;
        } else if (calender_button_showing_shadow_4.getVisibility() == View.VISIBLE) {
            month_start_day = 4;
        } else if (calender_button_showing_shadow_5.getVisibility() == View.VISIBLE) {
            month_start_day = 3;
        } else if (calender_button_showing_shadow_6.getVisibility() == View.VISIBLE) {
            month_start_day = 2;
        } else {
            month_start_day = 1;
        }
        String[] splitter_read = return_the_state_of_the_days().split("split");
        String first_line_string = "";
        String second_line_string = "";
        String three_line_string = "";
        String four_line_string = "";
        String five_line_string = "";
        String six_line_string = "";
        for (int i = 0; i < month_start_day; i++) {
            first_line_string = first_line_string.concat(splitter_read[i]).concat("split");
        }
        for (int i = month_start_day; i < month_start_day + 7; i++) {
            second_line_string = second_line_string.concat(splitter_read[i]).concat("split");
        }
        for (int i = month_start_day + 7; i < month_start_day + 14; i++) {
            three_line_string = three_line_string.concat(splitter_read[i]).concat("split");
        }
        for (int i = month_start_day + 14; i < month_start_day + 21; i++) {
            four_line_string = four_line_string.concat(splitter_read[i]).concat("split");
        }
        int how_many_are_empty = return_last_day_of_month() + (7 - month_start_day);
        if (how_many_are_empty <= 35) {
            for (int i = month_start_day + 21; i < splitter_read.length; i++) {
                five_line_string = five_line_string.concat(splitter_read[i]).concat("split");
            }
        } else {
            for (int i = month_start_day + 21; i < month_start_day + 28; i++) {
                five_line_string = five_line_string.concat(splitter_read[i]).concat("split");
            }
            for (int i = month_start_day + 28; i < splitter_read.length; i++) {
                six_line_string = six_line_string.concat(splitter_read[i]).concat("split");
            }
        }
        set_the_first_line(first_line_string);
        set_the_second_line(second_line_string);
        set_the_third_line(three_line_string);
        set_the_fourth_line(four_line_string);
        set_the_fifth_line(five_line_string);
        set_the_sixth_line(six_line_string);
    }

    private void set_the_first_line(String date) {
        if (getView() != null) {
            String[] date_split = date.split("split");
            int empty_length = 7 - date_split.length;
            for (int i = 0; i < empty_length; i++) {
                date = "empty".concat("split").concat(date);
            }
            String[] splitter_second = date.split("split");
            if (splitter_second[0].equals("empty")) {
                modes_for_four_drawable[0] = 0;
            } else if (splitter_second[0].equals("start")) {
                modes_for_four_drawable[0] = 1;
            } else if (splitter_second[0].equals("middle")) {
                modes_for_four_drawable[0] = 2;
            } else if (splitter_second[0].equals("end")) {
                modes_for_four_drawable[0] = 3;
            } else if (splitter_second[0].equals("beg_last")) {
                modes_for_four_drawable[0] = 4;
            }
            if (splitter_second[1].equals("empty")) {
                modes_for_four_drawable[1] = 0;
            } else if (splitter_second[1].equals("start")) {
                modes_for_four_drawable[1] = 1;
            } else if (splitter_second[1].equals("middle")) {
                modes_for_four_drawable[1] = 2;
            } else if (splitter_second[1].equals("end")) {
                modes_for_four_drawable[1] = 3;
            } else if (splitter_second[1].equals("beg_last")) {
                modes_for_four_drawable[1] = 4;
            }
            if (splitter_second[2].equals("empty")) {
                modes_for_four_drawable[2] = 0;
            } else if (splitter_second[2].equals("start")) {
                modes_for_four_drawable[2] = 1;
            } else if (splitter_second[2].equals("middle")) {
                modes_for_four_drawable[2] = 2;
            } else if (splitter_second[2].equals("end")) {
                modes_for_four_drawable[2] = 3;
            } else if (splitter_second[2].equals("beg_last")) {
                modes_for_four_drawable[2] = 4;
            }
            if (splitter_second[3].equals("empty")) {
                modes_for_four_drawable[3] = 0;
            } else if (splitter_second[3].equals("start")) {
                modes_for_four_drawable[3] = 1;
            } else if (splitter_second[3].equals("middle")) {
                modes_for_four_drawable[3] = 2;
            } else if (splitter_second[3].equals("end")) {
                modes_for_four_drawable[3] = 3;
            } else if (splitter_second[3].equals("beg_last")) {
                modes_for_four_drawable[3] = 4;
            }
            if (splitter_second[4].equals("empty")) {
                modes_for_four_drawable[4] = 0;
            } else if (splitter_second[4].equals("start")) {
                modes_for_four_drawable[4] = 1;
            } else if (splitter_second[4].equals("middle")) {
                modes_for_four_drawable[4] = 2;
            } else if (splitter_second[4].equals("end")) {
                modes_for_four_drawable[4] = 3;
            } else if (splitter_second[4].equals("beg_last")) {
                modes_for_four_drawable[4] = 4;
            }
            if (splitter_second[5].equals("empty")) {
                modes_for_four_drawable[5] = 0;
            } else if (splitter_second[5].equals("start")) {
                modes_for_four_drawable[5] = 1;
            } else if (splitter_second[5].equals("middle")) {
                modes_for_four_drawable[5] = 2;
            } else if (splitter_second[5].equals("end")) {
                modes_for_four_drawable[5] = 3;
            } else if (splitter_second[5].equals("beg_last")) {
                modes_for_four_drawable[5] = 4;
            }
            if (splitter_second[6].equals("empty")) {
                modes_for_four_drawable[6] = 0;
            } else if (splitter_second[6].equals("start")) {
                modes_for_four_drawable[6] = 1;
            } else if (splitter_second[6].equals("middle")) {
                modes_for_four_drawable[6] = 2;
            } else if (splitter_second[6].equals("end")) {
                modes_for_four_drawable[6] = 3;
            } else if (splitter_second[6].equals("beg_last")) {
                modes_for_four_drawable[6] = 4;
            }
        }
    }

    private void set_the_second_line(String date) {
        if (getView() != null) {
            String[] date_split = date.split("split");
            if (date_split[0].equals("empty")) {
                modes_for_four_drawable[7] = 0;
            } else if (date_split[0].equals("start")) {
                modes_for_four_drawable[7] = 1;
            } else if (date_split[0].equals("middle")) {
                modes_for_four_drawable[7] = 2;
            } else if (date_split[0].equals("end")) {
                modes_for_four_drawable[7] = 3;
            } else if (date_split[0].equals("beg_last")) {
                modes_for_four_drawable[7] = 4;
            }
            if (date_split[1].equals("empty")) {
                modes_for_four_drawable[8] = 0;
            } else if (date_split[1].equals("start")) {
                modes_for_four_drawable[8] = 1;
            } else if (date_split[1].equals("middle")) {
                modes_for_four_drawable[8] = 2;
            } else if (date_split[1].equals("end")) {
                modes_for_four_drawable[8] = 3;
            } else if (date_split[1].equals("beg_last")) {
                modes_for_four_drawable[8] = 4;
            }
            if (date_split[2].equals("empty")) {
                modes_for_four_drawable[9] = 0;
            } else if (date_split[2].equals("start")) {
                modes_for_four_drawable[9] = 1;
            } else if (date_split[2].equals("middle")) {
                modes_for_four_drawable[9] = 2;
            } else if (date_split[2].equals("end")) {
                modes_for_four_drawable[9] = 3;
            } else if (date_split[2].equals("beg_last")) {
                modes_for_four_drawable[9] = 4;
            }
            if (date_split[3].equals("empty")) {
                modes_for_four_drawable[10] = 0;
            } else if (date_split[3].equals("start")) {
                modes_for_four_drawable[10] = 1;
            } else if (date_split[3].equals("middle")) {
                modes_for_four_drawable[10] = 2;
            } else if (date_split[3].equals("end")) {
                modes_for_four_drawable[10] = 3;
            } else if (date_split[3].equals("beg_last")) {
                modes_for_four_drawable[10] = 4;
            }
            if (date_split[4].equals("empty")) {
                modes_for_four_drawable[11] = 0;
            } else if (date_split[4].equals("start")) {
                modes_for_four_drawable[11] = 1;
            } else if (date_split[4].equals("middle")) {
                modes_for_four_drawable[11] = 2;
            } else if (date_split[4].equals("end")) {
                modes_for_four_drawable[11] = 3;
            } else if (date_split[4].equals("beg_last")) {
                modes_for_four_drawable[11] = 4;
            }
            if (date_split[5].equals("empty")) {
                modes_for_four_drawable[12] = 0;
            } else if (date_split[5].equals("start")) {
                modes_for_four_drawable[12] = 1;
            } else if (date_split[5].equals("middle")) {
                modes_for_four_drawable[12] = 2;
            } else if (date_split[5].equals("end")) {
                modes_for_four_drawable[12] = 3;
            } else if (date_split[5].equals("beg_last")) {
                modes_for_four_drawable[12] = 4;
            }
            if (date_split[6].equals("empty")) {
                modes_for_four_drawable[13] = 0;
            } else if (date_split[6].equals("start")) {
                modes_for_four_drawable[13] = 1;
            } else if (date_split[6].equals("middle")) {
                modes_for_four_drawable[13] = 2;
            } else if (date_split[6].equals("end")) {
                modes_for_four_drawable[13] = 3;
            } else if (date_split[6].equals("beg_last")) {
                modes_for_four_drawable[13] = 4;
            }
        }
    }

    private void set_the_third_line(String date) {
        if (getView() != null) {
            String[] date_split = date.split("split");
            if (date_split[0].equals("empty")) {
                modes_for_four_drawable[14] = 0;
            } else if (date_split[0].equals("start")) {
                modes_for_four_drawable[14] = 1;
            } else if (date_split[0].equals("middle")) {
                modes_for_four_drawable[14] = 2;
            } else if (date_split[0].equals("end")) {
                modes_for_four_drawable[14] = 3;
            } else if (date_split[0].equals("beg_last")) {
                modes_for_four_drawable[14] = 4;
            }
            if (date_split[1].equals("empty")) {
                modes_for_four_drawable[15] = 0;
            } else if (date_split[1].equals("start")) {
                modes_for_four_drawable[15] = 1;
            } else if (date_split[1].equals("middle")) {
                modes_for_four_drawable[15] = 2;
            } else if (date_split[1].equals("end")) {
                modes_for_four_drawable[15] = 3;
            } else if (date_split[1].equals("beg_last")) {
                modes_for_four_drawable[15] = 4;
            }
            if (date_split[2].equals("empty")) {
                modes_for_four_drawable[16] = 0;
            } else if (date_split[2].equals("start")) {
                modes_for_four_drawable[16] = 1;
            } else if (date_split[2].equals("middle")) {
                modes_for_four_drawable[16] = 2;
            } else if (date_split[2].equals("end")) {
                modes_for_four_drawable[16] = 3;
            } else if (date_split[2].equals("beg_last")) {
                modes_for_four_drawable[16] = 4;
            }
            if (date_split[3].equals("empty")) {
                modes_for_four_drawable[17] = 0;
            } else if (date_split[3].equals("start")) {
                modes_for_four_drawable[17] = 1;
            } else if (date_split[3].equals("middle")) {
                modes_for_four_drawable[17] = 2;
            } else if (date_split[3].equals("end")) {
                modes_for_four_drawable[17] = 3;
            } else if (date_split[3].equals("beg_last")) {
                modes_for_four_drawable[17] = 4;
            }
            if (date_split[4].equals("empty")) {
                modes_for_four_drawable[18] = 0;
            } else if (date_split[4].equals("start")) {
                modes_for_four_drawable[18] = 1;
            } else if (date_split[4].equals("middle")) {
                modes_for_four_drawable[18] = 2;
            } else if (date_split[4].equals("end")) {
                modes_for_four_drawable[18] = 3;
            } else if (date_split[4].equals("beg_last")) {
                modes_for_four_drawable[18] = 4;
            }
            if (date_split[5].equals("empty")) {
                modes_for_four_drawable[19] = 0;
            } else if (date_split[5].equals("start")) {
                modes_for_four_drawable[19] = 1;
            } else if (date_split[5].equals("middle")) {
                modes_for_four_drawable[19] = 2;
            } else if (date_split[5].equals("end")) {
                modes_for_four_drawable[19] = 3;
            } else if (date_split[5].equals("beg_last")) {
                modes_for_four_drawable[19] = 4;
            }
            if (date_split[6].equals("empty")) {
                modes_for_four_drawable[20] = 0;
            } else if (date_split[6].equals("start")) {
                modes_for_four_drawable[20] = 1;
            } else if (date_split[6].equals("middle")) {
                modes_for_four_drawable[20] = 2;
            } else if (date_split[6].equals("end")) {
                modes_for_four_drawable[20] = 3;
            } else if (date_split[6].equals("beg_last")) {
                modes_for_four_drawable[20] = 4;
            }
        }
    }

    private void set_the_fourth_line(String date) {
        if (getView() != null) {
            String[] date_split = date.split("split");
            if (date_split[0].equals("empty")) {
                modes_for_four_drawable[21] = 0;
            } else if (date_split[0].equals("start")) {
                modes_for_four_drawable[21] = 1;
            } else if (date_split[0].equals("middle")) {
                modes_for_four_drawable[21] = 2;
            } else if (date_split[0].equals("end")) {
                modes_for_four_drawable[21] = 3;
            } else if (date_split[0].equals("beg_last")) {
                modes_for_four_drawable[21] = 4;
            }
            if (date_split[1].equals("empty")) {
                modes_for_four_drawable[22] = 0;
            } else if (date_split[1].equals("start")) {
                modes_for_four_drawable[22] = 1;
            } else if (date_split[1].equals("middle")) {
                modes_for_four_drawable[22] = 2;
            } else if (date_split[1].equals("end")) {
                modes_for_four_drawable[22] = 3;
            } else if (date_split[1].equals("beg_last")) {
                modes_for_four_drawable[22] = 4;
            }
            if (date_split[2].equals("empty")) {
                modes_for_four_drawable[23] = 0;
            } else if (date_split[2].equals("start")) {
                modes_for_four_drawable[23] = 1;
            } else if (date_split[2].equals("middle")) {
                modes_for_four_drawable[23] = 2;
            } else if (date_split[2].equals("end")) {
                modes_for_four_drawable[23] = 3;
            } else if (date_split[2].equals("beg_last")) {
                modes_for_four_drawable[23] = 4;
            }
            if (date_split[3].equals("empty")) {
                modes_for_four_drawable[24] = 0;
            } else if (date_split[3].equals("start")) {
                modes_for_four_drawable[24] = 1;
            } else if (date_split[3].equals("middle")) {
                modes_for_four_drawable[24] = 2;
            } else if (date_split[3].equals("end")) {
                modes_for_four_drawable[24] = 3;
            } else if (date_split[3].equals("beg_last")) {
                modes_for_four_drawable[24] = 4;
            }
            if (date_split[4].equals("empty")) {
                modes_for_four_drawable[25] = 0;
            } else if (date_split[4].equals("start")) {
                modes_for_four_drawable[25] = 1;
            } else if (date_split[4].equals("middle")) {
                modes_for_four_drawable[25] = 2;
            } else if (date_split[4].equals("end")) {
                modes_for_four_drawable[25] = 3;
            } else if (date_split[4].equals("beg_last")) {
                modes_for_four_drawable[25] = 4;
            }
            if (date_split[5].equals("empty")) {
                modes_for_four_drawable[26] = 0;
            } else if (date_split[5].equals("start")) {
                modes_for_four_drawable[26] = 1;
            } else if (date_split[5].equals("middle")) {
                modes_for_four_drawable[26] = 2;
            } else if (date_split[5].equals("end")) {
                modes_for_four_drawable[26] = 3;
            } else if (date_split[5].equals("beg_last")) {
                modes_for_four_drawable[26] = 4;
            }
            if (date_split[6].equals("empty")) {
                modes_for_four_drawable[27] = 0;
            } else if (date_split[6].equals("start")) {
                modes_for_four_drawable[27] = 1;
            } else if (date_split[6].equals("middle")) {
                modes_for_four_drawable[27] = 2;
            } else if (date_split[6].equals("end")) {
                modes_for_four_drawable[27] = 3;
            } else if (date_split[6].equals("beg_last")) {
                modes_for_four_drawable[27] = 4;
            }
        }
    }

    private void set_the_fifth_line(String date) {
        if (getView() != null) {
            String[] date_split = date.split("split");
            if (date_split.length > 0) {
                if (date_split[0].equals("empty")) {
                    modes_for_four_drawable[28] = 0;
                } else if (date_split[0].equals("start")) {
                    modes_for_four_drawable[28] = 1;
                } else if (date_split[0].equals("middle")) {
                    modes_for_four_drawable[28] = 2;
                } else if (date_split[0].equals("end")) {
                    modes_for_four_drawable[28] = 3;
                } else if (date_split[0].equals("beg_last")) {
                    modes_for_four_drawable[28] = 4;
                }
            } else {
                modes_for_four_drawable[28] = 0;
            }
            if (date_split.length > 1) {
                if (date_split[1].equals("empty")) {
                    modes_for_four_drawable[29] = 0;
                } else if (date_split[1].equals("start")) {
                    modes_for_four_drawable[29] = 1;
                } else if (date_split[1].equals("middle")) {
                    modes_for_four_drawable[29] = 2;
                } else if (date_split[1].equals("end")) {
                    modes_for_four_drawable[29] = 3;
                } else if (date_split[1].equals("beg_last")) {
                    modes_for_four_drawable[29] = 4;
                }
            } else {
                modes_for_four_drawable[29] = 0;
            }
            if (date_split.length > 2) {
                if (date_split[2].equals("empty")) {
                    modes_for_four_drawable[30] = 0;
                } else if (date_split[2].equals("start")) {
                    modes_for_four_drawable[30] = 1;
                } else if (date_split[2].equals("middle")) {
                    modes_for_four_drawable[30] = 2;
                } else if (date_split[2].equals("end")) {
                    modes_for_four_drawable[30] = 3;
                } else if (date_split[2].equals("beg_last")) {
                    modes_for_four_drawable[30] = 4;
                }
            } else {
                modes_for_four_drawable[30] = 0;
            }
            if (date_split.length > 3) {
                if (date_split[3].equals("empty")) {
                    modes_for_four_drawable[31] = 0;
                } else if (date_split[3].equals("start")) {
                    modes_for_four_drawable[31] = 1;
                } else if (date_split[3].equals("middle")) {
                    modes_for_four_drawable[31] = 2;
                } else if (date_split[3].equals("end")) {
                    modes_for_four_drawable[31] = 3;
                } else if (date_split[3].equals("beg_last")) {
                    modes_for_four_drawable[31] = 4;
                }
            } else {
                modes_for_four_drawable[31] = 0;
            }
            if (date_split.length > 4) {
                if (date_split[4].equals("empty")) {
                    modes_for_four_drawable[32] = 0;
                } else if (date_split[4].equals("start")) {
                    modes_for_four_drawable[32] = 1;
                } else if (date_split[4].equals("middle")) {
                    modes_for_four_drawable[32] = 2;
                } else if (date_split[4].equals("end")) {
                    modes_for_four_drawable[32] = 3;
                } else if (date_split[4].equals("beg_last")) {
                    modes_for_four_drawable[32] = 4;
                }
            } else {
                modes_for_four_drawable[32] = 0;
            }
            if (date_split.length > 5) {
                if (date_split[5].equals("empty")) {
                    modes_for_four_drawable[33] = 0;
                } else if (date_split[5].equals("start")) {
                    modes_for_four_drawable[33] = 1;
                } else if (date_split[5].equals("middle")) {
                    modes_for_four_drawable[33] = 2;
                } else if (date_split[5].equals("end")) {
                    modes_for_four_drawable[33] = 3;
                } else if (date_split[5].equals("beg_last")) {
                    modes_for_four_drawable[33] = 4;
                }
            } else {
                modes_for_four_drawable[33] = 0;
            }
            if (date_split.length > 6) {
                if (date_split[6].equals("empty")) {
                    modes_for_four_drawable[34] = 0;
                } else if (date_split[6].equals("start")) {
                    modes_for_four_drawable[34] = 1;
                } else if (date_split[6].equals("middle")) {
                    modes_for_four_drawable[34] = 2;
                } else if (date_split[6].equals("end")) {
                    modes_for_four_drawable[34] = 3;
                } else if (date_split[6].equals("beg_last")) {
                    modes_for_four_drawable[34] = 4;
                }
            } else {
                modes_for_four_drawable[34] = 0;
            }
        }
    }

    private void set_the_sixth_line(String date) {
        if (getView() != null) {
            String[] date_split = date.split("split");
            if (date_split.length > 0) {
                if (date_split[0].equals("empty")) {
                    modes_for_four_drawable[35] = 0;
                } else if (date_split[0].equals("start")) {
                    modes_for_four_drawable[35] = 1;
                } else if (date_split[0].equals("middle")) {
                    modes_for_four_drawable[35] = 2;
                } else if (date_split[0].equals("end")) {
                    modes_for_four_drawable[35] = 3;
                } else if (date_split[0].equals("beg_last")) {
                    modes_for_four_drawable[35] = 4;
                }
            } else {
                modes_for_four_drawable[35] = 0;
            }
            if (date_split.length > 1) {
                if (date_split[1].equals("empty")) {
                    modes_for_four_drawable[36] = 0;
                } else if (date_split[1].equals("start")) {
                    modes_for_four_drawable[36] = 1;
                } else if (date_split[1].equals("middle")) {
                    modes_for_four_drawable[36] = 2;
                } else if (date_split[1].equals("end")) {
                    modes_for_four_drawable[36] = 3;
                } else if (date_split[1].equals("beg_last")) {
                    modes_for_four_drawable[36] = 4;
                }
            } else {
                modes_for_four_drawable[36] = 0;
            }
        }
    }

    private String return_the_state_of_the_days() {
        if (getView() != null) {
            String month_info = "";
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            Calendar calendar = Calendar.getInstance();
            Calendar real_calender = Calendar.getInstance();
            calendar.set(Calendar.MONTH, return_month_string_to_int(splitter[0]));
            calendar.set(Calendar.YEAR, Integer.parseInt(splitter[1]));
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            if (!check_past_now_or_future().equals("future")) {
                if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) < Simplify_the_time.return_time_in_midnight(start_date)) {
                    month_info = "empty".concat("split");
                } else {
                    if (return_the_last_day_of_last_month().equals("continue")) {
                        if (return_state_of_day(calendar.getTimeInMillis())) {
                            month_info = "middle".concat("split");
                        } else {
                            month_info = "end".concat("split");
                        }
                    } else {
                        if (return_state_of_day(calendar.getTimeInMillis())) {
                            month_info = "start".concat("split");
                        } else {
                            month_info = "beg_last".concat("split");
                        }
                    }
                }
                if (check_past_now_or_future().equals("current")) {
                    for (int i = 2; i <= return_last_day_of_month(); i++) {
                        calendar.set(Calendar.DAY_OF_MONTH, i);
                        if (real_calender.get(Calendar.DAY_OF_MONTH) < i || Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) < Simplify_the_time.return_time_in_midnight(start_date)) {
                            month_info = month_info.concat("empty").concat("split");
                        } else {
                            String substring = month_info.substring(month_info.length() - 7, month_info.length() - 5);
                            if (substring.equals("rt") || substring.equals("le")) {
                                if (return_state_of_day(calendar.getTimeInMillis())) {
                                    month_info = month_info.concat("middle").concat("split");
                                } else {
                                    month_info = month_info.concat("end").concat("split");
                                }
                            } else {
                                if (return_state_of_day(calendar.getTimeInMillis())) {
                                    month_info = month_info.concat("start").concat("split");
                                } else {
                                    month_info = month_info.concat("beg_last").concat("split");
                                }
                            }
                        }
                    }
                } else {
                    for (int i = 2; i <= return_last_day_of_month(); i++) {
                        calendar.set(Calendar.DAY_OF_MONTH, i);
                        if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) < Simplify_the_time.return_time_in_midnight(start_date)) {
                            month_info = month_info.concat("empty").concat("split");
                        } else {
                            String substring = month_info.substring(month_info.length() - 7, month_info.length() - 5);
                            if (substring.equals("rt") || substring.equals("le")) {
                                if (return_state_of_day(calendar.getTimeInMillis())) {
                                    month_info = month_info.concat("middle").concat("split");
                                } else {
                                    month_info = month_info.concat("end").concat("split");
                                }
                            } else {
                                if (return_state_of_day(calendar.getTimeInMillis())) {
                                    month_info = month_info.concat("start").concat("split");
                                } else {
                                    month_info = month_info.concat("beg_last").concat("split");
                                }
                            }
                        }
                    }
                }
            } else {
                for (int i = 1; i <= 31; i++) {
                    month_info = month_info.concat("empty").concat("split");
                }
            }
            return month_info;
        } else {
            return "";
        }
    }

    private boolean return_state_of_day(long milli) {
        milli = Simplify_the_time.return_time_in_midnight(milli);
        if (history_of_mood.containsKey(milli)) {
            return true;
        } else {
            return false;
        }
    }

    private void add_to_list() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mood", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString("mood_stats", "");
            history_of_mood = new HashMap<>();
            if (shared != null && !shared.equals("")) {
                String[] split = shared.split("max_divide");
                for (int i = 0; i < split.length; i++) {
                    String[] small_split = split[i].split("small_split");
                    history_of_mood.put(Long.parseLong(small_split[1]), Integer.parseInt(small_split[0]));
                }
            }
        }
    }

    private String return_the_last_day_of_last_month() {
        Calendar calendar = Calendar.getInstance();
        TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
        String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
        int month = return_month_string_to_int(splitter[0]);
        int year = Integer.parseInt(splitter[1]);
        if (month == 0) {
            month = 11;
            year--;
        } else {
            month--;
        }
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        if (Simplify_the_time.return_time_in_midnight(start_date) <= Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis())) {
            if (return_state_of_day(calendar.getTimeInMillis())) {
                return "continue";
            } else {
                return "start";
            }
        } else {
            return "start";
        }
    }

    private void get_the_color() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mood", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString("mood_color", "");
            if (shared == null || shared.equals("")) {
                color = Color.parseColor("#000075");
            } else {
                color = Color.parseColor(shared);
            }

        }
    }

    private void save_the_input_for_good_habit_input(int mood, long milli) {
        if (getActivity() != null) {
            milli = Simplify_the_time.return_time_in_midnight(milli);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mood", Context.MODE_PRIVATE);
            if (mood == 0) {
                history_of_mood.remove(milli);
            } else {
                if (history_of_mood.containsKey(milli)) {
                    history_of_mood.remove(milli);
                    history_of_mood.put(milli, mood);
                } else {
                    history_of_mood.put(milli, mood);
                }
            }
            String save_me = "";
            for (Map.Entry<Long, Integer> map : history_of_mood.entrySet()) {
                save_me = save_me.concat(String.valueOf(map.getValue())).concat("small_split").concat(String.valueOf(map.getKey())).concat("max_divide");
            }
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("mood_stats", save_me);
            myEdit.commit();
        }
    }

    private void selct_the_mood_in_calender(int which) {
        if (getView() != null) {
            View very_bad_mood_shade_in_habits = getView().findViewById(R.id.very_bad_mood_shade_in_habits);
            View bad_mood_shade_in_habits = getView().findViewById(R.id.bad_mood_shade_in_habits);
            View ok_mood_shade_in_habits = getView().findViewById(R.id.ok_mood_shade_in_habits);
            View good_mood_shade_in_habits = getView().findViewById(R.id.good_mood_shade_in_habits);
            View very_good_mood_shade_in_habits = getView().findViewById(R.id.very_good_mood_shade_in_habits);
            View very_bad_mood_check_mark_in_mood = getView().findViewById(R.id.very_bad_mood_check_mark_in_mood);
            View bad_mood_check_mark_in_mood = getView().findViewById(R.id.bad_mood_check_mark_in_mood);
            View ok_mood_check_mark_in_mood = getView().findViewById(R.id.ok_mood_check_mark_in_mood);
            View good_mood_check_mark_in_mood = getView().findViewById(R.id.good_mood_check_mark_in_mood);
            View very_good_mood_check_mark_in_mood = getView().findViewById(R.id.very_good_mood_check_mark_in_mood);
            if (which == 0) {
                very_bad_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                bad_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                ok_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                good_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                very_good_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                very_bad_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                bad_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                ok_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                good_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                very_good_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
            } else if (which == 1) {
                very_bad_mood_shade_in_habits.setVisibility(View.VISIBLE);
                bad_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                ok_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                good_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                very_good_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                very_bad_mood_check_mark_in_mood.setVisibility(View.VISIBLE);
                bad_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                ok_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                good_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                very_good_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
            } else if (which == 2) {
                very_bad_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                bad_mood_shade_in_habits.setVisibility(View.VISIBLE);
                ok_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                good_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                very_good_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                very_bad_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                bad_mood_check_mark_in_mood.setVisibility(View.VISIBLE);
                ok_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                good_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                very_good_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
            } else if (which == 3) {
                very_bad_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                bad_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                ok_mood_shade_in_habits.setVisibility(View.VISIBLE);
                good_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                very_good_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                very_bad_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                bad_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                ok_mood_check_mark_in_mood.setVisibility(View.VISIBLE);
                good_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                very_good_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
            } else if (which == 4) {
                very_bad_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                bad_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                ok_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                good_mood_shade_in_habits.setVisibility(View.VISIBLE);
                very_good_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                very_bad_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                bad_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                ok_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                good_mood_check_mark_in_mood.setVisibility(View.VISIBLE);
                very_good_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
            } else if (which == 5) {
                very_bad_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                bad_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                ok_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                good_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                very_good_mood_shade_in_habits.setVisibility(View.VISIBLE);
                very_bad_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                bad_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                ok_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                good_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                very_good_mood_check_mark_in_mood.setVisibility(View.VISIBLE);
            }
        }
    }

    private void define_start_date() {
        /*if (history_of_mood.isEmpty()) {
            start_date = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis());
        } else {
            start_date = Collections.min(history_of_mood.keySet());
        }*/
        start_date = return_start_date();
    }

    private String return_the_color_of_mood(int which) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mood", Context.MODE_PRIVATE);
        String very_bad = sharedPreferences.getString("very_bad", "");
        String bad = sharedPreferences.getString("bad", "");
        String ok = sharedPreferences.getString("ok", "");
        String good = sharedPreferences.getString("good", "");
        String very_good = sharedPreferences.getString("very_good", "");
        String not_selected = sharedPreferences.getString("not_selected", "");
        if (which == 0) {
            if (not_selected == null || not_selected.equals("")) {
                return "#000000";
            } else {
                return not_selected;
            }
        }
        if (which == 1) {
            if (very_bad == null || very_bad.equals("")) {
                return "#ffff4444";
            } else {
                return very_bad;
            }
        } else if (which == 2) {
            if (bad == null || bad.equals("")) {
                return "#FFA500";
            } else {
                return bad;
            }
        } else if (which == 3) {
            if (ok == null || ok.equals("")) {
                return "#800080";
            } else {
                return ok;
            }
        } else if (which == 4) {
            if (good == null || good.equals("")) {
                return "#008b8b";
            } else {
                return good;
            }
        } else if (which == 5) {
            if (very_good == null || very_good.equals("")) {
                return "#32CD32";
            } else {
                return very_good;
            }
        }
        return "#32CD32";
    }

    private int return_color_of_days(long milli) {
        milli = Simplify_the_time.return_time_in_midnight(milli);
        if (history_of_mood.containsKey(milli)) {
            return history_of_mood.get(milli);
        } else {
            return 0;
        }
    }

    private void color_the_stuff() {
        if (getView() != null) {
            View view_at_the_top_action_bar_habits = getView().findViewById(R.id.view_at_the_top_action_bar_habits);
            View view_under_top_action_bar_half_color = getView().findViewById(R.id.view_under_top_action_bar_half_color);
            View back_button_in_view_information_good_habits = getView().findViewById(R.id.back_button_in_view_information_good_habits);
            View three_dots_button_in_top_in_habits = getView().findViewById(R.id.three_dots_button_in_top_in_habits);
            ConstraintLayout layout_inside_scroll_in_the_bad_habits = getView().findViewById(R.id.layout_inside_scroll_in_the_bad_habits);
            Button button_too_share_calender_in_good_habits = getView().findViewById(R.id.button_too_share_calender_in_good_habits);
            Button button_to_share_the_best_average_and_current_streak = getView().findViewById(R.id.button_to_share_the_best_average_and_current_streak);
            Button button_to_share_bar_chart = getView().findViewById(R.id.button_to_share_bar_chart);
            Button button_to_share_pie_chart = getView().findViewById(R.id.button_to_share_pie_chart);
            Button share_button_to_share_the_whole_year_in_the_good_habits = getView().findViewById(R.id.share_button_to_share_the_whole_year_in_the_good_habits);
            TextView text_asking_did_you_relapse_in_share = getView().findViewById(R.id.text_asking_did_you_relapse_in_share);
            Button very_bad_mood_button_in_habits_in_the_top_today = getView().findViewById(R.id.very_bad_mood_button_in_habits_in_the_top_today);
            Button bad_mood_button_in_habits_in_the_top_today = getView().findViewById(R.id.bad_mood_button_in_habits_in_the_top_today);
            Button ok_mood_button_in_habits_in_the_top_today = getView().findViewById(R.id.ok_mood_button_in_habits_in_the_top_today);
            Button good_mood_button_in_habits_in_the_top_today = getView().findViewById(R.id.good_mood_button_in_habits_in_the_top_today);
            Button very_good_mood_button_in_habits_in_the_top_today = getView().findViewById(R.id.very_good_mood_button_in_habits_in_the_top_today);
            ProgressBar progress_bar_number_one_mood = getView().findViewById(R.id.progress_bar_number_one_mood);
            ProgressBar progress_bar_number_two_mood = getView().findViewById(R.id.progress_bar_number_two_mood);
            ProgressBar progress_bar_number_three_mood = getView().findViewById(R.id.progress_bar_number_three_mood);
            ProgressBar progress_bar_number_four_mood = getView().findViewById(R.id.progress_bar_number_four_mood);
            ProgressBar progress_bar_number_five_mood = getView().findViewById(R.id.progress_bar_number_five_mood);
            Button button_to_share_mood_graph = getView().findViewById(R.id.button_to_share_mood_graph);
            View three_dots_view_in_mood_bar_to_change_week_month = getView().findViewById(R.id.three_dots_view_in_mood_bar_to_change_week_month);
            View legend_very_happy_mood_tracker = getView().findViewById(R.id.legend_very_happy_mood_tracker);
            View legend_happy_mood_tracker = getView().findViewById(R.id.legend_happy_mood_tracker);
            View legend_ok_mood_tracker = getView().findViewById(R.id.legend_ok_mood_tracker);
            View legend_bad_mood_tracker = getView().findViewById(R.id.legend_bad_mood_tracker);
            View legend_very_bad_mood_tracker = getView().findViewById(R.id.legend_very_bad_mood_tracker);
            Button this_streak_chart_is_only_available_for_pro_users_pie_chart_mood = getView().findViewById(R.id.this_streak_chart_is_only_available_for_pro_users_pie_chart_mood);
            Button this_streak_chart_is_only_available_for_pro_users_weekly_mood_average_chart_mood = getView().findViewById(R.id.this_streak_chart_is_only_available_for_pro_users_weekly_mood_average_chart_mood);
            Button this_streak_chart_is_only_available_for_pro_users_in_a_year_chart_mood = getView().findViewById(R.id.this_streak_chart_is_only_available_for_pro_users_in_a_year_chart_mood);
            view_at_the_top_action_bar_habits.setBackgroundColor(color);
            view_under_top_action_bar_half_color.setBackgroundColor(ColorUtils.blendARGB(color, Color.WHITE, 0.5F));
            Drawable drawable_for_buttons_two = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
            drawable_for_buttons_two = DrawableCompat.wrap(drawable_for_buttons_two);
            DrawableCompat.setTint(drawable_for_buttons_two, color);
            back_button_in_view_information_good_habits.setBackground(drawable_for_buttons_two);
            Drawable drawable_for_buttons_three = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
            drawable_for_buttons_three = DrawableCompat.wrap(drawable_for_buttons_three);
            DrawableCompat.setTint(drawable_for_buttons_three, color);
            three_dots_button_in_top_in_habits.setBackground(drawable_for_buttons_three);
            layout_inside_scroll_in_the_bad_habits.setBackgroundColor(ColorUtils.blendARGB(color, Color.WHITE, 0.9F));
            button_too_share_calender_in_good_habits.setTextColor(color);
            button_to_share_the_best_average_and_current_streak.setTextColor(color);
            button_to_share_bar_chart.setTextColor(color);
            button_to_share_pie_chart.setTextColor(color);
            share_button_to_share_the_whole_year_in_the_good_habits.setTextColor(color);
            text_asking_did_you_relapse_in_share.setTextColor(color);
            {
                Drawable drawable_for_buttons_four = ContextCompat.getDrawable(getContext(), R.drawable.ripple_for_all_circle).mutate();
                drawable_for_buttons_four = DrawableCompat.wrap(drawable_for_buttons_four);
                DrawableCompat.setTint(drawable_for_buttons_four, ColorUtils.blendARGB(color, Color.WHITE, 0.9F));
                very_bad_mood_button_in_habits_in_the_top_today.setBackground(drawable_for_buttons_four);
            }
            {
                Drawable drawable_for_buttons_four = ContextCompat.getDrawable(getContext(), R.drawable.ripple_for_all_circle).mutate();
                drawable_for_buttons_four = DrawableCompat.wrap(drawable_for_buttons_four);
                DrawableCompat.setTint(drawable_for_buttons_four, ColorUtils.blendARGB(color, Color.WHITE, 0.9F));
                bad_mood_button_in_habits_in_the_top_today.setBackground(drawable_for_buttons_four);
            }
            {
                Drawable drawable_for_buttons_four = ContextCompat.getDrawable(getContext(), R.drawable.ripple_for_all_circle).mutate();
                drawable_for_buttons_four = DrawableCompat.wrap(drawable_for_buttons_four);
                DrawableCompat.setTint(drawable_for_buttons_four, ColorUtils.blendARGB(color, Color.WHITE, 0.9F));
                ok_mood_button_in_habits_in_the_top_today.setBackground(drawable_for_buttons_four);
            }
            {
                Drawable drawable_for_buttons_four = ContextCompat.getDrawable(getContext(), R.drawable.ripple_for_all_circle).mutate();
                drawable_for_buttons_four = DrawableCompat.wrap(drawable_for_buttons_four);
                DrawableCompat.setTint(drawable_for_buttons_four, ColorUtils.blendARGB(color, Color.WHITE, 0.9F));
                good_mood_button_in_habits_in_the_top_today.setBackground(drawable_for_buttons_four);
            }
            {
                Drawable drawable_for_buttons_four = ContextCompat.getDrawable(getContext(), R.drawable.ripple_for_all_circle).mutate();
                drawable_for_buttons_four = DrawableCompat.wrap(drawable_for_buttons_four);
                DrawableCompat.setTint(drawable_for_buttons_four, ColorUtils.blendARGB(color, Color.WHITE, 0.9F));
                very_good_mood_button_in_habits_in_the_top_today.setBackground(drawable_for_buttons_four);
            }
            String very_bad_color = return_the_color_of_mood(1);
            String bad_color = return_the_color_of_mood(2);
            String ok_color = return_the_color_of_mood(3);
            String good_color = return_the_color_of_mood(4);
            String very_good_color = return_the_color_of_mood(5);
            {
                Drawable background = progress_bar_number_one_mood.getProgressDrawable().mutate();
                background.setTint(Color.parseColor(very_bad_color));
            }
            {
                Drawable background = progress_bar_number_two_mood.getProgressDrawable().mutate();
                background.setTint(Color.parseColor(bad_color));
            }
            {
                Drawable background = progress_bar_number_three_mood.getProgressDrawable().mutate();
                background.setTint(Color.parseColor(ok_color));
            }
            {
                Drawable background = progress_bar_number_four_mood.getProgressDrawable().mutate();
                background.setTint(Color.parseColor(good_color));
            }
            {
                Drawable background = progress_bar_number_five_mood.getProgressDrawable().mutate();
                background.setTint(Color.parseColor(very_good_color));
            }
            button_to_share_mood_graph.setTextColor(color);
            {
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_more_vert_24).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, color);
                three_dots_view_in_mood_bar_to_change_week_month.setBackground(wrappedDrawable);
            }
            {
                Drawable back_ground = ContextCompat.getDrawable(getContext(), R.drawable.mood_tracker_circle_under_pie).mutate();
                back_ground.setTint(Color.parseColor(very_good_color));
                legend_very_happy_mood_tracker.setBackground(back_ground);
            }
            {
                Drawable back_ground = ContextCompat.getDrawable(getContext(), R.drawable.mood_tracker_circle_under_pie).mutate();
                back_ground.setTint(Color.parseColor(good_color));
                legend_happy_mood_tracker.setBackground(back_ground);
            }
            {
                Drawable back_ground = ContextCompat.getDrawable(getContext(), R.drawable.mood_tracker_circle_under_pie).mutate();
                back_ground.setTint(Color.parseColor(ok_color));
                legend_ok_mood_tracker.setBackground(back_ground);
            }
            {
                Drawable back_ground = ContextCompat.getDrawable(getContext(), R.drawable.mood_tracker_circle_under_pie).mutate();
                back_ground.setTint(Color.parseColor(bad_color));
                legend_bad_mood_tracker.setBackground(back_ground);
            }
            {
                Drawable back_ground = ContextCompat.getDrawable(getContext(), R.drawable.mood_tracker_circle_under_pie).mutate();
                back_ground.setTint(Color.parseColor(very_bad_color));
                legend_very_bad_mood_tracker.setBackground(back_ground);
            }
            this_streak_chart_is_only_available_for_pro_users_pie_chart_mood.setBackgroundTintList(ColorStateList.valueOf(color));
            this_streak_chart_is_only_available_for_pro_users_weekly_mood_average_chart_mood.setBackgroundTintList(ColorStateList.valueOf(color));
            this_streak_chart_is_only_available_for_pro_users_in_a_year_chart_mood.setBackgroundTintList(ColorStateList.valueOf(color));
        }
    }

    private void color_the_under() {
        if (getView() != null) {
            int month_start_day;
            if (calender_button_showing_shadow_1.getVisibility() == View.VISIBLE) {
                month_start_day = 0;
            } else if (calender_button_showing_shadow_2.getVisibility() == View.VISIBLE) {
                month_start_day = 1;
            } else if (calender_button_showing_shadow_3.getVisibility() == View.VISIBLE) {
                month_start_day = 2;
            } else if (calender_button_showing_shadow_4.getVisibility() == View.VISIBLE) {
                month_start_day = 3;
            } else if (calender_button_showing_shadow_5.getVisibility() == View.VISIBLE) {
                month_start_day = 4;
            } else if (calender_button_showing_shadow_6.getVisibility() == View.VISIBLE) {
                month_start_day = 5;
            } else {
                month_start_day = 6;
            }
            Drawable local_first_part_rectangle_calender;
            Drawable local_last_part_rectangle_calender;
            Drawable local_first_and_last_part_rectangle_calender;
            Drawable local_middle_part_rectangle_calender;
            int color_counter = 1;
            for (int i = month_start_day; i < (return_last_day_of_month() + month_start_day); i++) {
                int color = Color.parseColor(colors[color_counter]);
                LayerDrawable layerDrawable = (LayerDrawable) ContextCompat.getDrawable(getContext(), R.drawable.first_part_rectangle_calender);
                if (layerDrawable != null) {
                    Drawable drawable1 = layerDrawable.getDrawable(1);
                    drawable1.setTint(color);
                }
                local_middle_part_rectangle_calender = ContextCompat.getDrawable(getContext(), R.drawable.middle_part_drawable_color);
                if (local_middle_part_rectangle_calender != null) {
                    local_middle_part_rectangle_calender.setTint(color);
                }
                LayerDrawable layerDrawable3 = (LayerDrawable) ContextCompat.getDrawable(getContext(), R.drawable.last_part_rectangle_calender);
                if (layerDrawable3 != null) {
                    Drawable drawable1 = layerDrawable3.getDrawable(1);
                    drawable1.setTint(color);
                }
                LayerDrawable layerDrawable4 = (LayerDrawable) ContextCompat.getDrawable(getContext(), R.drawable.first_and_last_part_rectangle_calender);
                if (layerDrawable4 != null) {
                    Drawable drawable1 = layerDrawable4.getDrawable(1);
                    drawable1.setTint(color);
                }
                local_first_part_rectangle_calender = ContextCompat.getDrawable(getContext(), R.drawable.first_part_rectangle_calender);
                local_last_part_rectangle_calender = ContextCompat.getDrawable(getContext(), R.drawable.last_part_rectangle_calender);
                local_first_and_last_part_rectangle_calender = ContextCompat.getDrawable(getContext(), R.drawable.first_and_last_part_rectangle_calender);
                if (modes_for_four_drawable[i] == 0) {
                    list_of_all_unders[i].setVisibility(View.INVISIBLE);
                } else if (modes_for_four_drawable[i] == 1) {
                    list_of_all_unders[i].setVisibility(View.VISIBLE);
                    list_of_all_unders[i].setBackground(local_first_part_rectangle_calender);
                } else if (modes_for_four_drawable[i] == 2) {
                    list_of_all_unders[i].setVisibility(View.VISIBLE);
                    list_of_all_unders[i].setBackground(local_middle_part_rectangle_calender);
                } else if (modes_for_four_drawable[i] == 3) {
                    list_of_all_unders[i].setVisibility(View.VISIBLE);
                    list_of_all_unders[i].setBackground(local_last_part_rectangle_calender);
                } else if (modes_for_four_drawable[i] == 4) {
                    list_of_all_unders[i].setVisibility(View.VISIBLE);
                    list_of_all_unders[i].setBackground(local_first_and_last_part_rectangle_calender);
                }
                color_counter++;
            }
        }
    }

    private void add_all_to_a_list() {
        View calender_under_number_state_1 = getView().findViewById(R.id.calender_under_number_state_1);
        View calender_under_number_state_2 = getView().findViewById(R.id.calender_under_number_state_2);
        View calender_under_number_state_3 = getView().findViewById(R.id.calender_under_number_state_3);
        View calender_under_number_state_4 = getView().findViewById(R.id.calender_under_number_state_4);
        View calender_under_number_state_5 = getView().findViewById(R.id.calender_under_number_state_5);
        View calender_under_number_state_6 = getView().findViewById(R.id.calender_under_number_state_6);
        View calender_under_number_state_7 = getView().findViewById(R.id.calender_under_number_state_7);
        View calender_under_number_state_8 = getView().findViewById(R.id.calender_under_number_state_8);
        View calender_under_number_state_9 = getView().findViewById(R.id.calender_under_number_state_9);
        View calender_under_number_state_10 = getView().findViewById(R.id.calender_under_number_state_10);
        View calender_under_number_state_11 = getView().findViewById(R.id.calender_under_number_state_11);
        View calender_under_number_state_12 = getView().findViewById(R.id.calender_under_number_state_12);
        View calender_under_number_state_13 = getView().findViewById(R.id.calender_under_number_state_13);
        View calender_under_number_state_14 = getView().findViewById(R.id.calender_under_number_state_14);
        View calender_under_number_state_15 = getView().findViewById(R.id.calender_under_number_state_15);
        View calender_under_number_state_16 = getView().findViewById(R.id.calender_under_number_state_16);
        View calender_under_number_state_17 = getView().findViewById(R.id.calender_under_number_state_17);
        View calender_under_number_state_18 = getView().findViewById(R.id.calender_under_number_state_18);
        View calender_under_number_state_19 = getView().findViewById(R.id.calender_under_number_state_19);
        View calender_under_number_state_20 = getView().findViewById(R.id.calender_under_number_state_20);
        View calender_under_number_state_21 = getView().findViewById(R.id.calender_under_number_state_21);
        View calender_under_number_state_22 = getView().findViewById(R.id.calender_under_number_state_22);
        View calender_under_number_state_23 = getView().findViewById(R.id.calender_under_number_state_23);
        View calender_under_number_state_24 = getView().findViewById(R.id.calender_under_number_state_24);
        View calender_under_number_state_25 = getView().findViewById(R.id.calender_under_number_state_25);
        View calender_under_number_state_26 = getView().findViewById(R.id.calender_under_number_state_26);
        View calender_under_number_state_27 = getView().findViewById(R.id.calender_under_number_state_27);
        View calender_under_number_state_28 = getView().findViewById(R.id.calender_under_number_state_28);
        View calender_under_number_state_29 = getView().findViewById(R.id.calender_under_number_state_29);
        View calender_under_number_state_30 = getView().findViewById(R.id.calender_under_number_state_30);
        View calender_under_number_state_31 = getView().findViewById(R.id.calender_under_number_state_31);
        View calender_under_number_state_32 = getView().findViewById(R.id.calender_under_number_state_32);
        View calender_under_number_state_33 = getView().findViewById(R.id.calender_under_number_state_33);
        View calender_under_number_state_34 = getView().findViewById(R.id.calender_under_number_state_34);
        View calender_under_number_state_35 = getView().findViewById(R.id.calender_under_number_state_35);
        View calender_under_number_state_36 = getView().findViewById(R.id.calender_under_number_state_36);
        View calender_under_number_state_37 = getView().findViewById(R.id.calender_under_number_state_37);
        list_of_all_unders = new View[37];
        list_of_all_unders[0] = calender_under_number_state_1;
        list_of_all_unders[1] = calender_under_number_state_2;
        list_of_all_unders[2] = calender_under_number_state_3;
        list_of_all_unders[3] = calender_under_number_state_4;
        list_of_all_unders[4] = calender_under_number_state_5;
        list_of_all_unders[5] = calender_under_number_state_6;
        list_of_all_unders[6] = calender_under_number_state_7;
        list_of_all_unders[7] = calender_under_number_state_8;
        list_of_all_unders[8] = calender_under_number_state_9;
        list_of_all_unders[9] = calender_under_number_state_10;
        list_of_all_unders[10] = calender_under_number_state_11;
        list_of_all_unders[11] = calender_under_number_state_12;
        list_of_all_unders[12] = calender_under_number_state_13;
        list_of_all_unders[13] = calender_under_number_state_14;
        list_of_all_unders[14] = calender_under_number_state_15;
        list_of_all_unders[15] = calender_under_number_state_16;
        list_of_all_unders[16] = calender_under_number_state_17;
        list_of_all_unders[17] = calender_under_number_state_18;
        list_of_all_unders[18] = calender_under_number_state_19;
        list_of_all_unders[19] = calender_under_number_state_20;
        list_of_all_unders[20] = calender_under_number_state_21;
        list_of_all_unders[21] = calender_under_number_state_22;
        list_of_all_unders[22] = calender_under_number_state_23;
        list_of_all_unders[23] = calender_under_number_state_24;
        list_of_all_unders[24] = calender_under_number_state_25;
        list_of_all_unders[25] = calender_under_number_state_26;
        list_of_all_unders[26] = calender_under_number_state_27;
        list_of_all_unders[27] = calender_under_number_state_28;
        list_of_all_unders[28] = calender_under_number_state_29;
        list_of_all_unders[29] = calender_under_number_state_30;
        list_of_all_unders[30] = calender_under_number_state_31;
        list_of_all_unders[31] = calender_under_number_state_32;
        list_of_all_unders[32] = calender_under_number_state_33;
        list_of_all_unders[33] = calender_under_number_state_34;
        list_of_all_unders[34] = calender_under_number_state_35;
        list_of_all_unders[35] = calender_under_number_state_36;
        list_of_all_unders[36] = calender_under_number_state_37;
    }

    private void color_the_middle() {
        int month_start_day;
        if (calender_button_showing_shadow_1.getVisibility() == View.VISIBLE) {
            month_start_day = 0;
        } else if (calender_button_showing_shadow_2.getVisibility() == View.VISIBLE) {
            month_start_day = 1;
        } else if (calender_button_showing_shadow_3.getVisibility() == View.VISIBLE) {
            month_start_day = 2;
        } else if (calender_button_showing_shadow_4.getVisibility() == View.VISIBLE) {
            month_start_day = 3;
        } else if (calender_button_showing_shadow_5.getVisibility() == View.VISIBLE) {
            month_start_day = 4;
        } else if (calender_button_showing_shadow_6.getVisibility() == View.VISIBLE) {
            month_start_day = 5;
        } else {
            month_start_day = 6;
        }
        View middle_calender_1 = getView().findViewById(R.id.middle_calender_1);
        View middle_calender_2 = getView().findViewById(R.id.middle_calender_2);
        View middle_calender_3 = getView().findViewById(R.id.middle_calender_3);
        View middle_calender_4 = getView().findViewById(R.id.middle_calender_4);
        View middle_calender_5 = getView().findViewById(R.id.middle_calender_5);
        View middle_calender_6 = getView().findViewById(R.id.middle_calender_6);
        View middle_calender_7 = getView().findViewById(R.id.middle_calender_7);
        View middle_calender_8 = getView().findViewById(R.id.middle_calender_8);
        View middle_calender_9 = getView().findViewById(R.id.middle_calender_9);
        View middle_calender_10 = getView().findViewById(R.id.middle_calender_10);
        View middle_calender_11 = getView().findViewById(R.id.middle_calender_11);
        View middle_calender_12 = getView().findViewById(R.id.middle_calender_12);
        View middle_calender_13 = getView().findViewById(R.id.middle_calender_13);
        View middle_calender_14 = getView().findViewById(R.id.middle_calender_14);
        View middle_calender_15 = getView().findViewById(R.id.middle_calender_15);
        View middle_calender_16 = getView().findViewById(R.id.middle_calender_16);
        View middle_calender_17 = getView().findViewById(R.id.middle_calender_17);
        View middle_calender_18 = getView().findViewById(R.id.middle_calender_18);
        View middle_calender_19 = getView().findViewById(R.id.middle_calender_19);
        View middle_calender_20 = getView().findViewById(R.id.middle_calender_20);
        View middle_calender_21 = getView().findViewById(R.id.middle_calender_21);
        View middle_calender_22 = getView().findViewById(R.id.middle_calender_22);
        View middle_calender_23 = getView().findViewById(R.id.middle_calender_23);
        View middle_calender_24 = getView().findViewById(R.id.middle_calender_24);
        View middle_calender_25 = getView().findViewById(R.id.middle_calender_25);
        View middle_calender_26 = getView().findViewById(R.id.middle_calender_26);
        View middle_calender_27 = getView().findViewById(R.id.middle_calender_27);
        View middle_calender_28 = getView().findViewById(R.id.middle_calender_28);
        View middle_calender_29 = getView().findViewById(R.id.middle_calender_29);
        View middle_calender_30 = getView().findViewById(R.id.middle_calender_30);
        View middle_calender_31 = getView().findViewById(R.id.middle_calender_31);
        if (middle_calender_1.getVisibility() == View.VISIBLE) {
            middle_calender_1.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[1 - month_start_day]), Color.parseColor(colors[2 - month_start_day])));
        }
        if (middle_calender_2.getVisibility() == View.VISIBLE) {
            middle_calender_2.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[2 - month_start_day]), Color.parseColor(colors[3 - month_start_day])));
        }
        if (middle_calender_3.getVisibility() == View.VISIBLE) {
            middle_calender_3.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[3 - month_start_day]), Color.parseColor(colors[4 - month_start_day])));
        }
        if (middle_calender_4.getVisibility() == View.VISIBLE) {
            middle_calender_4.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[4 - month_start_day]), Color.parseColor(colors[5 - month_start_day])));
        }
        if (middle_calender_5.getVisibility() == View.VISIBLE) {
            middle_calender_5.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[5 - month_start_day]), Color.parseColor(colors[6 - month_start_day])));
        }
        if (middle_calender_6.getVisibility() == View.VISIBLE) {
            middle_calender_6.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[6 - month_start_day]), Color.parseColor(colors[7 - month_start_day])));
        }
        if (middle_calender_7.getVisibility() == View.VISIBLE) {
            middle_calender_7.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[8 - month_start_day]), Color.parseColor(colors[9 - month_start_day])));
        }
        if (middle_calender_8.getVisibility() == View.VISIBLE) {
            middle_calender_8.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[9 - month_start_day]), Color.parseColor(colors[10 - month_start_day])));
        }
        if (middle_calender_9.getVisibility() == View.VISIBLE) {
            middle_calender_9.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[10 - month_start_day]), Color.parseColor(colors[11 - month_start_day])));
        }
        if (middle_calender_10.getVisibility() == View.VISIBLE) {
            middle_calender_10.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[11 - month_start_day]), Color.parseColor(colors[12 - month_start_day])));
        }
        if (middle_calender_11.getVisibility() == View.VISIBLE) {
            middle_calender_11.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[12 - month_start_day]), Color.parseColor(colors[13 - month_start_day])));
        }
        if (middle_calender_12.getVisibility() == View.VISIBLE) {
            middle_calender_12.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[13 - month_start_day]), Color.parseColor(colors[14 - month_start_day])));
        }
        if (middle_calender_13.getVisibility() == View.VISIBLE) {
            middle_calender_13.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[15 - month_start_day]), Color.parseColor(colors[16 - month_start_day])));
        }
        if (middle_calender_14.getVisibility() == View.VISIBLE) {
            middle_calender_14.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[16 - month_start_day]), Color.parseColor(colors[17 - month_start_day])));
        }
        if (middle_calender_15.getVisibility() == View.VISIBLE) {
            middle_calender_15.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[17 - month_start_day]), Color.parseColor(colors[18 - month_start_day])));
        }
        if (middle_calender_16.getVisibility() == View.VISIBLE) {
            middle_calender_16.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[18 - month_start_day]), Color.parseColor(colors[19 - month_start_day])));
        }
        if (middle_calender_17.getVisibility() == View.VISIBLE) {
            middle_calender_17.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[19 - month_start_day]), Color.parseColor(colors[20 - month_start_day])));
        }
        if (middle_calender_18.getVisibility() == View.VISIBLE) {
            middle_calender_18.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[20 - month_start_day]), Color.parseColor(colors[21 - month_start_day])));
        }
        if (middle_calender_19.getVisibility() == View.VISIBLE) {
            middle_calender_19.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[22 - month_start_day]), Color.parseColor(colors[23 - month_start_day])));
        }
        if (middle_calender_20.getVisibility() == View.VISIBLE) {
            middle_calender_20.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[23 - month_start_day]), Color.parseColor(colors[24 - month_start_day])));
        }
        if (middle_calender_21.getVisibility() == View.VISIBLE) {
            middle_calender_21.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[24 - month_start_day]), Color.parseColor(colors[25 - month_start_day])));
        }
        if (middle_calender_22.getVisibility() == View.VISIBLE) {
            middle_calender_22.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[25 - month_start_day]), Color.parseColor(colors[26 - month_start_day])));
        }
        if (middle_calender_23.getVisibility() == View.VISIBLE) {
            middle_calender_23.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[26 - month_start_day]), Color.parseColor(colors[27 - month_start_day])));
        }
        if (middle_calender_24.getVisibility() == View.VISIBLE) {
            middle_calender_24.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[27 - month_start_day]), Color.parseColor(colors[28 - month_start_day])));
        }
        if (middle_calender_25.getVisibility() == View.VISIBLE) {
            middle_calender_25.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[29 - month_start_day]), Color.parseColor(colors[30 - month_start_day])));
        }
        if (middle_calender_26.getVisibility() == View.VISIBLE) {
            middle_calender_26.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[30 - month_start_day]), Color.parseColor(colors[31 - month_start_day])));
        }
        if (middle_calender_27.getVisibility() == View.VISIBLE) {
            middle_calender_27.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[31 - month_start_day]), Color.parseColor(colors[32 - month_start_day])));
        }
        if (middle_calender_28.getVisibility() == View.VISIBLE) {
            middle_calender_28.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[32 - month_start_day]), Color.parseColor(colors[33 - month_start_day])));
        }
        if (middle_calender_29.getVisibility() == View.VISIBLE) {
            middle_calender_29.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[33 - month_start_day]), Color.parseColor(colors[34 - month_start_day])));
        }
        if (middle_calender_30.getVisibility() == View.VISIBLE) {
            middle_calender_30.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[34 - month_start_day]), Color.parseColor(colors[35 - month_start_day])));
        }
        if (middle_calender_31.getVisibility() == View.VISIBLE) {
            middle_calender_31.setBackground(return_drawable_for_middle_mood(Color.parseColor(colors[36 - month_start_day]), Color.parseColor(colors[37 - month_start_day])));
        }
    }

    private Drawable return_drawable_for_middle_mood(int first_color, int second_color) {
        Drawable middle_part;
        LayerDrawable layerDrawable = (LayerDrawable) ContextCompat.getDrawable(getContext(), R.drawable.middle_part_for_mood);
        Drawable drawable1 = layerDrawable.getDrawable(0);
        Drawable drawable2 = layerDrawable.getDrawable(1);
        drawable1.setTint(first_color);
        drawable2.setTint(second_color);
        middle_part = ContextCompat.getDrawable(getContext(), R.drawable.middle_part_for_mood);
        return middle_part;
    }

    private void make_the_buttons_in_the_top_mood(int result) {
        if (getView() != null) {
            View very_bad_mood_shade_in_habits_in_the_top_today = getView().findViewById(R.id.very_bad_mood_shade_in_habits_in_the_top_today);
            View bad_mood_shade_in_habits_in_the_top_today = getView().findViewById(R.id.bad_mood_shade_in_habits_in_the_top_today);
            View ok_mood_shade_in_habits_in_the_top_today = getView().findViewById(R.id.ok_mood_shade_in_habits_in_the_top_today);
            View good_mood_shade_in_habits_in_the_top_today = getView().findViewById(R.id.good_mood_shade_in_habits_in_the_top_today);
            View very_good_mood_shade_in_habits_in_the_top_today = getView().findViewById(R.id.very_good_mood_shade_in_habits_in_the_top_today);
            View very_bad_mood_check_mark_in_mood_in_the_top_today = getView().findViewById(R.id.very_bad_mood_check_mark_in_mood_in_the_top_today);
            View bad_mood_check_mark_in_mood_in_the_top_today = getView().findViewById(R.id.bad_mood_check_mark_in_mood_in_the_top_today);
            View ok_mood_check_mark_in_mood_in_the_top_today = getView().findViewById(R.id.ok_mood_check_mark_in_mood_in_the_top_today);
            View good_mood_check_mark_in_mood_in_the_top_today = getView().findViewById(R.id.good_mood_check_mark_in_mood_in_the_top_today);
            View very_good_mood_check_mark_in_mood_in_the_top_today = getView().findViewById(R.id.very_good_mood_check_mark_in_mood_in_the_top_today);
            if (result == 0) {
                very_bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                ok_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                good_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                very_good_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                very_bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                ok_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                very_good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
            } else if (result == 1) {
                very_bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.VISIBLE);
                bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                ok_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                good_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                very_good_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                very_bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.VISIBLE);
                bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                ok_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                very_good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
            } else if (result == 2) {
                very_bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.VISIBLE);
                ok_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                good_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                very_good_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                very_bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.VISIBLE);
                ok_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                very_good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
            } else if (result == 3) {
                very_bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                ok_mood_shade_in_habits_in_the_top_today.setVisibility(View.VISIBLE);
                good_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                very_good_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                very_bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                ok_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.VISIBLE);
                good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                very_good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
            } else if (result == 4) {
                very_bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                ok_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                good_mood_shade_in_habits_in_the_top_today.setVisibility(View.VISIBLE);
                very_good_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                very_bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                ok_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.VISIBLE);
                very_good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
            } else if (result == 5) {
                very_bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                ok_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                good_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                very_good_mood_shade_in_habits_in_the_top_today.setVisibility(View.VISIBLE);
                very_bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                ok_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                very_good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.VISIBLE);
            }
        }
    }

    private void button_listen_at_the_top() {
        if (getView() != null) {
            Button very_bad_mood_button_in_habits_in_the_top_today = getView().findViewById(R.id.very_bad_mood_button_in_habits_in_the_top_today);
            Button bad_mood_button_in_habits_in_the_top_today = getView().findViewById(R.id.bad_mood_button_in_habits_in_the_top_today);
            Button ok_mood_button_in_habits_in_the_top_today = getView().findViewById(R.id.ok_mood_button_in_habits_in_the_top_today);
            Button good_mood_button_in_habits_in_the_top_today = getView().findViewById(R.id.good_mood_button_in_habits_in_the_top_today);
            Button very_good_mood_button_in_habits_in_the_top_today = getView().findViewById(R.id.very_good_mood_button_in_habits_in_the_top_today);
            final View very_bad_mood_shade_in_habits_in_the_top_today = getView().findViewById(R.id.very_bad_mood_shade_in_habits_in_the_top_today);
            final View bad_mood_shade_in_habits_in_the_top_today = getView().findViewById(R.id.bad_mood_shade_in_habits_in_the_top_today);
            final View ok_mood_shade_in_habits_in_the_top_today = getView().findViewById(R.id.ok_mood_shade_in_habits_in_the_top_today);
            final View good_mood_shade_in_habits_in_the_top_today = getView().findViewById(R.id.good_mood_shade_in_habits_in_the_top_today);
            final View very_good_mood_shade_in_habits_in_the_top_today = getView().findViewById(R.id.very_good_mood_shade_in_habits_in_the_top_today);
            very_bad_mood_button_in_habits_in_the_top_today.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (very_bad_mood_shade_in_habits_in_the_top_today.getVisibility() == View.VISIBLE) {
                        make_the_buttons_in_the_top_mood(0);
                        save_the_input_for_good_habit_input(0, System.currentTimeMillis());
                    } else {
                        make_the_buttons_in_the_top_mood(1);
                        save_the_input_for_good_habit_input(1, System.currentTimeMillis());
                    }
                    color_the_calender();
                    //set_up_day_of_week_bar_chart();
                    clear_all_the_unders();
                    divide_it_into_weeks();
                    clear_the_middle();
                    make_the_middle_come_again();
                    color_the_under();
                    color_the_middle();
                    make_everything_average_mood();
                    draw_the_mood_line_chart();
                    draw_the_right_bar_chart_mood();
                    draw_pie_chart();
                    line_chart_for_streak.fitScreen();
                    set_the_leap_year();
                    put_values_into_year_in_good_habits();
                }
            });
            bad_mood_button_in_habits_in_the_top_today.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (bad_mood_shade_in_habits_in_the_top_today.getVisibility() == View.VISIBLE) {
                        make_the_buttons_in_the_top_mood(0);
                        save_the_input_for_good_habit_input(0, System.currentTimeMillis());
                    } else {
                        make_the_buttons_in_the_top_mood(2);
                        save_the_input_for_good_habit_input(2, System.currentTimeMillis());
                    }
                    color_the_calender();
                    //set_up_day_of_week_bar_chart();
                    clear_all_the_unders();
                    divide_it_into_weeks();
                    clear_the_middle();
                    make_the_middle_come_again();
                    color_the_under();
                    color_the_middle();
                    make_everything_average_mood();
                    draw_the_mood_line_chart();
                    draw_the_right_bar_chart_mood();
                    draw_pie_chart();
                    line_chart_for_streak.fitScreen();
                    set_the_leap_year();
                    put_values_into_year_in_good_habits();
                }
            });
            ok_mood_button_in_habits_in_the_top_today.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ok_mood_shade_in_habits_in_the_top_today.getVisibility() == View.VISIBLE) {
                        make_the_buttons_in_the_top_mood(0);
                        save_the_input_for_good_habit_input(0, System.currentTimeMillis());
                    } else {
                        make_the_buttons_in_the_top_mood(3);
                        save_the_input_for_good_habit_input(3, System.currentTimeMillis());
                    }
                    color_the_calender();
                    //set_up_day_of_week_bar_chart();
                    clear_all_the_unders();
                    divide_it_into_weeks();
                    clear_the_middle();
                    make_the_middle_come_again();
                    color_the_under();
                    color_the_middle();
                    make_everything_average_mood();
                    draw_the_mood_line_chart();
                    draw_the_right_bar_chart_mood();
                    draw_pie_chart();
                    line_chart_for_streak.fitScreen();
                    set_the_leap_year();
                    put_values_into_year_in_good_habits();
                }
            });
            good_mood_button_in_habits_in_the_top_today.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (good_mood_shade_in_habits_in_the_top_today.getVisibility() == View.VISIBLE) {
                        make_the_buttons_in_the_top_mood(0);
                        save_the_input_for_good_habit_input(0, System.currentTimeMillis());
                    } else {
                        make_the_buttons_in_the_top_mood(4);
                        save_the_input_for_good_habit_input(4, System.currentTimeMillis());
                    }
                    color_the_calender();
                    //set_up_day_of_week_bar_chart();
                    clear_all_the_unders();
                    divide_it_into_weeks();
                    clear_the_middle();
                    make_the_middle_come_again();
                    color_the_under();
                    color_the_middle();
                    make_everything_average_mood();
                    draw_the_mood_line_chart();
                    draw_the_right_bar_chart_mood();
                    draw_pie_chart();
                    line_chart_for_streak.fitScreen();
                    set_the_leap_year();
                    put_values_into_year_in_good_habits();
                }
            });
            very_good_mood_button_in_habits_in_the_top_today.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (very_good_mood_shade_in_habits_in_the_top_today.getVisibility() == View.VISIBLE) {
                        make_the_buttons_in_the_top_mood(0);
                        save_the_input_for_good_habit_input(0, System.currentTimeMillis());
                    } else {
                        make_the_buttons_in_the_top_mood(5);
                        save_the_input_for_good_habit_input(5, System.currentTimeMillis());
                    }
                    color_the_calender();
                    //set_up_day_of_week_bar_chart();
                    clear_all_the_unders();
                    divide_it_into_weeks();
                    clear_the_middle();
                    make_the_middle_come_again();
                    color_the_under();
                    color_the_middle();
                    make_everything_average_mood();
                    draw_the_mood_line_chart();
                    draw_the_right_bar_chart_mood();
                    draw_pie_chart();
                    line_chart_for_streak.fitScreen();
                    set_the_leap_year();
                    put_values_into_year_in_good_habits();
                }
            });
        }
    }

    private void set_up_buttons_once() {
        make_the_buttons_in_the_top_mood(return_color_of_days(System.currentTimeMillis()));
    }

    private void make_everything_average_mood() {
        if (getView() != null) {
            TextView text_showing_average_mood_number = getView().findViewById(R.id.text_showing_average_mood_number);
            View location_above_bmi_weight = getView().findViewById(R.id.location_above_bmi_weight);
            if (history_of_mood.isEmpty()) {
                make_everything_go_for_average_mood(0);
                return;
            } else {
                make_everything_go_for_average_mood(1);
            }
            String very_bad_color = return_the_color_of_mood(1);
            String bad_color = return_the_color_of_mood(2);
            String ok_color = return_the_color_of_mood(3);
            String good_color = return_the_color_of_mood(4);
            String very_good_color = return_the_color_of_mood(5);
            float average = 0;
            for (Map.Entry<Long, Integer> map : history_of_mood.entrySet()) {
                average = average + map.getValue();
            }
            average = average / history_of_mood.size();
            Drawable back_ground_location = location_above_bmi_weight.getBackground();
            float position_in_map = (average - 1) / 4;
            if (average <= 1.5) {
                back_ground_location.setTint(Color.parseColor(very_bad_color));
                text_showing_average_mood_number.setTextColor(Color.parseColor(very_bad_color));
            } else if (average <= 2.5) {
                back_ground_location.setTint(Color.parseColor(bad_color));
                text_showing_average_mood_number.setTextColor(Color.parseColor(bad_color));
            } else if (average <= 3.5) {
                back_ground_location.setTint(Color.parseColor(ok_color));
                text_showing_average_mood_number.setTextColor(Color.parseColor(ok_color));
            } else if (average <= 4.5) {
                back_ground_location.setTint(Color.parseColor(good_color));
                text_showing_average_mood_number.setTextColor(Color.parseColor(good_color));
            } else {
                back_ground_location.setTint(Color.parseColor(very_good_color));
                text_showing_average_mood_number.setTextColor(Color.parseColor(very_good_color));
            }
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) location_above_bmi_weight.getLayoutParams();
            params.horizontalBias = position_in_map;
            location_above_bmi_weight.setLayoutParams(params); // request the view to use the new modified params
            if ((int) average == average) {
                text_showing_average_mood_number.setText(String.valueOf((int) average));
            } else {
                text_showing_average_mood_number.setText(String.format("%.1f", average));
            }

        }
    }

    private void make_everything_go_for_average_mood(int which) {
        TextView text_showing_average_mood_number = getView().findViewById(R.id.text_showing_average_mood_number);
        View very_bad_mood_in_habits_in_the_average_mood = getView().findViewById(R.id.very_bad_mood_in_habits_in_the_average_mood);
        View bad_mood_in_habits_in_the_average_mood = getView().findViewById(R.id.bad_mood_in_habits_in_the_average_mood);
        View ok_mood_in_habits_in_the_average_mood = getView().findViewById(R.id.ok_mood_in_habits_in_the_average_mood);
        View good_mood_in_habits_in_the_average_mood = getView().findViewById(R.id.good_mood_in_habits_in_the_average_mood);
        View very_good_mood_in_habits_in_the_average_mood = getView().findViewById(R.id.very_good_mood_in_habits_in_the_average_mood);
        View location_above_bmi_weight = getView().findViewById(R.id.location_above_bmi_weight);
        ProgressBar progress_bar_number_one_mood = getView().findViewById(R.id.progress_bar_number_one_mood);
        ProgressBar progress_bar_number_two_mood = getView().findViewById(R.id.progress_bar_number_two_mood);
        ProgressBar progress_bar_number_three_mood = getView().findViewById(R.id.progress_bar_number_three_mood);
        ProgressBar progress_bar_number_four_mood = getView().findViewById(R.id.progress_bar_number_four_mood);
        ProgressBar progress_bar_number_five_mood = getView().findViewById(R.id.progress_bar_number_five_mood);
        TextView text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_average_bar = getView().findViewById(R.id.text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_average_bar);
        if (which == 0) {
            text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_average_bar.setVisibility(View.VISIBLE);
            text_showing_average_mood_number.setVisibility(View.INVISIBLE);
            very_bad_mood_in_habits_in_the_average_mood.setVisibility(View.INVISIBLE);
            bad_mood_in_habits_in_the_average_mood.setVisibility(View.INVISIBLE);
            ok_mood_in_habits_in_the_average_mood.setVisibility(View.INVISIBLE);
            good_mood_in_habits_in_the_average_mood.setVisibility(View.INVISIBLE);
            very_good_mood_in_habits_in_the_average_mood.setVisibility(View.INVISIBLE);
            location_above_bmi_weight.setVisibility(View.INVISIBLE);
            progress_bar_number_one_mood.setVisibility(View.INVISIBLE);
            progress_bar_number_two_mood.setVisibility(View.INVISIBLE);
            progress_bar_number_three_mood.setVisibility(View.INVISIBLE);
            progress_bar_number_four_mood.setVisibility(View.INVISIBLE);
            progress_bar_number_five_mood.setVisibility(View.INVISIBLE);
        } else if (which == 1) {
            text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_average_bar.setVisibility(View.INVISIBLE);
            text_showing_average_mood_number.setVisibility(View.VISIBLE);
            very_bad_mood_in_habits_in_the_average_mood.setVisibility(View.VISIBLE);
            bad_mood_in_habits_in_the_average_mood.setVisibility(View.VISIBLE);
            ok_mood_in_habits_in_the_average_mood.setVisibility(View.VISIBLE);
            good_mood_in_habits_in_the_average_mood.setVisibility(View.VISIBLE);
            very_good_mood_in_habits_in_the_average_mood.setVisibility(View.VISIBLE);
            location_above_bmi_weight.setVisibility(View.VISIBLE);
            progress_bar_number_one_mood.setVisibility(View.VISIBLE);
            progress_bar_number_two_mood.setVisibility(View.VISIBLE);
            progress_bar_number_three_mood.setVisibility(View.VISIBLE);
            progress_bar_number_four_mood.setVisibility(View.VISIBLE);
            progress_bar_number_five_mood.setVisibility(View.VISIBLE);
        }
    }

    private void draw_the_mood_line_chart() {
        if (getView() != null) {
            TextView text_saying_month_year_in_the_chart_mood_tracker = getView().findViewById(R.id.text_saying_month_year_in_the_chart_mood_tracker);
            Button button_to_show_forward_above_mood_tracker_graph = getView().findViewById(R.id.button_to_show_forward_above_mood_tracker_graph);
            View view_to_show_back_above_mood_tracker_graph = getView().findViewById(R.id.view_to_show_back_above_mood_tracker_graph);
            Am_i_paid am_i_paid = new Am_i_paid(getContext());
            if (am_i_paid.did_user_pay()) {
                Calendar calendar = Calendar.getInstance();
                int real_month = calendar.get(Calendar.MONTH);
                int real_year = calendar.get(Calendar.YEAR);
                if (month_for_mood_chart == -1) {
                    month_for_mood_chart = real_month;
                    year_for_mood_chart = real_year;
                    button_to_show_forward_above_mood_tracker_graph.setVisibility(View.INVISIBLE);
                    view_to_show_back_above_mood_tracker_graph.setVisibility(View.INVISIBLE);
                } else if (month_for_mood_chart == real_month && year_for_mood_chart == real_year) {
                    button_to_show_forward_above_mood_tracker_graph.setVisibility(View.INVISIBLE);
                    view_to_show_back_above_mood_tracker_graph.setVisibility(View.INVISIBLE);
                } else {
                    button_to_show_forward_above_mood_tracker_graph.setVisibility(View.VISIBLE);
                    view_to_show_back_above_mood_tracker_graph.setVisibility(View.VISIBLE);
                }
                text_saying_month_year_in_the_chart_mood_tracker.setText(return_month(month_for_mood_chart).concat(" ").concat(String.valueOf(year_for_mood_chart)));
            /*final List<Drawable> xAxisValues = new ArrayList<>();
            xAxisValues.add(return_mood_logo_very_bad());
            xAxisValues.add(return_mood_logo_bad());
            xAxisValues.add(return_mood_logo_ok());
            xAxisValues.add(return_mood_logo_good());
            xAxisValues.add(return_mood_logo_very_good());*/


                int color_card = color;
                line_chart_for_streak = getView().findViewById(R.id.cahrt_showing_mood_in_mood_tracker);
                TextView text_view_saying_that_there_is_not_enough_data_to_draw_this_line_chart = getView().findViewById(R.id.text_view_saying_that_there_is_not_enough_data_to_draw_this_line_chart);
                TextView saying_you_can_scroll_to_see_more_mood_chart = getView().findViewById(R.id.saying_you_can_scroll_to_see_more_mood_chart);
                line_chart_for_streak.invalidate();
                line_chart_for_streak.clear();
                //process the data
                ArrayList<Entry> y_values = new ArrayList<>();
                String string_to_split = return_data_mood_for_mood_line_chart();
                if (string_to_split.equals("")) {
                    text_view_saying_that_there_is_not_enough_data_to_draw_this_line_chart.setVisibility(View.VISIBLE);
                    line_chart_for_streak.setVisibility(View.INVISIBLE);
                    saying_you_can_scroll_to_see_more_mood_chart.setVisibility(View.INVISIBLE);
                    return;
                }
                text_view_saying_that_there_is_not_enough_data_to_draw_this_line_chart.setVisibility(View.INVISIBLE);
                line_chart_for_streak.setVisibility(View.VISIBLE);
                saying_you_can_scroll_to_see_more_mood_chart.setVisibility(View.VISIBLE);
                String[] split_data = string_to_split.split("big_split");
                String[] small_split_for_last_value = split_data[split_data.length - 1].split("small_split");
                int last_value = Integer.parseInt(small_split_for_last_value[0]);
                if (split_data.length <= 10) {
                    saying_you_can_scroll_to_see_more_mood_chart.setVisibility(View.INVISIBLE);
                }
                for (int i = 0; i < split_data.length; i++) {
                    String[] small_split = split_data[i].split("small_split");
                    if (small_split[1].equals("1")) {
                        y_values.add(new Entry(Integer.parseInt(small_split[0]), Integer.parseInt(small_split[1]), return_mood_logo_very_bad()));
                    } else if (small_split[1].equals("2")) {
                        y_values.add(new Entry(Integer.parseInt(small_split[0]), Integer.parseInt(small_split[1]), return_mood_logo_bad()));
                    } else if (small_split[1].equals("3")) {
                        y_values.add(new Entry(Integer.parseInt(small_split[0]), Integer.parseInt(small_split[1]), return_mood_logo_ok()));
                    } else if (small_split[1].equals("4")) {
                        y_values.add(new Entry(Integer.parseInt(small_split[0]), Integer.parseInt(small_split[1]), return_mood_logo_good()));
                    } else if (small_split[1].equals("5")) {
                        y_values.add(new Entry(Integer.parseInt(small_split[0]), Integer.parseInt(small_split[1]), return_mood_logo_very_good()));
                    }
                }
                line_chart_for_streak.fitScreen();
                line_chart_for_streak.setPadding(0, 0, 0, 0);
                //set grid lines
                line_chart_for_streak.getXAxis().setDrawGridLines(false);
                line_chart_for_streak.getAxisLeft().setDrawGridLines(true);
                line_chart_for_streak.getAxisRight().setDrawGridLines(false);

                //set x axis
                XAxis xAxis = line_chart_for_streak.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setLabelCount(y_values.size(), false);
                xAxis.setGranularity(1.0f);
                xAxis.setGranularityEnabled(true); // Required to enable granularity
                xAxis.setAvoidFirstLastClipping(true);
                xAxis.setAxisMaximum(last_value + 0.1f);
                // set description
                line_chart_for_streak.getDescription().setText("");
                // disable stuff
                line_chart_for_streak.setScaleEnabled(false);
                line_chart_for_streak.getLegend().setEnabled(false);

                //control left and right axis
                line_chart_for_streak.getAxisRight().setEnabled(false);
                line_chart_for_streak.getAxisLeft().setAxisMinimum(1);
                line_chart_for_streak.getAxisLeft().setDrawAxisLine(false);
                line_chart_for_streak.getAxisLeft().setGranularity(1.0f);
                line_chart_for_streak.getAxisLeft().setGranularityEnabled(true); // Required to enable granularity
                line_chart_for_streak.getAxisLeft().setAxisMaximum(5);

                LineDataSet lineDataSet = new LineDataSet(y_values, "data");
                lineDataSet.setColor(color_card);
                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(lineDataSet);
                LineData data = new LineData(dataSets);
                line_chart_for_streak.setData(data);
                line_chart_for_streak.getData().setHighlightEnabled(false);
                //lineDataSet.resetCircleColors();
                lineDataSet.setLineWidth(3f);
                lineDataSet.setCircleRadius(10f);
                //lineDataSet.setCircleHoleRadius(25f);
                lineDataSet.setCircleHoleColor(Color.WHITE);
                lineDataSet.setCircleColor(Color.WHITE);
                lineDataSet.setDrawValues(false);
                line_chart_for_streak.invalidate();
                line_chart_for_streak.setVisibleXRangeMaximum(9);
                line_chart_for_streak.moveViewToX(y_values.size());
            } else {
                Calendar calendar = Calendar.getInstance();
                int real_month = calendar.get(Calendar.MONTH);
                int real_year = calendar.get(Calendar.YEAR);
                if (month_for_mood_chart == -1) {
                    month_for_mood_chart = real_month;
                    year_for_mood_chart = real_year;
                    button_to_show_forward_above_mood_tracker_graph.setVisibility(View.INVISIBLE);
                    view_to_show_back_above_mood_tracker_graph.setVisibility(View.INVISIBLE);
                } else if (month_for_mood_chart == real_month && year_for_mood_chart == real_year) {
                    button_to_show_forward_above_mood_tracker_graph.setVisibility(View.INVISIBLE);
                    view_to_show_back_above_mood_tracker_graph.setVisibility(View.INVISIBLE);
                } else {
                    button_to_show_forward_above_mood_tracker_graph.setVisibility(View.VISIBLE);
                    view_to_show_back_above_mood_tracker_graph.setVisibility(View.VISIBLE);
                }
                text_saying_month_year_in_the_chart_mood_tracker.setText(return_month(month_for_mood_chart).concat(" ").concat(String.valueOf(year_for_mood_chart)));
                int color_card = color;
                line_chart_for_streak = getView().findViewById(R.id.cahrt_showing_mood_in_mood_tracker);
                TextView text_view_saying_that_there_is_not_enough_data_to_draw_this_line_chart = getView().findViewById(R.id.text_view_saying_that_there_is_not_enough_data_to_draw_this_line_chart);
                TextView saying_you_can_scroll_to_see_more_mood_chart = getView().findViewById(R.id.saying_you_can_scroll_to_see_more_mood_chart);
                line_chart_for_streak.invalidate();
                line_chart_for_streak.clear();
                //process the data
                ArrayList<Entry> y_values = new ArrayList<>();
                String string_to_split = return_data_mood_for_mood_line_chart();
                if (string_to_split.equals("")) {
                    text_view_saying_that_there_is_not_enough_data_to_draw_this_line_chart.setVisibility(View.VISIBLE);
                    line_chart_for_streak.setVisibility(View.INVISIBLE);
                    saying_you_can_scroll_to_see_more_mood_chart.setVisibility(View.INVISIBLE);
                    return;
                }
                text_view_saying_that_there_is_not_enough_data_to_draw_this_line_chart.setVisibility(View.INVISIBLE);
                line_chart_for_streak.setVisibility(View.VISIBLE);
                saying_you_can_scroll_to_see_more_mood_chart.setVisibility(View.VISIBLE);
                int last_value = 30;
                Random random = new Random();
                for (int i = 0; i < 31; i++) {
                    int mood = random.nextInt(5) + 1;
                    if (mood == 1) {
                        y_values.add(new Entry(i, mood, return_mood_logo_very_bad()));
                    } else if (mood == 2) {
                        y_values.add(new Entry(i, mood, return_mood_logo_bad()));
                    } else if (mood == 3) {
                        y_values.add(new Entry(i, mood, return_mood_logo_ok()));
                    } else if (mood == 4) {
                        y_values.add(new Entry(i, mood, return_mood_logo_good()));
                    } else if (mood == 5) {
                        y_values.add(new Entry(i, mood, return_mood_logo_very_good()));
                    }
                }
                line_chart_for_streak.fitScreen();
                line_chart_for_streak.setPadding(0, 0, 0, 0);
                //set grid lines
                line_chart_for_streak.getXAxis().setDrawGridLines(false);
                line_chart_for_streak.getAxisLeft().setDrawGridLines(true);
                line_chart_for_streak.getAxisRight().setDrawGridLines(false);

                //set x axis
                XAxis xAxis = line_chart_for_streak.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setLabelCount(y_values.size(), false);
                xAxis.setGranularity(1.0f);
                xAxis.setGranularityEnabled(true); // Required to enable granularity
                xAxis.setAvoidFirstLastClipping(true);
                xAxis.setAxisMaximum(last_value + 0.1f);
                // set description
                line_chart_for_streak.getDescription().setText("");
                // disable stuff
                line_chart_for_streak.setScaleEnabled(false);
                line_chart_for_streak.getLegend().setEnabled(false);

                //control left and right axis
                line_chart_for_streak.getAxisRight().setEnabled(false);
                line_chart_for_streak.getAxisLeft().setAxisMinimum(1);
                line_chart_for_streak.getAxisLeft().setDrawAxisLine(false);
                line_chart_for_streak.getAxisLeft().setGranularity(1.0f);
                line_chart_for_streak.getAxisLeft().setGranularityEnabled(true); // Required to enable granularity
                line_chart_for_streak.getAxisLeft().setAxisMaximum(5);

                LineDataSet lineDataSet = new LineDataSet(y_values, "data");
                lineDataSet.setColor(color_card);
                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(lineDataSet);
                LineData data = new LineData(dataSets);
                line_chart_for_streak.setData(data);
                line_chart_for_streak.getData().setHighlightEnabled(false);
                //lineDataSet.resetCircleColors();
                lineDataSet.setLineWidth(3f);
                lineDataSet.setCircleRadius(10f);
                //lineDataSet.setCircleHoleRadius(25f);
                lineDataSet.setCircleHoleColor(Color.WHITE);
                lineDataSet.setCircleColor(Color.WHITE);
                lineDataSet.setDrawValues(false);
                line_chart_for_streak.invalidate();
                line_chart_for_streak.setVisibleXRangeMaximum(9);
                line_chart_for_streak.moveViewToX(y_values.size());
            }
        }
    }

    private void back_and_front_button_listen_for_the_graph_mood() {
        if (getView() != null) {
            Button button_to_show_back_above_mood_tracker_graph = getView().findViewById(R.id.button_to_show_back_above_mood_tracker_graph);
            Button button_to_show_forward_above_mood_tracker_graph = getView().findViewById(R.id.button_to_show_forward_above_mood_tracker_graph);
            button_to_show_back_above_mood_tracker_graph.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((month_for_mood_chart) == 0) {
                        month_for_mood_chart = 11;
                        year_for_mood_chart--;
                    } else {
                        month_for_mood_chart--;
                    }
                    draw_the_mood_line_chart();
                }
            });
            button_to_show_forward_above_mood_tracker_graph.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((month_for_mood_chart) == 11) {
                        month_for_mood_chart = 0;
                        year_for_mood_chart++;
                    } else {
                        month_for_mood_chart++;
                    }
                    draw_the_mood_line_chart();
                }
            });
        }
    }

    private String return_data_mood_for_mood_line_chart() {
        String return_me = "";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year_for_mood_chart);
        calendar.set(Calendar.MONTH, month_for_mood_chart);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Calendar new_calender = Calendar.getInstance();
        for (int i = 1; i <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            new_calender.set(year_for_mood_chart, month_for_mood_chart, i);
            long time_in_milli = new_calender.getTimeInMillis();
            if (Simplify_the_time.return_time_in_midnight(time_in_milli) >= Simplify_the_time.return_time_in_midnight(start_date)) {
                if (Simplify_the_time.return_time_in_midnight(time_in_milli) <= Simplify_the_time.return_time_in_midnight(System.currentTimeMillis())) {
                    if (return_color_of_days(time_in_milli) == 1) {
                        return_me = return_me.concat(String.valueOf(i)).concat("small_split").concat("1").concat("big_split");
                    } else if (return_color_of_days(time_in_milli) == 2) {
                        return_me = return_me.concat(String.valueOf(i)).concat("small_split").concat("2").concat("big_split");
                    } else if (return_color_of_days(time_in_milli) == 3) {
                        return_me = return_me.concat(String.valueOf(i)).concat("small_split").concat("3").concat("big_split");
                    } else if (return_color_of_days(time_in_milli) == 4) {
                        return_me = return_me.concat(String.valueOf(i)).concat("small_split").concat("4").concat("big_split");
                    } else if (return_color_of_days(time_in_milli) == 5) {
                        return_me = return_me.concat(String.valueOf(i)).concat("small_split").concat("5").concat("big_split");
                    }
                }
            }
        }
        return return_me;
    }

    private Drawable return_mood_logo_very_bad() {
        String very_bad_color = return_the_color_of_mood(1);
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_sentiment_very_dissatisfied_24).mutate();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(very_bad_color));
        return wrappedDrawable;
    }

    private Drawable return_mood_logo_bad() {
        String bad_color = return_the_color_of_mood(2);
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_mood_bad_24).mutate();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(bad_color));
        return wrappedDrawable;
    }

    private Drawable return_mood_logo_ok() {
        String ok_color = return_the_color_of_mood(3);
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_face_24).mutate();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(ok_color));
        return wrappedDrawable;
    }

    private Drawable return_mood_logo_good() {
        String good_color = return_the_color_of_mood(4);
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_mood_24).mutate();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(good_color));
        return wrappedDrawable;
    }

    private Drawable return_mood_logo_very_good() {
        String very_good_color = return_the_color_of_mood(5);
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_sentiment_very_satisfied_24).mutate();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(very_good_color));
        return wrappedDrawable;
    }

    private void set_all_the_faces_in_the_mood() {
        View very_bad_mood_in_habits_in_the_top_today = getView().findViewById(R.id.very_bad_mood_in_habits_in_the_top_today);
        View bad_mood_in_habits_in_the_top_today = getView().findViewById(R.id.bad_mood_in_habits_in_the_top_today);
        View ok_mood_in_habits_in_the_top_today = getView().findViewById(R.id.ok_mood_in_habits_in_the_top_today);
        View good_mood_in_habits_in_the_top_today = getView().findViewById(R.id.good_mood_in_habits_in_the_top_today);
        View very_good_mood_in_habits_in_the_top_today = getView().findViewById(R.id.very_good_mood_in_habits_in_the_top_today);

        View very_bad_mood_in_habits = getView().findViewById(R.id.very_bad_mood_in_habits);
        View bad_mood_in_habits = getView().findViewById(R.id.bad_mood_in_habits);
        View ok_mood_in_habits = getView().findViewById(R.id.ok_mood_in_habits);
        View good_mood_in_habits = getView().findViewById(R.id.good_mood_in_habits);
        View very_good_mood_in_habits = getView().findViewById(R.id.very_good_mood_in_habits);

        View very_bad_mood_in_habits_in_the_average_mood = getView().findViewById(R.id.very_bad_mood_in_habits_in_the_average_mood);
        View bad_mood_in_habits_in_the_average_mood = getView().findViewById(R.id.bad_mood_in_habits_in_the_average_mood);
        View ok_mood_in_habits_in_the_average_mood = getView().findViewById(R.id.ok_mood_in_habits_in_the_average_mood);
        View good_mood_in_habits_in_the_average_mood = getView().findViewById(R.id.good_mood_in_habits_in_the_average_mood);
        View very_good_mood_in_habits_in_the_average_mood = getView().findViewById(R.id.very_good_mood_in_habits_in_the_average_mood);

        View very_bad_color_under_the_pie_mood_tracker = getView().findViewById(R.id.very_bad_color_under_the_pie_mood_tracker);
        View bad_color_under_the_pie_mood_tracker = getView().findViewById(R.id.bad_color_under_the_pie_mood_tracker);
        View ok_color_under_the_pie_mood_tracker = getView().findViewById(R.id.ok_color_under_the_pie_mood_tracker);
        View good_color_under_the_pie_mood_tracker = getView().findViewById(R.id.good_color_under_the_pie_mood_tracker);
        View very_good_color_under_the_pie_mood_tracker = getView().findViewById(R.id.very_good_color_under_the_pie_mood_tracker);

        String very_bad_color = return_the_color_of_mood(1);
        String bad_color = return_the_color_of_mood(2);
        String ok_color = return_the_color_of_mood(3);
        String good_color = return_the_color_of_mood(4);
        String very_good_color = return_the_color_of_mood(5);

        very_bad_mood_in_habits_in_the_top_today.setBackground(return_mood_logo_very_bad());
        very_bad_mood_in_habits.setBackground(return_mood_logo_very_bad());
        very_bad_mood_in_habits_in_the_average_mood.setBackground(return_mood_logo_very_bad());

        bad_mood_in_habits_in_the_top_today.setBackground(return_mood_logo_bad());
        bad_mood_in_habits.setBackground(return_mood_logo_bad());
        bad_mood_in_habits_in_the_average_mood.setBackground(return_mood_logo_bad());

        ok_mood_in_habits_in_the_top_today.setBackground(return_mood_logo_ok());
        ok_mood_in_habits.setBackground(return_mood_logo_ok());
        ok_mood_in_habits_in_the_average_mood.setBackground(return_mood_logo_ok());

        good_mood_in_habits_in_the_top_today.setBackground(return_mood_logo_good());
        good_mood_in_habits.setBackground(return_mood_logo_good());
        good_mood_in_habits_in_the_average_mood.setBackground(return_mood_logo_good());
        very_good_mood_in_habits_in_the_top_today.setBackground(return_mood_logo_very_good());
        very_good_mood_in_habits.setBackground(return_mood_logo_very_good());
        very_good_mood_in_habits_in_the_average_mood.setBackground(return_mood_logo_very_good());

        {
            Drawable background = very_bad_color_under_the_pie_mood_tracker.getBackground().mutate();
            background.setTint(Color.parseColor(very_bad_color));
        }
        {
            Drawable background = bad_color_under_the_pie_mood_tracker.getBackground().mutate();
            background.setTint(Color.parseColor(bad_color));
        }
        {
            Drawable background = ok_color_under_the_pie_mood_tracker.getBackground().mutate();
            background.setTint(Color.parseColor(ok_color));
        }
        {
            Drawable background = good_color_under_the_pie_mood_tracker.getBackground().mutate();
            background.setTint(Color.parseColor(good_color));
        }
        {
            Drawable background = very_good_color_under_the_pie_mood_tracker.getBackground().mutate();
            background.setTint(Color.parseColor(very_good_color));
        }
    }

    private String return_the_days_of_the_good_habit() {
        if (getView() != null) {
            int monday = 0;
            int tuesday = 0;
            int wednesday = 0;
            int thursday = 0;
            int friday = 0;
            int saturday = 0;
            int sunday = 0;
            float monday_average = 0;
            float tuesday_average = 0;
            float wednesday_average = 0;
            float thursday_average = 0;
            float friday_average = 0;
            float saturday_average = 0;
            float sunday_average = 0;
            for (Map.Entry<Long, Integer> map : history_of_mood.entrySet()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(map.getKey());
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                    monday = monday + 1;
                    monday_average = monday_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                    tuesday = tuesday + 1;
                    tuesday_average = tuesday_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                    wednesday = wednesday + 1;
                    wednesday_average = wednesday_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                    thursday = thursday + 1;
                    thursday_average = thursday_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                    friday = friday + 1;
                    friday_average = friday_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                    saturday = saturday + 1;
                    saturday_average = saturday_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    sunday = sunday + 1;
                    sunday_average = sunday_average + map.getValue();
                }
            }
            if (monday != 0) {
                monday_average = monday_average / monday;
            }
            if (tuesday != 0) {
                tuesday_average = tuesday_average / tuesday;
            }
            if (wednesday != 0) {
                wednesday_average = wednesday_average / wednesday;
            }
            if (thursday != 0) {
                thursday_average = thursday_average / thursday;
            }
            if (friday != 0) {
                friday_average = friday_average / friday;
            }
            if (saturday != 0) {
                saturday_average = saturday_average / saturday;
            }
            if (sunday != 0) {
                sunday_average = sunday_average / sunday;
            }
            return String.valueOf(monday_average).concat("split").concat(String.valueOf(tuesday_average)).concat("split").concat(String.valueOf(wednesday_average)).concat("split").concat(String.valueOf(thursday_average)).concat("split").concat(String.valueOf(friday_average)).concat("split").concat(String.valueOf(saturday_average)).concat("split").concat(String.valueOf(sunday_average));
        }
        return "";
    }

    private void draw_the_bar_for_average_mood() {
        if (getView() != null) {
            TextView text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse = getView().findViewById(R.id.text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse);
            BarChart cahrt_in_mood_about_the_average_for_each_month = getView().findViewById(R.id.cahrt_in_mood_about_the_average_for_each_month);
            BarChart cahrt_in_good_habits_about_how_many_times_for_each_days_of_week = getView().findViewById(R.id.cahrt_in_good_habits_about_how_many_times_for_each_days_of_week);
            Am_i_paid am_i_paid = new Am_i_paid(getContext());
            if (am_i_paid.did_user_pay()) {
                float max_days = 0;
                String days_of_week = return_the_days_of_the_good_habit();
                String[] split_days_of_week = days_of_week.split("split");
                for (int i = 0; i < split_days_of_week.length; i++) {
                    if (max_days < Float.parseFloat(split_days_of_week[i])) {
                        max_days = Float.parseFloat(split_days_of_week[i]);
                    }
                }
                if (max_days == 0) {
                    text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse.setVisibility(View.VISIBLE);
                    cahrt_in_mood_about_the_average_for_each_month.setVisibility(View.INVISIBLE);
                    cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setVisibility(View.INVISIBLE);
                    return;
                } else {
                    text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse.setVisibility(View.INVISIBLE);
                }
                final float max_days_final = max_days;
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setVisibility(View.VISIBLE);
                cahrt_in_mood_about_the_average_for_each_month.setVisibility(View.INVISIBLE);

                CustomBarChartRenderer barChartRender = new CustomBarChartRenderer(cahrt_in_good_habits_about_how_many_times_for_each_days_of_week, cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAnimator(), cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getViewPortHandler());
                barChartRender.setRadius(8);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setRenderer(barChartRender);
                List<BarEntry> entries = new ArrayList<>();
                String[] xAxisLables;
                int start_value;
                if (return_first_day_of_week().equals("monday")) {
                    xAxisLables = new String[]{"M", "T", "W", "T", "F", "S", "S"};
                    entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[0])));
                    entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[1])));
                    entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[2])));
                    entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[3])));
                    entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[4])));
                    entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[5])));
                    entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[6])));
                    start_value = 0;
                } else if (return_first_day_of_week().equals("tuesday")) {
                    xAxisLables = new String[]{"T", "W", "T", "F", "S", "S", "M"};
                    entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[1])));
                    entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[2])));
                    entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[3])));
                    entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[4])));
                    entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[5])));
                    entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[6])));
                    entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[0])));
                    start_value = 1;
                } else if (return_first_day_of_week().equals("wednesday")) {
                    xAxisLables = new String[]{"W", "T", "F", "S", "S", "M", "T"};
                    entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[2])));
                    entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[3])));
                    entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[4])));
                    entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[5])));
                    entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[6])));
                    entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[0])));
                    entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[1])));
                    start_value = 2;
                } else if (return_first_day_of_week().equals("thursday")) {
                    xAxisLables = new String[]{"T", "F", "S", "S", "M", "T", "W"};
                    entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[3])));
                    entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[4])));
                    entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[5])));
                    entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[6])));
                    entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[0])));
                    entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[1])));
                    entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[2])));
                    start_value = 3;
                } else if (return_first_day_of_week().equals("friday")) {
                    xAxisLables = new String[]{"F", "S", "S", "M", "T", "W", "T"};
                    entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[4])));
                    entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[5])));
                    entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[6])));
                    entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[0])));
                    entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[1])));
                    entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[2])));
                    entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[3])));
                    start_value = 4;
                } else if (return_first_day_of_week().equals("saturday")) {
                    xAxisLables = new String[]{"S", "S", "M", "T", "W", "T", "F"};
                    entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[5])));
                    entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[6])));
                    entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[0])));
                    entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[1])));
                    entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[2])));
                    entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[3])));
                    entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[4])));
                    start_value = 5;
                } else {
                    xAxisLables = new String[]{"S", "M", "T", "W", "T", "F", "S"};
                    entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[6])));
                    entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[0])));
                    entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[1])));
                    entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[2])));
                    entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[3])));
                    entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[4])));
                    entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[5])));
                    start_value = 6;
                }
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLables));
                ValueFormatter valueFormatter = new ValueFormatter() { //value format here, here is the overridden method
                    @Override
                    public String getFormattedValue(float value) {
                        if (value == 0 || (value / max_days_final < 0.1)) {
                            return "";
                        } else {
                            if ((int) value == value) {
                                return "" + (int) value;
                            } else {
                                return String.format("%.1f", value);
                            }
                        }
                    }
                };
                String very_bad_color = return_the_color_of_mood(1);
                String bad_color = return_the_color_of_mood(2);
                String ok_color = return_the_color_of_mood(3);
                String good_color = return_the_color_of_mood(4);
                String very_good_color = return_the_color_of_mood(5);
                int[] colors_for_bars = new int[7];
                int counter = 0;
                for (int i = start_value; i < start_value + 7; i++) {
                    float average_value = Float.parseFloat(split_days_of_week[i % 7]);
                    if (average_value <= 1.5) {
                        colors_for_bars[counter] = Color.parseColor(very_bad_color);
                    } else if (average_value <= 2.5) {
                        colors_for_bars[counter] = Color.parseColor(bad_color);
                    } else if (average_value <= 3.5) {
                        colors_for_bars[counter] = Color.parseColor(ok_color);
                    } else if (average_value <= 4.5) {
                        colors_for_bars[counter] = Color.parseColor(good_color);
                    } else {
                        colors_for_bars[counter] = Color.parseColor(very_good_color);
                    }
                    counter++;
                }
                BarDataSet set = new BarDataSet(entries, "BarDataSet");
                set.setColors(colors_for_bars);
                set.setValueTextColor(Color.WHITE);
                set.setValueTextSize(15);
                set.setValueTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                BarData data = new BarData(set);
                data.setValueFormatter(valueFormatter);
                data.setBarWidth(0.9f); // set custom bar width
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setData(data);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.invalidate(); // refresh
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setScaleEnabled(false);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getLegend().setEnabled(false);   // Hide the legend
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setExtraOffsets(0, 0, 0, 0);
                Description description = new Description();
                description.setText("");
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setDescription(description);
                XAxis xAxis = cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                data.setHighlightEnabled(false);
                data.setBarWidth(0.7f);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisLeft().setAxisMinimum(0f);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisRight().setAxisMinimum(0f);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getXAxis().setDrawGridLines(false);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisLeft().setDrawAxisLine(false);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisRight().setDrawAxisLine(false);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisLeft().setDrawLabels(false);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisRight().setDrawLabels(false);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setDrawValueAboveBar(false);
            } else {
                float max_days = 0;
                Random random = new Random();
                float monday = random.nextInt(5) + random.nextFloat();
                float tuesday = random.nextInt(5) + random.nextFloat();
                float wednesday = random.nextInt(5) + random.nextFloat();
                float thursday = random.nextInt(5) + random.nextFloat();
                float friday = random.nextInt(5) + random.nextFloat();
                float saturday = random.nextInt(5) + random.nextFloat();
                float sunday = random.nextInt(5) + random.nextFloat();
                String days_of_week = String.valueOf(monday).concat("split").concat(String.valueOf(tuesday)).concat("split").concat(String.valueOf(wednesday)).concat("split").concat(String.valueOf(thursday)).concat("split").concat(String.valueOf(friday)).concat("split").concat(String.valueOf(saturday)).concat("split").concat(String.valueOf(sunday));
                String[] split_days_of_week = days_of_week.split("split");
                for (int i = 0; i < split_days_of_week.length; i++) {
                    if (max_days < Float.parseFloat(split_days_of_week[i])) {
                        max_days = Float.parseFloat(split_days_of_week[i]);
                    }
                }
                if (max_days == 0) {
                    text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse.setVisibility(View.VISIBLE);
                    cahrt_in_mood_about_the_average_for_each_month.setVisibility(View.INVISIBLE);
                    cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setVisibility(View.INVISIBLE);
                    return;
                } else {
                    text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse.setVisibility(View.INVISIBLE);
                }
                final float max_days_final = max_days;
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setVisibility(View.VISIBLE);
                cahrt_in_mood_about_the_average_for_each_month.setVisibility(View.INVISIBLE);

                CustomBarChartRenderer barChartRender = new CustomBarChartRenderer(cahrt_in_good_habits_about_how_many_times_for_each_days_of_week, cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAnimator(), cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getViewPortHandler());
                barChartRender.setRadius(8);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setRenderer(barChartRender);
                List<BarEntry> entries = new ArrayList<>();
                String[] xAxisLables;
                int start_value;
                if (return_first_day_of_week().equals("monday")) {
                    xAxisLables = new String[]{"M", "T", "W", "T", "F", "S", "S"};
                    entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[0])));
                    entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[1])));
                    entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[2])));
                    entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[3])));
                    entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[4])));
                    entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[5])));
                    entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[6])));
                    start_value = 0;
                } else if (return_first_day_of_week().equals("tuesday")) {
                    xAxisLables = new String[]{"T", "W", "T", "F", "S", "S", "M"};
                    entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[1])));
                    entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[2])));
                    entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[3])));
                    entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[4])));
                    entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[5])));
                    entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[6])));
                    entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[0])));
                    start_value = 1;
                } else if (return_first_day_of_week().equals("wednesday")) {
                    xAxisLables = new String[]{"W", "T", "F", "S", "S", "M", "T"};
                    entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[2])));
                    entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[3])));
                    entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[4])));
                    entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[5])));
                    entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[6])));
                    entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[0])));
                    entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[1])));
                    start_value = 2;
                } else if (return_first_day_of_week().equals("thursday")) {
                    xAxisLables = new String[]{"T", "F", "S", "S", "M", "T", "W"};
                    entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[3])));
                    entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[4])));
                    entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[5])));
                    entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[6])));
                    entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[0])));
                    entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[1])));
                    entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[2])));
                    start_value = 3;
                } else if (return_first_day_of_week().equals("friday")) {
                    xAxisLables = new String[]{"F", "S", "S", "M", "T", "W", "T"};
                    entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[4])));
                    entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[5])));
                    entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[6])));
                    entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[0])));
                    entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[1])));
                    entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[2])));
                    entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[3])));
                    start_value = 4;
                } else if (return_first_day_of_week().equals("saturday")) {
                    xAxisLables = new String[]{"S", "S", "M", "T", "W", "T", "F"};
                    entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[5])));
                    entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[6])));
                    entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[0])));
                    entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[1])));
                    entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[2])));
                    entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[3])));
                    entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[4])));
                    start_value = 5;
                } else {
                    xAxisLables = new String[]{"S", "M", "T", "W", "T", "F", "S"};
                    entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[6])));
                    entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[0])));
                    entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[1])));
                    entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[2])));
                    entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[3])));
                    entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[4])));
                    entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[5])));
                    start_value = 6;
                }
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLables));
                ValueFormatter valueFormatter = new ValueFormatter() { //value format here, here is the overridden method
                    @Override
                    public String getFormattedValue(float value) {
                        if (value == 0 || (value / max_days_final < 0.1)) {
                            return "";
                        } else {
                            if ((int) value == value) {
                                return "" + (int) value;
                            } else {
                                return String.format("%.1f", value);
                            }
                        }
                    }
                };
                String very_bad_color = return_the_color_of_mood(1);
                String bad_color = return_the_color_of_mood(2);
                String ok_color = return_the_color_of_mood(3);
                String good_color = return_the_color_of_mood(4);
                String very_good_color = return_the_color_of_mood(5);
                int[] colors_for_bars = new int[7];
                int counter = 0;
                for (int i = start_value; i < start_value + 7; i++) {
                    float average_value = Float.parseFloat(split_days_of_week[i % 7]);
                    if (average_value <= 1.5) {
                        colors_for_bars[counter] = Color.parseColor(very_bad_color);
                    } else if (average_value <= 2.5) {
                        colors_for_bars[counter] = Color.parseColor(bad_color);
                    } else if (average_value <= 3.5) {
                        colors_for_bars[counter] = Color.parseColor(ok_color);
                    } else if (average_value <= 4.5) {
                        colors_for_bars[counter] = Color.parseColor(good_color);
                    } else {
                        colors_for_bars[counter] = Color.parseColor(very_good_color);
                    }
                    counter++;
                }
                BarDataSet set = new BarDataSet(entries, "BarDataSet");
                set.setColors(colors_for_bars);
                set.setValueTextColor(Color.WHITE);
                set.setValueTextSize(15);
                set.setValueTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                BarData data = new BarData(set);
                data.setValueFormatter(valueFormatter);
                data.setBarWidth(0.9f); // set custom bar width
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setData(data);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.invalidate(); // refresh
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setScaleEnabled(false);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getLegend().setEnabled(false);   // Hide the legend
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setExtraOffsets(0, 0, 0, 0);
                Description description = new Description();
                description.setText("");
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setDescription(description);
                XAxis xAxis = cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                data.setHighlightEnabled(false);
                data.setBarWidth(0.7f);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisLeft().setAxisMinimum(0f);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisRight().setAxisMinimum(0f);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getXAxis().setDrawGridLines(false);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisLeft().setDrawAxisLine(false);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisRight().setDrawAxisLine(false);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisLeft().setDrawLabels(false);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisRight().setDrawLabels(false);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setDrawValueAboveBar(false);
            }
        }
    }

    private String return_first_day_of_week() {
        Calendar calendar = Calendar.getInstance();
        if (calendar.getFirstDayOfWeek() == Calendar.MONDAY) {
            return "monday";
        } else if (calendar.getFirstDayOfWeek() == Calendar.TUESDAY) {
            return "tuesday";
        } else if (calendar.getFirstDayOfWeek() == Calendar.WEDNESDAY) {
            return "wednesday";
        } else if (calendar.getFirstDayOfWeek() == Calendar.THURSDAY) {
            return "thursday";
        } else if (calendar.getFirstDayOfWeek() == Calendar.FRIDAY) {
            return "friday";
        } else if (calendar.getFirstDayOfWeek() == Calendar.SATURDAY) {
            return "saturday";
        } else {
            return "sunday";
        }
    }

    private String return_data_for_bar_chart_yearly() {
        if (getView() != null) {
            int january = 0;
            int february = 0;
            int march = 0;
            int april = 0;
            int may = 0;
            int june = 0;
            int july = 0;
            int august = 0;
            int september = 0;
            int october = 0;
            int november = 0;
            int december = 0;
            float jan_avg = 0;
            float feb_average = 0;
            float march_average = 0;
            float april_average = 0;
            float may_average = 0;
            float june_average = 0;
            float july_average = 0;
            int august_average = 0;
            int september_average = 0;
            int october_average = 0;
            int november_average = 0;
            int december_average = 0;
            for (Map.Entry<Long, Integer> map : history_of_mood.entrySet()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(map.getKey());
                if (calendar.get(Calendar.MONTH) == Calendar.JANUARY) {
                    january = january + 1;
                    jan_avg = jan_avg + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FEBRUARY) {
                    february = february + 1;
                    feb_average = feb_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MARCH) {
                    march = march + 1;
                    march_average = march_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.APRIL) {
                    april = april + 1;
                    april_average = april_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MAY) {
                    may = may + 1;
                    may_average = may_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.JUNE) {
                    june = june + 1;
                    june_average = june_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.JULY) {
                    july = july + 1;
                    july_average = july_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.AUGUST) {
                    august = august + 1;
                    august_average = august_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SEPTEMBER) {
                    september = september + 1;
                    september_average = september_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.OCTOBER) {
                    october = october + 1;
                    october_average = october_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.NOVEMBER) {
                    november = november + 1;
                    november_average = november_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.DECEMBER) {
                    december = december + 1;
                    december_average = december_average + map.getValue();
                }
            }
            if (january != 0) {
                jan_avg = jan_avg / january;
            }
            if (february != 0) {
                feb_average = feb_average / february;
            }
            if (march != 0) {
                march_average = march_average / march;
            }
            if (april != 0) {
                april_average = april_average / april;
            }
            if (may != 0) {
                may_average = may_average / may;
            }
            if (june != 0) {
                june_average = june_average / june;
            }
            if (july != 0) {
                july_average = july_average / july;
            }
            if (august != 0) {
                august_average = august_average / august;
            }
            if (september != 0) {
                september_average = september_average / september;
            }
            if (october != 0) {
                october_average = october_average / october;
            }
            if (november != 0) {
                november_average = november_average / november;
            }
            if (december != 0) {
                december_average = december_average / december;
            }
            return String.valueOf(jan_avg).concat("split").concat(String.valueOf(feb_average)).concat("split").concat(String.valueOf(march_average)).concat("split").concat(String.valueOf(april_average)).concat("split").concat(String.valueOf(may_average)).concat("split").concat(String.valueOf(june_average)).concat("split").concat(String.valueOf(july_average)).concat("split").concat(String.valueOf(august_average)).concat("split").concat(String.valueOf(september_average)).concat("split").concat(String.valueOf(october_average)).concat("split").concat(String.valueOf(november_average)).concat("split").concat(String.valueOf(december_average));
        }
        return "";
    }

    private void draw_bar_for_average_mood_over_the_year() {
        if (getView() != null) {
            float max_days = 0;
            String days_of_week = return_data_for_bar_chart_yearly();
            String[] split_days_of_week = days_of_week.split("split");
            for (int i = 0; i < split_days_of_week.length; i++) {
                if (max_days < Float.parseFloat(split_days_of_week[i])) {
                    max_days = Float.parseFloat(split_days_of_week[i]);
                }
            }
            TextView text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse = getView().findViewById(R.id.text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse);
            BarChart cahrt_in_mood_about_the_average_for_each_month = getView().findViewById(R.id.cahrt_in_mood_about_the_average_for_each_month);
            BarChart cahrt_in_good_habits_about_how_many_times_for_each_days_of_week = getView().findViewById(R.id.cahrt_in_good_habits_about_how_many_times_for_each_days_of_week);
            if (max_days == 0) {
                text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse.setVisibility(View.VISIBLE);
                cahrt_in_mood_about_the_average_for_each_month.setVisibility(View.INVISIBLE);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setVisibility(View.INVISIBLE);
                return;
            } else {
                text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse.setVisibility(View.INVISIBLE);
            }
            final float max_days_final = max_days;
            cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setVisibility(View.INVISIBLE);
            cahrt_in_mood_about_the_average_for_each_month.setVisibility(View.VISIBLE);
            CustomBarChartRenderer barChartRender = new CustomBarChartRenderer(cahrt_in_mood_about_the_average_for_each_month, cahrt_in_mood_about_the_average_for_each_month.getAnimator(), cahrt_in_mood_about_the_average_for_each_month.getViewPortHandler());
            barChartRender.setRadius(8);
            cahrt_in_mood_about_the_average_for_each_month.setRenderer(barChartRender);
            List<BarEntry> entries = new ArrayList<>();
            String[] xAxisLables;
            xAxisLables = new String[]{"J", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D"};
            entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[0])));
            entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[1])));
            entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[2])));
            entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[3])));
            entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[4])));
            entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[5])));
            entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[6])));
            entries.add(new BarEntry(7, Float.parseFloat(split_days_of_week[7])));
            entries.add(new BarEntry(8, Float.parseFloat(split_days_of_week[8])));
            entries.add(new BarEntry(9, Float.parseFloat(split_days_of_week[9])));
            entries.add(new BarEntry(10, Float.parseFloat(split_days_of_week[10])));
            entries.add(new BarEntry(11, Float.parseFloat(split_days_of_week[11])));
            cahrt_in_mood_about_the_average_for_each_month.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLables));
            ValueFormatter valueFormatter = new ValueFormatter() { //value format here, here is the overridden method
                @Override
                public String getFormattedValue(float value) {
                    if (value == 0 || (value / max_days_final < 0.1)) {
                        return "";
                    } else {
                        if ((int) value == value) {
                            return "" + (int) value;
                        } else {
                            return String.format("%.1f", value);
                        }
                    }
                }
            };
            String very_bad_color = return_the_color_of_mood(1);
            String bad_color = return_the_color_of_mood(2);
            String ok_color = return_the_color_of_mood(3);
            String good_color = return_the_color_of_mood(4);
            String very_good_color = return_the_color_of_mood(5);
            int[] colors_for_bars = new int[12];
            for (int i = 0; i < 12; i++) {
                float average_value = Float.parseFloat(split_days_of_week[i]);
                if (average_value <= 1.5) {
                    colors_for_bars[i] = Color.parseColor(very_bad_color);
                } else if (average_value <= 2.5) {
                    colors_for_bars[i] = Color.parseColor(bad_color);
                } else if (average_value <= 3.5) {
                    colors_for_bars[i] = Color.parseColor(ok_color);
                } else if (average_value <= 4.5) {
                    colors_for_bars[i] = Color.parseColor(good_color);
                } else {
                    colors_for_bars[i] = Color.parseColor(very_good_color);
                }
            }
            BarDataSet set = new BarDataSet(entries, "BarDataSet");
            set.setColors(colors_for_bars);
            set.setValueTextColor(Color.WHITE);
            set.setValueTextSize(15);
            set.setValueTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            BarData data = new BarData(set);
            data.setValueFormatter(valueFormatter);
            data.setBarWidth(0.9f); // set custom bar width
            cahrt_in_mood_about_the_average_for_each_month.setData(data);
            cahrt_in_mood_about_the_average_for_each_month.invalidate(); // refresh
            cahrt_in_mood_about_the_average_for_each_month.setScaleEnabled(false);
            cahrt_in_mood_about_the_average_for_each_month.getLegend().setEnabled(false);   // Hide the legend
            cahrt_in_mood_about_the_average_for_each_month.setExtraOffsets(0, 0, 0, 0);
            Description description = new Description();
            description.setText("");
            cahrt_in_mood_about_the_average_for_each_month.setDescription(description);
            XAxis xAxis = cahrt_in_mood_about_the_average_for_each_month.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            data.setHighlightEnabled(false);
            data.setBarWidth(0.7f);
            cahrt_in_mood_about_the_average_for_each_month.getAxisLeft().setAxisMinimum(0f);
            cahrt_in_mood_about_the_average_for_each_month.getAxisRight().setAxisMinimum(0f);
            cahrt_in_mood_about_the_average_for_each_month.getXAxis().setDrawGridLines(false);
            cahrt_in_mood_about_the_average_for_each_month.getAxisLeft().setDrawAxisLine(false);
            cahrt_in_mood_about_the_average_for_each_month.getAxisRight().setDrawAxisLine(false);
            cahrt_in_mood_about_the_average_for_each_month.getAxisLeft().setDrawLabels(false);
            cahrt_in_mood_about_the_average_for_each_month.getAxisRight().setDrawLabels(false);
            cahrt_in_mood_about_the_average_for_each_month.setDrawValueAboveBar(false);

            cahrt_in_mood_about_the_average_for_each_month.setVisibleXRangeMaximum(7);
            cahrt_in_mood_about_the_average_for_each_month.moveViewToX(entries.size());
        }
    }

    private void three_dot_button_in_bar_chart_listen() {
        if (getView() != null) {
            final Button three_dots_button_to_ask_weekly_or_monthly_mood_bar_average = getView().findViewById(R.id.three_dots_button_to_ask_weekly_or_monthly_mood_bar_average);
            three_dots_button_to_ask_weekly_or_monthly_mood_bar_average.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    show_popup_good_habits(three_dots_button_to_ask_weekly_or_monthly_mood_bar_average);
                }
            });
        }
    }

    public void show_popup_good_habits(View view) {
        if (getContext() != null) {
            PopupMenu popupMenu = new PopupMenu(getContext(), view);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.inflate(R.menu.average_week_month_mood);
            popupMenu.show();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        TextView text_title_of_weekly_daily_habit_in_card = getView().findViewById(R.id.text_title_of_weekly_daily_habit_in_card);
        TextView saying_you_can_scroll_to_see_more_mood_average_cahrt_in_mood_tracker = getView().findViewById(R.id.saying_you_can_scroll_to_see_more_mood_average_cahrt_in_mood_tracker);
        if (item.getItemId() == R.id.item_number_one_for_menu_edit_week_month_mood) {
            draw_the_bar_for_average_mood();
            text_title_of_weekly_daily_habit_in_card.setText("Weekly Mood Average");
            saying_you_can_scroll_to_see_more_mood_average_cahrt_in_mood_tracker.setVisibility(View.INVISIBLE);
            return true;
        } else if (item.getItemId() == R.id.item_number_two_for_menu_delete_week_month_mood) {
            draw_bar_for_average_mood_over_the_year();
            text_title_of_weekly_daily_habit_in_card.setText("Monthly Mood Average");
            saying_you_can_scroll_to_see_more_mood_average_cahrt_in_mood_tracker.setVisibility(View.VISIBLE);
            return true;
        } else {
            return false;
        }
    }

    private void draw_the_right_bar_chart_mood() {
        BarChart cahrt_in_mood_about_the_average_for_each_month = getView().findViewById(R.id.cahrt_in_mood_about_the_average_for_each_month);
        BarChart cahrt_in_good_habits_about_how_many_times_for_each_days_of_week = getView().findViewById(R.id.cahrt_in_good_habits_about_how_many_times_for_each_days_of_week);
        if (cahrt_in_mood_about_the_average_for_each_month.getVisibility() == View.VISIBLE) {
            draw_bar_for_average_mood_over_the_year();
        } else {
            draw_the_bar_for_average_mood();
        }
    }

    private String return_info_about_pie() {
        if (getView() != null) {
            int very_bad = 0;
            int bad = 0;
            int ok = 0;
            int good = 0;
            int very_good = 0;
            String return_me;
            for (Map.Entry<Long, Integer> map : history_of_mood.entrySet()) {
                if (map.getValue() == 1) {
                    very_bad++;
                } else if (map.getValue() == 2) {
                    bad++;
                } else if (map.getValue() == 3) {
                    ok++;
                } else if (map.getValue() == 4) {
                    good++;
                } else if (map.getValue() == 5) {
                    very_good++;
                }
            }
            return_me = String.valueOf(very_bad).concat("split").concat(String.valueOf(bad)).concat("split").concat(String.valueOf(ok)).concat("split").concat(String.valueOf(good)).concat("split").concat(String.valueOf(very_good));
            return return_me;
        } else {
            return "";
        }
    }

    private void draw_pie_chart() {
        if (getView() != null) {
            TextView very_bad_mood_text_under_pie = getView().findViewById(R.id.very_bad_mood_text_under_pie);
            TextView bad_mood_text_under_pie = getView().findViewById(R.id.bad_mood_text_under_pie);
            TextView ok_mood_text_under_pie = getView().findViewById(R.id.ok_mood_text_under_pie);
            TextView good_mood_text_under_pie = getView().findViewById(R.id.good_mood_text_under_pie);
            TextView very_good_mood_text_under_pie = getView().findViewById(R.id.very_good_mood_text_under_pie);
            View very_bad_color_under_the_pie_mood_tracker = getView().findViewById(R.id.very_bad_color_under_the_pie_mood_tracker);
            View bad_color_under_the_pie_mood_tracker = getView().findViewById(R.id.bad_color_under_the_pie_mood_tracker);
            View ok_color_under_the_pie_mood_tracker = getView().findViewById(R.id.ok_color_under_the_pie_mood_tracker);
            View good_color_under_the_pie_mood_tracker = getView().findViewById(R.id.good_color_under_the_pie_mood_tracker);
            View very_good_color_under_the_pie_mood_tracker = getView().findViewById(R.id.very_good_color_under_the_pie_mood_tracker);

            PieChart mChart = getView().findViewById(R.id.chart_to_show_pie_of_yes_or_no_pie);
            String[] split_yes_no = return_info_about_pie().split("split");
            TextView text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_pie = getView().findViewById(R.id.text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_pie);
            if (Integer.parseInt(split_yes_no[0]) > 0 || Integer.parseInt(split_yes_no[1]) > 0 || Integer.parseInt(split_yes_no[2]) > 0 || Integer.parseInt(split_yes_no[3]) > 0 || Integer.parseInt(split_yes_no[4]) > 0) {
                mChart.setVisibility(View.VISIBLE);
                text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_pie.setVisibility(View.INVISIBLE);
                very_bad_color_under_the_pie_mood_tracker.setVisibility(View.VISIBLE);
                bad_color_under_the_pie_mood_tracker.setVisibility(View.VISIBLE);
                ok_color_under_the_pie_mood_tracker.setVisibility(View.VISIBLE);
                good_color_under_the_pie_mood_tracker.setVisibility(View.VISIBLE);
                very_good_color_under_the_pie_mood_tracker.setVisibility(View.VISIBLE);
                very_bad_mood_text_under_pie.setVisibility(View.VISIBLE);
                bad_mood_text_under_pie.setVisibility(View.VISIBLE);
                ok_mood_text_under_pie.setVisibility(View.VISIBLE);
                good_mood_text_under_pie.setVisibility(View.VISIBLE);
                very_good_mood_text_under_pie.setVisibility(View.VISIBLE);
                List<PieEntry> pieChartEntries = new ArrayList<>();
                int total = Integer.parseInt(split_yes_no[0]) + Integer.parseInt(split_yes_no[1]) + Integer.parseInt(split_yes_no[2]) + Integer.parseInt(split_yes_no[3]) + Integer.parseInt(split_yes_no[4]);
                for (int i = 0; i < 5; i++) {
                    if (Integer.parseInt(split_yes_no[i]) > 0) {
                        if (total / Integer.parseInt(split_yes_no[i]) >= 50) {
                            pieChartEntries.add(new PieEntry(Float.parseFloat(split_yes_no[i])));
                        } else {
                            pieChartEntries.add(new PieEntry(Float.parseFloat(split_yes_no[i]), return_mood_logo_white(i)));
                        }
                    }
                }
                mChart.getLegend().setEnabled(false);   // Hide the legend
                mChart.setEntryLabelColor(Color.WHITE);
                mChart.setEntryLabelTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                mChart.setCenterTextTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                PieDataSet set = new PieDataSet(pieChartEntries, "");
                PieData data = new PieData(set);
                mChart.setCenterText(String.valueOf(total).concat(" entries"));
                mChart.setCenterTextSize(16.5f);
                mChart.setCenterTextColor(color);
                mChart.setData(data);
                ArrayList<Integer> array_with_colors = new ArrayList<>();
                String very_bad_color = return_the_color_of_mood(1);
                String bad_color = return_the_color_of_mood(2);
                String ok_color = return_the_color_of_mood(3);
                String good_color = return_the_color_of_mood(4);
                String very_good_color = return_the_color_of_mood(5);
                if (Integer.parseInt(split_yes_no[0]) > 0) {
                    array_with_colors.add(Color.parseColor(very_bad_color));
                }
                if (Integer.parseInt(split_yes_no[1]) > 0) {
                    array_with_colors.add(Color.parseColor(bad_color));
                }
                if (Integer.parseInt(split_yes_no[2]) > 0) {
                    array_with_colors.add(Color.parseColor(ok_color));
                }
                if (Integer.parseInt(split_yes_no[3]) > 0) {
                    array_with_colors.add(Color.parseColor(good_color));
                }
                if (Integer.parseInt(split_yes_no[4]) > 0) {
                    array_with_colors.add(Color.parseColor(very_good_color));
                }
                very_bad_mood_text_under_pie.setText(split_yes_no[0]);
                bad_mood_text_under_pie.setText(split_yes_no[1]);
                ok_mood_text_under_pie.setText(split_yes_no[2]);
                good_mood_text_under_pie.setText(split_yes_no[3]);
                very_good_mood_text_under_pie.setText(split_yes_no[4]);
                set.setColors(array_with_colors);
                set.setDrawValues(false);
                mChart.getDescription().setEnabled(false);
                mChart.invalidate();
            } else {
                mChart.setVisibility(View.INVISIBLE);
                text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_pie.setVisibility(View.VISIBLE);
                very_bad_color_under_the_pie_mood_tracker.setVisibility(View.INVISIBLE);
                bad_color_under_the_pie_mood_tracker.setVisibility(View.INVISIBLE);
                ok_color_under_the_pie_mood_tracker.setVisibility(View.INVISIBLE);
                good_color_under_the_pie_mood_tracker.setVisibility(View.INVISIBLE);
                very_good_color_under_the_pie_mood_tracker.setVisibility(View.INVISIBLE);
                very_bad_mood_text_under_pie.setVisibility(View.INVISIBLE);
                bad_mood_text_under_pie.setVisibility(View.INVISIBLE);
                ok_mood_text_under_pie.setVisibility(View.INVISIBLE);
                good_mood_text_under_pie.setVisibility(View.INVISIBLE);
                very_good_mood_text_under_pie.setVisibility(View.INVISIBLE);
            }
        }
    }

    private Drawable return_mood_logo_white(int which) {
        if (which == 0) {
            Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_sentiment_very_dissatisfied_24).mutate();
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, Color.WHITE);
            return wrappedDrawable;
        } else if (which == 1) {
            Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_mood_bad_24).mutate();
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, Color.WHITE);
            return wrappedDrawable;
        } else if (which == 2) {
            Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_face_24).mutate();
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, Color.WHITE);
            return wrappedDrawable;
        } else if (which == 3) {
            Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_mood_24).mutate();
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, Color.WHITE);
            return wrappedDrawable;
        } else {
            Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_sentiment_very_satisfied_24).mutate();
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, Color.WHITE);
            return wrappedDrawable;
        }
    }

    private void add_the_views() {
        if (getView() != null) {
            View good_habits_layout_circle_jan1_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan1_in_mood);
            View good_habits_layout_circle_jan2_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan2_in_mood);
            View good_habits_layout_circle_jan3_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan3_in_mood);
            View good_habits_layout_circle_jan4_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan4_in_mood);
            View good_habits_layout_circle_jan5_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan5_in_mood);
            View good_habits_layout_circle_jan6_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan6_in_mood);
            View good_habits_layout_circle_jan7_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan7_in_mood);
            View good_habits_layout_circle_jan8_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan8_in_mood);
            View good_habits_layout_circle_jan9_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan9_in_mood);
            View good_habits_layout_circle_jan10_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan10_in_mood);
            View good_habits_layout_circle_jan11_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan11_in_mood);
            View good_habits_layout_circle_jan12_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan12_in_mood);
            View good_habits_layout_circle_jan13_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan13_in_mood);
            View good_habits_layout_circle_jan14_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan14_in_mood);
            View good_habits_layout_circle_jan15_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan15_in_mood);
            View good_habits_layout_circle_jan16_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan16_in_mood);
            View good_habits_layout_circle_jan17_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan17_in_mood);
            View good_habits_layout_circle_jan18_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan18_in_mood);
            View good_habits_layout_circle_jan19_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan19_in_mood);
            View good_habits_layout_circle_jan20_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan20_in_mood);
            View good_habits_layout_circle_jan21_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan21_in_mood);
            View good_habits_layout_circle_jan22_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan22_in_mood);
            View good_habits_layout_circle_jan23_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan23_in_mood);
            View good_habits_layout_circle_jan24_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan24_in_mood);
            View good_habits_layout_circle_jan25_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan25_in_mood);
            View good_habits_layout_circle_jan26_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan26_in_mood);
            View good_habits_layout_circle_jan27_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan27_in_mood);
            View good_habits_layout_circle_jan28_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan28_in_mood);
            View good_habits_layout_circle_jan29_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan29_in_mood);
            View good_habits_layout_circle_jan30_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan30_in_mood);
            View good_habits_layout_circle_jan31_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jan31_in_mood);
            View good_habits_layout_circle_feb1_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb1_in_mood);
            View good_habits_layout_circle_feb2_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb2_in_mood);
            View good_habits_layout_circle_feb3_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb3_in_mood);
            View good_habits_layout_circle_feb4_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb4_in_mood);
            View good_habits_layout_circle_feb5_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb5_in_mood);
            View good_habits_layout_circle_feb6_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb6_in_mood);
            View good_habits_layout_circle_feb7_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb7_in_mood);
            View good_habits_layout_circle_feb8_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb8_in_mood);
            View good_habits_layout_circle_feb9_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb9_in_mood);
            View good_habits_layout_circle_feb10_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb10_in_mood);
            View good_habits_layout_circle_feb11_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb11_in_mood);
            View good_habits_layout_circle_feb12_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb12_in_mood);
            View good_habits_layout_circle_feb13_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb13_in_mood);
            View good_habits_layout_circle_feb14_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb14_in_mood);
            View good_habits_layout_circle_feb15_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb15_in_mood);
            View good_habits_layout_circle_feb16_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb16_in_mood);
            View good_habits_layout_circle_feb17_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb17_in_mood);
            View good_habits_layout_circle_feb18_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb18_in_mood);
            View good_habits_layout_circle_feb19_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb19_in_mood);
            View good_habits_layout_circle_feb20_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb20_in_mood);
            View good_habits_layout_circle_feb21_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb21_in_mood);
            View good_habits_layout_circle_feb22_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb22_in_mood);
            View good_habits_layout_circle_feb23_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb23_in_mood);
            View good_habits_layout_circle_feb24_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb24_in_mood);
            View good_habits_layout_circle_feb25_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb25_in_mood);
            View good_habits_layout_circle_feb26_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb26_in_mood);
            View good_habits_layout_circle_feb27_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb27_in_mood);
            View good_habits_layout_circle_feb28_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb28_in_mood);
            View good_habits_layout_circle_feb29_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb29_in_mood);
            View good_habits_layout_circle_mar1_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar1_in_mood);
            View good_habits_layout_circle_mar2_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar2_in_mood);
            View good_habits_layout_circle_mar3_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar3_in_mood);
            View good_habits_layout_circle_mar4_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar4_in_mood);
            View good_habits_layout_circle_mar5_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar5_in_mood);
            View good_habits_layout_circle_mar6_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar6_in_mood);
            View good_habits_layout_circle_mar7_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar7_in_mood);
            View good_habits_layout_circle_mar8_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar8_in_mood);
            View good_habits_layout_circle_mar9_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar9_in_mood);
            View good_habits_layout_circle_mar10_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar10_in_mood);
            View good_habits_layout_circle_mar11_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar11_in_mood);
            View good_habits_layout_circle_mar12_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar12_in_mood);
            View good_habits_layout_circle_mar13_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar13_in_mood);
            View good_habits_layout_circle_mar14_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar14_in_mood);
            View good_habits_layout_circle_mar15_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar15_in_mood);
            View good_habits_layout_circle_mar16_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar16_in_mood);
            View good_habits_layout_circle_mar17_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar17_in_mood);
            View good_habits_layout_circle_mar18_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar18_in_mood);
            View good_habits_layout_circle_mar19_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar19_in_mood);
            View good_habits_layout_circle_mar20_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar20_in_mood);
            View good_habits_layout_circle_mar21_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar21_in_mood);
            View good_habits_layout_circle_mar22_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar22_in_mood);
            View good_habits_layout_circle_mar23_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar23_in_mood);
            View good_habits_layout_circle_mar24_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar24_in_mood);
            View good_habits_layout_circle_mar25_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar25_in_mood);
            View good_habits_layout_circle_mar26_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar26_in_mood);
            View good_habits_layout_circle_mar27_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar27_in_mood);
            View good_habits_layout_circle_mar28_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar28_in_mood);
            View good_habits_layout_circle_mar29_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar29_in_mood);
            View good_habits_layout_circle_mar30_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar30_in_mood);
            View good_habits_layout_circle_mar31_in_mood = getView().findViewById(R.id.good_habits_layout_circle_mar31_in_mood);
            View good_habits_layout_circle_apr1_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr1_in_mood);
            View good_habits_layout_circle_apr2_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr2_in_mood);
            View good_habits_layout_circle_apr3_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr3_in_mood);
            View good_habits_layout_circle_apr4_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr4_in_mood);
            View good_habits_layout_circle_apr5_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr5_in_mood);
            View good_habits_layout_circle_apr6_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr6_in_mood);
            View good_habits_layout_circle_apr7_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr7_in_mood);
            View good_habits_layout_circle_apr8_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr8_in_mood);
            View good_habits_layout_circle_apr9_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr9_in_mood);
            View good_habits_layout_circle_apr10_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr10_in_mood);
            View good_habits_layout_circle_apr11_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr11_in_mood);
            View good_habits_layout_circle_apr12_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr12_in_mood);
            View good_habits_layout_circle_apr13_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr13_in_mood);
            View good_habits_layout_circle_apr14_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr14_in_mood);
            View good_habits_layout_circle_apr15_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr15_in_mood);
            View good_habits_layout_circle_apr16_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr16_in_mood);
            View good_habits_layout_circle_apr17_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr17_in_mood);
            View good_habits_layout_circle_apr18_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr18_in_mood);
            View good_habits_layout_circle_apr19_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr19_in_mood);
            View good_habits_layout_circle_apr20_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr20_in_mood);
            View good_habits_layout_circle_apr21_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr21_in_mood);
            View good_habits_layout_circle_apr22_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr22_in_mood);
            View good_habits_layout_circle_apr23_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr23_in_mood);
            View good_habits_layout_circle_apr24_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr24_in_mood);
            View good_habits_layout_circle_apr25_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr25_in_mood);
            View good_habits_layout_circle_apr26_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr26_in_mood);
            View good_habits_layout_circle_apr27_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr27_in_mood);
            View good_habits_layout_circle_apr28_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr28_in_mood);
            View good_habits_layout_circle_apr29_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr29_in_mood);
            View good_habits_layout_circle_apr30_in_mood = getView().findViewById(R.id.good_habits_layout_circle_apr30_in_mood);
            View good_habits_layout_circle_may1_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may1_in_mood);
            View good_habits_layout_circle_may2_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may2_in_mood);
            View good_habits_layout_circle_may3_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may3_in_mood);
            View good_habits_layout_circle_may4_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may4_in_mood);
            View good_habits_layout_circle_may5_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may5_in_mood);
            View good_habits_layout_circle_may6_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may6_in_mood);
            View good_habits_layout_circle_may7_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may7_in_mood);
            View good_habits_layout_circle_may8_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may8_in_mood);
            View good_habits_layout_circle_may9_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may9_in_mood);
            View good_habits_layout_circle_may10_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may10_in_mood);
            View good_habits_layout_circle_may11_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may11_in_mood);
            View good_habits_layout_circle_may12_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may12_in_mood);
            View good_habits_layout_circle_may13_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may13_in_mood);
            View good_habits_layout_circle_may14_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may14_in_mood);
            View good_habits_layout_circle_may15_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may15_in_mood);
            View good_habits_layout_circle_may16_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may16_in_mood);
            View good_habits_layout_circle_may17_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may17_in_mood);
            View good_habits_layout_circle_may18_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may18_in_mood);
            View good_habits_layout_circle_may19_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may19_in_mood);
            View good_habits_layout_circle_may20_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may20_in_mood);
            View good_habits_layout_circle_may21_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may21_in_mood);
            View good_habits_layout_circle_may22_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may22_in_mood);
            View good_habits_layout_circle_may23_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may23_in_mood);
            View good_habits_layout_circle_may24_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may24_in_mood);
            View good_habits_layout_circle_may25_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may25_in_mood);
            View good_habits_layout_circle_may26_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may26_in_mood);
            View good_habits_layout_circle_may27_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may27_in_mood);
            View good_habits_layout_circle_may28_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may28_in_mood);
            View good_habits_layout_circle_may29_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may29_in_mood);
            View good_habits_layout_circle_may30_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may30_in_mood);
            View good_habits_layout_circle_may31_in_mood = getView().findViewById(R.id.good_habits_layout_circle_may31_in_mood);
            View good_habits_layout_circle_jun1_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun1_in_mood);
            View good_habits_layout_circle_jun2_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun2_in_mood);
            View good_habits_layout_circle_jun3_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun3_in_mood);
            View good_habits_layout_circle_jun4_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun4_in_mood);
            View good_habits_layout_circle_jun5_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun5_in_mood);
            View good_habits_layout_circle_jun6_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun6_in_mood);
            View good_habits_layout_circle_jun7_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun7_in_mood);
            View good_habits_layout_circle_jun8_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun8_in_mood);
            View good_habits_layout_circle_jun9_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun9_in_mood);
            View good_habits_layout_circle_jun10_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun10_in_mood);
            View good_habits_layout_circle_jun11_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun11_in_mood);
            View good_habits_layout_circle_jun12_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun12_in_mood);
            View good_habits_layout_circle_jun13_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun13_in_mood);
            View good_habits_layout_circle_jun14_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun14_in_mood);
            View good_habits_layout_circle_jun15_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun15_in_mood);
            View good_habits_layout_circle_jun16_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun16_in_mood);
            View good_habits_layout_circle_jun17_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun17_in_mood);
            View good_habits_layout_circle_jun18_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun18_in_mood);
            View good_habits_layout_circle_jun19_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun19_in_mood);
            View good_habits_layout_circle_jun20_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun20_in_mood);
            View good_habits_layout_circle_jun21_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun21_in_mood);
            View good_habits_layout_circle_jun22_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun22_in_mood);
            View good_habits_layout_circle_jun23_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun23_in_mood);
            View good_habits_layout_circle_jun24_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun24_in_mood);
            View good_habits_layout_circle_jun25_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun25_in_mood);
            View good_habits_layout_circle_jun26_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun26_in_mood);
            View good_habits_layout_circle_jun27_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun27_in_mood);
            View good_habits_layout_circle_jun28_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun28_in_mood);
            View good_habits_layout_circle_jun29_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun29_in_mood);
            View good_habits_layout_circle_jun30_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jun30_in_mood);
            View good_habits_layout_circle_jul1_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul1_in_mood);
            View good_habits_layout_circle_jul2_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul2_in_mood);
            View good_habits_layout_circle_jul3_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul3_in_mood);
            View good_habits_layout_circle_jul4_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul4_in_mood);
            View good_habits_layout_circle_jul5_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul5_in_mood);
            View good_habits_layout_circle_jul6_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul6_in_mood);
            View good_habits_layout_circle_jul7_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul7_in_mood);
            View good_habits_layout_circle_jul8_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul8_in_mood);
            View good_habits_layout_circle_jul9_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul9_in_mood);
            View good_habits_layout_circle_jul10_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul10_in_mood);
            View good_habits_layout_circle_jul11_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul11_in_mood);
            View good_habits_layout_circle_jul12_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul12_in_mood);
            View good_habits_layout_circle_jul13_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul13_in_mood);
            View good_habits_layout_circle_jul14_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul14_in_mood);
            View good_habits_layout_circle_jul15_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul15_in_mood);
            View good_habits_layout_circle_jul16_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul16_in_mood);
            View good_habits_layout_circle_jul17_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul17_in_mood);
            View good_habits_layout_circle_jul18_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul18_in_mood);
            View good_habits_layout_circle_jul19_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul19_in_mood);
            View good_habits_layout_circle_jul20_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul20_in_mood);
            View good_habits_layout_circle_jul21_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul21_in_mood);
            View good_habits_layout_circle_jul22_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul22_in_mood);
            View good_habits_layout_circle_jul23_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul23_in_mood);
            View good_habits_layout_circle_jul24_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul24_in_mood);
            View good_habits_layout_circle_jul25_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul25_in_mood);
            View good_habits_layout_circle_jul26_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul26_in_mood);
            View good_habits_layout_circle_jul27_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul27_in_mood);
            View good_habits_layout_circle_jul28_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul28_in_mood);
            View good_habits_layout_circle_jul29_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul29_in_mood);
            View good_habits_layout_circle_jul30_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul30_in_mood);
            View good_habits_layout_circle_jul31_in_mood = getView().findViewById(R.id.good_habits_layout_circle_jul31_in_mood);
            View good_habits_layout_circle_aug1_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug1_in_mood);
            View good_habits_layout_circle_aug2_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug2_in_mood);
            View good_habits_layout_circle_aug3_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug3_in_mood);
            View good_habits_layout_circle_aug4_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug4_in_mood);
            View good_habits_layout_circle_aug5_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug5_in_mood);
            View good_habits_layout_circle_aug6_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug6_in_mood);
            View good_habits_layout_circle_aug7_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug7_in_mood);
            View good_habits_layout_circle_aug8_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug8_in_mood);
            View good_habits_layout_circle_aug9_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug9_in_mood);
            View good_habits_layout_circle_aug10_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug10_in_mood);
            View good_habits_layout_circle_aug11_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug11_in_mood);
            View good_habits_layout_circle_aug12_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug12_in_mood);
            View good_habits_layout_circle_aug13_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug13_in_mood);
            View good_habits_layout_circle_aug14_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug14_in_mood);
            View good_habits_layout_circle_aug15_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug15_in_mood);
            View good_habits_layout_circle_aug16_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug16_in_mood);
            View good_habits_layout_circle_aug17_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug17_in_mood);
            View good_habits_layout_circle_aug18_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug18_in_mood);
            View good_habits_layout_circle_aug19_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug19_in_mood);
            View good_habits_layout_circle_aug20_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug20_in_mood);
            View good_habits_layout_circle_aug21_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug21_in_mood);
            View good_habits_layout_circle_aug22_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug22_in_mood);
            View good_habits_layout_circle_aug23_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug23_in_mood);
            View good_habits_layout_circle_aug24_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug24_in_mood);
            View good_habits_layout_circle_aug25_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug25_in_mood);
            View good_habits_layout_circle_aug26_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug26_in_mood);
            View good_habits_layout_circle_aug27_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug27_in_mood);
            View good_habits_layout_circle_aug28_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug28_in_mood);
            View good_habits_layout_circle_aug29_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug29_in_mood);
            View good_habits_layout_circle_aug30_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug30_in_mood);
            View good_habits_layout_circle_aug31_in_mood = getView().findViewById(R.id.good_habits_layout_circle_aug31_in_mood);
            View good_habits_layout_circle_sep1_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep1_in_mood);
            View good_habits_layout_circle_sep2_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep2_in_mood);
            View good_habits_layout_circle_sep3_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep3_in_mood);
            View good_habits_layout_circle_sep4_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep4_in_mood);
            View good_habits_layout_circle_sep5_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep5_in_mood);
            View good_habits_layout_circle_sep6_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep6_in_mood);
            View good_habits_layout_circle_sep7_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep7_in_mood);
            View good_habits_layout_circle_sep8_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep8_in_mood);
            View good_habits_layout_circle_sep9_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep9_in_mood);
            View good_habits_layout_circle_sep10_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep10_in_mood);
            View good_habits_layout_circle_sep11_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep11_in_mood);
            View good_habits_layout_circle_sep12_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep12_in_mood);
            View good_habits_layout_circle_sep13_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep13_in_mood);
            View good_habits_layout_circle_sep14_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep14_in_mood);
            View good_habits_layout_circle_sep15_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep15_in_mood);
            View good_habits_layout_circle_sep16_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep16_in_mood);
            View good_habits_layout_circle_sep17_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep17_in_mood);
            View good_habits_layout_circle_sep18_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep18_in_mood);
            View good_habits_layout_circle_sep19_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep19_in_mood);
            View good_habits_layout_circle_sep20_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep20_in_mood);
            View good_habits_layout_circle_sep21_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep21_in_mood);
            View good_habits_layout_circle_sep22_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep22_in_mood);
            View good_habits_layout_circle_sep23_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep23_in_mood);
            View good_habits_layout_circle_sep24_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep24_in_mood);
            View good_habits_layout_circle_sep25_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep25_in_mood);
            View good_habits_layout_circle_sep26_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep26_in_mood);
            View good_habits_layout_circle_sep27_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep27_in_mood);
            View good_habits_layout_circle_sep28_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep28_in_mood);
            View good_habits_layout_circle_sep29_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep29_in_mood);
            View good_habits_layout_circle_sep30_in_mood = getView().findViewById(R.id.good_habits_layout_circle_sep30_in_mood);
            View good_habits_layout_circle_oct1_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct1_in_mood);
            View good_habits_layout_circle_oct2_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct2_in_mood);
            View good_habits_layout_circle_oct3_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct3_in_mood);
            View good_habits_layout_circle_oct4_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct4_in_mood);
            View good_habits_layout_circle_oct5_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct5_in_mood);
            View good_habits_layout_circle_oct6_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct6_in_mood);
            View good_habits_layout_circle_oct7_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct7_in_mood);
            View good_habits_layout_circle_oct8_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct8_in_mood);
            View good_habits_layout_circle_oct9_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct9_in_mood);
            View good_habits_layout_circle_oct10_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct10_in_mood);
            View good_habits_layout_circle_oct11_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct11_in_mood);
            View good_habits_layout_circle_oct12_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct12_in_mood);
            View good_habits_layout_circle_oct13_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct13_in_mood);
            View good_habits_layout_circle_oct14_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct14_in_mood);
            View good_habits_layout_circle_oct15_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct15_in_mood);
            View good_habits_layout_circle_oct16_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct16_in_mood);
            View good_habits_layout_circle_oct17_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct17_in_mood);
            View good_habits_layout_circle_oct18_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct18_in_mood);
            View good_habits_layout_circle_oct19_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct19_in_mood);
            View good_habits_layout_circle_oct20_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct20_in_mood);
            View good_habits_layout_circle_oct21_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct21_in_mood);
            View good_habits_layout_circle_oct22_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct22_in_mood);
            View good_habits_layout_circle_oct23_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct23_in_mood);
            View good_habits_layout_circle_oct24_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct24_in_mood);
            View good_habits_layout_circle_oct25_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct25_in_mood);
            View good_habits_layout_circle_oct26_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct26_in_mood);
            View good_habits_layout_circle_oct27_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct27_in_mood);
            View good_habits_layout_circle_oct28_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct28_in_mood);
            View good_habits_layout_circle_oct29_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct29_in_mood);
            View good_habits_layout_circle_oct30_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct30_in_mood);
            View good_habits_layout_circle_oct31_in_mood = getView().findViewById(R.id.good_habits_layout_circle_oct31_in_mood);
            View good_habits_layout_circle_nov1_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov1_in_mood);
            View good_habits_layout_circle_nov2_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov2_in_mood);
            View good_habits_layout_circle_nov3_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov3_in_mood);
            View good_habits_layout_circle_nov4_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov4_in_mood);
            View good_habits_layout_circle_nov5_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov5_in_mood);
            View good_habits_layout_circle_nov6_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov6_in_mood);
            View good_habits_layout_circle_nov7_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov7_in_mood);
            View good_habits_layout_circle_nov8_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov8_in_mood);
            View good_habits_layout_circle_nov9_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov9_in_mood);
            View good_habits_layout_circle_nov10_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov10_in_mood);
            View good_habits_layout_circle_nov11_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov11_in_mood);
            View good_habits_layout_circle_nov12_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov12_in_mood);
            View good_habits_layout_circle_nov13_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov13_in_mood);
            View good_habits_layout_circle_nov14_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov14_in_mood);
            View good_habits_layout_circle_nov15_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov15_in_mood);
            View good_habits_layout_circle_nov16_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov16_in_mood);
            View good_habits_layout_circle_nov17_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov17_in_mood);
            View good_habits_layout_circle_nov18_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov18_in_mood);
            View good_habits_layout_circle_nov19_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov19_in_mood);
            View good_habits_layout_circle_nov20_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov20_in_mood);
            View good_habits_layout_circle_nov21_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov21_in_mood);
            View good_habits_layout_circle_nov22_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov22_in_mood);
            View good_habits_layout_circle_nov23_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov23_in_mood);
            View good_habits_layout_circle_nov24_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov24_in_mood);
            View good_habits_layout_circle_nov25_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov25_in_mood);
            View good_habits_layout_circle_nov26_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov26_in_mood);
            View good_habits_layout_circle_nov27_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov27_in_mood);
            View good_habits_layout_circle_nov28_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov28_in_mood);
            View good_habits_layout_circle_nov29_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov29_in_mood);
            View good_habits_layout_circle_nov30_in_mood = getView().findViewById(R.id.good_habits_layout_circle_nov30_in_mood);
            View good_habits_layout_circle_dec1_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec1_in_mood);
            View good_habits_layout_circle_dec2_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec2_in_mood);
            View good_habits_layout_circle_dec3_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec3_in_mood);
            View good_habits_layout_circle_dec4_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec4_in_mood);
            View good_habits_layout_circle_dec5_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec5_in_mood);
            View good_habits_layout_circle_dec6_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec6_in_mood);
            View good_habits_layout_circle_dec7_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec7_in_mood);
            View good_habits_layout_circle_dec8_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec8_in_mood);
            View good_habits_layout_circle_dec9_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec9_in_mood);
            View good_habits_layout_circle_dec10_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec10_in_mood);
            View good_habits_layout_circle_dec11_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec11_in_mood);
            View good_habits_layout_circle_dec12_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec12_in_mood);
            View good_habits_layout_circle_dec13_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec13_in_mood);
            View good_habits_layout_circle_dec14_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec14_in_mood);
            View good_habits_layout_circle_dec15_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec15_in_mood);
            View good_habits_layout_circle_dec16_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec16_in_mood);
            View good_habits_layout_circle_dec17_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec17_in_mood);
            View good_habits_layout_circle_dec18_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec18_in_mood);
            View good_habits_layout_circle_dec19_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec19_in_mood);
            View good_habits_layout_circle_dec20_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec20_in_mood);
            View good_habits_layout_circle_dec21_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec21_in_mood);
            View good_habits_layout_circle_dec22_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec22_in_mood);
            View good_habits_layout_circle_dec23_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec23_in_mood);
            View good_habits_layout_circle_dec24_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec24_in_mood);
            View good_habits_layout_circle_dec25_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec25_in_mood);
            View good_habits_layout_circle_dec26_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec26_in_mood);
            View good_habits_layout_circle_dec27_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec27_in_mood);
            View good_habits_layout_circle_dec28_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec28_in_mood);
            View good_habits_layout_circle_dec29_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec29_in_mood);
            View good_habits_layout_circle_dec30_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec30_in_mood);
            View good_habits_layout_circle_dec31_in_mood = getView().findViewById(R.id.good_habits_layout_circle_dec31_in_mood);
            list_of_all_the_calender_views_in_mood = new View[366];
            list_of_all_the_calender_views_in_mood[0] = good_habits_layout_circle_jan1_in_mood;
            list_of_all_the_calender_views_in_mood[1] = good_habits_layout_circle_jan2_in_mood;
            list_of_all_the_calender_views_in_mood[2] = good_habits_layout_circle_jan3_in_mood;
            list_of_all_the_calender_views_in_mood[3] = good_habits_layout_circle_jan4_in_mood;
            list_of_all_the_calender_views_in_mood[4] = good_habits_layout_circle_jan5_in_mood;
            list_of_all_the_calender_views_in_mood[5] = good_habits_layout_circle_jan6_in_mood;
            list_of_all_the_calender_views_in_mood[6] = good_habits_layout_circle_jan7_in_mood;
            list_of_all_the_calender_views_in_mood[7] = good_habits_layout_circle_jan8_in_mood;
            list_of_all_the_calender_views_in_mood[8] = good_habits_layout_circle_jan9_in_mood;
            list_of_all_the_calender_views_in_mood[9] = good_habits_layout_circle_jan10_in_mood;
            list_of_all_the_calender_views_in_mood[10] = good_habits_layout_circle_jan11_in_mood;
            list_of_all_the_calender_views_in_mood[11] = good_habits_layout_circle_jan12_in_mood;
            list_of_all_the_calender_views_in_mood[12] = good_habits_layout_circle_jan13_in_mood;
            list_of_all_the_calender_views_in_mood[13] = good_habits_layout_circle_jan14_in_mood;
            list_of_all_the_calender_views_in_mood[14] = good_habits_layout_circle_jan15_in_mood;
            list_of_all_the_calender_views_in_mood[15] = good_habits_layout_circle_jan16_in_mood;
            list_of_all_the_calender_views_in_mood[16] = good_habits_layout_circle_jan17_in_mood;
            list_of_all_the_calender_views_in_mood[17] = good_habits_layout_circle_jan18_in_mood;
            list_of_all_the_calender_views_in_mood[18] = good_habits_layout_circle_jan19_in_mood;
            list_of_all_the_calender_views_in_mood[19] = good_habits_layout_circle_jan20_in_mood;
            list_of_all_the_calender_views_in_mood[20] = good_habits_layout_circle_jan21_in_mood;
            list_of_all_the_calender_views_in_mood[21] = good_habits_layout_circle_jan22_in_mood;
            list_of_all_the_calender_views_in_mood[22] = good_habits_layout_circle_jan23_in_mood;
            list_of_all_the_calender_views_in_mood[23] = good_habits_layout_circle_jan24_in_mood;
            list_of_all_the_calender_views_in_mood[24] = good_habits_layout_circle_jan25_in_mood;
            list_of_all_the_calender_views_in_mood[25] = good_habits_layout_circle_jan26_in_mood;
            list_of_all_the_calender_views_in_mood[26] = good_habits_layout_circle_jan27_in_mood;
            list_of_all_the_calender_views_in_mood[27] = good_habits_layout_circle_jan28_in_mood;
            list_of_all_the_calender_views_in_mood[28] = good_habits_layout_circle_jan29_in_mood;
            list_of_all_the_calender_views_in_mood[29] = good_habits_layout_circle_jan30_in_mood;
            list_of_all_the_calender_views_in_mood[30] = good_habits_layout_circle_jan31_in_mood;
            list_of_all_the_calender_views_in_mood[31] = good_habits_layout_circle_feb1_in_mood;
            list_of_all_the_calender_views_in_mood[32] = good_habits_layout_circle_feb2_in_mood;
            list_of_all_the_calender_views_in_mood[33] = good_habits_layout_circle_feb3_in_mood;
            list_of_all_the_calender_views_in_mood[34] = good_habits_layout_circle_feb4_in_mood;
            list_of_all_the_calender_views_in_mood[35] = good_habits_layout_circle_feb5_in_mood;
            list_of_all_the_calender_views_in_mood[36] = good_habits_layout_circle_feb6_in_mood;
            list_of_all_the_calender_views_in_mood[37] = good_habits_layout_circle_feb7_in_mood;
            list_of_all_the_calender_views_in_mood[38] = good_habits_layout_circle_feb8_in_mood;
            list_of_all_the_calender_views_in_mood[39] = good_habits_layout_circle_feb9_in_mood;
            list_of_all_the_calender_views_in_mood[40] = good_habits_layout_circle_feb10_in_mood;
            list_of_all_the_calender_views_in_mood[41] = good_habits_layout_circle_feb11_in_mood;
            list_of_all_the_calender_views_in_mood[42] = good_habits_layout_circle_feb12_in_mood;
            list_of_all_the_calender_views_in_mood[43] = good_habits_layout_circle_feb13_in_mood;
            list_of_all_the_calender_views_in_mood[44] = good_habits_layout_circle_feb14_in_mood;
            list_of_all_the_calender_views_in_mood[45] = good_habits_layout_circle_feb15_in_mood;
            list_of_all_the_calender_views_in_mood[46] = good_habits_layout_circle_feb16_in_mood;
            list_of_all_the_calender_views_in_mood[47] = good_habits_layout_circle_feb17_in_mood;
            list_of_all_the_calender_views_in_mood[48] = good_habits_layout_circle_feb18_in_mood;
            list_of_all_the_calender_views_in_mood[49] = good_habits_layout_circle_feb19_in_mood;
            list_of_all_the_calender_views_in_mood[50] = good_habits_layout_circle_feb20_in_mood;
            list_of_all_the_calender_views_in_mood[51] = good_habits_layout_circle_feb21_in_mood;
            list_of_all_the_calender_views_in_mood[52] = good_habits_layout_circle_feb22_in_mood;
            list_of_all_the_calender_views_in_mood[53] = good_habits_layout_circle_feb23_in_mood;
            list_of_all_the_calender_views_in_mood[54] = good_habits_layout_circle_feb24_in_mood;
            list_of_all_the_calender_views_in_mood[55] = good_habits_layout_circle_feb25_in_mood;
            list_of_all_the_calender_views_in_mood[56] = good_habits_layout_circle_feb26_in_mood;
            list_of_all_the_calender_views_in_mood[57] = good_habits_layout_circle_feb27_in_mood;
            list_of_all_the_calender_views_in_mood[58] = good_habits_layout_circle_feb28_in_mood;
            list_of_all_the_calender_views_in_mood[59] = good_habits_layout_circle_feb29_in_mood;
            list_of_all_the_calender_views_in_mood[60] = good_habits_layout_circle_mar1_in_mood;
            list_of_all_the_calender_views_in_mood[61] = good_habits_layout_circle_mar2_in_mood;
            list_of_all_the_calender_views_in_mood[62] = good_habits_layout_circle_mar3_in_mood;
            list_of_all_the_calender_views_in_mood[63] = good_habits_layout_circle_mar4_in_mood;
            list_of_all_the_calender_views_in_mood[64] = good_habits_layout_circle_mar5_in_mood;
            list_of_all_the_calender_views_in_mood[65] = good_habits_layout_circle_mar6_in_mood;
            list_of_all_the_calender_views_in_mood[66] = good_habits_layout_circle_mar7_in_mood;
            list_of_all_the_calender_views_in_mood[67] = good_habits_layout_circle_mar8_in_mood;
            list_of_all_the_calender_views_in_mood[68] = good_habits_layout_circle_mar9_in_mood;
            list_of_all_the_calender_views_in_mood[69] = good_habits_layout_circle_mar10_in_mood;
            list_of_all_the_calender_views_in_mood[70] = good_habits_layout_circle_mar11_in_mood;
            list_of_all_the_calender_views_in_mood[71] = good_habits_layout_circle_mar12_in_mood;
            list_of_all_the_calender_views_in_mood[72] = good_habits_layout_circle_mar13_in_mood;
            list_of_all_the_calender_views_in_mood[73] = good_habits_layout_circle_mar14_in_mood;
            list_of_all_the_calender_views_in_mood[74] = good_habits_layout_circle_mar15_in_mood;
            list_of_all_the_calender_views_in_mood[75] = good_habits_layout_circle_mar16_in_mood;
            list_of_all_the_calender_views_in_mood[76] = good_habits_layout_circle_mar17_in_mood;
            list_of_all_the_calender_views_in_mood[77] = good_habits_layout_circle_mar18_in_mood;
            list_of_all_the_calender_views_in_mood[78] = good_habits_layout_circle_mar19_in_mood;
            list_of_all_the_calender_views_in_mood[79] = good_habits_layout_circle_mar20_in_mood;
            list_of_all_the_calender_views_in_mood[80] = good_habits_layout_circle_mar21_in_mood;
            list_of_all_the_calender_views_in_mood[81] = good_habits_layout_circle_mar22_in_mood;
            list_of_all_the_calender_views_in_mood[82] = good_habits_layout_circle_mar23_in_mood;
            list_of_all_the_calender_views_in_mood[83] = good_habits_layout_circle_mar24_in_mood;
            list_of_all_the_calender_views_in_mood[84] = good_habits_layout_circle_mar25_in_mood;
            list_of_all_the_calender_views_in_mood[85] = good_habits_layout_circle_mar26_in_mood;
            list_of_all_the_calender_views_in_mood[86] = good_habits_layout_circle_mar27_in_mood;
            list_of_all_the_calender_views_in_mood[87] = good_habits_layout_circle_mar28_in_mood;
            list_of_all_the_calender_views_in_mood[88] = good_habits_layout_circle_mar29_in_mood;
            list_of_all_the_calender_views_in_mood[89] = good_habits_layout_circle_mar30_in_mood;
            list_of_all_the_calender_views_in_mood[90] = good_habits_layout_circle_mar31_in_mood;
            list_of_all_the_calender_views_in_mood[91] = good_habits_layout_circle_apr1_in_mood;
            list_of_all_the_calender_views_in_mood[92] = good_habits_layout_circle_apr2_in_mood;
            list_of_all_the_calender_views_in_mood[93] = good_habits_layout_circle_apr3_in_mood;
            list_of_all_the_calender_views_in_mood[94] = good_habits_layout_circle_apr4_in_mood;
            list_of_all_the_calender_views_in_mood[95] = good_habits_layout_circle_apr5_in_mood;
            list_of_all_the_calender_views_in_mood[96] = good_habits_layout_circle_apr6_in_mood;
            list_of_all_the_calender_views_in_mood[97] = good_habits_layout_circle_apr7_in_mood;
            list_of_all_the_calender_views_in_mood[98] = good_habits_layout_circle_apr8_in_mood;
            list_of_all_the_calender_views_in_mood[99] = good_habits_layout_circle_apr9_in_mood;
            list_of_all_the_calender_views_in_mood[100] = good_habits_layout_circle_apr10_in_mood;
            list_of_all_the_calender_views_in_mood[101] = good_habits_layout_circle_apr11_in_mood;
            list_of_all_the_calender_views_in_mood[102] = good_habits_layout_circle_apr12_in_mood;
            list_of_all_the_calender_views_in_mood[103] = good_habits_layout_circle_apr13_in_mood;
            list_of_all_the_calender_views_in_mood[104] = good_habits_layout_circle_apr14_in_mood;
            list_of_all_the_calender_views_in_mood[105] = good_habits_layout_circle_apr15_in_mood;
            list_of_all_the_calender_views_in_mood[106] = good_habits_layout_circle_apr16_in_mood;
            list_of_all_the_calender_views_in_mood[107] = good_habits_layout_circle_apr17_in_mood;
            list_of_all_the_calender_views_in_mood[108] = good_habits_layout_circle_apr18_in_mood;
            list_of_all_the_calender_views_in_mood[109] = good_habits_layout_circle_apr19_in_mood;
            list_of_all_the_calender_views_in_mood[110] = good_habits_layout_circle_apr20_in_mood;
            list_of_all_the_calender_views_in_mood[111] = good_habits_layout_circle_apr21_in_mood;
            list_of_all_the_calender_views_in_mood[112] = good_habits_layout_circle_apr22_in_mood;
            list_of_all_the_calender_views_in_mood[113] = good_habits_layout_circle_apr23_in_mood;
            list_of_all_the_calender_views_in_mood[114] = good_habits_layout_circle_apr24_in_mood;
            list_of_all_the_calender_views_in_mood[115] = good_habits_layout_circle_apr25_in_mood;
            list_of_all_the_calender_views_in_mood[116] = good_habits_layout_circle_apr26_in_mood;
            list_of_all_the_calender_views_in_mood[117] = good_habits_layout_circle_apr27_in_mood;
            list_of_all_the_calender_views_in_mood[118] = good_habits_layout_circle_apr28_in_mood;
            list_of_all_the_calender_views_in_mood[119] = good_habits_layout_circle_apr29_in_mood;
            list_of_all_the_calender_views_in_mood[120] = good_habits_layout_circle_apr30_in_mood;
            list_of_all_the_calender_views_in_mood[121] = good_habits_layout_circle_may1_in_mood;
            list_of_all_the_calender_views_in_mood[122] = good_habits_layout_circle_may2_in_mood;
            list_of_all_the_calender_views_in_mood[123] = good_habits_layout_circle_may3_in_mood;
            list_of_all_the_calender_views_in_mood[124] = good_habits_layout_circle_may4_in_mood;
            list_of_all_the_calender_views_in_mood[125] = good_habits_layout_circle_may5_in_mood;
            list_of_all_the_calender_views_in_mood[126] = good_habits_layout_circle_may6_in_mood;
            list_of_all_the_calender_views_in_mood[127] = good_habits_layout_circle_may7_in_mood;
            list_of_all_the_calender_views_in_mood[128] = good_habits_layout_circle_may8_in_mood;
            list_of_all_the_calender_views_in_mood[129] = good_habits_layout_circle_may9_in_mood;
            list_of_all_the_calender_views_in_mood[130] = good_habits_layout_circle_may10_in_mood;
            list_of_all_the_calender_views_in_mood[131] = good_habits_layout_circle_may11_in_mood;
            list_of_all_the_calender_views_in_mood[132] = good_habits_layout_circle_may12_in_mood;
            list_of_all_the_calender_views_in_mood[133] = good_habits_layout_circle_may13_in_mood;
            list_of_all_the_calender_views_in_mood[134] = good_habits_layout_circle_may14_in_mood;
            list_of_all_the_calender_views_in_mood[135] = good_habits_layout_circle_may15_in_mood;
            list_of_all_the_calender_views_in_mood[136] = good_habits_layout_circle_may16_in_mood;
            list_of_all_the_calender_views_in_mood[137] = good_habits_layout_circle_may17_in_mood;
            list_of_all_the_calender_views_in_mood[138] = good_habits_layout_circle_may18_in_mood;
            list_of_all_the_calender_views_in_mood[139] = good_habits_layout_circle_may19_in_mood;
            list_of_all_the_calender_views_in_mood[140] = good_habits_layout_circle_may20_in_mood;
            list_of_all_the_calender_views_in_mood[141] = good_habits_layout_circle_may21_in_mood;
            list_of_all_the_calender_views_in_mood[142] = good_habits_layout_circle_may22_in_mood;
            list_of_all_the_calender_views_in_mood[143] = good_habits_layout_circle_may23_in_mood;
            list_of_all_the_calender_views_in_mood[144] = good_habits_layout_circle_may24_in_mood;
            list_of_all_the_calender_views_in_mood[145] = good_habits_layout_circle_may25_in_mood;
            list_of_all_the_calender_views_in_mood[146] = good_habits_layout_circle_may26_in_mood;
            list_of_all_the_calender_views_in_mood[147] = good_habits_layout_circle_may27_in_mood;
            list_of_all_the_calender_views_in_mood[148] = good_habits_layout_circle_may28_in_mood;
            list_of_all_the_calender_views_in_mood[149] = good_habits_layout_circle_may29_in_mood;
            list_of_all_the_calender_views_in_mood[150] = good_habits_layout_circle_may30_in_mood;
            list_of_all_the_calender_views_in_mood[151] = good_habits_layout_circle_may31_in_mood;
            list_of_all_the_calender_views_in_mood[152] = good_habits_layout_circle_jun1_in_mood;
            list_of_all_the_calender_views_in_mood[153] = good_habits_layout_circle_jun2_in_mood;
            list_of_all_the_calender_views_in_mood[154] = good_habits_layout_circle_jun3_in_mood;
            list_of_all_the_calender_views_in_mood[155] = good_habits_layout_circle_jun4_in_mood;
            list_of_all_the_calender_views_in_mood[156] = good_habits_layout_circle_jun5_in_mood;
            list_of_all_the_calender_views_in_mood[157] = good_habits_layout_circle_jun6_in_mood;
            list_of_all_the_calender_views_in_mood[158] = good_habits_layout_circle_jun7_in_mood;
            list_of_all_the_calender_views_in_mood[159] = good_habits_layout_circle_jun8_in_mood;
            list_of_all_the_calender_views_in_mood[160] = good_habits_layout_circle_jun9_in_mood;
            list_of_all_the_calender_views_in_mood[161] = good_habits_layout_circle_jun10_in_mood;
            list_of_all_the_calender_views_in_mood[162] = good_habits_layout_circle_jun11_in_mood;
            list_of_all_the_calender_views_in_mood[163] = good_habits_layout_circle_jun12_in_mood;
            list_of_all_the_calender_views_in_mood[164] = good_habits_layout_circle_jun13_in_mood;
            list_of_all_the_calender_views_in_mood[165] = good_habits_layout_circle_jun14_in_mood;
            list_of_all_the_calender_views_in_mood[166] = good_habits_layout_circle_jun15_in_mood;
            list_of_all_the_calender_views_in_mood[167] = good_habits_layout_circle_jun16_in_mood;
            list_of_all_the_calender_views_in_mood[168] = good_habits_layout_circle_jun17_in_mood;
            list_of_all_the_calender_views_in_mood[169] = good_habits_layout_circle_jun18_in_mood;
            list_of_all_the_calender_views_in_mood[170] = good_habits_layout_circle_jun19_in_mood;
            list_of_all_the_calender_views_in_mood[171] = good_habits_layout_circle_jun20_in_mood;
            list_of_all_the_calender_views_in_mood[172] = good_habits_layout_circle_jun21_in_mood;
            list_of_all_the_calender_views_in_mood[173] = good_habits_layout_circle_jun22_in_mood;
            list_of_all_the_calender_views_in_mood[174] = good_habits_layout_circle_jun23_in_mood;
            list_of_all_the_calender_views_in_mood[175] = good_habits_layout_circle_jun24_in_mood;
            list_of_all_the_calender_views_in_mood[176] = good_habits_layout_circle_jun25_in_mood;
            list_of_all_the_calender_views_in_mood[177] = good_habits_layout_circle_jun26_in_mood;
            list_of_all_the_calender_views_in_mood[178] = good_habits_layout_circle_jun27_in_mood;
            list_of_all_the_calender_views_in_mood[179] = good_habits_layout_circle_jun28_in_mood;
            list_of_all_the_calender_views_in_mood[180] = good_habits_layout_circle_jun29_in_mood;
            list_of_all_the_calender_views_in_mood[181] = good_habits_layout_circle_jun30_in_mood;
            list_of_all_the_calender_views_in_mood[182] = good_habits_layout_circle_jul1_in_mood;
            list_of_all_the_calender_views_in_mood[183] = good_habits_layout_circle_jul2_in_mood;
            list_of_all_the_calender_views_in_mood[184] = good_habits_layout_circle_jul3_in_mood;
            list_of_all_the_calender_views_in_mood[185] = good_habits_layout_circle_jul4_in_mood;
            list_of_all_the_calender_views_in_mood[186] = good_habits_layout_circle_jul5_in_mood;
            list_of_all_the_calender_views_in_mood[187] = good_habits_layout_circle_jul6_in_mood;
            list_of_all_the_calender_views_in_mood[188] = good_habits_layout_circle_jul7_in_mood;
            list_of_all_the_calender_views_in_mood[189] = good_habits_layout_circle_jul8_in_mood;
            list_of_all_the_calender_views_in_mood[190] = good_habits_layout_circle_jul9_in_mood;
            list_of_all_the_calender_views_in_mood[191] = good_habits_layout_circle_jul10_in_mood;
            list_of_all_the_calender_views_in_mood[192] = good_habits_layout_circle_jul11_in_mood;
            list_of_all_the_calender_views_in_mood[193] = good_habits_layout_circle_jul12_in_mood;
            list_of_all_the_calender_views_in_mood[194] = good_habits_layout_circle_jul13_in_mood;
            list_of_all_the_calender_views_in_mood[195] = good_habits_layout_circle_jul14_in_mood;
            list_of_all_the_calender_views_in_mood[196] = good_habits_layout_circle_jul15_in_mood;
            list_of_all_the_calender_views_in_mood[197] = good_habits_layout_circle_jul16_in_mood;
            list_of_all_the_calender_views_in_mood[198] = good_habits_layout_circle_jul17_in_mood;
            list_of_all_the_calender_views_in_mood[199] = good_habits_layout_circle_jul18_in_mood;
            list_of_all_the_calender_views_in_mood[200] = good_habits_layout_circle_jul19_in_mood;
            list_of_all_the_calender_views_in_mood[201] = good_habits_layout_circle_jul20_in_mood;
            list_of_all_the_calender_views_in_mood[202] = good_habits_layout_circle_jul21_in_mood;
            list_of_all_the_calender_views_in_mood[203] = good_habits_layout_circle_jul22_in_mood;
            list_of_all_the_calender_views_in_mood[204] = good_habits_layout_circle_jul23_in_mood;
            list_of_all_the_calender_views_in_mood[205] = good_habits_layout_circle_jul24_in_mood;
            list_of_all_the_calender_views_in_mood[206] = good_habits_layout_circle_jul25_in_mood;
            list_of_all_the_calender_views_in_mood[207] = good_habits_layout_circle_jul26_in_mood;
            list_of_all_the_calender_views_in_mood[208] = good_habits_layout_circle_jul27_in_mood;
            list_of_all_the_calender_views_in_mood[209] = good_habits_layout_circle_jul28_in_mood;
            list_of_all_the_calender_views_in_mood[210] = good_habits_layout_circle_jul29_in_mood;
            list_of_all_the_calender_views_in_mood[211] = good_habits_layout_circle_jul30_in_mood;
            list_of_all_the_calender_views_in_mood[212] = good_habits_layout_circle_jul31_in_mood;
            list_of_all_the_calender_views_in_mood[213] = good_habits_layout_circle_aug1_in_mood;
            list_of_all_the_calender_views_in_mood[214] = good_habits_layout_circle_aug2_in_mood;
            list_of_all_the_calender_views_in_mood[215] = good_habits_layout_circle_aug3_in_mood;
            list_of_all_the_calender_views_in_mood[216] = good_habits_layout_circle_aug4_in_mood;
            list_of_all_the_calender_views_in_mood[217] = good_habits_layout_circle_aug5_in_mood;
            list_of_all_the_calender_views_in_mood[218] = good_habits_layout_circle_aug6_in_mood;
            list_of_all_the_calender_views_in_mood[219] = good_habits_layout_circle_aug7_in_mood;
            list_of_all_the_calender_views_in_mood[220] = good_habits_layout_circle_aug8_in_mood;
            list_of_all_the_calender_views_in_mood[221] = good_habits_layout_circle_aug9_in_mood;
            list_of_all_the_calender_views_in_mood[222] = good_habits_layout_circle_aug10_in_mood;
            list_of_all_the_calender_views_in_mood[223] = good_habits_layout_circle_aug11_in_mood;
            list_of_all_the_calender_views_in_mood[224] = good_habits_layout_circle_aug12_in_mood;
            list_of_all_the_calender_views_in_mood[225] = good_habits_layout_circle_aug13_in_mood;
            list_of_all_the_calender_views_in_mood[226] = good_habits_layout_circle_aug14_in_mood;
            list_of_all_the_calender_views_in_mood[227] = good_habits_layout_circle_aug15_in_mood;
            list_of_all_the_calender_views_in_mood[228] = good_habits_layout_circle_aug16_in_mood;
            list_of_all_the_calender_views_in_mood[229] = good_habits_layout_circle_aug17_in_mood;
            list_of_all_the_calender_views_in_mood[230] = good_habits_layout_circle_aug18_in_mood;
            list_of_all_the_calender_views_in_mood[231] = good_habits_layout_circle_aug19_in_mood;
            list_of_all_the_calender_views_in_mood[232] = good_habits_layout_circle_aug20_in_mood;
            list_of_all_the_calender_views_in_mood[233] = good_habits_layout_circle_aug21_in_mood;
            list_of_all_the_calender_views_in_mood[234] = good_habits_layout_circle_aug22_in_mood;
            list_of_all_the_calender_views_in_mood[235] = good_habits_layout_circle_aug23_in_mood;
            list_of_all_the_calender_views_in_mood[236] = good_habits_layout_circle_aug24_in_mood;
            list_of_all_the_calender_views_in_mood[237] = good_habits_layout_circle_aug25_in_mood;
            list_of_all_the_calender_views_in_mood[238] = good_habits_layout_circle_aug26_in_mood;
            list_of_all_the_calender_views_in_mood[239] = good_habits_layout_circle_aug27_in_mood;
            list_of_all_the_calender_views_in_mood[240] = good_habits_layout_circle_aug28_in_mood;
            list_of_all_the_calender_views_in_mood[241] = good_habits_layout_circle_aug29_in_mood;
            list_of_all_the_calender_views_in_mood[242] = good_habits_layout_circle_aug30_in_mood;
            list_of_all_the_calender_views_in_mood[243] = good_habits_layout_circle_aug31_in_mood;
            list_of_all_the_calender_views_in_mood[244] = good_habits_layout_circle_sep1_in_mood;
            list_of_all_the_calender_views_in_mood[245] = good_habits_layout_circle_sep2_in_mood;
            list_of_all_the_calender_views_in_mood[246] = good_habits_layout_circle_sep3_in_mood;
            list_of_all_the_calender_views_in_mood[247] = good_habits_layout_circle_sep4_in_mood;
            list_of_all_the_calender_views_in_mood[248] = good_habits_layout_circle_sep5_in_mood;
            list_of_all_the_calender_views_in_mood[249] = good_habits_layout_circle_sep6_in_mood;
            list_of_all_the_calender_views_in_mood[250] = good_habits_layout_circle_sep7_in_mood;
            list_of_all_the_calender_views_in_mood[251] = good_habits_layout_circle_sep8_in_mood;
            list_of_all_the_calender_views_in_mood[252] = good_habits_layout_circle_sep9_in_mood;
            list_of_all_the_calender_views_in_mood[253] = good_habits_layout_circle_sep10_in_mood;
            list_of_all_the_calender_views_in_mood[254] = good_habits_layout_circle_sep11_in_mood;
            list_of_all_the_calender_views_in_mood[255] = good_habits_layout_circle_sep12_in_mood;
            list_of_all_the_calender_views_in_mood[256] = good_habits_layout_circle_sep13_in_mood;
            list_of_all_the_calender_views_in_mood[257] = good_habits_layout_circle_sep14_in_mood;
            list_of_all_the_calender_views_in_mood[258] = good_habits_layout_circle_sep15_in_mood;
            list_of_all_the_calender_views_in_mood[259] = good_habits_layout_circle_sep16_in_mood;
            list_of_all_the_calender_views_in_mood[260] = good_habits_layout_circle_sep17_in_mood;
            list_of_all_the_calender_views_in_mood[261] = good_habits_layout_circle_sep18_in_mood;
            list_of_all_the_calender_views_in_mood[262] = good_habits_layout_circle_sep19_in_mood;
            list_of_all_the_calender_views_in_mood[263] = good_habits_layout_circle_sep20_in_mood;
            list_of_all_the_calender_views_in_mood[264] = good_habits_layout_circle_sep21_in_mood;
            list_of_all_the_calender_views_in_mood[265] = good_habits_layout_circle_sep22_in_mood;
            list_of_all_the_calender_views_in_mood[266] = good_habits_layout_circle_sep23_in_mood;
            list_of_all_the_calender_views_in_mood[267] = good_habits_layout_circle_sep24_in_mood;
            list_of_all_the_calender_views_in_mood[268] = good_habits_layout_circle_sep25_in_mood;
            list_of_all_the_calender_views_in_mood[269] = good_habits_layout_circle_sep26_in_mood;
            list_of_all_the_calender_views_in_mood[270] = good_habits_layout_circle_sep27_in_mood;
            list_of_all_the_calender_views_in_mood[271] = good_habits_layout_circle_sep28_in_mood;
            list_of_all_the_calender_views_in_mood[272] = good_habits_layout_circle_sep29_in_mood;
            list_of_all_the_calender_views_in_mood[273] = good_habits_layout_circle_sep30_in_mood;
            list_of_all_the_calender_views_in_mood[274] = good_habits_layout_circle_oct1_in_mood;
            list_of_all_the_calender_views_in_mood[275] = good_habits_layout_circle_oct2_in_mood;
            list_of_all_the_calender_views_in_mood[276] = good_habits_layout_circle_oct3_in_mood;
            list_of_all_the_calender_views_in_mood[277] = good_habits_layout_circle_oct4_in_mood;
            list_of_all_the_calender_views_in_mood[278] = good_habits_layout_circle_oct5_in_mood;
            list_of_all_the_calender_views_in_mood[279] = good_habits_layout_circle_oct6_in_mood;
            list_of_all_the_calender_views_in_mood[280] = good_habits_layout_circle_oct7_in_mood;
            list_of_all_the_calender_views_in_mood[281] = good_habits_layout_circle_oct8_in_mood;
            list_of_all_the_calender_views_in_mood[282] = good_habits_layout_circle_oct9_in_mood;
            list_of_all_the_calender_views_in_mood[283] = good_habits_layout_circle_oct10_in_mood;
            list_of_all_the_calender_views_in_mood[284] = good_habits_layout_circle_oct11_in_mood;
            list_of_all_the_calender_views_in_mood[285] = good_habits_layout_circle_oct12_in_mood;
            list_of_all_the_calender_views_in_mood[286] = good_habits_layout_circle_oct13_in_mood;
            list_of_all_the_calender_views_in_mood[287] = good_habits_layout_circle_oct14_in_mood;
            list_of_all_the_calender_views_in_mood[288] = good_habits_layout_circle_oct15_in_mood;
            list_of_all_the_calender_views_in_mood[289] = good_habits_layout_circle_oct16_in_mood;
            list_of_all_the_calender_views_in_mood[290] = good_habits_layout_circle_oct17_in_mood;
            list_of_all_the_calender_views_in_mood[291] = good_habits_layout_circle_oct18_in_mood;
            list_of_all_the_calender_views_in_mood[292] = good_habits_layout_circle_oct19_in_mood;
            list_of_all_the_calender_views_in_mood[293] = good_habits_layout_circle_oct20_in_mood;
            list_of_all_the_calender_views_in_mood[294] = good_habits_layout_circle_oct21_in_mood;
            list_of_all_the_calender_views_in_mood[295] = good_habits_layout_circle_oct22_in_mood;
            list_of_all_the_calender_views_in_mood[296] = good_habits_layout_circle_oct23_in_mood;
            list_of_all_the_calender_views_in_mood[297] = good_habits_layout_circle_oct24_in_mood;
            list_of_all_the_calender_views_in_mood[298] = good_habits_layout_circle_oct25_in_mood;
            list_of_all_the_calender_views_in_mood[299] = good_habits_layout_circle_oct26_in_mood;
            list_of_all_the_calender_views_in_mood[300] = good_habits_layout_circle_oct27_in_mood;
            list_of_all_the_calender_views_in_mood[301] = good_habits_layout_circle_oct28_in_mood;
            list_of_all_the_calender_views_in_mood[302] = good_habits_layout_circle_oct29_in_mood;
            list_of_all_the_calender_views_in_mood[303] = good_habits_layout_circle_oct30_in_mood;
            list_of_all_the_calender_views_in_mood[304] = good_habits_layout_circle_oct31_in_mood;
            list_of_all_the_calender_views_in_mood[305] = good_habits_layout_circle_nov1_in_mood;
            list_of_all_the_calender_views_in_mood[306] = good_habits_layout_circle_nov2_in_mood;
            list_of_all_the_calender_views_in_mood[307] = good_habits_layout_circle_nov3_in_mood;
            list_of_all_the_calender_views_in_mood[308] = good_habits_layout_circle_nov4_in_mood;
            list_of_all_the_calender_views_in_mood[309] = good_habits_layout_circle_nov5_in_mood;
            list_of_all_the_calender_views_in_mood[310] = good_habits_layout_circle_nov6_in_mood;
            list_of_all_the_calender_views_in_mood[311] = good_habits_layout_circle_nov7_in_mood;
            list_of_all_the_calender_views_in_mood[312] = good_habits_layout_circle_nov8_in_mood;
            list_of_all_the_calender_views_in_mood[313] = good_habits_layout_circle_nov9_in_mood;
            list_of_all_the_calender_views_in_mood[314] = good_habits_layout_circle_nov10_in_mood;
            list_of_all_the_calender_views_in_mood[315] = good_habits_layout_circle_nov11_in_mood;
            list_of_all_the_calender_views_in_mood[316] = good_habits_layout_circle_nov12_in_mood;
            list_of_all_the_calender_views_in_mood[317] = good_habits_layout_circle_nov13_in_mood;
            list_of_all_the_calender_views_in_mood[318] = good_habits_layout_circle_nov14_in_mood;
            list_of_all_the_calender_views_in_mood[319] = good_habits_layout_circle_nov15_in_mood;
            list_of_all_the_calender_views_in_mood[320] = good_habits_layout_circle_nov16_in_mood;
            list_of_all_the_calender_views_in_mood[321] = good_habits_layout_circle_nov17_in_mood;
            list_of_all_the_calender_views_in_mood[322] = good_habits_layout_circle_nov18_in_mood;
            list_of_all_the_calender_views_in_mood[323] = good_habits_layout_circle_nov19_in_mood;
            list_of_all_the_calender_views_in_mood[324] = good_habits_layout_circle_nov20_in_mood;
            list_of_all_the_calender_views_in_mood[325] = good_habits_layout_circle_nov21_in_mood;
            list_of_all_the_calender_views_in_mood[326] = good_habits_layout_circle_nov22_in_mood;
            list_of_all_the_calender_views_in_mood[327] = good_habits_layout_circle_nov23_in_mood;
            list_of_all_the_calender_views_in_mood[328] = good_habits_layout_circle_nov24_in_mood;
            list_of_all_the_calender_views_in_mood[329] = good_habits_layout_circle_nov25_in_mood;
            list_of_all_the_calender_views_in_mood[330] = good_habits_layout_circle_nov26_in_mood;
            list_of_all_the_calender_views_in_mood[331] = good_habits_layout_circle_nov27_in_mood;
            list_of_all_the_calender_views_in_mood[332] = good_habits_layout_circle_nov28_in_mood;
            list_of_all_the_calender_views_in_mood[333] = good_habits_layout_circle_nov29_in_mood;
            list_of_all_the_calender_views_in_mood[334] = good_habits_layout_circle_nov30_in_mood;
            list_of_all_the_calender_views_in_mood[335] = good_habits_layout_circle_dec1_in_mood;
            list_of_all_the_calender_views_in_mood[336] = good_habits_layout_circle_dec2_in_mood;
            list_of_all_the_calender_views_in_mood[337] = good_habits_layout_circle_dec3_in_mood;
            list_of_all_the_calender_views_in_mood[338] = good_habits_layout_circle_dec4_in_mood;
            list_of_all_the_calender_views_in_mood[339] = good_habits_layout_circle_dec5_in_mood;
            list_of_all_the_calender_views_in_mood[340] = good_habits_layout_circle_dec6_in_mood;
            list_of_all_the_calender_views_in_mood[341] = good_habits_layout_circle_dec7_in_mood;
            list_of_all_the_calender_views_in_mood[342] = good_habits_layout_circle_dec8_in_mood;
            list_of_all_the_calender_views_in_mood[343] = good_habits_layout_circle_dec9_in_mood;
            list_of_all_the_calender_views_in_mood[344] = good_habits_layout_circle_dec10_in_mood;
            list_of_all_the_calender_views_in_mood[345] = good_habits_layout_circle_dec11_in_mood;
            list_of_all_the_calender_views_in_mood[346] = good_habits_layout_circle_dec12_in_mood;
            list_of_all_the_calender_views_in_mood[347] = good_habits_layout_circle_dec13_in_mood;
            list_of_all_the_calender_views_in_mood[348] = good_habits_layout_circle_dec14_in_mood;
            list_of_all_the_calender_views_in_mood[349] = good_habits_layout_circle_dec15_in_mood;
            list_of_all_the_calender_views_in_mood[350] = good_habits_layout_circle_dec16_in_mood;
            list_of_all_the_calender_views_in_mood[351] = good_habits_layout_circle_dec17_in_mood;
            list_of_all_the_calender_views_in_mood[352] = good_habits_layout_circle_dec18_in_mood;
            list_of_all_the_calender_views_in_mood[353] = good_habits_layout_circle_dec19_in_mood;
            list_of_all_the_calender_views_in_mood[354] = good_habits_layout_circle_dec20_in_mood;
            list_of_all_the_calender_views_in_mood[355] = good_habits_layout_circle_dec21_in_mood;
            list_of_all_the_calender_views_in_mood[356] = good_habits_layout_circle_dec22_in_mood;
            list_of_all_the_calender_views_in_mood[357] = good_habits_layout_circle_dec23_in_mood;
            list_of_all_the_calender_views_in_mood[358] = good_habits_layout_circle_dec24_in_mood;
            list_of_all_the_calender_views_in_mood[359] = good_habits_layout_circle_dec25_in_mood;
            list_of_all_the_calender_views_in_mood[360] = good_habits_layout_circle_dec26_in_mood;
            list_of_all_the_calender_views_in_mood[361] = good_habits_layout_circle_dec27_in_mood;
            list_of_all_the_calender_views_in_mood[362] = good_habits_layout_circle_dec28_in_mood;
            list_of_all_the_calender_views_in_mood[363] = good_habits_layout_circle_dec29_in_mood;
            list_of_all_the_calender_views_in_mood[364] = good_habits_layout_circle_dec30_in_mood;
            list_of_all_the_calender_views_in_mood[365] = good_habits_layout_circle_dec31_in_mood;
        }
    }

    private void forward_and_back_button_listen() {
        if (getView() != null) {
            Button button_shadow_for_the_back_for_good_habits_for_the_full_year_view_in_mood = getView().findViewById(R.id.button_shadow_for_the_back_for_good_habits_for_the_full_year_view_in_mood);
            Button button_shadow_for_the_front_for_good_habits_for_the_full_year_view_in_mood = getView().findViewById(R.id.button_shadow_for_the_front_for_good_habits_for_the_full_year_view_in_mood);
            final TextView text_saying_which_year_to_show_in_a_good_habits_year_in_mood = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year_in_mood);
            button_shadow_for_the_back_for_good_habits_for_the_full_year_view_in_mood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    text_saying_which_year_to_show_in_a_good_habits_year_in_mood.setText(String.valueOf(Integer.parseInt(text_saying_which_year_to_show_in_a_good_habits_year_in_mood.getText().toString()) - 1));
                    set_the_buttons();
                    set_the_leap_year();
                    restart_all_the_year_values();
                    put_values_into_year_in_good_habits();
                }
            });
            button_shadow_for_the_front_for_good_habits_for_the_full_year_view_in_mood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    text_saying_which_year_to_show_in_a_good_habits_year_in_mood.setText(String.valueOf(Integer.parseInt(text_saying_which_year_to_show_in_a_good_habits_year_in_mood.getText().toString()) + 1));
                    set_the_buttons();
                    set_the_leap_year();
                    restart_all_the_year_values();
                    put_values_into_year_in_good_habits();
                }
            });
        }
    }

    private void set_the_leap_year() {
        if (getView() != null) {
            TextView text_saying_which_year_to_show_in_a_good_habits_year_in_mood = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year_in_mood);
            View good_habits_layout_circle_feb29_in_mood = getView().findViewById(R.id.good_habits_layout_circle_feb29_in_mood);
            View backward_button_over_for_good_habits_for_the_full_year_view_in_mood = getView().findViewById(R.id.backward_button_over_for_good_habits_for_the_full_year_view_in_mood);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(text_saying_which_year_to_show_in_a_good_habits_year_in_mood.getText().toString()));
            if (calendar.getActualMaximum(Calendar.DAY_OF_YEAR) > 365) {
                if (backward_button_over_for_good_habits_for_the_full_year_view_in_mood.getVisibility() == View.VISIBLE) {
                    good_habits_layout_circle_feb29_in_mood.setVisibility(View.VISIBLE);
                } else {
                    calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
                    calendar.set(Calendar.DAY_OF_MONTH, 29);
                    if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) >= Simplify_the_time.return_time_in_midnight(start_date)) {
                        good_habits_layout_circle_feb29_in_mood.setVisibility(View.VISIBLE);
                    } else {
                        good_habits_layout_circle_feb29_in_mood.setVisibility(View.INVISIBLE);
                    }
                }
            } else {
                good_habits_layout_circle_feb29_in_mood.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void put_values_into_year_in_good_habits() {
        if (getView() != null && getContext() != null) {
            TextView text_saying_which_year_to_show_in_a_good_habits_year_in_mood = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year_in_mood);
            int year_from_text = Integer.parseInt(text_saying_which_year_to_show_in_a_good_habits_year_in_mood.getText().toString());
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year_from_text);

            String very_bad_color = return_the_color_of_mood(1);
            String bad_color = return_the_color_of_mood(2);
            String ok_color = return_the_color_of_mood(3);
            String good_color = return_the_color_of_mood(4);
            String very_good_color = return_the_color_of_mood(5);

            Drawable back_ground_for_very_good = ContextCompat.getDrawable(getContext(), R.drawable.mood_tracker_circle_under_pie).mutate();
            Drawable back_ground_for_good = ContextCompat.getDrawable(getContext(), R.drawable.mood_tracker_circle_under_pie).mutate();
            Drawable back_ground_for_ok = ContextCompat.getDrawable(getContext(), R.drawable.mood_tracker_circle_under_pie).mutate();
            Drawable back_ground_for_bad = ContextCompat.getDrawable(getContext(), R.drawable.mood_tracker_circle_under_pie).mutate();
            Drawable back_ground_for_very_bad = ContextCompat.getDrawable(getContext(), R.drawable.mood_tracker_circle_under_pie).mutate();
            Drawable back_ground_for_no_input = ContextCompat.getDrawable(getContext(), R.drawable.back_ground_for_year_in_habit).mutate();

            back_ground_for_very_bad.setTint(Color.parseColor(very_bad_color));
            back_ground_for_bad.setTint(Color.parseColor(bad_color));
            back_ground_for_ok.setTint(Color.parseColor(ok_color));
            back_ground_for_good.setTint(Color.parseColor(good_color));
            back_ground_for_very_good.setTint(Color.parseColor(very_good_color));

            Am_i_paid am_i_paid = new Am_i_paid(getContext());
            if (am_i_paid.did_user_pay()) {
                if (is_this_a_leap_year(year_from_text)) {
                    if (past_current_future_for_the_full_year().equals("past")) {
                        for (int i = 366; i >= 1; i--) {
                            calendar.set(Calendar.DAY_OF_YEAR, i);
                            if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) < Simplify_the_time.return_time_in_midnight(start_date)) {
                                break;
                            }
                            list_of_all_the_calender_views_in_mood[i-1].setVisibility(View.VISIBLE);
                            if (return_color_of_days(calendar.getTimeInMillis()) == 0) {
                                list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_no_input);
                            } else if (return_color_of_days(calendar.getTimeInMillis()) == 1) {
                                list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_very_bad);
                            } else if (return_color_of_days(calendar.getTimeInMillis()) == 2) {
                                list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_bad);
                            } else if (return_color_of_days(calendar.getTimeInMillis()) == 3) {
                                list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_ok);
                            } else if (return_color_of_days(calendar.getTimeInMillis()) == 4) {
                                list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_good);
                            } else if (return_color_of_days(calendar.getTimeInMillis()) == 5) {
                                list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_very_good);
                            }
                        }
                    } else if (past_current_future_for_the_full_year().equals("current")) {
                        Calendar calendar_get_the_today = Calendar.getInstance();
                        for (int i = calendar_get_the_today.get(Calendar.DAY_OF_YEAR); i >= 1; i--) {
                            calendar.set(Calendar.DAY_OF_YEAR, i);
                            if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) < Simplify_the_time.return_time_in_midnight(start_date)) {
                                break;
                            }
                            list_of_all_the_calender_views_in_mood[i - 1].setVisibility(View.VISIBLE);
                            if (return_color_of_days(calendar.getTimeInMillis()) == 0) {
                                list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_no_input);
                            } else if (return_color_of_days(calendar.getTimeInMillis()) == 1) {
                                list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_very_bad);
                            } else if (return_color_of_days(calendar.getTimeInMillis()) == 2) {
                                list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_bad);
                            } else if (return_color_of_days(calendar.getTimeInMillis()) == 3) {
                                list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_ok);
                            } else if (return_color_of_days(calendar.getTimeInMillis()) == 4) {
                                list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_good);
                            } else if (return_color_of_days(calendar.getTimeInMillis()) == 5) {
                                list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_very_good);
                            }
                        }
                    }
                } else {
                    if (past_current_future_for_the_full_year().equals("past")) {
                        for (int i = 365; i >= 1; i--) {
                            calendar.set(Calendar.DAY_OF_YEAR, i);
                            if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) < Simplify_the_time.return_time_in_midnight(start_date)) {
                                break;
                            }
                            if (i > 59) {
                                list_of_all_the_calender_views_in_mood[i].setVisibility(View.VISIBLE);
                                if (return_color_of_days(calendar.getTimeInMillis()) == 0) {
                                    list_of_all_the_calender_views_in_mood[i].setBackground(back_ground_for_no_input);
                                } else if (return_color_of_days(calendar.getTimeInMillis()) == 1) {
                                    list_of_all_the_calender_views_in_mood[i].setBackground(back_ground_for_very_bad);
                                } else if (return_color_of_days(calendar.getTimeInMillis()) == 2) {
                                    list_of_all_the_calender_views_in_mood[i].setBackground(back_ground_for_bad);
                                } else if (return_color_of_days(calendar.getTimeInMillis()) == 3) {
                                    list_of_all_the_calender_views_in_mood[i].setBackground(back_ground_for_ok);
                                } else if (return_color_of_days(calendar.getTimeInMillis()) == 4) {
                                    list_of_all_the_calender_views_in_mood[i].setBackground(back_ground_for_good);
                                } else if (return_color_of_days(calendar.getTimeInMillis()) == 5) {
                                    list_of_all_the_calender_views_in_mood[i].setBackground(back_ground_for_very_good);
                                }
                            } else {
                                list_of_all_the_calender_views_in_mood[i - 1].setVisibility(View.VISIBLE);
                                if (return_color_of_days(calendar.getTimeInMillis()) == 0) {
                                    list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_no_input);
                                } else if (return_color_of_days(calendar.getTimeInMillis()) == 1) {
                                    list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_very_bad);
                                } else if (return_color_of_days(calendar.getTimeInMillis()) == 2) {
                                    list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_bad);
                                } else if (return_color_of_days(calendar.getTimeInMillis()) == 3) {
                                    list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_ok);
                                } else if (return_color_of_days(calendar.getTimeInMillis()) == 4) {
                                    list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_good);
                                } else if (return_color_of_days(calendar.getTimeInMillis()) == 5) {
                                    list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_very_good);
                                }
                            }
                        }
                    } else if (past_current_future_for_the_full_year().equals("current")) {
                        Calendar calendar_get_the_today = Calendar.getInstance();
                        for (int i = calendar_get_the_today.get(Calendar.DAY_OF_YEAR); i >= 1; i--) {
                            calendar.set(Calendar.DAY_OF_YEAR, i);
                            if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) < Simplify_the_time.return_time_in_midnight(start_date)) {
                                break;
                            }
                            if (i > 59) {
                                list_of_all_the_calender_views_in_mood[i].setVisibility(View.VISIBLE);
                                if (return_color_of_days(calendar.getTimeInMillis()) == 0) {
                                    list_of_all_the_calender_views_in_mood[i].setBackground(back_ground_for_no_input);
                                } else if (return_color_of_days(calendar.getTimeInMillis()) == 1) {
                                    list_of_all_the_calender_views_in_mood[i].setBackground(back_ground_for_very_bad);
                                } else if (return_color_of_days(calendar.getTimeInMillis()) == 2) {
                                    list_of_all_the_calender_views_in_mood[i].setBackground(back_ground_for_bad);
                                } else if (return_color_of_days(calendar.getTimeInMillis()) == 3) {
                                    list_of_all_the_calender_views_in_mood[i].setBackground(back_ground_for_ok);
                                } else if (return_color_of_days(calendar.getTimeInMillis()) == 4) {
                                    list_of_all_the_calender_views_in_mood[i].setBackground(back_ground_for_good);
                                } else if (return_color_of_days(calendar.getTimeInMillis()) == 5) {
                                    list_of_all_the_calender_views_in_mood[i].setBackground(back_ground_for_very_good);
                                }
                            } else {
                                list_of_all_the_calender_views_in_mood[i - 1].setVisibility(View.VISIBLE);
                                if (return_color_of_days(calendar.getTimeInMillis()) == 0) {
                                    list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_no_input);
                                } else if (return_color_of_days(calendar.getTimeInMillis()) == 1) {
                                    list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_very_bad);
                                } else if (return_color_of_days(calendar.getTimeInMillis()) == 2) {
                                    list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_bad);
                                } else if (return_color_of_days(calendar.getTimeInMillis()) == 3) {
                                    list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_ok);
                                } else if (return_color_of_days(calendar.getTimeInMillis()) == 4) {
                                    list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_good);
                                } else if (return_color_of_days(calendar.getTimeInMillis()) == 5) {
                                    list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_very_good);
                                }
                            }
                        }
                    }
                }
            } else {
                Random random = new Random();
                if (is_this_a_leap_year(year_from_text)) {
                    for (int i = 366; i >= 1; i--) {
                        int number = random.nextInt(6);
                        calendar.set(Calendar.DAY_OF_YEAR, i);
                        list_of_all_the_calender_views_in_mood[i - 1].setVisibility(View.VISIBLE);
                        if (number == 0) {
                            list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_no_input);
                        } else if (number == 1) {
                            list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_very_bad);
                        } else if (number == 2) {
                            list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_bad);
                        } else if (number == 3) {
                            list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_ok);
                        } else if (number == 4) {
                            list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_good);
                        } else if (number == 5) {
                            list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_very_good);
                        }
                    }
                } else {
                    for (int i = 365; i >= 1; i--) {
                        int number = random.nextInt(6);
                        calendar.set(Calendar.DAY_OF_YEAR, i);
                        if (i > 59) {
                            list_of_all_the_calender_views_in_mood[i].setVisibility(View.VISIBLE);
                            if (number == 0) {
                                list_of_all_the_calender_views_in_mood[i].setBackground(back_ground_for_no_input);
                            } else if (number == 1) {
                                list_of_all_the_calender_views_in_mood[i].setBackground(back_ground_for_very_bad);
                            } else if (number == 2) {
                                list_of_all_the_calender_views_in_mood[i].setBackground(back_ground_for_bad);
                            } else if (number == 3) {
                                list_of_all_the_calender_views_in_mood[i].setBackground(back_ground_for_ok);
                            } else if (number == 4) {
                                list_of_all_the_calender_views_in_mood[i].setBackground(back_ground_for_good);
                            } else if (number == 5) {
                                list_of_all_the_calender_views_in_mood[i].setBackground(back_ground_for_very_good);
                            }
                        } else {
                            list_of_all_the_calender_views_in_mood[i - 1].setVisibility(View.VISIBLE);
                            if (number == 0) {
                                list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_no_input);
                            } else if (number == 1) {
                                list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_very_bad);
                            } else if (number == 2) {
                                list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_bad);
                            } else if (number == 3) {
                                list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_ok);
                            } else if (number == 4) {
                                list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_good);
                            } else if (number == 5) {
                                list_of_all_the_calender_views_in_mood[i - 1].setBackground(back_ground_for_very_good);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean is_this_a_leap_year(int year_from_function) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year_from_function);
        if (calendar.getActualMaximum(Calendar.DAY_OF_YEAR) > 365) {
            return true;
        } else {
            return false;
        }
    }

    private void restart_all_the_year_values() {
        for (int i = 0; i < 366; i++) {
            list_of_all_the_calender_views_in_mood[i].setVisibility(View.INVISIBLE);
        }
    }

    private String past_current_future_for_the_full_year() {
        if (getView() != null) {
            TextView text_saying_which_year_to_show_in_a_good_habits_year_in_mood = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year_in_mood);
            int year_from_text = Integer.parseInt(text_saying_which_year_to_show_in_a_good_habits_year_in_mood.getText().toString());
            Calendar calendar = Calendar.getInstance();
            int real_year = calendar.get(Calendar.YEAR);
            if (real_year < year_from_text) {
                return "future";
            } else if (real_year == year_from_text) {
                return "current";
            } else {
                return "past";
            }
        } else {
            return "";
        }
    }

    private void set_the_buttons() {
        if (getView() != null) {
            Button button_shadow_for_the_back_for_good_habits_for_the_full_year_view_in_mood = getView().findViewById(R.id.button_shadow_for_the_back_for_good_habits_for_the_full_year_view_in_mood);
            Button button_shadow_for_the_front_for_good_habits_for_the_full_year_view_in_mood = getView().findViewById(R.id.button_shadow_for_the_front_for_good_habits_for_the_full_year_view_in_mood);
            View forward_button_over_for_good_habits_for_the_full_year_view_in_mood = getView().findViewById(R.id.forward_button_over_for_good_habits_for_the_full_year_view_in_mood);
            View backward_button_over_for_good_habits_for_the_full_year_view_in_mood = getView().findViewById(R.id.backward_button_over_for_good_habits_for_the_full_year_view_in_mood);
            TextView text_saying_which_year_to_show_in_a_good_habits_year_in_mood = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year_in_mood);
            int year_from_text = Integer.parseInt(text_saying_which_year_to_show_in_a_good_habits_year_in_mood.getText().toString());
            Calendar calendar = Calendar.getInstance();
            int real_year = calendar.get(Calendar.YEAR);
            calendar.setTimeInMillis(start_date);
            int start_year = calendar.get(Calendar.YEAR);
            if (real_year == year_from_text) {
                forward_button_over_for_good_habits_for_the_full_year_view_in_mood.setVisibility(View.INVISIBLE);
                button_shadow_for_the_front_for_good_habits_for_the_full_year_view_in_mood.setVisibility(View.INVISIBLE);
            } else {
                forward_button_over_for_good_habits_for_the_full_year_view_in_mood.setVisibility(View.VISIBLE);
                button_shadow_for_the_front_for_good_habits_for_the_full_year_view_in_mood.setVisibility(View.VISIBLE);
            }
            if (start_year == year_from_text) {
                backward_button_over_for_good_habits_for_the_full_year_view_in_mood.setVisibility(View.INVISIBLE);
                button_shadow_for_the_back_for_good_habits_for_the_full_year_view_in_mood.setVisibility(View.INVISIBLE);
            } else {
                backward_button_over_for_good_habits_for_the_full_year_view_in_mood.setVisibility(View.VISIBLE);
                button_shadow_for_the_back_for_good_habits_for_the_full_year_view_in_mood.setVisibility(View.VISIBLE);
            }
        }
    }

    private void set_the_title_of_the_year_habit() {
        if (getView() != null) {
            TextView text_saying_which_year_to_show_in_a_good_habits_year_in_mood = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year_in_mood);
            Calendar calendar = Calendar.getInstance();
            text_saying_which_year_to_show_in_a_good_habits_year_in_mood.setText(String.valueOf(calendar.get(Calendar.YEAR)));
        }
    }

    private void watch_all_the_share_button() {
        if (getView() != null) {
            final Button button_too_share_calender_in_good_habits = getView().findViewById(R.id.button_too_share_calender_in_good_habits);
            final Button button_to_share_mood_graph = getView().findViewById(R.id.button_to_share_mood_graph);
            final Button button_to_share_the_best_average_and_current_streak = getView().findViewById(R.id.button_to_share_the_best_average_and_current_streak);
            final Button button_to_share_bar_chart = getView().findViewById(R.id.button_to_share_bar_chart);
            final Button button_to_share_pie_chart = getView().findViewById(R.id.button_to_share_pie_chart);
            final Button share_button_to_share_the_whole_year_in_the_good_habits = getView().findViewById(R.id.share_button_to_share_the_whole_year_in_the_good_habits);

            final CardView card_to_show_calender = getView().findViewById(R.id.card_to_show_calender);
            final CardView card_to_show_line_graph_for_mood_tracker = getView().findViewById(R.id.card_to_show_line_graph_for_mood_tracker);
            final CardView card_to_show_current_average_and_best_streak = getView().findViewById(R.id.card_to_show_current_average_and_best_streak);
            final CardView card_to_show_daily_input_by_week_of_days = getView().findViewById(R.id.card_to_show_daily_input_by_week_of_days);
            final CardView card_to_show_pie_chart_for_yes_or_no_over_the_days = getView().findViewById(R.id.card_to_show_pie_chart_for_yes_or_no_over_the_days);
            final CardView card_showing_the_habit_in_year_in_good_habits_in_mood = getView().findViewById(R.id.card_showing_the_habit_in_year_in_good_habits_in_mood);

            final View three_dots_view_in_mood_bar_to_change_week_month = getView().findViewById(R.id.three_dots_view_in_mood_bar_to_change_week_month);

            final Button very_good_mood_button_in_habits = getView().findViewById(R.id.very_good_mood_button_in_habits);
            button_too_share_calender_in_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (very_good_mood_button_in_habits.getVisibility() == View.VISIBLE) {
                        button_too_share_calender_in_good_habits.setVisibility(View.GONE);
                        hide_or_un_hide_the_button(0);
                        final ViewTreeObserver observer = very_good_mood_button_in_habits.getViewTreeObserver();
                        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                            @Override
                            public void onGlobalLayout() {
                                share_screen_shot(screenShot(card_to_show_calender));
                                button_too_share_calender_in_good_habits.setVisibility(View.VISIBLE);
                                hide_or_un_hide_the_button(1);
                                observer.removeOnGlobalLayoutListener(this);
                            }
                        });
                    } else {
                        button_too_share_calender_in_good_habits.setVisibility(View.INVISIBLE);
                        share_screen_shot(screenShot(card_to_show_calender));
                        button_too_share_calender_in_good_habits.setVisibility(View.VISIBLE);
                    }
                }
            });
            button_to_share_mood_graph.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button_to_share_mood_graph.setVisibility(View.INVISIBLE);
                    share_screen_shot(screenShot(card_to_show_line_graph_for_mood_tracker));
                    button_to_share_mood_graph.setVisibility(View.VISIBLE);
                }
            });
            button_to_share_the_best_average_and_current_streak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button_to_share_the_best_average_and_current_streak.setVisibility(View.INVISIBLE);
                    share_screen_shot(screenShot(card_to_show_current_average_and_best_streak));
                    button_to_share_the_best_average_and_current_streak.setVisibility(View.VISIBLE);
                }
            });
            button_to_share_bar_chart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button_to_share_bar_chart.setVisibility(View.INVISIBLE);
                    three_dots_view_in_mood_bar_to_change_week_month.setVisibility(View.INVISIBLE);
                    share_screen_shot(screenShot(card_to_show_daily_input_by_week_of_days));
                    button_to_share_bar_chart.setVisibility(View.VISIBLE);
                    three_dots_view_in_mood_bar_to_change_week_month.setVisibility(View.VISIBLE);
                }
            });
            button_to_share_pie_chart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button_to_share_pie_chart.setVisibility(View.INVISIBLE);
                    share_screen_shot(screenShot(card_to_show_pie_chart_for_yes_or_no_over_the_days));
                    button_to_share_pie_chart.setVisibility(View.VISIBLE);
                }
            });
            share_button_to_share_the_whole_year_in_the_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    share_button_to_share_the_whole_year_in_the_good_habits.setVisibility(View.INVISIBLE);
                    share_screen_shot(screenShot(card_showing_the_habit_in_year_in_good_habits_in_mood));
                    share_button_to_share_the_whole_year_in_the_good_habits.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    private Bitmap screenShot(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    //create bitmap from the ScrollView
    private Bitmap getBitmapFromView(View view, int height, int width) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;
    }

    private void share_screen_shot(Bitmap bitmap) {
        if (getView() != null && getContext() != null) {
            final ImageView view_to_pass_the_real_view_to_for_screen_shot = getView().findViewById(R.id.view_to_pass_the_real_view_to_for_screen_shot);
            //final ConstraintLayout layou_to_show_screen_shot_in_good_habits = getView().findViewById(R.id.layou_to_show_screen_shot_in_good_habits);
            final ScrollView scroll_view_to_take_screen_show_in_good_habtis = getView().findViewById(R.id.scroll_view_to_take_screen_show_in_good_habtis);
            view_to_pass_the_real_view_to_for_screen_shot.setImageBitmap(bitmap);
            scroll_view_to_take_screen_show_in_good_habtis.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    scroll_view_to_take_screen_show_in_good_habtis.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    share_the_bitmap(getBitmapFromView(scroll_view_to_take_screen_show_in_good_habtis, scroll_view_to_take_screen_show_in_good_habtis.getChildAt(0).getHeight(), scroll_view_to_take_screen_show_in_good_habtis.getChildAt(0).getWidth()));
                    view_to_pass_the_real_view_to_for_screen_shot.setImageDrawable(null);
                }
            });
        }
    }

    private void share_the_bitmap(Bitmap bitmap) {
        if (getView() != null && getContext() != null) {
            // save bitmap to cache directory
            try {
                File cachePath = new File(getContext().getCacheDir(), "images");
                cachePath.mkdirs(); // don't forget to make the directory
                FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                stream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            File imagePath = new File(getContext().getCacheDir(), "images");
            File newFile = new File(imagePath, "image.png");
            Uri contentUri = FileProvider.getUriForFile(getContext(), "com.easyhabitsapp.android.fileprovider", newFile);

            if (contentUri != null) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
                shareIntent.setDataAndType(contentUri, getActivity().getContentResolver().getType(contentUri));
                shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
                startActivity(Intent.createChooser(shareIntent, "Share"));
            }
        }
    }


    private void back_button_listen() {
        if (getActivity() != null && getView() != null) {
            Button back_button_in_view_information_good_habits = getView().findViewById(R.id.back_button_in_view_information_good_habits);
            back_button_in_view_information_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    habits_fragment habits_fragment = new habits_fragment();
                    mood_tracker old_fragment = (mood_tracker) getActivity().getSupportFragmentManager().findFragmentByTag("mood tracker");
                    com.easyhabitsapp.android.habits_fragment new_fragment = (com.easyhabitsapp.android.habits_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("habits");
                    if (old_fragment != null && new_fragment != null) {
                        getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).remove(new_fragment).add(R.id.fragment_container, habits_fragment, "habits").show(habits_fragment).commit();
                    }
                }
            });
        }
    }

    private void three_dot_button_listen() {
        if (getView() != null) {
            Button three_dots_button_in_top_in_habits = getView().findViewById(R.id.three_dots_button_in_top_in_habits);
            three_dots_button_in_top_in_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    show_pop_up_menu_mood(three_dots_button_in_top_in_habits);
                }
            });
        }
    }

    public void show_pop_up_menu_mood(View view) {
        if (getContext() != null) {
            PopupMenu popupMenu = new PopupMenu(getContext(), view);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getOrder() == 0) {
                        call_date_picker();
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            popupMenu.inflate(R.menu.three_dot_mood);
            popupMenu.show();
        }
    }

    private long return_start_date() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("first_date_of_mood", Context.MODE_PRIVATE);
            long first_time = sharedPreferences.getLong("first_date", -1);
            if (first_time == -1) {
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putLong("first_date", Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()));
                myEdit.commit();
                return Simplify_the_time.return_time_in_midnight(System.currentTimeMillis());
            } else {
                return first_time;
            }
        } else {
            return Simplify_the_time.return_time_in_midnight(System.currentTimeMillis());
        }
    }

    private void call_date_picker() {
        Universal_date_picker universal_date_picker = new Universal_date_picker();
        universal_date_picker.setTargetFragment(mood_tracker.this, 22);
        universal_date_picker.show(getActivity().getSupportFragmentManager(), "date_picker");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 22:
                int year_global = data.getIntExtra("year", 2001);
                int month_global = data.getIntExtra("month", 1);
                int day_global = data.getIntExtra("day", 20);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year_global);
                calendar.set(Calendar.MONTH, month_global);
                calendar.set(Calendar.DAY_OF_MONTH, day_global);
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("first_date_of_mood", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putLong("first_date", Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()));
                myEdit.commit();
                call_me_at_start();
                break;
        }
    }

    private void fade_the_views() {
        if (getView() != null && getContext() != null) {
            Button this_streak_chart_is_only_available_for_pro_users_pie_chart_mood = getView().findViewById(R.id.this_streak_chart_is_only_available_for_pro_users_pie_chart_mood);
            Button this_streak_chart_is_only_available_for_pro_users_weekly_mood_average_chart_mood = getView().findViewById(R.id.this_streak_chart_is_only_available_for_pro_users_weekly_mood_average_chart_mood);
            Button this_streak_chart_is_only_available_for_pro_users_in_a_year_chart_mood = getView().findViewById(R.id.this_streak_chart_is_only_available_for_pro_users_in_a_year_chart_mood);

            TextView title_of_the_card_saying_this_is_the_graph_card = getView().findViewById(R.id.title_of_the_card_saying_this_is_the_graph_card);
            Button button_to_share_mood_graph = getView().findViewById(R.id.button_to_share_mood_graph);
            TextView text_view_saying_that_there_is_not_enough_data_to_draw_this_line_chart = getView().findViewById(R.id.text_view_saying_that_there_is_not_enough_data_to_draw_this_line_chart);
            Button button_to_show_back_above_mood_tracker_graph = getView().findViewById(R.id.button_to_show_back_above_mood_tracker_graph);
            Button button_to_show_forward_above_mood_tracker_graph = getView().findViewById(R.id.button_to_show_forward_above_mood_tracker_graph);
            View view_to_show_back_above_mood_tracker_graph = getView().findViewById(R.id.view_to_show_back_above_mood_tracker_graph);
            View view_button_over_for_good_habits = getView().findViewById(R.id.view_button_over_for_good_habits);
            TextView text_saying_month_year_in_the_chart_mood_tracker = getView().findViewById(R.id.text_saying_month_year_in_the_chart_mood_tracker);
            LineChart cahrt_showing_mood_in_mood_tracker = getView().findViewById(R.id.cahrt_showing_mood_in_mood_tracker);
            TextView saying_you_can_scroll_to_see_more_mood_chart = getView().findViewById(R.id.saying_you_can_scroll_to_see_more_mood_chart);

            TextView text_title_of_weekly_daily_habit_in_card = getView().findViewById(R.id.text_title_of_weekly_daily_habit_in_card);
            BarChart cahrt_in_good_habits_about_how_many_times_for_each_days_of_week = getView().findViewById(R.id.cahrt_in_good_habits_about_how_many_times_for_each_days_of_week);
            BarChart cahrt_in_mood_about_the_average_for_each_month = getView().findViewById(R.id.cahrt_in_mood_about_the_average_for_each_month);
            Button button_to_share_bar_chart = getView().findViewById(R.id.button_to_share_bar_chart);
            TextView text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse = getView().findViewById(R.id.text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse);
            Button three_dots_button_to_ask_weekly_or_monthly_mood_bar_average = getView().findViewById(R.id.three_dots_button_to_ask_weekly_or_monthly_mood_bar_average);
            View three_dots_view_in_mood_bar_to_change_week_month = getView().findViewById(R.id.three_dots_view_in_mood_bar_to_change_week_month);
            TextView saying_you_can_scroll_to_see_more_mood_average_cahrt_in_mood_tracker = getView().findViewById(R.id.saying_you_can_scroll_to_see_more_mood_average_cahrt_in_mood_tracker);

            TextView title_saying_the_habit_name_in_year_for_good_habits_in_mood = getView().findViewById(R.id.title_saying_the_habit_name_in_year_for_good_habits_in_mood);
            TextView text_saying_which_year_to_show_in_a_good_habits_year_in_mood = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year_in_mood);
            Button button_shadow_for_the_back_for_good_habits_for_the_full_year_view_in_mood = getView().findViewById(R.id.button_shadow_for_the_back_for_good_habits_for_the_full_year_view_in_mood);
            Button button_shadow_for_the_front_for_good_habits_for_the_full_year_view_in_mood = getView().findViewById(R.id.button_shadow_for_the_front_for_good_habits_for_the_full_year_view_in_mood);
            View forward_button_over_for_good_habits_for_the_full_year_view_in_mood = getView().findViewById(R.id.forward_button_over_for_good_habits_for_the_full_year_view_in_mood);
            View backward_button_over_for_good_habits_for_the_full_year_view_in_mood = getView().findViewById(R.id.backward_button_over_for_good_habits_for_the_full_year_view_in_mood);
            ConstraintLayout layout_to_show_data_about_the_year_calender_in_mood = getView().findViewById(R.id.layout_to_show_data_about_the_year_calender_in_mood);
            Button share_button_to_share_the_whole_year_in_the_good_habits = getView().findViewById(R.id.share_button_to_share_the_whole_year_in_the_good_habits);

            Am_i_paid am_i_paid = new Am_i_paid(getContext());
            if (am_i_paid.did_user_pay()) {
                this_streak_chart_is_only_available_for_pro_users_pie_chart_mood.setVisibility(View.INVISIBLE);
                this_streak_chart_is_only_available_for_pro_users_weekly_mood_average_chart_mood.setVisibility(View.INVISIBLE);
                this_streak_chart_is_only_available_for_pro_users_in_a_year_chart_mood.setVisibility(View.INVISIBLE);

                title_of_the_card_saying_this_is_the_graph_card.setAlpha(1f);
                button_to_share_mood_graph.setAlpha(1f);
                text_view_saying_that_there_is_not_enough_data_to_draw_this_line_chart.setAlpha(1f);
                button_to_show_back_above_mood_tracker_graph.setAlpha(1f);
                button_to_show_forward_above_mood_tracker_graph.setAlpha(1f);
                view_to_show_back_above_mood_tracker_graph.setAlpha(1f);
                view_button_over_for_good_habits.setAlpha(1f);
                text_saying_month_year_in_the_chart_mood_tracker.setAlpha(1f);
                cahrt_showing_mood_in_mood_tracker.setAlpha(1f);
                saying_you_can_scroll_to_see_more_mood_chart.setAlpha(1f);

                text_title_of_weekly_daily_habit_in_card.setAlpha(1f);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setAlpha(1f);
                cahrt_in_mood_about_the_average_for_each_month.setAlpha(1f);
                button_to_share_bar_chart.setAlpha(1f);
                text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse.setAlpha(1f);
                three_dots_button_to_ask_weekly_or_monthly_mood_bar_average.setAlpha(1f);
                three_dots_view_in_mood_bar_to_change_week_month.setAlpha(1f);
                saying_you_can_scroll_to_see_more_mood_average_cahrt_in_mood_tracker.setAlpha(1f);

                title_saying_the_habit_name_in_year_for_good_habits_in_mood.setAlpha(1f);
                text_saying_which_year_to_show_in_a_good_habits_year_in_mood.setAlpha(1f);
                button_shadow_for_the_back_for_good_habits_for_the_full_year_view_in_mood.setAlpha(1f);
                button_shadow_for_the_front_for_good_habits_for_the_full_year_view_in_mood.setAlpha(1f);
                forward_button_over_for_good_habits_for_the_full_year_view_in_mood.setAlpha(1f);
                backward_button_over_for_good_habits_for_the_full_year_view_in_mood.setAlpha(1f);
                layout_to_show_data_about_the_year_calender_in_mood.setAlpha(1f);
                share_button_to_share_the_whole_year_in_the_good_habits.setAlpha(1f);
            } else {
                this_streak_chart_is_only_available_for_pro_users_pie_chart_mood.setVisibility(View.VISIBLE);
                this_streak_chart_is_only_available_for_pro_users_weekly_mood_average_chart_mood.setVisibility(View.VISIBLE);
                this_streak_chart_is_only_available_for_pro_users_in_a_year_chart_mood.setVisibility(View.VISIBLE);
                title_of_the_card_saying_this_is_the_graph_card.setAlpha(0.35f);
                button_to_share_mood_graph.setAlpha(0.35f);
                text_view_saying_that_there_is_not_enough_data_to_draw_this_line_chart.setAlpha(0.35f);
                button_to_show_back_above_mood_tracker_graph.setAlpha(0.35f);
                button_to_show_forward_above_mood_tracker_graph.setAlpha(0.35f);
                view_to_show_back_above_mood_tracker_graph.setAlpha(0.35f);
                view_button_over_for_good_habits.setAlpha(0.35f);
                text_saying_month_year_in_the_chart_mood_tracker.setAlpha(0.35f);
                cahrt_showing_mood_in_mood_tracker.setAlpha(0.35f);
                saying_you_can_scroll_to_see_more_mood_chart.setAlpha(0.35f);

                text_title_of_weekly_daily_habit_in_card.setAlpha(0.35f);
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setAlpha(0.35f);
                cahrt_in_mood_about_the_average_for_each_month.setAlpha(0.35f);
                button_to_share_bar_chart.setAlpha(0.35f);
                text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse.setAlpha(0.35f);
                three_dots_button_to_ask_weekly_or_monthly_mood_bar_average.setAlpha(0.35f);
                three_dots_view_in_mood_bar_to_change_week_month.setAlpha(0.35f);
                saying_you_can_scroll_to_see_more_mood_average_cahrt_in_mood_tracker.setAlpha(0.35f);

                title_saying_the_habit_name_in_year_for_good_habits_in_mood.setAlpha(0.35f);
                text_saying_which_year_to_show_in_a_good_habits_year_in_mood.setAlpha(0.35f);
                button_shadow_for_the_back_for_good_habits_for_the_full_year_view_in_mood.setAlpha(0.35f);
                button_shadow_for_the_front_for_good_habits_for_the_full_year_view_in_mood.setAlpha(0.35f);
                forward_button_over_for_good_habits_for_the_full_year_view_in_mood.setAlpha(0.35f);
                backward_button_over_for_good_habits_for_the_full_year_view_in_mood.setAlpha(0.35f);
                layout_to_show_data_about_the_year_calender_in_mood.setAlpha(0.35f);
                share_button_to_share_the_whole_year_in_the_good_habits.setAlpha(0.35f);
            }
        }
    }

    private void buy_premuim_button_listen() {
        if (getView() != null) {
            Button this_streak_chart_is_only_available_for_pro_users_pie_chart_mood = getView().findViewById(R.id.this_streak_chart_is_only_available_for_pro_users_pie_chart_mood);
            Button this_streak_chart_is_only_available_for_pro_users_weekly_mood_average_chart_mood = getView().findViewById(R.id.this_streak_chart_is_only_available_for_pro_users_weekly_mood_average_chart_mood);
            Button this_streak_chart_is_only_available_for_pro_users_in_a_year_chart_mood = getView().findViewById(R.id.this_streak_chart_is_only_available_for_pro_users_in_a_year_chart_mood);

            this_streak_chart_is_only_available_for_pro_users_pie_chart_mood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Buy_premuim buy_premuim = new Buy_premuim("use mood chart", true, "mood tracker");
                    mood_tracker old_fragment = (mood_tracker) getActivity().getSupportFragmentManager().findFragmentByTag("mood tracker");
                    if (old_fragment != null) {
                        getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
                    }
                }
            });
            this_streak_chart_is_only_available_for_pro_users_weekly_mood_average_chart_mood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Buy_premuim buy_premuim = new Buy_premuim("use the weekly and monthly average", true, "mood tracker");
                    mood_tracker old_fragment = (mood_tracker) getActivity().getSupportFragmentManager().findFragmentByTag("mood tracker");
                    if (old_fragment != null) {
                        getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
                    }
                }
            });
            this_streak_chart_is_only_available_for_pro_users_in_a_year_chart_mood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Buy_premuim buy_premuim = new Buy_premuim("use the mood in a year chart", true, "mood tracker");
                    mood_tracker old_fragment = (mood_tracker) getActivity().getSupportFragmentManager().findFragmentByTag("mood tracker");
                    if (old_fragment != null) {
                        getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
                    }
                }
            });
        }
    }
}