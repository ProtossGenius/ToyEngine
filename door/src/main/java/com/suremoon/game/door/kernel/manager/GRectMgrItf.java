package com.suremoon.game.door.kernel.manager;

import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.kernel.GRectDoItf;
import com.suremoon.game.door.kernel.GRectItf;
import com.suremoon.game.door.nils.NullGRectMgr;

import java.awt.*;
import java.util.List;

public interface GRectMgrItf {
    GRectMgrItf Null = new NullGRectMgr();
    List<GRectItf> getGRects(Rectangle screen);
    boolean GRectDo(Rectangle screen, GRectDoItf gRectDo);

    void delGRect(GRectItf rect);

    void addGRect(GRectItf rect);

    default void update(GRectItf rect, double newPosX, double newPosY){
        delGRect(rect);
        rect.setPosWithoutUpdateManager(newPosX, newPosY);
        addGRect(rect);
    }

    default void update(GRectItf rect, PointF newPos){
        update(rect, newPos.X, newPos.Y);
    }

    default void update(GRectItf rect, Point newPos){
        update(rect, newPos.x, newPos.y);
    }

    List<GRectItf> getAllGRects();

    int size();
    Rectangle Area();

    static boolean inArea(Rectangle area, Point p){
        return p.x >= area.x && p.x <= area.x + area.width && p.y >= area.y && p.y <= area.y + area.height;
    }
}
