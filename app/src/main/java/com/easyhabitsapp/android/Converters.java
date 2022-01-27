package com.easyhabitsapp.android;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Converters {
    @TypeConverter
    public static ArrayList<Long> fromString(String value) {
        ArrayList<Long> return_me = new ArrayList<>();
        if(value.equals("")){
            return return_me;
        } else {
            String[] concat = value.split("_");
            for(int i =0;i< concat.length;i++){
                return_me.add(Long.valueOf(concat[i]));
            }
            return return_me;
        }
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Long> list) {
        String save_me= "";
        for(int i =0; i <list.size();i++){
            save_me = save_me.concat(String.valueOf(list.get(i))).concat("_");
        }
        return save_me;
    }
    @TypeConverter
    public static HashMap<Long,Integer> fromString_hash(String value) {
        HashMap<Long,Integer> return_me = new HashMap<>();
        if(value.equals("")){
            return return_me;
        } else {
            String[] concat = value.split("_");
            for(int i =0;i< concat.length;i++){
                String[] small_split = concat[i].split(":");
                return_me.put(Long.parseLong(small_split[0]),Integer.parseInt(small_split[1]));
            }
            return return_me;
        }
    }

    @TypeConverter
    public static String fromHashMap(HashMap<Long,Integer> list) {
        String save_me= "";
        for (Map.Entry<Long, Integer> entry : list.entrySet()) {
            long key = entry.getKey();
            Integer value = entry.getValue();
            save_me = save_me.concat(String.valueOf(key)).concat(":").concat(String.valueOf(value)).concat("_");
        }
        return save_me;
    }
}
