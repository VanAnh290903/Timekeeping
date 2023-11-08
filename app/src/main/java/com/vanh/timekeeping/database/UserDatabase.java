package com.vanh.timekeeping.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.vanh.timekeeping.entity.Staff;
import com.vanh.timekeeping.entity.User;
import com.vanh.timekeeping.ulitilies.Constants;
import com.vanh.timekeeping.ulitilies.ConverstDate;

@Database(entities = {User.class}, version = Constants.VERSION_ROOM)
@TypeConverters({ConverstDate.class})
public abstract class UserDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "user.db";
    private static UserDatabase instance;

    public static synchronized UserDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract UserDAO userDAO();
}
