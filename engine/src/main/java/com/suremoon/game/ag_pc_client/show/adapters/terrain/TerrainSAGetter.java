package com.suremoon.game.ag_pc_client.show.adapters.terrain;

import com.suremoon.game.door.kernel.TerrainItf;
import com.suremoon.game.kernel.data.map.Terrain;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by Water Moon on 2018/3/21.
 */
public class TerrainSAGetter {
    public static final TerrainSAGetter TSAG = new TerrainSAGetter();
    HashMap<Integer, TerrainSAdapter> tsas;
    protected TerrainSAGetter(){
        tsas = new HashMap<>();
    }
    public static TerrainSAGetter getTSAG(){
        return TSAG;
    }
    public TerrainSAdapter getTsa(int terrainType){
        if(tsas.containsKey(terrainType)){
            return tsas.get(terrainType);
        }else{
            TerrainSAdapter tsa = new TerrainSAdapter(terrainType);
            tsas.put(terrainType, tsa);
            return tsa;

        }
    }
    public void show(Graphics gp, TerrainItf terrain, Point focusPoint){
        getTsa(terrain.getAGType()).show(gp, terrain, focusPoint);
    }
}
