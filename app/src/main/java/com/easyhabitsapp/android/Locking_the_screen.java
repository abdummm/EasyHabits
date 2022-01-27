package com.easyhabitsapp.android;

import android.app.AppOpsManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.github.lzyzsd.circleprogress.ArcProgress;

import java.util.concurrent.TimeUnit;

public class Locking_the_screen extends AppCompatActivity implements DialogInterface.OnDismissListener {
    private boolean isInFront = true;
    private Handler handler = new Handler();
    private Handler handler_for_sound = new Handler();
    private MediaPlayer mediaPlayer;
    private Handler handler_to_flash = new Handler();
    private int delay_for_arc = 1000;
    private Handler handler_to_update_arc = new Handler();
    static boolean is_the_lock_screen_active = true;
    private boolean is_fragment_visible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler.removeCallbacksAndMessages(null);
        handler_for_sound.removeCallbacksAndMessages(null);
        handler_to_flash.removeCallbacksAndMessages(null);
        handler_to_update_arc.removeCallbacksAndMessages(null);
        setContentView(R.layout.layout_lock_the_screen);
        hide_top_bar();
        back_button_for_emergency();
        text_watching_for_timer();
        button_listener_to_add_time();
        progress_bar_time();
        declaring_the_shared_false();
        open_whitelist();
        start_the_countdown_button();
        check_if_usage_is_allowed();
        show_dialog_to_ask_to_draw_over_screen();
        if (!Get_the_time.open_the_file(this).equals("00:00")) {
            make_the_stuff_go_for_time_mode();
            call_the_method_every_second();
            set_the_text_for_activity();
            check_the_state();
        }
        clear_edit_text();
        set_one_minute_emergency();
        set_the_stop_button();
        check_box_listen();
    }

    private void hide_top_bar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    private void back_button_for_emergency() {
        Button button_for_back_to_emergency = findViewById(R.id.button_for_back_to_emergency);
        button_for_back_to_emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Locking_the_screen.this, after_login.class);
                intent.putExtra("Start_the_emergency_true", true);
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void text_watching_for_timer() {
        EditText edit_text_for_hours_under_progress = findViewById(R.id.edit_text_for_hours_under_progress);
        EditText edit_text_for_minutes_under_progress = findViewById(R.id.edit_text_for_minutes_under_progress);
        edit_text_for_hours_under_progress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                EditText edit_text_for_hours_under_progress = findViewById(R.id.edit_text_for_hours_under_progress);
                SeekBar seekBar_for_time_lock = findViewById(R.id.seekBar_for_time_lock);
                TextView minutes_besides_edit_text_above_seekbar = findViewById(R.id.minutes_besides_edit_text_above_seekbar);
                if (edit_text_for_hours_under_progress.hasFocus()) {
                    change_text_for_hours(edit_text_for_hours_under_progress.getText().toString());
                    seekBar_for_time_lock.setProgress(0);
                    minutes_besides_edit_text_above_seekbar.setText("0 minutes");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_text_for_minutes_under_progress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                EditText edit_text_for_minutes_under_progress = findViewById(R.id.edit_text_for_minutes_under_progress);
                SeekBar seekBar_for_time_lock = findViewById(R.id.seekBar_for_time_lock);
                TextView minutes_besides_edit_text_above_seekbar = findViewById(R.id.minutes_besides_edit_text_above_seekbar);
                if (edit_text_for_minutes_under_progress.hasFocus()) {
                    change_text_for_minutes(edit_text_for_minutes_under_progress.getText().toString());
                    seekBar_for_time_lock.setProgress(0);
                    minutes_besides_edit_text_above_seekbar.setText("0 minutes");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void change_text_for_hours(String hours) {
        EditText edit_text_for_hours_under_progress = findViewById(R.id.edit_text_for_hours_under_progress);
        EditText edit_text_for_minutes_under_progress = findViewById(R.id.edit_text_for_minutes_under_progress);
        TextView lock_hours_to_end = findViewById(R.id.lock_hours_to_end);
        TextView lock_minutes_to_end = findViewById(R.id.lock_minutes_to_end);
        int hours_as_int;
        if (!hours.equals("")) {
            hours_as_int = Integer.parseInt(hours);
        } else {
            hours_as_int = 0;
        }
        if (hours_as_int >= 3) {
            edit_text_for_hours_under_progress.clearFocus();
            edit_text_for_hours_under_progress.setText("3");
            edit_text_for_hours_under_progress.setSelection(edit_text_for_hours_under_progress.getText().toString().length());
            edit_text_for_hours_under_progress.requestFocus();
            lock_hours_to_end.setText("03");
            edit_text_for_minutes_under_progress.clearFocus();
            edit_text_for_minutes_under_progress.setText("00");
            lock_minutes_to_end.setText("00");
            edit_text_for_minutes_under_progress.setSelection(edit_text_for_minutes_under_progress.getText().toString().length());
            if (hours_as_int > 3) {
                Toast toast = Toast.makeText(getApplicationContext(), "can't be greater than 3 hours", Toast.LENGTH_SHORT);
                toast.show();
            }
        } else {
            String set_text = "0";
            set_text = set_text.concat(String.valueOf(hours_as_int));
            lock_hours_to_end.setText(set_text);
        }
    }

    private void change_text_for_minutes(String minutes) {
        EditText edit_text_for_minutes_under_progress = findViewById(R.id.edit_text_for_minutes_under_progress);
        EditText edit_text_for_hours_under_progress = findViewById(R.id.edit_text_for_hours_under_progress);
        TextView lock_minutes_to_end = findViewById(R.id.lock_minutes_to_end);
        int minutes_as_int;
        if (!minutes.equals("")) {
            minutes_as_int = Integer.parseInt(minutes);
        } else {
            minutes_as_int = 0;
        }
        if (!edit_text_for_hours_under_progress.getText().toString().equals("") && Integer.parseInt(edit_text_for_hours_under_progress.getText().toString()) == 3) {
            edit_text_for_minutes_under_progress.clearFocus();
            edit_text_for_minutes_under_progress.setText("00");
            lock_minutes_to_end.setText("00");
            edit_text_for_minutes_under_progress.setSelection(edit_text_for_minutes_under_progress.getText().toString().length());
            edit_text_for_minutes_under_progress.requestFocus();
            Toast toast = Toast.makeText(getApplicationContext(), "can't be greater than 3 hours", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            if (minutes_as_int > 60) {
                edit_text_for_minutes_under_progress.clearFocus();
                edit_text_for_minutes_under_progress.setText("60");
                lock_minutes_to_end.setText("60");
                edit_text_for_minutes_under_progress.setSelection(edit_text_for_minutes_under_progress.getText().toString().length());
                edit_text_for_minutes_under_progress.requestFocus();
                Toast toast = Toast.makeText(getApplicationContext(), "can't be greater than 60 minutes", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                if (minutes_as_int >= 10) {
                    lock_minutes_to_end.setText(String.valueOf(minutes_as_int));
                } else {
                    String set_text = "0";
                    set_text = set_text.concat(String.valueOf(minutes_as_int));
                    lock_minutes_to_end.setText(set_text);
                }
            }
        }
    }

    private void button_listener_to_add_time() {
        Button add_one_minutes_under_the_lock = findViewById(R.id.add_one_minutes_under_the_lock);
        Button add_five_minutes_under_the_lock = findViewById(R.id.add_five_minutes_under_the_lock);
        Button add_fifteen_minutes_under_the_lock = findViewById(R.id.add_fifteen_minutes_under_the_lock);
        add_one_minutes_under_the_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edit_text_for_minutes_under_progress = findViewById(R.id.edit_text_for_minutes_under_progress);
                EditText edit_text_for_hours_under_progress = findViewById(R.id.edit_text_for_hours_under_progress);
                TextView lock_hours_to_end = findViewById(R.id.lock_hours_to_end);
                TextView lock_minutes_to_end = findViewById(R.id.lock_minutes_to_end);
                SeekBar seekBar_for_time_lock = findViewById(R.id.seekBar_for_time_lock);
                TextView minutes_besides_edit_text_above_seekbar = findViewById(R.id.minutes_besides_edit_text_above_seekbar);
                seekBar_for_time_lock.setProgress(0);
                minutes_besides_edit_text_above_seekbar.setText("0 minutes");
                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (mgr != null) {
                    mgr.hideSoftInputFromWindow(edit_text_for_minutes_under_progress.getWindowToken(), 0);
                    mgr.hideSoftInputFromWindow(edit_text_for_hours_under_progress.getWindowToken(), 0);
                }
                edit_text_for_hours_under_progress.clearFocus();
                edit_text_for_minutes_under_progress.clearFocus();
                int total_minutes = return_the_time();
                if (return_the_time() == 180) {
                    Toast toast = Toast.makeText(getApplicationContext(), "can't be greater than 3 hours", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if ((total_minutes + 1) >= 180) {
                        lock_hours_to_end.setText("03");
                        lock_minutes_to_end.setText("00");
                        edit_text_for_hours_under_progress.setText("3");
                        edit_text_for_minutes_under_progress.setText("0");
                    } else {
                        int hours_for_show = (total_minutes + 1) / 60;
                        int minutes_for_show = (total_minutes + 1) % 60;
                        edit_text_for_hours_under_progress.setText(String.valueOf(hours_for_show));
                        lock_hours_to_end.setText("0".concat(String.valueOf(hours_for_show)));
                        edit_text_for_minutes_under_progress.setText(String.valueOf(minutes_for_show));
                        if (minutes_for_show <= 9) {
                            lock_minutes_to_end.setText("0".concat(String.valueOf(minutes_for_show)));
                        } else {
                            lock_minutes_to_end.setText(String.valueOf(minutes_for_show));
                        }
                    }
                }
            }
        });
        add_five_minutes_under_the_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edit_text_for_minutes_under_progress = findViewById(R.id.edit_text_for_minutes_under_progress);
                EditText edit_text_for_hours_under_progress = findViewById(R.id.edit_text_for_hours_under_progress);
                TextView lock_hours_to_end = findViewById(R.id.lock_hours_to_end);
                TextView lock_minutes_to_end = findViewById(R.id.lock_minutes_to_end);
                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                SeekBar seekBar_for_time_lock = findViewById(R.id.seekBar_for_time_lock);
                TextView minutes_besides_edit_text_above_seekbar = findViewById(R.id.minutes_besides_edit_text_above_seekbar);
                seekBar_for_time_lock.setProgress(0);
                minutes_besides_edit_text_above_seekbar.setText("0 minutes");
                if (mgr != null) {
                    mgr.hideSoftInputFromWindow(edit_text_for_minutes_under_progress.getWindowToken(), 0);
                    mgr.hideSoftInputFromWindow(edit_text_for_hours_under_progress.getWindowToken(), 0);
                }
                edit_text_for_hours_under_progress.clearFocus();
                edit_text_for_minutes_under_progress.clearFocus();
                int total_minutes = return_the_time();
                if (return_the_time() == 180) {
                    Toast toast = Toast.makeText(getApplicationContext(), "can't be greater than 3 hours", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if ((total_minutes + 5) >= 180) {
                        lock_hours_to_end.setText("03");
                        lock_minutes_to_end.setText("00");
                        edit_text_for_hours_under_progress.setText("3");
                        edit_text_for_minutes_under_progress.setText("0");
                    } else {
                        int hours_for_show = (total_minutes + 5) / 60;
                        int minutes_for_show = (total_minutes + 5) % 60;
                        edit_text_for_hours_under_progress.setText(String.valueOf(hours_for_show));
                        lock_hours_to_end.setText("0".concat(String.valueOf(hours_for_show)));
                        edit_text_for_minutes_under_progress.setText(String.valueOf(minutes_for_show));
                        if (minutes_for_show <= 9) {
                            lock_minutes_to_end.setText("0".concat(String.valueOf(minutes_for_show)));
                        } else {
                            lock_minutes_to_end.setText(String.valueOf(minutes_for_show));
                        }
                    }
                }
            }
        });
        add_fifteen_minutes_under_the_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edit_text_for_minutes_under_progress = findViewById(R.id.edit_text_for_minutes_under_progress);
                EditText edit_text_for_hours_under_progress = findViewById(R.id.edit_text_for_hours_under_progress);
                TextView lock_hours_to_end = findViewById(R.id.lock_hours_to_end);
                TextView lock_minutes_to_end = findViewById(R.id.lock_minutes_to_end);
                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                SeekBar seekBar_for_time_lock = findViewById(R.id.seekBar_for_time_lock);
                TextView minutes_besides_edit_text_above_seekbar = findViewById(R.id.minutes_besides_edit_text_above_seekbar);
                seekBar_for_time_lock.setProgress(0);
                minutes_besides_edit_text_above_seekbar.setText("0 minutes");
                if (mgr != null) {
                    mgr.hideSoftInputFromWindow(edit_text_for_minutes_under_progress.getWindowToken(), 0);
                    mgr.hideSoftInputFromWindow(edit_text_for_hours_under_progress.getWindowToken(), 0);
                }
                edit_text_for_hours_under_progress.clearFocus();
                edit_text_for_minutes_under_progress.clearFocus();
                int total_minutes = return_the_time();
                if (return_the_time() == 180) {
                    Toast toast = Toast.makeText(getApplicationContext(), "can't be greater than 3 hours", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if ((total_minutes + 15) >= 180) {
                        lock_hours_to_end.setText("03");
                        lock_minutes_to_end.setText("00");
                        edit_text_for_hours_under_progress.setText("3");
                        edit_text_for_minutes_under_progress.setText("0");
                    } else {
                        int hours_for_show = (total_minutes + 15) / 60;
                        int minutes_for_show = (total_minutes + 15) % 60;
                        edit_text_for_hours_under_progress.setText(String.valueOf(hours_for_show));
                        lock_hours_to_end.setText("0".concat(String.valueOf(hours_for_show)));
                        edit_text_for_minutes_under_progress.setText(String.valueOf(minutes_for_show));
                        if (minutes_for_show <= 9) {
                            lock_minutes_to_end.setText("0".concat(String.valueOf(minutes_for_show)));
                        } else {
                            lock_minutes_to_end.setText(String.valueOf(minutes_for_show));
                        }
                    }
                }
            }
        });
    }

    private int return_the_time() {
        EditText edit_text_for_minutes_under_progress = findViewById(R.id.edit_text_for_minutes_under_progress);
        EditText edit_text_for_hours_under_progress = findViewById(R.id.edit_text_for_hours_under_progress);
        int minutes;

        if (!edit_text_for_hours_under_progress.getText().toString().equals("")) {
            minutes = Integer.parseInt(edit_text_for_hours_under_progress.getText().toString()) * 60;
        } else {
            minutes = 0;
        }
        if (!edit_text_for_minutes_under_progress.getText().toString().equals("")) {
            minutes = minutes + Integer.parseInt(edit_text_for_minutes_under_progress.getText().toString());
        }
        return minutes;

    }

    private void progress_bar_time() {
        SeekBar seekBar_for_time_lock = findViewById(R.id.seekBar_for_time_lock);
        seekBar_for_time_lock.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                TextView minutes_besides_edit_text_above_seekbar = findViewById(R.id.minutes_besides_edit_text_above_seekbar);
                EditText edit_text_for_hours_under_progress = findViewById(R.id.edit_text_for_hours_under_progress);
                EditText edit_text_for_minutes_under_progress = findViewById(R.id.edit_text_for_minutes_under_progress);
                TextView lock_hours_to_end = findViewById(R.id.lock_hours_to_end);
                TextView lock_minutes_to_end = findViewById(R.id.lock_minutes_to_end);
                minutes_besides_edit_text_above_seekbar.setText(String.valueOf(i * 2).concat(" minutes"));
                if (i != 0) {
                    edit_text_for_hours_under_progress.clearFocus();
                    edit_text_for_minutes_under_progress.clearFocus();
                    edit_text_for_hours_under_progress.getText().clear();
                    edit_text_for_minutes_under_progress.getText().clear();
                    lock_hours_to_end.setText("00");
                    lock_minutes_to_end.setText("00");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void open_whitelist() {
        Button add_other_apps_to_white_list_lock_button = findViewById(R.id.add_other_apps_to_white_list_lock_button);
        add_other_apps_to_white_list_lock_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Am_i_paid am_i_paid = new Am_i_paid(Locking_the_screen.this);
                if(am_i_paid.did_user_pay()){
                    Dialog_for_white_list dialog_for_white_list = new Dialog_for_white_list();
                    dialog_for_white_list.show(getSupportFragmentManager(), "dialog_tag");
                } else {
                    Buy_premuim buy_premuim = new Buy_premuim("whitelist apps", true, "activity");
                    hide_activity();
                    buy_premuim.set_the_function(new Buy_premuim.show_the_activity() {
                        @Override
                        public void show_the_activity_function() {
                            show_activity();
                        }
                    });
                    getSupportFragmentManager().beginTransaction().add(R.id.hold_buy_premuim_fragment_container_in_lock_screen, buy_premuim, "buy premium").show(buy_premuim).commit();
                }
            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        Button add_other_apps_to_white_list_lock_button = findViewById(R.id.add_other_apps_to_white_list_lock_button);
        SharedPreferences prefs = getSharedPreferences("white_or_purple", MODE_PRIVATE);
        boolean color_it_or_no = prefs.getBoolean("color_it_white", false);
        if (color_it_or_no) {
            add_other_apps_to_white_list_lock_button.setTextColor(Color.WHITE);
            add_other_apps_to_white_list_lock_button.setBackgroundResource(R.drawable.color_for_botton_on);
        } else {
            add_other_apps_to_white_list_lock_button.setTextColor(Color.parseColor("#607D8B"));
            add_other_apps_to_white_list_lock_button.setBackgroundResource(R.drawable.color_for_botton_off);
        }
        declaring_the_shared_false();
        if (!checking_if_it_is_ok() || !check_screen_permission_allowed()) {
            Intent intent = new Intent(Locking_the_screen.this, after_login.class);
            intent.putExtra("Start_the_emergency_true", true);
            finish();
            startActivity(intent);
        }
    }

    private void declaring_the_shared_false() {
        SharedPreferences.Editor editor = getSharedPreferences("white_or_purple", MODE_PRIVATE).edit();
        editor.putBoolean("color_it_white", false);
        editor.apply();
    }

    private void start_the_countdown_button() {
        final CheckBox check_box_to_ring_when_lock_is_done = findViewById(R.id.check_box_to_ring_when_lock_is_done);
        Button turn_on_the_lock_button = findViewById(R.id.turn_on_the_lock_button);
        turn_on_the_lock_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView lock_hours_to_end = findViewById(R.id.lock_hours_to_end);
                TextView lock_minutes_to_end = findViewById(R.id.lock_minutes_to_end);
                TextView minutes_besides_edit_text_above_seekbar = findViewById(R.id.minutes_besides_edit_text_above_seekbar);
                if (checking_if_it_is_ok()) {
                    if (!lock_hours_to_end.getText().toString().equals("00") || !lock_minutes_to_end.getText().toString().equals("00") || !minutes_besides_edit_text_above_seekbar.getText().toString().equals("0 minutes")) {
                        make_the_stuff_go_for_time_mode();
                        send_the_time();
                        call_the_method_every_second();
                        create_notification();
                        start_service();
                        update_the_arc_hander();
                        SharedPreferences sharedPreferences = getSharedPreferences("check_box", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putBoolean("check_box_state", check_box_to_ring_when_lock_is_done.isChecked());
                        myEdit.apply();
                        SharedPreferences sharedPreferences1 = getSharedPreferences("one_minute_emergency", MODE_PRIVATE);
                        SharedPreferences.Editor myEditone = sharedPreferences1.edit();
                        myEditone.remove("one_minute_time");
                        myEditone.apply();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "time can't be zero", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    Dialog_to_tell_user dialog_to_telluser = new Dialog_to_tell_user();
                    dialog_to_telluser.show(getSupportFragmentManager(), "dialog_tag");
                }
            }
        });
    }

    private void make_the_stuff_go_for_time_mode() {
        EditText edit_text_for_hours_under_progress = findViewById(R.id.edit_text_for_hours_under_progress);
        EditText edit_text_for_minutes_under_progress = findViewById(R.id.edit_text_for_minutes_under_progress);
        TextView semi_colon_under_progress = findViewById(R.id.semi_colon_under_progress);
        View horizantal_line_behind_or_lock_screen = findViewById(R.id.horizantal_line_behind_or_lock_screen);
        TextView or_text_asking_between_buttons_and_progress_bar = findViewById(R.id.or_text_asking_between_buttons_and_progress_bar);
        TextView minutes_besides_edit_text_above_seekbar = findViewById(R.id.minutes_besides_edit_text_above_seekbar);
        SeekBar seekBar_for_time_lock = findViewById(R.id.seekBar_for_time_lock);
        Button add_other_apps_to_white_list_lock_button = findViewById(R.id.add_other_apps_to_white_list_lock_button);
        Button turn_on_the_lock_button = findViewById(R.id.turn_on_the_lock_button);
        Button add_fifteen_minutes_under_the_lock = findViewById(R.id.add_fifteen_minutes_under_the_lock);
        Button add_five_minutes_under_the_lock = findViewById(R.id.add_five_minutes_under_the_lock);
        Button add_one_minutes_under_the_lock = findViewById(R.id.add_one_minutes_under_the_lock);
        Button button_to_open_one_minute_emergency = findViewById(R.id.button_to_open_one_minute_emergency);
        TextView text_telling_user_to_choose_time_for_lock_down = findViewById(R.id.text_telling_user_to_choose_time_for_lock_down);
        edit_text_for_hours_under_progress.setVisibility(View.GONE);
        edit_text_for_minutes_under_progress.setVisibility(View.GONE);
        semi_colon_under_progress.setVisibility(View.GONE);
        horizantal_line_behind_or_lock_screen.setVisibility(View.GONE);
        or_text_asking_between_buttons_and_progress_bar.setVisibility(View.GONE);
        minutes_besides_edit_text_above_seekbar.setVisibility(View.GONE);
        seekBar_for_time_lock.setVisibility(View.GONE);
        add_other_apps_to_white_list_lock_button.setVisibility(View.GONE);
        turn_on_the_lock_button.setVisibility(View.GONE);
        add_one_minutes_under_the_lock.setVisibility(View.GONE);
        add_fifteen_minutes_under_the_lock.setVisibility(View.GONE);
        add_five_minutes_under_the_lock.setVisibility(View.GONE);
        text_telling_user_to_choose_time_for_lock_down.setVisibility(View.GONE);
        button_to_open_one_minute_emergency.setVisibility(View.VISIBLE);
        ConstraintSet constraintSet = new ConstraintSet();
        ConstraintLayout constraintLayout = findViewById(R.id.id_for_lock_screen_layout);
        constraintSet.clone(constraintLayout);
        constraintSet.connect(R.id.progress_for_lock_circle, ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, 0);
        constraintSet.connect(R.id.progress_for_lock_circle, ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM, 0);
        constraintSet.setVerticalBias(R.id.progress_for_lock_circle, 0.35f);
        constraintSet.connect(R.id.check_box_to_ring_when_lock_is_done, ConstraintSet.TOP, R.id.progress_for_lock_circle, ConstraintSet.BOTTOM, dip_to_pixels(20));
        constraintSet.connect(R.id.button_to_open_one_minute_emergency, ConstraintSet.TOP, R.id.check_box_to_ring_when_lock_is_done, ConstraintSet.BOTTOM, dip_to_pixels(20));
        constraintSet.applyTo(constraintLayout);
    }

    private int dip_to_pixels(float dip) {
        Resources r = getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
    }

    private boolean checking_if_it_is_ok() {
        AppOpsManager appOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
        boolean granted = false;
        if (appOps != null) {
            int mode = appOps.checkOpNoThrow("android:get_usage_stats", android.os.Process.myUid(), getPackageName());
            granted = mode == AppOpsManager.MODE_ALLOWED;
        }
        return granted;
    }

    private void check_if_usage_is_allowed() {
        if (!checking_if_it_is_ok()) {
            Dialog_to_tell_user dialog_to_telluser = new Dialog_to_tell_user();
            dialog_to_telluser.show(getSupportFragmentManager(), "dialog_tag");
        }
    }

    private void send_the_time() {
        TextView minutes_besides_edit_text_above_seekbar = findViewById(R.id.minutes_besides_edit_text_above_seekbar);
        SharedPreferences sharedPreferences = getSharedPreferences("future_time_in_milli_seconds", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        int add_me;
        if (minutes_besides_edit_text_above_seekbar.getText().toString().equals("0 minutes")) {
            add_me = return_the_time() * 60000;
        } else if (minutes_besides_edit_text_above_seekbar.getText().toString().contains(" minutes")) {
            add_me = Integer.parseInt(minutes_besides_edit_text_above_seekbar.getText().toString().replace(" minutes", "")) * 60000;
        } else {
            add_me = Integer.parseInt(minutes_besides_edit_text_above_seekbar.getText().toString().replace(" minute", "")) * 60000;
        }
        long current_time = System.currentTimeMillis() + add_me;
        myEdit.putLong("time", current_time);
        myEdit.putLong("time_on_create", add_me);
        myEdit.apply();
    }

    private void call_the_method_every_second() {
        final Button button_to_open_one_minute_emergency = findViewById(R.id.button_to_open_one_minute_emergency);
        final Button button_to_stop_sound = findViewById(R.id.button_to_stop_sound);
        handler.postDelayed(new Runnable() {
            public void run() {
                set_the_text_for_activity();
                check_if_done();
                check_the_one_minute_time();
                update_the_arc_timer();
                if (isInFront && (button_to_open_one_minute_emergency.getVisibility() == View.VISIBLE || button_to_stop_sound.getVisibility() == View.VISIBLE) && !Get_the_time.open_the_file(getApplicationContext()).equals("00:00")) {
                    handler.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }

    @Override
    public void onResume() {
        super.onResume();
        isInFront = true;
        Button button_to_open_one_minute_emergency = findViewById(R.id.button_to_open_one_minute_emergency);
        Button button_to_stop_sound = findViewById(R.id.button_to_stop_sound);
        if (button_to_open_one_minute_emergency.getVisibility() == View.VISIBLE) {
            update_the_arc_hander();
            call_the_method_every_second();
        }
        if (Get_the_time.open_the_file(this).equals("00:00") && (button_to_open_one_minute_emergency.getVisibility() == View.VISIBLE || button_to_stop_sound.getVisibility() == View.VISIBLE)) {
            flash_the_arc();
        }
        is_the_lock_screen_active = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isInFront = false;
        stop_the_sound();
        is_the_lock_screen_active = false;
        /*handler.removeCallbacksAndMessages(null);
        handler_for_sound.removeCallbacksAndMessages(null);
        handler_to_flash.removeCallbacksAndMessages(null);
        handler_to_update_arc.removeCallbacksAndMessages(null);*/
    }

    private void set_the_text_for_activity() {
        TextView lock_hours_to_end = findViewById(R.id.lock_hours_to_end);
        TextView lock_minutes_to_end = findViewById(R.id.lock_minutes_to_end);
        String not_ready = Get_the_time.open_the_file(this);
        String[] splitted = not_ready.split(":");
        lock_hours_to_end.setText(splitted[0]);
        lock_minutes_to_end.setText(splitted[1]);
    }

    private void create_notification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel("lock_phone_service_channel", "Phone is locked", NotificationManager.IMPORTANCE_HIGH);
            serviceChannel.setSound(null, null);
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(serviceChannel);
            }
        }
    }

    private void start_service() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(new Intent(Locking_the_screen.this, Lock_phone_service.class));
        } else {
            startService(new Intent(Locking_the_screen.this, Lock_phone_service.class));
        }
    }

    private void check_if_done() {
        CheckBox check_box_to_ring_when_lock_is_done = findViewById(R.id.check_box_to_ring_when_lock_is_done);
        Button button_to_open_one_minute_emergency = findViewById(R.id.button_to_open_one_minute_emergency);
        Button button_to_stop_sound = findViewById(R.id.button_to_stop_sound);
        if (Get_the_time.open_the_file(this).equals("00:00")) {
            if (!check_box_to_ring_when_lock_is_done.isChecked()) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
            } else {
                start_the_sound();
                play_sound_fifteen_seconds();
                flash_the_arc();
                check_box_to_ring_when_lock_is_done.setVisibility(View.GONE);
                button_to_open_one_minute_emergency.setVisibility(View.GONE);
                button_to_stop_sound.setVisibility(View.VISIBLE);
            }
        }
    }

    private void clear_edit_text() {
        EditText edit_text_for_hours_under_progress = findViewById(R.id.edit_text_for_hours_under_progress);
        EditText edit_text_for_minutes_under_progress = findViewById(R.id.edit_text_for_minutes_under_progress);
        edit_text_for_hours_under_progress.getText().clear();
        edit_text_for_minutes_under_progress.getText().clear();
    }

    private void set_one_minute_emergency() {
        Button button_to_open_one_minute_emergency = findViewById(R.id.button_to_open_one_minute_emergency);
        button_to_open_one_minute_emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button_to_open_one_minute_emergency = findViewById(R.id.button_to_open_one_minute_emergency);
                if (button_to_open_one_minute_emergency.getText().equals("1 minute emergency")) {
                    SharedPreferences sharedPreferences = getSharedPreferences("one_minute_emergency", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putLong("one_minute_time", System.currentTimeMillis() + 960000);
                    Toast toast = Toast.makeText(getApplicationContext(), "Phone unlocked for a minute", Toast.LENGTH_SHORT);
                    toast.show();

                    myEdit.apply();
                } else if (button_to_open_one_minute_emergency.getBackgroundTintList() == ColorStateList.valueOf(Color.parseColor("#808080"))) {
                    Toast toast = Toast.makeText(getApplicationContext(), "The emergency feature can only be used once every 15 minutes", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Phone is already unlocked", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    private void check_the_one_minute_time() {
        Button button_to_open_one_minute_emergency = findViewById(R.id.button_to_open_one_minute_emergency);
        SharedPreferences sharedPreferences = getSharedPreferences("one_minute_emergency", MODE_PRIVATE);
        long time_in_future = sharedPreferences.getLong("one_minute_time", -1);
        long time_left = time_in_future - System.currentTimeMillis();
        Log.w("hellohello",String.valueOf(time_in_future));
        if((TimeUnit.MILLISECONDS.toSeconds(time_left-900000))< Get_the_time.return_the_seconds(this)) {
            if (time_left > 900000) {
                button_to_open_one_minute_emergency.setText(String.valueOf(TimeUnit.MILLISECONDS.toSeconds(time_left - 900000)).concat(" seconds"));
            } else if (time_left < 900000 && time_left > 0) {
                int minutes_left = (int) TimeUnit.MILLISECONDS.toMinutes(time_left);
                int seconds_left = (int) (TimeUnit.MILLISECONDS.toSeconds(time_left) - (minutes_left * 60));
                button_to_open_one_minute_emergency.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
                button_to_open_one_minute_emergency.setText(String.valueOf(minutes_left).concat("m ").concat(String.valueOf(seconds_left)).concat("s "));
            } else {
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.remove("one_minute_time");
                myEdit.apply();
            }
        } else if(time_in_future!=-1) {
            button_to_open_one_minute_emergency.setText(String.valueOf(Get_the_time.return_the_seconds(this)).concat(" seconds"));
        }
    }


    private void play_sound_fifteen_seconds() {
        handler_for_sound.postDelayed(new Runnable() {
            public void run() {
                Button button_to_stop_sound = findViewById(R.id.button_to_stop_sound);
                if (button_to_stop_sound.getVisibility() == View.VISIBLE) {
                    stop_the_sound();
                    handler.removeCallbacksAndMessages(null);
                    handler_for_sound.removeCallbacksAndMessages(null);
                    handler_to_flash.removeCallbacksAndMessages(null);
                    handler_to_update_arc.removeCallbacksAndMessages(null);
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }
            }
        }, 15000);
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
    }

    private void stop_the_sound() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void flash_the_arc() {
        final Button button_to_open_one_minute_emergency = findViewById(R.id.button_to_open_one_minute_emergency);
        final Button button_to_stop_sound = findViewById(R.id.button_to_stop_sound);
        handler_to_flash.postDelayed(new Runnable() {
            public void run() {
                Log.w("hellohello", "I am here 2");
                ArcProgress progress_for_lock_circle = findViewById(R.id.progress_for_lock_circle);
                if (progress_for_lock_circle.getProgress() == 0) {
                    progress_for_lock_circle.setProgress(100000);
                } else {
                    progress_for_lock_circle.setProgress(0);
                }
                if (isInFront && Get_the_time.open_the_file(getApplicationContext()).equals("00:00") && (button_to_open_one_minute_emergency.getVisibility() == View.VISIBLE || button_to_stop_sound.getVisibility() == View.VISIBLE)) {
                    handler_to_flash.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }

    private void set_the_stop_button() {
        Button button_to_stop_sound = findViewById(R.id.button_to_stop_sound);
        button_to_stop_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                handler.removeCallbacksAndMessages(null);
                handler_for_sound.removeCallbacksAndMessages(null);
                handler_to_flash.removeCallbacksAndMessages(null);
                handler_to_update_arc.removeCallbacksAndMessages(null);
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void update_the_arc() {
        ArcProgress progress_for_lock_circle = findViewById(R.id.progress_for_lock_circle);
        SharedPreferences sharedPreferences = getSharedPreferences("future_time_in_milli_seconds", MODE_PRIVATE);
        long future_time = sharedPreferences.getLong("time", 0);
        long time_left = future_time - System.currentTimeMillis();
        long time_on_create = sharedPreferences.getLong("time_on_create", -1);
        if (time_on_create != -1) {
            int percentage = (int) (((float) time_left / time_on_create) * 100000);
            progress_for_lock_circle.setProgress(percentage);
        } else {
            progress_for_lock_circle.setProgress(100000);
        }
    }

    private void update_the_arc_timer() {
        String not_ready = Get_the_time.open_the_file(this);
        String[] splitted = not_ready.split(":");
        int hours = Integer.parseInt(splitted[0]);
        int minutes = Integer.parseInt(splitted[1]);
        if (hours > 0) {
            delay_for_arc = 10000;
        } else {
            if (minutes > 10) {
                delay_for_arc = 1000;
            } else {
                delay_for_arc = 10;
            }
        }
    }

    private void update_the_arc_hander() {
        final Button button_to_open_one_minute_emergency = findViewById(R.id.button_to_open_one_minute_emergency);
        handler_to_update_arc.postDelayed(new Runnable() {
            public void run() {
                update_the_arc();
                if (isInFront && (button_to_open_one_minute_emergency.getVisibility() == View.VISIBLE) && !Get_the_time.open_the_file(getApplicationContext()).equals("00:00")) {
                    handler_to_update_arc.postDelayed(this, delay_for_arc);
                }
            }
        }, delay_for_arc);
    }

    private void check_box_listen() {
        CheckBox check_box_to_ring_when_lock_is_done = findViewById(R.id.check_box_to_ring_when_lock_is_done);
        check_box_to_ring_when_lock_is_done.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean check_box) {
                SharedPreferences sharedPreferences = getSharedPreferences("check_box", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putBoolean("check_box_state", check_box);
                myEdit.apply();
            }
        });
    }

    private void check_the_state() {
        CheckBox check_box_to_ring_when_lock_is_done = findViewById(R.id.check_box_to_ring_when_lock_is_done);
        SharedPreferences sharedPreferences = getSharedPreferences("check_box", MODE_PRIVATE);
        boolean check_box_state = sharedPreferences.getBoolean("check_box_state", false);
        check_box_to_ring_when_lock_is_done.setChecked(check_box_state);
    }
    private boolean check_screen_permission_allowed(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(this);
        }
        return true;
    }
    private void show_dialog_to_ask_to_draw_over_screen(){
        if(!check_screen_permission_allowed()){
            Dialog_to_ask_to_draw_over_other_apps dialog_to_ask_to_draw_over_other_apps = new Dialog_to_ask_to_draw_over_other_apps();
            dialog_to_ask_to_draw_over_other_apps.show(getSupportFragmentManager(), "dialog_tag");
        }
    }

    private void hide_activity(){
        is_fragment_visible = true;
        ScrollView scroll_view_id_in_lock_the_screen_activity = findViewById(R.id.scroll_view_id_in_lock_the_screen_activity);
        scroll_view_id_in_lock_the_screen_activity.setVisibility(View.INVISIBLE);
    }

    private void show_activity(){
        is_fragment_visible = false;
        ScrollView scroll_view_id_in_lock_the_screen_activity = findViewById(R.id.scroll_view_id_in_lock_the_screen_activity);
        scroll_view_id_in_lock_the_screen_activity.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(is_fragment_visible){
            show_activity();
            hide_buy_premuim();
        } else {
            this.finish();
        }
    }

    private void hide_buy_premuim(){
        Buy_premuim old_fragment = (Buy_premuim) getSupportFragmentManager().findFragmentByTag("buy premium");
        if (old_fragment != null) {
            getSupportFragmentManager().beginTransaction().remove(old_fragment).commit();
        }
    }
}

