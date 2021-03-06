package com.easyhabitsapp.android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static android.content.Context.MODE_PRIVATE;

public class chat_fragment extends Fragment {
    private Handler handler = new Handler();
    private Handler handler_for_five_minute_time = new Handler();
    private Handler handler_for_start_button_listen = new Handler();
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private int room_int = 1;
    private int which_user = 0;
    private RecyclerView recycle_view_for_chat_in_chat_fragment;
    private LinearLayoutManager linearLayoutManager;
    private Item_for_chat_adapter adapter;
    private ArrayList<Item_for_chat> chat_list = new ArrayList<>();
    private ChildEventListener childEventListener_child_1 = null;
    private ChildEventListener childEventListener_child_2 = null;
    private ValueEventListener valueEventListener = null;
    private long five_minute_time = 300000;
    private boolean user_removed = false;
    private String spinner_item;
    private boolean is_alert_showing = false;
    private String other_person_firebase = null;
    private boolean am_i_called_first_child_listener;
    private FirebaseFunctions firebaseFunctions;
    private boolean is_the_chat_still_going = false;
    private boolean was_this_reported = false;
    private FirebaseFirestore m_firebaseFirestore = FirebaseFirestore.getInstance();
    private boolean am_i_in_gift = false;
    private boolean did_i_leave_chat = false;
    private FirebaseAuth mAuth;
    private boolean am_i_signed_in = false;
    private boolean started_login_process = false;
    private FirebaseAuth.AuthStateListener authStateListener;
    private int which_screen_i_am_in = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //auth_changed_listener();
        mAuth = FirebaseAuth.getInstance();
        //mAuth.addAuthStateListener(authStateListener);
        set_up_recycle();
        color_the_text_at_the_top();
        if (last_time_chatted() != 0) {
            setHandler_for_start_button_listen();
        }
        start_session_button_listen();
        send_button_chat_listen();
        choose_chat_topic();
        message_text_wwatcher();
        back_button_listen();
        recycle_view_listen_scroll();
        three_dot_listen();
        gift_button_listen();
        back_button_listen_chat();
    }

    private void join_a_chat_room() {
        if (getActivity() != null) {
            final String habit_name = spinner_item;
            database.child(habit_name).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NotNull DataSnapshot snapshot) {
                    if (snapshot.getValue() == null) {
                        HashMap<String, Object> children = new HashMap<>();
                        children.put("user_1", "____messeges_for_chat_app____");
                        children.put("user_1_id", FirebaseAuth.getInstance().getCurrentUser().getUid());
                        database.child(habit_name).child("chat_room_1").updateChildren(children).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                which_user = 1;
                                if (childEventListener_child_1 == null) {
                                    first_child_listener();
                                    database.child(habit_name).child("chat_room_1").addChildEventListener(childEventListener_child_1);
                                }
                                is_the_chat_still_going = true;
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                room_int = room_int + 1;
                                join_a_chat_room();
                            }
                        });
                    } else {
                        database.child(habit_name).child("chat_room_".concat(String.valueOf(room_int))).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NotNull DataSnapshot snapshot2) {
                                if (snapshot2.getValue() == null) {
                                    HashMap<String, Object> children = new HashMap<>();
                                    children.put("user_1", "____messeges_for_chat_app____");
                                    children.put("user_1_id", FirebaseAuth.getInstance().getCurrentUser().getUid());
                                    database.child(habit_name).child("chat_room_".concat(String.valueOf(room_int))).updateChildren(children).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            which_user = 1;
                                            if (childEventListener_child_1 == null) {
                                                first_child_listener();
                                                database.child(habit_name).child("chat_room_".concat(String.valueOf(room_int))).addChildEventListener(childEventListener_child_1);
                                            }
                                            is_the_chat_still_going = true;
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull @NotNull Exception e) {
                                            room_int = room_int + 1;
                                            join_a_chat_room();
                                        }
                                    });
                                } else {
                                    database.child(habit_name).child("chat_room_".concat(String.valueOf(room_int))).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.getChildrenCount() == 2) {
                                                HashMap<String, Object> children = new HashMap<>();
                                                children.put("user_2", "____messeges_for_chat_app____");
                                                children.put("user_2_id", FirebaseAuth.getInstance().getCurrentUser().getUid());
                                                children.put("time", ServerValue.TIMESTAMP);
                                                database.child(habit_name).child("chat_room_".concat(String.valueOf(room_int))).updateChildren(children).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        put_the_last_time_firebase();
                                                        which_user = 2;
                                                        open_a_screen(2);
                                                        handler.removeCallbacksAndMessages(null);
                                                        other_person_chat_listener();
                                                        if (childEventListener_child_2 == null) {
                                                            second_child_listener();
                                                            database.child(habit_name).child("chat_room_".concat(String.valueOf(room_int))).addChildEventListener(childEventListener_child_2);
                                                        }
                                                        start_the_five_minute_time();
                                                        set_the_fire_base_of_other_person();
                                                        write_chat_now();
                                                        is_the_chat_still_going = true;
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull @NotNull Exception e) {
                                                        room_int = room_int + 1;
                                                        join_a_chat_room();
                                                    }
                                                });
                                            } else {
                                                room_int = room_int + 1;
                                                join_a_chat_room();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NotNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!am_i_in_gift) {
            leave_chat();
        }
    }

    private void color_the_text_at_the_top() {
        if (getActivity() != null) {
            TextView text_saying_start_session = getActivity().findViewById(R.id.text_saying_start_session);
            Spannable spannable = new SpannableString("Get to chat with a random fellow user!!");
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 19, text_saying_start_session.getText().toString().length() - 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text_saying_start_session.setText(spannable);
        }
    }

    private void start_session_button_listen() {
        if (getActivity() != null) {
            final Button button_to_start_seesion_in_chat = getActivity().findViewById(R.id.button_to_start_seesion_in_chat);
            final TextView searching_for_a_user_text_in_chat = getActivity().findViewById(R.id.searching_for_a_user_text_in_chat);
            final Spinner spinner_to_choose_chat_to_talk_about = getActivity().findViewById(R.id.spinner_to_choose_chat_to_talk_about);
            button_to_start_seesion_in_chat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (how_is_user_logged_in().equals("google")) {
                        if (button_to_start_seesion_in_chat.getText().toString().equals("Start Session")) {
                            spinner_item = spinner_to_choose_chat_to_talk_about.getSelectedItem().toString();
                            open_a_screen(0);
                            call_the_method_every_second();
                            button_to_start_seesion_in_chat.setText("Stop Searching");
                            up_date_function_if_pro();
                        } else if (button_to_start_seesion_in_chat.getText().toString().equals("Stop Searching")) {
                            database.child(spinner_item).child("chat_room_".concat(String.valueOf(room_int))).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    open_a_screen(1);
                                    button_to_start_seesion_in_chat.setText("Start Session");
                                    handler.removeCallbacksAndMessages(null);
                                    searching_for_a_user_text_in_chat.setText("Searching for a user");
                                    restart_the_values();
                                }
                            });
                        } else if (button_to_start_seesion_in_chat.getText().toString().contains("Next free session:")) {
                            Buy_premuim buy_premuim = new Buy_premuim("Free users are only allowed one free session per day. Subscribe to premium and enjoy unlimited daily chat sessions", false, "chat");
                            chat_fragment old_fragment = (chat_fragment) getActivity().getSupportFragmentManager().findFragmentByTag("chat");
                            if (old_fragment != null) {
                                getActivity().getSupportFragmentManager().beginTransaction().hide(old_fragment).add(R.id.fragment_container, buy_premuim, "buy premium").show(buy_premuim).commit();
                            }
                        }
                    } else {
                        if (started_login_process) {
                            Toast.makeText(getActivity(), "Please wait you are being signed in", Toast.LENGTH_LONG).show();
                        } else {
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Sign in with google")
                                    .setMessage("You have to sign in with google in order to be able to chat with others")

                                    // Specifying a listener allows you to take an action before dismissing the dialog.
                                    // The dialog is automatically dismissed when a dialog button is clicked.
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            Bottom_sheet_to_sing_in_to_write_posts bottom_sheet_to_sing_in_to_write_posts = new Bottom_sheet_to_sing_in_to_write_posts();
                                            bottom_sheet_to_sing_in_to_write_posts.setTargetFragment(chat_fragment.this, 254);
                                            bottom_sheet_to_sing_in_to_write_posts.show(getActivity().getSupportFragmentManager(), "tag");
                                        }
                                    }).setNegativeButton("cancel", null)
                                    .show();
                        }
                    }
                }
            });
        }
    }

    private void open_a_screen(int which) {
        if (getActivity() != null) {
            TextView text_saying_start_session = getActivity().findViewById(R.id.text_saying_start_session);
            View horizantal_straight_line_in_top = getActivity().findViewById(R.id.horizantal_straight_line_in_top);
            View chat_get_example_in_chat = getActivity().findViewById(R.id.chat_get_example_in_chat);
            TextView text_saying_start_example_hello = getActivity().findViewById(R.id.text_saying_start_example_hello);
            View chat_send_example_in_chat = getActivity().findViewById(R.id.chat_send_example_in_chat);
            TextView text_saying_secomd_example_hey = getActivity().findViewById(R.id.text_saying_secomd_example_hey);
            TextView searching_for_a_user_text_in_chat = getActivity().findViewById(R.id.searching_for_a_user_text_in_chat);
            View horizantal_straight_line_in_bottom = getActivity().findViewById(R.id.horizantal_straight_line_in_bottom);
            View rotating_glass_in_chat_loading = getActivity().findViewById(R.id.rotating_glass_in_chat_loading);
            Button button_to_start_seesion_in_chat = getActivity().findViewById(R.id.button_to_start_seesion_in_chat);
            TextView text_view_saying_messages_are_not_stored_chat = getActivity().findViewById(R.id.text_view_saying_messages_are_not_stored_chat);
            View view_at_the_top_in_chat_of_chat = getActivity().findViewById(R.id.view_at_the_top_in_chat_of_chat);
            View view_at_the_bottom_in_chat_of_chat = getActivity().findViewById(R.id.view_at_the_bottom_in_chat_of_chat);
            View send_view_circle_white_chat = getActivity().findViewById(R.id.send_view_circle_white_chat);
            View bottom_of_color_fav_above_white_chat = getActivity().findViewById(R.id.bottom_of_color_fav_above_white_chat);
            View text_box_for_edit_box = getActivity().findViewById(R.id.text_box_for_edit_box);
            Button ripple_send_button_chat = getActivity().findViewById(R.id.ripple_send_button_chat);
            View send_button_in_chat_white = getActivity().findViewById(R.id.send_button_in_chat_white);
            EditText edit_text_to_enter_chat_t_send_messages = getActivity().findViewById(R.id.edit_text_to_enter_chat_t_send_messages);
            TextView text_telling_the_user_to_choose_a_habit = getActivity().findViewById(R.id.text_telling_the_user_to_choose_a_habit);
            Spinner spinner_to_choose_chat_to_talk_about = getActivity().findViewById(R.id.spinner_to_choose_chat_to_talk_about);
            TextView text_telling_user_they_can_change_spinner = getActivity().findViewById(R.id.text_telling_user_they_can_change_spinner);
            RecyclerView recycle_view_for_chat_in_chat_fragment = getActivity().findViewById(R.id.recycle_view_for_chat_in_chat_fragment);
            TextView text_view_saying_be_nice_to_the_start = getActivity().findViewById(R.id.text_view_saying_be_nice_to_the_start);
            View box_around_spinner_chat = getActivity().findViewById(R.id.box_around_spinner_chat);
            Button three_dot_button_listen_in_chat_to_view_more_things = getActivity().findViewById(R.id.three_dot_button_listen_in_chat_to_view_more_things);
            View three_dot_id_in_chat_to_view_more_things = getActivity().findViewById(R.id.three_dot_id_in_chat_to_view_more_things);
            Button button_to_gift_in_chat_real_time = getActivity().findViewById(R.id.button_to_gift_in_chat_real_time);
            Button button_to_back_in_chat_visible_after_chat = getActivity().findViewById(R.id.button_to_back_in_chat_visible_after_chat);
            View back_view_white_in_chat = getActivity().findViewById(R.id.back_view_white_in_chat);
            if (which == 0) {
                view_at_the_top_in_chat_of_chat.setVisibility(View.INVISIBLE);
                view_at_the_bottom_in_chat_of_chat.setVisibility(View.INVISIBLE);
                send_view_circle_white_chat.setVisibility(View.INVISIBLE);
                bottom_of_color_fav_above_white_chat.setVisibility(View.INVISIBLE);
                text_box_for_edit_box.setVisibility(View.INVISIBLE);
                ripple_send_button_chat.setVisibility(View.INVISIBLE);
                send_button_in_chat_white.setVisibility(View.INVISIBLE);
                edit_text_to_enter_chat_t_send_messages.setVisibility(View.INVISIBLE);
                recycle_view_for_chat_in_chat_fragment.setVisibility(View.INVISIBLE);
                text_saying_start_session.setVisibility(View.INVISIBLE);
                horizantal_straight_line_in_top.setVisibility(View.INVISIBLE);
                chat_get_example_in_chat.setVisibility(View.INVISIBLE);
                text_saying_start_example_hello.setVisibility(View.INVISIBLE);
                chat_send_example_in_chat.setVisibility(View.INVISIBLE);
                text_saying_secomd_example_hey.setVisibility(View.INVISIBLE);
                horizantal_straight_line_in_bottom.setVisibility(View.INVISIBLE);
                button_to_start_seesion_in_chat.setVisibility(View.VISIBLE);
                text_view_saying_messages_are_not_stored_chat.setVisibility(View.INVISIBLE);
                text_telling_the_user_to_choose_a_habit.setVisibility(View.INVISIBLE);
                spinner_to_choose_chat_to_talk_about.setVisibility(View.INVISIBLE);
                box_around_spinner_chat.setVisibility(View.INVISIBLE);
                text_telling_user_they_can_change_spinner.setVisibility(View.INVISIBLE);
                searching_for_a_user_text_in_chat.setVisibility(View.VISIBLE);
                rotating_glass_in_chat_loading.setVisibility(View.VISIBLE);
                text_view_saying_be_nice_to_the_start.setVisibility(View.INVISIBLE);
                three_dot_button_listen_in_chat_to_view_more_things.setVisibility(View.INVISIBLE);
                three_dot_id_in_chat_to_view_more_things.setVisibility(View.INVISIBLE);
                button_to_gift_in_chat_real_time.setVisibility(View.INVISIBLE);
                button_to_back_in_chat_visible_after_chat.setVisibility(View.INVISIBLE);
                back_view_white_in_chat.setVisibility(View.INVISIBLE);
                which_screen_i_am_in = 0;
            } else if (which == 1) {
                view_at_the_top_in_chat_of_chat.setVisibility(View.INVISIBLE);
                view_at_the_bottom_in_chat_of_chat.setVisibility(View.INVISIBLE);
                send_view_circle_white_chat.setVisibility(View.INVISIBLE);
                bottom_of_color_fav_above_white_chat.setVisibility(View.INVISIBLE);
                text_box_for_edit_box.setVisibility(View.INVISIBLE);
                ripple_send_button_chat.setVisibility(View.INVISIBLE);
                send_button_in_chat_white.setVisibility(View.INVISIBLE);
                edit_text_to_enter_chat_t_send_messages.setVisibility(View.INVISIBLE);
                recycle_view_for_chat_in_chat_fragment.setVisibility(View.INVISIBLE);
                text_saying_start_session.setVisibility(View.VISIBLE);
                horizantal_straight_line_in_top.setVisibility(View.VISIBLE);
                chat_get_example_in_chat.setVisibility(View.VISIBLE);
                text_saying_start_example_hello.setVisibility(View.VISIBLE);
                chat_send_example_in_chat.setVisibility(View.VISIBLE);
                text_saying_secomd_example_hey.setVisibility(View.VISIBLE);
                horizantal_straight_line_in_bottom.setVisibility(View.VISIBLE);
                button_to_start_seesion_in_chat.setVisibility(View.VISIBLE);
                text_view_saying_messages_are_not_stored_chat.setVisibility(View.VISIBLE);
                text_telling_the_user_to_choose_a_habit.setVisibility(View.VISIBLE);
                spinner_to_choose_chat_to_talk_about.setVisibility(View.VISIBLE);
                box_around_spinner_chat.setVisibility(View.VISIBLE);
                text_telling_user_they_can_change_spinner.setVisibility(View.VISIBLE);
                searching_for_a_user_text_in_chat.setVisibility(View.INVISIBLE);
                rotating_glass_in_chat_loading.setVisibility(View.INVISIBLE);
                text_view_saying_be_nice_to_the_start.setVisibility(View.INVISIBLE);
                three_dot_button_listen_in_chat_to_view_more_things.setVisibility(View.INVISIBLE);
                three_dot_id_in_chat_to_view_more_things.setVisibility(View.INVISIBLE);
                button_to_gift_in_chat_real_time.setVisibility(View.INVISIBLE);
                button_to_back_in_chat_visible_after_chat.setVisibility(View.INVISIBLE);
                back_view_white_in_chat.setVisibility(View.INVISIBLE);
                which_screen_i_am_in = 1;
            } else if (which == 2) {
                view_at_the_top_in_chat_of_chat.setVisibility(View.VISIBLE);
                view_at_the_bottom_in_chat_of_chat.setVisibility(View.VISIBLE);
                send_view_circle_white_chat.setVisibility(View.VISIBLE);
                bottom_of_color_fav_above_white_chat.setVisibility(View.VISIBLE);
                text_box_for_edit_box.setVisibility(View.VISIBLE);
                ripple_send_button_chat.setVisibility(View.VISIBLE);
                send_button_in_chat_white.setVisibility(View.VISIBLE);
                edit_text_to_enter_chat_t_send_messages.setVisibility(View.VISIBLE);
                recycle_view_for_chat_in_chat_fragment.setVisibility(View.VISIBLE);
                text_saying_start_session.setVisibility(View.INVISIBLE);
                horizantal_straight_line_in_top.setVisibility(View.INVISIBLE);
                chat_get_example_in_chat.setVisibility(View.INVISIBLE);
                text_saying_start_example_hello.setVisibility(View.INVISIBLE);
                chat_send_example_in_chat.setVisibility(View.INVISIBLE);
                text_saying_secomd_example_hey.setVisibility(View.INVISIBLE);
                horizantal_straight_line_in_bottom.setVisibility(View.INVISIBLE);
                button_to_start_seesion_in_chat.setVisibility(View.INVISIBLE);
                text_view_saying_messages_are_not_stored_chat.setVisibility(View.INVISIBLE);
                text_telling_the_user_to_choose_a_habit.setVisibility(View.INVISIBLE);
                spinner_to_choose_chat_to_talk_about.setVisibility(View.INVISIBLE);
                box_around_spinner_chat.setVisibility(View.INVISIBLE);
                text_telling_user_they_can_change_spinner.setVisibility(View.INVISIBLE);
                searching_for_a_user_text_in_chat.setVisibility(View.INVISIBLE);
                rotating_glass_in_chat_loading.setVisibility(View.INVISIBLE);
                text_view_saying_be_nice_to_the_start.setVisibility(View.VISIBLE);
                three_dot_button_listen_in_chat_to_view_more_things.setVisibility(View.VISIBLE);
                three_dot_id_in_chat_to_view_more_things.setVisibility(View.VISIBLE);
                button_to_gift_in_chat_real_time.setVisibility(View.VISIBLE);
                button_to_back_in_chat_visible_after_chat.setVisibility(View.INVISIBLE);
                back_view_white_in_chat.setVisibility(View.INVISIBLE);
                which_screen_i_am_in = 2;
            }
        }
    }

    private void call_the_method_every_second() {
        handler.postDelayed(new Runnable() {
            public void run() {
                add_the_dots();
                handler.postDelayed(this, 500);

            }
        }, 500);
    }

    private void add_the_dots() {
        if (getActivity() != null) {
            TextView searching_for_a_user_text_in_chat = getActivity().findViewById(R.id.searching_for_a_user_text_in_chat);
            if (searching_for_a_user_text_in_chat.getText().toString().equals("Searching for a user")) {
                searching_for_a_user_text_in_chat.setText("Searching for a user.");
            } else if (searching_for_a_user_text_in_chat.getText().toString().equals("Searching for a user.")) {
                searching_for_a_user_text_in_chat.setText("Searching for a user..");
            } else if (searching_for_a_user_text_in_chat.getText().toString().equals("Searching for a user..")) {
                searching_for_a_user_text_in_chat.setText("Searching for a user...");
            } else if (searching_for_a_user_text_in_chat.getText().toString().equals("Searching for a user...")) {
                searching_for_a_user_text_in_chat.setText("Searching for a user");
            }
        }
    }

    private void send_button_chat_listen() {
        if (getActivity() != null) {
            Button ripple_send_button_chat = getActivity().findViewById(R.id.ripple_send_button_chat);
            final EditText edit_text_to_enter_chat_t_send_messages = getActivity().findViewById(R.id.edit_text_to_enter_chat_t_send_messages);
            final Spinner spinner_to_choose_chat_to_talk_about = getActivity().findViewById(R.id.spinner_to_choose_chat_to_talk_about);
            final TextView text_view_saying_be_nice_to_the_start = getActivity().findViewById(R.id.text_view_saying_be_nice_to_the_start);
            ripple_send_button_chat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!edit_text_to_enter_chat_t_send_messages.getText().toString().trim().equals("")) {
                        database.child(spinner_item).child("chat_room_".concat(String.valueOf(room_int))).child("user_".concat(String.valueOf(which_user))).setValue(edit_text_to_enter_chat_t_send_messages.getText().toString().trim()).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(getActivity(), "Can't text right now. please try again later", Toast.LENGTH_LONG).show();
                            }
                        });
                        chat_list.add(new Item_for_chat("", edit_text_to_enter_chat_t_send_messages.getText().toString().trim(),"normal"));
                        adapter.notifyDataSetChanged();
                        edit_text_to_enter_chat_t_send_messages.setText("");
                        recycle_view_for_chat_in_chat_fragment.scrollToPosition(chat_list.size() - 1);
                        text_view_saying_be_nice_to_the_start.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
    }

    /*private void end_chat_button_in_top() {
        if (getActivity() != null) {
            final Button button_to_end_chat_in_chat = getActivity().findViewById(R.id.button_to_end_chat_in_chat);
            button_to_end_chat_in_chat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (button_to_end_chat_in_chat.getText().toString().equals("End chat")) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Leave Chat?")
                                .setMessage("Are you sure you want to leave this chat?")

                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                // The dialog is automatically dismissed when a dialog button is clicked.
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        end_the_chat(0);
                                    }
                                })

                                // A null listener allows the button to dismiss the dialog and take no further action.
                                .setNegativeButton(android.R.string.no, null)
                                .show();
                    } else {
                        restart_the_values();
                        open_a_screen(1);
                        button_to_end_chat_in_chat.setText("End chat");
                    }
                }
            });
        }
    }*/

    private void choose_chat_topic() {
        if (getActivity() != null) {
            final ArrayList<String> spinner_list = new ArrayList<>();
            spinner_list.add("Video games");
            spinner_list.add("Social media");
            spinner_list.add("Impulsive shopping");
            spinner_list.add("Porn / masturbating");
            spinner_list.add("Smoking / Vaping");
            spinner_list.add("Weed / Marijuana");
            spinner_list.add("Alcohol");
            spinner_list.add("Drugs");
            spinner_list.add("Cursing");
            spinner_list.add("Procrastination");
            spinner_list.add("Lying");
            spinner_list.add("Coffee");
            spinner_list.add("Fast food");
            spinner_list.add("Sugar");
            spinner_list.add("Over eating");
            spinner_list.add("Gambling");
            spinner_list.add("Self harm");
            spinner_list.add("Over working");
            spinner_list.add("Over sleeping");
            spinner_list.add("Other");

            final Spinner spinner_to_choose_chat_to_talk_about = getActivity().findViewById(R.id.spinner_to_choose_chat_to_talk_about);
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_in_the_middle_text, spinner_list);
            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            spinner_to_choose_chat_to_talk_about.setAdapter(arrayAdapter);
        }
    }

    private void leave_chat() {
        if (getActivity() != null) {
            if (which_user == 1 || which_user == 2) {
                did_i_leave_chat = true;
                database.child(spinner_item).child("chat_room_".concat(String.valueOf(room_int))).removeValue();
            }
        }
    }

    private void restart_the_values() {
        if (getActivity() != null) {
            Button button_to_start_seesion_in_chat = getActivity().findViewById(R.id.button_to_start_seesion_in_chat);
            EditText edit_text_to_enter_chat_t_send_messages = getActivity().findViewById(R.id.edit_text_to_enter_chat_t_send_messages);
            Button ripple_send_button_chat = getActivity().findViewById(R.id.ripple_send_button_chat);
            TextView text_showing_the_timer_beside_end_chat = getActivity().findViewById(R.id.text_showing_the_timer_beside_end_chat);
            //Button button_to_end_chat_in_chat = getActivity().findViewById(R.id.button_to_end_chat_in_chat);
            button_to_start_seesion_in_chat.setText("Start Session");
            edit_text_to_enter_chat_t_send_messages.setText("");
            ripple_send_button_chat.setBackgroundResource(R.drawable.ripple_button_round_grey);
            chat_list.clear();
            adapter.notifyDataSetChanged();
            int other_user;
            if (which_user == 1) {
                other_user = 2;
            } else {
                other_user = 1;
            }
            if (valueEventListener != null) {
                database.child(spinner_item).child("chat_room_".concat(String.valueOf(room_int))).child("user_".concat(String.valueOf(other_user))).removeEventListener(valueEventListener);
            }
            if (childEventListener_child_2 != null) {
                database.child(spinner_item).child("chat_room_".concat(String.valueOf(room_int))).removeEventListener(childEventListener_child_2);
            }
            if (childEventListener_child_1 != null) {
                database.child(spinner_item).child("chat_room_1").removeEventListener(childEventListener_child_1);
            }
            valueEventListener = null;
            childEventListener_child_1 = null;
            childEventListener_child_2 = null;
            database = FirebaseDatabase.getInstance().getReference();
            room_int = 1;
            which_user = 0;
            handler_for_five_minute_time.removeCallbacksAndMessages(null);
            five_minute_time = 300000;
            user_removed = false;
            edit_text_to_enter_chat_t_send_messages.setEnabled(true);
            text_showing_the_timer_beside_end_chat.setText("05:00");
            other_person_firebase = null;
            am_i_called_first_child_listener = false;
            is_the_chat_still_going = false;
            was_this_reported = false;
            did_i_leave_chat = false;
            //button_to_end_chat_in_chat.setText("End chat");
        }
    }

    private void message_text_wwatcher() {
        if (getActivity() != null) {
            EditText edit_text_to_enter_chat_t_send_messages = getActivity().findViewById(R.id.edit_text_to_enter_chat_t_send_messages);
            final Button ripple_send_button_chat = getActivity().findViewById(R.id.ripple_send_button_chat);
            edit_text_to_enter_chat_t_send_messages.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().trim().equals("")) {
                        ripple_send_button_chat.setBackgroundResource(R.drawable.ripple_button_round_grey);
                    } else {
                        ripple_send_button_chat.setBackgroundResource(R.drawable.ripple_button_fav_any_circle);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    private void set_up_recycle() {
        if (getActivity() != null) {
            recycle_view_for_chat_in_chat_fragment = getActivity().findViewById(R.id.recycle_view_for_chat_in_chat_fragment);
            recycle_view_for_chat_in_chat_fragment.setHasFixedSize(false);
            linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setStackFromEnd(true);
            adapter = new Item_for_chat_adapter(chat_list);
            recycle_view_for_chat_in_chat_fragment.setLayoutManager(linearLayoutManager);
            recycle_view_for_chat_in_chat_fragment.setAdapter(adapter);
        }
    }

    private void other_person_chat_listener() {
        if (getActivity() != null) {
            final TextView text_view_saying_be_nice_to_the_start = getActivity().findViewById(R.id.text_view_saying_be_nice_to_the_start);
            int other_user;
            if (which_user == 1) {
                other_user = 2;
            } else {
                other_user = 1;
            }
            if (valueEventListener == null) {
                database.child(spinner_item).child("chat_room_".concat(String.valueOf(room_int))).child("user_".concat(String.valueOf(other_user))).addValueEventListener(valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.getValue() != null) {
                            if (!snapshot.getValue().toString().equals("____messeges_for_chat_app____")) {
                                if (snapshot.getValue().toString().contains("123457_hey_there_i_am_giving_you_")) {
                                    chat_list.add(new Item_for_chat(snapshot.getValue().toString().replace("123457_hey_there_i_am_giving_you_",""), snapshot.getValue().toString().replace("123457_hey_there_i_am_giving_you_",""),"i_get_gift"));
                                    adapter.notifyDataSetChanged();
                                    recycle_view_for_chat_in_chat_fragment.scrollToPosition(chat_list.size() - 1);
                                    text_view_saying_be_nice_to_the_start.setVisibility(View.INVISIBLE);
                                } else {
                                    chat_list.add(new Item_for_chat(snapshot.getValue().toString(), "","normal"));
                                    adapter.notifyDataSetChanged();
                                    recycle_view_for_chat_in_chat_fragment.scrollToPosition(chat_list.size() - 1);
                                    text_view_saying_be_nice_to_the_start.setVisibility(View.INVISIBLE);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }
    }

    private void first_child_listener() {
        if (getActivity() != null) {
            final String habit_name = spinner_item;
            childEventListener_child_1 = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    database.child(habit_name).child("chat_room_".concat(String.valueOf(room_int))).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getChildrenCount() == 5 && !am_i_called_first_child_listener) {
                                put_the_last_time_firebase();
                                am_i_called_first_child_listener = true;
                                open_a_screen(2);
                                handler.removeCallbacksAndMessages(null);
                                other_person_chat_listener();
                                start_the_five_minute_time();
                                set_the_fire_base_of_other_person();
                                write_chat_now();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                    if (which_user != 0 && !user_removed && five_minute_time > 5000) {
                        end_the_chat(1);
                        user_removed = true;
                    }
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
        }
    }

    private void second_child_listener() {
        childEventListener_child_2 = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (which_user != 0 && !user_removed && five_minute_time > 5000) {
                    end_the_chat(1);
                    user_removed = true;
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
    }

    private void end_the_chat(int which) {
        if (which == 0) {
            leave_chat();
            open_a_screen(1);
            restart_the_values();
            setHandler_for_start_button_listen();
            is_the_chat_still_going = false;
        } else if (which == 1) {
            if (getActivity() != null) {
                //Button button_to_end_chat_in_chat = getActivity().findViewById(R.id.button_to_end_chat_in_chat);
                EditText edit_text_to_enter_chat_t_send_messages = getActivity().findViewById(R.id.edit_text_to_enter_chat_t_send_messages);
                TextView text_view_saying_be_nice_to_the_start = getActivity().findViewById(R.id.text_view_saying_be_nice_to_the_start);
                Button button_to_back_in_chat_visible_after_chat = getActivity().findViewById(R.id.button_to_back_in_chat_visible_after_chat);
                View back_view_white_in_chat = getActivity().findViewById(R.id.back_view_white_in_chat);
                //button_to_end_chat_in_chat.setText("Leave");
                text_view_saying_be_nice_to_the_start.setVisibility(View.INVISIBLE);
                if (did_i_leave_chat) {
                    chat_list.add(new Item_for_chat("", "","i_left"));
                } else {
                    chat_list.add(new Item_for_chat("", "","they_left"));
                }
                adapter.notifyDataSetChanged();
                edit_text_to_enter_chat_t_send_messages.setText("");
                edit_text_to_enter_chat_t_send_messages.setEnabled(false);
                handler_for_five_minute_time.removeCallbacksAndMessages(null);
                setHandler_for_start_button_listen();
                is_the_chat_still_going = false;
                if (which_screen_i_am_in == 2) {
                    button_to_back_in_chat_visible_after_chat.setVisibility(View.VISIBLE);
                }
                back_view_white_in_chat.setVisibility(View.VISIBLE);
                //restart_the_values();
            }
            //they pressed end chat
        } else if (which == 2) {
            if (getActivity() != null) {
                leave_chat();
                //Button button_to_end_chat_in_chat = getActivity().findViewById(R.id.button_to_end_chat_in_chat);
                EditText edit_text_to_enter_chat_t_send_messages = getActivity().findViewById(R.id.edit_text_to_enter_chat_t_send_messages);
                TextView text_view_saying_be_nice_to_the_start = getActivity().findViewById(R.id.text_view_saying_be_nice_to_the_start);
                Button button_to_back_in_chat_visible_after_chat = getActivity().findViewById(R.id.button_to_back_in_chat_visible_after_chat);
                View back_view_white_in_chat = getActivity().findViewById(R.id.back_view_white_in_chat);
                //button_to_end_chat_in_chat.setText("Leave");
                text_view_saying_be_nice_to_the_start.setVisibility(View.INVISIBLE);
                chat_list.add(new Item_for_chat("", "","time_finish"));
                adapter.notifyDataSetChanged();
                edit_text_to_enter_chat_t_send_messages.setText("");
                edit_text_to_enter_chat_t_send_messages.setEnabled(false);
                handler_for_five_minute_time.removeCallbacksAndMessages(null);
                setHandler_for_start_button_listen();
                is_the_chat_still_going = false;
                if (which_screen_i_am_in == 2) {
                    button_to_back_in_chat_visible_after_chat.setVisibility(View.VISIBLE);
                }
                back_view_white_in_chat.setVisibility(View.VISIBLE);
                //restart_the_values();
            }
            //time finish
        }
    }

    private void start_the_five_minute_time() {
        handler_for_five_minute_time.postDelayed(new Runnable() {
            public void run() {
                five_minute_time = five_minute_time - 1000;
                if (five_minute_time > 0) {
                    update_the_timer();
                } else {
                    if (getActivity() != null) {
                        TextView text_showing_the_timer_beside_end_chat = getActivity().findViewById(R.id.text_showing_the_timer_beside_end_chat);
                        text_showing_the_timer_beside_end_chat.setText("00:00");
                    }
                    end_the_chat(2);
                }
                if (five_minute_time > 0) {
                    handler_for_five_minute_time.postDelayed(this, 1000);
                } else {
                    handler_for_five_minute_time.removeCallbacksAndMessages(null);
                }

            }
        }, 1000);
    }

    private void update_the_timer() {
        if (getActivity() != null) {
            TextView text_showing_the_timer_beside_end_chat = getActivity().findViewById(R.id.text_showing_the_timer_beside_end_chat);
            String minutes_string;
            String seconds_string;
            int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(five_minute_time);
            int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(five_minute_time) - minutes * 60;
            if (minutes < 10) {
                minutes_string = "0".concat(String.valueOf(minutes));
            } else {
                minutes_string = String.valueOf(minutes);
            }
            if (seconds < 10) {
                seconds_string = "0".concat(String.valueOf(seconds));
            } else {
                seconds_string = String.valueOf(seconds);
            }
            String final_string = minutes_string.concat(":").concat(seconds_string);
            text_showing_the_timer_beside_end_chat.setText(final_string);
        }
    }

    public boolean is_the_chat_going_on() {
        return is_the_chat_still_going;
    }

    private void back_button_listen() {
        if (getView() != null) {
            getView().setFocusableInTouchMode(true);
            getView().requestFocus();
            getView().setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                        if (!is_alert_showing) {
                            if (is_the_chat_going_on()) {
                                is_alert_showing = true;
                                new AlertDialog.Builder(getContext())
                                        .setTitle("Leave Chat?")
                                        .setMessage("Are you sure you want to leave this chat?")

                                        // Specifying a listener allows you to take an action before dismissing the dialog.
                                        // The dialog is automatically dismissed when a dialog button is clicked.
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                end_the_chat(0);
                                                if (getActivity() != null) {
                                                    getActivity().finishAffinity();
                                                }
                                            }
                                        })

                                        // A null listener allows the button to dismiss the dialog and take no further action.
                                        .setNegativeButton(android.R.string.no, null)
                                        .show();
                            } else {
                                if (getActivity() != null) {
                                    getActivity().finishAffinity();
                                }
                            }
                            return true;
                        } else {
                            if (getActivity() != null) {
                                getActivity().finishAffinity();
                            }
                        }
                    }
                    return false;
                }
            });
        }
    }

    private void set_the_fire_base_of_other_person() {
        if (other_person_firebase == null) {
            int other_user;
            if (which_user == 1) {
                other_user = 2;
            } else {
                other_user = 1;
            }
            final String habit_name = spinner_item;
            database.child(habit_name).child("chat_room_".concat(String.valueOf(room_int))).child("user_".concat(String.valueOf(other_user)).concat("_id")).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                    //if (dataSnapshot.getChildrenCount() == 2) {
                    other_person_firebase = (String) dataSnapshot.getValue();
                }

                @Override
                public void onCancelled(@NotNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void recycle_view_listen_scroll() {
        if (getActivity() != null) {
            RecyclerView recycle_view_for_chat_in_chat_fragment = getActivity().findViewById(R.id.recycle_view_for_chat_in_chat_fragment);
            recycle_view_for_chat_in_chat_fragment.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    if (bottom > oldBottom || bottom < oldBottom) {
                        recycle_view_for_chat_in_chat_fragment.scrollToPosition(chat_list.size() - 1);
                    }
                }
            });
        }
    }

    private void put_the_last_time_firebase() {
        database.child("last_chat").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(ServerValue.TIMESTAMP);
    }

    private void up_date_function_if_pro() {
        if (getActivity() != null) {
            final Button button_to_start_seesion_in_chat = getActivity().findViewById(R.id.button_to_start_seesion_in_chat);
            Am_i_paid am_i_paid = new Am_i_paid(getContext());
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("last_time_function_was_called", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            if (am_i_paid.did_user_pay()) {
                long last_time_checked = sharedPreferences.getLong("last_update", 0);
                if (last_time_checked < System.currentTimeMillis() - 86100000) {
                    HashMap<String, Object> children = new HashMap<>();
                    firebaseFunctions = FirebaseFunctions.getInstance();
                    firebaseFunctions
                            .getHttpsCallable("up_date_the_time_chat")
                            .call(children)
                            .continueWith(new Continuation<HttpsCallableResult, String>() {
                                @Override
                                public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                    if (String.valueOf(task.getResult().getData()).equals("success")) {
                                        myEdit.putLong("last_update", System.currentTimeMillis());
                                        myEdit.commit();
                                        if (button_to_start_seesion_in_chat.getText().toString().equals("Stop Searching")) {
                                            join_a_chat_room();
                                        }
                                    } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                        Toast.makeText(getActivity(), "A problem happened. Please try again later", Toast.LENGTH_LONG).show();
                                    }
                                    return "result";
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {

                        }
                    });
                } else {
                    join_a_chat_room();
                }
            } else {
                join_a_chat_room();
            }
        }
    }

    private long last_time_chatted() {
        //Button button_to_start_seesion_in_chat = getView().findViewById(R.id.button_to_start_seesion_in_chat);
        SharedPreferences sh = getActivity().getSharedPreferences("last_time_i_chatted", MODE_PRIVATE);
        long last_time = sh.getLong("last_chat", 0);
        if (last_time != 0 && last_time > System.currentTimeMillis() - 86400000) {
            return (last_time - (System.currentTimeMillis() - 86400000L));
        } else {
            return 0;
        }
    }

    private void setHandler_for_start_button_listen() {
        Am_i_paid am_i_paid = new Am_i_paid(getContext());
        if (!am_i_paid.did_user_pay()) {
            run_the_function_every_sixty_seconds();
            handler_for_start_button_listen.postDelayed(new Runnable() {
                public void run() {
                    run_the_function_every_sixty_seconds();
                    handler_for_start_button_listen.postDelayed(this, 60000);
                }
            }, 60000);
        }
    }

    private void write_chat_now() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("last_time_i_chatted", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putLong("last_chat", System.currentTimeMillis());
            myEdit.commit();
        }
    }

    private void run_the_function_every_sixty_seconds() {
        if (getView() != null) {
            Button button_to_start_seesion_in_chat = getView().findViewById(R.id.button_to_start_seesion_in_chat);
            long millis = last_time_chatted();
            if (millis == 0) {
                button_to_start_seesion_in_chat.setText("Start Session");
                button_to_start_seesion_in_chat.getBackground().setColorFilter(null);
                handler_for_start_button_listen.removeCallbacksAndMessages(null);
            } else {
                String set_button = String.format(Locale.getDefault(), "Next free session: %02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)));
                button_to_start_seesion_in_chat.setText(set_button);
                button_to_start_seesion_in_chat.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
            }
        }
    }

    private void three_dot_listen() {
        if (getView() != null) {
            Button three_dot_button_listen_in_chat_to_view_more_things = getView().findViewById(R.id.three_dot_button_listen_in_chat_to_view_more_things);
            three_dot_button_listen_in_chat_to_view_more_things.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (is_the_chat_still_going) {
                        show_menu(three_dot_button_listen_in_chat_to_view_more_things, 1);
                    } else {
                        show_menu(three_dot_button_listen_in_chat_to_view_more_things, 2);
                    }
                }
            });
        }
    }

    private void gift_button_listen() {
        if (getView() != null) {
            Button button_to_gift_in_chat_real_time = getView().findViewById(R.id.button_to_gift_in_chat_real_time);
            TextView text_view_saying_be_nice_to_the_start = getView().findViewById(R.id.text_view_saying_be_nice_to_the_start);
            button_to_gift_in_chat_real_time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bottom_sheet_to_give_coins bottom_sheet_to_give_coins = new Bottom_sheet_to_give_coins(other_person_firebase);
                    bottom_sheet_to_give_coins.set_gift_listen_which_one(new Bottom_sheet_to_give_coins.send_which_award_was_given() {
                        @Override
                        public void gift_was_clicked(ArrayList<Long> arrayList_of_gifts) {
                            am_i_in_gift = false;
                            if (arrayList_of_gifts != null && arrayList_of_gifts.size() == 4) {
                                long number_of_month = arrayList_of_gifts.get(0) + arrayList_of_gifts.get(1) * 3 + arrayList_of_gifts.get(2) * 6 + arrayList_of_gifts.get(3) * 12;
                                if (number_of_month != 0) {
                                    database.child(spinner_item).child("chat_room_".concat(String.valueOf(room_int))).child("user_".concat(String.valueOf(which_user))).setValue("123457_hey_there_i_am_giving_you_".concat(String.valueOf(number_of_month)));
                                    chat_list.add(new Item_for_chat(String.valueOf(number_of_month), String.valueOf(number_of_month), "i_give_gift"));
                                    adapter.notifyDataSetChanged();
                                    recycle_view_for_chat_in_chat_fragment.scrollToPosition(chat_list.size() - 1);
                                    text_view_saying_be_nice_to_the_start.setVisibility(View.INVISIBLE);
                                }
                            }
                        }
                    });
                    bottom_sheet_to_give_coins.show(getParentFragmentManager(), "");
                    am_i_in_gift = true;
                }
            });
        }
    }

    private void show_menu(View button, int which_version) {
        if (getContext() != null) {
            PopupMenu popupMenu = new PopupMenu(getContext(), button);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getOrder() == 0) {
                        if (which_version == 1) {
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Leave Chat?")
                                    .setMessage("Are you sure you want to leave this chat?")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            end_the_chat(0);
                                        }
                                    })
                                    .setNegativeButton(android.R.string.no, null)
                                    .show();
                        } else if (which_version == 2) {
                            restart_the_values();
                            open_a_screen(1);
                        }
                        return true;
                    } else if (item.getOrder() == 1) {
                        if (was_this_reported) {
                            Toast toast = Toast.makeText(getContext(), "Already reported", Toast.LENGTH_LONG);
                            toast.show();
                        } else {
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Report Person?")
                                    .setMessage("Are you sure you want to report this person?")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            report_button();
                                        }
                                    })
                                    .setNegativeButton(android.R.string.no, null)
                                    .show();
                        }
                        return true;
                    }
                    return false;
                }
            });
            if (which_version == 1) {
                popupMenu.inflate(R.menu.chat_options);
            } else if (which_version == 2) {
                popupMenu.inflate(R.menu.chat_options_second_version);
            }
            popupMenu.show();
        }
    }

    private void report_button() {
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        DocumentReference docIdRef = rootRef.collection("report_chat").document(other_person_firebase);
        docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        report_transaction();
                    } else {
                        create_firstore_report_document();
                    }
                } else {
                    Toast toast = Toast.makeText(getContext(), "A problem occurred please try again later", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    private void report_transaction() {
        final DocumentReference sfDocRef = m_firebaseFirestore.collection("report_chat").document(other_person_firebase);
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
                transaction.update(sfDocRef, "reports", array_lsit_with_upvote, "number_of_reports", number_of_reports);
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
                Toast toast = Toast.makeText(getContext(), "A problem occurred please try again later", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void create_firstore_report_document() {
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        DocumentReference docIdRef = rootRef.collection("report_chat").document(other_person_firebase);
        Map<String, Object> city = new HashMap<>();
        ArrayList<String> reports = new ArrayList<>();
        reports.add(FirebaseAuth.getInstance().getCurrentUser().getUid());
        city.put("reports", reports);
        city.put("number_of_reports", 1);
        docIdRef.set(city).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast toast = Toast.makeText(getContext(), "Thanks, your input was received and greatly appreciated!!", Toast.LENGTH_LONG);
                toast.show();
                was_this_reported = true;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast toast = Toast.makeText(getContext(), "A problem occurred please try again later", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void back_button_listen_chat() {
        if (getActivity() != null) {
            Button button_to_back_in_chat_visible_after_chat = getActivity().findViewById(R.id.button_to_back_in_chat_visible_after_chat);
            button_to_back_in_chat_visible_after_chat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    restart_the_values();
                    open_a_screen(1);
                }
            });
        }
    }

    private String how_is_user_logged_in() {
        try {
            if ((FirebaseAuth.getInstance().getCurrentUser() != null && FirebaseAuth.getInstance().getCurrentUser().getIdToken(false).getResult().getSignInProvider().equals("google.com"))) {
                return "google";
            } else {
                return "anonymous";
            }
        } catch (Exception exception){
            return "anonymous";
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
                    started_login_process = true;
                }
            }
        }
    }

    /*private void auth_changed_listener() {
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if ((firebaseAuth.getCurrentUser() != null && firebaseAuth.getCurrentUser().getIdToken(false).getResult().getSignInProvider().equals("google.com")) && !am_i_signed_in) {
                    mAuth = firebaseAuth;
                    currentFirebaseUser = mAuth.getCurrentUser();
                    am_i_signed_in = true;
                    restart_the_premium_and_call_it();
                }
            }
        };
    }*/

    private void restart_the_premium_and_call_it(){
        ((after_login)getActivity()).check_if_user_is_gifted(true);
    }
}