package com.easyhabitsapp.android;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Dialog_to_tell_user_too_many_sign_ups extends DialogFragment {
    private View mview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_to_tell_too_many_sign_ups, container);
        this.mview = rootView;
        setTitle();
        set_body();
        ok_button_listen();
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

    private void setTitle(){
        TextView title_of_dialog_custom = mview.findViewById(R.id.title_of_dialog_custom);
        title_of_dialog_custom.setText("Can't sync account");
    }

    private void set_body(){
        TextView body_of_dialog_custom = mview.findViewById(R.id.body_of_dialog_custom);
        body_of_dialog_custom.setText("It appears that there has been to many sign ups using the same account. To prevent fraud, we are not able to sync premium features to your account right now and we will try again in 24 hours. If you feel this is a mistake, please don't hesitate to contact us ;)");
    }

    private void ok_button_listen(){
        Button ok_for_dismiss_custom_dialog = mview.findViewById(R.id.ok_for_dismiss_custom_dialog);
        CheckBox check_box_saying_never_show_this_again_too_many_sign_ups = mview.findViewById(R.id.check_box_saying_never_show_this_again_too_many_sign_ups);
        ok_for_dismiss_custom_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check_box_saying_never_show_this_again_too_many_sign_ups.isChecked()){
                    save_dont_remind_me_again();
                }
                dismiss();
            }
        });
    }

    private void save_dont_remind_me_again(){
        Save_and_get.getInstance().save_this(mview.getContext(),System.currentTimeMillis(),"dont_remind_me_24h_too_many_sign_ups");
    }

}
