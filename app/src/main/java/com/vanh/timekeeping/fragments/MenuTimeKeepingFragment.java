package com.vanh.timekeeping.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vanh.timekeeping.activities.TimekeepingActivity;
import com.vanh.timekeeping.databinding.FragmentHomepageBinding;
import com.vanh.timekeeping.databinding.FragmentTimekeepingBinding;

public class MenuTimeKeepingFragment extends Fragment {
    private FragmentTimekeepingBinding binding;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTimekeepingBinding.inflate(inflater, container, false);


        init();
        setListeners();
        return binding.getRoot();
    }
    private void init() {

    }
    private void setListeners() {
        binding.btnTimeKeeping.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), TimekeepingActivity.class);
            startActivity(intent);
        });
    }

}
