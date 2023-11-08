package com.vanh.timekeeping.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.vanh.timekeeping.entity.Staff;
import com.vanh.timekeeping.entity.User;
import com.vanh.timekeeping.ulitilies.Constants;
import com.vanh.timekeeping.ulitilies.ConverstDate;

@Database(entities = {Staff.class}, version = Constants.VERSION_ROOM)
@TypeConverters({ConverstDate.class})
public abstract class StaffDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "staff.db";
    private static StaffDatabase instance;

    public static synchronized StaffDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(), StaffDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract StaffDAO staffDAO();
}
