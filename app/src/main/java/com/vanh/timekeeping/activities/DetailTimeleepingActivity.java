package com.vanh.timekeeping.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.vanh.timekeeping.databinding.ActivityDetailTimeleepingBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailTimeleepingActivity extends AppCompatActivity {
    private ActivityDetailTimeleepingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDetailTimeleepingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        setListener();
    }
    private void init(){}
    private void setListener(){
        Date currentDate = new Date();

        // Định dạng thời gian thành tháng/năm (vd: 11/2023)
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy", Locale.getDefault());
        String formattedDate = sdf.format(currentDate);

        // Lấy TextView từ layout và cập nhật giá trị
        TextView textViewMonthYear = binding.viewMonth;
        textViewMonthYear.setText(formattedDate);

    }
}