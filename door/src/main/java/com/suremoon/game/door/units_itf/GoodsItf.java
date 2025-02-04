package com.suremoon.game.door.units_itf;

public interface GoodsItf extends UnitItf {

    /**
     * 物品描述
     *
     * @return 物品描述
     */
    String getDesc();

    void setDesc(String desc);

    boolean repeatable();

    int getCount();

    int getGoodsType();
}
