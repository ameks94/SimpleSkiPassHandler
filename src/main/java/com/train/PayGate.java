package com.train;

import com.train.skipass.SkiPass;

import java.util.Set;

/**Pay gate to work with skiPass*/
public class PayGate {

    private SkiPassRegistrator registrator;

    public PayGate(SkiPassRegistrator registrator) {
        this.registrator = registrator;
    }

    public boolean checkAndUseCard(SkiPass skiPass) {
        if (isSkiPassBlocked(skiPass)) {
            return false;
        }
        return skiPass.useCardIfAllowed();

    }

    /**Is skiPass blocked by the registrator*/
    private boolean isSkiPassBlocked(SkiPass skiPass) {
        return skiPass == null ||
                registrator.isSkiPassBlocked(skiPass);

    }

    public Set<SkiPass> getRegisteredSkiPasses() {
        return registrator.getRegisteredSkiPasses();
    }
}
