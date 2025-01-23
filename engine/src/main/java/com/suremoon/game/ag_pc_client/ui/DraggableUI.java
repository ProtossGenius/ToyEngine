package com.suremoon.game.ag_pc_client.ui;

import com.suremoon.game.ag_pc_client.show.pc_show.AGForm;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DraggableUI extends IGameUI {
    Point lastPos = null;

    public DraggableUI(AGForm agForm, Rectangle bundle) {
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
            this.onMove(p.x - lastPos.x, p.y - lastPos.y);
        }

        lastPos = p;

        return true;
    }


}
