package com.easyhabitsapp.android;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.vanniktech.emoji.EmojiTextView;

import java.util.ArrayList;
import java.util.Random;

public class Adapter_buy_premuim extends RecyclerView.Adapter<Adapter_buy_premuim.ExampleViewHolder>{
    private ArrayList<Example_item_buy_premuim_features> m_example_list;


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pager_buy_premuim, parent, false);
        ExampleViewHolder exampleViewHolder = new ExampleViewHolder(view);
        return exampleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        final Example_item_buy_premuim_features current_item = m_example_list.get(position);
        set_color(holder,current_item);
        set_text(holder,current_item);
        set_emoji(holder,current_item);
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

    public Adapter_buy_premuim(ArrayList<Example_item_buy_premuim_features> example_item_add_reminder) {
        m_example_list = example_item_add_reminder;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public View circle_in_the_middle_of_view_pager;
        public TextView text_view_saying_the_name_of_of_premuim_feature;
        public EmojiTextView icon_of_the_premuim_feature;
        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            circle_in_the_middle_of_view_pager = itemView.findViewById(R.id.circle_in_the_middle_of_view_pager);
            text_view_saying_the_name_of_of_premuim_feature = itemView.findViewById(R.id.text_view_saying_the_name_of_of_premuim_feature);
            icon_of_the_premuim_feature = itemView.findViewById(R.id.icon_of_the_premuim_feature);

        }
    }

    private void set_color(ExampleViewHolder holder,Example_item_buy_premuim_features current_item){
        int light_color = ColorUtils.blendARGB(Color.parseColor(current_item.getColor()), Color.WHITE, 0.5F);
        holder.circle_in_the_middle_of_view_pager.setBackgroundTintList(ColorStateList.valueOf(light_color));
    }

    private void set_text(ExampleViewHolder holder,Example_item_buy_premuim_features current_item){
        holder.text_view_saying_the_name_of_of_premuim_feature.setText(current_item.getText());
    }

    private void set_emoji(ExampleViewHolder holder,Example_item_buy_premuim_features current_item){
        holder.icon_of_the_premuim_feature.setText(current_item.getEmoji_icon());
    }
}
