package com.vanh.timekeeping.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.vanh.timekeeping.entity.Timekeeping;
import com.vanh.timekeeping.entity.User;
import com.vanh.timekeeping.ulitilies.Constants;
import com.vanh.timekeeping.ulitilies.ConverstDate;

@Database(entities = {Timekeeping.class}, version = Constants.VERSION_ROOM)
@TypeConverters({ConverstDate.class})
public abstract class TimekeepingDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "timekeeping.db";
    private static TimekeepingDatabase instance;

    public static synchronized TimekeepingDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(), TimekeepingDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract TimekeepingDAO timekeepingDAO();
}
