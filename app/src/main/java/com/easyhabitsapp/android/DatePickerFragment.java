package com.easyhabitsapp.android;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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

public class DatePickerFragment extends DialogFragment
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
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        if (year == Calendar.getInstance().get(Calendar.YEAR)) {
            TextView showing_the_date_to_the_user = getActivity().findViewById(R.id.showing_the_date_to_the_user);
            showing_the_date_to_the_user.setText("Date: "+get_month_string(month)+  " " +day+",");
        } else {
            TextView showing_the_date_to_the_user = getActivity().findViewById(R.id.showing_the_date_to_the_user);
            showing_the_date_to_the_user.setText("Date: "+get_month_string(month)+ " " +day +", "+ year+",");
        }
        TextView showing_the_date_to_the_user = getActivity().findViewById(R.id.showing_the_date_to_the_user);
        showing_the_date_to_the_user.setVisibility(View.INVISIBLE);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("saving_time_only_date", getContext().MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        String month_string;
        String day_string;
        if(month>=10){
            month_string = String.valueOf(month+1);
        }else {
            month_string = "0".concat(String.valueOf(month+1));
        }
        if (day>=10){
            day_string= String.valueOf(day);
        } else {
            day_string = "0".concat(String.valueOf(day));
        }
        myEdit.putString("date",String.valueOf(year).concat("-").concat(month_string).concat("-").concat(day_string));
        myEdit.apply();
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
    }

    private String get_month_string(int choose_month) {
        if (choose_month == 0) {
            return "January";
        } else if (choose_month == 1) {
            return "February";
        } else if (choose_month == 2) {
            return "March";
        } else if (choose_month == 3) {
            return "April";
        } else if (choose_month == 4) {
            return "May";
        } else if (choose_month == 5) {
            return "June";
        } else if (choose_month == 6) {
            return "July";
        } else if (choose_month == 7) {
            return "August";
        } else if (choose_month == 8) {
            return "September";
        } else if (choose_month == 9) {
            return "October";
        } else if (choose_month == 10) {
            return "November";
        } else {
            return "December";
        }
    }

    @Override
    public void onCancel(DialogInterface datePicker) {
        super.onCancel(datePicker);
        if(getActivity()!=null) {
            Button button_that_will_open_time_user_hacked = getActivity().findViewById(R.id.button_that_will_open_time_user_hacked);
            if (button_that_will_open_time_user_hacked.getCurrentTextColor() == -1) {
                button_that_will_open_time_user_hacked.setBackgroundResource(R.drawable.color_for_botton_off);
                button_that_will_open_time_user_hacked.setTextColor(Color.parseColor("#607D8B"));
                ConstraintLayout constraintLayout = getActivity().findViewById(R.id.main_activity_constraint_layout);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                View empty_view_for_padding2_main_activity = getActivity().findViewById(R.id.empty_view_for_padding2_main_activity);
                constraintSet.clear(empty_view_for_padding2_main_activity.getId(), ConstraintSet.TOP);
                constraintSet.connect(empty_view_for_padding2_main_activity.getId(), ConstraintSet.TOP, button_that_will_open_time_user_hacked.getId(), ConstraintSet.BOTTOM, dip_to_pixels(10));
                constraintSet.applyTo(constraintLayout);
                TextView showing_the_date_to_the_user = getActivity().findViewById(R.id.showing_the_date_to_the_user);
                showing_the_date_to_the_user.setVisibility(View.INVISIBLE);
            }
        }
    }
    private int dip_to_pixels(float dip) {
        Resources r = getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
    }
}