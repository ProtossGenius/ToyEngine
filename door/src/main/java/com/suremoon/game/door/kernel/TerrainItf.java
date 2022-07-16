package com.suremoon.game.door.kernel;

import com.suremoon.game.door.units_itf.UnitItf;

public interface TerrainItf extends AGTypeInf, GRectItf {
    /**
     * @param unit ³¢ÊÔÔÚÆäÉÏÐÐ¶¯µÄµ¥Î»
     * @return ÊÇ·ñ¿ÉÒÔÔÚ¸ÃµØÐÎÐÐ×ß
     */
    boolean walkAble(UnitItf unit);

    /**
     * @return µØÍ¼ÏÔÊ¾ÀàÐÍ
     */
    String getTerrainType();

    /**
     * @param terrainType µØÍ¼ÏÔÊ¾ÀàÐÍ
     */
    void setTerrainType(String terrainType);
}
