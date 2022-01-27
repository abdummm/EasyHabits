package com.easyhabitsapp.android;

public class Color_item {
    private String mcolor_name;
    private int mcolor_image;
    public Color_item(String color_name,int color_image){
        mcolor_name = color_name;
        mcolor_image = color_image;
    }
    public int get_color_image(){
        return mcolor_image;
    }
    public String get_color_name(){
        return mcolor_name;
    }
}
