package com.easyhabitsapp.android;

import android.app.Activity;
import android.content.Intent;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Dialog_to_view_or_change_reminder extends DialogFragment {
    private View mview;
    private ArrayList<Recycle_view_good_reminder_item> list_for_reminder;
    private String tag_final;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dailog_to_view_or_change_view_reminder, container);
        this.mview = rootView;
        if (getDialog() != null) {
            getDialog().setCanceledOnTouchOutside(false);
            getDialog().setCancelable(false);
        }
        get_tag();
        set_up_recycle();
        ok_button_listen();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getActivity() != null && getDialog() != null && getDialog().getWindow() != null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = (displayMetrics.heightPixels / 100) * 90;
            int width = (displayMetrics.widthPixels / 100) * 90;
            getDialog().getWindow().setLayout(width, height);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    private void set_up_recycle() {
        RecyclerView recycle_view_to_view_or_edit_good_habits = mview.findViewById(R.id.recycle_view_to_view_or_edit_good_habits);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        list_for_reminder = new ArrayList<>();
        String tag = get_tag();
        String[] tag_split = tag.split("big_cut_for_reminder");
        for (int i = 0; i < tag_split.length; i++) {
            String[] small_split = tag_split[i].split("tiny_cut_for_reminder");
            list_for_reminder.add(new Recycle_view_good_reminder_item(small_split[0], put_days(small_split[1])));
        }
        final Recycle_view_good_reminder_adapter adapter = new Recycle_view_good_reminder_adapter(list_for_reminder);
        recycle_view_to_view_or_edit_good_habits.setLayoutManager(linearLayoutManager);
        recycle_view_to_view_or_edit_good_habits.setAdapter(adapter);
        adapter.setOnItemClick_listener(new Recycle_view_good_reminder_adapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                list_for_reminder.remove(position);
                adapter.notifyDataSetChanged();
                remove_from_list(position);
            }
        });
    }

    private String get_tag() {
        String tag = getTag();
        if (tag != null) {
            tag_final = tag;
            return tag;
        } else {
            return "";
        }
    }

    private String put_days(String days) {
        String small_days = "";
        if (days.contains("0123456")) {
            return "Everyday";
        } else {
            if (days.contains("0")) {
                small_days = small_days.concat("Mo., ");
            }
            if (days.contains("1")) {
                small_days = small_days.concat("Tu., ");
            }
            if (days.contains("2")) {
                small_days = small_days.concat("We., ");
            }
            if (days.contains("3")) {
                small_days = small_days.concat("Th., ");
            }
            if (days.contains("4")) {
                small_days = small_days.concat("Fr., ");
            }
            if (days.contains("5")) {
                small_days = small_days.concat("Sa., ");
            }
            if (days.contains("6")) {
                small_days = small_days.concat("Su., ");
            }
        }
        return small_days.substring(0, small_days.length() - 2);

    }

    private void ok_button_listen() {
        Button ok_button_for_white_list_dialog = mview.findViewById(R.id.ok_button_for_white_list_dialog);
        ok_button_for_white_list_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent().putExtra("reminder", tag_final);
                if (getTargetFragment() != null) {
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                }
                dismiss();
            }
        });
    }
    private void remove_from_list(int position){
        String[] split = tag_final.split("big_cut_for_reminder");
        tag_final = "";
        for(int i =0;i<split.length;i++){
            if(i != position ){
                tag_final = tag_final.concat(split[i]).concat("big_cut_for_reminder");
            }
        }
    }
}
