package com.suremoon.game.door.kernel;

import com.suremoon.game.door.netabout.AGMessage;

import java.awt.*;

public interface GameScreenItf {
    /**
     * @param focusPoint ÆÁÄ»½¹µã£¨ÉãÏñ»úÎ»ÖÃ£©
     */
    void setFocusPoint(Point focusPoint);

    default void setFocusPoint(int x, int y) {
        setFocusPoint(new Point(x, y));
    }

    /**
     * ÒÆ¶¯ÆÁÄ»
     */
    void screenMove();

    /**
     * ÏÔÊ¾µØÍ¼
     * @param gp »­°å
     */
    void showMap(Graphics gp);
    /**
     * @return
     * {Íæ¼Ò×ÔÉí}£¬
     * {ÈËÎï¶ÔÏó£¬ComboUnit},
     * {Ð§¹û¶ÔÏó£¬ }
     * }
     */
    AGMessage[][] getShowers();

    /**
     * @return ÆÁÄ»¾ØÐÎ
     */
    Rectangle getScreenRect();

    /**
     * @param moveLength ÆÁÄ»ÒÆ¶¯µÄ²½³¤
     */
    void setMoveLength(int moveLength);

    /**
     * @return ÆÁÄ»½¹µã
     */
    Point getFocusPoint();

    /**
     * @param lastPoint Êó±êµÄ×îºóÎ»ÖÃ
     */
    void setLastPoint(Point lastPoint);

    /**
     * ÒÆ¶¯ÆÁÄ»µÈ
     */
    void doCalc();

    /**
     * @param gm ËùÊôµÄMap
     */
    void setGameMap(GameMapItf gm);


    /**
     * @return ÓµÓÐµÄGameMap
     */
    GameMapItf getGameMap();
}
