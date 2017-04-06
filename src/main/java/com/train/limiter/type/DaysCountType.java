package com.train.limiter.type;

import java.time.LocalTime;

public enum DaysCountType {

    ONE_DAY_FIRST_PART(1, parseTime("09:00"), parseTime("13:00")),
    ONE_DAY_SECOND_PART(1, parseTime("13:00"), parseTime("17:00")),
    ONE_DAY(1, parseTime("09:00"), parseTime("17:00")),
    TWO_DAYS(2, parseTime("09:00"), parseTime("17:00")),
    FIVE_DAYS(3, parseTime("09:00"), parseTime("17:00"));

    private int daysCount;
    private LocalTime startTime;
    private LocalTime endTime;

    DaysCountType(final int daysCount, LocalTime startTime, LocalTime endTime) {
        this.daysCount = daysCount;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isAllowedTime(LocalTime time) {
        return time.isAfter(startTime) && time.isBefore(endTime);
    }

    private static LocalTime parseTime(String time) {
        return LocalTime.parse(time);
    }

    public int getDaysCount() {
        return daysCount;
    }
    
    public LocalTime getEndTime() {
        return endTime;
    }

}
