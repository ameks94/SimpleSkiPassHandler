package com.train.limiter.impl;

import com.train.limiter.SkiPassUsageLimiter;
import com.train.util.DateTimeHelper;
import com.train.limiter.type.DaysCountType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DaysCountLimiter implements SkiPassUsageLimiter {
    private DaysCountType daysCountType;
    private List<LocalDate> usedDays = new ArrayList<>();

    public DaysCountLimiter(DaysCountType daysCountType) {
        this.daysCountType = daysCountType;
    }

    /**Add today's day as used*/
    @Override
    public void useSkiPass() {
        LocalDate now = DateTimeHelper.nowDate();
        if (usedDays.contains(now)) {
            usedDays.add(now);
        }
    }

    /**Reject if time is not allowed
     * or all days were used before today*/
    @Override
    public boolean isTripAvailable() {
        LocalDateTime now = DateTimeHelper.nowDateTime();
        // if current time is not allowed for daysCountType
        if (!daysCountType.isAllowedTime(now.toLocalTime()))
            return false;
        // all days were used, and last was not today
        if ( usedDays.size() == daysCountType.getDaysCount() && 
                !usedDays.contains(now.toLocalDate()) )
            return false;

        return true;
    }
}
