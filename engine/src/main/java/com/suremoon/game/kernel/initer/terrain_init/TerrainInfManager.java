package com.suremoon.game.kernel.initer.terrain_init;

import com.suremoon.game.door.factorys.TerrainFactory;
import com.suremoon.game.door.infos.TerrainInformation;
import com.suremoon.game.door.kernel.TerrainItf;
import com.suremoon.game.kernel.data.map.Terrain;
import java.util.HashMap;

/** Created by Water Moon on 2018/3/6. */
public class TerrainInfManager implements TerrainFactory {
  HashMap<Integer, TerrainInformation> tihm;
  public static final TerrainInfManager TIM = new TerrainInfManager();

  public static final TerrainInfManager getTIM() {
    return TIM;
  }

  protected TerrainInfManager() {
    tihm = new HashMap<>();
  }

  public void putTerrainInf(int unitType, TerrainInformation ti) {
    tihm.put(unitType, ti);
  }

  public TerrainInformation getTerrainInf(int unitType) {
    return tihm.get(unitType);
  }

  @Override
  public TerrainItf productTerrain(int unitType) {
    TerrainInformation ti = getTerrainInf(unitType);
    Terrain t = new Terrain(unitType, ti.getBuffList());
    t.setSize(ti.getWidth(), ti.getHeight());
    t.setTwj(ti.getTwj());
    return t;
  }

  public HashMap<Integer, TerrainInformation> getTihm() {
    return tihm;
  }
}
