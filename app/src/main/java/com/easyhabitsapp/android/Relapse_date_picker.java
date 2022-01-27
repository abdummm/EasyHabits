package com.easyhabitsapp.android;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class Relapse_date_picker extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), R.style.DialogTheme, this, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        return datePickerDialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        TextView timer_to_restart_streak_textview = getActivity().findViewById(R.id.timer_to_restart_streak_textview);
        timer_to_restart_streak_textview.setText(String.valueOf(day).concat("_").concat(String.valueOf(month)).concat("_").concat(String.valueOf(year)));
        DialogFragment timePicker = new Relapse_time_picker();
        timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
    }

    @Override
    public void onCancel(DialogInterface datePicker) {
        super.onCancel(datePicker);
        if(getActivity()!=null) {
            Button choose_date_for_streak_button =  getActivity().findViewById(R.id.choose_date_for_streak_button);
            choose_date_for_streak_button.setBackgroundResource(R.drawable.color_for_botton_off);
            choose_date_for_streak_button.setTextColor(Color.parseColor("#607D8B"));
            Button set_restart_time_now =  getActivity().findViewById(R.id.set_restart_time_now);
            set_restart_time_now.setBackgroundResource(R.drawable.color_for_botton_off);
            set_restart_time_now.setTextColor(Color.parseColor("#607D8B"));
            TextView timer_to_restart_streak_textview = getActivity().findViewById(R.id.timer_to_restart_streak_textview);
            timer_to_restart_streak_textview.setVisibility(View.GONE);
            ConstraintLayout constraintLayout = getActivity().findViewById(R.id.button_fragment);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(R.id.Restart_streak_for_switch_textview,ConstraintSet.TOP,R.id.choose_date_for_streak_button,ConstraintSet.BOTTOM,dip_to_pixels(25));
            constraintSet.applyTo(constraintLayout);
        }
    }
    private int dip_to_pixels(float dip) {
        Resources r = getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
    }
}