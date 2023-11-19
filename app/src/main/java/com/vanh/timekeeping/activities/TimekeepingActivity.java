package com.vanh.timekeeping.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import java.sql.Time;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
        //Lấy ra tất cả staff
        setRCVListTimekeeping();
//jffjhddfjjdfh
    }
    private List<Staff> magic(List<Staff> staffAll, List<Timekeeping> timekeepings) {
        for (Timekeeping item : timekeepings) {
            staffAll.removeIf(staff -> staff.getIdStaff() == item.getIdStaff());
        }
        return staffAll;
    }
    private void setListener(){
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), MenuTimeKeepingFragment.class);
                startActivity(intent);
            }
        });
        binding.btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), ReviewActivity.class);
                startActivity(intent);
            }
        });
    }
    private void setRCVListTimekeeping()
    {
        ItemTimekeepingBinding bindingIT;
        staffs = StaffDatabase.getInstance(getApplicationContext()).staffDAO().getAllStaff();
        //lấy ra những người đã được điểm danh trong ngày hiện tại
        timekeepings = TimekeepingDatabase.getInstance(getApplicationContext()).timekeepingDAO().getTimekeepingByDate(HelperFunction.getDayNow());
        List<Staff> chuadiemdanh = magic(staffs, timekeepings);
        TimekeepingAdapter timekeepingAdapter= new TimekeepingAdapter(staffs, new TimekeepingListener() {
            int idBtnTimekeeping;
            Random random= new Random();
            int maxNumber= 1000;
            int idRvTk= random.nextInt(maxNumber);
            @Override
            public void onTimekeeingClick(Staff staff, int status) {
                // kiểm tra timekeeping này đã có trong timekeepingsDraft chưa, neeys chưa thì add còn rồi thì xóa cái cũ rồi add cái mới
                //để tạm create by là 1
                Timekeeping timekeeping = new Timekeeping(HelperFunction.getDayNow(), staff.getIdStaff(), status, 1);
                Iterator<Timekeeping> timekeepingIterator= timekeepingsDraft.iterator();
                while(timekeepingIterator.hasNext()){
                    Timekeeping existingTimekeeping= timekeepingIterator.next();
                    //kiểm tra tồn tại
                    if(existingTimekeeping.getTimekeepingDay().equals(timekeeping.getTimekeepingDay())&& existingTimekeeping.getIdStaff()==timekeeping.getIdStaff())
                    {
                        timekeepingIterator.remove();
                        break;
                    }
                }
                timekeepingsDraft.add(timekeeping);
                //

            }

        });
        binding.rcvTimeKeepingList.setAdapter(timekeepingAdapter);

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}