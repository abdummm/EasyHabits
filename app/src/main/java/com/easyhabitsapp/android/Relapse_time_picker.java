package com.easyhabitsapp.android;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class Relapse_time_picker extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), R.style.DialogTheme, this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        final_time(hourOfDay, minute);
    }

    private void final_time(int hour, int minute) {
        if (getActivity() != null) {
            final Calendar c = Calendar.getInstance();
            int current_hour = c.get(Calendar.HOUR_OF_DAY);
            int current_minute = c.get(Calendar.MINUTE);
            int day = c.get(Calendar.DAY_OF_MONTH);
            if (get_day() == day) {
                if (hour > current_hour) {
                    Toast toast = Toast.makeText(getContext(), "Can't be greater than current time", Toast.LENGTH_LONG);
                    toast.show();
                    ConstraintLayout constraintLayout = getActivity().findViewById(R.id.button_fragment);
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(R.id.Restart_streak_for_switch_textview, ConstraintSet.TOP, R.id.choose_date_for_streak_button, ConstraintSet.BOTTOM, dip_to_pixels(25));
                    constraintSet.applyTo(constraintLayout);
                } else if (current_hour == hour && minute > current_minute) {
                    Toast toast = Toast.makeText(getContext(), "Can't be greater than current time", Toast.LENGTH_LONG);
                    toast.show();
                    ConstraintLayout constraintLayout = getActivity().findViewById(R.id.button_fragment);
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(R.id.Restart_streak_for_switch_textview, ConstraintSet.TOP, R.id.choose_date_for_streak_button, ConstraintSet.BOTTOM, dip_to_pixels(25));
                    constraintSet.applyTo(constraintLayout);
                } else {
                    if (getActivity() != null) {
                        TextView timer_to_restart_streak_textview = getActivity().findViewById(R.id.timer_to_restart_streak_textview);
                        String text_already_there = timer_to_restart_streak_textview.getText().toString();
                        timer_to_restart_streak_textview.setText(Simplify_the_time.getting_the_time(String.valueOf(minute).concat(":").concat(String.valueOf(hour)).concat("_").concat(text_already_there)));
                        set_the_constraints();
                        Simplify_the_time.save_the_milli(String.valueOf(minute).concat(":").concat(String.valueOf(hour)).concat("_").concat(text_already_there), getActivity());
                    }
                }
            } else {
                if (getActivity() != null) {
                    TextView timer_to_restart_streak_textview = getActivity().findViewById(R.id.timer_to_restart_streak_textview);
                    String text_already_there = timer_to_restart_streak_textview.getText().toString();
                    timer_to_restart_streak_textview.setText(Simplify_the_time.getting_the_time(String.valueOf(minute).concat(":").concat(String.valueOf(hour)).concat("_").concat(text_already_there)));
                    set_the_constraints();
                    Simplify_the_time.save_the_milli(String.valueOf(minute).concat(":").concat(String.valueOf(hour)).concat("_").concat(text_already_there), getActivity());
                }
            }
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if (getActivity() != null) {
            Button choose_date_for_streak_button = getActivity().findViewById(R.id.choose_date_for_streak_button);
            choose_date_for_streak_button.setBackgroundResource(R.drawable.color_for_botton_off);
            choose_date_for_streak_button.setTextColor(Color.parseColor("#607D8B"));
            Button set_restart_time_now = getActivity().findViewById(R.id.set_restart_time_now);
            set_restart_time_now.setBackgroundResource(R.drawable.color_for_botton_off);
            set_restart_time_now.setTextColor(Color.parseColor("#607D8B"));
            TextView timer_to_restart_streak_textview = getActivity().findViewById(R.id.timer_to_restart_streak_textview);
            timer_to_restart_streak_textview.setVisibility(View.GONE);
            ConstraintLayout constraintLayout = getActivity().findViewById(R.id.button_fragment);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(R.id.Restart_streak_for_switch_textview, ConstraintSet.TOP, R.id.choose_date_for_streak_button, ConstraintSet.BOTTOM, dip_to_pixels(25));
            constraintSet.applyTo(constraintLayout);
        }
    }

    private int dip_to_pixels(float dip) {
        Resources r = getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
    }

    private void set_the_constraints() {
        if (getActivity() != null) {
            TextView timer_to_restart_streak_textview = getActivity().findViewById(R.id.timer_to_restart_streak_textview);
            timer_to_restart_streak_textview.setVisibility(View.VISIBLE);
            ConstraintLayout constraintLayout = getActivity().findViewById(R.id.button_fragment);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(timer_to_restart_streak_textview.getId(), ConstraintSet.TOP, R.id.choose_date_for_streak_button, ConstraintSet.BOTTOM, dip_to_pixels(20));
            constraintSet.connect(R.id.Restart_streak_for_switch_textview, ConstraintSet.TOP, R.id.timer_to_restart_streak_textview, ConstraintSet.BOTTOM, dip_to_pixels(25));
            constraintSet.applyTo(constraintLayout);
        }
    }

    private int get_day() {
        if (getActivity() != null) {
            TextView timer_to_restart_streak_textview = getActivity().findViewById(R.id.timer_to_restart_streak_textview);
            String[] splitter = timer_to_restart_streak_textview.getText().toString().split("_");
            return Integer.parseInt(splitter[0]);
        } else {
            return 0;
        }
    }
}
