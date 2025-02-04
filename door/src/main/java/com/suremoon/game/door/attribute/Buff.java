package com.suremoon.game.door.attribute;

import java.util.List;

public class Buff {
    private String id;
    private Integer level;
    private List<BuffEffect> buffEffects;
    private final Integer maxCount;

    public Buff(String id, Integer level, List<BuffEffect> buffEffects, Integer maxCount) {
        this.id = id;
        this.level = level;
        this.buffEffects = buffEffects;
        this.maxCount = maxCount;
    }

    public String getId() {
        return id;
    }

    public Integer getLevel() {
        return level;
    }

    public List<BuffEffect> getBuffEffects() {
        return buffEffects;
    }
}
