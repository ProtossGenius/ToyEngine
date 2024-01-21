package com.suremoon.game.kernel.data.units;

import com.suremoon.game.door.units_itf.GoodsItf;

public class Goods extends Unit implements GoodsItf {

    private String desc;

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
}
