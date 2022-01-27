package com.easyhabitsapp.android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class Recycle_view_good_reminder_adapter extends RecyclerView.Adapter<Recycle_view_good_reminder_adapter.Example_holder> {
    private ArrayList<Recycle_view_good_reminder_item> m_example_holder;
    private OnItemClickListener mlistener;
    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }
    public void setOnItemClick_listener(OnItemClickListener listener) {
        mlistener = listener;
    }
    public static class Example_holder extends RecyclerView.ViewHolder {
        public TextView time;
        public TextView day;
        public Button delete;

        public Example_holder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            time = itemView.findViewById(R.id.showing_the_date_in_the_start);
            day = itemView.findViewById(R.id.text_beside_circle_color_in_show_event);
            delete = itemView.findViewById(R.id.button_shadow_for_the_trash);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public Example_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_sample_good_habits, parent, false);
        Example_holder example_holder = new Example_holder(view, mlistener);
        return example_holder;
    }

    public Recycle_view_good_reminder_adapter(ArrayList<Recycle_view_good_reminder_item> example_list) {
        m_example_holder = example_list;
    }

    @Override
    public void onBindViewHolder(@NonNull Example_holder holder, int position) {
        Recycle_view_good_reminder_item current_item = m_example_holder.get(position);
        holder.day.setText(current_item.get_the_date());
        holder.time.setText(current_item.get_the_time());


    }

    @Override
    public int getItemCount() {
        return m_example_holder.size();
    }
}
