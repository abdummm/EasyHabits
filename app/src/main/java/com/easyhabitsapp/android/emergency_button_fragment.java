package com.easyhabitsapp.android;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class emergency_button_fragment extends Fragment {

    private ViewPager viewPager;
    private ArrayList<Model_for_emergency_options> model_for_emergency_optionsArrayList;
    private Adapter_for_emergency_options adapter_for_emergency_options;


    public emergency_button_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_for_emergency_button_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        coloring_the_text();
        all_the_cards_listen();
        put_the_views_into_cards();
    }

    private void coloring_the_text() {
        if (getActivity() != null) {
            TextView text_main_to_the_user_in_the_front = getActivity().findViewById(R.id.text_main_to_the_user_in_the_front);
            SpannableString spannableString = new SpannableString(text_main_to_the_user_in_the_front.getText().toString());
            int color_for_streak = Color.rgb(96, 125, 139);
            spannableString.setSpan(new ForegroundColorSpan(color_for_streak), 7, 15, 0);
            spannableString.setSpan(new ForegroundColorSpan(color_for_streak), 30, 37, 0);
            spannableString.setSpan(new ForegroundColorSpan(color_for_streak), 58, 69, 0);
            spannableString.setSpan(new ForegroundColorSpan(color_for_streak), 106, 116, 0);
            spannableString.setSpan(new ForegroundColorSpan(color_for_streak), 136, 146, 0);
            spannableString.setSpan(new ForegroundColorSpan(color_for_streak), 149, spannableString.length() - 2, 0);
            text_main_to_the_user_in_the_front.setText(spannableString);
        }
    }

    private Drawable color_me(int id) {
        Drawable icon = ContextCompat.getDrawable(getContext(), id).mutate();
        icon = DrawableCompat.wrap(icon);
        DrawableCompat.setTint(icon, Color.WHITE);
        return icon;
    }

    private void all_the_cards_listen() {
        if (getView() != null) {
            CardView card_to_click_for_emergency_for_journal = getView().findViewById(R.id.card_to_click_for_emergency_for_journal);
            CardView card_to_click_for_emergency_for_lock_phone = getView().findViewById(R.id.card_to_click_for_emergency_for_lock_phone);
            CardView card_to_click_for_emergency_for_weight_tracker = getView().findViewById(R.id.card_to_click_for_emergency_for_weight_tracker);
            CardView card_to_click_for_emergency_for_quotes = getView().findViewById(R.id.card_to_click_for_emergency_for_quotes);
            CardView card_to_click_for_emergency_for_cold_shower = getView().findViewById(R.id.card_to_click_for_emergency_for_cold_shower);
            CardView card_to_click_for_emergency_for_pushups = getView().findViewById(R.id.card_to_click_for_emergency_for_pushups);
            CardView card_to_click_for_emergency_for_phone_usage = getView().findViewById(R.id.card_to_click_for_emergency_for_phone_usage);
            CardView card_to_click_for_emergency_for_counter = getView().findViewById(R.id.card_to_click_for_emergency_for_counter);
            card_to_click_for_emergency_for_journal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity("Journal");
                }
            });
            card_to_click_for_emergency_for_lock_phone.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    open_the_right_activity("Lock Phone");
                }
            });
            card_to_click_for_emergency_for_weight_tracker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity("Weight Tracker");
                }
            });
            /*card_to_click_for_emergency_for_user_stories.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity("User Stories");
                }
            });
            card_to_click_for_emergency_for_mediation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity("Mediation");
                }
            });*/
            card_to_click_for_emergency_for_quotes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity("Quotes");
                }
            });
            card_to_click_for_emergency_for_cold_shower.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity("Cold Shower");
                }
            });
            card_to_click_for_emergency_for_pushups.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity("Push ups");
                }
            });
            card_to_click_for_emergency_for_phone_usage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity("Phone Usage");
                }
            });
            card_to_click_for_emergency_for_counter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity("Counter");
                }
            });
            /*card_to_click_for_emergency_for_running_biking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity("Running/Biking");
                }
            });*/
        }
    }

    private void open_the_right_activity(String text) {
        if (getActivity() != null) {
            if (text.equals("Journal")) {
                Intent intent = new Intent(getActivity(), Journal_emergency.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            } else if (text.equals("Lock Phone")) {
                Intent intent = new Intent(getActivity(), Locking_the_screen.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            } else if (text.equals("Cold Shower")) {
                Intent intent = new Intent(getActivity(), Cold_shower_activity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            } else if (text.equals("Push ups")) {
                Intent intent = new Intent(getActivity(), Pushups_emergency.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            } else if (text.equals("Quotes")) {
                Intent intent = new Intent(getActivity(), Motivational_quotes.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            } else if (text.equals("Mediation")) {
               /*Intent intent = new Intent(getActivity(), Motivational_quotes.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);*/
                Toast.makeText(getActivity(), "Coming soon", Toast.LENGTH_SHORT).show();
            } else if (text.equals("Counter")) {
                Intent intent = new Intent(getActivity(), Counter_activity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            } else if (text.equals("Phone Usage")) {
                Intent intent = new Intent(getActivity(), phone_usage_emergency.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            } else if (text.equals("User Stories")) {
               /*Intent intent = new Intent(getActivity(), phone_usage_emergency.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);*/
                Toast.makeText(getActivity(), "Coming soon", Toast.LENGTH_SHORT).show();
            } /*else if (text.equals("Running/Biking")) {
                Intent intent = new Intent(getActivity(), Running_biking_emergency.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            } */else if (text.equals("Weight Tracker")) {
                Intent intent = new Intent(getActivity(), Weight_tracker_emergency.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            }
        }
    }

    public void scroll_to_top() {
        if (getView() != null) {
            ScrollView emergency_scroll_view = getView().findViewById(R.id.emergency_scroll_view);
            emergency_scroll_view.fullScroll(ScrollView.FOCUS_UP);
        }
    }

    private void put_the_views_into_cards() {
        if (getView() != null) {
            View icon_of_card_in_emergency_journal = getView().findViewById(R.id.icon_of_card_in_emergency_journal);
            View icon_of_card_in_emergency_lock_phone = getView().findViewById(R.id.icon_of_card_in_emergency_lock_phone);
            View icon_of_card_in_emergency_weight_tracker = getView().findViewById(R.id.icon_of_card_in_emergency_weight_tracker);
            View icon_of_card_in_emergency_cold_shower = getView().findViewById(R.id.icon_of_card_in_emergency_cold_shower);
            View icon_of_card_in_emergency_pushups = getView().findViewById(R.id.icon_of_card_in_emergency_pushups);
            View icon_of_card_in_emergency_quotes = getView().findViewById(R.id.icon_of_card_in_emergency_quotes);
            View icon_of_card_in_emergency_phone_usage = getView().findViewById(R.id.icon_of_card_in_emergency_phone_usage);
            View icon_of_card_in_emergency_coutner = getView().findViewById(R.id.icon_of_card_in_emergency_coutner);
            icon_of_card_in_emergency_journal.setBackground(color_me(R.drawable.round_edit_24));
            icon_of_card_in_emergency_lock_phone.setBackground(color_me(R.drawable.round_screen_lock_portrait_24));
            icon_of_card_in_emergency_weight_tracker.setBackground(color_me(R.drawable.round_local_dining_24));
            icon_of_card_in_emergency_cold_shower.setBackground(color_me(R.drawable.round_bathtub_24));
            icon_of_card_in_emergency_pushups.setBackground(color_me(R.drawable.round_fitness_center_24));
            icon_of_card_in_emergency_quotes.setBackground(color_me(R.drawable.round_format_quote_24));
            icon_of_card_in_emergency_phone_usage.setBackground(color_me(R.drawable.stats_colored_fav));
            icon_of_card_in_emergency_coutner.setBackground(color_me(R.drawable.round_add_circle_24));
        }
    }

    /*private void lock_phone_listen_fo_click() {
        if (getActivity() != null) {
            Button cold_shower_button_emergency = getActivity().findViewById(R.id.cold_shower_button_emergency);
            cold_shower_button_emergency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        Intent intent = new Intent(getActivity(), Cold_shower_activity.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(0, 0);
                    }
                }
            });
        }
    }*/


    /*private void updateNavigationBarState(int actionId) {
        if (getActivity() != null) {
            BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.nav_view);
            Menu menu = bottomNavigationView.getMenu();
            MenuItem item = menu.getItem(actionId);
            item.setChecked(true);
        }
    }*/

    /*private void lock_the_phone_button() {
        if (getActivity() != null) {
            Button lock_phone_button_emergency = getActivity().findViewById(R.id.lock_phone_button_emergency);
            lock_phone_button_emergency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        Intent intent = new Intent(getActivity(), Locking_the_screen.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(0, 0);
                    }
                }
            });
        }
    }*/
    /*private void read_the_shared_and_set_the_buttons() {
        if (getActivity() != null) {
            Button button_number_one_for_emergency = getActivity().findViewById(R.id.button_number_one_for_emergency);
            Button button_number_two_for_emergency = getActivity().findViewById(R.id.button_number_two_for_emergency);
            Button button_number_three_for_emergency = getActivity().findViewById(R.id.button_number_three_for_emergency);
            Button button_number_four_for_emergency = getActivity().findViewById(R.id.button_number_four_for_emergency);
            Button button_number_five_for_emergency = getActivity().findViewById(R.id.button_number_five_for_emergency);
            Button button_number_six_for_emergency = getActivity().findViewById(R.id.button_number_six_for_emergency);
            Button button_number_seven_for_emergency = getActivity().findViewById(R.id.button_number_seven_for_emergency);
            Button button_number_eight_for_emergency = getActivity().findViewById(R.id.button_number_eight_for_emergency);
            Button button_number_nine_for_emergency = getActivity().findViewById(R.id.button_number_nine_for_emergency);
            Button button_number_ten_for_emergency = getActivity().findViewById(R.id.button_number_ten_for_emergency);
            Button button_number_eleven_for_emergency = getActivity().findViewById(R.id.button_number_eleven_for_emergency);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("emergency_options", getContext().MODE_PRIVATE);
            String habits = sharedPreferences.getString("emergency", "");
            if (habits != null) {
                if (habits.contains("Journal")) {
                    button_number_one_for_emergency.setText("Journal");
                }
                if (habits.contains("Lock Phone")) {
                    if (button_number_one_for_emergency.getText().toString().equals("")) {
                        button_number_one_for_emergency.setText("Lock Phone");
                    } else {
                        button_number_two_for_emergency.setText("Lock Phone");
                    }
                }
                if (habits.contains("Cold Shower")) {
                    if (button_number_one_for_emergency.getText().toString().equals("")) {
                        button_number_one_for_emergency.setText("Cold Shower");
                    } else if (button_number_two_for_emergency.getText().toString().equals("")) {
                        button_number_two_for_emergency.setText("Cold Shower");
                    } else {
                        button_number_three_for_emergency.setText("Cold Shower");
                    }
                }
                if (habits.contains("Push ups")) {
                    if (button_number_one_for_emergency.getText().toString().equals("")) {
                        button_number_one_for_emergency.setText("Push ups");
                    } else if (button_number_two_for_emergency.getText().toString().equals("")) {
                        button_number_two_for_emergency.setText("Push ups");
                    } else if (button_number_three_for_emergency.getText().toString().equals("")) {
                        button_number_three_for_emergency.setText("Push ups");
                    } else {
                        button_number_four_for_emergency.setText("Push ups");
                    }
                }
                if (habits.contains("Quotes")) {
                    if (button_number_one_for_emergency.getText().toString().equals("")) {
                        button_number_one_for_emergency.setText("Quotes");
                    } else if (button_number_two_for_emergency.getText().toString().equals("")) {
                        button_number_two_for_emergency.setText("Quotes");
                    } else if (button_number_three_for_emergency.getText().toString().equals("")) {
                        button_number_three_for_emergency.setText("Quotes");
                    } else if (button_number_four_for_emergency.getText().toString().equals("")) {
                        button_number_four_for_emergency.setText("Quotes");
                    } else {
                        button_number_five_for_emergency.setText("Quotes");
                    }
                }
                if (habits.contains("Mediation")) {
                    if (button_number_one_for_emergency.getText().toString().equals("")) {
                        button_number_one_for_emergency.setText("Mediation");
                    } else if (button_number_two_for_emergency.getText().toString().equals("")) {
                        button_number_two_for_emergency.setText("Mediation");
                    } else if (button_number_three_for_emergency.getText().toString().equals("")) {
                        button_number_three_for_emergency.setText("Mediation");
                    } else if (button_number_four_for_emergency.getText().toString().equals("")) {
                        button_number_four_for_emergency.setText("Mediation");
                    } else if (button_number_five_for_emergency.getText().toString().equals("")) {
                        button_number_five_for_emergency.setText("Mediation");
                    } else {
                        button_number_six_for_emergency.setText("Mediation");
                    }
                }
                if (habits.contains("Counter")) {
                    if (button_number_one_for_emergency.getText().toString().equals("")) {
                        button_number_one_for_emergency.setText("Counter");
                    } else if (button_number_two_for_emergency.getText().toString().equals("")) {
                        button_number_two_for_emergency.setText("Counter");
                    } else if (button_number_three_for_emergency.getText().toString().equals("")) {
                        button_number_three_for_emergency.setText("Counter");
                    } else if (button_number_four_for_emergency.getText().toString().equals("")) {
                        button_number_four_for_emergency.setText("Counter");
                    } else if (button_number_five_for_emergency.getText().toString().equals("")) {
                        button_number_five_for_emergency.setText("Counter");
                    } else if (button_number_six_for_emergency.getText().toString().equals("")) {
                        button_number_six_for_emergency.setText("Counter");
                    } else {
                        button_number_seven_for_emergency.setText("Counter");
                    }
                }
                if (habits.contains("Phone Usage")) {
                    if (button_number_one_for_emergency.getText().toString().equals("")) {
                        button_number_one_for_emergency.setText("Phone Usage");
                    } else if (button_number_two_for_emergency.getText().toString().equals("")) {
                        button_number_two_for_emergency.setText("Phone Usage");
                    } else if (button_number_three_for_emergency.getText().toString().equals("")) {
                        button_number_three_for_emergency.setText("Phone Usage");
                    } else if (button_number_four_for_emergency.getText().toString().equals("")) {
                        button_number_four_for_emergency.setText("Phone Usage");
                    } else if (button_number_five_for_emergency.getText().toString().equals("")) {
                        button_number_five_for_emergency.setText("Phone Usage");
                    } else if (button_number_six_for_emergency.getText().toString().equals("")) {
                        button_number_six_for_emergency.setText("Phone Usage");
                    } else if (button_number_seven_for_emergency.getText().toString().equals("")) {
                        button_number_seven_for_emergency.setText("Phone Usage");
                    } else {
                        button_number_eight_for_emergency.setText("Phone Usage");
                    }
                }
                if (habits.contains("User Stories")) {
                    if (button_number_one_for_emergency.getText().toString().equals("")) {
                        button_number_one_for_emergency.setText("User Stories");
                    } else if (button_number_two_for_emergency.getText().toString().equals("")) {
                        button_number_two_for_emergency.setText("User Stories");
                    } else if (button_number_three_for_emergency.getText().toString().equals("")) {
                        button_number_three_for_emergency.setText("User Stories");
                    } else if (button_number_four_for_emergency.getText().toString().equals("")) {
                        button_number_four_for_emergency.setText("User Stories");
                    } else if (button_number_five_for_emergency.getText().toString().equals("")) {
                        button_number_five_for_emergency.setText("User Stories");
                    } else if (button_number_six_for_emergency.getText().toString().equals("")) {
                        button_number_six_for_emergency.setText("User Stories");
                    } else if (button_number_seven_for_emergency.getText().toString().equals("")) {
                        button_number_seven_for_emergency.setText("User Stories");
                    } else if (button_number_eight_for_emergency.getText().toString().equals("")) {
                        button_number_eight_for_emergency.setText("User Stories");
                    } else {
                        button_number_nine_for_emergency.setText("User Stories");
                    }
                }
                if (habits.contains("Running/Biking")) {
                    if (button_number_one_for_emergency.getText().toString().equals("")) {
                        button_number_one_for_emergency.setText("Running/Biking");
                    } else if (button_number_two_for_emergency.getText().toString().equals("")) {
                        button_number_two_for_emergency.setText("Running/Biking");
                    } else if (button_number_three_for_emergency.getText().toString().equals("")) {
                        button_number_three_for_emergency.setText("Running/Biking");
                    } else if (button_number_four_for_emergency.getText().toString().equals("")) {
                        button_number_four_for_emergency.setText("Running/Biking");
                    } else if (button_number_five_for_emergency.getText().toString().equals("")) {
                        button_number_five_for_emergency.setText("Running/Biking");
                    } else if (button_number_six_for_emergency.getText().toString().equals("")) {
                        button_number_six_for_emergency.setText("Running/Biking");
                    } else if (button_number_seven_for_emergency.getText().toString().equals("")) {
                        button_number_seven_for_emergency.setText("Running/Biking");
                    } else if (button_number_eight_for_emergency.getText().toString().equals("")) {
                        button_number_eight_for_emergency.setText("Running/Biking");
                    } else if (button_number_nine_for_emergency.getText().toString().equals("")) {
                        button_number_nine_for_emergency.setText("Running/Biking");
                    } else {
                        button_number_ten_for_emergency.setText("Running/Biking");
                    }
                }
                if (habits.contains("Weight Tracker")) {
                    if (button_number_one_for_emergency.getText().toString().equals("")) {
                        button_number_one_for_emergency.setText("Weight Tracker");
                    } else if (button_number_two_for_emergency.getText().toString().equals("")) {
                        button_number_two_for_emergency.setText("Weight Tracker");
                    } else if (button_number_three_for_emergency.getText().toString().equals("")) {
                        button_number_three_for_emergency.setText("Weight Tracker");
                    } else if (button_number_four_for_emergency.getText().toString().equals("")) {
                        button_number_four_for_emergency.setText("Weight Tracker");
                    } else if (button_number_five_for_emergency.getText().toString().equals("")) {
                        button_number_five_for_emergency.setText("Weight Tracker");
                    } else if (button_number_six_for_emergency.getText().toString().equals("")) {
                        button_number_six_for_emergency.setText("Weight Tracker");
                    } else if (button_number_seven_for_emergency.getText().toString().equals("")) {
                        button_number_seven_for_emergency.setText("Weight Tracker");
                    } else if (button_number_eight_for_emergency.getText().toString().equals("")) {
                        button_number_eight_for_emergency.setText("Weight Tracker");
                    } else if (button_number_nine_for_emergency.getText().toString().equals("")) {
                        button_number_nine_for_emergency.setText("Weight Tracker");
                    } else if (button_number_ten_for_emergency.getText().toString().equals("")) {
                        button_number_ten_for_emergency.setText("Weight Tracker");
                    } else {
                        button_number_eleven_for_emergency.setText("Weight Tracker");
                    }
                }
                if (button_number_two_for_emergency.getText().toString().equals("")) {
                    ConstraintLayout constraintLayout = getActivity().findViewById(R.id.id_for_emergency_before_buttons);
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(button_number_one_for_emergency.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
                    constraintSet.connect(button_number_one_for_emergency.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                    constraintSet.applyTo(constraintLayout);
                    button_number_two_for_emergency.setVisibility(View.GONE);
                    button_number_three_for_emergency.setVisibility(View.GONE);
                    button_number_four_for_emergency.setVisibility(View.GONE);
                    button_number_five_for_emergency.setVisibility(View.GONE);
                    button_number_six_for_emergency.setVisibility(View.GONE);
                    button_number_seven_for_emergency.setVisibility(View.GONE);
                    button_number_eight_for_emergency.setVisibility(View.GONE);
                    button_number_nine_for_emergency.setVisibility(View.GONE);
                    button_number_ten_for_emergency.setVisibility(View.GONE);
                    button_number_eleven_for_emergency.setVisibility(View.GONE);
                } else if (button_number_three_for_emergency.getText().toString().equals("")) {
                    button_number_three_for_emergency.setVisibility(View.GONE);
                    button_number_four_for_emergency.setVisibility(View.GONE);
                    button_number_five_for_emergency.setVisibility(View.GONE);
                    button_number_six_for_emergency.setVisibility(View.GONE);
                    button_number_seven_for_emergency.setVisibility(View.GONE);
                    button_number_eight_for_emergency.setVisibility(View.GONE);
                    button_number_nine_for_emergency.setVisibility(View.GONE);
                    button_number_ten_for_emergency.setVisibility(View.GONE);
                    button_number_eleven_for_emergency.setVisibility(View.GONE);
                } else if (button_number_four_for_emergency.getText().toString().equals("")) {
                    ConstraintLayout constraintLayout = getActivity().findViewById(R.id.id_for_emergency_before_buttons);
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(button_number_three_for_emergency.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
                    constraintSet.connect(button_number_three_for_emergency.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                    constraintSet.applyTo(constraintLayout);
                    button_number_four_for_emergency.setVisibility(View.GONE);
                    button_number_five_for_emergency.setVisibility(View.GONE);
                    button_number_six_for_emergency.setVisibility(View.GONE);
                    button_number_seven_for_emergency.setVisibility(View.GONE);
                    button_number_eight_for_emergency.setVisibility(View.GONE);
                    button_number_nine_for_emergency.setVisibility(View.GONE);
                    button_number_ten_for_emergency.setVisibility(View.GONE);
                    button_number_eleven_for_emergency.setVisibility(View.GONE);
                } else if (button_number_five_for_emergency.getText().toString().equals("")) {
                    button_number_five_for_emergency.setVisibility(View.GONE);
                    button_number_six_for_emergency.setVisibility(View.GONE);
                    button_number_seven_for_emergency.setVisibility(View.GONE);
                    button_number_eight_for_emergency.setVisibility(View.GONE);
                    button_number_nine_for_emergency.setVisibility(View.GONE);
                    button_number_ten_for_emergency.setVisibility(View.GONE);
                    button_number_eleven_for_emergency.setVisibility(View.GONE);
                } else if (button_number_six_for_emergency.getText().toString().equals("")) {
                    ConstraintLayout constraintLayout = getActivity().findViewById(R.id.id_for_emergency_before_buttons);
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(button_number_five_for_emergency.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
                    constraintSet.connect(button_number_five_for_emergency.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                    constraintSet.applyTo(constraintLayout);
                    button_number_six_for_emergency.setVisibility(View.GONE);
                    button_number_seven_for_emergency.setVisibility(View.GONE);
                    button_number_eight_for_emergency.setVisibility(View.GONE);
                    button_number_nine_for_emergency.setVisibility(View.GONE);
                    button_number_ten_for_emergency.setVisibility(View.GONE);
                    button_number_eleven_for_emergency.setVisibility(View.GONE);
                } else if (button_number_seven_for_emergency.getText().toString().equals("")) {
                    button_number_seven_for_emergency.setVisibility(View.GONE);
                    button_number_eight_for_emergency.setVisibility(View.GONE);
                    button_number_nine_for_emergency.setVisibility(View.GONE);
                    button_number_ten_for_emergency.setVisibility(View.GONE);
                    button_number_eleven_for_emergency.setVisibility(View.GONE);
                } else if (button_number_eight_for_emergency.getText().toString().equals("")) {
                    ConstraintLayout constraintLayout = getActivity().findViewById(R.id.id_for_emergency_before_buttons);
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(button_number_seven_for_emergency.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
                    constraintSet.connect(button_number_seven_for_emergency.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                    constraintSet.applyTo(constraintLayout);
                    button_number_eight_for_emergency.setVisibility(View.GONE);
                    button_number_nine_for_emergency.setVisibility(View.GONE);
                    button_number_ten_for_emergency.setVisibility(View.GONE);
                    button_number_eleven_for_emergency.setVisibility(View.GONE);
                } else if (button_number_nine_for_emergency.getText().toString().equals("")) {
                    button_number_nine_for_emergency.setVisibility(View.GONE);
                    button_number_ten_for_emergency.setVisibility(View.GONE);
                    button_number_eleven_for_emergency.setVisibility(View.GONE);
                } else if (button_number_ten_for_emergency.getText().toString().equals("")) {
                    ConstraintLayout constraintLayout = getActivity().findViewById(R.id.id_for_emergency_before_buttons);
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(button_number_nine_for_emergency.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
                    constraintSet.connect(button_number_nine_for_emergency.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                    constraintSet.applyTo(constraintLayout);
                    button_number_ten_for_emergency.setVisibility(View.GONE);
                    button_number_eleven_for_emergency.setVisibility(View.GONE);
                } else if (button_number_eleven_for_emergency.getText().toString().equals("")) {
                    button_number_eleven_for_emergency.setVisibility(View.GONE);
                }
            }
        }
    }*/

    /*private void all_button_listen() {
        if (getActivity() != null) {
            final Button button_number_one_for_emergency = getActivity().findViewById(R.id.button_number_one_for_emergency);
            final Button button_number_two_for_emergency = getActivity().findViewById(R.id.button_number_two_for_emergency);
            final Button button_number_three_for_emergency = getActivity().findViewById(R.id.button_number_three_for_emergency);
            final Button button_number_four_for_emergency = getActivity().findViewById(R.id.button_number_four_for_emergency);
            final Button button_number_five_for_emergency = getActivity().findViewById(R.id.button_number_five_for_emergency);
            final Button button_number_six_for_emergency = getActivity().findViewById(R.id.button_number_six_for_emergency);
            final Button button_number_seven_for_emergency = getActivity().findViewById(R.id.button_number_seven_for_emergency);
            final Button button_number_eight_for_emergency = getActivity().findViewById(R.id.button_number_eight_for_emergency);
            final Button button_number_nine_for_emergency = getActivity().findViewById(R.id.button_number_nine_for_emergency);
            final Button button_number_ten_for_emergency = getActivity().findViewById(R.id.button_number_ten_for_emergency);
            final Button button_number_eleven_for_emergency = getActivity().findViewById(R.id.button_number_eleven_for_emergency);
            button_number_one_for_emergency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity(button_number_one_for_emergency.getText().toString());
                }
            });
            button_number_two_for_emergency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity(button_number_two_for_emergency.getText().toString());
                }
            });
            button_number_three_for_emergency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity(button_number_three_for_emergency.getText().toString());
                }
            });
            button_number_four_for_emergency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity(button_number_four_for_emergency.getText().toString());
                }
            });
            button_number_five_for_emergency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity(button_number_five_for_emergency.getText().toString());
                }
            });
            button_number_six_for_emergency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity(button_number_six_for_emergency.getText().toString());
                }
            });
            button_number_seven_for_emergency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity(button_number_seven_for_emergency.getText().toString());
                }
            });
            button_number_eight_for_emergency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity(button_number_eight_for_emergency.getText().toString());
                }
            });
            button_number_nine_for_emergency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity(button_number_nine_for_emergency.getText().toString());
                }
            });
            button_number_ten_for_emergency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity(button_number_ten_for_emergency.getText().toString());
                }
            });
            button_number_eleven_for_emergency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_the_right_activity(button_number_eleven_for_emergency.getText().toString());
                }
            });
        }
    }*/
   /* private void load_card() {
        if (getActivity() != null) {
            viewPager = getActivity().findViewById(R.id.view_pager_to_show_options_in_emergency);
            viewPager.setPadding(20, 0, 20, 0);
            viewPager.setClipToPadding(false);
            viewPager.setPageMargin(10);
            model_for_emergency_optionsArrayList = new ArrayList<>();
            model_for_emergency_optionsArrayList.add(new Model_for_emergency_options("Journal", color_me(R.drawable.round_edit_24)));
            model_for_emergency_optionsArrayList.add(new Model_for_emergency_options("Lock phone", color_me(R.drawable.round_screen_lock_portrait_24)));
            model_for_emergency_optionsArrayList.add(new Model_for_emergency_options("Cold shower", color_me(R.drawable.round_bathtub_24)));
            model_for_emergency_optionsArrayList.add(new Model_for_emergency_options("Pushups", color_me(R.drawable.round_fitness_center_24)));
            model_for_emergency_optionsArrayList.add(new Model_for_emergency_options("Motivational Quotes", color_me(R.drawable.round_format_quote_24)));
            model_for_emergency_optionsArrayList.add(new Model_for_emergency_options("Mediation", color_me(R.drawable.round_self_improvement_24)));
            model_for_emergency_optionsArrayList.add(new Model_for_emergency_options("Counter", color_me(R.drawable.round_add_circle_24)));
            model_for_emergency_optionsArrayList.add(new Model_for_emergency_options("Phone usage", color_me(R.drawable.stats_colored_fav)));
            model_for_emergency_optionsArrayList.add(new Model_for_emergency_options("User stories", color_me(R.drawable.round_group_24)));
            model_for_emergency_optionsArrayList.add(new Model_for_emergency_options("Running/Biking", color_me(R.drawable.round_directions_run_24)));
            model_for_emergency_optionsArrayList.add(new Model_for_emergency_options("Weight Tracker", color_me(R.drawable.round_local_dining_24)));
            adapter_for_emergency_options = new Adapter_for_emergency_options(getActivity(), model_for_emergency_optionsArrayList);
            viewPager.setAdapter(adapter_for_emergency_options);
            viewPager.setPadding(100,0,100,0);
        }
    }*/

}
