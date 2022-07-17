package com.suremoon.game.kernel.data.map.walkable;

import com.suremoon.game.door.kernel.TerrainItf;
import com.suremoon.game.door.kernel.TerrainWalkableItf;
import com.suremoon.game.door.units_itf.UnitItf;
import net.sf.json.JSONObject;

/** Created by Water Moon on 2018/4/16. */
public class TerrainOutInWalkable implements TerrainWalkableItf {
  public boolean WalkableI, WalkableO;

  public TerrainOutInWalkable(JSONObject jo) {
    WalkableI = ((String) jo.get("WalkableI")).trim().equals("true");
    WalkableO = ((String) jo.get("WalkableO")).trim().equals("true");
  }

  public TerrainOutInWalkable() {
    WalkableI = WalkableO = false;
  }

  @Override
  public boolean walkableJudge(TerrainItf terrain, UnitItf rect) {
    String terrainType = terrain.getTerrainType();
    if (WalkableO && WalkableI) return true;
    if (!(WalkableI || WalkableO)) return false;
    switch (terrainType) {
      case "00":
        return WalkableO && WalkableI;
      case "02":
      case "21":
        return WalkableI;
      default:
        return WalkableO;
    }
  }
}
