package com.easyhabitsapp.android;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Bottom_sheet_to_change_color extends BottomSheetDialogFragment {
    private BottomSheetListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enter_color_for_journal_bottom_sheet,container,false);
        Button teal_color_circle_under_bottom = view.findViewById(R.id.teal_color_circle_under_bottom);
        Button red_color_circle_under_bottom = view.findViewById(R.id.red_color_circle_under_bottom);
        Button green_color_circle_under_bottom = view.findViewById(R.id.green_color_circle_under_bottom);
        Button blue_color_circle_under_bottom = view.findViewById(R.id.blue_color_circle_under_bottom);
        Button orange_color_circle_under_bottom = view.findViewById(R.id.orange_color_circle_under_bottom);
        Button navy_color_circle_under_bottom = view.findViewById(R.id.navy_color_circle_under_bottom);
        Button black_color_circle_under_bottom = view.findViewById(R.id.black_color_circle_under_bottom);
        teal_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.on_color_choose("teal");
                dismiss();
            }
        });
        red_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.on_color_choose("red");
                dismiss();
            }
        });
        green_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.on_color_choose("green");
                dismiss();
            }
        });
        blue_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.on_color_choose("blue");
                dismiss();
            }
        });
        orange_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.on_color_choose("orange");
                dismiss();
            }
        });
        navy_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.on_color_choose("navy");
                dismiss();
            }
        });
        black_color_circle_under_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.on_color_choose("black");
                dismiss();
            }
        });
        return view;
    }
    public interface BottomSheetListener{
        void on_color_choose(String text);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (BottomSheetListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement bottomsheetlistener");
        }
    }
}
