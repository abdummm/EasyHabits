package com.easyhabitsapp.android;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_add_reminder_add_new_habit extends RecyclerView.Adapter<Adapter_add_reminder_add_new_habit.ExampleViewHolder> {
    private ArrayList<Example_item_reminder_add_new_habit> m_example_list;
    private delete_button_reminder_clicked delete_button_reminder_clicked_listen;
    private edit_button_reminder_clicked edit_button_reminder_clicked_listen;

    public void set_delete_reminder(delete_button_reminder_clicked listen) {
        delete_button_reminder_clicked_listen = listen;
    }

    public interface delete_button_reminder_clicked {
        void delete_got_clicked(int position);
    }

    public void set_edit_reminder(edit_button_reminder_clicked listen) {
        edit_button_reminder_clicked_listen = listen;
    }

    public interface edit_button_reminder_clicked {
        void edit_got_clicked(int position);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_reminder_add_new_habit, parent, false);
        ExampleViewHolder exampleViewHolder = new ExampleViewHolder(view);
        return exampleViewHolder;
    }

    public Adapter_add_reminder_add_new_habit(ArrayList<Example_item_reminder_add_new_habit> example_item_add_reminder) {
        m_example_list = example_item_add_reminder;
    }

    public void onBindViewHolder(@NonNull final ExampleViewHolder holder, final int position) {
        final Example_item_reminder_add_new_habit current_item = m_example_list.get(position);
        set_card_color(holder, current_item);
        delete_and_edit_button_listen(holder, current_item);
        set_buttons_color(holder, current_item);
        on_card_click(holder, current_item);
        set_text(holder,current_item);
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView text_saying_the_days_add_reminder_add_new_habit;
        public CardView card_to_show_reminder_add_new_habit;
        public Button delete_bottom_sheet_add_reminder;
        public Button edit_bottom_sheet_add_reminder;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            text_saying_the_days_add_reminder_add_new_habit = itemView.findViewById(R.id.text_saying_the_days_add_reminder_add_new_habit);
            card_to_show_reminder_add_new_habit = itemView.findViewById(R.id.card_to_show_reminder_add_new_habit);
            delete_bottom_sheet_add_reminder = itemView.findViewById(R.id.delete_bottom_sheet_add_reminder);
            edit_bottom_sheet_add_reminder = itemView.findViewById(R.id.edit_bottom_sheet_add_reminder);
        }
    }

    private void set_text(ExampleViewHolder holder, Example_item_reminder_add_new_habit current_item) {
        //holder.text_saying_the_days_add_reminder_add_new_habit.setText(current_item.get);
        String days = "";
        ArrayList<String> days_list = current_item.getDays();
        if(days_list.size() == 7){
            days = "Everyday ";
        } else {
            if(days_list.contains("Monday")){
                days = days.concat("Mo., ");
            }
            if(days_list.contains("Tuesday")){
                days = days.concat("Tu., ");
            }
            if(days_list.contains("Wednesday")){
                days = days.concat("We., ");
            }
            if(days_list.contains("Thursday")){
                days = days.concat("Th., ");
            }
            if(days_list.contains("Friday")){
                days = days.concat("Fr., ");
            }
            if(days_list.contains("Saturday")){
                days = days.concat("Sa., ");
            }
            if(days_list.contains("Sunday")){
                days = days.concat("Su., ");
            }
        }
        int minutes = (int) ((current_item.getTime() / (1000 * 60)) % 60);
        int hours = (int) ((current_item.getTime() / (1000 * 60 * 60)) % 24);
        String am_or_pm = "";
        if (hours < 12) {
            am_or_pm = "AM";
            if (hours == 0) {
                hours = 12;
            }
        } else {
            am_or_pm = "PM";
            hours = hours-12;
            if (hours == 0) {
                hours = 12;
            }
        }
        holder.text_saying_the_days_add_reminder_add_new_habit.setText(days.concat("at ").concat(String.valueOf(hours)).concat(":").concat(String.format("%02d", minutes)).concat(" ").concat(am_or_pm));
    }

    private void set_card_color(ExampleViewHolder holder, Example_item_reminder_add_new_habit current_item) {
        holder.card_to_show_reminder_add_new_habit.setCardBackgroundColor(current_item.getColor());
    }

    private void set_buttons_color(ExampleViewHolder holder, Example_item_reminder_add_new_habit current_item) {
        int resultColor = ColorUtils.blendARGB(Color.WHITE, current_item.getColor(), 0.75F);
        holder.delete_bottom_sheet_add_reminder.setForegroundTintList(ColorStateList.valueOf(resultColor));
        holder.edit_bottom_sheet_add_reminder.setForegroundTintList(ColorStateList.valueOf(resultColor));
    }

    private void delete_and_edit_button_listen(ExampleViewHolder holder, Example_item_reminder_add_new_habit current_item) {
        holder.delete_bottom_sheet_add_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_button_reminder_clicked_listen.delete_got_clicked(holder.getLayoutPosition());
            }
        });
        holder.edit_bottom_sheet_add_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_just_clicked(holder, current_item);
            }
        });
    }

    private void on_card_click(ExampleViewHolder holder, Example_item_reminder_add_new_habit current_item) {
        holder.card_to_show_reminder_add_new_habit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_just_clicked(holder, current_item);
            }
        });
    }

    private void edit_just_clicked(ExampleViewHolder holder, Example_item_reminder_add_new_habit current_item) {
        edit_button_reminder_clicked_listen.edit_got_clicked(holder.getLayoutPosition());
    }
}
