package com.vanh.timekeeping.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vanh.timekeeping.R;
import com.vanh.timekeeping.database.StaffDatabase;
import com.vanh.timekeeping.databinding.ActivityDetailStaffBinding;
import com.vanh.timekeeping.databinding.ActivityLoginBinding;
import com.vanh.timekeeping.entity.Staff;
import com.vanh.timekeeping.ulitilies.Constants;
import com.vanh.timekeeping.ulitilies.Gender;
import com.vanh.timekeeping.ulitilies.HelperFunction;

import java.text.DecimalFormat;

public class DetailStaffActivity extends AppCompatActivity {

    private ActivityDetailStaffBinding binding;
    private Staff staff;

    private TextView resultBasicSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailStaffBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        setListener();
    }

    private void init() {
        staff = (Staff) getIntent().getSerializableExtra("staff");
        if (staff == null) {
            finish();
        }
        setDetail();
//        resultBasicSalary= binding.resultBasicSalary;
//        double myValue= getDoubleFromTextView(resultBasicSalary);
//        DecimalFormat decimalFormat= new DecimalFormat("#,###");
//        String formattedValue= decimalFormat.format(myValue);
//        resultBasicSalary.setText(formattedValue);
    }
    private double getDoubleFromTextView(TextView textView)
    {
        try{
            String stringValue = textView.getText().toString();
            return Double.parseDouble(stringValue);
        }catch (NumberFormatException e)
        {
            e.printStackTrace();
            return 0.0;
        }
    }
    private void setDetail(){
        binding.resultID.setText(staff.getIdStaff());
        binding.resultGender.setText((staff.getGender() == 1) ? getString(R.string.female) : getString(R.string.male));
        binding.resultName.setText(staff.getNameStaff());
        binding.resultDateOfBirth.setText(staff.getDateOfBird());
        binding.resultBasicSalary.setText(staff.getBasicSalary() + "");
    }
    private void setListener() {
        binding.btnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        //btn menu
        binding.icViewMenuifStaff.setOnClickListener(view -> {
            int isVis = binding.menuStaff.getVisibility();
            if (isVis == View.GONE) {
                binding.menuStaff.setVisibility(View.VISIBLE);
            } else {
                binding.menuStaff.setVisibility(View.GONE);
            }
        });

        // btn del
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                builder.setTitle("Xác nhận");
//                builder.setMessage("Bạn có chắc chắn muốn thực hiện hành động này?");
//
//                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
                        StaffDatabase.getInstance(getApplicationContext()).staffDAO().deleteStaff(staff);
                        Toast.makeText(DetailStaffActivity.this, "Del success", Toast.LENGTH_SHORT).show();

                        Intent resultIntent = new Intent();
                        // Đặt kết quả thành RESULT_OK và gửi dữ liệu kết quả về Activity Main
                        setResult(RESULT_OK, resultIntent);

                        finish();
//                        dialog.dismiss();
//                    }
//                });
//
//                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Xử lý khi người dùng hủy bỏ
//                        // Ví dụ: đóng hộp thoại
//                        dialog.dismiss();
//                    }
//                });
//
//                AlertDialog dialog = builder.create();
//                dialog.show();

            }
        });
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid())
                {
                    staff.setGender(
                            (binding.femaleRadioButton.isChecked()) ?
                                    Integer.parseInt(String.valueOf(Gender.FEMALE))
                                    :
                                    Integer.parseInt(String.valueOf(Gender.MALE)));
                    staff.setNameStaff(binding.editFullname.getText().toString().trim());
                    staff.setDateOfBird(binding.editBirth.getText().toString().trim());
                    staff.setBasicSalary(Double.parseDouble(binding.editBsSalary.getText().toString().trim()));
                    StaffDatabase.getInstance(getApplicationContext()).staffDAO().updateStaff(staff);
                    Toast.makeText(DetailStaffActivity.this, "Update staff success", Toast.LENGTH_SHORT).show();
                    Intent resultIntent = new Intent();
                    setResult(RESULT_OK,resultIntent);
                    onVis(false);
                    setDetail();
                }


            }
        });

        binding.btnViewDetailTimekeeping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),DetailStaffActivity.class);
                startActivity(intent);
            }
        });
        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onVis(true);
                //set data vao cac o input

                if (staff.getGender() == 1) {
                    binding.femaleRadioButton.setChecked(true);
                } else {
                    binding.maleRadioButton.setChecked(true);
                }
                binding.editFullname.setText(staff.getNameStaff());
                binding.editBirth.setText(staff.getDateOfBird());
                binding.editBsSalary.setText(String.valueOf(staff.getBasicSalary()));
            }
        });
        binding.btnCancelU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onVis(false);
            }
        });
    }
    private void onVis(boolean status) {
        if (status) {

            binding.editGenderRadioGroup.setVisibility(View.VISIBLE);
            binding.UButtons.setVisibility(View.VISIBLE);
            binding.editFullname.setVisibility(View.VISIBLE);
            binding.editBirth.setVisibility(View.VISIBLE);
            binding.editBsSalary.setVisibility(View.VISIBLE);
            binding.menuStaff.setVisibility(View.GONE);
            //...


            binding.resultGender.setVisibility(View.GONE);
            binding.resultName.setVisibility(View.GONE);
            binding.resultDateOfBirth.setVisibility(View.GONE);
            binding.resultBasicSalary.setVisibility(View.GONE);
        }
        else {

            binding.editGenderRadioGroup.setVisibility(View.GONE);
            binding.UButtons.setVisibility(View.GONE);
            binding.editFullname.setVisibility(View.GONE);
            binding.editBirth.setVisibility(View.GONE);
            binding.editBsSalary.setVisibility(View.GONE);


            binding.resultGender.setVisibility(View.VISIBLE);
            binding.resultName.setVisibility(View.VISIBLE);
            binding.resultDateOfBirth.setVisibility(View.VISIBLE);
            binding.resultBasicSalary.setVisibility(View.VISIBLE);
        }

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
    private Boolean isValid() {
        if (!binding.maleRadioButton.isChecked() && !binding.femaleRadioButton.isChecked()) {
            Toast.makeText(this, "Please ,enter gender staff", Toast.LENGTH_SHORT).show();
            return false;

        } else if (binding.editFullname.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please, enter name staff", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.editBirth.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please, enter date of birth", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.editBsSalary.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please, enter basic salary", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

}