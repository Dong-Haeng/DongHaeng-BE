package com.donghaeng.dev.util;

public enum DayOfWeek {
    월요일(0),
    화요일(1),
    수요일(2),
    목요일(3),
    금요일(4);

    private int value;

    DayOfWeek(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DayOfWeek fromValue(int value) {
        for (DayOfWeek dayOfWeek : values()) {
            if (dayOfWeek.getValue() == value) {
                return dayOfWeek;
            }
        }
        throw new IllegalArgumentException("Invalid value for DayOfWeek: " + value);
    }
}
