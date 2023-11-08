package com.vanh.timekeeping.listeners;

import com.vanh.timekeeping.entity.Timekeeping;

public interface TimekeepingListener {
    void onAcceptClick();
    void onLateClick();
    void onOffClick();
}
