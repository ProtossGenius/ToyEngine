package com.suremoon.game.door.nils;

import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.kernel.*;
import com.suremoon.game.door.kernel.manager.GRectMgrItf;

import java.awt.*;
import java.util.ArrayList;

/**
 * to avoid the problem null GRectMgr may caused.
 */
public class NullGRectMgr implements GRectMgrItf {
    Rectangle area = new Rectangle();
    @Override
    public ArrayList<GRectItf> getGRects(Rectangle screen) {
        return new ArrayList<>();
    }

    @Override
    public boolean GRectDo(Rectangle screen, GRectDoItf gRectDo) {
        return false;
    }

    @Override
    public void delGRect(GRectItf rect) {

    }

    @Override
    public void addGRect(GRectItf rect) {

    }

    @Override
    public ArrayList<GRectItf> getAllGRects() {
        return new ArrayList<>();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Rectangle Area() {
        return area;
    }

}
