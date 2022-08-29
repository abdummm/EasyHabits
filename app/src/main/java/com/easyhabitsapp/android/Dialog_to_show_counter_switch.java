package com.easyhabitsapp.android;

import android.content.Context;
import android.content.SharedPreferences;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Dialog_to_show_counter_switch extends DialogFragment {
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
        TextView title_of_dialog_white_list = mview.findViewById(R.id.title_of_dialog_white_list);
        title_of_dialog_white_list.setText("Switch Counters");
        button_cancel_listen();
        set_up_the_recycle();
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
    private void set_up_the_recycle() {
        if (getActivity() != null) {
            final ArrayList<Example_item_for_journal> example_list = new ArrayList<>();
            RecyclerView recycle_for_showing_journals_to_switch = mview.findViewById(R.id.recycle_for_showing_journals_to_switch);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            Journal_adapter_switch adapter_switch = new Journal_adapter_switch(example_list);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Counter", Context.MODE_PRIVATE);
            String string = sharedPreferences.getString("counter", "");
            if(string!=null){
                String[] big_split = string.split("__split_counter_split_counter__");
                for (int i=0;i<big_split.length;i++){
                    String[] small_split = big_split[i].split("##split_small_split_small##");
                    String title;
                    if(small_split[0].equals("__##empty_title##__")){
                        title="";
                    } else {
                        title = small_split[0];
                    }
                    example_list.add(new Example_item_for_journal(title,String.valueOf(Integer.parseInt(small_split[1]))));
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
    private void remove_dialog(){
        if(getDialog()!=null){
            getDialog().dismiss();
        } else {
            dismiss();
        }
    }
    private void on_card_chosen(int card) {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Counter", getContext().MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putInt("which_one", card);
            myEdit.commit();
        }
    }
}
