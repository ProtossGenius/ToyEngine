package com.suremoon.game.kernel.data.units;

import com.suremoon.game.door.attribute.AttributeAdapter;
import com.suremoon.game.door.attribute.ComplexAttribute;
import com.suremoon.game.door.attribute.HurtCalcItf;
import com.suremoon.game.door.gometry.GRect;
import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.kernel.DieDo;
import com.suremoon.game.door.kernel.UnitRemItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.kernel.enums.LeaveStatus;
import com.suremoon.game.door.netabout.message.MsgUnit;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.door.units_itf.*;
import com.suremoon.game.door.units_itf.skill_about.SkillManager;
import com.suremoon.game.kernel.initer.buff_init.BuffInfManager;
import com.suremoon.game.kernel.initer.state_init.StateInfManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.suremoon.game.door.kernel.DieDo.Default;

/**
 * Created by Water Moon on 2018/1/10.
 */
public class Unit extends GRect implements UnitItf {
    private List<GoodsItf> bag = new ArrayList<>();
    protected ComplexAttribute attrib;
    protected StateItf state;
    protected LinkedList<CommandItf> cmdList = new LinkedList<>();
    protected boolean buffCalc = true, cmdCalc = true;
    protected boolean isDie = false;
    private int uType, gid, intervalTime = 60;
    private DieDo dieDo = Default;
    private UnitRemItf unitRem = UnitRemItf.Null;
    private String showName = "";
    private LeaveStatus leaveStatus = LeaveStatus.None;
    private UnitLeaveAction unitLeaveAction = UnitLeaveAction.Null;
    private int camp;
    private SkillManager skillManager = new SkillManager();
    private HurtCalcItf hurtCalcItf = HurtCalcItf.Null;
    private Integer selectedIndex = -1;

    public Unit(int uType) {
        this.uType = uType;
        this.attrib = new ComplexAttribute(new AttributeAdapter(), BuffInfManager.bim::productBuff);
    }

    public Unit(Unit u, Object move) {
        super(u, null);
        this.attrib = u.attrib;
        this.state = u.state;
        this.cmdList = u.cmdList;
        this.uType = u.uType;
        this.gid = u.gid;
        this.intervalTime = u.intervalTime;
        this.buffCalc = u.buffCalc;
        this.isDie = u.isDie;
        this.dieDo = u.dieDo;
        this.unitRem = u.unitRem;
        this.showName = u.showName;
        this.leaveStatus = u.leaveStatus;
        this.skillManager = u.skillManager;
        this.setTransparency(u.getTransparency());
        u.attrib = null;
        u.state = null;
        u.cmdList = null;
        u.dieDo = null;
        u.unitRem = null;
        u.showName = null;
        u.leaveStatus = null;
        u.skillManager = null;
        u.setDrop(true);
    }

    public Unit(MsgUnit mu) {
        uType = mu.UnitType;
        setGid(mu.gid);
        setPos(new PointF(mu.pos_x, mu.pos_y));
        setDirect(new PointF(mu.direct_x, mu.direct_y));
        setTransparency(mu.trans);
        State ags;
        ags = StateInfManager.sm.productState(IDManager.getName(mu.StateType));
        ags.smp.currentTime = ags.smp.startTime + mu.StatePTime;
        ags.smp.passedTime = mu.StatePTime;
        setState(ags);
        setSize(mu.width, mu.height);
        showName = mu.showName;
        attrib = new ComplexAttribute(new AttributeAdapter(), BuffInfManager.bim::productBuff);
        attrib.setHp(mu.hp);
        attrib.setBasicHp(mu.max_hp);
        attrib.setMp(mu.mp);
        attrib.setBasicMp(mu.max_mp);
        setSelectedIndex(mu.selectedGoods);
    }

    @Override
    public int getAGType() {
        return uType;
    }

    @Override
    public void setAGType(int i) {
        this.uType = i;
    }

