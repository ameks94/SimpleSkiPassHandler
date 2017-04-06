package com.train;

import com.train.skipass.SkiPass;

import java.util.*;
import java.util.stream.Collectors;

/**SkiPass registrar. Contains all skiPasses and their statuses*/
public class SkiPassRegistrator {
    
    //List of cards with their isBlocked values
    Map<SkiPass, Boolean> cardToIsBlocked = new HashMap<>();

    /**Register new skiPass*/
    public void registerSkiPass(SkiPass skiPass) {
        cardToIsBlocked.put(skiPass, false);
    }

    /**Unregister new skiPass*/
    public void unregisterSkiPass(SkiPass skiPass) {
        cardToIsBlocked.remove(skiPass);
    }

    public void blockSkiPass(SkiPass skiPass) {
        if (cardToIsBlocked.containsKey(skiPass)) {
            cardToIsBlocked.put(skiPass, true);
        } else {
            throw new RuntimeException("Can't block nonexistent skiPass.");
        }
    }

    public void unblockSkiPass(SkiPass skiPass) {
        if (cardToIsBlocked.containsKey(skiPass)) {
            cardToIsBlocked.put(skiPass, false);
        } else {
            throw new RuntimeException("Can't unblock nonexistent skiPass.");
        }
    }

    public boolean isSkiPassBlocked(SkiPass skiPass) {
        return cardToIsBlocked.get(skiPass);
    }

    public Set<SkiPass> getRegisteredSkiPasses() {
        return cardToIsBlocked.keySet();
    }

    public Set<SkiPass> getBlockedSkiPasses() {
        return cardToIsBlocked
                .entrySet()
                .stream()
                .filter(map -> map.getValue())
                .map(map -> map.getKey())
                .collect(Collectors.toSet());
    }
}
