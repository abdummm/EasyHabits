package com.easyhabitsapp.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static android.content.Context.MODE_PRIVATE;

public class Adapter_for_feed_posts extends RecyclerView.Adapter<Adapter_for_feed_posts.ExampleViewHolder> {
    private ArrayList<Example_item_feed_posts> m_example_list;
    private up_vote_clicked_not_signed_in up_vote_listener;
    private image_clicked_interface iamge_listen;
    private data_is_loaded data_is_loaded_listen;
    private post_is_clicked post_is_clicked_listener;
    private ExampleViewHolder global_holder;
    private Example_item_feed_posts global_current_item;
    private String global_document_id;
    private FirebaseFirestore global_fire_base_fire_store;
    private String global_my_id;
    private share_button_was_clicked share_button_was_clicked_listen;
    private old_post_is_clicked old_post_is_clicked_listen;
    private gift_button_listen gift_button_listen_listener;
    private save_only_for_pro save_only_for_pro_listener;
    private hide_post_clickled hide_post_listener;

    public void set_on_item_click_listen(up_vote_clicked_not_signed_in listener) {
        up_vote_listener = listener;
    }

    public interface up_vote_clicked_not_signed_in {
        void up_vote_click_no_sign_in(String mode);
    }

    public void set_image_click_listen(image_clicked_interface listener) {
        iamge_listen = listener;
    }

    public interface image_clicked_interface {
        void image_clicked(String base64);
    }

    public void set_data_load_listen(data_is_loaded listen) {
        data_is_loaded_listen = listen;
    }

    public interface data_is_loaded {
        void data_is_done_loading_from_adapter();
    }

    public void set_item_click_listen(post_is_clicked listen) {
        post_is_clicked_listener = listen;
    }


    public interface post_is_clicked {
        void post_just_got_clicked(int position, String m_title, String m_body, String m_category, Date m_time, Long m_streak, String m_span, boolean m_image, String m_user_id, ArrayList<HashMap<String, Object>> m_comments, ArrayList<Long> m_awards, boolean is_this_upvote, boolean is_this_down_vote, String m_document_id, boolean m_is_this_from_fire_base, ArrayList<String> m_upvotes, ArrayList<String> m_downvotes, boolean post_saved, int plus_or_minus, boolean m_is_this_laoding, boolean i_already_loaded, boolean upvote_or_down_vote_was_clicked, FirebaseFirestore m_firebaseFirestore, FirebaseUser m_firebaseUser, boolean was_this_reported, ArrayList<String> m_list_of_reports, boolean am_i_loading_up_voote_down_vote_from_fire_base, boolean did_i_see_this_tem, ArrayList<String> m_list_of_seen_posts, boolean m_is_post_by_dev,String name);
    }
    public void set_share_clicked_listen(share_button_was_clicked listen){
        share_button_was_clicked_listen = listen;
    }
    public interface share_button_was_clicked {
        void share_just_got_clciked(String title,String body);
    }

    public void set_old_button_clicked_listen(old_post_is_clicked listen){
        old_post_is_clicked_listen = listen;
    }
    public interface old_post_is_clicked {
        void old_post_got_clicked (int position, String name,String title,String body,String span,Date time,String category,int streak,String document_id);
    }

    public void set_up_gift_listen(gift_button_listen listen) {
        gift_button_listen_listener = listen;
    }

    public interface gift_button_listen {
        void gift_was_clicked(int index, String user_id,String document_id,int comment_position,int reply_position,ArrayList<Long> awards);
    }

    public void set_up_save_only_for_pro_listen(save_only_for_pro listen) {
        save_only_for_pro_listener = listen;
    }

    public interface save_only_for_pro {
        void save_only_for_pro();
    }

    public void set_up_hide_post(hide_post_clickled listen) {
        hide_post_listener = listen;
    }

