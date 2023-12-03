package com.vanh.timekeeping.ulitilies;

public enum Status {
    ON_TIME("On Time", 0),
    LATE_ARRIVAL("Late Arrival", 1),

    DAY_OFF("Day Off", 2);

    private String stringValue;
    private int intValue;
    private Status(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
//bài tập ở đây