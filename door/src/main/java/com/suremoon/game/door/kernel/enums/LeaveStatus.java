package com.suremoon.game.door.kernel.enums;

public enum LeaveStatus {
  /** 不准备离开 */
  None,
  /** 准备离开 */
  ReadyToLeave,
  /** 已经离开，当状态为Left时保证已经完全从原来的世界中移除。 */
  Left,
}
