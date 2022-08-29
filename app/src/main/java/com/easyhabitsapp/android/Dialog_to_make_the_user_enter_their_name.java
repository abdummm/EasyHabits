package com.easyhabitsapp.android;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.DialogFragment;

public class Dialog_to_make_the_user_enter_their_name extends DialogFragment {
    private View mview;
    private name_is_done name_is_done_listen;
    private String text_at_bottom = "";
    private String title = "";

    public void set_card_clicked_reply(name_is_done listen) {
        name_is_done_listen = listen;
    }

    public interface name_is_done {
        void name_is_done();
    }

    public Dialog_to_make_the_user_enter_their_name(String text_at_bottom,String title){
        this.text_at_bottom = text_at_bottom;
        this.title = title;
    }

    public Dialog_to_make_the_user_enter_their_name(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_to_make_the_user_enter_their_name, container);
        this.mview = rootView;
        TextView title_of_dialog = mview.findViewById(R.id.title_of_dialog);
        ok_button_listen();
        cancel_purchase();
        hide_text_at_button();
        if(!title.equals("")){
            title_of_dialog.setText(title);
        }
        put_the_name();
        return rootView;
    }

    @Override
    public void onResume() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getActivity()!=null && getDialog().getWindow()!=null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = (displayMetrics.widthPixels / 100) * 80;
            getDialog().getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        super.onResume();
    }

    private void ok_button_listen(){
        Button ok_to_set_name = mview.findViewById(R.id.ok_to_set_name);
        EditText edit_text_to_enter_your_username = mview.findViewById(R.id.edit_text_to_enter_your_username);
        ok_to_set_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_text_to_enter_your_username.getText().toString().trim().equals("")){
                    Toast.makeText(getContext(),"User name can't be empty",Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("name_online_for_post",MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("name",edit_text_to_enter_your_username.getText().toString().trim());
                    if(!return_the_name().equals("")){
                        myEdit.putLong("last_changed",System.currentTimeMillis());
                    }
                    myEdit.commit();
                    name_is_done_listen.name_is_done();
                    dismiss();
                }
            }
        });
    }

    private void cancel_purchase(){
        Button cancel_for_set_name = mview.findViewById(R.id.cancel_for_set_name);
        cancel_for_set_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
    private void hide_text_at_button(){
        if(!text_at_bottom.equals("")){
            TextView text_saying_gift_is_sent = mview.findViewById(R.id.text_saying_gift_is_sent);
            text_saying_gift_is_sent.setText(text_at_bottom);
            text_saying_gift_is_sent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            /*text_saying_gift_is_sent.setVisibility(View.GONE);
            ConstraintLayout constraintLayout = mview.findViewById(R.id.layout_to_change_name_dialog);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(R.id.ok_to_set_name,ConstraintSet.TOP,R.id.text_saying_gift_is_sent,ConstraintSet.BOTTOM, (int) convertDpToPixel(15, mview.getContext()));
            constraintSet.applyTo(constraintLayout);*/
        }
    }

    public static float convertDpToPixel(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private String return_the_name() {
        if (getActivity() != null) {
            SharedPreferences sh = getActivity().getSharedPreferences("name_online_for_post", MODE_PRIVATE);
            return sh.getString("name", "");
        } else {
            return "Name";
        }
    }

    private void put_the_name(){
        EditText edit_text_to_enter_your_username = mview.findViewById(R.id.edit_text_to_enter_your_username);
        if(!return_the_name().equals("")){
            edit_text_to_enter_your_username.setText(return_the_name());
        }
    }
}
