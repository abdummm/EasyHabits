package com.easyhabitsapp.android;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {habits_data_class.class},version = 1,exportSchema = false)
@TypeConverters({Converters.class})
public abstract class Room_database_habits extends RoomDatabase {
    private static Room_database_habits room_database_habits;
    private static String name = "database";
    public synchronized static Room_database_habits getInstance(Context context){
        if(room_database_habits==null){
            room_database_habits = Room.databaseBuilder(context.getApplicationContext(),Room_database_habits.class,name).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return room_database_habits;
    }

    public abstract Dao_for_habits_data dao_for_habits_data();
}
