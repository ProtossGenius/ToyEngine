package com.suremoon.game.kernel.data.map.algorithm;

import com.suremoon.game.door.gometry.Connectivity;
import com.suremoon.game.door.kernel.GameMapItf;
import java.awt.*;

public class FindPath {
  public static Path FindPath(GameMapItf gameMap, Point start, Point end) {
    Connectivity connStart = gameMap.getConnectivity(start);
    Connectivity connEnd = gameMap.getConnectivity(end);
    if (connStart.No != connEnd.No) {
      return null;
    }
    Point minBranchPoint = getMinBranchPoint(gameMap, start, end);
    Path pStart = new Path(start);
    return pStart;
  }

  static Point getMinBranchPoint(GameMapItf gameMap, Point start, Point end) {
    return null;
  }
}
