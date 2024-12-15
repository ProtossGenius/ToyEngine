package com.suremoon.game.ag_pc_client.ui;

import java.awt.*;
import java.awt.event.MouseEvent;

import com.suremoon.game.ag_pc_client.show.pc_show.AGForm;

public class DragableUI extends IGameUI {
    Point lastPos = null;

    public DragableUI(AGForm agForm, Rectangle bundle) {
        super(agForm, bundle);
    }

    @Override
    protected void _draw(Graphics cache) {

    }

    @Override
    protected boolean _mouseReleased(MouseEvent e) {
        lastPos = null;
        return false;
    }

    @Override
    protected boolean _mouseDragged(MouseEvent e) {
        var p = e.getPoint();
        if (lastPos != null) {
            _bundle.x += (p.x - lastPos.x);
            _bundle.y += (p.y - lastPos.y);
        }

        lastPos = p;

        return true;
    }


}
