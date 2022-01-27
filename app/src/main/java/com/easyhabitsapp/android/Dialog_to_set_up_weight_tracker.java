package com.easyhabitsapp.android;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class Dialog_to_set_up_weight_tracker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private View mview;
    private long milliseconds_of_date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_to_set_up_weight_tracker, container);
        this.mview = rootView;
        if (getDialog() != null) {
            getDialog().setCanceledOnTouchOutside(false);
        }
        set_up_the_hints();
        imperial_and_kilo_button_listen();
        set_the_time_as_today();
        button_time_listen();
        cancel_and_ok_button_listen();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getActivity() != null && getDialog() != null && getDialog().getWindow() != null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = (displayMetrics.heightPixels / 100) * 90;
            int width = (displayMetrics.widthPixels / 100) * 90;
            getDialog().getWindow().setLayout(width, height);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    private void set_up_the_hints() {
        EditText edit_text_to_enter_number_of_weight_under_height = mview.findViewById(R.id.edit_text_to_enter_number_of_weight_under_height);
        EditText edit_text_to_enter_goal_for_weight = mview.findViewById(R.id.edit_text_to_enter_goal_for_weight);
        Button choose_unit_for_weight_metric = mview.findViewById(R.id.choose_unit_for_weight_metric);
        if (choose_unit_for_weight_metric.getCurrentTextColor() == Color.WHITE) {
            edit_text_to_enter_number_of_weight_under_height.setHint("Kilos");
            edit_text_to_enter_goal_for_weight.setHint("Kilos");
        } else {
            edit_text_to_enter_number_of_weight_under_height.setHint("Pounds");
            edit_text_to_enter_goal_for_weight.setHint("Pounds");
        }
    }

    private void imperial_and_kilo_button_listen() {
        final Button choose_unit_for_weight_metric = mview.findViewById(R.id.choose_unit_for_weight_metric);
        final Button choose_unit_for_weight_imperial = mview.findViewById(R.id.choose_unit_for_weight_imperial);
        final EditText edit_text_to_enter_number_of_height = mview.findViewById(R.id.edit_text_to_enter_number_of_height);
        final ConstraintLayout layout_to_show_imperial_system_height = mview.findViewById(R.id.layout_to_show_imperial_system_height);
        choose_unit_for_weight_metric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose_unit_for_weight_metric.setTextColor(Color.WHITE);
                choose_unit_for_weight_metric.setBackgroundResource(R.drawable.color_for_botton_on);
                choose_unit_for_weight_imperial.setTextColor(Color.parseColor("#607D8B"));
                choose_unit_for_weight_imperial.setBackgroundResource(R.drawable.color_for_botton_off);
                edit_text_to_enter_number_of_height.setVisibility(View.VISIBLE);
                layout_to_show_imperial_system_height.setVisibility(View.INVISIBLE);
                set_up_the_hints();
            }
        });
        choose_unit_for_weight_imperial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose_unit_for_weight_metric.setTextColor(Color.parseColor("#607D8B"));
                choose_unit_for_weight_metric.setBackgroundResource(R.drawable.color_for_botton_off);
                choose_unit_for_weight_imperial.setTextColor(Color.WHITE);
                choose_unit_for_weight_imperial.setBackgroundResource(R.drawable.color_for_botton_on);
                edit_text_to_enter_number_of_height.setVisibility(View.INVISIBLE);
                layout_to_show_imperial_system_height.setVisibility(View.VISIBLE);
                set_up_the_hints();
            }
        });
    }

    private void button_time_listen() {
        final Button choose_date_for_streak_button = mview.findViewById(R.id.choose_date_for_streak_button);
        final Button set_restart_time_now = mview.findViewById(R.id.set_restart_time_now);
        choose_date_for_streak_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*set_restart_time_now.setTextColor(Color.parseColor("#607D8B"));
                set_restart_time_now.setBackgroundResource(R.drawable.color_for_botton_off);
                choose_date_for_streak_button.setTextColor(Color.WHITE);
                choose_date_for_streak_button.setBackgroundResource(R.drawable.color_for_botton_on);*/
                open_date_picker();
            }
        });
        set_restart_time_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose_date_for_streak_button.setTextColor(Color.parseColor("#607D8B"));
                choose_date_for_streak_button.setBackgroundResource(R.drawable.color_for_botton_off);
                set_restart_time_now.setTextColor(Color.WHITE);
                set_restart_time_now.setBackgroundResource(R.drawable.color_for_botton_on);
                set_the_time_as_today();
            }
        });
    }

    private void set_the_time_as_today() {
        TextView text_view_displaying_dates_under_button = mview.findViewById(R.id.text_view_displaying_dates_under_button);
        Calendar calendar = Calendar.getInstance();
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        text_view_displaying_dates_under_button.setText("Date: ".concat(Simplify_the_time.return_month(month).concat(" ").concat(day)));
        milliseconds_of_date = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis());
    }

    private void open_date_picker() {
        if (getActivity() != null) {
            Simple_date_picker simple_date_picker = new Simple_date_picker();
            simple_date_picker.setTargetFragment(Dialog_to_set_up_weight_tracker.this, 0);
            simple_date_picker.show(getActivity().getSupportFragmentManager(), "tag");
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        TextView text_view_displaying_dates_under_button = mview.findViewById(R.id.text_view_displaying_dates_under_button);
        Calendar calendar = Calendar.getInstance();
        int real_year = calendar.get(Calendar.YEAR);
        if (real_year != year) {
            text_view_displaying_dates_under_button.setText("Date: ".concat(Simplify_the_time.return_month(String.valueOf(month)).concat(" ").concat(String.valueOf(day))).concat(", ").concat(String.valueOf(year)));
        } else {
            text_view_displaying_dates_under_button.setText("Date: ".concat(Simplify_the_time.return_month(String.valueOf(month)).concat(" ").concat(String.valueOf(day))));
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

    private void cancel_and_ok_button_listen() {
        final Button choose_unit_for_weight_metric = mview.findViewById(R.id.choose_unit_for_weight_metric);
        Button cancel_button_for_white_list_dialog = mview.findViewById(R.id.cancel_button_for_white_list_dialog);
        Button ok_button_for_white_list_dialog = mview.findViewById(R.id.ok_button_for_white_list_dialog);
        final EditText edit_text_to_enter_number_of_height = mview.findViewById(R.id.edit_text_to_enter_number_of_height);
        final EditText edit_text_to_enter_number_of_weight_under_height = mview.findViewById(R.id.edit_text_to_enter_number_of_weight_under_height);
        final EditText edit_text_to_enter_goal_for_weight = mview.findViewById(R.id.edit_text_to_enter_goal_for_weight);
        final EditText edit_text_for_height_foot = mview.findViewById(R.id.edit_text_for_height_foot);
        final EditText edit_text_for_height_inch = mview.findViewById(R.id.edit_text_for_height_inch);
        cancel_button_for_white_list_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getContext() != null && getActivity() != null) {
                    Intent intent = new Intent(getContext(), after_login.class);
                    intent.putExtra("Start_the_emergency_true", true);
                    getContext().startActivity(intent);
                    getActivity().overridePendingTransition(0, 0);
                }
            }
        });
        ok_button_for_white_list_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(is_the_data_valid()){
                    save_the_info();
                    dismiss();
                }
            }
        });
    }

    private void save_the_info() {
        if (getActivity() != null) {
            EditText edit_text_to_enter_number_of_height = mview.findViewById(R.id.edit_text_to_enter_number_of_height);
            EditText edit_text_to_enter_number_of_weight_under_height = mview.findViewById(R.id.edit_text_to_enter_number_of_weight_under_height);
            EditText edit_text_to_enter_goal_for_weight = mview.findViewById(R.id.edit_text_to_enter_goal_for_weight);
            EditText edit_text_for_height_foot = mview.findViewById(R.id.edit_text_for_height_foot);
            EditText edit_text_for_height_inch = mview.findViewById(R.id.edit_text_for_height_inch);
            Button choose_unit_for_weight_metric = mview.findViewById(R.id.choose_unit_for_weight_metric);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("weight_emergency", getContext().MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            if (choose_unit_for_weight_metric.getCurrentTextColor() == Color.WHITE) {
                myEdit.putFloat("height_start", Float.parseFloat(edit_text_to_enter_number_of_height.getText().toString()));
                myEdit.putString("weight", String.valueOf(milliseconds_of_date).concat("small_divide").concat(String.valueOf(Float.parseFloat(edit_text_to_enter_number_of_weight_under_height.getText().toString())).concat("split")));
                myEdit.putFloat("goal_start", Float.parseFloat(edit_text_to_enter_goal_for_weight.getText().toString()));
                myEdit.putString("units", "metric");
                myEdit.putFloat("start_weight", Float.parseFloat(edit_text_to_enter_number_of_weight_under_height.getText().toString()));
            } else {
                int inches = Integer.parseInt(edit_text_for_height_foot.getText().toString()) * 12 + Integer.parseInt(edit_text_for_height_inch.getText().toString());
                myEdit.putFloat("height_start", inches * 2.54f);
                myEdit.putString("weight", String.valueOf(milliseconds_of_date).concat("small_divide").concat(String.valueOf(Float.parseFloat(edit_text_to_enter_number_of_weight_under_height.getText().toString()) / 2.205f).concat("split")));
                myEdit.putFloat("goal_start", Float.parseFloat(edit_text_to_enter_goal_for_weight.getText().toString()) / 2.205f);
                myEdit.putString("units", "imperial");
                myEdit.putFloat("start_weight", Float.parseFloat(edit_text_to_enter_number_of_weight_under_height.getText().toString()) / 2.205f);
            }
            myEdit.commit();
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

    private boolean is_the_data_valid() {
        final Button choose_unit_for_weight_metric = mview.findViewById(R.id.choose_unit_for_weight_metric);
        final EditText edit_text_to_enter_number_of_height = mview.findViewById(R.id.edit_text_to_enter_number_of_height);
        final EditText edit_text_to_enter_number_of_weight_under_height = mview.findViewById(R.id.edit_text_to_enter_number_of_weight_under_height);
        final EditText edit_text_to_enter_goal_for_weight = mview.findViewById(R.id.edit_text_to_enter_goal_for_weight);

        final EditText edit_text_for_height_foot = mview.findViewById(R.id.edit_text_for_height_foot);
        final EditText edit_text_for_height_inch = mview.findViewById(R.id.edit_text_for_height_inch);
        if (choose_unit_for_weight_metric.getCurrentTextColor() == Color.WHITE) {
            if (!edit_text_to_enter_number_of_height.getText().toString().equals("") && !edit_text_to_enter_number_of_height.getText().toString().equals(".") && !edit_text_to_enter_number_of_weight_under_height.getText().toString().equals("") && !edit_text_to_enter_number_of_weight_under_height.getText().toString().equals(".") && !edit_text_to_enter_goal_for_weight.getText().toString().equals("") && !edit_text_to_enter_goal_for_weight.getText().toString().equals(".") && Float.parseFloat(edit_text_to_enter_number_of_height.getText().toString()) != 0 && Float.parseFloat(edit_text_to_enter_number_of_weight_under_height.getText().toString()) != 0 && Float.parseFloat(edit_text_to_enter_goal_for_weight.getText().toString()) != 0) {
                return true;
            } else {
                if (edit_text_to_enter_number_of_height.getText().toString().equals("") || edit_text_to_enter_number_of_height.getText().toString().equals(".")) {
                    edit_text_to_enter_number_of_height.setError("Height can't be empty");
                    Toast.makeText(getActivity(), "Height can't be empty", Toast.LENGTH_SHORT).show();
                    return false;
                }else if (edit_text_to_enter_number_of_weight_under_height.getText().toString().equals("")||edit_text_to_enter_number_of_weight_under_height.getText().toString().equals(".")) {
                    edit_text_to_enter_number_of_weight_under_height.setError("Weight can't be empty");
                    Toast.makeText(getActivity(), "Weight can't be empty", Toast.LENGTH_SHORT).show();
                    return false;
                } else if (edit_text_to_enter_goal_for_weight.getText().toString().equals("") || edit_text_to_enter_goal_for_weight.getText().toString().equals(".")) {
                    edit_text_to_enter_goal_for_weight.setError("Goal can't be empty");
                    Toast.makeText(getActivity(), "Goal can't be empty", Toast.LENGTH_SHORT).show();
                    return false;
                } else if (Float.parseFloat(edit_text_to_enter_number_of_height.getText().toString()) == 0) {
                    edit_text_to_enter_number_of_height.setError("Height can't be zero");
                    Toast.makeText(getActivity(), "Height can't be zero", Toast.LENGTH_SHORT).show();
                    return false;
                } else if (Float.parseFloat(edit_text_to_enter_number_of_weight_under_height.getText().toString()) == 0) {
                    edit_text_to_enter_number_of_weight_under_height.setError("Weight can't be zero");
                    Toast.makeText(getActivity(), "Weight can't be zero", Toast.LENGTH_SHORT).show();
                    return false;
                } else if (Float.parseFloat(edit_text_to_enter_goal_for_weight.getText().toString()) == 0) {
                    edit_text_to_enter_goal_for_weight.setError("Goal can't be zero");
                    Toast.makeText(getActivity(), "Goal can't be zero", Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    return false;
                }
            }
        } else {
            if (!edit_text_for_height_foot.getText().toString().equals("") && !edit_text_for_height_foot.getText().toString().equals(".") && !edit_text_for_height_inch.getText().toString().equals("") && !edit_text_for_height_inch.getText().toString().equals(".") && !edit_text_to_enter_number_of_weight_under_height.getText().toString().equals("")&& !edit_text_to_enter_number_of_weight_under_height.getText().toString().equals(".") && !edit_text_to_enter_goal_for_weight.getText().toString().equals("")&& !edit_text_to_enter_goal_for_weight.getText().toString().equals(".") && Float.parseFloat(edit_text_for_height_foot.getText().toString()) == 0 && Float.parseFloat(edit_text_to_enter_number_of_weight_under_height.getText().toString()) == 0 && Float.parseFloat(edit_text_to_enter_goal_for_weight.getText().toString()) == 0) {
                return true;
            } else {
                if (edit_text_for_height_foot.getText().toString().equals("") || edit_text_for_height_foot.getText().toString().equals(".")) {
                    edit_text_for_height_foot.setError("Height can't be empty");
                    Toast.makeText(getActivity(), "Height can't be empty", Toast.LENGTH_SHORT).show();
                    return false;
                } else if (edit_text_for_height_inch.getText().toString().equals("") || edit_text_for_height_inch.getText().toString().equals(".")) {
                    edit_text_for_height_inch.setError("Height can't be empty");
                    Toast.makeText(getActivity(), "Height can't be empty", Toast.LENGTH_SHORT).show();
                    return false;
                } else if (edit_text_to_enter_number_of_weight_under_height.getText().toString().equals("") || edit_text_to_enter_number_of_weight_under_height.getText().toString().equals(".")) {
                    edit_text_to_enter_number_of_weight_under_height.setError("Weight can't be empty");
                    edit_text_to_enter_number_of_weight_under_height.setError("Weight can't be empty");
                    return false;
                } else if (edit_text_to_enter_goal_for_weight.getText().toString().equals("") || edit_text_to_enter_goal_for_weight.getText().toString().equals(".")) {
                    edit_text_to_enter_goal_for_weight.setError("Goal can't be empty");
                    Toast.makeText(getActivity(), "Goal can't be empty", Toast.LENGTH_SHORT).show();
                    return false;
                } else if (Float.parseFloat(edit_text_for_height_foot.getText().toString()) == 0) {
                    edit_text_for_height_foot.setError("Height can't be zero");
                    Toast.makeText(getActivity(), "Height can't be zero", Toast.LENGTH_SHORT).show();
                    return false;
                } else if (Float.parseFloat(edit_text_to_enter_number_of_weight_under_height.getText().toString()) == 0) {
                    edit_text_to_enter_number_of_weight_under_height.setError("Weight can't be zero");
                    edit_text_to_enter_number_of_weight_under_height.setError("Weight can't be zero");
                    return false;
                } else if (Float.parseFloat(edit_text_to_enter_goal_for_weight.getText().toString()) == 0) {
                    edit_text_to_enter_goal_for_weight.setError("Goal can't be zero");
                    Toast.makeText(getActivity(), "Goal can't be zero", Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    return false;
                }
            }
        }
    }
}
