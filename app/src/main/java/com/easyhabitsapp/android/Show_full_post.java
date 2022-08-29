package com.easyhabitsapp.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.PopupMenu;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static android.content.Context.MODE_PRIVATE;

public class Show_full_post extends Fragment {
    private int position;
    private String m_title;
    private String m_body;
    private String m_category;
    private Date m_time;
    private int m_streak;
    private String m_span;
    private boolean m_image;
    private String m_user_id;
    private ArrayList<HashMap<String, Object>> m_comments;
    private ArrayList<Long> m_awards;
    private boolean is_this_upvote;
    private boolean is_this_down_vote;
    private String m_document_id;
    private boolean m_is_this_from_fire_base;
    private ArrayList<String> m_upvotes;
    private ArrayList<String> m_downvotes;
    private boolean post_saved;
    private int plus_or_minus;
    private boolean m_is_this_laoding;
    private boolean i_already_loaded;
    private boolean upvote_or_down_vote_was_clicked;
    private boolean was_this_reported;
    private ArrayList<String> m_list_of_reports;
    private boolean am_i_loading_up_voote_down_vote_from_fire_base;
    private boolean did_i_see_this_tem;
    private ArrayList<String> m_list_of_seen_posts;
    private boolean m_is_post_by_dev;
    private ArrayList<HashMap<String, Object>> remove_reports_list;
    private String which_post_called_me;
    private String name_of_the_user;
    private int position_of_comment;
    private int position_of_reply;
    private String what_is_the_type_of_this;
    private String real_user_id;

    private FirebaseFirestore m_firebaseFirestore = FirebaseFirestore.getInstance();
    private String sort_type = "hot";
    private String previous_sort_type = "hot";

