package com.easyhabitsapp.android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Example_adapter_to_show extends RecyclerView.Adapter<Example_adapter_to_show.ExampleViwHolder> {
    private ArrayList<Example_item_for_showing_events> mexample_item;
    private OnItemClickListener mlistener;
    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }
    public void setOnItemClick_listener(OnItemClickListener listener) {
        mlistener = listener;
    }
    public static class ExampleViwHolder extends RecyclerView.ViewHolder {
        public View mcolor_view;
        public TextView mtext_line1;
        public TextView mtext_line2;
        public View mtrash_view;
        public TextView m_time_up;
        public TextView m_am_pm;
        public Button trash_button;
        public ExampleViwHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mcolor_view = itemView.findViewById(R.id.view_to_show_color_in_show_events);
            mtext_line1 = itemView.findViewById(R.id.text_beside_circle_color_in_show_event);
            mtext_line2 = itemView.findViewById(R.id.text_for_description);
            mtrash_view = itemView.findViewById(R.id.showing_the_delete_button);
            m_time_up = itemView.findViewById(R.id.showing_the_hour_and_minutes_and_colors);
            m_am_pm = itemView.findViewById(R.id.showing_am_or_pm_under_time);
            trash_button = itemView.findViewById(R.id.button_shadow_for_the_trash);
            trash_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public Example_adapter_to_show(ArrayList<Example_item_for_showing_events> example_item_for_showing_events) {
        mexample_item = example_item_for_showing_events;
    }

    @NonNull
    @Override
    public ExampleViwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_show_event,parent,false);
        ExampleViwHolder exampleViwHolder = new ExampleViwHolder(view,mlistener);
        return exampleViwHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViwHolder holder, int position) {
        Example_item_for_showing_events current_item = mexample_item.get(position);
        holder.mcolor_view.setBackgroundResource(current_item.getMdrawable_resource());
        holder.mtext_line1.setText(current_item.getMevent_name());
        holder.mtext_line2.setText(current_item.getMevent_description());
        holder.mtrash_view.setBackgroundResource(current_item.getmdrawable_trash_can());
        holder.m_time_up.setText(current_item.getMevent_time());
        holder.m_am_pm.setText(current_item.getMevent_am_or_pm());
    }

    @Override
    public int getItemCount() {
        return mexample_item.size();
    }
}
