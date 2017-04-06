package com.train;

import com.train.skipass.SkiPass;
import org.junit.Test;

import static com.train.SkiPassTestHelper.expiredDateTuesday;
import static org.junit.Assert.assertEquals;

public class GateTest {
    private static final SkiPassRegistrator REGISTRATOR = new SkiPassRegistrator();
    private static final PayGate PAY_GATE = new PayGate(REGISTRATOR);
    
    @Test
    public void testCheckAndUseSkiPass() {
        SkiPass seasonSkiPass = SkiPass.createSeasonSkiPass(expiredDateTuesday);
        REGISTRATOR.registerSkiPass(seasonSkiPass);
        PAY_GATE.checkAndUseCard(seasonSkiPass);
    }

    @Test
    public void testGetRegisteredSkiPasses() {
        SkiPass seasonSkiPass = SkiPass.createSeasonSkiPass(expiredDateTuesday);
        REGISTRATOR.registerSkiPass(seasonSkiPass);
        assertEquals(PAY_GATE.getRegisteredSkiPasses().size(), 1);
    }
    
}
