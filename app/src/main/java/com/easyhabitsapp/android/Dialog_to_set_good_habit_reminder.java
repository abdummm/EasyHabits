package com.easyhabitsapp.android;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Dialog_to_set_good_habit_reminder extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private View mview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_for_good_habit_reminder, container);
        this.mview = rootView;
        set_up_time_text();
        cacnel_and_ok_button_listen();
        change_time_button_listenr();
        return rootView;
    }

    private void cacnel_and_ok_button_listen() {
        Button cancel_button_for_white_list_dialog = mview.findViewById(R.id.cancel_button_for_white_list_dialog);
        final Button ok_button_for_white_list_dialog = mview.findViewById(R.id.ok_button_for_white_list_dialog);
        cancel_button_for_white_list_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getDialog() != null) {
                    getDialog().dismiss();
                } else {
                    dismiss();
                }
            }
        });
        ok_button_for_white_list_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ok_button_listen();
            }
        });
    }


    @Override
    public void onResume() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getActivity() != null && getDialog() != null && getDialog().getWindow() != null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = (displayMetrics.heightPixels / 100) * 90;
            int real_width = (displayMetrics.widthPixels / 100) * 90;
            getDialog().getWindow().setLayout(real_width, height);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        super.onResume();
    }

    private void set_up_time_text(){
        TextView text_view_saying_time_good_habit_dialog = mview.findViewById(R.id.text_view_saying_time_good_habit_dialog);
        text_view_saying_time_good_habit_dialog.setText("Time: ".concat(get_tag()));
    }
    private String get_tag(){
        String tag = getTag();
        if(tag!=null){
            return tag;
        } else {
            return "";
        }
    }
    private void change_time_button_listenr(){
        Button button_to_change_time_in_good_habits = mview.findViewById(R.id.button_to_change_time_in_good_habits);
        button_to_change_time_in_good_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() != null) {
                    DialogFragment Time_picker_for_good_habit_reminder = new Time_picker_for_good_habit_reminder();
                    Time_picker_for_good_habit_reminder.setTargetFragment(Dialog_to_set_good_habit_reminder.this, 0);
                    Time_picker_for_good_habit_reminder.show(getActivity().getSupportFragmentManager(), "time picker");
                }
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        TextView text_view_saying_time_good_habit_dialog = mview.findViewById(R.id.text_view_saying_time_good_habit_dialog);
        text_view_saying_time_good_habit_dialog.setText("Time: ".concat(Simplify_the_time.return_time(String.valueOf(minute).concat(":").concat(String.valueOf(hour)))));
    }
    private void ok_button_listen(){
        TextView text_view_saying_time_good_habit_dialog = mview.findViewById(R.id.text_view_saying_time_good_habit_dialog);
        if(return_the_check().equals("")) {
            Toast.makeText(getActivity(), "You must check at least one day", Toast.LENGTH_LONG).show();
        } else {
            Intent i = new Intent().putExtra("time", text_view_saying_time_good_habit_dialog.getText().toString().replace("Time: ","").trim()).putExtra("days",return_the_check());
            if (getTargetFragment() != null) {
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
            }
            dismiss();
        }
    }
    private String return_the_check(){
        String return_me = "";
        CheckBox monday_check_box_good_habits = mview.findViewById(R.id.monday_check_box_good_habits);
        CheckBox tuesday_check_box_good_habits = mview.findViewById(R.id.tuesday_check_box_good_habits);
        CheckBox wednesday_check_box_good_habits = mview.findViewById(R.id.wednesday_check_box_good_habits);
        CheckBox thursday_check_box_good_habits = mview.findViewById(R.id.thursday_check_box_good_habits);
        CheckBox friday_check_box_good_habits = mview.findViewById(R.id.friday_check_box_good_habits);
        CheckBox saturday_check_box_good_habits = mview.findViewById(R.id.saturday_check_box_good_habits);
        CheckBox sunday_check_box_good_habits = mview.findViewById(R.id.sunday_check_box_good_habits);
        if(monday_check_box_good_habits.isChecked()){
            return_me = return_me.concat("0");
        }if(tuesday_check_box_good_habits.isChecked()){
            return_me = return_me.concat("1");
        }if(wednesday_check_box_good_habits.isChecked()){
            return_me = return_me.concat("2");
        }if(thursday_check_box_good_habits.isChecked()){
            return_me = return_me.concat("3");
        }if(friday_check_box_good_habits.isChecked()){
            return_me = return_me.concat("4");
        }if(saturday_check_box_good_habits.isChecked()){
            return_me = return_me.concat("5");
        }if(sunday_check_box_good_habits.isChecked()){
            return_me = return_me.concat("6");
        }
        return return_me;
    }
}
