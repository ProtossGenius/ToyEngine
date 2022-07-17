package com.suremoon.game.configers;

import com.suremoon.game.kernel.data.GameConfig.GameConfiger;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Water Moon on 2018/1/16.
 */
public class JPicture extends JLabel{
    BufferedImage cache;
    boolean cacheResizeable = true;
    public JPicture(){
        cache = new BufferedImage(GameConfiger.DESIGN_SCREEN_WIDTH, GameConfiger.DESIGN_SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }


    public void refresh(){
        if(getDrawGraphics() == null)return;
        getDrawGraphics().drawImage(cache, 0, 0, getWidth(), getHeight(), null);
    }

    protected Graphics getDrawGraphics(){
        return super.getGraphics();
    }
    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        if(width <= 0 || height <= 0)return;
        if(cacheResizeable)
            cache = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public Graphics getGraphics() {
        return cache.getGraphics();
    }

    public BufferedImage getCache() {
        return cache;
    }

    public void setCacheResizeable(boolean cacheResizeable) {
        this.cacheResizeable = cacheResizeable;
    }
}
