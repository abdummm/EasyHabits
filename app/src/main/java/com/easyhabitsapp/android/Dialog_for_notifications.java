package com.easyhabitsapp.android;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Dialog_for_notifications extends DialogFragment {
    private View mview;
    private ok_button_clicked ok_button_clicked_listener;
    private cancel_button_clicked cancel_button_clicked_listener;

    public void set_ok_button_clicked(ok_button_clicked listener) {
        ok_button_clicked_listener = listener;
    }

    public interface ok_button_clicked {
        void ok_button_was_clicked();
    }

    public void set_cancel_button_clicked(cancel_button_clicked listener) {
        cancel_button_clicked_listener = listener;
    }

    public interface cancel_button_clicked {
        void cancel_button_was_clicked();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_to_ask_for_notification_permission, container);
        this.mview = rootView;
        ok_button_listen();
        cancel_button_listen();
        if (getDialog() != null) {
            getDialog().setCancelable(false);
            getDialog().setCanceledOnTouchOutside(false);
        }
        return rootView;
    }

    @Override
    public void onResume() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getActivity() != null && getDialog().getWindow() != null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = (displayMetrics.widthPixels / 100) * 90;
            //int height = (displayMetrics.heightPixels / 100) * 40;
            getDialog().getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        super.onResume();
    }

    private void ok_button_listen() {
        Button ok_for_dismiss_notifications_dialog = mview.findViewById(R.id.ok_for_dismiss_notifications_dialog);
        ok_for_dismiss_notifications_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ok_button_clicked_listener.ok_button_was_clicked();
                dismiss();
            }
        });
    }

    private void cancel_button_listen() {
        Button cancel_for_dismiss_notifications_dialog = mview.findViewById(R.id.cancel_for_dismiss_notifications_dialog);
        cancel_for_dismiss_notifications_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               cancel_button_clicked_listener.cancel_button_was_clicked();
               dismiss();
            }
        });
    }

}
