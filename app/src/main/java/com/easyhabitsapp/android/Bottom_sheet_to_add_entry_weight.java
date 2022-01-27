package com.easyhabitsapp.android;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;


public class Bottom_sheet_to_add_entry_weight extends BottomSheetDialogFragment implements DatePickerDialog.OnDateSetListener {
    private View mview;
    private Long milliseconds_of_date;
    private Bottom_sheet_for_add_entry_listen listener;

    public interface Bottom_sheet_for_add_entry_listen {
        void on_entry_added(String text);
    }

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
                    simple_date_picker.setTargetFragment(Bottom_sheet_to_add_entry_weight.this, 0);
                    simple_date_picker.show(getActivity().getSupportFragmentManager(), "tag");
                }
            }
        });
        set_up_the_hint();
        set_date_as_today();
        text_watcher();
        ok_and_cancel_click_listener();
        Button button_ok_in_add_entry_sheet = mview.findViewById(R.id.button_ok_in_add_entry_sheet);
        button_ok_in_add_entry_sheet.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        return view;
    }

    private void set_up_the_hint() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("weight_emergency", getContext().MODE_PRIVATE);
            String units = sharedPreferences.getString("units", "metric");
            EditText enter_weight_in_bottom_cheet_under_date = mview.findViewById(R.id.enter_weight_in_bottom_cheet_under_date);
            if (units != null) {
                if (units.equals("metric")) {
                    enter_weight_in_bottom_cheet_under_date.setHint("Weight in kilos");
                } else {
                    enter_weight_in_bottom_cheet_under_date.setHint("Weight in pounds");
                }
            }
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

    private void set_date_as_today() {
        Button button_to_choose_date_in_bottom_sheet = mview.findViewById(R.id.button_to_choose_date_in_bottom_sheet);
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        button_to_choose_date_in_bottom_sheet.setText("Date: ".concat(Simplify_the_time.return_month(String.valueOf(month)).concat(" ").concat(String.valueOf(day))).concat(" (click to change)"));
        milliseconds_of_date = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis());
    }

    private void text_watcher() {
        EditText enter_weight_in_bottom_cheet_under_date = mview.findViewById(R.id.enter_weight_in_bottom_cheet_under_date);
        final Button button_ok_in_add_entry_sheet = mview.findViewById(R.id.button_ok_in_add_entry_sheet);
        enter_weight_in_bottom_cheet_under_date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals("") && Float.parseFloat(charSequence.toString()) != 0) {
                    button_ok_in_add_entry_sheet.setEnabled(true);
                    button_ok_in_add_entry_sheet.getBackground().setColorFilter(null);
                } else {
                    button_ok_in_add_entry_sheet.setEnabled(false);
                    button_ok_in_add_entry_sheet.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void ok_and_cancel_click_listener() {
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

    private void save_the_info() {
        EditText enter_weight_in_bottom_cheet_under_date = mview.findViewById(R.id.enter_weight_in_bottom_cheet_under_date);
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("weight_emergency", getContext().MODE_PRIVATE);
            String units = sharedPreferences.getString("units", "");
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            String old_data = check_and_remove();
            if(units!=null && units.equals("metric")) {
                myEdit.putString("weight", old_data.concat(String.valueOf(milliseconds_of_date).concat("small_divide").concat(String.valueOf(Float.parseFloat(enter_weight_in_bottom_cheet_under_date.getText().toString())).concat("split"))));
            } else {
                float weight_in_kg = Float.parseFloat(enter_weight_in_bottom_cheet_under_date.getText().toString()) /2.205f;
                myEdit.putString("weight", old_data.concat(String.valueOf(milliseconds_of_date).concat("small_divide").concat(String.valueOf(weight_in_kg).concat("split"))));
            }
            myEdit.commit();
            listener.on_entry_added("update");
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (Bottom_sheet_for_add_entry_listen) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement bottomsheetlistener");
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        final Activity activity = getActivity();
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }
    }

    private String check_and_remove() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("weight_emergency", getContext().MODE_PRIVATE);
            String data = sharedPreferences.getString("weight", "");
            if (data != null) {
                if (data.contains(String.valueOf(milliseconds_of_date))) {
                    String[] split_weight = data.split("split");
                    String save_me = "";
                    for (int i = 0; i < split_weight.length; i++) {
                        if (!split_weight[i].contains(String.valueOf(milliseconds_of_date))) {
                            save_me = save_me.concat(split_weight[i]).concat("split");
                        }
                    }
                    return save_me;
                } else {
                    return data;
                }
            } else {
                return "";
            }
        } else {
            return "";
        }
    }
}
