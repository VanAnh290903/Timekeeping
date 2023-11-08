package com.vanh.timekeeping.listeners;

import com.vanh.timekeeping.entity.Staff;

public interface StaffListener {
    void onStaffClicked(Staff staff);
}
//vd:interface như 1 bộ khung với các bộ phận a, b, c
//nếu cái nào muốn sử dụng bộ khung này thì sẽ bắt buộc phải có các bộ phận đó