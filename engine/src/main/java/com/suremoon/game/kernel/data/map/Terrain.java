package com.suremoon.game.kernel.data.map;

import com.suremoon.game.door.kernel.TerrainItf;
import com.suremoon.game.door.kernel.TerrainWalkableItf;
import com.suremoon.game.door.gometry.GRect;
import com.suremoon.game.door.units_itf.BuffItf;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.kernel.initer.buff_init.BuffInfManager;

/**
 * Created by Water Moon on 2017/11/28.
 */
public class Terrain extends GRect implements TerrainItf {//map_unit, like: Grassland, Woodland, Sandy, etc.
    protected int UnitType;
    public static int width = 100, height = 100;
    protected int landHeight;//       待用
    protected String terrainType;//特指T12类型的地图。为位置。
    protected TerrainWalkableItf twj;
//    public boolean WalkableI, WalkabelO;//walkable in/out
    protected final BuffItf[]agbuffs;
    public Terrain(int unitType, String... buffinfs){
        this.UnitType = unitType;
        agbuffs = new BuffItf[buffinfs.length];
        for(int i = 0; i < buffinfs.length; ++i){
            agbuffs[i] = BuffInfManager.bim.productBuff(buffinfs[i]);
        }
    }

//    public boolean walkAble(){
//        int flag = 0;
//        if(WalkabelO && WalkableI)return true;
//        if(!(WalkableI || WalkabelO))return false;
//        switch (terrainType){
//            case "00":
//                return WalkabelO && WalkableI;
//            case "01":
//                return WalkabelO;
//            case "02":
//                return WalkableI;
//            case "21":
//                return WalkableI;
//            default:
//                return WalkabelO;
//        }
//    }

    @Override
    public boolean walkAble(UnitItf unit){
        return twj.walkableJudge(this, unit);
    }

    //=====================================getter and setter=====================================
//    public void setWalkable(boolean in, boolean out){
//        WalkableI = in;
//        WalkabelO = out;
//    }

    public void setTwj(TerrainWalkableItf twj) {
        this.twj = twj;
    }

    @Override
    public int getAGType(){
        return UnitType;
    }

    @Override
    public void setAGType(int i) {
        this.UnitType = i;
    }

    public BuffItf[] provideBuff(){
        return agbuffs;
    }

    @Override
    public String getTerrainType() {
        return "Terrain" + terrainType;
    }

    @Override
    public void setTerrainType(String terrainType) {
        this.terrainType = terrainType;
    }
}
