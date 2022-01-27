package com.easyhabitsapp.android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recycle_view_view_history_adapter extends RecyclerView.Adapter<Recycle_view_view_history_adapter.View_history_recycle_holder> {
    private ArrayList<Recycle_view_item_view_history> m_recycle_view_item_view_history;
    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public static class View_history_recycle_holder extends RecyclerView.ViewHolder {
        public TextView text_beside_circle_color_in_show_event;
        public TextView showing_the_date_in_the_start;
        public Button button_shadow_for_the_edit;
        public Button button_shadow_for_the_trash; // button names are reverse
        public View_history_recycle_holder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            text_beside_circle_color_in_show_event = itemView.findViewById(R.id.text_beside_circle_color_in_show_event);
            showing_the_date_in_the_start = itemView.findViewById(R.id.showing_the_date_in_the_start);
            button_shadow_for_the_edit = itemView.findViewById(R.id.button_shadow_for_the_edit);
            button_shadow_for_the_trash = itemView.findViewById(R.id.button_shadow_for_the_trash);

            button_shadow_for_the_trash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEditClick(position);
                        }
                    }
                }
            });
            button_shadow_for_the_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public Recycle_view_view_history_adapter(ArrayList<Recycle_view_item_view_history> example_list) {
        m_recycle_view_item_view_history = example_list;
    }
    @NonNull
    @Override
    public View_history_recycle_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_smaple_for_history_weight,parent,false);
        View_history_recycle_holder view_history_recycle_holder = new View_history_recycle_holder(view,mListener);
        return view_history_recycle_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull View_history_recycle_holder holder, int position) {
        Recycle_view_item_view_history current_item = m_recycle_view_item_view_history.get(position);

        holder.showing_the_date_in_the_start.setText(current_item.get_the_date());
        holder.text_beside_circle_color_in_show_event.setText(current_item.get_the_weight());
    }

    @Override
    public int getItemCount() {
        return m_recycle_view_item_view_history.size();
    }
}
