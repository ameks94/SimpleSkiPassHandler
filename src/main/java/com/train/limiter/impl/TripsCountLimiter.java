package com.train.limiter.impl;

import com.train.limiter.SkiPassUsageLimiter;
import com.train.limiter.type.TripsCountType;

public class TripsCountLimiter implements SkiPassUsageLimiter {

    private TripsCountType tripsCountType;
    private int tripsWasUsed = 0;

    public TripsCountLimiter(TripsCountType tripsCountType) {
        this.tripsCountType = tripsCountType;
    }

    @Override
    public void useSkiPass() {
        ++tripsWasUsed;
    }

    @Override
    public boolean isTripAvailable() {
        if ( tripsCountType.getMaxTripsNumber() == tripsWasUsed )
            return false;
        return true;
    }
}
