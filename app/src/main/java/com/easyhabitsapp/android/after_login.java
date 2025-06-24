package com.easyhabitsapp.android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.functions.FirebaseFunctions;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class after_login extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private com.easyhabitsapp.android.home_fragment home_fragment;
    private Sharing_fragment sharing_fragment;
    private Posts_fragment posts_fragment;
    //private com.easyhabitsapp.android.chat_fragment chat_fragment;
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
        sharing_fragment = new Sharing_fragment();
        posts_fragment = new Posts_fragment();
        //chat_fragment = new chat_fragment();
        habits_fragment = new habits_fragment();
        settings_fragment = new Settings_fragment();
        emergency_button_fragment = new emergency_button_fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, home_fragment, "home").commitNow();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, sharing_fragment, "sharing habits").hide(sharing_fragment).commitNow();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, posts_fragment, "posts").hide(posts_fragment).commitNow();
        //getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, chat_fragment, "chat").hide(chat_fragment).commitNow();
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
        //check_if_user_is_gifted(false);
//        set_up_billing();
        Payment_processer.getInstance().init(this, after_login.this, getSupportFragmentManager());
        create_user_name();
        disable_crashalytics_for_debug();
        listen_to_payment();
        listen_to_firebase();
        add_all_view_homes();
        add_mood_fragment();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            CharSequence title = item.getTitle();
            if (title.equals("Home")) {
                continue_switching_or_no(1);
                Event_manager_all_in_one.getInstance().record_fire_base_event(after_login.this, Event_manager_all_in_one.Event_type_fire_base_record.home_clicked, false);
            } else if (title.equals("Sharing")) {
                continue_switching_or_no(2);
                Event_manager_all_in_one.getInstance().record_fire_base_event(after_login.this, Event_manager_all_in_one.Event_type_fire_base_record.sharing_clicked, false);
            } else if (title.equals("Posts")) {
                continue_switching_or_no(3);
                Event_manager_all_in_one.getInstance().record_fire_base_event(after_login.this, Event_manager_all_in_one.Event_type_fire_base_record.post_clicked, false);
            } else if (title.equals("Tools")) {
                continue_switching_or_no(4);
                Event_manager_all_in_one.getInstance().record_fire_base_event(after_login.this, Event_manager_all_in_one.Event_type_fire_base_record.tools_clicked, false);
            } else if (title.equals("Settings")) {
                continue_switching_or_no(5);
                Event_manager_all_in_one.getInstance().record_fire_base_event(after_login.this, Event_manager_all_in_one.Event_type_fire_base_record.settings_clicked, false);
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
        Add_new_habit_2 add_a_habit_2 = (Add_new_habit_2) getSupportFragmentManager().findFragmentByTag("add a habit");
        Mood_tracker mood_tracker = (Mood_tracker) getSupportFragmentManager().findFragmentByTag("mood tracker");
        post_a_post post_a_post = (com.easyhabitsapp.android.post_a_post) getSupportFragmentManager().findFragmentByTag("write a post");
        Show_full_post show_full_post = (Show_full_post) getSupportFragmentManager().findFragmentByTag("show full post");
        Write_comment_for_post write_comment_for_post = (Write_comment_for_post) getSupportFragmentManager().findFragmentByTag("write a comment");
        Give_coins give_coins = (Give_coins) getSupportFragmentManager().findFragmentByTag("give_coins");
        edit_bad_habits edit_bad_habits = (edit_bad_habits) getSupportFragmentManager().findFragmentByTag("edit bad habit");
        Buy_premuim buy_premuim = (Buy_premuim) getSupportFragmentManager().findFragmentByTag("buy premium");
        if (home_fragment != null && home_fragment.isVisible()) {
            close_app();
        } else if(sharing_fragment !=null && sharing_fragment.isVisible()){
            close_app();
        } else if (posts_fragment != null && posts_fragment.isVisible()) {
            close_app();
        }/* else if (chat_fragment != null && chat_fragment.isVisible()) {
            close_app();
        }*/ else if (habits_fragment != null && habits_fragment.isVisible()) {
            /*if (habits_fragment.return_the_screen() == 3) {
                habits_fragment.open_a_screen(2);
                habits_fragment.restart_third_screen_good_information();
            } else if (habits_fragment.return_the_screen() == 2 || habits_fragment.return_the_screen() == 0) {
                close_app();
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
            close_app();
        } else if (emergency_button_fragment != null && emergency_button_fragment.isVisible() && home_fragment != null) {
            getSupportFragmentManager().beginTransaction().show(home_fragment).hide(emergency_button_fragment).commitNow();
            emergency_button_fragment.scroll_to_top();
        } else if (check_if_view_him_is_visible() && home_fragment != null) {
            hide_view_home();
            set_default_status_color();
            color_the_notification_bar_icons_dark();
            getSupportFragmentManager().beginTransaction().show(home_fragment).commitNow();
        } else if (add_a_habit_2 != null && add_a_habit_2.isVisible() && home_fragment != null) {
            getSupportFragmentManager().beginTransaction().show(home_fragment).remove(add_a_habit_2).commitNow();
        } else if (mood_tracker != null && mood_tracker.isVisible() && habits_fragment != null) {
            hide_mood_tracker();
            //remove_and_add_habits();
            set_default_status_color();
            color_the_notification_bar_icons_dark();
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
        } else if (buy_premuim != null && buy_premuim.isVisible()) {
            buy_premuim.back_was_pressed();
        }
    }

   /* private void exit_app_dialog() {
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
    }*/

    private void check_extra() {
        Intent intent = getIntent();
        boolean data = intent.getBooleanExtra("Start_the_emergency_true", false);
        int id = intent.getIntExtra("open_habit", -1);
        if (data) {
            getSupportFragmentManager().beginTransaction().hide(home_fragment).show(habits_fragment).commitNow();
        } else if (id != -1) {
            home_fragment.open_a_habit(id);
        }
    }

    private void remove_add_object() {
        Add_new_habit_2 add_a_habit_2 = (Add_new_habit_2) getSupportFragmentManager().findFragmentByTag("add a habit");
        if (add_a_habit_2 != null && add_a_habit_2.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(add_a_habit_2).commitNow();
        }
    }

    private void hide_view_home() {
        Room_database_habits database_habits = Room_database_habits.getInstance(this);
        List<habits_data_class> list = database_habits.dao_for_habits_data().getAll();
        for (int i = 0; i < list.size(); i++) {
            View_home_habit view_home_habit = (View_home_habit) getSupportFragmentManager().findFragmentByTag("view home: ".concat(String.valueOf(list.get(i).getId())));
            if (view_home_habit != null && view_home_habit.isVisible()) {
                getSupportFragmentManager().beginTransaction().hide(view_home_habit).commitNow();

            }
        }
    }

    private void hide_mood_tracker() {
        Mood_tracker mood_tracker = (Mood_tracker) getSupportFragmentManager().findFragmentByTag("mood tracker");
        if (mood_tracker != null && mood_tracker.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(mood_tracker).commitNow();
//            remove_and_add_habits();
        }
    }

    private void remove_emergency() {
        com.easyhabitsapp.android.emergency_button_fragment myFragment = (com.easyhabitsapp.android.emergency_button_fragment) getSupportFragmentManager().findFragmentByTag("emergency");
        if (myFragment != null && myFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(myFragment).commitNow();
        }
    }

    /*private void remove_and_add_habits() {
        habits_fragment = new habits_fragment();
        habits_fragment old_fragment = (habits_fragment) getSupportFragmentManager().findFragmentByTag("habits");
        if (old_fragment != null) {
            getSupportFragmentManager().beginTransaction().remove(old_fragment).commitNow();
        }
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, habits_fragment, "habits").hide(habits_fragment).commitNow();
    }*/

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
        Sharing_fragment sharing_fragment = (Sharing_fragment) getSupportFragmentManager().findFragmentByTag("sharing habits") ;
        getSupportFragmentManager().beginTransaction().hide(home_fragment).commitNow();
        getSupportFragmentManager().beginTransaction().hide(sharing_fragment).commitNow();
        getSupportFragmentManager().beginTransaction().hide(posts_fragment).commitNow();
//        getSupportFragmentManager().beginTransaction().hide(chat_fragment).commitNow();
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
                                home_was_pressed();
                            } else if (which_was_pressed == 2) {
                                sharing_was_pressed();
                            } else if (which_was_pressed == 3) {
                                post_was_pressed();
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
                                home_was_pressed();
                            } else if (which_was_pressed == 2) {
                                sharing_was_pressed();
                            } else if (which_was_pressed == 3) {
                                post_was_pressed();
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
                    home_was_pressed();
                } else if (which_was_pressed == 2) {
                    sharing_was_pressed();
                } else if (which_was_pressed == 3) {
                    post_was_pressed();
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
        hide_view_home();
        remove_add_object();
        hide_mood_tracker();
        define_the_habits();
        remove_the_post_a_post_fragment();
        update_post_remote();
        remove_the_full_post();
        remove_the_write_a_comment();
        remove_give_coisn();
        remove_edit_bad_habit();
        remove_buy_premuim();
        hide_all();
        set_default_status_color();
        color_the_notification_bar_icons_dark();
        getSupportFragmentManager().beginTransaction().show(home_fragment).commitNow();
        //habits_fragment.return_the_first_screen();
        emergency_button_fragment.scroll_to_top();
    }

    private void sharing_was_pressed(){
        remove_emergency();
        hide_view_home();
        remove_add_object();
        hide_mood_tracker();
        define_the_habits();
        remove_the_post_a_post_fragment();
        update_post_remote();
        remove_the_full_post();
        remove_the_write_a_comment();
        remove_give_coisn();
        remove_edit_bad_habit();
        remove_buy_premuim();
        hide_all();
        set_default_status_color();
        color_the_notification_bar_icons_dark();
        getSupportFragmentManager().beginTransaction().show(sharing_fragment).commitNow();
        //habits_fragment.return_the_first_screen();
        emergency_button_fragment.scroll_to_top();
    }

    private void post_was_pressed() {
        remove_emergency();
        hide_view_home();
        remove_add_object();
        hide_mood_tracker();
        define_the_habits();
        remove_the_post_a_post_fragment();
        update_post_remote();
        remove_the_full_post();
        remove_the_write_a_comment();
        remove_give_coisn();
        remove_edit_bad_habit();
        remove_buy_premuim();
        hide_all();
        set_default_status_color();
        color_the_notification_bar_icons_dark();
        getSupportFragmentManager().beginTransaction().show(posts_fragment).commitNow();
        //habits_fragment.return_the_first_screen();
        emergency_button_fragment.scroll_to_top();
    }

    /*private void chat_was_pressed() {
        remove_emergency();
        hide_view_home();
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
        //getSupportFragmentManager().beginTransaction().show(chat_fragment).commitNow();
        //habits_fragment.return_the_first_screen();
        emergency_button_fragment.scroll_to_top();
    }*/

    private void habits_was_pressed() {
        remove_emergency();
        hide_view_home();
        remove_add_object();
        hide_mood_tracker();
        define_the_habits();
        remove_the_post_a_post_fragment();
        update_post_remote();
        remove_the_full_post();
        remove_the_write_a_comment();
        remove_give_coisn();
        remove_edit_bad_habit();
        remove_buy_premuim();
        hide_all();
        set_default_status_color();
        color_the_notification_bar_icons_dark();
        getSupportFragmentManager().beginTransaction().show(habits_fragment).commitNow();
        //habits_fragment.return_the_first_screen();
        emergency_button_fragment.scroll_to_top();
    }

    private void settings_was_pressed() {
        remove_emergency();
        hide_view_home();
        remove_add_object();
        hide_mood_tracker();
        define_the_habits();
        remove_the_post_a_post_fragment();
        update_post_remote();
        remove_the_full_post();
        remove_the_write_a_comment();
        remove_give_coisn();
        remove_edit_bad_habit();
        remove_buy_premuim();
        hide_all();
        set_default_status_color();
        color_the_notification_bar_icons_dark();
        getSupportFragmentManager().beginTransaction().show(settings_fragment).commitNow();
        //habits_fragment.return_the_first_screen();
        emergency_button_fragment.scroll_to_top();
    }

    /*private void remove_show_the_post() {
        Show_image_post myFragment = (Show_image_post) getSupportFragmentManager().findFragmentByTag("show post image");
        if (myFragment != null && myFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(myFragment).commitNow();
        }
    }*/

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

    private void create_user_name() {
        SharedPreferences sharedPreferences = getSharedPreferences("name_online_for_post", MODE_PRIVATE);
        if (sharedPreferences.getString("name", "").equals("")) {
            String name = "User".concat(String.format("%04d", new Random().nextInt(10000)));
            Save_and_get.getInstance().save_this(this, name, "name_online_for_post", "name");
        }
    }

    private void disable_crashalytics_for_debug() {
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG);
    }

    private void close_app() {
        moveTaskToBack(true);
    }

    private void add_all_view_homes() {
        Room_database_habits database_habits = Room_database_habits.getInstance(this);
        List<habits_data_class> list = database_habits.dao_for_habits_data().getAll();
        for (int i = 0; i < list.size(); i++) {
            View_home_habit view_home_habit = new View_home_habit();
            Bundle args = new Bundle();
            args.putInt("which_id", list.get(i).getId());
            view_home_habit.setArguments(args);
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, view_home_habit, "view home: ".concat(String.valueOf(list.get(i).getId()))).hide(view_home_habit).commitNow();
        }
    }

    private boolean check_if_view_him_is_visible() {
        Room_database_habits database_habits = Room_database_habits.getInstance(this);
        List<habits_data_class> list = database_habits.dao_for_habits_data().getAll();
        for (int i = 0; i < list.size(); i++) {
            View_home_habit view_home_habit = (View_home_habit) getSupportFragmentManager().findFragmentByTag("view home: ".concat(String.valueOf(list.get(i).getId())));
            if (view_home_habit != null && view_home_habit.isVisible()) {
                return true;
            }
        }
        return false;
    }

    private void set_default_status_color() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#f1f3f9"));
    }

    private void listen_to_payment() {
        if (Payment_processer.getInstance().is_sub_purchase_list_ready) {
            if (!Payment_processer.getInstance().sub_purchase_list.isEmpty()) {
                make_views_pro();
            }
        } else {
            Payment_processer.getInstance().set_up_purchase_list_ready_3(new Payment_processer.sub_purchase_ready_3() {
                @Override
                public void sub_purchase_is_ready() {
                    if (!Payment_processer.getInstance().sub_purchase_list.isEmpty()) {
                        make_views_pro();
                    }
                }
            });
        }
    }

    private void listen_to_firebase() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            firebaseFirestore.collection("gifts").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            long time = document.getLong("time");
                            if (time > System.currentTimeMillis() - 86400000L) {
                                make_views_pro();
                            }
                        }
                    } else {
                        //fail
                    }
                }
            });
        }
    }

    private void make_views_pro() {
        Room_database_habits database_habits = Room_database_habits.getInstance(this);
        List<habits_data_class> list = database_habits.dao_for_habits_data().getAll();
        for (int i = 0; i < list.size(); i++) {
            View_home_habit view_home_habit = (View_home_habit) getSupportFragmentManager().findFragmentByTag("view home: ".concat(String.valueOf(list.get(i).getId())));
            if (view_home_habit != null) {
                view_home_habit.make_the_views_pro();
            }
        }
        Mood_tracker mood_tracker = (Mood_tracker) getSupportFragmentManager().findFragmentByTag("mood tracker");
        if (mood_tracker != null) {
            mood_tracker.make_views_pro();
        }
    }

    private void color_the_notification_bar_icons_dark() {
        View decor = getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void add_mood_fragment() {
        Mood_tracker mood_tracker = new Mood_tracker();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mood_tracker, "mood tracker").hide(mood_tracker).commitNow();
    }

}
