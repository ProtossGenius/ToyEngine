package com.suremoon.gametest.real_game_test.my_ui;

import com.suremoon.game.ag_pc_client.show.pc_show.AGForm;
import com.suremoon.game.ag_pc_client.ui.IGameUI;

import java.awt.*;
import java.awt.event.MouseEvent;


public class BagUI extends IGameUI {
    private Color color = Color.BLUE;

    public BagUI(AGForm agForm) {
        super(agForm, new Rectangle(0, 0, 500, 500));
    }

    @Override
    protected void _draw(Graphics cache) {
        cache.setColor(color);
        cache.fillRect(0, 0, _bundle.width, _bundle.height);
    }

    @Override
    protected boolean _mousePressed(MouseEvent e) {
        color = Color.RED;
        setNeedRedraw(true);
        return super._mousePressed(e);
    }

    @Override
    protected boolean _mouseMoved(MouseEvent e) {
        return super._mouseMoved(e);
    }

    @Override
    protected boolean _mouseReleased(MouseEvent e) {
        color = Color.BLUE;
        setNeedRedraw(true);
        return super._mouseReleased(e);
    }
}
