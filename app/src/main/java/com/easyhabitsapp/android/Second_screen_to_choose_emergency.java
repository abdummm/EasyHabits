package com.easyhabitsapp.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Second_screen_to_choose_emergency extends AppCompatActivity {
    private boolean first_intent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen_to_choose_emergency);
        play_the_anim_once();
        hide_top_bar();
        read_and_set();
        button_listen();
        back_button_listen();
        next_button_listen();
    }

    private void hide_top_bar() {
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().hide();
        }
    }

    private void read_and_set() {
        SharedPreferences sharedPreferences = getSharedPreferences("all_the_bad_habits", MODE_PRIVATE);
        String string = sharedPreferences.getString("bad_habits", "");
        if (string != null) {
            String[] splitter = string.split("__small_split__");
            select_the_buttons(1);
            if (splitter[1].toLowerCase().equals("video games")) {
                select_the_buttons(4);
                select_the_buttons(5);
                select_the_buttons(7);
                select_the_buttons(9);
            } else if (splitter[1].toLowerCase().equals("social media")) {
                select_the_buttons(2);
                select_the_buttons(6);
                select_the_buttons(8);
                select_the_buttons(9);
            } else if (splitter[1].toLowerCase().equals("impulsive shopping")) {
                select_the_buttons(2);
                select_the_buttons(9);
            } else if (splitter[1].toLowerCase().equals("porn / masturbating")) {
                select_the_buttons(2);
                select_the_buttons(3);
                select_the_buttons(4);
                select_the_buttons(5);
                select_the_buttons(6);
                select_the_buttons(7);
                select_the_buttons(9);
            } else if (splitter[1].toLowerCase().equals("smoking")) {
                select_the_buttons(4);
                select_the_buttons(5);
                select_the_buttons(6);
                select_the_buttons(7);
                select_the_buttons(9);
            } else if (splitter[1].toLowerCase().equals("alcohol")) {
                select_the_buttons(5);
                select_the_buttons(6);
                select_the_buttons(7);
                select_the_buttons(9);
            } else if (splitter[1].toLowerCase().equals("drugs")) {
                select_the_buttons(5);
                select_the_buttons(6);
                select_the_buttons(7);
                select_the_buttons(9);
            } else if (splitter[1].toLowerCase().equals("cursing")) {
                select_the_buttons(10);
            } else if (splitter[1].toLowerCase().equals("procrastination")) {
                select_the_buttons(10);
            } else if (splitter[1].toLowerCase().equals("lying")) {
                select_the_buttons(10);
            } else if (splitter[1].toLowerCase().equals("fast food")) {
                select_the_buttons(4);
                select_the_buttons(5);
                select_the_buttons(7);
                select_the_buttons(9);
                select_the_buttons(11);
            } else if (splitter[1].toLowerCase().equals("sugar")) {
                select_the_buttons(4);
                select_the_buttons(5);
                select_the_buttons(7);
                select_the_buttons(9);
                select_the_buttons(11);
            } else if (splitter[1].toLowerCase().equals("over eating")) {
                select_the_buttons(4);
                select_the_buttons(5);
                select_the_buttons(7);
                select_the_buttons(9);
                select_the_buttons(11);
            } else if (splitter[1].toLowerCase().equals("gambling")) {
                select_the_buttons(2);
                select_the_buttons(6);
                select_the_buttons(7);
                select_the_buttons(9);
            }
        }
    }

    private void select_the_buttons(int which) {
        Button journal_button_on_off_at_start = findViewById(R.id.journal_button_on_off_at_start);
        Button lock_phone_button_on_off_at_start = findViewById(R.id.lock_phone_button_on_off_at_start);
        Button cold_shower_button_on_off_at_start = findViewById(R.id.cold_shower_button_on_off_at_start);
        Button push_ups_button_on_off_at_start = findViewById(R.id.push_ups_button_on_off_at_start);
        Button running_or_biking_phone_button_on_off_at_start = findViewById(R.id.running_or_biking_phone_button_on_off_at_start);
        Button mediation_button_on_off_at_start = findViewById(R.id.mediation_button_on_off_at_start);
        Button motivational_quotes_button_on_off_at_start = findViewById(R.id.motivational_quotes_button_on_off_at_start);
        Button view_total_phone_usage_phone_button_on_off_at_start = findViewById(R.id.view_total_phone_usage_phone_button_on_off_at_start);
        Button view_user_submitted_stories_button_on_off_at_start = findViewById(R.id.view_user_submitted_stories_button_on_off_at_start);
        Button counter_button_on_off_at_start = findViewById(R.id.counter_button_on_off_at_start);
        Button weight_tracker_button_on_off_at_start = findViewById(R.id.weight_tracker_button_on_off_at_start);
        if (which == 1) {
            journal_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            journal_button_on_off_at_start.setTextColor(Color.WHITE);
            journal_button_on_off_at_start.setText(journal_button_on_off_at_start.getText().toString().concat(" ✓"));
        } else if (which == 2) {
            lock_phone_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            lock_phone_button_on_off_at_start.setTextColor(Color.WHITE);
            lock_phone_button_on_off_at_start.setText(lock_phone_button_on_off_at_start.getText().toString().concat(" ✓"));
        } else if (which == 3) {
            cold_shower_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            cold_shower_button_on_off_at_start.setTextColor(Color.WHITE);
            cold_shower_button_on_off_at_start.setText(cold_shower_button_on_off_at_start.getText().toString().concat(" ✓"));
        } else if (which == 4) {
            push_ups_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            push_ups_button_on_off_at_start.setTextColor(Color.WHITE);
            push_ups_button_on_off_at_start.setText(push_ups_button_on_off_at_start.getText().toString().concat(" ✓"));
        } else if (which == 5) {
            running_or_biking_phone_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            running_or_biking_phone_button_on_off_at_start.setTextColor(Color.WHITE);
            running_or_biking_phone_button_on_off_at_start.setText(running_or_biking_phone_button_on_off_at_start.getText().toString().concat(" ✓"));
        } else if (which == 6) {
            mediation_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            mediation_button_on_off_at_start.setTextColor(Color.WHITE);
            mediation_button_on_off_at_start.setText(mediation_button_on_off_at_start.getText().toString().concat(" ✓"));
        } else if (which == 7) {
            motivational_quotes_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            motivational_quotes_button_on_off_at_start.setTextColor(Color.WHITE);
            motivational_quotes_button_on_off_at_start.setText(motivational_quotes_button_on_off_at_start.getText().toString().concat(" ✓"));
        } else if (which == 8) {
            view_total_phone_usage_phone_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            view_total_phone_usage_phone_button_on_off_at_start.setTextColor(Color.WHITE);
            view_total_phone_usage_phone_button_on_off_at_start.setText(view_total_phone_usage_phone_button_on_off_at_start.getText().toString().concat(" ✓"));
        } else if (which == 9) {
            view_user_submitted_stories_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            view_user_submitted_stories_button_on_off_at_start.setTextColor(Color.WHITE);
            view_user_submitted_stories_button_on_off_at_start.setText(view_user_submitted_stories_button_on_off_at_start.getText().toString().concat(" ✓"));
        } else if (which == 10) {
            counter_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            counter_button_on_off_at_start.setTextColor(Color.WHITE);
            counter_button_on_off_at_start.setText(counter_button_on_off_at_start.getText().toString().concat(" ✓"));
        } else if (which == 11) {
            weight_tracker_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#607D8B")));
            weight_tracker_button_on_off_at_start.setTextColor(Color.WHITE);
            weight_tracker_button_on_off_at_start.setText(weight_tracker_button_on_off_at_start.getText().toString().concat(" ✓"));
        }
    }

    private void remove_some_buttons(int which) {
        Button journal_button_on_off_at_start = findViewById(R.id.journal_button_on_off_at_start);
        Button lock_phone_button_on_off_at_start = findViewById(R.id.lock_phone_button_on_off_at_start);
        Button cold_shower_button_on_off_at_start = findViewById(R.id.cold_shower_button_on_off_at_start);
        Button push_ups_button_on_off_at_start = findViewById(R.id.push_ups_button_on_off_at_start);
        Button running_or_biking_phone_button_on_off_at_start = findViewById(R.id.running_or_biking_phone_button_on_off_at_start);
        Button mediation_button_on_off_at_start = findViewById(R.id.mediation_button_on_off_at_start);
        Button motivational_quotes_button_on_off_at_start = findViewById(R.id.motivational_quotes_button_on_off_at_start);
        Button view_total_phone_usage_phone_button_on_off_at_start = findViewById(R.id.view_total_phone_usage_phone_button_on_off_at_start);
        Button view_user_submitted_stories_button_on_off_at_start = findViewById(R.id.view_user_submitted_stories_button_on_off_at_start);
        Button counter_button_on_off_at_start = findViewById(R.id.counter_button_on_off_at_start);
        Button weight_tracker_button_on_off_at_start = findViewById(R.id.weight_tracker_button_on_off_at_start);
        if (which == 1) {
            journal_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            journal_button_on_off_at_start.setTextColor(Color.BLACK);
            journal_button_on_off_at_start.setText(journal_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        } else if (which == 2) {
            lock_phone_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            lock_phone_button_on_off_at_start.setTextColor(Color.BLACK);
            lock_phone_button_on_off_at_start.setText(lock_phone_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        } else if (which == 3) {
            cold_shower_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            cold_shower_button_on_off_at_start.setTextColor(Color.BLACK);
            cold_shower_button_on_off_at_start.setText(cold_shower_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        } else if (which == 4) {
            push_ups_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            push_ups_button_on_off_at_start.setTextColor(Color.BLACK);
            push_ups_button_on_off_at_start.setText(push_ups_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        } else if (which == 5) {
            running_or_biking_phone_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            running_or_biking_phone_button_on_off_at_start.setTextColor(Color.BLACK);
            running_or_biking_phone_button_on_off_at_start.setText(running_or_biking_phone_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        } else if (which == 6) {
            mediation_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            mediation_button_on_off_at_start.setTextColor(Color.BLACK);
            mediation_button_on_off_at_start.setText(mediation_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        } else if (which == 7) {
            motivational_quotes_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            motivational_quotes_button_on_off_at_start.setTextColor(Color.BLACK);
            motivational_quotes_button_on_off_at_start.setText(motivational_quotes_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        } else if (which == 8) {
            view_total_phone_usage_phone_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            view_total_phone_usage_phone_button_on_off_at_start.setTextColor(Color.BLACK);
            view_total_phone_usage_phone_button_on_off_at_start.setText(view_total_phone_usage_phone_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        } else if (which == 9) {
            view_user_submitted_stories_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            view_user_submitted_stories_button_on_off_at_start.setTextColor(Color.BLACK);
            view_user_submitted_stories_button_on_off_at_start.setText(view_user_submitted_stories_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        } else if (which == 10) {
            counter_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            counter_button_on_off_at_start.setTextColor(Color.BLACK);
            counter_button_on_off_at_start.setText(counter_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        } else if (which == 11) {
            weight_tracker_button_on_off_at_start.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d6d7d7")));
            weight_tracker_button_on_off_at_start.setTextColor(Color.BLACK);
            weight_tracker_button_on_off_at_start.setText(weight_tracker_button_on_off_at_start.getText().toString().replace(" ✓", ""));
        }
    }

    private void button_listen() {
        final Button journal_button_on_off_at_start = findViewById(R.id.journal_button_on_off_at_start);
        final Button lock_phone_button_on_off_at_start = findViewById(R.id.lock_phone_button_on_off_at_start);
        final Button cold_shower_button_on_off_at_start = findViewById(R.id.cold_shower_button_on_off_at_start);
        final Button push_ups_button_on_off_at_start = findViewById(R.id.push_ups_button_on_off_at_start);
        final Button running_or_biking_phone_button_on_off_at_start = findViewById(R.id.running_or_biking_phone_button_on_off_at_start);
        final Button mediation_button_on_off_at_start = findViewById(R.id.mediation_button_on_off_at_start);
        final Button motivational_quotes_button_on_off_at_start = findViewById(R.id.motivational_quotes_button_on_off_at_start);
        final Button view_total_phone_usage_phone_button_on_off_at_start = findViewById(R.id.view_total_phone_usage_phone_button_on_off_at_start);
        final Button view_user_submitted_stories_button_on_off_at_start = findViewById(R.id.view_user_submitted_stories_button_on_off_at_start);
        final Button counter_button_on_off_at_start = findViewById(R.id.counter_button_on_off_at_start);
        final Button weight_tracker_button_on_off_at_start = findViewById(R.id.weight_tracker_button_on_off_at_start);
        journal_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (journal_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(1);
                } else {
                    select_the_buttons(1);
                }
            }
        });
        lock_phone_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lock_phone_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(2);
                } else {
                    select_the_buttons(2);
                }
            }
        });
        cold_shower_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cold_shower_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(3);
                } else {
                    select_the_buttons(3);
                }
            }
        });
        push_ups_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (push_ups_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(4);
                } else {
                    select_the_buttons(4);
                }
            }
        });
        running_or_biking_phone_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (running_or_biking_phone_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(5);
                } else {
                    select_the_buttons(5);
                }
            }
        });
        mediation_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediation_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(6);
                } else {
                    select_the_buttons(6);
                }
            }
        });
        motivational_quotes_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (motivational_quotes_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(7);
                } else {
                    select_the_buttons(7);
                }
            }
        });
        view_total_phone_usage_phone_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view_total_phone_usage_phone_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(8);
                } else {
                    select_the_buttons(8);
                }
            }
        });
        view_user_submitted_stories_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view_user_submitted_stories_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(9);
                } else {
                    select_the_buttons(9);
                }
            }
        });
        counter_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(10);
                } else {
                    select_the_buttons(10);
                }
            }
        });
        weight_tracker_button_on_off_at_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (weight_tracker_button_on_off_at_start.getCurrentTextColor() == -1) {
                    remove_some_buttons(11);
                } else {
                    select_the_buttons(11);
                }
            }
        });
    }

    private void back_button_listen() {
        Button back_button_to_previous = findViewById(R.id.back_button_to_previous);
        back_button_to_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Second_screen_to_choose_emergency.this, Which_habit_do_i_choose.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
            }
        });
    }

    private void next_button_listen() {
        Button next_button_to_next = findViewById(R.id.next_button_to_next);
        next_button_to_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check_if_at_least_one_button()) {
                    save_which_buttons();
                    startActivity(new Intent(Second_screen_to_choose_emergency.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please choose at least one feature", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.swipe_left, R.anim.swipe_out_right);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Objects.equals(intent.getStringExtra("classFrom"), "first")) {
            overridePendingTransition(R.anim.swipe_right, R.anim.swipe_out_left);
        } else {
            //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            overridePendingTransition(R.anim.swipe_left, R.anim.swipe_out_right);
        }
    }

    private void play_the_anim_once() {
        if (!first_intent) {
            overridePendingTransition(R.anim.swipe_right, R.anim.swipe_out_left);
            first_intent = true;
        }
    }

    private void save_which_buttons() {
        String save_me = "";
        Button journal_button_on_off_at_start = findViewById(R.id.journal_button_on_off_at_start);
        Button lock_phone_button_on_off_at_start = findViewById(R.id.lock_phone_button_on_off_at_start);
        Button cold_shower_button_on_off_at_start = findViewById(R.id.cold_shower_button_on_off_at_start);
        Button push_ups_button_on_off_at_start = findViewById(R.id.push_ups_button_on_off_at_start);
        Button running_or_biking_phone_button_on_off_at_start = findViewById(R.id.running_or_biking_phone_button_on_off_at_start);
        Button mediation_button_on_off_at_start = findViewById(R.id.mediation_button_on_off_at_start);
        Button motivational_quotes_button_on_off_at_start = findViewById(R.id.motivational_quotes_button_on_off_at_start);
        Button view_total_phone_usage_phone_button_on_off_at_start = findViewById(R.id.view_total_phone_usage_phone_button_on_off_at_start);
        Button view_user_submitted_stories_button_on_off_at_start = findViewById(R.id.view_user_submitted_stories_button_on_off_at_start);
        Button counter_button_on_off_at_start = findViewById(R.id.counter_button_on_off_at_start);
        Button weight_tracker_button_on_off_at_start = findViewById(R.id.weight_tracker_button_on_off_at_start);
        //String save_me = edit_text_showing_habit_name.getText().toString().concat("__small_split__").concat(which_spinner_is_chosen).concat("__small_split__").concat(return_the_color_name()).concat("##splitting_for_bad_habits##");
        if (journal_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(journal_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        if (lock_phone_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(lock_phone_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        if (cold_shower_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(cold_shower_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        if (push_ups_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(push_ups_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        if (running_or_biking_phone_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(running_or_biking_phone_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        if (mediation_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(mediation_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        if (motivational_quotes_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(motivational_quotes_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        if (view_total_phone_usage_phone_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(view_total_phone_usage_phone_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        if (view_user_submitted_stories_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(view_user_submitted_stories_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        if (counter_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(counter_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        if (weight_tracker_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            save_me = save_me.concat(weight_tracker_button_on_off_at_start.getText().toString().replace(" ✓", "")).concat("__small_split__");
        }
        save_me = save_me.concat("##splitting_for_bad_habits##");
        SharedPreferences sharedPreferences = getSharedPreferences("emergency_options", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("emergency", save_me);
        myEdit.commit();
    }

    private Boolean check_if_at_least_one_button() {
        Button journal_button_on_off_at_start = findViewById(R.id.journal_button_on_off_at_start);
        Button lock_phone_button_on_off_at_start = findViewById(R.id.lock_phone_button_on_off_at_start);
        Button cold_shower_button_on_off_at_start = findViewById(R.id.cold_shower_button_on_off_at_start);
        Button push_ups_button_on_off_at_start = findViewById(R.id.push_ups_button_on_off_at_start);
        Button running_or_biking_phone_button_on_off_at_start = findViewById(R.id.running_or_biking_phone_button_on_off_at_start);
        Button mediation_button_on_off_at_start = findViewById(R.id.mediation_button_on_off_at_start);
        Button motivational_quotes_button_on_off_at_start = findViewById(R.id.motivational_quotes_button_on_off_at_start);
        Button view_total_phone_usage_phone_button_on_off_at_start = findViewById(R.id.view_total_phone_usage_phone_button_on_off_at_start);
        Button view_user_submitted_stories_button_on_off_at_start = findViewById(R.id.view_user_submitted_stories_button_on_off_at_start);
        Button counter_button_on_off_at_start = findViewById(R.id.counter_button_on_off_at_start);
        Button weight_tracker_button_on_off_at_start = findViewById(R.id.weight_tracker_button_on_off_at_start);
        if (journal_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (lock_phone_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (cold_shower_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (push_ups_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (running_or_biking_phone_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (mediation_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (motivational_quotes_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (view_total_phone_usage_phone_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (view_user_submitted_stories_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (counter_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        } else if (weight_tracker_button_on_off_at_start.getCurrentTextColor() == Color.WHITE) {
            return true;
        }
        return false;
    }
}