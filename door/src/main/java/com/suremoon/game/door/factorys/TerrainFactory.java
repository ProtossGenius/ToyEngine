package com.suremoon.game.door.factorys;

import com.suremoon.game.door.infos.TerrainInformation;
import com.suremoon.game.door.kernel.TerrainItf;
import com.suremoon.game.door.tools.IDManager;

public interface TerrainFactory {
    TerrainItf productTerrain(int unitType);
    TerrainInformation getTerrainInf(int unitType);
    default TerrainInformation getTerrainInf(String terrain_name){
        return getTerrainInf(IDManager.getID(terrain_name));
    }
    void putTerrainInf(int unitType, TerrainInformation ti);
    default void putTerrainInf(String terrain_name, TerrainInformation ti){
        putTerrainInf(IDManager.getID(terrain_name), ti);
    }
}
