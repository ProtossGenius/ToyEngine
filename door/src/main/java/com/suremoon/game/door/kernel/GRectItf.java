package com.suremoon.game.door.kernel;

import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.kernel.manager.GRectMgrItf;
import com.suremoon.game.door.save_about.SerializeAble;

import java.awt.*;

public interface GRectItf extends CalcAble, SerializeAble {
    /**
     * @return ×ª»»³ÉawtµÄRect
     */
    Rectangle toRect();

    /**
     * @return »ñµÃGRectµÄ´óÐ¡
     */
    Dimension getSize();

    /**
     * @param size GRectµÄ´óÐ¡
     */
    void setSize(Point size);

    default void setSize(Dimension size) {
        setSize(size.width, size.height);
    }

    void setSize(int width, int height) ;
    /**Í¨¹ýÔö¼ÓÖµµÄ·½Ê½Ê¹Ö®ÒÆ¶¯,Í¬Ê±¸üÐÂÔÚ¹ÜÀíÆ÷ÖÐµÄÎ»ÖÃ
     * @param cg ÒÆ¶¯ÏòÁ¿
     */
    void moveAdd(PointF cg);

    /**
     * @return ×óÉÏ½ÇµÄÎ»ÖÃ
     */
    PointF getPos();


    /**ÉèÖÃÂä½ÅÎ»ÖÃ
     * @param x ºá×ø±ê
     * @param y ×Ý×ø±ê
     */
    void setPutPos(double x, double y);

    /** ÉèÖÃÂä½ÅÎ»ÖÃ
     * @param pos Î»ÖÃ
     */
    default void setPutPos(Point pos) {
        setPutPos(pos.x, pos.y);
    }

    default void setPutPos(PointF pos) {
        setPutPos(pos.X, pos.Y);
    }

    /** ÉèÖÃ×óÉÏ½ÇÎ»ÖÃ£¬ÇÒ¸üÐÂÆäÔÚmanagerÖÐµÄÎ»ÖÃ
     * @param x ºá×ø±ê
     * @param y ×Ý×ø±ê
     */
    void setPos(double x, double y);

    default void setPos(Point pos) {
        setPos(pos.x, pos.y);
    }

    default void setPos(PointF pos) {
        setPos(pos.X, pos.Y);
    }

    /**
     * ½öÉèÖÃÎ»ÖÃ
     */
    void setPosWithoutUpdateManager(double x, double y);
    default void setPosWithoutUpdateManager(Point pos){
        setPosWithoutUpdateManager(pos.x, pos.y);}
    default void setPosWithoutUpdateManager(PointF pos) {
        setPosWithoutUpdateManager(pos.X, pos.Y);
    }

    /**
     * @return »ñµÃºá×ø±ê
     */
    int getX();

    /**
     * @return »ñµÃ×Ý×ø±ê
     */
    int getY();

    int getWidth();

    int getHeight();

    /**
     * @return »ñµÃ½ÅËùÔÚµÄ×ø±ê
     */
    PointF getFootPos();

    /**
     * @param footPos ½ÅÔÚ¾ØÐÎÖÐµÄÏà¶ÔÎ»ÖÃ
     */
    void setFootPosPro(PointF footPos);

    /**
     * @return ½ÅÔÚ¾ØÐÎÖÐµÄÏà¶ÔÎ»ÖÃ
     */
    PointF getFootPosPro();

    /**
     * @param grm ÉèÖÃËùÔÚµÄ¹ÜÀíÆ÷
     */
    void setGRectMgr(GRectMgrItf grm);

    void setDirect(PointF direct);
    PointF getDirect();


    /**¶ªÆúµÄµ¥Î»»á±»´Ó¼ÆËã¶ÓÁÐ¼°¹ÜÀíÆ÷ÖÐ¶ªÆú
     * @param isDrop ÊÇ·ñÒª¶ªÆú¸Ãµ¥Î»
     */
    void setDrop(boolean isDrop);

    /**
     * @return ÊÇ·ñÒª¶ªÆú¸Ãµ¥Î»
     */
    boolean isDrop();

}
