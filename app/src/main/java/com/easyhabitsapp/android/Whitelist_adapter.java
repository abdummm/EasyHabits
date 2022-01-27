package com.easyhabitsapp.android;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Whitelist_adapter extends RecyclerView.Adapter<My_view_holder> {

    Context context;
    List<String> list;
    List<Drawable> image_list;
    List<String> checkbox_list_for_apps;

    public Whitelist_adapter(Context context, List<String> list, List<Drawable> image_list, List<String> checkbox_list_for_apps) {
        this.list = list;
        this.context = context;
        this.image_list = image_list;
        this.checkbox_list_for_apps = checkbox_list_for_apps;
    }

    @NotNull
    @Override
    public My_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_row_recycle_view, parent, false);
        return new My_view_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My_view_holder holder, final int position) {
        String[] splitter = list.get(position).split("_split_here_");
        holder.sample_row_recycler_text_view.setText(splitter[0]);
        holder.image_view_showing_app_icon.setImageDrawable(image_list.get(position));
        holder.checkBox_for_recycle_view_sample.setOnCheckedChangeListener(null);
        if (checkbox_list_for_apps.contains(list.get(position))) {
            holder.checkBox_for_recycle_view_sample.setChecked(true);
        } else {
            holder.checkBox_for_recycle_view_sample.setChecked(false);
        }
        holder.checkBox_for_recycle_view_sample.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkbox_list_for_apps.add(list.get(position));
                }else {
                    checkbox_list_for_apps.remove(list.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
