package com.easyhabitsapp.android;

import android.app.AppOpsManager;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class phone_usage_emergency extends AppCompatActivity {
    private long app_for_ground = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_usage_emergency);
        hide_top_bar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        get_the_time_of_the_apps();
        back_button_listen();
        lets_go_button();
        color_the_text_in_the_top();
        set_all_the_text();
        share_button_lsiten();
        if(!did_the_user_check()){
            Dialog_to_tell_user dialog_to_telluser = new Dialog_to_tell_user();
            dialog_to_telluser.show(getSupportFragmentManager(), "dialog_tag");
        }
    }

    private void get_the_time_of_the_apps() {
        long start_time = System.currentTimeMillis() - 7 * 86400000;
        long end_time = System.currentTimeMillis();
        UsageEvents.Event currentEvent;
        //  List<UsageEvents.Event> allEvents = new ArrayList<>();
        HashMap<String, AppUsageInfo> map = new HashMap<>();
        HashMap<String, List<UsageEvents.Event>> sameEvents = new HashMap<>();
        UsageStatsManager mUsageStatsManager;
        mUsageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        final PackageManager packageManager = getPackageManager(); //get a list of installed apps.
        if (mUsageStatsManager != null) {
            // Get all apps data from starting time to end time
            UsageEvents usageEvents = mUsageStatsManager.queryEvents(start_time, end_time);

            // Put these data into the map
            while (usageEvents.hasNextEvent()) {
                currentEvent = new UsageEvents.Event();
                usageEvents.getNextEvent(currentEvent);
                if (currentEvent.getEventType() == UsageEvents.Event.ACTIVITY_RESUMED ||
                        currentEvent.getEventType() == UsageEvents.Event.ACTIVITY_PAUSED) {
                    //  allEvents.add(currentEvent);
                    String key = currentEvent.getPackageName();
                    if (map.get(key) == null) {
                        map.put(key, new AppUsageInfo(key));
                        sameEvents.put(key, new ArrayList<UsageEvents.Event>());
                    }
                    sameEvents.get(key).add(currentEvent);
                }
            }

            // Traverse through each app data which is grouped together and count launch, calculate duration
            for (Map.Entry<String, List<UsageEvents.Event>> entry : sameEvents.entrySet()) {
                int totalEvents = entry.getValue().size();
                if (totalEvents > 1) {
                    for (int i = 0; i < totalEvents - 1; i++) {
                        UsageEvents.Event E0 = entry.getValue().get(i);
                        UsageEvents.Event E1 = entry.getValue().get(i + 1);

                        if (E1.getEventType() == 1 || E0.getEventType() == 1) {
                            map.get(E1.getPackageName()).launchCount++;
                        }

                        if (E0.getEventType() == 1 && E1.getEventType() == 2) {
                            long diff = E1.getTimeStamp() - E0.getTimeStamp();
                            map.get(E0.getPackageName()).timeInForeground += diff;
                        }
                    }
                }

                // If First eventtype is ACTIVITY_PAUSED then added the difference of start_time and Event occuring time because the application is already running.
                if (entry.getValue().get(0).getEventType() == 2) {
                    long diff = entry.getValue().get(0).getTimeStamp() - start_time;
                    map.get(entry.getValue().get(0).getPackageName()).timeInForeground += diff;
                }

                // If Last eventtype is ACTIVITY_RESUMED then added the difference of end_time and Event occuring time because the application is still running .
                if (entry.getValue().get(totalEvents - 1).getEventType() == 1) {
                    long diff = end_time - entry.getValue().get(totalEvents - 1).getTimeStamp();
                    map.get(entry.getValue().get(totalEvents - 1).getPackageName()).timeInForeground += diff;
                }
            }

            ArrayList<AppUsageInfo> smallInfoList = new ArrayList<>(map.values());

            // Concatenating data to show in a text view. You may do according to your requirement
            for (AppUsageInfo appUsageInfo : smallInfoList) {
                // Do according to your requirement
                if (packageManager.getLaunchIntentForPackage(appUsageInfo.packageName) != null) {
                    app_for_ground = app_for_ground + appUsageInfo.timeInForeground;
                }
            }

        } else {
            Toast.makeText(this, "Sorry can't load information", Toast.LENGTH_SHORT).show();
        }
    }

    private void hide_top_bar() {
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().hide();
        }
    }

    private void back_button_listen() {
        Button button_to_go_back_counter = findViewById(R.id.button_to_go_back_counter);
        button_to_go_back_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phone_usage_emergency.this, after_login.class);
                intent.putExtra("Start_the_emergency_true", true);
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void lets_go_button() {
        Button button_lets_go_to_go_back_to_emergency = findViewById(R.id.button_lets_go_to_go_back_to_emergency);
        button_lets_go_to_go_back_to_emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(phone_usage_emergency.this, after_login.class);
                intent.putExtra("Start_the_emergency_true", true);
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void color_the_text_in_the_top() {
        TextView text_saying_the_phone_usage_in_the_top = findViewById(R.id.text_saying_the_phone_usage_in_the_top);
        Spannable color_me = new SpannableString("Total phone usage in the past 7 days");
        color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), 12, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        color_me.setSpan(new ForegroundColorSpan(Color.parseColor("#607D8B")), text_saying_the_phone_usage_in_the_top.getText().toString().length() - 6, text_saying_the_phone_usage_in_the_top.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_saying_the_phone_usage_in_the_top.setText(color_me);
    }

    private void set_all_the_text() {
        TextView view_showing_number_of_hours = findViewById(R.id.view_showing_number_of_hours);
        TextView days_under_hours_phone_usage = findViewById(R.id.days_under_hours_phone_usage);
        TextView minutes_under_hours_phone_usage = findViewById(R.id.minutes_under_hours_phone_usage);
        TextView seconds_under_hours_phone_usage = findViewById(R.id.seconds_under_hours_phone_usage);
        view_showing_number_of_hours.setText(String.valueOf(TimeUnit.MILLISECONDS.toHours(app_for_ground)));
        double days_double = app_for_ground / 86400000d;
        days_under_hours_phone_usage.setText(String.format("%.2f", days_double));
        minutes_under_hours_phone_usage.setText(String.valueOf(TimeUnit.MILLISECONDS.toMinutes(app_for_ground)));
        seconds_under_hours_phone_usage.setText(String.valueOf(TimeUnit.MILLISECONDS.toSeconds(app_for_ground)));
    }

    private void share_button_lsiten() {
        final Button button_to_sahre_phone_usage = findViewById(R.id.button_to_sahre_phone_usage);
        final Button button_lets_go_to_go_back_to_emergency = findViewById(R.id.button_lets_go_to_go_back_to_emergency);
        final View back_button_in_the_journal_emergency = findViewById(R.id.back_button_in_the_journal_emergency);
        final Button button_to_go_back_counter = findViewById(R.id.button_to_go_back_counter);
        final ConstraintLayout layout_showing_phone_usage = findViewById(R.id.layout_showing_phone_usage);
        final TextView text_saying_name_of_the_app_in_the_phone_usage = findViewById(R.id.text_saying_name_of_the_app_in_the_phone_usage);
        button_to_sahre_phone_usage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_to_sahre_phone_usage.setVisibility(View.INVISIBLE);
                button_lets_go_to_go_back_to_emergency.setVisibility(View.INVISIBLE);
                back_button_in_the_journal_emergency.setVisibility(View.INVISIBLE);
                button_to_go_back_counter.setVisibility(View.INVISIBLE);
                text_saying_name_of_the_app_in_the_phone_usage.setVisibility(View.VISIBLE);
                share_the_bitmap(screenShot(layout_showing_phone_usage));
                button_to_sahre_phone_usage.setVisibility(View.VISIBLE);
                button_lets_go_to_go_back_to_emergency.setVisibility(View.VISIBLE);
                back_button_in_the_journal_emergency.setVisibility(View.VISIBLE);
                button_to_go_back_counter.setVisibility(View.VISIBLE);
                text_saying_name_of_the_app_in_the_phone_usage.setVisibility(View.INVISIBLE);
            }
        });
    }

    public Bitmap screenShot(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    private void share_the_bitmap(Bitmap bitmap) {
        // save bitmap to cache directory
        try {
            File cachePath = new File(this.getCacheDir(), "images");
            cachePath.mkdirs(); // don't forget to make the directory
            FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        File imagePath = new File(this.getCacheDir(), "images");
        File newFile = new File(imagePath, "image.png");
        Uri contentUri = FileProvider.getUriForFile(this, "com.easyhabitsapp.android.fileprovider", newFile);

        if (contentUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
            shareIntent.setDataAndType(contentUri, this.getContentResolver().getType(contentUri));
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            startActivity(Intent.createChooser(shareIntent, "Share"));
        }
    }

    private boolean did_the_user_check(){
        AppOpsManager appOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
        boolean granted = false;
        if (appOps!=null) {
            int mode = appOps.checkOpNoThrow("android:get_usage_stats", android.os.Process.myUid(), getPackageName());
            granted = mode == AppOpsManager.MODE_ALLOWED;
        }
        return granted;
    }
}