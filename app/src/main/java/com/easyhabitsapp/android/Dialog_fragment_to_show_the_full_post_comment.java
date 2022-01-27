package com.easyhabitsapp.android;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Dialog_fragment_to_show_the_full_post_comment extends DialogFragment {
    private View mview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_fragment_to_show_full_comment_post, container);
        this.mview = rootView;
        ok_button();
        set_the_text();
        return rootView;
    }

    @Override
    public void onResume() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getActivity()!=null && getDialog().getWindow()!=null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = (displayMetrics.heightPixels / 100) * 90;
            int width = (displayMetrics.widthPixels / 100) * 90;
            getDialog().getWindow().setLayout(width, height);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        super.onResume();
    }
    private void ok_button(){
        Button ok_for_permission = mview.findViewById(R.id.ok_for_permission);
        ok_for_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
    private void set_the_text() {
        TextView title_for_the_post_or_comment = mview.findViewById(R.id.title_for_the_post_or_comment);
        TextView body_in_dialog_fragment_for_showing_body_of_post = mview.findViewById(R.id.body_in_dialog_fragment_for_showing_body_of_post);
        View view_between_title_and_comment = mview.findViewById(R.id.view_between_title_and_comment);
        TextView title_of_dialog = mview.findViewById(R.id.title_of_dialog);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String title = bundle.getString("title");
            String body = bundle.getString("body");
            if(body.isEmpty()){
                title_for_the_post_or_comment.setText(first_letter_capital(title));
                view_between_title_and_comment.setVisibility(View.GONE);
                body_in_dialog_fragment_for_showing_body_of_post.setVisibility(View.GONE);
                title_of_dialog.setText("Show comment");
            } else {
                title_for_the_post_or_comment.setText(first_letter_capital(title));
                body_in_dialog_fragment_for_showing_body_of_post.setText(first_letter_capital(body));
                title_of_dialog.setText("Show post");
            }
        }
    }
    private String first_letter_capital(String sentence) {
        return sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
    }
}
