package com.suremoon.game.door.kernel;

import com.suremoon.game.door.gometry.Connectivity;
import com.suremoon.game.door.infos.MapInformation;
import com.suremoon.game.door.kernel.manager.EffectMgrItf;
import com.suremoon.game.door.kernel.manager.UnitMgrItf;
import java.awt.*;

/** 游戏地图接口 */
public interface GameMapItf {

  /**
   * 更换地图信息，这个方法主要是在地图编辑器里用到
   *
   * @param mapInfo 地图信息
   */
  void changeMap(MapInformation mapInfo);

  /**
   * @param value 地图是由四行三列的图片所描述的，在存储的时候会把它存储为一个数字
   * @return 将存储的数字变更成地形的状态（用于显示）
   */
  static String getType(Integer value) {
    int first = value / 10, second = value % 10;
    return "" + first + second;
  }

  /**
   * @return 地图的列数
   */
  int getCols();

  /**
   * @return 地图的行数
   */
  int getRows();

  default boolean isLegalPos(int x, int y) {
    return x >= 0 && x < getCols() && y >= 0 && y < getRows();
  }

  /**
   * @return 获取碰撞检测器
   */
  ImpactCheckerItf getImpactChecker();

  /**
   * @param ic 设置碰撞检测器
   */
  void setImpactChecker(ImpactCheckerItf ic);

  /**
   * @return 获得地图内地形矩形的宽度
   */
  int getTerrainWidth();

  /**
   * @return 获得地图内地形矩形的高度
   */
  int getTerrainHeight();

  /**
   * @return 地图的总宽度
   */
  int getMapWidth();

  /**
   * @return 地图的总高度
   */
  int getMapHeight();

  /**
   * 获取地形
   *
   * @param x 地形索引在x方向的值
   * @param y 地形索引在y方向的值
   * @return 处于(x,y)位置的地形
   */
  TerrainItf getTerrain(int x, int y);

  default TerrainItf getTerrain(Point p) {
    return getTerrain(p.x, p.y);
  }

  void setTerrain(int x, int y, TerrainItf terrain);

  /**
   * @param x 地形索引在x方向的值
   * @param y 地形索引在y方向的值
   * @return 处于(x,y)位置的地形连通性
   */
  Connectivity getConnectivity(int x, int y);

  default Connectivity getConnectivity(Point p) {
    return getConnectivity(p.x, p.y);
  }

  GameScreenItf createGameScreen();

  UnitMgrItf getUnitMgr();

  void setUnitMgr(UnitMgrItf um);

  EffectMgrItf getEffectMgr();

  void setEffectMgr(EffectMgrItf em);
  /**
   * @param worldMgr 设置世界管理器
   */
  void setWorldMgr(WorldMgrItf worldMgr);

  /**
   * @param world 设置当前地图所属的世界
   */
  void setWorld(WorldItf world);
  /**
   * @return 当前地图所属的世界
   */
  WorldItf getWorld();
}
