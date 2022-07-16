package com.suremoon.game.door.kernel;

import com.suremoon.game.door.factorys.*;
import com.suremoon.game.door.units_itf.EffectItf;
import com.suremoon.game.door.units_itf.UnitItf;

public interface WorldItf extends Runnable, CommandFactory, EffectFactory, StateFactory, TerrainFactory, UnitFactory, BuffFactory {
    /**
     * @return ÊÀ½çËùÓÐµÄGameMap
     */
    GameMapItf getGameMap();

    /**
     * @return ÊÀ½çÃû
     */
    String worldName();

    /**
     * @return Ë÷Òý
     */
    int getWorldIndex();

    int getPieceTime();

    Status getStatus();

    void setStatus(Status status);

    /**
     * ±£´æÊÀ½ç×´Ì¬¡£
     */
    void save();

    /**
     *  ÍÆËÍGRectµ½¼ÆËã¶ÓÁÐÖÐ
     */
    void pushGRectToCalcQueue(GRectItf gRect);

    default void addUnit(UnitItf unit){this.getGameMap().getUnitMgr().addUnit(unit);}
    default void addEffect(EffectItf effect){this.getGameMap().getEffectMgr().addEffect(effect);}
}
