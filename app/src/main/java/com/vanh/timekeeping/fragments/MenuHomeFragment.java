package com.vanh.timekeeping.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vanh.timekeeping.activities.AddStaffActivity;
import com.vanh.timekeeping.activities.DetailStaffActivity;
import com.vanh.timekeeping.adapters.StaffAdapter;
import com.vanh.timekeeping.database.StaffDatabase;
import com.vanh.timekeeping.databinding.FragmentHomepageBinding;
import com.vanh.timekeeping.entity.Staff;
import com.vanh.timekeeping.listeners.StaffListener;
import com.vanh.timekeeping.ulitilies.Constants;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuHomeFragment extends Fragment {
    private FragmentHomepageBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomepageBinding.inflate(inflater, container, false);
        init();
        setListeners();
        return binding.getRoot();
    }
    private void init() {
        setRCV();
    }
    private void setListeners() {
        binding.icAddStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(), AddStaffActivity.class);
                startActivityForResult(intent, Constants.IS_ADD_STAFF);
            }
        });
    }
    private void setRCV() {
        List<Staff> staffList = StaffDatabase.getInstance(getContext()).staffDAO().getAllStaff();
        StaffAdapter staffAdapter = new StaffAdapter(staffList, new StaffListener() {
            @Override
            public void onStaffClicked(Staff staff) {
                Intent intent = new Intent(getContext(), DetailStaffActivity.class);
                intent.putExtra("staff", staff);
                startActivityForResult(intent, Constants.IS_UPDATE_STAFF);
            }
        });
        binding.rcvStaffList.setAdapter(staffAdapter);
    }
    //Khi cos dữ liệu trả về từ activity khác, hàm này sẽ chạy
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //IS_ADD_STAFF là giá trị mà mk gửi đi, khi activity khác trả về sẽ trả lại giá trị này,
        // mk sẽ dùng nó để xác định xem activity nào trả lại
        //RESULT_OK cũng là giá trị bên kia trả về
        if (requestCode == Constants.IS_ADD_STAFF && resultCode == getActivity().RESULT_OK) {
            //ở đây anh đnag làm kiểu nếu có dữ liệu thay đổi sẽ load lại hoàn toàn dữ liệu.
            // nhưng đúng ra thì chúng ta phải thêm staff mà ta gửi lại từ bên addstaffactivity đó vào trong list và yêu cầu RCV sắp xếp lại
            //điều này sẽ giảm thời gian load, tài nguyên tiêu thụ cũng như hoạt cảnh đẹp

            setRCV(); // làm thế nào thì nhanh. và android cơ bản thì ko cần làm chi tiết
        }
        //viết ở trong hàm nay nhá
        if (requestCode == Constants.IS_UPDATE_STAFF && resultCode == getActivity().RESULT_OK){
            setRCV();
        }
    }

}
