package com.suremoon.game.kernel.data.command_analysis;

import com.suremoon.game.door.client.ActionData;
import com.suremoon.game.door.client.CmdCreatorItf;
import com.suremoon.game.door.client.StateAction;
import java.util.ArrayList;

/** Created by Water Moon on 2018/3/16. */
public class CANode {
  ActionData cmdId;
  ArrayList<CANode> next;
  boolean isLeaf;
  CmdCreatorItf cc;
  StateAction sa = new StateAction();

  public CANode(ActionData cmdId) {
    this.cmdId = cmdId;
    next = new ArrayList<>();
    isLeaf = false;
  }

  public CANode(ActionData cmdId, CmdCreatorItf cc) {
    this(cmdId, true);
    this.cc = cc;
  }

  public CANode(ActionData cmdId, boolean isLeaf) {
    this(cmdId);
    this.isLeaf = isLeaf;
  }

  public CmdCreatorItf getCmdCreater() {
    return cc;
  }

  public void setCmdCreater(CmdCreator cc) {
    this.cc = cc;
  }

  public ArrayList<CANode> getNext() {
    return next;
  }

  public void setNext(ArrayList<CANode> next) {
    this.next = next;
  }

  public StateAction getSa() {
    return sa;
  }

  public void setSa(StateAction sa) {
    this.sa = sa;
  }

  public ActionData getCmdId() {
    return cmdId;
  }

  public boolean isLeaf() {
    return isLeaf;
  }
}
