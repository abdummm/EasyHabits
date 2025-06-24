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
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static android.content.Context.MODE_PRIVATE;

public class Posts_fragment extends Fragment {
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private String sort_type = "hot";
    private String previous_sort_type = "hot";
    private RecyclerView recyclerView;
    private Adapter_for_feed_posts adapter;
    private LinearLayoutManager linearLayoutManager;
    private final ArrayList<Example_item_feed_posts> example_list = new ArrayList<>();
    private RecyclerView recyclerView_for_your_post;
    private Adapter_for_feed_posts adapter_for_your_post;
    private LinearLayoutManager linearLayoutManager_for_your_post;
    private final ArrayList<Example_item_feed_posts> example_list_for_your_post = new ArrayList<>();
    private boolean did_you_set_up_your_post = false;
    private DocumentSnapshot documentSnapshot_for_feed = null;
    private ArrayList<Integer> list_of_seen;
    private RecyclerView recylce_view_to_show_saved_posts;
    private Adapter_for_feed_posts adapter_for_saved_post;
    private LinearLayoutManager linearLayoutManager_for_saved_post;
    private final ArrayList<Example_item_feed_posts> example_list_for_saved_post = new ArrayList<>();
    private boolean did_you_sit_up_saved_post = false;
    private ArrayList<Integer> list_of_your_posts;
    private ArrayList<Integer> list_of_saved_posts;
    private int index_to_start_your_posts = 0;
    private int your_post_length;
    private int index_to_start_saved_posts = 0;
    private int saved_posts_length;
    private final int firebase_loading_limit = 15;
    private final int firebase_loading_time = 3000;
    private int which_screen_is_this_on = 1;
    private ArrayList<String> list_of_all_seen_posts_to_pass_for_the_adapter;
    private int which_saved_post_comment_is_this_on = 0;
    private boolean did_you_setup_your_comments = false;
    private RecyclerView recylce_view_to_show_your_comments;
    private LinearLayoutManager linearLayoutManager_for_comments;
    private Adapter_for_comment_reply adapter_for_comment_reply;
    private final ArrayList<Example_item_for_comments_replies> example_list_for_your_comment_reply = new ArrayList<>();
    private int which_saved_post_comment_is_this_on_real = 0;
    private boolean did_you_setup_saved_comments = false;
    private RecyclerView recylce_view_to_show_saved_comments;
    private LinearLayoutManager linearLayoutManager_for_saved_comments;
    private Adapter_for_comment_reply adapter_for_saved_comment_reply;
    private final ArrayList<Example_item_for_comments_replies> example_list_for_saved_comment_reply = new ArrayList<>();
    private boolean am_i_signed_in = false;
    private ArrayList<String> blocked_user_ids = new ArrayList<>();
    private ArrayList<String> hidden_document_ids = new ArrayList<>();


