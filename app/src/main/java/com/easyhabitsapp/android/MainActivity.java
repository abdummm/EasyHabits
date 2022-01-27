package com.easyhabitsapp.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_choose_date_listen();
        hide_top_bar();
        watching_text();
        button_listen();
        delete_the_shared();
        back_button_listen_to_the_previous();
        test();
    }
    private boolean check_the_name() {
        EditText enter_name_edittext = findViewById(R.id.enter_name_edittext);
        if (enter_name_edittext.getText().toString().trim().length() < 18 && enter_name_edittext.getText().toString().trim().length() != 0) {
            SharedPreferences sharedPreferences = getSharedPreferences("main_name", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("name", enter_name_edittext.getText().toString().trim());
            myEdit.apply();
            return true;
        } else if (enter_name_edittext.getText().toString().trim().length() > 18) {
            enter_name_edittext.setError("Name can't be greater than 18 characters");
            Toast toast = Toast.makeText(getApplicationContext(), "Name can't be greater than 18 characters", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        } else {
            enter_name_edittext.setError("Name can't empty");
            Toast toast = Toast.makeText(getApplicationContext(), "Name can't empty", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
    }

    private boolean check_if_date_is_chosen() {
        SharedPreferences sharedPreferences = getSharedPreferences("main_streak", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        EditText enter_streak_edittext = findViewById(R.id.enter_streak_edittext);
        Button button_that_will_open_time_user_hacked = findViewById(R.id.button_that_will_open_time_user_hacked);
        if ((enter_streak_edittext.getText().toString().length() > 0 || (button_that_will_open_time_user_hacked.getBackground().getConstantState() == getResources().getDrawable(R.drawable.color_for_botton_on).getConstantState()))) {
            if (enter_streak_edittext.getText().toString().length() > 0) {
                Calendar calender = Calendar.getInstance();
                //calender.add(Calendar.DAY_OF_MONTH, 0);
                calender.set(Calendar.HOUR_OF_DAY, 0);
                calender.set(Calendar.MINUTE, 0);
                calender.set(Calendar.SECOND, 0);
                calender.set(Calendar.MILLISECOND, 0);
                long days_in_millis = calender.getTimeInMillis() - (86400000*Integer.parseInt(enter_streak_edittext.getText().toString()));
                myEdit.putLong("time_in_milli", days_in_millis);
                myEdit.apply();
            }
            return true;
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Please enter your streak or data of last relapse", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
    }

    private void button_choose_date_listen() {
        Button button_that_will_open_time_user_hacked = findViewById(R.id.button_that_will_open_time_user_hacked);
        button_that_will_open_time_user_hacked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText enter_streak_edittext = findViewById(R.id.enter_streak_edittext);
                enter_streak_edittext.clearFocus();
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), "date_picker");
            }
        });
    }

    private void hide_top_bar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    private void watching_text() {
        final EditText enter_streak_edittext = findViewById(R.id.enter_streak_edittext);
        enter_streak_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (enter_streak_edittext.hasFocus()) {
                    Button button_that_will_open_time_user_hacked = findViewById(R.id.button_that_will_open_time_user_hacked);
                    if (button_that_will_open_time_user_hacked.getCurrentTextColor() == -1) {
                        button_that_will_open_time_user_hacked.setBackgroundResource(R.drawable.color_for_botton_off);
                        button_that_will_open_time_user_hacked.setTextColor(Color.parseColor("#607D8B"));
                        ConstraintLayout constraintLayout = findViewById(R.id.main_activity_constraint_layout);
                        ConstraintSet constraintSet = new ConstraintSet();
                        constraintSet.clone(constraintLayout);
                        View empty_view_for_padding2_main_activity = findViewById(R.id.empty_view_for_padding2_main_activity);
                        constraintSet.clear(empty_view_for_padding2_main_activity.getId(), ConstraintSet.TOP);
                        constraintSet.connect(empty_view_for_padding2_main_activity.getId(), ConstraintSet.TOP, button_that_will_open_time_user_hacked.getId(), ConstraintSet.BOTTOM, dip_to_pixels(10));
                        constraintSet.applyTo(constraintLayout);
                        TextView showing_the_date_to_the_user = findViewById(R.id.showing_the_date_to_the_user);
                        showing_the_date_to_the_user.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private int dip_to_pixels(float dip) {
        Resources r = getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
    }

    private boolean check_the_goals() {
        EditText goal1_enter_for_first_time = findViewById(R.id.goal1_enter_for_first_time);
        EditText goal2_enter_for_first_time = findViewById(R.id.goal2_enter_for_first_time);
        EditText goal3_enter_for_first_time = findViewById(R.id.goal3_enter_for_first_time);
        EditText goal4_enter_for_first_time = findViewById(R.id.goal4_enter_for_first_time);
        EditText goal5_enter_for_first_time = findViewById(R.id.goal5_enter_for_first_time);
        int goal1;
        int goal2;
        int goal3;
        int goal4;
        int goal5;
        if (goal1_enter_for_first_time.getText().toString().length() > 0) {
            goal1 = Integer.parseInt(goal1_enter_for_first_time.getText().toString());
        } else {
            goal1 = 0;
        }
        if (goal2_enter_for_first_time.getText().toString().length() > 0) {
            goal2 = Integer.parseInt(goal2_enter_for_first_time.getText().toString());
        } else {
            goal2 = 0;
        }
        if (goal3_enter_for_first_time.getText().toString().length() > 0) {
            goal3 = Integer.parseInt(goal3_enter_for_first_time.getText().toString());
        } else {
            goal3 = 0;
        }
        if (goal4_enter_for_first_time.getText().toString().length() > 0) {
            goal4 = Integer.parseInt(goal4_enter_for_first_time.getText().toString());
        } else {
            goal4 = 0;
        }
        if (goal5_enter_for_first_time.getText().toString().length() > 0) {
            goal5 = Integer.parseInt(goal5_enter_for_first_time.getText().toString());
        } else {
            goal5 = 0;
        }
        boolean is_there_a_problem = false;
        if (goal1 == goal2) {
            is_there_a_problem = true;
            goal1_enter_for_first_time.setError("Goal one can't be equal to goal two");
            goal2_enter_for_first_time.setError("Goal two can't be equal to goal one");
        }
        if (goal1 == goal3) {
            is_there_a_problem = true;
            goal1_enter_for_first_time.setError("Goal one can't be equal to goal three");
            goal3_enter_for_first_time.setError("Goal three can't be equal to goal one");
        }
        if (goal1 == goal4) {
            is_there_a_problem = true;
            goal1_enter_for_first_time.setError("Goal one can't be equal to goal four");
            goal4_enter_for_first_time.setError("Goal four can't be equal to goal one");
        }
        if (goal1 == goal5) {
            is_there_a_problem = true;
            goal1_enter_for_first_time.setError("Goal one can't be equal to goal five");
            goal5_enter_for_first_time.setError("Goal five can't be equal to goal one");
        }
        if (goal2 == goal3) {
            is_there_a_problem = true;
            goal2_enter_for_first_time.setError("Goal two can't be equal to goal three");
            goal3_enter_for_first_time.setError("Goal three can't be equal to goal two");
        }
        if (goal2 == goal4) {
            is_there_a_problem = true;
            goal2_enter_for_first_time.setError("Goal two can't be equal to goal four");
            goal4_enter_for_first_time.setError("Goal four can't be equal to goal two");
        }
        if (goal2 == goal5) {
            is_there_a_problem = true;
            goal2_enter_for_first_time.setError("Goal two can't be equal to goal five");
            goal5_enter_for_first_time.setError("Goal five can't be equal to goal two");
        }
        if (goal3 == goal4) {
            is_there_a_problem = true;
            goal3_enter_for_first_time.setError("Goal three can't be equal to goal four");
            goal4_enter_for_first_time.setError("Goal four can't be equal to goal three");
        }
        if (goal3 == goal5) {
            is_there_a_problem = true;
            goal3_enter_for_first_time.setError("Goal three can't be equal to goal five");
            goal5_enter_for_first_time.setError("Goal five can't be equal to goal three");
        }
        if (goal4 == goal5) {
            is_there_a_problem = true;
            goal4_enter_for_first_time.setError("Goal four can't be equal to goal five");
            goal5_enter_for_first_time.setError("Goal five can't be equal to goal four");
        }
        if (!is_there_a_problem) {
            return true;
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Goals can't be matching", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
    }

    private boolean check_the_goal_int() {
        EditText goal1_enter_for_first_time = findViewById(R.id.goal1_enter_for_first_time);
        EditText goal2_enter_for_first_time = findViewById(R.id.goal2_enter_for_first_time);
        EditText goal3_enter_for_first_time = findViewById(R.id.goal3_enter_for_first_time);
        EditText goal4_enter_for_first_time = findViewById(R.id.goal4_enter_for_first_time);
        EditText goal5_enter_for_first_time = findViewById(R.id.goal5_enter_for_first_time);
        int goal1;
        int goal2;
        int goal3;
        int goal4;
        int goal5;
        if (goal1_enter_for_first_time.getText().toString().length() > 0) {
            goal1 = Integer.parseInt(goal1_enter_for_first_time.getText().toString());
        } else {
            goal1 = 0;
        }
        if (goal2_enter_for_first_time.getText().toString().length() > 0) {
            goal2 = Integer.parseInt(goal2_enter_for_first_time.getText().toString());
        } else {
            goal2 = 0;
        }
        if (goal3_enter_for_first_time.getText().toString().length() > 0) {
            goal3 = Integer.parseInt(goal3_enter_for_first_time.getText().toString());
        } else {
            goal3 = 0;
        }
        if (goal4_enter_for_first_time.getText().toString().length() > 0) {
            goal4 = Integer.parseInt(goal4_enter_for_first_time.getText().toString());
        } else {
            goal4 = 0;
        }
        if (goal5_enter_for_first_time.getText().toString().length() > 0) {
            goal5 = Integer.parseInt(goal5_enter_for_first_time.getText().toString());
        } else {
            goal5 = 0;
        }
        if (goal1 != 0 && goal2 != 0 && goal3 != 0 && goal4 != 0 && goal5 != 0 && goal1 < 10000 && goal2 < 10000 && goal3 < 10000 && goal4 < 10000 && goal5 < 10000) {
            return true;
        } else {
            if (goal1 == 0) {
                goal1_enter_for_first_time.setError("Goal cant be set to zero");
            }
            if (goal2 == 0) {
                goal2_enter_for_first_time.setError("Goal cant be set to zero");
            }
            if (goal3 == 0) {
                goal3_enter_for_first_time.setError("Goal cant be set to zero");
            }
            if (goal4 == 0) {
                goal4_enter_for_first_time.setError("Goal cant be set to zero");
            }
            if (goal5 == 0) {
                goal5_enter_for_first_time.setError("Goal cant be set to zero");
            }
            if (goal1 > 10000) {
                goal1_enter_for_first_time.setError("Goal cant be greater than 10000");
            }
            if (goal2 > 10000) {
                goal2_enter_for_first_time.setError("Goal cant be greater than 10000");
            }
            if (goal3 > 10000) {
                goal3_enter_for_first_time.setError("Goal cant be greater than 10000");
            }
            if (goal4 > 10000) {
                goal4_enter_for_first_time.setError("Goal cant be greater than 10000");
            }
            if (goal5 > 10000) {
                goal5_enter_for_first_time.setError("Goal cant be greater than 10000");
            }
            if (goal1 == 0 || goal2 == 0 || goal3 == 0 || goal4 == 0 || goal5 == 0) {
                Toast toast = Toast.makeText(getApplicationContext(), "Goals can't be zero", Toast.LENGTH_LONG);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Goals can't be greater than 10000 days", Toast.LENGTH_LONG);
                toast.show();
            }
            return false;
        }
    }

    private boolean check_if_streak_is_bigger() {
        EditText goal1_enter_for_first_time = findViewById(R.id.goal1_enter_for_first_time);
        EditText goal2_enter_for_first_time = findViewById(R.id.goal2_enter_for_first_time);
        EditText goal3_enter_for_first_time = findViewById(R.id.goal3_enter_for_first_time);
        EditText goal4_enter_for_first_time = findViewById(R.id.goal4_enter_for_first_time);
        EditText goal5_enter_for_first_time = findViewById(R.id.goal5_enter_for_first_time);
        int goal1;
        int goal2;
        int goal3;
        int goal4;
        int goal5;
        if (goal1_enter_for_first_time.getText().toString().length() > 0) {
            goal1 = Integer.parseInt(goal1_enter_for_first_time.getText().toString());
        } else {
            goal1 = 0;
        }
        if (goal2_enter_for_first_time.getText().toString().length() > 0) {
            goal2 = Integer.parseInt(goal2_enter_for_first_time.getText().toString());
        } else {
            goal2 = 0;
        }
        if (goal3_enter_for_first_time.getText().toString().length() > 0) {
            goal3 = Integer.parseInt(goal3_enter_for_first_time.getText().toString());
        } else {
            goal3 = 0;
        }
        if (goal4_enter_for_first_time.getText().toString().length() > 0) {
            goal4 = Integer.parseInt(goal4_enter_for_first_time.getText().toString());
        } else {
            goal4 = 0;
        }
        if (goal5_enter_for_first_time.getText().toString().length() > 0) {
            goal5 = Integer.parseInt(goal5_enter_for_first_time.getText().toString());
        } else {
            goal5 = 0;
        }
        long time_left_in_days = return_the_streak();
        Log.w("google",String.valueOf(return_the_streak()));
        if (time_left_in_days < goal1 && time_left_in_days < goal2 && time_left_in_days < goal3 && time_left_in_days < goal4 && time_left_in_days < goal5) {
            return true;
        } else {
            if (goal1 == time_left_in_days || goal2 == time_left_in_days || goal3 == time_left_in_days || goal4 == time_left_in_days || goal5 == time_left_in_days) {
                if (goal1 == time_left_in_days) {
                    goal1_enter_for_first_time.setError("Goal cant be equal to the current streak. Please increase it");
                }
                if (goal2 == time_left_in_days) {
                    goal2_enter_for_first_time.setError("Goal cant be equal to the current streak. Please increase it");
                }
                if (goal3 == time_left_in_days) {
                    goal3_enter_for_first_time.setError("Goal cant be equal to the current streak. Please increase it");
                }
                if (goal4 == time_left_in_days) {
                    goal4_enter_for_first_time.setError("Goal cant be equal to the current streak. Please increase it");
                }
                if (goal5 == time_left_in_days) {
                    goal5_enter_for_first_time.setError("Goal cant be equal to the current streak. Please increase it");
                }
            } else {
                if (goal1 < time_left_in_days) {
                    goal1_enter_for_first_time.setError("Goal cant be less than the current streak. Please increase it");
                }
                if (goal2 < time_left_in_days) {
                    goal2_enter_for_first_time.setError("Goal cant be less than the current streak. Please increase it");
                }
                if (goal3 < time_left_in_days) {
                    goal3_enter_for_first_time.setError("Goal cant be less than the current streak. Please increase it");
                }
                if (goal4 < time_left_in_days) {
                    goal4_enter_for_first_time.setError("Goal cant be less than the current streak. Please increase it");
                }
                if (goal5 < time_left_in_days) {
                    goal5_enter_for_first_time.setError("Goal cant be less than the current streak. Please increase it");
                }
            }
            return false;
        }
    }

    private void button_listen() {
        Button submit_data_first_time_button = findViewById(R.id.submit_data_first_time_button);
        submit_data_first_time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_is_pressed();
            }
        });
    }

    private void button_is_pressed() {
        if (check_the_name()) {
            if (check_if_date_is_chosen()) {
                if (check_the_goal_int()) {
                    if (check_the_goals()) {
                        if (check_if_streak_is_bigger()) {
                            SharedPreferences sharedPreferences = getSharedPreferences("main_streak", MODE_PRIVATE);
                            SharedPreferences.Editor my_edit =  sharedPreferences.edit();
                            SharedPreferences sharedPreferences_for_habit = getSharedPreferences("all_the_bad_habits", MODE_PRIVATE);
                            String bad_habits = sharedPreferences_for_habit.getString("bad_habits", "");
                            String[] split_bad_habits = bad_habits.split("##splitting_for_bad_habits##");
                            String[] small_split = split_bad_habits[0].split("__small_split__");
                            String save_me = "first_time_split_here_first_time_split_here_first_time_split_here_first_time_split_here_first_time_split_here_".concat(String.valueOf(sharedPreferences.getLong("time_in_milli", 0))).concat("_split_max_here_");
                            my_edit.putString(small_split[0],save_me);
                            my_edit.apply();
                            save_the_goals();
                            save_the_logged_in();
                            save_the_journal();
                            Intent intent = new Intent(MainActivity.this, after_login.class);
                            finish();
                            startActivity(intent);
                            overridePendingTransition(R.anim.swipe_right,R.anim.swipe_out_left);
                        }
                    }
                }
            }
        }
    }

    private void delete_the_shared() {
        SharedPreferences sharedPreferences = getSharedPreferences("main_name", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.remove("name");
        myEdit.apply();
        SharedPreferences streak_shared = getSharedPreferences("main_streak", MODE_PRIVATE);
        SharedPreferences.Editor myEdit_shared = streak_shared.edit();
        myEdit_shared.remove("time_in_milli");
        myEdit_shared.apply();
    }
    public void save_the_goals(){
        EditText goal1_enter_for_first_time = findViewById(R.id.goal1_enter_for_first_time);
        EditText goal2_enter_for_first_time = findViewById(R.id.goal2_enter_for_first_time);
        EditText goal3_enter_for_first_time = findViewById(R.id.goal3_enter_for_first_time);
        EditText goal4_enter_for_first_time = findViewById(R.id.goal4_enter_for_first_time);
        EditText goal5_enter_for_first_time = findViewById(R.id.goal5_enter_for_first_time);
        int goal1;
        int goal2;
        int goal3;
        int goal4;
        int goal5;
        if (goal1_enter_for_first_time.getText().toString().length() > 0) {
            goal1 = Integer.parseInt(goal1_enter_for_first_time.getText().toString());
        } else {
            goal1 = 0;
        }
        if (goal2_enter_for_first_time.getText().toString().length() > 0) {
            goal2 = Integer.parseInt(goal2_enter_for_first_time.getText().toString());
        } else {
            goal2 = 0;
        }
        if (goal3_enter_for_first_time.getText().toString().length() > 0) {
            goal3 = Integer.parseInt(goal3_enter_for_first_time.getText().toString());
        } else {
            goal3 = 0;
        }
        if (goal4_enter_for_first_time.getText().toString().length() > 0) {
            goal4 = Integer.parseInt(goal4_enter_for_first_time.getText().toString());
        } else {
            goal4 = 0;
        }
        if (goal5_enter_for_first_time.getText().toString().length() > 0) {
            goal5 = Integer.parseInt(goal5_enter_for_first_time.getText().toString());
        } else {
            goal5 = 0;
        }
        ArrayList<Integer> goal_list = new ArrayList<>();
        goal_list.add(goal1);
        goal_list.add(goal2);
        goal_list.add(goal3);
        goal_list.add(goal4);
        goal_list.add(goal5);
        Collections.sort(goal_list);
        String final_goal="";
        for(int i=0;i<goal_list.size();i++) {
            if (i == goal_list.size()-1) {
                final_goal = final_goal.concat(String.valueOf(goal_list.get(i)));
            } else {
                final_goal = final_goal.concat(String.valueOf(goal_list.get(i))).concat("_");
            }
        }
        SharedPreferences shared = getSharedPreferences("main_goals",MODE_PRIVATE);
        SharedPreferences.Editor edit = shared.edit();
        edit.putString(return_save_name(),final_goal);
        edit.apply();
    }
    private void save_the_logged_in(){
        SharedPreferences shared = getSharedPreferences("logged_in_or_no",MODE_PRIVATE);
        SharedPreferences.Editor edit = shared.edit();
        edit.putBoolean("logged",true);
        edit.apply();
    }
    private int return_the_streak(){
        EditText enter_streak_edittext = findViewById(R.id.enter_streak_edittext);
        if(enter_streak_edittext.getText().toString().length()>0){
            Log.w("time_milli",enter_streak_edittext.getText().toString());
            return Integer.parseInt(enter_streak_edittext.getText().toString());
        } else{
            SharedPreferences shared = getSharedPreferences("main_streak", MODE_PRIVATE);
            long milli = shared.getLong("time_in_milli",0);
            Log.w("time_milli",String.valueOf(milli));
            return (int) TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis() - milli);
        }
    }
    private void back_button_listen_to_the_previous(){
        Button button_for_back_in_main = findViewById(R.id.button_for_back_in_main);
        button_for_back_in_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Second_screen_to_choose_emergency.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("classFrom", "three");
                startActivity(intent);
            }
        });
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.swipe_left,R.anim.swipe_out_right);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.swipe_right,R.anim.swipe_out_left);
    }
    private void save_the_journal(){
        SharedPreferences shared = getSharedPreferences("_all_the_journal", MODE_PRIVATE);
        SharedPreferences.Editor edit = shared.edit();
        edit.putString("journal", "My Journal".concat("!!@@##%%&&split_small!!@@##%%&&").concat("!!@@##%%&&split_small!!@@##%%&&").concat("@#$$#@i_am_the_last_edit_for_journal@#$$#@").concat("##&&&&max_divide&&&&##"));
        edit.apply();
    }
    private void test(){
        SharedPreferences shared = getSharedPreferences("Counter", MODE_PRIVATE);
        SharedPreferences.Editor edit = shared.edit();
        edit.putString("counter", "__##empty_title##__".concat("##split_small_split_small##").concat("0").concat("__split_counter_split_counter__"));
        edit.putInt("which_one",0);
        edit.apply();
    }
    private String return_save_name(){
        SharedPreferences sharedPreferences_for_bad_habits = getSharedPreferences("all_the_bad_habits", MODE_PRIVATE);
        String bad_habits = sharedPreferences_for_bad_habits.getString("bad_habits", "");
        if(bad_habits!=null) {
            String[] splitter = bad_habits.split("__small_split__");
            return splitter[0];
        } else {
            return "";
        }
    }
}