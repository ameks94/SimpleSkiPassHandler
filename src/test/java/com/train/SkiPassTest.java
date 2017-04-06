package com.train;


import com.train.limiter.type.DaysCountType;
import com.train.skipass.type.SkiPassType;
import com.train.limiter.type.TripsCountType;
import com.train.skipass.SkiPass;
import com.train.util.DateTimeHelper;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDateTime;

import static com.train.SkiPassTestHelper.expiredDateTuesday;
import static com.train.SkiPassTestHelper.nowMondayHalfDay;
import static com.train.SkiPassTestHelper.nowSaturdayHalfDay;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DateTimeHelper.class)
public class SkiPassTest extends Assert {
    
    @Before
    public void init() throws Exception {
        PowerMockito.mockStatic(DateTimeHelper.class);
    }
    
    @Test
    public void testSeasonSkiPass() throws Exception {
        mockCurrDateTime(nowSaturdayHalfDay);
        SkiPass seasonSkiPass = SkiPass.createSeasonSkiPass(expiredDateTuesday);
        assertNotNull(seasonSkiPass);
        assertEquals(seasonSkiPass.getType(), SkiPassType.SEASON);
        assertEquals(seasonSkiPass.getExpireDate(), expiredDateTuesday);
        assertNotNull(seasonSkiPass.getId());
        assertTrue(seasonSkiPass.isTripAllowed());

        seasonSkiPass = SkiPass.createSeasonSkiPass(expiredDateTuesday.minusDays(4));
        assertFalse(seasonSkiPass.isTripAllowed());
    }

    @Test
    public void testHolidaySkiPassTrips() throws Exception {
        mockCurrDateTime(nowSaturdayHalfDay);
        testSkiPassTrips(SkiPassType.HOLIDAY);
    }

    @Test
    public void testHolidaySkiPassDays() throws Exception {
        mockCurrDateTime(nowSaturdayHalfDay);
        testSkiPassDays(SkiPassType.HOLIDAY);
    }

    @Test
    public void testWorkdaySkiPassTrips() throws Exception {
        mockCurrDateTime(nowMondayHalfDay);
        testSkiPassTrips(SkiPassType.WORK_DAY);
    }

    @Test
    public void testWorkdaySkiPassDays() throws Exception {
        mockCurrDateTime(nowMondayHalfDay);
        testSkiPassDays(SkiPassType.WORK_DAY);
    }
    
    private void testSkiPassDays(SkiPassType type) {
        SkiPass holidaySkiPassTwoDays = SkiPass.createSkiPass(type, expiredDateTuesday, DaysCountType.TWO_DAYS);
        assertTrue(holidaySkiPassTwoDays.isTripAllowed());

        for (int i = 0; i < 10; i++ ) {
            holidaySkiPassTwoDays.useCardIfAllowed();
        }
        assertTrue(holidaySkiPassTwoDays.isTripAllowed());
    }

    private void testSkiPassTrips(SkiPassType type) {
        SkiPass holidaySkiPassTenTripsCount = SkiPass.createSkiPass(type, expiredDateTuesday, TripsCountType.TEN);
        assertTrue(holidaySkiPassTenTripsCount.isTripAllowed());
        int tripsCount = 0;
        while (holidaySkiPassTenTripsCount.useCardIfAllowed()) {
            tripsCount++;
        }
        assertEquals(tripsCount, TripsCountType.TEN.getMaxTripsNumber());
        assertFalse(holidaySkiPassTenTripsCount.isTripAllowed());
    }
    
    private static void  mockCurrDateTime(LocalDateTime dateTime) throws Exception {
        // spy was used for partical mocking
        PowerMockito.spy(DateTimeHelper.class);
        PowerMockito.doReturn(dateTime.toLocalDate()).when(DateTimeHelper.class, "nowDate");
        PowerMockito.doReturn(dateTime.toLocalTime()).when(DateTimeHelper.class, "nowTime");
        PowerMockito.doReturn(dateTime).when(DateTimeHelper.class, "nowDateTime");
    }
    
    
}
