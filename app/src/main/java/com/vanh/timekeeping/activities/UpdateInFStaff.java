package com.vanh.timekeeping.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;

import com.vanh.timekeeping.databinding.ActivityUpdateInFstaffBinding;

public class UpdateInFStaff extends AppCompatActivity {
    private ActivityUpdateInFstaffBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityUpdateInFstaffBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}