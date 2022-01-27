package com.easyhabitsapp.android;

/*public class Running_biking_emergency extends AppCompatActivity implements Dialog_for_location_permission.Dialog_for_location_permission_listener, Bottom_sheet_to_change_system.BottomSheetListener_for_system {
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private String running_or_biking;
    private Location last_location = null;
    private Long time_start;
    private float total_distance;
    private long time_for_calories = 0;
    private float total_calories;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_biking_emergency);
        check_permission();
        running_biking_button_lsiten();
        hide_top_bar();
        start_button_listen();
        three_buttons_listen();
        set_the_spinner();
        set_the_info_at_the_buttom();
        finish_button_listen();
    }

    private void check_permission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PermissionChecker.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PermissionChecker.PERMISSION_GRANTED) {
            change_the_layout(0);
        } else {
            final Dialog_for_location_permission dialog_for_location_permission = new Dialog_for_location_permission();
            dialog_for_location_permission.show(getSupportFragmentManager(), "dialog_tag");
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.executePendingTransactions();
            if (dialog_for_location_permission.getDialog() != null) {
                dialog_for_location_permission.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        getSupportFragmentManager().beginTransaction().remove(dialog_for_location_permission).commitAllowingStateLoss();
                    }
                });
            }
        }
    }

    private void set_up_location() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PermissionChecker.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PermissionChecker.PERMISSION_GRANTED) {
            fusedLocationProviderClient = new FusedLocationProviderClient(this);
            locationRequest = new LocationRequest();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setFastestInterval(3000);
            locationRequest.setInterval(6000);
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    if (last_location == null) {
                        last_location = locationResult.getLastLocation();
                    } else {
                        Log.w("accuracy",String.valueOf(locationResult.getLastLocation().getAccuracy()));
                        if(locationResult.getLastLocation().hasAccuracy() && locationResult.getLastLocation().getAccuracy()>=15) {
                            total_distance = total_distance + locationResult.getLastLocation().distanceTo(last_location);
                        }
                    }
                    calculate_stuff();
                    color_the_text_views();
                }
            }, getMainLooper());
        } else {
            call_the_permission();
        }
    }

    private void call_the_permission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        Permissions.check(this , permissions, null, null, new PermissionHandler() {
            @Override
            public void onGranted() {
                Toast.makeText(Running_biking_emergency.this, "Permission granted", Toast.LENGTH_SHORT).show();
                change_the_layout(0);
            }

            @Override
            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                super.onDenied(context, deniedPermissions);
                Toast.makeText(context, "Permission Denied, please accept it in order to use the running/biking feature", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Running_biking_emergency.this, after_login.class);
                intent.putExtra("Start_the_emergency_true", true);
                Running_biking_emergency.this.startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    public void onComplete(String state) {
        if (state.equals("ok")) {
            call_the_permission();
        } else {
            Intent intent = new Intent(this, after_login.class);
            intent.putExtra("Start_the_emergency_true", true);
            this.startActivity(intent);
            overridePendingTransition(0, 0);
        }
    }

    private void change_the_layout(int which) {
        Button buutton_for_running_running = findViewById(R.id.buutton_for_running_running);
        Button button_for_biking_running = findViewById(R.id.button_for_biking_running);
        TextView text_in_top_of_running = findViewById(R.id.text_in_top_of_running);

        Button button_to_press_on_add_counter = findViewById(R.id.button_to_press_on_add_counter);
        View circle_button_counter_view = findViewById(R.id.circle_button_counter_view);
        TextView text_showing_number_of_counter_in_circle = findViewById(R.id.text_showing_number_of_counter_in_circle);
        Button button_to_add_three_dots = findViewById(R.id.button_to_add_three_dots);
        View three_buttons_in_the_top_to_add = findViewById(R.id.three_buttons_in_the_top_to_add);
        ConstraintLayout layout_to_ask_for_height_and_weight = findViewById(R.id.layout_to_ask_for_height_and_weight);

        ConstraintLayout id_for_layout_in_the_middle_of_running = findViewById(R.id.id_for_layout_in_the_middle_of_running);
        Button finish_button_in_running_or_biking = findViewById(R.id.finish_button_in_running_or_biking);


        if (which == 0) {
            buutton_for_running_running.setVisibility(View.VISIBLE);
            button_for_biking_running.setVisibility(View.VISIBLE);
            text_in_top_of_running.setVisibility(View.VISIBLE);

            button_to_press_on_add_counter.setVisibility(View.INVISIBLE);
            circle_button_counter_view.setVisibility(View.INVISIBLE);
            text_showing_number_of_counter_in_circle.setVisibility(View.INVISIBLE);
            button_to_add_three_dots.setVisibility(View.INVISIBLE);
            three_buttons_in_the_top_to_add.setVisibility(View.INVISIBLE);
            layout_to_ask_for_height_and_weight.setVisibility(View.INVISIBLE);

            id_for_layout_in_the_middle_of_running.setVisibility(View.INVISIBLE);
            finish_button_in_running_or_biking.setVisibility(View.INVISIBLE);
        } else if (which == 1) {
            text_in_top_of_running.setVisibility(View.VISIBLE);
            buutton_for_running_running.setVisibility(View.INVISIBLE);
            button_for_biking_running.setVisibility(View.INVISIBLE);

            button_to_press_on_add_counter.setVisibility(View.VISIBLE);
            circle_button_counter_view.setVisibility(View.VISIBLE);
            text_showing_number_of_counter_in_circle.setVisibility(View.VISIBLE);
            button_to_add_three_dots.setVisibility(View.VISIBLE);
            three_buttons_in_the_top_to_add.setVisibility(View.VISIBLE);
            layout_to_ask_for_height_and_weight.setVisibility(View.VISIBLE);

            id_for_layout_in_the_middle_of_running.setVisibility(View.INVISIBLE);
            finish_button_in_running_or_biking.setVisibility(View.INVISIBLE);
        } else if (which == 2) {
            text_in_top_of_running.setVisibility(View.VISIBLE);
            buutton_for_running_running.setVisibility(View.INVISIBLE);
            button_for_biking_running.setVisibility(View.INVISIBLE);

            button_to_press_on_add_counter.setVisibility(View.INVISIBLE);
            circle_button_counter_view.setVisibility(View.INVISIBLE);
            text_showing_number_of_counter_in_circle.setVisibility(View.INVISIBLE);
            button_to_add_three_dots.setVisibility(View.INVISIBLE);
            three_buttons_in_the_top_to_add.setVisibility(View.INVISIBLE);
            layout_to_ask_for_height_and_weight.setVisibility(View.INVISIBLE);

            id_for_layout_in_the_middle_of_running.setVisibility(View.VISIBLE);
            finish_button_in_running_or_biking.setVisibility(View.VISIBLE);
        }
    }

    private void running_biking_button_lsiten() {
        Button buutton_for_running_running = findViewById(R.id.buutton_for_running_running);
        Button button_for_biking_running = findViewById(R.id.button_for_biking_running);
        final TextView text_in_top_of_running = findViewById(R.id.text_in_top_of_running);
        final ConstraintLayout constraintLayout_for_height = findViewById(R.id.constraintLayout_for_height);
        buutton_for_running_running.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running_or_biking = "running";
                change_the_layout(1);
                text_in_top_of_running.setText("Running");
                constraintLayout_for_height.setVisibility(View.VISIBLE);
            }
        });
        button_for_biking_running.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running_or_biking = "biking";
                change_the_layout(1);
                text_in_top_of_running.setText("Biking");
                constraintLayout_for_height.setVisibility(View.GONE);
            }
        });
    }

    private void hide_top_bar() {
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().hide();
        }
    }

    private void start_button_listen() {
        Button button_to_press_on_add_counter = findViewById(R.id.button_to_press_on_add_counter);
        button_to_press_on_add_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_the_layout(2);
                set_all_the_text_at_the_start();
                color_the_text_views();
                color_the_time();
                time_start = System.currentTimeMillis();
                set_up_location();
                save_the_info();
                call_the_handler();
            }
        });
    }

    private void calculate_stuff() {
        TextView pace_to_tell_running_biking = findViewById(R.id.pace_to_tell_running_biking);
        TextView distance_to_tell_running_biking = findViewById(R.id.distance_to_tell_running_biking);
        TextView speed_to_tell_running_biking = findViewById(R.id.speed_to_tell_running_biking);
        TextView calories_to_tell_running_biking = findViewById(R.id.calories_to_tell_running_biking);
        TextView steps_to_tell_running_biking = findViewById(R.id.steps_to_tell_running_biking);
        if (total_distance > 100) {
            if (read_the_shared(0).equals("metric")) {
                float minutes = (System.currentTimeMillis() - time_start) / 1000f;
                float pace = minutes / (total_distance / 1000);
                double roundOff = Math.round(pace * 100.0) / 100.0;
                pace_to_tell_running_biking.setText("Pace: ".concat(String.valueOf(roundOff).concat("minutes per kilometer")));
            } else {
                float minutes = (System.currentTimeMillis() - time_start) / 1000f;
                float pace = minutes / (total_distance / 1609);
                double roundOff = Math.round(pace * 100.0) / 100.0;
                pace_to_tell_running_biking.setText("Pace: ".concat(String.valueOf(roundOff).concat("minutes per mile")));
            }
        } else {
            pace_to_tell_running_biking.setText("Pace: ".concat("calculating"));
        }
        if (read_the_shared(0).equals("metric")) {
            if (total_distance < 100) {
                distance_to_tell_running_biking.setText("Distance: ".concat(String.valueOf(total_distance)).concat(" meters"));
            } else {
                float kilometers = total_distance / 1000;
                double distance_but_cut = Math.round(kilometers * 100.0) / 100.0;
                distance_to_tell_running_biking.setText("Distance: ".concat(String.valueOf(distance_but_cut)).concat(" kilometers"));
            }
        } else {
            float miles = total_distance / 1609;
            double distance_but_cut = Math.round(miles * 100.0) / 100.0;
            distance_to_tell_running_biking.setText("Distance: ".concat(String.valueOf(distance_but_cut)).concat(" miles"));
        }
        if (read_the_shared(0).equals("metric")) {
            float kilometers = total_distance / 1000;
            float hours = (System.currentTimeMillis() - time_start) / 3600000f;
            double speed = Math.round((kilometers / hours) * 100.0) / 100.0;
            speed_to_tell_running_biking.setText("Speed: ".concat(String.valueOf(speed)).concat(" Kilometers/hour"));
        } else {
            float kilometers = total_distance / 1609;
            float hours = (System.currentTimeMillis() - time_start) / 3600000f;
            double speed = Math.round((kilometers / hours) * 100.0) / 100.0;
            speed_to_tell_running_biking.setText("Speed: ".concat(String.valueOf(speed)).concat(" Miles/hour"));
        }
        if (!read_the_shared(2).equals("not_set")) {
            calories_to_tell_running_biking.setVisibility(View.VISIBLE);
            if (time_for_calories == 0) {
                time_for_calories = System.currentTimeMillis();
            } else {
                float minutes = (System.currentTimeMillis() - time_for_calories) / 60000f;
                float weight;
                float met = get_the_met();
                if (read_the_shared(3).equals("kilograms")) {
                    weight = Integer.parseInt(read_the_shared(2));
                } else {
                    weight = Integer.parseInt(read_the_shared(2)) / 2.205f;
                }
                total_calories = total_calories + (minutes * met * weight * 3.5f) / 200;
                calories_to_tell_running_biking.setText("Calories: ".concat(String.valueOf((int) total_calories)));
                time_for_calories = System.currentTimeMillis();
            }
        } else {
            calories_to_tell_running_biking.setVisibility(View.INVISIBLE);
            ConstraintLayout constraintLayout = findViewById(R.id.id_for_layout_in_the_middle_of_running);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(steps_to_tell_running_biking.getId(), ConstraintSet.TOP, speed_to_tell_running_biking.getId(), ConstraintSet.BOTTOM, dip_to_pixels(15));
            constraintSet.applyTo(constraintLayout);
        }
        if (!read_the_shared(1).equals("not_set") && running_or_biking.equals("running")) {
            steps_to_tell_running_biking.setVisibility(View.VISIBLE);
            float height_in_inches = Integer.parseInt(read_the_shared(1)) / 2.54f;
            float stride = height_in_inches * 0.1904f;
            float distance_in_feet = total_distance * 0.03441f;
            int steps = (int) (distance_in_feet / stride);
            steps_to_tell_running_biking.setText("Steps: ".concat(String.valueOf(steps)));
        } else {
            steps_to_tell_running_biking.setVisibility(View.GONE);
        }
    }

    private void three_buttons_listen() {
        Button button_to_add_three_dots = findViewById(R.id.button_to_add_three_dots);
        button_to_add_three_dots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bottom_sheet_to_change_system bottom_sheet_to_change_system = new Bottom_sheet_to_change_system();
                bottom_sheet_to_change_system.show(getSupportFragmentManager(), "system_tag");
            }
        });
    }

    @Override
    public void on_system_choose(String text) {
        SharedPreferences sharedPreferences = getSharedPreferences("Running_biking", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("system", text);
        myEdit.commit();
    }

    private String read_the_shared(int which) {
        SharedPreferences sh = getSharedPreferences("Running_biking", MODE_PRIVATE);
        if (which == 0) {
            return sh.getString("system", "metric");
        } else if (which == 1) {
            return sh.getString("height", "not_set");
        } else if (which == 2) {
            return sh.getString("weight", "not_set");
        } else {
            return sh.getString("units", "not_set");
        }
    }

    private void save_the_info() {
        EditText edit_text_to_get_weight = findViewById(R.id.edit_text_to_get_weight);
        EditText edit_text_to_get_height = findViewById(R.id.edit_text_to_get_height);
        Spinner spinner_to_listen_kilo_or_pound = findViewById(R.id.spinner_to_listen_kilo_or_pound);
        if (!edit_text_to_get_weight.getText().toString().equals("")) {
            SharedPreferences sharedPreferences = getSharedPreferences("Running_biking", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("weight", edit_text_to_get_weight.getText().toString());
            myEdit.commit();
        }
        if (!edit_text_to_get_height.getText().toString().equals("")) {
            SharedPreferences sharedPreferences = getSharedPreferences("Running_biking", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("height", edit_text_to_get_height.getText().toString());
            myEdit.commit();
        }
        if (spinner_to_listen_kilo_or_pound.getSelectedItemPosition() == 0) {
            SharedPreferences sharedPreferences = getSharedPreferences("Running_biking", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("units", "kilograms");
            myEdit.commit();
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("Running_biking", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("units", "pounds");
            myEdit.commit();
        }
    }

    private void set_the_spinner() {
        final ArrayList<String> spinner_list = new ArrayList<>();
        spinner_list.add("Kilograms");
        spinner_list.add("Pounds");
        Spinner spinner_to_listen_kilo_or_pound = findViewById(R.id.spinner_to_listen_kilo_or_pound);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_for_kilo_pound, spinner_list);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_to_listen_kilo_or_pound.setAdapter(arrayAdapter);
    }

    private void set_the_info_at_the_buttom() {
        EditText edit_text_to_get_weight = findViewById(R.id.edit_text_to_get_weight);
        EditText edit_text_to_get_height = findViewById(R.id.edit_text_to_get_height);
        Spinner spinner_to_listen_kilo_or_pound = findViewById(R.id.spinner_to_listen_kilo_or_pound);
        if (!read_the_shared(2).equals("not_set")) {
            edit_text_to_get_weight.setText(read_the_shared(2));
        }
        if (!read_the_shared(1).equals("not_set")) {
            edit_text_to_get_height.setText(read_the_shared(1));
        }
        if (read_the_shared(3).equals("Kilograms")) {
            spinner_to_listen_kilo_or_pound.setSelection(0);
        } else if (read_the_shared(3).equals("Pounds")) {
            spinner_to_listen_kilo_or_pound.setSelection(1);
        }
    }

    private float get_the_met() {
        TextView speed_to_tell_running_biking = findViewById(R.id.speed_to_tell_running_biking);
        float speed;
        if (speed_to_tell_running_biking.getText().toString().contains("Kilometers/hour")) {
            speed = Float.parseFloat(speed_to_tell_running_biking.getText().toString().replace("Speed:", "").replace(" Kilometers/hour", "")) / 1609f;
        } else {
            speed = Float.parseFloat(speed_to_tell_running_biking.getText().toString().replace("Speed:", "").replace(" Miles/hour", ""));
        }
        if (running_or_biking.equals("running")) {
            if (speed <= 4) {
                float percentage = speed/4f;
                return percentage*6;
            } else if (speed <= 5) {
                return 8.3f;
            } else if (speed <= 5.2) {
                return 9f;
            } else if (speed <= 6) {
                return 9.8f;
            } else if (speed <= 6.7) {
                return 10.5f;
            } else if (speed <= 7) {
                return 11f;
            } else if (speed <= 7.5) {
                return 11.5f;
            } else if (speed <= 8) {
                return 11.8f;
            } else if (speed <= 8.6) {
                return 12.3f;
            } else if (speed <= 9) {
                return 12.8f;
            } else if (speed <= 10) {
                return 14.5f;
            } else if (speed <= 11) {
                return 16;
            } else if (speed <= 12) {
                return 19f;
            } else if (speed <= 13) {
                return 19.8f;
            } else {
                return 23f;
            }
        } else {
            if (speed < 5.5) {
                float percentage = speed/5.5f;
                return percentage*3.5f;
            } else if (speed < 9.4) {
                return 5.8f;
            } else if (speed < 12) {
                return 6.8f;
            } else if (speed < 14) {
                return 8;
            } else if (speed < 16) {
                return 10;
            } else if (speed < 19) {
                return 12;
            } else {
                return 15.8f;
            }
        }
    }

    private int dip_to_pixels(float dip) {
        Resources r = getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
    }

    private void color_the_text_views() {
        //TextView time_to_tell_running_biking = findViewById(R.id.time_to_tell_running_biking);
        TextView pace_to_tell_running_biking = findViewById(R.id.pace_to_tell_running_biking);
        TextView distance_to_tell_running_biking = findViewById(R.id.distance_to_tell_running_biking);
        TextView speed_to_tell_running_biking = findViewById(R.id.speed_to_tell_running_biking);
        TextView calories_to_tell_running_biking = findViewById(R.id.calories_to_tell_running_biking);
        TextView steps_to_tell_running_biking = findViewById(R.id.steps_to_tell_running_biking);
        if (pace_to_tell_running_biking.getText().toString().contains("calculating")) {
            Spannable color_me = new SpannableString(pace_to_tell_running_biking.getText().toString());
            color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 6, pace_to_tell_running_biking.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            pace_to_tell_running_biking.setText(color_me);
        } else {
            String remove_pace = pace_to_tell_running_biking.getText().toString().replace("pace ", "");
            String[] split_on_word = remove_pace.split(" per ");
            Spannable color_me = new SpannableString(pace_to_tell_running_biking.getText().toString());
            color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 6, 6 + split_on_word[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), pace_to_tell_running_biking.getText().toString().length() - split_on_word[1].length(), pace_to_tell_running_biking.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            pace_to_tell_running_biking.setText(color_me);
        }
        if (distance_to_tell_running_biking.getText().toString().contains("miles")) {
            String text_without_extra = distance_to_tell_running_biking.getText().toString().replace("Distance: ", "").replace("miles", "");
            String[] split_on_dot = text_without_extra.split("\\.");
            Spannable color_me = new SpannableString(distance_to_tell_running_biking.getText().toString());
            color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 10, 10 + split_on_dot[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 10 + split_on_dot[0].length() + 1, 10 + split_on_dot[0].length() + 1 + split_on_dot[1].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            distance_to_tell_running_biking.setText(color_me);
        } else if (distance_to_tell_running_biking.getText().toString().contains("kilometers")) {
            String text_without_extra = distance_to_tell_running_biking.getText().toString().replace("Distance: ", "").replace("kilometers", "");
            String[] split_on_dot = text_without_extra.split("\\.");
            Spannable color_me = new SpannableString(distance_to_tell_running_biking.getText().toString());
            color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 10, 10 + split_on_dot[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 10 + split_on_dot[0].length() + 1, 10 + split_on_dot[0].length() + 1 + split_on_dot[1].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            distance_to_tell_running_biking.setText(color_me);
        } else if (distance_to_tell_running_biking.getText().toString().contains("meters")) {
            String text_without_extra = distance_to_tell_running_biking.getText().toString().replace("Distance: ", "").replace("meters", "");
            String[] split_on_dot = text_without_extra.split("\\.");
            Spannable color_me = new SpannableString(distance_to_tell_running_biking.getText().toString());
            color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 10, 10 + split_on_dot[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 10 + split_on_dot[0].length() + 1, 10 + split_on_dot[0].length() + 1 + split_on_dot[1].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            distance_to_tell_running_biking.setText(color_me);
        }
        if (speed_to_tell_running_biking.getText().toString().contains("Kilometers/hour")) {
            String text_without_extra = speed_to_tell_running_biking.getText().toString().replace("Speed: ", "").replace("Kilometers/hour", "");
            String[] split_on_dot = text_without_extra.split("\\.");
            Spannable color_me = new SpannableString(speed_to_tell_running_biking.getText().toString());
            color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 7, 7 + split_on_dot[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 7 + split_on_dot[0].length() + 1, 7 + split_on_dot[0].length() + 1 + split_on_dot[1].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            speed_to_tell_running_biking.setText(color_me);
        } else if (speed_to_tell_running_biking.getText().toString().contains("Miles/hour")) {
            String text_without_extra = speed_to_tell_running_biking.getText().toString().replace("Speed: ", "").replace("Miles/hour", "");
            String[] split_on_dot = text_without_extra.split("\\.");
            Spannable color_me = new SpannableString(speed_to_tell_running_biking.getText().toString());
            color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 7, 7 + split_on_dot[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 7 + split_on_dot[0].length() + 1, 7 + split_on_dot[0].length() + 1 + split_on_dot[1].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            speed_to_tell_running_biking.setText(color_me);
        }
        if (calories_to_tell_running_biking.getVisibility() == View.VISIBLE) {
            Spannable color_me = new SpannableString(calories_to_tell_running_biking.getText().toString());
            color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 10, calories_to_tell_running_biking.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            calories_to_tell_running_biking.setText(color_me);
        }
        if (steps_to_tell_running_biking.getVisibility() == View.VISIBLE) {
            Spannable color_me = new SpannableString(steps_to_tell_running_biking.getText().toString());
            color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 7, steps_to_tell_running_biking.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            steps_to_tell_running_biking.setText(color_me);
        }
    }

    private void color_the_time() {
        TextView time_to_tell_running_biking = findViewById(R.id.time_to_tell_running_biking);
        String remove_extra = time_to_tell_running_biking.getText().toString().replace("Time: ", "");
        String[] split_on_dots = remove_extra.split(":");
        if (split_on_dots.length == 3) {
            Spannable color_me = new SpannableString(time_to_tell_running_biking.getText().toString());
            color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 6, 6 + split_on_dots[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 6 + 1 + split_on_dots[0].length(), 6 + 1 + split_on_dots[0].length() + split_on_dots[1].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 6 + 1 + split_on_dots[0].length() + split_on_dots[1].length() + 1, 6 + 1 + split_on_dots[0].length() + split_on_dots[1].length() + split_on_dots[2].length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            time_to_tell_running_biking.setText(color_me);
        } else {
            Spannable color_me = new SpannableString(time_to_tell_running_biking.getText().toString());
            color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 6, 6 + split_on_dots[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 6 + 1 + split_on_dots[0].length(), 6 + 1 + split_on_dots[0].length() + split_on_dots[1].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            time_to_tell_running_biking.setText(color_me);
        }
    }

    private void call_the_handler() {
        handler.postDelayed(new Runnable() {
            public void run() {
                update_the_time();
                color_the_time();
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    private void update_the_time() {
        TextView time_to_tell_running_biking = findViewById(R.id.time_to_tell_running_biking);
        long millis = System.currentTimeMillis() - time_start;
        if (millis > 3600000) {
            long hoursMath = millis / 3600000;
            long minutesMath = (millis % 3600000) / 60000;
            long secondsMath = (millis % 60000) / 1000;
            String outMath = String.format(Locale.getDefault(), "%02d:%02d:%02d", hoursMath, minutesMath, secondsMath);
            time_to_tell_running_biking.setText("Time: ".concat(outMath));
        } else {
            long minutesMath = (millis % 3600000) / 60000;
            long secondsMath = (millis % 60000) / 1000;
            String outMath = String.format(Locale.getDefault(), "%02d:%02d", minutesMath, secondsMath);
            time_to_tell_running_biking.setText("Time: ".concat(outMath));
        }
    }

    private void finish_button_listen() {
        final Button finish_button_in_running_or_biking = findViewById(R.id.finish_button_in_running_or_biking);
        final TextView texting_saying_congrats_in_running = findViewById(R.id.texting_saying_congrats_in_running);
        finish_button_in_running_or_biking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (finish_button_in_running_or_biking.getText().toString().equals(" Finish ")) {
                    fusedLocationProviderClient.removeLocationUpdates(locationCallback);
                    handler.removeCallbacksAndMessages(null);
                    fusedLocationProviderClient.flushLocations();
                    texting_saying_congrats_in_running.setVisibility(View.VISIBLE);
                    Spannable color_me = new SpannableString("Congrats!! You did it");
                    color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 0, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 11, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    texting_saying_congrats_in_running.setText(color_me);
                    finish_button_in_running_or_biking.setTextColor(Color.parseColor("#607D8B"));
                    finish_button_in_running_or_biking.setText(" Let's go ");
                } else {
                    texting_saying_congrats_in_running.setVisibility(View.INVISIBLE);
                    finish_button_in_running_or_biking.setTextColor(Color.parseColor("#DC143C"));
                    finish_button_in_running_or_biking.setText(" Finish ");
                    restart_everything();
                    change_the_layout(0);
                }
            }
        });
    }

    private void restart_everything() {
        TextView text_in_top_of_running = findViewById(R.id.text_in_top_of_running);
        last_location = null;
        total_distance = 0;
        time_for_calories = 0;
        total_calories = 0;
        text_in_top_of_running.setText("Running / Biking");
    }

    private void set_all_the_text_at_the_start() {
        TextView time_to_tell_running_biking = findViewById(R.id.time_to_tell_running_biking);
        TextView pace_to_tell_running_biking = findViewById(R.id.pace_to_tell_running_biking);
        TextView distance_to_tell_running_biking = findViewById(R.id.distance_to_tell_running_biking);
        TextView speed_to_tell_running_biking = findViewById(R.id.speed_to_tell_running_biking);
        TextView calories_to_tell_running_biking = findViewById(R.id.calories_to_tell_running_biking);
        TextView steps_to_tell_running_biking = findViewById(R.id.steps_to_tell_running_biking);
        time_to_tell_running_biking.setText("Time: 00:00");
        pace_to_tell_running_biking.setText("Pace: calculating");
        distance_to_tell_running_biking.setText("Distance: 0.0 meters");
        if (read_the_shared(0).equals("metric")) {
            speed_to_tell_running_biking.setText("Speed: 0.0 Kilometers/hour");
        } else {
            speed_to_tell_running_biking.setText("Speed: 0.0 Miles/hour");
        }
        if (!read_the_shared(2).equals("not_set")) {
            calories_to_tell_running_biking.setVisibility(View.VISIBLE);
            calories_to_tell_running_biking.setText("Calories: 0");
        } else {
            calories_to_tell_running_biking.setVisibility(View.INVISIBLE);
            ConstraintLayout constraintLayout = findViewById(R.id.id_for_layout_in_the_middle_of_running);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(steps_to_tell_running_biking.getId(), ConstraintSet.TOP, speed_to_tell_running_biking.getId(), ConstraintSet.BOTTOM, dip_to_pixels(15));
            constraintSet.applyTo(constraintLayout);
        }
        if (!read_the_shared(1).equals("not_set") && running_or_biking.equals("running")) {
            steps_to_tell_running_biking.setVisibility(View.VISIBLE);
            steps_to_tell_running_biking.setText("Steps: 0");
        } else {
            steps_to_tell_running_biking.setVisibility(View.GONE);
        }
    }
}
*/