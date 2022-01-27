package com.easyhabitsapp.android;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WhiteList_old_apps extends RecyclerView.Adapter<My_view_holder2> {

    Context context;
    List<String> list;
    List<Drawable> image_list;

    public WhiteList_old_apps(Context context, List<String> list, List<Drawable> image_list) {
        this.list = list;
        this.context = context;
        this.image_list = image_list;
    }

    @NotNull
    @Override
    public My_view_holder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.smaple_row_for_previous, parent, false);
        return new My_view_holder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My_view_holder2 holder, final int position) {
        String[] splitter = list.get(position).split("_split_here_");
        holder.sample_row_recycler_text_view_for_apps_for_apps.setText(splitter[0]);
        holder.image_view_showing_app_icon_for_apps.setImageDrawable(image_list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
