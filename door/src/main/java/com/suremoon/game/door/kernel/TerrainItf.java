package com.suremoon.game.door.kernel;

import com.suremoon.game.door.units_itf.UnitItf;

public interface TerrainItf extends AGTypeInf, GRectItf {
  /**
   * @param unit 尝试在其上行动的单位
   * @return 是否可以在该地形行走
   */
  boolean walkAble(UnitItf unit);

  /**
   * @return 地图显示类型
   */
  String getTerrainType();

  /**
   * @param terrainType 地图显示类型
   */
  void setTerrainType(String terrainType);
}
