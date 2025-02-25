package com.suremoon.game.kernel.data.map;

import com.suremoon.game.ag_pc_client.show.adapters.terrain.TerrainSAGetter;
import com.suremoon.game.door.kernel.GameMapItf;
import com.suremoon.game.door.kernel.GameScreenItf;
import com.suremoon.game.door.netabout.AGMessage;
import com.suremoon.game.kernel.data.GameConfig.GameConfiger;

import java.awt.*;

/**
 * Created by Water Moon on 2017/12/28.
 */
public class GameScreen implements GameScreenItf {
    protected Point focusPoint = new Point(0, 0); // left_up point
    GameMapItf gameMap;
    Point lastPoint = new Point(0, 0);
    int moveLength = 5;
    private int width = GameConfiger.DESIGN_SCREEN_WIDTH;
    private int height = GameConfiger.DESIGN_SCREEN_HEIGHT;

    public GameScreen(GameMapItf gameMap) {
        this.gameMap = gameMap;
    }

    private void screenMove(int x, int y) {
        setFocusPoint(focusPoint.x + x, focusPoint.y + y);
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void screenMove() {
        GameConfiger gc = GameConfiger.getGC();
        Point ep = lastPoint;
        if (ep.x >= gc.getDesignScreenWdith() * 0.9) {
            screenMove(moveLength, 0);
        } else if (ep.x <= gc.getDesignScreenWdith() * 0.1) {
            screenMove(-moveLength, 0);
        }

        if (ep.y >= gc.getDesignScreenHeight() * 0.9) {
            screenMove(0, moveLength);
        } else if (ep.y <= gc.getDesignScreenHeight() * 0.1) {
            screenMove(0, -moveLength);
        }
    }

    public void showMap(Graphics gp) {
        int stx = focusPoint.x / gameMap.getTerrainWidth(),
                sty = focusPoint.y / gameMap.getTerrainHeight(),
                cols = width / gameMap.getTerrainWidth(),
                rows = height / gameMap.getTerrainHeight();
        for (int i = stx - 1; i <= stx + cols + 1; ++i) {
            for (int j = sty - 1; j <= sty + rows + 1; ++j) {
                TerrainSAGetter.getTSAG().show(gp, gameMap.getTerrain(i, j), focusPoint);
            }
        }
    }

    /**
     * { {0： 玩家自身}， {1： 人物对象，ComboUnit}, {2： 效果对象， } {3： 消息列表} {4: user bag info} }
     */
    @Override
    public AGMessage[][] getShowers() {
        AGMessage[][] res = new AGMessage[5][];
        res[0] = new AGMessage[1];
        res[1] = gameMap.getUnitMgr().getShowers(getScreenRect());
        res[2] = gameMap.getEffectMgr().getShowers(getScreenRect());
        res[3] = new AGMessage[0];
        return res;
    }

    @Override
    public Rectangle getScreenRect() {
        return new Rectangle(
                focusPoint.x,
                focusPoint.y,
                width,
                height);
    }

    @Override
    public void setMoveLength(int moveLength) {
        this.moveLength = moveLength;
    }

    @Override
    public Point getFocusPoint() {
        return focusPoint;
    }

    @Override
    public void setFocusPoint(Point focusPoint) {
        this.focusPoint = focusPoint;
        if (focusPoint.x < 0) focusPoint.x = 0;
        if (focusPoint.y < 0) focusPoint.y = 0;
        if (focusPoint.x > gameMap.getMapWidth() - width)
            focusPoint.x = gameMap.getMapWidth() - width;
        if (focusPoint.y > gameMap.getMapHeight() - height)
            focusPoint.y = gameMap.getMapHeight() - height;
    }

    @Override
    public void setLastPoint(Point lastPoint) {
        this.lastPoint = lastPoint;
    }

    @Override
    public void doCalc() {
        screenMove();
    }

    @Override
    public GameMapItf getGameMap() {
        return this.gameMap;
    }

    public Point getLastPoint() {
        return lastPoint;
    }

    @Override
    public void setGameMap(GameMapItf gm) {
        this.gameMap = gm;
    }
}
