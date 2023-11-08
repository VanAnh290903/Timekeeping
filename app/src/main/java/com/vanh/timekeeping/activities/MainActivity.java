package com.vanh.timekeeping.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.vanh.timekeeping.R;
import com.vanh.timekeeping.adapters.MenuAdapter;
import com.vanh.timekeeping.database.StaffDatabase;
import com.vanh.timekeeping.databinding.ActivityMainBinding;
import com.vanh.timekeeping.entity.Staff;
import com.vanh.timekeeping.ulitilies.HelperFunction;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final int ACTION_HOME = R.id.action_home;
    private static final int ACTION_TIMEKEEPING = R.id.action_timekeeping;
    private static final int ACTION_SALARY = R.id.action_salary;
    private static final int ACTION_SETTING = R.id.action_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        setListeners();

    }
    private void init() {
        binding.bottomNav.setSelectedItemId(R.id.action_home);
        MenuAdapter viewPagerMenuAdapter = new MenuAdapter(getSupportFragmentManager(), getLifecycle());
        binding.viewPagerMenu.setAdapter(viewPagerMenuAdapter);
        binding.bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    HelperFunction.hideSoftKeyboard(MainActivity.this);//ẩn bàn phím ảo của đt
                    binding.viewPagerMenu.setCurrentItem(0);
                    break;
                case R.id.action_timekeeping:
                    HelperFunction.hideSoftKeyboard(MainActivity.this);
                    binding.viewPagerMenu.setCurrentItem(1);
                    break;
                case R.id.action_salary:
                    HelperFunction.hideSoftKeyboard(MainActivity.this);
                    binding.viewPagerMenu.setCurrentItem(2);
                    break;
                case R.id.action_setting:
                    HelperFunction.hideSoftKeyboard(MainActivity.this);
                    binding.viewPagerMenu.setCurrentItem(3);
                    break;
            }
            return true;
        });
//        Staff staff = new Staff("MV1", "Vanh chos", "1231", 1,"10 10 2003", 11000000, 1);
//        StaffDatabase.getInstance(getApplicationContext()).staffDAO().insertStaff(staff);


    }


    private void setListeners() {

    }

}