package com.vanh.timekeeping.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.widget.Toast;

import com.vanh.timekeeping.database.StaffDatabase;
import com.vanh.timekeeping.databinding.ActivityAddStaffBinding;
import com.vanh.timekeeping.entity.Staff;
import com.vanh.timekeeping.ulitilies.Gender;

public class AddStaffActivity extends AppCompatActivity {

    private ActivityAddStaffBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAddStaffBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        setListener();
    }
    private void init(){

    }

    private void setListener(){
        binding.btnAddStaff.setOnClickListener(view -> {
//            Toast.makeText(this, Gender.MALE + "", Toast.LENGTH_SHORT).show();
            if (isValid()) {
                Staff staff = new Staff(
                        binding.staffId.getText().toString(),
                        binding.staffName.getText().toString(),
                        "",
                        (binding.staffGenderFe.isChecked()) ? Integer.parseInt(String.valueOf(Gender.FEMALE)) : Integer.parseInt(String.valueOf(Gender.MALE)),
                        binding.staffDateOfBirth.getText().toString(),
                        Double.parseDouble(binding.staffBasicSalary.getText().toString()),
                        1
                );
                StaffDatabase.getInstance(getApplicationContext()).staffDAO().insertStaff(staff);
                Toast.makeText(this, "Insert staff success", Toast.LENGTH_SHORT).show();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("staff", staff);

                // Đặt kết quả thành RESULT_OK và gửi dữ liệu kết quả về Activity Main
                setResult(RESULT_OK, resultIntent);

                finish();
            }
        });
        binding.btnCancelAdd.setOnClickListener(view -> {
            finish();
        });
    }
    @NonNull
    private Boolean isValid() {
        if(!binding.staffGenderMa.isChecked() && !binding.staffGenderFe.isChecked()) {
            Toast.makeText(this, "Please ,enter gender staff", Toast.LENGTH_SHORT).show();
            return false;
        }else if(binding.staffId.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please ,enter id staff", Toast.LENGTH_SHORT).show();
            return false;
        }else if(binding.staffName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please, enter name staff", Toast.LENGTH_SHORT).show();
            return false;
        }else if(binding.staffDateOfBirth.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please, enter date of birth", Toast.LENGTH_SHORT).show();
            return false;
        }else if(binding.staffBasicSalary.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please, enter basic salary", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

}