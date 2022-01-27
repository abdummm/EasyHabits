package com.easyhabitsapp.android;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;

public class Bottom_sheet_to_edit_weight_view_history extends BottomSheetDialogFragment implements DatePickerDialog.OnDateSetListener {
    private View mview;
    private Long milliseconds_of_date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_to_add_entry_weight, container, false);
        mview = view;
        Button button_to_choose_date_in_bottom_sheet = view.findViewById(R.id.button_to_choose_date_in_bottom_sheet);
        button_to_choose_date_in_bottom_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() != null) {
                    Simple_date_picker simple_date_picker = new Simple_date_picker();
                    simple_date_picker.setTargetFragment(Bottom_sheet_to_edit_weight_view_history.this, 0);
                    simple_date_picker.show(getActivity().getSupportFragmentManager(), "tag");
                }
            }
        });
        set_hint();
        set_the_time_and_weight();
        text_watcher();
        Button button_ok_in_add_entry_sheet = mview.findViewById(R.id.button_ok_in_add_entry_sheet);
        button_ok_in_add_entry_sheet.setEnabled(true);
        button_ok_in_add_entry_sheet.getBackground().setColorFilter(null);
        ok_cancel_button_listen();
        TextView text_in_top_of_add_entry_bottom_sheet = mview.findViewById(R.id.text_in_top_of_add_entry_bottom_sheet);
        text_in_top_of_add_entry_bottom_sheet.setText("Edit Weight Entry");
        return view;
    }

    private void set_the_time_and_weight() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("weight_emergency", getContext().MODE_PRIVATE);
        String units = sharedPreferences.getString("units", "");
        Button button_to_choose_date_in_bottom_sheet = mview.findViewById(R.id.button_to_choose_date_in_bottom_sheet);
        EditText enter_weight_in_bottom_cheet_under_date = mview.findViewById(R.id.enter_weight_in_bottom_cheet_under_date);
        String tag = tag_to_position();
        String[] splitter = tag.split("small_divide");
        milliseconds_of_date = Long.parseLong(splitter[0]);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(splitter[0]));
        int set_year = calendar.get(Calendar.YEAR);
        int set_month = calendar.get(Calendar.MONTH);
        int set_day = calendar.get(Calendar.DAY_OF_MONTH);
        Calendar real_calender = Calendar.getInstance();
        int real_year = real_calender.get(Calendar.YEAR);
        if (real_year != set_year) {
            button_to_choose_date_in_bottom_sheet.setText("Date: ".concat(Simplify_the_time.return_month(String.valueOf(set_month)).concat(" ").concat(String.valueOf(set_day))).concat(", ").concat(String.valueOf(set_year)).concat(" (click to change)"));
        } else {
            button_to_choose_date_in_bottom_sheet.setText("Date: ".concat(Simplify_the_time.return_month(String.valueOf(set_month)).concat(" ").concat(String.valueOf(set_day))).concat(" (click to change)"));
        }
        if (units!=null && units.equals("metric")) {
            enter_weight_in_bottom_cheet_under_date.setText(splitter[1]);
        } else if (units!=null && units.equals("imperial")){
            enter_weight_in_bottom_cheet_under_date.setText(String.format("%.1f", Float.parseFloat(splitter[1])*2.205f));
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Button button_to_choose_date_in_bottom_sheet = mview.findViewById(R.id.button_to_choose_date_in_bottom_sheet);
        Calendar calendar = Calendar.getInstance();
        int real_year = calendar.get(Calendar.YEAR);
        if (real_year != year) {
            button_to_choose_date_in_bottom_sheet.setText("Date: ".concat(Simplify_the_time.return_month(String.valueOf(month)).concat(" ").concat(String.valueOf(day))).concat(", ").concat(String.valueOf(year)));
        } else {
            button_to_choose_date_in_bottom_sheet.setText("Date: ".concat(Simplify_the_time.return_month(String.valueOf(month)).concat(" ").concat(String.valueOf(day))));
        }
        Calendar calender_to_set_milli = Calendar.getInstance();
        calender_to_set_milli.set(Calendar.YEAR, year);
        calender_to_set_milli.set(Calendar.MONTH, month);
        calender_to_set_milli.set(Calendar.DAY_OF_MONTH, day);
        calender_to_set_milli.set(Calendar.HOUR_OF_DAY, 0);
        calender_to_set_milli.set(Calendar.MINUTE, 0);
        calender_to_set_milli.set(Calendar.SECOND, 0);
        calender_to_set_milli.set(Calendar.MILLISECOND, 0);
        milliseconds_of_date = calender_to_set_milli.getTimeInMillis();
    }

    private void set_hint() {
        if (getActivity() != null) {
            EditText enter_weight_in_bottom_cheet_under_date = mview.findViewById(R.id.enter_weight_in_bottom_cheet_under_date);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("weight_emergency", getContext().MODE_PRIVATE);
            String units = sharedPreferences.getString("units", "metric");
            if (units != null && units.equals("metric")) {
                enter_weight_in_bottom_cheet_under_date.setHint("weight in kilograms");
            } else if (units != null && units.equals("imperial")) {
                enter_weight_in_bottom_cheet_under_date.setHint("weight in pounds");
            }
        }
    }

    private String tag_to_position() {
        String tag = getTag();
        if (tag != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("weight_emergency", getContext().MODE_PRIVATE);
            String weight = sharedPreferences.getString("weight", "");
            if (weight != null && !weight.equals("")) {
                int position = Integer.parseInt(tag);
                String[] split = weight.split("split");
                for (int i = 0; i < split.length; i++) {
                    if (i == position) {
                        return split[i];
                    }
                }
            }
        }
        return "";
    }
    private void text_watcher(){
        EditText enter_weight_in_bottom_cheet_under_date = mview.findViewById(R.id.enter_weight_in_bottom_cheet_under_date);
        final Button button_ok_in_add_entry_sheet = mview.findViewById(R.id.button_ok_in_add_entry_sheet);
        enter_weight_in_bottom_cheet_under_date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("")||Float.parseFloat(charSequence.toString()) == 0){
                    button_ok_in_add_entry_sheet.setEnabled(false);
                    button_ok_in_add_entry_sheet.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                } else {
                    button_ok_in_add_entry_sheet.setEnabled(true);
                    button_ok_in_add_entry_sheet.getBackground().setColorFilter(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void ok_cancel_button_listen(){
        Button button_ok_in_add_entry_sheet = mview.findViewById(R.id.button_ok_in_add_entry_sheet);
        Button button_cancel_in_add_entry_sheet = mview.findViewById(R.id.button_cancel_in_add_entry_sheet);
        button_ok_in_add_entry_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save_the_info();
                dismiss();
            }
        });
        button_cancel_in_add_entry_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
    private void save_the_info(){
        EditText enter_weight_in_bottom_cheet_under_date = mview.findViewById(R.id.enter_weight_in_bottom_cheet_under_date);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("weight_emergency", getContext().MODE_PRIVATE);
        String weight = sharedPreferences.getString("weight", "");
        String current = String.valueOf(milliseconds_of_date).concat("small_divide").concat(String.valueOf(Float.parseFloat(enter_weight_in_bottom_cheet_under_date.getText().toString())));
        if(weight!=null) {
            String[] weight_split = weight.split("split");
            int position = return_the_position();
            String save_me = "";
                for (int i = 0; i < weight_split.length; i++) {
                        if (i != position) {
                            if(!weight_split[i].contains(String.valueOf(milliseconds_of_date))) {
                                save_me = save_me.concat(weight_split[i]).concat("split");
                            }
                        } else {
                            save_me = save_me.concat(current).concat("split");
                        }
                }
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("weight",save_me);
            myEdit.commit();
        }
    }
    private int return_the_position(){
        String tag = getTag();
        if(tag!=null) {
            return Integer.parseInt(tag);
        } else {
            return 0;
        }
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        if (getActivity() != null) {
            super.onDismiss(dialog);
            Dialog_view_history_for_weight_tracker dialog_view_history_for_weight_tracker = new Dialog_view_history_for_weight_tracker();
            dialog_view_history_for_weight_tracker.show(getActivity().getSupportFragmentManager(), "dialog_tag");
        }
    }
}
