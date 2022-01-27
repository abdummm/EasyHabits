package com.easyhabitsapp.android;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Dialog_for_location_permission extends DialogFragment {
    private View mview;
    private Dialog_for_location_permission_listener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_to_ask_for_location, container);
        this.mview = rootView;
        ok_for_permission_button();
        cancel_for_permission_button();
        return rootView;
    }

    @Override
    public void onResume() {
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        super.onResume();
    }

    private void cancel_for_permission_button() {
        Button cancel_for_permission = mview.findViewById(R.id.cancel_for_permission);
        cancel_for_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onComplete("cancel");
                if (getDialog() != null) {
                    getDialog().dismiss();
                } else {
                    dismiss();
                }
            }
        });
    }

    private void ok_for_permission_button() {
        Button ok_for_permission = mview.findViewById(R.id.ok_for_permission);
        ok_for_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onComplete("ok");
                if (getDialog() != null) {
                    getDialog().dismiss();
                } else {
                    dismiss();
                }
            }
        });
    }

    public interface Dialog_for_location_permission_listener {
        void onComplete(String state);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (Dialog_for_location_permission_listener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }
}
