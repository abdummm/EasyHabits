package com.easyhabitsapp.android;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class Save_and_get extends Application {
    String split_keyword = "this_is_the_split_of_save_and_get";
    private static final Save_and_get instance = new Save_and_get();

    private Save_and_get() {
    }

    public static Save_and_get getInstance() {
        return instance;
    }

    public void save_this(Context context, String save_me, String where, boolean concat) {
        save_me = save_me.replace(split_keyword, "");
        SharedPreferences sharedPreferences = context.getSharedPreferences(where, MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        if (concat) {
            myEdit.putString(where, sharedPreferences.getString(where, "").concat(split_keyword).concat(save_me));
        } else {
            myEdit.putString(where, save_me);
        }
        myEdit.commit();
    }

    public void save_this(Context context, boolean save_me, String where) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(where, MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putBoolean(where, save_me);
        myEdit.commit();
    }

    public void save_this(Context context, boolean save_me, String where_main,String where_branch) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(where_main, MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putBoolean(where_branch, save_me);
        myEdit.commit();
    }

    public void save_this(Context context, String save_me, String where_main,String where_branch) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(where_main, MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString(where_branch, save_me);
        myEdit.commit();
    }

    public void save_this(Context context, long save_me, String where) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(where, MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putLong(where, save_me);
        myEdit.commit();
    }

    public void save_this(Context context, long save_me, String where_main,String where_branch) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(where_main, MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putLong(where_branch, save_me);
        myEdit.commit();
    }

    public void save_this(Context context, int save_me, String where_main,String where_branch) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(where_main, MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putInt(where_branch, save_me);
        myEdit.commit();
    }

    public String get_this(Context context, String where_main, String where_branch) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(where_main, MODE_PRIVATE);
        return sharedPreferences.getString(where_branch, "");
    }

    public boolean get_this_boolean(Context context, String where_main, String where_branch) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(where_main, MODE_PRIVATE);
        return sharedPreferences.getBoolean(where_branch, false);
    }

    public long get_this_long(Context context, String where_main, String where_branch,long defualt_value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(where_main, MODE_PRIVATE);
        return sharedPreferences.getLong(where_branch,defualt_value);
    }

    public int get_this_int(Context context, String where_main, String where_branch,int defualt_value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(where_main, MODE_PRIVATE);
        return sharedPreferences.getInt(where_branch,defualt_value);
    }

    public String return_split_keyword() {
        return split_keyword;
    }
}
