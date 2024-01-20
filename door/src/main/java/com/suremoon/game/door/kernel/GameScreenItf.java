package com.suremoon.game.door.kernel;

import com.suremoon.game.door.netabout.AGMessage;

import java.awt.*;

public interface GameScreenItf {
    default void setFocusPoint(int x, int y) {
        setFocusPoint(new Point(x, y));
    }

    /**
     * 移动屏幕
     */
    void screenMove();

    /**
     * 显示地图
     *
     * @param gp 画板
     */
    void showMap(Graphics gp);

    /**
     * @return {玩家自身}， {人物对象，ComboUnit}, {效果对象， } }
     */
    AGMessage[][] getShowers();

    /**
     * @return 屏幕矩形
     */
    Rectangle getScreenRect();

    /**
     * @param moveLength 屏幕移动的步长
     */
    void setMoveLength(int moveLength);

    /**
     * @return 屏幕焦点
     */
    Point getFocusPoint();

    /**
     * @param focusPoint 屏幕焦点（摄像机位置）
     */
    void setFocusPoint(Point focusPoint);

    /**
     * @param lastPoint 鼠标的最后位置
     */
    void setLastPoint(Point lastPoint);

    /**
     * 移动屏幕等
     */
    void doCalc();

    /**
     * @return 拥有的GameMap
     */
    GameMapItf getGameMap();

    /**
     * @param gm 所属的Map
     */
    void setGameMap(GameMapItf gm);

    void setWidth(int width);

    void setHeight(int height);
}
