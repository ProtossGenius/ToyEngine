package com.suremoon.game.ag_pc_client.show.adapters.terrain;

import com.suremoon.game.door.client.AGSAdapter;
import com.suremoon.game.door.kernel.TerrainItf;
import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.kernel.initer.terrain_init.TerrainInfManager;
import com.suremoon.game.door.infos.TerrainInformation;

import java.awt.*;

/**
 * Created by Water Moon on 2018/3/21.
 */
public class TerrainSAdapter {
    AGSAdapter shower;
    int interval;
    public TerrainSAdapter(int terrainType){
        TerrainInformation ti = TerrainInfManager.TIM.getTerrainInf(terrainType);
        shower = ti.getShower();
        interval = ti.getInterval();
    }
    public void show(Graphics gp, TerrainItf terrain, Point point){
        shower.show(gp, terrain.getTerrainType(), terrain, PointF.DIRECTION_ZERO, System.currentTimeMillis(), interval, true, point);
    }
}
