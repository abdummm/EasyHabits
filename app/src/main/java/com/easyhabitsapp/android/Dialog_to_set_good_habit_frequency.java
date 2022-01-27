package com.easyhabitsapp.android;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Dialog_to_set_good_habit_frequency extends DialogFragment {
    private View mview;
    private dissmiss_the_pick_frequency dissmiss_the_pick_frequency_listen;

    public void set_share_clicked_listen(dissmiss_the_pick_frequency listen) {
        dissmiss_the_pick_frequency_listen = listen;
    }

    public interface dissmiss_the_pick_frequency {
        void share_just_got_clciked(String text);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_to_set_good_habit_frequency, container);
        this.mview = rootView;
        all_button_listen();
        ok_and_cancel_button_listen();
        return rootView;
    }

    @Override
    public void onResume() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getDialog().getWindow() != null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            //int height = (displayMetrics.heightPixels / 100) * 80;
            int width = (displayMetrics.widthPixels / 100) * 90;
            getDialog().getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        super.onResume();
    }

    private void all_button_listen() {
        Button monday_button_in_good_habits = mview.findViewById(R.id.monday_button_in_good_habits);
        Button tuesday_button_in_good_habits = mview.findViewById(R.id.tuesday_button_in_good_habits);
        Button wednesday_button_in_good_habits = mview.findViewById(R.id.wednesday_button_in_good_habits);
        Button thursday_button_in_good_habits = mview.findViewById(R.id.thursday_button_in_good_habits);
        Button friday_button_in_good_habits = mview.findViewById(R.id.friday_button_in_good_habits);
        Button saturday_button_in_good_habits = mview.findViewById(R.id.saturday_button_in_good_habits);
        Button sunday_button_in_good_habits = mview.findViewById(R.id.sunday_button_in_good_habits);
        final CheckBox monday_check_box_good_habits = mview.findViewById(R.id.monday_check_box_good_habits);
        final CheckBox tuesday_check_box_good_habits = mview.findViewById(R.id.tuesday_check_box_good_habits);
        final CheckBox wednesday_check_box_good_habits = mview.findViewById(R.id.wednesday_check_box_good_habits);
        final CheckBox thursday_check_box_good_habits = mview.findViewById(R.id.thursday_check_box_good_habits);
        final CheckBox friday_check_box_good_habits = mview.findViewById(R.id.friday_check_box_good_habits);
        final CheckBox saturday_check_box_good_habits = mview.findViewById(R.id.saturday_check_box_good_habits);
        final CheckBox sunday_check_box_good_habits = mview.findViewById(R.id.sunday_check_box_good_habits);
        monday_button_in_good_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monday_check_box_good_habits.setChecked(!monday_check_box_good_habits.isChecked());
            }
        });
        tuesday_button_in_good_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tuesday_check_box_good_habits.setChecked(!tuesday_check_box_good_habits.isChecked());
            }
        });
        wednesday_button_in_good_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wednesday_check_box_good_habits.setChecked(!wednesday_check_box_good_habits.isChecked());
            }
        });
        thursday_button_in_good_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thursday_check_box_good_habits.setChecked(!thursday_check_box_good_habits.isChecked());
            }
        });
        friday_button_in_good_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friday_check_box_good_habits.setChecked(!friday_check_box_good_habits.isChecked());
            }
        });
        saturday_button_in_good_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saturday_check_box_good_habits.setChecked(!saturday_check_box_good_habits.isChecked());
            }
        });
        sunday_button_in_good_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sunday_check_box_good_habits.setChecked(!sunday_check_box_good_habits.isChecked());
            }
        });
    }

    private void ok_and_cancel_button_listen() {
        Button ok_button_for_white_list_dialog = mview.findViewById(R.id.ok_button_for_white_list_dialog);
        Button cancel_button_for_white_list_dialog = mview.findViewById(R.id.cancel_button_for_white_list_dialog);
        ok_button_for_white_list_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(return_days().equals("")){
                    Toast.makeText(getActivity(), "Please choose at least one day", Toast.LENGTH_SHORT).show();
                } else {
                    dissmiss_the_pick_frequency_listen.share_just_got_clciked(return_days());
                    dismiss();
                }
            }
        });
        cancel_button_for_white_list_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dissmiss_the_pick_frequency_listen.share_just_got_clciked("cancel");
                dismiss();
            }
        });
    }
    private String return_days(){
        CheckBox monday_check_box_good_habits = mview.findViewById(R.id.monday_check_box_good_habits);
        CheckBox tuesday_check_box_good_habits = mview.findViewById(R.id.tuesday_check_box_good_habits);
        CheckBox wednesday_check_box_good_habits = mview.findViewById(R.id.wednesday_check_box_good_habits);
        CheckBox thursday_check_box_good_habits = mview.findViewById(R.id.thursday_check_box_good_habits);
        CheckBox friday_check_box_good_habits = mview.findViewById(R.id.friday_check_box_good_habits);
        CheckBox saturday_check_box_good_habits = mview.findViewById(R.id.saturday_check_box_good_habits);
        CheckBox sunday_check_box_good_habits = mview.findViewById(R.id.sunday_check_box_good_habits);
        String return_me = "";
        if(monday_check_box_good_habits.isChecked()){
            return_me = "Mo";
        }
        if(tuesday_check_box_good_habits.isChecked()){
            return_me = return_me.concat("Tu");
        }
        if(wednesday_check_box_good_habits.isChecked()){
            return_me = return_me.concat("We");
        }
        if(thursday_check_box_good_habits.isChecked()){
            return_me = return_me.concat("Th");
        }
        if(friday_check_box_good_habits.isChecked()){
            return_me = return_me.concat("Fr");
        }
        if(saturday_check_box_good_habits.isChecked()){
            return_me = return_me.concat("Sa");
        }
        if(sunday_check_box_good_habits.isChecked()){
            return_me = return_me.concat("Su");
        }
        return return_me;
    }
}
