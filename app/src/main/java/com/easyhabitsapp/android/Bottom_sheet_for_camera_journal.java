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

public class Bottom_sheet_for_camera_journal extends BottomSheetDialogFragment {
    private BottomSheetListener_for_camera listener;
    public interface BottomSheetListener_for_camera{
        void onButtonChoose_camera(String text);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_for_camera,container,false);
        Button take_photo_for_journal_button = view.findViewById(R.id.take_photo_for_journal_button);
        Button import_galley_button_journal = view.findViewById(R.id.import_galley_button_journal);
        take_photo_for_journal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onButtonChoose_camera("take_photo");
                dismiss();
            }
        });
        import_galley_button_journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onButtonChoose_camera("import_photo");
                dismiss();
            }
        });
        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (BottomSheetListener_for_camera) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement bottomsheetlistener");
        }
    }
}
