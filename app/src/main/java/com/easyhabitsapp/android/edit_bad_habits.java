package com.easyhabitsapp.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class edit_bad_habits extends Fragment {

    private int position;
    private int year_global;
    private int month_global;
    private int day_global;
    private String which_spinner_is_chosen;
    private long time_in_milli = 0;
    private String color = "";
    private String icon = "";

    public edit_bad_habits() {
        // Required empty public constructor
    }

    public edit_bad_habits(int position) {
        this.position = position;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_bad_habits, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        read_the_bad_habits();
        changte_habit_button_listen();
        change_date_button_listen();
        goal_days_text_watcher();
        change_color_listen();
        pick_icon_button_lsitener();
        update_button_listen();
        back_button_listen();
    }

    private void read_the_bad_habits() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("list_of_the_bad_habits", MODE_PRIVATE);
            String old = sharedPreferences.getString("Bad_habits", "");
            String[] split_big = old.split("spit_max_for_the_bad_habits");
            String[] split_small = split_big[position].split("split_here_bad_habits");
            which_spinner_is_chosen = split_small[0];
            set_the_spinner(split_small[1]);
            time_in_milli(Long.parseLong(split_small[2]));
            time_in_milli = Long.parseLong(split_small[2]);
            set_the_goal(split_small[3]);
            set_the_color(split_small[4]);
            set_the_icon(split_small[5]);
        }
    }

    private void set_the_spinner(String text) {
        if (getView() != null) {
            EditText edit_text_showing_habit_name = getView().findViewById(R.id.edit_text_showing_habit_name);
            ConstraintLayout constraintLayout = getView().findViewById(R.id.layout_to_show_in_edit_habit_bad);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.applyTo(constraintLayout);
            edit_text_showing_habit_name.setVisibility(View.VISIBLE);
            edit_text_showing_habit_name.setText(text);
            edit_text_showing_habit_name.setSelection(edit_text_showing_habit_name.getText().toString().length());
        }
    }

    private void time_in_milli(long time_in_milli) {
        if (getView() != null) {
            Button button_that_will_open_time_user_hacked = getView().findViewById(R.id.button_that_will_open_time_user_hacked);
            TextView text_to_sayy_enter_time_of_last_relapse = getView().findViewById(R.id.text_to_sayy_enter_time_of_last_relapse);
            Date date = new Date();
            date.setTime(time_in_milli);
            String date_in_string = DateFormat.getDateInstance(DateFormat.MEDIUM).format(date);
            String time_in_string = DateFormat.getTimeInstance(DateFormat.SHORT).format(date);
            button_that_will_open_time_user_hacked.setText("Change Date");
            text_to_sayy_enter_time_of_last_relapse.setText("Date: ".concat(time_in_string).concat(" ").concat(date_in_string));
            Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
            text_to_sayy_enter_time_of_last_relapse.setTypeface(boldTypeface);
            text_to_sayy_enter_time_of_last_relapse.setTextSize(16);
        }
    }

    private void set_the_goal(String goal) {
        if (getView() != null) {
            EditText enter_goal_for_new_good_habit_in_habits = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits);
            TextView text_saying_days_beside_habit_edit_text = getView().findViewById(R.id.text_saying_days_beside_habit_edit_text);
            if (goal.equals("1")) {
                text_saying_days_beside_habit_edit_text.setText("day");
            } else {
                text_saying_days_beside_habit_edit_text.setText("days");
            }
            enter_goal_for_new_good_habit_in_habits.setText(goal);
        }
    }

    private void set_the_color(String text) {
        if (getView() != null) {
            View view_showing_the_icon_and_its_color = getView().findViewById(R.id.view_showing_the_icon_and_its_color);
            TextView text_view_asying_this_is_example_of_icon = getView().findViewById(R.id.text_view_asying_this_is_example_of_icon);
            color = text;
            if (text.equals("teal")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_dark_green);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#607D8B"));
            } else if (text.equals("red")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_red);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#e6194B"));
            } else if (text.equals("green")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_green);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#3cb44b"));
            } else if (text.equals("blue")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_blue);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#4363d8"));
            } else if (text.equals("orange")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_orange);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#f58231"));
            } else if (text.equals("brown")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_brown);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#9A6324"));
            } else if (text.equals("black")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_black);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#000000"));
            } else if (text.equals("cyan")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_cyan);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#42d4f4"));
            } else if (text.equals("lime")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_lime);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#bfef45"));
            } else if (text.equals("magenta")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_magenta);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#f032e6"));
            } else if (text.equals("navy")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_navy);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#000075"));
            } else if (text.equals("pink")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_pink);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#fabed4"));
            } else if (text.equals("yellow")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_yellow);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#ffe119"));
            } else if (text.equals("purple")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_purple);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#911eb4"));
            }
        }
    }

    private void set_the_icon(String text) {
        if (getView() != null && getContext() != null) {
            View showing_icon_in_color_good_habits = getView().findViewById(R.id.showing_icon_in_color_good_habits);
            icon = text;
            if (text.equals("games")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_videogame_asset_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("social_media")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_stay_current_portrait_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("shopping")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_shopping_cart_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("smoking")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_smoking_rooms_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("alcohol")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_sports_bar_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("lying")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_record_voice_over_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("procrastination")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_restore_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("cross")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_clear_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("coffee")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_local_cafe_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("fast food")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_fastfood_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("money")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_money_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("sleeping")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_airline_seat_individual_suite_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("healing")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_healing_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("work")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_work_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("tv")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_tv_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("couch")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_weekend_24).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (text.equals("other")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.happy_face_dark_green).mutate();
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                showing_icon_in_color_good_habits.setBackground(icon);
            }
        }
    }

    private void changte_habit_button_listen() {
        if (getView() != null) {
            Button submit_data_first_time_button = getView().findViewById(R.id.submit_data_first_time_button);
            submit_data_first_time_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog_asking_which_habit dialog_asking_which_habit = new Dialog_asking_which_habit();
                    dialog_asking_which_habit.setTargetFragment(edit_bad_habits.this, 1001);
                    dialog_asking_which_habit.show(getActivity().getSupportFragmentManager(), "tag");
                }
            });
        }
    }

    private void change_date_button_listen() {
        if (getView() != null) {
            Button button_that_will_open_time_user_hacked = getView().findViewById(R.id.button_that_will_open_time_user_hacked);
            button_that_will_open_time_user_hacked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Universal_date_picker universal_date_picker = new Universal_date_picker();
                    universal_date_picker.setTargetFragment(edit_bad_habits.this, 22);
                    universal_date_picker.show(getActivity().getSupportFragmentManager(), "date_picker");
                }
            });
        }
    }

    private void goal_days_text_watcher() {
        if (getView() != null) {
            final EditText enter_goal_for_new_good_habit_in_habits = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits);
            final TextView text_saying_days_beside_habit_edit_text = getView().findViewById(R.id.text_saying_days_beside_habit_edit_text);
            enter_goal_for_new_good_habit_in_habits.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (enter_goal_for_new_good_habit_in_habits.hasFocus()) {
                        if (!charSequence.toString().equals("")) {
                            if (Integer.parseInt(charSequence.toString()) == 1) {
                                text_saying_days_beside_habit_edit_text.setText(" day");
                            } else {
                                text_saying_days_beside_habit_edit_text.setText(" days");
                            }
                        } else {
                            text_saying_days_beside_habit_edit_text.setText(" days");
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    private void change_color_listen() {
        if (getView() != null) {
            Button button_to_choose_color_of_new_good_habit = getView().findViewById(R.id.button_to_choose_color_of_new_good_habit);
            button_to_choose_color_of_new_good_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bottom_sheet_choose_good_habit_color bottomSheetDialog = new Bottom_sheet_choose_good_habit_color();
                    bottomSheetDialog.setTargetFragment(edit_bad_habits.this, 1000);
                    bottomSheetDialog.show(getActivity().getSupportFragmentManager(), "tag");
                }
            });
        }
    }

    private void pick_icon_button_lsitener() {
        if (getView() != null) {
            Button button_to_choose_icon_of_the_new_good_habit = getView().findViewById(R.id.button_to_choose_icon_of_the_new_good_habit);
            button_to_choose_icon_of_the_new_good_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getView() != null) {
                        Dialog_to_choose_good_habit dialog_to_choose_good_habit = new Dialog_to_choose_good_habit();
                        dialog_to_choose_good_habit.setTargetFragment(edit_bad_habits.this, 100);
                        dialog_to_choose_good_habit.show(getActivity().getSupportFragmentManager(), "bad_habits_main");
                    }
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1000:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        String text = bundle.getString("color", "teal");
                        if (getView() != null) {
                            View view_showing_the_icon_and_its_color = getView().findViewById(R.id.view_showing_the_icon_and_its_color);
                            TextView text_view_asying_this_is_example_of_icon = getView().findViewById(R.id.text_view_asying_this_is_example_of_icon);
                            color = text;
                            if (text.equals("teal")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_dark_green);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#607D8B"));
                            } else if (text.equals("red")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_red);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#e6194B"));
                            } else if (text.equals("green")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_green);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#3cb44b"));
                            } else if (text.equals("blue")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_blue);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#4363d8"));
                            } else if (text.equals("orange")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_orange);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#f58231"));
                            } else if (text.equals("brown")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_brown);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#9A6324"));
                            } else if (text.equals("black")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_black);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#000000"));
                            } else if (text.equals("cyan")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_cyan);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#42d4f4"));
                            } else if (text.equals("lime")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_lime);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#bfef45"));
                            } else if (text.equals("magenta")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_magenta);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#f032e6"));
                            } else if (text.equals("navy")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_navy);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#000075"));
                            } else if (text.equals("pink")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_pink);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#fabed4"));
                            } else if (text.equals("yellow")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_yellow);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#ffe119"));
                            } else if (text.equals("purple")) {
                                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_purple);
                                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#911eb4"));
                            }
                        }
                    }
                }
                break;
            case 100:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        String text = bundle.getString("icon", "games");
                        if (getView() != null && getContext() != null) {
                            View showing_icon_in_color_good_habits = getView().findViewById(R.id.showing_icon_in_color_good_habits);
                            icon = text;
                            if (text.equals("games")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_videogame_asset_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("social_media")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_stay_current_portrait_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("shopping")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_shopping_cart_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("smoking")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_smoking_rooms_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("alcohol")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_sports_bar_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("lying")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_record_voice_over_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("procrastination")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_restore_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("cross")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_clear_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("coffee")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_local_cafe_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("fast food")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_fastfood_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("money")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_money_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("sleeping")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_airline_seat_individual_suite_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("healing")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_healing_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("work")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_work_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("tv")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_tv_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("couch")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_weekend_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("other")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.happy_face_dark_green).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            }
                        }
                    }
                }
                break;
            case 1001:
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    String text = bundle.getString("habit_name", "Custom");
                    if (getView() != null) {
                        Button submit_data_first_time_button = getView().findViewById(R.id.submit_data_first_time_button);
                        EditText edit_text_showing_habit_name = getView().findViewById(R.id.edit_text_showing_habit_name);
                        if (edit_text_showing_habit_name.getVisibility() != View.VISIBLE) {
                            ConstraintLayout constraintLayout = getView().findViewById(R.id.layout_to_show_under_scroll_view);
                            ConstraintSet constraintSet = new ConstraintSet();
                            constraintSet.clone(constraintLayout);
                            constraintSet.applyTo(constraintLayout);
                            edit_text_showing_habit_name.setVisibility(View.VISIBLE);
                            edit_text_showing_habit_name.setText(text);
                            edit_text_showing_habit_name.setSelection(edit_text_showing_habit_name.getText().toString().length());
                        } else {
                            edit_text_showing_habit_name.setText(text);
                            edit_text_showing_habit_name.setSelection(edit_text_showing_habit_name.getText().toString().length());
                        }
                        if (submit_data_first_time_button.getText().toString().equals("Choose a habit")) {
                            submit_data_first_time_button.setText("Change the habit");
                        }
                        which_spinner_is_chosen = text;
                    }
                }
                break;
            case 22:
                year_global = data.getIntExtra("year", 2001);
                month_global = data.getIntExtra("month", 1);
                day_global = data.getIntExtra("day", 20);
                Universal_time_picker universal_time_picker = new Universal_time_picker();
                universal_time_picker.setTargetFragment(edit_bad_habits.this, 222);
                universal_time_picker.show(getActivity().getSupportFragmentManager(), "time_picker");
                break;
            case 222:
                if (getView() != null && getContext() != null) {
                    int hour = data.getIntExtra("hour", 6);
                    int minute = data.getIntExtra("minute", 30);
                    Button button_that_will_open_time_user_hacked = getView().findViewById(R.id.button_that_will_open_time_user_hacked);
                    TextView text_to_sayy_enter_time_of_last_relapse = getView().findViewById(R.id.text_to_sayy_enter_time_of_last_relapse);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, year_global);
                    calendar.set(Calendar.MONTH, month_global);
                    calendar.set(Calendar.DAY_OF_MONTH, day_global);
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE, minute);
                    Date date = new Date();
                    date.setTime(calendar.getTimeInMillis());
                    String date_in_string = DateFormat.getDateInstance(DateFormat.MEDIUM).format(date);
                    String time_in_string = DateFormat.getTimeInstance(DateFormat.SHORT).format(date);
                    time_in_milli = calendar.getTimeInMillis();
                    button_that_will_open_time_user_hacked.setText("Change Date");
                    text_to_sayy_enter_time_of_last_relapse.setText("Date: ".concat(time_in_string).concat(" ").concat(date_in_string));
                    Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                    text_to_sayy_enter_time_of_last_relapse.setTypeface(boldTypeface);
                    text_to_sayy_enter_time_of_last_relapse.setTextSize(16);
                }
                break;
        }
    }

    private void update_button_listen() {
        if (getView() != null) {
            Button button_to_submit_good_habit_in_habits = getView().findViewById(R.id.button_to_submit_good_habit_in_habits);
            button_to_submit_good_habit_in_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (check_if_done()) {
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("list_of_the_bad_habits", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        String old = sharedPreferences.getString("Bad_habits","");
                        String save_me = "";
                        String[] big_split = old.split("spit_max_for_the_bad_habits");
                        for(int i = 0;i<big_split.length;i++){
                            //String[] small_split = big_split[i].split("split_here_bad_habits");
                            if(i == position){
                                save_me = save_me.concat(return_data_to_save()).concat("spit_max_for_the_bad_habits");
                            } else {
                                save_me = save_me.concat(big_split[i]).concat("spit_max_for_the_bad_habits");
                            }
                        }
                        myEdit.putString("Bad_habits",save_me);
                        myEdit.commit();
                        edit_bad_habits edit_a_habit = (edit_bad_habits) getActivity().getSupportFragmentManager().findFragmentByTag("edit bad habit");
                        View_home_habit view_home = (View_home_habit) getActivity().getSupportFragmentManager().findFragmentByTag("view home");
                        getActivity().getSupportFragmentManager().beginTransaction().show(view_home).remove(edit_a_habit).commit();
                    }
                }
            });
        }
    }

    private void back_button_listen() {
        if (getView() != null && getActivity() != null) {
            Button button_to_go_back_good_habit = getView().findViewById(R.id.button_to_go_back_good_habit);
            final edit_bad_habits edit_a_habit = (edit_bad_habits) getActivity().getSupportFragmentManager().findFragmentByTag("edit bad habit");
            final View_home_habit view_home = (View_home_habit) getActivity().getSupportFragmentManager().findFragmentByTag("view home");
            button_to_go_back_good_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (edit_a_habit != null) {
                        if (getActivity() != null && view_home != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().show(view_home).remove(edit_a_habit).commit();
                        }
                    }
                }
            });
        }
    }

    private boolean check_if_done() {
        EditText edit_text_showing_habit_name = getView().findViewById(R.id.edit_text_showing_habit_name);
        EditText enter_goal_for_new_good_habit_in_habits = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits);
        if (edit_text_showing_habit_name.getVisibility() != View.VISIBLE) {
            Toast.makeText(getActivity(), "Please choose a habit", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edit_text_showing_habit_name.getText().toString().trim().equals("")) {
            Toast.makeText(getActivity(), "Please choose type a habit name", Toast.LENGTH_SHORT).show();
            edit_text_showing_habit_name.setError("Please choose type a habit name");
            return false;
        }
        if (check_if_name_already_exists(edit_text_showing_habit_name.getText().toString().trim())) {
            Toast.makeText(getActivity(), "A habit with the same exact name already exists. PLease change the habit name. You can keep the type", Toast.LENGTH_LONG).show();
            edit_text_showing_habit_name.setError("Change the habit name. You can keep the same type");
            return false;
        }
        if (time_in_milli == 0) {
            Toast.makeText(getActivity(), "Please choose the date that you last did the habit", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (enter_goal_for_new_good_habit_in_habits.getText().toString().equals("") || Integer.parseInt(enter_goal_for_new_good_habit_in_habits.getText().toString()) == 0) {
            if (enter_goal_for_new_good_habit_in_habits.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Goal can't be empty", Toast.LENGTH_SHORT).show();
                enter_goal_for_new_good_habit_in_habits.setError("Goal can't be empty");
            } else {
                Toast.makeText(getActivity(), "Goal can't be zero", Toast.LENGTH_SHORT).show();
                enter_goal_for_new_good_habit_in_habits.setError("Goal can't be zero");
            }
            return false;
        }
        if (color.equals("")) {
            Toast.makeText(getActivity(), "Please choose a color", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (icon.equals("")) {
            Toast.makeText(getActivity(), "Please choose an icon", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean check_if_name_already_exists(String name) {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("list_of_the_bad_habits", MODE_PRIVATE);
            String old = sharedPreferences.getString("Bad_habits", "");
            if (old != null && !old.equals("")) {
                String[] big_split = old.split("spit_max_for_the_bad_habits");
                for (int i = 0; i < big_split.length; i++) {
                    if (i == position) {
                        continue;
                    }
                    String[] small_split = big_split[i].split("split_here_bad_habits");
                    if (name.trim().equals(small_split[1])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private String return_data_to_save() {
        if (getView() != null) {
            EditText edit_text_showing_habit_name = getView().findViewById(R.id.edit_text_showing_habit_name);
            EditText enter_goal_for_new_good_habit_in_habits = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits);
            String save_me = which_spinner_is_chosen;
            save_me = save_me.concat("split_here_bad_habits");
            save_me = save_me.concat(edit_text_showing_habit_name.getText().toString().trim());
            save_me = save_me.concat("split_here_bad_habits");
            save_me = save_me.concat(String.valueOf(time_in_milli));
            save_me = save_me.concat("split_here_bad_habits");
            save_me = save_me.concat(enter_goal_for_new_good_habit_in_habits.getText().toString());
            save_me = save_me.concat("split_here_bad_habits");
            save_me = save_me.concat(color);
            save_me = save_me.concat("split_here_bad_habits");
            save_me = save_me.concat(icon);
            Log.w("save_me",save_me);
            return save_me;
        } else {
            return "";
        }
    }
}