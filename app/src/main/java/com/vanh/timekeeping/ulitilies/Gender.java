package com.vanh.timekeeping.ulitilies;

public enum Gender {
    MALE( 0),
    FEMALE(1);

    private int intValue;
    private Gender(int value) {
        intValue = value;
    }

    @Override
    public String toString() {
        return intValue+"";
    }
}
