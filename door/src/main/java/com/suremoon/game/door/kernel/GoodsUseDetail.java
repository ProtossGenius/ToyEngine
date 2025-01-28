package com.suremoon.game.door.kernel;

import java.awt.*;

public class GoodsUseDetail {
    private Integer selectedIndex;
    private char key;
    private Point targetPoint;

    public Integer getSelectedIndex() {
        return selectedIndex;
    }

    public GoodsUseDetail(Integer selectedIndex, char key, Point targetPoint) {
        this.selectedIndex = selectedIndex;
        this.key = key;
        this.targetPoint = targetPoint;
    }

    public char getKey() {
        return key;
    }

    public Point getTargetPoint() {
        return targetPoint;
    }
}
