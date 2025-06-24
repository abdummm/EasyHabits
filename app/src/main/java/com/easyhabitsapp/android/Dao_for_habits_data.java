package com.easyhabitsapp.android;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Dao
public interface Dao_for_habits_data {
    @Insert(onConflict = REPLACE)
    long insert(habits_data_class habits_data_class);

    @Delete
    void delete(habits_data_class habits_data_class);

    @Delete
    void reset(List<habits_data_class> habits_data_class);

    @TypeConverters(Converters.class)
    @Query("UPDATE habits SET  relapse = :m_relapse WHERE ID = :m_ID")
    void update_relapse(int m_ID, ArrayList<Long> m_relapse);

    @TypeConverters(Converters.class)
    @Query("UPDATE habits SET  relapse_amount = :m_relapse_amount WHERE ID = :m_ID")
    void update_relapse_amount(int m_ID, HashMap<Long,Integer> m_relapse_amount);

    @Query("UPDATE habits SET  date_of_last_relapse = :date_of_last_relapse WHERE ID = :m_ID")
    void update_start_date(int m_ID, long date_of_last_relapse);

    @TypeConverters(Converters.class)
    @Query("UPDATE habits SET  bad_or_good_habit = :bad_or_good_habit,name_of_the_habit = :name_of_the_habit,date_of_last_relapse =:date_of_last_relapse,goal =:goal,color =:color,icon=:icon,type_of_the_habit=:type_of_the_habit,extra_type_info=:extra_type_info,notifications_on_or_off=:notifications_on_or_off,notifications_freq=:notifications_freq,notifications_freq_extra=:notifications_freq_extra,notifications_time=:notifications_time,habits_freq=:habits_freq,habits_freq_extra=:habits_freq_extra,relapse=:relapse,relapse_amount_timer=:relapse_amount_timer,relapse_amount=:relapse_amount WHERE ID = :m_ID")
    void edit_habit(int m_ID, String bad_or_good_habit,String name_of_the_habit,long date_of_last_relapse,int goal,String color,String icon,String type_of_the_habit,int extra_type_info,boolean notifications_on_or_off,String notifications_freq,String notifications_freq_extra,long notifications_time,String habits_freq,String habits_freq_extra,ArrayList<Long> relapse,HashMap<Long,Integer> relapse_amount_timer,HashMap<Long,Integer> relapse_amount);

    @Query("SELECT * FROM habits")
    List<habits_data_class> getAll();
}
