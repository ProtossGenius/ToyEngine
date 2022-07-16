package com.suremoon.game.door.infos;

import com.suremoon.game.door.client.AGSAdapter;
import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.kernel.EffectActionItf;

/**
 * Created by Water Moon on 2018/4/10.
 */
public class EffectInformation {
    EffectActionItf ea;
    protected AGSAdapter shower;
    protected int width, height;
    private int intervalTime; // mill-sec
    boolean isLoop;
    private PointF footPos;
    public EffectInformation(AGSAdapter shower, EffectActionItf ea, int width, int height, int intervalTime, PointF footPos, boolean isLoop) {
        this.shower = shower;
        this.ea = ea;
        this.width = width;
        this.height = height;
        this.intervalTime = intervalTime;
        this.isLoop = isLoop;
        this.footPos = footPos;
    }

    public AGSAdapter getShower(){
        return shower;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EffectActionItf getEffectAction() {
        return ea;
    }

    public boolean isLoop() {
        return isLoop;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public PointF getFootPos() {
        return footPos;
    }
}
