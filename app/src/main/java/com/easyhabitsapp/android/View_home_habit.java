package com.easyhabitsapp.android;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * create an instance of this fragment.
 */

public class View_home_habit extends Fragment implements PopupMenu.OnMenuItemClickListener {
    private String habit_name;
    private Handler handler = new Handler();
    private Handler handler2 = new Handler();
    private final int delay = 1000; //milliseconds
    private int delay2_for_goals = 120000;
    private int previous_int = 10;
    private boolean take_rest_day_for_congrats = false;
    private boolean take_rest_goal_for_congrats = false;
    private int color;
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
    private ArrayList<Long> history_of_relapse;
    private Long start_date;
    private int value_for_position;
    private LineChart line_chart_for_streak;
    private View[] list_of_all_the_calender_views;
    private ArrayList<Integer> each_streak_lengths;
    private ArrayList<Integer> days_of_months_good_habit;
    private HashMap<Long, Integer> hash_map_amount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_home_habit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        call_me_on_start();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            call_me_on_start();
        }
    }

    private void call_me_on_start() {
        open_the_and_shared();
        add_the_days_per_month_to_the_calender();
        put_all_the_relapses_into_a_array_list();
        calculate_all_the_streaks();
        displaying_streak_for_user();
        back_button_listen();
        color_the_stuff();
        declare_start_date();
        color_the_4_drawables();
        color_the_middle();
        define_the_buttons();
        set_the_first_day_of_the_week_number();
        set_the_days_on_the_real_text();
        back_and_front_button_listen();
        color_the_today_value();
        calender_button_listeners();
        remove_the_hiding_buttons();
        color_today();
        color_the_calender();
        set_up_share_and_yes_or_no_buttons();
        yes_and_no_button_listen_under_the_calender();
        divide_it_into_weeks();
        clear_the_middle();
        make_the_middle_come_again();
        hide_or_un_hide_the_button(1);
        color_the_button_under_the_calender();
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
        watch_all_the_share_button();
        make_the_views_transparent();
        uy_premuim_multiple_button_listen();
        change_the_text_if_it_is_a_good_habit();
        change_the_text_above_realpse_graphs_if_it_is_a_good_habit();
        change_the_four_text_good_habit();
        plus_and_minus_for_amount();
        amount_text_watcher();
    }

    private void open_the_and_shared() {
        if (getActivity() != null && getView() != null) {
            TextView text_view_to_write_title_of_good_habits_in_infromation_view = getView().findViewById(R.id.text_view_to_write_title_of_good_habits_in_infromation_view);
            if (getArguments() != null) {
                value_for_position = getArguments().getInt("which_position");
                habit_name = return_the_information_from_save(1);
                color = Color.parseColor(return_the_information_from_save(4));
                text_view_to_write_title_of_good_habits_in_infromation_view.setText(habit_name);
            }
        }
    }

    private void displaying_streak_for_user() {
        if (getView() != null && getContext() != null) {
            TextView textView = getView().findViewById(R.id.Your_streak_display_TextView);
            String streak_string = String.valueOf(return_the_streak());
            if (return_the_streak() == 1) {
                String sentence_with_streak = "Your current streak is ".concat(streak_string).concat(" day.");
                SpannableString spanned_sentence_with_streak = new SpannableString(sentence_with_streak);
                ForegroundColorSpan blue_color_to_span = new ForegroundColorSpan(color);
                StyleSpan boldspan = new StyleSpan(Typeface.BOLD);
                spanned_sentence_with_streak.setSpan(blue_color_to_span, 23, (23 + streak_string.length()), 0);
                spanned_sentence_with_streak.setSpan(boldspan, 0, spanned_sentence_with_streak.length(), 0);
                textView.setText(spanned_sentence_with_streak);
            } else {
                String sentence_with_streak = "Your current streak is " + streak_string + " days.";
                SpannableString spanned_sentence_with_streak = new SpannableString(sentence_with_streak);
                ForegroundColorSpan orange_color_to_span = new ForegroundColorSpan(color);
                StyleSpan boldspan = new StyleSpan(Typeface.BOLD);
                spanned_sentence_with_streak.setSpan(orange_color_to_span, 23, (23 + streak_string.length()), 0);
                spanned_sentence_with_streak.setSpan(boldspan, 0, spanned_sentence_with_streak.length(), 0);
                textView.setText(spanned_sentence_with_streak);
            }
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

    private void back_button_listen() {
        if (getView() != null) {
            Button back_button_in_view_information_good_habits = getView().findViewById(R.id.back_button_in_view_information_good_habits);
            back_button_in_view_information_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    habits_fragment habits_fragment = new habits_fragment();
                    View_home_habit view_home_habit = (View_home_habit) getActivity().getSupportFragmentManager().findFragmentByTag("view home");
                    home_fragment home_fragment = (com.easyhabitsapp.android.home_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("home");
                    com.easyhabitsapp.android.habits_fragment old_habits = (com.easyhabitsapp.android.habits_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("habits");
                    if (view_home_habit != null) {
                        if (getActivity() != null && home_fragment != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().remove(view_home_habit).remove(old_habits).add(R.id.fragment_container, habits_fragment, "habits").hide(habits_fragment).show(home_fragment).commit();
                        }
                    }
                }
            });
        }
    }

    private void color_the_stuff() {
        if (getView() != null) {
            View view_at_the_top_action_bar_habits = getView().findViewById(R.id.view_at_the_top_action_bar_habits);
            View view_under_top_action_bar_half_color = getView().findViewById(R.id.view_under_top_action_bar_half_color);
            View back_button_in_view_information_good_habits = getView().findViewById(R.id.back_button_in_view_information_good_habits);
            View three_dots_button_in_top_in_habits = getView().findViewById(R.id.three_dots_button_in_top_in_habits);
            ConstraintLayout layout_inside_scroll_in_the_bad_habits = getView().findViewById(R.id.layout_inside_scroll_in_the_bad_habits);
            Button button_to_share_streak_in_good_habits = getView().findViewById(R.id.button_to_share_streak_in_good_habits);
            Button button_too_share_calender_in_good_habits = getView().findViewById(R.id.button_too_share_calender_in_good_habits);
            Button button_to_share_the_best_average_and_current_streak = getView().findViewById(R.id.button_to_share_the_best_average_and_current_streak);
            Button button_to_share_goal_progress_good_habits = getView().findViewById(R.id.button_to_share_goal_progress_good_habits);
            Button button_to_share_bar_chart = getView().findViewById(R.id.button_to_share_bar_chart);
            Button button_to_share_pie_chart = getView().findViewById(R.id.button_to_share_pie_chart);
            Button button_to_share_line_chart_of_various_streaks = getView().findViewById(R.id.button_to_share_line_chart_of_various_streaks);
            Button sahre_button_for_the_foour_values_in_good_habits = getView().findViewById(R.id.sahre_button_for_the_foour_values_in_good_habits);
            Button share_button_to_share_the_whole_year_in_the_good_habits = getView().findViewById(R.id.share_button_to_share_the_whole_year_in_the_good_habits);
            ProgressBar progress_bar_showing_good_habit_progress_in_good_habits = getView().findViewById(R.id.progress_bar_showing_good_habit_progress_in_good_habits);
            TextView text_asking_did_you_relapse_in_share = getView().findViewById(R.id.text_asking_did_you_relapse_in_share);
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
            button_to_share_streak_in_good_habits.setTextColor(color);
            button_too_share_calender_in_good_habits.setTextColor(color);
            button_to_share_the_best_average_and_current_streak.setTextColor(color);
            button_to_share_goal_progress_good_habits.setTextColor(color);
            button_to_share_bar_chart.setTextColor(color);
            button_to_share_pie_chart.setTextColor(color);
            button_to_share_line_chart_of_various_streaks.setTextColor(color);
            sahre_button_for_the_foour_values_in_good_habits.setTextColor(color);
            share_button_to_share_the_whole_year_in_the_good_habits.setTextColor(color);
            text_asking_did_you_relapse_in_share.setTextColor(color);
            LayerDrawable progressBarDrawable = (LayerDrawable) progress_bar_showing_good_habit_progress_in_good_habits.getProgressDrawable();
            Drawable backgroundDrawable = progressBarDrawable.getDrawable(0);
            Drawable progressDrawable = progressBarDrawable.getDrawable(1);
            backgroundDrawable.setTint(Color.parseColor("#eaeceb"));
            progressDrawable.setTint(color);
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
            String yes_color = "#06a94d";
            String no_color = "#FF2400";
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
                            if (return_state_of_day(time_in_milli)) {
                                colors[i] = yes_color;
                            } else {
                                colors[i] = no_color;
                            }
                        } else {
                            colors[i] = "#000000";
                        }
                    } else {
                        colors[i] = "#000000";
                    }
                }
            } else {
                for (int i = 1; i <= return_last_day_of_month(); i++) {
                    colors[i] = "#000000";
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
                        if (calender_day > real_day) {
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
            Button button_saying_yes_under_calender_in_good_habits = getView().findViewById(R.id.button_saying_yes_under_calender_in_good_habits);
            Button button_saying_no_under_calender_in_good_habits = getView().findViewById(R.id.button_saying_no_under_calender_in_good_habits);
            Button button_too_share_calender_in_good_habits = getView().findViewById(R.id.button_too_share_calender_in_good_habits);
            TextView text_asking_did_you_relapse_in_share = getView().findViewById(R.id.text_asking_did_you_relapse_in_share);
            ConstraintLayout calender_in_stats = getView().findViewById(R.id.calender_in_stats);
            EditText how_many_times_did_you_do_this_habit_edit_text = getView().findViewById(R.id.how_many_times_did_you_do_this_habit_edit_text);
            View negative_view_beside_amount_in_view_habit = getView().findViewById(R.id.negative_view_beside_amount_in_view_habit);
            View positive_view_beside_amount_in_view_habit = getView().findViewById(R.id.positive_view_beside_amount_in_view_habit);
            Button negative_button_beside_amount_in_view_habit = getView().findViewById(R.id.negative_button_beside_amount_in_view_habit);
            Button positive_button_beside_amount_in_view_habit = getView().findViewById(R.id.positive_button_beside_amount_in_view_habit);
            if (which == 0) {
                if (return_the_information_from_save(6).equals("bad")) {
                    button_saying_yes_under_calender_in_good_habits.setVisibility(View.GONE);
                    button_saying_no_under_calender_in_good_habits.setVisibility(View.GONE);
                } else {
                    if (return_the_information_from_save(7).equals("yes/no")) {
                        button_saying_yes_under_calender_in_good_habits.setVisibility(View.GONE);
                        button_saying_no_under_calender_in_good_habits.setVisibility(View.GONE);
                    } else if (return_the_information_from_save(7).equals("amount")) {
                        how_many_times_did_you_do_this_habit_edit_text.setVisibility(View.GONE);
                        negative_view_beside_amount_in_view_habit.setVisibility(View.GONE);
                        positive_view_beside_amount_in_view_habit.setVisibility(View.GONE);
                        negative_button_beside_amount_in_view_habit.setVisibility(View.GONE);
                        positive_button_beside_amount_in_view_habit.setVisibility(View.GONE);
                    } else if (return_the_information_from_save(7).equals("timer")) {

                    }
                }
                text_asking_did_you_relapse_in_share.setVisibility(View.GONE);
                ConstraintLayout constraintLayout = getView().findViewById(R.id.layout_containting_the_calender);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.clear(button_too_share_calender_in_good_habits.getId(), ConstraintSet.TOP);
                constraintSet.connect(button_too_share_calender_in_good_habits.getId(), ConstraintSet.TOP, calender_in_stats.getId(), ConstraintSet.TOP, (int) convertDpToPixel(return_the_length_of_stat() + 7, getContext()));
                constraintSet.applyTo(constraintLayout);
            } else {
                if (return_the_information_from_save(6).equals("bad")) {
                    button_saying_yes_under_calender_in_good_habits.setVisibility(View.VISIBLE);
                    button_saying_no_under_calender_in_good_habits.setVisibility(View.VISIBLE);
                    ConstraintLayout constraintLayout = getView().findViewById(R.id.layout_containting_the_calender);
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(text_asking_did_you_relapse_in_share.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, (int) convertDpToPixel(return_the_length_of_stat() + 28, getContext()));
                    constraintSet.connect(button_too_share_calender_in_good_habits.getId(), ConstraintSet.TOP, button_saying_no_under_calender_in_good_habits.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(15, getContext()));
                    constraintSet.applyTo(constraintLayout);
                } else {
                    if (return_the_information_from_save(7).equals("yes/no")) {
                        button_saying_yes_under_calender_in_good_habits.setVisibility(View.VISIBLE);
                        button_saying_no_under_calender_in_good_habits.setVisibility(View.VISIBLE);
                        ConstraintLayout constraintLayout = getView().findViewById(R.id.layout_containting_the_calender);
                        ConstraintSet constraintSet = new ConstraintSet();
                        constraintSet.clone(constraintLayout);
                        constraintSet.connect(text_asking_did_you_relapse_in_share.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, (int) convertDpToPixel(return_the_length_of_stat() + 28, getContext()));
                        constraintSet.connect(button_too_share_calender_in_good_habits.getId(), ConstraintSet.TOP, button_saying_no_under_calender_in_good_habits.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(15, getContext()));
                        constraintSet.applyTo(constraintLayout);
                    } else if (return_the_information_from_save(7).equals("amount")) {
                        how_many_times_did_you_do_this_habit_edit_text.setVisibility(View.VISIBLE);
                        negative_view_beside_amount_in_view_habit.setVisibility(View.VISIBLE);
                        positive_view_beside_amount_in_view_habit.setVisibility(View.VISIBLE);
                        negative_button_beside_amount_in_view_habit.setVisibility(View.VISIBLE);
                        positive_button_beside_amount_in_view_habit.setVisibility(View.VISIBLE);
                        ConstraintLayout constraintLayout = getView().findViewById(R.id.layout_containting_the_calender);
                        ConstraintSet constraintSet = new ConstraintSet();
                        constraintSet.clone(constraintLayout);
                        constraintSet.connect(text_asking_did_you_relapse_in_share.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, (int) convertDpToPixel(return_the_length_of_stat() + 28, getContext()));
                        constraintSet.connect(button_too_share_calender_in_good_habits.getId(), ConstraintSet.TOP, how_many_times_did_you_do_this_habit_edit_text.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(15, getContext()));
                        constraintSet.applyTo(constraintLayout);
                    } else if (return_the_information_from_save(7).equals("timer")) {

                    }
                }
                text_asking_did_you_relapse_in_share.setVisibility(View.VISIBLE);
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
            Button button_saying_yes_under_calender_in_good_habits = getView().findViewById(R.id.button_saying_yes_under_calender_in_good_habits);
            Button button_saying_no_under_calender_in_good_habits = getView().findViewById(R.id.button_saying_no_under_calender_in_good_habits);
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
            Button button_saying_yes_under_calender_in_good_habits = getView().findViewById(R.id.button_saying_yes_under_calender_in_good_habits);
            Button button_saying_no_under_calender_in_good_habits = getView().findViewById(R.id.button_saying_no_under_calender_in_good_habits);
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            EditText how_many_times_did_you_do_this_habit_edit_text = getView().findViewById(R.id.how_many_times_did_you_do_this_habit_edit_text);
            String yes_color = "#06a94d";
            String no_color = "#FF2400";
            String[] splitter_temp = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            String month = String.valueOf(return_month_string_to_int(splitter_temp[0]));
            String year = String.valueOf(splitter_temp[1]);
            String[] splitter = color_the_today.split("_");
            if (return_the_information_from_save(6).equals("bad")) {
                if (month.equals(splitter[1]) && year.equals(splitter[2])) {
                    if (colors[Integer.parseInt(splitter[0])].equals(no_color)) {
                        if (!button_saying_yes_under_calender_in_good_habits.getText().toString().contains("✓")) {
                            button_saying_yes_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(return_the_information_from_save(4))));
                            button_saying_yes_under_calender_in_good_habits.setTextColor(Color.WHITE);
                            button_saying_yes_under_calender_in_good_habits.setText(button_saying_yes_under_calender_in_good_habits.getText().toString().concat(" ✓"));
                            button_saying_no_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                            button_saying_no_under_calender_in_good_habits.setTextColor(Color.BLACK);
                            button_saying_no_under_calender_in_good_habits.setText(button_saying_no_under_calender_in_good_habits.getText().toString().replace(" ✓", ""));
                        }
                    } else if (colors[Integer.parseInt(splitter[0])].equals(yes_color)) {
                        if (!button_saying_no_under_calender_in_good_habits.getText().toString().contains("✓")) {
                            button_saying_no_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(return_the_information_from_save(4))));
                            button_saying_no_under_calender_in_good_habits.setTextColor(Color.WHITE);
                            button_saying_no_under_calender_in_good_habits.setText(button_saying_no_under_calender_in_good_habits.getText().toString().concat(" ✓"));
                            button_saying_yes_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                            button_saying_yes_under_calender_in_good_habits.setTextColor(Color.BLACK);
                            button_saying_yes_under_calender_in_good_habits.setText(button_saying_yes_under_calender_in_good_habits.getText().toString().replace(" ✓", ""));
                        }
                    }
                }
            } else if (return_the_information_from_save(6).equals("good")) {
                if (return_the_information_from_save(7).equals("yes/no")) {
                    if (month.equals(splitter[1]) && year.equals(splitter[2])) {
                        if (colors[Integer.parseInt(splitter[0])].equals(yes_color)) {
                            if (!button_saying_yes_under_calender_in_good_habits.getText().toString().contains("✓")) {
                                button_saying_yes_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(return_the_information_from_save(4))));
                                button_saying_yes_under_calender_in_good_habits.setTextColor(Color.WHITE);
                                button_saying_yes_under_calender_in_good_habits.setText(button_saying_yes_under_calender_in_good_habits.getText().toString().concat(" ✓"));
                                button_saying_no_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                                button_saying_no_under_calender_in_good_habits.setTextColor(Color.BLACK);
                                button_saying_no_under_calender_in_good_habits.setText(button_saying_no_under_calender_in_good_habits.getText().toString().replace(" ✓", ""));
                            }
                        } else if (colors[Integer.parseInt(splitter[0])].equals(no_color)) {
                            if (!button_saying_no_under_calender_in_good_habits.getText().toString().contains("✓")) {
                                button_saying_no_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(return_the_information_from_save(4))));
                                button_saying_no_under_calender_in_good_habits.setTextColor(Color.WHITE);
                                button_saying_no_under_calender_in_good_habits.setText(button_saying_no_under_calender_in_good_habits.getText().toString().concat(" ✓"));
                                button_saying_yes_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                                button_saying_yes_under_calender_in_good_habits.setTextColor(Color.BLACK);
                                button_saying_yes_under_calender_in_good_habits.setText(button_saying_yes_under_calender_in_good_habits.getText().toString().replace(" ✓", ""));
                            }
                        }
                    }
                } else if (return_the_information_from_save(7).equals("amount")) {
                    String[] splitter_for_good = color_the_today.split("_");
                    int day_amount = Integer.parseInt(splitter_for_good[0]);
                    int month_amount = Integer.parseInt(splitter_for_good[1]);
                    int year_amount = Integer.parseInt(splitter_for_good[2]);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year_amount, month_amount, day_amount);
                    if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()))) {
                        how_many_times_did_you_do_this_habit_edit_text.setText(String.valueOf(hash_map_amount.get(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()))));
                        if (how_many_times_did_you_do_this_habit_edit_text.hasFocus()) {
                            how_many_times_did_you_do_this_habit_edit_text.setSelection(how_many_times_did_you_do_this_habit_edit_text.getText().toString().length());
                        }
                    } else {
                        how_many_times_did_you_do_this_habit_edit_text.setText("0");
                        if (how_many_times_did_you_do_this_habit_edit_text.hasFocus()) {
                            how_many_times_did_you_do_this_habit_edit_text.setSelection(how_many_times_did_you_do_this_habit_edit_text.getText().toString().length());
                        }
                    }
                } else if (return_the_information_from_save(7).equals("timer")) {

                }
            }
        }
    }

    private void yes_and_no_button_listen_under_the_calender() {
        if (getView() != null) {
            final Button button_saying_yes_under_calender_in_good_habits = getView().findViewById(R.id.button_saying_yes_under_calender_in_good_habits);
            final Button button_saying_no_under_calender_in_good_habits = getView().findViewById(R.id.button_saying_no_under_calender_in_good_habits);
            final TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            button_saying_yes_under_calender_in_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!button_saying_yes_under_calender_in_good_habits.getText().toString().contains("✓")) {
                        String[] split_for_day_month_year = color_the_today.split("_");
                        String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                        int calender_day = Integer.parseInt(split_for_day_month_year[0]);
                        int calender_month = return_month_string_to_int(month_and_year[0]);
                        int calender_year = Integer.parseInt(month_and_year[1]);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(calender_year, calender_month, calender_day);
                        save_the_input_for_good_habit_input("yes", calendar.getTimeInMillis());
                        put_all_the_relapses_into_a_array_list();
                        calculate_all_the_streaks();
                        button_saying_yes_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(return_the_information_from_save(4))));
                        button_saying_yes_under_calender_in_good_habits.setTextColor(Color.WHITE);
                        button_saying_yes_under_calender_in_good_habits.setText(button_saying_yes_under_calender_in_good_habits.getText().toString().concat(" ✓"));
                        button_saying_no_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                        button_saying_no_under_calender_in_good_habits.setTextColor(Color.BLACK);
                        button_saying_no_under_calender_in_good_habits.setText(button_saying_no_under_calender_in_good_habits.getText().toString().replace(" ✓", ""));
                        color_the_calender();
                        set_up_day_of_week_bar_chart();
                        clear_all_the_unders();
                        divide_it_into_weeks();
                        clear_the_middle();
                        make_the_middle_come_again();
                        // draw_pie_chart();
                        // line_chart_for_streak.fitScreen();
                        //set_up_the_various_streak_chart();
                        // setup_the_four_information_card();
                        // set_the_leap_year();
                        //put_values_into_year_in_good_habits();
                        displaying_streak_for_user();
                        calculate_the_average_streak();
                        calculate_the_best_streak();
                        calculate_the_current_streak();
                        set_the_text_for_in_card();
                        draw_pie_chart();
                        set_up_the_various_streak_chart();
                        setup_the_four_information_card();
                        set_the_leap_year();
                        put_values_into_year_in_good_habits();
                        line_chart_for_streak.fitScreen();
                    }
                }
            });
            button_saying_no_under_calender_in_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!button_saying_no_under_calender_in_good_habits.getText().toString().contains("✓")) {
                        String[] split_for_day_month_year = color_the_today.split("_");
                        String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                        int calender_day = Integer.parseInt(split_for_day_month_year[0]);
                        int calender_month = return_month_string_to_int(month_and_year[0]);
                        int calender_year = Integer.parseInt(month_and_year[1]);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(calender_year, calender_month, calender_day);
                        save_the_input_for_good_habit_input("no", calendar.getTimeInMillis());
                        put_all_the_relapses_into_a_array_list();
                        calculate_all_the_streaks();
                        button_saying_no_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(return_the_information_from_save(4))));
                        button_saying_no_under_calender_in_good_habits.setTextColor(Color.WHITE);
                        button_saying_no_under_calender_in_good_habits.setText(button_saying_no_under_calender_in_good_habits.getText().toString().concat(" ✓"));
                        button_saying_yes_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                        button_saying_yes_under_calender_in_good_habits.setTextColor(Color.BLACK);
                        button_saying_yes_under_calender_in_good_habits.setText(button_saying_yes_under_calender_in_good_habits.getText().toString().replace(" ✓", ""));
                        color_the_calender();
                        set_up_day_of_week_bar_chart();
                        clear_all_the_unders();
                        divide_it_into_weeks();
                        clear_the_middle();
                        make_the_middle_come_again();
                        //    draw_pie_chart();
                        //    line_chart_for_streak.fitScreen();
                        //   set_up_the_various_streak_chart();
                        //   show_snack_bar(0);
                        //  setup_the_four_information_card();
                        //  set_the_leap_year();
                        // put_values_into_year_in_good_habits();
                        displaying_streak_for_user();
                        calculate_the_average_streak();
                        calculate_the_best_streak();
                        calculate_the_current_streak();
                        set_the_text_for_in_card();
                        draw_pie_chart();
                        set_up_the_various_streak_chart();
                        setup_the_four_information_card();
                        set_the_leap_year();
                        put_values_into_year_in_good_habits();
                        line_chart_for_streak.fitScreen();
                    }
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
            View calender_under_number_state_1 = getView().findViewById(R.id.calender_under_number_state_1);
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
            View calender_under_number_state_36 = getView().findViewById(R.id.calender_under_number_state_36);
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
            if (calender_under_number_state_1.getBackground() == first_part_rectangle_calender || calender_under_number_state_1.getBackground() == middle_part_rectangle_calender) {
                middle_calender_1.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_2.getBackground() == first_part_rectangle_calender || calender_under_number_state_2.getBackground() == middle_part_rectangle_calender) {
                middle_calender_2.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_3.getBackground() == first_part_rectangle_calender || calender_under_number_state_3.getBackground() == middle_part_rectangle_calender) {
                middle_calender_3.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_4.getBackground() == first_part_rectangle_calender || calender_under_number_state_4.getBackground() == middle_part_rectangle_calender) {
                middle_calender_4.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_5.getBackground() == first_part_rectangle_calender || calender_under_number_state_5.getBackground() == middle_part_rectangle_calender) {
                middle_calender_5.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_6.getBackground() == first_part_rectangle_calender || calender_under_number_state_6.getBackground() == middle_part_rectangle_calender) {
                middle_calender_6.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_8.getBackground() == first_part_rectangle_calender || calender_under_number_state_8.getBackground() == middle_part_rectangle_calender) {
                middle_calender_7.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_9.getBackground() == first_part_rectangle_calender || calender_under_number_state_9.getBackground() == middle_part_rectangle_calender) {
                middle_calender_8.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_10.getBackground() == first_part_rectangle_calender || calender_under_number_state_10.getBackground() == middle_part_rectangle_calender) {
                middle_calender_9.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_11.getBackground() == first_part_rectangle_calender || calender_under_number_state_11.getBackground() == middle_part_rectangle_calender) {
                middle_calender_10.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_12.getBackground() == first_part_rectangle_calender || calender_under_number_state_12.getBackground() == middle_part_rectangle_calender) {
                middle_calender_11.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_13.getBackground() == first_part_rectangle_calender || calender_under_number_state_13.getBackground() == middle_part_rectangle_calender) {
                middle_calender_12.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_15.getBackground() == first_part_rectangle_calender || calender_under_number_state_15.getBackground() == middle_part_rectangle_calender) {
                middle_calender_13.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_16.getBackground() == first_part_rectangle_calender || calender_under_number_state_16.getBackground() == middle_part_rectangle_calender) {
                middle_calender_14.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_17.getBackground() == first_part_rectangle_calender || calender_under_number_state_17.getBackground() == middle_part_rectangle_calender) {
                middle_calender_15.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_18.getBackground() == first_part_rectangle_calender || calender_under_number_state_18.getBackground() == middle_part_rectangle_calender) {
                middle_calender_16.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_19.getBackground() == first_part_rectangle_calender || calender_under_number_state_19.getBackground() == middle_part_rectangle_calender) {
                middle_calender_17.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_20.getBackground() == first_part_rectangle_calender || calender_under_number_state_20.getBackground() == middle_part_rectangle_calender) {
                middle_calender_18.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_22.getBackground() == first_part_rectangle_calender || calender_under_number_state_22.getBackground() == middle_part_rectangle_calender) {
                middle_calender_19.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_23.getBackground() == first_part_rectangle_calender || calender_under_number_state_23.getBackground() == middle_part_rectangle_calender) {
                middle_calender_20.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_24.getBackground() == first_part_rectangle_calender || calender_under_number_state_24.getBackground() == middle_part_rectangle_calender) {
                middle_calender_21.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_25.getBackground() == first_part_rectangle_calender || calender_under_number_state_25.getBackground() == middle_part_rectangle_calender) {
                middle_calender_22.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_26.getBackground() == first_part_rectangle_calender || calender_under_number_state_26.getBackground() == middle_part_rectangle_calender) {
                middle_calender_23.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_27.getBackground() == first_part_rectangle_calender || calender_under_number_state_27.getBackground() == middle_part_rectangle_calender) {
                middle_calender_24.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_29.getBackground() == first_part_rectangle_calender || calender_under_number_state_29.getBackground() == middle_part_rectangle_calender) {
                middle_calender_25.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_30.getBackground() == first_part_rectangle_calender || calender_under_number_state_30.getBackground() == middle_part_rectangle_calender) {
                middle_calender_26.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_31.getBackground() == first_part_rectangle_calender || calender_under_number_state_31.getBackground() == middle_part_rectangle_calender) {
                middle_calender_27.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_32.getBackground() == first_part_rectangle_calender || calender_under_number_state_32.getBackground() == middle_part_rectangle_calender) {
                middle_calender_28.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_33.getBackground() == first_part_rectangle_calender || calender_under_number_state_33.getBackground() == middle_part_rectangle_calender) {
                middle_calender_29.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_34.getBackground() == first_part_rectangle_calender || calender_under_number_state_34.getBackground() == middle_part_rectangle_calender) {
                middle_calender_30.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_36.getBackground() == first_part_rectangle_calender || calender_under_number_state_36.getBackground() == middle_part_rectangle_calender) {
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

    private void color_the_middle() {
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
            middle_calender_1.setBackgroundColor(color);
            middle_calender_2.setBackgroundColor(color);
            middle_calender_3.setBackgroundColor(color);
            middle_calender_4.setBackgroundColor(color);
            middle_calender_5.setBackgroundColor(color);
            middle_calender_6.setBackgroundColor(color);
            middle_calender_7.setBackgroundColor(color);
            middle_calender_8.setBackgroundColor(color);
            middle_calender_9.setBackgroundColor(color);
            middle_calender_10.setBackgroundColor(color);
            middle_calender_11.setBackgroundColor(color);
            middle_calender_12.setBackgroundColor(color);
            middle_calender_13.setBackgroundColor(color);
            middle_calender_14.setBackgroundColor(color);
            middle_calender_15.setBackgroundColor(color);
            middle_calender_16.setBackgroundColor(color);
            middle_calender_17.setBackgroundColor(color);
            middle_calender_18.setBackgroundColor(color);
            middle_calender_19.setBackgroundColor(color);
            middle_calender_20.setBackgroundColor(color);
            middle_calender_21.setBackgroundColor(color);
            middle_calender_22.setBackgroundColor(color);
            middle_calender_23.setBackgroundColor(color);
            middle_calender_24.setBackgroundColor(color);
            middle_calender_25.setBackgroundColor(color);
            middle_calender_26.setBackgroundColor(color);
            middle_calender_27.setBackgroundColor(color);
            middle_calender_28.setBackgroundColor(color);
            middle_calender_29.setBackgroundColor(color);
            middle_calender_30.setBackgroundColor(color);
            middle_calender_31.setBackgroundColor(color);
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
            View calender_under_number_state_1 = getView().findViewById(R.id.calender_under_number_state_1);
            View calender_under_number_state_2 = getView().findViewById(R.id.calender_under_number_state_2);
            View calender_under_number_state_3 = getView().findViewById(R.id.calender_under_number_state_3);
            View calender_under_number_state_4 = getView().findViewById(R.id.calender_under_number_state_4);
            View calender_under_number_state_5 = getView().findViewById(R.id.calender_under_number_state_5);
            View calender_under_number_state_6 = getView().findViewById(R.id.calender_under_number_state_6);
            View calender_under_number_state_7 = getView().findViewById(R.id.calender_under_number_state_7);

            String[] date_split = date.split("split");
            int empty_length = 7 - date_split.length;
            for (int i = 0; i < empty_length; i++) {
                date = "empty".concat("split").concat(date);
            }
            String[] splitter_second = date.split("split");
            if (splitter_second[0].equals("empty")) {
                calender_under_number_state_1.setVisibility(View.INVISIBLE);
            } else if (splitter_second[0].equals("start")) {
                calender_under_number_state_1.setVisibility(View.VISIBLE);
                calender_under_number_state_1.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[0].equals("middle")) {
                calender_under_number_state_1.setVisibility(View.VISIBLE);
                calender_under_number_state_1.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[0].equals("end")) {
                calender_under_number_state_1.setVisibility(View.VISIBLE);
                calender_under_number_state_1.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[0].equals("beg_last")) {
                calender_under_number_state_1.setVisibility(View.VISIBLE);
                calender_under_number_state_1.setBackground(first_and_last_part_rectangle_calender);
            }
            if (splitter_second[1].equals("empty")) {
                calender_under_number_state_2.setVisibility(View.INVISIBLE);
            } else if (splitter_second[1].equals("start")) {
                calender_under_number_state_2.setVisibility(View.VISIBLE);
                calender_under_number_state_2.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[1].equals("middle")) {
                calender_under_number_state_2.setVisibility(View.VISIBLE);
                calender_under_number_state_2.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[1].equals("end")) {
                calender_under_number_state_2.setVisibility(View.VISIBLE);
                calender_under_number_state_2.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[1].equals("beg_last")) {
                calender_under_number_state_2.setVisibility(View.VISIBLE);
                calender_under_number_state_2.setBackground(first_and_last_part_rectangle_calender);
            }
            if (splitter_second[2].equals("empty")) {
                calender_under_number_state_3.setVisibility(View.INVISIBLE);
            } else if (splitter_second[2].equals("start")) {
                calender_under_number_state_3.setVisibility(View.VISIBLE);
                calender_under_number_state_3.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[2].equals("middle")) {
                calender_under_number_state_3.setVisibility(View.VISIBLE);
                calender_under_number_state_3.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[2].equals("end")) {
                calender_under_number_state_3.setVisibility(View.VISIBLE);
                calender_under_number_state_3.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[2].equals("beg_last")) {
                calender_under_number_state_3.setVisibility(View.VISIBLE);
                calender_under_number_state_3.setBackground(first_and_last_part_rectangle_calender);
            }
            if (splitter_second[3].equals("empty")) {
                calender_under_number_state_4.setVisibility(View.INVISIBLE);
            } else if (splitter_second[3].equals("start")) {
                calender_under_number_state_4.setVisibility(View.VISIBLE);
                calender_under_number_state_4.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[3].equals("middle")) {
                calender_under_number_state_4.setVisibility(View.VISIBLE);
                calender_under_number_state_4.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[3].equals("end")) {
                calender_under_number_state_4.setVisibility(View.VISIBLE);
                calender_under_number_state_4.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[3].equals("beg_last")) {
                calender_under_number_state_4.setVisibility(View.VISIBLE);
                calender_under_number_state_4.setBackground(first_and_last_part_rectangle_calender);
            }
            if (splitter_second[4].equals("empty")) {
                calender_under_number_state_5.setVisibility(View.INVISIBLE);
            } else if (splitter_second[4].equals("start")) {
                calender_under_number_state_5.setVisibility(View.VISIBLE);
                calender_under_number_state_5.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[4].equals("middle")) {
                calender_under_number_state_5.setVisibility(View.VISIBLE);
                calender_under_number_state_5.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[4].equals("end")) {
                calender_under_number_state_5.setVisibility(View.VISIBLE);
                calender_under_number_state_5.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[4].equals("beg_last")) {
                calender_under_number_state_5.setVisibility(View.VISIBLE);
                calender_under_number_state_5.setBackground(first_and_last_part_rectangle_calender);
            }
            if (splitter_second[5].equals("empty")) {
                calender_under_number_state_6.setVisibility(View.INVISIBLE);
            } else if (splitter_second[5].equals("start")) {
                calender_under_number_state_6.setVisibility(View.VISIBLE);
                calender_under_number_state_6.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[5].equals("middle")) {
                calender_under_number_state_6.setVisibility(View.VISIBLE);
                calender_under_number_state_6.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[5].equals("end")) {
                calender_under_number_state_6.setVisibility(View.VISIBLE);
                calender_under_number_state_6.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[5].equals("beg_last")) {
                calender_under_number_state_6.setVisibility(View.VISIBLE);
                calender_under_number_state_6.setBackground(first_and_last_part_rectangle_calender);
            }
            if (splitter_second[6].equals("empty")) {
                calender_under_number_state_7.setVisibility(View.INVISIBLE);
            } else if (splitter_second[6].equals("start")) {
                calender_under_number_state_7.setVisibility(View.VISIBLE);
                calender_under_number_state_7.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[6].equals("middle")) {
                calender_under_number_state_7.setVisibility(View.VISIBLE);
                calender_under_number_state_7.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[6].equals("end")) {
                calender_under_number_state_7.setVisibility(View.VISIBLE);
                calender_under_number_state_7.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[6].equals("beg_last")) {
                calender_under_number_state_7.setVisibility(View.VISIBLE);
                calender_under_number_state_7.setBackground(first_and_last_part_rectangle_calender);
            }
        }
    }

    private void set_the_second_line(String date) {
        if (getView() != null) {
            View calender_under_number_state_8 = getView().findViewById(R.id.calender_under_number_state_8);
            View calender_under_number_state_9 = getView().findViewById(R.id.calender_under_number_state_9);
            View calender_under_number_state_10 = getView().findViewById(R.id.calender_under_number_state_10);
            View calender_under_number_state_11 = getView().findViewById(R.id.calender_under_number_state_11);
            View calender_under_number_state_12 = getView().findViewById(R.id.calender_under_number_state_12);
            View calender_under_number_state_13 = getView().findViewById(R.id.calender_under_number_state_13);
            View calender_under_number_state_14 = getView().findViewById(R.id.calender_under_number_state_14);
            String[] date_split = date.split("split");
            if (date_split[0].equals("start")) {
                calender_under_number_state_8.setBackground(first_part_rectangle_calender);
            } else if (date_split[0].equals("middle")) {
                calender_under_number_state_8.setBackground(middle_part_rectangle_calender);
            } else if (date_split[0].equals("end")) {
                calender_under_number_state_8.setBackground(last_part_rectangle_calender);
            } else if (date_split[0].equals("beg_last")) {
                calender_under_number_state_8.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[1].equals("start")) {
                calender_under_number_state_9.setBackground(first_part_rectangle_calender);
            } else if (date_split[1].equals("middle")) {
                calender_under_number_state_9.setBackground(middle_part_rectangle_calender);
            } else if (date_split[1].equals("end")) {
                calender_under_number_state_9.setBackground(last_part_rectangle_calender);
            } else if (date_split[1].equals("beg_last")) {
                calender_under_number_state_9.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[2].equals("start")) {
                calender_under_number_state_10.setBackground(first_part_rectangle_calender);
            } else if (date_split[2].equals("middle")) {
                calender_under_number_state_10.setBackground(middle_part_rectangle_calender);
            } else if (date_split[2].equals("end")) {
                calender_under_number_state_10.setBackground(last_part_rectangle_calender);
            } else if (date_split[2].equals("beg_last")) {
                calender_under_number_state_10.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[3].equals("start")) {
                calender_under_number_state_11.setBackground(first_part_rectangle_calender);
            } else if (date_split[3].equals("middle")) {
                calender_under_number_state_11.setBackground(middle_part_rectangle_calender);
            } else if (date_split[3].equals("end")) {
                calender_under_number_state_11.setBackground(last_part_rectangle_calender);
            } else if (date_split[3].equals("beg_last")) {
                calender_under_number_state_11.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[4].equals("start")) {
                calender_under_number_state_12.setBackground(first_part_rectangle_calender);
            } else if (date_split[4].equals("middle")) {
                calender_under_number_state_12.setBackground(middle_part_rectangle_calender);
            } else if (date_split[4].equals("end")) {
                calender_under_number_state_12.setBackground(last_part_rectangle_calender);
            } else if (date_split[4].equals("beg_last")) {
                calender_under_number_state_12.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[5].equals("start")) {
                calender_under_number_state_13.setBackground(first_part_rectangle_calender);
            } else if (date_split[5].equals("middle")) {
                calender_under_number_state_13.setBackground(middle_part_rectangle_calender);
            } else if (date_split[5].equals("end")) {
                calender_under_number_state_13.setBackground(last_part_rectangle_calender);
            } else if (date_split[5].equals("beg_last")) {
                calender_under_number_state_13.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[6].equals("start")) {
                calender_under_number_state_14.setBackground(first_part_rectangle_calender);
            } else if (date_split[6].equals("middle")) {
                calender_under_number_state_14.setBackground(middle_part_rectangle_calender);
            } else if (date_split[6].equals("end")) {
                calender_under_number_state_14.setBackground(last_part_rectangle_calender);
            } else if (date_split[6].equals("beg_last")) {
                calender_under_number_state_14.setBackground(first_and_last_part_rectangle_calender);
            }
        }
    }

    private void set_the_third_line(String date) {
        if (getView() != null) {
            View calender_under_number_state_15 = getView().findViewById(R.id.calender_under_number_state_15);
            View calender_under_number_state_16 = getView().findViewById(R.id.calender_under_number_state_16);
            View calender_under_number_state_17 = getView().findViewById(R.id.calender_under_number_state_17);
            View calender_under_number_state_18 = getView().findViewById(R.id.calender_under_number_state_18);
            View calender_under_number_state_19 = getView().findViewById(R.id.calender_under_number_state_19);
            View calender_under_number_state_20 = getView().findViewById(R.id.calender_under_number_state_20);
            View calender_under_number_state_21 = getView().findViewById(R.id.calender_under_number_state_21);
            String[] date_split = date.split("split");
            if (date_split[0].equals("start")) {
                calender_under_number_state_15.setBackground(first_part_rectangle_calender);
            } else if (date_split[0].equals("middle")) {
                calender_under_number_state_15.setBackground(middle_part_rectangle_calender);
            } else if (date_split[0].equals("end")) {
                calender_under_number_state_15.setBackground(last_part_rectangle_calender);
            } else if (date_split[0].equals("beg_last")) {
                calender_under_number_state_15.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[1].equals("start")) {
                calender_under_number_state_16.setBackground(first_part_rectangle_calender);
            } else if (date_split[1].equals("middle")) {
                calender_under_number_state_16.setBackground(middle_part_rectangle_calender);
            } else if (date_split[1].equals("end")) {
                calender_under_number_state_16.setBackground(last_part_rectangle_calender);
            } else if (date_split[1].equals("beg_last")) {
                calender_under_number_state_16.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[2].equals("start")) {
                calender_under_number_state_17.setBackground(first_part_rectangle_calender);
            } else if (date_split[2].equals("middle")) {
                calender_under_number_state_17.setBackground(middle_part_rectangle_calender);
            } else if (date_split[2].equals("end")) {
                calender_under_number_state_17.setBackground(last_part_rectangle_calender);
            } else if (date_split[2].equals("beg_last")) {
                calender_under_number_state_17.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[3].equals("start")) {
                calender_under_number_state_18.setBackground(first_part_rectangle_calender);
            } else if (date_split[3].equals("middle")) {
                calender_under_number_state_18.setBackground(middle_part_rectangle_calender);
            } else if (date_split[3].equals("end")) {
                calender_under_number_state_18.setBackground(last_part_rectangle_calender);
            } else if (date_split[3].equals("beg_last")) {
                calender_under_number_state_18.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[4].equals("start")) {
                calender_under_number_state_19.setBackground(first_part_rectangle_calender);
            } else if (date_split[4].equals("middle")) {
                calender_under_number_state_19.setBackground(middle_part_rectangle_calender);
            } else if (date_split[4].equals("end")) {
                calender_under_number_state_19.setBackground(last_part_rectangle_calender);
            } else if (date_split[4].equals("beg_last")) {
                calender_under_number_state_19.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[5].equals("start")) {
                calender_under_number_state_20.setBackground(first_part_rectangle_calender);
            } else if (date_split[5].equals("middle")) {
                calender_under_number_state_20.setBackground(middle_part_rectangle_calender);
            } else if (date_split[5].equals("end")) {
                calender_under_number_state_20.setBackground(last_part_rectangle_calender);
            } else if (date_split[5].equals("beg_last")) {
                calender_under_number_state_20.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[6].equals("start")) {
                calender_under_number_state_21.setBackground(first_part_rectangle_calender);
            } else if (date_split[6].equals("middle")) {
                calender_under_number_state_21.setBackground(middle_part_rectangle_calender);
            } else if (date_split[6].equals("end")) {
                calender_under_number_state_21.setBackground(last_part_rectangle_calender);
            } else if (date_split[6].equals("beg_last")) {
                calender_under_number_state_21.setBackground(first_and_last_part_rectangle_calender);
            }
        }
    }

    private void set_the_fourth_line(String date) {
        if (getView() != null) {
            View calender_under_number_state_22 = getView().findViewById(R.id.calender_under_number_state_22);
            View calender_under_number_state_23 = getView().findViewById(R.id.calender_under_number_state_23);
            View calender_under_number_state_24 = getView().findViewById(R.id.calender_under_number_state_24);
            View calender_under_number_state_25 = getView().findViewById(R.id.calender_under_number_state_25);
            View calender_under_number_state_26 = getView().findViewById(R.id.calender_under_number_state_26);
            View calender_under_number_state_27 = getView().findViewById(R.id.calender_under_number_state_27);
            View calender_under_number_state_28 = getView().findViewById(R.id.calender_under_number_state_28);
            String[] date_split = date.split("split");
            if (date_split[0].equals("start")) {
                calender_under_number_state_22.setBackground(first_part_rectangle_calender);
            } else if (date_split[0].equals("middle")) {
                calender_under_number_state_22.setBackground(middle_part_rectangle_calender);
            } else if (date_split[0].equals("end")) {
                calender_under_number_state_22.setBackground(last_part_rectangle_calender);
            } else if (date_split[0].equals("beg_last")) {
                calender_under_number_state_22.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[1].equals("start")) {
                calender_under_number_state_23.setBackground(first_part_rectangle_calender);
            } else if (date_split[1].equals("middle")) {
                calender_under_number_state_23.setBackground(middle_part_rectangle_calender);
            } else if (date_split[1].equals("end")) {
                calender_under_number_state_23.setBackground(last_part_rectangle_calender);
            } else if (date_split[1].equals("beg_last")) {
                calender_under_number_state_23.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[2].equals("start")) {
                calender_under_number_state_24.setBackground(first_part_rectangle_calender);
            } else if (date_split[2].equals("middle")) {
                calender_under_number_state_24.setBackground(middle_part_rectangle_calender);
            } else if (date_split[2].equals("end")) {
                calender_under_number_state_24.setBackground(last_part_rectangle_calender);
            } else if (date_split[2].equals("beg_last")) {
                calender_under_number_state_24.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[3].equals("start")) {
                calender_under_number_state_25.setBackground(first_part_rectangle_calender);
            } else if (date_split[3].equals("middle")) {
                calender_under_number_state_25.setBackground(middle_part_rectangle_calender);
            } else if (date_split[3].equals("end")) {
                calender_under_number_state_25.setBackground(last_part_rectangle_calender);
            } else if (date_split[3].equals("beg_last")) {
                calender_under_number_state_25.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[4].equals("start")) {
                calender_under_number_state_26.setBackground(first_part_rectangle_calender);
            } else if (date_split[4].equals("middle")) {
                calender_under_number_state_26.setBackground(middle_part_rectangle_calender);
            } else if (date_split[4].equals("end")) {
                calender_under_number_state_26.setBackground(last_part_rectangle_calender);
            } else if (date_split[4].equals("beg_last")) {
                calender_under_number_state_26.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[5].equals("start")) {
                calender_under_number_state_27.setBackground(first_part_rectangle_calender);
            } else if (date_split[5].equals("middle")) {
                calender_under_number_state_27.setBackground(middle_part_rectangle_calender);
            } else if (date_split[5].equals("end")) {
                calender_under_number_state_27.setBackground(last_part_rectangle_calender);
            } else if (date_split[5].equals("beg_last")) {
                calender_under_number_state_27.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[6].equals("start")) {
                calender_under_number_state_28.setBackground(first_part_rectangle_calender);
            } else if (date_split[6].equals("middle")) {
                calender_under_number_state_28.setBackground(middle_part_rectangle_calender);
            } else if (date_split[6].equals("end")) {
                calender_under_number_state_28.setBackground(last_part_rectangle_calender);
            } else if (date_split[6].equals("beg_last")) {
                calender_under_number_state_28.setBackground(first_and_last_part_rectangle_calender);
            }
        }
    }

    private void set_the_fifth_line(String date) {
        if (getView() != null) {
            View calender_under_number_state_29 = getView().findViewById(R.id.calender_under_number_state_29);
            View calender_under_number_state_30 = getView().findViewById(R.id.calender_under_number_state_30);
            View calender_under_number_state_31 = getView().findViewById(R.id.calender_under_number_state_31);
            View calender_under_number_state_32 = getView().findViewById(R.id.calender_under_number_state_32);
            View calender_under_number_state_33 = getView().findViewById(R.id.calender_under_number_state_33);
            View calender_under_number_state_34 = getView().findViewById(R.id.calender_under_number_state_34);
            View calender_under_number_state_35 = getView().findViewById(R.id.calender_under_number_state_35);
            String[] date_split = date.split("split");
            if (date_split.length > 0) {
                if (date_split[0].equals("start")) {
                    calender_under_number_state_29.setBackground(first_part_rectangle_calender);
                } else if (date_split[0].equals("middle")) {
                    calender_under_number_state_29.setBackground(middle_part_rectangle_calender);
                } else if (date_split[0].equals("end")) {
                    calender_under_number_state_29.setBackground(last_part_rectangle_calender);
                } else if (date_split[0].equals("beg_last")) {
                    calender_under_number_state_29.setBackground(first_and_last_part_rectangle_calender);
                }
            }
            if (date_split.length > 1) {
                if (date_split[1].equals("start")) {
                    calender_under_number_state_30.setBackground(first_part_rectangle_calender);
                } else if (date_split[1].equals("middle")) {
                    calender_under_number_state_30.setBackground(middle_part_rectangle_calender);
                } else if (date_split[1].equals("end")) {
                    calender_under_number_state_30.setBackground(last_part_rectangle_calender);
                } else if (date_split[1].equals("beg_last")) {
                    calender_under_number_state_30.setBackground(first_and_last_part_rectangle_calender);
                }
            }
            if (date_split.length > 2) {
                if (date_split[2].equals("start")) {
                    calender_under_number_state_31.setBackground(first_part_rectangle_calender);
                } else if (date_split[2].equals("middle")) {
                    calender_under_number_state_31.setBackground(middle_part_rectangle_calender);
                } else if (date_split[2].equals("end")) {
                    calender_under_number_state_31.setBackground(last_part_rectangle_calender);
                } else if (date_split[2].equals("beg_last")) {
                    calender_under_number_state_31.setBackground(first_and_last_part_rectangle_calender);
                }
            }
            if (date_split.length > 3) {
                if (date_split[3].equals("start")) {
                    calender_under_number_state_32.setBackground(first_part_rectangle_calender);
                } else if (date_split[3].equals("middle")) {
                    calender_under_number_state_32.setBackground(middle_part_rectangle_calender);
                } else if (date_split[3].equals("end")) {
                    calender_under_number_state_32.setBackground(last_part_rectangle_calender);
                } else if (date_split[3].equals("beg_last")) {
                    calender_under_number_state_32.setBackground(first_and_last_part_rectangle_calender);
                }
            }
            if (date_split.length > 4) {
                if (date_split[4].equals("start")) {
                    calender_under_number_state_33.setBackground(first_part_rectangle_calender);
                } else if (date_split[4].equals("middle")) {
                    calender_under_number_state_33.setBackground(middle_part_rectangle_calender);
                } else if (date_split[4].equals("end")) {
                    calender_under_number_state_33.setBackground(last_part_rectangle_calender);
                } else if (date_split[4].equals("beg_last")) {
                    calender_under_number_state_33.setBackground(first_and_last_part_rectangle_calender);
                }
            }
            if (date_split.length > 5) {
                if (date_split[5].equals("start")) {
                    calender_under_number_state_34.setBackground(first_part_rectangle_calender);
                } else if (date_split[5].equals("middle")) {
                    calender_under_number_state_34.setBackground(middle_part_rectangle_calender);
                } else if (date_split[5].equals("end")) {
                    calender_under_number_state_34.setBackground(last_part_rectangle_calender);
                } else if (date_split[5].equals("beg_last")) {
                    calender_under_number_state_34.setBackground(first_and_last_part_rectangle_calender);
                }
            }
            if (date_split.length > 6) {
                if (date_split[6].equals("start")) {
                    calender_under_number_state_35.setBackground(first_part_rectangle_calender);
                } else if (date_split[6].equals("middle")) {
                    calender_under_number_state_35.setBackground(middle_part_rectangle_calender);
                } else if (date_split[6].equals("end")) {
                    calender_under_number_state_35.setBackground(last_part_rectangle_calender);
                } else if (date_split[6].equals("beg_last")) {
                    calender_under_number_state_35.setBackground(first_and_last_part_rectangle_calender);
                }
            }
        }
    }

    private void set_the_sixth_line(String date) {
        if (getView() != null) {
            View calender_under_number_state_36 = getView().findViewById(R.id.calender_under_number_state_36);
            View calender_under_number_state_37 = getView().findViewById(R.id.calender_under_number_state_37);
            String[] date_split = date.split("split");
            if (date_split.length > 0) {
                if (date_split[0].equals("start")) {
                    calender_under_number_state_36.setBackground(first_part_rectangle_calender);
                } else if (date_split[0].equals("middle")) {
                    calender_under_number_state_36.setBackground(middle_part_rectangle_calender);
                } else if (date_split[0].equals("end")) {
                    calender_under_number_state_36.setBackground(last_part_rectangle_calender);
                } else if (date_split[0].equals("beg_last")) {
                    calender_under_number_state_36.setBackground(first_and_last_part_rectangle_calender);
                }
            }
            if (date_split.length > 1) {
                if (date_split[1].equals("start")) {
                    calender_under_number_state_37.setBackground(first_part_rectangle_calender);
                } else if (date_split[1].equals("middle")) {
                    calender_under_number_state_37.setBackground(middle_part_rectangle_calender);
                } else if (date_split[1].equals("end")) {
                    calender_under_number_state_37.setBackground(last_part_rectangle_calender);
                } else if (date_split[1].equals("beg_last")) {
                    calender_under_number_state_37.setBackground(first_and_last_part_rectangle_calender);
                }
            }
        }
    }

    private String return_the_state_of_the_days() {
        if (getView() != null) {
            String month_info = "";
            String yes_color = "#06a94d";
            String yes_color_no_input = "#6a5acd";
            String no_color = "#FF2400";
            String no_color_no_input = "#000000";
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
        if (return_the_information_from_save(6).equals("bad")) {
            int index = Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(milli));
            if (index >= 0) {
                return false;
            } else {
                return true;
            }
        } else {
            if (return_the_information_from_save(7).equals("yes/no")) {
                if (return_the_information_from_save(8).equals("everyday")) {
                    int index = Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(milli));
                    if (index >= 0) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (return_the_information_from_save(8).equals("daysperweek")) {
                    Calendar calender = Calendar.getInstance();
                    calender.setTimeInMillis(milli);
                    if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                        if (!return_the_information_from_save(10).contains("Mo") || Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(milli)) >= 0) {
                            return true;
                        } else {
                            return false;
                        }
                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                        if (!return_the_information_from_save(10).contains("Tu") || Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(milli)) >= 0) {
                            return true;
                        } else {
                            return false;
                        }
                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                        if (!return_the_information_from_save(10).contains("We") || Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(milli)) >= 0) {
                            return true;
                        } else {
                            return false;
                        }
                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                        if (!return_the_information_from_save(10).contains("Th") || Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(milli)) >= 0) {
                            return true;
                        } else {
                            return false;
                        }
                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                        if (!return_the_information_from_save(10).contains("Fr") || Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(milli)) >= 0) {
                            return true;
                        } else {
                            return false;
                        }
                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                        if (!return_the_information_from_save(10).contains("Sa") || Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(milli)) >= 0) {
                            return true;
                        } else {
                            return false;
                        }
                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        if (!return_the_information_from_save(10).contains("Su") || Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(milli)) >= 0) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false; //shoudn't happen
                    }
                } else if (return_the_information_from_save(8).equals("everyndays")) {
                    int index = Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(milli));
                    if (index >= 0) {
                        return true;
                    } else {
                        if (history_of_relapse.size() > 0) {
                            index = (-index) - 2;
                            if (index < 0) {
                                return false;
                            } else {
                                long last_relapse = history_of_relapse.get(index);
                                if (Simplify_the_time.return_time_in_midnight(milli) - Simplify_the_time.return_time_in_midnight(last_relapse) >= Long.parseLong(return_the_information_from_save(10)) * TimeUnit.DAYS.toMillis(1)) {
                                    return false;
                                } else {
                                    return true;
                                }
                            }
                        } else {
                            return false;
                        }
                    }
                } else if (return_the_information_from_save(8).equals("dayspermonth")) {
                    //return true;
                    Calendar calender = Calendar.getInstance();
                    calender.setTimeInMillis(milli);
                    int day_of_the_month = calender.get(Calendar.DAY_OF_MONTH);
                    if (!days_of_months_good_habit.contains(day_of_the_month)) {
                        return true;
                    } else {
                        int index = Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(milli));
                        if (index >= 0) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else if (return_the_information_from_save(8).equals("timesaperiod")) {
                    return true;
                } else {
                    return false; // shoudn't happen
                }
            } else if (return_the_information_from_save(7).equals("amount")) {
                if (return_the_information_from_save(8).equals("everyday")) {
                    if (hash_map_amount != null && hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(milli))) {
                        int amount = hash_map_amount.get(Simplify_the_time.return_time_in_midnight(milli));
                        if (amount >= Integer.parseInt(return_the_information_from_save(9))) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else if (return_the_information_from_save(8).equals("daysperweek")) {
                    Calendar calender = Calendar.getInstance();
                    calender.setTimeInMillis(milli);
                    if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                        if (!return_the_information_from_save(10).contains("Mo") || (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(milli)) && is_amount_good_for_streak(Simplify_the_time.return_time_in_midnight(milli)))) {
                            return true;
                        } else {
                            return false;
                        }
                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                        if (!return_the_information_from_save(10).contains("Tu") || (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(milli)) && is_amount_good_for_streak(Simplify_the_time.return_time_in_midnight(milli)))) {
                            return true;
                        } else {
                            return false;
                        }
                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                        if (!return_the_information_from_save(10).contains("We") || (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(milli)) && is_amount_good_for_streak(Simplify_the_time.return_time_in_midnight(milli)))) {
                            return true;
                        } else {
                            return false;
                        }
                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                        if (!return_the_information_from_save(10).contains("Th") || (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(milli)) && is_amount_good_for_streak(Simplify_the_time.return_time_in_midnight(milli)))) {
                            return true;
                        } else {
                            return false;
                        }
                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                        if (!return_the_information_from_save(10).contains("Fr") || (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(milli)) && is_amount_good_for_streak(Simplify_the_time.return_time_in_midnight(milli)))) {
                            return true;
                        } else {
                            return false;
                        }
                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                        if (!return_the_information_from_save(10).contains("Sa") || (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(milli)) && is_amount_good_for_streak(Simplify_the_time.return_time_in_midnight(milli)))) {
                            return true;
                        } else {
                            return false;
                        }
                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        if (!return_the_information_from_save(10).contains("Su") || (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(milli)) && is_amount_good_for_streak(Simplify_the_time.return_time_in_midnight(milli)))) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false; //shoudn't happen
                    }
                } else if (return_the_information_from_save(8).equals("everyndays")) {
                    if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(milli)) && is_amount_good_for_streak(Simplify_the_time.return_time_in_midnight(milli))) {
                        return true;
                    } else {
                        if (hash_map_amount.size() > 0) {
                            milli = Simplify_the_time.return_time_in_midnight(milli);
                            for (int i = 1; i < Integer.parseInt(return_the_information_from_save(10)); i++) {
                                if (hash_map_amount.containsKey(milli - TimeUnit.DAYS.toMillis(i)) && hash_map_amount.get(milli - TimeUnit.DAYS.toMillis(i)) >= Integer.parseInt(return_the_information_from_save(9))) {
                                    return true;
                                }
                            }
                            return false;
                        } else {
                            return false;
                        }
                    }
                } else if (return_the_information_from_save(8).equals("dayspermonth")) {
                    return false;
                } else {
                    return false; // should not happen
                }
            } else if (return_the_information_from_save(7).equals("timer")) {
                return false;
            } else {
                return false;
            }
        }
    }

    private void declare_start_date() {
        start_date = Long.parseLong(return_the_information_from_save(2));
    }

    private String return_the_information_from_save(int which) {
        Room_database_habits room_database_habits = Room_database_habits.getInstance(getContext());
        List<habits_data_class> list = room_database_habits.dao_for_habits_data().getAll();
        habits_data_class habits_data_class = list.get(value_for_position);
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
        return ""; // cant be otherwise
    }

    private void put_all_the_relapses_into_a_array_list() {
        Room_database_habits room_database_habits = Room_database_habits.getInstance(getContext());
        List<habits_data_class> list = room_database_habits.dao_for_habits_data().getAll();
        habits_data_class habits_data_class = list.get(value_for_position);
        history_of_relapse = new ArrayList<>();
        hash_map_amount = new HashMap<>();
        if (return_the_information_from_save(6).equals("bad")) {
            if (habits_data_class.getRelapse() != null && !habits_data_class.getRelapse().isEmpty()) {
                history_of_relapse.addAll(return_relapses());
            }
        } else if (return_the_information_from_save(6).equals("good")) {
            if (return_the_information_from_save(7).equals("yes/no")) {
                if (habits_data_class.getRelapse() != null && !habits_data_class.getRelapse().isEmpty()) {
                    history_of_relapse.addAll(return_relapses());
                }
            } else if (return_the_information_from_save(7).equals("amount")) {
                hash_map_amount.putAll(return_relapse_amount());
            } else if (return_the_information_from_save(7).equals("timer")) {

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

    private int return_the_streak() {
        if (each_streak_lengths.isEmpty()) {
            return 0;
        } else {
            return each_streak_lengths.get(each_streak_lengths.size() - 1);
        }
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
        Room_database_habits database_habits = Room_database_habits.getInstance(getContext());
        database_habits.dao_for_habits_data().update_relapse(Integer.parseInt(return_the_information_from_save(5)), relapse);
    }

    private void save_the_input_for_good_habit_amount_input(int amount, long time_in_milli) {
        HashMap<Long, Integer> hashMap_relapse = return_relapse_amount();
        time_in_milli = Simplify_the_time.return_time_in_midnight(time_in_milli);
        hashMap_relapse.put(time_in_milli, amount);
        Room_database_habits database_habits = Room_database_habits.getInstance(getContext());
        database_habits.dao_for_habits_data().update_relapse_amount(Integer.parseInt(return_the_information_from_save(5)), hashMap_relapse);
    }

    public void insert(long x) {
        int pos = Collections.binarySearch(history_of_relapse, x);
        if (pos < 0) {
            history_of_relapse.add(-pos - 1, x);
        }
    }

    private void calculate_the_average_streak() {
        if (getView() != null) {
            Log.w("new_test", "inside average" + each_streak_lengths.toString());
            TextView actual_average_in_text_number_in_good_habits = getView().findViewById(R.id.actual_average_in_text_number_in_good_habits);
            int sum = 0;
            for (Integer mark : each_streak_lengths) {
                sum += mark;
            }
            float average = ((float) sum / each_streak_lengths.size());
            actual_average_in_text_number_in_good_habits.setText(String.format("%.2f", average).concat(" days"));
        }
    }

    private void calculate_the_current_streak() {
        if (getView() != null) {
            TextView actual_current_in_text_number_in_good_habits = getView().findViewById(R.id.actual_current_in_text_number_in_good_habits);
            if (return_the_streak() == 1) {
                actual_current_in_text_number_in_good_habits.setText(String.valueOf(return_the_streak()));
            } else {
                actual_current_in_text_number_in_good_habits.setText(String.valueOf(return_the_streak()));
            }
        }
    }

    private void calculate_the_best_streak() {
        if (getView() != null) {
            TextView actual_best_in_text_number_in_good_habits = getView().findViewById(R.id.actual_best_in_text_number_in_good_habits);
            if (each_streak_lengths != null && each_streak_lengths.size() > 0) {
                int best_streak = Collections.max(each_streak_lengths);
                if (best_streak == 1) {
                    actual_best_in_text_number_in_good_habits.setText(String.valueOf(best_streak).concat(" day"));
                } else {
                    actual_best_in_text_number_in_good_habits.setText(String.valueOf(best_streak).concat(" days"));
                }
            } else {
                actual_best_in_text_number_in_good_habits.setText("0 days");
            }
        }
    }

    private void set_the_text_for_in_card() {
        if (getView() != null) {
            TextView text_saying_the_progress_in_good_habits = getView().findViewById(R.id.text_saying_the_progress_in_good_habits);
            TextView text_saying_the_real_progress_in_good_habits = getView().findViewById(R.id.text_saying_the_real_progress_in_good_habits);
            ProgressBar progress_bar_showing_good_habit_progress_in_good_habits = getView().findViewById(R.id.progress_bar_showing_good_habit_progress_in_good_habits);
            int goal = Integer.parseInt(return_the_information_from_save(3));
            int number_done = return_the_streak();
            int percent = ((number_done * 100) / goal);
            text_saying_the_progress_in_good_habits.setText(String.valueOf(number_done).concat(" / ").concat(String.valueOf(goal)).concat(" days"));
            if (percent >= 100) {
                text_saying_the_real_progress_in_good_habits.setText("100%!!");
            } else {
                text_saying_the_real_progress_in_good_habits.setText(String.valueOf(percent).concat("%"));
            }
            progress_bar_showing_good_habit_progress_in_good_habits.setProgress(percent);
        }
    }

    private void set_up_day_of_week_bar_chart() {
        if (getView() != null) {
            int max_days = 0;
            BarChart cahrt_in_good_habits_about_how_many_times_for_each_days_of_week = getView().findViewById(R.id.cahrt_in_good_habits_about_how_many_times_for_each_days_of_week);
            TextView text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse = getView().findViewById(R.id.text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse);
            String days_of_week = return_the_days_of_the_good_habit();
            if (days_of_week.equals("") || days_of_week.equals("0split0split0split0split0split0split0")) {
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setVisibility(View.INVISIBLE);
                text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse.setVisibility(View.VISIBLE);
                return;
            } else {
                cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setVisibility(View.VISIBLE);
                text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse.setVisibility(View.GONE);
            }
            String[] split_days_of_week = days_of_week.split("split");
            for (int i = 0; i < split_days_of_week.length; i++) {
                if (max_days < Integer.parseInt(split_days_of_week[i])) {
                    max_days = Integer.parseInt(split_days_of_week[i]);
                }
            }
            final int max_days_final = max_days;
            CustomBarChartRenderer barChartRender = new CustomBarChartRenderer(cahrt_in_good_habits_about_how_many_times_for_each_days_of_week, cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAnimator(), cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getViewPortHandler());
            barChartRender.setRadius(8);
            cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setRenderer(barChartRender);
            List<BarEntry> entries = new ArrayList<>();
            String[] xAxisLables;
            if (return_first_day_of_week().equals("monday")) {
                xAxisLables = new String[]{"M", "T", "W", "T", "F", "S", "S"};
                entries.add(new BarEntry(0, Integer.parseInt(split_days_of_week[0])));
                entries.add(new BarEntry(1, Integer.parseInt(split_days_of_week[1])));
                entries.add(new BarEntry(2, Integer.parseInt(split_days_of_week[2])));
                entries.add(new BarEntry(3, Integer.parseInt(split_days_of_week[3])));
                entries.add(new BarEntry(4, Integer.parseInt(split_days_of_week[4])));
                entries.add(new BarEntry(5, Integer.parseInt(split_days_of_week[5])));
                entries.add(new BarEntry(6, Integer.parseInt(split_days_of_week[6])));
            } else if (return_first_day_of_week().equals("tuesday")) {
                xAxisLables = new String[]{"T", "W", "T", "F", "S", "S", "M"};
                entries.add(new BarEntry(0, Integer.parseInt(split_days_of_week[1])));
                entries.add(new BarEntry(1, Integer.parseInt(split_days_of_week[2])));
                entries.add(new BarEntry(2, Integer.parseInt(split_days_of_week[3])));
                entries.add(new BarEntry(3, Integer.parseInt(split_days_of_week[4])));
                entries.add(new BarEntry(4, Integer.parseInt(split_days_of_week[5])));
                entries.add(new BarEntry(5, Integer.parseInt(split_days_of_week[6])));
                entries.add(new BarEntry(6, Integer.parseInt(split_days_of_week[0])));
            } else if (return_first_day_of_week().equals("wednesday")) {
                xAxisLables = new String[]{"W", "T", "F", "S", "S", "M", "T"};
                entries.add(new BarEntry(0, Integer.parseInt(split_days_of_week[2])));
                entries.add(new BarEntry(1, Integer.parseInt(split_days_of_week[3])));
                entries.add(new BarEntry(2, Integer.parseInt(split_days_of_week[4])));
                entries.add(new BarEntry(3, Integer.parseInt(split_days_of_week[5])));
                entries.add(new BarEntry(4, Integer.parseInt(split_days_of_week[6])));
                entries.add(new BarEntry(5, Integer.parseInt(split_days_of_week[0])));
                entries.add(new BarEntry(6, Integer.parseInt(split_days_of_week[1])));
            } else if (return_first_day_of_week().equals("thursday")) {
                xAxisLables = new String[]{"T", "F", "S", "S", "M", "T", "W"};
                entries.add(new BarEntry(0, Integer.parseInt(split_days_of_week[3])));
                entries.add(new BarEntry(1, Integer.parseInt(split_days_of_week[4])));
                entries.add(new BarEntry(2, Integer.parseInt(split_days_of_week[5])));
                entries.add(new BarEntry(3, Integer.parseInt(split_days_of_week[6])));
                entries.add(new BarEntry(4, Integer.parseInt(split_days_of_week[0])));
                entries.add(new BarEntry(5, Integer.parseInt(split_days_of_week[1])));
                entries.add(new BarEntry(6, Integer.parseInt(split_days_of_week[2])));
            } else if (return_first_day_of_week().equals("friday")) {
                xAxisLables = new String[]{"F", "S", "S", "M", "T", "W", "T"};
                entries.add(new BarEntry(0, Integer.parseInt(split_days_of_week[4])));
                entries.add(new BarEntry(1, Integer.parseInt(split_days_of_week[5])));
                entries.add(new BarEntry(2, Integer.parseInt(split_days_of_week[6])));
                entries.add(new BarEntry(3, Integer.parseInt(split_days_of_week[0])));
                entries.add(new BarEntry(4, Integer.parseInt(split_days_of_week[1])));
                entries.add(new BarEntry(5, Integer.parseInt(split_days_of_week[2])));
                entries.add(new BarEntry(6, Integer.parseInt(split_days_of_week[3])));
            } else if (return_first_day_of_week().equals("saturday")) {
                xAxisLables = new String[]{"S", "S", "M", "T", "W", "T", "F"};
                entries.add(new BarEntry(0, Integer.parseInt(split_days_of_week[5])));
                entries.add(new BarEntry(1, Integer.parseInt(split_days_of_week[6])));
                entries.add(new BarEntry(2, Integer.parseInt(split_days_of_week[0])));
                entries.add(new BarEntry(3, Integer.parseInt(split_days_of_week[1])));
                entries.add(new BarEntry(4, Integer.parseInt(split_days_of_week[2])));
                entries.add(new BarEntry(5, Integer.parseInt(split_days_of_week[3])));
                entries.add(new BarEntry(6, Integer.parseInt(split_days_of_week[4])));
            } else {
                xAxisLables = new String[]{"S", "M", "T", "W", "T", "F", "S"};
                entries.add(new BarEntry(0, Integer.parseInt(split_days_of_week[6])));
                entries.add(new BarEntry(1, Integer.parseInt(split_days_of_week[0])));
                entries.add(new BarEntry(2, Integer.parseInt(split_days_of_week[1])));
                entries.add(new BarEntry(3, Integer.parseInt(split_days_of_week[2])));
                entries.add(new BarEntry(4, Integer.parseInt(split_days_of_week[3])));
                entries.add(new BarEntry(5, Integer.parseInt(split_days_of_week[4])));
                entries.add(new BarEntry(6, Integer.parseInt(split_days_of_week[5])));
            }
            cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLables));
            ValueFormatter valueFormatter = new ValueFormatter() { //value format here, here is the overridden method
                @Override
                public String getFormattedValue(float value) {
                    if (value == 0 || (value / max_days_final < 0.1)) {
                        return "";
                    } else {
                        return "" + (int) value;
                    }
                }
            };
            BarDataSet set = new BarDataSet(entries, "BarDataSet");
            set.setColors(color);
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

    private String return_the_days_of_the_good_habit() {
        if (getView() != null) {
            ArrayList<Long> relapse = return_relapses();
            int monday = 0;
            int tuesday = 0;
            int wednesday = 0;
            int thursday = 0;
            int friday = 0;
            int saturday = 0;
            int sunday = 0;
            if (return_the_information_from_save(6).equals("bad")) {
                for (int i = 0; i < relapse.size(); i++) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(relapse.get(i));
                    if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                        monday = monday + 1;
                    } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                        tuesday = tuesday + 1;
                    } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                        wednesday = wednesday + 1;
                    } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                        thursday = thursday + 1;
                    } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                        friday = friday + 1;
                    } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                        saturday = saturday + 1;
                    } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        sunday = sunday + 1;
                    }
                }
            } else {
                if (return_the_information_from_save(7).equals("yes/no")) {
                    if (return_the_information_from_save(8).equals("everyday")) {
                        for (int i = 0; i < relapse.size(); i++) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTimeInMillis(relapse.get(i));
                            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                                monday = monday + 1;
                            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                                tuesday = tuesday + 1;
                            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                                wednesday = wednesday + 1;
                            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                                thursday = thursday + 1;
                            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                                friday = friday + 1;
                            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                                saturday = saturday + 1;
                            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                                sunday = sunday + 1;
                            }
                        }
                    } else if (return_the_information_from_save(8).equals("daysperweek")) {
                    /*for (int i = 0; i < relapse.size(); i++) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(relapse.get(i));
                        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                            monday = monday + 1;
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                            tuesday = tuesday + 1;
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                            wednesday = wednesday + 1;
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                            thursday = thursday + 1;
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                            friday = friday + 1;
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                            saturday = saturday + 1;
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                            sunday = sunday + 1;
                        }
                    }*/
                        long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                        long difference = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                        Calendar calendar = Calendar.getInstance();
                        if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                            difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                        }
                        for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                            calendar.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                            int index = Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()));
                            if (index >= 0) {
                                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                                    monday = monday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                                    tuesday = tuesday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                                    wednesday = wednesday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                                    thursday = thursday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                                    friday = friday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                                    saturday = saturday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                                    sunday = sunday + 1;
                                }
                            } else {
                                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY && !return_the_information_from_save(10).contains("Mo")) {
                                    monday++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY && !return_the_information_from_save(10).contains("Tu")) {
                                    tuesday++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY && !return_the_information_from_save(10).contains("We")) {
                                    wednesday++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY && !return_the_information_from_save(10).contains("Th")) {
                                    thursday++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && !return_the_information_from_save(10).contains("Fr")) {
                                    friday++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY && !return_the_information_from_save(10).contains("Sa")) {
                                    saturday++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && !return_the_information_from_save(10).contains("Su")) {
                                    sunday++;
                                }
                            }
                        }
                    } else if (return_the_information_from_save(8).equals("everyndays")) {
                        long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                        long difference = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                        Calendar calendar = Calendar.getInstance();
                        if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                            difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                        }
                        for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                            calendar.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                            int index = Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()));
                            if (index >= 0) {
                                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                                    monday = monday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                                    tuesday = tuesday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                                    wednesday = wednesday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                                    thursday = thursday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                                    friday = friday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                                    saturday = saturday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                                    sunday = sunday + 1;
                                }
                            } else {
                                if (history_of_relapse.size() > 0) {
                                    index = (-index) - 2;
                                    if (index >= 0) {
                                        long last_relapse = history_of_relapse.get(index);
                                        if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) - Simplify_the_time.return_time_in_midnight(last_relapse) < Long.parseLong(return_the_information_from_save(10)) * TimeUnit.DAYS.toMillis(1)) {
                                            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                                                monday = monday + 1;
                                            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                                                tuesday = tuesday + 1;
                                            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                                                wednesday = wednesday + 1;
                                            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                                                thursday = thursday + 1;
                                            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                                                friday = friday + 1;
                                            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                                                saturday = saturday + 1;
                                            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                                                sunday = sunday + 1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else if (return_the_information_from_save(8).equals("dayspermonth")) {
                        Calendar calender = Calendar.getInstance();
                        long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                        long difference = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                        int sum = 0;
                        if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                            difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                        }
                        for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                            calender.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                            int day_of_the_month = calender.get(Calendar.DAY_OF_MONTH);
                            if (!days_of_months_good_habit.contains(day_of_the_month)) {
                                if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                                    monday = monday + 1;
                                } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                                    tuesday = tuesday + 1;
                                } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                                    wednesday = wednesday + 1;
                                } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                                    thursday = thursday + 1;
                                } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                                    friday = friday + 1;
                                } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                                    saturday = saturday + 1;
                                } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                                    sunday = sunday + 1;
                                }
                            } else {
                                int index = Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(calender.getTimeInMillis()));
                                if (index >= 0) {
                                    if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                                        monday = monday + 1;
                                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                                        tuesday = tuesday + 1;
                                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                                        wednesday = wednesday + 1;
                                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                                        thursday = thursday + 1;
                                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                                        friday = friday + 1;
                                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                                        saturday = saturday + 1;
                                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                                        sunday = sunday + 1;
                                    }
                                }
                            }
                        }
                    }
                } else if (return_the_information_from_save(7).equals("amount")) {
                    if (return_the_information_from_save(8).equals("everyday")) {
                        for (HashMap.Entry<Long, Integer> entry : hash_map_amount.entrySet()) {
                            int value = entry.getValue();
                            if (value >= Integer.parseInt(return_the_information_from_save(9))) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTimeInMillis(entry.getKey());
                                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                                    monday = monday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                                    tuesday = tuesday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                                    wednesday = wednesday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                                    thursday = thursday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                                    friday = friday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                                    saturday = saturday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                                    sunday = sunday + 1;
                                }
                            }
                        }
                    } else if (return_the_information_from_save(8).equals("daysperweek")) {
                        long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                        long difference = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                        Calendar calendar = Calendar.getInstance();
                        if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                            difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                        }
                        for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                            calendar.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                            if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis())) && is_amount_good_for_streak(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()))) {
                                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                                    monday = monday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                                    tuesday = tuesday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                                    wednesday = wednesday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                                    thursday = thursday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                                    friday = friday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                                    saturday = saturday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                                    sunday = sunday + 1;
                                }
                            } else {
                                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY && !return_the_information_from_save(10).contains("Mo")) {
                                    monday++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY && !return_the_information_from_save(10).contains("Tu")) {
                                    tuesday++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY && !return_the_information_from_save(10).contains("We")) {
                                    wednesday++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY && !return_the_information_from_save(10).contains("Th")) {
                                    thursday++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && !return_the_information_from_save(10).contains("Fr")) {
                                    friday++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY && !return_the_information_from_save(10).contains("Sa")) {
                                    saturday++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && !return_the_information_from_save(10).contains("Su")) {
                                    sunday++;
                                }
                            }
                        }
                    } else if (return_the_information_from_save(8).equals("everyndays")) {
                        long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                        long difference = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                        Calendar calendar = Calendar.getInstance();
                        if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                            difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                        }
                        for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                            calendar.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                            if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis())) && is_amount_good_for_streak(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()))) {
                                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                                    monday = monday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                                    tuesday = tuesday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                                    wednesday = wednesday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                                    thursday = thursday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                                    friday = friday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                                    saturday = saturday + 1;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                                    sunday = sunday + 1;
                                }
                            } else {
                                if (hash_map_amount.size() > 0) {
                                    long milli = Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis());
                                    for (int j = 1; j < Integer.parseInt(return_the_information_from_save(10)); j++) {
                                        if (hash_map_amount.containsKey(milli - TimeUnit.DAYS.toMillis(j)) && hash_map_amount.get(milli - TimeUnit.DAYS.toMillis(j)) >= Integer.parseInt(return_the_information_from_save(9))) {
                                            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                                                monday = monday + 1;
                                            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                                                tuesday = tuesday + 1;
                                            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                                                wednesday = wednesday + 1;
                                            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                                                thursday = thursday + 1;
                                            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                                                friday = friday + 1;
                                            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                                                saturday = saturday + 1;
                                            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                                                sunday = sunday + 1;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    } else if (return_the_information_from_save(8).equals("dayspermonth")) {
                        Calendar calender = Calendar.getInstance();
                        long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                        long difference = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                        int sum = 0;
                        if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                            difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                        }
                        for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                            calender.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                            int day_of_the_month = calender.get(Calendar.DAY_OF_MONTH);
                            if (!days_of_months_good_habit.contains(day_of_the_month)) {
                                if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                                    monday = monday + 1;
                                } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                                    tuesday = tuesday + 1;
                                } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                                    wednesday = wednesday + 1;
                                } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                                    thursday = thursday + 1;
                                } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                                    friday = friday + 1;
                                } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                                    saturday = saturday + 1;
                                } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                                    sunday = sunday + 1;
                                }
                            } else {
                                if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(calender.getTimeInMillis())) && is_amount_good_for_streak(Simplify_the_time.return_time_in_midnight(calender.getTimeInMillis()))) {
                                    if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                                        monday = monday + 1;
                                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                                        tuesday = tuesday + 1;
                                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                                        wednesday = wednesday + 1;
                                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                                        thursday = thursday + 1;
                                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                                        friday = friday + 1;
                                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                                        saturday = saturday + 1;
                                    } else if (calender.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                                        sunday = sunday + 1;
                                    }
                                }
                            }
                        }
                    }
                } else if (return_the_information_from_save(7).equals("timer")) {

                }
            }
            return String.valueOf(monday).concat("split").concat(String.valueOf(tuesday)).concat("split").concat(String.valueOf(wednesday)).concat("split").concat(String.valueOf(thursday)).concat("split").concat(String.valueOf(friday)).concat("split").concat(String.valueOf(saturday)).concat("split").concat(String.valueOf(sunday));
        }
        return "";
    }


    private void draw_pie_chart() {
        if (getView() != null) {
            PieChart mChart = getView().findViewById(R.id.chart_to_show_pie_of_yes_or_no_pie);
            Button this_pie_chart_is_only_available_for_pro_users_pie_chart_view_home = getView().findViewById(R.id.this_pie_chart_is_only_available_for_pro_users_pie_chart_view_home);
            TextView text_view_saying_that_there_is_not_enough_data_to_draw_this_chart = getView().findViewById(R.id.text_view_saying_that_there_is_not_enough_data_to_draw_this_chart);
            Am_i_paid am_i_paid = new Am_i_paid(getContext());
            if (am_i_paid.did_user_pay()) {
                this_pie_chart_is_only_available_for_pro_users_pie_chart_view_home.setVisibility(View.INVISIBLE);
                String[] split_yes_no = return_info_about_pie().split("split");
                if (Integer.parseInt(split_yes_no[0]) > 0 || Integer.parseInt(split_yes_no[1]) > 0) {
                    mChart.setVisibility(View.VISIBLE);
                    text_view_saying_that_there_is_not_enough_data_to_draw_this_chart.setVisibility(View.INVISIBLE);
                    List<PieEntry> pieChartEntries = new ArrayList<>();
                    List<LegendEntry> entries = new ArrayList<>();
                    LegendEntry entry = new LegendEntry();
                    LegendEntry entry2 = new LegendEntry();
                    if (Integer.parseInt(split_yes_no[0]) > 0) {
                        if (return_the_information_from_save(6).equals("bad")) {
                            pieChartEntries.add(new PieEntry(Float.parseFloat(split_yes_no[0]), String.valueOf(split_yes_no[0]).concat(" Yes")));
                            entry.formColor = ColorUtils.blendARGB(color, Color.RED, 0.7F);
                            entry.label = "Relapsed";
                            entries.add(entry);
                        } else if (return_the_information_from_save(6).equals("good")) {
                            pieChartEntries.add(new PieEntry(Float.parseFloat(split_yes_no[0]), String.valueOf(split_yes_no[0]).concat(" Yes")));
                            entry.formColor = color;
                            entry.label = "Completed";
                            entries.add(entry);
                        }
                    }
                    if (Integer.parseInt(split_yes_no[1]) > 0) {
                        if (return_the_information_from_save(6).equals("bad")) {
                            pieChartEntries.add(new PieEntry(Float.parseFloat(split_yes_no[1]), String.valueOf(split_yes_no[1]).concat(" No")));
                            entry2.formColor = color;
                            entry2.label = "Didn't Relapse";
                            entries.add(entry2);
                        } else if (return_the_information_from_save(6).equals("good")) {
                            pieChartEntries.add(new PieEntry(Float.parseFloat(split_yes_no[1]), String.valueOf(split_yes_no[1]).concat(" No")));
                            entry2.formColor = ColorUtils.blendARGB(color, Color.RED, 0.7F);
                            entry2.label = "not completed";
                            entries.add(entry2);
                        }
                    }
                    mChart.setEntryLabelColor(Color.WHITE);
                    mChart.setEntryLabelTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    mChart.setCenterTextTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    Legend l = mChart.getLegend();
                    l.setCustom(entries);
                    PieDataSet set = new PieDataSet(pieChartEntries, "");
                    PieData data = new PieData(set);
                    int total = Integer.parseInt(split_yes_no[0]) + Integer.parseInt(split_yes_no[1]);
                    mChart.setCenterText(String.valueOf(total).concat(" days"));
                    mChart.setCenterTextSize(16.5f);
                    mChart.setCenterTextColor(color);
                    mChart.setData(data);
                    if (Integer.parseInt(split_yes_no[0]) > 0 && Integer.parseInt(split_yes_no[1]) > 0) {
                        set.setColors(color, ColorUtils.blendARGB(color, Color.RED, 0.7F));
                    } else {
                        if (Integer.parseInt(split_yes_no[0]) > 0) {
                            set.setColors(color);
                        }
                        if (Integer.parseInt(split_yes_no[1]) > 0) {
                            set.setColors(ColorUtils.blendARGB(color, Color.RED, 0.7F));
                        }
                    }
                    set.setDrawValues(false);
                    mChart.getDescription().setEnabled(false);
                    mChart.invalidate();
                } else {
                    mChart.setVisibility(View.INVISIBLE);
                    text_view_saying_that_there_is_not_enough_data_to_draw_this_chart.setVisibility(View.VISIBLE);
                }
            } else {
                this_pie_chart_is_only_available_for_pro_users_pie_chart_view_home.setVisibility(View.VISIBLE);
                this_pie_chart_is_only_available_for_pro_users_pie_chart_view_home.setBackgroundTintList(ColorStateList.valueOf(color));
                mChart.setVisibility(View.VISIBLE);
                text_view_saying_that_there_is_not_enough_data_to_draw_this_chart.setVisibility(View.INVISIBLE);
                List<PieEntry> pieChartEntries = new ArrayList<>();
                List<LegendEntry> entries = new ArrayList<>();
                LegendEntry entry = new LegendEntry();
                LegendEntry entry2 = new LegendEntry();
                Random rand = new Random();
                int relapsed = rand.nextInt(100) + 1;
                int not_relapsed = rand.nextInt(100) + 1;
                pieChartEntries.add(new PieEntry((float) relapsed, String.valueOf(relapsed).concat(" Didn't relapse")));
                entry.formColor = color;
                entry.label = "Relapsed";
                entries.add(entry);
                pieChartEntries.add(new PieEntry((float) not_relapsed, String.valueOf(not_relapsed).concat(" Relapsed")));
                entry2.formColor = ColorUtils.blendARGB(color, Color.RED, 0.7F);
                entry2.label = "Didn't Relapse";
                entries.add(entry2);
                mChart.setEntryLabelColor(Color.WHITE);
                mChart.setEntryLabelTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                mChart.setCenterTextTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                Legend l = mChart.getLegend();
                l.setCustom(entries);
                PieDataSet set = new PieDataSet(pieChartEntries, "");
                PieData data = new PieData(set);
                int total = relapsed + not_relapsed;
                mChart.setCenterText(String.valueOf(total).concat(" days"));
                mChart.setCenterTextSize(16.5f);
                mChart.setCenterTextColor(color);
                mChart.setData(data);
                set.setColors(color, ColorUtils.blendARGB(color, Color.RED, 0.7F));
                set.setDrawValues(false);
                mChart.getDescription().setEnabled(false);
                mChart.invalidate();
            }
        }
    }

    private String return_info_about_pie() {
        if (getView() != null && getActivity() != null) {
            int yes = 0;
            int no = 0;
            if (return_the_information_from_save(6).equals("bad")) {
                yes = return_relapses().size();
                int total_days = (int) TimeUnit.MILLISECONDS.toDays(Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)))) + 1;
                no = total_days - yes;
                return String.valueOf(no).concat("split").concat(String.valueOf(yes));
            } else {
                if (return_the_information_from_save(7).equals("yes/no")) {
                    if (return_the_information_from_save(8).equals("everyday")) {
                        yes = return_relapses().size();
                        int total_days = (int) TimeUnit.MILLISECONDS.toDays(Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)))) + 1;
                        no = total_days - yes;
                        return String.valueOf(yes).concat("split").concat(String.valueOf(no));
                    } else if (return_the_information_from_save(8).equals("daysperweek")) {
                        long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                        long difference = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                        Calendar calendar = Calendar.getInstance();
                        if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                            difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                        }
                        for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                            calendar.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                            int index = Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()));
                            if (index >= 0) {
                                yes++;
                            } else {
                                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY && !return_the_information_from_save(10).contains("Mo")) {
                                    yes++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY && !return_the_information_from_save(10).contains("Tu")) {
                                    yes++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY && !return_the_information_from_save(10).contains("We")) {
                                    yes++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY && !return_the_information_from_save(10).contains("Th")) {
                                    yes++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && !return_the_information_from_save(10).contains("Fr")) {
                                    yes++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY && !return_the_information_from_save(10).contains("Sa")) {
                                    yes++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && !return_the_information_from_save(10).contains("Su")) {
                                    yes++;
                                } else {
                                    no++;
                                }
                            }
                        }
                        return String.valueOf(yes).concat("split").concat(String.valueOf(no));
                    } else if (return_the_information_from_save(8).equals("everyndays")) {
                        ArrayList<Long> filter_list = new ArrayList<>();
                        ArrayList<Long> filter_list_at_midnight = new ArrayList<>();
                        long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                        long difference = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                        Calendar calendar = Calendar.getInstance();
                        int sum = 0;
                        filter_list = return_relapses();
                        for (int i = 0; i < filter_list.size(); i++) {
                            filter_list_at_midnight.add(Simplify_the_time.return_time_in_midnight(filter_list.get(i)));
                        }
                        if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                            difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                        }
                        for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                            calendar.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                            int index = Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()));
                            if (index >= 0) {
                                yes++;
                            } else {
                                if (history_of_relapse.size() > 0) {
                                    index = (-index) - 2;
                                    if (index < 0) {
                                        no++;
                                    } else {
                                        long last_relapse = history_of_relapse.get(index);
                                        if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) - Simplify_the_time.return_time_in_midnight(last_relapse) >= Long.parseLong(return_the_information_from_save(10)) * TimeUnit.DAYS.toMillis(1)) {
                                            no++;
                                        } else {
                                            yes++;
                                        }
                                    }
                                } else {
                                    no++;
                                }
                            }
                        }
                        return String.valueOf(yes).concat("split").concat(String.valueOf(no));
                    } else if (return_the_information_from_save(8).equals("dayspermonth")) {
                        Calendar calender = Calendar.getInstance();
                        long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                        long difference = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                        int sum = 0;
                        if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                            difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                        }
                        for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                            calender.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                            int day_of_the_month = calender.get(Calendar.DAY_OF_MONTH);
                            if (!days_of_months_good_habit.contains(day_of_the_month)) {
                                yes++;
                            } else {
                                int index = Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(calender.getTimeInMillis()));
                                if (index >= 0) {
                                    yes++;
                                } else {
                                    no++;
                                }
                            }
                        }
                        return String.valueOf(yes).concat("split").concat(String.valueOf(no));
                    } else {
                        return "";
                    }
                } else if (return_the_information_from_save(7).equals("amount")) {
                    if (return_the_information_from_save(8).equals("everyday")) {
                        yes = hash_map_amount.size();
                        int total_days = (int) TimeUnit.MILLISECONDS.toDays(Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)))) + 1;
                        no = total_days - yes;
                        return String.valueOf(yes).concat("split").concat(String.valueOf(no));
                    } else if (return_the_information_from_save(8).equals("daysperweek")) {
                        long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                        long difference = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                        Calendar calendar = Calendar.getInstance();
                        if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                            difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                        }
                        for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                            calendar.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                            if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis())) && is_amount_good_for_streak(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()))) {
                                yes++;
                            } else {
                                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY && !return_the_information_from_save(10).contains("Mo")) {
                                    yes++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY && !return_the_information_from_save(10).contains("Tu")) {
                                    yes++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY && !return_the_information_from_save(10).contains("We")) {
                                    yes++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY && !return_the_information_from_save(10).contains("Th")) {
                                    yes++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && !return_the_information_from_save(10).contains("Fr")) {
                                    yes++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY && !return_the_information_from_save(10).contains("Sa")) {
                                    yes++;
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && !return_the_information_from_save(10).contains("Su")) {
                                    yes++;
                                } else {
                                    no++;
                                }
                            }
                        }
                        return String.valueOf(yes).concat("split").concat(String.valueOf(no));
                    } else if (return_the_information_from_save(8).equals("everyndays")) {
                        ArrayList<Long> filter_list = new ArrayList<>();
                        ArrayList<Long> filter_list_at_midnight = new ArrayList<>();
                        long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                        long difference = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                        Calendar calendar = Calendar.getInstance();
                        int sum = 0;
                        filter_list = return_relapses();
                        for (int i = 0; i < filter_list.size(); i++) {
                            filter_list_at_midnight.add(Simplify_the_time.return_time_in_midnight(filter_list.get(i)));
                        }
                        if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                            difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                        }
                        for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                            calendar.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                            if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis())) && is_amount_good_for_streak(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()))) {
                                yes++;
                            } else {
                                if (hash_map_amount.size() > 0) {
                                    long milli = Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis());
                                    for (int j = 1; j < Integer.parseInt(return_the_information_from_save(10)); j++) {
                                        if (hash_map_amount.containsKey(milli - TimeUnit.DAYS.toMillis(j)) && hash_map_amount.get(milli - TimeUnit.DAYS.toMillis(j)) >= Integer.parseInt(return_the_information_from_save(9))) {
                                            yes++;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        return String.valueOf(yes).concat("split").concat(String.valueOf(no));
                    } else if (return_the_information_from_save(8).equals("dayspermonth")) {
                        Calendar calender = Calendar.getInstance();
                        long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                        long difference = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                        if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                            difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                        }
                        for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                            calender.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                            int day_of_the_month = calender.get(Calendar.DAY_OF_MONTH);
                            if (!days_of_months_good_habit.contains(day_of_the_month)) {
                                yes++;
                            } else {
                                if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(calender.getTimeInMillis())) && is_amount_good_for_streak(Simplify_the_time.return_time_in_midnight(calender.getTimeInMillis()))) {
                                    yes++;
                                } else {
                                    no++;
                                }
                            }
                        }
                        return String.valueOf(yes).concat("split").concat(String.valueOf(no));
                    } else {
                        return "";
                    }
                } else if (return_the_information_from_save(7).equals("timer")) {
                    return "";
                } else {
                    return "";
                }
            }
        } else {
            return "";
        }
    }

    private void set_up_the_various_streak_chart() {
        if (getView() != null) {
            Button this_streak_chart_is_only_available_for_pro_users_pie_chart_view_home = getView().findViewById(R.id.this_streak_chart_is_only_available_for_pro_users_pie_chart_view_home);
            TextView text_saying_thet_there_is_not_enough_data_to_draw_various_streaks = getView().findViewById(R.id.text_saying_thet_there_is_not_enough_data_to_draw_various_streaks);
            line_chart_for_streak = getView().findViewById(R.id.cahrt_showing_various_chart_length_for_different_streaks);
            Am_i_paid am_i_paid = new Am_i_paid(getContext());
            if (am_i_paid.did_user_pay()) {
                this_streak_chart_is_only_available_for_pro_users_pie_chart_view_home.setVisibility(View.INVISIBLE);
                //process the data
                ArrayList<Entry> y_values = new ArrayList<>();
                String[] split_data = return_the_line_for_the_data_chart().split("split");
                if (split_data.length <= 1) {
                    text_saying_thet_there_is_not_enough_data_to_draw_various_streaks.setVisibility(View.VISIBLE);
                    line_chart_for_streak.setVisibility(View.INVISIBLE);
                    return;
                } else {
                    text_saying_thet_there_is_not_enough_data_to_draw_various_streaks.setVisibility(View.GONE);
                    line_chart_for_streak.setVisibility(View.VISIBLE);
                }
                for (int i = 0; i < split_data.length; i++) {
                    if (!split_data[i].equals("")) {
                        y_values.add(new Entry(i + 1, Integer.parseInt(split_data[i])));
                    }
                }

                line_chart_for_streak.setPadding(0, 0, 10, 0);
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
                // set description
                line_chart_for_streak.getDescription().setText("Each streak length in days");

                // disable stuff
                line_chart_for_streak.setScaleEnabled(false);
                line_chart_for_streak.getLegend().setEnabled(false);

                //control left and right axis
                line_chart_for_streak.getAxisRight().setEnabled(false);
                line_chart_for_streak.getAxisLeft().setAxisMinimum(0);
                line_chart_for_streak.getAxisLeft().setDrawAxisLine(false);
                line_chart_for_streak.getAxisLeft().setGranularity(1.0f);
                line_chart_for_streak.getAxisLeft().setGranularityEnabled(true); // Required to enable granularity

                LineDataSet lineDataSet = new LineDataSet(y_values, "data");
                lineDataSet.setColor(color);
                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(lineDataSet);
                LineData data = new LineData(dataSets);
                line_chart_for_streak.setData(data);
                line_chart_for_streak.getData().setHighlightEnabled(false);
                lineDataSet.setLineWidth(3f);
                lineDataSet.setCircleRadius(6f);
                lineDataSet.setCircleHoleRadius(3.5f);
                lineDataSet.setCircleHoleColor(color);
                lineDataSet.setCircleColor(Color.WHITE);
                lineDataSet.setDrawValues(false);
                line_chart_for_streak.invalidate();
                line_chart_for_streak.setVisibleXRangeMaximum(9);
                line_chart_for_streak.moveViewToX(y_values.size());
            } else {
                this_streak_chart_is_only_available_for_pro_users_pie_chart_view_home.setVisibility(View.VISIBLE);
                this_streak_chart_is_only_available_for_pro_users_pie_chart_view_home.setBackgroundTintList(ColorStateList.valueOf(color));
                ArrayList<Entry> y_values = new ArrayList<>();
                text_saying_thet_there_is_not_enough_data_to_draw_various_streaks.setVisibility(View.GONE);
                line_chart_for_streak.setVisibility(View.VISIBLE);
                Random random = new Random();
                int number_of_streaks = random.nextInt(50);
                for (int i = 0; i < number_of_streaks; i++) {
                    y_values.add(new Entry(i + 1, random.nextInt(15)));
                }
                line_chart_for_streak.setPadding(0, 0, 10, 0);
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
                // set description
                line_chart_for_streak.getDescription().setText("Each streak length in days");

                // disable stuff
                line_chart_for_streak.setScaleEnabled(false);
                line_chart_for_streak.getLegend().setEnabled(false);

                //control left and right axis
                line_chart_for_streak.getAxisRight().setEnabled(false);
                line_chart_for_streak.getAxisLeft().setAxisMinimum(0);
                line_chart_for_streak.getAxisLeft().setDrawAxisLine(false);
                line_chart_for_streak.getAxisLeft().setGranularity(1.0f);
                line_chart_for_streak.getAxisLeft().setGranularityEnabled(true); // Required to enable granularity

                LineDataSet lineDataSet = new LineDataSet(y_values, "data");
                lineDataSet.setColor(color);
                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(lineDataSet);
                LineData data = new LineData(dataSets);
                line_chart_for_streak.setData(data);
                line_chart_for_streak.getData().setHighlightEnabled(false);
                lineDataSet.setLineWidth(3f);
                lineDataSet.setCircleRadius(6f);
                lineDataSet.setCircleHoleRadius(3.5f);
                lineDataSet.setCircleHoleColor(color);
                lineDataSet.setCircleColor(Color.WHITE);
                lineDataSet.setDrawValues(false);
                line_chart_for_streak.invalidate();
                line_chart_for_streak.setVisibleXRangeMaximum(9);
                line_chart_for_streak.moveViewToX(y_values.size());
            }
        }
    }

    private String return_the_line_for_the_data_chart() {
        String return_me = "";
        for (int i = 0; i < each_streak_lengths.size(); i++) {
            return_me = return_me.concat(String.valueOf(each_streak_lengths.get(i))).concat("split");
        }
        return return_me;
    }

    private void setup_the_four_information_card() {
        if (getActivity() != null && getView() != null) {
            TextView text_saying_the_how_many_times_for_this_week_actual = getView().findViewById(R.id.text_saying_the_how_many_times_for_this_week_actual);
            TextView text_saying_the_how_many_times_for_this_month_actual = getView().findViewById(R.id.text_saying_the_how_many_times_for_this_month_actual);
            TextView text_saying_the_how_many_times_for_this_year_actual = getView().findViewById(R.id.text_saying_the_how_many_times_for_this_year_actual);
            TextView text_saying_the_how_many_times_for_this_all_time_actual = getView().findViewById(R.id.text_saying_the_how_many_times_for_this_all_time_actual);
            Button this_relapses_is_only_available_for_pro_users_pie_chart_view_home = getView().findViewById(R.id.this_relapses_is_only_available_for_pro_users_pie_chart_view_home);
            Am_i_paid am_i_paid = new Am_i_paid(getContext());
            if (am_i_paid.did_user_pay()) {
                this_relapses_is_only_available_for_pro_users_pie_chart_view_home.setVisibility(View.INVISIBLE);
                if (return_the_information_from_save(6).equals("bad")) {
                    ArrayList<Long> relapse = return_relapses();
                    long week_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (6 * 86400000L));
                    long month_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (29 * 86400000L));
                    long year_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (364 * 86400000L));
                    int week_data = 0;
                    int month_data = 0;
                    int year_data = 0;
                    int all_data = 0;
                    if (relapse.size() > 0) {
                        for (int i = relapse.size() - 1; i >= 0; i--) {
                            long time = Simplify_the_time.return_time_in_midnight(relapse.get(i));
                            if (time < week_ago) {
                                break;
                            }
                            week_data++;
                        }
                        for (int i = relapse.size() - 1; i >= 0; i--) {
                            long time = Simplify_the_time.return_time_in_midnight(relapse.get(i));
                            if (time < month_ago) {
                                break;
                            }
                            month_data++;
                        }
                        for (int i = relapse.size() - 1; i >= 0; i--) {
                            long time = Simplify_the_time.return_time_in_midnight(relapse.get(i));
                            if (time < year_ago) {
                                break;
                            }
                            year_data++;
                        }
                        all_data = relapse.size();
                        text_saying_the_how_many_times_for_this_week_actual.setText(String.valueOf(week_data));
                        text_saying_the_how_many_times_for_this_month_actual.setText(String.valueOf(month_data));
                        text_saying_the_how_many_times_for_this_year_actual.setText(String.valueOf(year_data));
                        text_saying_the_how_many_times_for_this_all_time_actual.setText(String.valueOf(all_data));
                    } else {
                        text_saying_the_how_many_times_for_this_week_actual.setText("No data");
                        text_saying_the_how_many_times_for_this_month_actual.setText("No data");
                        text_saying_the_how_many_times_for_this_year_actual.setText("No data");
                        text_saying_the_how_many_times_for_this_all_time_actual.setText("No data");
                    }
                } else {
                    if (return_the_information_from_save(7).equals("yes/no")) {
                        if (return_the_information_from_save(8).equals("everyday")) {
                            ArrayList<Long> relapse = return_relapses();
                            long week_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (6 * 86400000L));
                            long month_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (29 * 86400000L));
                            long year_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (364 * 86400000L));
                            int week_data = 0;
                            int month_data = 0;
                            int year_data = 0;
                            int all_data = 0;
                            if (relapse.size() > 0) {
                                for (int i = relapse.size() - 1; i >= 0; i--) {
                                    long time = Simplify_the_time.return_time_in_midnight(relapse.get(i));
                                    if (time < week_ago) {
                                        break;
                                    }
                                    week_data++;
                                }
                                for (int i = relapse.size() - 1; i >= 0; i--) {
                                    long time = Simplify_the_time.return_time_in_midnight(relapse.get(i));
                                    if (time < month_ago) {
                                        break;
                                    }
                                    month_data++;
                                }
                                for (int i = relapse.size() - 1; i >= 0; i--) {
                                    long time = Simplify_the_time.return_time_in_midnight(relapse.get(i));
                                    if (time < year_ago) {
                                        break;
                                    }
                                    year_data++;
                                }
                                all_data = relapse.size();
                                text_saying_the_how_many_times_for_this_week_actual.setText(String.valueOf(week_data));
                                text_saying_the_how_many_times_for_this_month_actual.setText(String.valueOf(month_data));
                                text_saying_the_how_many_times_for_this_year_actual.setText(String.valueOf(year_data));
                                text_saying_the_how_many_times_for_this_all_time_actual.setText(String.valueOf(all_data));
                            } else {
                                text_saying_the_how_many_times_for_this_week_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_month_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_year_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_all_time_actual.setText("No data");
                            }
                        } else if (return_the_information_from_save(8).equals("daysperweek")) {
                            long week_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (6 * 86400000L));
                            long month_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (29 * 86400000L));
                            long year_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (364 * 86400000L));
                            int week_data = 0;
                            int month_data = 0;
                            int year_data = 0;
                            int all_data = 0;
                            ArrayList<Long> filter_list = new ArrayList<>();
                            ArrayList<Long> filter_list_at_midnight = new ArrayList<>();
                            ArrayList<Long> relapse = return_relapses();
                            filter_list = return_relapses();
                            long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                            long difference = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                            Calendar calendar = Calendar.getInstance();
                            int sum = 0;
                            for (int i = 0; i < filter_list.size(); i++) {
                                filter_list_at_midnight.add(Simplify_the_time.return_time_in_midnight(filter_list.get(i)));
                            }
                            if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                                difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                            }
                            for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                                calendar.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                                    if (return_the_information_from_save(10).contains("Mo")) {
                                        if (filter_list_at_midnight.contains(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                            if (calendar.getTimeInMillis() >= week_ago) {
                                                week_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= month_ago) {
                                                month_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= year_ago) {
                                                year_data++;
                                            } else {
                                                all_data++;
                                            }
                                        }
                                    } else {
                                        if (calendar.getTimeInMillis() >= week_ago) {
                                            week_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= month_ago) {
                                            month_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= year_ago) {
                                            year_data++;
                                        } else {
                                            all_data++;
                                        }
                                    }
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                                    if (return_the_information_from_save(10).contains("Tu")) {
                                        if (filter_list_at_midnight.contains(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                            if (calendar.getTimeInMillis() >= week_ago) {
                                                week_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= month_ago) {
                                                month_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= year_ago) {
                                                year_data++;
                                            } else {
                                                all_data++;
                                            }
                                        }
                                    } else {
                                        if (calendar.getTimeInMillis() >= week_ago) {
                                            week_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= month_ago) {
                                            month_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= year_ago) {
                                            year_data++;
                                        } else {
                                            all_data++;
                                        }
                                    }
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                                    if (return_the_information_from_save(10).contains("We")) {
                                        if (filter_list_at_midnight.contains(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                            if (calendar.getTimeInMillis() >= week_ago) {
                                                week_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= month_ago) {
                                                month_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= year_ago) {
                                                year_data++;
                                            } else {
                                                all_data++;
                                            }
                                        }
                                    } else {
                                        if (calendar.getTimeInMillis() >= week_ago) {
                                            week_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= month_ago) {
                                            month_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= year_ago) {
                                            year_data++;
                                        } else {
                                            all_data++;
                                        }
                                    }
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                                    if (return_the_information_from_save(10).contains("Th")) {
                                        if (filter_list_at_midnight.contains(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                            if (calendar.getTimeInMillis() >= week_ago) {
                                                week_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= month_ago) {
                                                month_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= year_ago) {
                                                year_data++;
                                            } else {
                                                all_data++;
                                            }
                                        }
                                    } else {
                                        if (calendar.getTimeInMillis() >= week_ago) {
                                            week_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= month_ago) {
                                            month_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= year_ago) {
                                            year_data++;
                                        } else {
                                            all_data++;
                                        }
                                    }
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                                    if (return_the_information_from_save(10).contains("Fr")) {
                                        if (filter_list_at_midnight.contains(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                            if (calendar.getTimeInMillis() >= week_ago) {
                                                week_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= month_ago) {
                                                month_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= year_ago) {
                                                year_data++;
                                            } else {
                                                all_data++;
                                            }
                                        }
                                    } else {
                                        if (calendar.getTimeInMillis() >= week_ago) {
                                            week_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= month_ago) {
                                            month_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= year_ago) {
                                            year_data++;
                                        } else {
                                            all_data++;
                                        }
                                    }
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                                    if (return_the_information_from_save(10).contains("Sa")) {
                                        if (filter_list_at_midnight.contains(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                            if (calendar.getTimeInMillis() >= week_ago) {
                                                week_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= month_ago) {
                                                month_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= year_ago) {
                                                year_data++;
                                            } else {
                                                all_data++;
                                            }
                                        }
                                    } else {
                                        if (calendar.getTimeInMillis() >= week_ago) {
                                            week_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= month_ago) {
                                            month_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= year_ago) {
                                            year_data++;
                                        } else {
                                            all_data++;
                                        }
                                    }
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                                    if (return_the_information_from_save(10).contains("Su")) {
                                        if (filter_list_at_midnight.contains(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                            if (calendar.getTimeInMillis() >= week_ago) {
                                                week_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= month_ago) {
                                                month_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= year_ago) {
                                                year_data++;
                                            } else {
                                                all_data++;
                                            }
                                        }
                                    } else {
                                        if (calendar.getTimeInMillis() >= week_ago) {
                                            week_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= month_ago) {
                                            month_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= year_ago) {
                                            year_data++;
                                        } else {
                                            all_data++;
                                        }
                                    }
                                }
                            }
                            all_data = all_data + year_data;
                            if (all_data == 0) {
                                text_saying_the_how_many_times_for_this_week_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_month_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_year_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_all_time_actual.setText("No data");
                            } else {
                                text_saying_the_how_many_times_for_this_week_actual.setText(String.valueOf(week_data));
                                text_saying_the_how_many_times_for_this_month_actual.setText(String.valueOf(month_data));
                                text_saying_the_how_many_times_for_this_year_actual.setText(String.valueOf(year_data));
                                text_saying_the_how_many_times_for_this_all_time_actual.setText(String.valueOf(all_data));
                            }
                        } else if (return_the_information_from_save(8).equals("everyndays")) {
                            long week_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (6 * 86400000L));
                            long month_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (29 * 86400000L));
                            long year_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (364 * 86400000L));
                            int week_data = 0;
                            int month_data = 0;
                            int year_data = 0;
                            int all_data = 0;
                            ArrayList<Long> filter_list = new ArrayList<>();
                            ArrayList<Long> filter_list_at_midnight = new ArrayList<>();
                            ArrayList<Long> relapse = return_relapses();
                            if (return_the_information_from_save(7).equals("amount") || return_the_information_from_save(7).equals("timer")) {
                                for (int i = 0; i < relapse.size(); i++) {
                                    if (is_amount_good_for_streak(relapse.get(i))) {
                                        filter_list.add(relapse.get(i));
                                    }
                                }
                            } else {
                                filter_list = return_relapses();
                            }
                            long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                            long difference = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                            Calendar calendar = Calendar.getInstance();
                            for (int i = 0; i < filter_list.size(); i++) {
                                filter_list_at_midnight.add(Simplify_the_time.return_time_in_midnight(filter_list.get(i)));
                            }
                            if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                                difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                            }
                            for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                                calendar.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                                int index = Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()));
                                if (index >= 0) {
                                    if (calendar.getTimeInMillis() > week_ago) {
                                        week_data++;
                                    }
                                    if (calendar.getTimeInMillis() > month_ago) {
                                        month_data++;
                                    }
                                    if (calendar.getTimeInMillis() > year_ago) {
                                        year_data++;
                                    } else {
                                        all_data++;
                                    }
                                } else {
                                    if (history_of_relapse.size() > 0) {
                                        index = (-index) - 2;
                                        if (index > 0) {
                                            long last_relapse = history_of_relapse.get(index);
                                            if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) - Simplify_the_time.return_time_in_midnight(last_relapse) < Long.parseLong(return_the_information_from_save(10)) * TimeUnit.DAYS.toMillis(1)) {
                                                if (calendar.getTimeInMillis() > week_ago) {
                                                    week_data++;
                                                }
                                                if (calendar.getTimeInMillis() > month_ago) {
                                                    month_data++;
                                                }
                                                if (calendar.getTimeInMillis() > year_ago) {
                                                    year_data++;
                                                } else {
                                                    all_data++;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            all_data = all_data + year_data;
                            if (all_data == 0) {
                                text_saying_the_how_many_times_for_this_week_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_month_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_year_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_all_time_actual.setText("No data");
                            } else {
                                text_saying_the_how_many_times_for_this_week_actual.setText(String.valueOf(week_data));
                                text_saying_the_how_many_times_for_this_month_actual.setText(String.valueOf(month_data));
                                text_saying_the_how_many_times_for_this_year_actual.setText(String.valueOf(year_data));
                                text_saying_the_how_many_times_for_this_all_time_actual.setText(String.valueOf(all_data));
                            }
                        } else if (return_the_information_from_save(8).equals("dayspermonth")) {
                            long week_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (6 * 86400000L));
                            long month_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (29 * 86400000L));
                            long year_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (364 * 86400000L));
                            int week_data = 0;
                            int month_data = 0;
                            int year_data = 0;
                            int all_data = 0;
                            ArrayList<Long> filter_list = new ArrayList<>();
                            ArrayList<Long> filter_list_at_midnight = new ArrayList<>();
                            Calendar calender = Calendar.getInstance();
                            long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                            long difference = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                            ArrayList<Long> relapse = return_relapses();
                            filter_list = return_relapses();
                            for (int i = 0; i < filter_list.size(); i++) {
                                filter_list_at_midnight.add(Simplify_the_time.return_time_in_midnight(filter_list.get(i)));
                            }
                            if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                                difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                            }
                            for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                                calender.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                                int day_of_the_month = calender.get(Calendar.DAY_OF_MONTH);
                                if (!days_of_months_good_habit.contains(day_of_the_month)) {
                                    if (calender.getTimeInMillis() > week_ago) {
                                        week_data++;
                                    }
                                    if (calender.getTimeInMillis() > month_ago) {
                                        month_data++;
                                    }
                                    if (calender.getTimeInMillis() > year_ago) {
                                        year_data++;
                                    } else {
                                        all_data++;
                                    }
                                } else {
                                    int index = Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(calender.getTimeInMillis()));
                                    if (index >= 0) {
                                        if (calender.getTimeInMillis() > week_ago) {
                                            week_data++;
                                        }
                                        if (calender.getTimeInMillis() > month_ago) {
                                            month_data++;
                                        }
                                        if (calender.getTimeInMillis() > year_ago) {
                                            year_data++;
                                        } else {
                                            all_data++;
                                        }
                                    }
                                }
                            }
                            all_data = all_data + year_data;
                            if (all_data == 0) {
                                text_saying_the_how_many_times_for_this_week_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_month_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_year_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_all_time_actual.setText("No data");
                            } else {
                                text_saying_the_how_many_times_for_this_week_actual.setText(String.valueOf(week_data));
                                text_saying_the_how_many_times_for_this_month_actual.setText(String.valueOf(month_data));
                                text_saying_the_how_many_times_for_this_year_actual.setText(String.valueOf(year_data));
                                text_saying_the_how_many_times_for_this_all_time_actual.setText(String.valueOf(all_data));
                            }
                        }
                    } else if (return_the_information_from_save(7).equals("amount")) {
                        if (return_the_information_from_save(8).equals("everyday")) {
                            int week_data = 0;
                            int month_data = 0;
                            int year_data = 0;
                            int all_data;
                            long time_right_now = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis());
                            long week_ago = time_right_now - (TimeUnit.DAYS.toMillis(7));
                            long month_ago = time_right_now - (TimeUnit.DAYS.toMillis(30));
                            long year_ago = time_right_now - (TimeUnit.DAYS.toMillis(365));
                            for (HashMap.Entry<Long, Integer> entry : hash_map_amount.entrySet()) {
                                int value = entry.getValue();
                                if (value >= Integer.parseInt(return_the_information_from_save(9))) {
                                    long key = entry.getKey();
                                    long time_in_map = Simplify_the_time.return_time_in_midnight(key);
                                    if (time_in_map > week_ago) {
                                        week_data = week_data + 1;
                                    }
                                    if (time_in_map > month_ago) {
                                        month_data = month_data + 1;
                                    }
                                    if (time_in_map > year_ago) {
                                        year_data = year_data + 1;
                                    }
                                }
                            }
                            all_data = week_data + month_data + year_data;
                            if (all_data == 0) {
                                text_saying_the_how_many_times_for_this_week_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_month_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_year_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_all_time_actual.setText("No data");
                            } else {
                                text_saying_the_how_many_times_for_this_week_actual.setText(String.valueOf(week_data));
                                text_saying_the_how_many_times_for_this_month_actual.setText(String.valueOf(month_data));
                                text_saying_the_how_many_times_for_this_year_actual.setText(String.valueOf(year_data));
                                text_saying_the_how_many_times_for_this_all_time_actual.setText(String.valueOf(all_data));
                            }
                        } else if (return_the_information_from_save(8).equals("daysperweek")) {
                            long week_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (6 * 86400000L));
                            long month_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (29 * 86400000L));
                            long year_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (364 * 86400000L));
                            int week_data = 0;
                            int month_data = 0;
                            int year_data = 0;
                            int all_data = 0;
                            long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                            long difference = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                            Calendar calendar = Calendar.getInstance();
                            int sum = 0;
                            if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                                difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                            }
                            for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                                calendar.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                                    if (return_the_information_from_save(10).contains("Mo")) {
                                        if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i))) && hash_map_amount.get(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis())) >= Integer.parseInt(return_the_information_from_save(9))) {
                                            if (calendar.getTimeInMillis() >= week_ago) {
                                                week_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= month_ago) {
                                                month_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= year_ago) {
                                                year_data++;
                                            } else {
                                                all_data++;
                                            }
                                        }
                                    } else {
                                        if (calendar.getTimeInMillis() >= week_ago) {
                                            week_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= month_ago) {
                                            month_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= year_ago) {
                                            year_data++;
                                        } else {
                                            all_data++;
                                        }
                                    }
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                                    if (return_the_information_from_save(10).contains("Tu")) {
                                        if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i))) && hash_map_amount.get(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis())) >= Integer.parseInt(return_the_information_from_save(9))) {
                                            if (calendar.getTimeInMillis() >= week_ago) {
                                                week_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= month_ago) {
                                                month_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= year_ago) {
                                                year_data++;
                                            } else {
                                                all_data++;
                                            }
                                        }
                                    } else {
                                        if (calendar.getTimeInMillis() >= week_ago) {
                                            week_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= month_ago) {
                                            month_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= year_ago) {
                                            year_data++;
                                        } else {
                                            all_data++;
                                        }
                                    }
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                                    if (return_the_information_from_save(10).contains("We")) {
                                        if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i))) && hash_map_amount.get(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis())) >= Integer.parseInt(return_the_information_from_save(9))) {
                                            if (calendar.getTimeInMillis() >= week_ago) {
                                                week_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= month_ago) {
                                                month_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= year_ago) {
                                                year_data++;
                                            } else {
                                                all_data++;
                                            }
                                        }
                                    } else {
                                        if (calendar.getTimeInMillis() >= week_ago) {
                                            week_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= month_ago) {
                                            month_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= year_ago) {
                                            year_data++;
                                        } else {
                                            all_data++;
                                        }
                                    }
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                                    if (return_the_information_from_save(10).contains("Th")) {
                                        if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i))) && hash_map_amount.get(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis())) >= Integer.parseInt(return_the_information_from_save(9))) {
                                            if (calendar.getTimeInMillis() >= week_ago) {
                                                week_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= month_ago) {
                                                month_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= year_ago) {
                                                year_data++;
                                            } else {
                                                all_data++;
                                            }
                                        }
                                    } else {
                                        if (calendar.getTimeInMillis() >= week_ago) {
                                            week_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= month_ago) {
                                            month_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= year_ago) {
                                            year_data++;
                                        } else {
                                            all_data++;
                                        }
                                    }
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                                    if (return_the_information_from_save(10).contains("Fr")) {
                                        if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i))) && hash_map_amount.get(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis())) >= Integer.parseInt(return_the_information_from_save(9))) {
                                            if (calendar.getTimeInMillis() >= week_ago) {
                                                week_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= month_ago) {
                                                month_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= year_ago) {
                                                year_data++;
                                            } else {
                                                all_data++;
                                            }
                                        }
                                    } else {
                                        if (calendar.getTimeInMillis() >= week_ago) {
                                            week_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= month_ago) {
                                            month_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= year_ago) {
                                            year_data++;
                                        } else {
                                            all_data++;
                                        }
                                    }
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                                    if (return_the_information_from_save(10).contains("Sa")) {
                                        if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i))) && hash_map_amount.get(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis())) >= Integer.parseInt(return_the_information_from_save(9))) {
                                            if (calendar.getTimeInMillis() >= week_ago) {
                                                week_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= month_ago) {
                                                month_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= year_ago) {
                                                year_data++;
                                            } else {
                                                all_data++;
                                            }
                                        }
                                    } else {
                                        if (calendar.getTimeInMillis() >= week_ago) {
                                            week_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= month_ago) {
                                            month_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= year_ago) {
                                            year_data++;
                                        } else {
                                            all_data++;
                                        }
                                    }
                                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                                    if (return_the_information_from_save(10).contains("Su")) {
                                        if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i))) && hash_map_amount.get(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis())) >= Integer.parseInt(return_the_information_from_save(9))) {
                                            if (calendar.getTimeInMillis() >= week_ago) {
                                                week_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= month_ago) {
                                                month_data++;
                                            }
                                            if (calendar.getTimeInMillis() >= year_ago) {
                                                year_data++;
                                            } else {
                                                all_data++;
                                            }
                                        }
                                    } else {
                                        if (calendar.getTimeInMillis() >= week_ago) {
                                            week_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= month_ago) {
                                            month_data++;
                                        }
                                        if (calendar.getTimeInMillis() >= year_ago) {
                                            year_data++;
                                        } else {
                                            all_data++;
                                        }
                                    }
                                }
                            }
                            all_data = all_data + year_data;
                            if (all_data == 0) {
                                text_saying_the_how_many_times_for_this_week_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_month_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_year_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_all_time_actual.setText("No data");
                            } else {
                                text_saying_the_how_many_times_for_this_week_actual.setText(String.valueOf(week_data));
                                text_saying_the_how_many_times_for_this_month_actual.setText(String.valueOf(month_data));
                                text_saying_the_how_many_times_for_this_year_actual.setText(String.valueOf(year_data));
                                text_saying_the_how_many_times_for_this_all_time_actual.setText(String.valueOf(all_data));
                            }
                        } else if (return_the_information_from_save(8).equals("everyndays")) {
                            long week_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (6 * 86400000L));
                            long month_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (29 * 86400000L));
                            long year_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (364 * 86400000L));
                            int week_data = 0;
                            int month_data = 0;
                            int year_data = 0;
                            int all_data = 0;
                            long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                            long difference = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                            Calendar calendar = Calendar.getInstance();
                            if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                                difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                            }
                            for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                                calendar.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                                if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis())) && hash_map_amount.get(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis())) >= Integer.parseInt(return_the_information_from_save(9))) {
                                    if (calendar.getTimeInMillis() > week_ago) {
                                        week_data++;
                                    }
                                    if (calendar.getTimeInMillis() > month_ago) {
                                        month_data++;
                                    }
                                    if (calendar.getTimeInMillis() > year_ago) {
                                        year_data++;
                                    } else {
                                        all_data++;
                                    }
                                } else {
                                    if (return_state_of_day(calendar.getTimeInMillis())) {
                                        if (calendar.getTimeInMillis() > week_ago) {
                                            week_data++;
                                        }
                                        if (calendar.getTimeInMillis() > month_ago) {
                                            month_data++;
                                        }
                                        if (calendar.getTimeInMillis() > year_ago) {
                                            year_data++;
                                        } else {
                                            all_data++;
                                        }
                                    }
                                }
                            }
                            all_data = all_data + year_data;
                            if (all_data == 0) {
                                text_saying_the_how_many_times_for_this_week_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_month_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_year_actual.setText("No data");
                                text_saying_the_how_many_times_for_this_all_time_actual.setText("No data");
                            } else {
                                text_saying_the_how_many_times_for_this_week_actual.setText(String.valueOf(week_data));
                                text_saying_the_how_many_times_for_this_month_actual.setText(String.valueOf(month_data));
                                text_saying_the_how_many_times_for_this_year_actual.setText(String.valueOf(year_data));
                                text_saying_the_how_many_times_for_this_all_time_actual.setText(String.valueOf(all_data));
                            }
                        } else if (return_the_information_from_save(8).equals("dayspermonth")) {

                        }
                    } else if (return_the_information_from_save(7).equals("timer")) {

                    }
                }
            } else {
                this_relapses_is_only_available_for_pro_users_pie_chart_view_home.setVisibility(View.VISIBLE);
                this_relapses_is_only_available_for_pro_users_pie_chart_view_home.setBackgroundTintList(ColorStateList.valueOf(color));
                Random random = new Random();
                int week = random.nextInt(60);
                int month = random.nextInt(60) + week;
                int year = random.nextInt(60) + month;
                int all = week + month + year;
                text_saying_the_how_many_times_for_this_week_actual.setText(String.valueOf(week));
                text_saying_the_how_many_times_for_this_month_actual.setText(String.valueOf(month));
                text_saying_the_how_many_times_for_this_year_actual.setText(String.valueOf(year));
                text_saying_the_how_many_times_for_this_all_time_actual.setText(String.valueOf(all));
            }
        }
    }

    private void set_the_title_of_the_year_habit() {
        if (getView() != null) {
            TextView title_saying_the_habit_name_in_year_for_good_habits = getView().findViewById(R.id.title_saying_the_habit_name_in_year_for_good_habits);
            TextView text_saying_which_year_to_show_in_a_good_habits_year = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year);
            String capital_tital = return_the_information_from_save(1);
            title_saying_the_habit_name_in_year_for_good_habits.setText(capital_tital.concat(" in a year"));
            Calendar calendar = Calendar.getInstance();
            text_saying_which_year_to_show_in_a_good_habits_year.setText(String.valueOf(calendar.get(Calendar.YEAR)));
        }
    }

    private void add_the_views() {
        if (getView() != null) {
            View good_habits_layout_circle_jan1 = getView().findViewById(R.id.good_habits_layout_circle_jan1);
            View good_habits_layout_circle_jan2 = getView().findViewById(R.id.good_habits_layout_circle_jan2);
            View good_habits_layout_circle_jan3 = getView().findViewById(R.id.good_habits_layout_circle_jan3);
            View good_habits_layout_circle_jan4 = getView().findViewById(R.id.good_habits_layout_circle_jan4);
            View good_habits_layout_circle_jan5 = getView().findViewById(R.id.good_habits_layout_circle_jan5);
            View good_habits_layout_circle_jan6 = getView().findViewById(R.id.good_habits_layout_circle_jan6);
            View good_habits_layout_circle_jan7 = getView().findViewById(R.id.good_habits_layout_circle_jan7);
            View good_habits_layout_circle_jan8 = getView().findViewById(R.id.good_habits_layout_circle_jan8);
            View good_habits_layout_circle_jan9 = getView().findViewById(R.id.good_habits_layout_circle_jan9);
            View good_habits_layout_circle_jan10 = getView().findViewById(R.id.good_habits_layout_circle_jan10);
            View good_habits_layout_circle_jan11 = getView().findViewById(R.id.good_habits_layout_circle_jan11);
            View good_habits_layout_circle_jan12 = getView().findViewById(R.id.good_habits_layout_circle_jan12);
            View good_habits_layout_circle_jan13 = getView().findViewById(R.id.good_habits_layout_circle_jan13);
            View good_habits_layout_circle_jan14 = getView().findViewById(R.id.good_habits_layout_circle_jan14);
            View good_habits_layout_circle_jan15 = getView().findViewById(R.id.good_habits_layout_circle_jan15);
            View good_habits_layout_circle_jan16 = getView().findViewById(R.id.good_habits_layout_circle_jan16);
            View good_habits_layout_circle_jan17 = getView().findViewById(R.id.good_habits_layout_circle_jan17);
            View good_habits_layout_circle_jan18 = getView().findViewById(R.id.good_habits_layout_circle_jan18);
            View good_habits_layout_circle_jan19 = getView().findViewById(R.id.good_habits_layout_circle_jan19);
            View good_habits_layout_circle_jan20 = getView().findViewById(R.id.good_habits_layout_circle_jan20);
            View good_habits_layout_circle_jan21 = getView().findViewById(R.id.good_habits_layout_circle_jan21);
            View good_habits_layout_circle_jan22 = getView().findViewById(R.id.good_habits_layout_circle_jan22);
            View good_habits_layout_circle_jan23 = getView().findViewById(R.id.good_habits_layout_circle_jan23);
            View good_habits_layout_circle_jan24 = getView().findViewById(R.id.good_habits_layout_circle_jan24);
            View good_habits_layout_circle_jan25 = getView().findViewById(R.id.good_habits_layout_circle_jan25);
            View good_habits_layout_circle_jan26 = getView().findViewById(R.id.good_habits_layout_circle_jan26);
            View good_habits_layout_circle_jan27 = getView().findViewById(R.id.good_habits_layout_circle_jan27);
            View good_habits_layout_circle_jan28 = getView().findViewById(R.id.good_habits_layout_circle_jan28);
            View good_habits_layout_circle_jan29 = getView().findViewById(R.id.good_habits_layout_circle_jan29);
            View good_habits_layout_circle_jan30 = getView().findViewById(R.id.good_habits_layout_circle_jan30);
            View good_habits_layout_circle_jan31 = getView().findViewById(R.id.good_habits_layout_circle_jan31);
            View good_habits_layout_circle_feb1 = getView().findViewById(R.id.good_habits_layout_circle_feb1);
            View good_habits_layout_circle_feb2 = getView().findViewById(R.id.good_habits_layout_circle_feb2);
            View good_habits_layout_circle_feb3 = getView().findViewById(R.id.good_habits_layout_circle_feb3);
            View good_habits_layout_circle_feb4 = getView().findViewById(R.id.good_habits_layout_circle_feb4);
            View good_habits_layout_circle_feb5 = getView().findViewById(R.id.good_habits_layout_circle_feb5);
            View good_habits_layout_circle_feb6 = getView().findViewById(R.id.good_habits_layout_circle_feb6);
            View good_habits_layout_circle_feb7 = getView().findViewById(R.id.good_habits_layout_circle_feb7);
            View good_habits_layout_circle_feb8 = getView().findViewById(R.id.good_habits_layout_circle_feb8);
            View good_habits_layout_circle_feb9 = getView().findViewById(R.id.good_habits_layout_circle_feb9);
            View good_habits_layout_circle_feb10 = getView().findViewById(R.id.good_habits_layout_circle_feb10);
            View good_habits_layout_circle_feb11 = getView().findViewById(R.id.good_habits_layout_circle_feb11);
            View good_habits_layout_circle_feb12 = getView().findViewById(R.id.good_habits_layout_circle_feb12);
            View good_habits_layout_circle_feb13 = getView().findViewById(R.id.good_habits_layout_circle_feb13);
            View good_habits_layout_circle_feb14 = getView().findViewById(R.id.good_habits_layout_circle_feb14);
            View good_habits_layout_circle_feb15 = getView().findViewById(R.id.good_habits_layout_circle_feb15);
            View good_habits_layout_circle_feb16 = getView().findViewById(R.id.good_habits_layout_circle_feb16);
            View good_habits_layout_circle_feb17 = getView().findViewById(R.id.good_habits_layout_circle_feb17);
            View good_habits_layout_circle_feb18 = getView().findViewById(R.id.good_habits_layout_circle_feb18);
            View good_habits_layout_circle_feb19 = getView().findViewById(R.id.good_habits_layout_circle_feb19);
            View good_habits_layout_circle_feb20 = getView().findViewById(R.id.good_habits_layout_circle_feb20);
            View good_habits_layout_circle_feb21 = getView().findViewById(R.id.good_habits_layout_circle_feb21);
            View good_habits_layout_circle_feb22 = getView().findViewById(R.id.good_habits_layout_circle_feb22);
            View good_habits_layout_circle_feb23 = getView().findViewById(R.id.good_habits_layout_circle_feb23);
            View good_habits_layout_circle_feb24 = getView().findViewById(R.id.good_habits_layout_circle_feb24);
            View good_habits_layout_circle_feb25 = getView().findViewById(R.id.good_habits_layout_circle_feb25);
            View good_habits_layout_circle_feb26 = getView().findViewById(R.id.good_habits_layout_circle_feb26);
            View good_habits_layout_circle_feb27 = getView().findViewById(R.id.good_habits_layout_circle_feb27);
            View good_habits_layout_circle_feb28 = getView().findViewById(R.id.good_habits_layout_circle_feb28);
            View good_habits_layout_circle_feb29 = getView().findViewById(R.id.good_habits_layout_circle_feb29);
            View good_habits_layout_circle_mar1 = getView().findViewById(R.id.good_habits_layout_circle_mar1);
            View good_habits_layout_circle_mar2 = getView().findViewById(R.id.good_habits_layout_circle_mar2);
            View good_habits_layout_circle_mar3 = getView().findViewById(R.id.good_habits_layout_circle_mar3);
            View good_habits_layout_circle_mar4 = getView().findViewById(R.id.good_habits_layout_circle_mar4);
            View good_habits_layout_circle_mar5 = getView().findViewById(R.id.good_habits_layout_circle_mar5);
            View good_habits_layout_circle_mar6 = getView().findViewById(R.id.good_habits_layout_circle_mar6);
            View good_habits_layout_circle_mar7 = getView().findViewById(R.id.good_habits_layout_circle_mar7);
            View good_habits_layout_circle_mar8 = getView().findViewById(R.id.good_habits_layout_circle_mar8);
            View good_habits_layout_circle_mar9 = getView().findViewById(R.id.good_habits_layout_circle_mar9);
            View good_habits_layout_circle_mar10 = getView().findViewById(R.id.good_habits_layout_circle_mar10);
            View good_habits_layout_circle_mar11 = getView().findViewById(R.id.good_habits_layout_circle_mar11);
            View good_habits_layout_circle_mar12 = getView().findViewById(R.id.good_habits_layout_circle_mar12);
            View good_habits_layout_circle_mar13 = getView().findViewById(R.id.good_habits_layout_circle_mar13);
            View good_habits_layout_circle_mar14 = getView().findViewById(R.id.good_habits_layout_circle_mar14);
            View good_habits_layout_circle_mar15 = getView().findViewById(R.id.good_habits_layout_circle_mar15);
            View good_habits_layout_circle_mar16 = getView().findViewById(R.id.good_habits_layout_circle_mar16);
            View good_habits_layout_circle_mar17 = getView().findViewById(R.id.good_habits_layout_circle_mar17);
            View good_habits_layout_circle_mar18 = getView().findViewById(R.id.good_habits_layout_circle_mar18);
            View good_habits_layout_circle_mar19 = getView().findViewById(R.id.good_habits_layout_circle_mar19);
            View good_habits_layout_circle_mar20 = getView().findViewById(R.id.good_habits_layout_circle_mar20);
            View good_habits_layout_circle_mar21 = getView().findViewById(R.id.good_habits_layout_circle_mar21);
            View good_habits_layout_circle_mar22 = getView().findViewById(R.id.good_habits_layout_circle_mar22);
            View good_habits_layout_circle_mar23 = getView().findViewById(R.id.good_habits_layout_circle_mar23);
            View good_habits_layout_circle_mar24 = getView().findViewById(R.id.good_habits_layout_circle_mar24);
            View good_habits_layout_circle_mar25 = getView().findViewById(R.id.good_habits_layout_circle_mar25);
            View good_habits_layout_circle_mar26 = getView().findViewById(R.id.good_habits_layout_circle_mar26);
            View good_habits_layout_circle_mar27 = getView().findViewById(R.id.good_habits_layout_circle_mar27);
            View good_habits_layout_circle_mar28 = getView().findViewById(R.id.good_habits_layout_circle_mar28);
            View good_habits_layout_circle_mar29 = getView().findViewById(R.id.good_habits_layout_circle_mar29);
            View good_habits_layout_circle_mar30 = getView().findViewById(R.id.good_habits_layout_circle_mar30);
            View good_habits_layout_circle_mar31 = getView().findViewById(R.id.good_habits_layout_circle_mar31);
            View good_habits_layout_circle_apr1 = getView().findViewById(R.id.good_habits_layout_circle_apr1);
            View good_habits_layout_circle_apr2 = getView().findViewById(R.id.good_habits_layout_circle_apr2);
            View good_habits_layout_circle_apr3 = getView().findViewById(R.id.good_habits_layout_circle_apr3);
            View good_habits_layout_circle_apr4 = getView().findViewById(R.id.good_habits_layout_circle_apr4);
            View good_habits_layout_circle_apr5 = getView().findViewById(R.id.good_habits_layout_circle_apr5);
            View good_habits_layout_circle_apr6 = getView().findViewById(R.id.good_habits_layout_circle_apr6);
            View good_habits_layout_circle_apr7 = getView().findViewById(R.id.good_habits_layout_circle_apr7);
            View good_habits_layout_circle_apr8 = getView().findViewById(R.id.good_habits_layout_circle_apr8);
            View good_habits_layout_circle_apr9 = getView().findViewById(R.id.good_habits_layout_circle_apr9);
            View good_habits_layout_circle_apr10 = getView().findViewById(R.id.good_habits_layout_circle_apr10);
            View good_habits_layout_circle_apr11 = getView().findViewById(R.id.good_habits_layout_circle_apr11);
            View good_habits_layout_circle_apr12 = getView().findViewById(R.id.good_habits_layout_circle_apr12);
            View good_habits_layout_circle_apr13 = getView().findViewById(R.id.good_habits_layout_circle_apr13);
            View good_habits_layout_circle_apr14 = getView().findViewById(R.id.good_habits_layout_circle_apr14);
            View good_habits_layout_circle_apr15 = getView().findViewById(R.id.good_habits_layout_circle_apr15);
            View good_habits_layout_circle_apr16 = getView().findViewById(R.id.good_habits_layout_circle_apr16);
            View good_habits_layout_circle_apr17 = getView().findViewById(R.id.good_habits_layout_circle_apr17);
            View good_habits_layout_circle_apr18 = getView().findViewById(R.id.good_habits_layout_circle_apr18);
            View good_habits_layout_circle_apr19 = getView().findViewById(R.id.good_habits_layout_circle_apr19);
            View good_habits_layout_circle_apr20 = getView().findViewById(R.id.good_habits_layout_circle_apr20);
            View good_habits_layout_circle_apr21 = getView().findViewById(R.id.good_habits_layout_circle_apr21);
            View good_habits_layout_circle_apr22 = getView().findViewById(R.id.good_habits_layout_circle_apr22);
            View good_habits_layout_circle_apr23 = getView().findViewById(R.id.good_habits_layout_circle_apr23);
            View good_habits_layout_circle_apr24 = getView().findViewById(R.id.good_habits_layout_circle_apr24);
            View good_habits_layout_circle_apr25 = getView().findViewById(R.id.good_habits_layout_circle_apr25);
            View good_habits_layout_circle_apr26 = getView().findViewById(R.id.good_habits_layout_circle_apr26);
            View good_habits_layout_circle_apr27 = getView().findViewById(R.id.good_habits_layout_circle_apr27);
            View good_habits_layout_circle_apr28 = getView().findViewById(R.id.good_habits_layout_circle_apr28);
            View good_habits_layout_circle_apr29 = getView().findViewById(R.id.good_habits_layout_circle_apr29);
            View good_habits_layout_circle_apr30 = getView().findViewById(R.id.good_habits_layout_circle_apr30);
            View good_habits_layout_circle_may1 = getView().findViewById(R.id.good_habits_layout_circle_may1);
            View good_habits_layout_circle_may2 = getView().findViewById(R.id.good_habits_layout_circle_may2);
            View good_habits_layout_circle_may3 = getView().findViewById(R.id.good_habits_layout_circle_may3);
            View good_habits_layout_circle_may4 = getView().findViewById(R.id.good_habits_layout_circle_may4);
            View good_habits_layout_circle_may5 = getView().findViewById(R.id.good_habits_layout_circle_may5);
            View good_habits_layout_circle_may6 = getView().findViewById(R.id.good_habits_layout_circle_may6);
            View good_habits_layout_circle_may7 = getView().findViewById(R.id.good_habits_layout_circle_may7);
            View good_habits_layout_circle_may8 = getView().findViewById(R.id.good_habits_layout_circle_may8);
            View good_habits_layout_circle_may9 = getView().findViewById(R.id.good_habits_layout_circle_may9);
            View good_habits_layout_circle_may10 = getView().findViewById(R.id.good_habits_layout_circle_may10);
            View good_habits_layout_circle_may11 = getView().findViewById(R.id.good_habits_layout_circle_may11);
            View good_habits_layout_circle_may12 = getView().findViewById(R.id.good_habits_layout_circle_may12);
            View good_habits_layout_circle_may13 = getView().findViewById(R.id.good_habits_layout_circle_may13);
            View good_habits_layout_circle_may14 = getView().findViewById(R.id.good_habits_layout_circle_may14);
            View good_habits_layout_circle_may15 = getView().findViewById(R.id.good_habits_layout_circle_may15);
            View good_habits_layout_circle_may16 = getView().findViewById(R.id.good_habits_layout_circle_may16);
            View good_habits_layout_circle_may17 = getView().findViewById(R.id.good_habits_layout_circle_may17);
            View good_habits_layout_circle_may18 = getView().findViewById(R.id.good_habits_layout_circle_may18);
            View good_habits_layout_circle_may19 = getView().findViewById(R.id.good_habits_layout_circle_may19);
            View good_habits_layout_circle_may20 = getView().findViewById(R.id.good_habits_layout_circle_may20);
            View good_habits_layout_circle_may21 = getView().findViewById(R.id.good_habits_layout_circle_may21);
            View good_habits_layout_circle_may22 = getView().findViewById(R.id.good_habits_layout_circle_may22);
            View good_habits_layout_circle_may23 = getView().findViewById(R.id.good_habits_layout_circle_may23);
            View good_habits_layout_circle_may24 = getView().findViewById(R.id.good_habits_layout_circle_may24);
            View good_habits_layout_circle_may25 = getView().findViewById(R.id.good_habits_layout_circle_may25);
            View good_habits_layout_circle_may26 = getView().findViewById(R.id.good_habits_layout_circle_may26);
            View good_habits_layout_circle_may27 = getView().findViewById(R.id.good_habits_layout_circle_may27);
            View good_habits_layout_circle_may28 = getView().findViewById(R.id.good_habits_layout_circle_may28);
            View good_habits_layout_circle_may29 = getView().findViewById(R.id.good_habits_layout_circle_may29);
            View good_habits_layout_circle_may30 = getView().findViewById(R.id.good_habits_layout_circle_may30);
            View good_habits_layout_circle_may31 = getView().findViewById(R.id.good_habits_layout_circle_may31);
            View good_habits_layout_circle_jun1 = getView().findViewById(R.id.good_habits_layout_circle_jun1);
            View good_habits_layout_circle_jun2 = getView().findViewById(R.id.good_habits_layout_circle_jun2);
            View good_habits_layout_circle_jun3 = getView().findViewById(R.id.good_habits_layout_circle_jun3);
            View good_habits_layout_circle_jun4 = getView().findViewById(R.id.good_habits_layout_circle_jun4);
            View good_habits_layout_circle_jun5 = getView().findViewById(R.id.good_habits_layout_circle_jun5);
            View good_habits_layout_circle_jun6 = getView().findViewById(R.id.good_habits_layout_circle_jun6);
            View good_habits_layout_circle_jun7 = getView().findViewById(R.id.good_habits_layout_circle_jun7);
            View good_habits_layout_circle_jun8 = getView().findViewById(R.id.good_habits_layout_circle_jun8);
            View good_habits_layout_circle_jun9 = getView().findViewById(R.id.good_habits_layout_circle_jun9);
            View good_habits_layout_circle_jun10 = getView().findViewById(R.id.good_habits_layout_circle_jun10);
            View good_habits_layout_circle_jun11 = getView().findViewById(R.id.good_habits_layout_circle_jun11);
            View good_habits_layout_circle_jun12 = getView().findViewById(R.id.good_habits_layout_circle_jun12);
            View good_habits_layout_circle_jun13 = getView().findViewById(R.id.good_habits_layout_circle_jun13);
            View good_habits_layout_circle_jun14 = getView().findViewById(R.id.good_habits_layout_circle_jun14);
            View good_habits_layout_circle_jun15 = getView().findViewById(R.id.good_habits_layout_circle_jun15);
            View good_habits_layout_circle_jun16 = getView().findViewById(R.id.good_habits_layout_circle_jun16);
            View good_habits_layout_circle_jun17 = getView().findViewById(R.id.good_habits_layout_circle_jun17);
            View good_habits_layout_circle_jun18 = getView().findViewById(R.id.good_habits_layout_circle_jun18);
            View good_habits_layout_circle_jun19 = getView().findViewById(R.id.good_habits_layout_circle_jun19);
            View good_habits_layout_circle_jun20 = getView().findViewById(R.id.good_habits_layout_circle_jun20);
            View good_habits_layout_circle_jun21 = getView().findViewById(R.id.good_habits_layout_circle_jun21);
            View good_habits_layout_circle_jun22 = getView().findViewById(R.id.good_habits_layout_circle_jun22);
            View good_habits_layout_circle_jun23 = getView().findViewById(R.id.good_habits_layout_circle_jun23);
            View good_habits_layout_circle_jun24 = getView().findViewById(R.id.good_habits_layout_circle_jun24);
            View good_habits_layout_circle_jun25 = getView().findViewById(R.id.good_habits_layout_circle_jun25);
            View good_habits_layout_circle_jun26 = getView().findViewById(R.id.good_habits_layout_circle_jun26);
            View good_habits_layout_circle_jun27 = getView().findViewById(R.id.good_habits_layout_circle_jun27);
            View good_habits_layout_circle_jun28 = getView().findViewById(R.id.good_habits_layout_circle_jun28);
            View good_habits_layout_circle_jun29 = getView().findViewById(R.id.good_habits_layout_circle_jun29);
            View good_habits_layout_circle_jun30 = getView().findViewById(R.id.good_habits_layout_circle_jun30);
            View good_habits_layout_circle_jul1 = getView().findViewById(R.id.good_habits_layout_circle_jul1);
            View good_habits_layout_circle_jul2 = getView().findViewById(R.id.good_habits_layout_circle_jul2);
            View good_habits_layout_circle_jul3 = getView().findViewById(R.id.good_habits_layout_circle_jul3);
            View good_habits_layout_circle_jul4 = getView().findViewById(R.id.good_habits_layout_circle_jul4);
            View good_habits_layout_circle_jul5 = getView().findViewById(R.id.good_habits_layout_circle_jul5);
            View good_habits_layout_circle_jul6 = getView().findViewById(R.id.good_habits_layout_circle_jul6);
            View good_habits_layout_circle_jul7 = getView().findViewById(R.id.good_habits_layout_circle_jul7);
            View good_habits_layout_circle_jul8 = getView().findViewById(R.id.good_habits_layout_circle_jul8);
            View good_habits_layout_circle_jul9 = getView().findViewById(R.id.good_habits_layout_circle_jul9);
            View good_habits_layout_circle_jul10 = getView().findViewById(R.id.good_habits_layout_circle_jul10);
            View good_habits_layout_circle_jul11 = getView().findViewById(R.id.good_habits_layout_circle_jul11);
            View good_habits_layout_circle_jul12 = getView().findViewById(R.id.good_habits_layout_circle_jul12);
            View good_habits_layout_circle_jul13 = getView().findViewById(R.id.good_habits_layout_circle_jul13);
            View good_habits_layout_circle_jul14 = getView().findViewById(R.id.good_habits_layout_circle_jul14);
            View good_habits_layout_circle_jul15 = getView().findViewById(R.id.good_habits_layout_circle_jul15);
            View good_habits_layout_circle_jul16 = getView().findViewById(R.id.good_habits_layout_circle_jul16);
            View good_habits_layout_circle_jul17 = getView().findViewById(R.id.good_habits_layout_circle_jul17);
            View good_habits_layout_circle_jul18 = getView().findViewById(R.id.good_habits_layout_circle_jul18);
            View good_habits_layout_circle_jul19 = getView().findViewById(R.id.good_habits_layout_circle_jul19);
            View good_habits_layout_circle_jul20 = getView().findViewById(R.id.good_habits_layout_circle_jul20);
            View good_habits_layout_circle_jul21 = getView().findViewById(R.id.good_habits_layout_circle_jul21);
            View good_habits_layout_circle_jul22 = getView().findViewById(R.id.good_habits_layout_circle_jul22);
            View good_habits_layout_circle_jul23 = getView().findViewById(R.id.good_habits_layout_circle_jul23);
            View good_habits_layout_circle_jul24 = getView().findViewById(R.id.good_habits_layout_circle_jul24);
            View good_habits_layout_circle_jul25 = getView().findViewById(R.id.good_habits_layout_circle_jul25);
            View good_habits_layout_circle_jul26 = getView().findViewById(R.id.good_habits_layout_circle_jul26);
            View good_habits_layout_circle_jul27 = getView().findViewById(R.id.good_habits_layout_circle_jul27);
            View good_habits_layout_circle_jul28 = getView().findViewById(R.id.good_habits_layout_circle_jul28);
            View good_habits_layout_circle_jul29 = getView().findViewById(R.id.good_habits_layout_circle_jul29);
            View good_habits_layout_circle_jul30 = getView().findViewById(R.id.good_habits_layout_circle_jul30);
            View good_habits_layout_circle_jul31 = getView().findViewById(R.id.good_habits_layout_circle_jul31);
            View good_habits_layout_circle_aug1 = getView().findViewById(R.id.good_habits_layout_circle_aug1);
            View good_habits_layout_circle_aug2 = getView().findViewById(R.id.good_habits_layout_circle_aug2);
            View good_habits_layout_circle_aug3 = getView().findViewById(R.id.good_habits_layout_circle_aug3);
            View good_habits_layout_circle_aug4 = getView().findViewById(R.id.good_habits_layout_circle_aug4);
            View good_habits_layout_circle_aug5 = getView().findViewById(R.id.good_habits_layout_circle_aug5);
            View good_habits_layout_circle_aug6 = getView().findViewById(R.id.good_habits_layout_circle_aug6);
            View good_habits_layout_circle_aug7 = getView().findViewById(R.id.good_habits_layout_circle_aug7);
            View good_habits_layout_circle_aug8 = getView().findViewById(R.id.good_habits_layout_circle_aug8);
            View good_habits_layout_circle_aug9 = getView().findViewById(R.id.good_habits_layout_circle_aug9);
            View good_habits_layout_circle_aug10 = getView().findViewById(R.id.good_habits_layout_circle_aug10);
            View good_habits_layout_circle_aug11 = getView().findViewById(R.id.good_habits_layout_circle_aug11);
            View good_habits_layout_circle_aug12 = getView().findViewById(R.id.good_habits_layout_circle_aug12);
            View good_habits_layout_circle_aug13 = getView().findViewById(R.id.good_habits_layout_circle_aug13);
            View good_habits_layout_circle_aug14 = getView().findViewById(R.id.good_habits_layout_circle_aug14);
            View good_habits_layout_circle_aug15 = getView().findViewById(R.id.good_habits_layout_circle_aug15);
            View good_habits_layout_circle_aug16 = getView().findViewById(R.id.good_habits_layout_circle_aug16);
            View good_habits_layout_circle_aug17 = getView().findViewById(R.id.good_habits_layout_circle_aug17);
            View good_habits_layout_circle_aug18 = getView().findViewById(R.id.good_habits_layout_circle_aug18);
            View good_habits_layout_circle_aug19 = getView().findViewById(R.id.good_habits_layout_circle_aug19);
            View good_habits_layout_circle_aug20 = getView().findViewById(R.id.good_habits_layout_circle_aug20);
            View good_habits_layout_circle_aug21 = getView().findViewById(R.id.good_habits_layout_circle_aug21);
            View good_habits_layout_circle_aug22 = getView().findViewById(R.id.good_habits_layout_circle_aug22);
            View good_habits_layout_circle_aug23 = getView().findViewById(R.id.good_habits_layout_circle_aug23);
            View good_habits_layout_circle_aug24 = getView().findViewById(R.id.good_habits_layout_circle_aug24);
            View good_habits_layout_circle_aug25 = getView().findViewById(R.id.good_habits_layout_circle_aug25);
            View good_habits_layout_circle_aug26 = getView().findViewById(R.id.good_habits_layout_circle_aug26);
            View good_habits_layout_circle_aug27 = getView().findViewById(R.id.good_habits_layout_circle_aug27);
            View good_habits_layout_circle_aug28 = getView().findViewById(R.id.good_habits_layout_circle_aug28);
            View good_habits_layout_circle_aug29 = getView().findViewById(R.id.good_habits_layout_circle_aug29);
            View good_habits_layout_circle_aug30 = getView().findViewById(R.id.good_habits_layout_circle_aug30);
            View good_habits_layout_circle_aug31 = getView().findViewById(R.id.good_habits_layout_circle_aug31);
            View good_habits_layout_circle_sep1 = getView().findViewById(R.id.good_habits_layout_circle_sep1);
            View good_habits_layout_circle_sep2 = getView().findViewById(R.id.good_habits_layout_circle_sep2);
            View good_habits_layout_circle_sep3 = getView().findViewById(R.id.good_habits_layout_circle_sep3);
            View good_habits_layout_circle_sep4 = getView().findViewById(R.id.good_habits_layout_circle_sep4);
            View good_habits_layout_circle_sep5 = getView().findViewById(R.id.good_habits_layout_circle_sep5);
            View good_habits_layout_circle_sep6 = getView().findViewById(R.id.good_habits_layout_circle_sep6);
            View good_habits_layout_circle_sep7 = getView().findViewById(R.id.good_habits_layout_circle_sep7);
            View good_habits_layout_circle_sep8 = getView().findViewById(R.id.good_habits_layout_circle_sep8);
            View good_habits_layout_circle_sep9 = getView().findViewById(R.id.good_habits_layout_circle_sep9);
            View good_habits_layout_circle_sep10 = getView().findViewById(R.id.good_habits_layout_circle_sep10);
            View good_habits_layout_circle_sep11 = getView().findViewById(R.id.good_habits_layout_circle_sep11);
            View good_habits_layout_circle_sep12 = getView().findViewById(R.id.good_habits_layout_circle_sep12);
            View good_habits_layout_circle_sep13 = getView().findViewById(R.id.good_habits_layout_circle_sep13);
            View good_habits_layout_circle_sep14 = getView().findViewById(R.id.good_habits_layout_circle_sep14);
            View good_habits_layout_circle_sep15 = getView().findViewById(R.id.good_habits_layout_circle_sep15);
            View good_habits_layout_circle_sep16 = getView().findViewById(R.id.good_habits_layout_circle_sep16);
            View good_habits_layout_circle_sep17 = getView().findViewById(R.id.good_habits_layout_circle_sep17);
            View good_habits_layout_circle_sep18 = getView().findViewById(R.id.good_habits_layout_circle_sep18);
            View good_habits_layout_circle_sep19 = getView().findViewById(R.id.good_habits_layout_circle_sep19);
            View good_habits_layout_circle_sep20 = getView().findViewById(R.id.good_habits_layout_circle_sep20);
            View good_habits_layout_circle_sep21 = getView().findViewById(R.id.good_habits_layout_circle_sep21);
            View good_habits_layout_circle_sep22 = getView().findViewById(R.id.good_habits_layout_circle_sep22);
            View good_habits_layout_circle_sep23 = getView().findViewById(R.id.good_habits_layout_circle_sep23);
            View good_habits_layout_circle_sep24 = getView().findViewById(R.id.good_habits_layout_circle_sep24);
            View good_habits_layout_circle_sep25 = getView().findViewById(R.id.good_habits_layout_circle_sep25);
            View good_habits_layout_circle_sep26 = getView().findViewById(R.id.good_habits_layout_circle_sep26);
            View good_habits_layout_circle_sep27 = getView().findViewById(R.id.good_habits_layout_circle_sep27);
            View good_habits_layout_circle_sep28 = getView().findViewById(R.id.good_habits_layout_circle_sep28);
            View good_habits_layout_circle_sep29 = getView().findViewById(R.id.good_habits_layout_circle_sep29);
            View good_habits_layout_circle_sep30 = getView().findViewById(R.id.good_habits_layout_circle_sep30);
            View good_habits_layout_circle_oct1 = getView().findViewById(R.id.good_habits_layout_circle_oct1);
            View good_habits_layout_circle_oct2 = getView().findViewById(R.id.good_habits_layout_circle_oct2);
            View good_habits_layout_circle_oct3 = getView().findViewById(R.id.good_habits_layout_circle_oct3);
            View good_habits_layout_circle_oct4 = getView().findViewById(R.id.good_habits_layout_circle_oct4);
            View good_habits_layout_circle_oct5 = getView().findViewById(R.id.good_habits_layout_circle_oct5);
            View good_habits_layout_circle_oct6 = getView().findViewById(R.id.good_habits_layout_circle_oct6);
            View good_habits_layout_circle_oct7 = getView().findViewById(R.id.good_habits_layout_circle_oct7);
            View good_habits_layout_circle_oct8 = getView().findViewById(R.id.good_habits_layout_circle_oct8);
            View good_habits_layout_circle_oct9 = getView().findViewById(R.id.good_habits_layout_circle_oct9);
            View good_habits_layout_circle_oct10 = getView().findViewById(R.id.good_habits_layout_circle_oct10);
            View good_habits_layout_circle_oct11 = getView().findViewById(R.id.good_habits_layout_circle_oct11);
            View good_habits_layout_circle_oct12 = getView().findViewById(R.id.good_habits_layout_circle_oct12);
            View good_habits_layout_circle_oct13 = getView().findViewById(R.id.good_habits_layout_circle_oct13);
            View good_habits_layout_circle_oct14 = getView().findViewById(R.id.good_habits_layout_circle_oct14);
            View good_habits_layout_circle_oct15 = getView().findViewById(R.id.good_habits_layout_circle_oct15);
            View good_habits_layout_circle_oct16 = getView().findViewById(R.id.good_habits_layout_circle_oct16);
            View good_habits_layout_circle_oct17 = getView().findViewById(R.id.good_habits_layout_circle_oct17);
            View good_habits_layout_circle_oct18 = getView().findViewById(R.id.good_habits_layout_circle_oct18);
            View good_habits_layout_circle_oct19 = getView().findViewById(R.id.good_habits_layout_circle_oct19);
            View good_habits_layout_circle_oct20 = getView().findViewById(R.id.good_habits_layout_circle_oct20);
            View good_habits_layout_circle_oct21 = getView().findViewById(R.id.good_habits_layout_circle_oct21);
            View good_habits_layout_circle_oct22 = getView().findViewById(R.id.good_habits_layout_circle_oct22);
            View good_habits_layout_circle_oct23 = getView().findViewById(R.id.good_habits_layout_circle_oct23);
            View good_habits_layout_circle_oct24 = getView().findViewById(R.id.good_habits_layout_circle_oct24);
            View good_habits_layout_circle_oct25 = getView().findViewById(R.id.good_habits_layout_circle_oct25);
            View good_habits_layout_circle_oct26 = getView().findViewById(R.id.good_habits_layout_circle_oct26);
            View good_habits_layout_circle_oct27 = getView().findViewById(R.id.good_habits_layout_circle_oct27);
            View good_habits_layout_circle_oct28 = getView().findViewById(R.id.good_habits_layout_circle_oct28);
            View good_habits_layout_circle_oct29 = getView().findViewById(R.id.good_habits_layout_circle_oct29);
            View good_habits_layout_circle_oct30 = getView().findViewById(R.id.good_habits_layout_circle_oct30);
            View good_habits_layout_circle_oct31 = getView().findViewById(R.id.good_habits_layout_circle_oct31);
            View good_habits_layout_circle_nov1 = getView().findViewById(R.id.good_habits_layout_circle_nov1);
            View good_habits_layout_circle_nov2 = getView().findViewById(R.id.good_habits_layout_circle_nov2);
            View good_habits_layout_circle_nov3 = getView().findViewById(R.id.good_habits_layout_circle_nov3);
            View good_habits_layout_circle_nov4 = getView().findViewById(R.id.good_habits_layout_circle_nov4);
            View good_habits_layout_circle_nov5 = getView().findViewById(R.id.good_habits_layout_circle_nov5);
            View good_habits_layout_circle_nov6 = getView().findViewById(R.id.good_habits_layout_circle_nov6);
            View good_habits_layout_circle_nov7 = getView().findViewById(R.id.good_habits_layout_circle_nov7);
            View good_habits_layout_circle_nov8 = getView().findViewById(R.id.good_habits_layout_circle_nov8);
            View good_habits_layout_circle_nov9 = getView().findViewById(R.id.good_habits_layout_circle_nov9);
            View good_habits_layout_circle_nov10 = getView().findViewById(R.id.good_habits_layout_circle_nov10);
            View good_habits_layout_circle_nov11 = getView().findViewById(R.id.good_habits_layout_circle_nov11);
            View good_habits_layout_circle_nov12 = getView().findViewById(R.id.good_habits_layout_circle_nov12);
            View good_habits_layout_circle_nov13 = getView().findViewById(R.id.good_habits_layout_circle_nov13);
            View good_habits_layout_circle_nov14 = getView().findViewById(R.id.good_habits_layout_circle_nov14);
            View good_habits_layout_circle_nov15 = getView().findViewById(R.id.good_habits_layout_circle_nov15);
            View good_habits_layout_circle_nov16 = getView().findViewById(R.id.good_habits_layout_circle_nov16);
            View good_habits_layout_circle_nov17 = getView().findViewById(R.id.good_habits_layout_circle_nov17);
            View good_habits_layout_circle_nov18 = getView().findViewById(R.id.good_habits_layout_circle_nov18);
            View good_habits_layout_circle_nov19 = getView().findViewById(R.id.good_habits_layout_circle_nov19);
            View good_habits_layout_circle_nov20 = getView().findViewById(R.id.good_habits_layout_circle_nov20);
            View good_habits_layout_circle_nov21 = getView().findViewById(R.id.good_habits_layout_circle_nov21);
            View good_habits_layout_circle_nov22 = getView().findViewById(R.id.good_habits_layout_circle_nov22);
            View good_habits_layout_circle_nov23 = getView().findViewById(R.id.good_habits_layout_circle_nov23);
            View good_habits_layout_circle_nov24 = getView().findViewById(R.id.good_habits_layout_circle_nov24);
            View good_habits_layout_circle_nov25 = getView().findViewById(R.id.good_habits_layout_circle_nov25);
            View good_habits_layout_circle_nov26 = getView().findViewById(R.id.good_habits_layout_circle_nov26);
            View good_habits_layout_circle_nov27 = getView().findViewById(R.id.good_habits_layout_circle_nov27);
            View good_habits_layout_circle_nov28 = getView().findViewById(R.id.good_habits_layout_circle_nov28);
            View good_habits_layout_circle_nov29 = getView().findViewById(R.id.good_habits_layout_circle_nov29);
            View good_habits_layout_circle_nov30 = getView().findViewById(R.id.good_habits_layout_circle_nov30);
            View good_habits_layout_circle_dec1 = getView().findViewById(R.id.good_habits_layout_circle_dec1);
            View good_habits_layout_circle_dec2 = getView().findViewById(R.id.good_habits_layout_circle_dec2);
            View good_habits_layout_circle_dec3 = getView().findViewById(R.id.good_habits_layout_circle_dec3);
            View good_habits_layout_circle_dec4 = getView().findViewById(R.id.good_habits_layout_circle_dec4);
            View good_habits_layout_circle_dec5 = getView().findViewById(R.id.good_habits_layout_circle_dec5);
            View good_habits_layout_circle_dec6 = getView().findViewById(R.id.good_habits_layout_circle_dec6);
            View good_habits_layout_circle_dec7 = getView().findViewById(R.id.good_habits_layout_circle_dec7);
            View good_habits_layout_circle_dec8 = getView().findViewById(R.id.good_habits_layout_circle_dec8);
            View good_habits_layout_circle_dec9 = getView().findViewById(R.id.good_habits_layout_circle_dec9);
            View good_habits_layout_circle_dec10 = getView().findViewById(R.id.good_habits_layout_circle_dec10);
            View good_habits_layout_circle_dec11 = getView().findViewById(R.id.good_habits_layout_circle_dec11);
            View good_habits_layout_circle_dec12 = getView().findViewById(R.id.good_habits_layout_circle_dec12);
            View good_habits_layout_circle_dec13 = getView().findViewById(R.id.good_habits_layout_circle_dec13);
            View good_habits_layout_circle_dec14 = getView().findViewById(R.id.good_habits_layout_circle_dec14);
            View good_habits_layout_circle_dec15 = getView().findViewById(R.id.good_habits_layout_circle_dec15);
            View good_habits_layout_circle_dec16 = getView().findViewById(R.id.good_habits_layout_circle_dec16);
            View good_habits_layout_circle_dec17 = getView().findViewById(R.id.good_habits_layout_circle_dec17);
            View good_habits_layout_circle_dec18 = getView().findViewById(R.id.good_habits_layout_circle_dec18);
            View good_habits_layout_circle_dec19 = getView().findViewById(R.id.good_habits_layout_circle_dec19);
            View good_habits_layout_circle_dec20 = getView().findViewById(R.id.good_habits_layout_circle_dec20);
            View good_habits_layout_circle_dec21 = getView().findViewById(R.id.good_habits_layout_circle_dec21);
            View good_habits_layout_circle_dec22 = getView().findViewById(R.id.good_habits_layout_circle_dec22);
            View good_habits_layout_circle_dec23 = getView().findViewById(R.id.good_habits_layout_circle_dec23);
            View good_habits_layout_circle_dec24 = getView().findViewById(R.id.good_habits_layout_circle_dec24);
            View good_habits_layout_circle_dec25 = getView().findViewById(R.id.good_habits_layout_circle_dec25);
            View good_habits_layout_circle_dec26 = getView().findViewById(R.id.good_habits_layout_circle_dec26);
            View good_habits_layout_circle_dec27 = getView().findViewById(R.id.good_habits_layout_circle_dec27);
            View good_habits_layout_circle_dec28 = getView().findViewById(R.id.good_habits_layout_circle_dec28);
            View good_habits_layout_circle_dec29 = getView().findViewById(R.id.good_habits_layout_circle_dec29);
            View good_habits_layout_circle_dec30 = getView().findViewById(R.id.good_habits_layout_circle_dec30);
            View good_habits_layout_circle_dec31 = getView().findViewById(R.id.good_habits_layout_circle_dec31);
            list_of_all_the_calender_views = new View[366];
            list_of_all_the_calender_views[0] = good_habits_layout_circle_jan1;
            list_of_all_the_calender_views[1] = good_habits_layout_circle_jan2;
            list_of_all_the_calender_views[2] = good_habits_layout_circle_jan3;
            list_of_all_the_calender_views[3] = good_habits_layout_circle_jan4;
            list_of_all_the_calender_views[4] = good_habits_layout_circle_jan5;
            list_of_all_the_calender_views[5] = good_habits_layout_circle_jan6;
            list_of_all_the_calender_views[6] = good_habits_layout_circle_jan7;
            list_of_all_the_calender_views[7] = good_habits_layout_circle_jan8;
            list_of_all_the_calender_views[8] = good_habits_layout_circle_jan9;
            list_of_all_the_calender_views[9] = good_habits_layout_circle_jan10;
            list_of_all_the_calender_views[10] = good_habits_layout_circle_jan11;
            list_of_all_the_calender_views[11] = good_habits_layout_circle_jan12;
            list_of_all_the_calender_views[12] = good_habits_layout_circle_jan13;
            list_of_all_the_calender_views[13] = good_habits_layout_circle_jan14;
            list_of_all_the_calender_views[14] = good_habits_layout_circle_jan15;
            list_of_all_the_calender_views[15] = good_habits_layout_circle_jan16;
            list_of_all_the_calender_views[16] = good_habits_layout_circle_jan17;
            list_of_all_the_calender_views[17] = good_habits_layout_circle_jan18;
            list_of_all_the_calender_views[18] = good_habits_layout_circle_jan19;
            list_of_all_the_calender_views[19] = good_habits_layout_circle_jan20;
            list_of_all_the_calender_views[20] = good_habits_layout_circle_jan21;
            list_of_all_the_calender_views[21] = good_habits_layout_circle_jan22;
            list_of_all_the_calender_views[22] = good_habits_layout_circle_jan23;
            list_of_all_the_calender_views[23] = good_habits_layout_circle_jan24;
            list_of_all_the_calender_views[24] = good_habits_layout_circle_jan25;
            list_of_all_the_calender_views[25] = good_habits_layout_circle_jan26;
            list_of_all_the_calender_views[26] = good_habits_layout_circle_jan27;
            list_of_all_the_calender_views[27] = good_habits_layout_circle_jan28;
            list_of_all_the_calender_views[28] = good_habits_layout_circle_jan29;
            list_of_all_the_calender_views[29] = good_habits_layout_circle_jan30;
            list_of_all_the_calender_views[30] = good_habits_layout_circle_jan31;
            list_of_all_the_calender_views[31] = good_habits_layout_circle_feb1;
            list_of_all_the_calender_views[32] = good_habits_layout_circle_feb2;
            list_of_all_the_calender_views[33] = good_habits_layout_circle_feb3;
            list_of_all_the_calender_views[34] = good_habits_layout_circle_feb4;
            list_of_all_the_calender_views[35] = good_habits_layout_circle_feb5;
            list_of_all_the_calender_views[36] = good_habits_layout_circle_feb6;
            list_of_all_the_calender_views[37] = good_habits_layout_circle_feb7;
            list_of_all_the_calender_views[38] = good_habits_layout_circle_feb8;
            list_of_all_the_calender_views[39] = good_habits_layout_circle_feb9;
            list_of_all_the_calender_views[40] = good_habits_layout_circle_feb10;
            list_of_all_the_calender_views[41] = good_habits_layout_circle_feb11;
            list_of_all_the_calender_views[42] = good_habits_layout_circle_feb12;
            list_of_all_the_calender_views[43] = good_habits_layout_circle_feb13;
            list_of_all_the_calender_views[44] = good_habits_layout_circle_feb14;
            list_of_all_the_calender_views[45] = good_habits_layout_circle_feb15;
            list_of_all_the_calender_views[46] = good_habits_layout_circle_feb16;
            list_of_all_the_calender_views[47] = good_habits_layout_circle_feb17;
            list_of_all_the_calender_views[48] = good_habits_layout_circle_feb18;
            list_of_all_the_calender_views[49] = good_habits_layout_circle_feb19;
            list_of_all_the_calender_views[50] = good_habits_layout_circle_feb20;
            list_of_all_the_calender_views[51] = good_habits_layout_circle_feb21;
            list_of_all_the_calender_views[52] = good_habits_layout_circle_feb22;
            list_of_all_the_calender_views[53] = good_habits_layout_circle_feb23;
            list_of_all_the_calender_views[54] = good_habits_layout_circle_feb24;
            list_of_all_the_calender_views[55] = good_habits_layout_circle_feb25;
            list_of_all_the_calender_views[56] = good_habits_layout_circle_feb26;
            list_of_all_the_calender_views[57] = good_habits_layout_circle_feb27;
            list_of_all_the_calender_views[58] = good_habits_layout_circle_feb28;
            list_of_all_the_calender_views[59] = good_habits_layout_circle_feb29;
            list_of_all_the_calender_views[60] = good_habits_layout_circle_mar1;
            list_of_all_the_calender_views[61] = good_habits_layout_circle_mar2;
            list_of_all_the_calender_views[62] = good_habits_layout_circle_mar3;
            list_of_all_the_calender_views[63] = good_habits_layout_circle_mar4;
            list_of_all_the_calender_views[64] = good_habits_layout_circle_mar5;
            list_of_all_the_calender_views[65] = good_habits_layout_circle_mar6;
            list_of_all_the_calender_views[66] = good_habits_layout_circle_mar7;
            list_of_all_the_calender_views[67] = good_habits_layout_circle_mar8;
            list_of_all_the_calender_views[68] = good_habits_layout_circle_mar9;
            list_of_all_the_calender_views[69] = good_habits_layout_circle_mar10;
            list_of_all_the_calender_views[70] = good_habits_layout_circle_mar11;
            list_of_all_the_calender_views[71] = good_habits_layout_circle_mar12;
            list_of_all_the_calender_views[72] = good_habits_layout_circle_mar13;
            list_of_all_the_calender_views[73] = good_habits_layout_circle_mar14;
            list_of_all_the_calender_views[74] = good_habits_layout_circle_mar15;
            list_of_all_the_calender_views[75] = good_habits_layout_circle_mar16;
            list_of_all_the_calender_views[76] = good_habits_layout_circle_mar17;
            list_of_all_the_calender_views[77] = good_habits_layout_circle_mar18;
            list_of_all_the_calender_views[78] = good_habits_layout_circle_mar19;
            list_of_all_the_calender_views[79] = good_habits_layout_circle_mar20;
            list_of_all_the_calender_views[80] = good_habits_layout_circle_mar21;
            list_of_all_the_calender_views[81] = good_habits_layout_circle_mar22;
            list_of_all_the_calender_views[82] = good_habits_layout_circle_mar23;
            list_of_all_the_calender_views[83] = good_habits_layout_circle_mar24;
            list_of_all_the_calender_views[84] = good_habits_layout_circle_mar25;
            list_of_all_the_calender_views[85] = good_habits_layout_circle_mar26;
            list_of_all_the_calender_views[86] = good_habits_layout_circle_mar27;
            list_of_all_the_calender_views[87] = good_habits_layout_circle_mar28;
            list_of_all_the_calender_views[88] = good_habits_layout_circle_mar29;
            list_of_all_the_calender_views[89] = good_habits_layout_circle_mar30;
            list_of_all_the_calender_views[90] = good_habits_layout_circle_mar31;
            list_of_all_the_calender_views[91] = good_habits_layout_circle_apr1;
            list_of_all_the_calender_views[92] = good_habits_layout_circle_apr2;
            list_of_all_the_calender_views[93] = good_habits_layout_circle_apr3;
            list_of_all_the_calender_views[94] = good_habits_layout_circle_apr4;
            list_of_all_the_calender_views[95] = good_habits_layout_circle_apr5;
            list_of_all_the_calender_views[96] = good_habits_layout_circle_apr6;
            list_of_all_the_calender_views[97] = good_habits_layout_circle_apr7;
            list_of_all_the_calender_views[98] = good_habits_layout_circle_apr8;
            list_of_all_the_calender_views[99] = good_habits_layout_circle_apr9;
            list_of_all_the_calender_views[100] = good_habits_layout_circle_apr10;
            list_of_all_the_calender_views[101] = good_habits_layout_circle_apr11;
            list_of_all_the_calender_views[102] = good_habits_layout_circle_apr12;
            list_of_all_the_calender_views[103] = good_habits_layout_circle_apr13;
            list_of_all_the_calender_views[104] = good_habits_layout_circle_apr14;
            list_of_all_the_calender_views[105] = good_habits_layout_circle_apr15;
            list_of_all_the_calender_views[106] = good_habits_layout_circle_apr16;
            list_of_all_the_calender_views[107] = good_habits_layout_circle_apr17;
            list_of_all_the_calender_views[108] = good_habits_layout_circle_apr18;
            list_of_all_the_calender_views[109] = good_habits_layout_circle_apr19;
            list_of_all_the_calender_views[110] = good_habits_layout_circle_apr20;
            list_of_all_the_calender_views[111] = good_habits_layout_circle_apr21;
            list_of_all_the_calender_views[112] = good_habits_layout_circle_apr22;
            list_of_all_the_calender_views[113] = good_habits_layout_circle_apr23;
            list_of_all_the_calender_views[114] = good_habits_layout_circle_apr24;
            list_of_all_the_calender_views[115] = good_habits_layout_circle_apr25;
            list_of_all_the_calender_views[116] = good_habits_layout_circle_apr26;
            list_of_all_the_calender_views[117] = good_habits_layout_circle_apr27;
            list_of_all_the_calender_views[118] = good_habits_layout_circle_apr28;
            list_of_all_the_calender_views[119] = good_habits_layout_circle_apr29;
            list_of_all_the_calender_views[120] = good_habits_layout_circle_apr30;
            list_of_all_the_calender_views[121] = good_habits_layout_circle_may1;
            list_of_all_the_calender_views[122] = good_habits_layout_circle_may2;
            list_of_all_the_calender_views[123] = good_habits_layout_circle_may3;
            list_of_all_the_calender_views[124] = good_habits_layout_circle_may4;
            list_of_all_the_calender_views[125] = good_habits_layout_circle_may5;
            list_of_all_the_calender_views[126] = good_habits_layout_circle_may6;
            list_of_all_the_calender_views[127] = good_habits_layout_circle_may7;
            list_of_all_the_calender_views[128] = good_habits_layout_circle_may8;
            list_of_all_the_calender_views[129] = good_habits_layout_circle_may9;
            list_of_all_the_calender_views[130] = good_habits_layout_circle_may10;
            list_of_all_the_calender_views[131] = good_habits_layout_circle_may11;
            list_of_all_the_calender_views[132] = good_habits_layout_circle_may12;
            list_of_all_the_calender_views[133] = good_habits_layout_circle_may13;
            list_of_all_the_calender_views[134] = good_habits_layout_circle_may14;
            list_of_all_the_calender_views[135] = good_habits_layout_circle_may15;
            list_of_all_the_calender_views[136] = good_habits_layout_circle_may16;
            list_of_all_the_calender_views[137] = good_habits_layout_circle_may17;
            list_of_all_the_calender_views[138] = good_habits_layout_circle_may18;
            list_of_all_the_calender_views[139] = good_habits_layout_circle_may19;
            list_of_all_the_calender_views[140] = good_habits_layout_circle_may20;
            list_of_all_the_calender_views[141] = good_habits_layout_circle_may21;
            list_of_all_the_calender_views[142] = good_habits_layout_circle_may22;
            list_of_all_the_calender_views[143] = good_habits_layout_circle_may23;
            list_of_all_the_calender_views[144] = good_habits_layout_circle_may24;
            list_of_all_the_calender_views[145] = good_habits_layout_circle_may25;
            list_of_all_the_calender_views[146] = good_habits_layout_circle_may26;
            list_of_all_the_calender_views[147] = good_habits_layout_circle_may27;
            list_of_all_the_calender_views[148] = good_habits_layout_circle_may28;
            list_of_all_the_calender_views[149] = good_habits_layout_circle_may29;
            list_of_all_the_calender_views[150] = good_habits_layout_circle_may30;
            list_of_all_the_calender_views[151] = good_habits_layout_circle_may31;
            list_of_all_the_calender_views[152] = good_habits_layout_circle_jun1;
            list_of_all_the_calender_views[153] = good_habits_layout_circle_jun2;
            list_of_all_the_calender_views[154] = good_habits_layout_circle_jun3;
            list_of_all_the_calender_views[155] = good_habits_layout_circle_jun4;
            list_of_all_the_calender_views[156] = good_habits_layout_circle_jun5;
            list_of_all_the_calender_views[157] = good_habits_layout_circle_jun6;
            list_of_all_the_calender_views[158] = good_habits_layout_circle_jun7;
            list_of_all_the_calender_views[159] = good_habits_layout_circle_jun8;
            list_of_all_the_calender_views[160] = good_habits_layout_circle_jun9;
            list_of_all_the_calender_views[161] = good_habits_layout_circle_jun10;
            list_of_all_the_calender_views[162] = good_habits_layout_circle_jun11;
            list_of_all_the_calender_views[163] = good_habits_layout_circle_jun12;
            list_of_all_the_calender_views[164] = good_habits_layout_circle_jun13;
            list_of_all_the_calender_views[165] = good_habits_layout_circle_jun14;
            list_of_all_the_calender_views[166] = good_habits_layout_circle_jun15;
            list_of_all_the_calender_views[167] = good_habits_layout_circle_jun16;
            list_of_all_the_calender_views[168] = good_habits_layout_circle_jun17;
            list_of_all_the_calender_views[169] = good_habits_layout_circle_jun18;
            list_of_all_the_calender_views[170] = good_habits_layout_circle_jun19;
            list_of_all_the_calender_views[171] = good_habits_layout_circle_jun20;
            list_of_all_the_calender_views[172] = good_habits_layout_circle_jun21;
            list_of_all_the_calender_views[173] = good_habits_layout_circle_jun22;
            list_of_all_the_calender_views[174] = good_habits_layout_circle_jun23;
            list_of_all_the_calender_views[175] = good_habits_layout_circle_jun24;
            list_of_all_the_calender_views[176] = good_habits_layout_circle_jun25;
            list_of_all_the_calender_views[177] = good_habits_layout_circle_jun26;
            list_of_all_the_calender_views[178] = good_habits_layout_circle_jun27;
            list_of_all_the_calender_views[179] = good_habits_layout_circle_jun28;
            list_of_all_the_calender_views[180] = good_habits_layout_circle_jun29;
            list_of_all_the_calender_views[181] = good_habits_layout_circle_jun30;
            list_of_all_the_calender_views[182] = good_habits_layout_circle_jul1;
            list_of_all_the_calender_views[183] = good_habits_layout_circle_jul2;
            list_of_all_the_calender_views[184] = good_habits_layout_circle_jul3;
            list_of_all_the_calender_views[185] = good_habits_layout_circle_jul4;
            list_of_all_the_calender_views[186] = good_habits_layout_circle_jul5;
            list_of_all_the_calender_views[187] = good_habits_layout_circle_jul6;
            list_of_all_the_calender_views[188] = good_habits_layout_circle_jul7;
            list_of_all_the_calender_views[189] = good_habits_layout_circle_jul8;
            list_of_all_the_calender_views[190] = good_habits_layout_circle_jul9;
            list_of_all_the_calender_views[191] = good_habits_layout_circle_jul10;
            list_of_all_the_calender_views[192] = good_habits_layout_circle_jul11;
            list_of_all_the_calender_views[193] = good_habits_layout_circle_jul12;
            list_of_all_the_calender_views[194] = good_habits_layout_circle_jul13;
            list_of_all_the_calender_views[195] = good_habits_layout_circle_jul14;
            list_of_all_the_calender_views[196] = good_habits_layout_circle_jul15;
            list_of_all_the_calender_views[197] = good_habits_layout_circle_jul16;
            list_of_all_the_calender_views[198] = good_habits_layout_circle_jul17;
            list_of_all_the_calender_views[199] = good_habits_layout_circle_jul18;
            list_of_all_the_calender_views[200] = good_habits_layout_circle_jul19;
            list_of_all_the_calender_views[201] = good_habits_layout_circle_jul20;
            list_of_all_the_calender_views[202] = good_habits_layout_circle_jul21;
            list_of_all_the_calender_views[203] = good_habits_layout_circle_jul22;
            list_of_all_the_calender_views[204] = good_habits_layout_circle_jul23;
            list_of_all_the_calender_views[205] = good_habits_layout_circle_jul24;
            list_of_all_the_calender_views[206] = good_habits_layout_circle_jul25;
            list_of_all_the_calender_views[207] = good_habits_layout_circle_jul26;
            list_of_all_the_calender_views[208] = good_habits_layout_circle_jul27;
            list_of_all_the_calender_views[209] = good_habits_layout_circle_jul28;
            list_of_all_the_calender_views[210] = good_habits_layout_circle_jul29;
            list_of_all_the_calender_views[211] = good_habits_layout_circle_jul30;
            list_of_all_the_calender_views[212] = good_habits_layout_circle_jul31;
            list_of_all_the_calender_views[213] = good_habits_layout_circle_aug1;
            list_of_all_the_calender_views[214] = good_habits_layout_circle_aug2;
            list_of_all_the_calender_views[215] = good_habits_layout_circle_aug3;
            list_of_all_the_calender_views[216] = good_habits_layout_circle_aug4;
            list_of_all_the_calender_views[217] = good_habits_layout_circle_aug5;
            list_of_all_the_calender_views[218] = good_habits_layout_circle_aug6;
            list_of_all_the_calender_views[219] = good_habits_layout_circle_aug7;
            list_of_all_the_calender_views[220] = good_habits_layout_circle_aug8;
            list_of_all_the_calender_views[221] = good_habits_layout_circle_aug9;
            list_of_all_the_calender_views[222] = good_habits_layout_circle_aug10;
            list_of_all_the_calender_views[223] = good_habits_layout_circle_aug11;
            list_of_all_the_calender_views[224] = good_habits_layout_circle_aug12;
            list_of_all_the_calender_views[225] = good_habits_layout_circle_aug13;
            list_of_all_the_calender_views[226] = good_habits_layout_circle_aug14;
            list_of_all_the_calender_views[227] = good_habits_layout_circle_aug15;
            list_of_all_the_calender_views[228] = good_habits_layout_circle_aug16;
            list_of_all_the_calender_views[229] = good_habits_layout_circle_aug17;
            list_of_all_the_calender_views[230] = good_habits_layout_circle_aug18;
            list_of_all_the_calender_views[231] = good_habits_layout_circle_aug19;
            list_of_all_the_calender_views[232] = good_habits_layout_circle_aug20;
            list_of_all_the_calender_views[233] = good_habits_layout_circle_aug21;
            list_of_all_the_calender_views[234] = good_habits_layout_circle_aug22;
            list_of_all_the_calender_views[235] = good_habits_layout_circle_aug23;
            list_of_all_the_calender_views[236] = good_habits_layout_circle_aug24;
            list_of_all_the_calender_views[237] = good_habits_layout_circle_aug25;
            list_of_all_the_calender_views[238] = good_habits_layout_circle_aug26;
            list_of_all_the_calender_views[239] = good_habits_layout_circle_aug27;
            list_of_all_the_calender_views[240] = good_habits_layout_circle_aug28;
            list_of_all_the_calender_views[241] = good_habits_layout_circle_aug29;
            list_of_all_the_calender_views[242] = good_habits_layout_circle_aug30;
            list_of_all_the_calender_views[243] = good_habits_layout_circle_aug31;
            list_of_all_the_calender_views[244] = good_habits_layout_circle_sep1;
            list_of_all_the_calender_views[245] = good_habits_layout_circle_sep2;
            list_of_all_the_calender_views[246] = good_habits_layout_circle_sep3;
            list_of_all_the_calender_views[247] = good_habits_layout_circle_sep4;
            list_of_all_the_calender_views[248] = good_habits_layout_circle_sep5;
            list_of_all_the_calender_views[249] = good_habits_layout_circle_sep6;
            list_of_all_the_calender_views[250] = good_habits_layout_circle_sep7;
            list_of_all_the_calender_views[251] = good_habits_layout_circle_sep8;
            list_of_all_the_calender_views[252] = good_habits_layout_circle_sep9;
            list_of_all_the_calender_views[253] = good_habits_layout_circle_sep10;
            list_of_all_the_calender_views[254] = good_habits_layout_circle_sep11;
            list_of_all_the_calender_views[255] = good_habits_layout_circle_sep12;
            list_of_all_the_calender_views[256] = good_habits_layout_circle_sep13;
            list_of_all_the_calender_views[257] = good_habits_layout_circle_sep14;
            list_of_all_the_calender_views[258] = good_habits_layout_circle_sep15;
            list_of_all_the_calender_views[259] = good_habits_layout_circle_sep16;
            list_of_all_the_calender_views[260] = good_habits_layout_circle_sep17;
            list_of_all_the_calender_views[261] = good_habits_layout_circle_sep18;
            list_of_all_the_calender_views[262] = good_habits_layout_circle_sep19;
            list_of_all_the_calender_views[263] = good_habits_layout_circle_sep20;
            list_of_all_the_calender_views[264] = good_habits_layout_circle_sep21;
            list_of_all_the_calender_views[265] = good_habits_layout_circle_sep22;
            list_of_all_the_calender_views[266] = good_habits_layout_circle_sep23;
            list_of_all_the_calender_views[267] = good_habits_layout_circle_sep24;
            list_of_all_the_calender_views[268] = good_habits_layout_circle_sep25;
            list_of_all_the_calender_views[269] = good_habits_layout_circle_sep26;
            list_of_all_the_calender_views[270] = good_habits_layout_circle_sep27;
            list_of_all_the_calender_views[271] = good_habits_layout_circle_sep28;
            list_of_all_the_calender_views[272] = good_habits_layout_circle_sep29;
            list_of_all_the_calender_views[273] = good_habits_layout_circle_sep30;
            list_of_all_the_calender_views[274] = good_habits_layout_circle_oct1;
            list_of_all_the_calender_views[275] = good_habits_layout_circle_oct2;
            list_of_all_the_calender_views[276] = good_habits_layout_circle_oct3;
            list_of_all_the_calender_views[277] = good_habits_layout_circle_oct4;
            list_of_all_the_calender_views[278] = good_habits_layout_circle_oct5;
            list_of_all_the_calender_views[279] = good_habits_layout_circle_oct6;
            list_of_all_the_calender_views[280] = good_habits_layout_circle_oct7;
            list_of_all_the_calender_views[281] = good_habits_layout_circle_oct8;
            list_of_all_the_calender_views[282] = good_habits_layout_circle_oct9;
            list_of_all_the_calender_views[283] = good_habits_layout_circle_oct10;
            list_of_all_the_calender_views[284] = good_habits_layout_circle_oct11;
            list_of_all_the_calender_views[285] = good_habits_layout_circle_oct12;
            list_of_all_the_calender_views[286] = good_habits_layout_circle_oct13;
            list_of_all_the_calender_views[287] = good_habits_layout_circle_oct14;
            list_of_all_the_calender_views[288] = good_habits_layout_circle_oct15;
            list_of_all_the_calender_views[289] = good_habits_layout_circle_oct16;
            list_of_all_the_calender_views[290] = good_habits_layout_circle_oct17;
            list_of_all_the_calender_views[291] = good_habits_layout_circle_oct18;
            list_of_all_the_calender_views[292] = good_habits_layout_circle_oct19;
            list_of_all_the_calender_views[293] = good_habits_layout_circle_oct20;
            list_of_all_the_calender_views[294] = good_habits_layout_circle_oct21;
            list_of_all_the_calender_views[295] = good_habits_layout_circle_oct22;
            list_of_all_the_calender_views[296] = good_habits_layout_circle_oct23;
            list_of_all_the_calender_views[297] = good_habits_layout_circle_oct24;
            list_of_all_the_calender_views[298] = good_habits_layout_circle_oct25;
            list_of_all_the_calender_views[299] = good_habits_layout_circle_oct26;
            list_of_all_the_calender_views[300] = good_habits_layout_circle_oct27;
            list_of_all_the_calender_views[301] = good_habits_layout_circle_oct28;
            list_of_all_the_calender_views[302] = good_habits_layout_circle_oct29;
            list_of_all_the_calender_views[303] = good_habits_layout_circle_oct30;
            list_of_all_the_calender_views[304] = good_habits_layout_circle_oct31;
            list_of_all_the_calender_views[305] = good_habits_layout_circle_nov1;
            list_of_all_the_calender_views[306] = good_habits_layout_circle_nov2;
            list_of_all_the_calender_views[307] = good_habits_layout_circle_nov3;
            list_of_all_the_calender_views[308] = good_habits_layout_circle_nov4;
            list_of_all_the_calender_views[309] = good_habits_layout_circle_nov5;
            list_of_all_the_calender_views[310] = good_habits_layout_circle_nov6;
            list_of_all_the_calender_views[311] = good_habits_layout_circle_nov7;
            list_of_all_the_calender_views[312] = good_habits_layout_circle_nov8;
            list_of_all_the_calender_views[313] = good_habits_layout_circle_nov9;
            list_of_all_the_calender_views[314] = good_habits_layout_circle_nov10;
            list_of_all_the_calender_views[315] = good_habits_layout_circle_nov11;
            list_of_all_the_calender_views[316] = good_habits_layout_circle_nov12;
            list_of_all_the_calender_views[317] = good_habits_layout_circle_nov13;
            list_of_all_the_calender_views[318] = good_habits_layout_circle_nov14;
            list_of_all_the_calender_views[319] = good_habits_layout_circle_nov15;
            list_of_all_the_calender_views[320] = good_habits_layout_circle_nov16;
            list_of_all_the_calender_views[321] = good_habits_layout_circle_nov17;
            list_of_all_the_calender_views[322] = good_habits_layout_circle_nov18;
            list_of_all_the_calender_views[323] = good_habits_layout_circle_nov19;
            list_of_all_the_calender_views[324] = good_habits_layout_circle_nov20;
            list_of_all_the_calender_views[325] = good_habits_layout_circle_nov21;
            list_of_all_the_calender_views[326] = good_habits_layout_circle_nov22;
            list_of_all_the_calender_views[327] = good_habits_layout_circle_nov23;
            list_of_all_the_calender_views[328] = good_habits_layout_circle_nov24;
            list_of_all_the_calender_views[329] = good_habits_layout_circle_nov25;
            list_of_all_the_calender_views[330] = good_habits_layout_circle_nov26;
            list_of_all_the_calender_views[331] = good_habits_layout_circle_nov27;
            list_of_all_the_calender_views[332] = good_habits_layout_circle_nov28;
            list_of_all_the_calender_views[333] = good_habits_layout_circle_nov29;
            list_of_all_the_calender_views[334] = good_habits_layout_circle_nov30;
            list_of_all_the_calender_views[335] = good_habits_layout_circle_dec1;
            list_of_all_the_calender_views[336] = good_habits_layout_circle_dec2;
            list_of_all_the_calender_views[337] = good_habits_layout_circle_dec3;
            list_of_all_the_calender_views[338] = good_habits_layout_circle_dec4;
            list_of_all_the_calender_views[339] = good_habits_layout_circle_dec5;
            list_of_all_the_calender_views[340] = good_habits_layout_circle_dec6;
            list_of_all_the_calender_views[341] = good_habits_layout_circle_dec7;
            list_of_all_the_calender_views[342] = good_habits_layout_circle_dec8;
            list_of_all_the_calender_views[343] = good_habits_layout_circle_dec9;
            list_of_all_the_calender_views[344] = good_habits_layout_circle_dec10;
            list_of_all_the_calender_views[345] = good_habits_layout_circle_dec11;
            list_of_all_the_calender_views[346] = good_habits_layout_circle_dec12;
            list_of_all_the_calender_views[347] = good_habits_layout_circle_dec13;
            list_of_all_the_calender_views[348] = good_habits_layout_circle_dec14;
            list_of_all_the_calender_views[349] = good_habits_layout_circle_dec15;
            list_of_all_the_calender_views[350] = good_habits_layout_circle_dec16;
            list_of_all_the_calender_views[351] = good_habits_layout_circle_dec17;
            list_of_all_the_calender_views[352] = good_habits_layout_circle_dec18;
            list_of_all_the_calender_views[353] = good_habits_layout_circle_dec19;
            list_of_all_the_calender_views[354] = good_habits_layout_circle_dec20;
            list_of_all_the_calender_views[355] = good_habits_layout_circle_dec21;
            list_of_all_the_calender_views[356] = good_habits_layout_circle_dec22;
            list_of_all_the_calender_views[357] = good_habits_layout_circle_dec23;
            list_of_all_the_calender_views[358] = good_habits_layout_circle_dec24;
            list_of_all_the_calender_views[359] = good_habits_layout_circle_dec25;
            list_of_all_the_calender_views[360] = good_habits_layout_circle_dec26;
            list_of_all_the_calender_views[361] = good_habits_layout_circle_dec27;
            list_of_all_the_calender_views[362] = good_habits_layout_circle_dec28;
            list_of_all_the_calender_views[363] = good_habits_layout_circle_dec29;
            list_of_all_the_calender_views[364] = good_habits_layout_circle_dec30;
            list_of_all_the_calender_views[365] = good_habits_layout_circle_dec31;
        }
    }

    private void forward_and_back_button_listen() {
        if (getView() != null) {
            Button button_shadow_for_the_back_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.button_shadow_for_the_back_for_good_habits_for_the_full_year_view);
            Button button_shadow_for_the_front_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.button_shadow_for_the_front_for_good_habits_for_the_full_year_view);
            final TextView text_saying_which_year_to_show_in_a_good_habits_year = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year);
            button_shadow_for_the_back_for_good_habits_for_the_full_year_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    text_saying_which_year_to_show_in_a_good_habits_year.setText(String.valueOf(Integer.parseInt(text_saying_which_year_to_show_in_a_good_habits_year.getText().toString()) - 1));
                    set_the_buttons();
                    set_the_leap_year();
                    restart_all_the_year_values();
                    put_values_into_year_in_good_habits();
                }
            });
            button_shadow_for_the_front_for_good_habits_for_the_full_year_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    text_saying_which_year_to_show_in_a_good_habits_year.setText(String.valueOf(Integer.parseInt(text_saying_which_year_to_show_in_a_good_habits_year.getText().toString()) + 1));
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
            TextView text_saying_which_year_to_show_in_a_good_habits_year = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year);
            View good_habits_layout_circle_feb29 = getView().findViewById(R.id.good_habits_layout_circle_feb29);
            View backward_button_over_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.backward_button_over_for_good_habits_for_the_full_year_view);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(text_saying_which_year_to_show_in_a_good_habits_year.getText().toString()));
            if (calendar.getActualMaximum(Calendar.DAY_OF_YEAR) > 365) {
                if (backward_button_over_for_good_habits_for_the_full_year_view.getVisibility() == View.VISIBLE) {
                    good_habits_layout_circle_feb29.setVisibility(View.VISIBLE);
                } else {
                    calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
                    calendar.set(Calendar.DAY_OF_MONTH, 29);
                    if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) >= Simplify_the_time.return_time_in_midnight(start_date)) {
                        good_habits_layout_circle_feb29.setVisibility(View.VISIBLE);
                    } else {
                        good_habits_layout_circle_feb29.setVisibility(View.INVISIBLE);
                    }
                }
            } else {
                good_habits_layout_circle_feb29.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void put_values_into_year_in_good_habits() {
        if (getView() != null && getContext() != null) {
            TextView text_saying_which_year_to_show_in_a_good_habits_year = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year);
            Button this_yearly_data_is_only_available_for_pro_users_pie_chart_view_home = getView().findViewById(R.id.this_yearly_data_is_only_available_for_pro_users_pie_chart_view_home);
            Am_i_paid am_i_paid = new Am_i_paid(getContext());
            if (am_i_paid.did_user_pay()) {
                this_yearly_data_is_only_available_for_pro_users_pie_chart_view_home.setVisibility(View.INVISIBLE);
                int year_from_text = Integer.parseInt(text_saying_which_year_to_show_in_a_good_habits_year.getText().toString());
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year_from_text);
                Drawable back_ground_for_a_year_in_habits_yes = ContextCompat.getDrawable(getContext(), R.drawable.back_ground_for_a_year_in_habits_yes);
                Drawable back_ground_for_a_year_in_habits_no = ContextCompat.getDrawable(getContext(), R.drawable.back_ground_for_a_year_in_habits_no);
                if (is_this_a_leap_year(year_from_text)) {
                    if (past_current_future_for_the_full_year().equals("past")) {
                        for (int i = 366; i >= 1; i--) {
                            calendar.set(Calendar.DAY_OF_YEAR, i);
                            if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) < Simplify_the_time.return_time_in_midnight(start_date)) {
                                break;
                            }
                            list_of_all_the_calender_views[i - 1].setVisibility(View.VISIBLE);
                            if (return_state_of_day(calendar.getTimeInMillis())) {
                                list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_yes);
                            } else {
                                list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_no);
                            }
                        }
                    } else if (past_current_future_for_the_full_year().equals("current")) {
                        Calendar calendar_get_the_today = Calendar.getInstance();
                        for (int i = calendar_get_the_today.get(Calendar.DAY_OF_YEAR); i >= 1; i--) {
                            calendar.set(Calendar.DAY_OF_YEAR, i);
                            if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) < Simplify_the_time.return_time_in_midnight(start_date)) {
                                break;
                            }
                            list_of_all_the_calender_views[i - 1].setVisibility(View.VISIBLE);
                            if (return_state_of_day(calendar.getTimeInMillis())) {
                                list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_yes);
                            } else {
                                list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_no);
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
                                list_of_all_the_calender_views[i].setVisibility(View.VISIBLE);
                                if (return_state_of_day(calendar.getTimeInMillis())) {
                                    list_of_all_the_calender_views[i].setBackground(back_ground_for_a_year_in_habits_yes);
                                } else {
                                    list_of_all_the_calender_views[i].setBackground(back_ground_for_a_year_in_habits_no);
                                }
                            } else {
                                list_of_all_the_calender_views[i - 1].setVisibility(View.VISIBLE);
                                if (return_state_of_day(calendar.getTimeInMillis())) {
                                    list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_yes);
                                } else {
                                    list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_no);
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
                                list_of_all_the_calender_views[i].setVisibility(View.VISIBLE);
                                if (return_state_of_day(calendar.getTimeInMillis())) {
                                    list_of_all_the_calender_views[i].setBackground(back_ground_for_a_year_in_habits_yes);
                                } else {
                                    list_of_all_the_calender_views[i].setBackground(back_ground_for_a_year_in_habits_no);
                                }
                            } else {
                                list_of_all_the_calender_views[i - 1].setVisibility(View.VISIBLE);
                                if (return_state_of_day(calendar.getTimeInMillis())) {
                                    list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_yes);
                                } else {
                                    list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_no);
                                }
                            }
                        }
                    }
                }
            } else {
                this_yearly_data_is_only_available_for_pro_users_pie_chart_view_home.setVisibility(View.VISIBLE);
                this_yearly_data_is_only_available_for_pro_users_pie_chart_view_home.setBackgroundTintList(ColorStateList.valueOf(color));
                int year_from_text = Integer.parseInt(text_saying_which_year_to_show_in_a_good_habits_year.getText().toString());
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year_from_text);
                Drawable back_ground_for_a_year_in_habits_yes = ContextCompat.getDrawable(getContext(), R.drawable.back_ground_for_a_year_in_habits_yes);
                Drawable back_ground_for_a_year_in_habits_no = ContextCompat.getDrawable(getContext(), R.drawable.back_ground_for_a_year_in_habits_no);
                Random random = new Random();
                if (is_this_a_leap_year(year_from_text)) {
                    for (int i = 366; i >= 1; i--) {
                        int random_number = random.nextInt(2);
                        calendar.set(Calendar.DAY_OF_YEAR, i);
                        list_of_all_the_calender_views[i - 1].setVisibility(View.VISIBLE);
                        if (random_number == 0) {
                            list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_yes);
                        } else {
                            list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_no);
                        }
                    }
                } else {
                    for (int i = 365; i >= 1; i--) {
                        calendar.set(Calendar.DAY_OF_YEAR, i);
                        int random_number = random.nextInt(2);
                        if (i > 59) {
                            list_of_all_the_calender_views[i].setVisibility(View.VISIBLE);
                            if (random_number == 0) {
                                list_of_all_the_calender_views[i].setBackground(back_ground_for_a_year_in_habits_yes);
                            } else {
                                list_of_all_the_calender_views[i].setBackground(back_ground_for_a_year_in_habits_no);
                            }
                        } else {
                            list_of_all_the_calender_views[i - 1].setVisibility(View.VISIBLE);
                            if (random_number == 0) {
                                list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_yes);
                            } else {
                                list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_no);
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
        if (getContext() != null) {
            for (int i = 0; i < 366; i++) {
                list_of_all_the_calender_views[i].setVisibility(View.INVISIBLE);
            }
        }
    }

    private String past_current_future_for_the_full_year() {
        if (getView() != null) {
            TextView text_saying_which_year_to_show_in_a_good_habits_year = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year);
            int year_from_text = Integer.parseInt(text_saying_which_year_to_show_in_a_good_habits_year.getText().toString());
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
            Button button_shadow_for_the_back_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.button_shadow_for_the_back_for_good_habits_for_the_full_year_view);
            Button button_shadow_for_the_front_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.button_shadow_for_the_front_for_good_habits_for_the_full_year_view);
            View forward_button_over_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.forward_button_over_for_good_habits_for_the_full_year_view);
            View backward_button_over_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.backward_button_over_for_good_habits_for_the_full_year_view);
            TextView text_saying_which_year_to_show_in_a_good_habits_year = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year);
            int year_from_text = Integer.parseInt(text_saying_which_year_to_show_in_a_good_habits_year.getText().toString());
            Calendar calendar = Calendar.getInstance();
            int real_year = calendar.get(Calendar.YEAR);
            calendar.setTimeInMillis(start_date);
            int start_year = calendar.get(Calendar.YEAR);
            if (real_year == year_from_text) {
                forward_button_over_for_good_habits_for_the_full_year_view.setVisibility(View.INVISIBLE);
                button_shadow_for_the_front_for_good_habits_for_the_full_year_view.setVisibility(View.INVISIBLE);
            } else {
                forward_button_over_for_good_habits_for_the_full_year_view.setVisibility(View.VISIBLE);
                button_shadow_for_the_front_for_good_habits_for_the_full_year_view.setVisibility(View.VISIBLE);
            }
            if (start_year == year_from_text) {
                backward_button_over_for_good_habits_for_the_full_year_view.setVisibility(View.INVISIBLE);
                button_shadow_for_the_back_for_good_habits_for_the_full_year_view.setVisibility(View.INVISIBLE);
            } else {
                backward_button_over_for_good_habits_for_the_full_year_view.setVisibility(View.VISIBLE);
                button_shadow_for_the_back_for_good_habits_for_the_full_year_view.setVisibility(View.VISIBLE);
            }
        }
    }

    private void three_dots_to_delete_or_edit_at_top() {
        if (getView() != null) {
            final Button three_dots_button_in_top_in_habits = getView().findViewById(R.id.three_dots_button_in_top_in_habits);
            three_dots_button_in_top_in_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    show_popup_good_habits(three_dots_button_in_top_in_habits);
                }
            });
        }
    }

    public void show_popup_good_habits(View view) {
        if (getContext() != null) {
            PopupMenu popupMenu = new PopupMenu(getContext(), view);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.inflate(R.menu.popup_menu_habits);
            popupMenu.show();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.item_number_one_for_menu_edit_good_habits) {
            edit_has_been_clicked();
            return true;
        } else if (item.getItemId() == R.id.item_number_two_for_menu_delete_good_habits) {
            show_alert_dialog_for_delete();
            return true;
        } else {
            return false;
        }
    }

    private void edit_has_been_clicked() {
        edit_bad_habits new_fragment = new edit_bad_habits(value_for_position);
        View_home_habit old_fragment = (View_home_habit) getActivity().getSupportFragmentManager().findFragmentByTag("view home");
        if (old_fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, new_fragment, "edit bad habit").show(new_fragment).commit();
        }
    }

    private void delete_this_good_habit() {
       /* if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("list_of_the_bad_habits", Context.MODE_PRIVATE);
            String bad_habits = sharedPreferences.getString("Bad_habits", "");
            String[] split = bad_habits.split("spit_max_for_the_bad_habits");
            if (split.length == 1) {

            } else {
                String save_me = "";
                for (int i = 0; i < split.length; i++) {
                    if (i != value_for_position) {
                        save_me = save_me.concat(split[i]).concat("spit_max_for_the_bad_habits");
                    }
                }
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("Bad_habits", save_me);
                myEdit.commit();
                remove_the_framgent();
            }
        }*/
    }

    private void show_alert_dialog_for_delete() {
        if (getContext() != null) {
            new AlertDialog.Builder(getContext())
                    .setTitle("Delete habit")
                    .setMessage("Are you sure you want to permanently delete this habit?")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            delete_this_good_habit();
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.cancel, null)
                    .show();
        }
    }

    private void remove_the_framgent() {
        View_home_habit view_home_habit = (View_home_habit) getActivity().getSupportFragmentManager().findFragmentByTag("view home");
        home_fragment home_fragment = (com.easyhabitsapp.android.home_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("home");
        if (view_home_habit != null) {
            if (getActivity() != null && home_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().show(home_fragment).remove(view_home_habit).commit();
            }
        }
    }

    private void watch_all_the_share_button() {
        if (getView() != null) {
            final Button button_too_share_calender_in_good_habits = getView().findViewById(R.id.button_too_share_calender_in_good_habits);
            final Button button_to_share_the_best_average_and_current_streak = getView().findViewById(R.id.button_to_share_the_best_average_and_current_streak);
            final Button button_to_share_goal_progress_good_habits = getView().findViewById(R.id.button_to_share_goal_progress_good_habits);
            final Button button_to_share_bar_chart = getView().findViewById(R.id.button_to_share_bar_chart);
            final Button button_to_share_pie_chart = getView().findViewById(R.id.button_to_share_pie_chart);
            final Button button_to_share_line_chart_of_various_streaks = getView().findViewById(R.id.button_to_share_line_chart_of_various_streaks);
            final Button sahre_button_for_the_foour_values_in_good_habits = getView().findViewById(R.id.sahre_button_for_the_foour_values_in_good_habits);
            final Button share_button_to_share_the_whole_year_in_the_good_habits = getView().findViewById(R.id.share_button_to_share_the_whole_year_in_the_good_habits);
            final Button button_to_share_streak_in_good_habits = getView().findViewById(R.id.button_to_share_streak_in_good_habits);
            final CardView card_to_show_calender = getView().findViewById(R.id.card_to_show_calender);
            final CardView card_to_show_current_average_and_best_streak = getView().findViewById(R.id.card_to_show_current_average_and_best_streak);
            final CardView card_to_show_goal_progress_good_habits = getView().findViewById(R.id.card_to_show_goal_progress_good_habits);
            final CardView card_to_show_daily_input_by_week_of_days = getView().findViewById(R.id.card_to_show_daily_input_by_week_of_days);
            final CardView card_to_show_pie_chart_for_yes_or_no_over_the_days = getView().findViewById(R.id.card_to_show_pie_chart_for_yes_or_no_over_the_days);
            final CardView card_view_showing_multiple_streak_length = getView().findViewById(R.id.card_view_showing_multiple_streak_length);
            final CardView card_showingthe_four_values_day_week_month = getView().findViewById(R.id.card_showingthe_four_values_day_week_month);
            final CardView card_showing_the_habit_in_year_in_good_habits = getView().findViewById(R.id.card_showing_the_habit_in_year_in_good_habits);
            final CardView layout_to_say_streak_of_bad_habit = getView().findViewById(R.id.layout_to_say_streak_of_bad_habit);
            final Button button_saying_yes_under_calender_in_good_habits = getView().findViewById(R.id.button_saying_yes_under_calender_in_good_habits);
            button_too_share_calender_in_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (button_saying_yes_under_calender_in_good_habits.getVisibility() == View.VISIBLE) {
                        button_too_share_calender_in_good_habits.setVisibility(View.GONE);
                        hide_or_un_hide_the_button(0);
                        final ViewTreeObserver observer = button_saying_yes_under_calender_in_good_habits.getViewTreeObserver();
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
            button_to_share_the_best_average_and_current_streak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button_to_share_the_best_average_and_current_streak.setVisibility(View.INVISIBLE);
                    share_screen_shot(screenShot(card_to_show_current_average_and_best_streak));
                    button_to_share_the_best_average_and_current_streak.setVisibility(View.VISIBLE);
                }
            });
            button_to_share_goal_progress_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button_to_share_goal_progress_good_habits.setVisibility(View.INVISIBLE);
                    share_screen_shot(screenShot(card_to_show_goal_progress_good_habits));
                    button_to_share_goal_progress_good_habits.setVisibility(View.VISIBLE);
                }
            });
            button_to_share_bar_chart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button_to_share_bar_chart.setVisibility(View.INVISIBLE);
                    share_screen_shot(screenShot(card_to_show_daily_input_by_week_of_days));
                    button_to_share_bar_chart.setVisibility(View.VISIBLE);
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
            button_to_share_line_chart_of_various_streaks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button_to_share_line_chart_of_various_streaks.setVisibility(View.INVISIBLE);
                    share_screen_shot(screenShot(card_view_showing_multiple_streak_length));
                    button_to_share_line_chart_of_various_streaks.setVisibility(View.VISIBLE);
                }
            });
            sahre_button_for_the_foour_values_in_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sahre_button_for_the_foour_values_in_good_habits.setVisibility(View.INVISIBLE);
                    share_screen_shot(screenShot(card_showingthe_four_values_day_week_month));
                    sahre_button_for_the_foour_values_in_good_habits.setVisibility(View.VISIBLE);
                }
            });
            share_button_to_share_the_whole_year_in_the_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    share_button_to_share_the_whole_year_in_the_good_habits.setVisibility(View.INVISIBLE);
                    share_screen_shot(screenShot(card_showing_the_habit_in_year_in_good_habits));
                    share_button_to_share_the_whole_year_in_the_good_habits.setVisibility(View.VISIBLE);
                }
            });
            button_to_share_streak_in_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button_to_share_streak_in_good_habits.setVisibility(View.INVISIBLE);
                    share_screen_shot(screenShot(layout_to_say_streak_of_bad_habit));
                    button_to_share_streak_in_good_habits.setVisibility(View.VISIBLE);
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

    private void make_the_views_transparent() {
        if (getView() != null) {
            TextView text_for_title_in_the_card_to_show_pie = getView().findViewById(R.id.text_for_title_in_the_card_to_show_pie);
            PieChart chart_to_show_pie_of_yes_or_no_pie = getView().findViewById(R.id.chart_to_show_pie_of_yes_or_no_pie);
            TextView text_view_saying_that_there_is_not_enough_data_to_draw_this_chart = getView().findViewById(R.id.text_view_saying_that_there_is_not_enough_data_to_draw_this_chart);
            Button button_to_share_pie_chart = getView().findViewById(R.id.button_to_share_pie_chart);

            TextView title_for_various_streak_length_chart_inside_card = getView().findViewById(R.id.title_for_various_streak_length_chart_inside_card);
            LineChart cahrt_showing_various_chart_length_for_different_streaks = getView().findViewById(R.id.cahrt_showing_various_chart_length_for_different_streaks);
            TextView text_saying_thet_there_is_not_enough_data_to_draw_various_streaks = getView().findViewById(R.id.text_saying_thet_there_is_not_enough_data_to_draw_various_streaks);
            Button button_to_share_line_chart_of_various_streaks = getView().findViewById(R.id.button_to_share_line_chart_of_various_streaks);

            TextView title_for_values_inside_card_layout = getView().findViewById(R.id.title_for_values_inside_card_layout);
            TextView not_enough_data_for_chart_showing_the_four_values = getView().findViewById(R.id.not_enough_data_for_chart_showing_the_four_values);
            TextView text_saying_the_how_many_times_for_this_week = getView().findViewById(R.id.text_saying_the_how_many_times_for_this_week);
            TextView text_saying_the_how_many_times_for_this_week_actual = getView().findViewById(R.id.text_saying_the_how_many_times_for_this_week_actual);
            View line_between_week_and_month_good_habtis = getView().findViewById(R.id.line_between_week_and_month_good_habtis);
            TextView text_saying_the_how_many_times_for_this_month = getView().findViewById(R.id.text_saying_the_how_many_times_for_this_month);
            TextView text_saying_the_how_many_times_for_this_month_actual = getView().findViewById(R.id.text_saying_the_how_many_times_for_this_month_actual);
            View line_between_month_and_year_good_habtis = getView().findViewById(R.id.line_between_month_and_year_good_habtis);
            TextView text_saying_the_how_many_times_for_this_year = getView().findViewById(R.id.text_saying_the_how_many_times_for_this_year);
            TextView text_saying_the_how_many_times_for_this_year_actual = getView().findViewById(R.id.text_saying_the_how_many_times_for_this_year_actual);
            View line_between_year_and_all_time_good_habtis = getView().findViewById(R.id.line_between_year_and_all_time_good_habtis);
            TextView text_saying_the_how_many_times_for_this_all_time = getView().findViewById(R.id.text_saying_the_how_many_times_for_this_all_time);
            TextView text_saying_the_how_many_times_for_this_all_time_actual = getView().findViewById(R.id.text_saying_the_how_many_times_for_this_all_time_actual);
            View line_after_all_time = getView().findViewById(R.id.line_after_all_time);
            Button sahre_button_for_the_foour_values_in_good_habits = getView().findViewById(R.id.sahre_button_for_the_foour_values_in_good_habits);

            TextView title_saying_the_habit_name_in_year_for_good_habits = getView().findViewById(R.id.title_saying_the_habit_name_in_year_for_good_habits);
            TextView text_saying_which_year_to_show_in_a_good_habits_year = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year);
            Button button_shadow_for_the_back_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.button_shadow_for_the_back_for_good_habits_for_the_full_year_view);
            Button button_shadow_for_the_front_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.button_shadow_for_the_front_for_good_habits_for_the_full_year_view);
            View forward_button_over_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.forward_button_over_for_good_habits_for_the_full_year_view);
            View backward_button_over_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.backward_button_over_for_good_habits_for_the_full_year_view);
            ConstraintLayout layout_to_show_data_about_the_year_calender = getView().findViewById(R.id.layout_to_show_data_about_the_year_calender);
            Button share_button_to_share_the_whole_year_in_the_good_habits = getView().findViewById(R.id.share_button_to_share_the_whole_year_in_the_good_habits);

            Am_i_paid am_i_paid = new Am_i_paid(getContext());
            if (!am_i_paid.did_user_pay()) {
                text_for_title_in_the_card_to_show_pie.setAlpha(0.2f);
                chart_to_show_pie_of_yes_or_no_pie.setAlpha(0.2f);
                text_view_saying_that_there_is_not_enough_data_to_draw_this_chart.setAlpha(0.2f);
                button_to_share_pie_chart.setAlpha(0.2f);

                title_for_various_streak_length_chart_inside_card.setAlpha(0.2f);
                cahrt_showing_various_chart_length_for_different_streaks.setAlpha(0.2f);
                text_saying_thet_there_is_not_enough_data_to_draw_various_streaks.setAlpha(0.2f);
                button_to_share_line_chart_of_various_streaks.setAlpha(0.2f);

                title_for_values_inside_card_layout.setAlpha(0.2f);
                not_enough_data_for_chart_showing_the_four_values.setAlpha(0.2f);
                text_saying_the_how_many_times_for_this_week.setAlpha(0.2f);
                text_saying_the_how_many_times_for_this_week_actual.setAlpha(0.2f);
                line_between_week_and_month_good_habtis.setAlpha(0.2f);
                text_saying_the_how_many_times_for_this_month.setAlpha(0.2f);
                text_saying_the_how_many_times_for_this_month_actual.setAlpha(0.2f);
                line_between_month_and_year_good_habtis.setAlpha(0.2f);
                text_saying_the_how_many_times_for_this_year.setAlpha(0.2f);
                text_saying_the_how_many_times_for_this_year_actual.setAlpha(0.2f);
                line_between_year_and_all_time_good_habtis.setAlpha(0.2f);
                text_saying_the_how_many_times_for_this_all_time.setAlpha(0.2f);
                text_saying_the_how_many_times_for_this_all_time_actual.setAlpha(0.2f);
                line_after_all_time.setAlpha(0.2f);
                sahre_button_for_the_foour_values_in_good_habits.setAlpha(0.2f);
                title_saying_the_habit_name_in_year_for_good_habits.setAlpha(0.2f);
                text_saying_which_year_to_show_in_a_good_habits_year.setAlpha(0.2f);
                button_shadow_for_the_back_for_good_habits_for_the_full_year_view.setAlpha(0.2f);
                button_shadow_for_the_front_for_good_habits_for_the_full_year_view.setAlpha(0.2f);
                forward_button_over_for_good_habits_for_the_full_year_view.setAlpha(0.2f);
                backward_button_over_for_good_habits_for_the_full_year_view.setAlpha(0.2f);
                layout_to_show_data_about_the_year_calender.setAlpha(0.2f);
                share_button_to_share_the_whole_year_in_the_good_habits.setAlpha(0.2f);
            } else {
                text_for_title_in_the_card_to_show_pie.setAlpha(1f);
                chart_to_show_pie_of_yes_or_no_pie.setAlpha(1f);
                text_view_saying_that_there_is_not_enough_data_to_draw_this_chart.setAlpha(1f);
                button_to_share_pie_chart.setAlpha(1f);
                title_for_various_streak_length_chart_inside_card.setAlpha(1f);
                cahrt_showing_various_chart_length_for_different_streaks.setAlpha(1f);
                text_saying_thet_there_is_not_enough_data_to_draw_various_streaks.setAlpha(1f);
                button_to_share_line_chart_of_various_streaks.setAlpha(1f);
                title_for_values_inside_card_layout.setAlpha(1f);
                not_enough_data_for_chart_showing_the_four_values.setAlpha(1f);
                text_saying_the_how_many_times_for_this_week.setAlpha(1f);
                text_saying_the_how_many_times_for_this_week_actual.setAlpha(1f);
                line_between_week_and_month_good_habtis.setAlpha(1f);
                text_saying_the_how_many_times_for_this_month.setAlpha(1f);
                text_saying_the_how_many_times_for_this_month_actual.setAlpha(1f);
                line_between_month_and_year_good_habtis.setAlpha(1f);
                text_saying_the_how_many_times_for_this_year.setAlpha(1f);
                text_saying_the_how_many_times_for_this_year_actual.setAlpha(1f);
                line_between_year_and_all_time_good_habtis.setAlpha(1f);
                text_saying_the_how_many_times_for_this_all_time.setAlpha(1f);
                text_saying_the_how_many_times_for_this_all_time_actual.setAlpha(1f);
                line_after_all_time.setAlpha(1f);
                sahre_button_for_the_foour_values_in_good_habits.setAlpha(1f);
                title_saying_the_habit_name_in_year_for_good_habits.setAlpha(1f);
                text_saying_which_year_to_show_in_a_good_habits_year.setAlpha(1f);
                button_shadow_for_the_back_for_good_habits_for_the_full_year_view.setAlpha(1f);
                button_shadow_for_the_front_for_good_habits_for_the_full_year_view.setAlpha(1f);
                forward_button_over_for_good_habits_for_the_full_year_view.setAlpha(1f);
                backward_button_over_for_good_habits_for_the_full_year_view.setAlpha(1f);
                layout_to_show_data_about_the_year_calender.setAlpha(1f);
                share_button_to_share_the_whole_year_in_the_good_habits.setAlpha(1f);
            }
        }
    }

    private void uy_premuim_multiple_button_listen() {
        if (getView() != null) {
            Button this_pie_chart_is_only_available_for_pro_users_pie_chart_view_home = getView().findViewById(R.id.this_pie_chart_is_only_available_for_pro_users_pie_chart_view_home);
            Button this_streak_chart_is_only_available_for_pro_users_pie_chart_view_home = getView().findViewById(R.id.this_streak_chart_is_only_available_for_pro_users_pie_chart_view_home);
            Button this_relapses_is_only_available_for_pro_users_pie_chart_view_home = getView().findViewById(R.id.this_relapses_is_only_available_for_pro_users_pie_chart_view_home);
            Button this_yearly_data_is_only_available_for_pro_users_pie_chart_view_home = getView().findViewById(R.id.this_yearly_data_is_only_available_for_pro_users_pie_chart_view_home);
            this_pie_chart_is_only_available_for_pro_users_pie_chart_view_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_buy_premium("pie");
                }
            });
            this_streak_chart_is_only_available_for_pro_users_pie_chart_view_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_buy_premium("streak");
                }
            });
            this_relapses_is_only_available_for_pro_users_pie_chart_view_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_buy_premium("relapses chart");
                }
            });
            this_yearly_data_is_only_available_for_pro_users_pie_chart_view_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_buy_premium("yearly chart");
                }
            });
        }
    }

    private void open_buy_premium(String which_chart) {
        Buy_premuim buy_premuim = new Buy_premuim("to view the ".concat(which_chart).concat(" chart."), true, "view home");
        View_home_habit old_fragment = (View_home_habit) getActivity().getSupportFragmentManager().findFragmentByTag("view home");
        if (old_fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
        }
    }

    private ArrayList<Long> return_relapses() {
        Room_database_habits room_database_habits = Room_database_habits.getInstance(getContext());
        List<habits_data_class> list = room_database_habits.dao_for_habits_data().getAll();
        habits_data_class habits_data_class = list.get(value_for_position);
        if (habits_data_class.getRelapse() == null) {
            return new ArrayList<>();
        } else {
            return habits_data_class.getRelapse();
        }
    }

    private HashMap<Long, Integer> return_relapse_amount() {
        Room_database_habits room_database_habits = Room_database_habits.getInstance(getContext());
        List<habits_data_class> list = room_database_habits.dao_for_habits_data().getAll();
        habits_data_class habits_data_class = list.get(value_for_position);
        if (habits_data_class.getRelapse_amount() == null) {
            return new HashMap<Long, Integer>();
        } else {
            return habits_data_class.getRelapse_amount();
        }
    }

    private void calculate_all_the_streaks() {
        each_streak_lengths = new ArrayList<>();
        ArrayList<Long> relapse = return_relapses();
        ArrayList<Long> filter_list = new ArrayList<>();
        ArrayList<Long> filter_list_at_midnight = new ArrayList<>();
        long time_minus_24 = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - TimeUnit.DAYS.toMillis(1);
        long time_today = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis());
        if (return_the_information_from_save(6).equals("bad")) {
            if (relapse.size() > 1) {
                {
                    long difference = Math.max(Simplify_the_time.return_time_in_midnight(relapse.get(0)) - Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2))), 0);
                    each_streak_lengths.add((int) TimeUnit.MILLISECONDS.toDays(difference));
                }
                for (int i = 1; i < relapse.size(); i++) {
                    long difference = Math.max(Simplify_the_time.return_time_in_midnight(relapse.get(i)) - Simplify_the_time.return_time_in_midnight(relapse.get(i - 1)) - TimeUnit.DAYS.toMillis(1), 0);
                    each_streak_lengths.add((int) TimeUnit.MILLISECONDS.toDays(difference));
                }
                long difference = Math.max(time_minus_24 - Simplify_the_time.return_time_in_midnight(relapse.get(relapse.size() - 1)), 0);
                each_streak_lengths.add((int) TimeUnit.MILLISECONDS.toDays(difference));
            } else if (relapse.size() == 1) {
                {
                    long difference = Math.max(Simplify_the_time.return_time_in_midnight(relapse.get(0)) - Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2))), 0);
                    each_streak_lengths.add((int) TimeUnit.MILLISECONDS.toDays(difference));
                }
                long difference = Math.max(time_minus_24 - Simplify_the_time.return_time_in_midnight(relapse.get(0)), 0);
                each_streak_lengths.add((int) TimeUnit.MILLISECONDS.toDays(difference));
            } else {
                long difference = Math.max(time_today - Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2))), 0);
                each_streak_lengths.add((int) TimeUnit.MILLISECONDS.toDays(difference));
            }
        } else {
            if (return_the_information_from_save(7).equals("yes/no")) {
                filter_list = return_relapses();
                if (return_the_information_from_save(8).equals("everyday")) {
                    long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                    long difference = Simplify_the_time.return_time_in_midnight(time_today) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                    Calendar calendar = Calendar.getInstance();
                    int sum = 0;
                    if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                        difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                    }
                    for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                        calendar.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                        int index = Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()));
                        if (index >= 0) {
                            sum++;
                        } else {
                            each_streak_lengths.add(sum);
                            sum = 0;
                        }
                    }
                    if (sum >= 0) {
                        each_streak_lengths.add(sum);
                    }
                } else if (return_the_information_from_save(8).equals("daysperweek")) {
                    long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                    long difference = Simplify_the_time.return_time_in_midnight(time_today) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                    Calendar calendar = Calendar.getInstance();
                    int sum = 0;
                    for (int i = 0; i < filter_list.size(); i++) {
                        filter_list_at_midnight.add(Simplify_the_time.return_time_in_midnight(filter_list.get(i)));
                    }
                    if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                        difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                    }
                    for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                        calendar.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                            if (return_the_information_from_save(10).contains("Mo")) {
                                if (filter_list_at_midnight.contains(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                    sum++;
                                } else {
                                    each_streak_lengths.add(sum);
                                    sum = 0;
                                }
                            } else {
                                sum++;
                            }
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                            if (return_the_information_from_save(10).contains("Tu")) {
                                if (filter_list_at_midnight.contains(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                    sum++;
                                } else {
                                    each_streak_lengths.add(sum);
                                    sum = 0;
                                }
                            } else {
                                sum++;
                            }
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                            if (return_the_information_from_save(10).contains("We")) {
                                if (filter_list_at_midnight.contains(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                    sum++;
                                } else {
                                    each_streak_lengths.add(sum);
                                    sum = 0;
                                }
                            } else {
                                sum++;
                            }
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                            if (return_the_information_from_save(10).contains("Th")) {
                                if (filter_list_at_midnight.contains(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                    sum++;
                                } else {
                                    each_streak_lengths.add(sum);
                                    sum = 0;
                                }
                            } else {
                                sum++;
                            }
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                            if (return_the_information_from_save(10).contains("Fr")) {
                                if (filter_list_at_midnight.contains(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                    sum++;
                                } else {
                                    each_streak_lengths.add(sum);
                                    sum = 0;
                                }
                            } else {
                                sum++;
                            }
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                            if (return_the_information_from_save(10).contains("Sa")) {
                                if (filter_list_at_midnight.contains(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                    sum++;
                                } else {
                                    each_streak_lengths.add(sum);
                                    sum = 0;
                                }
                            } else {
                                sum++;
                            }
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                            if (return_the_information_from_save(10).contains("Su")) {
                                if (filter_list_at_midnight.contains(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                    sum++;
                                } else {
                                    each_streak_lengths.add(sum);
                                    sum = 0;
                                }
                            } else {
                                sum++;
                            }
                        }
                    }
                    if (sum >= 0) {
                        each_streak_lengths.add(sum);
                    }
                } else if (return_the_information_from_save(8).equals("everyndays")) {
                    long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                    long difference = Simplify_the_time.return_time_in_midnight(time_today) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                    Calendar calendar = Calendar.getInstance();
                    int sum = 0;
                    for (int i = 0; i < filter_list.size(); i++) {
                        filter_list_at_midnight.add(Simplify_the_time.return_time_in_midnight(filter_list.get(i)));
                    }
                    if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                        difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                    }
                    for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                        calendar.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                        int index = Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()));
                        if (index >= 0) {
                            sum++;
                        } else {
                            if (history_of_relapse.size() > 0) {
                                index = (-index) - 2;
                                if (index < 0) {
                                    each_streak_lengths.add(sum);
                                    sum = 0;
                                } else {
                                    long last_relapse = history_of_relapse.get(index);
                                    if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) - Simplify_the_time.return_time_in_midnight(last_relapse) >= Long.parseLong(return_the_information_from_save(10)) * TimeUnit.DAYS.toMillis(1)) {
                                        each_streak_lengths.add(sum);
                                        sum = 0;
                                    } else {
                                        sum++;
                                    }
                                }
                            } else {
                                each_streak_lengths.add(sum);
                                sum = 0;
                            }
                        }
                    }
                    if (sum >= 0) {
                        each_streak_lengths.add(sum);
                    }
                } else if (return_the_information_from_save(8).equals("dayspermonth")) {
                    Calendar calender = Calendar.getInstance();
                    long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                    long difference = Simplify_the_time.return_time_in_midnight(time_today) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                    int sum = 0;
                    for (int i = 0; i < filter_list.size(); i++) {
                        filter_list_at_midnight.add(Simplify_the_time.return_time_in_midnight(filter_list.get(i)));
                    }
                    if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                        difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                    }
                    for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                        calender.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                        int day_of_the_month = calender.get(Calendar.DAY_OF_MONTH);
                        if (!days_of_months_good_habit.contains(day_of_the_month)) {
                            sum++;
                        } else {
                            int index = Collections.binarySearch(history_of_relapse, Simplify_the_time.return_time_in_midnight(calender.getTimeInMillis()));
                            if (index >= 0) {
                                sum++;
                            } else {
                                each_streak_lengths.add(sum);
                                sum = 0;
                            }
                        }
                    }
                    if (sum >= 0) {
                        each_streak_lengths.add(sum);
                    }
                }
            } else if (return_the_information_from_save(7).equals("amount")) {
                if (return_the_information_from_save(8).equals("everyday")) {
                    long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                    long difference = Simplify_the_time.return_time_in_midnight(time_today) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                    Calendar calendar = Calendar.getInstance();
                    int sum = 0;
                    if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                        difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                    }
                    for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                        calendar.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                        if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()))) {
                            int value = hash_map_amount.get(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()));
                            if (value >= Integer.parseInt(return_the_information_from_save(9))) {
                                sum++;
                            } else {
                                each_streak_lengths.add(sum);
                                sum = 0;
                            }
                        } else {
                            each_streak_lengths.add(sum);
                            sum = 0;
                        }
                    }
                    if (sum >= 0) {
                        each_streak_lengths.add(sum);
                    }
                } else if (return_the_information_from_save(8).equals("daysperweek")) {
                    long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                    long difference = Simplify_the_time.return_time_in_midnight(time_today) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                    Calendar calendar = Calendar.getInstance();
                    int sum = 0;
                    if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                        difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                    }
                    for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                        calendar.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                            if (return_the_information_from_save(10).contains("Mo")) {
                                if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                    int value = hash_map_amount.get(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i))));
                                    if (value >= Integer.parseInt(return_the_information_from_save(9))) {
                                        sum++;
                                    } else {
                                        each_streak_lengths.add(sum);
                                        sum = 0;
                                    }
                                } else {
                                    each_streak_lengths.add(sum);
                                    sum = 0;
                                }
                            } else {
                                sum++;
                            }
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                            if (return_the_information_from_save(10).contains("Tu")) {
                                if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                    int value = hash_map_amount.get(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i))));
                                    if (value >= Integer.parseInt(return_the_information_from_save(9))) {
                                        sum++;
                                    } else {
                                        each_streak_lengths.add(sum);
                                        sum = 0;
                                    }
                                } else {
                                    each_streak_lengths.add(sum);
                                    sum = 0;
                                }
                            } else {
                                sum++;
                            }
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                            if (return_the_information_from_save(10).contains("We")) {
                                if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                    int value = hash_map_amount.get(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i))));
                                    if (value >= Integer.parseInt(return_the_information_from_save(9))) {
                                        sum++;
                                    } else {
                                        each_streak_lengths.add(sum);
                                        sum = 0;
                                    }
                                } else {
                                    each_streak_lengths.add(sum);
                                    sum = 0;
                                }
                            } else {
                                sum++;
                            }
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                            if (return_the_information_from_save(10).contains("Th")) {
                                if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                    int value = hash_map_amount.get(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i))));
                                    if (value >= Integer.parseInt(return_the_information_from_save(9))) {
                                        sum++;
                                    } else {
                                        each_streak_lengths.add(sum);
                                        sum = 0;
                                    }
                                } else {
                                    each_streak_lengths.add(sum);
                                    sum = 0;
                                }
                            } else {
                                sum++;
                            }
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                            if (return_the_information_from_save(10).contains("Fr")) {
                                if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                    int value = hash_map_amount.get(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i))));
                                    if (value >= Integer.parseInt(return_the_information_from_save(9))) {
                                        sum++;
                                    } else {
                                        each_streak_lengths.add(sum);
                                        sum = 0;
                                    }
                                } else {
                                    each_streak_lengths.add(sum);
                                    sum = 0;
                                }
                            } else {
                                sum++;
                            }
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                            if (return_the_information_from_save(10).contains("Sa")) {
                                if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                    int value = hash_map_amount.get(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i))));
                                    if (value >= Integer.parseInt(return_the_information_from_save(9))) {
                                        sum++;
                                    } else {
                                        each_streak_lengths.add(sum);
                                        sum = 0;
                                    }
                                } else {
                                    each_streak_lengths.add(sum);
                                    sum = 0;
                                }
                            } else {
                                sum++;
                            }
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                            if (return_the_information_from_save(10).contains("Su")) {
                                if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)))) {
                                    int value = hash_map_amount.get(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i))));
                                    if (value >= Integer.parseInt(return_the_information_from_save(9))) {
                                        sum++;
                                    } else {
                                        each_streak_lengths.add(sum);
                                        sum = 0;
                                    }
                                } else {
                                    each_streak_lengths.add(sum);
                                    sum = 0;
                                }
                            } else {
                                sum++;
                            }
                        }
                    }
                    if (sum >= 0) {
                        each_streak_lengths.add(sum);
                    }
                } else if (return_the_information_from_save(8).equals("everyndays")) {
                    long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                    long difference = Simplify_the_time.return_time_in_midnight(time_today) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                    Calendar calendar = Calendar.getInstance();
                    int sum = 0;
                    if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                        difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                    }
                    for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                        calendar.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                        if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis())) && is_amount_good_for_streak(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()))) {
                            sum++;
                        } else {
                            if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis())) && is_amount_good_for_streak(Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()))) {
                                sum++;
                            } else {
                                if (hash_map_amount.size() > 0) {
                                    long milli = Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis());
                                    for (int j = 1; j < Integer.parseInt(return_the_information_from_save(10)); j++) {
                                        if (hash_map_amount.containsKey(milli - TimeUnit.DAYS.toMillis(j)) && hash_map_amount.get(milli - TimeUnit.DAYS.toMillis(j)) >= Integer.parseInt(return_the_information_from_save(9))) {
                                            sum++;
                                        }
                                    }
                                    each_streak_lengths.add(sum);
                                    sum = 0;
                                } else {
                                    each_streak_lengths.add(sum);
                                    sum = 0;
                                }
                            }
                        }
                    }
                    if (sum >= 0) {
                        each_streak_lengths.add(sum);
                    }
                } else if (return_the_information_from_save(8).equals("dayspermonth")) {
                    Calendar calender = Calendar.getInstance();
                    long start_date = Simplify_the_time.return_time_in_midnight(Long.parseLong(return_the_information_from_save(2)));
                    long difference = Simplify_the_time.return_time_in_midnight(time_today) - Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(1);
                    int sum = 0;
                    if (difference % TimeUnit.DAYS.toMillis(1) != 0) {
                        difference = difference + (TimeUnit.DAYS.toMillis(1) - (difference % TimeUnit.DAYS.toMillis(1)));
                    }
                    for (int i = 0; i < TimeUnit.MILLISECONDS.toDays(difference); i++) {
                        calender.setTimeInMillis(Simplify_the_time.return_time_in_midnight(Simplify_the_time.return_time_in_midnight(start_date) + TimeUnit.DAYS.toMillis(i)));
                        int day_of_the_month = calender.get(Calendar.DAY_OF_MONTH);
                        if (!days_of_months_good_habit.contains(day_of_the_month)) {
                            sum++;
                        } else {
                            if (hash_map_amount.containsKey(Simplify_the_time.return_time_in_midnight(calender.getTimeInMillis())) && is_amount_good_for_streak(Simplify_the_time.return_time_in_midnight(calender.getTimeInMillis()))) {
                                sum++;
                            } else {
                                each_streak_lengths.add(sum);
                                sum = 0;
                            }
                        }
                    }
                    if (sum >= 0) {
                        each_streak_lengths.add(sum);
                    }
                }
            } else if (return_the_information_from_save(7).equals("timer")) {

            }
        }
        //each_streak_lengths.removeAll(Collections.singleton(0));
    }

    private boolean is_amount_good_for_streak(long time) {
        time = Simplify_the_time.return_time_in_midnight(time);
        if (hash_map_amount != null && hash_map_amount.containsKey(time)) {
            int amount_int = hash_map_amount.get(time);
            if (amount_int >= Integer.parseInt(return_the_information_from_save(9))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private HashMap<Long, Integer> return_amount() {
        Room_database_habits room_database_habits = Room_database_habits.getInstance(getContext());
        List<habits_data_class> list = room_database_habits.dao_for_habits_data().getAll();
        habits_data_class habits_data_class = list.get(value_for_position);
        return habits_data_class.getRelapse_amount_timer();
    }

    private void change_the_text_if_it_is_a_good_habit() {
        if (getView() != null) {
            TextView text_asking_did_you_relapse_in_share = getView().findViewById(R.id.text_asking_did_you_relapse_in_share);
            if (return_the_information_from_save(6).equals("good")) {
                if (return_the_information_from_save(7).equals("yes/no")) {
                    text_asking_did_you_relapse_in_share.setText("Did you complete this habit?");
                } else if (return_the_information_from_save(7).equals("amount")) {
                    text_asking_did_you_relapse_in_share.setText("How many times did you complete this habit?");
                } else if (return_the_information_from_save(7).equals("timer")) {
                    text_asking_did_you_relapse_in_share.setText("");
                }
            }
        }
    }

    private void change_the_text_above_realpse_graphs_if_it_is_a_good_habit() {
        if (getView() != null) {
            TextView text_title_of_weekly_daily_habit_in_card = getView().findViewById(R.id.text_title_of_weekly_daily_habit_in_card);
            if (return_the_information_from_save(6).equals("good")) {
                text_title_of_weekly_daily_habit_in_card.setText("Habit completion");
            }
        }
    }

    private void change_the_four_text_good_habit() {
        if (getView() != null) {
            TextView title_for_values_inside_card_layout = getView().findViewById(R.id.title_for_values_inside_card_layout);
            if (return_the_information_from_save(6).equals("good")) {
                title_for_values_inside_card_layout.setText("Habit completion");
            }
        }
    }

    private void add_the_days_per_month_to_the_calender() {
        if (return_the_information_from_save(6).equals("good") && return_the_information_from_save(8).equals("dayspermonth")) {
            if (days_of_months_good_habit == null) {
                days_of_months_good_habit = new ArrayList<>();
            }
            String[] split_numbers = return_the_information_from_save(10).split("_");
            for (int i = 0; i < split_numbers.length; i++) {
                days_of_months_good_habit.add(Integer.parseInt(split_numbers[i]));
            }
        }
    }

    private void plus_and_minus_for_amount() {
        if (getView() != null) {
            EditText how_many_times_did_you_do_this_habit_edit_text = getView().findViewById(R.id.how_many_times_did_you_do_this_habit_edit_text);
            Button negative_button_beside_amount_in_view_habit = getView().findViewById(R.id.negative_button_beside_amount_in_view_habit);
            Button positive_button_beside_amount_in_view_habit = getView().findViewById(R.id.positive_button_beside_amount_in_view_habit);
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            negative_button_beside_amount_in_view_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (how_many_times_did_you_do_this_habit_edit_text.getText().toString().equals("")) {
                        how_many_times_did_you_do_this_habit_edit_text.setText("0");
                    } else {
                        int how_many = Integer.parseInt(how_many_times_did_you_do_this_habit_edit_text.getText().toString());
                        if (how_many != 0) {
                            how_many = how_many - 1;
                            how_many_times_did_you_do_this_habit_edit_text.setText(String.valueOf(how_many));

                            String[] split_for_day_month_year = color_the_today.split("_");
                            String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                            int calender_day = Integer.parseInt(split_for_day_month_year[0]);
                            int calender_month = return_month_string_to_int(month_and_year[0]);
                            int calender_year = Integer.parseInt(month_and_year[1]);
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(calender_year, calender_month, calender_day);
                            save_the_input_for_good_habit_amount_input(how_many, calendar.getTimeInMillis());
                            put_all_the_relapses_into_a_array_list();
                            calculate_all_the_streaks();
                            color_the_calender();
                            set_up_day_of_week_bar_chart();
                            clear_all_the_unders();
                            divide_it_into_weeks();
                            clear_the_middle();
                            make_the_middle_come_again();
                            // draw_pie_chart();
                            // line_chart_for_streak.fitScreen();
                            //set_up_the_various_streak_chart();
                            // setup_the_four_information_card();
                            // set_the_leap_year();
                            //put_values_into_year_in_good_habits();
                            displaying_streak_for_user();
                            calculate_the_average_streak();
                            calculate_the_best_streak();
                            calculate_the_current_streak();
                            set_the_text_for_in_card();
                            draw_pie_chart();
                            set_up_the_various_streak_chart();
                            setup_the_four_information_card();
                            set_the_leap_year();
                            put_values_into_year_in_good_habits();
                            line_chart_for_streak.fitScreen();
                        }
                    }
                    if (how_many_times_did_you_do_this_habit_edit_text.hasFocus()) {
                        how_many_times_did_you_do_this_habit_edit_text.setSelection(how_many_times_did_you_do_this_habit_edit_text.getText().toString().length());
                    }
                }
            });
            positive_button_beside_amount_in_view_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (how_many_times_did_you_do_this_habit_edit_text.getText().toString().equals("")) {
                        how_many_times_did_you_do_this_habit_edit_text.setText("1");
                    } else {
                        int how_many = Integer.parseInt(how_many_times_did_you_do_this_habit_edit_text.getText().toString());
                        how_many = how_many + 1;
                        how_many_times_did_you_do_this_habit_edit_text.setText(String.valueOf(how_many));

                        String[] split_for_day_month_year = color_the_today.split("_");
                        String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                        int calender_day = Integer.parseInt(split_for_day_month_year[0]);
                        int calender_month = return_month_string_to_int(month_and_year[0]);
                        int calender_year = Integer.parseInt(month_and_year[1]);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(calender_year, calender_month, calender_day);
                        save_the_input_for_good_habit_amount_input(how_many, calendar.getTimeInMillis());
                        put_all_the_relapses_into_a_array_list();
                        calculate_all_the_streaks();
                        color_the_calender();
                        set_up_day_of_week_bar_chart();
                        clear_all_the_unders();
                        divide_it_into_weeks();
                        clear_the_middle();
                        make_the_middle_come_again();
                        // draw_pie_chart();
                        // line_chart_for_streak.fitScreen();
                        //set_up_the_various_streak_chart();
                        // setup_the_four_information_card();
                        // set_the_leap_year();
                        //put_values_into_year_in_good_habits();
                        displaying_streak_for_user();
                        calculate_the_average_streak();
                        calculate_the_best_streak();
                        calculate_the_current_streak();
                        set_the_text_for_in_card();
                        draw_pie_chart();
                        set_up_the_various_streak_chart();
                        setup_the_four_information_card();
                        set_the_leap_year();
                        put_values_into_year_in_good_habits();
                        line_chart_for_streak.fitScreen();
                    }
                    if (how_many_times_did_you_do_this_habit_edit_text.hasFocus()) {
                        how_many_times_did_you_do_this_habit_edit_text.setSelection(how_many_times_did_you_do_this_habit_edit_text.getText().toString().length());
                    }
                }
            });
        }
    }

    private void amount_text_watcher() {
        if (getView() != null) {
            EditText how_many_times_did_you_do_this_habit_edit_text = getView().findViewById(R.id.how_many_times_did_you_do_this_habit_edit_text);
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            how_many_times_did_you_do_this_habit_edit_text.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    int how_many;
                    if (how_many_times_did_you_do_this_habit_edit_text.getText().toString().equals("")) {
                        how_many = 0;
                    } else {
                        how_many = Integer.parseInt(how_many_times_did_you_do_this_habit_edit_text.getText().toString());
                    }
                    String[] split_for_day_month_year = color_the_today.split("_");
                    String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int calender_day = Integer.parseInt(split_for_day_month_year[0]);
                    int calender_month = return_month_string_to_int(month_and_year[0]);
                    int calender_year = Integer.parseInt(month_and_year[1]);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(calender_year, calender_month, calender_day);
                    save_the_input_for_good_habit_amount_input(how_many, calendar.getTimeInMillis());
                    put_all_the_relapses_into_a_array_list();
                    calculate_all_the_streaks();
                    color_the_calender();
                    set_up_day_of_week_bar_chart();
                    clear_all_the_unders();
                    divide_it_into_weeks();
                    clear_the_middle();
                    make_the_middle_come_again();
                    // draw_pie_chart();
                    // line_chart_for_streak.fitScreen();
                    //set_up_the_various_streak_chart();
                    // setup_the_four_information_card();
                    // set_the_leap_year();
                    //put_values_into_year_in_good_habits();
                    displaying_streak_for_user();
                    calculate_the_average_streak();
                    calculate_the_best_streak();
                    calculate_the_current_streak();
                    set_the_text_for_in_card();
                    draw_pie_chart();
                    set_up_the_various_streak_chart();
                    setup_the_four_information_card();
                    set_the_leap_year();
                    put_values_into_year_in_good_habits();
                    line_chart_for_streak.fitScreen();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }
}