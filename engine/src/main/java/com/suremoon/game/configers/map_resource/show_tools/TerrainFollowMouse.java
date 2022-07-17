package com.suremoon.game.configers.map_resource.show_tools;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Water Moon on 2018/5/25.
 */
public class TerrainFollowMouse {

    public static void showSign(Graphics graphics, BufferedImage bi, Point size, Point mousePos){
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                int x = bi.getRGB(i, j);
                x &= 0x00ffffff;
                x |= 0xAA000000;
                bi.setRGB(i, j, x);
            }
        }
        int ssx = mousePos.x - size.x / 2, ssy = mousePos.y - size.y / 2;
        graphics.drawImage(bi, ssx, ssy, null);
    }
}
