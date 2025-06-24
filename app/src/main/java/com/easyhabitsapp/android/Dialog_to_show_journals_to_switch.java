package com.easyhabitsapp.android;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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

public class Dialog_to_show_journals_to_switch extends DialogFragment {
    private View mview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_to_show_journals_to_switch, container);
        this.mview = rootView;
        if (getDialog() != null) {
            getDialog().setCanceledOnTouchOutside(false);
            getDialog().setCancelable(false);
        }
        set_up_the_recycle();
        button_cancel_listen();
        return rootView;
    }
    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getActivity() != null && getDialog() != null && getDialog().getWindow() != null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = (displayMetrics.heightPixels / 100) * 90;
            int width = (displayMetrics.widthPixels / 100) * 90;
            getDialog().getWindow().setLayout(width, height);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    private void set_up_the_recycle(){
        final ArrayList<Example_item_for_journal> example_list = new ArrayList<>();
        RecyclerView recycle_for_showing_journals_to_switch = mview.findViewById(R.id.recycle_for_showing_journals_to_switch);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        Journal_adapter_switch adapter_switch = new Journal_adapter_switch(example_list);
        String[] split_big = read_the_data().replace("@#$$#@i_am_the_last_edit_for_journal@#$$#@", "").split("##&&&&max_divide&&&&##");
        for(int i = 0;i<split_big.length;i++){
            String[] split_small = split_big[i].split("!!@@##%%&&split_small!!@@##%%&&",-1);
            String put_title;
            String put_body;
            put_title = split_small[0];
            put_body = split_small[1];
            example_list.add(new Example_item_for_journal(put_title,put_body));
        }
        recycle_for_showing_journals_to_switch.setHasFixedSize(true);
        recycle_for_showing_journals_to_switch.setLayoutManager(linearLayoutManager);
        recycle_for_showing_journals_to_switch.setAdapter(adapter_switch);
        adapter_switch.setOnItemClickListener(new Journal_adapter_switch.OnItemCLickListener() {
            @Override
            public void on_click(int position) {
                on_card_chosen(position);
                remove_dialog();
            }
        });
    }
    private String read_the_data() {
        if (getContext() != null) {
            SharedPreferences shared = getContext().getSharedPreferences("_all_the_journal", getContext().MODE_PRIVATE);
            String old_string = shared.getString("journal", "");
            if (old_string!=null){
                return old_string;
            } else {
                return "";
            }
        } else {
            return "";
        }
    }
    private void button_cancel_listen(){
        Button cancel_button_for_white_list_dialog = mview.findViewById(R.id.cancel_button_for_white_list_dialog);
        cancel_button_for_white_list_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove_dialog();
            }
        });
    }
    private void on_card_chosen(int card) {
        if (getActivity() != null) {
            String old_string = read_the_data().replace("@#$$#@i_am_the_last_edit_for_journal@#$$#@", "");
            String[] splitter = old_string.split("##&&&&max_divide&&&&##");
            splitter[card] = splitter[card].concat("@#$$#@i_am_the_last_edit_for_journal@#$$#@");
            String save_me = "";
            for (int i = 0; i < splitter.length; i++) {
                save_me = save_me.concat(splitter[i]).concat("##&&&&max_divide&&&&##");
            }
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("_all_the_journal", getContext().MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("journal", save_me);
            myEdit.commit();
        }
    }
    private void remove_dialog(){
        if(getDialog()!=null){
            getDialog().dismiss();
        } else {
            dismiss();
        }
    }
}
