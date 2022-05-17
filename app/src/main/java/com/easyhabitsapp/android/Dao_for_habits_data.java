package com.easyhabitsapp.android;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Dao
public interface Dao_for_habits_data {
    @Insert(onConflict = REPLACE)
    void insert(habits_data_class habits_data_class);

    @Delete
    void delete(habits_data_class habits_data_class);

    @Delete
    void reset(List<habits_data_class> habits_data_class);

    @Query("UPDATE habits SET  relapse = :m_relapse WHERE ID = :m_ID")
    void update_relapse(int m_ID, ArrayList<Long> m_relapse);

    @Query("UPDATE habits SET  relapse_amount = :m_relapse_amount WHERE ID = :m_ID")
    void update_relapse_amount(int m_ID, HashMap<Long,Integer> m_relapse_amount);

    @Query("SELECT * FROM habits")
    List<habits_data_class> getAll();
}
