package com.suremoon.game.ag_pc_client.mkids;

import com.suremoon.game.ag_pc_client.ui.GameUI;

import java.awt.event.MouseEvent;

public class UiMKID extends MKInpDeal {
    public static final UiMKID instace = new UiMKID();

    GameUI ui;

    private UiMKID() {
        super();
    }

    public void setMkid(MKInpDeal mkid) {
        this.mkid = mkid;
    }

    public void setGameUI(GameUI ui) {
        this.ui = ui;
    }

    @Override
    protected boolean _mouseMoved(MouseEvent e) {
        if (ui == null) return false;
        return ui.mouseMoved(e);
    }

    @Override
    protected boolean _mouseReleased(MouseEvent e) {
        if (ui == null) return false;
        return ui.mouseReleased(e);
    }

    @Override
    protected boolean _mousePressed(MouseEvent e) {
        if (ui == null) return false;
        return ui.mousePressed(e);
    }

    @Override
    protected boolean _mouseDragged(MouseEvent e) {
        if (ui == null) return false;
        return ui.mouseDragged(e);
    }
}
