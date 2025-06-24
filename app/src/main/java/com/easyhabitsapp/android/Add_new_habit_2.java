package com.easyhabitsapp.android;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.nex3z.togglebuttongroup.MultiSelectToggleGroup;
import com.nex3z.togglebuttongroup.button.CircularToggle;
import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;
import com.skydoves.colorpickerview.preference.ColorPickerPreferenceManager;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;
import com.vanniktech.emoji.EmojiEditText;
import com.vanniktech.emoji.EmojiManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.EmojiPopup;
import com.vanniktech.emoji.EmojiTextView;
import com.vanniktech.emoji.EmojiTheming;
import com.vanniktech.emoji.google.GoogleEmojiProvider;
import com.vanniktech.emoji.recent.RecentEmoji;
import com.vanniktech.emoji.traits.DisableKeyboardInputTrait;
import com.vanniktech.emoji.traits.ForceSingleEmojiTrait;

public class Add_new_habit_2 extends Fragment {

    private String habit_color;
    private String last_color;
    private EmojiPopup emojiPopup = null;
    private ArrayList<Example_item_reminder_add_new_habit> reminder_arraylist = new ArrayList<>();
    private Adapter_add_reminder_add_new_habit adapter_for_reminder;


    public Add_new_habit_2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        set_up_emoji_keyboard();
        return inflater.inflate(R.layout.fragment_add_new_habit_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ConstraintLayout layout_to_choose_custom_or_premade_habit = getView().findViewById(R.id.layout_to_choose_custom_or_premade_habit);
        ConstraintLayout constrain_layout_contatining_everything_for_card_add_new_habit = getView().findViewById(R.id.constrain_layout_contatining_everything_for_card_add_new_habit);
        ConstraintLayout layout_to_enter_habit_name = getView().findViewById(R.id.layout_to_enter_habit_name);
        ConstraintLayout layout_to_choose_color_add_new_habit = getView().findViewById(R.id.layout_to_choose_color_add_new_habit);
        ConstraintLayout layout_to_choose_how_often_wants_to_do_habit = getView().findViewById(R.id.layout_to_choose_how_often_wants_to_do_habit);
        ConstraintLayout layout_to_choose_how_many_times_per_day = getView().findViewById(R.id.layout_to_choose_how_many_times_per_day);
        ConstraintLayout layout_to_choose_reminder_add_new_habit = getView().findViewById(R.id.layout_to_choose_reminder_add_new_habit);
        layout_to_choose_custom_or_premade_habit.setVisibility(View.VISIBLE);
        constrain_layout_contatining_everything_for_card_add_new_habit.setVisibility(View.GONE);
        layout_to_enter_habit_name.setVisibility(View.GONE);
        layout_to_choose_color_add_new_habit.setVisibility(View.GONE);
        layout_to_choose_how_often_wants_to_do_habit.setVisibility(View.GONE);
        layout_to_choose_how_many_times_per_day.setVisibility(View.GONE);
        layout_to_choose_reminder_add_new_habit.setVisibility(View.GONE);
        cancel_button_listen();
        back_button_listen();
        forward_button_listen();
        continue_button_listen();
        set_habit_color();
        listen_to_on_click_on_the_colors();
        lock_the_custom_color();
        create_custom_habit_listen();
        pick_icon_button_listen();
        pick_icon_circle_button_listen();
        listen_to_parent_focus();
        configure_emoji_edit_text();
        emoji_text_watcher();
        click_outside_emoji_listen();
        add_or_quit_button_listen();
        set_up_frequecny();
        listen_for_frequncy_spinner_item_select();
        set_first_day_of_week_specific_days_per_week();
        reset_the_selected_days();// make sure that the function marks them all as checked
        click_outside_number_of_times_per_day_edit_text_listen();
        listen_to_notification_switch();
        add_a_reminder_button_listen();
        dismiss_the_spinner_view();
        set_up_recycle_view();
        set_up_recycle_view_habits();
        /*done_button_listen();
        color_button_add_new_habit_final();
        change_icon_button_listen_add_new_habit_final();
        build_up_and_quit_button_listen_in_the_end();*/
    }

