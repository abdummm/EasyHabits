package com.easyhabitsapp.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class after_login extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private com.easyhabitsapp.android.home_fragment home_fragment;
    private Posts_fragment posts_fragment;
    private com.easyhabitsapp.android.chat_fragment chat_fragment;
    private com.easyhabitsapp.android.habits_fragment habits_fragment;
    private Settings_fragment settings_fragment;
    private com.easyhabitsapp.android.emergency_button_fragment emergency_button_fragment;
    private Fragment active_fragment;
    private boolean pressed_programitically = false;

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    private BillingClient billingClient;
    private PurchasesUpdatedListener purchasesUpdatedListener;
    private AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener;
    private FirebaseFunctions firebaseFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        check_if_i_should_restart();
        home_fragment = new home_fragment();
        posts_fragment = new Posts_fragment();
        chat_fragment = new chat_fragment();
        habits_fragment = new habits_fragment();
        settings_fragment = new Settings_fragment();
        emergency_button_fragment = new emergency_button_fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, home_fragment, "home").commitNow();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, posts_fragment, "posts").hide(posts_fragment).commitNow();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, chat_fragment, "chat").hide(chat_fragment).commitNow();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, habits_fragment, "habits").hide(habits_fragment).commitNow();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, settings_fragment, "setting").hide(settings_fragment).commitNow();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, emergency_button_fragment, "emergency").hide(emergency_button_fragment).commitNow();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(R.id.navigation_home);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        check_extra();
        check_if_user_is_gifted(false);
        set_up_billing();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getOrder()) {
                case 0:
                    continue_switching_or_no(1);
                    break;
                case 1:
                    continue_switching_or_no(2);
                    break;
                case 2:
                    continue_switching_or_no(3);
                    break;
                case 3:
                    continue_switching_or_no(4);
                    break;
                case 4:
                    continue_switching_or_no(5);
                    break;
            }
            updateNavigationBarState(item.getItemId());
            return true;
        }
    };

    private void updateNavigationBarState(int actionId) {
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        Menu menu = bottomNavigationView.getMenu();

        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            item.setChecked(item.getItemId() == actionId);
        }
    }

    @Override
    public void onBackPressed() {
        /*home_fragment home_fragment = (home_fragment) getSupportFragmentManager().findFragmentByTag("home");
        Posts_fragment posts_fragment = (Posts_fragment) getSupportFragmentManager().findFragmentByTag("posts");
        chat_fragment chat_fragment = (chat_fragment) getSupportFragmentManager().findFragmentByTag("chat");
        habits_fragment habits_fragment = (habits_fragment) getSupportFragmentManager().findFragmentByTag("habits");
        Settings_fragment settings_fragment = (Settings_fragment) getSupportFragmentManager().findFragmentByTag("setting");*/
        com.easyhabitsapp.android.emergency_button_fragment emergency_button_fragment = (com.easyhabitsapp.android.emergency_button_fragment) getSupportFragmentManager().findFragmentByTag("emergency");
        View_home_habit view_home_habit = (View_home_habit) getSupportFragmentManager().findFragmentByTag("view home");
        add_a_habit add_a_habit = (com.easyhabitsapp.android.add_a_habit) getSupportFragmentManager().findFragmentByTag("add a habit");
        mood_tracker mood_tracker = (com.easyhabitsapp.android.mood_tracker) getSupportFragmentManager().findFragmentByTag("mood tracker");
        post_a_post post_a_post = (com.easyhabitsapp.android.post_a_post) getSupportFragmentManager().findFragmentByTag("write a post");
        Show_full_post show_full_post = (Show_full_post) getSupportFragmentManager().findFragmentByTag("show full post");
        Write_comment_for_post write_comment_for_post = (Write_comment_for_post) getSupportFragmentManager().findFragmentByTag("write a comment");
        Give_coins give_coins = (Give_coins) getSupportFragmentManager().findFragmentByTag("give_coins");
        edit_bad_habits edit_bad_habits = (edit_bad_habits) getSupportFragmentManager().findFragmentByTag("edit bad habit");
        Buy_premuim buy_premuim = (Buy_premuim) getSupportFragmentManager().findFragmentByTag("buy premium");
        if (home_fragment != null && home_fragment.isVisible()) {
            exit_app_dialog();
        } else if (posts_fragment != null && posts_fragment.isVisible()) {
            exit_app_dialog();
        } else if (chat_fragment != null && chat_fragment.isVisible()) {
            exit_app_dialog();
        } else if (habits_fragment != null && habits_fragment.isVisible()) {
            /*if (habits_fragment.return_the_screen() == 3) {
                habits_fragment.open_a_screen(2);
                habits_fragment.restart_third_screen_good_information();
            } else if (habits_fragment.return_the_screen() == 2 || habits_fragment.return_the_screen() == 0) {
                exit_app_dialog();
            } else if (habits_fragment.return_the_screen() == 1) {
                if (!habits_fragment.return_edit_mode()) {
                    if (habits_fragment.is_there_is_any_information_saved()) {
                        habits_fragment.open_a_screen(2);
                        habits_fragment.restart_values();
                    } else {
                        habits_fragment.open_a_screen(0);
                        habits_fragment.restart_values();
                    }
                } else {
                    habits_fragment.restart_values();
                    habits_fragment.restart_third_screen_good_information();
                    habits_fragment.open_a_screen(3);
                    habits_fragment.make_the_edit_false_good_habit();
                }
            }*/
        } else if (settings_fragment != null && settings_fragment.isVisible()) {
            exit_app_dialog();
        } else if (emergency_button_fragment != null && emergency_button_fragment.isVisible() && home_fragment != null) {
            getSupportFragmentManager().beginTransaction().show(home_fragment).hide(emergency_button_fragment).commitNow();
            emergency_button_fragment.scroll_to_top();
        } else if (view_home_habit != null && view_home_habit.isVisible() && home_fragment != null) {
            remove_view_home();
            getSupportFragmentManager().beginTransaction().show(home_fragment).commitNow();
        } else if (add_a_habit != null && add_a_habit.isVisible() && home_fragment != null) {
            getSupportFragmentManager().beginTransaction().show(home_fragment).remove(add_a_habit).commitNow();
        } else if (mood_tracker != null && mood_tracker.isVisible() && habits_fragment != null) {
            remove_mood_tracker();
            remove_and_add_habits();
            getSupportFragmentManager().beginTransaction().show(habits_fragment).commitNow();
        } else if (post_a_post != null && post_a_post.isVisible() && posts_fragment != null) {
            if (post_a_post.should_i_remove_post_a_post_fragment()) {
                new AlertDialog.Builder(this)
                        .setTitle("Discard Post?")
                        .setMessage("Are you sure you want to discard this post?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                remove_the_post_a_post_fragment();
                                getSupportFragmentManager().beginTransaction().show(posts_fragment).commitNow();
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            } else {
                remove_the_post_a_post_fragment();
                getSupportFragmentManager().beginTransaction().show(posts_fragment).commitNow();
            }
        } else if (show_full_post != null && show_full_post.isVisible() && posts_fragment != null) {
            show_full_post.back_was_pressed();
        } else if (write_comment_for_post != null && write_comment_for_post.isVisible() && show_full_post != null) {
            write_comment_for_post.x_or_back_was_pressed();
        } else if (give_coins != null && give_coins.isVisible() && posts_fragment != null && give_coins.return_what_called_you().equals("posts")) {
            remove_give_coisn();
            getSupportFragmentManager().beginTransaction().show(posts_fragment).commitNow();
        } else if (give_coins != null && give_coins.isVisible() && show_full_post != null && give_coins.return_what_called_you().equals("show")) {
            remove_give_coisn();
            getSupportFragmentManager().beginTransaction().show(show_full_post).commitNow();
        } else if (edit_bad_habits != null && edit_bad_habits.isVisible() && view_home_habit != null) {
            remove_edit_bad_habit();
            getSupportFragmentManager().beginTransaction().show(view_home_habit).commitNow();
        } else if (buy_premuim != null && buy_premuim.isVisible() && buy_premuim.return_caller().equals("home") && home_fragment != null) {
            remove_buy_premuim();
            getSupportFragmentManager().beginTransaction().show(home_fragment).commitNow();
        } else if (buy_premuim != null && buy_premuim.isVisible() && buy_premuim.return_caller().equals("posts") && posts_fragment != null) {
            remove_buy_premuim();
            getSupportFragmentManager().beginTransaction().show(posts_fragment).commitNow();
        } else if (buy_premuim != null && buy_premuim.isVisible() && buy_premuim.return_caller().equals("show full post") && show_full_post != null) {
            remove_buy_premuim();
            getSupportFragmentManager().beginTransaction().show(show_full_post).commitNow();
        } else if (buy_premuim != null && buy_premuim.isVisible() && buy_premuim.return_caller().equals("chat") && chat_fragment != null) {
            remove_buy_premuim();
            getSupportFragmentManager().beginTransaction().show(chat_fragment).commitNow();
        } else if (buy_premuim != null && buy_premuim.isVisible() && buy_premuim.return_caller().equals("good habits") && habits_fragment != null) {
            remove_buy_premuim();
            getSupportFragmentManager().beginTransaction().show(habits_fragment).commitNow();
        } else if (buy_premuim != null && buy_premuim.isVisible() && buy_premuim.return_caller().equals("activity")) {
            buy_premuim.go_back_to_activity();
        } else if (buy_premuim != null && buy_premuim.isVisible() && buy_premuim.return_caller().equals("view home") && habits_fragment != null) {
            remove_buy_premuim();
            getSupportFragmentManager().beginTransaction().show(view_home_habit).commitNow();
        } else if (buy_premuim != null && buy_premuim.isVisible() && buy_premuim.return_caller().equals("mood tracker") && habits_fragment != null){
            getSupportFragmentManager().beginTransaction().show(mood_tracker).commitNow();
        }
    }

    private void exit_app_dialog() {
        new AlertDialog.Builder(this)
                .setTitle("Exit app")
                .setMessage("Are you sure you want to exit this app?")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finishAndRemoveTask();
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

    private void check_extra() {
        Intent intent = getIntent();
        boolean data = intent.getBooleanExtra("Start_the_emergency_true", false);
        if (data) {
            getSupportFragmentManager().beginTransaction().hide(home_fragment).show(habits_fragment).commitNow();
        }
    }

    private void remove_add_object() {
        add_a_habit add_a_habit = (com.easyhabitsapp.android.add_a_habit) getSupportFragmentManager().findFragmentByTag("add a habit");
        if (add_a_habit != null && add_a_habit.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(add_a_habit).commitNow();
        }
    }

    private void remove_view_home() {
        View_home_habit view_home_habit = (View_home_habit) getSupportFragmentManager().findFragmentByTag("view home");
        if (view_home_habit != null && view_home_habit.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(view_home_habit).commitNow();
            remove_and_add_habits();
        }
    }

    private void remove_mood_tracker() {
        mood_tracker mood_tracker = (mood_tracker) getSupportFragmentManager().findFragmentByTag("mood tracker");
        if (mood_tracker != null && mood_tracker.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(mood_tracker).commitNow();
            remove_and_add_habits();
        }
    }

    private void remove_emergency() {
        com.easyhabitsapp.android.emergency_button_fragment myFragment = (com.easyhabitsapp.android.emergency_button_fragment) getSupportFragmentManager().findFragmentByTag("emergency");
        if (myFragment != null && myFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(myFragment).commitNow();
        }
    }

    private void remove_and_add_habits() {
        habits_fragment = new habits_fragment();
        habits_fragment old_fragment = (habits_fragment) getSupportFragmentManager().findFragmentByTag("habits");
        if (old_fragment != null) {
            getSupportFragmentManager().beginTransaction().remove(old_fragment).commitNow();
        }
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, habits_fragment, "habits").hide(habits_fragment).commitNow();
    }

    private void remove_give_coisn() {
        Give_coins give_coins = (Give_coins) getSupportFragmentManager().findFragmentByTag("give_coins");
        if (give_coins != null && give_coins.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(give_coins).commitNow();
        }
    }

    private void remove_edit_bad_habit() {
        edit_bad_habits edit_bad_habits = (edit_bad_habits) getSupportFragmentManager().findFragmentByTag("edit bad habit");
        if (edit_bad_habits != null && edit_bad_habits.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(edit_bad_habits).commitNow();
        }
    }

    private void remove_buy_premuim() {
        Buy_premuim buy_premuim = (Buy_premuim) getSupportFragmentManager().findFragmentByTag("buy premium");
        if (buy_premuim != null && buy_premuim.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(buy_premuim).commitNow();
        }
    }

    private void hide_all() {
        com.easyhabitsapp.android.home_fragment home_fragment = (com.easyhabitsapp.android.home_fragment) getSupportFragmentManager().findFragmentByTag("home");
        Posts_fragment posts_fragment = (Posts_fragment) getSupportFragmentManager().findFragmentByTag("posts");
        com.easyhabitsapp.android.chat_fragment chat_fragment = (com.easyhabitsapp.android.chat_fragment) getSupportFragmentManager().findFragmentByTag("chat");
        com.easyhabitsapp.android.habits_fragment habits_fragment = (com.easyhabitsapp.android.habits_fragment) getSupportFragmentManager().findFragmentByTag("habits");
        Settings_fragment settings_fragment = (Settings_fragment) getSupportFragmentManager().findFragmentByTag("setting");
        getSupportFragmentManager().beginTransaction().hide(home_fragment).commitNow();
        getSupportFragmentManager().beginTransaction().hide(posts_fragment).commitNow();
        getSupportFragmentManager().beginTransaction().hide(chat_fragment).commitNow();
        getSupportFragmentManager().beginTransaction().hide(habits_fragment).commitNow();
        getSupportFragmentManager().beginTransaction().hide(settings_fragment).commitNow();
    }

    private void define_the_habits() {
        habits_fragment = (com.easyhabitsapp.android.habits_fragment) getSupportFragmentManager().findFragmentByTag("habits");
    }

    private void check_if_i_should_restart() {
        boolean data = ((First_time_running) getApplicationContext()).return_restart();
        if (data) {
            Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void remove_the_post_a_post_fragment() {
        post_a_post myFragment = (post_a_post) getSupportFragmentManager().findFragmentByTag("write a post");
        if (myFragment != null && myFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(myFragment).commitNow();
        }
    }

    private void continue_switching_or_no(final int which_was_pressed) {
        post_a_post post_a_post = (com.easyhabitsapp.android.post_a_post) getSupportFragmentManager().findFragmentByTag("write a post");
        Write_comment_for_post write_comment_for_post = (Write_comment_for_post) getSupportFragmentManager().findFragmentByTag("write a comment");
        boolean first = false;
        boolean second = false;
        if (post_a_post != null && post_a_post.isVisible()) {
            first = true;
        }
        if (first && post_a_post.should_i_remove_post_a_post_fragment()) {
            second = true;
        }
        if (first && second && !pressed_programitically) {
            new AlertDialog.Builder(this)
                    .setTitle("Discard Post?")
                    .setMessage("Are you sure you want to discard this post?")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (which_was_pressed == 1) {
                                post_was_pressed();
                            } else if (which_was_pressed == 2) {
                                chat_was_pressed();
                            } else if (which_was_pressed == 3) {
                                home_was_pressed();
                            } else if (which_was_pressed == 4) {
                                habits_was_pressed();
                            } else if (which_was_pressed == 5) {
                                settings_was_pressed();
                            }
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            pressed_programitically = true;
                            BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
                            bottomNavigationView.setSelectedItemId(R.id.navigation_stats);
                        }
                    })
                    .show();
        } else if (write_comment_for_post != null && write_comment_for_post.isVisible() && !write_comment_for_post.is_edit_text_empty() && !pressed_programitically) {
            new AlertDialog.Builder(this)
                    .setTitle("Discard Comment?")
                    .setMessage("Are you sure you want to discard this comment?")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (which_was_pressed == 1) {
                                post_was_pressed();
                            } else if (which_was_pressed == 2) {
                                chat_was_pressed();
                            } else if (which_was_pressed == 3) {
                                home_was_pressed();
                            } else if (which_was_pressed == 4) {
                                habits_was_pressed();
                            } else if (which_was_pressed == 5) {
                                settings_was_pressed();
                            }
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            pressed_programitically = true;
                            BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
                            bottomNavigationView.setSelectedItemId(R.id.navigation_stats);
                        }
                    })
                    .show();
        } else {
            if (pressed_programitically) {
                pressed_programitically = false;
            } else {
                if (which_was_pressed == 1) {
                    post_was_pressed();
                } else if (which_was_pressed == 2) {
                    chat_was_pressed();
                } else if (which_was_pressed == 3) {
                    home_was_pressed();
                } else if (which_was_pressed == 4) {
                    habits_was_pressed();
                } else if (which_was_pressed == 5) {
                    settings_was_pressed();
                }
            }
        }
    }

    private void home_was_pressed() {
        remove_emergency();
        remove_view_home();
        remove_add_object();
        remove_mood_tracker();
        define_the_habits();
        remove_the_post_a_post_fragment();
        update_post_remote();
        remove_the_full_post();
        remove_the_write_a_comment();
        remove_give_coisn();
        remove_edit_bad_habit();
        remove_buy_premuim();
        hide_all();
        getSupportFragmentManager().beginTransaction().show(home_fragment).commitNow();
        //habits_fragment.return_the_first_screen();
        emergency_button_fragment.scroll_to_top();
    }

    private void post_was_pressed() {
        remove_emergency();
        remove_view_home();
        remove_add_object();
        remove_mood_tracker();
        define_the_habits();
        remove_the_post_a_post_fragment();
        update_post_remote();
        remove_the_full_post();
        remove_the_write_a_comment();
        remove_give_coisn();
        remove_edit_bad_habit();
        remove_buy_premuim();
        hide_all();
        getSupportFragmentManager().beginTransaction().show(posts_fragment).commitNow();
        //habits_fragment.return_the_first_screen();
        emergency_button_fragment.scroll_to_top();
    }

    private void chat_was_pressed() {
        remove_emergency();
        remove_view_home();
        remove_add_object();
        remove_mood_tracker();
        define_the_habits();
        remove_the_post_a_post_fragment();
        update_post_remote();
        remove_the_full_post();
        remove_the_write_a_comment();
        remove_give_coisn();
        remove_edit_bad_habit();
        remove_buy_premuim();
        hide_all();
        getSupportFragmentManager().beginTransaction().show(chat_fragment).commitNow();
        //habits_fragment.return_the_first_screen();
        emergency_button_fragment.scroll_to_top();
    }

    private void habits_was_pressed() {
        remove_emergency();
        remove_view_home();
        remove_add_object();
        remove_mood_tracker();
        define_the_habits();
        remove_the_post_a_post_fragment();
        update_post_remote();
        remove_the_full_post();
        remove_the_write_a_comment();
        remove_give_coisn();
        remove_edit_bad_habit();
        remove_buy_premuim();
        hide_all();
        getSupportFragmentManager().beginTransaction().show(habits_fragment).commitNow();
        //habits_fragment.return_the_first_screen();
        emergency_button_fragment.scroll_to_top();
    }

    private void settings_was_pressed() {
        remove_emergency();
        remove_view_home();
        remove_add_object();
        remove_mood_tracker();
        define_the_habits();
        remove_the_post_a_post_fragment();
        update_post_remote();
        remove_the_full_post();
        remove_the_write_a_comment();
        remove_give_coisn();
        remove_edit_bad_habit();
        remove_buy_premuim();
        hide_all();
        getSupportFragmentManager().beginTransaction().show(settings_fragment).commitNow();
        //habits_fragment.return_the_first_screen();
        emergency_button_fragment.scroll_to_top();
    }

    private void remove_show_the_post() {
        Show_image_post myFragment = (Show_image_post) getSupportFragmentManager().findFragmentByTag("show post image");
        if (myFragment != null && myFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(myFragment).commitNow();
        }
    }

    private void remove_the_full_post() {
        Show_full_post myFragment = (Show_full_post) getSupportFragmentManager().findFragmentByTag("show full post");
        if (myFragment != null && myFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(myFragment).commitNow();
        }
    }

    private void update_post_remote() {
        Show_full_post myFragment = (Show_full_post) getSupportFragmentManager().findFragmentByTag("show full post");
        if (myFragment != null && myFragment.isVisible()) {
            myFragment.update_teh_adapter_remote();
        }
    }

    private void remove_the_write_a_comment() {
        Write_comment_for_post write_comment_for_post = (Write_comment_for_post) getSupportFragmentManager().findFragmentByTag("write a comment");
        if (write_comment_for_post != null && write_comment_for_post.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(write_comment_for_post).commitNow();
        }
    }

    public void check_if_user_is_gifted(boolean run_anyways) {
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build();
        firebaseFirestore.setFirestoreSettings(settings);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        SharedPreferences sharedPreferences = getSharedPreferences("is_user_gifted", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            myEdit.putBoolean("premuim", false);
        } else {
            //if (sharedPreferences.getLong("last_checked", 0) < System.currentTimeMillis() - 86400000L || run_anyways) {
                firebaseFirestore.collection("gifts").document(firebaseUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                long time = document.getLong("time");
                                if (time > System.currentTimeMillis() - 86400000L) {
                                    myEdit.putBoolean("premuim", true);
                                    myEdit.putLong("premuim_until", time+86400000L);
                                    myEdit.commit();
                                } else {
                                    myEdit.putBoolean("premuim", false);
                                    myEdit.commit();
                                }
                            } else {
                                myEdit.putBoolean("premuim", false);
                                myEdit.commit();
                            }
                        } else {
                            //fail
                        }
                    }
                });
                //myEdit.putLong("last_checked", System.currentTimeMillis());
            //}
        }
    }

    private void set_up_billing() {
        acknowledgePurchaseResponseListener = new AcknowledgePurchaseResponseListener() {
            @Override
            public void onAcknowledgePurchaseResponse(@NonNull @NotNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {

                }
            }
        };
        purchasesUpdatedListener = new PurchasesUpdatedListener() {
            @Override
            public void onPurchasesUpdated(@NonNull @NotNull BillingResult billingResult, @Nullable @org.jetbrains.annotations.Nullable List<Purchase> list) {

            }
        };
        start_billing_connection(true);
    }

    public void start_billing_connection(boolean first_time) {
        SharedPreferences sharedPreferences = getSharedPreferences("is_user_gifted", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        if (first_time) {
            billingClient = set_up_clint();
        }
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull @NotNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    // billingClient.queryPurchasesAsync(BillingClient.SkuType.INAPP,purchasesResponseListener);
                    List<Purchase> purhcases = billingClient.queryPurchases(BillingClient.SkuType.SUBS).getPurchasesList();
                    for(int i = 0;i<purhcases.size();i++){
                        handle_purchases_sub(purhcases.get(i));
                    }
                    if (purhcases.size() > 0) {
                        if(purhcases.get(0).getSku().equals("1_year_subscription")){
                            myEdit.putString("type", "year");
                        } else if(purhcases.get(0).getSku().equals("1_month_subscription")){
                            myEdit.putString("type", "month");
                        }
                        myEdit.putBoolean("premuim_online", true);
                        myEdit.commit();
                    } else {
                        myEdit.putBoolean("premuim_online", false);
                        myEdit.commit();
                    }
                    List<Purchase> puchases_in_app = billingClient.queryPurchases(BillingClient.SkuType.INAPP).getPurchasesList();
                    Save_and_retrive_user_id save_and_retrive_user_id = new Save_and_retrive_user_id();
                    for(int i = 0;i<puchases_in_app.size();i++){
                        if(purhcases.get(i).getSku().equals("1_month_premium_gift")){
                            handle_purchase(purhcases.get(i),save_and_retrive_user_id.retrive_the_id(after_login.this,purhcases.get(i).getPurchaseTime(),1));
                        } else if(purhcases.get(i).getSku().equals("3_month_premium_gift")){
                            handle_purchase(purhcases.get(i),save_and_retrive_user_id.retrive_the_id(after_login.this,purhcases.get(i).getPurchaseTime(),3));
                        } else if(purhcases.get(i).getSku().equals("6_month_premium_gift")){
                            handle_purchase(purhcases.get(i),save_and_retrive_user_id.retrive_the_id(after_login.this,purhcases.get(i).getPurchaseTime(),6));
                        } else if(purhcases.get(i).getSku().equals("12_month_premium_gift")){
                            handle_purchase(purhcases.get(i),save_and_retrive_user_id.retrive_the_id(after_login.this,purhcases.get(i).getPurchaseTime(),12));
                        } else {
                            handle_purchase(purhcases.get(i),null);
                        }
                    }

                } else {
                    Toast.makeText(after_login.this, "An error occurred. Please try again later", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                start_billing_connection(false);
            }
        });
    }

    private BillingClient set_up_clint() {
        BillingClient billingClient = BillingClient.newBuilder(this)
                .enablePendingPurchases()
                .setListener(purchasesUpdatedListener)
                .build();
        return billingClient;
    }

    private void handle_purchase(Purchase purchase,String user_id) {
        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
            Save_and_retrive_user_id save_and_retrive_user_id = new Save_and_retrive_user_id();
            if (purchase.getSku().equals("1_month_premium_gift")) {
                ConsumeParams consumeParams =
                        ConsumeParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();
                ConsumeResponseListener listener = new ConsumeResponseListener() {
                    @Override
                    public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            Map<String, Object> data = new HashMap<>();
                            data.put("userId", user_id);
                            data.put("token", purchaseToken);
                            data.put("orderId", purchase.getOrderId());
                            firebaseFunctions = FirebaseFunctions.getInstance();
                            firebaseFunctions
                                    .getHttpsCallable("purchaseValidationAndroid")
                                    .call(data)
                                    .continueWith(new Continuation<HttpsCallableResult, String>() {
                                        @Override
                                        public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                            if (String.valueOf(task.getResult().getData()).equals("success")) {
                                                Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(0, false, 1);
                                                dialog_thank_user_for_purchase.show(getSupportFragmentManager(), "");
                                            } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                                Toast.makeText(after_login.this, "Gift failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                            }
                                            return "result";
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {

                                }
                            });
                            save_and_retrive_user_id.remove_the_id(after_login.this,1);
                        }
                    }
                };
                billingClient.consumeAsync(consumeParams, listener);
            } else if (purchase.getSku().equals("3_month_premium_gift")) {
                ConsumeParams consumeParams =
                        ConsumeParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();
                ConsumeResponseListener listener = new ConsumeResponseListener() {
                    @Override
                    public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            Map<String, Object> data = new HashMap<>();
                            data.put("userId", user_id);
                            data.put("token", purchaseToken);
                            data.put("orderId", purchase.getOrderId());
                            firebaseFunctions = FirebaseFunctions.getInstance();
                            firebaseFunctions
                                    .getHttpsCallable("purchaseValidationAndroid_3_month")
                                    .call(data)
                                    .continueWith(new Continuation<HttpsCallableResult, String>() {
                                        @Override
                                        public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                            if (String.valueOf(task.getResult().getData()).equals("success")) {
                                                Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(0, false, 3);
                                                dialog_thank_user_for_purchase.show(getSupportFragmentManager(), "");
                                            } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                                Toast.makeText(after_login.this, "Gift failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                            }
                                            return "result";
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {

                                }
                            });
                            save_and_retrive_user_id.remove_the_id(after_login.this,3);
                        }
                    }
                };

                billingClient.consumeAsync(consumeParams, listener);
            } else if (purchase.getSku().equals("6_month_premium_gift")) {
                ConsumeParams consumeParams =
                        ConsumeParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();

                ConsumeResponseListener listener = new ConsumeResponseListener() {
                    @Override
                    public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            Map<String, Object> data = new HashMap<>();
                            data.put("userId", user_id);
                            data.put("token", purchaseToken);
                            data.put("orderId", purchase.getOrderId());
                            firebaseFunctions = FirebaseFunctions.getInstance();
                            firebaseFunctions
                                    .getHttpsCallable("purchaseValidationAndroid_6_month")
                                    .call(data)
                                    .continueWith(new Continuation<HttpsCallableResult, String>() {
                                        @Override
                                        public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                            if (String.valueOf(task.getResult().getData()).equals("success")) {
                                                Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(0, false, 6);
                                                dialog_thank_user_for_purchase.show(getSupportFragmentManager(), "");
                                            } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                                Toast.makeText(after_login.this, "Gift failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                            }
                                            return "result";
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {

                                }
                            });
                            save_and_retrive_user_id.remove_the_id(after_login.this,6);
                        }
                    }
                };

                billingClient.consumeAsync(consumeParams, listener);
            } else if (purchase.getSku().equals("12_month_premium_gift")) {
                ConsumeParams consumeParams =
                        ConsumeParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();

                ConsumeResponseListener listener = new ConsumeResponseListener() {
                    @Override
                    public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            Map<String, Object> data = new HashMap<>();
                            data.put("userId", user_id);
                            data.put("token", purchaseToken);
                            data.put("orderId", purchase.getOrderId());
                            firebaseFunctions = FirebaseFunctions.getInstance();
                            firebaseFunctions
                                    .getHttpsCallable("purchaseValidationAndroid_12_month")
                                    .call(data)
                                    .continueWith(new Continuation<HttpsCallableResult, String>() {
                                        @Override
                                        public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                            if (String.valueOf(task.getResult().getData()).equals("success")) {
                                                Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(0, false, 12);
                                                dialog_thank_user_for_purchase.show(getSupportFragmentManager(), "");
                                            } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                                Toast.makeText(after_login.this, "Gift failed. You will not be charged and will receive a refund", Toast.LENGTH_LONG).show();
                                            }
                                            return "result";
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {

                                }
                            });
                            save_and_retrive_user_id.remove_the_id(after_login.this,12);
                        }
                    }
                };

                billingClient.consumeAsync(consumeParams, listener);
            } else if (purchase.getSku().equals("400_coins")){
                ConsumeParams consumeParams =
                        ConsumeParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();
                ConsumeResponseListener listener = new ConsumeResponseListener() {
                    @Override
                    public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            Toast.makeText(after_login.this, "Buying 400 coins...", Toast.LENGTH_LONG).show();
                            Map<String, Object> data = new HashMap<>();
                            data.put("token", purchaseToken);
                            data.put("orderId", purchase.getOrderId());
                            firebaseFunctions = FirebaseFunctions.getInstance();
                            firebaseFunctions
                                    .getHttpsCallable("buy400coins")
                                    .call(data)
                                    .continueWith(new Continuation<HttpsCallableResult, String>() {
                                        @Override
                                        public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                            if(String.valueOf(task.getResult().getData()).equals("success")){
                                                Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(400);
                                                dialog_thank_user_for_purchase.show(getSupportFragmentManager(),"");
                                            } else if (String.valueOf(task.getResult().getData()).equals("error")){
                                                Toast.makeText(after_login.this, "Buying failed. You will not be charged and will receive a refund",Toast.LENGTH_LONG).show();
                                            }
                                            return "result";
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {

                                }
                            });
                        }
                    }
                };
                billingClient.consumeAsync(consumeParams, listener);
            }  else if (purchase.getSku().equals("1500_coins")){
                ConsumeParams consumeParams =
                        ConsumeParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();
                ConsumeResponseListener listener = new ConsumeResponseListener() {
                    @Override
                    public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            Toast.makeText(after_login.this, "Buying 1500 coins...", Toast.LENGTH_LONG).show();
                            Map<String, Object> data = new HashMap<>();
                            data.put("token", purchaseToken);
                            data.put("orderId", purchase.getOrderId());
                            firebaseFunctions = FirebaseFunctions.getInstance();
                            firebaseFunctions
                                    .getHttpsCallable("buy1500coins")
                                    .call(data)
                                    .continueWith(new Continuation<HttpsCallableResult, String>() {
                                        @Override
                                        public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                            Log.w("asdasd",String.valueOf(task.getResult().getData()));
                                            if(String.valueOf(task.getResult().getData()).equals("success")){
                                                Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(1500);
                                                dialog_thank_user_for_purchase.show(getSupportFragmentManager(),"");
                                            } else if (String.valueOf(task.getResult().getData()).equals("error")){
                                                Toast.makeText(after_login.this, "Buying failed. You will not be charged and will receive a refund",Toast.LENGTH_LONG).show();
                                            }
                                            return "result";
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {

                                }
                            });
                        }
                    }
                };

                billingClient.consumeAsync(consumeParams, listener);
            }  else if (purchase.getSku().equals("3600_coins")){
                ConsumeParams consumeParams =
                        ConsumeParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();

                ConsumeResponseListener listener = new ConsumeResponseListener() {
                    @Override
                    public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            Toast.makeText(after_login.this, "Buying 3600 coins...", Toast.LENGTH_LONG).show();
                            Map<String, Object> data = new HashMap<>();
                            data.put("token", purchaseToken);
                            data.put("orderId", purchase.getOrderId());
                            firebaseFunctions = FirebaseFunctions.getInstance();
                            firebaseFunctions
                                    .getHttpsCallable("buy3600coins")
                                    .call(data)
                                    .continueWith(new Continuation<HttpsCallableResult, String>() {
                                        @Override
                                        public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                            Log.w("asdasd",String.valueOf(task.getResult().getData()));
                                            if(String.valueOf(task.getResult().getData()).equals("success")){
                                                Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(3600);
                                                dialog_thank_user_for_purchase.show(getSupportFragmentManager(),"");
                                            } else if (String.valueOf(task.getResult().getData()).equals("error")){
                                                Toast.makeText(after_login.this, "Buying failed. You will not be charged and will receive a refund",Toast.LENGTH_LONG).show();
                                            }
                                            return "result";
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {

                                }
                            });
                        }
                    }
                };

                billingClient.consumeAsync(consumeParams, listener);
            }  else if (purchase.getSku().equals("8400_coins")){
                ConsumeParams consumeParams =
                        ConsumeParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();

                ConsumeResponseListener listener = new ConsumeResponseListener() {
                    @Override
                    public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            Toast.makeText(after_login.this, "Buying 8400 coins...", Toast.LENGTH_LONG).show();
                            Map<String, Object> data = new HashMap<>();
                            data.put("token", purchaseToken);
                            data.put("orderId", purchase.getOrderId());
                            firebaseFunctions = FirebaseFunctions.getInstance();
                            firebaseFunctions
                                    .getHttpsCallable("buy8400coins")
                                    .call(data)
                                    .continueWith(new Continuation<HttpsCallableResult, String>() {
                                        @Override
                                        public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                            Log.w("asdasd",String.valueOf(task.getResult().getData()));
                                            if(String.valueOf(task.getResult().getData()).equals("success")){
                                                Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(8400);
                                                dialog_thank_user_for_purchase.show(getSupportFragmentManager(),"");
                                            } else if (String.valueOf(task.getResult().getData()).equals("error")){
                                                Toast.makeText(after_login.this, "Buying failed. You will not be charged and will receive a refund",Toast.LENGTH_LONG).show();
                                            }
                                            return "result";
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {

                                }
                            });
                        }
                    }
                };
                billingClient.consumeAsync(consumeParams, listener);
            }  else if (purchase.getSku().equals("16000_coins")){
                ConsumeParams consumeParams =
                        ConsumeParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();

                ConsumeResponseListener listener = new ConsumeResponseListener() {
                    @Override
                    public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            Toast.makeText(after_login.this, "Buying 16000 coins...", Toast.LENGTH_LONG).show();
                            Map<String, Object> data = new HashMap<>();
                            data.put("token", purchaseToken);
                            data.put("orderId", purchase.getOrderId());
                            firebaseFunctions = FirebaseFunctions.getInstance();
                            firebaseFunctions
                                    .getHttpsCallable("buy16000coins")
                                    .call(data)
                                    .continueWith(new Continuation<HttpsCallableResult, String>() {
                                        @Override
                                        public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                            Log.w("asdasd",String.valueOf(task.getResult().getData()));
                                            if(String.valueOf(task.getResult().getData()).equals("success")){
                                                Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(16000);
                                                dialog_thank_user_for_purchase.show(getSupportFragmentManager(),"");
                                            } else if (String.valueOf(task.getResult().getData()).equals("error")){
                                                Toast.makeText(after_login.this, "Buying failed. You will not be charged and will receive a refund",Toast.LENGTH_LONG).show();
                                            }
                                            return "result";
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {

                                }
                            });
                        }
                    }
                };

                billingClient.consumeAsync(consumeParams, listener);
            }  else if (purchase.getSku().equals("40000_coins")){
                ConsumeParams consumeParams =
                        ConsumeParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();

                ConsumeResponseListener listener = new ConsumeResponseListener() {
                    @Override
                    public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            Toast.makeText(after_login.this, "Buying 40000 coins...", Toast.LENGTH_LONG).show();
                            Map<String, Object> data = new HashMap<>();
                            data.put("token", purchaseToken);
                            data.put("orderId", purchase.getOrderId());
                            firebaseFunctions = FirebaseFunctions.getInstance();
                            firebaseFunctions
                                    .getHttpsCallable("buy40000coins")
                                    .call(data)
                                    .continueWith(new Continuation<HttpsCallableResult, String>() {
                                        @Override
                                        public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                            Log.w("asdasd",String.valueOf(task.getResult().getData()));
                                            if(String.valueOf(task.getResult().getData()).equals("success")){
                                                Dialog_thank_user_for_purchase dialog_thank_user_for_purchase = new Dialog_thank_user_for_purchase(40000);
                                                dialog_thank_user_for_purchase.show(getSupportFragmentManager(),"");
                                            } else if (String.valueOf(task.getResult().getData()).equals("error")){
                                                Toast.makeText(after_login.this, "Buying failed. You will not be charged and will receive a refund",Toast.LENGTH_LONG).show();
                                            }
                                            return "result";
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {

                                }
                            });
                        }
                    }
                };

                billingClient.consumeAsync(consumeParams, listener);
            }
        } else if (purchase.getPurchaseState() == Purchase.PurchaseState.PENDING) {
            //Toast.makeText(this, "purchase is being processed", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(this, "purchase is being processed", Toast.LENGTH_SHORT).show();
        }
    }

    private void handle_purchases_sub(Purchase purchase){
        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
            if (purchase.getSku().equals("1_month_subscription")) {
                if (!purchase.isAcknowledged()) {
                    set_buy_premuim_true();
                    AcknowledgePurchaseParams acknowledgePurchaseParams =
                            AcknowledgePurchaseParams.newBuilder()
                                    .setPurchaseToken(purchase.getPurchaseToken())
                                    .build();
                    if(am_i_sign_in_with_google()){
                        Map<String, Object> data = new HashMap<>();
                        data.put("token", purchase.getPurchaseToken());
                        data.put("orderId", purchase.getOrderId());
                        Dialog_subscribe_to_premuim dialog_subscribe_to_premuim = new Dialog_subscribe_to_premuim("month",false);
                        dialog_subscribe_to_premuim.setCancelable(false);
                        dialog_subscribe_to_premuim.show(getSupportFragmentManager(), "");
                        firebaseFunctions = FirebaseFunctions.getInstance();
                        firebaseFunctions
                                .getHttpsCallable("monthly_sub_i_buy")
                                .call(data)
                                .continueWith(new Continuation<HttpsCallableResult, String>() {
                                    @Override
                                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                        if (String.valueOf(task.getResult().getData()).equals("success")) {
                                            dialog_subscribe_to_premuim.loaded();
                                            billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
                                        } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                            Toast.makeText(after_login.this, "Verifying purchase with server failed. Please try restarting the app with internet connection. If that doesn't work contact supprt", Toast.LENGTH_LONG).show();                                    }
                                        return "result";
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {

                            }
                        });
                    } else {
                        Dialog_subscribe_to_premuim dialog_subscribe_to_premuim = new Dialog_subscribe_to_premuim("month",true);
                        dialog_subscribe_to_premuim.setCancelable(false);
                        dialog_subscribe_to_premuim.show(getSupportFragmentManager(), "");
                        billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
                    }
                }
            } else if (purchase.getSku().equals("1_year_subscription")) {
                if (!purchase.isAcknowledged()) {
                    set_buy_premuim_true();
                    AcknowledgePurchaseParams acknowledgePurchaseParams =
                            AcknowledgePurchaseParams.newBuilder()
                                    .setPurchaseToken(purchase.getPurchaseToken())
                                    .build();
                    if(am_i_sign_in_with_google()){
                        Map<String, Object> data = new HashMap<>();
                        data.put("token", purchase.getPurchaseToken());
                        data.put("orderId", purchase.getOrderId());
                        Dialog_subscribe_to_premuim dialog_subscribe_to_premuim = new Dialog_subscribe_to_premuim("year",false);
                        dialog_subscribe_to_premuim.setCancelable(false);
                        dialog_subscribe_to_premuim.show(getSupportFragmentManager(), "");
                        firebaseFunctions = FirebaseFunctions.getInstance();
                        firebaseFunctions
                                .getHttpsCallable("yearly_sub_i_buy")
                                .call(data)
                                .continueWith(new Continuation<HttpsCallableResult, String>() {
                                    @Override
                                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                        if (String.valueOf(task.getResult().getData()).equals("success")) {
                                            dialog_subscribe_to_premuim.loaded();
                                            billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
                                        } else if (String.valueOf(task.getResult().getData()).equals("error")) {
                                            Toast.makeText(after_login.this, "Verifying purchase with server failed. Please try restarting the app with internet connection. If that doesn't work contact Support", Toast.LENGTH_LONG).show();
                                        }
                                        return "result";
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {

                            }
                        });
                    } else {
                        Dialog_subscribe_to_premuim dialog_subscribe_to_premuim = new Dialog_subscribe_to_premuim("year",true);
                        dialog_subscribe_to_premuim.setCancelable(false);
                        dialog_subscribe_to_premuim.show(getSupportFragmentManager(), "");
                        billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
                    }
                }
            }
        } else if (purchase.getPurchaseState() == Purchase.PurchaseState.PENDING) {
            //Toast.makeText(getActivity(), "purchase is being processed", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(getActivity(), "purchase is being processed", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean am_i_sign_in_with_google(){
        if (FirebaseAuth.getInstance().getCurrentUser() != null && FirebaseAuth.getInstance().getCurrentUser().getIdToken(false).getResult().getSignInProvider().equals("google.com")) {
            return true;
        } else {
            return false;
        }
    }

    private void set_buy_premuim_true() {
            SharedPreferences sharedPreferences = getSharedPreferences("is_user_gifted", Context.MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putBoolean("premuim_online", true);
            myEdit.commit();
        }
}
