package com.suremoon.game.kernel.data.GameConfig;

import java.awt.*;
import java.util.Random;

/**
 * Created by Water Moon on 2017/12/17.
 */
public class GameConfiger {
    public final static GameConfiger gc = new GameConfiger();

    public static GameConfiger getGC() {
        return gc;
    }

    protected GameConfiger() {
        random = new Random(System.currentTimeMillis());
    }
    protected int width, height;
    protected Random random;
    public static final int DESIGN_SCREEN_WIDTH = 1920, DESIGN_SCREEN_HEIGHT = 1080;

    public int getDesignScreenWdith() {
        return DESIGN_SCREEN_WIDTH;
    }


    public int getDesignScreenHeight() {
        return DESIGN_SCREEN_HEIGHT;
    }

    public int getScreenWidth(){return width;}
    public int getScreenHeight(){return height;}

    public void setScreenWidth(int width) {
        this.width = width;
    }

    public void setScreenHeight(int height) {
        this.height = height;
    }
    public void setScreenSize(Point point){
        this.width = point.x;
        this.height = point.y;
    }
    public void setScreenSize(int width, int height){
        this.width = width;
        this.height = height;
    }
    public Point getDesignPos(Point ep){
        return new Point(ep.x * DESIGN_SCREEN_WIDTH / width, ep.y * DESIGN_SCREEN_HEIGHT / height);
    }

    public final Random getRandom() {
        return random;
    }
}