    public Posts_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build();
        firebaseFirestore.setFirestoreSettings(settings);
        set_up_the_array_list_for_seen_posts();
        add_information_to_array_list();
        sign_in_anonymous();
        three_buttons_in_top_listen();
        floating_button_listen_and_long_listen();
        watch_the_comments_psots_listen();
        saved_post_and_comments_button_listen();
        // there is 3 functions called in sign_in_anonymous
    }

    private void three_buttons_in_top_listen() {
        if (getView() != null) {
            Button button_to_go_to_all_posts = getView().findViewById(R.id.button_to_go_to_all_posts);
            Button button_to_go_to_your_posts = getView().findViewById(R.id.button_to_go_to_your_posts);
            Button button_to_go_to_saved_posts = getView().findViewById(R.id.button_to_go_to_saved_posts);
            button_to_go_to_all_posts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (which_screen_is_this_on != 1) {
                        which_screen_is_this_on = 1;
                        select_the_current_section_user_at(1);
                        should_is_show_button();
                    }
                }
            });
            button_to_go_to_your_posts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (which_screen_is_this_on != 2) {
                        post_is_clicked();
                    }
                }
            });
            button_to_go_to_saved_posts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (which_screen_is_this_on != 3) {
                        saved_posts_is_clicked();
                    }
                }
            });
        }
    }

    private void select_the_current_section_user_at(int which) {
        if (getView() != null) {
            View white_view_under_first_button_feed = getView().findViewById(R.id.white_view_under_first_button_feed);
            View white_view_under_second_button_your_posts = getView().findViewById(R.id.white_view_under_second_button_your_posts);
            View white_view_under_third_button_saved_posts = getView().findViewById(R.id.white_view_under_third_button_saved_posts);
            ConstraintLayout view_the_posts_in_posts = getView().findViewById(R.id.view_the_posts_in_posts);
            ConstraintLayout view_the_your_posts_in_posts = getView().findViewById(R.id.view_the_your_posts_in_posts);
            ConstraintLayout view_the_saved_posts_in_posts = getView().findViewById(R.id.view_the_saved_posts_in_posts);
            if (which == 1) {
                white_view_under_first_button_feed.setVisibility(View.VISIBLE);
                white_view_under_second_button_your_posts.setVisibility(View.INVISIBLE);
                white_view_under_third_button_saved_posts.setVisibility(View.INVISIBLE);

                view_the_posts_in_posts.setVisibility(View.VISIBLE);
                view_the_your_posts_in_posts.setVisibility(View.INVISIBLE);
                view_the_saved_posts_in_posts.setVisibility(View.INVISIBLE);
            } else if (which == 2) {
                white_view_under_first_button_feed.setVisibility(View.INVISIBLE);
                white_view_under_second_button_your_posts.setVisibility(View.VISIBLE);
                white_view_under_third_button_saved_posts.setVisibility(View.INVISIBLE);

                view_the_posts_in_posts.setVisibility(View.INVISIBLE);
                view_the_your_posts_in_posts.setVisibility(View.VISIBLE);
                view_the_saved_posts_in_posts.setVisibility(View.INVISIBLE);
            } else if (which == 3) {
                white_view_under_first_button_feed.setVisibility(View.INVISIBLE);
                white_view_under_second_button_your_posts.setVisibility(View.INVISIBLE);
                white_view_under_third_button_saved_posts.setVisibility(View.VISIBLE);

                view_the_posts_in_posts.setVisibility(View.INVISIBLE);
                view_the_your_posts_in_posts.setVisibility(View.INVISIBLE);
                view_the_saved_posts_in_posts.setVisibility(View.VISIBLE);
            }
        }
    }

    private void floating_button_listen_and_long_listen() {
        if (getActivity() != null) {
            final FloatingActionButton flaoting_button_to_write_a_post_in_posts = getActivity().findViewById(R.id.flaoting_button_to_write_a_post_in_posts);
            flaoting_button_to_write_a_post_in_posts.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (getActivity() != null) {
                        vibrate_the_device();
                        Toast toast = Toast.makeText(getActivity(), "Write a post", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    return true;
                }
            });
            flaoting_button_to_write_a_post_in_posts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*
                    Am_i_paid am_i_paid = new Am_i_paid(getContext());
                    if (am_i_paid.did_user_pay()) {
                        if (how_is_user_logged_in().equals("google")) {
                            post_a_post new_fragment = new post_a_post();
                            Posts_fragment old_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
                            post_a_post check_fragment = (post_a_post) getActivity().getSupportFragmentManager().findFragmentByTag("write a post");
                            if (check_fragment != null) {
                                getActivity().getSupportFragmentManager().beginTransaction().remove(check_fragment).commit();
                            }
                            if (old_fragment != null) {
                                getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, new_fragment, "write a post").show(new_fragment).commit();
                            }
                        } else {
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Sign in with google")
                                    .setMessage("You have to sign in with google in order to be able to write a post")

                                    // Specifying a listener allows you to take an action before dismissing the dialog.
                                    // The dialog is automatically dismissed when a dialog button is clicked.
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            Bottom_sheet_to_sing_in_to_write_posts bottom_sheet_to_sing_in_to_write_posts = new Bottom_sheet_to_sing_in_to_write_posts();
                                            bottom_sheet_to_sing_in_to_write_posts.setTargetFragment(Posts_fragment.this, 254);
                                            bottom_sheet_to_sing_in_to_write_posts.show(getActivity().getSupportFragmentManager(), "tag");
                                        }
                                    }).setNegativeButton("cancel", null)
                                    .show();
                        }
                    } else {
                        Buy_premuim buy_premuim = new Buy_premuim("post online",true,"posts");
                        Posts_fragment old_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
                        if (old_fragment != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
                        }
                    }
                     */
                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.post_written_clicked,false);
                    post_a_post new_fragment = new post_a_post();
                    Posts_fragment old_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
                    post_a_post check_fragment = (post_a_post) getActivity().getSupportFragmentManager().findFragmentByTag("write a post");
                    if (check_fragment != null) {
                        getActivity().getSupportFragmentManager().beginTransaction().remove(check_fragment).commit();
                    }
                    if (old_fragment != null) {
                        getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, new_fragment, "write a post").show(new_fragment).commit();
                    }
                }
            });
        }
    }

    private void vibrate_the_device() {
        if (getActivity() != null) {
            Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.EFFECT_TICK));
                } else {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                }
            } else {
                //deprecated in API 26
                v.vibrate(500);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 254) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                String text = bundle.getString("sign_in", "anonymous");
                if (text.equals("google")) {
                    reset_the_main();
                    if (which_screen_is_this_on == 3) {
                        example_list_for_saved_post.clear();
                        list_of_saved_posts.clear();
                        reset_saved_posts();
                        adapter_for_saved_post.notifyDataSetChanged();
                        add_stuff_to_saved_posts();
                    }
                }
            }
        } else if (requestCode == 1235 && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                sort_type = bundle.getString("sort", "hot");
                if (previous_sort_type.equals(sort_type)) {
                    Toast.makeText(getActivity(), "Already selected", Toast.LENGTH_SHORT).show();
                } else {
                    list_of_seen.clear();
                    documentSnapshot_for_feed = null;
                    clear_recylce_view();
                    load_into_recylce_view(firebase_loading_limit, false, true);
                    Button button_at_post_to_choose_the_ranking = getView().findViewById(R.id.button_at_post_to_choose_the_ranking);
                    if (sort_type.equals("hot")) {
                        button_at_post_to_choose_the_ranking.setText("HOT");
                        button_at_post_to_choose_the_ranking.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_local_fire_department_24, 0, R.drawable.round_arrow_drop_down_24, 0);
                    } else if (sort_type.equals("new")) {
                        button_at_post_to_choose_the_ranking.setText("NEW");
                        button_at_post_to_choose_the_ranking.setCompoundDrawablesWithIntrinsicBounds(R.drawable.round_trending_up_24, 0, R.drawable.round_arrow_drop_down_24, 0);
                    } else if (sort_type.equals("top")) {
                        button_at_post_to_choose_the_ranking.setText("TOP");
                        button_at_post_to_choose_the_ranking.setCompoundDrawablesWithIntrinsicBounds(R.drawable.round_emoji_events_24, 0, R.drawable.round_arrow_drop_down_24, 0);

                    }
                    previous_sort_type = sort_type;
                }
            }
        }
    }

    private String how_is_user_logged_in() {
        try {
            if ((FirebaseAuth.getInstance().getCurrentUser() != null && FirebaseAuth.getInstance().getCurrentUser().getIdToken(false).getResult().getSignInProvider().equals("google.com"))) {
                return "google";
            } else {
                return "anonymous";
            }
        } catch (Exception exception) {
            return "anonymous";
        }
    }

    private void sign_in_anonymous() {
        if (getActivity() != null) {
            if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                /*FirebaseAuth.getInstance().signInAnonymously()
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    set_up_the_recylce_view();
                                    load_into_recylce_view(firebase_loading_limit, false, true);
                                    sort_button_listen();
                                    remove_old_seen_posts();
                                } else {
                                    Toast.makeText(getActivity(), "Can't sign in. Please try again", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });*/
            } else {
                set_up_the_recylce_view();
                load_into_recylce_view(firebase_loading_limit, false, true);
                sort_button_listen();
                remove_old_seen_posts();
            }
        }
    }

    private void load_into_recylce_view(int what_is_the_limit, final boolean is_this_loading, final boolean should_i_run_again) {
        if (getActivity() != null && getView() != null) {
            if (sort_type.equals("hot")) {
                if (documentSnapshot_for_feed == null) {
                    firebaseFirestore.collection("posts").orderBy("score", Query.Direction.DESCENDING).limit(what_is_the_limit).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<DocumentSnapshot> snapshots = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot snapshot : snapshots) {
                                if (should_i_show_this_post((String) snapshot.get("userid"), snapshot.getId())) {
                                    String title = snapshot.getString("title");
                                    String body = snapshot.getString("body");
//                                String categorry = (String) snapshot.get("category");
                                    Date time = snapshot.getTimestamp("time_posted").toDate();
//                                long streak = (long) snapshot.get("streak");
                                    String span = snapshot.getString("span");
                                    String user_id = (String) snapshot.get("userid");
                                    ArrayList<HashMap<String, Object>> comments = (ArrayList<HashMap<String, Object>>) snapshot.get("comments");
                                    ArrayList<Long> awards = (ArrayList<Long>) snapshot.get("awards");
                                    String document_id = snapshot.getId();
                                    ArrayList<String> up_vote_list = (ArrayList<String>) snapshot.get("up_vote_list");
                                    ArrayList<String> down_vote_list = (ArrayList<String>) snapshot.get("down_vote_list");
                                    ArrayList<String> reports = (ArrayList<String>) snapshot.get("reports");
                                    boolean is_this_dev = (boolean) snapshot.get("dev");
                                    Map<String, Timestamp> map_with_time_for_comments = (Map<String, Timestamp>) snapshot.get("map_with_time");
                                    String name_of_poster = snapshot.getString("name");
                                    example_list.add(new Example_item_feed_posts(title, body, (long) -1, false, "", time, user_id, span, comments, awards, document_id, true, up_vote_list, down_vote_list, FirebaseAuth.getInstance().getCurrentUser(), is_this_loading, firebaseFirestore, reports, list_of_all_seen_posts_to_pass_for_the_adapter, is_this_dev, map_with_time_for_comments, name_of_poster));
                                    adapter.notifyDataSetChanged();
                                    documentSnapshot_for_feed = snapshot;
                                }
                            }
                            if (should_i_run_again) {
                                load_into_recylce_view(1, true, false);
                            }
                        }
                    });
                } else {
                    firebaseFirestore.collection("posts").orderBy("score", Query.Direction.DESCENDING).startAfter(documentSnapshot_for_feed).limit(what_is_the_limit).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<DocumentSnapshot> snapshots = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot snapshot : snapshots) {
                                if (should_i_show_this_post((String) snapshot.get("userid"), snapshot.getId())) {
                                    String title = snapshot.getString("title");
                                    String body = snapshot.getString("body");
//                                String categorry = (String) snapshot.get("category");
                                    Date time = snapshot.getTimestamp("time_posted").toDate();
//                                long streak = (long) snapshot.get("streak");
                                    String span = snapshot.getString("span");
                                    String user_id = (String) snapshot.get("userid");
                                    ArrayList<HashMap<String, Object>> comments = (ArrayList<HashMap<String, Object>>) snapshot.get("comments");
                                    ArrayList<Long> awards = (ArrayList<Long>) snapshot.get("awards");
                                    String document_id = snapshot.getId();
                                    ArrayList<String> up_vote_list = (ArrayList<String>) snapshot.get("up_vote_list");
                                    ArrayList<String> down_vote_list = (ArrayList<String>) snapshot.get("down_vote_list");
                                    ArrayList<String> reports = (ArrayList<String>) snapshot.get("reports");
                                    boolean is_this_dev = (boolean) snapshot.get("dev");
                                    Map<String, Timestamp> map_with_time_for_comments = (Map<String, Timestamp>) snapshot.get("map_with_time");
                                    String name_of_poster = snapshot.getString("name");
                                    example_list.add(new Example_item_feed_posts(title, body, (long) -1, false, "", time, user_id, span, comments, awards, document_id, true, up_vote_list, down_vote_list, FirebaseAuth.getInstance().getCurrentUser(), is_this_loading, firebaseFirestore, reports, list_of_all_seen_posts_to_pass_for_the_adapter, is_this_dev, map_with_time_for_comments, name_of_poster));
                                    adapter.notifyDataSetChanged();
                                    documentSnapshot_for_feed = snapshot;
                                }
                            }
                            if (should_i_run_again) {
                                load_into_recylce_view(1, true, false);
                            }
                        }
                    });
                }
            } else if (sort_type.equals("new")) {
                if (documentSnapshot_for_feed == null) {
                    firebaseFirestore.collection("posts").orderBy("time_posted", Query.Direction.DESCENDING).limit(what_is_the_limit).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<DocumentSnapshot> snapshots = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot snapshot : snapshots) {
                                if (should_i_show_this_post((String) snapshot.get("userid"), snapshot.getId())) {
                                    String title = snapshot.getString("title");
                                    String body = snapshot.getString("body");
//                                String categorry = (String) snapshot.get("category");
                                    Date time = snapshot.getTimestamp("time_posted").toDate();
//                                long streak = (long) snapshot.get("streak");
                                    String span = snapshot.getString("span");
                                    String user_id = (String) snapshot.get("userid");
                                    ArrayList<HashMap<String, Object>> comments = (ArrayList<HashMap<String, Object>>) snapshot.get("comments");
                                    ArrayList<Long> awards = (ArrayList<Long>) snapshot.get("awards");
                                    String document_id = snapshot.getId();
                                    ArrayList<String> up_vote_list = (ArrayList<String>) snapshot.get("up_vote_list");
                                    ArrayList<String> down_vote_list = (ArrayList<String>) snapshot.get("down_vote_list");
                                    ArrayList<String> reports = (ArrayList<String>) snapshot.get("reports");
                                    boolean is_this_dev = (boolean) snapshot.get("dev");
                                    Map<String, Timestamp> map_with_time_for_comments = (Map<String, Timestamp>) snapshot.get("map_with_time");
                                    String name_of_poster = snapshot.getString("name");
                                    example_list.add(new Example_item_feed_posts(title, body, (long) -1, false, "", time, user_id, span, comments, awards, document_id, true, up_vote_list, down_vote_list, FirebaseAuth.getInstance().getCurrentUser(), is_this_loading, firebaseFirestore, reports, list_of_all_seen_posts_to_pass_for_the_adapter, is_this_dev, map_with_time_for_comments, name_of_poster));
                                    adapter.notifyDataSetChanged();
                                    documentSnapshot_for_feed = snapshot;
                                }
                            }
                            if (should_i_run_again) {
                                load_into_recylce_view(1, true, false);
                            }
                        }
                    });
                } else {
                    firebaseFirestore.collection("posts").orderBy("time_posted", Query.Direction.DESCENDING).startAfter(documentSnapshot_for_feed).limit(what_is_the_limit).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<DocumentSnapshot> snapshots = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot snapshot : snapshots) {
                                if (should_i_show_this_post((String) snapshot.get("userid"), snapshot.getId())) {
                                    String title = snapshot.getString("title");
                                    String body = snapshot.getString("body");
//                                String categorry = (String) snapshot.get("category");
                                    Date time = snapshot.getTimestamp("time_posted").toDate();
//                                long streak = (long) snapshot.get("streak");
                                    String span = snapshot.getString("span");
                                    String user_id = (String) snapshot.get("userid");
                                    ArrayList<HashMap<String, Object>> comments = (ArrayList<HashMap<String, Object>>) snapshot.get("comments");
                                    ArrayList<Long> awards = (ArrayList<Long>) snapshot.get("awards");
                                    ArrayList<String> up_vote_list = (ArrayList<String>) snapshot.get("up_vote_list");
                                    ArrayList<String> down_vote_list = (ArrayList<String>) snapshot.get("down_vote_list");
                                    String document_id = snapshot.getId();
                                    ArrayList<String> reports = (ArrayList<String>) snapshot.get("reports");
                                    boolean is_this_dev = (boolean) snapshot.get("dev");
                                    Map<String, Timestamp> map_with_time_for_comments = (Map<String, Timestamp>) snapshot.get("map_with_time");
                                    String name_of_poster = snapshot.getString("name");
                                    example_list.add(new Example_item_feed_posts(title, body, (long) -1, false, "", time, user_id, span, comments, awards, document_id, true, up_vote_list, down_vote_list, FirebaseAuth.getInstance().getCurrentUser(), is_this_loading, firebaseFirestore, reports, list_of_all_seen_posts_to_pass_for_the_adapter, is_this_dev, map_with_time_for_comments, name_of_poster));
                                    adapter.notifyDataSetChanged();
                                    documentSnapshot_for_feed = snapshot;
                                }
                            }
                            if (should_i_run_again) {
                                load_into_recylce_view(1, true, false);
                            }
                        }
                    });
                }
            } else {
                if (documentSnapshot_for_feed == null) {
                    firebaseFirestore.collection("posts").orderBy("likes", Query.Direction.DESCENDING).limit(what_is_the_limit).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<DocumentSnapshot> snapshots = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot snapshot : snapshots) {
                                if (should_i_show_this_post((String) snapshot.get("userid"), snapshot.getId())) {
                                    String title = snapshot.getString("title");
                                    String body = snapshot.getString("body");
//                                String categorry = (String) snapshot.get("category");
                                    Date time = snapshot.getTimestamp("time_posted").toDate();
//                                long streak = (long) snapshot.get("streak");
                                    String span = snapshot.getString("span");
                                    String user_id = (String) snapshot.get("userid");
                                    ArrayList<HashMap<String, Object>> comments = (ArrayList<HashMap<String, Object>>) snapshot.get("comments");
                                    ArrayList<Long> awards = (ArrayList<Long>) snapshot.get("awards");
                                    ArrayList<String> up_vote_list = (ArrayList<String>) snapshot.get("up_vote_list");
                                    ArrayList<String> down_vote_list = (ArrayList<String>) snapshot.get("down_vote_list");
                                    String document_id = snapshot.getId();
                                    ArrayList<String> reports = (ArrayList<String>) snapshot.get("reports");
                                    boolean is_this_dev = (boolean) snapshot.get("dev");
                                    Map<String, Timestamp> map_with_time_for_comments = (Map<String, Timestamp>) snapshot.get("map_with_time");
                                    String name_of_poster = snapshot.getString("name");
                                    example_list.add(new Example_item_feed_posts(title, body, (long) -1, false, "", time, user_id, span, comments, awards, document_id, true, up_vote_list, down_vote_list, FirebaseAuth.getInstance().getCurrentUser(), is_this_loading, firebaseFirestore, reports, list_of_all_seen_posts_to_pass_for_the_adapter, is_this_dev, map_with_time_for_comments, name_of_poster));
                                    adapter.notifyDataSetChanged();
                                    documentSnapshot_for_feed = snapshot;
                                }
                            }
                            if (should_i_run_again) {
                                load_into_recylce_view(1, true, false);
                            }
                        }
                    });
                } else {
                    firebaseFirestore.collection("posts").orderBy("likes", Query.Direction.DESCENDING).startAfter(documentSnapshot_for_feed).limit(what_is_the_limit).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<DocumentSnapshot> snapshots = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot snapshot : snapshots) {
                                if (should_i_show_this_post((String) snapshot.get("userid"), snapshot.getId())) {
                                    String title = snapshot.getString("title");
                                    String body = snapshot.getString("body");
//                                String categorry = (String) snapshot.get("category");
                                    Date time = snapshot.getTimestamp("time_posted").toDate();
//                                long streak = (long) snapshot.get("streak");
                                    String span = snapshot.getString("span");
                                    String user_id = (String) snapshot.get("userid");
                                    ArrayList<HashMap<String, Object>> comments = (ArrayList<HashMap<String, Object>>) snapshot.get("comments");
                                    ArrayList<Long> awards = (ArrayList<Long>) snapshot.get("awards");
                                    ArrayList<String> up_vote_list = (ArrayList<String>) snapshot.get("up_vote_list");
                                    ArrayList<String> down_vote_list = (ArrayList<String>) snapshot.get("down_vote_list");
                                    String document_id = snapshot.getId();
                                    ArrayList<String> reports = (ArrayList<String>) snapshot.get("reports");
                                    boolean is_this_dev = (boolean) snapshot.get("dev");
                                    Map<String, Timestamp> map_with_time_for_comments = (Map<String, Timestamp>) snapshot.get("map_with_time");
                                    String name_of_poster = snapshot.getString("name");
                                    example_list.add(new Example_item_feed_posts(title, body, (long) -1, false, "", time, user_id, span, comments, awards, document_id, true, up_vote_list, down_vote_list, FirebaseAuth.getInstance().getCurrentUser(), is_this_loading, firebaseFirestore, reports, list_of_all_seen_posts_to_pass_for_the_adapter, is_this_dev, map_with_time_for_comments, name_of_poster));
                                    adapter.notifyDataSetChanged();
                                    documentSnapshot_for_feed = snapshot;
                                }
                            }
                            if (should_i_run_again) {
                                load_into_recylce_view(1, true, false);
                            }
                        }
                    });
                }
            }
        }
    }

    private void sort_button_listen() {
        if (getView() != null && getActivity() != null) {
            Button button_at_post_to_choose_the_ranking = getView().findViewById(R.id.button_at_post_to_choose_the_ranking);
            button_at_post_to_choose_the_ranking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bottom_sheet_choose_sort_method bottom_sheet_choose_sort_method = new Bottom_sheet_choose_sort_method();
                    bottom_sheet_choose_sort_method.setTargetFragment(Posts_fragment.this, 1235);
                    bottom_sheet_choose_sort_method.show(getActivity().getSupportFragmentManager(), "post");
                }
            });
        }
    }

    private void clear_recylce_view() {
        example_list.clear();
        adapter.notifyDataSetChanged();
    }

    private void set_up_the_recylce_view() {
        if (getView() != null && getActivity() != null) {
            list_of_seen = new ArrayList<>();
            recyclerView = getView().findViewById(R.id.recylce_view_to_show_all_posts);
            linearLayoutManager = new LinearLayoutManager(getActivity());
            adapter = new Adapter_for_feed_posts(example_list);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setHasFixedSize(false);
            adapter.set_on_item_click_listen(new Adapter_for_feed_posts.up_vote_clicked_not_signed_in() {
                @Override
                public void up_vote_click_no_sign_in(String mode) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Sign in with google")
                            .setMessage("In order to prevent malicious ".concat(mode).concat("s, please sign in with google to ".concat(mode)))
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Bottom_sheet_to_sing_in_to_write_posts bottom_sheet_to_sing_in_to_write_posts = new Bottom_sheet_to_sing_in_to_write_posts();
                                    bottom_sheet_to_sing_in_to_write_posts.setTargetFragment(Posts_fragment.this, 254);
                                    bottom_sheet_to_sing_in_to_write_posts.show(getActivity().getSupportFragmentManager(), "tag");
                                }
                            })
                            .setNegativeButton("cancel", null)
                            .show();
                }
            });
            adapter.set_image_click_listen(new Adapter_for_feed_posts.image_clicked_interface() {
                @Override
                public void image_clicked(String base64) {
                    show_image_fragment(base64);
                }
            });
            adapter.set_data_load_listen(new Adapter_for_feed_posts.data_is_loaded() {
                @Override
                public void data_is_done_loading_from_adapter() {
                    load_into_recylce_view(firebase_loading_limit, false, true);
                }
            });
            adapter.set_item_click_listen(new Adapter_for_feed_posts.post_is_clicked() {
                @Override
                public void post_just_got_clicked(int position, String m_title, String m_body, String m_category, Date m_time, Long m_streak, String m_span, boolean m_image, String m_user_id, ArrayList<HashMap<String, Object>> m_comments, ArrayList<Long> m_awards, boolean is_this_upvote, boolean is_this_down_vote, String m_document_id, boolean m_is_this_from_fire_base, ArrayList<String> m_upvotes, ArrayList<String> m_downvotes, boolean post_saved, int plus_or_minus, boolean m_is_this_laoding, boolean i_already_loaded, boolean upvote_or_down_vote_was_clicked, FirebaseFirestore m_firebaseFirestore, FirebaseUser m_firebaseUser, boolean was_this_reported, ArrayList<String> m_list_of_reports, boolean am_i_loading_up_voote_down_vote_from_fire_base, boolean did_i_see_this_tem, ArrayList<String> m_list_of_seen_posts, boolean m_is_post_by_dev, String name) {
                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.post_opened,false);
                    Show_full_post show_full_post = new Show_full_post();
                    show_full_post.set_hide_button_listen(new Show_full_post.hide_button_clicked() {
                        @Override
                        public void hide_button_clicked() {
                            example_list.remove(position);
                            adapter.notifyItemRemoved(position);
                            adapter.notifyItemRangeChanged(position, example_list.size());
                            add_information_to_array_list();
                        }
                    });
                    Bundle bundle = new Bundle();
                    bundle.putInt("position", position);
                    bundle.putString("title", m_title);
                    bundle.putString("body", m_body);
                    //bundle.putString("category", m_category);
                    bundle.putSerializable("time", m_time);
                    //bundle.putInt("streak", m_streak.intValue());
                    bundle.putString("span", m_span);
                    bundle.putBoolean("image", m_image);
                    bundle.putString("user_id", m_user_id);
                    bundle.putSerializable("comments", m_comments);
                    bundle.putSerializable("awards", m_awards);
                    bundle.putBoolean("is_this_upvoted", is_this_upvote);
                    bundle.putBoolean("is_this_downvote", is_this_down_vote);
                    bundle.putString("document_id", m_document_id);
                    bundle.putBoolean("is_this_from_firebase", m_is_this_from_fire_base);
                    bundle.putSerializable("list_of_upvotes", m_upvotes);
                    bundle.putSerializable("list_of_downvotes", m_downvotes);
                    bundle.putBoolean("post_saved", post_saved);
                    bundle.putInt("plus_or_minus", plus_or_minus);
                    bundle.putBoolean("is_this_loading", m_is_this_laoding);
                    bundle.putBoolean("i_already_loaded", i_already_loaded);
                    bundle.putBoolean("was_upvote_down_vote_clicked", upvote_or_down_vote_was_clicked);
                    bundle.putBoolean("was_this_reported", was_this_reported);
                    bundle.putSerializable("list_of_reports", m_list_of_reports);
                    bundle.putBoolean("am_i_loading_up_vote_down_vote", am_i_loading_up_voote_down_vote_from_fire_base);
                    bundle.putBoolean("did_i_see_this_item", did_i_see_this_tem);
                    bundle.putSerializable("list_of_seen_posts", m_list_of_seen_posts);
                    bundle.putBoolean("m_is_post_by_dev", m_is_post_by_dev);
                    bundle.putString("which_post_called_me", "feed");
                    bundle.putString("name_of_posted", name);
                    show_full_post.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, show_full_post, "show full post").hide(Posts_fragment.this).commit();
                }
            });
            adapter.set_share_clicked_listen(new Adapter_for_feed_posts.share_button_was_clicked() {
                @Override
                public void share_just_got_clciked(String title, String body) {
                    share_screen_shot(title, body);
                }
            });
            adapter.set_up_gift_listen(new Adapter_for_feed_posts.gift_button_listen() {
                @Override
                public void gift_was_clicked(int index, String user_id, String document_id, int comment_position, int reply_position, ArrayList<Long> awards) {
                    if (comment_position == -100 && reply_position == -100) {
                        Bottom_sheet_to_give_coins bottom_sheet_to_give_coins = new Bottom_sheet_to_give_coins(index, user_id, document_id, awards, "posts");
                        bottom_sheet_to_give_coins.set_update_gift(new Bottom_sheet_to_give_coins.update_gift_listen() {
                            @Override
                            public void gift_was_clicked(int index, ArrayList<Long> arrayList_of_awards) {
                                example_list.set(index, new Example_item_feed_posts(example_list.get(index).getM_title(), example_list.get(index).getM_body(), "", example_list.get(index).getM_time(), example_list.get(index).getM_streak(), example_list.get(index).getM_span(), false, example_list.get(index).getM_user_id(), example_list.get(index).getM_comments(), arrayList_of_awards, example_list.get(index).get_is_this_upvote(), example_list.get(index).get_the_down_vote(), example_list.get(index).getM_document_id(), example_list.get(index).get_is_this_from_firebase(), example_list.get(index).get_upvote_list(), example_list.get(index).getM_downvotes(), example_list.get(index).isPost_saved(), example_list.get(index).getPlus_or_minus(), example_list.get(index).isM_is_this_laoding(), example_list.get(index).get_i_already_laoded(), example_list.get(index).isUpvote_or_down_vote_was_clicked(), example_list.get(index).getM_firebaseFirestore(), example_list.get(index).getM_firebaseUser(), example_list.get(index).isWas_this_reported(), example_list.get(index).getM_list_of_reports(), example_list.get(index).isAm_i_loading_up_voote_down_vote_from_fire_base(), example_list.get(index).isDid_i_see_this_tem(), example_list.get(index).getM_list_of_seen_posts(), example_list.get(index).isM_is_post_by_dev(), example_list.get(index).getName_of_the_poster()));
                                adapter.notifyDataSetChanged();
                            }
                        });
                        bottom_sheet_to_give_coins.show(getParentFragmentManager(), "");
                    } else if (reply_position == -100) {

                    } else {

                    }
                }
            });
            adapter.set_up_save_only_for_pro_listen(new Adapter_for_feed_posts.save_only_for_pro() {
                @Override
                public void save_only_for_pro() {
                    Posts_fragment old_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
                    Buy_premuim buy_premuim = new Buy_premuim("save posts", true, old_fragment);
                    if (old_fragment != null) {
                        getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
                    }
                }
            });
            adapter.set_up_hide_post(new Adapter_for_feed_posts.hide_post_clickled() {
                @Override
                public void hide_post_clickled(int position) {
                    example_list.remove(position);
                    adapter.notifyItemRemoved(position);
                    adapter.notifyItemRangeChanged(position, example_list.size());
                    add_information_to_array_list();
                }
            });
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                    if (position < 5) {
                        make_the_top_and_botton_go(1); // visible
                    } else {
                        make_the_top_and_botton_go(0); // not visible
                    }
                }
            });
        }
    }

    private void set_up_the_posts_saved_posts() { // your post not saved
        if (getView() != null && getActivity() != null) {
            reset_your_posts();
            list_of_your_posts = new ArrayList<>();
            recyclerView_for_your_post = getView().findViewById(R.id.recylce_view_to_show_your_posts);
            linearLayoutManager_for_your_post = new LinearLayoutManager(getActivity());
            adapter_for_your_post = new Adapter_for_feed_posts(example_list_for_your_post);
            recyclerView_for_your_post.setLayoutManager(linearLayoutManager_for_your_post);
            recyclerView_for_your_post.setAdapter(adapter_for_your_post);
            recyclerView_for_your_post.setHasFixedSize(false);
            recyclerView_for_your_post.setNestedScrollingEnabled(false);
            adapter_for_your_post.set_on_item_click_listen(new Adapter_for_feed_posts.up_vote_clicked_not_signed_in() {
                @Override
                public void up_vote_click_no_sign_in(String mode) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Sign in with google")
                            .setMessage("In order to prevent malicious ".concat(mode).concat("s, please sign in with google to ".concat(mode)))
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Bottom_sheet_to_sing_in_to_write_posts bottom_sheet_to_sing_in_to_write_posts = new Bottom_sheet_to_sing_in_to_write_posts();
                                    bottom_sheet_to_sing_in_to_write_posts.setTargetFragment(Posts_fragment.this, 254);
                                    bottom_sheet_to_sing_in_to_write_posts.show(getActivity().getSupportFragmentManager(), "tag");
                                }
                            })
                            .setNegativeButton("cancel", null)
                            .show();
                }
            });
            adapter_for_your_post.set_image_click_listen(new Adapter_for_feed_posts.image_clicked_interface() {
                @Override
                public void image_clicked(String base64) {
                    show_image_fragment(base64);
                }
            });
            adapter_for_your_post.set_data_load_listen(new Adapter_for_feed_posts.data_is_loaded() {
                @Override
                public void data_is_done_loading_from_adapter() {
                    add_stuff_to_your_post_recylce();
                }
            });
            adapter_for_your_post.set_item_click_listen(new Adapter_for_feed_posts.post_is_clicked() {
                @Override
                public void post_just_got_clicked(int position, String m_title, String m_body, String m_category, Date m_time, Long m_streak, String m_span, boolean m_image, String m_user_id, ArrayList<HashMap<String, Object>> m_comments, ArrayList<Long> m_awards, boolean is_this_upvote, boolean is_this_down_vote, String m_document_id, boolean m_is_this_from_fire_base, ArrayList<String> m_upvotes, ArrayList<String> m_downvotes, boolean post_saved, int plus_or_minus, boolean m_is_this_laoding, boolean i_already_loaded, boolean upvote_or_down_vote_was_clicked, FirebaseFirestore m_firebaseFirestore, FirebaseUser m_firebaseUser, boolean was_this_reported, ArrayList<String> m_list_of_reports, boolean am_i_loading_up_voote_down_vote_from_fire_base, boolean did_i_see_this_tem, ArrayList<String> m_list_of_seen_posts, boolean m_is_post_by_dev, String name) {
                    if (getActivity() != null) {
                        Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.post_opened,false);
                        Show_full_post show_full_post = new Show_full_post();
                        show_full_post.set_hide_button_listen(new Show_full_post.hide_button_clicked() {
                            @Override
                            public void hide_button_clicked() {
                                example_list_for_your_post.remove(position);
                                adapter_for_your_post.notifyItemRemoved(position);
                                adapter_for_your_post.notifyItemRangeChanged(position, example_list_for_your_post.size());
                                add_information_to_array_list();
                                hide_your_posts_if_empty();
                            }
                        });
                        Bundle bundle = new Bundle();
                        bundle.putInt("position", position);
                        bundle.putString("title", m_title);
                        bundle.putString("body", m_body);
//                        bundle.putString("category", m_category);
                        bundle.putSerializable("time", m_time);
//                        bundle.putInt("streak", m_streak.intValue());
                        bundle.putString("span", m_span);
                        bundle.putBoolean("image", m_image);
                        bundle.putString("user_id", m_user_id);
                        bundle.putSerializable("comments", m_comments);
                        bundle.putSerializable("awards", m_awards);
                        bundle.putBoolean("is_this_upvoted", is_this_upvote);
                        bundle.putBoolean("is_this_downvote", is_this_down_vote);
                        bundle.putString("document_id", m_document_id);
                        bundle.putBoolean("is_this_from_firebase", m_is_this_from_fire_base);
                        bundle.putSerializable("list_of_upvotes", m_upvotes);
                        bundle.putSerializable("list_of_downvotes", m_downvotes);
                        bundle.putBoolean("post_saved", post_saved);
                        bundle.putInt("plus_or_minus", plus_or_minus);
                        bundle.putBoolean("is_this_loading", m_is_this_laoding);
                        bundle.putBoolean("i_already_loaded", i_already_loaded);
                        bundle.putBoolean("was_upvote_down_vote_clicked", upvote_or_down_vote_was_clicked);
                        bundle.putBoolean("was_this_reported", was_this_reported);
                        bundle.putSerializable("list_of_reports", m_list_of_reports);
                        bundle.putBoolean("am_i_loading_up_vote_down_vote", am_i_loading_up_voote_down_vote_from_fire_base);
                        bundle.putBoolean("did_i_see_this_item", did_i_see_this_tem);
                        bundle.putSerializable("list_of_seen_posts", m_list_of_seen_posts);
                        bundle.putBoolean("m_is_post_by_dev", m_is_post_by_dev);
                        bundle.putString("which_post_called_me", "your posts");
                        bundle.putString("name_of_posted", name);
                        show_full_post.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, show_full_post, "show full post").hide(Posts_fragment.this).commit();
                    }
                }
            });
            adapter_for_your_post.set_share_clicked_listen(new Adapter_for_feed_posts.share_button_was_clicked() {
                @Override
                public void share_just_got_clciked(String title, String body) {
                    share_screen_shot(title, body);
                }
            });
            adapter_for_your_post.set_old_button_clicked_listen(new Adapter_for_feed_posts.old_post_is_clicked() {
                @Override
                public void old_post_got_clicked(int position, String name, String title, String body, String span, Date time, String category, int streak, String document_id) {
                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.post_opened,false);
                    Show_full_post show_full_post = new Show_full_post();
                    show_full_post.set_hide_button_listen(new Show_full_post.hide_button_clicked() {
                        @Override
                        public void hide_button_clicked() {
                            example_list_for_your_post.remove(position);
                            adapter_for_your_post.notifyItemRemoved(position);
                            adapter_for_your_post.notifyItemRangeChanged(position, example_list_for_your_post.size());
                            add_information_to_array_list();
                            hide_your_posts_if_empty();
                        }
                    });
                    Bundle bundle = new Bundle();
                    bundle.putInt("position", position);
                    bundle.putString("title", title);
                    bundle.putString("body", body);
//                    bundle.putString("category", category);
                    bundle.putSerializable("time", time);
                    bundle.putInt("streak", streak);
                    bundle.putString("span", span);
                    bundle.putString("document_id", document_id);
                    bundle.putString("which_post_called_me", "your posts");
                    bundle.putString("name_of_posted", name);
                    bundle.putString("what_is_the_type_of_this", "your_post_offline");
                    bundle.putBoolean("post_saved", false);
                    show_full_post.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, show_full_post, "show full post").hide(Posts_fragment.this).commit();
                }
            });
            adapter_for_your_post.set_up_gift_listen(new Adapter_for_feed_posts.gift_button_listen() {
                @Override
                public void gift_was_clicked(int index, String user_id, String document_id, int comment_position, int reply_position, ArrayList<Long> awards) {
                    if (comment_position == -100 && reply_position == -100) {
                        Bottom_sheet_to_give_coins bottom_sheet_to_give_coins = new Bottom_sheet_to_give_coins(index, user_id, document_id, awards, "posts");
                        bottom_sheet_to_give_coins.set_update_gift(new Bottom_sheet_to_give_coins.update_gift_listen() {
                            @Override
                            public void gift_was_clicked(int index, ArrayList<Long> arrayList_of_awards) {
                                example_list_for_your_post.set(index, new Example_item_feed_posts(example_list.get(index).getM_title(), example_list.get(index).getM_body(), example_list.get(index).get_m_category(), example_list.get(index).getM_time(), example_list.get(index).getM_streak(), example_list.get(index).getM_span(), false, example_list.get(index).getM_user_id(), example_list.get(index).getM_comments(), arrayList_of_awards, example_list.get(index).get_is_this_upvote(), example_list.get(index).get_the_down_vote(), example_list.get(index).getM_document_id(), example_list.get(index).get_is_this_from_firebase(), example_list.get(index).get_upvote_list(), example_list.get(index).getM_downvotes(), example_list.get(index).isPost_saved(), example_list.get(index).getPlus_or_minus(), example_list.get(index).isM_is_this_laoding(), example_list.get(index).get_i_already_laoded(), example_list.get(index).isUpvote_or_down_vote_was_clicked(), example_list.get(index).getM_firebaseFirestore(), example_list.get(index).getM_firebaseUser(), example_list.get(index).isWas_this_reported(), example_list.get(index).getM_list_of_reports(), example_list.get(index).isAm_i_loading_up_voote_down_vote_from_fire_base(), example_list.get(index).isDid_i_see_this_tem(), example_list.get(index).getM_list_of_seen_posts(), example_list.get(index).isM_is_post_by_dev(), example_list.get(index).getName_of_the_poster()));
                                adapter_for_your_post.notifyDataSetChanged();
                            }
                        });
                        bottom_sheet_to_give_coins.show(getParentFragmentManager(), "");
                    } else if (reply_position == -100) {

                    } else {

                    }
                }
            });
            adapter_for_your_post.set_up_save_only_for_pro_listen(new Adapter_for_feed_posts.save_only_for_pro() {
                @Override
                public void save_only_for_pro() {
                    Posts_fragment old_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
                    Buy_premuim buy_premuim = new Buy_premuim("save posts", true, old_fragment);
                    if (old_fragment != null) {
                        getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
                    }
                }
            });
            adapter_for_your_post.set_up_hide_post(new Adapter_for_feed_posts.hide_post_clickled() {
                @Override
                public void hide_post_clickled(int position) {
                    example_list_for_your_post.remove(position);
                    adapter_for_your_post.notifyItemRemoved(position);
                    adapter_for_your_post.notifyItemRangeChanged(position, example_list_for_your_post.size());
                    add_information_to_array_list();
                    hide_your_posts_if_empty();
                }
            });
        }
    }

    private void add_stuff_to_your_post_recylce() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("posted_posts", MODE_PRIVATE);
            String old = sharedPreferences.getString("posts", "");
            if (index_to_start_your_posts >= 0) {
                if (your_post_length > 15) {
                    wait_for_result_for_your_post(old, firebase_loading_limit, true);
                } else {
                    wait_for_result_for_your_post(old, firebase_loading_limit, false);
                }
            }
        }
    }

    private void wait_for_result_for_your_post(final String old, final int recursion, final boolean put_the_loading) {
        if (getActivity() != null) {
            if (old != null && !old.isEmpty()) {
                if (recursion == 0 || your_post_length - index_to_start_your_posts == 0) {
                    load_post_in_our_once(old, put_the_loading);
                    return;
                }
                final String[] big_split = old.split("big_divide");
                final String[] small_split = big_split[your_post_length - index_to_start_your_posts].split("small_split");
                final DocumentReference docIdRef = firebaseFirestore.collection("posts").document(small_split[5]);
                docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot snapshot = task.getResult();
                            if (snapshot.exists()) {
                                if (should_i_show_this_post((String) snapshot.get("userid"), snapshot.getId())) {
                                    String title = snapshot.getString("title");
                                    String body = snapshot.getString("body");
//                                String categorry = (String) snapshot.get("category");
                                    Date time = snapshot.getTimestamp("time_posted").toDate();
//                                long streak = (long) snapshot.get("streak");
                                    String span = snapshot.getString("span");
                                    String user_id = (String) snapshot.get("userid");
                                    ArrayList<HashMap<String, Object>> comments = (ArrayList<HashMap<String, Object>>) snapshot.get("comments");
                                    ArrayList<Long> awards = (ArrayList<Long>) snapshot.get("awards");
                                    String document_id = snapshot.getId();
                                    ArrayList<String> up_vote_list = (ArrayList<String>) snapshot.get("up_vote_list");
                                    ArrayList<String> down_vote_list = (ArrayList<String>) snapshot.get("down_vote_list");
                                    ArrayList<String> reports = (ArrayList<String>) snapshot.get("reports");
                                    boolean is_this_dev = (boolean) snapshot.get("dev");
                                    Map<String, Timestamp> map_with_time_for_comments = (Map<String, Timestamp>) snapshot.get("map_with_time");
                                    String name_of_poster = snapshot.getString("name");
                                    example_list_for_your_post.add(new Example_item_feed_posts(title, body, (long) -1, false, "", time, user_id, span, comments, awards, document_id, true, up_vote_list, down_vote_list, FirebaseAuth.getInstance().getCurrentUser(), false, firebaseFirestore, reports, list_of_all_seen_posts_to_pass_for_the_adapter, is_this_dev, map_with_time_for_comments, name_of_poster));
                                    adapter_for_your_post.notifyDataSetChanged();
                                    int copy = recursion;
                                    copy--;
                                    index_to_start_your_posts++;
                                    wait_for_result_for_your_post(old, copy, put_the_loading);
                                } else {
                                    int copy = recursion;
                                    copy--;
                                    index_to_start_your_posts++;
                                    wait_for_result_for_your_post(old, copy, put_the_loading);
                                }
                            } else {
                                String name = small_split[0];
                                String title = small_split[1];
                                String body = small_split[2];
                                String span = small_split[3];
                                Date time = new Date(Long.parseLong(small_split[4]));
                                String document_id = small_split[5];
                                example_list_for_your_post.add(new Example_item_feed_posts(document_id, name, title, body, span, time, "", -1, false));
                                adapter_for_your_post.notifyDataSetChanged();
                                int copy_of_i = recursion;
                                copy_of_i--;
                                index_to_start_your_posts++;
                                wait_for_result_for_your_post(old, copy_of_i, put_the_loading);
                            }
                        } else {
                            Toast toast = Toast.makeText(getActivity(), "Cant load data", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                });
            }
        }
    }

    private void load_post_in_our_once(String old, final boolean is_this_laoding) {
        final String[] big_split = old.split("big_divide");
        final String[] small_split = big_split[your_post_length - index_to_start_your_posts].split("small_split");
        final DocumentReference docIdRef = firebaseFirestore.collection("posts").document(small_split[5]);
        docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot snapshot = task.getResult();
                    if (snapshot.exists()) {
                        if (should_i_show_this_post((String) snapshot.get("userid"), snapshot.getId())) {
                            String title = snapshot.getString("title");
                            String body = snapshot.getString("body");
//                        String categorry = (String) snapshot.get("category");
                            Date time = snapshot.getTimestamp("time_posted").toDate();
//                        long streak = (long) snapshot.get("streak");
                            String span = snapshot.getString("span");
                            String user_id = (String) snapshot.get("userid");
                            ArrayList<HashMap<String, Object>> comments = (ArrayList<HashMap<String, Object>>) snapshot.get("comments");
                            ArrayList<Long> awards = (ArrayList<Long>) snapshot.get("awards");
                            String document_id = snapshot.getId();
                            ArrayList<String> up_vote_list = (ArrayList<String>) snapshot.get("up_vote_list");
                            ArrayList<String> down_vote_list = (ArrayList<String>) snapshot.get("down_vote_list");
                            ArrayList<String> reports = (ArrayList<String>) snapshot.get("reports");
                            boolean is_this_dev = (boolean) snapshot.get("dev");
                            Map<String, Timestamp> map_with_time_for_comments = (Map<String, Timestamp>) snapshot.get("map_with_time");
                            String name_of_poster = snapshot.getString("name");
                            example_list_for_your_post.add(new Example_item_feed_posts(title, body, (long) -1, false, "", time, user_id, span, comments, awards, document_id, true, up_vote_list, down_vote_list, FirebaseAuth.getInstance().getCurrentUser(), is_this_laoding, firebaseFirestore, reports, list_of_all_seen_posts_to_pass_for_the_adapter, is_this_dev, map_with_time_for_comments, name_of_poster));
                            adapter_for_your_post.notifyDataSetChanged();
                            index_to_start_your_posts++;
                        }
                    } else {
                        String name = small_split[0];
                        String title = small_split[1];
                        String body = small_split[2];
                        String span = small_split[3];
                        Date time = new Date(Long.parseLong(small_split[4]));
                        String document_id = small_split[5];
                        example_list_for_your_post.add(new Example_item_feed_posts(document_id, name, title, body, span, time, "", -1, false));
                        adapter_for_your_post.notifyDataSetChanged();
                        index_to_start_your_posts++;
                    }
                } else {
                    Toast toast = Toast.makeText(getActivity(), "Cant load data", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    private void set_up_the_recycle_view_saved_posts_real() {
        if (getView() != null) {
            list_of_saved_posts = new ArrayList<>();
            reset_saved_posts();
            recylce_view_to_show_saved_posts = getView().findViewById(R.id.recylce_view_to_show_saved_posts);
            linearLayoutManager_for_saved_post = new LinearLayoutManager(getActivity());
            adapter_for_saved_post = new Adapter_for_feed_posts(example_list_for_saved_post);
            recylce_view_to_show_saved_posts.setLayoutManager(linearLayoutManager_for_saved_post);
            recylce_view_to_show_saved_posts.setAdapter(adapter_for_saved_post);
            recylce_view_to_show_saved_posts.setHasFixedSize(false);
            recylce_view_to_show_saved_posts.setNestedScrollingEnabled(false);
            adapter_for_saved_post.set_on_item_click_listen(new Adapter_for_feed_posts.up_vote_clicked_not_signed_in() {
                @Override
                public void up_vote_click_no_sign_in(String mode) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Sign in with google")
                            .setMessage("In order to prevent malicious ".concat(mode).concat("s, please sign in with google to ".concat(mode)))
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Bottom_sheet_to_sing_in_to_write_posts bottom_sheet_to_sing_in_to_write_posts = new Bottom_sheet_to_sing_in_to_write_posts();
                                    bottom_sheet_to_sing_in_to_write_posts.setTargetFragment(Posts_fragment.this, 254);
                                    bottom_sheet_to_sing_in_to_write_posts.show(getActivity().getSupportFragmentManager(), "tag");
                                }
                            })
                            .setNegativeButton("cancel", null)
                            .show();
                }
            });
            adapter_for_saved_post.set_image_click_listen(new Adapter_for_feed_posts.image_clicked_interface() {
                @Override
                public void image_clicked(String base64) {
                    show_image_fragment(base64);
                }
            });
            adapter_for_saved_post.set_data_load_listen(new Adapter_for_feed_posts.data_is_loaded() {
                @Override
                public void data_is_done_loading_from_adapter() {
                    add_stuff_to_your_post_recylce();
                }
            });
            adapter_for_saved_post.set_item_click_listen(new Adapter_for_feed_posts.post_is_clicked() {
                @Override
                public void post_just_got_clicked(int position, String m_title, String m_body, String m_category, Date m_time, Long m_streak, String m_span, boolean m_image, String m_user_id, ArrayList<HashMap<String, Object>> m_comments, ArrayList<Long> m_awards, boolean is_this_upvote, boolean is_this_down_vote, String m_document_id, boolean m_is_this_from_fire_base, ArrayList<String> m_upvotes, ArrayList<String> m_downvotes, boolean post_saved, int plus_or_minus, boolean m_is_this_laoding, boolean i_already_loaded, boolean upvote_or_down_vote_was_clicked, FirebaseFirestore m_firebaseFirestore, FirebaseUser m_firebaseUser, boolean was_this_reported, ArrayList<String> m_list_of_reports, boolean am_i_loading_up_voote_down_vote_from_fire_base, boolean did_i_see_this_tem, ArrayList<String> m_list_of_seen_posts, boolean m_is_post_by_dev, String name) {
                    if (getActivity() != null) {
                        Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.post_opened,false);
                        Show_full_post show_full_post = new Show_full_post();
                        show_full_post.set_hide_button_listen(new Show_full_post.hide_button_clicked() {
                            @Override
                            public void hide_button_clicked() {
                                example_list_for_saved_post.remove(position);
                                adapter_for_saved_post.notifyItemRemoved(position);
                                adapter_for_saved_post.notifyItemRangeChanged(position, example_list_for_saved_post.size());
                                add_information_to_array_list();
                                hide_saved_posts_if_empty();
                            }
                        });
                        Bundle bundle = new Bundle();
                        bundle.putInt("position", position);
                        bundle.putString("title", m_title);
                        bundle.putString("body", m_body);
//                        bundle.putString("category", m_category);
                        bundle.putSerializable("time", m_time);
//                        bundle.putInt("streak", m_streak.intValue());
                        bundle.putString("span", m_span);
                        bundle.putBoolean("image", m_image);
                        bundle.putString("user_id", m_user_id);
                        bundle.putSerializable("comments", m_comments);
                        bundle.putSerializable("awards", m_awards);
                        bundle.putBoolean("is_this_upvoted", is_this_upvote);
                        bundle.putBoolean("is_this_downvote", is_this_down_vote);
                        bundle.putString("document_id", m_document_id);
                        bundle.putBoolean("is_this_from_firebase", m_is_this_from_fire_base);
                        bundle.putSerializable("list_of_upvotes", m_upvotes);
                        bundle.putSerializable("list_of_downvotes", m_downvotes);
                        bundle.putBoolean("post_saved", post_saved);
                        bundle.putInt("plus_or_minus", plus_or_minus);
                        bundle.putBoolean("is_this_loading", m_is_this_laoding);
                        bundle.putBoolean("i_already_loaded", i_already_loaded);
                        bundle.putBoolean("was_upvote_down_vote_clicked", upvote_or_down_vote_was_clicked);
                        bundle.putBoolean("was_this_reported", was_this_reported);
                        bundle.putSerializable("list_of_reports", m_list_of_reports);
                        bundle.putBoolean("am_i_loading_up_vote_down_vote", am_i_loading_up_voote_down_vote_from_fire_base);
                        bundle.putBoolean("did_i_see_this_item", did_i_see_this_tem);
                        bundle.putSerializable("list_of_seen_posts", m_list_of_seen_posts);
                        bundle.putBoolean("m_is_post_by_dev", m_is_post_by_dev);
                        bundle.putString("which_post_called_me", "saved posts");
                        bundle.putString("name_of_posted", name);
                        show_full_post.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, show_full_post, "show full post").hide(Posts_fragment.this).commit();
                    }
                }
            });
            adapter_for_saved_post.set_share_clicked_listen(new Adapter_for_feed_posts.share_button_was_clicked() {
                @Override
                public void share_just_got_clciked(String title, String body) {
                    share_screen_shot(title, body);
                }
            });
            adapter_for_saved_post.set_old_button_clicked_listen(new Adapter_for_feed_posts.old_post_is_clicked() {
                @Override
                public void old_post_got_clicked(int position, String name, String title, String body, String span, Date time, String category, int streak, String document_id) {
                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.post_opened,false);
                    Show_full_post show_full_post = new Show_full_post();
                    show_full_post.set_hide_button_listen(new Show_full_post.hide_button_clicked() {
                        @Override
                        public void hide_button_clicked() {
                            example_list_for_saved_post.remove(position);
                            adapter_for_saved_post.notifyItemRemoved(position);
                            adapter_for_saved_post.notifyItemRangeChanged(position, example_list_for_saved_post.size());
                            add_information_to_array_list();
                            hide_saved_posts_if_empty();
                        }
                    });
                    Bundle bundle = new Bundle();
                    bundle.putInt("position", position);
                    bundle.putString("title", title);
                    bundle.putString("body", body);
//                    bundle.putString("category", category);
                    bundle.putSerializable("time", time);
                    bundle.putInt("streak", streak);
                    bundle.putString("span", span);
                    bundle.putString("document_id", document_id);
                    bundle.putString("which_post_called_me", "saved posts");
                    bundle.putString("name_of_posted", name);
                    bundle.putString("what_is_the_type_of_this", "saved_post_offline");
                    bundle.putBoolean("post_saved", true);
                    show_full_post.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, show_full_post, "show full post").hide(Posts_fragment.this).commit();
                }
            });
            adapter_for_saved_post.set_up_gift_listen(new Adapter_for_feed_posts.gift_button_listen() {
                @Override
                public void gift_was_clicked(int index, String user_id, String document_id, int comment_position, int reply_position, ArrayList<Long> awards) {
                    if (comment_position == -100 && reply_position == -100) {
                        Bottom_sheet_to_give_coins bottom_sheet_to_give_coins = new Bottom_sheet_to_give_coins(index, user_id, document_id, awards, "posts");
                        bottom_sheet_to_give_coins.set_update_gift(new Bottom_sheet_to_give_coins.update_gift_listen() {
                            @Override
                            public void gift_was_clicked(int index, ArrayList<Long> arrayList_of_awards) {
                                example_list_for_saved_post.set(index, new Example_item_feed_posts(example_list.get(index).getM_title(), example_list.get(index).getM_body(), example_list.get(index).get_m_category(), example_list.get(index).getM_time(), example_list.get(index).getM_streak(), example_list.get(index).getM_span(), false, example_list.get(index).getM_user_id(), example_list.get(index).getM_comments(), arrayList_of_awards, example_list.get(index).get_is_this_upvote(), example_list.get(index).get_the_down_vote(), example_list.get(index).getM_document_id(), example_list.get(index).get_is_this_from_firebase(), example_list.get(index).get_upvote_list(), example_list.get(index).getM_downvotes(), example_list.get(index).isPost_saved(), example_list.get(index).getPlus_or_minus(), example_list.get(index).isM_is_this_laoding(), example_list.get(index).get_i_already_laoded(), example_list.get(index).isUpvote_or_down_vote_was_clicked(), example_list.get(index).getM_firebaseFirestore(), example_list.get(index).getM_firebaseUser(), example_list.get(index).isWas_this_reported(), example_list.get(index).getM_list_of_reports(), example_list.get(index).isAm_i_loading_up_voote_down_vote_from_fire_base(), example_list.get(index).isDid_i_see_this_tem(), example_list.get(index).getM_list_of_seen_posts(), example_list.get(index).isM_is_post_by_dev(), example_list.get(index).getName_of_the_poster()));
                                adapter_for_saved_post.notifyDataSetChanged();
                            }
                        });
                        bottom_sheet_to_give_coins.show(getParentFragmentManager(), "");
                    } else if (reply_position == -100) {

                    } else {

                    }
                }
            });
            adapter_for_saved_post.set_up_save_only_for_pro_listen(new Adapter_for_feed_posts.save_only_for_pro() {
                @Override
                public void save_only_for_pro() {
                    Posts_fragment old_fragment = (Posts_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("posts");
                    Buy_premuim buy_premuim = new Buy_premuim("save posts", true, old_fragment);
                    if (old_fragment != null) {
                        getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
                    }
                }
            });
            adapter_for_saved_post.set_up_hide_post(new Adapter_for_feed_posts.hide_post_clickled() {
                @Override
                public void hide_post_clickled(int position) {
                    example_list_for_saved_post.remove(position);
                    adapter_for_saved_post.notifyItemRemoved(position);
                    adapter_for_saved_post.notifyItemRangeChanged(position, example_list_for_saved_post.size());
                    add_information_to_array_list();
                    hide_saved_posts_if_empty();
                }
            });
        }
    }

    private void add_stuff_to_saved_posts() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("saved_posts", MODE_PRIVATE);
            String old = sharedPreferences.getString("posts", "");
            if (index_to_start_saved_posts >= 0) {
                if (saved_posts_length > 15) {
                    wait_for_result_for_saved_post_real(old, firebase_loading_limit, true);
                } else {
                    wait_for_result_for_saved_post_real(old, firebase_loading_limit, false);
                }
            }
        }
    }

    private void load_post_in_saved_posts_once(String old, final boolean is_this_laoding) {
        final String[] big_split = old.split("big_divide");
        final String[] small_split = big_split[saved_posts_length - index_to_start_saved_posts].split("small_split");
        final DocumentReference docIdRef = firebaseFirestore.collection("posts").document(small_split[5]);
        docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot snapshot = task.getResult();
                    if (snapshot.exists()) {
                        if (should_i_show_this_post((String) snapshot.get("userid"), snapshot.getId())) {
                            String title = snapshot.getString("title");
                            String body = snapshot.getString("body");
//                        String categorry = (String) snapshot.get("category");
                            Date time = snapshot.getTimestamp("time_posted").toDate();
//                        long streak = (long) snapshot.get("streak");
                            String span = snapshot.getString("span");
                            String user_id = (String) snapshot.get("userid");
                            ArrayList<HashMap<String, Object>> comments = (ArrayList<HashMap<String, Object>>) snapshot.get("comments");
                            ArrayList<Long> awards = (ArrayList<Long>) snapshot.get("awards");
                            String document_id = snapshot.getId();
                            ArrayList<String> up_vote_list = (ArrayList<String>) snapshot.get("up_vote_list");
                            ArrayList<String> down_vote_list = (ArrayList<String>) snapshot.get("down_vote_list");
                            ArrayList<String> reports = (ArrayList<String>) snapshot.get("reports");
                            boolean is_this_dev = (boolean) snapshot.get("dev");
                            Map<String, Timestamp> map_with_time_for_comments = (Map<String, Timestamp>) snapshot.get("map_with_time");
                            String name_of_poster = snapshot.getString("name");
                            example_list_for_saved_post.add(new Example_item_feed_posts(title, body, (long) -1, false, "", time, user_id, span, comments, awards, document_id, true, up_vote_list, down_vote_list, FirebaseAuth.getInstance().getCurrentUser(), is_this_laoding, firebaseFirestore, reports, list_of_all_seen_posts_to_pass_for_the_adapter, is_this_dev, map_with_time_for_comments, name_of_poster));
                            adapter_for_saved_post.notifyDataSetChanged();
                            index_to_start_saved_posts++;
                        }
                    } else {
                        String name = small_split[0];
                        String title = small_split[1];
                        String body = small_split[2];
                        String span = small_split[3];
                        Date time = new Date(Long.parseLong(small_split[4]));
                        String document_id = small_split[5];
                        example_list_for_saved_post.add(new Example_item_feed_posts(document_id, name, title, body, span, time, "", -1, true));
                        adapter_for_saved_post.notifyDataSetChanged();
                        index_to_start_saved_posts++;
                    }
                } else {
                    Toast toast = Toast.makeText(getActivity(), "Cant load data", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    private void wait_for_result_for_saved_post_real(final String old, final int recursion, final boolean should_i_run_again) {
        if (getActivity() != null) {
            if (old != null && !old.isEmpty()) {
                if (recursion == 0 || saved_posts_length - index_to_start_saved_posts == 0) {
                    load_post_in_saved_posts_once(old, should_i_run_again);
                    return;
                }
                final String[] big_split = old.split("big_divide");
                final String[] small_split = big_split[saved_posts_length - index_to_start_saved_posts].split("small_split");
                final DocumentReference docIdRef = firebaseFirestore.collection("posts").document(small_split[5]);
                docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot snapshot = task.getResult();
                            if (snapshot.exists()) {
                                if (should_i_show_this_post((String) snapshot.get("userid"), snapshot.getId())) {
                                    String title = snapshot.getString("title");
                                    String body = snapshot.getString("body");
//                                String categorry = (String) snapshot.get("category");
                                    Date time = snapshot.getTimestamp("time_posted").toDate();
//                                long streak = (long) snapshot.get("streak");
                                    String span = snapshot.getString("span");
                                    String user_id = (String) snapshot.get("userid");
                                    ArrayList<HashMap<String, Object>> comments = (ArrayList<HashMap<String, Object>>) snapshot.get("comments");
                                    ArrayList<Long> awards = (ArrayList<Long>) snapshot.get("awards");
                                    String document_id = snapshot.getId();
                                    ArrayList<String> up_vote_list = (ArrayList<String>) snapshot.get("up_vote_list");
                                    ArrayList<String> down_vote_list = (ArrayList<String>) snapshot.get("down_vote_list");
                                    ArrayList<String> reports = (ArrayList<String>) snapshot.get("reports");
                                    boolean is_this_dev = (boolean) snapshot.get("dev");
                                    Map<String, Timestamp> map_with_time_for_comments = (Map<String, Timestamp>) snapshot.get("map_with_time");
                                    String name_of_poster = snapshot.getString("name");
                                    example_list_for_saved_post.add(new Example_item_feed_posts(title, body, (long) -1, false, "", time, user_id, span, comments, awards, document_id, true, up_vote_list, down_vote_list, FirebaseAuth.getInstance().getCurrentUser(), false, firebaseFirestore, reports, list_of_all_seen_posts_to_pass_for_the_adapter, is_this_dev, map_with_time_for_comments, name_of_poster));
                                    adapter_for_saved_post.notifyDataSetChanged();
                                    int copy_of_i = recursion;
                                    copy_of_i--;
                                    index_to_start_saved_posts++;
                                    wait_for_result_for_saved_post_real(old, copy_of_i, should_i_run_again);
                                }
                            } else {
                                String name = small_split[0];
                                String title = small_split[1];
                                String body = small_split[2];
                                String span = small_split[3];
                                Date time = new Date(Long.parseLong(small_split[4]));
                                String document_id = small_split[5];
                                example_list_for_saved_post.add(new Example_item_feed_posts(document_id, name, title, body, span, time, "", -1, true));
                                adapter_for_saved_post.notifyDataSetChanged();
                                int copy_of_i = recursion;
                                copy_of_i--;
                                index_to_start_saved_posts++;
                                wait_for_result_for_saved_post_real(old, copy_of_i, should_i_run_again);
                            }
                        } else {
                            Toast toast = Toast.makeText(getActivity(), "Cant load data", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                });
            }
        }
    }

    private void reset_your_posts() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("posted_posts", MODE_PRIVATE);
            String old = sharedPreferences.getString("posts", "");
            final String[] big_split = old.split("big_divide");
            your_post_length = big_split.length - 1;
            index_to_start_your_posts = 0;
        }
    }

    private void reset_saved_posts() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("saved_posts", MODE_PRIVATE);
            String old = sharedPreferences.getString("posts", "");
            final String[] big_split = old.split("big_divide");
            saved_posts_length = big_split.length - 1;
            index_to_start_saved_posts = 0;
        }
    }

    /*private void load_for_the_recylce_loading_screen(String old) {
        if (getActivity() != null) {
            if (old != null && !old.isEmpty()) {
                if (your_post_length - index_to_start_your_posts < 0) {
                    return;
                }
                final String[] big_split = old.split("big_divide");
                final String[] small_split = big_split[your_post_length - index_to_start_your_posts].split("small_split");
                final DocumentReference docIdRef = firebaseFirestore.collection("posts").document(small_split[6]);
                docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot snapshot = task.getResult();
                            if (snapshot !=null && snapshot.exists()) {
                                String title = snapshot.getString("title");
                                String body = snapshot.getString("body");
                                String categorry = (String) snapshot.get("category");
                                Date time = snapshot.getTimestamp("time_posted").toDate();
                                long streak = (long) snapshot.get("streak");
                                String span = snapshot.getString("span");
                                String user_id = (String) snapshot.get("userid");
                                ArrayList<HashMap<String, Object>> comments = (ArrayList<HashMap<String, Object>>) snapshot.get("comments");
                                ArrayList<Long> awards = (ArrayList<Long>) snapshot.get("awards");
                                String document_id = snapshot.getId();
                                ArrayList<String> up_vote_list = (ArrayList<String>) snapshot.get("up_vote_list");
                                ArrayList<String> down_vote_list = (ArrayList<String>) snapshot.get("down_vote_list");
                                ArrayList<String> reports = (ArrayList<String>) snapshot.get("reports");
                                boolean is_this_dev = (boolean) snapshot.get("dev");
                                Map<String, Timestamp> map_with_time_for_comments = (Map<String, Timestamp>) snapshot.get("map_with_time");
                                example_list_for_your_post.add(new Example_item_feed_posts(title, body, streak, false, categorry, time, user_id, span, comments, awards, document_id, true, up_vote_list, down_vote_list, firebaseUser, true, firebaseFirestore, reports, list_of_all_seen_posts_to_pass_for_the_adapter, is_this_dev, map_with_time_for_comments));
                                adapter_for_your_post.notifyDataSetChanged();
                            } else {
                                String title = small_split[0];
                                String body = small_split[1];
                                String span = small_split[2];
                                Date time = new Date(Long.parseLong(small_split[4]));
                                String category = small_split[5];
                                long streak = Long.parseLong(small_split[6]);
                                example_list_for_your_post.add(new Example_item_feed_posts(title, body, streak, false, category, time, null, span, null, null, null, false, null, null, firebaseUser, true, firebaseFirestore, null, null, false));
                                adapter_for_your_post.notifyDataSetChanged();
                            }
                        } else {
                            Toast toast = Toast.makeText(getActivity(), "Cant load data", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                });
            }
        }
    }*/

    /*private void loading_the_saved_post(String old) {
        if (getActivity() != null) {
            if (old != null && !old.isEmpty()) {
                if (saved_posts_length - index_to_start_saved_posts < 0) {
                    return;
                }
                final String[] big_split = old.split("big_divide");
                final String[] small_split = big_split[saved_posts_length - index_to_start_saved_posts].split("small_split");
                final DocumentReference docIdRef = firebaseFirestore.collection("posts").document(small_split[6]);
                docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot snapshot = task.getResult();
                            if (snapshot != null && snapshot.exists()) {
                                String title = snapshot.getString("title");
                                String body = snapshot.getString("body");
//                                String categorry = (String) snapshot.get("category");
                                Date time = snapshot.getTimestamp("time_posted").toDate();
//                                long streak = (long) snapshot.get("streak");
                                String span = snapshot.getString("span");
                                String user_id = (String) snapshot.get("userid");
                                ArrayList<HashMap<String, Object>> comments = (ArrayList<HashMap<String, Object>>) snapshot.get("comments");
                                ArrayList<Long> awards = (ArrayList<Long>) snapshot.get("awards");
                                String document_id = snapshot.getId();
                                ArrayList<String> up_vote_list = (ArrayList<String>) snapshot.get("up_vote_list");
                                ArrayList<String> down_vote_list = (ArrayList<String>) snapshot.get("down_vote_list");
                                ArrayList<String> reports = (ArrayList<String>) snapshot.get("reports");
                                boolean is_this_dev = (boolean) snapshot.get("dev");
                                Map<String, Timestamp> map_with_time_for_comments = (Map<String, Timestamp>) snapshot.get("map_with_time");
                                String name_of_poster = snapshot.getString("name");
                                example_list_for_saved_post.add(new Example_item_feed_posts(title, body, (long) -1, false, "", time, user_id, span, comments, awards, document_id, true, up_vote_list, down_vote_list, FirebaseAuth.getInstance().getCurrentUser(), true, firebaseFirestore, reports, list_of_all_seen_posts_to_pass_for_the_adapter, is_this_dev, map_with_time_for_comments, name_of_poster));
                                adapter_for_saved_post.notifyDataSetChanged();
                            } else {
                                *//*String title = small_split[0];
                                String body = small_split[1];
                                String span = small_split[2];
                                Date time = new Date(Long.parseLong(small_split[3]));
                                String category = small_split[4];
                                long streak = Long.parseLong(small_split[5]);
                                example_list_for_saved_post.add(new Example_item_feed_posts(title, body, streak, false, category, time, null, span, null, null, null, false, null, null, firebaseUser, true, firebaseFirestore, null, null, false));
                                adapter_for_saved_post.notifyDataSetChanged();*//*
                            }
                        } else {
                            Toast toast = Toast.makeText(getActivity(), "Cant load data", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                });
            }
        }
    }*/

    private void reset_the_main() {
        if (list_of_seen != null && !list_of_seen.isEmpty()) {
            list_of_seen.clear();
            documentSnapshot_for_feed = null;
            clear_recylce_view();
            load_into_recylce_view(firebase_loading_limit, false, true);
        }
    }

    private boolean is_shared_empty(String which) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(which.concat("_posts"), MODE_PRIVATE);
        String old = sharedPreferences.getString("posts", "");
        if (old.isEmpty()) {
            return true;
        } else {
            return false;
            /*String[] big_split = old.split("big_divide");
            for (int i = 0; i < big_split.length; i++) {
                String[] small_split = big_split[i].split("small_split");
                if (which.equals("posted")) {
                    if (should_i_show_this_post(FirebaseAuth.getInstance().getCurrentUser().getUid(), small_split[5])) {
                        return false;
                    }
                } else {
                    if (should_i_show_this_post(small_split[6], small_split[5])) {
                        return false;
                    }
                }
            }
            return true;*/
        }
    }

    private void show_image_fragment(String base64) {
        if (getActivity() != null) {
            Show_image_post show_image_post = new Show_image_post();
            Bundle bundle = new Bundle();
            bundle.putString("base64", base64);
            show_image_post.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, show_image_post, "show post image").hide(Posts_fragment.this).commit();
        }
    }

    private void make_the_top_and_botton_go(int which) {
        if (getView() != null) {
            View view_at_the_top_under_the_three_choices_post = getView().findViewById(R.id.view_at_the_top_under_the_three_choices_post);
            //Button filter_in_posts_category = getView().findViewById(R.id.filter_in_posts_category);
            Button button_at_post_to_choose_the_ranking = getView().findViewById(R.id.button_at_post_to_choose_the_ranking);
            //View view_at_the_top_of_posts_showing_three_buttons = getView().findViewById(R.id.view_at_the_top_of_posts_showing_three_buttons);
            //Button button_to_go_to_all_posts = getView().findViewById(R.id.button_to_go_to_all_posts);
            //Button button_to_go_to_your_posts = getView().findViewById(R.id.button_to_go_to_your_posts);
            //Button button_to_go_to_saved_posts = getView().findViewById(R.id.button_to_go_to_saved_posts);
            //View white_view_under_first_button_feed = getView().findViewById(R.id.white_view_under_first_button_feed);
            //View white_view_under_second_button_your_posts = getView().findViewById(R.id.white_view_under_second_button_your_posts);
            //View white_view_under_third_button_saved_posts = getView().findViewById(R.id.white_view_under_third_button_saved_posts);
            //View divider_between_first_two_buttons_posts = getView().findViewById(R.id.divider_between_first_two_buttons_posts);
            //View divider_between_second_two_buttons_posts = getView().findViewById(R.id.divider_between_second_two_buttons_posts);
            FloatingActionButton flaoting_button_to_write_a_post_in_posts = getView().findViewById(R.id.flaoting_button_to_write_a_post_in_posts);
            if (which == 0) {
                view_at_the_top_under_the_three_choices_post.setVisibility(View.GONE);
                // filter_in_posts_category.setVisibility(View.GONE);
                button_at_post_to_choose_the_ranking.setVisibility(View.GONE);
                // view_at_the_top_of_posts_showing_three_buttons.setVisibility(View.GONE);
                // button_to_go_to_all_posts.setVisibility(View.GONE);
                //button_to_go_to_your_posts.setVisibility(View.GONE);
                // button_to_go_to_saved_posts.setVisibility(View.GONE);
                // white_view_under_first_button_feed.setVisibility(View.GONE);
                // white_view_under_second_button_your_posts.setVisibility(View.GONE);
                // white_view_under_third_button_saved_posts.setVisibility(View.GONE);
                // divider_between_first_two_buttons_posts.setVisibility(View.GONE);
                //  divider_between_second_two_buttons_posts.setVisibility(View.GONE);
                flaoting_button_to_write_a_post_in_posts.setVisibility(View.GONE);
            } else if (which == 1) {
                view_at_the_top_under_the_three_choices_post.setVisibility(View.VISIBLE);
                //filter_in_posts_category.setVisibility(View.VISIBLE);
                button_at_post_to_choose_the_ranking.setVisibility(View.VISIBLE);
                // view_at_the_top_of_posts_showing_three_buttons.setVisibility(View.VISIBLE);
                // button_to_go_to_all_posts.setVisibility(View.VISIBLE);
                // button_to_go_to_your_posts.setVisibility(View.VISIBLE);
                // button_to_go_to_saved_posts.setVisibility(View.VISIBLE);
                // white_view_under_first_button_feed.setVisibility(View.VISIBLE);
                // white_view_under_second_button_your_posts.setVisibility(View.VISIBLE);
                // white_view_under_third_button_saved_posts.setVisibility(View.VISIBLE);
                // divider_between_first_two_buttons_posts.setVisibility(View.VISIBLE);
                //divider_between_second_two_buttons_posts.setVisibility(View.VISIBLE);
                flaoting_button_to_write_a_post_in_posts.setVisibility(View.VISIBLE);
            }
        }
    }

    private void should_is_show_button() {
        if (getView() != null) {
            FloatingActionButton flaoting_button_to_write_a_post_in_posts = getView().findViewById(R.id.flaoting_button_to_write_a_post_in_posts);
            View view_at_the_top_under_the_three_choices_post = getView().findViewById(R.id.view_at_the_top_under_the_three_choices_post);
            if (view_at_the_top_under_the_three_choices_post.getVisibility() == View.VISIBLE) {
                flaoting_button_to_write_a_post_in_posts.setVisibility(View.VISIBLE);
            } else {
                flaoting_button_to_write_a_post_in_posts.setVisibility(View.GONE);
            }
        }
    }

    private void set_up_the_array_list_for_seen_posts() {
        if (getActivity() != null) {
            list_of_all_seen_posts_to_pass_for_the_adapter = new ArrayList<>();
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("viewed_post_saved_id", MODE_PRIVATE);
            String old = sharedPreferences.getString("posts", "");
            if (old != null && !old.isEmpty()) {
                String[] big_split = old.split("max_divide");
                for (int i = 0; i < big_split.length; i++) {
                    String[] small_split = big_split[i].split("small_split");
                    list_of_all_seen_posts_to_pass_for_the_adapter.add(small_split[1]);
                }
            }
        }
    }

    public void update_the_adapter(int position, String m_title, String m_body, String m_category, Date m_time, Long m_streak, String m_span, boolean m_image, String m_user_id, ArrayList<HashMap<String, Object>> m_comments, ArrayList<Long> m_awards, boolean is_this_upvote, boolean is_this_down_vote, String m_document_id, boolean m_is_this_from_fire_base, ArrayList<String> m_upvotes, ArrayList<String> m_downvotes, boolean post_saved, int plus_or_minus, boolean m_is_this_laoding, boolean i_already_loaded, boolean upvote_or_down_vote_was_clicked, FirebaseFirestore m_firebaseFirestore, FirebaseUser m_firebaseUser, boolean was_this_reported, ArrayList<String> m_list_of_reports, boolean am_i_loading_up_voote_down_vote_from_fire_base, boolean did_i_see_this_tem, ArrayList<String> m_list_of_seen_posts, boolean m_is_post_by_dev, String which, String name) {
        if (which.equals("feed")) {
            example_list.set(position, new Example_item_feed_posts(m_title, m_body, m_category, m_time, m_streak, m_span, m_image, m_user_id, m_comments, m_awards, is_this_upvote, is_this_down_vote, m_document_id, m_is_this_from_fire_base, m_upvotes, m_downvotes, post_saved, plus_or_minus, m_is_this_laoding, i_already_loaded, upvote_or_down_vote_was_clicked, m_firebaseFirestore, m_firebaseUser, was_this_reported, m_list_of_reports, am_i_loading_up_voote_down_vote_from_fire_base, did_i_see_this_tem, m_list_of_seen_posts, m_is_post_by_dev, name));
            adapter.notifyDataSetChanged();
        } else if (which.equals("your posts")) {
            example_list_for_your_post.set(position, new Example_item_feed_posts(m_title, m_body, m_category, m_time, m_streak, m_span, m_image, m_user_id, m_comments, m_awards, is_this_upvote, is_this_down_vote, m_document_id, m_is_this_from_fire_base, m_upvotes, m_downvotes, post_saved, plus_or_minus, m_is_this_laoding, i_already_loaded, upvote_or_down_vote_was_clicked, m_firebaseFirestore, m_firebaseUser, was_this_reported, m_list_of_reports, am_i_loading_up_voote_down_vote_from_fire_base, did_i_see_this_tem, m_list_of_seen_posts, m_is_post_by_dev, name));
            adapter_for_your_post.notifyDataSetChanged();
        } else if (which.equals("saved posts")) {
            example_list_for_saved_post.set(position, new Example_item_feed_posts(m_title, m_body, m_category, m_time, m_streak, m_span, m_image, m_user_id, m_comments, m_awards, is_this_upvote, is_this_down_vote, m_document_id, m_is_this_from_fire_base, m_upvotes, m_downvotes, post_saved, plus_or_minus, m_is_this_laoding, i_already_loaded, upvote_or_down_vote_was_clicked, m_firebaseFirestore, m_firebaseUser, was_this_reported, m_list_of_reports, am_i_loading_up_voote_down_vote_from_fire_base, did_i_see_this_tem, m_list_of_seen_posts, m_is_post_by_dev, name));
            adapter_for_saved_post.notifyDataSetChanged();
        } else if (which.equals("your_comments")) {
            comment_is_clciked();
        } else if (which.equals("saved_comments")) {
            saved_commetns_clicked();
        }
    }

    private void remove_old_seen_posts() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("viewed_post_saved_id", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            String old = sharedPreferences.getString("posts", "");
            if (old != null && !old.isEmpty()) {
                String[] big_split = old.split("max_divide");
                String save_me = "";
                for (int i = 0; i < big_split.length; i++) {
                    String[] small_split = big_split[i].split("small_split");
                    if (Long.parseLong(small_split[0]) <= TimeUnit.DAYS.toMillis(31)) {
                        save_me = save_me.concat(small_split[0]).concat("small_split").concat(small_split[1]).concat("max_divide");
                    }
                }
                myEdit.putString("viewed_post_saved_id", save_me);
                myEdit.commit();
            }
        }
    }

    private void share_screen_shot(String title, String body) {
        if (getView() != null && getContext() != null) {
            final TextView text_to_put_in_share = getView().findViewById(R.id.text_to_put_in_share);
            final TextView text_body_to_put_in_share = getView().findViewById(R.id.text_body_to_put_in_share);
            final ScrollView scroll_view_to_take_screen_show_in_good_habtis = getView().findViewById(R.id.scroll_view_to_take_screen_show_in_good_habtis);
            scroll_view_to_take_screen_show_in_good_habtis.setVisibility(View.VISIBLE);
            text_to_put_in_share.setText(title);
            text_body_to_put_in_share.setText(body);
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
        if (getView() != null && getContext() != null && getActivity() != null) {
            // save bitmap to cache directory
            try {
                File cachePath = new File(getContext().getCacheDir(), "images");
                cachePath.mkdirs(); // don't forget to make the directory
                FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
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

    public void update_the_your_posts() {
        if (getView() != null) {
            if (which_screen_is_this_on == 2) {
                final RecyclerView recylce_view_to_show_your_posts = getView().findViewById(R.id.recylce_view_to_show_your_posts);
                final RecyclerView recylce_view_to_show_your_comments = getView().findViewById(R.id.recylce_view_to_show_your_comments);
                final View first_line_under_button_in_your_posts_button_in_posts = getView().findViewById(R.id.first_line_under_button_in_your_posts_button_in_posts);
                final View second_line_under_button_in_your_comments_button_in_posts = getView().findViewById(R.id.second_line_under_button_in_your_comments_button_in_posts);
                FloatingActionButton flaoting_button_to_write_a_post_in_posts = getView().findViewById(R.id.flaoting_button_to_write_a_post_in_posts);
                recylce_view_to_show_your_posts.setVisibility(View.VISIBLE);
                recylce_view_to_show_your_comments.setVisibility(View.INVISIBLE);
                first_line_under_button_in_your_posts_button_in_posts.setVisibility(View.VISIBLE);
                second_line_under_button_in_your_comments_button_in_posts.setVisibility(View.INVISIBLE);
                example_list_for_your_post.clear();
                list_of_your_posts.clear();
                reset_your_posts();
                adapter_for_your_post.notifyDataSetChanged();
                add_stuff_to_your_post_recylce();
                if (getView() != null) {
                    if (is_shared_empty("posted")) {
                        TextView written_post_is_empty_in_posts = getView().findViewById(R.id.written_post_is_empty_in_posts);
                        TextView written_comments_replies_is_empty_in_posts = getView().findViewById(R.id.written_comments_replies_is_empty_in_posts);
                        written_comments_replies_is_empty_in_posts.setVisibility(View.INVISIBLE);
                        written_post_is_empty_in_posts.setVisibility(View.VISIBLE);
                        recylce_view_to_show_your_posts.setVisibility(View.INVISIBLE);
                        recylce_view_to_show_your_comments.setVisibility(View.INVISIBLE);
                    } else {
                        TextView written_post_is_empty_in_posts = getView().findViewById(R.id.written_post_is_empty_in_posts);
                        TextView written_comments_replies_is_empty_in_posts = getView().findViewById(R.id.written_comments_replies_is_empty_in_posts);
                        written_comments_replies_is_empty_in_posts.setVisibility(View.INVISIBLE);
                        written_post_is_empty_in_posts.setVisibility(View.INVISIBLE);
                        recylce_view_to_show_your_posts.setVisibility(View.VISIBLE);
                        recylce_view_to_show_your_comments.setVisibility(View.INVISIBLE);
                    }
                }
            }
        }
    }

    private void watch_the_comments_psots_listen() {
        if (getView() != null) {
            Button button_to_see_your_posts_in_recycle_view_in_posts = getView().findViewById(R.id.button_to_see_your_posts_in_recycle_view_in_posts);
            Button button_to_see_your_comments_in_recycle_view_in_posts = getView().findViewById(R.id.button_to_see_your_comments_in_recycle_view_in_posts);
            button_to_see_your_posts_in_recycle_view_in_posts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (which_saved_post_comment_is_this_on != 0) {
                        post_is_clicked();
                    }
                }
            });
            button_to_see_your_comments_in_recycle_view_in_posts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (which_saved_post_comment_is_this_on != 1) {
                        comment_is_clciked();
                    }
                }
            });
        }
    }

    private void post_is_clicked() {
        if (getView() != null) {
            final RecyclerView recylce_view_to_show_your_posts = getView().findViewById(R.id.recylce_view_to_show_your_posts);
            final RecyclerView recylce_view_to_show_your_comments = getView().findViewById(R.id.recylce_view_to_show_your_comments);
            final View first_line_under_button_in_your_posts_button_in_posts = getView().findViewById(R.id.first_line_under_button_in_your_posts_button_in_posts);
            final View second_line_under_button_in_your_comments_button_in_posts = getView().findViewById(R.id.second_line_under_button_in_your_comments_button_in_posts);
            FloatingActionButton flaoting_button_to_write_a_post_in_posts = getView().findViewById(R.id.flaoting_button_to_write_a_post_in_posts);
            which_saved_post_comment_is_this_on = 0;
            recylce_view_to_show_your_posts.setVisibility(View.VISIBLE);
            recylce_view_to_show_your_comments.setVisibility(View.INVISIBLE);
            first_line_under_button_in_your_posts_button_in_posts.setVisibility(View.VISIBLE);
            second_line_under_button_in_your_comments_button_in_posts.setVisibility(View.INVISIBLE);
            which_screen_is_this_on = 2;
            if (!did_you_set_up_your_post) {
                did_you_set_up_your_post = true;
                set_up_the_posts_saved_posts(); // your psots
            } else {
                example_list_for_your_post.clear();
                list_of_your_posts.clear();
                reset_your_posts();
                adapter_for_your_post.notifyDataSetChanged();
            }
            add_stuff_to_your_post_recylce();
            select_the_current_section_user_at(2);
            if (getView() != null) {
                if (is_shared_empty("posted")) {
                    TextView written_post_is_empty_in_posts = getView().findViewById(R.id.written_post_is_empty_in_posts);
                    TextView written_comments_replies_is_empty_in_posts = getView().findViewById(R.id.written_comments_replies_is_empty_in_posts);
                    written_comments_replies_is_empty_in_posts.setVisibility(View.INVISIBLE);
                    written_post_is_empty_in_posts.setVisibility(View.VISIBLE);
                    recylce_view_to_show_your_posts.setVisibility(View.INVISIBLE);
                    recylce_view_to_show_your_comments.setVisibility(View.INVISIBLE);
                } else {
                    TextView written_post_is_empty_in_posts = getView().findViewById(R.id.written_post_is_empty_in_posts);
                    TextView written_comments_replies_is_empty_in_posts = getView().findViewById(R.id.written_comments_replies_is_empty_in_posts);
                    written_comments_replies_is_empty_in_posts.setVisibility(View.INVISIBLE);
                    written_post_is_empty_in_posts.setVisibility(View.INVISIBLE);
                    recylce_view_to_show_your_posts.setVisibility(View.VISIBLE);
                    recylce_view_to_show_your_comments.setVisibility(View.INVISIBLE);
                }
            }
            flaoting_button_to_write_a_post_in_posts.setVisibility(View.VISIBLE);
        }
    }

    private void comment_is_clciked() {
        if (getView() != null) {
            final RecyclerView recylce_view_to_show_your_posts = getView().findViewById(R.id.recylce_view_to_show_your_posts);
            final RecyclerView recylce_view_to_show_your_comments = getView().findViewById(R.id.recylce_view_to_show_your_comments);
            final View first_line_under_button_in_your_posts_button_in_posts = getView().findViewById(R.id.first_line_under_button_in_your_posts_button_in_posts);
            final View second_line_under_button_in_your_comments_button_in_posts = getView().findViewById(R.id.second_line_under_button_in_your_comments_button_in_posts);
            FloatingActionButton flaoting_button_to_write_a_post_in_posts = getView().findViewById(R.id.flaoting_button_to_write_a_post_in_posts);
            flaoting_button_to_write_a_post_in_posts.setVisibility(View.INVISIBLE);
            which_saved_post_comment_is_this_on = 1;
            recylce_view_to_show_your_posts.setVisibility(View.INVISIBLE);
            recylce_view_to_show_your_comments.setVisibility(View.VISIBLE);
            first_line_under_button_in_your_posts_button_in_posts.setVisibility(View.INVISIBLE);
            second_line_under_button_in_your_comments_button_in_posts.setVisibility(View.VISIBLE);
            if (!did_you_setup_your_comments) {
                did_you_setup_your_comments = true;
                set_up_comment_posts();
            } else {
                example_list_for_your_comment_reply.clear();
            }
            add_stuff_to_comment_reply_recycle_view();

            if (getView() != null) {
                if (return_shared("your_comments").isEmpty()) {
                    TextView written_post_is_empty_in_posts = getView().findViewById(R.id.written_post_is_empty_in_posts);
                    TextView written_comments_replies_is_empty_in_posts = getView().findViewById(R.id.written_comments_replies_is_empty_in_posts);
                    written_post_is_empty_in_posts.setVisibility(View.INVISIBLE);
                    recylce_view_to_show_your_posts.setVisibility(View.INVISIBLE);
                    recylce_view_to_show_your_comments.setVisibility(View.INVISIBLE);
                    written_comments_replies_is_empty_in_posts.setVisibility(View.VISIBLE);
                } else {
                    TextView written_post_is_empty_in_posts = getView().findViewById(R.id.written_post_is_empty_in_posts);
                    TextView written_comments_replies_is_empty_in_posts = getView().findViewById(R.id.written_comments_replies_is_empty_in_posts);
                    written_post_is_empty_in_posts.setVisibility(View.INVISIBLE);
                    recylce_view_to_show_your_posts.setVisibility(View.INVISIBLE);
                    recylce_view_to_show_your_comments.setVisibility(View.VISIBLE);
                    written_comments_replies_is_empty_in_posts.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void set_up_comment_posts() {
        if (getView() != null && getActivity() != null) {
            reset_your_posts();
            recylce_view_to_show_your_comments = getView().findViewById(R.id.recylce_view_to_show_your_comments);
            linearLayoutManager_for_comments = new LinearLayoutManager(getActivity());
            adapter_for_comment_reply = new Adapter_for_comment_reply(example_list_for_your_comment_reply);
            recylce_view_to_show_your_comments.setLayoutManager(linearLayoutManager_for_comments);
            recylce_view_to_show_your_comments.setAdapter(adapter_for_comment_reply);
            recylce_view_to_show_your_comments.setHasFixedSize(false);
            recylce_view_to_show_your_comments.setNestedScrollingEnabled(false);
            adapter_for_comment_reply.set_card_clicked_comment(new Adapter_for_comment_reply.card_was_clicked_comment() {
                @Override
                public void card_is_clicked_comment(String document_id, String name_in_the_main, String title_of_the_main, String body_of_the_main_post, String span_the_main, long time_the_main, String category_of_the_main, long streak_of_the_main_post, String body_of_the_comment, int position_of_the_comment, String name_of_the_comment, long time_of_the_comment, String category, long streak_of_the_comment) {
                    final DocumentReference docIdRef = firebaseFirestore.collection("posts").document(document_id);
                    docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot snapshot = task.getResult();
                                if (snapshot.exists()) {
                                    String title = snapshot.getString("title");
                                    String body = snapshot.getString("body");
//                                    String categorry = (String) snapshot.get("category");
                                    Date time = snapshot.getTimestamp("time_posted").toDate();
//                                    long streak = (long) snapshot.get("streak");
                                    String span = snapshot.getString("span");
                                    String user_id = (String) snapshot.get("userid");
                                    ArrayList<HashMap<String, Object>> comments = (ArrayList<HashMap<String, Object>>) snapshot.get("comments");
                                    ArrayList<Long> awards = (ArrayList<Long>) snapshot.get("awards");
                                    String document_id = snapshot.getId();
                                    ArrayList<String> up_vote_list = (ArrayList<String>) snapshot.get("up_vote_list");
                                    ArrayList<String> down_vote_list = (ArrayList<String>) snapshot.get("down_vote_list");
                                    ArrayList<String> reports = (ArrayList<String>) snapshot.get("reports");
                                    boolean is_this_dev = (boolean) snapshot.get("dev");
                                    String name_of_poster = snapshot.getString("name");

                                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.post_opened,false);
                                    Show_full_post show_full_post = new Show_full_post();
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("position", -1);
                                    bundle.putString("title", title);
                                    bundle.putString("body", body);
//                                    bundle.putString("category", categorry);
                                    bundle.putSerializable("time", time);
//                                    bundle.putInt("streak", (int) streak);
                                    bundle.putString("span", span);
                                    bundle.putBoolean("image", false);
                                    bundle.putString("user_id", user_id);
                                    bundle.putSerializable("comments", comments);
                                    bundle.putSerializable("awards", awards);
                                    bundle.putBoolean("is_this_upvoted", up_vote_list.contains(FirebaseAuth.getInstance().getCurrentUser().getUid()));
                                    bundle.putBoolean("is_this_downvote", down_vote_list.contains(FirebaseAuth.getInstance().getCurrentUser().getUid()));
                                    bundle.putString("document_id", document_id);
                                    bundle.putBoolean("is_this_from_firebase", true);
                                    bundle.putSerializable("list_of_upvotes", up_vote_list);
                                    bundle.putSerializable("list_of_downvotes", down_vote_list);
                                    bundle.putBoolean("post_saved", is_this_post_saved_for_saved_comments(document_id));
                                    bundle.putInt("plus_or_minus", 0);
                                    bundle.putBoolean("is_this_loading", false);
                                    bundle.putBoolean("i_already_loaded", false);
                                    bundle.putBoolean("was_upvote_down_vote_clicked", false);
                                    bundle.putBoolean("was_this_reported", reports.contains(document_id));
                                    bundle.putSerializable("list_of_reports", reports);
                                    bundle.putBoolean("am_i_loading_up_vote_down_vote", false);
                                    bundle.putBoolean("did_i_see_this_item", list_of_all_seen_posts_to_pass_for_the_adapter.contains(document_id));
                                    bundle.putSerializable("list_of_seen_posts", list_of_all_seen_posts_to_pass_for_the_adapter);
                                    bundle.putBoolean("m_is_post_by_dev", is_this_dev);
                                    bundle.putString("which_post_called_me", "your_comments");
                                    bundle.putString("name_of_posted", name_of_poster);
                                    bundle.putInt("comment_position", position_of_the_comment);
                                    bundle.putString("what_is_the_type_of_this", "comment_online");
                                    show_full_post.setArguments(bundle);
                                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, show_full_post, "show full post").hide(Posts_fragment.this).commit();
                                } else {
                                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.post_opened,false);
                                    Show_full_post show_full_post = new Show_full_post();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("title", title_of_the_main);
                                    bundle.putString("body", body_of_the_main_post);
//                                    bundle.putString("category", category_of_the_main);
                                    bundle.putSerializable("time", new Date(time_the_main));
//                                    bundle.putInt("streak", (int) streak_of_the_main_post);
                                    bundle.putString("span", span_the_main);
                                    bundle.putString("document_id", document_id);
                                    bundle.putString("which_post_called_me", "your_comments");
                                    bundle.putString("name_of_posted", name_in_the_main);
                                    bundle.putString("what_is_the_type_of_this", "your_post_offline_comment");
                                    bundle.putBoolean("post_saved", is_this_post_saved_for_saved_comments(document_id));

                                    bundle.putString("body_comment", body_of_the_comment);
                                    bundle.putString("name_comment", name_of_the_comment);
                                    bundle.putSerializable("time_comment", new Date(time_of_the_comment));
//                                    bundle.putString("category_comment", category);
                                    bundle.putInt("streak_comment", (int) streak_of_the_comment);
                                    bundle.putInt("position_of_comment", position_of_the_comment);
                                    show_full_post.setArguments(bundle);
                                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, show_full_post, "show full post").hide(Posts_fragment.this).commit();
                                }
                            } else {
                                Toast toast = Toast.makeText(getActivity(), "Cant load data", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }
                    });
                }
            });
            adapter_for_comment_reply.set_card_clicked_reply(new Adapter_for_comment_reply.card_was_clicked_reply() {
                @Override
                public void card_is_clicked_replly(String document_id, String name_in_the_main, String title_of_the_main, String body_of_the_main_post, String span_the_main, long time_the_main, String category_of_the_main, long streak_of_the_main_post, String body_of_the_comment, int position_of_the_comment, String name_of_the_comment, long time_of_the_comment, String category, long streak_of_the_comment, String body_of_the_reply, int position_of_the_reply, String name_of_the_reply, long time_of_the_reply, String category_reply, long streak_of_the_reply) {
                    final DocumentReference docIdRef = firebaseFirestore.collection("posts").document(document_id);
                    docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot snapshot = task.getResult();
                                if (snapshot.exists()) {
                                    String title = snapshot.getString("title");
                                    String body = snapshot.getString("body");
//                                    String categorry = (String) snapshot.get("category");
                                    Date time = snapshot.getTimestamp("time_posted").toDate();
//                                    long streak = (long) snapshot.get("streak");
                                    String span = snapshot.getString("span");
                                    String user_id = (String) snapshot.get("userid");
                                    ArrayList<HashMap<String, Object>> comments = (ArrayList<HashMap<String, Object>>) snapshot.get("comments");
                                    ArrayList<Long> awards = (ArrayList<Long>) snapshot.get("awards");
                                    String document_id = snapshot.getId();
                                    ArrayList<String> up_vote_list = (ArrayList<String>) snapshot.get("up_vote_list");
                                    ArrayList<String> down_vote_list = (ArrayList<String>) snapshot.get("down_vote_list");
                                    ArrayList<String> reports = (ArrayList<String>) snapshot.get("reports");
                                    boolean is_this_dev = (boolean) snapshot.get("dev");
                                    String name_of_poster = snapshot.getString("name");

                                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.post_opened,false);
                                    Show_full_post show_full_post = new Show_full_post();
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("position", -2);
                                    bundle.putString("title", title);
                                    bundle.putString("body", body);
//                                    bundle.putString("category", categorry);
                                    bundle.putSerializable("time", time);
//                                    bundle.putInt("streak", (int) streak);
                                    bundle.putString("span", span);
                                    bundle.putBoolean("image", false);
                                    bundle.putString("user_id", user_id);
                                    bundle.putSerializable("comments", comments);
                                    bundle.putSerializable("awards", awards);
                                    bundle.putBoolean("is_this_upvoted", up_vote_list.contains(FirebaseAuth.getInstance().getCurrentUser().getUid()));
                                    bundle.putBoolean("is_this_downvote", down_vote_list.contains(FirebaseAuth.getInstance().getCurrentUser().getUid()));
                                    bundle.putString("document_id", document_id);
                                    bundle.putBoolean("is_this_from_firebase", true);
                                    bundle.putSerializable("list_of_upvotes", up_vote_list);
                                    bundle.putSerializable("list_of_downvotes", down_vote_list);
                                    bundle.putBoolean("post_saved", is_this_post_saved_for_saved_comments(document_id));
                                    bundle.putInt("plus_or_minus", 0);
                                    bundle.putBoolean("is_this_loading", false);
                                    bundle.putBoolean("i_already_loaded", false);
                                    bundle.putBoolean("was_upvote_down_vote_clicked", false);
                                    bundle.putBoolean("was_this_reported", reports.contains(document_id));
                                    bundle.putSerializable("list_of_reports", reports);
                                    bundle.putBoolean("am_i_loading_up_vote_down_vote", false);
                                    bundle.putBoolean("did_i_see_this_item", list_of_all_seen_posts_to_pass_for_the_adapter.contains(document_id));
                                    bundle.putSerializable("list_of_seen_posts", list_of_all_seen_posts_to_pass_for_the_adapter);
                                    bundle.putBoolean("m_is_post_by_dev", is_this_dev);
                                    bundle.putString("which_post_called_me", "your_comments");
                                    bundle.putString("name_of_posted", name_of_poster);
                                    bundle.putInt("comment_position", position_of_the_comment);
                                    bundle.putInt("reply_position", position_of_the_reply);
                                    bundle.putString("what_is_the_type_of_this", "reply_online");
                                    show_full_post.setArguments(bundle);
                                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, show_full_post, "show full post").hide(Posts_fragment.this).commit();
                                } else {
                                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.post_opened,false);
                                    Show_full_post show_full_post = new Show_full_post();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("title", title_of_the_main);
                                    bundle.putString("body", body_of_the_main_post);
//                                    bundle.putString("category", category_of_the_main);
                                    bundle.putSerializable("time", new Date(time_the_main));
//                                    bundle.putInt("streak", (int) streak_of_the_main_post);
                                    bundle.putString("span", span_the_main);
                                    bundle.putString("document_id", document_id);
                                    bundle.putString("which_post_called_me", "your_comments");
                                    bundle.putString("name_of_posted", name_in_the_main);
                                    bundle.putString("what_is_the_type_of_this", "your_post_offline_reply");
                                    bundle.putBoolean("post_saved", is_this_post_saved_for_saved_comments(document_id));

                                    bundle.putString("body_comment", body_of_the_comment);
                                    bundle.putString("name_comment", name_of_the_comment);
                                    bundle.putSerializable("time_comment", new Date(time_of_the_comment));
//                                    bundle.putString("category_comment", category);
//                                    bundle.putInt("streak_comment", (int) streak_of_the_comment);
                                    bundle.putInt("position_of_comment", position_of_the_comment);

                                    bundle.putString("body_reply", body_of_the_reply);
                                    bundle.putInt("position_of_reply", position_of_the_reply);
                                    bundle.putString("name_reply", name_of_the_reply);
                                    bundle.putSerializable("time_reply", new Date(time_of_the_reply));
//                                    bundle.putString("category_reply", category_reply);
//                                    bundle.putInt("streak_reply", (int) streak_of_the_reply);
                                    show_full_post.setArguments(bundle);
                                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, show_full_post, "show full post").hide(Posts_fragment.this).commit();
                                }
                            } else {
                                Toast toast = Toast.makeText(getActivity(), "Cant load data", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }
                    });
                }
            });
        }
    }

    private void add_stuff_to_comment_reply_recycle_view() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("your_comments", MODE_PRIVATE);
            String comments = sharedPreferences.getString("your_comments", "");
            String[] comment_big_split = comments.split("big_divide");
            for (int i = comment_big_split.length - 1; i >= 0; i--) {
                String[] small_split_comment_reply = comment_big_split[i].split("small_split");
                if (small_split_comment_reply.length == 10) {
                    example_list_for_your_comment_reply.add(new Example_item_for_comments_replies(small_split_comment_reply[0], small_split_comment_reply[1], small_split_comment_reply[2], small_split_comment_reply[3], small_split_comment_reply[4], Long.parseLong(small_split_comment_reply[5]), small_split_comment_reply[6], Integer.parseInt(small_split_comment_reply[7]), small_split_comment_reply[8], Long.parseLong(small_split_comment_reply[9])));
                    adapter_for_comment_reply.notifyDataSetChanged();
                } else if (small_split_comment_reply.length == 14) {
                    example_list_for_your_comment_reply.add(new Example_item_for_comments_replies(small_split_comment_reply[0], small_split_comment_reply[1], small_split_comment_reply[2], small_split_comment_reply[3], small_split_comment_reply[4], Long.parseLong(small_split_comment_reply[5]), small_split_comment_reply[6], Integer.parseInt(small_split_comment_reply[7]), small_split_comment_reply[8], Long.parseLong(small_split_comment_reply[9]), small_split_comment_reply[10], Integer.parseInt(small_split_comment_reply[11]), small_split_comment_reply[12], Long.parseLong(small_split_comment_reply[13])));
                    adapter_for_comment_reply.notifyDataSetChanged();
                }
            }
        }
    }

    private String return_shared(String text) {
        if (getActivity() != null) {
            SharedPreferences sh = getActivity().getSharedPreferences(text, MODE_PRIVATE);
            return sh.getString(text, "");
        } else {
            return "";
        }
    }

    private void saved_posts_is_clicked() {
        if (getView() != null) {
            final FloatingActionButton flaoting_button_to_write_a_post_in_posts = getView().findViewById(R.id.flaoting_button_to_write_a_post_in_posts);
            final View first_line_under_button_in_saved_posts_button_in_posts = getView().findViewById(R.id.first_line_under_button_in_saved_posts_button_in_posts);
            final View second_line_under_button_in_saved_comments_button_in_posts = getView().findViewById(R.id.second_line_under_button_in_saved_comments_button_in_posts);
            first_line_under_button_in_saved_posts_button_in_posts.setVisibility(View.VISIBLE);
            second_line_under_button_in_saved_comments_button_in_posts.setVisibility(View.INVISIBLE);
            which_screen_is_this_on = 3;
            if (!did_you_sit_up_saved_post) {
                did_you_sit_up_saved_post = true;
                set_up_the_recycle_view_saved_posts_real();
            } else {
                example_list_for_saved_post.clear();
                list_of_saved_posts.clear();
                reset_saved_posts();
                adapter_for_saved_post.notifyDataSetChanged();
            }
            add_stuff_to_saved_posts();
            select_the_current_section_user_at(3);
            hide_saved_posts_if_empty();
            flaoting_button_to_write_a_post_in_posts.setVisibility(View.INVISIBLE);
        }
    }

    private void saved_post_and_comments_button_listen() {
        if (getView() != null) {
            Button button_to_see_saved_posts_in_recycle_view_in_posts = getView().findViewById(R.id.button_to_see_saved_posts_in_recycle_view_in_posts);
            Button button_to_see_your_comments_in_recycle_view_in_saved_posts = getView().findViewById(R.id.button_to_see_your_comments_in_recycle_view_in_saved_posts);
            button_to_see_saved_posts_in_recycle_view_in_posts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saved_posts_is_clicked();
                }
            });
            button_to_see_your_comments_in_recycle_view_in_saved_posts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saved_commetns_clicked();
                }
            });
        }
    }

    private void saved_commetns_clicked() {
        if (getView() != null) {
            final RecyclerView recylce_view_to_show_saved_posts = getView().findViewById(R.id.recylce_view_to_show_saved_posts);
            final RecyclerView recylce_view_to_show_saved_comments = getView().findViewById(R.id.recylce_view_to_show_saved_comments);
            final View first_line_under_button_in_saved_posts_button_in_posts = getView().findViewById(R.id.first_line_under_button_in_saved_posts_button_in_posts);
            final View second_line_under_button_in_saved_comments_button_in_posts = getView().findViewById(R.id.second_line_under_button_in_saved_comments_button_in_posts);
            FloatingActionButton flaoting_button_to_write_a_post_in_posts = getView().findViewById(R.id.flaoting_button_to_write_a_post_in_posts);
            flaoting_button_to_write_a_post_in_posts.setVisibility(View.INVISIBLE);
            which_saved_post_comment_is_this_on_real = 1;
            recylce_view_to_show_saved_posts.setVisibility(View.INVISIBLE);
            recylce_view_to_show_saved_comments.setVisibility(View.VISIBLE);
            first_line_under_button_in_saved_posts_button_in_posts.setVisibility(View.INVISIBLE);
            second_line_under_button_in_saved_comments_button_in_posts.setVisibility(View.VISIBLE);
            if (!did_you_setup_saved_comments) {
                did_you_setup_saved_comments = true;
                set_up_saved_comment_posts();
            } else {
                example_list_for_saved_comment_reply.clear();
            }
            add_stuff_to_comment_saved_reply_recycle_view();

            if (getView() != null) {
                if (return_shared("saved_comments").isEmpty()) {
                    TextView saved_post_is_empty_in_posts = getView().findViewById(R.id.saved_post_is_empty_in_posts);
                    TextView written_comments_replies_is_empty_in_save_posts = getView().findViewById(R.id.written_comments_replies_is_empty_in_save_posts);
                    saved_post_is_empty_in_posts.setVisibility(View.INVISIBLE);
                    recylce_view_to_show_saved_posts.setVisibility(View.INVISIBLE);
                    recylce_view_to_show_saved_comments.setVisibility(View.INVISIBLE);
                    written_comments_replies_is_empty_in_save_posts.setVisibility(View.VISIBLE);
                } else {
                    TextView saved_post_is_empty_in_posts = getView().findViewById(R.id.saved_post_is_empty_in_posts);
                    TextView written_comments_replies_is_empty_in_save_posts = getView().findViewById(R.id.written_comments_replies_is_empty_in_save_posts);
                    saved_post_is_empty_in_posts.setVisibility(View.INVISIBLE);
                    recylce_view_to_show_saved_posts.setVisibility(View.INVISIBLE);
                    recylce_view_to_show_saved_comments.setVisibility(View.VISIBLE);
                    written_comments_replies_is_empty_in_save_posts.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void set_up_saved_comment_posts() {
        if (getView() != null && getActivity() != null) {
            reset_your_posts();
            recylce_view_to_show_saved_comments = getView().findViewById(R.id.recylce_view_to_show_saved_comments);
            linearLayoutManager_for_saved_comments = new LinearLayoutManager(getActivity());
            adapter_for_saved_comment_reply = new Adapter_for_comment_reply(example_list_for_saved_comment_reply);
            recylce_view_to_show_saved_comments.setLayoutManager(linearLayoutManager_for_saved_comments);
            recylce_view_to_show_saved_comments.setAdapter(adapter_for_saved_comment_reply);
            recylce_view_to_show_saved_comments.setHasFixedSize(false);
            recylce_view_to_show_saved_comments.setNestedScrollingEnabled(false);
            adapter_for_saved_comment_reply.set_card_clicked_comment(new Adapter_for_comment_reply.card_was_clicked_comment() {
                @Override
                public void card_is_clicked_comment(String document_id, String name_in_the_main, String title_of_the_main, String body_of_the_main_post, String span_the_main, long time_the_main, String category_of_the_main, long streak_of_the_main_post, String body_of_the_comment, final int position_of_the_comment, String name_of_the_comment, long time_of_the_comment, String category, long streak_of_the_comment) {
                    final DocumentReference docIdRef = firebaseFirestore.collection("posts").document(document_id);
                    docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot snapshot = task.getResult();
                                if (snapshot.exists()) {
                                    String title = snapshot.getString("title");
                                    String body = snapshot.getString("body");
//                                    String categorry = (String) snapshot.get("category");
                                    Date time = snapshot.getTimestamp("time_posted").toDate();
//                                    long streak = (long) snapshot.get("streak");
                                    String span = snapshot.getString("span");
                                    String user_id = (String) snapshot.get("userid");
                                    ArrayList<HashMap<String, Object>> comments = (ArrayList<HashMap<String, Object>>) snapshot.get("comments");
                                    ArrayList<Long> awards = (ArrayList<Long>) snapshot.get("awards");
                                    String document_id = snapshot.getId();
                                    ArrayList<String> up_vote_list = (ArrayList<String>) snapshot.get("up_vote_list");
                                    ArrayList<String> down_vote_list = (ArrayList<String>) snapshot.get("down_vote_list");
                                    ArrayList<String> reports = (ArrayList<String>) snapshot.get("reports");
                                    boolean is_this_dev = (boolean) snapshot.get("dev");
                                    String name_of_poster = snapshot.getString("name");

                                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.post_opened,false);
                                    Show_full_post show_full_post = new Show_full_post();
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("position", -1);
                                    bundle.putString("title", title);
                                    bundle.putString("body", body);
//                                    bundle.putString("category", categorry);
                                    bundle.putSerializable("time", time);
//                                    bundle.putInt("streak", (int) streak);
                                    bundle.putString("span", span);
                                    bundle.putBoolean("image", false);
                                    bundle.putString("user_id", user_id);
                                    bundle.putSerializable("comments", comments);
                                    bundle.putSerializable("awards", awards);
                                    bundle.putBoolean("is_this_upvoted", up_vote_list.contains(FirebaseAuth.getInstance().getCurrentUser().getUid()));
                                    bundle.putBoolean("is_this_downvote", down_vote_list.contains(FirebaseAuth.getInstance().getCurrentUser().getUid()));
                                    bundle.putString("document_id", document_id);
                                    bundle.putBoolean("is_this_from_firebase", true);
                                    bundle.putSerializable("list_of_upvotes", up_vote_list);
                                    bundle.putSerializable("list_of_downvotes", down_vote_list);
                                    bundle.putBoolean("post_saved", is_this_post_saved_for_saved_comments(document_id));
                                    bundle.putInt("plus_or_minus", 0);
                                    bundle.putBoolean("is_this_loading", false);
                                    bundle.putBoolean("i_already_loaded", false);
                                    bundle.putBoolean("was_upvote_down_vote_clicked", false);
                                    bundle.putBoolean("was_this_reported", reports.contains(document_id));
                                    bundle.putSerializable("list_of_reports", reports);
                                    bundle.putBoolean("am_i_loading_up_vote_down_vote", false);
                                    bundle.putBoolean("did_i_see_this_item", list_of_all_seen_posts_to_pass_for_the_adapter.contains(document_id));
                                    bundle.putSerializable("list_of_seen_posts", list_of_all_seen_posts_to_pass_for_the_adapter);
                                    bundle.putBoolean("m_is_post_by_dev", is_this_dev);
                                    bundle.putString("which_post_called_me", "saved_comments");
                                    bundle.putString("name_of_posted", name_of_poster);
                                    bundle.putInt("comment_position", position_of_the_comment);
                                    bundle.putString("what_is_the_type_of_this", "comment_online");
                                    show_full_post.setArguments(bundle);
                                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, show_full_post, "show full post").hide(Posts_fragment.this).commit();
                                } else {
                              /*  String title = small_split[0];
                                String body = small_split[1];
                                String span = small_split[2];
                                Date time = new Date(Long.parseLong(small_split[3]));
                                String category = small_split[4];
                                long streak = Long.parseLong(small_split[5]);
                                example_list_for_your_post.add(new Example_item_feed_posts(title, body, streak, false, category, time, null, span, null, null, null, false, null, null, firebaseUser, false, firebaseFirestore, null, null, false));
                                adapter_for_your_post.notifyDataSetChanged();
                                int copy_of_i = recursion;
                                copy_of_i--;
                                index_to_start_your_posts++;
                                wait_for_result_for_saved_post(old, copy_of_i);*/
                                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.post_opened,false);
                                    Show_full_post show_full_post = new Show_full_post();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("title", title_of_the_main);
                                    bundle.putString("body", body_of_the_main_post);
//                                    bundle.putString("category", category_of_the_main);
                                    bundle.putSerializable("time", new Date(time_the_main));
                                    bundle.putInt("streak", (int) streak_of_the_main_post);
                                    bundle.putString("span", span_the_main);
                                    bundle.putString("document_id", document_id);
                                    bundle.putString("which_post_called_me", "saved_comments");
                                    bundle.putString("name_of_posted", name_in_the_main);
                                    bundle.putString("what_is_the_type_of_this", "saved_post_offline_comment");
                                    bundle.putBoolean("post_saved", is_this_post_saved_for_saved_comments(document_id));

                                    bundle.putString("body_comment", body_of_the_comment);
                                    bundle.putString("name_comment", name_of_the_comment);
                                    bundle.putSerializable("time_comment", new Date(time_of_the_comment));
//                                    bundle.putString("category_comment", category);
//                                    bundle.putInt("streak_comment", (int) streak_of_the_comment);
                                    bundle.putInt("position_of_comment", position_of_the_comment);
                                    show_full_post.setArguments(bundle);
                                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, show_full_post, "show full post").hide(Posts_fragment.this).commit();

                                }
                            } else {
                                Toast toast = Toast.makeText(getActivity(), "Cant load data", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }
                    });
                }
            });
            adapter_for_saved_comment_reply.set_card_clicked_reply(new Adapter_for_comment_reply.card_was_clicked_reply() {
                @Override
                public void card_is_clicked_replly(String document_id, String name_in_the_main, String title_of_the_main, String body_of_the_main_post, String span_the_main, long time_the_main, String category_of_the_main, long streak_of_the_main_post, String body_of_the_comment, final int position_of_the_comment, String name_of_the_comment, long time_of_the_comment, String category, long streak_of_the_comment, String body_of_the_reply, final int position_of_the_reply, String name_of_the_reply, long time_of_the_reply, String category_reply, long streak_of_the_reply) {
                    final DocumentReference docIdRef = firebaseFirestore.collection("posts").document(document_id);
                    docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot snapshot = task.getResult();
                                if (snapshot.exists()) {
                                    String title = snapshot.getString("title");
                                    String body = snapshot.getString("body");
//                                    String categorry = (String) snapshot.get("category");
                                    Date time = snapshot.getTimestamp("time_posted").toDate();
//                                    long streak = (long) snapshot.get("streak");
                                    String span = snapshot.getString("span");
                                    String user_id = (String) snapshot.get("userid");
                                    ArrayList<HashMap<String, Object>> comments = (ArrayList<HashMap<String, Object>>) snapshot.get("comments");
                                    ArrayList<Long> awards = (ArrayList<Long>) snapshot.get("awards");
                                    String document_id = snapshot.getId();
                                    ArrayList<String> up_vote_list = (ArrayList<String>) snapshot.get("up_vote_list");
                                    ArrayList<String> down_vote_list = (ArrayList<String>) snapshot.get("down_vote_list");
                                    ArrayList<String> reports = (ArrayList<String>) snapshot.get("reports");
                                    boolean is_this_dev = (boolean) snapshot.get("dev");
                                    String name_of_poster = snapshot.getString("name");

                                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.post_opened,false);
                                    Show_full_post show_full_post = new Show_full_post();
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("position", -2);
                                    bundle.putString("title", title);
                                    bundle.putString("body", body);
//                                    bundle.putString("category", categorry);
                                    bundle.putSerializable("time", time);
//                                    bundle.putInt("streak", (int) streak);
                                    bundle.putString("span", span);
                                    bundle.putBoolean("image", false);
                                    bundle.putString("user_id", user_id);
                                    bundle.putSerializable("comments", comments);
                                    bundle.putSerializable("awards", awards);
                                    bundle.putBoolean("is_this_upvoted", up_vote_list.contains(FirebaseAuth.getInstance().getCurrentUser().getUid()));
                                    bundle.putBoolean("is_this_downvote", down_vote_list.contains(FirebaseAuth.getInstance().getCurrentUser().getUid()));
                                    bundle.putString("document_id", document_id);
                                    bundle.putBoolean("is_this_from_firebase", true);
                                    bundle.putSerializable("list_of_upvotes", up_vote_list);
                                    bundle.putSerializable("list_of_downvotes", down_vote_list);
                                    bundle.putBoolean("post_saved", is_this_post_saved_for_saved_comments(document_id));
                                    bundle.putInt("plus_or_minus", 0);
                                    bundle.putBoolean("is_this_loading", false);
                                    bundle.putBoolean("i_already_loaded", false);
                                    bundle.putBoolean("was_upvote_down_vote_clicked", false);
                                    bundle.putBoolean("was_this_reported", reports.contains(document_id));
                                    bundle.putSerializable("list_of_reports", reports);
                                    bundle.putBoolean("am_i_loading_up_vote_down_vote", false);
                                    bundle.putBoolean("did_i_see_this_item", list_of_all_seen_posts_to_pass_for_the_adapter.contains(document_id));
                                    bundle.putSerializable("list_of_seen_posts", list_of_all_seen_posts_to_pass_for_the_adapter);
                                    bundle.putBoolean("m_is_post_by_dev", is_this_dev);
                                    bundle.putString("which_post_called_me", "saved_comments");
                                    bundle.putString("name_of_posted", name_of_poster);
                                    bundle.putInt("comment_position", position_of_the_comment);
                                    bundle.putInt("reply_position", position_of_the_reply);
                                    bundle.putString("what_is_the_type_of_this", "reply_online");
                                    show_full_post.setArguments(bundle);
                                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, show_full_post, "show full post").hide(Posts_fragment.this).commit();
                                } else {
                                    Event_manager_all_in_one.getInstance().record_fire_base_event(getContext(), Event_manager_all_in_one.Event_type_fire_base_record.post_opened,false);
                                    Show_full_post show_full_post = new Show_full_post();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("title", title_of_the_main);
                                    bundle.putString("body", body_of_the_main_post);
//                                    bundle.putString("category", category_of_the_main);
                                    bundle.putSerializable("time", new Date(time_the_main));
//                                    bundle.putInt("streak", (int) streak_of_the_main_post);
                                    bundle.putString("span", span_the_main);
                                    bundle.putString("document_id", document_id);
                                    bundle.putString("which_post_called_me", "saved_comments");
                                    bundle.putString("name_of_posted", name_in_the_main);
                                    bundle.putString("what_is_the_type_of_this", "saved_post_offline_reply");
                                    bundle.putBoolean("post_saved", is_this_post_saved_for_saved_comments(document_id));

                                    bundle.putString("body_comment", body_of_the_comment);
                                    bundle.putString("name_comment", name_of_the_comment);
                                    bundle.putSerializable("time_comment", new Date(time_of_the_comment));
//                                    bundle.putString("category_comment", category);
//                                    bundle.putInt("streak_comment", (int) streak_of_the_comment);
                                    bundle.putInt("position_of_comment", position_of_the_comment);

                                    bundle.putString("body_reply", body_of_the_reply);
                                    bundle.putInt("position_of_reply", position_of_the_reply);
                                    bundle.putString("name_reply", name_of_the_reply);
                                    bundle.putSerializable("time_reply", new Date(time_of_the_reply));
//                                    bundle.putString("category_reply", category_reply);
//                                    bundle.putInt("streak_reply", (int) streak_of_the_reply);
                                    show_full_post.setArguments(bundle);
                                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, show_full_post, "show full post").hide(Posts_fragment.this).commit();
                                }
                            } else {
                                Toast toast = Toast.makeText(getActivity(), "Cant load data", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }
                    });
                }
            });
        }
    }

    private void add_stuff_to_comment_saved_reply_recycle_view() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("saved_comments", MODE_PRIVATE);
            String comments = sharedPreferences.getString("saved_comments", "");
            String[] comment_big_split = comments.split("big_divide");
            for (int i = comment_big_split.length - 1; i >= 0; i--) {
                String[] comment_small_split = comment_big_split[i].split("small_split");
                if (comment_small_split.length == 10) {
                    example_list_for_saved_comment_reply.add(new Example_item_for_comments_replies(comment_small_split[0], comment_small_split[1], comment_small_split[2], comment_small_split[3], comment_small_split[4], Long.parseLong(comment_small_split[5]), comment_small_split[6], Integer.parseInt(comment_small_split[7]), comment_small_split[8], Long.parseLong(comment_small_split[9])));
                    adapter_for_saved_comment_reply.notifyDataSetChanged();
                } else if (comment_small_split.length == 14) {
                    example_list_for_saved_comment_reply.add(new Example_item_for_comments_replies(comment_small_split[0], comment_small_split[1], comment_small_split[2], comment_small_split[3], comment_small_split[4], Long.parseLong(comment_small_split[5]), comment_small_split[6], Integer.parseInt(comment_small_split[7]), comment_small_split[8], Long.parseLong(comment_small_split[9]), comment_small_split[10], Integer.parseInt(comment_small_split[11]), comment_small_split[12], Long.parseLong(comment_small_split[13])));
                    adapter_for_saved_comment_reply.notifyDataSetChanged();
                }
            }
        }
    }

    private boolean is_this_post_saved_for_saved_comments(String document_id) {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("saved_posts", MODE_PRIVATE);
            String posts = sharedPreferences.getString("posts", "");
            if (posts.equals("")) {
                return false;
            } // return false if it is empty
            String[] posts_big_split = posts.split("big_divide");
            for (int i = 0; i < posts_big_split.length; i++) {
                String[] small_split = posts_big_split[i].split("small_split");
                if (small_split[5].equals(document_id)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }
    /*private void restart_the_premium_and_call_it(){
        ((after_login)getActivity()).check_if_user_is_gifted(true);
    }*/

    private void add_information_to_array_list() {
        blocked_user_ids.clear();
        hidden_document_ids.clear();
        String blocked_user_ids_string = Save_and_get.getInstance().get_this(getContext(), "blocked_users", "blocked_users");
        String hidden_post_id_string = Save_and_get.getInstance().get_this(getContext(), "hide_posts", "hide_posts");
        if (!blocked_user_ids_string.equals("")) {
            String[] blocked_user_ids_array = blocked_user_ids_string.split(Save_and_get.getInstance().return_split_keyword());
            blocked_user_ids.addAll(Arrays.asList(blocked_user_ids_array));
        }
        if (!hidden_post_id_string.equals("")) {
            String[] hidden_post_id_array = hidden_post_id_string.split(Save_and_get.getInstance().return_split_keyword());
            hidden_document_ids.addAll(Arrays.asList(hidden_post_id_array));
        }
    }

    private boolean should_i_show_this_post(String user_id, String document_id) {
        if (!blocked_user_ids.contains(user_id) && !hidden_document_ids.contains(document_id)) {
            return true;
        } else {
            return false;
        }
    }

    private void hide_your_posts_if_empty() {
        if (getView() != null) {
            final RecyclerView recylce_view_to_show_your_posts = getView().findViewById(R.id.recylce_view_to_show_your_posts);
            final RecyclerView recylce_view_to_show_your_comments = getView().findViewById(R.id.recylce_view_to_show_your_comments);
            if (is_shared_empty("posted")) {
                TextView written_post_is_empty_in_posts = getView().findViewById(R.id.written_post_is_empty_in_posts);
                TextView written_comments_replies_is_empty_in_posts = getView().findViewById(R.id.written_comments_replies_is_empty_in_posts);
                written_comments_replies_is_empty_in_posts.setVisibility(View.INVISIBLE);
                written_post_is_empty_in_posts.setVisibility(View.VISIBLE);
                recylce_view_to_show_your_posts.setVisibility(View.INVISIBLE);
                recylce_view_to_show_your_comments.setVisibility(View.INVISIBLE);
            } else {
                TextView written_post_is_empty_in_posts = getView().findViewById(R.id.written_post_is_empty_in_posts);
                TextView written_comments_replies_is_empty_in_posts = getView().findViewById(R.id.written_comments_replies_is_empty_in_posts);
                written_comments_replies_is_empty_in_posts.setVisibility(View.INVISIBLE);
                written_post_is_empty_in_posts.setVisibility(View.INVISIBLE);
                recylce_view_to_show_your_posts.setVisibility(View.VISIBLE);
                recylce_view_to_show_your_comments.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void hide_saved_posts_if_empty() {
        if (getView() != null) {
            if (is_shared_empty("saved")) {
                TextView saved_post_is_empty_in_posts = getView().findViewById(R.id.saved_post_is_empty_in_posts);
                TextView written_comments_replies_is_empty_in_save_posts = getView().findViewById(R.id.written_comments_replies_is_empty_in_save_posts);
                RecyclerView recylce_view_to_show_saved_posts = getView().findViewById(R.id.recylce_view_to_show_saved_posts);
                RecyclerView recylce_view_to_show_saved_comments = getView().findViewById(R.id.recylce_view_to_show_saved_comments);
                saved_post_is_empty_in_posts.setVisibility(View.VISIBLE);
                recylce_view_to_show_saved_posts.setVisibility(View.INVISIBLE);
                written_comments_replies_is_empty_in_save_posts.setVisibility(View.INVISIBLE);
                recylce_view_to_show_saved_comments.setVisibility(View.INVISIBLE);
            } else {
                TextView saved_post_is_empty_in_posts = getView().findViewById(R.id.saved_post_is_empty_in_posts);
                TextView written_comments_replies_is_empty_in_save_posts = getView().findViewById(R.id.written_comments_replies_is_empty_in_save_posts);
                RecyclerView recylce_view_to_show_saved_posts = getView().findViewById(R.id.recylce_view_to_show_saved_posts);
                RecyclerView recylce_view_to_show_saved_comments = getView().findViewById(R.id.recylce_view_to_show_saved_comments);
                saved_post_is_empty_in_posts.setVisibility(View.INVISIBLE);
                recylce_view_to_show_saved_posts.setVisibility(View.VISIBLE);
                written_comments_replies_is_empty_in_save_posts.setVisibility(View.INVISIBLE);
                recylce_view_to_show_saved_comments.setVisibility(View.INVISIBLE);
            }
        }
    }
}