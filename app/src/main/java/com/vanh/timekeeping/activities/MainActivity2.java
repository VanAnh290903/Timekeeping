package com.vanh.timekeeping.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vanh.timekeeping.R;
import com.vanh.timekeeping.adapters.MenuAdapter;
import com.vanh.timekeeping.databinding.ActivityMain2Binding;
import com.vanh.timekeeping.databinding.ActivityMainBinding;
import com.vanh.timekeeping.ulitilies.HelperFunction;

public class MainActivity2 extends AppCompatActivity {
    private ActivityMain2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        setListeners();

    }
    private void init() {


    }


    private void setListeners() {
        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidate()) {
                    String fullname = binding.fullname.getText().toString().trim();
                    boolean gender = binding.radioButton.isChecked();

                    Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                    intent.putExtra("fullname", fullname);
                    intent.putExtra("gender", gender);
                    startActivity(intent);
                }
            }
        });
    }
    private boolean checkValidate() {
        if (binding.fullname.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Khong duoc de trong ho ten", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!binding.radioButton.isChecked() && !binding.radioButton2.isChecked()) {
            Toast.makeText(this, "Phai chon gioi tinh", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}