package com.suremoon.game.door.infos;

import com.suremoon.game.door.client.AGSAdapter;
import com.suremoon.game.door.gometry.PointF;

import java.util.HashMap;

/**
 * Created by Water Moon on 2018/3/6.
 */
public class UnitInformation {
    AGSAdapter shower;
    String []sptState;//support state
    HashMap<String, String> saMap;
    String showName;
    int width, height;
    PointF footPos;
    public UnitInformation(AGSAdapter shower, String showName, int width, int height, String[] sptState, PointF footPos, HashMap<String, String> saMap) {
        this.shower = shower;
        this.width = width;
        this.height = height;
        this.sptState = sptState;
        this.footPos = footPos;
        this.saMap = saMap;
        this.showName = showName;
    }

    public HashMap<String, String> getSAMap() {
        return saMap;
    }

    public AGSAdapter getShower() {
        return shower;
    }

    public String[] getSptState() {
        return sptState;
    }

    public String getShowName(){return showName;}

    public PointF getFootPos() {
        return footPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
