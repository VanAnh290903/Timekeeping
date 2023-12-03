package com.vanh.timekeeping.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.vanh.timekeeping.entity.Timekeeping;
import com.vanh.timekeeping.entity.User;

import java.util.List;

@Dao
public interface TimekeepingDAO {
    @Insert
    void insertTimekeeping(Timekeeping timekeeping);

    @Update
    void updateTimekeeping(Timekeeping timekeeping);

    @Delete
    void deleteTimekeeping(Timekeeping timekeeping);

    @Query("SELECT * FROM Timekeeping WHERE  timekeepingDay >= :startOfDayTimestamp AND timekeepingDay <= :endOfDayTimestamp")
    List<Timekeeping> getTimekeepingByDay(long startOfDayTimestamp, long endOfDayTimestamp);
}
