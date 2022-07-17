package com.suremoon.game.configers;

import com.suremoon.game.door.client.AGSAdapter;
import com.suremoon.game.door.gometry.GRect;
import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.ag_pc_client.resource.image.PicAreaArray;

import java.awt.*;

/**
 * Created by Water Moon on 2018/3/30.
 */
public class JPreview extends JPicture {
    public JPreview() {
        super();
    }

    Rectangle showRect = new Rectangle(0, 0, 100, 100);
    protected AGSAdapter agsAdapter;
    protected String name;
    protected int intervalTime = 60;
    protected PointF direct = PointF.DIRECTION_ZERO;
    public int state;
    public boolean inRefresh = true;
    public boolean isSuit = true;
    public static int STATE_RUNNING = 0, STATE_STOPED = 1;

    public void showAgsAdapter() {
        if (state == STATE_RUNNING) return;
        state = STATE_RUNNING;
        new Thread(()->{
            Graphics gp = getGraphics();
            long start = System.currentTimeMillis();
            while (STATE_RUNNING == state) {
                long now = System.currentTimeMillis();
                agsAdapter.show(gp, name, new GRect(showRect), direct, now - start, intervalTime, true, new Point(0, 0));
                if (inRefresh)
                    refresh();
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void close() {
        state = STATE_STOPED;
    }

    public AGSAdapter getAgsAdapter() {
        return agsAdapter;
    }

    public void setAgsAdapter(AGSAdapter agsAdapter, String name) {
        this.agsAdapter = agsAdapter;
        this.name = name;
    }

    public void setPicAreaArray(PicAreaArray paa) {
        AGSAdapter agsAdapter = new AGSAdapter();
        agsAdapter.getResList().put(name = "Resource", paa);
        setAgsAdapter(agsAdapter, name);
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public void setDirect(PointF direct) {
        this.direct = direct;
    }

    public void setShowRect(Rectangle showRect) {
        this.showRect = showRect;
    }

    @Override
    public void refresh() {
        if (isSuit) {
            setShowRect(new Rectangle(0, 0, getWidth(), getHeight()));
        }
        super.refresh();
    }

    public void setInRefresh(boolean inRefresh) {
        this.inRefresh = inRefresh;
    }

    public void setShowName(String name) {
        this.name = name;
    }

    public void SuitCompont(boolean isSuit) {
        this.isSuit = isSuit;
    }

}
