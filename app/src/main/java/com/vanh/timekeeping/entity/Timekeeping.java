package com.vanh.timekeeping.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Timekeeping implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private long timekeepingDay;
    private String idStaff;
    private int idStatus;
    private int createBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimekeepingDay() {
        return timekeepingDay;
    }

    public void setTimekeepingDay(long timekeepingDay) {
        this.timekeepingDay = timekeepingDay;
    }

    public String getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(String idStaff) {
        this.idStaff = idStaff;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Timekeeping(long timekeepingDay, String idStaff, int idStatus, int createBy) {
        this.timekeepingDay = timekeepingDay;
        this.idStaff = idStaff;
        this.idStatus = idStatus;
        this.createBy = createBy;
    }
}
