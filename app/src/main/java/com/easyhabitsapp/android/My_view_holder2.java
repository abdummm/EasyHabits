package com.easyhabitsapp.android;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class My_view_holder2 extends RecyclerView.ViewHolder {
    TextView sample_row_recycler_text_view_for_apps_for_apps;
    ImageView image_view_showing_app_icon_for_apps;


    public My_view_holder2(View item_view) {
        super(item_view);
        sample_row_recycler_text_view_for_apps_for_apps = item_view.findViewById(R.id.sample_row_recycler_text_view_for_apps_for_apps);
        image_view_showing_app_icon_for_apps = item_view.findViewById(R.id.image_view_showing_app_icon_for_apps);
    }
}
