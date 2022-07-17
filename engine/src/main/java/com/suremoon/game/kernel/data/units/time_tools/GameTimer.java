package com.suremoon.game.kernel.data.units.time_tools;

/**
 * Created by Water Moon on 2017/9/21.
 */
public class GameTimer extends SMPlayer {
    protected static GameTimer gt = new GameTimer();
    protected GameTimer(){
        super();
    }
    public static GameTimer getGt(){
        return gt;
    }
}
