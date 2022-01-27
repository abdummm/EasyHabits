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

public class Bottom_sheet_three_dots extends BottomSheetDialogFragment {
    private BottomSheetListener_for_three_dots listener;
    public interface BottomSheetListener_for_three_dots{
        void onButtonChoose(String text);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_for_3_dots,container,false);
        Button select_all_button = view.findViewById(R.id.select_all_button);
        Button copy_text_only_button = view.findViewById(R.id.copy_text_only_button);
        Button add_another_journal_button = view.findViewById(R.id.add_another_journal_button);
        Button delete_journal_button = view.findViewById(R.id.delete_journal_button);
        Button switch_journal_button = view.findViewById(R.id.switch_journal_button);
        View delete_icon_in_bottom_journal = view.findViewById(R.id.delete_icon_in_bottom_journal);
        View switch_icon_in_bottom_journal = view.findViewById(R.id.switch_icon_in_bottom_journal);
        View horizantal_straight_line_4 = view.findViewById(R.id.horizantal_straight_line_4);
        if(getTag()!=null) {
            if (getTag().equals("three_dots")) {
                select_all_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onButtonChoose("select_all");
                        dismiss();
                    }
                });
                copy_text_only_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onButtonChoose("copy_all");
                        dismiss();
                    }
                });
                add_another_journal_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onButtonChoose("add_another_journal");
                        dismiss();
                    }
                });
                delete_journal_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onButtonChoose("delete_journal");
                        dismiss();
                    }
                });
                switch_journal_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onButtonChoose("switch_journal");
                        dismiss();
                    }
                });
            } else {
                delete_journal_button.setVisibility(View.GONE);
                delete_icon_in_bottom_journal.setVisibility(View.GONE);
                switch_journal_button.setVisibility(View.GONE);
                switch_icon_in_bottom_journal.setVisibility(View.GONE);
                horizantal_straight_line_4.setVisibility(View.GONE);
                select_all_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onButtonChoose("select_all");
                        dismiss();
                    }
                });
                copy_text_only_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onButtonChoose("copy_all");
                        dismiss();
                    }
                });
                add_another_journal_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onButtonChoose("add_another_journal");
                        dismiss();
                    }
                });
            }
        } else {
            delete_journal_button.setVisibility(View.GONE);
            delete_icon_in_bottom_journal.setVisibility(View.GONE);
            switch_journal_button.setVisibility(View.GONE);
            switch_icon_in_bottom_journal.setVisibility(View.GONE);
            horizantal_straight_line_4.setVisibility(View.GONE);
            select_all_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onButtonChoose("select_all");
                    dismiss();
                }
            });
            copy_text_only_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onButtonChoose("copy_all");
                    dismiss();
                }
            });
            add_another_journal_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onButtonChoose("add_another_journal");
                    dismiss();
                }
            });
        }
        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (BottomSheetListener_for_three_dots) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement bottomsheetlistener");
        }
    }
}
