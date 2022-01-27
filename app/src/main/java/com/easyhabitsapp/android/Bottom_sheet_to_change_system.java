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

public class Bottom_sheet_to_change_system extends BottomSheetDialogFragment {
    private BottomSheetListener_for_system listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_to_choose_system,container,false);
        Button make_metric_system_running = view.findViewById(R.id.make_metric_system_running);
        Button make_imperial_system_running = view.findViewById(R.id.make_imperial_system_running);

        make_metric_system_running.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.on_system_choose("metric");
                dismiss();
            }
        });
        make_imperial_system_running.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.on_system_choose("imperial");
                dismiss();
            }
        });
        return view;
    }
    public interface BottomSheetListener_for_system{
        void on_system_choose(String text);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (BottomSheetListener_for_system) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement bottomsheetlistener");
        }
    }
}
