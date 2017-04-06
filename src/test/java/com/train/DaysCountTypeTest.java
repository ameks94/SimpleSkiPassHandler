package com.train;

import com.train.limiter.type.DaysCountType;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DaysCountTypeTest {
    
    @Test
    public void testHALF_DAY_FIRST() {
        assertTrue(DaysCountType.ONE_DAY_FIRST_PART.isAllowedTime(LocalTime.parse("10:25")));
        assertFalse(DaysCountType.ONE_DAY_FIRST_PART.isAllowedTime(LocalTime.parse("13:25")));
    }

    @Test
    public void testHALF_DAY_SECOND() {
        assertTrue(DaysCountType.ONE_DAY_SECOND_PART.isAllowedTime(LocalTime.parse("13:25")));
        assertFalse(DaysCountType.ONE_DAY_SECOND_PART.isAllowedTime(LocalTime.parse("18:25")));
    }

    @Test
    public void test_ONE_DAY_TWO_DAYS_FIVE_DAYS() {
        assertTrue(DaysCountType.ONE_DAY.isAllowedTime(LocalTime.parse("13:25")));
        assertTrue(DaysCountType.TWO_DAYS.getDaysCount() == 2);
        assertTrue(DaysCountType.FIVE_DAYS.getEndTime().equals(LocalTime.parse("17:00")));
    }

}
