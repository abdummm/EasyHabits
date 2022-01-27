package com.easyhabitsapp.android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.github.lzyzsd.circleprogress.ArcProgress;

import java.util.concurrent.TimeUnit;

public class Cold_shower_activity extends AppCompatActivity {
    private Handler text_view_handler = new Handler();
    private Handler sound_handler = new Handler();
    private boolean isInFront = false;
    private int delay_time = 1000;
    private MediaPlayer mediaPlayer;
    private int delay_for_sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_cold_shower);
        update_the_screen();
        hide_top_bar();
        on_button_press_listen();
        text_watching();
        back_button_listen();
        start_button_listen();
        check_box_listener();
        SharedPreferences sharedPreferences = getSharedPreferences("timer_is_paused", MODE_PRIVATE);
        if (what_is_time_left() > 0 && !sharedPreferences.getBoolean("paused", false)) {
            apply_the_check_list();
            start_the_count_down();
        } else if (what_is_time_left() > 0 && sharedPreferences.getBoolean("paused", false)) {
            update_the_text();
            hide_the_edit_box();
            set_up_the_buttons();
        }
        stop_and_pause();
    }

    private void hide_top_bar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    private int return_the_time() {
        EditText editText_to_enter_min_for_shower = findViewById(R.id.editText_to_enter_min_for_shower);
        EditText editText_to_enter_sec_for_shower = findViewById(R.id.editText_to_enter_sec_for_shower);
        int minutes;
        int seconds;
        if (editText_to_enter_min_for_shower.getText().toString().equals("")) {
            minutes = 0;
        } else {
            minutes = Integer.parseInt(editText_to_enter_min_for_shower.getText().toString());
        }
        if (editText_to_enter_sec_for_shower.getText().toString().equals("")) {
            seconds = 0;
        } else {
            seconds = Integer.parseInt(editText_to_enter_sec_for_shower.getText().toString());
        }
        return ((minutes * 60) + seconds);
    }

    private void set_the_text_for_time(int seconds) {
        EditText editText_to_enter_min_for_shower = findViewById(R.id.editText_to_enter_min_for_shower);
        EditText editText_to_enter_sec_for_shower = findViewById(R.id.editText_to_enter_sec_for_shower);
        int minutes = seconds / 60;
        int seconds_left = seconds - (minutes * 60);
        editText_to_enter_min_for_shower.setText(String.valueOf(minutes));
        editText_to_enter_min_for_shower.setSelection(editText_to_enter_min_for_shower.getText().toString().length());
        editText_to_enter_sec_for_shower.setText(String.valueOf(seconds_left));
        editText_to_enter_sec_for_shower.setSelection(editText_to_enter_sec_for_shower.getText().toString().length());
    }

    private void on_button_press_listen() {
        final EditText editText_to_enter_min_for_shower = findViewById(R.id.editText_to_enter_min_for_shower);
        final EditText editText_to_enter_sec_for_shower = findViewById(R.id.editText_to_enter_sec_for_shower);
        final Button stop_timer_for_shower = findViewById(R.id.stop_timer_for_shower);
        final Button pause_timer_for_cold_shower = findViewById(R.id.pause_timer_for_cold_shower);
        Button five_mins_add_to_cold_shower = findViewById(R.id.five_mins_add_to_cold_shower);
        Button one_mins_add_to_cold_shower = findViewById(R.id.one_mins_add_to_cold_shower);
        Button fifteen_sec_add_to_cold_shower = findViewById(R.id.fifteen_sec_add_to_cold_shower);
        five_mins_add_to_cold_shower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stop_timer_for_shower.getVisibility() == View.INVISIBLE) {
                    editText_to_enter_min_for_shower.clearFocus();
                    editText_to_enter_sec_for_shower.clearFocus();
                    if ((return_the_time() + 300) >= 3600) {
                        set_the_text_for_time(3600);
                    } else {
                        set_the_text_for_time((return_the_time() + 300));
                    }
                    update_the_text_view();
                } else {
                    if (pause_timer_for_cold_shower.getText().equals("Pause timer")) {
                        SharedPreferences sharedPreferences_for_time = getSharedPreferences("time_for_cold_shower_start", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit_for_time = sharedPreferences_for_time.edit();
                        myEdit_for_time.putLong("time", (using_text_view_return_the_time() * 1000) + System.currentTimeMillis() + 300000);
                        myEdit_for_time.apply();
                        update_the_text();
                    } else {
                        update_time_when_paused(300);
                    }
                }
            }
        });
        one_mins_add_to_cold_shower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stop_timer_for_shower.getVisibility() == View.INVISIBLE) {
                    editText_to_enter_min_for_shower.clearFocus();
                    editText_to_enter_sec_for_shower.clearFocus();
                    if ((return_the_time() + 60) >= 3600) {
                        set_the_text_for_time(3600);
                    } else {
                        set_the_text_for_time((return_the_time() + 60));
                    }
                    update_the_text_view();
                } else {
                    if (pause_timer_for_cold_shower.getText().equals("Pause timer")) {
                        SharedPreferences sharedPreferences_for_time = getSharedPreferences("time_for_cold_shower_start", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit_for_time = sharedPreferences_for_time.edit();
                        myEdit_for_time.putLong("time", (using_text_view_return_the_time() * 1000) + System.currentTimeMillis() + 60000);
                        myEdit_for_time.apply();
                        update_the_text();
                    } else {
                        update_time_when_paused(60);
                    }
                }
            }
        });
        fifteen_sec_add_to_cold_shower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stop_timer_for_shower.getVisibility() == View.INVISIBLE) {
                    editText_to_enter_min_for_shower.clearFocus();
                    editText_to_enter_sec_for_shower.clearFocus();
                    if ((return_the_time() + 15) >= 3600) {
                        set_the_text_for_time(3600);
                    } else {
                        set_the_text_for_time((return_the_time() + 15));
                    }
                    update_the_text_view();
                } else {
                    if (pause_timer_for_cold_shower.getText().equals("Pause timer")) {
                        SharedPreferences sharedPreferences_for_time = getSharedPreferences("time_for_cold_shower_start", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit_for_time = sharedPreferences_for_time.edit();
                        myEdit_for_time.putLong("time", (using_text_view_return_the_time() * 1000) + System.currentTimeMillis() + 15000);
                        myEdit_for_time.apply();
                        update_the_text();
                    } else {
                        update_time_when_paused(15);
                    }
                }
            }
        });
    }

    private void text_watching() {
        final EditText editText_to_enter_min_for_shower = findViewById(R.id.editText_to_enter_min_for_shower);
        final EditText editText_to_enter_sec_for_shower = findViewById(R.id.editText_to_enter_sec_for_shower);
        editText_to_enter_min_for_shower.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText_to_enter_min_for_shower.hasFocus()) {
                    if (!editText_to_enter_min_for_shower.getText().toString().equals("") && Integer.parseInt(editText_to_enter_min_for_shower.getText().toString()) > 60) {
                        editText_to_enter_min_for_shower.clearFocus();
                        editText_to_enter_min_for_shower.setText("60");
                        editText_to_enter_min_for_shower.setSelection(editText_to_enter_min_for_shower.getText().toString().length());
                        editText_to_enter_min_for_shower.requestFocus();
                    }
                    if (editText_to_enter_min_for_shower.getText().toString().equals("60")) {
                        EditText editText_to_enter_sec_for_shower = findViewById(R.id.editText_to_enter_sec_for_shower);
                        editText_to_enter_sec_for_shower.clearFocus();
                        editText_to_enter_sec_for_shower.setText("0");
                        editText_to_enter_sec_for_shower.setSelection(editText_to_enter_sec_for_shower.getText().toString().length());
                    }
                    update_the_text_view();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editText_to_enter_sec_for_shower.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText_to_enter_sec_for_shower.hasFocus()) {
                    if (!editText_to_enter_min_for_shower.getText().toString().equals("60")) {
                        if (!editText_to_enter_sec_for_shower.getText().toString().equals("") && Integer.parseInt(editText_to_enter_sec_for_shower.getText().toString()) > 60) {
                            editText_to_enter_sec_for_shower.clearFocus();
                            editText_to_enter_sec_for_shower.setText("60");
                            editText_to_enter_sec_for_shower.setSelection(editText_to_enter_sec_for_shower.getText().toString().length());
                            editText_to_enter_sec_for_shower.requestFocus();
                        }
                    } else {
                        editText_to_enter_sec_for_shower.clearFocus();
                        editText_to_enter_sec_for_shower.setText("0");
                        editText_to_enter_sec_for_shower.setSelection(editText_to_enter_sec_for_shower.getText().toString().length());
                        editText_to_enter_sec_for_shower.requestFocus();
                    }
                    update_the_text_view();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void back_button_listen() {
        Button button_for_back_to_emergency = findViewById(R.id.button_for_back_to_emergency);
        button_for_back_to_emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cold_shower_activity.this, after_login.class);
                intent.putExtra("Start_the_emergency_true", true);
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void update_the_text_view() {
        EditText editText_to_enter_min_for_shower = findViewById(R.id.editText_to_enter_min_for_shower);
        EditText editText_to_enter_sec_for_shower = findViewById(R.id.editText_to_enter_sec_for_shower);
        TextView minutes_left_in_shower = findViewById(R.id.minutes_left_in_shower);
        TextView seconds_left_in_shower = findViewById(R.id.seconds_left_in_shower);
        int minutes;
        int seconds;
        if (editText_to_enter_min_for_shower.getText().toString().equals("")) {
            minutes = 0;
        } else {
            minutes = Integer.parseInt(editText_to_enter_min_for_shower.getText().toString());
        }
        if (editText_to_enter_sec_for_shower.getText().toString().equals("")) {
            seconds = 0;
        } else {
            seconds = Integer.parseInt(editText_to_enter_sec_for_shower.getText().toString());
        }
        if (minutes >= 10) {
            minutes_left_in_shower.setText(String.valueOf(minutes));
        } else {
            minutes_left_in_shower.setText("0".concat(String.valueOf(minutes)));
        }
        if (seconds >= 10) {
            seconds_left_in_shower.setText(String.valueOf(seconds));
        } else {
            seconds_left_in_shower.setText("0".concat(String.valueOf(seconds)));
        }
    }

    private void start_button_listen() {
        Button Starting_timer_for_cold_shower_button = findViewById(R.id.Starting_timer_for_cold_shower_button);
        final TextView minutes_left_in_shower = findViewById(R.id.minutes_left_in_shower);
        final TextView seconds_left_in_shower = findViewById(R.id.seconds_left_in_shower);
        Starting_timer_for_cold_shower_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (minutes_left_in_shower.getText().toString().equals("00") && seconds_left_in_shower.getText().toString().equals("00")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Time cant be zero", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    CheckBox check_box_for_ringing = findViewById(R.id.check_box_for_ringing);
                    start_the_count_down();
                    SharedPreferences sharedPreferences_for_time = getSharedPreferences("time_for_cold_shower_start", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit_for_time = sharedPreferences_for_time.edit();
                    myEdit_for_time.putLong("time", System.currentTimeMillis() + (return_the_time() * 1000));
                    myEdit_for_time.apply();
                    update_the_text_handler();
                    delay_for_sound = return_the_time() * 1000;
                    play_the_sound();
                    SharedPreferences sharedPreferences_for_pause = getSharedPreferences("timer_is_paused", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit_for_pause = sharedPreferences_for_pause.edit();
                    myEdit_for_pause.putBoolean("paused", false);
                    myEdit_for_pause.apply();
                }
            }
        });
    }

    private void start_the_count_down() {
        hide_the_edit();
        set_up_the_buttons();
    }

    private void check_box_listener() {
        CheckBox check_box_for_ringing = findViewById(R.id.check_box_for_ringing);
        check_box_for_ringing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences sharedPreferences = getSharedPreferences("cold_shower_check", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putBoolean("check_box", b);
                myEdit.apply();
            }
        });
    }

    private void hide_the_edit() {
        EditText editText_to_enter_min_for_shower = findViewById(R.id.editText_to_enter_min_for_shower);
        TextView colon_between_two_times_clock = findViewById(R.id.colon_between_two_times_clock);
        EditText editText_to_enter_sec_for_shower = findViewById(R.id.editText_to_enter_sec_for_shower);
        editText_to_enter_min_for_shower.setVisibility(View.INVISIBLE);
        colon_between_two_times_clock.setVisibility(View.INVISIBLE);
        editText_to_enter_sec_for_shower.setVisibility(View.INVISIBLE);
    }

    private void apply_the_check_list() {
        CheckBox check_box_for_ringing = findViewById(R.id.check_box_for_ringing);
        SharedPreferences sharedPreferences = getSharedPreferences("cold_shower_check", MODE_PRIVATE);
        check_box_for_ringing.setChecked(sharedPreferences.getBoolean("check_box", false));
    }

    private void set_up_the_buttons() {
        Button Starting_timer_for_cold_shower_button = findViewById(R.id.Starting_timer_for_cold_shower_button);
        Starting_timer_for_cold_shower_button.setVisibility(View.INVISIBLE);
        Button stop_timer_for_shower = findViewById(R.id.stop_timer_for_shower);
        Button pause_timer_for_cold_shower = findViewById(R.id.pause_timer_for_cold_shower);
        stop_timer_for_shower.setVisibility(View.VISIBLE);
        pause_timer_for_cold_shower.setVisibility(View.VISIBLE);
    }

    private long what_is_time_left() {
        SharedPreferences sharedPreferences_for_time = getSharedPreferences("time_for_cold_shower_start", MODE_PRIVATE);

        return (sharedPreferences_for_time.getLong("time", -1) - System.currentTimeMillis());
    }

    private void update_the_text_handler() {
        final Button stop_timer_for_shower = findViewById(R.id.stop_timer_for_shower);
        text_view_handler.postDelayed(new Runnable() {
            public void run() {
                update_the_time_delay();
                update_the_text();
                check_if_done();
                if (isInFront && (stop_timer_for_shower.getVisibility() == View.VISIBLE)) {
                    text_view_handler.postDelayed(this, delay_time);
                }
            }
        }, delay_time);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isInFront = true;
        SharedPreferences sharedPreferences = getSharedPreferences("timer_is_paused", MODE_PRIVATE);
        if (!sharedPreferences.getBoolean("paused", false)) {
            update_the_text_handler();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        isInFront = false;
        //text_view_handler.removeCallbacksAndMessages(null);
    }

    private void update_the_time_delay() {
        long time_left_in_minute = TimeUnit.MILLISECONDS.toMinutes(what_is_time_left());
        if (time_left_in_minute >= 10) {
            delay_time = 1000;
        } else if (time_left_in_minute >= 1) {
            delay_time = 100;
        } else {
            delay_time = 10;
        }
    }

    private void update_the_text() {
        TextView minutes_left_in_shower = findViewById(R.id.minutes_left_in_shower);
        TextView seconds_left_in_shower = findViewById(R.id.seconds_left_in_shower);
        Log.w("shower", String.valueOf(what_is_time_left()));
        if (what_is_time_left() > 0) {
            int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(what_is_time_left());
            int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(what_is_time_left()) - minutes * 60;
            if (minutes >= 10) {
                minutes_left_in_shower.setText(String.valueOf(minutes));
            } else {
                minutes_left_in_shower.setText("0".concat(String.valueOf(minutes)));
            }
            if (seconds >= 10) {
                seconds_left_in_shower.setText(String.valueOf(seconds));
            } else {
                seconds_left_in_shower.setText("0".concat(String.valueOf(seconds)));
            }
        }
    }

    private void play_the_sound() {
        sound_handler.postDelayed(new Runnable() {
            public void run() {
                CheckBox check_box_for_ringing = findViewById(R.id.check_box_for_ringing);
                if (check_box_for_ringing.isChecked()) {
                    start_the_sound();
                    stop_the_sound_handler();
                }
            }
        }, delay_for_sound);
    }

    private void check_if_done() {
        CheckBox check_box_for_ringing = findViewById(R.id.check_box_for_ringing);
        Button stop_timer_for_shower = findViewById(R.id.stop_timer_for_shower);
        if (what_is_time_left() < 0 && (stop_timer_for_shower.getVisibility() == View.VISIBLE) && !check_box_for_ringing.isChecked()) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            overridePendingTransition(0, 0);
        }
    }

    private void start_the_sound() {
        Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alert == null) {
            // alert is null, using backup
            alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);

            // I can't see this ever being null (as always have a default notification)
            // but just incase
            if (alert == null) {
                // alert backup is null, using 2nd backup
                alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            }
        }
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (audioManager != null) {
            if (audioManager.isMusicActive()) {
                audioManager.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            }
        }
        mediaPlayer = MediaPlayer.create(getApplicationContext(), alert);
        mediaPlayer.start();
        TextView minutes_left_in_shower = findViewById(R.id.minutes_left_in_shower);
        TextView seconds_left_in_shower = findViewById(R.id.seconds_left_in_shower);
        minutes_left_in_shower.setText("00");
        seconds_left_in_shower.setText("00");
    }

    private void stop_the_sound_handler() {
        sound_handler.postDelayed(new Runnable() {
            public void run() {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        }, 15000);
    }

    private void stop_and_pause() {
        final Button stop_timer_for_shower = findViewById(R.id.stop_timer_for_shower);
        final Button pause_timer_for_cold_shower = findViewById(R.id.pause_timer_for_cold_shower);
        final Button Starting_timer_for_cold_shower_button = findViewById(R.id.Starting_timer_for_cold_shower_button);
        final ArcProgress timer_progress_for_shower_count_down = findViewById(R.id.timer_progress_for_shower_count_down);
        stop_timer_for_shower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_view_handler.removeCallbacksAndMessages(null);
                sound_handler.removeCallbacksAndMessages(null);
                stop_timer_for_shower.setVisibility(View.INVISIBLE);
                pause_timer_for_cold_shower.setVisibility(View.INVISIBLE);
                Starting_timer_for_cold_shower_button.setVisibility(View.VISIBLE);
                timer_progress_for_shower_count_down.setProgress(10000);
                TextView minutes_left_in_shower = findViewById(R.id.minutes_left_in_shower);
                TextView seconds_left_in_shower = findViewById(R.id.seconds_left_in_shower);
                minutes_left_in_shower.setText("00");
                seconds_left_in_shower.setText("00");
                SharedPreferences sharedPreferences_for_time = getSharedPreferences("time_for_cold_shower_start", MODE_PRIVATE);
                SharedPreferences.Editor myEdit_for_time = sharedPreferences_for_time.edit();
                myEdit_for_time.remove("time");
                myEdit_for_time.apply();
                EditText editText_to_enter_min_for_shower = findViewById(R.id.editText_to_enter_min_for_shower);
                EditText editText_to_enter_sec_for_shower = findViewById(R.id.editText_to_enter_sec_for_shower);
                TextView colon_between_two_times_clock = findViewById(R.id.colon_between_two_times_clock);
                editText_to_enter_min_for_shower.setVisibility(View.VISIBLE);
                editText_to_enter_sec_for_shower.setVisibility(View.VISIBLE);
                colon_between_two_times_clock.setVisibility(View.VISIBLE);
                editText_to_enter_min_for_shower.getText().clear();
                editText_to_enter_sec_for_shower.getText().clear();
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                CheckBox check_box_for_ringing = findViewById(R.id.check_box_for_ringing);
                check_box_for_ringing.setChecked(false);
                if(pause_timer_for_cold_shower.getText().equals("Resume timer")){
                    pause_timer_for_cold_shower.setText("Pause timer");
                    SharedPreferences sharedPreferences = getSharedPreferences("timer_is_paused", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putBoolean("paused", false);
                    myEdit.apply();
                }
            }
        });
        pause_timer_for_cold_shower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pause_timer_for_cold_shower.getText().equals("Pause timer")) {
                    pause_timer_for_cold_shower.setText("Resume timer");
                    SharedPreferences sharedPreferences = getSharedPreferences("timer_is_paused", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putBoolean("paused", true);
                    myEdit.apply();
                    text_view_handler.removeCallbacksAndMessages(null);
                    sound_handler.removeCallbacksAndMessages(null);
                } else {
                    pause_timer_for_cold_shower.setText("Pause timer");
                    SharedPreferences sharedPreferences = getSharedPreferences("timer_is_paused", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putBoolean("paused", false);
                    myEdit.apply();
                    continue_the_time();
                }
            }
        });
    }

    private int using_text_view_return_the_time() {
        TextView seconds_left_in_shower = findViewById(R.id.seconds_left_in_shower);
        TextView minutes_left_in_shower = findViewById(R.id.minutes_left_in_shower);
        int minutes;
        int seconds;
        if (minutes_left_in_shower.getText().toString().equals("")) {
            minutes = 0;
        } else {
            minutes = Integer.parseInt(minutes_left_in_shower.getText().toString());
        }
        if (seconds_left_in_shower.getText().toString().equals("")) {
            seconds = 0;
        } else {
            seconds = Integer.parseInt(seconds_left_in_shower.getText().toString());
        }
        return seconds + (minutes * 60);
    }

    private void hide_the_edit_box() {
        EditText editText_to_enter_min_for_shower = findViewById(R.id.editText_to_enter_min_for_shower);
        EditText editText_to_enter_sec_for_shower = findViewById(R.id.editText_to_enter_sec_for_shower);
        TextView colon_between_two_times_clock = findViewById(R.id.colon_between_two_times_clock);
        editText_to_enter_min_for_shower.setVisibility(View.INVISIBLE);
        editText_to_enter_sec_for_shower.setVisibility(View.INVISIBLE);
        colon_between_two_times_clock.setVisibility(View.INVISIBLE);
    }

    private void update_time_when_paused(int how_much) {
        TextView seconds_left_in_shower = findViewById(R.id.seconds_left_in_shower);
        TextView minutes_left_in_shower = findViewById(R.id.minutes_left_in_shower);
        int new_time = using_text_view_return_the_time() + how_much;
        if (new_time >= 3600) {
            new_time = 3600;
        }
        int minutes = new_time / 60;
        int seconds = new_time - (minutes * 60);
        if (minutes >= 10) {
            minutes_left_in_shower.setText(String.valueOf(minutes));
        } else {
            minutes_left_in_shower.setText("0".concat(String.valueOf(minutes)));
        }
        if (seconds >= 10) {
            seconds_left_in_shower.setText(String.valueOf(seconds));
        } else {
            seconds_left_in_shower.setText("0".concat(String.valueOf(seconds)));
        }
    }

    private void continue_the_time() {
        SharedPreferences sharedPreferences_for_time = getSharedPreferences("time_for_cold_shower_start", MODE_PRIVATE);
        SharedPreferences.Editor myEdit_for_time = sharedPreferences_for_time.edit();
        myEdit_for_time.putLong("time", System.currentTimeMillis() + (using_text_view_return_the_time() * 1000));
        myEdit_for_time.apply();
        update_the_text_handler();
        delay_for_sound = using_text_view_return_the_time() * 1000;
        play_the_sound();
    }

    private void update_the_arc() {

    }

    private void update_the_screen(){
        final ConstraintLayout constraintLayout = findViewById(R.id.cold_shower_layout);
        final ViewTreeObserver vto = constraintLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ArcProgress timer_progress_for_shower_count_down = findViewById(R.id.timer_progress_for_shower_count_down);
                EditText editText_to_enter_min_for_shower = findViewById(R.id.editText_to_enter_min_for_shower);
                Button one_mins_add_to_cold_shower = findViewById(R.id.one_mins_add_to_cold_shower);
                CheckBox check_box_for_ringing = findViewById(R.id.check_box_for_ringing);
                Button Starting_timer_for_cold_shower_button = findViewById(R.id.Starting_timer_for_cold_shower_button);
                Button button_for_back_to_emergency = findViewById(R.id.button_for_back_to_emergency);
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                //float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
                float dpHeight = displayMetrics.heightPixels;
                //float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
                float other_height = dip_to_pixels(15) + dip_to_pixels(40) + dip_to_pixels(300) + dip_to_pixels(15)+(editText_to_enter_min_for_shower.getMeasuredHeight()) +dip_to_pixels(25)+ (one_mins_add_to_cold_shower.getMeasuredHeight()) +dip_to_pixels(20)+ (check_box_for_ringing.getMeasuredHeight())+ dip_to_pixels(25) + dip_to_pixels(1) +dip_to_pixels(10) + (Starting_timer_for_cold_shower_button.getMeasuredHeight())+(get_status_height())+dip_to_pixels(5);
                float margin = dpHeight-other_height;
                if(margin<=((dip_to_pixels(16))+check_box_for_ringing.getMeasuredHeight())){
                    margin = dip_to_pixels(16) + check_box_for_ringing.getMeasuredHeight();
                }
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.clear(timer_progress_for_shower_count_down.getId(), ConstraintSet.TOP);
                constraintSet.connect(timer_progress_for_shower_count_down.getId(), ConstraintSet.TOP, button_for_back_to_emergency.getId(), ConstraintSet.BOTTOM, (int) margin);
                constraintSet.applyTo(constraintLayout);
                Log.w("hacker_hacker",String.valueOf(dpFromPx(Starting_timer_for_cold_shower_button.getMeasuredHeight())));
                constraintLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private float dpFromPx(final float px) {
        return px / getResources().getDisplayMetrics().density;
    }
    private int get_status_height(){
        Rect rectangle = new Rect();
        Window window = getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        return rectangle.top;
    }
    private int dip_to_pixels(float dip) {
        Resources r = getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
    }
}
