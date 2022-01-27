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

public class Dialog_to_ask_if_user_wants_to_give_this_award extends DialogFragment {
    private View mview;
    private int award;
    private String post_comment_or_reply;
    private give_award_listen give_award_listen_listenenr;

    public Dialog_to_ask_if_user_wants_to_give_this_award(int award, String post_comment_or_reply){
        this.award = award;
        this.post_comment_or_reply = post_comment_or_reply;
    }

    public void set_give_award_clicked_listen(give_award_listen listen) {
        give_award_listen_listenenr = listen;
    }

    public interface give_award_listen {
        void give_award_clicked();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_to_ask_award, container);
        this.mview = rootView;
        ok_button_listen();
        cancel_purchase();
        set_awawrd_and_text();
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
        Button ok_for_permission = mview.findViewById(R.id.ok_for_permission);
        ok_for_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                give_award_listen_listenenr.give_award_clicked();
                dismiss();
            }
        });
    }

    private void cancel_purchase(){
        Button cancel_for_permission = mview.findViewById(R.id.cancel_for_permission);
        cancel_for_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }


    private void set_awawrd_and_text(){
        TextView text_saying_gift_is_sent = mview.findViewById(R.id.text_saying_gift_is_sent);
        View show_the_award_image = mview.findViewById(R.id.show_the_award_image);
        if(award == 0){
            show_the_award_image.setBackgroundResource(R.drawable.silver_award_png);
            if(post_comment_or_reply.equals("post")){
                text_saying_gift_is_sent.setText("Are you sure you want to give this post a silver award?");
            } else if(post_comment_or_reply.equals("comment")){
                text_saying_gift_is_sent.setText("Are you sure you want to give this comment a silver award?");
            } else if(post_comment_or_reply.equals("reply")){
                text_saying_gift_is_sent.setText("Are you sure you want to give this reply a silver award?");
            }
        }else if(award == 1){
            show_the_award_image.setBackgroundResource(R.drawable.gold_award_png);
            if(post_comment_or_reply.equals("post")){
                text_saying_gift_is_sent.setText("Are you sure you want to give this post a gold award?");
            } else if(post_comment_or_reply.equals("comment")){
                text_saying_gift_is_sent.setText("Are you sure you want to give this comment a gold award?");
            } else if(post_comment_or_reply.equals("reply")){
                text_saying_gift_is_sent.setText("Are you sure you want to give this reply a gold award?");
            }
        } else if(award == 2){
            show_the_award_image.setBackgroundResource(R.drawable.ic_plat_award_posts);
            if(post_comment_or_reply.equals("post")){
                text_saying_gift_is_sent.setText("Are you sure you want to give this post a platinum award?");
            } else if(post_comment_or_reply.equals("comment")){
                text_saying_gift_is_sent.setText("Are you sure you want to give this comment a platinum award?");
            } else if(post_comment_or_reply.equals("reply")){
                text_saying_gift_is_sent.setText("Are you sure you want to give this reply a platinum award?");
            }
        }
    }
}
