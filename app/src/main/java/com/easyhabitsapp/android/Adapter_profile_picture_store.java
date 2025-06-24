package com.easyhabitsapp.android;

import android.content.res.ColorStateList;
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

public class Adapter_profile_picture_store extends RecyclerView.Adapter<Adapter_profile_picture_store.ExampleViewHolder> {
    private ArrayList<Example_item_profile_picture_store_item> m_example_list;

    @NonNull
    @Override
    public Adapter_profile_picture_store.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_new_habit_recycle_view_item, parent, false);
        Adapter_profile_picture_store.ExampleViewHolder exampleViewHolder = new Adapter_profile_picture_store.ExampleViewHolder(view);
        return exampleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_profile_picture_store.ExampleViewHolder holder, int position) {
        final Example_item_profile_picture_store_item current_item = m_example_list.get(position);
        write_the_name(holder, current_item);
        set_icon(holder, current_item);
        set_color(holder, current_item);
        set_price(holder,current_item);
        set_the_limited_text(holder,current_item);
        buy_button_clicked_listen(holder,current_item);
        cart_button_clciked(holder,current_item);
        icon_clicked(holder,current_item);
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView text_view_with_the_description_;
        public Button view_with_the_profile_picture_for_sale;
        public View view_with_shopping_cart_buy_profile_picture;
        public View line_under_rare_buy_profile_picture;
        public Button buy_with_black_bars_at_the_bottom;
        public TextView text_view_saying_the_rarity_of_the_item;
        public Button button_with_a_shopping_cart_buy_profile_picture;
        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            text_view_with_the_description_ = itemView.findViewById(R.id.text_view_with_the_description_);
            view_with_the_profile_picture_for_sale = itemView.findViewById(R.id.view_with_the_profile_picture_for_sale);
            view_with_shopping_cart_buy_profile_picture = itemView.findViewById(R.id.view_with_shopping_cart_buy_profile_picture);
            line_under_rare_buy_profile_picture = itemView.findViewById(R.id.line_under_rare_buy_profile_picture);
            buy_with_black_bars_at_the_bottom = itemView.findViewById(R.id.buy_with_black_bars_at_the_bottom);
            text_view_saying_the_rarity_of_the_item = itemView.findViewById(R.id.text_view_saying_the_rarity_of_the_item);
            button_with_a_shopping_cart_buy_profile_picture = itemView.findViewById(R.id.button_with_a_shopping_cart_buy_profile_picture);
        }
    }

    public Adapter_profile_picture_store(ArrayList<Example_item_profile_picture_store_item> example_item_profile_picture_store_item) {
        m_example_list = example_item_profile_picture_store_item;
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

    private void write_the_name(ExampleViewHolder holder, Example_item_profile_picture_store_item current_item) {
        holder.text_view_with_the_description_.setText(current_item.getName());
    }

    private void set_icon(ExampleViewHolder holder, Example_item_profile_picture_store_item current_item) {
        holder.view_with_the_profile_picture_for_sale.setBackground(current_item.getIcon());
    }

    private void set_color(ExampleViewHolder holder, Example_item_profile_picture_store_item current_item) {
        if (current_item.getType().equals("limited")) {
            holder.line_under_rare_buy_profile_picture.setBackgroundTintList(ColorStateList.valueOf(current_item.getColor()));
        }
        holder.view_with_shopping_cart_buy_profile_picture.setBackgroundTintList(ColorStateList.valueOf(current_item.getColor()));
        holder.buy_with_black_bars_at_the_bottom.setBackgroundTintList(ColorStateList.valueOf(current_item.getColor()));
    }

    private void set_price(ExampleViewHolder holder, Example_item_profile_picture_store_item current_item){
        holder.buy_with_black_bars_at_the_bottom.setText("Buy | ".concat(current_item.getPrice()));
    }

    private void set_the_limited_text(ExampleViewHolder holder, Example_item_profile_picture_store_item current_item){
        if(current_item.getType().equals("normal")){
            holder.text_view_saying_the_rarity_of_the_item.setVisibility(View.INVISIBLE);
            holder.line_under_rare_buy_profile_picture.setVisibility(View.INVISIBLE);
        } else if(current_item.getType().equals("limited")){
            holder.text_view_saying_the_rarity_of_the_item.setVisibility(View.VISIBLE);
            holder.line_under_rare_buy_profile_picture.setVisibility(View.VISIBLE);
            holder.text_view_saying_the_rarity_of_the_item.setText(String.valueOf(current_item.getUnique_limited_number()).concat(" of ").concat(String.valueOf(current_item.getTotal_limited_items())));
        }
    }

    private void buy_button_clicked_listen(ExampleViewHolder holder, Example_item_profile_picture_store_item current_item){
        holder.buy_with_black_bars_at_the_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void cart_button_clciked(ExampleViewHolder holder, Example_item_profile_picture_store_item current_item){
        holder.button_with_a_shopping_cart_buy_profile_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void icon_clicked(ExampleViewHolder holder, Example_item_profile_picture_store_item current_item){
        holder.view_with_the_profile_picture_for_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
