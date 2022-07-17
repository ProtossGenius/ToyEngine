package com.suremoon.gametest;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;


/**
 * Created by Water Moon on 2018/5/20.
 */
public class RobotTest {

    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        Thread.sleep(5000);
        for(int i = 0; i < 600; ++i){
            int tmp = i % 2, tt =  new Random(System.currentTimeMillis()).nextInt() % 1000;
            robot.mouseMove(300 + 20 * tmp , 300 + 20 * tmp);
            robot.mousePress(KeyEvent.BUTTON1_MASK);
            Thread.sleep(200);
            robot.mouseRelease(KeyEvent.BUTTON1_MASK);
            Thread.sleep(5000 + tt);
        }
    }
}
