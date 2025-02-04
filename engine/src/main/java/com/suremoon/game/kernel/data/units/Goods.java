package com.suremoon.game.kernel.data.units;

import com.suremoon.game.door.attribute.Buff;
import com.suremoon.game.door.units_itf.GoodsItf;

import java.util.List;

public class Goods extends Unit implements GoodsItf {
    private boolean rpa;
    private int count;
    private String desc;
    private List<Buff> buffs;

    public Goods(int unitId) {
        super(unitId);
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean repeatable() {
        return rpa;
    }

    public List<Buff> getBuffs() {
        return buffs;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public int getGoodsType() {
        // TODO: goods Type should config.
        return 0;
    }
}
