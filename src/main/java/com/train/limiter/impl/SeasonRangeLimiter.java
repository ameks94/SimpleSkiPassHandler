package com.train.limiter.impl;

import com.train.limiter.SkiPassUsageLimiter;

public class SeasonRangeLimiter implements SkiPassUsageLimiter {

    /** Does nothing, since it has only expiration date*/
    @Override
    public void useSkiPass() {
        
    }

    /** Always true, since it has only expiration date*/
    @Override
    public boolean isTripAvailable() {
        return true;
    }
}
