package com.suremoon.game.door.client;

import com.suremoon.game.door.error.ErrorDeal;
import com.suremoon.game.door.gometry.GRect;
import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.kernel.GRectItf;
import com.suremoon.game.door.tools.IDManager;

import java.awt.*;
import java.util.TreeMap;

/**
 * Created by Water Moon on 2018/1/12.
 */
public class AGSAdapter {
    public final static String directList[] = {"e", "w", "s", "n", "se", "sw", "ne", "nw"};
    public final static PointF directPointList[] = new PointF[8];

    static {
        setDP(0, 1, 0);
        setDP(1, -1, 0);
        setDP(2, 0, 1);
        setDP(3, 0, -1);
        setDP(4, 1, 1);
        setDP(5, -1, 1);
        setDP(6, 1, -1);
        setDP(7, -1, -1);
    }

    public TreeMap<String, PicAreaArrayItf> resList;
    public TreeMap<String, String> state2action;//type is state type//only for unit.
    public int direct_nums;

    public AGSAdapter() {
        direct_nums = 8;
        resList = new TreeMap<>();
        state2action = new TreeMap<>();
    }

    public AGSAdapter(int direct_nums) {
        this();
        if (direct_nums != 4) direct_nums = 8;
        this.direct_nums = direct_nums;
    }

    public TreeMap<String, PicAreaArrayItf> getResList() {
        return resList;
    }

    public TreeMap<String, String> getState2action() {
        return state2action;
    }

    public synchronized void show(Graphics gp, String name, GRectItf rect, PointF direct, long passedTime, long intervalTime, boolean isLoop, Point focusPoint) {
        String direct_str = getDirect(direct, 8);
        PicAreaArrayItf paa = resList.get(name + direct_str);
        if (paa == null) {
            ErrorDeal.putError("error happened in AgsAdapter, name not exists: " + name);
            return;
        }
        int id = (int) (passedTime / intervalTime);
        if (id >= paa.getLength()) {
            if (isLoop) id %= paa.getLength();
            else id = paa.getLength() - 1;
        }
        PicAreaItf pa = paa.getPicArea(id);
        pa.show(gp, (int) (rect.getX() - focusPoint.getX()), (int) (rect.getY() - focusPoint.getY()), rect.getWidth(), rect.getHeight());
    }

    public void show(Graphics gp, int typeId, GRect rect, PointF direct, long passedTime, long intervalTime, boolean isLoop, Point focusPoint) throws Exception {
        String type = IDManager.getName(Integer.valueOf(typeId));
        String name = state2action.get(type);
        if (name == null || name.equals("")) {
            throw new Exception("error happened in AgsAdapter, state-name pear not exists: action name is: " + name + "  , type name is: " + type + "  , type id is: " + typeId);
        }
        show(gp, name, rect, direct, passedTime, intervalTime, isLoop, focusPoint);
    }

    protected static void setDP(int id, int x, int y) {
        directPointList[id] = PointF.DIRECTION_ZERO.getDirection(new PointF(x, y));
    }

    public String getDirect(PointF pf, int direct_nums) { //direct_nums == (8 or 4) if not equ 8  then think eqa 4
        if (pf.equals(PointF.DIRECTION_ZERO)) return "";// for map, effect, etc.
        double mindis = 2;
        int id = 0;
        if (direct_nums != 8 && direct_nums != 4) direct_nums = 4;
        for (int i = 0; i < direct_nums; ++i) {
            double tm = PointF.getDistance(pf, directPointList[i]);
            if (tm < mindis) {
                mindis = tm;
                id = i;
            }
        }
        return " " + directList[id];
    }

    public void setResList(TreeMap<String, PicAreaArrayItf> resList) {
        this.resList = resList;
    }

    public void setState2action(TreeMap<String, String> state2action) {
        this.state2action = state2action;
    }

}
