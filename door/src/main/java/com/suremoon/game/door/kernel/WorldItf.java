package com.suremoon.game.door.kernel;

import com.suremoon.game.door.factorys.*;
import com.suremoon.game.door.units_itf.EffectItf;
import com.suremoon.game.door.units_itf.UnitItf;

public interface WorldItf
        extends Runnable,
        CommandFactory,
        EffectFactory,
        StateFactory,
        TerrainFactory,
        UnitFactory,
        BuffFactory {
    /**
     * @return 世界所有的GameMap
     */
    GameMapItf getGameMap();

    /**
     * @return 世界名
     */
    String worldName();

    /**
     * @return 索引
     */
    int getWorldIndex();

    int getPieceTime();

    Status getStatus();

    void setStatus(Status status);

    /**
     * 保存世界状态。
     */
    void save();

    /**
     * 推送GRect到计算队列中
     */
    void pushGRectToCalcQueue(GRectItf gRect);

    default void addCalcUnit(UnitItf unit) {
        if (unit == null) {
            return;
        }
        pushGRectToCalcQueue(unit);
        this.getGameMap().getUnitMgr().addUnit(unit);
    }

    default void addCalcEffect(EffectItf effect) {
        if (effect == null) {
            return;
        }
        pushGRectToCalcQueue(effect);
        this.getGameMap().getEffectMgr().addEffect(effect);
    }
}
