package com.suremoon.game.door.client;

import java.awt.*;

public interface PicAreaItf {
    void show(Graphics gp, int x, int y, int width, int height);
    default void show(Graphics gp, Rectangle rect){
        show(gp, rect.x, rect.y, rect.width, rect.height);
    }
}
