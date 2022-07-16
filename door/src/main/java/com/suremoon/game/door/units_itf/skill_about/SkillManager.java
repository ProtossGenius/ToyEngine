package com.suremoon.game.door.units_itf.skill_about;

import com.suremoon.game.door.attribute.AttributeAdapter;
import com.suremoon.game.door.units_itf.UnitItf;

import java.util.HashMap;
import java.util.Map;
/**
 * ¼¼ÄÜ¹ÜÀíÆ÷
 */
public class SkillManager {
    private Map<String, SkillInfo> skillInfoMap = new HashMap<>();

    /** Ê¹ÓÃ¼¼ÄÜ
     * ÅÐ¶Ï¼¼ÄÜÊÇ·ñ¿ÉÒÔÊÍ·Å£¬Èô¿ÉÒÔÊÍ·ÅÔò¸üÐÂ×´Ì¬²¢Ê©·Å¼¼ÄÜ
     * @param skillName
     * @param unit
     * @return ÊÇ·ñÊÍ·Å³É¹¦
     */
    public boolean useSkill(String skillName, UnitItf unit){
        SkillInfo info = skillInfoMap.get(skillName);
        AttributeAdapter attr = unit.getAttribute();
        if(info == null)return false;
        if(System.currentTimeMillis() - info.lastTime < info.cd){
            return false;
        }
        if(attr.getMp() < info.cost){
            return false;
        }
        attr.setMp(attr.getMp() - info.cost);
        info.lastTime = System.currentTimeMillis();

        return true;
    }
    public SkillManager addSkillInfo(String skillName, int cd, int cost, SkillDo skillDo){
        skillInfoMap.put(skillName, new SkillInfo(cd, cost, skillDo));
        return this;
    }
    public SkillInfo getSkillInfo(String skillName){
        return skillInfoMap.get(skillName);
    }
    public Map<String, SkillInfo> getSkillInfoMap() {
        return skillInfoMap;
    }
}
