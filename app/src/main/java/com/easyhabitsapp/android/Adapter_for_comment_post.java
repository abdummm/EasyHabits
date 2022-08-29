package com.easyhabitsapp.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import androidx.recyclerview.widget.LinearLayoutManager;
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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Adapter_for_comment_post extends RecyclerView.Adapter<Adapter_for_comment_post.ExampleViewHolder> {
    private ArrayList<Example_item_comments_feed> m_example_list;
    private share_button_was_clicked share_button_was_clicked_listen;
    private reply_button_was_clicked reply_button_was_clicked_listener;
    private reply_nested_button_nested_interface reply_nested_button_nested_interface_liesten;
    private up_vote_listener up_vote_listener_listen;
    private gift_button_listen gift_button_listen_listener;
    private save_is_only_for_pro save_is_only_for_pro_listen;


    public void set_share_clicked_listen(share_button_was_clicked listen) {
        share_button_was_clicked_listen = listen;
    }

    public interface share_button_was_clicked {
        void share_just_got_clciked(String body);
    }

    public void set_reply_clicked_listen(reply_button_was_clicked listen) {
        reply_button_was_clicked_listener = listen;
    }

    public interface reply_button_was_clicked {
        void reply_was_clicked_in_commenmt(int position, String body, int recycle_view_position);
    }

    public void reply_nested_function(reply_nested_button_nested_interface listen) {
        reply_nested_button_nested_interface_liesten = listen;
    }

    public interface reply_nested_button_nested_interface {
        void reply_button_clicked_nested_one(String name, String body, String user_id, int position, int recycle_view_position);
    }

    public void set_up_vote_down_vote_clicked_listen(up_vote_listener listen) {
        up_vote_listener_listen = listen;
    }

    public interface up_vote_listener {
        void up_vote_down_vote_was_clciked(String body);
    }

    public void set_up_gift_listen(gift_button_listen listen) {
        gift_button_listen_listener = listen;
    }

    public interface gift_button_listen {
        void gift_was_clicked(String user_id, String document_id, int comment_position, int reply_position, ArrayList<Long> awards);
    }

    public void set_up_save_only_for_pro(save_is_only_for_pro listen) {
        save_is_only_for_pro_listen = listen;
    }

    public interface save_is_only_for_pro {
        void save_is_for_pro(String mode);
    }


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_comment_post, parent, false);
        ExampleViewHolder exampleViewHolder = new ExampleViewHolder(view);
        return exampleViewHolder;
    }

    public Adapter_for_comment_post(ArrayList<Example_item_comments_feed> Example_item_comments_feed) {
        m_example_list = Example_item_comments_feed;
    }

    @Override
    public void onBindViewHolder(@NonNull final ExampleViewHolder holder, final int position) {
        final Example_item_comments_feed current_item = m_example_list.get(position);
        if (current_item.isIs_this_an_archived_comment()) {
            three_dot_button_listen(holder, current_item);
            save_button_listen_archived_your_comment(holder, current_item);
            set_time(holder, current_item);
            set_the_dev(holder, current_item);
//            set_category(holder, current_item);
//            set_streak(holder, current_item);
            set_up_awards(holder, current_item);
            set_body(holder, current_item);
            set_likes(holder, current_item);
            share_button_lsiten(holder, current_item);
            set_is_saved(holder, current_item);
            set_the_name(holder, current_item);
            buttons_listen_archived(holder, current_item);
            if (current_item.getReplies() != null) {
                set_up_the_recylce_view(holder, current_item);
            }
        } else {
            three_dot_button_listen(holder, current_item);
            save_button_listen(holder, current_item);
            set_time(holder, current_item);
            set_the_dev(holder, current_item);
//            set_category(holder, current_item);
//            set_streak(holder, current_item);
            set_up_awards(holder, current_item);
            set_body(holder, current_item);
            set_likes(holder, current_item);
            color_the_up_vote_down_vote_at_start(holder, current_item);
            up_vote_or_down_vote_listener(holder, current_item);
            share_button_lsiten(holder, current_item);
            set_is_saved(holder, current_item);
            reply_button_listen(holder, current_item);
            set_up_the_recylce_view(holder, current_item);
            set_the_name(holder, current_item);
            gift_button_listen(holder, current_item);
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
        public Button button_to_watch_three_dot_in_card_in_post_in_top_comment;
        public ConstraintLayout constriant_inside_card_inside_post_feed_comment;
        public Button button_to_watch_save_in_card_in_post_comment;
        public TextView text_at_top_left_of_card_to_show_time_comment;
        public TextView text_view_saying_i_am_the_dev_of_the_app_comment;
        public View view_behind_i_am_the_dev_of_the_text_comment;
        //public TextView text_at_the_top_showing_what_category_this_is_in_comment;
        //public TextView text_at_the_top_showing_how_much_is_the_streak_comment;
        //public View circle_between_the_streak_and_the_dev_icon_comment;
        //public View circle_between_time_and_cat_in_streak_in_post_comment;
        public View circle_between_time_and_dev_in_card_comment;
        public TextView actual_text_to_show_post_in_card_comment;
        public TextView plat_text_beside_the_award_posts_comment;
        public TextView plat_text_beside_the_gold_award_posts_comment;
        public TextView silver_text_beside_the_award_post_comment;
        public TextView text_saying_the_number_of_awards_comment;
        public View three_dots_at_the_top_right_of_post_comment;
        public View circle_between_gold_and_silver_in_post_comment;
        public View start_part_of_the_grey_part_in_post_award_comment;
        public View middle_part_of_the_grey_part_in_post_award_comment;
        public View end_part_of_the_grey_part_in_post_award_comment;
        public View circle_between_awards_and_plat_in_post_comment;
        public View view_showing_the_plat_award_comment;
        public View circle_between_plat_and_gold_in_post_comment;
        public View view_showing_the_gold_award_comment;
        public View view_showing_the_silver_award_comment;
        public TextView text_showing_the_number_of_up_votes_comment;
        public View upvote_view_in_post_card_comment;
        public View down_vote_view_in_post_card_comment;
        public Button button_to_watch_upvote_in_card_in_post_comment;
        public Button button_to_watch_downvote_in_card_in_post_comment;
        public Button button_to_watch_share_in_card_in_post_in_top_comment;
        public View save_button_in_card_in_posts_comment;
        public Button reply_button_in_tem_id_comment;
        public RecyclerView recylce_view_insode_each_comment_showing_replies;
        public TextView text_name_in_the_card_of_the_post_user_name;
        public Button button_to_watch_gift_in_card_in_post_comment;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            button_to_watch_three_dot_in_card_in_post_in_top_comment = itemView.findViewById(R.id.button_to_watch_three_dot_in_card_in_post_in_top_comment);
            constriant_inside_card_inside_post_feed_comment = itemView.findViewById(R.id.constriant_inside_card_inside_post_feed_comment);
            button_to_watch_save_in_card_in_post_comment = itemView.findViewById(R.id.button_to_watch_save_in_card_in_post_comment);
            text_at_top_left_of_card_to_show_time_comment = itemView.findViewById(R.id.text_at_top_left_of_card_to_show_time_comment);
            text_view_saying_i_am_the_dev_of_the_app_comment = itemView.findViewById(R.id.text_view_saying_i_am_the_dev_of_the_app_comment);
            view_behind_i_am_the_dev_of_the_text_comment = itemView.findViewById(R.id.view_behind_i_am_the_dev_of_the_text_comment);
            //text_at_the_top_showing_what_category_this_is_in_comment = itemView.findViewById(R.id.text_at_the_top_showing_what_category_this_is_in_comment);
            //text_at_the_top_showing_how_much_is_the_streak_comment = itemView.findViewById(R.id.text_at_the_top_showing_how_much_is_the_streak_comment);
            //circle_between_the_streak_and_the_dev_icon_comment = itemView.findViewById(R.id.circle_between_the_streak_and_the_dev_icon_comment);
            //circle_between_time_and_cat_in_streak_in_post_comment = itemView.findViewById(R.id.circle_between_time_and_cat_in_streak_in_post_comment);
            actual_text_to_show_post_in_card_comment = itemView.findViewById(R.id.actual_text_to_show_post_in_card_comment);
            plat_text_beside_the_award_posts_comment = itemView.findViewById(R.id.plat_text_beside_the_award_posts_comment);
            plat_text_beside_the_gold_award_posts_comment = itemView.findViewById(R.id.plat_text_beside_the_gold_award_posts_comment);
            silver_text_beside_the_award_post_comment = itemView.findViewById(R.id.silver_text_beside_the_award_post_comment);
            text_saying_the_number_of_awards_comment = itemView.findViewById(R.id.text_saying_the_number_of_awards_comment);
            three_dots_at_the_top_right_of_post_comment = itemView.findViewById(R.id.three_dots_at_the_top_right_of_post_comment);
            circle_between_gold_and_silver_in_post_comment = itemView.findViewById(R.id.circle_between_gold_and_silver_in_post_comment);
            start_part_of_the_grey_part_in_post_award_comment = itemView.findViewById(R.id.start_part_of_the_grey_part_in_post_award_comment);
            middle_part_of_the_grey_part_in_post_award_comment = itemView.findViewById(R.id.middle_part_of_the_grey_part_in_post_award_comment);
            end_part_of_the_grey_part_in_post_award_comment = itemView.findViewById(R.id.end_part_of_the_grey_part_in_post_award_comment);
            circle_between_awards_and_plat_in_post_comment = itemView.findViewById(R.id.circle_between_awards_and_plat_in_post_comment);
            view_showing_the_plat_award_comment = itemView.findViewById(R.id.view_showing_the_plat_award_comment);
            circle_between_plat_and_gold_in_post_comment = itemView.findViewById(R.id.circle_between_plat_and_gold_in_post_comment);
            view_showing_the_gold_award_comment = itemView.findViewById(R.id.view_showing_the_gold_award_comment);
            view_showing_the_silver_award_comment = itemView.findViewById(R.id.view_showing_the_silver_award_comment);
            text_showing_the_number_of_up_votes_comment = itemView.findViewById(R.id.text_showing_the_number_of_up_votes_comment);
            upvote_view_in_post_card_comment = itemView.findViewById(R.id.upvote_view_in_post_card_comment);
            down_vote_view_in_post_card_comment = itemView.findViewById(R.id.down_vote_view_in_post_card_comment);
            button_to_watch_upvote_in_card_in_post_comment = itemView.findViewById(R.id.button_to_watch_upvote_in_card_in_post_comment);
            button_to_watch_downvote_in_card_in_post_comment = itemView.findViewById(R.id.button_to_watch_downvote_in_card_in_post_comment);
            button_to_watch_share_in_card_in_post_in_top_comment = itemView.findViewById(R.id.button_to_watch_share_in_card_in_post_in_top_comment);
            save_button_in_card_in_posts_comment = itemView.findViewById(R.id.save_button_in_card_in_posts_comment);
            reply_button_in_tem_id_comment = itemView.findViewById(R.id.reply_button_in_tem_id_comment);
            recylce_view_insode_each_comment_showing_replies = itemView.findViewById(R.id.recylce_view_insode_each_comment_showing_replies);
            text_name_in_the_card_of_the_post_user_name = itemView.findViewById(R.id.text_name_in_the_card_of_the_post_user_name);
            button_to_watch_gift_in_card_in_post_comment = itemView.findViewById(R.id.button_to_watch_gift_in_card_in_post_comment);
            circle_between_time_and_dev_in_card_comment = itemView.findViewById(R.id.circle_between_time_and_dev_in_card_comment);
        }
    }

    private void three_dot_button_listen(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        holder.button_to_watch_three_dot_in_card_in_post_in_top_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu_was_clicked(holder, current_item);
            }
        });
    }

    private void menu_was_clicked(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        PopupMenu popupMenu = new PopupMenu(holder.constriant_inside_card_inside_post_feed_comment.getContext(), holder.button_to_watch_three_dot_in_card_in_post_in_top_comment);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.report_button_comment_in_tree_dots) {
                    if (current_item.isIs_this_an_archived_comment()) {
                        Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "Can't report archived comments", Toast.LENGTH_SHORT).show();
                    } else {
                        if (am_i_signed_in_with_google(current_item)) {
                            if (current_item.isReported() || current_item.getReport_list().contains(current_item.return_my_user_id())) {
                                Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "Already reported", Toast.LENGTH_SHORT);
                                toast.show();
                            } else {
                                new AlertDialog.Builder(holder.constriant_inside_card_inside_post_feed_comment.getContext())
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
                            up_vote_listener_listen.up_vote_down_vote_was_clciked("report");
                        }
                    }
                }
                return true;
            }
        });
        popupMenu.inflate(R.menu.three_dot_comment_post);
        popupMenu.show();
    }

    private void report_comment(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        final DocumentReference sfDocRef = current_item.getFirebaseFirestore().collection("posts").document(current_item.getDocument_id());
        current_item.getFirebaseFirestore().runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);
                ArrayList<HashMap<String, Object>> comments = (ArrayList<HashMap<String, Object>>) snapshot.get("comments");
                ArrayList<String> reports_list = (ArrayList<String>) comments.get(current_item.getPosition()).get("reports");
                reports_list.add(current_item.return_my_user_id());
                transaction.update(sfDocRef, "comments", comments, "type", "comment");

                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "Thanks, your input was received and greatly appreciated!!", Toast.LENGTH_LONG);
                toast.show();
                current_item.setReported(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "Can't report right now", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void save_button_listen(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        holder.button_to_watch_save_in_card_in_post_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (current_item.isSaved() || does_commetn_already_saved(holder, current_item)) {
                    remove_the_comment(holder, current_item);
                    current_item.setSaved(false);
                    Drawable not_saved = ContextCompat.getDrawable(holder.constriant_inside_card_inside_post_feed_comment.getContext(), R.drawable.round_turned_in_not_24);
                    holder.save_button_in_card_in_posts_comment.setBackground(not_saved);
                } else {
                    Am_i_paid am_i_paid = new Am_i_paid(holder.constriant_inside_card_inside_post_feed_comment.getContext());
                    if (am_i_paid.did_user_pay()) {
                        save_the_comment(holder, current_item);
                        current_item.setSaved(true);
                        Drawable saved = ContextCompat.getDrawable(holder.constriant_inside_card_inside_post_feed_comment.getContext(), R.drawable.round_turned_in_24);
                        holder.save_button_in_card_in_posts_comment.setBackground(saved);
                    } else {
                        save_is_only_for_pro_listen.save_is_for_pro("comment");
                    }
                }
            }
        });
    }

    private void save_the_comment(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        SharedPreferences sharedPreferences = holder.constriant_inside_card_inside_post_feed_comment.getContext().getSharedPreferences("saved_comments", Context.MODE_PRIVATE);
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
        String body_of_the_comment = current_item.getBody().replace("small_split", "").replace("big_divide", "");
        String position_of_the_comment = String.valueOf(current_item.getPosition());
        String name_of_the_comment = current_item.getName().replace("small_split", "").replace("big_divide", "");
        long time_of_the_comment = current_item.getTime().getTime();
//        String category = current_item.getCategory();
//        long streak_of_the_comment = current_item.getStreak();

        String all = document_id.concat("small_split").concat(name_in_the_main).concat("small_split").concat(title_of_the_main).concat("small_split").concat(body_of_the_main_post).concat("small_split").concat(span_the_main).concat("small_split").concat(String.valueOf(time_the_main)).concat("small_split").concat(body_of_the_comment).concat("small_split").concat(position_of_the_comment).concat("small_split").concat(name_of_the_comment).concat("small_split").concat(String.valueOf(time_of_the_comment)).concat("big_divide");
        if (old != null && !old.isEmpty()) {
            myEdit.putString("saved_comments", old.concat(all));
        } else {
            myEdit.putString("saved_comments", all);
        }
        myEdit.commit();

    }

    private void remove_the_comment(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        SharedPreferences sharedPreferences = holder.constriant_inside_card_inside_post_feed_comment.getContext().getSharedPreferences("saved_comments", Context.MODE_PRIVATE);
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
        String body_of_the_comment = current_item.getBody().replace("small_split", "").replace("big_divide", "");
        String position_of_the_comment = String.valueOf(current_item.getPosition());
        String name_of_the_comment = current_item.getName().replace("small_split", "").replace("big_divide", "");
        long time_of_the_comment = current_item.getTime().getTime();
//        String category = current_item.getCategory();
//        long streak_of_the_comment = current_item.getStreak();
        String remove_all = document_id.concat("small_split").concat(name_in_the_main).concat("small_split").concat(title_of_the_main).concat("small_split").concat(body_of_the_main_post).concat("small_split").concat(span_the_main).concat("small_split").concat(String.valueOf(time_the_main)).concat("small_split").concat(body_of_the_comment).concat("small_split").concat(position_of_the_comment).concat("small_split").concat(name_of_the_comment).concat("small_split").concat(String.valueOf(time_of_the_comment));
        if (old != null && !old.isEmpty()) {
            String save_me = "";
            String[] big_spit = old.split("big_divide");
            for (int i = 0; i < big_spit.length; i++) {
                if (!big_spit[i].equals(remove_all)) {
                    save_me = save_me.concat(big_spit[i]).concat("big_divide");
                }
            }
            myEdit.putString("saved_comments", save_me);
            myEdit.commit();
        }
    }

    private boolean does_commetn_already_saved(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        SharedPreferences sharedPreferences = holder.constriant_inside_card_inside_post_feed_comment.getContext().getSharedPreferences("saved_comments", Context.MODE_PRIVATE);
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
        String body_of_the_comment = current_item.getBody().replace("small_split", "").replace("big_divide", "");
        String position_of_the_comment = String.valueOf(current_item.getPosition());
        String name_of_the_comment = current_item.getName().replace("small_split", "").replace("big_divide", "");
        long time_of_the_comment = current_item.getTime().getTime();
//        String category = current_item.getCategory();
//        long streak_of_the_comment = current_item.getStreak();
        String remove_all = document_id.concat("small_split").concat(name_in_the_main).concat("small_split").concat(title_of_the_main).concat("small_split").concat(body_of_the_main_post).concat("small_split").concat(span_the_main).concat("small_split").concat(String.valueOf(time_the_main)).concat("small_split").concat(body_of_the_comment).concat("small_split").concat(position_of_the_comment).concat("small_split").concat(name_of_the_comment).concat("small_split").concat(String.valueOf(time_of_the_comment));
        if (old != null && !old.isEmpty()) {
            String[] big_spit = old.split("big_divide");
            for (int i = 0; i < big_spit.length; i++) {
                if (big_spit[i].equals(remove_all)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private void set_time(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        holder.text_at_top_left_of_card_to_show_time_comment.setText(return_time_at_top(current_item.getTime()));
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

    private void set_the_dev(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        if (current_item.isIs_this_an_archived_comment()) {
            holder.view_behind_i_am_the_dev_of_the_text_comment.setVisibility(View.INVISIBLE);
            holder.text_view_saying_i_am_the_dev_of_the_app_comment.setVisibility(View.INVISIBLE);
            holder.circle_between_time_and_dev_in_card_comment.setVisibility(View.INVISIBLE);
        } else {
            if (current_item.isDev()) {
                holder.view_behind_i_am_the_dev_of_the_text_comment.setVisibility(View.VISIBLE);
                holder.text_view_saying_i_am_the_dev_of_the_app_comment.setVisibility(View.VISIBLE);
                holder.circle_between_time_and_dev_in_card_comment.setVisibility(View.VISIBLE);
            } else {
                holder.view_behind_i_am_the_dev_of_the_text_comment.setVisibility(View.INVISIBLE);
                holder.text_view_saying_i_am_the_dev_of_the_app_comment.setVisibility(View.INVISIBLE);
                holder.circle_between_time_and_dev_in_card_comment.setVisibility(View.INVISIBLE);
            }
        }
    }

    /*private void set_category(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        holder.text_at_the_top_showing_what_category_this_is_in_comment.setText(current_item.getCategory());
    }*/

    /*private void set_streak(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        if (current_item.getStreak() < 0) {
            holder.text_at_the_top_showing_how_much_is_the_streak_comment.setVisibility(View.INVISIBLE);
            holder.circle_between_time_and_cat_in_streak_in_post_comment.setVisibility(View.INVISIBLE);
            ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed_comment;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(holder.circle_between_the_streak_and_the_dev_icon_comment.getId(), ConstraintSet.START, holder.text_at_the_top_showing_what_category_this_is_in_comment.getId(), ConstraintSet.END, (int) convertDpToPixel(5, holder.constriant_inside_card_inside_post_feed_comment.getContext()));
            constraintSet.applyTo(constraintLayout);
        } else {
            holder.text_at_the_top_showing_how_much_is_the_streak_comment.setVisibility(View.VISIBLE);
            holder.circle_between_time_and_cat_in_streak_in_post_comment.setVisibility(View.VISIBLE);
            if (current_item.getStreak() == 1) {
                holder.text_at_the_top_showing_how_much_is_the_streak_comment.setText("1 day");
            } else {
                holder.text_at_the_top_showing_how_much_is_the_streak_comment.setText(String.valueOf(current_item.getStreak()).concat(" days"));
            }
        }
    }*/

    private static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private void set_up_awards(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        if (current_item.isIs_this_an_archived_comment()) {
            make_the_awards_be_invisble(holder);
            ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed_comment;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(holder.actual_text_to_show_post_in_card_comment.getId(), ConstraintSet.TOP, holder.three_dots_at_the_top_right_of_post_comment.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(5, holder.constriant_inside_card_inside_post_feed_comment.getContext()));
            constraintSet.applyTo(constraintLayout);
        } else {
            long plat_award = current_item.getAwards().get(0);
            long gold_award = current_item.getAwards().get(1);
            long silver_award = current_item.getAwards().get(2);
            if (plat_award == 0 && gold_award == 0 && silver_award == 0) {
                make_the_awards_be_invisble(holder);
                ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed_comment;
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(holder.actual_text_to_show_post_in_card_comment.getId(), ConstraintSet.TOP, holder.three_dots_at_the_top_right_of_post_comment.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(5, holder.constriant_inside_card_inside_post_feed_comment.getContext()));
                constraintSet.applyTo(constraintLayout);
            } else {
                make_every_award_visible(holder);
                {
                    ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed_comment;
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(holder.actual_text_to_show_post_in_card_comment.getId(), ConstraintSet.TOP, holder.text_saying_the_number_of_awards_comment.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(15, holder.constriant_inside_card_inside_post_feed_comment.getContext()));
                    constraintSet.applyTo(constraintLayout);
                }
                holder.plat_text_beside_the_award_posts_comment.setText(String.valueOf(plat_award));
                holder.plat_text_beside_the_gold_award_posts_comment.setText(String.valueOf(gold_award));
                holder.silver_text_beside_the_award_post_comment.setText(String.valueOf(silver_award));
                long awards_total_number = plat_award + gold_award + silver_award;
                if (awards_total_number == 1) {
                    holder.text_saying_the_number_of_awards_comment.setText("1 award");
                } else {
                    holder.text_saying_the_number_of_awards_comment.setText(String.valueOf(awards_total_number).concat(" awards"));
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
                    ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed_comment;
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(holder.circle_between_gold_and_silver_in_post_comment.getId(), ConstraintSet.START, holder.plat_text_beside_the_award_posts_comment.getId(), ConstraintSet.END, (int) convertDpToPixel(5, holder.constriant_inside_card_inside_post_feed_comment.getContext()));
                    constraintSet.applyTo(constraintLayout);
                } else if (plat_award > 0 && gold_award > 0 && silver_award == 0) {
                    make_the_silver_invisible(holder);
                }
            }
        }
    }

    private void make_the_awards_be_invisble(@NonNull ExampleViewHolder holder) {
        holder.start_part_of_the_grey_part_in_post_award_comment.setVisibility(View.GONE);
        holder.middle_part_of_the_grey_part_in_post_award_comment.setVisibility(View.GONE);
        holder.end_part_of_the_grey_part_in_post_award_comment.setVisibility(View.GONE);
        holder.text_saying_the_number_of_awards_comment.setVisibility(View.GONE);
        holder.circle_between_awards_and_plat_in_post_comment.setVisibility(View.GONE);
        holder.view_showing_the_plat_award_comment.setVisibility(View.GONE);
        holder.plat_text_beside_the_award_posts_comment.setVisibility(View.GONE);
        holder.circle_between_plat_and_gold_in_post_comment.setVisibility(View.GONE);
        holder.view_showing_the_gold_award_comment.setVisibility(View.GONE);
        holder.plat_text_beside_the_gold_award_posts_comment.setVisibility(View.GONE);
        holder.circle_between_gold_and_silver_in_post_comment.setVisibility(View.GONE);
        holder.view_showing_the_silver_award_comment.setVisibility(View.GONE);
        holder.silver_text_beside_the_award_post_comment.setVisibility(View.GONE);
    }

    private void make_every_award_visible(@NonNull ExampleViewHolder holder) {
        holder.start_part_of_the_grey_part_in_post_award_comment.setVisibility(View.VISIBLE);
        holder.middle_part_of_the_grey_part_in_post_award_comment.setVisibility(View.VISIBLE);
        holder.end_part_of_the_grey_part_in_post_award_comment.setVisibility(View.VISIBLE);
        holder.text_saying_the_number_of_awards_comment.setVisibility(View.VISIBLE);
        holder.circle_between_awards_and_plat_in_post_comment.setVisibility(View.VISIBLE);
        holder.view_showing_the_plat_award_comment.setVisibility(View.VISIBLE);
        holder.plat_text_beside_the_award_posts_comment.setVisibility(View.VISIBLE);
        holder.circle_between_plat_and_gold_in_post_comment.setVisibility(View.VISIBLE);
        holder.view_showing_the_gold_award_comment.setVisibility(View.VISIBLE);
        holder.plat_text_beside_the_gold_award_posts_comment.setVisibility(View.VISIBLE);
        holder.circle_between_gold_and_silver_in_post_comment.setVisibility(View.VISIBLE);
        holder.view_showing_the_silver_award_comment.setVisibility(View.VISIBLE);
        holder.silver_text_beside_the_award_post_comment.setVisibility(View.VISIBLE);
    }

    private void make_the_plat_invisible(@NonNull ExampleViewHolder holder) {
        holder.circle_between_awards_and_plat_in_post_comment.setVisibility(View.GONE);
        holder.view_showing_the_plat_award_comment.setVisibility(View.GONE);
        holder.plat_text_beside_the_award_posts_comment.setVisibility(View.GONE);
    }

    private void make_the_gold_invisible(@NonNull ExampleViewHolder holder) {
        holder.circle_between_plat_and_gold_in_post_comment.setVisibility(View.GONE);
        holder.view_showing_the_gold_award_comment.setVisibility(View.GONE);
        holder.plat_text_beside_the_gold_award_posts_comment.setVisibility(View.GONE);
    }

    private void make_the_silver_invisible(@NonNull ExampleViewHolder holder) {
        holder.circle_between_gold_and_silver_in_post_comment.setVisibility(View.GONE);
        holder.view_showing_the_silver_award_comment.setVisibility(View.GONE);
        holder.silver_text_beside_the_award_post_comment.setVisibility(View.GONE);
    }

    private void conect_me_to_start(int which, @NonNull ExampleViewHolder holder) {
        if (which == 1) {
            ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed_comment;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(holder.circle_between_plat_and_gold_in_post_comment.getId(), ConstraintSet.START, holder.text_saying_the_number_of_awards_comment.getId(), ConstraintSet.END, (int) convertDpToPixel(5, holder.constriant_inside_card_inside_post_feed_comment.getContext()));
            constraintSet.applyTo(constraintLayout);
        } else if (which == 2) {
            ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed_comment;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(holder.circle_between_gold_and_silver_in_post_comment.getId(), ConstraintSet.START, holder.text_saying_the_number_of_awards_comment.getId(), ConstraintSet.END, (int) convertDpToPixel(5, holder.constriant_inside_card_inside_post_feed_comment.getContext()));
            constraintSet.applyTo(constraintLayout);
        }
    }

    private void set_body(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        holder.actual_text_to_show_post_in_card_comment.setText(first_letter_capital(current_item.getBody().trim()));
    }

    private void set_likes(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        if (current_item.isIs_this_an_archived_comment()) {
            holder.text_showing_the_number_of_up_votes_comment.setText("1");
        } else {
            int likes = (current_item.getUp_vote_list().size() - current_item.getDown_vote_list().size()) + current_item.getPlus_or_minus();
            if (likes > 1) {
                holder.text_showing_the_number_of_up_votes_comment.setText(String.valueOf(likes));
            } else {
                holder.text_showing_the_number_of_up_votes_comment.setText("1");
            }
        }
    }

    private void color_the_up_vote_down_vote_at_start(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        if (current_item.isUpvoted() || (!current_item.isWas_this_touched() && current_item.getUp_vote_list().contains(current_item.return_my_user_id()))) {
            color_the_up_vote_down_vote(1, holder, current_item);
        }
        if (current_item.isDown_vote() || (!current_item.isWas_this_touched() && current_item.getDown_vote_list().contains(current_item.return_my_user_id()))) {
            color_the_up_vote_down_vote(2, holder, current_item);
        }
    }

    private void color_the_up_vote_down_vote(int which, @NonNull final ExampleViewHolder holder, Example_item_comments_feed current_item) {
        if (which == 0) {
            // no vote
            {
                holder.text_showing_the_number_of_up_votes_comment.setTextColor(Color.parseColor("#808080"));
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(holder.constriant_inside_card_inside_post_feed_comment.getContext(), R.drawable.round_arrow_drop_up_24).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#808080"));
                holder.upvote_view_in_post_card_comment.setBackground(wrappedDrawable);
            }
            {
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(holder.constriant_inside_card_inside_post_feed_comment.getContext(), R.drawable.arrow_down_down_vote_card).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#808080"));
                holder.down_vote_view_in_post_card_comment.setBackground(wrappedDrawable);
            }
            current_item.setUpvoted(false);
            current_item.setDown_vote(false);
        } else if (which == 1) {
            // up vote
            {
                holder.text_showing_the_number_of_up_votes_comment.setTextColor(Color.parseColor("#FF4500"));
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(holder.constriant_inside_card_inside_post_feed_comment.getContext(), R.drawable.round_arrow_drop_up_24).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#FF4500"));
                holder.upvote_view_in_post_card_comment.setBackground(wrappedDrawable);
            }
            {
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(holder.constriant_inside_card_inside_post_feed_comment.getContext(), R.drawable.arrow_down_down_vote_card).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#808080"));
                holder.down_vote_view_in_post_card_comment.setBackground(wrappedDrawable);
            }
            current_item.setUpvoted(true);
            current_item.setDown_vote(false);
        } else if (which == 2) {
            // down vote
            {
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(holder.constriant_inside_card_inside_post_feed_comment.getContext(), R.drawable.round_arrow_drop_up_24).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#808080"));
                holder.upvote_view_in_post_card_comment.setBackground(wrappedDrawable);
            }
            {
                holder.text_showing_the_number_of_up_votes_comment.setTextColor(Color.parseColor("#9494FF"));
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(holder.constriant_inside_card_inside_post_feed_comment.getContext(), R.drawable.arrow_down_down_vote_card).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#9494FF"));
                holder.down_vote_view_in_post_card_comment.setBackground(wrappedDrawable);
            }
            current_item.setUpvoted(false);
            current_item.setDown_vote(true);
        }
    }

    private void up_vote_or_down_vote_listener(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        holder.button_to_watch_upvote_in_card_in_post_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (current_item.isAm_i_loading_for_up_vote_and_down_vote()) {
                    Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "You are up voting and down voting too quickly!!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (am_i_signed_in_with_google(current_item)) {
                        up_vote_was_clicked(holder, current_item);
                    } else {
                        if (up_vote_listener_listen != null) {
                            up_vote_listener_listen.up_vote_down_vote_was_clciked("up vote");
                        }
                    }
                }
            }
        });
        holder.button_to_watch_downvote_in_card_in_post_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (current_item.isAm_i_loading_for_up_vote_and_down_vote()) {
                    Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "You are up voting and down voting too quickly!!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (am_i_signed_in_with_google(current_item)) {
                        down_vote_was_cliclked(holder, current_item);
                    } else {
                        if (up_vote_listener_listen != null) {
                            up_vote_listener_listen.up_vote_down_vote_was_clciked("down vote");
                        }
                    }
                }
            }
        });
    }

    private boolean am_i_signed_in_with_google(final Example_item_comments_feed current_item) {
        /*if (current_item.getFirebaseUser() != null && current_item.getFirebaseUser().getIdToken(false).getResult().getSignInProvider().equals("google.com")) {
            return true;
        } else {
            return false;
        }*/
        return true;
    }

    private void up_vote_was_clicked(@NonNull final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        int mode;
        if (current_item.getUp_vote_list().contains(current_item.return_my_user_id())) {
            mode = 1;
        } else if (current_item.getDown_vote_list().contains(current_item.return_my_user_id())) {
            mode = 2;
        } else {
            mode = 3;
        }
        current_item.setWas_this_touched(true);
        if (current_item.isDown_vote()) {
            int upvotes = (current_item.getUp_vote_list().size() - current_item.getDown_vote_list().size()) + current_item.getPlus_or_minus();
            upvotes = upvotes + 2;
            if (upvotes > 1) {
                holder.text_showing_the_number_of_up_votes_comment.setText(String.valueOf(upvotes));
            } else {
                holder.text_showing_the_number_of_up_votes_comment.setText("1");
                Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "Recorded but not shown publicly", Toast.LENGTH_SHORT);
                toast.show();
            }
            color_the_up_vote_down_vote(1, holder, current_item);
            current_item.setAm_i_loading_for_up_vote_and_down_vote(true);
            remove_the_down_vote(holder, current_item, true);
            if (mode == 1) {
                current_item.setPlus_or_minus(0);
            } else if (mode == 2) {
                current_item.setPlus_or_minus(2);
            } else {
                current_item.setPlus_or_minus(1);
            }
        } else {
            if (current_item.isUpvoted()) {
                int upvotes = (current_item.getUp_vote_list().size() - current_item.getDown_vote_list().size()) + current_item.getPlus_or_minus();
                upvotes--;
                if (upvotes > 1) {
                    holder.text_showing_the_number_of_up_votes_comment.setText(String.valueOf(upvotes));
                } else {
                    holder.text_showing_the_number_of_up_votes_comment.setText("1");
                    Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "Recorded but not shown publicly", Toast.LENGTH_SHORT);
                    toast.show();
                }
                color_the_up_vote_down_vote(0, holder, current_item);
                remove_the_upvote(holder, current_item, false);
                if (mode == 1) {
                    current_item.setPlus_or_minus(-1);
                } else if (mode == 2) {
                    current_item.setPlus_or_minus(1);
                } else {
                    current_item.setPlus_or_minus(0);
                }
            } else {
                int upvotes = (current_item.getUp_vote_list().size() - current_item.getDown_vote_list().size()) + current_item.getPlus_or_minus();
                upvotes++;
                if (upvotes > 1) {
                    holder.text_showing_the_number_of_up_votes_comment.setText(String.valueOf(upvotes));
                } else {
                    holder.text_showing_the_number_of_up_votes_comment.setText("1");
                    Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "Recorded but not shown publicly", Toast.LENGTH_SHORT);
                    toast.show();
                }
                color_the_up_vote_down_vote(1, holder, current_item);
                put_up_vote(holder, current_item);
                if (mode == 1) {
                    current_item.setPlus_or_minus(0);
                } else if (mode == 2) {
                    current_item.setPlus_or_minus(2);
                } else {
                    current_item.setPlus_or_minus(1);
                }
            }
        }
    }

    private void down_vote_was_cliclked(@NonNull final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        int mode;
        if (current_item.getUp_vote_list().contains(current_item.return_my_user_id())) {
            mode = 1;
        } else if (current_item.getDown_vote_list().contains(current_item.return_my_user_id())) {
            mode = 2;
        } else {
            mode = 3;
        }
        current_item.setWas_this_touched(true);
        if (current_item.isUpvoted()) {
            int upvotes = (current_item.getUp_vote_list().size() - current_item.getDown_vote_list().size()) + current_item.getPlus_or_minus();
            upvotes = upvotes - 2;
            if (upvotes > 1) {
                holder.text_showing_the_number_of_up_votes_comment.setText(String.valueOf(upvotes));
            } else {
                holder.text_showing_the_number_of_up_votes_comment.setText("1");
                Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "Recorded but not shown publicly", Toast.LENGTH_SHORT);
                toast.show();
            }
            color_the_up_vote_down_vote(2, holder, current_item);
            current_item.setAm_i_loading_for_up_vote_and_down_vote(true);
            remove_the_upvote(holder, current_item, true);
            if (mode == 1) {
                current_item.setPlus_or_minus(-2);
            } else if (mode == 2) {
                current_item.setPlus_or_minus(0);
            } else {
                current_item.setPlus_or_minus(-1);
            }
        } else {
            if (current_item.isDown_vote()) {
                int upvotes = (current_item.getUp_vote_list().size() - current_item.getDown_vote_list().size()) + current_item.getPlus_or_minus();
                upvotes++;
                if (upvotes > 1) {
                    holder.text_showing_the_number_of_up_votes_comment.setText(String.valueOf(upvotes));
                } else {
                    holder.text_showing_the_number_of_up_votes_comment.setText("1");
                    Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "Recorded but not shown publicly", Toast.LENGTH_SHORT);
                    toast.show();
                }
                color_the_up_vote_down_vote(0, holder, current_item);
                remove_the_down_vote(holder, current_item, false);
                if (mode == 1) {
                    current_item.setPlus_or_minus(-1);
                } else if (mode == 2) {
                    current_item.setPlus_or_minus(1);
                } else {
                    current_item.setPlus_or_minus(0);
                }
            } else {
                int upvotes = (current_item.getUp_vote_list().size() - current_item.getDown_vote_list().size()) + current_item.getPlus_or_minus();
                upvotes--;
                if (upvotes > 1) {
                    holder.text_showing_the_number_of_up_votes_comment.setText(String.valueOf(upvotes));
                } else {
                    holder.text_showing_the_number_of_up_votes_comment.setText("1");
                    Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "Recorded but not shown publicly", Toast.LENGTH_SHORT);
                    toast.show();
                }
                color_the_up_vote_down_vote(2, holder, current_item);
                put_dwon_vote(holder, current_item);
                if (mode == 1) {
                    current_item.setPlus_or_minus(-2);
                } else if (mode == 2) {
                    current_item.setPlus_or_minus(0);
                } else {
                    current_item.setPlus_or_minus(-1);
                }
            }
        }
    }

    private void put_up_vote(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        final DocumentReference sfDocRef = current_item.getFirebaseFirestore().collection("posts").document(current_item.getDocument_id());
        current_item.getFirebaseFirestore().runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(@NotNull Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);
                ArrayList<Map<String, Object>> array_list_of_map = (ArrayList<Map<String, Object>>) snapshot.get("comments");
                Map<String, Object> map = array_list_of_map.get(current_item.getPosition());
                ArrayList<String> arrayList_with_upvote = (ArrayList<String>) map.get("up_vote_list");
                arrayList_with_upvote.add(current_item.return_my_user_id());
                transaction.update(sfDocRef, "comments", array_list_of_map, "type", "comment");
                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                current_item.setAm_i_loading_for_up_vote_and_down_vote(false);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (!current_item.isAm_i_loading_for_up_vote_and_down_vote()) {
                    Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "Cant upvote please try again later", Toast.LENGTH_SHORT);
                    toast.show();
                }
                current_item.setAm_i_loading_for_up_vote_and_down_vote(false);
            }
        });
    }

    private void remove_the_upvote(final ExampleViewHolder holder, final Example_item_comments_feed current_item, final boolean run_after_this) {
        final DocumentReference sfDocRef = current_item.getFirebaseFirestore().collection("posts").document(current_item.getDocument_id());
        current_item.getFirebaseFirestore().runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(@NotNull Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);

                // Note: this could be done without a transaction
                //       by updating the population using FieldValue.increment()
                ArrayList<Map<String, Object>> array_list_of_map = (ArrayList<Map<String, Object>>) snapshot.get("comments");
                Map<String, Object> map = array_list_of_map.get(current_item.getPosition());
                ArrayList<String> arrayList_with_upvote = (ArrayList<String>) map.get("up_vote_list");
                arrayList_with_upvote.remove(current_item.return_my_user_id());
                map.put("up_vote_list", arrayList_with_upvote);
                Log.w("arrayarray", String.valueOf(current_item.getPosition()));
                transaction.update(sfDocRef, "comments", array_list_of_map, "type", "comment");

                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                if (run_after_this) {
                    put_dwon_vote(holder, current_item);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (!current_item.isAm_i_loading_for_up_vote_and_down_vote()) {
                    Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "Cant remove up vote please try again later", Toast.LENGTH_SHORT);
                    toast.show();
                }
                current_item.setAm_i_loading_for_up_vote_and_down_vote(false);
            }
        });
    }

    private void put_dwon_vote(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        final DocumentReference sfDocRef = current_item.getFirebaseFirestore().collection("posts").document(current_item.getDocument_id());
        current_item.getFirebaseFirestore().runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(@NotNull Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);

                // Note: this could be done without a transaction
                //       by updating the population using FieldValue.increment()
                ArrayList<Map<String, Object>> array_list_of_map = (ArrayList<Map<String, Object>>) snapshot.get("comments");
                Map<String, Object> map = array_list_of_map.get(current_item.getPosition());
                ArrayList<String> arrayList_with_upvote = (ArrayList<String>) map.get("down_vote_list");
                arrayList_with_upvote.add(current_item.return_my_user_id());
                transaction.update(sfDocRef, "comments", array_list_of_map, "type", "comment");
                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                current_item.setAm_i_loading_for_up_vote_and_down_vote(false);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (!current_item.isAm_i_loading_for_up_vote_and_down_vote()) {
                    Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "Cant down vote please try again later", Toast.LENGTH_SHORT);
                    toast.show();
                }
                current_item.setAm_i_loading_for_up_vote_and_down_vote(false);
            }
        });
    }

    private void remove_the_down_vote(final ExampleViewHolder holder, final Example_item_comments_feed current_item, final boolean start_after_this) {
        final DocumentReference sfDocRef = current_item.getFirebaseFirestore().collection("posts").document(current_item.getDocument_id());
        current_item.getFirebaseFirestore().runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(@NotNull Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);

                // Note: this could be done without a transaction
                //       by updating the population using FieldValue.increment()
                ArrayList<Map<String, Object>> array_list_of_map = (ArrayList<Map<String, Object>>) snapshot.get("comments");
                Map<String, Object> map = array_list_of_map.get(current_item.getPosition());
                ArrayList<String> arrayList_with_upvote = (ArrayList<String>) map.get("down_vote_list");
                arrayList_with_upvote.remove(current_item.return_my_user_id());
                transaction.update(sfDocRef, "comments", array_list_of_map, "type", "comment");

                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                if (start_after_this) {
                    put_up_vote(holder, current_item);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (!current_item.isAm_i_loading_for_up_vote_and_down_vote()) {
                    Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "Cant remove down vote please try again later", Toast.LENGTH_SHORT);
                    toast.show();
                }
                current_item.setAm_i_loading_for_up_vote_and_down_vote(false);
            }
        });
    }

    private void share_button_lsiten(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        holder.button_to_watch_share_in_card_in_post_in_top_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share_button_was_clicked_listen.share_just_got_clciked(current_item.getBody());
            }
        });
    }

    private String first_letter_capital(String sentence) {
        return sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
    }

    private void set_is_saved(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        if (current_item.isSaved() || does_commetn_already_saved(holder, current_item)) {
            Drawable saved = ContextCompat.getDrawable(holder.constriant_inside_card_inside_post_feed_comment.getContext(), R.drawable.round_turned_in_24);
            holder.save_button_in_card_in_posts_comment.setBackground(saved);
        } else {
            Drawable not_saved = ContextCompat.getDrawable(holder.constriant_inside_card_inside_post_feed_comment.getContext(), R.drawable.round_turned_in_not_24);
            holder.save_button_in_card_in_posts_comment.setBackground(not_saved);
        }
    }

    private void reply_button_listen(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        holder.reply_button_in_tem_id_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (am_i_signed_in_with_google(current_item)) {
                    reply_button_was_clicked_listener.reply_was_clicked_in_commenmt(current_item.getPosition(), current_item.getBody(), holder.getAdapterPosition());
                } else {
                    if (up_vote_listener_listen != null) {
                        up_vote_listener_listen.up_vote_down_vote_was_clciked("comment");
                    }
                }
            }
        });
    }

    private void set_up_the_recylce_view(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        RecyclerView recyclerView = holder.recylce_view_insode_each_comment_showing_replies;
        if (current_item.getReplies().size() == 0) {
            holder.recylce_view_insode_each_comment_showing_replies.setVisibility(View.GONE);
        } else {
            holder.recylce_view_insode_each_comment_showing_replies.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.constriant_inside_card_inside_post_feed_comment.getContext());
            ArrayList<Example_reply_to_comment> example_list = new ArrayList<>();
            if (!current_item.isIs_this_switched()) {
                for (int i = 0; i < current_item.getReplies().size(); i++) {
                    example_list.add(new Example_reply_to_comment(current_item.getReplies().get(i), current_item.getFirebaseFirestore(), current_item.getFirebaseUser(), current_item.getPosition(), current_item.getReplies().size(), i, current_item.getDocument_id(), current_item.getTitle_of_the_main_post(), current_item.getBody_of_the_main_post(), current_item.getSpan_of_the_main_post(), current_item.getTime_of_the_main_post(), current_item.getCategory_of_the_main_post(), current_item.getStreak_of_the_main_post(), current_item.getName(), current_item.getBody(), current_item.getTime(), current_item.getCategory(), current_item.getStreak(), current_item.getName(), current_item.isIs_this_an_archived_comment()));
                }
            } else {
                example_list.add(new Example_reply_to_comment(current_item.getReplies().get(0), current_item.getFirebaseFirestore(), current_item.getFirebaseUser(), current_item.getPosition(), current_item.getReplies().size(), current_item.getIndex_of_comment_switched(), current_item.getDocument_id(), current_item.getTitle_of_the_main_post(), current_item.getBody_of_the_main_post(), current_item.getSpan_of_the_main_post(), current_item.getTime_of_the_main_post(), current_item.getCategory_of_the_main_post(), current_item.getStreak_of_the_main_post(), current_item.getName(), current_item.getBody(), current_item.getTime(), current_item.getCategory(), current_item.getStreak(), current_item.getName(), current_item.isIs_this_an_archived_comment()));
                for (int i = 1; i < current_item.getReplies().size(); i++) {
                    if (i == current_item.getIndex_of_comment_switched()) {
                        example_list.add(new Example_reply_to_comment(current_item.getReplies().get(i), current_item.getFirebaseFirestore(), current_item.getFirebaseUser(), current_item.getPosition(), current_item.getReplies().size(), 0, current_item.getDocument_id(), current_item.getTitle_of_the_main_post(), current_item.getBody_of_the_main_post(), current_item.getSpan_of_the_main_post(), current_item.getTime_of_the_main_post(), current_item.getCategory_of_the_main_post(), current_item.getStreak_of_the_main_post(), current_item.getName(), current_item.getBody(), current_item.getTime(), current_item.getCategory(), current_item.getStreak(), current_item.getName(), current_item.isIs_this_an_archived_comment()));
                    } else {
                        example_list.add(new Example_reply_to_comment(current_item.getReplies().get(i), current_item.getFirebaseFirestore(), current_item.getFirebaseUser(), current_item.getPosition(), current_item.getReplies().size(), i, current_item.getDocument_id(), current_item.getTitle_of_the_main_post(), current_item.getBody_of_the_main_post(), current_item.getSpan_of_the_main_post(), current_item.getTime_of_the_main_post(), current_item.getCategory_of_the_main_post(), current_item.getStreak_of_the_main_post(), current_item.getName(), current_item.getBody(), current_item.getTime(), current_item.getCategory(), current_item.getStreak(), current_item.getName(), current_item.isIs_this_an_archived_comment()));
                    }
                }
            }
            Adapter_for_comment_replies adapter = new Adapter_for_comment_replies(example_list);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setHasFixedSize(false);
            adapter.set_reply_clicked_listen(new Adapter_for_comment_replies.share_button_was_clicked_nested() {
                @Override
                public void share_button_nested(String comment) {
                    share_button_was_clicked_listen.share_just_got_clciked(comment);
                }
            });
            adapter.set_reply_button_clicked_lsiten(new Adapter_for_comment_replies.reply_button_was_clicked() {
                @Override
                public void reply_button_clicked(String body, String name, String main_comment_reply_id) {
                    reply_nested_button_nested_interface_liesten.reply_button_clicked_nested_one(name, body, main_comment_reply_id, current_item.getPosition(), holder.getAdapterPosition());
                }
            });
            adapter.set_up_vote_down_vote_clicked(new Adapter_for_comment_replies.Up_vote_down_vote_clicked() {
                @Override
                public void up_vote_down_vote_button_clicked(String body) {
                    up_vote_listener_listen.up_vote_down_vote_was_clciked(body);
                }
            });
            adapter.set_up_gift_listen(new Adapter_for_comment_replies.gift_button_listen() {
                @Override
                public void gift_was_clicked(String user_id, String document_id, int comment_position, int reply_position, ArrayList<Long> awards) {
                    gift_button_listen_listener.gift_was_clicked(user_id, document_id, comment_position, reply_position, awards);
                }
            });
            adapter.set_up_save_is_only_for_bro(new Adapter_for_comment_replies.save_only_for_pro_lsiten() {
                @Override
                public void save_is_only_for_pro() {
                    save_is_only_for_pro_listen.save_is_for_pro("reply");
                }
            });
        }
    }

    private void set_the_name(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        holder.text_name_in_the_card_of_the_post_user_name.setText(current_item.getName());
    }

    private void buttons_listen_archived(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {

        holder.button_to_watch_upvote_in_card_in_post_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "Can't up vote archived post", Toast.LENGTH_SHORT).show();
            }
        });
        holder.button_to_watch_downvote_in_card_in_post_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "Can't down vote archived post", Toast.LENGTH_SHORT).show();

            }
        });
        holder.reply_button_in_tem_id_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "Can't reply to archived post", Toast.LENGTH_SHORT).show();
            }
        });
        holder.button_to_watch_gift_in_card_in_post_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "Can't gift to archived post", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void save_button_listen_archived_your_comment(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        holder.button_to_watch_save_in_card_in_post_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.constriant_inside_card_inside_post_feed_comment.getContext(), "Comment Already saved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void gift_button_listen(final ExampleViewHolder holder, final Example_item_comments_feed current_item) {
        holder.button_to_watch_gift_in_card_in_post_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gift_button_listen_listener.gift_was_clicked(current_item.getUser_id(), current_item.getDocument_id(), current_item.getPosition(), -100, current_item.getAwards());
            }
        });
    }
}
