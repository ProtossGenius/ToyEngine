package com.suremoon.game.configers.map_resource.show_tools;

import com.suremoon.game.kernel.data.GameConfig.GameConfiger;
import com.suremoon.game.kernel.data.map.GameScreen;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Water Moon on 2018/5/24.
 */
public class ChoicedTerrainSign {
    static BufferedImage bi;
    static GameConfiger gc = GameConfiger.getGC();
    static {
        bi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                bi.setRGB(i, j, 0x55ff0000);
            }
        }
    }
    public static void showSign(Graphics graphics, GameScreen gameScreen, Point size, Point mousePos){
        int px = gameScreen.getFocusPoint().x % size.x, py = gameScreen.getFocusPoint().y % size.y;
        int ssx = (mousePos.x + px) / size.x, ssy = (mousePos.y + py) / size.y;
        graphics.drawImage(bi, (ssx) * size.x - px, (ssy) * size.y-py, null);
    }
}
