package com.vanh.timekeeping.listeners;

import com.vanh.timekeeping.entity.Staff;
import com.vanh.timekeeping.entity.Timekeeping;

public interface TimekeepingListener {
    //ko dùng các sự kiện này nữa
//    void onAcceptClick(Staff staff);
//    void onLateClick(Staff staff);
//    void onOffClick(Staff staff);

        void onTimekeeingClick(Staff staff, int status);
}
