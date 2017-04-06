package com.train.limiter.type;

public enum TripsCountType {
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100);

    private final int maxTripsNumber;

    TripsCountType(final int maxTripsNumber) {
        this.maxTripsNumber = maxTripsNumber;
    }

    public int getMaxTripsNumber() { return maxTripsNumber; }
}
