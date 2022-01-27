package com.easyhabitsapp.android;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class My_view_holder extends RecyclerView.ViewHolder {
    TextView sample_row_recycler_text_view;
    ImageView image_view_showing_app_icon;
    CheckBox checkBox_for_recycle_view_sample;


    public My_view_holder(View item_view) {
        super(item_view);
        sample_row_recycler_text_view = item_view.findViewById(R.id.sample_row_recycler_text_view);
        image_view_showing_app_icon = item_view.findViewById(R.id.image_view_showing_app_icon);
        checkBox_for_recycle_view_sample = item_view.findViewById(R.id.checkBox_for_recycle_view_sample);
    }
}
