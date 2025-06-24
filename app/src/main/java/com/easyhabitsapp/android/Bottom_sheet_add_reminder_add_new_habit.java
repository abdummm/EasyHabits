package com.easyhabitsapp.android;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.nex3z.togglebuttongroup.button.CircularToggle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Bottom_sheet_add_reminder_add_new_habit extends BottomSheetDialogFragment {
    private String mode;
    private String color;
    private ArrayList<String> days;
    private long time;
    private reminder_was_just_set reminder_was_just_set_listen;
    private done_clicked done_clicked_listen;
    private int position;

    public Bottom_sheet_add_reminder_add_new_habit(String color) {
        this.mode = "new";
        this.color = color;
    }

    public Bottom_sheet_add_reminder_add_new_habit(String color, ArrayList<String> days, long time,int position) {
        this.mode = "edit";
        this.color = color;
        this.days = days;
        this.time = time;
        this.position = position;
    }

    public void set_share_clicked_listen(reminder_was_just_set listen) {
        reminder_was_just_set_listen = listen;
    }

    public interface reminder_was_just_set {
        void remider_added(long time, ArrayList<String> days);
    }

    public void set_done_clicked_edit(done_clicked listen) {
        done_clicked_listen = listen;
    }

    public interface done_clicked {
        void done_was_just_clicked(long time, ArrayList<String> days,int position);
    }

    private View mview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_add_reminder, container, false);
        mview = view;
        set_first_day_of_week_specific_days_per_week();
        set_color();
        cancel_button_listen();
        done_button_listen();
        set_time_picker();
        if (mode.equals("new")) {
            set_all_checked();
        } else if (mode.equals("edit")) {
            set_checked_and_time_for_edit();
        }
        return view;
    }

    private void set_first_day_of_week_specific_days_per_week() {
        CircularToggle day_1_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_1_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_2_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_2_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_3_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_3_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_4_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_4_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_5_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_5_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_6_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_6_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_7_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_7_choose_days_of_week_reminder_bottom_sheet);
        Calendar calendar = Calendar.getInstance();
        /*if (calendar.getFirstDayOfWeek() == Calendar.MONDAY) {
            day_1_choose_days_of_week_reminder_bottom_sheet.setText("M");
            day_2_choose_days_of_week_reminder_bottom_sheet.setText("T");
            day_3_choose_days_of_week_reminder_bottom_sheet.setText("W");
            day_4_choose_days_of_week_reminder_bottom_sheet.setText("T");
            day_5_choose_days_of_week_reminder_bottom_sheet.setText("F");
            day_6_choose_days_of_week_reminder_bottom_sheet.setText("S");
            day_7_choose_days_of_week_reminder_bottom_sheet.setText("S");
        } else if (calendar.getFirstDayOfWeek() == Calendar.FRIDAY) {
            day_1_choose_days_of_week_reminder_bottom_sheet.setText("F");
            day_2_choose_days_of_week_reminder_bottom_sheet.setText("S");
            day_3_choose_days_of_week_reminder_bottom_sheet.setText("S");
            day_4_choose_days_of_week_reminder_bottom_sheet.setText("M");
            day_5_choose_days_of_week_reminder_bottom_sheet.setText("T");
            day_6_choose_days_of_week_reminder_bottom_sheet.setText("W");
            day_7_choose_days_of_week_reminder_bottom_sheet.setText("T");
        } else if (calendar.getFirstDayOfWeek() == Calendar.SATURDAY) {
            day_1_choose_days_of_week_reminder_bottom_sheet.setText("S");
            day_2_choose_days_of_week_reminder_bottom_sheet.setText("S");
            day_3_choose_days_of_week_reminder_bottom_sheet.setText("M");
            day_4_choose_days_of_week_reminder_bottom_sheet.setText("T");
            day_5_choose_days_of_week_reminder_bottom_sheet.setText("W");
            day_6_choose_days_of_week_reminder_bottom_sheet.setText("T");
            day_7_choose_days_of_week_reminder_bottom_sheet.setText("F");
        } else *//*if (calendar.getFirstDayOfWeek() == Calendar.SUNDAY)*//* {
            day_1_choose_days_of_week_reminder_bottom_sheet.setText("S");
            day_2_choose_days_of_week_reminder_bottom_sheet.setText("M");
            day_3_choose_days_of_week_reminder_bottom_sheet.setText("T");
            day_4_choose_days_of_week_reminder_bottom_sheet.setText("W");
            day_5_choose_days_of_week_reminder_bottom_sheet.setText("T");
            day_6_choose_days_of_week_reminder_bottom_sheet.setText("F");
            day_7_choose_days_of_week_reminder_bottom_sheet.setText("S");
        }*/
        day_1_choose_days_of_week_reminder_bottom_sheet.setText("M");
        day_2_choose_days_of_week_reminder_bottom_sheet.setText("T");
        day_3_choose_days_of_week_reminder_bottom_sheet.setText("W");
        day_4_choose_days_of_week_reminder_bottom_sheet.setText("T");
        day_5_choose_days_of_week_reminder_bottom_sheet.setText("F");
        day_6_choose_days_of_week_reminder_bottom_sheet.setText("S");
        day_7_choose_days_of_week_reminder_bottom_sheet.setText("S");
    }

    /*private void prevent_dragging() {
        this.setCancelable(false);
    }*/

    private void set_time_picker() {
        NumberPicker hour_date_picker_add_reminder = mview.findViewById(R.id.hour_date_picker_add_reminder);
        NumberPicker minute_date_picker_add_reminder = mview.findViewById(R.id.minute_date_picker_add_reminder);
        NumberPicker am_pm_picker_add_reminder = mview.findViewById(R.id.am_pm_picker_add_reminder);
        hour_date_picker_add_reminder.setMinValue(1);
        hour_date_picker_add_reminder.setMaxValue(12);
        hour_date_picker_add_reminder.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%02d", value);
            }
        });
        minute_date_picker_add_reminder.setMinValue(0);
        minute_date_picker_add_reminder.setMaxValue(59);
        minute_date_picker_add_reminder.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%02d", value);
            }
        });
        am_pm_picker_add_reminder.setMinValue(0);
        am_pm_picker_add_reminder.setMaxValue(1);
        am_pm_picker_add_reminder.setDisplayedValues(new String[]{"Am", "Pm"});
        hour_date_picker_add_reminder.setWrapSelectorWheel(false);
        minute_date_picker_add_reminder.setWrapSelectorWheel(false);
        am_pm_picker_add_reminder.setWrapSelectorWheel(false);
        am_pm_picker_add_reminder.setValue(1);
        hour_date_picker_add_reminder.setValue(8);
    }

    private void set_color() {
        CircularToggle day_1_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_1_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_2_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_2_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_3_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_3_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_4_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_4_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_5_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_5_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_6_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_6_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_7_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_7_choose_days_of_week_reminder_bottom_sheet);
        day_1_choose_days_of_week_reminder_bottom_sheet.setMarkerColor(Color.parseColor(color));
        day_2_choose_days_of_week_reminder_bottom_sheet.setMarkerColor(Color.parseColor(color));
        day_3_choose_days_of_week_reminder_bottom_sheet.setMarkerColor(Color.parseColor(color));
        day_4_choose_days_of_week_reminder_bottom_sheet.setMarkerColor(Color.parseColor(color));
        day_5_choose_days_of_week_reminder_bottom_sheet.setMarkerColor(Color.parseColor(color));
        day_6_choose_days_of_week_reminder_bottom_sheet.setMarkerColor(Color.parseColor(color));
        day_7_choose_days_of_week_reminder_bottom_sheet.setMarkerColor(Color.parseColor(color));
    }

    private void set_all_checked() {
        CircularToggle day_1_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_1_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_2_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_2_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_3_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_3_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_4_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_4_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_5_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_5_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_6_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_6_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_7_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_7_choose_days_of_week_reminder_bottom_sheet);
        day_1_choose_days_of_week_reminder_bottom_sheet.setChecked(true);
        day_2_choose_days_of_week_reminder_bottom_sheet.setChecked(true);
        day_3_choose_days_of_week_reminder_bottom_sheet.setChecked(true);
        day_4_choose_days_of_week_reminder_bottom_sheet.setChecked(true);
        day_5_choose_days_of_week_reminder_bottom_sheet.setChecked(true);
        day_6_choose_days_of_week_reminder_bottom_sheet.setChecked(true);
        day_7_choose_days_of_week_reminder_bottom_sheet.setChecked(true);
    }

    private void cancel_button_listen() {
        Button dismiss_bottom_sheet_add_reminder = mview.findViewById(R.id.dismiss_bottom_sheet_add_reminder);
        dismiss_bottom_sheet_add_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void done_button_listen() {
        NumberPicker hour_date_picker_add_reminder = mview.findViewById(R.id.hour_date_picker_add_reminder);
        NumberPicker minute_date_picker_add_reminder = mview.findViewById(R.id.minute_date_picker_add_reminder);
        NumberPicker am_pm_picker_add_reminder = mview.findViewById(R.id.am_pm_picker_add_reminder);
        Button ok_bottom_sheet_add_reminder = mview.findViewById(R.id.ok_bottom_sheet_add_reminder);
        ok_bottom_sheet_add_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (return_the_selected_days().isEmpty()) {
                    Toast.makeText(getContext(), "Please select at least 1 day for the notification to appear on", Toast.LENGTH_SHORT).show();
                } else {
                    long time = 0;
                    int hour = hour_date_picker_add_reminder.getValue();
                    int minute = minute_date_picker_add_reminder.getValue();
                    if (am_pm_picker_add_reminder.getValue() == 1) {
                        time = time + TimeUnit.HOURS.toMillis(12);
                    }
                    if (hour == 12) {
                        time = time + TimeUnit.MINUTES.toMillis(minute);
                    } else {
                        time = time + TimeUnit.HOURS.toMillis(hour);
                        time = time + TimeUnit.MINUTES.toMillis(minute);
                    }
                    if(mode.equals("new")) {
                        reminder_was_just_set_listen.remider_added(time, return_the_selected_days());
                    } else if(mode.equals("edit")){
                        done_clicked_listen.done_was_just_clicked(time,return_the_selected_days(),position);
                    }
                    dismiss();
                }
            }
        });
    }

    private ArrayList<String> return_the_selected_days() {
        CircularToggle day_1_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_1_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_2_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_2_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_3_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_3_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_4_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_4_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_5_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_5_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_6_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_6_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_7_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_7_choose_days_of_week_reminder_bottom_sheet);
        Calendar calendar = Calendar.getInstance();
        ArrayList<String> days = new ArrayList<>();
        /*if (calendar.getFirstDayOfWeek() == Calendar.MONDAY) {
            if(day_1_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Monday");
            }
            if(day_2_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Tuesday");
            }
            if(day_3_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Wednesday");
            }
            if(day_4_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Thursday");
            }
            if(day_5_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Friday");
            }
            if(day_6_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Saturday");
            }
            if(day_7_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Sunday");
            }
        } else if (calendar.getFirstDayOfWeek() == Calendar.FRIDAY) {
            if(day_1_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Friday");
            }
            if(day_2_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Saturday");
            }
            if(day_3_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Sunday");
            }
            if(day_4_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Monday");
            }
            if(day_5_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Tuesday");
            }
            if(day_6_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Wednesday");
            }
            if(day_7_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Thursday");
            }
        } else if (calendar.getFirstDayOfWeek() == Calendar.SATURDAY) {
            if(day_1_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Saturday");
            }
            if(day_2_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Sunday");
            }
            if(day_3_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Monday");
            }
            if(day_4_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Tuesday");
            }
            if(day_5_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Wednesday");
            }
            if(day_6_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Thursday");
            }
            if(day_7_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Friday");
            }
        } else *//*if (calendar.getFirstDayOfWeek() == Calendar.SUNDAY)*//* {
            if(day_1_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Sunday");
            }
            if(day_2_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Monday");
            }
            if(day_3_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Tuesday");
            }
            if(day_4_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Wednesday");
            }
            if(day_5_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Thursday");
            }
            if(day_6_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Friday");
            }
            if(day_7_choose_days_of_week_reminder_bottom_sheet.isChecked()){
                days.add("Saturday");
            }
        }*/
        if (day_1_choose_days_of_week_reminder_bottom_sheet.isChecked()) {
            days.add("Monday");
        }
        if (day_2_choose_days_of_week_reminder_bottom_sheet.isChecked()) {
            days.add("Tuesday");
        }
        if (day_3_choose_days_of_week_reminder_bottom_sheet.isChecked()) {
            days.add("Wednesday");
        }
        if (day_4_choose_days_of_week_reminder_bottom_sheet.isChecked()) {
            days.add("Thursday");
        }
        if (day_5_choose_days_of_week_reminder_bottom_sheet.isChecked()) {
            days.add("Friday");
        }
        if (day_6_choose_days_of_week_reminder_bottom_sheet.isChecked()) {
            days.add("Saturday");
        }
        if (day_7_choose_days_of_week_reminder_bottom_sheet.isChecked()) {
            days.add("Sunday");
        }
        return days;
    }

    private void set_checked_and_time_for_edit() {
        CircularToggle day_1_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_1_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_2_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_2_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_3_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_3_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_4_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_4_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_5_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_5_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_6_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_6_choose_days_of_week_reminder_bottom_sheet);
        CircularToggle day_7_choose_days_of_week_reminder_bottom_sheet = mview.findViewById(R.id.day_7_choose_days_of_week_reminder_bottom_sheet);
        NumberPicker hour_date_picker_add_reminder = mview.findViewById(R.id.hour_date_picker_add_reminder);
        NumberPicker minute_date_picker_add_reminder = mview.findViewById(R.id.minute_date_picker_add_reminder);
        NumberPicker am_pm_picker_add_reminder = mview.findViewById(R.id.am_pm_picker_add_reminder);
        /*if (calendar.getFirstDayOfWeek() == Calendar.MONDAY) {
            if(days.contains("Monday")){

            }
            if(days.contains("Tuesday")){

            }
            if(days.contains("Wednesday")){

            }
            if(days.contains("Thursday")){

            }
            if(days.contains("Friday")){

            }
            if(days.contains("Saturday")){

            }
            if(days.contains("Sunday")){

            }
        } else if (calendar.getFirstDayOfWeek() == Calendar.FRIDAY) {
            if(days.contains("Friday")){

            }
            if(days.contains("Saturday")){

            }
            if(days.contains("Sunday")){

            }
            if(days.contains("Monday")){

            }
            if(days.contains("Tuesday")){

            }
            if(days.contains("Wednesday")){

            }
            if(days.contains("Thursday")){

            }
        } else if (calendar.getFirstDayOfWeek() == Calendar.SATURDAY) {
            if(days.contains("Saturday")){

            }
            if(days.contains("Sunday")){

            }
            if(days.contains("Monday")){

            }
            if(days.contains("Tuesday")){

            }
            if(days.contains("Wednesday")){

            }
            if(days.contains("Thursday")){

            }
            if(days.contains("Friday")){

            }
        } else *//*if (calendar.getFirstDayOfWeek() == Calendar.SUNDAY)*//* {
            if(days.contains("Sunday")){

            }
            if(days.contains("Monday")){

            }
            if(days.contains("Tuesday")){

            }
            if(days.contains("Wednesday")){

            }
            if(days.contains("Thursday")){

            }
            if(days.contains("Friday")){

            }
            if(days.contains("Saturday")){

            }
        }*/
        if (days.contains("Monday")) {
            day_1_choose_days_of_week_reminder_bottom_sheet.setChecked(true);
        }
        if (days.contains("Tuesday")) {
            day_2_choose_days_of_week_reminder_bottom_sheet.setChecked(true);
        }
        if (days.contains("Wednesday")) {
            day_3_choose_days_of_week_reminder_bottom_sheet.setChecked(true);
        }
        if (days.contains("Thursday")) {
            day_4_choose_days_of_week_reminder_bottom_sheet.setChecked(true);
        }
        if (days.contains("Friday")) {
            day_5_choose_days_of_week_reminder_bottom_sheet.setChecked(true);
        }
        if (days.contains("Saturday")) {
            day_6_choose_days_of_week_reminder_bottom_sheet.setChecked(true);
        }
        if (days.contains("Sunday")) {
            day_7_choose_days_of_week_reminder_bottom_sheet.setChecked(true);
        }
        int minutes = (int) ((time / (1000 * 60)) % 60);
        int hours = (int) ((time / (1000 * 60 * 60)) % 24);
        if (hours < 12) {
            am_pm_picker_add_reminder.setValue(0);
            minute_date_picker_add_reminder.setValue(minutes);
            if (hours == 0) {
                hour_date_picker_add_reminder.setValue(12);
            } else {
                hour_date_picker_add_reminder.setValue(hours);
            }
        } else {
            am_pm_picker_add_reminder.setValue(1);
            minute_date_picker_add_reminder.setValue(minutes);
            hours = hours-12;
            if (hours == 0) {
                hour_date_picker_add_reminder.setValue(12);
            } else {
                hour_date_picker_add_reminder.setValue(hours);
            }
        }
    }
}
