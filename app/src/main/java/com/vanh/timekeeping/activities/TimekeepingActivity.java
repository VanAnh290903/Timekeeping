package com.vanh.timekeeping.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.vanh.timekeeping.adapters.TimekeepingAdapter;
import com.vanh.timekeeping.database.StaffDatabase;
import com.vanh.timekeeping.database.TimekeepingDatabase;
import com.vanh.timekeeping.databinding.ActivityTimekeepingBinding;
import com.vanh.timekeeping.databinding.ItemTimekeepingBinding;
import com.vanh.timekeeping.entity.Staff;
import com.vanh.timekeeping.entity.Timekeeping;
import com.vanh.timekeeping.fragments.MenuTimeKeepingFragment;
import com.vanh.timekeeping.listeners.TimekeepingListener;
import com.vanh.timekeeping.ulitilies.Constants;
import com.vanh.timekeeping.ulitilies.HelperFunction;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class TimekeepingActivity extends AppCompatActivity {
private ActivityTimekeepingBinding binding;

    private List<Staff> staffs;
    private List<Timekeeping> timekeepings;
    //khai báo biến cục bộ ở đây
    private List<Timekeeping> timekeepingsDraft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTimekeepingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        setListener();
    }
    private void init(){
        binding.viewTime.setText(HelperFunction.convertFormTimestampToDay(HelperFunction.getTimestampNow()));
        //Lấy ra tất cả staff
        setRCVListTimekeeping();

    }
    private List<Staff> magic(List<Staff> staffAll, List<Timekeeping> timekeepings) {
        List<String> idsToRemove = timekeepings.stream()
                .map(Timekeeping::getIdStaff)
                .collect(Collectors.toList());

        staffAll.removeIf(staff -> idsToRemove.contains(staff.getIdStaff()));

        return staffAll;
    }
    private void setListener(){
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), ReviewActivity.class);
                intent.putExtra(Constants.LIST_TIME_KEEPING, (Serializable)timekeepingsDraft);
                startActivityForResult(intent, Constants.IS_TIMEKEEPING);
//                Log.d(TimekeepingActivity.class.toString(), timekeepingsDraft.toString());
            }
        });
    }
    private void setRCVListTimekeeping()
    {
        staffs = StaffDatabase.getInstance(getApplicationContext()).staffDAO().getAllStaff();
        //lấy ra những người đã được điểm danh trong ngày hiện tại
        timekeepings = TimekeepingDatabase.getInstance(getApplicationContext()).timekeepingDAO().getTimekeepingByDay(HelperFunction.getTimestampStartOfDay(HelperFunction.getTimestampNow()), HelperFunction.getTimestampEndOfDay(HelperFunction.getTimestampNow()));
        List<Staff> chuadiemdanh = magic(staffs, timekeepings);
        if (chuadiemdanh.isEmpty()) {
            setResult(RESULT_CANCELED, new Intent());
            finish();
        }
        TimekeepingAdapter timekeepingAdapter= new TimekeepingAdapter(chuadiemdanh, new TimekeepingListener() {
            @Override
            public void onTimekeeingClick(Staff staff, int status) {
                // kiểm tra timekeeping này đã có trong timekeepingsDraft chưa, neeys chưa thì add còn rồi thì xóa cái cũ rồi add cái mới
                //để tạm create by là 1
                Timekeeping timekeeping = new Timekeeping(HelperFunction.getTimestampNow(), staff.getIdStaff(), status, 1);
                if(timekeepingsDraft != null) {
                    Iterator<Timekeeping> timekeepingIterator= timekeepingsDraft.iterator();
                    while(timekeepingIterator.hasNext()){
                        Timekeeping existingTimekeeping= timekeepingIterator.next();
                        //kiểm tra tồn tại
                        if(Objects.equals(existingTimekeeping.getIdStaff(), timekeeping.getIdStaff()))
                        {
                            timekeepingIterator.remove();
                            break;
                        }
                    }
                } else {
                    timekeepingsDraft = new ArrayList<>();
                }
                timekeepingsDraft.add(timekeeping);
            }

        });
        binding.rcvTimeKeepingList.setAdapter(timekeepingAdapter);

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            setResult(RESULT_OK, new Intent());
            finish();
        }
    }

}