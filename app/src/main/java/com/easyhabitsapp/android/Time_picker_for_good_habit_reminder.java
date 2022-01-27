package com.easyhabitsapp.android;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


public class Time_picker_for_good_habit_reminder extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        /*final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);*/


        return new TimePickerDialog(getActivity(), R.style.DialogTheme, (TimePickerDialog.OnTimeSetListener) getTargetFragment(), 8, 0,
                DateFormat.is24HourFormat(getActivity()));
    }
}
