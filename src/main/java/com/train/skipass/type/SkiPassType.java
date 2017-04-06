package com.train.skipass.type;

import com.train.util.DateTimeHelper;

public enum SkiPassType {
    HOLIDAY,
    WORK_DAY,
    SEASON;

    public boolean isSkiPassValidToday() {
        switch (this) {
            case HOLIDAY:
                return DateTimeHelper.nowDate().query(DateTimeHelper::isHoliday);
            case SEASON:
                return true;
            case WORK_DAY:
                return !DateTimeHelper.nowDate().query(DateTimeHelper::isHoliday);
        }
        return false;
    }
}
