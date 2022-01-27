package com.easyhabitsapp.android;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Dialog_to_choose_good_habit extends DialogFragment {
    private the_icon_clicked the_icon_clicked_listen;
    public void set_icon_listen(the_icon_clicked listen) {
        the_icon_clicked_listen = listen;
    }

    public interface the_icon_clicked {
        void share_just_got_clciked(int text);
    }


    private View mview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_choose_icon_good_habits, container);
        this.mview = rootView;
        cancel_button_listen();
        button_habits_listen();
        set_up_recycle_view();
        set_bad_habits();
        set_up_good_habits();
        return rootView;
    }

    private void cancel_button_listen() {
        Button button_saying_cancel_in_corner_of_dialog = mview.findViewById(R.id.button_saying_cancel_in_corner_of_dialog);
        button_saying_cancel_in_corner_of_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getDialog() != null) {
                    getDialog().dismiss();
                } else {
                    dismiss();
                }
            }
        });
    }


    @Override
    public void onResume() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getActivity() != null && getDialog() != null && getDialog().getWindow() != null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = (displayMetrics.heightPixels / 100) * 90;
            int real_width = (displayMetrics.widthPixels / 100) * 95;
            getDialog().getWindow().setLayout(real_width, height);
            //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        super.onResume();
    }

    private void set_up_recycle_view() {
        if (mview != null) {
            RecyclerView recycle_view_to_show_icons_in_add_a_habit_dialog = mview.findViewById(R.id.recycle_view_to_show_icons_in_add_a_habit_dialog);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            ArrayList<Example_item_for_icons> list_for_habits = new ArrayList<>();
            list_for_habits.add(new Example_item_for_icons("Video games",R.drawable.round_videogame_asset_24,"Phone Block",R.drawable.round_app_blocking_24,"Shopping",R.drawable.round_shopping_cart_24));
            list_for_habits.add(new Example_item_for_icons("Smoking",R.drawable.round_smoking_rooms_24,"Drink",R.drawable.round_sports_bar_24,"Time",R.drawable.round_schedule_24));
            list_for_habits.add(new Example_item_for_icons("Cancel",R.drawable.round_cancel_24,"Fast Food",R.drawable.round_fastfood_24,"Money",R.drawable.round_money_24));
            list_for_habits.add(new Example_item_for_icons("Computer",R.drawable.round_computer_24,"Couch",R.drawable.round_weekend_24,"Work",R.drawable.round_work_24));
            list_for_habits.add(new Example_item_for_icons("Brush",R.drawable.round_brush_24,"Finance",R.drawable.round_account_balance_24,"Running",R.drawable.round_directions_run_24));
            list_for_habits.add(new Example_item_for_icons("Gym",R.drawable.round_fitness_center_24,"Education",R.drawable.round_school_24,"Language",R.drawable.round_translate_24));
            list_for_habits.add(new Example_item_for_icons("Sleep",R.drawable.round_airline_seat_individual_suite_24,"Reading",R.drawable.round_menu_book_24,"Language",R.drawable.round_receipt_long_24));
            list_for_habits.add(new Example_item_for_icons("Check",R.drawable.round_check_circle_24,"Desktop",R.drawable.round_desktop_windows_24,"Heart",R.drawable.round_favorite_24));
            list_for_habits.add(new Example_item_for_icons("Seat",R.drawable.round_event_seat_24,"Pet",R.drawable.round_pets_24,"Work",R.drawable.round_work_24));
            list_for_habits.add(new Example_item_for_icons("Close",R.drawable.round_close_24,"Payment",R.drawable.round_payments_24,"Mail",R.drawable.round_mail_24));
            list_for_habits.add(new Example_item_for_icons("Microscope",R.drawable.round_biotech_24,"Couch",R.drawable.round_weekend_24,"People",R.drawable.round_groups_24));
            list_for_habits.add(new Example_item_for_icons("Education",R.drawable.round_school_24,"Trophy",R.drawable.round_emoji_events_24,"Engineering",R.drawable.round_engineering_24));
            list_for_habits.add(new Example_item_for_icons("Health",R.drawable.round_health_and_safety_24,"Construction",R.drawable.round_handyman_24,"Psychology",R.drawable.round_psychology_24));
            list_for_habits.add(new Example_item_for_icons("Airplane",R.drawable.round_airplane_ticket_24,"Gaming",R.drawable.round_games_24,"Light Bulb",R.drawable.round_emoji_objects_24));
            list_for_habits.add(new Example_item_for_icons("Water",R.drawable.round_water_drop_24,"Science",R.drawable.round_science_24,"Self improvement",R.drawable.round_self_improvement_24));
            list_for_habits.add(new Example_item_for_icons("Soccer",R.drawable.round_sports_soccer_24,"Business",R.drawable.round_business_24,"Architecture",R.drawable.round_architecture_24));
            list_for_habits.add(new Example_item_for_icons("Hiking",R.drawable.round_hiking_24,"Recycling",R.drawable.round_recycling_24,"Nature",R.drawable.round_emoji_nature_24));
            list_for_habits.add(new Example_item_for_icons("Basket ball",R.drawable.round_sports_basketball_24,"Bed",R.drawable.round_king_bed_24,"Sport",R.drawable.round_sports_24));
            list_for_habits.add(new Example_item_for_icons("Sick",R.drawable.round_sick_24,"Elder",R.drawable.round_elderly_24,"Surfing",R.drawable.round_surfing_24));
            list_for_habits.add(new Example_item_for_icons("Driving",R.drawable.round_sports_motorsports_24,"Base ball",R.drawable.round_sports_baseball_24,"Interest",R.drawable.round_interests_24));
            list_for_habits.add(new Example_item_for_icons("Volley ball",R.drawable.round_sports_volleyball_24,"Rabbit",R.drawable.round_cruelty_free_24,"Kayaking",R.drawable.round_kayaking_24));
            list_for_habits.add(new Example_item_for_icons("foot ball",R.drawable.round_sports_football_24,"Skate board",R.drawable.round_skateboarding_24,"Cricket",R.drawable.round_sports_cricket_24));
            list_for_habits.add(new Example_item_for_icons("Book",R.drawable.round_auto_stories_24,"Sun",R.drawable.round_wb_sunny_24,"heal",R.drawable.round_healing_24));
            list_for_habits.add(new Example_item_for_icons("Camera",R.drawable.round_photo_camera_24,"Phone",R.drawable.round_call_24,"Chat",R.drawable.round_chat_24));
            list_for_habits.add(new Example_item_for_icons("Restaurant",R.drawable.round_restaurant_24,"Car",R.drawable.round_directions_car_24,"Park",R.drawable.round_park_24));
            list_for_habits.add(new Example_item_for_icons("Fast food",R.drawable.round_fastfood_24,"Bike",R.drawable.round_directions_bike_24,"Burger",R.drawable.round_lunch_dining_24));
            list_for_habits.add(new Example_item_for_icons("Plant",R.drawable.round_local_florist_24,"Gas",R.drawable.round_local_gas_station_24,"Boat",R.drawable.round_sailing_24));
            list_for_habits.add(new Example_item_for_icons("Pizza",R.drawable.round_local_pizza_24,"Religion",R.drawable.round_mosque_24,"Star",R.drawable.round_star_24));
            list_for_habits.add(new Example_item_for_icons("Fitness",R.drawable.round_fitness_center_24,"Rest",R.drawable.round_spa_24,"Grass",R.drawable.round_grass_24));
            list_for_habits.add(new Example_item_for_icons("Child",R.drawable.round_child_care_24,"Beach",R.drawable.round_beach_access_24,"Pool",R.drawable.round_pool_24));
            list_for_habits.add(new Example_item_for_icons("Kitchen",R.drawable.round_kitchen_24,"Gambling",R.drawable.round_casino_24,"Food",R.drawable.round_local_dining_24));
            list_for_habits.add(new Example_item_for_icons("Bathtub",R.drawable.round_bathtub_24,"Smoking",R.drawable.round_smoking_rooms_24,"Shower",R.drawable.round_shower_24));
            list_for_habits.add(new Example_item_for_icons("Yard",R.drawable.round_yard_24,"Coffee",R.drawable.round_coffee_24,"Other",R.drawable.happy_face_dark_green));
            Adapter_pick_icons adapter_pick_icons = new Adapter_pick_icons(list_for_habits);
            adapter_pick_icons.set_share_clicked_listen(new Adapter_pick_icons.button_in_icons_got_clicked() {
                @Override
                public void share_just_got_clciked(int text) {
                    the_icon_clicked_listen.share_just_got_clciked(text);
                    dismiss();
                }
            });
            recycle_view_to_show_icons_in_add_a_habit_dialog.setLayoutManager(linearLayoutManager);
            recycle_view_to_show_icons_in_add_a_habit_dialog.setAdapter(adapter_pick_icons);
        }
    }

    private void set_bad_habits(){
        if(mview!=null){
            RecyclerView recycle_view_to_show_icons_in_add_a_habit_dialog_bad_habits = mview.findViewById(R.id.recycle_view_to_show_icons_in_add_a_habit_dialog_bad_habits);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            ArrayList<Example_item_for_icons> list_for_habits = new ArrayList<>();
            list_for_habits.add(new Example_item_for_icons("Smoking",R.drawable.round_smoking_rooms_24,"Drink",R.drawable.round_sports_bar_24,"Time",R.drawable.round_schedule_24));
            list_for_habits.add(new Example_item_for_icons("Video games",R.drawable.round_videogame_asset_24,"Phone Block",R.drawable.round_app_blocking_24,"Shopping",R.drawable.round_shopping_cart_24));
            list_for_habits.add(new Example_item_for_icons("Cancel",R.drawable.round_cancel_24,"Fast Food",R.drawable.round_fastfood_24,"Money",R.drawable.round_money_24));
            list_for_habits.add(new Example_item_for_icons("Computer",R.drawable.round_computer_24,"Couch",R.drawable.round_weekend_24,"Work",R.drawable.round_work_24));
            list_for_habits.add(new Example_item_for_icons("Over sleeping",R.drawable.round_airline_seat_individual_suite_24,"Desktop",R.drawable.round_desktop_windows_24,"Heart",R.drawable.round_favorite_24));
            list_for_habits.add(new Example_item_for_icons("Over Working",R.drawable.round_work_24,"Close",R.drawable.round_close_24,"Couch",R.drawable.round_weekend_24));
            list_for_habits.add(new Example_item_for_icons("heal",R.drawable.round_healing_24,"Gaming",R.drawable.round_games_24,"Sick",R.drawable.round_sick_24));
            list_for_habits.add(new Example_item_for_icons("Pizza",R.drawable.round_local_pizza_24,"Phone",R.drawable.round_call_24,"Chat",R.drawable.round_chat_24));
            list_for_habits.add(new Example_item_for_icons("Kitchen",R.drawable.round_kitchen_24,"Gambling",R.drawable.round_casino_24,"Food",R.drawable.round_local_dining_24));
            list_for_habits.add(new Example_item_for_icons("Coffee",R.drawable.round_coffee_24,"Smoking",R.drawable.round_smoking_rooms_24,"Other",R.drawable.happy_face_dark_green));
            Adapter_pick_icons adapter_pick_icons = new Adapter_pick_icons(list_for_habits);
            adapter_pick_icons.set_share_clicked_listen(new Adapter_pick_icons.button_in_icons_got_clicked() {
                @Override
                public void share_just_got_clciked(int text) {
                    the_icon_clicked_listen.share_just_got_clciked(text);
                    dismiss();
                }
            });
            recycle_view_to_show_icons_in_add_a_habit_dialog_bad_habits.setLayoutManager(linearLayoutManager);
            recycle_view_to_show_icons_in_add_a_habit_dialog_bad_habits.setAdapter(adapter_pick_icons);
        }
    }

    private void set_up_good_habits(){
        if(mview!=null){
            RecyclerView recycle_view_to_show_icons_in_add_a_habit_dialog_good_habits = mview.findViewById(R.id.recycle_view_to_show_icons_in_add_a_habit_dialog_good_habits);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            ArrayList<Example_item_for_icons> list_for_habits = new ArrayList<>();
            list_for_habits.add(new Example_item_for_icons("Brush",R.drawable.round_brush_24,"Finance",R.drawable.round_account_balance_24,"Running",R.drawable.round_directions_run_24));
            list_for_habits.add(new Example_item_for_icons("Gym",R.drawable.round_fitness_center_24,"Education",R.drawable.round_school_24,"Language",R.drawable.round_translate_24));
            list_for_habits.add(new Example_item_for_icons("Sleep",R.drawable.round_airline_seat_individual_suite_24,"Reading",R.drawable.round_menu_book_24,"Language",R.drawable.round_receipt_long_24));
            list_for_habits.add(new Example_item_for_icons("Check",R.drawable.round_check_circle_24,"Desktop",R.drawable.round_desktop_windows_24,"Heart",R.drawable.round_favorite_24));
            list_for_habits.add(new Example_item_for_icons("Seat",R.drawable.round_event_seat_24,"Pet",R.drawable.round_pets_24,"Work",R.drawable.round_work_24));
            list_for_habits.add(new Example_item_for_icons("Surfing",R.drawable.round_surfing_24,"Payment",R.drawable.round_payments_24,"Mail",R.drawable.round_mail_24));
            list_for_habits.add(new Example_item_for_icons("Microscope",R.drawable.round_biotech_24,"Couch",R.drawable.round_weekend_24,"People",R.drawable.round_groups_24));
            list_for_habits.add(new Example_item_for_icons("Education",R.drawable.round_school_24,"Trophy",R.drawable.round_emoji_events_24,"Engineering",R.drawable.round_engineering_24));
            list_for_habits.add(new Example_item_for_icons("Health",R.drawable.round_health_and_safety_24,"Construction",R.drawable.round_handyman_24,"Psychology",R.drawable.round_psychology_24));
            list_for_habits.add(new Example_item_for_icons("Elder",R.drawable.round_elderly_24,"Airplane",R.drawable.round_airplane_ticket_24,"Light Bulb",R.drawable.round_emoji_objects_24));
            list_for_habits.add(new Example_item_for_icons("Water",R.drawable.round_water_drop_24,"Science",R.drawable.round_science_24,"Self improvement",R.drawable.round_self_improvement_24));
            list_for_habits.add(new Example_item_for_icons("Soccer",R.drawable.round_sports_soccer_24,"Business",R.drawable.round_business_24,"Architecture",R.drawable.round_architecture_24));
            list_for_habits.add(new Example_item_for_icons("Hiking",R.drawable.round_hiking_24,"Recycling",R.drawable.round_recycling_24,"Nature",R.drawable.round_emoji_nature_24));
            list_for_habits.add(new Example_item_for_icons("Basket ball",R.drawable.round_sports_basketball_24,"Bed",R.drawable.round_king_bed_24,"Sport",R.drawable.round_sports_24));
            list_for_habits.add(new Example_item_for_icons("Driving",R.drawable.round_sports_motorsports_24,"Base ball",R.drawable.round_sports_baseball_24,"Interest",R.drawable.round_interests_24));
            list_for_habits.add(new Example_item_for_icons("Volley ball",R.drawable.round_sports_volleyball_24,"Rabbit",R.drawable.round_cruelty_free_24,"Kayaking",R.drawable.round_kayaking_24));
            list_for_habits.add(new Example_item_for_icons("foot ball",R.drawable.round_sports_football_24,"Skate board",R.drawable.round_skateboarding_24,"Cricket",R.drawable.round_sports_cricket_24));
            list_for_habits.add(new Example_item_for_icons("Book",R.drawable.round_auto_stories_24,"Sun",R.drawable.round_wb_sunny_24,"heal",R.drawable.round_healing_24));
            list_for_habits.add(new Example_item_for_icons("Camera",R.drawable.round_photo_camera_24,"Phone",R.drawable.round_call_24,"Chat",R.drawable.round_chat_24));
            list_for_habits.add(new Example_item_for_icons("Restaurant",R.drawable.round_restaurant_24,"Car",R.drawable.round_directions_car_24,"Park",R.drawable.round_park_24));
            list_for_habits.add(new Example_item_for_icons("Fast food",R.drawable.round_fastfood_24,"Bike",R.drawable.round_directions_bike_24,"Burger",R.drawable.round_lunch_dining_24));
            list_for_habits.add(new Example_item_for_icons("Plant",R.drawable.round_local_florist_24,"Gas",R.drawable.round_local_gas_station_24,"Boat",R.drawable.round_sailing_24));
            list_for_habits.add(new Example_item_for_icons("Pizza",R.drawable.round_local_pizza_24,"Religion",R.drawable.round_mosque_24,"Star",R.drawable.round_star_24));
            list_for_habits.add(new Example_item_for_icons("Fitness",R.drawable.round_fitness_center_24,"Rest",R.drawable.round_spa_24,"Grass",R.drawable.round_grass_24));
            list_for_habits.add(new Example_item_for_icons("Child",R.drawable.round_child_care_24,"Beach",R.drawable.round_beach_access_24,"Pool",R.drawable.round_pool_24));
            list_for_habits.add(new Example_item_for_icons("Kitchen",R.drawable.round_kitchen_24,"Gambling",R.drawable.round_casino_24,"Food",R.drawable.round_local_dining_24));
            list_for_habits.add(new Example_item_for_icons("Bathtub",R.drawable.round_bathtub_24,"Smoking",R.drawable.round_smoking_rooms_24,"Shower",R.drawable.round_shower_24));
            list_for_habits.add(new Example_item_for_icons("Yard",R.drawable.round_yard_24,"Coffee",R.drawable.round_coffee_24,"Other",R.drawable.happy_face_dark_green));
            Adapter_pick_icons adapter_pick_icons = new Adapter_pick_icons(list_for_habits);
            adapter_pick_icons.set_share_clicked_listen(new Adapter_pick_icons.button_in_icons_got_clicked() {
                @Override
                public void share_just_got_clciked(int text) {
                    the_icon_clicked_listen.share_just_got_clciked(text);
                    dismiss();
                }
            });
            recycle_view_to_show_icons_in_add_a_habit_dialog_good_habits.setLayoutManager(linearLayoutManager);
            recycle_view_to_show_icons_in_add_a_habit_dialog_good_habits.setAdapter(adapter_pick_icons);
        }
    }

    private void button_habits_listen(){
        if(mview!=null){
            Button button_to_go_to_all_posts = mview.findViewById(R.id.button_to_go_to_all_posts);
            Button button_to_go_to_your_posts = mview.findViewById(R.id.button_to_go_to_your_posts);
            Button button_to_go_to_saved_posts = mview.findViewById(R.id.button_to_go_to_saved_posts);
            View white_view_under_first_button_feed = mview.findViewById(R.id.white_view_under_first_button_feed);
            View white_view_under_second_button_your_posts = mview.findViewById(R.id.white_view_under_second_button_your_posts);
            View white_view_under_third_button_saved_posts = mview.findViewById(R.id.white_view_under_third_button_saved_posts);
            RecyclerView recycle_view_to_show_icons_in_add_a_habit_dialog = mview.findViewById(R.id.recycle_view_to_show_icons_in_add_a_habit_dialog);
            RecyclerView recycle_view_to_show_icons_in_add_a_habit_dialog_bad_habits = mview.findViewById(R.id.recycle_view_to_show_icons_in_add_a_habit_dialog_bad_habits);
            RecyclerView recycle_view_to_show_icons_in_add_a_habit_dialog_good_habits = mview.findViewById(R.id.recycle_view_to_show_icons_in_add_a_habit_dialog_good_habits);
            button_to_go_to_all_posts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    white_view_under_first_button_feed.setVisibility(View.VISIBLE);
                    white_view_under_second_button_your_posts.setVisibility(View.INVISIBLE);
                    white_view_under_third_button_saved_posts.setVisibility(View.INVISIBLE);

                    recycle_view_to_show_icons_in_add_a_habit_dialog.setVisibility(View.VISIBLE);
                    recycle_view_to_show_icons_in_add_a_habit_dialog_bad_habits.setVisibility(View.INVISIBLE);
                    recycle_view_to_show_icons_in_add_a_habit_dialog_good_habits.setVisibility(View.INVISIBLE);
                }
            });
            button_to_go_to_your_posts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    white_view_under_first_button_feed.setVisibility(View.INVISIBLE);
                    white_view_under_second_button_your_posts.setVisibility(View.VISIBLE);
                    white_view_under_third_button_saved_posts.setVisibility(View.INVISIBLE);

                    recycle_view_to_show_icons_in_add_a_habit_dialog.setVisibility(View.INVISIBLE);
                    recycle_view_to_show_icons_in_add_a_habit_dialog_bad_habits.setVisibility(View.VISIBLE);
                    recycle_view_to_show_icons_in_add_a_habit_dialog_good_habits.setVisibility(View.INVISIBLE);
                }
            });
            button_to_go_to_saved_posts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    white_view_under_first_button_feed.setVisibility(View.INVISIBLE);
                    white_view_under_second_button_your_posts.setVisibility(View.INVISIBLE);
                    white_view_under_third_button_saved_posts.setVisibility(View.VISIBLE);

                    recycle_view_to_show_icons_in_add_a_habit_dialog.setVisibility(View.INVISIBLE);
                    recycle_view_to_show_icons_in_add_a_habit_dialog_bad_habits.setVisibility(View.INVISIBLE);
                    recycle_view_to_show_icons_in_add_a_habit_dialog_good_habits.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}