    private RecyclerView recyclerView;
    private Adapter_for_comment_post adapter;
    private LinearLayoutManager linearLayoutManager;
    private final ArrayList<Example_item_comments_feed> example_list = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_full_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            /*title_for_post = bundle.getString("title");
            body_for_post = bundle.getString("body");
            likes_from_post = bundle.getInt("likes");
            is_post_upvoted = bundle.getBoolean("up_voted");
            is_post_down_voted = bundle.getBoolean("down_vote");
            long number_of_comments = bundle.getLong("number_of_comments");
            is_this_posts_saved = bundle.getBoolean("saved");
            boolean dev = bundle.getBoolean("dev");
            time_for_post = bundle.getLong("time");
            category_for_post = bundle.getString("category");
            streak_for_post = bundle.getInt("streak");
            is_this_already_reported = bundle.getBoolean("reported");
            long plat = bundle.getLong("plat");
            long gold = bundle.getLong("gold");
            long silver = bundle.getLong("silver");
            String user_id = bundle.getString("id");
            document_id_for_fire_base = bundle.getString("document_id");
            span_for_post = bundle.getString("span");
            comments_for_post = (ArrayList<HashMap<String, Object>>) bundle.getSerializable("comments");*/
            position = bundle.getInt("position");
            m_title = bundle.getString("title");
            m_body = bundle.getString("body");
           // m_category = bundle.getString("category");
            m_time = (Date) bundle.getSerializable("time");
            //m_streak = bundle.getInt("streak");
            m_span = bundle.getString("span");
            m_image = bundle.getBoolean("image");
            m_user_id =  FirebaseAuth.getInstance().getCurrentUser().getUid();
            m_comments = (ArrayList<HashMap<String, Object>>) bundle.getSerializable("comments");
            m_awards = (ArrayList<Long>) bundle.getSerializable("awards");
            is_this_upvote = bundle.getBoolean("is_this_upvoted");
            is_this_down_vote = bundle.getBoolean("is_this_downvote");
            m_document_id = bundle.getString("document_id");
            m_is_this_from_fire_base = bundle.getBoolean("is_this_from_firebase");
            m_upvotes = (ArrayList<String>) bundle.getSerializable("list_of_upvotes");
            m_downvotes = (ArrayList<String>) bundle.getSerializable("list_of_downvotes");
            post_saved = bundle.getBoolean("post_saved");
            plus_or_minus = bundle.getInt("plus_or_minus");
            m_is_this_laoding = bundle.getBoolean("is_this_loading");
            i_already_loaded = bundle.getBoolean("i_already_loaded");
            upvote_or_down_vote_was_clicked = bundle.getBoolean("was_upvote_down_vote_clicked");
            was_this_reported = bundle.getBoolean("was_this_reported");
            m_list_of_reports = (ArrayList<String>) bundle.getSerializable("list_of_reports");
            am_i_loading_up_voote_down_vote_from_fire_base = false;
            did_i_see_this_tem = bundle.getBoolean("did_i_see_this_item");
            m_list_of_seen_posts = (ArrayList<String>) bundle.getSerializable("list_of_seen_posts");
            m_is_post_by_dev = bundle.getBoolean("m_is_post_by_dev");
            which_post_called_me = bundle.getString("which_post_called_me");
            name_of_the_user = bundle.getString("name_of_posted");
            what_is_the_type_of_this = bundle.getString("what_is_the_type_of_this", "normal");
            real_user_id = bundle.getString("user_id");
            Log.w("test123",what_is_the_type_of_this);
            if (what_is_the_type_of_this.equals("normal")) {
                if (position == -1) {
                    position_of_comment = bundle.getInt("comment_position", 0);
                } else if (position == -2) {
                    position_of_comment = bundle.getInt("comment_position", 0);
                    position_of_reply = bundle.getInt("reply_position", 0);
                }
                set_the_four_text_at_top(m_time.getTime(), m_category, m_streak, m_is_post_by_dev);
                set_up_title_and_body(m_title, m_body);
                set_up_likes_and_comments((m_upvotes.size() - m_downvotes.size()) + plus_or_minus, m_comments.size(), is_this_upvote, is_this_down_vote);
                set_up_the_save(post_saved);
                set_up_the_back_ground_colors_of_the_ripple();
                set_up_the_awards(m_awards.get(0).intValue(), m_awards.get(1).intValue(), m_awards.get(2).intValue());
                three_dot_show_menu_lsiten();
                back_button_listen();
                save_button_click_listen();
                up_vote_button_click();
                down_vote_button_click();
                sort_button_listen();
                set_up_comment_recycle_view();
                add_comment_button_listen();
                sort_stuff_correctly();
                if (position == -1) {
                    add_comment_at_start();
                } else if (position == -2) {
                    add_reply_at_start();
                }
                set_up_the_comments();
                share_button_listen();
                set_the_text_at_the_top();
                set_the_bar_color_under_title();
                set_up_the_name(name_of_the_user);
                gift_button_listen();
            } else if (what_is_the_type_of_this.equals("comment_online")) {
                position_of_comment = bundle.getInt("comment_position", 0);
                set_the_four_text_at_top(m_time.getTime(), m_category, m_streak, m_is_post_by_dev);
                set_up_title_and_body(m_title, m_body);
                set_up_likes_and_comments((m_upvotes.size() - m_downvotes.size()) + plus_or_minus, m_comments.size(), is_this_upvote, is_this_down_vote);
                set_up_the_save(post_saved);
                set_up_the_back_ground_colors_of_the_ripple();
                set_up_the_awards(m_awards.get(0).intValue(), m_awards.get(1).intValue(), m_awards.get(2).intValue());
                three_dot_show_menu_lsiten();
                back_button_listen();
                save_button_click_listen();
                up_vote_button_click();
                down_vote_button_click();
                sort_button_listen();
                set_up_comment_recycle_view();
                add_comment_button_listen();
                sort_stuff_correctly();
                add_comment_at_start();
                set_up_the_comments();
                share_button_listen();
                set_the_text_at_the_top();
                set_the_bar_color_under_title();
                set_up_the_name(name_of_the_user);
                gift_button_listen();
            } else if (what_is_the_type_of_this.equals("reply_online")) {
                position_of_comment = bundle.getInt("comment_position", 0);
                position_of_reply = bundle.getInt("reply_position", 0);
                set_the_four_text_at_top(m_time.getTime(), m_category, m_streak, m_is_post_by_dev);
                set_up_title_and_body(m_title, m_body);
                set_up_likes_and_comments((m_upvotes.size() - m_downvotes.size()) + plus_or_minus, m_comments.size(), is_this_upvote, is_this_down_vote);
                set_up_the_save(post_saved);
                set_up_the_back_ground_colors_of_the_ripple();
                set_up_the_awards(m_awards.get(0).intValue(), m_awards.get(1).intValue(), m_awards.get(2).intValue());
                three_dot_show_menu_lsiten();
                back_button_listen();
                save_button_click_listen();
                up_vote_button_click();
                down_vote_button_click();
                sort_button_listen();
                set_up_comment_recycle_view();
                add_comment_button_listen();
                sort_stuff_correctly();
                //add_comment_at_start();
                add_reply_at_start();
                set_up_the_comments();
                share_button_listen();
                set_the_text_at_the_top();
                set_the_bar_color_under_title();
                set_up_the_name(name_of_the_user);
                gift_button_listen();
            } else if (what_is_the_type_of_this.equals("your_post_offline")) {
                set_the_four_text_at_top(m_time.getTime(), m_category, m_streak, false);
                set_up_title_and_body(m_title, m_body);
                set_up_likes_and_comments(1, 0, false, false);
                set_up_the_save(post_saved);
                set_up_the_back_ground_colors_of_the_ripple();
                set_up_the_awards(0, 0, 0);
                back_button_listen();
                share_button_listen();
                set_the_text_at_the_top();
                set_the_bar_color_under_title();
                set_up_the_name(name_of_the_user);
                make_the_text_saying_no_comments_visible();
                all_buttons_listen_for_old_post();
                save_button_click_listen();
            } else if (what_is_the_type_of_this.equals("your_post_offline_comment")) {
                set_the_four_text_at_top(m_time.getTime(), m_category, m_streak, false);
                set_up_title_and_body(m_title, m_body);
                set_up_likes_and_comments(1, 0, false, false);
                set_up_the_save(post_saved);
                set_up_the_back_ground_colors_of_the_ripple();
                set_up_the_awards(0, 0, 0);
                back_button_listen();
                share_button_listen();
                set_the_text_at_the_top();
                set_the_bar_color_under_title();
                set_up_the_name(name_of_the_user);
                all_buttons_listen_for_old_post();
                set_up_comment_recycle_view();
                String body_of_the_comment = bundle.getString("body_comment");
                String name_of_the_comment = bundle.getString("name_comment");
                Date time_of_the_comment = (Date) bundle.getSerializable("time_comment");
                String category_comment = bundle.getString("category_comment");
                int streak_of_the_comment = bundle.getInt("streak_comment");
                int position_of_comment = bundle.getInt("position_of_comment");
                add_the_comment_saved(body_of_the_comment, name_of_the_comment, time_of_the_comment, category_comment, streak_of_the_comment, false, position_of_comment);
                save_button_click_listen();
            } else if (what_is_the_type_of_this.equals("your_post_offline_reply")) {
                set_the_four_text_at_top(m_time.getTime(), m_category, m_streak, false);
                set_up_title_and_body(m_title, m_body);
                set_up_likes_and_comments(1, 0, false, false);
                set_up_the_save(post_saved);
                set_up_the_back_ground_colors_of_the_ripple();
                set_up_the_awards(0, 0, 0);
                back_button_listen();
                share_button_listen();
                set_the_text_at_the_top();
                set_the_bar_color_under_title();
                set_up_the_name(name_of_the_user);
                //make_the_text_saying_no_comments_visible();
                all_buttons_listen_for_old_post();
                set_up_comment_recycle_view();
                String body_of_the_comment = bundle.getString("body_comment");
                String name_of_the_comment = bundle.getString("name_comment");
                Date time_of_the_comment = (Date) bundle.getSerializable("time_comment");
                String category_comment = bundle.getString("category_comment");
                int streak_of_the_comment = bundle.getInt("streak_comment");
                int position_of_comment = bundle.getInt("position_of_comment");

                String body_reply = bundle.getString("body_reply");
                int position_reply = bundle.getInt("position_of_reply");
                String name_reply = bundle.getString("name_reply");
                Date time_reply = (Date) bundle.getSerializable("time_reply");
                String category_reply = bundle.getString("category_reply");
                int streak_reply = bundle.getInt("streak_reply");
                add_comment_reply(body_of_the_comment, name_of_the_comment, time_of_the_comment, category_comment, streak_of_the_comment, position_of_comment, body_reply, position_reply, name_reply, time_reply, category_reply, streak_reply);
                save_button_click_listen();
            } else if (what_is_the_type_of_this.equals("saved_post_offline")) {
                set_the_four_text_at_top(m_time.getTime(), m_category, m_streak, false);
                set_up_title_and_body(m_title, m_body);
                set_up_likes_and_comments(1, 0, false, false);
                set_up_the_save(post_saved);
                set_up_the_back_ground_colors_of_the_ripple();
                set_up_the_awards(0, 0, 0);
                back_button_listen();
                share_button_listen();
                set_the_text_at_the_top();
                set_the_bar_color_under_title();
                set_up_the_name(name_of_the_user);
                make_the_text_saying_no_comments_visible();
                all_buttons_listen_for_old_post();
                save_button_click_listen();
            } else if (what_is_the_type_of_this.equals("saved_post_offline_comment")) {
                set_the_four_text_at_top(m_time.getTime(), m_category, m_streak, false);
                set_up_title_and_body(m_title, m_body);
                set_up_likes_and_comments(1, 0, false, false);
                set_up_the_save(post_saved);
                set_up_the_back_ground_colors_of_the_ripple();
                set_up_the_awards(0, 0, 0);
                back_button_listen();
                share_button_listen();
                set_the_text_at_the_top();
                set_the_bar_color_under_title();
                set_up_the_name(name_of_the_user);
                //make_the_text_saying_no_comments_visible();
                all_buttons_listen_for_old_post();
                set_up_comment_recycle_view();
                String body_of_the_comment = bundle.getString("body_comment");
                String name_of_the_comment = bundle.getString("name_comment");
                Date time_of_the_comment = (Date) bundle.getSerializable("time_comment");
                String category_comment = bundle.getString("category_comment");
                int streak_of_the_comment = bundle.getInt("streak_comment");
                int position_of_comment = bundle.getInt("position_of_comment");
                add_the_comment_saved(body_of_the_comment, name_of_the_comment, time_of_the_comment, category_comment, streak_of_the_comment, true, position_of_comment);
                save_button_click_listen();
            } else if (what_is_the_type_of_this.equals("saved_post_offline_reply")) {
                set_the_four_text_at_top(m_time.getTime(), m_category, m_streak, false);
                set_up_title_and_body(m_title, m_body);
                set_up_likes_and_comments(1, 0, false, false);
                set_up_the_save(post_saved);
                set_up_the_back_ground_colors_of_the_ripple();
                set_up_the_awards(0, 0, 0);
                back_button_listen();
                share_button_listen();
                set_the_text_at_the_top();
                set_the_bar_color_under_title();
                set_up_the_name(name_of_the_user);
                //make_the_text_saying_no_comments_visible();
                all_buttons_listen_for_old_post();
                set_up_comment_recycle_view();
                String body_of_the_comment = bundle.getString("body_comment");
                String name_of_the_comment = bundle.getString("name_comment");
                Date time_of_the_comment = (Date) bundle.getSerializable("time_comment");
                String category_comment = bundle.getString("category_comment");
                int streak_of_the_comment = bundle.getInt("streak_comment");
                int position_of_comment = bundle.getInt("position_of_comment");

                String body_reply = bundle.getString("body_reply");
                int position_reply = bundle.getInt("position_of_reply");
                String name_reply = bundle.getString("name_reply");
                Date time_reply = (Date) bundle.getSerializable("time_reply");
                String category_reply = bundle.getString("category_reply");
                int streak_reply = bundle.getInt("streak_reply");
                add_comment_reply(body_of_the_comment, name_of_the_comment, time_of_the_comment, category_comment, streak_of_the_comment, position_of_comment, body_reply, position_reply, name_reply, time_reply, category_reply, streak_reply);
                save_button_click_listen();
            }
        }
    }

    private void set_the_four_text_at_top(long time, String category, int streak, boolean dev) {
        if (getView() != null) {
            TextView text_at_top_left_of_card_to_show_time = getView().findViewById(R.id.text_at_top_left_of_card_to_show_time);
            //TextView text_at_the_top_showing_what_category_this_is_in = getView().findViewById(R.id.text_at_the_top_showing_what_category_this_is_in);
            //TextView text_at_the_top_showing_how_much_is_the_streak = getView().findViewById(R.id.text_at_the_top_showing_how_much_is_the_streak);
            //View circle_between_time_and_cat_in_streak_in_post = getView().findViewById(R.id.circle_between_time_and_cat_in_streak_in_post);
            ConstraintLayout layout_inside_nested_layout_for_comments_in_full_post = getView().findViewById(R.id.layout_inside_nested_layout_for_comments_in_full_post);
            //View circle_between_the_streak_and_the_dev_icon = getView().findViewById(R.id.circle_between_the_streak_and_the_dev_icon);
            View view_behind_i_am_the_dev_of_the_text = getView().findViewById(R.id.view_behind_i_am_the_dev_of_the_text);
            TextView text_view_saying_i_am_the_dev_of_the_app = getView().findViewById(R.id.text_view_saying_i_am_the_dev_of_the_app);
            View circle_between_time_and_dev_in_card = getView().findViewById(R.id.circle_between_time_and_dev_in_card);
            text_at_top_left_of_card_to_show_time.setText(return_time_at_top(time));
            //text_at_the_top_showing_what_category_this_is_in.setText(category);
            /*if (streak < 0) {
                text_at_the_top_showing_how_much_is_the_streak.setVisibility(View.INVISIBLE);
                circle_between_time_and_cat_in_streak_in_post.setVisibility(View.INVISIBLE);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(layout_inside_nested_layout_for_comments_in_full_post);
                constraintSet.connect(circle_between_the_streak_and_the_dev_icon.getId(), ConstraintSet.START, text_at_the_top_showing_what_category_this_is_in.getId(), ConstraintSet.END, (int) convertDpToPixel(5, getContext()));
                constraintSet.applyTo(layout_inside_nested_layout_for_comments_in_full_post);
            } else {
                if (streak == 1) {
                    text_at_the_top_showing_how_much_is_the_streak.setText("1 day");
                } else {
                    text_at_the_top_showing_how_much_is_the_streak.setText(String.valueOf(streak).concat(" days"));
                }
            }*/
            if (!dev) {
                view_behind_i_am_the_dev_of_the_text.setVisibility(View.INVISIBLE);
                text_view_saying_i_am_the_dev_of_the_app.setVisibility(View.INVISIBLE);
                circle_between_time_and_dev_in_card.setVisibility(View.INVISIBLE);
            }
        }
    }

    private String return_time_at_top(long date) {
        long differnce = System.currentTimeMillis() - date;
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

    private static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private void set_up_the_awards(int plat, int gold, int silver) {
        if (getView() != null) {
            ConstraintLayout layout_inside_nested_layout_for_comments_in_full_post = getView().findViewById(R.id.layout_inside_nested_layout_for_comments_in_full_post);
            TextView text_for_full_post = getView().findViewById(R.id.text_for_full_post);
            TextView text_at_top_left_of_card_to_show_time = getView().findViewById(R.id.text_at_top_left_of_card_to_show_time);
            View start_part_of_the_grey_part_in_post_award = getView().findViewById(R.id.start_part_of_the_grey_part_in_post_award);
            View middle_part_of_the_grey_part_in_post_award = getView().findViewById(R.id.middle_part_of_the_grey_part_in_post_award);
            View end_part_of_the_grey_part_in_post_award = getView().findViewById(R.id.end_part_of_the_grey_part_in_post_award);
            TextView text_saying_the_number_of_awards = getView().findViewById(R.id.text_saying_the_number_of_awards);
            View circle_between_awards_and_plat_in_post = getView().findViewById(R.id.circle_between_awards_and_plat_in_post);
            View view_showing_the_plat_award = getView().findViewById(R.id.view_showing_the_plat_award);
            TextView plat_text_beside_the_award_posts = getView().findViewById(R.id.plat_text_beside_the_award_posts);
            View circle_between_plat_and_gold_in_post = getView().findViewById(R.id.circle_between_plat_and_gold_in_post);
            View view_showing_the_gold_award = getView().findViewById(R.id.view_showing_the_gold_award);
            TextView plat_text_beside_the_gold_award_posts = getView().findViewById(R.id.plat_text_beside_the_gold_award_posts);
            View circle_between_gold_and_silver_in_post = getView().findViewById(R.id.circle_between_gold_and_silver_in_post);
            View view_showing_the_silver_award = getView().findViewById(R.id.view_showing_the_silver_award);
            TextView silver_text_beside_the_award_post = getView().findViewById(R.id.silver_text_beside_the_award_post);
            if (plat == 0 && gold == 0 && silver == 0) {
                start_part_of_the_grey_part_in_post_award.setVisibility(View.GONE);
                middle_part_of_the_grey_part_in_post_award.setVisibility(View.GONE);
                end_part_of_the_grey_part_in_post_award.setVisibility(View.GONE);
                text_saying_the_number_of_awards.setVisibility(View.GONE);
                circle_between_awards_and_plat_in_post.setVisibility(View.GONE);
                view_showing_the_plat_award.setVisibility(View.GONE);
                plat_text_beside_the_award_posts.setVisibility(View.GONE);
                circle_between_plat_and_gold_in_post.setVisibility(View.GONE);
                view_showing_the_gold_award.setVisibility(View.GONE);
                plat_text_beside_the_gold_award_posts.setVisibility(View.GONE);
                circle_between_gold_and_silver_in_post.setVisibility(View.GONE);
                view_showing_the_silver_award.setVisibility(View.GONE);
                silver_text_beside_the_award_post.setVisibility(View.GONE);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(layout_inside_nested_layout_for_comments_in_full_post);
                constraintSet.connect(text_for_full_post.getId(), ConstraintSet.TOP, text_at_top_left_of_card_to_show_time.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(20, getContext()));
                constraintSet.applyTo(layout_inside_nested_layout_for_comments_in_full_post);
            } else {
                plat_text_beside_the_award_posts.setText(String.valueOf(plat));
                plat_text_beside_the_gold_award_posts.setText(String.valueOf(gold));
                silver_text_beside_the_award_post.setText(String.valueOf(silver));
                long awards_total_number = plat + gold + silver;
                if (awards_total_number == 1) {
                    text_saying_the_number_of_awards.setText("1 award");
                } else {
                    text_saying_the_number_of_awards.setText(String.valueOf(awards_total_number).concat(" awards"));
                }
                if (plat == 0 && gold == 0 && silver > 0) {
                    make_the_plat_invisible();
                    make_the_gold_invisible();
                    conect_me_to_start(1);
                } else if (plat == 0 && gold > 0 && silver == 0) {
                    make_the_plat_invisible();
                    make_the_silver_invisible();
                    conect_me_to_start(0);
                } else if (plat == 0 && gold > 0 && silver > 0) {
                    make_the_plat_invisible();
                    conect_me_to_start(0);
                } else if (plat > 0 && gold == 0 && silver == 0) {
                    make_the_gold_invisible();
                    make_the_silver_invisible();
                } else if (plat > 0 && gold == 0 && silver > 0) {
                    make_the_gold_invisible();
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(layout_inside_nested_layout_for_comments_in_full_post);
                    constraintSet.connect(circle_between_gold_and_silver_in_post.getId(), ConstraintSet.START, plat_text_beside_the_award_posts.getId(), ConstraintSet.END, (int) convertDpToPixel(5, getContext()));
                    constraintSet.applyTo(layout_inside_nested_layout_for_comments_in_full_post);
                } else if (plat > 0 && gold > 0 && silver == 0) {
                    make_the_silver_invisible();
                }
            }
        }
    }

    private void make_the_plat_invisible() {
        if (getView() != null) {
            View circle_between_awards_and_plat_in_post = getView().findViewById(R.id.circle_between_awards_and_plat_in_post);
            View view_showing_the_plat_award = getView().findViewById(R.id.view_showing_the_plat_award);
            TextView plat_text_beside_the_award_posts = getView().findViewById(R.id.plat_text_beside_the_award_posts);
            circle_between_awards_and_plat_in_post.setVisibility(View.GONE);
            view_showing_the_plat_award.setVisibility(View.GONE);
            plat_text_beside_the_award_posts.setVisibility(View.GONE);
        }
    }

    private void make_the_gold_invisible() {
        if (getView() != null) {
            View circle_between_plat_and_gold_in_post = getView().findViewById(R.id.circle_between_plat_and_gold_in_post);
            View view_showing_the_gold_award = getView().findViewById(R.id.view_showing_the_gold_award);
            TextView plat_text_beside_the_gold_award_posts = getView().findViewById(R.id.plat_text_beside_the_gold_award_posts);
            circle_between_plat_and_gold_in_post.setVisibility(View.GONE);
            view_showing_the_gold_award.setVisibility(View.GONE);
            plat_text_beside_the_gold_award_posts.setVisibility(View.GONE);
        }
    }

    private void make_the_silver_invisible() {
        if (getView() != null) {
            View circle_between_gold_and_silver_in_post = getView().findViewById(R.id.circle_between_gold_and_silver_in_post);
            View view_showing_the_silver_award = getView().findViewById(R.id.view_showing_the_silver_award);
            TextView silver_text_beside_the_award_post = getView().findViewById(R.id.silver_text_beside_the_award_post);
            circle_between_gold_and_silver_in_post.setVisibility(View.GONE);
            view_showing_the_silver_award.setVisibility(View.GONE);
            silver_text_beside_the_award_post.setVisibility(View.GONE);
        }
    }

    private void conect_me_to_start(int which) {
        ConstraintLayout layout_inside_nested_layout_for_comments_in_full_post = getView().findViewById(R.id.layout_inside_nested_layout_for_comments_in_full_post);
        View circle_between_plat_and_gold_in_post = getView().findViewById(R.id.circle_between_plat_and_gold_in_post);
        View circle_between_gold_and_silver_in_post = getView().findViewById(R.id.circle_between_gold_and_silver_in_post);
        TextView text_saying_the_number_of_awards = getView().findViewById(R.id.text_saying_the_number_of_awards);
        if (which == 1) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(layout_inside_nested_layout_for_comments_in_full_post);
            constraintSet.connect(circle_between_plat_and_gold_in_post.getId(), ConstraintSet.START, text_saying_the_number_of_awards.getId(), ConstraintSet.END, (int) convertDpToPixel(5, getContext()));
            constraintSet.applyTo(layout_inside_nested_layout_for_comments_in_full_post);
        } else if (which == 2) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(layout_inside_nested_layout_for_comments_in_full_post);
            constraintSet.connect(circle_between_gold_and_silver_in_post.getId(), ConstraintSet.START, text_saying_the_number_of_awards.getId(), ConstraintSet.END, (int) convertDpToPixel(5, getContext()));
            constraintSet.applyTo(layout_inside_nested_layout_for_comments_in_full_post);
        }
    }

    private void set_up_title_and_body(String title, String body) {
        if (getView() != null) {
            TextView text_for_full_post = getView().findViewById(R.id.text_for_full_post);
            TextView body_text_view_showing_the_full_post = getView().findViewById(R.id.body_text_view_showing_the_full_post);
            text_for_full_post.setText(first_letter_capital(title));
            body_text_view_showing_the_full_post.setText(first_letter_capital(body));
        }
    }

    private void set_up_likes_and_comments(int likes, int comments, boolean up_vote, boolean down_vote) {
        if (getView() != null && getContext() != null) {
            TextView text_showing_the_number_of_up_votes = getView().findViewById(R.id.text_showing_the_number_of_up_votes);
            TextView text_telling_the_number_of_comments_in_card_in_post = getView().findViewById(R.id.text_telling_the_number_of_comments_in_card_in_post);
            View upvote_view_in_post_card = getView().findViewById(R.id.upvote_view_in_post_card);
            View down_vote_view_in_post_card = getView().findViewById(R.id.down_vote_view_in_post_card);
            if (likes >= 1) {
                text_showing_the_number_of_up_votes.setText(String.valueOf(likes));
            } else {
                text_showing_the_number_of_up_votes.setText("1");
            }
            text_telling_the_number_of_comments_in_card_in_post.setText(String.valueOf(comments));
            if (up_vote) {
                text_showing_the_number_of_up_votes.setTextColor(Color.parseColor("#FF4500"));
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_arrow_drop_up_24).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#FF4500"));
                upvote_view_in_post_card.setBackground(wrappedDrawable);
            } else if (down_vote) {
                text_showing_the_number_of_up_votes.setTextColor(Color.parseColor("#9494FF"));
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.arrow_down_down_vote_card).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#9494FF"));
                down_vote_view_in_post_card.setBackground(wrappedDrawable);
            }
        }
    }

    private void set_up_the_save(boolean saved) {
        if (getView() != null && getContext() != null) {
            View save_button_in_card_in_posts = getView().findViewById(R.id.save_button_in_card_in_posts);
            View save_view_in_full_view_in_post = getView().findViewById(R.id.save_view_in_full_view_in_post);
            Drawable not_saved = ContextCompat.getDrawable(getContext(), R.drawable.round_turned_in_not_24);
            Drawable saved_drawable = ContextCompat.getDrawable(getContext(), R.drawable.round_turned_in_24);
            Drawable not_saved_white = ContextCompat.getDrawable(getContext(), R.drawable.white_no_save);
            Drawable saved_white = ContextCompat.getDrawable(getContext(), R.drawable.white_save);
            if (saved) {
                save_button_in_card_in_posts.setBackground(saved_drawable);
                save_view_in_full_view_in_post.setBackground(saved_white);
            } else {
                save_button_in_card_in_posts.setBackground(not_saved);
                save_view_in_full_view_in_post.setBackground(not_saved_white);
            }
        }
    }

    private void set_up_the_back_ground_colors_of_the_ripple() {
        if (getView() != null) {
            Button button_showing_ripple_for_three_dot_in_full_view = getView().findViewById(R.id.button_showing_ripple_for_three_dot_in_full_view);
            Button button_showing_rippl_for_gift_in_view = getView().findViewById(R.id.button_showing_rippl_for_gift_in_view);
            Button button_showing_rippl_for_share_in_view = getView().findViewById(R.id.button_showing_rippl_for_share_in_view);
            Button button_showing_rippl_for_save_in_view = getView().findViewById(R.id.button_showing_rippl_for_save_in_view);
            Button back_button_in_view_information_good_habits = getView().findViewById(R.id.back_button_in_view_information_good_habits);
            Button button_to_sort_stuff_inside_post_for_comments = getView().findViewById(R.id.button_to_sort_stuff_inside_post_for_comments);
            int color = getResources().getColor(R.color.fav, null);
            {
                Drawable drawable_for_buttons_four = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons_four = DrawableCompat.wrap(drawable_for_buttons_four);
                DrawableCompat.setTint(drawable_for_buttons_four, color);
                button_showing_ripple_for_three_dot_in_full_view.setBackground(drawable_for_buttons_four);
            }
            {
                Drawable drawable_for_buttons_four = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons_four = DrawableCompat.wrap(drawable_for_buttons_four);
                DrawableCompat.setTint(drawable_for_buttons_four, color);
                button_showing_rippl_for_gift_in_view.setBackground(drawable_for_buttons_four);
            }
            {
                Drawable drawable_for_buttons_four = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons_four = DrawableCompat.wrap(drawable_for_buttons_four);
                DrawableCompat.setTint(drawable_for_buttons_four, color);
                button_showing_rippl_for_share_in_view.setBackground(drawable_for_buttons_four);
            }
            {
                Drawable drawable_for_buttons_four = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons_four = DrawableCompat.wrap(drawable_for_buttons_four);
                DrawableCompat.setTint(drawable_for_buttons_four, color);
                button_showing_rippl_for_save_in_view.setBackground(drawable_for_buttons_four);
            }
            {
                Drawable drawable_for_buttons_four = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
                drawable_for_buttons_four = DrawableCompat.wrap(drawable_for_buttons_four);
                DrawableCompat.setTint(drawable_for_buttons_four, color);
                back_button_in_view_information_good_habits.setBackground(drawable_for_buttons_four);
            }
            {
                Drawable drawable_for_buttons_four = ContextCompat.getDrawable(getContext(), R.drawable.ripple_for_all).mutate();
                drawable_for_buttons_four = DrawableCompat.wrap(drawable_for_buttons_four);
                DrawableCompat.setTint(drawable_for_buttons_four, Color.parseColor("#f1f3f5"));
                button_to_sort_stuff_inside_post_for_comments.setBackground(drawable_for_buttons_four);
            }
        }
    }

    private void three_dot_show_menu_lsiten() {
        if (getView() != null && getContext() != null) {
            final Button button_showing_ripple_for_three_dot_in_full_view = getView().findViewById(R.id.button_showing_ripple_for_three_dot_in_full_view);
            button_showing_ripple_for_three_dot_in_full_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(getContext(), button_showing_ripple_for_three_dot_in_full_view);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (was_this_reported) {
                                Toast toast = Toast.makeText(getActivity(), "Already reported", Toast.LENGTH_SHORT);
                                toast.show();
                            } else if (am_i_signed_in_with_google()) {
                                new AlertDialog.Builder(getContext())
                                        .setTitle("Report post?")
                                        .setMessage("Are you sure you want to report this post?")

                                        // Specifying a listener allows you to take an action before dismissing the dialog.
                                        // The dialog is automatically dismissed when a dialog button is clicked.
                                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                report_post();
                                            }
                                        })
                                        .setNegativeButton("cancel", null)
                                        .show();
                            } else {
                                new AlertDialog.Builder(getContext())
                                        .setTitle("Sign in with google")
                                        .setMessage("In order to prevent malicious reports sign in with google to be able to report")
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                Bottom_sheet_to_sing_in_to_write_posts bottom_sheet_to_sing_in_to_write_posts = new Bottom_sheet_to_sing_in_to_write_posts();
                                                bottom_sheet_to_sing_in_to_write_posts.setTargetFragment(Show_full_post.this, 254);
                                                bottom_sheet_to_sing_in_to_write_posts.show(getActivity().getSupportFragmentManager(), "tag");
                                            }
                                        }).setNegativeButton("cancel", null)
                                        .show();
                            }
                            return true;
                        }
                    });
                    popupMenu.inflate(R.menu.report_in_full_post);
                    popupMenu.show();
                }
            });
        }
    }

    private void report_post() {
        final DocumentReference sfDocRef = m_firebaseFirestore.collection("posts").document(m_document_id);
        m_firebaseFirestore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);

                // Note: this could be done without a transaction
                //       by updating the population using FieldValue.increment()
                ArrayList<String> array_lsit_with_upvote = (ArrayList<String>) snapshot.get("reports");
                array_lsit_with_upvote.add(FirebaseAuth.getInstance().getCurrentUser().getUid());
                long number_of_reports = snapshot.getLong("number_of_reports");
                number_of_reports++;
                transaction.update(sfDocRef, "reports", array_lsit_with_upvote, "number_of_reports", number_of_reports, "type", "main");

                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast toast = Toast.makeText(getContext(), "Thanks, your input was received and greatly appreciated!!", Toast.LENGTH_LONG);
                toast.show();
                was_this_reported = true;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast toast = Toast.makeText(getContext(), "Already reported", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void back_button_listen() {
        if (getView() != null && getActivity() != null) {
            Button back_button_in_view_information_good_habits = getView().findViewById(R.id.back_button_in_view_information_good_habits);
            back_button_in_view_information_good_habits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Posts_fragment old_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
                    if (old_fragment != null) {
                        old_fragment.update_the_adapter(position, m_title, m_body, m_category, m_time, (long) m_streak, m_span, m_image, m_user_id, m_comments, m_awards, is_this_upvote, is_this_down_vote, m_document_id, m_is_this_from_fire_base, m_upvotes, m_downvotes, post_saved, plus_or_minus, m_is_this_laoding, i_already_loaded, upvote_or_down_vote_was_clicked, m_firebaseFirestore, FirebaseAuth.getInstance().getCurrentUser(), was_this_reported, m_list_of_reports, am_i_loading_up_voote_down_vote_from_fire_base, did_i_see_this_tem, m_list_of_seen_posts, m_is_post_by_dev, which_post_called_me, name_of_the_user);
                        getActivity().getSupportFragmentManager().beginTransaction().remove(Show_full_post.this).show(old_fragment).commit();
                    }
                }
            });
        }
    }

    private void save_button_click_listen() {
        if (getView() != null) {
            Button button_showing_rippl_for_save_in_view = getView().findViewById(R.id.button_showing_rippl_for_save_in_view);
            Button button_to_watch_save_in_card_in_post = getView().findViewById(R.id.button_to_watch_save_in_card_in_post);
            button_showing_rippl_for_save_in_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (post_saved) {
                        remove_the_post(m_document_id);
                        set_up_the_save(false);
                        post_saved = false;
                    } else {
                        Am_i_paid am_i_paid = new Am_i_paid(getContext());
                        if (am_i_paid.did_user_pay()) {
                            save_the_post(m_document_id, m_title, m_body, m_span, m_time.getTime(), m_category, m_streak);
                            set_up_the_save(true);
                            post_saved = true;
                        } else {
                            Buy_premuim buy_premuim = new Buy_premuim("save posts", true, "show full post");
                            Show_full_post old_fragment = (Show_full_post) getActivity().getSupportFragmentManager().findFragmentByTag("show full post");
                            if (old_fragment != null) {
                                getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
                            }
                        }
                    }
                }
            });
            button_to_watch_save_in_card_in_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (post_saved) {
                        remove_the_post(m_document_id);
                        set_up_the_save(false);
                        post_saved = false;
                    } else {
                        Am_i_paid am_i_paid = new Am_i_paid(getContext());
                        if (am_i_paid.did_user_pay()) {
                            save_the_post(m_document_id, m_title, m_body, m_span, m_time.getTime(), m_category, m_streak);
                            set_up_the_save(true);
                            post_saved = true;
                        } else {
                            Buy_premuim buy_premuim = new Buy_premuim("save posts", true, "show full post");
                            Show_full_post old_fragment = (Show_full_post) getActivity().getSupportFragmentManager().findFragmentByTag("show full post");
                            if (old_fragment != null) {
                                getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
                            }
                        }
                    }
                }
            });
        }
    }

    private void save_the_post(String document_id, String title, String body, String span, long time, String category, long streak) {
        if (getContext() != null) {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("saved_posts", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            String old = sharedPreferences.getString("posts", "");
            title = title.replace("big_divide", "").replace("small_split", "");
            body = body.replace("big_divide", "").replace("small_split", "");
            String save_me;
            if (old != null && !old.isEmpty()) {
                save_me = old.concat(name_of_the_user).concat("small_split").concat(title).concat("small_split").concat(body).concat("small_split").concat(span).concat("small_split").concat(String.valueOf(time)).concat("small_split").concat(document_id).concat("big_divide");
            } else {
                save_me = name_of_the_user.concat("small_split").concat(title).concat("small_split").concat(body).concat("small_split").concat(span).concat("small_split").concat(String.valueOf(time)).concat("small_split").concat(document_id).concat("big_divide");
            }
            myEdit.putString("posts", save_me);
            myEdit.commit();
        }
    }

    private void remove_the_post(String document_id) {
        if (getContext() != null) {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("saved_posts", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            String old = sharedPreferences.getString("posts", "");
            if (old != null && !old.isEmpty()) {
                String[] big_split = old.split("big_divide");
                String save_me = "";
                for (int i = 0; i < big_split.length; i++) {
                    String[] small_split = big_split[i].split("small_split");
                    if (!small_split[5].equals(document_id)) {
                        save_me = save_me.concat(big_split[i]).concat("big_divide");
                    }
                }
                myEdit.putString("posts", save_me);
                myEdit.commit();
            }
        }
    }

    private void up_vote_button_click() {
        if (getView() != null) {
            Button button_to_watch_upvote_in_card_in_post = getView().findViewById(R.id.button_to_watch_upvote_in_card_in_post);
            final TextView text_showing_the_number_of_up_votes = getView().findViewById(R.id.text_showing_the_number_of_up_votes);
            button_to_watch_upvote_in_card_in_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (am_i_loading_up_voote_down_vote_from_fire_base) {
                        Toast toast = Toast.makeText(getContext(), "You are up voting and down voting too quickly!!", Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (am_i_signed_in_with_google()) {
                        upvote_or_down_vote_was_clicked = true;
                        int mode;
                        if (m_upvotes.contains(m_user_id)) {
                            mode = 1;
                        } else if (m_downvotes.contains(m_user_id)) {
                            mode = 2;
                        } else {
                            mode = 3;
                        }
                        Log.w("mode", String.valueOf(mode));
                        int likes_from_post = (m_upvotes.size() - m_downvotes.size()) + plus_or_minus;
                        if (is_this_upvote) {
                            color_the_up_vote_and_down_vote(0);
                            likes_from_post--;
                            remove_the_upvote(false);
                            if (mode == 1) {
                                plus_or_minus = -1;
                            } else if (mode == 2) {
                                plus_or_minus = 1;
                            } else {
                                plus_or_minus = 0;
                            }
                        } else if (is_this_down_vote) {
                            color_the_up_vote_and_down_vote(1);
                            likes_from_post = likes_from_post + 2;
                            am_i_loading_up_voote_down_vote_from_fire_base = true;
                            remove_the_down_vote(true);
                            if (mode == 1) {
                                plus_or_minus = 0;
                            } else if (mode == 2) {
                                plus_or_minus = 2;
                            } else {
                                plus_or_minus = 1;
                            }
                        } else {
                            color_the_up_vote_and_down_vote(1);
                            likes_from_post++;
                            put_up_vote();
                            if (mode == 1) {
                                plus_or_minus = 0;
                            } else if (mode == 2) {
                                plus_or_minus = 2;
                            } else {
                                plus_or_minus = 1;
                            }
                        }
                        if (likes_from_post > 1) {
                            text_showing_the_number_of_up_votes.setText(String.valueOf(likes_from_post));
                        } else {
                            text_showing_the_number_of_up_votes.setText("1");
                            Toast toast = Toast.makeText(getContext(), "Recorded but not shown publicly", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    } else {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Sign in with google")
                                .setMessage("In order to prevent malicious up votes sign in with google to be able to up vote")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Bottom_sheet_to_sing_in_to_write_posts bottom_sheet_to_sing_in_to_write_posts = new Bottom_sheet_to_sing_in_to_write_posts();
                                        bottom_sheet_to_sing_in_to_write_posts.setTargetFragment(Show_full_post.this, 254);
                                        bottom_sheet_to_sing_in_to_write_posts.show(getActivity().getSupportFragmentManager(), "tag");
                                    }
                                })
                                .setNegativeButton("cancel", null)
                                .show();
                    }
                }
            });
        }
    }

    private void down_vote_button_click() {
        if (getView() != null) {
            Button button_to_watch_downvote_in_card_in_post = getView().findViewById(R.id.button_to_watch_downvote_in_card_in_post);
            final TextView text_showing_the_number_of_up_votes = getView().findViewById(R.id.text_showing_the_number_of_up_votes);
            button_to_watch_downvote_in_card_in_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (am_i_loading_up_voote_down_vote_from_fire_base) {
                        Toast toast = Toast.makeText(getContext(), "You are up voting and down voting too quickly!!", Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (am_i_signed_in_with_google()) {
                        upvote_or_down_vote_was_clicked = true;
                        int mode;
                        if (m_upvotes.contains(m_user_id)) {
                            mode = 1;
                        } else if (m_downvotes.contains(m_user_id)) {
                            mode = 2;
                        } else {
                            mode = 3;
                        }
                        int likes_from_post = (m_upvotes.size() - m_downvotes.size()) + plus_or_minus;
                        if (is_this_upvote) {
                            color_the_up_vote_and_down_vote(2);
                            likes_from_post = likes_from_post - 2;
                            am_i_loading_up_voote_down_vote_from_fire_base = true;
                            remove_the_upvote(true);
                            if (mode == 1) {
                                plus_or_minus = -2;
                            } else if (mode == 2) {
                                plus_or_minus = 0;
                            } else {
                                plus_or_minus = -1;
                            }
                        } else if (is_this_down_vote) {
                            color_the_up_vote_and_down_vote(0);
                            likes_from_post++;
                            remove_the_down_vote(false);
                            if (mode == 1) {
                                plus_or_minus = -1;
                            } else if (mode == 2) {
                                plus_or_minus = 1;
                            } else {
                                plus_or_minus = 0;
                            }
                        } else {
                            color_the_up_vote_and_down_vote(2);
                            likes_from_post--;
                            put_dwon_vote();
                            if (mode == 1) {
                                plus_or_minus = -2;
                            } else if (mode == 2) {
                                plus_or_minus = 0;
                            } else {
                                plus_or_minus = -1;
                            }
                        }
                        if (likes_from_post > 1) {
                            text_showing_the_number_of_up_votes.setText(String.valueOf(likes_from_post));
                        } else {
                            text_showing_the_number_of_up_votes.setText("1");
                            Toast toast = Toast.makeText(getContext(), "Recorded but not shown publicly", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    } else {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Sign in with google")
                                .setMessage("In order to prevent malicious down votes sign in with google to be able to dwon vote")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Bottom_sheet_to_sing_in_to_write_posts bottom_sheet_to_sing_in_to_write_posts = new Bottom_sheet_to_sing_in_to_write_posts();
                                        bottom_sheet_to_sing_in_to_write_posts.setTargetFragment(Show_full_post.this, 254);
                                        bottom_sheet_to_sing_in_to_write_posts.show(getActivity().getSupportFragmentManager(), "tag");
                                    }
                                })
                                .setNegativeButton("cancel", null)
                                .show();
                    }
                }
            });
        }
    }

    private void color_the_up_vote_and_down_vote(int which) {
        if (getContext() != null && getView() != null) {
            TextView text_showing_the_number_of_up_votes = getView().findViewById(R.id.text_showing_the_number_of_up_votes);
            View upvote_view_in_post_card = getView().findViewById(R.id.upvote_view_in_post_card);
            View down_vote_view_in_post_card = getView().findViewById(R.id.down_vote_view_in_post_card);
            if (which == 0) {
                // no vote
                {
                    text_showing_the_number_of_up_votes.setTextColor(Color.parseColor("#808080"));
                    Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_arrow_drop_up_24).mutate();
                    Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                    DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#808080"));
                    upvote_view_in_post_card.setBackground(wrappedDrawable);
                }
                {
                    Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.arrow_down_down_vote_card).mutate();
                    Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                    DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#808080"));
                    down_vote_view_in_post_card.setBackground(wrappedDrawable);
                }
                is_this_upvote = false;
                is_this_down_vote = false;
            } else if (which == 1) {
                // up vote
                {
                    text_showing_the_number_of_up_votes.setTextColor(Color.parseColor("#FF4500"));
                    Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_arrow_drop_up_24).mutate();
                    Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                    DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#FF4500"));
                    upvote_view_in_post_card.setBackground(wrappedDrawable);
                }
                {
                    Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.arrow_down_down_vote_card).mutate();
                    Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                    DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#808080"));
                    down_vote_view_in_post_card.setBackground(wrappedDrawable);
                }
                is_this_upvote = true;
                is_this_down_vote = false;
            } else if (which == 2) {
                // down vote
                {
                    Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_arrow_drop_up_24).mutate();
                    Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                    DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#808080"));
                    upvote_view_in_post_card.setBackground(wrappedDrawable);
                }
                {
                    text_showing_the_number_of_up_votes.setTextColor(Color.parseColor("#9494FF"));
                    Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.arrow_down_down_vote_card).mutate();
                    Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                    DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#9494FF"));
                    down_vote_view_in_post_card.setBackground(wrappedDrawable);
                }
                is_this_upvote = false;
                is_this_down_vote = true;
            }
        }
    }

    private void put_up_vote() {
        final DocumentReference sfDocRef = m_firebaseFirestore.collection("posts").document(m_document_id);
        m_firebaseFirestore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);

                // Note: this could be done without a transaction
                //       by updating the population using FieldValue.increment()
                long newPopulation = snapshot.getLong("likes") + 1;
                ArrayList<String> array_lsit_with_upvote = (ArrayList<String>) snapshot.get("up_vote_list");
                array_lsit_with_upvote.add(FirebaseAuth.getInstance().getCurrentUser().getUid());
                transaction.update(sfDocRef, "likes", newPopulation, "up_vote_list", array_lsit_with_upvote, "type", "main");
                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                am_i_loading_up_voote_down_vote_from_fire_base = false;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (!am_i_loading_up_voote_down_vote_from_fire_base && getContext() != null) {
                    Toast toast = Toast.makeText(getContext(), "Cant upvote please try again later", Toast.LENGTH_SHORT);
                    toast.show();
                }
                am_i_loading_up_voote_down_vote_from_fire_base = false;
            }
        });
    }

    private void remove_the_upvote(final boolean run_after_this) {
        final DocumentReference sfDocRef = m_firebaseFirestore.collection("posts").document(m_document_id);
        m_firebaseFirestore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);

                // Note: this could be done without a transaction
                //       by updating the population using FieldValue.increment()
                long newPopulation = snapshot.getLong("likes") - 1;
                ArrayList<String> array_lsit_with_upvote = (ArrayList<String>) snapshot.get("up_vote_list");
                array_lsit_with_upvote.remove(FirebaseAuth.getInstance().getCurrentUser().getUid());
                transaction.update(sfDocRef, "likes", newPopulation, "up_vote_list", array_lsit_with_upvote, "type", "main");

                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                if (run_after_this) {
                    put_dwon_vote();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (!am_i_loading_up_voote_down_vote_from_fire_base && getContext() != null) {
                    Toast toast = Toast.makeText(getContext(), "Cant remove up vote please try again later", Toast.LENGTH_SHORT);
                    toast.show();
                }
                am_i_loading_up_voote_down_vote_from_fire_base = false;
            }
        });
    }

    private void put_dwon_vote() {
        final DocumentReference sfDocRef = m_firebaseFirestore.collection("posts").document(m_document_id);
        m_firebaseFirestore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);

                // Note: this could be done without a transaction
                //       by updating the population using FieldValue.increment()
                long newPopulation = snapshot.getLong("likes") - 1;
                ArrayList<String> array_lsit_with_upvote = (ArrayList<String>) snapshot.get("down_vote_list");
                array_lsit_with_upvote.add(FirebaseAuth.getInstance().getCurrentUser().getUid());
                transaction.update(sfDocRef, "likes", newPopulation, "down_vote_list", array_lsit_with_upvote, "type", "main");
                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                am_i_loading_up_voote_down_vote_from_fire_base = false;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (!am_i_loading_up_voote_down_vote_from_fire_base && getContext() != null) {
                    Toast toast = Toast.makeText(getContext(), "Cant down vote please try again later", Toast.LENGTH_SHORT);
                    toast.show();
                }
                am_i_loading_up_voote_down_vote_from_fire_base = false;
            }
        });
    }

    private void remove_the_down_vote(final boolean start_after_this) {
        final DocumentReference sfDocRef = m_firebaseFirestore.collection("posts").document(m_document_id);
        m_firebaseFirestore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);

                // Note: this could be done without a transaction
                //       by updating the population using FieldValue.increment()
                long newPopulation = snapshot.getLong("likes") + 1;
                ArrayList<String> array_lsit_with_upvote = (ArrayList<String>) snapshot.get("down_vote_list");
                array_lsit_with_upvote.remove(FirebaseAuth.getInstance().getCurrentUser().getUid());
                transaction.update(sfDocRef, "likes", newPopulation, "down_vote_list", array_lsit_with_upvote, "type", "main");

                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                if (start_after_this) {
                    put_up_vote();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (!am_i_loading_up_voote_down_vote_from_fire_base && getContext() != null) {
                    Toast toast = Toast.makeText(getContext(), "Cant remove down vote please try again later", Toast.LENGTH_SHORT);
                    toast.show();
                }
                am_i_loading_up_voote_down_vote_from_fire_base = false;
            }
        });
    }

    public void back_was_pressed() {
        if (getActivity() != null) {
            Posts_fragment old_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
            if (old_fragment != null) {
                old_fragment.update_the_adapter(position, m_title, m_body, m_category, m_time, (long) m_streak, m_span, m_image, m_user_id, m_comments, m_awards, is_this_upvote, is_this_down_vote, m_document_id, m_is_this_from_fire_base, m_upvotes, m_downvotes, post_saved, plus_or_minus, m_is_this_laoding, i_already_loaded, upvote_or_down_vote_was_clicked, m_firebaseFirestore, FirebaseAuth.getInstance().getCurrentUser(), was_this_reported, m_list_of_reports, am_i_loading_up_voote_down_vote_from_fire_base, did_i_see_this_tem, m_list_of_seen_posts, m_is_post_by_dev, which_post_called_me, name_of_the_user);
                getActivity().getSupportFragmentManager().beginTransaction().remove(Show_full_post.this).show(old_fragment).commit();
            }
        }
    }

    public void update_teh_adapter_remote() {
        if (getActivity() != null) {
            Posts_fragment old_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
            if (old_fragment != null) {
                old_fragment.update_the_adapter(position, m_title, m_body, m_category, m_time, (long) m_streak, m_span, m_image, m_user_id, m_comments, m_awards, is_this_upvote, is_this_down_vote, m_document_id, m_is_this_from_fire_base, m_upvotes, m_downvotes, post_saved, plus_or_minus, m_is_this_laoding, i_already_loaded, upvote_or_down_vote_was_clicked, m_firebaseFirestore, FirebaseAuth.getInstance().getCurrentUser(), was_this_reported, m_list_of_reports, am_i_loading_up_voote_down_vote_from_fire_base, did_i_see_this_tem, m_list_of_seen_posts, m_is_post_by_dev, which_post_called_me, name_of_the_user);
            }
        }
    }

    private void sort_button_listen() {
        if (getView() != null) {
            Button button_to_sort_stuff_inside_post_for_comments = getView().findViewById(R.id.button_to_sort_stuff_inside_post_for_comments);
            button_to_sort_stuff_inside_post_for_comments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bottom_sheet_choose_sort_method bottom_sheet_choose_sort_method = new Bottom_sheet_choose_sort_method();
                    bottom_sheet_choose_sort_method.setTargetFragment(Show_full_post.this, 9875);
                    bottom_sheet_choose_sort_method.show(getActivity().getSupportFragmentManager(), "comment");
                }
            });
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9875 && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                sort_type = bundle.getString("sort", "hot");
                if (previous_sort_type.equals(sort_type)) {
                    Toast.makeText(getActivity(), "Already selected", Toast.LENGTH_SHORT).show();
                } else {
                    Button button_to_sort_stuff_inside_post_for_comments = getView().findViewById(R.id.button_to_sort_stuff_inside_post_for_comments);
                    if (sort_type.equals("hot")) {
                        button_to_sort_stuff_inside_post_for_comments.setText("HOT");
                        button_to_sort_stuff_inside_post_for_comments.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_local_fire_department_24, 0, R.drawable.round_arrow_drop_down_24, 0);
                    } else if (sort_type.equals("new")) {
                        button_to_sort_stuff_inside_post_for_comments.setText("NEW");
                        button_to_sort_stuff_inside_post_for_comments.setCompoundDrawablesWithIntrinsicBounds(R.drawable.round_trending_up_24, 0, R.drawable.round_arrow_drop_down_24, 0);
                    } else if (sort_type.equals("top")) {
                        button_to_sort_stuff_inside_post_for_comments.setText("TOP");
                        button_to_sort_stuff_inside_post_for_comments.setCompoundDrawablesWithIntrinsicBounds(R.drawable.round_emoji_events_24, 0, R.drawable.round_arrow_drop_down_24, 0);
                    }
                    previous_sort_type = sort_type;
                }
            }
        } else if (requestCode == 254 && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                String text = bundle.getString("sign_in", "anonymous");
                if (text.equals("google")) {
                    if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                        m_user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        if (m_upvotes.contains(m_user_id)) {
                            is_this_upvote = true;
                        } else {
                            is_this_upvote = false;
                        }
                        if (m_downvotes.contains(m_user_id)) {
                            is_this_down_vote = true;
                        } else {
                            is_this_down_vote = false;
                        }
                        set_up_likes_and_comments((m_upvotes.size() - m_downvotes.size()) + plus_or_minus, m_comments.size(), is_this_upvote, is_this_down_vote);
                        example_list.clear();
                        set_up_the_comments();
                    }
                }
            }
        }
    }

    private void add_comment_button_listen() {
        if (getView() != null) {
            Button button_to_add_comment = getView().findViewById(R.id.button_to_add_comment);
            button_to_add_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (am_i_signed_in_with_google()) {
                        Write_comment_for_post write_comment_for_post = new Write_comment_for_post();
                        Bundle bundle = new Bundle();
                        bundle.putString("title", m_title);
                        bundle.putString("body", m_body);
                        bundle.putString("id", m_document_id);
                        bundle.putInt("position", -1);
                        write_comment_for_post.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, write_comment_for_post, "write a comment").hide(Show_full_post.this).commit();
                    } else {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Sign in with google")
                                .setMessage("In order to prevent malicious comments sign in with google to be able to comment")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Bottom_sheet_to_sing_in_to_write_posts bottom_sheet_to_sing_in_to_write_posts = new Bottom_sheet_to_sing_in_to_write_posts();
                                        bottom_sheet_to_sing_in_to_write_posts.setTargetFragment(Show_full_post.this, 254);
                                        bottom_sheet_to_sing_in_to_write_posts.show(getActivity().getSupportFragmentManager(), "tag");
                                    }
                                })
                                .setNegativeButton("cancel", null)
                                .show();
                    }
                }
            });
        }
    }

    private void set_up_the_comments() {
        if (getView() != null) {
            TextView text_saying_the_the_comments_are_empty = getView().findViewById(R.id.text_saying_the_the_comments_are_empty);
            RecyclerView recycle_view_to_show_the_comments = getView().findViewById(R.id.recycle_view_to_show_the_comments);
            if (remove_reports_list.isEmpty()) {
                text_saying_the_the_comments_are_empty.setVisibility(View.VISIBLE);
                recycle_view_to_show_the_comments.setVisibility(View.GONE);
            } else {
                text_saying_the_the_comments_are_empty.setVisibility(View.GONE);
                recycle_view_to_show_the_comments.setVisibility(View.VISIBLE);
                Collections.sort(remove_reports_list, new Comment_custom_sort());
                for (int i = 0; i < remove_reports_list.size(); i++) {
                    if (position == -1 && i == return_position_of_comment() || position == -2 && i == return_position_of_comment()) {
                        continue;
                    }
                    HashMap<String, Object> map = remove_reports_list.get(i);
                    String body = (String) map.get("body");
                    ArrayList<Long> awards = (ArrayList<Long>) map.get("awards");
                    boolean dev = (boolean) map.get("dev");
                    ArrayList<String> up_vote_list = (ArrayList<String>) map.get("up_vote_list");
                    ArrayList<String> down_vote_list = (ArrayList<String>) map.get("down_vote_list");
                    ArrayList<String> report_list = (ArrayList<String>) map.get("reports");
                    //long streak = (long) map.get("streak");
                    String user_id = (String) map.get("user_id");
                    ArrayList<HashMap<String, Object>> replies = (ArrayList<HashMap<String, Object>>) map.get("replies");
                    // firebase firestore
                    //document id
                    //position
                    // map with time
                    //String category = (String) map.get("category");
                    Timestamp date = (Timestamp) map.get("date");
                    //fire base user
                    int position_of_real = (int) map.get("position_of_real");
                    String name = (String) map.get("name");
                    example_list.add(new Example_item_comments_feed(body, awards, dev, up_vote_list, down_vote_list, report_list, -1, user_id, replies, m_firebaseFirestore, position_of_real, m_document_id, date.toDate(), "", FirebaseAuth.getInstance().getCurrentUser(), name, m_title, m_body, m_span, m_time, m_category, m_streak, name_of_the_user));
                    adapter.notifyDataSetChanged();
                }
            }
                /*for (int i = 0; i < m_comments.size(); i++) {
                    Map<String, Object> map = m_comments.get(i);
                    String body = (String) map.get("body");
                    ArrayList<Long> awards = (ArrayList<Long>) map.get("awards");
                    boolean dev = (boolean) map.get("dev");
                    ArrayList<String> up_vote_list = (ArrayList<String>) map.get("up_vote_list");
                    ArrayList<String> down_vote_list = (ArrayList<String>) map.get("down_vote_list");
                    ArrayList<String> report_list = (ArrayList<String>) map.get("reports");
                    long streak = (long) map.get("streak");
                    String user_id = (String) map.get("user_id");
                    ArrayList<HashMap<String, Object>> replies = (ArrayList<HashMap<String, Object>>) map.get("replies");
                    // firebase firestore
                    //document id
                    //position
                    // map with time
                    String category = (String) map.get("category");
                    //fire base user
                    example_list.add(new Example_item_comments_feed(body, awards, dev, up_vote_list, down_vote_list, report_list, (int) streak, user_id, replies, m_firebaseFirestore, i, m_document_id, map_with_time_for_comments, category, FirebaseAuth.getInstance().getCurrentUser()));
                    adapter.notifyDataSetChanged();
                }*/
        }
    }


    private void set_up_comment_recycle_view() {
        if (getView() != null) {
            recyclerView = getView().findViewById(R.id.recycle_view_to_show_the_comments);
            linearLayoutManager = new LinearLayoutManager(getActivity());
            adapter = new Adapter_for_comment_post(example_list);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setHasFixedSize(false);
            adapter.set_share_clicked_listen(new Adapter_for_comment_post.share_button_was_clicked() {
                @Override
                public void share_just_got_clciked(String body) {
                    share_screen_shot("", first_letter_capital(body), true);
                }
            });
            adapter.set_reply_clicked_listen(new Adapter_for_comment_post.reply_button_was_clicked() {
                @Override
                public void reply_was_clicked_in_commenmt(int position, String body, int recycle_view_position) {
                    Write_comment_for_post write_comment_for_post = new Write_comment_for_post();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", body);
                    bundle.putString("body", "");
                    bundle.putString("id", m_document_id);
                    bundle.putInt("position", position);
                    bundle.putInt("recycle_view_position", recycle_view_position);
                    write_comment_for_post.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, write_comment_for_post, "write a comment").hide(Show_full_post.this).commit();
                }
            });
            adapter.reply_nested_function(new Adapter_for_comment_post.reply_nested_button_nested_interface() {
                @Override
                public void reply_button_clicked_nested_one(String name, String body, String user_id, int position, int recycle_view_position) {
                    Write_comment_for_post write_comment_for_post = new Write_comment_for_post();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", body);
                    bundle.putString("body", "");
                    bundle.putString("id", m_document_id);
                    bundle.putInt("position", position);
                    bundle.putInt("recycle_view_position", recycle_view_position);
                    bundle.putString("user_id", user_id);
                    bundle.putString("name_of_the_reply", name);
                    write_comment_for_post.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, write_comment_for_post, "write a comment").hide(Show_full_post.this).commit();
                }
            });
            adapter.set_up_vote_down_vote_clicked_listen(new Adapter_for_comment_post.up_vote_listener() {
                @Override
                public void up_vote_down_vote_was_clciked(String body) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Sign in with google")
                            .setMessage("In order to prevent malicious ".concat(body).concat("s sign in with google to be able to ".concat(body)))
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Bottom_sheet_to_sing_in_to_write_posts bottom_sheet_to_sing_in_to_write_posts = new Bottom_sheet_to_sing_in_to_write_posts();
                                    bottom_sheet_to_sing_in_to_write_posts.setTargetFragment(Show_full_post.this, 254);
                                    bottom_sheet_to_sing_in_to_write_posts.show(getActivity().getSupportFragmentManager(), "tag");
                                }
                            })
                            .setNegativeButton("cancel", null)
                            .show();
                }
            });
            adapter.set_up_gift_listen(new Adapter_for_comment_post.gift_button_listen() {
                @Override
                public void gift_was_clicked(String user_id, String document_id, int comment_position, int reply_position, ArrayList<Long> awards) {
                    if (comment_position == -100 && reply_position == -100) {
                        Bottom_sheet_to_give_coins bottom_sheet_to_give_coins = new Bottom_sheet_to_give_coins(position, real_user_id, m_document_id, awards, "show");
                        bottom_sheet_to_give_coins.set_update_gift(new Bottom_sheet_to_give_coins.update_gift_listen() {
                            @Override
                            public void gift_was_clicked(int index, ArrayList<Long> arrayList_of_awards) {
                                m_awards = arrayList_of_awards;
                                set_up_the_awards(m_awards.get(0).intValue(), m_awards.get(1).intValue(), m_awards.get(2).intValue());
                            }
                        });
                        bottom_sheet_to_give_coins.show(getParentFragmentManager(), "");
                    } else if (reply_position == -100) {
                        Bottom_sheet_to_give_coins bottom_sheet_to_give_coins = new Bottom_sheet_to_give_coins(position_of_comment, real_user_id, m_document_id, comment_position, awards, "show");
                        bottom_sheet_to_give_coins.set_update_gift(new Bottom_sheet_to_give_coins.update_gift_listen() {
                            @Override
                            public void gift_was_clicked(int index, ArrayList<Long> arrayList_of_awards) {
                                example_list.set(index, new Example_item_comments_feed(example_list.get(index).getBody(), arrayList_of_awards, example_list.get(index).isDev(), example_list.get(index).getUp_vote_list(), example_list.get(index).getDown_vote_list(), example_list.get(index).getReport_list(), (int) example_list.get(index).getStreak(), user_id, example_list.get(index).getReplies(), m_firebaseFirestore, example_list.get(index).getPosition(), m_document_id, example_list.get(index).getTime(), example_list.get(index).getCategory(), FirebaseAuth.getInstance().getCurrentUser(), example_list.get(index).getName(), m_title, m_body, m_span, m_time, m_category, m_streak, name_of_the_user));
                                adapter.notifyDataSetChanged();
                            }
                        });
                        bottom_sheet_to_give_coins.show(getParentFragmentManager(), "");
                    } else {
                        Bottom_sheet_to_give_coins bottom_sheet_to_give_coins = new Bottom_sheet_to_give_coins(position_of_reply, real_user_id, m_document_id, comment_position, reply_position, awards, "show");
                        bottom_sheet_to_give_coins.set_update_gift(new Bottom_sheet_to_give_coins.update_gift_listen() {
                            @Override
                            public void gift_was_clicked(int index, ArrayList<Long> arrayList_of_awards) {
                                example_list.set(index, new Example_item_comments_feed(example_list.get(index).getBody(), arrayList_of_awards, example_list.get(index).isDev(), example_list.get(index).getUp_vote_list(), example_list.get(index).getDown_vote_list(), example_list.get(index).getReport_list(), (int) example_list.get(index).getStreak(), user_id, example_list.get(index).getReplies(), m_firebaseFirestore, example_list.get(index).getPosition(), m_document_id, example_list.get(index).getTime(), example_list.get(index).getCategory(), FirebaseAuth.getInstance().getCurrentUser(), example_list.get(index).getName(), m_title, m_body, m_span, m_time, m_category, m_streak, name_of_the_user));
                                adapter.notifyDataSetChanged();
                            }
                        });
                        bottom_sheet_to_give_coins.show(getParentFragmentManager(), "");
                    }
                }
            });
            adapter.set_up_save_only_for_pro(new Adapter_for_comment_post.save_is_only_for_pro() {
                @Override
                public void save_is_for_pro(String mode) {
                    if (mode.equals("comment")) {
                        Buy_premuim buy_premuim = new Buy_premuim("save comments", true, "show full post");
                        Show_full_post old_fragment = (Show_full_post) getActivity().getSupportFragmentManager().findFragmentByTag("show full post");
                        if (old_fragment != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
                        }
                    } else {
                        Buy_premuim buy_premuim = new Buy_premuim("save replies", true, "show full post");
                        Show_full_post old_fragment = (Show_full_post) getActivity().getSupportFragmentManager().findFragmentByTag("show full post");
                        if (old_fragment != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
                        }
                    }
                }
            });
        }
    }

    private String first_letter_capital(String sentence) {
        return sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
    }

    private void share_button_listen() {
        if (getView() != null) {
            Button button_showing_rippl_for_share_in_view = getView().findViewById(R.id.button_showing_rippl_for_share_in_view);
            button_showing_rippl_for_share_in_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    share_screen_shot(first_letter_capital(m_title), first_letter_capital(m_body), false);
                }
            });
        }
    }

    private void share_screen_shot(String title, String body, boolean comment) {
        if (getView() != null && getContext() != null) {
            final TextView text_to_put_in_share = getView().findViewById(R.id.text_to_put_in_share);
            final TextView text_body_to_put_in_share = getView().findViewById(R.id.text_body_to_put_in_share);
            final View thin_line_in_share = getView().findViewById(R.id.thin_line_in_share);
            final ScrollView scroll_view_to_take_screen_show_in_good_habtis = getView().findViewById(R.id.scroll_view_to_take_screen_show_in_good_habtis);
            final View view_under_the_body_to_extend = getView().findViewById(R.id.view_under_the_body_to_extend);
            final View view_with_white_back_ground_in_share_screen = getView().findViewById(R.id.view_with_white_back_ground_in_share_screen);
            scroll_view_to_take_screen_show_in_good_habtis.setVisibility(View.VISIBLE);
            if (comment) {
                text_to_put_in_share.setVisibility(View.GONE);
                thin_line_in_share.setVisibility(View.GONE);
                text_body_to_put_in_share.setText(body);
                ConstraintLayout constraintLayout = getView().findViewById(R.id.layou_to_show_screen_shot_in_good_habits);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(text_body_to_put_in_share.getId(), ConstraintSet.TOP, view_with_white_back_ground_in_share_screen.getId(), ConstraintSet.TOP, (int) convertDpToPixel(15, getContext()));
                constraintSet.applyTo(constraintLayout);
            } else {
                text_to_put_in_share.setVisibility(View.VISIBLE);
                thin_line_in_share.setVisibility(View.VISIBLE);
                text_to_put_in_share.setText(title);
                text_body_to_put_in_share.setText(body);
                ConstraintLayout constraintLayout = getView().findViewById(R.id.layou_to_show_screen_shot_in_good_habits);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(text_body_to_put_in_share.getId(), ConstraintSet.TOP, thin_line_in_share.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(15, getContext()));
                constraintSet.applyTo(constraintLayout);
            }
            scroll_view_to_take_screen_show_in_good_habtis.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    scroll_view_to_take_screen_show_in_good_habtis.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    share_the_bitmap(getBitmapFromView(scroll_view_to_take_screen_show_in_good_habtis, scroll_view_to_take_screen_show_in_good_habtis.getChildAt(0).getHeight(), scroll_view_to_take_screen_show_in_good_habtis.getChildAt(0).getWidth()));
                    text_to_put_in_share.setText("");
                    text_body_to_put_in_share.setText("");
                    scroll_view_to_take_screen_show_in_good_habtis.setVisibility(View.GONE);
                }
            });
        }
    }

    private void share_the_bitmap(Bitmap bitmap) {
        if (getView() != null && getContext() != null) {
            // save bitmap to cache directory
            try {
                File cachePath = new File(getContext().getCacheDir(), "images");
                cachePath.mkdirs(); // don't forget to make the directory
                FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                stream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            File imagePath = new File(getContext().getCacheDir(), "images");
            File newFile = new File(imagePath, "image.png");
            Uri contentUri = FileProvider.getUriForFile(getContext(), "com.easyhabitsapp.android.fileprovider", newFile);

            if (contentUri != null) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
                shareIntent.setDataAndType(contentUri, getActivity().getContentResolver().getType(contentUri));
                shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
                startActivity(Intent.createChooser(shareIntent, "Share"));
            }
        }
    }

    private Bitmap getBitmapFromView(View view, int height, int width) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;
    }

    private void set_the_text_at_the_top() {
        if (getView() != null) {
            TextView text_putting_the_title_of_post_in_bar_in_post_full_detail = getView().findViewById(R.id.text_putting_the_title_of_post_in_bar_in_post_full_detail);
            text_putting_the_title_of_post_in_bar_in_post_full_detail.setText(first_letter_capital(m_title));
        }
    }

    private void set_the_bar_color_under_title() {
        if (getView() != null) {
            View view_under_top_action_bar_half_color = getView().findViewById(R.id.view_under_top_action_bar_half_color);
            view_under_top_action_bar_half_color.setBackgroundColor(ColorUtils.blendARGB(getResources().getColor(R.color.fav, null), Color.WHITE, 0.5F));
        }
    }

    private void sort_stuff_correctly() {
        remove_reports_list = new ArrayList<>();
        if (!m_comments.isEmpty()) {
            for (int i = 0; i < m_comments.size(); i++) {
                Map<String, Object> map = m_comments.get(i);
                ArrayList<String> report_list = (ArrayList<String>) map.get("reports");
                if (report_list != null && report_list.size() <= 5) {
                    map.put("position_of_real", i);
                    remove_reports_list.add(m_comments.get(i));
                }
            }
        }
    }

    /*private double return_comment_score(int up, int down) {
        if ((up + down) == 0) {
            return 0;
        } else {
            int number_of_upvotes_and_down_votes = up + down;
            double z = 1.281551565545f;
            double p = (double) (up) / number_of_upvotes_and_down_votes;

            double left = p + 1d / (2 * number_of_upvotes_and_down_votes) * z * z;
            double right = z * sqrt(p * (1 - p) / number_of_upvotes_and_down_votes + z * z / (4 * number_of_upvotes_and_down_votes * number_of_upvotes_and_down_votes));
            double under = 1 + 1d / number_of_upvotes_and_down_votes * z * z;

            return (left - right) / under;
        }
    }*/
    public void add_new_comment(String body, ArrayList<Long> awards, boolean dev, ArrayList<
            String> up_vote_list, ArrayList<String> down_vote_list, ArrayList<String> report_list,
                                long streak, String user_id, ArrayList<HashMap<String, Object>> replies,
                                int position, Timestamp date, String
                                        category, ArrayList<HashMap<String, Object>> comments, String name_of_the_user) {
        if (getView() != null) {
            put_the_comment_in_your_comments(body, position, name_of_the_user, date.toDate().getTime());
            TextView text_telling_the_number_of_comments_in_card_in_post = getView().findViewById(R.id.text_telling_the_number_of_comments_in_card_in_post);
            TextView text_saying_the_the_comments_are_empty = getView().findViewById(R.id.text_saying_the_the_comments_are_empty);
            RecyclerView recycle_view_to_show_the_comments = getView().findViewById(R.id.recycle_view_to_show_the_comments);
            example_list.add(0, new Example_item_comments_feed(body, awards, dev, up_vote_list, down_vote_list, report_list, -1, user_id, replies, m_firebaseFirestore, position, m_document_id, date.toDate(), ""/*was category*/, FirebaseAuth.getInstance().getCurrentUser(), name_of_the_user, m_title, m_body, m_span, m_time, m_category, m_streak, this.name_of_the_user));
            adapter.notifyDataSetChanged();
            m_comments = comments;
            int number_of_comments = Integer.parseInt(text_telling_the_number_of_comments_in_card_in_post.getText().toString()) + 1;
            text_telling_the_number_of_comments_in_card_in_post.setText(String.valueOf(number_of_comments));
            text_saying_the_the_comments_are_empty.setVisibility(View.GONE);
            recycle_view_to_show_the_comments.setVisibility(View.VISIBLE);
        }
    }

    public void reply_to_the_comment(ArrayList<HashMap<String, Object>> replies,
                                     int position_of_recycle_view, int position_of_comment, int position_of_reply) {
        if (getView() != null) {
            put_the_reply_in_your_replies(example_list.get(position_of_recycle_view).getBody(), position_of_comment, example_list.get(position_of_recycle_view).getName(), example_list.get(position_of_recycle_view).getTime().getTime(), example_list.get(position_of_recycle_view).getCategory(), example_list.get(position_of_recycle_view).getStreak(), replies.get(position_of_reply), position_of_reply);
            example_list.set(position_of_recycle_view, new Example_item_comments_feed(example_list.get(position_of_recycle_view).getBody(), example_list.get(position_of_recycle_view).getAwards(), example_list.get(position_of_recycle_view).isDev(), example_list.get(position_of_recycle_view).getUp_vote_list(), example_list.get(position_of_recycle_view).getDown_vote_list(), example_list.get(position_of_recycle_view).getReport_list(), example_list.get(position_of_recycle_view).getStreak(), example_list.get(position_of_recycle_view).getUser_id(), replies, m_firebaseFirestore, position_of_comment, m_document_id, example_list.get(position_of_recycle_view).getTime(), example_list.get(position_of_recycle_view).getCategory(), FirebaseAuth.getInstance().getCurrentUser(), example_list.get(position_of_recycle_view).getName(), example_list.get(position_of_recycle_view).getTitle_of_the_main_post(), example_list.get(position_of_recycle_view).getBody_of_the_main_post(), example_list.get(position_of_recycle_view).getSpan_of_the_main_post(), example_list.get(position_of_recycle_view).getTime_of_the_main_post(), example_list.get(position_of_recycle_view).getCategory_of_the_main_post(), example_list.get(position_of_recycle_view).getStreak_of_the_main_post(), example_list.get(position_of_recycle_view).getName_of_the_main_post()));
            adapter.notifyDataSetChanged();
        }
    }

    private void set_up_the_name(String name) {
        if (getView() != null) {
            TextView text_saying_the_name_of_the_user_in_show_full_post = getView().findViewById(R.id.text_saying_the_name_of_the_user_in_show_full_post);
            text_saying_the_name_of_the_user_in_show_full_post.setText(name);
        }
    }

    private void put_the_comment_in_your_comments(String body_of_the_comment,
                                                  int position_of_the_comment, String name_of_the_comment, long time_of_the_comment) {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("your_comments", MODE_PRIVATE);
            String old = sharedPreferences.getString("your_comments", "");
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            String name_of_the_user = this.name_of_the_user.replace("small_split", "").replace("big_divide", "");
            String m_title = this.m_title.replace("small_split", "").replace("big_divide", "");
            String m_body = this.m_body.replace("small_split", "").replace("big_divide", "");
            body_of_the_comment = body_of_the_comment.replace("small_split", "").replace("big_divide", "");
            name_of_the_comment = name_of_the_comment.replace("small_split", "").replace("big_divide", "");
            String save_me = m_document_id.concat("small_split").concat(name_of_the_user).concat("small_split").concat(m_title).concat("small_split").concat(m_body).concat("small_split").concat(m_span).concat("small_split").concat(String.valueOf(m_time.getTime())).concat("small_split").concat(body_of_the_comment).concat("small_split").concat(String.valueOf(position_of_the_comment)).concat("small_split").concat(name_of_the_comment).concat("small_split").concat(String.valueOf(time_of_the_comment)).concat("big_divide");
            myEdit.putString("your_comments", old.concat(save_me));
            myEdit.commit();
        }
    }

    private void put_the_reply_in_your_replies(String body_of_the_comment,
                                               int position_of_the_comment, String name_of_the_comment, long time_of_the_comment, String
                                                       category, long streak_of_the_comment, HashMap<String, Object> map, int position_of_the_reply) {
        if (getActivity() != null) {
            String body_of_the_reply = (String) map.get("body");
            String name_of_the_reply = (String) map.get("name");
            long time_of_the_reply = ((Timestamp) map.get("date")).toDate().getTime();
            //String category_reply = (String) map.get("category");
            //long streak_of_the_reply = (long) map.get("streak");

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("your_comments", MODE_PRIVATE);
            String old = sharedPreferences.getString("your_comments", "");
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            String name_of_the_user = this.name_of_the_user.replace("small_split", "").replace("big_divide", "");
            String m_title = this.m_title.replace("small_split", "").replace("big_divide", "");
            String m_body = this.m_body.replace("small_split", "").replace("big_divide", "");
            body_of_the_comment = body_of_the_comment.replace("small_split", "").replace("big_divide", "");
            name_of_the_comment = name_of_the_comment.replace("small_split", "").replace("big_divide", "");
            body_of_the_reply = body_of_the_reply.replace("small_split", "").replace("big_divide", "");
            name_of_the_reply = name_of_the_reply.replace("small_split", "").replace("big_divide", "");
            String save_me = m_document_id.concat("small_split").concat(name_of_the_user).concat("small_split").concat(m_title).concat("small_split").concat(m_body).concat("small_split").concat(m_span).concat("small_split").concat(String.valueOf(m_time.getTime())).concat("small_split").concat(body_of_the_comment).concat("small_split").concat(String.valueOf(position_of_the_comment)).concat("small_split").concat(name_of_the_comment).concat("small_split").concat(String.valueOf(time_of_the_comment)).concat("small_split").concat(body_of_the_reply).concat("small_split").concat(String.valueOf(position_of_the_reply)).concat("small_split").concat(name_of_the_reply).concat("small_split").concat(String.valueOf(time_of_the_reply)).concat("big_divide");
            myEdit.putString("your_comments", old.concat(save_me));
            myEdit.commit();
        }
    }

    private boolean am_i_signed_in_with_google() {
        /*if (FirebaseAuth.getInstance().getCurrentUser() != null && FirebaseAuth.getInstance().getCurrentUser().getIdToken(false).getResult().getSignInProvider().equals("google.com")) {
            return true;
        } else {
            return false;
        }*/
        return true;
    }

    private int return_position_of_comment() {
        for (int i = 0; i < remove_reports_list.size(); i++) {
            HashMap<String, Object> map = remove_reports_list.get(i);
            int position_of_real = (int) map.get("position_of_real");
            if (position_of_real == position_of_comment) {
                return i;
            }
        }
        return 0;
    }

    private void add_comment_at_start() {
        HashMap<String, Object> map = remove_reports_list.get(return_position_of_comment());
        String body = (String) map.get("body");
        ArrayList<Long> awards = (ArrayList<Long>) map.get("awards");
        boolean dev = (boolean) map.get("dev");
        ArrayList<String> up_vote_list = (ArrayList<String>) map.get("up_vote_list");
        ArrayList<String> down_vote_list = (ArrayList<String>) map.get("down_vote_list");
        ArrayList<String> report_list = (ArrayList<String>) map.get("reports");
//        long streak = (long) map.get("streak");
        String user_id = (String) map.get("user_id");
        ArrayList<HashMap<String, Object>> replies = (ArrayList<HashMap<String, Object>>) map.get("replies");
        // firebase firestore
        //document id
        //position
        // map with time
//        String category = (String) map.get("category");
        Timestamp date = (Timestamp) map.get("date");
        //fire base user
        int position_of_real = (int) map.get("position_of_real");
        String name = (String) map.get("name");
        example_list.add(0, new Example_item_comments_feed(body, awards, dev, up_vote_list, down_vote_list, report_list, -1, user_id, replies, m_firebaseFirestore, position_of_real, m_document_id, date.toDate(), "", FirebaseAuth.getInstance().getCurrentUser(), name, m_title, m_body, m_span, m_time, m_category, m_streak, name_of_the_user));
        adapter.notifyDataSetChanged();
    }

    private void add_reply_at_start() {
        HashMap<String, Object> map = remove_reports_list.get(return_position_of_comment());
        String body = (String) map.get("body");
        ArrayList<Long> awards = (ArrayList<Long>) map.get("awards");
        boolean dev = (boolean) map.get("dev");
        ArrayList<String> up_vote_list = (ArrayList<String>) map.get("up_vote_list");
        ArrayList<String> down_vote_list = (ArrayList<String>) map.get("down_vote_list");
        ArrayList<String> report_list = (ArrayList<String>) map.get("reports");
//        long streak = (long) map.get("streak");
        String user_id = (String) map.get("user_id");
        ArrayList<HashMap<String, Object>> replies = (ArrayList<HashMap<String, Object>>) map.get("replies");
        HashMap<String, Object> reply_location = replies.get(position_of_reply);
        int index = replies.indexOf(reply_location);
        replies.remove(position_of_reply);
        replies.add(0, reply_location);

        // firebase firestore
        //document id
        //position
        // map with time
//        String category = (String) map.get("category");
        Timestamp date = (Timestamp) map.get("date");
        //fire base user
        int position_of_real = (int) map.get("position_of_real");
        String name = (String) map.get("name");
        example_list.add(0, new Example_item_comments_feed(body, awards, dev, up_vote_list, down_vote_list, report_list, (int) -1, user_id, replies, m_firebaseFirestore, position_of_real, m_document_id, date.toDate(), "", FirebaseAuth.getInstance().getCurrentUser(), name, m_title, m_body, m_span, m_time, m_category, m_streak, name_of_the_user, index));
        adapter.notifyDataSetChanged();
    }

    private void make_the_text_saying_no_comments_visible() {
        if (getView() != null) {
            TextView text_saying_the_the_comments_are_empty = getView().findViewById(R.id.text_saying_the_the_comments_are_empty);
            RecyclerView recycle_view_to_show_the_comments = getView().findViewById(R.id.recycle_view_to_show_the_comments);
            Button button_to_sort_stuff_inside_post_for_comments = getView().findViewById(R.id.button_to_sort_stuff_inside_post_for_comments);
            text_saying_the_the_comments_are_empty.setVisibility(View.VISIBLE);
            recycle_view_to_show_the_comments.setVisibility(View.GONE);
            text_saying_the_the_comments_are_empty.setText("No comments are available for archived posts.");
            button_to_sort_stuff_inside_post_for_comments.setVisibility(View.INVISIBLE);
        }
    }

    private void all_buttons_listen_for_old_post() {
        if (getView() != null && getContext() != null) {
            Button button_to_watch_upvote_in_card_in_post = getView().findViewById(R.id.button_to_watch_upvote_in_card_in_post);
            Button button_to_watch_downvote_in_card_in_post = getView().findViewById(R.id.button_to_watch_downvote_in_card_in_post);
            Button button_showing_rippl_for_gift_in_view = getView().findViewById(R.id.button_showing_rippl_for_gift_in_view);
            Button button_to_watch_gift_in_card_in_post = getView().findViewById(R.id.button_to_watch_gift_in_card_in_post);
            Button button_showing_ripple_for_three_dot_in_full_view = getView().findViewById(R.id.button_showing_ripple_for_three_dot_in_full_view);
            Button button_to_add_comment = getView().findViewById(R.id.button_to_add_comment);
            button_to_watch_upvote_in_card_in_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "can't up vote archived posts", Toast.LENGTH_SHORT).show();
                }
            });
            button_to_watch_downvote_in_card_in_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "can't down vote archived posts", Toast.LENGTH_SHORT).show();
                }
            });
            button_showing_rippl_for_gift_in_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "can't gift archived posts", Toast.LENGTH_SHORT).show();
                }
            });
            button_to_watch_gift_in_card_in_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "can't gift archived posts", Toast.LENGTH_SHORT).show();
                }
            });
            button_showing_ripple_for_three_dot_in_full_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(getContext(), button_showing_ripple_for_three_dot_in_full_view);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            Toast.makeText(getActivity(), "can't report archived posts", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    });
                    popupMenu.inflate(R.menu.report_in_full_post);
                    popupMenu.show();
                }
            });
            button_to_add_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "can't comment on archived posts", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void add_the_comment_saved(String body_of_the_comment, String name_of_the_comment, Date time_of_the_comment, String category_comment, int streak_of_the_comment, boolean is_this_saved, int position) {
        example_list.add(new Example_item_comments_feed(m_document_id, m_title, m_body, m_span, m_time, m_category, m_streak, name_of_the_user, position, body_of_the_comment, name_of_the_comment, time_of_the_comment, category_comment, streak_of_the_comment, is_this_saved));
        adapter.notifyDataSetChanged();
    }

    /*private void save_button_listen_in_your_post() {
        if (getView() != null) {
            Button button_to_watch_save_in_card_in_post = getView().findViewById(R.id.button_to_watch_save_in_card_in_post);
            Button button_showing_rippl_for_save_in_view = getView().findViewById(R.id.button_showing_rippl_for_save_in_view);
            button_to_watch_save_in_card_in_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Post already saved", Toast.LENGTH_SHORT).show();
                }
            });
            button_showing_rippl_for_save_in_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Post already saved", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }*/

    private void add_comment_reply(String body_of_the_comment, String name_of_the_comment, Date time_of_the_comment, String category_comment, int streak_of_the_comment, int position, String body_reply, int position_reply, String name_reply, Date time_reply, String category_reply, int streak_reply) {
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("body", body_reply);
        hashMap.put("name", name_reply);
        hashMap.put("position_reply", position_reply);
        hashMap.put("date", new Timestamp(time_reply));
        hashMap.put("category", category_reply);
        hashMap.put("streak", streak_reply);
        arrayList.add(hashMap);
        example_list.add(new Example_item_comments_feed(m_document_id, m_title, m_body, m_span, m_time, m_category, m_streak, name_of_the_user, position, body_of_the_comment, name_of_the_comment, time_of_the_comment, category_comment, streak_of_the_comment, arrayList));
        adapter.notifyDataSetChanged();
    }

    private void gift_button_listen() {
        if (getView() != null) {
            Button button_to_watch_gift_in_card_in_post = getView().findViewById(R.id.button_to_watch_gift_in_card_in_post);
            Button button_showing_rippl_for_gift_in_view = getView().findViewById(R.id.button_showing_rippl_for_gift_in_view);
            button_to_watch_gift_in_card_in_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (am_i_signed_in_with_google()) {
                        Bottom_sheet_to_give_coins bottom_sheet_to_give_coins = new Bottom_sheet_to_give_coins(position, real_user_id, m_document_id, m_awards, "show");
                        bottom_sheet_to_give_coins.set_update_gift(new Bottom_sheet_to_give_coins.update_gift_listen() {
                            @Override
                            public void gift_was_clicked(int index, ArrayList<Long> arrayList_of_awards) {
                                m_awards = arrayList_of_awards;
                                set_up_the_awards(m_awards.get(0).intValue(), m_awards.get(1).intValue(), m_awards.get(2).intValue());
                            }
                        });
                        bottom_sheet_to_give_coins.show(getParentFragmentManager(), "");
                    } else {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Sign in with google")
                                .setMessage("You must sign in with google in order to be able to gift")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Bottom_sheet_to_sing_in_to_write_posts bottom_sheet_to_sing_in_to_write_posts = new Bottom_sheet_to_sing_in_to_write_posts();
                                        bottom_sheet_to_sing_in_to_write_posts.setTargetFragment(Show_full_post.this, 254);
                                        bottom_sheet_to_sing_in_to_write_posts.show(getActivity().getSupportFragmentManager(), "tag");
                                    }
                                }).setNegativeButton("cancel", null)
                                .show();
                    }
                }
            });
            button_showing_rippl_for_gift_in_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (am_i_signed_in_with_google()) {
                        Bottom_sheet_to_give_coins bottom_sheet_to_give_coins = new Bottom_sheet_to_give_coins(position, real_user_id, m_document_id, m_awards, "show");
                        bottom_sheet_to_give_coins.set_update_gift(new Bottom_sheet_to_give_coins.update_gift_listen() {
                            @Override
                            public void gift_was_clicked(int index, ArrayList<Long> arrayList_of_awards) {
                                m_awards = arrayList_of_awards;
                                set_up_the_awards(m_awards.get(0).intValue(), m_awards.get(1).intValue(), m_awards.get(2).intValue());
                            }
                        });
                        bottom_sheet_to_give_coins.show(getParentFragmentManager(), "");
                    } else {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Sign in with google")
                                .setMessage("You must sign in with google in order to be able to gift")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Bottom_sheet_to_sing_in_to_write_posts bottom_sheet_to_sing_in_to_write_posts = new Bottom_sheet_to_sing_in_to_write_posts();
                                        bottom_sheet_to_sing_in_to_write_posts.setTargetFragment(Show_full_post.this, 254);
                                        bottom_sheet_to_sing_in_to_write_posts.show(getActivity().getSupportFragmentManager(), "tag");
                                    }
                                }).setNegativeButton("cancel", null)
                                .show();
                    }
                }
            });
        }
    }
}