package com.easyhabitsapp.android;

import android.graphics.drawable.Drawable;

public class Example_item_emergency {
    private int color;
    private String text;
    private Drawable drawable;
    private boolean entry_today;

    public Example_item_emergency(int color, String text, Drawable drawable) {
        this.color = color;
        this.text = text;
        this.drawable = drawable;
    }
    public Example_item_emergency(int color, String text, Drawable drawable,boolean entry_today) {
        this.color = color;
        this.text = text;
        this.drawable = drawable;
        this.entry_today = entry_today;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public boolean isEntry_today() {
        return entry_today;
    }

    public void setEntry_today(boolean entry_today) {
        this.entry_today = entry_today;
    }
}
