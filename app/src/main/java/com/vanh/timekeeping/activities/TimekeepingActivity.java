package com.vanh.timekeeping.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.vanh.timekeeping.database.StaffDatabase;
import com.vanh.timekeeping.database.TimekeepingDatabase;
import com.vanh.timekeeping.databinding.ActivityTimekeepingBinding;
import com.vanh.timekeeping.entity.Staff;
import com.vanh.timekeeping.entity.Timekeeping;
import com.vanh.timekeeping.ulitilies.HelperFunction;

import java.util.Date;
import java.util.List;

public class TimekeepingActivity extends AppCompatActivity {
private ActivityTimekeepingBinding binding;

    private List<Staff> staffs;
    private List<Timekeeping> timekeepings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTimekeepingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        setListener();
    }
    private void init(){
        //Lấy ra tất cả staff
        staffs = StaffDatabase.getInstance(getApplicationContext()).staffDAO().getAllStaff();
        //lấy ra những người đã được điểm danh trong ngày hiện tại
        timekeepings = TimekeepingDatabase.getInstance(getApplicationContext()).timekeepingDAO().getTimekeepingByDate(HelperFunction.getDayNow());
        List<Staff> chuadiemdanh = magic(staffs, timekeepings);


    }
    private List<Staff> magic(List<Staff> staffAll, List<Timekeeping> timekeepings) {
        for (Timekeeping item : timekeepings) {
            staffAll.removeIf(staff -> staff.getIdStaff() == item.getIdStaff());
        }
        return staffAll;
    }
    private void setListener(){

    }

}