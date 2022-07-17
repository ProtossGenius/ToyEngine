package com.suremoon.game.door.client;

/**
 * StateAction 目前主要用于技能的指示器，在某些按键的情况下出发技能指示器。
 * 将来可能用于其它的部分。
 */
public class StateAction {
    public static final int NOTHING = 0, POINTER = 1;
    public static final int POINTER_LINE = 11, POINTER_CIRCLE = 12, POINTER_AREA = 13;
    public int width;
    public int sa_type = NOTHING, sa_showType;
}
