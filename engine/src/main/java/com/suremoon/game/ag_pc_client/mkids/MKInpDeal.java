package com.suremoon.game.ag_pc_client.mkids;

import com.suremoon.game.ag_pc_client.controller.SMKeyBoard;
import com.suremoon.game.ag_pc_client.controller.SMMouse;
import com.suremoon.game.ag_pc_client.show.showable_rect.control_item.GRCtrlItem;
import com.suremoon.game.door.kernel.GameMapItf;
import com.suremoon.game.door.kernel.GameScreenItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.kernel.data.GameConfig.GameConfiger;
import com.suremoon.game.ag_pc_client.show.showable_rect.control_item.input_about.EventIncoming;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by Water Moon on 2018/3/23.
 */
public class MKInpDeal implements EventIncoming {
    MKInpDeal mkid = NULL_MKID;
    GRCtrlItem ci;
    SMKeyBoard smkb = null;
    SMMouse smm = null;
    GameScreenItf gameScreen;
    GameMapItf gameMap;

    public Point getLastPoint() {
        return lastPoint;
    }

    Point lastPoint = new Point(0, 0);
    Component parent;//MKInpDeal
    public static final MKInpDeal NULL_MKID = new NullMKID();


    public MKInpDeal(MKInpDeal mkid) {
        this(mkid.gameScreen);
        this.mkid = mkid;
        this.smkb = mkid.smkb;
        this.smm = mkid.smm;
    }

    public MKInpDeal(GameScreenItf gameScreen) {
        this.gameMap = gameScreen.getGameMap();
        this.gameScreen = gameScreen;
        this.smkb = mkid.smkb;
        this.smm = mkid.smm;
    }

    private MKInpDeal() {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        mkid.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        mkid.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        mkid.keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mkid.mouseClicked(e);
        Point focusPoint = gameScreen.getFocusPoint(), te = GameConfiger.gc.getDesignPos(e.getPoint());
        setLastPoint(new Point(focusPoint.x + te.x, focusPoint.y + te.y));
        gameScreen.setLastPoint(te);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mkid.mousePressed(e);
        Point p = gameScreen.getFocusPoint(), te = GameConfiger.gc.getDesignPos(e.getPoint());
        setLastPoint(new Point(p.x + te.x, p.y + te.y));
        gameScreen.setLastPoint(te);
    }

    public void setLastPoint(Point lastPoint) {
        this.lastPoint = lastPoint;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mkid.mouseReleased(e);
        Point p = gameScreen.getFocusPoint(), te = GameConfiger.gc.getDesignPos(e.getPoint());
        setLastPoint(new Point(p.x + te.x, p.y + te.y));
        gameScreen.setLastPoint(te);
    }

    public void setControlItem(GRCtrlItem ci) {
        mkid.setControlItem(ci);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mkid.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mkid.mouseExited(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mkid.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mkid.mouseMoved(e);
        Point p = gameScreen.getFocusPoint(), te = GameConfiger.gc.getDesignPos(e.getPoint());
        gameScreen.setLastPoint(GameConfiger.gc.getDesignPos(e.getPoint()));
        setLastPoint(new Point(p.x + te.x, p.y + te.y));
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

    public GameScreenItf getGameScreen() {
        return gameScreen;
    }

    public static class NullMKID extends MKInpDeal {
        public NullMKID() {
            smkb = new SMKeyBoard();
            smm = new SMMouse();
            ci = new GRCtrlItem();
        }

        @Override
        public void keyTyped(KeyEvent e) {
            smkb.keyTyped(e);
            ci.keyTyped(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            smkb.keyPressed(e);
            ci.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            smkb.keyReleased(e);
            ci.keyReleased(e);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            smm.mouseClicked(e);
            ci.mouseClicked(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            smm.mousePressed(e);
            ci.mousePressed(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            smm.mousePressed(e);
            ci.mouseReleased(e);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            smm.mouseEntered(e);
            ci.mouseEntered(e);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            smm.mouseExited(e);
            ci.mouseExited(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            smm.mouseDragged(e);
            ci.mouseExited(e);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            smm.mouseMoved(e);
            ci.mouseMoved(e);
        }

        @Override
        public void setControlItem(GRCtrlItem ci) {
            this.ci = ci;
            ci.setMkInpDeal(this);
            if (parent != null) {
                ci.setParent(parent);
            }
        }
    }

}
