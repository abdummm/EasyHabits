/*
package com.easyhabitsapp.android;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
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

public class Bottom_sheet_choose_good_habit_color extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_to_choose_good_habit_color, container, false);
        Button teal_color_circle_under_bottom = view.findViewById(R.id.teal_color_circle_under_bottom);
        Button red_color_circle_under_bottom = view.findViewById(R.id.red_color_circle_under_bottom);
        Button green_color_circle_under_bottom = view.findViewById(R.id.green_color_circle_under_bottom);
        Button blue_color_circle_under_bottom = view.findViewById(R.id.blue_color_circle_under_bottom);
        Button orange_color_circle_under_bottom = view.findViewById(R.id.orange_color_circle_under_bottom);
        Button brown_color_circle_under_bottom = view.findViewById(R.id.brown_color_circle_under_bottom);
        Button black_color_circle_under_bottom = view.findViewById(R.id.black_color_circle_under_bottom);
        Button cyan_color_circle_under_bottom = view.findViewById(R.id.cyan_color_circle_under_bottom);
        Button lime_color_circle_under_bottom = view.findViewById(R.id.lime_color_circle_under_bottom);
        Button magenta_color_circle_under_bottom = view.findViewById(R.id.magenta_color_circle_under_bottom);
        Button navy_color_circle_under_bottom = view.findViewById(R.id.navy_color_circle_under_bottom);
        Button pink_color_circle_under_bottom = view.findViewById(R.id.pink_color_circle_under_bottom);
        Button yellow_color_circle_under_bottom = view.findViewById(R.id.yellow_color_circle_under_bottom);
        Button purple_color_circle_under_bottom = view.findViewById(R.id.purple_color_circle_under_bottom);
        teal_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent()
                        .putExtra("color", "#607D8B");
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                dismiss();
            }
        });
        red_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent()
                        .putExtra("color", "#cc4545");
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                dismiss();
            }
        });
        green_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent()
                        .putExtra("color", "#3c8439");
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                dismiss();
            }
        });
        blue_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent()
                        .putExtra("color", "#4363d8");
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                dismiss();
            }
        });
        orange_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent()
                        .putExtra("color", "#f66b55");
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                dismiss();
            }
        });
        brown_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent()
                        .putExtra("color", "#954ae0");
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                dismiss();
            }
        });
        black_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent()
                        .putExtra("color", "#000000");
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                dismiss();
            }
        });
        cyan_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent()
                        .putExtra("color", "#5757e7");
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                dismiss();
            }
        });
        lime_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent()
                        .putExtra("color", "#2ea8b6");
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                dismiss();
            }
        });
        magenta_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent()
                        .putExtra("color", "#366a9b");
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                dismiss();
            }
        });
        navy_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent()
                        .putExtra("color", "#000075");
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                dismiss();
            }
        });
        pink_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent()
                        .putExtra("color", "#947c72");
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                dismiss();
            }
        });
        yellow_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent()
                        .putExtra("color", "#cbaf60");
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                dismiss();
            }
        });
        purple_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Payment_processer.getInstance().state_of_the_user()){
                    ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(getContext())
                            .setTitle("ColorPicker")
                            .setPreferenceName("MyColorPickerDialog")
                            .setPositiveButton("Ok",
                                    new ColorEnvelopeListener() {
                                        @Override
                                        public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                                            Intent i = new Intent()
                                                    .putExtra("color", "#".concat(envelope.getHexCode()));
                                            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                                            dismiss();
                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
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
                    builder.show(); // shows the dialog
                } else {
                    Buy_premuim buy_premuim = new Buy_premuim("Custom color picker",true,"add a habit");
                    add_a_habit old_fragment = (add_a_habit) getActivity().getSupportFragmentManager().findFragmentByTag("add a habit");
                    if (old_fragment != null) {
                        getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
                    }
                }
            }
        });
        return view;
    }
}
*/
