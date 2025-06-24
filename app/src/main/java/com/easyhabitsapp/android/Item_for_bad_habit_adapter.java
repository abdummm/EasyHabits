package com.easyhabitsapp.android;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.vanniktech.emoji.Emoji;
import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.EmojiTextView;
import com.vanniktech.emoji.google.GoogleEmojiProvider;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Item_for_bad_habit_adapter extends RecyclerView.Adapter<Item_for_bad_habit_adapter.Item_adapter_bad_habits> {
    private ArrayList<Item_for_home_habits> m_example;

    private Card_clicked_listen Card_clicked_listen_listener;
    private today_clicked_listen today_clicked_listen_listener;

    public void set_card_clicked_listen(Card_clicked_listen listener) {
        Card_clicked_listen_listener = listener;
    }

    public interface Card_clicked_listen {
        void card_just_got_clicked(int id);
    }

    public void set_today_clicked_listen(today_clicked_listen listener) {
        today_clicked_listen_listener = listener;
    }

    public interface today_clicked_listen {
        void today_just_got_clicked(int id, int position);
    }

    public static class Item_adapter_bad_habits extends RecyclerView.ViewHolder {
        public View circle_with_color_at_the_end_of_icon;
        public EmojiTextView emoji_text_view_icon;
        public TextView text_with_name_of_the_habit;
        public View back_ground_of_emoji_fifty_color;
        public CardView card_to_show_bad_habits_in_home_main;
        public View white_back_ground_for_the_checkat_the_far_right;
        public View check_mark_5;
        public ConstraintLayout layout_in_card_of_the_habit;
        public TextView text_with_quantity_of_habit;
        public View check_mark_1;
        public View check_mark_2;
        public View check_mark_3;
        public View check_mark_4;

        public Item_adapter_bad_habits(View itemview) {
            super(itemview);
            circle_with_color_at_the_end_of_icon = itemview.findViewById(R.id.circle_with_color_at_the_end_of_icon);
            emoji_text_view_icon = itemview.findViewById(R.id.emoji_text_view_icon);
            text_with_name_of_the_habit = itemview.findViewById(R.id.text_with_name_of_the_habit);
            back_ground_of_emoji_fifty_color = itemview.findViewById(R.id.back_ground_of_emoji_fifty_color);
            card_to_show_bad_habits_in_home_main = itemview.findViewById(R.id.card_to_show_bad_habits_in_home_main);
            white_back_ground_for_the_checkat_the_far_right = itemview.findViewById(R.id.white_back_ground_for_the_checkat_the_far_right);
            layout_in_card_of_the_habit = itemview.findViewById(R.id.layout_in_card_of_the_habit);
            text_with_quantity_of_habit = itemview.findViewById(R.id.text_with_quantity_of_habit);
            check_mark_1 = itemview.findViewById(R.id.check_mark_1);
            check_mark_2 = itemview.findViewById(R.id.check_mark_2);
            check_mark_3 = itemview.findViewById(R.id.check_mark_3);
            check_mark_4 = itemview.findViewById(R.id.check_mark_4);
            check_mark_5 = itemview.findViewById(R.id.check_mark_5);
            /*itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });*/
        }
    }

    public Item_for_bad_habit_adapter(ArrayList<Item_for_home_habits> example_list) {
        m_example = example_list;
    }

    @NonNull
    @Override
    public Item_adapter_bad_habits onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EmojiManager.install(new GoogleEmojiProvider());
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_for_home_multiple_habits, parent, false);
        Item_adapter_bad_habits item_adapter_bad_habits = new Item_adapter_bad_habits(view);
        return item_adapter_bad_habits;
    }

    @Override
    public void onBindViewHolder(@NonNull Item_adapter_bad_habits holder, int position) {
        Item_for_home_habits current_item = m_example.get(position);
        set_the_icon(holder, current_item);
        set_the_text(holder, current_item);
        set_card_color(holder, current_item);
        set_the_fifty_color(holder, current_item);
        check_button_listen(holder, current_item);
        card_click_listen(holder, current_item);
        set_the_check_or_plus(holder, current_item);
        set_frequncy_text(holder, current_item);
        set_up_last_5_days(holder, current_item);
        set_the_amount_text(holder, current_item);
        set_the_margin_top(holder, current_item);
    }

    @Override
    public int getItemCount() {
        return m_example.size();
    }

    private void set_the_icon(Item_adapter_bad_habits holder, Item_for_home_habits current_item) {
        holder.emoji_text_view_icon.setText(current_item.getM_icon());
    }

    private void set_the_text(Item_adapter_bad_habits holder, Item_for_home_habits current_item) {
        holder.text_with_name_of_the_habit.setText(make_first_letter_of_title_capital(current_item.getM_habit_name()));
    }

    private void set_the_fifty_color(Item_adapter_bad_habits holder, Item_for_home_habits current_item) {
        if (!current_item.getM_icon().isEmpty()) {
            int light_color = ColorUtils.blendARGB(Color.parseColor(current_item.getM_title_color()), Color.WHITE, 0.5F);
            int dark_color = ColorUtils.blendARGB(Color.parseColor(current_item.getM_title_color()), Color.BLACK, 0.04F);
            holder.back_ground_of_emoji_fifty_color.setVisibility(View.VISIBLE);
            holder.circle_with_color_at_the_end_of_icon.setVisibility(View.VISIBLE);
            holder.emoji_text_view_icon.setVisibility(View.VISIBLE);
            holder.back_ground_of_emoji_fifty_color.setBackgroundTintList(ColorStateList.valueOf(light_color));
            holder.circle_with_color_at_the_end_of_icon.setBackgroundTintList(ColorStateList.valueOf(dark_color));

        } else {
            holder.back_ground_of_emoji_fifty_color.setVisibility(View.GONE);
            holder.circle_with_color_at_the_end_of_icon.setVisibility(View.GONE);
            holder.emoji_text_view_icon.setVisibility(View.GONE);
        }
    }

    private void set_card_color(Item_adapter_bad_habits holder, Item_for_home_habits current_item) {
        holder.card_to_show_bad_habits_in_home_main.setCardBackgroundColor(Color.parseColor(current_item.getM_title_color()));
    }

    private void check_button_listen(Item_adapter_bad_habits holder, Item_for_home_habits current_item) {
        holder.white_back_ground_for_the_checkat_the_far_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                today_clicked_listen_listener.today_just_got_clicked(current_item.getId(), holder.getAbsoluteAdapterPosition());
            }
        });
    }

    private void card_click_listen(Item_adapter_bad_habits holder, Item_for_home_habits current_item) {
        holder.card_to_show_bad_habits_in_home_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card_clicked_listen_listener.card_just_got_clicked(current_item.getId());
            }
        });
    }

    private void set_the_check_or_plus(Item_adapter_bad_habits holder, Item_for_home_habits current_item) {
        if(current_item.getType().equals("Build_up")){
            if (current_item.getAmount().getTarget_amount() == 1) {
                holder.check_mark_5.setBackground(ContextCompat.getDrawable(holder.layout_in_card_of_the_habit.getContext(), R.drawable.check_48px));
            } else {
                holder.check_mark_5.setBackground(ContextCompat.getDrawable(holder.layout_in_card_of_the_habit.getContext(), R.drawable.add_48px_round));
            }
        } else if(current_item.getType().equals("Quit")){
            holder.check_mark_5.setBackground(ContextCompat.getDrawable(holder.layout_in_card_of_the_habit.getContext(), R.drawable.close_48px));
        }
        holder.check_mark_5.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
    }

    private void set_frequncy_text(Item_adapter_bad_habits holder, Item_for_home_habits current_item) {
        if (current_item.getAmount().getTarget_amount() == 1) {
            holder.text_with_quantity_of_habit.setVisibility(View.GONE);
        } else {
            holder.text_with_quantity_of_habit.setVisibility(View.VISIBLE);
            //set text here
        }
    }

    private void set_up_last_5_days(Item_adapter_bad_habits holder, Item_for_home_habits current_item) {
        ArrayList<String> last_5_days = current_item.getLast_5_days();
        View[] check_marks = {holder.check_mark_1, holder.check_mark_2, holder.check_mark_3, holder.check_mark_4};
        for (int i = 0; i < check_marks.length; i++) {
            if (last_5_days.get(i).equals("empty")) {
                check_marks[i].setBackgroundResource(R.drawable.circle_20px);
                check_marks[i].setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
            } else if (last_5_days.get(i).equals("yes")) {
                if(current_item.getType().equals("Build_up")){
                    check_marks[i].setBackgroundResource(R.drawable.check_48px);
                    check_marks[i].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#06a94d"))); //green
                } else if (current_item.getType().equals("Quit")){
                    check_marks[i].setBackgroundResource(R.drawable.check_48px);
                    check_marks[i].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#06a94d"))); // red
                }
            } else if (last_5_days.get(i).equals("no")) {
                check_marks[i].setBackgroundResource(R.drawable.close_48px);
                check_marks[i].setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF2400")));
            }
        }
        if(current_item.getType().equals("Build_up")){
            if (last_5_days.get(4).equals("empty") || last_5_days.get(4).equals("no")) {
                holder.check_mark_5.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
            } else if (last_5_days.get(4).equals("yes")) {
                holder.check_mark_5.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#06a94d"))); //green
            }
        } else if (current_item.getType().equals("Quit")) {
            if (last_5_days.get(4).equals("empty") || last_5_days.get(4).equals("yes")){
                holder.check_mark_5.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
            }  else if (last_5_days.get(4).equals("no")) {
                holder.check_mark_5.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF2400"))); // red

            }
        }
    }

    private void set_the_amount_text(Item_adapter_bad_habits holder, Item_for_home_habits current_item) {
        holder.text_with_quantity_of_habit.setText(String.valueOf(current_item.getAmount().getReal_amount()).concat("/").concat(String.valueOf(current_item.getAmount().getTarget_amount())));
    }

    private void set_the_margin_top(Item_adapter_bad_habits holder, Item_for_home_habits current_item) {
        if (holder.getAbsoluteAdapterPosition() == 0) {
            RecyclerView.LayoutParams newLayoutParams = (RecyclerView.LayoutParams) holder.card_to_show_bad_habits_in_home_main.getLayoutParams();
            newLayoutParams.topMargin = current_item.getFirst_item_margin();
            holder.card_to_show_bad_habits_in_home_main.setLayoutParams(newLayoutParams);
        } else {
            RecyclerView.LayoutParams newLayoutParams = (RecyclerView.LayoutParams) holder.card_to_show_bad_habits_in_home_main.getLayoutParams();
            newLayoutParams.topMargin = (int) convertDpToPixel(5,holder.layout_in_card_of_the_habit.getContext());
            holder.card_to_show_bad_habits_in_home_main.setLayoutParams(newLayoutParams);
        }
    }

    private static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private String make_first_letter_of_title_capital(String myString){
        return myString.substring(0, 1).toUpperCase() + myString.substring(1).toLowerCase();
    }
}
