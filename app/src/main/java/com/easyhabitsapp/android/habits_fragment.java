package com.easyhabitsapp.android;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class habits_fragment extends Fragment {

    public habits_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_habits_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        set_teh_recycel_view();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {

        }
    }

    private void set_teh_recycel_view() {
        if (getView() != null && getActivity()!=null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mood", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString("mood_stats", "");
            RecyclerView recycle_view_to_show_the_emergency_options = getView().findViewById(R.id.recycle_view_to_show_the_emergency_options);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            ArrayList<Example_item_emergency> list_for_habits = new ArrayList<>();
            if (shared != null && shared.contains(String.valueOf(Simplify_the_time.return_time_in_midnight(System.currentTimeMillis())))) {
                list_for_habits.add(new Example_item_emergency(Color.parseColor("#000075"),"Mood tracker",return_the_icon("happy_face_navy"),true));
            } else {
                list_for_habits.add(new Example_item_emergency(Color.parseColor("#000075"),"Mood tracker",return_the_icon("happy_face_navy"),false));
            }
            list_for_habits.add(new Example_item_emergency(Color.parseColor("#2ea8b6"),"Journal",return_the_icon("round_edit_24")));
            //list_for_habits.add(new Example_item_emergency(Color.parseColor("#f66b55"),"Lock Phone",return_the_icon("round_screen_lock_portrait_24")));
            list_for_habits.add(new Example_item_emergency(Color.parseColor("#cc4545"),"Weight Tracker",return_the_icon("round_local_dining_24")));
            list_for_habits.add(new Example_item_emergency(Color.parseColor("#5757e7"),"Counter",return_the_icon("round_add_circle_24")));
            list_for_habits.add(new Example_item_emergency(Color.parseColor("#f66b55"),"More Coming Soon...",return_the_icon("round_add_circle_24")));
            Adapter_for_emergency adapter = new Adapter_for_emergency(list_for_habits);
            adapter.set_on_item_click_listener_for_mood(new Adapter_for_emergency.OnItemClickListener() {
                @Override
                public void onItemClick(int which) {
                    save_the_mood(which);
                }
            });
            adapter.set_on_card_click_listener_for_mood(new Adapter_for_emergency.OnCardClickListener() {
                @Override
                public void onCardClick(String which) {
                    if (getActivity() != null) {
                        if (which.equals("Mood tracker")) {
                            mood_tracker new_fragment = new mood_tracker();
                            habits_fragment old_fragment = (habits_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("habits");
                            mood_tracker check_fragment = (mood_tracker) getActivity().getSupportFragmentManager().findFragmentByTag("mood tracker");
                            if (check_fragment != null) {
                                getActivity().getSupportFragmentManager().beginTransaction().remove(check_fragment).commit();
                            }
                            if (old_fragment != null) {
                                getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, new_fragment, "mood tracker").show(new_fragment).commit();
                            }
                        } else if (which.equals("Journal")) {
                            Intent intent = new Intent(getActivity(), Journal_emergency.class);
                            startActivity(intent);
                            getActivity().overridePendingTransition(0, 0);
                        /*} else if (which.equals("Lock Phone")) {
                            Intent intent = new Intent(getActivity(), Locking_the_screen.class);
                            startActivity(intent);
                            getActivity().overridePendingTransition(0, 0);*/
                        } else if (which.equals("Weight Tracker")) {
                            Intent intent = new Intent(getActivity(), Weight_tracker_emergency.class);
                            startActivity(intent);
                            getActivity().overridePendingTransition(0, 0);
                        } else if (which.equals("Counter")) {
                            Intent intent = new Intent(getActivity(), Counter_activity.class);
                            startActivity(intent);
                            getActivity().overridePendingTransition(0, 0);
                        } else if (which.equals("More Coming Soon...")) {
                            Toast.makeText(getActivity(), "More tools are coming soon", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            recycle_view_to_show_the_emergency_options.setLayoutManager(linearLayoutManager);
            recycle_view_to_show_the_emergency_options.setAdapter(adapter);
        }
    }

    private Drawable return_the_icon(String name) {
        Resources resources = getContext().getResources();
        final int resourceId = resources.getIdentifier(name, "drawable",
                getContext().getPackageName());
        return resources.getDrawable(resourceId, null);
    }

    private void save_the_mood(int mood) {
        if (getView() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mood", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString("mood_stats", "");
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            if (shared != null) {
                myEdit.putString("mood_stats", shared.concat(String.valueOf(mood)).concat("small_split").concat(String.valueOf(Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()))).concat("max_divide"));
                myEdit.commit();
            } else {
                myEdit.putString("mood_stats", "".concat(String.valueOf(mood)).concat("small_split").concat(String.valueOf(Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()))).concat("max_divide"));
                myEdit.commit();
            }

        }
    }
    /*private String reminders = "";
    private String color = "teal";
    private String icon;
    private int which_habit_am_i_on = 1;
    private int max_habits;
    private String frequency_global = "";
    private Button calender_button_showing_shadow_1;
    private Button calender_button_showing_shadow_2;
    private Button calender_button_showing_shadow_3;
    private Button calender_button_showing_shadow_4;
    private Button calender_button_showing_shadow_5;
    private Button calender_button_showing_shadow_6;
    private Button calender_button_showing_shadow_7;
    private Button calender_button_showing_shadow_8;
    private Button calender_button_showing_shadow_9;
    private Button calender_button_showing_shadow_10;
    private Button calender_button_showing_shadow_11;
    private Button calender_button_showing_shadow_12;
    private Button calender_button_showing_shadow_13;
    private Button calender_button_showing_shadow_14;
    private Button calender_button_showing_shadow_15;
    private Button calender_button_showing_shadow_16;
    private Button calender_button_showing_shadow_17;
    private Button calender_button_showing_shadow_18;
    private Button calender_button_showing_shadow_19;
    private Button calender_button_showing_shadow_20;
    private Button calender_button_showing_shadow_21;
    private Button calender_button_showing_shadow_22;
    private Button calender_button_showing_shadow_23;
    private Button calender_button_showing_shadow_24;
    private Button calender_button_showing_shadow_25;
    private Button calender_button_showing_shadow_26;
    private Button calender_button_showing_shadow_27;
    private Button calender_button_showing_shadow_28;
    private Button calender_button_showing_shadow_29;
    private Button calender_button_showing_shadow_30;
    private Button calender_button_showing_shadow_31;
    private Button calender_button_showing_shadow_32;
    private Button calender_button_showing_shadow_33;
    private Button calender_button_showing_shadow_34;
    private Button calender_button_showing_shadow_35;
    private Button calender_button_showing_shadow_36;
    private Button calender_button_showing_shadow_37;
    private String color_the_today;
    private Drawable first_part_rectangle_calender;
    private Drawable middle_part_rectangle_calender;
    private Drawable last_part_rectangle_calender;
    private Drawable first_and_last_part_rectangle_calender;
    private String frequency_faster;
    private String history_faster;
    private String[] history_split_faster;
    private String[] colors;
    private long start_date;
    private int which_main_screen_is_this_on;
    private String array_history;
    private LineChart line_chart_for_streak;
    private boolean am_i_in_edit = false;
    private ArrayList<Integer> streaks_information;
    private View[] list_of_all_the_calender_views;
    private NavigableSet<Long> time_history_with_all_values;
    private ArrayList<Long> array_with_all_values;

    public habits_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_habits_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        add_the_views();
        which_screen_to_open();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            add_the_views();
            which_screen_to_open();
        }
    }

    private void add_a_new_good_habit_listener() {
        if (getView() != null) {
            Button button_to_add_good_habits_in_habits = getView().findViewById(R.id.button_to_add_good_habits_in_habits);
            button_to_add_good_habits_in_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_a_screen(1);
                }
            });
        }
    }

    public void open_a_screen(int which) {
        if (getView() != null) {
            ConstraintLayout layout_to_show_when_there_is_no_good_habits = getView().findViewById(R.id.layout_to_show_when_there_is_no_good_habits);
            ScrollView habit_scroll_view = getView().findViewById(R.id.habit_scroll_view);
            ConstraintLayout layout_with_good_habits_in_habits = getView().findViewById(R.id.layout_with_good_habits_in_habits);
            ConstraintLayout layout_showing_all_the_detail_of_good_habit_in_good_habits = getView().findViewById(R.id.layout_showing_all_the_detail_of_good_habit_in_good_habits);
            if (which == 0) {
                // no good habits yet
                layout_to_show_when_there_is_no_good_habits.setVisibility(View.VISIBLE);
                habit_scroll_view.setVisibility(View.GONE);
                layout_with_good_habits_in_habits.setVisibility(View.GONE);
                layout_showing_all_the_detail_of_good_habit_in_good_habits.setVisibility(View.GONE);
                which_main_screen_is_this_on = 0;
                color_the_text_saing_no_good_habits();
                add_a_new_good_habit_listener();
            } else if (which == 1) {
                layout_to_show_when_there_is_no_good_habits.setVisibility(View.GONE);
                habit_scroll_view.setVisibility(View.VISIBLE);
                layout_with_good_habits_in_habits.setVisibility(View.GONE);
                layout_showing_all_the_detail_of_good_habit_in_good_habits.setVisibility(View.GONE);
                which_main_screen_is_this_on = 1;
                goal_days_text_watcher();
                frequency_edit_text_watcher();
                go_back_to_first_screen();
                add_a_reminder_button_listen();
                choose_color_for_habit();
                pick_button_listener();
                view_reminder_button_listen();
                done_button_listener();
                choose_habit_frequency_button_listener();
            } else if (which == 2) {
                layout_to_show_when_there_is_no_good_habits.setVisibility(View.GONE);
                habit_scroll_view.setVisibility(View.GONE);
                layout_with_good_habits_in_habits.setVisibility(View.VISIBLE);
                layout_showing_all_the_detail_of_good_habit_in_good_habits.setVisibility(View.GONE);
                which_main_screen_is_this_on = 2;
                set_up_the_colors();
                determind_the_max_habits();
                back_button_listen();
                forward_button_listen();
                fill_up_the_card();
                update_the_arrows();
                mood_buttons_listen();
                check_if_there_is_input_today();
                add_button_habits_listen();
                check_if_good_habits_input_has_been_recorded();
                yes_and_no_button_listen_for_good_habits();
                set_up_the_card_button_listen();
                return_the_percent_for_card();
                mood_tracker_listen();
            } else if (which == 3) {
                layout_to_show_when_there_is_no_good_habits.setVisibility(View.GONE);
                habit_scroll_view.setVisibility(View.GONE);
                layout_with_good_habits_in_habits.setVisibility(View.GONE);
                layout_showing_all_the_detail_of_good_habit_in_good_habits.setVisibility(View.VISIBLE);
                which_main_screen_is_this_on = 3;
                color_the_4_drawables();
                define_values_for_pre_card();
                add_all_the_values_into_history_array_list();
                color_the_views_on_click_of_card();
                set_button_yes_or_no();
                set_the_text_for_in_card();
                yes_and_no_button_listen_in_good_habits_view();
                back_in_good_habits_information_screen();
                set_the_streak();
                read_the_best_and_average_streak();

                define_the_buttons();
                set_the_first_day_of_the_week_number();
                set_the_days_on_the_real_text();
                back_and_front_button_listen();
                color_the_today_value();
                calender_button_listeners();
                remove_the_hiding_buttons();
                color_today();
                color_the_calender();
                set_up_share_and_yes_or_no_buttons();
                yes_and_no_button_listen_under_the_calender();
                color_the_background();
                set_up_day_of_week_bar_chart();
                divide_it_into_weeks();
                clear_the_middle();
                make_the_middle_come_again();
                color_the_middle();
                draw_pie_chart();
                set_up_the_various_streak_chart();
                watch_all_the_share_button();
                three_dots_to_delete_or_edit_at_top();
                setup_the_four_information_card();
                forward_and_back_button_listen();
                restart_all_the_year_values();
                set_the_title_of_the_year_habit();
                set_the_leap_year();
                put_values_into_year_in_good_habits();
                set_the_buttons();
                blur_the_views();
                buy_premuim_button_listen();
            }
        }
    }

    private void color_the_text_saing_no_good_habits() {
        if (getView() != null) {
            TextView text_view_saying_you_didnt_add_any_good_habits_yet = getView().findViewById(R.id.text_view_saying_you_didnt_add_any_good_habits_yet);
            Spannable spannable = new SpannableString("It looks like you didn't add good habits yet. Press the button below to add some!!");
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 29, 40, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 71, 80, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text_view_saying_you_didnt_add_any_good_habits_yet.setText(spannable);
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

    private void frequency_edit_text_watcher() {
        if (getView() != null) {
            final EditText enter_number_of_days_for_habits = getView().findViewById(R.id.enter_number_of_days_for_habits);
            final TextView text_just_saying_days_in_habits = getView().findViewById(R.id.text_just_saying_days_in_habits);
            final Button button_saying_every_day_about_the_habit = getView().findViewById(R.id.button_saying_every_day_about_the_habit);
            final Button button_frequent_in_starting_a_good_habit = getView().findViewById(R.id.button_frequent_in_starting_a_good_habit);
            enter_number_of_days_for_habits.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (enter_number_of_days_for_habits.hasFocus()) {
                        if (!charSequence.toString().equals("")) {
                            if (Integer.parseInt(charSequence.toString()) == 1) {
                                if (!button_saying_every_day_about_the_habit.getText().toString().contains("✓")) {
                                    text_just_saying_days_in_habits.setText(" day");
                                    button_saying_every_day_about_the_habit.setTextColor(Color.WHITE);
                                    button_frequent_in_starting_a_good_habit.setTextColor(Color.BLACK);
                                    button_saying_every_day_about_the_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
                                    button_frequent_in_starting_a_good_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                                    button_saying_every_day_about_the_habit.setText(button_saying_every_day_about_the_habit.getText().toString().concat(" ✓"));
                                    button_frequent_in_starting_a_good_habit.setText(button_frequent_in_starting_a_good_habit.getText().toString().replace(" ✓", ""));
                                }
                            } else {
                                if (!button_frequent_in_starting_a_good_habit.getText().toString().contains("✓")) {
                                    text_just_saying_days_in_habits.setText(" days");
                                    button_saying_every_day_about_the_habit.setTextColor(Color.BLACK);
                                    button_frequent_in_starting_a_good_habit.setTextColor(Color.WHITE);
                                    button_saying_every_day_about_the_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                                    button_frequent_in_starting_a_good_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
                                    button_frequent_in_starting_a_good_habit.setText(button_frequent_in_starting_a_good_habit.getText().toString().concat(" ✓"));
                                    button_saying_every_day_about_the_habit.setText(button_saying_every_day_about_the_habit.getText().toString().replace(" ✓", ""));
                                }
                            }
                        } else {
                            if (!button_frequent_in_starting_a_good_habit.getText().toString().contains("✓")) {
                                text_just_saying_days_in_habits.setText(" days");
                                button_saying_every_day_about_the_habit.setTextColor(Color.BLACK);
                                button_frequent_in_starting_a_good_habit.setTextColor(Color.WHITE);
                                button_saying_every_day_about_the_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                                button_frequent_in_starting_a_good_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
                                button_frequent_in_starting_a_good_habit.setText(button_frequent_in_starting_a_good_habit.getText().toString().concat(" ✓"));
                                button_saying_every_day_about_the_habit.setText(button_saying_every_day_about_the_habit.getText().toString().replace(" ✓", ""));
                            }
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    private void choose_color_for_habit() {
        if (getView() != null) {
            Button button_to_choose_color_of_new_good_habit = getView().findViewById(R.id.button_to_choose_color_of_new_good_habit);
            button_to_choose_color_of_new_good_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getView() != null) {
                        Bottom_sheet_choose_good_habit_color bottomSheetDialog = new Bottom_sheet_choose_good_habit_color();
                        bottomSheetDialog.setTargetFragment(habits_fragment.this, 1000);
                        bottomSheetDialog.show(getActivity().getSupportFragmentManager(), "tag");
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
                        String text = bundle.getString("icon", "fitness");
                        if (getView() != null && getContext() != null) {
                            View showing_icon_in_color_good_habits = getView().findViewById(R.id.showing_icon_in_color_good_habits);
                            icon = text;
                            if (text.equals("fitness")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_fitness_center_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("finance")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_account_balance_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("education")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_school_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("reading")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_menu_book_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("socializing")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_group_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("eating healthy")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_local_dining_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("mediation")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_self_improvement_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("running/walking")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_directions_run_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("sports")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_sports_tennis_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("fasting")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_no_food_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("outdoors")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_terrain_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("sleeping healthy")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_airline_seat_individual_suite_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("photography")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_photo_camera_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("art")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_brush_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("time")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_query_builder_24).mutate();
                                TypedValue typedValue = new TypedValue();
                                getContext().getTheme().resolveAttribute(Color.WHITE, typedValue, true);
                                icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
                                showing_icon_in_color_good_habits.setBackground(icon);
                            } else if (text.equals("business")) {
                                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_business_24).mutate();
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
            case 10:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        String time = bundle.getString("time", "08:00 AM");
                        String days = bundle.getString("days", "0123456");
                        reminders = reminders.concat(time).concat("tiny_cut_for_reminder").concat(days).concat("big_cut_for_reminder");
                        update_the_view_reminder_button();
                    }
                }
                break;
            case 99:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        reminders = bundle.getString("reminder", "08:00 AM".concat("tiny_cut_for_reminder").concat("0123456").concat("big_cut_for_reminder"));
                        update_the_view_reminder_button();
                    }
                }
                break;
            case 1010:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null && getView() != null) {
                        String frequency = bundle.getString("frequency", "");
                        update_the_frequency(frequency);
                        frequency_global = frequency;
                    }
                }
                break;
        }
    }

    private void pick_button_listener() {
        if (getView() != null) {
            Button button_to_choose_icon_of_the_new_good_habit = getView().findViewById(R.id.button_to_choose_icon_of_the_new_good_habit);
            button_to_choose_icon_of_the_new_good_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getView() != null) {
                        Dialog_to_choose_good_habit dialog_to_choose_good_habit = new Dialog_to_choose_good_habit();
                        dialog_to_choose_good_habit.setTargetFragment(habits_fragment.this, 100);
                        dialog_to_choose_good_habit.show(getActivity().getSupportFragmentManager(), "tag");
                    }
                }
            });
        }
    }

    private void go_back_to_first_screen() {
        if (getView() != null) {
            Button button_to_go_back_good_habit = getView().findViewById(R.id.button_to_go_back_good_habit);
            button_to_go_back_good_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (read_the_habits().equals("")) {
                        open_a_screen(0);
                        restart_values();
                    } else {
                        if (!am_i_in_edit) {
                            open_a_screen(2);
                            restart_values();
                        } else {
                            restart_values();
                            restart_third_screen_good_information();
                            open_a_screen(3);
                            am_i_in_edit = false;
                        }
                    }
                }
            });
        }
    }

    private void add_a_reminder_button_listen() {
        if (getView() != null) {
            Button button_to_add_reminder_in_good_habits = getView().findViewById(R.id.button_to_add_reminder_in_good_habits);
            button_to_add_reminder_in_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getView() != null) {
                        DialogFragment Time_picker_for_good_habit_reminder = new Time_picker_for_good_habit_reminder();
                        Time_picker_for_good_habit_reminder.setTargetFragment(habits_fragment.this, 0);
                        Time_picker_for_good_habit_reminder.show(getActivity().getSupportFragmentManager(), "time picker");
                    }
                }
            });
        }
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        if (getView() != null) {
            Dialog_to_set_good_habit_reminder dialog_to_set_good_habit_reminder = new Dialog_to_set_good_habit_reminder();
            dialog_to_set_good_habit_reminder.setTargetFragment(habits_fragment.this, 10);
            dialog_to_set_good_habit_reminder.show(getActivity().getSupportFragmentManager(), Simplify_the_time.return_time(String.valueOf(minute).concat(":").concat(String.valueOf(hour))));
        }
    }

    private void update_the_view_reminder_button() {
        if (getView() != null) {
            Button button_to_view_reminder_in_good_habits = getView().findViewById(R.id.button_to_view_reminder_in_good_habits);
            Button button_to_add_reminder_in_good_habits = getView().findViewById(R.id.button_to_add_reminder_in_good_habits);
            TextView number_seven_in_habit_activity = getView().findViewById(R.id.number_seven_in_habit_activity);
            ConstraintLayout constraintLayout = getView().findViewById(R.id.layout_to_show_under_scroll_view);
            if (reminders.equals("")) {
                button_to_view_reminder_in_good_habits.setVisibility(View.GONE);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.clear(button_to_view_reminder_in_good_habits.getId(), ConstraintSet.START);
                constraintSet.clear(button_to_view_reminder_in_good_habits.getId(), ConstraintSet.END);
                constraintSet.clear(button_to_add_reminder_in_good_habits.getId(), ConstraintSet.START);
                constraintSet.clear(button_to_add_reminder_in_good_habits.getId(), ConstraintSet.END);
                constraintSet.connect(button_to_add_reminder_in_good_habits.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                constraintSet.connect(button_to_add_reminder_in_good_habits.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
                constraintSet.applyTo(constraintLayout);
            } else {
                button_to_view_reminder_in_good_habits.setVisibility(View.VISIBLE);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.clear(button_to_view_reminder_in_good_habits.getId(), ConstraintSet.START);
                constraintSet.clear(button_to_view_reminder_in_good_habits.getId(), ConstraintSet.END);
                constraintSet.clear(button_to_add_reminder_in_good_habits.getId(), ConstraintSet.START);
                constraintSet.clear(button_to_add_reminder_in_good_habits.getId(), ConstraintSet.END);
                constraintSet.createHorizontalChain(number_seven_in_habit_activity.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, new int[]{button_to_add_reminder_in_good_habits.getId(), button_to_view_reminder_in_good_habits.getId()}, null, ConstraintSet.CHAIN_SPREAD);
                constraintSet.applyTo(constraintLayout);
            }
        }
    }

    private void view_reminder_button_listen() {
        if (getView() != null) {
            Button button_to_view_reminder_in_good_habits = getView().findViewById(R.id.button_to_view_reminder_in_good_habits);
            button_to_view_reminder_in_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getView() != null) {
                        Dialog_to_view_or_change_reminder dialog_to_view_or_change_reminder = new Dialog_to_view_or_change_reminder();
                        dialog_to_view_or_change_reminder.setTargetFragment(habits_fragment.this, 99);
                        dialog_to_view_or_change_reminder.show(getActivity().getSupportFragmentManager(), reminders);
                    }
                }
            });
        }
    }

    public void restart_values() {
        if (getView() != null) {
            EditText edit_text_enter_good_habit_name_in_habit = getView().findViewById(R.id.edit_text_enter_good_habit_name_in_habit);
            EditText edit_text_enter_good_habit_question_in_habit = getView().findViewById(R.id.edit_text_enter_good_habit_question_in_habit);
            EditText enter_goal_for_new_good_habit_in_habits = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits);
            EditText enter_number_of_days_for_habits = getView().findViewById(R.id.enter_number_of_days_for_habits);
            TextView text_saying_days_beside_habit_edit_text = getView().findViewById(R.id.text_saying_days_beside_habit_edit_text);
            TextView text_just_saying_days_in_habits = getView().findViewById(R.id.text_just_saying_days_in_habits);
            View view_showing_the_icon_and_its_color = getView().findViewById(R.id.view_showing_the_icon_and_its_color);
            View showing_icon_in_color_good_habits = getView().findViewById(R.id.showing_icon_in_color_good_habits);
            TextView text_view_asying_this_is_example_of_icon = getView().findViewById(R.id.text_view_asying_this_is_example_of_icon);
            Button button_saying_every_day_about_the_habit = getView().findViewById(R.id.button_saying_every_day_about_the_habit);
            Button button_frequent_in_starting_a_good_habit = getView().findViewById(R.id.button_frequent_in_starting_a_good_habit);
            Button button_saying_days_of_week_about_habit = getView().findViewById(R.id.button_saying_days_of_week_about_habit);
            ConstraintLayout layout_to_show_under_scroll_view = getView().findViewById(R.id.layout_to_show_under_scroll_view);
            ConstraintLayout layout_that_contains_frequency_for_habits = getView().findViewById(R.id.layout_that_contains_frequency_for_habits);
            TextView text_view_saying_this_is_the_frequency_in_habit = getView().findViewById(R.id.text_view_saying_this_is_the_frequency_in_habit);
            TextView text_saying_which_days_of_the_week_are_supposed_for_the_habit = getView().findViewById(R.id.text_saying_which_days_of_the_week_are_supposed_for_the_habit);
            ScrollView habit_scroll_view = getView().findViewById(R.id.habit_scroll_view);
            enter_goal_for_new_good_habit_in_habits.clearFocus();
            enter_number_of_days_for_habits.clearFocus();
            layout_to_show_under_scroll_view.requestFocus();
            edit_text_enter_good_habit_name_in_habit.setText("");
            edit_text_enter_good_habit_question_in_habit.setText("");
            enter_goal_for_new_good_habit_in_habits.setText("");
            enter_number_of_days_for_habits.setText("");
            text_saying_days_beside_habit_edit_text.setText(" days");
            text_just_saying_days_in_habits.setText(" days");
            view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_dark_green);
            showing_icon_in_color_good_habits.setBackgroundResource(0);
            text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#607D8B"));
            reminders = "";
            update_the_view_reminder_button();
            button_saying_every_day_about_the_habit.setTextColor(Color.WHITE);
            button_frequent_in_starting_a_good_habit.setTextColor(Color.BLACK);
            button_saying_days_of_week_about_habit.setTextColor(Color.BLACK);
            button_saying_every_day_about_the_habit.setText("Everyday ✓");
            button_frequent_in_starting_a_good_habit.setText("Frequent");
            button_saying_days_of_week_about_habit.setText("Days");
            button_saying_every_day_about_the_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            button_frequent_in_starting_a_good_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            button_saying_days_of_week_about_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            layout_that_contains_frequency_for_habits.setVisibility(View.VISIBLE);
            enter_number_of_days_for_habits.setText("1");
            enter_number_of_days_for_habits.setSelection(enter_number_of_days_for_habits.getText().toString().length());
            text_view_saying_this_is_the_frequency_in_habit.setVisibility(View.VISIBLE);
            text_saying_which_days_of_the_week_are_supposed_for_the_habit.setVisibility(View.INVISIBLE);
            edit_text_enter_good_habit_name_in_habit.setError(null);
            edit_text_enter_good_habit_question_in_habit.setError(null);
            enter_goal_for_new_good_habit_in_habits.setError(null);
            enter_number_of_days_for_habits.setError(null);
            habit_scroll_view.fullScroll(ScrollView.FOCUS_UP);
        }
    }

    private void done_button_listener() {
        if (getView() != null) {
            Button button_to_submit_good_habit_in_habits = getView().findViewById(R.id.button_to_submit_good_habit_in_habits);
            final EditText edit_text_enter_good_habit_name_in_habit = getView().findViewById(R.id.edit_text_enter_good_habit_name_in_habit);
            final EditText edit_text_enter_good_habit_question_in_habit = getView().findViewById(R.id.edit_text_enter_good_habit_question_in_habit);
            final EditText enter_goal_for_new_good_habit_in_habits = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits);
            final EditText enter_number_of_days_for_habits = getView().findViewById(R.id.enter_number_of_days_for_habits);
            final ScrollView habit_scroll_view = getView().findViewById(R.id.habit_scroll_view);
            final View showing_icon_in_color_good_habits = getView().findViewById(R.id.showing_icon_in_color_good_habits);
            final TextView text_saying_which_days_of_the_week_are_supposed_for_the_habit = getView().findViewById(R.id.text_saying_which_days_of_the_week_are_supposed_for_the_habit);
            button_to_submit_good_habit_in_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!am_i_in_edit) {
                        if (edit_text_enter_good_habit_name_in_habit.getText().toString().trim().equals("")) {
                            edit_text_enter_good_habit_name_in_habit.setError("cant't be empty");
                            habit_scroll_view.smoothScrollTo(0, edit_text_enter_good_habit_name_in_habit.getTop());
                            Toast.makeText(getActivity(), "Please enter a title", Toast.LENGTH_SHORT).show();
                        } else {
                            if (edit_text_enter_good_habit_question_in_habit.getText().toString().trim().equals("")) {
                                edit_text_enter_good_habit_question_in_habit.setError("cant't be empty");
                                habit_scroll_view.smoothScrollTo(0, edit_text_enter_good_habit_question_in_habit.getTop());
                                Toast.makeText(getActivity(), "Please enter a question", Toast.LENGTH_SHORT).show();
                            } else {
                                if (enter_goal_for_new_good_habit_in_habits.getText().toString().equals("")) {
                                    enter_goal_for_new_good_habit_in_habits.setError("cant't be empty");
                                    habit_scroll_view.smoothScrollTo(0, enter_goal_for_new_good_habit_in_habits.getTop());
                                    Toast.makeText(getActivity(), "Please enter a goal", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (showing_icon_in_color_good_habits.getBackground() == null) {
                                        habit_scroll_view.smoothScrollTo(0, showing_icon_in_color_good_habits.getTop());
                                        Toast.makeText(getActivity(), "Please pick an icon", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (enter_number_of_days_for_habits.getText().toString().equals("") && text_saying_which_days_of_the_week_are_supposed_for_the_habit.getVisibility() == View.INVISIBLE) {
                                            enter_number_of_days_for_habits.setError("cant't be empty");
                                            habit_scroll_view.smoothScrollTo(0, enter_number_of_days_for_habits.getTop());
                                            Toast.makeText(getActivity(), "Please enter a frequency", Toast.LENGTH_SHORT).show();
                                        } else {
                                            if (retuen_all_the_titles().contains(edit_text_enter_good_habit_name_in_habit.getText().toString().trim().toLowerCase())) {
                                                edit_text_enter_good_habit_name_in_habit.setError("Habit already exists");
                                                habit_scroll_view.smoothScrollTo(0, edit_text_enter_good_habit_name_in_habit.getTop());
                                                Toast.makeText(getActivity(), "A habit with the same title exists. Please change the title", Toast.LENGTH_LONG).show();
                                            } else {
                                                save_the_new_habit();
                                                set_notifiction_new(edit_text_enter_good_habit_name_in_habit.getText().toString().trim().toLowerCase(), return_number_off_bad_habits()-1);
                                                restart_values();
                                                open_a_screen(2);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (edit_text_enter_good_habit_name_in_habit.getText().toString().trim().equals("")) {
                            edit_text_enter_good_habit_name_in_habit.setError("cant't be empty");
                            habit_scroll_view.smoothScrollTo(0, edit_text_enter_good_habit_name_in_habit.getTop());
                            Toast.makeText(getActivity(), "Please enter a title", Toast.LENGTH_SHORT).show();
                        } else {
                            if (edit_text_enter_good_habit_question_in_habit.getText().toString().trim().equals("")) {
                                edit_text_enter_good_habit_question_in_habit.setError("cant't be empty");
                                habit_scroll_view.smoothScrollTo(0, edit_text_enter_good_habit_question_in_habit.getTop());
                                Toast.makeText(getActivity(), "Please enter a question", Toast.LENGTH_SHORT).show();
                            } else {
                                if (enter_goal_for_new_good_habit_in_habits.getText().toString().equals("")) {
                                    enter_goal_for_new_good_habit_in_habits.setError("cant't be empty");
                                    habit_scroll_view.smoothScrollTo(0, enter_goal_for_new_good_habit_in_habits.getTop());
                                    Toast.makeText(getActivity(), "Please enter a icon", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (showing_icon_in_color_good_habits.getBackground() == null) {
                                        habit_scroll_view.smoothScrollTo(0, showing_icon_in_color_good_habits.getTop());
                                        Toast.makeText(getActivity(), "Please pick an goal", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (enter_number_of_days_for_habits.getText().toString().equals("") && text_saying_which_days_of_the_week_are_supposed_for_the_habit.getVisibility() == View.INVISIBLE) {
                                            enter_number_of_days_for_habits.setError("cant't be empty");
                                            habit_scroll_view.smoothScrollTo(0, enter_number_of_days_for_habits.getTop());
                                            Toast.makeText(getActivity(), "Please enter a frequency", Toast.LENGTH_SHORT).show();
                                        } else {
                                            if (check_if_it_is_in_anything_else(edit_text_enter_good_habit_name_in_habit.getText().toString().trim().toLowerCase())) {
                                                edit_text_enter_good_habit_name_in_habit.setError("Habit already exists");
                                                habit_scroll_view.smoothScrollTo(0, edit_text_enter_good_habit_name_in_habit.getTop());
                                                Toast.makeText(getActivity(), "A habit with the same title exists. Please change the title", Toast.LENGTH_LONG).show();
                                            } else {
                                                cancel_alarm(which_habit_am_i_on-1);
                                                edit_the_current_habit();
                                                restart_values();
                                                restart_third_screen_good_information();
                                                set_up_the_colors();
                                                fill_up_the_card();
                                                open_a_screen(3);
                                                am_i_in_edit = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    private void save_the_new_habit() {
        if (getView() != null && getActivity() != null) {
            EditText edit_text_enter_good_habit_name_in_habit = getView().findViewById(R.id.edit_text_enter_good_habit_name_in_habit);
            EditText edit_text_enter_good_habit_question_in_habit = getView().findViewById(R.id.edit_text_enter_good_habit_question_in_habit);
            EditText enter_goal_for_new_good_habit_in_habits = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits);
            EditText enter_number_of_days_for_habits = getView().findViewById(R.id.enter_number_of_days_for_habits);
            TextView text_saying_which_days_of_the_week_are_supposed_for_the_habit = getView().findViewById(R.id.text_saying_which_days_of_the_week_are_supposed_for_the_habit);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits", Context.MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            String old = sharedPreferences.getString("good_history", "");
            if (text_saying_which_days_of_the_week_are_supposed_for_the_habit.getVisibility() != View.VISIBLE) {
                if (old != null) {
                    myEdit.putString("good_history", old.concat(edit_text_enter_good_habit_name_in_habit.getText().toString().trim().toLowerCase()).concat("small_split").concat(edit_text_enter_good_habit_question_in_habit.getText().toString().trim()).concat("small_split".concat(enter_goal_for_new_good_habit_in_habits.getText().toString())).concat("small_split".concat(color).concat("small_split").concat(icon).concat("small_split").concat(enter_number_of_days_for_habits.getText().toString())).concat("small_split").concat(reminders).concat("small_split").concat(String.valueOf(System.currentTimeMillis())).concat("max_divide"));
                } else {
                    myEdit.putString("good_history", edit_text_enter_good_habit_name_in_habit.getText().toString().trim().toLowerCase().concat("small_split").concat(edit_text_enter_good_habit_question_in_habit.getText().toString().trim()).concat("small_split".concat(enter_goal_for_new_good_habit_in_habits.getText().toString())).concat("small_split".concat(color).concat("small_split").concat(icon).concat("small_split").concat(enter_number_of_days_for_habits.getText().toString())).concat("small_split").concat(reminders).concat("small_split").concat(String.valueOf(System.currentTimeMillis())).concat("max_divide"));
                }
            } else {
                if (old != null) {
                    myEdit.putString("good_history", old.concat(edit_text_enter_good_habit_name_in_habit.getText().toString().trim().toLowerCase()).concat("small_split").concat(edit_text_enter_good_habit_question_in_habit.getText().toString().trim()).concat("small_split".concat(enter_goal_for_new_good_habit_in_habits.getText().toString())).concat("small_split".concat(color).concat("small_split").concat(icon).concat("small_split").concat(frequency_global)).concat("small_split").concat(reminders).concat("small_split").concat(String.valueOf(System.currentTimeMillis())).concat("max_divide"));
                } else {
                    myEdit.putString("good_history", edit_text_enter_good_habit_name_in_habit.getText().toString().trim().toLowerCase().concat("small_split").concat(edit_text_enter_good_habit_question_in_habit.getText().toString().trim()).concat("small_split".concat(enter_goal_for_new_good_habit_in_habits.getText().toString())).concat("small_split".concat(color).concat("small_split").concat(icon).concat("small_split").concat(frequency_global)).concat("small_split").concat(reminders).concat("small_split").concat(String.valueOf(System.currentTimeMillis())).concat("max_divide"));
                }
            }
            myEdit.commit();
        }
    }

    private void which_screen_to_open() {
        if (getView() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString("good_history", "empty");
            if (shared != null) {
                if (shared.equals("empty")) {
                    open_a_screen(0);
                } else {
                    open_a_screen(2);
                }
            } else {
                open_a_screen(0);
            }
        }
    }

    private void set_up_the_colors() {
        if (getView() != null && getContext() != null) {
            CardView card_in_good_habits_habits = getView().findViewById(R.id.card_in_good_habits_habits);
            View hbaits_divider_between_mood_in_habits = getView().findViewById(R.id.hbaits_divider_between_mood_in_habits);
            Button yes_i_did_good_habit_today_card = getView().findViewById(R.id.yes_i_did_good_habit_today_card);
            Button no_i_did_good_habit_today_card = getView().findViewById(R.id.no_i_did_good_habit_today_card);
            Button button_go_back_good_habits = getView().findViewById(R.id.button_go_back_good_habits);
            Button button_go_forward_good_habits = getView().findViewById(R.id.button_go_forward_good_habits);
            String read_file = read_the_habits();
            String[] splitter = read_file.split("small_split");
            Button button_to_add_good_habits_under_the_card_in_good_habits = getView().findViewById(R.id.button_to_add_good_habits_under_the_card_in_good_habits);
            if (splitter[3].equals("teal")) {
                card_in_good_habits_habits.setCardBackgroundColor(Color.parseColor("#607D8B"));
                hbaits_divider_between_mood_in_habits.setBackgroundColor(Color.parseColor("#607D8B"));
                yes_i_did_good_habit_today_card.setTextColor(Color.parseColor("#607D8B"));
                no_i_did_good_habit_today_card.setTextColor(Color.parseColor("#607D8B"));
                Spannable spannable = new SpannableString("How was your mood today?");
                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 13, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.round_more_vert_24).mutate();
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, Color.parseColor("#607D8B"));
                Drawable drawable_for_buttons = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons = DrawableCompat.wrap(drawable_for_buttons);
                DrawableCompat.setTint(drawable_for_buttons, Color.parseColor("#607D8B"));
                button_go_back_good_habits.setBackground(drawable_for_buttons);
                Drawable drawable_for_buttons_two = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons_two = DrawableCompat.wrap(drawable_for_buttons_two);
                DrawableCompat.setTint(drawable_for_buttons_two, Color.parseColor("#607D8B"));
                button_go_forward_good_habits.setBackground(drawable_for_buttons_two);
                button_to_add_good_habits_under_the_card_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            } else if (splitter[3].equals("red")) {
                card_in_good_habits_habits.setCardBackgroundColor(Color.parseColor("#e6194B"));
                hbaits_divider_between_mood_in_habits.setBackgroundColor(Color.parseColor("#e6194B"));
                yes_i_did_good_habit_today_card.setTextColor(Color.parseColor("#e6194B"));
                no_i_did_good_habit_today_card.setTextColor(Color.parseColor("#e6194B"));
                Spannable spannable = new SpannableString("How was your mood today?");
                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#e6194B")), 13, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.round_more_vert_24).mutate();
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, Color.parseColor("#e6194B"));
                Drawable drawable_for_buttons = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons = DrawableCompat.wrap(drawable_for_buttons);
                DrawableCompat.setTint(drawable_for_buttons, Color.parseColor("#e6194B"));
                button_go_back_good_habits.setBackground(drawable_for_buttons);
                Drawable drawable_for_buttons_two = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons_two = DrawableCompat.wrap(drawable_for_buttons_two);
                DrawableCompat.setTint(drawable_for_buttons_two, Color.parseColor("#e6194B"));
                button_go_forward_good_habits.setBackground(drawable_for_buttons_two);
                button_to_add_good_habits_under_the_card_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#e6194B")));
            } else if (splitter[3].equals("green")) {
                card_in_good_habits_habits.setCardBackgroundColor(Color.parseColor("#3cb44b"));
                hbaits_divider_between_mood_in_habits.setBackgroundColor(Color.parseColor("#3cb44b"));
                yes_i_did_good_habit_today_card.setTextColor(Color.parseColor("#3cb44b"));
                no_i_did_good_habit_today_card.setTextColor(Color.parseColor("#3cb44b"));
                Spannable spannable = new SpannableString("How was your mood today?");
                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#3cb44b")), 13, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.round_more_vert_24).mutate();
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, Color.parseColor("#3cb44b"));
                Drawable drawable_for_buttons = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons = DrawableCompat.wrap(drawable_for_buttons);
                DrawableCompat.setTint(drawable_for_buttons, Color.parseColor("#3cb44b"));
                button_go_back_good_habits.setBackground(drawable_for_buttons);
                Drawable drawable_for_buttons_two = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons_two = DrawableCompat.wrap(drawable_for_buttons_two);
                DrawableCompat.setTint(drawable_for_buttons_two, Color.parseColor("#3cb44b"));
                button_go_forward_good_habits.setBackground(drawable_for_buttons_two);
                button_to_add_good_habits_under_the_card_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#3cb44b")));
            } else if (splitter[3].equals("blue")) {
                card_in_good_habits_habits.setCardBackgroundColor(Color.parseColor("#4363d8"));
                hbaits_divider_between_mood_in_habits.setBackgroundColor(Color.parseColor("#4363d8"));
                yes_i_did_good_habit_today_card.setTextColor(Color.parseColor("#4363d8"));
                no_i_did_good_habit_today_card.setTextColor(Color.parseColor("#4363d8"));
                Spannable spannable = new SpannableString("How was your mood today?");
                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#4363d8")), 13, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.round_more_vert_24).mutate();
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, Color.parseColor("#4363d8"));
                Drawable drawable_for_buttons = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons = DrawableCompat.wrap(drawable_for_buttons);
                DrawableCompat.setTint(drawable_for_buttons, Color.parseColor("#4363d8"));
                button_go_back_good_habits.setBackground(drawable_for_buttons);
                Drawable drawable_for_buttons_two = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons_two = DrawableCompat.wrap(drawable_for_buttons_two);
                DrawableCompat.setTint(drawable_for_buttons_two, Color.parseColor("#4363d8"));
                button_go_forward_good_habits.setBackground(drawable_for_buttons_two);
                button_to_add_good_habits_under_the_card_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4363d8")));
            } else if (splitter[3].equals("orange")) {
                card_in_good_habits_habits.setCardBackgroundColor(Color.parseColor("#f58231"));
                hbaits_divider_between_mood_in_habits.setBackgroundColor(Color.parseColor("#f58231"));
                yes_i_did_good_habit_today_card.setTextColor(Color.parseColor("#f58231"));
                no_i_did_good_habit_today_card.setTextColor(Color.parseColor("#f58231"));
                Spannable spannable = new SpannableString("How was your mood today?");
                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#f58231")), 13, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.round_more_vert_24).mutate();
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, Color.parseColor("#f58231"));
                Drawable drawable_for_buttons = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons = DrawableCompat.wrap(drawable_for_buttons);
                DrawableCompat.setTint(drawable_for_buttons, Color.parseColor("#f58231"));
                button_go_back_good_habits.setBackground(drawable_for_buttons);
                Drawable drawable_for_buttons_two = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons_two = DrawableCompat.wrap(drawable_for_buttons_two);
                DrawableCompat.setTint(drawable_for_buttons_two, Color.parseColor("#f58231"));
                button_go_forward_good_habits.setBackground(drawable_for_buttons_two);
                button_to_add_good_habits_under_the_card_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#f58231")));
            } else if (splitter[3].equals("brown")) {
                card_in_good_habits_habits.setCardBackgroundColor(Color.parseColor("#9A6324"));
                hbaits_divider_between_mood_in_habits.setBackgroundColor(Color.parseColor("#9A6324"));
                yes_i_did_good_habit_today_card.setTextColor(Color.parseColor("#9A6324"));
                no_i_did_good_habit_today_card.setTextColor(Color.parseColor("#9A6324"));
                Spannable spannable = new SpannableString("How was your mood today?");
                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#9A6324")), 13, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.round_more_vert_24).mutate();
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, Color.parseColor("#9A6324"));
                Drawable drawable_for_buttons = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons = DrawableCompat.wrap(drawable_for_buttons);
                DrawableCompat.setTint(drawable_for_buttons, Color.parseColor("#9A6324"));
                button_go_back_good_habits.setBackground(drawable_for_buttons);
                Drawable drawable_for_buttons_two = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons_two = DrawableCompat.wrap(drawable_for_buttons_two);
                DrawableCompat.setTint(drawable_for_buttons_two, Color.parseColor("#9A6324"));
                button_go_forward_good_habits.setBackground(drawable_for_buttons_two);
                button_to_add_good_habits_under_the_card_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#9A6324")));
            } else if (splitter[3].equals("black")) {
                card_in_good_habits_habits.setCardBackgroundColor(Color.parseColor("#000000"));
                hbaits_divider_between_mood_in_habits.setBackgroundColor(Color.parseColor("#000000"));
                yes_i_did_good_habit_today_card.setTextColor(Color.parseColor("#000000"));
                no_i_did_good_habit_today_card.setTextColor(Color.parseColor("#000000"));
                Spannable spannable = new SpannableString("How was your mood today?");
                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#000000")), 13, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.round_more_vert_24).mutate();
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, Color.parseColor("#000000"));
                Drawable drawable_for_buttons = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons = DrawableCompat.wrap(drawable_for_buttons);
                DrawableCompat.setTint(drawable_for_buttons, Color.parseColor("#000000"));
                button_go_back_good_habits.setBackground(drawable_for_buttons);
                Drawable drawable_for_buttons_two = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons_two = DrawableCompat.wrap(drawable_for_buttons_two);
                DrawableCompat.setTint(drawable_for_buttons_two, Color.parseColor("#000000"));
                button_go_forward_good_habits.setBackground(drawable_for_buttons_two);
                button_to_add_good_habits_under_the_card_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
            } else if (splitter[3].equals("cyan")) {
                card_in_good_habits_habits.setCardBackgroundColor(Color.parseColor("#42d4f4"));
                hbaits_divider_between_mood_in_habits.setBackgroundColor(Color.parseColor("#42d4f4"));
                yes_i_did_good_habit_today_card.setTextColor(Color.parseColor("#42d4f4"));
                no_i_did_good_habit_today_card.setTextColor(Color.parseColor("#42d4f4"));
                Spannable spannable = new SpannableString("How was your mood today?");
                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#42d4f4")), 13, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.round_more_vert_24).mutate();
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, Color.parseColor("#42d4f4"));
                Drawable drawable_for_buttons = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons = DrawableCompat.wrap(drawable_for_buttons);
                DrawableCompat.setTint(drawable_for_buttons, Color.parseColor("#42d4f4"));
                button_go_back_good_habits.setBackground(drawable_for_buttons);
                Drawable drawable_for_buttons_two = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons_two = DrawableCompat.wrap(drawable_for_buttons_two);
                DrawableCompat.setTint(drawable_for_buttons_two, Color.parseColor("#42d4f4"));
                button_go_forward_good_habits.setBackground(drawable_for_buttons_two);
                button_to_add_good_habits_under_the_card_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#42d4f4")));
            } else if (splitter[3].equals("lime")) {
                card_in_good_habits_habits.setCardBackgroundColor(Color.parseColor("#bfef45"));
                hbaits_divider_between_mood_in_habits.setBackgroundColor(Color.parseColor("#bfef45"));
                yes_i_did_good_habit_today_card.setTextColor(Color.parseColor("#bfef45"));
                no_i_did_good_habit_today_card.setTextColor(Color.parseColor("#bfef45"));
                Spannable spannable = new SpannableString("How was your mood today?");
                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#bfef45")), 13, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.round_more_vert_24).mutate();
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, Color.parseColor("#bfef45"));
                Drawable drawable_for_buttons = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons = DrawableCompat.wrap(drawable_for_buttons);
                DrawableCompat.setTint(drawable_for_buttons, Color.parseColor("#bfef45"));
                button_go_back_good_habits.setBackground(drawable_for_buttons);
                Drawable drawable_for_buttons_two = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons_two = DrawableCompat.wrap(drawable_for_buttons_two);
                DrawableCompat.setTint(drawable_for_buttons_two, Color.parseColor("#bfef45"));
                button_go_forward_good_habits.setBackground(drawable_for_buttons_two);
                button_to_add_good_habits_under_the_card_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#bfef45")));
            } else if (splitter[3].equals("magenta")) {
                card_in_good_habits_habits.setCardBackgroundColor(Color.parseColor("#f032e6"));
                hbaits_divider_between_mood_in_habits.setBackgroundColor(Color.parseColor("#f032e6"));
                yes_i_did_good_habit_today_card.setTextColor(Color.parseColor("#f032e6"));
                no_i_did_good_habit_today_card.setTextColor(Color.parseColor("#f032e6"));
                Spannable spannable = new SpannableString("How was your mood today?");
                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#f032e6")), 13, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.round_more_vert_24).mutate();
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, Color.parseColor("#f032e6"));
                Drawable drawable_for_buttons = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons = DrawableCompat.wrap(drawable_for_buttons);
                DrawableCompat.setTint(drawable_for_buttons, Color.parseColor("#f032e6"));
                button_go_back_good_habits.setBackground(drawable_for_buttons);
                Drawable drawable_for_buttons_two = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons_two = DrawableCompat.wrap(drawable_for_buttons_two);
                DrawableCompat.setTint(drawable_for_buttons_two, Color.parseColor("#f032e6"));
                button_go_forward_good_habits.setBackground(drawable_for_buttons_two);
                button_to_add_good_habits_under_the_card_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#f032e6")));
            } else if (splitter[3].equals("navy")) {
                card_in_good_habits_habits.setCardBackgroundColor(Color.parseColor("#000075"));
                hbaits_divider_between_mood_in_habits.setBackgroundColor(Color.parseColor("#000075"));
                yes_i_did_good_habit_today_card.setTextColor(Color.parseColor("#000075"));
                no_i_did_good_habit_today_card.setTextColor(Color.parseColor("#000075"));
                Spannable spannable = new SpannableString("How was your mood today?");
                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#000075")), 13, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.round_more_vert_24).mutate();
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, Color.parseColor("#000075"));
                Drawable drawable_for_buttons = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons = DrawableCompat.wrap(drawable_for_buttons);
                DrawableCompat.setTint(drawable_for_buttons, Color.parseColor("#000075"));
                button_go_back_good_habits.setBackground(drawable_for_buttons);
                Drawable drawable_for_buttons_two = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons_two = DrawableCompat.wrap(drawable_for_buttons_two);
                DrawableCompat.setTint(drawable_for_buttons_two, Color.parseColor("#000075"));
                button_go_forward_good_habits.setBackground(drawable_for_buttons_two);
                button_to_add_good_habits_under_the_card_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000075")));
            } else if (splitter[3].equals("pink")) {
                card_in_good_habits_habits.setCardBackgroundColor(Color.parseColor("#fabed4"));
                hbaits_divider_between_mood_in_habits.setBackgroundColor(Color.parseColor("#fabed4"));
                yes_i_did_good_habit_today_card.setTextColor(Color.parseColor("#fabed4"));
                no_i_did_good_habit_today_card.setTextColor(Color.parseColor("#fabed4"));
                Spannable spannable = new SpannableString("How was your mood today?");
                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#fabed4")), 13, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.round_more_vert_24).mutate();
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, Color.parseColor("#fabed4"));
                Drawable drawable_for_buttons = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons = DrawableCompat.wrap(drawable_for_buttons);
                DrawableCompat.setTint(drawable_for_buttons, Color.parseColor("#fabed4"));
                button_go_back_good_habits.setBackground(drawable_for_buttons);
                Drawable drawable_for_buttons_two = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons_two = DrawableCompat.wrap(drawable_for_buttons_two);
                DrawableCompat.setTint(drawable_for_buttons_two, Color.parseColor("#fabed4"));
                button_go_forward_good_habits.setBackground(drawable_for_buttons_two);
                button_to_add_good_habits_under_the_card_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fabed4")));
            } else if (splitter[3].equals("yellow")) {
                card_in_good_habits_habits.setCardBackgroundColor(Color.parseColor("#ffe119"));
                hbaits_divider_between_mood_in_habits.setBackgroundColor(Color.parseColor("#ffe119"));
                yes_i_did_good_habit_today_card.setTextColor(Color.parseColor("#ffe119"));
                no_i_did_good_habit_today_card.setTextColor(Color.parseColor("#ffe119"));
                Spannable spannable = new SpannableString("How was your mood today?");
                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ffe119")), 13, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.round_more_vert_24).mutate();
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, Color.parseColor("#ffe119"));
                Drawable drawable_for_buttons = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons = DrawableCompat.wrap(drawable_for_buttons);
                DrawableCompat.setTint(drawable_for_buttons, Color.parseColor("#ffe119"));
                button_go_back_good_habits.setBackground(drawable_for_buttons);
                Drawable drawable_for_buttons_two = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons_two = DrawableCompat.wrap(drawable_for_buttons_two);
                DrawableCompat.setTint(drawable_for_buttons_two, Color.parseColor("#ffe119"));
                button_go_forward_good_habits.setBackground(drawable_for_buttons_two);
                button_to_add_good_habits_under_the_card_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffe119")));
            } else if (splitter[3].equals("purple")) {
                card_in_good_habits_habits.setCardBackgroundColor(Color.parseColor("#911eb4"));
                hbaits_divider_between_mood_in_habits.setBackgroundColor(Color.parseColor("#911eb4"));
                yes_i_did_good_habit_today_card.setTextColor(Color.parseColor("#911eb4"));
                no_i_did_good_habit_today_card.setTextColor(Color.parseColor("#911eb4"));
                Spannable spannable = new SpannableString("How was your mood today?");
                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#911eb4")), 13, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.round_more_vert_24).mutate();
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, Color.parseColor("#911eb4"));
                Drawable drawable_for_buttons = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons = DrawableCompat.wrap(drawable_for_buttons);
                DrawableCompat.setTint(drawable_for_buttons, Color.parseColor("#911eb4"));
                button_go_back_good_habits.setBackground(drawable_for_buttons);
                Drawable drawable_for_buttons_two = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons_two = DrawableCompat.wrap(drawable_for_buttons_two);
                DrawableCompat.setTint(drawable_for_buttons_two, Color.parseColor("#911eb4"));
                button_go_forward_good_habits.setBackground(drawable_for_buttons_two);
                button_to_add_good_habits_under_the_card_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#911eb4")));
            }
        }
    }

    private String read_the_habits() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString("good_history", "");
            if (shared != null) {
                String[] splitter = shared.split("max_divide");
                return splitter[which_habit_am_i_on - 1];
            }
        }
        return "";
    }

    private void determind_the_max_habits() {
        if (getView() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString("good_history", "");
            if (shared != null) {
                String[] split = shared.split("max_divide");
                max_habits = split.length;
            } else {
                max_habits = 1;
            }
        } else {
            max_habits = 1;
        }
    }

    private void update_the_arrows() {
        if (getView() != null) {
            Button button_go_back_good_habits = getView().findViewById(R.id.button_go_back_good_habits);
            View go_back_in_good_habits_habits = getView().findViewById(R.id.go_back_in_good_habits_habits);
            Button button_go_forward_good_habits = getView().findViewById(R.id.button_go_forward_good_habits);
            View go_forward_in_good_habits_habits = getView().findViewById(R.id.go_forward_in_good_habits_habits);
            if (which_habit_am_i_on == 1 && max_habits != 1) {
                button_go_back_good_habits.setVisibility(View.INVISIBLE);
                go_back_in_good_habits_habits.setVisibility(View.INVISIBLE);
                button_go_forward_good_habits.setVisibility(View.VISIBLE);
                go_forward_in_good_habits_habits.setVisibility(View.VISIBLE);
            } else if (which_habit_am_i_on == 1) {
                button_go_back_good_habits.setVisibility(View.INVISIBLE);
                go_back_in_good_habits_habits.setVisibility(View.INVISIBLE);
                button_go_forward_good_habits.setVisibility(View.INVISIBLE);
                go_forward_in_good_habits_habits.setVisibility(View.INVISIBLE);
            } else if (which_habit_am_i_on != max_habits) {
                button_go_back_good_habits.setVisibility(View.VISIBLE);
                go_back_in_good_habits_habits.setVisibility(View.VISIBLE);
                button_go_forward_good_habits.setVisibility(View.VISIBLE);
                go_forward_in_good_habits_habits.setVisibility(View.VISIBLE);
            } else {
                button_go_back_good_habits.setVisibility(View.VISIBLE);
                go_back_in_good_habits_habits.setVisibility(View.VISIBLE);
                button_go_forward_good_habits.setVisibility(View.INVISIBLE);
                go_forward_in_good_habits_habits.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void back_button_listen() {
        if (getView() != null) {
            final Button button_go_back_good_habits = getView().findViewById(R.id.button_go_back_good_habits);
            final Button button_go_forward_good_habits = getView().findViewById(R.id.button_go_forward_good_habits);
            final TextView text_saying_thanks_for_your_input = getView().findViewById(R.id.text_saying_thanks_for_your_input);
            button_go_back_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (which_habit_am_i_on != 1) {
                        which_habit_am_i_on = which_habit_am_i_on - 1;
                        set_up_the_colors();
                        fill_up_the_card();
                        update_the_arrows();
                        if (text_saying_thanks_for_your_input.getText().toString().equals("Today's input has been recorded")) {

                        } else {
                            span_the_text_behind_mood();
                        }
                        check_if_good_habits_input_has_been_recorded();
                        return_the_percent_for_card();
                        define_values_for_pre_card();
                        add_all_the_values_into_history_array_list();
                    }
                }
            });
        }
    }

    private void forward_button_listen() {
        if (getView() != null) {
            final Button button_go_back_good_habits = getView().findViewById(R.id.button_go_back_good_habits);
            final Button button_go_forward_good_habits = getView().findViewById(R.id.button_go_forward_good_habits);
            final TextView text_saying_thanks_for_your_input = getView().findViewById(R.id.text_saying_thanks_for_your_input);
            button_go_forward_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (which_habit_am_i_on != max_habits) {
                        which_habit_am_i_on = which_habit_am_i_on + 1;
                        set_up_the_colors();
                        fill_up_the_card();
                        update_the_arrows();
                        if (text_saying_thanks_for_your_input.getText().toString().equals("Today's input has been recorded")) {

                        } else {
                            span_the_text_behind_mood();
                        }
                        check_if_good_habits_input_has_been_recorded();
                        return_the_percent_for_card();
                        define_values_for_pre_card();
                        add_all_the_values_into_history_array_list();
                    }
                }
            });
        }
    }

    private void fill_up_the_card() {
        if (getView() != null) {
            TextView title_of_the_good_habit_in_card = getView().findViewById(R.id.title_of_the_good_habit_in_card);
            TextView question_of_good_habit_in_card = getView().findViewById(R.id.question_of_good_habit_in_card);
            View view_with_the_actual_icon_in_habits = getView().findViewById(R.id.view_with_the_actual_icon_in_habits);
            String habits = read_the_habits();
            String[] splitter = habits.split("small_split");
            title_of_the_good_habit_in_card.setText(first_letter_cap(splitter[0]));
            question_of_good_habit_in_card.setText(first_letter_cap(splitter[1]).replace("?", "").concat("?"));
            String color;
            if (splitter[3].equals("teal")) {
                color = "#607D8B";
            } else if (splitter[3].equals("red")) {
                color = "#e6194B";
            } else if (splitter[3].equals("green")) {
                color = "#3cb44b";
            } else if (splitter[3].equals("blue")) {
                color = "#4363d8";
            } else if (splitter[3].equals("orange")) {
                color = "#f58231";
            } else if (splitter[3].equals("brown")) {
                color = "#9A6324";
            } else if (splitter[3].equals("black")) {
                color = "#000000";
            } else if (splitter[3].equals("cyan")) {
                color = "#42d4f4";
            } else if (splitter[3].equals("lime")) {
                color = "#bfef45";
            } else if (splitter[3].equals("magenta")) {
                color = "#f032e6";
            } else if (splitter[3].equals("navy")) {
                color = "#000075";
            } else if (splitter[3].equals("pink")) {
                color = "#fabed4";
            } else if (splitter[3].equals("yellow")) {
                color = "#ffe119";
            } else if (splitter[3].equals("purple")) {
                color = "#911eb4";
            } else {
                color = "#607D8B";
            }
            if (splitter[4].equals("fitness")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_fitness_center_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.parseColor(color));
                view_with_the_actual_icon_in_habits.setBackground(icon);
            } else if (splitter[4].equals("finance")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_account_balance_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.parseColor(color));
                view_with_the_actual_icon_in_habits.setBackground(icon);
            } else if (splitter[4].equals("education")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_school_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.parseColor(color));
                view_with_the_actual_icon_in_habits.setBackground(icon);
            } else if (splitter[4].equals("reading")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_menu_book_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.parseColor(color));
                view_with_the_actual_icon_in_habits.setBackground(icon);
            } else if (splitter[4].equals("socializing")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_group_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.parseColor(color));
                view_with_the_actual_icon_in_habits.setBackground(icon);
            } else if (splitter[4].equals("eating healthy")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_local_dining_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.parseColor(color));
                view_with_the_actual_icon_in_habits.setBackground(icon);
            } else if (splitter[4].equals("mediation")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_self_improvement_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.parseColor(color));
                view_with_the_actual_icon_in_habits.setBackground(icon);
            } else if (splitter[4].equals("running/walking")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_directions_run_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.parseColor(color));
                view_with_the_actual_icon_in_habits.setBackground(icon);
            } else if (splitter[4].equals("sports")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_sports_tennis_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.parseColor(color));
                view_with_the_actual_icon_in_habits.setBackground(icon);
            } else if (splitter[4].equals("fasting")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_no_food_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.parseColor(color));
                view_with_the_actual_icon_in_habits.setBackground(icon);
            } else if (splitter[4].equals("outdoors")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_terrain_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.parseColor(color));
                view_with_the_actual_icon_in_habits.setBackground(icon);
            } else if (splitter[4].equals("sleeping healthy")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_airline_seat_individual_suite_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.parseColor(color));
                view_with_the_actual_icon_in_habits.setBackground(icon);
            } else if (splitter[4].equals("photography")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_photo_camera_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.parseColor(color));
                view_with_the_actual_icon_in_habits.setBackground(icon);
            } else if (splitter[4].equals("art")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_brush_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.parseColor(color));
                view_with_the_actual_icon_in_habits.setBackground(icon);
            } else if (splitter[4].equals("time")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_query_builder_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.parseColor(color));
                view_with_the_actual_icon_in_habits.setBackground(icon);
            } else if (splitter[4].equals("business")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_business_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.parseColor(color));
                view_with_the_actual_icon_in_habits.setBackground(icon);
            } else if (splitter[4].equals("other")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.happy_face_dark_green).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.parseColor(color));
                view_with_the_actual_icon_in_habits.setBackground(icon);
            }
        }
    }

    private String first_letter_cap(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    private void mood_buttons_listen() {
        if (getView() != null) {
            Button very_bad_mood_button_in_habits = getView().findViewById(R.id.very_bad_mood_button_in_habits);
            Button bad_mood_button_in_habits = getView().findViewById(R.id.bad_mood_button_in_habits);
            Button ok_mood_button_in_habits = getView().findViewById(R.id.ok_mood_button_in_habits);
            Button good_mood_button_in_habits = getView().findViewById(R.id.good_mood_button_in_habits);
            Button very_good_mood_button_in_habits = getView().findViewById(R.id.very_good_mood_button_in_habits);
            final TextView text_saying_thanks_for_your_input = getView().findViewById(R.id.text_saying_thanks_for_your_input);
            very_bad_mood_button_in_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    remove_the_old_input();
                    make_all_the_mood_buttons_invisible();
                    text_saying_thanks_for_your_input.setText("Hang tight, better days are coming!!");
                    span_the_text_behind_mood();
                    save_the_mood(1);
                }
            });
            bad_mood_button_in_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    remove_the_old_input();
                    make_all_the_mood_buttons_invisible();
                    text_saying_thanks_for_your_input.setText("Good days are loading!!");
                    span_the_text_behind_mood();
                    save_the_mood(2);
                }
            });
            ok_mood_button_in_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    remove_the_old_input();
                    make_all_the_mood_buttons_invisible();
                    text_saying_thanks_for_your_input.setText("It's going to be alright!!");
                    span_the_text_behind_mood();
                    save_the_mood(3);
                }
            });
            good_mood_button_in_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    remove_the_old_input();
                    make_all_the_mood_buttons_invisible();
                    text_saying_thanks_for_your_input.setText("Keep the good vibes up!!");
                    span_the_text_behind_mood();
                    save_the_mood(4);
                }
            });
            very_good_mood_button_in_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    remove_the_old_input();
                    make_all_the_mood_buttons_invisible();
                    text_saying_thanks_for_your_input.setText("Great to hear!!");
                    span_the_text_behind_mood();
                    save_the_mood(5);
                }
            });
        }
    }

    private void make_all_the_mood_buttons_invisible() {
        if (getView() != null) {
            Button very_bad_mood_button_in_habits = getView().findViewById(R.id.very_bad_mood_button_in_habits);
            Button bad_mood_button_in_habits = getView().findViewById(R.id.bad_mood_button_in_habits);
            Button ok_mood_button_in_habits = getView().findViewById(R.id.ok_mood_button_in_habits);
            Button good_mood_button_in_habits = getView().findViewById(R.id.good_mood_button_in_habits);
            Button very_good_mood_button_in_habits = getView().findViewById(R.id.very_good_mood_button_in_habits);
            View very_bad_mood_in_habits = getView().findViewById(R.id.very_bad_mood_in_habits);
            View bad_mood_in_habits = getView().findViewById(R.id.bad_mood_in_habits);
            View ok_mood_in_habits = getView().findViewById(R.id.ok_mood_in_habits);
            View good_mood_in_habits = getView().findViewById(R.id.good_mood_in_habits);
            View very_good_mood_in_habits = getView().findViewById(R.id.very_good_mood_in_habits);
            TextView text_saying_thanks_for_your_input = getView().findViewById(R.id.text_saying_thanks_for_your_input);
            very_bad_mood_button_in_habits.setVisibility(View.INVISIBLE);
            bad_mood_button_in_habits.setVisibility(View.INVISIBLE);
            ok_mood_button_in_habits.setVisibility(View.INVISIBLE);
            good_mood_button_in_habits.setVisibility(View.INVISIBLE);
            very_good_mood_button_in_habits.setVisibility(View.INVISIBLE);
            very_bad_mood_in_habits.setVisibility(View.INVISIBLE);
            bad_mood_in_habits.setVisibility(View.INVISIBLE);
            ok_mood_in_habits.setVisibility(View.INVISIBLE);
            good_mood_in_habits.setVisibility(View.INVISIBLE);
            very_good_mood_in_habits.setVisibility(View.INVISIBLE);
            text_saying_thanks_for_your_input.setVisibility(View.VISIBLE);
        }
    }

    private void span_the_text_behind_mood() {
        if (getView() != null) {
            TextView text_saying_thanks_for_your_input = getView().findViewById(R.id.text_saying_thanks_for_your_input);
            CardView card_in_good_habits_habits = getView().findViewById(R.id.card_in_good_habits_habits);
            if (text_saying_thanks_for_your_input.getText().toString().equals("Great to hear!!")) {
                Spannable spannable = new SpannableString("Great to hear!!");
                spannable.setSpan(new ForegroundColorSpan(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor()), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                text_saying_thanks_for_your_input.setText(spannable);
            } else if (text_saying_thanks_for_your_input.getText().toString().equals("Keep the good vibes up!!")) {
                Spannable spannable = new SpannableString("Keep the good vibes up!!");
                spannable.setSpan(new ForegroundColorSpan(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor()), 9, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                text_saying_thanks_for_your_input.setText(spannable);
            } else if (text_saying_thanks_for_your_input.getText().toString().equals("It's going to be alright!!")) {
                Spannable spannable = new SpannableString("It's going to be alright!!");
                spannable.setSpan(new ForegroundColorSpan(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor()), 16, 24, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                text_saying_thanks_for_your_input.setText(spannable);
            } else if (text_saying_thanks_for_your_input.getText().toString().equals("Good days are loading!!")) {
                Spannable spannable = new SpannableString("Good days are loading!!");
                spannable.setSpan(new ForegroundColorSpan(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor()), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                text_saying_thanks_for_your_input.setText(spannable);
            } else if (text_saying_thanks_for_your_input.getText().toString().equals("Hang tight, better days are coming!!")) {
                Spannable spannable = new SpannableString("Hang tight, better days are coming!!");
                spannable.setSpan(new ForegroundColorSpan(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor()), 12, 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                text_saying_thanks_for_your_input.setText(spannable);
            }
        }
    }

    private void save_the_mood(int mood) {
        if (getView() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mood", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString("mood_stats", "");
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            if (shared != null) {
                myEdit.putString("mood_stats", shared.concat(String.valueOf(mood)).concat("small_split").concat(String.valueOf(Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()))).concat("max_divide"));
                myEdit.commit();
            } else {
                myEdit.putString("mood_stats", "".concat(String.valueOf(mood)).concat("small_split").concat(String.valueOf(Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()))).concat("max_divide"));
                myEdit.commit();
            }

        }
    }

    private void remove_the_old_input() {
        if (getView() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mood", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString("mood_stats", "");
            if (shared != null && shared.contains(String.valueOf(Simplify_the_time.return_time_in_midnight(System.currentTimeMillis())))) {
                String[] split = shared.split("max_divide");
                String new_string = "";
                for (int i = 0; i < split.length; i++) {
                    if (!split[i].contains(String.valueOf(Simplify_the_time.return_time_in_midnight(System.currentTimeMillis())))) {
                        new_string = new_string.concat(split[i]).concat("max_divide");
                    }
                }
            }
        }
    }

    private void check_if_there_is_input_today() {
        if (getView() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mood", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString("mood_stats", "");
            Button very_bad_mood_button_in_habits = getView().findViewById(R.id.very_bad_mood_button_in_habits);
            Button bad_mood_button_in_habits = getView().findViewById(R.id.bad_mood_button_in_habits);
            Button ok_mood_button_in_habits = getView().findViewById(R.id.ok_mood_button_in_habits);
            Button good_mood_button_in_habits = getView().findViewById(R.id.good_mood_button_in_habits);
            Button very_good_mood_button_in_habits = getView().findViewById(R.id.very_good_mood_button_in_habits);
            View very_bad_mood_in_habits = getView().findViewById(R.id.very_bad_mood_in_habits);
            View bad_mood_in_habits = getView().findViewById(R.id.bad_mood_in_habits);
            View ok_mood_in_habits = getView().findViewById(R.id.ok_mood_in_habits);
            View good_mood_in_habits = getView().findViewById(R.id.good_mood_in_habits);
            View very_good_mood_in_habits = getView().findViewById(R.id.very_good_mood_in_habits);
            TextView text_saying_thanks_for_your_input = getView().findViewById(R.id.text_saying_thanks_for_your_input);
            if (shared != null && shared.contains(String.valueOf(Simplify_the_time.return_time_in_midnight(System.currentTimeMillis())))) {
                very_bad_mood_button_in_habits.setVisibility(View.INVISIBLE);
                bad_mood_button_in_habits.setVisibility(View.INVISIBLE);
                ok_mood_button_in_habits.setVisibility(View.INVISIBLE);
                good_mood_button_in_habits.setVisibility(View.INVISIBLE);
                very_good_mood_button_in_habits.setVisibility(View.INVISIBLE);
                very_bad_mood_in_habits.setVisibility(View.INVISIBLE);
                bad_mood_in_habits.setVisibility(View.INVISIBLE);
                ok_mood_in_habits.setVisibility(View.INVISIBLE);
                good_mood_in_habits.setVisibility(View.INVISIBLE);
                very_good_mood_in_habits.setVisibility(View.INVISIBLE);
                text_saying_thanks_for_your_input.setVisibility(View.VISIBLE);

            }
        }
    }

    private void add_button_habits_listen() {
        if (getView() != null) {
            Button button_to_add_good_habits_under_the_card_in_good_habits = getView().findViewById(R.id.button_to_add_good_habits_under_the_card_in_good_habits);
            final TextView text_saying_that_you_are_creating_a_new_habit_top = getView().findViewById(R.id.text_saying_that_you_are_creating_a_new_habit_top);
            button_to_add_good_habits_under_the_card_in_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (return_number_off_bad_habits() >= 2) {
                        Am_i_paid am_i_paid = new Am_i_paid(getContext());
                        if (am_i_paid.did_user_pay()) {
                            open_a_screen(1);
                            text_saying_that_you_are_creating_a_new_habit_top.setText("Create a new good habit!!");
                        } else {
                            Buy_premuim buy_premuim = new Buy_premuim("add more than 2 habits", true, "good habits");
                            habits_fragment old_fragment = (habits_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("habits");
                            if (old_fragment != null) {
                                getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
                            }
                        }
                    } else {
                        open_a_screen(1);
                        text_saying_that_you_are_creating_a_new_habit_top.setText("Create a new good habit!!");
                    }
                }
            });
        }
    }

    private void yes_and_no_button_listen_for_good_habits() {
        if (getView() != null) {
            final Button yes_i_did_good_habit_today_card = getView().findViewById(R.id.yes_i_did_good_habit_today_card);
            final Button no_i_did_good_habit_today_card = getView().findViewById(R.id.no_i_did_good_habit_today_card);
            final TextView text_saying_input_got_it_for_yes_or_no = getView().findViewById(R.id.text_saying_input_got_it_for_yes_or_no);
            yes_i_did_good_habit_today_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    yes_i_did_good_habit_today_card.setVisibility(View.INVISIBLE);
                    no_i_did_good_habit_today_card.setVisibility(View.INVISIBLE);
                    text_saying_input_got_it_for_yes_or_no.setVisibility(View.VISIBLE);
                    save_the_input_for_good_habit_input("yes", System.currentTimeMillis());
                    define_values_for_pre_card();
                    add_all_the_values_into_history_array_list();
                    return_the_percent_for_card();
                }
            });
            no_i_did_good_habit_today_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    yes_i_did_good_habit_today_card.setVisibility(View.INVISIBLE);
                    no_i_did_good_habit_today_card.setVisibility(View.INVISIBLE);
                    text_saying_input_got_it_for_yes_or_no.setVisibility(View.VISIBLE);
                    save_the_input_for_good_habit_input("no", System.currentTimeMillis());
                    define_values_for_pre_card();
                    add_all_the_values_into_history_array_list();
                    return_the_percent_for_card();
                }
            });
        }
    }

    private void save_the_input_for_good_habit_input(String yes_or_no, long milli_time) {
        if (getView() != null && getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits_save", Context.MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            TextView title_of_the_good_habit_in_card = getView().findViewById(R.id.title_of_the_good_habit_in_card);
            String old = sharedPreferences.getString(title_of_the_good_habit_in_card.getText().toString().toLowerCase(), "");
            String time_at_midnight = String.valueOf(Simplify_the_time.return_time_in_midnight(milli_time));
            String today_value = yes_or_no.concat("small_split").concat(time_at_midnight).concat("max_divide");
            if (old != null && !old.contains(time_at_midnight) && !old.equals("")) {
                String[] big_split = old.split("max_divide");
                String save_me = "";
                for (int i = big_split.length - 1; i >= 0; i--) {
                    long time = Long.parseLong(big_split[i].replace("yes", "").replace("no", "").replace("small_split", ""));
                    if (Simplify_the_time.return_time_in_midnight(milli_time) > Simplify_the_time.return_time_in_midnight(time)) {
                        String[] split_to_two = old.split(big_split[i].concat("max_divide"), -1);
                        save_me = split_to_two[0].concat(big_split[i].concat("max_divide")).concat(today_value).concat(split_to_two[1]);
                        break;
                    }
                }
                if (save_me.equals("")) {
                    save_me = today_value.concat(old);
                }
                myEdit.putString(title_of_the_good_habit_in_card.getText().toString().toLowerCase(), save_me);
            } else if (old != null && old.contains(time_at_midnight)) {
                String[] split = old.split("max_divide");
                String new_string = "";
                for (int i = 0; i < split.length; i++) {
                    if (!split[i].contains(time_at_midnight)) {
                        new_string = new_string.concat(split[i]).concat("max_divide");
                    } else {
                        new_string = new_string.concat(yes_or_no.concat("small_split").concat(time_at_midnight)).concat("max_divide");
                    }
                }
                myEdit.putString(title_of_the_good_habit_in_card.getText().toString().toLowerCase(), new_string);
            } else if (old == null || old.equals("")) {
                myEdit.putString(title_of_the_good_habit_in_card.getText().toString().toLowerCase(), today_value);
            }
            myEdit.commit();
        }
    }

    private void check_if_good_habits_input_has_been_recorded() {
        if (getView() != null && getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits_save", Context.MODE_PRIVATE);
            TextView title_of_the_good_habit_in_card = getView().findViewById(R.id.title_of_the_good_habit_in_card);
            Button yes_i_did_good_habit_today_card = getView().findViewById(R.id.yes_i_did_good_habit_today_card);
            Button no_i_did_good_habit_today_card = getView().findViewById(R.id.no_i_did_good_habit_today_card);
            TextView text_saying_input_got_it_for_yes_or_no = getView().findViewById(R.id.text_saying_input_got_it_for_yes_or_no);
            String old = sharedPreferences.getString(title_of_the_good_habit_in_card.getText().toString().toLowerCase(), "");
            if (old != null && old.contains(String.valueOf(Simplify_the_time.return_time_in_midnight(System.currentTimeMillis())))) {
                yes_i_did_good_habit_today_card.setVisibility(View.INVISIBLE);
                no_i_did_good_habit_today_card.setVisibility(View.INVISIBLE);
                text_saying_input_got_it_for_yes_or_no.setVisibility(View.VISIBLE);
            } else {
                yes_i_did_good_habit_today_card.setVisibility(View.VISIBLE);
                no_i_did_good_habit_today_card.setVisibility(View.VISIBLE);
                text_saying_input_got_it_for_yes_or_no.setVisibility(View.INVISIBLE);
            }
        }
    }

    private String retuen_all_the_titles() {
        if (getView() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString("good_history", "");
            if (shared != null) {
                if (shared.contains("max_divide")) {
                    String[] split = shared.split("max_divide");
                    String return_me = "";
                    for (int i = 0; i < split.length; i++) {
                        String[] title = split[i].split("small_split");
                        return_me = return_me.concat(title[0]);
                    }
                    return return_me;
                } else {
                    return shared;
                }
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void set_up_the_card_button_listen() {
        if (getView() != null && getContext() != null) {
            final CardView card_in_good_habits_habits = getView().findViewById(R.id.card_in_good_habits_habits);
            final Button button_go_back_good_habits = getView().findViewById(R.id.button_go_back_good_habits);
            final Button button_go_forward_good_habits = getView().findViewById(R.id.button_go_forward_good_habits);
            final TextView text_saying_thanks_for_your_input = getView().findViewById(R.id.text_saying_thanks_for_your_input);
            card_in_good_habits_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (which_habit_am_i_on <= 2) {
                        open_a_screen(3);
                    } else {
                        Am_i_paid am_i_paid = new Am_i_paid(getContext());
                        if (am_i_paid.did_user_pay()) {
                            open_a_screen(3);
                        } else {
                            Buy_premuim buy_premuim = new Buy_premuim("use more than 2 habits", true, "good habits");
                            habits_fragment old_fragment = (habits_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("habits");
                            if (old_fragment != null) {
                                getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
                            }
                        }
                    }
                }
            });
            card_in_good_habits_habits.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
                @Override
                public boolean onSwipeLeft() {
                    if (which_habit_am_i_on != max_habits) {
                        which_habit_am_i_on = which_habit_am_i_on + 1;
                        button_go_back_good_habits.setBackgroundResource(0);
                        button_go_forward_good_habits.setBackgroundResource(0);
                        button_go_back_good_habits.setBackgroundResource(R.drawable.ripple_button_fav_any_circle);
                        button_go_forward_good_habits.setBackgroundResource(R.drawable.ripple_button_fav_any_circle);
                        set_up_the_colors();
                        fill_up_the_card();
                        update_the_arrows();
                        if (text_saying_thanks_for_your_input.getText().toString().equals("Today's input has been recorded")) {

                        } else {
                            span_the_text_behind_mood();
                        }
                        check_if_good_habits_input_has_been_recorded();
                        return_the_percent_for_card();
                        define_values_for_pre_card();
                        add_all_the_values_into_history_array_list();
                    }
                    card_in_good_habits_habits.setPressed(false);
                    return true;
                }

                public boolean onSwipeRight() {
                    if (which_habit_am_i_on != 1) {
                        which_habit_am_i_on = which_habit_am_i_on - 1;
                        button_go_back_good_habits.setBackgroundResource(0);
                        button_go_forward_good_habits.setBackgroundResource(0);
                        button_go_back_good_habits.setBackgroundResource(R.drawable.ripple_button_fav_any_circle);
                        button_go_forward_good_habits.setBackgroundResource(R.drawable.ripple_button_fav_any_circle);
                        set_up_the_colors();
                        fill_up_the_card();
                        update_the_arrows();
                        if (text_saying_thanks_for_your_input.getText().toString().equals("Today's input has been recorded")) {

                        } else {
                            span_the_text_behind_mood();
                        }
                        check_if_good_habits_input_has_been_recorded();
                        return_the_percent_for_card();
                        define_values_for_pre_card();
                        add_all_the_values_into_history_array_list();
                    }
                    card_in_good_habits_habits.setPressed(false);
                    return true;
                }

            });
        }
    }

    private void set_button_yes_or_no() {
        if (getView() != null) {
            TextView title_of_the_good_habit_in_card = getView().findViewById(R.id.title_of_the_good_habit_in_card);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits_save", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString(title_of_the_good_habit_in_card.getText().toString().toLowerCase(), "");
            Button button_to_set_as_yes_for_good_habit_in_good = getView().findViewById(R.id.button_to_set_as_yes_for_good_habit_in_good);
            Button button_to_set_as_no_for_good_habit_in_good = getView().findViewById(R.id.button_to_set_as_no_for_good_habit_in_good);
            CardView card_in_good_habits_habits = getView().findViewById(R.id.card_in_good_habits_habits);
            if (shared != null && shared.contains(String.valueOf(Simplify_the_time.return_time_in_midnight(System.currentTimeMillis())))) {
                String[] big_split = shared.split("max_divide");
                for (int i = 0; i < big_split.length; i++) {
                    if (big_split[i].contains(String.valueOf(Simplify_the_time.return_time_in_midnight(System.currentTimeMillis())))) {
                        if (big_split[i].contains("yes")) {
                            button_to_set_as_yes_for_good_habit_in_good.setBackgroundTintList(ColorStateList.valueOf(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor()));
                            button_to_set_as_yes_for_good_habit_in_good.setTextColor(Color.WHITE);
                            button_to_set_as_yes_for_good_habit_in_good.setText(button_to_set_as_yes_for_good_habit_in_good.getText().toString().concat(" ✓"));
                        } else if (big_split[i].contains("no")) {
                            button_to_set_as_no_for_good_habit_in_good.setBackgroundTintList(ColorStateList.valueOf(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor()));
                            button_to_set_as_no_for_good_habit_in_good.setTextColor(Color.WHITE);
                            button_to_set_as_no_for_good_habit_in_good.setText(button_to_set_as_no_for_good_habit_in_good.getText().toString().concat(" ✓"));
                        }
                    }
                }
            }
        }
    }

    private void set_the_text_for_in_card() {
        if (getView() != null) {
            TextView did_you_do_the_habit_in_the_good_view = getView().findViewById(R.id.did_you_do_the_habit_in_the_good_view);
            TextView question_of_good_habit_in_card = getView().findViewById(R.id.question_of_good_habit_in_card);
            TextView title_of_the_good_habit_in_card = getView().findViewById(R.id.title_of_the_good_habit_in_card);
            TextView text_saying_the_progress_in_good_habits = getView().findViewById(R.id.text_saying_the_progress_in_good_habits);
            TextView text_saying_the_real_progress_in_good_habits = getView().findViewById(R.id.text_saying_the_real_progress_in_good_habits);
            ProgressBar progress_bar_showing_good_habit_progress_in_good_habits = getView().findViewById(R.id.progress_bar_showing_good_habit_progress_in_good_habits);
            did_you_do_the_habit_in_the_good_view.setText(question_of_good_habit_in_card.getText().toString());
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits_save", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString(title_of_the_good_habit_in_card.getText().toString().toLowerCase(), "");
            String[] habits_split = read_the_habits().split("small_split");
            int goal = Integer.parseInt(habits_split[2]);
            int number_done = shared.split("yes").length - 1;
            int percent = ((number_done * 100) / goal);
            text_saying_the_progress_in_good_habits.setText(String.valueOf(number_done).concat(" / ").concat(String.valueOf(goal)).concat(" days"));
            if (percent >= 100) {
                text_saying_the_real_progress_in_good_habits.setText("100%!!");
            } else {
                text_saying_the_real_progress_in_good_habits.setText(String.valueOf(percent).concat("%"));
            }
            progress_bar_showing_good_habit_progress_in_good_habits.setProgress(percent);
        }
    }

    private void color_the_views_on_click_of_card() {
        if (getView() != null && getContext() != null) {
            CardView card_in_good_habits_habits = getView().findViewById(R.id.card_in_good_habits_habits);
            View view_at_the_top_action_bar_habits = getView().findViewById(R.id.view_at_the_top_action_bar_habits);
            View view_under_top_action_bar_half_color = getView().findViewById(R.id.view_under_top_action_bar_half_color);
            TextView did_you_do_the_habit_in_the_good_view = getView().findViewById(R.id.did_you_do_the_habit_in_the_good_view);
            Button back_button_in_view_information_good_habits = getView().findViewById(R.id.back_button_in_view_information_good_habits);
            TextView text_title_of_the_goal_card_in_good_habits = getView().findViewById(R.id.text_title_of_the_goal_card_in_good_habits);
            TextView text_saying_the_progress_in_good_habits = getView().findViewById(R.id.text_saying_the_progress_in_good_habits);
            ProgressBar progress_bar_showing_good_habit_progress_in_good_habits = getView().findViewById(R.id.progress_bar_showing_good_habit_progress_in_good_habits);
            TextView text_saying_the_real_progress_in_good_habits = getView().findViewById(R.id.text_saying_the_real_progress_in_good_habits);
            Button button_to_share_goal_progress_good_habits = getView().findViewById(R.id.button_to_share_goal_progress_good_habits);
            TextView title_of_the_text_of_the_streak_card = getView().findViewById(R.id.title_of_the_text_of_the_streak_card);
            TextView current_streak_text_in_card = getView().findViewById(R.id.current_streak_text_in_card);
            TextView actual_current_in_text_number_in_good_habits = getView().findViewById(R.id.actual_current_in_text_number_in_good_habits);
            Button button_to_share_streak_in_good_habits = getView().findViewById(R.id.button_to_share_streak_in_good_habits);
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            View backward_button_over_for_good_habits = getView().findViewById(R.id.backward_button_over_for_good_habits);
            View forward_button_over_for_good_habits = getView().findViewById(R.id.forward_button_over_for_good_habits);
            Button button_too_share_calender_in_good_habits = getView().findViewById(R.id.button_too_share_calender_in_good_habits);
            Button button_to_share_bar_chart = getView().findViewById(R.id.button_to_share_bar_chart);
            Button button_to_share_pie_chart = getView().findViewById(R.id.button_to_share_pie_chart);
            Button button_to_share_line_chart_of_various_streaks = getView().findViewById(R.id.button_to_share_line_chart_of_various_streaks);
            Button three_dots_button_in_top_in_habits = getView().findViewById(R.id.three_dots_button_in_top_in_habits);
            Button sahre_button_for_the_foour_values_in_good_habits = getView().findViewById(R.id.sahre_button_for_the_foour_values_in_good_habits);
            View line_after_all_time = getView().findViewById(R.id.line_after_all_time);
            View line_between_year_and_all_time_good_habtis = getView().findViewById(R.id.line_between_year_and_all_time_good_habtis);
            View line_between_month_and_year_good_habtis = getView().findViewById(R.id.line_between_month_and_year_good_habtis);
            View line_between_week_and_month_good_habtis = getView().findViewById(R.id.line_between_week_and_month_good_habtis);
            Button share_button_to_share_the_whole_year_in_the_good_habits = getView().findViewById(R.id.share_button_to_share_the_whole_year_in_the_good_habits);
            ConstraintLayout layou_to_show_screen_shot_in_good_habits = getView().findViewById(R.id.layou_to_show_screen_shot_in_good_habits);


            int color = card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor();
            view_at_the_top_action_bar_habits.setBackgroundColor(color);
            view_under_top_action_bar_half_color.setBackgroundColor(ColorUtils.blendARGB(color, Color.WHITE, 0.5F));
            did_you_do_the_habit_in_the_good_view.setTextColor(color);
            Drawable drawable_for_buttons_two = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
            drawable_for_buttons_two = DrawableCompat.wrap(drawable_for_buttons_two);
            DrawableCompat.setTint(drawable_for_buttons_two, color);
            back_button_in_view_information_good_habits.setBackground(drawable_for_buttons_two);
            Drawable drawable_for_buttons_three = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
            drawable_for_buttons_three = DrawableCompat.wrap(drawable_for_buttons_three);
            DrawableCompat.setTint(drawable_for_buttons_three, color);
            three_dots_button_in_top_in_habits.setBackground(drawable_for_buttons_three);
            /*text_title_of_the_goal_card_in_good_habits.setTextColor(color);
            text_saying_the_progress_in_good_habits.setTextColor(color);
            text_saying_the_real_progress_in_good_habits.setTextColor(color);
            button_to_share_goal_progress_good_habits.setTextColor(color);

            LayerDrawable progressBarDrawable = (LayerDrawable) progress_bar_showing_good_habit_progress_in_good_habits.getProgressDrawable();
            Drawable backgroundDrawable = progressBarDrawable.getDrawable(0);
            Drawable progressDrawable = progressBarDrawable.getDrawable(1);

            backgroundDrawable.setTint(Color.parseColor("#eaeceb"));
            progressDrawable.setTint(color);

            /*title_of_the_text_of_the_streak_card.setTextColor(color);
            //current_streak_text_in_card.setTextColor(color);
            actual_current_in_text_number_in_good_habits.setTextColor(color);
            button_to_share_streak_in_good_habits.setTextColor(color);
            {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_arrow_back_ios_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.BLACK);
                backward_button_over_for_good_habits.setBackground(icon);
            }
            {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_arrow_forward_ios_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.BLACK);
                forward_button_over_for_good_habits.setBackground(icon);
            }
            button_too_share_calender_in_good_habits.setTextColor(color);
            button_to_share_bar_chart.setTextColor(color);
            button_to_share_pie_chart.setTextColor(color);
            button_to_share_line_chart_of_various_streaks.setTextColor(color);
            sahre_button_for_the_foour_values_in_good_habits.setTextColor(color);
            line_after_all_time.setBackgroundColor(color);
            line_between_year_and_all_time_good_habtis.setBackgroundColor(color);
            line_between_month_and_year_good_habtis.setBackgroundColor(color);
            line_between_week_and_month_good_habtis.setBackgroundColor(color);
            share_button_to_share_the_whole_year_in_the_good_habits.setTextColor(color);
            layou_to_show_screen_shot_in_good_habits.setBackgroundColor(ColorUtils.blendARGB(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor(), Color.WHITE, 0.9F));
        }
    }

    private void yes_and_no_button_listen_in_good_habits_view() {
        if (getView() != null) {
            final CardView card_in_good_habits_habits = getView().findViewById(R.id.card_in_good_habits_habits);
            final Button button_to_set_as_yes_for_good_habit_in_good = getView().findViewById(R.id.button_to_set_as_yes_for_good_habit_in_good);
            final Button button_to_set_as_no_for_good_habit_in_good = getView().findViewById(R.id.button_to_set_as_no_for_good_habit_in_good);
            button_to_set_as_yes_for_good_habit_in_good.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (button_to_set_as_yes_for_good_habit_in_good.getCurrentTextColor() != Color.WHITE) {
                        save_the_input_for_good_habit_input("yes", System.currentTimeMillis());
                        button_to_set_as_yes_for_good_habit_in_good.setBackgroundTintList(ColorStateList.valueOf(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor()));
                        button_to_set_as_yes_for_good_habit_in_good.setTextColor(Color.WHITE);
                        button_to_set_as_yes_for_good_habit_in_good.setText(button_to_set_as_yes_for_good_habit_in_good.getText().toString().concat(" ✓"));
                        button_to_set_as_no_for_good_habit_in_good.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                        button_to_set_as_no_for_good_habit_in_good.setTextColor(Color.BLACK);
                        button_to_set_as_no_for_good_habit_in_good.setText(button_to_set_as_no_for_good_habit_in_good.getText().toString().replace(" ✓", ""));
                        define_values_for_pre_card();
                        add_all_the_values_into_history_array_list();
                        set_the_text_for_in_card();
                        set_the_streak();
                        read_the_best_and_average_streak();
                        color_the_calender();
                        set_up_day_of_week_bar_chart();
                        clear_all_the_unders();
                        divide_it_into_weeks();
                        clear_the_middle();
                        make_the_middle_come_again();
                        draw_pie_chart();
                        line_chart_for_streak.fitScreen();
                        set_up_the_various_streak_chart();
                        setup_the_four_information_card();
                        set_the_leap_year();
                        put_values_into_year_in_good_habits();
                    }
                }
            });
            button_to_set_as_no_for_good_habit_in_good.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (button_to_set_as_no_for_good_habit_in_good.getCurrentTextColor() != Color.WHITE) {
                        save_the_input_for_good_habit_input("no", System.currentTimeMillis());
                        button_to_set_as_no_for_good_habit_in_good.setBackgroundTintList(ColorStateList.valueOf(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor()));
                        button_to_set_as_no_for_good_habit_in_good.setTextColor(Color.WHITE);
                        button_to_set_as_no_for_good_habit_in_good.setText(button_to_set_as_no_for_good_habit_in_good.getText().toString().concat(" ✓"));
                        button_to_set_as_yes_for_good_habit_in_good.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                        button_to_set_as_yes_for_good_habit_in_good.setTextColor(Color.BLACK);
                        button_to_set_as_yes_for_good_habit_in_good.setText(button_to_set_as_yes_for_good_habit_in_good.getText().toString().replace(" ✓", ""));
                        define_values_for_pre_card();
                        add_all_the_values_into_history_array_list();
                        set_the_text_for_in_card();
                        set_the_streak();
                        read_the_best_and_average_streak();
                        color_the_calender();
                        set_up_day_of_week_bar_chart();
                        clear_all_the_unders();
                        divide_it_into_weeks();
                        clear_the_middle();
                        make_the_middle_come_again();
                        draw_pie_chart();
                        line_chart_for_streak.fitScreen();
                        set_up_the_various_streak_chart();
                        show_snack_bar(System.currentTimeMillis());
                        setup_the_four_information_card();
                        set_the_leap_year();
                        put_values_into_year_in_good_habits();
                    }
                }
            });
        }
    }

    private void back_in_good_habits_information_screen() {
        if (getView() != null) {
            Button back_button_in_view_information_good_habits = getView().findViewById(R.id.back_button_in_view_information_good_habits);
            back_button_in_view_information_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_a_screen(2);
                    restart_third_screen_good_information();
                }
            });
        }
    }

    public void restart_third_screen_good_information() {
        if (getView() != null) {
            Button button_to_set_as_yes_for_good_habit_in_good = getView().findViewById(R.id.button_to_set_as_yes_for_good_habit_in_good);
            Button button_to_set_as_no_for_good_habit_in_good = getView().findViewById(R.id.button_to_set_as_no_for_good_habit_in_good);
            Button button_saying_yes_under_calender_in_good_habits = getView().findViewById(R.id.button_saying_yes_under_calender_in_good_habits);
            Button button_saying_no_under_calender_in_good_habits = getView().findViewById(R.id.button_saying_no_under_calender_in_good_habits);
            ScrollView scroll_view_in_good_habits_under_action_bar = getView().findViewById(R.id.scroll_view_in_good_habits_under_action_bar);
            Button button_shadow_for_the_back_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.button_shadow_for_the_back_for_good_habits_for_the_full_year_view);
            Button button_shadow_for_the_front_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.button_shadow_for_the_front_for_good_habits_for_the_full_year_view);
            View forward_button_over_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.forward_button_over_for_good_habits_for_the_full_year_view);
            View backward_button_over_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.backward_button_over_for_good_habits_for_the_full_year_view);
            View good_habits_layout_circle_feb29 = getView().findViewById(R.id.good_habits_layout_circle_feb29);
            button_to_set_as_yes_for_good_habit_in_good.setText("Yes");
            button_to_set_as_no_for_good_habit_in_good.setText("No");
            button_to_set_as_no_for_good_habit_in_good.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            button_to_set_as_no_for_good_habit_in_good.setTextColor(Color.BLACK);
            button_to_set_as_no_for_good_habit_in_good.setText(button_to_set_as_no_for_good_habit_in_good.getText().toString().replace(" ✓", ""));
            button_to_set_as_yes_for_good_habit_in_good.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            button_to_set_as_yes_for_good_habit_in_good.setTextColor(Color.BLACK);
            button_to_set_as_yes_for_good_habit_in_good.setText(button_to_set_as_yes_for_good_habit_in_good.getText().toString().replace(" ✓", ""));
            button_saying_yes_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            button_saying_yes_under_calender_in_good_habits.setTextColor(Color.BLACK);
            button_saying_yes_under_calender_in_good_habits.setText(button_saying_yes_under_calender_in_good_habits.getText().toString().replace(" ✓", ""));
            button_saying_no_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            button_saying_no_under_calender_in_good_habits.setTextColor(Color.BLACK);
            button_saying_no_under_calender_in_good_habits.setText(button_saying_no_under_calender_in_good_habits.getText().toString().replace(" ✓", ""));
            clear_the_color_from_the_keyboard();
            button_saying_yes_under_calender_in_good_habits.setVisibility(View.GONE);
            button_saying_no_under_calender_in_good_habits.setVisibility(View.GONE);
            clear_all_the_text_from_calender();
            scroll_view_in_good_habits_under_action_bar.fullScroll(ScrollView.FOCUS_UP);
            clear_all_the_unders();
            array_history = "";
            line_chart_for_streak.fitScreen();
            set_all_buttons_to_visible();
            restart_all_the_year_values();
            button_shadow_for_the_back_for_good_habits_for_the_full_year_view.setVisibility(View.INVISIBLE);
            button_shadow_for_the_front_for_good_habits_for_the_full_year_view.setVisibility(View.INVISIBLE);
            forward_button_over_for_good_habits_for_the_full_year_view.setVisibility(View.INVISIBLE);
            backward_button_over_for_good_habits_for_the_full_year_view.setVisibility(View.INVISIBLE);
            good_habits_layout_circle_feb29.setVisibility(View.VISIBLE);
        }
    }

    private void choose_habit_frequency_button_listener() {
        if (getView() != null) {
            final Button button_saying_every_day_about_the_habit = getView().findViewById(R.id.button_saying_every_day_about_the_habit);
            final Button button_frequent_in_starting_a_good_habit = getView().findViewById(R.id.button_frequent_in_starting_a_good_habit);
            final Button button_saying_days_of_week_about_habit = getView().findViewById(R.id.button_saying_days_of_week_about_habit);
            final ConstraintLayout layout_that_contains_frequency_for_habits = getView().findViewById(R.id.layout_that_contains_frequency_for_habits);
            final EditText enter_number_of_days_for_habits = getView().findViewById(R.id.enter_number_of_days_for_habits);
            final TextView text_view_saying_this_is_the_frequency_in_habit = getView().findViewById(R.id.text_view_saying_this_is_the_frequency_in_habit);
            final TextView text_saying_which_days_of_the_week_are_supposed_for_the_habit = getView().findViewById(R.id.text_saying_which_days_of_the_week_are_supposed_for_the_habit);
            button_saying_every_day_about_the_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!button_saying_every_day_about_the_habit.getText().toString().contains("✓")) {
                        button_saying_every_day_about_the_habit.setTextColor(Color.WHITE);
                        button_frequent_in_starting_a_good_habit.setTextColor(Color.BLACK);
                        button_saying_days_of_week_about_habit.setTextColor(Color.BLACK);
                        button_saying_every_day_about_the_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
                        button_frequent_in_starting_a_good_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                        button_saying_days_of_week_about_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                        button_saying_every_day_about_the_habit.setText(button_saying_every_day_about_the_habit.getText().toString().concat(" ✓"));
                        button_frequent_in_starting_a_good_habit.setText(button_frequent_in_starting_a_good_habit.getText().toString().replace(" ✓", ""));
                        button_saying_days_of_week_about_habit.setText(button_saying_days_of_week_about_habit.getText().toString().replace(" ✓", ""));
                        layout_that_contains_frequency_for_habits.setVisibility(View.VISIBLE);
                        enter_number_of_days_for_habits.setText("1");
                        enter_number_of_days_for_habits.setSelection(enter_number_of_days_for_habits.getText().toString().length());
                        text_view_saying_this_is_the_frequency_in_habit.setVisibility(View.VISIBLE);
                        text_saying_which_days_of_the_week_are_supposed_for_the_habit.setVisibility(View.INVISIBLE);
                    }
                }
            });
            button_frequent_in_starting_a_good_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!button_frequent_in_starting_a_good_habit.getText().toString().contains("✓")) {
                        button_saying_every_day_about_the_habit.setTextColor(Color.BLACK);
                        button_frequent_in_starting_a_good_habit.setTextColor(Color.WHITE);
                        button_saying_days_of_week_about_habit.setTextColor(Color.BLACK);
                        button_saying_every_day_about_the_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                        button_frequent_in_starting_a_good_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
                        button_saying_days_of_week_about_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                        button_frequent_in_starting_a_good_habit.setText(button_frequent_in_starting_a_good_habit.getText().toString().concat(" ✓"));
                        button_saying_every_day_about_the_habit.setText(button_saying_every_day_about_the_habit.getText().toString().replace(" ✓", ""));
                        button_saying_days_of_week_about_habit.setText(button_saying_days_of_week_about_habit.getText().toString().replace(" ✓", ""));
                        layout_that_contains_frequency_for_habits.setVisibility(View.VISIBLE);
                        enter_number_of_days_for_habits.setText("");
                        text_view_saying_this_is_the_frequency_in_habit.setVisibility(View.VISIBLE);
                        text_saying_which_days_of_the_week_are_supposed_for_the_habit.setVisibility(View.INVISIBLE);
                    }
                }
            });
            button_saying_days_of_week_about_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getView() != null) {
                        /*button_saying_every_day_about_the_habit.setTextColor(Color.BLACK);
                        button_frequent_in_starting_a_good_habit.setTextColor(Color.BLACK);
                        button_saying_days_of_week_about_habit.setTextColor(Color.WHITE);
                        button_saying_every_day_about_the_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                        button_frequent_in_starting_a_good_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                        button_saying_days_of_week_about_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
                        Dialog_to_set_good_habit_frequency dialog_to_set_good_habit_frequency = new Dialog_to_set_good_habit_frequency();
                        dialog_to_set_good_habit_frequency.setTargetFragment(habits_fragment.this, 1010);
                        dialog_to_set_good_habit_frequency.show(getActivity().getSupportFragmentManager(), "tag");
                    }
                }
            });
        }
    }

    private void update_the_frequency(String frequency) {
        if (getView() != null) {
            Button button_saying_every_day_about_the_habit = getView().findViewById(R.id.button_saying_every_day_about_the_habit);
            Button button_frequent_in_starting_a_good_habit = getView().findViewById(R.id.button_frequent_in_starting_a_good_habit);
            Button button_saying_days_of_week_about_habit = getView().findViewById(R.id.button_saying_days_of_week_about_habit);
            ConstraintLayout layout_that_contains_frequency_for_habits = getView().findViewById(R.id.layout_that_contains_frequency_for_habits);
            TextView text_view_saying_this_is_the_frequency_in_habit = getView().findViewById(R.id.text_view_saying_this_is_the_frequency_in_habit);
            TextView text_saying_which_days_of_the_week_are_supposed_for_the_habit = getView().findViewById(R.id.text_saying_which_days_of_the_week_are_supposed_for_the_habit);
            button_saying_every_day_about_the_habit.setTextColor(Color.BLACK);
            button_frequent_in_starting_a_good_habit.setTextColor(Color.BLACK);
            button_saying_days_of_week_about_habit.setTextColor(Color.WHITE);
            button_saying_every_day_about_the_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            button_frequent_in_starting_a_good_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            button_saying_days_of_week_about_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            button_frequent_in_starting_a_good_habit.setText(button_frequent_in_starting_a_good_habit.getText().toString().replace(" ✓", ""));
            button_saying_every_day_about_the_habit.setText(button_saying_every_day_about_the_habit.getText().toString().replace(" ✓", ""));
            button_saying_days_of_week_about_habit.setText(button_saying_days_of_week_about_habit.getText().toString().replace(" ✓", "").concat(" ✓"));
            layout_that_contains_frequency_for_habits.setVisibility(View.INVISIBLE);
            text_view_saying_this_is_the_frequency_in_habit.setVisibility(View.INVISIBLE);
            text_saying_which_days_of_the_week_are_supposed_for_the_habit.setVisibility(View.VISIBLE);
            String set_habit_frequency = "";
            if (frequency.contains("Mo")) {
                set_habit_frequency = "Mo., ";
            }
            if (frequency.contains("Tu")) {
                set_habit_frequency = set_habit_frequency.concat("Tu., ");
            }
            if (frequency.contains("We")) {
                set_habit_frequency = set_habit_frequency.concat("We., ");
            }
            if (frequency.contains("Th")) {
                set_habit_frequency = set_habit_frequency.concat("Th., ");
            }
            if (frequency.contains("Fr")) {
                set_habit_frequency = set_habit_frequency.concat("Fr., ");
            }
            if (frequency.contains("Sa")) {
                set_habit_frequency = set_habit_frequency.concat("Sa., ");
            }
            if (frequency.contains("Su")) {
                set_habit_frequency = set_habit_frequency.concat("Su., ");
            }
            set_habit_frequency = set_habit_frequency.substring(0, set_habit_frequency.length() - 2);
            text_saying_which_days_of_the_week_are_supposed_for_the_habit.setText("Days of week: ".concat(set_habit_frequency));
        }
    }

    private void define_values_for_pre_card() {
        if (getView() != null && getActivity() != null) {
            TextView title_of_the_good_habit_in_card = getView().findViewById(R.id.title_of_the_good_habit_in_card);
            String habits = read_the_habits();
            String[] split_the_habit = habits.split("small_split");
            frequency_faster = split_the_habit[5];
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits_save", Context.MODE_PRIVATE);
            history_faster = sharedPreferences.getString(title_of_the_good_habit_in_card.getText().toString().toLowerCase(), "");
            history_split_faster = history_faster.split("max_divide");
            start_date = Long.parseLong(split_the_habit[7]);
        }
    }

    /*private String read_the_good_habit_for_the_day(long milli) {
        if (getView() != null) {
            String time_at_midnight = String.valueOf(Simplify_the_time.return_time_in_midnight(milli));
            if (history_faster != null && history_faster.contains(time_at_midnight)) {
                for (int i = history_split_faster.length - 1; i >= 0; i--) {
                    if (history_split_faster[i].contains(time_at_midnight)) {
                        String[] split_the_smallest = history_split_faster[i].split("small_split");
                        return split_the_smallest[0];
                    }
                }
            }
            if (frequency_faster.contains("Mo") || frequency_faster.contains("Tu") || frequency_faster.contains("We") || frequency_faster.contains("Th") || frequency_faster.contains("Fr") || frequency_faster.contains("Sa") || frequency_faster.contains("Su")) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(milli);
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY && frequency_faster.contains("Mo")) {
                    return "no input - did not do it";
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY && frequency_faster.contains("Tu")) {
                    return "no input - did not do it";
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY && frequency_faster.contains("We")) {
                    return "no input - did not do it";
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY && frequency_faster.contains("Th")) {
                    return "no input - did not do it";
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && frequency_faster.contains("Fr")) {
                    return "no input - did not do it";
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY && frequency_faster.contains("Sa")) {
                    return "no input - did not do it";
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && frequency_faster.contains("Su")) {
                    return "no input - did not do it";
                }
                return "no input - yes not for today";
            }
            if (history_faster != null && !history_faster.equals("")) {
                long max_time = Long.parseLong(history_split_faster[0].replace("yes", "").replace("no", "").replace("small_split", ""));
                for (int i = history_split_faster.length - 1; i >= 0; i--) {
                    long split_small = Long.parseLong(history_split_faster[i].replace("yes", "").replace("no", "").replace("small_split", ""));
                    if (split_small > max_time) {
                        max_time = split_small;
                    }
                }
                long time_difference_milli = milli - max_time;
                if (time_difference_milli > 0) {
                    int days = (int) (time_difference_milli / 86400000);
                    if (days > Integer.parseInt(frequency_faster)) {
                        return "no input - did not do it";
                    } else {
                        return "no input - yes not for today";
                    }
                }
            }
        }
        return "";
    }*/

    /*private String change_read_the_good_habit_for_the_day(long milli) {
        if (getView() != null) {
            String time_at_midnight = String.valueOf(Simplify_the_time.return_time_in_midnight(milli));
            if (history_faster != null && history_faster.contains(time_at_midnight)) {
                for (int i = history_split_faster.length - 1; i >= 0; i--) {
                    if (history_split_faster[i].contains(time_at_midnight)) {
                        if (history_split_faster[i].toLowerCase().contains("yes")) {
                            Log.w("firstfirst","yes");
                            return "yes";
                        }
                    }
                }
            }
            if (frequency_faster.contains("Mo") || frequency_faster.contains("Tu") || frequency_faster.contains("We") || frequency_faster.contains("Th") || frequency_faster.contains("Fr") || frequency_faster.contains("Sa") || frequency_faster.contains("Su")) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(milli);
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY && frequency_faster.contains("Mo")) {
                    return "no";
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY && frequency_faster.contains("Tu")) {
                    return "no";
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY && frequency_faster.contains("We")) {
                    return "no";
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY && frequency_faster.contains("Th")) {
                    return "no";
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && frequency_faster.contains("Fr")) {
                    return "no";
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY && frequency_faster.contains("Sa")) {
                    return "no";
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && frequency_faster.contains("Su")) {
                    return "no";
                }
                return "yes";
            }
            if (history_faster != null && !history_faster.equals("")) {
                long max_time = Long.parseLong(history_split_faster[0].replace("yes", "").replace("no", "").replace("small_split", ""));
                for (int i = history_split_faster.length - 1; i >= 0; i--) {
                    long split_small = Long.parseLong(history_split_faster[i].replace("yes", "").replace("no", "").replace("small_split", ""));
                    if (split_small > max_time) {
                        max_time = split_small;
                    }
                }
                long time_difference_milli = milli - max_time;
                if (time_difference_milli > 0) {
                    int days = (int) (time_difference_milli / 86400000);
                    if (days > Integer.parseInt(frequency_faster)) {
                        return "no";
                    } else {
                        return "yes";
                    }
                }
            }
            return "no";
        }
        return "no";
    }

    private boolean is_day_required(long milli) {
        if (frequency_faster.contains("Mo") || frequency_faster.contains("Tu") || frequency_faster.contains("We") || frequency_faster.contains("Th") || frequency_faster.contains("Fr") || frequency_faster.contains("Sa") || frequency_faster.contains("Su")) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(milli);
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY && frequency_faster.contains("Mo")) {
                return true;
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY && frequency_faster.contains("Tu")) {
                return true;
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY && frequency_faster.contains("We")) {
                return true;
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY && frequency_faster.contains("Th")) {
                return true;
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && frequency_faster.contains("Fr")) {
                return true;
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY && frequency_faster.contains("Sa")) {
                return true;
            } else
                return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && frequency_faster.contains("Su");
        } else {
            long max_time;
            if (time_history_with_all_values.lower(Simplify_the_time.return_time_in_midnight(milli)) != null) {
                max_time = time_history_with_all_values.lower(Simplify_the_time.return_time_in_midnight(milli));
            } else {
                return true;
            }
            /*if (max_time == 0) {
                return true;
            }
            max_time = Simplify_the_time.return_time_in_midnight(max_time);
            long midnight_time = Simplify_the_time.return_time_in_midnight(milli);
            long time_difference_milli = midnight_time - max_time;
            int days = (int) Math.ceil((double) time_difference_milli / 86400000d);
            return days >= Integer.parseInt(frequency_faster);
        }
    }

    private String return_state_of_day(long milli) {
        String return_me;
        if (is_day_required(milli)) {
            return_me = "required ";
        } else {
            return_me = "reject required ";
        }
        int index = Collections.binarySearch(array_with_all_values, Simplify_the_time.return_time_in_midnight(milli));
        if (index >= 0) {
            String[] small_split = history_split_faster[index].split("small_split");
            return_me = return_me.concat(small_split[0].toLowerCase());
        } else {
            return_me = return_me.concat("reject input");
        }
        /*if (history_faster != null && history_faster.contains(time_at_midnight)) {
            for (int i = 0; i < history_split_faster.length; i++) {
                if (history_split_faster[i].contains(time_at_midnight)) {
                    String[] split_the_smallest = history_split_faster[i].split("small_split");
                    return_me = return_me.concat(split_the_smallest[0].toLowerCase());
                    break;
                }
            }
        } else {
            return_me = return_me.concat("reject input");
        }
        return return_me;
    }

    private int return_the_streak() {
        int streak = 0;
        int negative_counter = 1;
        String return_from_function = return_state_of_day(System.currentTimeMillis() - (86400000L * negative_counter));
        int how_many_days = (int) ((Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(start_date)) / 86400000);
        while ((return_from_function.contains("reject required ") || return_from_function.equals("required yes")) && negative_counter <= how_many_days) {
            streak++;
            negative_counter++;
            return_from_function = return_state_of_day(System.currentTimeMillis() - (86400000L * negative_counter));
        }
        if (return_state_of_day(System.currentTimeMillis()).contains("reject required") || return_state_of_day(System.currentTimeMillis()).contains("yes")) {
            streak++;
        }
        return streak;
    }

    private void set_the_streak() {
        if (getView() != null) {
            TextView actual_current_in_text_number_in_good_habits = getView().findViewById(R.id.actual_current_in_text_number_in_good_habits);
            actual_current_in_text_number_in_good_habits.setText(String.valueOf(return_the_streak()));
        }
    }

    private void return_the_percent_for_card() {
        if (getView() != null) {
            TextView title_of_the_good_habit_in_card = getView().findViewById(R.id.title_of_the_good_habit_in_card);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits_save", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString(title_of_the_good_habit_in_card.getText().toString().toLowerCase(), "");
            TextView percent_in_the_corener_of_card = getView().findViewById(R.id.percent_in_the_corener_of_card);
            String[] habits_split = read_the_habits().split("small_split");
            int goal = Integer.parseInt(habits_split[2]);
            int number_done = shared.split("yes").length - 1;
            int percent = ((number_done * 100) / goal);
            if (percent >= 100) {
                percent_in_the_corener_of_card.setText("100%!!");
            } else {
                percent_in_the_corener_of_card.setText(String.valueOf(percent).concat("%"));
            }
        }
    }

    private void read_the_best_and_average_streak() {
        if (getView() != null && getActivity() != null) {
            TextView title_of_the_good_habit_in_card = getView().findViewById(R.id.title_of_the_good_habit_in_card);
            TextView actual_average_in_text_number_in_good_habits = getView().findViewById(R.id.actual_average_in_text_number_in_good_habits);
            TextView actual_best_in_text_number_in_good_habits = getView().findViewById(R.id.actual_best_in_text_number_in_good_habits);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits_save", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString(title_of_the_good_habit_in_card.getText().toString().toLowerCase(), "");
            if (shared != null) {
                int days = (int) ((Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) - Simplify_the_time.return_time_in_midnight(start_date)) / 86400000L);
                int where_to_write = 0;
                boolean you_can_add = false;
                streaks_information = new ArrayList<>();
                for (int i = 0; i <= days; i++) {
                    String return_from_function = return_state_of_day(start_date + (i * 86400000L));
                    if (return_from_function.contains("reject required") || return_from_function.contains("yes")) {
                        if (streaks_information.size() <= where_to_write) {
                            streaks_information.add(1);
                        } else {
                            streaks_information.set(where_to_write, streaks_information.get(where_to_write) + 1);
                        }
                        you_can_add = true;
                    } else {
                        if (you_can_add) {
                            where_to_write++;
                            you_can_add = false;
                        }
                    }
                }
                int sum = 0;
                for (int i = 0; i < streaks_information.size(); i++) {
                    sum = sum + streaks_information.get(i);
                }

                float average = ((float) sum / streaks_information.size());
                if (streaks_information.size() > 0) {
                    actual_best_in_text_number_in_good_habits.setText(String.valueOf(Collections.max(streaks_information)).concat(" days"));
                } else {
                    actual_best_in_text_number_in_good_habits.setText("0 days");
                    average = 0;
                }
                actual_average_in_text_number_in_good_habits.setText(String.format("%.2f", average).concat(" days"));
            }
        }
    }

    private void set_the_first_day_of_the_week_number() {
        if (getView() != null) {
            Calendar calender = Calendar.getInstance();
            TextView first_day_in_the_week_calender = getView().findViewById(R.id.first_day_in_the_week_calender);
            TextView second_day_in_the_week_calender = getView().findViewById(R.id.second_day_in_the_week_calender);
            TextView third_day_in_the_week_calender = getView().findViewById(R.id.third_day_in_the_week_calender);
            TextView fourth_day_in_the_week_calender = getView().findViewById(R.id.fourth_day_in_the_week_calender);
            TextView fifth_day_in_the_week_calender = getView().findViewById(R.id.fifth_day_in_the_week_calender);
            TextView sixth_day_in_the_week_calender = getView().findViewById(R.id.sixth_day_in_the_week_calender);
            TextView seventh_day_in_the_week_calender = getView().findViewById(R.id.seventh_day_in_the_week_calender);
            int year = calender.get(Calendar.YEAR);
            String month = return_month(calender.get(Calendar.MONTH));
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            month_and_year_in_calender_for_good_habits.setText(month.concat(" ").concat(String.valueOf(year)));
            if (calender.getFirstDayOfWeek() == Calendar.SUNDAY) {
                first_day_in_the_week_calender.setText("S");
                second_day_in_the_week_calender.setText("M");
                third_day_in_the_week_calender.setText("T");
                fourth_day_in_the_week_calender.setText("W");
                fifth_day_in_the_week_calender.setText("T");
                sixth_day_in_the_week_calender.setText("F");
                seventh_day_in_the_week_calender.setText("S");
            } else if (calender.getFirstDayOfWeek() == Calendar.MONDAY) {
                first_day_in_the_week_calender.setText("M");
                second_day_in_the_week_calender.setText("T");
                third_day_in_the_week_calender.setText("W");
                fourth_day_in_the_week_calender.setText("T");
                fifth_day_in_the_week_calender.setText("F");
                sixth_day_in_the_week_calender.setText("S");
                seventh_day_in_the_week_calender.setText("S");
            } else if (calender.getFirstDayOfWeek() == Calendar.TUESDAY) {
                first_day_in_the_week_calender.setText("T");
                second_day_in_the_week_calender.setText("W");
                third_day_in_the_week_calender.setText("T");
                fourth_day_in_the_week_calender.setText("F");
                fifth_day_in_the_week_calender.setText("S");
                sixth_day_in_the_week_calender.setText("S");
                seventh_day_in_the_week_calender.setText("M");
            } else if (calender.getFirstDayOfWeek() == Calendar.WEDNESDAY) {
                first_day_in_the_week_calender.setText("W");
                second_day_in_the_week_calender.setText("T");
                third_day_in_the_week_calender.setText("F");
                fourth_day_in_the_week_calender.setText("S");
                fifth_day_in_the_week_calender.setText("S");
                sixth_day_in_the_week_calender.setText("M");
                seventh_day_in_the_week_calender.setText("T");
            } else if (calender.getFirstDayOfWeek() == Calendar.THURSDAY) {
                first_day_in_the_week_calender.setText("T");
                second_day_in_the_week_calender.setText("F");
                third_day_in_the_week_calender.setText("S");
                fourth_day_in_the_week_calender.setText("S");
                fifth_day_in_the_week_calender.setText("M");
                sixth_day_in_the_week_calender.setText("T");
                seventh_day_in_the_week_calender.setText("W");
            } else if (calender.getFirstDayOfWeek() == Calendar.FRIDAY) {
                first_day_in_the_week_calender.setText("F");
                second_day_in_the_week_calender.setText("S");
                third_day_in_the_week_calender.setText("S");
                fourth_day_in_the_week_calender.setText("M");
                fifth_day_in_the_week_calender.setText("T");
                sixth_day_in_the_week_calender.setText("W");
                seventh_day_in_the_week_calender.setText("T");
            } else {
                first_day_in_the_week_calender.setText("S");
                second_day_in_the_week_calender.setText("S");
                third_day_in_the_week_calender.setText("M");
                fourth_day_in_the_week_calender.setText("T");
                fifth_day_in_the_week_calender.setText("W");
                sixth_day_in_the_week_calender.setText("T");
                seventh_day_in_the_week_calender.setText("F");
            }
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

    private int return_first_day_of_month() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            Calendar calender_for_first_day_of_month_only = Calendar.getInstance();
            calender_for_first_day_of_month_only.set(Calendar.MONTH, return_month_string_to_int(splitter[0]));
            calender_for_first_day_of_month_only.set(Calendar.YEAR, Integer.parseInt(splitter[1]));
            calender_for_first_day_of_month_only.set(Calendar.DAY_OF_MONTH, calender_for_first_day_of_month_only.getActualMinimum(Calendar.DAY_OF_MONTH));
            return calender_for_first_day_of_month_only.get(Calendar.DAY_OF_WEEK);
        } else {
            return 0;
        }
    }

    private int return_last_day_of_month() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            Calendar calender_for_last_day_of_month_only = Calendar.getInstance();
            String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            calender_for_last_day_of_month_only.set(Calendar.MONTH, return_month_string_to_int(splitter[0]));
            calender_for_last_day_of_month_only.set(Calendar.YEAR, Integer.parseInt(splitter[1]));
            calender_for_last_day_of_month_only.set(Calendar.DAY_OF_MONTH, 1);
            return calender_for_last_day_of_month_only.getActualMaximum(Calendar.DAY_OF_MONTH);
        } else {
            return 1;
        }
    }

    private int return_month_string_to_int(String month) {
        if (month.equals("January")) {
            return 0;
        } else if (month.equals("February")) {
            return 1;
        } else if (month.equals("March")) {
            return 2;
        } else if (month.equals("April")) {
            return 3;
        } else if (month.equals("May")) {
            return 4;
        } else if (month.equals("June")) {
            return 5;
        } else if (month.equals("July")) {
            return 6;
        } else if (month.equals("August")) {
            return 7;
        } else if (month.equals("September")) {
            return 8;
        } else if (month.equals("October")) {
            return 9;
        } else if (month.equals("November")) {
            return 10;
        } else {
            return 11;
        }
    }

    private void set_the_days_on_the_real_text() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            Calendar calender_for_first_day_of_month_only = Calendar.getInstance();
            calender_for_first_day_of_month_only.set(Calendar.MONTH, return_month_string_to_int(splitter[0]));
            calender_for_first_day_of_month_only.set(Calendar.YEAR, Integer.parseInt(splitter[1]));
            if (calender_for_first_day_of_month_only.getFirstDayOfWeek() == Calendar.SUNDAY) {
                day_is_sunday();
            } else if (calender_for_first_day_of_month_only.getFirstDayOfWeek() == Calendar.MONDAY) {
                day_is_monday();
            } else if (calender_for_first_day_of_month_only.getFirstDayOfWeek() == Calendar.TUESDAY) {
                day_is_tuesday();
            } else if (calender_for_first_day_of_month_only.getFirstDayOfWeek() == Calendar.WEDNESDAY) {
                day_is_wednesday();
            } else if (calender_for_first_day_of_month_only.getFirstDayOfWeek() == Calendar.THURSDAY) {
                day_is_thursday();
            } else if (calender_for_first_day_of_month_only.getFirstDayOfWeek() == Calendar.FRIDAY) {
                day_is_friday();
            } else {
                day_is_saturday();
            }
        }
    }

    private void day_is_sunday() {
        int day = 1;
        if (return_first_day_of_month() == Calendar.SUNDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.MONDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.TUESDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.WEDNESDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.THURSDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.FRIDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void day_is_monday() {
        int day = 1;
        if (return_first_day_of_month() == Calendar.MONDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.TUESDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.WEDNESDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.THURSDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.FRIDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.SATURDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void day_is_tuesday() {
        int day = 1;
        if (return_first_day_of_month() == Calendar.TUESDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.WEDNESDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.THURSDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.FRIDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.SATURDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.SUNDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void day_is_wednesday() {
        int day = 1;
        if (return_first_day_of_month() == Calendar.WEDNESDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.THURSDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.FRIDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.SATURDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.SUNDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.MONDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void day_is_thursday() {
        int day = 1;
        if (return_first_day_of_month() == Calendar.THURSDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.FRIDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.SATURDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.SUNDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.MONDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.TUESDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void day_is_friday() {
        int day = 1;
        if (return_first_day_of_month() == Calendar.FRIDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.SATURDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.SUNDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.MONDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.TUESDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.WEDNESDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void day_is_saturday() {
        int day = 1;
        if (return_first_day_of_month() == Calendar.SATURDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.SUNDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.MONDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.TUESDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.WEDNESDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (return_first_day_of_month() == Calendar.THURSDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= return_last_day_of_month()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void define_the_buttons() {
        if (getView() != null) {
            calender_button_showing_shadow_1 = getView().findViewById(R.id.calender_button_showing_shadow_1);
            calender_button_showing_shadow_2 = getView().findViewById(R.id.calender_button_showing_shadow_2);
            calender_button_showing_shadow_3 = getView().findViewById(R.id.calender_button_showing_shadow_3);
            calender_button_showing_shadow_4 = getView().findViewById(R.id.calender_button_showing_shadow_4);
            calender_button_showing_shadow_5 = getView().findViewById(R.id.calender_button_showing_shadow_5);
            calender_button_showing_shadow_6 = getView().findViewById(R.id.calender_button_showing_shadow_6);
            calender_button_showing_shadow_7 = getView().findViewById(R.id.calender_button_showing_shadow_7);
            calender_button_showing_shadow_8 = getView().findViewById(R.id.calender_button_showing_shadow_8);
            calender_button_showing_shadow_9 = getView().findViewById(R.id.calender_button_showing_shadow_9);
            calender_button_showing_shadow_10 = getView().findViewById(R.id.calender_button_showing_shadow_10);
            calender_button_showing_shadow_11 = getView().findViewById(R.id.calender_button_showing_shadow_11);
            calender_button_showing_shadow_12 = getView().findViewById(R.id.calender_button_showing_shadow_12);
            calender_button_showing_shadow_13 = getView().findViewById(R.id.calender_button_showing_shadow_13);
            calender_button_showing_shadow_14 = getView().findViewById(R.id.calender_button_showing_shadow_14);
            calender_button_showing_shadow_15 = getView().findViewById(R.id.calender_button_showing_shadow_15);
            calender_button_showing_shadow_16 = getView().findViewById(R.id.calender_button_showing_shadow_16);
            calender_button_showing_shadow_17 = getView().findViewById(R.id.calender_button_showing_shadow_17);
            calender_button_showing_shadow_18 = getView().findViewById(R.id.calender_button_showing_shadow_18);
            calender_button_showing_shadow_19 = getView().findViewById(R.id.calender_button_showing_shadow_19);
            calender_button_showing_shadow_20 = getView().findViewById(R.id.calender_button_showing_shadow_20);
            calender_button_showing_shadow_21 = getView().findViewById(R.id.calender_button_showing_shadow_21);
            calender_button_showing_shadow_22 = getView().findViewById(R.id.calender_button_showing_shadow_22);
            calender_button_showing_shadow_23 = getView().findViewById(R.id.calender_button_showing_shadow_23);
            calender_button_showing_shadow_24 = getView().findViewById(R.id.calender_button_showing_shadow_24);
            calender_button_showing_shadow_25 = getView().findViewById(R.id.calender_button_showing_shadow_25);
            calender_button_showing_shadow_26 = getView().findViewById(R.id.calender_button_showing_shadow_26);
            calender_button_showing_shadow_27 = getView().findViewById(R.id.calender_button_showing_shadow_27);
            calender_button_showing_shadow_28 = getView().findViewById(R.id.calender_button_showing_shadow_28);
            calender_button_showing_shadow_29 = getView().findViewById(R.id.calender_button_showing_shadow_29);
            calender_button_showing_shadow_30 = getView().findViewById(R.id.calender_button_showing_shadow_30);
            calender_button_showing_shadow_31 = getView().findViewById(R.id.calender_button_showing_shadow_31);
            calender_button_showing_shadow_32 = getView().findViewById(R.id.calender_button_showing_shadow_32);
            calender_button_showing_shadow_33 = getView().findViewById(R.id.calender_button_showing_shadow_33);
            calender_button_showing_shadow_34 = getView().findViewById(R.id.calender_button_showing_shadow_34);
            calender_button_showing_shadow_35 = getView().findViewById(R.id.calender_button_showing_shadow_35);
            calender_button_showing_shadow_36 = getView().findViewById(R.id.calender_button_showing_shadow_36);
            calender_button_showing_shadow_37 = getView().findViewById(R.id.calender_button_showing_shadow_37);
        }
    }

    private void back_and_front_button_listen() {
        if (getView() != null) {
            Button button_shadow_for_the_back_for_good_habits = getView().findViewById(R.id.button_shadow_for_the_back_for_good_habits);
            Button button_shadow_for_the_front_for_good_habits = getView().findViewById(R.id.button_shadow_for_the_front_for_good_habits);
            final TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            button_shadow_for_the_back_for_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    if (return_month_string_to_int(splitter[0]) == 0) {
                        String month_name = return_month(11);
                        String year = String.valueOf(Integer.parseInt(splitter[1]) - 1);
                        month_and_year_in_calender_for_good_habits.setText(month_name.concat(" ").concat(year));
                        clear_the_calender();
                        set_the_days_on_the_real_text();
                        clear_the_color_from_the_keyboard();
                        set_all_buttons_to_visible();
                        remove_the_hiding_buttons();
                        color_today();
                        check_if_date_is_future();
                        hide_or_un_hide_the_button(0);
                        color_the_calender();
                        check_if_date_is_future();
                        color_the_button_under_the_calender();
                        clear_all_the_unders();
                        divide_it_into_weeks();
                        clear_the_middle();
                        make_the_middle_come_again();
                    } else {
                        String month_name = return_month(return_month_string_to_int(splitter[0]) - 1);
                        month_and_year_in_calender_for_good_habits.setText(month_name.concat(" ").concat(splitter[1]));
                        clear_the_calender();
                        set_the_days_on_the_real_text();
                        clear_the_color_from_the_keyboard();
                        set_all_buttons_to_visible();
                        remove_the_hiding_buttons();
                        color_today();
                        check_if_date_is_future();
                        hide_or_un_hide_the_button(0);
                        color_the_calender();
                        check_if_date_is_future();
                        color_the_button_under_the_calender();
                        clear_all_the_unders();
                        divide_it_into_weeks();
                        clear_the_middle();
                        make_the_middle_come_again();
                    }
                }
            });
            button_shadow_for_the_front_for_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    if (return_month_string_to_int((splitter[0])) == 11) {
                        String month_name = return_month(0);
                        String year = String.valueOf(Integer.parseInt(splitter[1]) + 1);
                        month_and_year_in_calender_for_good_habits.setText(month_name.concat(" ").concat(year));
                        clear_the_calender();
                        set_the_days_on_the_real_text();
                        clear_the_color_from_the_keyboard();
                        set_all_buttons_to_visible();
                        remove_the_hiding_buttons();
                        color_today();
                        check_if_date_is_future();
                        hide_or_un_hide_the_button(0);
                        color_the_calender();
                        check_if_date_is_future();
                        color_the_button_under_the_calender();
                        clear_all_the_unders();
                        divide_it_into_weeks();
                        clear_the_middle();
                        make_the_middle_come_again();
                    } else {
                        String month_name = return_month(return_month_string_to_int(splitter[0]) + 1);
                        month_and_year_in_calender_for_good_habits.setText(month_name.concat(" ").concat(splitter[1]));
                        clear_the_calender();
                        set_the_days_on_the_real_text();
                        clear_the_color_from_the_keyboard();
                        set_all_buttons_to_visible();
                        remove_the_hiding_buttons();
                        color_today();
                        check_if_date_is_future();
                        hide_or_un_hide_the_button(0);
                        color_the_calender();
                        check_if_date_is_future();
                        color_the_button_under_the_calender();
                        clear_all_the_unders();
                        divide_it_into_weeks();
                        clear_the_middle();
                        make_the_middle_come_again();
                    }
                }
            });
        }
    }

    private void clear_the_calender() {
        calender_button_showing_shadow_1.setText("");
        calender_button_showing_shadow_2.setText("");
        calender_button_showing_shadow_3.setText("");
        calender_button_showing_shadow_4.setText("");
        calender_button_showing_shadow_5.setText("");
        calender_button_showing_shadow_6.setText("");
        calender_button_showing_shadow_7.setText("");
        calender_button_showing_shadow_8.setText("");
        calender_button_showing_shadow_9.setText("");
        calender_button_showing_shadow_10.setText("");
        calender_button_showing_shadow_11.setText("");
        calender_button_showing_shadow_12.setText("");
        calender_button_showing_shadow_13.setText("");
        calender_button_showing_shadow_14.setText("");
        calender_button_showing_shadow_15.setText("");
        calender_button_showing_shadow_16.setText("");
        calender_button_showing_shadow_17.setText("");
        calender_button_showing_shadow_18.setText("");
        calender_button_showing_shadow_19.setText("");
        calender_button_showing_shadow_20.setText("");
        calender_button_showing_shadow_21.setText("");
        calender_button_showing_shadow_22.setText("");
        calender_button_showing_shadow_23.setText("");
        calender_button_showing_shadow_24.setText("");
        calender_button_showing_shadow_25.setText("");
        calender_button_showing_shadow_26.setText("");
        calender_button_showing_shadow_27.setText("");
        calender_button_showing_shadow_28.setText("");
        calender_button_showing_shadow_29.setText("");
        calender_button_showing_shadow_30.setText("");
        calender_button_showing_shadow_31.setText("");
        calender_button_showing_shadow_32.setText("");
        calender_button_showing_shadow_33.setText("");
        calender_button_showing_shadow_34.setText("");
        calender_button_showing_shadow_35.setText("");
        calender_button_showing_shadow_36.setText("");
        calender_button_showing_shadow_37.setText("");
    }

    private void color_the_selected(int which) {
        if (which == 1) {
            calender_button_showing_shadow_1.setTextColor(Color.WHITE);
            calender_button_showing_shadow_1.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 2) {
            calender_button_showing_shadow_2.setTextColor(Color.WHITE);
            calender_button_showing_shadow_2.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 3) {
            calender_button_showing_shadow_3.setTextColor(Color.WHITE);
            calender_button_showing_shadow_3.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 4) {
            calender_button_showing_shadow_4.setTextColor(Color.WHITE);
            calender_button_showing_shadow_4.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 5) {
            calender_button_showing_shadow_5.setTextColor(Color.WHITE);
            calender_button_showing_shadow_5.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 6) {
            calender_button_showing_shadow_6.setTextColor(Color.WHITE);
            calender_button_showing_shadow_6.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 7) {
            calender_button_showing_shadow_7.setTextColor(Color.WHITE);
            calender_button_showing_shadow_7.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 8) {
            calender_button_showing_shadow_8.setTextColor(Color.WHITE);
            calender_button_showing_shadow_8.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 9) {
            calender_button_showing_shadow_9.setTextColor(Color.WHITE);
            calender_button_showing_shadow_9.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 10) {
            calender_button_showing_shadow_10.setTextColor(Color.WHITE);
            calender_button_showing_shadow_10.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 11) {
            calender_button_showing_shadow_11.setTextColor(Color.WHITE);
            calender_button_showing_shadow_11.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 12) {
            calender_button_showing_shadow_12.setTextColor(Color.WHITE);
            calender_button_showing_shadow_12.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 13) {
            calender_button_showing_shadow_13.setTextColor(Color.WHITE);
            calender_button_showing_shadow_13.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 14) {
            calender_button_showing_shadow_14.setTextColor(Color.WHITE);
            calender_button_showing_shadow_14.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 15) {
            calender_button_showing_shadow_15.setTextColor(Color.WHITE);
            calender_button_showing_shadow_15.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 16) {
            calender_button_showing_shadow_16.setTextColor(Color.WHITE);
            calender_button_showing_shadow_16.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 17) {
            calender_button_showing_shadow_17.setTextColor(Color.WHITE);
            calender_button_showing_shadow_17.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 18) {
            calender_button_showing_shadow_18.setTextColor(Color.WHITE);
            calender_button_showing_shadow_18.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 19) {
            calender_button_showing_shadow_19.setTextColor(Color.WHITE);
            calender_button_showing_shadow_19.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 20) {
            calender_button_showing_shadow_20.setTextColor(Color.WHITE);
            calender_button_showing_shadow_20.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 21) {
            calender_button_showing_shadow_21.setTextColor(Color.WHITE);
            calender_button_showing_shadow_21.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 22) {
            calender_button_showing_shadow_22.setTextColor(Color.WHITE);
            calender_button_showing_shadow_22.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 23) {
            calender_button_showing_shadow_23.setTextColor(Color.WHITE);
            calender_button_showing_shadow_23.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 24) {
            calender_button_showing_shadow_24.setTextColor(Color.WHITE);
            calender_button_showing_shadow_24.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 25) {
            calender_button_showing_shadow_25.setTextColor(Color.WHITE);
            calender_button_showing_shadow_25.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 26) {
            calender_button_showing_shadow_26.setTextColor(Color.WHITE);
            calender_button_showing_shadow_26.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 27) {
            calender_button_showing_shadow_27.setTextColor(Color.WHITE);
            calender_button_showing_shadow_27.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 28) {
            calender_button_showing_shadow_28.setTextColor(Color.WHITE);
            calender_button_showing_shadow_28.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 29) {
            calender_button_showing_shadow_29.setTextColor(Color.WHITE);
            calender_button_showing_shadow_29.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 30) {
            calender_button_showing_shadow_30.setTextColor(Color.WHITE);
            calender_button_showing_shadow_30.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 31) {
            calender_button_showing_shadow_31.setTextColor(Color.WHITE);
            calender_button_showing_shadow_31.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 32) {
            calender_button_showing_shadow_32.setTextColor(Color.WHITE);
            calender_button_showing_shadow_32.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 33) {
            calender_button_showing_shadow_33.setTextColor(Color.WHITE);
            calender_button_showing_shadow_33.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 34) {
            calender_button_showing_shadow_34.setTextColor(Color.WHITE);
            calender_button_showing_shadow_34.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 35) {
            calender_button_showing_shadow_35.setTextColor(Color.WHITE);
            calender_button_showing_shadow_35.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 36) {
            calender_button_showing_shadow_36.setTextColor(Color.WHITE);
            calender_button_showing_shadow_36.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else {
            calender_button_showing_shadow_37.setTextColor(Color.WHITE);
            calender_button_showing_shadow_37.setBackgroundResource(R.drawable.round_button_colored_fav);
        }
    }

    private void clear_the_color_from_the_keyboard() {
        calender_button_showing_shadow_1.setTextColor(Color.BLACK);
        calender_button_showing_shadow_1.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_2.setTextColor(Color.BLACK);
        calender_button_showing_shadow_2.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_3.setTextColor(Color.BLACK);
        calender_button_showing_shadow_3.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_4.setTextColor(Color.BLACK);
        calender_button_showing_shadow_4.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_5.setTextColor(Color.BLACK);
        calender_button_showing_shadow_5.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_6.setTextColor(Color.BLACK);
        calender_button_showing_shadow_6.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_7.setTextColor(Color.BLACK);
        calender_button_showing_shadow_7.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_8.setTextColor(Color.BLACK);
        calender_button_showing_shadow_8.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_9.setTextColor(Color.BLACK);
        calender_button_showing_shadow_9.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_10.setTextColor(Color.BLACK);
        calender_button_showing_shadow_10.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_11.setTextColor(Color.BLACK);
        calender_button_showing_shadow_11.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_12.setTextColor(Color.BLACK);
        calender_button_showing_shadow_12.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_13.setTextColor(Color.BLACK);
        calender_button_showing_shadow_13.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_14.setTextColor(Color.BLACK);
        calender_button_showing_shadow_14.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_15.setTextColor(Color.BLACK);
        calender_button_showing_shadow_15.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_16.setTextColor(Color.BLACK);
        calender_button_showing_shadow_16.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_17.setTextColor(Color.BLACK);
        calender_button_showing_shadow_17.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_18.setTextColor(Color.BLACK);
        calender_button_showing_shadow_18.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_19.setTextColor(Color.BLACK);
        calender_button_showing_shadow_19.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_20.setTextColor(Color.BLACK);
        calender_button_showing_shadow_20.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_21.setTextColor(Color.BLACK);
        calender_button_showing_shadow_21.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_22.setTextColor(Color.BLACK);
        calender_button_showing_shadow_22.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_23.setTextColor(Color.BLACK);
        calender_button_showing_shadow_23.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_24.setTextColor(Color.BLACK);
        calender_button_showing_shadow_24.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_25.setTextColor(Color.BLACK);
        calender_button_showing_shadow_25.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_26.setTextColor(Color.BLACK);
        calender_button_showing_shadow_26.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_27.setTextColor(Color.BLACK);
        calender_button_showing_shadow_27.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_28.setTextColor(Color.BLACK);
        calender_button_showing_shadow_28.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_29.setTextColor(Color.BLACK);
        calender_button_showing_shadow_29.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_30.setTextColor(Color.BLACK);
        calender_button_showing_shadow_30.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_31.setTextColor(Color.BLACK);
        calender_button_showing_shadow_31.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_32.setTextColor(Color.BLACK);
        calender_button_showing_shadow_32.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_33.setTextColor(Color.BLACK);
        calender_button_showing_shadow_33.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_34.setTextColor(Color.BLACK);
        calender_button_showing_shadow_34.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_35.setTextColor(Color.BLACK);
        calender_button_showing_shadow_35.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_36.setTextColor(Color.BLACK);
        calender_button_showing_shadow_36.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_37.setTextColor(Color.BLACK);
        calender_button_showing_shadow_37.setBackgroundResource(R.drawable.round_button);
    }

    private void color_the_today_value() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        color_the_today = String.valueOf(day).concat("_").concat(String.valueOf(month)).concat("_").concat(String.valueOf(year));
    }

    private void calender_button_listeners() {
        calender_button_showing_shadow_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(1);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_1.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(2);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_2.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(3);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_3.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(4);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_4.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(5);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_5.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(6);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_6.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(7);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_7.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(8);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_8.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(9);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_9.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(10);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_10.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(11);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_11.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(12);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_12.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(13);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_13.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(14);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_14.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(15);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_15.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(16);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_16.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(17);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_17.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(18);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_18.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(19);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_19.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(20);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_20.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(21);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_21.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(22);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_22.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(23);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_23.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(24);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_24.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(25);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_25.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(26);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_26.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(27);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_27.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(28);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_28.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(29);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_29.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(30);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_30.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(31);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_31.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(32);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_32.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(33);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_33.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(34);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_34.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(35);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_35.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(36);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_36.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
        calender_button_showing_shadow_37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_the_background_only();
                color_only_today();
                color_the_selected(37);
                if (getView() != null) {
                    TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
                    String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                    int month = return_month_string_to_int(splitter[0]);
                    color_the_today = calender_button_showing_shadow_37.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
                }
                check_if_date_is_future();
                color_the_button_under_the_calender();
            }
        });
    }

    private void remove_the_hiding_buttons() {
        if (calender_button_showing_shadow_1.getText().toString().equals("")) {
            calender_button_showing_shadow_1.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_2.getText().toString().equals("")) {
            calender_button_showing_shadow_2.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_3.getText().toString().equals("")) {
            calender_button_showing_shadow_3.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_4.getText().toString().equals("")) {
            calender_button_showing_shadow_4.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_5.getText().toString().equals("")) {
            calender_button_showing_shadow_5.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_6.getText().toString().equals("")) {
            calender_button_showing_shadow_6.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_29.getText().toString().equals("")) {
            calender_button_showing_shadow_29.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_30.getText().toString().equals("")) {
            calender_button_showing_shadow_30.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_31.getText().toString().equals("")) {
            calender_button_showing_shadow_31.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_32.getText().toString().equals("")) {
            calender_button_showing_shadow_32.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_33.getText().toString().equals("")) {
            calender_button_showing_shadow_33.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_34.getText().toString().equals("")) {
            calender_button_showing_shadow_34.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_35.getText().toString().equals("")) {
            calender_button_showing_shadow_35.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_36.getText().toString().equals("")) {
            calender_button_showing_shadow_36.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_37.getText().toString().equals("")) {
            calender_button_showing_shadow_37.setVisibility(View.INVISIBLE);
        }
    }

    private void set_all_buttons_to_visible() {
        calender_button_showing_shadow_1.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_2.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_3.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_4.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_5.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_6.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_7.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_8.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_9.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_10.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_11.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_12.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_13.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_14.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_15.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_16.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_17.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_17.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_18.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_19.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_20.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_21.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_22.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_23.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_24.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_25.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_26.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_27.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_28.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_29.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_30.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_31.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_32.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_33.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_34.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_35.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_36.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_37.setVisibility(View.VISIBLE);
    }

    private int return_which_day_is_linked_to_calender(int which) {
        if (calender_button_showing_shadow_1.getText().toString().equals(String.valueOf(which))) {
            return 1;
        } else if (calender_button_showing_shadow_2.getText().toString().equals(String.valueOf(which))) {
            return 2;
        } else if (calender_button_showing_shadow_3.getText().toString().equals(String.valueOf(which))) {
            return 3;
        } else if (calender_button_showing_shadow_4.getText().toString().equals(String.valueOf(which))) {
            return 4;
        } else if (calender_button_showing_shadow_5.getText().toString().equals(String.valueOf(which))) {
            return 5;
        } else if (calender_button_showing_shadow_6.getText().toString().equals(String.valueOf(which))) {
            return 6;
        } else if (calender_button_showing_shadow_7.getText().toString().equals(String.valueOf(which))) {
            return 7;
        } else if (calender_button_showing_shadow_8.getText().toString().equals(String.valueOf(which))) {
            return 8;
        } else if (calender_button_showing_shadow_9.getText().toString().equals(String.valueOf(which))) {
            return 9;
        } else if (calender_button_showing_shadow_10.getText().toString().equals(String.valueOf(which))) {
            return 10;
        } else if (calender_button_showing_shadow_11.getText().toString().equals(String.valueOf(which))) {
            return 11;
        } else if (calender_button_showing_shadow_12.getText().toString().equals(String.valueOf(which))) {
            return 12;
        } else if (calender_button_showing_shadow_13.getText().toString().equals(String.valueOf(which))) {
            return 13;
        } else if (calender_button_showing_shadow_14.getText().toString().equals(String.valueOf(which))) {
            return 14;
        } else if (calender_button_showing_shadow_15.getText().toString().equals(String.valueOf(which))) {
            return 15;
        } else if (calender_button_showing_shadow_16.getText().toString().equals(String.valueOf(which))) {
            return 16;
        } else if (calender_button_showing_shadow_17.getText().toString().equals(String.valueOf(which))) {
            return 17;
        } else if (calender_button_showing_shadow_18.getText().toString().equals(String.valueOf(which))) {
            return 18;
        } else if (calender_button_showing_shadow_19.getText().toString().equals(String.valueOf(which))) {
            return 19;
        } else if (calender_button_showing_shadow_20.getText().toString().equals(String.valueOf(which))) {
            return 20;
        } else if (calender_button_showing_shadow_21.getText().toString().equals(String.valueOf(which))) {
            return 21;
        } else if (calender_button_showing_shadow_22.getText().toString().equals(String.valueOf(which))) {
            return 22;
        } else if (calender_button_showing_shadow_23.getText().toString().equals(String.valueOf(which))) {
            return 23;
        } else if (calender_button_showing_shadow_24.getText().toString().equals(String.valueOf(which))) {
            return 24;
        } else if (calender_button_showing_shadow_25.getText().toString().equals(String.valueOf(which))) {
            return 25;
        } else if (calender_button_showing_shadow_26.getText().toString().equals(String.valueOf(which))) {
            return 26;
        } else if (calender_button_showing_shadow_27.getText().toString().equals(String.valueOf(which))) {
            return 27;
        } else if (calender_button_showing_shadow_28.getText().toString().equals(String.valueOf(which))) {
            return 28;
        } else if (calender_button_showing_shadow_29.getText().toString().equals(String.valueOf(which))) {
            return 29;
        } else if (calender_button_showing_shadow_30.getText().toString().equals(String.valueOf(which))) {
            return 30;
        } else if (calender_button_showing_shadow_31.getText().toString().equals(String.valueOf(which))) {
            return 31;
        } else if (calender_button_showing_shadow_32.getText().toString().equals(String.valueOf(which))) {
            return 32;
        } else if (calender_button_showing_shadow_33.getText().toString().equals(String.valueOf(which))) {
            return 33;
        } else if (calender_button_showing_shadow_34.getText().toString().equals(String.valueOf(which))) {
            return 34;
        } else if (calender_button_showing_shadow_35.getText().toString().equals(String.valueOf(which))) {
            return 35;
        } else if (calender_button_showing_shadow_36.getText().toString().equals(String.valueOf(which))) {
            return 36;
        } else {
            return 37;
        }
    }

    private boolean check_if_i_am_chosen(int which) {
        if (which == 1 && calender_button_showing_shadow_1.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 2 && calender_button_showing_shadow_2.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 3 && calender_button_showing_shadow_3.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 4 && calender_button_showing_shadow_4.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 5 && calender_button_showing_shadow_5.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 6 && calender_button_showing_shadow_6.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 7 && calender_button_showing_shadow_7.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 8 && calender_button_showing_shadow_8.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 9 && calender_button_showing_shadow_9.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 10 && calender_button_showing_shadow_10.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 11 && calender_button_showing_shadow_11.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 12 && calender_button_showing_shadow_12.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 13 && calender_button_showing_shadow_13.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 14 && calender_button_showing_shadow_14.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 15 && calender_button_showing_shadow_15.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 16 && calender_button_showing_shadow_16.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 17 && calender_button_showing_shadow_17.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 18 && calender_button_showing_shadow_18.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 19 && calender_button_showing_shadow_19.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 20 && calender_button_showing_shadow_20.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 21 && calender_button_showing_shadow_21.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 22 && calender_button_showing_shadow_22.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 23 && calender_button_showing_shadow_23.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 24 && calender_button_showing_shadow_24.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 25 && calender_button_showing_shadow_25.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 26 && calender_button_showing_shadow_26.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 27 && calender_button_showing_shadow_27.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 28 && calender_button_showing_shadow_28.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 29 && calender_button_showing_shadow_29.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 30 && calender_button_showing_shadow_30.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 31 && calender_button_showing_shadow_31.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 32 && calender_button_showing_shadow_32.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 33 && calender_button_showing_shadow_33.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 34 && calender_button_showing_shadow_34.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 35 && calender_button_showing_shadow_35.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (which == 36 && calender_button_showing_shadow_36.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else
            return which == 37 && calender_button_showing_shadow_37.getCurrentTextColor() == Color.WHITE;
    }

    private void color_today() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            String[] text_to_split = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            int year_from_text = Integer.parseInt(text_to_split[1]);
            int month_from_text = return_month_string_to_int(text_to_split[0]);
            String[] saved_text_split = color_the_today.split("_");
            int year = Integer.parseInt(saved_text_split[2]);
            int month = Integer.parseInt(saved_text_split[1]);
            int day = Integer.parseInt(saved_text_split[0]);
            if ((year_from_text == year) && (month_from_text == month)) {
                if (!calender_button_showing_shadow_1.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_1.getText().toString()) == day) {
                    color_the_selected(1);
                } else if (!calender_button_showing_shadow_2.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_2.getText().toString()) == day) {
                    color_the_selected(2);
                } else if (!calender_button_showing_shadow_3.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_3.getText().toString()) == day) {
                    color_the_selected(3);
                } else if (!calender_button_showing_shadow_4.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_4.getText().toString()) == day) {
                    color_the_selected(4);
                } else if (!calender_button_showing_shadow_5.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_5.getText().toString()) == day) {
                    color_the_selected(5);
                } else if (!calender_button_showing_shadow_6.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_6.getText().toString()) == day) {
                    color_the_selected(6);
                } else if (!calender_button_showing_shadow_7.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_7.getText().toString()) == day) {
                    color_the_selected(7);
                } else if (!calender_button_showing_shadow_8.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_8.getText().toString()) == day) {
                    color_the_selected(8);
                } else if (!calender_button_showing_shadow_9.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_9.getText().toString()) == day) {
                    color_the_selected(9);
                } else if (!calender_button_showing_shadow_10.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_10.getText().toString()) == day) {
                    color_the_selected(10);
                } else if (!calender_button_showing_shadow_11.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_11.getText().toString()) == day) {
                    color_the_selected(11);
                } else if (!calender_button_showing_shadow_12.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_12.getText().toString()) == day) {
                    color_the_selected(12);
                } else if (!calender_button_showing_shadow_13.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_13.getText().toString()) == day) {
                    color_the_selected(13);
                } else if (!calender_button_showing_shadow_14.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_14.getText().toString()) == day) {
                    color_the_selected(14);
                } else if (!calender_button_showing_shadow_15.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_15.getText().toString()) == day) {
                    color_the_selected(15);
                } else if (!calender_button_showing_shadow_16.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_16.getText().toString()) == day) {
                    color_the_selected(16);
                } else if (!calender_button_showing_shadow_17.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_17.getText().toString()) == day) {
                    color_the_selected(17);
                } else if (!calender_button_showing_shadow_18.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_18.getText().toString()) == day) {
                    color_the_selected(18);
                } else if (!calender_button_showing_shadow_19.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_19.getText().toString()) == day) {
                    color_the_selected(19);
                } else if (!calender_button_showing_shadow_20.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_20.getText().toString()) == day) {
                    color_the_selected(20);
                } else if (!calender_button_showing_shadow_21.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_21.getText().toString()) == day) {
                    color_the_selected(21);
                } else if (!calender_button_showing_shadow_22.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_22.getText().toString()) == day) {
                    color_the_selected(22);
                } else if (!calender_button_showing_shadow_23.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_23.getText().toString()) == day) {
                    color_the_selected(23);
                } else if (!calender_button_showing_shadow_24.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_24.getText().toString()) == day) {
                    color_the_selected(24);
                } else if (!calender_button_showing_shadow_25.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_25.getText().toString()) == day) {
                    color_the_selected(25);
                } else if (!calender_button_showing_shadow_26.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_26.getText().toString()) == day) {
                    color_the_selected(26);
                } else if (!calender_button_showing_shadow_27.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_27.getText().toString()) == day) {
                    color_the_selected(27);
                } else if (!calender_button_showing_shadow_28.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_28.getText().toString()) == day) {
                    color_the_selected(28);
                } else if (!calender_button_showing_shadow_29.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_29.getText().toString()) == day) {
                    color_the_selected(29);
                } else if (!calender_button_showing_shadow_30.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_30.getText().toString()) == day) {
                    color_the_selected(30);
                } else if (!calender_button_showing_shadow_31.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_31.getText().toString()) == day) {
                    color_the_selected(31);
                } else if (!calender_button_showing_shadow_32.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_32.getText().toString()) == day) {
                    color_the_selected(32);
                } else if (!calender_button_showing_shadow_33.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_33.getText().toString()) == day) {
                    color_the_selected(33);
                } else if (!calender_button_showing_shadow_34.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_34.getText().toString()) == day) {
                    color_the_selected(34);
                } else if (!calender_button_showing_shadow_35.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_35.getText().toString()) == day) {
                    color_the_selected(35);
                } else if (!calender_button_showing_shadow_36.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_36.getText().toString()) == day) {
                    color_the_selected(36);
                } else if (!calender_button_showing_shadow_37.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_37.getText().toString()) == day) {
                    color_the_selected(37);
                }
            }
        }
    }

    private void color_the_calender() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            String yes_color = "#06a94d";
            String yes_color_no_input = "#6a5acd";
            String no_color = "#FF2400";
            String no_color_no_input = "#000000";
            colors = new String[return_last_day_of_month() + 1];
            Calendar calendar = Calendar.getInstance();
            String[] split_month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            int year = Integer.parseInt(split_month_and_year[1]);
            int month = return_month_string_to_int(split_month_and_year[0]);
            if (!check_past_now_or_future().equals("future")) {
                for (int i = 1; i <= return_last_day_of_month(); i++) {
                    calendar.set(year, month, i);
                    long time_in_milli = calendar.getTimeInMillis();
                    if (Simplify_the_time.return_time_in_midnight(time_in_milli) >= Simplify_the_time.return_time_in_midnight(start_date)) {
                        String the_read_value = return_state_of_day(time_in_milli);
                        if (the_read_value.contains("yes")) {
                            colors[i] = yes_color;
                        } else if (the_read_value.equals("reject required reject input")) {
                            if (Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) >= Simplify_the_time.return_time_in_midnight(time_in_milli)) {
                                colors[i] = yes_color_no_input;
                            } else {
                                colors[i] = "#000000";
                            }
                        } else if (the_read_value.contains("no")) {
                            colors[i] = no_color;
                        } else if (the_read_value.equals("required reject input")) {
                            if (Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()) >= Simplify_the_time.return_time_in_midnight(time_in_milli)) {
                                colors[i] = no_color_no_input;
                            } else {
                                colors[i] = "#000000";
                            }
                        } else if (the_read_value.equals("")) {
                            colors[i] = "#000000";
                        }
                    } else {
                        colors[i] = "#000000";
                    }
                }
            } else {
                for (int i = 1; i <= return_last_day_of_month(); i++) {
                    colors[i] = "#000000";
                }
            }
            for (int i = 1; i <= return_last_day_of_month(); i++) {
                if (return_which_day_is_linked_to_calender(i) == 1) {
                    if (calender_button_showing_shadow_1.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_1.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 2) {
                    if (calender_button_showing_shadow_2.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_2.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 3) {
                    if (calender_button_showing_shadow_3.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_3.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 4) {
                    if (calender_button_showing_shadow_4.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_4.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 5) {
                    if (calender_button_showing_shadow_5.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_5.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 6) {
                    if (calender_button_showing_shadow_6.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_6.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 7) {
                    if (calender_button_showing_shadow_7.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_7.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 8) {
                    if (calender_button_showing_shadow_8.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_8.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 9) {
                    if (calender_button_showing_shadow_9.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_9.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 10) {
                    if (calender_button_showing_shadow_10.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_10.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 11) {
                    if (calender_button_showing_shadow_11.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_11.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 12) {
                    if (calender_button_showing_shadow_12.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_12.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 13) {
                    if (calender_button_showing_shadow_13.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_13.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 14) {
                    if (calender_button_showing_shadow_14.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_14.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 15) {
                    if (calender_button_showing_shadow_15.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_15.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 16) {
                    if (calender_button_showing_shadow_16.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_16.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 17) {
                    if (calender_button_showing_shadow_17.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_17.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 18) {
                    if (calender_button_showing_shadow_18.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_18.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 19) {
                    if (calender_button_showing_shadow_19.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_19.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 20) {
                    if (calender_button_showing_shadow_20.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_20.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 21) {
                    if (calender_button_showing_shadow_21.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_21.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 22) {
                    if (calender_button_showing_shadow_22.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_22.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 23) {
                    if (calender_button_showing_shadow_23.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_23.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 24) {
                    if (calender_button_showing_shadow_24.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_24.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 25) {
                    if (calender_button_showing_shadow_25.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_25.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 26) {
                    if (calender_button_showing_shadow_26.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_26.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 27) {
                    if (calender_button_showing_shadow_27.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_27.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 28) {
                    if (calender_button_showing_shadow_28.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_28.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 29) {
                    if (calender_button_showing_shadow_29.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_29.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 30) {
                    if (calender_button_showing_shadow_30.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_30.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 31) {
                    if (calender_button_showing_shadow_31.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_31.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 32) {
                    if (calender_button_showing_shadow_32.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_32.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 33) {
                    if (calender_button_showing_shadow_33.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_33.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 34) {
                    if (calender_button_showing_shadow_34.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_34.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 35) {
                    if (calender_button_showing_shadow_35.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_35.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 36) {
                    if (calender_button_showing_shadow_36.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_36.setTextColor(Color.parseColor(colors[i]));
                    }
                } else {
                    if (calender_button_showing_shadow_37.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_37.setTextColor(Color.parseColor(colors[i]));
                    }
                }
            }
        }
    }

    private void check_if_date_is_future() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            String[] split_for_day_month_year = color_the_today.split("_");
            String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            int calender_day = Integer.parseInt(split_for_day_month_year[0]);
            int calender_month = return_month_string_to_int(month_and_year[0]);
            int calender_year = Integer.parseInt(month_and_year[1]);
            Calendar calendar = Calendar.getInstance();
            int real_year = calendar.get(Calendar.YEAR);
            int real_month = calendar.get(Calendar.MONTH);
            int real_day = calendar.get(Calendar.DAY_OF_MONTH);
            Calendar calendar_new = Calendar.getInstance();
            calendar_new.set(calender_year, calender_month, calender_day);
            String[] splitter_temp_from_text = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            String month_from_text = String.valueOf(return_month_string_to_int(splitter_temp_from_text[0]));
            String year_from_text = String.valueOf(splitter_temp_from_text[1]);
            String[] splitter_for_colored_value = color_the_today.split("_");
            if (calendar_new.getTimeInMillis() < Simplify_the_time.return_time_in_midnight(start_date)) {
                hide_or_un_hide_the_button(0);
            } else {
                if (calender_year > real_year) {
                    hide_or_un_hide_the_button(0);
                } else if (calender_year == real_year) {
                    if (calender_month > real_month) {
                        hide_or_un_hide_the_button(0);
                    } else if (calender_month == real_month) {
                        if (calender_day >= real_day) {
                            hide_or_un_hide_the_button(0);
                        } else {
                            if (month_from_text.equals(splitter_for_colored_value[1]) && year_from_text.equals(splitter_for_colored_value[2])) {
                                hide_or_un_hide_the_button(1);
                            } else {
                                hide_or_un_hide_the_button(0);
                            }
                        }
                    } else {
                        if (month_from_text.equals(splitter_for_colored_value[1]) && year_from_text.equals(splitter_for_colored_value[2])) {
                            hide_or_un_hide_the_button(1);
                        } else {
                            hide_or_un_hide_the_button(0);
                        }
                    }
                } else {
                    if (month_from_text.equals(splitter_for_colored_value[1]) && year_from_text.equals(splitter_for_colored_value[2])) {
                        hide_or_un_hide_the_button(1);
                    } else {
                        hide_or_un_hide_the_button(0);
                    }
                }
            }
        }
    }

    private void hide_or_un_hide_the_button(int which) {
        if (getView() != null && getContext() != null) {
            Button button_saying_yes_under_calender_in_good_habits = getView().findViewById(R.id.button_saying_yes_under_calender_in_good_habits);
            Button button_saying_no_under_calender_in_good_habits = getView().findViewById(R.id.button_saying_no_under_calender_in_good_habits);
            Button button_too_share_calender_in_good_habits = getView().findViewById(R.id.button_too_share_calender_in_good_habits);
            ConstraintLayout calender_in_stats = getView().findViewById(R.id.calender_in_stats);
            if (which == 0) {
                button_saying_yes_under_calender_in_good_habits.setVisibility(View.GONE);
                button_saying_no_under_calender_in_good_habits.setVisibility(View.GONE);
                ConstraintLayout constraintLayout = getView().findViewById(R.id.layout_containting_the_calender);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.clear(button_too_share_calender_in_good_habits.getId(), ConstraintSet.TOP);
                constraintSet.connect(button_too_share_calender_in_good_habits.getId(), ConstraintSet.TOP, calender_in_stats.getId(), ConstraintSet.TOP, (int) convertDpToPixel(return_the_length_of_stat() + 10, getContext()));
                constraintSet.applyTo(constraintLayout);
            } else {
                button_saying_yes_under_calender_in_good_habits.setVisibility(View.VISIBLE);
                button_saying_no_under_calender_in_good_habits.setVisibility(View.VISIBLE);
                ConstraintLayout constraintLayout = getView().findViewById(R.id.layout_containting_the_calender);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(button_saying_yes_under_calender_in_good_habits.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, (int) convertDpToPixel(return_the_length_of_stat() + 28, getContext()));
                constraintSet.connect(button_saying_no_under_calender_in_good_habits.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, (int) convertDpToPixel(return_the_length_of_stat() + 28, getContext()));
                constraintSet.connect(button_too_share_calender_in_good_habits.getId(), ConstraintSet.TOP, button_saying_no_under_calender_in_good_habits.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(7, getContext()));
                constraintSet.applyTo(constraintLayout);
            }
        }
    }

    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private int return_the_length_of_stat() {
        if (calender_button_showing_shadow_36.getText().toString().equals("")) {
            if (calender_button_showing_shadow_29.getText().toString().equals("")) {
                return 240;
            } else {
                return 280;
            }
        } else {
            return 320;
        }
    }

    private void set_up_share_and_yes_or_no_buttons() {
        if (getView() != null && getContext() != null) {
            Button button_saying_yes_under_calender_in_good_habits = getView().findViewById(R.id.button_saying_yes_under_calender_in_good_habits);
            Button button_saying_no_under_calender_in_good_habits = getView().findViewById(R.id.button_saying_no_under_calender_in_good_habits);
            Button button_too_share_calender_in_good_habits = getView().findViewById(R.id.button_too_share_calender_in_good_habits);
            ConstraintLayout calender_in_stats = getView().findViewById(R.id.calender_in_stats);
            ConstraintLayout constraintLayout = getView().findViewById(R.id.layout_containting_the_calender);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(button_saying_yes_under_calender_in_good_habits.getId(), ConstraintSet.TOP, calender_in_stats.getId(), ConstraintSet.TOP, (int) convertDpToPixel(return_the_length_of_stat() + 28, getContext()));
            constraintSet.connect(button_saying_no_under_calender_in_good_habits.getId(), ConstraintSet.TOP, calender_in_stats.getId(), ConstraintSet.TOP, (int) convertDpToPixel(return_the_length_of_stat() + 28, getContext()));
            constraintSet.clear(button_too_share_calender_in_good_habits.getId(), ConstraintSet.TOP);
            constraintSet.connect(button_too_share_calender_in_good_habits.getId(), ConstraintSet.TOP, calender_in_stats.getId(), ConstraintSet.TOP, (int) (convertDpToPixel(return_the_length_of_stat() + 10, getContext())));
            constraintSet.applyTo(constraintLayout);
        }
    }

    private void color_only_today() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            String[] split_the_month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            String[] split_the_color = color_the_today.split("_");
            int old_day = Integer.parseInt(split_the_color[0]);
            int old_month = Integer.parseInt(split_the_color[1]);
            int old_year = Integer.parseInt(split_the_color[2]);
            int new_month = return_month_string_to_int(split_the_month_and_year[0]);
            int new_year = Integer.parseInt(split_the_month_and_year[1]);
            if (old_month == new_month && old_year == new_year) {
                if (colors[old_day] != null) {
                    if (calender_button_showing_shadow_1.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_1.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_2.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_2.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_3.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_3.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_4.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_4.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_5.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_5.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_6.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_6.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_7.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_7.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_8.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_8.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_9.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_9.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_10.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_10.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_11.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_11.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_12.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_12.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_13.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_13.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_14.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_14.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_15.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_15.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_16.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_16.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_17.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_17.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_18.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_18.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_19.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_19.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_20.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_20.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_21.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_21.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_22.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_22.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_23.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_23.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_24.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_24.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_25.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_25.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_26.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_26.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_27.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_27.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_28.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_28.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_29.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_29.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_30.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_30.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_31.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_31.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_32.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_32.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_33.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_33.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_34.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_34.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_35.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_35.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_36.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_36.setTextColor(Color.parseColor(colors[old_day]));
                    } else {
                        calender_button_showing_shadow_37.setTextColor(Color.parseColor(colors[old_day]));
                    }
                }
            }
        }
    }

    private void clear_the_background_only() {
        calender_button_showing_shadow_1.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_2.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_3.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_4.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_5.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_6.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_7.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_8.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_9.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_10.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_11.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_12.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_13.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_14.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_15.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_16.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_17.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_18.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_19.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_20.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_21.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_22.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_23.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_24.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_25.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_26.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_27.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_28.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_29.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_30.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_31.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_32.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_33.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_34.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_35.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_36.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_37.setBackgroundResource(R.drawable.round_button);
    }

    private String check_past_now_or_future() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            String[] split_month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            int calender_month = return_month_string_to_int(split_month_and_year[0]);
            int calender_year = Integer.parseInt(split_month_and_year[1]);
            Calendar calendar = Calendar.getInstance();
            int real_year = calendar.get(Calendar.YEAR);
            int real_month = calendar.get(Calendar.MONTH);
            if (calender_year > real_year) {
                return "future";
            } else if (calender_year == real_year) {
                if (calender_month > real_month) {
                    return "future";
                } else if (calender_month == real_month) {
                    return "current";
                } else {
                    return "past";
                }
            } else {
                return "past";
            }
        } else {
            return "future";
        }
    }

    private void clear_all_the_text_from_calender() {
        calender_button_showing_shadow_1.setText("");
        calender_button_showing_shadow_2.setText("");
        calender_button_showing_shadow_3.setText("");
        calender_button_showing_shadow_4.setText("");
        calender_button_showing_shadow_5.setText("");
        calender_button_showing_shadow_6.setText("");
        calender_button_showing_shadow_7.setText("");
        calender_button_showing_shadow_8.setText("");
        calender_button_showing_shadow_9.setText("");
        calender_button_showing_shadow_10.setText("");
        calender_button_showing_shadow_11.setText("");
        calender_button_showing_shadow_12.setText("");
        calender_button_showing_shadow_13.setText("");
        calender_button_showing_shadow_14.setText("");
        calender_button_showing_shadow_15.setText("");
        calender_button_showing_shadow_16.setText("");
        calender_button_showing_shadow_17.setText("");
        calender_button_showing_shadow_18.setText("");
        calender_button_showing_shadow_19.setText("");
        calender_button_showing_shadow_20.setText("");
        calender_button_showing_shadow_21.setText("");
        calender_button_showing_shadow_22.setText("");
        calender_button_showing_shadow_23.setText("");
        calender_button_showing_shadow_24.setText("");
        calender_button_showing_shadow_25.setText("");
        calender_button_showing_shadow_26.setText("");
        calender_button_showing_shadow_27.setText("");
        calender_button_showing_shadow_28.setText("");
        calender_button_showing_shadow_29.setText("");
        calender_button_showing_shadow_30.setText("");
        calender_button_showing_shadow_31.setText("");
        calender_button_showing_shadow_32.setText("");
        calender_button_showing_shadow_33.setText("");
        calender_button_showing_shadow_34.setText("");
        calender_button_showing_shadow_35.setText("");
        calender_button_showing_shadow_36.setText("");
        calender_button_showing_shadow_37.setText("");
    }

    private void color_the_button_under_the_calender() {
        if (getView() != null) {
            Button button_saying_yes_under_calender_in_good_habits = getView().findViewById(R.id.button_saying_yes_under_calender_in_good_habits);
            Button button_saying_no_under_calender_in_good_habits = getView().findViewById(R.id.button_saying_no_under_calender_in_good_habits);
            CardView card_in_good_habits_habits = getView().findViewById(R.id.card_in_good_habits_habits);
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            String yes_color = "#06a94d";
            String yes_color_no_input = "#6a5acd";
            String no_color = "#FF2400";
            String no_color_no_input = "#000000";
            String[] splitter_temp = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            String month = String.valueOf(return_month_string_to_int(splitter_temp[0]));
            String year = String.valueOf(splitter_temp[1]);
            String[] splitter = color_the_today.split("_");
            if (month.equals(splitter[1]) && year.equals(splitter[2])) {
                if (colors[Integer.parseInt(splitter[0])].equals(yes_color)) {
                    if (!button_saying_yes_under_calender_in_good_habits.getText().toString().contains("✓")) {
                        button_saying_yes_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor()));
                        button_saying_yes_under_calender_in_good_habits.setTextColor(Color.WHITE);
                        button_saying_yes_under_calender_in_good_habits.setText(button_saying_yes_under_calender_in_good_habits.getText().toString().concat(" ✓"));
                        button_saying_no_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                        button_saying_no_under_calender_in_good_habits.setTextColor(Color.BLACK);
                        button_saying_no_under_calender_in_good_habits.setText(button_saying_no_under_calender_in_good_habits.getText().toString().replace(" ✓", ""));
                    }
                } else if (colors[Integer.parseInt(splitter[0])].equals(yes_color_no_input)) {
                    button_saying_yes_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                    button_saying_yes_under_calender_in_good_habits.setTextColor(Color.BLACK);
                    button_saying_yes_under_calender_in_good_habits.setText(button_saying_yes_under_calender_in_good_habits.getText().toString().replace(" ✓", ""));
                    button_saying_no_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                    button_saying_no_under_calender_in_good_habits.setTextColor(Color.BLACK);
                    button_saying_no_under_calender_in_good_habits.setText(button_saying_no_under_calender_in_good_habits.getText().toString().replace(" ✓", ""));
                } else if (colors[Integer.parseInt(splitter[0])].equals(no_color)) {
                    if (!button_saying_no_under_calender_in_good_habits.getText().toString().contains("✓")) {
                        button_saying_no_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor()));
                        button_saying_no_under_calender_in_good_habits.setTextColor(Color.WHITE);
                        button_saying_no_under_calender_in_good_habits.setText(button_saying_no_under_calender_in_good_habits.getText().toString().concat(" ✓"));
                        button_saying_yes_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                        button_saying_yes_under_calender_in_good_habits.setTextColor(Color.BLACK);
                        button_saying_yes_under_calender_in_good_habits.setText(button_saying_yes_under_calender_in_good_habits.getText().toString().replace(" ✓", ""));
                    }
                } else if (colors[Integer.parseInt(splitter[0])].equals(no_color_no_input)) {
                    button_saying_yes_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                    button_saying_yes_under_calender_in_good_habits.setTextColor(Color.BLACK);
                    button_saying_yes_under_calender_in_good_habits.setText(button_saying_yes_under_calender_in_good_habits.getText().toString().replace(" ✓", ""));
                    button_saying_no_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                    button_saying_no_under_calender_in_good_habits.setTextColor(Color.BLACK);
                    button_saying_no_under_calender_in_good_habits.setText(button_saying_no_under_calender_in_good_habits.getText().toString().replace(" ✓", ""));
                }
            }
        }
    }

    private void yes_and_no_button_listen_under_the_calender() {
        if (getView() != null) {
            final Button button_saying_yes_under_calender_in_good_habits = getView().findViewById(R.id.button_saying_yes_under_calender_in_good_habits);
            final Button button_saying_no_under_calender_in_good_habits = getView().findViewById(R.id.button_saying_no_under_calender_in_good_habits);
            final CardView card_in_good_habits_habits = getView().findViewById(R.id.card_in_good_habits_habits);
            final TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            button_saying_yes_under_calender_in_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!button_saying_yes_under_calender_in_good_habits.getText().toString().contains("✓")) {
                        String[] split_for_day_month_year = color_the_today.split("_");
                        String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                        int calender_day = Integer.parseInt(split_for_day_month_year[0]);
                        int calender_month = return_month_string_to_int(month_and_year[0]);
                        int calender_year = Integer.parseInt(month_and_year[1]);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(calender_year, calender_month, calender_day);
                        save_the_input_for_good_habit_input("yes", calendar.getTimeInMillis());
                        button_saying_yes_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor()));
                        button_saying_yes_under_calender_in_good_habits.setTextColor(Color.WHITE);
                        button_saying_yes_under_calender_in_good_habits.setText(button_saying_yes_under_calender_in_good_habits.getText().toString().concat(" ✓"));
                        button_saying_no_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                        button_saying_no_under_calender_in_good_habits.setTextColor(Color.BLACK);
                        button_saying_no_under_calender_in_good_habits.setText(button_saying_no_under_calender_in_good_habits.getText().toString().replace(" ✓", ""));
                        define_values_for_pre_card();
                        add_all_the_values_into_history_array_list();
                        set_the_text_for_in_card();
                        set_the_streak();
                        read_the_best_and_average_streak();
                        color_the_calender();
                        set_up_day_of_week_bar_chart();
                        clear_all_the_unders();
                        divide_it_into_weeks();
                        clear_the_middle();
                        make_the_middle_come_again();
                        draw_pie_chart();
                        line_chart_for_streak.fitScreen();
                        set_up_the_various_streak_chart();
                        setup_the_four_information_card();
                        set_the_leap_year();
                        put_values_into_year_in_good_habits();
                    }
                }
            });
            button_saying_no_under_calender_in_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!button_saying_no_under_calender_in_good_habits.getText().toString().contains("✓")) {
                        String[] split_for_day_month_year = color_the_today.split("_");
                        String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                        int calender_day = Integer.parseInt(split_for_day_month_year[0]);
                        int calender_month = return_month_string_to_int(month_and_year[0]);
                        int calender_year = Integer.parseInt(month_and_year[1]);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(calender_year, calender_month, calender_day);
                        save_the_input_for_good_habit_input("no", calendar.getTimeInMillis());
                        button_saying_no_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor()));
                        button_saying_no_under_calender_in_good_habits.setTextColor(Color.WHITE);
                        button_saying_no_under_calender_in_good_habits.setText(button_saying_no_under_calender_in_good_habits.getText().toString().concat(" ✓"));
                        button_saying_yes_under_calender_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                        button_saying_yes_under_calender_in_good_habits.setTextColor(Color.BLACK);
                        button_saying_yes_under_calender_in_good_habits.setText(button_saying_yes_under_calender_in_good_habits.getText().toString().replace(" ✓", ""));
                        define_values_for_pre_card();
                        add_all_the_values_into_history_array_list();
                        set_the_text_for_in_card();
                        set_the_streak();
                        read_the_best_and_average_streak();
                        color_the_calender();
                        set_up_day_of_week_bar_chart();
                        clear_all_the_unders();
                        divide_it_into_weeks();
                        clear_the_middle();
                        make_the_middle_come_again();
                        draw_pie_chart();
                        line_chart_for_streak.fitScreen();
                        set_up_the_various_streak_chart();
                        show_snack_bar(0);
                        setup_the_four_information_card();
                        set_the_leap_year();
                        put_values_into_year_in_good_habits();
                    }
                }
            });
        }
    }

    private void color_the_background() {
        if (getView() != null) {
            ConstraintLayout layout_inside_scroll_view_under_action_bar = getView().findViewById(R.id.layout_inside_scroll_view_under_action_bar);
            CardView card_in_good_habits_habits = getView().findViewById(R.id.card_in_good_habits_habits);
            layout_inside_scroll_view_under_action_bar.setBackgroundColor(ColorUtils.blendARGB(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor(), Color.WHITE, 0.9F));
        }
    }

    private String return_the_days_of_the_good_habit() {
        if (getView() != null) {
            TextView title_of_the_good_habit_in_card = getView().findViewById(R.id.title_of_the_good_habit_in_card);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits_save", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString(title_of_the_good_habit_in_card.getText().toString().toLowerCase(), "");
            if (shared != null) {
                String[] split_the_yes_or_no = shared.split("max_divide");
                int monday = 0;
                int tuesday = 0;
                int wednesday = 0;
                int thursday = 0;
                int friday = 0;
                int saturday = 0;
                int sunday = 0;
                for (int i = 0; i < split_the_yes_or_no.length; i++) {
                    if (split_the_yes_or_no[i].contains("yes")) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(Long.parseLong(split_the_yes_or_no[i].replace("yessmall_split", "")));
                        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                            monday = monday + 1;
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                            tuesday = tuesday + 1;
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                            wednesday = wednesday + 1;
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                            thursday = thursday + 1;
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                            friday = friday + 1;
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                            saturday = saturday + 1;
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                            sunday = sunday + 1;
                        }
                    }
                }
                return String.valueOf(monday).concat("split").concat(String.valueOf(tuesday)).concat("split").concat(String.valueOf(wednesday)).concat("split").concat(String.valueOf(thursday)).concat("split").concat(String.valueOf(friday)).concat("split").concat(String.valueOf(saturday)).concat("split").concat(String.valueOf(sunday));
            }
        }
        return "";
    }

    private void set_up_day_of_week_bar_chart() {
        if (getView() != null) {
            CardView card_in_good_habits_habits = getView().findViewById(R.id.card_in_good_habits_habits);
            int max_days = 0;
            String days_of_week = return_the_days_of_the_good_habit();
            String[] split_days_of_week = days_of_week.split("split");
            for (int i = 0; i < split_days_of_week.length; i++) {
                if (max_days < Integer.parseInt(split_days_of_week[i])) {
                    max_days = Integer.parseInt(split_days_of_week[i]);
                }
            }
            final int max_days_final = max_days;
            BarChart cahrt_in_good_habits_about_how_many_times_for_each_days_of_week = getView().findViewById(R.id.cahrt_in_good_habits_about_how_many_times_for_each_days_of_week);
            CustomBarChartRenderer barChartRender = new CustomBarChartRenderer(cahrt_in_good_habits_about_how_many_times_for_each_days_of_week, cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAnimator(), cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getViewPortHandler());
            barChartRender.setRadius(8);
            cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setRenderer(barChartRender);
            List<BarEntry> entries = new ArrayList<>();
            String[] xAxisLables;
            if (return_first_day_of_week().equals("monday")) {
                xAxisLables = new String[]{"M", "T", "W", "T", "F", "S", "S"};
                entries.add(new BarEntry(0, Integer.parseInt(split_days_of_week[0])));
                entries.add(new BarEntry(1, Integer.parseInt(split_days_of_week[1])));
                entries.add(new BarEntry(2, Integer.parseInt(split_days_of_week[2])));
                entries.add(new BarEntry(3, Integer.parseInt(split_days_of_week[3])));
                entries.add(new BarEntry(4, Integer.parseInt(split_days_of_week[4])));
                entries.add(new BarEntry(5, Integer.parseInt(split_days_of_week[5])));
                entries.add(new BarEntry(6, Integer.parseInt(split_days_of_week[6])));
            } else if (return_first_day_of_week().equals("tuesday")) {
                xAxisLables = new String[]{"T", "W", "T", "F", "S", "S", "M"};
                entries.add(new BarEntry(0, Integer.parseInt(split_days_of_week[1])));
                entries.add(new BarEntry(1, Integer.parseInt(split_days_of_week[2])));
                entries.add(new BarEntry(2, Integer.parseInt(split_days_of_week[3])));
                entries.add(new BarEntry(3, Integer.parseInt(split_days_of_week[4])));
                entries.add(new BarEntry(4, Integer.parseInt(split_days_of_week[5])));
                entries.add(new BarEntry(5, Integer.parseInt(split_days_of_week[6])));
                entries.add(new BarEntry(6, Integer.parseInt(split_days_of_week[0])));
            } else if (return_first_day_of_week().equals("wednesday")) {
                xAxisLables = new String[]{"W", "T", "F", "S", "S", "M", "T"};
                entries.add(new BarEntry(0, Integer.parseInt(split_days_of_week[2])));
                entries.add(new BarEntry(1, Integer.parseInt(split_days_of_week[3])));
                entries.add(new BarEntry(2, Integer.parseInt(split_days_of_week[4])));
                entries.add(new BarEntry(3, Integer.parseInt(split_days_of_week[5])));
                entries.add(new BarEntry(4, Integer.parseInt(split_days_of_week[6])));
                entries.add(new BarEntry(5, Integer.parseInt(split_days_of_week[0])));
                entries.add(new BarEntry(6, Integer.parseInt(split_days_of_week[1])));
            } else if (return_first_day_of_week().equals("thursday")) {
                xAxisLables = new String[]{"T", "F", "S", "S", "M", "T", "W"};
                entries.add(new BarEntry(0, Integer.parseInt(split_days_of_week[3])));
                entries.add(new BarEntry(1, Integer.parseInt(split_days_of_week[4])));
                entries.add(new BarEntry(2, Integer.parseInt(split_days_of_week[5])));
                entries.add(new BarEntry(3, Integer.parseInt(split_days_of_week[6])));
                entries.add(new BarEntry(4, Integer.parseInt(split_days_of_week[0])));
                entries.add(new BarEntry(5, Integer.parseInt(split_days_of_week[1])));
                entries.add(new BarEntry(6, Integer.parseInt(split_days_of_week[2])));
            } else if (return_first_day_of_week().equals("friday")) {
                xAxisLables = new String[]{"F", "S", "S", "M", "T", "W", "T"};
                entries.add(new BarEntry(0, Integer.parseInt(split_days_of_week[4])));
                entries.add(new BarEntry(1, Integer.parseInt(split_days_of_week[5])));
                entries.add(new BarEntry(2, Integer.parseInt(split_days_of_week[6])));
                entries.add(new BarEntry(3, Integer.parseInt(split_days_of_week[0])));
                entries.add(new BarEntry(4, Integer.parseInt(split_days_of_week[1])));
                entries.add(new BarEntry(5, Integer.parseInt(split_days_of_week[2])));
                entries.add(new BarEntry(6, Integer.parseInt(split_days_of_week[3])));
            } else if (return_first_day_of_week().equals("saturday")) {
                xAxisLables = new String[]{"S", "S", "M", "T", "W", "T", "F"};
                entries.add(new BarEntry(0, Integer.parseInt(split_days_of_week[5])));
                entries.add(new BarEntry(1, Integer.parseInt(split_days_of_week[6])));
                entries.add(new BarEntry(2, Integer.parseInt(split_days_of_week[0])));
                entries.add(new BarEntry(3, Integer.parseInt(split_days_of_week[1])));
                entries.add(new BarEntry(4, Integer.parseInt(split_days_of_week[2])));
                entries.add(new BarEntry(5, Integer.parseInt(split_days_of_week[3])));
                entries.add(new BarEntry(6, Integer.parseInt(split_days_of_week[4])));
            } else {
                xAxisLables = new String[]{"S", "M", "T", "W", "T", "F", "S"};
                entries.add(new BarEntry(0, Integer.parseInt(split_days_of_week[6])));
                entries.add(new BarEntry(1, Integer.parseInt(split_days_of_week[0])));
                entries.add(new BarEntry(2, Integer.parseInt(split_days_of_week[1])));
                entries.add(new BarEntry(3, Integer.parseInt(split_days_of_week[2])));
                entries.add(new BarEntry(4, Integer.parseInt(split_days_of_week[3])));
                entries.add(new BarEntry(5, Integer.parseInt(split_days_of_week[4])));
                entries.add(new BarEntry(6, Integer.parseInt(split_days_of_week[5])));
            }
            cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLables));
            ValueFormatter valueFormatter = new ValueFormatter() { //value format here, here is the overridden method
                @Override
                public String getFormattedValue(float value) {
                    if (value == 0 || (value / max_days_final < 0.1)) {
                        return "";
                    } else {
                        return "" + (int) value;
                    }
                }
            };
            BarDataSet set = new BarDataSet(entries, "BarDataSet");
            set.setColors(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor());
            set.setValueTextColor(Color.WHITE);
            set.setValueTextSize(15);
            set.setValueTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            BarData data = new BarData(set);
            data.setValueFormatter(valueFormatter);
            data.setBarWidth(0.9f); // set custom bar width
            cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setData(data);
            cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.invalidate(); // refresh
            cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setScaleEnabled(false);
            cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getLegend().setEnabled(false);   // Hide the legend
            cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setExtraOffsets(0, 0, 0, 0);
            Description description = new Description();
            description.setText("");
            cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setDescription(description);
            XAxis xAxis = cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            data.setHighlightEnabled(false);
            data.setBarWidth(0.7f);
            cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisLeft().setAxisMinimum(0f);
            cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisRight().setAxisMinimum(0f);
            cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getXAxis().setDrawGridLines(false);
            cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisLeft().setDrawAxisLine(false);
            cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisRight().setDrawAxisLine(false);
            cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisLeft().setDrawLabels(false);
            cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisRight().setDrawLabels(false);
            cahrt_in_good_habits_about_how_many_times_for_each_days_of_week.setDrawValueAboveBar(false);
        }
    }

    private String return_first_day_of_week() {
        Calendar calendar = Calendar.getInstance();
        if (calendar.getFirstDayOfWeek() == Calendar.MONDAY) {
            return "monday";
        } else if (calendar.getFirstDayOfWeek() == Calendar.TUESDAY) {
            return "tuesday";
        } else if (calendar.getFirstDayOfWeek() == Calendar.WEDNESDAY) {
            return "wednesday";
        } else if (calendar.getFirstDayOfWeek() == Calendar.THURSDAY) {
            return "thursday";
        } else if (calendar.getFirstDayOfWeek() == Calendar.FRIDAY) {
            return "friday";
        } else if (calendar.getFirstDayOfWeek() == Calendar.SATURDAY) {
            return "saturday";
        } else {
            return "sunday";
        }
    }

    private void divide_it_into_weeks() {
        int month_start_day;
        if (calender_button_showing_shadow_1.getVisibility() == View.VISIBLE) {
            month_start_day = 7;
        } else if (calender_button_showing_shadow_2.getVisibility() == View.VISIBLE) {
            month_start_day = 6;
        } else if (calender_button_showing_shadow_3.getVisibility() == View.VISIBLE) {
            month_start_day = 5;
        } else if (calender_button_showing_shadow_4.getVisibility() == View.VISIBLE) {
            month_start_day = 4;
        } else if (calender_button_showing_shadow_5.getVisibility() == View.VISIBLE) {
            month_start_day = 3;
        } else if (calender_button_showing_shadow_6.getVisibility() == View.VISIBLE) {
            month_start_day = 2;
        } else {
            month_start_day = 1;
        }
        String[] splitter_read = return_the_state_of_the_days().split("split");
        String first_line_string = "";
        String second_line_string = "";
        String three_line_string = "";
        String four_line_string = "";
        String five_line_string = "";
        String six_line_string = "";
        for (int i = 0; i < month_start_day; i++) {
            first_line_string = first_line_string.concat(splitter_read[i]).concat("split");
        }
        for (int i = month_start_day; i < month_start_day + 7; i++) {
            second_line_string = second_line_string.concat(splitter_read[i]).concat("split");
        }
        for (int i = month_start_day + 7; i < month_start_day + 14; i++) {
            three_line_string = three_line_string.concat(splitter_read[i]).concat("split");
        }
        for (int i = month_start_day + 14; i < month_start_day + 21; i++) {
            four_line_string = four_line_string.concat(splitter_read[i]).concat("split");
        }
        int how_many_are_empty = return_last_day_of_month() + (7 - month_start_day);
        if (how_many_are_empty <= 35) {
            for (int i = month_start_day + 21; i < splitter_read.length; i++) {
                five_line_string = five_line_string.concat(splitter_read[i]).concat("split");
            }
        } else {
            for (int i = month_start_day + 21; i < month_start_day + 28; i++) {
                five_line_string = five_line_string.concat(splitter_read[i]).concat("split");
            }
            for (int i = month_start_day + 28; i < splitter_read.length; i++) {
                six_line_string = six_line_string.concat(splitter_read[i]).concat("split");
            }
        }
        set_the_first_line(first_line_string);
        set_the_second_line(second_line_string);
        set_the_third_line(three_line_string);
        set_the_fourth_line(four_line_string);
        set_the_fifth_line(five_line_string);
        set_the_sixth_line(six_line_string);
    }

    private String return_the_state_of_the_days() {
        if (getView() != null) {
            String month_info = "";
            String yes_color = "#06a94d";
            String yes_color_no_input = "#6a5acd";
            String no_color = "#FF2400";
            String no_color_no_input = "#000000";
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            Calendar calendar = Calendar.getInstance();
            Calendar real_calender = Calendar.getInstance();
            calendar.set(Calendar.MONTH, return_month_string_to_int(splitter[0]));
            calendar.set(Calendar.YEAR, Integer.parseInt(splitter[1]));
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            if (!check_past_now_or_future().equals("future")) {
                if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) < Simplify_the_time.return_time_in_midnight(start_date)) {
                    month_info = "empty".concat("split");
                } else {
                    if (return_the_last_day_of_last_month().equals("continue")) {
                        if (return_state_of_day(calendar.getTimeInMillis()).contains("yes") || return_state_of_day(calendar.getTimeInMillis()).contains("reject required")) {
                            month_info = "middle".concat("split");
                        } else {
                            month_info = "end".concat("split");
                        }
                    } else {
                        if (return_state_of_day(calendar.getTimeInMillis()).contains("yes") || return_state_of_day(calendar.getTimeInMillis()).contains("reject required")) {
                            month_info = "start".concat("split");
                        } else {
                            month_info = "beg_last".concat("split");
                        }
                    }
                }
                if (check_past_now_or_future().equals("current")) {
                    for (int i = 2; i <= return_last_day_of_month(); i++) {
                        calendar.set(Calendar.DAY_OF_MONTH, i);
                        if (real_calender.get(Calendar.DAY_OF_MONTH) < i || Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) < Simplify_the_time.return_time_in_midnight(start_date)) {
                            month_info = month_info.concat("empty").concat("split");
                        } else {
                            String substring = month_info.substring(month_info.length() - 7, month_info.length() - 5);
                            if (substring.equals("rt") || substring.equals("le")) {
                                if (return_state_of_day(calendar.getTimeInMillis()).contains("yes") || return_state_of_day(calendar.getTimeInMillis()).contains("reject required")) {
                                    month_info = month_info.concat("middle").concat("split");
                                } else {
                                    month_info = month_info.concat("end").concat("split");
                                }
                            } else {
                                if (return_state_of_day(calendar.getTimeInMillis()).contains("yes") || return_state_of_day(calendar.getTimeInMillis()).contains("reject required")) {
                                    month_info = month_info.concat("start").concat("split");
                                } else {
                                    month_info = month_info.concat("beg_last").concat("split");
                                }
                            }
                        }
                    }
                } else {
                    for (int i = 2; i <= return_last_day_of_month(); i++) {
                        calendar.set(Calendar.DAY_OF_MONTH, i);
                        if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) < Simplify_the_time.return_time_in_midnight(start_date)) {
                            month_info = month_info.concat("empty").concat("split");
                        } else {
                            String substring = month_info.substring(month_info.length() - 7, month_info.length() - 5);
                            if (substring.equals("rt") || substring.equals("le")) {
                                if (return_state_of_day(calendar.getTimeInMillis()).contains("yes") || return_state_of_day(calendar.getTimeInMillis()).contains("reject required")) {
                                    month_info = month_info.concat("middle").concat("split");
                                } else {
                                    month_info = month_info.concat("end").concat("split");
                                }
                            } else {
                                if (return_state_of_day(calendar.getTimeInMillis()).contains("yes") || return_state_of_day(calendar.getTimeInMillis()).contains("reject required")) {
                                    month_info = month_info.concat("start").concat("split");
                                } else {
                                    month_info = month_info.concat("beg_last").concat("split");
                                }
                            }
                        }
                    }
                }
            } else {
                for (int i = 1; i <= 31; i++) {
                    month_info = month_info.concat("empty").concat("split");
                }
            }
            return month_info;
        } else {
            return "";
        }
    }

    private String return_the_last_day_of_last_month() {
        Calendar calendar = Calendar.getInstance();
        TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
        String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
        int month = return_month_string_to_int(splitter[0]);
        int year = Integer.parseInt(splitter[1]);
        if (month == 0) {
            month = 11;
            year--;
        } else {
            month--;
        }
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        if (Simplify_the_time.return_time_in_midnight(start_date) <= Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis())) {
            if (return_state_of_day(calendar.getTimeInMillis()).contains("yes") || return_state_of_day(calendar.getTimeInMillis()).contains("reject required")) {
                return "continue";
            } else {
                return "start";
            }
        } else {
            return "start";
        }
    }

    private void set_the_first_line(String date) {
        if (getView() != null) {
            View calender_under_number_state_1 = getView().findViewById(R.id.calender_under_number_state_1);
            View calender_under_number_state_2 = getView().findViewById(R.id.calender_under_number_state_2);
            View calender_under_number_state_3 = getView().findViewById(R.id.calender_under_number_state_3);
            View calender_under_number_state_4 = getView().findViewById(R.id.calender_under_number_state_4);
            View calender_under_number_state_5 = getView().findViewById(R.id.calender_under_number_state_5);
            View calender_under_number_state_6 = getView().findViewById(R.id.calender_under_number_state_6);
            View calender_under_number_state_7 = getView().findViewById(R.id.calender_under_number_state_7);

            String[] date_split = date.split("split");
            int empty_length = 7 - date_split.length;
            for (int i = 0; i < empty_length; i++) {
                date = "empty".concat("split").concat(date);
            }
            String[] splitter_second = date.split("split");
            if (splitter_second[0].equals("empty")) {
                calender_under_number_state_1.setVisibility(View.INVISIBLE);
            } else if (splitter_second[0].equals("start")) {
                calender_under_number_state_1.setVisibility(View.VISIBLE);
                calender_under_number_state_1.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[0].equals("middle")) {
                calender_under_number_state_1.setVisibility(View.VISIBLE);
                calender_under_number_state_1.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[0].equals("end")) {
                calender_under_number_state_1.setVisibility(View.VISIBLE);
                calender_under_number_state_1.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[0].equals("beg_last")) {
                calender_under_number_state_1.setVisibility(View.VISIBLE);
                calender_under_number_state_1.setBackground(first_and_last_part_rectangle_calender);
            }
            if (splitter_second[1].equals("empty")) {
                calender_under_number_state_2.setVisibility(View.INVISIBLE);
            } else if (splitter_second[1].equals("start")) {
                calender_under_number_state_2.setVisibility(View.VISIBLE);
                calender_under_number_state_2.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[1].equals("middle")) {
                calender_under_number_state_2.setVisibility(View.VISIBLE);
                calender_under_number_state_2.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[1].equals("end")) {
                calender_under_number_state_2.setVisibility(View.VISIBLE);
                calender_under_number_state_2.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[1].equals("beg_last")) {
                calender_under_number_state_2.setVisibility(View.VISIBLE);
                calender_under_number_state_2.setBackground(first_and_last_part_rectangle_calender);
            }
            if (splitter_second[2].equals("empty")) {
                calender_under_number_state_3.setVisibility(View.INVISIBLE);
            } else if (splitter_second[2].equals("start")) {
                calender_under_number_state_3.setVisibility(View.VISIBLE);
                calender_under_number_state_3.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[2].equals("middle")) {
                calender_under_number_state_3.setVisibility(View.VISIBLE);
                calender_under_number_state_3.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[2].equals("end")) {
                calender_under_number_state_3.setVisibility(View.VISIBLE);
                calender_under_number_state_3.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[2].equals("beg_last")) {
                calender_under_number_state_3.setVisibility(View.VISIBLE);
                calender_under_number_state_3.setBackground(first_and_last_part_rectangle_calender);
            }
            if (splitter_second[3].equals("empty")) {
                calender_under_number_state_4.setVisibility(View.INVISIBLE);
            } else if (splitter_second[3].equals("start")) {
                calender_under_number_state_4.setVisibility(View.VISIBLE);
                calender_under_number_state_4.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[3].equals("middle")) {
                calender_under_number_state_4.setVisibility(View.VISIBLE);
                calender_under_number_state_4.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[3].equals("end")) {
                calender_under_number_state_4.setVisibility(View.VISIBLE);
                calender_under_number_state_4.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[3].equals("beg_last")) {
                calender_under_number_state_4.setVisibility(View.VISIBLE);
                calender_under_number_state_4.setBackground(first_and_last_part_rectangle_calender);
            }
            if (splitter_second[4].equals("empty")) {
                calender_under_number_state_5.setVisibility(View.INVISIBLE);
            } else if (splitter_second[4].equals("start")) {
                calender_under_number_state_5.setVisibility(View.VISIBLE);
                calender_under_number_state_5.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[4].equals("middle")) {
                calender_under_number_state_5.setVisibility(View.VISIBLE);
                calender_under_number_state_5.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[4].equals("end")) {
                calender_under_number_state_5.setVisibility(View.VISIBLE);
                calender_under_number_state_5.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[4].equals("beg_last")) {
                calender_under_number_state_5.setVisibility(View.VISIBLE);
                calender_under_number_state_5.setBackground(first_and_last_part_rectangle_calender);
            }
            if (splitter_second[5].equals("empty")) {
                calender_under_number_state_6.setVisibility(View.INVISIBLE);
            } else if (splitter_second[5].equals("start")) {
                calender_under_number_state_6.setVisibility(View.VISIBLE);
                calender_under_number_state_6.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[5].equals("middle")) {
                calender_under_number_state_6.setVisibility(View.VISIBLE);
                calender_under_number_state_6.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[5].equals("end")) {
                calender_under_number_state_6.setVisibility(View.VISIBLE);
                calender_under_number_state_6.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[5].equals("beg_last")) {
                calender_under_number_state_6.setVisibility(View.VISIBLE);
                calender_under_number_state_6.setBackground(first_and_last_part_rectangle_calender);
            }
            if (splitter_second[6].equals("empty")) {
                calender_under_number_state_7.setVisibility(View.INVISIBLE);
            } else if (splitter_second[6].equals("start")) {
                calender_under_number_state_7.setVisibility(View.VISIBLE);
                calender_under_number_state_7.setBackground(first_part_rectangle_calender);
            } else if (splitter_second[6].equals("middle")) {
                calender_under_number_state_7.setVisibility(View.VISIBLE);
                calender_under_number_state_7.setBackground(middle_part_rectangle_calender);
            } else if (splitter_second[6].equals("end")) {
                calender_under_number_state_7.setVisibility(View.VISIBLE);
                calender_under_number_state_7.setBackground(last_part_rectangle_calender);
            } else if (splitter_second[6].equals("beg_last")) {
                calender_under_number_state_7.setVisibility(View.VISIBLE);
                calender_under_number_state_7.setBackground(first_and_last_part_rectangle_calender);
            }
        }
    }

    private void set_the_second_line(String date) {
        if (getView() != null) {
            View calender_under_number_state_8 = getView().findViewById(R.id.calender_under_number_state_8);
            View calender_under_number_state_9 = getView().findViewById(R.id.calender_under_number_state_9);
            View calender_under_number_state_10 = getView().findViewById(R.id.calender_under_number_state_10);
            View calender_under_number_state_11 = getView().findViewById(R.id.calender_under_number_state_11);
            View calender_under_number_state_12 = getView().findViewById(R.id.calender_under_number_state_12);
            View calender_under_number_state_13 = getView().findViewById(R.id.calender_under_number_state_13);
            View calender_under_number_state_14 = getView().findViewById(R.id.calender_under_number_state_14);
            String[] date_split = date.split("split");
            if (date_split[0].equals("start")) {
                calender_under_number_state_8.setBackground(first_part_rectangle_calender);
            } else if (date_split[0].equals("middle")) {
                calender_under_number_state_8.setBackground(middle_part_rectangle_calender);
            } else if (date_split[0].equals("end")) {
                calender_under_number_state_8.setBackground(last_part_rectangle_calender);
            } else if (date_split[0].equals("beg_last")) {
                calender_under_number_state_8.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[1].equals("start")) {
                calender_under_number_state_9.setBackground(first_part_rectangle_calender);
            } else if (date_split[1].equals("middle")) {
                calender_under_number_state_9.setBackground(middle_part_rectangle_calender);
            } else if (date_split[1].equals("end")) {
                calender_under_number_state_9.setBackground(last_part_rectangle_calender);
            } else if (date_split[1].equals("beg_last")) {
                calender_under_number_state_9.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[2].equals("start")) {
                calender_under_number_state_10.setBackground(first_part_rectangle_calender);
            } else if (date_split[2].equals("middle")) {
                calender_under_number_state_10.setBackground(middle_part_rectangle_calender);
            } else if (date_split[2].equals("end")) {
                calender_under_number_state_10.setBackground(last_part_rectangle_calender);
            } else if (date_split[2].equals("beg_last")) {
                calender_under_number_state_10.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[3].equals("start")) {
                calender_under_number_state_11.setBackground(first_part_rectangle_calender);
            } else if (date_split[3].equals("middle")) {
                calender_under_number_state_11.setBackground(middle_part_rectangle_calender);
            } else if (date_split[3].equals("end")) {
                calender_under_number_state_11.setBackground(last_part_rectangle_calender);
            } else if (date_split[3].equals("beg_last")) {
                calender_under_number_state_11.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[4].equals("start")) {
                calender_under_number_state_12.setBackground(first_part_rectangle_calender);
            } else if (date_split[4].equals("middle")) {
                calender_under_number_state_12.setBackground(middle_part_rectangle_calender);
            } else if (date_split[4].equals("end")) {
                calender_under_number_state_12.setBackground(last_part_rectangle_calender);
            } else if (date_split[4].equals("beg_last")) {
                calender_under_number_state_12.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[5].equals("start")) {
                calender_under_number_state_13.setBackground(first_part_rectangle_calender);
            } else if (date_split[5].equals("middle")) {
                calender_under_number_state_13.setBackground(middle_part_rectangle_calender);
            } else if (date_split[5].equals("end")) {
                calender_under_number_state_13.setBackground(last_part_rectangle_calender);
            } else if (date_split[5].equals("beg_last")) {
                calender_under_number_state_13.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[6].equals("start")) {
                calender_under_number_state_14.setBackground(first_part_rectangle_calender);
            } else if (date_split[6].equals("middle")) {
                calender_under_number_state_14.setBackground(middle_part_rectangle_calender);
            } else if (date_split[6].equals("end")) {
                calender_under_number_state_14.setBackground(last_part_rectangle_calender);
            } else if (date_split[6].equals("beg_last")) {
                calender_under_number_state_14.setBackground(first_and_last_part_rectangle_calender);
            }
        }
    }

    private void set_the_third_line(String date) {
        if (getView() != null) {
            View calender_under_number_state_15 = getView().findViewById(R.id.calender_under_number_state_15);
            View calender_under_number_state_16 = getView().findViewById(R.id.calender_under_number_state_16);
            View calender_under_number_state_17 = getView().findViewById(R.id.calender_under_number_state_17);
            View calender_under_number_state_18 = getView().findViewById(R.id.calender_under_number_state_18);
            View calender_under_number_state_19 = getView().findViewById(R.id.calender_under_number_state_19);
            View calender_under_number_state_20 = getView().findViewById(R.id.calender_under_number_state_20);
            View calender_under_number_state_21 = getView().findViewById(R.id.calender_under_number_state_21);
            String[] date_split = date.split("split");
            if (date_split[0].equals("start")) {
                calender_under_number_state_15.setBackground(first_part_rectangle_calender);
            } else if (date_split[0].equals("middle")) {
                calender_under_number_state_15.setBackground(middle_part_rectangle_calender);
            } else if (date_split[0].equals("end")) {
                calender_under_number_state_15.setBackground(last_part_rectangle_calender);
            } else if (date_split[0].equals("beg_last")) {
                calender_under_number_state_15.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[1].equals("start")) {
                calender_under_number_state_16.setBackground(first_part_rectangle_calender);
            } else if (date_split[1].equals("middle")) {
                calender_under_number_state_16.setBackground(middle_part_rectangle_calender);
            } else if (date_split[1].equals("end")) {
                calender_under_number_state_16.setBackground(last_part_rectangle_calender);
            } else if (date_split[1].equals("beg_last")) {
                calender_under_number_state_16.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[2].equals("start")) {
                calender_under_number_state_17.setBackground(first_part_rectangle_calender);
            } else if (date_split[2].equals("middle")) {
                calender_under_number_state_17.setBackground(middle_part_rectangle_calender);
            } else if (date_split[2].equals("end")) {
                calender_under_number_state_17.setBackground(last_part_rectangle_calender);
            } else if (date_split[2].equals("beg_last")) {
                calender_under_number_state_17.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[3].equals("start")) {
                calender_under_number_state_18.setBackground(first_part_rectangle_calender);
            } else if (date_split[3].equals("middle")) {
                calender_under_number_state_18.setBackground(middle_part_rectangle_calender);
            } else if (date_split[3].equals("end")) {
                calender_under_number_state_18.setBackground(last_part_rectangle_calender);
            } else if (date_split[3].equals("beg_last")) {
                calender_under_number_state_18.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[4].equals("start")) {
                calender_under_number_state_19.setBackground(first_part_rectangle_calender);
            } else if (date_split[4].equals("middle")) {
                calender_under_number_state_19.setBackground(middle_part_rectangle_calender);
            } else if (date_split[4].equals("end")) {
                calender_under_number_state_19.setBackground(last_part_rectangle_calender);
            } else if (date_split[4].equals("beg_last")) {
                calender_under_number_state_19.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[5].equals("start")) {
                calender_under_number_state_20.setBackground(first_part_rectangle_calender);
            } else if (date_split[5].equals("middle")) {
                calender_under_number_state_20.setBackground(middle_part_rectangle_calender);
            } else if (date_split[5].equals("end")) {
                calender_under_number_state_20.setBackground(last_part_rectangle_calender);
            } else if (date_split[5].equals("beg_last")) {
                calender_under_number_state_20.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[6].equals("start")) {
                calender_under_number_state_21.setBackground(first_part_rectangle_calender);
            } else if (date_split[6].equals("middle")) {
                calender_under_number_state_21.setBackground(middle_part_rectangle_calender);
            } else if (date_split[6].equals("end")) {
                calender_under_number_state_21.setBackground(last_part_rectangle_calender);
            } else if (date_split[6].equals("beg_last")) {
                calender_under_number_state_21.setBackground(first_and_last_part_rectangle_calender);
            }
        }
    }

    private void set_the_fourth_line(String date) {
        if (getView() != null) {
            View calender_under_number_state_22 = getView().findViewById(R.id.calender_under_number_state_22);
            View calender_under_number_state_23 = getView().findViewById(R.id.calender_under_number_state_23);
            View calender_under_number_state_24 = getView().findViewById(R.id.calender_under_number_state_24);
            View calender_under_number_state_25 = getView().findViewById(R.id.calender_under_number_state_25);
            View calender_under_number_state_26 = getView().findViewById(R.id.calender_under_number_state_26);
            View calender_under_number_state_27 = getView().findViewById(R.id.calender_under_number_state_27);
            View calender_under_number_state_28 = getView().findViewById(R.id.calender_under_number_state_28);
            String[] date_split = date.split("split");
            if (date_split[0].equals("start")) {
                calender_under_number_state_22.setBackground(first_part_rectangle_calender);
            } else if (date_split[0].equals("middle")) {
                calender_under_number_state_22.setBackground(middle_part_rectangle_calender);
            } else if (date_split[0].equals("end")) {
                calender_under_number_state_22.setBackground(last_part_rectangle_calender);
            } else if (date_split[0].equals("beg_last")) {
                calender_under_number_state_22.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[1].equals("start")) {
                calender_under_number_state_23.setBackground(first_part_rectangle_calender);
            } else if (date_split[1].equals("middle")) {
                calender_under_number_state_23.setBackground(middle_part_rectangle_calender);
            } else if (date_split[1].equals("end")) {
                calender_under_number_state_23.setBackground(last_part_rectangle_calender);
            } else if (date_split[1].equals("beg_last")) {
                calender_under_number_state_23.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[2].equals("start")) {
                calender_under_number_state_24.setBackground(first_part_rectangle_calender);
            } else if (date_split[2].equals("middle")) {
                calender_under_number_state_24.setBackground(middle_part_rectangle_calender);
            } else if (date_split[2].equals("end")) {
                calender_under_number_state_24.setBackground(last_part_rectangle_calender);
            } else if (date_split[2].equals("beg_last")) {
                calender_under_number_state_24.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[3].equals("start")) {
                calender_under_number_state_25.setBackground(first_part_rectangle_calender);
            } else if (date_split[3].equals("middle")) {
                calender_under_number_state_25.setBackground(middle_part_rectangle_calender);
            } else if (date_split[3].equals("end")) {
                calender_under_number_state_25.setBackground(last_part_rectangle_calender);
            } else if (date_split[3].equals("beg_last")) {
                calender_under_number_state_25.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[4].equals("start")) {
                calender_under_number_state_26.setBackground(first_part_rectangle_calender);
            } else if (date_split[4].equals("middle")) {
                calender_under_number_state_26.setBackground(middle_part_rectangle_calender);
            } else if (date_split[4].equals("end")) {
                calender_under_number_state_26.setBackground(last_part_rectangle_calender);
            } else if (date_split[4].equals("beg_last")) {
                calender_under_number_state_26.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[5].equals("start")) {
                calender_under_number_state_27.setBackground(first_part_rectangle_calender);
            } else if (date_split[5].equals("middle")) {
                calender_under_number_state_27.setBackground(middle_part_rectangle_calender);
            } else if (date_split[5].equals("end")) {
                calender_under_number_state_27.setBackground(last_part_rectangle_calender);
            } else if (date_split[5].equals("beg_last")) {
                calender_under_number_state_27.setBackground(first_and_last_part_rectangle_calender);
            }
            if (date_split[6].equals("start")) {
                calender_under_number_state_28.setBackground(first_part_rectangle_calender);
            } else if (date_split[6].equals("middle")) {
                calender_under_number_state_28.setBackground(middle_part_rectangle_calender);
            } else if (date_split[6].equals("end")) {
                calender_under_number_state_28.setBackground(last_part_rectangle_calender);
            } else if (date_split[6].equals("beg_last")) {
                calender_under_number_state_28.setBackground(first_and_last_part_rectangle_calender);
            }
        }
    }

    private void set_the_fifth_line(String date) {
        if (getView() != null) {
            View calender_under_number_state_29 = getView().findViewById(R.id.calender_under_number_state_29);
            View calender_under_number_state_30 = getView().findViewById(R.id.calender_under_number_state_30);
            View calender_under_number_state_31 = getView().findViewById(R.id.calender_under_number_state_31);
            View calender_under_number_state_32 = getView().findViewById(R.id.calender_under_number_state_32);
            View calender_under_number_state_33 = getView().findViewById(R.id.calender_under_number_state_33);
            View calender_under_number_state_34 = getView().findViewById(R.id.calender_under_number_state_34);
            View calender_under_number_state_35 = getView().findViewById(R.id.calender_under_number_state_35);
            String[] date_split = date.split("split");
            if (date_split.length > 0) {
                if (date_split[0].equals("start")) {
                    calender_under_number_state_29.setBackground(first_part_rectangle_calender);
                } else if (date_split[0].equals("middle")) {
                    calender_under_number_state_29.setBackground(middle_part_rectangle_calender);
                } else if (date_split[0].equals("end")) {
                    calender_under_number_state_29.setBackground(last_part_rectangle_calender);
                } else if (date_split[0].equals("beg_last")) {
                    calender_under_number_state_29.setBackground(first_and_last_part_rectangle_calender);
                }
            }
            if (date_split.length > 1) {
                if (date_split[1].equals("start")) {
                    calender_under_number_state_30.setBackground(first_part_rectangle_calender);
                } else if (date_split[1].equals("middle")) {
                    calender_under_number_state_30.setBackground(middle_part_rectangle_calender);
                } else if (date_split[1].equals("end")) {
                    calender_under_number_state_30.setBackground(last_part_rectangle_calender);
                } else if (date_split[1].equals("beg_last")) {
                    calender_under_number_state_30.setBackground(first_and_last_part_rectangle_calender);
                }
            }
            if (date_split.length > 2) {
                if (date_split[2].equals("start")) {
                    calender_under_number_state_31.setBackground(first_part_rectangle_calender);
                } else if (date_split[2].equals("middle")) {
                    calender_under_number_state_31.setBackground(middle_part_rectangle_calender);
                } else if (date_split[2].equals("end")) {
                    calender_under_number_state_31.setBackground(last_part_rectangle_calender);
                } else if (date_split[2].equals("beg_last")) {
                    calender_under_number_state_31.setBackground(first_and_last_part_rectangle_calender);
                }
            }
            if (date_split.length > 3) {
                if (date_split[3].equals("start")) {
                    calender_under_number_state_32.setBackground(first_part_rectangle_calender);
                } else if (date_split[3].equals("middle")) {
                    calender_under_number_state_32.setBackground(middle_part_rectangle_calender);
                } else if (date_split[3].equals("end")) {
                    calender_under_number_state_32.setBackground(last_part_rectangle_calender);
                } else if (date_split[3].equals("beg_last")) {
                    calender_under_number_state_32.setBackground(first_and_last_part_rectangle_calender);
                }
            }
            if (date_split.length > 4) {
                if (date_split[4].equals("start")) {
                    calender_under_number_state_33.setBackground(first_part_rectangle_calender);
                } else if (date_split[4].equals("middle")) {
                    calender_under_number_state_33.setBackground(middle_part_rectangle_calender);
                } else if (date_split[4].equals("end")) {
                    calender_under_number_state_33.setBackground(last_part_rectangle_calender);
                } else if (date_split[4].equals("beg_last")) {
                    calender_under_number_state_33.setBackground(first_and_last_part_rectangle_calender);
                }
            }
            if (date_split.length > 5) {
                if (date_split[5].equals("start")) {
                    calender_under_number_state_34.setBackground(first_part_rectangle_calender);
                } else if (date_split[5].equals("middle")) {
                    calender_under_number_state_34.setBackground(middle_part_rectangle_calender);
                } else if (date_split[5].equals("end")) {
                    calender_under_number_state_34.setBackground(last_part_rectangle_calender);
                } else if (date_split[5].equals("beg_last")) {
                    calender_under_number_state_34.setBackground(first_and_last_part_rectangle_calender);
                }
            }
            if (date_split.length > 6) {
                if (date_split[6].equals("start")) {
                    calender_under_number_state_35.setBackground(first_part_rectangle_calender);
                } else if (date_split[6].equals("middle")) {
                    calender_under_number_state_35.setBackground(middle_part_rectangle_calender);
                } else if (date_split[6].equals("end")) {
                    calender_under_number_state_35.setBackground(last_part_rectangle_calender);
                } else if (date_split[6].equals("beg_last")) {
                    calender_under_number_state_35.setBackground(first_and_last_part_rectangle_calender);
                }
            }
        }
    }

    private void set_the_sixth_line(String date) {
        if (getView() != null) {
            View calender_under_number_state_36 = getView().findViewById(R.id.calender_under_number_state_36);
            View calender_under_number_state_37 = getView().findViewById(R.id.calender_under_number_state_37);
            String[] date_split = date.split("split");
            if (date_split.length > 0) {
                if (date_split[0].equals("start")) {
                    calender_under_number_state_36.setBackground(first_part_rectangle_calender);
                } else if (date_split[0].equals("middle")) {
                    calender_under_number_state_36.setBackground(middle_part_rectangle_calender);
                } else if (date_split[0].equals("end")) {
                    calender_under_number_state_36.setBackground(last_part_rectangle_calender);
                } else if (date_split[0].equals("beg_last")) {
                    calender_under_number_state_36.setBackground(first_and_last_part_rectangle_calender);
                }
            }
            if (date_split.length > 1) {
                if (date_split[1].equals("start")) {
                    calender_under_number_state_37.setBackground(first_part_rectangle_calender);
                } else if (date_split[1].equals("middle")) {
                    calender_under_number_state_37.setBackground(middle_part_rectangle_calender);
                } else if (date_split[1].equals("end")) {
                    calender_under_number_state_37.setBackground(last_part_rectangle_calender);
                } else if (date_split[1].equals("beg_last")) {
                    calender_under_number_state_37.setBackground(first_and_last_part_rectangle_calender);
                }
            }
        }
    }

    private void clear_all_the_unders() {
        if (getView() != null) {
            View calender_under_number_state_1 = getView().findViewById(R.id.calender_under_number_state_1);
            View calender_under_number_state_2 = getView().findViewById(R.id.calender_under_number_state_2);
            View calender_under_number_state_3 = getView().findViewById(R.id.calender_under_number_state_3);
            View calender_under_number_state_4 = getView().findViewById(R.id.calender_under_number_state_4);
            View calender_under_number_state_5 = getView().findViewById(R.id.calender_under_number_state_5);
            View calender_under_number_state_6 = getView().findViewById(R.id.calender_under_number_state_6);
            View calender_under_number_state_7 = getView().findViewById(R.id.calender_under_number_state_7);
            View calender_under_number_state_8 = getView().findViewById(R.id.calender_under_number_state_8);
            View calender_under_number_state_9 = getView().findViewById(R.id.calender_under_number_state_9);
            View calender_under_number_state_10 = getView().findViewById(R.id.calender_under_number_state_10);
            View calender_under_number_state_11 = getView().findViewById(R.id.calender_under_number_state_11);
            View calender_under_number_state_12 = getView().findViewById(R.id.calender_under_number_state_12);
            View calender_under_number_state_13 = getView().findViewById(R.id.calender_under_number_state_13);
            View calender_under_number_state_14 = getView().findViewById(R.id.calender_under_number_state_14);
            View calender_under_number_state_15 = getView().findViewById(R.id.calender_under_number_state_15);
            View calender_under_number_state_16 = getView().findViewById(R.id.calender_under_number_state_16);
            View calender_under_number_state_17 = getView().findViewById(R.id.calender_under_number_state_17);
            View calender_under_number_state_18 = getView().findViewById(R.id.calender_under_number_state_18);
            View calender_under_number_state_19 = getView().findViewById(R.id.calender_under_number_state_19);
            View calender_under_number_state_20 = getView().findViewById(R.id.calender_under_number_state_20);
            View calender_under_number_state_21 = getView().findViewById(R.id.calender_under_number_state_21);
            View calender_under_number_state_22 = getView().findViewById(R.id.calender_under_number_state_22);
            View calender_under_number_state_23 = getView().findViewById(R.id.calender_under_number_state_23);
            View calender_under_number_state_24 = getView().findViewById(R.id.calender_under_number_state_24);
            View calender_under_number_state_25 = getView().findViewById(R.id.calender_under_number_state_25);
            View calender_under_number_state_26 = getView().findViewById(R.id.calender_under_number_state_26);
            View calender_under_number_state_27 = getView().findViewById(R.id.calender_under_number_state_27);
            View calender_under_number_state_28 = getView().findViewById(R.id.calender_under_number_state_28);
            View calender_under_number_state_29 = getView().findViewById(R.id.calender_under_number_state_29);
            View calender_under_number_state_30 = getView().findViewById(R.id.calender_under_number_state_30);
            View calender_under_number_state_31 = getView().findViewById(R.id.calender_under_number_state_31);
            View calender_under_number_state_32 = getView().findViewById(R.id.calender_under_number_state_32);
            View calender_under_number_state_33 = getView().findViewById(R.id.calender_under_number_state_33);
            View calender_under_number_state_34 = getView().findViewById(R.id.calender_under_number_state_34);
            View calender_under_number_state_35 = getView().findViewById(R.id.calender_under_number_state_35);
            View calender_under_number_state_36 = getView().findViewById(R.id.calender_under_number_state_36);
            View calender_under_number_state_37 = getView().findViewById(R.id.calender_under_number_state_37);
            calender_under_number_state_1.setBackgroundResource(0);
            calender_under_number_state_2.setBackgroundResource(0);
            calender_under_number_state_3.setBackgroundResource(0);
            calender_under_number_state_4.setBackgroundResource(0);
            calender_under_number_state_5.setBackgroundResource(0);
            calender_under_number_state_6.setBackgroundResource(0);
            calender_under_number_state_7.setBackgroundResource(0);
            calender_under_number_state_8.setBackgroundResource(0);
            calender_under_number_state_9.setBackgroundResource(0);
            calender_under_number_state_10.setBackgroundResource(0);
            calender_under_number_state_11.setBackgroundResource(0);
            calender_under_number_state_12.setBackgroundResource(0);
            calender_under_number_state_13.setBackgroundResource(0);
            calender_under_number_state_14.setBackgroundResource(0);
            calender_under_number_state_15.setBackgroundResource(0);
            calender_under_number_state_16.setBackgroundResource(0);
            calender_under_number_state_17.setBackgroundResource(0);
            calender_under_number_state_18.setBackgroundResource(0);
            calender_under_number_state_19.setBackgroundResource(0);
            calender_under_number_state_20.setBackgroundResource(0);
            calender_under_number_state_21.setBackgroundResource(0);
            calender_under_number_state_22.setBackgroundResource(0);
            calender_under_number_state_23.setBackgroundResource(0);
            calender_under_number_state_24.setBackgroundResource(0);
            calender_under_number_state_25.setBackgroundResource(0);
            calender_under_number_state_26.setBackgroundResource(0);
            calender_under_number_state_27.setBackgroundResource(0);
            calender_under_number_state_28.setBackgroundResource(0);
            calender_under_number_state_29.setBackgroundResource(0);
            calender_under_number_state_30.setBackgroundResource(0);
            calender_under_number_state_31.setBackgroundResource(0);
            calender_under_number_state_32.setBackgroundResource(0);
            calender_under_number_state_33.setBackgroundResource(0);
            calender_under_number_state_34.setBackgroundResource(0);
            calender_under_number_state_35.setBackgroundResource(0);
            calender_under_number_state_36.setBackgroundResource(0);
            calender_under_number_state_37.setBackgroundResource(0);
        }
    }

    private void clear_the_middle() {
        if (getView() != null) {
            View middle_calender_1 = getView().findViewById(R.id.middle_calender_1);
            View middle_calender_2 = getView().findViewById(R.id.middle_calender_2);
            View middle_calender_3 = getView().findViewById(R.id.middle_calender_3);
            View middle_calender_4 = getView().findViewById(R.id.middle_calender_4);
            View middle_calender_5 = getView().findViewById(R.id.middle_calender_5);
            View middle_calender_6 = getView().findViewById(R.id.middle_calender_6);
            View middle_calender_7 = getView().findViewById(R.id.middle_calender_7);
            View middle_calender_8 = getView().findViewById(R.id.middle_calender_8);
            View middle_calender_9 = getView().findViewById(R.id.middle_calender_9);
            View middle_calender_10 = getView().findViewById(R.id.middle_calender_10);
            View middle_calender_11 = getView().findViewById(R.id.middle_calender_11);
            View middle_calender_12 = getView().findViewById(R.id.middle_calender_12);
            View middle_calender_13 = getView().findViewById(R.id.middle_calender_13);
            View middle_calender_14 = getView().findViewById(R.id.middle_calender_14);
            View middle_calender_15 = getView().findViewById(R.id.middle_calender_15);
            View middle_calender_16 = getView().findViewById(R.id.middle_calender_16);
            View middle_calender_17 = getView().findViewById(R.id.middle_calender_17);
            View middle_calender_18 = getView().findViewById(R.id.middle_calender_18);
            View middle_calender_19 = getView().findViewById(R.id.middle_calender_19);
            View middle_calender_20 = getView().findViewById(R.id.middle_calender_20);
            View middle_calender_21 = getView().findViewById(R.id.middle_calender_21);
            View middle_calender_22 = getView().findViewById(R.id.middle_calender_22);
            View middle_calender_23 = getView().findViewById(R.id.middle_calender_23);
            View middle_calender_24 = getView().findViewById(R.id.middle_calender_24);
            View middle_calender_25 = getView().findViewById(R.id.middle_calender_25);
            View middle_calender_26 = getView().findViewById(R.id.middle_calender_26);
            View middle_calender_27 = getView().findViewById(R.id.middle_calender_27);
            View middle_calender_28 = getView().findViewById(R.id.middle_calender_28);
            View middle_calender_29 = getView().findViewById(R.id.middle_calender_29);
            View middle_calender_30 = getView().findViewById(R.id.middle_calender_30);
            View middle_calender_31 = getView().findViewById(R.id.middle_calender_31);
            middle_calender_1.setVisibility(View.INVISIBLE);
            middle_calender_2.setVisibility(View.INVISIBLE);
            middle_calender_3.setVisibility(View.INVISIBLE);
            middle_calender_4.setVisibility(View.INVISIBLE);
            middle_calender_5.setVisibility(View.INVISIBLE);
            middle_calender_6.setVisibility(View.INVISIBLE);
            middle_calender_7.setVisibility(View.INVISIBLE);
            middle_calender_8.setVisibility(View.INVISIBLE);
            middle_calender_9.setVisibility(View.INVISIBLE);
            middle_calender_10.setVisibility(View.INVISIBLE);
            middle_calender_11.setVisibility(View.INVISIBLE);
            middle_calender_12.setVisibility(View.INVISIBLE);
            middle_calender_13.setVisibility(View.INVISIBLE);
            middle_calender_14.setVisibility(View.INVISIBLE);
            middle_calender_15.setVisibility(View.INVISIBLE);
            middle_calender_16.setVisibility(View.INVISIBLE);
            middle_calender_17.setVisibility(View.INVISIBLE);
            middle_calender_18.setVisibility(View.INVISIBLE);
            middle_calender_19.setVisibility(View.INVISIBLE);
            middle_calender_20.setVisibility(View.INVISIBLE);
            middle_calender_21.setVisibility(View.INVISIBLE);
            middle_calender_22.setVisibility(View.INVISIBLE);
            middle_calender_23.setVisibility(View.INVISIBLE);
            middle_calender_24.setVisibility(View.INVISIBLE);
            middle_calender_25.setVisibility(View.INVISIBLE);
            middle_calender_26.setVisibility(View.INVISIBLE);
            middle_calender_27.setVisibility(View.INVISIBLE);
            middle_calender_28.setVisibility(View.INVISIBLE);
            middle_calender_29.setVisibility(View.INVISIBLE);
            middle_calender_30.setVisibility(View.INVISIBLE);
            middle_calender_31.setVisibility(View.INVISIBLE);
        }
    }

    private void make_the_middle_come_again() {
        if (getView() != null) {
            View calender_under_number_state_1 = getView().findViewById(R.id.calender_under_number_state_1);
            View calender_under_number_state_2 = getView().findViewById(R.id.calender_under_number_state_2);
            View calender_under_number_state_3 = getView().findViewById(R.id.calender_under_number_state_3);
            View calender_under_number_state_4 = getView().findViewById(R.id.calender_under_number_state_4);
            View calender_under_number_state_5 = getView().findViewById(R.id.calender_under_number_state_5);
            View calender_under_number_state_6 = getView().findViewById(R.id.calender_under_number_state_6);
            View calender_under_number_state_8 = getView().findViewById(R.id.calender_under_number_state_8);
            View calender_under_number_state_9 = getView().findViewById(R.id.calender_under_number_state_9);
            View calender_under_number_state_10 = getView().findViewById(R.id.calender_under_number_state_10);
            View calender_under_number_state_11 = getView().findViewById(R.id.calender_under_number_state_11);
            View calender_under_number_state_12 = getView().findViewById(R.id.calender_under_number_state_12);
            View calender_under_number_state_13 = getView().findViewById(R.id.calender_under_number_state_13);
            View calender_under_number_state_15 = getView().findViewById(R.id.calender_under_number_state_15);
            View calender_under_number_state_16 = getView().findViewById(R.id.calender_under_number_state_16);
            View calender_under_number_state_17 = getView().findViewById(R.id.calender_under_number_state_17);
            View calender_under_number_state_18 = getView().findViewById(R.id.calender_under_number_state_18);
            View calender_under_number_state_19 = getView().findViewById(R.id.calender_under_number_state_19);
            View calender_under_number_state_20 = getView().findViewById(R.id.calender_under_number_state_20);
            View calender_under_number_state_22 = getView().findViewById(R.id.calender_under_number_state_22);
            View calender_under_number_state_23 = getView().findViewById(R.id.calender_under_number_state_23);
            View calender_under_number_state_24 = getView().findViewById(R.id.calender_under_number_state_24);
            View calender_under_number_state_25 = getView().findViewById(R.id.calender_under_number_state_25);
            View calender_under_number_state_26 = getView().findViewById(R.id.calender_under_number_state_26);
            View calender_under_number_state_27 = getView().findViewById(R.id.calender_under_number_state_27);
            View calender_under_number_state_29 = getView().findViewById(R.id.calender_under_number_state_29);
            View calender_under_number_state_30 = getView().findViewById(R.id.calender_under_number_state_30);
            View calender_under_number_state_31 = getView().findViewById(R.id.calender_under_number_state_31);
            View calender_under_number_state_32 = getView().findViewById(R.id.calender_under_number_state_32);
            View calender_under_number_state_33 = getView().findViewById(R.id.calender_under_number_state_33);
            View calender_under_number_state_34 = getView().findViewById(R.id.calender_under_number_state_34);
            View calender_under_number_state_36 = getView().findViewById(R.id.calender_under_number_state_36);
            View middle_calender_1 = getView().findViewById(R.id.middle_calender_1);
            View middle_calender_2 = getView().findViewById(R.id.middle_calender_2);
            View middle_calender_3 = getView().findViewById(R.id.middle_calender_3);
            View middle_calender_4 = getView().findViewById(R.id.middle_calender_4);
            View middle_calender_5 = getView().findViewById(R.id.middle_calender_5);
            View middle_calender_6 = getView().findViewById(R.id.middle_calender_6);
            View middle_calender_7 = getView().findViewById(R.id.middle_calender_7);
            View middle_calender_8 = getView().findViewById(R.id.middle_calender_8);
            View middle_calender_9 = getView().findViewById(R.id.middle_calender_9);
            View middle_calender_10 = getView().findViewById(R.id.middle_calender_10);
            View middle_calender_11 = getView().findViewById(R.id.middle_calender_11);
            View middle_calender_12 = getView().findViewById(R.id.middle_calender_12);
            View middle_calender_13 = getView().findViewById(R.id.middle_calender_13);
            View middle_calender_14 = getView().findViewById(R.id.middle_calender_14);
            View middle_calender_15 = getView().findViewById(R.id.middle_calender_15);
            View middle_calender_16 = getView().findViewById(R.id.middle_calender_16);
            View middle_calender_17 = getView().findViewById(R.id.middle_calender_17);
            View middle_calender_18 = getView().findViewById(R.id.middle_calender_18);
            View middle_calender_19 = getView().findViewById(R.id.middle_calender_19);
            View middle_calender_20 = getView().findViewById(R.id.middle_calender_20);
            View middle_calender_21 = getView().findViewById(R.id.middle_calender_21);
            View middle_calender_22 = getView().findViewById(R.id.middle_calender_22);
            View middle_calender_23 = getView().findViewById(R.id.middle_calender_23);
            View middle_calender_24 = getView().findViewById(R.id.middle_calender_24);
            View middle_calender_25 = getView().findViewById(R.id.middle_calender_25);
            View middle_calender_26 = getView().findViewById(R.id.middle_calender_26);
            View middle_calender_27 = getView().findViewById(R.id.middle_calender_27);
            View middle_calender_28 = getView().findViewById(R.id.middle_calender_28);
            View middle_calender_29 = getView().findViewById(R.id.middle_calender_29);
            View middle_calender_30 = getView().findViewById(R.id.middle_calender_30);
            View middle_calender_31 = getView().findViewById(R.id.middle_calender_31);
            if (calender_under_number_state_1.getBackground() == first_part_rectangle_calender || calender_under_number_state_1.getBackground() == middle_part_rectangle_calender) {
                middle_calender_1.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_2.getBackground() == first_part_rectangle_calender || calender_under_number_state_2.getBackground() == middle_part_rectangle_calender) {
                middle_calender_2.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_3.getBackground() == first_part_rectangle_calender || calender_under_number_state_3.getBackground() == middle_part_rectangle_calender) {
                middle_calender_3.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_4.getBackground() == first_part_rectangle_calender || calender_under_number_state_4.getBackground() == middle_part_rectangle_calender) {
                middle_calender_4.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_5.getBackground() == first_part_rectangle_calender || calender_under_number_state_5.getBackground() == middle_part_rectangle_calender) {
                middle_calender_5.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_6.getBackground() == first_part_rectangle_calender || calender_under_number_state_6.getBackground() == middle_part_rectangle_calender) {
                middle_calender_6.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_8.getBackground() == first_part_rectangle_calender || calender_under_number_state_8.getBackground() == middle_part_rectangle_calender) {
                middle_calender_7.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_9.getBackground() == first_part_rectangle_calender || calender_under_number_state_9.getBackground() == middle_part_rectangle_calender) {
                middle_calender_8.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_10.getBackground() == first_part_rectangle_calender || calender_under_number_state_10.getBackground() == middle_part_rectangle_calender) {
                middle_calender_9.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_11.getBackground() == first_part_rectangle_calender || calender_under_number_state_11.getBackground() == middle_part_rectangle_calender) {
                middle_calender_10.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_12.getBackground() == first_part_rectangle_calender || calender_under_number_state_12.getBackground() == middle_part_rectangle_calender) {
                middle_calender_11.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_13.getBackground() == first_part_rectangle_calender || calender_under_number_state_13.getBackground() == middle_part_rectangle_calender) {
                middle_calender_12.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_15.getBackground() == first_part_rectangle_calender || calender_under_number_state_15.getBackground() == middle_part_rectangle_calender) {
                middle_calender_13.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_16.getBackground() == first_part_rectangle_calender || calender_under_number_state_16.getBackground() == middle_part_rectangle_calender) {
                middle_calender_14.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_17.getBackground() == first_part_rectangle_calender || calender_under_number_state_17.getBackground() == middle_part_rectangle_calender) {
                middle_calender_15.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_18.getBackground() == first_part_rectangle_calender || calender_under_number_state_18.getBackground() == middle_part_rectangle_calender) {
                middle_calender_16.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_19.getBackground() == first_part_rectangle_calender || calender_under_number_state_19.getBackground() == middle_part_rectangle_calender) {
                middle_calender_17.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_20.getBackground() == first_part_rectangle_calender || calender_under_number_state_20.getBackground() == middle_part_rectangle_calender) {
                middle_calender_18.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_22.getBackground() == first_part_rectangle_calender || calender_under_number_state_22.getBackground() == middle_part_rectangle_calender) {
                middle_calender_19.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_23.getBackground() == first_part_rectangle_calender || calender_under_number_state_23.getBackground() == middle_part_rectangle_calender) {
                middle_calender_20.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_24.getBackground() == first_part_rectangle_calender || calender_under_number_state_24.getBackground() == middle_part_rectangle_calender) {
                middle_calender_21.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_25.getBackground() == first_part_rectangle_calender || calender_under_number_state_25.getBackground() == middle_part_rectangle_calender) {
                middle_calender_22.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_26.getBackground() == first_part_rectangle_calender || calender_under_number_state_26.getBackground() == middle_part_rectangle_calender) {
                middle_calender_23.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_27.getBackground() == first_part_rectangle_calender || calender_under_number_state_27.getBackground() == middle_part_rectangle_calender) {
                middle_calender_24.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_29.getBackground() == first_part_rectangle_calender || calender_under_number_state_29.getBackground() == middle_part_rectangle_calender) {
                middle_calender_25.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_30.getBackground() == first_part_rectangle_calender || calender_under_number_state_30.getBackground() == middle_part_rectangle_calender) {
                middle_calender_26.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_31.getBackground() == first_part_rectangle_calender || calender_under_number_state_31.getBackground() == middle_part_rectangle_calender) {
                middle_calender_27.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_32.getBackground() == first_part_rectangle_calender || calender_under_number_state_32.getBackground() == middle_part_rectangle_calender) {
                middle_calender_28.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_33.getBackground() == first_part_rectangle_calender || calender_under_number_state_33.getBackground() == middle_part_rectangle_calender) {
                middle_calender_29.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_34.getBackground() == first_part_rectangle_calender || calender_under_number_state_34.getBackground() == middle_part_rectangle_calender) {
                middle_calender_30.setVisibility(View.VISIBLE);
            }
            if (calender_under_number_state_36.getBackground() == first_part_rectangle_calender || calender_under_number_state_36.getBackground() == middle_part_rectangle_calender) {
                middle_calender_31.setVisibility(View.VISIBLE);
            }
        }
    }

    private void color_the_4_drawables() {
        if (getContext() != null && getView() != null) {
            CardView card_in_good_habits_habits = getView().findViewById(R.id.card_in_good_habits_habits);
            LayerDrawable layerDrawable = (LayerDrawable) ContextCompat.getDrawable(getContext(), R.drawable.first_part_rectangle_calender);
            if (layerDrawable != null) {
                Drawable drawable1 = layerDrawable.getDrawable(1);
                drawable1.setTint(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor());
            }
            middle_part_rectangle_calender = ContextCompat.getDrawable(getContext(), R.drawable.middle_part_drawable_color);
            if (middle_part_rectangle_calender != null) {
                middle_part_rectangle_calender.setTint(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor());
            }
            LayerDrawable layerDrawable3 = (LayerDrawable) ContextCompat.getDrawable(getContext(), R.drawable.last_part_rectangle_calender);
            if (layerDrawable3 != null) {
                Drawable drawable1 = layerDrawable3.getDrawable(1);
                drawable1.setTint(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor());
            }
            LayerDrawable layerDrawable4 = (LayerDrawable) ContextCompat.getDrawable(getContext(), R.drawable.first_and_last_part_rectangle_calender);
            if (layerDrawable4 != null) {
                Drawable drawable1 = layerDrawable4.getDrawable(1);
                drawable1.setTint(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor());
            }
            first_part_rectangle_calender = ContextCompat.getDrawable(getContext(), R.drawable.first_part_rectangle_calender);
            last_part_rectangle_calender = ContextCompat.getDrawable(getContext(), R.drawable.last_part_rectangle_calender);
            first_and_last_part_rectangle_calender = ContextCompat.getDrawable(getContext(), R.drawable.first_and_last_part_rectangle_calender);
        }
    }

    private void color_the_middle() {
        if (getView() != null) {
            CardView card_in_good_habits_habits = getView().findViewById(R.id.card_in_good_habits_habits);
            int color = card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor();
            View middle_calender_1 = getView().findViewById(R.id.middle_calender_1);
            View middle_calender_2 = getView().findViewById(R.id.middle_calender_2);
            View middle_calender_3 = getView().findViewById(R.id.middle_calender_3);
            View middle_calender_4 = getView().findViewById(R.id.middle_calender_4);
            View middle_calender_5 = getView().findViewById(R.id.middle_calender_5);
            View middle_calender_6 = getView().findViewById(R.id.middle_calender_6);
            View middle_calender_7 = getView().findViewById(R.id.middle_calender_7);
            View middle_calender_8 = getView().findViewById(R.id.middle_calender_8);
            View middle_calender_9 = getView().findViewById(R.id.middle_calender_9);
            View middle_calender_10 = getView().findViewById(R.id.middle_calender_10);
            View middle_calender_11 = getView().findViewById(R.id.middle_calender_11);
            View middle_calender_12 = getView().findViewById(R.id.middle_calender_12);
            View middle_calender_13 = getView().findViewById(R.id.middle_calender_13);
            View middle_calender_14 = getView().findViewById(R.id.middle_calender_14);
            View middle_calender_15 = getView().findViewById(R.id.middle_calender_15);
            View middle_calender_16 = getView().findViewById(R.id.middle_calender_16);
            View middle_calender_17 = getView().findViewById(R.id.middle_calender_17);
            View middle_calender_18 = getView().findViewById(R.id.middle_calender_18);
            View middle_calender_19 = getView().findViewById(R.id.middle_calender_19);
            View middle_calender_20 = getView().findViewById(R.id.middle_calender_20);
            View middle_calender_21 = getView().findViewById(R.id.middle_calender_21);
            View middle_calender_22 = getView().findViewById(R.id.middle_calender_22);
            View middle_calender_23 = getView().findViewById(R.id.middle_calender_23);
            View middle_calender_24 = getView().findViewById(R.id.middle_calender_24);
            View middle_calender_25 = getView().findViewById(R.id.middle_calender_25);
            View middle_calender_26 = getView().findViewById(R.id.middle_calender_26);
            View middle_calender_27 = getView().findViewById(R.id.middle_calender_27);
            View middle_calender_28 = getView().findViewById(R.id.middle_calender_28);
            View middle_calender_29 = getView().findViewById(R.id.middle_calender_29);
            View middle_calender_30 = getView().findViewById(R.id.middle_calender_30);
            View middle_calender_31 = getView().findViewById(R.id.middle_calender_31);
            middle_calender_1.setBackgroundColor(color);
            middle_calender_2.setBackgroundColor(color);
            middle_calender_3.setBackgroundColor(color);
            middle_calender_4.setBackgroundColor(color);
            middle_calender_5.setBackgroundColor(color);
            middle_calender_6.setBackgroundColor(color);
            middle_calender_7.setBackgroundColor(color);
            middle_calender_8.setBackgroundColor(color);
            middle_calender_9.setBackgroundColor(color);
            middle_calender_10.setBackgroundColor(color);
            middle_calender_11.setBackgroundColor(color);
            middle_calender_12.setBackgroundColor(color);
            middle_calender_13.setBackgroundColor(color);
            middle_calender_14.setBackgroundColor(color);
            middle_calender_15.setBackgroundColor(color);
            middle_calender_16.setBackgroundColor(color);
            middle_calender_17.setBackgroundColor(color);
            middle_calender_18.setBackgroundColor(color);
            middle_calender_19.setBackgroundColor(color);
            middle_calender_20.setBackgroundColor(color);
            middle_calender_21.setBackgroundColor(color);
            middle_calender_22.setBackgroundColor(color);
            middle_calender_23.setBackgroundColor(color);
            middle_calender_24.setBackgroundColor(color);
            middle_calender_25.setBackgroundColor(color);
            middle_calender_26.setBackgroundColor(color);
            middle_calender_27.setBackgroundColor(color);
            middle_calender_28.setBackgroundColor(color);
            middle_calender_29.setBackgroundColor(color);
            middle_calender_30.setBackgroundColor(color);
            middle_calender_31.setBackgroundColor(color);
        }
    }

    private String return_info_about_pie() {
        if (getView() != null && getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits_save", Context.MODE_PRIVATE);
            TextView title_of_the_good_habit_in_card = getView().findViewById(R.id.title_of_the_good_habit_in_card);
            String old = sharedPreferences.getString(title_of_the_good_habit_in_card.getText().toString().toLowerCase(), "");
            if (old != null) {
                String[] split_yes_no = old.split("max_divide");
                int yes = 0;
                int no = 0;
                if (!old.equals("")) {
                    for (int i = 0; i < split_yes_no.length; i++) {
                        if (split_yes_no[i].contains("yes")) {
                            yes++;
                        } else {
                            no++;
                        }
                    }
                    return String.valueOf(yes).concat("split").concat(String.valueOf(no));
                } else {
                    return "0split0";
                }
            } else {
                return "0split0";
            }
        } else {
            return "";
        }
    }

    private void draw_pie_chart() {
        if (getView() != null) {
            PieChart mChart = getView().findViewById(R.id.chart_to_show_pie_of_yes_or_no_pie);
            CardView card_in_good_habits_habits = getView().findViewById(R.id.card_in_good_habits_habits);
            TextView text_view_saying_that_there_is_not_enough_data_to_draw_this_chart = getView().findViewById(R.id.text_view_saying_that_there_is_not_enough_data_to_draw_this_chart);
            Am_i_paid am_i_paid = new Am_i_paid(getContext());
            if (am_i_paid.did_user_pay()) {
                String[] split_yes_no = return_info_about_pie().split("split");
                if (Integer.parseInt(split_yes_no[0]) > 0 || Integer.parseInt(split_yes_no[1]) > 0) {
                    mChart.setVisibility(View.VISIBLE);
                    text_view_saying_that_there_is_not_enough_data_to_draw_this_chart.setVisibility(View.INVISIBLE);
                    List<PieEntry> pieChartEntries = new ArrayList<>();
                    List<LegendEntry> entries = new ArrayList<>();
                    LegendEntry entry = new LegendEntry();
                    LegendEntry entry2 = new LegendEntry();
                    if (Integer.parseInt(split_yes_no[0]) > 0) {
                        pieChartEntries.add(new PieEntry(Float.parseFloat(split_yes_no[0]), String.valueOf(split_yes_no[0]).concat(" Yes")));
                        entry.formColor = card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor();
                        entry.label = "Yes";
                        entries.add(entry);
                    }
                    if (Integer.parseInt(split_yes_no[1]) > 0) {
                        pieChartEntries.add(new PieEntry(Float.parseFloat(split_yes_no[1]), String.valueOf(split_yes_no[1]).concat(" No")));
                        entry2.formColor = ColorUtils.blendARGB(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor(), Color.RED, 0.7F);
                        entry2.label = "No";
                        entries.add(entry2);
                    }
                    mChart.setEntryLabelColor(Color.WHITE);
                    mChart.setEntryLabelTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    mChart.setCenterTextTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    Legend l = mChart.getLegend();
                    l.setCustom(entries);
                    PieDataSet set = new PieDataSet(pieChartEntries, "");
                    PieData data = new PieData(set);
                    int total = Integer.parseInt(split_yes_no[0]) + Integer.parseInt(split_yes_no[1]);
                    mChart.setCenterText(String.valueOf(total).concat(" days"));
                    mChart.setCenterTextSize(16.5f);
                    mChart.setCenterTextColor(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor());
                    mChart.setData(data);
                    if (Integer.parseInt(split_yes_no[0]) > 0 && Integer.parseInt(split_yes_no[1]) > 0) {
                        set.setColors(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor(), ColorUtils.blendARGB(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor(), Color.RED, 0.7F));
                    } else {
                        if (Integer.parseInt(split_yes_no[0]) > 0) {
                            set.setColors(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor());
                        }
                        if (Integer.parseInt(split_yes_no[1]) > 0) {
                            set.setColors(ColorUtils.blendARGB(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor(), Color.RED, 0.7F));
                        }
                    }
                    set.setDrawValues(false);
                    mChart.getDescription().setEnabled(false);
                    mChart.invalidate();
                } else {
                    mChart.setVisibility(View.INVISIBLE);
                    text_view_saying_that_there_is_not_enough_data_to_draw_this_chart.setVisibility(View.VISIBLE);
                }
            } else {
                Random random = new Random();
                int yes = random.nextInt(100) + 1;
                int no = random.nextInt(100) + 1;
                mChart.setVisibility(View.VISIBLE);
                text_view_saying_that_there_is_not_enough_data_to_draw_this_chart.setVisibility(View.INVISIBLE);
                List<PieEntry> pieChartEntries = new ArrayList<>();
                List<LegendEntry> entries = new ArrayList<>();
                LegendEntry entry = new LegendEntry();
                LegendEntry entry2 = new LegendEntry();
                pieChartEntries.add(new PieEntry((float) yes, String.valueOf(yes).concat(" Yes")));
                entry.formColor = card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor();
                entry.label = "Yes";
                entries.add(entry);
                pieChartEntries.add(new PieEntry((float) no, String.valueOf(no).concat(" No")));
                entry2.formColor = ColorUtils.blendARGB(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor(), Color.RED, 0.7F);
                entry2.label = "No";
                entries.add(entry2);
                mChart.setEntryLabelColor(Color.WHITE);
                mChart.setEntryLabelTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                mChart.setCenterTextTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                Legend l = mChart.getLegend();
                l.setCustom(entries);
                PieDataSet set = new PieDataSet(pieChartEntries, "");
                PieData data = new PieData(set);
                int total = yes + no;
                mChart.setCenterText(String.valueOf(total).concat(" days"));
                mChart.setCenterTextSize(16.5f);
                mChart.setCenterTextColor(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor());
                mChart.setData(data);
                set.setColors(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor(), ColorUtils.blendARGB(card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor(), Color.RED, 0.7F));
                set.setDrawValues(false);
                mChart.getDescription().setEnabled(false);
                mChart.invalidate();
            }
        }
    }

    private String return_the_line_for_the_data_chart() {
        String return_me = "";
        for (int i = 0; i < streaks_information.size(); i++) {
            return_me = return_me.concat(String.valueOf(streaks_information.get(i))).concat("split");
        }
        return return_me;
    }

    private void set_up_the_various_streak_chart() {
        if (getView() != null) {
            CardView card_in_good_habits_habits = getView().findViewById(R.id.card_in_good_habits_habits);
            line_chart_for_streak = getView().findViewById(R.id.cahrt_showing_various_chart_length_for_different_streaks);
            int color_card = card_in_good_habits_habits.getCardBackgroundColor().getDefaultColor();
            Am_i_paid am_i_paid = new Am_i_paid(getContext());
            if (am_i_paid.did_user_pay()) {
                //process the data
                ArrayList<Entry> y_values = new ArrayList<>();
                String[] split_data = return_the_line_for_the_data_chart().split("split");
            /*int for_loop_value;
            if(10>=split_data.length){
                for_loop_value = 0;
            } else {
                for_loop_value = split_data.length-10;
            }
                for (int i = 0; i < split_data.length; i++) {
                    if (!split_data[i].equals("")) {
                        y_values.add(new Entry(i + 1, Integer.parseInt(split_data[i])));
                    }
                }

                line_chart_for_streak.setPadding(0, 0, 10, 0);
                //set grid lines
                line_chart_for_streak.getXAxis().setDrawGridLines(false);
                line_chart_for_streak.getAxisLeft().setDrawGridLines(true);
                line_chart_for_streak.getAxisRight().setDrawGridLines(false);

                //set x axis
                XAxis xAxis = line_chart_for_streak.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setLabelCount(y_values.size(), false);
                xAxis.setGranularity(1.0f);
                xAxis.setGranularityEnabled(true); // Required to enable granularity

                if (!return_the_line_for_the_data_chart().equals("")) {
                    int last_value = Integer.parseInt(split_data[split_data.length - 1]);
                    xAxis.setAxisMaximum(last_value + 0.1f);
                }
                // set description
                line_chart_for_streak.getDescription().setText("Each streak length in days");

                // disable stuff
                line_chart_for_streak.setScaleEnabled(false);
                line_chart_for_streak.getLegend().setEnabled(false);

                //control left and right axis
                line_chart_for_streak.getAxisRight().setEnabled(false);
                line_chart_for_streak.getAxisLeft().setAxisMinimum(0);
                line_chart_for_streak.getAxisLeft().setDrawAxisLine(false);
                line_chart_for_streak.getAxisLeft().setGranularity(1.0f);
                line_chart_for_streak.getAxisLeft().setGranularityEnabled(true); // Required to enable granularity

                LineDataSet lineDataSet = new LineDataSet(y_values, "data");
                lineDataSet.setColor(color_card);
                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(lineDataSet);
                LineData data = new LineData(dataSets);
                line_chart_for_streak.setData(data);
                line_chart_for_streak.getData().setHighlightEnabled(false);
                lineDataSet.setLineWidth(3f);
                lineDataSet.setCircleRadius(6f);
                lineDataSet.setCircleHoleRadius(3.5f);
                lineDataSet.setCircleHoleColor(color_card);
                lineDataSet.setCircleColor(Color.WHITE);
                lineDataSet.setDrawValues(false);
                line_chart_for_streak.invalidate();
                line_chart_for_streak.setVisibleXRangeMaximum(9);
                line_chart_for_streak.moveViewToX(y_values.size());
            } else {
                Random random = new Random();
                ArrayList<Entry> y_values = new ArrayList<>();
                int number_of_entries = random.nextInt(50) + 20;
                for (int i = 0; i < number_of_entries; i++) {
                    y_values.add(new Entry(i + 1, random.nextInt(25)));
                }

                line_chart_for_streak.setPadding(0, 0, 10, 0);
                //set grid lines
                line_chart_for_streak.getXAxis().setDrawGridLines(false);
                line_chart_for_streak.getAxisLeft().setDrawGridLines(true);
                line_chart_for_streak.getAxisRight().setDrawGridLines(false);

                //set x axis
                XAxis xAxis = line_chart_for_streak.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setLabelCount(y_values.size(), false);
                xAxis.setGranularity(1.0f);
                xAxis.setGranularityEnabled(true); // Required to enable granularity
                // set description
                line_chart_for_streak.getDescription().setText("Each streak length in days");

                // disable stuff
                line_chart_for_streak.setScaleEnabled(false);
                line_chart_for_streak.getLegend().setEnabled(false);

                //control left and right axis
                line_chart_for_streak.getAxisRight().setEnabled(false);
                line_chart_for_streak.getAxisLeft().setAxisMinimum(0);
                line_chart_for_streak.getAxisLeft().setDrawAxisLine(false);
                line_chart_for_streak.getAxisLeft().setGranularity(1.0f);
                line_chart_for_streak.getAxisLeft().setGranularityEnabled(true); // Required to enable granularity

                LineDataSet lineDataSet = new LineDataSet(y_values, "data");
                lineDataSet.setColor(color_card);
                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(lineDataSet);
                LineData data = new LineData(dataSets);
                line_chart_for_streak.setData(data);
                line_chart_for_streak.getData().setHighlightEnabled(false);
                lineDataSet.setLineWidth(3f);
                lineDataSet.setCircleRadius(6f);
                lineDataSet.setCircleHoleRadius(3.5f);
                lineDataSet.setCircleHoleColor(color_card);
                lineDataSet.setCircleColor(Color.WHITE);
                lineDataSet.setDrawValues(false);
                line_chart_for_streak.invalidate();
                line_chart_for_streak.setVisibleXRangeMaximum(9);
                line_chart_for_streak.moveViewToX(y_values.size());
            }
        }
    }

    private void watch_all_the_share_button() {
        if (getView() != null) {
            final Button button_too_share_calender_in_good_habits = getView().findViewById(R.id.button_too_share_calender_in_good_habits);
            final Button button_to_share_streak_in_good_habits = getView().findViewById(R.id.button_to_share_streak_in_good_habits);
            final Button button_to_share_goal_progress_good_habits = getView().findViewById(R.id.button_to_share_goal_progress_good_habits);
            final Button button_to_share_bar_chart = getView().findViewById(R.id.button_to_share_bar_chart);
            final Button button_to_share_pie_chart = getView().findViewById(R.id.button_to_share_pie_chart);
            final Button button_to_share_line_chart_of_various_streaks = getView().findViewById(R.id.button_to_share_line_chart_of_various_streaks);
            final Button sahre_button_for_the_foour_values_in_good_habits = getView().findViewById(R.id.sahre_button_for_the_foour_values_in_good_habits);
            final Button share_button_to_share_the_whole_year_in_the_good_habits = getView().findViewById(R.id.share_button_to_share_the_whole_year_in_the_good_habits);
            final CardView card_to_show_calender = getView().findViewById(R.id.card_to_show_calender);
            final CardView card_to_show_current_average_and_best_streak = getView().findViewById(R.id.card_to_show_current_average_and_best_streak);
            final CardView card_to_show_goal_progress_good_habits = getView().findViewById(R.id.card_to_show_goal_progress_good_habits);
            final CardView card_to_show_daily_input_by_week_of_days = getView().findViewById(R.id.card_to_show_daily_input_by_week_of_days);
            final CardView card_to_show_pie_chart_for_yes_or_no_over_the_days = getView().findViewById(R.id.card_to_show_pie_chart_for_yes_or_no_over_the_days);
            final CardView card_view_showing_multiple_streak_length = getView().findViewById(R.id.card_view_showing_multiple_streak_length);
            final CardView card_showingthe_four_values_day_week_month = getView().findViewById(R.id.card_showingthe_four_values_day_week_month);
            final CardView card_showing_the_habit_in_year_in_good_habits = getView().findViewById(R.id.card_showing_the_habit_in_year_in_good_habits);
            final Button button_saying_yes_under_calender_in_good_habits = getView().findViewById(R.id.button_saying_yes_under_calender_in_good_habits);
            button_too_share_calender_in_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (button_saying_yes_under_calender_in_good_habits.getVisibility() == View.VISIBLE) {
                        button_too_share_calender_in_good_habits.setVisibility(View.GONE);
                        hide_or_un_hide_the_button(0);
                        final ViewTreeObserver observer = button_saying_yes_under_calender_in_good_habits.getViewTreeObserver();
                        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                            @Override
                            public void onGlobalLayout() {
                                share_screen_shot(screenShot(card_to_show_calender));
                                button_too_share_calender_in_good_habits.setVisibility(View.VISIBLE);
                                hide_or_un_hide_the_button(1);
                                observer.removeOnGlobalLayoutListener(this);
                            }
                        });
                    } else {
                        button_too_share_calender_in_good_habits.setVisibility(View.INVISIBLE);
                        share_screen_shot(screenShot(card_to_show_calender));
                        button_too_share_calender_in_good_habits.setVisibility(View.VISIBLE);
                    }
                }
            });
            button_to_share_streak_in_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button_to_share_streak_in_good_habits.setVisibility(View.INVISIBLE);
                    share_screen_shot(screenShot(card_to_show_current_average_and_best_streak));
                    button_to_share_streak_in_good_habits.setVisibility(View.VISIBLE);
                }
            });
            button_to_share_goal_progress_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button_to_share_goal_progress_good_habits.setVisibility(View.INVISIBLE);
                    share_screen_shot(screenShot(card_to_show_goal_progress_good_habits));
                    button_to_share_goal_progress_good_habits.setVisibility(View.VISIBLE);
                }
            });
            button_to_share_bar_chart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button_to_share_bar_chart.setVisibility(View.INVISIBLE);
                    share_screen_shot(screenShot(card_to_show_daily_input_by_week_of_days));
                    button_to_share_bar_chart.setVisibility(View.VISIBLE);
                }
            });
            button_to_share_pie_chart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button_to_share_pie_chart.setVisibility(View.INVISIBLE);
                    share_screen_shot(screenShot(card_to_show_pie_chart_for_yes_or_no_over_the_days));
                    button_to_share_pie_chart.setVisibility(View.VISIBLE);
                }
            });
            button_to_share_line_chart_of_various_streaks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button_to_share_line_chart_of_various_streaks.setVisibility(View.INVISIBLE);
                    share_screen_shot(screenShot(card_view_showing_multiple_streak_length));
                    button_to_share_line_chart_of_various_streaks.setVisibility(View.VISIBLE);
                }
            });
            sahre_button_for_the_foour_values_in_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sahre_button_for_the_foour_values_in_good_habits.setVisibility(View.INVISIBLE);
                    share_screen_shot(screenShot(card_showingthe_four_values_day_week_month));
                    sahre_button_for_the_foour_values_in_good_habits.setVisibility(View.VISIBLE);
                }
            });
            share_button_to_share_the_whole_year_in_the_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    share_button_to_share_the_whole_year_in_the_good_habits.setVisibility(View.INVISIBLE);
                    share_screen_shot(screenShot(card_showing_the_habit_in_year_in_good_habits));
                    share_button_to_share_the_whole_year_in_the_good_habits.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    private Bitmap screenShot(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    //create bitmap from the ScrollView
    private Bitmap getBitmapFromView(View view, int height, int width) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;
    }

    private void share_screen_shot(Bitmap bitmap) {
        if (getView() != null && getContext() != null) {
            final ImageView view_to_pass_the_real_view_to_for_screen_shot = getView().findViewById(R.id.view_to_pass_the_real_view_to_for_screen_shot);
            //final ConstraintLayout layou_to_show_screen_shot_in_good_habits = getView().findViewById(R.id.layou_to_show_screen_shot_in_good_habits);
            final ScrollView scroll_view_to_take_screen_show_in_good_habtis = getView().findViewById(R.id.scroll_view_to_take_screen_show_in_good_habtis);
            view_to_pass_the_real_view_to_for_screen_shot.setImageBitmap(bitmap);
            scroll_view_to_take_screen_show_in_good_habtis.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    scroll_view_to_take_screen_show_in_good_habtis.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    share_the_bitmap(getBitmapFromView(scroll_view_to_take_screen_show_in_good_habtis, scroll_view_to_take_screen_show_in_good_habtis.getChildAt(0).getHeight(), scroll_view_to_take_screen_show_in_good_habtis.getChildAt(0).getWidth()));
                    view_to_pass_the_real_view_to_for_screen_shot.setImageDrawable(null);
                }
            });
        }
    }

    private void share_the_bitmap(Bitmap bitmap) {
        if (getView() != null && getContext() != null) {
            // save bitmap to cache directory
            try {
                File cachePath = new File(getContext().getCacheDir(), "images");
                cachePath.mkdirs(); // don't forget to make the directory
                FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                stream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            File imagePath = new File(getContext().getCacheDir(), "images");
            File newFile = new File(imagePath, "image.png");
            Uri contentUri = FileProvider.getUriForFile(getContext(), "com.easyhabitsapp.android.fileprovider", newFile);

            if (contentUri != null) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
                shareIntent.setDataAndType(contentUri, getActivity().getContentResolver().getType(contentUri));
                shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
                startActivity(Intent.createChooser(shareIntent, "Share"));
            }
        }
    }

    private boolean check_if_streak_should_be_imapcted(long milli) {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.month_and_year_in_calender_for_good_habits);
            String[] splitter = color_the_today.split("_");
            String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            Calendar calendar = Calendar.getInstance();
            if (milli == 0) {
                int calender_day = Integer.parseInt(splitter[0]);
                int calender_month = return_month_string_to_int(month_and_year[0]);
                int calender_year = Integer.parseInt(month_and_year[1]);
                calendar.set(calender_year, calender_month, calender_day);
            } else {
                calendar.setTimeInMillis(milli);
            }
            return !is_day_required(calendar.getTimeInMillis());
        } else {
            return true;
        }
    }

    private void show_snack_bar(long milli) {
        if (getActivity() != null) {
            if (check_if_streak_should_be_imapcted(milli)) {
                final Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), "Streak was not impacted. Day not required", Snackbar.LENGTH_LONG);
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
            } else {
                final Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), "Streak was impacted!!. Day required!!", Snackbar.LENGTH_LONG);
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
            }
        }
    }

    public int return_the_screen() {
        return which_main_screen_is_this_on;
    }

    public boolean is_there_is_any_information_saved() {
        if (getView() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString("good_history", "empty");
            if (shared != null) {
                return !shared.equals("empty");
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void return_the_first_screen() {
        if (getView() != null) {
            TextView text_saying_thanks_for_your_input = getView().findViewById(R.id.text_saying_thanks_for_your_input);
            if (which_main_screen_is_this_on == 3) {
                open_a_screen(2);
                restart_values();
                which_habit_am_i_on = 1;
                set_up_the_colors();
                fill_up_the_card();
                update_the_arrows();
                if (text_saying_thanks_for_your_input.getText().toString().equals("Today's input has been recorded")) {
                } else {
                    span_the_text_behind_mood();
                }
                check_if_good_habits_input_has_been_recorded();
                return_the_percent_for_card();
                define_values_for_pre_card();
                restart_third_screen_good_information();
            } else if (which_main_screen_is_this_on == 2) {
                restart_values();
                which_habit_am_i_on = 1;
                set_up_the_colors();
                fill_up_the_card();
                update_the_arrows();
                if (text_saying_thanks_for_your_input.getText().toString().equals("Today's input has been recorded")) {
                } else {
                    span_the_text_behind_mood();
                }
                check_if_good_habits_input_has_been_recorded();
                return_the_percent_for_card();
                define_values_for_pre_card();
            } else if (which_main_screen_is_this_on == 1) {
                if (is_there_is_any_information_saved()) {
                    open_a_screen(2);
                    restart_values();
                    which_habit_am_i_on = 1;
                    set_up_the_colors();
                    fill_up_the_card();
                    update_the_arrows();
                    if (text_saying_thanks_for_your_input.getText().toString().equals("Today's input has been recorded")) {

                    } else {
                        span_the_text_behind_mood();
                    }
                    check_if_good_habits_input_has_been_recorded();
                    return_the_percent_for_card();
                    define_values_for_pre_card();
                    if (am_i_in_edit) {
                        restart_third_screen_good_information();
                    }
                    am_i_in_edit = false;
                } else {
                    open_a_screen(0);
                    restart_values();
                }
            }
        }
    }

    public void show_popup_good_habits(View view) {
        if (getContext() != null) {
            PopupMenu popupMenu = new PopupMenu(getContext(), view);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.inflate(R.menu.popup_menu_habits);
            popupMenu.show();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.item_number_one_for_menu_edit_good_habits) {
            am_i_in_edit = true;
            edit_has_been_clicked();
            return true;
        } else if (item.getItemId() == R.id.item_number_two_for_menu_delete_good_habits) {
            delete_this_good_habit();
            return true;
        } else {
            return false;
        }
    }

    private void delete_this_good_habit() {
        new AlertDialog.Builder(getContext())
                .setTitle("Delete habit")
                .setMessage("Are you sure you want to permanently delete this habit?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        {
                            if (getActivity() != null) {
                                cancel_alarm(which_habit_am_i_on-1);
                                TextView title_of_the_good_habit_in_card = getActivity().findViewById(R.id.title_of_the_good_habit_in_card);
                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits", Context.MODE_PRIVATE);
                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                String old = sharedPreferences.getString("good_history", "");
                                if (old != null) {
                                    String[] big_splitter = old.split("max_divide");
                                    String new_string = "";
                                    for (int i = 0; i < big_splitter.length; i++) {
                                        String[] small_splitter = big_splitter[i].split("small_split");
                                        if (!small_splitter[0].equals(title_of_the_good_habit_in_card.getText().toString().toLowerCase())) {
                                            new_string = new_string.concat(big_splitter[i]).concat("max_divide");
                                        }
                                    }
                                    if (new_string.equals("")) {
                                        myEdit.remove("good_history");
                                    } else {
                                        myEdit.putString("good_history", new_string);
                                    }
                                    myEdit.commit();
                                }
                            }
                        }
                        {
                            if (getView() != null) {
                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits_save", Context.MODE_PRIVATE);
                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                TextView title_of_the_good_habit_in_card = getView().findViewById(R.id.title_of_the_good_habit_in_card);
                                myEdit.remove(title_of_the_good_habit_in_card.getText().toString().toLowerCase()).commit();
                            }
                        }
                        which_habit_am_i_on = 1;
                        which_screen_to_open();
                        restart_third_screen_good_information();
                        restart_values();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

    private void three_dots_to_delete_or_edit_at_top() {
        if (getView() != null) {
            final Button three_dots_button_in_top_in_habits = getView().findViewById(R.id.three_dots_button_in_top_in_habits);
            three_dots_button_in_top_in_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    show_popup_good_habits(three_dots_button_in_top_in_habits);
                }
            });
        }
    }

    private void edit_has_been_clicked() {
        if (getView() != null && getContext() != null) {
            open_a_screen(1);
            String[] split_me = read_the_habits().split("small_split");
            EditText edit_text_enter_good_habit_name_in_habit = getView().findViewById(R.id.edit_text_enter_good_habit_name_in_habit);
            EditText edit_text_enter_good_habit_question_in_habit = getView().findViewById(R.id.edit_text_enter_good_habit_question_in_habit);
            EditText enter_goal_for_new_good_habit_in_habits = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits);
            TextView text_saying_that_you_are_creating_a_new_habit_top = getView().findViewById(R.id.text_saying_that_you_are_creating_a_new_habit_top);
            TextView text_saying_days_beside_habit_edit_text = getView().findViewById(R.id.text_saying_days_beside_habit_edit_text);
            View view_showing_the_icon_and_its_color = getView().findViewById(R.id.view_showing_the_icon_and_its_color);
            TextView text_view_asying_this_is_example_of_icon = getView().findViewById(R.id.text_view_asying_this_is_example_of_icon);
            View showing_icon_in_color_good_habits = getView().findViewById(R.id.showing_icon_in_color_good_habits);
            TextView text_just_saying_days_in_habits = getView().findViewById(R.id.text_just_saying_days_in_habits);
            EditText enter_number_of_days_for_habits = getView().findViewById(R.id.enter_number_of_days_for_habits);
            TextView text_saying_which_days_of_the_week_are_supposed_for_the_habit = getView().findViewById(R.id.text_saying_which_days_of_the_week_are_supposed_for_the_habit);
            ConstraintLayout layout_that_contains_frequency_for_habits = getView().findViewById(R.id.layout_that_contains_frequency_for_habits);
            Button button_saying_every_day_about_the_habit = getView().findViewById(R.id.button_saying_every_day_about_the_habit);
            Button button_frequent_in_starting_a_good_habit = getView().findViewById(R.id.button_frequent_in_starting_a_good_habit);
            Button button_saying_days_of_week_about_habit = getView().findViewById(R.id.button_saying_days_of_week_about_habit);
            edit_text_enter_good_habit_name_in_habit.setText(split_me[0]);
            edit_text_enter_good_habit_question_in_habit.setText(split_me[1]);
            enter_goal_for_new_good_habit_in_habits.setText(split_me[2]);
            text_saying_that_you_are_creating_a_new_habit_top.setText("Edit a habit!!");
            if (Integer.parseInt(split_me[2]) == 1) {
                text_saying_days_beside_habit_edit_text.setText("day");
            }
            edit_text_enter_good_habit_name_in_habit.clearFocus();
            edit_text_enter_good_habit_question_in_habit.clearFocus();
            enter_goal_for_new_good_habit_in_habits.clearFocus();
            color = split_me[3];
            if (split_me[3].equals("teal")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_dark_green);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#607D8B"));
            } else if (split_me[3].equals("red")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_red);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#e6194B"));
            } else if (split_me[3].equals("green")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_green);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#3cb44b"));
            } else if (split_me[3].equals("blue")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_blue);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#4363d8"));
            } else if (split_me[3].equals("orange")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_orange);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#f58231"));
            } else if (split_me[3].equals("brown")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_brown);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#9A6324"));
            } else if (split_me[3].equals("black")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_black);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#000000"));
            } else if (split_me[3].equals("cyan")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_cyan);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#42d4f4"));
            } else if (split_me[3].equals("lime")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_lime);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#bfef45"));
            } else if (split_me[3].equals("magenta")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_magenta);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#f032e6"));
            } else if (split_me[3].equals("navy")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_navy);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#000075"));
            } else if (split_me[3].equals("pink")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_pink);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#fabed4"));
            } else if (split_me[3].equals("yellow")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_yellow);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#ffe119"));
            } else if (split_me[3].equals("purple")) {
                view_showing_the_icon_and_its_color.setBackgroundResource(R.drawable.z_purple);
                text_view_asying_this_is_example_of_icon.setTextColor(Color.parseColor("#911eb4"));
            }
            icon = split_me[4];
            if (split_me[4].equals("fitness")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_fitness_center_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.WHITE);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (split_me[4].equals("finance")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_account_balance_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.WHITE);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (split_me[4].equals("education")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_school_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.WHITE);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (split_me[4].equals("reading")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_menu_book_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.WHITE);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (split_me[4].equals("socializing")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_group_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.WHITE);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (split_me[4].equals("eating healthy")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_local_dining_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.WHITE);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (split_me[4].equals("mediation")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_self_improvement_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.WHITE);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (split_me[4].equals("running/walking")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_directions_run_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.WHITE);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (split_me[4].equals("sports")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_sports_tennis_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.WHITE);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (split_me[4].equals("fasting")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_no_food_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.WHITE);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (split_me[4].equals("outdoors")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_terrain_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.WHITE);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (split_me[4].equals("sleeping healthy")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_airline_seat_individual_suite_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.WHITE);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (split_me[4].equals("photography")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_photo_camera_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.WHITE);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (split_me[4].equals("art")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_brush_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.WHITE);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (split_me[4].equals("time")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_query_builder_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.WHITE);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (split_me[4].equals("business")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.round_business_24).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.WHITE);
                showing_icon_in_color_good_habits.setBackground(icon);
            } else if (split_me[4].equals("other")) {
                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.happy_face_dark_green).mutate();
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTint(icon, Color.WHITE);
                showing_icon_in_color_good_habits.setBackground(icon);
            }
            if (split_me[5].contains("Mo") || split_me[5].contains("Tu") || split_me[5].contains("We") || split_me[5].contains("Th") || split_me[5].contains("Fr") || split_me[5].contains("Sa") || split_me[5].contains("Su")) {
                String display_me = "";
                if (split_me[5].contains("Mo")) {
                    display_me = "Mo., ";
                }
                if (split_me[5].contains("Tu")) {
                    display_me = display_me.concat("Tu., ");
                }
                if (split_me[5].contains("We")) {
                    display_me = display_me.concat("We., ");
                }
                if (split_me[5].contains("Th")) {
                    display_me = display_me.concat("Th., ");
                }
                if (split_me[5].contains("Fr")) {
                    display_me = display_me.concat("Fr., ");
                }
                if (split_me[5].contains("Sa")) {
                    display_me = display_me.concat("Sa., ");
                }
                if (split_me[5].contains("Su")) {
                    display_me = display_me.concat("Su., ");
                }
                display_me = display_me.substring(0, display_me.length() - 2);
                text_saying_which_days_of_the_week_are_supposed_for_the_habit.setText("Days of week: ".concat(display_me));
                layout_that_contains_frequency_for_habits.setVisibility(View.INVISIBLE);
                text_saying_which_days_of_the_week_are_supposed_for_the_habit.setVisibility(View.VISIBLE);
                button_saying_every_day_about_the_habit.setTextColor(Color.BLACK);
                button_frequent_in_starting_a_good_habit.setTextColor(Color.BLACK);
                button_saying_days_of_week_about_habit.setTextColor(Color.WHITE);
                button_saying_every_day_about_the_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                button_frequent_in_starting_a_good_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                button_saying_days_of_week_about_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
                button_frequent_in_starting_a_good_habit.setText(button_frequent_in_starting_a_good_habit.getText().toString().replace(" ✓", ""));
                button_saying_every_day_about_the_habit.setText(button_saying_every_day_about_the_habit.getText().toString().replace(" ✓", ""));
                button_saying_days_of_week_about_habit.setText(button_saying_days_of_week_about_habit.getText().toString().replace(" ✓", "").concat(" ✓"));
            } else {
                enter_number_of_days_for_habits.setText(split_me[5]);
                if (!enter_number_of_days_for_habits.getText().toString().equals("") && Integer.parseInt(enter_number_of_days_for_habits.getText().toString()) == 1) {
                    text_just_saying_days_in_habits.setText("day");
                } else {
                    text_just_saying_days_in_habits.setText("days");
                    button_saying_every_day_about_the_habit.setTextColor(Color.BLACK);
                    button_frequent_in_starting_a_good_habit.setTextColor(Color.WHITE);
                    button_saying_days_of_week_about_habit.setTextColor(Color.BLACK);
                    button_saying_every_day_about_the_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                    button_frequent_in_starting_a_good_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
                    button_saying_days_of_week_about_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
                    button_frequent_in_starting_a_good_habit.setText(button_frequent_in_starting_a_good_habit.getText().toString().concat(" ✓"));
                    button_saying_every_day_about_the_habit.setText(button_saying_every_day_about_the_habit.getText().toString().replace(" ✓", ""));
                    button_saying_days_of_week_about_habit.setText(button_saying_days_of_week_about_habit.getText().toString().replace(" ✓", ""));

                }
            }
            reminders = split_me[6];
            update_the_view_reminder_button();
        }
    }

    public boolean return_edit_mode() {
        return am_i_in_edit;
    }

    public void make_the_edit_false_good_habit() {
        am_i_in_edit = false;
    }

    private void edit_the_current_habit() {
        if (getView() != null && getActivity() != null) {
            EditText edit_text_enter_good_habit_name_in_habit = getView().findViewById(R.id.edit_text_enter_good_habit_name_in_habit);
            EditText edit_text_enter_good_habit_question_in_habit = getView().findViewById(R.id.edit_text_enter_good_habit_question_in_habit);
            EditText enter_goal_for_new_good_habit_in_habits = getView().findViewById(R.id.enter_goal_for_new_good_habit_in_habits);
            EditText enter_number_of_days_for_habits = getView().findViewById(R.id.enter_number_of_days_for_habits);
            TextView text_saying_which_days_of_the_week_are_supposed_for_the_habit = getView().findViewById(R.id.text_saying_which_days_of_the_week_are_supposed_for_the_habit);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits", Context.MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            String old = sharedPreferences.getString("good_history", "");
            String new_string = "";
            String remove_me = "";
            if (old != null) {
                String[] splitter = old.split("max_divide");
                for (int i = 0; i < splitter.length; i++) {
                    if (i == (which_habit_am_i_on - 1)) {
                        set_notifiction_new(edit_text_enter_good_habit_name_in_habit.getText().toString().trim().toLowerCase(),i);
                        String[] small_split = splitter[i].split("small_split");
                        if (!edit_text_enter_good_habit_name_in_habit.getText().toString().trim().toLowerCase().equals(small_split[0].trim().toLowerCase())) {
                            remove_me = small_split[0].trim().toLowerCase();
                        }
                        String date_to_start = small_split[7];
                        if (text_saying_which_days_of_the_week_are_supposed_for_the_habit.getVisibility() != View.VISIBLE) {
                            new_string = new_string.concat(edit_text_enter_good_habit_name_in_habit.getText().toString().trim().toLowerCase().concat("small_split").concat(edit_text_enter_good_habit_question_in_habit.getText().toString().trim()).concat("small_split".concat(enter_goal_for_new_good_habit_in_habits.getText().toString())).concat("small_split".concat(color).concat("small_split").concat(icon).concat("small_split").concat(enter_number_of_days_for_habits.getText().toString())).concat("small_split").concat(reminders).concat("small_split").concat(date_to_start)).concat("max_divide");
                        } else {
                            new_string = new_string.concat(edit_text_enter_good_habit_name_in_habit.getText().toString().trim().toLowerCase().concat("small_split").concat(edit_text_enter_good_habit_question_in_habit.getText().toString().trim()).concat("small_split".concat(enter_goal_for_new_good_habit_in_habits.getText().toString())).concat("small_split".concat(color).concat("small_split").concat(icon).concat("small_split").concat(return_frequency_for_edit())).concat("small_split").concat(reminders).concat("small_split").concat(String.valueOf(date_to_start)).concat("max_divide"));
                        }
                    } else {
                        new_string = new_string.concat(splitter[i]).concat("max_divide");
                    }
                }
                myEdit.putString("good_history", new_string);
            } else {
                if (text_saying_which_days_of_the_week_are_supposed_for_the_habit.getVisibility() != View.VISIBLE) {
                    myEdit.putString("good_history", edit_text_enter_good_habit_name_in_habit.getText().toString().trim().toLowerCase().concat("small_split").concat(edit_text_enter_good_habit_question_in_habit.getText().toString().trim()).concat("small_split".concat(enter_goal_for_new_good_habit_in_habits.getText().toString())).concat("small_split".concat(color).concat("small_split").concat(icon).concat("small_split").concat(enter_number_of_days_for_habits.getText().toString())).concat("small_split").concat(reminders).concat("small_split").concat(String.valueOf(System.currentTimeMillis())).concat("max_divide"));
                } else {
                    myEdit.putString("good_history", edit_text_enter_good_habit_name_in_habit.getText().toString().trim().toLowerCase().concat("small_split").concat(edit_text_enter_good_habit_question_in_habit.getText().toString().trim()).concat("small_split".concat(enter_goal_for_new_good_habit_in_habits.getText().toString())).concat("small_split".concat(color).concat("small_split").concat(icon).concat("small_split").concat(return_frequency_for_edit())).concat("small_split").concat(reminders).concat("small_split").concat(String.valueOf(System.currentTimeMillis())).concat("max_divide"));
                }
            }
            myEdit.commit();
            if (!remove_me.equals("")) {
                SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("good_habits_save", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit2 = sharedPreferences2.edit();
                String old_data = sharedPreferences2.getString(remove_me, "");
                edit2.putString(edit_text_enter_good_habit_name_in_habit.getText().toString().trim().toLowerCase(), old_data);
                edit2.remove(remove_me);
                edit2.commit();
            }
        }
    }

    private boolean check_if_it_is_in_anything_else(String value) {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits", Context.MODE_PRIVATE);
            String old = sharedPreferences.getString("good_history", "");
            if (old != null) {
                String[] split_me = old.split("max_divide");
                for (int i = 0; i < split_me.length; i++) {
                    String[] small_split = split_me[i].split("small_split");
                    if (value.trim().toLowerCase().equals(small_split[0].trim().toLowerCase()) && (i + 1) != which_habit_am_i_on) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*private String return_color_for_edit() {
        if (getView() != null) {
            TextView text_view_asying_this_is_example_of_icon = getView().findViewById(R.id.text_view_asying_this_is_example_of_icon);
            int color = text_view_asying_this_is_example_of_icon.getCurrentTextColor();
            if (color == Color.parseColor("#607D8B")) {
                return "teal";
            } else if (color == Color.parseColor("#e6194B")) {
                return "red";
            } else if (color == Color.parseColor("#3cb44b")) {
                return "green";
            } else if (color == Color.parseColor("#4363d8")) {
                return "blue";
            } else if (color == Color.parseColor("#f58231")) {
                return "orange";
            } else if (color == Color.parseColor("#9A6324")) {
                return "brown";
            } else if (color == Color.parseColor("#000000")) {
                return "black";
            } else if (color == Color.parseColor("#42d4f4")) {
                return "cyan";
            } else if (color == Color.parseColor("#bfef45")) {
                return "lime";
            } else if (color == Color.parseColor("#f032e6")) {
                return "magenta";
            } else if (color == Color.parseColor("#000075")) {
                return "navy";
            } else if (color == Color.parseColor("#fabed4")) {
                return "pink";
            } else if (color == Color.parseColor("#ffe119")) {
                return "yellow";
            } else if (color == Color.parseColor("#911eb4")) {
                return "purple";
            }
        }
        return "";
    }

    private String return_frequency_for_edit() {
        if (getView() != null) {
            TextView text_saying_which_days_of_the_week_are_supposed_for_the_habit = getView().findViewById(R.id.text_saying_which_days_of_the_week_are_supposed_for_the_habit);
            return text_saying_which_days_of_the_week_are_supposed_for_the_habit.getText().toString().replace(".", "").replace(",", "").replace(" ", "");
        } else {
            return "";
        }
    }

    private void setup_the_four_information_card() {
        if (getActivity() != null && getView() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits_save", Context.MODE_PRIVATE);
            TextView title_of_the_good_habit_in_card = getView().findViewById(R.id.title_of_the_good_habit_in_card);
            String old = sharedPreferences.getString(title_of_the_good_habit_in_card.getText().toString().toLowerCase(), "");
            TextView text_saying_the_how_many_times_for_this_week_actual = getView().findViewById(R.id.text_saying_the_how_many_times_for_this_week_actual);
            TextView text_saying_the_how_many_times_for_this_month_actual = getView().findViewById(R.id.text_saying_the_how_many_times_for_this_month_actual);
            TextView text_saying_the_how_many_times_for_this_year_actual = getView().findViewById(R.id.text_saying_the_how_many_times_for_this_year_actual);
            TextView text_saying_the_how_many_times_for_this_all_time_actual = getView().findViewById(R.id.text_saying_the_how_many_times_for_this_all_time_actual);
            Am_i_paid am_i_paid = new Am_i_paid(getContext());
            if (am_i_paid.did_user_pay()) {
                long week_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (6 * 86400000L));
                long month_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (29 * 86400000L));
                long year_ago = Simplify_the_time.return_time_in_midnight(System.currentTimeMillis() - (364 * 86400000L));
                int week_data = 0;
                int month_data = 0;
                int year_data = 0;
                int all_data = 0;
                if (old != null && !old.equals("")) {
                    String[] split_max = old.split("max_divide");
                    for (int i = split_max.length - 1; i >= 0; i--) {
                        long time = Simplify_the_time.return_time_in_midnight(Long.parseLong(split_max[i].replace("yes", "").replace("no", "").replace("small_split", "")));
                        if (time < week_ago) {
                            break;
                        }
                        if (split_max[i].contains("yes")) {
                            week_data++;
                        }
                    }
                    for (int i = split_max.length - 1; i >= 0; i--) {
                        long time = Simplify_the_time.return_time_in_midnight(Long.parseLong(split_max[i].replace("yes", "").replace("no", "").replace("small_split", "")));
                        if (time < month_ago) {
                            break;
                        }
                        if (split_max[i].contains("yes")) {
                            month_data++;
                        }
                    }
                    for (int i = split_max.length - 1; i >= 0; i--) {
                        long time = Simplify_the_time.return_time_in_midnight(Long.parseLong(split_max[i].replace("yes", "").replace("no", "").replace("small_split", "")));
                        if (time < year_ago) {
                            break;
                        }
                        if (split_max[i].contains("yes")) {
                            year_data++;
                        }
                    }
                    for (int i = split_max.length - 1; i >= 0; i--) {
                        if (i > split_max.length - 10) {
                        }
                        if (split_max[i].contains("yes")) {
                            all_data++;
                        }
                    }
                    text_saying_the_how_many_times_for_this_week_actual.setText(String.valueOf(week_data));
                    text_saying_the_how_many_times_for_this_month_actual.setText(String.valueOf(month_data));
                    text_saying_the_how_many_times_for_this_year_actual.setText(String.valueOf(year_data));
                    text_saying_the_how_many_times_for_this_all_time_actual.setText(String.valueOf(all_data));
                }
            } else {
                Random random = new Random();
                int week_data = random.nextInt(10);
                int month_data = random.nextInt(10) + week_data;
                int year_data = random.nextInt(10) + month_data;
                int all_data = week_data + month_data + year_data;
                text_saying_the_how_many_times_for_this_week_actual.setText(String.valueOf(week_data));
                text_saying_the_how_many_times_for_this_month_actual.setText(String.valueOf(month_data));
                text_saying_the_how_many_times_for_this_year_actual.setText(String.valueOf(year_data));
                text_saying_the_how_many_times_for_this_all_time_actual.setText(String.valueOf(all_data));
            }
        }
    }

    private void set_the_title_of_the_year_habit() {
        if (getView() != null) {
            TextView title_saying_the_habit_name_in_year_for_good_habits = getView().findViewById(R.id.title_saying_the_habit_name_in_year_for_good_habits);
            TextView title_of_the_good_habit_in_card = getView().findViewById(R.id.title_of_the_good_habit_in_card);
            TextView text_saying_which_year_to_show_in_a_good_habits_year = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year);
            String capital_tital = first_letter_cap(title_of_the_good_habit_in_card.getText().toString().trim());
            title_saying_the_habit_name_in_year_for_good_habits.setText(capital_tital.concat(" in a year"));
            Calendar calendar = Calendar.getInstance();
            text_saying_which_year_to_show_in_a_good_habits_year.setText(String.valueOf(calendar.get(Calendar.YEAR)));
        }
    }

    private void add_the_views() {
        if (getView() != null) {
            View good_habits_layout_circle_jan1 = getView().findViewById(R.id.good_habits_layout_circle_jan1);
            View good_habits_layout_circle_jan2 = getView().findViewById(R.id.good_habits_layout_circle_jan2);
            View good_habits_layout_circle_jan3 = getView().findViewById(R.id.good_habits_layout_circle_jan3);
            View good_habits_layout_circle_jan4 = getView().findViewById(R.id.good_habits_layout_circle_jan4);
            View good_habits_layout_circle_jan5 = getView().findViewById(R.id.good_habits_layout_circle_jan5);
            View good_habits_layout_circle_jan6 = getView().findViewById(R.id.good_habits_layout_circle_jan6);
            View good_habits_layout_circle_jan7 = getView().findViewById(R.id.good_habits_layout_circle_jan7);
            View good_habits_layout_circle_jan8 = getView().findViewById(R.id.good_habits_layout_circle_jan8);
            View good_habits_layout_circle_jan9 = getView().findViewById(R.id.good_habits_layout_circle_jan9);
            View good_habits_layout_circle_jan10 = getView().findViewById(R.id.good_habits_layout_circle_jan10);
            View good_habits_layout_circle_jan11 = getView().findViewById(R.id.good_habits_layout_circle_jan11);
            View good_habits_layout_circle_jan12 = getView().findViewById(R.id.good_habits_layout_circle_jan12);
            View good_habits_layout_circle_jan13 = getView().findViewById(R.id.good_habits_layout_circle_jan13);
            View good_habits_layout_circle_jan14 = getView().findViewById(R.id.good_habits_layout_circle_jan14);
            View good_habits_layout_circle_jan15 = getView().findViewById(R.id.good_habits_layout_circle_jan15);
            View good_habits_layout_circle_jan16 = getView().findViewById(R.id.good_habits_layout_circle_jan16);
            View good_habits_layout_circle_jan17 = getView().findViewById(R.id.good_habits_layout_circle_jan17);
            View good_habits_layout_circle_jan18 = getView().findViewById(R.id.good_habits_layout_circle_jan18);
            View good_habits_layout_circle_jan19 = getView().findViewById(R.id.good_habits_layout_circle_jan19);
            View good_habits_layout_circle_jan20 = getView().findViewById(R.id.good_habits_layout_circle_jan20);
            View good_habits_layout_circle_jan21 = getView().findViewById(R.id.good_habits_layout_circle_jan21);
            View good_habits_layout_circle_jan22 = getView().findViewById(R.id.good_habits_layout_circle_jan22);
            View good_habits_layout_circle_jan23 = getView().findViewById(R.id.good_habits_layout_circle_jan23);
            View good_habits_layout_circle_jan24 = getView().findViewById(R.id.good_habits_layout_circle_jan24);
            View good_habits_layout_circle_jan25 = getView().findViewById(R.id.good_habits_layout_circle_jan25);
            View good_habits_layout_circle_jan26 = getView().findViewById(R.id.good_habits_layout_circle_jan26);
            View good_habits_layout_circle_jan27 = getView().findViewById(R.id.good_habits_layout_circle_jan27);
            View good_habits_layout_circle_jan28 = getView().findViewById(R.id.good_habits_layout_circle_jan28);
            View good_habits_layout_circle_jan29 = getView().findViewById(R.id.good_habits_layout_circle_jan29);
            View good_habits_layout_circle_jan30 = getView().findViewById(R.id.good_habits_layout_circle_jan30);
            View good_habits_layout_circle_jan31 = getView().findViewById(R.id.good_habits_layout_circle_jan31);
            View good_habits_layout_circle_feb1 = getView().findViewById(R.id.good_habits_layout_circle_feb1);
            View good_habits_layout_circle_feb2 = getView().findViewById(R.id.good_habits_layout_circle_feb2);
            View good_habits_layout_circle_feb3 = getView().findViewById(R.id.good_habits_layout_circle_feb3);
            View good_habits_layout_circle_feb4 = getView().findViewById(R.id.good_habits_layout_circle_feb4);
            View good_habits_layout_circle_feb5 = getView().findViewById(R.id.good_habits_layout_circle_feb5);
            View good_habits_layout_circle_feb6 = getView().findViewById(R.id.good_habits_layout_circle_feb6);
            View good_habits_layout_circle_feb7 = getView().findViewById(R.id.good_habits_layout_circle_feb7);
            View good_habits_layout_circle_feb8 = getView().findViewById(R.id.good_habits_layout_circle_feb8);
            View good_habits_layout_circle_feb9 = getView().findViewById(R.id.good_habits_layout_circle_feb9);
            View good_habits_layout_circle_feb10 = getView().findViewById(R.id.good_habits_layout_circle_feb10);
            View good_habits_layout_circle_feb11 = getView().findViewById(R.id.good_habits_layout_circle_feb11);
            View good_habits_layout_circle_feb12 = getView().findViewById(R.id.good_habits_layout_circle_feb12);
            View good_habits_layout_circle_feb13 = getView().findViewById(R.id.good_habits_layout_circle_feb13);
            View good_habits_layout_circle_feb14 = getView().findViewById(R.id.good_habits_layout_circle_feb14);
            View good_habits_layout_circle_feb15 = getView().findViewById(R.id.good_habits_layout_circle_feb15);
            View good_habits_layout_circle_feb16 = getView().findViewById(R.id.good_habits_layout_circle_feb16);
            View good_habits_layout_circle_feb17 = getView().findViewById(R.id.good_habits_layout_circle_feb17);
            View good_habits_layout_circle_feb18 = getView().findViewById(R.id.good_habits_layout_circle_feb18);
            View good_habits_layout_circle_feb19 = getView().findViewById(R.id.good_habits_layout_circle_feb19);
            View good_habits_layout_circle_feb20 = getView().findViewById(R.id.good_habits_layout_circle_feb20);
            View good_habits_layout_circle_feb21 = getView().findViewById(R.id.good_habits_layout_circle_feb21);
            View good_habits_layout_circle_feb22 = getView().findViewById(R.id.good_habits_layout_circle_feb22);
            View good_habits_layout_circle_feb23 = getView().findViewById(R.id.good_habits_layout_circle_feb23);
            View good_habits_layout_circle_feb24 = getView().findViewById(R.id.good_habits_layout_circle_feb24);
            View good_habits_layout_circle_feb25 = getView().findViewById(R.id.good_habits_layout_circle_feb25);
            View good_habits_layout_circle_feb26 = getView().findViewById(R.id.good_habits_layout_circle_feb26);
            View good_habits_layout_circle_feb27 = getView().findViewById(R.id.good_habits_layout_circle_feb27);
            View good_habits_layout_circle_feb28 = getView().findViewById(R.id.good_habits_layout_circle_feb28);
            View good_habits_layout_circle_feb29 = getView().findViewById(R.id.good_habits_layout_circle_feb29);
            View good_habits_layout_circle_mar1 = getView().findViewById(R.id.good_habits_layout_circle_mar1);
            View good_habits_layout_circle_mar2 = getView().findViewById(R.id.good_habits_layout_circle_mar2);
            View good_habits_layout_circle_mar3 = getView().findViewById(R.id.good_habits_layout_circle_mar3);
            View good_habits_layout_circle_mar4 = getView().findViewById(R.id.good_habits_layout_circle_mar4);
            View good_habits_layout_circle_mar5 = getView().findViewById(R.id.good_habits_layout_circle_mar5);
            View good_habits_layout_circle_mar6 = getView().findViewById(R.id.good_habits_layout_circle_mar6);
            View good_habits_layout_circle_mar7 = getView().findViewById(R.id.good_habits_layout_circle_mar7);
            View good_habits_layout_circle_mar8 = getView().findViewById(R.id.good_habits_layout_circle_mar8);
            View good_habits_layout_circle_mar9 = getView().findViewById(R.id.good_habits_layout_circle_mar9);
            View good_habits_layout_circle_mar10 = getView().findViewById(R.id.good_habits_layout_circle_mar10);
            View good_habits_layout_circle_mar11 = getView().findViewById(R.id.good_habits_layout_circle_mar11);
            View good_habits_layout_circle_mar12 = getView().findViewById(R.id.good_habits_layout_circle_mar12);
            View good_habits_layout_circle_mar13 = getView().findViewById(R.id.good_habits_layout_circle_mar13);
            View good_habits_layout_circle_mar14 = getView().findViewById(R.id.good_habits_layout_circle_mar14);
            View good_habits_layout_circle_mar15 = getView().findViewById(R.id.good_habits_layout_circle_mar15);
            View good_habits_layout_circle_mar16 = getView().findViewById(R.id.good_habits_layout_circle_mar16);
            View good_habits_layout_circle_mar17 = getView().findViewById(R.id.good_habits_layout_circle_mar17);
            View good_habits_layout_circle_mar18 = getView().findViewById(R.id.good_habits_layout_circle_mar18);
            View good_habits_layout_circle_mar19 = getView().findViewById(R.id.good_habits_layout_circle_mar19);
            View good_habits_layout_circle_mar20 = getView().findViewById(R.id.good_habits_layout_circle_mar20);
            View good_habits_layout_circle_mar21 = getView().findViewById(R.id.good_habits_layout_circle_mar21);
            View good_habits_layout_circle_mar22 = getView().findViewById(R.id.good_habits_layout_circle_mar22);
            View good_habits_layout_circle_mar23 = getView().findViewById(R.id.good_habits_layout_circle_mar23);
            View good_habits_layout_circle_mar24 = getView().findViewById(R.id.good_habits_layout_circle_mar24);
            View good_habits_layout_circle_mar25 = getView().findViewById(R.id.good_habits_layout_circle_mar25);
            View good_habits_layout_circle_mar26 = getView().findViewById(R.id.good_habits_layout_circle_mar26);
            View good_habits_layout_circle_mar27 = getView().findViewById(R.id.good_habits_layout_circle_mar27);
            View good_habits_layout_circle_mar28 = getView().findViewById(R.id.good_habits_layout_circle_mar28);
            View good_habits_layout_circle_mar29 = getView().findViewById(R.id.good_habits_layout_circle_mar29);
            View good_habits_layout_circle_mar30 = getView().findViewById(R.id.good_habits_layout_circle_mar30);
            View good_habits_layout_circle_mar31 = getView().findViewById(R.id.good_habits_layout_circle_mar31);
            View good_habits_layout_circle_apr1 = getView().findViewById(R.id.good_habits_layout_circle_apr1);
            View good_habits_layout_circle_apr2 = getView().findViewById(R.id.good_habits_layout_circle_apr2);
            View good_habits_layout_circle_apr3 = getView().findViewById(R.id.good_habits_layout_circle_apr3);
            View good_habits_layout_circle_apr4 = getView().findViewById(R.id.good_habits_layout_circle_apr4);
            View good_habits_layout_circle_apr5 = getView().findViewById(R.id.good_habits_layout_circle_apr5);
            View good_habits_layout_circle_apr6 = getView().findViewById(R.id.good_habits_layout_circle_apr6);
            View good_habits_layout_circle_apr7 = getView().findViewById(R.id.good_habits_layout_circle_apr7);
            View good_habits_layout_circle_apr8 = getView().findViewById(R.id.good_habits_layout_circle_apr8);
            View good_habits_layout_circle_apr9 = getView().findViewById(R.id.good_habits_layout_circle_apr9);
            View good_habits_layout_circle_apr10 = getView().findViewById(R.id.good_habits_layout_circle_apr10);
            View good_habits_layout_circle_apr11 = getView().findViewById(R.id.good_habits_layout_circle_apr11);
            View good_habits_layout_circle_apr12 = getView().findViewById(R.id.good_habits_layout_circle_apr12);
            View good_habits_layout_circle_apr13 = getView().findViewById(R.id.good_habits_layout_circle_apr13);
            View good_habits_layout_circle_apr14 = getView().findViewById(R.id.good_habits_layout_circle_apr14);
            View good_habits_layout_circle_apr15 = getView().findViewById(R.id.good_habits_layout_circle_apr15);
            View good_habits_layout_circle_apr16 = getView().findViewById(R.id.good_habits_layout_circle_apr16);
            View good_habits_layout_circle_apr17 = getView().findViewById(R.id.good_habits_layout_circle_apr17);
            View good_habits_layout_circle_apr18 = getView().findViewById(R.id.good_habits_layout_circle_apr18);
            View good_habits_layout_circle_apr19 = getView().findViewById(R.id.good_habits_layout_circle_apr19);
            View good_habits_layout_circle_apr20 = getView().findViewById(R.id.good_habits_layout_circle_apr20);
            View good_habits_layout_circle_apr21 = getView().findViewById(R.id.good_habits_layout_circle_apr21);
            View good_habits_layout_circle_apr22 = getView().findViewById(R.id.good_habits_layout_circle_apr22);
            View good_habits_layout_circle_apr23 = getView().findViewById(R.id.good_habits_layout_circle_apr23);
            View good_habits_layout_circle_apr24 = getView().findViewById(R.id.good_habits_layout_circle_apr24);
            View good_habits_layout_circle_apr25 = getView().findViewById(R.id.good_habits_layout_circle_apr25);
            View good_habits_layout_circle_apr26 = getView().findViewById(R.id.good_habits_layout_circle_apr26);
            View good_habits_layout_circle_apr27 = getView().findViewById(R.id.good_habits_layout_circle_apr27);
            View good_habits_layout_circle_apr28 = getView().findViewById(R.id.good_habits_layout_circle_apr28);
            View good_habits_layout_circle_apr29 = getView().findViewById(R.id.good_habits_layout_circle_apr29);
            View good_habits_layout_circle_apr30 = getView().findViewById(R.id.good_habits_layout_circle_apr30);
            View good_habits_layout_circle_may1 = getView().findViewById(R.id.good_habits_layout_circle_may1);
            View good_habits_layout_circle_may2 = getView().findViewById(R.id.good_habits_layout_circle_may2);
            View good_habits_layout_circle_may3 = getView().findViewById(R.id.good_habits_layout_circle_may3);
            View good_habits_layout_circle_may4 = getView().findViewById(R.id.good_habits_layout_circle_may4);
            View good_habits_layout_circle_may5 = getView().findViewById(R.id.good_habits_layout_circle_may5);
            View good_habits_layout_circle_may6 = getView().findViewById(R.id.good_habits_layout_circle_may6);
            View good_habits_layout_circle_may7 = getView().findViewById(R.id.good_habits_layout_circle_may7);
            View good_habits_layout_circle_may8 = getView().findViewById(R.id.good_habits_layout_circle_may8);
            View good_habits_layout_circle_may9 = getView().findViewById(R.id.good_habits_layout_circle_may9);
            View good_habits_layout_circle_may10 = getView().findViewById(R.id.good_habits_layout_circle_may10);
            View good_habits_layout_circle_may11 = getView().findViewById(R.id.good_habits_layout_circle_may11);
            View good_habits_layout_circle_may12 = getView().findViewById(R.id.good_habits_layout_circle_may12);
            View good_habits_layout_circle_may13 = getView().findViewById(R.id.good_habits_layout_circle_may13);
            View good_habits_layout_circle_may14 = getView().findViewById(R.id.good_habits_layout_circle_may14);
            View good_habits_layout_circle_may15 = getView().findViewById(R.id.good_habits_layout_circle_may15);
            View good_habits_layout_circle_may16 = getView().findViewById(R.id.good_habits_layout_circle_may16);
            View good_habits_layout_circle_may17 = getView().findViewById(R.id.good_habits_layout_circle_may17);
            View good_habits_layout_circle_may18 = getView().findViewById(R.id.good_habits_layout_circle_may18);
            View good_habits_layout_circle_may19 = getView().findViewById(R.id.good_habits_layout_circle_may19);
            View good_habits_layout_circle_may20 = getView().findViewById(R.id.good_habits_layout_circle_may20);
            View good_habits_layout_circle_may21 = getView().findViewById(R.id.good_habits_layout_circle_may21);
            View good_habits_layout_circle_may22 = getView().findViewById(R.id.good_habits_layout_circle_may22);
            View good_habits_layout_circle_may23 = getView().findViewById(R.id.good_habits_layout_circle_may23);
            View good_habits_layout_circle_may24 = getView().findViewById(R.id.good_habits_layout_circle_may24);
            View good_habits_layout_circle_may25 = getView().findViewById(R.id.good_habits_layout_circle_may25);
            View good_habits_layout_circle_may26 = getView().findViewById(R.id.good_habits_layout_circle_may26);
            View good_habits_layout_circle_may27 = getView().findViewById(R.id.good_habits_layout_circle_may27);
            View good_habits_layout_circle_may28 = getView().findViewById(R.id.good_habits_layout_circle_may28);
            View good_habits_layout_circle_may29 = getView().findViewById(R.id.good_habits_layout_circle_may29);
            View good_habits_layout_circle_may30 = getView().findViewById(R.id.good_habits_layout_circle_may30);
            View good_habits_layout_circle_may31 = getView().findViewById(R.id.good_habits_layout_circle_may31);
            View good_habits_layout_circle_jun1 = getView().findViewById(R.id.good_habits_layout_circle_jun1);
            View good_habits_layout_circle_jun2 = getView().findViewById(R.id.good_habits_layout_circle_jun2);
            View good_habits_layout_circle_jun3 = getView().findViewById(R.id.good_habits_layout_circle_jun3);
            View good_habits_layout_circle_jun4 = getView().findViewById(R.id.good_habits_layout_circle_jun4);
            View good_habits_layout_circle_jun5 = getView().findViewById(R.id.good_habits_layout_circle_jun5);
            View good_habits_layout_circle_jun6 = getView().findViewById(R.id.good_habits_layout_circle_jun6);
            View good_habits_layout_circle_jun7 = getView().findViewById(R.id.good_habits_layout_circle_jun7);
            View good_habits_layout_circle_jun8 = getView().findViewById(R.id.good_habits_layout_circle_jun8);
            View good_habits_layout_circle_jun9 = getView().findViewById(R.id.good_habits_layout_circle_jun9);
            View good_habits_layout_circle_jun10 = getView().findViewById(R.id.good_habits_layout_circle_jun10);
            View good_habits_layout_circle_jun11 = getView().findViewById(R.id.good_habits_layout_circle_jun11);
            View good_habits_layout_circle_jun12 = getView().findViewById(R.id.good_habits_layout_circle_jun12);
            View good_habits_layout_circle_jun13 = getView().findViewById(R.id.good_habits_layout_circle_jun13);
            View good_habits_layout_circle_jun14 = getView().findViewById(R.id.good_habits_layout_circle_jun14);
            View good_habits_layout_circle_jun15 = getView().findViewById(R.id.good_habits_layout_circle_jun15);
            View good_habits_layout_circle_jun16 = getView().findViewById(R.id.good_habits_layout_circle_jun16);
            View good_habits_layout_circle_jun17 = getView().findViewById(R.id.good_habits_layout_circle_jun17);
            View good_habits_layout_circle_jun18 = getView().findViewById(R.id.good_habits_layout_circle_jun18);
            View good_habits_layout_circle_jun19 = getView().findViewById(R.id.good_habits_layout_circle_jun19);
            View good_habits_layout_circle_jun20 = getView().findViewById(R.id.good_habits_layout_circle_jun20);
            View good_habits_layout_circle_jun21 = getView().findViewById(R.id.good_habits_layout_circle_jun21);
            View good_habits_layout_circle_jun22 = getView().findViewById(R.id.good_habits_layout_circle_jun22);
            View good_habits_layout_circle_jun23 = getView().findViewById(R.id.good_habits_layout_circle_jun23);
            View good_habits_layout_circle_jun24 = getView().findViewById(R.id.good_habits_layout_circle_jun24);
            View good_habits_layout_circle_jun25 = getView().findViewById(R.id.good_habits_layout_circle_jun25);
            View good_habits_layout_circle_jun26 = getView().findViewById(R.id.good_habits_layout_circle_jun26);
            View good_habits_layout_circle_jun27 = getView().findViewById(R.id.good_habits_layout_circle_jun27);
            View good_habits_layout_circle_jun28 = getView().findViewById(R.id.good_habits_layout_circle_jun28);
            View good_habits_layout_circle_jun29 = getView().findViewById(R.id.good_habits_layout_circle_jun29);
            View good_habits_layout_circle_jun30 = getView().findViewById(R.id.good_habits_layout_circle_jun30);
            View good_habits_layout_circle_jul1 = getView().findViewById(R.id.good_habits_layout_circle_jul1);
            View good_habits_layout_circle_jul2 = getView().findViewById(R.id.good_habits_layout_circle_jul2);
            View good_habits_layout_circle_jul3 = getView().findViewById(R.id.good_habits_layout_circle_jul3);
            View good_habits_layout_circle_jul4 = getView().findViewById(R.id.good_habits_layout_circle_jul4);
            View good_habits_layout_circle_jul5 = getView().findViewById(R.id.good_habits_layout_circle_jul5);
            View good_habits_layout_circle_jul6 = getView().findViewById(R.id.good_habits_layout_circle_jul6);
            View good_habits_layout_circle_jul7 = getView().findViewById(R.id.good_habits_layout_circle_jul7);
            View good_habits_layout_circle_jul8 = getView().findViewById(R.id.good_habits_layout_circle_jul8);
            View good_habits_layout_circle_jul9 = getView().findViewById(R.id.good_habits_layout_circle_jul9);
            View good_habits_layout_circle_jul10 = getView().findViewById(R.id.good_habits_layout_circle_jul10);
            View good_habits_layout_circle_jul11 = getView().findViewById(R.id.good_habits_layout_circle_jul11);
            View good_habits_layout_circle_jul12 = getView().findViewById(R.id.good_habits_layout_circle_jul12);
            View good_habits_layout_circle_jul13 = getView().findViewById(R.id.good_habits_layout_circle_jul13);
            View good_habits_layout_circle_jul14 = getView().findViewById(R.id.good_habits_layout_circle_jul14);
            View good_habits_layout_circle_jul15 = getView().findViewById(R.id.good_habits_layout_circle_jul15);
            View good_habits_layout_circle_jul16 = getView().findViewById(R.id.good_habits_layout_circle_jul16);
            View good_habits_layout_circle_jul17 = getView().findViewById(R.id.good_habits_layout_circle_jul17);
            View good_habits_layout_circle_jul18 = getView().findViewById(R.id.good_habits_layout_circle_jul18);
            View good_habits_layout_circle_jul19 = getView().findViewById(R.id.good_habits_layout_circle_jul19);
            View good_habits_layout_circle_jul20 = getView().findViewById(R.id.good_habits_layout_circle_jul20);
            View good_habits_layout_circle_jul21 = getView().findViewById(R.id.good_habits_layout_circle_jul21);
            View good_habits_layout_circle_jul22 = getView().findViewById(R.id.good_habits_layout_circle_jul22);
            View good_habits_layout_circle_jul23 = getView().findViewById(R.id.good_habits_layout_circle_jul23);
            View good_habits_layout_circle_jul24 = getView().findViewById(R.id.good_habits_layout_circle_jul24);
            View good_habits_layout_circle_jul25 = getView().findViewById(R.id.good_habits_layout_circle_jul25);
            View good_habits_layout_circle_jul26 = getView().findViewById(R.id.good_habits_layout_circle_jul26);
            View good_habits_layout_circle_jul27 = getView().findViewById(R.id.good_habits_layout_circle_jul27);
            View good_habits_layout_circle_jul28 = getView().findViewById(R.id.good_habits_layout_circle_jul28);
            View good_habits_layout_circle_jul29 = getView().findViewById(R.id.good_habits_layout_circle_jul29);
            View good_habits_layout_circle_jul30 = getView().findViewById(R.id.good_habits_layout_circle_jul30);
            View good_habits_layout_circle_jul31 = getView().findViewById(R.id.good_habits_layout_circle_jul31);
            View good_habits_layout_circle_aug1 = getView().findViewById(R.id.good_habits_layout_circle_aug1);
            View good_habits_layout_circle_aug2 = getView().findViewById(R.id.good_habits_layout_circle_aug2);
            View good_habits_layout_circle_aug3 = getView().findViewById(R.id.good_habits_layout_circle_aug3);
            View good_habits_layout_circle_aug4 = getView().findViewById(R.id.good_habits_layout_circle_aug4);
            View good_habits_layout_circle_aug5 = getView().findViewById(R.id.good_habits_layout_circle_aug5);
            View good_habits_layout_circle_aug6 = getView().findViewById(R.id.good_habits_layout_circle_aug6);
            View good_habits_layout_circle_aug7 = getView().findViewById(R.id.good_habits_layout_circle_aug7);
            View good_habits_layout_circle_aug8 = getView().findViewById(R.id.good_habits_layout_circle_aug8);
            View good_habits_layout_circle_aug9 = getView().findViewById(R.id.good_habits_layout_circle_aug9);
            View good_habits_layout_circle_aug10 = getView().findViewById(R.id.good_habits_layout_circle_aug10);
            View good_habits_layout_circle_aug11 = getView().findViewById(R.id.good_habits_layout_circle_aug11);
            View good_habits_layout_circle_aug12 = getView().findViewById(R.id.good_habits_layout_circle_aug12);
            View good_habits_layout_circle_aug13 = getView().findViewById(R.id.good_habits_layout_circle_aug13);
            View good_habits_layout_circle_aug14 = getView().findViewById(R.id.good_habits_layout_circle_aug14);
            View good_habits_layout_circle_aug15 = getView().findViewById(R.id.good_habits_layout_circle_aug15);
            View good_habits_layout_circle_aug16 = getView().findViewById(R.id.good_habits_layout_circle_aug16);
            View good_habits_layout_circle_aug17 = getView().findViewById(R.id.good_habits_layout_circle_aug17);
            View good_habits_layout_circle_aug18 = getView().findViewById(R.id.good_habits_layout_circle_aug18);
            View good_habits_layout_circle_aug19 = getView().findViewById(R.id.good_habits_layout_circle_aug19);
            View good_habits_layout_circle_aug20 = getView().findViewById(R.id.good_habits_layout_circle_aug20);
            View good_habits_layout_circle_aug21 = getView().findViewById(R.id.good_habits_layout_circle_aug21);
            View good_habits_layout_circle_aug22 = getView().findViewById(R.id.good_habits_layout_circle_aug22);
            View good_habits_layout_circle_aug23 = getView().findViewById(R.id.good_habits_layout_circle_aug23);
            View good_habits_layout_circle_aug24 = getView().findViewById(R.id.good_habits_layout_circle_aug24);
            View good_habits_layout_circle_aug25 = getView().findViewById(R.id.good_habits_layout_circle_aug25);
            View good_habits_layout_circle_aug26 = getView().findViewById(R.id.good_habits_layout_circle_aug26);
            View good_habits_layout_circle_aug27 = getView().findViewById(R.id.good_habits_layout_circle_aug27);
            View good_habits_layout_circle_aug28 = getView().findViewById(R.id.good_habits_layout_circle_aug28);
            View good_habits_layout_circle_aug29 = getView().findViewById(R.id.good_habits_layout_circle_aug29);
            View good_habits_layout_circle_aug30 = getView().findViewById(R.id.good_habits_layout_circle_aug30);
            View good_habits_layout_circle_aug31 = getView().findViewById(R.id.good_habits_layout_circle_aug31);
            View good_habits_layout_circle_sep1 = getView().findViewById(R.id.good_habits_layout_circle_sep1);
            View good_habits_layout_circle_sep2 = getView().findViewById(R.id.good_habits_layout_circle_sep2);
            View good_habits_layout_circle_sep3 = getView().findViewById(R.id.good_habits_layout_circle_sep3);
            View good_habits_layout_circle_sep4 = getView().findViewById(R.id.good_habits_layout_circle_sep4);
            View good_habits_layout_circle_sep5 = getView().findViewById(R.id.good_habits_layout_circle_sep5);
            View good_habits_layout_circle_sep6 = getView().findViewById(R.id.good_habits_layout_circle_sep6);
            View good_habits_layout_circle_sep7 = getView().findViewById(R.id.good_habits_layout_circle_sep7);
            View good_habits_layout_circle_sep8 = getView().findViewById(R.id.good_habits_layout_circle_sep8);
            View good_habits_layout_circle_sep9 = getView().findViewById(R.id.good_habits_layout_circle_sep9);
            View good_habits_layout_circle_sep10 = getView().findViewById(R.id.good_habits_layout_circle_sep10);
            View good_habits_layout_circle_sep11 = getView().findViewById(R.id.good_habits_layout_circle_sep11);
            View good_habits_layout_circle_sep12 = getView().findViewById(R.id.good_habits_layout_circle_sep12);
            View good_habits_layout_circle_sep13 = getView().findViewById(R.id.good_habits_layout_circle_sep13);
            View good_habits_layout_circle_sep14 = getView().findViewById(R.id.good_habits_layout_circle_sep14);
            View good_habits_layout_circle_sep15 = getView().findViewById(R.id.good_habits_layout_circle_sep15);
            View good_habits_layout_circle_sep16 = getView().findViewById(R.id.good_habits_layout_circle_sep16);
            View good_habits_layout_circle_sep17 = getView().findViewById(R.id.good_habits_layout_circle_sep17);
            View good_habits_layout_circle_sep18 = getView().findViewById(R.id.good_habits_layout_circle_sep18);
            View good_habits_layout_circle_sep19 = getView().findViewById(R.id.good_habits_layout_circle_sep19);
            View good_habits_layout_circle_sep20 = getView().findViewById(R.id.good_habits_layout_circle_sep20);
            View good_habits_layout_circle_sep21 = getView().findViewById(R.id.good_habits_layout_circle_sep21);
            View good_habits_layout_circle_sep22 = getView().findViewById(R.id.good_habits_layout_circle_sep22);
            View good_habits_layout_circle_sep23 = getView().findViewById(R.id.good_habits_layout_circle_sep23);
            View good_habits_layout_circle_sep24 = getView().findViewById(R.id.good_habits_layout_circle_sep24);
            View good_habits_layout_circle_sep25 = getView().findViewById(R.id.good_habits_layout_circle_sep25);
            View good_habits_layout_circle_sep26 = getView().findViewById(R.id.good_habits_layout_circle_sep26);
            View good_habits_layout_circle_sep27 = getView().findViewById(R.id.good_habits_layout_circle_sep27);
            View good_habits_layout_circle_sep28 = getView().findViewById(R.id.good_habits_layout_circle_sep28);
            View good_habits_layout_circle_sep29 = getView().findViewById(R.id.good_habits_layout_circle_sep29);
            View good_habits_layout_circle_sep30 = getView().findViewById(R.id.good_habits_layout_circle_sep30);
            View good_habits_layout_circle_oct1 = getView().findViewById(R.id.good_habits_layout_circle_oct1);
            View good_habits_layout_circle_oct2 = getView().findViewById(R.id.good_habits_layout_circle_oct2);
            View good_habits_layout_circle_oct3 = getView().findViewById(R.id.good_habits_layout_circle_oct3);
            View good_habits_layout_circle_oct4 = getView().findViewById(R.id.good_habits_layout_circle_oct4);
            View good_habits_layout_circle_oct5 = getView().findViewById(R.id.good_habits_layout_circle_oct5);
            View good_habits_layout_circle_oct6 = getView().findViewById(R.id.good_habits_layout_circle_oct6);
            View good_habits_layout_circle_oct7 = getView().findViewById(R.id.good_habits_layout_circle_oct7);
            View good_habits_layout_circle_oct8 = getView().findViewById(R.id.good_habits_layout_circle_oct8);
            View good_habits_layout_circle_oct9 = getView().findViewById(R.id.good_habits_layout_circle_oct9);
            View good_habits_layout_circle_oct10 = getView().findViewById(R.id.good_habits_layout_circle_oct10);
            View good_habits_layout_circle_oct11 = getView().findViewById(R.id.good_habits_layout_circle_oct11);
            View good_habits_layout_circle_oct12 = getView().findViewById(R.id.good_habits_layout_circle_oct12);
            View good_habits_layout_circle_oct13 = getView().findViewById(R.id.good_habits_layout_circle_oct13);
            View good_habits_layout_circle_oct14 = getView().findViewById(R.id.good_habits_layout_circle_oct14);
            View good_habits_layout_circle_oct15 = getView().findViewById(R.id.good_habits_layout_circle_oct15);
            View good_habits_layout_circle_oct16 = getView().findViewById(R.id.good_habits_layout_circle_oct16);
            View good_habits_layout_circle_oct17 = getView().findViewById(R.id.good_habits_layout_circle_oct17);
            View good_habits_layout_circle_oct18 = getView().findViewById(R.id.good_habits_layout_circle_oct18);
            View good_habits_layout_circle_oct19 = getView().findViewById(R.id.good_habits_layout_circle_oct19);
            View good_habits_layout_circle_oct20 = getView().findViewById(R.id.good_habits_layout_circle_oct20);
            View good_habits_layout_circle_oct21 = getView().findViewById(R.id.good_habits_layout_circle_oct21);
            View good_habits_layout_circle_oct22 = getView().findViewById(R.id.good_habits_layout_circle_oct22);
            View good_habits_layout_circle_oct23 = getView().findViewById(R.id.good_habits_layout_circle_oct23);
            View good_habits_layout_circle_oct24 = getView().findViewById(R.id.good_habits_layout_circle_oct24);
            View good_habits_layout_circle_oct25 = getView().findViewById(R.id.good_habits_layout_circle_oct25);
            View good_habits_layout_circle_oct26 = getView().findViewById(R.id.good_habits_layout_circle_oct26);
            View good_habits_layout_circle_oct27 = getView().findViewById(R.id.good_habits_layout_circle_oct27);
            View good_habits_layout_circle_oct28 = getView().findViewById(R.id.good_habits_layout_circle_oct28);
            View good_habits_layout_circle_oct29 = getView().findViewById(R.id.good_habits_layout_circle_oct29);
            View good_habits_layout_circle_oct30 = getView().findViewById(R.id.good_habits_layout_circle_oct30);
            View good_habits_layout_circle_oct31 = getView().findViewById(R.id.good_habits_layout_circle_oct31);
            View good_habits_layout_circle_nov1 = getView().findViewById(R.id.good_habits_layout_circle_nov1);
            View good_habits_layout_circle_nov2 = getView().findViewById(R.id.good_habits_layout_circle_nov2);
            View good_habits_layout_circle_nov3 = getView().findViewById(R.id.good_habits_layout_circle_nov3);
            View good_habits_layout_circle_nov4 = getView().findViewById(R.id.good_habits_layout_circle_nov4);
            View good_habits_layout_circle_nov5 = getView().findViewById(R.id.good_habits_layout_circle_nov5);
            View good_habits_layout_circle_nov6 = getView().findViewById(R.id.good_habits_layout_circle_nov6);
            View good_habits_layout_circle_nov7 = getView().findViewById(R.id.good_habits_layout_circle_nov7);
            View good_habits_layout_circle_nov8 = getView().findViewById(R.id.good_habits_layout_circle_nov8);
            View good_habits_layout_circle_nov9 = getView().findViewById(R.id.good_habits_layout_circle_nov9);
            View good_habits_layout_circle_nov10 = getView().findViewById(R.id.good_habits_layout_circle_nov10);
            View good_habits_layout_circle_nov11 = getView().findViewById(R.id.good_habits_layout_circle_nov11);
            View good_habits_layout_circle_nov12 = getView().findViewById(R.id.good_habits_layout_circle_nov12);
            View good_habits_layout_circle_nov13 = getView().findViewById(R.id.good_habits_layout_circle_nov13);
            View good_habits_layout_circle_nov14 = getView().findViewById(R.id.good_habits_layout_circle_nov14);
            View good_habits_layout_circle_nov15 = getView().findViewById(R.id.good_habits_layout_circle_nov15);
            View good_habits_layout_circle_nov16 = getView().findViewById(R.id.good_habits_layout_circle_nov16);
            View good_habits_layout_circle_nov17 = getView().findViewById(R.id.good_habits_layout_circle_nov17);
            View good_habits_layout_circle_nov18 = getView().findViewById(R.id.good_habits_layout_circle_nov18);
            View good_habits_layout_circle_nov19 = getView().findViewById(R.id.good_habits_layout_circle_nov19);
            View good_habits_layout_circle_nov20 = getView().findViewById(R.id.good_habits_layout_circle_nov20);
            View good_habits_layout_circle_nov21 = getView().findViewById(R.id.good_habits_layout_circle_nov21);
            View good_habits_layout_circle_nov22 = getView().findViewById(R.id.good_habits_layout_circle_nov22);
            View good_habits_layout_circle_nov23 = getView().findViewById(R.id.good_habits_layout_circle_nov23);
            View good_habits_layout_circle_nov24 = getView().findViewById(R.id.good_habits_layout_circle_nov24);
            View good_habits_layout_circle_nov25 = getView().findViewById(R.id.good_habits_layout_circle_nov25);
            View good_habits_layout_circle_nov26 = getView().findViewById(R.id.good_habits_layout_circle_nov26);
            View good_habits_layout_circle_nov27 = getView().findViewById(R.id.good_habits_layout_circle_nov27);
            View good_habits_layout_circle_nov28 = getView().findViewById(R.id.good_habits_layout_circle_nov28);
            View good_habits_layout_circle_nov29 = getView().findViewById(R.id.good_habits_layout_circle_nov29);
            View good_habits_layout_circle_nov30 = getView().findViewById(R.id.good_habits_layout_circle_nov30);
            View good_habits_layout_circle_dec1 = getView().findViewById(R.id.good_habits_layout_circle_dec1);
            View good_habits_layout_circle_dec2 = getView().findViewById(R.id.good_habits_layout_circle_dec2);
            View good_habits_layout_circle_dec3 = getView().findViewById(R.id.good_habits_layout_circle_dec3);
            View good_habits_layout_circle_dec4 = getView().findViewById(R.id.good_habits_layout_circle_dec4);
            View good_habits_layout_circle_dec5 = getView().findViewById(R.id.good_habits_layout_circle_dec5);
            View good_habits_layout_circle_dec6 = getView().findViewById(R.id.good_habits_layout_circle_dec6);
            View good_habits_layout_circle_dec7 = getView().findViewById(R.id.good_habits_layout_circle_dec7);
            View good_habits_layout_circle_dec8 = getView().findViewById(R.id.good_habits_layout_circle_dec8);
            View good_habits_layout_circle_dec9 = getView().findViewById(R.id.good_habits_layout_circle_dec9);
            View good_habits_layout_circle_dec10 = getView().findViewById(R.id.good_habits_layout_circle_dec10);
            View good_habits_layout_circle_dec11 = getView().findViewById(R.id.good_habits_layout_circle_dec11);
            View good_habits_layout_circle_dec12 = getView().findViewById(R.id.good_habits_layout_circle_dec12);
            View good_habits_layout_circle_dec13 = getView().findViewById(R.id.good_habits_layout_circle_dec13);
            View good_habits_layout_circle_dec14 = getView().findViewById(R.id.good_habits_layout_circle_dec14);
            View good_habits_layout_circle_dec15 = getView().findViewById(R.id.good_habits_layout_circle_dec15);
            View good_habits_layout_circle_dec16 = getView().findViewById(R.id.good_habits_layout_circle_dec16);
            View good_habits_layout_circle_dec17 = getView().findViewById(R.id.good_habits_layout_circle_dec17);
            View good_habits_layout_circle_dec18 = getView().findViewById(R.id.good_habits_layout_circle_dec18);
            View good_habits_layout_circle_dec19 = getView().findViewById(R.id.good_habits_layout_circle_dec19);
            View good_habits_layout_circle_dec20 = getView().findViewById(R.id.good_habits_layout_circle_dec20);
            View good_habits_layout_circle_dec21 = getView().findViewById(R.id.good_habits_layout_circle_dec21);
            View good_habits_layout_circle_dec22 = getView().findViewById(R.id.good_habits_layout_circle_dec22);
            View good_habits_layout_circle_dec23 = getView().findViewById(R.id.good_habits_layout_circle_dec23);
            View good_habits_layout_circle_dec24 = getView().findViewById(R.id.good_habits_layout_circle_dec24);
            View good_habits_layout_circle_dec25 = getView().findViewById(R.id.good_habits_layout_circle_dec25);
            View good_habits_layout_circle_dec26 = getView().findViewById(R.id.good_habits_layout_circle_dec26);
            View good_habits_layout_circle_dec27 = getView().findViewById(R.id.good_habits_layout_circle_dec27);
            View good_habits_layout_circle_dec28 = getView().findViewById(R.id.good_habits_layout_circle_dec28);
            View good_habits_layout_circle_dec29 = getView().findViewById(R.id.good_habits_layout_circle_dec29);
            View good_habits_layout_circle_dec30 = getView().findViewById(R.id.good_habits_layout_circle_dec30);
            View good_habits_layout_circle_dec31 = getView().findViewById(R.id.good_habits_layout_circle_dec31);
            list_of_all_the_calender_views = new View[366];
            list_of_all_the_calender_views[0] = good_habits_layout_circle_jan1;
            list_of_all_the_calender_views[1] = good_habits_layout_circle_jan2;
            list_of_all_the_calender_views[2] = good_habits_layout_circle_jan3;
            list_of_all_the_calender_views[3] = good_habits_layout_circle_jan4;
            list_of_all_the_calender_views[4] = good_habits_layout_circle_jan5;
            list_of_all_the_calender_views[5] = good_habits_layout_circle_jan6;
            list_of_all_the_calender_views[6] = good_habits_layout_circle_jan7;
            list_of_all_the_calender_views[7] = good_habits_layout_circle_jan8;
            list_of_all_the_calender_views[8] = good_habits_layout_circle_jan9;
            list_of_all_the_calender_views[9] = good_habits_layout_circle_jan10;
            list_of_all_the_calender_views[10] = good_habits_layout_circle_jan11;
            list_of_all_the_calender_views[11] = good_habits_layout_circle_jan12;
            list_of_all_the_calender_views[12] = good_habits_layout_circle_jan13;
            list_of_all_the_calender_views[13] = good_habits_layout_circle_jan14;
            list_of_all_the_calender_views[14] = good_habits_layout_circle_jan15;
            list_of_all_the_calender_views[15] = good_habits_layout_circle_jan16;
            list_of_all_the_calender_views[16] = good_habits_layout_circle_jan17;
            list_of_all_the_calender_views[17] = good_habits_layout_circle_jan18;
            list_of_all_the_calender_views[18] = good_habits_layout_circle_jan19;
            list_of_all_the_calender_views[19] = good_habits_layout_circle_jan20;
            list_of_all_the_calender_views[20] = good_habits_layout_circle_jan21;
            list_of_all_the_calender_views[21] = good_habits_layout_circle_jan22;
            list_of_all_the_calender_views[22] = good_habits_layout_circle_jan23;
            list_of_all_the_calender_views[23] = good_habits_layout_circle_jan24;
            list_of_all_the_calender_views[24] = good_habits_layout_circle_jan25;
            list_of_all_the_calender_views[25] = good_habits_layout_circle_jan26;
            list_of_all_the_calender_views[26] = good_habits_layout_circle_jan27;
            list_of_all_the_calender_views[27] = good_habits_layout_circle_jan28;
            list_of_all_the_calender_views[28] = good_habits_layout_circle_jan29;
            list_of_all_the_calender_views[29] = good_habits_layout_circle_jan30;
            list_of_all_the_calender_views[30] = good_habits_layout_circle_jan31;
            list_of_all_the_calender_views[31] = good_habits_layout_circle_feb1;
            list_of_all_the_calender_views[32] = good_habits_layout_circle_feb2;
            list_of_all_the_calender_views[33] = good_habits_layout_circle_feb3;
            list_of_all_the_calender_views[34] = good_habits_layout_circle_feb4;
            list_of_all_the_calender_views[35] = good_habits_layout_circle_feb5;
            list_of_all_the_calender_views[36] = good_habits_layout_circle_feb6;
            list_of_all_the_calender_views[37] = good_habits_layout_circle_feb7;
            list_of_all_the_calender_views[38] = good_habits_layout_circle_feb8;
            list_of_all_the_calender_views[39] = good_habits_layout_circle_feb9;
            list_of_all_the_calender_views[40] = good_habits_layout_circle_feb10;
            list_of_all_the_calender_views[41] = good_habits_layout_circle_feb11;
            list_of_all_the_calender_views[42] = good_habits_layout_circle_feb12;
            list_of_all_the_calender_views[43] = good_habits_layout_circle_feb13;
            list_of_all_the_calender_views[44] = good_habits_layout_circle_feb14;
            list_of_all_the_calender_views[45] = good_habits_layout_circle_feb15;
            list_of_all_the_calender_views[46] = good_habits_layout_circle_feb16;
            list_of_all_the_calender_views[47] = good_habits_layout_circle_feb17;
            list_of_all_the_calender_views[48] = good_habits_layout_circle_feb18;
            list_of_all_the_calender_views[49] = good_habits_layout_circle_feb19;
            list_of_all_the_calender_views[50] = good_habits_layout_circle_feb20;
            list_of_all_the_calender_views[51] = good_habits_layout_circle_feb21;
            list_of_all_the_calender_views[52] = good_habits_layout_circle_feb22;
            list_of_all_the_calender_views[53] = good_habits_layout_circle_feb23;
            list_of_all_the_calender_views[54] = good_habits_layout_circle_feb24;
            list_of_all_the_calender_views[55] = good_habits_layout_circle_feb25;
            list_of_all_the_calender_views[56] = good_habits_layout_circle_feb26;
            list_of_all_the_calender_views[57] = good_habits_layout_circle_feb27;
            list_of_all_the_calender_views[58] = good_habits_layout_circle_feb28;
            list_of_all_the_calender_views[59] = good_habits_layout_circle_feb29;
            list_of_all_the_calender_views[60] = good_habits_layout_circle_mar1;
            list_of_all_the_calender_views[61] = good_habits_layout_circle_mar2;
            list_of_all_the_calender_views[62] = good_habits_layout_circle_mar3;
            list_of_all_the_calender_views[63] = good_habits_layout_circle_mar4;
            list_of_all_the_calender_views[64] = good_habits_layout_circle_mar5;
            list_of_all_the_calender_views[65] = good_habits_layout_circle_mar6;
            list_of_all_the_calender_views[66] = good_habits_layout_circle_mar7;
            list_of_all_the_calender_views[67] = good_habits_layout_circle_mar8;
            list_of_all_the_calender_views[68] = good_habits_layout_circle_mar9;
            list_of_all_the_calender_views[69] = good_habits_layout_circle_mar10;
            list_of_all_the_calender_views[70] = good_habits_layout_circle_mar11;
            list_of_all_the_calender_views[71] = good_habits_layout_circle_mar12;
            list_of_all_the_calender_views[72] = good_habits_layout_circle_mar13;
            list_of_all_the_calender_views[73] = good_habits_layout_circle_mar14;
            list_of_all_the_calender_views[74] = good_habits_layout_circle_mar15;
            list_of_all_the_calender_views[75] = good_habits_layout_circle_mar16;
            list_of_all_the_calender_views[76] = good_habits_layout_circle_mar17;
            list_of_all_the_calender_views[77] = good_habits_layout_circle_mar18;
            list_of_all_the_calender_views[78] = good_habits_layout_circle_mar19;
            list_of_all_the_calender_views[79] = good_habits_layout_circle_mar20;
            list_of_all_the_calender_views[80] = good_habits_layout_circle_mar21;
            list_of_all_the_calender_views[81] = good_habits_layout_circle_mar22;
            list_of_all_the_calender_views[82] = good_habits_layout_circle_mar23;
            list_of_all_the_calender_views[83] = good_habits_layout_circle_mar24;
            list_of_all_the_calender_views[84] = good_habits_layout_circle_mar25;
            list_of_all_the_calender_views[85] = good_habits_layout_circle_mar26;
            list_of_all_the_calender_views[86] = good_habits_layout_circle_mar27;
            list_of_all_the_calender_views[87] = good_habits_layout_circle_mar28;
            list_of_all_the_calender_views[88] = good_habits_layout_circle_mar29;
            list_of_all_the_calender_views[89] = good_habits_layout_circle_mar30;
            list_of_all_the_calender_views[90] = good_habits_layout_circle_mar31;
            list_of_all_the_calender_views[91] = good_habits_layout_circle_apr1;
            list_of_all_the_calender_views[92] = good_habits_layout_circle_apr2;
            list_of_all_the_calender_views[93] = good_habits_layout_circle_apr3;
            list_of_all_the_calender_views[94] = good_habits_layout_circle_apr4;
            list_of_all_the_calender_views[95] = good_habits_layout_circle_apr5;
            list_of_all_the_calender_views[96] = good_habits_layout_circle_apr6;
            list_of_all_the_calender_views[97] = good_habits_layout_circle_apr7;
            list_of_all_the_calender_views[98] = good_habits_layout_circle_apr8;
            list_of_all_the_calender_views[99] = good_habits_layout_circle_apr9;
            list_of_all_the_calender_views[100] = good_habits_layout_circle_apr10;
            list_of_all_the_calender_views[101] = good_habits_layout_circle_apr11;
            list_of_all_the_calender_views[102] = good_habits_layout_circle_apr12;
            list_of_all_the_calender_views[103] = good_habits_layout_circle_apr13;
            list_of_all_the_calender_views[104] = good_habits_layout_circle_apr14;
            list_of_all_the_calender_views[105] = good_habits_layout_circle_apr15;
            list_of_all_the_calender_views[106] = good_habits_layout_circle_apr16;
            list_of_all_the_calender_views[107] = good_habits_layout_circle_apr17;
            list_of_all_the_calender_views[108] = good_habits_layout_circle_apr18;
            list_of_all_the_calender_views[109] = good_habits_layout_circle_apr19;
            list_of_all_the_calender_views[110] = good_habits_layout_circle_apr20;
            list_of_all_the_calender_views[111] = good_habits_layout_circle_apr21;
            list_of_all_the_calender_views[112] = good_habits_layout_circle_apr22;
            list_of_all_the_calender_views[113] = good_habits_layout_circle_apr23;
            list_of_all_the_calender_views[114] = good_habits_layout_circle_apr24;
            list_of_all_the_calender_views[115] = good_habits_layout_circle_apr25;
            list_of_all_the_calender_views[116] = good_habits_layout_circle_apr26;
            list_of_all_the_calender_views[117] = good_habits_layout_circle_apr27;
            list_of_all_the_calender_views[118] = good_habits_layout_circle_apr28;
            list_of_all_the_calender_views[119] = good_habits_layout_circle_apr29;
            list_of_all_the_calender_views[120] = good_habits_layout_circle_apr30;
            list_of_all_the_calender_views[121] = good_habits_layout_circle_may1;
            list_of_all_the_calender_views[122] = good_habits_layout_circle_may2;
            list_of_all_the_calender_views[123] = good_habits_layout_circle_may3;
            list_of_all_the_calender_views[124] = good_habits_layout_circle_may4;
            list_of_all_the_calender_views[125] = good_habits_layout_circle_may5;
            list_of_all_the_calender_views[126] = good_habits_layout_circle_may6;
            list_of_all_the_calender_views[127] = good_habits_layout_circle_may7;
            list_of_all_the_calender_views[128] = good_habits_layout_circle_may8;
            list_of_all_the_calender_views[129] = good_habits_layout_circle_may9;
            list_of_all_the_calender_views[130] = good_habits_layout_circle_may10;
            list_of_all_the_calender_views[131] = good_habits_layout_circle_may11;
            list_of_all_the_calender_views[132] = good_habits_layout_circle_may12;
            list_of_all_the_calender_views[133] = good_habits_layout_circle_may13;
            list_of_all_the_calender_views[134] = good_habits_layout_circle_may14;
            list_of_all_the_calender_views[135] = good_habits_layout_circle_may15;
            list_of_all_the_calender_views[136] = good_habits_layout_circle_may16;
            list_of_all_the_calender_views[137] = good_habits_layout_circle_may17;
            list_of_all_the_calender_views[138] = good_habits_layout_circle_may18;
            list_of_all_the_calender_views[139] = good_habits_layout_circle_may19;
            list_of_all_the_calender_views[140] = good_habits_layout_circle_may20;
            list_of_all_the_calender_views[141] = good_habits_layout_circle_may21;
            list_of_all_the_calender_views[142] = good_habits_layout_circle_may22;
            list_of_all_the_calender_views[143] = good_habits_layout_circle_may23;
            list_of_all_the_calender_views[144] = good_habits_layout_circle_may24;
            list_of_all_the_calender_views[145] = good_habits_layout_circle_may25;
            list_of_all_the_calender_views[146] = good_habits_layout_circle_may26;
            list_of_all_the_calender_views[147] = good_habits_layout_circle_may27;
            list_of_all_the_calender_views[148] = good_habits_layout_circle_may28;
            list_of_all_the_calender_views[149] = good_habits_layout_circle_may29;
            list_of_all_the_calender_views[150] = good_habits_layout_circle_may30;
            list_of_all_the_calender_views[151] = good_habits_layout_circle_may31;
            list_of_all_the_calender_views[152] = good_habits_layout_circle_jun1;
            list_of_all_the_calender_views[153] = good_habits_layout_circle_jun2;
            list_of_all_the_calender_views[154] = good_habits_layout_circle_jun3;
            list_of_all_the_calender_views[155] = good_habits_layout_circle_jun4;
            list_of_all_the_calender_views[156] = good_habits_layout_circle_jun5;
            list_of_all_the_calender_views[157] = good_habits_layout_circle_jun6;
            list_of_all_the_calender_views[158] = good_habits_layout_circle_jun7;
            list_of_all_the_calender_views[159] = good_habits_layout_circle_jun8;
            list_of_all_the_calender_views[160] = good_habits_layout_circle_jun9;
            list_of_all_the_calender_views[161] = good_habits_layout_circle_jun10;
            list_of_all_the_calender_views[162] = good_habits_layout_circle_jun11;
            list_of_all_the_calender_views[163] = good_habits_layout_circle_jun12;
            list_of_all_the_calender_views[164] = good_habits_layout_circle_jun13;
            list_of_all_the_calender_views[165] = good_habits_layout_circle_jun14;
            list_of_all_the_calender_views[166] = good_habits_layout_circle_jun15;
            list_of_all_the_calender_views[167] = good_habits_layout_circle_jun16;
            list_of_all_the_calender_views[168] = good_habits_layout_circle_jun17;
            list_of_all_the_calender_views[169] = good_habits_layout_circle_jun18;
            list_of_all_the_calender_views[170] = good_habits_layout_circle_jun19;
            list_of_all_the_calender_views[171] = good_habits_layout_circle_jun20;
            list_of_all_the_calender_views[172] = good_habits_layout_circle_jun21;
            list_of_all_the_calender_views[173] = good_habits_layout_circle_jun22;
            list_of_all_the_calender_views[174] = good_habits_layout_circle_jun23;
            list_of_all_the_calender_views[175] = good_habits_layout_circle_jun24;
            list_of_all_the_calender_views[176] = good_habits_layout_circle_jun25;
            list_of_all_the_calender_views[177] = good_habits_layout_circle_jun26;
            list_of_all_the_calender_views[178] = good_habits_layout_circle_jun27;
            list_of_all_the_calender_views[179] = good_habits_layout_circle_jun28;
            list_of_all_the_calender_views[180] = good_habits_layout_circle_jun29;
            list_of_all_the_calender_views[181] = good_habits_layout_circle_jun30;
            list_of_all_the_calender_views[182] = good_habits_layout_circle_jul1;
            list_of_all_the_calender_views[183] = good_habits_layout_circle_jul2;
            list_of_all_the_calender_views[184] = good_habits_layout_circle_jul3;
            list_of_all_the_calender_views[185] = good_habits_layout_circle_jul4;
            list_of_all_the_calender_views[186] = good_habits_layout_circle_jul5;
            list_of_all_the_calender_views[187] = good_habits_layout_circle_jul6;
            list_of_all_the_calender_views[188] = good_habits_layout_circle_jul7;
            list_of_all_the_calender_views[189] = good_habits_layout_circle_jul8;
            list_of_all_the_calender_views[190] = good_habits_layout_circle_jul9;
            list_of_all_the_calender_views[191] = good_habits_layout_circle_jul10;
            list_of_all_the_calender_views[192] = good_habits_layout_circle_jul11;
            list_of_all_the_calender_views[193] = good_habits_layout_circle_jul12;
            list_of_all_the_calender_views[194] = good_habits_layout_circle_jul13;
            list_of_all_the_calender_views[195] = good_habits_layout_circle_jul14;
            list_of_all_the_calender_views[196] = good_habits_layout_circle_jul15;
            list_of_all_the_calender_views[197] = good_habits_layout_circle_jul16;
            list_of_all_the_calender_views[198] = good_habits_layout_circle_jul17;
            list_of_all_the_calender_views[199] = good_habits_layout_circle_jul18;
            list_of_all_the_calender_views[200] = good_habits_layout_circle_jul19;
            list_of_all_the_calender_views[201] = good_habits_layout_circle_jul20;
            list_of_all_the_calender_views[202] = good_habits_layout_circle_jul21;
            list_of_all_the_calender_views[203] = good_habits_layout_circle_jul22;
            list_of_all_the_calender_views[204] = good_habits_layout_circle_jul23;
            list_of_all_the_calender_views[205] = good_habits_layout_circle_jul24;
            list_of_all_the_calender_views[206] = good_habits_layout_circle_jul25;
            list_of_all_the_calender_views[207] = good_habits_layout_circle_jul26;
            list_of_all_the_calender_views[208] = good_habits_layout_circle_jul27;
            list_of_all_the_calender_views[209] = good_habits_layout_circle_jul28;
            list_of_all_the_calender_views[210] = good_habits_layout_circle_jul29;
            list_of_all_the_calender_views[211] = good_habits_layout_circle_jul30;
            list_of_all_the_calender_views[212] = good_habits_layout_circle_jul31;
            list_of_all_the_calender_views[213] = good_habits_layout_circle_aug1;
            list_of_all_the_calender_views[214] = good_habits_layout_circle_aug2;
            list_of_all_the_calender_views[215] = good_habits_layout_circle_aug3;
            list_of_all_the_calender_views[216] = good_habits_layout_circle_aug4;
            list_of_all_the_calender_views[217] = good_habits_layout_circle_aug5;
            list_of_all_the_calender_views[218] = good_habits_layout_circle_aug6;
            list_of_all_the_calender_views[219] = good_habits_layout_circle_aug7;
            list_of_all_the_calender_views[220] = good_habits_layout_circle_aug8;
            list_of_all_the_calender_views[221] = good_habits_layout_circle_aug9;
            list_of_all_the_calender_views[222] = good_habits_layout_circle_aug10;
            list_of_all_the_calender_views[223] = good_habits_layout_circle_aug11;
            list_of_all_the_calender_views[224] = good_habits_layout_circle_aug12;
            list_of_all_the_calender_views[225] = good_habits_layout_circle_aug13;
            list_of_all_the_calender_views[226] = good_habits_layout_circle_aug14;
            list_of_all_the_calender_views[227] = good_habits_layout_circle_aug15;
            list_of_all_the_calender_views[228] = good_habits_layout_circle_aug16;
            list_of_all_the_calender_views[229] = good_habits_layout_circle_aug17;
            list_of_all_the_calender_views[230] = good_habits_layout_circle_aug18;
            list_of_all_the_calender_views[231] = good_habits_layout_circle_aug19;
            list_of_all_the_calender_views[232] = good_habits_layout_circle_aug20;
            list_of_all_the_calender_views[233] = good_habits_layout_circle_aug21;
            list_of_all_the_calender_views[234] = good_habits_layout_circle_aug22;
            list_of_all_the_calender_views[235] = good_habits_layout_circle_aug23;
            list_of_all_the_calender_views[236] = good_habits_layout_circle_aug24;
            list_of_all_the_calender_views[237] = good_habits_layout_circle_aug25;
            list_of_all_the_calender_views[238] = good_habits_layout_circle_aug26;
            list_of_all_the_calender_views[239] = good_habits_layout_circle_aug27;
            list_of_all_the_calender_views[240] = good_habits_layout_circle_aug28;
            list_of_all_the_calender_views[241] = good_habits_layout_circle_aug29;
            list_of_all_the_calender_views[242] = good_habits_layout_circle_aug30;
            list_of_all_the_calender_views[243] = good_habits_layout_circle_aug31;
            list_of_all_the_calender_views[244] = good_habits_layout_circle_sep1;
            list_of_all_the_calender_views[245] = good_habits_layout_circle_sep2;
            list_of_all_the_calender_views[246] = good_habits_layout_circle_sep3;
            list_of_all_the_calender_views[247] = good_habits_layout_circle_sep4;
            list_of_all_the_calender_views[248] = good_habits_layout_circle_sep5;
            list_of_all_the_calender_views[249] = good_habits_layout_circle_sep6;
            list_of_all_the_calender_views[250] = good_habits_layout_circle_sep7;
            list_of_all_the_calender_views[251] = good_habits_layout_circle_sep8;
            list_of_all_the_calender_views[252] = good_habits_layout_circle_sep9;
            list_of_all_the_calender_views[253] = good_habits_layout_circle_sep10;
            list_of_all_the_calender_views[254] = good_habits_layout_circle_sep11;
            list_of_all_the_calender_views[255] = good_habits_layout_circle_sep12;
            list_of_all_the_calender_views[256] = good_habits_layout_circle_sep13;
            list_of_all_the_calender_views[257] = good_habits_layout_circle_sep14;
            list_of_all_the_calender_views[258] = good_habits_layout_circle_sep15;
            list_of_all_the_calender_views[259] = good_habits_layout_circle_sep16;
            list_of_all_the_calender_views[260] = good_habits_layout_circle_sep17;
            list_of_all_the_calender_views[261] = good_habits_layout_circle_sep18;
            list_of_all_the_calender_views[262] = good_habits_layout_circle_sep19;
            list_of_all_the_calender_views[263] = good_habits_layout_circle_sep20;
            list_of_all_the_calender_views[264] = good_habits_layout_circle_sep21;
            list_of_all_the_calender_views[265] = good_habits_layout_circle_sep22;
            list_of_all_the_calender_views[266] = good_habits_layout_circle_sep23;
            list_of_all_the_calender_views[267] = good_habits_layout_circle_sep24;
            list_of_all_the_calender_views[268] = good_habits_layout_circle_sep25;
            list_of_all_the_calender_views[269] = good_habits_layout_circle_sep26;
            list_of_all_the_calender_views[270] = good_habits_layout_circle_sep27;
            list_of_all_the_calender_views[271] = good_habits_layout_circle_sep28;
            list_of_all_the_calender_views[272] = good_habits_layout_circle_sep29;
            list_of_all_the_calender_views[273] = good_habits_layout_circle_sep30;
            list_of_all_the_calender_views[274] = good_habits_layout_circle_oct1;
            list_of_all_the_calender_views[275] = good_habits_layout_circle_oct2;
            list_of_all_the_calender_views[276] = good_habits_layout_circle_oct3;
            list_of_all_the_calender_views[277] = good_habits_layout_circle_oct4;
            list_of_all_the_calender_views[278] = good_habits_layout_circle_oct5;
            list_of_all_the_calender_views[279] = good_habits_layout_circle_oct6;
            list_of_all_the_calender_views[280] = good_habits_layout_circle_oct7;
            list_of_all_the_calender_views[281] = good_habits_layout_circle_oct8;
            list_of_all_the_calender_views[282] = good_habits_layout_circle_oct9;
            list_of_all_the_calender_views[283] = good_habits_layout_circle_oct10;
            list_of_all_the_calender_views[284] = good_habits_layout_circle_oct11;
            list_of_all_the_calender_views[285] = good_habits_layout_circle_oct12;
            list_of_all_the_calender_views[286] = good_habits_layout_circle_oct13;
            list_of_all_the_calender_views[287] = good_habits_layout_circle_oct14;
            list_of_all_the_calender_views[288] = good_habits_layout_circle_oct15;
            list_of_all_the_calender_views[289] = good_habits_layout_circle_oct16;
            list_of_all_the_calender_views[290] = good_habits_layout_circle_oct17;
            list_of_all_the_calender_views[291] = good_habits_layout_circle_oct18;
            list_of_all_the_calender_views[292] = good_habits_layout_circle_oct19;
            list_of_all_the_calender_views[293] = good_habits_layout_circle_oct20;
            list_of_all_the_calender_views[294] = good_habits_layout_circle_oct21;
            list_of_all_the_calender_views[295] = good_habits_layout_circle_oct22;
            list_of_all_the_calender_views[296] = good_habits_layout_circle_oct23;
            list_of_all_the_calender_views[297] = good_habits_layout_circle_oct24;
            list_of_all_the_calender_views[298] = good_habits_layout_circle_oct25;
            list_of_all_the_calender_views[299] = good_habits_layout_circle_oct26;
            list_of_all_the_calender_views[300] = good_habits_layout_circle_oct27;
            list_of_all_the_calender_views[301] = good_habits_layout_circle_oct28;
            list_of_all_the_calender_views[302] = good_habits_layout_circle_oct29;
            list_of_all_the_calender_views[303] = good_habits_layout_circle_oct30;
            list_of_all_the_calender_views[304] = good_habits_layout_circle_oct31;
            list_of_all_the_calender_views[305] = good_habits_layout_circle_nov1;
            list_of_all_the_calender_views[306] = good_habits_layout_circle_nov2;
            list_of_all_the_calender_views[307] = good_habits_layout_circle_nov3;
            list_of_all_the_calender_views[308] = good_habits_layout_circle_nov4;
            list_of_all_the_calender_views[309] = good_habits_layout_circle_nov5;
            list_of_all_the_calender_views[310] = good_habits_layout_circle_nov6;
            list_of_all_the_calender_views[311] = good_habits_layout_circle_nov7;
            list_of_all_the_calender_views[312] = good_habits_layout_circle_nov8;
            list_of_all_the_calender_views[313] = good_habits_layout_circle_nov9;
            list_of_all_the_calender_views[314] = good_habits_layout_circle_nov10;
            list_of_all_the_calender_views[315] = good_habits_layout_circle_nov11;
            list_of_all_the_calender_views[316] = good_habits_layout_circle_nov12;
            list_of_all_the_calender_views[317] = good_habits_layout_circle_nov13;
            list_of_all_the_calender_views[318] = good_habits_layout_circle_nov14;
            list_of_all_the_calender_views[319] = good_habits_layout_circle_nov15;
            list_of_all_the_calender_views[320] = good_habits_layout_circle_nov16;
            list_of_all_the_calender_views[321] = good_habits_layout_circle_nov17;
            list_of_all_the_calender_views[322] = good_habits_layout_circle_nov18;
            list_of_all_the_calender_views[323] = good_habits_layout_circle_nov19;
            list_of_all_the_calender_views[324] = good_habits_layout_circle_nov20;
            list_of_all_the_calender_views[325] = good_habits_layout_circle_nov21;
            list_of_all_the_calender_views[326] = good_habits_layout_circle_nov22;
            list_of_all_the_calender_views[327] = good_habits_layout_circle_nov23;
            list_of_all_the_calender_views[328] = good_habits_layout_circle_nov24;
            list_of_all_the_calender_views[329] = good_habits_layout_circle_nov25;
            list_of_all_the_calender_views[330] = good_habits_layout_circle_nov26;
            list_of_all_the_calender_views[331] = good_habits_layout_circle_nov27;
            list_of_all_the_calender_views[332] = good_habits_layout_circle_nov28;
            list_of_all_the_calender_views[333] = good_habits_layout_circle_nov29;
            list_of_all_the_calender_views[334] = good_habits_layout_circle_nov30;
            list_of_all_the_calender_views[335] = good_habits_layout_circle_dec1;
            list_of_all_the_calender_views[336] = good_habits_layout_circle_dec2;
            list_of_all_the_calender_views[337] = good_habits_layout_circle_dec3;
            list_of_all_the_calender_views[338] = good_habits_layout_circle_dec4;
            list_of_all_the_calender_views[339] = good_habits_layout_circle_dec5;
            list_of_all_the_calender_views[340] = good_habits_layout_circle_dec6;
            list_of_all_the_calender_views[341] = good_habits_layout_circle_dec7;
            list_of_all_the_calender_views[342] = good_habits_layout_circle_dec8;
            list_of_all_the_calender_views[343] = good_habits_layout_circle_dec9;
            list_of_all_the_calender_views[344] = good_habits_layout_circle_dec10;
            list_of_all_the_calender_views[345] = good_habits_layout_circle_dec11;
            list_of_all_the_calender_views[346] = good_habits_layout_circle_dec12;
            list_of_all_the_calender_views[347] = good_habits_layout_circle_dec13;
            list_of_all_the_calender_views[348] = good_habits_layout_circle_dec14;
            list_of_all_the_calender_views[349] = good_habits_layout_circle_dec15;
            list_of_all_the_calender_views[350] = good_habits_layout_circle_dec16;
            list_of_all_the_calender_views[351] = good_habits_layout_circle_dec17;
            list_of_all_the_calender_views[352] = good_habits_layout_circle_dec18;
            list_of_all_the_calender_views[353] = good_habits_layout_circle_dec19;
            list_of_all_the_calender_views[354] = good_habits_layout_circle_dec20;
            list_of_all_the_calender_views[355] = good_habits_layout_circle_dec21;
            list_of_all_the_calender_views[356] = good_habits_layout_circle_dec22;
            list_of_all_the_calender_views[357] = good_habits_layout_circle_dec23;
            list_of_all_the_calender_views[358] = good_habits_layout_circle_dec24;
            list_of_all_the_calender_views[359] = good_habits_layout_circle_dec25;
            list_of_all_the_calender_views[360] = good_habits_layout_circle_dec26;
            list_of_all_the_calender_views[361] = good_habits_layout_circle_dec27;
            list_of_all_the_calender_views[362] = good_habits_layout_circle_dec28;
            list_of_all_the_calender_views[363] = good_habits_layout_circle_dec29;
            list_of_all_the_calender_views[364] = good_habits_layout_circle_dec30;
            list_of_all_the_calender_views[365] = good_habits_layout_circle_dec31;
        }
    }

    private void forward_and_back_button_listen() {
        if (getView() != null) {
            Button button_shadow_for_the_back_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.button_shadow_for_the_back_for_good_habits_for_the_full_year_view);
            Button button_shadow_for_the_front_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.button_shadow_for_the_front_for_good_habits_for_the_full_year_view);
            final TextView text_saying_which_year_to_show_in_a_good_habits_year = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year);
            button_shadow_for_the_back_for_good_habits_for_the_full_year_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    text_saying_which_year_to_show_in_a_good_habits_year.setText(String.valueOf(Integer.parseInt(text_saying_which_year_to_show_in_a_good_habits_year.getText().toString()) - 1));
                    set_the_buttons();
                    set_the_leap_year();
                    restart_all_the_year_values();
                    put_values_into_year_in_good_habits();
                }
            });
            button_shadow_for_the_front_for_good_habits_for_the_full_year_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    text_saying_which_year_to_show_in_a_good_habits_year.setText(String.valueOf(Integer.parseInt(text_saying_which_year_to_show_in_a_good_habits_year.getText().toString()) + 1));
                    set_the_buttons();
                    set_the_leap_year();
                    restart_all_the_year_values();
                    put_values_into_year_in_good_habits();
                }
            });
        }
    }

    private void set_the_leap_year() {
        if (getView() != null) {
            TextView text_saying_which_year_to_show_in_a_good_habits_year = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year);
            View good_habits_layout_circle_feb29 = getView().findViewById(R.id.good_habits_layout_circle_feb29);
            View backward_button_over_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.backward_button_over_for_good_habits_for_the_full_year_view);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(text_saying_which_year_to_show_in_a_good_habits_year.getText().toString()));
            if (calendar.getActualMaximum(Calendar.DAY_OF_YEAR) > 365) {
                if (backward_button_over_for_good_habits_for_the_full_year_view.getVisibility() == View.VISIBLE) {
                    good_habits_layout_circle_feb29.setVisibility(View.VISIBLE);
                } else {
                    calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
                    calendar.set(Calendar.DAY_OF_MONTH, 29);
                    if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) >= Simplify_the_time.return_time_in_midnight(start_date)) {
                        good_habits_layout_circle_feb29.setVisibility(View.VISIBLE);
                    } else {
                        good_habits_layout_circle_feb29.setVisibility(View.INVISIBLE);
                    }
                }
            } else {
                good_habits_layout_circle_feb29.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void put_values_into_year_in_good_habits() {
        if (getView() != null && getContext() != null) {
            TextView text_saying_which_year_to_show_in_a_good_habits_year = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year);
            int year_from_text = Integer.parseInt(text_saying_which_year_to_show_in_a_good_habits_year.getText().toString());
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year_from_text);
            String yes_color = "#06a94d";
            String yes_color_no_input = "#6a5acd";
            String no_color = "#FF2400";
            String no_color_no_input = "#000000";
            Drawable back_ground_for_a_year_in_habits_yes = ContextCompat.getDrawable(getContext(), R.drawable.back_ground_for_a_year_in_habits_yes);
            Drawable back_ground_for_a_year_in_habits_no = ContextCompat.getDrawable(getContext(), R.drawable.back_ground_for_a_year_in_habits_no);
            Drawable back_ground_for_year_in_habit = ContextCompat.getDrawable(getContext(), R.drawable.back_ground_for_year_in_habit);
            Drawable back_ground_for_a_year_in_habits_not_required = ContextCompat.getDrawable(getContext(), R.drawable.back_ground_for_a_year_in_habits_not_required);
            Am_i_paid am_i_paid = new Am_i_paid(getContext());
            if (am_i_paid.did_user_pay()) {
                if (is_this_a_leap_year(year_from_text)) {
                    if (past_current_future_for_the_full_year().equals("past")) {
                        for (int i = 366; i >= 1; i--) {
                            calendar.set(Calendar.DAY_OF_YEAR, i);
                            if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) < Simplify_the_time.return_time_in_midnight(start_date)) {
                                break;
                            }
                            list_of_all_the_calender_views[i - 1].setVisibility(View.VISIBLE);
                            if (return_state_of_day(calendar.getTimeInMillis()).contains("yes")) {
                                list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_yes);
                            } else if (return_state_of_day(calendar.getTimeInMillis()).contains("no")) {
                                list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_no);
                            } else if (return_state_of_day(calendar.getTimeInMillis()).contains("reject required")) {
                                list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_not_required);
                            } else {
                                list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_year_in_habit);
                            }
                        }
                    } else if (past_current_future_for_the_full_year().equals("current")) {
                        Calendar calendar_get_the_today = Calendar.getInstance();
                        for (int i = calendar_get_the_today.get(Calendar.DAY_OF_YEAR); i >= 1; i--) {
                            calendar.set(Calendar.DAY_OF_YEAR, i);
                            if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) < Simplify_the_time.return_time_in_midnight(start_date)) {
                                break;
                            }
                            list_of_all_the_calender_views[i - 1].setVisibility(View.VISIBLE);
                            if (return_state_of_day(calendar.getTimeInMillis()).contains("yes")) {
                                list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_yes);
                            } else if (return_state_of_day(calendar.getTimeInMillis()).contains("no")) {
                                list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_no);
                            } else if (return_state_of_day(calendar.getTimeInMillis()).contains("reject required")) {
                                list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_not_required);
                            } else {
                                list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_year_in_habit);
                            }
                        }
                    }
                } else {
                    if (past_current_future_for_the_full_year().equals("past")) {
                        for (int i = 365; i >= 1; i--) {
                            calendar.set(Calendar.DAY_OF_YEAR, i);
                            if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) < Simplify_the_time.return_time_in_midnight(start_date)) {
                                break;
                            }
                            if (i > 59) {
                                list_of_all_the_calender_views[i].setVisibility(View.VISIBLE);
                                if (return_state_of_day(calendar.getTimeInMillis()).contains("yes")) {
                                    list_of_all_the_calender_views[i].setBackground(back_ground_for_a_year_in_habits_yes);
                                } else if (return_state_of_day(calendar.getTimeInMillis()).contains("no")) {
                                    list_of_all_the_calender_views[i].setBackground(back_ground_for_a_year_in_habits_no);
                                } else if (return_state_of_day(calendar.getTimeInMillis()).contains("reject required")) {
                                    list_of_all_the_calender_views[i].setBackground(back_ground_for_a_year_in_habits_not_required);
                                } else {
                                    list_of_all_the_calender_views[i].setBackground(back_ground_for_year_in_habit);
                                }
                            } else {
                                list_of_all_the_calender_views[i - 1].setVisibility(View.VISIBLE);
                                if (return_state_of_day(calendar.getTimeInMillis()).contains("yes")) {
                                    list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_yes);
                                } else if (return_state_of_day(calendar.getTimeInMillis()).contains("no")) {
                                    list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_no);
                                } else if (return_state_of_day(calendar.getTimeInMillis()).contains("reject required")) {
                                    list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_not_required);
                                } else {
                                    list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_year_in_habit);
                                }
                            }
                        }
                    } else if (past_current_future_for_the_full_year().equals("current")) {
                        Calendar calendar_get_the_today = Calendar.getInstance();
                        for (int i = calendar_get_the_today.get(Calendar.DAY_OF_YEAR); i >= 1; i--) {
                            calendar.set(Calendar.DAY_OF_YEAR, i);
                            if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) < Simplify_the_time.return_time_in_midnight(start_date)) {
                                break;
                            }
                            if (i > 59) {
                                list_of_all_the_calender_views[i].setVisibility(View.VISIBLE);
                                if (return_state_of_day(calendar.getTimeInMillis()).contains("yes")) {
                                    list_of_all_the_calender_views[i].setBackground(back_ground_for_a_year_in_habits_yes);
                                } else if (return_state_of_day(calendar.getTimeInMillis()).contains("no")) {
                                    list_of_all_the_calender_views[i].setBackground(back_ground_for_a_year_in_habits_no);
                                } else if (return_state_of_day(calendar.getTimeInMillis()).contains("reject required")) {
                                    list_of_all_the_calender_views[i].setBackground(back_ground_for_a_year_in_habits_not_required);
                                } else {
                                    list_of_all_the_calender_views[i].setBackground(back_ground_for_year_in_habit);
                                }
                            } else {
                                list_of_all_the_calender_views[i - 1].setVisibility(View.VISIBLE);
                                if (return_state_of_day(calendar.getTimeInMillis()).contains("yes")) {
                                    list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_yes);
                                } else if (return_state_of_day(calendar.getTimeInMillis()).contains("no")) {
                                    list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_no);
                                } else if (return_state_of_day(calendar.getTimeInMillis()).contains("reject required")) {
                                    list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_not_required);
                                } else {
                                    list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_year_in_habit);
                                }
                            }
                        }
                    }
                }
            } else {
                Random random = new Random();
                if (is_this_a_leap_year(year_from_text)) {
                    for (int i = 366; i >= 1; i--) {
                        int number = random.nextInt(4);
                        calendar.set(Calendar.DAY_OF_YEAR, i);
                        list_of_all_the_calender_views[i - 1].setVisibility(View.VISIBLE);
                        if (number == 0) {
                            list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_yes);
                        } else if (number == 1) {
                            list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_no);
                        } else if (number == 2) {
                            list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_not_required);
                        } else {
                            list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_year_in_habit);
                        }
                    }
                } else {
                    for (int i = 365; i >= 1; i--) {
                        int number = random.nextInt(4);
                        calendar.set(Calendar.DAY_OF_YEAR, i);
                        if (i > 59) {
                            list_of_all_the_calender_views[i].setVisibility(View.VISIBLE);
                            if (number == 0) {
                                list_of_all_the_calender_views[i].setBackground(back_ground_for_a_year_in_habits_yes);
                            } else if (number == 1) {
                                list_of_all_the_calender_views[i].setBackground(back_ground_for_a_year_in_habits_no);
                            } else if (number == 2) {
                                list_of_all_the_calender_views[i].setBackground(back_ground_for_a_year_in_habits_not_required);
                            } else {
                                list_of_all_the_calender_views[i].setBackground(back_ground_for_year_in_habit);
                            }
                        } else {
                            list_of_all_the_calender_views[i - 1].setVisibility(View.VISIBLE);
                            if (number == 0) {
                                list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_yes);
                            } else if (number == 1) {
                                list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_no);
                            } else if (number == 2) {
                                list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_a_year_in_habits_not_required);
                            } else {
                                list_of_all_the_calender_views[i - 1].setBackground(back_ground_for_year_in_habit);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean is_this_a_leap_year(int year_from_function) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year_from_function);
        if (calendar.getActualMaximum(Calendar.DAY_OF_YEAR) > 365) {
            return true;
        } else {
            return false;
        }
    }

    private void restart_all_the_year_values() {
        if (getContext() != null) {
            for (int i = 0; i < 366; i++) {
                list_of_all_the_calender_views[i].setVisibility(View.INVISIBLE);
            }
        }
    }

    private String past_current_future_for_the_full_year() {
        if (getView() != null) {
            TextView text_saying_which_year_to_show_in_a_good_habits_year = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year);
            int year_from_text = Integer.parseInt(text_saying_which_year_to_show_in_a_good_habits_year.getText().toString());
            Calendar calendar = Calendar.getInstance();
            int real_year = calendar.get(Calendar.YEAR);
            if (real_year < year_from_text) {
                return "future";
            } else if (real_year == year_from_text) {
                return "current";
            } else {
                return "past";
            }
        } else {
            return "";
        }
    }

    private void add_all_the_values_into_history_array_list() {
        // Long[] array_with_time_history = new Long[history_split_faster.length];
        time_history_with_all_values = new TreeSet<>();
        array_with_all_values = new ArrayList<>();
        for (int i = 0; i < history_split_faster.length; i++) {
            if (history_split_faster[i].contains("yes")) {
                time_history_with_all_values.add(Long.parseLong(history_split_faster[i].replace("yes", "").replace("small_split", "")));
            }
            if (!history_split_faster[i].equals("")) {
                array_with_all_values.add(Long.parseLong(history_split_faster[i].replace("yes", "").replace("no", "").replace("small_split", "")));
            }
        }
        //time_history_with_all_values = new TreeSet<>(Arrays.asList(array_with_time_history));
    }

    private void set_the_buttons() {
        if (getView() != null) {
            Button button_shadow_for_the_back_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.button_shadow_for_the_back_for_good_habits_for_the_full_year_view);
            Button button_shadow_for_the_front_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.button_shadow_for_the_front_for_good_habits_for_the_full_year_view);
            View forward_button_over_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.forward_button_over_for_good_habits_for_the_full_year_view);
            View backward_button_over_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.backward_button_over_for_good_habits_for_the_full_year_view);
            TextView text_saying_which_year_to_show_in_a_good_habits_year = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year);
            int year_from_text = Integer.parseInt(text_saying_which_year_to_show_in_a_good_habits_year.getText().toString());
            Calendar calendar = Calendar.getInstance();
            int real_year = calendar.get(Calendar.YEAR);
            calendar.setTimeInMillis(start_date);
            int start_year = calendar.get(Calendar.YEAR);
            if (real_year == year_from_text) {
                forward_button_over_for_good_habits_for_the_full_year_view.setVisibility(View.INVISIBLE);
                button_shadow_for_the_front_for_good_habits_for_the_full_year_view.setVisibility(View.INVISIBLE);
            } else {
                forward_button_over_for_good_habits_for_the_full_year_view.setVisibility(View.VISIBLE);
                button_shadow_for_the_front_for_good_habits_for_the_full_year_view.setVisibility(View.VISIBLE);
            }
            if (start_year == year_from_text) {
                backward_button_over_for_good_habits_for_the_full_year_view.setVisibility(View.INVISIBLE);
                button_shadow_for_the_back_for_good_habits_for_the_full_year_view.setVisibility(View.INVISIBLE);
            } else {
                backward_button_over_for_good_habits_for_the_full_year_view.setVisibility(View.VISIBLE);
                button_shadow_for_the_back_for_good_habits_for_the_full_year_view.setVisibility(View.VISIBLE);
            }
        }
    }

    private void mood_tracker_listen() {
        CardView card_to_ask_how_was_your_mood = getView().findViewById(R.id.card_to_ask_how_was_your_mood);
        card_to_ask_how_was_your_mood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mood_tracker new_fragment = new mood_tracker();
                habits_fragment old_fragment = (habits_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("habits");
                mood_tracker check_fragment = (mood_tracker) getActivity().getSupportFragmentManager().findFragmentByTag("mood tracker");
                if (check_fragment != null) {
                    getActivity().getSupportFragmentManager().beginTransaction().remove(check_fragment).commit();
                }
                if (old_fragment != null) {
                    getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, new_fragment, "mood tracker").show(new_fragment).commit();
                }
            }
        });
    }

    private int return_number_off_bad_habits() {
        SharedPreferences sh = getActivity().getSharedPreferences("good_habits", Context.MODE_PRIVATE);
        String s1 = sh.getString("good_history", "");
        if (s1.equals("")) {
            return 0;
        } else {
            return s1.split("max_divide").length;
        }
    }

    private void blur_the_views() {
        if (getView() != null) {
            Button this_pie_chart_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits = getView().findViewById(R.id.this_pie_chart_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits);
            Button this_line_chart_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits = getView().findViewById(R.id.this_line_chart_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits);
            Button this_four_values_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits = getView().findViewById(R.id.this_four_values_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits);
            Button this_is_only_for_premuim_in_good_habits_showing_yearly_good_habit = getView().findViewById(R.id.this_is_only_for_premuim_in_good_habits_showing_yearly_good_habit);

            TextView text_for_title_in_the_card_to_show_pie = getView().findViewById(R.id.text_for_title_in_the_card_to_show_pie);
            PieChart chart_to_show_pie_of_yes_or_no_pie = getView().findViewById(R.id.chart_to_show_pie_of_yes_or_no_pie);
            TextView text_view_saying_that_there_is_not_enough_data_to_draw_this_chart = getView().findViewById(R.id.text_view_saying_that_there_is_not_enough_data_to_draw_this_chart);
            Button button_to_share_pie_chart = getView().findViewById(R.id.button_to_share_pie_chart);

            TextView title_for_various_streak_length_chart_inside_card = getView().findViewById(R.id.title_for_various_streak_length_chart_inside_card);
            LineChart cahrt_showing_various_chart_length_for_different_streaks = getView().findViewById(R.id.cahrt_showing_various_chart_length_for_different_streaks);
            TextView text_saying_thet_there_is_not_enough_data_to_draw_various_streaks = getView().findViewById(R.id.text_saying_thet_there_is_not_enough_data_to_draw_various_streaks);
            Button button_to_share_line_chart_of_various_streaks = getView().findViewById(R.id.button_to_share_line_chart_of_various_streaks);

            ConstraintLayout constraint_layout_showing_the_four_values_to_blur_in_the_future = getView().findViewById(R.id.constraint_layout_showing_the_four_values_to_blur_in_the_future);

            TextView title_saying_the_habit_name_in_year_for_good_habits = getView().findViewById(R.id.title_saying_the_habit_name_in_year_for_good_habits);
            TextView text_saying_which_year_to_show_in_a_good_habits_year = getView().findViewById(R.id.text_saying_which_year_to_show_in_a_good_habits_year);
            Button button_shadow_for_the_back_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.button_shadow_for_the_back_for_good_habits_for_the_full_year_view);
            Button button_shadow_for_the_front_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.button_shadow_for_the_front_for_good_habits_for_the_full_year_view);
            View forward_button_over_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.forward_button_over_for_good_habits_for_the_full_year_view);
            View backward_button_over_for_good_habits_for_the_full_year_view = getView().findViewById(R.id.backward_button_over_for_good_habits_for_the_full_year_view);
            ConstraintLayout layout_to_show_data_about_the_year_calender = getView().findViewById(R.id.layout_to_show_data_about_the_year_calender);
            Button share_button_to_share_the_whole_year_in_the_good_habits = getView().findViewById(R.id.share_button_to_share_the_whole_year_in_the_good_habits);

            Am_i_paid am_i_paid = new Am_i_paid(getContext());
            if (am_i_paid.did_user_pay()) {
                this_pie_chart_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits.setVisibility(View.INVISIBLE);
                this_line_chart_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits.setVisibility(View.INVISIBLE);
                this_four_values_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits.setVisibility(View.INVISIBLE);
                this_is_only_for_premuim_in_good_habits_showing_yearly_good_habit.setVisibility(View.INVISIBLE);

                text_for_title_in_the_card_to_show_pie.setAlpha(1f);
                chart_to_show_pie_of_yes_or_no_pie.setAlpha(1f);
                text_view_saying_that_there_is_not_enough_data_to_draw_this_chart.setAlpha(1f);
                button_to_share_pie_chart.setAlpha(1f);

                title_for_various_streak_length_chart_inside_card.setAlpha(1f);
                cahrt_showing_various_chart_length_for_different_streaks.setAlpha(1f);
                text_saying_thet_there_is_not_enough_data_to_draw_various_streaks.setAlpha(1f);
                button_to_share_line_chart_of_various_streaks.setAlpha(1f);

                constraint_layout_showing_the_four_values_to_blur_in_the_future.setAlpha(1f);

                title_saying_the_habit_name_in_year_for_good_habits.setAlpha(1f);
                text_saying_which_year_to_show_in_a_good_habits_year.setAlpha(1f);
                button_shadow_for_the_back_for_good_habits_for_the_full_year_view.setAlpha(1f);
                button_shadow_for_the_front_for_good_habits_for_the_full_year_view.setAlpha(1f);
                forward_button_over_for_good_habits_for_the_full_year_view.setAlpha(1f);
                backward_button_over_for_good_habits_for_the_full_year_view.setAlpha(1f);
                layout_to_show_data_about_the_year_calender.setAlpha(1f);
                share_button_to_share_the_whole_year_in_the_good_habits.setAlpha(1f);
            } else {
                this_pie_chart_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits.setVisibility(View.VISIBLE);
                this_line_chart_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits.setVisibility(View.VISIBLE);
                this_four_values_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits.setVisibility(View.VISIBLE);
                this_is_only_for_premuim_in_good_habits_showing_yearly_good_habit.setVisibility(View.VISIBLE);
                this_pie_chart_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(return_the_color())));
                this_line_chart_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(return_the_color())));
                this_four_values_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(return_the_color())));
                this_is_only_for_premuim_in_good_habits_showing_yearly_good_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(return_the_color())));

                text_for_title_in_the_card_to_show_pie.setAlpha(0.2f);
                chart_to_show_pie_of_yes_or_no_pie.setAlpha(0.2f);
                text_view_saying_that_there_is_not_enough_data_to_draw_this_chart.setAlpha(0.2f);
                button_to_share_pie_chart.setAlpha(0.2f);

                title_for_various_streak_length_chart_inside_card.setAlpha(0.2f);
                cahrt_showing_various_chart_length_for_different_streaks.setAlpha(0.2f);
                text_saying_thet_there_is_not_enough_data_to_draw_various_streaks.setAlpha(0.2f);
                button_to_share_line_chart_of_various_streaks.setAlpha(0.2f);

                constraint_layout_showing_the_four_values_to_blur_in_the_future.setAlpha(0.2f);

                title_saying_the_habit_name_in_year_for_good_habits.setAlpha(0.2f);
                text_saying_which_year_to_show_in_a_good_habits_year.setAlpha(0.2f);
                button_shadow_for_the_back_for_good_habits_for_the_full_year_view.setAlpha(0.2f);
                button_shadow_for_the_front_for_good_habits_for_the_full_year_view.setAlpha(0.2f);
                forward_button_over_for_good_habits_for_the_full_year_view.setAlpha(0.2f);
                backward_button_over_for_good_habits_for_the_full_year_view.setAlpha(0.2f);
                layout_to_show_data_about_the_year_calender.setAlpha(0.2f);
                share_button_to_share_the_whole_year_in_the_good_habits.setAlpha(0.2f);
            }
        }
    }

    private String return_the_color() {
        String habits = read_the_habits();
        String[] splitter = habits.split("small_split");
        String color;
        if (splitter[3].equals("teal")) {
            color = "#607D8B";
        } else if (splitter[3].equals("red")) {
            color = "#e6194B";
        } else if (splitter[3].equals("green")) {
            color = "#3cb44b";
        } else if (splitter[3].equals("blue")) {
            color = "#4363d8";
        } else if (splitter[3].equals("orange")) {
            color = "#f58231";
        } else if (splitter[3].equals("brown")) {
            color = "#9A6324";
        } else if (splitter[3].equals("black")) {
            color = "#000000";
        } else if (splitter[3].equals("cyan")) {
            color = "#42d4f4";
        } else if (splitter[3].equals("lime")) {
            color = "#bfef45";
        } else if (splitter[3].equals("magenta")) {
            color = "#f032e6";
        } else if (splitter[3].equals("navy")) {
            color = "#000075";
        } else if (splitter[3].equals("pink")) {
            color = "#fabed4";
        } else if (splitter[3].equals("yellow")) {
            color = "#ffe119";
        } else if (splitter[3].equals("purple")) {
            color = "#911eb4";
        } else {
            color = "#607D8B";
        }
        return color;
    }

    private void buy_premuim_button_listen() {
        Button this_pie_chart_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits = getView().findViewById(R.id.this_pie_chart_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits);
        Button this_line_chart_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits = getView().findViewById(R.id.this_line_chart_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits);
        Button this_four_values_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits = getView().findViewById(R.id.this_four_values_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits);
        Button this_is_only_for_premuim_in_good_habits_showing_yearly_good_habit = getView().findViewById(R.id.this_is_only_for_premuim_in_good_habits_showing_yearly_good_habit);
        this_pie_chart_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy_premuim_was_clicked("pie");
            }
        });
        this_line_chart_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy_premuim_was_clicked("streak");
            }
        });
        this_four_values_is_only_available_for_pro_users_pie_chart_view_home_in_good_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy_premuim_was_clicked("Successful days");
            }
        });
        this_is_only_for_premuim_in_good_habits_showing_yearly_good_habit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy_premuim_was_clicked("Yearly");
            }
        });
    }

    private void buy_premuim_was_clicked(String which_chart) {
        Buy_premuim buy_premuim = new Buy_premuim("to view the ".concat(which_chart).concat(" chart."), true, "good habits");
        habits_fragment old_fragment = (habits_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("habits");
        if (old_fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
        }
    }

    private void set_notifiction_new(String habit_name, int id) {
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        if (!get_the_reminder_text(id).isEmpty()) {
            String[] big_split = get_the_reminder_text(id).split("big_cut_for_reminder");
            for (int i = 0; i < big_split.length; i++) {
                String[] small_split = big_split[i].split("tiny_cut_for_reminder");
                Intent intent = new Intent(getActivity(), Send_notifcation_at_set_time.class);
                intent.putExtra("days", small_split[1]);
                intent.putExtra("channel_name", habit_name);
                intent.putExtra("channel_id", "100");
                intent.putExtra("id", id);
                PendingIntent pendingintent = PendingIntent.getBroadcast(getActivity(), id, intent, 0);
                long time_in_milli = return_calender_for_alarm(small_split[0]).getTimeInMillis();
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time_in_milli, AlarmManager.INTERVAL_DAY, pendingintent);
            }
        }
    }

    private String get_the_reminder_text(int which) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("good_habits", Context.MODE_PRIVATE);
        String old = sharedPreferences.getString("good_history", "");
        String[] big_split = old.split("max_divide");
        String[] small_split = big_split[which].split("small_split");
        return small_split[6];
    }

    private Calendar return_calender_for_alarm(String time) {
        Calendar calendar = Calendar.getInstance();
        if (time.contains("AM")) {
            String time_without_am = time.replace(" AM", "");
            String[] small_split = time_without_am.split(":");
            int hour = Integer.parseInt(small_split[0]);
            int minute = Integer.parseInt(small_split[1]);
            calendar.set(Calendar.HOUR, hour);
            calendar.set(Calendar.AM_PM, Calendar.AM);
            calendar.set(Calendar.MINUTE, minute);
        } else {
            String time_without_am = time.replace(" PM", "");
            String[] small_split = time_without_am.split(":");
            int hour = Integer.parseInt(small_split[0]);
            int minute = Integer.parseInt(small_split[1]);
            calendar.set(Calendar.HOUR, hour);
            calendar.set(Calendar.AM_PM, Calendar.PM);
            calendar.set(Calendar.MINUTE, minute);
        }
        return calendar;
    }

    private void cancel_alarm(int id){
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(getActivity(), Send_notifcation_at_set_time.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), id, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
    }*/
}