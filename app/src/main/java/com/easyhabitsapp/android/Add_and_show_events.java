package com.easyhabitsapp.android;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.easyhabitsapp.android.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class Add_and_show_events extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private View mview;
    private ArrayList<Color_item> mcolor_item;
    private Color_adapter m_adapter;
    private RecyclerView mrecycle_view;
    private Example_adapter_to_show madapter;
    private RecyclerView.LayoutManager mlayout_manager;
    private RecyclerView mrecycle_view_trash;
    private Example_adapter_to_show_trash_can madapter_trash;
    private RecyclerView.LayoutManager mlayout_manager_trash;
    private int real_width;
    private ArrayList<Example_item_for_showing_events_trash_can> example_item_for_showing_events_trash_cans;
    private ArrayList<Example_item_for_showing_events> example_item_for_showing_events;
    private long time_in_right_now;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        set_the_screen();
        View rootView = inflater.inflate(R.layout.add_and_show_events, container);
        this.mview = rootView;
        if (getDialog() != null) {
            getDialog().setCanceledOnTouchOutside(false);
        }
        time_in_right_now = System.currentTimeMillis();
        add_the_date();
        color_the_top_buttons();
        top_button_listener();
        inlist();
        set_the_color_spinner();
        clear_all_the_focus();
        set_the_spinner();
        cancel_and_ok_watcher();
        text_watcher();
        time_button_watchers();
        check_if_event_is_in_the_past();
        organize_the_events();
        call_the_recycle_view();
        return rootView;
    }

    @Override
    public void onStart() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getActivity() != null && getDialog() != null && getDialog().getWindow() != null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = (displayMetrics.heightPixels / 100) * 90;
            real_width = (displayMetrics.widthPixels / 100) * 90;
            getDialog().getWindow().setLayout(real_width, height);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        super.onStart();
    }

    private void set_the_screen() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getActivity() != null && getDialog() != null && getDialog().getWindow() != null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = (displayMetrics.heightPixels / 100) * 90;
            real_width = (displayMetrics.widthPixels / 100) * 90;
            getDialog().getWindow().setLayout(real_width, height);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    private void color_the_top_buttons() {
        String tag = getTag();
        if (tag != null) {
            String[] split_the_tag = tag.split("split");
            Button add_events_button_inside_dialog = mview.findViewById(R.id.add_events_button_inside_dialog);
            Button show_events_button_inside_dialog = mview.findViewById(R.id.show_events_button_inside_dialog);
            ConstraintLayout show_events_layout_in_dialog = mview.findViewById(R.id.show_events_layout_in_dialog);
            ConstraintLayout add_events_layout_in_dialog = mview.findViewById(R.id.add_events_layout_in_dialog);
            if (split_the_tag[0].equals("add_event")) {
                add_events_button_inside_dialog.setBackgroundResource(R.drawable.color_for_botton_on);
                add_events_button_inside_dialog.setTextColor(Color.WHITE);
                add_events_layout_in_dialog.setVisibility(View.VISIBLE);
                show_events_layout_in_dialog.setVisibility(View.INVISIBLE);
                ScrollView scroll_add_view = mview.findViewById(R.id.scroll_add_view);
                scroll_add_view.setVisibility(View.VISIBLE);
                Button cancel_button_on_the_left_events = mview.findViewById(R.id.cancel_button_on_the_left_events);
                cancel_button_on_the_left_events.setVisibility(View.VISIBLE);
            } else {
                show_events_button_inside_dialog.setBackgroundResource(R.drawable.color_for_botton_on);
                show_events_button_inside_dialog.setTextColor(Color.WHITE);
                add_events_layout_in_dialog.setVisibility(View.INVISIBLE);
                show_events_layout_in_dialog.setVisibility(View.VISIBLE);
                ScrollView scroll_add_view = mview.findViewById(R.id.scroll_add_view);
                scroll_add_view.setVisibility(View.INVISIBLE);
                Button cancel_button_on_the_left_events = mview.findViewById(R.id.cancel_button_on_the_left_events);
                cancel_button_on_the_left_events.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void top_button_listener() {
        final Button add_events_button_inside_dialog = mview.findViewById(R.id.add_events_button_inside_dialog);
        final Button show_events_button_inside_dialog = mview.findViewById(R.id.show_events_button_inside_dialog);
        final ConstraintLayout show_events_layout_in_dialog = mview.findViewById(R.id.show_events_layout_in_dialog);
        final ConstraintLayout add_events_layout_in_dialog = mview.findViewById(R.id.add_events_layout_in_dialog);
        add_events_button_inside_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_events_button_inside_dialog.setBackgroundResource(R.drawable.color_for_botton_on);
                add_events_button_inside_dialog.setTextColor(Color.WHITE);
                show_events_button_inside_dialog.setBackgroundResource(R.drawable.color_for_botton_off);
                show_events_button_inside_dialog.setTextColor(Color.parseColor("#607D8B"));
                add_events_layout_in_dialog.setVisibility(View.VISIBLE);
                show_events_layout_in_dialog.setVisibility(View.INVISIBLE);
                ScrollView scroll_add_view = mview.findViewById(R.id.scroll_add_view);
                scroll_add_view.setVisibility(View.VISIBLE);
                Button cancel_button_on_the_left_events = mview.findViewById(R.id.cancel_button_on_the_left_events);
                cancel_button_on_the_left_events.setVisibility(View.VISIBLE);
            }
        });
        show_events_button_inside_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_events_button_inside_dialog.setBackgroundResource(R.drawable.color_for_botton_on);
                show_events_button_inside_dialog.setTextColor(Color.WHITE);
                add_events_button_inside_dialog.setBackgroundResource(R.drawable.color_for_botton_off);
                add_events_button_inside_dialog.setTextColor(Color.parseColor("#607D8B"));
                add_events_layout_in_dialog.setVisibility(View.INVISIBLE);
                show_events_layout_in_dialog.setVisibility(View.VISIBLE);
                ScrollView scroll_add_view = mview.findViewById(R.id.scroll_add_view);
                scroll_add_view.setVisibility(View.INVISIBLE);
                Button cancel_button_on_the_left_events = mview.findViewById(R.id.cancel_button_on_the_left_events);
                cancel_button_on_the_left_events.setVisibility(View.INVISIBLE);
            }
        });
    }

    /*@SuppressLint("ClickableViewAccessibility")
    private void make_the_edit_text_scroll_able() {
        EditText enter_the_description_of_the_edit_text = mview.findViewById(R.id.enter_the_description_of_the_edit_text);
        enter_the_description_of_the_edit_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.enter_the_description_of_the_edit_text) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_UP:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }
        });
    }*/

    private void inlist() {
        mcolor_item = new ArrayList<>();
        mcolor_item.add(new Color_item("Teal", R.drawable.z_dark_green));
        mcolor_item.add(new Color_item("Red", R.drawable.z_red));
        mcolor_item.add(new Color_item("Green", R.drawable.z_green));
        mcolor_item.add(new Color_item("Yellow", R.drawable.z_yellow));
        mcolor_item.add(new Color_item("Blue", R.drawable.z_blue));
        mcolor_item.add(new Color_item("Orange", R.drawable.z_orange));
        mcolor_item.add(new Color_item("Purple", R.drawable.z_purple));
        mcolor_item.add(new Color_item("Cyan", R.drawable.z_cyan));
        mcolor_item.add(new Color_item("Magenta", R.drawable.z_magenta));
        mcolor_item.add(new Color_item("Lime", R.drawable.z_lime));
        mcolor_item.add(new Color_item("Pink", R.drawable.z_pink));
        mcolor_item.add(new Color_item("Brown", R.drawable.z_brown));
        mcolor_item.add(new Color_item("Navy", R.drawable.z_navy));
        mcolor_item.add(new Color_item("Black", R.drawable.z_black));
    }

    private void set_the_color_spinner() {
        if (getActivity() != null) {
            Spinner spinner_to_choose_the_color_event = mview.findViewById(R.id.spinner_to_choose_the_color_event);
            m_adapter = new Color_adapter(getContext(), mcolor_item);
            spinner_to_choose_the_color_event.setAdapter(m_adapter);
            spinner_to_choose_the_color_event.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Spinner spinner_to_choose_the_color_event = mview.findViewById(R.id.spinner_to_choose_the_color_event);
                    spinner_to_choose_the_color_event.requestFocus();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    private void clear_all_the_focus() {
        EditText enter_the_name_of_the_event_you_want_to_add = mview.findViewById(R.id.enter_the_name_of_the_event_you_want_to_add);
        EditText enter_the_description_of_the_edit_text = mview.findViewById(R.id.enter_the_description_of_the_edit_text);
        enter_the_name_of_the_event_you_want_to_add.clearFocus();
        enter_the_description_of_the_edit_text.clearFocus();
        ConstraintLayout add_events_layout_in_dialog = mview.findViewById(R.id.add_events_layout_in_dialog);
        ConstraintLayout show_events_layout_in_dialog = mview.findViewById(R.id.show_events_layout_in_dialog);
        if (add_events_layout_in_dialog.getVisibility() == View.VISIBLE) {
            add_events_layout_in_dialog.requestFocus();
        } else {
            show_events_layout_in_dialog.requestLayout();
        }
    }

    private void cancel_and_ok_watcher() {
        Button cancel_button_on_the_left_events = mview.findViewById(R.id.cancel_button_on_the_left_events);
        Button ok_button_on_the_right_events = mview.findViewById(R.id.ok_button_on_the_right_events);
        cancel_button_on_the_left_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        ok_button_on_the_right_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button add_events_button_inside_dialog = mview.findViewById(R.id.add_events_button_inside_dialog);
                if (add_events_button_inside_dialog.getCurrentTextColor() == Color.WHITE) {
                    save_the_info();
                } else {
                    getDialog().dismiss();
                }
            }
        });
    }

    private void set_the_spinner() {
        if (getContext() != null) {
            ArrayList<String> spinnerArray = new ArrayList<>();
            spinnerArray.add("AM");
            spinnerArray.add("PM");
            Spinner spinner = mview.findViewById(R.id.am_or_pm_spinner);
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_layout_for_reason, spinnerArray);
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item_layout);
            spinner.setAdapter(spinnerArrayAdapter);
        }
    }

    private void text_watcher() {
        final EditText hour_for_the_reminder_beside_check_box = mview.findViewById(R.id.hour_for_the_reminder_beside_check_box);
        final EditText minute_for_the_reminder_beside_check_box = mview.findViewById(R.id.minute_for_the_reminder_beside_check_box);
        hour_for_the_reminder_beside_check_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!hour_for_the_reminder_beside_check_box.getText().toString().equals("")) {
                    if (Integer.parseInt(hour_for_the_reminder_beside_check_box.getText().toString()) > 12) {
                        hour_for_the_reminder_beside_check_box.clearFocus();
                        hour_for_the_reminder_beside_check_box.setText("12");
                        hour_for_the_reminder_beside_check_box.requestFocus();
                        hour_for_the_reminder_beside_check_box.setSelection(hour_for_the_reminder_beside_check_box.getText().toString().length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        minute_for_the_reminder_beside_check_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!minute_for_the_reminder_beside_check_box.getText().toString().equals("")) {
                    if (Integer.parseInt(minute_for_the_reminder_beside_check_box.getText().toString()) > 59) {
                        minute_for_the_reminder_beside_check_box.clearFocus();
                        minute_for_the_reminder_beside_check_box.setText("59");
                        minute_for_the_reminder_beside_check_box.requestFocus();
                        minute_for_the_reminder_beside_check_box.setSelection(minute_for_the_reminder_beside_check_box.getText().toString().length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void time_button_watchers() {
        final Button button_to_set_specific_time = mview.findViewById(R.id.button_to_set_specific_time);
        final Button button_to_set_as_all_day = mview.findViewById(R.id.button_to_set_as_all_day);
        final EditText hour_for_the_reminder_beside_check_box = mview.findViewById(R.id.hour_for_the_reminder_beside_check_box);
        final EditText minute_for_the_reminder_beside_check_box = mview.findViewById(R.id.minute_for_the_reminder_beside_check_box);
        button_to_set_specific_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() != null) {
                    DialogFragment Time_picker_for_notification = new Time_picker_for_notification();
                    Time_picker_for_notification.setTargetFragment(Add_and_show_events.this, 0);
                    Time_picker_for_notification.show(getActivity().getSupportFragmentManager(), "time picker");
                }
            }
        });
        button_to_set_as_all_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_to_set_as_all_day.setBackgroundResource(R.drawable.color_for_botton_on);
                button_to_set_as_all_day.setTextColor(Color.WHITE);
                button_to_set_specific_time.setBackgroundResource(R.drawable.color_for_botton_off);
                button_to_set_specific_time.setTextColor(Color.parseColor("#607D8B"));
                hour_for_the_reminder_beside_check_box.setText("8");
                minute_for_the_reminder_beside_check_box.setText("00");
                Spinner am_or_pm_spinner = mview.findViewById(R.id.am_or_pm_spinner);
                am_or_pm_spinner.setSelection(0);
                button_to_set_specific_time.setText("  Certain Time  ");
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        Button button_to_set_specific_time = mview.findViewById(R.id.button_to_set_specific_time);
        Button button_to_set_as_all_day = mview.findViewById(R.id.button_to_set_as_all_day);
        button_to_set_specific_time.setBackgroundResource(R.drawable.color_for_botton_on);
        button_to_set_specific_time.setTextColor(Color.WHITE);
        button_to_set_as_all_day.setBackgroundResource(R.drawable.color_for_botton_off);
        button_to_set_as_all_day.setTextColor(Color.parseColor("#607D8B"));
        String time_and_hour = set_and_return_the_time(hour, minute);
        button_to_set_specific_time.setText(time_and_hour);
        button_to_set_specific_time.setVisibility(View.VISIBLE);
        EditText hour_for_the_reminder_beside_check_box = mview.findViewById(R.id.hour_for_the_reminder_beside_check_box);
        EditText minute_for_the_reminder_beside_check_box = mview.findViewById(R.id.minute_for_the_reminder_beside_check_box);
        String[] splitter = time_and_hour.split(" ");
        String[] hour_minute_split = splitter[0].split(":");
        hour_for_the_reminder_beside_check_box.setText(hour_minute_split[0]);
        minute_for_the_reminder_beside_check_box.setText(hour_minute_split[1]);
        Spinner am_or_pm_spinner = mview.findViewById(R.id.am_or_pm_spinner);
        if (splitter[1].equals("AM")) {
            am_or_pm_spinner.setSelection(0);
        } else {
            am_or_pm_spinner.setSelection(1);
        }
        hour_for_the_reminder_beside_check_box.clearFocus();
        minute_for_the_reminder_beside_check_box.clearFocus();
        ConstraintLayout add_events_layout_in_dialog = mview.findViewById(R.id.add_events_layout_in_dialog);
        add_events_layout_in_dialog.requestFocus();
    }

    private String set_and_return_the_time(int hour, int minute) {
        String text_hour;
        String text_minute;
        String am_pm;
        if (hour >= 12) {
            if (hour == 12) {
                text_hour = "12";
            } else {
                text_hour = String.valueOf(hour - 12);
            }
            am_pm = "PM";
        } else {
            if (hour == 0) {
                text_hour = "12";
            } else {
                text_hour = String.valueOf(hour);
            }
            am_pm = "AM";
        }
        if (minute >= 10) {
            text_minute = String.valueOf(minute);
        } else {
            text_minute = "0".concat(String.valueOf(minute));
        }
        return text_hour.concat(":").concat(text_minute).concat(" ").concat(am_pm);
    }

    private int dip_to_pixels(float dip) {
        Resources r = getResources();
        float pixels = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );
        return Math.round(pixels);
    }

    private void save_the_info() {
        EditText enter_the_name_of_the_event_you_want_to_add = mview.findViewById(R.id.enter_the_name_of_the_event_you_want_to_add);
        EditText enter_the_description_of_the_edit_text = mview.findViewById(R.id.enter_the_description_of_the_edit_text);
        Spinner spinner_to_choose_the_color_event = mview.findViewById(R.id.spinner_to_choose_the_color_event);
        Button button_to_set_as_all_day = mview.findViewById(R.id.button_to_set_as_all_day);
        Button button_to_set_specific_time = mview.findViewById(R.id.button_to_set_specific_time);
        EditText hour_for_the_reminder_beside_check_box = mview.findViewById(R.id.hour_for_the_reminder_beside_check_box);
        EditText minute_for_the_reminder_beside_check_box = mview.findViewById(R.id.minute_for_the_reminder_beside_check_box);
        CheckBox check_box_asking_to_show_notification = mview.findViewById(R.id.check_box_asking_to_show_notification);
        String event_name = enter_the_name_of_the_event_you_want_to_add.getText().toString().trim();
        String event_description = enter_the_description_of_the_edit_text.getText().toString().trim();
        String selected_color = return_the_color_name();
        String all_day_or_certain_time;
        String checked_or_no = String.valueOf(check_box_asking_to_show_notification.isChecked());
        if (button_to_set_as_all_day.getCurrentTextColor() == Color.WHITE) {
            all_day_or_certain_time = "all_day";
        } else {
            all_day_or_certain_time = button_to_set_specific_time.getText().toString();
        }
        String reminder_time;
        if (!event_name.equals("")) {
            if (!hour_for_the_reminder_beside_check_box.getText().toString().equals("") && !minute_for_the_reminder_beside_check_box.getText().toString().equals("")) {
                reminder_time = return_the_time();
                if (getActivity() != null) {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("events_shared", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    String old_string = sharedPreferences.getString("events", "");
                    if (old_string == null) {
                        old_string = "";
                    }
                    String save_the_milli = String.valueOf(convert_time_to_milli(get_the_tag(), all_day_or_certain_time));
                    Log.w("testing_the_system", save_the_milli);
                    myEdit.putString("events", old_string.concat(save_the_milli).concat("split").concat(event_name).concat("split").concat(event_description).concat("split").concat(selected_color).concat("split").concat(all_day_or_certain_time).concat("split").concat(reminder_time).concat("split").concat(checked_or_no).concat("max_divide"));
                    myEdit.apply();
                    getDialog().dismiss();
                }
            } else {
                if (hour_for_the_reminder_beside_check_box.getText().toString().equals("")) {
                    hour_for_the_reminder_beside_check_box.setError("Time can't be empty");
                    Toast.makeText(getContext(), "Please indicate the reminder time", Toast.LENGTH_SHORT).show();
                } else if (minute_for_the_reminder_beside_check_box.getText().toString().equals("")) {
                    minute_for_the_reminder_beside_check_box.setError("Time Can't be empty");
                    Toast.makeText(getContext(), "Please indicate the reminder time", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            enter_the_name_of_the_event_you_want_to_add.setError("Name can't be empty");
            Toast.makeText(getContext(), "Please indicate the event name", Toast.LENGTH_SHORT).show();
        }
    }

    private String return_the_color_name() {
        Spinner spinner_to_choose_the_color_event = mview.findViewById(R.id.spinner_to_choose_the_color_event);
        int item_position = spinner_to_choose_the_color_event.getSelectedItemPosition();
        if (item_position == 0) {
            return "Teal";
        } else if (item_position == 1) {
            return "Red";
        } else if (item_position == 2) {
            return "Green";
        } else if (item_position == 3) {
            return "Yellow";
        } else if (item_position == 4) {
            return "Blue";
        } else if (item_position == 5) {
            return "Orange";
        } else if (item_position == 6) {
            return "Purple";
        } else if (item_position == 7) {
            return "Cyan";
        } else if (item_position == 8) {
            return "Magenta";
        } else if (item_position == 9) {
            return "Lime";
        } else if (item_position == 10) {
            return "Pink";
        } else if (item_position == 11) {
            return "Brown";
        } else if (item_position == 12) {
            return "Navy";
        } else {
            return "Black";
        }
    }

    private String return_the_time() {
        EditText hour_for_the_reminder_beside_check_box = mview.findViewById(R.id.hour_for_the_reminder_beside_check_box);
        EditText minute_for_the_reminder_beside_check_box = mview.findViewById(R.id.minute_for_the_reminder_beside_check_box);
        Spinner am_or_pm_spinner = mview.findViewById(R.id.am_or_pm_spinner);
        String hour = hour_for_the_reminder_beside_check_box.getText().toString();
        String minute = minute_for_the_reminder_beside_check_box.getText().toString();
        String am_pm;
        if (am_or_pm_spinner.getSelectedItemPosition() == 0) {
            am_pm = "AM";
        } else {
            am_pm = "PM";
        }
        return hour.concat(minute).concat(am_pm);
    }

    private void check_if_event_is_in_the_past() {
        String tag = getTag();
        if (tag != null) {
            Calendar calendar = Calendar.getInstance();
            int real_day = calendar.get(Calendar.DAY_OF_MONTH);
            int real_month = calendar.get(Calendar.MONTH) + 1;
            int real_year = calendar.get(Calendar.YEAR);
            String[] splitter = tag.split("split");
            String[] day_month_year = splitter[1].split("/");
            int day = Integer.parseInt(day_month_year[0]);
            int month = Integer.parseInt(day_month_year[1]);
            int year = Integer.parseInt(day_month_year[2]);
            ConstraintLayout layout_only_for_reminder_time = mview.findViewById(R.id.layout_only_for_reminder_time);
            if (year == real_year) {
                if (month == real_month) {
                    if (day < real_day) {
                        layout_only_for_reminder_time.setVisibility(View.GONE);
                    }
                } else if (month < real_month) {
                    layout_only_for_reminder_time.setVisibility(View.GONE);
                }
            } else if (year < real_year) {
                layout_only_for_reminder_time.setVisibility(View.GONE);
            }
        }
    }

    private void show_the_today_events() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("events_shared", MODE_PRIVATE);
            String string = sharedPreferences.getString("events", "");
            if (string != null && !string.equals("")) {
                String[] splitter = string.split("max_divide");
                example_item_for_showing_events = new ArrayList<>();
                for (int i = 0; i < splitter.length; i++) {
                    String[] split_the_split = splitter[i].split("split");
                    if (convert_milli_to_date(Long.parseLong(split_the_split[0])).equals(get_the_tag())) {
                        String time;
                        String am_or_pm;
                        if (split_the_split[4].equals("all_day")) {
                            time = "All Day";
                            am_or_pm = "";
                        } else {
                            String[] split_number_four = convert_milli_to_time(Long.parseLong(split_the_split[0])).split(" ");
                            time = split_number_four[0];
                            am_or_pm = split_number_four[1];
                        }
                        String short_text = set_the_width(split_the_split[1]);
                        String short_describe = set_the_width_description(split_the_split[2]);
                        if (split_the_split[3].equals("Teal")) {
                            example_item_for_showing_events.add(new Example_item_for_showing_events(R.drawable.z_dark_green, short_text, short_describe, R.drawable.baseline_delete_dark_green, time, am_or_pm));
                        } else if (split_the_split[3].equals("Red")) {
                            example_item_for_showing_events.add(new Example_item_for_showing_events(R.drawable.z_red, short_text, short_describe, R.drawable.baseline_delete_red, time, am_or_pm));
                        } else if (split_the_split[3].equals("Green")) {
                            example_item_for_showing_events.add(new Example_item_for_showing_events(R.drawable.z_green, short_text, short_describe, R.drawable.baseline_delete_green, time, am_or_pm));
                        } else if (split_the_split[3].equals("Yellow")) {
                            example_item_for_showing_events.add(new Example_item_for_showing_events(R.drawable.z_yellow, short_text, short_describe, R.drawable.baseline_delete_yellow, time, am_or_pm));
                        } else if (split_the_split[3].equals("Blue")) {
                            example_item_for_showing_events.add(new Example_item_for_showing_events(R.drawable.z_blue, short_text, short_describe, R.drawable.baseline_delete_blue, time, am_or_pm));
                        } else if (split_the_split[3].equals("Orange")) {
                            example_item_for_showing_events.add(new Example_item_for_showing_events(R.drawable.z_orange, short_text, short_describe, R.drawable.baseline_delete_orange, time, am_or_pm));
                        } else if (split_the_split[3].equals("Purple")) {
                            example_item_for_showing_events.add(new Example_item_for_showing_events(R.drawable.z_purple, short_text, short_describe, R.drawable.baseline_delete_purple, time, am_or_pm));
                        } else if (split_the_split[3].equals("Cyan")) {
                            example_item_for_showing_events.add(new Example_item_for_showing_events(R.drawable.z_cyan, short_text, short_describe, R.drawable.baseline_delete_cyan, time, am_or_pm));
                        } else if (split_the_split[3].equals("Magenta")) {
                            example_item_for_showing_events.add(new Example_item_for_showing_events(R.drawable.z_magenta, short_text, short_describe, R.drawable.baseline_delete_magenta, time, am_or_pm));
                        } else if (split_the_split[3].equals("Lime")) {
                            example_item_for_showing_events.add(new Example_item_for_showing_events(R.drawable.z_lime, short_text, short_describe, R.drawable.baseline_delete_lime, time, am_or_pm));
                        } else if (split_the_split[3].equals("Pink")) {
                            example_item_for_showing_events.add(new Example_item_for_showing_events(R.drawable.z_pink, short_text, short_describe, R.drawable.baseline_delete_pink, time, am_or_pm));
                        } else if (split_the_split[3].equals("Brown")) {
                            example_item_for_showing_events.add(new Example_item_for_showing_events(R.drawable.z_brown, short_text, short_describe, R.drawable.baseline_delete_brown, time, am_or_pm));
                        } else if (split_the_split[3].equals("Navy")) {
                            example_item_for_showing_events.add(new Example_item_for_showing_events(R.drawable.z_navy, short_text, short_describe, R.drawable.baseline_delete_navy, time, am_or_pm));
                        } else if (split_the_split[3].equals("Black")) {
                            example_item_for_showing_events.add(new Example_item_for_showing_events(R.drawable.z_black, short_text, short_describe, R.drawable.baseline_delete_black, time, am_or_pm));
                        }
                    }
                }
                mrecycle_view = mview.findViewById(R.id.today_events_recycle_view);
                mrecycle_view.setHasFixedSize(false);
                mlayout_manager = new LinearLayoutManager(getContext());
                madapter = new Example_adapter_to_show(example_item_for_showing_events);
                mrecycle_view.setLayoutManager(mlayout_manager);
                mrecycle_view.setAdapter(madapter);
                madapter.setOnItemClick_listener(new Example_adapter_to_show.OnItemClickListener() {
                    @Override
                    public void onDeleteClick(final int position) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Delete event")
                                .setMessage("Are you sure you want to delete this event?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        removeItem(position);
                                    }
                                })
                                .setNegativeButton(android.R.string.cancel, null)
                                .show();
                    }
                });

            }
        }
    }

    private String get_the_tag() {
        String tag = getTag();
        if (tag != null) {
            String[] splitter = tag.split("split");
            return splitter[1];
        } else {
            return "";
        }
    }

    /*private static String get_the_next_days(String start_date, int i) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(start_date));
            calendar.add(Calendar.DATE, i);  // number of days to add
            return simpleDateFormat.format(calendar.getTime());  // dt is now the new date
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return start_date;
    }*/

    /*private String return_today_right() {
        Calendar calendar = Calendar.getInstance();
        String real_year = String.valueOf(calendar.get(Calendar.YEAR));
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String real_day;
        String real_month;
        if (month >= 10) {
            real_month = String.valueOf(month);
        } else {
            real_month = "0".concat(String.valueOf(month));
        }
        if (day >= 10) {
            real_day = String.valueOf(day);
        } else {
            real_day = "0".concat(String.valueOf(month));
        }
        return real_day.concat("/").concat(real_month).concat("/").concat(real_year);
    }*/

    private void call_the_recycle_view() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("events_shared", MODE_PRIVATE);
            String string = sharedPreferences.getString("events", "");
            boolean contains_today = false;
            boolean contains_next_week = false;
            if (string != null && !string.equals("")) {
                String[] splitter = string.split("max_divide");
                for (int i = 0; i < splitter.length; i++) {
                    String[] split_the_split = splitter[i].split("split");
                    if (convert_milli_to_date(Long.parseLong(split_the_split[0])).equals(get_the_tag())) {
                        contains_today = true;
                    }
                }
            }
            if (string != null && !string.equals("")) {
                String[] splitter = string.split("max_divide");
                for (int i = 0; i < splitter.length; i++) {
                    String[] split_the_split = splitter[i].split("split");
                    if (Long.parseLong(split_the_split[0]) > time_in_right_now && Long.parseLong(split_the_split[0]) < (time_in_right_now + Time_for_calculation.time_left_mid_night() + 604800000)) {
                        contains_next_week = true;
                    }
                }
            }
            if (contains_today && contains_next_week) {
                show_the_today_events();
                show_next_week_events();
            } else if (contains_today) {
                show_the_today_events();
                RecyclerView upcoming_events_recycle_view = mview.findViewById(R.id.upcoming_events_recycle_view);
                upcoming_events_recycle_view.setVisibility(View.INVISIBLE);
                TextView text_telling_upcoming_events = mview.findViewById(R.id.text_telling_upcoming_events);
                TextView text_telling_no_upcoming_events = mview.findViewById(R.id.text_telling_no_upcoming_events);
                text_telling_no_upcoming_events.setVisibility(View.VISIBLE);
                ConstraintLayout constraintLayout = mview.findViewById(R.id.show_events_layout_in_dialog);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.clear(text_telling_upcoming_events.getId(), ConstraintSet.TOP);
                constraintSet.connect(text_telling_upcoming_events.getId(), ConstraintSet.BOTTOM, text_telling_no_upcoming_events.getId(), ConstraintSet.TOP, dip_to_pixels(20));
                constraintSet.applyTo(constraintLayout);
            } else if (contains_next_week) {
                show_next_week_events();
                RecyclerView today_events_recycle_view = mview.findViewById(R.id.today_events_recycle_view);
                TextView text_telling_no_events_today = mview.findViewById(R.id.text_telling_no_events_today);
                TextView text_telling_upcoming_events = mview.findViewById(R.id.text_telling_upcoming_events);
                today_events_recycle_view.setVisibility(View.INVISIBLE);
                text_telling_no_events_today.setVisibility(View.VISIBLE);
                ConstraintLayout constraintLayout = mview.findViewById(R.id.show_events_layout_in_dialog);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.clear(text_telling_upcoming_events.getId(), ConstraintSet.BOTTOM);
                constraintSet.connect(text_telling_upcoming_events.getId(), ConstraintSet.TOP, text_telling_no_events_today.getId(), ConstraintSet.BOTTOM, dip_to_pixels(20));
                constraintSet.applyTo(constraintLayout);
            } else {
                RecyclerView upcoming_events_recycle_view = mview.findViewById(R.id.upcoming_events_recycle_view);
                RecyclerView today_events_recycle_view = mview.findViewById(R.id.today_events_recycle_view);
                ConstraintLayout constraintLayout = mview.findViewById(R.id.show_events_layout_in_dialog);
                TextView text_telling_no_events_today = mview.findViewById(R.id.text_telling_no_events_today);
                TextView text_telling_no_upcoming_events = mview.findViewById(R.id.text_telling_no_upcoming_events);
                TextView text_telling_upcoming_events = mview.findViewById(R.id.text_telling_upcoming_events);
                TextView events_for_a_certain_date = mview.findViewById(R.id.events_for_a_certain_date);
                upcoming_events_recycle_view.setVisibility(View.INVISIBLE);
                today_events_recycle_view.setVisibility(View.INVISIBLE);
                text_telling_no_events_today.setVisibility(View.VISIBLE);
                text_telling_no_upcoming_events.setVisibility(View.VISIBLE);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.clear(text_telling_no_events_today.getId(), ConstraintSet.TOP);
                constraintSet.clear(text_telling_no_upcoming_events.getId(), ConstraintSet.BOTTOM);
                constraintSet.connect(text_telling_no_events_today.getId(), ConstraintSet.TOP, today_events_recycle_view.getId(), ConstraintSet.TOP, 0);
                constraintSet.connect(text_telling_no_events_today.getId(), ConstraintSet.BOTTOM, today_events_recycle_view.getId(), ConstraintSet.BOTTOM, 0);
                constraintSet.connect(text_telling_no_upcoming_events.getId(), ConstraintSet.TOP, upcoming_events_recycle_view.getId(), ConstraintSet.TOP, 0);
                constraintSet.connect(text_telling_no_upcoming_events.getId(), ConstraintSet.BOTTOM, upcoming_events_recycle_view.getId(), ConstraintSet.BOTTOM, 0);
                constraintSet.connect(text_telling_upcoming_events.getId(), ConstraintSet.TOP, events_for_a_certain_date.getId(), ConstraintSet.BOTTOM, 0);
                constraintSet.connect(text_telling_upcoming_events.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
                constraintSet.applyTo(constraintLayout);
            }
        }
    }

    private void add_the_date() {
        String tag = getTag();
        if (tag != null) {
            TextView events_for_a_certain_date = mview.findViewById(R.id.events_for_a_certain_date);
            TextView text_telling_no_events_today = mview.findViewById(R.id.text_telling_no_events_today);
            String[] splitting_on_split = tag.split("split");
            String[] dates = splitting_on_split[1].split("/");
            Calendar calendar = Calendar.getInstance();
            int real_year = calendar.get(Calendar.YEAR);
            String set_me;
            String set_the_no_events;
            if (real_year == Integer.parseInt(dates[2])) {
                set_me = "Events for - ".concat(return_month(Integer.parseInt(dates[1]) - 1)).concat(" ").concat(dates[0]);
                set_the_no_events = "- No events for ".concat(return_month(Integer.parseInt(dates[1]) - 1)).concat(" ").concat(dates[0]).concat(" -");
            } else {
                set_me = "Events for - ".concat(return_month(Integer.parseInt(dates[1]) - 1)).concat(" ").concat(dates[0]).concat(", ").concat(String.valueOf(dates[2]));
                set_the_no_events = "- No events for ".concat(return_month(Integer.parseInt(dates[1]) - 1)).concat(" ").concat(dates[0]).concat(", ").concat(String.valueOf(dates[2])).concat(" -");
            }
            events_for_a_certain_date.setText(set_me);
            text_telling_no_events_today.setText(set_the_no_events);
        }
    }

    private void show_next_week_events() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("events_shared", MODE_PRIVATE);
            String string = sharedPreferences.getString("events", "");
            if (string != null && !string.equals("")) {
                String[] splitter = string.split("max_divide");
                example_item_for_showing_events_trash_cans = new ArrayList<>();
                for (int i = 0; i < splitter.length; i++) {
                    String[] split_the_split = splitter[i].split("split");
                    if (Long.parseLong(split_the_split[0]) > time_in_right_now && Long.parseLong(split_the_split[0]) < (time_in_right_now + Time_for_calculation.time_left_mid_night() + 604800000)) {
                        String time;
                        String am_or_pm;
                        if (split_the_split[4].equals("all_day")) {
                            time = "All Day";
                            am_or_pm = "";
                        } else {
                            String[] split_number_four = convert_milli_to_time(Long.parseLong(split_the_split[0])).split(" ");
                            time = split_number_four[0];
                            am_or_pm = split_number_four[1];
                        }
                        String[] split_to_days = return_month_and_day(Long.parseLong(split_the_split[0])).split(" ");
                        String first_month = split_to_days[0].concat("\n").concat(split_to_days[1]);
                        String short_text = set_the_width_main_trash_can(split_the_split[1]);
                        String short_describe = set_the_width_description_trash(split_the_split[2]);
                        if (split_the_split[3].equals("Teal")) {
                            example_item_for_showing_events_trash_cans.add(new Example_item_for_showing_events_trash_can(R.drawable.z_dark_green, short_text, short_describe, R.drawable.baseline_delete_dark_green, time, am_or_pm, first_month));
                        } else if (split_the_split[3].equals("Red")) {
                            example_item_for_showing_events_trash_cans.add(new Example_item_for_showing_events_trash_can(R.drawable.z_red, short_text, short_describe, R.drawable.baseline_delete_red, time, am_or_pm, first_month));
                        } else if (split_the_split[3].equals("Green")) {
                            example_item_for_showing_events_trash_cans.add(new Example_item_for_showing_events_trash_can(R.drawable.z_green, short_text, short_describe, R.drawable.baseline_delete_green, time, am_or_pm, first_month));
                        } else if (split_the_split[3].equals("Yellow")) {
                            example_item_for_showing_events_trash_cans.add(new Example_item_for_showing_events_trash_can(R.drawable.z_yellow, short_text, short_describe, R.drawable.baseline_delete_yellow, time, am_or_pm, first_month));
                        } else if (split_the_split[3].equals("Blue")) {
                            example_item_for_showing_events_trash_cans.add(new Example_item_for_showing_events_trash_can(R.drawable.z_blue, short_text, short_describe, R.drawable.baseline_delete_blue, time, am_or_pm, first_month));
                        } else if (split_the_split[3].equals("Orange")) {
                            example_item_for_showing_events_trash_cans.add(new Example_item_for_showing_events_trash_can(R.drawable.z_orange, short_text, short_describe, R.drawable.baseline_delete_orange, time, am_or_pm, first_month));
                        } else if (split_the_split[3].equals("Purple")) {
                            example_item_for_showing_events_trash_cans.add(new Example_item_for_showing_events_trash_can(R.drawable.z_purple, short_text, short_describe, R.drawable.baseline_delete_purple, time, am_or_pm, first_month));
                        } else if (split_the_split[3].equals("Cyan")) {
                            example_item_for_showing_events_trash_cans.add(new Example_item_for_showing_events_trash_can(R.drawable.z_cyan, short_text, short_describe, R.drawable.baseline_delete_cyan, time, am_or_pm, first_month));
                        } else if (split_the_split[3].equals("Magenta")) {
                            example_item_for_showing_events_trash_cans.add(new Example_item_for_showing_events_trash_can(R.drawable.z_magenta, short_text, short_describe, R.drawable.baseline_delete_magenta, time, am_or_pm, first_month));
                        } else if (split_the_split[3].equals("Lime")) {
                            example_item_for_showing_events_trash_cans.add(new Example_item_for_showing_events_trash_can(R.drawable.z_lime, short_text, short_describe, R.drawable.baseline_delete_lime, time, am_or_pm, first_month));
                        } else if (split_the_split[3].equals("Pink")) {
                            example_item_for_showing_events_trash_cans.add(new Example_item_for_showing_events_trash_can(R.drawable.z_pink, short_text, short_describe, R.drawable.baseline_delete_pink, time, am_or_pm, first_month));
                        } else if (split_the_split[3].equals("Brown")) {
                            example_item_for_showing_events_trash_cans.add(new Example_item_for_showing_events_trash_can(R.drawable.z_brown, short_text, short_describe, R.drawable.baseline_delete_brown, time, am_or_pm, first_month));
                        } else if (split_the_split[3].equals("Navy")) {
                            example_item_for_showing_events_trash_cans.add(new Example_item_for_showing_events_trash_can(R.drawable.z_navy, short_text, short_describe, R.drawable.baseline_delete_navy, time, am_or_pm, first_month));
                        } else if (split_the_split[3].equals("Black")) {
                            example_item_for_showing_events_trash_cans.add(new Example_item_for_showing_events_trash_can(R.drawable.z_black, short_text, short_describe, R.drawable.baseline_delete_black, time, am_or_pm, first_month));
                        }
                    }
                }
            }
            mrecycle_view_trash = mview.findViewById(R.id.upcoming_events_recycle_view);
            mrecycle_view_trash.setHasFixedSize(false);
            mlayout_manager_trash = new LinearLayoutManager(getContext());
            madapter_trash = new Example_adapter_to_show_trash_can(example_item_for_showing_events_trash_cans);
            mrecycle_view_trash.setLayoutManager(mlayout_manager_trash);
            mrecycle_view_trash.setAdapter(madapter_trash);
            madapter_trash.setOnItemClick_listener(new Example_adapter_to_show_trash_can.OnItemClickListener() {
                @Override
                public void onDeleteClick(final int position) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Delete event")
                            .setMessage("Are you sure you want to delete this event?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    removeItem_for_trash(position);
                                }
                            })
                            .setNegativeButton(android.R.string.cancel, null)
                            .show();
                }
            });
        }
    }

    private String return_month(int month) {
        if (month == 0) {
            return "January";
        } else if (month == 1) {
            return "February";
        } else if (month == 2) {
            return "March";
        } else if (month == 3) {
            return "April";
        } else if (month == 4) {
            return "May";
        } else if (month == 5) {
            return "June";
        } else if (month == 6) {
            return "July";
        } else if (month == 7) {
            return "August";
        } else if (month == 8) {
            return "September";
        } else if (month == 9) {
            return "October";
        } else if (month == 10) {
            return "November";
        } else {
            return "December";
        }
    }

    private String return_month_short(int month) {
        if (month == 1) {
            return "Jan";
        } else if (month == 2) {
            return "Feb.";
        } else if (month == 3) {
            return "Mar.";
        } else if (month == 4) {
            return "Apr.";
        } else if (month == 5) {
            return "May";
        } else if (month == 6) {
            return "Jun.";
        } else if (month == 7) {
            return "Jul.";
        } else if (month == 8) {
            return "Aug.";
        } else if (month == 9) {
            return "Sep.";
        } else if (month == 10) {
            return "Oct.";
        } else if (month == 11) {
            return "Nov.";
        } else {
            return "Dec.";
        }
    }

    private String set_the_width(String text) {
        if (getActivity() != null) {
            float right_width = real_width - dip_to_pixels(120 + 2);
            TextPaint paint = new TextPaint();
            paint.setTextSize(spToPx(20));
            float text_width = paint.measureText(text);
            if (text_width > right_width) {
                text = text.concat("...");
                StringBuilder builder = new StringBuilder(text);
                while (text_width > right_width) {
                    if (builder.toString().length() > 4) {
                        builder.deleteCharAt(builder.toString().length() - 4);
                        text_width = paint.measureText(builder.toString());
                    } else {
                        break;
                    }
                }
                return builder.toString();
            }
        }
        return text;
    }

    private String set_the_width_description(String text) {
        if (getActivity() != null) {
            float right_width = real_width - dip_to_pixels(120 + 2);
            TextPaint paint = new TextPaint();
            paint.setTextSize(spToPx(15));
            float text_width = paint.measureText(text);
            if (text_width > right_width) {
                text = text.concat("...");
                StringBuilder builder = new StringBuilder(text);
                while (text_width > right_width) {
                    if (builder.toString().length() > 4) {
                        builder.deleteCharAt(builder.toString().length() - 4);
                        text_width = paint.measureText(builder.toString());
                    } else {
                        break;
                    }
                }
                return builder.toString();
            }
        }
        return text;
    }

    private String set_the_width_main_trash_can(String text) {
        if (getActivity() != null) {
            float right_width = real_width - dip_to_pixels(158 + 2);
            TextPaint paint = new TextPaint();
            paint.setTextSize(spToPx(20));
            float text_width = paint.measureText(text);
            if (text_width > right_width) {
                text = text.concat("...");
                StringBuilder builder = new StringBuilder(text);
                while (text_width > right_width) {
                    if (builder.toString().length() > 4) {
                        builder.deleteCharAt(builder.toString().length() - 4);
                        text_width = paint.measureText(builder.toString());
                    } else {
                        break;
                    }
                }
                return builder.toString();
            }
        }
        return text;
    }

    private String set_the_width_description_trash(String text) {
        if (getActivity() != null) {
            float right_width = real_width - dip_to_pixels(158 + 2);
            TextPaint paint = new TextPaint();
            paint.setTextSize(spToPx(15));
            float text_width = paint.measureText(text);
            if (text_width > right_width) {
                text = text.concat("...");
                StringBuilder builder = new StringBuilder(text);
                while (text_width > right_width) {
                    if (builder.toString().length() > 4) {
                        builder.deleteCharAt(builder.toString().length() - 4);
                        text_width = paint.measureText(builder.toString());
                    } else {
                        break;
                    }
                }
                return builder.toString();
            }
        }
        return text;
    }

    private int spToPx(float sp) {
        if (getContext() != null) {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getContext().getResources().getDisplayMetrics());
        } else {
            return 0;
        }
    }

    private void organize_the_events() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("events_shared", MODE_PRIVATE);
            String string = sharedPreferences.getString("events", "");
            if (string != null && !string.equals("")) {
                ArrayList<Long> only_The_time_milli = new ArrayList<>();
                String[] the_biggest_splitter = string.split("max_divide");
                for (int i = 0; i < the_biggest_splitter.length; i++) {
                    String[] split_the_split = the_biggest_splitter[i].split("split");
                    only_The_time_milli.add(Long.parseLong(split_the_split[0]));
                }
                Collections.sort(only_The_time_milli);
                String save_me = "";
                for (int i =0;i<only_The_time_milli.size();i++){
                    for (int j= 0;j<the_biggest_splitter.length;j++){
                        if(the_biggest_splitter[j].contains(String.valueOf(only_The_time_milli.get(i)))) {
                            save_me = save_me.concat(the_biggest_splitter[i]).concat("max_divide");
                            break;
                        }
                    }
                }
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("events", save_me);
                myEdit.apply();
            }
        }
    }

    public void removeItem(int position) {
        example_item_for_showing_events.remove(position);
        madapter.notifyItemRemoved(position);
        remove_item_from_today_list(position);
        call_the_recycle_view();
    }

    public void removeItem_for_trash(int position) {
        example_item_for_showing_events_trash_cans.remove(position);
        madapter_trash.notifyItemRemoved(position);
        remove_item_from_future_list(position);
        call_the_recycle_view();
    }

    private void remove_item_from_today_list(int position) {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("events_shared", MODE_PRIVATE);
            String string = sharedPreferences.getString("events", "");
            if (string != null && !string.equals("")) {
                String[] splitter = string.split("max_divide");
                ArrayList<String> everything_but_split = new ArrayList<>();
                for (int i = 0; i < splitter.length; i++) {
                    String[] split_the_split = splitter[i].split("split");
                    if (convert_milli_to_date(Long.parseLong(split_the_split[0])).equals(get_the_tag())) {
                        everything_but_split.add(splitter[i]);
                    }
                }
                String save_me="";
                for (int i = 0; i < splitter.length; i++) {
                    if(!everything_but_split.get(position).equals(splitter[i])){
                        save_me = save_me.concat(splitter[i]).concat("max_divide");
                    }
                }
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("events",  save_me);
                myEdit.commit();
            }
        }
    }

    private long convert_time_to_milli(String day, String time) {
        if (getContext() != null) {
            String hour_in_string = "";
            String minute_in_string = "";
            if (time.equals("all_day")) {
                hour_in_string = "00";
                minute_in_string = "00";
            } else {
                String[] time_fixer1 = time.split(" ");
                String[] time_fixer2 = time_fixer1[0].split(":");
                int hours_from_time = Integer.parseInt(time_fixer2[0]);
                int minutes_from_time = Integer.parseInt(time_fixer2[1]);
                if (hours_from_time < 12 && time_fixer1[1].equals("AM")) {
                    if (hours_from_time >= 10) {
                        hour_in_string = time_fixer2[0];
                    } else {
                        hour_in_string = "0".concat(time_fixer2[0]);
                    }
                    if (minutes_from_time >= 10) {
                        minute_in_string = time_fixer2[1];
                    } else {
                        minute_in_string = "0".concat(time_fixer2[1]);
                    }
                } else if (hours_from_time < 12 && time_fixer1[1].equals("PM")) {
                    hour_in_string = String.valueOf(hours_from_time + 12);
                    if (minutes_from_time >= 10) {
                        minute_in_string = time_fixer2[1];
                    } else {
                        minute_in_string = "0".concat(time_fixer2[1]);
                    }
                } else if (hours_from_time == 12 && time_fixer1[1].equals("AM")) {
                    hour_in_string = "00";
                    if (minutes_from_time >= 10) {
                        minute_in_string = time_fixer2[1];
                    } else {
                        minute_in_string = "0".concat(time_fixer2[1]);
                    }
                } else if (hours_from_time == 12 && time_fixer1[1].equals("PM")) {
                    hour_in_string = "12";
                    if (minutes_from_time >= 10) {
                        minute_in_string = time_fixer2[1];
                    } else {
                        minute_in_string = "0".concat(time_fixer2[1]);
                    }
                }
            }
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            try {
                Date date = formatter.parse(day.concat(" ").concat(hour_in_string).concat(":").concat(minute_in_string));
                if (date != null) {
                    return date.getTime();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    private String convert_milli_to_date(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    private String convert_milli_to_time(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        String start_string = formatter.format(calendar.getTime());
        String[] split = start_string.split(":");
        String am_pm = "";
        String final_hour = "";
        String final_minute = split[1];
        int first_hour = Integer.parseInt(split[0]);
        if (first_hour < 12) {
            if (first_hour == 0) {
                am_pm = "AM";
                final_hour = "12";
            } else {
                am_pm = "AM";
                final_hour = String.valueOf(first_hour);
            }
        } else {
            if (first_hour == 12) {
                am_pm = "PM";
                final_hour = "12";
            } else {
                am_pm = "PM";
                final_hour = String.valueOf(first_hour - 12);
            }
        }
        return final_hour.concat(":").concat(final_minute).concat(" ").concat(am_pm);
    }

    private String return_month_and_day(long milliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        String month_and_day_first = formatter.format(calendar.getTime());
        String[] splitter = month_and_day_first.split("_");
        String month = return_month_short(Integer.parseInt(splitter[1]));
        String day = String.valueOf(Integer.parseInt(splitter[0]));
        return month.concat(" ").concat(day);
    }
    private void remove_item_from_future_list(int position) {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("events_shared", MODE_PRIVATE);
            String string = sharedPreferences.getString("events", "");
            if (string != null) {
                String[] splitter = string.split("max_divide");
                ArrayList<String> milli_but_in_string = new ArrayList<>();
                for (int i = 0; i < splitter.length; i++) {
                    String[] split_the_split = splitter[i].split("split");
                    if (Long.parseLong(split_the_split[0]) > time_in_right_now && Long.parseLong(split_the_split[0]) < (time_in_right_now + Time_for_calculation.time_left_mid_night() + 604800000)) {
                        milli_but_in_string.add(splitter[i]);
                    }
                }
                String save_me = "";
                for (int i =0;i<splitter.length;i++){
                    if(!splitter[i].contains(milli_but_in_string.get(position))){
                        save_me = save_me.concat(splitter[i]).concat("max_divide");
                    }
                }
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("events",  save_me);
                myEdit.commit();
            }
        }
    }
}