    private void cancel_button_listen() {
        if (getView() != null) {
            Button button_to_cancel_new_habit_in_add_new_habit = getView().findViewById(R.id.button_to_cancel_new_habit_in_add_new_habit);
            button_to_cancel_new_habit_in_add_new_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getActivity() != null) {
                        home_fragment new_fragment = (com.easyhabitsapp.android.home_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("home");
                        Add_new_habit_2 old_fragment = (Add_new_habit_2) getActivity().getSupportFragmentManager().findFragmentByTag("add a habit");
                        if (old_fragment != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).show(new_fragment).commit();
                        }
                    }
                }
            });
        }
    }

    /*private void get_height_of_keyboard() {
        if (getView() != null && getActivity() != null) {
            Window mRootWindow = getActivity().getWindow();
            View mRootView = mRootWindow.getDecorView().findViewById(android.R.id.content);
            EditText edit_text_to_enter_habit_name_ = getView().findViewById(R.id.edit_text_to_enter_habit_name_);
            View top_of_keyboard_and_bottom_of_edittext = getView().findViewById(R.id.top_of_keyboard_and_bottom_of_edittext);
            TextView text_satying_whats_the_name_of_the_habit_add_new_habit = getView().findViewById(R.id.text_satying_whats_the_name_of_the_habit_add_new_habit);
            mRootView.getViewTreeObserver().addOnGlobalLayoutListener(
                    new ViewTreeObserver.OnGlobalLayoutListener() {
                        public void onGlobalLayout() {
                            if(getView()!=null) {
                                Rect r = new Rect();
                                View view = mRootWindow.getDecorView();
                                view.getWindowVisibleDisplayFrame(r);
                                keyboard_height = mRootView.getHeight() - r.bottom;
                                // r.left, r.top, r.right, r.bottom
                                if (edit_text_to_enter_habit_name_.hasFocus()) {
                                    ConstraintLayout layout = (ConstraintLayout) getView().findViewById(R.id.layout_to_enter_habit_name);
                                    ConstraintSet set = new ConstraintSet();
                                    set.clone(layout);
                                    set.connect(edit_text_to_enter_habit_name_.getId(),ConstraintSet.TOP,text_satying_whats_the_name_of_the_habit_add_new_habit.getId(),ConstraintSet.BOTTOM,0);
                                    set.connect(top_of_keyboard_and_bottom_of_edittext.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, (int) (keyboard_height - convertDpToPixel(75, getContext())));
                                    set.applyTo(layout);
                                }
                            }
                        }
                    });
        }
    }

    private void listen_to_enter_habit_name_focus() {
        if (getView() != null) {
            EditText edit_text_to_enter_habit_name_ = getView().findViewById(R.id.edit_text_to_enter_habit_name_);
            TextView text_satying_whats_the_name_of_the_habit_add_new_habit = getView().findViewById(R.id.text_satying_whats_the_name_of_the_habit_add_new_habit);
            View top_of_keyboard_and_bottom_of_edittext = getView().findViewById(R.id.top_of_keyboard_and_bottom_of_edittext);
            edit_text_to_enter_habit_name_.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (getView() != null && getContext() != null) {
                        if (hasFocus) {

                        } else {
                            ConstraintLayout layout = (ConstraintLayout) getView().findViewById(R.id.layout_to_enter_habit_name);
                            ConstraintSet set = new ConstraintSet();
                            set.clone(layout);
                            set.connect(edit_text_to_enter_habit_name_.getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP,0);
                            set.connect(top_of_keyboard_and_bottom_of_edittext.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, (int) (keyboard_height - convertDpToPixel(75, getContext())));
                            set.applyTo(layout);
                        }
                    }
                }
            });
        }
    }*/


    /*private static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }*/

    private void back_button_listen() {
        if (getView() != null) {
            Button back_button_in_add_new_habit = getView().findViewById(R.id.back_button_in_add_new_habit);
            back_button_in_add_new_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    back_button_pressed();
                }
            });
        }
    }

    private void forward_button_listen() {
        if (getView() != null) {
            Button front_button_in_add_new_habit = getView().findViewById(R.id.front_button_in_add_new_habit);
            front_button_in_add_new_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    forward_button_pressed();
                }
            });
        }
    }

    private void continue_button_listen() {
        if (getView() != null) {
            Button continue_button_at_the_buttom = getView().findViewById(R.id.continue_button_at_the_buttom);
            continue_button_at_the_buttom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    forward_button_pressed();
                }
            });
        }
    }


    private void back_button_pressed() {
        if (getView() != null) {
            dismiss_keyboard();
            int which_screen_am_i_on = which_screen_am_i_on();
            Button white_button_build_habit_add_new_habit = getView().findViewById(R.id.white_button_build_habit_add_new_habit);
            if (which_screen_am_i_on == 1) {
                change_screen(0);
            } else if (which_screen_am_i_on == 2) {
                change_screen(1);
            } else if (which_screen_am_i_on == 3) {
                change_screen(2);
            } else if (which_screen_am_i_on == 4) {
                change_screen(3);
            } else if (which_screen_am_i_on == 5) {
                make_the_spinner_go_away();
                change_screen(4);
            } else if (which_screen_am_i_on == 6) {
                change_screen(5);
            } else if (which_screen_am_i_on == 7) {
                if (white_button_build_habit_add_new_habit.getVisibility() == View.VISIBLE) {
                    change_screen(6);
                } else {
                    change_screen(4);
                }
            }
        }
    }

    private void forward_button_pressed() {
        if (getView() != null && getActivity() != null) {
            EditText edit_text_to_enter_habit_name_ = getView().findViewById(R.id.edit_text_to_enter_habit_name_);
            EditText edit_text_to_enter_how_many_times_per_day = getView().findViewById(R.id.edit_text_to_enter_how_many_times_per_day);
            Button white_button_build_habit_add_new_habit = getView().findViewById(R.id.white_button_build_habit_add_new_habit);
            SwitchMaterial switch_to_turn_on_notifications_add_new_habit = getView().findViewById(R.id.switch_to_turn_on_notifications_add_new_habit);
            dismiss_keyboard();
            int which_screen_am_i_on = which_screen_am_i_on();
            if (which_screen_am_i_on == 0) {
                change_screen(1);
                color_every_thing();
            } else if (which_screen_am_i_on == 1) {
                if (!edit_text_to_enter_habit_name_.getText().toString().trim().isEmpty()) {
                    change_screen(2);
                } else {
                    Toast.makeText(getContext(), "Habit name can't be empty", Toast.LENGTH_SHORT).show();
                }
                select_the_selected_color();
            } else if (which_screen_am_i_on == 2) {
                change_screen(3);
            } else if (which_screen_am_i_on == 3) {
                change_screen(4);
            } else if (which_screen_am_i_on == 4) {
                if (white_button_build_habit_add_new_habit.getVisibility() == View.VISIBLE) {
                    change_screen(5);
                } else {
                    change_screen(7);
                }
            } else if (which_screen_am_i_on == 5) {
                make_the_spinner_go_away();
                change_screen(6);
            } else if (which_screen_am_i_on == 6) {
                if (edit_text_to_enter_how_many_times_per_day.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Number of times can't be empty", Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(edit_text_to_enter_how_many_times_per_day.getText().toString()) == 0) {
                    Toast.makeText(getContext(), "Number of times must be at least 1", Toast.LENGTH_SHORT).show();
                } else {
                    change_screen(7);
                }
            } else if (which_screen_am_i_on == 7) {
                if (switch_to_turn_on_notifications_add_new_habit.isChecked() && reminder_arraylist.isEmpty()) {
                    Toast.makeText(getContext(), "Please add at least one reminder or turn off notifications", Toast.LENGTH_SHORT).show();
                } else {
                    Add_new_habit_final add_new_habit_final;
                    if (return_type().equals("Build_up")) {
                        if (are_notifications_on()) {
                            add_new_habit_final = new Add_new_habit_final(return_habit_name(), return_habit_color(), return_icon(), return_type(), return_habit_frequencuy(), return_frequency_extra(), are_notifications_on(), return_notifications());
                        } else {
                            add_new_habit_final = new Add_new_habit_final(return_habit_name(), return_habit_color(), return_icon(), return_type(), return_habit_frequencuy(), return_frequency_extra(), are_notifications_on());
                        }
                    } else {
                        if (are_notifications_on()) {
                            add_new_habit_final = new Add_new_habit_final(return_habit_name(), return_habit_color(), return_icon(), return_type(), are_notifications_on(), return_notifications());
                        } else {
                            add_new_habit_final = new Add_new_habit_final(return_habit_name(), return_habit_color(), return_icon(), return_type(), are_notifications_on());

                        }
                    }
                    Add_new_habit_2 old_fragment = (Add_new_habit_2) getActivity().getSupportFragmentManager().findFragmentByTag("add a habit");
                    getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).add(R.id.fragment_container, add_new_habit_final, "add a habit final").show(add_new_habit_final).commit();
                }
            }
        }
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

    private void change_screen(int which_screen) {
        if (getView() != null) {
            ConstraintLayout layout_to_choose_custom_or_premade_habit = getView().findViewById(R.id.layout_to_choose_custom_or_premade_habit);
            ConstraintLayout constrain_layout_contatining_everything_for_card_add_new_habit = getView().findViewById(R.id.constrain_layout_contatining_everything_for_card_add_new_habit);
            ConstraintLayout layout_to_enter_habit_name = getView().findViewById(R.id.layout_to_enter_habit_name);
            ConstraintLayout layout_to_choose_color_add_new_habit = getView().findViewById(R.id.layout_to_choose_color_add_new_habit);
            ConstraintLayout layout_to_pick_icon_add_new_habit = getView().findViewById(R.id.layout_to_pick_icon_add_new_habit);
            ConstraintLayout layout_to_choose_good_or_bad_add_new_habit = getView().findViewById(R.id.layout_to_choose_good_or_bad_add_new_habit);
            ConstraintLayout layout_to_choose_how_often_wants_to_do_habit = getView().findViewById(R.id.layout_to_choose_how_often_wants_to_do_habit);
            ConstraintLayout layout_to_choose_how_many_times_per_day = getView().findViewById(R.id.layout_to_choose_how_many_times_per_day);
            ConstraintLayout layout_to_choose_reminder_add_new_habit = getView().findViewById(R.id.layout_to_choose_reminder_add_new_habit);
            if (which_screen == 0) {
                dismiss_keyboard();
                layout_to_choose_custom_or_premade_habit.setVisibility(View.VISIBLE);
                constrain_layout_contatining_everything_for_card_add_new_habit.setVisibility(View.GONE);
                layout_to_enter_habit_name.setVisibility(View.GONE);
                layout_to_choose_color_add_new_habit.setVisibility(View.GONE);
                layout_to_pick_icon_add_new_habit.setVisibility(View.GONE);
                layout_to_choose_good_or_bad_add_new_habit.setVisibility(View.GONE);
                layout_to_choose_how_many_times_per_day.setVisibility(View.GONE);
                layout_to_choose_reminder_add_new_habit.setVisibility(View.GONE);
                reset_everything();
            } else if (which_screen == 1) {
                dismiss_keyboard();
                layout_to_choose_custom_or_premade_habit.setVisibility(View.GONE);
                constrain_layout_contatining_everything_for_card_add_new_habit.setVisibility(View.VISIBLE);
                layout_to_enter_habit_name.setVisibility(View.VISIBLE);
                layout_to_choose_color_add_new_habit.setVisibility(View.GONE);
                layout_to_pick_icon_add_new_habit.setVisibility(View.GONE);
                layout_to_choose_good_or_bad_add_new_habit.setVisibility(View.GONE);
                layout_to_choose_how_often_wants_to_do_habit.setVisibility(View.GONE);
                layout_to_choose_how_many_times_per_day.setVisibility(View.GONE);
                layout_to_choose_reminder_add_new_habit.setVisibility(View.GONE);
            } else if (which_screen == 2) {
                dismiss_keyboard();
                layout_to_choose_custom_or_premade_habit.setVisibility(View.GONE);
                constrain_layout_contatining_everything_for_card_add_new_habit.setVisibility(View.VISIBLE);
                layout_to_enter_habit_name.setVisibility(View.GONE);
                layout_to_choose_color_add_new_habit.setVisibility(View.VISIBLE);
                layout_to_pick_icon_add_new_habit.setVisibility(View.GONE);
                layout_to_choose_good_or_bad_add_new_habit.setVisibility(View.GONE);
                layout_to_choose_how_often_wants_to_do_habit.setVisibility(View.GONE);
                layout_to_choose_how_many_times_per_day.setVisibility(View.GONE);
                layout_to_choose_reminder_add_new_habit.setVisibility(View.GONE);
            } else if (which_screen == 3) {
                dismiss_keyboard();
                layout_to_choose_custom_or_premade_habit.setVisibility(View.GONE);
                constrain_layout_contatining_everything_for_card_add_new_habit.setVisibility(View.VISIBLE);
                layout_to_enter_habit_name.setVisibility(View.GONE);
                layout_to_choose_color_add_new_habit.setVisibility(View.GONE);
                layout_to_pick_icon_add_new_habit.setVisibility(View.VISIBLE);
                layout_to_choose_good_or_bad_add_new_habit.setVisibility(View.GONE);
                layout_to_choose_how_often_wants_to_do_habit.setVisibility(View.GONE);
                layout_to_choose_how_many_times_per_day.setVisibility(View.GONE);
                layout_to_choose_reminder_add_new_habit.setVisibility(View.GONE);
            } else if (which_screen == 4) {
                dismiss_keyboard();
                layout_to_choose_custom_or_premade_habit.setVisibility(View.GONE);
                constrain_layout_contatining_everything_for_card_add_new_habit.setVisibility(View.VISIBLE);
                layout_to_enter_habit_name.setVisibility(View.GONE);
                layout_to_choose_color_add_new_habit.setVisibility(View.GONE);
                layout_to_pick_icon_add_new_habit.setVisibility(View.GONE);
                layout_to_choose_good_or_bad_add_new_habit.setVisibility(View.VISIBLE);
                layout_to_choose_how_often_wants_to_do_habit.setVisibility(View.GONE);
                layout_to_choose_how_many_times_per_day.setVisibility(View.GONE);
                layout_to_choose_reminder_add_new_habit.setVisibility(View.GONE);
            } else if (which_screen == 5) {
                dismiss_keyboard();
                layout_to_choose_custom_or_premade_habit.setVisibility(View.GONE);
                constrain_layout_contatining_everything_for_card_add_new_habit.setVisibility(View.VISIBLE);
                layout_to_enter_habit_name.setVisibility(View.GONE);
                layout_to_choose_color_add_new_habit.setVisibility(View.GONE);
                layout_to_pick_icon_add_new_habit.setVisibility(View.GONE);
                layout_to_choose_good_or_bad_add_new_habit.setVisibility(View.GONE);
                layout_to_choose_how_often_wants_to_do_habit.setVisibility(View.VISIBLE);
                layout_to_choose_how_many_times_per_day.setVisibility(View.GONE);
                layout_to_choose_reminder_add_new_habit.setVisibility(View.GONE);
            } else if (which_screen == 6) {
                dismiss_keyboard();
                layout_to_choose_custom_or_premade_habit.setVisibility(View.GONE);
                constrain_layout_contatining_everything_for_card_add_new_habit.setVisibility(View.VISIBLE);
                layout_to_enter_habit_name.setVisibility(View.GONE);
                layout_to_choose_color_add_new_habit.setVisibility(View.GONE);
                layout_to_pick_icon_add_new_habit.setVisibility(View.GONE);
                layout_to_choose_good_or_bad_add_new_habit.setVisibility(View.GONE);
                layout_to_choose_how_often_wants_to_do_habit.setVisibility(View.GONE);
                layout_to_choose_how_many_times_per_day.setVisibility(View.VISIBLE);
                layout_to_choose_reminder_add_new_habit.setVisibility(View.GONE);
            } else if (which_screen == 7) {
                dismiss_keyboard();
                layout_to_choose_custom_or_premade_habit.setVisibility(View.GONE);
                constrain_layout_contatining_everything_for_card_add_new_habit.setVisibility(View.VISIBLE);
                layout_to_enter_habit_name.setVisibility(View.GONE);
                layout_to_choose_color_add_new_habit.setVisibility(View.GONE);
                layout_to_pick_icon_add_new_habit.setVisibility(View.GONE);
                layout_to_choose_good_or_bad_add_new_habit.setVisibility(View.GONE);
                layout_to_choose_how_often_wants_to_do_habit.setVisibility(View.GONE);
                layout_to_choose_how_many_times_per_day.setVisibility(View.GONE);
                layout_to_choose_reminder_add_new_habit.setVisibility(View.VISIBLE);
            }
            update_progress_bar(which_screen);
        }
    }

    private int which_screen_am_i_on() {
        if (getView() != null) {
            ConstraintLayout layout_to_choose_custom_or_premade_habit = getView().findViewById(R.id.layout_to_choose_custom_or_premade_habit);
            ConstraintLayout layout_to_enter_habit_name = getView().findViewById(R.id.layout_to_enter_habit_name);
            ConstraintLayout layout_to_choose_color_add_new_habit = getView().findViewById(R.id.layout_to_choose_color_add_new_habit);
            ConstraintLayout layout_to_pick_icon_add_new_habit = getView().findViewById(R.id.layout_to_pick_icon_add_new_habit);
            ConstraintLayout layout_to_choose_good_or_bad_add_new_habit = getView().findViewById(R.id.layout_to_choose_good_or_bad_add_new_habit);
            ConstraintLayout layout_to_choose_how_often_wants_to_do_habit = getView().findViewById(R.id.layout_to_choose_how_often_wants_to_do_habit);
            ConstraintLayout layout_to_choose_how_many_times_per_day = getView().findViewById(R.id.layout_to_choose_how_many_times_per_day);
            ConstraintLayout layout_to_choose_reminder_add_new_habit = getView().findViewById(R.id.layout_to_choose_reminder_add_new_habit);
            if (layout_to_choose_custom_or_premade_habit.getVisibility() == View.VISIBLE) {
                return 0;
            } else if (layout_to_enter_habit_name.getVisibility() == View.VISIBLE) {
                return 1;
            } else if (layout_to_choose_color_add_new_habit.getVisibility() == View.VISIBLE) {
                return 2;
            } else if (layout_to_pick_icon_add_new_habit.getVisibility() == View.VISIBLE) {
                return 3;
            } else if (layout_to_choose_good_or_bad_add_new_habit.getVisibility() == View.VISIBLE) {
                return 4;
            } else if (layout_to_choose_how_often_wants_to_do_habit.getVisibility() == View.VISIBLE) {
                return 5;
            } else if (layout_to_choose_how_many_times_per_day.getVisibility() == View.VISIBLE) {
                return 6;
            } else if (layout_to_choose_reminder_add_new_habit.getVisibility() == View.VISIBLE) {
                return 7;
            }
        }
        return 1;
    }

    private String get_a_random_color() {
        ArrayList<String> random_color_list = new ArrayList<>();
        random_color_list.add("#ed94c9");
        random_color_list.add("#e095ea");
        random_color_list.add("#ec8aad");
        random_color_list.add("#ea867c");
        random_color_list.add("#e7aa6d");
        random_color_list.add("#ddc652");
        random_color_list.add("#b3cd5f");
        random_color_list.add("#7fc5a0");
        random_color_list.add("#6ecabd");
        random_color_list.add("#77cebb");
        random_color_list.add("#6bb6db");
        random_color_list.add("#7ea8e5");
        random_color_list.add("#888ce3");
        String random_color = random_color_list.get(new Random().nextInt(random_color_list.size()));
        while (random_color.equals(last_color)) {
            random_color = random_color_list.get(new Random().nextInt(random_color_list.size()));
        }
        last_color = random_color;
        return random_color;
    }

    private void set_habit_color() {
        habit_color = get_a_random_color();
    }

    private void set_continue_color_button() {
        if (getView() != null) {
            Button continue_button_at_the_buttom = getView().findViewById(R.id.continue_button_at_the_buttom);
            continue_button_at_the_buttom.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(habit_color)));
        }
    }

    private void set_the_color_for_the_bar_at_the_top() {
        if (getView() != null) {
            ProgressBar progress_bar_showing_which_page_I_am_in = getView().findViewById(R.id.progress_bar_showing_which_page_I_am_in);
            LayerDrawable progressBarDrawable = (LayerDrawable) progress_bar_showing_which_page_I_am_in.getProgressDrawable();
            Drawable backgroundDrawable = progressBarDrawable.getDrawable(0);
            Drawable progressDrawable = progressBarDrawable.getDrawable(1);
            backgroundDrawable.setTint(Color.parseColor("#eaeceb"));
            progressDrawable.setTint(Color.parseColor(habit_color));
        }
    }

    private void listen_to_on_click_on_the_colors() {
        if (getView() != null) {
            View gray_view_selected_number_1 = getView().findViewById(R.id.gray_view_selected_number_1);
            View check_mark_selected_number_1 = getView().findViewById(R.id.check_mark_selected_number_1);
            View gray_view_selected_number_2 = getView().findViewById(R.id.gray_view_selected_number_2);
            View check_mark_selected_number_2 = getView().findViewById(R.id.check_mark_selected_number_2);
            View gray_view_selected_number_3 = getView().findViewById(R.id.gray_view_selected_number_3);
            View check_mark_selected_number_3 = getView().findViewById(R.id.check_mark_selected_number_3);
            View gray_view_selected_number_4 = getView().findViewById(R.id.gray_view_selected_number_4);
            View check_mark_selected_number_4 = getView().findViewById(R.id.check_mark_selected_number_4);
            View gray_view_selected_number_5 = getView().findViewById(R.id.gray_view_selected_number_5);
            View check_mark_selected_number_5 = getView().findViewById(R.id.check_mark_selected_number_5);
            View gray_view_selected_number_6 = getView().findViewById(R.id.gray_view_selected_number_6);
            View check_mark_selected_number_6 = getView().findViewById(R.id.check_mark_selected_number_6);
            View gray_view_selected_number_7 = getView().findViewById(R.id.gray_view_selected_number_7);
            View check_mark_selected_number_7 = getView().findViewById(R.id.check_mark_selected_number_7);
            View gray_view_selected_number_8 = getView().findViewById(R.id.gray_view_selected_number_8);
            View check_mark_selected_number_8 = getView().findViewById(R.id.check_mark_selected_number_8);
            View gray_view_selected_number_9 = getView().findViewById(R.id.gray_view_selected_number_9);
            View check_mark_selected_number_9 = getView().findViewById(R.id.check_mark_selected_number_9);
            Button teal_color_circle_under_bottom = getView().findViewById(R.id.teal_color_circle_under_bottom);
            Button red_color_circle_under_bottom = getView().findViewById(R.id.red_color_circle_under_bottom);
            Button green_color_circle_under_bottom = getView().findViewById(R.id.green_color_circle_under_bottom);
            Button blue_color_circle_under_bottom = getView().findViewById(R.id.blue_color_circle_under_bottom);
            Button orange_color_circle_under_bottom = getView().findViewById(R.id.orange_color_circle_under_bottom);
            Button brown_color_circle_under_bottom = getView().findViewById(R.id.brown_color_circle_under_bottom);
            Button black_color_circle_under_bottom = getView().findViewById(R.id.black_color_circle_under_bottom);
            Button cyan_color_circle_under_bottom = getView().findViewById(R.id.cyan_color_circle_under_bottom);
            Button lime_color_circle_under_bottom = getView().findViewById(R.id.lime_color_circle_under_bottom);
            Button magenta_color_circle_under_bottom = getView().findViewById(R.id.magenta_color_circle_under_bottom);
            Button navy_color_circle_under_bottom = getView().findViewById(R.id.navy_color_circle_under_bottom);
            Button pink_color_circle_under_bottom = getView().findViewById(R.id.pink_color_circle_under_bottom);
            Button yellow_color_circle_under_bottom = getView().findViewById(R.id.yellow_color_circle_under_bottom);
            Button purple_color_circle_under_bottom = getView().findViewById(R.id.purple_color_circle_under_bottom);
            View gray_view_selected_number_10 = getView().findViewById(R.id.gray_view_selected_number_10);
            View check_mark_selected_number_10 = getView().findViewById(R.id.check_mark_selected_number_10);
            View gray_view_selected_number_11 = getView().findViewById(R.id.gray_view_selected_number_11);
            View check_mark_selected_number_11 = getView().findViewById(R.id.check_mark_selected_number_11);
            View gray_view_selected_number_12 = getView().findViewById(R.id.gray_view_selected_number_12);
            View check_mark_selected_number_12 = getView().findViewById(R.id.check_mark_selected_number_12);
            View gray_view_selected_number_13 = getView().findViewById(R.id.gray_view_selected_number_13);
            View check_mark_selected_number_13 = getView().findViewById(R.id.check_mark_selected_number_13);
            View gray_view_selected_number_14 = getView().findViewById(R.id.gray_view_selected_number_14);
            View check_mark_selected_number_14 = getView().findViewById(R.id.check_mark_selected_number_14);
            teal_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clear_all_check_marks();
                    gray_view_selected_number_1.setVisibility(View.VISIBLE);
                    check_mark_selected_number_1.setVisibility(View.VISIBLE);
                    habit_color = "#ed94c9";
                    color_every_thing();
                }
            });
            red_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clear_all_check_marks();
                    gray_view_selected_number_2.setVisibility(View.VISIBLE);
                    check_mark_selected_number_2.setVisibility(View.VISIBLE);
                    habit_color = "#e095ea";
                    color_every_thing();
                }
            });
            green_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clear_all_check_marks();
                    gray_view_selected_number_3.setVisibility(View.VISIBLE);
                    check_mark_selected_number_3.setVisibility(View.VISIBLE);
                    habit_color = "#ec8aad";
                    color_every_thing();
                }
            });
            blue_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clear_all_check_marks();
                    gray_view_selected_number_4.setVisibility(View.VISIBLE);
                    check_mark_selected_number_4.setVisibility(View.VISIBLE);
                    habit_color = "#ea867c";
                    color_every_thing();
                }
            });
            orange_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clear_all_check_marks();
                    gray_view_selected_number_5.setVisibility(View.VISIBLE);
                    check_mark_selected_number_5.setVisibility(View.VISIBLE);
                    habit_color = "#e7aa6d";
                    color_every_thing();
                }
            });
            brown_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clear_all_check_marks();
                    gray_view_selected_number_6.setVisibility(View.VISIBLE);
                    check_mark_selected_number_6.setVisibility(View.VISIBLE);
                    habit_color = "#ddc652";
                    color_every_thing();
                }
            });
            black_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clear_all_check_marks();
                    gray_view_selected_number_7.setVisibility(View.VISIBLE);
                    check_mark_selected_number_7.setVisibility(View.VISIBLE);
                    habit_color = "#b3cd5f";
                    color_every_thing();
                }
            });
            cyan_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clear_all_check_marks();
                    gray_view_selected_number_8.setVisibility(View.VISIBLE);
                    check_mark_selected_number_8.setVisibility(View.VISIBLE);
                    habit_color = "#7fc5a0";
                    color_every_thing();
                }
            });
            lime_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clear_all_check_marks();
                    gray_view_selected_number_9.setVisibility(View.VISIBLE);
                    check_mark_selected_number_9.setVisibility(View.VISIBLE);
                    habit_color = "#6ecabd";
                    color_every_thing();
                }
            });
            magenta_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clear_all_check_marks();
                    gray_view_selected_number_10.setVisibility(View.VISIBLE);
                    check_mark_selected_number_10.setVisibility(View.VISIBLE);
                    habit_color = "#77cebb";
                    color_every_thing();
                }
            });
            navy_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clear_all_check_marks();
                    gray_view_selected_number_11.setVisibility(View.VISIBLE);
                    check_mark_selected_number_11.setVisibility(View.VISIBLE);
                    habit_color = "#6bb6db";
                    color_every_thing();
                }
            });
            pink_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clear_all_check_marks();
                    gray_view_selected_number_12.setVisibility(View.VISIBLE);
                    check_mark_selected_number_12.setVisibility(View.VISIBLE);
                    habit_color = "#7ea8e5";
                    color_every_thing();
                }
            });
            yellow_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clear_all_check_marks();
                    gray_view_selected_number_13.setVisibility(View.VISIBLE);
                    check_mark_selected_number_13.setVisibility(View.VISIBLE);
                    habit_color = "#888ce3";
                    color_every_thing();
                }
            });
            purple_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*clear_all_check_marks();
                    gray_view_selected_number_14.setVisibility(View.VISIBLE);
                    check_mark_selected_number_14.setVisibility(View.VISIBLE);*/
                    if (Payment_processer.getInstance().state_of_the_user()) {
                        ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(getContext())
                                .setTitle("ColorPicker")
                                .setPositiveButton("Ok", new ColorEnvelopeListener() {
                                    @Override
                                    public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                                                /*Intent i = new Intent()
                                                        .putExtra("color", "#".concat(envelope.getHexCode()));
                                                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                                                dismiss();*/
                                        clear_all_check_marks();
                                        gray_view_selected_number_14.setVisibility(View.VISIBLE);
                                        check_mark_selected_number_14.setVisibility(View.VISIBLE);
                                        habit_color = "#".concat(envelope.getHexCode());
                                        color_every_thing();
                                    }
                                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                })
                                .attachAlphaSlideBar(false) // the default value is true.
                                .attachBrightnessSlideBar(true)  // the default value is true.
                                .setBottomSpace(12);
                        ColorPickerView colorPickerView = builder.getColorPickerView();
                        colorPickerView.setFlagView(new Color_picker_custom_flag(getContext(), R.layout.color_picker_layout_flag));
                        colorPickerView.setInitialColor(Color.parseColor(habit_color));
                        builder.show();
                    } else {
                        Add_new_habit_2 old_fragment = (Add_new_habit_2) getActivity().getSupportFragmentManager().findFragmentByTag("add a habit");
                        Buy_premuim buy_premuim = new Buy_premuim("Use custom color picker", true, old_fragment);
                        if (old_fragment != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
                        }
                    }
                }
            });
        }
    }

    private void clear_all_check_marks() {
        if (getView() != null) {
            View gray_view_selected_number_1 = getView().findViewById(R.id.gray_view_selected_number_1);
            View check_mark_selected_number_1 = getView().findViewById(R.id.check_mark_selected_number_1);
            View gray_view_selected_number_2 = getView().findViewById(R.id.gray_view_selected_number_2);
            View check_mark_selected_number_2 = getView().findViewById(R.id.check_mark_selected_number_2);
            View gray_view_selected_number_3 = getView().findViewById(R.id.gray_view_selected_number_3);
            View check_mark_selected_number_3 = getView().findViewById(R.id.check_mark_selected_number_3);
            View gray_view_selected_number_4 = getView().findViewById(R.id.gray_view_selected_number_4);
            View check_mark_selected_number_4 = getView().findViewById(R.id.check_mark_selected_number_4);
            View gray_view_selected_number_5 = getView().findViewById(R.id.gray_view_selected_number_5);
            View check_mark_selected_number_5 = getView().findViewById(R.id.check_mark_selected_number_5);
            View gray_view_selected_number_6 = getView().findViewById(R.id.gray_view_selected_number_6);
            View check_mark_selected_number_6 = getView().findViewById(R.id.check_mark_selected_number_6);
            View gray_view_selected_number_7 = getView().findViewById(R.id.gray_view_selected_number_7);
            View check_mark_selected_number_7 = getView().findViewById(R.id.check_mark_selected_number_7);
            View gray_view_selected_number_8 = getView().findViewById(R.id.gray_view_selected_number_8);
            View check_mark_selected_number_8 = getView().findViewById(R.id.check_mark_selected_number_8);
            View gray_view_selected_number_9 = getView().findViewById(R.id.gray_view_selected_number_9);
            View check_mark_selected_number_9 = getView().findViewById(R.id.check_mark_selected_number_9);
            View gray_view_selected_number_10 = getView().findViewById(R.id.gray_view_selected_number_10);
            View check_mark_selected_number_10 = getView().findViewById(R.id.check_mark_selected_number_10);
            View gray_view_selected_number_11 = getView().findViewById(R.id.gray_view_selected_number_11);
            View check_mark_selected_number_11 = getView().findViewById(R.id.check_mark_selected_number_11);
            View gray_view_selected_number_12 = getView().findViewById(R.id.gray_view_selected_number_12);
            View check_mark_selected_number_12 = getView().findViewById(R.id.check_mark_selected_number_12);
            View gray_view_selected_number_13 = getView().findViewById(R.id.gray_view_selected_number_13);
            View check_mark_selected_number_13 = getView().findViewById(R.id.check_mark_selected_number_13);
            View gray_view_selected_number_14 = getView().findViewById(R.id.gray_view_selected_number_14);
            View check_mark_selected_number_14 = getView().findViewById(R.id.check_mark_selected_number_14);
            gray_view_selected_number_1.setVisibility(View.INVISIBLE);
            check_mark_selected_number_1.setVisibility(View.INVISIBLE);
            gray_view_selected_number_2.setVisibility(View.INVISIBLE);
            check_mark_selected_number_2.setVisibility(View.INVISIBLE);
            gray_view_selected_number_3.setVisibility(View.INVISIBLE);
            check_mark_selected_number_3.setVisibility(View.INVISIBLE);
            gray_view_selected_number_4.setVisibility(View.INVISIBLE);
            check_mark_selected_number_4.setVisibility(View.INVISIBLE);
            gray_view_selected_number_5.setVisibility(View.INVISIBLE);
            check_mark_selected_number_5.setVisibility(View.INVISIBLE);
            gray_view_selected_number_6.setVisibility(View.INVISIBLE);
            check_mark_selected_number_6.setVisibility(View.INVISIBLE);
            gray_view_selected_number_7.setVisibility(View.INVISIBLE);
            check_mark_selected_number_7.setVisibility(View.INVISIBLE);
            gray_view_selected_number_8.setVisibility(View.INVISIBLE);
            check_mark_selected_number_8.setVisibility(View.INVISIBLE);
            gray_view_selected_number_9.setVisibility(View.INVISIBLE);
            check_mark_selected_number_9.setVisibility(View.INVISIBLE);
            gray_view_selected_number_10.setVisibility(View.INVISIBLE);
            check_mark_selected_number_10.setVisibility(View.INVISIBLE);
            gray_view_selected_number_11.setVisibility(View.INVISIBLE);
            check_mark_selected_number_11.setVisibility(View.INVISIBLE);
            gray_view_selected_number_12.setVisibility(View.INVISIBLE);
            check_mark_selected_number_12.setVisibility(View.INVISIBLE);
            gray_view_selected_number_13.setVisibility(View.INVISIBLE);
            check_mark_selected_number_13.setVisibility(View.INVISIBLE);
            gray_view_selected_number_14.setVisibility(View.INVISIBLE);
            check_mark_selected_number_14.setVisibility(View.INVISIBLE);
        }
    }

    private void color_every_thing() {
        set_continue_color_button();
        set_the_color_for_the_bar_at_the_top();
        color_the_icon_button();
        color_the_icon_circle();
        color_underline_of_emoji();
        set_days_color_frequency();
        color_the_add_a_reminder_notification();
        /*color_the_done_button();
        set_tint_of_color_button_final();*/
        //color_the_cursor();
    }

    private void lock_the_custom_color() {
        if (getView() != null && getActivity() != null) {
            View lock_selected_number_14 = getView().findViewById(R.id.lock_selected_number_14);
            View gray_view_for_lock = getView().findViewById(R.id.gray_view_for_lock);
            if (Payment_processer.getInstance().state_of_the_user()) {
                gray_view_for_lock.setVisibility(View.INVISIBLE);
                lock_selected_number_14.setVisibility(View.INVISIBLE);
            } else {
                gray_view_for_lock.setVisibility(View.VISIBLE);
                lock_selected_number_14.setVisibility(View.VISIBLE);
            }
        }
    }

    private void select_the_selected_color() {
        if (getView() != null) {
            View gray_view_selected_number_1 = getView().findViewById(R.id.gray_view_selected_number_1);
            View check_mark_selected_number_1 = getView().findViewById(R.id.check_mark_selected_number_1);
            View gray_view_selected_number_2 = getView().findViewById(R.id.gray_view_selected_number_2);
            View check_mark_selected_number_2 = getView().findViewById(R.id.check_mark_selected_number_2);
            View gray_view_selected_number_3 = getView().findViewById(R.id.gray_view_selected_number_3);
            View check_mark_selected_number_3 = getView().findViewById(R.id.check_mark_selected_number_3);
            View gray_view_selected_number_4 = getView().findViewById(R.id.gray_view_selected_number_4);
            View check_mark_selected_number_4 = getView().findViewById(R.id.check_mark_selected_number_4);
            View gray_view_selected_number_5 = getView().findViewById(R.id.gray_view_selected_number_5);
            View check_mark_selected_number_5 = getView().findViewById(R.id.check_mark_selected_number_5);
            View gray_view_selected_number_6 = getView().findViewById(R.id.gray_view_selected_number_6);
            View check_mark_selected_number_6 = getView().findViewById(R.id.check_mark_selected_number_6);
            View gray_view_selected_number_7 = getView().findViewById(R.id.gray_view_selected_number_7);
            View check_mark_selected_number_7 = getView().findViewById(R.id.check_mark_selected_number_7);
            View gray_view_selected_number_8 = getView().findViewById(R.id.gray_view_selected_number_8);
            View check_mark_selected_number_8 = getView().findViewById(R.id.check_mark_selected_number_8);
            View gray_view_selected_number_9 = getView().findViewById(R.id.gray_view_selected_number_9);
            View check_mark_selected_number_9 = getView().findViewById(R.id.check_mark_selected_number_9);
            View gray_view_selected_number_10 = getView().findViewById(R.id.gray_view_selected_number_10);
            View check_mark_selected_number_10 = getView().findViewById(R.id.check_mark_selected_number_10);
            View gray_view_selected_number_11 = getView().findViewById(R.id.gray_view_selected_number_11);
            View check_mark_selected_number_11 = getView().findViewById(R.id.check_mark_selected_number_11);
            View gray_view_selected_number_12 = getView().findViewById(R.id.gray_view_selected_number_12);
            View check_mark_selected_number_12 = getView().findViewById(R.id.check_mark_selected_number_12);
            View gray_view_selected_number_13 = getView().findViewById(R.id.gray_view_selected_number_13);
            View check_mark_selected_number_13 = getView().findViewById(R.id.check_mark_selected_number_13);
            View gray_view_selected_number_14 = getView().findViewById(R.id.gray_view_selected_number_14);
            View check_mark_selected_number_14 = getView().findViewById(R.id.check_mark_selected_number_14);
            clear_all_check_marks();
            if (habit_color.equals("#ed94c9")) {
                gray_view_selected_number_1.setVisibility(View.VISIBLE);
                check_mark_selected_number_1.setVisibility(View.VISIBLE);
            } else if (habit_color.equals("#e095ea")) {
                gray_view_selected_number_2.setVisibility(View.VISIBLE);
                check_mark_selected_number_2.setVisibility(View.VISIBLE);
            } else if (habit_color.equals("#ec8aad")) {
                gray_view_selected_number_3.setVisibility(View.VISIBLE);
                check_mark_selected_number_3.setVisibility(View.VISIBLE);
            } else if (habit_color.equals("#ea867c")) {
                gray_view_selected_number_4.setVisibility(View.VISIBLE);
                check_mark_selected_number_4.setVisibility(View.VISIBLE);
            } else if (habit_color.equals("#e7aa6d")) {
                gray_view_selected_number_5.setVisibility(View.VISIBLE);
                check_mark_selected_number_5.setVisibility(View.VISIBLE);
            } else if (habit_color.equals("#ddc652")) {
                gray_view_selected_number_6.setVisibility(View.VISIBLE);
                check_mark_selected_number_6.setVisibility(View.VISIBLE);
            } else if (habit_color.equals("#b3cd5f")) {
                gray_view_selected_number_7.setVisibility(View.VISIBLE);
                check_mark_selected_number_7.setVisibility(View.VISIBLE);
            } else if (habit_color.equals("#7fc5a0")) {
                gray_view_selected_number_8.setVisibility(View.VISIBLE);
                check_mark_selected_number_8.setVisibility(View.VISIBLE);
            } else if (habit_color.equals("#6ecabd")) {
                gray_view_selected_number_9.setVisibility(View.VISIBLE);
                check_mark_selected_number_9.setVisibility(View.VISIBLE);
            } else if (habit_color.equals("#77cebb")) {
                gray_view_selected_number_10.setVisibility(View.VISIBLE);
                check_mark_selected_number_10.setVisibility(View.VISIBLE);
            } else if (habit_color.equals("#6bb6db")) {
                gray_view_selected_number_11.setVisibility(View.VISIBLE);
                check_mark_selected_number_11.setVisibility(View.VISIBLE);
            } else if (habit_color.equals("#7ea8e5")) {
                gray_view_selected_number_12.setVisibility(View.VISIBLE);
                check_mark_selected_number_12.setVisibility(View.VISIBLE);
            } else if (habit_color.equals("#888ce3")) {
                gray_view_selected_number_13.setVisibility(View.VISIBLE);
                check_mark_selected_number_13.setVisibility(View.VISIBLE);
            } else {
                gray_view_selected_number_14.setVisibility(View.VISIBLE);
                check_mark_selected_number_14.setVisibility(View.VISIBLE);
            }
        }
    }

    private void create_custom_habit_listen() {
        if (getView() != null) {
            CardView create_a_custom_habit = getView().findViewById(R.id.create_a_custom_habit);
            create_a_custom_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    forward_button_pressed();
                }
            });
        }
    }

    private void reset_everything() {
        clear_the_name_edit_text();
        reset_habit_color();
        reset_the_build_or_quit();
        reset_the_emoji_edit_text_and_text_view();
        reset_the_selected_days();
        reset_the_number_of_times_per_day_edit_text();
        reset_the_choose_the_frequency_spinner();
        reset_the_notifications_selector();
        clear_the_reminder_array_list();
    }

    private void clear_the_name_edit_text() {
        if (getView() != null) {
            EditText edit_text_to_enter_habit_name_ = getView().findViewById(R.id.edit_text_to_enter_habit_name_);
            edit_text_to_enter_habit_name_.getText().clear();
        }
    }

    private void reset_habit_color() {
        if (getView() != null) {
            habit_color = get_a_random_color();
        }
    }

    private void reset_the_build_or_quit() {
        if (getView() != null) {
            Button white_button_build_habit_add_new_habit = getView().findViewById(R.id.white_button_build_habit_add_new_habit);
            Button white_button_quit_add_new_habit = getView().findViewById(R.id.white_button_quit_add_new_habit);
            Button grey_button_build_habit_add_new_habit = getView().findViewById(R.id.grey_button_build_habit_add_new_habit);
            Button grey_button_quit_add_new_habit = getView().findViewById(R.id.grey_button_quit_add_new_habit);
            white_button_build_habit_add_new_habit.setVisibility(View.VISIBLE);
            grey_button_build_habit_add_new_habit.setVisibility(View.INVISIBLE);
            white_button_quit_add_new_habit.setVisibility(View.INVISIBLE);
            grey_button_quit_add_new_habit.setVisibility(View.VISIBLE);
        }
    }

    private void reset_the_emoji_edit_text_and_text_view() {
        if (getView() != null) {
            EmojiEditText emoji_edit_text = getView().findViewById(R.id.emoji_edit_text);
            EmojiTextView emojiTextView = getView().findViewById(R.id.emoji_view_add_new_habit);
            emoji_edit_text.setText("");
            emojiTextView.setText("");
        }
    }

    private void reset_the_selected_days() {
        if (getView() != null) {
            CircularToggle day_1_choose_days_of_week = getView().findViewById(R.id.day_1_choose_days_of_week);
            CircularToggle day_2_choose_days_of_week = getView().findViewById(R.id.day_2_choose_days_of_week);
            CircularToggle day_3_choose_days_of_week = getView().findViewById(R.id.day_3_choose_days_of_week);
            CircularToggle day_4_choose_days_of_week = getView().findViewById(R.id.day_4_choose_days_of_week);
            CircularToggle day_5_choose_days_of_week = getView().findViewById(R.id.day_5_choose_days_of_week);
            CircularToggle day_6_choose_days_of_week = getView().findViewById(R.id.day_6_choose_days_of_week);
            CircularToggle day_7_choose_days_of_week = getView().findViewById(R.id.day_7_choose_days_of_week);
            day_1_choose_days_of_week.setChecked(true);
            day_2_choose_days_of_week.setChecked(true);
            day_3_choose_days_of_week.setChecked(true);
            day_4_choose_days_of_week.setChecked(true);
            day_5_choose_days_of_week.setChecked(true);
            day_6_choose_days_of_week.setChecked(true);
            day_7_choose_days_of_week.setChecked(true);
        }
    }

    private void reset_the_number_of_times_per_day_edit_text() {
        if (getView() != null) {
            EditText edit_text_to_enter_how_many_times_per_day = getView().findViewById(R.id.edit_text_to_enter_how_many_times_per_day);
            edit_text_to_enter_how_many_times_per_day.getText().clear();
        }
    }

    private void reset_the_choose_the_frequency_spinner() {
        if (getView() != null) {
            PowerSpinnerView spinner_to_choose_frequency_of_habit_add_new_habit = getView().findViewById(R.id.spinner_to_choose_frequency_of_habit_add_new_habit);
            MultiSelectToggleGroup select_days_of_week_for_specific_days_add_new_habit = getView().findViewById(R.id.select_days_of_week_for_specific_days_add_new_habit);
            spinner_to_choose_frequency_of_habit_add_new_habit.selectItemByIndex(0);
            select_days_of_week_for_specific_days_add_new_habit.setVisibility(View.INVISIBLE);
        }
    }

    private void reset_the_notifications_selector() {
        if (getView() != null) {
            SwitchMaterial switch_to_turn_on_notifications_add_new_habit = getView().findViewById(R.id.switch_to_turn_on_notifications_add_new_habit);
            ConstraintLayout layout_with_more_info_add_notification = getView().findViewById(R.id.layout_with_more_info_add_notification);
            RecyclerView recyvle_view_show_reminders = getView().findViewById(R.id.recyvle_view_show_reminders);
            TextView text_saying_no_reminder = getView().findViewById(R.id.text_saying_no_reminder);
            switch_to_turn_on_notifications_add_new_habit.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#ececec")));
            switch_to_turn_on_notifications_add_new_habit.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#b2b2b2")));
            layout_with_more_info_add_notification.setVisibility(View.GONE);
            recyvle_view_show_reminders.setVisibility(View.GONE);
            text_saying_no_reminder.setVisibility(View.INVISIBLE);
            switch_to_turn_on_notifications_add_new_habit.setChecked(false);

        }
    }

    private void clear_the_reminder_array_list() {
        reminder_arraylist.clear();
        adapter_for_reminder.notifyDataSetChanged();
    }

    private void color_the_icon_button() {
        if (getView() != null) {
            Button pick_icon_button_in_add_new_habit = getView().findViewById(R.id.pick_icon_button_in_add_new_habit);
            pick_icon_button_in_add_new_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(habit_color)));
        }
    }

    private void color_the_icon_circle() {
        if (getView() != null && getContext() != null) {
            View color_showing_the_chosen_view = getView().findViewById(R.id.color_showing_the_chosen_view);
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.z_dark_green).mutate();
            drawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(drawable, Color.parseColor(habit_color));
            color_showing_the_chosen_view.setForeground(drawable);
        }
    }

    private void pick_icon_button_listen() {
        if (getView() != null) {
            Button pick_icon_button_in_add_new_habit = getView().findViewById(R.id.pick_icon_button_in_add_new_habit);
            pick_icon_button_in_add_new_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pick_icon();
                }
            });
        }
    }

    private void pick_icon_circle_button_listen() {
        if (getView() != null) {
            Button button_with_rippl_icon_add_new_habit = getView().findViewById(R.id.button_with_rippl_icon_add_new_habit);
            button_with_rippl_icon_add_new_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pick_icon();
                }
            });
        }
    }

    private void pick_icon() {
        if (emojiPopup.isShowing()) {
            hide_emoji_keybaord();
        } else {
            show_emoji_keybaord();
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

    private void set_up_emoji_keyboard() {
        EmojiManager.install(new GoogleEmojiProvider());
    }

    private void listen_to_parent_focus() {
        if (getView() != null) {
            ConstraintLayout layout_to_enter_habit_name = getView().findViewById(R.id.layout_to_enter_habit_name);
            layout_to_enter_habit_name.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    dismiss_keyboard();
                    return false;
                }
            });
        }
    }

    private void configure_emoji_edit_text() {
        if (getView() != null) {
            EmojiEditText emoji_edit_text = getView().findViewById(R.id.emoji_edit_text);
            ConstraintLayout layout_to_pick_icon_add_new_habit = getView().findViewById(R.id.layout_to_pick_icon_add_new_habit);
            if (emojiPopup == null) {
                EmojiTheming emojiTheming = new EmojiTheming(Color.WHITE, Color.parseColor(habit_color), Color.parseColor(habit_color), Color.parseColor(habit_color), Color.parseColor(habit_color), Color.parseColor(habit_color));
                emojiPopup = new EmojiPopup(layout_to_pick_icon_add_new_habit, emoji_edit_text, emojiTheming);
                DisableKeyboardInputTrait disableKeyboardInputTrait = new DisableKeyboardInputTrait(emojiPopup);
                ForceSingleEmojiTrait forceSingleEmojiTrait = new ForceSingleEmojiTrait();
                disableKeyboardInputTrait.install(emoji_edit_text);
                forceSingleEmojiTrait.install(emoji_edit_text);
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

    private void color_underline_of_emoji() {
        if (getView() != null) {
            EmojiEditText emoji_edit_text = getView().findViewById(R.id.emoji_edit_text);
            emoji_edit_text.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(habit_color)));
        }
    }

    /*private void emoji_focus_listen(){
        if(getView()!=null){
            EmojiEditText emoji_edit_text = getView().findViewById(R.id.emoji_edit_text);
            emoji_edit_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus && !emojiPopup.isShowing()){
                        emojiPopup.toggle();
                    }
                }
            });
        }
    }*/

    private void click_outside_emoji_listen() {
        if (getView() != null) {
            ConstraintLayout layout_to_pick_icon_add_new_habit = getView().findViewById(R.id.layout_to_pick_icon_add_new_habit);
            layout_to_pick_icon_add_new_habit.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    hide_emoji_keybaord();
                    return false;
                }
            });
        }
    }

    private void hide_emoji_keybaord() {
        dismiss_keyboard();
        transfer_emoji_from_edit_text_to_view(true);
    }

    private void show_emoji_keybaord() {
        transfer_emoji_from_edit_text_to_view(false);
        emojiPopup.toggle();
    }

    private void add_or_quit_button_listen() {
        if (getView() != null) {
            Button white_button_build_habit_add_new_habit = getView().findViewById(R.id.white_button_build_habit_add_new_habit);
            Button white_button_quit_add_new_habit = getView().findViewById(R.id.white_button_quit_add_new_habit);
            Button grey_button_build_habit_add_new_habit = getView().findViewById(R.id.grey_button_build_habit_add_new_habit);
            Button grey_button_quit_add_new_habit = getView().findViewById(R.id.grey_button_quit_add_new_habit);
            grey_button_build_habit_add_new_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    white_button_build_habit_add_new_habit.setVisibility(View.VISIBLE);
                    grey_button_build_habit_add_new_habit.setVisibility(View.INVISIBLE);
                    white_button_quit_add_new_habit.setVisibility(View.INVISIBLE);
                    grey_button_quit_add_new_habit.setVisibility(View.VISIBLE);
                }
            });
            grey_button_quit_add_new_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    white_button_build_habit_add_new_habit.setVisibility(View.INVISIBLE);
                    grey_button_build_habit_add_new_habit.setVisibility(View.VISIBLE);
                    white_button_quit_add_new_habit.setVisibility(View.VISIBLE);
                    grey_button_quit_add_new_habit.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    private void set_up_frequecny() {
        if (getView() != null) {
            PowerSpinnerView spinner_to_choose_frequency_of_habit_add_new_habit = getView().findViewById(R.id.spinner_to_choose_frequency_of_habit_add_new_habit);
            ArrayList<String> items_list = new ArrayList<>();
            items_list.add("Everyday");
            items_list.add("Specific days per week");
            spinner_to_choose_frequency_of_habit_add_new_habit.setItems(items_list);
            spinner_to_choose_frequency_of_habit_add_new_habit.selectItemByIndex(0);
        }
    }

    private void listen_for_frequncy_spinner_item_select() {
        if (getView() != null) {
            PowerSpinnerView spinner_to_choose_frequency_of_habit_add_new_habit = getView().findViewById(R.id.spinner_to_choose_frequency_of_habit_add_new_habit);
            MultiSelectToggleGroup select_days_of_week_for_specific_days_add_new_habit = getView().findViewById(R.id.select_days_of_week_for_specific_days_add_new_habit);
            spinner_to_choose_frequency_of_habit_add_new_habit.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<Object>() {
                @Override
                public void onItemSelected(int i, @Nullable Object o, int i1, Object t1) {
                    if (i1 == 0) {
                        select_days_of_week_for_specific_days_add_new_habit.setVisibility(View.INVISIBLE);
                    } else if (i1 == 1) {
                        select_days_of_week_for_specific_days_add_new_habit.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    private void set_first_day_of_week_specific_days_per_week() {
        if (getView() != null) {
            CircularToggle day_1_choose_days_of_week = getView().findViewById(R.id.day_1_choose_days_of_week);
            CircularToggle day_2_choose_days_of_week = getView().findViewById(R.id.day_2_choose_days_of_week);
            CircularToggle day_3_choose_days_of_week = getView().findViewById(R.id.day_3_choose_days_of_week);
            CircularToggle day_4_choose_days_of_week = getView().findViewById(R.id.day_4_choose_days_of_week);
            CircularToggle day_5_choose_days_of_week = getView().findViewById(R.id.day_5_choose_days_of_week);
            CircularToggle day_6_choose_days_of_week = getView().findViewById(R.id.day_6_choose_days_of_week);
            CircularToggle day_7_choose_days_of_week = getView().findViewById(R.id.day_7_choose_days_of_week);
            Calendar calendar = Calendar.getInstance();
            /*if (calendar.getFirstDayOfWeek() == Calendar.MONDAY) {
                day_1_choose_days_of_week.setText("M");
                day_2_choose_days_of_week.setText("T");
                day_3_choose_days_of_week.setText("W");
                day_4_choose_days_of_week.setText("T");
                day_5_choose_days_of_week.setText("F");
                day_6_choose_days_of_week.setText("S");
                day_7_choose_days_of_week.setText("S");
            } else if (calendar.getFirstDayOfWeek() == Calendar.FRIDAY) {
                day_1_choose_days_of_week.setText("F");
                day_2_choose_days_of_week.setText("S");
                day_3_choose_days_of_week.setText("S");
                day_4_choose_days_of_week.setText("M");
                day_5_choose_days_of_week.setText("T");
                day_6_choose_days_of_week.setText("W");
                day_7_choose_days_of_week.setText("T");
            } else if (calendar.getFirstDayOfWeek() == Calendar.SATURDAY) {
                day_1_choose_days_of_week.setText("S");
                day_2_choose_days_of_week.setText("S");
                day_3_choose_days_of_week.setText("M");
                day_4_choose_days_of_week.setText("T");
                day_5_choose_days_of_week.setText("W");
                day_6_choose_days_of_week.setText("T");
                day_7_choose_days_of_week.setText("F");
            } else if (calendar.getFirstDayOfWeek() == Calendar.SUNDAY) {
                day_1_choose_days_of_week.setText("S");
                day_2_choose_days_of_week.setText("M");
                day_3_choose_days_of_week.setText("T");
                day_4_choose_days_of_week.setText("W");
                day_5_choose_days_of_week.setText("T");
                day_6_choose_days_of_week.setText("F");
                day_7_choose_days_of_week.setText("S");
            }*/
            day_1_choose_days_of_week.setText("M");
            day_2_choose_days_of_week.setText("T");
            day_3_choose_days_of_week.setText("W");
            day_4_choose_days_of_week.setText("T");
            day_5_choose_days_of_week.setText("F");
            day_6_choose_days_of_week.setText("S");
            day_7_choose_days_of_week.setText("S");
        }
    }

    private void set_days_color_frequency() {
        if (getView() != null) {
            CircularToggle day_1_choose_days_of_week = getView().findViewById(R.id.day_1_choose_days_of_week);
            CircularToggle day_2_choose_days_of_week = getView().findViewById(R.id.day_2_choose_days_of_week);
            CircularToggle day_3_choose_days_of_week = getView().findViewById(R.id.day_3_choose_days_of_week);
            CircularToggle day_4_choose_days_of_week = getView().findViewById(R.id.day_4_choose_days_of_week);
            CircularToggle day_5_choose_days_of_week = getView().findViewById(R.id.day_5_choose_days_of_week);
            CircularToggle day_6_choose_days_of_week = getView().findViewById(R.id.day_6_choose_days_of_week);
            CircularToggle day_7_choose_days_of_week = getView().findViewById(R.id.day_7_choose_days_of_week);
            day_1_choose_days_of_week.setMarkerColor(Color.parseColor(habit_color));
            day_2_choose_days_of_week.setMarkerColor(Color.parseColor(habit_color));
            day_3_choose_days_of_week.setMarkerColor(Color.parseColor(habit_color));
            day_4_choose_days_of_week.setMarkerColor(Color.parseColor(habit_color));
            day_5_choose_days_of_week.setMarkerColor(Color.parseColor(habit_color));
            day_6_choose_days_of_week.setMarkerColor(Color.parseColor(habit_color));
            day_7_choose_days_of_week.setMarkerColor(Color.parseColor(habit_color));
        }
    }

    private void click_outside_number_of_times_per_day_edit_text_listen() {
        if (getView() != null) {
            ConstraintLayout layout_to_choose_how_many_times_per_day = getView().findViewById(R.id.layout_to_choose_how_many_times_per_day);
            layout_to_choose_how_many_times_per_day.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    dismiss_keyboard();
                    return false;
                }
            });
        }
    }

    /*private void color_the_cursor(){
        if(getView()!=null && getContext()!=null){
            Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.color_cursor_for_add_new_habit);
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, Color.parseColor(habit_color));
        }
    }*/

    private void make_the_spinner_go_away() {
        if (getView() != null) {
            PowerSpinnerView spinner_to_choose_frequency_of_habit_add_new_habit = getView().findViewById(R.id.spinner_to_choose_frequency_of_habit_add_new_habit);
            spinner_to_choose_frequency_of_habit_add_new_habit.dismiss();
        }
    }

    private void listen_to_notification_switch() {
        if (getView() != null) {
            SwitchMaterial switch_to_turn_on_notifications_add_new_habit = getView().findViewById(R.id.switch_to_turn_on_notifications_add_new_habit);
            ConstraintLayout layout_with_more_info_add_notification = getView().findViewById(R.id.layout_with_more_info_add_notification);
            RecyclerView recyvle_view_show_reminders = getView().findViewById(R.id.recyvle_view_show_reminders);
            switch_to_turn_on_notifications_add_new_habit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (switch_to_turn_on_notifications_add_new_habit.isChecked()) {
                        int resultColor = ColorUtils.blendARGB(Color.parseColor(habit_color), Color.WHITE, 0.5F);
                        switch_to_turn_on_notifications_add_new_habit.setThumbTintList(ColorStateList.valueOf(Color.parseColor(habit_color)));
                        switch_to_turn_on_notifications_add_new_habit.setTrackTintList(ColorStateList.valueOf(resultColor));
                        layout_with_more_info_add_notification.setVisibility(View.VISIBLE);
                        recyvle_view_show_reminders.setVisibility(View.VISIBLE);
                    } else {
                        switch_to_turn_on_notifications_add_new_habit.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#ececec")));
                        switch_to_turn_on_notifications_add_new_habit.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#b2b2b2")));
                        layout_with_more_info_add_notification.setVisibility(View.GONE);
                        recyvle_view_show_reminders.setVisibility(View.GONE);
                    }
                    toggle_the_no_reminder_visibility();
                }
            });
        }
    }

    private void color_the_add_a_reminder_notification() {
        if (getView() != null) {
//            View line_between_notifications_and_add_new_notifications = getView().findViewById(R.id.line_between_notifications_and_add_new_notifications);
            Button button_add_new_reminder_add_new_habit = getView().findViewById(R.id.button_add_new_reminder_add_new_habit);
//            line_between_notifications_and_add_new_notifications.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(habit_color)));
            button_add_new_reminder_add_new_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(habit_color)));
        }
    }

    private void add_a_reminder_button_listen() {
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

    private void update_progress_bar(int which_screen) {
        if (getView() != null) {
            ProgressBar progress_bar_showing_which_page_I_am_in = getView().findViewById(R.id.progress_bar_showing_which_page_I_am_in);
            progress_bar_showing_which_page_I_am_in.setProgress((int) (12.5 * which_screen));
        }
    }

    private void dismiss_the_spinner_view() {
        if (getView() != null) {
            ConstraintLayout layout_to_choose_how_often_wants_to_do_habit = getView().findViewById(R.id.layout_to_choose_how_often_wants_to_do_habit);
            PowerSpinnerView spinner_to_choose_frequency_of_habit_add_new_habit = getView().findViewById(R.id.spinner_to_choose_frequency_of_habit_add_new_habit);
            layout_to_choose_how_often_wants_to_do_habit.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    spinner_to_choose_frequency_of_habit_add_new_habit.dismiss();
                    return false;
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
            }
        });
        bottom_sheet_add_reminder_add_new_habit.show(getParentFragmentManager(), "add reminder bottom sheet");
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

    private void add_new_item_to_reminder_array_list(long time, ArrayList<String> days, int color) {
        reminder_arraylist.add(new Example_item_reminder_add_new_habit(time, days, color));
        adapter_for_reminder.notifyItemInserted(reminder_arraylist.size() - 1);
    }

    private void toggle_the_no_reminder_visibility() {
        if (getView() != null) {
            SwitchMaterial switch_to_turn_on_notifications_add_new_habit = getView().findViewById(R.id.switch_to_turn_on_notifications_add_new_habit);
            TextView text_saying_no_reminder = getView().findViewById(R.id.text_saying_no_reminder);
            if (switch_to_turn_on_notifications_add_new_habit.isChecked() && reminder_arraylist.isEmpty()) {
                text_saying_no_reminder.setVisibility(View.VISIBLE);
            } else {
                text_saying_no_reminder.setVisibility(View.INVISIBLE);
            }
        }
    }

    /*private void done_button_listen(){
        if(getView()!=null){
            Button done_button_at_the_buttom_add_new_habit = getView().findViewById(R.id.done_button_at_the_buttom_add_new_habit);
            done_button_at_the_buttom_add_new_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    done_button_clicked();
                }
            });
        }
    }

    private void done_button_clicked(){

    }

    private void color_the_done_button(){
        if(getView()!=null){
            Button done_button_at_the_buttom_add_new_habit = getView().findViewById(R.id.done_button_at_the_buttom_add_new_habit);
            done_button_at_the_buttom_add_new_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(habit_color)));
        }
    }

    private void set_tint_of_color_button_final(){
        if(getView()!=null){
            View drawable_with_color_at_the_start_of_color_button_add_new_habit = getView().findViewById(R.id.drawable_with_color_at_the_start_of_color_button_add_new_habit);
            View drawable_with_color_at_the_start_of_icon_button_add_new_habit = getView().findViewById(R.id.drawable_with_color_at_the_start_of_icon_button_add_new_habit);
            drawable_with_color_at_the_start_of_color_button_add_new_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(habit_color)));
            drawable_with_color_at_the_start_of_icon_button_add_new_habit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(habit_color)));
        }
    }

    private void color_button_add_new_habit_final(){
        if(getView()!=null){
            Button change_color_button_in_add_new_habit_final = getView().findViewById(R.id.change_color_button_in_add_new_habit_final);
            change_color_button_in_add_new_habit_final.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    private void change_icon_button_listen_add_new_habit_final(){
        if(getView()!=null){
            Button change_icon_button_in_add_new_habit_final_step = getView().findViewById(R.id.change_icon_button_in_add_new_habit_final_step);
            change_icon_button_in_add_new_habit_final_step.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    private void build_up_and_quit_button_listen_in_the_end(){
        if(getView()!=null){
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
                }
            });
            grey_button_quit_add_new_habit_in_the_final.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    white_button_build_habit_add_new_habit_in_the_final.setVisibility(View.INVISIBLE);
                    white_button_quit_add_new_habit_in_the_final.setVisibility(View.VISIBLE);
                    grey_button_build_habit_add_new_habit_in_the_final.setVisibility(View.VISIBLE);
                    grey_button_quit_add_new_habit_in_the_final.setVisibility(View.INVISIBLE);
                }
            });
        }
    }*/

    private String return_habit_name() {
        EditText edit_text_to_enter_habit_name_ = getView().findViewById(R.id.edit_text_to_enter_habit_name_);
        return edit_text_to_enter_habit_name_.getText().toString().trim();
    }

    private String return_habit_color() {
        EditText edit_text_to_enter_habit_name_ = getView().findViewById(R.id.edit_text_to_enter_habit_name_);
        return habit_color;
    }

    private String return_icon() {
        EmojiTextView emoji_view_add_new_habit = getView().findViewById(R.id.emoji_view_add_new_habit);
        return emoji_view_add_new_habit.getText().toString();
    }

    private String return_type() {
        Button white_button_build_habit_add_new_habit = getView().findViewById(R.id.white_button_build_habit_add_new_habit);
        //Button white_button_quit_add_new_habit = getView().findViewById(R.id.white_button_quit_add_new_habit);
        if (white_button_build_habit_add_new_habit.getVisibility() == View.VISIBLE) {
            return "Build_up";
        } else {
            return "Quit";
        }
    }

    private ArrayList<String> return_habit_frequencuy() {
        PowerSpinnerView spinner_to_choose_frequency_of_habit_add_new_habit = getView().findViewById(R.id.spinner_to_choose_frequency_of_habit_add_new_habit);
        CircularToggle day_1_choose_days_of_week = getView().findViewById(R.id.day_1_choose_days_of_week);
        CircularToggle day_2_choose_days_of_week = getView().findViewById(R.id.day_2_choose_days_of_week);
        CircularToggle day_3_choose_days_of_week = getView().findViewById(R.id.day_3_choose_days_of_week);
        CircularToggle day_4_choose_days_of_week = getView().findViewById(R.id.day_4_choose_days_of_week);
        CircularToggle day_5_choose_days_of_week = getView().findViewById(R.id.day_5_choose_days_of_week);
        CircularToggle day_6_choose_days_of_week = getView().findViewById(R.id.day_6_choose_days_of_week);
        CircularToggle day_7_choose_days_of_week = getView().findViewById(R.id.day_7_choose_days_of_week);
        ArrayList<String> days = new ArrayList<>();
        if (spinner_to_choose_frequency_of_habit_add_new_habit.getSelectedIndex() == 0) {
            days.add("Monday");
            days.add("Tuesday");
            days.add("Wednesday");
            days.add("Thursday");
            days.add("Friday");
            days.add("Saturday");
            days.add("Sunday");
        } else if (spinner_to_choose_frequency_of_habit_add_new_habit.getSelectedIndex() == 1) {
            if (day_1_choose_days_of_week.isChecked()) {
                days.add("Monday");
            }
            if (day_2_choose_days_of_week.isChecked()) {
                days.add("Tuesday");
            }
            if (day_3_choose_days_of_week.isChecked()) {
                days.add("Wednesday");
            }
            if (day_4_choose_days_of_week.isChecked()) {
                days.add("Thursday");
            }
            if (day_5_choose_days_of_week.isChecked()) {
                days.add("Friday");
            }
            if (day_6_choose_days_of_week.isChecked()) {
                days.add("Saturday");
            }
            if (day_7_choose_days_of_week.isChecked()) {
                days.add("Sunday");
            }
        }
        return days;
    }

    private int return_frequency_extra() {
        EditText edit_text_to_enter_how_many_times_per_day = getView().findViewById(R.id.edit_text_to_enter_how_many_times_per_day);
        return Integer.parseInt(edit_text_to_enter_how_many_times_per_day.getText().toString());
    }

    private boolean are_notifications_on() {
        SwitchMaterial switch_to_turn_on_notifications_add_new_habit = getView().findViewById(R.id.switch_to_turn_on_notifications_add_new_habit);
        return switch_to_turn_on_notifications_add_new_habit.isChecked();
    }

    private ArrayList<Notification_object_add_new_habit> return_notifications() {
        ArrayList<Notification_object_add_new_habit> notification_object_add_new_habits = new ArrayList<>();
        for (int i = 0; i < reminder_arraylist.size(); i++) {
            Notification_object_add_new_habit object_add_new_habit = new Notification_object_add_new_habit(reminder_arraylist.get(i).getDays(), reminder_arraylist.get(i).getTime());
            notification_object_add_new_habits.add(object_add_new_habit);
        }
        return notification_object_add_new_habits;
    }

    private void set_up_recycle_view_habits() {
        if (getView() != null) {
            RecyclerView habits_recycle_view_not_custom = getView().findViewById(R.id.habits_recycle_view_not_custom);
            ArrayList<Example_item_add_new_habit> add_new_habits = new ArrayList<>();
            //good habits
            add_new_habits.add(new Example_item_add_new_habit(get_a_random_color(), "Workout", "\uD83C\uDFCB️"));
            add_new_habits.add(new Example_item_add_new_habit(get_a_random_color(), "Drink Water", "\uD83D\uDCA7"));
            add_new_habits.add(new Example_item_add_new_habit(get_a_random_color(), "Brush Teeth", "\uD83E\uDEA5"));
            add_new_habits.add(new Example_item_add_new_habit(get_a_random_color(), "Shower", "\uD83D\uDEC0"));
            add_new_habits.add(new Example_item_add_new_habit(get_a_random_color(), "Read", "\uD83D\uDCDA"));
            add_new_habits.add(new Example_item_add_new_habit(get_a_random_color(), "Walk", "\uD83D\uDEB6"));
            add_new_habits.add(new Example_item_add_new_habit(get_a_random_color(), "Eat Healthy", "\uD83C\uDF47"));
            //bad habits
            add_new_habits.add(new Example_item_add_new_habit(get_a_random_color(), "Avoid Fast Food", "\uD83C\uDF54"));
            add_new_habits.add(new Example_item_add_new_habit(get_a_random_color(), "Avoid Social Media", "\uD83D\uDCF1"));
            add_new_habits.add(new Example_item_add_new_habit(get_a_random_color(), "Avoid Video Games", "\uD83C\uDFAE"));
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            Adapter_add_habit adapter_add_habit = new Adapter_add_habit(add_new_habits);
            habits_recycle_view_not_custom.setLayoutManager(linearLayoutManager);
            habits_recycle_view_not_custom.setAdapter(adapter_add_habit);
            ArrayList<String> days = new ArrayList<>();
            days.add("Monday");
            days.add("Tuesday");
            days.add("Wednesday");
            days.add("Thursday");
            days.add("Friday");
            days.add("Saturday");
            days.add("Sunday");
            ArrayList<Notification_object_add_new_habit> notification_object_add_new_habits = new ArrayList<>();
            notification_object_add_new_habits.add(new Notification_object_add_new_habit(days, TimeUnit.HOURS.toMillis(21)));
            adapter_add_habit.set_card_clicked_listen(new Adapter_add_habit.Card_clicked_listen() {
                @Override
                public void card_just_got_clicked(String name, String color, String emoji) {
                    if (name.equals("Workout") || name.equals("Drink Water") || name.equals("Brush Teeth") || name.equals("Shower") || name.equals("Read") || name.equals("Walk") || name.equals("Eat Healthy")) {
                        Add_new_habit_final add_new_habit_final = new Add_new_habit_final(name, color, emoji, "Build_up", days, 1, true, notification_object_add_new_habits);
                        Add_new_habit_2 old_fragment = (Add_new_habit_2) getActivity().getSupportFragmentManager().findFragmentByTag("add a habit");
                        getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).add(R.id.fragment_container, add_new_habit_final, "add a habit final").show(add_new_habit_final).commit();
                    }
                    if (name.equals("Avoid Fast Food") || name.equals("Avoid Social Media") || name.equals("Avoid Video Games")) {
                        Add_new_habit_final add_new_habit_final = new Add_new_habit_final(name, color, emoji, "Quit", true, notification_object_add_new_habits);
                        Add_new_habit_2 old_fragment = (Add_new_habit_2) getActivity().getSupportFragmentManager().findFragmentByTag("add a habit");
                        getActivity().getSupportFragmentManager().beginTransaction().remove(old_fragment).add(R.id.fragment_container, add_new_habit_final, "add a habit final").show(add_new_habit_final).commit();
                    }
                }
            });
        }
    }
}