package com.easyhabitsapp.android;

import android.graphics.drawable.Drawable;

public class Model_for_emergency_options {
    String title;
    Drawable icon;
    public Model_for_emergency_options(String title,Drawable icon){
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
