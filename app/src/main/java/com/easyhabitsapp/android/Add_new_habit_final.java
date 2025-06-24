package com.easyhabitsapp.android;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.nex3z.togglebuttongroup.MultiSelectToggleGroup;
import com.nex3z.togglebuttongroup.button.CircularToggle;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;
import com.vanniktech.emoji.EmojiEditText;
import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.EmojiPopup;
import com.vanniktech.emoji.EmojiTextView;
import com.vanniktech.emoji.EmojiTheming;
import com.vanniktech.emoji.google.GoogleEmojiProvider;
import com.vanniktech.emoji.traits.DisableKeyboardInputTrait;
import com.vanniktech.emoji.traits.ForceSingleEmojiTrait;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Add_new_habit_final extends Fragment {

    private String habit_name;
    private String habit_color;
    private String habit_icon;
    private String type_of_habit;
    private ArrayList<String> habit_frequency;
    private int habit_frequency_extra;
    private boolean notifications;
    private ArrayList<Notification_object_add_new_habit> notifications_extra;
    private EmojiPopup emojiPopup = null;
    private Adapter_add_reminder_add_new_habit adapter_for_reminder;
    private ArrayList<Example_item_reminder_add_new_habit> reminder_arraylist = new ArrayList<>();
    private boolean first_time_to_show_emoji = true;


    public Add_new_habit_final() {
        // Required empty public constructor
    }

    public Add_new_habit_final(String habit_name, String habit_color, String habit_icon, String type_of_habit, ArrayList<String> habit_frequency, int habit_frequency_extra, boolean notifications, ArrayList<Notification_object_add_new_habit> notifications_extra) {
        this.habit_name = habit_name;
        this.habit_color = habit_color;
        this.habit_icon = habit_icon;
        this.type_of_habit = type_of_habit;
        this.habit_frequency = habit_frequency;
        this.habit_frequency_extra = habit_frequency_extra;
        this.notifications = notifications;
        this.notifications_extra = notifications_extra;
    }

    public Add_new_habit_final(String habit_name, String habit_color, String habit_icon, String type_of_habit, ArrayList<String> habit_frequency, int habit_frequency_extra, boolean notifications) {
        this.habit_name = habit_name;
        this.habit_color = habit_color;
        this.habit_icon = habit_icon;
        this.type_of_habit = type_of_habit;
        this.habit_frequency = habit_frequency;
        this.habit_frequency_extra = habit_frequency_extra;
        this.notifications = notifications;
    }

    public Add_new_habit_final(String habit_name, String habit_color, String habit_icon, String type_of_habit, boolean notifications, ArrayList<Notification_object_add_new_habit> notifications_extra) {
        this.habit_name = habit_name;
        this.habit_color = habit_color;
        this.habit_icon = habit_icon;
        this.type_of_habit = type_of_habit;
        this.notifications = notifications;
        this.notifications_extra = notifications_extra;
    }

    public Add_new_habit_final(String habit_name, String habit_color, String habit_icon, String type_of_habit, boolean notifications) {
        this.habit_name = habit_name;
        this.habit_color = habit_color;
        this.habit_icon = habit_icon;
        this.type_of_habit = type_of_habit;
        this.notifications = notifications;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_new_habit_final, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        set_up_emoji_keyboard();
        done_button_listen();
        change_icon_button_listen_add_new_habit_final();
        build_up_and_quit_button_listen_in_the_end();
        set_the_name_edit_text();
        set_emoji_start();
        set_quite_or_build_start();
        set_up_frequency();
        listen_for_frequncy_spinner_item_select();
        color_everything();
        cancel_button_listen();
        set_up_frequncy_real();
        set_frequency_per_day();
        listen_to_swtich();
        set_up_emoji();
        emoji_text_watcher();
        layout_listen();
        set_up_recycle_view();
        add_notifications();
        check_reminders();
        listen_to_add_reminder_button();
    }

    private void done_button_listen() {
        if (getView() != null) {
            Button done_button_at_the_buttom_add_new_habit = getView().findViewById(R.id.done_button_at_the_buttom_add_new_habit);
            done_button_at_the_buttom_add_new_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    done_button_clicked();
                }
            });
        }
    }

    private void done_button_clicked() {
        if (done_button_check()) {
            habits_data_class habits_data_class = new habits_data_class();
            habits_data_class.setName_of_the_habit(return_name_of_the_habit());
            habits_data_class.setColor(habit_color);
            habits_data_class.setIcon(return_icon_of_the_habit());
            habits_data_class.setBad_or_good_habit(build_or_quit_habit());
            if (build_or_quit_habit().equals("Build_up")) {
                habits_data_class.setHabits_freq(return_frequency());
                habits_data_class.setHabits_freq_extra(return_frequency_extra());
            }
            habits_data_class.setNotifications_on_or_off(is_notifications_on());
            if (is_notifications_on()) {
                ArrayList<Notification_object_add_new_habit> notification_object = new ArrayList<>();
                for (int i = 0; i < reminder_arraylist.size(); i++) {
                    Notification_object_add_new_habit notification_object_add_new_habit = new Notification_object_add_new_habit(reminder_arraylist.get(i).getDays(), reminder_arraylist.get(i).getTime());
                    notification_object.add(notification_object_add_new_habit);
                }
                habits_data_class.setNotifications_freq(notification_object);
            }
            Room_database_habits database_habits = Room_database_habits.getInstance(getContext());
            long id = database_habits.dao_for_habits_data().insert(habits_data_class);
            Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.habit_added_bad, false);

            Toast.makeText(getContext(), "Habit created successfully!", Toast.LENGTH_SHORT).show();

            View_home_habit view_home_habit = new View_home_habit();
            Bundle args = new Bundle();
            args.putInt("which_id", (int) id);
            view_home_habit.setArguments(args);
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, view_home_habit, "view home: ".concat(String.valueOf(id))).hide(view_home_habit).commitNow();

            home_fragment new_fragment = (com.easyhabitsapp.android.home_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("home");
            Add_new_habit_final old_fragment = (Add_new_habit_final) getActivity().getSupportFragmentManager().findFragmentByTag("add a habit final");
            if (old_fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).show(new_fragment).commit();
            }
        }
    }

    private void color_the_done_button() {
        if (getView() != null) {
            Button done_button_at_the_buttom_add_new_habit = getView().findViewById(R.id.done_button_at_the_buttom_add_new_habit);
            done_button_at_the_buttom_add_new_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(habit_color)));
        }
    }

    private void set_tint_of_color_button_final() {
        if (getView() != null) {
            View drawable_with_color_at_the_start_of_color_button_add_new_habit = getView().findViewById(R.id.drawable_with_color_at_the_start_of_color_button_add_new_habit);
            View drawable_with_color_at_the_start_of_icon_button_add_new_habit = getView().findViewById(R.id.drawable_with_color_at_the_start_of_icon_button_add_new_habit);
            drawable_with_color_at_the_start_of_color_button_add_new_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(habit_color)));
            drawable_with_color_at_the_start_of_icon_button_add_new_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(habit_color)));
        }
    }

    private void color_button_add_new_habit_final() {
        if (getView() != null) {
            Button change_color_button_in_add_new_habit_final = getView().findViewById(R.id.change_color_button_in_add_new_habit_final);
            change_color_button_in_add_new_habit_final.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bottom_sheet_dialog_choose_color bottom_sheet_dialog_choose_color = new Bottom_sheet_dialog_choose_color(habit_color);
                    bottom_sheet_dialog_choose_color.set_done_clicked_edit(new Bottom_sheet_dialog_choose_color.color_chosen() {
                        @Override
                        public void color_got_chosen(String color) {
                            habit_color = color;
                            color_everything();
                        }
                    });
                    bottom_sheet_dialog_choose_color.show(getParentFragmentManager(), "choose color");
                }
            });
        }
    }

    private void change_icon_button_listen_add_new_habit_final() {
        if (getView() != null) {
            Button change_icon_button_in_add_new_habit_final_step = getView().findViewById(R.id.change_icon_button_in_add_new_habit_final_step);
            change_icon_button_in_add_new_habit_final_step.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (emojiPopup.isShowing()) {
                        hide_emoji_keybaord();
                    } else {
                        show_emoji_keybaord();
                    }
                }
            });
        }
    }

    private void build_up_and_quit_button_listen_in_the_end() {
        if (getView() != null) {
            Button white_button_build_habit_add_new_habit_in_the_final = getView().findViewById(R.id.white_button_build_habit_add_new_habit_in_the_final);
            Button white_button_quit_add_new_habit_in_the_final = getView().findViewById(R.id.white_button_quit_add_new_habit_in_the_final);
            Button grey_button_build_habit_add_new_habit_in_the_final = getView().findViewById(R.id.grey_button_build_habit_add_new_habit_in_the_final);
            Button grey_button_quit_add_new_habit_in_the_final = getView().findViewById(R.id.grey_button_quit_add_new_habit_in_the_final);
            grey_button_build_habit_add_new_habit_in_the_final.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    white_button_build_habit_add_new_habit_in_the_final.setVisibility(View.VISIBLE);
                    white_button_quit_add_new_habit_in_the_final.setVisibility(View.INVISIBLE);
                    grey_button_build_habit_add_new_habit_in_the_final.setVisibility(View.INVISIBLE);
                    grey_button_quit_add_new_habit_in_the_final.setVisibility(View.VISIBLE);
                    show_frequency();
                }
            });
            grey_button_quit_add_new_habit_in_the_final.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    white_button_build_habit_add_new_habit_in_the_final.setVisibility(View.INVISIBLE);
                    white_button_quit_add_new_habit_in_the_final.setVisibility(View.VISIBLE);
                    grey_button_build_habit_add_new_habit_in_the_final.setVisibility(View.VISIBLE);
                    grey_button_quit_add_new_habit_in_the_final.setVisibility(View.INVISIBLE);
                    hide_frequency();
                }
            });
        }
    }

    private void set_the_name_edit_text() {
        if (getView() != null) {
            EditText edit_text_to_enter_habit_name_final = getView().findViewById(R.id.edit_text_to_enter_habit_name_final);
            edit_text_to_enter_habit_name_final.setText(habit_name);
        }
    }

    private void set_emoji_start() {
        if (getView() != null) {
            EmojiTextView emoji_view_add_new_habit = getView().findViewById(R.id.emoji_view_add_new_habit);
            EmojiEditText emoji_edit_text = getView().findViewById(R.id.emoji_edit_text);
            emoji_view_add_new_habit.setText(habit_icon);
            emoji_edit_text.setText(habit_icon);

        }
    }

    private void set_quite_or_build_start() {
        if (getView() != null) {
            Button white_button_build_habit_add_new_habit_in_the_final = getView().findViewById(R.id.white_button_build_habit_add_new_habit_in_the_final);
            Button white_button_quit_add_new_habit_in_the_final = getView().findViewById(R.id.white_button_quit_add_new_habit_in_the_final);
            Button grey_button_build_habit_add_new_habit_in_the_final = getView().findViewById(R.id.grey_button_build_habit_add_new_habit_in_the_final);
            Button grey_button_quit_add_new_habit_in_the_final = getView().findViewById(R.id.grey_button_quit_add_new_habit_in_the_final);
            if (type_of_habit.equals("Build_up")) {
                white_button_build_habit_add_new_habit_in_the_final.setVisibility(View.VISIBLE);
                white_button_quit_add_new_habit_in_the_final.setVisibility(View.INVISIBLE);
                grey_button_build_habit_add_new_habit_in_the_final.setVisibility(View.INVISIBLE);
                grey_button_quit_add_new_habit_in_the_final.setVisibility(View.VISIBLE);
            } else if (type_of_habit.equals("Quit")) {
                white_button_build_habit_add_new_habit_in_the_final.setVisibility(View.INVISIBLE);
                white_button_quit_add_new_habit_in_the_final.setVisibility(View.VISIBLE);
                grey_button_build_habit_add_new_habit_in_the_final.setVisibility(View.VISIBLE);
                grey_button_quit_add_new_habit_in_the_final.setVisibility(View.INVISIBLE);
                hide_frequency();
            }
        }
    }

    private void set_up_frequency() {
        if (getView() != null) {
            PowerSpinnerView spinner_to_choose_frequency_of_habit_add_new_habit_in_final = getView().findViewById(R.id.spinner_to_choose_frequency_of_habit_add_new_habit_in_final);
            CircularToggle day_1_choose_days_of_week_final = getView().findViewById(R.id.day_1_choose_days_of_week_final);
            CircularToggle day_2_choose_days_of_week_final = getView().findViewById(R.id.day_2_choose_days_of_week_final);
            CircularToggle day_3_choose_days_of_week_final = getView().findViewById(R.id.day_3_choose_days_of_week_final);
            CircularToggle day_4_choose_days_of_week_final = getView().findViewById(R.id.day_4_choose_days_of_week_final);
            CircularToggle day_5_choose_days_of_week_final = getView().findViewById(R.id.day_5_choose_days_of_week_final);
            CircularToggle day_6_choose_days_of_week_final = getView().findViewById(R.id.day_6_choose_days_of_week_final);
            CircularToggle day_7_choose_days_of_week_final = getView().findViewById(R.id.day_7_choose_days_of_week_final);
            ArrayList<String> items_list = new ArrayList<>();
            items_list.add("Everyday");
            items_list.add("Specific days per week");
            spinner_to_choose_frequency_of_habit_add_new_habit_in_final.setItems(items_list);
        }
    }

    private void set_up_frequncy_real() {
        if (getView() != null) {
            PowerSpinnerView spinner_to_choose_frequency_of_habit_add_new_habit_in_final = getView().findViewById(R.id.spinner_to_choose_frequency_of_habit_add_new_habit_in_final);
            CircularToggle day_1_choose_days_of_week_final = getView().findViewById(R.id.day_1_choose_days_of_week_final);
            CircularToggle day_2_choose_days_of_week_final = getView().findViewById(R.id.day_2_choose_days_of_week_final);
            CircularToggle day_3_choose_days_of_week_final = getView().findViewById(R.id.day_3_choose_days_of_week_final);
            CircularToggle day_4_choose_days_of_week_final = getView().findViewById(R.id.day_4_choose_days_of_week_final);
            CircularToggle day_5_choose_days_of_week_final = getView().findViewById(R.id.day_5_choose_days_of_week_final);
            CircularToggle day_6_choose_days_of_week_final = getView().findViewById(R.id.day_6_choose_days_of_week_final);
            CircularToggle day_7_choose_days_of_week_final = getView().findViewById(R.id.day_7_choose_days_of_week_final);
            if (type_of_habit.equals("Build_up")) {
                if (habit_frequency.size() == 7) {
                    spinner_to_choose_frequency_of_habit_add_new_habit_in_final.selectItemByIndex(0);
                } else {
                    spinner_to_choose_frequency_of_habit_add_new_habit_in_final.selectItemByIndex(1);
                    if (habit_frequency.contains("Monday")) {
                        day_1_choose_days_of_week_final.setChecked(true);
                    } else if (habit_frequency.contains("Tuesday")) {
                        day_2_choose_days_of_week_final.setChecked(true);
                    } else if (habit_frequency.contains("Wednesday")) {
                        day_3_choose_days_of_week_final.setChecked(true);
                    } else if (habit_frequency.contains("Thursday")) {
                        day_4_choose_days_of_week_final.setChecked(true);
                    } else if (habit_frequency.contains("Friday")) {
                        day_5_choose_days_of_week_final.setChecked(true);
                    } else if (habit_frequency.contains("Saturday")) {
                        day_6_choose_days_of_week_final.setChecked(true);
                    } else if (habit_frequency.contains("Sunday")) {
                        day_7_choose_days_of_week_final.setChecked(true);
                    }
                }
            }
        }
    }

    private void listen_for_frequncy_spinner_item_select() {
        if (getView() != null) {
            PowerSpinnerView spinner_to_choose_frequency_of_habit_add_new_habit_in_final = getView().findViewById(R.id.spinner_to_choose_frequency_of_habit_add_new_habit_in_final);
            MultiSelectToggleGroup select_days_of_week_for_specific_days_add_new_habit_final = getView().findViewById(R.id.select_days_of_week_for_specific_days_add_new_habit_final);
            spinner_to_choose_frequency_of_habit_add_new_habit_in_final.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<Object>() {
                @Override
                public void onItemSelected(int i, @Nullable Object o, int i1, Object t1) {
                    if (i1 == 0) {
                        select_days_of_week_for_specific_days_add_new_habit_final.setVisibility(View.GONE);
                    } else if (i1 == 1) {
                        select_days_of_week_for_specific_days_add_new_habit_final.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    private void hide_frequency() {
        if (getView() != null) {
            TextView text_saying_frequency_in_add_new_habit_final = getView().findViewById(R.id.text_saying_frequency_in_add_new_habit_final);
            View view_at_the_bottom_of_frequency_text = getView().findViewById(R.id.view_at_the_bottom_of_frequency_text);
            PowerSpinnerView spinner_to_choose_frequency_of_habit_add_new_habit_in_final = getView().findViewById(R.id.spinner_to_choose_frequency_of_habit_add_new_habit_in_final);
            MultiSelectToggleGroup select_days_of_week_for_specific_days_add_new_habit_final = getView().findViewById(R.id.select_days_of_week_for_specific_days_add_new_habit_final);
            TextView text_saying_frequency_per_day_in_add_new_habit_final = getView().findViewById(R.id.text_saying_frequency_per_day_in_add_new_habit_final);
            View view_at_the_bottom_of_frequency_per_day_text = getView().findViewById(R.id.view_at_the_bottom_of_frequency_per_day_text);
            EditText edit_text_to_enter_how_many_times_per_day = getView().findViewById(R.id.edit_text_to_enter_how_many_times_per_day);
            text_saying_frequency_in_add_new_habit_final.setVisibility(View.GONE);
            view_at_the_bottom_of_frequency_text.setVisibility(View.GONE);
            spinner_to_choose_frequency_of_habit_add_new_habit_in_final.setVisibility(View.GONE);
            select_days_of_week_for_specific_days_add_new_habit_final.setVisibility(View.GONE);
            text_saying_frequency_per_day_in_add_new_habit_final.setVisibility(View.GONE);
            view_at_the_bottom_of_frequency_per_day_text.setVisibility(View.GONE);
            edit_text_to_enter_how_many_times_per_day.setVisibility(View.GONE);
        }
    }

    private void show_frequency() {
        if (getView() != null) {
            TextView text_saying_frequency_in_add_new_habit_final = getView().findViewById(R.id.text_saying_frequency_in_add_new_habit_final);
            View view_at_the_bottom_of_frequency_text = getView().findViewById(R.id.view_at_the_bottom_of_frequency_text);
            PowerSpinnerView spinner_to_choose_frequency_of_habit_add_new_habit_in_final = getView().findViewById(R.id.spinner_to_choose_frequency_of_habit_add_new_habit_in_final);
            MultiSelectToggleGroup select_days_of_week_for_specific_days_add_new_habit_final = getView().findViewById(R.id.select_days_of_week_for_specific_days_add_new_habit_final);
            TextView text_saying_frequency_per_day_in_add_new_habit_final = getView().findViewById(R.id.text_saying_frequency_per_day_in_add_new_habit_final);
            View view_at_the_bottom_of_frequency_per_day_text = getView().findViewById(R.id.view_at_the_bottom_of_frequency_per_day_text);
            EditText edit_text_to_enter_how_many_times_per_day = getView().findViewById(R.id.edit_text_to_enter_how_many_times_per_day);
            text_saying_frequency_in_add_new_habit_final.setVisibility(View.VISIBLE);
            view_at_the_bottom_of_frequency_text.setVisibility(View.VISIBLE);
            spinner_to_choose_frequency_of_habit_add_new_habit_in_final.setVisibility(View.VISIBLE);
            show_multi_select_group_if_right();
            text_saying_frequency_per_day_in_add_new_habit_final.setVisibility(View.VISIBLE);
            view_at_the_bottom_of_frequency_per_day_text.setVisibility(View.VISIBLE);
            edit_text_to_enter_how_many_times_per_day.setVisibility(View.VISIBLE);
        }
    }

    private void set_days_color_frequency() {
        if (getView() != null) {
            CircularToggle day_1_choose_days_of_week_final = getView().findViewById(R.id.day_1_choose_days_of_week_final);
            CircularToggle day_2_choose_days_of_week_final = getView().findViewById(R.id.day_2_choose_days_of_week_final);
            CircularToggle day_3_choose_days_of_week_final = getView().findViewById(R.id.day_3_choose_days_of_week_final);
            CircularToggle day_4_choose_days_of_week_final = getView().findViewById(R.id.day_4_choose_days_of_week_final);
            CircularToggle day_5_choose_days_of_week_final = getView().findViewById(R.id.day_5_choose_days_of_week_final);
            CircularToggle day_6_choose_days_of_week_final = getView().findViewById(R.id.day_6_choose_days_of_week_final);
            CircularToggle day_7_choose_days_of_week_final = getView().findViewById(R.id.day_7_choose_days_of_week_final);
            day_1_choose_days_of_week_final.setMarkerColor(Color.parseColor(habit_color));
            day_2_choose_days_of_week_final.setMarkerColor(Color.parseColor(habit_color));
            day_3_choose_days_of_week_final.setMarkerColor(Color.parseColor(habit_color));
            day_4_choose_days_of_week_final.setMarkerColor(Color.parseColor(habit_color));
            day_5_choose_days_of_week_final.setMarkerColor(Color.parseColor(habit_color));
            day_6_choose_days_of_week_final.setMarkerColor(Color.parseColor(habit_color));
            day_7_choose_days_of_week_final.setMarkerColor(Color.parseColor(habit_color));
        }
    }

    private void show_multi_select_group_if_right() {
        if (getView() != null) {
            PowerSpinnerView spinner_to_choose_frequency_of_habit_add_new_habit_in_final = getView().findViewById(R.id.spinner_to_choose_frequency_of_habit_add_new_habit_in_final);
            MultiSelectToggleGroup select_days_of_week_for_specific_days_add_new_habit_final = getView().findViewById(R.id.select_days_of_week_for_specific_days_add_new_habit_final);
            if (spinner_to_choose_frequency_of_habit_add_new_habit_in_final.getSelectedIndex() == 1) {
                select_days_of_week_for_specific_days_add_new_habit_final.setVisibility(View.VISIBLE);
            }
        }
    }

    private void color_everything() {
        color_the_done_button();
        set_tint_of_color_button_final();
        color_button_add_new_habit_final();
        set_days_color_frequency();
        color_underline_of_emoji();
        color_the_add_reminder_button();
        change_the_switch_color();
        change_the_color_for_reminder_arraylist();
    }

    private void cancel_button_listen() {
        if (getView() != null) {
            Button button_to_cancel_new_habit_in_add_new_habit_add_new_habit_final = getView().findViewById(R.id.button_to_cancel_new_habit_in_add_new_habit_add_new_habit_final);
            button_to_cancel_new_habit_in_add_new_habit_add_new_habit_final.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    home_fragment new_fragment = (com.easyhabitsapp.android.home_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("home");
                    Add_new_habit_final old_fragment = (Add_new_habit_final) getActivity().getSupportFragmentManager().findFragmentByTag("add a habit final");
                    if (old_fragment != null) {
                        getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).show(new_fragment).commit();
                    }
                }
            });
        }
    }

    private void set_frequency_per_day() {
        if (getView() != null) {
            EditText edit_text_to_enter_how_many_times_per_day = getView().findViewById(R.id.edit_text_to_enter_how_many_times_per_day);
            if (type_of_habit.equals("Build_up")) {
                edit_text_to_enter_how_many_times_per_day.setText(String.valueOf(habit_frequency_extra));
            }
        }
    }

    private void listen_to_swtich() {
        if (getView() != null) {
            SwitchMaterial switch_to_turn_on_notifications_add_new_habit_final = getView().findViewById(R.id.switch_to_turn_on_notifications_add_new_habit_final);
            switch_to_turn_on_notifications_add_new_habit_final.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (switch_to_turn_on_notifications_add_new_habit_final.isChecked()) {
                        int resultColor = ColorUtils.blendARGB(Color.parseColor(habit_color), Color.WHITE, 0.5F);
                        switch_to_turn_on_notifications_add_new_habit_final.setThumbTintList(ColorStateList.valueOf(Color.parseColor(habit_color)));
                        switch_to_turn_on_notifications_add_new_habit_final.setTrackTintList(ColorStateList.valueOf(resultColor));
                    } else {
                        switch_to_turn_on_notifications_add_new_habit_final.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#ececec")));
                        switch_to_turn_on_notifications_add_new_habit_final.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#b2b2b2")));
                    }
                    toggle_the_no_reminder_visibility();
                    set_the_done_button();
                }
            });
        }
    }

    private void set_up_emoji() {
        if (getView() != null) {
            EmojiEditText emoji_edit_text = getView().findViewById(R.id.emoji_edit_text);
            ConstraintLayout layout_containing_all_for_add_new_habit_main_final_screeen = getView().findViewById(R.id.layout_containing_all_for_add_new_habit_main_final_screeen);
            if (emojiPopup == null) {
                EmojiTheming emojiTheming = new EmojiTheming(Color.WHITE, Color.parseColor(habit_color), Color.parseColor(habit_color), Color.parseColor(habit_color), Color.parseColor(habit_color), Color.parseColor(habit_color));
                emojiPopup = new EmojiPopup(layout_containing_all_for_add_new_habit_main_final_screeen, emoji_edit_text, emojiTheming);
                DisableKeyboardInputTrait disableKeyboardInputTrait = new DisableKeyboardInputTrait(emojiPopup);
                ForceSingleEmojiTrait forceSingleEmojiTrait = new ForceSingleEmojiTrait();
                disableKeyboardInputTrait.install(emoji_edit_text);
                forceSingleEmojiTrait.install(emoji_edit_text);
            }
        }
    }

    private void set_up_emoji_keyboard() {
        EmojiManager.install(new GoogleEmojiProvider());
    }

    private void hide_emoji_keybaord() {
        dismiss_keyboard();
        transfer_emoji_from_edit_text_to_view(true);
    }

    private void show_emoji_keybaord() {
        transfer_emoji_from_edit_text_to_view(false);
        emojiPopup.show();
        if (first_time_to_show_emoji) {
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    emojiPopup.dismiss();
                    emojiPopup.show();
                }
            }, 500);
        }
        first_time_to_show_emoji = false;
    }

    private void dismiss_keyboard() {
        if (getActivity() != null) {
            View view = getActivity().getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    private void transfer_emoji_from_edit_text_to_view(boolean is_emoji_view_showing) {
        if (getView() != null) {
            EmojiEditText emoji_edit_text = getView().findViewById(R.id.emoji_edit_text);
            EmojiTextView emojiTextView = getView().findViewById(R.id.emoji_view_add_new_habit);
            if (is_emoji_view_showing) {
                emojiTextView.setText(emoji_edit_text.getText().toString());
                emojiTextView.setVisibility(View.VISIBLE);
                emoji_edit_text.setVisibility(View.INVISIBLE);
            } else {
                emojiTextView.setVisibility(View.INVISIBLE);
                emoji_edit_text.setVisibility(View.VISIBLE);
            }
        }
    }

    private void emoji_text_watcher() {
        if (getView() != null) {
            EmojiEditText emoji_edit_text = getView().findViewById(R.id.emoji_edit_text);
            emoji_edit_text.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!s.toString().isEmpty()) {
                        hide_emoji_keybaord();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    private void layout_listen() {
        if (getView() != null) {
            ConstraintLayout layout_containing_all_for_add_new_habit_main_final_screeen = getView().findViewById(R.id.layout_containing_all_for_add_new_habit_main_final_screeen);
            layout_containing_all_for_add_new_habit_main_final_screeen.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    dismiss_keyboard();
                    return false;
                }
            });
        }
    }

    private void color_underline_of_emoji() {
        if (getView() != null) {
            EmojiEditText emoji_edit_text = getView().findViewById(R.id.emoji_edit_text);
            emoji_edit_text.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(habit_color)));
        }
    }

    private void check_reminders() {
        if (getView() != null) {
            SwitchMaterial switch_to_turn_on_notifications_add_new_habit_final = getView().findViewById(R.id.switch_to_turn_on_notifications_add_new_habit_final);
            if (notifications) {
                switch_to_turn_on_notifications_add_new_habit_final.setChecked(true);
            } else {
                switch_to_turn_on_notifications_add_new_habit_final.setChecked(false);
            }
            toggle_the_no_reminder_visibility();
            set_the_done_button();
        }
    }

    private void set_up_recycle_view() {
        if (getView() != null) {
            RecyclerView recyvle_view_show_reminders = getView().findViewById(R.id.recyvle_view_show_reminders);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            adapter_for_reminder = new Adapter_add_reminder_add_new_habit(reminder_arraylist);
            recyvle_view_show_reminders.setLayoutManager(linearLayoutManager);
            recyvle_view_show_reminders.setAdapter(adapter_for_reminder);
            adapter_for_reminder.set_delete_reminder(new Adapter_add_reminder_add_new_habit.delete_button_reminder_clicked() {
                @Override
                public void delete_got_clicked(int position) {
                    reminder_arraylist.remove(position);
                    adapter_for_reminder.notifyDataSetChanged();
                    toggle_the_no_reminder_visibility();
                    set_the_done_button();
                    set_the_height_of_the_recycle_view();
                }
            });
            adapter_for_reminder.set_edit_reminder(new Adapter_add_reminder_add_new_habit.edit_button_reminder_clicked() {
                @Override
                public void edit_got_clicked(int position) {
                    Bottom_sheet_add_reminder_add_new_habit bottom_sheet_add_reminder_add_new_habit = new Bottom_sheet_add_reminder_add_new_habit(habit_color, reminder_arraylist.get(position).getDays(), reminder_arraylist.get(position).getTime(), position);
                    bottom_sheet_add_reminder_add_new_habit.set_done_clicked_edit(new Bottom_sheet_add_reminder_add_new_habit.done_clicked() {
                        @Override
                        public void done_was_just_clicked(long time, ArrayList<String> days, int position) {
                            reminder_arraylist.set(position, new Example_item_reminder_add_new_habit(time, days, Color.parseColor(habit_color)));
                            adapter_for_reminder.notifyDataSetChanged();
                        }
                    });
                    bottom_sheet_add_reminder_add_new_habit.show(getParentFragmentManager(), "add reminder bottom sheet");
                }
            });
        }
    }

    private void toggle_the_no_reminder_visibility() {
        if (getView() != null) {
            SwitchMaterial switch_to_turn_on_notifications_add_new_habit_final = getView().findViewById(R.id.switch_to_turn_on_notifications_add_new_habit_final);
            RecyclerView recyvle_view_show_reminders = getView().findViewById(R.id.recyvle_view_show_reminders);
            TextView text_saying_no_reminder = getView().findViewById(R.id.text_saying_no_reminder);
            ConstraintLayout layout_with_more_info_add_notification = getView().findViewById(R.id.layout_with_more_info_add_notification);
//            View pixel_at_the_bottom_of_the_text_for_reminders = getView().findViewById(R.id.pixel_at_the_bottom_of_the_text_for_reminders);
            if (switch_to_turn_on_notifications_add_new_habit_final.isChecked()) {
                if (reminder_arraylist.isEmpty()) {
                    text_saying_no_reminder.setVisibility(View.VISIBLE);
//                    pixel_at_the_bottom_of_the_text_for_reminders.setVisibility(View.VISIBLE);
                    recyvle_view_show_reminders.setVisibility(View.GONE);
                } else {
                    text_saying_no_reminder.setVisibility(View.GONE);
//                    pixel_at_the_bottom_of_the_text_for_reminders.setVisibility(View.GONE);
                    recyvle_view_show_reminders.setVisibility(View.VISIBLE);
                }
                layout_with_more_info_add_notification.setVisibility(View.VISIBLE);
            } else {
                text_saying_no_reminder.setVisibility(View.GONE);
//                pixel_at_the_bottom_of_the_text_for_reminders.setVisibility(View.GONE);
                recyvle_view_show_reminders.setVisibility(View.GONE);
                layout_with_more_info_add_notification.setVisibility(View.GONE);
            }
        }
    }

    private void add_notifications() {
        if (getView() != null) {
            if (notifications) {
                for (int i = 0; i < notifications_extra.size(); i++) {
                    reminder_arraylist.add(new Example_item_reminder_add_new_habit(notifications_extra.get(i).getTime(), notifications_extra.get(i).getDays(), Color.parseColor(habit_color)));
                }
                adapter_for_reminder.notifyDataSetChanged();
                set_the_height_of_the_recycle_view();
            }
        }
    }

    private void color_the_add_reminder_button() {
        if (getView() != null) {
            Button button_add_new_reminder_add_new_habit = getView().findViewById(R.id.button_add_new_reminder_add_new_habit);
            button_add_new_reminder_add_new_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(habit_color)));
        }
    }

    private void listen_to_add_reminder_button() {
        if (getView() != null) {
            Button button_add_new_reminder_add_new_habit = getView().findViewById(R.id.button_add_new_reminder_add_new_habit);
            button_add_new_reminder_add_new_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    add_reminder_clicked();
                }
            });
        }
    }

    private void add_reminder_clicked() {
        Bottom_sheet_add_reminder_add_new_habit bottom_sheet_add_reminder_add_new_habit = new Bottom_sheet_add_reminder_add_new_habit(habit_color);
        bottom_sheet_add_reminder_add_new_habit.set_share_clicked_listen(new Bottom_sheet_add_reminder_add_new_habit.reminder_was_just_set() {
            @Override
            public void remider_added(long time, ArrayList<String> days) {
                add_new_item_to_reminder_array_list(time, days, Color.parseColor(habit_color));
                toggle_the_no_reminder_visibility();
                set_the_done_button();
            }
        });
        bottom_sheet_add_reminder_add_new_habit.show(getParentFragmentManager(), "add reminder bottom sheet");
    }

    private void add_new_item_to_reminder_array_list(long time, ArrayList<String> days, int color) {
        reminder_arraylist.add(new Example_item_reminder_add_new_habit(time, days, color));
        adapter_for_reminder.notifyItemInserted(reminder_arraylist.size() - 1);
        set_the_height_of_the_recycle_view();
    }

    private boolean done_button_check() {
        EditText edit_text_to_enter_habit_name_final = getView().findViewById(R.id.edit_text_to_enter_habit_name_final);
        EditText edit_text_to_enter_how_many_times_per_day = getView().findViewById(R.id.edit_text_to_enter_how_many_times_per_day);
        SwitchMaterial switch_to_turn_on_notifications_add_new_habit_final = getView().findViewById(R.id.switch_to_turn_on_notifications_add_new_habit_final);
        PowerSpinnerView spinner_to_choose_frequency_of_habit_add_new_habit_in_final = getView().findViewById(R.id.spinner_to_choose_frequency_of_habit_add_new_habit_in_final);
        if (edit_text_to_enter_habit_name_final.getText().toString().trim().equals("")) {
            Toast.makeText(getContext(), "Name can't be empty", Toast.LENGTH_LONG).show();
            return false;
        }
        if (build_or_quit_habit().equals("Build_up") && edit_text_to_enter_how_many_times_per_day.getText().toString().trim().equals("")) {
            Toast.makeText(getContext(), "Frequency per day can't be empty", Toast.LENGTH_LONG).show();
            return false;
        }
        if (build_or_quit_habit().equals("Build_up") && spinner_to_choose_frequency_of_habit_add_new_habit_in_final.getSelectedIndex() == 1 && is_no_day_selected()) {
            Toast.makeText(getContext(), "Please select at least 1 day per week", Toast.LENGTH_LONG).show();
            return false;
        }
        if (switch_to_turn_on_notifications_add_new_habit_final.isChecked() && reminder_arraylist.isEmpty()) {
            Toast.makeText(getContext(), "No notifications added yet", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean is_no_day_selected() {
        CircularToggle day_1_choose_days_of_week_final = getView().findViewById(R.id.day_1_choose_days_of_week_final);
        CircularToggle day_2_choose_days_of_week_final = getView().findViewById(R.id.day_2_choose_days_of_week_final);
        CircularToggle day_3_choose_days_of_week_final = getView().findViewById(R.id.day_3_choose_days_of_week_final);
        CircularToggle day_4_choose_days_of_week_final = getView().findViewById(R.id.day_4_choose_days_of_week_final);
        CircularToggle day_5_choose_days_of_week_final = getView().findViewById(R.id.day_5_choose_days_of_week_final);
        CircularToggle day_6_choose_days_of_week_final = getView().findViewById(R.id.day_6_choose_days_of_week_final);
        CircularToggle day_7_choose_days_of_week_final = getView().findViewById(R.id.day_7_choose_days_of_week_final);
        if (day_1_choose_days_of_week_final.isChecked()) {
            return false;
        } else if (day_2_choose_days_of_week_final.isChecked()) {
            return false;
        } else if (day_3_choose_days_of_week_final.isChecked()) {
            return false;
        } else if (day_4_choose_days_of_week_final.isChecked()) {
            return false;
        } else if (day_5_choose_days_of_week_final.isChecked()) {
            return false;
        } else if (day_6_choose_days_of_week_final.isChecked()) {
            return false;
        } else if (day_7_choose_days_of_week_final.isChecked()) {
            return false;
        }
        return true;
    }

    private String return_name_of_the_habit() {
        EditText edit_text_to_enter_habit_name_final = getView().findViewById(R.id.edit_text_to_enter_habit_name_final);
        return edit_text_to_enter_habit_name_final.getText().toString().trim();
    }

    private String return_icon_of_the_habit() {
        EmojiTextView emoji_view_add_new_habit = getView().findViewById(R.id.emoji_view_add_new_habit);
        return emoji_view_add_new_habit.getText().toString();
    }

    private String build_or_quit_habit() {
        Button white_button_build_habit_add_new_habit_in_the_final = getView().findViewById(R.id.white_button_build_habit_add_new_habit_in_the_final);
        //Button white_button_quit_add_new_habit_in_the_final = getView().findViewById(R.id.white_button_quit_add_new_habit_in_the_final);
        if (white_button_build_habit_add_new_habit_in_the_final.getVisibility() == View.VISIBLE) {
            return "Build_up";
        } else {
            return "Quit";
        }
    }

    private ArrayList<String> return_frequency() {
        PowerSpinnerView spinner_to_choose_frequency_of_habit_add_new_habit_in_final = getView().findViewById(R.id.spinner_to_choose_frequency_of_habit_add_new_habit_in_final);
        CircularToggle day_1_choose_days_of_week_final = getView().findViewById(R.id.day_1_choose_days_of_week_final);
        CircularToggle day_2_choose_days_of_week_final = getView().findViewById(R.id.day_2_choose_days_of_week_final);
        CircularToggle day_3_choose_days_of_week_final = getView().findViewById(R.id.day_3_choose_days_of_week_final);
        CircularToggle day_4_choose_days_of_week_final = getView().findViewById(R.id.day_4_choose_days_of_week_final);
        CircularToggle day_5_choose_days_of_week_final = getView().findViewById(R.id.day_5_choose_days_of_week_final);
        CircularToggle day_6_choose_days_of_week_final = getView().findViewById(R.id.day_6_choose_days_of_week_final);
        CircularToggle day_7_choose_days_of_week_final = getView().findViewById(R.id.day_7_choose_days_of_week_final);
        ArrayList<String> days = new ArrayList<>();
        if (spinner_to_choose_frequency_of_habit_add_new_habit_in_final.getSelectedIndex() == 0) {
            days.add("Monday");
            days.add("Tuesday");
            days.add("Wednesday");
            days.add("Thursday");
            days.add("Friday");
            days.add("Saturday");
            days.add("Sunday");
        } else if (spinner_to_choose_frequency_of_habit_add_new_habit_in_final.getSelectedIndex() == 1) {
            if (day_1_choose_days_of_week_final.isChecked()) {
                days.add("Monday");
            } else if (day_2_choose_days_of_week_final.isChecked()) {
                days.add("Tuesday");
            } else if (day_3_choose_days_of_week_final.isChecked()) {
                days.add("Wednesday");
            } else if (day_4_choose_days_of_week_final.isChecked()) {
                days.add("Thursday");
            } else if (day_5_choose_days_of_week_final.isChecked()) {
                days.add("Friday");
            } else if (day_6_choose_days_of_week_final.isChecked()) {
                days.add("Saturday");
            } else if (day_7_choose_days_of_week_final.isChecked()) {
                days.add("Sunday");
            }
        }
        return days;
    }

    private int return_frequency_extra() {
        EditText edit_text_to_enter_how_many_times_per_day = getView().findViewById(R.id.edit_text_to_enter_how_many_times_per_day);
        return Integer.parseInt(edit_text_to_enter_how_many_times_per_day.getText().toString());
    }

    private boolean is_notifications_on() {
        SwitchMaterial switch_to_turn_on_notifications_add_new_habit_final = getView().findViewById(R.id.switch_to_turn_on_notifications_add_new_habit_final);
        return switch_to_turn_on_notifications_add_new_habit_final.isChecked();
    }

    private void change_the_switch_color() {
        SwitchMaterial switch_to_turn_on_notifications_add_new_habit_final = getView().findViewById(R.id.switch_to_turn_on_notifications_add_new_habit_final);
        if (switch_to_turn_on_notifications_add_new_habit_final.isChecked()) {
            int resultColor = ColorUtils.blendARGB(Color.parseColor(habit_color), Color.WHITE, 0.5F);
            switch_to_turn_on_notifications_add_new_habit_final.setThumbTintList(ColorStateList.valueOf(Color.parseColor(habit_color)));
            switch_to_turn_on_notifications_add_new_habit_final.setTrackTintList(ColorStateList.valueOf(resultColor));
        } else {
            switch_to_turn_on_notifications_add_new_habit_final.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#ececec")));
            switch_to_turn_on_notifications_add_new_habit_final.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#b2b2b2")));
        }
    }

    private void change_the_color_for_reminder_arraylist() {
        for (int i = 0; i < reminder_arraylist.size(); i++) {
            reminder_arraylist.get(i).setColor(Color.parseColor(habit_color));
        }
        if (adapter_for_reminder != null) {
            adapter_for_reminder.notifyDataSetChanged();
        }
    }

    private void set_the_done_button() {
        if (getView() != null && getContext()!=null) {
            SwitchMaterial switch_to_turn_on_notifications_add_new_habit_final = getView().findViewById(R.id.switch_to_turn_on_notifications_add_new_habit_final);
            if (switch_to_turn_on_notifications_add_new_habit_final.isChecked()){
                if (reminder_arraylist.isEmpty()) {
                    ConstraintLayout constraintLayout = getView().findViewById(R.id.layout_containing_all_for_add_new_habit_main_final_screeen);
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(R.id.done_button_at_the_buttom_add_new_habit,ConstraintSet.TOP,R.id.text_saying_no_reminder,ConstraintSet.BOTTOM, (int) convertDpToPixel(20,getContext()));
                    constraintSet.applyTo(constraintLayout);
                } else {
                    ConstraintLayout constraintLayout = getView().findViewById(R.id.layout_containing_all_for_add_new_habit_main_final_screeen);
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(R.id.done_button_at_the_buttom_add_new_habit,ConstraintSet.TOP,R.id.recyvle_view_show_reminders,ConstraintSet.BOTTOM, (int) convertDpToPixel(20,getContext()));
                    constraintSet.applyTo(constraintLayout);
                }
            } else {
                ConstraintLayout constraintLayout = getView().findViewById(R.id.layout_containing_all_for_add_new_habit_main_final_screeen);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(R.id.done_button_at_the_buttom_add_new_habit,ConstraintSet.TOP,R.id.layout_to_enable_or_disable_notifications,ConstraintSet.BOTTOM, (int) convertDpToPixel(20,getContext()));
                constraintSet.applyTo(constraintLayout);
            }
        }
    }

    public static float convertDpToPixel(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private void set_the_height_of_the_recycle_view(){
        if(getView()!=null && getContext()!=null){
            RecyclerView recyvle_view_show_reminders = getView().findViewById(R.id.recyvle_view_show_reminders);
            if(reminder_arraylist.size() == 1){
                ViewGroup.LayoutParams params = recyvle_view_show_reminders.getLayoutParams();
                params.height = (int) convertDpToPixel(85,getContext());
                recyvle_view_show_reminders.setLayoutParams(params);
            } else if(reminder_arraylist.size() > 1) {
                ViewGroup.LayoutParams params = recyvle_view_show_reminders.getLayoutParams();
                params.height = (int) convertDpToPixel(85*1.65f,getContext());
                recyvle_view_show_reminders.setLayoutParams(params);
            }
        }
    }
}