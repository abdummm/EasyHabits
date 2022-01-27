package com.easyhabitsapp.android;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class Am_i_paid extends Application {
    private Context context;
    public Am_i_paid(Context context){
        this.context = context;
    }
    public boolean did_user_pay() {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("is_user_gifted", MODE_PRIVATE);
            boolean online = sharedPreferences.getBoolean("premuim", false);
            boolean offline = sharedPreferences.getBoolean("premuim_online", false);
            if (online || offline) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}
