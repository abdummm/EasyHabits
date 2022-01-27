package com.easyhabitsapp.android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Journal_adapter_switch extends RecyclerView.Adapter<Journal_adapter_switch.Example_holder> {
    private ArrayList<Example_item_for_journal> m_example_holder;
    private OnItemCLickListener mlistener;

    public interface OnItemCLickListener {
        void on_click(int position);
    }

    public void setOnItemClickListener(OnItemCLickListener listener) {
        mlistener = listener;
    }

    public static class Example_holder extends RecyclerView.ViewHolder {
        public TextView title_text_view;
        public TextView body_text_view;

        public Example_holder(@NonNull View itemView, final OnItemCLickListener listener) {
            super(itemView);
            title_text_view = itemView.findViewById(R.id.text_to_show_title_of_journal);
            body_text_view = itemView.findViewById(R.id.text_to_show_body_of_journal);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.on_click(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public Example_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.switch_journal_card_layout, parent, false);
        Example_holder example_holder = new Example_holder(view, mlistener);
        return example_holder;
    }

    public Journal_adapter_switch(ArrayList<Example_item_for_journal> example_list) {
        m_example_holder = example_list;
    }

    @Override
    public void onBindViewHolder(@NonNull Example_holder holder, int position) {
        Example_item_for_journal current_item = m_example_holder.get(position);
        holder.body_text_view.setText(current_item.get_body());
        holder.title_text_view.setText(current_item.get_title());


    }

    @Override
    public int getItemCount() {
        return m_example_holder.size();
    }
}
