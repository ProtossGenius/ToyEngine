package com.suremoon.game.door.kernel;

import com.suremoon.game.door.units_itf.UnitItf;

/**
 * Created by Water Moon on 2018/4/16.
 */
public interface TerrainWalkableItf {
    boolean walkableJudge(TerrainItf terrain, UnitItf rect);
}
