package com.easyhabitsapp.android;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_for_emergency extends RecyclerView.Adapter<Adapter_for_emergency.ExampleViewHolder>{
    private ArrayList<Example_item_emergency> m_example_list;
    private Adapter_for_emergency.OnItemClickListener m_listener;
    private Adapter_for_emergency.OnCardClickListener card_listener;

    public interface OnItemClickListener {
        void onItemClick(int which);
    }

    public void set_on_item_click_listener_for_mood(Adapter_for_emergency.OnItemClickListener listener){
        m_listener = listener;
    }

    public interface OnCardClickListener {
        void onCardClick(String which);
    }

    public void set_on_card_click_listener_for_mood(Adapter_for_emergency.OnCardClickListener listener){
        card_listener = listener;
    }

    @NonNull
    @Override
    public Adapter_for_emergency.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_emergency_main, parent, false);
        Adapter_for_emergency.ExampleViewHolder exampleViewHolder = new Adapter_for_emergency.ExampleViewHolder(view);
        return exampleViewHolder;
    }

    public Adapter_for_emergency(ArrayList<Example_item_emergency> Example_reply_to_comment) {
        m_example_list = Example_reply_to_comment;
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter_for_emergency.ExampleViewHolder holder, final int position) {
        final Example_item_emergency current_item = m_example_list.get(position);
        if(current_item.getText().equals("Mood tracker")){
            holder.not_mood_tracker_in_the_card.setVisibility(View.GONE);
            holder.mood_tracker_in_the_card.setVisibility(View.VISIBLE);
            holder.card_to_show_bad_habits_in_home_main.setCardBackgroundColor(current_item.getColor());
            set_the_on_button_click_listen(holder);
            set_entry_today(holder,current_item.isEntry_today());
            holder.text_saying_thanks_for_your_input.setText("Today's input has been recorded");
        } else {
            holder.not_mood_tracker_in_the_card.setVisibility(View.VISIBLE);
            holder.mood_tracker_in_the_card.setVisibility(View.GONE);
            holder.card_to_show_bad_habits_in_home_main.setCardBackgroundColor(current_item.getColor());
            holder.text_saying_streak_in_card.setText(current_item.getText());
            set_the_icon(current_item.getDrawable(),holder,current_item.getColor());
        }
        on_card_click_listen(holder,current_item);
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
        public TextView text_saying_streak_in_card;
        public CardView card_to_show_bad_habits_in_home_main;
        public View set_up_the_icon_for_the_card;
        public ConstraintLayout not_mood_tracker_in_the_card;
        public ConstraintLayout mood_tracker_in_the_card;
        public Button very_bad_mood_button_in_habits;
        public Button bad_mood_button_in_habits;
        public Button ok_mood_button_in_habits;
        public Button good_mood_button_in_habits;
        public Button very_good_mood_button_in_habits;
        public TextView text_saying_thanks_for_your_input;
        public View very_bad_mood_in_habits;
        public View bad_mood_in_habits;
        public View ok_mood_in_habits;
        public View good_mood_in_habits;
        public View very_good_mood_in_habits;
        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            text_saying_streak_in_card = itemView.findViewById(R.id.text_saying_streak_in_card);
            card_to_show_bad_habits_in_home_main = itemView.findViewById(R.id.card_to_show_bad_habits_in_home_main);
            set_up_the_icon_for_the_card = itemView.findViewById(R.id.set_up_the_icon_for_the_card);
            not_mood_tracker_in_the_card = itemView.findViewById(R.id.not_mood_tracker_in_the_card);
            mood_tracker_in_the_card = itemView.findViewById(R.id.mood_tracker_in_the_card);
            very_bad_mood_button_in_habits = itemView.findViewById(R.id.very_bad_mood_button_in_habits);
            bad_mood_button_in_habits = itemView.findViewById(R.id.bad_mood_button_in_habits);
            ok_mood_button_in_habits = itemView.findViewById(R.id.ok_mood_button_in_habits);
            good_mood_button_in_habits = itemView.findViewById(R.id.good_mood_button_in_habits);
            very_good_mood_button_in_habits = itemView.findViewById(R.id.very_good_mood_button_in_habits);
            text_saying_thanks_for_your_input = itemView.findViewById(R.id.text_saying_thanks_for_your_input);
            very_bad_mood_in_habits = itemView.findViewById(R.id.very_bad_mood_in_habits);
            bad_mood_in_habits = itemView.findViewById(R.id.bad_mood_in_habits);
            ok_mood_in_habits = itemView.findViewById(R.id.ok_mood_in_habits);
            good_mood_in_habits = itemView.findViewById(R.id.good_mood_in_habits);
            very_good_mood_in_habits = itemView.findViewById(R.id.very_good_mood_in_habits);
        }
    }

    private void set_the_icon(Drawable icon, Adapter_for_emergency.ExampleViewHolder holder, int color){
        icon = DrawableCompat.wrap(icon).mutate();
        DrawableCompat.setTint(icon,color);
        holder.set_up_the_icon_for_the_card.setBackground(icon);
    }
    private void set_the_on_button_click_listen(Adapter_for_emergency.ExampleViewHolder holder){
        holder.very_bad_mood_button_in_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_entry_today(holder,true);
                holder.text_saying_thanks_for_your_input.setText("Hang tight, better days are coming!!");
                m_listener.onItemClick(1);
            }
        });
        holder.bad_mood_button_in_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_entry_today(holder,true);
                holder.text_saying_thanks_for_your_input.setText("Good days are loading!!");
                m_listener.onItemClick(2);
            }
        });
        holder.ok_mood_button_in_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_entry_today(holder,true);
                holder.text_saying_thanks_for_your_input.setText("It's going to be alright!!");
                m_listener.onItemClick(3);
            }
        });
        holder.good_mood_button_in_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_entry_today(holder,true);
                holder.text_saying_thanks_for_your_input.setText("Keep the good vibes up!!");
                m_listener.onItemClick(4);
            }
        });
        holder.very_good_mood_button_in_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_entry_today(holder,true);
                holder.text_saying_thanks_for_your_input.setText("Great to hear!!");
                m_listener.onItemClick(5);
            }
        });
    }

    private void set_entry_today(Adapter_for_emergency.ExampleViewHolder holder, boolean entry){
        if(entry){
            holder.text_saying_thanks_for_your_input.setVisibility(View.VISIBLE);
            holder.very_bad_mood_button_in_habits.setVisibility(View.INVISIBLE);
            holder.bad_mood_button_in_habits.setVisibility(View.INVISIBLE);
            holder.ok_mood_button_in_habits.setVisibility(View.INVISIBLE);
            holder.good_mood_button_in_habits.setVisibility(View.INVISIBLE);
            holder.very_good_mood_button_in_habits.setVisibility(View.INVISIBLE);
            holder.very_bad_mood_in_habits.setVisibility(View.INVISIBLE);
            holder.bad_mood_in_habits.setVisibility(View.INVISIBLE);
            holder.ok_mood_in_habits.setVisibility(View.INVISIBLE);
            holder.good_mood_in_habits.setVisibility(View.INVISIBLE);
            holder.very_good_mood_in_habits.setVisibility(View.INVISIBLE);
        } else {
            holder.text_saying_thanks_for_your_input.setVisibility(View.INVISIBLE);
            holder.very_bad_mood_button_in_habits.setVisibility(View.VISIBLE);
            holder.bad_mood_button_in_habits.setVisibility(View.VISIBLE);
            holder.ok_mood_button_in_habits.setVisibility(View.VISIBLE);
            holder.good_mood_button_in_habits.setVisibility(View.VISIBLE);
            holder.very_good_mood_button_in_habits.setVisibility(View.VISIBLE);
            holder.very_bad_mood_in_habits.setVisibility(View.VISIBLE);
            holder.bad_mood_in_habits.setVisibility(View.VISIBLE);
            holder.ok_mood_in_habits.setVisibility(View.VISIBLE);
            holder.good_mood_in_habits.setVisibility(View.VISIBLE);
            holder.very_good_mood_in_habits.setVisibility(View.VISIBLE);
        }
    }
    private void on_card_click_listen(Adapter_for_emergency.ExampleViewHolder holder,Example_item_emergency current_item){
        holder.card_to_show_bad_habits_in_home_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                card_listener.onCardClick(current_item.getText());
            }
        });
    }
}
