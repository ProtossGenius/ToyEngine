package com.suremoon.game.door.infos;

import com.suremoon.game.door.client.AGSAdapter;
import com.suremoon.game.door.kernel.TerrainWalkableItf;

/**
 * Created by Water Moon on 2018/3/6.
 */
public class TerrainInformation {
    public TerrainInformation(AGSAdapter shower, String[] buffList, TerrainWalkableItf walkable, boolean isDecorate, String taCol, int interval) {
        this.shower = shower;
        this.buffList = buffList;
        this.isDecorate = isDecorate;
        this.averageColor = taCol;
        this.interval = interval;
        this.twj = walkable;
    }

    private AGSAdapter shower;
    private String[] buffList;
    public boolean isDecorate;
    TerrainWalkableItf twj;
    String averageColor;
    int interval;

    int width = 100, height = 100;
    public AGSAdapter getShower() {
        return shower;
    }

    public TerrainWalkableItf getTwj() {
        return twj;
    }

    public String[] getBuffList() {
        return buffList;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isDecorate() {
        return isDecorate;
    }

    public String getAverageColor() {
        return averageColor;
    }

    public int getInterval() {
        return interval;
    }
}