    public interface hide_post_clickled {
        void hide_post_clickled(int position);
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_feed, parent, false);
        ExampleViewHolder exampleViewHolder = new ExampleViewHolder(view);
        return exampleViewHolder;
    }

    public Adapter_for_feed_posts(ArrayList<Example_item_feed_posts> example_item_feed_posts) {
        m_example_list = example_item_feed_posts;
    }

    @Override
    public void onBindViewHolder(@NonNull final ExampleViewHolder holder, final int position) {
        final Example_item_feed_posts current_item = m_example_list.get(position);
        global_holder = holder;
        global_current_item = current_item;
        if (current_item.isM_is_this_laoding() && !current_item.get_i_already_laoded()) {
            current_item.set_i_already_load(true);
            holder.card_showing_the_non_loading.setVisibility(View.GONE);
            holder.card_showing_teh_loading.setVisibility(View.VISIBLE);
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    data_is_loaded_listen.data_is_done_loading_from_adapter();
                    holder.card_showing_the_non_loading.setVisibility(View.VISIBLE);
                    holder.card_showing_teh_loading.setVisibility(View.GONE);
                    do_everything(current_item, holder);
                }
            }, 3000);
        } else {
            do_everything(current_item, holder);
        }
    }

    private void do_everything(final Example_item_feed_posts current_item, @NonNull final ExampleViewHolder holder) {
        if (current_item.get_is_this_from_firebase()) {
            resest_the_stuff(holder, holder.constriant_inside_card_inside_post_feed.getContext());
            //deal with the image
            if (current_item.isM_image()) {
               /* ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed;
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(holder.layput_to_put_the_two_buttons_in.getId(), ConstraintSet.TOP, holder.image_to_show_any_base_64_in_psot.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(15, holder.constriant_inside_card_inside_post_feed.getContext()));
                constraintSet.applyTo(constraintLayout);
                holder.image_to_show_any_base_64_in_psot.setVisibility(View.VISIBLE);
                holder.actual_text_to_show_post_in_card.setVisibility(View.INVISIBLE);
                byte[] decodedString = Base64.decode(current_item.getM_body(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                holder.image_to_show_any_base_64_in_psot.setImageBitmap(decodedByte);*/
            } else {
                ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed;
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(holder.layput_to_put_the_two_buttons_in.getId(), ConstraintSet.TOP, holder.actual_text_to_show_post_in_card.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(15, holder.constriant_inside_card_inside_post_feed.getContext()));
                constraintSet.applyTo(constraintLayout);
                holder.image_to_show_any_base_64_in_psot.setVisibility(View.GONE);
                holder.actual_text_to_show_post_in_card.setVisibility(View.VISIBLE);
                holder.actual_text_to_show_post_in_card.setText(first_letter_capital(current_item.getM_body()));
            }

            // put the main information
            holder.title_for_post_in_posts_to_show_for_public.setText(make_first_letter_cap(current_item.getM_title()));
            if (current_item.getM_upvotes() >= 1) {
                holder.text_showing_the_number_of_up_votes.setText(String.valueOf(current_item.getM_upvotes()));
            } else {
                holder.text_showing_the_number_of_up_votes.setText("1");
            }
            holder.text_telling_the_number_of_comments_in_card_in_post.setText(String.valueOf(current_item.getM_number_of_comments()));
            holder.text_at_top_left_of_card_to_show_time.setText(return_time_at_top(current_item.getM_time()));
            //holder.text_at_the_top_showing_what_category_this_is_in.setText(current_item.get_m_category());

            //hide the streak
            /*if (current_item.getM_streak() < 0) {
                holder.text_at_the_top_showing_how_much_is_the_streak.setVisibility(View.INVISIBLE);
                holder.circle_between_time_and_cat_in_streak_in_post.setVisibility(View.INVISIBLE);
                ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed;
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(holder.circle_between_the_streak_and_the_dev_icon.getId(), ConstraintSet.START, holder.text_at_the_top_showing_what_category_this_is_in.getId(), ConstraintSet.END, (int) convertDpToPixel(5, holder.constriant_inside_card_inside_post_feed.getContext()));
                constraintSet.applyTo(constraintLayout);
            } else {
                holder.text_at_the_top_showing_how_much_is_the_streak.setVisibility(View.VISIBLE);
                holder.circle_between_time_and_cat_in_streak_in_post.setVisibility(View.VISIBLE);
                if (current_item.getM_streak() == 1) {
                    holder.text_at_the_top_showing_how_much_is_the_streak.setText("1 day");
                } else {
                    holder.text_at_the_top_showing_how_much_is_the_streak.setText(String.valueOf(current_item.getM_streak()).concat(" days"));
                }
            }*/

            // put the awards
            long plat_award = current_item.getM_awards().get(0);
            long gold_award = current_item.getM_awards().get(1);
            long silver_award = current_item.getM_awards().get(2);
            if (plat_award == 0 && gold_award == 0 && silver_award == 0) {
                make_the_awards_be_invisble(holder);
                ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed;
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(holder.title_for_post_in_posts_to_show_for_public.getId(), ConstraintSet.TOP, holder.three_dots_at_the_top_right_of_post.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(5, holder.constriant_inside_card_inside_post_feed.getContext()));
                constraintSet.applyTo(constraintLayout);
            } else {
                make_every_award_visible(holder);
                {
                    ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed;
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(holder.title_for_post_in_posts_to_show_for_public.getId(), ConstraintSet.TOP, holder.text_saying_the_number_of_awards.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(15, holder.constriant_inside_card_inside_post_feed.getContext()));
                    constraintSet.applyTo(constraintLayout);
                }
                holder.plat_text_beside_the_award_posts.setText(String.valueOf(plat_award));
                holder.plat_text_beside_the_gold_award_posts.setText(String.valueOf(gold_award));
                holder.silver_text_beside_the_award_post.setText(String.valueOf(silver_award));
                long awards_total_number = plat_award + gold_award + silver_award;
                if (awards_total_number == 1) {
                    holder.text_saying_the_number_of_awards.setText("1 award");
                } else {
                    holder.text_saying_the_number_of_awards.setText(String.valueOf(awards_total_number).concat(" awards"));
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
                    ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed;
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(holder.circle_between_gold_and_silver_in_post.getId(), ConstraintSet.START, holder.plat_text_beside_the_award_posts.getId(), ConstraintSet.END, (int) convertDpToPixel(5, holder.constriant_inside_card_inside_post_feed.getContext()));
                    constraintSet.applyTo(constraintLayout);
                } else if (plat_award > 0 && gold_award > 0 && silver_award == 0) {
                    make_the_silver_invisible(holder);
                }
            }
            // up vote and down vote
            deal_with_the_upvote_and_down_vote(holder, current_item);


            // deal with the save
            if (current_item.get_m_saved()) {
                holder.save_button_in_card_in_posts.setBackground(ContextCompat.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.round_turned_in_24));
            } else if (is_this_saved(holder.constriant_inside_card_inside_post_feed.getContext(), current_item.return_the_document_id())) {
                holder.save_button_in_card_in_posts.setBackground(ContextCompat.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.round_turned_in_24));
                current_item.set_saved(true);
            } else {
                holder.save_button_in_card_in_posts.setBackground(ContextCompat.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.round_turned_in_not_24));
            }
            holder.button_to_watch_save_in_card_in_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (current_item.get_m_saved()) {
                        holder.save_button_in_card_in_posts.setBackground(ContextCompat.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.round_turned_in_not_24));
                        remove_the_post(holder.constriant_inside_card_inside_post_feed.getContext(), current_item.return_the_document_id());
                        current_item.set_saved(false);
                    } else {
                        if (Payment_processer.getInstance().state_of_the_user()) {
                            holder.save_button_in_card_in_posts.setBackground(ContextCompat.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.round_turned_in_24));
                            save_the_post(holder.constriant_inside_card_inside_post_feed.getContext(), current_item.return_the_document_id(), current_item.getM_title(), current_item.getM_body(), current_item.getM_span(), current_item.getM_time().getTime(), current_item.get_m_category(), current_item.getM_streak(), current_item.getName_of_the_poster());
                            current_item.set_saved(true);
                        } else {
                            save_only_for_pro_listener.save_only_for_pro();
                        }
                    }
                }
            });
            report_button_listen(holder, holder.constriant_inside_card_inside_post_feed.getContext(), current_item.return_the_document_id(), current_item.get_the_data_base(), current_item.getM_my_user_id(), current_item);
            if (current_item.is_this_reported() || current_item.return_list_of_reports().contains(current_item.getM_my_user_id())) {
                Drawable drawable = ContextCompat.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.round_flag_24);
                holder.three_dots_at_the_top_right_of_post.setBackground(drawable);
                current_item.set_the_report(true);
            } else {
                Drawable drawable = ContextCompat.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.outline_outlined_flag_24);
                holder.three_dots_at_the_top_right_of_post.setBackground(drawable);
            }
            holder.card_showing_the_non_loading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    current_item.setDid_i_see_this_tem(true);
                    add_me_to_seen_posts(current_item, holder.constriant_inside_card_inside_post_feed.getContext());
                    holder.title_for_post_in_posts_to_show_for_public.setTextColor(Color.parseColor("#888888"));
                    holder.actual_text_to_show_post_in_card.setTextColor(Color.parseColor("#888888"));
                    post_is_clicked_listener.post_just_got_clicked(holder.getAdapterPosition(), current_item.getM_title(), current_item.getM_body(), current_item.get_m_category(), current_item.getM_time(), current_item.getM_streak(), current_item.getM_span(), current_item.isM_image(), current_item.getM_user_id(), current_item.getM_comments(), current_item.getM_awards(), current_item.get_is_this_upvote(), current_item.isIs_this_down_vote(), current_item.getM_document_id(), current_item.isM_is_this_from_fire_base(), current_item.get_upvote_list(), current_item.getM_downvotes(), current_item.get_m_saved(), current_item.getPlus_or_minus(), current_item.isM_is_this_laoding(), current_item.isI_already_loaded(), current_item.isUpvote_or_down_vote_was_clicked(), current_item.getM_firebaseFirestore(), current_item.getM_firebaseUser(), current_item.isWas_this_reported(), current_item.getM_list_of_reports(), current_item.isAm_i_loading_up_voote_down_vote_from_fire_base(), current_item.isDid_i_see_this_tem(), current_item.getM_list_of_seen_posts(), current_item.isM_is_post_by_dev(),current_item.getName_of_the_poster());
                }
            });
            holder.image_to_show_any_base_64_in_psot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (current_item.isM_image()) {
                        iamge_listen.image_clicked(current_item.getM_body());
                    }
                }
            });
            handle_the_color_of_card(current_item, holder);
            global_document_id = current_item.return_the_document_id();
            global_fire_base_fire_store = current_item.get_the_data_base();
            global_my_id = current_item.getM_my_user_id();
            share_button_listen(holder, current_item);
            set_the_name(holder, current_item);
            gift_button_listen(holder, current_item);
            set_is_this_by_dev(current_item.isM_is_post_by_dev(),holder,current_item);
            three_dot_listen(holder, holder.constriant_inside_card_inside_post_feed.getContext(), current_item.return_the_document_id(), current_item.get_the_data_base(), current_item.getM_my_user_id(), current_item);
        } else {
            set_the_name(holder, current_item);
            holder.title_for_post_in_posts_to_show_for_public.setText(make_first_letter_cap(current_item.getM_title()));
            holder.actual_text_to_show_post_in_card.setText(first_letter_capital(current_item.getM_body()));
            holder.text_at_top_left_of_card_to_show_time.setText(return_time_at_top(current_item.getM_time()));
            //holder.text_at_the_top_showing_what_category_this_is_in.setText(current_item.get_m_category());
            /*if (current_item.getM_streak() < 0) {
                holder.text_at_the_top_showing_how_much_is_the_streak.setVisibility(View.INVISIBLE);
                holder.circle_between_time_and_cat_in_streak_in_post.setVisibility(View.INVISIBLE);
                ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed;
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(holder.circle_between_the_streak_and_the_dev_icon.getId(), ConstraintSet.START, holder.text_at_the_top_showing_what_category_this_is_in.getId(), ConstraintSet.END, (int) convertDpToPixel(5, holder.constriant_inside_card_inside_post_feed.getContext()));
                constraintSet.applyTo(constraintLayout);
            } else {
                holder.text_at_the_top_showing_how_much_is_the_streak.setVisibility(View.VISIBLE);
                holder.circle_between_time_and_cat_in_streak_in_post.setVisibility(View.VISIBLE);
                if (current_item.getM_streak() == 1) {
                    holder.text_at_the_top_showing_how_much_is_the_streak.setText("1 day");
                } else {
                    holder.text_at_the_top_showing_how_much_is_the_streak.setText(String.valueOf(current_item.getM_streak()).concat(" days"));
                }
            }*/
            holder.text_showing_the_number_of_up_votes.setText("1");
            holder.text_telling_the_number_of_comments_in_card_in_post.setText("0");

            make_the_awards_be_invisble(holder);
            ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(holder.title_for_post_in_posts_to_show_for_public.getId(), ConstraintSet.TOP, holder.three_dots_at_the_top_right_of_post.getId(), ConstraintSet.BOTTOM, (int) convertDpToPixel(5, holder.constriant_inside_card_inside_post_feed.getContext()));
            constraintSet.applyTo(constraintLayout);
            set_is_this_by_dev(false,holder,current_item);
            if (current_item.get_m_saved()) {
                holder.save_button_in_card_in_posts.setBackground(ContextCompat.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.round_turned_in_24));
            } else if (is_this_saved(holder.constriant_inside_card_inside_post_feed.getContext(), current_item.return_the_document_id())) {
                holder.save_button_in_card_in_posts.setBackground(ContextCompat.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.round_turned_in_24));
                current_item.set_saved(true);
            } else {
                holder.save_button_in_card_in_posts.setBackground(ContextCompat.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.round_turned_in_not_24));
            }

            Drawable drawable = ContextCompat.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.outline_outlined_flag_24);
            holder.three_dots_at_the_top_right_of_post.setBackground(drawable);
            holder.button_to_watch_three_dot_in_card_in_post_in_top.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(holder.constriant_inside_card_inside_post_feed.getContext(), "can't report achieved posts",Toast.LENGTH_SHORT).show();
                }
            });
            holder.button_to_watch_upvote_in_card_in_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(holder.constriant_inside_card_inside_post_feed.getContext(), "can't up vote achieved posts",Toast.LENGTH_SHORT).show();
                }
            });
            holder.button_to_watch_downvote_in_card_in_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(holder.constriant_inside_card_inside_post_feed.getContext(), "can't down vote achieved posts",Toast.LENGTH_SHORT).show();
                }
            });
            holder.button_to_watch_gift_in_card_in_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(holder.constriant_inside_card_inside_post_feed.getContext(), "can't award achieved posts",Toast.LENGTH_SHORT).show();
                }
            });
            share_button_listen(holder, current_item);
            holder.button_to_watch_save_in_card_in_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Event_manager_all_in_one.getInstance().record_fire_base_event(holder.constriant_inside_card_inside_post_feed.getContext(), Event_manager_all_in_one.Event_type_fire_base_record.post_saved,false);
                    if (current_item.get_m_saved()) {
                        holder.save_button_in_card_in_posts.setBackground(ContextCompat.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.round_turned_in_not_24));
                        remove_the_post(holder.constriant_inside_card_inside_post_feed.getContext(), current_item.return_the_document_id());
                        current_item.set_saved(false);
                    } else {
                        if (Payment_processer.getInstance().state_of_the_user()) {
                            holder.save_button_in_card_in_posts.setBackground(ContextCompat.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.round_turned_in_24));
                            save_the_post(holder.constriant_inside_card_inside_post_feed.getContext(), current_item.return_the_document_id(), current_item.getM_title(), current_item.getM_body(), current_item.getM_span(), current_item.getM_time().getTime(), current_item.get_m_category(), current_item.getM_streak(), current_item.getName_of_the_poster());
                            current_item.set_saved(true);
                        } else {
                            save_only_for_pro_listener.save_only_for_pro();
                        }
                    }
                }
            });
            holder.card_showing_the_non_loading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    old_post_is_clicked_listen.old_post_got_clicked(holder.getAdapterPosition(),current_item.getName_of_the_poster(),current_item.getM_title(),current_item.getM_body(),current_item.getM_span(),current_item.getM_time(),current_item.getM_category(),(int) current_item.getM_streak(),current_item.getM_document_id());
                }
            });
            three_dot_listen_for_archieved(holder,holder.constriant_inside_card_inside_post_feed.getContext(),current_item.return_the_document_id(),current_item);
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
        public TextView title_for_post_in_posts_to_show_for_public;
        public TextView actual_text_to_show_post_in_card;
        public ImageView image_to_show_any_base_64_in_psot;
        public ConstraintLayout constriant_inside_card_inside_post_feed;
        public ConstraintLayout layput_to_put_the_two_buttons_in;
        public TextView text_showing_the_number_of_up_votes;
        public TextView text_telling_the_number_of_comments_in_card_in_post;
        public TextView text_at_top_left_of_card_to_show_time;
        //public TextView text_at_the_top_showing_what_category_this_is_in;
        //public TextView text_at_the_top_showing_how_much_is_the_streak;
        //public View circle_between_time_and_cat_in_streak_in_post;
        public View circle_between_time_and_dev_in_card;

        public View start_part_of_the_grey_part_in_post_award;
        public View middle_part_of_the_grey_part_in_post_award;
        public View end_part_of_the_grey_part_in_post_award;
        public TextView text_saying_the_number_of_awards;
        public View circle_between_awards_and_plat_in_post;
        public View view_showing_the_plat_award;
        public TextView plat_text_beside_the_award_posts;
        public View circle_between_plat_and_gold_in_post;
        public View view_showing_the_gold_award;
        public TextView plat_text_beside_the_gold_award_posts;
        public View circle_between_gold_and_silver_in_post;
        public View view_showing_the_silver_award;
        public TextView silver_text_beside_the_award_post;

        public View three_dots_at_the_top_right_of_post;
        public Button button_to_watch_upvote_in_card_in_post;
        public View upvote_view_in_post_card;
        public Button button_to_watch_downvote_in_card_in_post;
        public View down_vote_view_in_post_card;
        public View save_button_in_card_in_posts;
        public Button button_to_watch_save_in_card_in_post;
        public CardView card_showing_teh_loading;
        public CardView card_showing_the_non_loading;
        public ConstraintLayout main_layout_in_posts_in_card_in_item;
        public Button button_to_watch_three_dot_in_card_in_post_in_top;
        //public View circle_between_the_streak_and_the_dev_icon;
        public Button button_to_watch_share_in_card_in_post_in_top;
        public TextView text_saying_name_in_feed_post_recylce_view_card;
        public TextView text_view_saying_i_am_the_dev_of_the_app;
        public View view_at_the_top_of_the_dev_object;
        public View view_at_the_bottom_of_the_dev_object;
        public View view_at_the_start_of_the_dev_object;
        public View view_at_the_end_of_the_dev_object;
        public View view_behind_i_am_the_dev_of_the_text;
        public Button button_to_watch_gift_in_card_in_post;
        public Button button_watch_three_dot_to_add_extra_options;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            title_for_post_in_posts_to_show_for_public = itemView.findViewById(R.id.title_for_post_in_posts_to_show_for_public);
            actual_text_to_show_post_in_card = itemView.findViewById(R.id.actual_text_to_show_post_in_card);
            image_to_show_any_base_64_in_psot = itemView.findViewById(R.id.image_to_show_any_base_64_in_psot);
            constriant_inside_card_inside_post_feed = itemView.findViewById(R.id.constriant_inside_card_inside_post_feed);
            layput_to_put_the_two_buttons_in = itemView.findViewById(R.id.layput_to_put_the_two_buttons_in);
            text_showing_the_number_of_up_votes = itemView.findViewById(R.id.text_showing_the_number_of_up_votes);
            text_telling_the_number_of_comments_in_card_in_post = itemView.findViewById(R.id.text_telling_the_number_of_comments_in_card_in_post);
            text_at_top_left_of_card_to_show_time = itemView.findViewById(R.id.text_at_top_left_of_card_to_show_time);
