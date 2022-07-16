package com.suremoon.game.door.units_itf;

import com.suremoon.game.door.attribute.ElementPriorities;
import com.suremoon.game.door.attribute.ComplexAttribute;
import com.suremoon.game.door.attribute.HurtCalcItf;
import com.suremoon.game.door.kernel.AGTypeInf;
import com.suremoon.game.door.kernel.DieDo;
import com.suremoon.game.door.kernel.GRectItf;
import com.suremoon.game.door.kernel.UnitRemItf;
import com.suremoon.game.door.kernel.enums.LeaveStatus;
import com.suremoon.game.door.save_about.SerializeAble;
import com.suremoon.game.door.units_itf.skill_about.SkillManager;

public interface UnitItf extends AGTypeInf, GRectItf, SerializeAble {

    void setHurtCalcItf(HurtCalcItf hurtCalc);
    HurtCalcItf getHurtCalcItf();

    /**
     * @return ÊÇ·ñÒÑËÀÍö
     */
    boolean isDie();

    ComplexAttribute getAttribute();

    StateItf getState();

    void setState(StateItf state);

    void acceptBuff(BuffItf buff);
    void acceptCmd(CommandItf cmd);
    void setUnitRem(UnitRemItf itf);
    UnitRemItf getUnitRem();

    /**
     * @return Î¨Ò»ID
     */
    int getGid();

    void setGid(int gid);

    /**
     * @return ¶¯»­¼ä¸ôÊ±¼ä
     */
    int getIntervalTime();

    /**
     * @param hel ÖÎÁÆ
     */
    void BeHeal(double hel);

    void setDieDo(DieDo dieDo);

    /**
     * ÖØÉú
     */
    void relive();

    void setShowName(String showName);
    String getShowName();

    LeaveStatus getLeaveStatus();

    void setLeaveStatus(LeaveStatus leaveStatus);

    /**
     * @param unitLeaveAction µ±Íæ¼ÒÉèÖÃÎªÀë¿ª£¬±»ÒÆ³ýµ±Ç°ÊÀ½çÖ®ºó×öÊ²Ã´
     */
    void setLeaveAction(UnitLeaveAction unitLeaveAction);
    UnitLeaveAction getLeaveAction();

    /**
     * @return Íæ¼ÒËùÊôµÄÕóÓª
     */
    int getCamp();

    /**
     * @param camp Íæ¼ÒËùÊôµÄÕóÓª
     */
    void setCamp(int camp);

    SkillManager getSkillManager();

// ------------- ÒÔÏÂÊÇdefaultÊµÏÖ ------------
    default void BeHurt(double hurt){
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
     * @param attacker ¹¥»÷Õß
     * @param ad ADÉËº¦
     * @param ap APÉËº¦
     */
    default void underAttack(UnitItf attacker, double ad, double ap) {
        underAttack(attacker, new ElementPriorities(ad, ap));
    }

    /**
     *
     * @param skillName ¼¼ÄÜÃû
     * @return ÊÇ·ñ³É¹¦Ê©·Å¼¼ÄÜ
     */
    default boolean useSkill(String skillName){
        return getSkillManager().useSkill(skillName, this);
    }
}
