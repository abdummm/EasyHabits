package com.easyhabitsapp.android;

import androidx.room.TypeConverter;

import java.util.HashMap;
import java.util.Map;

public class Converter_hash_map {
    @TypeConverter
    public static HashMap<Long,Integer> fromString(String value) {
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
