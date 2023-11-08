package com.vanh.timekeeping.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vanh.timekeeping.R;
import com.vanh.timekeeping.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        setListener();

    }

    private void init() {

    }

    private void setListener() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.username.getText().toString();
                String pass = binding.password.getText().toString();
                if (!name.equals("") && !pass.equals("")) {
                    if (name.equals("Vananh") && pass.equals("Vananh1234")) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Sai mat khau hoac tai khoan", Toast.LENGTH_SHORT).show();
                        binding.username.setText("");
                        binding.password.setText("");
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Nhap thong tin day du", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}