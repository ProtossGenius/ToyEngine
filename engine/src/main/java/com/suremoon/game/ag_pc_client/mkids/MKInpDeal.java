package com.suremoon.game.ag_pc_client.mkids;

import com.suremoon.game.ag_pc_client.controller.SMKeyBoard;
import com.suremoon.game.ag_pc_client.controller.SMMouse;
import com.suremoon.game.ag_pc_client.show.showable_rect.control_item.GRCtrlItem;
import com.suremoon.game.ag_pc_client.show.showable_rect.control_item.input_about.EventIncoming;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Water Moon on 2018/3/23.
 */
public class MKInpDeal implements EventIncoming {

    MKInpDeal mkid;
    GRCtrlItem ci;
    SMKeyBoard smkb = null;
    SMMouse smm = null;

    Component parent; // MKInpDeal

    public MKInpDeal(MKInpDeal mkid) {
        this.mkid = mkid;
        if (mkid != null) {
            this.smkb = mkid.smkb;
            this.smm = mkid.smm;
        } else {
            this.smkb = new SMKeyBoard();
            this.smm = new SMMouse();
        }
    }

    public MKInpDeal() {
        this.smkb = new SMKeyBoard();
        this.smm = new SMMouse();
    }

    /**
     * @param e 事件
     * @return 返回是否消费掉这个事件
     */
    protected boolean _keyTyped(KeyEvent e) {
        return false;
    }

    @Override
    public final void keyTyped(KeyEvent e) {
        for (var m = this; m != null; m = m.mkid)
            if (m._keyTyped(e))
                return;
    }

    protected boolean _keyPressed(KeyEvent e) {
        return false;
    }

    @Override
    public final void keyPressed(KeyEvent e) {
        for (var m = this; m != null; m = m.mkid) if (m._keyPressed(e)) return;
    }

    protected boolean _keyReleased(KeyEvent e) {
        return false;
    }

    @Override
    public final void keyReleased(KeyEvent e) {
        for (var m = this; m != null; m = m.mkid) if (m._keyReleased(e)) return;
    }

    protected boolean _mouseClicked(MouseEvent e) {
        return false;
    }

    @Override
    public final void mouseClicked(MouseEvent e) {
        for (var m = this; m != null; m = m.mkid) if (m._mouseClicked(e)) return;


    }

    protected boolean _mousePressed(MouseEvent e) {
        return false;
    }

    @Override
    public final void mousePressed(MouseEvent e) {
        for (var m = this; m != null; m = m.mkid) if (m._mousePressed(e)) return;


    }

    protected boolean _mouseReleased(MouseEvent e) {
        return false;
    }

    @Override
    public final void mouseReleased(MouseEvent e) {
        for (var m = this; m != null; m = m.mkid) if (m._mouseReleased(e)) return;


    }

    protected boolean _mouseEntered(MouseEvent e) {
        return false;
    }

    @Override
    public final void mouseEntered(MouseEvent e) {
        for (var m = this; m != null; m = m.mkid) if (m._mouseEntered(e)) return;

    }

    protected boolean _mouseExited(MouseEvent e) {
        return false;
    }

    @Override
    public final void mouseExited(MouseEvent e) {
        for (var m = this; m != null; m = m.mkid) if (m._mouseExited(e)) return;

    }

    protected boolean _mouseDragged(MouseEvent e) {
        return false;
    }

    @Override
    public final void mouseDragged(MouseEvent e) {
        for (var m = this; m != null; m = m.mkid) if (m._mouseDragged(e)) return;

    }

    protected boolean _mouseMoved(MouseEvent e) {
        return false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (var m = this; m != null; m = m.mkid) if (m._mouseMoved(e)) return;
    }

    public void setParent(Component parent) {
        this.parent = parent;
        parent.addMouseListener(this);
        parent.addKeyListener(this);
        parent.addMouseMotionListener(this);
        if (this.ci != null) {
            ci.setParent(parent);
        }
    }

    public void setControlItem(GRCtrlItem ci) {
        mkid.setControlItem(ci);
    }
}
