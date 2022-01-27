package com.easyhabitsapp.android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Adapter_for_comment_reply extends RecyclerView.Adapter<Adapter_for_comment_reply.ExampleViewHolder> {
    private ArrayList<Example_item_for_comments_replies> m_example_list;
    private card_was_clicked_comment card_was_clicked_comment_listen;
    private card_was_clicked_reply card_was_clicked_reply_listen;

    public void set_card_clicked_comment(card_was_clicked_comment listen) {
        card_was_clicked_comment_listen = listen;
    }

    public interface card_was_clicked_comment {
        void card_is_clicked_comment(String document_id,String name_in_the_main, String title_of_the_main, String body_of_the_main_post, String span_the_main, long time_the_main, String category_of_the_main, long streak_of_the_main_post, String body_of_the_comment, int position_of_the_comment, String name_of_the_comment, long time_of_the_comment, String category, long streak_of_the_comment);
    }

    public void set_card_clicked_reply(card_was_clicked_reply listen) {
        card_was_clicked_reply_listen = listen;
    }

    public interface card_was_clicked_reply {
        void card_is_clicked_replly(String document_id,String name_in_the_main, String title_of_the_main, String body_of_the_main_post, String span_the_main, long time_the_main, String category_of_the_main, long streak_of_the_main_post, String body_of_the_comment, int position_of_the_comment, String name_of_the_comment, long time_of_the_comment, String category, long streak_of_the_comment, String body_of_the_reply, int position_of_the_reply, String name_of_the_reply, long time_of_the_reply, String category_reply, long streak_of_the_reply);
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_comment_and_reply, parent, false);
        ExampleViewHolder exampleViewHolder = new ExampleViewHolder(view);
        return exampleViewHolder;
    }

    public Adapter_for_comment_reply(ArrayList<Example_item_for_comments_replies> Example_reply_to_comment) {
        m_example_list = Example_reply_to_comment;
    }

    @Override
    public void onBindViewHolder(@NonNull final ExampleViewHolder holder, final int position) {
        final Example_item_for_comments_replies current_item = m_example_list.get(position);
        set_title(holder, current_item);
        set_body(holder, current_item);
        card_click_listen(holder, current_item);
        set_up_date(holder, current_item);
        set_the_name(holder, current_item);
        set_category(holder,current_item);
        set_streak(holder,current_item);

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
        public TextView title_for_post_in_posts_to_show_for_public;
        public TextView text_for_post_reply_or_comment_card_item;
        public CardView card_showing_the_non_loading_comment_reply;
        public TextView name_of_the_replier_in_card_in_reply;
        public TextView text_at_top_left_of_card_to_show_time_comment_reply;
        public TextView text_at_the_top_showing_what_category_this_is_in_comment_reply;
        public TextView text_at_the_top_showing_how_much_is_the_streak_comment_reply;
        public View circle_between_time_and_cat_in_streak_in_post_comment_reply;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            title_for_post_in_posts_to_show_for_public = itemView.findViewById(R.id.title_for_post_in_posts_to_show_for_public);
            text_for_post_reply_or_comment_card_item = itemView.findViewById(R.id.text_for_post_reply_or_comment_card_item);
            card_showing_the_non_loading_comment_reply = itemView.findViewById(R.id.card_showing_the_non_loading_comment_reply);
            name_of_the_replier_in_card_in_reply = itemView.findViewById(R.id.name_of_the_replier_in_card_in_reply);
            text_at_top_left_of_card_to_show_time_comment_reply = itemView.findViewById(R.id.text_at_top_left_of_card_to_show_time_comment_reply);
            text_at_the_top_showing_what_category_this_is_in_comment_reply = itemView.findViewById(R.id.text_at_the_top_showing_what_category_this_is_in_comment_reply);
            text_at_the_top_showing_how_much_is_the_streak_comment_reply = itemView.findViewById(R.id.text_at_the_top_showing_how_much_is_the_streak_comment_reply);
            circle_between_time_and_cat_in_streak_in_post_comment_reply = itemView.findViewById(R.id.circle_between_time_and_cat_in_streak_in_post_comment_reply);
        }
    }

    private void set_title(final ExampleViewHolder holder, final Example_item_for_comments_replies current_item) {
        holder.title_for_post_in_posts_to_show_for_public.setText(make_first_letter_cap(current_item.getTitle_of_the_main()));
    }

    private void set_body(final ExampleViewHolder holder, final Example_item_for_comments_replies current_item) {
        if (current_item.getComment_or_reply().equals("comment")) {
            holder.text_for_post_reply_or_comment_card_item.setText(make_first_letter_cap(current_item.getBody_of_the_comment()));
        } else if (current_item.getComment_or_reply().equals("reply")) {
            holder.text_for_post_reply_or_comment_card_item.setText(make_first_letter_cap(current_item.getBody_of_the_reply()));
        }
    }

    private String make_first_letter_cap(String title) {
        return title.substring(0, 1).toUpperCase() + title.substring(1);
    }

    private void card_click_listen(final ExampleViewHolder holder, final Example_item_for_comments_replies current_item) {
        holder.card_showing_the_non_loading_comment_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (current_item.getComment_or_reply().equals("comment")) {
                    card_was_clicked_comment_listen.card_is_clicked_comment(current_item.getDocument_id(),current_item.getName_in_the_main(), current_item.getTitle_of_the_main(), current_item.getBody_of_the_main_post(), current_item.getSpan_the_main(), current_item.getTime_the_main(), current_item.getCategory_of_the_main(), current_item.getStreak_of_the_main_post(), current_item.getBody_of_the_comment(), current_item.getPosition_of_the_comment(), current_item.getName_of_the_comment(), current_item.getTime_of_the_comment(), current_item.getCategory(), current_item.getStreak_of_the_comment());
                } else if (current_item.getComment_or_reply().equals("reply")) {
                    card_was_clicked_reply_listen.card_is_clicked_replly(current_item.getDocument_id(),current_item.getName_in_the_main(), current_item.getTitle_of_the_main(), current_item.getBody_of_the_main_post(), current_item.getSpan_the_main(), current_item.getTime_the_main(), current_item.getCategory_of_the_main(), current_item.getStreak_of_the_main_post(), current_item.getBody_of_the_comment(), current_item.getPosition_of_the_comment(), current_item.getName_of_the_comment(), current_item.getTime_of_the_comment(), current_item.getCategory(), current_item.getStreak_of_the_comment(), current_item.getBody_of_the_reply(), current_item.getPosition_of_the_reply(), current_item.getName_of_the_reply(), current_item.getTime_of_the_reply(), current_item.getCategory_reply(), current_item.getStreak_of_the_reply());
                }
            }
        });
    }

    private void set_up_date(final ExampleViewHolder holder, final Example_item_for_comments_replies current_item) {
        if (current_item.getComment_or_reply().equals("comment")) {
            holder.text_at_top_left_of_card_to_show_time_comment_reply.setText(return_time_at_top(current_item.getTime_of_the_comment()));
        } else if (current_item.getComment_or_reply().equals("reply")) {
            holder.text_at_top_left_of_card_to_show_time_comment_reply.setText(return_time_at_top(current_item.getTime_of_the_reply()));
        }
    }

    private String return_time_at_top(long differnce) {
        differnce = System.currentTimeMillis() - differnce;
        if (differnce < 3600000L) {
            int time = (int) TimeUnit.MILLISECONDS.toMinutes(differnce);
            if (time == 0 || time == 1) {
                return "1m";
            } else {
                return String.valueOf(time).concat("m");
            }
        } else if (differnce < 86400000L) {
            int time = (int) TimeUnit.MILLISECONDS.toHours(differnce);
            if (time == 0 || time == 1) {
                return "1h";
            } else {
                return String.valueOf(time).concat("h");
            }
        } else {
            int time = (int) TimeUnit.MILLISECONDS.toDays(differnce);
            if (time == 0 || time == 1) {
                return "1d";
            } else {
                return String.valueOf(time).concat("d");
            }
        }
    }

    private void set_the_name(final ExampleViewHolder holder, final Example_item_for_comments_replies current_item) {
        if (current_item.getComment_or_reply().equals("comment")) {
            holder.name_of_the_replier_in_card_in_reply.setText(current_item.getName_of_the_comment());
        } else if (current_item.getComment_or_reply().equals("reply")) {
            holder.name_of_the_replier_in_card_in_reply.setText(current_item.getName_of_the_reply());
        }
    }

    private void set_category(final ExampleViewHolder holder, final Example_item_for_comments_replies current_item) {
        if (current_item.getComment_or_reply().equals("comment")) {
            holder.text_at_the_top_showing_what_category_this_is_in_comment_reply.setText(current_item.getCategory());
        } else if (current_item.getComment_or_reply().equals("reply")) {
            holder.text_at_the_top_showing_what_category_this_is_in_comment_reply.setText(current_item.getCategory_reply());
        }
    }

    private void set_streak(final ExampleViewHolder holder, final Example_item_for_comments_replies current_item){
        if (current_item.getComment_or_reply().equals("comment")) {
            if(current_item.getStreak_of_the_comment() != -1){
                holder.text_at_the_top_showing_how_much_is_the_streak_comment_reply.setText(String.valueOf(current_item.getStreak_of_the_comment()));
                holder.circle_between_time_and_cat_in_streak_in_post_comment_reply.setVisibility(View.VISIBLE);
                holder.text_at_the_top_showing_how_much_is_the_streak_comment_reply.setVisibility(View.VISIBLE);
            } else {
                holder.circle_between_time_and_cat_in_streak_in_post_comment_reply.setVisibility(View.INVISIBLE);
                holder.text_at_the_top_showing_how_much_is_the_streak_comment_reply.setVisibility(View.INVISIBLE);
            }
        } else if (current_item.getComment_or_reply().equals("reply")) {
            if (current_item.getStreak_of_the_reply() != -1) {
                holder.text_at_the_top_showing_how_much_is_the_streak_comment_reply.setText(String.valueOf(current_item.getStreak_of_the_reply()));
                holder.circle_between_time_and_cat_in_streak_in_post_comment_reply.setVisibility(View.VISIBLE);
                holder.text_at_the_top_showing_how_much_is_the_streak_comment_reply.setVisibility(View.VISIBLE);
            } else {
                holder.circle_between_time_and_cat_in_streak_in_post_comment_reply.setVisibility(View.INVISIBLE);
                holder.text_at_the_top_showing_how_much_is_the_streak_comment_reply.setVisibility(View.INVISIBLE);
            }
        }
    }
}
