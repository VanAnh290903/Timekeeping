package com.vanh.timekeeping.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.vanh.timekeeping.entity.Staff;

import java.util.List;

@Dao
public interface StaffDAO {
    @Insert
    void insertStaff(Staff staff);

    @Update
    void updateStaff(Staff staff);
    @Delete
    void deleteStaff(Staff staff);

    @Query("SELECT * FROM Staff")
    List<Staff> getAllStaff();

    @Query("SELECT * FROM Staff where idStaff = :idStaff LIMIT 1")
    Staff getStaffById(String idStaff);

    @Query("SELECT nameStaff FROM Staff WHERE idStaff = :id")
    String getNameStaffById(String id);


}
