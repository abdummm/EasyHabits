package com.easyhabitsapp.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Bottom_sheet_choose_sort_method extends BottomSheetDialogFragment {

    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_to_choose_sorting_method_posts, container, false);
        mView = view;
        button_lsiten();
        set_title();
        return view;
    }

    private void button_lsiten() {
        Button saying_sort_by_hot_in_bottom_sheet = mView.findViewById(R.id.saying_sort_by_hot_in_bottom_sheet);
        Button saying_sort_by_top_in_bottom_sheet = mView.findViewById(R.id.saying_sort_by_top_in_bottom_sheet);
        Button saying_sort_by_new_in_bottom_sheet = mView.findViewById(R.id.saying_sort_by_new_in_bottom_sheet);
        Button back_button_lsiten_in_bottom_sheet_to_sort = mView.findViewById(R.id.back_button_lsiten_in_bottom_sheet_to_sort);
        Button saying_sort_i_want_top_this_day = mView.findViewById(R.id.saying_sort_i_want_top_this_day);
        Button saying_sort_i_want_top_this_week = mView.findViewById(R.id.saying_sort_i_want_top_this_week);
        Button saying_sort_i_want_top_this_month = mView.findViewById(R.id.saying_sort_i_want_top_this_month);
        saying_sort_by_hot_in_bottom_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent().putExtra("sort", "hot");
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                dismiss();
            }
        });
        saying_sort_by_top_in_bottom_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if(get_me_tag().equals("post")){
                    change_visible(2);
                } else {
                    Intent i = new Intent().putExtra("sort", "top");
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                    dismiss();
                }*/
                Intent i = new Intent().putExtra("sort", "top");
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                dismiss();
            }
        });
        saying_sort_by_new_in_bottom_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent().putExtra("sort", "new");
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                dismiss();
            }
        });
        back_button_lsiten_in_bottom_sheet_to_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_visible(1);
            }
        });
        saying_sort_i_want_top_this_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent().putExtra("sort", "top_day");
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                dismiss();
            }
        });
        saying_sort_i_want_top_this_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent().putExtra("sort", "top_week");
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                dismiss();
            }
        });
        saying_sort_i_want_top_this_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent().putExtra("sort", "top_month");
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                dismiss();
            }
        });
    }

    private void change_visible(int which) {
        TextView text_at_the_top_of_the_bottom_saying_sort_by_what = mView.findViewById(R.id.text_at_the_top_of_the_bottom_saying_sort_by_what);
        Button saying_sort_by_hot_in_bottom_sheet = mView.findViewById(R.id.saying_sort_by_hot_in_bottom_sheet);
        Button saying_sort_by_top_in_bottom_sheet = mView.findViewById(R.id.saying_sort_by_top_in_bottom_sheet);
        Button saying_sort_by_new_in_bottom_sheet = mView.findViewById(R.id.saying_sort_by_new_in_bottom_sheet);
        View fire_in_the_hot_button_sort = mView.findViewById(R.id.fire_in_the_hot_button_sort);
        View fire_in_the_top_button_sort = mView.findViewById(R.id.fire_in_the_top_button_sort);
        View fire_in_the_new_button_sort = mView.findViewById(R.id.fire_in_the_new_button_sort);
        View back_button_in_bottom_sheet_listen = mView.findViewById(R.id.back_button_in_bottom_sheet_listen);
        Button back_button_lsiten_in_bottom_sheet_to_sort = mView.findViewById(R.id.back_button_lsiten_in_bottom_sheet_to_sort);
        Button saying_sort_i_want_top_this_day = mView.findViewById(R.id.saying_sort_i_want_top_this_day);
        Button saying_sort_i_want_top_this_week = mView.findViewById(R.id.saying_sort_i_want_top_this_week);
        Button saying_sort_i_want_top_this_month = mView.findViewById(R.id.saying_sort_i_want_top_this_month);
        if (which == 1) {
            text_at_the_top_of_the_bottom_saying_sort_by_what.setText("Sort Posts By");
            saying_sort_by_hot_in_bottom_sheet.setVisibility(View.VISIBLE);
            saying_sort_by_top_in_bottom_sheet.setVisibility(View.VISIBLE);
            saying_sort_by_new_in_bottom_sheet.setVisibility(View.VISIBLE);
            fire_in_the_hot_button_sort.setVisibility(View.VISIBLE);
            fire_in_the_top_button_sort.setVisibility(View.VISIBLE);
            fire_in_the_new_button_sort.setVisibility(View.VISIBLE);
            back_button_in_bottom_sheet_listen.setVisibility(View.INVISIBLE);
            back_button_lsiten_in_bottom_sheet_to_sort.setVisibility(View.INVISIBLE);
            saying_sort_i_want_top_this_day.setVisibility(View.INVISIBLE);
            saying_sort_i_want_top_this_week.setVisibility(View.INVISIBLE);
            saying_sort_i_want_top_this_month.setVisibility(View.INVISIBLE);
        } else {
            text_at_the_top_of_the_bottom_saying_sort_by_what.setText("View Top Posts of");
            saying_sort_by_hot_in_bottom_sheet.setVisibility(View.INVISIBLE);
            saying_sort_by_top_in_bottom_sheet.setVisibility(View.INVISIBLE);
            saying_sort_by_new_in_bottom_sheet.setVisibility(View.INVISIBLE);
            fire_in_the_hot_button_sort.setVisibility(View.INVISIBLE);
            fire_in_the_top_button_sort.setVisibility(View.INVISIBLE);
            fire_in_the_new_button_sort.setVisibility(View.INVISIBLE);
            back_button_in_bottom_sheet_listen.setVisibility(View.VISIBLE);
            back_button_lsiten_in_bottom_sheet_to_sort.setVisibility(View.VISIBLE);
            saying_sort_i_want_top_this_day.setVisibility(View.VISIBLE);
            saying_sort_i_want_top_this_week.setVisibility(View.VISIBLE);
            saying_sort_i_want_top_this_month.setVisibility(View.VISIBLE);
        }
    }
    private void set_title(){
        TextView text_at_the_top_of_the_bottom_saying_sort_by_what = mView.findViewById(R.id.text_at_the_top_of_the_bottom_saying_sort_by_what);
        if(get_me_tag().equals("comment")){
            text_at_the_top_of_the_bottom_saying_sort_by_what.setText("Sort Comments by");
        }
    }
    private String get_me_tag(){
        if(getTag()!=null){
            return getTag();
        } else {
            return "post";
        }
    }
}
