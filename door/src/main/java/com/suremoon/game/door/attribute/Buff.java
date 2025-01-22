package com.suremoon.game.door.attribute;

import java.util.List;

public class Buff {
    private String id;
    private Integer level;
    private List<BuffEffect> buffEffects;

    public Buff(String id, Integer level, List<BuffEffect> buffEffects) {
        this.id = id;
        this.level = level;
        this.buffEffects = buffEffects;
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