//            text_at_the_top_showing_what_category_this_is_in = itemView.findViewById(R.id.text_at_the_top_showing_what_category_this_is_in);
//            text_at_the_top_showing_how_much_is_the_streak = itemView.findViewById(R.id.text_at_the_top_showing_how_much_is_the_streak);
//            circle_between_time_and_cat_in_streak_in_post = itemView.findViewById(R.id.circle_between_time_and_cat_in_streak_in_post);
            start_part_of_the_grey_part_in_post_award = itemView.findViewById(R.id.start_part_of_the_grey_part_in_post_award);
            middle_part_of_the_grey_part_in_post_award = itemView.findViewById(R.id.middle_part_of_the_grey_part_in_post_award);
            end_part_of_the_grey_part_in_post_award = itemView.findViewById(R.id.end_part_of_the_grey_part_in_post_award);
            text_saying_the_number_of_awards = itemView.findViewById(R.id.text_saying_the_number_of_awards);
            circle_between_awards_and_plat_in_post = itemView.findViewById(R.id.circle_between_awards_and_plat_in_post);
            view_showing_the_plat_award = itemView.findViewById(R.id.view_showing_the_plat_award);
            plat_text_beside_the_award_posts = itemView.findViewById(R.id.plat_text_beside_the_award_posts);
            circle_between_plat_and_gold_in_post = itemView.findViewById(R.id.circle_between_plat_and_gold_in_post);
            view_showing_the_gold_award = itemView.findViewById(R.id.view_showing_the_gold_award);
            plat_text_beside_the_gold_award_posts = itemView.findViewById(R.id.plat_text_beside_the_gold_award_posts);
            circle_between_gold_and_silver_in_post = itemView.findViewById(R.id.circle_between_gold_and_silver_in_post);
            view_showing_the_silver_award = itemView.findViewById(R.id.view_showing_the_silver_award);
            silver_text_beside_the_award_post = itemView.findViewById(R.id.silver_text_beside_the_award_post);
            three_dots_at_the_top_right_of_post = itemView.findViewById(R.id.three_dots_at_the_top_right_of_post);
            button_to_watch_upvote_in_card_in_post = itemView.findViewById(R.id.button_to_watch_upvote_in_card_in_post);
            upvote_view_in_post_card = itemView.findViewById(R.id.upvote_view_in_post_card);
            button_to_watch_downvote_in_card_in_post = itemView.findViewById(R.id.button_to_watch_downvote_in_card_in_post);
            down_vote_view_in_post_card = itemView.findViewById(R.id.down_vote_view_in_post_card);
            save_button_in_card_in_posts = itemView.findViewById(R.id.save_button_in_card_in_posts);
            button_to_watch_save_in_card_in_post = itemView.findViewById(R.id.button_to_watch_save_in_card_in_post);
            card_showing_teh_loading = itemView.findViewById(R.id.card_showing_teh_loading);
            card_showing_the_non_loading = itemView.findViewById(R.id.card_showing_the_non_loading);
            main_layout_in_posts_in_card_in_item = itemView.findViewById(R.id.main_layout_in_posts_in_card_in_item);
            button_to_watch_three_dot_in_card_in_post_in_top = itemView.findViewById(R.id.button_to_watch_three_dot_in_card_in_post_in_top);
            //circle_between_the_streak_and_the_dev_icon = itemView.findViewById(R.id.circle_between_the_streak_and_the_dev_icon);
            button_to_watch_share_in_card_in_post_in_top = itemView.findViewById(R.id.button_to_watch_share_in_card_in_post_in_top);
            text_saying_name_in_feed_post_recylce_view_card = itemView.findViewById(R.id.text_saying_name_in_feed_post_recylce_view_card);
            text_view_saying_i_am_the_dev_of_the_app = itemView.findViewById(R.id.text_view_saying_i_am_the_dev_of_the_app);
            view_at_the_top_of_the_dev_object = itemView.findViewById(R.id.view_at_the_top_of_the_dev_object);
            view_at_the_bottom_of_the_dev_object = itemView.findViewById(R.id.view_at_the_bottom_of_the_dev_object);
            view_at_the_start_of_the_dev_object = itemView.findViewById(R.id.view_at_the_start_of_the_dev_object);
            view_at_the_end_of_the_dev_object = itemView.findViewById(R.id.view_at_the_end_of_the_dev_object);
            view_behind_i_am_the_dev_of_the_text = itemView.findViewById(R.id.view_behind_i_am_the_dev_of_the_text);
            button_to_watch_gift_in_card_in_post = itemView.findViewById(R.id.button_to_watch_gift_in_card_in_post);
            circle_between_time_and_dev_in_card = itemView.findViewById(R.id.circle_between_time_and_dev_in_card);
            button_watch_three_dot_to_add_extra_options = itemView.findViewById(R.id.button_watch_three_dot_to_add_extra_options);
        }
    }

    private static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
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

    private String make_first_letter_cap(String title) {
        return title.substring(0, 1).toUpperCase() + title.substring(1);
    }

    private void make_the_awards_be_invisble(@NonNull ExampleViewHolder holder) {
        holder.start_part_of_the_grey_part_in_post_award.setVisibility(View.GONE);
        holder.middle_part_of_the_grey_part_in_post_award.setVisibility(View.GONE);
        holder.end_part_of_the_grey_part_in_post_award.setVisibility(View.GONE);
        holder.text_saying_the_number_of_awards.setVisibility(View.GONE);
        holder.circle_between_awards_and_plat_in_post.setVisibility(View.GONE);
        holder.view_showing_the_plat_award.setVisibility(View.GONE);
        holder.plat_text_beside_the_award_posts.setVisibility(View.GONE);
        holder.circle_between_plat_and_gold_in_post.setVisibility(View.GONE);
        holder.view_showing_the_gold_award.setVisibility(View.GONE);
        holder.plat_text_beside_the_gold_award_posts.setVisibility(View.GONE);
        holder.circle_between_gold_and_silver_in_post.setVisibility(View.GONE);
        holder.view_showing_the_silver_award.setVisibility(View.GONE);
        holder.silver_text_beside_the_award_post.setVisibility(View.GONE);
    }

    private void make_every_award_visible(@NonNull ExampleViewHolder holder) {
        holder.start_part_of_the_grey_part_in_post_award.setVisibility(View.VISIBLE);
        holder.middle_part_of_the_grey_part_in_post_award.setVisibility(View.VISIBLE);
        holder.end_part_of_the_grey_part_in_post_award.setVisibility(View.VISIBLE);
        holder.text_saying_the_number_of_awards.setVisibility(View.VISIBLE);
        holder.circle_between_awards_and_plat_in_post.setVisibility(View.VISIBLE);
        holder.view_showing_the_plat_award.setVisibility(View.VISIBLE);
        holder.plat_text_beside_the_award_posts.setVisibility(View.VISIBLE);
        holder.circle_between_plat_and_gold_in_post.setVisibility(View.VISIBLE);
        holder.view_showing_the_gold_award.setVisibility(View.VISIBLE);
        holder.plat_text_beside_the_gold_award_posts.setVisibility(View.VISIBLE);
        holder.circle_between_gold_and_silver_in_post.setVisibility(View.VISIBLE);
        holder.view_showing_the_silver_award.setVisibility(View.VISIBLE);
        holder.silver_text_beside_the_award_post.setVisibility(View.VISIBLE);
    }

    private void make_the_plat_invisible(@NonNull ExampleViewHolder holder) {
        holder.circle_between_awards_and_plat_in_post.setVisibility(View.GONE);
        holder.view_showing_the_plat_award.setVisibility(View.GONE);
        holder.plat_text_beside_the_award_posts.setVisibility(View.GONE);
    }

    private void make_the_gold_invisible(@NonNull ExampleViewHolder holder) {
        holder.circle_between_plat_and_gold_in_post.setVisibility(View.GONE);
        holder.view_showing_the_gold_award.setVisibility(View.GONE);
        holder.plat_text_beside_the_gold_award_posts.setVisibility(View.GONE);
    }

    private void make_the_silver_invisible(@NonNull ExampleViewHolder holder) {
        holder.circle_between_gold_and_silver_in_post.setVisibility(View.GONE);
        holder.view_showing_the_silver_award.setVisibility(View.GONE);
        holder.silver_text_beside_the_award_post.setVisibility(View.GONE);
    }

    private void conect_me_to_start(int which, @NonNull ExampleViewHolder holder) {
        if (which == 1) {
            ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(holder.circle_between_plat_and_gold_in_post.getId(), ConstraintSet.START, holder.text_saying_the_number_of_awards.getId(), ConstraintSet.END, (int) convertDpToPixel(5, holder.constriant_inside_card_inside_post_feed.getContext()));
            constraintSet.applyTo(constraintLayout);
        } else if (which == 2) {
            ConstraintLayout constraintLayout = holder.constriant_inside_card_inside_post_feed;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(holder.circle_between_gold_and_silver_in_post.getId(), ConstraintSet.START, holder.text_saying_the_number_of_awards.getId(), ConstraintSet.END, (int) convertDpToPixel(5, holder.constriant_inside_card_inside_post_feed.getContext()));
            constraintSet.applyTo(constraintLayout);
        }
    }

    private boolean is_this_have_upvote(ArrayList<String> arrayList, String uid) {
        if (arrayList.contains(uid)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean is_this_have_down_vote(ArrayList<String> arrayList, String uid) {
        if (arrayList.contains(uid)) {
            return true;
        } else {
            return false;
        }
    }

    private void put_up_vote(String document_id, FirebaseFirestore firebaseFirestore, final String my_id, final Context context, final Example_item_feed_posts current_item) {
        final DocumentReference sfDocRef = firebaseFirestore.collection("posts").document(document_id);
        firebaseFirestore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);

                // Note: this could be done without a transaction
                //       by updating the population using FieldValue.increment()
                long newPopulation = snapshot.getLong("likes") + 1;
                ArrayList<String> array_lsit_with_upvote = (ArrayList<String>) snapshot.get("up_vote_list");
                array_lsit_with_upvote.add(my_id);
                transaction.update(sfDocRef, "likes", newPopulation, "up_vote_list", array_lsit_with_upvote,"type","main");
                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                current_item.setAm_i_loading_up_voote_down_vote_from_fire_base(false);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (!current_item.isAm_i_loading_up_voote_down_vote_from_fire_base()) {
                    Toast toast = Toast.makeText(context, "Cant upvote please try again later", Toast.LENGTH_SHORT);
                    toast.show();
                }
                current_item.setAm_i_loading_up_voote_down_vote_from_fire_base(false);
            }
        });
    }

    private void remove_the_upvote(final String document_id, final FirebaseFirestore firebaseFirestore, final String my_id, final Context context, final boolean run_after_this, final Example_item_feed_posts current_item) {
        final DocumentReference sfDocRef = firebaseFirestore.collection("posts").document(document_id);
        firebaseFirestore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);

                // Note: this could be done without a transaction
                //       by updating the population using FieldValue.increment()
                long newPopulation = snapshot.getLong("likes") - 1;
                ArrayList<String> array_lsit_with_upvote = (ArrayList<String>) snapshot.get("up_vote_list");
                array_lsit_with_upvote.remove(my_id);
                transaction.update(sfDocRef, "likes", newPopulation, "up_vote_list", array_lsit_with_upvote,"type","main");

                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                if (run_after_this) {
                    put_dwon_vote(document_id, firebaseFirestore, my_id, context, current_item);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (!current_item.isAm_i_loading_up_voote_down_vote_from_fire_base()) {
                    Toast toast = Toast.makeText(context, "Cant remove up vote please try again later", Toast.LENGTH_SHORT);
                    toast.show();
                }
                current_item.setAm_i_loading_up_voote_down_vote_from_fire_base(false);
            }
        });
    }

    private void put_dwon_vote(String document_id, FirebaseFirestore firebaseFirestore, final String my_id, final Context context, final Example_item_feed_posts current_item) {
        final DocumentReference sfDocRef = firebaseFirestore.collection("posts").document(document_id);
        firebaseFirestore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);

                // Note: this could be done without a transaction
                //       by updating the population using FieldValue.increment()
                long newPopulation = snapshot.getLong("likes") - 1;
                ArrayList<String> array_lsit_with_upvote = (ArrayList<String>) snapshot.get("down_vote_list");
                array_lsit_with_upvote.add(my_id);
                transaction.update(sfDocRef, "likes", newPopulation, "down_vote_list", array_lsit_with_upvote,"type","main");
                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                current_item.setAm_i_loading_up_voote_down_vote_from_fire_base(false);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (!current_item.isAm_i_loading_up_voote_down_vote_from_fire_base()) {
                    Toast toast = Toast.makeText(context, "Cant down vote please try again later", Toast.LENGTH_SHORT);
                    toast.show();
                }
                current_item.setAm_i_loading_up_voote_down_vote_from_fire_base(false);
            }
        });
    }

    private void remove_the_down_vote(final String document_id, final FirebaseFirestore firebaseFirestore, final String my_id, final Context context, final boolean start_after_this, final Example_item_feed_posts current_item) {
        final DocumentReference sfDocRef = firebaseFirestore.collection("posts").document(document_id);
        firebaseFirestore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);

                // Note: this could be done without a transaction
                //       by updating the population using FieldValue.increment()
                long newPopulation = snapshot.getLong("likes") + 1;
                ArrayList<String> array_lsit_with_upvote = (ArrayList<String>) snapshot.get("down_vote_list");
                array_lsit_with_upvote.remove(my_id);
                transaction.update(sfDocRef, "likes", newPopulation, "down_vote_list", array_lsit_with_upvote,"type","main");

                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                if (start_after_this) {
                    put_up_vote(document_id, firebaseFirestore, my_id, context, current_item);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (!current_item.isAm_i_loading_up_voote_down_vote_from_fire_base()) {
                    Toast toast = Toast.makeText(context, "Cant remove down vote please try again later", Toast.LENGTH_SHORT);
                    toast.show();
                }
                current_item.setAm_i_loading_up_voote_down_vote_from_fire_base(false);
            }
        });
    }

    private void resest_the_stuff(@NonNull ExampleViewHolder holder, Context context) {
        holder.text_showing_the_number_of_up_votes.setTextColor(Color.parseColor("#808080"));
        {
            Drawable unwrappedDrawable = AppCompatResources.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.round_arrow_drop_up_24).mutate();
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#808080"));
            holder.upvote_view_in_post_card.setBackground(wrappedDrawable);
        }
        {
            Drawable unwrappedDrawable = AppCompatResources.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.arrow_down_down_vote_card).mutate();
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#808080"));
            holder.down_vote_view_in_post_card.setBackground(wrappedDrawable);
        }
        Drawable not_saved = ContextCompat.getDrawable(context, R.drawable.round_turned_in_not_24);
        holder.save_button_in_card_in_posts.setBackground(not_saved);
    }

    private void save_the_post(Context context, String document_id, String title, String body, String span, long time, String category, long streak,String name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("saved_posts", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        String old = sharedPreferences.getString("posts", "");
        title = title.replace("big_divide", "").replace("small_split", "");
        body = body.replace("big_divide", "").replace("small_split", "");
        String save_me;
        if (old != null && !old.isEmpty()) {
            save_me = old.concat(name).concat("small_split").concat(title).concat("small_split").concat(body).concat("small_split").concat(span).concat("small_split").concat(String.valueOf(time)).concat("small_split").concat(document_id).concat("big_divide");
        } else {
            save_me = name.concat("small_split").concat(title).concat("small_split").concat(body).concat("small_split").concat(span).concat("small_split").concat(String.valueOf(time)).concat("small_split").concat(document_id).concat("big_divide");
        }
        myEdit.putString("posts", save_me);
        myEdit.commit();
    }

    private void remove_the_post(Context context, String document_id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("saved_posts", MODE_PRIVATE);
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

    private boolean is_this_saved(Context context, String document_id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("saved_posts", MODE_PRIVATE);
        String old = sharedPreferences.getString("posts", "");
        if (old != null && !old.isEmpty()) {
            String[] big_split = old.split("big_divide");
            for (int i = 0; i < big_split.length; i++) {
                String[] small_split = big_split[i].split("small_split");
                if (small_split[5].equals(document_id)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private void deal_with_the_upvote_and_down_vote(@NonNull final ExampleViewHolder holder, final Example_item_feed_posts current_item) {
        if (current_item.get_is_this_upvote() || (!current_item.return_state_of_touch() && is_this_have_upvote(current_item.get_upvote_list(), current_item.getM_my_user_id()))) {
            color_the_up_vote_down_vote(1, holder, current_item);
        }
        if (current_item.get_the_down_vote() || (!current_item.return_state_of_touch() && is_this_have_down_vote(current_item.getM_downvotes(), current_item.getM_my_user_id()))) {
            color_the_up_vote_down_vote(2, holder, current_item);
        }
        holder.button_to_watch_upvote_in_card_in_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event_manager_all_in_one.getInstance().record_fire_base_event(holder.constriant_inside_card_inside_post_feed.getContext(), Event_manager_all_in_one.Event_type_fire_base_record.post_upvoted,false);
                if (current_item.isAm_i_loading_up_voote_down_vote_from_fire_base()) {
                    Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed.getContext(), "You are up voting and down voting too quickly!!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (current_item.are_me_signed_with_google()) {
                        up_vote_was_clicked(holder, current_item);
                    } else {
                        if (up_vote_listener != null) {
                            up_vote_listener.up_vote_click_no_sign_in("up vote");
                        }
                    }
                }
            }
        });
        holder.button_to_watch_downvote_in_card_in_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event_manager_all_in_one.getInstance().record_fire_base_event(holder.constriant_inside_card_inside_post_feed.getContext(), Event_manager_all_in_one.Event_type_fire_base_record.post_downvoted,false);
                if (current_item.isAm_i_loading_up_voote_down_vote_from_fire_base()) {
                    Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed.getContext(), "You are up voting and down voting too quickly!!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (current_item.are_me_signed_with_google()) {
                        down_vote_was_cliclked(holder, current_item);
                    } else {
                        if (up_vote_listener != null) {
                            up_vote_listener.up_vote_click_no_sign_in("down vote");
                        }
                    }
                }
            }
        });
    }

    /*private void show_menu_to_report(View view,final Context context,final String document_id, final FirebaseFirestore firebaseFirestore, final String my_id){
            PopupMenu popupMenu = new PopupMenu(context, view);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId() == R.id.item_to_report_post_in_menu) {
                        report_post(document_id,firebaseFirestore,my_id,context);
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            popupMenu.inflate(R.menu.report_a_post);
            popupMenu.show();
    }*/
    private void report_post(final String document_id, final FirebaseFirestore firebaseFirestore, final String my_id, final Context context, final Example_item_feed_posts current_item, @NonNull final ExampleViewHolder holder) {
        final DocumentReference sfDocRef = firebaseFirestore.collection("posts").document(document_id);
        firebaseFirestore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);

                // Note: this could be done without a transaction
                //       by updating the population using FieldValue.increment()
                ArrayList<String> array_lsit_with_upvote = (ArrayList<String>) snapshot.get("reports");
                array_lsit_with_upvote.add(my_id);
                long number_of_reports = snapshot.getLong("number_of_reports");
                number_of_reports++;
                transaction.update(sfDocRef, "reports", array_lsit_with_upvote, "number_of_reports", number_of_reports,"type","main");

                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast toast = Toast.makeText(context, "Thanks, your input was received and greatly appreciated!!", Toast.LENGTH_LONG);
                toast.show();
                current_item.set_the_report(true);
                Drawable drawable = ContextCompat.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.round_flag_24);
                holder.three_dots_at_the_top_right_of_post.setBackground(drawable);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast toast = Toast.makeText(context, "Can't report right now please try again later", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void report_button_listen(@NonNull final ExampleViewHolder holder, final Context context, final String document_id, final FirebaseFirestore firebaseFirestore, final String my_id, final Example_item_feed_posts current_item) {
        holder.button_to_watch_three_dot_in_card_in_post_in_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event_manager_all_in_one.getInstance().record_fire_base_event(context, Event_manager_all_in_one.Event_type_fire_base_record.post_reported,false);
                report_is_clicked(holder,context,document_id,firebaseFirestore,my_id,current_item);
            }
        });
    }

    private void report_is_clicked(@NonNull final ExampleViewHolder holder, final Context context, final String document_id, final FirebaseFirestore firebaseFirestore, final String my_id, final Example_item_feed_posts current_item){
        if (current_item.are_me_signed_with_google()) {
            if (current_item.is_this_reported() || current_item.return_list_of_reports().contains(my_id)) {
                Toast toast = Toast.makeText(context, "Already reported", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                new AlertDialog.Builder(context)
                        .setTitle("Report post?")
                        .setMessage("Are you sure you want to report this post?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                report_post(document_id, firebaseFirestore, my_id, context, current_item, holder);
                            }
                        })
                        .setNegativeButton("cancel", null)
                        .show();
            }
        } else {
            up_vote_listener.up_vote_click_no_sign_in("report");
        }
    }

    private void handle_the_color_of_card(final Example_item_feed_posts current_item, @NonNull final ExampleViewHolder holder) {
        if (current_item.isDid_i_see_this_tem() || is_this_post_view(current_item)) {
            holder.title_for_post_in_posts_to_show_for_public.setTextColor(Color.parseColor("#888888"));
            holder.actual_text_to_show_post_in_card.setTextColor(Color.parseColor("#888888"));
        } else {
            holder.title_for_post_in_posts_to_show_for_public.setTextColor(Color.parseColor("#000000"));
            holder.actual_text_to_show_post_in_card.setTextColor(Color.parseColor("#000000"));
        }
    }

    private boolean is_this_post_view(Example_item_feed_posts current_item) {
        if (current_item.return_lsit_of_seen_posts().contains(current_item.return_the_document_id())) {
            return true;
        } else {
            return false;
        }
    }

    private void add_me_to_seen_posts(Example_item_feed_posts current_item, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("viewed_post_saved_id", MODE_PRIVATE);
        String old = sharedPreferences.getString("posts", "");
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        if (old != null && !old.isEmpty()) {
            long time = current_item.getM_time().getTime();
            String save_me = old.concat(String.valueOf(time)).concat("small_split").concat(current_item.return_the_document_id()).concat("max_divide");
            myEdit.putString("posts", save_me);
        } else {
            long time = current_item.getM_time().getTime();
            String save_me = String.valueOf(time).concat("small_split").concat(current_item.return_the_document_id()).concat("max_divide");
            myEdit.putString("posts", save_me);
        }
        myEdit.commit();
    }

    private void color_the_up_vote_down_vote(int which, @NonNull final ExampleViewHolder holder, Example_item_feed_posts current_item) {
        if (which == 0) {
            // no vote
            {
                holder.text_showing_the_number_of_up_votes.setTextColor(Color.parseColor("#808080"));
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.round_arrow_drop_up_24).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#808080"));
                holder.upvote_view_in_post_card.setBackground(wrappedDrawable);
            }
            {
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.arrow_down_down_vote_card).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#808080"));
                holder.down_vote_view_in_post_card.setBackground(wrappedDrawable);
            }
            current_item.set_is_this_upvoted(false);
            current_item.set_the_down_vote(false);
        } else if (which == 1) {
            // up vote
            {
                holder.text_showing_the_number_of_up_votes.setTextColor(Color.parseColor("#FF4500"));
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.round_arrow_drop_up_24).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#FF4500"));
                holder.upvote_view_in_post_card.setBackground(wrappedDrawable);
            }
            {
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.arrow_down_down_vote_card).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#808080"));
                holder.down_vote_view_in_post_card.setBackground(wrappedDrawable);
            }
            current_item.set_is_this_upvoted(true);
            current_item.set_the_down_vote(false);
        } else if (which == 2) {
            // down vote
            {
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.round_arrow_drop_up_24).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#808080"));
                holder.upvote_view_in_post_card.setBackground(wrappedDrawable);
            }
            {
                holder.text_showing_the_number_of_up_votes.setTextColor(Color.parseColor("#9494FF"));
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(holder.constriant_inside_card_inside_post_feed.getContext(), R.drawable.arrow_down_down_vote_card).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#9494FF"));
                holder.down_vote_view_in_post_card.setBackground(wrappedDrawable);
            }
            current_item.set_is_this_upvoted(false);
            current_item.set_the_down_vote(true);
        }
    }

    private void up_vote_was_clicked(@NonNull final ExampleViewHolder holder, final Example_item_feed_posts current_item) {
        int mode;
        if (is_this_have_upvote(current_item.get_upvote_list(), current_item.getM_my_user_id())) {
            mode = 1;
        } else if ((is_this_have_down_vote(current_item.getM_downvotes(), current_item.getM_my_user_id()))) {
            mode = 2;
        } else {
            mode = 3;
        }
        current_item.code_was_touched(true);
        if (current_item.get_the_down_vote()) {
            int upvotes = (int) current_item.getM_upvotes();
            upvotes = upvotes + 2;
            if (upvotes > 1) {
                holder.text_showing_the_number_of_up_votes.setText(String.valueOf(upvotes));
            } else {
                holder.text_showing_the_number_of_up_votes.setText("1");
                Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed.getContext(), "Recorded but not shown publicly", Toast.LENGTH_SHORT);
                toast.show();
            }
            color_the_up_vote_down_vote(1, holder, current_item);
            current_item.setAm_i_loading_up_voote_down_vote_from_fire_base(true);
            remove_the_down_vote(current_item.return_the_document_id(), current_item.get_the_data_base(), current_item.getM_my_user_id(), holder.constriant_inside_card_inside_post_feed.getContext(), true, current_item);
            if (mode == 1) {
                current_item.set_plus_or_minus(0);
            } else if (mode == 2) {
                current_item.set_plus_or_minus(2);
            } else {
                current_item.set_plus_or_minus(1);
            }
        } else {
            if (current_item.get_is_this_upvote()) {
                int upvotes = (int) current_item.getM_upvotes();
                upvotes--;
                if (upvotes > 1) {
                    holder.text_showing_the_number_of_up_votes.setText(String.valueOf(upvotes));
                } else {
                    holder.text_showing_the_number_of_up_votes.setText("1");
                    Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed.getContext(), "Recorded but not shown publicly", Toast.LENGTH_SHORT);
                    toast.show();
                }
                color_the_up_vote_down_vote(0, holder, current_item);
                remove_the_upvote(current_item.return_the_document_id(), current_item.get_the_data_base(), current_item.getM_my_user_id(), holder.constriant_inside_card_inside_post_feed.getContext(), false, current_item);
                if (mode == 1) {
                    current_item.set_plus_or_minus(-1);
                } else if (mode == 2) {
                    current_item.set_plus_or_minus(1);
                } else {
                    current_item.set_plus_or_minus(0);
                }
            } else {
                int upvotes = (int) current_item.getM_upvotes();
                upvotes++;
                if (upvotes > 1) {
                    holder.text_showing_the_number_of_up_votes.setText(String.valueOf(upvotes));
                } else {
                    holder.text_showing_the_number_of_up_votes.setText("1");
                    Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed.getContext(), "Recorded but not shown publicly", Toast.LENGTH_SHORT);
                    toast.show();
                }
                color_the_up_vote_down_vote(1, holder, current_item);
                put_up_vote(current_item.return_the_document_id(), current_item.get_the_data_base(), current_item.getM_my_user_id(), holder.constriant_inside_card_inside_post_feed.getContext(), current_item);
                if (mode == 1) {
                    current_item.set_plus_or_minus(0);
                } else if (mode == 2) {
                    current_item.set_plus_or_minus(2);
                } else {
                    current_item.set_plus_or_minus(1);
                }
            }
        }
    }

    private void down_vote_was_cliclked(@NonNull final ExampleViewHolder holder, final Example_item_feed_posts current_item) {
        int mode;
        if (is_this_have_upvote(current_item.get_upvote_list(), current_item.getM_my_user_id())) {
            mode = 1;
        } else if ((is_this_have_down_vote(current_item.getM_downvotes(), current_item.getM_my_user_id()))) {
            mode = 2;
        } else {
            mode = 3;
        }
        current_item.code_was_touched(true);
        if (current_item.get_is_this_upvote()) {
            int upvotes = (int) current_item.getM_upvotes();
            upvotes = upvotes - 2;
            if (upvotes > 1) {
                holder.text_showing_the_number_of_up_votes.setText(String.valueOf(upvotes));
            } else {
                holder.text_showing_the_number_of_up_votes.setText("1");
                Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed.getContext(), "Recorded but not shown publicly", Toast.LENGTH_SHORT);
                toast.show();
            }
            color_the_up_vote_down_vote(2, holder, current_item);
            current_item.setAm_i_loading_up_voote_down_vote_from_fire_base(true);
            remove_the_upvote(current_item.return_the_document_id(), current_item.get_the_data_base(), current_item.getM_my_user_id(), holder.constriant_inside_card_inside_post_feed.getContext(), true, current_item);
            if (mode == 1) {
                current_item.set_plus_or_minus(-2);
            } else if (mode == 2) {
                current_item.set_plus_or_minus(0);
            } else {
                current_item.set_plus_or_minus(-1);
            }
        } else {
            if (current_item.get_the_down_vote()) {
                int upvotes = (int) current_item.getM_upvotes();
                upvotes++;
                if (upvotes > 1) {
                    holder.text_showing_the_number_of_up_votes.setText(String.valueOf(upvotes));
                } else {
                    holder.text_showing_the_number_of_up_votes.setText("1");
                    Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed.getContext(), "Recorded but not shown publicly", Toast.LENGTH_SHORT);
                    toast.show();
                }
                color_the_up_vote_down_vote(0, holder, current_item);
                remove_the_down_vote(current_item.return_the_document_id(), current_item.get_the_data_base(), current_item.getM_my_user_id(), holder.constriant_inside_card_inside_post_feed.getContext(), false, current_item);
                if (mode == 1) {
                    current_item.set_plus_or_minus(-1);
                } else if (mode == 2) {
                    current_item.set_plus_or_minus(1);
                } else {
                    current_item.set_plus_or_minus(0);
                }
            } else {
                int upvotes = (int) current_item.getM_upvotes();
                upvotes--;
                if (upvotes > 1) {
                    holder.text_showing_the_number_of_up_votes.setText(String.valueOf(upvotes));
                } else {
                    holder.text_showing_the_number_of_up_votes.setText("1");
                    Toast toast = Toast.makeText(holder.constriant_inside_card_inside_post_feed.getContext(), "Recorded but not shown publicly", Toast.LENGTH_SHORT);
                    toast.show();
                }
                color_the_up_vote_down_vote(2, holder, current_item);
                put_dwon_vote(current_item.return_the_document_id(), current_item.get_the_data_base(), current_item.getM_my_user_id(), holder.constriant_inside_card_inside_post_feed.getContext(), current_item);
                if (mode == 1) {
                    current_item.set_plus_or_minus(-2);
                } else if (mode == 2) {
                    current_item.set_plus_or_minus(0);
                } else {
                    current_item.set_plus_or_minus(-1);
                }
            }
        }
    }

    private void share_button_listen(@NonNull final ExampleViewHolder holder, final Example_item_feed_posts current_item) {
        holder.button_to_watch_share_in_card_in_post_in_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event_manager_all_in_one.getInstance().record_fire_base_event(holder.constriant_inside_card_inside_post_feed.getContext(), Event_manager_all_in_one.Event_type_fire_base_record.post_shared,false);
                share_button_was_clicked_listen.share_just_got_clciked(first_letter_capital(current_item.getM_title()), current_item.getM_body());
            }
        });
    }

    private String first_letter_capital(String sentence) {
        return sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
    }

    private void set_the_name(@NonNull final ExampleViewHolder holder, final Example_item_feed_posts current_item){
        holder.text_saying_name_in_feed_post_recylce_view_card.setText(current_item.getName_of_the_poster());
    }

    private void set_is_this_by_dev(boolean mode,@NonNull final ExampleViewHolder holder, final Example_item_feed_posts current_item){
        if(mode){
            holder.circle_between_time_and_dev_in_card.setVisibility(View.VISIBLE);
            holder.text_view_saying_i_am_the_dev_of_the_app.setVisibility(View.VISIBLE);
            holder.view_at_the_top_of_the_dev_object.setVisibility(View.VISIBLE);
            holder.view_at_the_bottom_of_the_dev_object.setVisibility(View.VISIBLE);
            holder.view_at_the_start_of_the_dev_object.setVisibility(View.VISIBLE);
            holder.view_at_the_end_of_the_dev_object.setVisibility(View.VISIBLE);
            holder.view_behind_i_am_the_dev_of_the_text.setVisibility(View.VISIBLE);
        } else {
            holder.circle_between_time_and_dev_in_card.setVisibility(View.INVISIBLE);
            holder.text_view_saying_i_am_the_dev_of_the_app.setVisibility(View.INVISIBLE);
            holder.view_at_the_top_of_the_dev_object.setVisibility(View.INVISIBLE);
            holder.view_at_the_bottom_of_the_dev_object.setVisibility(View.INVISIBLE);
            holder.view_at_the_start_of_the_dev_object.setVisibility(View.INVISIBLE);
            holder.view_at_the_end_of_the_dev_object.setVisibility(View.INVISIBLE);
            holder.view_behind_i_am_the_dev_of_the_text.setVisibility(View.INVISIBLE);
        }
    }
    private void gift_button_listen(@NonNull final ExampleViewHolder holder, final Example_item_feed_posts current_item){
        holder.button_to_watch_gift_in_card_in_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event_manager_all_in_one.getInstance().record_fire_base_event(holder.constriant_inside_card_inside_post_feed.getContext(), Event_manager_all_in_one.Event_type_fire_base_record.post_gifted,false);
                gift_button_listen_listener.gift_was_clicked(holder.getAdapterPosition(),current_item.get_user_id(),current_item.getM_document_id(),-100,-100,current_item.getM_awards());
            }
        });
    }

    private void three_dot_listen(@NonNull final ExampleViewHolder holder, final Context context, final String document_id, final FirebaseFirestore firebaseFirestore, final String my_id, final Example_item_feed_posts current_item){
        holder.button_watch_three_dot_to_add_extra_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(holder.constriant_inside_card_inside_post_feed.getContext(), holder.button_watch_three_dot_to_add_extra_options);
                popupMenu.getMenuInflater().inflate(R.menu.menu_for_posts, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getOrder() == 0){
                            Alert_dialog_show alert_dialog_show = new Alert_dialog_show();
                            alert_dialog_show.show_alert_dialog(context,"Hide Post?","Are you sure you want to hide this post?");
                            alert_dialog_show.set_ok_button_listen(new Alert_dialog_show.ok_button_clicked() {
                                @Override
                                public void ok_button_clicked() {
                                    if (is_this_saved(context, document_id)) {
                                        remove_the_post(context,document_id);
                                    }
                                    if (is_this_your_post(context,document_id)) {
                                        remove_your_post(context,document_id);
                                    }
                                    Save_and_get.getInstance().save_this(context,document_id,"hide_posts",true);
                                    Toast.makeText(context,"Post hidden",Toast.LENGTH_SHORT).show();
                                    hide_post_listener.hide_post_clickled(holder.getAdapterPosition());
                                }
                            });
                        }  else if(menuItem.getOrder() == 1){
                            Alert_dialog_show alert_dialog_show = new Alert_dialog_show();
                            alert_dialog_show.show_alert_dialog(context,"Block User?","Are you sure you want to block this user?");
                            alert_dialog_show.set_ok_button_listen(new Alert_dialog_show.ok_button_clicked() {
                                @Override
                                public void ok_button_clicked() {
                                    if(current_item.get_user_id().equals(current_item.getM_my_user_id())){
                                        Toast.makeText(context,"You can't block yourself!",Toast.LENGTH_LONG).show();
                                    } else {
                                        if (is_this_saved(context, document_id)) {
                                            remove_the_post(context,document_id);
                                        }
                                        if (is_this_your_post(context,document_id)) {
                                            remove_your_post(context,document_id);
                                        }
                                        Save_and_get.getInstance().save_this(context,current_item.get_user_id(),"blocked_users",true);
                                        Toast.makeText(context,"User blocked",Toast.LENGTH_SHORT).show();
                                        hide_post_listener.hide_post_clickled(holder.getAdapterPosition());
                                    }
                                }
                            });
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    private boolean is_this_your_post(Context context, String document_id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("posted_posts", MODE_PRIVATE);
        String old = sharedPreferences.getString("posts", "");
        String[] big_split = old.split("big_divide");
        for (int i = 0; i < big_split.length; i++) {
            String[] small_split = big_split[i].split("small_split");
            if (small_split[5].equals(document_id)) {
                return true;
            }
        }
        return false;
    }

    private void remove_your_post(Context context, String document_id) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("posted_posts", MODE_PRIVATE);
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

    private void three_dot_listen_for_archieved(@NonNull final ExampleViewHolder holder, final Context context, final String document_id, final Example_item_feed_posts current_item){
        holder.button_watch_three_dot_to_add_extra_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(holder.constriant_inside_card_inside_post_feed.getContext(), holder.button_watch_three_dot_to_add_extra_options);
                popupMenu.getMenuInflater().inflate(R.menu.menu_for_posts, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getOrder() == 0){
                            Alert_dialog_show alert_dialog_show = new Alert_dialog_show();
                            alert_dialog_show.show_alert_dialog(context,"Hide Post?","Are you sure you want to hide this post?");
                            alert_dialog_show.set_ok_button_listen(new Alert_dialog_show.ok_button_clicked() {
                                @Override
                                public void ok_button_clicked() {
                                    if (is_this_saved(context, document_id)) {
                                        remove_the_post(context,document_id);
                                    }
                                    if (is_this_your_post(context,document_id)) {
                                        remove_your_post(context,document_id);
                                    }
                                    Save_and_get.getInstance().save_this(context,document_id,"hide_posts",true);
                                    Toast.makeText(context,"Post hidden",Toast.LENGTH_SHORT).show();
                                    hide_post_listener.hide_post_clickled(holder.getAdapterPosition());
                                }
                            });
                        }  else if(menuItem.getOrder() == 1){
                            Alert_dialog_show alert_dialog_show = new Alert_dialog_show();
                            alert_dialog_show.show_alert_dialog(context,"Block User?","Are you sure you want to block this user?");
                            alert_dialog_show.set_ok_button_listen(new Alert_dialog_show.ok_button_clicked() {
                                @Override
                                public void ok_button_clicked() {
                                    if(is_this_your_post(context,document_id)){
                                        Toast.makeText(context,"You can't block yourself!",Toast.LENGTH_LONG).show();
                                    } else {
                                        if (is_this_saved(context, document_id)) {
                                            remove_the_post(context,document_id);
                                        }
                                        if (is_this_your_post(context,document_id)) {
                                            remove_your_post(context,document_id);
                                        }
//                                        Save_and_get.getInstance().save_this(context,current_item.get_user_id(),"blocked_users",true);
                                        Toast.makeText(context,"User blocked",Toast.LENGTH_SHORT).show();
                                        hide_post_listener.hide_post_clickled(holder.getAdapterPosition());
                                    }
                                }
                            });
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }
}