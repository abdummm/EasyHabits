package com.easyhabitsapp.android;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.billingclient.api.Purchase;

import java.util.concurrent.TimeUnit;

public class Save_and_retrive_user_id {

    public void save_the_id(Context context, Purchase purchase,String mode,String user_id,int length_in_month){
        SharedPreferences sharedPreferences = context.getSharedPreferences("pending_buys", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        String old = sharedPreferences.getString("pending_buys","");
        if(mode.equals("main")){
            if(old!=null && !old.isEmpty()){
                if(is_this_saved(purchase.getPurchaseTime(),old)){
                    myEdit.putString("pending_buys",old.concat(String.valueOf(purchase.getPurchaseTime())).concat("small_split").concat(user_id).concat("max_divide"));
                    myEdit.commit();
                }
            } else {
                myEdit.putString("pending_buys",String.valueOf(purchase.getPurchaseTime()).concat("small_split").concat(user_id).concat("max_divide"));
                myEdit.commit();
            }
        } else if (mode.equals("back_up")) {
            if (should_i_save_back_up(length_in_month, context)) {
                myEdit.putString(String.valueOf(length_in_month).concat("_month"), user_id.concat("small_split").concat(String.valueOf(System.currentTimeMillis())));
                myEdit.commit();
            }
        }
    }

    public String retrive_the_id(Context context,long time,int length){
        SharedPreferences sharedPreferences = context.getSharedPreferences("pending_buys", Context.MODE_PRIVATE);
        String old = sharedPreferences.getString("pending_buys","");
        String[] big = old.split("max_divide");
        for(int i = 0;i<big.length;i++){
            String[] small = big[i].split("small_split");
            if(Long.parseLong(small[0]) == time){
                return small[1];
            }
        }
        String backup = sharedPreferences.getString(String.valueOf(length).concat("_month"),"");
        String[] split = backup.split("small_split");
        return split[0];
    }

    private boolean is_this_saved(long time,String old){
        boolean should_i_save = true;
        String[] big = old.split("max_divide");
        for(int i = 0;i<big.length;i++){
            String[] small = big[i].split("small_split");
            if(Long.parseLong(small[0]) == time){
                should_i_save = false;
                break;
            }
        }
        return should_i_save;
    }

    private boolean should_i_save_back_up(int month,Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("pending_buys", Context.MODE_PRIVATE);
        String old = sharedPreferences.getString(String.valueOf(month).concat("_month"),"");
        if(old !=null && !old.isEmpty()){
            String[] split = old.split("small_split");
            if(Long.parseLong(split[1]) <= (System.currentTimeMillis() - TimeUnit.DAYS.toMillis(3))){
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public void remove_the_id(Context context,int month){
        SharedPreferences sharedPreferences = context.getSharedPreferences("pending_buys", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString(String.valueOf(month).concat("_month"),"");
    }
}
