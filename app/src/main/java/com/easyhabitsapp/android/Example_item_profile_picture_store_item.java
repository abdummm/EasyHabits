package com.easyhabitsapp.android;

import android.graphics.drawable.Drawable;
import android.view.View;

public class Example_item_profile_picture_store_item {
    private String name;
    private String description;
    private Drawable icon;
    private String rarity;
    private int color;
    private String price;
    private int total_limited_items;
    private int unique_limited_number;
    private String type;

    public Example_item_profile_picture_store_item(String name, String description, Drawable icon, String rarity, int color, String price, int total_limited_items, int unique_limited_number) {
        this.name = name;
        this.icon = icon;
        this.rarity = rarity;
        this.color = color;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getRarity() {
        return rarity;
    }

    public int getColor() {
        return color;
    }

    public String getPrice() {
        return price;
    }

    public int getTotal_limited_items() {
        return total_limited_items;
    }

    public int getUnique_limited_number() {
        return unique_limited_number;
    }

    public String getType() {
        return type;
    }
}
