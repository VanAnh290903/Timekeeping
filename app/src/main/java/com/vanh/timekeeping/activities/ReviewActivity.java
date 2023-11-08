package com.vanh.timekeeping.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;

import com.vanh.timekeeping.databinding.ActivityLoginBinding;
import com.vanh.timekeeping.databinding.ActivityReviewBinding;

public class ReviewActivity extends AppCompatActivity {
    private ActivityReviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_review);

        binding = ActivityReviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        setListener();
        String formatdate = "d-M-yyyy";
        binding.viewTime.setFormat12Hour(formatdate);
        binding.viewTime.setFormat24Hour(formatdate);
    }
    private void init(){}
    private void setListener(){}
}