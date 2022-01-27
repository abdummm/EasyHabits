package com.easyhabitsapp.android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_pick_icons extends RecyclerView.Adapter<Adapter_pick_icons.ExampleViewHolder> {
    private ArrayList<Example_item_for_icons> m_example_list;
    private button_in_icons_got_clicked button_in_icons_got_clicked_listen;

    @NonNull
    @Override
    public Adapter_pick_icons.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.icons_layout_recycle_view, parent, false);
        Adapter_pick_icons.ExampleViewHolder exampleViewHolder = new Adapter_pick_icons.ExampleViewHolder(view);
        return exampleViewHolder;
    }

    public Adapter_pick_icons(ArrayList<Example_item_for_icons> Example_item_for_icons) {
        m_example_list = Example_item_for_icons;
    }

    public void set_share_clicked_listen(button_in_icons_got_clicked listen) {
        button_in_icons_got_clicked_listen = listen;
    }

    public interface button_in_icons_got_clicked {
        void share_just_got_clciked(int text);
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter_pick_icons.ExampleViewHolder holder, final int position) {
        final Example_item_for_icons current_item = m_example_list.get(position);
        holder.view_1_to_show_good_icon_recycle_view.setBackgroundResource(current_item.getFirst_id());
        holder.text_1_for_good_habits_recycle_view.setText(current_item.getFirst_text());
        holder.first_button_in_list_to_choose_good_habit_icon_recycle_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_in_icons_got_clicked_listen.share_just_got_clciked(current_item.getFirst_id());
            }
        });

        if (current_item.getSecond_text().equals("null")) {
            holder.second_button_in_list_to_choose_good_habit_icon_recycle_view.setVisibility(View.INVISIBLE);
            holder.view_2_to_show_good_icon_recycle_view.setVisibility(View.INVISIBLE);
            holder.text_2_for_good_habits_recycle_view.setVisibility(View.INVISIBLE);
        } else {
            holder.second_button_in_list_to_choose_good_habit_icon_recycle_view.setVisibility(View.VISIBLE);
            holder.view_2_to_show_good_icon_recycle_view.setVisibility(View.VISIBLE);
            holder.text_2_for_good_habits_recycle_view.setVisibility(View.VISIBLE);

            holder.view_2_to_show_good_icon_recycle_view.setBackgroundResource(current_item.getSecond_id());
            holder.text_2_for_good_habits_recycle_view.setText(current_item.getSecond_text());
            holder.second_button_in_list_to_choose_good_habit_icon_recycle_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button_in_icons_got_clicked_listen.share_just_got_clciked(current_item.getSecond_id());
                }
            });
        }
        if (current_item.getThird_text().equals("null")) {
            holder.third_button_in_list_to_choose_good_habit_icon_recycle_view.setVisibility(View.INVISIBLE);
            holder.view_3_to_show_good_icon_recycle_view.setVisibility(View.INVISIBLE);
            holder.text_3_for_good_habits_recycle_view.setVisibility(View.INVISIBLE);
        } else {
            holder.third_button_in_list_to_choose_good_habit_icon_recycle_view.setVisibility(View.VISIBLE);
            holder.view_3_to_show_good_icon_recycle_view.setVisibility(View.VISIBLE);
            holder.text_3_for_good_habits_recycle_view.setVisibility(View.VISIBLE);

            holder.view_3_to_show_good_icon_recycle_view.setBackgroundResource(current_item.getThird_id());
            holder.text_3_for_good_habits_recycle_view.setText(current_item.getThird_text());
            holder.third_button_in_list_to_choose_good_habit_icon_recycle_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button_in_icons_got_clicked_listen.share_just_got_clciked(current_item.getThird_id());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return m_example_list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public View view_1_to_show_good_icon_recycle_view;
        public View view_2_to_show_good_icon_recycle_view;
        public View view_3_to_show_good_icon_recycle_view;
        public TextView text_1_for_good_habits_recycle_view;
        public TextView text_2_for_good_habits_recycle_view;
        public TextView text_3_for_good_habits_recycle_view;
        public Button first_button_in_list_to_choose_good_habit_icon_recycle_view;
        public Button second_button_in_list_to_choose_good_habit_icon_recycle_view;
        public Button third_button_in_list_to_choose_good_habit_icon_recycle_view;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            view_1_to_show_good_icon_recycle_view = itemView.findViewById(R.id.view_1_to_show_good_icon_recycle_view);
            view_2_to_show_good_icon_recycle_view = itemView.findViewById(R.id.view_2_to_show_good_icon_recycle_view);
            view_3_to_show_good_icon_recycle_view = itemView.findViewById(R.id.view_3_to_show_good_icon_recycle_view);
            text_1_for_good_habits_recycle_view = itemView.findViewById(R.id.text_1_for_good_habits_recycle_view);
            text_2_for_good_habits_recycle_view = itemView.findViewById(R.id.text_2_for_good_habits_recycle_view);
            text_3_for_good_habits_recycle_view = itemView.findViewById(R.id.text_3_for_good_habits_recycle_view);
            first_button_in_list_to_choose_good_habit_icon_recycle_view = itemView.findViewById(R.id.first_button_in_list_to_choose_good_habit_icon_recycle_view);
            second_button_in_list_to_choose_good_habit_icon_recycle_view = itemView.findViewById(R.id.second_button_in_list_to_choose_good_habit_icon_recycle_view);
            third_button_in_list_to_choose_good_habit_icon_recycle_view = itemView.findViewById(R.id.third_button_in_list_to_choose_good_habit_icon_recycle_view);
        }
    }
}
