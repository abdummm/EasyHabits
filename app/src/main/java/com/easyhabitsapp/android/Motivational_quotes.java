/*
package com.easyhabitsapp.android;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Motivational_quotes extends AppCompatActivity {
    private int current_int;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivational_quotes);
        hide_top_bar();
        load_in_the_first_quote();
        back_and_forward_listen();
        back_button_listen();
        copy_button_listen();
        share_button_listen();
    }

    private void load_in_the_first_quote(){
        TextView quotes_in_motivate_button = findViewById(R.id.quotes_in_motivate_button);
        SharedPreferences sharedPreferences = getSharedPreferences("Motivation_quotes_emergency", MODE_PRIVATE);
        current_int = sharedPreferences.getInt("motivation",0);
        String[] split_into_quotes = read_text_line().split("::split_on_me_::");
        if(current_int<split_into_quotes.length){
            quotes_in_motivate_button.setText(split_into_quotes[current_int]);
        }
    }
    private String read_text_line(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("motivation_in_button.txt")));
            // do reading, usually loop until end of file reading
            String mLine;
            if ((mLine = reader.readLine()) != null) {
                return mLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Error cant get a quote";
    }
    private void back_and_forward_listen(){
        final TextView quotes_in_motivate_button = findViewById(R.id.quotes_in_motivate_button);
        Button back_button_ios_in_motivation = findViewById(R.id.back_button_ios_in_motivation);
        Button forward_button_ios_in_motivation = findViewById(R.id.forward_button_ios_in_motivation);
        back_button_ios_in_motivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] split_into_quotes = read_text_line().split("::split_on_me_::");
                if(current_int<=0){
                    current_int = split_into_quotes.length-1;
                    quotes_in_motivate_button.setText(split_into_quotes[current_int].trim());
                } else {
                    current_int = current_int -1;
                    quotes_in_motivate_button.setText(split_into_quotes[current_int].trim());
                }
            }
        });
        forward_button_ios_in_motivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] split_into_quotes = read_text_line().split("::split_on_me_::");
                if(current_int>=split_into_quotes.length-1){
                    current_int = 0;
                    quotes_in_motivate_button.setText(split_into_quotes[current_int].trim());
                } else {
                    current_int = current_int +1;
                    quotes_in_motivate_button.setText(split_into_quotes[current_int].trim());
                }
            }
        });
    }
    private void hide_top_bar() {
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().hide();
        }
    }
    private void back_button_listen(){
        Button button_to_go_back_counter = findViewById(R.id.button_to_go_back_counter);
        button_to_go_back_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Motivational_quotes.this, after_login.class);
                intent.putExtra("Start_the_emergency_true", true);
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }
    private void copy_button_listen(){
        Button button_to_copy_motivation = findViewById(R.id.button_to_copy_motivation);
        final TextView quotes_in_motivate_button = findViewById(R.id.quotes_in_motivate_button);
        button_to_copy_motivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label",quotes_in_motivate_button.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast toast = Toast.makeText(getApplicationContext(), "copied", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
    private void share_button_listen(){
        Button button_to_share_motivation = findViewById(R.id.button_to_share_motivation);
        final TextView quotes_in_motivate_button = findViewById(R.id.quotes_in_motivate_button);
        button_to_share_motivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                String shareBody = quotes_in_motivate_button.getText().toString(); // dont forget to add name of app
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Motivational Quotes");
                intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intent, "Motivational Quotes"));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        save_the_number();
    }
    private void save_the_number(){
        SharedPreferences sharedPreferences = getSharedPreferences("Motivation_quotes_emergency", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putInt("motivation", current_int);
        myEdit.commit();
    }
}*/
