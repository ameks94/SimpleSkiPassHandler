package com.train.limiter;

/**Additional usage limitator*/
public interface SkiPassUsageLimiter {
    void useSkiPass();
    boolean isTripAvailable();
}
