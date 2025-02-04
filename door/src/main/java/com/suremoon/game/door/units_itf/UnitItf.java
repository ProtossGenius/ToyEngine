package com.suremoon.game.door.units_itf;

import com.suremoon.game.door.attribute.ComplexAttribute;
import com.suremoon.game.door.attribute.ElementPriorities;
import com.suremoon.game.door.attribute.HurtCalcItf;
import com.suremoon.game.door.kernel.AGTypeInf;
import com.suremoon.game.door.kernel.DieDo;
import com.suremoon.game.door.kernel.GRectItf;
import com.suremoon.game.door.kernel.UnitRemItf;
import com.suremoon.game.door.kernel.enums.LeaveStatus;
import com.suremoon.game.door.save_about.SerializeAble;
import com.suremoon.game.door.units_itf.skill_about.SkillManager;

import java.util.List;

public interface UnitItf extends AGTypeInf, GRectItf, SerializeAble {

    void setHurtCalcItf(HurtCalcItf hurtCalc);

    HurtCalcItf getHurtCalcItf();

    /**
     * @return 是否已死亡
     */
    boolean isDie();

    ComplexAttribute getAttribute();

    default void addBuff(String b) {
        getAttribute().getBuffManager().addBuff(b);
    }

    default void removeBuff(String buffName) {
        getAttribute().getBuffManager().removeBuff(buffName);
    }

    StateItf getState();

    void setState(StateItf state);

    void acceptCmd(CommandItf cmd);

    void setUnitRem(UnitRemItf itf);

    UnitRemItf getUnitRem();

    /**
     * @return 唯一ID
     */
    int getGid();

    void setGid(int gid);

    /**
     * @return 动画间隔时间
     */
    int getIntervalTime();

    /**
     * @param hel 治疗
     */
    void BeHeal(double hel);

    void setDieDo(DieDo dieDo);

    /**
     * 重生
     */
    void relive();

    void setShowName(String showName);

    String getShowName();

    LeaveStatus getLeaveStatus();

    void setLeaveStatus(LeaveStatus leaveStatus);

    /**
     * @param unitLeaveAction 当玩家设置为离开，被移除当前世界之后做什么
     */
    void setLeaveAction(UnitLeaveAction unitLeaveAction);

    UnitLeaveAction getLeaveAction();

    /**
     * @return 玩家所属的阵营
     */
    int getCamp();

    /**
     * @param camp 玩家所属的阵营
     */
    void setCamp(int camp);

    SkillManager getSkillManager();

    List<GoodsItf> getBag();

    // ------------- 以下是default实现 ------------
    default void BeHurt(double hurt) {
        double hp = this.getAttribute().getHp();
        hp = hp - hurt > 0 ? hp - hurt : 0;
        this.getAttribute().setHp(hp);
    }

    default void underAttack(UnitItf attacker, ElementPriorities elementPriorities) {
        double hurt = getHurtCalcItf().underAttack(this, elementPriorities);
        BeHurt(hurt);
        getUnitRem().underAttack(this, attacker, hurt);
    }

    /**
     * @param attacker 攻击者
     * @param ad       AD伤害
     * @param ap       AP伤害
     */
    default void underAttack(UnitItf attacker, double ad, double ap) {
        underAttack(attacker, new ElementPriorities(ad, ap));
    }

    /**
     * @param skillName 技能名
     * @return 是否成功施放技能
     */
    default boolean useSkill(String skillName) {
        return getSkillManager().useSkill(skillName, this);
    }

    void setSelectedIndex(int index);

    Integer getSelectedIndex();
}
