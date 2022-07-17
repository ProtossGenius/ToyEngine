package com.suremoon.game.kernel.data.map;

import com.suremoon.game.door.code_tools.Pair;
import com.suremoon.game.door.gometry.Connectivity;
import com.suremoon.game.door.infos.MapInformation;
import com.suremoon.game.door.infos.TerrainInformation;
import com.suremoon.game.door.kernel.GameMapItf;
import com.suremoon.game.door.kernel.ImpactCheckerItf;
import com.suremoon.game.door.kernel.TerrainItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.kernel.manager.EffectMgrItf;
import com.suremoon.game.door.kernel.manager.UnitMgrItf;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.kernel.data.map.impact_checker.ImpactCheckerFactory;
import com.suremoon.game.kernel.data.map.managers.GRMEffectMgr;
import com.suremoon.game.kernel.data.map.managers.GRMUnitMgr;
import com.suremoon.game.kernel.initer.terrain_init.TerrainInfManager;
import java.awt.*;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/** Created by Water Moon on 2017/12/16. */
public class GameMap implements GameMapItf {
  protected UnitMgrItf um;
  protected EffectMgrItf em;
  protected ImpactCheckerItf ic;
  private WorldMgrItf worldMgr;
  private WorldItf world;
  /** GameMap should not save mousePoint about message. */
  //    protected Point lastPoint = new Point(0, 0);
  protected int cols = 1000, rows = 1000;

  private int width, height, totalWidth, totalHeight;
  protected TerrainItf[][] map;
  protected Connectivity[][] connectivity;

  public GameMap() {
    this(1, 1, 100, 100);
  }

  public GameMap(int cols, int rows, int width, int height) {
    this(new Terrain[cols][rows], width, height);
  }

  public GameMap(Terrain[][] map, int width, int height) {
    this.map = map;
    initConnectivity();
    checkConnectivity();
    cols = map.length;
    rows = map[0].length;
    ic = ImpactCheckerFactory.Instance.product();
    this.width = width;
    this.height = height;
    totalWidth = width * cols;
    totalHeight = height * rows;
    this.em =
        new GRMEffectMgr(
            new Dimension(totalWidth, totalHeight), new Dimension(width * 5, height * 5));
    this.um =
        new GRMUnitMgr(
            new Dimension(totalWidth, totalHeight), new Dimension(width * 5, height * 5));
  }

  public GameMap(MapInformation mapIfm) {
    changeMap(mapIfm);
    initConnectivity();
    checkConnectivity();
    ic = ImpactCheckerFactory.Instance.product();
  }

  static Point[] nexts =
      new Point[] {new Point(-1, 0), new Point(1, 0), new Point(0, -1), new Point(0, 1)};

  private void fillConnectivity(int x, int y, int no) {
    if (!getTerrain(x, y).walkAble(null)) {
      connectivity[x][y] = new Connectivity(0, 0, false, no);
      return;
    }
    int branchFlag = 0;
    connectivity[x][y] = new Connectivity(0, branchFlag++, true, no);

    Queue<Point> q = new LinkedList();
    q.add(new Point(x, y));

    while (!q.isEmpty()) {
      Point front = q.poll();
      Connectivity frontConn = getConnectivity(front.x, front.y);
      int num = 0;
      for (Point it : nexts) {
        int nextX = front.x + it.x, nextY = front.y + it.y;
        if (isLegalPos(nextX, nextY)
            && connectivity[nextX][nextY] == null
            && getTerrain(nextX, nextY).walkAble(null)) {
          ++num;
          connectivity[nextX][nextY] =
              new Connectivity(frontConn.blockPos, frontConn.branchFlag, true, no);
          q.add(new Point(nextX, nextY));
        }
      }
      if (num > 1) {
        for (Point it : nexts) {
          int nextX = front.x + it.x, nextY = front.y + it.y;
          if (isLegalPos(nextX, nextY) && getTerrain(nextX, nextY).walkAble(null)) {
            connectivity[nextX][nextY].branchFlag = branchFlag++;
          }
        }
      }
    }
  }

  private void initConnectivity() {
    int no = 0;
    connectivity = new Connectivity[map.length][map[0].length];
    if (getTerrain(0, 0) == null) return;
    for (int i = 0; i < map.length; ++i) {
      for (int j = 0; j < map[0].length; ++j) {
        if (connectivity[i][j] == null) {
          fillConnectivity(i, j, no++);
        }
      }
    }
  }

