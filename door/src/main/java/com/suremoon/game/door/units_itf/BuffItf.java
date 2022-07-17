package com.suremoon.game.door.units_itf;

import com.suremoon.game.door.kernel.CalcAble;
import com.suremoon.game.door.nils.NullBuff;

public interface BuffItf extends CalcAble {

  /**
   * 设置Buff的拥有者
   *
   * @param u Unit
   * @return 自身
   */
  BuffItf setOwner(UnitItf u);

  /**
   * @return 这个buff是否依旧有效，如果失效的话将移除该Buff。
   */
  boolean isAlive();

  BuffItf Null = new NullBuff();
}
