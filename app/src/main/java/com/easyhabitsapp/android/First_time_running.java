package com.easyhabitsapp.android;

import android.app.Application;

public class First_time_running extends Application {
    boolean restart = true;
    public void update_restart(boolean value){
        restart = value;
    }
    public boolean return_restart(){
        return restart;
    }
}
