package com.vanh.timekeeping.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vanh.timekeeping.activities.TimekeepingActivity;
import com.vanh.timekeeping.adapters.ReviewTimekeepingAdapter;
import com.vanh.timekeeping.database.TimekeepingDatabase;
import com.vanh.timekeeping.databinding.FragmentHomepageBinding;
import com.vanh.timekeeping.databinding.FragmentTimekeepingBinding;
import com.vanh.timekeeping.entity.Staff;
import com.vanh.timekeeping.entity.Timekeeping;
import com.vanh.timekeeping.listeners.StaffListener;
import com.vanh.timekeeping.listeners.TimekeepingListener;
import com.vanh.timekeeping.ulitilies.Constants;
import com.vanh.timekeeping.ulitilies.Gender;
import com.vanh.timekeeping.ulitilies.HelperFunction;

import java.time.Instant;
import java.util.List;

public class MenuTimeKeepingFragment extends Fragment {
    private FragmentTimekeepingBinding binding;
    long currentTimestamp;
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
        currentTimestamp = HelperFunction.getTimestampNow();
        binding.textView3.setText(HelperFunction.convertFormTimestampToDay(currentTimestamp));
        setRCV(HelperFunction.getTimestampStartOfNow(), HelperFunction.getTimestampEndOfNow());
        setEnable();
    }
    private void setEnable() {
        if (HelperFunction.getTimestampStartOfDay(currentTimestamp) < HelperFunction.getTimestampStartOfDay(HelperFunction.getTimestampNow())) {
            binding.btnNextDay.setEnabled(true);
        } else {
            binding.btnNextDay.setEnabled(false);
        }
    }

    private void setRCV(long start, long end) {
        List<Timekeeping> timekeepings = TimekeepingDatabase.getInstance(getContext()).
                timekeepingDAO().
                getTimekeepingByDay(start, end);
        if (timekeepings.isEmpty()) {
            if (currentTimestamp == HelperFunction.getTimestampNow()) {
                binding.notificationNoneTimekeepingOfNow.setVisibility(View.VISIBLE);
            } else {
                binding.notificationNoneTimekeepingOfNow.setVisibility(View.VISIBLE);
                binding.rcvTimekeepingList.setVisibility(View.GONE);
                binding.notificationNoneTimekeepingOfNow.setText("Data empty");
            }
        } else {
            binding.notificationNoneTimekeepingOfNow.setVisibility(View.GONE);
            binding.rcvTimekeepingList.setVisibility(View.VISIBLE);
            ReviewTimekeepingAdapter reviewTimekeepingAdapter = new ReviewTimekeepingAdapter(timekeepings, new StaffListener() {
                @Override
                public void onStaffClicked(Staff staff) {

                }
            });
            binding.rcvTimekeepingList.setAdapter(reviewTimekeepingAdapter);
        }
    }

    private void setListeners() {
        binding.btnTimeKeeping.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), TimekeepingActivity.class);
            startActivityForResult(intent, Constants.IS_TIMEKEEPING);
        });
        binding.btnPrevDay.setOnClickListener(v -> {
            currentTimestamp = currentTimestamp - Constants.TIMESTAMP_IN_ONE_DAY;
            binding.textView3.setText(HelperFunction.convertFormTimestampToDay(currentTimestamp));
            setRCV(HelperFunction.getTimestampStartOfDay(currentTimestamp), HelperFunction.getTimestampEndOfDay(currentTimestamp));
            setEnable();
        });
        binding.btnNextDay.setOnClickListener(v -> {
            currentTimestamp = currentTimestamp + Constants.TIMESTAMP_IN_ONE_DAY;
            binding.textView3.setText(HelperFunction.convertFormTimestampToDay(currentTimestamp));
            setRCV(HelperFunction.getTimestampStartOfDay(currentTimestamp), HelperFunction.getTimestampEndOfDay(currentTimestamp));
            setEnable();
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.IS_TIMEKEEPING && resultCode == getActivity().RESULT_OK) {
            Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
            setRCV(HelperFunction.getTimestampStartOfNow(), HelperFunction.getTimestampEndOfNow());
        }
    }
}
