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

public class Bottom_sheet_counter extends BottomSheetDialogFragment {
    private BottomSheetListener_for_counter listener;

    public interface BottomSheetListener_for_counter {
        void on_button_choose_counter(String text);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_coutner, container, false);
        Button button_to_add_another_counter = view.findViewById(R.id.button_to_add_another_counter);
        Button button_to_delete_counter = view.findViewById(R.id.button_to_delete_counter);
        Button button_to_switch_counter = view.findViewById(R.id.button_to_switch_counter);
        View add_another_journal_icon_in_bottom_journal = view.findViewById(R.id.add_another_journal_icon_in_bottom_journal);
        View delete_icon_in_bottom_journal = view.findViewById(R.id.delete_icon_in_bottom_journal);
        View switch_icon_in_bottom_journal = view.findViewById(R.id.switch_icon_in_bottom_journal);
        View horizantal_straight_line_1 = view.findViewById(R.id.horizantal_straight_line_1);
        View horizantal_straight_line_2 = view.findViewById(R.id.horizantal_straight_line_2);
        button_to_add_another_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.on_button_choose_counter("add_counter");
                dismiss();
            }
        });
        if (getTag() != null) {
            if (!getTag().equals("only_one")) {
                button_to_delete_counter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.on_button_choose_counter("delete_counter");
                        dismiss();
                    }
                });
                button_to_switch_counter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.on_button_choose_counter("switch_counter");
                        dismiss();
                    }
                });
            } else {
                button_to_delete_counter.setVisibility(View.GONE);
                button_to_switch_counter.setVisibility(View.GONE);
                horizantal_straight_line_1.setVisibility(View.GONE);
                horizantal_straight_line_2.setVisibility(View.GONE);
                delete_icon_in_bottom_journal.setVisibility(View.GONE);
                switch_icon_in_bottom_journal.setVisibility(View.GONE);
                add_another_journal_icon_in_bottom_journal.setVisibility(View.GONE);
            }
        } else {
            button_to_delete_counter.setVisibility(View.GONE);
            button_to_switch_counter.setVisibility(View.GONE);
            horizantal_straight_line_1.setVisibility(View.GONE);
            horizantal_straight_line_2.setVisibility(View.GONE);
            delete_icon_in_bottom_journal.setVisibility(View.GONE);
            switch_icon_in_bottom_journal.setVisibility(View.GONE);
            add_another_journal_icon_in_bottom_journal.setVisibility(View.GONE);
        }
        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (BottomSheetListener_for_counter) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement bottomsheetlistener");
        }
    }
}
