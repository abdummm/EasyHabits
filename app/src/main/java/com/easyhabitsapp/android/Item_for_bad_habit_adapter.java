package com.easyhabitsapp.android;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Item_for_bad_habit_adapter extends RecyclerView.Adapter<Item_for_bad_habit_adapter.Item_adapter_bad_habits> {
    private ArrayList<Item_for_home_habits> m_example;
    private OnItemClickListener m_listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void set_on_item_click_listener(OnItemClickListener listener){
        m_listener = listener;
    }

    public static class Item_adapter_bad_habits extends RecyclerView.ViewHolder{
        public TextView text_view_in_card_saying_the_card;
        public TextView text_saying_streak_in_card;
        public CardView card_to_show_bad_habits_in_home_main;
        public View set_up_the_icon_for_the_card;
        public Item_adapter_bad_habits (View itemview, final OnItemClickListener listener ){
            super(itemview);
            text_view_in_card_saying_the_card = itemview.findViewById(R.id.text_view_in_card_saying_the_card);
            text_saying_streak_in_card = itemview.findViewById(R.id.text_saying_streak_in_card);
            card_to_show_bad_habits_in_home_main = itemview.findViewById(R.id.card_to_show_bad_habits_in_home_main);
            set_up_the_icon_for_the_card = itemview.findViewById(R.id.set_up_the_icon_for_the_card);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public Item_for_bad_habit_adapter(ArrayList<Item_for_home_habits>example_list){
        m_example = example_list;
    }
    @NonNull
    @Override
    public Item_adapter_bad_habits onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_for_home_multiple_habits,parent,false);
        Item_adapter_bad_habits item_adapter_bad_habits = new Item_adapter_bad_habits(view,m_listener);
        return item_adapter_bad_habits;
    }

    @Override
    public void onBindViewHolder(@NonNull Item_adapter_bad_habits holder, int position) {
        Item_for_home_habits current_item = m_example.get(position);
        holder.card_to_show_bad_habits_in_home_main.setCardBackgroundColor(current_item.getM_title_color());
        holder.text_view_in_card_saying_the_card.setText(current_item.getM_habit_name());
        holder.text_saying_streak_in_card.setText(String.valueOf(current_item.getM_streak_in_day()).concat(" days"));
        set_the_icon(current_item.getM_icon(),holder,current_item.getM_title_color());
    }

    @Override
    public int getItemCount() {
        return m_example.size();
    }

    private void set_the_icon(Drawable icon,Item_adapter_bad_habits holder, int color){
        icon = DrawableCompat.wrap(icon).mutate();
        DrawableCompat.setTint(icon,color);
        holder.set_up_the_icon_for_the_card.setBackground(icon);
    }
}