  private void checkConnectivity() {
    for (int i = 0; i < connectivity.length; ++i) {
      if (connectivity[i] == null) {
        System.err.println("Error, connectivity init fail, connectivity[" + i + "] == null");
        break;
      }
      for (int j = 0; j < connectivity[i].length; ++j) {
        if (connectivity[i][j] == null) {
          System.err.println(
              "Error, connectivity init fail, connectivity[" + i + "][" + j + "] == null");
          break;
        }
      }
    }
  }

  @Override
  public void changeMap(MapInformation mapIfm) {
    rows = cols = 1;
    map = new Terrain[1][1];
    int id = 0;
    for (Map.Entry<Integer, TerrainInformation> it : TerrainInfManager.TIM.getTihm().entrySet()) {
      id = it.getKey();
    }
    map[0][0] = TerrainInfManager.TIM.productTerrain(id);
    map[0][0].setTerrainType("00");
    TerrainItf[][] tmp_map;
    tmp_map = new Terrain[mapIfm.getCols()][mapIfm.getRows()];
    Map<Integer, String> list = mapIfm.gettConfs();
    Pair<Integer, Integer>[][] tmap = mapIfm.getTmap();
    ic = ImpactCheckerFactory.Instance.product();
    for (int i = 0; i < mapIfm.getCols(); ++i) {
      for (int j = 0; j < mapIfm.getRows(); ++j) {
        String it = list.get(tmap[i][j].getKey());
        tmp_map[i][j] = TerrainInfManager.getTIM().productTerrain(IDManager.getID(it));
        tmp_map[i][j].setTerrainType(GameMapItf.getType(tmap[i][j].getValue()));
        tmp_map[i][j].setPos(i * Terrain.width, j * Terrain.height);
      }
    }
    this.map = tmp_map;
    cols = mapIfm.getCols();
    rows = mapIfm.getRows();
    width = mapIfm.getTwidth();
    height = mapIfm.getTheight();
    totalWidth = width * cols;
    totalHeight = height * rows;
    this.em =
        new GRMEffectMgr(new Dimension(totalWidth, totalHeight), new Dimension(width, height));
    this.um = new GRMUnitMgr(new Dimension(totalWidth, totalHeight), new Dimension(width, height));
  }

  // =====================================getter and setter=====================================

  @Override
  public int getCols() {
    return cols;
  }

  @Override
  public int getRows() {
    return rows;
  }

  @Override
  public ImpactCheckerItf getImpactChecker() {
    return ic;
  }

  @Override
  public void setImpactChecker(ImpactCheckerItf ic) {
    this.ic = ic;
  }

  @Override
  public int getTerrainWidth() {
    return width;
  }

  @Override
  public int getTerrainHeight() {
    return height;
  }

  @Override
  public int getMapWidth() {
    return totalWidth;
  }

  @Override
  public int getMapHeight() {
    return totalHeight;
  }

  @Override
  public TerrainItf getTerrain(int x, int y) {
    x %= cols;
    if (x < 0) x += cols;
    y %= rows;
    if (y < 0) y += rows;
    return map[x][y];
  }

  public void setTerrain(int x, int y, TerrainItf terrain) {
    x %= cols;
    if (x < 0) x += cols;
    y %= rows;
    if (y < 0) y += rows;
    map[x][y] = terrain;
  }

  @Override
  public Connectivity getConnectivity(int x, int y) {
    if (x >= cols || x < 0) {
      return null;
    }
    if (y >= rows || y < 0) {
      return null;
    }
    return connectivity[x][y];
  }

  @Override
  public GameScreen createGameScreen() {
    return new GameScreen(this);
  }

  public UnitMgrItf getUnitMgr() {
    return um;
  }

  public void setUnitMgr(UnitMgrItf um) {
    this.um = um;
  }

  public EffectMgrItf getEffectMgr() {
    return em;
  }

  public void setEffectMgr(EffectMgrItf em) {
    this.em = em;
  }

  @Override
  public void setWorldMgr(WorldMgrItf worldMgr) {
    this.worldMgr = worldMgr;
  }

  @Override
  public void setWorld(WorldItf world) {
    this.world = world;
  }

  @Override
  public WorldItf getWorld() {
    return this.world;
  }
}
