package com.easyhabitsapp.android;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Item_for_chat_adapter extends RecyclerView.Adapter<Item_for_chat_adapter.chat_view_holder> {
    private ArrayList<Item_for_chat> m_item_for_chats;

    @NonNull
    @Override
    public chat_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_chat_sample, parent, false);
        chat_view_holder chat_view_holder = new chat_view_holder(view);
        return chat_view_holder;
    }

    public Item_for_chat_adapter(ArrayList<Item_for_chat> item_for_chats) {
        m_item_for_chats = item_for_chats;
    }

    @Override
    public void onBindViewHolder(@NonNull chat_view_holder holder, int position) {
        Item_for_chat current_item = m_item_for_chats.get(position);
        if(current_item.getMode().equals("normal")){
            float screen_width = Resources.getSystem().getDisplayMetrics().widthPixels;
            TextPaint paint = new TextPaint();
            paint.setTextSize(holder.send.getTextSize());
            paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            if (current_item.get_got_messages().equals("")) {
                holder.text_in_middle.setVisibility(View.INVISIBLE);
                holder.got.setText("");
                holder.got_bubble.setVisibility(View.INVISIBLE);
                //holder.send.setText(current_item.get_send_messages());
                holder.send_bubble.setVisibility(View.VISIBLE);
                float text_width = paint.measureText(current_item.get_send_messages());
                String new_string = "";
                if (text_width > screen_width * 0.6) {
                    String old_string = current_item.get_send_messages();
                    String temp_line = "";
                    for (int i = 0; i < current_item.get_send_messages().length(); i++) {
                        temp_line = temp_line.concat(old_string.substring(i, i + 1));
                        if (paint.measureText(temp_line) > screen_width * 0.6) {
                            new_string = new_string.concat(temp_line).concat("\n");
                            temp_line = "";
                        }
                    }
                    new_string = new_string.concat(temp_line);
                } else {
                    new_string = current_item.get_send_messages();
                }
                holder.send.setText(new_string);
            } else {
                holder.text_in_middle.setVisibility(View.INVISIBLE);
                //holder.got.setText(current_item.get_got_messages());
                holder.got_bubble.setVisibility(View.VISIBLE);
                holder.send.setText("");
                holder.send_bubble.setVisibility(View.INVISIBLE);
                float text_width = paint.measureText(current_item.get_got_messages());
                String new_string = "";
                if (text_width > screen_width * 0.6) {
                    String old_string = current_item.get_got_messages();
                    String temp_line = "";
                    for (int i = 0; i < current_item.get_got_messages().length(); i++) {
                        temp_line = temp_line.concat(old_string.substring(i, i + 1));
                        if (paint.measureText(temp_line) > screen_width * 0.6) {
                            new_string = new_string.concat(temp_line).concat("\n");
                            temp_line = "";
                        }
                    }
                    new_string = new_string.concat(temp_line);
                } else {
                    new_string = current_item.get_got_messages();
                }
                holder.got.setText(new_string);
            }
        } else {
            if(current_item.getMode().equals("i_get_gift")){
                holder.text_in_middle.setVisibility(View.VISIBLE);
                holder.text_in_middle.setText("The other user has gifted you ".concat(current_item.get_send_messages()).concat(" months of premium!!"));
                holder.got.setText("");
                holder.got_bubble.setVisibility(View.INVISIBLE);
                holder.send.setText("");
                holder.send_bubble.setVisibility(View.INVISIBLE);
            } else if (current_item.getMode().equals("i_left")){
                holder.text_in_middle.setVisibility(View.VISIBLE);
                holder.text_in_middle.setText("You left the chat");
                holder.got.setText("");
                holder.got_bubble.setVisibility(View.INVISIBLE);
                holder.send.setText("");
                holder.send_bubble.setVisibility(View.INVISIBLE);
            } else if (current_item.getMode().equals("they_left")){
                holder.text_in_middle.setVisibility(View.VISIBLE);
                holder.text_in_middle.setText("The other user has left the chat.");
                holder.got.setText("");
                holder.got_bubble.setVisibility(View.INVISIBLE);
                holder.send.setText("");
                holder.send_bubble.setVisibility(View.INVISIBLE);
            } else if(current_item.getMode().equals("time_finish")){
                holder.text_in_middle.setVisibility(View.VISIBLE);
                holder.text_in_middle.setText("Time ran out. Hope to see you again.");
                holder.got.setText("");
                holder.got_bubble.setVisibility(View.INVISIBLE);
                holder.send.setText("");
                holder.send_bubble.setVisibility(View.INVISIBLE);
            } else if(current_item.getMode().equals("i_give_gift")){
                holder.text_in_middle.setVisibility(View.VISIBLE);
                holder.text_in_middle.setText("You gifted the other user ".concat(current_item.get_got_messages()).concat(" months of premium!!"));
                holder.got.setText("");
                holder.got_bubble.setVisibility(View.INVISIBLE);
                holder.send.setText("");
                holder.send_bubble.setVisibility(View.INVISIBLE);
            }
        }
        if ((current_item.get_got_messages().equals("they_left") && current_item.get_send_messages().equals("they_left")) || (current_item.get_got_messages().equals("time_finish") && current_item.get_send_messages().equals("time_finish")) || (current_item.get_got_messages().equals("i_left") && current_item.get_send_messages().equals("i_left")) || (current_item.get_got_messages().contains("987456321_i_am_the_giver") && current_item.get_send_messages().contains("987456321_i_am_the_giver")) || (current_item.get_got_messages().contains("123457_hey_there_i_am_giving_you_") && current_item.get_send_messages().contains("123457_hey_there_i_am_giving_you_"))) {
            if ((current_item.get_got_messages().equals("they_left") && current_item.get_send_messages().equals("they_left"))) {
                holder.text_in_middle.setVisibility(View.VISIBLE);
                holder.text_in_middle.setText("The other user has left the chat.");
                holder.got.setText("");
                holder.got_bubble.setVisibility(View.INVISIBLE);
                holder.send.setText("");
                holder.send_bubble.setVisibility(View.INVISIBLE);
            } else if ((current_item.get_got_messages().equals("time_finish") && current_item.get_send_messages().equals("time_finish"))) {
                holder.text_in_middle.setVisibility(View.VISIBLE);
                holder.text_in_middle.setText("Time ran out. Hope to see you again.");
                holder.got.setText("");
                holder.got_bubble.setVisibility(View.INVISIBLE);
                holder.send.setText("");
                holder.send_bubble.setVisibility(View.INVISIBLE);
            } else if ((current_item.get_got_messages().equals("i_left") && current_item.get_send_messages().equals("i_left"))) {
                holder.text_in_middle.setVisibility(View.VISIBLE);
                holder.text_in_middle.setText("You left the chat");
                holder.got.setText("");
                holder.got_bubble.setVisibility(View.INVISIBLE);
                holder.send.setText("");
                holder.send_bubble.setVisibility(View.INVISIBLE);
            } else if (current_item.get_got_messages().contains("987456321_i_am_the_giver") && current_item.get_send_messages().contains("987456321_i_am_the_giver")) {
                String months = current_item.get_got_messages().replace("987456321_i_am_the_giver", "");
                holder.text_in_middle.setVisibility(View.VISIBLE);
                holder.text_in_middle.setText("You gifted the other user ".concat(months).concat(" months of premium!!"));
                holder.got.setText("");
                holder.got_bubble.setVisibility(View.INVISIBLE);
                holder.send.setText("");
                holder.send_bubble.setVisibility(View.INVISIBLE);
            } else if ((current_item.get_got_messages().contains("123457_hey_there_i_am_giving_you_") && current_item.get_send_messages().contains("123457_hey_there_i_am_giving_you_"))) {
                String months = current_item.get_got_messages().replace("987456321_i_am_the_giver", "");
                holder.text_in_middle.setVisibility(View.VISIBLE);
                holder.text_in_middle.setText("The other user has gifted you ".concat(months).concat(" months of premium!!"));
                holder.got.setText("");
                holder.got_bubble.setVisibility(View.INVISIBLE);
                holder.send.setText("");
                holder.send_bubble.setVisibility(View.INVISIBLE);
            }
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return m_item_for_chats.size();
    }

    public static class chat_view_holder extends RecyclerView.ViewHolder {
        public TextView send;
        public TextView got;
        public View got_bubble;
        public View send_bubble;
        public TextView text_in_middle;

        public chat_view_holder(@NonNull View itemView) {
            super(itemView);
            got = itemView.findViewById(R.id.text_from_sender);
            send = itemView.findViewById(R.id.text_saying_secomd_example_hey);
            got_bubble = itemView.findViewById(R.id.chat_bubble_sender);
            send_bubble = itemView.findViewById(R.id.chat_bubble_from_me);
            text_in_middle = itemView.findViewById(R.id.text_sending_message_in_recycle);
        }
    }
}
