package com.vanh.timekeeping.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vanh.timekeeping.database.StaffDatabase;
import com.vanh.timekeeping.databinding.ActivityAddStaffBinding;
import com.vanh.timekeeping.entity.Staff;
import com.vanh.timekeeping.ulitilies.Gender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddStaffActivity extends AppCompatActivity {

    private ActivityAddStaffBinding binding;
    private SimpleDateFormat dateFormat;
ImageView imageView;
FloatingActionButton buttonCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAddStaffBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        setListener();

    }

    private void init(){
//        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getColor()));
      imageView = binding.shapeableImageView;
      buttonCamera= binding.Camera;
      buttonCamera.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {



              ImagePicker.with(AddStaffActivity.this)
                      .crop()	    			//Crop image(Optional), Check Customization for more option
                      .compress(1024)			//Final image size will be less than 1 MB(Optional)
                      .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                      .start();
          }
      });
    }


    private void setListener(){
        binding.btnAddStaff.setOnClickListener(view -> {
            String imageUrl = getImageUrlFromImageView();
//            Toast.makeText(this, Gender.MALE + "", Toast.LENGTH_SHORT).show();
            if (isValid()) {
                Staff staff = new Staff(
                        binding.staffId.getText().toString(),
                        binding.staffName.getText().toString(),
                        imageUrl,
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
        //chỉ cho nhập date
//        dateFormat= new SimpleDateFormat("dd/MM/yyyy");
//        binding.staffDateOfBirth.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                validateDate(editable.toString());
//            }
//        });
        binding.btnCancelAdd.setOnClickListener(view -> {
            finish();
        });

    }
    private boolean validateDate( String inputDate)
    {
        try {
            Date date= dateFormat.parse(inputDate);

           if(isOver18t(date))
           {
                return true;
           }
           else {
                return false;
           }

        } catch (ParseException e) {
            e.printStackTrace();
            binding.staffDateOfBirth.setError("Không hợp lệ");
            return false;
        }
    }
    //check 18t
    private boolean isOver18t(Date birthday)
    {
        Calendar Birthcal= Calendar.getInstance();
        Birthcal.setTime(birthday);
        Calendar currentCalendar = Calendar.getInstance();
        int age = currentCalendar.get(Calendar.YEAR)- Birthcal.get(Calendar.YEAR);
        if(age<18|| (age==18 && currentCalendar.get(Calendar.DAY_OF_YEAR) < Birthcal.get(Calendar.DAY_OF_YEAR)) || (age==18 && currentCalendar.get(Calendar.MONTH)< Birthcal.get(Calendar.MONTH))){
            int daysRemaining = Birthcal.get(Calendar.DAY_OF_YEAR)-currentCalendar.get(Calendar.DAY_OF_YEAR);
            binding.staffDateOfBirth.setError("Chưa đủ 18t");
            return false;
        }
        else if(Birthcal.get(Calendar.YEAR)<1900|| Birthcal.get(Calendar.YEAR)>currentCalendar.get(Calendar.YEAR))
        {
            binding.staffDateOfBirth.setError("không hợp lý");
            return false;
        }
        return true;
    }
    private String getImageUrlFromImageView() {
        if (imageView.getDrawable() instanceof BitmapDrawable) {
            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
            Bitmap bitmap = drawable.getBitmap();

            // Lưu ảnh vào bộ nhớ và trả về địa chỉ ảnh
            return saveImageToStorage(bitmap);
        } else {
            return ""; // Trả về chuỗi trống nếu không có ảnh trong ImageView
        }
    }

    // Hàm để lưu ảnh vào bộ nhớ và trả về địa chỉ ảnh
    private String saveImageToStorage(Bitmap bitmap) {
        // Thực hiện lưu ảnh vào bộ nhớ và trả về địa chỉ ảnh
        // Bạn có thể sử dụng các thư viện hoặc phương pháp tùy thuộc vào nhu cầu của bạn
        // Ví dụ: sử dụng File.createTempFile để tạo một tệp tạm thời và lưu ảnh vào đó
        // Sau đó, trả về địa chỉ của tệp tạm thời.
        // Lưu ý: Hãy thay đổi phương thức này theo nhu cầu thực tế của bạn.
        return ""; // Đây là một giả định, bạn cần cập nhật phương thức này dựa trên yêu cầu của bạn.
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
//        }else if (!validateDate(binding.staffDateOfBirth.getText().toString())) {
////            binding.staffDateOfBirth.setError("chưa đủ 18t");
//            Toast.makeText(this, binding.staffDateOfBirth.getError(), Toast.LENGTH_SHORT).show();
//            return false;
        } else if(binding.staffBasicSalary.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please, enter basic salary", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri= data.getData();

        imageView.setImageURI(uri);
    }
    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) {
            return uri.getPath(); // Trả về đường dẫn trực tiếp nếu không truy vấn được
        }
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(columnIndex);
        cursor.close();
        return path;
    }
}