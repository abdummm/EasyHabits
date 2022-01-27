package com.easyhabitsapp.android;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimePickerFragment extends DialogFragment
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
        if (getActivity() != null) {
            final Calendar c = Calendar.getInstance();
            int current_hour = c.get(Calendar.HOUR_OF_DAY);
            int current_minute = c.get(Calendar.MINUTE);
            int day = c.get(Calendar.DAY_OF_MONTH);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("saving_time_only_date", getContext().MODE_PRIVATE);
            String date_from_save = sharedPreferences.getString("date", "");
            String[] splitter = date_from_save.split("-");
            if (day == Integer.parseInt(splitter[2])) {
                if (hourOfDay > current_hour) {
                    Toast toast = Toast.makeText(getContext(), "Can't be greater than current time", Toast.LENGTH_LONG);
                    toast.show();
                } else if (current_hour == hourOfDay && minute > current_minute) {
                    Toast toast = Toast.makeText(getContext(), "Can't be greater than current time", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    final_time(hourOfDay, minute);
                }
            } else {
                final_time(hourOfDay, minute);
            }
        }
    }

    private void final_time(int hour, int minute) {
        if (getActivity() != null) {
            TextView showing_the_date_to_the_user = getActivity().findViewById(R.id.showing_the_date_to_the_user);
            int real_hour;
            String real_minute;
            String am_or_pm;
            if (hour > 12) {
                real_hour = hour - 12;
                am_or_pm = "PM";
            } else {
                if (hour != 0) {
                    real_hour = hour;
                    am_or_pm = "AM";
                } else {
                    real_hour = 12;
                    am_or_pm = "AM";
                }
            }
            if (minute >= 10) {
                real_minute = String.valueOf(minute);
            } else {
                real_minute = "0".concat(String.valueOf(minute));
            }
            showing_the_date_to_the_user.setText(showing_the_date_to_the_user.getText().toString().concat(" ").concat(String.valueOf(real_hour).concat(":").concat(real_minute).concat("").concat(" ").concat(am_or_pm)));
            ConstraintLayout constraintLayout = getActivity().findViewById(R.id.main_activity_constraint_layout);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            View empty_view_for_padding2_main_activity = getActivity().findViewById(R.id.empty_view_for_padding2_main_activity);
            constraintSet.clear(empty_view_for_padding2_main_activity.getId(), ConstraintSet.TOP);
            constraintSet.connect(empty_view_for_padding2_main_activity.getId(), ConstraintSet.TOP, showing_the_date_to_the_user.getId(), ConstraintSet.BOTTOM, dip_to_pixels(10));
            constraintSet.applyTo(constraintLayout);
            Button button_that_will_open_time_user_hacked = getActivity().findViewById(R.id.button_that_will_open_time_user_hacked);
            button_that_will_open_time_user_hacked.setBackgroundResource(R.drawable.color_for_botton_on);
            showing_the_date_to_the_user.setVisibility(View.VISIBLE);
            button_that_will_open_time_user_hacked.setTextColor(Color.WHITE);
            EditText enter_streak_edittext = getActivity().findViewById(R.id.enter_streak_edittext);
            enter_streak_edittext.clearFocus();
            enter_streak_edittext.setText("");
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("saving_time_only_date", getContext().MODE_PRIVATE);
            String date_from_save = sharedPreferences.getString("date", "");
            SharedPreferences shared = getActivity().getSharedPreferences("main_streak", getContext().MODE_PRIVATE);
            SharedPreferences.Editor myEdit_time = shared.edit();
            if (!date_from_save.equals("")) {
                String hour_but_string;
                if (hour >= 10) {
                    hour_but_string = String.valueOf(hour);
                } else {
                    hour_but_string = "0".concat(String.valueOf(hour));
                }
                String time_in_string = date_from_save.concat(" ").concat(hour_but_string).concat(":").concat(real_minute);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                try {
                    Date date = formatter.parse(time_in_string);
                    myEdit_time.putLong("time_in_milli", date.getTime());
                    myEdit_time.apply();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onCancel(@NotNull DialogInterface dialog) {
        super.onCancel(dialog);
        if (getActivity() != null) {
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
