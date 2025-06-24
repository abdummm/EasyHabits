package com.easyhabitsapp.android;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class stats_fragment extends Fragment {
    Button calender_button_showing_shadow_1;
    Button calender_button_showing_shadow_2;
    Button calender_button_showing_shadow_3;
    Button calender_button_showing_shadow_4;
    Button calender_button_showing_shadow_5;
    Button calender_button_showing_shadow_6;
    Button calender_button_showing_shadow_7;
    Button calender_button_showing_shadow_8;
    Button calender_button_showing_shadow_9;
    Button calender_button_showing_shadow_10;
    Button calender_button_showing_shadow_11;
    Button calender_button_showing_shadow_12;
    Button calender_button_showing_shadow_13;
    Button calender_button_showing_shadow_14;
    Button calender_button_showing_shadow_15;
    Button calender_button_showing_shadow_16;
    Button calender_button_showing_shadow_17;
    Button calender_button_showing_shadow_18;
    Button calender_button_showing_shadow_19;
    Button calender_button_showing_shadow_20;
    Button calender_button_showing_shadow_21;
    Button calender_button_showing_shadow_22;
    Button calender_button_showing_shadow_23;
    Button calender_button_showing_shadow_24;
    Button calender_button_showing_shadow_25;
    Button calender_button_showing_shadow_26;
    Button calender_button_showing_shadow_27;
    Button calender_button_showing_shadow_28;
    Button calender_button_showing_shadow_29;
    Button calender_button_showing_shadow_30;
    Button calender_button_showing_shadow_31;
    Button calender_button_showing_shadow_32;
    Button calender_button_showing_shadow_33;
    Button calender_button_showing_shadow_34;
    Button calender_button_showing_shadow_35;
    Button calender_button_showing_shadow_36;
    Button calender_button_showing_shadow_37;
    String color_the_today;
    Drawable first_part_rectangle_calender;
    Drawable middle_part_rectangle_calender;
    Drawable last_part_rectangle_calender;
    Drawable first_and_last_part_rectangle_calender;
    String what_day_is_today;
    private Drawable color_black;
    private Drawable color_blue;
    private Drawable color_brown;
    private Drawable color_cyan;
    private Drawable color_dark_green;
    private Drawable color_green;
    private Drawable color_lime;
    private Drawable color_magenta;
    private Drawable color_navy;
    private Drawable color_orange;
    private Drawable color_pink;
    private Drawable color_purple;
    private Drawable color_red;
    private Drawable color_yellow;
    private boolean should_i_refresh = true;

    public stats_fragment() {
        // Required empty public constructor
    }


    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stats_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        set_the_colors();
        define_the_buttons();
        set_the_first_day_of_the_week_number();
        set_the_days_on_the_real_text();
        back_and_front_button_listen();
        color_the_today_value();
        color_today();
        calender_button_listeners();
        remove_the_hiding_buttons();
        divide_it_into_weeks();
        hide_the_middle();
        clear_the_drawables();
        make_the_middle_come_again();
        add_and_show_button_listener();
        move_the_buttons();
        set_the_color_original();
        clear_all_the_events_circles();
        set_up_the_events();
        set_the_line_chart();
    }

    private void set_the_first_day_of_the_week_number() {
        if (getActivity() != null) {
            Calendar calender = Calendar.getInstance();
            TextView first_day_in_the_week_calender = getActivity().findViewById(R.id.first_day_in_the_week_calender);
            TextView second_day_in_the_week_calender = getActivity().findViewById(R.id.second_day_in_the_week_calender);
            TextView third_day_in_the_week_calender = getActivity().findViewById(R.id.third_day_in_the_week_calender);
            TextView fourth_day_in_the_week_calender = getActivity().findViewById(R.id.fourth_day_in_the_week_calender);
            TextView fifth_day_in_the_week_calender = getActivity().findViewById(R.id.fifth_day_in_the_week_calender);
            TextView sixth_day_in_the_week_calender = getActivity().findViewById(R.id.sixth_day_in_the_week_calender);
            TextView seventh_day_in_the_week_calender = getActivity().findViewById(R.id.seventh_day_in_the_week_calender);
            int year = calender.get(Calendar.YEAR);
            String month = return_month(calender.get(Calendar.MONTH));
            TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
            month_and_year_in_calender.setText(month.concat(" ").concat(String.valueOf(year)));
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
        if (getActivity() != null) {
            TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
            String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
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
        if (getActivity() != null) {
            TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
            Calendar calender_for_last_day_of_month_only = Calendar.getInstance();
            String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
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
        if (getActivity() != null) {
            TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
            String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
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
        if (getActivity() != null) {
            calender_button_showing_shadow_1 = getActivity().findViewById(R.id.calender_button_showing_shadow_1);
            calender_button_showing_shadow_2 = getActivity().findViewById(R.id.calender_button_showing_shadow_2);
            calender_button_showing_shadow_3 = getActivity().findViewById(R.id.calender_button_showing_shadow_3);
            calender_button_showing_shadow_4 = getActivity().findViewById(R.id.calender_button_showing_shadow_4);
            calender_button_showing_shadow_5 = getActivity().findViewById(R.id.calender_button_showing_shadow_5);
            calender_button_showing_shadow_6 = getActivity().findViewById(R.id.calender_button_showing_shadow_6);
            calender_button_showing_shadow_7 = getActivity().findViewById(R.id.calender_button_showing_shadow_7);
            calender_button_showing_shadow_8 = getActivity().findViewById(R.id.calender_button_showing_shadow_8);
            calender_button_showing_shadow_9 = getActivity().findViewById(R.id.calender_button_showing_shadow_9);
            calender_button_showing_shadow_10 = getActivity().findViewById(R.id.calender_button_showing_shadow_10);
            calender_button_showing_shadow_11 = getActivity().findViewById(R.id.calender_button_showing_shadow_11);
            calender_button_showing_shadow_12 = getActivity().findViewById(R.id.calender_button_showing_shadow_12);
            calender_button_showing_shadow_13 = getActivity().findViewById(R.id.calender_button_showing_shadow_13);
            calender_button_showing_shadow_14 = getActivity().findViewById(R.id.calender_button_showing_shadow_14);
            calender_button_showing_shadow_15 = getActivity().findViewById(R.id.calender_button_showing_shadow_15);
            calender_button_showing_shadow_16 = getActivity().findViewById(R.id.calender_button_showing_shadow_16);
            calender_button_showing_shadow_17 = getActivity().findViewById(R.id.calender_button_showing_shadow_17);
            calender_button_showing_shadow_18 = getActivity().findViewById(R.id.calender_button_showing_shadow_18);
            calender_button_showing_shadow_19 = getActivity().findViewById(R.id.calender_button_showing_shadow_19);
            calender_button_showing_shadow_20 = getActivity().findViewById(R.id.calender_button_showing_shadow_20);
            calender_button_showing_shadow_21 = getActivity().findViewById(R.id.calender_button_showing_shadow_21);
            calender_button_showing_shadow_22 = getActivity().findViewById(R.id.calender_button_showing_shadow_22);
            calender_button_showing_shadow_23 = getActivity().findViewById(R.id.calender_button_showing_shadow_23);
            calender_button_showing_shadow_24 = getActivity().findViewById(R.id.calender_button_showing_shadow_24);
            calender_button_showing_shadow_25 = getActivity().findViewById(R.id.calender_button_showing_shadow_25);
            calender_button_showing_shadow_26 = getActivity().findViewById(R.id.calender_button_showing_shadow_26);
            calender_button_showing_shadow_27 = getActivity().findViewById(R.id.calender_button_showing_shadow_27);
            calender_button_showing_shadow_28 = getActivity().findViewById(R.id.calender_button_showing_shadow_28);
            calender_button_showing_shadow_29 = getActivity().findViewById(R.id.calender_button_showing_shadow_29);
            calender_button_showing_shadow_30 = getActivity().findViewById(R.id.calender_button_showing_shadow_30);
            calender_button_showing_shadow_31 = getActivity().findViewById(R.id.calender_button_showing_shadow_31);
            calender_button_showing_shadow_32 = getActivity().findViewById(R.id.calender_button_showing_shadow_32);
            calender_button_showing_shadow_33 = getActivity().findViewById(R.id.calender_button_showing_shadow_33);
            calender_button_showing_shadow_34 = getActivity().findViewById(R.id.calender_button_showing_shadow_34);
            calender_button_showing_shadow_35 = getActivity().findViewById(R.id.calender_button_showing_shadow_35);
            calender_button_showing_shadow_36 = getActivity().findViewById(R.id.calender_button_showing_shadow_36);
            calender_button_showing_shadow_37 = getActivity().findViewById(R.id.calender_button_showing_shadow_37);
            first_part_rectangle_calender = getActivity().getDrawable(R.drawable.first_part_rectangle_calender);
            middle_part_rectangle_calender = getActivity().getDrawable(R.drawable.middle_part_rectangle_calender);
            last_part_rectangle_calender = getActivity().getDrawable(R.drawable.last_part_rectangle_calender);
            first_and_last_part_rectangle_calender = getActivity().getDrawable(R.drawable.first_and_last_part_rectangle_calender);
        }
    }

    private void back_and_front_button_listen() {
        if (getActivity() != null) {
            Button button_shadow_for_the_back = getActivity().findViewById(R.id.button_shadow_for_the_back);
            Button button_shadow_for_the_front = getActivity().findViewById(R.id.button_shadow_for_the_front);
            final TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
            button_shadow_for_the_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    if (return_month_string_to_int(splitter[0]) == 0) {
                        String month_name = return_month(11);
                        String year = String.valueOf(Integer.parseInt(splitter[1]) - 1);
                        month_and_year_in_calender.setText(month_name.concat(" ").concat(year));
                        clear_the_calender();
                        set_the_days_on_the_real_text();
                        clear_the_color_from_the_keyboard();
                        color_today();
                        set_all_buttons_to_visible();
                        remove_the_hiding_buttons();
                        color_the_day_which_is_today();
                        divide_it_into_weeks();
                        hide_the_middle();
                        clear_the_drawables();
                        make_the_middle_come_again();
                        move_the_buttons();
                        clear_all_the_events_circles();
                        set_up_the_events();
                    } else {
                        String month_name = return_month(return_month_string_to_int(splitter[0]) - 1);
                        month_and_year_in_calender.setText(month_name.concat(" ").concat(splitter[1]));
                        clear_the_calender();
                        set_the_days_on_the_real_text();
                        clear_the_color_from_the_keyboard();
                        color_today();
                        set_all_buttons_to_visible();
                        remove_the_hiding_buttons();
                        color_the_day_which_is_today();
                        divide_it_into_weeks();
                        hide_the_middle();
                        clear_the_drawables();
                        make_the_middle_come_again();
                        move_the_buttons();
                        clear_all_the_events_circles();
                        set_up_the_events();

                    }
                }
            });
            button_shadow_for_the_front.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    if (return_month_string_to_int((splitter[0])) == 11) {
                        String month_name = return_month(0);
                        String year = String.valueOf(Integer.parseInt(splitter[1]) + 1);
                        month_and_year_in_calender.setText(month_name.concat(" ").concat(year));
                        clear_the_calender();
                        set_the_days_on_the_real_text();
                        clear_the_color_from_the_keyboard();
                        color_today();
                        set_all_buttons_to_visible();
                        remove_the_hiding_buttons();
                        color_the_day_which_is_today();
                        divide_it_into_weeks();
                        hide_the_middle();
                        clear_the_drawables();
                        make_the_middle_come_again();
                        move_the_buttons();
                        clear_all_the_events_circles();
                        set_up_the_events();
                    } else {
                        String month_name = return_month(return_month_string_to_int(splitter[0]) + 1);
                        month_and_year_in_calender.setText(month_name.concat(" ").concat(splitter[1]));
                        clear_the_calender();
                        set_the_days_on_the_real_text();
                        clear_the_color_from_the_keyboard();
                        color_today();
                        set_all_buttons_to_visible();
                        remove_the_hiding_buttons();
                        color_the_day_which_is_today();
                        divide_it_into_weeks();
                        hide_the_middle();
                        clear_the_drawables();
                        make_the_middle_come_again();
                        move_the_buttons();
                        clear_all_the_events_circles();
                        set_up_the_events();
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

    private void color_today() {
        if (getActivity() != null) {
            TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
            String[] text_to_split = month_and_year_in_calender.getText().toString().split(" ");
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
                clear_the_color_from_the_keyboard();
                color_the_selected(1);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_1.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_1.getText().toString()));
            }
        });
        calender_button_showing_shadow_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(2);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_2.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_2.getText().toString()));
            }
        });
        calender_button_showing_shadow_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(3);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_3.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_3.getText().toString()));
            }
        });
        calender_button_showing_shadow_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(4);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_4.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_4.getText().toString()));
            }
        });
        calender_button_showing_shadow_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(5);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_5.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_5.getText().toString()));
            }
        });
        calender_button_showing_shadow_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(6);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_6.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_6.getText().toString()));
            }
        });
        calender_button_showing_shadow_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(7);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_7.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_7.getText().toString()));
            }
        });
        calender_button_showing_shadow_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(8);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_8.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_8.getText().toString()));
            }
        });
        calender_button_showing_shadow_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(9);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_9.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_9.getText().toString()));
            }
        });
        calender_button_showing_shadow_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(10);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_10.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_10.getText().toString()));
            }
        });
        calender_button_showing_shadow_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(11);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_11.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_11.getText().toString()));
            }
        });
        calender_button_showing_shadow_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(12);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_12.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_12.getText().toString()));
            }
        });
        calender_button_showing_shadow_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(13);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_13.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_13.getText().toString()));
            }
        });
        calender_button_showing_shadow_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(14);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_14.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_14.getText().toString()));
            }
        });
        calender_button_showing_shadow_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(15);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_15.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_15.getText().toString()));
            }
        });
        calender_button_showing_shadow_16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(16);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_16.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_16.getText().toString()));
            }
        });
        calender_button_showing_shadow_17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(17);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_17.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_17.getText().toString()));
            }
        });
        calender_button_showing_shadow_18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(18);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_18.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_18.getText().toString()));
            }
        });
        calender_button_showing_shadow_19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(19);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_19.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_19.getText().toString()));
            }
        });
        calender_button_showing_shadow_20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(20);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_20.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_20.getText().toString()));
            }
        });
        calender_button_showing_shadow_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(21);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_21.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_21.getText().toString()));
            }
        });
        calender_button_showing_shadow_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(22);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_22.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_22.getText().toString()));
            }
        });
        calender_button_showing_shadow_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(23);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_23.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_23.getText().toString()));
            }
        });
        calender_button_showing_shadow_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(24);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_24.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_24.getText().toString()));
            }
        });
        calender_button_showing_shadow_25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(25);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_25.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_25.getText().toString()));
            }
        });
        calender_button_showing_shadow_26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(26);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_26.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_26.getText().toString()));
            }
        });
        calender_button_showing_shadow_27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(27);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_27.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_27.getText().toString()));
            }
        });
        calender_button_showing_shadow_28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(28);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_28.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_28.getText().toString()));
            }
        });
        calender_button_showing_shadow_29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(29);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_29.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_29.getText().toString()));
            }
        });
        calender_button_showing_shadow_30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(30);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_30.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_30.getText().toString()));
            }
        });
        calender_button_showing_shadow_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(31);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_31.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_31.getText().toString()));
            }
        });
        calender_button_showing_shadow_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(32);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_32.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_32.getText().toString()));
            }
        });
        calender_button_showing_shadow_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(33);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_33.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_33.getText().toString()));
            }
        });
        calender_button_showing_shadow_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(34);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_34.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_34.getText().toString()));
            }
        });
        calender_button_showing_shadow_35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(35);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_35.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_35.getText().toString()));
            }
        });
        calender_button_showing_shadow_36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(36);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_36.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_36.getText().toString()));
            }
        });
        calender_button_showing_shadow_37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_color_from_the_keyboard();
                color_the_selected(37);
                if (getActivity() != null) {
                    TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
                    String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_37.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                color_the_day_which_is_today();
                set_the_today_for_the_events(Integer.parseInt(calender_button_showing_shadow_37.getText().toString()));
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

    private void color_the_day_which_is_today() {
        if (getActivity() != null) {
            TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
            String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
            Calendar calendar = Calendar.getInstance();
            int real_day = calendar.get(Calendar.DAY_OF_MONTH);
            int real_month = calendar.get(Calendar.MONTH);
            int real_year = calendar.get(Calendar.YEAR);
            if (real_year == Integer.parseInt(splitter[1]) && real_month == return_month_string_to_int(splitter[0]) && !check_if_i_am_chosen(return_which_day_is_linked_to_calender(real_day))) {
                if (return_which_day_is_linked_to_calender(real_day) == 1) {
                    calender_button_showing_shadow_1.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 2) {
                    calender_button_showing_shadow_2.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 3) {
                    calender_button_showing_shadow_3.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 4) {
                    calender_button_showing_shadow_4.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 5) {
                    calender_button_showing_shadow_5.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 6) {
                    calender_button_showing_shadow_6.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 7) {
                    calender_button_showing_shadow_7.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 8) {
                    calender_button_showing_shadow_8.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 9) {
                    calender_button_showing_shadow_9.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 10) {
                    calender_button_showing_shadow_10.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 11) {
                    calender_button_showing_shadow_11.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 12) {
                    calender_button_showing_shadow_12.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 13) {
                    calender_button_showing_shadow_13.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 14) {
                    calender_button_showing_shadow_14.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 15) {
                    calender_button_showing_shadow_15.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 16) {
                    calender_button_showing_shadow_16.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 17) {
                    calender_button_showing_shadow_17.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 18) {
                    calender_button_showing_shadow_18.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 19) {
                    calender_button_showing_shadow_19.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 20) {
                    calender_button_showing_shadow_20.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 21) {
                    calender_button_showing_shadow_21.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 22) {
                    calender_button_showing_shadow_22.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 23) {
                    calender_button_showing_shadow_23.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 24) {
                    calender_button_showing_shadow_24.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 25) {
                    calender_button_showing_shadow_25.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 26) {
                    calender_button_showing_shadow_26.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 27) {
                    calender_button_showing_shadow_27.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 28) {
                    calender_button_showing_shadow_28.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 29) {
                    calender_button_showing_shadow_29.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 30) {
                    calender_button_showing_shadow_30.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 31) {
                    calender_button_showing_shadow_31.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 32) {
                    calender_button_showing_shadow_32.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 33) {
                    calender_button_showing_shadow_33.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 34) {
                    calender_button_showing_shadow_34.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 35) {
                    calender_button_showing_shadow_35.setTextColor(Color.parseColor("#607D8B"));
                } else if (return_which_day_is_linked_to_calender(real_day) == 36) {
                    calender_button_showing_shadow_36.setTextColor(Color.parseColor("#607D8B"));
                } else {
                    calender_button_showing_shadow_37.setTextColor(Color.parseColor("#607D8B"));
                }
            }
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
        } else if (which == 37 && calender_button_showing_shadow_37.getCurrentTextColor() == Color.WHITE) {
            return true;
        }
        return false;
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

    private String return_the_states_of_days() {
        if (getContext() != null) {
            String all_states = "";
            for (int i = 1; i <= return_last_day_of_month(); i++) {
                String today = return_day_month_and_year(i);
                if (Return_the_time_from_milli_to_time.read_the_files(getContext()).contains(today)) {
                    String[] splitter = Return_the_time_from_milli_to_time.read_the_files(getContext()).split("_split_here_");
                    String single_date = "";
                    for (int j = 0; j < splitter.length; j++) {
                        if (splitter[j].contains(today)) {
                            single_date = splitter[j];
                            break;
                        }
                    }
                    String[] split_the_single = single_date.split("_split_");
                    all_states = all_states.concat(split_the_single[0]).concat("split");
                } else {
                    all_states = all_states.concat("middle").concat("split");
                }
            }
            return all_states;
        } else {
            return "";
        }
    }

    private String return_day_month_and_year(int day) {
        if (getActivity() != null) {
            String time;
            if (day < 10) {
                time = "0".concat(String.valueOf(day));
            } else {
                time = String.valueOf(day);
            }
            time = time.concat("_");
            TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
            String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
            if ((return_month_string_to_int(splitter[0]) + 1) < 10) {
                time = time.concat("0".concat(String.valueOf(1 + return_month_string_to_int(splitter[0]))));
            } else {
                time = time.concat(String.valueOf(1 + return_month_string_to_int(splitter[0])));
            }
            time = time.concat("_");
            time = time.concat(splitter[1]);
            return time;
        } else {
            return "";
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
        String[] splitter_read = return_the_states_of_days().split("split");

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

    private void hide_the_middle() {
        if (getActivity() != null) {
            View middle_calender_1 = getActivity().findViewById(R.id.middle_calender_1);
            View middle_calender_2 = getActivity().findViewById(R.id.middle_calender_2);
            View middle_calender_3 = getActivity().findViewById(R.id.middle_calender_3);
            View middle_calender_4 = getActivity().findViewById(R.id.middle_calender_4);
            View middle_calender_5 = getActivity().findViewById(R.id.middle_calender_5);
            View middle_calender_6 = getActivity().findViewById(R.id.middle_calender_6);
            View middle_calender_7 = getActivity().findViewById(R.id.middle_calender_7);
            View middle_calender_8 = getActivity().findViewById(R.id.middle_calender_8);
            View middle_calender_9 = getActivity().findViewById(R.id.middle_calender_9);
            View middle_calender_10 = getActivity().findViewById(R.id.middle_calender_10);
            View middle_calender_11 = getActivity().findViewById(R.id.middle_calender_11);
            View middle_calender_12 = getActivity().findViewById(R.id.middle_calender_12);
            View middle_calender_13 = getActivity().findViewById(R.id.middle_calender_13);
            View middle_calender_14 = getActivity().findViewById(R.id.middle_calender_14);
            View middle_calender_15 = getActivity().findViewById(R.id.middle_calender_15);
            View middle_calender_16 = getActivity().findViewById(R.id.middle_calender_16);
            View middle_calender_17 = getActivity().findViewById(R.id.middle_calender_17);
            View middle_calender_18 = getActivity().findViewById(R.id.middle_calender_18);
            View middle_calender_19 = getActivity().findViewById(R.id.middle_calender_19);
            View middle_calender_20 = getActivity().findViewById(R.id.middle_calender_20);
            View middle_calender_21 = getActivity().findViewById(R.id.middle_calender_21);
            View middle_calender_22 = getActivity().findViewById(R.id.middle_calender_22);
            View middle_calender_23 = getActivity().findViewById(R.id.middle_calender_23);
            View middle_calender_24 = getActivity().findViewById(R.id.middle_calender_24);
            View middle_calender_25 = getActivity().findViewById(R.id.middle_calender_25);
            View middle_calender_26 = getActivity().findViewById(R.id.middle_calender_26);
            View middle_calender_27 = getActivity().findViewById(R.id.middle_calender_27);
            View middle_calender_28 = getActivity().findViewById(R.id.middle_calender_28);
            View middle_calender_29 = getActivity().findViewById(R.id.middle_calender_29);
            View middle_calender_30 = getActivity().findViewById(R.id.middle_calender_30);
            View middle_calender_31 = getActivity().findViewById(R.id.middle_calender_31);
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

    private void set_the_first_line(String date) {
        if (getActivity() != null) {
            View calender_under_number_state_1 = getActivity().findViewById(R.id.calender_under_number_state_1);
            View calender_under_number_state_2 = getActivity().findViewById(R.id.calender_under_number_state_2);
            View calender_under_number_state_3 = getActivity().findViewById(R.id.calender_under_number_state_3);
            View calender_under_number_state_4 = getActivity().findViewById(R.id.calender_under_number_state_4);
            View calender_under_number_state_5 = getActivity().findViewById(R.id.calender_under_number_state_5);
            View calender_under_number_state_6 = getActivity().findViewById(R.id.calender_under_number_state_6);
            View calender_under_number_state_7 = getActivity().findViewById(R.id.calender_under_number_state_7);

            String[] date_split = date.split("split");
            int empty_length = 7 - date_split.length;
            for (int i = 0; i < empty_length; i++) {
                date = "empty".concat("split").concat(date);
            }
            String[] splitter_second = date.split("split");
            if (splitter_second[0].equals("empty")) {
                calender_under_number_state_1.setVisibility(View.INVISIBLE);
            } else if (splitter_second[0].equals("start")) {
                calender_under_number_state_1.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[0].equals("middle")) {
                calender_under_number_state_1.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[0].equals("end")) {
                calender_under_number_state_1.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[0].equals("beg_last")) {
                calender_under_number_state_1.setBackground(first_and_last_part_rectangle_calender);
            }
            if (splitter_second[1].equals("empty")) {
                calender_under_number_state_2.setVisibility(View.INVISIBLE);
            } else if (splitter_second[1].equals("start")) {
                calender_under_number_state_2.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[1].equals("middle")) {
                calender_under_number_state_2.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[1].equals("end")) {
                calender_under_number_state_2.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[1].equals("beg_last")) {
                calender_under_number_state_2.setBackground(first_and_last_part_rectangle_calender);
            }
            if (splitter_second[2].equals("empty")) {
                calender_under_number_state_3.setVisibility(View.INVISIBLE);
            } else if (splitter_second[2].equals("start")) {
                calender_under_number_state_3.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[2].equals("middle")) {
                calender_under_number_state_3.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[2].equals("end")) {
                calender_under_number_state_3.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[2].equals("beg_last")) {
                calender_under_number_state_3.setBackground(first_and_last_part_rectangle_calender);
            }
            if (splitter_second[3].equals("empty")) {
                calender_under_number_state_4.setVisibility(View.INVISIBLE);
            } else if (splitter_second[3].equals("start")) {
                calender_under_number_state_4.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[3].equals("middle")) {
                calender_under_number_state_4.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[3].equals("end")) {
                calender_under_number_state_4.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[3].equals("beg_last")) {
                calender_under_number_state_4.setBackground(first_and_last_part_rectangle_calender);
            }
            if (splitter_second[4].equals("empty")) {
                calender_under_number_state_5.setVisibility(View.INVISIBLE);
            } else if (splitter_second[4].equals("start")) {
                calender_under_number_state_5.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[4].equals("middle")) {
                calender_under_number_state_5.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[4].equals("end")) {
                calender_under_number_state_5.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[4].equals("beg_last")) {
                calender_under_number_state_5.setBackground(first_and_last_part_rectangle_calender);
            }
            if (splitter_second[5].equals("empty")) {
                calender_under_number_state_6.setVisibility(View.INVISIBLE);
            } else if (splitter_second[5].equals("start")) {
                calender_under_number_state_6.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[5].equals("middle")) {
                calender_under_number_state_6.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[5].equals("end")) {
                calender_under_number_state_6.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[5].equals("beg_last")) {
                calender_under_number_state_6.setBackground(first_and_last_part_rectangle_calender);
            }
            if (splitter_second[6].equals("empty")) {
                calender_under_number_state_7.setVisibility(View.INVISIBLE);
            } else if (splitter_second[6].equals("start")) {
                calender_under_number_state_7.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[6].equals("middle")) {
                calender_under_number_state_7.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[6].equals("end")) {
                calender_under_number_state_7.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[6].equals("beg_last")) {
                calender_under_number_state_7.setBackground(first_and_last_part_rectangle_calender);
            }
        }
    }

    private void set_the_second_line(String date) {
        if (getActivity() != null) {
            View calender_under_number_state_8 = getActivity().findViewById(R.id.calender_under_number_state_8);
            View calender_under_number_state_9 = getActivity().findViewById(R.id.calender_under_number_state_9);
            View calender_under_number_state_10 = getActivity().findViewById(R.id.calender_under_number_state_10);
            View calender_under_number_state_11 = getActivity().findViewById(R.id.calender_under_number_state_11);
            View calender_under_number_state_12 = getActivity().findViewById(R.id.calender_under_number_state_12);
            View calender_under_number_state_13 = getActivity().findViewById(R.id.calender_under_number_state_13);
            View calender_under_number_state_14 = getActivity().findViewById(R.id.calender_under_number_state_14);
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
        if (getActivity() != null) {
            View calender_under_number_state_15 = getActivity().findViewById(R.id.calender_under_number_state_15);
            View calender_under_number_state_16 = getActivity().findViewById(R.id.calender_under_number_state_16);
            View calender_under_number_state_17 = getActivity().findViewById(R.id.calender_under_number_state_17);
            View calender_under_number_state_18 = getActivity().findViewById(R.id.calender_under_number_state_18);
            View calender_under_number_state_19 = getActivity().findViewById(R.id.calender_under_number_state_19);
            View calender_under_number_state_20 = getActivity().findViewById(R.id.calender_under_number_state_20);
            View calender_under_number_state_21 = getActivity().findViewById(R.id.calender_under_number_state_21);
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
        if (getActivity() != null) {
            View calender_under_number_state_22 = getActivity().findViewById(R.id.calender_under_number_state_22);
            View calender_under_number_state_23 = getActivity().findViewById(R.id.calender_under_number_state_23);
            View calender_under_number_state_24 = getActivity().findViewById(R.id.calender_under_number_state_24);
            View calender_under_number_state_25 = getActivity().findViewById(R.id.calender_under_number_state_25);
            View calender_under_number_state_26 = getActivity().findViewById(R.id.calender_under_number_state_26);
            View calender_under_number_state_27 = getActivity().findViewById(R.id.calender_under_number_state_27);
            View calender_under_number_state_28 = getActivity().findViewById(R.id.calender_under_number_state_28);
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
        if (getActivity() != null) {
            View calender_under_number_state_29 = getActivity().findViewById(R.id.calender_under_number_state_29);
            View calender_under_number_state_30 = getActivity().findViewById(R.id.calender_under_number_state_30);
            View calender_under_number_state_31 = getActivity().findViewById(R.id.calender_under_number_state_31);
            View calender_under_number_state_32 = getActivity().findViewById(R.id.calender_under_number_state_32);
            View calender_under_number_state_33 = getActivity().findViewById(R.id.calender_under_number_state_33);
            View calender_under_number_state_34 = getActivity().findViewById(R.id.calender_under_number_state_34);
            View calender_under_number_state_35 = getActivity().findViewById(R.id.calender_under_number_state_35);
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
        if (getActivity() != null) {
            View calender_under_number_state_36 = getActivity().findViewById(R.id.calender_under_number_state_36);
            View calender_under_number_state_37 = getActivity().findViewById(R.id.calender_under_number_state_37);
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

    private void clear_the_drawables() {
        if (getActivity() != null) {
            View calender_under_number_state_1 = getActivity().findViewById(R.id.calender_under_number_state_1);
            View calender_under_number_state_2 = getActivity().findViewById(R.id.calender_under_number_state_2);
            View calender_under_number_state_3 = getActivity().findViewById(R.id.calender_under_number_state_3);
            View calender_under_number_state_4 = getActivity().findViewById(R.id.calender_under_number_state_4);
            View calender_under_number_state_5 = getActivity().findViewById(R.id.calender_under_number_state_5);
            View calender_under_number_state_6 = getActivity().findViewById(R.id.calender_under_number_state_6);
            View calender_under_number_state_7 = getActivity().findViewById(R.id.calender_under_number_state_7);
            View calender_under_number_state_8 = getActivity().findViewById(R.id.calender_under_number_state_8);
            View calender_under_number_state_9 = getActivity().findViewById(R.id.calender_under_number_state_9);
            View calender_under_number_state_10 = getActivity().findViewById(R.id.calender_under_number_state_10);
            View calender_under_number_state_11 = getActivity().findViewById(R.id.calender_under_number_state_11);
            View calender_under_number_state_12 = getActivity().findViewById(R.id.calender_under_number_state_12);
            View calender_under_number_state_13 = getActivity().findViewById(R.id.calender_under_number_state_13);
            View calender_under_number_state_14 = getActivity().findViewById(R.id.calender_under_number_state_14);
            View calender_under_number_state_15 = getActivity().findViewById(R.id.calender_under_number_state_15);
            View calender_under_number_state_16 = getActivity().findViewById(R.id.calender_under_number_state_16);
            View calender_under_number_state_17 = getActivity().findViewById(R.id.calender_under_number_state_17);
            View calender_under_number_state_18 = getActivity().findViewById(R.id.calender_under_number_state_18);
            View calender_under_number_state_19 = getActivity().findViewById(R.id.calender_under_number_state_19);
            View calender_under_number_state_20 = getActivity().findViewById(R.id.calender_under_number_state_20);
            View calender_under_number_state_21 = getActivity().findViewById(R.id.calender_under_number_state_21);
            View calender_under_number_state_22 = getActivity().findViewById(R.id.calender_under_number_state_22);
            View calender_under_number_state_23 = getActivity().findViewById(R.id.calender_under_number_state_23);
            View calender_under_number_state_24 = getActivity().findViewById(R.id.calender_under_number_state_24);
            View calender_under_number_state_25 = getActivity().findViewById(R.id.calender_under_number_state_25);
            View calender_under_number_state_26 = getActivity().findViewById(R.id.calender_under_number_state_26);
            View calender_under_number_state_27 = getActivity().findViewById(R.id.calender_under_number_state_27);
            View calender_under_number_state_28 = getActivity().findViewById(R.id.calender_under_number_state_28);
            View calender_under_number_state_29 = getActivity().findViewById(R.id.calender_under_number_state_29);
            View calender_under_number_state_30 = getActivity().findViewById(R.id.calender_under_number_state_30);
            View calender_under_number_state_31 = getActivity().findViewById(R.id.calender_under_number_state_31);
            View calender_under_number_state_32 = getActivity().findViewById(R.id.calender_under_number_state_32);
            View calender_under_number_state_33 = getActivity().findViewById(R.id.calender_under_number_state_33);
            View calender_under_number_state_34 = getActivity().findViewById(R.id.calender_under_number_state_34);
            View calender_under_number_state_35 = getActivity().findViewById(R.id.calender_under_number_state_35);
            View calender_under_number_state_36 = getActivity().findViewById(R.id.calender_under_number_state_36);
            View calender_under_number_state_37 = getActivity().findViewById(R.id.calender_under_number_state_37);
            String text_from_class = Return_the_time_from_milli_to_time.read_the_files(getContext());
            String[] splitter_for_first = text_from_class.split("_split_here_");
            String[] get_the_final_date = splitter_for_first[0].split("_split_");
            TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
            String[] year_and_month = month_and_year_in_calender.getText().toString().split(" ");
            String[] splitter_to_dates = get_the_final_date[1].split("_");
            int day_from_the_date = Integer.parseInt(splitter_to_dates[0]);
            int month_from_date = Integer.parseInt(splitter_to_dates[1]) - 1;
            int year_from_date = Integer.parseInt(splitter_to_dates[2]);
            Calendar calendar = Calendar.getInstance();
            int real_day = calendar.get(Calendar.DAY_OF_MONTH);
            int real_month = calendar.get(Calendar.MONTH);
            int real_year = calendar.get(Calendar.YEAR);
            if (Integer.parseInt(year_and_month[1]) < year_from_date) {
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
            } else {
                if (return_month_string_to_int(year_and_month[0]) < month_from_date) {
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
                } else {
                    for (int i = 1; i < return_last_day_of_month() + 1; i++) {
                        if (i < day_from_the_date) {
                            if (return_which_day_is_linked_to_calender(i) == 1) {
                                calender_under_number_state_1.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 2) {
                                calender_under_number_state_2.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 3) {
                                calender_under_number_state_3.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 4) {
                                calender_under_number_state_4.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 5) {
                                calender_under_number_state_5.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 6) {
                                calender_under_number_state_6.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 7) {
                                calender_under_number_state_7.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 8) {
                                calender_under_number_state_8.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 9) {
                                calender_under_number_state_9.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 10) {
                                calender_under_number_state_10.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 11) {
                                calender_under_number_state_11.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 12) {
                                calender_under_number_state_12.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 13) {
                                calender_under_number_state_13.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 14) {
                                calender_under_number_state_14.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 15) {
                                calender_under_number_state_15.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 16) {
                                calender_under_number_state_16.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 17) {
                                calender_under_number_state_17.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 18) {
                                calender_under_number_state_18.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 19) {
                                calender_under_number_state_19.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 20) {
                                calender_under_number_state_20.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 21) {
                                calender_under_number_state_21.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 22) {
                                calender_under_number_state_22.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 23) {
                                calender_under_number_state_23.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 24) {
                                calender_under_number_state_24.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 25) {
                                calender_under_number_state_25.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 26) {
                                calender_under_number_state_26.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 27) {
                                calender_under_number_state_27.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 28) {
                                calender_under_number_state_28.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 29) {
                                calender_under_number_state_29.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 30) {
                                calender_under_number_state_30.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 31) {
                                calender_under_number_state_31.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 32) {
                                calender_under_number_state_32.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 33) {
                                calender_under_number_state_33.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 34) {
                                calender_under_number_state_34.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 35) {
                                calender_under_number_state_35.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 36) {
                                calender_under_number_state_36.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 37) {
                                calender_under_number_state_37.setBackgroundResource(0);
                            }
                        }
                    }
                }
            }
            if (Integer.parseInt(year_and_month[1]) > real_year) {
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
            } else {
                if (return_month_string_to_int(year_and_month[0]) > real_month) {
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
                } else {
                    for (int i = 1; i < return_last_day_of_month() + 1; i++) {
                        if (i > real_day) {
                            if (return_which_day_is_linked_to_calender(i) == 1) {
                                calender_under_number_state_1.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 2) {
                                calender_under_number_state_2.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 3) {
                                calender_under_number_state_3.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 4) {
                                calender_under_number_state_4.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 5) {
                                calender_under_number_state_5.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 6) {
                                calender_under_number_state_6.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 7) {
                                calender_under_number_state_7.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 8) {
                                calender_under_number_state_8.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 9) {
                                calender_under_number_state_9.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 10) {
                                calender_under_number_state_10.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 11) {
                                calender_under_number_state_11.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 12) {
                                calender_under_number_state_12.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 13) {
                                calender_under_number_state_13.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 14) {
                                calender_under_number_state_14.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 15) {
                                calender_under_number_state_15.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 16) {
                                calender_under_number_state_16.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 17) {
                                calender_under_number_state_17.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 18) {
                                calender_under_number_state_18.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 19) {
                                calender_under_number_state_19.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 20) {
                                calender_under_number_state_20.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 21) {
                                calender_under_number_state_21.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 22) {
                                calender_under_number_state_22.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 23) {
                                calender_under_number_state_23.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 24) {
                                calender_under_number_state_24.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 25) {
                                calender_under_number_state_25.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 26) {
                                calender_under_number_state_26.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 27) {
                                calender_under_number_state_27.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 28) {
                                calender_under_number_state_28.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 29) {
                                calender_under_number_state_29.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 30) {
                                calender_under_number_state_30.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 31) {
                                calender_under_number_state_31.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 32) {
                                calender_under_number_state_32.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 33) {
                                calender_under_number_state_33.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 34) {
                                calender_under_number_state_34.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 35) {
                                calender_under_number_state_35.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 36) {
                                calender_under_number_state_36.setBackgroundResource(0);
                            } else if (return_which_day_is_linked_to_calender(i) == 37) {
                                calender_under_number_state_37.setBackgroundResource(0);
                            }
                        }
                    }
                }
            }
        }
    }

    private void make_the_middle_come_again() {
        if (getActivity() != null) {
            View calender_under_number_state_1 = getActivity().findViewById(R.id.calender_under_number_state_1);
            View calender_under_number_state_2 = getActivity().findViewById(R.id.calender_under_number_state_2);
            View calender_under_number_state_3 = getActivity().findViewById(R.id.calender_under_number_state_3);
            View calender_under_number_state_4 = getActivity().findViewById(R.id.calender_under_number_state_4);
            View calender_under_number_state_5 = getActivity().findViewById(R.id.calender_under_number_state_5);
            View calender_under_number_state_6 = getActivity().findViewById(R.id.calender_under_number_state_6);
            View calender_under_number_state_8 = getActivity().findViewById(R.id.calender_under_number_state_8);
            View calender_under_number_state_9 = getActivity().findViewById(R.id.calender_under_number_state_9);
            View calender_under_number_state_10 = getActivity().findViewById(R.id.calender_under_number_state_10);
            View calender_under_number_state_11 = getActivity().findViewById(R.id.calender_under_number_state_11);
            View calender_under_number_state_12 = getActivity().findViewById(R.id.calender_under_number_state_12);
            View calender_under_number_state_13 = getActivity().findViewById(R.id.calender_under_number_state_13);
            View calender_under_number_state_15 = getActivity().findViewById(R.id.calender_under_number_state_15);
            View calender_under_number_state_16 = getActivity().findViewById(R.id.calender_under_number_state_16);
            View calender_under_number_state_17 = getActivity().findViewById(R.id.calender_under_number_state_17);
            View calender_under_number_state_18 = getActivity().findViewById(R.id.calender_under_number_state_18);
            View calender_under_number_state_19 = getActivity().findViewById(R.id.calender_under_number_state_19);
            View calender_under_number_state_20 = getActivity().findViewById(R.id.calender_under_number_state_20);
            View calender_under_number_state_22 = getActivity().findViewById(R.id.calender_under_number_state_22);
            View calender_under_number_state_23 = getActivity().findViewById(R.id.calender_under_number_state_23);
            View calender_under_number_state_24 = getActivity().findViewById(R.id.calender_under_number_state_24);
            View calender_under_number_state_25 = getActivity().findViewById(R.id.calender_under_number_state_25);
            View calender_under_number_state_26 = getActivity().findViewById(R.id.calender_under_number_state_26);
            View calender_under_number_state_27 = getActivity().findViewById(R.id.calender_under_number_state_27);
            View calender_under_number_state_29 = getActivity().findViewById(R.id.calender_under_number_state_29);
            View calender_under_number_state_30 = getActivity().findViewById(R.id.calender_under_number_state_30);
            View calender_under_number_state_31 = getActivity().findViewById(R.id.calender_under_number_state_31);
            View calender_under_number_state_32 = getActivity().findViewById(R.id.calender_under_number_state_32);
            View calender_under_number_state_33 = getActivity().findViewById(R.id.calender_under_number_state_33);
            View calender_under_number_state_34 = getActivity().findViewById(R.id.calender_under_number_state_34);
            View calender_under_number_state_36 = getActivity().findViewById(R.id.calender_under_number_state_36);
            View middle_calender_1 = getActivity().findViewById(R.id.middle_calender_1);
            View middle_calender_2 = getActivity().findViewById(R.id.middle_calender_2);
            View middle_calender_3 = getActivity().findViewById(R.id.middle_calender_3);
            View middle_calender_4 = getActivity().findViewById(R.id.middle_calender_4);
            View middle_calender_5 = getActivity().findViewById(R.id.middle_calender_5);
            View middle_calender_6 = getActivity().findViewById(R.id.middle_calender_6);
            View middle_calender_7 = getActivity().findViewById(R.id.middle_calender_7);
            View middle_calender_8 = getActivity().findViewById(R.id.middle_calender_8);
            View middle_calender_9 = getActivity().findViewById(R.id.middle_calender_9);
            View middle_calender_10 = getActivity().findViewById(R.id.middle_calender_10);
            View middle_calender_11 = getActivity().findViewById(R.id.middle_calender_11);
            View middle_calender_12 = getActivity().findViewById(R.id.middle_calender_12);
            View middle_calender_13 = getActivity().findViewById(R.id.middle_calender_13);
            View middle_calender_14 = getActivity().findViewById(R.id.middle_calender_14);
            View middle_calender_15 = getActivity().findViewById(R.id.middle_calender_15);
            View middle_calender_16 = getActivity().findViewById(R.id.middle_calender_16);
            View middle_calender_17 = getActivity().findViewById(R.id.middle_calender_17);
            View middle_calender_18 = getActivity().findViewById(R.id.middle_calender_18);
            View middle_calender_19 = getActivity().findViewById(R.id.middle_calender_19);
            View middle_calender_20 = getActivity().findViewById(R.id.middle_calender_20);
            View middle_calender_21 = getActivity().findViewById(R.id.middle_calender_21);
            View middle_calender_22 = getActivity().findViewById(R.id.middle_calender_22);
            View middle_calender_23 = getActivity().findViewById(R.id.middle_calender_23);
            View middle_calender_24 = getActivity().findViewById(R.id.middle_calender_24);
            View middle_calender_25 = getActivity().findViewById(R.id.middle_calender_25);
            View middle_calender_26 = getActivity().findViewById(R.id.middle_calender_26);
            View middle_calender_27 = getActivity().findViewById(R.id.middle_calender_27);
            View middle_calender_28 = getActivity().findViewById(R.id.middle_calender_28);
            View middle_calender_29 = getActivity().findViewById(R.id.middle_calender_29);
            View middle_calender_30 = getActivity().findViewById(R.id.middle_calender_30);
            View middle_calender_31 = getActivity().findViewById(R.id.middle_calender_31);
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

    private void add_and_show_button_listener() {
        if (getActivity() != null) {
            Button add_events_button_for_calender = getActivity().findViewById(R.id.add_events_button_for_calender);
            Button show_events_button_for_calender = getActivity().findViewById(R.id.show_events_button_for_calender);
            add_events_button_for_calender.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        final Add_and_show_events add_and_show_events = new Add_and_show_events();
                        add_and_show_events.show(getActivity().getSupportFragmentManager(), "add_event".concat("split").concat(what_day_is_today));
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.executePendingTransactions();
                        if (add_and_show_events.getDialog() != null) {
                            add_and_show_events.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialogInterface) {
                                    clear_all_the_events_circles();
                                    set_up_the_events();
                                    getActivity().getSupportFragmentManager().beginTransaction().remove(add_and_show_events).commitAllowingStateLoss();
                                }
                            });
                        }
                    }
                }
            });
            show_events_button_for_calender.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        final Add_and_show_events add_and_show_events = new Add_and_show_events();
                        add_and_show_events.show(getActivity().getSupportFragmentManager(), "show_event".concat("split").concat(what_day_is_today));
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.executePendingTransactions();
                        if (add_and_show_events.getDialog() != null) {
                            add_and_show_events.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialogInterface) {
                                    clear_all_the_events_circles();
                                    set_up_the_events();
                                    getActivity().getSupportFragmentManager().beginTransaction().remove(add_and_show_events).commitAllowingStateLoss();
                                }
                            });
                        }
                    }
                }
            });
        }
    }

    private void move_the_buttons() {
        if (getActivity() != null) {
            ConstraintLayout constraintLayout = getActivity().findViewById(R.id.stat_fragment_for_layout);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            Button add_events_button_for_calender = getActivity().findViewById(R.id.add_events_button_for_calender);
            if (calender_button_showing_shadow_29.getVisibility() == View.INVISIBLE) {
                constraintSet.connect(add_events_button_for_calender.getId(), ConstraintSet.TOP, R.id.calender_in_stats, ConstraintSet.TOP, dip_to_pixels(260));
            } else if (calender_button_showing_shadow_36.getVisibility() == View.INVISIBLE) {
                constraintSet.connect(add_events_button_for_calender.getId(), ConstraintSet.TOP, R.id.calender_in_stats, ConstraintSet.TOP, dip_to_pixels(300));
            } else {
                constraintSet.connect(add_events_button_for_calender.getId(), ConstraintSet.TOP, R.id.calender_in_stats, ConstraintSet.TOP, dip_to_pixels(340));
            }
            constraintSet.applyTo(constraintLayout);
        }
    }

    private void set_the_today_for_the_events(int day) {
        if (getActivity() != null) {
            TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
            String[] splitter = month_and_year_in_calender.getText().toString().split(" ");
            String month;
            String day_string;
            if ((return_month_string_to_int(splitter[0]) + 1) >= 10) {
                month = String.valueOf(return_month_string_to_int(splitter[0]) + 1);
            } else {
                month = "0".concat(String.valueOf(return_month_string_to_int(splitter[0]) + 1));
            }
            if (day >= 10) {
                day_string = String.valueOf(day);
            } else {
                day_string = "0".concat(String.valueOf(day));
            }
            String year = splitter[1];
            what_day_is_today = day_string.concat("/").concat(month).concat("/").concat(year);
        }
    }

    private void set_the_color_original() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String day_string;
        String month_string;
        if (day >= 10) {
            day_string = String.valueOf(day);
        } else {
            day_string = "0".concat(String.valueOf(day));
        }
        if (month >= 10) {
            month_string = String.valueOf(month);
        } else {
            month_string = "0".concat(String.valueOf(month));
        }
        what_day_is_today = day_string.concat("/").concat(month_string).concat("/").concat(year);
    }

    private void set_up_the_events() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("events_shared", MODE_PRIVATE);
            String string = sharedPreferences.getString("events", "");
            if (string != null) {
                String[] split_max = string.split("max_divide");
                for (int i = 0; i < split_max.length; i++) {
                    String[] split_the_split = split_max[i].split("split");
                    if ((!split_the_split[0].equals("")) && convert_milli_to_date(Long.parseLong(split_the_split[0])).equals(return_the_day_from_text())) {
                        if (split_the_split[3].equals("Teal")) {
                            put_the_event(convert_milli_to_date_day_only(Long.parseLong(split_the_split[0])), color_dark_green);
                        } else if (split_the_split[3].equals("Red")) {
                            put_the_event(convert_milli_to_date_day_only(Long.parseLong(split_the_split[0])), color_red);
                        } else if (split_the_split[3].equals("Green")) {
                            put_the_event(convert_milli_to_date_day_only(Long.parseLong(split_the_split[0])), color_green);
                        } else if (split_the_split[3].equals("Yellow")) {
                            put_the_event(convert_milli_to_date_day_only(Long.parseLong(split_the_split[0])), color_yellow);
                        } else if (split_the_split[3].equals("Blue")) {
                            put_the_event(convert_milli_to_date_day_only(Long.parseLong(split_the_split[0])), color_blue);
                        } else if (split_the_split[3].equals("Orange")) {
                            put_the_event(convert_milli_to_date_day_only(Long.parseLong(split_the_split[0])), color_orange);
                        } else if (split_the_split[3].equals("Purple")) {
                            put_the_event(convert_milli_to_date_day_only(Long.parseLong(split_the_split[0])), color_purple);
                        } else if (split_the_split[3].equals("Cyan")) {
                            put_the_event(convert_milli_to_date_day_only(Long.parseLong(split_the_split[0])), color_cyan);
                        } else if (split_the_split[3].equals("Magenta")) {
                            put_the_event(convert_milli_to_date_day_only(Long.parseLong(split_the_split[0])), color_magenta);
                        } else if (split_the_split[3].equals("Lime")) {
                            put_the_event(convert_milli_to_date_day_only(Long.parseLong(split_the_split[0])), color_lime);
                        } else if (split_the_split[3].equals("Pink")) {
                            put_the_event(convert_milli_to_date_day_only(Long.parseLong(split_the_split[0])), color_pink);
                        } else if (split_the_split[3].equals("Brown")) {
                            put_the_event(convert_milli_to_date_day_only(Long.parseLong(split_the_split[0])), color_brown);
                        } else if (split_the_split[3].equals("Navy")) {
                            put_the_event(convert_milli_to_date_day_only(Long.parseLong(split_the_split[0])), color_navy);
                        } else if (split_the_split[3].equals("Black")) {
                            put_the_event(convert_milli_to_date_day_only(Long.parseLong(split_the_split[0])), color_black);
                        }
                    }
                }
            }
        }
    }

    private String return_the_day_from_text() {
        if (getActivity() != null) {
            TextView month_and_year_in_calender = getActivity().findViewById(R.id.month_and_year_in_calender);
            String[] split = month_and_year_in_calender.getText().toString().split(" ");
            int month = return_month_string_to_int(split[0]) + 1;
            if (month >= 10) {
                return String.valueOf(return_month_string_to_int(split[0]) + 1).concat("/").concat(split[1]);
            } else {
                return "0".concat(String.valueOf(return_month_string_to_int(split[0]) + 1)).concat("/").concat(split[1]);
            }
        }
        return "";
    }

    private String convert_milli_to_date(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    private int convert_milli_to_date_day_only(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return Integer.parseInt(formatter.format(calendar.getTime()));
    }

    private void clear_all_the_events_circles() {
        if (getActivity() != null) {
            View event_for_calender_one_1 = getActivity().findViewById(R.id.event_for_calender_one_1);
            View event_for_calender_one_2 = getActivity().findViewById(R.id.event_for_calender_one_2);
            View event_for_calender_one_3 = getActivity().findViewById(R.id.event_for_calender_one_3);
            View event_for_calender_two_1 = getActivity().findViewById(R.id.event_for_calender_two_1);
            View event_for_calender_two_2 = getActivity().findViewById(R.id.event_for_calender_two_2);
            View event_for_calender_two_3 = getActivity().findViewById(R.id.event_for_calender_two_3);
            View event_for_calender_three_1 = getActivity().findViewById(R.id.event_for_calender_three_1);
            View event_for_calender_three_2 = getActivity().findViewById(R.id.event_for_calender_three_2);
            View event_for_calender_three_3 = getActivity().findViewById(R.id.event_for_calender_three_3);
            View event_for_calender_four_1 = getActivity().findViewById(R.id.event_for_calender_four_1);
            View event_for_calender_four_2 = getActivity().findViewById(R.id.event_for_calender_four_2);
            View event_for_calender_four_3 = getActivity().findViewById(R.id.event_for_calender_four_3);
            View event_for_calender_five_1 = getActivity().findViewById(R.id.event_for_calender_five_1);
            View event_for_calender_five_2 = getActivity().findViewById(R.id.event_for_calender_five_2);
            View event_for_calender_five_3 = getActivity().findViewById(R.id.event_for_calender_five_3);
            View event_for_calender_six_1 = getActivity().findViewById(R.id.event_for_calender_six_1);
            View event_for_calender_six_2 = getActivity().findViewById(R.id.event_for_calender_six_2);
            View event_for_calender_six_3 = getActivity().findViewById(R.id.event_for_calender_six_3);
            View event_for_calender_seven_1 = getActivity().findViewById(R.id.event_for_calender_seven_1);
            View event_for_calender_seven_2 = getActivity().findViewById(R.id.event_for_calender_seven_2);
            View event_for_calender_seven_3 = getActivity().findViewById(R.id.event_for_calender_seven_3);
            View event_for_calender_eight_1 = getActivity().findViewById(R.id.event_for_calender_eight_1);
            View event_for_calender_eight_2 = getActivity().findViewById(R.id.event_for_calender_eight_2);
            View event_for_calender_eight_3 = getActivity().findViewById(R.id.event_for_calender_eight_3);
            View event_for_calender_nine_1 = getActivity().findViewById(R.id.event_for_calender_nine_1);
            View event_for_calender_nine_2 = getActivity().findViewById(R.id.event_for_calender_nine_2);
            View event_for_calender_nine_3 = getActivity().findViewById(R.id.event_for_calender_nine_3);
            View event_for_calender_ten_1 = getActivity().findViewById(R.id.event_for_calender_ten_1);
            View event_for_calender_ten_2 = getActivity().findViewById(R.id.event_for_calender_ten_2);
            View event_for_calender_ten_3 = getActivity().findViewById(R.id.event_for_calender_ten_3);
            View event_for_calender_eleven_1 = getActivity().findViewById(R.id.event_for_calender_eleven_1);
            View event_for_calender_eleven_2 = getActivity().findViewById(R.id.event_for_calender_eleven_2);
            View event_for_calender_eleven_3 = getActivity().findViewById(R.id.event_for_calender_eleven_3);
            View event_for_calender_twelve_1 = getActivity().findViewById(R.id.event_for_calender_twelve_1);
            View event_for_calender_twelve_2 = getActivity().findViewById(R.id.event_for_calender_twelve_2);
            View event_for_calender_twelve_3 = getActivity().findViewById(R.id.event_for_calender_twelve_3);
            View event_for_calender_thirteen_1 = getActivity().findViewById(R.id.event_for_calender_thirteen_1);
            View event_for_calender_thirteen_2 = getActivity().findViewById(R.id.event_for_calender_thirteen_2);
            View event_for_calender_thirteen_3 = getActivity().findViewById(R.id.event_for_calender_thirteen_3);
            View event_for_calender_fourteen_1 = getActivity().findViewById(R.id.event_for_calender_fourteen_1);
            View event_for_calender_fourteen_2 = getActivity().findViewById(R.id.event_for_calender_fourteen_2);
            View event_for_calender_fourteen_3 = getActivity().findViewById(R.id.event_for_calender_fourteen_3);
            View event_for_calender_fifteen_1 = getActivity().findViewById(R.id.event_for_calender_fifteen_1);
            View event_for_calender_fifteen_2 = getActivity().findViewById(R.id.event_for_calender_fifteen_2);
            View event_for_calender_fifteen_3 = getActivity().findViewById(R.id.event_for_calender_fifteen_3);
            View event_for_calender_sixteen_1 = getActivity().findViewById(R.id.event_for_calender_sixteen_1);
            View event_for_calender_sixteen_2 = getActivity().findViewById(R.id.event_for_calender_sixteen_2);
            View event_for_calender_sixteen_3 = getActivity().findViewById(R.id.event_for_calender_sixteen_3);
            View event_for_calender_seventeen_1 = getActivity().findViewById(R.id.event_for_calender_seventeen_1);
            View event_for_calender_seventeen_2 = getActivity().findViewById(R.id.event_for_calender_seventeen_2);
            View event_for_calender_seventeen_3 = getActivity().findViewById(R.id.event_for_calender_seventeen_3);
            View event_for_calender_eighteen_1 = getActivity().findViewById(R.id.event_for_calender_eighteen_1);
            View event_for_calender_eighteen_2 = getActivity().findViewById(R.id.event_for_calender_eighteen_2);
            View event_for_calender_eighteen_3 = getActivity().findViewById(R.id.event_for_calender_eighteen_3);
            View event_for_calender_nineteen_1 = getActivity().findViewById(R.id.event_for_calender_nineteen_1);
            View event_for_calender_nineteen_2 = getActivity().findViewById(R.id.event_for_calender_nineteen_2);
            View event_for_calender_nineteen_3 = getActivity().findViewById(R.id.event_for_calender_nineteen_3);
            View event_for_calender_twenty_1 = getActivity().findViewById(R.id.event_for_calender_twenty_1);
            View event_for_calender_twenty_2 = getActivity().findViewById(R.id.event_for_calender_twenty_2);
            View event_for_calender_twenty_3 = getActivity().findViewById(R.id.event_for_calender_twenty_3);
            View event_for_calender_twenty_one_1 = getActivity().findViewById(R.id.event_for_calender_twenty_one_1);
            View event_for_calender_twenty_one_2 = getActivity().findViewById(R.id.event_for_calender_twenty_one_2);
            View event_for_calender_twenty_one_3 = getActivity().findViewById(R.id.event_for_calender_twenty_one_3);
            View event_for_calender_twenty_two_1 = getActivity().findViewById(R.id.event_for_calender_twenty_two_1);
            View event_for_calender_twenty_two_2 = getActivity().findViewById(R.id.event_for_calender_twenty_two_2);
            View event_for_calender_twenty_two_3 = getActivity().findViewById(R.id.event_for_calender_twenty_two_3);
            View event_for_calender_twenty_three_1 = getActivity().findViewById(R.id.event_for_calender_twenty_three_1);
            View event_for_calender_twenty_three_2 = getActivity().findViewById(R.id.event_for_calender_twenty_three_2);
            View event_for_calender_twenty_three_3 = getActivity().findViewById(R.id.event_for_calender_twenty_three_3);
            View event_for_calender_twenty_four_1 = getActivity().findViewById(R.id.event_for_calender_twenty_four_1);
            View event_for_calender_twenty_four_2 = getActivity().findViewById(R.id.event_for_calender_twenty_four_2);
            View event_for_calender_twenty_four_3 = getActivity().findViewById(R.id.event_for_calender_twenty_four_3);
            View event_for_calender_twenty_five_1 = getActivity().findViewById(R.id.event_for_calender_twenty_five_1);
            View event_for_calender_twenty_five_2 = getActivity().findViewById(R.id.event_for_calender_twenty_five_2);
            View event_for_calender_twenty_five_3 = getActivity().findViewById(R.id.event_for_calender_twenty_five_3);
            View event_for_calender_twenty_six_1 = getActivity().findViewById(R.id.event_for_calender_twenty_six_1);
            View event_for_calender_twenty_six_2 = getActivity().findViewById(R.id.event_for_calender_twenty_six_2);
            View event_for_calender_twenty_six_3 = getActivity().findViewById(R.id.event_for_calender_twenty_six_3);
            View event_for_calender_twenty_seven_1 = getActivity().findViewById(R.id.event_for_calender_twenty_seven_1);
            View event_for_calender_twenty_seven_2 = getActivity().findViewById(R.id.event_for_calender_twenty_seven_2);
            View event_for_calender_twenty_seven_3 = getActivity().findViewById(R.id.event_for_calender_twenty_seven_3);
            View event_for_calender_twenty_eight_1 = getActivity().findViewById(R.id.event_for_calender_twenty_eight_1);
            View event_for_calender_twenty_eight_2 = getActivity().findViewById(R.id.event_for_calender_twenty_eight_2);
            View event_for_calender_twenty_eight_3 = getActivity().findViewById(R.id.event_for_calender_twenty_eight_3);
            View event_for_calender_twenty_nine_1 = getActivity().findViewById(R.id.event_for_calender_twenty_nine_1);
            View event_for_calender_twenty_nine_2 = getActivity().findViewById(R.id.event_for_calender_twenty_nine_2);
            View event_for_calender_twenty_nine_3 = getActivity().findViewById(R.id.event_for_calender_twenty_nine_3);
            View event_for_calender_thirty_one_1 = getActivity().findViewById(R.id.event_for_calender_thirty_one_1);
            View event_for_calender_thirty_one_2 = getActivity().findViewById(R.id.event_for_calender_thirty_one_2);
            View event_for_calender_thirty_one_3 = getActivity().findViewById(R.id.event_for_calender_thirty_one_3);
            View event_for_calender_thirty_two_1 = getActivity().findViewById(R.id.event_for_calender_thirty_two_1);
            View event_for_calender_thirty_two_2 = getActivity().findViewById(R.id.event_for_calender_thirty_two_2);
            View event_for_calender_thirty_two_3 = getActivity().findViewById(R.id.event_for_calender_thirty_two_3);
            View event_for_calender_thirty_three_1 = getActivity().findViewById(R.id.event_for_calender_thirty_three_1);
            View event_for_calender_thirty_three_2 = getActivity().findViewById(R.id.event_for_calender_thirty_three_2);
            View event_for_calender_thirty_three_3 = getActivity().findViewById(R.id.event_for_calender_thirty_three_3);
            View event_for_calender_thirty_four_1 = getActivity().findViewById(R.id.event_for_calender_thirty_four_1);
            View event_for_calender_thirty_four_2 = getActivity().findViewById(R.id.event_for_calender_thirty_four_2);
            View event_for_calender_thirty_four_3 = getActivity().findViewById(R.id.event_for_calender_thirty_four_3);
            View event_for_calender_thirty_five_1 = getActivity().findViewById(R.id.event_for_calender_thirty_five_1);
            View event_for_calender_thirty_five_2 = getActivity().findViewById(R.id.event_for_calender_thirty_five_2);
            View event_for_calender_thirty_five_3 = getActivity().findViewById(R.id.event_for_calender_thirty_five_3);
            View event_for_calender_thirty_six_1 = getActivity().findViewById(R.id.event_for_calender_thirty_six_1);
            View event_for_calender_thirty_six_2 = getActivity().findViewById(R.id.event_for_calender_thirty_six_2);
            View event_for_calender_thirty_six_3 = getActivity().findViewById(R.id.event_for_calender_thirty_six_3);
            View event_for_calender_thirty_seven_1 = getActivity().findViewById(R.id.event_for_calender_thirty_seven_1);
            View event_for_calender_thirty_seven_2 = getActivity().findViewById(R.id.event_for_calender_thirty_seven_2);
            View event_for_calender_thirty_seven_3 = getActivity().findViewById(R.id.event_for_calender_thirty_seven_3);
            event_for_calender_one_1.setBackgroundResource(0);
            event_for_calender_one_2.setBackgroundResource(0);
            event_for_calender_one_3.setBackgroundResource(0);
            event_for_calender_two_1.setBackgroundResource(0);
            event_for_calender_two_2.setBackgroundResource(0);
            event_for_calender_two_3.setBackgroundResource(0);
            event_for_calender_three_1.setBackgroundResource(0);
            event_for_calender_three_2.setBackgroundResource(0);
            event_for_calender_three_3.setBackgroundResource(0);
            event_for_calender_four_1.setBackgroundResource(0);
            event_for_calender_four_2.setBackgroundResource(0);
            event_for_calender_four_3.setBackgroundResource(0);
            event_for_calender_five_1.setBackgroundResource(0);
            event_for_calender_five_2.setBackgroundResource(0);
            event_for_calender_five_3.setBackgroundResource(0);
            event_for_calender_six_1.setBackgroundResource(0);
            event_for_calender_six_2.setBackgroundResource(0);
            event_for_calender_six_3.setBackgroundResource(0);
            event_for_calender_seven_1.setBackgroundResource(0);
            event_for_calender_seven_2.setBackgroundResource(0);
            event_for_calender_seven_3.setBackgroundResource(0);
            event_for_calender_eight_1.setBackgroundResource(0);
            event_for_calender_eight_2.setBackgroundResource(0);
            event_for_calender_eight_3.setBackgroundResource(0);
            event_for_calender_nine_1.setBackgroundResource(0);
            event_for_calender_nine_2.setBackgroundResource(0);
            event_for_calender_nine_3.setBackgroundResource(0);
            event_for_calender_ten_1.setBackgroundResource(0);
            event_for_calender_ten_2.setBackgroundResource(0);
            event_for_calender_ten_3.setBackgroundResource(0);
            event_for_calender_eleven_1.setBackgroundResource(0);
            event_for_calender_eleven_2.setBackgroundResource(0);
            event_for_calender_eleven_3.setBackgroundResource(0);
            event_for_calender_twelve_1.setBackgroundResource(0);
            event_for_calender_twelve_2.setBackgroundResource(0);
            event_for_calender_twelve_3.setBackgroundResource(0);
            event_for_calender_thirteen_1.setBackgroundResource(0);
            event_for_calender_thirteen_2.setBackgroundResource(0);
            event_for_calender_thirteen_3.setBackgroundResource(0);
            event_for_calender_fourteen_1.setBackgroundResource(0);
            event_for_calender_fourteen_2.setBackgroundResource(0);
            event_for_calender_fourteen_3.setBackgroundResource(0);
            event_for_calender_fifteen_1.setBackgroundResource(0);
            event_for_calender_fifteen_2.setBackgroundResource(0);
            event_for_calender_fifteen_3.setBackgroundResource(0);
            event_for_calender_sixteen_1.setBackgroundResource(0);
            event_for_calender_sixteen_2.setBackgroundResource(0);
            event_for_calender_sixteen_3.setBackgroundResource(0);
            event_for_calender_seventeen_1.setBackgroundResource(0);
            event_for_calender_seventeen_2.setBackgroundResource(0);
            event_for_calender_seventeen_3.setBackgroundResource(0);
            event_for_calender_eighteen_1.setBackgroundResource(0);
            event_for_calender_eighteen_2.setBackgroundResource(0);
            event_for_calender_eighteen_3.setBackgroundResource(0);
            event_for_calender_nineteen_1.setBackgroundResource(0);
            event_for_calender_nineteen_2.setBackgroundResource(0);
            event_for_calender_nineteen_3.setBackgroundResource(0);
            event_for_calender_twenty_1.setBackgroundResource(0);
            event_for_calender_twenty_2.setBackgroundResource(0);
            event_for_calender_twenty_3.setBackgroundResource(0);
            event_for_calender_twenty_one_1.setBackgroundResource(0);
            event_for_calender_twenty_one_2.setBackgroundResource(0);
            event_for_calender_twenty_one_3.setBackgroundResource(0);
            event_for_calender_twenty_two_1.setBackgroundResource(0);
            event_for_calender_twenty_two_2.setBackgroundResource(0);
            event_for_calender_twenty_two_3.setBackgroundResource(0);
            event_for_calender_twenty_three_1.setBackgroundResource(0);
            event_for_calender_twenty_three_2.setBackgroundResource(0);
            event_for_calender_twenty_three_3.setBackgroundResource(0);
            event_for_calender_twenty_four_1.setBackgroundResource(0);
            event_for_calender_twenty_four_2.setBackgroundResource(0);
            event_for_calender_twenty_four_3.setBackgroundResource(0);
            event_for_calender_twenty_five_1.setBackgroundResource(0);
            event_for_calender_twenty_five_2.setBackgroundResource(0);
            event_for_calender_twenty_five_3.setBackgroundResource(0);
            event_for_calender_twenty_six_1.setBackgroundResource(0);
            event_for_calender_twenty_six_2.setBackgroundResource(0);
            event_for_calender_twenty_six_3.setBackgroundResource(0);
            event_for_calender_twenty_seven_1.setBackgroundResource(0);
            event_for_calender_twenty_seven_2.setBackgroundResource(0);
            event_for_calender_twenty_seven_3.setBackgroundResource(0);
            event_for_calender_twenty_eight_1.setBackgroundResource(0);
            event_for_calender_twenty_eight_2.setBackgroundResource(0);
            event_for_calender_twenty_eight_3.setBackgroundResource(0);
            event_for_calender_twenty_nine_1.setBackgroundResource(0);
            event_for_calender_twenty_nine_2.setBackgroundResource(0);
            event_for_calender_twenty_nine_3.setBackgroundResource(0);
            event_for_calender_thirty_one_1.setBackgroundResource(0);
            event_for_calender_thirty_one_2.setBackgroundResource(0);
            event_for_calender_thirty_one_3.setBackgroundResource(0);
            event_for_calender_thirty_two_1.setBackgroundResource(0);
            event_for_calender_thirty_two_2.setBackgroundResource(0);
            event_for_calender_thirty_two_3.setBackgroundResource(0);
            event_for_calender_thirty_three_1.setBackgroundResource(0);
            event_for_calender_thirty_three_2.setBackgroundResource(0);
            event_for_calender_thirty_three_3.setBackgroundResource(0);
            event_for_calender_thirty_four_1.setBackgroundResource(0);
            event_for_calender_thirty_four_2.setBackgroundResource(0);
            event_for_calender_thirty_four_3.setBackgroundResource(0);
            event_for_calender_thirty_five_1.setBackgroundResource(0);
            event_for_calender_thirty_five_2.setBackgroundResource(0);
            event_for_calender_thirty_five_3.setBackgroundResource(0);
            event_for_calender_thirty_six_1.setBackgroundResource(0);
            event_for_calender_thirty_six_2.setBackgroundResource(0);
            event_for_calender_thirty_six_3.setBackgroundResource(0);
            event_for_calender_thirty_seven_1.setBackgroundResource(0);
            event_for_calender_thirty_seven_2.setBackgroundResource(0);
            event_for_calender_thirty_seven_3.setBackgroundResource(0);
        }
    }

    private void put_the_event(int day, Drawable color) {
        if (getActivity() != null) {
            View event_for_calender_one_1 = getActivity().findViewById(R.id.event_for_calender_one_1);
            View event_for_calender_one_2 = getActivity().findViewById(R.id.event_for_calender_one_2);
            View event_for_calender_one_3 = getActivity().findViewById(R.id.event_for_calender_one_3);
            View event_for_calender_two_1 = getActivity().findViewById(R.id.event_for_calender_two_1);
            View event_for_calender_two_2 = getActivity().findViewById(R.id.event_for_calender_two_2);
            View event_for_calender_two_3 = getActivity().findViewById(R.id.event_for_calender_two_3);
            View event_for_calender_three_1 = getActivity().findViewById(R.id.event_for_calender_three_1);
            View event_for_calender_three_2 = getActivity().findViewById(R.id.event_for_calender_three_2);
            View event_for_calender_three_3 = getActivity().findViewById(R.id.event_for_calender_three_3);
            View event_for_calender_four_1 = getActivity().findViewById(R.id.event_for_calender_four_1);
            View event_for_calender_four_2 = getActivity().findViewById(R.id.event_for_calender_four_2);
            View event_for_calender_four_3 = getActivity().findViewById(R.id.event_for_calender_four_3);
            View event_for_calender_five_1 = getActivity().findViewById(R.id.event_for_calender_five_1);
            View event_for_calender_five_2 = getActivity().findViewById(R.id.event_for_calender_five_2);
            View event_for_calender_five_3 = getActivity().findViewById(R.id.event_for_calender_five_3);
            View event_for_calender_six_1 = getActivity().findViewById(R.id.event_for_calender_six_1);
            View event_for_calender_six_2 = getActivity().findViewById(R.id.event_for_calender_six_2);
            View event_for_calender_six_3 = getActivity().findViewById(R.id.event_for_calender_six_3);
            View event_for_calender_seven_1 = getActivity().findViewById(R.id.event_for_calender_seven_1);
            View event_for_calender_seven_2 = getActivity().findViewById(R.id.event_for_calender_seven_2);
            View event_for_calender_seven_3 = getActivity().findViewById(R.id.event_for_calender_seven_3);
            View event_for_calender_eight_1 = getActivity().findViewById(R.id.event_for_calender_eight_1);
            View event_for_calender_eight_2 = getActivity().findViewById(R.id.event_for_calender_eight_2);
            View event_for_calender_eight_3 = getActivity().findViewById(R.id.event_for_calender_eight_3);
            View event_for_calender_nine_1 = getActivity().findViewById(R.id.event_for_calender_nine_1);
            View event_for_calender_nine_2 = getActivity().findViewById(R.id.event_for_calender_nine_2);
            View event_for_calender_nine_3 = getActivity().findViewById(R.id.event_for_calender_nine_3);
            View event_for_calender_ten_1 = getActivity().findViewById(R.id.event_for_calender_ten_1);
            View event_for_calender_ten_2 = getActivity().findViewById(R.id.event_for_calender_ten_2);
            View event_for_calender_ten_3 = getActivity().findViewById(R.id.event_for_calender_ten_3);
            View event_for_calender_eleven_1 = getActivity().findViewById(R.id.event_for_calender_eleven_1);
            View event_for_calender_eleven_2 = getActivity().findViewById(R.id.event_for_calender_eleven_2);
            View event_for_calender_eleven_3 = getActivity().findViewById(R.id.event_for_calender_eleven_3);
            View event_for_calender_twelve_1 = getActivity().findViewById(R.id.event_for_calender_twelve_1);
            View event_for_calender_twelve_2 = getActivity().findViewById(R.id.event_for_calender_twelve_2);
            View event_for_calender_twelve_3 = getActivity().findViewById(R.id.event_for_calender_twelve_3);
            View event_for_calender_thirteen_1 = getActivity().findViewById(R.id.event_for_calender_thirteen_1);
            View event_for_calender_thirteen_2 = getActivity().findViewById(R.id.event_for_calender_thirteen_2);
            View event_for_calender_thirteen_3 = getActivity().findViewById(R.id.event_for_calender_thirteen_3);
            View event_for_calender_fourteen_1 = getActivity().findViewById(R.id.event_for_calender_fourteen_1);
            View event_for_calender_fourteen_2 = getActivity().findViewById(R.id.event_for_calender_fourteen_2);
            View event_for_calender_fourteen_3 = getActivity().findViewById(R.id.event_for_calender_fourteen_3);
            View event_for_calender_fifteen_1 = getActivity().findViewById(R.id.event_for_calender_fifteen_1);
            View event_for_calender_fifteen_2 = getActivity().findViewById(R.id.event_for_calender_fifteen_2);
            View event_for_calender_fifteen_3 = getActivity().findViewById(R.id.event_for_calender_fifteen_3);
            View event_for_calender_sixteen_1 = getActivity().findViewById(R.id.event_for_calender_sixteen_1);
            View event_for_calender_sixteen_2 = getActivity().findViewById(R.id.event_for_calender_sixteen_2);
            View event_for_calender_sixteen_3 = getActivity().findViewById(R.id.event_for_calender_sixteen_3);
            View event_for_calender_seventeen_1 = getActivity().findViewById(R.id.event_for_calender_seventeen_1);
            View event_for_calender_seventeen_2 = getActivity().findViewById(R.id.event_for_calender_seventeen_2);
            View event_for_calender_seventeen_3 = getActivity().findViewById(R.id.event_for_calender_seventeen_3);
            View event_for_calender_eighteen_1 = getActivity().findViewById(R.id.event_for_calender_eighteen_1);
            View event_for_calender_eighteen_2 = getActivity().findViewById(R.id.event_for_calender_eighteen_2);
            View event_for_calender_eighteen_3 = getActivity().findViewById(R.id.event_for_calender_eighteen_3);
            View event_for_calender_nineteen_1 = getActivity().findViewById(R.id.event_for_calender_nineteen_1);
            View event_for_calender_nineteen_2 = getActivity().findViewById(R.id.event_for_calender_nineteen_2);
            View event_for_calender_nineteen_3 = getActivity().findViewById(R.id.event_for_calender_nineteen_3);
            View event_for_calender_twenty_1 = getActivity().findViewById(R.id.event_for_calender_twenty_1);
            View event_for_calender_twenty_2 = getActivity().findViewById(R.id.event_for_calender_twenty_2);
            View event_for_calender_twenty_3 = getActivity().findViewById(R.id.event_for_calender_twenty_3);
            View event_for_calender_twenty_one_1 = getActivity().findViewById(R.id.event_for_calender_twenty_one_1);
            View event_for_calender_twenty_one_2 = getActivity().findViewById(R.id.event_for_calender_twenty_one_2);
            View event_for_calender_twenty_one_3 = getActivity().findViewById(R.id.event_for_calender_twenty_one_3);
            View event_for_calender_twenty_two_1 = getActivity().findViewById(R.id.event_for_calender_twenty_two_1);
            View event_for_calender_twenty_two_2 = getActivity().findViewById(R.id.event_for_calender_twenty_two_2);
            View event_for_calender_twenty_two_3 = getActivity().findViewById(R.id.event_for_calender_twenty_two_3);
            View event_for_calender_twenty_three_1 = getActivity().findViewById(R.id.event_for_calender_twenty_three_1);
            View event_for_calender_twenty_three_2 = getActivity().findViewById(R.id.event_for_calender_twenty_three_2);
            View event_for_calender_twenty_three_3 = getActivity().findViewById(R.id.event_for_calender_twenty_three_3);
            View event_for_calender_twenty_four_1 = getActivity().findViewById(R.id.event_for_calender_twenty_four_1);
            View event_for_calender_twenty_four_2 = getActivity().findViewById(R.id.event_for_calender_twenty_four_2);
            View event_for_calender_twenty_four_3 = getActivity().findViewById(R.id.event_for_calender_twenty_four_3);
            View event_for_calender_twenty_five_1 = getActivity().findViewById(R.id.event_for_calender_twenty_five_1);
            View event_for_calender_twenty_five_2 = getActivity().findViewById(R.id.event_for_calender_twenty_five_2);
            View event_for_calender_twenty_five_3 = getActivity().findViewById(R.id.event_for_calender_twenty_five_3);
            View event_for_calender_twenty_six_1 = getActivity().findViewById(R.id.event_for_calender_twenty_six_1);
            View event_for_calender_twenty_six_2 = getActivity().findViewById(R.id.event_for_calender_twenty_six_2);
            View event_for_calender_twenty_six_3 = getActivity().findViewById(R.id.event_for_calender_twenty_six_3);
            View event_for_calender_twenty_seven_1 = getActivity().findViewById(R.id.event_for_calender_twenty_seven_1);
            View event_for_calender_twenty_seven_2 = getActivity().findViewById(R.id.event_for_calender_twenty_seven_2);
            View event_for_calender_twenty_seven_3 = getActivity().findViewById(R.id.event_for_calender_twenty_seven_3);
            View event_for_calender_twenty_eight_1 = getActivity().findViewById(R.id.event_for_calender_twenty_eight_1);
            View event_for_calender_twenty_eight_2 = getActivity().findViewById(R.id.event_for_calender_twenty_eight_2);
            View event_for_calender_twenty_eight_3 = getActivity().findViewById(R.id.event_for_calender_twenty_eight_3);
            View event_for_calender_twenty_nine_1 = getActivity().findViewById(R.id.event_for_calender_twenty_nine_1);
            View event_for_calender_twenty_nine_2 = getActivity().findViewById(R.id.event_for_calender_twenty_nine_2);
            View event_for_calender_twenty_nine_3 = getActivity().findViewById(R.id.event_for_calender_twenty_nine_3);
            View event_for_calender_thirty_1 = getActivity().findViewById(R.id.event_for_calender_thirty_1);
            View event_for_calender_thirty_2 = getActivity().findViewById(R.id.event_for_calender_thirty_2);
            View event_for_calender_thirty_3 = getActivity().findViewById(R.id.event_for_calender_thirty_3);
            View event_for_calender_thirty_one_1 = getActivity().findViewById(R.id.event_for_calender_thirty_one_1);
            View event_for_calender_thirty_one_2 = getActivity().findViewById(R.id.event_for_calender_thirty_one_2);
            View event_for_calender_thirty_one_3 = getActivity().findViewById(R.id.event_for_calender_thirty_one_3);
            View event_for_calender_thirty_two_1 = getActivity().findViewById(R.id.event_for_calender_thirty_two_1);
            View event_for_calender_thirty_two_2 = getActivity().findViewById(R.id.event_for_calender_thirty_two_2);
            View event_for_calender_thirty_two_3 = getActivity().findViewById(R.id.event_for_calender_thirty_two_3);
            View event_for_calender_thirty_three_1 = getActivity().findViewById(R.id.event_for_calender_thirty_three_1);
            View event_for_calender_thirty_three_2 = getActivity().findViewById(R.id.event_for_calender_thirty_three_2);
            View event_for_calender_thirty_three_3 = getActivity().findViewById(R.id.event_for_calender_thirty_three_3);
            View event_for_calender_thirty_four_1 = getActivity().findViewById(R.id.event_for_calender_thirty_four_1);
            View event_for_calender_thirty_four_2 = getActivity().findViewById(R.id.event_for_calender_thirty_four_2);
            View event_for_calender_thirty_four_3 = getActivity().findViewById(R.id.event_for_calender_thirty_four_3);
            View event_for_calender_thirty_five_1 = getActivity().findViewById(R.id.event_for_calender_thirty_five_1);
            View event_for_calender_thirty_five_2 = getActivity().findViewById(R.id.event_for_calender_thirty_five_2);
            View event_for_calender_thirty_five_3 = getActivity().findViewById(R.id.event_for_calender_thirty_five_3);
            View event_for_calender_thirty_six_1 = getActivity().findViewById(R.id.event_for_calender_thirty_six_1);
            View event_for_calender_thirty_six_2 = getActivity().findViewById(R.id.event_for_calender_thirty_six_2);
            View event_for_calender_thirty_six_3 = getActivity().findViewById(R.id.event_for_calender_thirty_six_3);
            View event_for_calender_thirty_seven_1 = getActivity().findViewById(R.id.event_for_calender_thirty_seven_1);
            View event_for_calender_thirty_seven_2 = getActivity().findViewById(R.id.event_for_calender_thirty_seven_2);
            View event_for_calender_thirty_seven_3 = getActivity().findViewById(R.id.event_for_calender_thirty_seven_3);
            if (!calender_button_showing_shadow_1.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_1.getText().toString()) == day) {
                if (event_for_calender_one_3.getBackground() == null) {
                    event_for_calender_one_3.setBackground(color);
                } else if (event_for_calender_one_1.getBackground() == null) {
                    event_for_calender_one_1.setBackground(color);
                } else if (event_for_calender_one_2.getBackground() == null) {
                    event_for_calender_one_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_2.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_2.getText().toString()) == day) {
                if (event_for_calender_two_3.getBackground() == null) {
                    event_for_calender_two_3.setBackground(color);
                } else if (event_for_calender_two_1.getBackground() == null) {
                    event_for_calender_two_1.setBackground(color);
                } else if (event_for_calender_two_2.getBackground() == null) {
                    event_for_calender_two_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_3.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_3.getText().toString()) == day) {
                if (event_for_calender_three_3.getBackground() == null) {
                    event_for_calender_three_3.setBackground(color);
                } else if (event_for_calender_three_1.getBackground() == null) {
                    event_for_calender_three_1.setBackground(color);
                } else if (event_for_calender_three_2.getBackground() == null) {
                    event_for_calender_three_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_4.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_4.getText().toString()) == day) {
                if (event_for_calender_four_3.getBackground() == null) {
                    event_for_calender_four_3.setBackground(color);
                } else if (event_for_calender_four_1.getBackground() == null) {
                    event_for_calender_four_1.setBackground(color);
                } else if (event_for_calender_four_2.getBackground() == null) {
                    event_for_calender_four_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_5.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_5.getText().toString()) == day) {
                if (event_for_calender_five_3.getBackground() == null) {
                    event_for_calender_five_3.setBackground(color);
                } else if (event_for_calender_five_1.getBackground() == null) {
                    event_for_calender_five_1.setBackground(color);
                } else if (event_for_calender_five_2.getBackground() == null) {
                    event_for_calender_five_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_6.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_6.getText().toString()) == day) {
                if (event_for_calender_six_3.getBackground() == null) {
                    event_for_calender_six_3.setBackground(color);
                } else if (event_for_calender_six_1.getBackground() == null) {
                    event_for_calender_six_1.setBackground(color);
                } else if (event_for_calender_six_2.getBackground() == null) {
                    event_for_calender_six_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_7.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_7.getText().toString()) == day) {
                if (event_for_calender_seven_3.getBackground() == null) {
                    event_for_calender_seven_3.setBackground(color);
                } else if (event_for_calender_seven_1.getBackground() == null) {
                    event_for_calender_seven_1.setBackground(color);
                } else if (event_for_calender_seven_2.getBackground() == null) {
                    event_for_calender_seven_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_8.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_8.getText().toString()) == day) {
                if (event_for_calender_eight_3.getBackground() == null) {
                    event_for_calender_eight_3.setBackground(color);
                } else if (event_for_calender_eight_1.getBackground() == null) {
                    event_for_calender_eight_1.setBackground(color);
                } else if (event_for_calender_eight_2.getBackground() == null) {
                    event_for_calender_eight_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_9.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_9.getText().toString()) == day) {
                if (event_for_calender_nine_3.getBackground() == null) {
                    event_for_calender_nine_3.setBackground(color);
                } else if (event_for_calender_nine_1.getBackground() == null) {
                    event_for_calender_nine_1.setBackground(color);
                } else if (event_for_calender_nine_2.getBackground() == null) {
                    event_for_calender_nine_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_10.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_10.getText().toString()) == day) {
                if (event_for_calender_ten_3.getBackground() == null) {
                    event_for_calender_ten_3.setBackground(color);
                } else if (event_for_calender_ten_1.getBackground() == null) {
                    event_for_calender_ten_1.setBackground(color);
                } else if (event_for_calender_ten_2.getBackground() == null) {
                    event_for_calender_ten_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_11.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_11.getText().toString()) == day) {
                if (event_for_calender_eleven_3.getBackground() == null) {
                    event_for_calender_eleven_3.setBackground(color);
                } else if (event_for_calender_eleven_1.getBackground() == null) {
                    event_for_calender_eleven_1.setBackground(color);
                } else if (event_for_calender_eleven_2.getBackground() == null) {
                    event_for_calender_eleven_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_12.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_12.getText().toString()) == day) {
                if (event_for_calender_twelve_3.getBackground() == null) {
                    event_for_calender_twelve_3.setBackground(color);
                } else if (event_for_calender_twelve_1.getBackground() == null) {
                    event_for_calender_twelve_1.setBackground(color);
                } else if (event_for_calender_twelve_2.getBackground() == null) {
                    event_for_calender_twelve_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_13.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_13.getText().toString()) == day) {
                if (event_for_calender_thirteen_3.getBackground() == null) {
                    event_for_calender_thirteen_3.setBackground(color);
                } else if (event_for_calender_thirteen_1.getBackground() == null) {
                    event_for_calender_thirteen_1.setBackground(color);
                } else if (event_for_calender_thirteen_2.getBackground() == null) {
                    event_for_calender_thirteen_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_14.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_14.getText().toString()) == day) {
                if (event_for_calender_fourteen_3.getBackground() == null) {
                    event_for_calender_fourteen_3.setBackground(color);
                } else if (event_for_calender_fourteen_1.getBackground() == null) {
                    event_for_calender_fourteen_1.setBackground(color);
                } else if (event_for_calender_fourteen_2.getBackground() == null) {
                    event_for_calender_fourteen_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_15.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_15.getText().toString()) == day) {
                if (event_for_calender_fifteen_3.getBackground() == null) {
                    event_for_calender_fifteen_3.setBackground(color);
                } else if (event_for_calender_fifteen_1.getBackground() == null) {
                    event_for_calender_fifteen_1.setBackground(color);
                } else if (event_for_calender_fifteen_2.getBackground() == null) {
                    event_for_calender_fifteen_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_16.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_16.getText().toString()) == day) {
                if (event_for_calender_sixteen_3.getBackground() == null) {
                    event_for_calender_sixteen_3.setBackground(color);
                } else if (event_for_calender_sixteen_1.getBackground() == null) {
                    event_for_calender_sixteen_1.setBackground(color);
                } else if (event_for_calender_sixteen_2.getBackground() == null) {
                    event_for_calender_sixteen_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_17.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_17.getText().toString()) == day) {
                if (event_for_calender_seventeen_3.getBackground() == null) {
                    event_for_calender_seventeen_3.setBackground(color);
                } else if (event_for_calender_seventeen_1.getBackground() == null) {
                    event_for_calender_seventeen_1.setBackground(color);
                } else if (event_for_calender_seventeen_2.getBackground() == null) {
                    event_for_calender_seventeen_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_18.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_18.getText().toString()) == day) {
                if (event_for_calender_eighteen_3.getBackground() == null) {
                    event_for_calender_eighteen_3.setBackground(color);
                } else if (event_for_calender_eighteen_1.getBackground() == null) {
                    event_for_calender_eighteen_1.setBackground(color);
                } else if (event_for_calender_eighteen_2.getBackground() == null) {
                    event_for_calender_eighteen_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_19.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_19.getText().toString()) == day) {
                if (event_for_calender_nineteen_3.getBackground() == null) {
                    event_for_calender_nineteen_3.setBackground(color);
                } else if (event_for_calender_nineteen_1.getBackground() == null) {
                    event_for_calender_nineteen_1.setBackground(color);
                } else if (event_for_calender_nineteen_2.getBackground() == null) {
                    event_for_calender_nineteen_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_20.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_20.getText().toString()) == day) {
                if (event_for_calender_twenty_3.getBackground() == null) {
                    event_for_calender_twenty_3.setBackground(color);
                } else if (event_for_calender_twenty_1.getBackground() == null) {
                    event_for_calender_twenty_1.setBackground(color);
                } else if (event_for_calender_twenty_2.getBackground() == null) {
                    event_for_calender_twenty_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_21.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_21.getText().toString()) == day) {
                if (event_for_calender_twenty_one_3.getBackground() == null) {
                    event_for_calender_twenty_one_3.setBackground(color);
                } else if (event_for_calender_twenty_one_1.getBackground() == null) {
                    event_for_calender_twenty_one_1.setBackground(color);
                } else if (event_for_calender_twenty_one_2.getBackground() == null) {
                    event_for_calender_twenty_one_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_22.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_22.getText().toString()) == day) {
                if (event_for_calender_twenty_two_3.getBackground() == null) {
                    event_for_calender_twenty_two_3.setBackground(color);
                } else if (event_for_calender_twenty_two_1.getBackground() == null) {
                    event_for_calender_twenty_two_1.setBackground(color);
                } else if (event_for_calender_twenty_two_2.getBackground() == null) {
                    event_for_calender_twenty_two_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_23.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_23.getText().toString()) == day) {
                if (event_for_calender_twenty_three_3.getBackground() == null) {
                    event_for_calender_twenty_three_3.setBackground(color);
                } else if (event_for_calender_twenty_three_1.getBackground() == null) {
                    event_for_calender_twenty_three_1.setBackground(color);
                } else if (event_for_calender_twenty_three_2.getBackground() == null) {
                    event_for_calender_twenty_three_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_24.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_24.getText().toString()) == day) {
                if (event_for_calender_twenty_four_3.getBackground() == null) {
                    event_for_calender_twenty_four_3.setBackground(color);
                } else if (event_for_calender_twenty_four_1.getBackground() == null) {
                    event_for_calender_twenty_four_1.setBackground(color);
                } else if (event_for_calender_twenty_four_2.getBackground() == null) {
                    event_for_calender_twenty_four_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_25.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_25.getText().toString()) == day) {
                if (event_for_calender_twenty_five_3.getBackground() == null) {
                    event_for_calender_twenty_five_3.setBackground(color);
                } else if (event_for_calender_twenty_five_1.getBackground() == null) {
                    event_for_calender_twenty_five_1.setBackground(color);
                } else if (event_for_calender_twenty_five_2.getBackground() == null) {
                    event_for_calender_twenty_five_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_26.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_26.getText().toString()) == day) {
                if (event_for_calender_twenty_six_3.getBackground() == null) {
                    event_for_calender_twenty_six_3.setBackground(color);
                } else if (event_for_calender_twenty_six_1.getBackground() == null) {
                    event_for_calender_twenty_six_1.setBackground(color);
                } else if (event_for_calender_twenty_six_2.getBackground() == null) {
                    event_for_calender_twenty_six_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_27.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_27.getText().toString()) == day) {
                if (event_for_calender_twenty_seven_3.getBackground() == null) {
                    event_for_calender_twenty_seven_3.setBackground(color);
                } else if (event_for_calender_twenty_seven_1.getBackground() == null) {
                    event_for_calender_twenty_seven_1.setBackground(color);
                } else if (event_for_calender_twenty_seven_2.getBackground() == null) {
                    event_for_calender_twenty_seven_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_28.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_28.getText().toString()) == day) {
                if (event_for_calender_twenty_eight_3.getBackground() == null) {
                    event_for_calender_twenty_eight_3.setBackground(color);
                } else if (event_for_calender_twenty_eight_1.getBackground() == null) {
                    event_for_calender_twenty_eight_1.setBackground(color);
                } else if (event_for_calender_twenty_eight_2.getBackground() == null) {
                    event_for_calender_twenty_eight_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_29.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_29.getText().toString()) == day) {
                if (event_for_calender_twenty_nine_3.getBackground() == null) {
                    event_for_calender_twenty_nine_3.setBackground(color);
                } else if (event_for_calender_twenty_nine_1.getBackground() == null) {
                    event_for_calender_twenty_nine_1.setBackground(color);
                } else if (event_for_calender_twenty_nine_2.getBackground() == null) {
                    event_for_calender_twenty_nine_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_30.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_30.getText().toString()) == day) {
                if (event_for_calender_thirty_3.getBackground() == null) {
                    event_for_calender_thirty_3.setBackground(color);
                } else if (event_for_calender_thirty_1.getBackground() == null) {
                    event_for_calender_thirty_1.setBackground(color);
                } else if (event_for_calender_thirty_2.getBackground() == null) {
                    event_for_calender_thirty_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_31.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_31.getText().toString()) == day) {
                if (event_for_calender_thirty_one_3.getBackground() == null) {
                    event_for_calender_thirty_one_3.setBackground(color);
                } else if (event_for_calender_thirty_one_1.getBackground() == null) {
                    event_for_calender_thirty_one_1.setBackground(color);
                } else if (event_for_calender_thirty_one_2.getBackground() == null) {
                    event_for_calender_thirty_one_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_32.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_32.getText().toString()) == day) {
                if (event_for_calender_thirty_two_3.getBackground() == null) {
                    event_for_calender_thirty_two_3.setBackground(color);
                } else if (event_for_calender_thirty_two_1.getBackground() == null) {
                    event_for_calender_thirty_two_1.setBackground(color);
                } else if (event_for_calender_thirty_two_2.getBackground() == null) {
                    event_for_calender_thirty_two_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_33.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_33.getText().toString()) == day) {
                if (event_for_calender_thirty_three_3.getBackground() == null) {
                    event_for_calender_thirty_three_3.setBackground(color);
                } else if (event_for_calender_thirty_three_1.getBackground() == null) {
                    event_for_calender_thirty_three_1.setBackground(color);
                } else if (event_for_calender_thirty_three_2.getBackground() == null) {
                    event_for_calender_thirty_three_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_34.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_34.getText().toString()) == day) {
                if (event_for_calender_thirty_four_3.getBackground() == null) {
                    event_for_calender_thirty_four_3.setBackground(color);
                } else if (event_for_calender_thirty_four_1.getBackground() == null) {
                    event_for_calender_thirty_four_1.setBackground(color);
                } else if (event_for_calender_thirty_four_2.getBackground() == null) {
                    event_for_calender_thirty_four_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_35.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_35.getText().toString()) == day) {
                if (event_for_calender_thirty_five_3.getBackground() == null) {
                    event_for_calender_thirty_five_3.setBackground(color);
                } else if (event_for_calender_thirty_five_1.getBackground() == null) {
                    event_for_calender_thirty_five_1.setBackground(color);
                } else if (event_for_calender_thirty_five_2.getBackground() == null) {
                    event_for_calender_thirty_five_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_36.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_36.getText().toString()) == day) {
                if (event_for_calender_thirty_six_3.getBackground() == null) {
                    event_for_calender_thirty_six_3.setBackground(color);
                } else if (event_for_calender_thirty_six_1.getBackground() == null) {
                    event_for_calender_thirty_six_1.setBackground(color);
                } else if (event_for_calender_thirty_six_2.getBackground() == null) {
                    event_for_calender_thirty_six_2.setBackground(color);
                }
            } else if (!calender_button_showing_shadow_37.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_37.getText().toString()) == day) {
                if (event_for_calender_thirty_seven_3.getBackground() == null) {
                    event_for_calender_thirty_seven_3.setBackground(color);
                } else if (event_for_calender_thirty_seven_1.getBackground() == null) {
                    event_for_calender_thirty_seven_1.setBackground(color);
                } else if (event_for_calender_thirty_seven_2.getBackground() == null) {
                    event_for_calender_thirty_seven_2.setBackground(color);
                }
            }
        }
    }

    private void set_the_colors() {
        if (getActivity() != null) {
            color_black = getActivity().getDrawable(R.drawable.z_black);
            color_blue = getActivity().getDrawable(R.drawable.z_blue);
            color_brown = getActivity().getDrawable(R.drawable.z_brown);
            color_cyan = getActivity().getDrawable(R.drawable.z_cyan);
            color_dark_green = getActivity().getDrawable(R.drawable.z_dark_green);
            color_green = getActivity().getDrawable(R.drawable.z_green);
            color_lime = getActivity().getDrawable(R.drawable.z_lime);
            color_magenta = getActivity().getDrawable(R.drawable.z_magenta);
            color_navy = getActivity().getDrawable(R.drawable.z_navy);
            color_orange = getActivity().getDrawable(R.drawable.z_orange);
            color_pink = getActivity().getDrawable(R.drawable.z_pink);
            color_purple = getActivity().getDrawable(R.drawable.z_purple);
            color_red = getActivity().getDrawable(R.drawable.z_red);
            color_yellow = getActivity().getDrawable(R.drawable.z_yellow);
        }
    }

    private void set_the_line_chart() {
        if (getActivity() != null) {
            LineChart line_chart_for_streak = getActivity().findViewById(R.id.line_chart_for_streak);
            line_chart_for_streak.getXAxis().setDrawGridLines(false);
            line_chart_for_streak.getAxisLeft().setDrawGridLines(true);
            line_chart_for_streak.getAxisRight().setDrawGridLines(false);
            XAxis xAxis = line_chart_for_streak.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            line_chart_for_streak.getDescription().setText("Streak length in days");
            line_chart_for_streak.getAxisRight().setEnabled(false);
            //line_chart_for_streak.getXAxis().setEnabled(false);
            line_chart_for_streak.setScaleEnabled(false);
            line_chart_for_streak.getLegend().setEnabled(false);
            line_chart_for_streak.getAxisLeft().setTextColor(Color.parseColor("#607D8B"));
            line_chart_for_streak.getAxisLeft().setAxisLineColor(Color.parseColor("#607D8B"));
            line_chart_for_streak.getAxisLeft().setTextSize(17);
            line_chart_for_streak.getAxisLeft().setAxisMinimum(0);
            line_chart_for_streak.getAxisLeft().setAxisMaximum(37);
            line_chart_for_streak.getAxisLeft().setDrawAxisLine(false);
            line_chart_for_streak.getXAxis().setEnabled(false);
            line_chart_for_streak.getAxisLeft().setLabelCount(5, true);
            ArrayList<Entry> y_values = new ArrayList<>();
            y_values.add(new Entry(0, 10));
            y_values.add(new Entry(1, 20));
            y_values.add(new Entry(2, 8));
            y_values.add(new Entry(3, 26));
            y_values.add(new Entry(4, 37));
            line_chart_for_streak.getXAxis().setAxisMinimum(-0.2f);
            float rangeHigh = 10;
            float rangeLow = 0;
            float increment = (rangeHigh - rangeLow) / 20;
            float metricLine = rangeLow;

            for (int i = 0; i < 20; i++) {
                LimitLine llRange = new LimitLine(metricLine, "");
                llRange.setLineColor(Color.parseColor("#D4DCDF"));
                llRange.setLineWidth(10f);
                line_chart_for_streak.getAxisLeft().addLimitLine(llRange);
                metricLine = metricLine + increment;
            }
            line_chart_for_streak.getAxisLeft().setDrawLimitLinesBehindData(true);
            xAxis.setCenterAxisLabels(true);
            LineDataSet lineDataSet = new LineDataSet(y_values, "data");
            lineDataSet.setColor(Color.parseColor("#607D8B"));
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(lineDataSet);
            LineData data = new LineData(dataSets);
            line_chart_for_streak.setData(data);
            line_chart_for_streak.getData().setHighlightEnabled(false);
            lineDataSet.setLineWidth(3.5f);
            lineDataSet.setCircleRadius(8f);
            lineDataSet.setCircleHoleRadius(3.5f);
            lineDataSet.setCircleHoleColor(Color.WHITE);
            lineDataSet.setCircleColor(Color.parseColor("#B3607D8B"));
            lineDataSet.setDrawValues(false);
        }
    }
}
