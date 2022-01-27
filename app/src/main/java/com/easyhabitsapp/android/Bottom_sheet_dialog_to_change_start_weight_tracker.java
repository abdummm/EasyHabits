package com.easyhabitsapp.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Bottom_sheet_dialog_to_change_start_weight_tracker extends BottomSheetDialogFragment {
    private View mview;
    private BottomSheetListener_for_changing_start_weight_info listener;

    public interface BottomSheetListener_for_changing_start_weight_info {
        void on_dailog_close_weight();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_to_change_start_weight, container, false);
        mview = view;
        button_listen();
        ok_button_listen();
        make_back_button_change();
        return view;
    }

    private void button_listen() {
        Button button_to_change_units_bottom_sheet_start = mview.findViewById(R.id.button_to_change_units_bottom_sheet_start);
        Button button_to_change_height_in_button_sheet = mview.findViewById(R.id.button_to_change_height_in_button_sheet);
        Button change_weight_for_sheet_weight_tracker = mview.findViewById(R.id.change_weight_for_sheet_weight_tracker);
        Button change_goal_in_bottom_sheet_weight_tracker = mview.findViewById(R.id.change_goal_in_bottom_sheet_weight_tracker);
        Button view_history_button_in_weight_tracekr = mview.findViewById(R.id.view_history_button_in_weight_tracekr);
        button_to_change_units_bottom_sheet_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_the_layout(1);
                set_the_text_view(1);
                set_up_units_button();
            }
        });
        button_to_change_height_in_button_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_the_layout(2);
                set_the_text_view(2);
            }
        });
        change_weight_for_sheet_weight_tracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_the_layout(3);
                set_the_text_view(3);
            }
        });
        change_goal_in_bottom_sheet_weight_tracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_the_layout(4);
                set_the_text_view(4);
            }
        });
        view_history_button_in_weight_tracekr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getActivity()!=null) {
                    Dialog_view_history_for_weight_tracker dialog_view_history_for_weight_tracker = new Dialog_view_history_for_weight_tracker();
                    dialog_view_history_for_weight_tracker.show(getActivity().getSupportFragmentManager(), "history_tag");
                    dismiss();
                }
            }
        });
    }

    private void make_back_button_change() {
        Button button_to_go_back_counter = mview.findViewById(R.id.button_to_go_back_counter);
        final EditText enter_weight_or_height_full_edit_text_in_sheet = mview.findViewById(R.id.enter_weight_or_height_full_edit_text_in_sheet);
        final EditText edit_text_for_height_foot = mview.findViewById(R.id.edit_text_for_height_foot);
        final EditText edit_text_for_height_inch = mview.findViewById(R.id.edit_text_for_height_inch);
        button_to_go_back_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view_for_keyboard = null;
                if(enter_weight_or_height_full_edit_text_in_sheet.hasFocus()){
                    view_for_keyboard = enter_weight_or_height_full_edit_text_in_sheet;
                } else if(edit_text_for_height_foot.hasFocus()){
                    view_for_keyboard = edit_text_for_height_foot;
                } else if (edit_text_for_height_inch.hasFocus()){
                    view_for_keyboard = edit_text_for_height_inch;
                }
                if (view_for_keyboard != null) {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view_for_keyboard.getWindowToken(), 0);
                }
                change_the_layout(0);
                set_the_text_view(0);
            }
        });
    }

    private void change_the_layout(int which) {
        Button button_to_change_units_bottom_sheet_start = mview.findViewById(R.id.button_to_change_units_bottom_sheet_start);
        View horizantal_straight_line_1 = mview.findViewById(R.id.horizantal_straight_line_1);
        Button button_to_change_height_in_button_sheet = mview.findViewById(R.id.button_to_change_height_in_button_sheet);
        View horizantal_straight_line_2 = mview.findViewById(R.id.horizantal_straight_line_2);
        Button change_weight_for_sheet_weight_tracker = mview.findViewById(R.id.change_weight_for_sheet_weight_tracker);
        View horizantal_straight_line_3 = mview.findViewById(R.id.horizantal_straight_line_3);
        Button change_goal_in_bottom_sheet_weight_tracker = mview.findViewById(R.id.change_goal_in_bottom_sheet_weight_tracker);
        TextView text_in_top_of_add_entry_bottom_sheet = mview.findViewById(R.id.text_in_top_of_add_entry_bottom_sheet);
        Button choose_unit_for_weight_metric = mview.findViewById(R.id.choose_unit_for_weight_metric);
        Button choose_unit_for_weight_imperial = mview.findViewById(R.id.choose_unit_for_weight_imperial);
        EditText enter_weight_or_height_full_edit_text_in_sheet = mview.findViewById(R.id.enter_weight_or_height_full_edit_text_in_sheet);
        ConstraintLayout layout_to_show_imperial_system_height_in_bottom_sheet = mview.findViewById(R.id.layout_to_show_imperial_system_height_in_bottom_sheet);
        Button button_ok_in_add_entry_sheet = mview.findViewById(R.id.button_ok_in_add_entry_sheet);
        View back_button_in_the_journal_emergency = mview.findViewById(R.id.back_button_in_the_journal_emergency);
        Button button_to_go_back_counter = mview.findViewById(R.id.button_to_go_back_counter);
        View horizantal_straight_line_before_view_history = mview.findViewById(R.id.horizantal_straight_line_before_view_history);
        Button view_history_button_in_weight_tracekr = mview.findViewById(R.id.view_history_button_in_weight_tracekr);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("weight_emergency", getContext().MODE_PRIVATE);
        String units = sharedPreferences.getString("units", "metric");
        if (which == 0) {
            button_to_change_units_bottom_sheet_start.setVisibility(View.VISIBLE);
            horizantal_straight_line_1.setVisibility(View.VISIBLE);
            button_to_change_height_in_button_sheet.setVisibility(View.VISIBLE);
            horizantal_straight_line_2.setVisibility(View.VISIBLE);
            change_weight_for_sheet_weight_tracker.setVisibility(View.VISIBLE);
            horizantal_straight_line_3.setVisibility(View.VISIBLE);
            change_goal_in_bottom_sheet_weight_tracker.setVisibility(View.VISIBLE);
            horizantal_straight_line_before_view_history.setVisibility(View.VISIBLE);
            view_history_button_in_weight_tracekr.setVisibility(View.VISIBLE);

            text_in_top_of_add_entry_bottom_sheet.setVisibility(View.INVISIBLE);

            choose_unit_for_weight_metric.setVisibility(View.INVISIBLE);
            choose_unit_for_weight_imperial.setVisibility(View.INVISIBLE);

            enter_weight_or_height_full_edit_text_in_sheet.setVisibility(View.INVISIBLE);

            layout_to_show_imperial_system_height_in_bottom_sheet.setVisibility(View.INVISIBLE);

            button_ok_in_add_entry_sheet.setVisibility(View.INVISIBLE);
            back_button_in_the_journal_emergency.setVisibility(View.INVISIBLE);
            button_to_go_back_counter.setVisibility(View.INVISIBLE);
        } else if (which == 1) {
            button_to_change_units_bottom_sheet_start.setVisibility(View.INVISIBLE);
            horizantal_straight_line_1.setVisibility(View.INVISIBLE);
            button_to_change_height_in_button_sheet.setVisibility(View.INVISIBLE);
            horizantal_straight_line_2.setVisibility(View.INVISIBLE);
            change_weight_for_sheet_weight_tracker.setVisibility(View.INVISIBLE);
            horizantal_straight_line_3.setVisibility(View.INVISIBLE);
            change_goal_in_bottom_sheet_weight_tracker.setVisibility(View.INVISIBLE);
            horizantal_straight_line_before_view_history.setVisibility(View.INVISIBLE);
            view_history_button_in_weight_tracekr.setVisibility(View.GONE);

            text_in_top_of_add_entry_bottom_sheet.setVisibility(View.VISIBLE);

            choose_unit_for_weight_metric.setVisibility(View.VISIBLE);
            choose_unit_for_weight_imperial.setVisibility(View.VISIBLE);

            enter_weight_or_height_full_edit_text_in_sheet.setVisibility(View.INVISIBLE);

            layout_to_show_imperial_system_height_in_bottom_sheet.setVisibility(View.INVISIBLE);

            button_ok_in_add_entry_sheet.setVisibility(View.VISIBLE);
            back_button_in_the_journal_emergency.setVisibility(View.VISIBLE);
            button_to_go_back_counter.setVisibility(View.VISIBLE);
        } else if (which == 2) {
            button_to_change_units_bottom_sheet_start.setVisibility(View.INVISIBLE);
            horizantal_straight_line_1.setVisibility(View.INVISIBLE);
            button_to_change_height_in_button_sheet.setVisibility(View.INVISIBLE);
            horizantal_straight_line_2.setVisibility(View.INVISIBLE);
            change_weight_for_sheet_weight_tracker.setVisibility(View.INVISIBLE);
            horizantal_straight_line_3.setVisibility(View.INVISIBLE);
            change_goal_in_bottom_sheet_weight_tracker.setVisibility(View.INVISIBLE);
            horizantal_straight_line_before_view_history.setVisibility(View.INVISIBLE);
            view_history_button_in_weight_tracekr.setVisibility(View.GONE);

            text_in_top_of_add_entry_bottom_sheet.setVisibility(View.VISIBLE);

            choose_unit_for_weight_metric.setVisibility(View.INVISIBLE);
            choose_unit_for_weight_imperial.setVisibility(View.INVISIBLE);
            if (units != null) {
                if (units.equals("metric")) {
                    enter_weight_or_height_full_edit_text_in_sheet.setVisibility(View.VISIBLE);
                    enter_weight_or_height_full_edit_text_in_sheet.setHint("height in cm");
                } else {
                    layout_to_show_imperial_system_height_in_bottom_sheet.setVisibility(View.VISIBLE);
                }
            }
            button_ok_in_add_entry_sheet.setVisibility(View.VISIBLE);
            back_button_in_the_journal_emergency.setVisibility(View.VISIBLE);
            button_to_go_back_counter.setVisibility(View.VISIBLE);
        } else if (which == 3 || which == 4) {
            button_to_change_units_bottom_sheet_start.setVisibility(View.INVISIBLE);
            horizantal_straight_line_1.setVisibility(View.INVISIBLE);
            button_to_change_height_in_button_sheet.setVisibility(View.INVISIBLE);
            horizantal_straight_line_2.setVisibility(View.INVISIBLE);
            change_weight_for_sheet_weight_tracker.setVisibility(View.INVISIBLE);
            horizantal_straight_line_3.setVisibility(View.INVISIBLE);
            change_goal_in_bottom_sheet_weight_tracker.setVisibility(View.INVISIBLE);
            horizantal_straight_line_before_view_history.setVisibility(View.INVISIBLE);
            view_history_button_in_weight_tracekr.setVisibility(View.GONE);

            text_in_top_of_add_entry_bottom_sheet.setVisibility(View.VISIBLE);

            choose_unit_for_weight_metric.setVisibility(View.INVISIBLE);
            choose_unit_for_weight_imperial.setVisibility(View.INVISIBLE);

            enter_weight_or_height_full_edit_text_in_sheet.setVisibility(View.VISIBLE);
            if (units != null) {
                if (units.equals("metric")) {
                    enter_weight_or_height_full_edit_text_in_sheet.setHint("weight in kilos");
                } else {
                    enter_weight_or_height_full_edit_text_in_sheet.setHint("weight in pounds");
                }
            }

            layout_to_show_imperial_system_height_in_bottom_sheet.setVisibility(View.INVISIBLE);

            button_ok_in_add_entry_sheet.setVisibility(View.VISIBLE);
            back_button_in_the_journal_emergency.setVisibility(View.VISIBLE);
            button_to_go_back_counter.setVisibility(View.VISIBLE);
        }
    }

    private void set_the_text_view(int which) {
        TextView text_in_top_of_add_entry_bottom_sheet = mview.findViewById(R.id.text_in_top_of_add_entry_bottom_sheet);
        if (which == 0) {
            text_in_top_of_add_entry_bottom_sheet.setText("");
        } else if (which == 1) {
            text_in_top_of_add_entry_bottom_sheet.setText("Change Weight Units");
        } else if (which == 2) {
            text_in_top_of_add_entry_bottom_sheet.setText("Change Height");
        } else if (which == 3) {
            text_in_top_of_add_entry_bottom_sheet.setText("Change Start Weight");
        } else if (which == 4) {
            text_in_top_of_add_entry_bottom_sheet.setText("Change Goal");
        }
    }

    private void set_up_units_button() {
        if (getActivity() != null && getContext() != null) {
            final Button choose_unit_for_weight_metric = mview.findViewById(R.id.choose_unit_for_weight_metric);
            final Button choose_unit_for_weight_imperial = mview.findViewById(R.id.choose_unit_for_weight_imperial);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("weight_emergency", getContext().MODE_PRIVATE);
            String units = sharedPreferences.getString("units", "metric");
            if (units != null) {
                if (units.equals("metric")) {
                    choose_unit_for_weight_metric.setTextColor(Color.WHITE);
                    choose_unit_for_weight_metric.setBackgroundResource(R.drawable.color_for_botton_on);
                    choose_unit_for_weight_imperial.setTextColor(Color.parseColor("#607D8B"));
                    choose_unit_for_weight_imperial.setBackgroundResource(R.drawable.color_for_botton_off);
                } else {
                    choose_unit_for_weight_imperial.setTextColor(Color.WHITE);
                    choose_unit_for_weight_imperial.setBackgroundResource(R.drawable.color_for_botton_on);
                    choose_unit_for_weight_metric.setTextColor(Color.parseColor("#607D8B"));
                    choose_unit_for_weight_metric.setBackgroundResource(R.drawable.color_for_botton_off);
                }
            }
            choose_unit_for_weight_metric.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    choose_unit_for_weight_metric.setTextColor(Color.WHITE);
                    choose_unit_for_weight_metric.setBackgroundResource(R.drawable.color_for_botton_on);
                    choose_unit_for_weight_imperial.setTextColor(Color.parseColor("#607D8B"));
                    choose_unit_for_weight_imperial.setBackgroundResource(R.drawable.color_for_botton_off);
                }
            });
            choose_unit_for_weight_imperial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    choose_unit_for_weight_imperial.setTextColor(Color.WHITE);
                    choose_unit_for_weight_imperial.setBackgroundResource(R.drawable.color_for_botton_on);
                    choose_unit_for_weight_metric.setTextColor(Color.parseColor("#607D8B"));
                    choose_unit_for_weight_metric.setBackgroundResource(R.drawable.color_for_botton_off);
                }
            });
        }
    }

    private void ok_button_listen() {
        if (getActivity() != null && getContext() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("weight_emergency", getContext().MODE_PRIVATE);
            final SharedPreferences.Editor myEdit = sharedPreferences.edit();
            Button button_to_change_units_bottom_sheet_start = mview.findViewById(R.id.button_to_change_units_bottom_sheet_start);
            Button button_to_change_height_in_button_sheet = mview.findViewById(R.id.button_to_change_height_in_button_sheet);
            Button change_weight_for_sheet_weight_tracker = mview.findViewById(R.id.change_weight_for_sheet_weight_tracker);
            Button change_goal_in_bottom_sheet_weight_tracker = mview.findViewById(R.id.change_goal_in_bottom_sheet_weight_tracker);
            final TextView text_in_top_of_add_entry_bottom_sheet = mview.findViewById(R.id.text_in_top_of_add_entry_bottom_sheet);
            final Button choose_unit_for_weight_metric = mview.findViewById(R.id.choose_unit_for_weight_metric);
            Button choose_unit_for_weight_imperial = mview.findViewById(R.id.choose_unit_for_weight_imperial);
            final EditText enter_weight_or_height_full_edit_text_in_sheet = mview.findViewById(R.id.enter_weight_or_height_full_edit_text_in_sheet);
            final ConstraintLayout layout_to_show_imperial_system_height_in_bottom_sheet = mview.findViewById(R.id.layout_to_show_imperial_system_height_in_bottom_sheet);
            Button button_ok_in_add_entry_sheet = mview.findViewById(R.id.button_ok_in_add_entry_sheet);
            View back_button_in_the_journal_emergency = mview.findViewById(R.id.back_button_in_the_journal_emergency);
            Button button_to_go_back_counter = mview.findViewById(R.id.button_to_go_back_counter);
            final EditText edit_text_for_height_foot = mview.findViewById(R.id.edit_text_for_height_foot);
            final EditText edit_text_for_height_inch = mview.findViewById(R.id.edit_text_for_height_inch);
            button_ok_in_add_entry_sheet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (text_in_top_of_add_entry_bottom_sheet.getText().toString().equals("Change Weight Units")) {
                        if (choose_unit_for_weight_metric.getCurrentTextColor() == Color.WHITE) {
                            myEdit.putString("units", "metric");
                            myEdit.commit();
                            listener.on_dailog_close_weight();
                            dismiss();
                        } else {
                            myEdit.putString("units", "imperial");
                            myEdit.commit();
                            listener.on_dailog_close_weight();
                            dismiss();
                        }
                    } else if (text_in_top_of_add_entry_bottom_sheet.getText().toString().equals("Change Height")) {
                        if (layout_to_show_imperial_system_height_in_bottom_sheet.getVisibility() == View.VISIBLE) {
                            if (!edit_text_for_height_foot.getText().toString().equals("") && !edit_text_for_height_foot.getText().toString().equals("0") && !edit_text_for_height_inch.getText().toString().equals("")) {
                                int inches = Integer.parseInt(edit_text_for_height_foot.getText().toString()) * 12 + Integer.parseInt(edit_text_for_height_inch.getText().toString());
                                myEdit.putFloat("height_start", inches * 2.54f);
                                myEdit.commit();
                                listener.on_dailog_close_weight();
                                dismiss();
                            } else {
                                if (edit_text_for_height_foot.getText().toString().equals("")) {
                                    edit_text_for_height_foot.setError("foot cant be empty");
                                } else if (edit_text_for_height_foot.getText().toString().equals("0")) {
                                    edit_text_for_height_foot.setError("inch cant be zero");
                                } else if (edit_text_for_height_inch.getText().toString().equals("")) {
                                    edit_text_for_height_foot.setError("inch cant be empty");
                                }
                            }
                        } else {
                            if (!enter_weight_or_height_full_edit_text_in_sheet.getText().toString().equals("") && !enter_weight_or_height_full_edit_text_in_sheet.getText().toString().equals("0")) {
                                myEdit.putFloat("height_start", Float.parseFloat(enter_weight_or_height_full_edit_text_in_sheet.getText().toString()));
                                myEdit.commit();
                                listener.on_dailog_close_weight();
                                dismiss();
                            } else {
                                if (enter_weight_or_height_full_edit_text_in_sheet.getText().toString().equals("")) {
                                    enter_weight_or_height_full_edit_text_in_sheet.setError("Height can't be empty");
                                } else if (enter_weight_or_height_full_edit_text_in_sheet.getText().toString().equals("0")) {
                                    enter_weight_or_height_full_edit_text_in_sheet.setError("Height can't be zero");
                                }
                            }
                        }
                    } else if (text_in_top_of_add_entry_bottom_sheet.getText().toString().equals("Change Start Weight")) {
                        if (enter_weight_or_height_full_edit_text_in_sheet.getHint().equals("weight in kilos")) {
                            if (!enter_weight_or_height_full_edit_text_in_sheet.getText().toString().equals("") && !enter_weight_or_height_full_edit_text_in_sheet.getText().toString().equals("0")) {
                                myEdit.putFloat("start_weight", Float.parseFloat(enter_weight_or_height_full_edit_text_in_sheet.getText().toString()));
                                myEdit.commit();
                                listener.on_dailog_close_weight();
                                dismiss();
                            } else {
                                if (enter_weight_or_height_full_edit_text_in_sheet.getText().toString().equals("")) {
                                    enter_weight_or_height_full_edit_text_in_sheet.setError("Weight can't be empty");
                                } else if (enter_weight_or_height_full_edit_text_in_sheet.getText().toString().equals("0")) {
                                    enter_weight_or_height_full_edit_text_in_sheet.setError("Weight can't be zero");
                                }
                            }
                        } else if (enter_weight_or_height_full_edit_text_in_sheet.getHint().equals("weight in pounds")) {
                            if (!enter_weight_or_height_full_edit_text_in_sheet.getText().toString().equals("") && !enter_weight_or_height_full_edit_text_in_sheet.getText().toString().equals("0")) {
                                myEdit.putFloat("start_weight", Float.parseFloat(enter_weight_or_height_full_edit_text_in_sheet.getText().toString()) / 2.205f);
                                myEdit.commit();
                                listener.on_dailog_close_weight();
                                dismiss();
                            } else {
                                if (enter_weight_or_height_full_edit_text_in_sheet.getText().toString().equals("")) {
                                    enter_weight_or_height_full_edit_text_in_sheet.setError("Weight can't be empty");
                                } else if (enter_weight_or_height_full_edit_text_in_sheet.getText().toString().equals("0")) {
                                    enter_weight_or_height_full_edit_text_in_sheet.setError("Weight can't be zero");
                                }
                            }
                        }
                    } else if (text_in_top_of_add_entry_bottom_sheet.getText().toString().equals("Change Goal")) {
                        if (enter_weight_or_height_full_edit_text_in_sheet.getHint().equals("weight in kilos")) {
                            if (!enter_weight_or_height_full_edit_text_in_sheet.getText().toString().equals("") && !enter_weight_or_height_full_edit_text_in_sheet.getText().toString().equals("0")) {
                                myEdit.putFloat("goal_start", Float.parseFloat(enter_weight_or_height_full_edit_text_in_sheet.getText().toString()));
                                myEdit.commit();
                                listener.on_dailog_close_weight();
                                dismiss();
                            } else {
                                if (enter_weight_or_height_full_edit_text_in_sheet.getText().toString().equals("")) {
                                    enter_weight_or_height_full_edit_text_in_sheet.setError("Weight can't be empty");
                                } else if (enter_weight_or_height_full_edit_text_in_sheet.getText().toString().equals("0")) {
                                    enter_weight_or_height_full_edit_text_in_sheet.setError("Weight can't be zero");
                                }
                            }
                        } else if (enter_weight_or_height_full_edit_text_in_sheet.getHint().equals("weight in pounds")) {
                            if (!enter_weight_or_height_full_edit_text_in_sheet.getText().toString().equals("") && !enter_weight_or_height_full_edit_text_in_sheet.getText().toString().equals("0")) {
                                myEdit.putFloat("goal_start", Float.parseFloat(enter_weight_or_height_full_edit_text_in_sheet.getText().toString()) / 2.205f);
                                myEdit.commit();
                                listener.on_dailog_close_weight();
                                dismiss();
                            } else {
                                if (enter_weight_or_height_full_edit_text_in_sheet.getText().toString().equals("")) {
                                    enter_weight_or_height_full_edit_text_in_sheet.setError("Weight can't be empty");
                                } else if (enter_weight_or_height_full_edit_text_in_sheet.getText().toString().equals("0")) {
                                    enter_weight_or_height_full_edit_text_in_sheet.setError("Weight can't be zero");
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (BottomSheetListener_for_changing_start_weight_info) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement bottomsheetlistener");
        }
    }
}
