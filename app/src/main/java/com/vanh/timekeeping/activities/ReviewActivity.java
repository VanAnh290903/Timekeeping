package com.vanh.timekeeping.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.widget.Toast;

import com.vanh.timekeeping.adapters.ReviewTimekeepingAdapter;
import com.vanh.timekeeping.database.TimekeepingDatabase;
import com.vanh.timekeeping.databinding.ActivityLoginBinding;
import com.vanh.timekeeping.databinding.ActivityReviewBinding;
import com.vanh.timekeeping.entity.Staff;
import com.vanh.timekeeping.entity.Timekeeping;
import com.vanh.timekeeping.listeners.StaffListener;
import com.vanh.timekeeping.ulitilies.Constants;
import com.vanh.timekeeping.ulitilies.HelperFunction;

import java.util.ArrayList;
import java.util.List;

public class ReviewActivity extends AppCompatActivity {
    private ActivityReviewBinding binding;
    private List<Timekeeping> timekeepings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        setListener();
    }
    private void init(){
        binding.viewTime.setText(HelperFunction.convertFormTimestampToDay(HelperFunction.getTimestampNow()));
        Intent intent = getIntent();
        timekeepings = new ArrayList<>();
        timekeepings = (List<Timekeeping>) intent.getSerializableExtra(Constants.LIST_TIME_KEEPING);

        ReviewTimekeepingAdapter reviewTimekeepingAdapter = new ReviewTimekeepingAdapter(timekeepings, staff -> {
        });
        binding.rcvRVTimeKeepingList.setAdapter(reviewTimekeepingAdapter);
    }
    private void setListener(){
        binding.btnCancel.setOnClickListener(v -> {
            finish();
        });
        binding.btnDone.setOnClickListener(v -> {
            for (Timekeeping timekeeping : timekeepings) {
                TimekeepingDatabase.getInstance(getApplicationContext()).timekeepingDAO().insertTimekeeping(timekeeping);
            }
            setResult(RESULT_OK, new Intent());
            finish();
        });
    }
}