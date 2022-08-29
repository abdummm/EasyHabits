package com.easyhabitsapp.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Before_main_activity extends AppCompatActivity {
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_main__activity);
        hide_top_bar();
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            sign_in_anonymous();
        } else {
            delay();
        }
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

    private void sign_in_anonymous(){
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            FirebaseAuth.getInstance().signInAnonymously()
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                checking_where_to_go();
                                finish();
                            } else {
                                checking_where_to_go();
                                finish();
                                Toast.makeText(getApplicationContext(), "Can't sign in. Please try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
