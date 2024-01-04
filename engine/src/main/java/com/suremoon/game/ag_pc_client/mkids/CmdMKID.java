package com.suremoon.game.ag_pc_client.mkids;

import com.suremoon.game.ag_pc_client.show.pc_show.AGForm;
import com.suremoon.game.door.client.CmdAnalysisItf;
import com.suremoon.game.door.client.CmdCreatorItf;
import com.suremoon.game.door.client.StateAction;
import com.suremoon.game.door.kernel.GameMapItf;
import com.suremoon.game.door.kernel.GameScreenItf;
import com.suremoon.game.door.netabout.AGMessage;
import com.suremoon.game.door.netabout.message.MsgScreenInfo;
import com.suremoon.game.door.netabout.message.MsgUnit;
import com.suremoon.game.door.units_itf.CommandItf;
import com.suremoon.game.kernel.data.GameConfig.GameConfiger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Water Moon on 2018/3/23.
 */
public class CmdMKID extends MKInpDeal {
    CmdAnalysisItf cmdAnalysis;
    OnCmdItf onCmd = null;
    AGForm form;
    int targetUnit = -1;
    GameScreenItf gameScreen;
    GameMapItf gameMap;

    Point lastPoint = new Point(0, 0);

    public CmdMKID(MKInpDeal mkid, AGForm form, CmdAnalysisItf cmdAnalysis) {
        super(mkid);
        this.form = form;
        this.gameScreen = this.form.getGameScreen();
        this.gameMap = gameScreen.getGameMap();
        this.cmdAnalysis = cmdAnalysis;
    }

    public CmdMKID(AGForm form, CmdAnalysisItf cmdAnalysis) {
        this(null, form, cmdAnalysis);
    }

    public void setOnCmd(OnCmdItf onCmd) {
        this.onCmd = onCmd;
    }


    @Override
    protected boolean _keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) return false;
        cmdAnalysis.putAction(0, e.getKeyCode(), 0);
        return CADo();

    }

    @Override
    protected boolean _keyReleased(KeyEvent e) {
        cmdAnalysis.putAction(0, e.getKeyCode(), 1);
        return CADo();
    }

    @Override
    protected boolean _mouseClicked(MouseEvent e) {
        Point focusPoint = gameScreen.getFocusPoint(), te = GameConfiger.gc.getDesignPos(e.getPoint());
        setLastPoint(new Point(focusPoint.x + te.x, focusPoint.y + te.y));
        gameScreen.setLastPoint(te);
        return false;
    }

    @Override
    protected boolean _mousePressed(MouseEvent e) {
        Point p = gameScreen.getFocusPoint(), te = GameConfiger.gc.getDesignPos(e.getPoint());
        setLastPoint(new Point(p.x + te.x, p.y + te.y));
        gameScreen.setLastPoint(te);
        choiceUnit();
        cmdAnalysis.putAction(1, e.getButton(), 0);
        return CADo();

    }

    @Override
    protected boolean _mouseReleased(MouseEvent e) {
        Point p = gameScreen.getFocusPoint(), te = GameConfiger.gc.getDesignPos(e.getPoint());
        setLastPoint(new Point(p.x + te.x, p.y + te.y));
        gameScreen.setLastPoint(te);
        choiceUnit();
        cmdAnalysis.putAction(1, e.getButton(), 1);
        return CADo();

    }

    @Override
    public boolean _mouseDragged(MouseEvent e) {
        return choiceUnit();

    }

    @Override
    public boolean _mouseMoved(MouseEvent e) {
        Point p = gameScreen.getFocusPoint(), te = GameConfiger.gc.getDesignPos(e.getPoint());
        gameScreen.setLastPoint(GameConfiger.gc.getDesignPos(e.getPoint()));
        setLastPoint(new Point(p.x + te.x, p.y + te.y));
        return choiceUnit();

    }

    public boolean CADo() {
        CmdCreatorItf cc;
        StateAction sa;
        //        CmdAnalysis.ActionData ad[] = ca.getActionList();
        //        for(int i = 0; i < ad.length; ++i){
        //            System.out.println(ad[i]);
        //        }
        //        System.out.println("==============================");
        if ((sa = cmdAnalysis.getStateAction()) != null) {
            if (sa.sa_type != StateAction.NOTHING) {
                // TODO: 2018/4/5 此处应当加入关于sa的处理。
                //                System.out.println("sa type is" + sa.sa_type);
            }
        }
        if ((cc = cmdAnalysis.getCmdCreator()) != null) {
            /** test is error when cmd->msg->cmd(c/s) */
            CommandItf cmd =
                    cc.productCommand(targetUnit, lastPoint)
                            .setAppendCmd(smkb.isKeyPressed(KeyEvent.VK_SHIFT));
            this.onCmd.OnCommand(cmd);
            return true;
        }
        return false;
    }

    public CmdAnalysisItf getCmdAnalysis() {
        return cmdAnalysis;
    }

    public void setCmdAnalysis(CmdAnalysisItf ca) {
        this.cmdAnalysis = ca;
    }

    private boolean choiceUnit() {
        MsgScreenInfo screenInfo = form.getScreenInfo();
        AGMessage[] units = screenInfo.getShowInfo()[1];
        targetUnit = -1;
        for (int i = 0; i < units.length; ++i) {
            MsgUnit unit = (MsgUnit) units[i];

            if (new Rectangle(unit.pos_x, unit.pos_y, unit.width, unit.height).contains(this.lastPoint)) {
                targetUnit = unit.gid;
                return true;
            }
        }
        return false;
    }


    public Point getLastPoint() {
        return lastPoint;
    }

    protected void setLastPoint(Point lastPoint) {
        this.lastPoint = lastPoint;
    }
}
