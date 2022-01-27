package com.easyhabitsapp.android;


/**
 * A simple {@link Fragment} subclass.
 */
/*
public class cold_shower extends Fragment {
    private long time_to_save;
    private Handler handler = new Handler();
    private Handler handler2 = new Handler();
    private final int delay = 100; //milliseconds
    private int delay2 = 15000; //milliseconds
    private boolean call_the_time = false;
    private long starting_time;
    private boolean run_the_chronometer = false;
    private boolean one_time_only_chronometer = true;
    private long pause_offset;
    private boolean call_the_player = true;
    private MediaPlayer mediaPlayer;

    public cold_shower() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.emergency_cold_shower, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        watching_text();
        the_five_min_button_listener();
        the_one_min_button_listener();
        the_fifteen_sec_button_listener();
        restart_button_listen();
        start_the_timer();
        stop_the_timer();
        call_the_method_every_second();
        pause_the_timer_button();
        enabling_the_check_box();
    }

    private void is_it_higher_than_the_normal() {
        if (getActivity() != null) {
            EditText editText_to_enter_min_for_shower = getActivity().findViewById(R.id.editText_to_enter_min_for_shower);
            EditText editText_to_enter_sec_for_shower = getActivity().findViewById(R.id.editText_to_enter_sec_for_shower);
            if (!editText_to_enter_min_for_shower.getText().toString().equals("")) {
                if (Integer.parseInt(editText_to_enter_min_for_shower.getText().toString()) > 99) {
                    editText_to_enter_min_for_shower.setText("99");
                }
            }
            if (!editText_to_enter_sec_for_shower.getText().toString().equals("")) {
                if (Integer.parseInt(editText_to_enter_sec_for_shower.getText().toString()) > 60) {
                    editText_to_enter_sec_for_shower.setText("60");
                }
            }
        }
    }

    private void watching_text() {
        if (getActivity() != null) {
            EditText editText_to_enter_min_for_shower = getActivity().findViewById(R.id.editText_to_enter_min_for_shower);
            EditText editText_to_enter_sec_for_shower = getActivity().findViewById(R.id.editText_to_enter_sec_for_shower);
            editText_to_enter_min_for_shower.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    is_it_higher_than_the_normal();
                    setting_the_main_clock();
                    if (getActivity() != null) {
                        EditText editText_to_enter_min_for_shower = getActivity().findViewById(R.id.editText_to_enter_min_for_shower);
                        editText_to_enter_min_for_shower.setSelection(editText_to_enter_min_for_shower.getText().toString().length());
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
                    is_it_higher_than_the_normal();
                    setting_the_main_clock();
                    if (getActivity() != null) {
                        EditText editText_to_enter_sec_for_shower = getActivity().findViewById(R.id.editText_to_enter_sec_for_shower);
                        editText_to_enter_sec_for_shower.setSelection(editText_to_enter_sec_for_shower.getText().toString().length());
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    private void the_seconds_total_to_mins(int time_to_add) {
        if (getActivity() != null) {
            EditText editText_to_enter_min_for_shower = getActivity().findViewById(R.id.editText_to_enter_min_for_shower);
            EditText editText_to_enter_sec_for_shower = getActivity().findViewById(R.id.editText_to_enter_sec_for_shower);
            int seconds;
            int minutes;
            int total_seconds;
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
            total_seconds = (minutes * 60) + seconds;
            setting_the_time_again(total_seconds + time_to_add);
        }
    }

    private void setting_the_time_again(int time_to_set) {
        if (getActivity() != null) {
            EditText editText_to_enter_min_for_shower = getActivity().findViewById(R.id.editText_to_enter_min_for_shower);
            EditText editText_to_enter_sec_for_shower = getActivity().findViewById(R.id.editText_to_enter_sec_for_shower);
            if (time_to_set <= 6000) {
                int minutes = time_to_set / 60;
                int seconds = time_to_set - (minutes * 60);
                editText_to_enter_min_for_shower.setText(String.valueOf(minutes));
                editText_to_enter_sec_for_shower.setText(String.valueOf(seconds));
            } else {
                editText_to_enter_min_for_shower.setText("99");
                editText_to_enter_sec_for_shower.setText("59");
            }
        }
    }

    private void the_five_min_button_listener() {
        if (getActivity() != null) {
            Button five_mins_add_to_cold_shower = getActivity().findViewById(R.id.five_mins_add_to_cold_shower);
            five_mins_add_to_cold_shower.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!call_the_time) {
                        the_seconds_total_to_mins(300);
                    } else {
                        if ((time_to_save + 300000) <= (System.currentTimeMillis() + 6000000)) {
                            time_to_save = time_to_save + 300000;
                            starting_time = starting_time + 300000;
                        } else {
                            time_to_save = System.currentTimeMillis() + 6000000;
                        }
                    }
                }
            });
        }
    }

    private void the_one_min_button_listener() {
        if (getActivity() != null) {
            Button one_mins_add_to_cold_shower = getActivity().findViewById(R.id.one_mins_add_to_cold_shower);
            one_mins_add_to_cold_shower.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!call_the_time) {
                        the_seconds_total_to_mins(60);
                    } else {
                        if ((time_to_save + 60000) <= (System.currentTimeMillis() + 6000000)) {
                            time_to_save = time_to_save + 60000;
                            starting_time = starting_time + 60000;
                        } else {
                            time_to_save = System.currentTimeMillis() + 6000000;
                        }
                    }
                }
            });
        }
    }

    private void the_fifteen_sec_button_listener() {
        if (getActivity() != null) {
            Button fifteen_sec_add_to_cold_shower = getActivity().findViewById(R.id.fifteen_sec_add_to_cold_shower);
            fifteen_sec_add_to_cold_shower.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!call_the_time) {
                        the_seconds_total_to_mins(15);
                    } else {
                        if ((time_to_save + 15000) <= (System.currentTimeMillis() + 6000000)) {
                            time_to_save = time_to_save + 15000;
                            starting_time = starting_time + 15000;
                        } else {
                            time_to_save = System.currentTimeMillis() + 6000000;
                        }
                    }
                }
            });
        }
    }

    private void setting_the_main_clock() {
        if (getActivity() != null) {
            TextView minutes_remaining_for_shower_to_finish = getActivity().findViewById(R.id.minutes_for_shower_to_end);
            TextView seconds_remaining_for_shower_to_finish = getActivity().findViewById(R.id.seconds_for_shower_to_end);
            EditText editText_to_enter_min_for_shower = getActivity().findViewById(R.id.editText_to_enter_min_for_shower);
            EditText editText_to_enter_sec_for_shower = getActivity().findViewById(R.id.editText_to_enter_sec_for_shower);
            if (!editText_to_enter_min_for_shower.getText().toString().equals("0") && !editText_to_enter_min_for_shower.getText().toString().equals("00") && !editText_to_enter_min_for_shower.getText().toString().equals("")) {
                if (editText_to_enter_min_for_shower.getText().toString().length() > 1) {
                    minutes_remaining_for_shower_to_finish.setText(editText_to_enter_min_for_shower.getText().toString());
                } else {
                    minutes_remaining_for_shower_to_finish.setText("0".concat(editText_to_enter_min_for_shower.getText().toString()));
                }
            } else {
                minutes_remaining_for_shower_to_finish.setText("00");
            }
            if (!editText_to_enter_sec_for_shower.getText().toString().equals("0") && !editText_to_enter_sec_for_shower.getText().toString().equals("00") && !editText_to_enter_sec_for_shower.getText().toString().equals("")) {
                if (editText_to_enter_sec_for_shower.getText().toString().length() > 1) {
                    seconds_remaining_for_shower_to_finish.setText(editText_to_enter_sec_for_shower.getText().toString());
                } else {
                    seconds_remaining_for_shower_to_finish.setText("0".concat(editText_to_enter_sec_for_shower.getText().toString()));
                }
            } else {
                seconds_remaining_for_shower_to_finish.setText("00");
            }
        }
    }

    private void restart_button_listen() {
        if (getActivity() != null) {
            Button restarting_timer_for_cold_shower_button = getActivity().findViewById(R.id.restarting_timer_for_cold_shower_button);
            restarting_timer_for_cold_shower_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        EditText editText_to_enter_min_for_shower = getActivity().findViewById(R.id.editText_to_enter_min_for_shower);
                        EditText editText_to_enter_sec_for_shower = getActivity().findViewById(R.id.editText_to_enter_sec_for_shower);
                        editText_to_enter_min_for_shower.setText("0");
                        editText_to_enter_sec_for_shower.setText("0");
                    }
                }
            });
        }
    }

    private void start_the_timer() {
        if (getActivity() != null) {
            Button Starting_timer_for_cold_shower_button = getActivity().findViewById(R.id.Starting_timer_for_cold_shower_button);
            Starting_timer_for_cold_shower_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        EditText editText_to_enter_min_for_shower = getActivity().findViewById(R.id.editText_to_enter_min_for_shower);
                        EditText editText_to_enter_sec_for_shower = getActivity().findViewById(R.id.editText_to_enter_sec_for_shower);
                        if ((!editText_to_enter_min_for_shower.getText().toString().equals("") || !editText_to_enter_sec_for_shower.getText().toString().equals("")) && ((!editText_to_enter_min_for_shower.getText().toString().equals("0") && !editText_to_enter_min_for_shower.getText().toString().equals("00")) || (!editText_to_enter_sec_for_shower.getText().toString().equals("0") && !editText_to_enter_sec_for_shower.getText().toString().equals("00")))) {
                            Button restarting_timer_for_cold_shower_button = getActivity().findViewById(R.id.restarting_timer_for_cold_shower_button);
                            Button Starting_timer_for_cold_shower_button = getActivity().findViewById(R.id.Starting_timer_for_cold_shower_button);
                            Button pause_timer_for_cold_shower = getActivity().findViewById(R.id.pause_timer_for_cold_shower);
                            Button stop_timer_cold_shower = getActivity().findViewById(R.id.stop_timer_cold_shower);
                            View transparent_line_for_the_limit_of_scroll = getActivity().findViewById(R.id.transparent_line_for_the_limit_of_scroll);
                            restarting_timer_for_cold_shower_button.setVisibility(View.INVISIBLE);
                            Starting_timer_for_cold_shower_button.setVisibility(View.INVISIBLE);
                            pause_timer_for_cold_shower.setVisibility(View.VISIBLE);
                            stop_timer_cold_shower.setVisibility(View.VISIBLE);
                            time_to_save = System.currentTimeMillis() + time_to_seconds();
                            call_the_time = true;
                            starting_time = time_to_seconds();
                            editText_to_enter_min_for_shower.setText("");
                            editText_to_enter_sec_for_shower.setText("");
                            editText_to_enter_min_for_shower.clearFocus();
                            editText_to_enter_sec_for_shower.clearFocus();
                            transparent_line_for_the_limit_of_scroll.requestFocus();
                        } else {
                            Toast toast = Toast.makeText(getActivity().getApplicationContext(), "time cant be zero", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                }
            });
        }
    }

    private void stop_the_timer() {
        if (getActivity() != null) {
            Button stop_timer_cold_shower = getActivity().findViewById(R.id.stop_timer_cold_shower);
            stop_timer_cold_shower.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        CheckBox check_box_for_ringing = getActivity().findViewById(R.id.check_box_for_ringing);
                        check_box_for_ringing.setEnabled(true);
                        ArcProgress arcProgress = getActivity().findViewById(R.id.timer_progress_for_shower_count_down);
                        EditText editText_to_enter_min_for_shower = getActivity().findViewById(R.id.editText_to_enter_min_for_shower);
                        EditText editText_to_enter_sec_for_shower = getActivity().findViewById(R.id.editText_to_enter_sec_for_shower);
                        Button restarting_timer_for_cold_shower_button = getActivity().findViewById(R.id.restarting_timer_for_cold_shower_button);
                        Button Starting_timer_for_cold_shower_button = getActivity().findViewById(R.id.Starting_timer_for_cold_shower_button);
                        Button pause_timer_for_cold_shower = getActivity().findViewById(R.id.pause_timer_for_cold_shower);
                        Button stop_timer_cold_shower = getActivity().findViewById(R.id.stop_timer_cold_shower);
                        restarting_timer_for_cold_shower_button.setVisibility(View.VISIBLE);
                        Starting_timer_for_cold_shower_button.setVisibility(View.VISIBLE);
                        pause_timer_for_cold_shower.setVisibility(View.INVISIBLE);
                        stop_timer_cold_shower.setVisibility(View.INVISIBLE);
                        call_the_time = false;
                        editText_to_enter_min_for_shower.setText("0");
                        editText_to_enter_sec_for_shower.setText("0");
                        arcProgress.setProgress(0);
                        if (pause_timer_for_cold_shower.getText().toString().equals("Continue timer")){
                            pause_timer_for_cold_shower.setText("Pause timer");
                        }
                        pause_timer_for_cold_shower.setText("");
                        if (run_the_chronometer) {
                            TextView plus_sign_behind_shower_timer = getActivity().findViewById(R.id.plus_sign_behind_shower_timer);
                            TextView minutes_remaining_for_shower_to_finish = getActivity().findViewById(R.id.minutes_for_shower_to_end);
                            TextView seconds_remaining_for_shower_to_finish = getActivity().findViewById(R.id.seconds_for_shower_to_end);
                            TextView colon_between_two_times_clock2 = getActivity().findViewById(R.id.colon_between_two_times_clock2);
                            Chronometer chronometer_cold_shower = getActivity().findViewById(R.id.chronometer_cold_shower);
                            plus_sign_behind_shower_timer.setVisibility(View.GONE);
                            chronometer_cold_shower.setVisibility(View.GONE);
                            minutes_remaining_for_shower_to_finish.setVisibility(View.VISIBLE);
                            seconds_remaining_for_shower_to_finish.setVisibility(View.VISIBLE);
                            colon_between_two_times_clock2.setVisibility(View.VISIBLE);
                            run_the_chronometer = false;
                            one_time_only_chronometer = true;
                            handler2.removeCallbacksAndMessages(null);
                            if (mediaPlayer != null) {
                                if (mediaPlayer.isPlaying()) {
                                    if (check_box_for_ringing.isChecked()) {
                                        mediaPlayer.reset();
                                        mediaPlayer.release();
                                        mediaPlayer = null;
                                    }
                                }
                            }

                            if (getActivity() != null) {
                                AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                                if (audioManager != null) {
                                    audioManager.abandonAudioFocus(null);
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    private long time_to_seconds() {
        if (getActivity() != null) {
            TextView minutes_remaining_for_shower_to_finish = getActivity().findViewById(R.id.minutes_for_shower_to_end);
            TextView seconds_remaining_for_shower_to_finish = getActivity().findViewById(R.id.seconds_for_shower_to_end);
            return TimeUnit.SECONDS.toMillis((Integer.parseInt(minutes_remaining_for_shower_to_finish.getText().toString()) * 60) + Integer.parseInt(seconds_remaining_for_shower_to_finish.getText().toString()));
        }
        return 60;
    }

    private void counting_down_timer() {
        if (getActivity() != null) {
            TextView minutes_remaining_for_shower_to_finish = getActivity().findViewById(R.id.minutes_for_shower_to_end);
            TextView seconds_remaining_for_shower_to_finish = getActivity().findViewById(R.id.seconds_for_shower_to_end);
            long time_in_sec = TimeUnit.MILLISECONDS.toSeconds(time_to_save - System.currentTimeMillis());
            int minutes = (int) time_in_sec / 60;
            int seconds = (int) time_in_sec - (minutes * 60);
            if (minutes > 9 || minutes < 0) {
                minutes_remaining_for_shower_to_finish.setText(String.valueOf(minutes));
            } else {
                minutes_remaining_for_shower_to_finish.setText("0".concat(String.valueOf(minutes)));
            }
            if (seconds > 9 || seconds < 0) {
                seconds_remaining_for_shower_to_finish.setText(String.valueOf(seconds));
            } else {
                seconds_remaining_for_shower_to_finish.setText("0".concat(String.valueOf(seconds)));
            }
        }
    }

    private void call_the_method_every_second() {
        handler.postDelayed(new Runnable() {
            public void run() {
                if (call_the_time) {
                    counting_down_timer();
                    progress_par_edit();
                    timer_hitting_zero();
                }
                if (run_the_chronometer) {
                    dealing_with_the_chronometer();
                }
                if (cold_shower.this.isVisible()) {
                    handler.postDelayed(this, delay);
                }
            }
        }, delay);
    }

    private void close_the_sound_player() {
        handler2.postDelayed(new Runnable() {
            public void run() {
                try {
                    if (call_the_player) {
                        if (getActivity() != null) {
                            AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                            if (audioManager != null) {
                                audioManager.abandonAudioFocus(null);
                            }
                        }
                        if (mediaPlayer != null) {
                            if (mediaPlayer.isPlaying()) {
                                mediaPlayer.reset();
                                mediaPlayer.release();
                                mediaPlayer = null;
                            }
                        }
                        call_the_player = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (cold_shower.this.isVisible()) {
                    handler.postDelayed(this, delay2);
                }
            }
        }, delay2);
    }

    private void pause_the_timer_button() {
        if (getActivity() != null) {
            Button pause_timer_for_cold_shower = getActivity().findViewById(R.id.pause_timer_for_cold_shower);
            pause_timer_for_cold_shower.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() != null) {
                        Chronometer chronometer_cold_shower = getActivity().findViewById(R.id.chronometer_cold_shower);
                        Button pause_timer_for_cold_shower = getActivity().findViewById(R.id.pause_timer_for_cold_shower);
                        TextView minutes_remaining_for_shower_to_finish = getActivity().findViewById(R.id.minutes_for_shower_to_end);
                        if (pause_timer_for_cold_shower.getText().toString().equals("Pause timer")) {
                            pause_timer_for_cold_shower.setText("Continue timer");
                            call_the_time = false;
                            pause_offset = SystemClock.elapsedRealtime() - chronometer_cold_shower.getBase();
                            run_the_chronometer = false;
                            chronometer_cold_shower.stop();
                        } else {
                            pause_timer_for_cold_shower.setText("Pause timer");
                            if (minutes_remaining_for_shower_to_finish.getVisibility() == View.VISIBLE) {
                                call_the_time = true;
                                time_to_save = System.currentTimeMillis() + time_to_seconds();
                            } else {
                                run_the_chronometer = true;
                                chronometer_cold_shower.setBase(SystemClock.elapsedRealtime() - pause_offset);
                            }
                        }
                    }
                }
            });
        }
    }

    private void progress_par_edit() {
        if (getActivity() != null) {
            ArcProgress arcProgress = getActivity().findViewById(R.id.timer_progress_for_shower_count_down);
            arcProgress.setProgress((int) (time_to_seconds() * 100 / starting_time));
        }
    }

    private void timer_hitting_zero() {
        if (getActivity() != null) {
            if (time_to_seconds() == 0 || time_to_seconds() < 0) {
                call_the_time = false;
                run_the_chronometer = true;
            }
        }
    }

    private void dealing_with_the_chronometer() {
        if (getActivity() != null) {
            TextView plus_sign_behind_shower_timer = getActivity().findViewById(R.id.plus_sign_behind_shower_timer);
            TextView minutes_remaining_for_shower_to_finish = getActivity().findViewById(R.id.minutes_for_shower_to_end);
            TextView seconds_remaining_for_shower_to_finish = getActivity().findViewById(R.id.seconds_for_shower_to_end);
            TextView colon_between_two_times_clock2 = getActivity().findViewById(R.id.colon_between_two_times_clock2);
            Chronometer chronometer_cold_shower = getActivity().findViewById(R.id.chronometer_cold_shower);
            CheckBox check_box_for_ringing = getActivity().findViewById(R.id.check_box_for_ringing);
            if (one_time_only_chronometer) {
                chronometer_cold_shower.setBase(SystemClock.elapsedRealtime());
                chronometer_cold_shower.start();
                minutes_remaining_for_shower_to_finish.setVisibility(View.GONE);
                seconds_remaining_for_shower_to_finish.setVisibility(View.GONE);
                colon_between_two_times_clock2.setVisibility(View.GONE);
                chronometer_cold_shower.setVisibility(View.VISIBLE);
                plus_sign_behind_shower_timer.setVisibility(View.VISIBLE);
                if (check_box_for_ringing.isChecked()) {
                    dealing_with_sound();
                }
                check_box_for_ringing.setEnabled(false);
                one_time_only_chronometer = false;
            } else {
                chronometer_cold_shower.start();
            }
        }
    }

    private void dealing_with_sound() {
        if (getActivity() != null) {
            Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            if (alert == null) {
                // alert is null, using backup
                alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                // I can't see this ever being null (as always have a default notification)
                // but just incase
                if (alert == null) {
                    // alert backup is null, using 2nd backup
                    alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                }
            }
            AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
            if (audioManager != null) {
                if (audioManager.isMusicActive()) {
                    audioManager.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                }
            }
            mediaPlayer = MediaPlayer.create(getActivity().getApplicationContext(), alert);
            mediaPlayer.start();
            call_the_player = true;
            close_the_sound_player();
        }
    }
    private void enabling_the_check_box() {
        if (getActivity() != null) {
            CheckBox check_box_for_ringing = getActivity().findViewById(R.id.check_box_for_ringing);
            check_box_for_ringing.setEnabled(true);
        }
    }
}
*/
