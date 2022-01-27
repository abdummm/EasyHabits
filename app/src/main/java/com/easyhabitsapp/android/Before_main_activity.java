package com.easyhabitsapp.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class Before_main_activity extends AppCompatActivity {
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_main__activity);
        hide_top_bar();
        delay();
    }
    private void checking_where_to_go(){
        ((First_time_running) getApplicationContext()).update_restart(false);
        Intent intent = new Intent(Before_main_activity.this, after_login.class);
        startActivity(intent);
    }
    private void delay(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checking_where_to_go();
                finish();
                handler.removeCallbacksAndMessages(null);
            }
        }, 500);
    }
    private void hide_top_bar() {
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().hide();
        }
    }
}