    @Override
    public void doCalc(WorldItf world, WorldMgrItf worldMgr) {
        super.doCalc(world, worldMgr);
        dieCheck();
        if (state != null) state.doCalc(world, worldMgr); // state shouldn't be null
        else
            System.err.print(
                    "Error happened in Unit, witch uTypeId is " + uType + ", whose state is null.");
        if (cmdCalc) {
            synchronized (this.cmdList) {
                this.unitRem.doCalc(this, world, worldMgr);
                for (Iterator<CommandItf> itor = cmdList.iterator(); itor.hasNext(); ) {
                    CommandItf cmd = itor.next();
                    if (cmd.getCmdAction().actionDo(cmd, world, worldMgr)) {
                        itor.remove();
                    }
                    cmd.updateFlagTime();
                }
            }
        }
        if (isDie()) {
            dieDo(world, worldMgr);
        }
    }

    private void dieCheck() {
        if (getState().getAGType() == IDManager.getID("die")) {
            attrib.setHp(0);
            isDie = true;
            return;
        }
        if (attrib.getHp() <= 0) {
            attrib.setHp(0);
            isDie = true;
            cmdCalc = false;
            buffCalc = false;
            setState(StateInfManager.sm.productState("die"));
        }
    }

    // =====================================getter and setter=====================================

    @Override
    public boolean isDie() {
        return isDie;
    }

    @Override
    public ComplexAttribute getAttribute() {
        return attrib;
    }

    @Override
    public StateItf getState() {
        return state;
    }

    public void setState(StateItf state) {
        this.state = createState(state);
    }

    StateItf createState(StateItf state) {
        if (state == null) return null;
        return state.createState(this);
    }

    @Override
    public void acceptCmd(CommandItf cmd) {
        if (cmd == null) {
            return;
        }
        cmd = cmd.setOwner(this);
        synchronized (this.cmdList) {
            if (cmd.isAppendCmd()) {
                addCmd(cmd);
            } else {
                setCmd(cmd);
            }
        }
    }

    private void setCmd(CommandItf cmd) {
        this.cmdList.clear();
        addCmd(cmd);
    }

    private void addCmd(CommandItf cmd) {
        this.cmdList.add(cmd);
    }

    @Override
    public UnitRemItf getUnitRem() {
        return unitRem;
    }

    @Override
    public void setUnitRem(UnitRemItf itf) {
        if (itf != null) {
            unitRem = itf;
        }
    }

    @Override
    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    @Override
    public int getIntervalTime() {
        return intervalTime;
    }

    @Override
    public void BeHeal(double hel) {
        if (hel < 0) hel = 0;
        double hp = attrib.getHp(), max_hp = attrib.getMaxHp();
        this.getAttribute().setHp(Math.min(hp + hel, max_hp));
    }

    @Override
    public void setDieDo(DieDo dieDo) {
        this.dieDo = dieDo;
    }

    private void dieDo(WorldItf world, WorldMgrItf worldMgr) {
        this.dieDo.Do(this, world, worldMgr);
    }

    @Override
    public void relive() {
        setState(StateInfManager.sm.productState("paused"));
        getAttribute().setHp(getAttribute().getMaxHp());
        cmdCalc = true;
        buffCalc = true;
        isDie = false;
    }

    public String getShowName() {
        return showName;
    }

    @Override
    public void setShowName(String showName) {
        this.showName = showName;
    }

    @Override
    public LeaveStatus getLeaveStatus() {
        return leaveStatus;
    }

    @Override
    public void setLeaveStatus(LeaveStatus leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    @Override
    public UnitLeaveAction getLeaveAction() {
        return unitLeaveAction;
    }

    @Override
    public void setLeaveAction(UnitLeaveAction unitLeaveAction) {
        this.unitLeaveAction = unitLeaveAction;
    }

    @Override
    public int getCamp() {
        return camp;
    }

    @Override
    public void setCamp(int camp) {
        this.camp = camp;
    }

    @Override
    public SkillManager getSkillManager() {
        return this.skillManager;
    }

    @Override
    public List<GoodsItf> getBag() {
        return bag;
    }

    @Override
    public HurtCalcItf getHurtCalcItf() {
        return hurtCalcItf;
    }

    @Override
    public void setHurtCalcItf(HurtCalcItf hurtCalcItf) {
        this.hurtCalcItf = hurtCalcItf;
    }

    @Override
    public Integer getSelectedIndex() {
        return selectedIndex;
    }

    @Override
    public void setSelectedIndex(int index) {
        this.selectedIndex = index;
    }
}
