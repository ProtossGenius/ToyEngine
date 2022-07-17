package com.suremoon.game.configers.map_resource.show_tools;

import com.suremoon.game.kernel.data.GameConfig.GameConfiger;
import com.suremoon.game.kernel.data.map.GameScreen;

import java.awt.*;

/**
 * Created by Water Moon on 2018/5/3.
 */
public class GridLines {
    static GameConfiger gc = GameConfiger.getGC();
    public static void showNetLine(Graphics graphics, GameScreen gameScreen, Point size){
        int px = gameScreen.getFocusPoint().x % size.x, py = gameScreen.getFocusPoint().y % size.y,
                dx = gc.getDesignScreenWdith() / size.x, dy = gc.getDesignScreenHeight() / size.y;
        ((Graphics2D)graphics).setStroke(new BasicStroke(1.8f));
        for(int i = 0; i < dx+1; ++i){
            graphics.drawLine(-px + i * size.x, 0, -px+i*size.x, gc.getDesignScreenHeight());
        }
        for(int i = 0; i < dy+1; ++i){
            graphics.drawLine(0, i * size.y - py, gc.getDesignScreenWdith(), i * size.y - py);
        }
    }
}
