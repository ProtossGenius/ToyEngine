package com.suremoon.game.kernel.data.map.walkable;

import com.suremoon.game.door.kernel.GRectItf;
import com.suremoon.game.door.kernel.TerrainItf;
import com.suremoon.game.door.error.ErrorDeal;
import com.suremoon.game.door.kernel.TerrainWalkableItf;
import com.suremoon.game.door.units_itf.UnitItf;
import net.sf.json.JSONObject;

import java.lang.reflect.Constructor;

/**
 * Created by Water Moon on 2018/4/17.
 */
public class InnerJarTerrainWalkable implements TerrainWalkableItf {
    TerrainWalkableItf twj;
    public InnerJarTerrainWalkable(JSONObject jo) {
        String className = (String) jo.get("ClassName");
        try {
            Class<?> clz = Class.forName(className);
            Constructor c = clz.getConstructor();
            twj = (TerrainWalkableItf) c.newInstance();
        } catch (Exception e) {
            ErrorDeal.putError("Exception happened in TW_IJ_Judge, exception message is: " + e.getMessage());
        }
    }

    @Override
    public boolean walkableJudge(TerrainItf terrain, UnitItf rect) {
        return twj.walkableJudge(terrain, rect);
    }
}
