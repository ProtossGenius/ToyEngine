package com.suremoon.game.door.attribute;

import java.util.List;

public class Buff {
    private String id;
    private Integer level;
    private List<BuffEffect> buffEffects;
    private final String nextBuff;
    private final String lastBuff;

    public Buff(String id, Integer level, List<BuffEffect> buffEffects, String nextBuff, String lastBuff) {
        this.id = id;
        this.level = level;
        this.buffEffects = buffEffects;
        this.nextBuff = nextBuff;
        this.lastBuff = lastBuff;
    }

    public String getId() {
        return id;
    }

    public Integer getLevel() {
        return level;
    }

    public String getNextBuff() {
        return nextBuff;
    }

    public String getLastBuff() {
        return lastBuff;
    }

    public List<BuffEffect> getBuffEffects() {
        return buffEffects;
    }
}
