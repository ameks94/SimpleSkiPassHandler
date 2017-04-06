package com.train.skipass;

import com.train.limiter.SkiPassUsageLimiter;
import com.train.limiter.impl.DaysCountLimiter;
import com.train.limiter.impl.SeasonRangeLimiter;
import com.train.limiter.impl.TripsCountLimiter;
import com.train.limiter.type.DaysCountType;
import com.train.skipass.type.SkiPassType;
import com.train.limiter.type.TripsCountType;
import com.train.util.DateTimeHelper;

import java.time.LocalDate;
import java.util.UUID;

public class SkiPass {
    private UUID id;
    private SkiPassType type;
    private LocalDate expireDate;
    private SkiPassUsageLimiter limiter;

    private SkiPass(SkiPassType type, LocalDate expireDate, SkiPassUsageLimiter limiter) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.expireDate = expireDate;
        this.limiter = limiter;
    }

    public static SkiPass createSkiPass(SkiPassType type, LocalDate expiredDate, TripsCountType tripsCountType) {
        if (type.equals(SkiPassType.SEASON))
            throw new RuntimeException("TripsCountType is not allowed for Season skipass");
        return new SkiPass(type, expiredDate, new TripsCountLimiter(tripsCountType));
    }

    public static SkiPass createSkiPass(SkiPassType type, LocalDate expiredDate, DaysCountType daysCountType) {
        if (type.equals(SkiPassType.SEASON))
            throw new RuntimeException("DaysCountType is not allowed for Season skipass");
        return new SkiPass(type, expiredDate, new DaysCountLimiter(daysCountType));
    }

    public static SkiPass createSeasonSkiPass(LocalDate expiredDate) {
        return new SkiPass(SkiPassType.SEASON, expiredDate, new SeasonRangeLimiter());
    }
    
    public boolean isTripAllowed() {
        return !isDateExpired() && 
                type.isSkiPassValidToday() && 
                limiter.isTripAvailable();
    }

    public boolean useCardIfAllowed() {
        if (!isTripAllowed())
            return false;
        limiter.useSkiPass();
        return true;
    }

    public UUID getId() {
        return id;
    }

    public SkiPassType getType() {
        return type;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }
    
    private boolean isDateExpired() {
        return  DateTimeHelper.nowDate().isAfter(expireDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkiPass skiPass = (SkiPass) o;

        if (id != null ? !id.equals(skiPass.id) : skiPass.id != null) return false;
        return type == skiPass.type;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
