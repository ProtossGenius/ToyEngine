package com.suremoon.game.ag_pc_client.show.showable_rect.string_show;

import java.awt.*;
import java.util.Iterator;

/**
 * 只能容纳固定数量的字符串，超出移除顶端数据
 */
public class ConsoleString extends StringShow{
    public ConsoleString(String str, Rectangle showArea, Font font) {
        super(str, showArea, font);
    }

    public ConsoleString(String str, Rectangle showArea) {
        super(str, showArea);
    }

    public ConsoleString(Rectangle showArea){
        super("", showArea);
    }

    @Override
    public boolean drawOn(Graphics gp) {
        return this.drawOn(gp, 0);
    }

    @Override
    public boolean drawOn(Graphics gp, int yChange) {
        boolean result = super.drawOn(gp, 0);
        int maxLength = super.MaxLines();
        synchronized (ssls){
            while(maxLength < ssls.size()){
                ssls.removeFirst();
            }
        }
        return result;
    }
}
