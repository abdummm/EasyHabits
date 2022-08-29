package com.easyhabitsapp.android;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.List;

public class Lock_phone_service extends Service {
    private Handler handler = new Handler();
    private WindowManager windowManager;
    private ConstraintLayout constraintLayout;
    private ConstraintSet set;
    private View horizontol_line_one;
    private View horizontol_line_two;
    private View vertical_line_one;
    private View vertical_line_two;
    private View main_view;
    private View top_of_dialog;
    private TextView view_to_tell_app_is_locked;
    private TextView text_time_remaining;
    private TextView white_text_up;
    private ArrayList<String> white_list;
    private float horizontol_line_one_bias;
    private float horizontol_line_two_bias;
    private float vertical_line_one_bias;
    private float vertical_line_two_bias;
    private boolean is_the_activity_running;


    @Override
    public void onCreate() {
        super.onCreate();
        is_the_activity_running = false;
        Intent notificationIntent = new Intent(this, Locking_the_screen.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
        Notification notification = new NotificationCompat.Builder(this, "lock_phone_service_channel")
                .setContentTitle("Phone is locked")
                .setContentText("locking phone")
                .setSmallIcon(R.drawable.round_chat_24)
                .setContentIntent(pendingIntent)
                .setOnlyAlertOnce(true)
                .build();
        startForeground(99999, notification);
        constraintLayout = new ConstraintLayout(this);
        ConstraintLayout.LayoutParams layoutParams_for_layout = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
        constraintLayout.setLayoutParams(layoutParams_for_layout);
        constraintLayout.setBackgroundColor(Color.parseColor("#40000000"));
        set = new ConstraintSet();
        set.clone(constraintLayout);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        create_ids();
        horizontol_line_one_bias = 0.375f;
        horizontol_line_two_bias = 0.625f;
        vertical_line_one_bias = 0.2f;
        vertical_line_two_bias = 0.8f;

        create_a_constraint();
        white_list = new ArrayList<>();
        setWhite_list();
        add_the_to_the_white_list();
        set_the_service_text();
        call_the_method_every_second();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    private void call_the_method_every_second() {
        handler.postDelayed(new Runnable() {
            public void run() {
                set_the_service_text();
                check_if_time_is_up();
                change_the_text();
                put_or_remove();
                checking_the_redirect();
                if (!Get_the_time.open_the_file(getApplicationContext()).equals("00:00")) {
                    handler.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }

    private void set_the_service_text() {
        Intent notificationIntent = new Intent(this, Locking_the_screen.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
        Notification notification = new NotificationCompat.Builder(this, "lock_phone_service_channel")
                .setContentTitle("Phone is locked")
                .setContentText(Get_the_time.open_the_file(this))
                .setSmallIcon(R.drawable.round_chat_24)
                .setContentIntent(pendingIntent)
                .setOnlyAlertOnce(true)
                .build();
        startForeground(99999, notification);
    }


    private void check_if_time_is_up() {
        if (Get_the_time.open_the_file(this).equals("00:00")) {
            deal_with_window("remove");
            SharedPreferences sharedPreferences = getSharedPreferences("one_minute_emergency", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.remove("one_minute_time");
            myEdit.apply();
           stopSelf();
        }
    }

    private void create_a_constraint() {

        set.constrainWidth(horizontol_line_one.getId(), 0);
        set.constrainHeight(horizontol_line_one.getId(), 1);
        set.connect(horizontol_line_one.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
        set.connect(horizontol_line_one.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        set.connect(horizontol_line_one.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
        set.connect(horizontol_line_one.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
        set.setVerticalBias(horizontol_line_one.getId(), horizontol_line_one_bias);
        horizontol_line_one.setBackgroundColor(Color.TRANSPARENT);

        set.constrainHeight(horizontol_line_two.getId(), 1);
        set.constrainWidth(horizontol_line_two.getId(), 0);
        set.connect(horizontol_line_two.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
        set.connect(horizontol_line_two.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        set.connect(horizontol_line_two.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
        set.connect(horizontol_line_two.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
        set.setVerticalBias(horizontol_line_two.getId(), horizontol_line_two_bias);
        horizontol_line_two.setBackgroundColor(Color.TRANSPARENT);

        set.constrainWidth(vertical_line_one.getId(), 1);
        set.constrainHeight(vertical_line_one.getId(), 0);
        set.connect(vertical_line_one.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
        set.connect(vertical_line_one.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        set.connect(vertical_line_one.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
        set.connect(vertical_line_one.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
        set.setHorizontalBias(vertical_line_one.getId(), vertical_line_one_bias);
        vertical_line_one.setBackgroundColor(Color.TRANSPARENT);

        set.constrainWidth(vertical_line_two.getId(), 1);
        set.constrainHeight(vertical_line_two.getId(), 0);
        set.connect(vertical_line_two.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
        set.connect(vertical_line_two.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        set.connect(vertical_line_two.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
        set.connect(vertical_line_two.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
        set.setHorizontalBias(vertical_line_two.getId(), vertical_line_two_bias);
        vertical_line_two.setBackgroundColor(Color.TRANSPARENT);

        set.constrainHeight(main_view.getId(), ConstraintSet.MATCH_CONSTRAINT);
        set.constrainWidth(main_view.getId(), ConstraintSet.MATCH_CONSTRAINT);
        set.connect(main_view.getId(), ConstraintSet.TOP, horizontol_line_one.getId(), ConstraintSet.BOTTOM, 0);
        set.connect(main_view.getId(), ConstraintSet.START, vertical_line_one.getId(), ConstraintSet.END, 0);
        set.connect(main_view.getId(), ConstraintSet.END, vertical_line_two.getId(), ConstraintSet.START, 0);
        set.connect(main_view.getId(), ConstraintSet.BOTTOM, horizontol_line_two.getId(), ConstraintSet.TOP, 0);
        main_view.setBackgroundResource(R.drawable.white_list_with_stroke);

        set.constrainWidth(top_of_dialog.getId(), 0);
        set.constrainHeight(top_of_dialog.getId(), dip_to_pixels(50));
        set.connect(top_of_dialog.getId(), ConstraintSet.TOP, main_view.getId(), ConstraintSet.TOP, 0);
        set.connect(top_of_dialog.getId(), ConstraintSet.START, main_view.getId(), ConstraintSet.START, 0);
        set.connect(top_of_dialog.getId(), ConstraintSet.END, main_view.getId(), ConstraintSet.END, 0);
        top_of_dialog.setBackgroundResource(R.drawable.removing_corners_for_view);

        set.constrainHeight(view_to_tell_app_is_locked.getId(), ViewGroup.LayoutParams.WRAP_CONTENT);
        set.constrainWidth(view_to_tell_app_is_locked.getId(), ViewGroup.LayoutParams.WRAP_CONTENT);
        set.connect(view_to_tell_app_is_locked.getId(), ConstraintSet.TOP, top_of_dialog.getId(), ConstraintSet.BOTTOM, 0);
        set.connect(view_to_tell_app_is_locked.getId(), ConstraintSet.START, main_view.getId(), ConstraintSet.START, 0);
        set.connect(view_to_tell_app_is_locked.getId(), ConstraintSet.END, main_view.getId(), ConstraintSet.END, 0);
        set.connect(view_to_tell_app_is_locked.getId(), ConstraintSet.BOTTOM, main_view.getId(), ConstraintSet.BOTTOM, 0);
        view_to_tell_app_is_locked.setText("App is locked");
        view_to_tell_app_is_locked.setTextColor(Color.parseColor("#607D8B"));
        view_to_tell_app_is_locked.setTextSize(20);
        view_to_tell_app_is_locked.setTypeface(Typeface.DEFAULT_BOLD);
        set.setVerticalBias(view_to_tell_app_is_locked.getId(), 0.3f);

        set.constrainHeight(text_time_remaining.getId(), ViewGroup.LayoutParams.WRAP_CONTENT);
        set.constrainWidth(text_time_remaining.getId(), ViewGroup.LayoutParams.WRAP_CONTENT);
        set.connect(text_time_remaining.getId(), ConstraintSet.TOP, top_of_dialog.getId(), ConstraintSet.BOTTOM, 0);
        set.connect(text_time_remaining.getId(), ConstraintSet.START, main_view.getId(), ConstraintSet.START, 0);
        set.connect(text_time_remaining.getId(), ConstraintSet.END, main_view.getId(), ConstraintSet.END, 0);
        set.connect(text_time_remaining.getId(), ConstraintSet.BOTTOM, main_view.getId(), ConstraintSet.BOTTOM, 0);
        text_time_remaining.setText("00:00");
        text_time_remaining.setTextColor(Color.parseColor("#607D8B"));
        text_time_remaining.setTextSize(20);
        text_time_remaining.setTypeface(Typeface.DEFAULT_BOLD);
        set.setVerticalBias(text_time_remaining.getId(), 0.7f);

        set.constrainHeight(white_text_up.getId(), ViewGroup.LayoutParams.WRAP_CONTENT);
        set.constrainWidth(white_text_up.getId(), ViewGroup.LayoutParams.WRAP_CONTENT);
        set.connect(white_text_up.getId(), ConstraintSet.TOP, top_of_dialog.getId(), ConstraintSet.TOP, 0);
        set.connect(white_text_up.getId(), ConstraintSet.START, top_of_dialog.getId(), ConstraintSet.START, 0);
        set.connect(white_text_up.getId(), ConstraintSet.END, top_of_dialog.getId(), ConstraintSet.END, 0);
        set.connect(white_text_up.getId(), ConstraintSet.BOTTOM, top_of_dialog.getId(), ConstraintSet.BOTTOM, 0);
        white_text_up.setText("Locked");
        white_text_up.setTextColor(Color.WHITE);
        white_text_up.setTextSize(20);
        white_text_up.setTypeface(Typeface.DEFAULT_BOLD);
        set.applyTo(constraintLayout);
    }

    private int dip_to_pixels(float dip) {
        Resources r = getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
    }

    private void create_ids() {
        horizontol_line_one = new View(this);
        horizontol_line_one.setId(View.generateViewId());
        horizontol_line_two = new View(this);
        horizontol_line_two.setId(View.generateViewId());
        vertical_line_one = new View(this);
        vertical_line_one.setId(View.generateViewId());
        vertical_line_two = new View(this);
        vertical_line_two.setId(View.generateViewId());
        main_view = new View(this);
        main_view.setId(View.generateViewId());
        top_of_dialog = new View(this);
        top_of_dialog.setId(View.generateViewId());
        view_to_tell_app_is_locked = new TextView(this);
        view_to_tell_app_is_locked.setId(View.generateViewId());
        text_time_remaining = new TextView(this);
        text_time_remaining.setId(View.generateViewId());
        white_text_up = new TextView(this);
        white_text_up.setId(View.generateViewId());
        white_text_up = new TextView(this);
        white_text_up.setId(View.generateViewId());
        constraintLayout.addView(horizontol_line_one);
        constraintLayout.addView(horizontol_line_two);
        constraintLayout.addView(vertical_line_one);
        constraintLayout.addView(vertical_line_two);
        constraintLayout.addView(main_view);
        constraintLayout.addView(top_of_dialog);
        constraintLayout.addView(view_to_tell_app_is_locked);
        constraintLayout.addView(text_time_remaining);
        constraintLayout.addView(white_text_up);
    }

    private void change_the_text() {
        if (constraintLayout.isShown()) {
            text_time_remaining.setText(Get_the_time.open_the_file(this));
            view_to_tell_app_is_locked.setText(check_the_name());
        }
    }

    private void put_or_remove() {
        if (white_list.contains(getForegroundApp())) {
            deal_with_window("remove");
        } else {
            deal_with_window("put");
        }
    }

    private void deal_with_window(String what) {
        if (what.equals("put")) {
            if (Get_the_time.return_the_seconds(this) > 5 && get_the_shared()) {
                if (!constraintLayout.isShown()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        WindowManager.LayoutParams params = new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
                        params.x = 0;
                        params.y = 0;
                        params.gravity = Gravity.CENTER;
                        windowManager.addView(constraintLayout, params);
                        change_the_text();
                    } else {
                        WindowManager.LayoutParams params = new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_TOAST, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
                        params.x = 0;
                        params.y = 0;
                        params.gravity = Gravity.CENTER;
                        windowManager.addView(constraintLayout, params);
                        change_the_text();
                    }
                }
            }
        } else {
            if (constraintLayout.isShown()) {
                windowManager.removeView(constraintLayout);
            }
        }
    }

    private void setWhite_list() {
        List<ApplicationInfo> packages = this.getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
        PackageManager packageManager = getPackageManager();
        for (ApplicationInfo packageInfo : packages) {
            if (packageManager.getLaunchIntentForPackage(packageInfo.packageName) == null) {
                white_list.add(packageInfo.packageName);
            }
        }
        white_list.add("null");
        white_list.add(BuildConfig.APPLICATION_ID);
        white_list.add("com.google.android.googlequicksearchbox");
    }

    public String getForegroundApp() {
        String foregroundApp = "null";
        UsageStatsManager mUsageStatsManager = (UsageStatsManager) this.getSystemService(Service.USAGE_STATS_SERVICE);
        long time = System.currentTimeMillis();
        UsageEvents usageEvents = mUsageStatsManager.queryEvents(time - 1000 * 3600, time);
        UsageEvents.Event event = new UsageEvents.Event();
        while (usageEvents.hasNextEvent()) {
            usageEvents.getNextEvent(event);
            if (event.getEventType() == UsageEvents.Event.MOVE_TO_FOREGROUND) {
                foregroundApp = event.getPackageName();
            }
        }
        return foregroundApp;
    }

    private int spToPx(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, this.getResources().getDisplayMetrics());
    }
    private String check_the_name(){
        String packageName = getForegroundApp();
        PackageManager packageManager= getApplicationContext().getPackageManager();
        String appName;
        try {
            appName = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA));
        } catch (PackageManager.NameNotFoundException e) {
            appName = "App";
            e.printStackTrace();
        }
        TextPaint paint = new TextPaint();
        paint.setTextSize(spToPx(20));
        float width = paint.measureText(appName.concat(" is locked"));
        if (width+dip_to_pixels(10)<return_the_width()){
            return appName.concat(" is locked");
        }else{
            return "App is locked";
        }
    }
    private int return_the_width(){
        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return (int) (0.6 * metrics.widthPixels);
        } else {
            return (int) (0.35 * metrics.widthPixels);
        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        int newOrientation = newConfig.orientation;

        if (newOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            vertical_line_one_bias = 0.325f;
            vertical_line_two_bias = 0.675f;
            horizontol_line_one_bias = 0.28f;
            horizontol_line_two_bias = 0.72f;
        } else {
            horizontol_line_one_bias = 0.375f;
            horizontol_line_two_bias = 0.625f;
            vertical_line_one_bias = 0.2f;
            vertical_line_two_bias = 0.8f;
        }
        create_a_constraint();
    }
    private boolean get_the_shared(){
        SharedPreferences sharedPreferences = getSharedPreferences("one_minute_emergency", MODE_PRIVATE);
        long time_in_future = sharedPreferences.getLong("one_minute_time",-1);
        long time_left = time_in_future - System.currentTimeMillis();
        if(time_left>900000){
           return false;
        } else {
            return true;
        }
    }
    private boolean read_the_state_of_ring(){
        SharedPreferences sharedPreferences = getSharedPreferences("check_box", MODE_PRIVATE);
        return sharedPreferences.getBoolean("check_box_state", false);
    }
    private void checking_the_redirect(){
        if(read_the_state_of_ring()&& Get_the_time.return_the_seconds(this)<=5&&!Locking_the_screen.is_the_lock_screen_active&&!is_the_activity_running){
            is_the_activity_running = true;
            Intent start_intent = new Intent(this, Locking_the_screen.class);
            start_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(start_intent);
            stopSelf();
        }
    }
    private void add_the_to_the_white_list(){
        SharedPreferences sharedPreferences = getSharedPreferences("send_the_apps", MODE_PRIVATE);
        String list_of_apps = sharedPreferences.getString("send_apps", "");
        if(!list_of_apps.equals("")){
            String[] split_the_app = list_of_apps.split("_split_max_here_");
            for(int i =0;i<split_the_app.length;i++){
                String[] split_again = split_the_app[i].split("_split_here_");
                white_list.add(split_again[1]);
            }
        }
    }
}
