package com.easyhabitsapp.android;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {habits_data_class.class},version = 3,exportSchema = false)
//@TypeConverters({Converters.class})
public abstract class Room_database_habits extends RoomDatabase {

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'habits' ADD COLUMN advanced integer NOT NULL default 1");
        }
    };
    private static Room_database_habits room_database_habits;
    private static String name = "database";
    public synchronized static Room_database_habits getInstance(Context context){
        if(room_database_habits==null){
            room_database_habits = Room.databaseBuilder(context.getApplicationContext(),Room_database_habits.class,name).allowMainThreadQueries().fallbackToDestructiveMigration().addMigrations(MIGRATION_1_2).build();
        }
        return room_database_habits;
    }

    public abstract Dao_for_habits_data dao_for_habits_data();
}
