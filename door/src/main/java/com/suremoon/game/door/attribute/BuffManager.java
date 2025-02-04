package com.suremoon.game.door.attribute;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

public class BuffManager {
    private Map<String, Buff> buffMap;
    private Map<String, BuffEffect>[] buffs;
    private SMAttribute basic;
    private SMAttribute after;
    private Function<String, Buff> buffGetter;

    public BuffManager(SMAttribute basic, SMAttribute after, Function<String, Buff> buffGetter) {
        this.basic = basic;
        this.after = after;
        buffMap = new HashMap<>();
        buffs = new Map[100];
        basic.setOnChange(attr -> this.recalcAttrib());
        this.buffGetter = buffGetter;
    }

    public synchronized boolean containsBuff(String buffName) {
        return buffMap.containsKey(buffName);
    }

    public synchronized void addBuff(String buffName) {
        var buff = buffMap.get(buffName);
        if (buff == null) {
            addBuff(buffGetter.apply(buffName));
        }
    }

    private synchronized void addBuff(Buff b) {
        if (b == null) {
            return;
        }
        if (buffs[b.getLevel()] == null) {
            buffs[b.getLevel()] = new TreeMap<>();
        }
        var buffEffectMap = buffs[b.getLevel()];
        b.getBuffEffects().forEach(be -> {
            var totalBe = buffEffectMap.get(be.getKey());
            if (totalBe == null) {
                totalBe = be.createTotalBuffEffect();
            }
            totalBe.add(be);
            buffEffectMap.put(be.getKey(), totalBe);
        });
    }

    public synchronized void removeBuff(String buffName) {
        var b = buffMap.get(buffName);
        if (b == null) {
            return;
        }

        var buffEffectMap = buffs[b.getLevel()];
        b.getBuffEffects().forEach(be -> {
            var totalBe = buffEffectMap.get(be.getKey());
            if (totalBe == null) {
                totalBe = be.createTotalBuffEffect();
            }
            totalBe.remove(be);
            buffEffectMap.put(be.getKey(), totalBe);
        });
        buffMap.remove(buffName);
        recalcAttrib();
    }

    private synchronized void recalcAttrib() {
        after.copy(basic);
        for (int i = 0; i < buffs.length; i++) {
            if (buffs[i] == null || buffs[i].isEmpty()) {
                continue;
            }
            buffs[i].forEach((key, be) -> {
                be.parseBuff(after);
            });
        }
    }
}
