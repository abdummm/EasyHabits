package com.easyhabitsapp.android;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

import java.util.ArrayList;

public class Bottom_sheet_dialog_choose_color extends BottomSheetDialogFragment {

    public View mview;
    private String habit_color;
    private color_chosen color_chosen_listen;

    public Bottom_sheet_dialog_choose_color(String habit_color){
        this.habit_color = habit_color;
    }

    public void set_done_clicked_edit(color_chosen listen) {
        color_chosen_listen = listen;
    }

    public interface color_chosen {
        void color_got_chosen(String color);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_color_bottom_sheet, container, false);
        mview = view;
        listen_to_dismiss_click();
        listen_to_buttons_click();
        select_the_selected_color();
        return view;
    }

    private void listen_to_buttons_click() {
        View gray_view_selected_number_1 = mview.findViewById(R.id.gray_view_selected_number_1);
        View check_mark_selected_number_1 = mview.findViewById(R.id.check_mark_selected_number_1);
        View gray_view_selected_number_2 = mview.findViewById(R.id.gray_view_selected_number_2);
        View check_mark_selected_number_2 = mview.findViewById(R.id.check_mark_selected_number_2);
        View gray_view_selected_number_3 = mview.findViewById(R.id.gray_view_selected_number_3);
        View check_mark_selected_number_3 = mview.findViewById(R.id.check_mark_selected_number_3);
        View gray_view_selected_number_4 = mview.findViewById(R.id.gray_view_selected_number_4);
        View check_mark_selected_number_4 = mview.findViewById(R.id.check_mark_selected_number_4);
        View gray_view_selected_number_5 = mview.findViewById(R.id.gray_view_selected_number_5);
        View check_mark_selected_number_5 = mview.findViewById(R.id.check_mark_selected_number_5);
        View gray_view_selected_number_6 = mview.findViewById(R.id.gray_view_selected_number_6);
        View check_mark_selected_number_6 = mview.findViewById(R.id.check_mark_selected_number_6);
        View gray_view_selected_number_7 = mview.findViewById(R.id.gray_view_selected_number_7);
        View check_mark_selected_number_7 = mview.findViewById(R.id.check_mark_selected_number_7);
        View gray_view_selected_number_8 = mview.findViewById(R.id.gray_view_selected_number_8);
        View check_mark_selected_number_8 = mview.findViewById(R.id.check_mark_selected_number_8);
        View gray_view_selected_number_9 = mview.findViewById(R.id.gray_view_selected_number_9);
        View check_mark_selected_number_9 = mview.findViewById(R.id.check_mark_selected_number_9);
        Button teal_color_circle_under_bottom = mview.findViewById(R.id.teal_color_circle_under_bottom);
        Button red_color_circle_under_bottom = mview.findViewById(R.id.red_color_circle_under_bottom);
        Button green_color_circle_under_bottom = mview.findViewById(R.id.green_color_circle_under_bottom);
        Button blue_color_circle_under_bottom = mview.findViewById(R.id.blue_color_circle_under_bottom);
        Button orange_color_circle_under_bottom = mview.findViewById(R.id.orange_color_circle_under_bottom);
        Button brown_color_circle_under_bottom = mview.findViewById(R.id.brown_color_circle_under_bottom);
        Button black_color_circle_under_bottom = mview.findViewById(R.id.black_color_circle_under_bottom);
        Button cyan_color_circle_under_bottom = mview.findViewById(R.id.cyan_color_circle_under_bottom);
        Button lime_color_circle_under_bottom = mview.findViewById(R.id.lime_color_circle_under_bottom);
        Button magenta_color_circle_under_bottom = mview.findViewById(R.id.magenta_color_circle_under_bottom);
        Button navy_color_circle_under_bottom = mview.findViewById(R.id.navy_color_circle_under_bottom);
        Button pink_color_circle_under_bottom = mview.findViewById(R.id.pink_color_circle_under_bottom);
        Button yellow_color_circle_under_bottom = mview.findViewById(R.id.yellow_color_circle_under_bottom);
        Button purple_color_circle_under_bottom = mview.findViewById(R.id.purple_color_circle_under_bottom);
        View gray_view_selected_number_10 = mview.findViewById(R.id.gray_view_selected_number_10);
        View check_mark_selected_number_10 = mview.findViewById(R.id.check_mark_selected_number_10);
        View gray_view_selected_number_11 = mview.findViewById(R.id.gray_view_selected_number_11);
        View check_mark_selected_number_11 = mview.findViewById(R.id.check_mark_selected_number_11);
        View gray_view_selected_number_12 = mview.findViewById(R.id.gray_view_selected_number_12);
        View check_mark_selected_number_12 = mview.findViewById(R.id.check_mark_selected_number_12);
        View gray_view_selected_number_13 = mview.findViewById(R.id.gray_view_selected_number_13);
        View check_mark_selected_number_13 = mview.findViewById(R.id.check_mark_selected_number_13);
        View gray_view_selected_number_14 = mview.findViewById(R.id.gray_view_selected_number_14);
        View check_mark_selected_number_14 = mview.findViewById(R.id.check_mark_selected_number_14);
        teal_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_all_check_marks();
                gray_view_selected_number_1.setVisibility(View.VISIBLE);
                check_mark_selected_number_1.setVisibility(View.VISIBLE);
                habit_color = "#ed94c9";
                color_chosen_listen.color_got_chosen(habit_color);
                dismiss();
            }
        });
        red_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_all_check_marks();
                gray_view_selected_number_2.setVisibility(View.VISIBLE);
                check_mark_selected_number_2.setVisibility(View.VISIBLE);
                habit_color = "#e095ea";
                color_chosen_listen.color_got_chosen(habit_color);
                dismiss();
            }
        });
        green_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_all_check_marks();
                gray_view_selected_number_3.setVisibility(View.VISIBLE);
                check_mark_selected_number_3.setVisibility(View.VISIBLE);
                habit_color = "#ec8aad";
                color_chosen_listen.color_got_chosen(habit_color);
                dismiss();

            }
        });
        blue_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_all_check_marks();
                gray_view_selected_number_4.setVisibility(View.VISIBLE);
                check_mark_selected_number_4.setVisibility(View.VISIBLE);
                habit_color = "#ea867c";
                color_chosen_listen.color_got_chosen(habit_color);
                dismiss();

            }
        });
        orange_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_all_check_marks();
                gray_view_selected_number_5.setVisibility(View.VISIBLE);
                check_mark_selected_number_5.setVisibility(View.VISIBLE);
                habit_color = "#e7aa6d";
                color_chosen_listen.color_got_chosen(habit_color);
                dismiss();

            }
        });
        brown_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_all_check_marks();
                gray_view_selected_number_6.setVisibility(View.VISIBLE);
                check_mark_selected_number_6.setVisibility(View.VISIBLE);
                habit_color = "#ddc652";
                color_chosen_listen.color_got_chosen(habit_color);
                dismiss();

            }
        });
        black_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_all_check_marks();
                gray_view_selected_number_7.setVisibility(View.VISIBLE);
                check_mark_selected_number_7.setVisibility(View.VISIBLE);
                habit_color = "#b3cd5f";
                color_chosen_listen.color_got_chosen(habit_color);
                dismiss();

            }
        });
        cyan_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_all_check_marks();
                gray_view_selected_number_8.setVisibility(View.VISIBLE);
                check_mark_selected_number_8.setVisibility(View.VISIBLE);
                habit_color = "#7fc5a0";
                color_chosen_listen.color_got_chosen(habit_color);
                dismiss();

            }
        });
        lime_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_all_check_marks();
                gray_view_selected_number_9.setVisibility(View.VISIBLE);
                check_mark_selected_number_9.setVisibility(View.VISIBLE);
                habit_color = "#6ecabd";
                color_chosen_listen.color_got_chosen(habit_color);
                dismiss();

            }
        });
        magenta_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_all_check_marks();
                gray_view_selected_number_10.setVisibility(View.VISIBLE);
                check_mark_selected_number_10.setVisibility(View.VISIBLE);
                habit_color = "#77cebb";
                color_chosen_listen.color_got_chosen(habit_color);
                dismiss();

            }
        });
        navy_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_all_check_marks();
                gray_view_selected_number_11.setVisibility(View.VISIBLE);
                check_mark_selected_number_11.setVisibility(View.VISIBLE);
                habit_color = "#6bb6db";
                color_chosen_listen.color_got_chosen(habit_color);
                dismiss();

            }
        });
        pink_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_all_check_marks();
                gray_view_selected_number_12.setVisibility(View.VISIBLE);
                check_mark_selected_number_12.setVisibility(View.VISIBLE);
                habit_color = "#7ea8e5";
                color_chosen_listen.color_got_chosen(habit_color);
                dismiss();

            }
        });
        yellow_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_all_check_marks();
                gray_view_selected_number_13.setVisibility(View.VISIBLE);
                check_mark_selected_number_13.setVisibility(View.VISIBLE);
                habit_color = "#888ce3";
                color_chosen_listen.color_got_chosen(habit_color);
                dismiss();

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
                                    color_chosen_listen.color_got_chosen(habit_color);
                                    dismiss();

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

    private void listen_to_dismiss_click() {
        Button ok_bottom_sheet_add_reminder = mview.findViewById(R.id.ok_bottom_sheet_add_reminder);
        ok_bottom_sheet_add_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void clear_all_check_marks() {
        if (mview != null) {
            View gray_view_selected_number_1 = mview.findViewById(R.id.gray_view_selected_number_1);
            View check_mark_selected_number_1 = mview.findViewById(R.id.check_mark_selected_number_1);
            View gray_view_selected_number_2 = mview.findViewById(R.id.gray_view_selected_number_2);
            View check_mark_selected_number_2 = mview.findViewById(R.id.check_mark_selected_number_2);
            View gray_view_selected_number_3 = mview.findViewById(R.id.gray_view_selected_number_3);
            View check_mark_selected_number_3 = mview.findViewById(R.id.check_mark_selected_number_3);
            View gray_view_selected_number_4 = mview.findViewById(R.id.gray_view_selected_number_4);
            View check_mark_selected_number_4 = mview.findViewById(R.id.check_mark_selected_number_4);
            View gray_view_selected_number_5 = mview.findViewById(R.id.gray_view_selected_number_5);
            View check_mark_selected_number_5 = mview.findViewById(R.id.check_mark_selected_number_5);
            View gray_view_selected_number_6 = mview.findViewById(R.id.gray_view_selected_number_6);
            View check_mark_selected_number_6 = mview.findViewById(R.id.check_mark_selected_number_6);
            View gray_view_selected_number_7 = mview.findViewById(R.id.gray_view_selected_number_7);
            View check_mark_selected_number_7 = mview.findViewById(R.id.check_mark_selected_number_7);
            View gray_view_selected_number_8 = mview.findViewById(R.id.gray_view_selected_number_8);
            View check_mark_selected_number_8 = mview.findViewById(R.id.check_mark_selected_number_8);
            View gray_view_selected_number_9 = mview.findViewById(R.id.gray_view_selected_number_9);
            View check_mark_selected_number_9 = mview.findViewById(R.id.check_mark_selected_number_9);
            View gray_view_selected_number_10 = mview.findViewById(R.id.gray_view_selected_number_10);
            View check_mark_selected_number_10 = mview.findViewById(R.id.check_mark_selected_number_10);
            View gray_view_selected_number_11 = mview.findViewById(R.id.gray_view_selected_number_11);
            View check_mark_selected_number_11 = mview.findViewById(R.id.check_mark_selected_number_11);
            View gray_view_selected_number_12 = mview.findViewById(R.id.gray_view_selected_number_12);
            View check_mark_selected_number_12 = mview.findViewById(R.id.check_mark_selected_number_12);
            View gray_view_selected_number_13 = mview.findViewById(R.id.gray_view_selected_number_13);
            View check_mark_selected_number_13 = mview.findViewById(R.id.check_mark_selected_number_13);
            View gray_view_selected_number_14 = mview.findViewById(R.id.gray_view_selected_number_14);
            View check_mark_selected_number_14 = mview.findViewById(R.id.check_mark_selected_number_14);
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

    private void select_the_selected_color() {
            View gray_view_selected_number_1 = mview.findViewById(R.id.gray_view_selected_number_1);
            View check_mark_selected_number_1 = mview.findViewById(R.id.check_mark_selected_number_1);
            View gray_view_selected_number_2 = mview.findViewById(R.id.gray_view_selected_number_2);
            View check_mark_selected_number_2 = mview.findViewById(R.id.check_mark_selected_number_2);
            View gray_view_selected_number_3 = mview.findViewById(R.id.gray_view_selected_number_3);
            View check_mark_selected_number_3 = mview.findViewById(R.id.check_mark_selected_number_3);
            View gray_view_selected_number_4 = mview.findViewById(R.id.gray_view_selected_number_4);
            View check_mark_selected_number_4 = mview.findViewById(R.id.check_mark_selected_number_4);
            View gray_view_selected_number_5 = mview.findViewById(R.id.gray_view_selected_number_5);
            View check_mark_selected_number_5 = mview.findViewById(R.id.check_mark_selected_number_5);
            View gray_view_selected_number_6 = mview.findViewById(R.id.gray_view_selected_number_6);
            View check_mark_selected_number_6 = mview.findViewById(R.id.check_mark_selected_number_6);
            View gray_view_selected_number_7 = mview.findViewById(R.id.gray_view_selected_number_7);
            View check_mark_selected_number_7 = mview.findViewById(R.id.check_mark_selected_number_7);
            View gray_view_selected_number_8 = mview.findViewById(R.id.gray_view_selected_number_8);
            View check_mark_selected_number_8 = mview.findViewById(R.id.check_mark_selected_number_8);
            View gray_view_selected_number_9 = mview.findViewById(R.id.gray_view_selected_number_9);
            View check_mark_selected_number_9 = mview.findViewById(R.id.check_mark_selected_number_9);
            View gray_view_selected_number_10 = mview.findViewById(R.id.gray_view_selected_number_10);
            View check_mark_selected_number_10 = mview.findViewById(R.id.check_mark_selected_number_10);
            View gray_view_selected_number_11 = mview.findViewById(R.id.gray_view_selected_number_11);
            View check_mark_selected_number_11 = mview.findViewById(R.id.check_mark_selected_number_11);
            View gray_view_selected_number_12 = mview.findViewById(R.id.gray_view_selected_number_12);
            View check_mark_selected_number_12 = mview.findViewById(R.id.check_mark_selected_number_12);
            View gray_view_selected_number_13 = mview.findViewById(R.id.gray_view_selected_number_13);
            View check_mark_selected_number_13 = mview.findViewById(R.id.check_mark_selected_number_13);
            View gray_view_selected_number_14 = mview.findViewById(R.id.gray_view_selected_number_14);
            View check_mark_selected_number_14 = mview.findViewById(R.id.check_mark_selected_number_14);
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
