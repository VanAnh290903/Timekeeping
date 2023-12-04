package com.vanh.timekeeping.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vanh.timekeeping.R;
import com.vanh.timekeeping.adapters.StaffAdapter;
import com.vanh.timekeeping.database.StaffDatabase;
import com.vanh.timekeeping.databinding.ActivityMain2Binding;
import com.vanh.timekeeping.databinding.ActivityMain3Binding;
import com.vanh.timekeeping.entity.Staff;
import com.vanh.timekeeping.listeners.StaffListener;
import com.vanh.timekeeping.ulitilies.Constants;

import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    private ActivityMain3Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        setListeners();

    }
    private void init() {
        String fullname = null;
        if (getIntent().hasExtra("fullname")) {
            fullname = getIntent().getStringExtra("fullname");
        }

        boolean gender = false;
        if (getIntent().hasExtra("gender")) {
            gender = getIntent().getBooleanExtra("gender", false);
        }
        String a = "Xin chào " + ((gender) ? "anh" : "chị") + " " + fullname;
        binding.titleView.setText("Xin chào " + ((gender) ? "anh" : "chị") + " " + fullname);

        List<Staff> staffList = StaffDatabase.getInstance(getApplicationContext()).staffDAO().getAllStaff();
        StaffAdapter staffAdapter = new StaffAdapter(staffList, new StaffListener() {
            @Override
            public void onStaffClicked(Staff staff) {
                Intent intent = new Intent(getApplicationContext(), DetailStaffActivity.class);
                intent.putExtra("staff", staff);
                startActivityForResult(intent, Constants.IS_UPDATE_STAFF);
            }
        });
        binding.rcvStaffList.setAdapter(staffAdapter);
    }


    private void setListeners() {

    }
}