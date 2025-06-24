package com.easyhabitsapp.android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static android.content.Context.MODE_PRIVATE;

public class Write_comment_for_post extends Fragment {
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    private String category_global = "";
    private long streak_global;
    private ArrayList<Long> awards;
    private ArrayList<String> up_vote_list;
    private ArrayList<String> array_list_with_down_vote;
    private ArrayList<String> reports;
    private ArrayList<HashMap<String, Object>> replies;
    private ArrayList<HashMap<String, Object>> array_list_comments;
    private ArrayList<HashMap<String, Object>> repleis_inside_array_list;
    private int recycle_view_position;
    private String name;
    private int position_of_comment;
    private String name_of_the_reply;
/*    private String comment_id;
    private String reply_id;*/
    private String user_id_of_the_main_reply;

    public Write_comment_for_post() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_write_comment_for_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        Bundle bundle = getArguments();
        if (bundle != null) {
            String type = bundle.getString("type");
            if (type.equals("comment")) {
                post_button_listen(bundle.getString("id"), "comment");
            } else if (type.equals("reply")) {
                position_of_comment = bundle.getInt("position");
                post_button_listen(bundle.getString("id"), "reply");
                recycle_view_position = bundle.getInt("recycle_view_position");
            } else if (type.equals("reply_to_reply")){
                position_of_comment = bundle.getInt("position");
                user_id_of_the_main_reply = bundle.getString("user_id");
                post_button_listen(bundle.getString("id"), "reply_to_reply");
                recycle_view_position = bundle.getInt("recycle_view_position");
                name_of_the_reply = bundle.getString("name_of_the_reply");
                put_drawable_at_the_start();
                watch_the_edit_text();
            }
            button_to_expand_in_dialog_fragment(bundle.getString("title"), bundle.getString("body"));
            set_the_title(bundle.getString("title"));
            set_the_text_at_the_top(type);
        }
        x_button_listen();
//        add_category_button_lsiten();
    }

    private void set_the_title(String text) {
        if (getView() != null) {
            TextView text_saying_the_title_or_body_of_post_in_comments = getView().findViewById(R.id.text_saying_the_title_or_body_of_post_in_comments);
            text_saying_the_title_or_body_of_post_in_comments.setText(first_letter_capital(text));
        }
    }

    private void post_button_listen(final String document_id, final String mode) {
        if (getView() != null) {
            TextView add_comment_id_in_full_comment = getView().findViewById(R.id.add_comment_id_in_full_comment);
            Button button_to_post_comment_in_full_comment_view = getView().findViewById(R.id.button_to_post_comment_in_full_comment_view);
            final EditText edit_text_to_enter_comment_in_full_view = getView().findViewById(R.id.edit_text_to_enter_comment_in_full_view);
            button_to_post_comment_in_full_comment_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (edit_text_to_enter_comment_in_full_view.getText().toString().trim().equals("")) {
                        Toast toast = Toast.makeText(getContext(), "Comment can't be empty", Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (edit_text_to_enter_comment_in_full_view.getText().toString().trim().length() > 40000) {
                        Toast toast = Toast.makeText(getContext(), "Comment is too long", Toast.LENGTH_SHORT);
                        toast.show();
                    }/* else if (category_global.isEmpty()) {
                        Toast toast = Toast.makeText(getContext(), "Please choose a category by pressing the \"Choose Category\"", Toast.LENGTH_SHORT);
                        toast.show();
                    }*/ else {
                        if (mode.equals("comment")) {
                            add_the_comment(document_id, edit_text_to_enter_comment_in_full_view.getText().toString().trim());
                        } else if (mode.equals("reply")) {
                            add_a_reply(edit_text_to_enter_comment_in_full_view.getText().toString().trim(), position_of_comment, document_id);
                        } else if (mode.equals("reply_to_reply")) {
                            String combine = edit_text_to_enter_comment_in_full_view.getText().toString().trim();
                            combine = combine.replace("@".concat(name_of_the_reply), "").trim();
                            if (combine.length() == 0) {
                                Toast toast = Toast.makeText(getContext(), "Reply can't be empty", Toast.LENGTH_SHORT);
                                toast.show();
                            } else {
                                combine = "@".concat(name_of_the_reply).concat(" ").concat(combine);
                                add_a_reply(combine, position_of_comment, document_id);
                            }
                        }
                    }
                }
            });
        }
    }

    private void add_the_comment(String document_id, final String body_of_the_comment) {
        if (getView() != null) {
            final DocumentReference sfDocRef = firebaseFirestore.collection("posts").document(document_id);
            //final CheckBox check_box_to_ask_add_streak_to_comment_in_add_a_comment = getView().findViewById(R.id.check_box_to_ask_add_streak_to_comment_in_add_a_comment);
            firebaseFirestore.runTransaction(new Transaction.Function<Void>() {
                @Override
                public Void apply(@NotNull Transaction transaction) throws FirebaseFirestoreException {
                    DocumentSnapshot snapshot = transaction.get(sfDocRef);

                    // Note: this could be done without a transaction
                    //       by updating the population using FieldValue.increment()
                    array_list_comments = (ArrayList<HashMap<String, Object>>) snapshot.get("comments");
                    HashMap<String, Object> map = new HashMap<>();

                    up_vote_list = new ArrayList<>();
                    up_vote_list.add(firebaseUser.getUid());
                    map.put("up_vote_list", up_vote_list);

                    array_list_with_down_vote = new ArrayList<>();
                    map.put("down_vote_list", array_list_with_down_vote);

                    awards = new ArrayList<>();
                    awards.add(0L);
                    awards.add(0L);
                    awards.add(0L);
                    map.put("awards", awards);
                    map.put("body", body_of_the_comment);

                    map.put("dev", false);

                    reports = new ArrayList<>();
                    map.put("reports", reports);


                    /*if (check_box_to_ask_add_streak_to_comment_in_add_a_comment.getVisibility() == View.VISIBLE && check_box_to_ask_add_streak_to_comment_in_add_a_comment.isChecked()) {
                        map.put("streak", streak_global);
                    } else {
                        map.put("streak", -1L);
                    }*/

                    map.put("user_id", firebaseUser.getUid());

//                    map.put("category", category_global);

                    map.put("date", Timestamp.now());

                    replies = new ArrayList<>();
                    map.put("replies", replies);
                    name = return_the_name();
                    map.put("name", name);
                    /*comment_id = create_random_comment_id();
                    map.put("comment_id",comment_id);*/
                    map.put("should_i_hide_this",false);


                    array_list_comments.add(map);

                    transaction.update(sfDocRef, "comments", array_list_comments, "type", "comment");
                    // Success
                    return null;
                }
            }).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast toast = Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT);
                    toast.show();
                    if (getActivity() != null) {
                        Show_full_post old_fragment = (Show_full_post) getActivity().getSupportFragmentManager().findFragmentByTag("show full post");
                        if (old_fragment != null) {
                            /*if (check_box_to_ask_add_streak_to_comment_in_add_a_comment.getVisibility() == View.VISIBLE && check_box_to_ask_add_streak_to_comment_in_add_a_comment.isChecked()) {
                                old_fragment.add_new_comment(body_of_the_comment, awards, false, up_vote_list, array_list_with_down_vote, reports, streak_global, firebaseUser.getUid(), replies, position_of_comment_to_save, Timestamp.now(), category_global, array_list_comments, name);
                            } else {
                                old_fragment.add_new_comment(body_of_the_comment, awards, false, up_vote_list, array_list_with_down_vote, reports, -1L, firebaseUser.getUid(), replies, position_of_comment_to_save, Timestamp.now(), category_global, array_list_comments, name);
                            }*/
                            old_fragment.add_new_comment(body_of_the_comment, awards, false, up_vote_list, array_list_with_down_vote, reports, -1L, firebaseUser.getUid(), replies, array_list_comments.size()-1, Timestamp.now(), "", array_list_comments, name);
                            getActivity().getSupportFragmentManager().beginTransaction().remove(Write_comment_for_post.this).show(old_fragment).commit();
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast toast = Toast.makeText(getContext(), "Can't comment at the moment try again later", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }
    }

    private void x_button_listen() {
        if (getView() != null) {
            Button button_to_press_x_on_comment_to_go_back = getView().findViewById(R.id.button_to_press_x_on_comment_to_go_back);
            button_to_press_x_on_comment_to_go_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    x_or_back_was_pressed();
                }
            });
        }
    }

    public void x_or_back_was_pressed() {
        if (getView() != null && getActivity() != null) {
            EditText edit_text_to_enter_comment_in_full_view = getView().findViewById(R.id.edit_text_to_enter_comment_in_full_view);
            if (!edit_text_to_enter_comment_in_full_view.getText().toString().trim().isEmpty() && name_of_the_reply != null && !edit_text_to_enter_comment_in_full_view.getText().toString().trim().equals("@".concat(name_of_the_reply))) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Discard comment")
                        .setMessage("Are you sure you want to discard this comment?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Show_full_post old_fragment = (Show_full_post) getActivity().getSupportFragmentManager().findFragmentByTag("show full post");
                                if (old_fragment != null) {
                                    getActivity().getSupportFragmentManager().beginTransaction().remove(Write_comment_for_post.this).show(old_fragment).commit();
                                }
                            }
                        })
                        .setNegativeButton("cancel", null)
                        .show();
            } else {
                Show_full_post old_fragment = (Show_full_post) getActivity().getSupportFragmentManager().findFragmentByTag("show full post");
                if (old_fragment != null && getActivity() != null) {
                    getActivity().getSupportFragmentManager().beginTransaction().remove(Write_comment_for_post.this).show(old_fragment).commit();
                }
            }
        }
    }

    public boolean is_edit_text_empty() {
        if (getView() != null) {
            EditText edit_text_to_enter_comment_in_full_view = getView().findViewById(R.id.edit_text_to_enter_comment_in_full_view);
            if (edit_text_to_enter_comment_in_full_view.getText().toString().trim().isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    private void button_to_expand_in_dialog_fragment(final String title, final String body) {
        if (getView() != null) {
            Button button_to_show_full_post_in_write_a_comment = getView().findViewById(R.id.button_to_show_full_post_in_write_a_comment);
            button_to_show_full_post_in_write_a_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        Dialog_fragment_to_show_the_full_post_comment dialogFragment = new Dialog_fragment_to_show_the_full_post_comment();
                        Bundle bundle = new Bundle();
                        bundle.putString("title", title);
                        bundle.putString("body", body);
                        dialogFragment.setArguments(bundle);
                        dialogFragment.show(getActivity().getSupportFragmentManager(), "tag");
                    }
                }
            });
        }
    }

    /*private void add_category_button_lsiten() {
        if (getView() != null) {
            Button choose_categpry_button_in_write_a_comment_to_add = getView().findViewById(R.id.choose_categpry_button_in_write_a_comment_to_add);
            choose_categpry_button_in_write_a_comment_to_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog_asking_which_habit dialog_asking_which_habit = new Dialog_asking_which_habit();
                    dialog_asking_which_habit.setTargetFragment(Write_comment_for_post.this, 1001);
                    dialog_asking_which_habit.show(getActivity().getSupportFragmentManager(), "post");
                }
            });
        }
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1001:
                category_global = data.getStringExtra("habit_name");
                //make_the_check_box_visisble_or_no();
                //rename_button();
                break;
        }
    }

    /*private void make_the_check_box_visisble_or_no() {
        if (getView() != null) {
            CheckBox check_box_to_ask_add_streak_to_comment_in_add_a_comment = getView().findViewById(R.id.check_box_to_ask_add_streak_to_comment_in_add_a_comment);
            int value = check_if_cat_already_exists();
            check_box_to_ask_add_streak_to_comment_in_add_a_comment.setChecked(false);
            if (value != -1) {
                streak_global = value;
                check_box_to_ask_add_streak_to_comment_in_add_a_comment.setVisibility(View.VISIBLE);
                if (value == 1) {
                    check_box_to_ask_add_streak_to_comment_in_add_a_comment.setText("Show Streak? 1 day");
                } else {
                    check_box_to_ask_add_streak_to_comment_in_add_a_comment.setText("Show Streak? ".concat(String.valueOf(value)).concat(" days"));
                }
            } else {

                check_box_to_ask_add_streak_to_comment_in_add_a_comment.setVisibility(View.INVISIBLE);
            }
        }
    }*/

    /*private int check_if_cat_already_exists() {
        if (getActivity() != null && getContext() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("list_of_the_bad_habits", getContext().MODE_PRIVATE);
            String bad_habits = sharedPreferences.getString("Bad_habits", "");
            if (bad_habits != null && !bad_habits.equals("") && bad_habits.contains(category_global)) {
                String[] split_bad_habits = bad_habits.split("spit_max_for_the_bad_habits");
                for (String big_split : split_bad_habits) {
                    String[] small_split = big_split.split("split_here_bad_habits");
                    if (small_split[0].equals(category_global)) {

                        return return_the_streak(small_split[1], small_split[2]);
                    }
                }
                return -1;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }*/

    /*private int return_the_streak(String name, String real_time) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("bad_habits_relapses", MODE_PRIVATE);
        String old = sharedPreferences.getString(name.toLowerCase(), "");
        String[] split_the_dates = old.split("split_here_small");
        long old_time;
        if (!old.equals("")) {
            old_time = Long.parseLong(split_the_dates[split_the_dates.length - 1]);
        } else {
            old_time = Long.parseLong(real_time);
        }
        return (int) TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis() - old_time);
    }*/

    /*private void rename_button() {
        if (getView() != null) {
            Button choose_categpry_button_in_write_a_comment_to_add = getView().findViewById(R.id.choose_categpry_button_in_write_a_comment_to_add);
            choose_categpry_button_in_write_a_comment_to_add.setText(category_global);
        }
    }*/

    private String first_letter_capital(String sentence) {
        return sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
    }

    private void add_a_reply(final String body, final int position_of_comment, String document_id) {
        if (getView() != null) {
            final DocumentReference sfDocRef = firebaseFirestore.collection("posts").document(document_id);
            //final CheckBox check_box_to_ask_add_streak_to_comment_in_add_a_comment = getView().findViewById(R.id.check_box_to_ask_add_streak_to_comment_in_add_a_comment);
            firebaseFirestore.runTransaction(new Transaction.Function<Void>() {
                @Override
                public Void apply(@NotNull Transaction transaction) throws FirebaseFirestoreException {
                    DocumentSnapshot snapshot = transaction.get(sfDocRef);

                    // Note: this could be done without a transaction
                    //       by updating the population using FieldValue.increment()
                    array_list_comments = (ArrayList<HashMap<String, Object>>) snapshot.get("comments");
                    HashMap<String, Object> map_from_firebase = array_list_comments.get(position_of_comment);
                    repleis_inside_array_list = (ArrayList<HashMap<String, Object>>) map_from_firebase.get("replies");
                    HashMap<String, Object> map = new HashMap<>();
                    up_vote_list = new ArrayList<>();
                    up_vote_list.add(firebaseUser.getUid());
                    map.put("up_vote_list", up_vote_list);

                    array_list_with_down_vote = new ArrayList<>();
                    map.put("down_vote_list", array_list_with_down_vote);

                    awards = new ArrayList<>();
                    awards.add(0L);
                    awards.add(0L);
                    awards.add(0L);
                    map.put("awards", awards);
                    map.put("body", body);

                    map.put("dev", false);

                    reports = new ArrayList<>();
                    map.put("reports", reports);


                    /*if (check_box_to_ask_add_streak_to_comment_in_add_a_comment.getVisibility() == View.VISIBLE && check_box_to_ask_add_streak_to_comment_in_add_a_comment.isChecked()) {
                        map.put("streak", streak_global);
                    } else {
                        map.put("streak", -1L);
                    }*/

                    map.put("user_id", firebaseUser.getUid());

                    //map.put("category", category_global);

                    map.put("date", Timestamp.now());
                    name = return_the_name();
                    map.put("name", name);
                    /*reply_id = create_random_comment_id();
                    map.put("reply_id",reply_id);*/
                    map.put("should_i_hide_this",false);

                    repleis_inside_array_list.add(map);


                    transaction.update(sfDocRef, "comments", array_list_comments, "type", "reply");

                    // Success
                    return null;
                }
            }).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast toast = Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT);
                    toast.show();
                    if (getActivity() != null) {
                        Show_full_post old_fragment = (Show_full_post) getActivity().getSupportFragmentManager().findFragmentByTag("show full post");
                        if (old_fragment != null) {
                            /*if (check_box_to_ask_add_streak_to_comment_in_add_a_comment.getVisibility() == View.VISIBLE && check_box_to_ask_add_streak_to_comment_in_add_a_comment.isChecked()) {
                                old_fragment.reply_to_the_comment(repleis_inside_array_list, recycle_view_position,position_of_comment, position_of_reply);
                            } else {
                                old_fragment.reply_to_the_comment(repleis_inside_array_list, recycle_view_position,position_of_comment, position_of_reply);
                            }*/
                            old_fragment.reply_to_the_comment(repleis_inside_array_list, recycle_view_position, repleis_inside_array_list.size()-1,position_of_comment);
                            getActivity().getSupportFragmentManager().beginTransaction().remove(Write_comment_for_post.this).show(old_fragment).commit();
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast toast = Toast.makeText(getContext(), "Can't reply at the moment try again later", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }
    }

    private String return_the_name() {
        if (getActivity() != null) {
            SharedPreferences sh = getActivity().getSharedPreferences("name_online_for_post", MODE_PRIVATE);
            return sh.getString("name", "Name");
        } else {
            return "Name";
        }
    }

    private void put_drawable_at_the_start() {
        if (getActivity() != null && getContext() != null && getView() != null) {
            final TextView text_with_name_of_reply = getActivity().findViewById(R.id.text_with_name_of_reply);
            text_with_name_of_reply.setText("@".concat(name_of_the_reply));

            final ViewTreeObserver viewTreeObserver = text_with_name_of_reply.getViewTreeObserver();
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (getActivity() != null && getContext() != null && getView() != null) {
                        EditText edit_text_to_enter_comment_in_full_view = getActivity().findViewById(R.id.edit_text_to_enter_comment_in_full_view);
                        TextDrawable fav_background = new TextDrawable(getContext(), "@".concat(name_of_the_reply), text_with_name_of_reply.getMeasuredWidth() + 40, text_with_name_of_reply.getMeasuredHeight() + 18, 16);
                        fav_background.setBounds(0, 0, text_with_name_of_reply.getMeasuredWidth() + 40, text_with_name_of_reply.getMeasuredHeight() + 18);
                        SpannableString spannableString = new SpannableString("@".concat(name_of_the_reply));
                        ImageSpan span;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                            span = new ImageSpan(fav_background, ImageSpan.ALIGN_CENTER);
                        } else {
                            span = new ImageSpan(fav_background, ImageSpan.ALIGN_BOTTOM);
                        }
                        spannableString.setSpan(span, 0, name_of_the_reply.length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        edit_text_to_enter_comment_in_full_view.setText(TextUtils.concat(spannableString, " "));
                        edit_text_to_enter_comment_in_full_view.setSelection(name_of_the_reply.length() + 2);
                        edit_text_to_enter_comment_in_full_view.requestFocus();
                        viewTreeObserver.removeOnGlobalLayoutListener(this);
                    }
                }
            });
        }
    }

    private void watch_the_edit_text() {
        if (getActivity() != null) {
            final EditText edit_text_to_enter_comment_in_full_view = getActivity().findViewById(R.id.edit_text_to_enter_comment_in_full_view);
            final TextView text_with_name_of_reply = getActivity().findViewById(R.id.text_with_name_of_reply);
            text_with_name_of_reply.setText("@".concat(name_of_the_reply));
            edit_text_to_enter_comment_in_full_view.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (edit_text_to_enter_comment_in_full_view.getText().toString().length() >= name_of_the_reply.length()) {
                        String edit_name_minus_one = edit_text_to_enter_comment_in_full_view.getText().toString().substring(0, name_of_the_reply.length());
                        String real_name_minus_one = "@".concat(name_of_the_reply).substring(0, name_of_the_reply.length());
                        if (edit_text_to_enter_comment_in_full_view.getSelectionStart() == name_of_the_reply.length() && edit_name_minus_one.equals(real_name_minus_one) && !edit_text_to_enter_comment_in_full_view.getText().toString().equals("")) {
                            edit_text_to_enter_comment_in_full_view.setText("");
                        } else if (edit_text_to_enter_comment_in_full_view.getText().toString().length() >= name_of_the_reply.length() + 2) {
                            String edit_name_minus_one1 = edit_text_to_enter_comment_in_full_view.getText().toString().substring(1, name_of_the_reply.length() + 2);
                            String real_name_minus_one1 = "@".concat(name_of_the_reply);
                            if (edit_text_to_enter_comment_in_full_view.getSelectionStart() == 1 && edit_name_minus_one1.equals(real_name_minus_one1) && !edit_text_to_enter_comment_in_full_view.getText().toString().equals("")) {
                                String first_character = String.valueOf(edit_text_to_enter_comment_in_full_view.getText().toString().charAt(0));
                                String last_string = edit_text_to_enter_comment_in_full_view.getText().toString().substring(2 + name_of_the_reply.length());
                                TextDrawable fav_background = new TextDrawable(getContext(), "@".concat(name_of_the_reply), text_with_name_of_reply.getMeasuredWidth() + 40, text_with_name_of_reply.getMeasuredHeight() + 18, 16);
                                fav_background.setBounds(0, 0, text_with_name_of_reply.getMeasuredWidth() + 40, text_with_name_of_reply.getMeasuredHeight() + 18);
                                SpannableString spannableString = new SpannableString("@".concat(name_of_the_reply));
                                ImageSpan span;
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                                    span = new ImageSpan(fav_background, ImageSpan.ALIGN_CENTER);
                                } else {
                                    span = new ImageSpan(fav_background, ImageSpan.ALIGN_BOTTOM);
                                }
                                spannableString.setSpan(span, 0, name_of_the_reply.length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                edit_text_to_enter_comment_in_full_view.setText(TextUtils.concat(spannableString, first_character, last_string));
                                edit_text_to_enter_comment_in_full_view.setSelection(name_of_the_reply.length() + 2);
                            }
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    /*private String create_random_comment_id() {
        String string_to_choose_from = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int length = 10;
        for (int i = 0; i < length; i++) { // length of the random string.
            int index = random.nextInt();
            stringBuilder.append(string_to_choose_from.charAt(index));
        }
        return stringBuilder.toString();
    }*/

    private void set_the_text_at_the_top(String type) {
        if (getView() != null) {
            TextView add_comment_id_in_full_comment = getView().findViewById(R.id.add_comment_id_in_full_comment);
            if(type.equals("comment")){
                add_comment_id_in_full_comment.setText("Add a comment");
            } else if(type.equals("reply")){
                add_comment_id_in_full_comment.setText("Add a reply");
            } else if(type.equals("reply_to_reply")){
                add_comment_id_in_full_comment.setText("Add a reply");
            }
        }
    }
}