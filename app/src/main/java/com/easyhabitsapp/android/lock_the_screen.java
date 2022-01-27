package com.easyhabitsapp.android;


import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import com.github.lzyzsd.circleprogress.ArcProgress;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class lock_the_screen extends Fragment {
    private Handler handler = new Handler();
    private Handler handler2 = new Handler();
    private Handler handler3 = new Handler();
    private Handler handler4 = new Handler();
    private final int delay4 = 20000;
    private final int delay = 3000; //milliseconds
    private int delay2;
    private final int delay3 = 1000;
    private static final String FILE_NAME = "only_once_locker.txt";
    private int minutes_global = 0;
    private int hours_global = 0;
    private long time_to_ring;
    private long start_time;
    private boolean call_the_timers = false;
    private MediaPlayer mediaPlayer;
    private boolean call_the_stop = false;

    public lock_the_screen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_lock_the_screen, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        watching_text_clock_change();
        fifteen_min_listen();
        five_min_listen();
        one_min_listen();
        grab_focus();
        bar_change();
        submit_button_to_listen();
        stop_timer_watch_listen();
        white_list_button_listen();
    }

    private String getForegroundApp() {
        String currentApp = "NULL";
        if (getContext() != null) {
            UsageStatsManager usm = (UsageStatsManager) getContext().getSystemService(Context.USAGE_STATS_SERVICE);
            long time = System.currentTimeMillis();
            if (usm != null) {
                List<UsageStats> appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000 * 1000, time);
                if (appList != null && appList.size() > 0) {
                    SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
                    for (UsageStats usageStats : appList) {
                        mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
                    }
                    if (mySortedMap != null && !mySortedMap.isEmpty()) {
                        currentApp = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                    }
                }
            }
        }
        return currentApp;
    }

    private void call_the_method_every_second() {
        handler.postDelayed(new Runnable() {
            public void run() {
                load_only_once();
                if (!getForegroundApp().equals("com.example.learn1")) {
                    if (getActivity() != null) {
                        Intent intent = new Intent(getActivity(), after_login.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        intent.putExtra("open_fragment", true);
                        getActivity().startActivity(intent);
                    }
                }
                handler.postDelayed(this, delay);
            }
        }, delay);
    }

    private void load_only_once() {
        FileInputStream fileInputStream = null;
        try {
            if (getActivity() != null) {
                fileInputStream = getActivity().openFileInput(FILE_NAME);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String load_text;
                while ((load_text = bufferedReader.readLine()) != null) {
                    stringBuilder.append(load_text);
                }
                String string_to_load = stringBuilder.toString();
                saving_the_only_once();
                launching_the_fragment_only_once(string_to_load);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saving_the_only_once() {
        FileOutputStream fileOutputStream = null;
        try {
            if (getActivity() != null) {
                fileOutputStream = getActivity().openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                fileOutputStream.write("false".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void launching_the_fragment_only_once(String answer) {
        if (answer.equals("true")) {
            if (getActivity() != null) {
                Intent intent = new Intent(getActivity(), after_login.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("open_fragment", true);
                getActivity().startActivity(intent);
            }
        }
    }

    private void watching_text_clock_change() {
        if (getActivity() != null) {
            EditText edit_text_for_hours_under_progress = getActivity().findViewById(R.id.edit_text_for_hours_under_progress);
            EditText edit_text_for_minutes_under_progress = getActivity().findViewById(R.id.edit_text_for_minutes_under_progress);
            edit_text_for_hours_under_progress.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (getActivity() != null) {
                        EditText edit_text_for_hours_under_progress = getActivity().findViewById(R.id.edit_text_for_hours_under_progress);
                        if (!edit_text_for_hours_under_progress.getText().toString().equals("")) {
                            int hours = Integer.parseInt(edit_text_for_hours_under_progress.getText().toString());
                            if (hours > 2) {
                                edit_text_for_hours_under_progress.setText("2");
                            }
                        }
                        setting_the_text_for_hours(edit_text_for_hours_under_progress.getText().toString());
                        edit_text_for_hours_under_progress.setSelection(edit_text_for_hours_under_progress.getText().toString().length());
                        checking_for_change();
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
                    if (getActivity() != null) {
                        EditText edit_text_for_minutes_under_progress = getActivity().findViewById(R.id.edit_text_for_minutes_under_progress);
                        if (!edit_text_for_minutes_under_progress.getText().toString().equals("")) {
                            int minutes = Integer.parseInt(edit_text_for_minutes_under_progress.getText().toString());
                            if (minutes > 60) {
                                edit_text_for_minutes_under_progress.setText("60");
                            }
                        }
                        setting_the_text_for_minutes(edit_text_for_minutes_under_progress.getText().toString());
                        edit_text_for_minutes_under_progress.setSelection(edit_text_for_minutes_under_progress.getText().toString().length());
                        checking_for_change();
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    private void setting_the_text_for_hours(String hours) {
        if (getActivity() != null) {
            TextView lock_hours_to_end = getActivity().findViewById(R.id.lock_hours_to_end);
            if (hours.equals("")) {
                lock_hours_to_end.setText("00");
            } else if (hours.equals("0") || hours.equals("00")) {
                lock_hours_to_end.setText("00");
            } else {
                lock_hours_to_end.setText("0".concat(hours));
            }
        }
    }

    private void setting_the_text_for_minutes(String minutes_string) {
        if (getActivity() != null) {
            TextView lock_minutes_to_end = getActivity().findViewById(R.id.lock_minutes_to_end);
            if (minutes_string.equals("")) {
                lock_minutes_to_end.setText("00");
            } else {
                int minutes_int = Integer.parseInt(minutes_string);
                if (minutes_int >= 10) {
                    lock_minutes_to_end.setText(minutes_string);
                } else if (minutes_int == 0) {
                    lock_minutes_to_end.setText("00");
                } else {
                    lock_minutes_to_end.setText("0".concat(minutes_string));
                }
            }
        }
    }

    private void fifteen_min_listen() {
        if (getActivity() != null) {
            Button add_ten_minutes_under_the_lock = getActivity().findViewById(R.id.add_fifteen_minutes_under_the_lock);
            add_ten_minutes_under_the_lock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        EditText edit_text_for_hours_under_progress = getActivity().findViewById(R.id.edit_text_for_hours_under_progress);
                        EditText edit_text_for_minutes_under_progress = getActivity().findViewById(R.id.edit_text_for_minutes_under_progress);
                        set_bar_to_zero();
                        add_time(15);
                        edit_text_for_hours_under_progress.clearFocus();
                        edit_text_for_minutes_under_progress.clearFocus();
                        grab_focus();
                    }
                }
            });
        }
    }

    private void five_min_listen() {
        if (getActivity() != null) {
            Button add_five_minutes_under_the_lock = getActivity().findViewById(R.id.add_five_minutes_under_the_lock);
            add_five_minutes_under_the_lock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        EditText edit_text_for_hours_under_progress = getActivity().findViewById(R.id.edit_text_for_hours_under_progress);
                        EditText edit_text_for_minutes_under_progress = getActivity().findViewById(R.id.edit_text_for_minutes_under_progress);
                        set_bar_to_zero();
                        add_time(5);
                        edit_text_for_hours_under_progress.clearFocus();
                        edit_text_for_minutes_under_progress.clearFocus();
                        grab_focus();
                    }
                }
            });
        }
    }

    private void one_min_listen() {
        if (getActivity() != null) {
            Button add_one_minutes_under_the_lock = getActivity().findViewById(R.id.add_one_minutes_under_the_lock);
            add_one_minutes_under_the_lock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        EditText edit_text_for_hours_under_progress = getActivity().findViewById(R.id.edit_text_for_hours_under_progress);
                        EditText edit_text_for_minutes_under_progress = getActivity().findViewById(R.id.edit_text_for_minutes_under_progress);
                        set_bar_to_zero();
                        add_time(1);
                        edit_text_for_hours_under_progress.clearFocus();
                        edit_text_for_minutes_under_progress.clearFocus();
                        grab_focus();
                    }
                }
            });
        }
    }

    private void add_time(int minutes) {
        if (getActivity() != null) {
            EditText edit_text_for_hours_under_progress = getActivity().findViewById(R.id.edit_text_for_hours_under_progress);
            EditText edit_text_for_minutes_under_progress = getActivity().findViewById(R.id.edit_text_for_minutes_under_progress);
            int total_minutes;
            if (edit_text_for_minutes_under_progress.getText().toString().equals("")) {
                total_minutes = 0;
            } else {
                total_minutes = Integer.parseInt(edit_text_for_minutes_under_progress.getText().toString());
            }
            if (!edit_text_for_hours_under_progress.getText().toString().equals("")) {
                total_minutes = total_minutes + (Integer.parseInt(edit_text_for_hours_under_progress.getText().toString()) * 60);
            }
            total_minutes = total_minutes + minutes;
            put_time(total_minutes);
        }
    }

    private void put_time(int total_minutes) {
        if (getActivity() != null) {
            EditText edit_text_for_hours_under_progress = getActivity().findViewById(R.id.edit_text_for_hours_under_progress);
            EditText edit_text_for_minutes_under_progress = getActivity().findViewById(R.id.edit_text_for_minutes_under_progress);
            if (total_minutes < 180) {
                int hours = total_minutes / 60;
                int minutes = total_minutes - (hours * 60);
                edit_text_for_hours_under_progress.setText(String.valueOf(hours));
                edit_text_for_minutes_under_progress.setText(String.valueOf(minutes));
            } else {
                edit_text_for_hours_under_progress.setText("2");
                edit_text_for_minutes_under_progress.setText("60");
            }
        }
    }

    private void grab_focus() {
        if (getActivity() != null) {
            View current = getActivity().getCurrentFocus();
            if (current != null) {
                current.clearFocus();
            }
            TextView semi_colon_under_progress = getActivity().findViewById(R.id.semi_colon_under_progress);
            semi_colon_under_progress.requestFocus();
        }
    }

    private void bar_change() {
        if (getActivity() != null) {
            SeekBar seekBar_for_time_lock = getActivity().findViewById(R.id.seekBar_for_time_lock);
            seekBar_for_time_lock.setMax(90);
            seekBar_for_time_lock.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                    if (getActivity() != null) {
                        EditText edit_text_for_hours_under_progress = getActivity().findViewById(R.id.edit_text_for_hours_under_progress);
                        EditText edit_text_for_minutes_under_progress = getActivity().findViewById(R.id.edit_text_for_minutes_under_progress);
                        TextView minutes_besides_edit_text_above_seekbar = getActivity().findViewById(R.id.minutes_besides_edit_text_above_seekbar);
                        int minutes = progress * 2;
                        minutes_besides_edit_text_above_seekbar.setText(String.valueOf(minutes).concat(" Minutes"));
                        edit_text_for_hours_under_progress.setText("");
                        edit_text_for_minutes_under_progress.setText("");
                        grab_focus();
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
    }

    private void set_bar_to_zero() {
        if (getActivity() != null) {
            SeekBar seekBar_for_time_lock = getActivity().findViewById(R.id.seekBar_for_time_lock);
            seekBar_for_time_lock.setProgress(0);
        }
    }

    private void checking_for_change() {
        if (getActivity() != null) {
            EditText edit_text_for_hours_under_progress = getActivity().findViewById(R.id.edit_text_for_hours_under_progress);
            EditText edit_text_for_minutes_under_progress = getActivity().findViewById(R.id.edit_text_for_minutes_under_progress);
            int hours;
            int minutes;
            if (!edit_text_for_hours_under_progress.getText().toString().equals("")) {
                hours = Integer.parseInt(edit_text_for_hours_under_progress.getText().toString());
            } else {
                hours = 0;
            }
            if (!edit_text_for_minutes_under_progress.getText().toString().equals("")) {
                minutes = Integer.parseInt(edit_text_for_minutes_under_progress.getText().toString());
            } else {
                minutes = 0;
            }
            if (minutes != minutes_global || hours != hours_global) {
                set_bar_to_zero();
            }
            minutes_global = minutes;
            hours_global = hours;
        }
    }

    private void submit_button_to_listen() {
        if (getActivity() != null) {
            Button turn_on_the_lock_button = getActivity().findViewById(R.id.turn_on_the_lock_button);
            turn_on_the_lock_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        if (check_if_it_is_ok()) {
                            grab_focus();
                            SeekBar seekBar_for_time_lock = getActivity().findViewById(R.id.seekBar_for_time_lock);
                            if (seekBar_for_time_lock.getProgress() != 0) {
                                progress_time("bar");
                            } else {
                                progress_time("not_time");
                            }
                        } else {
                            Toast toast = Toast.makeText(getActivity().getApplicationContext(), "time cant be zero", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                }
            });
        }
    }

    private void progress_time(String who) {
        if (getActivity() != null) {
            int minutes;
            if (who.equals("bar")) {
                TextView minutes_besides_edit_text_above_seekbar = getActivity().findViewById(R.id.minutes_besides_edit_text_above_seekbar);
                minutes = Integer.parseInt(minutes_besides_edit_text_above_seekbar.getText().toString().replace(" Minutes", ""));
            } else {
                minutes = time_to_minutes();
            }
            call_the_timers = true;
            time_to_ring = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(minutes);
            start_time = System.currentTimeMillis();
            delay2 = 600 * minutes;
            update_the_arc_minute();
            calculate_the_arc();
            update_the_time_minute();
            time_left();
            everything_go();
        }
    }

    private void update_the_arc_minute() {
        handler2.postDelayed(new Runnable() {
            public void run() {
                calculate_the_arc();
                if (lock_the_screen.this.isVisible() && call_the_timers) {
                    handler2.postDelayed(this, delay2);
                }
            }
        }, delay2);
    }

    private void calculate_the_arc() {
        if (getActivity() != null) {
            ArcProgress progress_for_lock_circle = getActivity().findViewById(R.id.progress_for_lock_circle);
            long current_time = System.currentTimeMillis() - start_time;
            long goal_time = time_to_ring - start_time;
            int progress_arc = (int) ((current_time * 100) / goal_time);
            progress_for_lock_circle.setProgress(100 - progress_arc);
        }
    }

    private int time_to_minutes() {
        if (getActivity() != null) {
            TextView lock_hours_to_end = getActivity().findViewById(R.id.lock_hours_to_end);
            TextView lock_minutes_to_end = getActivity().findViewById(R.id.lock_minutes_to_end);
            int hours;
            int minutes;
            if (lock_hours_to_end.getText().toString().equals("")) {
                hours = 0;
            } else {
                hours = Integer.parseInt(lock_hours_to_end.getText().toString());
            }
            if (lock_minutes_to_end.getText().toString().equals("")) {
                minutes = 0;
            } else {
                minutes = Integer.parseInt(lock_minutes_to_end.getText().toString());
            }
            return (hours * 60) + minutes;
        }
        return 0;
    }

    private void time_left() {
        if (getActivity() != null) {
            double difference = time_to_ring - System.currentTimeMillis();
            int time_left_to_end = (int) (Math.ceil(difference / 60000));
            if (time_left_to_end != 0) {
                int hours = time_left_to_end / 60;
                int minutes = (time_left_to_end - (hours * 60));
                setting_the_text_for_hours(String.valueOf(hours));
                setting_the_text_for_minutes(String.valueOf(minutes));
            } else {
                setting_the_text_for_hours("0");
                setting_the_text_for_minutes("0");
                handler2.removeCallbacksAndMessages(null);
                handler3.removeCallbacksAndMessages(null);
                ArcProgress progress_for_lock_circle = getActivity().findViewById(R.id.progress_for_lock_circle);
                progress_for_lock_circle.setProgress(0);
                call_the_timers = false;
                CheckBox check_box_to_ring_when_lock_is_done = getActivity().findViewById(R.id.check_box_to_ring_when_lock_is_done);
                Button button_to_stop_the_timer_lock = getActivity().findViewById(R.id.button_to_stop_the_timer_lock);
                check_box_to_ring_when_lock_is_done.setEnabled(false);
                if (check_box_to_ring_when_lock_is_done.isChecked()) {
                    dealing_with_sound();
                    button_to_stop_the_timer_lock.setVisibility(View.VISIBLE);
                    call_the_stop = true;
                } else {
                    Intent intent = new Intent(getActivity(), after_login.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.putExtra("open_fragment", true);
                    getActivity().startActivity(intent);
                }
            }
        }
    }

    private void update_the_time_minute() {
        handler3.postDelayed(new Runnable() {
            public void run() {
                time_left();
                if (lock_the_screen.this.isVisible() && call_the_timers) {
                    handler3.postDelayed(this, delay3);
                }
            }
        }, delay3);
    }

    private boolean check_if_it_is_ok() {
        if (getActivity() != null) {
            SeekBar seekBar_for_time_lock = getActivity().findViewById(R.id.seekBar_for_time_lock);
            if (seekBar_for_time_lock.getProgress() != 0 || time_to_minutes() != 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private void everything_go() {
        if (getActivity() != null) {
            EditText edit_text_for_hours_under_progress = getActivity().findViewById(R.id.edit_text_for_hours_under_progress);
            TextView semi_colon_under_progress = getActivity().findViewById(R.id.semi_colon_under_progress);
            EditText edit_text_for_minutes_under_progress = getActivity().findViewById(R.id.edit_text_for_minutes_under_progress);
            Button add_fifteen_minutes_under_the_lock = getActivity().findViewById(R.id.add_fifteen_minutes_under_the_lock);
            Button add_five_minutes_under_the_lock = getActivity().findViewById(R.id.add_five_minutes_under_the_lock);
            Button add_one_minutes_under_the_lock = getActivity().findViewById(R.id.add_one_minutes_under_the_lock);
            View horizantal_line_behind_or_lock_screen = getActivity().findViewById(R.id.horizantal_line_behind_or_lock_screen);
            TextView or_text_asking_between_buttons_and_progress_bar = getActivity().findViewById(R.id.or_text_asking_between_buttons_and_progress_bar);
            TextView minutes_besides_edit_text_above_seekbar = getActivity().findViewById(R.id.minutes_besides_edit_text_above_seekbar);
            SeekBar seekBar_for_time_lock = getActivity().findViewById(R.id.seekBar_for_time_lock);
            Button add_other_apps_to_white_list_lock_button = getActivity().findViewById(R.id.add_other_apps_to_white_list_lock_button);
            CheckBox check_box_to_ring_when_lock_is_done = getActivity().findViewById(R.id.check_box_to_ring_when_lock_is_done);
            ArcProgress progress_for_lock_circle = getActivity().findViewById(R.id.progress_for_lock_circle);
            Button turn_on_the_lock_button = getActivity().findViewById(R.id.turn_on_the_lock_button);
            edit_text_for_hours_under_progress.setVisibility(View.GONE);
            semi_colon_under_progress.setVisibility(View.GONE);
            edit_text_for_minutes_under_progress.setVisibility(View.GONE);
            add_fifteen_minutes_under_the_lock.setVisibility(View.GONE);
            add_five_minutes_under_the_lock.setVisibility(View.GONE);
            add_one_minutes_under_the_lock.setVisibility(View.GONE);
            horizantal_line_behind_or_lock_screen.setVisibility(View.GONE);
            or_text_asking_between_buttons_and_progress_bar.setVisibility(View.GONE);
            minutes_besides_edit_text_above_seekbar.setVisibility(View.GONE);
            seekBar_for_time_lock.setVisibility(View.GONE);
            add_other_apps_to_white_list_lock_button.setVisibility(View.GONE);
            turn_on_the_lock_button.setVisibility(View.GONE);

            ConstraintLayout constraintLayout = getActivity().findViewById(R.id.id_for_lock_screen_layout);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(check_box_to_ring_when_lock_is_done.getId(), ConstraintSet.TOP, progress_for_lock_circle.getId(), ConstraintSet.BOTTOM, dip_to_pixels(25));
            constraintSet.applyTo(constraintLayout);
        }
    }

    private int dip_to_pixels(float dip) {
        Resources r = getResources();
        float pixels = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );
        return Math.round(pixels);
    }

    private void dealing_with_sound() {
        if (getActivity() != null) {
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
            AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
            if (audioManager != null) {
                if (audioManager.isMusicActive()) {
                    audioManager.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                }
            }
            mediaPlayer = MediaPlayer.create(getActivity().getApplicationContext(), alert);
            try {
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            close_the_sound_player();
        }
    }

    private void close_the_sound_player() {
        handler4.postDelayed(new Runnable() {
            public void run() {
                call_the_stop = false;
                handler4.removeCallbacksAndMessages(null);
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.reset();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                }
                if (getActivity() != null) {
                    Intent intent = new Intent(getActivity(), after_login.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.putExtra("open_fragment", true);
                    getActivity().startActivity(intent);
                }
                if (lock_the_screen.this.isVisible() && call_the_stop) {
                    handler4.postDelayed(this, delay4);
                }
            }
        }, delay4);
    }

    private void stop_timer_watch_listen() {
        if (getActivity() != null) {
            Button button_to_stop_the_timer_lock = getActivity().findViewById(R.id.button_to_stop_the_timer_lock);
            button_to_stop_the_timer_lock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        CheckBox check_box_to_ring_when_lock_is_done = getActivity().findViewById(R.id.check_box_to_ring_when_lock_is_done);
                        if (mediaPlayer != null) {
                            if (mediaPlayer.isPlaying()) {
                                if (check_box_to_ring_when_lock_is_done.isChecked()) {
                                    mediaPlayer.reset();
                                    mediaPlayer.release();
                                    mediaPlayer = null;
                                }
                            }
                        }
                        Intent intent = new Intent(getActivity(), after_login.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        intent.putExtra("open_fragment", true);
                        getActivity().startActivity(intent);
                    }
                }
            });
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        if (getActivity() != null) {
            CheckBox check_box_to_ring_when_lock_is_done = getActivity().findViewById(R.id.check_box_to_ring_when_lock_is_done);
            if (check_box_to_ring_when_lock_is_done.isChecked()) {
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.reset();
                        mediaPlayer.release();
                    }
                }
            }
            call_the_stop = false;
            handler4.removeCallbacksAndMessages(null);
        }
    }
    private void white_list_button_listen() {
        if (getActivity() != null) {
            Button add_other_apps_to_white_list_lock_button = getActivity().findViewById(R.id.add_other_apps_to_white_list_lock_button);
            add_other_apps_to_white_list_lock_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null ) {
                        saving_only_to_send("");
                        Dialog_for_white_list dialog_for_white_list = new Dialog_for_white_list();
                        dialog_for_white_list.show(getFragmentManager(),"dialog_tag");
                    }
                }
            });
        }
    }
    private void saving_only_to_send(String save_me){
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("send_the_apps", getContext().MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("send_apps", save_me);
            Log.w("see",save_me);
            myEdit.commit();
        }
    }
    public void change_the_button_color() {
        if (getActivity() != null) {
            Button add_other_apps_to_white_list_lock_button = getActivity().findViewById(R.id.add_other_apps_to_white_list_lock_button);
            add_other_apps_to_white_list_lock_button.setBackgroundResource(R.drawable.color_for_botton_on);
        }
    }
}
