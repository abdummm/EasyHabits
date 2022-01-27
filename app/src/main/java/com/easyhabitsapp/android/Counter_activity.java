package com.easyhabitsapp.android;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

public class Counter_activity extends AppCompatActivity implements Bottom_sheet_counter.BottomSheetListener_for_counter {

    private boolean am_i_in_fragment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_activity);
        check_if_empty();
        hide_top_bar();
        open_the_data_and_set_it();
        listening_to_all_buttons();
    }

    private void listening_to_all_buttons() {
        Button reset_counter_in_counter = findViewById(R.id.reset_counter_in_counter);
        Button button_to_go_back_counter = findViewById(R.id.button_to_go_back_counter);
        Button button_to_add_three_dots = findViewById(R.id.button_to_add_three_dots);
        final Button button_to_press_on_add_counter = findViewById(R.id.button_to_press_on_add_counter);
        final TextView text_showing_number_of_counter_in_circle = findViewById(R.id.text_showing_number_of_counter_in_circle);
        final EditText name_of_the_counter = findViewById(R.id.name_of_the_counter);
        button_to_go_back_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Counter_activity.this, after_login.class);
                intent.putExtra("Start_the_emergency_true", true);
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        button_to_press_on_add_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int old_count = Integer.parseInt(text_showing_number_of_counter_in_circle.getText().toString());
                text_showing_number_of_counter_in_circle.setText(String.valueOf(old_count + 1));
                View keyboard_view = getCurrentFocus();
                if (keyboard_view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(keyboard_view.getWindowToken(), 0);
                }
                name_of_the_counter.clearFocus();
            }
        });
        reset_counter_in_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_showing_number_of_counter_in_circle.setText("0");
                reset_counter();
            }
        });
        button_to_add_three_dots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bottom_sheet_counter bottom_sheet_counter = new Bottom_sheet_counter();
                bottom_sheet_counter.show(getSupportFragmentManager(), get_the_tag());
            }
        });
    }

    private void open_the_data_and_set_it() {
        EditText name_of_the_counter = findViewById(R.id.name_of_the_counter);
        TextView text_showing_number_of_counter_in_circle = findViewById(R.id.text_showing_number_of_counter_in_circle);
        SharedPreferences sharedPreferences = getSharedPreferences("Counter", MODE_PRIVATE);
        String string = sharedPreferences.getString("counter", "");
        int which_one = sharedPreferences.getInt("which_one", 0);
        if (string != null) {
            String[] big_data = string.split("__split_counter_split_counter__");
            String[] small_data = big_data[which_one].split("##split_small_split_small##");
            if (small_data[0].equals("__##empty_title##__")) {
                name_of_the_counter.setText("");
            } else {
                name_of_the_counter.setText(small_data[0]);
            }
            text_showing_number_of_counter_in_circle.setText(String.valueOf(small_data[1]));
        }
    }

    private void reset_counter() {
        SharedPreferences sharedPreferences = getSharedPreferences("Counter", MODE_PRIVATE);
        String string = sharedPreferences.getString("counter", "");
        int which_one = sharedPreferences.getInt("which_one", 0);
        if (string != null) {
            String[] big_data = string.split("__split_counter_split_counter__");
            String[] small_data = big_data[which_one].split("##split_small_split_small##");
            String new_counter_entry = small_data[0].concat("##split_small_split_small##").concat(String.valueOf(0));
            String save_me = "";
            for (int i = 0; i < big_data.length; i++) {
                if (which_one != i) {
                    save_me = save_me.concat(big_data[i]).concat("__split_counter_split_counter__");
                } else {
                    save_me = save_me.concat(new_counter_entry).concat("__split_counter_split_counter__");
                }
            }
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("counter", save_me);
            myEdit.commit();
        }
    }

    private void save_the_counter() {
        TextView text_showing_number_of_counter_in_circle = findViewById(R.id.text_showing_number_of_counter_in_circle);
        EditText name_of_the_counter = findViewById(R.id.name_of_the_counter);
        String title;
        String counter = text_showing_number_of_counter_in_circle.getText().toString();
        if (name_of_the_counter.getText().toString().equals("")) {
            title = "__##empty_title##__";
        } else {
            title = name_of_the_counter.getText().toString();
        }
        String title_and_counter = title.concat("##split_small_split_small##").concat(counter);
        SharedPreferences sharedPreferences = getSharedPreferences("Counter", MODE_PRIVATE);
        String string = sharedPreferences.getString("counter", "");
        int which_one = sharedPreferences.getInt("which_one", 0);
        if (string != null) {
            String save_me = "";
            String[] big_data = string.split("__split_counter_split_counter__");
            for (int i = 0; i < big_data.length; i++) {
                if (i != which_one) {
                    save_me = save_me.concat(big_data[i]).concat("__split_counter_split_counter__");
                } else {
                    save_me = save_me.concat(title_and_counter).concat("__split_counter_split_counter__");
                }
            }
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("counter", save_me);
            myEdit.commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        save_the_counter();
    }

    private void hide_top_bar() {
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().hide();
        }
    }

    private String get_the_tag() {
        SharedPreferences sharedPreferences = getSharedPreferences("Counter", MODE_PRIVATE);
        String string = sharedPreferences.getString("counter", "");
        if (string != null) {
            String[] split_big = string.split("__split_counter_split_counter__");
            if (split_big.length > 1) {
                return "more_than_one";
            } else {
                return "only_one";
            }
        }
        return "only_one";
    }

    @Override
    public void on_button_choose_counter(String text) {
        if (text.equals("add_counter")) {
            save_the_counter();
            add_another_counter();
            open_the_data_and_set_it();
        } else if (text.equals("delete_counter")) {
            delete_counter();
            open_the_data_and_set_it();
        } else if (text.equals("switch_counter")) {
            save_the_counter();
            switch_counter();
        }
    }

    private void add_another_counter() {
        Am_i_paid am_i_paid = new Am_i_paid(this);
        if (am_i_paid.did_user_pay()) {
            String new_counter = "Counter".concat("##split_small_split_small##").concat("0").concat("__split_counter_split_counter__");
            SharedPreferences sharedPreferences = getSharedPreferences("Counter", MODE_PRIVATE);
            String string = sharedPreferences.getString("counter", "");
            if (string != null) {
                String[] big_split = string.split("__split_counter_split_counter__");
                int which_one_to_save = big_split.length;
                String save_final = string.concat(new_counter);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("counter", save_final);
                myEdit.putInt("which_one", which_one_to_save);
                myEdit.commit();
            }
        } else {
            Buy_premuim buy_premuim = new Buy_premuim("add more than 1 counter", true, "activity");
            hide_activity();
            buy_premuim.set_the_function(new Buy_premuim.show_the_activity() {
                @Override
                public void show_the_activity_function() {
                    show_activity();
                }
            });
            getSupportFragmentManager().beginTransaction().add(R.id.hold_buy_premuim_fragment_container_in_counter, buy_premuim, "buy premium").show(buy_premuim).commit();
        }
    }

    private void delete_counter() {
        SharedPreferences sharedPreferences = getSharedPreferences("Counter", MODE_PRIVATE);
        String string = sharedPreferences.getString("counter", "");
        int which_one = sharedPreferences.getInt("which_one", 0);
        if (string != null) {
            String[] big_split = string.split("__split_counter_split_counter__");
            String save_me = "";
            for (int i = 0; i < big_split.length; i++) {
                if (i != which_one) {
                    save_me = save_me.concat(big_split[i]).concat("__split_counter_split_counter__");
                }
            }
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("counter", save_me);
            myEdit.putInt("which_one", big_split.length - 2);
            myEdit.commit();
        }
    }

    private void switch_counter() {
        final Dialog_to_show_counter_switch dialog_to_show_counter_switch = new Dialog_to_show_counter_switch();
        dialog_to_show_counter_switch.show(getSupportFragmentManager(), "switch");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.executePendingTransactions();
        if (dialog_to_show_counter_switch.getDialog() != null) {
            dialog_to_show_counter_switch.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    open_the_data_and_set_it();
                    getSupportFragmentManager().beginTransaction().remove(dialog_to_show_counter_switch).commitAllowingStateLoss();
                }
            });
        }
    }

    private void check_if_empty() {
        SharedPreferences shared = getSharedPreferences("Counter", MODE_PRIVATE);
        SharedPreferences.Editor edit = shared.edit();
        if (shared.getString("counter", "").equals("")) {
            edit.putString("counter", "Counter".concat("##split_small_split_small##").concat("0").concat("__split_counter_split_counter__"));
            edit.putInt("which_one", 0);
            edit.commit();
        }
    }

    private void hide_activity(){
        am_i_in_fragment = true;
        ConstraintLayout cosntraint_layout_covering_the_counter_activity = findViewById(R.id.cosntraint_layout_covering_the_counter_activity);
        cosntraint_layout_covering_the_counter_activity.setVisibility(View.INVISIBLE);
    }

    private void show_activity(){
        am_i_in_fragment = false;
        ConstraintLayout cosntraint_layout_covering_the_counter_activity = findViewById(R.id.cosntraint_layout_covering_the_counter_activity);
        cosntraint_layout_covering_the_counter_activity.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(am_i_in_fragment){
            show_activity();
            remove_the_buy_premuim();
        } else {
            this.finish();
        }
    }

    private void remove_the_buy_premuim(){
        Buy_premuim old_fragment = (Buy_premuim) getSupportFragmentManager().findFragmentByTag("buy premium");
        if (old_fragment != null) {
            getSupportFragmentManager().beginTransaction().remove(old_fragment).commit();
        }
    }
}