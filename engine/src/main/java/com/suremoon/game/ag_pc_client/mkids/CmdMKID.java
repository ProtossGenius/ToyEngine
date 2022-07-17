package com.suremoon.game.ag_pc_client.mkids;

import com.suremoon.game.ag_pc_client.show.pc_show.AGForm;
import com.suremoon.game.door.client.CmdAnalysisItf;
import com.suremoon.game.door.client.CmdCreatorItf;
import com.suremoon.game.door.client.StateAction;
import com.suremoon.game.door.netabout.AGMessage;
import com.suremoon.game.door.netabout.message.MsgScreenInfo;
import com.suremoon.game.door.netabout.message.MsgUnit;
import com.suremoon.game.door.units_itf.CommandItf;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/** Created by Water Moon on 2018/3/23. */
public class CmdMKID extends MKInpDeal {
  CmdAnalysisItf cmdAnalysis;
  OnCmdItf onCmd = null;
  AGForm form;
  int targetUnit = -1;

  public CmdMKID(AGForm form, CmdAnalysisItf cmdAnalysis) {
    super(form.getGameScreen());
    this.form = form;
    this.cmdAnalysis = cmdAnalysis;
  }

  public void setOnCmd(OnCmdItf onCmd) {
    this.onCmd = onCmd;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    super.keyTyped(e);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    super.keyPressed(e);
    if (e.getKeyCode() == KeyEvent.VK_SHIFT) return;
    cmdAnalysis.putAction(0, e.getKeyCode(), 0);
    CADo();
  }

  @Override
  public void keyReleased(KeyEvent e) {
    super.keyReleased(e);
    cmdAnalysis.putAction(0, e.getKeyCode(), 1);
    CADo();
  }

  @Override
  public void mousePressed(MouseEvent e) {
    super.mousePressed(e);
    choiceUnit();
    cmdAnalysis.putAction(1, e.getButton(), 0);
    CADo();
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    super.mouseReleased(e);
    choiceUnit();
    cmdAnalysis.putAction(1, e.getButton(), 1);
    CADo();
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    super.mouseDragged(e);
    choiceUnit();
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    super.mouseMoved(e);
    choiceUnit();
  }

  public void CADo() {
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
    }
  }

  public CmdAnalysisItf getCmdAnalysis() {
    return cmdAnalysis;
  }

  public void setCmdAnalysis(CmdAnalysisItf ca) {
    this.cmdAnalysis = ca;
  }

  private void choiceUnit() {
    MsgScreenInfo screenInfo = form.getScreenInfo();
    AGMessage[] units = screenInfo.getShowInfo()[1];
    targetUnit = -1;
    for (int i = 0; i < units.length; ++i) {
      MsgUnit unit = (MsgUnit) units[i];

      if (new Rectangle(unit.pos_x, unit.pos_y, unit.width, unit.height).contains(this.lastPoint)) {
        targetUnit = unit.gid;
        break;
      }
    }
  }
}
