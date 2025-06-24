package com.easyhabitsapp.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.PopupMenu;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static android.content.Context.MODE_PRIVATE;

public class Adapter_for_comment_replies extends RecyclerView.Adapter<Adapter_for_comment_replies.ExampleViewHolder> {
    private ArrayList<Example_reply_to_comment> m_example_list;
    private share_button_was_clicked_nested share_button_was_clicked_nested_var;
    private reply_button_was_clicked reply_button_was_clicked_listen;
    private Up_vote_down_vote_clicked up_vote_down_vote_clicked_listen;
    private gift_button_listen gift_button_listen_listener;
    private save_only_for_pro_lsiten save_only_for_pro_lsiten_listen;
    private hide_for_reply hide_for_reply_listen;

    public void set_reply_clicked_listen(share_button_was_clicked_nested listen) {
        share_button_was_clicked_nested_var = listen;
    }

    public interface share_button_was_clicked_nested {
        void share_button_nested(String comment);
    }

    public void set_reply_button_clicked_lsiten(reply_button_was_clicked listen) {
        reply_button_was_clicked_listen = listen;
    }

    public interface reply_button_was_clicked {
        void reply_button_clicked(String body, String name, String main_comment_reply_id);
    }

    public void set_up_vote_down_vote_clicked(Up_vote_down_vote_clicked listen) {
        up_vote_down_vote_clicked_listen = listen;
    }

    public interface Up_vote_down_vote_clicked {
        void up_vote_down_vote_button_clicked(String body);
    }

    public void set_up_gift_listen(gift_button_listen listen) {
        gift_button_listen_listener = listen;
    }

    public interface gift_button_listen {
        void gift_was_clicked(String user_id,String document_id,int comment_position,int reply_position,ArrayList<Long> awards,int recycle_view_reply_position);
    }

    public void set_up_save_is_only_for_bro(save_only_for_pro_lsiten listen) {
        save_only_for_pro_lsiten_listen = listen;
    }

    public interface save_only_for_pro_lsiten {
        void save_is_only_for_pro();
    }

    public void set_up_hide_for_reply(hide_for_reply listen) {
        hide_for_reply_listen = listen;
    }

