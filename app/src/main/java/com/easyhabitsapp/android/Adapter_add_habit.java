package com.easyhabitsapp.android;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vanniktech.emoji.EmojiTextView;

import java.util.ArrayList;

public class Adapter_add_habit extends RecyclerView.Adapter<Adapter_add_habit.ExampleViewHolder>{
    private ArrayList<Example_item_add_new_habit> m_example_list;

    private  Card_clicked_listen Card_clicked_listen_listener;

    public interface Card_clicked_listen {
        void card_just_got_clicked(String name,String color,String emoji);
    }

    public void set_card_clicked_listen(Card_clicked_listen listener){
        Card_clicked_listen_listener = listener;
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

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_new_habit_recycle_view_item, parent, false);
        ExampleViewHolder exampleViewHolder = new ExampleViewHolder(view);
        return exampleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        final Example_item_add_new_habit current_item = m_example_list.get(position);
        set_emoji(holder,current_item);
        set_text(holder,current_item);
        set_color(holder,current_item);
        listen_to_card_click(holder,current_item);
    }

    public Adapter_add_habit(ArrayList<Example_item_add_new_habit> example_item_add_reminder) {
        m_example_list = example_item_add_reminder;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public View circle_with_color_at_the_end_of_icon;
        public EmojiTextView emoji_text_view_icon;
        public TextView text_with_name_of_the_habit;
        public CardView card_to_show_bad_habits_in_home_main;
        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            circle_with_color_at_the_end_of_icon = itemView.findViewById(R.id.circle_with_color_at_the_end_of_icon);
            emoji_text_view_icon = itemView.findViewById(R.id.emoji_text_view_icon);
            text_with_name_of_the_habit = itemView.findViewById(R.id.text_with_name_of_the_habit);
            card_to_show_bad_habits_in_home_main = itemView.findViewById(R.id.card_to_show_bad_habits_in_home_main);
        }
    }

    private void set_color(ExampleViewHolder holder,Example_item_add_new_habit current_item){
        holder.circle_with_color_at_the_end_of_icon.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(current_item.getColor())));
    }

    private void set_text(ExampleViewHolder holder,Example_item_add_new_habit current_item){
        holder.text_with_name_of_the_habit.setText(current_item.getName());
    }

    private void set_emoji(ExampleViewHolder holder,Example_item_add_new_habit current_item){
        holder.emoji_text_view_icon.setText(current_item.getEmoji());
    }

    private void listen_to_card_click(ExampleViewHolder holder,Example_item_add_new_habit current_item){
        holder.card_to_show_bad_habits_in_home_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card_clicked_listen_listener.card_just_got_clicked(current_item.getName(),current_item.getColor(),current_item.getEmoji());
            }
        });
    }
}
