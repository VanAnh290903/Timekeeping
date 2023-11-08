package com.vanh.timekeeping.entity;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

//@Entity(foreignKeys = {@ForeignKey(entity = User.class,
//        parentColumns = "idUser",
//        childColumns = "createBy",
//        onDelete = ForeignKey.CASCADE)})
@Entity
public class Staff implements Serializable {
    @PrimaryKey
    @NonNull
    private String idStaff;
    private String nameStaff;
    private String avatar;
    private int gender;
    private String dateOfBird;
    private double basicSalary;
    private int createBy;


    public String getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(String idStaff) {
        this.idStaff = idStaff;
    }

    public String getNameStaff() {
        return nameStaff;
    }

    public void setNameStaff(String nameStaff) {
        this.nameStaff = nameStaff;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDateOfBird() {
        return dateOfBird;
    }

    public void setDateOfBird(String dateOfBird) {
        this.dateOfBird = dateOfBird;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Staff(String idStaff, String nameStaff, String avatar, int gender, String dateOfBird, double basicSalary, int createBy) {
        this.idStaff = idStaff;
        this.nameStaff = nameStaff;
        this.avatar = avatar;
        this.gender = gender;
        this.dateOfBird = dateOfBird;
        this.basicSalary = basicSalary;
        this.createBy = createBy;
    }
}
