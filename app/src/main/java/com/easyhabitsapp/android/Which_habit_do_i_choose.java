package com.easyhabitsapp.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;

public class Which_habit_do_i_choose extends AppCompatActivity implements Dialog_asking_which_habit.dialog_choosing_habit_listen_cancel {
    String which_spinner_is_chosen = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_which_habit_do_i_choose);
        set_up_which_button_to_choose();
        hide_top_bar();
        set_the_color_spinner();
        button_listen_next();
    }

    private void set_up_which_button_to_choose() {
        Button submit_data_first_time_button = findViewById(R.id.submit_data_first_time_button);
        submit_data_first_time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog_asking_which_habit dialog_asking_which_habit = new Dialog_asking_which_habit();
                dialog_asking_which_habit.show(getSupportFragmentManager(), "tag");
            }
        });
    }

    private void hide_top_bar() {
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().hide();
        }
    }

    /*@Override
    public void onDismiss(DialogInterface dialogInterface) {
        Button submit_data_first_time_button = findViewById(R.id.submit_data_first_time_button);
        EditText edit_text_showing_habit_name = findViewById(R.id.edit_text_showing_habit_name);
        TextView text_asking_to_choose_a_color = findViewById(R.id.text_asking_to_choose_a_color);
        SharedPreferences sh = getSharedPreferences("all_the_bad_habits", MODE_PRIVATE);
        String after_the_read = sh.getString("bad_habits", "");
        if (edit_text_showing_habit_name.getVisibility() == View.INVISIBLE) {
            ConstraintLayout constraintLayout = findViewById(R.id.activity_called_the_first);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(text_asking_to_choose_a_color.getId(), ConstraintSet.TOP, edit_text_showing_habit_name.getId(), ConstraintSet.BOTTOM, dip_to_pixels(30));
            constraintSet.applyTo(constraintLayout);
            edit_text_showing_habit_name.setVisibility(View.VISIBLE);
            edit_text_showing_habit_name.setText(after_the_read);
            edit_text_showing_habit_name.setSelection(edit_text_showing_habit_name.getText().toString().length());
        } else {
            edit_text_showing_habit_name.setText(after_the_read);
            edit_text_showing_habit_name.setSelection(edit_text_showing_habit_name.getText().toString().length());
        }
        if (submit_data_first_time_button.getText().toString().equals("Choose a habit")) {
            submit_data_first_time_button.setText("Change the habit");
        }
        which_spinner_is_chosen = after_the_read;
    }*/

    private int dip_to_pixels(float dip) {
        Resources r = getResources();
        float pixels = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );
        return Math.round(pixels);
    }

    private void set_the_color_spinner() {
        ArrayList<Color_item> mcolor_item;
        mcolor_item = new ArrayList<>();
        mcolor_item.add(new Color_item("Teal", R.drawable.z_dark_green));
        mcolor_item.add(new Color_item("Red", R.drawable.z_red));
        mcolor_item.add(new Color_item("Green", R.drawable.z_green));
        mcolor_item.add(new Color_item("Yellow", R.drawable.z_yellow));
        mcolor_item.add(new Color_item("Blue", R.drawable.z_blue));
        mcolor_item.add(new Color_item("Orange", R.drawable.z_orange));
        mcolor_item.add(new Color_item("Purple", R.drawable.z_purple));
        mcolor_item.add(new Color_item("Cyan", R.drawable.z_cyan));
        mcolor_item.add(new Color_item("Magenta", R.drawable.z_magenta));
        mcolor_item.add(new Color_item("Lime", R.drawable.z_lime));
        mcolor_item.add(new Color_item("Pink", R.drawable.z_pink));
        mcolor_item.add(new Color_item("Brown", R.drawable.z_brown));
        mcolor_item.add(new Color_item("Navy", R.drawable.z_navy));
        mcolor_item.add(new Color_item("Black", R.drawable.z_black));
        Color_adapter m_adapter;
        Spinner spinner_to_ask_which_color = findViewById(R.id.spinner_to_ask_which_color);
        m_adapter = new Color_adapter(this, mcolor_item);
        spinner_to_ask_which_color.setAdapter(m_adapter);
        spinner_to_ask_which_color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void button_listen_next() {
        SharedPreferences sharedPreferences = getSharedPreferences("all_the_bad_habits", MODE_PRIVATE);
        final String bad_habits = sharedPreferences.getString("bad_habits", "");
        Button next_button_to_go_for_next_screen = findViewById(R.id.next_button_to_go_for_next_screen);
        final Button submit_data_first_time_button = findViewById(R.id.submit_data_first_time_button);
        final EditText edit_text_showing_habit_name = findViewById(R.id.edit_text_showing_habit_name);
        next_button_to_go_for_next_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (submit_data_first_time_button.getText().toString().equals("Choose a habit")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please choose a habit", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (edit_text_showing_habit_name.getText().toString().trim().equals("")) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Habit name can't be empty", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        if (bad_habits !=null && !bad_habits.contains(edit_text_showing_habit_name.getText().toString().trim())) {
                            save_the_info();
                        } else if (bad_habits ==null){
                            save_the_info();
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "A habit with that name already exists, please cahnge the habit or the habit name", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                }
            }
        });
    }

    private void save_the_info() {
        SharedPreferences sharedPreferences_for_bad_habits = getSharedPreferences("all_the_bad_habits", MODE_PRIVATE);
        String bad_habits = sharedPreferences_for_bad_habits.getString("bad_habits", "");
        EditText edit_text_showing_habit_name = findViewById(R.id.edit_text_showing_habit_name);
        String save_me = edit_text_showing_habit_name.getText().toString().concat("__small_split__").concat(which_spinner_is_chosen).concat("__small_split__").concat(return_the_color_name()).concat("##splitting_for_bad_habits##");
        SharedPreferences sharedPreferences = getSharedPreferences("all_the_bad_habits", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("bad_habits", save_me);
        myEdit.commit();
        Intent intent = new Intent(Which_habit_do_i_choose.this, Second_screen_to_choose_emergency.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("classFrom", "first");
        startActivity(intent);
    }

    private String return_the_color_name() {
        Spinner spinner_to_ask_which_color = findViewById(R.id.spinner_to_ask_which_color);
        int item_position = spinner_to_ask_which_color.getSelectedItemPosition();
        if (item_position == 0) {
            return "Teal";
        } else if (item_position == 1) {
            return "Red";
        } else if (item_position == 2) {
            return "Green";
        } else if (item_position == 3) {
            return "Yellow";
        } else if (item_position == 4) {
            return "Blue";
        } else if (item_position == 5) {
            return "Orange";
        } else if (item_position == 6) {
            return "Purple";
        } else if (item_position == 7) {
            return "Cyan";
        } else if (item_position == 8) {
            return "Magenta";
        } else if (item_position == 9) {
            return "Lime";
        } else if (item_position == 10) {
            return "Pink";
        } else if (item_position == 11) {
            return "Brown";
        } else if (item_position == 12) {
            return "Navy";
        } else {
            return "Black";
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        overridePendingTransition(R.anim.swipe_left, R.anim.swipe_out_right);
    }

    @Override
    public void on_habit_choose(String text) {
        Button submit_data_first_time_button = findViewById(R.id.submit_data_first_time_button);
        EditText edit_text_showing_habit_name = findViewById(R.id.edit_text_showing_habit_name);
        TextView text_asking_to_choose_a_color = findViewById(R.id.text_asking_to_choose_a_color);
        if (edit_text_showing_habit_name.getVisibility() == View.INVISIBLE) {
            ConstraintLayout constraintLayout = findViewById(R.id.activity_called_the_first);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(text_asking_to_choose_a_color.getId(), ConstraintSet.TOP, edit_text_showing_habit_name.getId(), ConstraintSet.BOTTOM, dip_to_pixels(30));
            constraintSet.applyTo(constraintLayout);
            edit_text_showing_habit_name.setVisibility(View.VISIBLE);
            edit_text_showing_habit_name.setText(text);
            edit_text_showing_habit_name.setSelection(edit_text_showing_habit_name.getText().toString().length());
        } else {
            edit_text_showing_habit_name.setText(text);
            edit_text_showing_habit_name.setSelection(edit_text_showing_habit_name.getText().toString().length());
        }
        if (submit_data_first_time_button.getText().toString().equals("Choose a habit")) {
            submit_data_first_time_button.setText("Change the habit");
        }
        which_spinner_is_chosen = text;
    }
}