    public interface hide_for_reply {
        void hide_for_reply(int position);
    }


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_comment_reply, parent, false);
        ExampleViewHolder exampleViewHolder = new ExampleViewHolder(view);
        return exampleViewHolder;
    }

    public Adapter_for_comment_replies(ArrayList<Example_reply_to_comment> Example_reply_to_comment) {
        m_example_list = Example_reply_to_comment;
    }

    @Override
    public void onBindViewHolder(@NonNull final ExampleViewHolder holder, final int position) {
        final Example_reply_to_comment current_item = m_example_list.get(position);
        if (current_item.isIs_this_archived()) {
            set_likes_to_one(holder, current_item);
            set_body(holder, current_item);
            set_time(holder, current_item);
//            set_category(holder, current_item);
//            set_streak(holder, current_item);
            set_dev_to_false(holder, current_item);
            set_the_line_at_the_left(holder, current_item);
            set_awards_to_zero(holder, current_item);
            set_the_name_of_the_reply(holder, current_item);
            watch_the_buttons_for_archieved(holder, current_item);
            set_the_save(holder,current_item);
            three_dot_button(holder, current_item);
        } else {
            set_likes(holder, current_item);
            set_body(holder, current_item);
            set_time(holder, current_item);
//            set_category(holder, current_item);
//            set_streak(holder, current_item);
            set_dev(holder, current_item);
            set_the_line_at_the_left(holder, current_item);
            set_awards(holder, current_item);
            set_the_name_of_the_reply(holder, current_item);
            click_the_up_vote(holder, current_item);
            click_the_down_vote(holder, current_item);
            save_the_up_vote_and_down_vote(holder, current_item, 0);
            three_dot_button(holder, current_item);
            share_button_watch(holder, current_item);
            save_button_watch(holder, current_item);
            check_if_comment_is_saved(holder, current_item);
            reply_button_watch(holder, current_item);
            set_the_save(holder,current_item);
            gift_button_listen(holder,current_item);
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
        public TextView text_showing_the_number_of_up_votes_comment_reply;
        public TextView actual_text_to_show_post_in_card_comment_reply;
        public TextView text_at_top_left_of_card_to_show_time_comment_reply;
//        public TextView text_at_the_top_showing_what_category_this_is_in_comment_reply;
//        public TextView text_at_the_top_showing_how_much_is_the_streak_comment_reply;
        public ConstraintLayout constriant_inside_card_inside_post_feed_comment_reply;
//        public View circle_between_time_and_cat_in_streak_in_post_comment_reply;
        public TextView text_view_saying_i_am_the_dev_of_the_app_comment_reply;
        public View view_behind_i_am_the_dev_of_the_text_comment_reply;
        public View circle_between_the_streak_and_the_dev_icon_comment_reply;
        public View line_at_the_start_of_reply_in_the_comment_section;
        public ConstraintLayout main_layout_in_posts_in_card_in_item_comment_reply;
        public View twenty_five_percent_in_reply;
        public View seventy_five_percent_in_reply;
        public View start_part_of_the_grey_part_in_post_award_comment_reply;
        public View middle_part_of_the_grey_part_in_post_award_comment_reply;
        public View end_part_of_the_grey_part_in_post_award_comment_reply;
        public TextView text_saying_the_number_of_awards_comment_reply;
        public View circle_between_awards_and_plat_in_post_comment_reply;
        public View view_showing_the_plat_award_comment_reply;
        public TextView plat_text_beside_the_award_posts_comment_reply;
        public View circle_between_plat_and_gold_in_post_comment_reply;
        public View view_showing_the_gold_award_comment_reply;
        public TextView plat_text_beside_the_gold_award_posts_comment_reply;
        public View circle_between_gold_and_silver_in_post_comment_reply;
        public View view_showing_the_silver_award_comment_reply;
        public TextView silver_text_beside_the_award_post_comment_reply;
        public View three_dots_at_the_top_right_of_post_comment_reply;
        public TextView name_of_the_replier_in_card_in_reply;
        public Button button_to_watch_upvote_in_card_in_post_comment_reply;
        public Button button_to_watch_downvote_in_card_in_post_comment_reply;
        public View upvote_view_in_post_card_comment_reply;
        public View down_vote_view_in_post_card_comment_reply;
        public Button button_to_watch_three_dot_in_card_in_post_in_top_comment_reply;
        public Button button_to_watch_share_in_card_in_post_in_top_comment_reply;
        public View save_button_in_card_in_posts_comment_reply;
        public Button button_to_watch_save_in_card_in_post_comment_reply;
        public Button reply_button_in_tem_id_comment_reply;
        public TextView text_with_name_of_reply_in_card_comment;
        public Button button_to_watch_gift_in_card_in_post_comment_reply;
        public View circle_between_time_and_dev_in_card_comment_reply;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            text_showing_the_number_of_up_votes_comment_reply = itemView.findViewById(R.id.text_showing_the_number_of_up_votes_comment_reply);
            actual_text_to_show_post_in_card_comment_reply = itemView.findViewById(R.id.actual_text_to_show_post_in_card_comment_reply);
            text_at_top_left_of_card_to_show_time_comment_reply = itemView.findViewById(R.id.text_at_top_left_of_card_to_show_time_comment_reply);
//            text_at_the_top_showing_what_category_this_is_in_comment_reply  = itemView.findViewById(R.id.text_at_the_top_showing_what_category_this_is_in_comment_reply);
//            text_at_the_top_showing_how_much_is_the_streak_comment_reply = itemView.findViewById(R.id.text_at_the_top_showing_how_much_is_the_streak_comment_reply);
            constriant_inside_card_inside_post_feed_comment_reply = itemView.findViewById(R.id.constriant_inside_card_inside_post_feed_comment_reply);
//            circle_between_time_and_cat_in_streak_in_post_comment_reply = itemView.findViewById(R.id.circle_between_time_and_cat_in_streak_in_post_comment_reply);
            text_view_saying_i_am_the_dev_of_the_app_comment_reply = itemView.findViewById(R.id.text_view_saying_i_am_the_dev_of_the_app_comment_reply);
            view_behind_i_am_the_dev_of_the_text_comment_reply = itemView.findViewById(R.id.view_behind_i_am_the_dev_of_the_text_comment_reply);
//            circle_between_the_streak_and_the_dev_icon_comment_reply = itemView.findViewById(R.id.circle_between_the_streak_and_the_dev_icon_comment_reply);
            line_at_the_start_of_reply_in_the_comment_section = itemView.findViewById(R.id.line_at_the_start_of_reply_in_the_comment_section);
            main_layout_in_posts_in_card_in_item_comment_reply = itemView.findViewById(R.id.main_layout_in_posts_in_card_in_item_comment_reply);
            twenty_five_percent_in_reply = itemView.findViewById(R.id.twenty_five_percent_in_reply);
            seventy_five_percent_in_reply = itemView.findViewById(R.id.seventy_five_percent_in_reply);
            start_part_of_the_grey_part_in_post_award_comment_reply = itemView.findViewById(R.id.start_part_of_the_grey_part_in_post_award_comment_reply);
            middle_part_of_the_grey_part_in_post_award_comment_reply = itemView.findViewById(R.id.middle_part_of_the_grey_part_in_post_award_comment_reply);
            end_part_of_the_grey_part_in_post_award_comment_reply = itemView.findViewById(R.id.end_part_of_the_grey_part_in_post_award_comment_reply);
            text_saying_the_number_of_awards_comment_reply = itemView.findViewById(R.id.text_saying_the_number_of_awards_comment_reply);
            circle_between_awards_and_plat_in_post_comment_reply = itemView.findViewById(R.id.circle_between_awards_and_plat_in_post_comment_reply);
            view_showing_the_plat_award_comment_reply = itemView.findViewById(R.id.view_showing_the_plat_award_comment_reply);
            plat_text_beside_the_award_posts_comment_reply = itemView.findViewById(R.id.plat_text_beside_the_award_posts_comment_reply);
            circle_between_plat_and_gold_in_post_comment_reply = itemView.findViewById(R.id.circle_between_plat_and_gold_in_post_comment_reply);
            view_showing_the_gold_award_comment_reply = itemView.findViewById(R.id.view_showing_the_gold_award_comment_reply);
            plat_text_beside_the_gold_award_posts_comment_reply = itemView.findViewById(R.id.plat_text_beside_the_gold_award_posts_comment_reply);
            circle_between_gold_and_silver_in_post_comment_reply = itemView.findViewById(R.id.circle_between_gold_and_silver_in_post_comment_reply);
            view_showing_the_silver_award_comment_reply = itemView.findViewById(R.id.view_showing_the_silver_award_comment_reply);
            silver_text_beside_the_award_post_comment_reply = itemView.findViewById(R.id.silver_text_beside_the_award_post_comment_reply);
            three_dots_at_the_top_right_of_post_comment_reply = itemView.findViewById(R.id.three_dots_at_the_top_right_of_post_comment_reply);
            name_of_the_replier_in_card_in_reply = itemView.findViewById(R.id.name_of_the_replier_in_card_in_reply);
            button_to_watch_upvote_in_card_in_post_comment_reply = itemView.findViewById(R.id.button_to_watch_upvote_in_card_in_post_comment_reply);
            button_to_watch_downvote_in_card_in_post_comment_reply = itemView.findViewById(R.id.button_to_watch_downvote_in_card_in_post_comment_reply);
            upvote_view_in_post_card_comment_reply = itemView.findViewById(R.id.upvote_view_in_post_card_comment_reply);
            down_vote_view_in_post_card_comment_reply = itemView.findViewById(R.id.down_vote_view_in_post_card_comment_reply);
            button_to_watch_three_dot_in_card_in_post_in_top_comment_reply = itemView.findViewById(R.id.button_to_watch_three_dot_in_card_in_post_in_top_comment_reply);
            button_to_watch_share_in_card_in_post_in_top_comment_reply = itemView.findViewById(R.id.button_to_watch_share_in_card_in_post_in_top_comment_reply);
            save_button_in_card_in_posts_comment_reply = itemView.findViewById(R.id.save_button_in_card_in_posts_comment_reply);
            button_to_watch_save_in_card_in_post_comment_reply = itemView.findViewById(R.id.button_to_watch_save_in_card_in_post_comment_reply);
            reply_button_in_tem_id_comment_reply = itemView.findViewById(R.id.reply_button_in_tem_id_comment_reply);
            text_with_name_of_reply_in_card_comment = itemView.findViewById(R.id.text_with_name_of_reply_in_card_comment);
            button_to_watch_gift_in_card_in_post_comment_reply = itemView.findViewById(R.id.button_to_watch_gift_in_card_in_post_comment_reply);
            circle_between_time_and_dev_in_card_comment_reply = itemView.findViewById(R.id.circle_between_time_and_dev_in_card_comment_reply);
        }
    }

    private void set_likes(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        int likes = current_item.getUp_vote_list().size() - current_item.getDown_vote_list().size();
        current_item.setLikes(current_item.getUp_vote_list().size() - current_item.getDown_vote_list().size());
        if (likes >= 1) {
            holder.text_showing_the_number_of_up_votes_comment_reply.setText(String.valueOf(likes));
        } else {
            holder.text_showing_the_number_of_up_votes_comment_reply.setText("1");
        }
    }

    private void set_body(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        if (current_item.getBody().charAt(0) == '@') {
            int end = 0;
            for (int i = 2; i < current_item.getBody().length(); i++) {
                if (current_item.getBody().charAt(i) == ' ') {
                    end = i;
                    break;
                }
            }
            final String cut_body = current_item.getBody().substring(0, end);
            final String second_cut_body = current_item.getBody().substring(end);
            holder.text_with_name_of_reply_in_card_comment.setText(cut_body);
            final ViewTreeObserver viewTreeObserver = holder.text_with_name_of_reply_in_card_comment.getViewTreeObserver();
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    TextDrawable fav_background = new TextDrawable(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), cut_body, holder.text_with_name_of_reply_in_card_comment.getMeasuredWidth() + 7, holder.text_with_name_of_reply_in_card_comment.getMeasuredHeight() + 5, 14);
                    fav_background.setBounds(0, 0, holder.text_with_name_of_reply_in_card_comment.getMeasuredWidth() + 7, holder.text_with_name_of_reply_in_card_comment.getMeasuredHeight() + 5);
                    SpannableString spannableString = new SpannableString(cut_body);
                    ImageSpan span;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                        span = new ImageSpan(fav_background, ImageSpan.ALIGN_CENTER);
                    } else {
                        span = new ImageSpan(fav_background, ImageSpan.ALIGN_BOTTOM);
                    }
                    spannableString.setSpan(span, 0, cut_body.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    holder.actual_text_to_show_post_in_card_comment_reply.setText(TextUtils.concat(spannableString, second_cut_body));
                }
            });
        } else {
            holder.actual_text_to_show_post_in_card_comment_reply.setText(current_item.getBody());
        }
    }

    private void set_time(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        holder.text_at_top_left_of_card_to_show_time_comment_reply.setText(return_time_at_top(current_item.getDate()));
    }

    private String return_time_at_top(Date date) {
        long differnce = (new Date()).getTime() - date.getTime();
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

    /*private void set_category(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        holder.text_at_the_top_showing_what_category_this_is_in_comment_reply.setText(current_item.getCategory());
    }*/

   /* private void set_streak(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        int streak = current_item.getStreak();
        if (streak < 0) {
            holder.text_at_the_top_showing_how_much_is_the_streak_comment_reply.setVisibility(View.INVISIBLE);
            holder.circle_between_time_and_cat_in_streak_in_post_comment_reply.setVisibility(View.INVISIBLE);
            ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed_comment_reply;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(holder.circle_between_time_and_cat_in_streak_in_post_comment_reply.getId(), ConstraintSet.START, holder.text_at_the_top_showing_how_much_is_the_streak_comment_reply.getId(), ConstraintSet.END, (int) convertDpToPixel(5, holder.constriant_inside_card_inside_post_feed_comment_reply.getContext()));
            constraintSet.applyTo(constraintLayout);
        } else {
            holder.text_at_the_top_showing_how_much_is_the_streak_comment_reply.setVisibility(View.VISIBLE);
            holder.circle_between_time_and_cat_in_streak_in_post_comment_reply.setVisibility(View.VISIBLE);
            if (current_item.getStreak() == 1) {
                holder.text_at_the_top_showing_how_much_is_the_streak_comment_reply.setText("1 day");
            } else {
                holder.text_at_the_top_showing_how_much_is_the_streak_comment_reply.setText(String.valueOf(current_item.getStreak()).concat(" days"));
            }
        }
    }*/

    private static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private void set_dev(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        if (current_item.isDev()) {
            holder.view_behind_i_am_the_dev_of_the_text_comment_reply.setVisibility(View.VISIBLE);
            holder.text_view_saying_i_am_the_dev_of_the_app_comment_reply.setVisibility(View.VISIBLE);
            holder.circle_between_time_and_dev_in_card_comment_reply.setVisibility(View.VISIBLE);
        } else {
            holder.view_behind_i_am_the_dev_of_the_text_comment_reply.setVisibility(View.INVISIBLE);
            holder.text_view_saying_i_am_the_dev_of_the_app_comment_reply.setVisibility(View.INVISIBLE);
            holder.circle_between_time_and_dev_in_card_comment_reply.setVisibility(View.INVISIBLE);
        }
    }

    private void set_the_line_at_the_left(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        ConstraintLayout constraintLayout = holder.main_layout_in_posts_in_card_in_item_comment_reply;
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        if (current_item.getReplies_size() == 1) {
            constraintSet.connect(holder.line_at_the_start_of_reply_in_the_comment_section.getId(), ConstraintSet.TOP, holder.twenty_five_percent_in_reply.getId(), ConstraintSet.BOTTOM, 0);
            constraintSet.connect(holder.line_at_the_start_of_reply_in_the_comment_section.getId(), ConstraintSet.BOTTOM, holder.seventy_five_percent_in_reply.getId(), ConstraintSet.TOP, 0);
            constraintSet.applyTo(constraintLayout);
        } else {
            if (holder.getAdapterPosition() == 0) {
                constraintSet.connect(holder.line_at_the_start_of_reply_in_the_comment_section.getId(), ConstraintSet.TOP, holder.twenty_five_percent_in_reply.getId(), ConstraintSet.BOTTOM, 0);
                constraintSet.connect(holder.line_at_the_start_of_reply_in_the_comment_section.getId(), ConstraintSet.BOTTOM, holder.main_layout_in_posts_in_card_in_item_comment_reply.getId(), ConstraintSet.BOTTOM, 0);
            } else if (holder.getAdapterPosition() == current_item.getReplies_size() - 1) {
                constraintSet.connect(holder.line_at_the_start_of_reply_in_the_comment_section.getId(), ConstraintSet.TOP, holder.main_layout_in_posts_in_card_in_item_comment_reply.getId(), ConstraintSet.TOP, 0);
                constraintSet.connect(holder.line_at_the_start_of_reply_in_the_comment_section.getId(), ConstraintSet.BOTTOM, holder.seventy_five_percent_in_reply.getId(), ConstraintSet.TOP, 0);
            } else {
                constraintSet.connect(holder.line_at_the_start_of_reply_in_the_comment_section.getId(), ConstraintSet.TOP, holder.main_layout_in_posts_in_card_in_item_comment_reply.getId(), ConstraintSet.TOP, 0);
                constraintSet.connect(holder.line_at_the_start_of_reply_in_the_comment_section.getId(), ConstraintSet.BOTTOM, holder.main_layout_in_posts_in_card_in_item_comment_reply.getId(), ConstraintSet.BOTTOM, 0);
            }
            constraintSet.applyTo(constraintLayout);
        }
    }

    private void set_awards(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        long plat_award = current_item.getAwards().get(0);
        long gold_award = current_item.getAwards().get(1);
        long silver_award = current_item.getAwards().get(2);
        if (plat_award == 0 && gold_award == 0 && silver_award == 0) {
            make_the_awards_be_invisble(holder);
            ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed_comment_reply;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(holder.actual_text_to_show_post_in_card_comment_reply.getId(), ConstraintSet.TOP, holder.three_dots_at_the_top_right_of_post_comment_reply.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(5, holder.constriant_inside_card_inside_post_feed_comment_reply.getContext()));
            constraintSet.applyTo(constraintLayout);
        } else {
            make_every_award_visible(holder);
            {
                ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed_comment_reply;
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(holder.actual_text_to_show_post_in_card_comment_reply.getId(), ConstraintSet.TOP, holder.text_saying_the_number_of_awards_comment_reply.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(15, holder.actual_text_to_show_post_in_card_comment_reply.getContext()));
                constraintSet.applyTo(constraintLayout);
            }
            holder.plat_text_beside_the_award_posts_comment_reply.setText(String.valueOf(plat_award));
            holder.plat_text_beside_the_gold_award_posts_comment_reply.setText(String.valueOf(gold_award));
            holder.silver_text_beside_the_award_post_comment_reply.setText(String.valueOf(silver_award));
            long awards_total_number = plat_award + gold_award + silver_award;
            if (awards_total_number == 1) {
                holder.text_saying_the_number_of_awards_comment_reply.setText("1 award");
            } else {
                holder.text_saying_the_number_of_awards_comment_reply.setText(String.valueOf(awards_total_number).concat(" awards"));
            }
            if (plat_award == 0 && gold_award == 0 && silver_award > 0) {
                make_the_plat_invisible(holder);
                make_the_gold_invisible(holder);
                conect_me_to_start(1, holder);
            } else if (plat_award == 0 && gold_award > 0 && silver_award == 0) {
                make_the_plat_invisible(holder);
                make_the_silver_invisible(holder);
                conect_me_to_start(0, holder);
            } else if (plat_award == 0 && gold_award > 0 && silver_award > 0) {
                make_the_plat_invisible(holder);
                conect_me_to_start(0, holder);
            } else if (plat_award > 0 && gold_award == 0 && silver_award == 0) {
                make_the_gold_invisible(holder);
                make_the_silver_invisible(holder);
            } else if (plat_award > 0 && gold_award == 0 && silver_award > 0) {
                make_the_gold_invisible(holder);
                ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed_comment_reply;
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(holder.circle_between_gold_and_silver_in_post_comment_reply.getId(), ConstraintSet.START, holder.plat_text_beside_the_award_posts_comment_reply.getId(), ConstraintSet.END, (int) convertDpToPixel(5, holder.constriant_inside_card_inside_post_feed_comment_reply.getContext()));
                constraintSet.applyTo(constraintLayout);
            } else if (plat_award > 0 && gold_award > 0 && silver_award == 0) {
                make_the_silver_invisible(holder);
            }
        }
    }

    private void make_the_awards_be_invisble(@NonNull ExampleViewHolder holder) {
        holder.start_part_of_the_grey_part_in_post_award_comment_reply.setVisibility(View.GONE);
        holder.middle_part_of_the_grey_part_in_post_award_comment_reply.setVisibility(View.GONE);
        holder.end_part_of_the_grey_part_in_post_award_comment_reply.setVisibility(View.GONE);
        holder.text_saying_the_number_of_awards_comment_reply.setVisibility(View.GONE);
        holder.circle_between_awards_and_plat_in_post_comment_reply.setVisibility(View.GONE);
        holder.view_showing_the_plat_award_comment_reply.setVisibility(View.GONE);
        holder.plat_text_beside_the_award_posts_comment_reply.setVisibility(View.GONE);
        holder.circle_between_plat_and_gold_in_post_comment_reply.setVisibility(View.GONE);
        holder.view_showing_the_gold_award_comment_reply.setVisibility(View.GONE);
        holder.plat_text_beside_the_gold_award_posts_comment_reply.setVisibility(View.GONE);
        holder.circle_between_gold_and_silver_in_post_comment_reply.setVisibility(View.GONE);
        holder.view_showing_the_silver_award_comment_reply.setVisibility(View.GONE);
        holder.silver_text_beside_the_award_post_comment_reply.setVisibility(View.GONE);
    }

    private void make_every_award_visible(@NonNull ExampleViewHolder holder) {
        holder.start_part_of_the_grey_part_in_post_award_comment_reply.setVisibility(View.VISIBLE);
        holder.middle_part_of_the_grey_part_in_post_award_comment_reply.setVisibility(View.VISIBLE);
        holder.end_part_of_the_grey_part_in_post_award_comment_reply.setVisibility(View.VISIBLE);
        holder.text_saying_the_number_of_awards_comment_reply.setVisibility(View.VISIBLE);
        holder.circle_between_awards_and_plat_in_post_comment_reply.setVisibility(View.VISIBLE);
        holder.view_showing_the_plat_award_comment_reply.setVisibility(View.VISIBLE);
        holder.plat_text_beside_the_award_posts_comment_reply.setVisibility(View.VISIBLE);
        holder.circle_between_plat_and_gold_in_post_comment_reply.setVisibility(View.VISIBLE);
        holder.view_showing_the_gold_award_comment_reply.setVisibility(View.VISIBLE);
        holder.plat_text_beside_the_gold_award_posts_comment_reply.setVisibility(View.VISIBLE);
        holder.circle_between_gold_and_silver_in_post_comment_reply.setVisibility(View.VISIBLE);
        holder.view_showing_the_silver_award_comment_reply.setVisibility(View.VISIBLE);
        holder.silver_text_beside_the_award_post_comment_reply.setVisibility(View.VISIBLE);
    }

    private void make_the_plat_invisible(@NonNull ExampleViewHolder holder) {
        holder.circle_between_awards_and_plat_in_post_comment_reply.setVisibility(View.GONE);
        holder.view_showing_the_plat_award_comment_reply.setVisibility(View.GONE);
        holder.plat_text_beside_the_award_posts_comment_reply.setVisibility(View.GONE);
    }

    private void make_the_gold_invisible(@NonNull ExampleViewHolder holder) {
        holder.circle_between_plat_and_gold_in_post_comment_reply.setVisibility(View.GONE);
        holder.view_showing_the_gold_award_comment_reply.setVisibility(View.GONE);
        holder.plat_text_beside_the_gold_award_posts_comment_reply.setVisibility(View.GONE);
    }

    private void make_the_silver_invisible(@NonNull ExampleViewHolder holder) {
        holder.circle_between_gold_and_silver_in_post_comment_reply.setVisibility(View.GONE);
        holder.view_showing_the_silver_award_comment_reply.setVisibility(View.GONE);
        holder.silver_text_beside_the_award_post_comment_reply.setVisibility(View.GONE);
    }

    private void conect_me_to_start(int which, @NonNull ExampleViewHolder holder) {
        if (which == 1) {
            ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed_comment_reply;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(holder.circle_between_plat_and_gold_in_post_comment_reply.getId(), ConstraintSet.START, holder.text_saying_the_number_of_awards_comment_reply.getId(), ConstraintSet.END, (int) convertDpToPixel(5, holder.constriant_inside_card_inside_post_feed_comment_reply.getContext()));
            constraintSet.applyTo(constraintLayout);
        } else if (which == 2) {
            ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed_comment_reply;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(holder.circle_between_gold_and_silver_in_post_comment_reply.getId(), ConstraintSet.START, holder.text_saying_the_number_of_awards_comment_reply.getId(), ConstraintSet.END, (int) convertDpToPixel(5, holder.constriant_inside_card_inside_post_feed_comment_reply.getContext()));
            constraintSet.applyTo(constraintLayout);
        }
    }

    private void set_the_name_of_the_reply(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        holder.name_of_the_replier_in_card_in_reply.setText(current_item.getName());
    }

    private void click_the_up_vote(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        holder.button_to_watch_upvote_in_card_in_post_comment_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event_manager_all_in_one.getInstance().record_fire_base_event(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), Event_manager_all_in_one.Event_type_fire_base_record.reply_upvote,false);
                if (am_i_signed_in_with_google(current_item)) {
                    boolean up_vote = current_item.isUp_vote_clicked();
                    boolean down_vote = current_item.isDown_vote_clicked();
                    if (!current_item.isAm_i_loading()) {
                        if (up_vote) {
                            add_or_remove_likes(holder, current_item, 0);
                            remove_the_up_vote(holder, current_item, false);
                            set_up_the_upvote_and_the_down_vote(holder, current_item, "neither");
                            save_the_up_vote_and_down_vote(holder, current_item, 3);
                        } else if (down_vote) {
                            add_or_remove_likes(holder, current_item, 3);
                            remove_the_down_vote(holder, current_item, true);
                            set_up_the_upvote_and_the_down_vote(holder, current_item, "up");
                            save_the_up_vote_and_down_vote(holder, current_item, 1);
                        } else {
                            add_or_remove_likes(holder, current_item, 1);
                            put_the_up_vote(holder, current_item);
                            set_up_the_upvote_and_the_down_vote(holder, current_item, "up");
                            save_the_up_vote_and_down_vote(holder, current_item, 1);
                        }
                    } else {
                        Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), "You are up voting and down voting too quickly!!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    up_vote_down_vote_clicked_listen.up_vote_down_vote_button_clicked("up vote");
                }
            }
        });
    }

    private void click_the_down_vote(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        holder.button_to_watch_downvote_in_card_in_post_comment_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event_manager_all_in_one.getInstance().record_fire_base_event(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), Event_manager_all_in_one.Event_type_fire_base_record.reply_downvote,false);
                if (am_i_signed_in_with_google(current_item)) {
                    boolean up_vote = current_item.isUp_vote_clicked();
                    boolean down_vote = current_item.isDown_vote_clicked();
                    if (!current_item.isAm_i_loading()) {
                        if (up_vote) {
                            add_or_remove_likes(holder, current_item, 2);
                            remove_the_up_vote(holder, current_item, true);
                            set_up_the_upvote_and_the_down_vote(holder, current_item, "down");
                            save_the_up_vote_and_down_vote(holder, current_item, 2);
                        } else if (down_vote) {
                            add_or_remove_likes(holder, current_item, 1);
                            remove_the_down_vote(holder, current_item, false);
                            set_up_the_upvote_and_the_down_vote(holder, current_item, "neither");
                            save_the_up_vote_and_down_vote(holder, current_item, 3);
                        } else {
                            add_or_remove_likes(holder, current_item, 0);
                            put_the_down_vote(holder, current_item);
                            set_up_the_upvote_and_the_down_vote(holder, current_item, "down");
                            save_the_up_vote_and_down_vote(holder, current_item, 2);
                        }
                    } else {
                        Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), "You are up voting and down voting too quickly!!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    up_vote_down_vote_clicked_listen.up_vote_down_vote_button_clicked("down vote");
                }
            }
        });
    }

    private void put_the_up_vote(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        final DocumentReference sfDocRef = current_item.getFirebaseFirestore().collection("posts").document(current_item.getDocument_id());
        current_item.getFirebaseFirestore().runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(@NotNull Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);
                ArrayList<Map<String, Object>> array_list_of_map = (ArrayList<Map<String, Object>>) snapshot.get("comments");
                Map<String, Object> map = array_list_of_map.get(current_item.getComment_position());
                ArrayList<Map<String, Object>> array_list_with_replies = (ArrayList<Map<String, Object>>) map.get("replies");
                Map<String, Object> reply_map = array_list_with_replies.get(current_item.getReply_position());
                ArrayList<String> array_with_up_votes = (ArrayList<String>) reply_map.get("up_vote_list");
                array_with_up_votes.add(current_item.return_my_user_id());
                transaction.update(sfDocRef, "comments", array_list_of_map, "type", "reply");
                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                current_item.setAm_i_loading(false);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (!current_item.isAm_i_loading()) {
                    Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), "Cant upvote please try again later", Toast.LENGTH_SHORT);
                    toast.show();
                }
                current_item.setAm_i_loading(false);
            }
        });
    }

    private void remove_the_up_vote(final ExampleViewHolder holder, final Example_reply_to_comment current_item, final boolean run_after_this) {
        final DocumentReference sfDocRef = current_item.getFirebaseFirestore().collection("posts").document(current_item.getDocument_id());
        current_item.getFirebaseFirestore().runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(@NotNull Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);
                ArrayList<Map<String, Object>> array_list_of_map = (ArrayList<Map<String, Object>>) snapshot.get("comments");
                Map<String, Object> map = array_list_of_map.get(current_item.getComment_position());
                ArrayList<Map<String, Object>> array_list_with_replies = (ArrayList<Map<String, Object>>) map.get("replies");
                Map<String, Object> reply_map = array_list_with_replies.get(current_item.getReply_position());
                ArrayList<String> array_with_up_votes = (ArrayList<String>) reply_map.get("up_vote_list");
                array_with_up_votes.remove(current_item.return_my_user_id());
                transaction.update(sfDocRef, "comments", array_list_of_map, "type", "reply");

                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                if (run_after_this) {
                    put_the_down_vote(holder, current_item);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (!current_item.isAm_i_loading()) {
                    Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), "Cant remove up vote please try again later", Toast.LENGTH_SHORT);
                    toast.show();
                }
                current_item.setAm_i_loading(false);
            }
        });
    }

    private void put_the_down_vote(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        final DocumentReference sfDocRef = current_item.getFirebaseFirestore().collection("posts").document(current_item.getDocument_id());
        current_item.getFirebaseFirestore().runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(@NotNull Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);
                ArrayList<Map<String, Object>> array_list_of_map = (ArrayList<Map<String, Object>>) snapshot.get("comments");
                Map<String, Object> map = array_list_of_map.get(current_item.getComment_position());
                ArrayList<Map<String, Object>> array_list_with_replies = (ArrayList<Map<String, Object>>) map.get("replies");
                Map<String, Object> reply_map = array_list_with_replies.get(current_item.getReply_position());
                ArrayList<String> array_with_up_votes = (ArrayList<String>) reply_map.get("down_vote_list");
                array_with_up_votes.add(current_item.return_my_user_id());
                transaction.update(sfDocRef, "comments", array_list_of_map, "type", "reply");
                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                current_item.setAm_i_loading(false);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (!current_item.isAm_i_loading()) {
                    Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), "Cant down vote please try again later", Toast.LENGTH_SHORT);
                    toast.show();
                }
                current_item.setAm_i_loading(false);
            }
        });
    }

    private void remove_the_down_vote(final ExampleViewHolder holder, final Example_reply_to_comment current_item, final boolean start_after_this) {
        final DocumentReference sfDocRef = current_item.getFirebaseFirestore().collection("posts").document(current_item.getDocument_id());
        current_item.getFirebaseFirestore().runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(@NotNull Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);
                ArrayList<Map<String, Object>> array_list_of_map = (ArrayList<Map<String, Object>>) snapshot.get("comments");
                Map<String, Object> map = array_list_of_map.get(current_item.getComment_position());
                ArrayList<Map<String, Object>> array_list_with_replies = (ArrayList<Map<String, Object>>) map.get("replies");
                Map<String, Object> reply_map = array_list_with_replies.get(current_item.getReply_position());
                ArrayList<String> array_with_up_votes = (ArrayList<String>) reply_map.get("down_vote_list");
                array_with_up_votes.remove(current_item.return_my_user_id());
                transaction.update(sfDocRef, "comments", array_list_of_map, "type", "reply");

                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                if (start_after_this) {
                    put_the_up_vote(holder, current_item);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (!current_item.isAm_i_loading()) {
                    Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), "Cant remove down vote please try again later", Toast.LENGTH_SHORT);
                    toast.show();
                }
                current_item.setAm_i_loading(false);
            }
        });
    }

    private void set_up_the_upvote_and_the_down_vote(final ExampleViewHolder holder, final Example_reply_to_comment current_item, String up_vote_down_vote) {
        if (up_vote_down_vote.equals("up")) {
            {
                holder.text_showing_the_number_of_up_votes_comment_reply.setTextColor(Color.parseColor("#FF4500"));
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), R.drawable.round_arrow_drop_up_24).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#FF4500"));
                holder.upvote_view_in_post_card_comment_reply.setBackground(wrappedDrawable);
            }
            {
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), R.drawable.arrow_down_down_vote_card).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#808080"));
                holder.down_vote_view_in_post_card_comment_reply.setBackground(wrappedDrawable);
            }
        } else if (up_vote_down_vote.equals("down")) {
            {
                holder.text_showing_the_number_of_up_votes_comment_reply.setTextColor(Color.parseColor("#9494FF"));
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), R.drawable.round_arrow_drop_up_24).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#808080"));
                holder.upvote_view_in_post_card_comment_reply.setBackground(wrappedDrawable);
            }
            {
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), R.drawable.arrow_down_down_vote_card).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#9494FF"));
                holder.down_vote_view_in_post_card_comment_reply.setBackground(wrappedDrawable);
            }
        } else {
            {
                holder.text_showing_the_number_of_up_votes_comment_reply.setTextColor(Color.parseColor("#808080"));
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), R.drawable.round_arrow_drop_up_24).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#808080"));
                holder.upvote_view_in_post_card_comment_reply.setBackground(wrappedDrawable);
            }
            {
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), R.drawable.arrow_down_down_vote_card).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#808080"));
                holder.down_vote_view_in_post_card_comment_reply.setBackground(wrappedDrawable);
            }
        }
    }

    private void save_the_up_vote_and_down_vote(final ExampleViewHolder holder, final Example_reply_to_comment current_item, int mode) {
        if (mode == 0) {
            // initial
            if (current_item.getUp_vote_list().contains(current_item.return_my_user_id())) {
                current_item.setUp_vote_clicked(true);
                current_item.setDown_vote_clicked(false);
                set_up_the_upvote_and_the_down_vote(holder, current_item, "up");
            } else if (current_item.getDown_vote_list().contains(current_item.return_my_user_id())) {
                current_item.setUp_vote_clicked(false);
                current_item.setDown_vote_clicked(true);
                set_up_the_upvote_and_the_down_vote(holder, current_item, "down");
            } else {
                current_item.setUp_vote_clicked(false);
                current_item.setDown_vote_clicked(false);
                set_up_the_upvote_and_the_down_vote(holder, current_item, "neither");
            }
        } else if (mode == 1) {
            current_item.setUp_vote_clicked(true);
            current_item.setDown_vote_clicked(false);
            set_up_the_upvote_and_the_down_vote(holder, current_item, "up");
        } else if (mode == 2) {
            current_item.setUp_vote_clicked(false);
            current_item.setDown_vote_clicked(true);
            set_up_the_upvote_and_the_down_vote(holder, current_item, "down");
        } else if (mode == 3) {
            current_item.setUp_vote_clicked(false);
            current_item.setDown_vote_clicked(false);
            set_up_the_upvote_and_the_down_vote(holder, current_item, "neither");
        }
    }

    private void add_or_remove_likes(final ExampleViewHolder holder, final Example_reply_to_comment current_item, int mode) {
        int likes = current_item.getLikes();
        if (mode == 0) {
            likes--;
        } else if (mode == 1) {
            likes++;
        } else if (mode == 2) {
            likes = likes - 2;
        } else if (mode == 3) {
            likes = likes + 2;
        }
        if (likes >= 1) {
            holder.text_showing_the_number_of_up_votes_comment_reply.setText(String.valueOf(likes));
        } else {
            holder.text_showing_the_number_of_up_votes_comment_reply.setText("1");
            Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), "Recorded but not shown publicly", Toast.LENGTH_SHORT);
            toast.show();
        }
        current_item.setLikes(likes);
    }

    private void three_dot_button(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        holder.button_to_watch_three_dot_in_card_in_post_in_top_comment_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu_clicked(holder, current_item);
            }
        });
    }

    private void menu_clicked(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        PopupMenu popupMenu = new PopupMenu(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), holder.button_to_watch_three_dot_in_card_in_post_in_top_comment_reply);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getOrder() == 0) {
                    Event_manager_all_in_one.getInstance().record_fire_base_event(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), Event_manager_all_in_one.Event_type_fire_base_record.reply_reported,false);
                    if (current_item.isIs_this_archived()) {
                        Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), "Can't report archived posts", Toast.LENGTH_SHORT).show();
                    } else {
                        if (am_i_signed_in_with_google(current_item)) {
                            if (current_item.isIs_this_reported() || current_item.getReports().contains(current_item.return_my_user_id())) {
                                Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), "Already reported", Toast.LENGTH_SHORT);
                                toast.show();
                            } else {
                                new AlertDialog.Builder(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext())
                                        .setTitle("Report comment?")
                                        .setMessage("Are you sure you want to comment this post?")

                                        // Specifying a listener allows you to take an action before dismissing the dialog.
                                        // The dialog is automatically dismissed when a dialog button is clicked.
                                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                report_comment(holder, current_item);
                                            }
                                        })
                                        .setNegativeButton("cancel", null)
                                        .show();
                            }
                        } else {
                            up_vote_down_vote_clicked_listen.up_vote_down_vote_button_clicked("report");
                        }
                    }
                } else if(item.getOrder()==1){
                    Alert_dialog_show alert_dialog_show = new Alert_dialog_show();
                    alert_dialog_show.show_alert_dialog(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(),"Hide Content?","Are you sure you want to hide this content?");
                    alert_dialog_show.set_ok_button_listen(new Alert_dialog_show.ok_button_clicked() {
                        @Override
                        public void ok_button_clicked() {
                            String user_id = current_item.getUser_id();
                            String body = current_item.getBody();
                            Save_and_get.getInstance().save_this(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(),user_id.concat("small_split_for_save_and_get").concat(body),"hide_reply",true);
                            Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(),"Content hidden",Toast.LENGTH_SHORT).show();
                            hide_for_reply_listen.hide_for_reply(holder.getAdapterPosition());
                        }
                    });
                } else if(item.getOrder()==2){
                    Alert_dialog_show alert_dialog_show = new Alert_dialog_show();
                    alert_dialog_show.show_alert_dialog(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(),"Block User?","Are you sure you want to block this user?");
                    alert_dialog_show.set_ok_button_listen(new Alert_dialog_show.ok_button_clicked() {
                        @Override
                        public void ok_button_clicked() {
                            if(current_item.getFirebaseUser().getUid().equals(current_item.getUser_id())){
                                Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(),"You can't block yourself!",Toast.LENGTH_LONG).show();
                            } else {
                                /*if (current_item.isSaved() || does_commetn_already_saved(holder, current_item)) {
                                    remove_the_comment(holder, current_item);
                                    current_item.setSaved(false);
                                }*/
                                Save_and_get.getInstance().save_this(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(),current_item.getUser_id(),"blocked_users",true);
                                Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(),"User blocked",Toast.LENGTH_SHORT).show();
                                hide_for_reply_listen.hide_for_reply(holder.getAdapterPosition());
                            }
                        }
                    });
                }
                return true;
            }
        });
        popupMenu.inflate(R.menu.three_dot_comment_post);
        popupMenu.show();
    }

    private void report_comment(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        final DocumentReference sfDocRef = current_item.getFirebaseFirestore().collection("posts").document(current_item.getDocument_id());
        current_item.getFirebaseFirestore().runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);
                ArrayList<Map<String, Object>> array_list_of_map = (ArrayList<Map<String, Object>>) snapshot.get("comments");
                Map<String, Object> map = array_list_of_map.get(current_item.getComment_position());
                ArrayList<Map<String, Object>> array_list_with_replies = (ArrayList<Map<String, Object>>) map.get("replies");
                Map<String, Object> reply_map = array_list_with_replies.get(current_item.getReply_position());
                ArrayList<String> array_with_reports = (ArrayList<String>) reply_map.get("reports");
                array_with_reports.add(current_item.return_my_user_id());
                transaction.update(sfDocRef, "comments", array_list_of_map, "type", "reply");

                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), "Thanks, your input was received and greatly appreciated!!", Toast.LENGTH_LONG);
                toast.show();
                current_item.setIs_this_reported(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), "Can't report right now", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void share_button_watch(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        holder.button_to_watch_share_in_card_in_post_in_top_comment_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event_manager_all_in_one.getInstance().record_fire_base_event(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), Event_manager_all_in_one.Event_type_fire_base_record.reply_shared,false);
                share_button_was_clicked_nested_var.share_button_nested(holder.actual_text_to_show_post_in_card_comment_reply.getText().toString().trim());
            }
        });
    }

    private void save_button_pressed(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        if (current_item.isIs_this_saved() || is_this_saved(holder, current_item)) {
            remove_the_icon(holder, current_item);
            current_item.setIs_this_saved(false);
            remove_the_reply_save(holder, current_item);
        } else {
            if (Payment_processer.getInstance().state_of_the_user()) {
                save_the_icon(holder, current_item);
                current_item.setIs_this_saved(true);
                save_the_comment_reply(holder, current_item);
            } else {
                save_only_for_pro_lsiten_listen.save_is_only_for_pro();
            }
        }
    }

    private boolean is_this_saved(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        SharedPreferences sharedPreferences = holder.constriant_inside_card_inside_post_feed_comment_reply.getContext().getSharedPreferences("saved_comments", MODE_PRIVATE);
        String old = sharedPreferences.getString("saved_comments", "");
        String document_id = current_item.getDocument_id(); // document id

        // post information
        String name_in_the_main = current_item.getName_of_the_main_post().replace("small_split", "").replace("big_divide", "");
        String title_of_the_main = current_item.getTitle_of_the_main_post();
        String body_of_the_main_post = current_item.getBody_of_the_main_post().replace("small_split", "").replace("big_divide", "");
        String span_the_main = current_item.getSpan_of_the_main_post();
        long time_the_main = current_item.getTime_of_the_main_post().getTime();
//        String category_of_the_main = current_item.getCategory_of_the_main_post();
//        long streak_of_the_main_post = current_item.getStreak_of_the_main_post();

        // comment information
        String body_of_the_comment = current_item.getBody_of_the_comment_post().replace("small_split", "").replace("big_divide", "");
        String position_of_the_comment = String.valueOf(current_item.getComment_position());
        String name_of_the_comment = current_item.getName_of_the_comment_post().replace("small_split", "").replace("big_divide", "");
        long time_of_the_comment = current_item.getTime_of_the_comment_post().getTime();
//        String category = current_item.getCategory_of_the_comment_post();
//        long streak_of_the_comment = current_item.getStreak_of_the_comment_post();

        // reply information
        String body_of_the_reply = current_item.getBody().replace("small_split", "").replace("big_divide", "");
        String position_of_the_reply = String.valueOf(current_item.getReply_position());
        String name_of_the_reply = current_item.getName().replace("small_split", "").replace("big_divide", "");
        long time_of_the_reply = current_item.getDate().getTime();
//        String category_reply = current_item.getCategory();
//        long streak_of_the_reply = current_item.getStreak();

        String all = document_id.concat("small_split").concat(name_in_the_main).concat("small_split").concat(title_of_the_main).concat("small_split").concat(body_of_the_main_post).concat("small_split").concat(span_the_main).concat("small_split").concat(String.valueOf(time_the_main)).concat("small_split").concat(body_of_the_comment).concat("small_split").concat(position_of_the_comment).concat("small_split").concat(name_of_the_comment).concat("small_split").concat(String.valueOf(time_of_the_comment)).concat("small_split").concat(body_of_the_reply).concat("small_split").concat(position_of_the_reply).concat("small_split").concat(name_of_the_reply).concat("small_split").concat(String.valueOf(time_of_the_reply));

        if (old != null && !old.isEmpty()) {
            String[] big_spit = old.split("big_divide");
            for (int i = 0; i < big_spit.length; i++) {
                if (big_spit[i].equals(all)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private void save_the_icon(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        Drawable saved = ContextCompat.getDrawable(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), R.drawable.round_turned_in_24);
        holder.save_button_in_card_in_posts_comment_reply.setBackground(saved);
    }

    private void remove_the_icon(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        Drawable not_saved = ContextCompat.getDrawable(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), R.drawable.round_turned_in_not_24);
        holder.save_button_in_card_in_posts_comment_reply.setBackground(not_saved);
    }

    private void save_the_comment_reply(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        SharedPreferences sharedPreferences = holder.constriant_inside_card_inside_post_feed_comment_reply.getContext().getSharedPreferences("saved_comments", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        String old = sharedPreferences.getString("saved_comments", "");
        String document_id = current_item.getDocument_id(); // document id

        // post information
        String name_in_the_main = current_item.getName_of_the_main_post().replace("small_split", "").replace("big_divide", "");
        String title_of_the_main = current_item.getTitle_of_the_main_post();
        String body_of_the_main_post = current_item.getBody_of_the_main_post().replace("small_split", "").replace("big_divide", "");
        String span_the_main = current_item.getSpan_of_the_main_post();
        long time_the_main = current_item.getTime_of_the_main_post().getTime();
//        String category_of_the_main = current_item.getCategory_of_the_main_post();
//        long streak_of_the_main_post = current_item.getStreak_of_the_main_post();

        // comment information
        String body_of_the_comment = current_item.getBody_of_the_comment_post().replace("small_split", "").replace("big_divide", "");
        String position_of_the_comment = String.valueOf(current_item.getComment_position());
        String name_of_the_comment = current_item.getName_of_the_comment_post().replace("small_split", "").replace("big_divide", "");
        long time_of_the_comment = current_item.getTime_of_the_comment_post().getTime();
//        String category = current_item.getCategory_of_the_comment_post();
//        long streak_of_the_comment = current_item.getStreak_of_the_comment_post();

        // reply information
        String body_of_the_reply = current_item.getBody().replace("small_split", "").replace("big_divide", "");
        String position_of_the_reply = String.valueOf(current_item.getReply_position());
        String name_of_the_reply = current_item.getName().replace("small_split", "").replace("big_divide", "");
        long time_of_the_reply = current_item.getDate().getTime();
//        String category_reply = current_item.getCategory();
//        long streak_of_the_reply = current_item.getStreak();

        String all = document_id.concat("small_split").concat(name_in_the_main).concat("small_split").concat(title_of_the_main).concat("small_split").concat(body_of_the_main_post).concat("small_split").concat(span_the_main).concat("small_split").concat(String.valueOf(time_the_main)).concat("small_split").concat(body_of_the_comment).concat("small_split").concat(position_of_the_comment).concat("small_split").concat(name_of_the_comment).concat("small_split").concat(String.valueOf(time_of_the_comment)).concat("small_split").concat(body_of_the_reply).concat("small_split").concat(position_of_the_reply).concat("small_split").concat(name_of_the_reply).concat("small_split").concat(String.valueOf(time_of_the_reply)).concat("big_divide");
        if (old != null && !old.isEmpty()) {
            myEdit.putString("saved_comments", old.concat(all));
        } else {
            myEdit.putString("saved_comments", all);
        }
        myEdit.commit();
    }

    private void remove_the_reply_save(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        SharedPreferences sharedPreferences = holder.constriant_inside_card_inside_post_feed_comment_reply.getContext().getSharedPreferences("saved_comments", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        String old = sharedPreferences.getString("saved_comments", "");
        String document_id = current_item.getDocument_id(); // document id

        // post information
        String name_in_the_main = current_item.getName_of_the_main_post().replace("small_split", "").replace("big_divide", "");
        String title_of_the_main = current_item.getTitle_of_the_main_post();
        String body_of_the_main_post = current_item.getBody_of_the_main_post().replace("small_split", "").replace("big_divide", "");
        String span_the_main = current_item.getSpan_of_the_main_post();
        long time_the_main = current_item.getTime_of_the_main_post().getTime();
//        String category_of_the_main = current_item.getCategory_of_the_main_post();
//        long streak_of_the_main_post = current_item.getStreak_of_the_main_post();

        // comment information
        String body_of_the_comment = current_item.getBody_of_the_comment_post().replace("small_split", "").replace("big_divide", "");
        String position_of_the_comment = String.valueOf(current_item.getComment_position());
        String name_of_the_comment = current_item.getName_of_the_comment_post().replace("small_split", "").replace("big_divide", "");
        long time_of_the_comment = current_item.getTime_of_the_comment_post().getTime();
//        String category = current_item.getCategory_of_the_comment_post();
//        long streak_of_the_comment = current_item.getStreak_of_the_comment_post();

        // reply information
        String body_of_the_reply = current_item.getBody().replace("small_split", "").replace("big_divide", "");
        String position_of_the_reply = String.valueOf(current_item.getReply_position());
        String name_of_the_reply = current_item.getName().replace("small_split", "").replace("big_divide", "");
        long time_of_the_reply = current_item.getDate().getTime();
//        String category_reply = current_item.getCategory();
//        long streak_of_the_reply = current_item.getStreak();
        String all = document_id.concat("small_split").concat(name_in_the_main).concat("small_split").concat(title_of_the_main).concat("small_split").concat(body_of_the_main_post).concat("small_split").concat(span_the_main).concat("small_split").concat(String.valueOf(time_the_main)).concat("small_split").concat(body_of_the_comment).concat("small_split").concat(position_of_the_comment).concat("small_split").concat(name_of_the_comment).concat("small_split").concat(String.valueOf(time_of_the_comment)).concat("small_split").concat(body_of_the_reply).concat("small_split").concat(position_of_the_reply).concat("small_split").concat(name_of_the_reply).concat("small_split").concat(String.valueOf(time_of_the_reply));
        if (old != null && !old.isEmpty()) {
            String save_me = "";
            String[] big_spit = old.split("big_divide");
            for (int i = 0; i < big_spit.length; i++) {
                if (!big_spit[i].equals(all)) {
                    save_me = save_me.concat(big_spit[i]).concat("big_divide");
                }
            }
            myEdit.putString("saved_comments", save_me);
            myEdit.commit();
        }
    }

    private void save_button_watch(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        holder.button_to_watch_save_in_card_in_post_comment_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event_manager_all_in_one.getInstance().record_fire_base_event(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), Event_manager_all_in_one.Event_type_fire_base_record.reply_saved,false);
                save_button_pressed(holder, current_item);
            }
        });
    }

    private void check_if_comment_is_saved(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        if (current_item.isIs_this_saved() || is_this_saved(holder, current_item)) {
            save_the_icon(holder, current_item);
            current_item.setIs_this_saved(true);
        } else {
            remove_the_icon(holder, current_item);
            current_item.setIs_this_saved(false);
        }
    }

    private void reply_button_watch(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        holder.reply_button_in_tem_id_comment_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event_manager_all_in_one.getInstance().record_fire_base_event(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), Event_manager_all_in_one.Event_type_fire_base_record.reply_written,false);
                if (am_i_signed_in_with_google(current_item)) {
                    reply_button_was_clicked_listen.reply_button_clicked(current_item.getBody(), current_item.getName(), current_item.getUser_id());
                } else {
                    if (up_vote_down_vote_clicked_listen != null) {
                        up_vote_down_vote_clicked_listen.up_vote_down_vote_button_clicked("comment");
                    }
                }
            }
        });
    }

    private boolean am_i_signed_in_with_google(final Example_reply_to_comment current_item) {
        /*if (current_item.getFirebaseUser() != null && current_item.getFirebaseUser().getIdToken(false).getResult().getSignInProvider().equals("google.com")) {
            return true;
        } else {
            return false;
        }*/
        return true;
    }

    private void set_likes_to_one(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        holder.text_showing_the_number_of_up_votes_comment_reply.setText("1");
    }

    private void set_dev_to_false(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        holder.view_behind_i_am_the_dev_of_the_text_comment_reply.setVisibility(View.INVISIBLE);
        holder.text_view_saying_i_am_the_dev_of_the_app_comment_reply.setVisibility(View.INVISIBLE);
        holder.circle_between_time_and_dev_in_card_comment_reply.setVisibility(View.INVISIBLE);
    }

    private void set_awards_to_zero(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        make_the_awards_be_invisble(holder);
        ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed_comment_reply;
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(holder.actual_text_to_show_post_in_card_comment_reply.getId(), ConstraintSet.TOP, holder.three_dots_at_the_top_right_of_post_comment_reply.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(5, holder.constriant_inside_card_inside_post_feed_comment_reply.getContext()));
        constraintSet.applyTo(constraintLayout);
    }

    private void watch_the_buttons_for_archieved(final ExampleViewHolder holder, final Example_reply_to_comment current_item) {
        holder.button_to_watch_upvote_in_card_in_post_comment_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), "Can't up vote archived posts", Toast.LENGTH_SHORT).show();
            }
        });
        holder.button_to_watch_downvote_in_card_in_post_comment_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), "Can't down vote archived posts", Toast.LENGTH_SHORT).show();
            }
        });
        holder.reply_button_in_tem_id_comment_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), "Can't reply to archived posts", Toast.LENGTH_SHORT).show();

            }
        });
        holder.button_to_watch_gift_in_card_in_post_comment_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), "Can't gift archived posts", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void set_the_save(final ExampleViewHolder holder, final Example_reply_to_comment current_item){
        if (current_item.isIs_this_saved() || is_this_saved(holder, current_item)) {
            save_the_icon(holder, current_item);
        } else {
            remove_the_icon(holder, current_item);
        }
    }

    private void gift_button_listen(final ExampleViewHolder holder, final Example_reply_to_comment current_item){
        holder.button_to_watch_gift_in_card_in_post_comment_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event_manager_all_in_one.getInstance().record_fire_base_event(holder.constriant_inside_card_inside_post_feed_comment_reply.getContext(), Event_manager_all_in_one.Event_type_fire_base_record.reply_gifted,false);
                gift_button_listen_listener.gift_was_clicked(current_item.getUser_id(),current_item.getDocument_id(),current_item.getComment_position(),current_item.getReply_position(),current_item.getAwards(),holder.getAdapterPosition());
            }
        });
    }
}