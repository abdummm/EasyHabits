package com.easyhabitsapp.android;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Dialog_custom extends DialogFragment {
    private View mview;
    private String title;
    private String body;
    private int text_size;
    private boolean allow_close_outside;
    private String mode;

    public Dialog_custom(String title,String body){
        this.title = title;
        this.body = body;
        mode = "simple";
    }

    public Dialog_custom(String title,String body,int text_size,boolean allow_close_outside){
        this.title = title;
        this.body = body;
        this.text_size = text_size;
        this.allow_close_outside = allow_close_outside;
        mode = "complex";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_custom, container);
        this.mview = rootView;
        setTitle();
        set_body();
        ok_button_listen();
        set_text_size();
        if (getDialog() != null) {
            if(mode.equals("simple")) {
                getDialog().setCancelable(true);
                getDialog().setCanceledOnTouchOutside(true);
            } else if(mode.equals("complex")){
                getDialog().setCancelable(allow_close_outside);
                getDialog().setCanceledOnTouchOutside(allow_close_outside);
            }
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

    private void setTitle(){
        TextView title_of_dialog_custom = mview.findViewById(R.id.title_of_dialog_custom);
        title_of_dialog_custom.setText(title);
    }

    private void set_body(){
        TextView body_of_dialog_custom = mview.findViewById(R.id.body_of_dialog_custom);
        body_of_dialog_custom.setText(body);
    }

    private void ok_button_listen(){
        Button ok_for_dismiss_custom_dialog = mview.findViewById(R.id.ok_for_dismiss_custom_dialog);
        ok_for_dismiss_custom_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
    private void set_text_size(){
        if(mode.equals("complex")){
            TextView body_of_dialog_custom = mview.findViewById(R.id.body_of_dialog_custom);
            body_of_dialog_custom.setTextSize(TypedValue.COMPLEX_UNIT_SP, text_size);
        }
    }
}
